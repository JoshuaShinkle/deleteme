package com.google.android.gms.common;

import java.util.concurrent.Callable;

/* loaded from: classes2.dex */
final class zzv extends zzx {
    private final Callable zze;

    public /* synthetic */ zzv(Callable callable, zzu zzuVar) {
        super();
        this.zze = callable;
    }

    @Override // com.google.android.gms.common.zzx
    public final String zza() {
        try {
            return (String) this.zze.call();
        } catch (Exception e9) {
            throw new RuntimeException(e9);
        }
    }
}
