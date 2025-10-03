package com.google.android.gms.measurement.internal;

import android.content.SharedPreferences;
import android.util.Pair;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;

/* loaded from: classes2.dex */
public final class zzfm {

    @VisibleForTesting
    private final String zza;
    private final String zzb;
    private final String zzc;
    private final long zzd;
    private final /* synthetic */ zzfj zze;

    private zzfm(zzfj zzfjVar, String str, long j9) {
        this.zze = zzfjVar;
        Preconditions.checkNotEmpty(str);
        Preconditions.checkArgument(j9 > 0);
        this.zza = String.valueOf(str).concat(":start");
        this.zzb = String.valueOf(str).concat(":count");
        this.zzc = String.valueOf(str).concat(":value");
        this.zzd = j9;
    }

    private final void zzb() {
        this.zze.zzc();
        long jCurrentTimeMillis = this.zze.zzl().currentTimeMillis();
        SharedPreferences.Editor editorEdit = this.zze.zzf().edit();
        editorEdit.remove(this.zzb);
        editorEdit.remove(this.zzc);
        editorEdit.putLong(this.zza, jCurrentTimeMillis);
        editorEdit.apply();
    }

    private final long zzc() {
        return this.zze.zzf().getLong(this.zza, 0L);
    }

    public final void zza(String str, long j9) {
        this.zze.zzc();
        if (zzc() == 0) {
            zzb();
        }
        if (str == null) {
            str = "";
        }
        long j10 = this.zze.zzf().getLong(this.zzb, 0L);
        if (j10 <= 0) {
            SharedPreferences.Editor editorEdit = this.zze.zzf().edit();
            editorEdit.putString(this.zzc, str);
            editorEdit.putLong(this.zzb, 1L);
            editorEdit.apply();
            return;
        }
        long j11 = j10 + 1;
        boolean z8 = (this.zze.zzo().zzg().nextLong() & Long.MAX_VALUE) < Long.MAX_VALUE / j11;
        SharedPreferences.Editor editorEdit2 = this.zze.zzf().edit();
        if (z8) {
            editorEdit2.putString(this.zzc, str);
        }
        editorEdit2.putLong(this.zzb, j11);
        editorEdit2.apply();
    }

    public final Pair<String, Long> zza() {
        long jAbs;
        this.zze.zzc();
        this.zze.zzc();
        long jZzc = zzc();
        if (jZzc == 0) {
            zzb();
            jAbs = 0;
        } else {
            jAbs = Math.abs(jZzc - this.zze.zzl().currentTimeMillis());
        }
        long j9 = this.zzd;
        if (jAbs < j9) {
            return null;
        }
        if (jAbs > (j9 << 1)) {
            zzb();
            return null;
        }
        String string = this.zze.zzf().getString(this.zzc, null);
        long j10 = this.zze.zzf().getLong(this.zzb, 0L);
        zzb();
        if (string != null && j10 > 0) {
            return new Pair<>(string, Long.valueOf(j10));
        }
        return zzfj.zza;
    }
}
