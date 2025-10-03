package com.google.android.gms.internal.measurement;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* loaded from: classes2.dex */
public abstract class zzew<E> extends zzex<E> implements List<E>, RandomAccess {
    private static final zzfv<Object> zza = new zzez(zzfl.zza, 0);

    public static <E> zzew<E> zza() {
        return (zzew<E>) zzfl.zza;
    }

    @Override // java.util.List
    @Deprecated
    public final void add(int i9, E e9) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List
    @Deprecated
    public final boolean addAll(int i9, Collection<? extends E> collection) {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.android.gms.internal.measurement.zzex, java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean contains(@NullableDecl Object obj) {
        return indexOf(obj) >= 0;
    }

    @Override // java.util.Collection, java.util.List
    public boolean equals(@NullableDecl Object obj) {
        if (obj == zzdw.zza(this)) {
            return true;
        }
        if (obj instanceof List) {
            List list = (List) obj;
            int size = size();
            if (size == list.size()) {
                if (list instanceof RandomAccess) {
                    for (int i9 = 0; i9 < size; i9++) {
                        if (zzdu.zza(get(i9), list.get(i9))) {
                        }
                    }
                    return true;
                }
                int size2 = size();
                Iterator<E> it = list.iterator();
                int i10 = 0;
                while (true) {
                    if (i10 < size2) {
                        if (!it.hasNext()) {
                            break;
                        }
                        E e9 = get(i10);
                        i10++;
                        if (!zzdu.zza(e9, it.next())) {
                            break;
                        }
                    } else if (!it.hasNext()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override // java.util.Collection, java.util.List
    public int hashCode() {
        int size = size();
        int i9 = 1;
        for (int i10 = 0; i10 < size; i10++) {
            i9 = ~(~((i9 * 31) + get(i10).hashCode()));
        }
        return i9;
    }

    @Override // java.util.List
    public int indexOf(@NullableDecl Object obj) {
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

    @Override // com.google.android.gms.internal.measurement.zzex, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
    public /* synthetic */ Iterator iterator() {
        return iterator();
    }

    @Override // java.util.List
    public int lastIndexOf(@NullableDecl Object obj) {
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
    public /* synthetic */ ListIterator listIterator(int i9) {
        zzdw.zzb(i9, size());
        return isEmpty() ? zza : new zzez(this, i9);
    }

    @Override // java.util.List
    @Deprecated
    public final E remove(int i9) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List
    @Deprecated
    public final E set(int i9, E e9) {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.android.gms.internal.measurement.zzex
    /* renamed from: zzb */
    public final zzfs<E> iterator() {
        return (zzfv) listIterator();
    }

    @Override // com.google.android.gms.internal.measurement.zzex
    public final zzew<E> zzc() {
        return this;
    }

    public static <E> zzew<E> zza(E e9) {
        Object[] objArr = {e9};
        for (int i9 = 0; i9 <= 0; i9++) {
            zzfi.zza(objArr[0], 0);
        }
        return zza(objArr, 1);
    }

    @Override // com.google.android.gms.internal.measurement.zzex
    public int zzb(Object[] objArr, int i9) {
        int size = size();
        for (int i10 = 0; i10 < size; i10++) {
            objArr[i9 + i10] = get(i10);
        }
        return i9 + size;
    }

    public static <E> zzew<E> zza(Object[] objArr) {
        return zza(objArr, objArr.length);
    }

    public static <E> zzew<E> zza(Object[] objArr, int i9) {
        if (i9 == 0) {
            return (zzew<E>) zzfl.zza;
        }
        return new zzfl(objArr, i9);
    }

    @Override // java.util.List
    public /* synthetic */ ListIterator listIterator() {
        return (zzfv) listIterator(0);
    }

    @Override // java.util.List
    /* renamed from: zza, reason: merged with bridge method [inline-methods] */
    public zzew<E> subList(int i9, int i10) {
        zzdw.zza(i9, i10, size());
        int i11 = i10 - i9;
        if (i11 == size()) {
            return this;
        }
        if (i11 == 0) {
            return (zzew<E>) zzfl.zza;
        }
        return new zzey(this, i9, i11);
    }
}
