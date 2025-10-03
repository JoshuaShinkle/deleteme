package com.android.billingclient.api;

import java.util.ArrayList;
import java.util.List;

@Deprecated
/* renamed from: com.android.billingclient.api.n */
/* loaded from: classes.dex */
public class C0786n {

    /* renamed from: a */
    public String f3627a;

    /* renamed from: b */
    public List f3628b;

    /* renamed from: com.android.billingclient.api.n$a */
    public static class a {

        /* renamed from: a */
        public String f3629a;

        /* renamed from: b */
        public List f3630b;

        public /* synthetic */ a(C0795r0 c0795r0) {
        }

        /* renamed from: a */
        public C0786n m3778a() {
            String str = this.f3629a;
            if (str == null) {
                throw new IllegalArgumentException("SKU type must be set");
            }
            if (this.f3630b == null) {
                throw new IllegalArgumentException("SKU list must be set");
            }
            C0786n c0786n = new C0786n();
            c0786n.f3627a = str;
            c0786n.f3628b = this.f3630b;
            return c0786n;
        }

        /* renamed from: b */
        public a m3779b(List<String> list) {
            this.f3630b = new ArrayList(list);
            return this;
        }

        /* renamed from: c */
        public a m3780c(String str) {
            this.f3629a = str;
            return this;
        }
    }

    /* renamed from: c */
    public static a m3773c() {
        return new a(null);
    }

    /* renamed from: a */
    public String m3776a() {
        return this.f3627a;
    }

    /* renamed from: b */
    public List<String> m3777b() {
        return this.f3628b;
    }
}
