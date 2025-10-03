package org.apache.commons.p159io.output;

import com.google.firebase.crashlytics.internal.metadata.UserMetadata;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class ByteArrayOutputStream extends OutputStream {
    private static final byte[] EMPTY_BYTE_ARRAY = new byte[0];
    private List buffers;
    private int count;
    private byte[] currentBuffer;
    private int currentBufferIndex;
    private int filledBufferSum;

    public ByteArrayOutputStream() {
        this(UserMetadata.MAX_ATTRIBUTE_SIZE);
    }

    private byte[] getBuffer(int i9) {
        return (byte[]) this.buffers.get(i9);
    }

    private void needNewBuffer(int i9) {
        if (this.currentBufferIndex < this.buffers.size() - 1) {
            this.filledBufferSum += this.currentBuffer.length;
            int i10 = this.currentBufferIndex + 1;
            this.currentBufferIndex = i10;
            this.currentBuffer = getBuffer(i10);
            return;
        }
        byte[] bArr = this.currentBuffer;
        if (bArr == null) {
            this.filledBufferSum = 0;
        } else {
            i9 = Math.max(bArr.length << 1, i9 - this.filledBufferSum);
            this.filledBufferSum += this.currentBuffer.length;
        }
        this.currentBufferIndex++;
        byte[] bArr2 = new byte[i9];
        this.currentBuffer = bArr2;
        this.buffers.add(bArr2);
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
    }

    public synchronized void reset() {
        this.count = 0;
        this.filledBufferSum = 0;
        this.currentBufferIndex = 0;
        this.currentBuffer = getBuffer(0);
    }

    public synchronized int size() {
        return this.count;
    }

    public synchronized byte[] toByteArray() {
        int i9 = this.count;
        if (i9 == 0) {
            return EMPTY_BYTE_ARRAY;
        }
        byte[] bArr = new byte[i9];
        int i10 = 0;
        for (int i11 = 0; i11 < this.buffers.size(); i11++) {
            byte[] buffer = getBuffer(i11);
            int iMin = Math.min(buffer.length, i9);
            System.arraycopy(buffer, 0, bArr, i10, iMin);
            i10 += iMin;
            i9 -= iMin;
            if (i9 == 0) {
                break;
            }
        }
        return bArr;
    }

    public String toString() {
        return new String(toByteArray());
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr, int i9, int i10) {
        int i11;
        if (i9 < 0 || i9 > bArr.length || i10 < 0 || (i11 = i9 + i10) > bArr.length || i11 < 0) {
            throw new IndexOutOfBoundsException();
        }
        if (i10 == 0) {
            return;
        }
        synchronized (this) {
            int i12 = this.count;
            int i13 = i12 + i10;
            int i14 = i12 - this.filledBufferSum;
            while (i10 > 0) {
                int iMin = Math.min(i10, this.currentBuffer.length - i14);
                System.arraycopy(bArr, i11 - i10, this.currentBuffer, i14, iMin);
                i10 -= iMin;
                if (i10 > 0) {
                    needNewBuffer(i13);
                    i14 = 0;
                }
            }
            this.count = i13;
        }
    }

    public synchronized void writeTo(OutputStream outputStream) {
        int i9 = this.count;
        for (int i10 = 0; i10 < this.buffers.size(); i10++) {
            byte[] buffer = getBuffer(i10);
            int iMin = Math.min(buffer.length, i9);
            outputStream.write(buffer, 0, iMin);
            i9 -= iMin;
            if (i9 == 0) {
                break;
            }
        }
    }

    public ByteArrayOutputStream(int i9) {
        this.buffers = new ArrayList();
        if (i9 >= 0) {
            needNewBuffer(i9);
            return;
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Negative initial size: ");
        stringBuffer.append(i9);
        throw new IllegalArgumentException(stringBuffer.toString());
    }

    public String toString(String str) {
        return new String(toByteArray(), str);
    }

    @Override // java.io.OutputStream
    public synchronized void write(int i9) {
        int i10 = this.count;
        int i11 = i10 - this.filledBufferSum;
        if (i11 == this.currentBuffer.length) {
            needNewBuffer(i10 + 1);
            i11 = 0;
        }
        this.currentBuffer[i11] = (byte) i9;
        this.count++;
    }
}
