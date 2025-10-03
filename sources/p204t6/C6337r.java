package p204t6;

import android.support.v4.media.session.PlaybackStateCompat;
import com.google.common.primitives.UnsignedBytes;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import kotlin.text.C5244a;
import okio.ByteString;
import p007a6.C0042f;
import p213u6.C6423a;

/* renamed from: t6.r */
/* loaded from: classes.dex */
public final class C6337r implements InterfaceC6324e {

    /* renamed from: b */
    public final InterfaceC6342w f21362b;

    /* renamed from: c */
    public final C6322c f21363c;

    /* renamed from: d */
    public boolean f21364d;

    public C6337r(InterfaceC6342w interfaceC6342w) {
        C0042f.m158e(interfaceC6342w, "source");
        this.f21362b = interfaceC6342w;
        this.f21363c = new C6322c();
    }

    @Override // p204t6.InterfaceC6342w
    /* renamed from: a */
    public C6343x mo21076a() {
        return this.f21362b.mo21076a();
    }

    @Override // p204t6.InterfaceC6324e, p204t6.InterfaceC6323d
    /* renamed from: b */
    public C6322c mo24215b() {
        return this.f21363c;
    }

    @Override // p204t6.InterfaceC6324e
    /* renamed from: c */
    public InputStream mo24216c() {
        return new a();
    }

    @Override // p204t6.InterfaceC6342w, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws EOFException {
        if (this.f21364d) {
            return;
        }
        this.f21364d = true;
        this.f21362b.close();
        this.f21363c.m24231u();
    }

    @Override // p204t6.InterfaceC6342w
    /* renamed from: d */
    public long mo21077d(C6322c c6322c, long j9) {
        C0042f.m158e(c6322c, "sink");
        if (!(j9 >= 0)) {
            throw new IllegalArgumentException(("byteCount < 0: " + j9).toString());
        }
        if (!(!this.f21364d)) {
            throw new IllegalStateException("closed".toString());
        }
        if (this.f21363c.size() == 0 && this.f21362b.mo21077d(this.f21363c, PlaybackStateCompat.ACTION_PLAY_FROM_URI) == -1) {
            return -1L;
        }
        return this.f21363c.mo21077d(c6322c, Math.min(j9, this.f21363c.size()));
    }

    @Override // p204t6.InterfaceC6324e
    /* renamed from: e */
    public ByteString mo24217e(long j9) throws EOFException {
        mo24228r(j9);
        return this.f21363c.mo24217e(j9);
    }

    /* renamed from: f */
    public long m24281f(byte b9) {
        return m24282u(b9, 0L, Long.MAX_VALUE);
    }

    @Override // p204t6.InterfaceC6324e
    /* renamed from: g */
    public boolean mo24218g() {
        if (!this.f21364d) {
            return this.f21363c.mo24218g() && this.f21362b.mo21077d(this.f21363c, PlaybackStateCompat.ACTION_PLAY_FROM_URI) == -1;
        }
        throw new IllegalStateException("closed".toString());
    }

    @Override // p204t6.InterfaceC6324e
    /* renamed from: i */
    public String mo24220i(long j9) throws EOFException {
        if (!(j9 >= 0)) {
            throw new IllegalArgumentException(("limit < 0: " + j9).toString());
        }
        long j10 = j9 == Long.MAX_VALUE ? Long.MAX_VALUE : j9 + 1;
        long jM24282u = m24282u((byte) 10, 0L, j10);
        if (jM24282u != -1) {
            return C6423a.m24576b(this.f21363c, jM24282u);
        }
        if (j10 < Long.MAX_VALUE && m24285x(j10) && this.f21363c.m24236z(j10 - 1) == 13 && m24285x(1 + j10) && this.f21363c.m24236z(j10) == 10) {
            return C6423a.m24576b(this.f21363c, j10);
        }
        C6322c c6322c = new C6322c();
        C6322c c6322c2 = this.f21363c;
        c6322c2.m24235y(c6322c, 0L, Math.min(32, c6322c2.size()));
        throw new EOFException("\\n not found: limit=" + Math.min(this.f21363c.size(), j9) + " content=" + c6322c.m24192E().mo21883i() + (char) 8230);
    }

    @Override // java.nio.channels.Channel
    public boolean isOpen() {
        return !this.f21364d;
    }

    @Override // p204t6.InterfaceC6324e
    /* renamed from: k */
    public String mo24222k(Charset charset) {
        C0042f.m158e(charset, "charset");
        this.f21363c.mo24219h(this.f21362b);
        return this.f21363c.mo24222k(charset);
    }

    @Override // p204t6.InterfaceC6324e
    /* renamed from: m */
    public String mo24224m() {
        return mo24220i(Long.MAX_VALUE);
    }

    @Override // p204t6.InterfaceC6324e
    /* renamed from: n */
    public int mo24225n(C6334o c6334o) throws EOFException {
        C0042f.m158e(c6334o, "options");
        if (!(!this.f21364d)) {
            throw new IllegalStateException("closed".toString());
        }
        while (true) {
            int iM24577c = C6423a.m24577c(this.f21363c, c6334o, true);
            if (iM24577c != -2) {
                if (iM24577c != -1) {
                    this.f21363c.skip(c6334o.m24272d()[iM24577c].m21892r());
                    return iM24577c;
                }
            } else if (this.f21362b.mo21077d(this.f21363c, PlaybackStateCompat.ACTION_PLAY_FROM_URI) == -1) {
                break;
            }
        }
        return -1;
    }

    @Override // p204t6.InterfaceC6324e
    /* renamed from: o */
    public byte[] mo24226o(long j9) throws EOFException {
        mo24228r(j9);
        return this.f21363c.mo24226o(j9);
    }

    @Override // p204t6.InterfaceC6324e
    /* renamed from: r */
    public void mo24228r(long j9) throws EOFException {
        if (!m24285x(j9)) {
            throw new EOFException();
        }
    }

    @Override // java.nio.channels.ReadableByteChannel
    public int read(ByteBuffer byteBuffer) {
        C0042f.m158e(byteBuffer, "sink");
        if (this.f21363c.size() == 0 && this.f21362b.mo21077d(this.f21363c, PlaybackStateCompat.ACTION_PLAY_FROM_URI) == -1) {
            return -1;
        }
        return this.f21363c.read(byteBuffer);
    }

    @Override // p204t6.InterfaceC6324e
    public byte readByte() throws EOFException {
        mo24228r(1L);
        return this.f21363c.readByte();
    }

    @Override // p204t6.InterfaceC6324e
    public int readInt() throws EOFException {
        mo24228r(4L);
        return this.f21363c.readInt();
    }

    @Override // p204t6.InterfaceC6324e
    public short readShort() throws EOFException {
        mo24228r(2L);
        return this.f21363c.readShort();
    }

    @Override // p204t6.InterfaceC6324e
    public void skip(long j9) throws EOFException {
        if (!(!this.f21364d)) {
            throw new IllegalStateException("closed".toString());
        }
        while (j9 > 0) {
            if (this.f21363c.size() == 0 && this.f21362b.mo21077d(this.f21363c, PlaybackStateCompat.ACTION_PLAY_FROM_URI) == -1) {
                throw new EOFException();
            }
            long jMin = Math.min(j9, this.f21363c.size());
            this.f21363c.skip(jMin);
            j9 -= jMin;
        }
    }

    @Override // p204t6.InterfaceC6324e
    /* renamed from: t */
    public long mo24230t() throws EOFException {
        byte bM24236z;
        mo24228r(1L);
        int i9 = 0;
        while (true) {
            int i10 = i9 + 1;
            if (!m24285x(i10)) {
                break;
            }
            bM24236z = this.f21363c.m24236z(i9);
            if ((bM24236z < 48 || bM24236z > 57) && ((bM24236z < 97 || bM24236z > 102) && (bM24236z < 65 || bM24236z > 70))) {
                break;
            }
            i9 = i10;
        }
        if (i9 == 0) {
            StringBuilder sb = new StringBuilder();
            sb.append("Expected leading [0-9a-fA-F] character but was 0x");
            String string = Integer.toString(bM24236z, C5244a.m20494a(C5244a.m20494a(16)));
            C0042f.m157d(string, "toString(this, checkRadix(radix))");
            sb.append(string);
            throw new NumberFormatException(sb.toString());
        }
        return this.f21363c.mo24230t();
    }

    public String toString() {
        return "buffer(" + this.f21362b + ')';
    }

    /* renamed from: u */
    public long m24282u(byte b9, long j9, long j10) {
        if (!(!this.f21364d)) {
            throw new IllegalStateException("closed".toString());
        }
        if (!(0 <= j9 && j9 <= j10)) {
            throw new IllegalArgumentException(("fromIndex=" + j9 + " toIndex=" + j10).toString());
        }
        while (j9 < j10) {
            long jM24188A = this.f21363c.m24188A(b9, j9, j10);
            if (jM24188A != -1) {
                return jM24188A;
            }
            long size = this.f21363c.size();
            if (size >= j10 || this.f21362b.mo21077d(this.f21363c, PlaybackStateCompat.ACTION_PLAY_FROM_URI) == -1) {
                return -1L;
            }
            j9 = Math.max(j9, size);
        }
        return -1L;
    }

    /* renamed from: v */
    public int m24283v() throws EOFException {
        mo24228r(4L);
        return this.f21363c.m24194G();
    }

    /* renamed from: w */
    public short m24284w() throws EOFException {
        mo24228r(2L);
        return this.f21363c.m24195H();
    }

    /* renamed from: x */
    public boolean m24285x(long j9) {
        if (!(j9 >= 0)) {
            throw new IllegalArgumentException(("byteCount < 0: " + j9).toString());
        }
        if (!(!this.f21364d)) {
            throw new IllegalStateException("closed".toString());
        }
        while (this.f21363c.size() < j9) {
            if (this.f21362b.mo21077d(this.f21363c, PlaybackStateCompat.ACTION_PLAY_FROM_URI) == -1) {
                return false;
            }
        }
        return true;
    }

    /* renamed from: t6.r$a */
    public static final class a extends InputStream {
        public a() {
        }

        @Override // java.io.InputStream
        public int available() throws IOException {
            C6337r c6337r = C6337r.this;
            if (c6337r.f21364d) {
                throw new IOException("closed");
            }
            return (int) Math.min(c6337r.f21363c.size(), Integer.MAX_VALUE);
        }

        @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws EOFException {
            C6337r.this.close();
        }

        @Override // java.io.InputStream
        public int read() throws IOException {
            C6337r c6337r = C6337r.this;
            if (c6337r.f21364d) {
                throw new IOException("closed");
            }
            if (c6337r.f21363c.size() == 0) {
                C6337r c6337r2 = C6337r.this;
                if (c6337r2.f21362b.mo21077d(c6337r2.f21363c, PlaybackStateCompat.ACTION_PLAY_FROM_URI) == -1) {
                    return -1;
                }
            }
            return C6337r.this.f21363c.readByte() & UnsignedBytes.MAX_VALUE;
        }

        public String toString() {
            return C6337r.this + ".inputStream()";
        }

        @Override // java.io.InputStream
        public int read(byte[] bArr, int i9, int i10) throws IOException {
            C0042f.m158e(bArr, "data");
            if (!C6337r.this.f21364d) {
                C6320a.m24153b(bArr.length, i9, i10);
                if (C6337r.this.f21363c.size() == 0) {
                    C6337r c6337r = C6337r.this;
                    if (c6337r.f21362b.mo21077d(c6337r.f21363c, PlaybackStateCompat.ACTION_PLAY_FROM_URI) == -1) {
                        return -1;
                    }
                }
                return C6337r.this.f21363c.read(bArr, i9, i10);
            }
            throw new IOException("closed");
        }
    }
}
