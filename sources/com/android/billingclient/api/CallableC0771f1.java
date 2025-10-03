package com.android.billingclient.api;

import com.google.android.gms.internal.play_billing.zzu;
import java.util.concurrent.Callable;

/* renamed from: com.android.billingclient.api.f1 */
/* loaded from: classes.dex */
public final class CallableC0771f1 implements Callable {

    /* renamed from: b */
    public final /* synthetic */ String f3591b;

    /* renamed from: c */
    public final /* synthetic */ InterfaceC0780k f3592c;

    /* renamed from: d */
    public final /* synthetic */ C0766e f3593d;

    public CallableC0771f1(C0766e c0766e, String str, InterfaceC0780k interfaceC0780k) {
        this.f3593d = c0766e;
        this.f3591b = str;
        this.f3592c = interfaceC0780k;
    }

    @Override // java.util.concurrent.Callable
    public final /* bridge */ /* synthetic */ Object call() {
        C0785m0 c0785m0M3706u = C0766e.m3706u(this.f3593d, this.f3591b, 9);
        if (c0785m0M3706u.m3772b() != null) {
            this.f3592c.onQueryPurchasesResponse(c0785m0M3706u.m3771a(), c0785m0M3706u.m3772b());
            return null;
        }
        this.f3592c.onQueryPurchasesResponse(c0785m0M3706u.m3771a(), zzu.zzk());
        return null;
    }
}
