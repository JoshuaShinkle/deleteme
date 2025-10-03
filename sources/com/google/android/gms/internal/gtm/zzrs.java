package com.google.android.gms.internal.gtm;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.RandomAccess;

/* loaded from: classes2.dex */
public final class zzrs extends zzpo<String> implements zzrt, RandomAccess {
    private static final zzrs zzbce;
    private static final zzrt zzbcf;
    private final List<Object> zzbcg;

    static {
        zzrs zzrsVar = new zzrs();
        zzbce = zzrsVar;
        zzrsVar.zzmi();
        zzbcf = zzrsVar;
    }

    public zzrs() {
        this(10);
    }

    private static String zzv(Object obj) {
        return obj instanceof String ? (String) obj : obj instanceof zzps ? ((zzps) obj).zznc() : zzre.zzj((byte[]) obj);
    }

    @Override // com.google.android.gms.internal.gtm.zzpo, java.util.AbstractList, java.util.List
    public final /* synthetic */ void add(int i9, Object obj) {
        zzmz();
        this.zzbcg.add(i9, (String) obj);
        ((AbstractList) this).modCount++;
    }

    @Override // com.google.android.gms.internal.gtm.zzpo, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean addAll(Collection<? extends String> collection) {
        return addAll(size(), collection);
    }

    @Override // com.google.android.gms.internal.gtm.zzpo, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final void clear() {
        zzmz();
        this.zzbcg.clear();
        ((AbstractList) this).modCount++;
    }

    @Override // com.google.android.gms.internal.gtm.zzpo, java.util.AbstractList, java.util.Collection, java.util.List
    public final /* bridge */ /* synthetic */ boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override // java.util.AbstractList, java.util.List
    public final /* synthetic */ Object get(int i9) {
        Object obj = this.zzbcg.get(i9);
        if (obj instanceof String) {
            return (String) obj;
        }
        if (obj instanceof zzps) {
            zzps zzpsVar = (zzps) obj;
            String strZznc = zzpsVar.zznc();
            if (zzpsVar.zznd()) {
                this.zzbcg.set(i9, strZznc);
            }
            return strZznc;
        }
        byte[] bArr = (byte[]) obj;
        String strZzj = zzre.zzj(bArr);
        if (zzre.zzi(bArr)) {
            this.zzbcg.set(i9, strZzj);
        }
        return strZzj;
    }

    @Override // com.google.android.gms.internal.gtm.zzpo, java.util.AbstractList, java.util.Collection, java.util.List
    public final /* bridge */ /* synthetic */ int hashCode() {
        return super.hashCode();
    }

    @Override // com.google.android.gms.internal.gtm.zzpo, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final /* bridge */ /* synthetic */ boolean remove(Object obj) {
        return super.remove(obj);
    }

    @Override // com.google.android.gms.internal.gtm.zzpo, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final /* bridge */ /* synthetic */ boolean removeAll(Collection collection) {
        return super.removeAll(collection);
    }

    @Override // com.google.android.gms.internal.gtm.zzpo, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final /* bridge */ /* synthetic */ boolean retainAll(Collection collection) {
        return super.retainAll(collection);
    }

    @Override // com.google.android.gms.internal.gtm.zzpo, java.util.AbstractList, java.util.List
    public final /* synthetic */ Object set(int i9, Object obj) {
        zzmz();
        return zzv(this.zzbcg.set(i9, (String) obj));
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zzbcg.size();
    }

    @Override // com.google.android.gms.internal.gtm.zzrj
    public final /* synthetic */ zzrj zzaj(int i9) {
        if (i9 < size()) {
            throw new IllegalArgumentException();
        }
        ArrayList arrayList = new ArrayList(i9);
        arrayList.addAll(this.zzbcg);
        return new zzrs((ArrayList<Object>) arrayList);
    }

    @Override // com.google.android.gms.internal.gtm.zzrt
    public final Object zzbn(int i9) {
        return this.zzbcg.get(i9);
    }

    @Override // com.google.android.gms.internal.gtm.zzrt
    public final void zzc(zzps zzpsVar) {
        zzmz();
        this.zzbcg.add(zzpsVar);
        ((AbstractList) this).modCount++;
    }

    @Override // com.google.android.gms.internal.gtm.zzpo, com.google.android.gms.internal.gtm.zzrj
    public final /* bridge */ /* synthetic */ boolean zzmy() {
        return super.zzmy();
    }

    @Override // com.google.android.gms.internal.gtm.zzrt
    public final List<?> zzqa() {
        return Collections.unmodifiableList(this.zzbcg);
    }

    @Override // com.google.android.gms.internal.gtm.zzrt
    public final zzrt zzqb() {
        return zzmy() ? new zztu(this) : this;
    }

    public zzrs(int i9) {
        this((ArrayList<Object>) new ArrayList(i9));
    }

    @Override // com.google.android.gms.internal.gtm.zzpo, java.util.AbstractList, java.util.List
    public final boolean addAll(int i9, Collection<? extends String> collection) {
        zzmz();
        if (collection instanceof zzrt) {
            collection = ((zzrt) collection).zzqa();
        }
        boolean zAddAll = this.zzbcg.addAll(i9, collection);
        ((AbstractList) this).modCount++;
        return zAddAll;
    }

    @Override // com.google.android.gms.internal.gtm.zzpo, java.util.AbstractList, java.util.List
    public final /* synthetic */ Object remove(int i9) {
        zzmz();
        Object objRemove = this.zzbcg.remove(i9);
        ((AbstractList) this).modCount++;
        return zzv(objRemove);
    }

    private zzrs(ArrayList<Object> arrayList) {
        this.zzbcg = arrayList;
    }
}
