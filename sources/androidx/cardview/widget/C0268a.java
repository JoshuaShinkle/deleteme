package androidx.cardview.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.view.View;

/* renamed from: androidx.cardview.widget.a */
/* loaded from: classes.dex */
public class C0268a implements InterfaceC0270c {
    @Override // androidx.cardview.widget.InterfaceC0270c
    /* renamed from: a */
    public void mo1104a(InterfaceC0269b interfaceC0269b, Context context, ColorStateList colorStateList, float f9, float f10, float f11) {
        interfaceC0269b.mo1099a(new C0271d(colorStateList, f9));
        View viewMo1103e = interfaceC0269b.mo1103e();
        viewMo1103e.setClipToOutline(true);
        viewMo1103e.setElevation(f10);
        mo1118o(interfaceC0269b, f11);
    }

    @Override // androidx.cardview.widget.InterfaceC0270c
    /* renamed from: b */
    public void mo1105b(InterfaceC0269b interfaceC0269b, float f9) {
        m1119p(interfaceC0269b).m1127h(f9);
    }

    @Override // androidx.cardview.widget.InterfaceC0270c
    /* renamed from: c */
    public float mo1106c(InterfaceC0269b interfaceC0269b) {
        return interfaceC0269b.mo1103e().getElevation();
    }

    @Override // androidx.cardview.widget.InterfaceC0270c
    /* renamed from: d */
    public float mo1107d(InterfaceC0269b interfaceC0269b) {
        return m1119p(interfaceC0269b).m1123d();
    }

    @Override // androidx.cardview.widget.InterfaceC0270c
    /* renamed from: e */
    public void mo1108e(InterfaceC0269b interfaceC0269b) {
        mo1118o(interfaceC0269b, mo1110g(interfaceC0269b));
    }

    @Override // androidx.cardview.widget.InterfaceC0270c
    /* renamed from: f */
    public void mo1109f(InterfaceC0269b interfaceC0269b, float f9) {
        interfaceC0269b.mo1103e().setElevation(f9);
    }

    @Override // androidx.cardview.widget.InterfaceC0270c
    /* renamed from: g */
    public float mo1110g(InterfaceC0269b interfaceC0269b) {
        return m1119p(interfaceC0269b).m1122c();
    }

    @Override // androidx.cardview.widget.InterfaceC0270c
    /* renamed from: h */
    public ColorStateList mo1111h(InterfaceC0269b interfaceC0269b) {
        return m1119p(interfaceC0269b).m1121b();
    }

    @Override // androidx.cardview.widget.InterfaceC0270c
    /* renamed from: i */
    public void mo1112i(InterfaceC0269b interfaceC0269b) {
        if (!interfaceC0269b.mo1101c()) {
            interfaceC0269b.setShadowPadding(0, 0, 0, 0);
            return;
        }
        float fMo1110g = mo1110g(interfaceC0269b);
        float fMo1107d = mo1107d(interfaceC0269b);
        int iCeil = (int) Math.ceil(C0272e.m1129a(fMo1110g, fMo1107d, interfaceC0269b.mo1100b()));
        int iCeil2 = (int) Math.ceil(C0272e.m1130b(fMo1110g, fMo1107d, interfaceC0269b.mo1100b()));
        interfaceC0269b.setShadowPadding(iCeil, iCeil2, iCeil, iCeil2);
    }

    @Override // androidx.cardview.widget.InterfaceC0270c
    /* renamed from: j */
    public void mo1113j() {
    }

    @Override // androidx.cardview.widget.InterfaceC0270c
    /* renamed from: k */
    public float mo1114k(InterfaceC0269b interfaceC0269b) {
        return mo1107d(interfaceC0269b) * 2.0f;
    }

    @Override // androidx.cardview.widget.InterfaceC0270c
    /* renamed from: l */
    public float mo1115l(InterfaceC0269b interfaceC0269b) {
        return mo1107d(interfaceC0269b) * 2.0f;
    }

    @Override // androidx.cardview.widget.InterfaceC0270c
    /* renamed from: m */
    public void mo1116m(InterfaceC0269b interfaceC0269b) {
        mo1118o(interfaceC0269b, mo1110g(interfaceC0269b));
    }

    @Override // androidx.cardview.widget.InterfaceC0270c
    /* renamed from: n */
    public void mo1117n(InterfaceC0269b interfaceC0269b, ColorStateList colorStateList) {
        m1119p(interfaceC0269b).m1125f(colorStateList);
    }

    @Override // androidx.cardview.widget.InterfaceC0270c
    /* renamed from: o */
    public void mo1118o(InterfaceC0269b interfaceC0269b, float f9) {
        m1119p(interfaceC0269b).m1126g(f9, interfaceC0269b.mo1101c(), interfaceC0269b.mo1100b());
        mo1112i(interfaceC0269b);
    }

    /* renamed from: p */
    public final C0271d m1119p(InterfaceC0269b interfaceC0269b) {
        return (C0271d) interfaceC0269b.mo1102d();
    }
}
