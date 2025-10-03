package com.google.android.gms.internal.measurement;

import java.util.AbstractCollection;
import java.util.Iterator;

/* loaded from: classes2.dex */
final class zzet extends AbstractCollection {
    private final /* synthetic */ zzel zza;

    public zzet(zzel zzelVar) {
        this.zza = zzelVar;
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final void clear() {
        this.zza.clear();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public final Iterator iterator() {
        return this.zza.zzg();
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final int size() {
        return this.zza.size();
    }
}
