package com.bumptech.glide.load.engine;

import android.os.Looper;
import p012b1.InterfaceC0595j;
import p226w1.C6516i;
import p243y0.InterfaceC6589b;

/* renamed from: com.bumptech.glide.load.engine.h */
/* loaded from: classes.dex */
public class C0839h<Z> implements InterfaceC0595j<Z> {

    /* renamed from: b */
    public final boolean f3850b;

    /* renamed from: c */
    public final boolean f3851c;

    /* renamed from: d */
    public a f3852d;

    /* renamed from: e */
    public InterfaceC6589b f3853e;

    /* renamed from: f */
    public int f3854f;

    /* renamed from: g */
    public boolean f3855g;

    /* renamed from: h */
    public final InterfaceC0595j<Z> f3856h;

    /* renamed from: com.bumptech.glide.load.engine.h$a */
    public interface a {
        /* renamed from: d */
        void mo3932d(InterfaceC6589b interfaceC6589b, C0839h<?> c0839h);
    }

    public C0839h(InterfaceC0595j<Z> interfaceC0595j, boolean z8, boolean z9) {
        this.f3856h = (InterfaceC0595j) C6516i.m24938d(interfaceC0595j);
        this.f3850b = z8;
        this.f3851c = z9;
    }

    /* renamed from: a */
    public void m3958a() {
        if (this.f3855g) {
            throw new IllegalStateException("Cannot acquire a recycled resource");
        }
        if (!Looper.getMainLooper().equals(Looper.myLooper())) {
            throw new IllegalThreadStateException("Must call acquire on the main thread");
        }
        this.f3854f++;
    }

    @Override // p012b1.InterfaceC0595j
    /* renamed from: b */
    public void mo3281b() {
        if (this.f3854f > 0) {
            throw new IllegalStateException("Cannot recycle a resource while it is still acquired");
        }
        if (this.f3855g) {
            throw new IllegalStateException("Cannot recycle a resource that has already been recycled");
        }
        this.f3855g = true;
        if (this.f3851c) {
            this.f3856h.mo3281b();
        }
    }

    @Override // p012b1.InterfaceC0595j
    /* renamed from: c */
    public int mo3282c() {
        return this.f3856h.mo3282c();
    }

    @Override // p012b1.InterfaceC0595j
    /* renamed from: d */
    public Class<Z> mo3283d() {
        return this.f3856h.mo3283d();
    }

    /* renamed from: e */
    public InterfaceC0595j<Z> m3959e() {
        return this.f3856h;
    }

    /* renamed from: f */
    public boolean m3960f() {
        return this.f3850b;
    }

    /* renamed from: g */
    public void m3961g() {
        if (this.f3854f <= 0) {
            throw new IllegalStateException("Cannot release a recycled or not yet acquired resource");
        }
        if (!Looper.getMainLooper().equals(Looper.myLooper())) {
            throw new IllegalThreadStateException("Must call release on the main thread");
        }
        int i9 = this.f3854f - 1;
        this.f3854f = i9;
        if (i9 == 0) {
            this.f3852d.mo3932d(this.f3853e, this);
        }
    }

    @Override // p012b1.InterfaceC0595j
    public Z get() {
        return this.f3856h.get();
    }

    /* renamed from: h */
    public void m3962h(InterfaceC6589b interfaceC6589b, a aVar) {
        this.f3853e = interfaceC6589b;
        this.f3852d = aVar;
    }

    public String toString() {
        return "EngineResource{isCacheable=" + this.f3850b + ", listener=" + this.f3852d + ", key=" + this.f3853e + ", acquired=" + this.f3854f + ", isRecycled=" + this.f3855g + ", resource=" + this.f3856h + '}';
    }
}
