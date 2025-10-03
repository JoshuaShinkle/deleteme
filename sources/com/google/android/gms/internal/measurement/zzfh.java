package com.google.android.gms.internal.measurement;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;

/* loaded from: classes2.dex */
public final class zzfh<K, V> extends zzfc<K, V> {
    /* JADX WARN: Removed duplicated region for block: B:16:0x004a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final zzfe<K, V> zza() {
        zzex zzftVar;
        Set<Map.Entry<K, Collection<V>>> setEntrySet = this.zza.entrySet();
        if (setEntrySet.isEmpty()) {
            return zzev.zza;
        }
        zzfa zzfaVar = new zzfa(setEntrySet.size());
        Iterator<Map.Entry<K, Collection<V>>> it = setEntrySet.iterator();
        int size = 0;
        while (true) {
            int i9 = 1;
            if (!it.hasNext()) {
                zzfaVar.zzc = true;
                return new zzfe<>(zzfk.zza(zzfaVar.zzb, zzfaVar.zza), size, null);
            }
            Map.Entry<K, Collection<V>> next = it.next();
            K key = next.getKey();
            Collection<V> value = next.getValue();
            if (!(value instanceof zzff) || (value instanceof SortedSet)) {
                Object[] array = value.toArray();
                int length = array.length;
                while (true) {
                    if (length == 0) {
                        zzftVar = zzfr.zza;
                        break;
                    }
                    if (length == i9) {
                        zzftVar = new zzft(array[0]);
                        break;
                    }
                    int iZza = zzff.zza(length);
                    Object[] objArr = new Object[iZza];
                    int i10 = iZza - 1;
                    int i11 = 0;
                    int i12 = 0;
                    for (int i13 = 0; i13 < length; i13++) {
                        Object objZza = zzfi.zza(array[i13], i13);
                        int iHashCode = objZza.hashCode();
                        int iZza2 = zzeu.zza(iHashCode);
                        while (true) {
                            int i14 = iZza2 & i10;
                            Object obj = objArr[i14];
                            if (obj == null) {
                                array[i11] = objZza;
                                objArr[i14] = objZza;
                                i12 += iHashCode;
                                i11++;
                                break;
                            }
                            if (!obj.equals(objZza)) {
                                iZza2++;
                            }
                        }
                    }
                    Arrays.fill(array, i11, length, (Object) null);
                    if (i11 == 1) {
                        zzftVar = new zzft(array[0], i12);
                        break;
                    }
                    if (zzff.zza(i11) < iZza / 2) {
                        length = i11;
                        i9 = 1;
                    } else {
                        int length2 = array.length;
                        if (i11 < (length2 >> 1) + (length2 >> 2)) {
                            array = Arrays.copyOf(array, i11);
                        }
                        zzftVar = new zzfr(array, i12, objArr, i10, i11);
                    }
                }
            } else {
                zzftVar = (zzff) value;
                if (zzftVar.zzg()) {
                }
            }
            if (!zzftVar.isEmpty()) {
                int i15 = (zzfaVar.zzb + 1) << 1;
                Object[] objArr2 = zzfaVar.zza;
                if (i15 > objArr2.length) {
                    int length3 = objArr2.length;
                    if (i15 < 0) {
                        throw new AssertionError("cannot store more than MAX_VALUE elements");
                    }
                    int iHighestOneBit = length3 + (length3 >> 1) + 1;
                    if (iHighestOneBit < i15) {
                        iHighestOneBit = Integer.highestOneBit(i15 - 1) << 1;
                    }
                    if (iHighestOneBit < 0) {
                        iHighestOneBit = Integer.MAX_VALUE;
                    }
                    zzfaVar.zza = Arrays.copyOf(objArr2, iHighestOneBit);
                    zzfaVar.zzc = false;
                }
                zzei.zza(key, zzftVar);
                Object[] objArr3 = zzfaVar.zza;
                int i16 = zzfaVar.zzb;
                objArr3[i16 * 2] = key;
                objArr3[(i16 * 2) + 1] = zzftVar;
                zzfaVar.zzb = i16 + 1;
                size += zzftVar.size();
            }
        }
    }
}
