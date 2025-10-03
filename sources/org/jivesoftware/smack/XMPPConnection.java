package org.jivesoftware.smack;

import de.measite.smack.AndroidDebugger;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.compression.XMPPInputOutputStream;
import org.jivesoftware.smack.packet.AbstractC5586IQ;
import org.jivesoftware.smack.packet.AbstractC5594b;
import org.jivesoftware.smack.packet.Bind;
import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smack.packet.Session;
import p222v6.C6492e;
import p222v6.InterfaceC6489b;
import p222v6.InterfaceC6490c;
import p231w6.C6546a;
import p231w6.InterfaceC6547b;
import p240x6.C6578c;
import p240x6.InterfaceC6582g;

/* loaded from: classes.dex */
public abstract class XMPPConnection {

    /* renamed from: h */
    public Reader f19165h;

    /* renamed from: i */
    public Writer f19166i;

    /* renamed from: k */
    public final int f19168k;

    /* renamed from: l */
    public final ConnectionConfiguration f19169l;

    /* renamed from: m */
    public String f19170m;

    /* renamed from: n */
    public FromMode f19171n;

    /* renamed from: o */
    public boolean f19172o;

    /* renamed from: p */
    public XMPPInputOutputStream f19173p;

    /* renamed from: q */
    public final ScheduledExecutorService f19174q;

    /* renamed from: r */
    public String f19175r;

    /* renamed from: s */
    public int f19176s;

    /* renamed from: t */
    public AtomicBoolean f19177t;

    /* renamed from: u */
    public boolean f19178u;

    /* renamed from: v */
    public IOException f19179v;

    /* renamed from: w */
    public boolean f19180w;

    /* renamed from: x */
    public boolean f19181x;

    /* renamed from: y */
    public static final Logger f19156y = Logger.getLogger(XMPPConnection.class.getName());

    /* renamed from: z */
    public static final AtomicInteger f19157z = new AtomicInteger(0);

    /* renamed from: A */
    public static final Set<InterfaceC6489b> f19155A = new CopyOnWriteArraySet();

    /* renamed from: a */
    public final Collection<InterfaceC6490c> f19158a = new CopyOnWriteArrayList();

    /* renamed from: b */
    public final Collection<C5582b> f19159b = new ConcurrentLinkedQueue();

    /* renamed from: c */
    public final Map<InterfaceC5583c, C5579d> f19160c = new ConcurrentHashMap();

    /* renamed from: d */
    public final Map<InterfaceC5583c, C5579d> f19161d = new ConcurrentHashMap();

    /* renamed from: e */
    public final Map<Object, C5577b> f19162e = new ConcurrentHashMap();

    /* renamed from: f */
    public long f19163f = C6492e.m24827c();

    /* renamed from: g */
    public InterfaceC6547b f19164g = null;

    /* renamed from: j */
    public C5585d f19167j = new C5585d(this);

    public enum FromMode {
        UNCHANGED,
        OMITTED,
        USER
    }

    /* renamed from: org.jivesoftware.smack.XMPPConnection$a */
    public static /* synthetic */ class C5576a {

        /* renamed from: a */
        public static final /* synthetic */ int[] f19186a;

        static {
            int[] iArr = new int[FromMode.values().length];
            f19186a = iArr;
            try {
                iArr[FromMode.OMITTED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f19186a[FromMode.USER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f19186a[FromMode.UNCHANGED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    /* renamed from: org.jivesoftware.smack.XMPPConnection$b */
    public static class C5577b {

        /* renamed from: a */
        public InterfaceC6582g f19187a;

        /* renamed from: a */
        public void m22015a(AbstractC5594b abstractC5594b) {
            InterfaceC6582g interfaceC6582g = this.f19187a;
            if (interfaceC6582g == null || interfaceC6582g.mo25192a(abstractC5594b)) {
                throw null;
            }
        }

        public boolean equals(Object obj) {
            if (obj == null || !(obj instanceof C5577b)) {
                return false;
            }
            throw null;
        }
    }

    /* renamed from: org.jivesoftware.smack.XMPPConnection$c */
    public class RunnableC5578c implements Runnable {

        /* renamed from: b */
        public AbstractC5594b f19188b;

        public RunnableC5578c(AbstractC5594b abstractC5594b) {
            this.f19188b = abstractC5594b;
        }

        @Override // java.lang.Runnable
        public void run() {
            Iterator<C5579d> it = XMPPConnection.this.f19160c.values().iterator();
            while (it.hasNext()) {
                try {
                    it.next().m22016a(this.f19188b);
                } catch (SmackException.NotConnectedException e9) {
                    XMPPConnection.f19156y.log(Level.WARNING, "Got not connected exception, aborting", (Throwable) e9);
                    return;
                } catch (Exception e10) {
                    XMPPConnection.f19156y.log(Level.SEVERE, "Exception in packet listener", (Throwable) e10);
                }
            }
        }
    }

    /* renamed from: org.jivesoftware.smack.XMPPConnection$d */
    public static class C5579d {

        /* renamed from: a */
        public InterfaceC5583c f19190a;

        /* renamed from: b */
        public InterfaceC6582g f19191b;

        public C5579d(InterfaceC5583c interfaceC5583c, InterfaceC6582g interfaceC6582g) {
            this.f19190a = interfaceC5583c;
            this.f19191b = interfaceC6582g;
        }

        /* renamed from: a */
        public void m22016a(AbstractC5594b abstractC5594b) {
            InterfaceC6582g interfaceC6582g = this.f19191b;
            if (interfaceC6582g == null || interfaceC6582g.mo25192a(abstractC5594b)) {
                this.f19190a.processPacket(abstractC5594b);
            }
        }
    }

    /* renamed from: org.jivesoftware.smack.XMPPConnection$e */
    public static final class ThreadFactoryC5580e implements ThreadFactory {

        /* renamed from: a */
        public final int f19192a;

        /* renamed from: b */
        public int f19193b;

        public /* synthetic */ ThreadFactoryC5580e(int i9, C5576a c5576a) {
            this(i9);
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            StringBuilder sb = new StringBuilder();
            sb.append("Smack Executor Service ");
            int i9 = this.f19193b;
            this.f19193b = i9 + 1;
            sb.append(i9);
            sb.append(" (");
            sb.append(this.f19192a);
            sb.append(")");
            Thread thread = new Thread(runnable, sb.toString());
            thread.setDaemon(true);
            return thread;
        }

        public ThreadFactoryC5580e(int i9) {
            this.f19193b = 0;
            this.f19192a = i9;
        }
    }

    static {
        C6492e.m24830f();
    }

    public XMPPConnection(ConnectionConfiguration connectionConfiguration) {
        int andIncrement = f19157z.getAndIncrement();
        this.f19168k = andIncrement;
        this.f19171n = FromMode.OMITTED;
        this.f19172o = false;
        this.f19174q = new ScheduledThreadPoolExecutor(1, new ThreadFactoryC5580e(andIncrement, null));
        this.f19177t = new AtomicBoolean(false);
        this.f19180w = false;
        this.f19181x = false;
        this.f19169l = connectionConfiguration;
    }

    /* renamed from: b */
    public static void m21962b(InterfaceC6489b interfaceC6489b) {
        f19155A.add(interfaceC6489b);
    }

    /* renamed from: u */
    public static Collection<InterfaceC6489b> m21963u() {
        return Collections.unmodifiableCollection(f19155A);
    }

    /* renamed from: A */
    public Reader mo21964A() {
        return this.f19165h;
    }

    /* renamed from: B */
    public C5585d mo21965B() {
        return this.f19167j;
    }

    /* renamed from: C */
    public String m21966C() {
        return this.f19169l.m21935l();
    }

    /* renamed from: D */
    public abstract String mo21967D();

    /* renamed from: E */
    public Writer mo21968E() {
        return this.f19166i;
    }

    /* renamed from: F */
    public void m21969F() throws ClassNotFoundException {
        String property;
        Class<?> cls;
        if (this.f19165h == null || this.f19166i == null) {
            throw new NullPointerException("Reader or writer isn't initialized.");
        }
        if (this.f19169l.m21941r()) {
            InterfaceC6547b interfaceC6547b = this.f19164g;
            if (interfaceC6547b != null) {
                this.f19165h = interfaceC6547b.newConnectionReader(this.f19165h);
                this.f19166i = this.f19164g.newConnectionWriter(this.f19166i);
                return;
            }
            Class<?> cls2 = null;
            try {
                property = System.getProperty("smack.debuggerClass");
            } catch (Throwable unused) {
                property = null;
            }
            if (property != null) {
                try {
                    cls2 = Class.forName(property);
                } catch (Exception unused2) {
                    f19156y.warning("Unabled to instantiate debugger class " + property);
                }
            }
            if (cls2 == null) {
                try {
                    try {
                        cls = AndroidDebugger.class;
                        boolean z8 = AndroidDebugger.printInterpreted;
                    } catch (Exception unused3) {
                        f19156y.warning("Unabled to instantiate either Smack debugger class");
                    }
                } catch (Exception unused4) {
                    cls = C6546a.class;
                    int i9 = C6546a.f22041h;
                }
                cls2 = cls;
            }
            try {
                InterfaceC6547b interfaceC6547b2 = (InterfaceC6547b) cls2.getConstructor(XMPPConnection.class, Writer.class, Reader.class).newInstance(this, this.f19166i, this.f19165h);
                this.f19164g = interfaceC6547b2;
                this.f19165h = interfaceC6547b2.getReader();
                this.f19166i = this.f19164g.getWriter();
            } catch (Exception e9) {
                throw new IllegalArgumentException("Can't initialize the configured debugger!", e9);
            }
        }
    }

    /* renamed from: G */
    public abstract boolean mo21970G();

    /* renamed from: H */
    public abstract boolean mo21971H();

    /* renamed from: I */
    public abstract void mo21972I(String str, String str2, String str3);

    /* renamed from: J */
    public void m21973J() {
        this.f19169l.m21945v();
    }

    /* renamed from: K */
    public void mo21974K(AbstractC5594b abstractC5594b) {
        if (abstractC5594b == null) {
            return;
        }
        Iterator<C5582b> it = m22012x().iterator();
        while (it.hasNext()) {
            it.next().m22025e(abstractC5594b);
        }
        this.f19174q.submit(new RunnableC5578c(abstractC5594b));
    }

    /* renamed from: L */
    public void m21975L(InterfaceC6490c interfaceC6490c) {
        this.f19158a.remove(interfaceC6490c);
    }

    /* renamed from: M */
    public void m21976M(C5582b c5582b) {
        this.f19159b.remove(c5582b);
    }

    /* renamed from: N */
    public void m21977N(InterfaceC5583c interfaceC5583c) {
        this.f19160c.remove(interfaceC5583c);
    }

    /* renamed from: O */
    public ScheduledFuture<?> m21978O(Runnable runnable, long j9, TimeUnit timeUnit) {
        return this.f19174q.schedule(runnable, j9, timeUnit);
    }

    /* renamed from: P */
    public void m21979P(AbstractC5594b abstractC5594b) {
        if (!mo21971H()) {
            throw new SmackException.NotConnectedException();
        }
        if (abstractC5594b == null) {
            throw new NullPointerException("Packet is null.");
        }
        int i9 = C5576a.f19186a[this.f19171n.ordinal()];
        if (i9 == 1) {
            abstractC5594b.m22165r(null);
        } else if (i9 == 2) {
            abstractC5594b.m22165r(mo21967D());
        }
        m22006q(abstractC5594b);
        mo21980Q(abstractC5594b);
        m22007r(abstractC5594b);
    }

    /* renamed from: Q */
    public abstract void mo21980Q(AbstractC5594b abstractC5594b);

    /* renamed from: R */
    public void mo21981R() {
        synchronized (this.f19177t) {
            this.f19177t.set(true);
            this.f19177t.notify();
        }
    }

    /* renamed from: S */
    public void mo21982S() {
        C5581a.m22019c(this).m22020d(true);
    }

    /* renamed from: T */
    public void mo21983T() {
        this.f19178u = true;
    }

    /* renamed from: U */
    public void m21984U(IOException iOException) {
        this.f19179v = iOException;
    }

    /* renamed from: V */
    public void m21985V(String str, String str2, String str3) {
        this.f19169l.m21948y(str, str2, str3);
    }

    /* renamed from: W */
    public void mo21986W() {
        this.f19172o = true;
    }

    /* renamed from: X */
    public void mo21987X(String str) {
        this.f19170m = str;
    }

    /* renamed from: Y */
    public void mo21988Y(String str) {
        this.f19169l.m21924C(str);
    }

    /* renamed from: Z */
    public void m21989Z(boolean z8) {
        if (this.f19181x) {
            return;
        }
        this.f19181x = z8;
    }

    /* renamed from: a0 */
    public abstract void mo21990a0();

    /* renamed from: b0 */
    public void mo21991b0() throws SmackException.NoResponseException, IOException {
        IOException iOException = this.f19179v;
        if (iOException == null) {
            throw new SmackException.NoResponseException();
        }
        throw iOException;
    }

    /* renamed from: c */
    public void m21992c(InterfaceC6490c interfaceC6490c) {
        if (interfaceC6490c == null || this.f19158a.contains(interfaceC6490c)) {
            return;
        }
        this.f19158a.add(interfaceC6490c);
    }

    /* renamed from: d */
    public void m21993d(InterfaceC5583c interfaceC5583c, InterfaceC6582g interfaceC6582g) {
        if (interfaceC5583c == null) {
            throw new NullPointerException("Packet listener is null.");
        }
        this.f19160c.put(interfaceC5583c, new C5579d(interfaceC5583c, interfaceC6582g));
    }

    /* renamed from: e */
    public void m21994e(InterfaceC5583c interfaceC5583c, InterfaceC6582g interfaceC6582g) {
        if (interfaceC5583c == null) {
            throw new NullPointerException("Packet listener is null.");
        }
        this.f19161d.put(interfaceC5583c, new C5579d(interfaceC5583c, interfaceC6582g));
    }

    /* renamed from: f */
    public String m21995f(String str) {
        synchronized (this.f19177t) {
            if (!this.f19177t.get()) {
                try {
                    this.f19177t.wait(m22013y());
                } catch (InterruptedException unused) {
                }
                if (!this.f19177t.get()) {
                    throw new SmackException.ResourceBindingNotOfferedException();
                }
            }
        }
        Bind bind = new Bind();
        bind.m22052I(str);
        String strM22050G = ((Bind) m22003n(bind).m22023c()).m22050G();
        if (this.f19178u && !mo22008s().m21942s()) {
            m22003n(new Session()).m22023c();
        }
        return strM22050G;
    }

    public void finalize() throws Throwable {
        try {
            this.f19174q.shutdownNow();
        } finally {
            super.finalize();
        }
    }

    /* renamed from: g */
    public void m21996g() {
        Iterator<InterfaceC6490c> it = m22010v().iterator();
        while (it.hasNext()) {
            it.next().authenticated(this);
        }
    }

    /* renamed from: h */
    public void m21997h() {
        Iterator<InterfaceC6490c> it = m22010v().iterator();
        while (it.hasNext()) {
            try {
                it.next().connectionClosed();
            } catch (Exception e9) {
                f19156y.log(Level.SEVERE, "Error in listener while closing connection", (Throwable) e9);
            }
        }
    }

    /* renamed from: i */
    public void m21998i(Exception exc) {
        f19156y.log(Level.WARNING, "Connection closed with error", (Throwable) exc);
        Iterator<InterfaceC6490c> it = m22010v().iterator();
        while (it.hasNext()) {
            try {
                it.next().connectionClosedOnError(exc);
            } catch (Exception e9) {
                f19156y.log(Level.SEVERE, "Error in listener while closing connection", (Throwable) e9);
            }
        }
    }

    /* renamed from: j */
    public void m21999j() {
        Iterator<InterfaceC6490c> it = m22010v().iterator();
        while (it.hasNext()) {
            it.next().connected(this);
        }
    }

    /* renamed from: k */
    public void m22000k() {
        this.f19167j.m22042i();
        this.f19177t.set(false);
        this.f19178u = false;
        this.f19179v = null;
        mo22001l();
    }

    /* renamed from: l */
    public abstract void mo22001l();

    /* renamed from: m */
    public C5582b m22002m(InterfaceC6582g interfaceC6582g) {
        C5582b c5582b = new C5582b(this, interfaceC6582g);
        this.f19159b.add(c5582b);
        return c5582b;
    }

    /* renamed from: n */
    public C5582b m22003n(AbstractC5586IQ abstractC5586IQ) {
        C5582b c5582bM22002m = m22002m(new C6578c(abstractC5586IQ, this));
        m21979P(abstractC5586IQ);
        return c5582bM22002m;
    }

    /* renamed from: o */
    public void m22004o() {
        m22005p(new Presence(Presence.Type.unavailable));
    }

    /* renamed from: p */
    public synchronized void m22005p(Presence presence) {
        if (mo21971H()) {
            m21979P(presence);
            mo21990a0();
            m21997h();
        }
    }

    /* renamed from: q */
    public final void m22006q(AbstractC5594b abstractC5594b) {
        if (abstractC5594b != null) {
            Iterator<C5577b> it = this.f19162e.values().iterator();
            while (it.hasNext()) {
                it.next().m22015a(abstractC5594b);
            }
        }
    }

    /* renamed from: r */
    public final void m22007r(AbstractC5594b abstractC5594b) {
        Iterator<C5579d> it = this.f19161d.values().iterator();
        while (it.hasNext()) {
            try {
                it.next().m22016a(abstractC5594b);
            } catch (SmackException.NotConnectedException unused) {
                f19156y.log(Level.WARNING, "Got not connected exception, aborting");
                return;
            }
        }
    }

    /* renamed from: s */
    public ConnectionConfiguration mo22008s() {
        return this.f19169l;
    }

    /* renamed from: t */
    public int m22009t() {
        return this.f19168k;
    }

    /* renamed from: v */
    public Collection<InterfaceC6490c> m22010v() {
        return this.f19158a;
    }

    /* renamed from: w */
    public String m22011w() {
        return this.f19175r;
    }

    /* renamed from: x */
    public Collection<C5582b> m22012x() {
        return this.f19159b;
    }

    /* renamed from: y */
    public long m22013y() {
        return this.f19163f;
    }

    /* renamed from: z */
    public int m22014z() {
        return this.f19176s;
    }
}
