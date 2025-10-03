package com.google.android.gms.internal.measurement;

import java.util.Iterator;
import java.util.Map;

/* loaded from: classes2.dex */
final class zzki implements Iterator {
    private int zza;
    private boolean zzb;
    private Iterator zzc;
    private final /* synthetic */ zzka zzd;

    private zzki(zzka zzkaVar) {
        this.zzd = zzkaVar;
        this.zza = -1;
    }

    private final Iterator zza() {
        if (this.zzc == null) {
            this.zzc = this.zzd.zzc.entrySet().iterator();
        }
        return this.zzc;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.zza + 1 < this.zzd.zzb.size() || (!this.zzd.zzc.isEmpty() && zza().hasNext());
    }

    @Override // java.util.Iterator
    public final /* synthetic */ Object next() {
        this.zzb = true;
        int i9 = this.zza + 1;
        this.zza = i9;
        return i9 < this.zzd.zzb.size() ? (Map.Entry) this.zzd.zzb.get(this.zza) : (Map.Entry) zza().next();
    }

    @Override // java.util.Iterator
    public final void remove() {
        if (!this.zzb) {
            throw new IllegalStateException("remove() was called before next()");
        }
        this.zzb = false;
        this.zzd.zzf();
        if (this.zza >= this.zzd.zzb.size()) {
            zza().remove();
            return;
        }
        zzka zzkaVar = this.zzd;
        int i9 = this.zza;
        this.zza = i9 - 1;
        zzkaVar.zzc(i9);
    }

    public /* synthetic */ zzki(zzka zzkaVar, zzjz zzjzVar) {
        this(zzkaVar);
    }
}
