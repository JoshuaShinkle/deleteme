package com.google.android.gms.maps.internal;

import android.os.Parcel;
import com.google.android.gms.maps.model.PointOfInterest;

/* loaded from: classes2.dex */
public abstract class zzbc extends com.google.android.gms.internal.maps.zzb implements zzbb {
    public zzbc() {
        super("com.google.android.gms.maps.internal.IOnPoiClickListener");
    }

    @Override // com.google.android.gms.internal.maps.zzb
    public final boolean dispatchTransaction(int i9, Parcel parcel, Parcel parcel2, int i10) {
        if (i9 != 1) {
            return false;
        }
        zza((PointOfInterest) com.google.android.gms.internal.maps.zzc.zza(parcel, PointOfInterest.CREATOR));
        parcel2.writeNoException();
        return true;
    }
}
