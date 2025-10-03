package p209u2;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;

/* renamed from: u2.j */
/* loaded from: classes.dex */
public class ThreadFactoryC6373j implements ThreadFactory {

    /* renamed from: a */
    public final String f21523a;

    /* renamed from: b */
    public final AtomicLong f21524b = new AtomicLong();

    public ThreadFactoryC6373j(String str) {
        this.f21523a = str;
    }

    @Override // java.util.concurrent.ThreadFactory
    public Thread newThread(Runnable runnable) {
        String str = this.f21523a;
        if (this.f21524b.getAndIncrement() > 0) {
            str = str + "-" + this.f21524b.get();
        }
        return new Thread(runnable, str);
    }
}
