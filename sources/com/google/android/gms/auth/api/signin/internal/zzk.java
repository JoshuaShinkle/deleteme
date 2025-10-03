package com.google.android.gms.auth.api.signin.internal;

import com.google.android.gms.common.api.Status;

/* loaded from: classes2.dex */
final class zzk extends zzd {
    private final /* synthetic */ zzl zzcj;

    public zzk(zzl zzlVar) {
        this.zzcj = zzlVar;
    }

    @Override // com.google.android.gms.auth.api.signin.internal.zzd, com.google.android.gms.auth.api.signin.internal.zzt
    public final void zze(Status status) {
        this.zzcj.setResult((zzl) status);
    }
}
