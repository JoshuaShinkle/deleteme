package com.google.android.gms.internal.measurement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes2.dex */
final class zzis extends zziq {
    private static final Class<?> zza = Collections.unmodifiableList(Collections.emptyList()).getClass();

    private zzis() {
        super();
    }

    private static <E> List<E> zzc(Object obj, long j9) {
        return (List) zzkt.zzf(obj, j9);
    }

    @Override // com.google.android.gms.internal.measurement.zziq
    public final <L> List<L> zza(Object obj, long j9) {
        return zza(obj, j9, 10);
    }

    @Override // com.google.android.gms.internal.measurement.zziq
    public final void zzb(Object obj, long j9) {
        Object objUnmodifiableList;
        List list = (List) zzkt.zzf(obj, j9);
        if (list instanceof zzin) {
            objUnmodifiableList = ((zzin) list).mo17535h_();
        } else {
            if (zza.isAssignableFrom(list.getClass())) {
                return;
            }
            if ((list instanceof zzjs) && (list instanceof zzid)) {
                zzid zzidVar = (zzid) list;
                if (zzidVar.zza()) {
                    zzidVar.mo17534i_();
                    return;
                }
                return;
            }
            objUnmodifiableList = Collections.unmodifiableList(list);
        }
        zzkt.zza(obj, j9, objUnmodifiableList);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static <L> List<L> zza(Object obj, long j9, int i9) {
        zzio zzioVar;
        List<L> listZzc = zzc(obj, j9);
        if (listZzc.isEmpty()) {
            List<L> zzioVar2 = listZzc instanceof zzin ? new zzio(i9) : ((listZzc instanceof zzjs) && (listZzc instanceof zzid)) ? ((zzid) listZzc).zza(i9) : new ArrayList<>(i9);
            zzkt.zza(obj, j9, zzioVar2);
            return zzioVar2;
        }
        if (zza.isAssignableFrom(listZzc.getClass())) {
            ArrayList arrayList = new ArrayList(listZzc.size() + i9);
            arrayList.addAll(listZzc);
            zzkt.zza(obj, j9, arrayList);
            zzioVar = arrayList;
        } else {
            if (!(listZzc instanceof zzks)) {
                if (!(listZzc instanceof zzjs) || !(listZzc instanceof zzid)) {
                    return listZzc;
                }
                zzid zzidVar = (zzid) listZzc;
                if (zzidVar.zza()) {
                    return listZzc;
                }
                zzid zzidVarZza = zzidVar.zza(listZzc.size() + i9);
                zzkt.zza(obj, j9, zzidVarZza);
                return zzidVarZza;
            }
            zzio zzioVar3 = new zzio(listZzc.size() + i9);
            zzioVar3.addAll((zzks) listZzc);
            zzkt.zza(obj, j9, zzioVar3);
            zzioVar = zzioVar3;
        }
        return zzioVar;
    }

    @Override // com.google.android.gms.internal.measurement.zziq
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
        zzkt.zza(obj, j9, listZzc);
    }
}
