package com.bumptech.glide.load.resource.bitmap;

import com.google.android.exoplayer2.C3322C;
import com.google.common.primitives.UnsignedBytes;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import p022c1.InterfaceC0705b;

/* loaded from: classes.dex */
public class RecyclableBufferedInputStream extends FilterInputStream {

    /* renamed from: b */
    public volatile byte[] f3889b;

    /* renamed from: c */
    public int f3890c;

    /* renamed from: d */
    public int f3891d;

    /* renamed from: e */
    public int f3892e;

    /* renamed from: f */
    public int f3893f;

    /* renamed from: g */
    public final InterfaceC0705b f3894g;

    public static class InvalidMarkException extends IOException {
        private static final long serialVersionUID = -4338378848813561757L;

        public InvalidMarkException(String str) {
            super(str);
        }
    }

    public RecyclableBufferedInputStream(InputStream inputStream, InterfaceC0705b interfaceC0705b) {
        this(inputStream, interfaceC0705b, C3322C.DEFAULT_BUFFER_SEGMENT_SIZE);
    }

    /* renamed from: v */
    public static IOException m3970v() throws IOException {
        throw new IOException("BufferedInputStream is closed");
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public synchronized int available() {
        InputStream inputStream;
        inputStream = ((FilterInputStream) this).in;
        if (this.f3889b == null || inputStream == null) {
            throw m3970v();
        }
        return (this.f3890c - this.f3893f) + inputStream.available();
    }

    @Override // java.io.FilterInputStream, java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (this.f3889b != null) {
            this.f3894g.put(this.f3889b);
            this.f3889b = null;
        }
        InputStream inputStream = ((FilterInputStream) this).in;
        ((FilterInputStream) this).in = null;
        if (inputStream != null) {
            inputStream.close();
        }
    }

    /* renamed from: f */
    public final int m3971f(InputStream inputStream, byte[] bArr) throws IOException {
        int i9 = this.f3892e;
        if (i9 != -1) {
            int i10 = this.f3893f - i9;
            int i11 = this.f3891d;
            if (i10 < i11) {
                if (i9 == 0 && i11 > bArr.length && this.f3890c == bArr.length) {
                    int length = bArr.length * 2;
                    if (length <= i11) {
                        i11 = length;
                    }
                    byte[] bArr2 = (byte[]) this.f3894g.mo3481d(i11, byte[].class);
                    System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
                    this.f3889b = bArr2;
                    this.f3894g.put(bArr);
                    bArr = bArr2;
                } else if (i9 > 0) {
                    System.arraycopy(bArr, i9, bArr, 0, bArr.length - i9);
                }
                int i12 = this.f3893f - this.f3892e;
                this.f3893f = i12;
                this.f3892e = 0;
                this.f3890c = 0;
                int i13 = inputStream.read(bArr, i12, bArr.length - i12);
                int i14 = this.f3893f;
                if (i13 > 0) {
                    i14 += i13;
                }
                this.f3890c = i14;
                return i13;
            }
        }
        int i15 = inputStream.read(bArr);
        if (i15 > 0) {
            this.f3892e = -1;
            this.f3893f = 0;
            this.f3890c = i15;
        }
        return i15;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public synchronized void mark(int i9) {
        this.f3891d = Math.max(this.f3891d, i9);
        this.f3892e = this.f3893f;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public boolean markSupported() {
        return true;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public synchronized int read() {
        byte[] bArr = this.f3889b;
        InputStream inputStream = ((FilterInputStream) this).in;
        if (bArr == null || inputStream == null) {
            throw m3970v();
        }
        if (this.f3893f >= this.f3890c && m3971f(inputStream, bArr) == -1) {
            return -1;
        }
        if (bArr != this.f3889b && (bArr = this.f3889b) == null) {
            throw m3970v();
        }
        int i9 = this.f3890c;
        int i10 = this.f3893f;
        if (i9 - i10 <= 0) {
            return -1;
        }
        this.f3893f = i10 + 1;
        return bArr[i10] & UnsignedBytes.MAX_VALUE;
    }

    public synchronized void release() {
        if (this.f3889b != null) {
            this.f3894g.put(this.f3889b);
            this.f3889b = null;
        }
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public synchronized void reset() {
        if (this.f3889b == null) {
            throw new IOException("Stream is closed");
        }
        int i9 = this.f3892e;
        if (-1 == i9) {
            throw new InvalidMarkException("Mark has been invalidated, pos: " + this.f3893f + " markLimit: " + this.f3891d);
        }
        this.f3893f = i9;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public synchronized long skip(long j9) {
        if (j9 < 1) {
            return 0L;
        }
        byte[] bArr = this.f3889b;
        if (bArr == null) {
            throw m3970v();
        }
        InputStream inputStream = ((FilterInputStream) this).in;
        if (inputStream == null) {
            throw m3970v();
        }
        int i9 = this.f3890c;
        int i10 = this.f3893f;
        if (i9 - i10 >= j9) {
            this.f3893f = (int) (i10 + j9);
            return j9;
        }
        long j10 = i9 - i10;
        this.f3893f = i9;
        if (this.f3892e == -1 || j9 > this.f3891d) {
            return j10 + inputStream.skip(j9 - j10);
        }
        if (m3971f(inputStream, bArr) == -1) {
            return j10;
        }
        int i11 = this.f3890c;
        int i12 = this.f3893f;
        if (i11 - i12 >= j9 - j10) {
            this.f3893f = (int) ((i12 + j9) - j10);
            return j9;
        }
        long j11 = (j10 + i11) - i12;
        this.f3893f = i11;
        return j11;
    }

    /* renamed from: u */
    public synchronized void m3972u() {
        this.f3891d = this.f3889b.length;
    }

    public RecyclableBufferedInputStream(InputStream inputStream, InterfaceC0705b interfaceC0705b, int i9) {
        super(inputStream);
        this.f3892e = -1;
        this.f3894g = interfaceC0705b;
        this.f3889b = (byte[]) interfaceC0705b.mo3481d(i9, byte[].class);
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public synchronized int read(byte[] bArr, int i9, int i10) {
        int i11;
        int i12;
        byte[] bArr2 = this.f3889b;
        if (bArr2 == null) {
            throw m3970v();
        }
        if (i10 == 0) {
            return 0;
        }
        InputStream inputStream = ((FilterInputStream) this).in;
        if (inputStream != null) {
            int i13 = this.f3893f;
            int i14 = this.f3890c;
            if (i13 < i14) {
                int i15 = i14 - i13 >= i10 ? i10 : i14 - i13;
                System.arraycopy(bArr2, i13, bArr, i9, i15);
                this.f3893f += i15;
                if (i15 == i10 || inputStream.available() == 0) {
                    return i15;
                }
                i9 += i15;
                i11 = i10 - i15;
            } else {
                i11 = i10;
            }
            while (true) {
                if (this.f3892e == -1 && i11 >= bArr2.length) {
                    i12 = inputStream.read(bArr, i9, i11);
                    if (i12 == -1) {
                        return i11 != i10 ? i10 - i11 : -1;
                    }
                } else {
                    if (m3971f(inputStream, bArr2) == -1) {
                        return i11 != i10 ? i10 - i11 : -1;
                    }
                    if (bArr2 != this.f3889b && (bArr2 = this.f3889b) == null) {
                        throw m3970v();
                    }
                    int i16 = this.f3890c;
                    int i17 = this.f3893f;
                    i12 = i16 - i17 >= i11 ? i11 : i16 - i17;
                    System.arraycopy(bArr2, i17, bArr, i9, i12);
                    this.f3893f += i12;
                }
                i11 -= i12;
                if (i11 == 0) {
                    return i10;
                }
                if (inputStream.available() == 0) {
                    return i10 - i11;
                }
                i9 += i12;
            }
        } else {
            throw m3970v();
        }
    }
}
