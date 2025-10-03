package com.bumptech.glide.load.engine;

import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.InterfaceC0834c;
import java.io.File;
import java.util.List;
import p012b1.C0586a;
import p073g1.InterfaceC4820n;
import p243y0.InterfaceC6589b;
import p252z0.InterfaceC6805d;

/* renamed from: com.bumptech.glide.load.engine.b */
/* loaded from: classes.dex */
public class C0833b implements InterfaceC0834c, InterfaceC6805d.a<Object> {

    /* renamed from: b */
    public final List<InterfaceC6589b> f3769b;

    /* renamed from: c */
    public final C0835d<?> f3770c;

    /* renamed from: d */
    public final InterfaceC0834c.a f3771d;

    /* renamed from: e */
    public int f3772e;

    /* renamed from: f */
    public InterfaceC6589b f3773f;

    /* renamed from: g */
    public List<InterfaceC4820n<File, ?>> f3774g;

    /* renamed from: h */
    public int f3775h;

    /* renamed from: i */
    public volatile InterfaceC4820n.a<?> f3776i;

    /* renamed from: j */
    public File f3777j;

    public C0833b(C0835d<?> c0835d, InterfaceC0834c.a aVar) {
        this(c0835d.m3906c(), c0835d, aVar);
    }

    @Override // com.bumptech.glide.load.engine.InterfaceC0834c
    /* renamed from: a */
    public boolean mo3900a() {
        while (true) {
            boolean z8 = false;
            if (this.f3774g != null && m3901b()) {
                this.f3776i = null;
                while (!z8 && m3901b()) {
                    List<InterfaceC4820n<File, ?>> list = this.f3774g;
                    int i9 = this.f3775h;
                    this.f3775h = i9 + 1;
                    this.f3776i = list.get(i9).mo3827b(this.f3777j, this.f3770c.m3921r(), this.f3770c.m3909f(), this.f3770c.m3913j());
                    if (this.f3776i != null && this.f3770c.m3922s(this.f3776i.f16778c.mo56a())) {
                        this.f3776i.f16778c.mo59g(this.f3770c.m3914k(), this);
                        z8 = true;
                    }
                }
                return z8;
            }
            int i10 = this.f3772e + 1;
            this.f3772e = i10;
            if (i10 >= this.f3769b.size()) {
                return false;
            }
            InterfaceC6589b interfaceC6589b = this.f3769b.get(this.f3772e);
            File fileMo18600b = this.f3770c.m3907d().mo18600b(new C0586a(interfaceC6589b, this.f3770c.m3917n()));
            this.f3777j = fileMo18600b;
            if (fileMo18600b != null) {
                this.f3773f = interfaceC6589b;
                this.f3774g = this.f3770c.m3912i(fileMo18600b);
                this.f3775h = 0;
            }
        }
    }

    /* renamed from: b */
    public final boolean m3901b() {
        return this.f3775h < this.f3774g.size();
    }

    @Override // p252z0.InterfaceC6805d.a
    /* renamed from: c */
    public void mo3902c(Exception exc) {
        this.f3771d.mo3846e(this.f3773f, exc, this.f3776i.f16778c, DataSource.DATA_DISK_CACHE);
    }

    @Override // com.bumptech.glide.load.engine.InterfaceC0834c
    public void cancel() {
        InterfaceC4820n.a<?> aVar = this.f3776i;
        if (aVar != null) {
            aVar.f16778c.cancel();
        }
    }

    @Override // p252z0.InterfaceC6805d.a
    /* renamed from: f */
    public void mo3903f(Object obj) {
        this.f3771d.mo3843b(this.f3773f, obj, this.f3776i.f16778c, DataSource.DATA_DISK_CACHE, this.f3773f);
    }

    public C0833b(List<InterfaceC6589b> list, C0835d<?> c0835d, InterfaceC0834c.a aVar) {
        this.f3772e = -1;
        this.f3769b = list;
        this.f3770c = c0835d;
        this.f3771d = aVar;
    }
}
