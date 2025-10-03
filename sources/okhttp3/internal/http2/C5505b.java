package okhttp3.internal.http2;

import com.google.android.exoplayer2.C3322C;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import java.io.Closeable;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.Socket;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import kotlin.jvm.internal.Ref$IntRef;
import kotlin.jvm.internal.Ref$ObjectRef;
import okhttp3.internal.http2.C5506c;
import okio.ByteString;
import p007a6.C0040d;
import p007a6.C0042f;
import p098i6.C5057d;
import p129l6.AbstractC5294a;
import p129l6.C5296c;
import p129l6.C5297d;
import p129l6.C5298e;
import p157o6.C5469a;
import p157o6.C5472d;
import p157o6.C5475g;
import p157o6.InterfaceC5474f;
import p168p6.C6113j;
import p203t5.C6319g;
import p204t6.C6322c;
import p204t6.InterfaceC6323d;
import p204t6.InterfaceC6324e;
import p257z5.InterfaceC6831a;

/* renamed from: okhttp3.internal.http2.b */
/* loaded from: classes.dex */
public final class C5505b implements Closeable {

    /* renamed from: D */
    public static final b f18787D = new b(null);

    /* renamed from: E */
    public static final C5475g f18788E;

    /* renamed from: A */
    public final C5507d f18789A;

    /* renamed from: B */
    public final d f18790B;

    /* renamed from: C */
    public final Set<Integer> f18791C;

    /* renamed from: b */
    public final boolean f18792b;

    /* renamed from: c */
    public final c f18793c;

    /* renamed from: d */
    public final Map<Integer, C5472d> f18794d;

    /* renamed from: e */
    public final String f18795e;

    /* renamed from: f */
    public int f18796f;

    /* renamed from: g */
    public int f18797g;

    /* renamed from: h */
    public boolean f18798h;

    /* renamed from: i */
    public final C5298e f18799i;

    /* renamed from: j */
    public final C5297d f18800j;

    /* renamed from: k */
    public final C5297d f18801k;

    /* renamed from: l */
    public final C5297d f18802l;

    /* renamed from: m */
    public final InterfaceC5474f f18803m;

    /* renamed from: n */
    public long f18804n;

    /* renamed from: o */
    public long f18805o;

    /* renamed from: p */
    public long f18806p;

    /* renamed from: q */
    public long f18807q;

    /* renamed from: r */
    public long f18808r;

    /* renamed from: s */
    public long f18809s;

    /* renamed from: t */
    public final C5475g f18810t;

    /* renamed from: u */
    public C5475g f18811u;

    /* renamed from: v */
    public long f18812v;

    /* renamed from: w */
    public long f18813w;

    /* renamed from: x */
    public long f18814x;

    /* renamed from: y */
    public long f18815y;

    /* renamed from: z */
    public final Socket f18816z;

    /* renamed from: okhttp3.internal.http2.b$a */
    public static final class a {

        /* renamed from: a */
        public boolean f18817a;

        /* renamed from: b */
        public final C5298e f18818b;

        /* renamed from: c */
        public Socket f18819c;

        /* renamed from: d */
        public String f18820d;

        /* renamed from: e */
        public InterfaceC6324e f18821e;

        /* renamed from: f */
        public InterfaceC6323d f18822f;

        /* renamed from: g */
        public c f18823g;

        /* renamed from: h */
        public InterfaceC5474f f18824h;

        /* renamed from: i */
        public int f18825i;

        public a(boolean z8, C5298e c5298e) {
            C0042f.m158e(c5298e, "taskRunner");
            this.f18817a = z8;
            this.f18818b = c5298e;
            this.f18823g = c.f18827b;
            this.f18824h = InterfaceC5474f.f18449b;
        }

        /* renamed from: a */
        public final C5505b m21478a() {
            return new C5505b(this);
        }

        /* renamed from: b */
        public final boolean m21479b() {
            return this.f18817a;
        }

        /* renamed from: c */
        public final String m21480c() {
            String str = this.f18820d;
            if (str != null) {
                return str;
            }
            C0042f.m167n("connectionName");
            return null;
        }

        /* renamed from: d */
        public final c m21481d() {
            return this.f18823g;
        }

        /* renamed from: e */
        public final int m21482e() {
            return this.f18825i;
        }

        /* renamed from: f */
        public final InterfaceC5474f m21483f() {
            return this.f18824h;
        }

        /* renamed from: g */
        public final InterfaceC6323d m21484g() {
            InterfaceC6323d interfaceC6323d = this.f18822f;
            if (interfaceC6323d != null) {
                return interfaceC6323d;
            }
            C0042f.m167n("sink");
            return null;
        }

        /* renamed from: h */
        public final Socket m21485h() {
            Socket socket = this.f18819c;
            if (socket != null) {
                return socket;
            }
            C0042f.m167n("socket");
            return null;
        }

        /* renamed from: i */
        public final InterfaceC6324e m21486i() {
            InterfaceC6324e interfaceC6324e = this.f18821e;
            if (interfaceC6324e != null) {
                return interfaceC6324e;
            }
            C0042f.m167n("source");
            return null;
        }

        /* renamed from: j */
        public final C5298e m21487j() {
            return this.f18818b;
        }

        /* renamed from: k */
        public final a m21488k(c cVar) {
            C0042f.m158e(cVar, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
            this.f18823g = cVar;
            return this;
        }

        /* renamed from: l */
        public final a m21489l(int i9) {
            this.f18825i = i9;
            return this;
        }

        /* renamed from: m */
        public final void m21490m(String str) {
            C0042f.m158e(str, "<set-?>");
            this.f18820d = str;
        }

        /* renamed from: n */
        public final void m21491n(InterfaceC6323d interfaceC6323d) {
            C0042f.m158e(interfaceC6323d, "<set-?>");
            this.f18822f = interfaceC6323d;
        }

        /* renamed from: o */
        public final void m21492o(Socket socket) {
            C0042f.m158e(socket, "<set-?>");
            this.f18819c = socket;
        }

        /* renamed from: p */
        public final void m21493p(InterfaceC6324e interfaceC6324e) {
            C0042f.m158e(interfaceC6324e, "<set-?>");
            this.f18821e = interfaceC6324e;
        }

        /* renamed from: q */
        public final a m21494q(Socket socket, String str, InterfaceC6324e interfaceC6324e, InterfaceC6323d interfaceC6323d) {
            String str2;
            C0042f.m158e(socket, "socket");
            C0042f.m158e(str, "peerName");
            C0042f.m158e(interfaceC6324e, "source");
            C0042f.m158e(interfaceC6323d, "sink");
            m21492o(socket);
            if (this.f18817a) {
                str2 = C5057d.f17451i + ' ' + str;
            } else {
                str2 = "MockWebServer " + str;
            }
            m21490m(str2);
            m21493p(interfaceC6324e);
            m21491n(interfaceC6323d);
            return this;
        }
    }

    /* renamed from: okhttp3.internal.http2.b$b */
    public static final class b {
        public b() {
        }

        public /* synthetic */ b(C0040d c0040d) {
            this();
        }

        /* renamed from: a */
        public final C5475g m21495a() {
            return C5505b.f18788E;
        }
    }

    /* renamed from: okhttp3.internal.http2.b$c */
    public static abstract class c {

        /* renamed from: a */
        public static final b f18826a = new b(null);

        /* renamed from: b */
        public static final c f18827b = new a();

        /* renamed from: okhttp3.internal.http2.b$c$a */
        public static final class a extends c {
            @Override // okhttp3.internal.http2.C5505b.c
            /* renamed from: b */
            public void mo21278b(C5472d c5472d) {
                C0042f.m158e(c5472d, "stream");
                c5472d.m21131d(ErrorCode.REFUSED_STREAM, null);
            }
        }

        /* renamed from: okhttp3.internal.http2.b$c$b */
        public static final class b {
            public b() {
            }

            public /* synthetic */ b(C0040d c0040d) {
                this();
            }
        }

        /* renamed from: a */
        public void mo21277a(C5505b c5505b, C5475g c5475g) {
            C0042f.m158e(c5505b, "connection");
            C0042f.m158e(c5475g, "settings");
        }

        /* renamed from: b */
        public abstract void mo21278b(C5472d c5472d);
    }

    /* renamed from: okhttp3.internal.http2.b$d */
    public final class d implements C5506c.c, InterfaceC6831a<C6319g> {

        /* renamed from: b */
        public final C5506c f18828b;

        /* renamed from: c */
        public final /* synthetic */ C5505b f18829c;

        /* renamed from: okhttp3.internal.http2.b$d$a */
        public static final class a extends AbstractC5294a {

            /* renamed from: e */
            public final /* synthetic */ C5505b f18830e;

            /* renamed from: f */
            public final /* synthetic */ Ref$ObjectRef f18831f;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(String str, boolean z8, C5505b c5505b, Ref$ObjectRef ref$ObjectRef) {
                super(str, z8);
                this.f18830e = c5505b;
                this.f18831f = ref$ObjectRef;
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // p129l6.AbstractC5294a
            /* renamed from: f */
            public long mo20652f() {
                this.f18830e.m21449Q().mo21277a(this.f18830e, (C5475g) this.f18831f.element);
                return -1L;
            }
        }

        /* renamed from: okhttp3.internal.http2.b$d$b */
        public static final class b extends AbstractC5294a {

            /* renamed from: e */
            public final /* synthetic */ C5505b f18832e;

            /* renamed from: f */
            public final /* synthetic */ C5472d f18833f;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public b(String str, boolean z8, C5505b c5505b, C5472d c5472d) {
                super(str, z8);
                this.f18832e = c5505b;
                this.f18833f = c5472d;
            }

            @Override // p129l6.AbstractC5294a
            /* renamed from: f */
            public long mo20652f() {
                try {
                    this.f18832e.m21449Q().mo21278b(this.f18833f);
                    return -1L;
                } catch (IOException e9) {
                    C6113j.f20745a.m23447g().m23440j("Http2Connection.Listener failure for " + this.f18832e.m21447O(), 4, e9);
                    try {
                        this.f18833f.m21131d(ErrorCode.PROTOCOL_ERROR, e9);
                        return -1L;
                    } catch (IOException unused) {
                        return -1L;
                    }
                }
            }
        }

        /* renamed from: okhttp3.internal.http2.b$d$c */
        public static final class c extends AbstractC5294a {

            /* renamed from: e */
            public final /* synthetic */ C5505b f18834e;

            /* renamed from: f */
            public final /* synthetic */ int f18835f;

            /* renamed from: g */
            public final /* synthetic */ int f18836g;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public c(String str, boolean z8, C5505b c5505b, int i9, int i10) {
                super(str, z8);
                this.f18834e = c5505b;
                this.f18835f = i9;
                this.f18836g = i10;
            }

            @Override // p129l6.AbstractC5294a
            /* renamed from: f */
            public long mo20652f() throws IOException {
                this.f18834e.m21474q0(true, this.f18835f, this.f18836g);
                return -1L;
            }
        }

        /* renamed from: okhttp3.internal.http2.b$d$d, reason: collision with other inner class name */
        public static final class C6875d extends AbstractC5294a {

            /* renamed from: e */
            public final /* synthetic */ d f18837e;

            /* renamed from: f */
            public final /* synthetic */ boolean f18838f;

            /* renamed from: g */
            public final /* synthetic */ C5475g f18839g;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C6875d(String str, boolean z8, d dVar, boolean z9, C5475g c5475g) {
                super(str, z8);
                this.f18837e = dVar;
                this.f18838f = z9;
                this.f18839g = c5475g;
            }

            @Override // p129l6.AbstractC5294a
            /* renamed from: f */
            public long mo20652f() {
                this.f18837e.m21506l(this.f18838f, this.f18839g);
                return -1L;
            }
        }

        public d(C5505b c5505b, C5506c c5506c) {
            C0042f.m158e(c5506c, "reader");
            this.f18829c = c5505b;
            this.f18828b = c5506c;
        }

        @Override // p257z5.InterfaceC6831a
        /* renamed from: a */
        public /* bridge */ /* synthetic */ C6319g mo21200a() throws Throwable {
            m21507m();
            return C6319g.f21314a;
        }

        @Override // okhttp3.internal.http2.C5506c.c
        /* renamed from: b */
        public void mo21496b(boolean z8, C5475g c5475g) {
            C0042f.m158e(c5475g, "settings");
            this.f18829c.f18800j.m20666i(new C6875d(this.f18829c.m21447O() + " applyAndAckSettings", true, this, z8, c5475g), 0L);
        }

        @Override // okhttp3.internal.http2.C5506c.c
        /* renamed from: c */
        public void mo21497c() {
        }

        @Override // okhttp3.internal.http2.C5506c.c
        /* renamed from: d */
        public void mo21498d(boolean z8, int i9, int i10, List<C5469a> list) {
            C0042f.m158e(list, "headerBlock");
            if (this.f18829c.m21464f0(i9)) {
                this.f18829c.m21461c0(i9, list, z8);
                return;
            }
            C5505b c5505b = this.f18829c;
            synchronized (c5505b) {
                C5472d c5472dM21453U = c5505b.m21453U(i9);
                if (c5472dM21453U != null) {
                    C6319g c6319g = C6319g.f21314a;
                    c5472dM21453U.m21151x(C5057d.m19776P(list), z8);
                    return;
                }
                if (c5505b.f18798h) {
                    return;
                }
                if (i9 <= c5505b.m21448P()) {
                    return;
                }
                if (i9 % 2 == c5505b.m21450R() % 2) {
                    return;
                }
                C5472d c5472d = new C5472d(i9, c5505b, false, z8, C5057d.m19776P(list));
                c5505b.m21467i0(i9);
                c5505b.m21454V().put(Integer.valueOf(i9), c5472d);
                c5505b.f18799i.m20679i().m20666i(new b(c5505b.m21447O() + '[' + i9 + "] onStream", true, c5505b, c5472d), 0L);
            }
        }

        @Override // okhttp3.internal.http2.C5506c.c
        /* renamed from: e */
        public void mo21499e(int i9, long j9) {
            if (i9 == 0) {
                C5505b c5505b = this.f18829c;
                synchronized (c5505b) {
                    c5505b.f18815y = c5505b.m21455W() + j9;
                    C0042f.m156c(c5505b, "null cannot be cast to non-null type java.lang.Object");
                    c5505b.notifyAll();
                    C6319g c6319g = C6319g.f21314a;
                }
                return;
            }
            C5472d c5472dM21453U = this.f18829c.m21453U(i9);
            if (c5472dM21453U != null) {
                synchronized (c5472dM21453U) {
                    c5472dM21453U.m21128a(j9);
                    C6319g c6319g2 = C6319g.f21314a;
                }
            }
        }

        @Override // okhttp3.internal.http2.C5506c.c
        /* renamed from: f */
        public void mo21500f(boolean z8, int i9, InterfaceC6324e interfaceC6324e, int i10) {
            C0042f.m158e(interfaceC6324e, "source");
            if (this.f18829c.m21464f0(i9)) {
                this.f18829c.m21460b0(i9, interfaceC6324e, i10, z8);
                return;
            }
            C5472d c5472dM21453U = this.f18829c.m21453U(i9);
            if (c5472dM21453U == null) {
                this.f18829c.m21476s0(i9, ErrorCode.PROTOCOL_ERROR);
                long j9 = i10;
                this.f18829c.m21471n0(j9);
                interfaceC6324e.skip(j9);
                return;
            }
            c5472dM21453U.m21150w(interfaceC6324e, i10);
            if (z8) {
                c5472dM21453U.m21151x(C5057d.f17444b, true);
            }
        }

        @Override // okhttp3.internal.http2.C5506c.c
        /* renamed from: g */
        public void mo21501g(boolean z8, int i9, int i10) {
            if (!z8) {
                this.f18829c.f18800j.m20666i(new c(this.f18829c.m21447O() + " ping", true, this.f18829c, i9, i10), 0L);
                return;
            }
            C5505b c5505b = this.f18829c;
            synchronized (c5505b) {
                if (i9 == 1) {
                    c5505b.f18805o++;
                } else if (i9 != 2) {
                    if (i9 == 3) {
                        c5505b.f18808r++;
                        C0042f.m156c(c5505b, "null cannot be cast to non-null type java.lang.Object");
                        c5505b.notifyAll();
                    }
                    C6319g c6319g = C6319g.f21314a;
                } else {
                    c5505b.f18807q++;
                }
            }
        }

        @Override // okhttp3.internal.http2.C5506c.c
        /* renamed from: h */
        public void mo21502h(int i9, int i10, int i11, boolean z8) {
        }

        @Override // okhttp3.internal.http2.C5506c.c
        /* renamed from: i */
        public void mo21503i(int i9, ErrorCode errorCode) {
            C0042f.m158e(errorCode, "errorCode");
            if (this.f18829c.m21464f0(i9)) {
                this.f18829c.m21463e0(i9, errorCode);
                return;
            }
            C5472d c5472dM21465g0 = this.f18829c.m21465g0(i9);
            if (c5472dM21465g0 != null) {
                c5472dM21465g0.m21152y(errorCode);
            }
        }

        @Override // okhttp3.internal.http2.C5506c.c
        /* renamed from: j */
        public void mo21504j(int i9, int i10, List<C5469a> list) {
            C0042f.m158e(list, "requestHeaders");
            this.f18829c.m21462d0(i10, list);
        }

        @Override // okhttp3.internal.http2.C5506c.c
        /* renamed from: k */
        public void mo21505k(int i9, ErrorCode errorCode, ByteString byteString) {
            int i10;
            Object[] array;
            C0042f.m158e(errorCode, "errorCode");
            C0042f.m158e(byteString, "debugData");
            byteString.m21892r();
            C5505b c5505b = this.f18829c;
            synchronized (c5505b) {
                array = c5505b.m21454V().values().toArray(new C5472d[0]);
                c5505b.f18798h = true;
                C6319g c6319g = C6319g.f21314a;
            }
            for (C5472d c5472d : (C5472d[]) array) {
                if (c5472d.m21137j() > i9 && c5472d.m21147t()) {
                    c5472d.m21152y(ErrorCode.REFUSED_STREAM);
                    this.f18829c.m21465g0(c5472d.m21137j());
                }
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r13v1 */
        /* JADX WARN: Type inference failed for: r13v2, types: [T, o6.g] */
        /* JADX WARN: Type inference failed for: r13v3 */
        /* renamed from: l */
        public final void m21506l(boolean z8, C5475g c5475g) {
            ?? r13;
            long jM21179c;
            int i9;
            C5472d[] c5472dArr;
            C0042f.m158e(c5475g, "settings");
            Ref$ObjectRef ref$ObjectRef = new Ref$ObjectRef();
            C5507d c5507dM21456X = this.f18829c.m21456X();
            C5505b c5505b = this.f18829c;
            synchronized (c5507dM21456X) {
                synchronized (c5505b) {
                    C5475g c5475gM21452T = c5505b.m21452T();
                    if (z8) {
                        r13 = c5475g;
                    } else {
                        C5475g c5475g2 = new C5475g();
                        c5475g2.m21183g(c5475gM21452T);
                        c5475g2.m21183g(c5475g);
                        r13 = c5475g2;
                    }
                    ref$ObjectRef.element = r13;
                    jM21179c = r13.m21179c() - c5475gM21452T.m21179c();
                    c5472dArr = (jM21179c == 0 || c5505b.m21454V().isEmpty()) ? null : (C5472d[]) c5505b.m21454V().values().toArray(new C5472d[0]);
                    c5505b.m21468j0((C5475g) ref$ObjectRef.element);
                    c5505b.f18802l.m20666i(new a(c5505b.m21447O() + " onSettings", true, c5505b, ref$ObjectRef), 0L);
                    C6319g c6319g = C6319g.f21314a;
                }
                try {
                    c5505b.m21456X().m21538f((C5475g) ref$ObjectRef.element);
                } catch (IOException e9) {
                    c5505b.m21445M(e9);
                }
                C6319g c6319g2 = C6319g.f21314a;
            }
            if (c5472dArr != null) {
                for (C5472d c5472d : c5472dArr) {
                    synchronized (c5472d) {
                        c5472d.m21128a(jM21179c);
                        C6319g c6319g3 = C6319g.f21314a;
                    }
                }
            }
        }

        /* renamed from: m */
        public void m21507m() throws Throwable {
            ErrorCode errorCode;
            ErrorCode errorCode2 = ErrorCode.INTERNAL_ERROR;
            IOException e9 = null;
            try {
                this.f18828b.m21517v(this);
                while (this.f18828b.m21516u(false, this)) {
                }
                errorCode = ErrorCode.NO_ERROR;
                try {
                    try {
                        this.f18829c.m21444L(errorCode, ErrorCode.CANCEL, null);
                    } catch (IOException e10) {
                        e9 = e10;
                        ErrorCode errorCode3 = ErrorCode.PROTOCOL_ERROR;
                        this.f18829c.m21444L(errorCode3, errorCode3, e9);
                        C5057d.m19799m(this.f18828b);
                    }
                } catch (Throwable th) {
                    th = th;
                    this.f18829c.m21444L(errorCode, errorCode2, e9);
                    C5057d.m19799m(this.f18828b);
                    throw th;
                }
            } catch (IOException e11) {
                e9 = e11;
                errorCode = errorCode2;
            } catch (Throwable th2) {
                th = th2;
                errorCode = errorCode2;
                this.f18829c.m21444L(errorCode, errorCode2, e9);
                C5057d.m19799m(this.f18828b);
                throw th;
            }
            C5057d.m19799m(this.f18828b);
        }
    }

    /* renamed from: okhttp3.internal.http2.b$e */
    public static final class e extends AbstractC5294a {

        /* renamed from: e */
        public final /* synthetic */ C5505b f18840e;

        /* renamed from: f */
        public final /* synthetic */ int f18841f;

        /* renamed from: g */
        public final /* synthetic */ C6322c f18842g;

        /* renamed from: h */
        public final /* synthetic */ int f18843h;

        /* renamed from: i */
        public final /* synthetic */ boolean f18844i;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public e(String str, boolean z8, C5505b c5505b, int i9, C6322c c6322c, int i10, boolean z9) {
            super(str, z8);
            this.f18840e = c5505b;
            this.f18841f = i9;
            this.f18842g = c6322c;
            this.f18843h = i10;
            this.f18844i = z9;
        }

        @Override // p129l6.AbstractC5294a
        /* renamed from: f */
        public long mo20652f() {
            try {
                boolean zMo21175c = this.f18840e.f18803m.mo21175c(this.f18841f, this.f18842g, this.f18843h, this.f18844i);
                if (zMo21175c) {
                    this.f18840e.m21456X().m21534D(this.f18841f, ErrorCode.CANCEL);
                }
                if (!zMo21175c && !this.f18844i) {
                    return -1L;
                }
                synchronized (this.f18840e) {
                    this.f18840e.f18791C.remove(Integer.valueOf(this.f18841f));
                }
                return -1L;
            } catch (IOException unused) {
                return -1L;
            }
        }
    }

    /* renamed from: okhttp3.internal.http2.b$f */
    public static final class f extends AbstractC5294a {

        /* renamed from: e */
        public final /* synthetic */ C5505b f18845e;

        /* renamed from: f */
        public final /* synthetic */ int f18846f;

        /* renamed from: g */
        public final /* synthetic */ List f18847g;

        /* renamed from: h */
        public final /* synthetic */ boolean f18848h;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public f(String str, boolean z8, C5505b c5505b, int i9, List list, boolean z9) {
            super(str, z8);
            this.f18845e = c5505b;
            this.f18846f = i9;
            this.f18847g = list;
            this.f18848h = z9;
        }

        @Override // p129l6.AbstractC5294a
        /* renamed from: f */
        public long mo20652f() {
            boolean zMo21174b = this.f18845e.f18803m.mo21174b(this.f18846f, this.f18847g, this.f18848h);
            if (zMo21174b) {
                try {
                    this.f18845e.m21456X().m21534D(this.f18846f, ErrorCode.CANCEL);
                } catch (IOException unused) {
                    return -1L;
                }
            }
            if (!zMo21174b && !this.f18848h) {
                return -1L;
            }
            synchronized (this.f18845e) {
                this.f18845e.f18791C.remove(Integer.valueOf(this.f18846f));
            }
            return -1L;
        }
    }

    /* renamed from: okhttp3.internal.http2.b$g */
    public static final class g extends AbstractC5294a {

        /* renamed from: e */
        public final /* synthetic */ C5505b f18849e;

        /* renamed from: f */
        public final /* synthetic */ int f18850f;

        /* renamed from: g */
        public final /* synthetic */ List f18851g;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public g(String str, boolean z8, C5505b c5505b, int i9, List list) {
            super(str, z8);
            this.f18849e = c5505b;
            this.f18850f = i9;
            this.f18851g = list;
        }

        @Override // p129l6.AbstractC5294a
        /* renamed from: f */
        public long mo20652f() {
            if (!this.f18849e.f18803m.mo21173a(this.f18850f, this.f18851g)) {
                return -1L;
            }
            try {
                this.f18849e.m21456X().m21534D(this.f18850f, ErrorCode.CANCEL);
                synchronized (this.f18849e) {
                    this.f18849e.f18791C.remove(Integer.valueOf(this.f18850f));
                }
                return -1L;
            } catch (IOException unused) {
                return -1L;
            }
        }
    }

    /* renamed from: okhttp3.internal.http2.b$h */
    public static final class h extends AbstractC5294a {

        /* renamed from: e */
        public final /* synthetic */ C5505b f18852e;

        /* renamed from: f */
        public final /* synthetic */ int f18853f;

        /* renamed from: g */
        public final /* synthetic */ ErrorCode f18854g;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public h(String str, boolean z8, C5505b c5505b, int i9, ErrorCode errorCode) {
            super(str, z8);
            this.f18852e = c5505b;
            this.f18853f = i9;
            this.f18854g = errorCode;
        }

        @Override // p129l6.AbstractC5294a
        /* renamed from: f */
        public long mo20652f() {
            this.f18852e.f18803m.mo21176d(this.f18853f, this.f18854g);
            synchronized (this.f18852e) {
                this.f18852e.f18791C.remove(Integer.valueOf(this.f18853f));
                C6319g c6319g = C6319g.f21314a;
            }
            return -1L;
        }
    }

    /* renamed from: okhttp3.internal.http2.b$i */
    public static final class i extends AbstractC5294a {

        /* renamed from: e */
        public final /* synthetic */ C5505b f18855e;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public i(String str, boolean z8, C5505b c5505b) {
            super(str, z8);
            this.f18855e = c5505b;
        }

        @Override // p129l6.AbstractC5294a
        /* renamed from: f */
        public long mo20652f() throws IOException {
            this.f18855e.m21474q0(false, 2, 0);
            return -1L;
        }
    }

    /* renamed from: okhttp3.internal.http2.b$j */
    public static final class j extends AbstractC5294a {

        /* renamed from: e */
        public final /* synthetic */ C5505b f18856e;

        /* renamed from: f */
        public final /* synthetic */ long f18857f;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public j(String str, C5505b c5505b, long j9) {
            super(str, false, 2, null);
            this.f18856e = c5505b;
            this.f18857f = j9;
        }

        @Override // p129l6.AbstractC5294a
        /* renamed from: f */
        public long mo20652f() throws IOException {
            boolean z8;
            synchronized (this.f18856e) {
                if (this.f18856e.f18805o < this.f18856e.f18804n) {
                    z8 = true;
                } else {
                    this.f18856e.f18804n++;
                    z8 = false;
                }
            }
            if (z8) {
                this.f18856e.m21445M(null);
                return -1L;
            }
            this.f18856e.m21474q0(false, 1, 0);
            return this.f18857f;
        }
    }

    /* renamed from: okhttp3.internal.http2.b$k */
    public static final class k extends AbstractC5294a {

        /* renamed from: e */
        public final /* synthetic */ C5505b f18858e;

        /* renamed from: f */
        public final /* synthetic */ int f18859f;

        /* renamed from: g */
        public final /* synthetic */ ErrorCode f18860g;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public k(String str, boolean z8, C5505b c5505b, int i9, ErrorCode errorCode) {
            super(str, z8);
            this.f18858e = c5505b;
            this.f18859f = i9;
            this.f18860g = errorCode;
        }

        @Override // p129l6.AbstractC5294a
        /* renamed from: f */
        public long mo20652f() throws IOException {
            try {
                this.f18858e.m21475r0(this.f18859f, this.f18860g);
                return -1L;
            } catch (IOException e9) {
                this.f18858e.m21445M(e9);
                return -1L;
            }
        }
    }

    /* renamed from: okhttp3.internal.http2.b$l */
    public static final class l extends AbstractC5294a {

        /* renamed from: e */
        public final /* synthetic */ C5505b f18861e;

        /* renamed from: f */
        public final /* synthetic */ int f18862f;

        /* renamed from: g */
        public final /* synthetic */ long f18863g;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public l(String str, boolean z8, C5505b c5505b, int i9, long j9) {
            super(str, z8);
            this.f18861e = c5505b;
            this.f18862f = i9;
            this.f18863g = j9;
        }

        @Override // p129l6.AbstractC5294a
        /* renamed from: f */
        public long mo20652f() throws IOException {
            try {
                this.f18861e.m21456X().m21536F(this.f18862f, this.f18863g);
                return -1L;
            } catch (IOException e9) {
                this.f18861e.m21445M(e9);
                return -1L;
            }
        }
    }

    static {
        C5475g c5475g = new C5475g();
        c5475g.m21184h(7, 65535);
        c5475g.m21184h(5, 16384);
        f18788E = c5475g;
    }

    public C5505b(a aVar) {
        C0042f.m158e(aVar, "builder");
        boolean zM21479b = aVar.m21479b();
        this.f18792b = zM21479b;
        this.f18793c = aVar.m21481d();
        this.f18794d = new LinkedHashMap();
        String strM21480c = aVar.m21480c();
        this.f18795e = strM21480c;
        this.f18797g = aVar.m21479b() ? 3 : 2;
        C5298e c5298eM21487j = aVar.m21487j();
        this.f18799i = c5298eM21487j;
        C5297d c5297dM20679i = c5298eM21487j.m20679i();
        this.f18800j = c5297dM20679i;
        this.f18801k = c5298eM21487j.m20679i();
        this.f18802l = c5298eM21487j.m20679i();
        this.f18803m = aVar.m21483f();
        C5475g c5475g = new C5475g();
        if (aVar.m21479b()) {
            c5475g.m21184h(7, C3322C.DEFAULT_MUXED_BUFFER_SIZE);
        }
        this.f18810t = c5475g;
        this.f18811u = f18788E;
        this.f18815y = r2.m21179c();
        this.f18816z = aVar.m21485h();
        this.f18789A = new C5507d(aVar.m21484g(), zM21479b);
        this.f18790B = new d(this, new C5506c(aVar.m21486i(), zM21479b));
        this.f18791C = new LinkedHashSet();
        if (aVar.m21482e() != 0) {
            long nanos = TimeUnit.MILLISECONDS.toNanos(aVar.m21482e());
            c5297dM20679i.m20666i(new j(strM21480c + " ping", this, nanos), nanos);
        }
    }

    /* renamed from: m0 */
    public static /* synthetic */ void m21437m0(C5505b c5505b, boolean z8, C5298e c5298e, int i9, Object obj) {
        if ((i9 & 1) != 0) {
            z8 = true;
        }
        if ((i9 & 2) != 0) {
            c5298e = C5298e.f17983i;
        }
        c5505b.m21470l0(z8, c5298e);
    }

    /* renamed from: L */
    public final void m21444L(ErrorCode errorCode, ErrorCode errorCode2, IOException iOException) throws IOException {
        int i9;
        Object[] array;
        C0042f.m158e(errorCode, "connectionCode");
        C0042f.m158e(errorCode2, "streamCode");
        if (C5057d.f17450h && Thread.holdsLock(this)) {
            throw new AssertionError("Thread " + Thread.currentThread().getName() + " MUST NOT hold lock on " + this);
        }
        try {
            m21469k0(errorCode);
        } catch (IOException unused) {
        }
        synchronized (this) {
            if (!this.f18794d.isEmpty()) {
                array = this.f18794d.values().toArray(new C5472d[0]);
                this.f18794d.clear();
            } else {
                array = null;
            }
            C6319g c6319g = C6319g.f21314a;
        }
        C5472d[] c5472dArr = (C5472d[]) array;
        if (c5472dArr != null) {
            for (C5472d c5472d : c5472dArr) {
                try {
                    c5472d.m21131d(errorCode2, iOException);
                } catch (IOException unused2) {
                }
            }
        }
        try {
            this.f18789A.close();
        } catch (IOException unused3) {
        }
        try {
            this.f18816z.close();
        } catch (IOException unused4) {
        }
        this.f18800j.m20670n();
        this.f18801k.m20670n();
        this.f18802l.m20670n();
    }

    /* renamed from: M */
    public final void m21445M(IOException iOException) throws IOException {
        ErrorCode errorCode = ErrorCode.PROTOCOL_ERROR;
        m21444L(errorCode, errorCode, iOException);
    }

    /* renamed from: N */
    public final boolean m21446N() {
        return this.f18792b;
    }

    /* renamed from: O */
    public final String m21447O() {
        return this.f18795e;
    }

    /* renamed from: P */
    public final int m21448P() {
        return this.f18796f;
    }

    /* renamed from: Q */
    public final c m21449Q() {
        return this.f18793c;
    }

    /* renamed from: R */
    public final int m21450R() {
        return this.f18797g;
    }

    /* renamed from: S */
    public final C5475g m21451S() {
        return this.f18810t;
    }

    /* renamed from: T */
    public final C5475g m21452T() {
        return this.f18811u;
    }

    /* renamed from: U */
    public final synchronized C5472d m21453U(int i9) {
        return this.f18794d.get(Integer.valueOf(i9));
    }

    /* renamed from: V */
    public final Map<Integer, C5472d> m21454V() {
        return this.f18794d;
    }

    /* renamed from: W */
    public final long m21455W() {
        return this.f18815y;
    }

    /* renamed from: X */
    public final C5507d m21456X() {
        return this.f18789A;
    }

    /* renamed from: Y */
    public final synchronized boolean m21457Y(long j9) {
        if (this.f18798h) {
            return false;
        }
        if (this.f18807q < this.f18806p) {
            if (j9 >= this.f18809s) {
                return false;
            }
        }
        return true;
    }

    /* renamed from: Z */
    public final C5472d m21458Z(int i9, List<C5469a> list, boolean z8) {
        int i10;
        C5472d c5472d;
        boolean z9;
        boolean z10 = !z8;
        synchronized (this.f18789A) {
            synchronized (this) {
                if (this.f18797g > 1073741823) {
                    m21469k0(ErrorCode.REFUSED_STREAM);
                }
                if (this.f18798h) {
                    throw new ConnectionShutdownException();
                }
                i10 = this.f18797g;
                this.f18797g = i10 + 2;
                c5472d = new C5472d(i10, this, z10, false, null);
                z9 = !z8 || this.f18814x >= this.f18815y || c5472d.m21145r() >= c5472d.m21144q();
                if (c5472d.m21148u()) {
                    this.f18794d.put(Integer.valueOf(i10), c5472d);
                }
                C6319g c6319g = C6319g.f21314a;
            }
            if (i9 == 0) {
                this.f18789A.m21544z(z10, i10, list);
            } else {
                if (!(true ^ this.f18792b)) {
                    throw new IllegalArgumentException("client streams shouldn't have associated stream IDs".toString());
                }
                this.f18789A.m21533C(i9, i10, list);
            }
        }
        if (z9) {
            this.f18789A.flush();
        }
        return c5472d;
    }

    /* renamed from: a0 */
    public final C5472d m21459a0(List<C5469a> list, boolean z8) {
        C0042f.m158e(list, "requestHeaders");
        return m21458Z(0, list, z8);
    }

    /* renamed from: b0 */
    public final void m21460b0(int i9, InterfaceC6324e interfaceC6324e, int i10, boolean z8) {
        C0042f.m158e(interfaceC6324e, "source");
        C6322c c6322c = new C6322c();
        long j9 = i10;
        interfaceC6324e.mo24228r(j9);
        interfaceC6324e.mo21077d(c6322c, j9);
        this.f18801k.m20666i(new e(this.f18795e + '[' + i9 + "] onData", true, this, i9, c6322c, i10, z8), 0L);
    }

    /* renamed from: c0 */
    public final void m21461c0(int i9, List<C5469a> list, boolean z8) {
        C0042f.m158e(list, "requestHeaders");
        this.f18801k.m20666i(new f(this.f18795e + '[' + i9 + "] onHeaders", true, this, i9, list, z8), 0L);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        m21444L(ErrorCode.NO_ERROR, ErrorCode.CANCEL, null);
    }

    /* renamed from: d0 */
    public final void m21462d0(int i9, List<C5469a> list) {
        C0042f.m158e(list, "requestHeaders");
        synchronized (this) {
            if (this.f18791C.contains(Integer.valueOf(i9))) {
                m21476s0(i9, ErrorCode.PROTOCOL_ERROR);
                return;
            }
            this.f18791C.add(Integer.valueOf(i9));
            this.f18801k.m20666i(new g(this.f18795e + '[' + i9 + "] onRequest", true, this, i9, list), 0L);
        }
    }

    /* renamed from: e0 */
    public final void m21463e0(int i9, ErrorCode errorCode) {
        C0042f.m158e(errorCode, "errorCode");
        this.f18801k.m20666i(new h(this.f18795e + '[' + i9 + "] onReset", true, this, i9, errorCode), 0L);
    }

    /* renamed from: f0 */
    public final boolean m21464f0(int i9) {
        return i9 != 0 && (i9 & 1) == 0;
    }

    public final void flush() {
        this.f18789A.flush();
    }

    /* renamed from: g0 */
    public final synchronized C5472d m21465g0(int i9) {
        C5472d c5472dRemove;
        c5472dRemove = this.f18794d.remove(Integer.valueOf(i9));
        C0042f.m156c(this, "null cannot be cast to non-null type java.lang.Object");
        notifyAll();
        return c5472dRemove;
    }

    /* renamed from: h0 */
    public final void m21466h0() {
        synchronized (this) {
            long j9 = this.f18807q;
            long j10 = this.f18806p;
            if (j9 < j10) {
                return;
            }
            this.f18806p = j10 + 1;
            this.f18809s = System.nanoTime() + 1000000000;
            C6319g c6319g = C6319g.f21314a;
            this.f18800j.m20666i(new i(this.f18795e + " ping", true, this), 0L);
        }
    }

    /* renamed from: i0 */
    public final void m21467i0(int i9) {
        this.f18796f = i9;
    }

    /* renamed from: j0 */
    public final void m21468j0(C5475g c5475g) {
        C0042f.m158e(c5475g, "<set-?>");
        this.f18811u = c5475g;
    }

    /* renamed from: k0 */
    public final void m21469k0(ErrorCode errorCode) {
        C0042f.m158e(errorCode, "statusCode");
        synchronized (this.f18789A) {
            Ref$IntRef ref$IntRef = new Ref$IntRef();
            synchronized (this) {
                if (this.f18798h) {
                    return;
                }
                this.f18798h = true;
                int i9 = this.f18796f;
                ref$IntRef.element = i9;
                C6319g c6319g = C6319g.f21314a;
                this.f18789A.m21543y(i9, errorCode, C5057d.f17443a);
            }
        }
    }

    /* renamed from: l0 */
    public final void m21470l0(boolean z8, C5298e c5298e) {
        C0042f.m158e(c5298e, "taskRunner");
        if (z8) {
            this.f18789A.m21539u();
            this.f18789A.m21535E(this.f18810t);
            if (this.f18810t.m21179c() != 65535) {
                this.f18789A.m21536F(0, r5 - 65535);
            }
        }
        c5298e.m20679i().m20666i(new C5296c(this.f18795e, true, this.f18790B), 0L);
    }

    /* renamed from: n0 */
    public final synchronized void m21471n0(long j9) {
        long j10 = this.f18812v + j9;
        this.f18812v = j10;
        long j11 = j10 - this.f18813w;
        if (j11 >= this.f18810t.m21179c() / 2) {
            m21477t0(0, j11);
            this.f18813w += j11;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x0038, code lost:
    
        r2 = java.lang.Math.min((int) java.lang.Math.min(r12, r6 - r4), r8.f18789A.m21531A());
        r6 = r2;
        r8.f18814x += r6;
        r4 = p203t5.C6319g.f21314a;
     */
    /* renamed from: o0 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void m21472o0(int i9, boolean z8, C6322c c6322c, long j9) {
        int iMin;
        long j10;
        if (j9 == 0) {
            this.f18789A.m21540v(z8, i9, c6322c, 0);
            return;
        }
        while (j9 > 0) {
            synchronized (this) {
                while (true) {
                    try {
                        long j11 = this.f18814x;
                        long j12 = this.f18815y;
                        if (j11 < j12) {
                            break;
                        }
                        if (!this.f18794d.containsKey(Integer.valueOf(i9))) {
                            throw new IOException("stream closed");
                        }
                        C0042f.m156c(this, "null cannot be cast to non-null type java.lang.Object");
                        wait();
                    } catch (InterruptedException unused) {
                        Thread.currentThread().interrupt();
                        throw new InterruptedIOException();
                    }
                }
            }
            j9 -= j10;
            this.f18789A.m21540v(z8 && j9 == 0, i9, c6322c, iMin);
        }
    }

    /* renamed from: p0 */
    public final void m21473p0(int i9, boolean z8, List<C5469a> list) {
        C0042f.m158e(list, "alternating");
        this.f18789A.m21544z(z8, i9, list);
    }

    /* renamed from: q0 */
    public final void m21474q0(boolean z8, int i9, int i10) throws IOException {
        try {
            this.f18789A.m21532B(z8, i9, i10);
        } catch (IOException e9) {
            m21445M(e9);
        }
    }

    /* renamed from: r0 */
    public final void m21475r0(int i9, ErrorCode errorCode) {
        C0042f.m158e(errorCode, "statusCode");
        this.f18789A.m21534D(i9, errorCode);
    }

    /* renamed from: s0 */
    public final void m21476s0(int i9, ErrorCode errorCode) {
        C0042f.m158e(errorCode, "errorCode");
        this.f18800j.m20666i(new k(this.f18795e + '[' + i9 + "] writeSynReset", true, this, i9, errorCode), 0L);
    }

    /* renamed from: t0 */
    public final void m21477t0(int i9, long j9) {
        this.f18800j.m20666i(new l(this.f18795e + '[' + i9 + "] windowUpdate", true, this, i9, j9), 0L);
    }
}
