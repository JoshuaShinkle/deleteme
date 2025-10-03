package p204t6;

import android.support.v4.media.session.PlaybackStateCompat;
import java.nio.ByteBuffer;
import okio.ByteString;
import p007a6.C0042f;

/* renamed from: t6.q */
/* loaded from: classes.dex */
public final class C6336q implements InterfaceC6323d {

    /* renamed from: b */
    public final InterfaceC6340u f21359b;

    /* renamed from: c */
    public final C6322c f21360c;

    /* renamed from: d */
    public boolean f21361d;

    public C6336q(InterfaceC6340u interfaceC6340u) {
        C0042f.m158e(interfaceC6340u, "sink");
        this.f21359b = interfaceC6340u;
        this.f21360c = new C6322c();
    }

    @Override // p204t6.InterfaceC6340u
    /* renamed from: a */
    public C6343x mo21081a() {
        return this.f21359b.mo21081a();
    }

    @Override // p204t6.InterfaceC6323d
    /* renamed from: b */
    public C6322c mo24215b() {
        return this.f21360c;
    }

    @Override // p204t6.InterfaceC6340u, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws Throwable {
        if (this.f21361d) {
            return;
        }
        try {
            if (this.f21360c.size() > 0) {
                InterfaceC6340u interfaceC6340u = this.f21359b;
                C6322c c6322c = this.f21360c;
                interfaceC6340u.mo21082q(c6322c, c6322c.size());
            }
            th = null;
        } catch (Throwable th) {
            th = th;
        }
        try {
            this.f21359b.close();
        } catch (Throwable th2) {
            if (th == null) {
                th = th2;
            }
        }
        this.f21361d = true;
        if (th != null) {
            throw th;
        }
    }

    /* renamed from: f */
    public InterfaceC6323d m24280f() {
        if (!(!this.f21361d)) {
            throw new IllegalStateException("closed".toString());
        }
        long jM24233w = this.f21360c.m24233w();
        if (jM24233w > 0) {
            this.f21359b.mo21082q(this.f21360c, jM24233w);
        }
        return this;
    }

    @Override // p204t6.InterfaceC6323d, p204t6.InterfaceC6340u, java.io.Flushable
    public void flush() {
        if (!(!this.f21361d)) {
            throw new IllegalStateException("closed".toString());
        }
        if (this.f21360c.size() > 0) {
            InterfaceC6340u interfaceC6340u = this.f21359b;
            C6322c c6322c = this.f21360c;
            interfaceC6340u.mo21082q(c6322c, c6322c.size());
        }
        this.f21359b.flush();
    }

    @Override // p204t6.InterfaceC6323d
    /* renamed from: h */
    public long mo24219h(InterfaceC6342w interfaceC6342w) {
        C0042f.m158e(interfaceC6342w, "source");
        long j9 = 0;
        while (true) {
            long jMo21077d = interfaceC6342w.mo21077d(this.f21360c, PlaybackStateCompat.ACTION_PLAY_FROM_URI);
            if (jMo21077d == -1) {
                return j9;
            }
            j9 += jMo21077d;
            m24280f();
        }
    }

    @Override // java.nio.channels.Channel
    public boolean isOpen() {
        return !this.f21361d;
    }

    @Override // p204t6.InterfaceC6323d
    /* renamed from: j */
    public InterfaceC6323d mo24221j(String str) {
        C0042f.m158e(str, "string");
        if (!(!this.f21361d)) {
            throw new IllegalStateException("closed".toString());
        }
        this.f21360c.mo24221j(str);
        return m24280f();
    }

    @Override // p204t6.InterfaceC6323d
    /* renamed from: l */
    public InterfaceC6323d mo24223l(long j9) {
        if (!(!this.f21361d)) {
            throw new IllegalStateException("closed".toString());
        }
        this.f21360c.mo24223l(j9);
        return m24280f();
    }

    @Override // p204t6.InterfaceC6323d
    /* renamed from: p */
    public InterfaceC6323d mo24227p(ByteString byteString) {
        C0042f.m158e(byteString, "byteString");
        if (!(!this.f21361d)) {
            throw new IllegalStateException("closed".toString());
        }
        this.f21360c.mo24227p(byteString);
        return m24280f();
    }

    @Override // p204t6.InterfaceC6340u
    /* renamed from: q */
    public void mo21082q(C6322c c6322c, long j9) {
        C0042f.m158e(c6322c, "source");
        if (!(!this.f21361d)) {
            throw new IllegalStateException("closed".toString());
        }
        this.f21360c.mo21082q(c6322c, j9);
        m24280f();
    }

    @Override // p204t6.InterfaceC6323d
    /* renamed from: s */
    public InterfaceC6323d mo24229s(long j9) {
        if (!(!this.f21361d)) {
            throw new IllegalStateException("closed".toString());
        }
        this.f21360c.mo24229s(j9);
        return m24280f();
    }

    public String toString() {
        return "buffer(" + this.f21359b + ')';
    }

    @Override // java.nio.channels.WritableByteChannel
    public int write(ByteBuffer byteBuffer) {
        C0042f.m158e(byteBuffer, "source");
        if (!(!this.f21361d)) {
            throw new IllegalStateException("closed".toString());
        }
        int iWrite = this.f21360c.write(byteBuffer);
        m24280f();
        return iWrite;
    }

    @Override // p204t6.InterfaceC6323d
    public InterfaceC6323d writeByte(int i9) {
        if (!(!this.f21361d)) {
            throw new IllegalStateException("closed".toString());
        }
        this.f21360c.writeByte(i9);
        return m24280f();
    }

    @Override // p204t6.InterfaceC6323d
    public InterfaceC6323d writeInt(int i9) {
        if (!(!this.f21361d)) {
            throw new IllegalStateException("closed".toString());
        }
        this.f21360c.writeInt(i9);
        return m24280f();
    }

    @Override // p204t6.InterfaceC6323d
    public InterfaceC6323d writeShort(int i9) {
        if (!(!this.f21361d)) {
            throw new IllegalStateException("closed".toString());
        }
        this.f21360c.writeShort(i9);
        return m24280f();
    }

    @Override // p204t6.InterfaceC6323d
    public InterfaceC6323d write(byte[] bArr) {
        C0042f.m158e(bArr, "source");
        if (!this.f21361d) {
            this.f21360c.write(bArr);
            return m24280f();
        }
        throw new IllegalStateException("closed".toString());
    }

    @Override // p204t6.InterfaceC6323d
    public InterfaceC6323d write(byte[] bArr, int i9, int i10) {
        C0042f.m158e(bArr, "source");
        if (!this.f21361d) {
            this.f21360c.write(bArr, i9, i10);
            return m24280f();
        }
        throw new IllegalStateException("closed".toString());
    }
}
