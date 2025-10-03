package com.android.billingclient.api;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.android.billingclient.api.C0772g;
import com.android.billingclient.api.C0774h;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.source.smoothstreaming.SsMediaSource;
import com.google.android.gms.internal.play_billing.zzb;
import com.google.android.gms.internal.play_billing.zze;
import com.google.android.gms.internal.play_billing.zzfa;
import com.google.android.gms.internal.play_billing.zzfb;
import com.google.android.gms.internal.play_billing.zzfe;
import com.google.android.gms.internal.play_billing.zzff;
import com.google.android.gms.internal.play_billing.zzfh;
import com.google.android.gms.internal.play_billing.zzfj;
import com.google.android.gms.internal.play_billing.zzfl;
import com.google.android.gms.internal.play_billing.zzfm;
import com.google.android.gms.internal.play_billing.zzfu;
import com.google.android.gms.internal.play_billing.zzfw;
import com.google.android.gms.internal.play_billing.zzu;
import com.google.android.gms.internal.play_billing.zzz;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.json.JSONException;

/* renamed from: com.android.billingclient.api.e */
/* loaded from: classes.dex */
public class C0766e extends AbstractC0763d {

    /* renamed from: a */
    public volatile int f3560a;

    /* renamed from: b */
    public final String f3561b;

    /* renamed from: c */
    public final Handler f3562c;

    /* renamed from: d */
    public volatile C0801u0 f3563d;

    /* renamed from: e */
    public Context f3564e;

    /* renamed from: f */
    public InterfaceC0755a0 f3565f;

    /* renamed from: g */
    public volatile zze f3566g;

    /* renamed from: h */
    public volatile ServiceConnectionC0800u f3567h;

    /* renamed from: i */
    public boolean f3568i;

    /* renamed from: j */
    public boolean f3569j;

    /* renamed from: k */
    public int f3570k;

    /* renamed from: l */
    public boolean f3571l;

    /* renamed from: m */
    public boolean f3572m;

    /* renamed from: n */
    public boolean f3573n;

    /* renamed from: o */
    public boolean f3574o;

    /* renamed from: p */
    public boolean f3575p;

    /* renamed from: q */
    public boolean f3576q;

    /* renamed from: r */
    public boolean f3577r;

    /* renamed from: s */
    public boolean f3578s;

    /* renamed from: t */
    public boolean f3579t;

    /* renamed from: u */
    public boolean f3580u;

    /* renamed from: v */
    public boolean f3581v;

    /* renamed from: w */
    public boolean f3582w;

    /* renamed from: x */
    public C0783l0 f3583x;

    /* renamed from: y */
    public boolean f3584y;

    /* renamed from: z */
    public ExecutorService f3585z;

    public C0766e(Context context, C0783l0 c0783l0, InterfaceC0782l interfaceC0782l, String str, String str2, InterfaceC0760c interfaceC0760c, InterfaceC0755a0 interfaceC0755a0) {
        this.f3560a = 0;
        this.f3562c = new Handler(Looper.getMainLooper());
        this.f3570k = 0;
        this.f3561b = str;
        m3714j(context, interfaceC0782l, c0783l0, interfaceC0760c, str, null);
    }

    /* renamed from: u */
    public static /* synthetic */ C0785m0 m3706u(C0766e c0766e, String str, int i9) {
        Bundle bundleZzi;
        zzb.zzi("BillingClient", "Querying owned items, item type: ".concat(String.valueOf(str)));
        ArrayList arrayList = new ArrayList();
        boolean z8 = true;
        int i10 = 0;
        Bundle bundleZzc = zzb.zzc(c0766e.f3573n, c0766e.f3581v, true, false, c0766e.f3561b);
        List list = null;
        String string = null;
        while (true) {
            try {
                if (c0766e.f3573n) {
                    bundleZzi = c0766e.f3566g.zzj(z8 != c0766e.f3581v ? 9 : 19, c0766e.f3564e.getPackageName(), str, string, bundleZzc);
                } else {
                    bundleZzi = c0766e.f3566g.zzi(3, c0766e.f3564e.getPackageName(), str, string);
                }
                C0787n0 c0787n0M3784a = C0789o0.m3784a(bundleZzi, "BillingClient", "getPurchase()");
                C0774h c0774hM3781a = c0787n0M3784a.m3781a();
                if (c0774hM3781a != C0761c0.f3536l) {
                    c0766e.f3565f.mo3666b(C0810z.m3799a(c0787n0M3784a.m3782b(), 9, c0774hM3781a));
                    return new C0785m0(c0774hM3781a, list);
                }
                ArrayList<String> stringArrayList = bundleZzi.getStringArrayList("INAPP_PURCHASE_ITEM_LIST");
                ArrayList<String> stringArrayList2 = bundleZzi.getStringArrayList("INAPP_PURCHASE_DATA_LIST");
                ArrayList<String> stringArrayList3 = bundleZzi.getStringArrayList("INAPP_DATA_SIGNATURE_LIST");
                int i11 = i10;
                int i12 = i11;
                while (i11 < stringArrayList2.size()) {
                    String str2 = stringArrayList2.get(i11);
                    String str3 = stringArrayList3.get(i11);
                    zzb.zzi("BillingClient", "Sku is owned: ".concat(String.valueOf(stringArrayList.get(i11))));
                    try {
                        Purchase purchase = new Purchase(str2, str3);
                        if (TextUtils.isEmpty(purchase.m3642c())) {
                            zzb.zzj("BillingClient", "BUG: empty/null token!");
                            i12 = 1;
                        }
                        arrayList.add(purchase);
                        i11++;
                    } catch (JSONException e9) {
                        zzb.zzk("BillingClient", "Got an exception trying to decode the purchase!", e9);
                        InterfaceC0755a0 interfaceC0755a0 = c0766e.f3565f;
                        C0774h c0774h = C0761c0.f3534j;
                        interfaceC0755a0.mo3666b(C0810z.m3799a(51, 9, c0774h));
                        return new C0785m0(c0774h, null);
                    }
                }
                if (i12 != 0) {
                    c0766e.f3565f.mo3666b(C0810z.m3799a(26, 9, C0761c0.f3534j));
                }
                string = bundleZzi.getString("INAPP_CONTINUATION_TOKEN");
                zzb.zzi("BillingClient", "Continuation token: ".concat(String.valueOf(string)));
                if (TextUtils.isEmpty(string)) {
                    return new C0785m0(C0761c0.f3536l, arrayList);
                }
                list = null;
                z8 = true;
                i10 = 0;
            } catch (Exception e10) {
                InterfaceC0755a0 interfaceC0755a02 = c0766e.f3565f;
                C0774h c0774h2 = C0761c0.f3537m;
                interfaceC0755a02.mo3666b(C0810z.m3799a(52, 9, c0774h2));
                zzb.zzk("BillingClient", "Got exception trying to get purchasesm try to reconnect", e10);
                return new C0785m0(c0774h2, null);
            }
        }
    }

    @SuppressLint({"PrivateApi"})
    /* renamed from: y */
    public static String m3707y() {
        try {
            return (String) Class.forName("com.android.billingclient.ktx.BuildConfig").getField("VERSION_NAME").get(null);
        } catch (Exception unused) {
            return "6.0.1";
        }
    }

    /* renamed from: A */
    public final void m3708A(String str, final InterfaceC0780k interfaceC0780k) {
        if (!mo3674d()) {
            InterfaceC0755a0 interfaceC0755a0 = this.f3565f;
            C0774h c0774h = C0761c0.f3537m;
            interfaceC0755a0.mo3666b(C0810z.m3799a(2, 9, c0774h));
            interfaceC0780k.onQueryPurchasesResponse(c0774h, zzu.zzk());
            return;
        }
        if (TextUtils.isEmpty(str)) {
            zzb.zzj("BillingClient", "Please provide a valid product type.");
            InterfaceC0755a0 interfaceC0755a02 = this.f3565f;
            C0774h c0774h2 = C0761c0.f3531g;
            interfaceC0755a02.mo3666b(C0810z.m3799a(50, 9, c0774h2));
            interfaceC0780k.onQueryPurchasesResponse(c0774h2, zzu.zzk());
            return;
        }
        if (m3722z(new CallableC0771f1(this, str, interfaceC0780k), SsMediaSource.DEFAULT_LIVE_PRESENTATION_DELAY_MS, new Runnable() { // from class: com.android.billingclient.api.c1
            @Override // java.lang.Runnable
            public final void run() {
                this.f3551b.m3717s(interfaceC0780k);
            }
        }, m3719v()) == null) {
            C0774h c0774hM3721x = m3721x();
            this.f3565f.mo3666b(C0810z.m3799a(25, 9, c0774hM3721x));
            interfaceC0780k.onQueryPurchasesResponse(c0774hM3721x, zzu.zzk());
        }
    }

    /* renamed from: B */
    public final void m3709B(C0774h c0774h, int i9, int i10) {
        if (c0774h.m3760b() == 0) {
            InterfaceC0755a0 interfaceC0755a0 = this.f3565f;
            zzfe zzfeVarZzv = zzff.zzv();
            zzfeVarZzv.zzj(5);
            zzfu zzfuVarZzv = zzfw.zzv();
            zzfuVarZzv.zzi(i10);
            zzfeVarZzv.zzi((zzfw) zzfuVarZzv.zzc());
            interfaceC0755a0.mo3667c((zzff) zzfeVarZzv.zzc());
            return;
        }
        InterfaceC0755a0 interfaceC0755a02 = this.f3565f;
        zzfa zzfaVarZzv = zzfb.zzv();
        zzfh zzfhVarZzv = zzfj.zzv();
        zzfhVarZzv.zzj(c0774h.m3760b());
        zzfhVarZzv.zzi(c0774h.m3759a());
        zzfhVarZzv.zzk(i9);
        zzfaVarZzv.zzi(zzfhVarZzv);
        zzfaVarZzv.zzk(5);
        zzfu zzfuVarZzv2 = zzfw.zzv();
        zzfuVarZzv2.zzi(i10);
        zzfaVarZzv.zzj((zzfw) zzfuVarZzv2.zzc());
        interfaceC0755a02.mo3666b((zzfb) zzfaVarZzv.zzc());
    }

    /* renamed from: E */
    public final /* synthetic */ Bundle m3710E(int i9, String str, String str2, C0772g c0772g, Bundle bundle) {
        return this.f3566g.zzg(i9, this.f3564e.getPackageName(), str, str2, null, bundle);
    }

    /* renamed from: F */
    public final /* synthetic */ Bundle m3711F(String str, String str2) {
        return this.f3566g.zzf(3, this.f3564e.getPackageName(), str, str2, null);
    }

    /* renamed from: K */
    public final /* synthetic */ Object m3712K(C0754a c0754a, InterfaceC0757b interfaceC0757b) {
        try {
            zze zzeVar = this.f3566g;
            String packageName = this.f3564e.getPackageName();
            String strM3662a = c0754a.m3662a();
            String str = this.f3561b;
            Bundle bundle = new Bundle();
            bundle.putString("playBillingLibraryVersion", str);
            Bundle bundleZzd = zzeVar.zzd(9, packageName, strM3662a, bundle);
            int iZzb = zzb.zzb(bundleZzd, "BillingClient");
            String strZzf = zzb.zzf(bundleZzd, "BillingClient");
            C0774h.a aVarM3756c = C0774h.m3756c();
            aVarM3756c.m3763c(iZzb);
            aVarM3756c.m3762b(strZzf);
            interfaceC0757b.mo3668a(aVarM3756c.m3761a());
            return null;
        } catch (Exception e9) {
            zzb.zzk("BillingClient", "Error acknowledge purchase!", e9);
            InterfaceC0755a0 interfaceC0755a0 = this.f3565f;
            C0774h c0774h = C0761c0.f3537m;
            interfaceC0755a0.mo3666b(C0810z.m3799a(28, 3, c0774h));
            interfaceC0757b.mo3668a(c0774h);
            return null;
        }
    }

    /* renamed from: L */
    public final /* synthetic */ Object m3713L(String str, List list, String str2, InterfaceC0788o interfaceC0788o) {
        String strZzf;
        int i9;
        Bundle bundleZzk;
        ArrayList arrayList = new ArrayList();
        int size = list.size();
        int i10 = 0;
        while (true) {
            if (i10 >= size) {
                strZzf = "";
                i9 = 0;
                break;
            }
            int i11 = i10 + 20;
            ArrayList<String> arrayList2 = new ArrayList<>(list.subList(i10, i11 > size ? size : i11));
            Bundle bundle = new Bundle();
            bundle.putStringArrayList("ITEM_ID_LIST", arrayList2);
            bundle.putString("playBillingLibraryVersion", this.f3561b);
            try {
                if (this.f3574o) {
                    zze zzeVar = this.f3566g;
                    String packageName = this.f3564e.getPackageName();
                    int i12 = this.f3570k;
                    String str3 = this.f3561b;
                    Bundle bundle2 = new Bundle();
                    if (i12 >= 9) {
                        bundle2.putString("playBillingLibraryVersion", str3);
                    }
                    if (i12 >= 9) {
                        bundle2.putBoolean("enablePendingPurchases", true);
                    }
                    bundleZzk = zzeVar.zzl(10, packageName, str, bundle, bundle2);
                } else {
                    bundleZzk = this.f3566g.zzk(3, this.f3564e.getPackageName(), str, bundle);
                }
                strZzf = "Item is unavailable for purchase.";
                if (bundleZzk == null) {
                    zzb.zzj("BillingClient", "querySkuDetailsAsync got null sku details list");
                    this.f3565f.mo3666b(C0810z.m3799a(44, 8, C0761c0.f3523B));
                    break;
                }
                if (bundleZzk.containsKey("DETAILS_LIST")) {
                    ArrayList<String> stringArrayList = bundleZzk.getStringArrayList("DETAILS_LIST");
                    if (stringArrayList == null) {
                        zzb.zzj("BillingClient", "querySkuDetailsAsync got null response list");
                        this.f3565f.mo3666b(C0810z.m3799a(46, 8, C0761c0.f3523B));
                        break;
                    }
                    for (int i13 = 0; i13 < stringArrayList.size(); i13++) {
                        try {
                            SkuDetails skuDetails = new SkuDetails(stringArrayList.get(i13));
                            zzb.zzi("BillingClient", "Got sku details: ".concat(skuDetails.toString()));
                            arrayList.add(skuDetails);
                        } catch (JSONException e9) {
                            zzb.zzk("BillingClient", "Got a JSON exception trying to decode SkuDetails.", e9);
                            strZzf = "Error trying to decode SkuDetails.";
                            this.f3565f.mo3666b(C0810z.m3799a(47, 8, C0761c0.m3669a(6, "Error trying to decode SkuDetails.")));
                            arrayList = null;
                            i9 = 6;
                            C0774h.a aVarM3756c = C0774h.m3756c();
                            aVarM3756c.m3763c(i9);
                            aVarM3756c.m3762b(strZzf);
                            interfaceC0788o.mo3783a(aVarM3756c.m3761a(), arrayList);
                            return null;
                        }
                    }
                    i10 = i11;
                } else {
                    int iZzb = zzb.zzb(bundleZzk, "BillingClient");
                    strZzf = zzb.zzf(bundleZzk, "BillingClient");
                    if (iZzb != 0) {
                        zzb.zzj("BillingClient", "getSkuDetails() failed. Response code: " + iZzb);
                        this.f3565f.mo3666b(C0810z.m3799a(23, 8, C0761c0.m3669a(iZzb, strZzf)));
                        i9 = iZzb;
                    } else {
                        zzb.zzj("BillingClient", "getSkuDetails() returned a bundle with neither an error nor a detail list.");
                        this.f3565f.mo3666b(C0810z.m3799a(45, 8, C0761c0.m3669a(6, strZzf)));
                    }
                }
            } catch (Exception e10) {
                zzb.zzk("BillingClient", "querySkuDetailsAsync got a remote exception (try to reconnect).", e10);
                this.f3565f.mo3666b(C0810z.m3799a(43, 8, C0761c0.f3537m));
                strZzf = "Service connection is disconnected.";
                i9 = -1;
                arrayList = null;
            }
        }
        arrayList = null;
        i9 = 4;
        C0774h.a aVarM3756c2 = C0774h.m3756c();
        aVarM3756c2.m3763c(i9);
        aVarM3756c2.m3762b(strZzf);
        interfaceC0788o.mo3783a(aVarM3756c2.m3761a(), arrayList);
        return null;
    }

    @Override // com.android.billingclient.api.AbstractC0763d
    /* renamed from: a */
    public final void mo3671a(final C0754a c0754a, final InterfaceC0757b interfaceC0757b) {
        if (!mo3674d()) {
            InterfaceC0755a0 interfaceC0755a0 = this.f3565f;
            C0774h c0774h = C0761c0.f3537m;
            interfaceC0755a0.mo3666b(C0810z.m3799a(2, 3, c0774h));
            interfaceC0757b.mo3668a(c0774h);
            return;
        }
        if (TextUtils.isEmpty(c0754a.m3662a())) {
            zzb.zzj("BillingClient", "Please provide a valid purchase token.");
            InterfaceC0755a0 interfaceC0755a02 = this.f3565f;
            C0774h c0774h2 = C0761c0.f3533i;
            interfaceC0755a02.mo3666b(C0810z.m3799a(26, 3, c0774h2));
            interfaceC0757b.mo3668a(c0774h2);
            return;
        }
        if (!this.f3573n) {
            InterfaceC0755a0 interfaceC0755a03 = this.f3565f;
            C0774h c0774h3 = C0761c0.f3526b;
            interfaceC0755a03.mo3666b(C0810z.m3799a(27, 3, c0774h3));
            interfaceC0757b.mo3668a(c0774h3);
            return;
        }
        if (m3722z(new Callable() { // from class: com.android.billingclient.api.x0
            @Override // java.util.concurrent.Callable
            public final Object call() {
                this.f3651b.m3712K(c0754a, interfaceC0757b);
                return null;
            }
        }, SsMediaSource.DEFAULT_LIVE_PRESENTATION_DELAY_MS, new Runnable() { // from class: com.android.billingclient.api.y0
            @Override // java.lang.Runnable
            public final void run() {
                this.f3654b.m3715q(interfaceC0757b);
            }
        }, m3719v()) == null) {
            C0774h c0774hM3721x = m3721x();
            this.f3565f.mo3666b(C0810z.m3799a(25, 3, c0774hM3721x));
            interfaceC0757b.mo3668a(c0774hM3721x);
        }
    }

    @Override // com.android.billingclient.api.AbstractC0763d
    /* renamed from: b */
    public final void mo3672b() {
        this.f3565f.mo3667c(C0810z.m3800b(12));
        try {
            this.f3563d.m3797d();
            if (this.f3567h != null) {
                this.f3567h.m3792c();
            }
            if (this.f3567h != null && this.f3566g != null) {
                zzb.zzi("BillingClient", "Unbinding from service.");
                this.f3564e.unbindService(this.f3567h);
                this.f3567h = null;
            }
            this.f3566g = null;
            ExecutorService executorService = this.f3585z;
            if (executorService != null) {
                executorService.shutdownNow();
                this.f3585z = null;
            }
        } catch (Exception e9) {
            zzb.zzk("BillingClient", "There was an exception while ending connection!", e9);
        } finally {
            this.f3560a = 3;
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:48:0x00b7  */
    @Override // com.android.billingclient.api.AbstractC0763d
    /* renamed from: c */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final C0774h mo3673c(String str) {
        if (!mo3674d()) {
            C0774h c0774h = C0761c0.f3537m;
            if (c0774h.m3760b() != 0) {
                this.f3565f.mo3666b(C0810z.m3799a(2, 5, c0774h));
            } else {
                this.f3565f.mo3667c(C0810z.m3800b(5));
            }
            return c0774h;
        }
        C0774h c0774h2 = C0761c0.f3525a;
        switch (str) {
            case "subscriptions":
                C0774h c0774h3 = this.f3568i ? C0761c0.f3536l : C0761c0.f3539o;
                m3709B(c0774h3, 9, 2);
                return c0774h3;
            case "subscriptionsUpdate":
                C0774h c0774h4 = this.f3569j ? C0761c0.f3536l : C0761c0.f3540p;
                m3709B(c0774h4, 10, 3);
                return c0774h4;
            case "priceChangeConfirmation":
                C0774h c0774h5 = this.f3572m ? C0761c0.f3536l : C0761c0.f3542r;
                m3709B(c0774h5, 35, 4);
                return c0774h5;
            case "bbb":
                C0774h c0774h6 = this.f3575p ? C0761c0.f3536l : C0761c0.f3547w;
                m3709B(c0774h6, 30, 5);
                return c0774h6;
            case "aaa":
                C0774h c0774h7 = this.f3577r ? C0761c0.f3536l : C0761c0.f3543s;
                m3709B(c0774h7, 31, 6);
                return c0774h7;
            case "ddd":
                C0774h c0774h8 = this.f3576q ? C0761c0.f3536l : C0761c0.f3545u;
                m3709B(c0774h8, 21, 7);
                return c0774h8;
            case "ccc":
                C0774h c0774h9 = this.f3578s ? C0761c0.f3536l : C0761c0.f3544t;
                m3709B(c0774h9, 19, 8);
                return c0774h9;
            case "eee":
                C0774h c0774h10 = this.f3578s ? C0761c0.f3536l : C0761c0.f3544t;
                m3709B(c0774h10, 61, 9);
                return c0774h10;
            case "fff":
                C0774h c0774h11 = this.f3579t ? C0761c0.f3536l : C0761c0.f3546v;
                m3709B(c0774h11, 20, 10);
                return c0774h11;
            case "ggg":
                C0774h c0774h12 = this.f3580u ? C0761c0.f3536l : C0761c0.f3550z;
                m3709B(c0774h12, 32, 11);
                return c0774h12;
            case "hhh":
                C0774h c0774h13 = this.f3580u ? C0761c0.f3536l : C0761c0.f3522A;
                m3709B(c0774h13, 33, 12);
                return c0774h13;
            case "iii":
                C0774h c0774h14 = this.f3582w ? C0761c0.f3536l : C0761c0.f3524C;
                m3709B(c0774h14, 60, 13);
                return c0774h14;
            default:
                zzb.zzj("BillingClient", "Unsupported feature: ".concat(str));
                C0774h c0774h15 = C0761c0.f3549y;
                m3709B(c0774h15, 34, 1);
                return c0774h15;
        }
    }

    @Override // com.android.billingclient.api.AbstractC0763d
    /* renamed from: d */
    public final boolean mo3674d() {
        return (this.f3560a != 2 || this.f3566g == null || this.f3567h == null) ? false : true;
    }

    /* JADX WARN: Removed duplicated region for block: B:150:0x0394  */
    /* JADX WARN: Removed duplicated region for block: B:154:0x039d  */
    /* JADX WARN: Removed duplicated region for block: B:156:0x03a1  */
    /* JADX WARN: Removed duplicated region for block: B:157:0x03a4  */
    /* JADX WARN: Removed duplicated region for block: B:166:0x0404 A[Catch: Exception -> 0x044f, CancellationException -> 0x0466, TimeoutException -> 0x0468, TryCatch #4 {CancellationException -> 0x0466, TimeoutException -> 0x0468, Exception -> 0x044f, blocks: (B:164:0x03f0, B:166:0x0404, B:168:0x0435), top: B:186:0x03f0 }] */
    /* JADX WARN: Removed duplicated region for block: B:168:0x0435 A[Catch: Exception -> 0x044f, CancellationException -> 0x0466, TimeoutException -> 0x0468, TRY_LEAVE, TryCatch #4 {CancellationException -> 0x0466, TimeoutException -> 0x0468, Exception -> 0x044f, blocks: (B:164:0x03f0, B:166:0x0404, B:168:0x0435), top: B:186:0x03f0 }] */
    @Override // com.android.billingclient.api.AbstractC0763d
    /* renamed from: e */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final C0774h mo3675e(Activity activity, final C0772g c0772g) {
        String str;
        String str2;
        Future futureM3722z;
        int iZzb;
        boolean z8;
        String str3;
        SkuDetails skuDetails;
        C0772g.b bVar;
        String str4;
        String str5;
        boolean z9;
        String str6;
        boolean z10;
        String str7;
        int i9;
        final int i10;
        C0772g.b bVar2;
        final C0766e c0766e = this;
        if (!mo3674d()) {
            InterfaceC0755a0 interfaceC0755a0 = c0766e.f3565f;
            C0774h c0774h = C0761c0.f3537m;
            interfaceC0755a0.mo3666b(C0810z.m3799a(2, 2, c0774h));
            c0766e.m3720w(c0774h);
            return c0774h;
        }
        ArrayList<SkuDetails> arrayListM3738h = c0772g.m3738h();
        List listM3739i = c0772g.m3739i();
        SkuDetails skuDetails2 = (SkuDetails) zzz.zza(arrayListM3738h, null);
        C0772g.b bVar3 = (C0772g.b) zzz.zza(listM3739i, null);
        if (skuDetails2 == null) {
            bVar3.m3744a();
            throw null;
        }
        final String strM3651e = skuDetails2.m3651e();
        final String strM3653g = skuDetails2.m3653g();
        if (strM3653g.equals("subs") && !c0766e.f3568i) {
            zzb.zzj("BillingClient", "Current client doesn't support subscriptions.");
            InterfaceC0755a0 interfaceC0755a02 = c0766e.f3565f;
            C0774h c0774h2 = C0761c0.f3539o;
            interfaceC0755a02.mo3666b(C0810z.m3799a(9, 2, c0774h2));
            c0766e.m3720w(c0774h2);
            return c0774h2;
        }
        if (c0772g.m3741r() && !c0766e.f3571l) {
            zzb.zzj("BillingClient", "Current client doesn't support extra params for buy intent.");
            InterfaceC0755a0 interfaceC0755a03 = c0766e.f3565f;
            C0774h c0774h3 = C0761c0.f3532h;
            interfaceC0755a03.mo3666b(C0810z.m3799a(18, 2, c0774h3));
            c0766e.m3720w(c0774h3);
            return c0774h3;
        }
        if (arrayListM3738h.size() > 1 && !c0766e.f3578s) {
            zzb.zzj("BillingClient", "Current client doesn't support multi-item purchases.");
            InterfaceC0755a0 interfaceC0755a04 = c0766e.f3565f;
            C0774h c0774h4 = C0761c0.f3544t;
            interfaceC0755a04.mo3666b(C0810z.m3799a(19, 2, c0774h4));
            c0766e.m3720w(c0774h4);
            return c0774h4;
        }
        if (!listM3739i.isEmpty() && !c0766e.f3579t) {
            zzb.zzj("BillingClient", "Current client doesn't support purchases with ProductDetails.");
            InterfaceC0755a0 interfaceC0755a05 = c0766e.f3565f;
            C0774h c0774h5 = C0761c0.f3546v;
            interfaceC0755a05.mo3666b(C0810z.m3799a(20, 2, c0774h5));
            c0766e.m3720w(c0774h5);
            return c0774h5;
        }
        if (c0766e.f3571l) {
            boolean z11 = c0766e.f3573n;
            boolean z12 = c0766e.f3584y;
            String str8 = c0766e.f3561b;
            final Bundle bundle = new Bundle();
            bundle.putString("playBillingLibraryVersion", str8);
            if (c0772g.m3733c() != 0) {
                bundle.putInt("prorationMode", c0772g.m3733c());
            } else if (c0772g.m3732b() != 0) {
                bundle.putInt("prorationMode", c0772g.m3732b());
            }
            if (!TextUtils.isEmpty(c0772g.m3734d())) {
                bundle.putString("accountId", c0772g.m3734d());
            }
            if (!TextUtils.isEmpty(c0772g.m3735e())) {
                bundle.putString("obfuscatedProfileId", c0772g.m3735e());
            }
            if (c0772g.m3740q()) {
                bundle.putBoolean("isOfferPersonalizedByDeveloper", true);
            }
            if (!TextUtils.isEmpty(null)) {
                bundle.putStringArrayList("skusToReplace", new ArrayList<>(Arrays.asList(null)));
            }
            if (!TextUtils.isEmpty(c0772g.m3736f())) {
                bundle.putString("oldSkuPurchaseToken", c0772g.m3736f());
            }
            String str9 = null;
            if (!TextUtils.isEmpty(null)) {
                bundle.putString("oldSkuPurchaseId", null);
            }
            if (!TextUtils.isEmpty(c0772g.m3737g())) {
                bundle.putString("originalExternalTransactionId", c0772g.m3737g());
                str9 = null;
            }
            if (!TextUtils.isEmpty(str9)) {
                bundle.putString("paymentsPurchaseParams", str9);
            }
            if (z11) {
                z8 = true;
                bundle.putBoolean("enablePendingPurchases", true);
            } else {
                z8 = true;
            }
            if (z12) {
                bundle.putBoolean("enableAlternativeBilling", z8);
            }
            str = "BUY_INTENT";
            if (arrayListM3738h.isEmpty()) {
                str3 = "proxyPackageVersion";
                skuDetails = skuDetails2;
                bVar = bVar3;
                str4 = strM3651e;
                str5 = "BillingClient";
                z9 = true;
                ArrayList<String> arrayList = new ArrayList<>(listM3739i.size() - 1);
                ArrayList<String> arrayList2 = new ArrayList<>(listM3739i.size() - 1);
                ArrayList<String> arrayList3 = new ArrayList<>();
                ArrayList<String> arrayList4 = new ArrayList<>();
                ArrayList<String> arrayList5 = new ArrayList<>();
                if (listM3739i.size() > 0) {
                    ((C0772g.b) listM3739i.get(0)).m3744a();
                    throw null;
                }
                bundle.putStringArrayList("SKU_OFFER_ID_TOKEN_LIST", arrayList4);
                if (!arrayList3.isEmpty()) {
                    bundle.putStringArrayList("skuDetailsTokens", arrayList3);
                }
                if (!arrayList5.isEmpty()) {
                    bundle.putStringArrayList("SKU_SERIALIZED_DOCID_LIST", arrayList5);
                }
                if (!arrayList.isEmpty()) {
                    bundle.putStringArrayList("additionalSkus", arrayList);
                    bundle.putStringArrayList("additionalSkuTypes", arrayList2);
                }
            } else {
                ArrayList<String> arrayList6 = new ArrayList<>();
                ArrayList<String> arrayList7 = new ArrayList<>();
                str4 = strM3651e;
                ArrayList<String> arrayList8 = new ArrayList<>();
                str3 = "proxyPackageVersion";
                ArrayList<Integer> arrayList9 = new ArrayList<>();
                str5 = "BillingClient";
                ArrayList<String> arrayList10 = new ArrayList<>();
                boolean z13 = false;
                boolean z14 = false;
                boolean z15 = false;
                boolean z16 = false;
                for (SkuDetails skuDetails3 : arrayListM3738h) {
                    if (skuDetails3.m3659m().isEmpty()) {
                        bVar2 = bVar3;
                    } else {
                        bVar2 = bVar3;
                        arrayList6.add(skuDetails3.m3659m());
                    }
                    String strM3656j = skuDetails3.m3656j();
                    SkuDetails skuDetails4 = skuDetails2;
                    String strM3655i = skuDetails3.m3655i();
                    int iM3654h = skuDetails3.m3654h();
                    String strM3658l = skuDetails3.m3658l();
                    arrayList7.add(strM3656j);
                    z13 |= !TextUtils.isEmpty(strM3656j);
                    arrayList8.add(strM3655i);
                    z14 |= !TextUtils.isEmpty(strM3655i);
                    arrayList9.add(Integer.valueOf(iM3654h));
                    z15 |= iM3654h != 0;
                    z16 |= !TextUtils.isEmpty(strM3658l);
                    arrayList10.add(strM3658l);
                    bVar3 = bVar2;
                    skuDetails2 = skuDetails4;
                }
                skuDetails = skuDetails2;
                bVar = bVar3;
                if (!arrayList6.isEmpty()) {
                    bundle.putStringArrayList("skuDetailsTokens", arrayList6);
                }
                if (z13) {
                    bundle.putStringArrayList("SKU_OFFER_ID_TOKEN_LIST", arrayList7);
                }
                if (z14) {
                    bundle.putStringArrayList("SKU_OFFER_ID_LIST", arrayList8);
                }
                if (z15) {
                    bundle.putIntegerArrayList("SKU_OFFER_TYPE_LIST", arrayList9);
                }
                if (z16) {
                    bundle.putStringArrayList("SKU_SERIALIZED_DOCID_LIST", arrayList10);
                }
                z9 = true;
                if (arrayListM3738h.size() > 1) {
                    ArrayList<String> arrayList11 = new ArrayList<>(arrayListM3738h.size() - 1);
                    ArrayList<String> arrayList12 = new ArrayList<>(arrayListM3738h.size() - 1);
                    for (int i11 = 1; i11 < arrayListM3738h.size(); i11++) {
                        arrayList11.add(((SkuDetails) arrayListM3738h.get(i11)).m3651e());
                        arrayList12.add(((SkuDetails) arrayListM3738h.get(i11)).m3653g());
                    }
                    bundle.putStringArrayList("additionalSkus", arrayList11);
                    bundle.putStringArrayList("additionalSkuTypes", arrayList12);
                }
            }
            c0766e = this;
            if (bundle.containsKey("SKU_OFFER_ID_TOKEN_LIST") && !c0766e.f3576q) {
                InterfaceC0755a0 interfaceC0755a06 = c0766e.f3565f;
                C0774h c0774h6 = C0761c0.f3545u;
                interfaceC0755a06.mo3666b(C0810z.m3799a(21, 2, c0774h6));
                c0766e.m3720w(c0774h6);
                return c0774h6;
            }
            if (skuDetails == null || TextUtils.isEmpty(skuDetails.m3657k())) {
                if (bVar != null) {
                    bVar.m3744a();
                    throw null;
                }
                str6 = null;
                z10 = false;
            } else {
                bundle.putString("skuPackageName", skuDetails.m3657k());
                z10 = z9;
                str6 = null;
            }
            if (!TextUtils.isEmpty(str6)) {
                bundle.putString("accountName", str6);
            }
            Intent intent = activity.getIntent();
            if (intent == null) {
                str2 = str5;
                zzb.zzj(str2, "Activity's intent is null.");
            } else {
                str2 = str5;
                if (!TextUtils.isEmpty(intent.getStringExtra("PROXY_PACKAGE"))) {
                    String stringExtra = intent.getStringExtra("PROXY_PACKAGE");
                    bundle.putString("proxyPackage", stringExtra);
                    try {
                        str7 = str3;
                        try {
                            bundle.putString(str7, c0766e.f3564e.getPackageManager().getPackageInfo(stringExtra, 0).versionName);
                        } catch (PackageManager.NameNotFoundException unused) {
                            bundle.putString(str7, "package not found");
                            if (!c0766e.f3579t) {
                                if (!c0766e.f3577r) {
                                    if (c0766e.f3573n) {
                                    }
                                }
                            }
                            Bundle bundle2 = (Bundle) futureM3722z.get(DefaultRenderersFactory.DEFAULT_ALLOWED_VIDEO_JOINING_TIME_MS, TimeUnit.MILLISECONDS);
                            iZzb = zzb.zzb(bundle2, str2);
                            String strZzf = zzb.zzf(bundle2, str2);
                            if (iZzb != 0) {
                            }
                        }
                    } catch (PackageManager.NameNotFoundException unused2) {
                        str7 = str3;
                    }
                }
            }
            if (!c0766e.f3579t && !listM3739i.isEmpty()) {
                i9 = 17;
            } else if (!c0766e.f3577r && z10) {
                i9 = 15;
            } else if (c0766e.f3573n) {
                i9 = 6;
            } else {
                i10 = 9;
                final String str10 = str4;
                futureM3722z = m3722z(new Callable() { // from class: com.android.billingclient.api.a1
                    @Override // java.util.concurrent.Callable
                    public final Object call() {
                        return this.f3513b.m3710E(i10, str10, strM3653g, c0772g, bundle);
                    }
                }, DefaultRenderersFactory.DEFAULT_ALLOWED_VIDEO_JOINING_TIME_MS, null, c0766e.f3562c);
            }
            i10 = i9;
            final String str102 = str4;
            futureM3722z = m3722z(new Callable() { // from class: com.android.billingclient.api.a1
                @Override // java.util.concurrent.Callable
                public final Object call() {
                    return this.f3513b.m3710E(i10, str102, strM3653g, c0772g, bundle);
                }
            }, DefaultRenderersFactory.DEFAULT_ALLOWED_VIDEO_JOINING_TIME_MS, null, c0766e.f3562c);
        } else {
            str = "BUY_INTENT";
            str2 = "BillingClient";
            futureM3722z = m3722z(new Callable() { // from class: com.android.billingclient.api.b1
                @Override // java.util.concurrent.Callable
                public final Object call() {
                    return this.f3519b.m3711F(strM3651e, strM3653g);
                }
            }, DefaultRenderersFactory.DEFAULT_ALLOWED_VIDEO_JOINING_TIME_MS, null, c0766e.f3562c);
        }
        try {
            Bundle bundle22 = (Bundle) futureM3722z.get(DefaultRenderersFactory.DEFAULT_ALLOWED_VIDEO_JOINING_TIME_MS, TimeUnit.MILLISECONDS);
            iZzb = zzb.zzb(bundle22, str2);
            String strZzf2 = zzb.zzf(bundle22, str2);
            if (iZzb != 0) {
                Intent intent2 = new Intent(activity, (Class<?>) ProxyBillingActivity.class);
                String str11 = str;
                intent2.putExtra(str11, (PendingIntent) bundle22.getParcelable(str11));
                activity.startActivity(intent2);
                return C0761c0.f3536l;
            }
            zzb.zzj(str2, "Unable to buy item, Error response code: " + iZzb);
            C0774h.a aVarM3756c = C0774h.m3756c();
            aVarM3756c.m3763c(iZzb);
            aVarM3756c.m3762b(strZzf2);
            C0774h c0774hM3761a = aVarM3756c.m3761a();
            c0766e.f3565f.mo3666b(C0810z.m3799a(3, 2, c0774hM3761a));
            c0766e.m3720w(c0774hM3761a);
            return c0774hM3761a;
        } catch (CancellationException e9) {
            e = e9;
            zzb.zzk(str2, "Time out while launching billing flow. Try to reconnect", e);
            InterfaceC0755a0 interfaceC0755a07 = c0766e.f3565f;
            C0774h c0774h7 = C0761c0.f3538n;
            interfaceC0755a07.mo3666b(C0810z.m3799a(4, 2, c0774h7));
            c0766e.m3720w(c0774h7);
            return c0774h7;
        } catch (TimeoutException e10) {
            e = e10;
            zzb.zzk(str2, "Time out while launching billing flow. Try to reconnect", e);
            InterfaceC0755a0 interfaceC0755a072 = c0766e.f3565f;
            C0774h c0774h72 = C0761c0.f3538n;
            interfaceC0755a072.mo3666b(C0810z.m3799a(4, 2, c0774h72));
            c0766e.m3720w(c0774h72);
            return c0774h72;
        } catch (Exception e11) {
            zzb.zzk(str2, "Exception while launching billing flow. Try to reconnect", e11);
            InterfaceC0755a0 interfaceC0755a08 = c0766e.f3565f;
            C0774h c0774h8 = C0761c0.f3537m;
            interfaceC0755a08.mo3666b(C0810z.m3799a(5, 2, c0774h8));
            c0766e.m3720w(c0774h8);
            return c0774h8;
        }
    }

    @Override // com.android.billingclient.api.AbstractC0763d
    /* renamed from: g */
    public final void mo3676g(C0784m c0784m, InterfaceC0780k interfaceC0780k) {
        m3708A(c0784m.m3767b(), interfaceC0780k);
    }

    @Override // com.android.billingclient.api.AbstractC0763d
    /* renamed from: h */
    public final void mo3677h(C0786n c0786n, final InterfaceC0788o interfaceC0788o) {
        if (!mo3674d()) {
            InterfaceC0755a0 interfaceC0755a0 = this.f3565f;
            C0774h c0774h = C0761c0.f3537m;
            interfaceC0755a0.mo3666b(C0810z.m3799a(2, 8, c0774h));
            interfaceC0788o.mo3783a(c0774h, null);
            return;
        }
        final String strM3776a = c0786n.m3776a();
        final List<String> listM3777b = c0786n.m3777b();
        if (TextUtils.isEmpty(strM3776a)) {
            zzb.zzj("BillingClient", "Please fix the input params. SKU type can't be empty.");
            InterfaceC0755a0 interfaceC0755a02 = this.f3565f;
            C0774h c0774h2 = C0761c0.f3530f;
            interfaceC0755a02.mo3666b(C0810z.m3799a(49, 8, c0774h2));
            interfaceC0788o.mo3783a(c0774h2, null);
            return;
        }
        if (listM3777b == null) {
            zzb.zzj("BillingClient", "Please fix the input params. The list of SKUs can't be empty.");
            InterfaceC0755a0 interfaceC0755a03 = this.f3565f;
            C0774h c0774h3 = C0761c0.f3529e;
            interfaceC0755a03.mo3666b(C0810z.m3799a(48, 8, c0774h3));
            interfaceC0788o.mo3783a(c0774h3, null);
            return;
        }
        final String str = null;
        if (m3722z(new Callable(strM3776a, listM3777b, str, interfaceC0788o) { // from class: com.android.billingclient.api.w0

            /* renamed from: c */
            public final /* synthetic */ String f3648c;

            /* renamed from: d */
            public final /* synthetic */ List f3649d;

            /* renamed from: e */
            public final /* synthetic */ InterfaceC0788o f3650e;

            {
                this.f3650e = interfaceC0788o;
            }

            @Override // java.util.concurrent.Callable
            public final Object call() {
                this.f3647b.m3713L(this.f3648c, this.f3649d, null, this.f3650e);
                return null;
            }
        }, SsMediaSource.DEFAULT_LIVE_PRESENTATION_DELAY_MS, new Runnable() { // from class: com.android.billingclient.api.z0
            @Override // java.lang.Runnable
            public final void run() {
                this.f3656b.m3718t(interfaceC0788o);
            }
        }, m3719v()) == null) {
            C0774h c0774hM3721x = m3721x();
            this.f3565f.mo3666b(C0810z.m3799a(25, 8, c0774hM3721x));
            interfaceC0788o.mo3783a(c0774hM3721x, null);
        }
    }

    @Override // com.android.billingclient.api.AbstractC0763d
    /* renamed from: i */
    public final void mo3678i(InterfaceC0769f interfaceC0769f) {
        if (mo3674d()) {
            zzb.zzi("BillingClient", "Service connection is valid. No need to re-initialize.");
            this.f3565f.mo3667c(C0810z.m3800b(6));
            interfaceC0769f.onBillingSetupFinished(C0761c0.f3536l);
            return;
        }
        int i9 = 1;
        if (this.f3560a == 1) {
            zzb.zzj("BillingClient", "Client is already in the process of connecting to billing service.");
            InterfaceC0755a0 interfaceC0755a0 = this.f3565f;
            C0774h c0774h = C0761c0.f3528d;
            interfaceC0755a0.mo3666b(C0810z.m3799a(37, 6, c0774h));
            interfaceC0769f.onBillingSetupFinished(c0774h);
            return;
        }
        if (this.f3560a == 3) {
            zzb.zzj("BillingClient", "Client was already closed and can't be reused. Please create another instance.");
            InterfaceC0755a0 interfaceC0755a02 = this.f3565f;
            C0774h c0774h2 = C0761c0.f3537m;
            interfaceC0755a02.mo3666b(C0810z.m3799a(38, 6, c0774h2));
            interfaceC0769f.onBillingSetupFinished(c0774h2);
            return;
        }
        this.f3560a = 1;
        this.f3563d.m3798e();
        zzb.zzi("BillingClient", "Starting in-app billing setup.");
        this.f3567h = new ServiceConnectionC0800u(this, interfaceC0769f, null);
        Intent intent = new Intent("com.android.vending.billing.InAppBillingService.BIND");
        intent.setPackage("com.android.vending");
        List<ResolveInfo> listQueryIntentServices = this.f3564e.getPackageManager().queryIntentServices(intent, 0);
        if (listQueryIntentServices == null || listQueryIntentServices.isEmpty()) {
            i9 = 41;
        } else {
            ServiceInfo serviceInfo = listQueryIntentServices.get(0).serviceInfo;
            if (serviceInfo != null) {
                String str = serviceInfo.packageName;
                String str2 = serviceInfo.name;
                if (!"com.android.vending".equals(str) || str2 == null) {
                    zzb.zzj("BillingClient", "The device doesn't have valid Play Store.");
                    i9 = 40;
                } else {
                    ComponentName componentName = new ComponentName(str, str2);
                    Intent intent2 = new Intent(intent);
                    intent2.setComponent(componentName);
                    intent2.putExtra("playBillingLibraryVersion", this.f3561b);
                    if (this.f3564e.bindService(intent2, this.f3567h, 1)) {
                        zzb.zzi("BillingClient", "Service was bonded successfully.");
                        return;
                    } else {
                        zzb.zzj("BillingClient", "Connection to Billing service is blocked.");
                        i9 = 39;
                    }
                }
            }
        }
        this.f3560a = 0;
        zzb.zzi("BillingClient", "Billing service unavailable on device.");
        InterfaceC0755a0 interfaceC0755a03 = this.f3565f;
        C0774h c0774h3 = C0761c0.f3527c;
        interfaceC0755a03.mo3666b(C0810z.m3799a(i9, 6, c0774h3));
        interfaceC0769f.onBillingSetupFinished(c0774h3);
    }

    /* renamed from: j */
    public final void m3714j(Context context, InterfaceC0782l interfaceC0782l, C0783l0 c0783l0, InterfaceC0760c interfaceC0760c, String str, InterfaceC0755a0 interfaceC0755a0) {
        this.f3564e = context.getApplicationContext();
        zzfl zzflVarZzv = zzfm.zzv();
        zzflVarZzv.zzj(str);
        zzflVarZzv.zzi(this.f3564e.getPackageName());
        if (interfaceC0755a0 != null) {
            this.f3565f = interfaceC0755a0;
        } else {
            this.f3565f = new C0764d0(this.f3564e, (zzfm) zzflVarZzv.zzc());
        }
        if (interfaceC0782l == null) {
            zzb.zzj("BillingClient", "Billing client should have a valid listener but the provided is null.");
        }
        this.f3563d = new C0801u0(this.f3564e, interfaceC0782l, interfaceC0760c, this.f3565f);
        this.f3583x = c0783l0;
        this.f3584y = interfaceC0760c != null;
    }

    /* renamed from: q */
    public final /* synthetic */ void m3715q(InterfaceC0757b interfaceC0757b) {
        InterfaceC0755a0 interfaceC0755a0 = this.f3565f;
        C0774h c0774h = C0761c0.f3538n;
        interfaceC0755a0.mo3666b(C0810z.m3799a(24, 3, c0774h));
        interfaceC0757b.mo3668a(c0774h);
    }

    /* renamed from: r */
    public final /* synthetic */ void m3716r(C0774h c0774h) {
        if (this.f3563d.m3796c() != null) {
            this.f3563d.m3796c().onPurchasesUpdated(c0774h, null);
        } else {
            this.f3563d.m3795b();
            zzb.zzj("BillingClient", "No valid listener is set in BroadcastManager");
        }
    }

    /* renamed from: s */
    public final /* synthetic */ void m3717s(InterfaceC0780k interfaceC0780k) {
        InterfaceC0755a0 interfaceC0755a0 = this.f3565f;
        C0774h c0774h = C0761c0.f3538n;
        interfaceC0755a0.mo3666b(C0810z.m3799a(24, 9, c0774h));
        interfaceC0780k.onQueryPurchasesResponse(c0774h, zzu.zzk());
    }

    /* renamed from: t */
    public final /* synthetic */ void m3718t(InterfaceC0788o interfaceC0788o) {
        InterfaceC0755a0 interfaceC0755a0 = this.f3565f;
        C0774h c0774h = C0761c0.f3538n;
        interfaceC0755a0.mo3666b(C0810z.m3799a(24, 8, c0774h));
        interfaceC0788o.mo3783a(c0774h, null);
    }

    /* renamed from: v */
    public final Handler m3719v() {
        return Looper.myLooper() == null ? this.f3562c : new Handler(Looper.myLooper());
    }

    /* renamed from: w */
    public final C0774h m3720w(final C0774h c0774h) {
        if (Thread.interrupted()) {
            return c0774h;
        }
        this.f3562c.post(new Runnable() { // from class: com.android.billingclient.api.e1
            @Override // java.lang.Runnable
            public final void run() {
                this.f3587b.m3716r(c0774h);
            }
        });
        return c0774h;
    }

    /* renamed from: x */
    public final C0774h m3721x() {
        return (this.f3560a == 0 || this.f3560a == 3) ? C0761c0.f3537m : C0761c0.f3534j;
    }

    /* renamed from: z */
    public final Future m3722z(Callable callable, long j9, final Runnable runnable, Handler handler) {
        if (this.f3585z == null) {
            this.f3585z = Executors.newFixedThreadPool(zzb.zza, new ThreadFactoryC0792q(this));
        }
        try {
            final Future futureSubmit = this.f3585z.submit(callable);
            handler.postDelayed(new Runnable() { // from class: com.android.billingclient.api.d1
                @Override // java.lang.Runnable
                public final void run() {
                    Future future = futureSubmit;
                    Runnable runnable2 = runnable;
                    if (future.isDone() || future.isCancelled()) {
                        return;
                    }
                    future.cancel(true);
                    zzb.zzj("BillingClient", "Async task is taking too long, cancel it!");
                    if (runnable2 != null) {
                        runnable2.run();
                    }
                }
            }, (long) (j9 * 0.95d));
            return futureSubmit;
        } catch (Exception e9) {
            zzb.zzk("BillingClient", "Async task throws exception!", e9);
            return null;
        }
    }

    public C0766e(String str, C0783l0 c0783l0, Context context, InterfaceC0773g0 interfaceC0773g0, InterfaceC0755a0 interfaceC0755a0) {
        this.f3560a = 0;
        this.f3562c = new Handler(Looper.getMainLooper());
        this.f3570k = 0;
        this.f3561b = m3707y();
        this.f3564e = context.getApplicationContext();
        zzfl zzflVarZzv = zzfm.zzv();
        zzflVarZzv.zzj(m3707y());
        zzflVarZzv.zzi(this.f3564e.getPackageName());
        this.f3565f = new C0764d0(this.f3564e, (zzfm) zzflVarZzv.zzc());
        zzb.zzj("BillingClient", "Billing client should have a valid listener but the provided is null.");
        this.f3563d = new C0801u0(this.f3564e, null, this.f3565f);
        this.f3583x = c0783l0;
    }

    public C0766e(String str, C0783l0 c0783l0, Context context, InterfaceC0782l interfaceC0782l, InterfaceC0760c interfaceC0760c, InterfaceC0755a0 interfaceC0755a0) {
        this(context, c0783l0, interfaceC0782l, m3707y(), null, interfaceC0760c, null);
    }
}
