package com.google.android.gms.internal.measurement;

import java.util.Iterator;

/* loaded from: classes2.dex */
final class zzku implements Iterator<String> {
    private Iterator<String> zza;
    private final /* synthetic */ zzks zzb;

    public zzku(zzks zzksVar) {
        this.zzb = zzksVar;
        this.zza = zzksVar.zza.iterator();
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.zza.hasNext();
    }

    @Override // java.util.Iterator
    public final /* synthetic */ String next() {
        return this.zza.next();
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException();
    }
}
