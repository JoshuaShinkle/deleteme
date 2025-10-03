package com.google.android.gms.tagmanager;

import android.util.LruCache;

/* loaded from: classes2.dex */
final class zzdc extends LruCache {
    private final /* synthetic */ zzs zzahz;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzdc(zzdb zzdbVar, int i9, zzs zzsVar) {
        super(i9);
        this.zzahz = zzsVar;
    }

    @Override // android.util.LruCache
    public final int sizeOf(Object obj, Object obj2) {
        return this.zzahz.sizeOf(obj, obj2);
    }
}
