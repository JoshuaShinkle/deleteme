package com.google.android.exoplayer2.extractor.ogg;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.extractor.ExtractorInput;
import com.google.android.exoplayer2.extractor.SeekMap;
import com.google.android.exoplayer2.extractor.SeekPoint;
import com.google.android.exoplayer2.extractor.ogg.StreamReader;
import com.google.android.exoplayer2.extractor.p037ts.PsExtractor;
import com.google.android.exoplayer2.util.FlacStreamInfo;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.Util;
import com.google.common.base.Ascii;
import com.google.common.primitives.UnsignedBytes;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* loaded from: classes.dex */
final class FlacReader extends StreamReader {
    private static final byte AUDIO_PACKET_TYPE = -1;
    private static final int FRAME_HEADER_SAMPLE_NUMBER_OFFSET = 4;
    private static final byte SEEKTABLE_PACKET_TYPE = 3;
    private FlacOggSeeker flacOggSeeker;
    private FlacStreamInfo streamInfo;

    public class FlacOggSeeker implements OggSeeker, SeekMap {
        private static final int METADATA_LENGTH_OFFSET = 1;
        private static final int SEEK_POINT_SIZE = 18;
        private long firstFrameOffset = -1;
        private long pendingSeekGranule = -1;
        private long[] seekPointGranules;
        private long[] seekPointOffsets;

        public FlacOggSeeker() {
        }

        @Override // com.google.android.exoplayer2.extractor.ogg.OggSeeker
        public SeekMap createSeekMap() {
            return this;
        }

        @Override // com.google.android.exoplayer2.extractor.SeekMap
        public long getDurationUs() {
            return FlacReader.this.streamInfo.durationUs();
        }

        @Override // com.google.android.exoplayer2.extractor.SeekMap
        public SeekMap.SeekPoints getSeekPoints(long j9) {
            int iBinarySearchFloor = Util.binarySearchFloor(this.seekPointGranules, FlacReader.this.convertTimeToGranule(j9), true, true);
            long jConvertGranuleToTime = FlacReader.this.convertGranuleToTime(this.seekPointGranules[iBinarySearchFloor]);
            SeekPoint seekPoint = new SeekPoint(jConvertGranuleToTime, this.firstFrameOffset + this.seekPointOffsets[iBinarySearchFloor]);
            if (jConvertGranuleToTime < j9) {
                long[] jArr = this.seekPointGranules;
                if (iBinarySearchFloor != jArr.length - 1) {
                    int i9 = iBinarySearchFloor + 1;
                    return new SeekMap.SeekPoints(seekPoint, new SeekPoint(FlacReader.this.convertGranuleToTime(jArr[i9]), this.firstFrameOffset + this.seekPointOffsets[i9]));
                }
            }
            return new SeekMap.SeekPoints(seekPoint);
        }

        @Override // com.google.android.exoplayer2.extractor.SeekMap
        public boolean isSeekable() {
            return true;
        }

        public void parseSeekTable(ParsableByteArray parsableByteArray) {
            parsableByteArray.skipBytes(1);
            int unsignedInt24 = parsableByteArray.readUnsignedInt24() / 18;
            this.seekPointGranules = new long[unsignedInt24];
            this.seekPointOffsets = new long[unsignedInt24];
            for (int i9 = 0; i9 < unsignedInt24; i9++) {
                this.seekPointGranules[i9] = parsableByteArray.readLong();
                this.seekPointOffsets[i9] = parsableByteArray.readLong();
                parsableByteArray.skipBytes(2);
            }
        }

        @Override // com.google.android.exoplayer2.extractor.ogg.OggSeeker
        public long read(ExtractorInput extractorInput) {
            long j9 = this.pendingSeekGranule;
            if (j9 < 0) {
                return -1L;
            }
            long j10 = -(j9 + 2);
            this.pendingSeekGranule = -1L;
            return j10;
        }

        public void setFirstFrameOffset(long j9) {
            this.firstFrameOffset = j9;
        }

        @Override // com.google.android.exoplayer2.extractor.ogg.OggSeeker
        public long startSeek(long j9) {
            long jConvertTimeToGranule = FlacReader.this.convertTimeToGranule(j9);
            this.pendingSeekGranule = this.seekPointGranules[Util.binarySearchFloor(this.seekPointGranules, jConvertTimeToGranule, true, true)];
            return jConvertTimeToGranule;
        }
    }

    private int getFlacFrameBlockSize(ParsableByteArray parsableByteArray) {
        int i9;
        int i10;
        int i11 = (parsableByteArray.data[2] & 255) >> 4;
        switch (i11) {
            case 1:
                return PsExtractor.AUDIO_STREAM;
            case 2:
            case 3:
            case 4:
            case 5:
                i9 = 576;
                i10 = i11 - 2;
                break;
            case 6:
            case 7:
                parsableByteArray.skipBytes(4);
                parsableByteArray.readUtf8EncodedLong();
                int unsignedByte = i11 == 6 ? parsableByteArray.readUnsignedByte() : parsableByteArray.readUnsignedShort();
                parsableByteArray.setPosition(0);
                return unsignedByte + 1;
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
                i10 = i11 - 8;
                i9 = 256;
                break;
            default:
                return -1;
        }
        return i9 << i10;
    }

    private static boolean isAudioPacket(byte[] bArr) {
        return bArr[0] == -1;
    }

    public static boolean verifyBitstreamType(ParsableByteArray parsableByteArray) {
        return parsableByteArray.bytesLeft() >= 5 && parsableByteArray.readUnsignedByte() == 127 && parsableByteArray.readUnsignedInt() == 1179402563;
    }

    @Override // com.google.android.exoplayer2.extractor.ogg.StreamReader
    public long preparePayload(ParsableByteArray parsableByteArray) {
        if (isAudioPacket(parsableByteArray.data)) {
            return getFlacFrameBlockSize(parsableByteArray);
        }
        return -1L;
    }

    @Override // com.google.android.exoplayer2.extractor.ogg.StreamReader
    public boolean readHeaders(ParsableByteArray parsableByteArray, long j9, StreamReader.SetupData setupData) {
        byte[] bArr = parsableByteArray.data;
        if (this.streamInfo == null) {
            this.streamInfo = new FlacStreamInfo(bArr, 17);
            byte[] bArrCopyOfRange = Arrays.copyOfRange(bArr, 9, parsableByteArray.limit());
            bArrCopyOfRange[4] = UnsignedBytes.MAX_POWER_OF_TWO;
            List listSingletonList = Collections.singletonList(bArrCopyOfRange);
            int iBitRate = this.streamInfo.bitRate();
            FlacStreamInfo flacStreamInfo = this.streamInfo;
            setupData.format = Format.createAudioSampleFormat(null, MimeTypes.AUDIO_FLAC, null, -1, iBitRate, flacStreamInfo.channels, flacStreamInfo.sampleRate, listSingletonList, null, 0, null);
            return true;
        }
        if ((bArr[0] & Ascii.DEL) == 3) {
            FlacOggSeeker flacOggSeeker = new FlacOggSeeker();
            this.flacOggSeeker = flacOggSeeker;
            flacOggSeeker.parseSeekTable(parsableByteArray);
            return true;
        }
        if (!isAudioPacket(bArr)) {
            return true;
        }
        FlacOggSeeker flacOggSeeker2 = this.flacOggSeeker;
        if (flacOggSeeker2 != null) {
            flacOggSeeker2.setFirstFrameOffset(j9);
            setupData.oggSeeker = this.flacOggSeeker;
        }
        return false;
    }

    @Override // com.google.android.exoplayer2.extractor.ogg.StreamReader
    public void reset(boolean z8) {
        super.reset(z8);
        if (z8) {
            this.streamInfo = null;
            this.flacOggSeeker = null;
        }
    }
}
