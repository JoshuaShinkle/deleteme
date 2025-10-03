package com.android.billingclient.api;

import android.content.Context;
import com.google.android.gms.internal.play_billing.zzb;
import com.google.android.gms.internal.play_billing.zzfb;
import com.google.android.gms.internal.play_billing.zzff;
import com.google.android.gms.internal.play_billing.zzfm;
import com.google.android.gms.internal.play_billing.zzfy;
import com.google.android.gms.internal.play_billing.zzfz;
import com.google.android.gms.internal.play_billing.zzgd;

/* renamed from: com.android.billingclient.api.d0 */
/* loaded from: classes.dex */
public final class C0764d0 implements InterfaceC0755a0 {

    /* renamed from: a */
    public final zzfm f3556a;

    /* renamed from: b */
    public final C0770f0 f3557b;

    public C0764d0(Context context, zzfm zzfmVar) {
        this.f3557b = new C0770f0(context);
        this.f3556a = zzfmVar;
    }

    @Override // com.android.billingclient.api.InterfaceC0755a0
    /* renamed from: a */
    public final void mo3665a(zzgd zzgdVar) {
        try {
            zzfy zzfyVarZzv = zzfz.zzv();
            zzfm zzfmVar = this.f3556a;
            if (zzfmVar != null) {
                zzfyVarZzv.zzk(zzfmVar);
            }
            zzfyVarZzv.zzl(zzgdVar);
            this.f3557b.m3723a((zzfz) zzfyVarZzv.zzc());
        } catch (Throwable unused) {
            zzb.zzj("BillingLogger", "Unable to log.");
        }
    }

    @Override // com.android.billingclient.api.InterfaceC0755a0
    /* renamed from: b */
    public final void mo3666b(zzfb zzfbVar) {
        try {
            zzfy zzfyVarZzv = zzfz.zzv();
            zzfm zzfmVar = this.f3556a;
            if (zzfmVar != null) {
                zzfyVarZzv.zzk(zzfmVar);
            }
            zzfyVarZzv.zzi(zzfbVar);
            this.f3557b.m3723a((zzfz) zzfyVarZzv.zzc());
        } catch (Throwable unused) {
            zzb.zzj("BillingLogger", "Unable to log.");
        }
    }

    @Override // com.android.billingclient.api.InterfaceC0755a0
    /* renamed from: c */
    public final void mo3667c(zzff zzffVar) {
        try {
            zzfy zzfyVarZzv = zzfz.zzv();
            zzfm zzfmVar = this.f3556a;
            if (zzfmVar != null) {
                zzfyVarZzv.zzk(zzfmVar);
            }
            zzfyVarZzv.zzj(zzffVar);
            this.f3557b.m3723a((zzfz) zzfyVarZzv.zzc());
        } catch (Throwable unused) {
            zzb.zzj("BillingLogger", "Unable to log.");
        }
    }
}
