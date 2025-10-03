package com.bumptech.glide.load.engine;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import com.bumptech.glide.load.engine.C0839h;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import p012b1.InterfaceC0595j;
import p226w1.C6516i;
import p226w1.C6517j;
import p243y0.InterfaceC6589b;

/* renamed from: com.bumptech.glide.load.engine.a */
/* loaded from: classes.dex */
public final class C0832a {

    /* renamed from: a */
    public final boolean f3757a;

    /* renamed from: b */
    public final Handler f3758b = new Handler(Looper.getMainLooper(), new a());

    /* renamed from: c */
    public final Map<InterfaceC6589b, c> f3759c = new HashMap();

    /* renamed from: d */
    public C0839h.a f3760d;

    /* renamed from: e */
    public ReferenceQueue<C0839h<?>> f3761e;

    /* renamed from: f */
    public Thread f3762f;

    /* renamed from: g */
    public volatile boolean f3763g;

    /* renamed from: com.bumptech.glide.load.engine.a$a */
    public class a implements Handler.Callback {
        public a() {
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            if (message.what != 1) {
                return false;
            }
            C0832a.this.m3894c((c) message.obj);
            return true;
        }
    }

    /* renamed from: com.bumptech.glide.load.engine.a$b */
    public class b implements Runnable {
        public b() {
        }

        @Override // java.lang.Runnable
        public void run() throws SecurityException, IllegalArgumentException {
            Process.setThreadPriority(10);
            C0832a.this.m3893b();
        }
    }

    /* renamed from: com.bumptech.glide.load.engine.a$c */
    public static final class c extends WeakReference<C0839h<?>> {

        /* renamed from: a */
        public final InterfaceC6589b f3766a;

        /* renamed from: b */
        public final boolean f3767b;

        /* renamed from: c */
        public InterfaceC0595j<?> f3768c;

        public c(InterfaceC6589b interfaceC6589b, C0839h<?> c0839h, ReferenceQueue<? super C0839h<?>> referenceQueue, boolean z8) {
            super(c0839h, referenceQueue);
            this.f3766a = (InterfaceC6589b) C6516i.m24938d(interfaceC6589b);
            this.f3768c = (c0839h.m3960f() && z8) ? (InterfaceC0595j) C6516i.m24938d(c0839h.m3959e()) : null;
            this.f3767b = c0839h.m3960f();
        }

        /* renamed from: a */
        public void m3899a() {
            this.f3768c = null;
            clear();
        }
    }

    public C0832a(boolean z8) {
        this.f3757a = z8;
    }

    /* renamed from: a */
    public void m3892a(InterfaceC6589b interfaceC6589b, C0839h<?> c0839h) {
        c cVarPut = this.f3759c.put(interfaceC6589b, new c(interfaceC6589b, c0839h, m3897f(), this.f3757a));
        if (cVarPut != null) {
            cVarPut.m3899a();
        }
    }

    /* renamed from: b */
    public void m3893b() {
        while (!this.f3763g) {
            try {
                this.f3758b.obtainMessage(1, (c) this.f3761e.remove()).sendToTarget();
            } catch (InterruptedException unused) {
                Thread.currentThread().interrupt();
            }
        }
    }

    /* renamed from: c */
    public void m3894c(c cVar) {
        InterfaceC0595j<?> interfaceC0595j;
        C6517j.m24941b();
        this.f3759c.remove(cVar.f3766a);
        if (!cVar.f3767b || (interfaceC0595j = cVar.f3768c) == null) {
            return;
        }
        C0839h<?> c0839h = new C0839h<>(interfaceC0595j, true, false);
        c0839h.m3962h(cVar.f3766a, this.f3760d);
        this.f3760d.mo3932d(cVar.f3766a, c0839h);
    }

    /* renamed from: d */
    public void m3895d(InterfaceC6589b interfaceC6589b) {
        c cVarRemove = this.f3759c.remove(interfaceC6589b);
        if (cVarRemove != null) {
            cVarRemove.m3899a();
        }
    }

    /* renamed from: e */
    public C0839h<?> m3896e(InterfaceC6589b interfaceC6589b) {
        c cVar = this.f3759c.get(interfaceC6589b);
        if (cVar == null) {
            return null;
        }
        C0839h<?> c0839h = cVar.get();
        if (c0839h == null) {
            m3894c(cVar);
        }
        return c0839h;
    }

    /* renamed from: f */
    public final ReferenceQueue<C0839h<?>> m3897f() {
        if (this.f3761e == null) {
            this.f3761e = new ReferenceQueue<>();
            Thread thread = new Thread(new b(), "glide-active-resources");
            this.f3762f = thread;
            thread.start();
        }
        return this.f3761e;
    }

    /* renamed from: g */
    public void m3898g(C0839h.a aVar) {
        this.f3760d = aVar;
    }
}
