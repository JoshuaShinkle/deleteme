package com.google.protobuf;

import com.google.common.primitives.UnsignedBytes;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Iterator;

/* loaded from: classes2.dex */
class IterableByteBufferInputStream extends InputStream {
    private long currentAddress;
    private byte[] currentArray;
    private int currentArrayOffset;
    private ByteBuffer currentByteBuffer;
    private int currentByteBufferPos;
    private int currentIndex;
    private int dataSize = 0;
    private boolean hasArray;
    private Iterator<ByteBuffer> iterator;

    public IterableByteBufferInputStream(Iterable<ByteBuffer> iterable) {
        this.iterator = iterable.iterator();
        for (ByteBuffer byteBuffer : iterable) {
            this.dataSize++;
        }
        this.currentIndex = -1;
        if (getNextByteBuffer()) {
            return;
        }
        this.currentByteBuffer = Internal.EMPTY_BYTE_BUFFER;
        this.currentIndex = 0;
        this.currentByteBufferPos = 0;
        this.currentAddress = 0L;
    }

    private boolean getNextByteBuffer() {
        this.currentIndex++;
        if (!this.iterator.hasNext()) {
            return false;
        }
        ByteBuffer next = this.iterator.next();
        this.currentByteBuffer = next;
        this.currentByteBufferPos = next.position();
        if (this.currentByteBuffer.hasArray()) {
            this.hasArray = true;
            this.currentArray = this.currentByteBuffer.array();
            this.currentArrayOffset = this.currentByteBuffer.arrayOffset();
        } else {
            this.hasArray = false;
            this.currentAddress = UnsafeUtil.addressOffset(this.currentByteBuffer);
            this.currentArray = null;
        }
        return true;
    }

    private void updateCurrentByteBufferPos(int i9) {
        int i10 = this.currentByteBufferPos + i9;
        this.currentByteBufferPos = i10;
        if (i10 == this.currentByteBuffer.limit()) {
            getNextByteBuffer();
        }
    }

    @Override // java.io.InputStream
    public int read() {
        if (this.currentIndex == this.dataSize) {
            return -1;
        }
        if (this.hasArray) {
            int i9 = this.currentArray[this.currentByteBufferPos + this.currentArrayOffset] & UnsignedBytes.MAX_VALUE;
            updateCurrentByteBufferPos(1);
            return i9;
        }
        int i10 = UnsafeUtil.getByte(this.currentByteBufferPos + this.currentAddress) & UnsignedBytes.MAX_VALUE;
        updateCurrentByteBufferPos(1);
        return i10;
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i9, int i10) {
        if (this.currentIndex == this.dataSize) {
            return -1;
        }
        int iLimit = this.currentByteBuffer.limit();
        int i11 = this.currentByteBufferPos;
        int i12 = iLimit - i11;
        if (i10 > i12) {
            i10 = i12;
        }
        if (this.hasArray) {
            System.arraycopy(this.currentArray, i11 + this.currentArrayOffset, bArr, i9, i10);
            updateCurrentByteBufferPos(i10);
        } else {
            int iPosition = this.currentByteBuffer.position();
            this.currentByteBuffer.position(this.currentByteBufferPos);
            this.currentByteBuffer.get(bArr, i9, i10);
            this.currentByteBuffer.position(iPosition);
            updateCurrentByteBufferPos(i10);
        }
        return i10;
    }
}
