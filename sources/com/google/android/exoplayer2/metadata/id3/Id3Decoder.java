package com.google.android.exoplayer2.metadata.id3;

import android.util.Log;
import com.google.android.exoplayer2.metadata.Metadata;
import com.google.android.exoplayer2.metadata.MetadataDecoder;
import com.google.android.exoplayer2.metadata.MetadataInputBuffer;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.Util;
import com.google.common.primitives.UnsignedBytes;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import org.apache.commons.lang3.CharEncoding;

/* loaded from: classes.dex */
public final class Id3Decoder implements MetadataDecoder {
    private static final int FRAME_FLAG_V3_HAS_GROUP_IDENTIFIER = 32;
    private static final int FRAME_FLAG_V3_IS_COMPRESSED = 128;
    private static final int FRAME_FLAG_V3_IS_ENCRYPTED = 64;
    private static final int FRAME_FLAG_V4_HAS_DATA_LENGTH = 1;
    private static final int FRAME_FLAG_V4_HAS_GROUP_IDENTIFIER = 64;
    private static final int FRAME_FLAG_V4_IS_COMPRESSED = 8;
    private static final int FRAME_FLAG_V4_IS_ENCRYPTED = 4;
    private static final int FRAME_FLAG_V4_IS_UNSYNCHRONIZED = 2;
    public static final int ID3_HEADER_LENGTH = 10;
    public static final int ID3_TAG = Util.getIntegerCodeForString("ID3");
    private static final int ID3_TEXT_ENCODING_ISO_8859_1 = 0;
    private static final int ID3_TEXT_ENCODING_UTF_16 = 1;
    private static final int ID3_TEXT_ENCODING_UTF_16BE = 2;
    private static final int ID3_TEXT_ENCODING_UTF_8 = 3;
    private static final String TAG = "Id3Decoder";
    private final FramePredicate framePredicate;

    public interface FramePredicate {
        boolean evaluate(int i9, int i10, int i11, int i12, int i13);
    }

    public static final class Id3Header {
        private final int framesSize;
        private final boolean isUnsynchronized;
        private final int majorVersion;

        public Id3Header(int i9, boolean z8, int i10) {
            this.majorVersion = i9;
            this.isUnsynchronized = z8;
            this.framesSize = i10;
        }
    }

    public Id3Decoder() {
        this(null);
    }

    private static byte[] copyOfRangeIfValid(byte[] bArr, int i9, int i10) {
        return i10 <= i9 ? new byte[0] : Arrays.copyOfRange(bArr, i9, i10);
    }

    private static ApicFrame decodeApicFrame(ParsableByteArray parsableByteArray, int i9, int i10) {
        int iIndexOfZeroByte;
        String str;
        int unsignedByte = parsableByteArray.readUnsignedByte();
        String charsetName = getCharsetName(unsignedByte);
        int i11 = i9 - 1;
        byte[] bArr = new byte[i11];
        parsableByteArray.readBytes(bArr, 0, i11);
        if (i10 == 2) {
            str = "image/" + Util.toLowerInvariant(new String(bArr, 0, 3, CharEncoding.ISO_8859_1));
            if (str.equals("image/jpg")) {
                str = "image/jpeg";
            }
            iIndexOfZeroByte = 2;
        } else {
            iIndexOfZeroByte = indexOfZeroByte(bArr, 0);
            String lowerInvariant = Util.toLowerInvariant(new String(bArr, 0, iIndexOfZeroByte, CharEncoding.ISO_8859_1));
            if (lowerInvariant.indexOf(47) == -1) {
                str = "image/" + lowerInvariant;
            } else {
                str = lowerInvariant;
            }
        }
        int i12 = bArr[iIndexOfZeroByte + 1] & UnsignedBytes.MAX_VALUE;
        int i13 = iIndexOfZeroByte + 2;
        int iIndexOfEos = indexOfEos(bArr, i13, unsignedByte);
        return new ApicFrame(str, new String(bArr, i13, iIndexOfEos - i13, charsetName), i12, copyOfRangeIfValid(bArr, iIndexOfEos + delimiterLength(unsignedByte), i11));
    }

    private static BinaryFrame decodeBinaryFrame(ParsableByteArray parsableByteArray, int i9, String str) {
        byte[] bArr = new byte[i9];
        parsableByteArray.readBytes(bArr, 0, i9);
        return new BinaryFrame(str, bArr);
    }

    private static ChapterFrame decodeChapterFrame(ParsableByteArray parsableByteArray, int i9, int i10, boolean z8, int i11, FramePredicate framePredicate) {
        int position = parsableByteArray.getPosition();
        int iIndexOfZeroByte = indexOfZeroByte(parsableByteArray.data, position);
        String str = new String(parsableByteArray.data, position, iIndexOfZeroByte - position, CharEncoding.ISO_8859_1);
        parsableByteArray.setPosition(iIndexOfZeroByte + 1);
        int i12 = parsableByteArray.readInt();
        int i13 = parsableByteArray.readInt();
        long unsignedInt = parsableByteArray.readUnsignedInt();
        long j9 = unsignedInt == 4294967295L ? -1L : unsignedInt;
        long unsignedInt2 = parsableByteArray.readUnsignedInt();
        long j10 = unsignedInt2 == 4294967295L ? -1L : unsignedInt2;
        ArrayList arrayList = new ArrayList();
        int i14 = position + i9;
        while (parsableByteArray.getPosition() < i14) {
            Id3Frame id3FrameDecodeFrame = decodeFrame(i10, parsableByteArray, z8, i11, framePredicate);
            if (id3FrameDecodeFrame != null) {
                arrayList.add(id3FrameDecodeFrame);
            }
        }
        Id3Frame[] id3FrameArr = new Id3Frame[arrayList.size()];
        arrayList.toArray(id3FrameArr);
        return new ChapterFrame(str, i12, i13, j9, j10, id3FrameArr);
    }

    private static ChapterTocFrame decodeChapterTOCFrame(ParsableByteArray parsableByteArray, int i9, int i10, boolean z8, int i11, FramePredicate framePredicate) {
        int position = parsableByteArray.getPosition();
        int iIndexOfZeroByte = indexOfZeroByte(parsableByteArray.data, position);
        String str = new String(parsableByteArray.data, position, iIndexOfZeroByte - position, CharEncoding.ISO_8859_1);
        parsableByteArray.setPosition(iIndexOfZeroByte + 1);
        int unsignedByte = parsableByteArray.readUnsignedByte();
        boolean z9 = (unsignedByte & 2) != 0;
        boolean z10 = (unsignedByte & 1) != 0;
        int unsignedByte2 = parsableByteArray.readUnsignedByte();
        String[] strArr = new String[unsignedByte2];
        for (int i12 = 0; i12 < unsignedByte2; i12++) {
            int position2 = parsableByteArray.getPosition();
            int iIndexOfZeroByte2 = indexOfZeroByte(parsableByteArray.data, position2);
            strArr[i12] = new String(parsableByteArray.data, position2, iIndexOfZeroByte2 - position2, CharEncoding.ISO_8859_1);
            parsableByteArray.setPosition(iIndexOfZeroByte2 + 1);
        }
        ArrayList arrayList = new ArrayList();
        int i13 = position + i9;
        while (parsableByteArray.getPosition() < i13) {
            Id3Frame id3FrameDecodeFrame = decodeFrame(i10, parsableByteArray, z8, i11, framePredicate);
            if (id3FrameDecodeFrame != null) {
                arrayList.add(id3FrameDecodeFrame);
            }
        }
        Id3Frame[] id3FrameArr = new Id3Frame[arrayList.size()];
        arrayList.toArray(id3FrameArr);
        return new ChapterTocFrame(str, z9, z10, strArr, id3FrameArr);
    }

    private static CommentFrame decodeCommentFrame(ParsableByteArray parsableByteArray, int i9) {
        if (i9 < 4) {
            return null;
        }
        int unsignedByte = parsableByteArray.readUnsignedByte();
        String charsetName = getCharsetName(unsignedByte);
        byte[] bArr = new byte[3];
        parsableByteArray.readBytes(bArr, 0, 3);
        String str = new String(bArr, 0, 3);
        int i10 = i9 - 4;
        byte[] bArr2 = new byte[i10];
        parsableByteArray.readBytes(bArr2, 0, i10);
        int iIndexOfEos = indexOfEos(bArr2, 0, unsignedByte);
        String str2 = new String(bArr2, 0, iIndexOfEos, charsetName);
        int iDelimiterLength = iIndexOfEos + delimiterLength(unsignedByte);
        return new CommentFrame(str, str2, decodeStringIfValid(bArr2, iDelimiterLength, indexOfEos(bArr2, iDelimiterLength, unsignedByte), charsetName));
    }

    /* JADX WARN: Removed duplicated region for block: B:140:0x01a8  */
    /* JADX WARN: Removed duplicated region for block: B:146:0x01c2  */
    /* JADX WARN: Removed duplicated region for block: B:151:0x01da A[Catch: all -> 0x012e, UnsupportedEncodingException -> 0x0208, TryCatch #0 {UnsupportedEncodingException -> 0x0208, blocks: (B:91:0x011c, B:153:0x01e4, B:93:0x0124, B:102:0x013d, B:104:0x0145, B:112:0x015f, B:121:0x0177, B:132:0x0192, B:139:0x01a3, B:145:0x01b2, B:150:0x01ca, B:151:0x01da), top: B:163:0x0112, outer: #1 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static Id3Frame decodeFrame(int i9, ParsableByteArray parsableByteArray, boolean z8, int i10, FramePredicate framePredicate) {
        int unsignedIntToInt;
        String str;
        int i11;
        int i12;
        boolean z9;
        boolean z10;
        boolean z11;
        boolean z12;
        boolean z13;
        Id3Frame id3FrameDecodeCommentFrame;
        int unsignedByte = parsableByteArray.readUnsignedByte();
        int unsignedByte2 = parsableByteArray.readUnsignedByte();
        int unsignedByte3 = parsableByteArray.readUnsignedByte();
        int unsignedByte4 = i9 >= 3 ? parsableByteArray.readUnsignedByte() : 0;
        if (i9 == 4) {
            unsignedIntToInt = parsableByteArray.readUnsignedIntToInt();
            if (!z8) {
                unsignedIntToInt = (((unsignedIntToInt >> 24) & 255) << 21) | (unsignedIntToInt & 255) | (((unsignedIntToInt >> 8) & 255) << 7) | (((unsignedIntToInt >> 16) & 255) << 14);
            }
        } else {
            unsignedIntToInt = i9 == 3 ? parsableByteArray.readUnsignedIntToInt() : parsableByteArray.readUnsignedInt24();
        }
        int iRemoveUnsynchronization = unsignedIntToInt;
        int unsignedShort = i9 >= 3 ? parsableByteArray.readUnsignedShort() : 0;
        if (unsignedByte == 0 && unsignedByte2 == 0 && unsignedByte3 == 0 && unsignedByte4 == 0 && iRemoveUnsynchronization == 0 && unsignedShort == 0) {
            parsableByteArray.setPosition(parsableByteArray.limit());
            return null;
        }
        int position = parsableByteArray.getPosition() + iRemoveUnsynchronization;
        if (position > parsableByteArray.limit()) {
            Log.w(TAG, "Frame size exceeds remaining tag data");
            parsableByteArray.setPosition(parsableByteArray.limit());
            return null;
        }
        if (framePredicate != null) {
            str = TAG;
            i11 = position;
            i12 = unsignedShort;
            if (!framePredicate.evaluate(i9, unsignedByte, unsignedByte2, unsignedByte3, unsignedByte4)) {
                parsableByteArray.setPosition(i11);
                return null;
            }
        } else {
            str = TAG;
            i11 = position;
            i12 = unsignedShort;
        }
        if (i9 == 3) {
            int i13 = i12;
            z10 = (i13 & FRAME_FLAG_V3_IS_COMPRESSED) != 0;
            z11 = (i13 & 64) != 0;
            z9 = (i13 & 32) != 0;
            z13 = z10;
            z12 = false;
        } else {
            int i14 = i12;
            if (i9 == 4) {
                boolean z14 = (i14 & 64) != 0;
                boolean z15 = (i14 & 8) != 0;
                boolean z16 = (i14 & 4) != 0;
                z12 = (i14 & 2) != 0;
                boolean z17 = (i14 & 1) != 0;
                z9 = z14;
                z10 = z17;
                z13 = z15;
                z11 = z16;
            } else {
                z9 = false;
                z10 = false;
                z11 = false;
                z12 = false;
                z13 = false;
            }
        }
        if (z13 || z11) {
            Log.w(str, "Skipping unsupported compressed or encrypted frame");
            parsableByteArray.setPosition(i11);
            return null;
        }
        if (z9) {
            iRemoveUnsynchronization--;
            parsableByteArray.skipBytes(1);
        }
        if (z10) {
            iRemoveUnsynchronization -= 4;
            parsableByteArray.skipBytes(4);
        }
        if (z12) {
            iRemoveUnsynchronization = removeUnsynchronization(parsableByteArray, iRemoveUnsynchronization);
        }
        try {
            try {
                if (unsignedByte == 84 && unsignedByte2 == 88 && unsignedByte3 == 88 && (i9 == 2 || unsignedByte4 == 88)) {
                    id3FrameDecodeCommentFrame = decodeTxxxFrame(parsableByteArray, iRemoveUnsynchronization);
                } else if (unsignedByte == 84) {
                    id3FrameDecodeCommentFrame = decodeTextInformationFrame(parsableByteArray, iRemoveUnsynchronization, getFrameId(i9, unsignedByte, unsignedByte2, unsignedByte3, unsignedByte4));
                } else if (unsignedByte == 87 && unsignedByte2 == 88 && unsignedByte3 == 88 && (i9 == 2 || unsignedByte4 == 88)) {
                    id3FrameDecodeCommentFrame = decodeWxxxFrame(parsableByteArray, iRemoveUnsynchronization);
                } else if (unsignedByte == 87) {
                    id3FrameDecodeCommentFrame = decodeUrlLinkFrame(parsableByteArray, iRemoveUnsynchronization, getFrameId(i9, unsignedByte, unsignedByte2, unsignedByte3, unsignedByte4));
                } else if (unsignedByte == 80 && unsignedByte2 == 82 && unsignedByte3 == 73 && unsignedByte4 == 86) {
                    id3FrameDecodeCommentFrame = decodePrivFrame(parsableByteArray, iRemoveUnsynchronization);
                } else if (unsignedByte == 71 && unsignedByte2 == 69 && unsignedByte3 == 79 && (unsignedByte4 == 66 || i9 == 2)) {
                    id3FrameDecodeCommentFrame = decodeGeobFrame(parsableByteArray, iRemoveUnsynchronization);
                } else if (i9 == 2) {
                    if (unsignedByte == 80 && unsignedByte2 == 73 && unsignedByte3 == 67) {
                        id3FrameDecodeCommentFrame = decodeApicFrame(parsableByteArray, iRemoveUnsynchronization, i9);
                    }
                    id3FrameDecodeCommentFrame = (unsignedByte != 67 && unsignedByte2 == 79 && unsignedByte3 == 77 && (unsignedByte4 == 77 || i9 == 2)) ? decodeCommentFrame(parsableByteArray, iRemoveUnsynchronization) : (unsignedByte != 67 && unsignedByte2 == 72 && unsignedByte3 == 65 && unsignedByte4 == 80) ? decodeChapterFrame(parsableByteArray, iRemoveUnsynchronization, i9, z8, i10, framePredicate) : (unsignedByte != 67 && unsignedByte2 == 84 && unsignedByte3 == 79 && unsignedByte4 == 67) ? decodeChapterTOCFrame(parsableByteArray, iRemoveUnsynchronization, i9, z8, i10, framePredicate) : decodeBinaryFrame(parsableByteArray, iRemoveUnsynchronization, getFrameId(i9, unsignedByte, unsignedByte2, unsignedByte3, unsignedByte4));
                } else if (unsignedByte == 65 && unsignedByte2 == 80 && unsignedByte3 == 73 && unsignedByte4 == 67) {
                    id3FrameDecodeCommentFrame = decodeApicFrame(parsableByteArray, iRemoveUnsynchronization, i9);
                } else if (unsignedByte != 67) {
                }
                if (id3FrameDecodeCommentFrame == null) {
                    Log.w(str, "Failed to decode frame: id=" + getFrameId(i9, unsignedByte, unsignedByte2, unsignedByte3, unsignedByte4) + ", frameSize=" + iRemoveUnsynchronization);
                }
                parsableByteArray.setPosition(i11);
                return id3FrameDecodeCommentFrame;
            } catch (UnsupportedEncodingException unused) {
                Log.w(str, "Unsupported character encoding");
                parsableByteArray.setPosition(i11);
                return null;
            }
        } catch (Throwable th) {
            parsableByteArray.setPosition(i11);
            throw th;
        }
    }

    private static GeobFrame decodeGeobFrame(ParsableByteArray parsableByteArray, int i9) {
        int unsignedByte = parsableByteArray.readUnsignedByte();
        String charsetName = getCharsetName(unsignedByte);
        int i10 = i9 - 1;
        byte[] bArr = new byte[i10];
        parsableByteArray.readBytes(bArr, 0, i10);
        int iIndexOfZeroByte = indexOfZeroByte(bArr, 0);
        String str = new String(bArr, 0, iIndexOfZeroByte, CharEncoding.ISO_8859_1);
        int i11 = iIndexOfZeroByte + 1;
        int iIndexOfEos = indexOfEos(bArr, i11, unsignedByte);
        String strDecodeStringIfValid = decodeStringIfValid(bArr, i11, iIndexOfEos, charsetName);
        int iDelimiterLength = iIndexOfEos + delimiterLength(unsignedByte);
        int iIndexOfEos2 = indexOfEos(bArr, iDelimiterLength, unsignedByte);
        return new GeobFrame(str, strDecodeStringIfValid, decodeStringIfValid(bArr, iDelimiterLength, iIndexOfEos2, charsetName), copyOfRangeIfValid(bArr, iIndexOfEos2 + delimiterLength(unsignedByte), i10));
    }

    private static Id3Header decodeHeader(ParsableByteArray parsableByteArray) {
        if (parsableByteArray.bytesLeft() < 10) {
            Log.w(TAG, "Data too short to be an ID3 tag");
            return null;
        }
        int unsignedInt24 = parsableByteArray.readUnsignedInt24();
        if (unsignedInt24 != ID3_TAG) {
            Log.w(TAG, "Unexpected first three bytes of ID3 tag header: " + unsignedInt24);
            return null;
        }
        int unsignedByte = parsableByteArray.readUnsignedByte();
        parsableByteArray.skipBytes(1);
        int unsignedByte2 = parsableByteArray.readUnsignedByte();
        int synchSafeInt = parsableByteArray.readSynchSafeInt();
        if (unsignedByte == 2) {
            if ((unsignedByte2 & 64) != 0) {
                Log.w(TAG, "Skipped ID3 tag with majorVersion=2 and undefined compression scheme");
                return null;
            }
        } else if (unsignedByte == 3) {
            if ((unsignedByte2 & 64) != 0) {
                int i9 = parsableByteArray.readInt();
                parsableByteArray.skipBytes(i9);
                synchSafeInt -= i9 + 4;
            }
        } else {
            if (unsignedByte != 4) {
                Log.w(TAG, "Skipped ID3 tag with unsupported majorVersion=" + unsignedByte);
                return null;
            }
            if ((unsignedByte2 & 64) != 0) {
                int synchSafeInt2 = parsableByteArray.readSynchSafeInt();
                parsableByteArray.skipBytes(synchSafeInt2 - 4);
                synchSafeInt -= synchSafeInt2;
            }
            if ((unsignedByte2 & 16) != 0) {
                synchSafeInt -= 10;
            }
        }
        return new Id3Header(unsignedByte, unsignedByte < 4 && (unsignedByte2 & FRAME_FLAG_V3_IS_COMPRESSED) != 0, synchSafeInt);
    }

    private static PrivFrame decodePrivFrame(ParsableByteArray parsableByteArray, int i9) {
        byte[] bArr = new byte[i9];
        parsableByteArray.readBytes(bArr, 0, i9);
        int iIndexOfZeroByte = indexOfZeroByte(bArr, 0);
        return new PrivFrame(new String(bArr, 0, iIndexOfZeroByte, CharEncoding.ISO_8859_1), copyOfRangeIfValid(bArr, iIndexOfZeroByte + 1, i9));
    }

    private static String decodeStringIfValid(byte[] bArr, int i9, int i10, String str) {
        return (i10 <= i9 || i10 > bArr.length) ? "" : new String(bArr, i9, i10 - i9, str);
    }

    private static TextInformationFrame decodeTextInformationFrame(ParsableByteArray parsableByteArray, int i9, String str) {
        if (i9 < 1) {
            return null;
        }
        int unsignedByte = parsableByteArray.readUnsignedByte();
        String charsetName = getCharsetName(unsignedByte);
        int i10 = i9 - 1;
        byte[] bArr = new byte[i10];
        parsableByteArray.readBytes(bArr, 0, i10);
        return new TextInformationFrame(str, null, new String(bArr, 0, indexOfEos(bArr, 0, unsignedByte), charsetName));
    }

    private static TextInformationFrame decodeTxxxFrame(ParsableByteArray parsableByteArray, int i9) {
        if (i9 < 1) {
            return null;
        }
        int unsignedByte = parsableByteArray.readUnsignedByte();
        String charsetName = getCharsetName(unsignedByte);
        int i10 = i9 - 1;
        byte[] bArr = new byte[i10];
        parsableByteArray.readBytes(bArr, 0, i10);
        int iIndexOfEos = indexOfEos(bArr, 0, unsignedByte);
        String str = new String(bArr, 0, iIndexOfEos, charsetName);
        int iDelimiterLength = iIndexOfEos + delimiterLength(unsignedByte);
        return new TextInformationFrame("TXXX", str, decodeStringIfValid(bArr, iDelimiterLength, indexOfEos(bArr, iDelimiterLength, unsignedByte), charsetName));
    }

    private static UrlLinkFrame decodeUrlLinkFrame(ParsableByteArray parsableByteArray, int i9, String str) {
        byte[] bArr = new byte[i9];
        parsableByteArray.readBytes(bArr, 0, i9);
        return new UrlLinkFrame(str, null, new String(bArr, 0, indexOfZeroByte(bArr, 0), CharEncoding.ISO_8859_1));
    }

    private static UrlLinkFrame decodeWxxxFrame(ParsableByteArray parsableByteArray, int i9) {
        if (i9 < 1) {
            return null;
        }
        int unsignedByte = parsableByteArray.readUnsignedByte();
        String charsetName = getCharsetName(unsignedByte);
        int i10 = i9 - 1;
        byte[] bArr = new byte[i10];
        parsableByteArray.readBytes(bArr, 0, i10);
        int iIndexOfEos = indexOfEos(bArr, 0, unsignedByte);
        String str = new String(bArr, 0, iIndexOfEos, charsetName);
        int iDelimiterLength = iIndexOfEos + delimiterLength(unsignedByte);
        return new UrlLinkFrame("WXXX", str, decodeStringIfValid(bArr, iDelimiterLength, indexOfZeroByte(bArr, iDelimiterLength), CharEncoding.ISO_8859_1));
    }

    private static int delimiterLength(int i9) {
        return (i9 == 0 || i9 == 3) ? 1 : 2;
    }

    private static String getCharsetName(int i9) {
        return i9 != 1 ? i9 != 2 ? i9 != 3 ? CharEncoding.ISO_8859_1 : "UTF-8" : CharEncoding.UTF_16BE : "UTF-16";
    }

    private static String getFrameId(int i9, int i10, int i11, int i12, int i13) {
        return i9 == 2 ? String.format(Locale.US, "%c%c%c", Integer.valueOf(i10), Integer.valueOf(i11), Integer.valueOf(i12)) : String.format(Locale.US, "%c%c%c%c", Integer.valueOf(i10), Integer.valueOf(i11), Integer.valueOf(i12), Integer.valueOf(i13));
    }

    private static int indexOfEos(byte[] bArr, int i9, int i10) {
        int iIndexOfZeroByte = indexOfZeroByte(bArr, i9);
        if (i10 == 0 || i10 == 3) {
            return iIndexOfZeroByte;
        }
        while (iIndexOfZeroByte < bArr.length - 1) {
            if (iIndexOfZeroByte % 2 == 0 && bArr[iIndexOfZeroByte + 1] == 0) {
                return iIndexOfZeroByte;
            }
            iIndexOfZeroByte = indexOfZeroByte(bArr, iIndexOfZeroByte + 1);
        }
        return bArr.length;
    }

    private static int indexOfZeroByte(byte[] bArr, int i9) {
        while (i9 < bArr.length) {
            if (bArr[i9] == 0) {
                return i9;
            }
            i9++;
        }
        return bArr.length;
    }

    private static int removeUnsynchronization(ParsableByteArray parsableByteArray, int i9) {
        byte[] bArr = parsableByteArray.data;
        int position = parsableByteArray.getPosition();
        while (true) {
            int i10 = position + 1;
            if (i10 >= i9) {
                return i9;
            }
            if ((bArr[position] & UnsignedBytes.MAX_VALUE) == 255 && bArr[i10] == 0) {
                System.arraycopy(bArr, position + 2, bArr, i10, (i9 - position) - 2);
                i9--;
            }
            position = i10;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x0079 A[PHI: r3
      0x0079: PHI (r3v16 int) = (r3v5 int), (r3v19 int) binds: [B:40:0x0086, B:31:0x0076] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static boolean validateFrames(ParsableByteArray parsableByteArray, int i9, int i10, boolean z8) {
        int unsignedInt24;
        long unsignedInt242;
        int unsignedShort;
        int i11;
        int position = parsableByteArray.getPosition();
        while (true) {
            try {
                boolean z9 = true;
                if (parsableByteArray.bytesLeft() < i10) {
                    return true;
                }
                if (i9 >= 3) {
                    unsignedInt24 = parsableByteArray.readInt();
                    unsignedInt242 = parsableByteArray.readUnsignedInt();
                    unsignedShort = parsableByteArray.readUnsignedShort();
                } else {
                    unsignedInt24 = parsableByteArray.readUnsignedInt24();
                    unsignedInt242 = parsableByteArray.readUnsignedInt24();
                    unsignedShort = 0;
                }
                if (unsignedInt24 == 0 && unsignedInt242 == 0 && unsignedShort == 0) {
                    return true;
                }
                if (i9 == 4 && !z8) {
                    if ((8421504 & unsignedInt242) != 0) {
                        return false;
                    }
                    unsignedInt242 = (((unsignedInt242 >> 24) & 255) << 21) | (unsignedInt242 & 255) | (((unsignedInt242 >> 8) & 255) << 7) | (((unsignedInt242 >> 16) & 255) << 14);
                }
                if (i9 == 4) {
                    i11 = (unsignedShort & 64) != 0 ? 1 : 0;
                    if ((unsignedShort & 1) == 0) {
                        z9 = false;
                    }
                } else if (i9 == 3) {
                    i11 = (unsignedShort & 32) != 0 ? 1 : 0;
                    if ((unsignedShort & FRAME_FLAG_V3_IS_COMPRESSED) == 0) {
                    }
                } else {
                    i11 = 0;
                    z9 = false;
                }
                if (z9) {
                    i11 += 4;
                }
                if (unsignedInt242 < i11) {
                    return false;
                }
                if (parsableByteArray.bytesLeft() < unsignedInt242) {
                    return false;
                }
                parsableByteArray.skipBytes((int) unsignedInt242);
            } finally {
                parsableByteArray.setPosition(position);
            }
        }
    }

    @Override // com.google.android.exoplayer2.metadata.MetadataDecoder
    public Metadata decode(MetadataInputBuffer metadataInputBuffer) {
        ByteBuffer byteBuffer = metadataInputBuffer.data;
        return decode(byteBuffer.array(), byteBuffer.limit());
    }

    public Id3Decoder(FramePredicate framePredicate) {
        this.framePredicate = framePredicate;
    }

    public Metadata decode(byte[] bArr, int i9) {
        ArrayList arrayList = new ArrayList();
        ParsableByteArray parsableByteArray = new ParsableByteArray(bArr, i9);
        Id3Header id3HeaderDecodeHeader = decodeHeader(parsableByteArray);
        if (id3HeaderDecodeHeader == null) {
            return null;
        }
        int position = parsableByteArray.getPosition();
        int i10 = id3HeaderDecodeHeader.majorVersion == 2 ? 6 : 10;
        int iRemoveUnsynchronization = id3HeaderDecodeHeader.framesSize;
        if (id3HeaderDecodeHeader.isUnsynchronized) {
            iRemoveUnsynchronization = removeUnsynchronization(parsableByteArray, id3HeaderDecodeHeader.framesSize);
        }
        parsableByteArray.setLimit(position + iRemoveUnsynchronization);
        boolean z8 = false;
        if (!validateFrames(parsableByteArray, id3HeaderDecodeHeader.majorVersion, i10, false)) {
            if (id3HeaderDecodeHeader.majorVersion != 4 || !validateFrames(parsableByteArray, 4, i10, true)) {
                Log.w(TAG, "Failed to validate ID3 tag with majorVersion=" + id3HeaderDecodeHeader.majorVersion);
                return null;
            }
            z8 = true;
        }
        while (parsableByteArray.bytesLeft() >= i10) {
            Id3Frame id3FrameDecodeFrame = decodeFrame(id3HeaderDecodeHeader.majorVersion, parsableByteArray, z8, i10, this.framePredicate);
            if (id3FrameDecodeFrame != null) {
                arrayList.add(id3FrameDecodeFrame);
            }
        }
        return new Metadata(arrayList);
    }
}
