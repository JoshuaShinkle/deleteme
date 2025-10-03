package com.google.i18n.phonenumbers.repackaged.com.google.protobuf.micro;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

/* loaded from: classes2.dex */
public final class CodedOutputStreamMicro {
    public static final int DEFAULT_BUFFER_SIZE = 4096;
    public static final int LITTLE_ENDIAN_32_SIZE = 4;
    public static final int LITTLE_ENDIAN_64_SIZE = 8;
    private final byte[] buffer;
    private final int limit;
    private final OutputStream output;
    private int position;

    public static class OutOfSpaceException extends IOException {
        private static final long serialVersionUID = -6947486886997889499L;

        public OutOfSpaceException() {
            super("CodedOutputStream was writing to a flat byte array and ran out of space.");
        }
    }

    private CodedOutputStreamMicro(byte[] bArr, int i9, int i10) {
        this.output = null;
        this.buffer = bArr;
        this.position = i9;
        this.limit = i9 + i10;
    }

    public static int computeBoolSize(int i9, boolean z8) {
        return computeTagSize(i9) + computeBoolSizeNoTag(z8);
    }

    public static int computeBoolSizeNoTag(boolean z8) {
        return 1;
    }

    public static int computeByteArraySize(int i9, byte[] bArr) {
        return computeTagSize(i9) + computeByteArraySizeNoTag(bArr);
    }

    public static int computeByteArraySizeNoTag(byte[] bArr) {
        return computeRawVarint32Size(bArr.length) + bArr.length;
    }

    public static int computeBytesSize(int i9, ByteStringMicro byteStringMicro) {
        return computeTagSize(i9) + computeBytesSizeNoTag(byteStringMicro);
    }

    public static int computeBytesSizeNoTag(ByteStringMicro byteStringMicro) {
        return computeRawVarint32Size(byteStringMicro.size()) + byteStringMicro.size();
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

    public static int computeGroupSize(int i9, MessageMicro messageMicro) {
        return (computeTagSize(i9) * 2) + computeGroupSizeNoTag(messageMicro);
    }

    public static int computeGroupSizeNoTag(MessageMicro messageMicro) {
        return messageMicro.getSerializedSize();
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

    public static int computeMessageSize(int i9, MessageMicro messageMicro) {
        return computeTagSize(i9) + computeMessageSizeNoTag(messageMicro);
    }

    public static int computeMessageSizeNoTag(MessageMicro messageMicro) {
        int serializedSize = messageMicro.getSerializedSize();
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

    public static int computeStringSizeNoTag(String str) throws UnsupportedEncodingException {
        try {
            byte[] bytes = str.getBytes("UTF-8");
            return computeRawVarint32Size(bytes.length) + bytes.length;
        } catch (UnsupportedEncodingException unused) {
            throw new RuntimeException("UTF-8 not supported.");
        }
    }

    public static int computeTagSize(int i9) {
        return computeRawVarint32Size(WireFormatMicro.makeTag(i9, 0));
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

    public static int encodeZigZag32(int i9) {
        return (i9 >> 31) ^ (i9 << 1);
    }

    public static long encodeZigZag64(long j9) {
        return (j9 >> 63) ^ (j9 << 1);
    }

    public static CodedOutputStreamMicro newInstance(OutputStream outputStream) {
        return newInstance(outputStream, 4096);
    }

    private void refreshBuffer() throws IOException {
        OutputStream outputStream = this.output;
        if (outputStream == null) {
            throw new OutOfSpaceException();
        }
        outputStream.write(this.buffer, 0, this.position);
        this.position = 0;
    }

    public void checkNoSpaceLeft() {
        if (spaceLeft() != 0) {
            throw new IllegalStateException("Did not write as much data as expected.");
        }
    }

    public void flush() throws IOException {
        if (this.output != null) {
            refreshBuffer();
        }
    }

    public int spaceLeft() {
        if (this.output == null) {
            return this.limit - this.position;
        }
        throw new UnsupportedOperationException("spaceLeft() can only be called on CodedOutputStreams that are writing to a flat array.");
    }

    public void writeBool(int i9, boolean z8) throws IOException {
        writeTag(i9, 0);
        writeBoolNoTag(z8);
    }

    public void writeBoolNoTag(boolean z8) throws IOException {
        writeRawByte(z8 ? 1 : 0);
    }

    public void writeByteArray(int i9, byte[] bArr) throws IOException {
        writeTag(i9, 2);
        writeByteArrayNoTag(bArr);
    }

    public void writeByteArrayNoTag(byte[] bArr) throws IOException {
        writeRawVarint32(bArr.length);
        writeRawBytes(bArr);
    }

    public void writeBytes(int i9, ByteStringMicro byteStringMicro) throws IOException {
        writeTag(i9, 2);
        writeBytesNoTag(byteStringMicro);
    }

    public void writeBytesNoTag(ByteStringMicro byteStringMicro) throws IOException {
        byte[] byteArray = byteStringMicro.toByteArray();
        writeRawVarint32(byteArray.length);
        writeRawBytes(byteArray);
    }

    public void writeDouble(int i9, double d9) throws IOException {
        writeTag(i9, 1);
        writeDoubleNoTag(d9);
    }

    public void writeDoubleNoTag(double d9) throws IOException {
        writeRawLittleEndian64(Double.doubleToLongBits(d9));
    }

    public void writeEnum(int i9, int i10) throws IOException {
        writeTag(i9, 0);
        writeEnumNoTag(i10);
    }

    public void writeEnumNoTag(int i9) throws IOException {
        writeRawVarint32(i9);
    }

    public void writeFixed32(int i9, int i10) throws IOException {
        writeTag(i9, 5);
        writeFixed32NoTag(i10);
    }

    public void writeFixed32NoTag(int i9) throws IOException {
        writeRawLittleEndian32(i9);
    }

    public void writeFixed64(int i9, long j9) throws IOException {
        writeTag(i9, 1);
        writeFixed64NoTag(j9);
    }

    public void writeFixed64NoTag(long j9) throws IOException {
        writeRawLittleEndian64(j9);
    }

    public void writeFloat(int i9, float f9) throws IOException {
        writeTag(i9, 5);
        writeFloatNoTag(f9);
    }

    public void writeFloatNoTag(float f9) throws IOException {
        writeRawLittleEndian32(Float.floatToIntBits(f9));
    }

    public void writeGroup(int i9, MessageMicro messageMicro) throws IOException {
        writeTag(i9, 3);
        writeGroupNoTag(messageMicro);
        writeTag(i9, 4);
    }

    public void writeGroupNoTag(MessageMicro messageMicro) {
        messageMicro.writeTo(this);
    }

    public void writeInt32(int i9, int i10) throws IOException {
        writeTag(i9, 0);
        writeInt32NoTag(i10);
    }

    public void writeInt32NoTag(int i9) throws IOException {
        if (i9 >= 0) {
            writeRawVarint32(i9);
        } else {
            writeRawVarint64(i9);
        }
    }

    public void writeInt64(int i9, long j9) throws IOException {
        writeTag(i9, 0);
        writeInt64NoTag(j9);
    }

    public void writeInt64NoTag(long j9) throws IOException {
        writeRawVarint64(j9);
    }

    public void writeMessage(int i9, MessageMicro messageMicro) throws IOException {
        writeTag(i9, 2);
        writeMessageNoTag(messageMicro);
    }

    public void writeMessageNoTag(MessageMicro messageMicro) throws IOException {
        writeRawVarint32(messageMicro.getCachedSize());
        messageMicro.writeTo(this);
    }

    public void writeRawByte(byte b9) throws IOException {
        if (this.position == this.limit) {
            refreshBuffer();
        }
        byte[] bArr = this.buffer;
        int i9 = this.position;
        this.position = i9 + 1;
        bArr[i9] = b9;
    }

    public void writeRawBytes(byte[] bArr) throws IOException {
        writeRawBytes(bArr, 0, bArr.length);
    }

    public void writeRawLittleEndian32(int i9) throws IOException {
        writeRawByte(i9 & 255);
        writeRawByte((i9 >> 8) & 255);
        writeRawByte((i9 >> 16) & 255);
        writeRawByte((i9 >> 24) & 255);
    }

    public void writeRawLittleEndian64(long j9) throws IOException {
        writeRawByte(((int) j9) & 255);
        writeRawByte(((int) (j9 >> 8)) & 255);
        writeRawByte(((int) (j9 >> 16)) & 255);
        writeRawByte(((int) (j9 >> 24)) & 255);
        writeRawByte(((int) (j9 >> 32)) & 255);
        writeRawByte(((int) (j9 >> 40)) & 255);
        writeRawByte(((int) (j9 >> 48)) & 255);
        writeRawByte(((int) (j9 >> 56)) & 255);
    }

    public void writeRawVarint32(int i9) throws IOException {
        while ((i9 & (-128)) != 0) {
            writeRawByte((i9 & 127) | 128);
            i9 >>>= 7;
        }
        writeRawByte(i9);
    }

    public void writeRawVarint64(long j9) throws IOException {
        while (((-128) & j9) != 0) {
            writeRawByte((((int) j9) & 127) | 128);
            j9 >>>= 7;
        }
        writeRawByte((int) j9);
    }

    public void writeSFixed32(int i9, int i10) throws IOException {
        writeTag(i9, 5);
        writeSFixed32NoTag(i10);
    }

    public void writeSFixed32NoTag(int i9) throws IOException {
        writeRawLittleEndian32(i9);
    }

    public void writeSFixed64(int i9, long j9) throws IOException {
        writeTag(i9, 1);
        writeSFixed64NoTag(j9);
    }

    public void writeSFixed64NoTag(long j9) throws IOException {
        writeRawLittleEndian64(j9);
    }

    public void writeSInt32(int i9, int i10) throws IOException {
        writeTag(i9, 0);
        writeSInt32NoTag(i10);
    }

    public void writeSInt32NoTag(int i9) throws IOException {
        writeRawVarint32(encodeZigZag32(i9));
    }

    public void writeSInt64(int i9, long j9) throws IOException {
        writeTag(i9, 0);
        writeSInt64NoTag(j9);
    }

    public void writeSInt64NoTag(long j9) throws IOException {
        writeRawVarint64(encodeZigZag64(j9));
    }

    public void writeString(int i9, String str) throws IOException {
        writeTag(i9, 2);
        writeStringNoTag(str);
    }

    public void writeStringNoTag(String str) throws IOException {
        byte[] bytes = str.getBytes("UTF-8");
        writeRawVarint32(bytes.length);
        writeRawBytes(bytes);
    }

    public void writeTag(int i9, int i10) throws IOException {
        writeRawVarint32(WireFormatMicro.makeTag(i9, i10));
    }

    public void writeUInt32(int i9, int i10) throws IOException {
        writeTag(i9, 0);
        writeUInt32NoTag(i10);
    }

    public void writeUInt32NoTag(int i9) throws IOException {
        writeRawVarint32(i9);
    }

    public void writeUInt64(int i9, long j9) throws IOException {
        writeTag(i9, 0);
        writeUInt64NoTag(j9);
    }

    public void writeUInt64NoTag(long j9) throws IOException {
        writeRawVarint64(j9);
    }

    public static CodedOutputStreamMicro newInstance(OutputStream outputStream, int i9) {
        return new CodedOutputStreamMicro(outputStream, new byte[i9]);
    }

    public void writeRawBytes(byte[] bArr, int i9, int i10) throws IOException {
        int i11 = this.limit;
        int i12 = this.position;
        if (i11 - i12 >= i10) {
            System.arraycopy(bArr, i9, this.buffer, i12, i10);
            this.position += i10;
            return;
        }
        int i13 = i11 - i12;
        System.arraycopy(bArr, i9, this.buffer, i12, i13);
        int i14 = i9 + i13;
        int i15 = i10 - i13;
        this.position = this.limit;
        refreshBuffer();
        if (i15 > this.limit) {
            this.output.write(bArr, i14, i15);
        } else {
            System.arraycopy(bArr, i14, this.buffer, 0, i15);
            this.position = i15;
        }
    }

    public static CodedOutputStreamMicro newInstance(byte[] bArr) {
        return newInstance(bArr, 0, bArr.length);
    }

    public static CodedOutputStreamMicro newInstance(byte[] bArr, int i9, int i10) {
        return new CodedOutputStreamMicro(bArr, i9, i10);
    }

    public void writeRawByte(int i9) throws IOException {
        writeRawByte((byte) i9);
    }

    private CodedOutputStreamMicro(OutputStream outputStream, byte[] bArr) {
        this.output = outputStream;
        this.buffer = bArr;
        this.position = 0;
        this.limit = bArr.length;
    }
}
