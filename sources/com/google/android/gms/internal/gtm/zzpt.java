package com.google.android.gms.internal.gtm;

import java.util.NoSuchElementException;

/* loaded from: classes2.dex */
final class zzpt extends zzpv {
    private final int limit;
    private int position = 0;
    private final /* synthetic */ zzps zzawa;

    public zzpt(zzps zzpsVar) {
        this.zzawa = zzpsVar;
        this.limit = zzpsVar.size();
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.position < this.limit;
    }

    @Override // com.google.android.gms.internal.gtm.zzpz
    public final byte nextByte() {
        int i9 = this.position;
        if (i9 >= this.limit) {
            throw new NoSuchElementException();
        }
        this.position = i9 + 1;
        return this.zzawa.zzal(i9);
    }
}
