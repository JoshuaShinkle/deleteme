package com.google.android.gms.internal.common;

/* loaded from: classes2.dex */
final class zzae extends zzz {
    private final zzag zza;

    public zzae(zzag zzagVar, int i9) {
        super(zzagVar.size(), i9);
        this.zza = zzagVar;
    }

    @Override // com.google.android.gms.internal.common.zzz
    public final Object zza(int i9) {
        return this.zza.get(i9);
    }
}
