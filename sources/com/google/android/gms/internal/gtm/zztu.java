package com.google.android.gms.internal.gtm;

import java.util.AbstractList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;

/* loaded from: classes2.dex */
public final class zztu extends AbstractList<String> implements zzrt, RandomAccess {
    private final zzrt zzbeo;

    public zztu(zzrt zzrtVar) {
        this.zzbeo = zzrtVar;
    }

    @Override // java.util.AbstractList, java.util.List
    public final /* synthetic */ Object get(int i9) {
        return (String) this.zzbeo.get(i9);
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
    public final Iterator<String> iterator() {
        return new zztw(this);
    }

    @Override // java.util.AbstractList, java.util.List
    public final ListIterator<String> listIterator(int i9) {
        return new zztv(this, i9);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zzbeo.size();
    }

    @Override // com.google.android.gms.internal.gtm.zzrt
    public final Object zzbn(int i9) {
        return this.zzbeo.zzbn(i9);
    }

    @Override // com.google.android.gms.internal.gtm.zzrt
    public final void zzc(zzps zzpsVar) {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.android.gms.internal.gtm.zzrt
    public final List<?> zzqa() {
        return this.zzbeo.zzqa();
    }

    @Override // com.google.android.gms.internal.gtm.zzrt
    public final zzrt zzqb() {
        return this;
    }
}
