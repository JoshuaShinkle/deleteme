package com.google.android.gms.common.internal.service;

import android.os.IBinder;
import android.os.Parcel;

/* loaded from: classes2.dex */
public final class zam extends com.google.android.gms.internal.base.zab implements zak {
    public zam(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.common.internal.service.ICommonService");
    }

    @Override // com.google.android.gms.common.internal.service.zak
    public final void zaa(zai zaiVar) {
        Parcel parcelZaa = zaa();
        com.google.android.gms.internal.base.zad.zaa(parcelZaa, zaiVar);
        zac(1, parcelZaa);
    }
}
