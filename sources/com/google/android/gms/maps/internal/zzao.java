package com.google.android.gms.maps.internal;

import android.os.Parcel;
import com.google.android.gms.maps.model.LatLng;

/* loaded from: classes2.dex */
public abstract class zzao extends com.google.android.gms.internal.maps.zzb implements zzan {
    public zzao() {
        super("com.google.android.gms.maps.internal.IOnMapLongClickListener");
    }

    @Override // com.google.android.gms.internal.maps.zzb
    public final boolean dispatchTransaction(int i9, Parcel parcel, Parcel parcel2, int i10) {
        if (i9 != 1) {
            return false;
        }
        onMapLongClick((LatLng) com.google.android.gms.internal.maps.zzc.zza(parcel, LatLng.CREATOR));
        parcel2.writeNoException();
        return true;
    }
}
