package com.google.android.gms.internal.measurement;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.AbstractCollection;
import java.util.Arrays;
import java.util.Collection;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* loaded from: classes2.dex */
public abstract class zzex<E> extends AbstractCollection<E> implements Serializable {
    private static final Object[] zza = new Object[0];

    @Override // java.util.AbstractCollection, java.util.Collection
    @Deprecated
    public final boolean add(E e9) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    @Deprecated
    public final boolean addAll(Collection<? extends E> collection) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    @Deprecated
    public final void clear() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public abstract boolean contains(@NullableDecl Object obj);

    @Override // java.util.AbstractCollection, java.util.Collection
    @Deprecated
    public final boolean remove(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    @Deprecated
    public final boolean removeAll(Collection<?> collection) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    @Deprecated
    public final boolean retainAll(Collection<?> collection) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final Object[] toArray() {
        return toArray(zza);
    }

    public int zzb(Object[] objArr, int i9) {
        zzfs zzfsVar = (zzfs) iterator();
        while (zzfsVar.hasNext()) {
            objArr[i9] = zzfsVar.next();
            i9++;
        }
        return i9;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
    /* renamed from: zzb, reason: merged with bridge method [inline-methods] */
    public abstract zzfs<E> iterator();

    public zzew<E> zzc() {
        return isEmpty() ? zzew.zza() : zzew.zza(toArray());
    }

    @NullableDecl
    public Object[] zzd() {
        return null;
    }

    int zze() {
        throw new UnsupportedOperationException();
    }

    public int zzf() {
        throw new UnsupportedOperationException();
    }

    public abstract boolean zzg();

    @Override // java.util.AbstractCollection, java.util.Collection
    public final <T> T[] toArray(T[] tArr) {
        zzdw.zza(tArr);
        int size = size();
        if (tArr.length < size) {
            Object[] objArrZzd = zzd();
            if (objArrZzd != null) {
                return (T[]) Arrays.copyOfRange(objArrZzd, zze(), zzf(), tArr.getClass());
            }
            tArr = (T[]) ((Object[]) Array.newInstance(tArr.getClass().getComponentType(), size));
        } else if (tArr.length > size) {
            tArr[size] = null;
        }
        zzb(tArr, 0);
        return tArr;
    }
}
