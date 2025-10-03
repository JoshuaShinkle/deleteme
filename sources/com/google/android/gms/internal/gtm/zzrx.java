package com.google.android.gms.internal.gtm;

import java.util.List;

/* loaded from: classes2.dex */
final class zzrx extends zzru {
    private zzrx() {
        super();
    }

    private static <E> zzrj<E> zzd(Object obj, long j9) {
        return (zzrj) zztx.zzp(obj, j9);
    }

    @Override // com.google.android.gms.internal.gtm.zzru
    public final <L> List<L> zza(Object obj, long j9) {
        zzrj zzrjVarZzd = zzd(obj, j9);
        if (zzrjVarZzd.zzmy()) {
            return zzrjVarZzd;
        }
        int size = zzrjVarZzd.size();
        zzrj zzrjVarZzaj = zzrjVarZzd.zzaj(size == 0 ? 10 : size << 1);
        zztx.zza(obj, j9, zzrjVarZzaj);
        return zzrjVarZzaj;
    }

    @Override // com.google.android.gms.internal.gtm.zzru
    public final void zzb(Object obj, long j9) {
        zzd(obj, j9).zzmi();
    }

    @Override // com.google.android.gms.internal.gtm.zzru
    public final <E> void zza(Object obj, Object obj2, long j9) {
        zzrj zzrjVarZzd = zzd(obj, j9);
        zzrj zzrjVarZzd2 = zzd(obj2, j9);
        int size = zzrjVarZzd.size();
        int size2 = zzrjVarZzd2.size();
        if (size > 0 && size2 > 0) {
            if (!zzrjVarZzd.zzmy()) {
                zzrjVarZzd = zzrjVarZzd.zzaj(size2 + size);
            }
            zzrjVarZzd.addAll(zzrjVarZzd2);
        }
        if (size > 0) {
            zzrjVarZzd2 = zzrjVarZzd;
        }
        zztx.zza(obj, j9, zzrjVarZzd2);
    }
}
