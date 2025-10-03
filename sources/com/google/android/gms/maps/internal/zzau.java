package com.google.android.gms.maps.internal;

import android.os.Parcel;

/* loaded from: classes2.dex */
public abstract class zzau extends com.google.android.gms.internal.maps.zzb implements zzat {
    public zzau() {
        super("com.google.android.gms.maps.internal.IOnMarkerDragListener");
    }

    @Override // com.google.android.gms.internal.maps.zzb
    public final boolean dispatchTransaction(int i9, Parcel parcel, Parcel parcel2, int i10) {
        if (i9 == 1) {
            zzb(com.google.android.gms.internal.maps.zzu.zzg(parcel.readStrongBinder()));
        } else if (i9 == 2) {
            zzd(com.google.android.gms.internal.maps.zzu.zzg(parcel.readStrongBinder()));
        } else {
            if (i9 != 3) {
                return false;
            }
            zzc(com.google.android.gms.internal.maps.zzu.zzg(parcel.readStrongBinder()));
        }
        parcel2.writeNoException();
        return true;
    }
}
