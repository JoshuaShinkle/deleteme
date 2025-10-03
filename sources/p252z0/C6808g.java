package p252z0;

import com.google.common.base.Ascii;
import com.google.common.primitives.UnsignedBytes;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

/* renamed from: z0.g */
/* loaded from: classes.dex */
public final class C6808g extends FilterInputStream {

    /* renamed from: d */
    public static final byte[] f22552d;

    /* renamed from: e */
    public static final int f22553e;

    /* renamed from: f */
    public static final int f22554f;

    /* renamed from: b */
    public final byte f22555b;

    /* renamed from: c */
    public int f22556c;

    static {
        byte[] bArr = {-1, -31, 0, Ascii.f15383FS, 69, 120, 105, 102, 0, 0, 77, 77, 0, 0, 0, 0, 0, 8, 0, 1, 1, Ascii.DC2, 0, 2, 0, 0, 0, 1, 0};
        f22552d = bArr;
        int length = bArr.length;
        f22553e = length;
        f22554f = length + 2;
    }

    public C6808g(InputStream inputStream, int i9) {
        super(inputStream);
        if (i9 >= -1 && i9 <= 8) {
            this.f22555b = (byte) i9;
            return;
        }
        throw new IllegalArgumentException("Cannot add invalid orientation: " + i9);
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public void mark(int i9) {
        throw new UnsupportedOperationException();
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public boolean markSupported() {
        return false;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read() {
        int i9;
        int i10 = this.f22556c;
        int i11 = (i10 < 2 || i10 > (i9 = f22554f)) ? super.read() : i10 == i9 ? this.f22555b : f22552d[i10 - 2] & UnsignedBytes.MAX_VALUE;
        if (i11 != -1) {
            this.f22556c++;
        }
        return i11;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public void reset() {
        throw new UnsupportedOperationException();
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public long skip(long j9) throws IOException {
        long jSkip = super.skip(j9);
        if (jSkip > 0) {
            this.f22556c = (int) (this.f22556c + jSkip);
        }
        return jSkip;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] bArr, int i9, int i10) throws IOException {
        int i11;
        int i12 = this.f22556c;
        int i13 = f22554f;
        if (i12 > i13) {
            i11 = super.read(bArr, i9, i10);
        } else if (i12 == i13) {
            bArr[i9] = this.f22555b;
            i11 = 1;
        } else if (i12 < 2) {
            i11 = super.read(bArr, i9, 2 - i12);
        } else {
            int iMin = Math.min(i13 - i12, i10);
            System.arraycopy(f22552d, this.f22556c - 2, bArr, i9, iMin);
            i11 = iMin;
        }
        if (i11 > 0) {
            this.f22556c += i11;
        }
        return i11;
    }
}
