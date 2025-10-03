package com.cyberlink.you.chat.p034e;

import com.cyberlink.clsm.C1199a;
import com.cyberlink.clsm.C1206h;
import com.cyberlink.clsm.Decrypter;
import com.cyberlink.clsm.Encrypter;
import com.cyberlink.clsm.IKeyStore;
import com.cyberlink.clsm.ISessionCacheStore;
import com.cyberlink.clsm.SmClient;
import com.cyberlink.clsm.SmLog;
import com.cyberlink.you.Globals;
import com.cyberlink.you.friends.FriendsClient;
import com.cyberlink.you.utility.CLUtility;
import com.cyberlink.you.utility.ULogUtility;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import p054e2.C4711b;
import p054e2.C4713d;
import p116k4.AbstractC5146g0;
import p116k4.C5154j;
import p209u2.AbstractC6381r;
import p209u2.C6370g;
import p209u2.C6383t;
import p209u2.C6385v;

/* renamed from: com.cyberlink.you.chat.e.b */
/* loaded from: classes.dex */
public final class C2889b {

    /* renamed from: f */
    public static final C2889b f12605f = new C2889b();

    /* renamed from: a */
    public final XmppRemoteClient f12606a = new XmppRemoteClient();

    /* renamed from: b */
    public final IKeyStore f12607b = new C4711b(Globals.m7372O());

    /* renamed from: c */
    public final ISessionCacheStore f12608c = new C4713d(Globals.m7372O());

    /* renamed from: d */
    public final Object f12609d = new Object();

    /* renamed from: e */
    public Future<SmClient> f12610e = null;

    /* renamed from: com.cyberlink.you.chat.e.b$a */
    public class a extends AbstractC6381r<Boolean, Void> {

        /* renamed from: c */
        public final /* synthetic */ long f12611c;

        public a(long j9) {
            this.f12611c = j9;
        }

        @Override // p209u2.AbstractC6381r
        /* renamed from: k, reason: merged with bridge method [inline-methods] */
        public void m24503g(Boolean bool) {
        }
    }

    /* renamed from: com.cyberlink.you.chat.e.b$b */
    public class b extends AbstractC6381r<Boolean, Void> {

        /* renamed from: c */
        public final /* synthetic */ long f12613c;

        public b(long j9) {
            this.f12613c = j9;
        }

        @Override // p209u2.AbstractC6381r
        /* renamed from: k, reason: merged with bridge method [inline-methods] */
        public void m24503g(Boolean bool) {
            if (bool.booleanValue()) {
                return;
            }
            ULogUtility.m16684t("CLSM", "updateSession but failed");
        }
    }

    public C2889b() {
        m14308k();
        SmLog.m5269b(new C2890c());
    }

    /* renamed from: h */
    public static C2889b m14298h() {
        return f12605f;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: l */
    public /* synthetic */ SmClient m14299l() {
        return new SmClient(this.f12606a, this.f12607b, this.f12608c);
    }

    /* renamed from: b */
    public final Decrypter m14300b(OutputStream outputStream, C1199a c1199a) {
        try {
            return m14306i().m5255g(outputStream, c1199a);
        } catch (Exception e9) {
            throw new IllegalStateException("Cannot create decrypter", e9);
        }
    }

    /* renamed from: c */
    public final Encrypter m14301c(OutputStream outputStream) {
        try {
            return m14306i().m5256h(outputStream);
        } catch (Exception e9) {
            throw new IllegalStateException("Cannot create encrypter", e9);
        }
    }

    /* renamed from: d */
    public String m14302d(long j9, String str, long j10) {
        System.currentTimeMillis();
        try {
            return m14306i().m5257i(String.valueOf(j9), str, j10);
        } catch (Exception e9) {
            C5154j.m20076j(e9);
            return null;
        }
    }

    /* renamed from: e */
    public void m14303e(InputStream inputStream, OutputStream outputStream, C1199a c1199a) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        try {
            Decrypter decrypterM14300b = m14300b(outputStream, c1199a);
            try {
                if (decrypterM14300b.mo5230w(inputStream)) {
                    decrypterM14300b.close();
                    return;
                }
                throw new IllegalStateException("Cannot decrypt stream: " + decrypterM14300b.mo5228u());
            } finally {
            }
        } finally {
            C6370g.m24480b(inputStream);
            C6370g.m24480b(outputStream);
        }
    }

    /* renamed from: f */
    public C1199a m14304f(InputStream inputStream, OutputStream outputStream) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        try {
            Encrypter encrypterM14301c = m14301c(outputStream);
            try {
                if (!encrypterM14301c.mo5230w(inputStream)) {
                    throw new IllegalStateException("Cannot encrypt stream");
                }
                C1199a c1199aM5234x = encrypterM14301c.m5234x();
                encrypterM14301c.close();
                return c1199aM5234x;
            } finally {
            }
        } finally {
            C6370g.m24480b(inputStream);
            C6370g.m24480b(outputStream);
        }
    }

    /* renamed from: g */
    public String m14305g(String str, String str2) {
        System.currentTimeMillis();
        try {
            return m14306i().m5258j(str, str2);
        } catch (Exception e9) {
            C5154j.m20076j(e9);
            return null;
        }
    }

    /* renamed from: i */
    public final SmClient m14306i() {
        synchronized (this.f12609d) {
            Future<SmClient> future = this.f12610e;
            if (future != null) {
                return future.get();
            }
            return m14308k().get();
        }
    }

    /* renamed from: j */
    public String m14307j(boolean z8) {
        try {
            return m14306i().m5261m(z8);
        } catch (Exception e9) {
            C5154j.m20076j(e9);
            return null;
        }
    }

    /* renamed from: k */
    public final Future<SmClient> m14308k() {
        Future<SmClient> futureSubmit;
        synchronized (this.f12609d) {
            m14311o();
            Future<SmClient> future = this.f12610e;
            if (future != null && !future.isDone()) {
                this.f12610e.cancel(true);
            }
            futureSubmit = C6385v.f21553a.submit(new Callable() { // from class: com.cyberlink.you.chat.e.a
                @Override // java.util.concurrent.Callable
                public final Object call() {
                    return this.f12604b.m14299l();
                }
            });
            this.f12610e = futureSubmit;
        }
        return futureSubmit;
    }

    /* renamed from: m */
    public void m14309m(String str, long j9, String str2, String str3) {
        this.f12606a.m14296o(str, j9, str2, str3);
    }

    /* renamed from: n */
    public void m14310n(Collection<Long> collection, AbstractC6381r<Map<Long, List<String>>, Void> abstractC6381r) {
        System.currentTimeMillis();
        try {
            m14306i().m5262n(collection, true, abstractC6381r);
        } catch (Exception e9) {
            C5154j.m20076j(e9);
            abstractC6381r.m24507e();
        }
    }

    /* renamed from: o */
    public final void m14311o() {
        boolean z8 = Globals.m7388i0().m7569k2() && CLUtility.m16445H1(Globals.m7388i0().getApplicationContext());
        String strM20043a = AbstractC5146g0.m20043a(Globals.m7372O());
        if (z8) {
            strM20043a = "A-1-" + C6383t.m24519h(AbstractC5146g0.m20043a(Globals.m7388i0()).getBytes());
        }
        C1206h.m5278a().m5288k(Globals.m7388i0().m7449L()).m5287j(Globals.m7388i0().m7568k1().longValue()).m5285h(strM20043a).m5289l(FriendsClient.m15642G("clsm", "getPublicKey")).m5290m(FriendsClient.m15642G("clsm", "setPublicKey")).m5286i(z8);
    }

    /* renamed from: p */
    public void m14312p(long j9, long j10) {
        try {
            m14306i().m5265v(String.valueOf(j9), j10, new a(System.currentTimeMillis()));
        } catch (Exception e9) {
            C5154j.m20076j(e9);
        }
    }

    /* renamed from: q */
    public void m14313q() {
        Future<SmClient> future;
        synchronized (this.f12609d) {
            try {
                try {
                    future = this.f12610e;
                } catch (Exception e9) {
                    C5154j.m20076j(e9);
                }
                if (future != null) {
                    future.get().m5264u();
                    this.f12607b.clear();
                    this.f12608c.clear();
                    C1206h.m5278a().m5288k(null);
                } else {
                    this.f12607b.clear();
                    this.f12608c.clear();
                    C1206h.m5278a().m5288k(null);
                }
            } finally {
                this.f12610e = null;
            }
        }
    }

    /* renamed from: r */
    public boolean m14314r() {
        try {
            m14311o();
            return m14306i().m5266w();
        } catch (Exception e9) {
            C5154j.m20076j(e9);
            return false;
        }
    }

    /* renamed from: s */
    public void m14315s(long j9, long j10, String str, String str2) {
        try {
            m14306i().m5267x(String.valueOf(j9), j10, str, str2, new b(System.currentTimeMillis()));
        } catch (Exception e9) {
            C5154j.m20076j(e9);
        }
    }
}
