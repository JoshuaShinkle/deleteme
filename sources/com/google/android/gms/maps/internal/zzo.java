package com.google.android.gms.maps.internal;

import android.os.Parcel;

/* loaded from: classes2.dex */
public abstract class zzo extends com.google.android.gms.internal.maps.zzb implements zzn {
    public zzo() {
        super("com.google.android.gms.maps.internal.IOnCameraIdleListener");
    }

    @Override // com.google.android.gms.internal.maps.zzb
    public final boolean dispatchTransaction(int i9, Parcel parcel, Parcel parcel2, int i10) {
        if (i9 != 1) {
            return false;
        }
        onCameraIdle();
        parcel2.writeNoException();
        return true;
    }
}
