package com.google.android.gms.internal.auth;

import com.google.android.gms.common.api.Status;

/* loaded from: classes2.dex */
final class zzm extends zzn {
    private final /* synthetic */ zzl zzag;

    public zzm(zzl zzlVar) {
        this.zzag = zzlVar;
    }

    @Override // com.google.android.gms.internal.auth.zzn, com.google.android.gms.auth.account.zza
    public final void zza(boolean z8) {
        this.zzag.setResult((zzl) new zzq(z8 ? Status.RESULT_SUCCESS : zzh.zzad));
    }
}
