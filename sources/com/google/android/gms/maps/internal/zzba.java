package com.google.android.gms.maps.internal;

import android.location.Location;
import android.os.Parcel;

/* loaded from: classes2.dex */
public abstract class zzba extends com.google.android.gms.internal.maps.zzb implements zzaz {
    public zzba() {
        super("com.google.android.gms.maps.internal.IOnMyLocationClickListener");
    }

    @Override // com.google.android.gms.internal.maps.zzb
    public final boolean dispatchTransaction(int i9, Parcel parcel, Parcel parcel2, int i10) {
        if (i9 != 1) {
            return false;
        }
        onMyLocationClick((Location) com.google.android.gms.internal.maps.zzc.zza(parcel, Location.CREATOR));
        parcel2.writeNoException();
        return true;
    }
}
