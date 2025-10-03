package com.google.android.gms.internal.play_billing;

import java.util.AbstractList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;

@Deprecated
/* loaded from: classes2.dex */
public final class zzel extends AbstractList implements RandomAccess, zzcn {
    private final zzcn zza;

    public zzel(zzcn zzcnVar) {
        this.zza = zzcnVar;
    }

    @Override // java.util.AbstractList, java.util.List
    public final /* bridge */ /* synthetic */ Object get(int i9) {
        return ((zzcm) this.zza).get(i9);
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
    public final Iterator iterator() {
        return new zzek(this);
    }

    @Override // java.util.AbstractList, java.util.List
    public final ListIterator listIterator(int i9) {
        return new zzej(this, i9);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zza.size();
    }

    @Override // com.google.android.gms.internal.play_billing.zzcn
    public final zzcn zze() {
        return this;
    }

    @Override // com.google.android.gms.internal.play_billing.zzcn
    public final Object zzf(int i9) {
        return this.zza.zzf(i9);
    }

    @Override // com.google.android.gms.internal.play_billing.zzcn
    public final List zzh() {
        return this.zza.zzh();
    }
}
