package p182r2;

import com.cyberlink.util.PriorityThreadPoolExecutor;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import p209u2.ThreadFactoryC6373j;

/* renamed from: r2.k */
/* loaded from: classes.dex */
public class C6203k extends AbstractC6191b {

    /* renamed from: g */
    public final Object f20891g = new Object();

    /* renamed from: h */
    public final ConcurrentHashMap<String, PriorityThreadPoolExecutor> f20892h = new ConcurrentHashMap<>();

    /* renamed from: i */
    public boolean f20893i = false;

    /* renamed from: j */
    public boolean f20894j = false;

    @Override // p182r2.AbstractC6195d
    /* renamed from: d */
    public String mo23688d() {
        return "XSender.M";
    }

    /* renamed from: q */
    public void m23724q() {
        this.f20864c.getQueue().clear();
        synchronized (this.f20891g) {
            Iterator<PriorityThreadPoolExecutor> it = this.f20892h.values().iterator();
            while (it.hasNext()) {
                it.next().m7345c();
            }
            this.f20892h.clear();
        }
    }

    /* renamed from: r */
    public final PriorityThreadPoolExecutor m23725r(String str) {
        PriorityThreadPoolExecutor priorityThreadPoolExecutor = this.f20892h.get(str);
        if (priorityThreadPoolExecutor == null) {
            synchronized (this.f20891g) {
                priorityThreadPoolExecutor = this.f20892h.get(str);
                if (priorityThreadPoolExecutor == null) {
                    priorityThreadPoolExecutor = new PriorityThreadPoolExecutor(true, 0, 1, 60L, TimeUnit.SECONDS, new ThreadFactoryC6373j("XSender.M"));
                    if (this.f20894j) {
                        priorityThreadPoolExecutor.m24495a();
                    }
                    this.f20892h.put(str, priorityThreadPoolExecutor);
                }
            }
        }
        return priorityThreadPoolExecutor;
    }

    /* renamed from: s */
    public void m23726s(boolean z8) {
        synchronized (this.f20891g) {
            this.f20894j = z8;
            for (PriorityThreadPoolExecutor priorityThreadPoolExecutor : this.f20892h.values()) {
                if (z8) {
                    priorityThreadPoolExecutor.m24495a();
                } else {
                    priorityThreadPoolExecutor.m24496b();
                }
            }
        }
    }

    /* renamed from: t */
    public void m23727t() {
        if (this.f20893i) {
            throw new IllegalStateException("Has initialized");
        }
        this.f20893i = true;
        this.f20864c.submit(new RunnableC6200h());
    }

    /* renamed from: u */
    public void m23728u(C6201i c6201i) {
        m23725r(c6201i.f20883b).m7347f(C6205m.m23734a(this, c6201i));
        m23674m(c6201i);
        if (this.f20894j) {
            mo23669g(c6201i);
        }
    }
}
