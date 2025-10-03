package com.google.android.gms.internal.p260authapi;

import com.google.android.gms.auth.api.credentials.Credential;
import com.google.android.gms.common.api.Status;

/* loaded from: classes2.dex */
final class zzl extends zzh {
    private final /* synthetic */ zzi zzap;

    public zzl(zzi zziVar) {
        this.zzap = zziVar;
    }

    @Override // com.google.android.gms.internal.p260authapi.zzh, com.google.android.gms.internal.p260authapi.zzv
    public final void zzc(Status status, Credential credential) {
        this.zzap.setResult((zzi) new zzg(status, credential));
    }

    @Override // com.google.android.gms.internal.p260authapi.zzh, com.google.android.gms.internal.p260authapi.zzv
    public final void zzd(Status status) {
        this.zzap.setResult((zzi) zzg.zzc(status));
    }
}
