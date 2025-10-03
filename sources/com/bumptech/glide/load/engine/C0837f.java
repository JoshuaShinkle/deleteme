package com.bumptech.glide.load.engine;

import android.util.Log;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.C0839h;
import com.bumptech.glide.load.engine.DecodeJob;
import java.util.Map;
import p012b1.AbstractC0588c;
import p012b1.C0590e;
import p012b1.C0591f;
import p012b1.C0593h;
import p012b1.C0597l;
import p012b1.InterfaceC0589d;
import p012b1.InterfaceC0595j;
import p021c0.InterfaceC0699e;
import p043d1.C4654b;
import p043d1.InterfaceC4653a;
import p043d1.InterfaceC4661i;
import p053e1.ExecutorServiceC4708a;
import p190s1.InterfaceC6251h;
import p207u0.C6357g;
import p226w1.C6512e;
import p226w1.C6516i;
import p226w1.C6517j;
import p235x1.C6563a;
import p243y0.C6592e;
import p243y0.InterfaceC6589b;
import p243y0.InterfaceC6595h;

/* renamed from: com.bumptech.glide.load.engine.f */
/* loaded from: classes.dex */
public class C0837f implements InterfaceC0589d, InterfaceC4661i.a, C0839h.a {

    /* renamed from: i */
    public static final boolean f3801i = Log.isLoggable("Engine", 2);

    /* renamed from: a */
    public final C0593h f3802a;

    /* renamed from: b */
    public final C0591f f3803b;

    /* renamed from: c */
    public final InterfaceC4661i f3804c;

    /* renamed from: d */
    public final b f3805d;

    /* renamed from: e */
    public final C0597l f3806e;

    /* renamed from: f */
    public final c f3807f;

    /* renamed from: g */
    public final a f3808g;

    /* renamed from: h */
    public final C0832a f3809h;

    /* renamed from: com.bumptech.glide.load.engine.f$a */
    public static class a {

        /* renamed from: a */
        public final DecodeJob.InterfaceC0829e f3810a;

        /* renamed from: b */
        public final InterfaceC0699e<DecodeJob<?>> f3811b = C6563a.m25127d(150, new C6842a());

        /* renamed from: c */
        public int f3812c;

        /* renamed from: com.bumptech.glide.load.engine.f$a$a, reason: collision with other inner class name */
        public class C6842a implements C6563a.d<DecodeJob<?>> {
            public C6842a() {
            }

            @Override // p235x1.C6563a.d
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public DecodeJob<?> mo3287a() {
                a aVar = a.this;
                return new DecodeJob<>(aVar.f3810a, aVar.f3811b);
            }
        }

        public a(DecodeJob.InterfaceC0829e interfaceC0829e) {
            this.f3810a = interfaceC0829e;
        }

        /* renamed from: a */
        public <R> DecodeJob<R> m3939a(C6357g c6357g, Object obj, C0590e c0590e, InterfaceC6589b interfaceC6589b, int i9, int i10, Class<?> cls, Class<R> cls2, Priority priority, AbstractC0588c abstractC0588c, Map<Class<?>, InterfaceC6595h<?>> map, boolean z8, boolean z9, boolean z10, C6592e c6592e, DecodeJob.InterfaceC0826b<R> interfaceC0826b) {
            DecodeJob decodeJob = (DecodeJob) C6516i.m24938d(this.f3811b.mo3465b());
            int i11 = this.f3812c;
            this.f3812c = i11 + 1;
            return decodeJob.m3854n(c6357g, obj, c0590e, interfaceC6589b, i9, i10, cls, cls2, priority, abstractC0588c, map, z8, z9, z10, c6592e, interfaceC0826b, i11);
        }
    }

    /* renamed from: com.bumptech.glide.load.engine.f$b */
    public static class b {

        /* renamed from: a */
        public final ExecutorServiceC4708a f3814a;

        /* renamed from: b */
        public final ExecutorServiceC4708a f3815b;

        /* renamed from: c */
        public final ExecutorServiceC4708a f3816c;

        /* renamed from: d */
        public final ExecutorServiceC4708a f3817d;

        /* renamed from: e */
        public final InterfaceC0589d f3818e;

        /* renamed from: f */
        public final InterfaceC0699e<C0838g<?>> f3819f = C6563a.m25127d(150, new a());

        /* renamed from: com.bumptech.glide.load.engine.f$b$a */
        public class a implements C6563a.d<C0838g<?>> {
            public a() {
            }

            @Override // p235x1.C6563a.d
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public C0838g<?> mo3287a() {
                b bVar = b.this;
                return new C0838g<>(bVar.f3814a, bVar.f3815b, bVar.f3816c, bVar.f3817d, bVar.f3818e, bVar.f3819f);
            }
        }

        public b(ExecutorServiceC4708a executorServiceC4708a, ExecutorServiceC4708a executorServiceC4708a2, ExecutorServiceC4708a executorServiceC4708a3, ExecutorServiceC4708a executorServiceC4708a4, InterfaceC0589d interfaceC0589d) {
            this.f3814a = executorServiceC4708a;
            this.f3815b = executorServiceC4708a2;
            this.f3816c = executorServiceC4708a3;
            this.f3817d = executorServiceC4708a4;
            this.f3818e = interfaceC0589d;
        }

        /* renamed from: a */
        public <R> C0838g<R> m3941a(InterfaceC6589b interfaceC6589b, boolean z8, boolean z9, boolean z10, boolean z11) {
            return ((C0838g) C6516i.m24938d(this.f3819f.mo3465b())).m3951l(interfaceC6589b, z8, z9, z10, z11);
        }
    }

    /* renamed from: com.bumptech.glide.load.engine.f$c */
    public static class c implements DecodeJob.InterfaceC0829e {

        /* renamed from: a */
        public final InterfaceC4653a.a f3821a;

        /* renamed from: b */
        public volatile InterfaceC4653a f3822b;

        public c(InterfaceC4653a.a aVar) {
            this.f3821a = aVar;
        }

        @Override // com.bumptech.glide.load.engine.DecodeJob.InterfaceC0829e
        /* renamed from: a */
        public InterfaceC4653a mo3875a() {
            if (this.f3822b == null) {
                synchronized (this) {
                    if (this.f3822b == null) {
                        this.f3822b = this.f3821a.build();
                    }
                    if (this.f3822b == null) {
                        this.f3822b = new C4654b();
                    }
                }
            }
            return this.f3822b;
        }
    }

    /* renamed from: com.bumptech.glide.load.engine.f$d */
    public static class d {

        /* renamed from: a */
        public final C0838g<?> f3823a;

        /* renamed from: b */
        public final InterfaceC6251h f3824b;

        public d(InterfaceC6251h interfaceC6251h, C0838g<?> c0838g) {
            this.f3824b = interfaceC6251h;
            this.f3823a = c0838g;
        }

        /* renamed from: a */
        public void m3943a() {
            this.f3823a.m3955p(this.f3824b);
        }
    }

    public C0837f(InterfaceC4661i interfaceC4661i, InterfaceC4653a.a aVar, ExecutorServiceC4708a executorServiceC4708a, ExecutorServiceC4708a executorServiceC4708a2, ExecutorServiceC4708a executorServiceC4708a3, ExecutorServiceC4708a executorServiceC4708a4, boolean z8) {
        this(interfaceC4661i, aVar, executorServiceC4708a, executorServiceC4708a2, executorServiceC4708a3, executorServiceC4708a4, null, null, null, null, null, null, z8);
    }

    /* renamed from: j */
    public static void m3930j(String str, long j9, InterfaceC6589b interfaceC6589b) {
        Log.v("Engine", str + " in " + C6512e.m24922a(j9) + "ms, key: " + interfaceC6589b);
    }

    @Override // p012b1.InterfaceC0589d
    /* renamed from: a */
    public void mo3271a(C0838g<?> c0838g, InterfaceC6589b interfaceC6589b, C0839h<?> c0839h) {
        C6517j.m24941b();
        if (c0839h != null) {
            c0839h.m3962h(interfaceC6589b, this);
            if (c0839h.m3960f()) {
                this.f3809h.m3892a(interfaceC6589b, c0839h);
            }
        }
        this.f3802a.m3278d(interfaceC6589b, c0838g);
    }

    @Override // p012b1.InterfaceC0589d
    /* renamed from: b */
    public void mo3272b(C0838g<?> c0838g, InterfaceC6589b interfaceC6589b) {
        C6517j.m24941b();
        this.f3802a.m3278d(interfaceC6589b, c0838g);
    }

    @Override // p043d1.InterfaceC4661i.a
    /* renamed from: c */
    public void mo3931c(InterfaceC0595j<?> interfaceC0595j) {
        C6517j.m24941b();
        this.f3806e.m3290a(interfaceC0595j);
    }

    @Override // com.bumptech.glide.load.engine.C0839h.a
    /* renamed from: d */
    public void mo3932d(InterfaceC6589b interfaceC6589b, C0839h<?> c0839h) {
        C6517j.m24941b();
        this.f3809h.m3895d(interfaceC6589b);
        if (c0839h.m3960f()) {
            this.f3804c.mo18612d(interfaceC6589b, c0839h);
        } else {
            this.f3806e.m3290a(c0839h);
        }
    }

    /* renamed from: e */
    public void m3933e() {
        this.f3807f.mo3875a().clear();
    }

    /* renamed from: f */
    public final C0839h<?> m3934f(InterfaceC6589b interfaceC6589b) {
        InterfaceC0595j<?> interfaceC0595jMo18613e = this.f3804c.mo18613e(interfaceC6589b);
        if (interfaceC0595jMo18613e == null) {
            return null;
        }
        return interfaceC0595jMo18613e instanceof C0839h ? (C0839h) interfaceC0595jMo18613e : new C0839h<>(interfaceC0595jMo18613e, true, true);
    }

    /* renamed from: g */
    public <R> d m3935g(C6357g c6357g, Object obj, InterfaceC6589b interfaceC6589b, int i9, int i10, Class<?> cls, Class<R> cls2, Priority priority, AbstractC0588c abstractC0588c, Map<Class<?>, InterfaceC6595h<?>> map, boolean z8, boolean z9, C6592e c6592e, boolean z10, boolean z11, boolean z12, boolean z13, InterfaceC6251h interfaceC6251h) {
        C6517j.m24941b();
        boolean z14 = f3801i;
        long jM24923b = z14 ? C6512e.m24923b() : 0L;
        C0590e c0590eM3273a = this.f3803b.m3273a(obj, interfaceC6589b, i9, i10, map, cls, cls2, c6592e);
        C0839h<?> c0839hM3936h = m3936h(c0590eM3273a, z10);
        if (c0839hM3936h != null) {
            interfaceC6251h.mo4010c(c0839hM3936h, DataSource.MEMORY_CACHE);
            if (z14) {
                m3930j("Loaded resource from active resources", jM24923b, c0590eM3273a);
            }
            return null;
        }
        C0839h<?> c0839hM3937i = m3937i(c0590eM3273a, z10);
        if (c0839hM3937i != null) {
            interfaceC6251h.mo4010c(c0839hM3937i, DataSource.MEMORY_CACHE);
            if (z14) {
                m3930j("Loaded resource from cache", jM24923b, c0590eM3273a);
            }
            return null;
        }
        C0838g<?> c0838gM3275a = this.f3802a.m3275a(c0590eM3273a, z13);
        if (c0838gM3275a != null) {
            c0838gM3275a.m3944b(interfaceC6251h);
            if (z14) {
                m3930j("Added to existing load", jM24923b, c0590eM3273a);
            }
            return new d(interfaceC6251h, c0838gM3275a);
        }
        C0838g<R> c0838gM3941a = this.f3805d.m3941a(c0590eM3273a, z10, z11, z12, z13);
        DecodeJob<R> decodeJobM3939a = this.f3808g.m3939a(c6357g, obj, c0590eM3273a, interfaceC6589b, i9, i10, cls, cls2, priority, abstractC0588c, map, z8, z9, z13, c6592e, c0838gM3941a);
        this.f3802a.m3277c(c0590eM3273a, c0838gM3941a);
        c0838gM3941a.m3944b(interfaceC6251h);
        c0838gM3941a.m3956q(decodeJobM3939a);
        if (z14) {
            m3930j("Started new load", jM24923b, c0590eM3273a);
        }
        return new d(interfaceC6251h, c0838gM3941a);
    }

    /* renamed from: h */
    public final C0839h<?> m3936h(InterfaceC6589b interfaceC6589b, boolean z8) {
        if (!z8) {
            return null;
        }
        C0839h<?> c0839hM3896e = this.f3809h.m3896e(interfaceC6589b);
        if (c0839hM3896e != null) {
            c0839hM3896e.m3958a();
        }
        return c0839hM3896e;
    }

    /* renamed from: i */
    public final C0839h<?> m3937i(InterfaceC6589b interfaceC6589b, boolean z8) {
        if (!z8) {
            return null;
        }
        C0839h<?> c0839hM3934f = m3934f(interfaceC6589b);
        if (c0839hM3934f != null) {
            c0839hM3934f.m3958a();
            this.f3809h.m3892a(interfaceC6589b, c0839hM3934f);
        }
        return c0839hM3934f;
    }

    /* renamed from: k */
    public void m3938k(InterfaceC0595j<?> interfaceC0595j) {
        C6517j.m24941b();
        if (!(interfaceC0595j instanceof C0839h)) {
            throw new IllegalArgumentException("Cannot release anything but an EngineResource");
        }
        ((C0839h) interfaceC0595j).m3961g();
    }

    public C0837f(InterfaceC4661i interfaceC4661i, InterfaceC4653a.a aVar, ExecutorServiceC4708a executorServiceC4708a, ExecutorServiceC4708a executorServiceC4708a2, ExecutorServiceC4708a executorServiceC4708a3, ExecutorServiceC4708a executorServiceC4708a4, C0593h c0593h, C0591f c0591f, C0832a c0832a, b bVar, a aVar2, C0597l c0597l, boolean z8) {
        this.f3804c = interfaceC4661i;
        c cVar = new c(aVar);
        this.f3807f = cVar;
        C0832a c0832a2 = c0832a == null ? new C0832a(z8) : c0832a;
        this.f3809h = c0832a2;
        c0832a2.m3898g(this);
        this.f3803b = c0591f == null ? new C0591f() : c0591f;
        this.f3802a = c0593h == null ? new C0593h() : c0593h;
        this.f3805d = bVar == null ? new b(executorServiceC4708a, executorServiceC4708a2, executorServiceC4708a3, executorServiceC4708a4, this) : bVar;
        this.f3808g = aVar2 == null ? new a(cVar) : aVar2;
        this.f3806e = c0597l == null ? new C0597l() : c0597l;
        interfaceC4661i.mo18611c(this);
    }
}
