package com.google.android.gms.maps.internal;

import android.os.Parcel;
import com.google.android.gms.maps.model.StreetViewPanoramaOrientation;

/* loaded from: classes2.dex */
public abstract class zzbo extends com.google.android.gms.internal.maps.zzb implements zzbn {
    public zzbo() {
        super("com.google.android.gms.maps.internal.IOnStreetViewPanoramaLongClickListener");
    }

    @Override // com.google.android.gms.internal.maps.zzb
    public final boolean dispatchTransaction(int i9, Parcel parcel, Parcel parcel2, int i10) {
        if (i9 != 1) {
            return false;
        }
        onStreetViewPanoramaLongClick((StreetViewPanoramaOrientation) com.google.android.gms.internal.maps.zzc.zza(parcel, StreetViewPanoramaOrientation.CREATOR));
        parcel2.writeNoException();
        return true;
    }
}
