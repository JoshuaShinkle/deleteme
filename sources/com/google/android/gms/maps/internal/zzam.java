package com.google.android.gms.maps.internal;

import android.os.Parcel;

/* loaded from: classes2.dex */
public abstract class zzam extends com.google.android.gms.internal.maps.zzb implements zzal {
    public zzam() {
        super("com.google.android.gms.maps.internal.IOnMapLoadedCallback");
    }

    @Override // com.google.android.gms.internal.maps.zzb
    public final boolean dispatchTransaction(int i9, Parcel parcel, Parcel parcel2, int i10) {
        if (i9 != 1) {
            return false;
        }
        onMapLoaded();
        parcel2.writeNoException();
        return true;
    }
}
