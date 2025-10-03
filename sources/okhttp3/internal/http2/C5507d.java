package okhttp3.internal.http2;

import java.io.Closeable;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import okhttp3.internal.http2.C5504a;
import p007a6.C0040d;
import p007a6.C0042f;
import p098i6.C5057d;
import p157o6.C5469a;
import p157o6.C5470b;
import p157o6.C5475g;
import p204t6.C6322c;
import p204t6.InterfaceC6323d;

/* renamed from: okhttp3.internal.http2.d */
/* loaded from: classes.dex */
public final class C5507d implements Closeable {

    /* renamed from: h */
    public static final a f18876h = new a(null);

    /* renamed from: i */
    public static final Logger f18877i = Logger.getLogger(C5470b.class.getName());

    /* renamed from: b */
    public final InterfaceC6323d f18878b;

    /* renamed from: c */
    public final boolean f18879c;

    /* renamed from: d */
    public final C6322c f18880d;

    /* renamed from: e */
    public int f18881e;

    /* renamed from: f */
    public boolean f18882f;

    /* renamed from: g */
    public final C5504a.b f18883g;

    /* renamed from: okhttp3.internal.http2.d$a */
    public static final class a {
        public a() {
        }

        public /* synthetic */ a(C0040d c0040d) {
            this();
        }
    }

    public C5507d(InterfaceC6323d interfaceC6323d, boolean z8) {
        C0042f.m158e(interfaceC6323d, "sink");
        this.f18878b = interfaceC6323d;
        this.f18879c = z8;
        C6322c c6322c = new C6322c();
        this.f18880d = c6322c;
        this.f18881e = 16384;
        this.f18883g = new C5504a.b(0, false, c6322c, 3, null);
    }

    /* renamed from: A */
    public final int m21531A() {
        return this.f18881e;
    }

    /* renamed from: B */
    public final synchronized void m21532B(boolean z8, int i9, int i10) {
        if (this.f18882f) {
            throw new IOException("closed");
        }
        m21542x(0, 8, 6, z8 ? 1 : 0);
        this.f18878b.writeInt(i9);
        this.f18878b.writeInt(i10);
        this.f18878b.flush();
    }

    /* renamed from: C */
    public final synchronized void m21533C(int i9, int i10, List<C5469a> list) {
        C0042f.m158e(list, "requestHeaders");
        if (this.f18882f) {
            throw new IOException("closed");
        }
        this.f18883g.m21423g(list);
        long size = this.f18880d.size();
        int iMin = (int) Math.min(this.f18881e - 4, size);
        long j9 = iMin;
        m21542x(i9, iMin + 4, 5, size == j9 ? 4 : 0);
        this.f18878b.writeInt(i10 & Integer.MAX_VALUE);
        this.f18878b.mo21082q(this.f18880d, j9);
        if (size > j9) {
            m21537G(i9, size - j9);
        }
    }

    /* renamed from: D */
    public final synchronized void m21534D(int i9, ErrorCode errorCode) {
        C0042f.m158e(errorCode, "errorCode");
        if (this.f18882f) {
            throw new IOException("closed");
        }
        if (!(errorCode.m21394b() != -1)) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        m21542x(i9, 4, 3, 0);
        this.f18878b.writeInt(errorCode.m21394b());
        this.f18878b.flush();
    }

    /* renamed from: E */
    public final synchronized void m21535E(C5475g c5475g) {
        C0042f.m158e(c5475g, "settings");
        if (this.f18882f) {
            throw new IOException("closed");
        }
        int i9 = 0;
        m21542x(0, c5475g.m21185i() * 6, 4, 0);
        while (i9 < 10) {
            if (c5475g.m21182f(i9)) {
                this.f18878b.writeShort(i9 != 4 ? i9 != 7 ? i9 : 4 : 3);
                this.f18878b.writeInt(c5475g.m21177a(i9));
            }
            i9++;
        }
        this.f18878b.flush();
    }

    /* renamed from: F */
    public final synchronized void m21536F(int i9, long j9) {
        if (this.f18882f) {
            throw new IOException("closed");
        }
        if (!(j9 != 0 && j9 <= 2147483647L)) {
            throw new IllegalArgumentException(("windowSizeIncrement == 0 || windowSizeIncrement > 0x7fffffffL: " + j9).toString());
        }
        m21542x(i9, 4, 8, 0);
        this.f18878b.writeInt((int) j9);
        this.f18878b.flush();
    }

    /* renamed from: G */
    public final void m21537G(int i9, long j9) {
        while (j9 > 0) {
            long jMin = Math.min(this.f18881e, j9);
            j9 -= jMin;
            m21542x(i9, (int) jMin, 9, j9 == 0 ? 4 : 0);
            this.f18878b.mo21082q(this.f18880d, jMin);
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public synchronized void close() {
        this.f18882f = true;
        this.f18878b.close();
    }

    /* renamed from: f */
    public final synchronized void m21538f(C5475g c5475g) {
        C0042f.m158e(c5475g, "peerSettings");
        if (this.f18882f) {
            throw new IOException("closed");
        }
        this.f18881e = c5475g.m21181e(this.f18881e);
        if (c5475g.m21178b() != -1) {
            this.f18883g.m21421e(c5475g.m21178b());
        }
        m21542x(0, 0, 4, 1);
        this.f18878b.flush();
    }

    public final synchronized void flush() {
        if (this.f18882f) {
            throw new IOException("closed");
        }
        this.f18878b.flush();
    }

    /* renamed from: u */
    public final synchronized void m21539u() {
        if (this.f18882f) {
            throw new IOException("closed");
        }
        if (this.f18879c) {
            Logger logger = f18877i;
            if (logger.isLoggable(Level.FINE)) {
                logger.fine(C5057d.m19806t(">> CONNECTION " + C5470b.f18400b.mo21883i(), new Object[0]));
            }
            this.f18878b.mo24227p(C5470b.f18400b);
            this.f18878b.flush();
        }
    }

    /* renamed from: v */
    public final synchronized void m21540v(boolean z8, int i9, C6322c c6322c, int i10) {
        if (this.f18882f) {
            throw new IOException("closed");
        }
        m21541w(i9, z8 ? 1 : 0, c6322c, i10);
    }

    /* renamed from: w */
    public final void m21541w(int i9, int i10, C6322c c6322c, int i11) {
        m21542x(i9, i11, 0, i10);
        if (i11 > 0) {
            InterfaceC6323d interfaceC6323d = this.f18878b;
            C0042f.m155b(c6322c);
            interfaceC6323d.mo21082q(c6322c, i11);
        }
    }

    /* renamed from: x */
    public final void m21542x(int i9, int i10, int i11, int i12) {
        Logger logger = f18877i;
        if (logger.isLoggable(Level.FINE)) {
            logger.fine(C5470b.f18399a.m21118c(false, i9, i10, i11, i12));
        }
        if (!(i10 <= this.f18881e)) {
            throw new IllegalArgumentException(("FRAME_SIZE_ERROR length > " + this.f18881e + ": " + i10).toString());
        }
        if (!((Integer.MIN_VALUE & i9) == 0)) {
            throw new IllegalArgumentException(("reserved bit set: " + i9).toString());
        }
        C5057d.m19786Z(this.f18878b, i10);
        this.f18878b.writeByte(i11 & 255);
        this.f18878b.writeByte(i12 & 255);
        this.f18878b.writeInt(i9 & Integer.MAX_VALUE);
    }

    /* renamed from: y */
    public final synchronized void m21543y(int i9, ErrorCode errorCode, byte[] bArr) {
        C0042f.m158e(errorCode, "errorCode");
        C0042f.m158e(bArr, "debugData");
        if (this.f18882f) {
            throw new IOException("closed");
        }
        if (!(errorCode.m21394b() != -1)) {
            throw new IllegalArgumentException("errorCode.httpCode == -1".toString());
        }
        m21542x(0, bArr.length + 8, 7, 0);
        this.f18878b.writeInt(i9);
        this.f18878b.writeInt(errorCode.m21394b());
        if (!(bArr.length == 0)) {
            this.f18878b.write(bArr);
        }
        this.f18878b.flush();
    }

    /* renamed from: z */
    public final synchronized void m21544z(boolean z8, int i9, List<C5469a> list) {
        C0042f.m158e(list, "headerBlock");
        if (this.f18882f) {
            throw new IOException("closed");
        }
        this.f18883g.m21423g(list);
        long size = this.f18880d.size();
        long jMin = Math.min(this.f18881e, size);
        int i10 = size == jMin ? 4 : 0;
        if (z8) {
            i10 |= 1;
        }
        m21542x(i9, (int) jMin, 1, i10);
        this.f18878b.mo21082q(this.f18880d, jMin);
        if (size > jMin) {
            m21537G(i9, size - jMin);
        }
    }
}
