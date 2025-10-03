package com.google.android.gms.internal.gtm;

import com.google.android.gms.common.util.VisibleForTesting;

@VisibleForTesting
/* loaded from: classes2.dex */
public final class zzbu extends zzan {
    @VisibleForTesting
    public zzbu(zzap zzapVar) {
        super(zzapVar);
    }

    @Override // com.google.android.gms.internal.gtm.zzan
    public final void zzaw() {
    }

    public final zzv zzfa() {
        zzdb();
        return zzcq().zzau();
    }

    public final String zzfb() {
        zzdb();
        zzv zzvVarZzfa = zzfa();
        int i9 = zzvVarZzfa.zzul;
        int i10 = zzvVarZzfa.zzum;
        StringBuilder sb = new StringBuilder(23);
        sb.append(i9);
        sb.append("x");
        sb.append(i10);
        return sb.toString();
    }
}
