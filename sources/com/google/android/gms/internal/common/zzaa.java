package com.google.android.gms.internal.common;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Arrays;

/* loaded from: classes2.dex */
class zzaa extends zzab {
    Object[] zza = new Object[4];
    int zzb = 0;
    boolean zzc;

    public zzaa(int i9) {
    }

    private final void zzb(int i9) {
        Object[] objArr = this.zza;
        int length = objArr.length;
        if (length >= i9) {
            if (this.zzc) {
                this.zza = (Object[]) objArr.clone();
                this.zzc = false;
                return;
            }
            return;
        }
        int i10 = length + (length >> 1) + 1;
        if (i10 < i9) {
            int iHighestOneBit = Integer.highestOneBit(i9 - 1);
            i10 = iHighestOneBit + iHighestOneBit;
        }
        if (i10 < 0) {
            i10 = Integer.MAX_VALUE;
        }
        this.zza = Arrays.copyOf(objArr, i10);
        this.zzc = false;
    }

    @CanIgnoreReturnValue
    public final zzaa zza(Object obj) {
        obj.getClass();
        zzb(this.zzb + 1);
        Object[] objArr = this.zza;
        int i9 = this.zzb;
        this.zzb = i9 + 1;
        objArr[i9] = obj;
        return this;
    }
}
