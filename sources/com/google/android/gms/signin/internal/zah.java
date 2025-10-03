package com.google.android.gms.signin.internal;

import android.os.IBinder;
import android.os.Parcel;
import com.google.android.gms.common.internal.IAccountAccessor;

/* loaded from: classes2.dex */
public final class zah extends com.google.android.gms.internal.base.zab implements zae {
    public zah(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.signin.internal.ISignInService");
    }

    @Override // com.google.android.gms.signin.internal.zae
    public final void zaa(int i9) {
        Parcel parcelZaa = zaa();
        parcelZaa.writeInt(i9);
        zab(7, parcelZaa);
    }

    @Override // com.google.android.gms.signin.internal.zae
    public final void zaa(IAccountAccessor iAccountAccessor, int i9, boolean z8) {
        Parcel parcelZaa = zaa();
        com.google.android.gms.internal.base.zad.zaa(parcelZaa, iAccountAccessor);
        parcelZaa.writeInt(i9);
        com.google.android.gms.internal.base.zad.zaa(parcelZaa, z8);
        zab(9, parcelZaa);
    }

    @Override // com.google.android.gms.signin.internal.zae
    public final void zaa(zak zakVar, zac zacVar) {
        Parcel parcelZaa = zaa();
        com.google.android.gms.internal.base.zad.zaa(parcelZaa, zakVar);
        com.google.android.gms.internal.base.zad.zaa(parcelZaa, zacVar);
        zab(12, parcelZaa);
    }
}
