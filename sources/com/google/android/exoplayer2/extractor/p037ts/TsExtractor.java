package com.google.android.exoplayer2.extractor.p037ts;

import android.util.SparseArray;
import android.util.SparseBooleanArray;
import android.util.SparseIntArray;
import com.google.android.exoplayer2.C3322C;
import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.extractor.Extractor;
import com.google.android.exoplayer2.extractor.ExtractorInput;
import com.google.android.exoplayer2.extractor.ExtractorOutput;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.extractor.PositionHolder;
import com.google.android.exoplayer2.extractor.SeekMap;
import com.google.android.exoplayer2.extractor.p037ts.TsPayloadReader;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.ParsableBitArray;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.TimestampAdjuster;
import com.google.android.exoplayer2.util.Util;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* loaded from: classes.dex */
public final class TsExtractor implements Extractor {
    private static final int BUFFER_SIZE = 9400;
    private static final int MAX_PID_PLUS_ONE = 8192;
    public static final int MODE_HLS = 2;
    public static final int MODE_MULTI_PMT = 0;
    public static final int MODE_SINGLE_PMT = 1;
    private static final int SNIFF_TS_PACKET_COUNT = 5;
    private static final int TS_PACKET_SIZE = 188;
    private static final int TS_PAT_PID = 0;
    public static final int TS_STREAM_TYPE_AAC_ADTS = 15;
    public static final int TS_STREAM_TYPE_AAC_LATM = 17;
    public static final int TS_STREAM_TYPE_AC3 = 129;
    public static final int TS_STREAM_TYPE_DTS = 138;
    public static final int TS_STREAM_TYPE_DVBSUBS = 89;
    public static final int TS_STREAM_TYPE_E_AC3 = 135;
    public static final int TS_STREAM_TYPE_H262 = 2;
    public static final int TS_STREAM_TYPE_H264 = 27;
    public static final int TS_STREAM_TYPE_H265 = 36;
    public static final int TS_STREAM_TYPE_HDMV_DTS = 130;
    public static final int TS_STREAM_TYPE_ID3 = 21;
    public static final int TS_STREAM_TYPE_MPA = 3;
    public static final int TS_STREAM_TYPE_MPA_LSF = 4;
    public static final int TS_STREAM_TYPE_SPLICE_INFO = 134;
    private static final int TS_SYNC_BYTE = 71;
    private int bytesSinceLastSync;
    private final SparseIntArray continuityCounters;
    private TsPayloadReader id3Reader;
    private final int mode;
    private ExtractorOutput output;
    private final TsPayloadReader.Factory payloadReaderFactory;
    private int remainingPmts;
    private final List<TimestampAdjuster> timestampAdjusters;
    private final SparseBooleanArray trackIds;
    private boolean tracksEnded;
    private final ParsableByteArray tsPacketBuffer;
    private final SparseArray<TsPayloadReader> tsPayloadReaders;
    public static final ExtractorsFactory FACTORY = new ExtractorsFactory() { // from class: com.google.android.exoplayer2.extractor.ts.TsExtractor.1
        @Override // com.google.android.exoplayer2.extractor.ExtractorsFactory
        public Extractor[] createExtractors() {
            return new Extractor[]{new TsExtractor()};
        }
    };
    private static final long AC3_FORMAT_IDENTIFIER = Util.getIntegerCodeForString("AC-3");
    private static final long E_AC3_FORMAT_IDENTIFIER = Util.getIntegerCodeForString("EAC3");
    private static final long HEVC_FORMAT_IDENTIFIER = Util.getIntegerCodeForString("HEVC");

    @Retention(RetentionPolicy.SOURCE)
    public @interface Mode {
    }

    public class PatReader implements SectionPayloadReader {
        private final ParsableBitArray patScratch = new ParsableBitArray(new byte[4]);

        public PatReader() {
        }

        @Override // com.google.android.exoplayer2.extractor.p037ts.SectionPayloadReader
        public void consume(ParsableByteArray parsableByteArray) {
            if (parsableByteArray.readUnsignedByte() != 0) {
                return;
            }
            parsableByteArray.skipBytes(7);
            int iBytesLeft = parsableByteArray.bytesLeft() / 4;
            for (int i9 = 0; i9 < iBytesLeft; i9++) {
                parsableByteArray.readBytes(this.patScratch, 4);
                int bits = this.patScratch.readBits(16);
                this.patScratch.skipBits(3);
                if (bits == 0) {
                    this.patScratch.skipBits(13);
                } else {
                    int bits2 = this.patScratch.readBits(13);
                    TsExtractor.this.tsPayloadReaders.put(bits2, new SectionReader(TsExtractor.this.new PmtReader(bits2)));
                    TsExtractor.access$108(TsExtractor.this);
                }
            }
            if (TsExtractor.this.mode != 2) {
                TsExtractor.this.tsPayloadReaders.remove(0);
            }
        }

        @Override // com.google.android.exoplayer2.extractor.p037ts.SectionPayloadReader
        public void init(TimestampAdjuster timestampAdjuster, ExtractorOutput extractorOutput, TsPayloadReader.TrackIdGenerator trackIdGenerator) {
        }
    }

    public class PmtReader implements SectionPayloadReader {
        private static final int TS_PMT_DESC_AC3 = 106;
        private static final int TS_PMT_DESC_DTS = 123;
        private static final int TS_PMT_DESC_DVBSUBS = 89;
        private static final int TS_PMT_DESC_EAC3 = 122;
        private static final int TS_PMT_DESC_ISO639_LANG = 10;
        private static final int TS_PMT_DESC_REGISTRATION = 5;
        private final int pid;
        private final ParsableBitArray pmtScratch = new ParsableBitArray(new byte[5]);
        private final SparseArray<TsPayloadReader> trackIdToReaderScratch = new SparseArray<>();
        private final SparseIntArray trackIdToPidScratch = new SparseIntArray();

        public PmtReader(int i9) {
            this.pid = i9;
        }

        /* JADX WARN: Removed duplicated region for block: B:18:0x0047  */
        /* JADX WARN: Removed duplicated region for block: B:21:0x004d  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        private TsPayloadReader.EsInfo readEsInfo(ParsableByteArray parsableByteArray, int i9) {
            int position = parsableByteArray.getPosition();
            int i10 = i9 + position;
            int i11 = -1;
            String strTrim = null;
            ArrayList arrayList = null;
            while (parsableByteArray.getPosition() < i10) {
                int unsignedByte = parsableByteArray.readUnsignedByte();
                int position2 = parsableByteArray.getPosition() + parsableByteArray.readUnsignedByte();
                if (unsignedByte == 5) {
                    long unsignedInt = parsableByteArray.readUnsignedInt();
                    if (unsignedInt == TsExtractor.AC3_FORMAT_IDENTIFIER) {
                        i11 = 129;
                    } else if (unsignedInt == TsExtractor.E_AC3_FORMAT_IDENTIFIER) {
                        i11 = 135;
                    } else if (unsignedInt == TsExtractor.HEVC_FORMAT_IDENTIFIER) {
                        i11 = 36;
                    }
                } else if (unsignedByte != 106) {
                    if (unsignedByte != 122) {
                        if (unsignedByte == 123) {
                            i11 = TsExtractor.TS_STREAM_TYPE_DTS;
                        } else if (unsignedByte == 10) {
                            strTrim = parsableByteArray.readString(3).trim();
                        } else if (unsignedByte == 89) {
                            ArrayList arrayList2 = new ArrayList();
                            while (parsableByteArray.getPosition() < position2) {
                                String strTrim2 = parsableByteArray.readString(3).trim();
                                int unsignedByte2 = parsableByteArray.readUnsignedByte();
                                byte[] bArr = new byte[4];
                                parsableByteArray.readBytes(bArr, 0, 4);
                                arrayList2.add(new TsPayloadReader.DvbSubtitleInfo(strTrim2, unsignedByte2, bArr));
                            }
                            arrayList = arrayList2;
                            i11 = 89;
                        }
                    }
                }
                parsableByteArray.skipBytes(position2 - parsableByteArray.getPosition());
            }
            parsableByteArray.setPosition(i10);
            return new TsPayloadReader.EsInfo(i11, strTrim, arrayList, Arrays.copyOfRange(parsableByteArray.data, position, i10));
        }

        @Override // com.google.android.exoplayer2.extractor.p037ts.SectionPayloadReader
        public void consume(ParsableByteArray parsableByteArray) {
            TimestampAdjuster timestampAdjuster;
            if (parsableByteArray.readUnsignedByte() != 2) {
                return;
            }
            if (TsExtractor.this.mode == 1 || TsExtractor.this.mode == 2 || TsExtractor.this.remainingPmts == 1) {
                timestampAdjuster = (TimestampAdjuster) TsExtractor.this.timestampAdjusters.get(0);
            } else {
                timestampAdjuster = new TimestampAdjuster(((TimestampAdjuster) TsExtractor.this.timestampAdjusters.get(0)).getFirstSampleTimestampUs());
                TsExtractor.this.timestampAdjusters.add(timestampAdjuster);
            }
            parsableByteArray.skipBytes(2);
            int unsignedShort = parsableByteArray.readUnsignedShort();
            int i9 = 5;
            parsableByteArray.skipBytes(5);
            parsableByteArray.readBytes(this.pmtScratch, 2);
            int i10 = 4;
            this.pmtScratch.skipBits(4);
            parsableByteArray.skipBytes(this.pmtScratch.readBits(12));
            if (TsExtractor.this.mode == 2 && TsExtractor.this.id3Reader == null) {
                TsPayloadReader.EsInfo esInfo = new TsPayloadReader.EsInfo(21, null, null, new byte[0]);
                TsExtractor tsExtractor = TsExtractor.this;
                tsExtractor.id3Reader = tsExtractor.payloadReaderFactory.createPayloadReader(21, esInfo);
                TsExtractor.this.id3Reader.init(timestampAdjuster, TsExtractor.this.output, new TsPayloadReader.TrackIdGenerator(unsignedShort, 21, 8192));
            }
            this.trackIdToReaderScratch.clear();
            this.trackIdToPidScratch.clear();
            int iBytesLeft = parsableByteArray.bytesLeft();
            while (iBytesLeft > 0) {
                parsableByteArray.readBytes(this.pmtScratch, i9);
                int bits = this.pmtScratch.readBits(8);
                this.pmtScratch.skipBits(3);
                int bits2 = this.pmtScratch.readBits(13);
                this.pmtScratch.skipBits(i10);
                int bits3 = this.pmtScratch.readBits(12);
                TsPayloadReader.EsInfo esInfo2 = readEsInfo(parsableByteArray, bits3);
                if (bits == 6) {
                    bits = esInfo2.streamType;
                }
                iBytesLeft -= bits3 + 5;
                int i11 = TsExtractor.this.mode == 2 ? bits : bits2;
                if (!TsExtractor.this.trackIds.get(i11)) {
                    TsPayloadReader tsPayloadReaderCreatePayloadReader = (TsExtractor.this.mode == 2 && bits == 21) ? TsExtractor.this.id3Reader : TsExtractor.this.payloadReaderFactory.createPayloadReader(bits, esInfo2);
                    if (TsExtractor.this.mode != 2 || bits2 < this.trackIdToPidScratch.get(i11, 8192)) {
                        this.trackIdToPidScratch.put(i11, bits2);
                        this.trackIdToReaderScratch.put(i11, tsPayloadReaderCreatePayloadReader);
                    }
                }
                i9 = 5;
                i10 = 4;
            }
            int size = this.trackIdToPidScratch.size();
            for (int i12 = 0; i12 < size; i12++) {
                int iKeyAt = this.trackIdToPidScratch.keyAt(i12);
                TsExtractor.this.trackIds.put(iKeyAt, true);
                TsPayloadReader tsPayloadReaderValueAt = this.trackIdToReaderScratch.valueAt(i12);
                if (tsPayloadReaderValueAt != null) {
                    if (tsPayloadReaderValueAt != TsExtractor.this.id3Reader) {
                        tsPayloadReaderValueAt.init(timestampAdjuster, TsExtractor.this.output, new TsPayloadReader.TrackIdGenerator(unsignedShort, iKeyAt, 8192));
                    }
                    TsExtractor.this.tsPayloadReaders.put(this.trackIdToPidScratch.valueAt(i12), tsPayloadReaderValueAt);
                }
            }
            if (TsExtractor.this.mode == 2) {
                if (TsExtractor.this.tracksEnded) {
                    return;
                }
                TsExtractor.this.output.endTracks();
                TsExtractor.this.remainingPmts = 0;
                TsExtractor.this.tracksEnded = true;
                return;
            }
            TsExtractor.this.tsPayloadReaders.remove(this.pid);
            TsExtractor tsExtractor2 = TsExtractor.this;
            tsExtractor2.remainingPmts = tsExtractor2.mode != 1 ? TsExtractor.this.remainingPmts - 1 : 0;
            if (TsExtractor.this.remainingPmts == 0) {
                TsExtractor.this.output.endTracks();
                TsExtractor.this.tracksEnded = true;
            }
        }

        @Override // com.google.android.exoplayer2.extractor.p037ts.SectionPayloadReader
        public void init(TimestampAdjuster timestampAdjuster, ExtractorOutput extractorOutput, TsPayloadReader.TrackIdGenerator trackIdGenerator) {
        }
    }

    public TsExtractor() {
        this(0);
    }

    public static /* synthetic */ int access$108(TsExtractor tsExtractor) {
        int i9 = tsExtractor.remainingPmts;
        tsExtractor.remainingPmts = i9 + 1;
        return i9;
    }

    private void resetPayloadReaders() {
        this.trackIds.clear();
        this.tsPayloadReaders.clear();
        SparseArray<TsPayloadReader> sparseArrayCreateInitialPayloadReaders = this.payloadReaderFactory.createInitialPayloadReaders();
        int size = sparseArrayCreateInitialPayloadReaders.size();
        for (int i9 = 0; i9 < size; i9++) {
            this.tsPayloadReaders.put(sparseArrayCreateInitialPayloadReaders.keyAt(i9), sparseArrayCreateInitialPayloadReaders.valueAt(i9));
        }
        this.tsPayloadReaders.put(0, new SectionReader(new PatReader()));
        this.id3Reader = null;
    }

    @Override // com.google.android.exoplayer2.extractor.Extractor
    public void init(ExtractorOutput extractorOutput) {
        this.output = extractorOutput;
        extractorOutput.seekMap(new SeekMap.Unseekable(C3322C.TIME_UNSET));
    }

    @Override // com.google.android.exoplayer2.extractor.Extractor
    public int read(ExtractorInput extractorInput, PositionHolder positionHolder) throws ParserException {
        ParsableByteArray parsableByteArray = this.tsPacketBuffer;
        byte[] bArr = parsableByteArray.data;
        if (9400 - parsableByteArray.getPosition() < TS_PACKET_SIZE) {
            int iBytesLeft = this.tsPacketBuffer.bytesLeft();
            if (iBytesLeft > 0) {
                System.arraycopy(bArr, this.tsPacketBuffer.getPosition(), bArr, 0, iBytesLeft);
            }
            this.tsPacketBuffer.reset(bArr, iBytesLeft);
        }
        while (this.tsPacketBuffer.bytesLeft() < TS_PACKET_SIZE) {
            int iLimit = this.tsPacketBuffer.limit();
            int i9 = extractorInput.read(bArr, iLimit, 9400 - iLimit);
            if (i9 == -1) {
                return -1;
            }
            this.tsPacketBuffer.setLimit(iLimit + i9);
        }
        int iLimit2 = this.tsPacketBuffer.limit();
        int position = this.tsPacketBuffer.getPosition();
        int i10 = position;
        while (i10 < iLimit2 && bArr[i10] != 71) {
            i10++;
        }
        this.tsPacketBuffer.setPosition(i10);
        int i11 = i10 + TS_PACKET_SIZE;
        if (i11 > iLimit2) {
            int i12 = this.bytesSinceLastSync + (i10 - position);
            this.bytesSinceLastSync = i12;
            if (this.mode != 2 || i12 <= 376) {
                return 0;
            }
            throw new ParserException("Cannot find sync byte. Most likely not a Transport Stream.");
        }
        this.bytesSinceLastSync = 0;
        int i13 = this.tsPacketBuffer.readInt();
        if ((8388608 & i13) != 0) {
            this.tsPacketBuffer.setPosition(i11);
            return 0;
        }
        boolean z8 = (4194304 & i13) != 0;
        int i14 = (2096896 & i13) >> 8;
        boolean z9 = (i13 & 32) != 0;
        TsPayloadReader tsPayloadReader = (i13 & 16) != 0 ? this.tsPayloadReaders.get(i14) : null;
        if (tsPayloadReader == null) {
            this.tsPacketBuffer.setPosition(i11);
            return 0;
        }
        if (this.mode != 2) {
            int i15 = i13 & 15;
            int i16 = this.continuityCounters.get(i14, i15 - 1);
            this.continuityCounters.put(i14, i15);
            if (i16 == i15) {
                this.tsPacketBuffer.setPosition(i11);
                return 0;
            }
            if (i15 != ((i16 + 1) & 15)) {
                tsPayloadReader.seek();
            }
        }
        if (z9) {
            this.tsPacketBuffer.skipBytes(this.tsPacketBuffer.readUnsignedByte());
        }
        this.tsPacketBuffer.setLimit(i11);
        tsPayloadReader.consume(this.tsPacketBuffer, z8);
        this.tsPacketBuffer.setLimit(iLimit2);
        this.tsPacketBuffer.setPosition(i11);
        return 0;
    }

    @Override // com.google.android.exoplayer2.extractor.Extractor
    public void release() {
    }

    @Override // com.google.android.exoplayer2.extractor.Extractor
    public void seek(long j9, long j10) {
        int size = this.timestampAdjusters.size();
        for (int i9 = 0; i9 < size; i9++) {
            this.timestampAdjusters.get(i9).reset();
        }
        this.tsPacketBuffer.reset();
        this.continuityCounters.clear();
        resetPayloadReaders();
        this.bytesSinceLastSync = 0;
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x0021, code lost:
    
        r1 = r1 + 1;
     */
    @Override // com.google.android.exoplayer2.extractor.Extractor
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public boolean sniff(ExtractorInput extractorInput) {
        byte[] bArr = this.tsPacketBuffer.data;
        extractorInput.peekFully(bArr, 0, 940);
        int i9 = 0;
        while (i9 < TS_PACKET_SIZE) {
            for (int i10 = 0; i10 != 5; i10++) {
                if (bArr[(i10 * TS_PACKET_SIZE) + i9] != 71) {
                    break;
                }
            }
            extractorInput.skipFully(i9);
            return true;
        }
        return false;
    }

    public TsExtractor(int i9) {
        this(1, i9);
    }

    public TsExtractor(int i9, int i10) {
        this(i9, new TimestampAdjuster(0L), new DefaultTsPayloadReaderFactory(i10));
    }

    public TsExtractor(int i9, TimestampAdjuster timestampAdjuster, TsPayloadReader.Factory factory) {
        this.payloadReaderFactory = (TsPayloadReader.Factory) Assertions.checkNotNull(factory);
        this.mode = i9;
        if (i9 != 1 && i9 != 2) {
            ArrayList arrayList = new ArrayList();
            this.timestampAdjusters = arrayList;
            arrayList.add(timestampAdjuster);
        } else {
            this.timestampAdjusters = Collections.singletonList(timestampAdjuster);
        }
        this.tsPacketBuffer = new ParsableByteArray(new byte[BUFFER_SIZE], 0);
        this.trackIds = new SparseBooleanArray();
        this.tsPayloadReaders = new SparseArray<>();
        this.continuityCounters = new SparseIntArray();
        resetPayloadReaders();
    }
}
