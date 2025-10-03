package okhttp3.internal.http2;

import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import okhttp3.internal.http2.C5504a;
import okio.ByteString;
import p007a6.C0040d;
import p007a6.C0042f;
import p027c6.C0745a;
import p027c6.C0749e;
import p098i6.C5057d;
import p157o6.C5469a;
import p157o6.C5470b;
import p157o6.C5475g;
import p204t6.C6322c;
import p204t6.C6343x;
import p204t6.InterfaceC6324e;
import p204t6.InterfaceC6342w;

/* renamed from: okhttp3.internal.http2.c */
/* loaded from: classes.dex */
public final class C5506c implements Closeable {

    /* renamed from: f */
    public static final a f18864f = new a(null);

    /* renamed from: g */
    public static final Logger f18865g;

    /* renamed from: b */
    public final InterfaceC6324e f18866b;

    /* renamed from: c */
    public final boolean f18867c;

    /* renamed from: d */
    public final b f18868d;

    /* renamed from: e */
    public final C5504a.a f18869e;

    /* renamed from: okhttp3.internal.http2.c$a */
    public static final class a {
        public a() {
        }

        public /* synthetic */ a(C0040d c0040d) {
            this();
        }

        /* renamed from: a */
        public final Logger m21522a() {
            return C5506c.f18865g;
        }

        /* renamed from: b */
        public final int m21523b(int i9, int i10, int i11) throws IOException {
            if ((i10 & 8) != 0) {
                i9--;
            }
            if (i11 <= i9) {
                return i9 - i11;
            }
            throw new IOException("PROTOCOL_ERROR padding " + i11 + " > remaining length " + i9);
        }
    }

    /* renamed from: okhttp3.internal.http2.c$b */
    public static final class b implements InterfaceC6342w {

        /* renamed from: b */
        public final InterfaceC6324e f18870b;

        /* renamed from: c */
        public int f18871c;

        /* renamed from: d */
        public int f18872d;

        /* renamed from: e */
        public int f18873e;

        /* renamed from: f */
        public int f18874f;

        /* renamed from: g */
        public int f18875g;

        public b(InterfaceC6324e interfaceC6324e) {
            C0042f.m158e(interfaceC6324e, "source");
            this.f18870b = interfaceC6324e;
        }

        @Override // p204t6.InterfaceC6342w
        /* renamed from: a */
        public C6343x mo21076a() {
            return this.f18870b.mo21076a();
        }

        @Override // p204t6.InterfaceC6342w, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
        }

        @Override // p204t6.InterfaceC6342w
        /* renamed from: d */
        public long mo21077d(C6322c c6322c, long j9) throws IOException {
            C0042f.m158e(c6322c, "sink");
            while (true) {
                int i9 = this.f18874f;
                if (i9 != 0) {
                    long jMo21077d = this.f18870b.mo21077d(c6322c, Math.min(j9, i9));
                    if (jMo21077d == -1) {
                        return -1L;
                    }
                    this.f18874f -= (int) jMo21077d;
                    return jMo21077d;
                }
                this.f18870b.skip(this.f18875g);
                this.f18875g = 0;
                if ((this.f18872d & 4) != 0) {
                    return -1L;
                }
                m21525u();
            }
        }

        /* renamed from: f */
        public final int m21524f() {
            return this.f18874f;
        }

        /* renamed from: u */
        public final void m21525u() throws IOException {
            int i9 = this.f18873e;
            int iM19770J = C5057d.m19770J(this.f18870b);
            this.f18874f = iM19770J;
            this.f18871c = iM19770J;
            int iM19790d = C5057d.m19790d(this.f18870b.readByte(), 255);
            this.f18872d = C5057d.m19790d(this.f18870b.readByte(), 255);
            a aVar = C5506c.f18864f;
            if (aVar.m21522a().isLoggable(Level.FINE)) {
                aVar.m21522a().fine(C5470b.f18399a.m21118c(true, this.f18873e, this.f18871c, iM19790d, this.f18872d));
            }
            int i10 = this.f18870b.readInt() & Integer.MAX_VALUE;
            this.f18873e = i10;
            if (iM19790d == 9) {
                if (i10 != i9) {
                    throw new IOException("TYPE_CONTINUATION streamId changed");
                }
            } else {
                throw new IOException(iM19790d + " != TYPE_CONTINUATION");
            }
        }

        /* renamed from: v */
        public final void m21526v(int i9) {
            this.f18872d = i9;
        }

        /* renamed from: w */
        public final void m21527w(int i9) {
            this.f18874f = i9;
        }

        /* renamed from: x */
        public final void m21528x(int i9) {
            this.f18871c = i9;
        }

        /* renamed from: y */
        public final void m21529y(int i9) {
            this.f18875g = i9;
        }

        /* renamed from: z */
        public final void m21530z(int i9) {
            this.f18873e = i9;
        }
    }

    /* renamed from: okhttp3.internal.http2.c$c */
    public interface c {
        /* renamed from: b */
        void mo21496b(boolean z8, C5475g c5475g);

        /* renamed from: c */
        void mo21497c();

        /* renamed from: d */
        void mo21498d(boolean z8, int i9, int i10, List<C5469a> list);

        /* renamed from: e */
        void mo21499e(int i9, long j9);

        /* renamed from: f */
        void mo21500f(boolean z8, int i9, InterfaceC6324e interfaceC6324e, int i10);

        /* renamed from: g */
        void mo21501g(boolean z8, int i9, int i10);

        /* renamed from: h */
        void mo21502h(int i9, int i10, int i11, boolean z8);

        /* renamed from: i */
        void mo21503i(int i9, ErrorCode errorCode);

        /* renamed from: j */
        void mo21504j(int i9, int i10, List<C5469a> list);

        /* renamed from: k */
        void mo21505k(int i9, ErrorCode errorCode, ByteString byteString);
    }

    static {
        Logger logger = Logger.getLogger(C5470b.class.getName());
        C0042f.m157d(logger, "getLogger(Http2::class.java.name)");
        f18865g = logger;
    }

    public C5506c(InterfaceC6324e interfaceC6324e, boolean z8) {
        C0042f.m158e(interfaceC6324e, "source");
        this.f18866b = interfaceC6324e;
        this.f18867c = z8;
        b bVar = new b(interfaceC6324e);
        this.f18868d = bVar;
        this.f18869e = new C5504a.a(bVar, 4096, 0, 4, null);
    }

    /* renamed from: A */
    public final void m21509A(c cVar, int i9, int i10, int i11) throws IOException {
        if (i9 != 8) {
            throw new IOException("TYPE_PING length != 8: " + i9);
        }
        if (i11 != 0) {
            throw new IOException("TYPE_PING streamId != 0");
        }
        cVar.mo21501g((i10 & 1) != 0, this.f18866b.readInt(), this.f18866b.readInt());
    }

    /* renamed from: B */
    public final void m21510B(c cVar, int i9) {
        int i10 = this.f18866b.readInt();
        cVar.mo21502h(i9, i10 & Integer.MAX_VALUE, C5057d.m19790d(this.f18866b.readByte(), 255) + 1, (Integer.MIN_VALUE & i10) != 0);
    }

    /* renamed from: C */
    public final void m21511C(c cVar, int i9, int i10, int i11) throws IOException {
        if (i9 == 5) {
            if (i11 == 0) {
                throw new IOException("TYPE_PRIORITY streamId == 0");
            }
            m21510B(cVar, i11);
        } else {
            throw new IOException("TYPE_PRIORITY length: " + i9 + " != 5");
        }
    }

    /* renamed from: D */
    public final void m21512D(c cVar, int i9, int i10, int i11) throws IOException {
        if (i11 == 0) {
            throw new IOException("PROTOCOL_ERROR: TYPE_PUSH_PROMISE streamId == 0");
        }
        int iM19790d = (i10 & 8) != 0 ? C5057d.m19790d(this.f18866b.readByte(), 255) : 0;
        cVar.mo21504j(i11, this.f18866b.readInt() & Integer.MAX_VALUE, m21520y(f18864f.m21523b(i9 - 4, i10, iM19790d), iM19790d, i10, i11));
    }

    /* renamed from: E */
    public final void m21513E(c cVar, int i9, int i10, int i11) throws IOException {
        if (i9 != 4) {
            throw new IOException("TYPE_RST_STREAM length: " + i9 + " != 4");
        }
        if (i11 == 0) {
            throw new IOException("TYPE_RST_STREAM streamId == 0");
        }
        int i12 = this.f18866b.readInt();
        ErrorCode errorCodeM21395a = ErrorCode.f18750b.m21395a(i12);
        if (errorCodeM21395a != null) {
            cVar.mo21503i(i11, errorCodeM21395a);
            return;
        }
        throw new IOException("TYPE_RST_STREAM unexpected error code: " + i12);
    }

    /* renamed from: F */
    public final void m21514F(c cVar, int i9, int i10, int i11) throws IOException {
        int i12;
        if (i11 != 0) {
            throw new IOException("TYPE_SETTINGS streamId != 0");
        }
        if ((i10 & 1) != 0) {
            if (i9 != 0) {
                throw new IOException("FRAME_SIZE_ERROR ack frame should be empty!");
            }
            cVar.mo21497c();
            return;
        }
        if (i9 % 6 != 0) {
            throw new IOException("TYPE_SETTINGS length % 6 != 0: " + i9);
        }
        C5475g c5475g = new C5475g();
        C0745a c0745aM3625f = C0749e.m3625f(C0749e.m3626g(0, i9), 6);
        int iM3610a = c0745aM3625f.m3610a();
        int iM3611b = c0745aM3625f.m3611b();
        int iM3612c = c0745aM3625f.m3612c();
        if ((iM3612c > 0 && iM3610a <= iM3611b) || (iM3612c < 0 && iM3611b <= iM3610a)) {
            while (true) {
                int iM19791e = C5057d.m19791e(this.f18866b.readShort(), 65535);
                i12 = this.f18866b.readInt();
                if (iM19791e != 2) {
                    if (iM19791e == 3) {
                        iM19791e = 4;
                    } else if (iM19791e != 4) {
                        if (iM19791e == 5 && (i12 < 16384 || i12 > 16777215)) {
                            break;
                        }
                    } else {
                        if (i12 < 0) {
                            throw new IOException("PROTOCOL_ERROR SETTINGS_INITIAL_WINDOW_SIZE > 2^31 - 1");
                        }
                        iM19791e = 7;
                    }
                } else if (i12 != 0 && i12 != 1) {
                    throw new IOException("PROTOCOL_ERROR SETTINGS_ENABLE_PUSH != 0 or 1");
                }
                c5475g.m21184h(iM19791e, i12);
                if (iM3610a == iM3611b) {
                    break;
                } else {
                    iM3610a += iM3612c;
                }
            }
            throw new IOException("PROTOCOL_ERROR SETTINGS_MAX_FRAME_SIZE: " + i12);
        }
        cVar.mo21496b(false, c5475g);
    }

    /* renamed from: G */
    public final void m21515G(c cVar, int i9, int i10, int i11) throws IOException {
        if (i9 != 4) {
            throw new IOException("TYPE_WINDOW_UPDATE length !=4: " + i9);
        }
        long jM19792f = C5057d.m19792f(this.f18866b.readInt(), 2147483647L);
        if (jM19792f == 0) {
            throw new IOException("windowSizeIncrement was 0");
        }
        cVar.mo21499e(i11, jM19792f);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.f18866b.close();
    }

    /* renamed from: u */
    public final boolean m21516u(boolean z8, c cVar) throws IOException {
        C0042f.m158e(cVar, "handler");
        try {
            this.f18866b.mo24228r(9L);
            int iM19770J = C5057d.m19770J(this.f18866b);
            if (iM19770J > 16384) {
                throw new IOException("FRAME_SIZE_ERROR: " + iM19770J);
            }
            int iM19790d = C5057d.m19790d(this.f18866b.readByte(), 255);
            int iM19790d2 = C5057d.m19790d(this.f18866b.readByte(), 255);
            int i9 = this.f18866b.readInt() & Integer.MAX_VALUE;
            Logger logger = f18865g;
            if (logger.isLoggable(Level.FINE)) {
                logger.fine(C5470b.f18399a.m21118c(true, i9, iM19770J, iM19790d, iM19790d2));
            }
            if (z8 && iM19790d != 4) {
                throw new IOException("Expected a SETTINGS frame but was " + C5470b.f18399a.m21117b(iM19790d));
            }
            switch (iM19790d) {
                case 0:
                    m21518w(cVar, iM19770J, iM19790d2, i9);
                    return true;
                case 1:
                    m21521z(cVar, iM19770J, iM19790d2, i9);
                    return true;
                case 2:
                    m21511C(cVar, iM19770J, iM19790d2, i9);
                    return true;
                case 3:
                    m21513E(cVar, iM19770J, iM19790d2, i9);
                    return true;
                case 4:
                    m21514F(cVar, iM19770J, iM19790d2, i9);
                    return true;
                case 5:
                    m21512D(cVar, iM19770J, iM19790d2, i9);
                    return true;
                case 6:
                    m21509A(cVar, iM19770J, iM19790d2, i9);
                    return true;
                case 7:
                    m21519x(cVar, iM19770J, iM19790d2, i9);
                    return true;
                case 8:
                    m21515G(cVar, iM19770J, iM19790d2, i9);
                    return true;
                default:
                    this.f18866b.skip(iM19770J);
                    return true;
            }
        } catch (EOFException unused) {
            return false;
        }
    }

    /* renamed from: v */
    public final void m21517v(c cVar) throws IOException {
        C0042f.m158e(cVar, "handler");
        if (this.f18867c) {
            if (!m21516u(true, cVar)) {
                throw new IOException("Required SETTINGS preface not received");
            }
            return;
        }
        InterfaceC6324e interfaceC6324e = this.f18866b;
        ByteString byteString = C5470b.f18400b;
        ByteString byteStringMo24217e = interfaceC6324e.mo24217e(byteString.m21892r());
        Logger logger = f18865g;
        if (logger.isLoggable(Level.FINE)) {
            logger.fine(C5057d.m19806t("<< CONNECTION " + byteStringMo24217e.mo21883i(), new Object[0]));
        }
        if (C0042f.m154a(byteString, byteStringMo24217e)) {
            return;
        }
        throw new IOException("Expected a connection header but was " + byteStringMo24217e.m21895u());
    }

    /* renamed from: w */
    public final void m21518w(c cVar, int i9, int i10, int i11) throws IOException {
        if (i11 == 0) {
            throw new IOException("PROTOCOL_ERROR: TYPE_DATA streamId == 0");
        }
        boolean z8 = (i10 & 1) != 0;
        if ((i10 & 32) != 0) {
            throw new IOException("PROTOCOL_ERROR: FLAG_COMPRESSED without SETTINGS_COMPRESS_DATA");
        }
        int iM19790d = (i10 & 8) != 0 ? C5057d.m19790d(this.f18866b.readByte(), 255) : 0;
        cVar.mo21500f(z8, i11, this.f18866b, f18864f.m21523b(i9, i10, iM19790d));
        this.f18866b.skip(iM19790d);
    }

    /* renamed from: x */
    public final void m21519x(c cVar, int i9, int i10, int i11) throws IOException {
        if (i9 < 8) {
            throw new IOException("TYPE_GOAWAY length < 8: " + i9);
        }
        if (i11 != 0) {
            throw new IOException("TYPE_GOAWAY streamId != 0");
        }
        int i12 = this.f18866b.readInt();
        int i13 = this.f18866b.readInt();
        int i14 = i9 - 8;
        ErrorCode errorCodeM21395a = ErrorCode.f18750b.m21395a(i13);
        if (errorCodeM21395a == null) {
            throw new IOException("TYPE_GOAWAY unexpected error code: " + i13);
        }
        ByteString byteStringMo24217e = ByteString.f19096e;
        if (i14 > 0) {
            byteStringMo24217e = this.f18866b.mo24217e(i14);
        }
        cVar.mo21505k(i12, errorCodeM21395a, byteStringMo24217e);
    }

    /* renamed from: y */
    public final List<C5469a> m21520y(int i9, int i10, int i11, int i12) throws IOException {
        this.f18868d.m21527w(i9);
        b bVar = this.f18868d;
        bVar.m21528x(bVar.m21524f());
        this.f18868d.m21529y(i10);
        this.f18868d.m21526v(i11);
        this.f18868d.m21530z(i12);
        this.f18869e.m21410k();
        return this.f18869e.m21404e();
    }

    /* renamed from: z */
    public final void m21521z(c cVar, int i9, int i10, int i11) throws IOException {
        if (i11 == 0) {
            throw new IOException("PROTOCOL_ERROR: TYPE_HEADERS streamId == 0");
        }
        boolean z8 = (i10 & 1) != 0;
        int iM19790d = (i10 & 8) != 0 ? C5057d.m19790d(this.f18866b.readByte(), 255) : 0;
        if ((i10 & 32) != 0) {
            m21510B(cVar, i11);
            i9 -= 5;
        }
        cVar.mo21498d(z8, i11, -1, m21520y(f18864f.m21523b(i9, i10, iM19790d), iM19790d, i10, i11));
    }
}
