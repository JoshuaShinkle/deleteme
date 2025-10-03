package com.google.android.gms.maps.internal;

import android.os.Parcel;

/* loaded from: classes2.dex */
public abstract class zzbg extends com.google.android.gms.internal.maps.zzb implements zzbf {
    public zzbg() {
        super("com.google.android.gms.maps.internal.IOnPolylineClickListener");
    }

    @Override // com.google.android.gms.internal.maps.zzb
    public final boolean dispatchTransaction(int i9, Parcel parcel, Parcel parcel2, int i10) {
        if (i9 != 1) {
            return false;
        }
        zza(com.google.android.gms.internal.maps.zzaa.zzi(parcel.readStrongBinder()));
        parcel2.writeNoException();
        return true;
    }
}
