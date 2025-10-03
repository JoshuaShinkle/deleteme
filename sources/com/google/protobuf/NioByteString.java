package com.google.protobuf;

import com.google.common.primitives.UnsignedBytes;
import com.google.protobuf.ByteString;
import java.io.IOException;
import java.io.InputStream;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.InvalidMarkException;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.List;

/* loaded from: classes2.dex */
final class NioByteString extends ByteString.LeafByteString {
    private final ByteBuffer buffer;

    public NioByteString(ByteBuffer byteBuffer) {
        Internal.checkNotNull(byteBuffer, "buffer");
        this.buffer = byteBuffer.slice().order(ByteOrder.nativeOrder());
    }

    private void readObject(ObjectInputStream objectInputStream) throws InvalidObjectException {
        throw new InvalidObjectException("NioByteString instances are not to be serialized directly");
    }

    private ByteBuffer slice(int i9, int i10) {
        if (i9 < this.buffer.position() || i10 > this.buffer.limit() || i9 > i10) {
            throw new IllegalArgumentException(String.format("Invalid indices [%d, %d]", Integer.valueOf(i9), Integer.valueOf(i10)));
        }
        ByteBuffer byteBufferSlice = this.buffer.slice();
        byteBufferSlice.position(i9 - this.buffer.position());
        byteBufferSlice.limit(i10 - this.buffer.position());
        return byteBufferSlice;
    }

    private Object writeReplace() {
        return ByteString.copyFrom(this.buffer.slice());
    }

    @Override // com.google.protobuf.ByteString
    public ByteBuffer asReadOnlyByteBuffer() {
        return this.buffer.asReadOnlyBuffer();
    }

    @Override // com.google.protobuf.ByteString
    public List<ByteBuffer> asReadOnlyByteBufferList() {
        return Collections.singletonList(asReadOnlyByteBuffer());
    }

    @Override // com.google.protobuf.ByteString
    public byte byteAt(int i9) {
        try {
            return this.buffer.get(i9);
        } catch (ArrayIndexOutOfBoundsException e9) {
            throw e9;
        } catch (IndexOutOfBoundsException e10) {
            throw new ArrayIndexOutOfBoundsException(e10.getMessage());
        }
    }

    @Override // com.google.protobuf.ByteString
    public void copyTo(ByteBuffer byteBuffer) {
        byteBuffer.put(this.buffer.slice());
    }

    @Override // com.google.protobuf.ByteString
    public void copyToInternal(byte[] bArr, int i9, int i10, int i11) {
        ByteBuffer byteBufferSlice = this.buffer.slice();
        byteBufferSlice.position(i9);
        byteBufferSlice.get(bArr, i10, i11);
    }

    @Override // com.google.protobuf.ByteString
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ByteString)) {
            return false;
        }
        ByteString byteString = (ByteString) obj;
        if (size() != byteString.size()) {
            return false;
        }
        if (size() == 0) {
            return true;
        }
        return obj instanceof NioByteString ? this.buffer.equals(((NioByteString) obj).buffer) : obj instanceof RopeByteString ? obj.equals(this) : this.buffer.equals(byteString.asReadOnlyByteBuffer());
    }

    @Override // com.google.protobuf.ByteString.LeafByteString
    public boolean equalsRange(ByteString byteString, int i9, int i10) {
        return substring(0, i10).equals(byteString.substring(i9, i10 + i9));
    }

    @Override // com.google.protobuf.ByteString
    public byte internalByteAt(int i9) {
        return byteAt(i9);
    }

    @Override // com.google.protobuf.ByteString
    public boolean isValidUtf8() {
        return Utf8.isValidUtf8(this.buffer);
    }

    @Override // com.google.protobuf.ByteString
    public CodedInputStream newCodedInput() {
        return CodedInputStream.newInstance(this.buffer, true);
    }

    @Override // com.google.protobuf.ByteString
    public InputStream newInput() {
        return new InputStream() { // from class: com.google.protobuf.NioByteString.1
            private final ByteBuffer buf;

            {
                this.buf = NioByteString.this.buffer.slice();
            }

            @Override // java.io.InputStream
            public int available() {
                return this.buf.remaining();
            }

            @Override // java.io.InputStream
            public void mark(int i9) {
                this.buf.mark();
            }

            @Override // java.io.InputStream
            public boolean markSupported() {
                return true;
            }

            @Override // java.io.InputStream
            public int read() {
                if (this.buf.hasRemaining()) {
                    return this.buf.get() & UnsignedBytes.MAX_VALUE;
                }
                return -1;
            }

            @Override // java.io.InputStream
            public void reset() throws IOException {
                try {
                    this.buf.reset();
                } catch (InvalidMarkException e9) {
                    throw new IOException(e9);
                }
            }

            @Override // java.io.InputStream
            public int read(byte[] bArr, int i9, int i10) {
                if (!this.buf.hasRemaining()) {
                    return -1;
                }
                int iMin = Math.min(i10, this.buf.remaining());
                this.buf.get(bArr, i9, iMin);
                return iMin;
            }
        };
    }

    @Override // com.google.protobuf.ByteString
    public int partialHash(int i9, int i10, int i11) {
        for (int i12 = i10; i12 < i10 + i11; i12++) {
            i9 = (i9 * 31) + this.buffer.get(i12);
        }
        return i9;
    }

    @Override // com.google.protobuf.ByteString
    public int partialIsValidUtf8(int i9, int i10, int i11) {
        return Utf8.partialIsValidUtf8(i9, this.buffer, i10, i11 + i10);
    }

    @Override // com.google.protobuf.ByteString
    public int size() {
        return this.buffer.remaining();
    }

    @Override // com.google.protobuf.ByteString
    public ByteString substring(int i9, int i10) {
        try {
            return new NioByteString(slice(i9, i10));
        } catch (ArrayIndexOutOfBoundsException e9) {
            throw e9;
        } catch (IndexOutOfBoundsException e10) {
            throw new ArrayIndexOutOfBoundsException(e10.getMessage());
        }
    }

    @Override // com.google.protobuf.ByteString
    public String toStringInternal(Charset charset) {
        byte[] byteArray;
        int length;
        int iArrayOffset;
        if (this.buffer.hasArray()) {
            byteArray = this.buffer.array();
            iArrayOffset = this.buffer.arrayOffset() + this.buffer.position();
            length = this.buffer.remaining();
        } else {
            byteArray = toByteArray();
            length = byteArray.length;
            iArrayOffset = 0;
        }
        return new String(byteArray, iArrayOffset, length, charset);
    }

    @Override // com.google.protobuf.ByteString
    public void writeTo(OutputStream outputStream) throws IOException {
        outputStream.write(toByteArray());
    }

    @Override // com.google.protobuf.ByteString
    public void writeToInternal(OutputStream outputStream, int i9, int i10) throws IOException {
        if (!this.buffer.hasArray()) {
            ByteBufferWriter.write(slice(i9, i10 + i9), outputStream);
        } else {
            outputStream.write(this.buffer.array(), this.buffer.arrayOffset() + this.buffer.position() + i9, i10);
        }
    }

    @Override // com.google.protobuf.ByteString
    public void writeTo(ByteOutput byteOutput) {
        byteOutput.writeLazy(this.buffer.slice());
    }
}
