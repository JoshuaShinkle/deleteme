package com.google.android.gms.internal.appinvite;

import com.google.android.gms.common.api.Status;

/* loaded from: classes2.dex */
final class zzj extends zze {
    private final /* synthetic */ zzg zzk;

    public zzj(zzg zzgVar) {
        this.zzk = zzgVar;
    }

    @Override // com.google.android.gms.internal.appinvite.zze, com.google.android.gms.internal.appinvite.zzo
    public final void zza(Status status) {
        this.zzk.setResult((zzg) status);
    }
}
