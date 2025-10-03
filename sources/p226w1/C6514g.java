package p226w1;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

/* renamed from: w1.g */
/* loaded from: classes.dex */
public class C6514g extends FilterInputStream {

    /* renamed from: b */
    public int f21922b;

    public C6514g(InputStream inputStream) {
        super(inputStream);
        this.f21922b = Integer.MIN_VALUE;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int available() {
        int i9 = this.f21922b;
        return i9 == Integer.MIN_VALUE ? super.available() : Math.min(i9, super.available());
    }

    /* renamed from: f */
    public final long m24931f(long j9) {
        int i9 = this.f21922b;
        if (i9 == 0) {
            return -1L;
        }
        return (i9 == Integer.MIN_VALUE || j9 <= ((long) i9)) ? j9 : i9;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public synchronized void mark(int i9) {
        super.mark(i9);
        this.f21922b = i9;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read() throws IOException {
        if (m24931f(1L) == -1) {
            return -1;
        }
        int i9 = super.read();
        m24932u(1L);
        return i9;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public synchronized void reset() {
        super.reset();
        this.f21922b = Integer.MIN_VALUE;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public long skip(long j9) throws IOException {
        long jM24931f = m24931f(j9);
        if (jM24931f == -1) {
            return 0L;
        }
        long jSkip = super.skip(jM24931f);
        m24932u(jSkip);
        return jSkip;
    }

    /* renamed from: u */
    public final void m24932u(long j9) {
        int i9 = this.f21922b;
        if (i9 == Integer.MIN_VALUE || j9 == -1) {
            return;
        }
        this.f21922b = (int) (i9 - j9);
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] bArr, int i9, int i10) throws IOException {
        int iM24931f = (int) m24931f(i10);
        if (iM24931f == -1) {
            return -1;
        }
        int i11 = super.read(bArr, i9, iM24931f);
        m24932u(i11);
        return i11;
    }
}
