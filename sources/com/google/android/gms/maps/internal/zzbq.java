package com.google.android.gms.maps.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* loaded from: classes2.dex */
public abstract class zzbq extends com.google.android.gms.internal.maps.zzb implements zzbp {
    public zzbq() {
        super("com.google.android.gms.maps.internal.IOnStreetViewPanoramaReadyCallback");
    }

    @Override // com.google.android.gms.internal.maps.zzb
    public final boolean dispatchTransaction(int i9, Parcel parcel, Parcel parcel2, int i10) {
        IStreetViewPanoramaDelegate zzbuVar;
        if (i9 != 1) {
            return false;
        }
        IBinder strongBinder = parcel.readStrongBinder();
        if (strongBinder == null) {
            zzbuVar = null;
        } else {
            IInterface iInterfaceQueryLocalInterface = strongBinder.queryLocalInterface("com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate");
            zzbuVar = iInterfaceQueryLocalInterface instanceof IStreetViewPanoramaDelegate ? (IStreetViewPanoramaDelegate) iInterfaceQueryLocalInterface : new zzbu(strongBinder);
        }
        zza(zzbuVar);
        parcel2.writeNoException();
        return true;
    }
}
