package p209u2;

import java.util.concurrent.atomic.AtomicBoolean;

/* renamed from: u2.a */
/* loaded from: classes.dex */
public abstract class AbstractRunnableC6364a implements Runnable {

    /* renamed from: b */
    public final AtomicBoolean f21502b = new AtomicBoolean(false);

    /* renamed from: a */
    public void m24447a() {
        if (this.f21502b.getAndSet(true)) {
            return;
        }
        m24449c();
    }

    /* renamed from: b */
    public boolean m24448b() {
        return this.f21502b.get();
    }

    /* renamed from: c */
    public void m24449c() {
    }
}
