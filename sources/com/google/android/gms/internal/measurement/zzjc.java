package com.google.android.gms.internal.measurement;

import java.util.Iterator;
import java.util.Map;

/* loaded from: classes2.dex */
final class zzjc implements zziz {
    @Override // com.google.android.gms.internal.measurement.zziz
    public final Map<?, ?> zza(Object obj) {
        return (zzja) obj;
    }

    @Override // com.google.android.gms.internal.measurement.zziz
    public final Map<?, ?> zzb(Object obj) {
        return (zzja) obj;
    }

    @Override // com.google.android.gms.internal.measurement.zziz
    public final boolean zzc(Object obj) {
        return !((zzja) obj).zzd();
    }

    @Override // com.google.android.gms.internal.measurement.zziz
    public final Object zzd(Object obj) {
        ((zzja) obj).zzc();
        return obj;
    }

    @Override // com.google.android.gms.internal.measurement.zziz
    public final Object zze(Object obj) {
        return zzja.zza().zzb();
    }

    @Override // com.google.android.gms.internal.measurement.zziz
    public final zzix<?, ?> zzf(Object obj) {
        throw new NoSuchMethodError();
    }

    @Override // com.google.android.gms.internal.measurement.zziz
    public final Object zza(Object obj, Object obj2) {
        zzja zzjaVarZzb = (zzja) obj;
        zzja zzjaVar = (zzja) obj2;
        if (!zzjaVar.isEmpty()) {
            if (!zzjaVarZzb.zzd()) {
                zzjaVarZzb = zzjaVarZzb.zzb();
            }
            zzjaVarZzb.zza(zzjaVar);
        }
        return zzjaVarZzb;
    }

    @Override // com.google.android.gms.internal.measurement.zziz
    public final int zza(int i9, Object obj, Object obj2) {
        zzja zzjaVar = (zzja) obj;
        if (zzjaVar.isEmpty()) {
            return 0;
        }
        Iterator it = zzjaVar.entrySet().iterator();
        if (!it.hasNext()) {
            return 0;
        }
        Map.Entry entry = (Map.Entry) it.next();
        entry.getKey();
        entry.getValue();
        throw new NoSuchMethodError();
    }
}
