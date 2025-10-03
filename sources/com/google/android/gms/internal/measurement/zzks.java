package com.google.android.gms.internal.measurement;

import java.util.AbstractList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;

/* loaded from: classes2.dex */
public final class zzks extends AbstractList<String> implements zzin, RandomAccess {
    private final zzin zza;

    public zzks(zzin zzinVar) {
        this.zza = zzinVar;
    }

    @Override // java.util.AbstractList, java.util.List
    public final /* synthetic */ Object get(int i9) {
        return (String) this.zza.get(i9);
    }

    @Override // com.google.android.gms.internal.measurement.zzin
    /* renamed from: h_ */
    public final zzin mo17535h_() {
        return this;
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
    public final Iterator<String> iterator() {
        return new zzku(this);
    }

    @Override // java.util.AbstractList, java.util.List
    public final ListIterator<String> listIterator(int i9) {
        return new zzkr(this, i9);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zza.size();
    }

    @Override // com.google.android.gms.internal.measurement.zzin
    public final void zza(zzgm zzgmVar) {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.android.gms.internal.measurement.zzin
    public final Object zzb(int i9) {
        return this.zza.zzb(i9);
    }

    @Override // com.google.android.gms.internal.measurement.zzin
    public final List<?> zzb() {
        return this.zza.zzb();
    }
}
