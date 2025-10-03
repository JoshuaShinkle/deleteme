package com.google.android.gms.internal.gtm;

import java.util.Iterator;
import java.util.Map;

/* loaded from: classes2.dex */
final class zztk implements Iterator {
    private int pos;
    private Iterator zzbee;
    private final /* synthetic */ zztc zzbef;
    private boolean zzbej;

    private zztk(zztc zztcVar) {
        this.zzbef = zztcVar;
        this.pos = -1;
    }

    private final Iterator zzrf() {
        if (this.zzbee == null) {
            this.zzbee = this.zzbef.zzbea.entrySet().iterator();
        }
        return this.zzbee;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.pos + 1 < this.zzbef.zzbdz.size() || (!this.zzbef.zzbea.isEmpty() && zzrf().hasNext());
    }

    @Override // java.util.Iterator
    public final /* synthetic */ Object next() {
        this.zzbej = true;
        int i9 = this.pos + 1;
        this.pos = i9;
        return i9 < this.zzbef.zzbdz.size() ? (Map.Entry) this.zzbef.zzbdz.get(this.pos) : (Map.Entry) zzrf().next();
    }

    @Override // java.util.Iterator
    public final void remove() {
        if (!this.zzbej) {
            throw new IllegalStateException("remove() was called before next()");
        }
        this.zzbej = false;
        this.zzbef.zzrd();
        if (this.pos >= this.zzbef.zzbdz.size()) {
            zzrf().remove();
            return;
        }
        zztc zztcVar = this.zzbef;
        int i9 = this.pos;
        this.pos = i9 - 1;
        zztcVar.zzbw(i9);
    }

    public /* synthetic */ zztk(zztc zztcVar, zztd zztdVar) {
        this(zztcVar);
    }
}
