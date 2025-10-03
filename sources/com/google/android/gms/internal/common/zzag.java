package com.google.android.gms.internal.common;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.DoNotCall;
import com.google.errorprone.annotations.InlineMe;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;
import org.jspecify.nullness.NullMarked;

@NullMarked
/* loaded from: classes2.dex */
public abstract class zzag extends zzac implements List, RandomAccess {
    private static final zzak zza = new zzae(zzai.zza, 0);

    public static zzag zzi(Object[] objArr, int i9) {
        return i9 == 0 ? zzai.zza : new zzai(objArr, i9);
    }

    public static zzag zzj(Iterable iterable) {
        iterable.getClass();
        if (iterable instanceof Collection) {
            return zzk((Collection) iterable);
        }
        Iterator it = iterable.iterator();
        if (!it.hasNext()) {
            return zzai.zza;
        }
        Object next = it.next();
        if (!it.hasNext()) {
            return zzm(next);
        }
        zzad zzadVar = new zzad(4);
        zzadVar.zzb(next);
        zzadVar.zzc(it);
        zzadVar.zzc = true;
        return zzi(zzadVar.zza, zzadVar.zzb);
    }

    public static zzag zzk(Collection collection) {
        if (!(collection instanceof zzac)) {
            Object[] array = collection.toArray();
            int length = array.length;
            zzah.zza(array, length);
            return zzi(array, length);
        }
        zzag zzagVarZzd = ((zzac) collection).zzd();
        if (!zzagVarZzd.zzf()) {
            return zzagVarZzd;
        }
        Object[] array2 = zzagVarZzd.toArray();
        return zzi(array2, array2.length);
    }

    public static zzag zzl() {
        return zzai.zza;
    }

    public static zzag zzm(Object obj) {
        Object[] objArr = {obj};
        zzah.zza(objArr, 1);
        return zzi(objArr, 1);
    }

    public static zzag zzn(Object obj, Object obj2) {
        Object[] objArr = {obj, obj2};
        zzah.zza(objArr, 2);
        return zzi(objArr, 2);
    }

    @Override // java.util.List
    @DoNotCall("Always throws UnsupportedOperationException")
    @Deprecated
    public final void add(int i9, Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List
    @CanIgnoreReturnValue
    @DoNotCall("Always throws UnsupportedOperationException")
    @Deprecated
    public final boolean addAll(int i9, Collection collection) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
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
                        if (zzr.zza(get(i9), list.get(i9))) {
                        }
                    }
                    return true;
                }
                Iterator it = iterator();
                Iterator it2 = list.iterator();
                while (true) {
                    if (it.hasNext()) {
                        if (!it2.hasNext() || !zzr.zza(it.next(), it2.next())) {
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

    @Override // com.google.android.gms.internal.common.zzac, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
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
    @CanIgnoreReturnValue
    @DoNotCall("Always throws UnsupportedOperationException")
    @Deprecated
    public final Object remove(int i9) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List
    @CanIgnoreReturnValue
    @DoNotCall("Always throws UnsupportedOperationException")
    @Deprecated
    public final Object set(int i9, Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.android.gms.internal.common.zzac
    public int zza(Object[] objArr, int i9) {
        int size = size();
        for (int i10 = 0; i10 < size; i10++) {
            objArr[i10] = get(i10);
        }
        return size;
    }

    @Override // com.google.android.gms.internal.common.zzac
    @InlineMe(replacement = "this")
    @Deprecated
    public final zzag zzd() {
        return this;
    }

    @Override // com.google.android.gms.internal.common.zzac
    /* renamed from: zze */
    public final zzaj iterator() {
        return listIterator(0);
    }

    @Override // java.util.List
    /* renamed from: zzh, reason: merged with bridge method [inline-methods] */
    public zzag subList(int i9, int i10) {
        zzs.zzc(i9, i10, size());
        int i11 = i10 - i9;
        return i11 == size() ? this : i11 == 0 ? zzai.zza : new zzaf(this, i9, i11);
    }

    @Override // java.util.List
    /* renamed from: zzo, reason: merged with bridge method [inline-methods] */
    public final zzak listIterator(int i9) {
        zzs.zzb(i9, size(), FirebaseAnalytics.Param.INDEX);
        return isEmpty() ? zza : new zzae(this, i9);
    }
}
