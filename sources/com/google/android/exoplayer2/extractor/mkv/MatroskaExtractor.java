package com.google.android.exoplayer2.extractor.mkv;

import android.util.Log;
import android.util.SparseArray;
import com.google.android.exoplayer2.C3322C;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.audio.Ac3Util;
import com.google.android.exoplayer2.drm.DrmInitData;
import com.google.android.exoplayer2.extractor.ChunkIndex;
import com.google.android.exoplayer2.extractor.Extractor;
import com.google.android.exoplayer2.extractor.ExtractorInput;
import com.google.android.exoplayer2.extractor.ExtractorOutput;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.extractor.PositionHolder;
import com.google.android.exoplayer2.extractor.SeekMap;
import com.google.android.exoplayer2.extractor.TrackOutput;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.LongArray;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.NalUnitUtil;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.Util;
import com.google.android.exoplayer2.video.AvcConfig;
import com.google.android.exoplayer2.video.ColorInfo;
import com.google.android.exoplayer2.video.HevcConfig;
import com.google.android.gms.auth.api.credentials.CredentialsApi;
import com.google.common.primitives.UnsignedBytes;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import org.apache.commons.lang3.CharUtils;

/* loaded from: classes.dex */
public final class MatroskaExtractor implements Extractor {
    private static final int BLOCK_STATE_DATA = 2;
    private static final int BLOCK_STATE_HEADER = 1;
    private static final int BLOCK_STATE_START = 0;
    private static final String CODEC_ID_AAC = "A_AAC";
    private static final String CODEC_ID_AC3 = "A_AC3";
    private static final String CODEC_ID_ACM = "A_MS/ACM";
    private static final String CODEC_ID_ASS = "S_TEXT/ASS";
    private static final String CODEC_ID_DTS = "A_DTS";
    private static final String CODEC_ID_DTS_EXPRESS = "A_DTS/EXPRESS";
    private static final String CODEC_ID_DTS_LOSSLESS = "A_DTS/LOSSLESS";
    private static final String CODEC_ID_DVBSUB = "S_DVBSUB";
    private static final String CODEC_ID_E_AC3 = "A_EAC3";
    private static final String CODEC_ID_FLAC = "A_FLAC";
    private static final String CODEC_ID_FOURCC = "V_MS/VFW/FOURCC";
    private static final String CODEC_ID_H264 = "V_MPEG4/ISO/AVC";
    private static final String CODEC_ID_H265 = "V_MPEGH/ISO/HEVC";
    private static final String CODEC_ID_MP2 = "A_MPEG/L2";
    private static final String CODEC_ID_MP3 = "A_MPEG/L3";
    private static final String CODEC_ID_MPEG2 = "V_MPEG2";
    private static final String CODEC_ID_MPEG4_AP = "V_MPEG4/ISO/AP";
    private static final String CODEC_ID_MPEG4_ASP = "V_MPEG4/ISO/ASP";
    private static final String CODEC_ID_MPEG4_SP = "V_MPEG4/ISO/SP";
    private static final String CODEC_ID_OPUS = "A_OPUS";
    private static final String CODEC_ID_PCM_INT_LIT = "A_PCM/INT/LIT";
    private static final String CODEC_ID_PGS = "S_HDMV/PGS";
    private static final String CODEC_ID_SUBRIP = "S_TEXT/UTF8";
    private static final String CODEC_ID_THEORA = "V_THEORA";
    private static final String CODEC_ID_TRUEHD = "A_TRUEHD";
    private static final String CODEC_ID_VOBSUB = "S_VOBSUB";
    private static final String CODEC_ID_VORBIS = "A_VORBIS";
    private static final String CODEC_ID_VP8 = "V_VP8";
    private static final String CODEC_ID_VP9 = "V_VP9";
    private static final String DOC_TYPE_MATROSKA = "matroska";
    private static final String DOC_TYPE_WEBM = "webm";
    private static final int ENCRYPTION_IV_SIZE = 8;
    public static final int FLAG_DISABLE_SEEK_FOR_CUES = 1;
    private static final int FOURCC_COMPRESSION_VC1 = 826496599;
    private static final int ID_AUDIO = 225;
    private static final int ID_AUDIO_BIT_DEPTH = 25188;
    private static final int ID_BLOCK = 161;
    private static final int ID_BLOCK_DURATION = 155;
    private static final int ID_BLOCK_GROUP = 160;
    private static final int ID_CHANNELS = 159;
    private static final int ID_CLUSTER = 524531317;
    private static final int ID_CODEC_DELAY = 22186;
    private static final int ID_CODEC_ID = 134;
    private static final int ID_CODEC_PRIVATE = 25506;
    private static final int ID_COLOUR = 21936;
    private static final int ID_COLOUR_PRIMARIES = 21947;
    private static final int ID_COLOUR_RANGE = 21945;
    private static final int ID_COLOUR_TRANSFER = 21946;
    private static final int ID_CONTENT_COMPRESSION = 20532;
    private static final int ID_CONTENT_COMPRESSION_ALGORITHM = 16980;
    private static final int ID_CONTENT_COMPRESSION_SETTINGS = 16981;
    private static final int ID_CONTENT_ENCODING = 25152;
    private static final int ID_CONTENT_ENCODINGS = 28032;
    private static final int ID_CONTENT_ENCODING_ORDER = 20529;
    private static final int ID_CONTENT_ENCODING_SCOPE = 20530;
    private static final int ID_CONTENT_ENCRYPTION = 20533;
    private static final int ID_CONTENT_ENCRYPTION_AES_SETTINGS = 18407;
    private static final int ID_CONTENT_ENCRYPTION_AES_SETTINGS_CIPHER_MODE = 18408;
    private static final int ID_CONTENT_ENCRYPTION_ALGORITHM = 18401;
    private static final int ID_CONTENT_ENCRYPTION_KEY_ID = 18402;
    private static final int ID_CUES = 475249515;
    private static final int ID_CUE_CLUSTER_POSITION = 241;
    private static final int ID_CUE_POINT = 187;
    private static final int ID_CUE_TIME = 179;
    private static final int ID_CUE_TRACK_POSITIONS = 183;
    private static final int ID_DEFAULT_DURATION = 2352003;
    private static final int ID_DISPLAY_HEIGHT = 21690;
    private static final int ID_DISPLAY_UNIT = 21682;
    private static final int ID_DISPLAY_WIDTH = 21680;
    private static final int ID_DOC_TYPE = 17026;
    private static final int ID_DOC_TYPE_READ_VERSION = 17029;
    private static final int ID_DURATION = 17545;
    private static final int ID_EBML = 440786851;
    private static final int ID_EBML_READ_VERSION = 17143;
    private static final int ID_FLAG_DEFAULT = 136;
    private static final int ID_FLAG_FORCED = 21930;
    private static final int ID_INFO = 357149030;
    private static final int ID_LANGUAGE = 2274716;
    private static final int ID_LUMNINANCE_MAX = 21977;
    private static final int ID_LUMNINANCE_MIN = 21978;
    private static final int ID_MASTERING_METADATA = 21968;
    private static final int ID_MAX_CLL = 21948;
    private static final int ID_MAX_FALL = 21949;
    private static final int ID_PIXEL_HEIGHT = 186;
    private static final int ID_PIXEL_WIDTH = 176;
    private static final int ID_PRIMARY_B_CHROMATICITY_X = 21973;
    private static final int ID_PRIMARY_B_CHROMATICITY_Y = 21974;
    private static final int ID_PRIMARY_G_CHROMATICITY_X = 21971;
    private static final int ID_PRIMARY_G_CHROMATICITY_Y = 21972;
    private static final int ID_PRIMARY_R_CHROMATICITY_X = 21969;
    private static final int ID_PRIMARY_R_CHROMATICITY_Y = 21970;
    private static final int ID_PROJECTION = 30320;
    private static final int ID_PROJECTION_PRIVATE = 30322;
    private static final int ID_REFERENCE_BLOCK = 251;
    private static final int ID_SAMPLING_FREQUENCY = 181;
    private static final int ID_SEEK = 19899;
    private static final int ID_SEEK_HEAD = 290298740;
    private static final int ID_SEEK_ID = 21419;
    private static final int ID_SEEK_POSITION = 21420;
    private static final int ID_SEEK_PRE_ROLL = 22203;
    private static final int ID_SEGMENT = 408125543;
    private static final int ID_SEGMENT_INFO = 357149030;
    private static final int ID_SIMPLE_BLOCK = 163;
    private static final int ID_STEREO_MODE = 21432;
    private static final int ID_TIMECODE_SCALE = 2807729;
    private static final int ID_TIME_CODE = 231;
    private static final int ID_TRACKS = 374648427;
    private static final int ID_TRACK_ENTRY = 174;
    private static final int ID_TRACK_NUMBER = 215;
    private static final int ID_TRACK_TYPE = 131;
    private static final int ID_VIDEO = 224;
    private static final int ID_WHITE_POINT_CHROMATICITY_X = 21975;
    private static final int ID_WHITE_POINT_CHROMATICITY_Y = 21976;
    private static final int LACING_EBML = 3;
    private static final int LACING_FIXED_SIZE = 2;
    private static final int LACING_NONE = 0;
    private static final int LACING_XIPH = 1;
    private static final int OPUS_MAX_INPUT_SIZE = 5760;
    private static final int SSA_PREFIX_END_TIMECODE_OFFSET = 21;
    private static final String SSA_TIMECODE_FORMAT = "%01d:%02d:%02d:%02d";
    private static final long SSA_TIMECODE_LAST_VALUE_SCALING_FACTOR = 10000;
    private static final int SUBRIP_PREFIX_END_TIMECODE_OFFSET = 19;
    private static final String SUBRIP_TIMECODE_FORMAT = "%02d:%02d:%02d,%03d";
    private static final long SUBRIP_TIMECODE_LAST_VALUE_SCALING_FACTOR = 1000;
    private static final String TAG = "MatroskaExtractor";
    private static final int TRACK_TYPE_AUDIO = 2;
    private static final int UNSET_ENTRY_ID = -1;
    private static final int VORBIS_MAX_INPUT_SIZE = 8192;
    private static final int WAVE_FORMAT_EXTENSIBLE = 65534;
    private static final int WAVE_FORMAT_PCM = 1;
    private static final int WAVE_FORMAT_SIZE = 18;
    private long blockDurationUs;
    private int blockFlags;
    private int blockLacingSampleCount;
    private int blockLacingSampleIndex;
    private int[] blockLacingSampleSizes;
    private int blockState;
    private long blockTimeUs;
    private int blockTrackNumber;
    private int blockTrackNumberLength;
    private long clusterTimecodeUs;
    private LongArray cueClusterPositions;
    private LongArray cueTimesUs;
    private long cuesContentPosition;
    private Track currentTrack;
    private long durationTimecode;
    private long durationUs;
    private final ParsableByteArray encryptionInitializationVector;
    private final ParsableByteArray encryptionSubsampleData;
    private ByteBuffer encryptionSubsampleDataBuffer;
    private ExtractorOutput extractorOutput;
    private final ParsableByteArray nalLength;
    private final ParsableByteArray nalStartCode;
    private final EbmlReader reader;
    private int sampleBytesRead;
    private int sampleBytesWritten;
    private int sampleCurrentNalBytesRemaining;
    private boolean sampleEncodingHandled;
    private boolean sampleInitializationVectorRead;
    private int samplePartitionCount;
    private boolean samplePartitionCountRead;
    private boolean sampleRead;
    private boolean sampleSeenReferenceBlock;
    private byte sampleSignalByte;
    private boolean sampleSignalByteRead;
    private final ParsableByteArray sampleStrippedBytes;
    private final ParsableByteArray scratch;
    private int seekEntryId;
    private final ParsableByteArray seekEntryIdBytes;
    private long seekEntryPosition;
    private boolean seekForCues;
    private final boolean seekForCuesEnabled;
    private long seekPositionAfterBuildingCues;
    private boolean seenClusterPositionForCurrentCuePoint;
    private long segmentContentPosition;
    private long segmentContentSize;
    private boolean sentSeekMap;
    private final ParsableByteArray subtitleSample;
    private long timecodeScale;
    private final SparseArray<Track> tracks;
    private final VarintReader varintReader;
    private final ParsableByteArray vorbisNumPageSamples;
    public static final ExtractorsFactory FACTORY = new ExtractorsFactory() { // from class: com.google.android.exoplayer2.extractor.mkv.MatroskaExtractor.1
        @Override // com.google.android.exoplayer2.extractor.ExtractorsFactory
        public Extractor[] createExtractors() {
            return new Extractor[]{new MatroskaExtractor()};
        }
    };
    private static final byte[] SUBRIP_PREFIX = {49, 10, 48, 48, 58, 48, 48, 58, 48, 48, 44, 48, 48, 48, 32, 45, 45, 62, 32, 48, 48, 58, 48, 48, 58, 48, 48, 44, 48, 48, 48, 10};
    private static final byte[] SUBRIP_TIMECODE_EMPTY = {32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32};
    private static final byte[] SSA_DIALOGUE_FORMAT = Util.getUtf8Bytes("Format: Start, End, ReadOrder, Layer, Style, Name, MarginL, MarginR, MarginV, Effect, Text");
    private static final byte[] SSA_PREFIX = {68, 105, 97, 108, 111, 103, 117, 101, 58, 32, 48, 58, 48, 48, 58, 48, 48, 58, 48, 48, 44, 48, 58, 48, 48, 58, 48, 48, 58, 48, 48, 44};
    private static final byte[] SSA_TIMECODE_EMPTY = {32, 32, 32, 32, 32, 32, 32, 32, 32, 32};
    private static final UUID WAVE_SUBFORMAT_PCM = new UUID(72057594037932032L, -9223371306706625679L);

    @Retention(RetentionPolicy.SOURCE)
    public @interface Flags {
    }

    public final class InnerEbmlReaderOutput implements EbmlReaderOutput {
        private InnerEbmlReaderOutput() {
        }

        @Override // com.google.android.exoplayer2.extractor.mkv.EbmlReaderOutput
        public void binaryElement(int i9, int i10, ExtractorInput extractorInput) throws ParserException {
            MatroskaExtractor.this.binaryElement(i9, i10, extractorInput);
        }

        @Override // com.google.android.exoplayer2.extractor.mkv.EbmlReaderOutput
        public void endMasterElement(int i9) throws ParserException {
            MatroskaExtractor.this.endMasterElement(i9);
        }

        @Override // com.google.android.exoplayer2.extractor.mkv.EbmlReaderOutput
        public void floatElement(int i9, double d9) {
            MatroskaExtractor.this.floatElement(i9, d9);
        }

        @Override // com.google.android.exoplayer2.extractor.mkv.EbmlReaderOutput
        public int getElementType(int i9) {
            return MatroskaExtractor.this.getElementType(i9);
        }

        @Override // com.google.android.exoplayer2.extractor.mkv.EbmlReaderOutput
        public void integerElement(int i9, long j9) throws ParserException {
            MatroskaExtractor.this.integerElement(i9, j9);
        }

        @Override // com.google.android.exoplayer2.extractor.mkv.EbmlReaderOutput
        public boolean isLevel1Element(int i9) {
            return MatroskaExtractor.this.isLevel1Element(i9);
        }

        @Override // com.google.android.exoplayer2.extractor.mkv.EbmlReaderOutput
        public void startMasterElement(int i9, long j9, long j10) throws ParserException {
            MatroskaExtractor.this.startMasterElement(i9, j9, j10);
        }

        @Override // com.google.android.exoplayer2.extractor.mkv.EbmlReaderOutput
        public void stringElement(int i9, String str) throws ParserException {
            MatroskaExtractor.this.stringElement(i9, str);
        }
    }

    public static final class Track {
        private static final int DEFAULT_MAX_CLL = 1000;
        private static final int DEFAULT_MAX_FALL = 200;
        private static final int DISPLAY_UNIT_PIXELS = 0;
        private static final int MAX_CHROMATICITY = 50000;
        public int audioBitDepth;
        public int channelCount;
        public long codecDelayNs;
        public String codecId;
        public byte[] codecPrivate;
        public int colorRange;
        public int colorSpace;
        public int colorTransfer;
        public TrackOutput.CryptoData cryptoData;
        public int defaultSampleDurationNs;
        public int displayHeight;
        public int displayUnit;
        public int displayWidth;
        public DrmInitData drmInitData;
        public boolean flagDefault;
        public boolean flagForced;
        public boolean hasColorInfo;
        public boolean hasContentEncryption;
        public int height;
        private String language;
        public int maxContentLuminance;
        public int maxFrameAverageLuminance;
        public float maxMasteringLuminance;
        public float minMasteringLuminance;
        public int nalUnitLengthFieldLength;
        public int number;
        public TrackOutput output;
        public float primaryBChromaticityX;
        public float primaryBChromaticityY;
        public float primaryGChromaticityX;
        public float primaryGChromaticityY;
        public float primaryRChromaticityX;
        public float primaryRChromaticityY;
        public byte[] projectionData;
        public int sampleRate;
        public byte[] sampleStrippedBytes;
        public long seekPreRollNs;
        public int stereoMode;
        public TrueHdSampleRechunker trueHdSampleRechunker;
        public int type;
        public float whitePointChromaticityX;
        public float whitePointChromaticityY;
        public int width;

        private Track() {
            this.width = -1;
            this.height = -1;
            this.displayWidth = -1;
            this.displayHeight = -1;
            this.displayUnit = 0;
            this.projectionData = null;
            this.stereoMode = -1;
            this.hasColorInfo = false;
            this.colorSpace = -1;
            this.colorTransfer = -1;
            this.colorRange = -1;
            this.maxContentLuminance = 1000;
            this.maxFrameAverageLuminance = DEFAULT_MAX_FALL;
            this.primaryRChromaticityX = -1.0f;
            this.primaryRChromaticityY = -1.0f;
            this.primaryGChromaticityX = -1.0f;
            this.primaryGChromaticityY = -1.0f;
            this.primaryBChromaticityX = -1.0f;
            this.primaryBChromaticityY = -1.0f;
            this.whitePointChromaticityX = -1.0f;
            this.whitePointChromaticityY = -1.0f;
            this.maxMasteringLuminance = -1.0f;
            this.minMasteringLuminance = -1.0f;
            this.channelCount = 1;
            this.audioBitDepth = -1;
            this.sampleRate = 8000;
            this.codecDelayNs = 0L;
            this.seekPreRollNs = 0L;
            this.flagDefault = true;
            this.language = "eng";
        }

        private byte[] getHdrStaticInfo() {
            if (this.primaryRChromaticityX == -1.0f || this.primaryRChromaticityY == -1.0f || this.primaryGChromaticityX == -1.0f || this.primaryGChromaticityY == -1.0f || this.primaryBChromaticityX == -1.0f || this.primaryBChromaticityY == -1.0f || this.whitePointChromaticityX == -1.0f || this.whitePointChromaticityY == -1.0f || this.maxMasteringLuminance == -1.0f || this.minMasteringLuminance == -1.0f) {
                return null;
            }
            byte[] bArr = new byte[25];
            ByteBuffer byteBufferWrap = ByteBuffer.wrap(bArr);
            byteBufferWrap.put((byte) 0);
            byteBufferWrap.putShort((short) ((this.primaryRChromaticityX * 50000.0f) + 0.5f));
            byteBufferWrap.putShort((short) ((this.primaryRChromaticityY * 50000.0f) + 0.5f));
            byteBufferWrap.putShort((short) ((this.primaryGChromaticityX * 50000.0f) + 0.5f));
            byteBufferWrap.putShort((short) ((this.primaryGChromaticityY * 50000.0f) + 0.5f));
            byteBufferWrap.putShort((short) ((this.primaryBChromaticityX * 50000.0f) + 0.5f));
            byteBufferWrap.putShort((short) ((this.primaryBChromaticityY * 50000.0f) + 0.5f));
            byteBufferWrap.putShort((short) ((this.whitePointChromaticityX * 50000.0f) + 0.5f));
            byteBufferWrap.putShort((short) ((this.whitePointChromaticityY * 50000.0f) + 0.5f));
            byteBufferWrap.putShort((short) (this.maxMasteringLuminance + 0.5f));
            byteBufferWrap.putShort((short) (this.minMasteringLuminance + 0.5f));
            byteBufferWrap.putShort((short) this.maxContentLuminance);
            byteBufferWrap.putShort((short) this.maxFrameAverageLuminance);
            return bArr;
        }

        private static List<byte[]> parseFourCcVc1Private(ParsableByteArray parsableByteArray) throws ParserException {
            try {
                parsableByteArray.skipBytes(16);
                if (parsableByteArray.readLittleEndianUnsignedInt() != 826496599) {
                    return null;
                }
                byte[] bArr = parsableByteArray.data;
                for (int position = parsableByteArray.getPosition() + 20; position < bArr.length - 4; position++) {
                    if (bArr[position] == 0 && bArr[position + 1] == 0 && bArr[position + 2] == 1 && bArr[position + 3] == 15) {
                        return Collections.singletonList(Arrays.copyOfRange(bArr, position, bArr.length));
                    }
                }
                throw new ParserException("Failed to find FourCC VC1 initialization data");
            } catch (ArrayIndexOutOfBoundsException unused) {
                throw new ParserException("Error parsing FourCC VC1 codec private");
            }
        }

        private static boolean parseMsAcmCodecPrivate(ParsableByteArray parsableByteArray) throws ParserException {
            try {
                int littleEndianUnsignedShort = parsableByteArray.readLittleEndianUnsignedShort();
                if (littleEndianUnsignedShort == 1) {
                    return true;
                }
                if (littleEndianUnsignedShort != MatroskaExtractor.WAVE_FORMAT_EXTENSIBLE) {
                    return false;
                }
                parsableByteArray.setPosition(24);
                if (parsableByteArray.readLong() == MatroskaExtractor.WAVE_SUBFORMAT_PCM.getMostSignificantBits()) {
                    if (parsableByteArray.readLong() == MatroskaExtractor.WAVE_SUBFORMAT_PCM.getLeastSignificantBits()) {
                        return true;
                    }
                }
                return false;
            } catch (ArrayIndexOutOfBoundsException unused) {
                throw new ParserException("Error parsing MS/ACM codec private");
            }
        }

        private static List<byte[]> parseVorbisCodecPrivate(byte[] bArr) throws ParserException {
            int i9;
            int i10;
            try {
                if (bArr[0] != 2) {
                    throw new ParserException("Error parsing vorbis codec private");
                }
                int i11 = 0;
                int i12 = 1;
                while (true) {
                    i9 = bArr[i12];
                    if (i9 != -1) {
                        break;
                    }
                    i11 += 255;
                    i12++;
                }
                int i13 = i12 + 1;
                int i14 = i11 + i9;
                int i15 = 0;
                while (true) {
                    i10 = bArr[i13];
                    if (i10 != -1) {
                        break;
                    }
                    i15 += 255;
                    i13++;
                }
                int i16 = i13 + 1;
                int i17 = i15 + i10;
                if (bArr[i16] != 1) {
                    throw new ParserException("Error parsing vorbis codec private");
                }
                byte[] bArr2 = new byte[i14];
                System.arraycopy(bArr, i16, bArr2, 0, i14);
                int i18 = i16 + i14;
                if (bArr[i18] != 3) {
                    throw new ParserException("Error parsing vorbis codec private");
                }
                int i19 = i18 + i17;
                if (bArr[i19] != 5) {
                    throw new ParserException("Error parsing vorbis codec private");
                }
                byte[] bArr3 = new byte[bArr.length - i19];
                System.arraycopy(bArr, i19, bArr3, 0, bArr.length - i19);
                ArrayList arrayList = new ArrayList(2);
                arrayList.add(bArr2);
                arrayList.add(bArr3);
                return arrayList;
            } catch (ArrayIndexOutOfBoundsException unused) {
                throw new ParserException("Error parsing vorbis codec private");
            }
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        /* JADX WARN: Removed duplicated region for block: B:177:0x0349  */
        /* JADX WARN: Removed duplicated region for block: B:180:0x0351  */
        /* JADX WARN: Removed duplicated region for block: B:181:0x0377  */
        /* JADX WARN: Removed duplicated region for block: B:4:0x0014  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public void initializeOutput(ExtractorOutput extractorOutput, int i9) throws ParserException {
            char c9;
            List<byte[]> listSingletonList;
            String str;
            int pcmEncoding;
            String str2;
            String str3;
            int i10;
            String str4;
            int i11;
            List<byte[]> list;
            int i12;
            Format formatCreateImageSampleFormat;
            int i13;
            int i14;
            String str5 = this.codecId;
            str5.hashCode();
            int i15 = 1;
            switch (str5.hashCode()) {
                case -2095576542:
                    if (!str5.equals(MatroskaExtractor.CODEC_ID_MPEG4_AP)) {
                        c9 = 65535;
                        break;
                    } else {
                        c9 = 0;
                        break;
                    }
                case -2095575984:
                    if (str5.equals(MatroskaExtractor.CODEC_ID_MPEG4_SP)) {
                        c9 = 1;
                        break;
                    }
                    break;
                case -1985379776:
                    if (str5.equals(MatroskaExtractor.CODEC_ID_ACM)) {
                        c9 = 2;
                        break;
                    }
                    break;
                case -1784763192:
                    if (str5.equals(MatroskaExtractor.CODEC_ID_TRUEHD)) {
                        c9 = 3;
                        break;
                    }
                    break;
                case -1730367663:
                    if (str5.equals(MatroskaExtractor.CODEC_ID_VORBIS)) {
                        c9 = 4;
                        break;
                    }
                    break;
                case -1482641358:
                    if (str5.equals(MatroskaExtractor.CODEC_ID_MP2)) {
                        c9 = 5;
                        break;
                    }
                    break;
                case -1482641357:
                    if (str5.equals(MatroskaExtractor.CODEC_ID_MP3)) {
                        c9 = 6;
                        break;
                    }
                    break;
                case -1373388978:
                    if (str5.equals(MatroskaExtractor.CODEC_ID_FOURCC)) {
                        c9 = 7;
                        break;
                    }
                    break;
                case -933872740:
                    if (str5.equals(MatroskaExtractor.CODEC_ID_DVBSUB)) {
                        c9 = '\b';
                        break;
                    }
                    break;
                case -538363189:
                    if (str5.equals(MatroskaExtractor.CODEC_ID_MPEG4_ASP)) {
                        c9 = '\t';
                        break;
                    }
                    break;
                case -538363109:
                    if (str5.equals(MatroskaExtractor.CODEC_ID_H264)) {
                        c9 = '\n';
                        break;
                    }
                    break;
                case -425012669:
                    if (str5.equals(MatroskaExtractor.CODEC_ID_VOBSUB)) {
                        c9 = 11;
                        break;
                    }
                    break;
                case -356037306:
                    if (str5.equals(MatroskaExtractor.CODEC_ID_DTS_LOSSLESS)) {
                        c9 = '\f';
                        break;
                    }
                    break;
                case 62923557:
                    if (str5.equals(MatroskaExtractor.CODEC_ID_AAC)) {
                        c9 = CharUtils.f19105CR;
                        break;
                    }
                    break;
                case 62923603:
                    if (str5.equals(MatroskaExtractor.CODEC_ID_AC3)) {
                        c9 = 14;
                        break;
                    }
                    break;
                case 62927045:
                    if (str5.equals(MatroskaExtractor.CODEC_ID_DTS)) {
                        c9 = 15;
                        break;
                    }
                    break;
                case 82338133:
                    if (str5.equals(MatroskaExtractor.CODEC_ID_VP8)) {
                        c9 = 16;
                        break;
                    }
                    break;
                case 82338134:
                    if (str5.equals(MatroskaExtractor.CODEC_ID_VP9)) {
                        c9 = 17;
                        break;
                    }
                    break;
                case 99146302:
                    if (str5.equals(MatroskaExtractor.CODEC_ID_PGS)) {
                        c9 = 18;
                        break;
                    }
                    break;
                case 444813526:
                    if (str5.equals(MatroskaExtractor.CODEC_ID_THEORA)) {
                        c9 = 19;
                        break;
                    }
                    break;
                case 542569478:
                    if (str5.equals(MatroskaExtractor.CODEC_ID_DTS_EXPRESS)) {
                        c9 = 20;
                        break;
                    }
                    break;
                case 725957860:
                    if (str5.equals(MatroskaExtractor.CODEC_ID_PCM_INT_LIT)) {
                        c9 = 21;
                        break;
                    }
                    break;
                case 738597099:
                    if (str5.equals(MatroskaExtractor.CODEC_ID_ASS)) {
                        c9 = 22;
                        break;
                    }
                    break;
                case 855502857:
                    if (str5.equals(MatroskaExtractor.CODEC_ID_H265)) {
                        c9 = 23;
                        break;
                    }
                    break;
                case 1422270023:
                    if (str5.equals(MatroskaExtractor.CODEC_ID_SUBRIP)) {
                        c9 = 24;
                        break;
                    }
                    break;
                case 1809237540:
                    if (str5.equals(MatroskaExtractor.CODEC_ID_MPEG2)) {
                        c9 = 25;
                        break;
                    }
                    break;
                case 1950749482:
                    if (str5.equals(MatroskaExtractor.CODEC_ID_E_AC3)) {
                        c9 = 26;
                        break;
                    }
                    break;
                case 1950789798:
                    if (str5.equals(MatroskaExtractor.CODEC_ID_FLAC)) {
                        c9 = 27;
                        break;
                    }
                    break;
                case 1951062397:
                    if (str5.equals(MatroskaExtractor.CODEC_ID_OPUS)) {
                        c9 = 28;
                        break;
                    }
                    break;
            }
            String str6 = MimeTypes.AUDIO_UNKNOWN;
            switch (c9) {
                case 0:
                case 1:
                case '\t':
                    byte[] bArr = this.codecPrivate;
                    listSingletonList = bArr == null ? null : Collections.singletonList(bArr);
                    str = MimeTypes.VIDEO_MP4V;
                    str6 = str;
                    i12 = -1;
                    i11 = -1;
                    int i16 = (this.flagDefault ? 1 : 0) | 0 | (this.flagForced ? 2 : 0);
                    if (MimeTypes.isAudio(str6)) {
                        formatCreateImageSampleFormat = Format.createAudioSampleFormat(Integer.toString(i9), str6, null, -1, i11, this.channelCount, this.sampleRate, i12, listSingletonList, this.drmInitData, i16, this.language);
                    } else if (MimeTypes.isVideo(str6)) {
                        if (this.displayUnit == 0) {
                            int i17 = this.displayWidth;
                            i13 = -1;
                            if (i17 == -1) {
                                i17 = this.width;
                            }
                            this.displayWidth = i17;
                            int i18 = this.displayHeight;
                            if (i18 == -1) {
                                i18 = this.height;
                            }
                            this.displayHeight = i18;
                        } else {
                            i13 = -1;
                        }
                        formatCreateImageSampleFormat = Format.createVideoSampleFormat(Integer.toString(i9), str6, null, -1, i11, this.width, this.height, -1.0f, listSingletonList, -1, (this.displayWidth == i13 || (i14 = this.displayHeight) == i13) ? -1.0f : (this.height * r2) / (this.width * i14), this.projectionData, this.stereoMode, this.hasColorInfo ? new ColorInfo(this.colorSpace, this.colorRange, this.colorTransfer, getHdrStaticInfo()) : null, this.drmInitData);
                        i15 = 2;
                    } else {
                        if (MimeTypes.APPLICATION_SUBRIP.equals(str6)) {
                            formatCreateImageSampleFormat = Format.createTextSampleFormat(Integer.toString(i9), str6, i16, this.language, this.drmInitData);
                        } else if (MimeTypes.TEXT_SSA.equals(str6)) {
                            ArrayList arrayList = new ArrayList(2);
                            arrayList.add(MatroskaExtractor.SSA_DIALOGUE_FORMAT);
                            arrayList.add(this.codecPrivate);
                            formatCreateImageSampleFormat = Format.createTextSampleFormat(Integer.toString(i9), str6, null, -1, i16, this.language, -1, this.drmInitData, Long.MAX_VALUE, arrayList);
                        } else {
                            if (!MimeTypes.APPLICATION_VOBSUB.equals(str6) && !MimeTypes.APPLICATION_PGS.equals(str6) && !MimeTypes.APPLICATION_DVBSUBS.equals(str6)) {
                                throw new ParserException("Unexpected MIME type.");
                            }
                            formatCreateImageSampleFormat = Format.createImageSampleFormat(Integer.toString(i9), str6, null, -1, i16, listSingletonList, this.language, this.drmInitData);
                        }
                        i15 = 3;
                    }
                    TrackOutput trackOutputTrack = extractorOutput.track(this.number, i15);
                    this.output = trackOutputTrack;
                    trackOutputTrack.format(formatCreateImageSampleFormat);
                    return;
                case 2:
                    if (parseMsAcmCodecPrivate(new ParsableByteArray(this.codecPrivate))) {
                        pcmEncoding = Util.getPcmEncoding(this.audioBitDepth);
                        if (pcmEncoding == 0) {
                            Log.w(MatroskaExtractor.TAG, "Unsupported PCM bit depth: " + this.audioBitDepth + ". Setting mimeType to " + MimeTypes.AUDIO_UNKNOWN);
                        }
                        i12 = pcmEncoding;
                        str6 = MimeTypes.AUDIO_RAW;
                        listSingletonList = null;
                        i11 = -1;
                        int i162 = (this.flagDefault ? 1 : 0) | 0 | (this.flagForced ? 2 : 0);
                        if (MimeTypes.isAudio(str6)) {
                        }
                        TrackOutput trackOutputTrack2 = extractorOutput.track(this.number, i15);
                        this.output = trackOutputTrack2;
                        trackOutputTrack2.format(formatCreateImageSampleFormat);
                        return;
                    }
                    Log.w(MatroskaExtractor.TAG, "Non-PCM MS/ACM is unsupported. Setting mimeType to " + MimeTypes.AUDIO_UNKNOWN);
                    listSingletonList = null;
                    i12 = -1;
                    i11 = -1;
                    int i1622 = (this.flagDefault ? 1 : 0) | 0 | (this.flagForced ? 2 : 0);
                    if (MimeTypes.isAudio(str6)) {
                    }
                    TrackOutput trackOutputTrack22 = extractorOutput.track(this.number, i15);
                    this.output = trackOutputTrack22;
                    trackOutputTrack22.format(formatCreateImageSampleFormat);
                    return;
                case 3:
                    this.trueHdSampleRechunker = new TrueHdSampleRechunker();
                    str2 = MimeTypes.AUDIO_TRUEHD;
                    str6 = str2;
                    listSingletonList = null;
                    i12 = -1;
                    i11 = -1;
                    int i16222 = (this.flagDefault ? 1 : 0) | 0 | (this.flagForced ? 2 : 0);
                    if (MimeTypes.isAudio(str6)) {
                    }
                    TrackOutput trackOutputTrack222 = extractorOutput.track(this.number, i15);
                    this.output = trackOutputTrack222;
                    trackOutputTrack222.format(formatCreateImageSampleFormat);
                    return;
                case 4:
                    listSingletonList = parseVorbisCodecPrivate(this.codecPrivate);
                    str3 = MimeTypes.AUDIO_VORBIS;
                    i10 = 8192;
                    str6 = str3;
                    i11 = i10;
                    i12 = -1;
                    int i162222 = (this.flagDefault ? 1 : 0) | 0 | (this.flagForced ? 2 : 0);
                    if (MimeTypes.isAudio(str6)) {
                    }
                    TrackOutput trackOutputTrack2222 = extractorOutput.track(this.number, i15);
                    this.output = trackOutputTrack2222;
                    trackOutputTrack2222.format(formatCreateImageSampleFormat);
                    return;
                case 5:
                    str4 = MimeTypes.AUDIO_MPEG_L2;
                    str6 = str4;
                    i11 = 4096;
                    listSingletonList = null;
                    i12 = -1;
                    int i1622222 = (this.flagDefault ? 1 : 0) | 0 | (this.flagForced ? 2 : 0);
                    if (MimeTypes.isAudio(str6)) {
                    }
                    TrackOutput trackOutputTrack22222 = extractorOutput.track(this.number, i15);
                    this.output = trackOutputTrack22222;
                    trackOutputTrack22222.format(formatCreateImageSampleFormat);
                    return;
                case 6:
                    str4 = MimeTypes.AUDIO_MPEG;
                    str6 = str4;
                    i11 = 4096;
                    listSingletonList = null;
                    i12 = -1;
                    int i16222222 = (this.flagDefault ? 1 : 0) | 0 | (this.flagForced ? 2 : 0);
                    if (MimeTypes.isAudio(str6)) {
                    }
                    TrackOutput trackOutputTrack222222 = extractorOutput.track(this.number, i15);
                    this.output = trackOutputTrack222222;
                    trackOutputTrack222222.format(formatCreateImageSampleFormat);
                    return;
                case 7:
                    listSingletonList = parseFourCcVc1Private(new ParsableByteArray(this.codecPrivate));
                    if (listSingletonList != null) {
                        str = MimeTypes.VIDEO_VC1;
                        str6 = str;
                        i12 = -1;
                        i11 = -1;
                        int i162222222 = (this.flagDefault ? 1 : 0) | 0 | (this.flagForced ? 2 : 0);
                        if (MimeTypes.isAudio(str6)) {
                        }
                        TrackOutput trackOutputTrack2222222 = extractorOutput.track(this.number, i15);
                        this.output = trackOutputTrack2222222;
                        trackOutputTrack2222222.format(formatCreateImageSampleFormat);
                        return;
                    }
                    Log.w(MatroskaExtractor.TAG, "Unsupported FourCC. Setting mimeType to video/x-unknown");
                    str6 = MimeTypes.VIDEO_UNKNOWN;
                    i12 = -1;
                    i11 = -1;
                    int i1622222222 = (this.flagDefault ? 1 : 0) | 0 | (this.flagForced ? 2 : 0);
                    if (MimeTypes.isAudio(str6)) {
                    }
                    TrackOutput trackOutputTrack22222222 = extractorOutput.track(this.number, i15);
                    this.output = trackOutputTrack22222222;
                    trackOutputTrack22222222.format(formatCreateImageSampleFormat);
                    return;
                case '\b':
                    byte[] bArr2 = this.codecPrivate;
                    listSingletonList = Collections.singletonList(new byte[]{bArr2[0], bArr2[1], bArr2[2], bArr2[3]});
                    str6 = MimeTypes.APPLICATION_DVBSUBS;
                    i12 = -1;
                    i11 = -1;
                    int i16222222222 = (this.flagDefault ? 1 : 0) | 0 | (this.flagForced ? 2 : 0);
                    if (MimeTypes.isAudio(str6)) {
                    }
                    TrackOutput trackOutputTrack222222222 = extractorOutput.track(this.number, i15);
                    this.output = trackOutputTrack222222222;
                    trackOutputTrack222222222.format(formatCreateImageSampleFormat);
                    return;
                case '\n':
                    AvcConfig avcConfig = AvcConfig.parse(new ParsableByteArray(this.codecPrivate));
                    list = avcConfig.initializationData;
                    this.nalUnitLengthFieldLength = avcConfig.nalUnitLengthFieldLength;
                    str = MimeTypes.VIDEO_H264;
                    listSingletonList = list;
                    str6 = str;
                    i12 = -1;
                    i11 = -1;
                    int i162222222222 = (this.flagDefault ? 1 : 0) | 0 | (this.flagForced ? 2 : 0);
                    if (MimeTypes.isAudio(str6)) {
                    }
                    TrackOutput trackOutputTrack2222222222 = extractorOutput.track(this.number, i15);
                    this.output = trackOutputTrack2222222222;
                    trackOutputTrack2222222222.format(formatCreateImageSampleFormat);
                    return;
                case 11:
                    listSingletonList = Collections.singletonList(this.codecPrivate);
                    str6 = MimeTypes.APPLICATION_VOBSUB;
                    i12 = -1;
                    i11 = -1;
                    int i1622222222222 = (this.flagDefault ? 1 : 0) | 0 | (this.flagForced ? 2 : 0);
                    if (MimeTypes.isAudio(str6)) {
                    }
                    TrackOutput trackOutputTrack22222222222 = extractorOutput.track(this.number, i15);
                    this.output = trackOutputTrack22222222222;
                    trackOutputTrack22222222222.format(formatCreateImageSampleFormat);
                    return;
                case '\f':
                    str2 = MimeTypes.AUDIO_DTS_HD;
                    str6 = str2;
                    listSingletonList = null;
                    i12 = -1;
                    i11 = -1;
                    int i16222222222222 = (this.flagDefault ? 1 : 0) | 0 | (this.flagForced ? 2 : 0);
                    if (MimeTypes.isAudio(str6)) {
                    }
                    TrackOutput trackOutputTrack222222222222 = extractorOutput.track(this.number, i15);
                    this.output = trackOutputTrack222222222222;
                    trackOutputTrack222222222222.format(formatCreateImageSampleFormat);
                    return;
                case '\r':
                    listSingletonList = Collections.singletonList(this.codecPrivate);
                    str = MimeTypes.AUDIO_AAC;
                    str6 = str;
                    i12 = -1;
                    i11 = -1;
                    int i162222222222222 = (this.flagDefault ? 1 : 0) | 0 | (this.flagForced ? 2 : 0);
                    if (MimeTypes.isAudio(str6)) {
                    }
                    TrackOutput trackOutputTrack2222222222222 = extractorOutput.track(this.number, i15);
                    this.output = trackOutputTrack2222222222222;
                    trackOutputTrack2222222222222.format(formatCreateImageSampleFormat);
                    return;
                case 14:
                    str2 = MimeTypes.AUDIO_AC3;
                    str6 = str2;
                    listSingletonList = null;
                    i12 = -1;
                    i11 = -1;
                    int i1622222222222222 = (this.flagDefault ? 1 : 0) | 0 | (this.flagForced ? 2 : 0);
                    if (MimeTypes.isAudio(str6)) {
                    }
                    TrackOutput trackOutputTrack22222222222222 = extractorOutput.track(this.number, i15);
                    this.output = trackOutputTrack22222222222222;
                    trackOutputTrack22222222222222.format(formatCreateImageSampleFormat);
                    return;
                case 15:
                case 20:
                    str2 = MimeTypes.AUDIO_DTS;
                    str6 = str2;
                    listSingletonList = null;
                    i12 = -1;
                    i11 = -1;
                    int i16222222222222222 = (this.flagDefault ? 1 : 0) | 0 | (this.flagForced ? 2 : 0);
                    if (MimeTypes.isAudio(str6)) {
                    }
                    TrackOutput trackOutputTrack222222222222222 = extractorOutput.track(this.number, i15);
                    this.output = trackOutputTrack222222222222222;
                    trackOutputTrack222222222222222.format(formatCreateImageSampleFormat);
                    return;
                case 16:
                    str2 = MimeTypes.VIDEO_VP8;
                    str6 = str2;
                    listSingletonList = null;
                    i12 = -1;
                    i11 = -1;
                    int i162222222222222222 = (this.flagDefault ? 1 : 0) | 0 | (this.flagForced ? 2 : 0);
                    if (MimeTypes.isAudio(str6)) {
                    }
                    TrackOutput trackOutputTrack2222222222222222 = extractorOutput.track(this.number, i15);
                    this.output = trackOutputTrack2222222222222222;
                    trackOutputTrack2222222222222222.format(formatCreateImageSampleFormat);
                    return;
                case 17:
                    str2 = MimeTypes.VIDEO_VP9;
                    str6 = str2;
                    listSingletonList = null;
                    i12 = -1;
                    i11 = -1;
                    int i1622222222222222222 = (this.flagDefault ? 1 : 0) | 0 | (this.flagForced ? 2 : 0);
                    if (MimeTypes.isAudio(str6)) {
                    }
                    TrackOutput trackOutputTrack22222222222222222 = extractorOutput.track(this.number, i15);
                    this.output = trackOutputTrack22222222222222222;
                    trackOutputTrack22222222222222222.format(formatCreateImageSampleFormat);
                    return;
                case 18:
                    str6 = MimeTypes.APPLICATION_PGS;
                    listSingletonList = null;
                    i12 = -1;
                    i11 = -1;
                    int i16222222222222222222 = (this.flagDefault ? 1 : 0) | 0 | (this.flagForced ? 2 : 0);
                    if (MimeTypes.isAudio(str6)) {
                    }
                    TrackOutput trackOutputTrack222222222222222222 = extractorOutput.track(this.number, i15);
                    this.output = trackOutputTrack222222222222222222;
                    trackOutputTrack222222222222222222.format(formatCreateImageSampleFormat);
                    return;
                case 19:
                    str6 = MimeTypes.VIDEO_UNKNOWN;
                    listSingletonList = null;
                    i12 = -1;
                    i11 = -1;
                    int i162222222222222222222 = (this.flagDefault ? 1 : 0) | 0 | (this.flagForced ? 2 : 0);
                    if (MimeTypes.isAudio(str6)) {
                    }
                    TrackOutput trackOutputTrack2222222222222222222 = extractorOutput.track(this.number, i15);
                    this.output = trackOutputTrack2222222222222222222;
                    trackOutputTrack2222222222222222222.format(formatCreateImageSampleFormat);
                    return;
                case 21:
                    pcmEncoding = Util.getPcmEncoding(this.audioBitDepth);
                    if (pcmEncoding == 0) {
                        Log.w(MatroskaExtractor.TAG, "Unsupported PCM bit depth: " + this.audioBitDepth + ". Setting mimeType to " + MimeTypes.AUDIO_UNKNOWN);
                        listSingletonList = null;
                        i12 = -1;
                        i11 = -1;
                        int i1622222222222222222222 = (this.flagDefault ? 1 : 0) | 0 | (this.flagForced ? 2 : 0);
                        if (MimeTypes.isAudio(str6)) {
                        }
                        TrackOutput trackOutputTrack22222222222222222222 = extractorOutput.track(this.number, i15);
                        this.output = trackOutputTrack22222222222222222222;
                        trackOutputTrack22222222222222222222.format(formatCreateImageSampleFormat);
                        return;
                    }
                    i12 = pcmEncoding;
                    str6 = MimeTypes.AUDIO_RAW;
                    listSingletonList = null;
                    i11 = -1;
                    int i16222222222222222222222 = (this.flagDefault ? 1 : 0) | 0 | (this.flagForced ? 2 : 0);
                    if (MimeTypes.isAudio(str6)) {
                    }
                    TrackOutput trackOutputTrack222222222222222222222 = extractorOutput.track(this.number, i15);
                    this.output = trackOutputTrack222222222222222222222;
                    trackOutputTrack222222222222222222222.format(formatCreateImageSampleFormat);
                    return;
                case 22:
                    str6 = MimeTypes.TEXT_SSA;
                    listSingletonList = null;
                    i12 = -1;
                    i11 = -1;
                    int i162222222222222222222222 = (this.flagDefault ? 1 : 0) | 0 | (this.flagForced ? 2 : 0);
                    if (MimeTypes.isAudio(str6)) {
                    }
                    TrackOutput trackOutputTrack2222222222222222222222 = extractorOutput.track(this.number, i15);
                    this.output = trackOutputTrack2222222222222222222222;
                    trackOutputTrack2222222222222222222222.format(formatCreateImageSampleFormat);
                    return;
                case 23:
                    HevcConfig hevcConfig = HevcConfig.parse(new ParsableByteArray(this.codecPrivate));
                    list = hevcConfig.initializationData;
                    this.nalUnitLengthFieldLength = hevcConfig.nalUnitLengthFieldLength;
                    str = MimeTypes.VIDEO_H265;
                    listSingletonList = list;
                    str6 = str;
                    i12 = -1;
                    i11 = -1;
                    int i1622222222222222222222222 = (this.flagDefault ? 1 : 0) | 0 | (this.flagForced ? 2 : 0);
                    if (MimeTypes.isAudio(str6)) {
                    }
                    TrackOutput trackOutputTrack22222222222222222222222 = extractorOutput.track(this.number, i15);
                    this.output = trackOutputTrack22222222222222222222222;
                    trackOutputTrack22222222222222222222222.format(formatCreateImageSampleFormat);
                    return;
                case 24:
                    str6 = MimeTypes.APPLICATION_SUBRIP;
                    listSingletonList = null;
                    i12 = -1;
                    i11 = -1;
                    int i16222222222222222222222222 = (this.flagDefault ? 1 : 0) | 0 | (this.flagForced ? 2 : 0);
                    if (MimeTypes.isAudio(str6)) {
                    }
                    TrackOutput trackOutputTrack222222222222222222222222 = extractorOutput.track(this.number, i15);
                    this.output = trackOutputTrack222222222222222222222222;
                    trackOutputTrack222222222222222222222222.format(formatCreateImageSampleFormat);
                    return;
                case 25:
                    str2 = MimeTypes.VIDEO_MPEG2;
                    str6 = str2;
                    listSingletonList = null;
                    i12 = -1;
                    i11 = -1;
                    int i162222222222222222222222222 = (this.flagDefault ? 1 : 0) | 0 | (this.flagForced ? 2 : 0);
                    if (MimeTypes.isAudio(str6)) {
                    }
                    TrackOutput trackOutputTrack2222222222222222222222222 = extractorOutput.track(this.number, i15);
                    this.output = trackOutputTrack2222222222222222222222222;
                    trackOutputTrack2222222222222222222222222.format(formatCreateImageSampleFormat);
                    return;
                case 26:
                    str2 = MimeTypes.AUDIO_E_AC3;
                    str6 = str2;
                    listSingletonList = null;
                    i12 = -1;
                    i11 = -1;
                    int i1622222222222222222222222222 = (this.flagDefault ? 1 : 0) | 0 | (this.flagForced ? 2 : 0);
                    if (MimeTypes.isAudio(str6)) {
                    }
                    TrackOutput trackOutputTrack22222222222222222222222222 = extractorOutput.track(this.number, i15);
                    this.output = trackOutputTrack22222222222222222222222222;
                    trackOutputTrack22222222222222222222222222.format(formatCreateImageSampleFormat);
                    return;
                case 27:
                    listSingletonList = Collections.singletonList(this.codecPrivate);
                    str = MimeTypes.AUDIO_FLAC;
                    str6 = str;
                    i12 = -1;
                    i11 = -1;
                    int i16222222222222222222222222222 = (this.flagDefault ? 1 : 0) | 0 | (this.flagForced ? 2 : 0);
                    if (MimeTypes.isAudio(str6)) {
                    }
                    TrackOutput trackOutputTrack222222222222222222222222222 = extractorOutput.track(this.number, i15);
                    this.output = trackOutputTrack222222222222222222222222222;
                    trackOutputTrack222222222222222222222222222.format(formatCreateImageSampleFormat);
                    return;
                case 28:
                    listSingletonList = new ArrayList<>(3);
                    listSingletonList.add(this.codecPrivate);
                    listSingletonList.add(ByteBuffer.allocate(8).order(ByteOrder.nativeOrder()).putLong(this.codecDelayNs).array());
                    listSingletonList.add(ByteBuffer.allocate(8).order(ByteOrder.nativeOrder()).putLong(this.seekPreRollNs).array());
                    str3 = MimeTypes.AUDIO_OPUS;
                    i10 = MatroskaExtractor.OPUS_MAX_INPUT_SIZE;
                    str6 = str3;
                    i11 = i10;
                    i12 = -1;
                    int i162222222222222222222222222222 = (this.flagDefault ? 1 : 0) | 0 | (this.flagForced ? 2 : 0);
                    if (MimeTypes.isAudio(str6)) {
                    }
                    TrackOutput trackOutputTrack2222222222222222222222222222 = extractorOutput.track(this.number, i15);
                    this.output = trackOutputTrack2222222222222222222222222222;
                    trackOutputTrack2222222222222222222222222222.format(formatCreateImageSampleFormat);
                    return;
                default:
                    throw new ParserException("Unrecognized codec identifier.");
            }
        }

        public void outputPendingSampleMetadata() {
            TrueHdSampleRechunker trueHdSampleRechunker = this.trueHdSampleRechunker;
            if (trueHdSampleRechunker != null) {
                trueHdSampleRechunker.outputPendingSampleMetadata(this);
            }
        }

        public void reset() {
            TrueHdSampleRechunker trueHdSampleRechunker = this.trueHdSampleRechunker;
            if (trueHdSampleRechunker != null) {
                trueHdSampleRechunker.reset();
            }
        }
    }

    public static final class TrueHdSampleRechunker {
        private int blockFlags;
        private int chunkSize;
        private boolean foundSyncframe;
        private int sampleCount;
        private final byte[] syncframePrefix = new byte[12];
        private long timeUs;

        public void outputPendingSampleMetadata(Track track) {
            if (!this.foundSyncframe || this.sampleCount <= 0) {
                return;
            }
            track.output.sampleMetadata(this.timeUs, this.blockFlags, this.chunkSize, 0, track.cryptoData);
            this.sampleCount = 0;
        }

        public void reset() {
            this.foundSyncframe = false;
        }

        public void sampleMetadata(Track track, long j9) {
            if (this.foundSyncframe) {
                int i9 = this.sampleCount;
                int i10 = i9 + 1;
                this.sampleCount = i10;
                if (i9 == 0) {
                    this.timeUs = j9;
                }
                if (i10 < 8) {
                    return;
                }
                track.output.sampleMetadata(this.timeUs, this.blockFlags, this.chunkSize, 0, track.cryptoData);
                this.sampleCount = 0;
            }
        }

        public void startSample(ExtractorInput extractorInput, int i9, int i10) {
            if (!this.foundSyncframe) {
                extractorInput.peekFully(this.syncframePrefix, 0, 12);
                extractorInput.resetPeekPosition();
                if (Ac3Util.parseTrueHdSyncframeAudioSampleCount(this.syncframePrefix) == -1) {
                    return;
                }
                this.foundSyncframe = true;
                this.sampleCount = 0;
            }
            if (this.sampleCount == 0) {
                this.blockFlags = i9;
                this.chunkSize = 0;
            }
            this.chunkSize += i10;
        }
    }

    public MatroskaExtractor() {
        this(0);
    }

    private SeekMap buildSeekMap() {
        LongArray longArray;
        LongArray longArray2;
        if (this.segmentContentPosition == -1 || this.durationUs == C3322C.TIME_UNSET || (longArray = this.cueTimesUs) == null || longArray.size() == 0 || (longArray2 = this.cueClusterPositions) == null || longArray2.size() != this.cueTimesUs.size()) {
            this.cueTimesUs = null;
            this.cueClusterPositions = null;
            return new SeekMap.Unseekable(this.durationUs);
        }
        int size = this.cueTimesUs.size();
        int[] iArr = new int[size];
        long[] jArr = new long[size];
        long[] jArr2 = new long[size];
        long[] jArr3 = new long[size];
        int i9 = 0;
        for (int i10 = 0; i10 < size; i10++) {
            jArr3[i10] = this.cueTimesUs.get(i10);
            jArr[i10] = this.segmentContentPosition + this.cueClusterPositions.get(i10);
        }
        while (true) {
            int i11 = size - 1;
            if (i9 >= i11) {
                iArr[i11] = (int) ((this.segmentContentPosition + this.segmentContentSize) - jArr[i11]);
                jArr2[i11] = this.durationUs - jArr3[i11];
                this.cueTimesUs = null;
                this.cueClusterPositions = null;
                return new ChunkIndex(iArr, jArr, jArr2, jArr3);
            }
            int i12 = i9 + 1;
            iArr[i9] = (int) (jArr[i12] - jArr[i9]);
            jArr2[i9] = jArr3[i12] - jArr3[i9];
            i9 = i12;
        }
    }

    private void commitSampleToOutput(Track track, long j9) {
        TrueHdSampleRechunker trueHdSampleRechunker = track.trueHdSampleRechunker;
        if (trueHdSampleRechunker != null) {
            trueHdSampleRechunker.sampleMetadata(track, j9);
        } else {
            if (CODEC_ID_SUBRIP.equals(track.codecId)) {
                commitSubtitleSample(track, SUBRIP_TIMECODE_FORMAT, 19, 1000L, SUBRIP_TIMECODE_EMPTY);
            } else if (CODEC_ID_ASS.equals(track.codecId)) {
                commitSubtitleSample(track, SSA_TIMECODE_FORMAT, 21, SSA_TIMECODE_LAST_VALUE_SCALING_FACTOR, SSA_TIMECODE_EMPTY);
            }
            track.output.sampleMetadata(j9, this.blockFlags, this.sampleBytesWritten, 0, track.cryptoData);
        }
        this.sampleRead = true;
        resetSample();
    }

    private void commitSubtitleSample(Track track, String str, int i9, long j9, byte[] bArr) {
        setSampleDuration(this.subtitleSample.data, this.blockDurationUs, str, i9, j9, bArr);
        TrackOutput trackOutput = track.output;
        ParsableByteArray parsableByteArray = this.subtitleSample;
        trackOutput.sampleData(parsableByteArray, parsableByteArray.limit());
        this.sampleBytesWritten += this.subtitleSample.limit();
    }

    private static int[] ensureArrayCapacity(int[] iArr, int i9) {
        return iArr == null ? new int[i9] : iArr.length >= i9 ? iArr : new int[Math.max(iArr.length * 2, i9)];
    }

    private static boolean isCodecSupported(String str) {
        return CODEC_ID_VP8.equals(str) || CODEC_ID_VP9.equals(str) || CODEC_ID_MPEG2.equals(str) || CODEC_ID_MPEG4_SP.equals(str) || CODEC_ID_MPEG4_ASP.equals(str) || CODEC_ID_MPEG4_AP.equals(str) || CODEC_ID_H264.equals(str) || CODEC_ID_H265.equals(str) || CODEC_ID_FOURCC.equals(str) || CODEC_ID_THEORA.equals(str) || CODEC_ID_OPUS.equals(str) || CODEC_ID_VORBIS.equals(str) || CODEC_ID_AAC.equals(str) || CODEC_ID_MP2.equals(str) || CODEC_ID_MP3.equals(str) || CODEC_ID_AC3.equals(str) || CODEC_ID_E_AC3.equals(str) || CODEC_ID_TRUEHD.equals(str) || CODEC_ID_DTS.equals(str) || CODEC_ID_DTS_EXPRESS.equals(str) || CODEC_ID_DTS_LOSSLESS.equals(str) || CODEC_ID_FLAC.equals(str) || CODEC_ID_ACM.equals(str) || CODEC_ID_PCM_INT_LIT.equals(str) || CODEC_ID_SUBRIP.equals(str) || CODEC_ID_ASS.equals(str) || CODEC_ID_VOBSUB.equals(str) || CODEC_ID_PGS.equals(str) || CODEC_ID_DVBSUB.equals(str);
    }

    private boolean maybeSeekForCues(PositionHolder positionHolder, long j9) {
        if (this.seekForCues) {
            this.seekPositionAfterBuildingCues = j9;
            positionHolder.position = this.cuesContentPosition;
            this.seekForCues = false;
            return true;
        }
        if (this.sentSeekMap) {
            long j10 = this.seekPositionAfterBuildingCues;
            if (j10 != -1) {
                positionHolder.position = j10;
                this.seekPositionAfterBuildingCues = -1L;
                return true;
            }
        }
        return false;
    }

    private void readScratch(ExtractorInput extractorInput, int i9) {
        if (this.scratch.limit() >= i9) {
            return;
        }
        if (this.scratch.capacity() < i9) {
            ParsableByteArray parsableByteArray = this.scratch;
            byte[] bArr = parsableByteArray.data;
            parsableByteArray.reset(Arrays.copyOf(bArr, Math.max(bArr.length * 2, i9)), this.scratch.limit());
        }
        ParsableByteArray parsableByteArray2 = this.scratch;
        extractorInput.readFully(parsableByteArray2.data, parsableByteArray2.limit(), i9 - this.scratch.limit());
        this.scratch.setLimit(i9);
    }

    private int readToOutput(ExtractorInput extractorInput, TrackOutput trackOutput, int i9) {
        int iSampleData;
        int iBytesLeft = this.sampleStrippedBytes.bytesLeft();
        if (iBytesLeft > 0) {
            iSampleData = Math.min(i9, iBytesLeft);
            trackOutput.sampleData(this.sampleStrippedBytes, iSampleData);
        } else {
            iSampleData = trackOutput.sampleData(extractorInput, i9, false);
        }
        this.sampleBytesRead += iSampleData;
        this.sampleBytesWritten += iSampleData;
        return iSampleData;
    }

    private void readToTarget(ExtractorInput extractorInput, byte[] bArr, int i9, int i10) {
        int iMin = Math.min(i10, this.sampleStrippedBytes.bytesLeft());
        extractorInput.readFully(bArr, i9 + iMin, i10 - iMin);
        if (iMin > 0) {
            this.sampleStrippedBytes.readBytes(bArr, i9, iMin);
        }
        this.sampleBytesRead += i10;
    }

    private void resetSample() {
        this.sampleBytesRead = 0;
        this.sampleBytesWritten = 0;
        this.sampleCurrentNalBytesRemaining = 0;
        this.sampleEncodingHandled = false;
        this.sampleSignalByteRead = false;
        this.samplePartitionCountRead = false;
        this.samplePartitionCount = 0;
        this.sampleSignalByte = (byte) 0;
        this.sampleInitializationVectorRead = false;
        this.sampleStrippedBytes.reset();
    }

    private long scaleTimecodeToUs(long j9) throws ParserException {
        long j10 = this.timecodeScale;
        if (j10 != C3322C.TIME_UNSET) {
            return Util.scaleLargeTimestamp(j9, j10, 1000L);
        }
        throw new ParserException("Can't scale timecode prior to timecodeScale being set.");
    }

    private static void setSampleDuration(byte[] bArr, long j9, String str, int i9, long j10, byte[] bArr2) {
        byte[] utf8Bytes;
        if (j9 == C3322C.TIME_UNSET) {
            utf8Bytes = bArr2;
        } else {
            int i10 = (int) (j9 / 3600000000L);
            long j11 = j9 - ((i10 * 3600) * C3322C.MICROS_PER_SECOND);
            int i11 = (int) (j11 / 60000000);
            long j12 = j11 - ((i11 * 60) * C3322C.MICROS_PER_SECOND);
            int i12 = (int) (j12 / C3322C.MICROS_PER_SECOND);
            utf8Bytes = Util.getUtf8Bytes(String.format(Locale.US, str, Integer.valueOf(i10), Integer.valueOf(i11), Integer.valueOf(i12), Integer.valueOf((int) ((j12 - (i12 * C3322C.MICROS_PER_SECOND)) / j10))));
        }
        System.arraycopy(utf8Bytes, 0, bArr, i9, bArr2.length);
    }

    private void writeSampleData(ExtractorInput extractorInput, Track track, int i9) throws ParserException {
        int i10;
        if (CODEC_ID_SUBRIP.equals(track.codecId)) {
            writeSubtitleSampleData(extractorInput, SUBRIP_PREFIX, i9);
            return;
        }
        if (CODEC_ID_ASS.equals(track.codecId)) {
            writeSubtitleSampleData(extractorInput, SSA_PREFIX, i9);
            return;
        }
        TrackOutput trackOutput = track.output;
        if (!this.sampleEncodingHandled) {
            if (track.hasContentEncryption) {
                this.blockFlags &= -1073741825;
                if (!this.sampleSignalByteRead) {
                    extractorInput.readFully(this.scratch.data, 0, 1);
                    this.sampleBytesRead++;
                    byte b9 = this.scratch.data[0];
                    if ((b9 & UnsignedBytes.MAX_POWER_OF_TWO) == 128) {
                        throw new ParserException("Extension bit is set in signal byte");
                    }
                    this.sampleSignalByte = b9;
                    this.sampleSignalByteRead = true;
                }
                byte b10 = this.sampleSignalByte;
                if ((b10 & 1) == 1) {
                    boolean z8 = (b10 & 2) == 2;
                    this.blockFlags |= 1073741824;
                    if (!this.sampleInitializationVectorRead) {
                        extractorInput.readFully(this.encryptionInitializationVector.data, 0, 8);
                        this.sampleBytesRead += 8;
                        this.sampleInitializationVectorRead = true;
                        ParsableByteArray parsableByteArray = this.scratch;
                        parsableByteArray.data[0] = (byte) ((z8 ? 128 : 0) | 8);
                        parsableByteArray.setPosition(0);
                        trackOutput.sampleData(this.scratch, 1);
                        this.sampleBytesWritten++;
                        this.encryptionInitializationVector.setPosition(0);
                        trackOutput.sampleData(this.encryptionInitializationVector, 8);
                        this.sampleBytesWritten += 8;
                    }
                    if (z8) {
                        if (!this.samplePartitionCountRead) {
                            extractorInput.readFully(this.scratch.data, 0, 1);
                            this.sampleBytesRead++;
                            this.scratch.setPosition(0);
                            this.samplePartitionCount = this.scratch.readUnsignedByte();
                            this.samplePartitionCountRead = true;
                        }
                        int i11 = this.samplePartitionCount * 4;
                        this.scratch.reset(i11);
                        extractorInput.readFully(this.scratch.data, 0, i11);
                        this.sampleBytesRead += i11;
                        short s8 = (short) ((this.samplePartitionCount / 2) + 1);
                        int i12 = (s8 * 6) + 2;
                        ByteBuffer byteBuffer = this.encryptionSubsampleDataBuffer;
                        if (byteBuffer == null || byteBuffer.capacity() < i12) {
                            this.encryptionSubsampleDataBuffer = ByteBuffer.allocate(i12);
                        }
                        this.encryptionSubsampleDataBuffer.position(0);
                        this.encryptionSubsampleDataBuffer.putShort(s8);
                        int i13 = 0;
                        int i14 = 0;
                        while (true) {
                            i10 = this.samplePartitionCount;
                            if (i13 >= i10) {
                                break;
                            }
                            int unsignedIntToInt = this.scratch.readUnsignedIntToInt();
                            if (i13 % 2 == 0) {
                                this.encryptionSubsampleDataBuffer.putShort((short) (unsignedIntToInt - i14));
                            } else {
                                this.encryptionSubsampleDataBuffer.putInt(unsignedIntToInt - i14);
                            }
                            i13++;
                            i14 = unsignedIntToInt;
                        }
                        int i15 = (i9 - this.sampleBytesRead) - i14;
                        if (i10 % 2 == 1) {
                            this.encryptionSubsampleDataBuffer.putInt(i15);
                        } else {
                            this.encryptionSubsampleDataBuffer.putShort((short) i15);
                            this.encryptionSubsampleDataBuffer.putInt(0);
                        }
                        this.encryptionSubsampleData.reset(this.encryptionSubsampleDataBuffer.array(), i12);
                        trackOutput.sampleData(this.encryptionSubsampleData, i12);
                        this.sampleBytesWritten += i12;
                    }
                }
            } else {
                byte[] bArr = track.sampleStrippedBytes;
                if (bArr != null) {
                    this.sampleStrippedBytes.reset(bArr, bArr.length);
                }
            }
            this.sampleEncodingHandled = true;
        }
        int iLimit = i9 + this.sampleStrippedBytes.limit();
        if (!CODEC_ID_H264.equals(track.codecId) && !CODEC_ID_H265.equals(track.codecId)) {
            if (track.trueHdSampleRechunker != null) {
                Assertions.checkState(this.sampleStrippedBytes.limit() == 0);
                track.trueHdSampleRechunker.startSample(extractorInput, this.blockFlags, iLimit);
            }
            while (true) {
                int i16 = this.sampleBytesRead;
                if (i16 >= iLimit) {
                    break;
                } else {
                    readToOutput(extractorInput, trackOutput, iLimit - i16);
                }
            }
        } else {
            byte[] bArr2 = this.nalLength.data;
            bArr2[0] = 0;
            bArr2[1] = 0;
            bArr2[2] = 0;
            int i17 = track.nalUnitLengthFieldLength;
            int i18 = 4 - i17;
            while (this.sampleBytesRead < iLimit) {
                int i19 = this.sampleCurrentNalBytesRemaining;
                if (i19 == 0) {
                    readToTarget(extractorInput, bArr2, i18, i17);
                    this.nalLength.setPosition(0);
                    this.sampleCurrentNalBytesRemaining = this.nalLength.readUnsignedIntToInt();
                    this.nalStartCode.setPosition(0);
                    trackOutput.sampleData(this.nalStartCode, 4);
                    this.sampleBytesWritten += 4;
                } else {
                    this.sampleCurrentNalBytesRemaining = i19 - readToOutput(extractorInput, trackOutput, i19);
                }
            }
        }
        if (CODEC_ID_VORBIS.equals(track.codecId)) {
            this.vorbisNumPageSamples.setPosition(0);
            trackOutput.sampleData(this.vorbisNumPageSamples, 4);
            this.sampleBytesWritten += 4;
        }
    }

    private void writeSubtitleSampleData(ExtractorInput extractorInput, byte[] bArr, int i9) {
        int length = bArr.length + i9;
        if (this.subtitleSample.capacity() < length) {
            this.subtitleSample.data = Arrays.copyOf(bArr, length + i9);
        } else {
            System.arraycopy(bArr, 0, this.subtitleSample.data, 0, bArr.length);
        }
        extractorInput.readFully(this.subtitleSample.data, bArr.length, i9);
        this.subtitleSample.reset(length);
    }

    /* JADX WARN: Code restructure failed: missing block: B:75:0x01e5, code lost:
    
        throw new com.google.android.exoplayer2.ParserException("EBML lacing sample size out of range.");
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void binaryElement(int i9, int i10, ExtractorInput extractorInput) throws ParserException {
        int i11;
        char c9;
        long j9;
        int i12;
        int i13;
        int i14;
        int i15 = 0;
        int i16 = 1;
        if (i9 != ID_BLOCK && i9 != ID_SIMPLE_BLOCK) {
            if (i9 == ID_CONTENT_COMPRESSION_SETTINGS) {
                byte[] bArr = new byte[i10];
                this.currentTrack.sampleStrippedBytes = bArr;
                extractorInput.readFully(bArr, 0, i10);
                return;
            }
            if (i9 == ID_CONTENT_ENCRYPTION_KEY_ID) {
                byte[] bArr2 = new byte[i10];
                extractorInput.readFully(bArr2, 0, i10);
                this.currentTrack.cryptoData = new TrackOutput.CryptoData(1, bArr2, 0, 0);
                return;
            }
            if (i9 == ID_SEEK_ID) {
                Arrays.fill(this.seekEntryIdBytes.data, (byte) 0);
                extractorInput.readFully(this.seekEntryIdBytes.data, 4 - i10, i10);
                this.seekEntryIdBytes.setPosition(0);
                this.seekEntryId = (int) this.seekEntryIdBytes.readUnsignedInt();
                return;
            }
            if (i9 == ID_CODEC_PRIVATE) {
                byte[] bArr3 = new byte[i10];
                this.currentTrack.codecPrivate = bArr3;
                extractorInput.readFully(bArr3, 0, i10);
                return;
            } else if (i9 == ID_PROJECTION_PRIVATE) {
                byte[] bArr4 = new byte[i10];
                this.currentTrack.projectionData = bArr4;
                extractorInput.readFully(bArr4, 0, i10);
                return;
            } else {
                throw new ParserException("Unexpected id: " + i9);
            }
        }
        if (this.blockState == 0) {
            this.blockTrackNumber = (int) this.varintReader.readUnsignedVarint(extractorInput, false, true, 8);
            this.blockTrackNumberLength = this.varintReader.getLastLength();
            this.blockDurationUs = C3322C.TIME_UNSET;
            this.blockState = 1;
            this.scratch.reset();
        }
        Track track = this.tracks.get(this.blockTrackNumber);
        if (track == null) {
            extractorInput.skipFully(i10 - this.blockTrackNumberLength);
            this.blockState = 0;
            return;
        }
        if (this.blockState == 1) {
            readScratch(extractorInput, 3);
            int i17 = (this.scratch.data[2] & 6) >> 1;
            byte b9 = UnsignedBytes.MAX_VALUE;
            if (i17 == 0) {
                this.blockLacingSampleCount = 1;
                int[] iArrEnsureArrayCapacity = ensureArrayCapacity(this.blockLacingSampleSizes, 1);
                this.blockLacingSampleSizes = iArrEnsureArrayCapacity;
                iArrEnsureArrayCapacity[0] = (i10 - this.blockTrackNumberLength) - 3;
            } else {
                if (i9 != ID_SIMPLE_BLOCK) {
                    throw new ParserException("Lacing only supported in SimpleBlocks.");
                }
                int i18 = 4;
                readScratch(extractorInput, 4);
                int i19 = (this.scratch.data[3] & UnsignedBytes.MAX_VALUE) + 1;
                this.blockLacingSampleCount = i19;
                int[] iArrEnsureArrayCapacity2 = ensureArrayCapacity(this.blockLacingSampleSizes, i19);
                this.blockLacingSampleSizes = iArrEnsureArrayCapacity2;
                if (i17 == 2) {
                    int i20 = (i10 - this.blockTrackNumberLength) - 4;
                    int i21 = this.blockLacingSampleCount;
                    Arrays.fill(iArrEnsureArrayCapacity2, 0, i21, i20 / i21);
                } else if (i17 == 1) {
                    int i22 = 0;
                    int i23 = 0;
                    while (true) {
                        i12 = this.blockLacingSampleCount;
                        if (i22 >= i12 - 1) {
                            break;
                        }
                        this.blockLacingSampleSizes[i22] = 0;
                        do {
                            i18++;
                            readScratch(extractorInput, i18);
                            i13 = this.scratch.data[i18 - 1] & UnsignedBytes.MAX_VALUE;
                            int[] iArr = this.blockLacingSampleSizes;
                            i14 = iArr[i22] + i13;
                            iArr[i22] = i14;
                        } while (i13 == 255);
                        i23 += i14;
                        i22++;
                    }
                    this.blockLacingSampleSizes[i12 - 1] = ((i10 - this.blockTrackNumberLength) - i18) - i23;
                } else {
                    if (i17 != 3) {
                        throw new ParserException("Unexpected lacing value: " + i17);
                    }
                    int i24 = 0;
                    int i25 = 0;
                    while (true) {
                        int i26 = this.blockLacingSampleCount;
                        if (i24 >= i26 - 1) {
                            c9 = 1;
                            this.blockLacingSampleSizes[i26 - 1] = ((i10 - this.blockTrackNumberLength) - i18) - i25;
                            break;
                        }
                        this.blockLacingSampleSizes[i24] = i15;
                        i18++;
                        readScratch(extractorInput, i18);
                        int i27 = i18 - 1;
                        if (this.scratch.data[i27] == 0) {
                            throw new ParserException("No valid varint length mask found");
                        }
                        int i28 = i15;
                        while (true) {
                            if (i28 >= 8) {
                                j9 = 0;
                                break;
                            }
                            int i29 = i16 << (7 - i28);
                            if ((this.scratch.data[i27] & i29) != 0) {
                                i18 += i28;
                                readScratch(extractorInput, i18);
                                j9 = (~i29) & this.scratch.data[i27] & b9;
                                int i30 = i27 + 1;
                                while (i30 < i18) {
                                    j9 = (j9 << 8) | (this.scratch.data[i30] & b9);
                                    i30++;
                                    b9 = UnsignedBytes.MAX_VALUE;
                                }
                                if (i24 > 0) {
                                    j9 -= (1 << ((i28 * 7) + 6)) - 1;
                                }
                            } else {
                                i28++;
                                i16 = 1;
                                b9 = UnsignedBytes.MAX_VALUE;
                            }
                        }
                        if (j9 < -2147483648L || j9 > 2147483647L) {
                            break;
                        }
                        int i31 = (int) j9;
                        int[] iArr2 = this.blockLacingSampleSizes;
                        if (i24 != 0) {
                            i31 += iArr2[i24 - 1];
                        }
                        iArr2[i24] = i31;
                        i25 += i31;
                        i24++;
                        i15 = 0;
                        i16 = 1;
                        b9 = UnsignedBytes.MAX_VALUE;
                    }
                }
            }
            c9 = 1;
            byte[] bArr5 = this.scratch.data;
            this.blockTimeUs = this.clusterTimecodeUs + scaleTimecodeToUs((bArr5[c9] & UnsignedBytes.MAX_VALUE) | (bArr5[0] << 8));
            byte b10 = this.scratch.data[2];
            this.blockFlags = ((track.type == 2 || (i9 == ID_SIMPLE_BLOCK && (b10 & UnsignedBytes.MAX_POWER_OF_TWO) == 128)) ? 1 : 0) | ((b10 & 8) == 8 ? Integer.MIN_VALUE : 0);
            this.blockState = 2;
            this.blockLacingSampleIndex = 0;
            i11 = ID_SIMPLE_BLOCK;
        } else {
            i11 = ID_SIMPLE_BLOCK;
        }
        if (i9 != i11) {
            writeSampleData(extractorInput, track, this.blockLacingSampleSizes[0]);
            return;
        }
        while (true) {
            int i32 = this.blockLacingSampleIndex;
            if (i32 >= this.blockLacingSampleCount) {
                this.blockState = 0;
                return;
            } else {
                writeSampleData(extractorInput, track, this.blockLacingSampleSizes[i32]);
                commitSampleToOutput(track, this.blockTimeUs + ((this.blockLacingSampleIndex * track.defaultSampleDurationNs) / CredentialsApi.ACTIVITY_RESULT_ADD_ACCOUNT));
                this.blockLacingSampleIndex++;
            }
        }
    }

    public void endMasterElement(int i9) throws ParserException {
        if (i9 == ID_BLOCK_GROUP) {
            if (this.blockState != 2) {
                return;
            }
            if (!this.sampleSeenReferenceBlock) {
                this.blockFlags |= 1;
            }
            commitSampleToOutput(this.tracks.get(this.blockTrackNumber), this.blockTimeUs);
            this.blockState = 0;
            return;
        }
        if (i9 == ID_TRACK_ENTRY) {
            if (isCodecSupported(this.currentTrack.codecId)) {
                Track track = this.currentTrack;
                track.initializeOutput(this.extractorOutput, track.number);
                SparseArray<Track> sparseArray = this.tracks;
                Track track2 = this.currentTrack;
                sparseArray.put(track2.number, track2);
            }
            this.currentTrack = null;
            return;
        }
        if (i9 == ID_SEEK) {
            int i10 = this.seekEntryId;
            if (i10 != -1) {
                long j9 = this.seekEntryPosition;
                if (j9 != -1) {
                    if (i10 == ID_CUES) {
                        this.cuesContentPosition = j9;
                        return;
                    }
                    return;
                }
            }
            throw new ParserException("Mandatory element SeekID or SeekPosition not found");
        }
        if (i9 == ID_CONTENT_ENCODING) {
            Track track3 = this.currentTrack;
            if (track3.hasContentEncryption) {
                if (track3.cryptoData == null) {
                    throw new ParserException("Encrypted Track found but ContentEncKeyID was not found");
                }
                track3.drmInitData = new DrmInitData(new DrmInitData.SchemeData(C3322C.UUID_NIL, MimeTypes.VIDEO_WEBM, this.currentTrack.cryptoData.encryptionKey));
                return;
            }
            return;
        }
        if (i9 == ID_CONTENT_ENCODINGS) {
            Track track4 = this.currentTrack;
            if (track4.hasContentEncryption && track4.sampleStrippedBytes != null) {
                throw new ParserException("Combining encryption and compression is not supported");
            }
            return;
        }
        if (i9 == 357149030) {
            if (this.timecodeScale == C3322C.TIME_UNSET) {
                this.timecodeScale = C3322C.MICROS_PER_SECOND;
            }
            long j10 = this.durationTimecode;
            if (j10 != C3322C.TIME_UNSET) {
                this.durationUs = scaleTimecodeToUs(j10);
                return;
            }
            return;
        }
        if (i9 == ID_TRACKS) {
            if (this.tracks.size() == 0) {
                throw new ParserException("No valid tracks were found");
            }
            this.extractorOutput.endTracks();
        } else if (i9 == ID_CUES && !this.sentSeekMap) {
            this.extractorOutput.seekMap(buildSeekMap());
            this.sentSeekMap = true;
        }
    }

    public void floatElement(int i9, double d9) {
        if (i9 == ID_SAMPLING_FREQUENCY) {
            this.currentTrack.sampleRate = (int) d9;
        }
        if (i9 == ID_DURATION) {
            this.durationTimecode = (long) d9;
            return;
        }
        switch (i9) {
            case ID_PRIMARY_R_CHROMATICITY_X /* 21969 */:
                this.currentTrack.primaryRChromaticityX = (float) d9;
                break;
            case ID_PRIMARY_R_CHROMATICITY_Y /* 21970 */:
                this.currentTrack.primaryRChromaticityY = (float) d9;
                break;
            case ID_PRIMARY_G_CHROMATICITY_X /* 21971 */:
                this.currentTrack.primaryGChromaticityX = (float) d9;
                break;
            case ID_PRIMARY_G_CHROMATICITY_Y /* 21972 */:
                this.currentTrack.primaryGChromaticityY = (float) d9;
                break;
            case ID_PRIMARY_B_CHROMATICITY_X /* 21973 */:
                this.currentTrack.primaryBChromaticityX = (float) d9;
                break;
            case ID_PRIMARY_B_CHROMATICITY_Y /* 21974 */:
                this.currentTrack.primaryBChromaticityY = (float) d9;
                break;
            case ID_WHITE_POINT_CHROMATICITY_X /* 21975 */:
                this.currentTrack.whitePointChromaticityX = (float) d9;
                break;
            case ID_WHITE_POINT_CHROMATICITY_Y /* 21976 */:
                this.currentTrack.whitePointChromaticityY = (float) d9;
                break;
            case ID_LUMNINANCE_MAX /* 21977 */:
                this.currentTrack.maxMasteringLuminance = (float) d9;
                break;
            case ID_LUMNINANCE_MIN /* 21978 */:
                this.currentTrack.minMasteringLuminance = (float) d9;
                break;
        }
    }

    public int getElementType(int i9) {
        switch (i9) {
            case ID_TRACK_TYPE /* 131 */:
            case ID_FLAG_DEFAULT /* 136 */:
            case ID_BLOCK_DURATION /* 155 */:
            case ID_CHANNELS /* 159 */:
            case ID_PIXEL_WIDTH /* 176 */:
            case ID_CUE_TIME /* 179 */:
            case ID_PIXEL_HEIGHT /* 186 */:
            case ID_TRACK_NUMBER /* 215 */:
            case ID_TIME_CODE /* 231 */:
            case ID_CUE_CLUSTER_POSITION /* 241 */:
            case ID_REFERENCE_BLOCK /* 251 */:
            case ID_CONTENT_COMPRESSION_ALGORITHM /* 16980 */:
            case ID_DOC_TYPE_READ_VERSION /* 17029 */:
            case ID_EBML_READ_VERSION /* 17143 */:
            case ID_CONTENT_ENCRYPTION_ALGORITHM /* 18401 */:
            case ID_CONTENT_ENCRYPTION_AES_SETTINGS_CIPHER_MODE /* 18408 */:
            case ID_CONTENT_ENCODING_ORDER /* 20529 */:
            case ID_CONTENT_ENCODING_SCOPE /* 20530 */:
            case ID_SEEK_POSITION /* 21420 */:
            case ID_STEREO_MODE /* 21432 */:
            case ID_DISPLAY_WIDTH /* 21680 */:
            case ID_DISPLAY_UNIT /* 21682 */:
            case ID_DISPLAY_HEIGHT /* 21690 */:
            case ID_FLAG_FORCED /* 21930 */:
            case ID_COLOUR_RANGE /* 21945 */:
            case ID_COLOUR_TRANSFER /* 21946 */:
            case ID_COLOUR_PRIMARIES /* 21947 */:
            case ID_MAX_CLL /* 21948 */:
            case ID_MAX_FALL /* 21949 */:
            case ID_CODEC_DELAY /* 22186 */:
            case ID_SEEK_PRE_ROLL /* 22203 */:
            case ID_AUDIO_BIT_DEPTH /* 25188 */:
            case ID_DEFAULT_DURATION /* 2352003 */:
            case ID_TIMECODE_SCALE /* 2807729 */:
                return 2;
            case 134:
            case 17026:
            case ID_LANGUAGE /* 2274716 */:
                return 3;
            case ID_BLOCK_GROUP /* 160 */:
            case ID_TRACK_ENTRY /* 174 */:
            case ID_CUE_TRACK_POSITIONS /* 183 */:
            case ID_CUE_POINT /* 187 */:
            case 224:
            case ID_AUDIO /* 225 */:
            case ID_CONTENT_ENCRYPTION_AES_SETTINGS /* 18407 */:
            case ID_SEEK /* 19899 */:
            case ID_CONTENT_COMPRESSION /* 20532 */:
            case ID_CONTENT_ENCRYPTION /* 20533 */:
            case ID_COLOUR /* 21936 */:
            case ID_MASTERING_METADATA /* 21968 */:
            case ID_CONTENT_ENCODING /* 25152 */:
            case ID_CONTENT_ENCODINGS /* 28032 */:
            case ID_PROJECTION /* 30320 */:
            case ID_SEEK_HEAD /* 290298740 */:
            case 357149030:
            case ID_TRACKS /* 374648427 */:
            case ID_SEGMENT /* 408125543 */:
            case ID_EBML /* 440786851 */:
            case ID_CUES /* 475249515 */:
            case ID_CLUSTER /* 524531317 */:
                return 1;
            case ID_BLOCK /* 161 */:
            case ID_SIMPLE_BLOCK /* 163 */:
            case ID_CONTENT_COMPRESSION_SETTINGS /* 16981 */:
            case ID_CONTENT_ENCRYPTION_KEY_ID /* 18402 */:
            case ID_SEEK_ID /* 21419 */:
            case ID_CODEC_PRIVATE /* 25506 */:
            case ID_PROJECTION_PRIVATE /* 30322 */:
                return 4;
            case ID_SAMPLING_FREQUENCY /* 181 */:
            case ID_DURATION /* 17545 */:
            case ID_PRIMARY_R_CHROMATICITY_X /* 21969 */:
            case ID_PRIMARY_R_CHROMATICITY_Y /* 21970 */:
            case ID_PRIMARY_G_CHROMATICITY_X /* 21971 */:
            case ID_PRIMARY_G_CHROMATICITY_Y /* 21972 */:
            case ID_PRIMARY_B_CHROMATICITY_X /* 21973 */:
            case ID_PRIMARY_B_CHROMATICITY_Y /* 21974 */:
            case ID_WHITE_POINT_CHROMATICITY_X /* 21975 */:
            case ID_WHITE_POINT_CHROMATICITY_Y /* 21976 */:
            case ID_LUMNINANCE_MAX /* 21977 */:
            case ID_LUMNINANCE_MIN /* 21978 */:
                return 5;
            default:
                return 0;
        }
    }

    @Override // com.google.android.exoplayer2.extractor.Extractor
    public void init(ExtractorOutput extractorOutput) {
        this.extractorOutput = extractorOutput;
    }

    public void integerElement(int i9, long j9) throws ParserException {
        if (i9 == ID_CONTENT_ENCODING_ORDER) {
            if (j9 == 0) {
                return;
            }
            throw new ParserException("ContentEncodingOrder " + j9 + " not supported");
        }
        if (i9 == ID_CONTENT_ENCODING_SCOPE) {
            if (j9 == 1) {
                return;
            }
            throw new ParserException("ContentEncodingScope " + j9 + " not supported");
        }
        switch (i9) {
            case ID_TRACK_TYPE /* 131 */:
                this.currentTrack.type = (int) j9;
                return;
            case ID_FLAG_DEFAULT /* 136 */:
                this.currentTrack.flagForced = j9 == 1;
                return;
            case ID_BLOCK_DURATION /* 155 */:
                this.blockDurationUs = scaleTimecodeToUs(j9);
                return;
            case ID_CHANNELS /* 159 */:
                this.currentTrack.channelCount = (int) j9;
                return;
            case ID_PIXEL_WIDTH /* 176 */:
                this.currentTrack.width = (int) j9;
                return;
            case ID_CUE_TIME /* 179 */:
                this.cueTimesUs.add(scaleTimecodeToUs(j9));
                return;
            case ID_PIXEL_HEIGHT /* 186 */:
                this.currentTrack.height = (int) j9;
                return;
            case ID_TRACK_NUMBER /* 215 */:
                this.currentTrack.number = (int) j9;
                return;
            case ID_TIME_CODE /* 231 */:
                this.clusterTimecodeUs = scaleTimecodeToUs(j9);
                return;
            case ID_CUE_CLUSTER_POSITION /* 241 */:
                if (this.seenClusterPositionForCurrentCuePoint) {
                    return;
                }
                this.cueClusterPositions.add(j9);
                this.seenClusterPositionForCurrentCuePoint = true;
                return;
            case ID_REFERENCE_BLOCK /* 251 */:
                this.sampleSeenReferenceBlock = true;
                return;
            case ID_CONTENT_COMPRESSION_ALGORITHM /* 16980 */:
                if (j9 == 3) {
                    return;
                }
                throw new ParserException("ContentCompAlgo " + j9 + " not supported");
            case ID_DOC_TYPE_READ_VERSION /* 17029 */:
                if (j9 < 1 || j9 > 2) {
                    throw new ParserException("DocTypeReadVersion " + j9 + " not supported");
                }
                return;
            case ID_EBML_READ_VERSION /* 17143 */:
                if (j9 == 1) {
                    return;
                }
                throw new ParserException("EBMLReadVersion " + j9 + " not supported");
            case ID_CONTENT_ENCRYPTION_ALGORITHM /* 18401 */:
                if (j9 == 5) {
                    return;
                }
                throw new ParserException("ContentEncAlgo " + j9 + " not supported");
            case ID_CONTENT_ENCRYPTION_AES_SETTINGS_CIPHER_MODE /* 18408 */:
                if (j9 == 1) {
                    return;
                }
                throw new ParserException("AESSettingsCipherMode " + j9 + " not supported");
            case ID_SEEK_POSITION /* 21420 */:
                this.seekEntryPosition = j9 + this.segmentContentPosition;
                return;
            case ID_STEREO_MODE /* 21432 */:
                int i10 = (int) j9;
                if (i10 == 0) {
                    this.currentTrack.stereoMode = 0;
                    return;
                }
                if (i10 == 1) {
                    this.currentTrack.stereoMode = 2;
                    return;
                } else if (i10 == 3) {
                    this.currentTrack.stereoMode = 1;
                    return;
                } else {
                    if (i10 != 15) {
                        return;
                    }
                    this.currentTrack.stereoMode = 3;
                    return;
                }
            case ID_DISPLAY_WIDTH /* 21680 */:
                this.currentTrack.displayWidth = (int) j9;
                return;
            case ID_DISPLAY_UNIT /* 21682 */:
                this.currentTrack.displayUnit = (int) j9;
                return;
            case ID_DISPLAY_HEIGHT /* 21690 */:
                this.currentTrack.displayHeight = (int) j9;
                return;
            case ID_FLAG_FORCED /* 21930 */:
                this.currentTrack.flagDefault = j9 == 1;
                return;
            case ID_CODEC_DELAY /* 22186 */:
                this.currentTrack.codecDelayNs = j9;
                return;
            case ID_SEEK_PRE_ROLL /* 22203 */:
                this.currentTrack.seekPreRollNs = j9;
                return;
            case ID_AUDIO_BIT_DEPTH /* 25188 */:
                this.currentTrack.audioBitDepth = (int) j9;
                return;
            case ID_DEFAULT_DURATION /* 2352003 */:
                this.currentTrack.defaultSampleDurationNs = (int) j9;
                return;
            case ID_TIMECODE_SCALE /* 2807729 */:
                this.timecodeScale = j9;
                return;
            default:
                switch (i9) {
                    case ID_COLOUR_RANGE /* 21945 */:
                        int i11 = (int) j9;
                        if (i11 == 1) {
                            this.currentTrack.colorRange = 2;
                            return;
                        } else {
                            if (i11 != 2) {
                                return;
                            }
                            this.currentTrack.colorRange = 1;
                            return;
                        }
                    case ID_COLOUR_TRANSFER /* 21946 */:
                        int i12 = (int) j9;
                        if (i12 != 1) {
                            if (i12 == 16) {
                                this.currentTrack.colorTransfer = 6;
                                return;
                            } else if (i12 == 18) {
                                this.currentTrack.colorTransfer = 7;
                                return;
                            } else if (i12 != 6 && i12 != 7) {
                                return;
                            }
                        }
                        this.currentTrack.colorTransfer = 3;
                        return;
                    case ID_COLOUR_PRIMARIES /* 21947 */:
                        Track track = this.currentTrack;
                        track.hasColorInfo = true;
                        int i13 = (int) j9;
                        if (i13 == 1) {
                            track.colorSpace = 1;
                            return;
                        }
                        if (i13 == 9) {
                            track.colorSpace = 6;
                            return;
                        } else {
                            if (i13 == 4 || i13 == 5 || i13 == 6 || i13 == 7) {
                                track.colorSpace = 2;
                                return;
                            }
                            return;
                        }
                    case ID_MAX_CLL /* 21948 */:
                        this.currentTrack.maxContentLuminance = (int) j9;
                        return;
                    case ID_MAX_FALL /* 21949 */:
                        this.currentTrack.maxFrameAverageLuminance = (int) j9;
                        return;
                    default:
                        return;
                }
        }
    }

    public boolean isLevel1Element(int i9) {
        return i9 == 357149030 || i9 == ID_CLUSTER || i9 == ID_CUES || i9 == ID_TRACKS;
    }

    @Override // com.google.android.exoplayer2.extractor.Extractor
    public int read(ExtractorInput extractorInput, PositionHolder positionHolder) {
        this.sampleRead = false;
        boolean z8 = true;
        while (z8 && !this.sampleRead) {
            z8 = this.reader.read(extractorInput);
            if (z8 && maybeSeekForCues(positionHolder, extractorInput.getPosition())) {
                return 1;
            }
        }
        if (z8) {
            return 0;
        }
        for (int i9 = 0; i9 < this.tracks.size(); i9++) {
            this.tracks.valueAt(i9).outputPendingSampleMetadata();
        }
        return -1;
    }

    @Override // com.google.android.exoplayer2.extractor.Extractor
    public void release() {
    }

    @Override // com.google.android.exoplayer2.extractor.Extractor
    public void seek(long j9, long j10) {
        this.clusterTimecodeUs = C3322C.TIME_UNSET;
        this.blockState = 0;
        this.reader.reset();
        this.varintReader.reset();
        resetSample();
        for (int i9 = 0; i9 < this.tracks.size(); i9++) {
            this.tracks.valueAt(i9).reset();
        }
    }

    @Override // com.google.android.exoplayer2.extractor.Extractor
    public boolean sniff(ExtractorInput extractorInput) {
        return new Sniffer().sniff(extractorInput);
    }

    public void startMasterElement(int i9, long j9, long j10) throws ParserException {
        if (i9 == ID_BLOCK_GROUP) {
            this.sampleSeenReferenceBlock = false;
            return;
        }
        if (i9 == ID_TRACK_ENTRY) {
            this.currentTrack = new Track();
            return;
        }
        if (i9 == ID_CUE_POINT) {
            this.seenClusterPositionForCurrentCuePoint = false;
            return;
        }
        if (i9 == ID_SEEK) {
            this.seekEntryId = -1;
            this.seekEntryPosition = -1L;
            return;
        }
        if (i9 == ID_CONTENT_ENCRYPTION) {
            this.currentTrack.hasContentEncryption = true;
            return;
        }
        if (i9 == ID_MASTERING_METADATA) {
            this.currentTrack.hasColorInfo = true;
            return;
        }
        if (i9 == ID_SEGMENT) {
            long j11 = this.segmentContentPosition;
            if (j11 != -1 && j11 != j9) {
                throw new ParserException("Multiple Segment elements not supported");
            }
            this.segmentContentPosition = j9;
            this.segmentContentSize = j10;
            return;
        }
        if (i9 == ID_CUES) {
            this.cueTimesUs = new LongArray();
            this.cueClusterPositions = new LongArray();
        } else if (i9 == ID_CLUSTER && !this.sentSeekMap) {
            if (this.seekForCuesEnabled && this.cuesContentPosition != -1) {
                this.seekForCues = true;
            } else {
                this.extractorOutput.seekMap(new SeekMap.Unseekable(this.durationUs));
                this.sentSeekMap = true;
            }
        }
    }

    public void stringElement(int i9, String str) throws ParserException {
        if (i9 == 134) {
            this.currentTrack.codecId = str;
            return;
        }
        if (i9 != 17026) {
            if (i9 != ID_LANGUAGE) {
                return;
            }
            this.currentTrack.language = str;
        } else {
            if (DOC_TYPE_WEBM.equals(str) || DOC_TYPE_MATROSKA.equals(str)) {
                return;
            }
            throw new ParserException("DocType " + str + " not supported");
        }
    }

    public MatroskaExtractor(int i9) {
        this(new DefaultEbmlReader(), i9);
    }

    public MatroskaExtractor(EbmlReader ebmlReader, int i9) {
        this.segmentContentPosition = -1L;
        this.timecodeScale = C3322C.TIME_UNSET;
        this.durationTimecode = C3322C.TIME_UNSET;
        this.durationUs = C3322C.TIME_UNSET;
        this.cuesContentPosition = -1L;
        this.seekPositionAfterBuildingCues = -1L;
        this.clusterTimecodeUs = C3322C.TIME_UNSET;
        this.reader = ebmlReader;
        ebmlReader.init(new InnerEbmlReaderOutput());
        this.seekForCuesEnabled = (i9 & 1) == 0;
        this.varintReader = new VarintReader();
        this.tracks = new SparseArray<>();
        this.scratch = new ParsableByteArray(4);
        this.vorbisNumPageSamples = new ParsableByteArray(ByteBuffer.allocate(4).putInt(-1).array());
        this.seekEntryIdBytes = new ParsableByteArray(4);
        this.nalStartCode = new ParsableByteArray(NalUnitUtil.NAL_START_CODE);
        this.nalLength = new ParsableByteArray(4);
        this.sampleStrippedBytes = new ParsableByteArray();
        this.subtitleSample = new ParsableByteArray();
        this.encryptionInitializationVector = new ParsableByteArray(8);
        this.encryptionSubsampleData = new ParsableByteArray();
    }
}
