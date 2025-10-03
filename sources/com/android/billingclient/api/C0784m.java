package com.android.billingclient.api;

/* renamed from: com.android.billingclient.api.m */
/* loaded from: classes.dex */
public final class C0784m {

    /* renamed from: a */
    public final String f3623a;

    /* renamed from: com.android.billingclient.api.m$a */
    public static class a {

        /* renamed from: a */
        public String f3624a;

        public /* synthetic */ a(C0791p0 c0791p0) {
        }

        /* renamed from: a */
        public C0784m m3769a() {
            if (this.f3624a != null) {
                return new C0784m(this, null);
            }
            throw new IllegalArgumentException("Product type must be set");
        }

        /* renamed from: b */
        public a m3770b(String str) {
            this.f3624a = str;
            return this;
        }
    }

    public /* synthetic */ C0784m(a aVar, C0793q0 c0793q0) {
        this.f3623a = aVar.f3624a;
    }

    /* renamed from: a */
    public static a m3766a() {
        return new a(null);
    }

    /* renamed from: b */
    public final String m3767b() {
        return this.f3623a;
    }
}
