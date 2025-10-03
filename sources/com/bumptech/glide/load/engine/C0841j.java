package com.bumptech.glide.load.engine;

import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.InterfaceC0834c;
import java.io.File;
import java.util.List;
import p012b1.C0596k;
import p073g1.InterfaceC4820n;
import p243y0.InterfaceC6589b;
import p252z0.InterfaceC6805d;

/* renamed from: com.bumptech.glide.load.engine.j */
/* loaded from: classes.dex */
public class C0841j implements InterfaceC0834c, InterfaceC6805d.a<Object> {

    /* renamed from: b */
    public final InterfaceC0834c.a f3861b;

    /* renamed from: c */
    public final C0835d<?> f3862c;

    /* renamed from: d */
    public int f3863d;

    /* renamed from: e */
    public int f3864e = -1;

    /* renamed from: f */
    public InterfaceC6589b f3865f;

    /* renamed from: g */
    public List<InterfaceC4820n<File, ?>> f3866g;

    /* renamed from: h */
    public int f3867h;

    /* renamed from: i */
    public volatile InterfaceC4820n.a<?> f3868i;

    /* renamed from: j */
    public File f3869j;

    /* renamed from: k */
    public C0596k f3870k;

    public C0841j(C0835d<?> c0835d, InterfaceC0834c.a aVar) {
        this.f3862c = c0835d;
        this.f3861b = aVar;
    }

    @Override // com.bumptech.glide.load.engine.InterfaceC0834c
    /* renamed from: a */
    public boolean mo3900a() {
        List<InterfaceC6589b> listM3906c = this.f3862c.m3906c();
        boolean z8 = false;
        if (listM3906c.isEmpty()) {
            return false;
        }
        List<Class<?>> listM3915l = this.f3862c.m3915l();
        if (listM3915l.isEmpty() && File.class.equals(this.f3862c.m3919p())) {
            return false;
        }
        while (true) {
            if (this.f3866g != null && m3965b()) {
                this.f3868i = null;
                while (!z8 && m3965b()) {
                    List<InterfaceC4820n<File, ?>> list = this.f3866g;
                    int i9 = this.f3867h;
                    this.f3867h = i9 + 1;
                    this.f3868i = list.get(i9).mo3827b(this.f3869j, this.f3862c.m3921r(), this.f3862c.m3909f(), this.f3862c.m3913j());
                    if (this.f3868i != null && this.f3862c.m3922s(this.f3868i.f16778c.mo56a())) {
                        this.f3868i.f16778c.mo59g(this.f3862c.m3914k(), this);
                        z8 = true;
                    }
                }
                return z8;
            }
            int i10 = this.f3864e + 1;
            this.f3864e = i10;
            if (i10 >= listM3915l.size()) {
                int i11 = this.f3863d + 1;
                this.f3863d = i11;
                if (i11 >= listM3906c.size()) {
                    return false;
                }
                this.f3864e = 0;
            }
            InterfaceC6589b interfaceC6589b = listM3906c.get(this.f3863d);
            Class<?> cls = listM3915l.get(this.f3864e);
            this.f3870k = new C0596k(this.f3862c.m3905b(), interfaceC6589b, this.f3862c.m3917n(), this.f3862c.m3921r(), this.f3862c.m3909f(), this.f3862c.m3920q(cls), cls, this.f3862c.m3913j());
            File fileMo18600b = this.f3862c.m3907d().mo18600b(this.f3870k);
            this.f3869j = fileMo18600b;
            if (fileMo18600b != null) {
                this.f3865f = interfaceC6589b;
                this.f3866g = this.f3862c.m3912i(fileMo18600b);
                this.f3867h = 0;
            }
        }
    }

    /* renamed from: b */
    public final boolean m3965b() {
        return this.f3867h < this.f3866g.size();
    }

    @Override // p252z0.InterfaceC6805d.a
    /* renamed from: c */
    public void mo3902c(Exception exc) {
        this.f3861b.mo3846e(this.f3870k, exc, this.f3868i.f16778c, DataSource.RESOURCE_DISK_CACHE);
    }

    @Override // com.bumptech.glide.load.engine.InterfaceC0834c
    public void cancel() {
        InterfaceC4820n.a<?> aVar = this.f3868i;
        if (aVar != null) {
            aVar.f16778c.cancel();
        }
    }

    @Override // p252z0.InterfaceC6805d.a
    /* renamed from: f */
    public void mo3903f(Object obj) {
        this.f3861b.mo3843b(this.f3865f, obj, this.f3868i.f16778c, DataSource.RESOURCE_DISK_CACHE, this.f3870k);
    }
}
