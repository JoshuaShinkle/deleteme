package com.android.billingclient.api;

import android.content.Context;
import android.content.IntentFilter;

/* renamed from: com.android.billingclient.api.u0 */
/* loaded from: classes.dex */
public final class C0801u0 {

    /* renamed from: a */
    public final Context f3645a;

    /* renamed from: b */
    public final C0799t0 f3646b;

    /* JADX WARN: Multi-variable type inference failed */
    public C0801u0(Context context, InterfaceC0773g0 interfaceC0773g0, InterfaceC0755a0 interfaceC0755a0) {
        this.f3645a = context;
        this.f3646b = new C0799t0(this, null, interfaceC0755a0, 0 == true ? 1 : 0);
    }

    /* renamed from: b */
    public final InterfaceC0773g0 m3795b() {
        C0799t0.m3785a(this.f3646b);
        return null;
    }

    /* renamed from: c */
    public final InterfaceC0782l m3796c() {
        return this.f3646b.f3637a;
    }

    /* renamed from: d */
    public final void m3797d() {
        this.f3646b.m3788d(this.f3645a);
    }

    /* renamed from: e */
    public final void m3798e() {
        IntentFilter intentFilter = new IntentFilter("com.android.vending.billing.PURCHASES_UPDATED");
        intentFilter.addAction("com.android.vending.billing.ALTERNATIVE_BILLING");
        this.f3646b.m3787c(this.f3645a, intentFilter);
    }

    public C0801u0(Context context, InterfaceC0782l interfaceC0782l, InterfaceC0760c interfaceC0760c, InterfaceC0755a0 interfaceC0755a0) {
        this.f3645a = context;
        this.f3646b = new C0799t0(this, interfaceC0782l, interfaceC0760c, interfaceC0755a0, null);
    }
}
