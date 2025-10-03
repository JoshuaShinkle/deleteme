package com.google.android.gms.internal.measurement;

import java.util.NoSuchElementException;

/* loaded from: classes2.dex */
final class zzfg extends zzfs {
    private boolean zza;
    private final /* synthetic */ Object zzb;

    public zzfg(Object obj) {
        this.zzb = obj;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return !this.zza;
    }

    @Override // java.util.Iterator
    public final Object next() {
        if (this.zza) {
            throw new NoSuchElementException();
        }
        this.zza = true;
        return this.zzb;
    }
}
