package com.bumptech.glide.load.engine;

import android.util.Log;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.InterfaceC0834c;
import java.util.Collections;
import java.util.List;
import p012b1.AbstractC0588c;
import p012b1.C0586a;
import p012b1.C0587b;
import p073g1.InterfaceC4820n;
import p226w1.C6512e;
import p243y0.InterfaceC6588a;
import p243y0.InterfaceC6589b;
import p252z0.InterfaceC6805d;

/* renamed from: com.bumptech.glide.load.engine.k */
/* loaded from: classes.dex */
public class C0842k implements InterfaceC0834c, InterfaceC6805d.a<Object>, InterfaceC0834c.a {

    /* renamed from: b */
    public final C0835d<?> f3871b;

    /* renamed from: c */
    public final InterfaceC0834c.a f3872c;

    /* renamed from: d */
    public int f3873d;

    /* renamed from: e */
    public C0833b f3874e;

    /* renamed from: f */
    public Object f3875f;

    /* renamed from: g */
    public volatile InterfaceC4820n.a<?> f3876g;

    /* renamed from: h */
    public C0586a f3877h;

    public C0842k(C0835d<?> c0835d, InterfaceC0834c.a aVar) {
        this.f3871b = c0835d;
        this.f3872c = aVar;
    }

    @Override // com.bumptech.glide.load.engine.InterfaceC0834c
    /* renamed from: a */
    public boolean mo3900a() {
        Object obj = this.f3875f;
        if (obj != null) {
            this.f3875f = null;
            m3966g(obj);
        }
        C0833b c0833b = this.f3874e;
        if (c0833b != null && c0833b.mo3900a()) {
            return true;
        }
        this.f3874e = null;
        this.f3876g = null;
        boolean z8 = false;
        while (!z8 && m3967h()) {
            List<InterfaceC4820n.a<?>> listM3910g = this.f3871b.m3910g();
            int i9 = this.f3873d;
            this.f3873d = i9 + 1;
            this.f3876g = listM3910g.get(i9);
            if (this.f3876g != null && (this.f3871b.m3908e().mo3269c(this.f3876g.f16778c.mo58e()) || this.f3871b.m3922s(this.f3876g.f16778c.mo56a()))) {
                this.f3876g.f16778c.mo59g(this.f3871b.m3914k(), this);
                z8 = true;
            }
        }
        return z8;
    }

    @Override // com.bumptech.glide.load.engine.InterfaceC0834c.a
    /* renamed from: b */
    public void mo3843b(InterfaceC6589b interfaceC6589b, Object obj, InterfaceC6805d<?> interfaceC6805d, DataSource dataSource, InterfaceC6589b interfaceC6589b2) {
        this.f3872c.mo3843b(interfaceC6589b, obj, interfaceC6805d, this.f3876g.f16778c.mo58e(), interfaceC6589b);
    }

    @Override // p252z0.InterfaceC6805d.a
    /* renamed from: c */
    public void mo3902c(Exception exc) {
        this.f3872c.mo3846e(this.f3877h, exc, this.f3876g.f16778c, this.f3876g.f16778c.mo58e());
    }

    @Override // com.bumptech.glide.load.engine.InterfaceC0834c
    public void cancel() {
        InterfaceC4820n.a<?> aVar = this.f3876g;
        if (aVar != null) {
            aVar.f16778c.cancel();
        }
    }

    @Override // com.bumptech.glide.load.engine.InterfaceC0834c.a
    /* renamed from: d */
    public void mo3845d() {
        throw new UnsupportedOperationException();
    }

    @Override // com.bumptech.glide.load.engine.InterfaceC0834c.a
    /* renamed from: e */
    public void mo3846e(InterfaceC6589b interfaceC6589b, Exception exc, InterfaceC6805d<?> interfaceC6805d, DataSource dataSource) {
        this.f3872c.mo3846e(interfaceC6589b, exc, interfaceC6805d, this.f3876g.f16778c.mo58e());
    }

    @Override // p252z0.InterfaceC6805d.a
    /* renamed from: f */
    public void mo3903f(Object obj) {
        AbstractC0588c abstractC0588cM3908e = this.f3871b.m3908e();
        if (obj == null || !abstractC0588cM3908e.mo3269c(this.f3876g.f16778c.mo58e())) {
            this.f3872c.mo3843b(this.f3876g.f16776a, obj, this.f3876g.f16778c, this.f3876g.f16778c.mo58e(), this.f3877h);
        } else {
            this.f3875f = obj;
            this.f3872c.mo3845d();
        }
    }

    /* renamed from: g */
    public final void m3966g(Object obj) {
        long jM24923b = C6512e.m24923b();
        try {
            InterfaceC6588a<X> interfaceC6588aM3918o = this.f3871b.m3918o(obj);
            C0587b c0587b = new C0587b(interfaceC6588aM3918o, obj, this.f3871b.m3913j());
            this.f3877h = new C0586a(this.f3876g.f16776a, this.f3871b.m3917n());
            this.f3871b.m3907d().mo18599a(this.f3877h, c0587b);
            if (Log.isLoggable("SourceGenerator", 2)) {
                Log.v("SourceGenerator", "Finished encoding source to cache, key: " + this.f3877h + ", data: " + obj + ", encoder: " + interfaceC6588aM3918o + ", duration: " + C6512e.m24922a(jM24923b));
            }
            this.f3876g.f16778c.mo57b();
            this.f3874e = new C0833b(Collections.singletonList(this.f3876g.f16776a), this.f3871b, this);
        } catch (Throwable th) {
            this.f3876g.f16778c.mo57b();
            throw th;
        }
    }

    /* renamed from: h */
    public final boolean m3967h() {
        return this.f3873d < this.f3871b.m3910g().size();
    }
}
