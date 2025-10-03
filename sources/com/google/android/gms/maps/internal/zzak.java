package com.google.android.gms.maps.internal;

import android.os.Parcel;
import com.google.android.gms.maps.model.LatLng;

/* loaded from: classes2.dex */
public abstract class zzak extends com.google.android.gms.internal.maps.zzb implements zzaj {
    public zzak() {
        super("com.google.android.gms.maps.internal.IOnMapClickListener");
    }

    @Override // com.google.android.gms.internal.maps.zzb
    public final boolean dispatchTransaction(int i9, Parcel parcel, Parcel parcel2, int i10) {
        if (i9 != 1) {
            return false;
        }
        onMapClick((LatLng) com.google.android.gms.internal.maps.zzc.zza(parcel, LatLng.CREATOR));
        parcel2.writeNoException();
        return true;
    }
}
