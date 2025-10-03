package com.google.android.gms.internal.play_billing;

import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;

/* loaded from: classes2.dex */
public abstract class zzu extends zzr implements List, RandomAccess {
    private static final zzai zza = new zzs(zzaa.zza, 0);
    public static final /* synthetic */ int zzd = 0;

    public static zzu zzi(Object[] objArr, int i9) {
        return i9 == 0 ? zzaa.zza : new zzaa(objArr, i9);
    }

    public static zzu zzj(Collection collection) {
        if (collection instanceof zzr) {
            zzu zzuVarZzd = ((zzr) collection).zzd();
            if (!zzuVarZzd.zzf()) {
                return zzuVarZzd;
            }
            Object[] array = zzuVarZzd.toArray();
            return zzi(array, array.length);
        }
        Object[] array2 = collection.toArray();
        int length = array2.length;
        for (int i9 = 0; i9 < length; i9++) {
            if (array2[i9] == null) {
                throw new NullPointerException("at index " + i9);
            }
        }
        return zzi(array2, length);
    }

    public static zzu zzk() {
        return zzaa.zza;
    }

    @Override // java.util.List
    @Deprecated
    public final void add(int i9, Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List
    @Deprecated
    public final boolean addAll(int i9, Collection collection) {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.android.gms.internal.play_billing.zzr, java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean contains(Object obj) {
        return indexOf(obj) >= 0;
    }

    @Override // java.util.Collection, java.util.List
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof List) {
            List list = (List) obj;
            int size = size();
            if (size == list.size()) {
                if (list instanceof RandomAccess) {
                    for (int i9 = 0; i9 < size; i9++) {
                        if (zzl.zza(get(i9), list.get(i9))) {
                        }
                    }
                    return true;
                }
                Iterator it = iterator();
                Iterator it2 = list.iterator();
                while (true) {
                    if (it.hasNext()) {
                        if (!it2.hasNext() || !zzl.zza(it.next(), it2.next())) {
                            break;
                        }
                    } else if (!it2.hasNext()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override // java.util.Collection, java.util.List
    public final int hashCode() {
        int size = size();
        int iHashCode = 1;
        for (int i9 = 0; i9 < size; i9++) {
            iHashCode = (iHashCode * 31) + get(i9).hashCode();
        }
        return iHashCode;
    }

    @Override // java.util.List
    public final int indexOf(Object obj) {
        if (obj == null) {
            return -1;
        }
        int size = size();
        for (int i9 = 0; i9 < size; i9++) {
            if (obj.equals(get(i9))) {
                return i9;
            }
        }
        return -1;
    }

    @Override // com.google.android.gms.internal.play_billing.zzr, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final /* synthetic */ Iterator iterator() {
        return listIterator(0);
    }

    @Override // java.util.List
    public final int lastIndexOf(Object obj) {
        if (obj == null) {
            return -1;
        }
        for (int size = size() - 1; size >= 0; size--) {
            if (obj.equals(get(size))) {
                return size;
            }
        }
        return -1;
    }

    @Override // java.util.List
    public final /* synthetic */ ListIterator listIterator() {
        return listIterator(0);
    }

    @Override // java.util.List
    @Deprecated
    public final Object remove(int i9) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List
    @Deprecated
    public final Object set(int i9, Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.android.gms.internal.play_billing.zzr
    public int zza(Object[] objArr, int i9) {
        int size = size();
        for (int i10 = 0; i10 < size; i10++) {
            objArr[i10] = get(i10);
        }
        return size;
    }

    @Override // com.google.android.gms.internal.play_billing.zzr
    @Deprecated
    public final zzu zzd() {
        return this;
    }

    @Override // com.google.android.gms.internal.play_billing.zzr
    /* renamed from: zze */
    public final zzah iterator() {
        return listIterator(0);
    }

    @Override // java.util.List
    /* renamed from: zzh, reason: merged with bridge method [inline-methods] */
    public zzu subList(int i9, int i10) {
        zzm.zzd(i9, i10, size());
        int i11 = i10 - i9;
        return i11 == size() ? this : i11 == 0 ? zzaa.zza : new zzt(this, i9, i11);
    }

    @Override // java.util.List
    /* renamed from: zzl, reason: merged with bridge method [inline-methods] */
    public final zzai listIterator(int i9) {
        zzm.zzb(i9, size(), FirebaseAnalytics.Param.INDEX);
        return isEmpty() ? zza : new zzs(this, i9);
    }
}
