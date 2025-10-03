package com.google.android.gms.internal.play_billing;

import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.List;

/* loaded from: classes2.dex */
final class zzt extends zzu {
    final transient int zza;
    final transient int zzb;
    final /* synthetic */ zzu zzc;

    public zzt(zzu zzuVar, int i9, int i10) {
        this.zzc = zzuVar;
        this.zza = i9;
        this.zzb = i10;
    }

    @Override // java.util.List
    public final Object get(int i9) {
        zzm.zza(i9, this.zzb, FirebaseAnalytics.Param.INDEX);
        return this.zzc.get(i9 + this.zza);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zzb;
    }

    @Override // com.google.android.gms.internal.play_billing.zzu, java.util.List
    public final /* bridge */ /* synthetic */ List subList(int i9, int i10) {
        return subList(i9, i10);
    }

    @Override // com.google.android.gms.internal.play_billing.zzr
    public final int zzb() {
        return this.zzc.zzc() + this.zza + this.zzb;
    }

    @Override // com.google.android.gms.internal.play_billing.zzr
    public final int zzc() {
        return this.zzc.zzc() + this.zza;
    }

    @Override // com.google.android.gms.internal.play_billing.zzr
    public final boolean zzf() {
        return true;
    }

    @Override // com.google.android.gms.internal.play_billing.zzr
    public final Object[] zzg() {
        return this.zzc.zzg();
    }

    @Override // com.google.android.gms.internal.play_billing.zzu
    /* renamed from: zzh */
    public final zzu subList(int i9, int i10) {
        zzm.zzd(i9, i10, this.zzb);
        zzu zzuVar = this.zzc;
        int i11 = this.zza;
        return zzuVar.subList(i9 + i11, i10 + i11);
    }
}
