package com.google.android.gms.internal.play_billing;

import java.util.Iterator;
import java.util.Map;

/* loaded from: classes2.dex */
final class zzdy implements Iterator {
    final /* synthetic */ zzec zza;
    private int zzb = -1;
    private boolean zzc;
    private Iterator zzd;

    public /* synthetic */ zzdy(zzec zzecVar, zzdx zzdxVar) {
        this.zza = zzecVar;
    }

    private final Iterator zza() {
        if (this.zzd == null) {
            this.zzd = this.zza.zzc.entrySet().iterator();
        }
        return this.zzd;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        if (this.zzb + 1 >= this.zza.zzb.size()) {
            return !this.zza.zzc.isEmpty() && zza().hasNext();
        }
        return true;
    }

    @Override // java.util.Iterator
    public final /* bridge */ /* synthetic */ Object next() {
        this.zzc = true;
        int i9 = this.zzb + 1;
        this.zzb = i9;
        return i9 < this.zza.zzb.size() ? (Map.Entry) this.zza.zzb.get(this.zzb) : (Map.Entry) zza().next();
    }

    @Override // java.util.Iterator
    public final void remove() {
        if (!this.zzc) {
            throw new IllegalStateException("remove() was called before next()");
        }
        this.zzc = false;
        this.zza.zzn();
        if (this.zzb >= this.zza.zzb.size()) {
            zza().remove();
            return;
        }
        zzec zzecVar = this.zza;
        int i9 = this.zzb;
        this.zzb = i9 - 1;
        zzecVar.zzl(i9);
    }
}
