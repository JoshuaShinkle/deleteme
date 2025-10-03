package com.google.android.gms.internal.gtm;

/* loaded from: classes2.dex */
final class zzcx extends zzam implements zzbp<zzcy> {
    private final zzcy zzacl;

    public zzcx(zzap zzapVar) {
        super(zzapVar);
        this.zzacl = new zzcy();
    }

    @Override // com.google.android.gms.internal.gtm.zzbp
    public final void zza(String str, boolean z8) {
        if ("ga_autoActivityTracking".equals(str)) {
            this.zzacl.zzacp = z8 ? 1 : 0;
        } else if ("ga_anonymizeIp".equals(str)) {
            this.zzacl.zzacq = z8 ? 1 : 0;
        } else if (!"ga_reportUncaughtExceptions".equals(str)) {
            zzd("bool configuration name not recognized", str);
        } else {
            this.zzacl.zzacr = z8 ? 1 : 0;
        }
    }

    @Override // com.google.android.gms.internal.gtm.zzbp
    public final void zzb(String str, String str2) {
        this.zzacl.zzacs.put(str, str2);
    }

    @Override // com.google.android.gms.internal.gtm.zzbp
    public final void zzc(String str, String str2) {
        if ("ga_trackingId".equals(str)) {
            this.zzacl.zzacm = str2;
            return;
        }
        if (!"ga_sampleFrequency".equals(str)) {
            zzd("string configuration name not recognized", str);
            return;
        }
        try {
            this.zzacl.zzacn = Double.parseDouble(str2);
        } catch (NumberFormatException e9) {
            zzc("Error parsing ga_sampleFrequency value", str2, e9);
        }
    }

    @Override // com.google.android.gms.internal.gtm.zzbp
    public final /* synthetic */ zzbn zzel() {
        return this.zzacl;
    }

    @Override // com.google.android.gms.internal.gtm.zzbp
    public final void zzb(String str, int i9) {
        if ("ga_sessionTimeout".equals(str)) {
            this.zzacl.zzaco = i9;
        } else {
            zzd("int configuration name not recognized", str);
        }
    }
}
