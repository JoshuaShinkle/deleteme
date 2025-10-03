package com.google.android.gms.internal.play_billing;

import java.util.Arrays;

/* loaded from: classes2.dex */
public final class zzw {
    Object[] zza = new Object[8];
    int zzb = 0;
    zzv zzc;

    public final zzw zza(Object obj, Object obj2) {
        int i9 = this.zzb + 1;
        Object[] objArr = this.zza;
        int length = objArr.length;
        int i10 = i9 + i9;
        if (i10 > length) {
            int i11 = length + (length >> 1) + 1;
            if (i11 < i10) {
                int iHighestOneBit = Integer.highestOneBit(i10 - 1);
                i11 = iHighestOneBit + iHighestOneBit;
            }
            if (i11 < 0) {
                i11 = Integer.MAX_VALUE;
            }
            this.zza = Arrays.copyOf(objArr, i11);
        }
        zzp.zza(obj, obj2);
        Object[] objArr2 = this.zza;
        int i12 = this.zzb;
        int i13 = i12 + i12;
        objArr2[i13] = obj;
        objArr2[i13 + 1] = obj2;
        this.zzb = i12 + 1;
        return this;
    }

    public final zzx zzb() {
        zzv zzvVar = this.zzc;
        if (zzvVar != null) {
            throw zzvVar.zza();
        }
        zzaf zzafVarZzf = zzaf.zzf(this.zzb, this.zza, this);
        zzv zzvVar2 = this.zzc;
        if (zzvVar2 == null) {
            return zzafVarZzf;
        }
        throw zzvVar2.zza();
    }
}
