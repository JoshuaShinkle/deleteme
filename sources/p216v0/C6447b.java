package p216v0;

import com.google.firebase.crashlytics.internal.metadata.UserMetadata;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

/* renamed from: v0.b */
/* loaded from: classes.dex */
public class C6447b implements Closeable {

    /* renamed from: b */
    public final InputStream f21708b;

    /* renamed from: c */
    public final Charset f21709c;

    /* renamed from: d */
    public byte[] f21710d;

    /* renamed from: e */
    public int f21711e;

    /* renamed from: f */
    public int f21712f;

    /* renamed from: v0.b$a */
    public class a extends ByteArrayOutputStream {
        public a(int i9) {
            super(i9);
        }

        @Override // java.io.ByteArrayOutputStream
        public String toString() {
            int i9 = ((ByteArrayOutputStream) this).count;
            if (i9 > 0 && ((ByteArrayOutputStream) this).buf[i9 - 1] == 13) {
                i9--;
            }
            try {
                return new String(((ByteArrayOutputStream) this).buf, 0, i9, C6447b.this.f21709c.name());
            } catch (UnsupportedEncodingException e9) {
                throw new AssertionError(e9);
            }
        }
    }

    public C6447b(InputStream inputStream, Charset charset) {
        this(inputStream, UserMetadata.MAX_INTERNAL_KEY_SIZE, charset);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        synchronized (this.f21708b) {
            if (this.f21710d != null) {
                this.f21710d = null;
                this.f21708b.close();
            }
        }
    }

    /* renamed from: u */
    public final void m24688u() throws IOException {
        InputStream inputStream = this.f21708b;
        byte[] bArr = this.f21710d;
        int i9 = inputStream.read(bArr, 0, bArr.length);
        if (i9 == -1) {
            throw new EOFException();
        }
        this.f21711e = 0;
        this.f21712f = i9;
    }

    /* renamed from: v */
    public boolean m24689v() {
        return this.f21712f == -1;
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x002b  */
    /* renamed from: w */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public String m24690w() {
        int i9;
        byte[] bArr;
        int i10;
        synchronized (this.f21708b) {
            if (this.f21710d == null) {
                throw new IOException("LineReader is closed");
            }
            if (this.f21711e >= this.f21712f) {
                m24688u();
            }
            for (int i11 = this.f21711e; i11 != this.f21712f; i11++) {
                byte[] bArr2 = this.f21710d;
                if (bArr2[i11] == 10) {
                    int i12 = this.f21711e;
                    if (i11 != i12) {
                        i10 = i11 - 1;
                        if (bArr2[i10] != 13) {
                            i10 = i11;
                        }
                    }
                    String str = new String(bArr2, i12, i10 - i12, this.f21709c.name());
                    this.f21711e = i11 + 1;
                    return str;
                }
            }
            a aVar = new a((this.f21712f - this.f21711e) + 80);
            loop1: while (true) {
                byte[] bArr3 = this.f21710d;
                int i13 = this.f21711e;
                aVar.write(bArr3, i13, this.f21712f - i13);
                this.f21712f = -1;
                m24688u();
                i9 = this.f21711e;
                while (i9 != this.f21712f) {
                    bArr = this.f21710d;
                    if (bArr[i9] == 10) {
                        break loop1;
                    }
                    i9++;
                }
            }
            int i14 = this.f21711e;
            if (i9 != i14) {
                aVar.write(bArr, i14, i9 - i14);
            }
            this.f21711e = i9 + 1;
            return aVar.toString();
        }
    }

    public C6447b(InputStream inputStream, int i9, Charset charset) {
        if (inputStream == null || charset == null) {
            throw null;
        }
        if (i9 < 0) {
            throw new IllegalArgumentException("capacity <= 0");
        }
        if (!charset.equals(C6448c.f21714a)) {
            throw new IllegalArgumentException("Unsupported encoding");
        }
        this.f21708b = inputStream;
        this.f21709c = charset;
        this.f21710d = new byte[i9];
    }
}
