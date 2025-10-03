package p079g7;

import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jivesoftware.smack.InterfaceC5583c;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.packet.AbstractC5586IQ;
import org.jivesoftware.smack.packet.AbstractC5594b;
import org.jivesoftware.smackx.bytestreams.ibb.InBandBytestreamManager;
import org.jivesoftware.smackx.bytestreams.ibb.packet.Open;
import p069f7.InterfaceC4791a;
import p240x6.C6576a;
import p240x6.C6579d;
import p240x6.C6584i;
import p240x6.InterfaceC6582g;

/* renamed from: g7.d */
/* loaded from: classes.dex */
public class C4971d implements InterfaceC5583c {

    /* renamed from: d */
    public static final Logger f17067d = Logger.getLogger(C4971d.class.getName());

    /* renamed from: a */
    public final InBandBytestreamManager f17068a;

    /* renamed from: b */
    public final InterfaceC6582g f17069b = new C6576a(new C6584i(Open.class), new C6579d(AbstractC5586IQ.a.f19232c));

    /* renamed from: c */
    public final ExecutorService f17070c = Executors.newCachedThreadPool();

    /* renamed from: g7.d$a */
    public class a implements Runnable {

        /* renamed from: b */
        public final /* synthetic */ AbstractC5594b f17071b;

        public a(AbstractC5594b abstractC5594b) {
            this.f17071b = abstractC5594b;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                C4971d.this.m19248d(this.f17071b);
            } catch (SmackException.NotConnectedException e9) {
                C4971d.f17067d.log(Level.WARNING, "proccessRequest", (Throwable) e9);
            }
        }
    }

    public C4971d(InBandBytestreamManager inBandBytestreamManager) {
        this.f17068a = inBandBytestreamManager;
    }

    /* renamed from: c */
    public InterfaceC6582g m19247c() {
        return this.f17069b;
    }

    /* renamed from: d */
    public final void m19248d(AbstractC5594b abstractC5594b) {
        Open open = (Open) abstractC5594b;
        if (open.m22399G() > this.f17068a.m22386f()) {
            this.f17068a.m22391k(open);
            return;
        }
        if (this.f17068a.m22385e().remove(open.m22401I())) {
            return;
        }
        C4970c c4970c = new C4970c(this.f17068a, open);
        InterfaceC4791a interfaceC4791aM22388h = this.f17068a.m22388h(open.m22160j());
        if (interfaceC4791aM22388h != null) {
            interfaceC4791aM22388h.m19025a(c4970c);
        } else {
            if (this.f17068a.m22384c().isEmpty()) {
                this.f17068a.m22390j(open);
                return;
            }
            Iterator<InterfaceC4791a> it = this.f17068a.m22384c().iterator();
            while (it.hasNext()) {
                it.next().m19025a(c4970c);
            }
        }
    }

    /* renamed from: e */
    public void m19249e() {
        this.f17070c.shutdownNow();
    }

    @Override // org.jivesoftware.smack.InterfaceC5583c
    public void processPacket(AbstractC5594b abstractC5594b) {
        this.f17070c.execute(new a(abstractC5594b));
    }
}
