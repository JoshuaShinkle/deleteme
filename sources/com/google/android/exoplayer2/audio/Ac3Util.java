package com.google.android.exoplayer2.audio;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.drm.DrmInitData;
import com.google.android.exoplayer2.extractor.p037ts.PsExtractor;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.ParsableBitArray;
import com.google.android.exoplayer2.util.ParsableByteArray;
import java.nio.ByteBuffer;

/* loaded from: classes.dex */
public final class Ac3Util {
    private static final int AC3_SYNCFRAME_AUDIO_SAMPLE_COUNT = 1536;
    public static final int TRUEHD_RECHUNK_SAMPLE_COUNT = 8;
    public static final int TRUEHD_SYNCFRAME_PREFIX_LENGTH = 12;
    private static final int[] BLOCKS_PER_SYNCFRAME_BY_NUMBLKSCOD = {1, 2, 3, 6};
    private static final int[] SAMPLE_RATE_BY_FSCOD = {48000, 44100, 32000};
    private static final int[] SAMPLE_RATE_BY_FSCOD2 = {24000, 22050, 16000};
    private static final int[] CHANNEL_COUNT_BY_ACMOD = {2, 1, 2, 3, 3, 4, 4, 5};
    private static final int AUDIO_SAMPLES_PER_AUDIO_BLOCK = 256;
    private static final int[] BITRATE_BY_HALF_FRMSIZECOD = {32, 40, 48, 56, 64, 80, 96, 112, 128, 160, PsExtractor.AUDIO_STREAM, 224, AUDIO_SAMPLES_PER_AUDIO_BLOCK, 320, 384, 448, 512, 576, 640};
    private static final int[] SYNCFRAME_SIZE_WORDS_BY_HALF_FRMSIZECOD_44_1 = {69, 87, 104, 121, 139, 174, 208, 243, 278, 348, 417, 487, 557, 696, 835, 975, 1114, 1253, 1393};

    public static final class Ac3SyncFrameInfo {
        public static final int STREAM_TYPE_TYPE0 = 0;
        public static final int STREAM_TYPE_TYPE1 = 1;
        public static final int STREAM_TYPE_TYPE2 = 2;
        public static final int STREAM_TYPE_UNDEFINED = -1;
        public final int channelCount;
        public final int frameSize;
        public final String mimeType;
        public final int sampleCount;
        public final int sampleRate;
        public final int streamType;

        private Ac3SyncFrameInfo(String str, int i9, int i10, int i11, int i12, int i13) {
            this.mimeType = str;
            this.streamType = i9;
            this.channelCount = i10;
            this.sampleRate = i11;
            this.frameSize = i12;
            this.sampleCount = i13;
        }
    }

    private Ac3Util() {
    }

    public static int getAc3SyncframeAudioSampleCount() {
        return AC3_SYNCFRAME_AUDIO_SAMPLE_COUNT;
    }

    private static int getAc3SyncframeSize(int i9, int i10) {
        int i11 = i10 / 2;
        if (i9 < 0) {
            return -1;
        }
        int[] iArr = SAMPLE_RATE_BY_FSCOD;
        if (i9 >= iArr.length || i10 < 0) {
            return -1;
        }
        int[] iArr2 = SYNCFRAME_SIZE_WORDS_BY_HALF_FRMSIZECOD_44_1;
        if (i11 >= iArr2.length) {
            return -1;
        }
        int i12 = iArr[i9];
        if (i12 == 44100) {
            return (iArr2[i11] + (i10 % 2)) * 2;
        }
        int i13 = BITRATE_BY_HALF_FRMSIZECOD[i11];
        return i12 == 32000 ? i13 * 6 : i13 * 4;
    }

    public static Format parseAc3AnnexFFormat(ParsableByteArray parsableByteArray, String str, String str2, DrmInitData drmInitData) {
        int i9 = SAMPLE_RATE_BY_FSCOD[(parsableByteArray.readUnsignedByte() & PsExtractor.AUDIO_STREAM) >> 6];
        int unsignedByte = parsableByteArray.readUnsignedByte();
        int i10 = CHANNEL_COUNT_BY_ACMOD[(unsignedByte & 56) >> 3];
        if ((unsignedByte & 4) != 0) {
            i10++;
        }
        return Format.createAudioSampleFormat(str, MimeTypes.AUDIO_AC3, null, -1, -1, i10, i9, null, drmInitData, 0, str2);
    }

    public static Ac3SyncFrameInfo parseAc3SyncframeInfo(ParsableBitArray parsableBitArray) {
        int ac3SyncframeSize;
        int i9;
        int i10;
        int bits;
        String str;
        int i11;
        int bits2;
        int i12;
        int i13;
        int position = parsableBitArray.getPosition();
        parsableBitArray.skipBits(40);
        boolean z8 = parsableBitArray.readBits(5) == 16;
        parsableBitArray.setPosition(position);
        if (z8) {
            parsableBitArray.skipBits(16);
            bits = parsableBitArray.readBits(2);
            parsableBitArray.skipBits(3);
            ac3SyncframeSize = (parsableBitArray.readBits(11) + 1) * 2;
            int bits3 = parsableBitArray.readBits(2);
            if (bits3 == 3) {
                i12 = 6;
                i9 = SAMPLE_RATE_BY_FSCOD2[parsableBitArray.readBits(2)];
                bits2 = 3;
            } else {
                bits2 = parsableBitArray.readBits(2);
                i12 = BLOCKS_PER_SYNCFRAME_BY_NUMBLKSCOD[bits2];
                i9 = SAMPLE_RATE_BY_FSCOD[bits3];
            }
            i11 = i12 * AUDIO_SAMPLES_PER_AUDIO_BLOCK;
            int bits4 = parsableBitArray.readBits(3);
            boolean bit = parsableBitArray.readBit();
            i10 = CHANNEL_COUNT_BY_ACMOD[bits4] + (bit ? 1 : 0);
            parsableBitArray.skipBits(10);
            if (parsableBitArray.readBit()) {
                parsableBitArray.skipBits(8);
            }
            if (bits4 == 0) {
                parsableBitArray.skipBits(5);
                if (parsableBitArray.readBit()) {
                    parsableBitArray.skipBits(8);
                }
            }
            if (bits == 1 && parsableBitArray.readBit()) {
                parsableBitArray.skipBits(16);
            }
            if (parsableBitArray.readBit()) {
                if (bits4 > 2) {
                    parsableBitArray.skipBits(2);
                }
                if ((bits4 & 1) != 0 && bits4 > 2) {
                    parsableBitArray.skipBits(6);
                }
                if ((bits4 & 4) != 0) {
                    parsableBitArray.skipBits(6);
                }
                if (bit && parsableBitArray.readBit()) {
                    parsableBitArray.skipBits(5);
                }
                if (bits == 0) {
                    if (parsableBitArray.readBit()) {
                        parsableBitArray.skipBits(6);
                    }
                    if (bits4 == 0 && parsableBitArray.readBit()) {
                        parsableBitArray.skipBits(6);
                    }
                    if (parsableBitArray.readBit()) {
                        parsableBitArray.skipBits(6);
                    }
                    int bits5 = parsableBitArray.readBits(2);
                    if (bits5 == 1) {
                        parsableBitArray.skipBits(5);
                    } else if (bits5 == 2) {
                        parsableBitArray.skipBits(12);
                    } else if (bits5 == 3) {
                        int bits6 = parsableBitArray.readBits(5);
                        if (parsableBitArray.readBit()) {
                            parsableBitArray.skipBits(5);
                            if (parsableBitArray.readBit()) {
                                parsableBitArray.skipBits(4);
                            }
                            if (parsableBitArray.readBit()) {
                                parsableBitArray.skipBits(4);
                            }
                            if (parsableBitArray.readBit()) {
                                parsableBitArray.skipBits(4);
                            }
                            if (parsableBitArray.readBit()) {
                                parsableBitArray.skipBits(4);
                            }
                            if (parsableBitArray.readBit()) {
                                parsableBitArray.skipBits(4);
                            }
                            if (parsableBitArray.readBit()) {
                                parsableBitArray.skipBits(4);
                            }
                            if (parsableBitArray.readBit()) {
                                parsableBitArray.skipBits(4);
                            }
                            if (parsableBitArray.readBit()) {
                                if (parsableBitArray.readBit()) {
                                    parsableBitArray.skipBits(4);
                                }
                                if (parsableBitArray.readBit()) {
                                    parsableBitArray.skipBits(4);
                                }
                            }
                        }
                        if (parsableBitArray.readBit()) {
                            parsableBitArray.skipBits(5);
                            if (parsableBitArray.readBit()) {
                                parsableBitArray.skipBits(7);
                                if (parsableBitArray.readBit()) {
                                    parsableBitArray.skipBits(8);
                                }
                            }
                        }
                        parsableBitArray.skipBits((bits6 + 2) * 8);
                        parsableBitArray.byteAlign();
                    }
                    if (bits4 < 2) {
                        if (parsableBitArray.readBit()) {
                            parsableBitArray.skipBits(14);
                        }
                        if (bits4 == 0 && parsableBitArray.readBit()) {
                            parsableBitArray.skipBits(14);
                        }
                    }
                    if (parsableBitArray.readBit()) {
                        if (bits2 == 0) {
                            parsableBitArray.skipBits(5);
                        } else {
                            for (int i14 = 0; i14 < i12; i14++) {
                                if (parsableBitArray.readBit()) {
                                    parsableBitArray.skipBits(5);
                                }
                            }
                        }
                    }
                }
            }
            if (parsableBitArray.readBit()) {
                parsableBitArray.skipBits(5);
                if (bits4 == 2) {
                    parsableBitArray.skipBits(4);
                }
                if (bits4 >= 6) {
                    parsableBitArray.skipBits(2);
                }
                if (parsableBitArray.readBit()) {
                    parsableBitArray.skipBits(8);
                }
                if (bits4 == 0 && parsableBitArray.readBit()) {
                    parsableBitArray.skipBits(8);
                }
                i13 = 3;
                if (bits3 < 3) {
                    parsableBitArray.skipBit();
                }
            } else {
                i13 = 3;
            }
            if (bits == 0 && bits2 != i13) {
                parsableBitArray.skipBit();
            }
            if (bits == 2 && (bits2 == i13 || parsableBitArray.readBit())) {
                parsableBitArray.skipBits(6);
            }
            str = (parsableBitArray.readBit() && parsableBitArray.readBits(6) == 1 && parsableBitArray.readBits(8) == 1) ? MimeTypes.AUDIO_E_AC3_JOC : MimeTypes.AUDIO_E_AC3;
        } else {
            parsableBitArray.skipBits(32);
            int bits7 = parsableBitArray.readBits(2);
            ac3SyncframeSize = getAc3SyncframeSize(bits7, parsableBitArray.readBits(6));
            parsableBitArray.skipBits(8);
            int bits8 = parsableBitArray.readBits(3);
            if ((bits8 & 1) != 0 && bits8 != 1) {
                parsableBitArray.skipBits(2);
            }
            if ((bits8 & 4) != 0) {
                parsableBitArray.skipBits(2);
            }
            if (bits8 == 2) {
                parsableBitArray.skipBits(2);
            }
            i9 = SAMPLE_RATE_BY_FSCOD[bits7];
            i10 = CHANNEL_COUNT_BY_ACMOD[bits8] + (parsableBitArray.readBit() ? 1 : 0);
            bits = -1;
            str = MimeTypes.AUDIO_AC3;
            i11 = AC3_SYNCFRAME_AUDIO_SAMPLE_COUNT;
        }
        return new Ac3SyncFrameInfo(str, bits, i10, i9, ac3SyncframeSize, i11);
    }

    public static int parseAc3SyncframeSize(byte[] bArr) {
        if (bArr.length < 5) {
            return -1;
        }
        byte b9 = bArr[4];
        return getAc3SyncframeSize((b9 & 192) >> 6, b9 & 63);
    }

    public static Format parseEAc3AnnexFFormat(ParsableByteArray parsableByteArray, String str, String str2, DrmInitData drmInitData) {
        parsableByteArray.skipBytes(2);
        int i9 = SAMPLE_RATE_BY_FSCOD[(parsableByteArray.readUnsignedByte() & PsExtractor.AUDIO_STREAM) >> 6];
        int unsignedByte = parsableByteArray.readUnsignedByte();
        int i10 = CHANNEL_COUNT_BY_ACMOD[(unsignedByte & 14) >> 1];
        if ((unsignedByte & 1) != 0) {
            i10++;
        }
        if (((parsableByteArray.readUnsignedByte() & 30) >> 1) > 0 && (2 & parsableByteArray.readUnsignedByte()) != 0) {
            i10 += 2;
        }
        return Format.createAudioSampleFormat(str, (parsableByteArray.bytesLeft() <= 0 || (parsableByteArray.readUnsignedByte() & 1) == 0) ? MimeTypes.AUDIO_E_AC3 : MimeTypes.AUDIO_E_AC3_JOC, null, -1, -1, i10, i9, null, drmInitData, 0, str2);
    }

    public static int parseEAc3SyncframeAudioSampleCount(ByteBuffer byteBuffer) {
        return (((byteBuffer.get(byteBuffer.position() + 4) & 192) >> 6) != 3 ? BLOCKS_PER_SYNCFRAME_BY_NUMBLKSCOD[(byteBuffer.get(byteBuffer.position() + 4) & 48) >> 4] : 6) * AUDIO_SAMPLES_PER_AUDIO_BLOCK;
    }

    public static int parseTrueHdSyncframeAudioSampleCount(byte[] bArr) {
        if (bArr[4] == -8 && bArr[5] == 114 && bArr[6] == 111 && bArr[7] == -70) {
            return 40 << (bArr[8] & 7);
        }
        return 0;
    }

    public static int parseTrueHdSyncframeAudioSampleCount(ByteBuffer byteBuffer) {
        if (byteBuffer.getInt(byteBuffer.position() + 4) != -1167101192) {
            return 0;
        }
        return 40 << (byteBuffer.get(byteBuffer.position() + 8) & 7);
    }
}
