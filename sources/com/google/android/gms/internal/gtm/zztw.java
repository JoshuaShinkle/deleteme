package com.google.android.gms.internal.gtm;

import java.util.Iterator;

/* loaded from: classes2.dex */
final class zztw implements Iterator<String> {
    private final /* synthetic */ zztu zzber;
    private Iterator<String> zzbes;

    public zztw(zztu zztuVar) {
        this.zzber = zztuVar;
        this.zzbes = zztuVar.zzbeo.iterator();
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.zzbes.hasNext();
    }

    @Override // java.util.Iterator
    public final /* synthetic */ String next() {
        return this.zzbes.next();
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException();
    }
}
