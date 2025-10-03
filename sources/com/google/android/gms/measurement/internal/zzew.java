package com.google.android.gms.measurement.internal;

import com.google.firebase.crashlytics.internal.metadata.UserMetadata;

/* loaded from: classes2.dex */
final class zzew implements Runnable {
    private final /* synthetic */ int zza;
    private final /* synthetic */ String zzb;
    private final /* synthetic */ Object zzc;
    private final /* synthetic */ Object zzd;
    private final /* synthetic */ Object zze;
    private final /* synthetic */ zzex zzf;

    public zzew(zzex zzexVar, int i9, String str, Object obj, Object obj2, Object obj3) {
        this.zzf = zzexVar;
        this.zza = i9;
        this.zzb = str;
        this.zzc = obj;
        this.zzd = obj2;
        this.zze = obj3;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzfj zzfjVarZzb = this.zzf.zzy.zzb();
        if (!zzfjVarZzb.zzz()) {
            this.zzf.zza(6, "Persisted config not initialized. Not logging error/warn");
            return;
        }
        if (this.zzf.zza == 0) {
            if (this.zzf.zzs().zze()) {
                this.zzf.zza = 'C';
            } else {
                this.zzf.zza = 'c';
            }
        }
        if (this.zzf.zzb < 0) {
            this.zzf.zzb = 31049L;
        }
        char cCharAt = "01VDIWEA?".charAt(this.zza);
        char c9 = this.zzf.zza;
        long j9 = this.zzf.zzb;
        String strZza = zzex.zza(true, this.zzb, this.zzc, this.zzd, this.zze);
        StringBuilder sb = new StringBuilder(String.valueOf(strZza).length() + 24);
        sb.append("2");
        sb.append(cCharAt);
        sb.append(c9);
        sb.append(j9);
        sb.append(":");
        sb.append(strZza);
        String string = sb.toString();
        if (string.length() > 1024) {
            string = this.zzb.substring(0, UserMetadata.MAX_ATTRIBUTE_SIZE);
        }
        zzfjVarZzb.zzb.zza(string, 1L);
    }
}
