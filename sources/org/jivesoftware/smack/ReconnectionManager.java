package org.jivesoftware.smack;

import java.util.Iterator;
import java.util.Random;
import java.util.logging.Logger;
import org.jivesoftware.smack.XMPPException;
import p222v6.C6488a;
import p222v6.InterfaceC6489b;
import p222v6.InterfaceC6490c;

/* loaded from: classes.dex */
public class ReconnectionManager extends C6488a {

    /* renamed from: e */
    public static final Logger f19148e = Logger.getLogger(ReconnectionManager.class.getName());

    /* renamed from: a */
    public XMPPConnection f19149a;

    /* renamed from: b */
    public Thread f19150b;

    /* renamed from: c */
    public int f19151c;

    /* renamed from: d */
    public boolean f19152d;

    /* renamed from: org.jivesoftware.smack.ReconnectionManager$a */
    public class C5574a implements InterfaceC6489b {
        @Override // p222v6.InterfaceC6489b
        /* renamed from: a */
        public void mo21958a(XMPPConnection xMPPConnection) {
            xMPPConnection.m21992c(new ReconnectionManager(xMPPConnection, null));
        }
    }

    /* renamed from: org.jivesoftware.smack.ReconnectionManager$b */
    public class C5575b extends Thread {

        /* renamed from: b */
        public int f19153b = 0;

        public C5575b() {
        }

        /* renamed from: a */
        public final int m21959a() {
            int i9 = this.f19153b + 1;
            this.f19153b = i9;
            return i9 > 13 ? ReconnectionManager.this.f19151c * 6 * 5 : i9 > 7 ? ReconnectionManager.this.f19151c * 6 : ReconnectionManager.this.f19151c;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() throws InterruptedException {
            while (ReconnectionManager.this.m21954e()) {
                int iM21959a = m21959a();
                while (ReconnectionManager.this.m21954e() && iM21959a > 0) {
                    try {
                        Thread.sleep(1000L);
                        iM21959a--;
                        ReconnectionManager.this.m21955f(iM21959a);
                    } catch (InterruptedException e9) {
                        ReconnectionManager.f19148e.warning("Sleeping thread interrupted");
                        ReconnectionManager.this.m21956g(e9);
                    }
                }
                try {
                    if (ReconnectionManager.this.m21954e()) {
                        ReconnectionManager.this.f19149a.m22000k();
                    }
                } catch (Exception e10) {
                    ReconnectionManager.this.m21956g(e10);
                }
            }
        }
    }

    static {
        XMPPConnection.m21962b(new C5574a());
    }

    public /* synthetic */ ReconnectionManager(XMPPConnection xMPPConnection, C5574a c5574a) {
        this(xMPPConnection);
    }

    @Override // p222v6.C6488a, p222v6.InterfaceC6490c
    public void connectionClosed() {
        this.f19152d = true;
    }

    @Override // p222v6.C6488a, p222v6.InterfaceC6490c
    public void connectionClosedOnError(Exception exc) {
        this.f19152d = false;
        if (!((exc instanceof XMPPException.StreamErrorException) && "conflict".equals(((XMPPException.StreamErrorException) exc).m22017a().m22168a())) && m21954e()) {
            m21957h();
        }
    }

    /* renamed from: e */
    public final boolean m21954e() {
        return (this.f19152d || this.f19149a.mo21971H() || !this.f19149a.mo22008s().m21943t()) ? false : true;
    }

    /* renamed from: f */
    public void m21955f(int i9) {
        if (m21954e()) {
            Iterator<InterfaceC6490c> it = this.f19149a.f19158a.iterator();
            while (it.hasNext()) {
                it.next().reconnectingIn(i9);
            }
        }
    }

    /* renamed from: g */
    public void m21956g(Exception exc) {
        if (m21954e()) {
            Iterator<InterfaceC6490c> it = this.f19149a.f19158a.iterator();
            while (it.hasNext()) {
                it.next().reconnectionFailed(exc);
            }
        }
    }

    /* renamed from: h */
    public synchronized void m21957h() {
        if (m21954e()) {
            Thread thread = this.f19150b;
            if (thread != null && thread.isAlive()) {
                return;
            }
            C5575b c5575b = new C5575b();
            this.f19150b = c5575b;
            c5575b.setName("Smack Reconnection Manager");
            this.f19150b.setDaemon(true);
            this.f19150b.start();
        }
    }

    public ReconnectionManager(XMPPConnection xMPPConnection) {
        this.f19151c = new Random().nextInt(11) + 5;
        this.f19152d = false;
        this.f19149a = xMPPConnection;
    }
}
