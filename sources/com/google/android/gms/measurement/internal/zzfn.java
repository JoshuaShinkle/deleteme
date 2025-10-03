package com.google.android.gms.measurement.internal;

import android.content.SharedPreferences;
import com.google.android.gms.common.internal.Preconditions;

/* loaded from: classes2.dex */
public final class zzfn {
    private final String zza;
    private final long zzb;
    private boolean zzc;
    private long zzd;
    private final /* synthetic */ zzfj zze;

    public zzfn(zzfj zzfjVar, String str, long j9) {
        this.zze = zzfjVar;
        Preconditions.checkNotEmpty(str);
        this.zza = str;
        this.zzb = j9;
    }

    public final long zza() {
        if (!this.zzc) {
            this.zzc = true;
            this.zzd = this.zze.zzf().getLong(this.zza, this.zzb);
        }
        return this.zzd;
    }

    public final void zza(long j9) {
        SharedPreferences.Editor editorEdit = this.zze.zzf().edit();
        editorEdit.putLong(this.zza, j9);
        editorEdit.apply();
        this.zzd = j9;
    }
}
