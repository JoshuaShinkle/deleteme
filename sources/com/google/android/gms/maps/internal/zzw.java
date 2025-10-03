package com.google.android.gms.maps.internal;

import android.os.Parcel;

/* loaded from: classes2.dex */
public abstract class zzw extends com.google.android.gms.internal.maps.zzb implements zzv {
    public zzw() {
        super("com.google.android.gms.maps.internal.IOnCircleClickListener");
    }

    @Override // com.google.android.gms.internal.maps.zzb
    public final boolean dispatchTransaction(int i9, Parcel parcel, Parcel parcel2, int i10) {
        if (i9 != 1) {
            return false;
        }
        zza(com.google.android.gms.internal.maps.zzi.zzc(parcel.readStrongBinder()));
        parcel2.writeNoException();
        return true;
    }
}
