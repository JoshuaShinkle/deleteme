package com.google.android.gms.internal.measurement;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes2.dex */
final class zzkc implements Iterator {
    private int zza;
    private Iterator zzb;
    private final /* synthetic */ zzka zzc;

    private zzkc(zzka zzkaVar) {
        this.zzc = zzkaVar;
        this.zza = zzkaVar.zzb.size();
    }

    private final Iterator zza() {
        if (this.zzb == null) {
            this.zzb = this.zzc.zzf.entrySet().iterator();
        }
        return this.zzb;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        int i9 = this.zza;
        return (i9 > 0 && i9 <= this.zzc.zzb.size()) || zza().hasNext();
    }

    @Override // java.util.Iterator
    public final /* synthetic */ Object next() {
        if (zza().hasNext()) {
            return (Map.Entry) zza().next();
        }
        List list = this.zzc.zzb;
        int i9 = this.zza - 1;
        this.zza = i9;
        return (Map.Entry) list.get(i9);
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException();
    }

    public /* synthetic */ zzkc(zzka zzkaVar, zzjz zzjzVar) {
        this(zzkaVar);
    }
}
