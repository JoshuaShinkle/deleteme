package com.google.android.gms.internal.play_billing;

import java.util.Iterator;

/* loaded from: classes2.dex */
final class zzek implements Iterator {
    final Iterator zza;
    final /* synthetic */ zzel zzb;

    public zzek(zzel zzelVar) {
        this.zzb = zzelVar;
        this.zza = zzelVar.zza.iterator();
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.zza.hasNext();
    }

    @Override // java.util.Iterator
    public final /* bridge */ /* synthetic */ Object next() {
        return (String) this.zza.next();
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException();
    }
}
