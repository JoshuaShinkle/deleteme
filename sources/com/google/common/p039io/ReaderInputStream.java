package com.google.common.p039io;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.primitives.UnsignedBytes;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CoderResult;
import java.nio.charset.CodingErrorAction;
import java.util.Arrays;

@GwtIncompatible
/* loaded from: classes2.dex */
final class ReaderInputStream extends InputStream {
    private ByteBuffer byteBuffer;
    private CharBuffer charBuffer;
    private boolean doneFlushing;
    private boolean draining;
    private final CharsetEncoder encoder;
    private boolean endOfInput;
    private final Reader reader;
    private final byte[] singleByte;

    public ReaderInputStream(Reader reader, Charset charset, int i9) {
        this(reader, charset.newEncoder().onMalformedInput(CodingErrorAction.REPLACE).onUnmappableCharacter(CodingErrorAction.REPLACE), i9);
    }

    private static int availableCapacity(Buffer buffer) {
        return buffer.capacity() - buffer.limit();
    }

    private int drain(byte[] bArr, int i9, int i10) {
        int iMin = Math.min(i10, this.byteBuffer.remaining());
        this.byteBuffer.get(bArr, i9, iMin);
        return iMin;
    }

    private static CharBuffer grow(CharBuffer charBuffer) {
        CharBuffer charBufferWrap = CharBuffer.wrap(Arrays.copyOf(charBuffer.array(), charBuffer.capacity() * 2));
        charBufferWrap.position(charBuffer.position());
        charBufferWrap.limit(charBuffer.limit());
        return charBufferWrap;
    }

    private void readMoreChars() throws IOException {
        if (availableCapacity(this.charBuffer) == 0) {
            if (this.charBuffer.position() > 0) {
                this.charBuffer.compact().flip();
            } else {
                this.charBuffer = grow(this.charBuffer);
            }
        }
        int iLimit = this.charBuffer.limit();
        int i9 = this.reader.read(this.charBuffer.array(), iLimit, availableCapacity(this.charBuffer));
        if (i9 == -1) {
            this.endOfInput = true;
        } else {
            this.charBuffer.limit(iLimit + i9);
        }
    }

    private void startDraining(boolean z8) {
        this.byteBuffer.flip();
        if (z8 && this.byteBuffer.remaining() == 0) {
            this.byteBuffer = ByteBuffer.allocate(this.byteBuffer.capacity() * 2);
        } else {
            this.draining = true;
        }
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.reader.close();
    }

    @Override // java.io.InputStream
    public int read() {
        if (read(this.singleByte) == 1) {
            return UnsignedBytes.toInt(this.singleByte[0]);
        }
        return -1;
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x0029, code lost:
    
        if (r2 <= 0) goto L16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x002c, code lost:
    
        return -1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:?, code lost:
    
        return r2;
     */
    @Override // java.io.InputStream
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public int read(byte[] bArr, int i9, int i10) throws IOException {
        Preconditions.checkPositionIndexes(i9, i9 + i10, bArr.length);
        if (i10 == 0) {
            return 0;
        }
        boolean z8 = this.endOfInput;
        int iDrain = 0;
        while (true) {
            if (this.draining) {
                iDrain += drain(bArr, i9 + iDrain, i10 - iDrain);
                if (iDrain == i10 || this.doneFlushing) {
                    break;
                }
                this.draining = false;
                this.byteBuffer.clear();
            }
            while (true) {
                CoderResult coderResultFlush = this.doneFlushing ? CoderResult.UNDERFLOW : z8 ? this.encoder.flush(this.byteBuffer) : this.encoder.encode(this.charBuffer, this.byteBuffer, this.endOfInput);
                if (coderResultFlush.isOverflow()) {
                    startDraining(true);
                    break;
                }
                if (coderResultFlush.isUnderflow()) {
                    if (z8) {
                        this.doneFlushing = true;
                        startDraining(false);
                        break;
                    }
                    if (this.endOfInput) {
                        z8 = true;
                    } else {
                        readMoreChars();
                    }
                } else if (coderResultFlush.isError()) {
                    coderResultFlush.throwException();
                    return 0;
                }
            }
        }
    }

    public ReaderInputStream(Reader reader, CharsetEncoder charsetEncoder, int i9) {
        this.singleByte = new byte[1];
        this.reader = (Reader) Preconditions.checkNotNull(reader);
        this.encoder = (CharsetEncoder) Preconditions.checkNotNull(charsetEncoder);
        Preconditions.checkArgument(i9 > 0, "bufferSize must be positive: %s", i9);
        charsetEncoder.reset();
        CharBuffer charBufferAllocate = CharBuffer.allocate(i9);
        this.charBuffer = charBufferAllocate;
        charBufferAllocate.flip();
        this.byteBuffer = ByteBuffer.allocate(i9);
    }
}
