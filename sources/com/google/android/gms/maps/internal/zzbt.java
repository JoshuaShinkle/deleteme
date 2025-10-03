package com.google.android.gms.maps.internal;

import android.graphics.Bitmap;
import android.os.Parcel;
import com.google.android.gms.dynamic.IObjectWrapper;

/* loaded from: classes2.dex */
public abstract class zzbt extends com.google.android.gms.internal.maps.zzb implements zzbs {
    public zzbt() {
        super("com.google.android.gms.maps.internal.ISnapshotReadyCallback");
    }

    @Override // com.google.android.gms.internal.maps.zzb
    public final boolean dispatchTransaction(int i9, Parcel parcel, Parcel parcel2, int i10) {
        if (i9 == 1) {
            onSnapshotReady((Bitmap) com.google.android.gms.internal.maps.zzc.zza(parcel, Bitmap.CREATOR));
        } else {
            if (i9 != 2) {
                return false;
            }
            zzb(IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()));
        }
        parcel2.writeNoException();
        return true;
    }
}
