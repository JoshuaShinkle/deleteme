package com.google.android.exoplayer2.extractor.p037ts;

import android.util.Log;
import com.google.android.exoplayer2.C3322C;
import com.google.android.exoplayer2.extractor.ExtractorOutput;
import com.google.android.exoplayer2.extractor.p037ts.TsPayloadReader;
import com.google.android.exoplayer2.util.ParsableBitArray;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.TimestampAdjuster;

/* loaded from: classes.dex */
public final class PesReader implements TsPayloadReader {
    private static final int HEADER_SIZE = 9;
    private static final int MAX_HEADER_EXTENSION_SIZE = 10;
    private static final int PES_SCRATCH_SIZE = 10;
    private static final int STATE_FINDING_HEADER = 0;
    private static final int STATE_READING_BODY = 3;
    private static final int STATE_READING_HEADER = 1;
    private static final int STATE_READING_HEADER_EXTENSION = 2;
    private static final String TAG = "PesReader";
    private int bytesRead;
    private boolean dataAlignmentIndicator;
    private boolean dtsFlag;
    private int extendedHeaderLength;
    private int payloadSize;
    private boolean ptsFlag;
    private final ElementaryStreamReader reader;
    private boolean seenFirstDts;
    private long timeUs;
    private TimestampAdjuster timestampAdjuster;
    private final ParsableBitArray pesScratch = new ParsableBitArray(new byte[10]);
    private int state = 0;

    public PesReader(ElementaryStreamReader elementaryStreamReader) {
        this.reader = elementaryStreamReader;
    }

    private boolean continueRead(ParsableByteArray parsableByteArray, byte[] bArr, int i9) {
        int iMin = Math.min(parsableByteArray.bytesLeft(), i9 - this.bytesRead);
        if (iMin <= 0) {
            return true;
        }
        if (bArr == null) {
            parsableByteArray.skipBytes(iMin);
        } else {
            parsableByteArray.readBytes(bArr, this.bytesRead, iMin);
        }
        int i10 = this.bytesRead + iMin;
        this.bytesRead = i10;
        return i10 == i9;
    }

    private boolean parseHeader() {
        this.pesScratch.setPosition(0);
        int bits = this.pesScratch.readBits(24);
        if (bits != 1) {
            Log.w(TAG, "Unexpected start code prefix: " + bits);
            this.payloadSize = -1;
            return false;
        }
        this.pesScratch.skipBits(8);
        int bits2 = this.pesScratch.readBits(16);
        this.pesScratch.skipBits(5);
        this.dataAlignmentIndicator = this.pesScratch.readBit();
        this.pesScratch.skipBits(2);
        this.ptsFlag = this.pesScratch.readBit();
        this.dtsFlag = this.pesScratch.readBit();
        this.pesScratch.skipBits(6);
        int bits3 = this.pesScratch.readBits(8);
        this.extendedHeaderLength = bits3;
        if (bits2 == 0) {
            this.payloadSize = -1;
        } else {
            this.payloadSize = ((bits2 + 6) - 9) - bits3;
        }
        return true;
    }

    private void parseHeaderExtension() {
        this.pesScratch.setPosition(0);
        this.timeUs = C3322C.TIME_UNSET;
        if (this.ptsFlag) {
            this.pesScratch.skipBits(4);
            this.pesScratch.skipBits(1);
            this.pesScratch.skipBits(1);
            long bits = (this.pesScratch.readBits(3) << 30) | (this.pesScratch.readBits(15) << 15) | this.pesScratch.readBits(15);
            this.pesScratch.skipBits(1);
            if (!this.seenFirstDts && this.dtsFlag) {
                this.pesScratch.skipBits(4);
                this.pesScratch.skipBits(1);
                this.pesScratch.skipBits(1);
                this.pesScratch.skipBits(1);
                this.timestampAdjuster.adjustTsTimestamp((this.pesScratch.readBits(3) << 30) | (this.pesScratch.readBits(15) << 15) | this.pesScratch.readBits(15));
                this.seenFirstDts = true;
            }
            this.timeUs = this.timestampAdjuster.adjustTsTimestamp(bits);
        }
    }

    private void setState(int i9) {
        this.state = i9;
        this.bytesRead = 0;
    }

    @Override // com.google.android.exoplayer2.extractor.p037ts.TsPayloadReader
    public final void consume(ParsableByteArray parsableByteArray, boolean z8) {
        if (z8) {
            int i9 = this.state;
            if (i9 == 2) {
                Log.w(TAG, "Unexpected start indicator reading extended header");
            } else if (i9 == 3) {
                if (this.payloadSize != -1) {
                    Log.w(TAG, "Unexpected start indicator: expected " + this.payloadSize + " more bytes");
                }
                this.reader.packetFinished();
            }
            setState(1);
        }
        while (parsableByteArray.bytesLeft() > 0) {
            int i10 = this.state;
            if (i10 != 0) {
                if (i10 != 1) {
                    if (i10 == 2) {
                        if (continueRead(parsableByteArray, this.pesScratch.data, Math.min(10, this.extendedHeaderLength)) && continueRead(parsableByteArray, null, this.extendedHeaderLength)) {
                            parseHeaderExtension();
                            this.reader.packetStarted(this.timeUs, this.dataAlignmentIndicator);
                            setState(3);
                        }
                    } else if (i10 == 3) {
                        int iBytesLeft = parsableByteArray.bytesLeft();
                        int i11 = this.payloadSize;
                        int i12 = i11 != -1 ? iBytesLeft - i11 : 0;
                        if (i12 > 0) {
                            iBytesLeft -= i12;
                            parsableByteArray.setLimit(parsableByteArray.getPosition() + iBytesLeft);
                        }
                        this.reader.consume(parsableByteArray);
                        int i13 = this.payloadSize;
                        if (i13 != -1) {
                            int i14 = i13 - iBytesLeft;
                            this.payloadSize = i14;
                            if (i14 == 0) {
                                this.reader.packetFinished();
                                setState(1);
                            }
                        }
                    }
                } else if (continueRead(parsableByteArray, this.pesScratch.data, 9)) {
                    setState(parseHeader() ? 2 : 0);
                }
            } else {
                parsableByteArray.skipBytes(parsableByteArray.bytesLeft());
            }
        }
    }

    @Override // com.google.android.exoplayer2.extractor.p037ts.TsPayloadReader
    public void init(TimestampAdjuster timestampAdjuster, ExtractorOutput extractorOutput, TsPayloadReader.TrackIdGenerator trackIdGenerator) {
        this.timestampAdjuster = timestampAdjuster;
        this.reader.createTracks(extractorOutput, trackIdGenerator);
    }

    @Override // com.google.android.exoplayer2.extractor.p037ts.TsPayloadReader
    public final void seek() {
        this.state = 0;
        this.bytesRead = 0;
        this.seenFirstDts = false;
        this.reader.seek();
    }
}
