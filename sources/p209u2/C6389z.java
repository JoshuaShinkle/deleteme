package p209u2;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import p209u2.C6389z.b;

/* renamed from: u2.z */
/* loaded from: classes.dex */
public final class C6389z<T extends b> {

    /* renamed from: a */
    public final String f21566a;

    /* renamed from: b */
    public final ExecutorService f21567b;

    /* renamed from: c */
    public final ArrayList<WeakReference<T>> f21568c = new ArrayList<>();

    /* renamed from: u2.z$a */
    public static abstract class a<T extends b> {
        /* renamed from: a */
        public void mo5634a(String str, Throwable th) {
        }

        /* renamed from: b */
        public abstract void mo5635b(T t8);
    }

    /* renamed from: u2.z$b */
    public interface b {
    }

    public C6389z(String str) {
        String str2 = "WeakObservers." + str;
        this.f21566a = str2;
        this.f21567b = new ThreadPoolExecutor(0, 1, 5L, TimeUnit.MINUTES, new LinkedBlockingQueue(), new ThreadFactoryC6373j(str2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: d */
    public /* synthetic */ void m24536d(WeakReference weakReference) {
        synchronized (this.f21568c) {
            WeakReference[] weakReferenceArrM24538h = m24538h(this.f21568c);
            b bVar = (b) weakReference.get();
            boolean z8 = false;
            for (WeakReference weakReference2 : weakReferenceArrM24538h) {
                b bVar2 = (b) weakReference2.get();
                if (bVar2 == null) {
                    this.f21568c.remove(weakReference2);
                } else if (bVar2 == bVar) {
                    z8 = true;
                }
            }
            if (!z8 && bVar != null) {
                this.f21568c.add(weakReference);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: e */
    public /* synthetic */ void m24537e(WeakReference weakReference) {
        synchronized (this.f21568c) {
            WeakReference[] weakReferenceArrM24538h = m24538h(this.f21568c);
            b bVar = (b) weakReference.get();
            int length = weakReferenceArrM24538h.length;
            int i9 = 0;
            while (true) {
                if (i9 >= length) {
                    break;
                }
                WeakReference weakReference2 = weakReferenceArrM24538h[i9];
                b bVar2 = (b) weakReference2.get();
                if (bVar2 == null) {
                    this.f21568c.remove(weakReference2);
                } else if (bVar2 == bVar) {
                    this.f21568c.remove(weakReference2);
                    break;
                }
                i9++;
            }
        }
    }

    /* renamed from: h */
    public static <T> WeakReference<T>[] m24538h(ArrayList<WeakReference<T>> arrayList) {
        return (WeakReference[]) arrayList.toArray(new WeakReference[arrayList.size()]);
    }

    /* renamed from: c */
    public void m24539c(T t8) {
        final WeakReference weakReference = new WeakReference(t8);
        this.f21567b.execute(new Runnable() { // from class: u2.y
            @Override // java.lang.Runnable
            public final void run() {
                this.f21564b.m24536d(weakReference);
            }
        });
    }

    /* renamed from: f */
    public void m24540f(a<T> aVar) {
        synchronized (this.f21568c) {
            System.currentTimeMillis();
            for (WeakReference weakReference : m24538h(this.f21568c)) {
                b bVar = (b) weakReference.get();
                if (bVar == null) {
                    this.f21568c.remove(weakReference);
                } else {
                    try {
                        aVar.mo5635b(bVar);
                    } catch (Throwable th) {
                        aVar.mo5634a(this.f21566a, th);
                    }
                }
            }
        }
    }

    public void finalize() throws Throwable {
        super.finalize();
        this.f21567b.shutdown();
    }

    /* renamed from: g */
    public void m24541g(T t8) {
        final WeakReference weakReference = new WeakReference(t8);
        this.f21567b.execute(new Runnable() { // from class: u2.x
            @Override // java.lang.Runnable
            public final void run() {
                this.f21562b.m24537e(weakReference);
            }
        });
    }
}
