package com.google.android.gms.maps.internal;

import android.os.Parcel;
import com.google.android.gms.maps.model.StreetViewPanoramaCamera;

/* loaded from: classes2.dex */
public abstract class zzbi extends com.google.android.gms.internal.maps.zzb implements zzbh {
    public zzbi() {
        super("com.google.android.gms.maps.internal.IOnStreetViewPanoramaCameraChangeListener");
    }

    @Override // com.google.android.gms.internal.maps.zzb
    public final boolean dispatchTransaction(int i9, Parcel parcel, Parcel parcel2, int i10) {
        if (i9 != 1) {
            return false;
        }
        onStreetViewPanoramaCameraChange((StreetViewPanoramaCamera) com.google.android.gms.internal.maps.zzc.zza(parcel, StreetViewPanoramaCamera.CREATOR));
        parcel2.writeNoException();
        return true;
    }
}
