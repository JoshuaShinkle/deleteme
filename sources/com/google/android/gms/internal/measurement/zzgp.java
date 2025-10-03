package com.google.android.gms.internal.measurement;

import java.util.NoSuchElementException;

/* loaded from: classes2.dex */
final class zzgp extends zzgr {
    private int zza = 0;
    private final int zzb;
    private final /* synthetic */ zzgm zzc;

    public zzgp(zzgm zzgmVar) {
        this.zzc = zzgmVar;
        this.zzb = zzgmVar.zza();
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.zza < this.zzb;
    }

    @Override // com.google.android.gms.internal.measurement.zzgv
    public final byte zza() {
        int i9 = this.zza;
        if (i9 >= this.zzb) {
            throw new NoSuchElementException();
        }
        this.zza = i9 + 1;
        return this.zzc.zzb(i9);
    }
}
