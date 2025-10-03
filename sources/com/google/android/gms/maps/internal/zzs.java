package com.google.android.gms.maps.internal;

import android.os.Parcel;

/* loaded from: classes2.dex */
public abstract class zzs extends com.google.android.gms.internal.maps.zzb implements zzr {
    public zzs() {
        super("com.google.android.gms.maps.internal.IOnCameraMoveListener");
    }

    @Override // com.google.android.gms.internal.maps.zzb
    public final boolean dispatchTransaction(int i9, Parcel parcel, Parcel parcel2, int i10) {
        if (i9 != 1) {
            return false;
        }
        onCameraMove();
        parcel2.writeNoException();
        return true;
    }
}
