package com.google.android.gms.internal.measurement;

import java.util.List;

/* loaded from: classes2.dex */
final class zzir extends zziq {
    private zzir() {
        super();
    }

    private static <E> zzid<E> zzc(Object obj, long j9) {
        return (zzid) zzkt.zzf(obj, j9);
    }

    @Override // com.google.android.gms.internal.measurement.zziq
    public final <L> List<L> zza(Object obj, long j9) {
        zzid zzidVarZzc = zzc(obj, j9);
        if (zzidVarZzc.zza()) {
            return zzidVarZzc;
        }
        int size = zzidVarZzc.size();
        zzid zzidVarZza = zzidVarZzc.zza(size == 0 ? 10 : size << 1);
        zzkt.zza(obj, j9, zzidVarZza);
        return zzidVarZza;
    }

    @Override // com.google.android.gms.internal.measurement.zziq
    public final void zzb(Object obj, long j9) {
        zzc(obj, j9).mo17534i_();
    }

    @Override // com.google.android.gms.internal.measurement.zziq
    public final <E> void zza(Object obj, Object obj2, long j9) {
        zzid zzidVarZzc = zzc(obj, j9);
        zzid zzidVarZzc2 = zzc(obj2, j9);
        int size = zzidVarZzc.size();
        int size2 = zzidVarZzc2.size();
        if (size > 0 && size2 > 0) {
            if (!zzidVarZzc.zza()) {
                zzidVarZzc = zzidVarZzc.zza(size2 + size);
            }
            zzidVarZzc.addAll(zzidVarZzc2);
        }
        if (size > 0) {
            zzidVarZzc2 = zzidVarZzc;
        }
        zzkt.zza(obj, j9, zzidVarZzc2);
    }
}
