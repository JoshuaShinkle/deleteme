package org.jivesoftware.smack.tcp;

import com.google.firebase.iid.ServiceStarter;
import java.io.IOException;
import java.io.Writer;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.packet.AbstractC5594b;
import org.jivesoftware.smack.util.BlockingQueueC5607a;

/* renamed from: org.jivesoftware.smack.tcp.b */
/* loaded from: classes.dex */
public class C5602b {

    /* renamed from: g */
    public static final Logger f19416g = Logger.getLogger(C5602b.class.getName());

    /* renamed from: a */
    public final XMPPTCPConnection f19417a;

    /* renamed from: c */
    public Thread f19419c;

    /* renamed from: d */
    public Writer f19420d;

    /* renamed from: e */
    public volatile boolean f19421e;

    /* renamed from: b */
    public final BlockingQueueC5607a<AbstractC5594b> f19418b = new BlockingQueueC5607a<>(ServiceStarter.ERROR_UNKNOWN, true);

    /* renamed from: f */
    public AtomicBoolean f19422f = new AtomicBoolean(false);

    /* renamed from: org.jivesoftware.smack.tcp.b$a */
    public class a extends Thread {
        public a() {
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() throws IOException {
            C5602b.this.m22237i(this);
        }
    }

    public C5602b(XMPPTCPConnection xMPPTCPConnection) {
        this.f19417a = xMPPTCPConnection;
        m22230b();
    }

    /* renamed from: b */
    public void m22230b() {
        this.f19420d = this.f19417a.mo21968E();
        this.f19421e = false;
        this.f19422f.set(false);
        this.f19418b.m22294s();
        a aVar = new a();
        this.f19419c = aVar;
        aVar.setName("Smack Packet Writer (" + this.f19417a.m22009t() + ")");
        this.f19419c.setDaemon(true);
    }

    /* renamed from: c */
    public final AbstractC5594b m22231c() {
        if (this.f19421e) {
            return null;
        }
        try {
            return this.f19418b.take();
        } catch (InterruptedException unused) {
            return null;
        }
    }

    /* renamed from: d */
    public void m22232d() {
        this.f19420d.write("<stream:stream to=\"" + this.f19417a.m21966C() + "\" xmlns=\"jabber:client\" xmlns:stream=\"http://etherx.jabber.org/streams\" version=\"1.0\">");
        this.f19420d.flush();
    }

    /* renamed from: e */
    public void m22233e(AbstractC5594b abstractC5594b) throws SmackException.NotConnectedException {
        if (this.f19421e) {
            throw new SmackException.NotConnectedException();
        }
        try {
            this.f19418b.put(abstractC5594b);
        } catch (InterruptedException unused) {
            throw new SmackException.NotConnectedException();
        }
    }

    /* renamed from: f */
    public void m22234f(Writer writer) {
        this.f19420d = writer;
    }

    /* renamed from: g */
    public void m22235g() {
        if (this.f19421e) {
            return;
        }
        this.f19421e = true;
        this.f19418b.m22293r();
        synchronized (this.f19422f) {
            if (!this.f19422f.get()) {
                try {
                    this.f19422f.wait(this.f19417a.m22013y());
                } catch (InterruptedException e9) {
                    f19416g.log(Level.WARNING, "shutdown", (Throwable) e9);
                }
            }
        }
    }

    /* renamed from: h */
    public void m22236h() {
        this.f19419c.start();
    }

    /* renamed from: i */
    public final void m22237i(Thread thread) throws IOException {
        try {
            m22232d();
            while (!this.f19421e && this.f19419c == thread) {
                AbstractC5594b abstractC5594bM22231c = m22231c();
                if (abstractC5594bM22231c != null) {
                    this.f19420d.write(abstractC5594bM22231c.mo22057u().toString());
                    if (this.f19418b.isEmpty()) {
                        this.f19420d.flush();
                    }
                }
            }
            while (!this.f19418b.isEmpty()) {
                try {
                    this.f19420d.write(this.f19418b.remove().mo22057u().toString());
                } catch (Exception e9) {
                    f19416g.log(Level.WARNING, "Exception flushing queue during shutdown, ignore and continue", (Throwable) e9);
                }
            }
            this.f19420d.flush();
            this.f19418b.clear();
            try {
                try {
                    this.f19420d.write("</stream:stream>");
                    this.f19420d.flush();
                } finally {
                    try {
                        this.f19420d.close();
                    } catch (Exception unused) {
                    }
                }
            } catch (Exception e10) {
                f19416g.log(Level.WARNING, "Exception writing closing stream element", (Throwable) e10);
            }
            this.f19422f.set(true);
            synchronized (this.f19422f) {
                this.f19422f.notify();
            }
        } catch (IOException e11) {
            if (this.f19421e || this.f19417a.m22205j0()) {
                return;
            }
            m22235g();
            this.f19417a.m22211p0(e11);
        }
    }
}
