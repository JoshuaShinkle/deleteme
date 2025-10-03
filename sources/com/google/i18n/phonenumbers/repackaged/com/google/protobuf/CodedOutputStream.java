package com.google.i18n.phonenumbers.repackaged.com.google.protobuf;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

/* loaded from: classes2.dex */
public final class CodedOutputStream {
    public static final int DEFAULT_BUFFER_SIZE = 4096;
    public static final int LITTLE_ENDIAN_32_SIZE = 4;
    public static final int LITTLE_ENDIAN_64_SIZE = 8;
    private final byte[] buffer;
    private final int limit;
    private final OutputStream output;
    private int position;
    private int totalBytesWritten;

    public static class ByteBufferOutputStream extends OutputStream {
        private final ByteBuffer byteBuffer;

        public ByteBufferOutputStream(ByteBuffer byteBuffer) {
            this.byteBuffer = byteBuffer;
        }

        @Override // java.io.OutputStream
        public void write(int i9) {
            this.byteBuffer.put((byte) i9);
        }

        @Override // java.io.OutputStream
        public void write(byte[] bArr, int i9, int i10) {
            this.byteBuffer.put(bArr, i9, i10);
        }
    }

    public static class OutOfSpaceException extends IOException {
        private static final long serialVersionUID = -6947486886997889499L;

        public OutOfSpaceException() {
            super("CodedOutputStream was writing to a flat byte array and ran out of space.");
        }
    }

    private CodedOutputStream(byte[] bArr, int i9, int i10) {
        this.totalBytesWritten = 0;
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

    public static int computeByteBufferSize(int i9, ByteBuffer byteBuffer) {
        return computeTagSize(i9) + computeByteBufferSizeNoTag(byteBuffer);
    }

    public static int computeByteBufferSizeNoTag(ByteBuffer byteBuffer) {
        return computeRawVarint32Size(byteBuffer.capacity()) + byteBuffer.capacity();
    }

    public static int computeBytesSize(int i9, ByteString byteString) {
        return computeTagSize(i9) + computeBytesSizeNoTag(byteString);
    }

    public static int computeBytesSizeNoTag(ByteString byteString) {
        return computeRawVarint32Size(byteString.size()) + byteString.size();
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
        return computeInt32SizeNoTag(i9);
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

    public static int computeGroupSize(int i9, MessageLite messageLite) {
        return (computeTagSize(i9) * 2) + computeGroupSizeNoTag(messageLite);
    }

    public static int computeGroupSizeNoTag(MessageLite messageLite) {
        return messageLite.getSerializedSize();
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

    public static int computeLazyFieldMessageSetExtensionSize(int i9, LazyFieldLite lazyFieldLite) {
        return (computeTagSize(1) * 2) + computeUInt32Size(2, i9) + computeLazyFieldSize(3, lazyFieldLite);
    }

    public static int computeLazyFieldSize(int i9, LazyFieldLite lazyFieldLite) {
        return computeTagSize(i9) + computeLazyFieldSizeNoTag(lazyFieldLite);
    }

    public static int computeLazyFieldSizeNoTag(LazyFieldLite lazyFieldLite) {
        int serializedSize = lazyFieldLite.getSerializedSize();
        return computeRawVarint32Size(serializedSize) + serializedSize;
    }

    public static int computeMessageSetExtensionSize(int i9, MessageLite messageLite) {
        return (computeTagSize(1) * 2) + computeUInt32Size(2, i9) + computeMessageSize(3, messageLite);
    }

    public static int computeMessageSize(int i9, MessageLite messageLite) {
        return computeTagSize(i9) + computeMessageSizeNoTag(messageLite);
    }

    public static int computeMessageSizeNoTag(MessageLite messageLite) {
        int serializedSize = messageLite.getSerializedSize();
        return computeRawVarint32Size(serializedSize) + serializedSize;
    }

    public static int computePreferredBufferSize(int i9) {
        if (i9 > 4096) {
            return 4096;
        }
        return i9;
    }

    public static int computeRawMessageSetExtensionSize(int i9, ByteString byteString) {
        return (computeTagSize(1) * 2) + computeUInt32Size(2, i9) + computeBytesSize(3, byteString);
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
        } catch (UnsupportedEncodingException e9) {
            throw new RuntimeException("UTF-8 not supported.", e9);
        }
    }

    public static int computeTagSize(int i9) {
        return computeRawVarint32Size(WireFormat.makeTag(i9, 0));
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

    @Deprecated
    public static int computeUnknownGroupSize(int i9, MessageLite messageLite) {
        return computeGroupSize(i9, messageLite);
    }

    @Deprecated
    public static int computeUnknownGroupSizeNoTag(MessageLite messageLite) {
        return computeGroupSizeNoTag(messageLite);
    }

    public static int encodeZigZag32(int i9) {
        return (i9 >> 31) ^ (i9 << 1);
    }

    public static long encodeZigZag64(long j9) {
        return (j9 >> 63) ^ (j9 << 1);
    }

    public static CodedOutputStream newInstance(OutputStream outputStream) {
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

    private void writeRawBytesInternal(ByteBuffer byteBuffer) throws IOException {
        int iRemaining = byteBuffer.remaining();
        int i9 = this.limit;
        int i10 = this.position;
        if (i9 - i10 >= iRemaining) {
            byteBuffer.get(this.buffer, i10, iRemaining);
            this.position += iRemaining;
            this.totalBytesWritten += iRemaining;
            return;
        }
        int i11 = i9 - i10;
        byteBuffer.get(this.buffer, i10, i11);
        int i12 = iRemaining - i11;
        this.position = this.limit;
        this.totalBytesWritten += i11;
        refreshBuffer();
        while (true) {
            int i13 = this.limit;
            if (i12 <= i13) {
                byteBuffer.get(this.buffer, 0, i12);
                this.position = i12;
                this.totalBytesWritten += i12;
                return;
            } else {
                byteBuffer.get(this.buffer, 0, i13);
                this.output.write(this.buffer, 0, this.limit);
                int i14 = this.limit;
                i12 -= i14;
                this.totalBytesWritten += i14;
            }
        }
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

    public int getTotalBytesWritten() {
        return this.totalBytesWritten;
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

    public void writeByteBuffer(int i9, ByteBuffer byteBuffer) throws IOException {
        writeTag(i9, 2);
        writeByteBufferNoTag(byteBuffer);
    }

    public void writeByteBufferNoTag(ByteBuffer byteBuffer) throws IOException {
        writeRawVarint32(byteBuffer.capacity());
        writeRawBytes(byteBuffer);
    }

    public void writeBytes(int i9, ByteString byteString) {
        writeTag(i9, 2);
        writeBytesNoTag(byteString);
    }

    public void writeBytesNoTag(ByteString byteString) {
        writeRawVarint32(byteString.size());
        writeRawBytes(byteString);
    }

    public void writeDouble(int i9, double d9) throws IOException {
        writeTag(i9, 1);
        writeDoubleNoTag(d9);
    }

    public void writeDoubleNoTag(double d9) throws IOException {
        writeRawLittleEndian64(Double.doubleToRawLongBits(d9));
    }

    public void writeEnum(int i9, int i10) throws IOException {
        writeTag(i9, 0);
        writeEnumNoTag(i10);
    }

    public void writeEnumNoTag(int i9) throws IOException {
        writeInt32NoTag(i9);
    }

    public void writeFixed32(int i9, int i10) {
        writeTag(i9, 5);
        writeFixed32NoTag(i10);
    }

    public void writeFixed32NoTag(int i9) {
        writeRawLittleEndian32(i9);
    }

    public void writeFixed64(int i9, long j9) {
        writeTag(i9, 1);
        writeFixed64NoTag(j9);
    }

    public void writeFixed64NoTag(long j9) {
        writeRawLittleEndian64(j9);
    }

    public void writeFloat(int i9, float f9) throws IOException {
        writeTag(i9, 5);
        writeFloatNoTag(f9);
    }

    public void writeFloatNoTag(float f9) throws IOException {
        writeRawLittleEndian32(Float.floatToRawIntBits(f9));
    }

    public void writeGroup(int i9, MessageLite messageLite) {
        writeTag(i9, 3);
        writeGroupNoTag(messageLite);
        writeTag(i9, 4);
    }

    public void writeGroupNoTag(MessageLite messageLite) {
        messageLite.writeTo(this);
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

    public void writeMessage(int i9, MessageLite messageLite) {
        writeTag(i9, 2);
        writeMessageNoTag(messageLite);
    }

    public void writeMessageNoTag(MessageLite messageLite) {
        writeRawVarint32(messageLite.getSerializedSize());
        messageLite.writeTo(this);
    }

    public void writeMessageSetExtension(int i9, MessageLite messageLite) {
        writeTag(1, 3);
        writeUInt32(2, i9);
        writeMessage(3, messageLite);
        writeTag(1, 4);
    }

    public void writeRawByte(byte b9) throws IOException {
        if (this.position == this.limit) {
            refreshBuffer();
        }
        byte[] bArr = this.buffer;
        int i9 = this.position;
        this.position = i9 + 1;
        bArr[i9] = b9;
        this.totalBytesWritten++;
    }

    public void writeRawBytes(ByteString byteString) throws IOException {
        writeRawBytes(byteString, 0, byteString.size());
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

    public void writeRawMessageSetExtension(int i9, ByteString byteString) {
        writeTag(1, 3);
        writeUInt32(2, i9);
        writeBytes(3, byteString);
        writeTag(1, 4);
    }

    public void writeRawVarint32(int i9) {
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

    public void writeSInt32(int i9, int i10) {
        writeTag(i9, 0);
        writeSInt32NoTag(i10);
    }

    public void writeSInt32NoTag(int i9) {
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

    public void writeTag(int i9, int i10) {
        writeRawVarint32(WireFormat.makeTag(i9, i10));
    }

    public void writeUInt32(int i9, int i10) {
        writeTag(i9, 0);
        writeUInt32NoTag(i10);
    }

    public void writeUInt32NoTag(int i9) {
        writeRawVarint32(i9);
    }

    public void writeUInt64(int i9, long j9) {
        writeTag(i9, 0);
        writeUInt64NoTag(j9);
    }

    public void writeUInt64NoTag(long j9) {
        writeRawVarint64(j9);
    }

    @Deprecated
    public void writeUnknownGroup(int i9, MessageLite messageLite) {
        writeGroup(i9, messageLite);
    }

    @Deprecated
    public void writeUnknownGroupNoTag(MessageLite messageLite) {
        writeGroupNoTag(messageLite);
    }

    public static CodedOutputStream newInstance(OutputStream outputStream, int i9) {
        return new CodedOutputStream(outputStream, new byte[i9]);
    }

    public void writeRawBytes(byte[] bArr) throws IOException {
        writeRawBytes(bArr, 0, bArr.length);
    }

    public static CodedOutputStream newInstance(byte[] bArr) {
        return newInstance(bArr, 0, bArr.length);
    }

    public void writeByteArray(int i9, byte[] bArr, int i10, int i11) throws IOException {
        writeTag(i9, 2);
        writeByteArrayNoTag(bArr, i10, i11);
    }

    public void writeByteArrayNoTag(byte[] bArr, int i9, int i10) throws IOException {
        writeRawVarint32(i10);
        writeRawBytes(bArr, i9, i10);
    }

    public void writeRawBytes(ByteBuffer byteBuffer) throws IOException {
        if (byteBuffer.hasArray()) {
            writeRawBytes(byteBuffer.array(), byteBuffer.arrayOffset(), byteBuffer.capacity());
            return;
        }
        ByteBuffer byteBufferDuplicate = byteBuffer.duplicate();
        byteBufferDuplicate.clear();
        writeRawBytesInternal(byteBufferDuplicate);
    }

    public static CodedOutputStream newInstance(byte[] bArr, int i9, int i10) {
        return new CodedOutputStream(bArr, i9, i10);
    }

    public static CodedOutputStream newInstance(ByteBuffer byteBuffer) {
        return newInstance(byteBuffer, 4096);
    }

    public void writeRawByte(int i9) throws IOException {
        writeRawByte((byte) i9);
    }

    public static CodedOutputStream newInstance(ByteBuffer byteBuffer, int i9) {
        return newInstance(new ByteBufferOutputStream(byteBuffer), i9);
    }

    private CodedOutputStream(OutputStream outputStream, byte[] bArr) {
        this.totalBytesWritten = 0;
        this.output = outputStream;
        this.buffer = bArr;
        this.position = 0;
        this.limit = bArr.length;
    }

    public void writeRawBytes(byte[] bArr, int i9, int i10) throws IOException {
        int i11 = this.limit;
        int i12 = this.position;
        if (i11 - i12 >= i10) {
            System.arraycopy(bArr, i9, this.buffer, i12, i10);
            this.position += i10;
            this.totalBytesWritten += i10;
            return;
        }
        int i13 = i11 - i12;
        System.arraycopy(bArr, i9, this.buffer, i12, i13);
        int i14 = i9 + i13;
        int i15 = i10 - i13;
        this.position = this.limit;
        this.totalBytesWritten += i13;
        refreshBuffer();
        if (i15 <= this.limit) {
            System.arraycopy(bArr, i14, this.buffer, 0, i15);
            this.position = i15;
        } else {
            this.output.write(bArr, i14, i15);
        }
        this.totalBytesWritten += i15;
    }

    public void writeRawBytes(ByteString byteString, int i9, int i10) throws IOException {
        int i11 = this.limit;
        int i12 = this.position;
        if (i11 - i12 >= i10) {
            byteString.copyTo(this.buffer, i9, i12, i10);
            this.position += i10;
            this.totalBytesWritten += i10;
            return;
        }
        int i13 = i11 - i12;
        byteString.copyTo(this.buffer, i9, i12, i13);
        int i14 = i9 + i13;
        int i15 = i10 - i13;
        this.position = this.limit;
        this.totalBytesWritten += i13;
        refreshBuffer();
        if (i15 <= this.limit) {
            byteString.copyTo(this.buffer, i14, 0, i15);
            this.position = i15;
        } else {
            byteString.writeTo(this.output, i14, i15);
        }
        this.totalBytesWritten += i15;
    }
}
