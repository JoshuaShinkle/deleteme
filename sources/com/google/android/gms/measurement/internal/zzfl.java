package com.google.android.gms.measurement.internal;

import android.content.SharedPreferences;
import com.google.android.gms.common.internal.Preconditions;

/* loaded from: classes2.dex */
public final class zzfl {
    private final String zza;
    private final boolean zzb;
    private boolean zzc;
    private boolean zzd;
    private final /* synthetic */ zzfj zze;

    public zzfl(zzfj zzfjVar, String str, boolean z8) {
        this.zze = zzfjVar;
        Preconditions.checkNotEmpty(str);
        this.zza = str;
        this.zzb = z8;
    }

    public final boolean zza() {
        if (!this.zzc) {
            this.zzc = true;
            this.zzd = this.zze.zzf().getBoolean(this.zza, this.zzb);
        }
        return this.zzd;
    }

    public final void zza(boolean z8) {
        SharedPreferences.Editor editorEdit = this.zze.zzf().edit();
        editorEdit.putBoolean(this.zza, z8);
        editorEdit.apply();
        this.zzd = z8;
    }
}
