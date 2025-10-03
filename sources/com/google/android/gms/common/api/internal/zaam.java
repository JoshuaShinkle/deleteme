package com.google.android.gms.common.api.internal;

import java.lang.ref.WeakReference;

/* loaded from: classes2.dex */
final class zaam extends com.google.android.gms.signin.internal.zad {
    private final WeakReference<zaad> zaa;

    public zaam(zaad zaadVar) {
        this.zaa = new WeakReference<>(zaadVar);
    }

    @Override // com.google.android.gms.signin.internal.zad, com.google.android.gms.signin.internal.zac
    public final void zaa(com.google.android.gms.signin.internal.zam zamVar) {
        zaad zaadVar = this.zaa.get();
        if (zaadVar == null) {
            return;
        }
        zaadVar.zaa.zaa(new zaal(this, zaadVar, zaadVar, zamVar));
    }
}
