package com.google.i18n.phonenumbers.repackaged.com.google.protobuf.nano;

import com.google.android.exoplayer2.extractor.p037ts.PsExtractor;
import java.io.IOException;
import java.nio.BufferOverflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.ReadOnlyBufferException;

/* loaded from: classes2.dex */
public final class CodedOutputByteBufferNano {
    public static final int LITTLE_ENDIAN_32_SIZE = 4;
    public static final int LITTLE_ENDIAN_64_SIZE = 8;
    private static final int MAX_UTF8_EXPANSION = 3;
    private final ByteBuffer buffer;

    public static class OutOfSpaceException extends IOException {
        private static final long serialVersionUID = -6947486886997889499L;

        public OutOfSpaceException(int i9, int i10) {
            StringBuilder sb = new StringBuilder("CodedOutputStream was writing to a flat byte array and ran out of space (pos ".length() + 31);
            sb.append("CodedOutputStream was writing to a flat byte array and ran out of space (pos ");
            sb.append(i9);
            sb.append(" limit ");
            sb.append(i10);
            sb.append(").");
            super(sb.toString());
        }
    }

    private CodedOutputByteBufferNano(byte[] bArr, int i9, int i10) {
        this(ByteBuffer.wrap(bArr, i9, i10));
    }

    public static int computeBoolSize(int i9, boolean z8) {
        return computeTagSize(i9) + computeBoolSizeNoTag(z8);
    }

    public static int computeBoolSizeNoTag(boolean z8) {
        return 1;
    }

    public static int computeBytesSize(int i9, byte[] bArr) {
        return computeTagSize(i9) + computeBytesSizeNoTag(bArr);
    }

    public static int computeBytesSizeNoTag(byte[] bArr) {
        return computeRawVarint32Size(bArr.length) + bArr.length;
    }

    public static int computeDoubleSize(int i9, double d9) {
        return computeTagSize(i9) + computeDoubleSizeNoTag(d9);
    }

    public static int computeDoubleSizeNoTag(double d9) {
        return 8;
    }

    public static int computeEnumSize(int i9, int i10) {
        return computeTagSize(i9) + computeEnumSizeNoTag(i10);
    }

    public static int computeEnumSizeNoTag(int i9) {
        return computeRawVarint32Size(i9);
    }

    public static int computeFixed32Size(int i9, int i10) {
        return computeTagSize(i9) + computeFixed32SizeNoTag(i10);
    }

    public static int computeFixed32SizeNoTag(int i9) {
        return 4;
    }

    public static int computeFixed64Size(int i9, long j9) {
        return computeTagSize(i9) + computeFixed64SizeNoTag(j9);
    }

    public static int computeFixed64SizeNoTag(long j9) {
        return 8;
    }

    public static int computeFloatSize(int i9, float f9) {
        return computeTagSize(i9) + computeFloatSizeNoTag(f9);
    }

    public static int computeFloatSizeNoTag(float f9) {
        return 4;
    }

    public static int computeGroupSize(int i9, MessageNano messageNano) {
        return (computeTagSize(i9) * 2) + computeGroupSizeNoTag(messageNano);
    }

    public static int computeGroupSizeNoTag(MessageNano messageNano) {
        return messageNano.getSerializedSize();
    }

    public static int computeInt32Size(int i9, int i10) {
        return computeTagSize(i9) + computeInt32SizeNoTag(i10);
    }

    public static int computeInt32SizeNoTag(int i9) {
        if (i9 >= 0) {
            return computeRawVarint32Size(i9);
        }
        return 10;
    }

    public static int computeInt64Size(int i9, long j9) {
        return computeTagSize(i9) + computeInt64SizeNoTag(j9);
    }

    public static int computeInt64SizeNoTag(long j9) {
        return computeRawVarint64Size(j9);
    }

    public static int computeMessageSize(int i9, MessageNano messageNano) {
        return computeTagSize(i9) + computeMessageSizeNoTag(messageNano);
    }

    public static int computeMessageSizeNoTag(MessageNano messageNano) {
        int serializedSize = messageNano.getSerializedSize();
        return computeRawVarint32Size(serializedSize) + serializedSize;
    }

    public static int computeRawVarint32Size(int i9) {
        if ((i9 & (-128)) == 0) {
            return 1;
        }
        if ((i9 & (-16384)) == 0) {
            return 2;
        }
        if (((-2097152) & i9) == 0) {
            return 3;
        }
        return (i9 & (-268435456)) == 0 ? 4 : 5;
    }

    public static int computeRawVarint64Size(long j9) {
        if (((-128) & j9) == 0) {
            return 1;
        }
        if (((-16384) & j9) == 0) {
            return 2;
        }
        if (((-2097152) & j9) == 0) {
            return 3;
        }
        if (((-268435456) & j9) == 0) {
            return 4;
        }
        if (((-34359738368L) & j9) == 0) {
            return 5;
        }
        if (((-4398046511104L) & j9) == 0) {
            return 6;
        }
        if (((-562949953421312L) & j9) == 0) {
            return 7;
        }
        if (((-72057594037927936L) & j9) == 0) {
            return 8;
        }
        return (j9 & Long.MIN_VALUE) == 0 ? 9 : 10;
    }

    public static int computeSFixed32Size(int i9, int i10) {
        return computeTagSize(i9) + computeSFixed32SizeNoTag(i10);
    }

    public static int computeSFixed32SizeNoTag(int i9) {
        return 4;
    }

    public static int computeSFixed64Size(int i9, long j9) {
        return computeTagSize(i9) + computeSFixed64SizeNoTag(j9);
    }

    public static int computeSFixed64SizeNoTag(long j9) {
        return 8;
    }

    public static int computeSInt32Size(int i9, int i10) {
        return computeTagSize(i9) + computeSInt32SizeNoTag(i10);
    }

    public static int computeSInt32SizeNoTag(int i9) {
        return computeRawVarint32Size(encodeZigZag32(i9));
    }

    public static int computeSInt64Size(int i9, long j9) {
        return computeTagSize(i9) + computeSInt64SizeNoTag(j9);
    }

    public static int computeSInt64SizeNoTag(long j9) {
        return computeRawVarint64Size(encodeZigZag64(j9));
    }

    public static int computeStringSize(int i9, String str) {
        return computeTagSize(i9) + computeStringSizeNoTag(str);
    }

    public static int computeStringSizeNoTag(String str) {
        int iEncodedLength = encodedLength(str);
        return computeRawVarint32Size(iEncodedLength) + iEncodedLength;
    }

    public static int computeTagSize(int i9) {
        return computeRawVarint32Size(WireFormatNano.makeTag(i9, 0));
    }

    public static int computeUInt32Size(int i9, int i10) {
        return computeTagSize(i9) + computeUInt32SizeNoTag(i10);
    }

    public static int computeUInt32SizeNoTag(int i9) {
        return computeRawVarint32Size(i9);
    }

    public static int computeUInt64Size(int i9, long j9) {
        return computeTagSize(i9) + computeUInt64SizeNoTag(j9);
    }

    public static int computeUInt64SizeNoTag(long j9) {
        return computeRawVarint64Size(j9);
    }

    private static void encode(CharSequence charSequence, ByteBuffer byteBuffer) {
        if (byteBuffer.isReadOnly()) {
            throw new ReadOnlyBufferException();
        }
        if (!byteBuffer.hasArray()) {
            encodeDirect(charSequence, byteBuffer);
            return;
        }
        try {
            byteBuffer.position(encode(charSequence, byteBuffer.array(), byteBuffer.arrayOffset() + byteBuffer.position(), byteBuffer.remaining()) - byteBuffer.arrayOffset());
        } catch (ArrayIndexOutOfBoundsException e9) {
            BufferOverflowException bufferOverflowException = new BufferOverflowException();
            bufferOverflowException.initCause(e9);
            throw bufferOverflowException;
        }
    }

    private static void encodeDirect(CharSequence charSequence, ByteBuffer byteBuffer) {
        int length = charSequence.length();
        int i9 = 0;
        while (i9 < length) {
            char cCharAt = charSequence.charAt(i9);
            if (cCharAt < 128) {
                byteBuffer.put((byte) cCharAt);
            } else if (cCharAt < 2048) {
                byteBuffer.put((byte) ((cCharAt >>> 6) | 960));
                byteBuffer.put((byte) ((cCharAt & '?') | 128));
            } else {
                if (cCharAt >= 55296 && 57343 >= cCharAt) {
                    int i10 = i9 + 1;
                    if (i10 != charSequence.length()) {
                        char cCharAt2 = charSequence.charAt(i10);
                        if (Character.isSurrogatePair(cCharAt, cCharAt2)) {
                            int codePoint = Character.toCodePoint(cCharAt, cCharAt2);
                            byteBuffer.put((byte) ((codePoint >>> 18) | PsExtractor.VIDEO_STREAM_MASK));
                            byteBuffer.put((byte) (((codePoint >>> 12) & 63) | 128));
                            byteBuffer.put((byte) (((codePoint >>> 6) & 63) | 128));
                            byteBuffer.put((byte) ((codePoint & 63) | 128));
                            i9 = i10;
                        } else {
                            i9 = i10;
                        }
                    }
                    StringBuilder sb = new StringBuilder(39);
                    sb.append("Unpaired surrogate at index ");
                    sb.append(i9 - 1);
                    throw new IllegalArgumentException(sb.toString());
                }
                byteBuffer.put((byte) ((cCharAt >>> '\f') | 480));
                byteBuffer.put((byte) (((cCharAt >>> 6) & 63) | 128));
                byteBuffer.put((byte) ((cCharAt & '?') | 128));
            }
            i9++;
        }
    }

    public static int encodeZigZag32(int i9) {
        return (i9 >> 31) ^ (i9 << 1);
    }

    public static long encodeZigZag64(long j9) {
        return (j9 >> 63) ^ (j9 << 1);
    }

    private static int encodedLength(CharSequence charSequence) {
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
        long j9 = iEncodedLengthGeneral + 4294967296L;
        StringBuilder sb = new StringBuilder(54);
        sb.append("UTF-8 length does not fit in int: ");
        sb.append(j9);
        throw new IllegalArgumentException(sb.toString());
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
                        StringBuilder sb = new StringBuilder(39);
                        sb.append("Unpaired surrogate at index ");
                        sb.append(i9);
                        throw new IllegalArgumentException(sb.toString());
                    }
                    i9++;
                }
            }
            i9++;
        }
        return i10;
    }

    public static CodedOutputByteBufferNano newInstance(byte[] bArr) {
        return newInstance(bArr, 0, bArr.length);
    }

    public void checkNoSpaceLeft() {
        if (spaceLeft() != 0) {
            throw new IllegalStateException("Did not write as much data as expected.");
        }
    }

    public int position() {
        return this.buffer.position();
    }

    public void reset() {
        this.buffer.clear();
    }

    public int spaceLeft() {
        return this.buffer.remaining();
    }

    public void writeBool(int i9, boolean z8) throws OutOfSpaceException {
        writeTag(i9, 0);
        writeBoolNoTag(z8);
    }

    public void writeBoolNoTag(boolean z8) throws OutOfSpaceException {
        writeRawByte(z8 ? 1 : 0);
    }

    public void writeBytes(int i9, byte[] bArr) throws OutOfSpaceException {
        writeTag(i9, 2);
        writeBytesNoTag(bArr);
    }

    public void writeBytesNoTag(byte[] bArr) throws OutOfSpaceException {
        writeRawVarint32(bArr.length);
        writeRawBytes(bArr);
    }

    public void writeDouble(int i9, double d9) throws OutOfSpaceException {
        writeTag(i9, 1);
        writeDoubleNoTag(d9);
    }

    public void writeDoubleNoTag(double d9) throws OutOfSpaceException {
        writeRawLittleEndian64(Double.doubleToLongBits(d9));
    }

    public void writeEnum(int i9, int i10) throws OutOfSpaceException {
        writeTag(i9, 0);
        writeEnumNoTag(i10);
    }

    public void writeEnumNoTag(int i9) throws OutOfSpaceException {
        writeRawVarint32(i9);
    }

    public void writeFixed32(int i9, int i10) throws OutOfSpaceException {
        writeTag(i9, 5);
        writeFixed32NoTag(i10);
    }

    public void writeFixed32NoTag(int i9) throws OutOfSpaceException {
        writeRawLittleEndian32(i9);
    }

    public void writeFixed64(int i9, long j9) throws OutOfSpaceException {
        writeTag(i9, 1);
        writeFixed64NoTag(j9);
    }

    public void writeFixed64NoTag(long j9) throws OutOfSpaceException {
        writeRawLittleEndian64(j9);
    }

    public void writeFloat(int i9, float f9) throws OutOfSpaceException {
        writeTag(i9, 5);
        writeFloatNoTag(f9);
    }

    public void writeFloatNoTag(float f9) throws OutOfSpaceException {
        writeRawLittleEndian32(Float.floatToIntBits(f9));
    }

    public void writeGroup(int i9, MessageNano messageNano) throws OutOfSpaceException {
        writeTag(i9, 3);
        writeGroupNoTag(messageNano);
        writeTag(i9, 4);
    }

    public void writeGroupNoTag(MessageNano messageNano) {
        messageNano.writeTo(this);
    }

    public void writeInt32(int i9, int i10) throws OutOfSpaceException {
        writeTag(i9, 0);
        writeInt32NoTag(i10);
    }

    public void writeInt32NoTag(int i9) throws OutOfSpaceException {
        if (i9 >= 0) {
            writeRawVarint32(i9);
        } else {
            writeRawVarint64(i9);
        }
    }

    public void writeInt64(int i9, long j9) throws OutOfSpaceException {
        writeTag(i9, 0);
        writeInt64NoTag(j9);
    }

    public void writeInt64NoTag(long j9) throws OutOfSpaceException {
        writeRawVarint64(j9);
    }

    public void writeMessage(int i9, MessageNano messageNano) throws OutOfSpaceException {
        writeTag(i9, 2);
        writeMessageNoTag(messageNano);
    }

    public void writeMessageNoTag(MessageNano messageNano) throws OutOfSpaceException {
        writeRawVarint32(messageNano.getCachedSize());
        messageNano.writeTo(this);
    }

    public void writeRawByte(byte b9) throws OutOfSpaceException {
        if (!this.buffer.hasRemaining()) {
            throw new OutOfSpaceException(this.buffer.position(), this.buffer.limit());
        }
        this.buffer.put(b9);
    }

    public void writeRawBytes(byte[] bArr) throws OutOfSpaceException {
        writeRawBytes(bArr, 0, bArr.length);
    }

    public void writeRawLittleEndian32(int i9) throws OutOfSpaceException {
        if (this.buffer.remaining() < 4) {
            throw new OutOfSpaceException(this.buffer.position(), this.buffer.limit());
        }
        this.buffer.putInt(i9);
    }

    public void writeRawLittleEndian64(long j9) throws OutOfSpaceException {
        if (this.buffer.remaining() < 8) {
            throw new OutOfSpaceException(this.buffer.position(), this.buffer.limit());
        }
        this.buffer.putLong(j9);
    }

    public void writeRawVarint32(int i9) throws OutOfSpaceException {
        while ((i9 & (-128)) != 0) {
            writeRawByte((i9 & 127) | 128);
            i9 >>>= 7;
        }
        writeRawByte(i9);
    }

    public void writeRawVarint64(long j9) throws OutOfSpaceException {
        while (((-128) & j9) != 0) {
            writeRawByte((((int) j9) & 127) | 128);
            j9 >>>= 7;
        }
        writeRawByte((int) j9);
    }

    public void writeSFixed32(int i9, int i10) throws OutOfSpaceException {
        writeTag(i9, 5);
        writeSFixed32NoTag(i10);
    }

    public void writeSFixed32NoTag(int i9) throws OutOfSpaceException {
        writeRawLittleEndian32(i9);
    }

    public void writeSFixed64(int i9, long j9) throws OutOfSpaceException {
        writeTag(i9, 1);
        writeSFixed64NoTag(j9);
    }

    public void writeSFixed64NoTag(long j9) throws OutOfSpaceException {
        writeRawLittleEndian64(j9);
    }

    public void writeSInt32(int i9, int i10) throws OutOfSpaceException {
        writeTag(i9, 0);
        writeSInt32NoTag(i10);
    }

    public void writeSInt32NoTag(int i9) throws OutOfSpaceException {
        writeRawVarint32(encodeZigZag32(i9));
    }

    public void writeSInt64(int i9, long j9) throws OutOfSpaceException {
        writeTag(i9, 0);
        writeSInt64NoTag(j9);
    }

    public void writeSInt64NoTag(long j9) throws OutOfSpaceException {
        writeRawVarint64(encodeZigZag64(j9));
    }

    public void writeString(int i9, String str) throws OutOfSpaceException {
        writeTag(i9, 2);
        writeStringNoTag(str);
    }

    public void writeStringNoTag(String str) throws OutOfSpaceException {
        try {
            int iComputeRawVarint32Size = computeRawVarint32Size(str.length());
            if (iComputeRawVarint32Size != computeRawVarint32Size(str.length() * 3)) {
                writeRawVarint32(encodedLength(str));
                encode(str, this.buffer);
                return;
            }
            int iPosition = this.buffer.position();
            if (this.buffer.remaining() < iComputeRawVarint32Size) {
                throw new OutOfSpaceException(iPosition + iComputeRawVarint32Size, this.buffer.limit());
            }
            this.buffer.position(iPosition + iComputeRawVarint32Size);
            encode(str, this.buffer);
            int iPosition2 = this.buffer.position();
            this.buffer.position(iPosition);
            writeRawVarint32((iPosition2 - iPosition) - iComputeRawVarint32Size);
            this.buffer.position(iPosition2);
        } catch (BufferOverflowException e9) {
            OutOfSpaceException outOfSpaceException = new OutOfSpaceException(this.buffer.position(), this.buffer.limit());
            outOfSpaceException.initCause(e9);
            throw outOfSpaceException;
        }
    }

    public void writeTag(int i9, int i10) throws OutOfSpaceException {
        writeRawVarint32(WireFormatNano.makeTag(i9, i10));
    }

    public void writeUInt32(int i9, int i10) throws OutOfSpaceException {
        writeTag(i9, 0);
        writeUInt32NoTag(i10);
    }

    public void writeUInt32NoTag(int i9) throws OutOfSpaceException {
        writeRawVarint32(i9);
    }

    public void writeUInt64(int i9, long j9) throws OutOfSpaceException {
        writeTag(i9, 0);
        writeUInt64NoTag(j9);
    }

    public void writeUInt64NoTag(long j9) throws OutOfSpaceException {
        writeRawVarint64(j9);
    }

    private CodedOutputByteBufferNano(ByteBuffer byteBuffer) {
        this.buffer = byteBuffer;
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
    }

    public static int computeBytesSize(int i9, int i10) {
        return computeTagSize(i9) + computeBytesSizeNoTag(i10);
    }

    public static int computeBytesSizeNoTag(int i9) {
        return computeRawVarint32Size(i9) + i9;
    }

    public static CodedOutputByteBufferNano newInstance(byte[] bArr, int i9, int i10) {
        return new CodedOutputByteBufferNano(bArr, i9, i10);
    }

    public void writeRawBytes(byte[] bArr, int i9, int i10) throws OutOfSpaceException {
        if (this.buffer.remaining() < i10) {
            throw new OutOfSpaceException(this.buffer.position(), this.buffer.limit());
        }
        this.buffer.put(bArr, i9, i10);
    }

    public void writeBytes(int i9, byte[] bArr, int i10, int i11) throws OutOfSpaceException {
        writeTag(i9, 2);
        writeBytesNoTag(bArr, i10, i11);
    }

    public void writeBytesNoTag(byte[] bArr, int i9, int i10) throws OutOfSpaceException {
        writeRawVarint32(i10);
        writeRawBytes(bArr, i9, i10);
    }

    public void writeRawByte(int i9) throws OutOfSpaceException {
        writeRawByte((byte) i9);
    }

    private static int encode(CharSequence charSequence, byte[] bArr, int i9, int i10) {
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
                        if (i16 <= i14 - 4) {
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
                            StringBuilder sb = new StringBuilder(39);
                            sb.append("Unpaired surrogate at index ");
                            sb.append(i15 - 1);
                            throw new IllegalArgumentException(sb.toString());
                        }
                        if (55296 <= cCharAt2 && cCharAt2 <= 57343 && ((i12 = i15 + 1) == charSequence.length() || !Character.isSurrogatePair(cCharAt2, charSequence.charAt(i12)))) {
                            StringBuilder sb2 = new StringBuilder(39);
                            sb2.append("Unpaired surrogate at index ");
                            sb2.append(i15);
                            throw new IllegalArgumentException(sb2.toString());
                        }
                        StringBuilder sb3 = new StringBuilder(37);
                        sb3.append("Failed writing ");
                        sb3.append(cCharAt2);
                        sb3.append(" at index ");
                        sb3.append(i16);
                        throw new ArrayIndexOutOfBoundsException(sb3.toString());
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
}
