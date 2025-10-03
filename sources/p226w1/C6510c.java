package p226w1;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

/* renamed from: w1.c */
/* loaded from: classes.dex */
public final class C6510c extends FilterInputStream {

    /* renamed from: b */
    public final long f21912b;

    /* renamed from: c */
    public int f21913c;

    public C6510c(InputStream inputStream, long j9) {
        super(inputStream);
        this.f21912b = j9;
    }

    /* renamed from: u */
    public static InputStream m24917u(InputStream inputStream, long j9) {
        return new C6510c(inputStream, j9);
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public synchronized int available() {
        return (int) Math.max(this.f21912b - this.f21913c, ((FilterInputStream) this).in.available());
    }

    /* renamed from: f */
    public final int m24918f(int i9) throws IOException {
        if (i9 >= 0) {
            this.f21913c += i9;
        } else if (this.f21912b - this.f21913c > 0) {
            throw new IOException("Failed to read all expected data, expected: " + this.f21912b + ", but read: " + this.f21913c);
        }
        return i9;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public synchronized int read() {
        int i9;
        i9 = super.read();
        m24918f(i9 >= 0 ? 1 : -1);
        return i9;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] bArr) {
        return read(bArr, 0, bArr.length);
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public synchronized int read(byte[] bArr, int i9, int i10) {
        return m24918f(super.read(bArr, i9, i10));
    }
}
