package com.google.android.gms.measurement.internal;

import android.content.SharedPreferences;
import com.google.android.gms.common.internal.Preconditions;

/* loaded from: classes2.dex */
public final class zzfp {
    private final String zza;
    private final String zzb;
    private boolean zzc;
    private String zzd;
    private final /* synthetic */ zzfj zze;

    public zzfp(zzfj zzfjVar, String str, String str2) {
        this.zze = zzfjVar;
        Preconditions.checkNotEmpty(str);
        this.zza = str;
        this.zzb = null;
    }

    public final String zza() {
        if (!this.zzc) {
            this.zzc = true;
            this.zzd = this.zze.zzf().getString(this.zza, null);
        }
        return this.zzd;
    }

    public final void zza(String str) {
        SharedPreferences.Editor editorEdit = this.zze.zzf().edit();
        editorEdit.putString(this.zza, str);
        editorEdit.apply();
        this.zzd = str;
    }
}
