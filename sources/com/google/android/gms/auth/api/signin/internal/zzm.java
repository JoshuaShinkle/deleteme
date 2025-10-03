package com.google.android.gms.auth.api.signin.internal;

import com.google.android.gms.common.api.Status;

/* loaded from: classes2.dex */
final class zzm extends zzd {
    private final /* synthetic */ zzn zzck;

    public zzm(zzn zznVar) {
        this.zzck = zznVar;
    }

    @Override // com.google.android.gms.auth.api.signin.internal.zzd, com.google.android.gms.auth.api.signin.internal.zzt
    public final void zzf(Status status) {
        this.zzck.setResult((zzn) status);
    }
}
