package com.android.billingclient.api;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import com.google.android.gms.internal.play_billing.zzb;
import com.google.android.gms.internal.play_billing.zzbn;
import com.google.android.gms.internal.play_billing.zzfb;
import com.google.android.gms.internal.play_billing.zzu;
import java.util.List;

/* renamed from: com.android.billingclient.api.t0 */
/* loaded from: classes.dex */
public final class C0799t0 extends BroadcastReceiver {

    /* renamed from: a */
    public final InterfaceC0782l f3637a;

    /* renamed from: b */
    public final InterfaceC0755a0 f3638b;

    /* renamed from: c */
    public boolean f3639c;

    /* renamed from: d */
    public final /* synthetic */ C0801u0 f3640d;

    public /* synthetic */ C0799t0(C0801u0 c0801u0, InterfaceC0773g0 interfaceC0773g0, InterfaceC0755a0 interfaceC0755a0, C0797s0 c0797s0) {
        this.f3640d = c0801u0;
        this.f3637a = null;
        this.f3638b = interfaceC0755a0;
    }

    /* renamed from: a */
    public static /* bridge */ /* synthetic */ InterfaceC0773g0 m3785a(C0799t0 c0799t0) {
        c0799t0.getClass();
        return null;
    }

    @SuppressLint({"UnprotectedReceiver"})
    /* renamed from: c */
    public final void m3787c(Context context, IntentFilter intentFilter) {
        if (this.f3639c) {
            return;
        }
        if (Build.VERSION.SDK_INT >= 33) {
            context.registerReceiver(this.f3640d.f3646b, intentFilter, 2);
        } else {
            context.registerReceiver(this.f3640d.f3646b, intentFilter);
        }
        this.f3639c = true;
    }

    /* renamed from: d */
    public final void m3788d(Context context) {
        if (!this.f3639c) {
            zzb.zzj("BillingBroadcastManager", "Receiver is not registered.");
        } else {
            context.unregisterReceiver(this.f3640d.f3646b);
            this.f3639c = false;
        }
    }

    /* renamed from: e */
    public final void m3789e(Bundle bundle, C0774h c0774h, int i9) {
        if (bundle.getByteArray("FAILURE_LOGGING_PAYLOAD") == null) {
            this.f3638b.mo3666b(C0810z.m3799a(23, i9, c0774h));
            return;
        }
        try {
            this.f3638b.mo3666b(zzfb.zzx(bundle.getByteArray("FAILURE_LOGGING_PAYLOAD"), zzbn.zza()));
        } catch (Throwable unused) {
            zzb.zzj("BillingBroadcastManager", "Failed parsing Api failure.");
        }
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        Bundle extras = intent.getExtras();
        int i9 = 1;
        if (extras == null) {
            zzb.zzj("BillingBroadcastManager", "Bundle is null.");
            InterfaceC0755a0 interfaceC0755a0 = this.f3638b;
            C0774h c0774h = C0761c0.f3534j;
            interfaceC0755a0.mo3666b(C0810z.m3799a(11, 1, c0774h));
            InterfaceC0782l interfaceC0782l = this.f3637a;
            if (interfaceC0782l != null) {
                interfaceC0782l.onPurchasesUpdated(c0774h, null);
                return;
            }
            return;
        }
        C0774h c0774hZzd = zzb.zzd(intent, "BillingBroadcastManager");
        String action = intent.getAction();
        String string = extras.getString("INTENT_SOURCE");
        if (string == "LAUNCH_BILLING_FLOW" || (string != null && string.equals("LAUNCH_BILLING_FLOW"))) {
            i9 = 2;
        }
        if (action.equals("com.android.vending.billing.PURCHASES_UPDATED")) {
            List<Purchase> listZzh = zzb.zzh(extras);
            if (c0774hZzd.m3760b() == 0) {
                this.f3638b.mo3667c(C0810z.m3800b(i9));
            } else {
                m3789e(extras, c0774hZzd, i9);
            }
            this.f3637a.onPurchasesUpdated(c0774hZzd, listZzh);
            return;
        }
        if (action.equals("com.android.vending.billing.ALTERNATIVE_BILLING")) {
            if (c0774hZzd.m3760b() != 0) {
                m3789e(extras, c0774hZzd, i9);
                this.f3637a.onPurchasesUpdated(c0774hZzd, zzu.zzk());
                return;
            }
            zzb.zzj("BillingBroadcastManager", "AlternativeBillingListener is null.");
            InterfaceC0755a0 interfaceC0755a02 = this.f3638b;
            C0774h c0774h2 = C0761c0.f3534j;
            interfaceC0755a02.mo3666b(C0810z.m3799a(15, i9, c0774h2));
            this.f3637a.onPurchasesUpdated(c0774h2, zzu.zzk());
        }
    }

    public /* synthetic */ C0799t0(C0801u0 c0801u0, InterfaceC0782l interfaceC0782l, InterfaceC0760c interfaceC0760c, InterfaceC0755a0 interfaceC0755a0, C0797s0 c0797s0) {
        this.f3640d = c0801u0;
        this.f3637a = interfaceC0782l;
        this.f3638b = interfaceC0755a0;
    }
}
