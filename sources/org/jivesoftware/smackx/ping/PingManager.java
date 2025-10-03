package org.jivesoftware.smackx.ping;

import com.google.android.gms.auth.api.credentials.CredentialsApi;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jivesoftware.smack.InterfaceC5583c;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.AbstractC5586IQ;
import org.jivesoftware.smack.packet.AbstractC5594b;
import org.jivesoftware.smackx.disco.ServiceDiscoveryManager;
import org.jivesoftware.smackx.ping.packet.Ping;
import org.jivesoftware.smackx.ping.packet.Pong;
import p214u7.InterfaceC6426a;
import p222v6.AbstractC6491d;
import p222v6.C6488a;
import p222v6.InterfaceC6489b;
import p240x6.C6576a;
import p240x6.C6579d;
import p240x6.C6584i;
import p240x6.InterfaceC6582g;

/* loaded from: classes.dex */
public class PingManager extends AbstractC6491d {

    /* renamed from: g */
    public static final Logger f19782g = Logger.getLogger(PingManager.class.getName());

    /* renamed from: h */
    public static final Map<XMPPConnection, PingManager> f19783h = Collections.synchronizedMap(new WeakHashMap());

    /* renamed from: i */
    public static final InterfaceC6582g f19784i = new C6576a(new C6584i(Ping.class), new C6579d(AbstractC5586IQ.a.f19231b));

    /* renamed from: j */
    public static final InterfaceC6582g f19785j = new C6576a(new C6584i(Pong.class), new C6579d(AbstractC5586IQ.a.f19233d));

    /* renamed from: k */
    public static int f19786k;

    /* renamed from: b */
    public final Set<InterfaceC6426a> f19787b;

    /* renamed from: c */
    public int f19788c;

    /* renamed from: d */
    public ScheduledFuture<?> f19789d;

    /* renamed from: e */
    public long f19790e;

    /* renamed from: f */
    public final Runnable f19791f;

    /* renamed from: org.jivesoftware.smackx.ping.PingManager$a */
    public class C5661a implements InterfaceC6489b {
        @Override // p222v6.InterfaceC6489b
        /* renamed from: a */
        public void mo21958a(XMPPConnection xMPPConnection) {
            PingManager.m22638l(xMPPConnection);
        }
    }

    /* renamed from: org.jivesoftware.smackx.ping.PingManager$b */
    public class C5662b implements InterfaceC5583c {
        public C5662b() {
        }

        @Override // org.jivesoftware.smack.InterfaceC5583c
        public void processPacket(AbstractC5594b abstractC5594b) {
            PingManager.this.m24823a().m21979P(new Pong(abstractC5594b));
        }
    }

    /* renamed from: org.jivesoftware.smackx.ping.PingManager$c */
    public class C5663c implements InterfaceC5583c {
        public C5663c() {
        }

        @Override // org.jivesoftware.smack.InterfaceC5583c
        public void processPacket(AbstractC5594b abstractC5594b) {
            PingManager.this.f19790e = System.currentTimeMillis();
        }
    }

    /* renamed from: org.jivesoftware.smackx.ping.PingManager$d */
    public class C5664d extends C6488a {
        public C5664d() {
        }

        @Override // p222v6.C6488a, p222v6.InterfaceC6490c
        public void authenticated(XMPPConnection xMPPConnection) {
            PingManager.this.m22640n();
        }

        @Override // p222v6.C6488a, p222v6.InterfaceC6490c
        public void connectionClosed() {
            PingManager.this.m22642p();
        }

        @Override // p222v6.C6488a, p222v6.InterfaceC6490c
        public void connectionClosedOnError(Exception exc) {
            PingManager.this.m22642p();
        }
    }

    /* renamed from: org.jivesoftware.smackx.ping.PingManager$e */
    public class RunnableC5665e implements Runnable {
        public RunnableC5665e() {
        }

        @Override // java.lang.Runnable
        public void run() throws InterruptedException {
            PingManager.f19782g.fine("ServerPingTask run()");
            XMPPConnection xMPPConnectionM24823a = PingManager.this.m24823a();
            if (xMPPConnectionM24823a != null && PingManager.this.f19788c > 0) {
                long jM22639m = PingManager.this.m22639m();
                if (jM22639m > 0) {
                    int iCurrentTimeMillis = (int) (((PingManager.this.f19788c * CredentialsApi.ACTIVITY_RESULT_ADD_ACCOUNT) - (System.currentTimeMillis() - jM22639m)) / 1000);
                    if (iCurrentTimeMillis > 0) {
                        PingManager.this.m22641o(iCurrentTimeMillis);
                        return;
                    }
                }
                if (!xMPPConnectionM24823a.mo21970G()) {
                    PingManager.f19782g.warning("ServerPingTask: XMPPConnection was not authenticated");
                    return;
                }
                boolean zM22645s = false;
                for (int i9 = 0; i9 < 3; i9++) {
                    if (i9 != 0) {
                        try {
                            Thread.sleep(1000L);
                        } catch (InterruptedException unused) {
                            return;
                        }
                    }
                    try {
                        zM22645s = PingManager.this.m22645s(false);
                    } catch (SmackException e9) {
                        PingManager.f19782g.log(Level.WARNING, "SmackError while pinging server", (Throwable) e9);
                        zM22645s = false;
                    }
                    if (zM22645s) {
                        break;
                    }
                }
                PingManager.f19782g.fine("ServerPingTask res=" + zM22645s);
                if (zM22645s) {
                    PingManager.this.m22640n();
                    return;
                }
                Iterator it = PingManager.this.f19787b.iterator();
                while (it.hasNext()) {
                    ((InterfaceC6426a) it.next()).mo14541a();
                }
            }
        }
    }

    static {
        XMPPConnection.m21962b(new C5661a());
        f19786k = 1800;
    }

    public PingManager(XMPPConnection xMPPConnection) {
        super(xMPPConnection);
        this.f19787b = Collections.synchronizedSet(new HashSet());
        this.f19788c = f19786k;
        this.f19790e = -1L;
        this.f19791f = new RunnableC5665e();
        ServiceDiscoveryManager.m22486j(xMPPConnection).m22488g("urn:xmpp:ping");
        f19783h.put(xMPPConnection, this);
        xMPPConnection.m21993d(new C5662b(), f19784i);
        xMPPConnection.m21993d(new C5663c(), f19785j);
        xMPPConnection.m21992c(new C5664d());
        m22640n();
    }

    /* renamed from: l */
    public static synchronized PingManager m22638l(XMPPConnection xMPPConnection) {
        PingManager pingManager;
        pingManager = f19783h.get(xMPPConnection);
        if (pingManager == null) {
            pingManager = new PingManager(xMPPConnection);
        }
        return pingManager;
    }

    /* renamed from: m */
    public long m22639m() {
        return this.f19790e;
    }

    /* renamed from: n */
    public final void m22640n() {
        m22641o(0);
    }

    /* renamed from: o */
    public final synchronized void m22641o(int i9) {
        m22642p();
        int i10 = this.f19788c;
        if (i10 > 0) {
            int i11 = i10 - i9;
            f19782g.fine("Scheduling ServerPingTask in " + i11 + " seconds (pingInterval=" + this.f19788c + ", delta=" + i9 + ")");
            this.f19789d = m24824b(this.f19791f, (long) i11, TimeUnit.SECONDS);
        }
    }

    /* renamed from: p */
    public final void m22642p() {
        ScheduledFuture<?> scheduledFuture = this.f19789d;
        if (scheduledFuture != null) {
            scheduledFuture.cancel(true);
            this.f19789d = null;
        }
    }

    /* renamed from: q */
    public boolean m22643q(String str) {
        return m22644r(str, m24823a().m22013y());
    }

    /* renamed from: r */
    public boolean m22644r(String str, long j9) {
        try {
            m24823a().m22003n(new Ping(str)).m22023c();
            return true;
        } catch (XMPPException unused) {
            return str.equals(m24823a().m21966C());
        }
    }

    /* renamed from: s */
    public boolean m22645s(boolean z8) {
        boolean zM22643q;
        try {
            zM22643q = m22643q(m24823a().m21966C());
        } catch (SmackException.NoResponseException unused) {
            zM22643q = false;
        }
        if (!zM22643q && z8) {
            Iterator<InterfaceC6426a> it = this.f19787b.iterator();
            while (it.hasNext()) {
                it.next().mo14541a();
            }
        }
        return zM22643q;
    }

    /* renamed from: t */
    public void m22646t(InterfaceC6426a interfaceC6426a) {
        this.f19787b.add(interfaceC6426a);
    }

    /* renamed from: u */
    public void m22647u(int i9) {
        this.f19788c = i9;
        m22640n();
    }
}
