package com.google.android.gms.measurement.internal;

import java.util.Iterator;

/* loaded from: classes2.dex */
final class zzap implements Iterator<String> {
    private Iterator<String> zza;
    private final /* synthetic */ zzam zzb;

    public zzap(zzam zzamVar) {
        this.zzb = zzamVar;
        this.zza = zzamVar.zza.keySet().iterator();
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
        throw new UnsupportedOperationException("Remove not supported");
    }
}
