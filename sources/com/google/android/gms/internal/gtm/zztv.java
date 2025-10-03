package com.google.android.gms.internal.gtm;

import java.util.ListIterator;

/* loaded from: classes2.dex */
final class zztv implements ListIterator<String> {
    private ListIterator<String> zzbep;
    private final /* synthetic */ int zzbeq;
    private final /* synthetic */ zztu zzber;

    public zztv(zztu zztuVar, int i9) {
        this.zzber = zztuVar;
        this.zzbeq = i9;
        this.zzbep = zztuVar.zzbeo.listIterator(i9);
    }

    @Override // java.util.ListIterator
    public final /* synthetic */ void add(String str) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public final boolean hasNext() {
        return this.zzbep.hasNext();
    }

    @Override // java.util.ListIterator
    public final boolean hasPrevious() {
        return this.zzbep.hasPrevious();
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public final /* synthetic */ Object next() {
        return this.zzbep.next();
    }

    @Override // java.util.ListIterator
    public final int nextIndex() {
        return this.zzbep.nextIndex();
    }

    @Override // java.util.ListIterator
    public final /* synthetic */ String previous() {
        return this.zzbep.previous();
    }

    @Override // java.util.ListIterator
    public final int previousIndex() {
        return this.zzbep.previousIndex();
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.ListIterator
    public final /* synthetic */ void set(String str) {
        throw new UnsupportedOperationException();
    }
}
