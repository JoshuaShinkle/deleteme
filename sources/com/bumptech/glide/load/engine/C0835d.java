package com.bumptech.glide.load.engine;

import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DecodeJob;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import p012b1.AbstractC0588c;
import p012b1.InterfaceC0595j;
import p022c1.InterfaceC0705b;
import p043d1.InterfaceC4653a;
import p073g1.InterfaceC4820n;
import p093i1.C5043b;
import p207u0.C6357g;
import p243y0.C6592e;
import p243y0.InterfaceC6588a;
import p243y0.InterfaceC6589b;
import p243y0.InterfaceC6594g;
import p243y0.InterfaceC6595h;

/* renamed from: com.bumptech.glide.load.engine.d */
/* loaded from: classes.dex */
public final class C0835d<Transcode> {

    /* renamed from: a */
    public final List<InterfaceC4820n.a<?>> f3778a = new ArrayList();

    /* renamed from: b */
    public final List<InterfaceC6589b> f3779b = new ArrayList();

    /* renamed from: c */
    public C6357g f3780c;

    /* renamed from: d */
    public Object f3781d;

    /* renamed from: e */
    public int f3782e;

    /* renamed from: f */
    public int f3783f;

    /* renamed from: g */
    public Class<?> f3784g;

    /* renamed from: h */
    public DecodeJob.InterfaceC0829e f3785h;

    /* renamed from: i */
    public C6592e f3786i;

    /* renamed from: j */
    public Map<Class<?>, InterfaceC6595h<?>> f3787j;

    /* renamed from: k */
    public Class<Transcode> f3788k;

    /* renamed from: l */
    public boolean f3789l;

    /* renamed from: m */
    public boolean f3790m;

    /* renamed from: n */
    public InterfaceC6589b f3791n;

    /* renamed from: o */
    public Priority f3792o;

    /* renamed from: p */
    public AbstractC0588c f3793p;

    /* renamed from: q */
    public boolean f3794q;

    /* renamed from: r */
    public boolean f3795r;

    /* renamed from: a */
    public void m3904a() {
        this.f3780c = null;
        this.f3781d = null;
        this.f3791n = null;
        this.f3784g = null;
        this.f3788k = null;
        this.f3786i = null;
        this.f3792o = null;
        this.f3787j = null;
        this.f3793p = null;
        this.f3778a.clear();
        this.f3789l = false;
        this.f3779b.clear();
        this.f3790m = false;
    }

    /* renamed from: b */
    public InterfaceC0705b m3905b() {
        return this.f3780c.m24406b();
    }

    /* renamed from: c */
    public List<InterfaceC6589b> m3906c() {
        if (!this.f3790m) {
            this.f3790m = true;
            this.f3779b.clear();
            List<InterfaceC4820n.a<?>> listM3910g = m3910g();
            int size = listM3910g.size();
            for (int i9 = 0; i9 < size; i9++) {
                InterfaceC4820n.a<?> aVar = listM3910g.get(i9);
                if (!this.f3779b.contains(aVar.f16776a)) {
                    this.f3779b.add(aVar.f16776a);
                }
                for (int i10 = 0; i10 < aVar.f16777b.size(); i10++) {
                    if (!this.f3779b.contains(aVar.f16777b.get(i10))) {
                        this.f3779b.add(aVar.f16777b.get(i10));
                    }
                }
            }
        }
        return this.f3779b;
    }

    /* renamed from: d */
    public InterfaceC4653a m3907d() {
        return this.f3785h.mo3875a();
    }

    /* renamed from: e */
    public AbstractC0588c m3908e() {
        return this.f3793p;
    }

    /* renamed from: f */
    public int m3909f() {
        return this.f3783f;
    }

    /* renamed from: g */
    public List<InterfaceC4820n.a<?>> m3910g() {
        if (!this.f3789l) {
            this.f3789l = true;
            this.f3778a.clear();
            List listM3812i = this.f3780c.m24412h().m3812i(this.f3781d);
            int size = listM3812i.size();
            for (int i9 = 0; i9 < size; i9++) {
                InterfaceC4820n.a<?> aVarMo3827b = ((InterfaceC4820n) listM3812i.get(i9)).mo3827b(this.f3781d, this.f3782e, this.f3783f, this.f3786i);
                if (aVarMo3827b != null) {
                    this.f3778a.add(aVarMo3827b);
                }
            }
        }
        return this.f3778a;
    }

    /* renamed from: h */
    public <Data> C0840i<Data, ?, Transcode> m3911h(Class<Data> cls) {
        return this.f3780c.m24412h().m3811h(cls, this.f3784g, this.f3788k);
    }

    /* renamed from: i */
    public List<InterfaceC4820n<File, ?>> m3912i(File file) {
        return this.f3780c.m24412h().m3812i(file);
    }

    /* renamed from: j */
    public C6592e m3913j() {
        return this.f3786i;
    }

    /* renamed from: k */
    public Priority m3914k() {
        return this.f3792o;
    }

    /* renamed from: l */
    public List<Class<?>> m3915l() {
        return this.f3780c.m24412h().m3813j(this.f3781d.getClass(), this.f3784g, this.f3788k);
    }

    /* renamed from: m */
    public <Z> InterfaceC6594g<Z> m3916m(InterfaceC0595j<Z> interfaceC0595j) {
        return this.f3780c.m24412h().m3814k(interfaceC0595j);
    }

    /* renamed from: n */
    public InterfaceC6589b m3917n() {
        return this.f3791n;
    }

    /* renamed from: o */
    public <X> InterfaceC6588a<X> m3918o(X x8) {
        return this.f3780c.m24412h().m3816m(x8);
    }

    /* renamed from: p */
    public Class<?> m3919p() {
        return this.f3788k;
    }

    /* renamed from: q */
    public <Z> InterfaceC6595h<Z> m3920q(Class<Z> cls) {
        InterfaceC6595h<Z> interfaceC6595h = (InterfaceC6595h) this.f3787j.get(cls);
        if (interfaceC6595h == null) {
            Iterator<Map.Entry<Class<?>, InterfaceC6595h<?>>> it = this.f3787j.entrySet().iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                Map.Entry<Class<?>, InterfaceC6595h<?>> next = it.next();
                if (next.getKey().isAssignableFrom(cls)) {
                    interfaceC6595h = (InterfaceC6595h) next.getValue();
                    break;
                }
            }
        }
        if (interfaceC6595h != null) {
            return interfaceC6595h;
        }
        if (!this.f3787j.isEmpty() || !this.f3794q) {
            return C5043b.m19695c();
        }
        throw new IllegalArgumentException("Missing transformation for " + cls + ". If you wish to ignore unknown resource types, use the optional transformation methods.");
    }

    /* renamed from: r */
    public int m3921r() {
        return this.f3782e;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: s */
    public boolean m3922s(Class<?> cls) {
        return m3911h(cls) != null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: t */
    public <R> void m3923t(C6357g c6357g, Object obj, InterfaceC6589b interfaceC6589b, int i9, int i10, AbstractC0588c abstractC0588c, Class<?> cls, Class<R> cls2, Priority priority, C6592e c6592e, Map<Class<?>, InterfaceC6595h<?>> map, boolean z8, boolean z9, DecodeJob.InterfaceC0829e interfaceC0829e) {
        this.f3780c = c6357g;
        this.f3781d = obj;
        this.f3791n = interfaceC6589b;
        this.f3782e = i9;
        this.f3783f = i10;
        this.f3793p = abstractC0588c;
        this.f3784g = cls;
        this.f3785h = interfaceC0829e;
        this.f3788k = cls2;
        this.f3792o = priority;
        this.f3786i = c6592e;
        this.f3787j = map;
        this.f3794q = z8;
        this.f3795r = z9;
    }

    /* renamed from: u */
    public boolean m3924u(InterfaceC0595j<?> interfaceC0595j) {
        return this.f3780c.m24412h().m3817n(interfaceC0595j);
    }

    /* renamed from: v */
    public boolean m3925v() {
        return this.f3795r;
    }

    /* renamed from: w */
    public boolean m3926w(InterfaceC6589b interfaceC6589b) {
        List<InterfaceC4820n.a<?>> listM3910g = m3910g();
        int size = listM3910g.size();
        for (int i9 = 0; i9 < size; i9++) {
            if (listM3910g.get(i9).f16776a.equals(interfaceC6589b)) {
                return true;
            }
        }
        return false;
    }
}
