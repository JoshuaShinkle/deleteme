package com.google.android.gms.internal.gtm;

/* loaded from: classes2.dex */
final class zzcb implements zzbp<zzcc> {
    private final zzcc zzaat = new zzcc();
    private final zzap zzwc;

    public zzcb(zzap zzapVar) {
        this.zzwc = zzapVar;
    }

    @Override // com.google.android.gms.internal.gtm.zzbp
    public final void zza(String str, boolean z8) {
        if (!"ga_dryRun".equals(str)) {
            this.zzwc.zzco().zzd("Bool xml configuration name not recognized", str);
        } else {
            this.zzaat.zzaay = z8 ? 1 : 0;
        }
    }

    @Override // com.google.android.gms.internal.gtm.zzbp
    public final void zzb(String str, int i9) {
        if ("ga_dispatchPeriod".equals(str)) {
            this.zzaat.zzaax = i9;
        } else {
            this.zzwc.zzco().zzd("Int xml configuration name not recognized", str);
        }
    }

    @Override // com.google.android.gms.internal.gtm.zzbp
    public final void zzb(String str, String str2) {
    }

    @Override // com.google.android.gms.internal.gtm.zzbp
    public final void zzc(String str, String str2) {
        if ("ga_appName".equals(str)) {
            this.zzaat.zzaau = str2;
            return;
        }
        if ("ga_appVersion".equals(str)) {
            this.zzaat.zzaav = str2;
        } else if ("ga_logLevel".equals(str)) {
            this.zzaat.zzaaw = str2;
        } else {
            this.zzwc.zzco().zzd("String xml configuration name not recognized", str);
        }
    }

    @Override // com.google.android.gms.internal.gtm.zzbp
    public final /* synthetic */ zzbn zzel() {
        return this.zzaat;
    }
}
