package com.android.billingclient.api;

/* renamed from: com.android.billingclient.api.j0 */
/* loaded from: classes.dex */
public final class C0779j0 {

    /* renamed from: a */
    public boolean f3622a;

    public /* synthetic */ C0779j0(C0777i0 c0777i0) {
    }

    /* renamed from: a */
    public final C0779j0 m3764a() {
        this.f3622a = true;
        return this;
    }

    /* renamed from: b */
    public final C0783l0 m3765b() {
        if (this.f3622a) {
            return new C0783l0(true, false, null);
        }
        throw new IllegalArgumentException("Pending purchases for one-time products must be supported.");
    }
}
