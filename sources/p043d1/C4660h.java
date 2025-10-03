package p043d1;

import android.annotation.SuppressLint;
import p012b1.InterfaceC0595j;
import p043d1.InterfaceC4661i;
import p226w1.C6513f;
import p243y0.InterfaceC6589b;

/* renamed from: d1.h */
/* loaded from: classes.dex */
public class C4660h extends C6513f<InterfaceC6589b, InterfaceC0595j<?>> implements InterfaceC4661i {

    /* renamed from: e */
    public InterfaceC4661i.a f16307e;

    public C4660h(long j9) {
        super(j9);
    }

    @Override // p043d1.InterfaceC4661i
    @SuppressLint({"InlinedApi"})
    /* renamed from: a */
    public void mo18610a(int i9) {
        if (i9 >= 40) {
            m24924b();
        } else if (i9 >= 20 || i9 == 15) {
            m24930m(m24927h() / 2);
        }
    }

    @Override // p043d1.InterfaceC4661i
    /* renamed from: c */
    public void mo18611c(InterfaceC4661i.a aVar) {
        this.f16307e = aVar;
    }

    @Override // p043d1.InterfaceC4661i
    /* renamed from: d */
    public /* bridge */ /* synthetic */ InterfaceC0595j mo18612d(InterfaceC6589b interfaceC6589b, InterfaceC0595j interfaceC0595j) {
        return (InterfaceC0595j) super.m24928k(interfaceC6589b, interfaceC0595j);
    }

    @Override // p043d1.InterfaceC4661i
    /* renamed from: e */
    public /* bridge */ /* synthetic */ InterfaceC0595j mo18613e(InterfaceC6589b interfaceC6589b) {
        return (InterfaceC0595j) super.m24929l(interfaceC6589b);
    }

    @Override // p226w1.C6513f
    /* renamed from: n, reason: merged with bridge method [inline-methods] */
    public int mo18614i(InterfaceC0595j<?> interfaceC0595j) {
        return interfaceC0595j == null ? super.mo18614i(null) : interfaceC0595j.mo3282c();
    }

    @Override // p226w1.C6513f
    /* renamed from: o, reason: merged with bridge method [inline-methods] */
    public void mo18615j(InterfaceC6589b interfaceC6589b, InterfaceC0595j<?> interfaceC0595j) {
        InterfaceC4661i.a aVar = this.f16307e;
        if (aVar == null || interfaceC0595j == null) {
            return;
        }
        aVar.mo3931c(interfaceC0595j);
    }
}
