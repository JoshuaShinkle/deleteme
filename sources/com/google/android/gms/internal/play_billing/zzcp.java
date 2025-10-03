package com.google.android.gms.internal.play_billing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes2.dex */
final class zzcp extends zzct {
    private static final Class zza = Collections.unmodifiableList(Collections.emptyList()).getClass();

    private zzcp() {
        super(null);
    }

    public /* synthetic */ zzcp(zzco zzcoVar) {
        super(null);
    }

    @Override // com.google.android.gms.internal.play_billing.zzct
    public final void zza(Object obj, long j9) {
        Object objUnmodifiableList;
        List list = (List) zzeq.zzf(obj, j9);
        if (list instanceof zzcn) {
            objUnmodifiableList = ((zzcn) list).zze();
        } else {
            if (zza.isAssignableFrom(list.getClass())) {
                return;
            }
            if ((list instanceof zzdm) && (list instanceof zzcf)) {
                zzcf zzcfVar = (zzcf) list;
                if (zzcfVar.zzc()) {
                    zzcfVar.zzb();
                    return;
                }
                return;
            }
            objUnmodifiableList = Collections.unmodifiableList(list);
        }
        zzeq.zzs(obj, j9, objUnmodifiableList);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.play_billing.zzct
    public final void zzb(Object obj, Object obj2, long j9) {
        zzcm zzcmVar;
        List list = (List) zzeq.zzf(obj2, j9);
        int size = list.size();
        List listZzd = (List) zzeq.zzf(obj, j9);
        if (listZzd.isEmpty()) {
            listZzd = listZzd instanceof zzcn ? new zzcm(size) : ((listZzd instanceof zzdm) && (listZzd instanceof zzcf)) ? ((zzcf) listZzd).zzd(size) : new ArrayList(size);
            zzeq.zzs(obj, j9, listZzd);
        } else {
            if (zza.isAssignableFrom(listZzd.getClass())) {
                ArrayList arrayList = new ArrayList(listZzd.size() + size);
                arrayList.addAll(listZzd);
                zzeq.zzs(obj, j9, arrayList);
                zzcmVar = arrayList;
            } else if (listZzd instanceof zzel) {
                zzcm zzcmVar2 = new zzcm(listZzd.size() + size);
                zzcmVar2.addAll(zzcmVar2.size(), (zzel) listZzd);
                zzeq.zzs(obj, j9, zzcmVar2);
                zzcmVar = zzcmVar2;
            } else if ((listZzd instanceof zzdm) && (listZzd instanceof zzcf)) {
                zzcf zzcfVar = (zzcf) listZzd;
                if (!zzcfVar.zzc()) {
                    listZzd = zzcfVar.zzd(listZzd.size() + size);
                    zzeq.zzs(obj, j9, listZzd);
                }
            }
            listZzd = zzcmVar;
        }
        int size2 = listZzd.size();
        int size3 = list.size();
        if (size2 > 0 && size3 > 0) {
            listZzd.addAll(list);
        }
        if (size2 > 0) {
            list = listZzd;
        }
        zzeq.zzs(obj, j9, list);
    }
}
