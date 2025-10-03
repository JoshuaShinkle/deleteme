package p099i7;

import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jivesoftware.smack.InterfaceC5583c;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.packet.AbstractC5586IQ;
import org.jivesoftware.smack.packet.AbstractC5594b;
import org.jivesoftware.smackx.bytestreams.socks5.Socks5BytestreamManager;
import org.jivesoftware.smackx.bytestreams.socks5.packet.Bytestream;
import p069f7.InterfaceC4791a;
import p240x6.C6576a;
import p240x6.C6579d;
import p240x6.C6584i;
import p240x6.InterfaceC6582g;

/* renamed from: i7.a */
/* loaded from: classes.dex */
public final class C5058a implements InterfaceC5583c {

    /* renamed from: d */
    public static final Logger f17452d = Logger.getLogger(C5058a.class.getName());

    /* renamed from: a */
    public final Socks5BytestreamManager f17453a;

    /* renamed from: b */
    public final InterfaceC6582g f17454b = new C6576a(new C6584i(Bytestream.class), new C6579d(AbstractC5586IQ.a.f19232c));

    /* renamed from: c */
    public final ExecutorService f17455c = Executors.newCachedThreadPool();

    /* renamed from: i7.a$a */
    public class a implements Runnable {

        /* renamed from: b */
        public final /* synthetic */ AbstractC5594b f17456b;

        public a(AbstractC5594b abstractC5594b) {
            this.f17456b = abstractC5594b;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                C5058a.this.m19816d(this.f17456b);
            } catch (SmackException.NotConnectedException e9) {
                C5058a.f17452d.log(Level.WARNING, "process request", (Throwable) e9);
            }
        }
    }

    public C5058a(Socks5BytestreamManager socks5BytestreamManager) {
        this.f17453a = socks5BytestreamManager;
    }

    /* renamed from: c */
    public InterfaceC6582g m19815c() {
        return this.f17454b;
    }

    /* renamed from: d */
    public final void m19816d(AbstractC5594b abstractC5594b) {
        Bytestream bytestream = (Bytestream) abstractC5594b;
        if (this.f17453a.m22407f().remove(bytestream.m22416M())) {
            return;
        }
        C5059b c5059b = new C5059b(this.f17453a, bytestream);
        InterfaceC4791a interfaceC4791aM22408g = this.f17453a.m22408g(bytestream.m22160j());
        if (interfaceC4791aM22408g != null) {
            interfaceC4791aM22408g.m19025a(c5059b);
        } else {
            if (this.f17453a.m22406d().isEmpty()) {
                this.f17453a.m22409h(bytestream);
                return;
            }
            Iterator<InterfaceC4791a> it = this.f17453a.m22406d().iterator();
            while (it.hasNext()) {
                it.next().m19025a(c5059b);
            }
        }
    }

    /* renamed from: e */
    public void m19817e() {
        this.f17455c.shutdownNow();
    }

    @Override // org.jivesoftware.smack.InterfaceC5583c
    public void processPacket(AbstractC5594b abstractC5594b) {
        this.f17455c.execute(new a(abstractC5594b));
    }
}
