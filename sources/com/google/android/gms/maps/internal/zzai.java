package com.google.android.gms.maps.internal;

import android.location.Location;
import android.os.IBinder;
import android.os.Parcel;

/* loaded from: classes2.dex */
public final class zzai extends com.google.android.gms.internal.maps.zza implements zzah {
    public zzai(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.maps.internal.IOnLocationChangeListener");
    }

    @Override // com.google.android.gms.maps.internal.zzah
    public final void zza(Location location) {
        Parcel parcelZza = zza();
        com.google.android.gms.internal.maps.zzc.zza(parcelZza, location);
        zzb(2, parcelZza);
    }
}
