package p222v6;

import java.lang.ref.WeakReference;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import org.jivesoftware.smack.XMPPConnection;

/* renamed from: v6.d */
/* loaded from: classes.dex */
public abstract class AbstractC6491d {

    /* renamed from: a */
    public final WeakReference<XMPPConnection> f21813a;

    public AbstractC6491d(XMPPConnection xMPPConnection) {
        this.f21813a = new WeakReference<>(xMPPConnection);
    }

    /* renamed from: a */
    public final XMPPConnection m24823a() {
        return this.f21813a.get();
    }

    /* renamed from: b */
    public ScheduledFuture<?> m24824b(Runnable runnable, long j9, TimeUnit timeUnit) {
        return m24823a().m21978O(runnable, j9, timeUnit);
    }
}
