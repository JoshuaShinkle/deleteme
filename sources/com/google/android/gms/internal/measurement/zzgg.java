package com.google.android.gms.internal.measurement;

import java.util.AbstractList;
import java.util.Collection;
import java.util.List;
import java.util.RandomAccess;

/* loaded from: classes2.dex */
abstract class zzgg<E> extends AbstractList<E> implements zzid<E> {
    private boolean zza = true;

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean add(E e9) {
        zzc();
        return super.add(e9);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean addAll(Collection<? extends E> collection) {
        zzc();
        return super.addAll(collection);
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public void clear() {
        zzc();
        super.clear();
    }

    @Override // java.util.AbstractList, java.util.Collection, java.util.List
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof List)) {
            return false;
        }
        if (!(obj instanceof RandomAccess)) {
            return super.equals(obj);
        }
        List list = (List) obj;
        int size = size();
        if (size != list.size()) {
            return false;
        }
        for (int i9 = 0; i9 < size; i9++) {
            if (!get(i9).equals(list.get(i9))) {
                return false;
            }
        }
        return true;
    }

    @Override // java.util.AbstractList, java.util.Collection, java.util.List
    public int hashCode() {
        int size = size();
        int iHashCode = 1;
        for (int i9 = 0; i9 < size; i9++) {
            iHashCode = (iHashCode * 31) + get(i9).hashCode();
        }
        return iHashCode;
    }

    @Override // com.google.android.gms.internal.measurement.zzid
    /* renamed from: i_ */
    public final void mo17534i_() {
        this.zza = false;
    }

    @Override // java.util.AbstractList, java.util.List
    public E remove(int i9) {
        zzc();
        return (E) super.remove(i9);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean removeAll(Collection<?> collection) {
        zzc();
        return super.removeAll(collection);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean retainAll(Collection<?> collection) {
        zzc();
        return super.retainAll(collection);
    }

    @Override // java.util.AbstractList, java.util.List
    public E set(int i9, E e9) {
        zzc();
        return (E) super.set(i9, e9);
    }

    @Override // com.google.android.gms.internal.measurement.zzid
    public boolean zza() {
        return this.zza;
    }

    public final void zzc() {
        if (!this.zza) {
            throw new UnsupportedOperationException();
        }
    }

    @Override // java.util.AbstractList, java.util.List
    public void add(int i9, E e9) {
        zzc();
        super.add(i9, e9);
    }

    @Override // java.util.AbstractList, java.util.List
    public boolean addAll(int i9, Collection<? extends E> collection) {
        zzc();
        return super.addAll(i9, collection);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean remove(Object obj) {
        zzc();
        return super.remove(obj);
    }
}
