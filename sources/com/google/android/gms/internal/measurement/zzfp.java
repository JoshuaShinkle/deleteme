package com.google.android.gms.internal.measurement;

import java.util.Iterator;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* loaded from: classes2.dex */
final class zzfp<K> extends zzff<K> {
    private final transient zzfb<K, ?> zza;
    private final transient zzew<K> zzb;

    public zzfp(zzfb<K, ?> zzfbVar, zzew<K> zzewVar) {
        this.zza = zzfbVar;
        this.zzb = zzewVar;
    }

    @Override // com.google.android.gms.internal.measurement.zzex, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean contains(@NullableDecl Object obj) {
        return this.zza.get(obj) != null;
    }

    @Override // com.google.android.gms.internal.measurement.zzff, com.google.android.gms.internal.measurement.zzex, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
    public final /* synthetic */ Iterator iterator() {
        return iterator();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return this.zza.size();
    }

    @Override // com.google.android.gms.internal.measurement.zzex
    /* renamed from: zzb */
    public final zzfs<K> iterator() {
        return (zzfs) zzc().iterator();
    }

    @Override // com.google.android.gms.internal.measurement.zzff, com.google.android.gms.internal.measurement.zzex
    public final zzew<K> zzc() {
        return this.zzb;
    }

    @Override // com.google.android.gms.internal.measurement.zzex
    public final boolean zzg() {
        return true;
    }

    @Override // com.google.android.gms.internal.measurement.zzex
    public final int zzb(Object[] objArr, int i9) {
        return zzc().zzb(objArr, i9);
    }
}
