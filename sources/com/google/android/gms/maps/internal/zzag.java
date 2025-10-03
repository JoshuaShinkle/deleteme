package com.google.android.gms.maps.internal;

import android.os.Parcel;

/* loaded from: classes2.dex */
public abstract class zzag extends com.google.android.gms.internal.maps.zzb implements zzaf {
    public zzag() {
        super("com.google.android.gms.maps.internal.IOnInfoWindowLongClickListener");
    }

    @Override // com.google.android.gms.internal.maps.zzb
    public final boolean dispatchTransaction(int i9, Parcel parcel, Parcel parcel2, int i10) {
        if (i9 != 1) {
            return false;
        }
        zzf(com.google.android.gms.internal.maps.zzu.zzg(parcel.readStrongBinder()));
        parcel2.writeNoException();
        return true;
    }
}
