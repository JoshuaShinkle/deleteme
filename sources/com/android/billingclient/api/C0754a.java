package com.android.billingclient.api;

/* renamed from: com.android.billingclient.api.a */
/* loaded from: classes.dex */
public final class C0754a {

    /* renamed from: a */
    public String f3511a;

    /* renamed from: com.android.billingclient.api.a$a */
    public static final class a {

        /* renamed from: a */
        public String f3512a;

        public /* synthetic */ a(C0790p c0790p) {
        }

        /* renamed from: a */
        public C0754a m3663a() {
            String str = this.f3512a;
            if (str == null) {
                throw new IllegalArgumentException("Purchase token must be set");
            }
            C0754a c0754a = new C0754a(null);
            c0754a.f3511a = str;
            return c0754a;
        }

        /* renamed from: b */
        public a m3664b(String str) {
            this.f3512a = str;
            return this;
        }
    }

    public /* synthetic */ C0754a(C0775h0 c0775h0) {
    }

    /* renamed from: b */
    public static a m3660b() {
        return new a(null);
    }

    /* renamed from: a */
    public String m3662a() {
        return this.f3511a;
    }
}
