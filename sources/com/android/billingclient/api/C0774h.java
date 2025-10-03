package com.android.billingclient.api;

import com.google.android.gms.internal.play_billing.zzb;

/* renamed from: com.android.billingclient.api.h */
/* loaded from: classes.dex */
public final class C0774h {

    /* renamed from: a */
    public int f3616a;

    /* renamed from: b */
    public String f3617b;

    /* renamed from: com.android.billingclient.api.h$a */
    public static class a {

        /* renamed from: a */
        public int f3618a;

        /* renamed from: b */
        public String f3619b = "";

        public /* synthetic */ a(C0758b0 c0758b0) {
        }

        /* renamed from: a */
        public C0774h m3761a() {
            C0774h c0774h = new C0774h();
            c0774h.f3616a = this.f3618a;
            c0774h.f3617b = this.f3619b;
            return c0774h;
        }

        /* renamed from: b */
        public a m3762b(String str) {
            this.f3619b = str;
            return this;
        }

        /* renamed from: c */
        public a m3763c(int i9) {
            this.f3618a = i9;
            return this;
        }
    }

    /* renamed from: c */
    public static a m3756c() {
        return new a(null);
    }

    /* renamed from: a */
    public String m3759a() {
        return this.f3617b;
    }

    /* renamed from: b */
    public int m3760b() {
        return this.f3616a;
    }

    public String toString() {
        return "Response Code: " + zzb.zzg(this.f3616a) + ", Debug Message: " + this.f3617b;
    }
}
