package p200t2;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;

/* renamed from: t2.a */
/* loaded from: classes.dex */
public class ThreadFactoryC6285a implements ThreadFactory {

    /* renamed from: a */
    public final String f21195a;

    /* renamed from: b */
    public final AtomicLong f21196b = new AtomicLong();

    public ThreadFactoryC6285a(String str) {
        this.f21195a = str;
    }

    @Override // java.util.concurrent.ThreadFactory
    public Thread newThread(Runnable runnable) {
        String str = this.f21195a;
        if (this.f21196b.getAndIncrement() > 0) {
            str = str + "-" + this.f21196b.get();
        }
        return new Thread(runnable, str);
    }
}
