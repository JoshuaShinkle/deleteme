package com.google.android.gms.internal.gtm;

import java.util.Iterator;
import java.util.Map;

/* loaded from: classes2.dex */
final class zzsg implements zzsf {
    @Override // com.google.android.gms.internal.gtm.zzsf
    public final Object zzaa(Object obj) {
        ((zzse) obj).zzmi();
        return obj;
    }

    @Override // com.google.android.gms.internal.gtm.zzsf
    public final Object zzab(Object obj) {
        return zzse.zzqf().zzqg();
    }

    @Override // com.google.android.gms.internal.gtm.zzsf
    public final zzsd<?, ?> zzac(Object obj) {
        throw new NoSuchMethodError();
    }

    @Override // com.google.android.gms.internal.gtm.zzsf
    public final int zzb(int i9, Object obj, Object obj2) {
        zzse zzseVar = (zzse) obj;
        if (zzseVar.isEmpty()) {
            return 0;
        }
        Iterator it = zzseVar.entrySet().iterator();
        if (!it.hasNext()) {
            return 0;
        }
        Map.Entry entry = (Map.Entry) it.next();
        entry.getKey();
        entry.getValue();
        throw new NoSuchMethodError();
    }

    @Override // com.google.android.gms.internal.gtm.zzsf
    public final Object zzc(Object obj, Object obj2) {
        zzse zzseVarZzqg = (zzse) obj;
        zzse zzseVar = (zzse) obj2;
        if (!zzseVar.isEmpty()) {
            if (!zzseVarZzqg.isMutable()) {
                zzseVarZzqg = zzseVarZzqg.zzqg();
            }
            zzseVarZzqg.zza(zzseVar);
        }
        return zzseVarZzqg;
    }

    @Override // com.google.android.gms.internal.gtm.zzsf
    public final Map<?, ?> zzx(Object obj) {
        return (zzse) obj;
    }

    @Override // com.google.android.gms.internal.gtm.zzsf
    public final Map<?, ?> zzy(Object obj) {
        return (zzse) obj;
    }

    @Override // com.google.android.gms.internal.gtm.zzsf
    public final boolean zzz(Object obj) {
        return !((zzse) obj).isMutable();
    }
}
