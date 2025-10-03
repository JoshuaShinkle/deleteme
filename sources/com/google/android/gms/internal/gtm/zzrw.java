package com.google.android.gms.internal.gtm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes2.dex */
final class zzrw extends zzru {
    private static final Class<?> zzbcj = Collections.unmodifiableList(Collections.emptyList()).getClass();

    private zzrw() {
        super();
    }

    private static <E> List<E> zzc(Object obj, long j9) {
        return (List) zztx.zzp(obj, j9);
    }

    @Override // com.google.android.gms.internal.gtm.zzru
    public final <L> List<L> zza(Object obj, long j9) {
        return zza(obj, j9, 10);
    }

    @Override // com.google.android.gms.internal.gtm.zzru
    public final void zzb(Object obj, long j9) {
        Object objUnmodifiableList;
        List list = (List) zztx.zzp(obj, j9);
        if (list instanceof zzrt) {
            objUnmodifiableList = ((zzrt) list).zzqb();
        } else {
            if (zzbcj.isAssignableFrom(list.getClass())) {
                return;
            }
            if ((list instanceof zzsv) && (list instanceof zzrj)) {
                zzrj zzrjVar = (zzrj) list;
                if (zzrjVar.zzmy()) {
                    zzrjVar.zzmi();
                    return;
                }
                return;
            }
            objUnmodifiableList = Collections.unmodifiableList(list);
        }
        zztx.zza(obj, j9, objUnmodifiableList);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static <L> List<L> zza(Object obj, long j9, int i9) {
        zzrs zzrsVar;
        List<L> listZzc = zzc(obj, j9);
        if (listZzc.isEmpty()) {
            List<L> zzrsVar2 = listZzc instanceof zzrt ? new zzrs(i9) : ((listZzc instanceof zzsv) && (listZzc instanceof zzrj)) ? ((zzrj) listZzc).zzaj(i9) : new ArrayList<>(i9);
            zztx.zza(obj, j9, zzrsVar2);
            return zzrsVar2;
        }
        if (zzbcj.isAssignableFrom(listZzc.getClass())) {
            ArrayList arrayList = new ArrayList(listZzc.size() + i9);
            arrayList.addAll(listZzc);
            zztx.zza(obj, j9, arrayList);
            zzrsVar = arrayList;
        } else {
            if (!(listZzc instanceof zztu)) {
                if (!(listZzc instanceof zzsv) || !(listZzc instanceof zzrj)) {
                    return listZzc;
                }
                zzrj zzrjVar = (zzrj) listZzc;
                if (zzrjVar.zzmy()) {
                    return listZzc;
                }
                zzrj zzrjVarZzaj = zzrjVar.zzaj(listZzc.size() + i9);
                zztx.zza(obj, j9, zzrjVarZzaj);
                return zzrjVarZzaj;
            }
            zzrs zzrsVar3 = new zzrs(listZzc.size() + i9);
            zzrsVar3.addAll((zztu) listZzc);
            zztx.zza(obj, j9, zzrsVar3);
            zzrsVar = zzrsVar3;
        }
        return zzrsVar;
    }

    @Override // com.google.android.gms.internal.gtm.zzru
    public final <E> void zza(Object obj, Object obj2, long j9) {
        List listZzc = zzc(obj2, j9);
        List listZza = zza(obj, j9, listZzc.size());
        int size = listZza.size();
        int size2 = listZzc.size();
        if (size > 0 && size2 > 0) {
            listZza.addAll(listZzc);
        }
        if (size > 0) {
            listZzc = listZza;
        }
        zztx.zza(obj, j9, listZzc);
    }
}
