package com.android.billingclient.api;

import android.content.Context;
import com.google.android.datatransport.Encoding;
import com.google.android.datatransport.Event;
import com.google.android.datatransport.Transformer;
import com.google.android.datatransport.Transport;
import com.google.android.datatransport.cct.CCTDestination;
import com.google.android.datatransport.runtime.TransportRuntime;
import com.google.android.gms.internal.play_billing.zzb;
import com.google.android.gms.internal.play_billing.zzfz;

/* renamed from: com.android.billingclient.api.f0 */
/* loaded from: classes.dex */
public final class C0770f0 {

    /* renamed from: a */
    public boolean f3589a;

    /* renamed from: b */
    public Transport f3590b;

    public C0770f0(Context context) {
        try {
            TransportRuntime.initialize(context);
            this.f3590b = TransportRuntime.getInstance().newFactory(CCTDestination.INSTANCE).getTransport("PLAY_BILLING_LIBRARY", zzfz.class, Encoding.m17449of("proto"), new Transformer() { // from class: com.android.billingclient.api.e0
                @Override // com.google.android.datatransport.Transformer
                public final Object apply(Object obj) {
                    return ((zzfz) obj).zzc();
                }
            });
        } catch (Throwable unused) {
            this.f3589a = true;
        }
    }

    /* renamed from: a */
    public final void m3723a(zzfz zzfzVar) {
        if (this.f3589a) {
            zzb.zzj("BillingLogger", "Skipping logging since initialization failed.");
            return;
        }
        try {
            this.f3590b.send(Event.ofData(zzfzVar));
        } catch (Throwable unused) {
            zzb.zzj("BillingLogger", "logging failed.");
        }
    }
}
