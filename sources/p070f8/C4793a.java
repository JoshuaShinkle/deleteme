package p070f8;

import com.google.android.exoplayer2.C3322C;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketTimeoutException;
import java.nio.ByteBuffer;
import p060e8.C4772d;

/* renamed from: f8.a */
/* loaded from: classes.dex */
public final class C4793a extends BufferedInputStream {

    /* renamed from: b */
    public final boolean f16645b;

    /* renamed from: c */
    public final int f16646c;

    /* renamed from: d */
    public long f16647d;

    /* renamed from: e */
    public long f16648e;

    /* renamed from: f */
    public int f16649f;

    /* renamed from: g */
    public boolean f16650g;

    public C4793a(InputStream inputStream, int i9, int i10) {
        super(inputStream, i9);
        this.f16648e = 0L;
        C4772d.m18998d(i10 >= 0);
        this.f16646c = i10;
        this.f16649f = i10;
        this.f16645b = i10 != 0;
        this.f16647d = System.nanoTime();
    }

    /* renamed from: w */
    public static C4793a m19026w(InputStream inputStream, int i9, int i10) {
        return inputStream instanceof C4793a ? (C4793a) inputStream : new C4793a(inputStream, i9, i10);
    }

    /* renamed from: f */
    public final boolean m19027f() {
        return this.f16648e != 0 && System.nanoTime() - this.f16647d > this.f16648e;
    }

    @Override // java.io.BufferedInputStream, java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] bArr, int i9, int i10) throws IOException {
        int i11;
        if (this.f16650g || (this.f16645b && this.f16649f <= 0)) {
            return -1;
        }
        if (Thread.interrupted()) {
            this.f16650g = true;
            return -1;
        }
        if (m19027f()) {
            throw new SocketTimeoutException("Read timeout");
        }
        if (this.f16645b && i10 > (i11 = this.f16649f)) {
            i10 = i11;
        }
        try {
            int i12 = super.read(bArr, i9, i10);
            this.f16649f -= i12;
            return i12;
        } catch (SocketTimeoutException unused) {
            return 0;
        }
    }

    @Override // java.io.BufferedInputStream, java.io.FilterInputStream, java.io.InputStream
    public void reset() throws IOException {
        super.reset();
        this.f16649f = this.f16646c - ((BufferedInputStream) this).markpos;
    }

    /* renamed from: u */
    public ByteBuffer m19028u(int i9) throws IOException {
        C4772d.m18999e(i9 >= 0, "maxSize must be 0 (unlimited) or larger");
        boolean z8 = i9 > 0;
        int i10 = 32768;
        if (z8 && i9 < 32768) {
            i10 = i9;
        }
        byte[] bArr = new byte[i10];
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(i10);
        while (true) {
            int i11 = read(bArr);
            if (i11 == -1) {
                break;
            }
            if (z8) {
                if (i11 >= i9) {
                    byteArrayOutputStream.write(bArr, 0, i9);
                    break;
                }
                i9 -= i11;
            }
            byteArrayOutputStream.write(bArr, 0, i11);
        }
        return ByteBuffer.wrap(byteArrayOutputStream.toByteArray());
    }

    /* renamed from: v */
    public C4793a m19029v(long j9, long j10) {
        this.f16647d = j9;
        this.f16648e = j10 * C3322C.MICROS_PER_SECOND;
        return this;
    }
}
