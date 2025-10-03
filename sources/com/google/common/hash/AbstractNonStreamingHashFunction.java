package com.google.common.hash;

import com.google.common.base.Preconditions;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.util.Arrays;

/* loaded from: classes2.dex */
abstract class AbstractNonStreamingHashFunction extends AbstractHashFunction {

    public final class BufferingHasher extends AbstractHasher {
        final ExposedByteArrayOutputStream stream;

        public BufferingHasher(int i9) {
            this.stream = new ExposedByteArrayOutputStream(i9);
        }

        @Override // com.google.common.hash.Hasher
        public HashCode hash() {
            return AbstractNonStreamingHashFunction.this.hashBytes(this.stream.byteArray(), 0, this.stream.length());
        }

        @Override // com.google.common.hash.PrimitiveSink
        public Hasher putByte(byte b9) throws IOException {
            this.stream.write(b9);
            return this;
        }

        @Override // com.google.common.hash.AbstractHasher, com.google.common.hash.PrimitiveSink
        public Hasher putBytes(byte[] bArr, int i9, int i10) throws IOException {
            this.stream.write(bArr, i9, i10);
            return this;
        }

        @Override // com.google.common.hash.AbstractHasher, com.google.common.hash.PrimitiveSink
        public Hasher putBytes(ByteBuffer byteBuffer) {
            this.stream.write(byteBuffer);
            return this;
        }
    }

    public static final class ExposedByteArrayOutputStream extends ByteArrayOutputStream {
        public ExposedByteArrayOutputStream(int i9) {
            super(i9);
        }

        public byte[] byteArray() {
            return ((ByteArrayOutputStream) this).buf;
        }

        public int length() {
            return ((ByteArrayOutputStream) this).count;
        }

        public void write(ByteBuffer byteBuffer) {
            int iRemaining = byteBuffer.remaining();
            int i9 = ((ByteArrayOutputStream) this).count;
            int i10 = i9 + iRemaining;
            byte[] bArr = ((ByteArrayOutputStream) this).buf;
            if (i10 > bArr.length) {
                ((ByteArrayOutputStream) this).buf = Arrays.copyOf(bArr, i9 + iRemaining);
            }
            byteBuffer.get(((ByteArrayOutputStream) this).buf, ((ByteArrayOutputStream) this).count, iRemaining);
            ((ByteArrayOutputStream) this).count += iRemaining;
        }
    }

    @Override // com.google.common.hash.AbstractHashFunction, com.google.common.hash.HashFunction
    public HashCode hashBytes(ByteBuffer byteBuffer) {
        return newHasher(byteBuffer.remaining()).putBytes(byteBuffer).hash();
    }

    @Override // com.google.common.hash.AbstractHashFunction, com.google.common.hash.HashFunction
    public abstract HashCode hashBytes(byte[] bArr, int i9, int i10);

    @Override // com.google.common.hash.AbstractHashFunction, com.google.common.hash.HashFunction
    public HashCode hashInt(int i9) {
        return hashBytes(ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN).putInt(i9).array());
    }

    @Override // com.google.common.hash.AbstractHashFunction, com.google.common.hash.HashFunction
    public HashCode hashLong(long j9) {
        return hashBytes(ByteBuffer.allocate(8).order(ByteOrder.LITTLE_ENDIAN).putLong(j9).array());
    }

    @Override // com.google.common.hash.AbstractHashFunction, com.google.common.hash.HashFunction
    public HashCode hashString(CharSequence charSequence, Charset charset) {
        return hashBytes(charSequence.toString().getBytes(charset));
    }

    @Override // com.google.common.hash.AbstractHashFunction, com.google.common.hash.HashFunction
    public HashCode hashUnencodedChars(CharSequence charSequence) {
        int length = charSequence.length();
        ByteBuffer byteBufferOrder = ByteBuffer.allocate(length * 2).order(ByteOrder.LITTLE_ENDIAN);
        for (int i9 = 0; i9 < length; i9++) {
            byteBufferOrder.putChar(charSequence.charAt(i9));
        }
        return hashBytes(byteBufferOrder.array());
    }

    @Override // com.google.common.hash.HashFunction
    public Hasher newHasher() {
        return newHasher(32);
    }

    @Override // com.google.common.hash.AbstractHashFunction, com.google.common.hash.HashFunction
    public Hasher newHasher(int i9) {
        Preconditions.checkArgument(i9 >= 0);
        return new BufferingHasher(i9);
    }
}
