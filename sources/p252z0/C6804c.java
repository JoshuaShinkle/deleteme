package p252z0;

import com.google.android.exoplayer2.C3322C;
import java.io.IOException;
import java.io.OutputStream;
import p022c1.InterfaceC0705b;

/* renamed from: z0.c */
/* loaded from: classes.dex */
public final class C6804c extends OutputStream {

    /* renamed from: b */
    public final OutputStream f22545b;

    /* renamed from: c */
    public byte[] f22546c;

    /* renamed from: d */
    public InterfaceC0705b f22547d;

    /* renamed from: e */
    public int f22548e;

    public C6804c(OutputStream outputStream, InterfaceC0705b interfaceC0705b) {
        this(outputStream, interfaceC0705b, C3322C.DEFAULT_BUFFER_SEGMENT_SIZE);
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        try {
            flush();
            this.f22545b.close();
            release();
        } catch (Throwable th) {
            this.f22545b.close();
            throw th;
        }
    }

    /* renamed from: f */
    public final void m25364f() throws IOException {
        int i9 = this.f22548e;
        if (i9 > 0) {
            this.f22545b.write(this.f22546c, 0, i9);
            this.f22548e = 0;
        }
    }

    @Override // java.io.OutputStream, java.io.Flushable
    public void flush() throws IOException {
        m25364f();
        this.f22545b.flush();
    }

    public final void release() {
        byte[] bArr = this.f22546c;
        if (bArr != null) {
            this.f22547d.put(bArr);
            this.f22546c = null;
        }
    }

    /* renamed from: u */
    public final void m25365u() throws IOException {
        if (this.f22548e == this.f22546c.length) {
            m25364f();
        }
    }

    @Override // java.io.OutputStream
    public void write(int i9) throws IOException {
        byte[] bArr = this.f22546c;
        int i10 = this.f22548e;
        this.f22548e = i10 + 1;
        bArr[i10] = (byte) i9;
        m25365u();
    }

    public C6804c(OutputStream outputStream, InterfaceC0705b interfaceC0705b, int i9) {
        this.f22545b = outputStream;
        this.f22547d = interfaceC0705b;
        this.f22546c = (byte[]) interfaceC0705b.mo3481d(i9, byte[].class);
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr) throws IOException {
        write(bArr, 0, bArr.length);
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr, int i9, int i10) throws IOException {
        int i11 = 0;
        do {
            int i12 = i10 - i11;
            int i13 = i9 + i11;
            int i14 = this.f22548e;
            if (i14 == 0 && i12 >= this.f22546c.length) {
                this.f22545b.write(bArr, i13, i12);
                return;
            }
            int iMin = Math.min(i12, this.f22546c.length - i14);
            System.arraycopy(bArr, i13, this.f22546c, this.f22548e, iMin);
            this.f22548e += iMin;
            i11 += iMin;
            m25365u();
        } while (i11 < i10);
    }
}
