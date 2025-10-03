package com.google.android.gms.internal.play_billing;

import com.google.common.primitives.UnsignedBytes;
import java.util.Comparator;

/* loaded from: classes2.dex */
final class zzas implements Comparator {
    @Override // java.util.Comparator
    public final /* synthetic */ int compare(Object obj, Object obj2) {
        zzba zzbaVar = (zzba) obj;
        zzba zzbaVar2 = (zzba) obj2;
        zzar zzarVar = new zzar(zzbaVar);
        zzar zzarVar2 = new zzar(zzbaVar2);
        while (zzarVar.hasNext() && zzarVar2.hasNext()) {
            int iCompareTo = Integer.valueOf(zzarVar.zza() & UnsignedBytes.MAX_VALUE).compareTo(Integer.valueOf(zzarVar2.zza() & UnsignedBytes.MAX_VALUE));
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        return Integer.valueOf(zzbaVar.zzd()).compareTo(Integer.valueOf(zzbaVar2.zzd()));
    }
}
