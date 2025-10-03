package com.google.protobuf;

import com.google.android.exoplayer2.extractor.p037ts.PsExtractor;
import com.google.common.base.Ascii;
import java.nio.ByteBuffer;

/* loaded from: classes2.dex */
final class Utf8 {
    private static final long ASCII_MASK_LONG = -9187201950435737472L;
    public static final int COMPLETE = 0;
    public static final int MALFORMED = -1;
    static final int MAX_BYTES_PER_CHAR = 3;
    private static final int UNSAFE_COUNT_ASCII_THRESHOLD = 16;
    private static final Processor processor;

    public static class DecodeUtil {
        private DecodeUtil() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static void handleFourBytes(byte b9, byte b10, byte b11, byte b12, char[] cArr, int i9) throws InvalidProtocolBufferException {
            if (isNotTrailingByte(b10) || (((b9 << Ascii.f15383FS) + (b10 + 112)) >> 30) != 0 || isNotTrailingByte(b11) || isNotTrailingByte(b12)) {
                throw InvalidProtocolBufferException.invalidUtf8();
            }
            int iTrailingByteValue = ((b9 & 7) << 18) | (trailingByteValue(b10) << 12) | (trailingByteValue(b11) << 6) | trailingByteValue(b12);
            cArr[i9] = highSurrogate(iTrailingByteValue);
            cArr[i9 + 1] = lowSurrogate(iTrailingByteValue);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static void handleOneByte(byte b9, char[] cArr, int i9) {
            cArr[i9] = (char) b9;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static void handleThreeBytes(byte b9, byte b10, byte b11, char[] cArr, int i9) throws InvalidProtocolBufferException {
            if (isNotTrailingByte(b10) || ((b9 == -32 && b10 < -96) || ((b9 == -19 && b10 >= -96) || isNotTrailingByte(b11)))) {
                throw InvalidProtocolBufferException.invalidUtf8();
            }
            cArr[i9] = (char) (((b9 & Ascii.f15389SI) << 12) | (trailingByteValue(b10) << 6) | trailingByteValue(b11));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static void handleTwoBytes(byte b9, byte b10, char[] cArr, int i9) throws InvalidProtocolBufferException {
            if (b9 < -62 || isNotTrailingByte(b10)) {
                throw InvalidProtocolBufferException.invalidUtf8();
            }
            cArr[i9] = (char) (((b9 & Ascii.f15392US) << 6) | trailingByteValue(b10));
        }

        private static char highSurrogate(int i9) {
            return (char) ((i9 >>> 10) + 55232);
        }

        private static boolean isNotTrailingByte(byte b9) {
            return b9 > -65;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static boolean isOneByte(byte b9) {
            return b9 >= 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static boolean isThreeBytes(byte b9) {
            return b9 < -16;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static boolean isTwoBytes(byte b9) {
            return b9 < -32;
        }

        private static char lowSurrogate(int i9) {
            return (char) ((i9 & 1023) + 56320);
        }

        private static int trailingByteValue(byte b9) {
            return b9 & 63;
        }
    }

    public static abstract class Processor {
        public final String decodeUtf8(ByteBuffer byteBuffer, int i9, int i10) throws InvalidProtocolBufferException {
            if (byteBuffer.hasArray()) {
                return decodeUtf8(byteBuffer.array(), byteBuffer.arrayOffset() + i9, i10);
            }
            return byteBuffer.isDirect() ? decodeUtf8Direct(byteBuffer, i9, i10) : decodeUtf8Default(byteBuffer, i9, i10);
        }

        public abstract String decodeUtf8(byte[] bArr, int i9, int i10);

        public final String decodeUtf8Default(ByteBuffer byteBuffer, int i9, int i10) throws InvalidProtocolBufferException {
            if ((i9 | i10 | ((byteBuffer.limit() - i9) - i10)) < 0) {
                throw new ArrayIndexOutOfBoundsException(String.format("buffer limit=%d, index=%d, limit=%d", Integer.valueOf(byteBuffer.limit()), Integer.valueOf(i9), Integer.valueOf(i10)));
            }
            int i11 = i9 + i10;
            char[] cArr = new char[i10];
            int i12 = 0;
            while (i9 < i11) {
                byte b9 = byteBuffer.get(i9);
                if (!DecodeUtil.isOneByte(b9)) {
                    break;
                }
                i9++;
                DecodeUtil.handleOneByte(b9, cArr, i12);
                i12++;
            }
            int i13 = i12;
            while (i9 < i11) {
                int i14 = i9 + 1;
                byte b10 = byteBuffer.get(i9);
                if (DecodeUtil.isOneByte(b10)) {
                    int i15 = i13 + 1;
                    DecodeUtil.handleOneByte(b10, cArr, i13);
                    while (i14 < i11) {
                        byte b11 = byteBuffer.get(i14);
                        if (!DecodeUtil.isOneByte(b11)) {
                            break;
                        }
                        i14++;
                        DecodeUtil.handleOneByte(b11, cArr, i15);
                        i15++;
                    }
                    i9 = i14;
                    i13 = i15;
                } else if (DecodeUtil.isTwoBytes(b10)) {
                    if (i14 >= i11) {
                        throw InvalidProtocolBufferException.invalidUtf8();
                    }
                    DecodeUtil.handleTwoBytes(b10, byteBuffer.get(i14), cArr, i13);
                    i9 = i14 + 1;
                    i13++;
                } else if (DecodeUtil.isThreeBytes(b10)) {
                    if (i14 >= i11 - 1) {
                        throw InvalidProtocolBufferException.invalidUtf8();
                    }
                    int i16 = i14 + 1;
                    DecodeUtil.handleThreeBytes(b10, byteBuffer.get(i14), byteBuffer.get(i16), cArr, i13);
                    i9 = i16 + 1;
                    i13++;
                } else {
                    if (i14 >= i11 - 2) {
                        throw InvalidProtocolBufferException.invalidUtf8();
                    }
                    int i17 = i14 + 1;
                    byte b12 = byteBuffer.get(i14);
                    int i18 = i17 + 1;
                    DecodeUtil.handleFourBytes(b10, b12, byteBuffer.get(i17), byteBuffer.get(i18), cArr, i13);
                    i9 = i18 + 1;
                    i13 = i13 + 1 + 1;
                }
            }
            return new String(cArr, 0, i13);
        }

        public abstract String decodeUtf8Direct(ByteBuffer byteBuffer, int i9, int i10);

        public abstract int encodeUtf8(CharSequence charSequence, byte[] bArr, int i9, int i10);

        public final void encodeUtf8(CharSequence charSequence, ByteBuffer byteBuffer) {
            if (byteBuffer.hasArray()) {
                int iArrayOffset = byteBuffer.arrayOffset();
                byteBuffer.position(Utf8.encode(charSequence, byteBuffer.array(), byteBuffer.position() + iArrayOffset, byteBuffer.remaining()) - iArrayOffset);
            } else if (byteBuffer.isDirect()) {
                encodeUtf8Direct(charSequence, byteBuffer);
            } else {
                encodeUtf8Default(charSequence, byteBuffer);
            }
        }

        public final void encodeUtf8Default(CharSequence charSequence, ByteBuffer byteBuffer) {
            int length = charSequence.length();
            int iPosition = byteBuffer.position();
            int i9 = 0;
            while (i9 < length) {
                try {
                    char cCharAt = charSequence.charAt(i9);
                    if (cCharAt >= 128) {
                        break;
                    }
                    byteBuffer.put(iPosition + i9, (byte) cCharAt);
                    i9++;
                } catch (IndexOutOfBoundsException unused) {
                    throw new ArrayIndexOutOfBoundsException("Failed writing " + charSequence.charAt(i9) + " at index " + (byteBuffer.position() + Math.max(i9, (iPosition - byteBuffer.position()) + 1)));
                }
            }
            if (i9 == length) {
                byteBuffer.position(iPosition + i9);
                return;
            }
            iPosition += i9;
            while (i9 < length) {
                char cCharAt2 = charSequence.charAt(i9);
                if (cCharAt2 < 128) {
                    byteBuffer.put(iPosition, (byte) cCharAt2);
                } else if (cCharAt2 < 2048) {
                    int i10 = iPosition + 1;
                    try {
                        byteBuffer.put(iPosition, (byte) ((cCharAt2 >>> 6) | PsExtractor.AUDIO_STREAM));
                        byteBuffer.put(i10, (byte) ((cCharAt2 & '?') | 128));
                        iPosition = i10;
                    } catch (IndexOutOfBoundsException unused2) {
                        iPosition = i10;
                        throw new ArrayIndexOutOfBoundsException("Failed writing " + charSequence.charAt(i9) + " at index " + (byteBuffer.position() + Math.max(i9, (iPosition - byteBuffer.position()) + 1)));
                    }
                } else {
                    if (cCharAt2 >= 55296 && 57343 >= cCharAt2) {
                        int i11 = i9 + 1;
                        if (i11 != length) {
                            try {
                                char cCharAt3 = charSequence.charAt(i11);
                                if (Character.isSurrogatePair(cCharAt2, cCharAt3)) {
                                    int codePoint = Character.toCodePoint(cCharAt2, cCharAt3);
                                    int i12 = iPosition + 1;
                                    try {
                                        byteBuffer.put(iPosition, (byte) ((codePoint >>> 18) | PsExtractor.VIDEO_STREAM_MASK));
                                        int i13 = i12 + 1;
                                        byteBuffer.put(i12, (byte) (((codePoint >>> 12) & 63) | 128));
                                        int i14 = i13 + 1;
                                        byteBuffer.put(i13, (byte) (((codePoint >>> 6) & 63) | 128));
                                        byteBuffer.put(i14, (byte) ((codePoint & 63) | 128));
                                        iPosition = i14;
                                        i9 = i11;
                                    } catch (IndexOutOfBoundsException unused3) {
                                        iPosition = i12;
                                        i9 = i11;
                                        throw new ArrayIndexOutOfBoundsException("Failed writing " + charSequence.charAt(i9) + " at index " + (byteBuffer.position() + Math.max(i9, (iPosition - byteBuffer.position()) + 1)));
                                    }
                                } else {
                                    i9 = i11;
                                }
                            } catch (IndexOutOfBoundsException unused4) {
                            }
                        }
                        throw new UnpairedSurrogateException(i9, length);
                    }
                    int i15 = iPosition + 1;
                    byteBuffer.put(iPosition, (byte) ((cCharAt2 >>> '\f') | 224));
                    iPosition = i15 + 1;
                    byteBuffer.put(i15, (byte) (((cCharAt2 >>> 6) & 63) | 128));
                    byteBuffer.put(iPosition, (byte) ((cCharAt2 & '?') | 128));
                }
                i9++;
                iPosition++;
            }
            byteBuffer.position(iPosition);
        }

        public abstract void encodeUtf8Direct(CharSequence charSequence, ByteBuffer byteBuffer);

        public final boolean isValidUtf8(byte[] bArr, int i9, int i10) {
            return partialIsValidUtf8(0, bArr, i9, i10) == 0;
        }

        public final int partialIsValidUtf8(int i9, ByteBuffer byteBuffer, int i10, int i11) {
            if (!byteBuffer.hasArray()) {
                return byteBuffer.isDirect() ? partialIsValidUtf8Direct(i9, byteBuffer, i10, i11) : partialIsValidUtf8Default(i9, byteBuffer, i10, i11);
            }
            int iArrayOffset = byteBuffer.arrayOffset();
            return partialIsValidUtf8(i9, byteBuffer.array(), i10 + iArrayOffset, iArrayOffset + i11);
        }

        public abstract int partialIsValidUtf8(int i9, byte[] bArr, int i10, int i11);

        /* JADX WARN: Code restructure failed: missing block: B:10:0x0017, code lost:
        
            if (r8.get(r9) > (-65)) goto L13;
         */
        /* JADX WARN: Code restructure failed: missing block: B:31:0x004c, code lost:
        
            if (r8.get(r9) > (-65)) goto L32;
         */
        /* JADX WARN: Code restructure failed: missing block: B:52:0x008f, code lost:
        
            if (r8.get(r7) > (-65)) goto L53;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final int partialIsValidUtf8Default(int i9, ByteBuffer byteBuffer, int i10, int i11) {
            byte b9;
            int i12;
            int i13;
            if (i9 != 0) {
                if (i10 >= i11) {
                    return i9;
                }
                byte b10 = (byte) i9;
                if (b10 < -32) {
                    if (b10 >= -62) {
                        i13 = i10 + 1;
                    }
                    return -1;
                }
                if (b10 < -16) {
                    byte b11 = (byte) (~(i9 >> 8));
                    if (b11 == 0) {
                        int i14 = i10 + 1;
                        byte b12 = byteBuffer.get(i10);
                        if (i14 >= i11) {
                            return Utf8.incompleteStateFor(b10, b12);
                        }
                        i10 = i14;
                        b11 = b12;
                    }
                    if (b11 <= -65 && ((b10 != -32 || b11 >= -96) && (b10 != -19 || b11 < -96))) {
                        i13 = i10 + 1;
                    }
                    return -1;
                }
                byte b13 = (byte) (~(i9 >> 8));
                if (b13 == 0) {
                    i12 = i10 + 1;
                    b13 = byteBuffer.get(i10);
                    if (i12 >= i11) {
                        return Utf8.incompleteStateFor(b10, b13);
                    }
                    b9 = 0;
                } else {
                    b9 = (byte) (i9 >> 16);
                    i12 = i10;
                }
                if (b9 == 0) {
                    int i15 = i12 + 1;
                    byte b14 = byteBuffer.get(i12);
                    if (i15 >= i11) {
                        return Utf8.incompleteStateFor(b10, b13, b14);
                    }
                    b9 = b14;
                    i12 = i15;
                }
                if (b13 <= -65 && (((b10 << Ascii.f15383FS) + (b13 + 112)) >> 30) == 0 && b9 <= -65) {
                    i10 = i12 + 1;
                }
                return -1;
                i10 = i13;
            }
            return partialIsValidUtf8(byteBuffer, i10, i11);
        }

        public abstract int partialIsValidUtf8Direct(int i9, ByteBuffer byteBuffer, int i10, int i11);

        public final boolean isValidUtf8(ByteBuffer byteBuffer, int i9, int i10) {
            return partialIsValidUtf8(0, byteBuffer, i9, i10) == 0;
        }

        private static int partialIsValidUtf8(ByteBuffer byteBuffer, int i9, int i10) {
            int iEstimateConsecutiveAscii = i9 + Utf8.estimateConsecutiveAscii(byteBuffer, i9, i10);
            while (iEstimateConsecutiveAscii < i10) {
                int i11 = iEstimateConsecutiveAscii + 1;
                byte b9 = byteBuffer.get(iEstimateConsecutiveAscii);
                if (b9 < 0) {
                    if (b9 < -32) {
                        if (i11 >= i10) {
                            return b9;
                        }
                        if (b9 < -62 || byteBuffer.get(i11) > -65) {
                            return -1;
                        }
                        i11++;
                    } else {
                        if (b9 >= -16) {
                            if (i11 >= i10 - 2) {
                                return Utf8.incompleteStateFor(byteBuffer, b9, i11, i10 - i11);
                            }
                            int i12 = i11 + 1;
                            byte b10 = byteBuffer.get(i11);
                            if (b10 <= -65 && (((b9 << Ascii.f15383FS) + (b10 + 112)) >> 30) == 0) {
                                int i13 = i12 + 1;
                                if (byteBuffer.get(i12) <= -65) {
                                    i11 = i13 + 1;
                                    if (byteBuffer.get(i13) > -65) {
                                    }
                                }
                            }
                            return -1;
                        }
                        if (i11 >= i10 - 1) {
                            return Utf8.incompleteStateFor(byteBuffer, b9, i11, i10 - i11);
                        }
                        int i14 = i11 + 1;
                        byte b11 = byteBuffer.get(i11);
                        if (b11 > -65 || ((b9 == -32 && b11 < -96) || ((b9 == -19 && b11 >= -96) || byteBuffer.get(i14) > -65))) {
                            return -1;
                        }
                        iEstimateConsecutiveAscii = i14 + 1;
                    }
                }
                iEstimateConsecutiveAscii = i11;
            }
            return 0;
        }
    }

    public static class UnpairedSurrogateException extends IllegalArgumentException {
        public UnpairedSurrogateException(int i9, int i10) {
            super("Unpaired surrogate at index " + i9 + " of " + i10);
        }
    }

    public static final class UnsafeProcessor extends Processor {
        public static boolean isAvailable() {
            return UnsafeUtil.hasUnsafeArrayOperations() && UnsafeUtil.hasUnsafeByteBufferOperations();
        }

        private static int unsafeEstimateConsecutiveAscii(byte[] bArr, long j9, int i9) {
            int i10 = 0;
            if (i9 < 16) {
                return 0;
            }
            while (i10 < i9) {
                long j10 = 1 + j9;
                if (UnsafeUtil.getByte(bArr, j9) < 0) {
                    return i10;
                }
                i10++;
                j9 = j10;
            }
            return i9;
        }

        private static int unsafeIncompleteStateFor(byte[] bArr, int i9, long j9, int i10) {
            if (i10 == 0) {
                return Utf8.incompleteStateFor(i9);
            }
            if (i10 == 1) {
                return Utf8.incompleteStateFor(i9, UnsafeUtil.getByte(bArr, j9));
            }
            if (i10 == 2) {
                return Utf8.incompleteStateFor(i9, UnsafeUtil.getByte(bArr, j9), UnsafeUtil.getByte(bArr, j9 + 1));
            }
            throw new AssertionError();
        }

        @Override // com.google.protobuf.Utf8.Processor
        public String decodeUtf8(byte[] bArr, int i9, int i10) throws InvalidProtocolBufferException {
            if ((i9 | i10 | ((bArr.length - i9) - i10)) < 0) {
                throw new ArrayIndexOutOfBoundsException(String.format("buffer length=%d, index=%d, size=%d", Integer.valueOf(bArr.length), Integer.valueOf(i9), Integer.valueOf(i10)));
            }
            int i11 = i9 + i10;
            char[] cArr = new char[i10];
            int i12 = 0;
            while (i9 < i11) {
                byte b9 = UnsafeUtil.getByte(bArr, i9);
                if (!DecodeUtil.isOneByte(b9)) {
                    break;
                }
                i9++;
                DecodeUtil.handleOneByte(b9, cArr, i12);
                i12++;
            }
            int i13 = i12;
            while (i9 < i11) {
                int i14 = i9 + 1;
                byte b10 = UnsafeUtil.getByte(bArr, i9);
                if (DecodeUtil.isOneByte(b10)) {
                    int i15 = i13 + 1;
                    DecodeUtil.handleOneByte(b10, cArr, i13);
                    while (i14 < i11) {
                        byte b11 = UnsafeUtil.getByte(bArr, i14);
                        if (!DecodeUtil.isOneByte(b11)) {
                            break;
                        }
                        i14++;
                        DecodeUtil.handleOneByte(b11, cArr, i15);
                        i15++;
                    }
                    i9 = i14;
                    i13 = i15;
                } else if (DecodeUtil.isTwoBytes(b10)) {
                    if (i14 >= i11) {
                        throw InvalidProtocolBufferException.invalidUtf8();
                    }
                    DecodeUtil.handleTwoBytes(b10, UnsafeUtil.getByte(bArr, i14), cArr, i13);
                    i9 = i14 + 1;
                    i13++;
                } else if (DecodeUtil.isThreeBytes(b10)) {
                    if (i14 >= i11 - 1) {
                        throw InvalidProtocolBufferException.invalidUtf8();
                    }
                    int i16 = i14 + 1;
                    DecodeUtil.handleThreeBytes(b10, UnsafeUtil.getByte(bArr, i14), UnsafeUtil.getByte(bArr, i16), cArr, i13);
                    i9 = i16 + 1;
                    i13++;
                } else {
                    if (i14 >= i11 - 2) {
                        throw InvalidProtocolBufferException.invalidUtf8();
                    }
                    int i17 = i14 + 1;
                    byte b12 = UnsafeUtil.getByte(bArr, i14);
                    int i18 = i17 + 1;
                    DecodeUtil.handleFourBytes(b10, b12, UnsafeUtil.getByte(bArr, i17), UnsafeUtil.getByte(bArr, i18), cArr, i13);
                    i9 = i18 + 1;
                    i13 = i13 + 1 + 1;
                }
            }
            return new String(cArr, 0, i13);
        }

        @Override // com.google.protobuf.Utf8.Processor
        public String decodeUtf8Direct(ByteBuffer byteBuffer, int i9, int i10) throws InvalidProtocolBufferException {
            if ((i9 | i10 | ((byteBuffer.limit() - i9) - i10)) < 0) {
                throw new ArrayIndexOutOfBoundsException(String.format("buffer limit=%d, index=%d, limit=%d", Integer.valueOf(byteBuffer.limit()), Integer.valueOf(i9), Integer.valueOf(i10)));
            }
            long jAddressOffset = UnsafeUtil.addressOffset(byteBuffer) + i9;
            long j9 = i10 + jAddressOffset;
            char[] cArr = new char[i10];
            int i11 = 0;
            while (jAddressOffset < j9) {
                byte b9 = UnsafeUtil.getByte(jAddressOffset);
                if (!DecodeUtil.isOneByte(b9)) {
                    break;
                }
                jAddressOffset++;
                DecodeUtil.handleOneByte(b9, cArr, i11);
                i11++;
            }
            while (true) {
                int i12 = i11;
                while (jAddressOffset < j9) {
                    long j10 = jAddressOffset + 1;
                    byte b10 = UnsafeUtil.getByte(jAddressOffset);
                    if (DecodeUtil.isOneByte(b10)) {
                        int i13 = i12 + 1;
                        DecodeUtil.handleOneByte(b10, cArr, i12);
                        while (j10 < j9) {
                            byte b11 = UnsafeUtil.getByte(j10);
                            if (!DecodeUtil.isOneByte(b11)) {
                                break;
                            }
                            j10++;
                            DecodeUtil.handleOneByte(b11, cArr, i13);
                            i13++;
                        }
                        i12 = i13;
                        jAddressOffset = j10;
                    } else if (DecodeUtil.isTwoBytes(b10)) {
                        if (j10 >= j9) {
                            throw InvalidProtocolBufferException.invalidUtf8();
                        }
                        jAddressOffset = j10 + 1;
                        DecodeUtil.handleTwoBytes(b10, UnsafeUtil.getByte(j10), cArr, i12);
                        i12++;
                    } else if (DecodeUtil.isThreeBytes(b10)) {
                        if (j10 >= j9 - 1) {
                            throw InvalidProtocolBufferException.invalidUtf8();
                        }
                        long j11 = j10 + 1;
                        DecodeUtil.handleThreeBytes(b10, UnsafeUtil.getByte(j10), UnsafeUtil.getByte(j11), cArr, i12);
                        i12++;
                        jAddressOffset = j11 + 1;
                    } else {
                        if (j10 >= j9 - 2) {
                            throw InvalidProtocolBufferException.invalidUtf8();
                        }
                        long j12 = j10 + 1;
                        byte b12 = UnsafeUtil.getByte(j10);
                        long j13 = j12 + 1;
                        byte b13 = UnsafeUtil.getByte(j12);
                        jAddressOffset = j13 + 1;
                        DecodeUtil.handleFourBytes(b10, b12, b13, UnsafeUtil.getByte(j13), cArr, i12);
                        i11 = i12 + 1 + 1;
                    }
                }
                return new String(cArr, 0, i12);
            }
        }

        @Override // com.google.protobuf.Utf8.Processor
        public int encodeUtf8(CharSequence charSequence, byte[] bArr, int i9, int i10) {
            char c9;
            long j9;
            long j10;
            long j11;
            char c10;
            int i11;
            char cCharAt;
            long j12 = i9;
            long j13 = i10 + j12;
            int length = charSequence.length();
            if (length > i10 || bArr.length - i10 < i9) {
                throw new ArrayIndexOutOfBoundsException("Failed writing " + charSequence.charAt(length - 1) + " at index " + (i9 + i10));
            }
            int i12 = 0;
            while (true) {
                c9 = 128;
                j9 = 1;
                if (i12 >= length || (cCharAt = charSequence.charAt(i12)) >= 128) {
                    break;
                }
                UnsafeUtil.putByte(bArr, j12, (byte) cCharAt);
                i12++;
                j12 = 1 + j12;
            }
            if (i12 == length) {
                return (int) j12;
            }
            while (i12 < length) {
                char cCharAt2 = charSequence.charAt(i12);
                if (cCharAt2 < c9 && j12 < j13) {
                    long j14 = j12 + j9;
                    UnsafeUtil.putByte(bArr, j12, (byte) cCharAt2);
                    j11 = j9;
                    j10 = j14;
                    c10 = c9;
                } else if (cCharAt2 < 2048 && j12 <= j13 - 2) {
                    long j15 = j12 + j9;
                    UnsafeUtil.putByte(bArr, j12, (byte) ((cCharAt2 >>> 6) | 960));
                    long j16 = j15 + j9;
                    UnsafeUtil.putByte(bArr, j15, (byte) ((cCharAt2 & '?') | 128));
                    long j17 = j9;
                    c10 = 128;
                    j10 = j16;
                    j11 = j17;
                } else {
                    if ((cCharAt2 >= 55296 && 57343 >= cCharAt2) || j12 > j13 - 3) {
                        if (j12 > j13 - 4) {
                            if (55296 <= cCharAt2 && cCharAt2 <= 57343 && ((i11 = i12 + 1) == length || !Character.isSurrogatePair(cCharAt2, charSequence.charAt(i11)))) {
                                throw new UnpairedSurrogateException(i12, length);
                            }
                            throw new ArrayIndexOutOfBoundsException("Failed writing " + cCharAt2 + " at index " + j12);
                        }
                        int i13 = i12 + 1;
                        if (i13 != length) {
                            char cCharAt3 = charSequence.charAt(i13);
                            if (Character.isSurrogatePair(cCharAt2, cCharAt3)) {
                                int codePoint = Character.toCodePoint(cCharAt2, cCharAt3);
                                long j18 = j12 + 1;
                                UnsafeUtil.putByte(bArr, j12, (byte) ((codePoint >>> 18) | PsExtractor.VIDEO_STREAM_MASK));
                                long j19 = j18 + 1;
                                c10 = 128;
                                UnsafeUtil.putByte(bArr, j18, (byte) (((codePoint >>> 12) & 63) | 128));
                                long j20 = j19 + 1;
                                UnsafeUtil.putByte(bArr, j19, (byte) (((codePoint >>> 6) & 63) | 128));
                                j11 = 1;
                                j10 = j20 + 1;
                                UnsafeUtil.putByte(bArr, j20, (byte) ((codePoint & 63) | 128));
                                i12 = i13;
                            } else {
                                i12 = i13;
                            }
                        }
                        throw new UnpairedSurrogateException(i12 - 1, length);
                    }
                    long j21 = j12 + j9;
                    UnsafeUtil.putByte(bArr, j12, (byte) ((cCharAt2 >>> '\f') | 480));
                    long j22 = j21 + j9;
                    UnsafeUtil.putByte(bArr, j21, (byte) (((cCharAt2 >>> 6) & 63) | 128));
                    UnsafeUtil.putByte(bArr, j22, (byte) ((cCharAt2 & '?') | 128));
                    j10 = j22 + 1;
                    j11 = 1;
                    c10 = 128;
                }
                i12++;
                c9 = c10;
                long j23 = j11;
                j12 = j10;
                j9 = j23;
            }
            return (int) j12;
        }

        @Override // com.google.protobuf.Utf8.Processor
        public void encodeUtf8Direct(CharSequence charSequence, ByteBuffer byteBuffer) {
            char c9;
            long j9;
            int i9;
            int i10;
            long j10;
            char c10;
            char cCharAt;
            long jAddressOffset = UnsafeUtil.addressOffset(byteBuffer);
            long jPosition = byteBuffer.position() + jAddressOffset;
            long jLimit = byteBuffer.limit() + jAddressOffset;
            int length = charSequence.length();
            if (length > jLimit - jPosition) {
                throw new ArrayIndexOutOfBoundsException("Failed writing " + charSequence.charAt(length - 1) + " at index " + byteBuffer.limit());
            }
            int i11 = 0;
            while (true) {
                c9 = 128;
                if (i11 >= length || (cCharAt = charSequence.charAt(i11)) >= 128) {
                    break;
                }
                UnsafeUtil.putByte(jPosition, (byte) cCharAt);
                i11++;
                jPosition++;
            }
            if (i11 == length) {
                byteBuffer.position((int) (jPosition - jAddressOffset));
                return;
            }
            while (i11 < length) {
                char cCharAt2 = charSequence.charAt(i11);
                if (cCharAt2 >= c9 || jPosition >= jLimit) {
                    if (cCharAt2 >= 2048 || jPosition > jLimit - 2) {
                        j9 = jAddressOffset;
                        if ((cCharAt2 >= 55296 && 57343 >= cCharAt2) || jPosition > jLimit - 3) {
                            if (jPosition > jLimit - 4) {
                                if (55296 <= cCharAt2 && cCharAt2 <= 57343 && ((i9 = i11 + 1) == length || !Character.isSurrogatePair(cCharAt2, charSequence.charAt(i9)))) {
                                    throw new UnpairedSurrogateException(i11, length);
                                }
                                throw new ArrayIndexOutOfBoundsException("Failed writing " + cCharAt2 + " at index " + jPosition);
                            }
                            i10 = i11 + 1;
                            if (i10 != length) {
                                char cCharAt3 = charSequence.charAt(i10);
                                if (Character.isSurrogatePair(cCharAt2, cCharAt3)) {
                                    int codePoint = Character.toCodePoint(cCharAt2, cCharAt3);
                                    j10 = jLimit;
                                    long j11 = jPosition + 1;
                                    UnsafeUtil.putByte(jPosition, (byte) ((codePoint >>> 18) | PsExtractor.VIDEO_STREAM_MASK));
                                    long j12 = j11 + 1;
                                    c10 = 128;
                                    UnsafeUtil.putByte(j11, (byte) (((codePoint >>> 12) & 63) | 128));
                                    long j13 = j12 + 1;
                                    UnsafeUtil.putByte(j12, (byte) (((codePoint >>> 6) & 63) | 128));
                                    UnsafeUtil.putByte(j13, (byte) ((codePoint & 63) | 128));
                                    jPosition = j13 + 1;
                                } else {
                                    i11 = i10;
                                }
                            }
                            throw new UnpairedSurrogateException(i11 - 1, length);
                        }
                        long j14 = jPosition + 1;
                        UnsafeUtil.putByte(jPosition, (byte) ((cCharAt2 >>> '\f') | 480));
                        long j15 = j14 + 1;
                        UnsafeUtil.putByte(j14, (byte) (((cCharAt2 >>> 6) & 63) | 128));
                        UnsafeUtil.putByte(j15, (byte) ((cCharAt2 & '?') | 128));
                        jPosition = j15 + 1;
                    } else {
                        j9 = jAddressOffset;
                        long j16 = jPosition + 1;
                        UnsafeUtil.putByte(jPosition, (byte) ((cCharAt2 >>> 6) | 960));
                        UnsafeUtil.putByte(j16, (byte) ((cCharAt2 & '?') | 128));
                        jPosition = j16 + 1;
                    }
                    j10 = jLimit;
                    i10 = i11;
                    c10 = 128;
                } else {
                    UnsafeUtil.putByte(jPosition, (byte) cCharAt2);
                    j10 = jLimit;
                    i10 = i11;
                    c10 = c9;
                    jPosition++;
                    j9 = jAddressOffset;
                }
                c9 = c10;
                jAddressOffset = j9;
                jLimit = j10;
                i11 = i10 + 1;
            }
            byteBuffer.position((int) (jPosition - jAddressOffset));
        }

        /* JADX WARN: Code restructure failed: missing block: B:35:0x0059, code lost:
        
            if (com.google.protobuf.UnsafeUtil.getByte(r13, r2) > (-65)) goto L38;
         */
        /* JADX WARN: Code restructure failed: missing block: B:58:0x009e, code lost:
        
            if (com.google.protobuf.UnsafeUtil.getByte(r13, r2) > (-65)) goto L59;
         */
        @Override // com.google.protobuf.Utf8.Processor
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public int partialIsValidUtf8(int i9, byte[] bArr, int i10, int i11) {
            long j9;
            byte b9 = 0;
            if ((i10 | i11 | (bArr.length - i11)) < 0) {
                throw new ArrayIndexOutOfBoundsException(String.format("Array length=%d, index=%d, limit=%d", Integer.valueOf(bArr.length), Integer.valueOf(i10), Integer.valueOf(i11)));
            }
            long j10 = i10;
            long j11 = i11;
            if (i9 != 0) {
                if (j10 >= j11) {
                    return i9;
                }
                byte b10 = (byte) i9;
                if (b10 < -32) {
                    if (b10 >= -62) {
                        long j12 = 1 + j10;
                        if (UnsafeUtil.getByte(bArr, j10) <= -65) {
                            j10 = j12;
                        }
                    }
                    return -1;
                }
                if (b10 < -16) {
                    byte b11 = (byte) (~(i9 >> 8));
                    if (b11 == 0) {
                        long j13 = j10 + 1;
                        b11 = UnsafeUtil.getByte(bArr, j10);
                        if (j13 >= j11) {
                            return Utf8.incompleteStateFor(b10, b11);
                        }
                        j10 = j13;
                    }
                    if (b11 <= -65 && ((b10 != -32 || b11 >= -96) && (b10 != -19 || b11 < -96))) {
                        j9 = j10 + 1;
                    }
                    return -1;
                }
                byte b12 = (byte) (~(i9 >> 8));
                if (b12 == 0) {
                    long j14 = j10 + 1;
                    b12 = UnsafeUtil.getByte(bArr, j10);
                    if (j14 >= j11) {
                        return Utf8.incompleteStateFor(b10, b12);
                    }
                    j10 = j14;
                } else {
                    b9 = (byte) (i9 >> 16);
                }
                if (b9 == 0) {
                    long j15 = j10 + 1;
                    b9 = UnsafeUtil.getByte(bArr, j10);
                    if (j15 >= j11) {
                        return Utf8.incompleteStateFor(b10, b12, b9);
                    }
                    j10 = j15;
                }
                if (b12 <= -65 && (((b10 << Ascii.f15383FS) + (b12 + 112)) >> 30) == 0 && b9 <= -65) {
                    j9 = j10 + 1;
                }
                return -1;
                j10 = j9;
            }
            return partialIsValidUtf8(bArr, j10, (int) (j11 - j10));
        }

        /* JADX WARN: Code restructure failed: missing block: B:35:0x0063, code lost:
        
            if (com.google.protobuf.UnsafeUtil.getByte(r2) > (-65)) goto L38;
         */
        /* JADX WARN: Code restructure failed: missing block: B:58:0x00a8, code lost:
        
            if (com.google.protobuf.UnsafeUtil.getByte(r2) > (-65)) goto L59;
         */
        @Override // com.google.protobuf.Utf8.Processor
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public int partialIsValidUtf8Direct(int i9, ByteBuffer byteBuffer, int i10, int i11) {
            long j9;
            byte b9 = 0;
            if ((i10 | i11 | (byteBuffer.limit() - i11)) < 0) {
                throw new ArrayIndexOutOfBoundsException(String.format("buffer limit=%d, index=%d, limit=%d", Integer.valueOf(byteBuffer.limit()), Integer.valueOf(i10), Integer.valueOf(i11)));
            }
            long jAddressOffset = UnsafeUtil.addressOffset(byteBuffer) + i10;
            long j10 = (i11 - i10) + jAddressOffset;
            if (i9 != 0) {
                if (jAddressOffset >= j10) {
                    return i9;
                }
                byte b10 = (byte) i9;
                if (b10 < -32) {
                    if (b10 >= -62) {
                        long j11 = 1 + jAddressOffset;
                        if (UnsafeUtil.getByte(jAddressOffset) <= -65) {
                            jAddressOffset = j11;
                        }
                    }
                    return -1;
                }
                if (b10 < -16) {
                    byte b11 = (byte) (~(i9 >> 8));
                    if (b11 == 0) {
                        long j12 = jAddressOffset + 1;
                        b11 = UnsafeUtil.getByte(jAddressOffset);
                        if (j12 >= j10) {
                            return Utf8.incompleteStateFor(b10, b11);
                        }
                        jAddressOffset = j12;
                    }
                    if (b11 <= -65 && ((b10 != -32 || b11 >= -96) && (b10 != -19 || b11 < -96))) {
                        j9 = jAddressOffset + 1;
                    }
                    return -1;
                }
                byte b12 = (byte) (~(i9 >> 8));
                if (b12 == 0) {
                    long j13 = jAddressOffset + 1;
                    b12 = UnsafeUtil.getByte(jAddressOffset);
                    if (j13 >= j10) {
                        return Utf8.incompleteStateFor(b10, b12);
                    }
                    jAddressOffset = j13;
                } else {
                    b9 = (byte) (i9 >> 16);
                }
                if (b9 == 0) {
                    long j14 = jAddressOffset + 1;
                    b9 = UnsafeUtil.getByte(jAddressOffset);
                    if (j14 >= j10) {
                        return Utf8.incompleteStateFor(b10, b12, b9);
                    }
                    jAddressOffset = j14;
                }
                if (b12 <= -65 && (((b10 << Ascii.f15383FS) + (b12 + 112)) >> 30) == 0 && b9 <= -65) {
                    j9 = jAddressOffset + 1;
                }
                return -1;
                jAddressOffset = j9;
            }
            return partialIsValidUtf8(jAddressOffset, (int) (j10 - jAddressOffset));
        }

        private static int unsafeEstimateConsecutiveAscii(long j9, int i9) {
            if (i9 < 16) {
                return 0;
            }
            int i10 = 8 - (((int) j9) & 7);
            int i11 = i10;
            while (i11 > 0) {
                long j10 = 1 + j9;
                if (UnsafeUtil.getByte(j9) < 0) {
                    return i10 - i11;
                }
                i11--;
                j9 = j10;
            }
            int i12 = i9 - i10;
            while (i12 >= 8 && (UnsafeUtil.getLong(j9) & Utf8.ASCII_MASK_LONG) == 0) {
                j9 += 8;
                i12 -= 8;
            }
            return i9 - i12;
        }

        private static int unsafeIncompleteStateFor(long j9, int i9, int i10) {
            if (i10 == 0) {
                return Utf8.incompleteStateFor(i9);
            }
            if (i10 == 1) {
                return Utf8.incompleteStateFor(i9, UnsafeUtil.getByte(j9));
            }
            if (i10 == 2) {
                return Utf8.incompleteStateFor(i9, UnsafeUtil.getByte(j9), UnsafeUtil.getByte(j9 + 1));
            }
            throw new AssertionError();
        }

        /* JADX WARN: Code restructure failed: missing block: B:22:0x0039, code lost:
        
            return -1;
         */
        /* JADX WARN: Code restructure failed: missing block: B:39:0x0063, code lost:
        
            return -1;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        private static int partialIsValidUtf8(byte[] bArr, long j9, int i9) {
            long j10;
            int iUnsafeEstimateConsecutiveAscii = unsafeEstimateConsecutiveAscii(bArr, j9, i9);
            int i10 = i9 - iUnsafeEstimateConsecutiveAscii;
            long j11 = j9 + iUnsafeEstimateConsecutiveAscii;
            while (true) {
                byte b9 = 0;
                while (true) {
                    if (i10 <= 0) {
                        break;
                    }
                    long j12 = j11 + 1;
                    b9 = UnsafeUtil.getByte(bArr, j11);
                    if (b9 < 0) {
                        j11 = j12;
                        break;
                    }
                    i10--;
                    j11 = j12;
                }
                if (i10 == 0) {
                    return 0;
                }
                int i11 = i10 - 1;
                if (b9 >= -32) {
                    if (b9 >= -16) {
                        if (i11 < 3) {
                            return unsafeIncompleteStateFor(bArr, b9, j11, i11);
                        }
                        i10 = i11 - 3;
                        long j13 = j11 + 1;
                        byte b10 = UnsafeUtil.getByte(bArr, j11);
                        if (b10 <= -65 && (((b9 << Ascii.f15383FS) + (b10 + 112)) >> 30) == 0) {
                            long j14 = j13 + 1;
                            if (UnsafeUtil.getByte(bArr, j13) > -65) {
                                break;
                            }
                            j10 = 1 + j14;
                            if (UnsafeUtil.getByte(bArr, j14) > -65) {
                                break;
                            }
                        } else {
                            break;
                        }
                    } else {
                        if (i11 < 2) {
                            return unsafeIncompleteStateFor(bArr, b9, j11, i11);
                        }
                        i10 = i11 - 2;
                        long j15 = j11 + 1;
                        byte b11 = UnsafeUtil.getByte(bArr, j11);
                        if (b11 > -65 || ((b9 == -32 && b11 < -96) || (b9 == -19 && b11 >= -96))) {
                            break;
                        }
                        j10 = 1 + j15;
                        if (UnsafeUtil.getByte(bArr, j15) > -65) {
                            break;
                        }
                    }
                } else if (i11 != 0) {
                    i10 = i11 - 1;
                    if (b9 < -62) {
                        break;
                    }
                    j10 = 1 + j11;
                    if (UnsafeUtil.getByte(bArr, j11) > -65) {
                        break;
                    }
                } else {
                    return b9;
                }
                j11 = j10;
            }
            return -1;
        }

        /* JADX WARN: Code restructure failed: missing block: B:22:0x0039, code lost:
        
            return -1;
         */
        /* JADX WARN: Code restructure failed: missing block: B:39:0x0063, code lost:
        
            return -1;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        private static int partialIsValidUtf8(long j9, int i9) {
            long j10;
            int iUnsafeEstimateConsecutiveAscii = unsafeEstimateConsecutiveAscii(j9, i9);
            long j11 = j9 + iUnsafeEstimateConsecutiveAscii;
            int i10 = i9 - iUnsafeEstimateConsecutiveAscii;
            while (true) {
                byte b9 = 0;
                while (true) {
                    if (i10 <= 0) {
                        break;
                    }
                    long j12 = j11 + 1;
                    b9 = UnsafeUtil.getByte(j11);
                    if (b9 < 0) {
                        j11 = j12;
                        break;
                    }
                    i10--;
                    j11 = j12;
                }
                if (i10 == 0) {
                    return 0;
                }
                int i11 = i10 - 1;
                if (b9 >= -32) {
                    if (b9 >= -16) {
                        if (i11 < 3) {
                            return unsafeIncompleteStateFor(j11, b9, i11);
                        }
                        i10 = i11 - 3;
                        long j13 = j11 + 1;
                        byte b10 = UnsafeUtil.getByte(j11);
                        if (b10 <= -65 && (((b9 << Ascii.f15383FS) + (b10 + 112)) >> 30) == 0) {
                            long j14 = j13 + 1;
                            if (UnsafeUtil.getByte(j13) > -65) {
                                break;
                            }
                            j10 = 1 + j14;
                            if (UnsafeUtil.getByte(j14) > -65) {
                                break;
                            }
                        } else {
                            break;
                        }
                    } else {
                        if (i11 < 2) {
                            return unsafeIncompleteStateFor(j11, b9, i11);
                        }
                        i10 = i11 - 2;
                        long j15 = j11 + 1;
                        byte b11 = UnsafeUtil.getByte(j11);
                        if (b11 > -65 || ((b9 == -32 && b11 < -96) || (b9 == -19 && b11 >= -96))) {
                            break;
                        }
                        j10 = 1 + j15;
                        if (UnsafeUtil.getByte(j15) > -65) {
                            break;
                        }
                    }
                } else if (i11 != 0) {
                    i10 = i11 - 1;
                    if (b9 < -62) {
                        break;
                    }
                    j10 = 1 + j11;
                    if (UnsafeUtil.getByte(j11) > -65) {
                        break;
                    }
                } else {
                    return b9;
                }
                j11 = j10;
            }
            return -1;
        }
    }

    static {
        processor = (!UnsafeProcessor.isAvailable() || Android.isOnAndroidDevice()) ? new SafeProcessor() : new UnsafeProcessor();
    }

    private Utf8() {
    }

    public static String decodeUtf8(ByteBuffer byteBuffer, int i9, int i10) throws InvalidProtocolBufferException {
        return processor.decodeUtf8(byteBuffer, i9, i10);
    }

    public static int encode(CharSequence charSequence, byte[] bArr, int i9, int i10) {
        return processor.encodeUtf8(charSequence, bArr, i9, i10);
    }

    public static void encodeUtf8(CharSequence charSequence, ByteBuffer byteBuffer) {
        processor.encodeUtf8(charSequence, byteBuffer);
    }

    public static int encodedLength(CharSequence charSequence) {
        int length = charSequence.length();
        int i9 = 0;
        while (i9 < length && charSequence.charAt(i9) < 128) {
            i9++;
        }
        int iEncodedLengthGeneral = length;
        while (true) {
            if (i9 < length) {
                char cCharAt = charSequence.charAt(i9);
                if (cCharAt >= 2048) {
                    iEncodedLengthGeneral += encodedLengthGeneral(charSequence, i9);
                    break;
                }
                iEncodedLengthGeneral += (127 - cCharAt) >>> 31;
                i9++;
            } else {
                break;
            }
        }
        if (iEncodedLengthGeneral >= length) {
            return iEncodedLengthGeneral;
        }
        throw new IllegalArgumentException("UTF-8 length does not fit in int: " + (iEncodedLengthGeneral + 4294967296L));
    }

    private static int encodedLengthGeneral(CharSequence charSequence, int i9) {
        int length = charSequence.length();
        int i10 = 0;
        while (i9 < length) {
            char cCharAt = charSequence.charAt(i9);
            if (cCharAt < 2048) {
                i10 += (127 - cCharAt) >>> 31;
            } else {
                i10 += 2;
                if (55296 <= cCharAt && cCharAt <= 57343) {
                    if (Character.codePointAt(charSequence, i9) < 65536) {
                        throw new UnpairedSurrogateException(i9, length);
                    }
                    i9++;
                }
            }
            i9++;
        }
        return i10;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int estimateConsecutiveAscii(ByteBuffer byteBuffer, int i9, int i10) {
        int i11 = i10 - 7;
        int i12 = i9;
        while (i12 < i11 && (byteBuffer.getLong(i12) & ASCII_MASK_LONG) == 0) {
            i12 += 8;
        }
        return i12 - i9;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int incompleteStateFor(int i9) {
        if (i9 > -12) {
            return -1;
        }
        return i9;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int incompleteStateFor(int i9, int i10) {
        if (i9 > -12 || i10 > -65) {
            return -1;
        }
        return i9 ^ (i10 << 8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int incompleteStateFor(int i9, int i10, int i11) {
        if (i9 > -12 || i10 > -65 || i11 > -65) {
            return -1;
        }
        return (i9 ^ (i10 << 8)) ^ (i11 << 16);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int incompleteStateFor(byte[] bArr, int i9, int i10) {
        byte b9 = bArr[i9 - 1];
        int i11 = i10 - i9;
        if (i11 == 0) {
            return incompleteStateFor(b9);
        }
        if (i11 == 1) {
            return incompleteStateFor(b9, bArr[i9]);
        }
        if (i11 == 2) {
            return incompleteStateFor(b9, bArr[i9], bArr[i9 + 1]);
        }
        throw new AssertionError();
    }

    public static boolean isValidUtf8(byte[] bArr) {
        return processor.isValidUtf8(bArr, 0, bArr.length);
    }

    public static int partialIsValidUtf8(int i9, byte[] bArr, int i10, int i11) {
        return processor.partialIsValidUtf8(i9, bArr, i10, i11);
    }

    public static String decodeUtf8(byte[] bArr, int i9, int i10) {
        return processor.decodeUtf8(bArr, i9, i10);
    }

    public static boolean isValidUtf8(byte[] bArr, int i9, int i10) {
        return processor.isValidUtf8(bArr, i9, i10);
    }

    public static int partialIsValidUtf8(int i9, ByteBuffer byteBuffer, int i10, int i11) {
        return processor.partialIsValidUtf8(i9, byteBuffer, i10, i11);
    }

    public static boolean isValidUtf8(ByteBuffer byteBuffer) {
        return processor.isValidUtf8(byteBuffer, byteBuffer.position(), byteBuffer.remaining());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int incompleteStateFor(ByteBuffer byteBuffer, int i9, int i10, int i11) {
        if (i11 == 0) {
            return incompleteStateFor(i9);
        }
        if (i11 == 1) {
            return incompleteStateFor(i9, byteBuffer.get(i10));
        }
        if (i11 == 2) {
            return incompleteStateFor(i9, byteBuffer.get(i10), byteBuffer.get(i10 + 1));
        }
        throw new AssertionError();
    }

    public static final class SafeProcessor extends Processor {
        private static int partialIsValidUtf8NonAscii(byte[] bArr, int i9, int i10) {
            while (i9 < i10) {
                int i11 = i9 + 1;
                byte b9 = bArr[i9];
                if (b9 < 0) {
                    if (b9 < -32) {
                        if (i11 >= i10) {
                            return b9;
                        }
                        if (b9 >= -62) {
                            i9 = i11 + 1;
                            if (bArr[i11] > -65) {
                            }
                        }
                        return -1;
                    }
                    if (b9 >= -16) {
                        if (i11 >= i10 - 2) {
                            return Utf8.incompleteStateFor(bArr, i11, i10);
                        }
                        int i12 = i11 + 1;
                        byte b10 = bArr[i11];
                        if (b10 <= -65 && (((b9 << Ascii.f15383FS) + (b10 + 112)) >> 30) == 0) {
                            int i13 = i12 + 1;
                            if (bArr[i12] <= -65) {
                                i11 = i13 + 1;
                                if (bArr[i13] > -65) {
                                }
                            }
                        }
                        return -1;
                    }
                    if (i11 >= i10 - 1) {
                        return Utf8.incompleteStateFor(bArr, i11, i10);
                    }
                    int i14 = i11 + 1;
                    byte b11 = bArr[i11];
                    if (b11 <= -65 && ((b9 != -32 || b11 >= -96) && (b9 != -19 || b11 < -96))) {
                        i9 = i14 + 1;
                        if (bArr[i14] > -65) {
                        }
                    }
                    return -1;
                }
                i9 = i11;
            }
            return 0;
        }

        @Override // com.google.protobuf.Utf8.Processor
        public String decodeUtf8(byte[] bArr, int i9, int i10) throws InvalidProtocolBufferException {
            if ((i9 | i10 | ((bArr.length - i9) - i10)) < 0) {
                throw new ArrayIndexOutOfBoundsException(String.format("buffer length=%d, index=%d, size=%d", Integer.valueOf(bArr.length), Integer.valueOf(i9), Integer.valueOf(i10)));
            }
            int i11 = i9 + i10;
            char[] cArr = new char[i10];
            int i12 = 0;
            while (i9 < i11) {
                byte b9 = bArr[i9];
                if (!DecodeUtil.isOneByte(b9)) {
                    break;
                }
                i9++;
                DecodeUtil.handleOneByte(b9, cArr, i12);
                i12++;
            }
            int i13 = i12;
            while (i9 < i11) {
                int i14 = i9 + 1;
                byte b10 = bArr[i9];
                if (DecodeUtil.isOneByte(b10)) {
                    int i15 = i13 + 1;
                    DecodeUtil.handleOneByte(b10, cArr, i13);
                    while (i14 < i11) {
                        byte b11 = bArr[i14];
                        if (!DecodeUtil.isOneByte(b11)) {
                            break;
                        }
                        i14++;
                        DecodeUtil.handleOneByte(b11, cArr, i15);
                        i15++;
                    }
                    i9 = i14;
                    i13 = i15;
                } else if (DecodeUtil.isTwoBytes(b10)) {
                    if (i14 >= i11) {
                        throw InvalidProtocolBufferException.invalidUtf8();
                    }
                    DecodeUtil.handleTwoBytes(b10, bArr[i14], cArr, i13);
                    i9 = i14 + 1;
                    i13++;
                } else if (DecodeUtil.isThreeBytes(b10)) {
                    if (i14 >= i11 - 1) {
                        throw InvalidProtocolBufferException.invalidUtf8();
                    }
                    int i16 = i14 + 1;
                    DecodeUtil.handleThreeBytes(b10, bArr[i14], bArr[i16], cArr, i13);
                    i9 = i16 + 1;
                    i13++;
                } else {
                    if (i14 >= i11 - 2) {
                        throw InvalidProtocolBufferException.invalidUtf8();
                    }
                    int i17 = i14 + 1;
                    byte b12 = bArr[i14];
                    int i18 = i17 + 1;
                    DecodeUtil.handleFourBytes(b10, b12, bArr[i17], bArr[i18], cArr, i13);
                    i9 = i18 + 1;
                    i13 = i13 + 1 + 1;
                }
            }
            return new String(cArr, 0, i13);
        }

        @Override // com.google.protobuf.Utf8.Processor
        public String decodeUtf8Direct(ByteBuffer byteBuffer, int i9, int i10) {
            return decodeUtf8Default(byteBuffer, i9, i10);
        }

        @Override // com.google.protobuf.Utf8.Processor
        public int encodeUtf8(CharSequence charSequence, byte[] bArr, int i9, int i10) {
            int i11;
            int i12;
            int i13;
            char cCharAt;
            int length = charSequence.length();
            int i14 = i10 + i9;
            int i15 = 0;
            while (i15 < length && (i13 = i15 + i9) < i14 && (cCharAt = charSequence.charAt(i15)) < 128) {
                bArr[i13] = (byte) cCharAt;
                i15++;
            }
            if (i15 == length) {
                return i9 + length;
            }
            int i16 = i9 + i15;
            while (i15 < length) {
                char cCharAt2 = charSequence.charAt(i15);
                if (cCharAt2 >= 128 || i16 >= i14) {
                    if (cCharAt2 < 2048 && i16 <= i14 - 2) {
                        int i17 = i16 + 1;
                        bArr[i16] = (byte) ((cCharAt2 >>> 6) | 960);
                        i16 = i17 + 1;
                        bArr[i17] = (byte) ((cCharAt2 & '?') | 128);
                    } else {
                        if ((cCharAt2 >= 55296 && 57343 >= cCharAt2) || i16 > i14 - 3) {
                            if (i16 > i14 - 4) {
                                if (55296 <= cCharAt2 && cCharAt2 <= 57343 && ((i12 = i15 + 1) == charSequence.length() || !Character.isSurrogatePair(cCharAt2, charSequence.charAt(i12)))) {
                                    throw new UnpairedSurrogateException(i15, length);
                                }
                                throw new ArrayIndexOutOfBoundsException("Failed writing " + cCharAt2 + " at index " + i16);
                            }
                            int i18 = i15 + 1;
                            if (i18 != charSequence.length()) {
                                char cCharAt3 = charSequence.charAt(i18);
                                if (Character.isSurrogatePair(cCharAt2, cCharAt3)) {
                                    int codePoint = Character.toCodePoint(cCharAt2, cCharAt3);
                                    int i19 = i16 + 1;
                                    bArr[i16] = (byte) ((codePoint >>> 18) | PsExtractor.VIDEO_STREAM_MASK);
                                    int i20 = i19 + 1;
                                    bArr[i19] = (byte) (((codePoint >>> 12) & 63) | 128);
                                    int i21 = i20 + 1;
                                    bArr[i20] = (byte) (((codePoint >>> 6) & 63) | 128);
                                    i16 = i21 + 1;
                                    bArr[i21] = (byte) ((codePoint & 63) | 128);
                                    i15 = i18;
                                } else {
                                    i15 = i18;
                                }
                            }
                            throw new UnpairedSurrogateException(i15 - 1, length);
                        }
                        int i22 = i16 + 1;
                        bArr[i16] = (byte) ((cCharAt2 >>> '\f') | 480);
                        int i23 = i22 + 1;
                        bArr[i22] = (byte) (((cCharAt2 >>> 6) & 63) | 128);
                        i11 = i23 + 1;
                        bArr[i23] = (byte) ((cCharAt2 & '?') | 128);
                    }
                    i15++;
                } else {
                    i11 = i16 + 1;
                    bArr[i16] = (byte) cCharAt2;
                }
                i16 = i11;
                i15++;
            }
            return i16;
        }

        @Override // com.google.protobuf.Utf8.Processor
        public void encodeUtf8Direct(CharSequence charSequence, ByteBuffer byteBuffer) {
            encodeUtf8Default(charSequence, byteBuffer);
        }

        /* JADX WARN: Code restructure failed: missing block: B:10:0x0015, code lost:
        
            if (r8[r9] > (-65)) goto L13;
         */
        /* JADX WARN: Code restructure failed: missing block: B:31:0x0046, code lost:
        
            if (r8[r9] > (-65)) goto L32;
         */
        /* JADX WARN: Code restructure failed: missing block: B:52:0x0083, code lost:
        
            if (r8[r7] > (-65)) goto L53;
         */
        @Override // com.google.protobuf.Utf8.Processor
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public int partialIsValidUtf8(int i9, byte[] bArr, int i10, int i11) {
            byte b9;
            int i12;
            int i13;
            if (i9 != 0) {
                if (i10 >= i11) {
                    return i9;
                }
                byte b10 = (byte) i9;
                if (b10 < -32) {
                    if (b10 >= -62) {
                        i13 = i10 + 1;
                    }
                    return -1;
                }
                if (b10 < -16) {
                    byte b11 = (byte) (~(i9 >> 8));
                    if (b11 == 0) {
                        int i14 = i10 + 1;
                        byte b12 = bArr[i10];
                        if (i14 >= i11) {
                            return Utf8.incompleteStateFor(b10, b12);
                        }
                        i10 = i14;
                        b11 = b12;
                    }
                    if (b11 <= -65 && ((b10 != -32 || b11 >= -96) && (b10 != -19 || b11 < -96))) {
                        i13 = i10 + 1;
                    }
                    return -1;
                }
                byte b13 = (byte) (~(i9 >> 8));
                if (b13 == 0) {
                    i12 = i10 + 1;
                    b13 = bArr[i10];
                    if (i12 >= i11) {
                        return Utf8.incompleteStateFor(b10, b13);
                    }
                    b9 = 0;
                } else {
                    b9 = (byte) (i9 >> 16);
                    i12 = i10;
                }
                if (b9 == 0) {
                    int i15 = i12 + 1;
                    byte b14 = bArr[i12];
                    if (i15 >= i11) {
                        return Utf8.incompleteStateFor(b10, b13, b14);
                    }
                    b9 = b14;
                    i12 = i15;
                }
                if (b13 <= -65 && (((b10 << Ascii.f15383FS) + (b13 + 112)) >> 30) == 0 && b9 <= -65) {
                    i10 = i12 + 1;
                }
                return -1;
                i10 = i13;
            }
            return partialIsValidUtf8(bArr, i10, i11);
        }

        @Override // com.google.protobuf.Utf8.Processor
        public int partialIsValidUtf8Direct(int i9, ByteBuffer byteBuffer, int i10, int i11) {
            return partialIsValidUtf8Default(i9, byteBuffer, i10, i11);
        }

        private static int partialIsValidUtf8(byte[] bArr, int i9, int i10) {
            while (i9 < i10 && bArr[i9] >= 0) {
                i9++;
            }
            if (i9 >= i10) {
                return 0;
            }
            return partialIsValidUtf8NonAscii(bArr, i9, i10);
        }
    }
}
