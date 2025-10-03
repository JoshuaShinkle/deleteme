package com.android.billingclient.api;

import android.text.TextUtils;
import com.google.android.gms.internal.play_billing.zzu;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.android.billingclient.api.g */
/* loaded from: classes.dex */
public class C0772g {

    /* renamed from: a */
    public boolean f3594a;

    /* renamed from: b */
    public String f3595b;

    /* renamed from: c */
    public String f3596c;

    /* renamed from: d */
    public c f3597d;

    /* renamed from: e */
    public zzu f3598e;

    /* renamed from: f */
    public ArrayList f3599f;

    /* renamed from: g */
    public boolean f3600g;

    /* renamed from: com.android.billingclient.api.g$a */
    public static class a {

        /* renamed from: a */
        public String f3601a;

        /* renamed from: b */
        public String f3602b;

        /* renamed from: c */
        public List f3603c;

        /* renamed from: d */
        public ArrayList f3604d;

        /* renamed from: e */
        public boolean f3605e;

        /* renamed from: f */
        public c.a f3606f;

        public /* synthetic */ a(C0802v c0802v) {
            c.a aVarM3745a = c.m3745a();
            c.a.m3754b(aVarM3745a);
            this.f3606f = aVarM3745a;
        }

        /* renamed from: a */
        public C0772g m3742a() {
            ArrayList arrayList = this.f3604d;
            boolean z8 = true;
            boolean z9 = (arrayList == null || arrayList.isEmpty()) ? false : true;
            List list = this.f3603c;
            boolean z10 = (list == null || list.isEmpty()) ? false : true;
            if (!z9 && !z10) {
                throw new IllegalArgumentException("Details of the products must be provided.");
            }
            if (z9 && z10) {
                throw new IllegalArgumentException("Set SkuDetails or ProductDetailsParams, not both.");
            }
            C0808y c0808y = null;
            if (!z9) {
                b bVar = (b) this.f3603c.get(0);
                for (int i9 = 0; i9 < this.f3603c.size(); i9++) {
                    b bVar2 = (b) this.f3603c.get(i9);
                    if (bVar2 == null) {
                        throw new IllegalArgumentException("ProductDetailsParams cannot be null.");
                    }
                    if (i9 != 0) {
                        bVar2.m3744a();
                        throw null;
                    }
                }
                bVar.m3744a();
                throw null;
            }
            if (this.f3604d.contains(null)) {
                throw new IllegalArgumentException("SKU cannot be null.");
            }
            if (this.f3604d.size() > 1) {
                SkuDetails skuDetails = (SkuDetails) this.f3604d.get(0);
                String strM3653g = skuDetails.m3653g();
                ArrayList arrayList2 = this.f3604d;
                int size = arrayList2.size();
                for (int i10 = 0; i10 < size; i10++) {
                    SkuDetails skuDetails2 = (SkuDetails) arrayList2.get(i10);
                    if (!strM3653g.equals("play_pass_subs") && !skuDetails2.m3653g().equals("play_pass_subs") && !strM3653g.equals(skuDetails2.m3653g())) {
                        throw new IllegalArgumentException("SKUs should have the same type.");
                    }
                }
                String strM3657k = skuDetails.m3657k();
                ArrayList arrayList3 = this.f3604d;
                int size2 = arrayList3.size();
                for (int i11 = 0; i11 < size2; i11++) {
                    SkuDetails skuDetails3 = (SkuDetails) arrayList3.get(i11);
                    if (!strM3653g.equals("play_pass_subs") && !skuDetails3.m3653g().equals("play_pass_subs") && !strM3657k.equals(skuDetails3.m3657k())) {
                        throw new IllegalArgumentException("All SKUs must have the same package name.");
                    }
                }
            }
            C0772g c0772g = new C0772g(c0808y);
            if (!z9 || ((SkuDetails) this.f3604d.get(0)).m3657k().isEmpty()) {
                if (z10) {
                    ((b) this.f3603c.get(0)).m3744a();
                    throw null;
                }
                z8 = false;
            }
            c0772g.f3594a = z8;
            c0772g.f3595b = this.f3601a;
            c0772g.f3596c = this.f3602b;
            c0772g.f3597d = this.f3606f.m3755a();
            ArrayList arrayList4 = this.f3604d;
            c0772g.f3599f = arrayList4 != null ? new ArrayList(arrayList4) : new ArrayList();
            c0772g.f3600g = this.f3605e;
            List list2 = this.f3603c;
            c0772g.f3598e = list2 != null ? zzu.zzj(list2) : zzu.zzk();
            return c0772g;
        }

        @Deprecated
        /* renamed from: b */
        public a m3743b(SkuDetails skuDetails) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(skuDetails);
            this.f3604d = arrayList;
            return this;
        }
    }

    /* renamed from: com.android.billingclient.api.g$b */
    public static final class b {
        /* renamed from: a */
        public final C0778j m3744a() {
            return null;
        }
    }

    /* renamed from: com.android.billingclient.api.g$c */
    public static class c {

        /* renamed from: a */
        public String f3607a;

        /* renamed from: b */
        public String f3608b;

        /* renamed from: c */
        public int f3609c = 0;

        /* renamed from: d */
        public int f3610d = 0;

        /* renamed from: com.android.billingclient.api.g$c$a */
        public static class a {

            /* renamed from: a */
            public String f3611a;

            /* renamed from: b */
            public String f3612b;

            /* renamed from: c */
            public boolean f3613c;

            /* renamed from: d */
            public int f3614d = 0;

            /* renamed from: e */
            public int f3615e = 0;

            public /* synthetic */ a(C0804w c0804w) {
            }

            /* renamed from: b */
            public static /* synthetic */ a m3754b(a aVar) {
                aVar.f3613c = true;
                return aVar;
            }

            /* renamed from: a */
            public c m3755a() {
                C0806x c0806x = null;
                boolean z8 = (TextUtils.isEmpty(this.f3611a) && TextUtils.isEmpty(null)) ? false : true;
                boolean zIsEmpty = true ^ TextUtils.isEmpty(this.f3612b);
                if (z8 && zIsEmpty) {
                    throw new IllegalArgumentException("Please provide Old SKU purchase information(token/id) or original external transaction id, not both.");
                }
                if (!this.f3613c && !z8 && !zIsEmpty) {
                    throw new IllegalArgumentException("Old SKU purchase information(token/id) or original external transaction id must be provided.");
                }
                c cVar = new c(c0806x);
                cVar.f3607a = this.f3611a;
                cVar.f3609c = this.f3614d;
                cVar.f3610d = this.f3615e;
                cVar.f3608b = this.f3612b;
                return cVar;
            }
        }

        public /* synthetic */ c(C0806x c0806x) {
        }

        /* renamed from: a */
        public static a m3745a() {
            return new a(null);
        }

        @Deprecated
        /* renamed from: b */
        public final int m3750b() {
            return this.f3609c;
        }

        /* renamed from: c */
        public final int m3751c() {
            return this.f3610d;
        }

        /* renamed from: d */
        public final String m3752d() {
            return this.f3607a;
        }

        /* renamed from: e */
        public final String m3753e() {
            return this.f3608b;
        }
    }

    public /* synthetic */ C0772g(C0808y c0808y) {
    }

    /* renamed from: a */
    public static a m3724a() {
        return new a(null);
    }

    @Deprecated
    /* renamed from: b */
    public final int m3732b() {
        return this.f3597d.m3750b();
    }

    /* renamed from: c */
    public final int m3733c() {
        return this.f3597d.m3751c();
    }

    /* renamed from: d */
    public final String m3734d() {
        return this.f3595b;
    }

    /* renamed from: e */
    public final String m3735e() {
        return this.f3596c;
    }

    /* renamed from: f */
    public final String m3736f() {
        return this.f3597d.m3752d();
    }

    /* renamed from: g */
    public final String m3737g() {
        return this.f3597d.m3753e();
    }

    /* renamed from: h */
    public final ArrayList m3738h() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.f3599f);
        return arrayList;
    }

    /* renamed from: i */
    public final List m3739i() {
        return this.f3598e;
    }

    /* renamed from: q */
    public final boolean m3740q() {
        return this.f3600g;
    }

    /* renamed from: r */
    public final boolean m3741r() {
        return (this.f3595b == null && this.f3596c == null && this.f3597d.m3753e() == null && this.f3597d.m3750b() == 0 && this.f3597d.m3751c() == 0 && !this.f3594a && !this.f3600g) ? false : true;
    }
}
