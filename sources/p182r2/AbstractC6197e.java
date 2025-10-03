package p182r2;

import com.cyberlink.util.PriorityThreadPoolExecutor;
import com.cyberlink.you.chat.XMPPManager;
import com.google.android.exoplayer2.source.smoothstreaming.SsMediaSource;
import java.util.concurrent.atomic.AtomicBoolean;
import org.jivesoftware.smack.packet.Message;
import p182r2.AbstractC6189a;
import p182r2.AbstractC6195d;

/* renamed from: r2.e */
/* loaded from: classes.dex */
public abstract class AbstractC6197e<Sender extends AbstractC6195d<MsgEx>, MsgEx extends AbstractC6189a> extends PriorityThreadPoolExecutor.AbstractRunnableC1405e {

    /* renamed from: e */
    public final Sender f20874e;

    /* renamed from: f */
    public final MsgEx f20875f;

    /* renamed from: r2.e$a */
    public class a implements XMPPManager.InterfaceC2873x {

        /* renamed from: a */
        public final /* synthetic */ Object f20876a;

        /* renamed from: b */
        public final /* synthetic */ AtomicBoolean f20877b;

        /* renamed from: c */
        public final /* synthetic */ AtomicBoolean f20878c;

        public a(Object obj, AtomicBoolean atomicBoolean, AtomicBoolean atomicBoolean2) {
            this.f20876a = obj;
            this.f20877b = atomicBoolean;
            this.f20878c = atomicBoolean2;
        }

        @Override // com.cyberlink.you.chat.XMPPManager.InterfaceC2873x
        /* renamed from: a */
        public void mo5816a() {
            C6218z.m23764c(AbstractC6197e.this.mo23706d(), "[%s] > failed", AbstractC6197e.this.mo23707e());
            synchronized (this.f20876a) {
                this.f20877b.set(true);
                this.f20876a.notify();
            }
        }

        @Override // com.cyberlink.you.chat.XMPPManager.InterfaceC2873x
        public void onSuccess() {
            C6218z.m23762a(AbstractC6197e.this.mo23706d(), "[%s] > success", AbstractC6197e.this.mo23707e());
            synchronized (this.f20876a) {
                this.f20877b.set(true);
                this.f20878c.set(true);
                this.f20876a.notify();
            }
        }
    }

    public AbstractC6197e(Sender sender, MsgEx msgex, PriorityThreadPoolExecutor.Priority priority) {
        super(priority);
        this.f20874e = sender;
        this.f20875f = msgex;
    }

    /* renamed from: c */
    public abstract Message mo23705c();

    /* renamed from: d */
    public abstract String mo23706d();

    /* renamed from: e */
    public abstract String mo23707e();

    /* renamed from: f */
    public void mo23708f() {
    }

    /* renamed from: g */
    public void mo23709g() {
    }

    /* renamed from: h */
    public final boolean m23710h(Message message) {
        C6218z.m23767f(mo23706d(), "[%s] > send", mo23707e());
        Object obj = new Object();
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        AtomicBoolean atomicBoolean2 = new AtomicBoolean(false);
        synchronized (obj) {
            this.f20874e.mo23670h(this.f20875f);
            XMPPManager.m14184g0().m14241e1(message, new a(obj, atomicBoolean2, atomicBoolean));
            if (!atomicBoolean2.get()) {
                try {
                    obj.wait(SsMediaSource.DEFAULT_LIVE_PRESENTATION_DELAY_MS);
                } catch (InterruptedException unused) {
                    Thread.currentThread().interrupt();
                }
            }
        }
        return atomicBoolean.get();
    }

    @Override // java.lang.Runnable
    public void run() {
        C6218z.m23767f(mo23706d(), "[%s] run", mo23707e());
        Message messageMo23705c = mo23705c();
        if (messageMo23705c == null) {
            return;
        }
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (!m23710h(messageMo23705c)) {
            this.f20874e.mo23669g(this.f20875f);
            C6218z.m23770i(mo23706d(), "[%s] > failed, next one", mo23707e());
        } else if (this.f20874e.mo23671i(this.f20875f)) {
            mo23709g();
            C6218z.m23767f(mo23706d(), "[%s] > took %dms", mo23707e(), Long.valueOf(System.currentTimeMillis() - jCurrentTimeMillis));
        } else {
            mo23708f();
            this.f20874e.mo23669g(this.f20875f);
            C6218z.m23770i(mo23706d(), "[%s] > no ack, next one", mo23707e());
        }
    }

    public AbstractC6197e(Sender sender, MsgEx msgex, long j9) {
        super(PriorityThreadPoolExecutor.Priority.NORMAL, j9);
        this.f20874e = sender;
        this.f20875f = msgex;
    }
}
