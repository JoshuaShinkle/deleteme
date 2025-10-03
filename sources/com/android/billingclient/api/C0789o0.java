package com.android.billingclient.api;

import android.os.Bundle;
import com.android.billingclient.api.C0774h;
import com.google.android.gms.internal.play_billing.zzb;
import java.util.ArrayList;

/* renamed from: com.android.billingclient.api.o0 */
/* loaded from: classes.dex */
public final class C0789o0 {
    /* renamed from: a */
    public static C0787n0 m3784a(Bundle bundle, String str, String str2) {
        C0774h c0774h = C0761c0.f3534j;
        if (bundle == null) {
            zzb.zzj("BillingClient", String.format("%s got null owned items list", str2));
            return new C0787n0(c0774h, 54);
        }
        int iZzb = zzb.zzb(bundle, "BillingClient");
        String strZzf = zzb.zzf(bundle, "BillingClient");
        C0774h.a aVarM3756c = C0774h.m3756c();
        aVarM3756c.m3763c(iZzb);
        aVarM3756c.m3762b(strZzf);
        C0774h c0774hM3761a = aVarM3756c.m3761a();
        if (iZzb != 0) {
            zzb.zzj("BillingClient", String.format("%s failed. Response code: %s", str2, Integer.valueOf(iZzb)));
            return new C0787n0(c0774hM3761a, 23);
        }
        if (!bundle.containsKey("INAPP_PURCHASE_ITEM_LIST") || !bundle.containsKey("INAPP_PURCHASE_DATA_LIST") || !bundle.containsKey("INAPP_DATA_SIGNATURE_LIST")) {
            zzb.zzj("BillingClient", String.format("Bundle returned from %s doesn't contain required fields.", str2));
            return new C0787n0(c0774h, 55);
        }
        ArrayList<String> stringArrayList = bundle.getStringArrayList("INAPP_PURCHASE_ITEM_LIST");
        ArrayList<String> stringArrayList2 = bundle.getStringArrayList("INAPP_PURCHASE_DATA_LIST");
        ArrayList<String> stringArrayList3 = bundle.getStringArrayList("INAPP_DATA_SIGNATURE_LIST");
        if (stringArrayList == null) {
            zzb.zzj("BillingClient", String.format("Bundle returned from %s contains null SKUs list.", str2));
            return new C0787n0(c0774h, 56);
        }
        if (stringArrayList2 == null) {
            zzb.zzj("BillingClient", String.format("Bundle returned from %s contains null purchases list.", str2));
            return new C0787n0(c0774h, 57);
        }
        if (stringArrayList3 != null) {
            return new C0787n0(C0761c0.f3536l, 1);
        }
        zzb.zzj("BillingClient", String.format("Bundle returned from %s contains null signatures list.", str2));
        return new C0787n0(c0774h, 58);
    }
}
