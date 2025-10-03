package com.google.android.gms.auth.api.accounttransfer;

import com.google.android.gms.auth.api.accounttransfer.AccountTransferClient;
import com.google.android.gms.internal.auth.zzad;
import com.google.android.gms.internal.auth.zzz;

/* loaded from: classes2.dex */
final class zze extends AccountTransferClient.zzb<byte[]> {
    private final /* synthetic */ zzad zzap;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zze(AccountTransferClient accountTransferClient, zzad zzadVar) {
        super(null);
        this.zzap = zzadVar;
    }

    @Override // com.google.android.gms.auth.api.accounttransfer.AccountTransferClient.zzb
    public final void zza(zzz zzzVar) {
        zzzVar.zza(new zzf(this, this), this.zzap);
    }
}
