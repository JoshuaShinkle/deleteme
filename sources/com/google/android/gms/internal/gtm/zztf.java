package com.google.android.gms.internal.gtm;

import java.util.Iterator;

/* loaded from: classes2.dex */
final class zztf extends zztl {
    private final /* synthetic */ zztc zzbef;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    private zztf(zztc zztcVar) {
        super(zztcVar, null);
        this.zzbef = zztcVar;
    }

    @Override // com.google.android.gms.internal.gtm.zztl, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final Iterator iterator() {
        return new zzte(this.zzbef, null);
    }

    public /* synthetic */ zztf(zztc zztcVar, zztd zztdVar) {
        this(zztcVar);
    }
}
