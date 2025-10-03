package p182r2;

import com.cyberlink.you.chat.C2904l;
import com.cyberlink.you.chat.XMPPManager;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import p182r2.AbstractC6189a;
import p209u2.ThreadFactoryC6373j;

/* renamed from: r2.d */
/* loaded from: classes.dex */
public abstract class AbstractC6195d<M extends AbstractC6189a> {

    /* renamed from: a */
    public final Object f20862a = new Object();

    /* renamed from: b */
    public final ConcurrentHashMap<String, b> f20863b = new ConcurrentHashMap<>();

    /* renamed from: c */
    public final ThreadPoolExecutor f20864c = new ThreadPoolExecutor(0, 2, 60, TimeUnit.SECONDS, new LinkedBlockingQueue(), new ThreadFactoryC6373j(mo23688d() + ".P"));

    /* renamed from: d */
    public final XMPPManager.InterfaceC2849a0 f20865d;

    /* renamed from: e */
    public final XMPPManager.InterfaceC2867r f20866e;

    /* renamed from: r2.d$a */
    public class a implements XMPPManager.InterfaceC2849a0 {
        public a() {
        }

        @Override // com.cyberlink.you.chat.XMPPManager.InterfaceC2849a0
        /* renamed from: F */
        public void mo5716F(String str) {
            AbstractC6195d.this.m23689f(str);
        }

        @Override // com.cyberlink.you.chat.XMPPManager.InterfaceC2849a0
        /* renamed from: p */
        public void mo5718p(String str, Date date) {
            AbstractC6195d.this.m23689f(str);
        }
    }

    public AbstractC6195d() {
        a aVar = new a();
        this.f20865d = aVar;
        XMPPManager.InterfaceC2867r interfaceC2867r = new XMPPManager.InterfaceC2867r() { // from class: r2.c
            @Override // com.cyberlink.you.chat.XMPPManager.InterfaceC2867r
            /* renamed from: a */
            public final void mo14289a(C2904l c2904l) {
                this.f20860b.m23686e(c2904l);
            }
        };
        this.f20866e = interfaceC2867r;
        XMPPManager.m14184g0().m14206G(aVar);
        XMPPManager.m14184g0().m14210J(interfaceC2867r);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: e */
    public /* synthetic */ void m23686e(C2904l c2904l) {
        m23689f(c2904l.m14446v());
    }

    /* renamed from: c */
    public final b m23687c(String str) {
        b bVar = this.f20863b.get(str);
        if (bVar == null) {
            synchronized (this.f20862a) {
                bVar = this.f20863b.get(str);
                if (bVar == null) {
                    bVar = new b(null);
                    this.f20863b.put(str, bVar);
                }
            }
        }
        return bVar;
    }

    /* renamed from: d */
    public abstract String mo23688d();

    /* renamed from: f */
    public final void m23689f(String str) {
        b bVarRemove = this.f20863b.remove(str);
        if (bVarRemove == null) {
            return;
        }
        synchronized (bVarRemove) {
            bVarRemove.f20868a.set(false);
            bVarRemove.notify();
            C6218z.m23767f(mo23688d(), "> ack: %s", str);
        }
    }

    /* renamed from: g */
    public void mo23669g(M m8) {
        this.f20863b.remove(m8.f20852a);
    }

    /* renamed from: h */
    public void mo23670h(M m8) {
        m23687c(m8.f20852a);
    }

    /* renamed from: i */
    public boolean mo23671i(M m8) {
        return m23690j(m8.f20852a);
    }

    /* renamed from: j */
    public final boolean m23690j(String str) {
        boolean z8;
        b bVarM23687c = m23687c(str);
        synchronized (bVarM23687c) {
            z8 = true;
            C6218z.m23767f(mo23688d(), "> wait for ack: %s", str);
            while (bVarM23687c.f20868a.get()) {
                try {
                    bVarM23687c.wait(10000L);
                } catch (InterruptedException unused) {
                }
                if (bVarM23687c.f20869b.decrementAndGet() == 0) {
                    break;
                }
            }
            if (bVarM23687c.f20868a.get()) {
                z8 = false;
            }
        }
        return z8;
    }

    /* renamed from: r2.d$b */
    public static class b {

        /* renamed from: a */
        public final AtomicBoolean f20868a;

        /* renamed from: b */
        public final AtomicInteger f20869b;

        public b() {
            this.f20868a = new AtomicBoolean(true);
            this.f20869b = new AtomicInteger(3);
        }

        public /* synthetic */ b(a aVar) {
            this();
        }
    }
}
