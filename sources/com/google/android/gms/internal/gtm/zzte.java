package com.google.android.gms.internal.gtm;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes2.dex */
final class zzte implements Iterator {
    private int pos;
    private Iterator zzbee;
    private final /* synthetic */ zztc zzbef;

    private zzte(zztc zztcVar) {
        this.zzbef = zztcVar;
        this.pos = zztcVar.zzbdz.size();
    }

    private final Iterator zzrf() {
        if (this.zzbee == null) {
            this.zzbee = this.zzbef.zzbec.entrySet().iterator();
        }
        return this.zzbee;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        int i9 = this.pos;
        return (i9 > 0 && i9 <= this.zzbef.zzbdz.size()) || zzrf().hasNext();
    }

    @Override // java.util.Iterator
    public final /* synthetic */ Object next() {
        if (zzrf().hasNext()) {
            return (Map.Entry) zzrf().next();
        }
        List list = this.zzbef.zzbdz;
        int i9 = this.pos - 1;
        this.pos = i9;
        return (Map.Entry) list.get(i9);
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException();
    }

    public /* synthetic */ zzte(zztc zztcVar, zztd zztdVar) {
        this(zztcVar);
    }
}
