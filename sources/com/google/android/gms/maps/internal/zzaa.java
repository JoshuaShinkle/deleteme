package com.google.android.gms.maps.internal;

import android.os.Parcel;

/* loaded from: classes2.dex */
public abstract class zzaa extends com.google.android.gms.internal.maps.zzb implements zzz {
    public zzaa() {
        super("com.google.android.gms.maps.internal.IOnIndoorStateChangeListener");
    }

    @Override // com.google.android.gms.internal.maps.zzb
    public final boolean dispatchTransaction(int i9, Parcel parcel, Parcel parcel2, int i10) {
        if (i9 == 1) {
            onIndoorBuildingFocused();
        } else {
            if (i9 != 2) {
                return false;
            }
            zza(com.google.android.gms.internal.maps.zzo.zze(parcel.readStrongBinder()));
        }
        parcel2.writeNoException();
        return true;
    }
}
