package com.google.android.gms.internal.measurement;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.RandomAccess;

/* loaded from: classes2.dex */
public final class zzio extends zzgg<String> implements zzin, RandomAccess {
    private static final zzio zza;
    private static final zzin zzb;
    private final List<Object> zzc;

    static {
        zzio zzioVar = new zzio();
        zza = zzioVar;
        zzioVar.mo17534i_();
        zzb = zzioVar;
    }

    public zzio() {
        this(10);
    }

    @Override // com.google.android.gms.internal.measurement.zzgg, java.util.AbstractList, java.util.List
    public final /* synthetic */ void add(int i9, Object obj) {
        zzc();
        this.zzc.add(i9, (String) obj);
        ((AbstractList) this).modCount++;
    }

    @Override // com.google.android.gms.internal.measurement.zzgg, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean addAll(Collection<? extends String> collection) {
        return addAll(size(), collection);
    }

    @Override // com.google.android.gms.internal.measurement.zzgg, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final void clear() {
        zzc();
        this.zzc.clear();
        ((AbstractList) this).modCount++;
    }

    @Override // com.google.android.gms.internal.measurement.zzgg, java.util.AbstractList, java.util.Collection, java.util.List
    public final /* bridge */ /* synthetic */ boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override // java.util.AbstractList, java.util.List
    public final /* synthetic */ Object get(int i9) {
        Object obj = this.zzc.get(i9);
        if (obj instanceof String) {
            return (String) obj;
        }
        if (obj instanceof zzgm) {
            zzgm zzgmVar = (zzgm) obj;
            String strZzb = zzgmVar.zzb();
            if (zzgmVar.zzc()) {
                this.zzc.set(i9, strZzb);
            }
            return strZzb;
        }
        byte[] bArr = (byte[]) obj;
        String strZzb2 = zzhx.zzb(bArr);
        if (zzhx.zza(bArr)) {
            this.zzc.set(i9, strZzb2);
        }
        return strZzb2;
    }

    @Override // com.google.android.gms.internal.measurement.zzin
    /* renamed from: h_ */
    public final zzin mo17535h_() {
        return zza() ? new zzks(this) : this;
    }

    @Override // com.google.android.gms.internal.measurement.zzgg, java.util.AbstractList, java.util.Collection, java.util.List
    public final /* bridge */ /* synthetic */ int hashCode() {
        return super.hashCode();
    }

    @Override // com.google.android.gms.internal.measurement.zzgg, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final /* bridge */ /* synthetic */ boolean remove(Object obj) {
        return super.remove(obj);
    }

    @Override // com.google.android.gms.internal.measurement.zzgg, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final /* bridge */ /* synthetic */ boolean removeAll(Collection collection) {
        return super.removeAll(collection);
    }

    @Override // com.google.android.gms.internal.measurement.zzgg, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final /* bridge */ /* synthetic */ boolean retainAll(Collection collection) {
        return super.retainAll(collection);
    }

    @Override // com.google.android.gms.internal.measurement.zzgg, java.util.AbstractList, java.util.List
    public final /* synthetic */ Object set(int i9, Object obj) {
        zzc();
        return zza(this.zzc.set(i9, (String) obj));
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zzc.size();
    }

    @Override // com.google.android.gms.internal.measurement.zzin
    public final void zza(zzgm zzgmVar) {
        zzc();
        this.zzc.add(zzgmVar);
        ((AbstractList) this).modCount++;
    }

    @Override // com.google.android.gms.internal.measurement.zzin
    public final Object zzb(int i9) {
        return this.zzc.get(i9);
    }

    public zzio(int i9) {
        this((ArrayList<Object>) new ArrayList(i9));
    }

    @Override // com.google.android.gms.internal.measurement.zzgg, java.util.AbstractList, java.util.List
    public final boolean addAll(int i9, Collection<? extends String> collection) {
        zzc();
        if (collection instanceof zzin) {
            collection = ((zzin) collection).zzb();
        }
        boolean zAddAll = this.zzc.addAll(i9, collection);
        ((AbstractList) this).modCount++;
        return zAddAll;
    }

    @Override // com.google.android.gms.internal.measurement.zzgg, java.util.AbstractList, java.util.List
    public final /* synthetic */ Object remove(int i9) {
        zzc();
        Object objRemove = this.zzc.remove(i9);
        ((AbstractList) this).modCount++;
        return zza(objRemove);
    }

    @Override // com.google.android.gms.internal.measurement.zzin
    public final List<?> zzb() {
        return Collections.unmodifiableList(this.zzc);
    }

    private zzio(ArrayList<Object> arrayList) {
        this.zzc = arrayList;
    }

    private static String zza(Object obj) {
        if (obj instanceof String) {
            return (String) obj;
        }
        if (obj instanceof zzgm) {
            return ((zzgm) obj).zzb();
        }
        return zzhx.zzb((byte[]) obj);
    }

    @Override // com.google.android.gms.internal.measurement.zzgg, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final /* bridge */ /* synthetic */ boolean add(Object obj) {
        return super.add(obj);
    }

    @Override // com.google.android.gms.internal.measurement.zzgg, com.google.android.gms.internal.measurement.zzid
    public final /* bridge */ /* synthetic */ boolean zza() {
        return super.zza();
    }

    @Override // com.google.android.gms.internal.measurement.zzid
    public final /* synthetic */ zzid zza(int i9) {
        if (i9 >= size()) {
            ArrayList arrayList = new ArrayList(i9);
            arrayList.addAll(this.zzc);
            return new zzio((ArrayList<Object>) arrayList);
        }
        throw new IllegalArgumentException();
    }
}
