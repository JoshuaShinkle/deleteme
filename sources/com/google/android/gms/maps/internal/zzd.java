package com.google.android.gms.maps.internal;

import android.os.Parcel;

/* loaded from: classes2.dex */
public abstract class zzd extends com.google.android.gms.internal.maps.zzb implements zzc {
    public zzd() {
        super("com.google.android.gms.maps.internal.ICancelableCallback");
    }

    @Override // com.google.android.gms.internal.maps.zzb
    public final boolean dispatchTransaction(int i9, Parcel parcel, Parcel parcel2, int i10) {
        if (i9 == 1) {
            onFinish();
        } else {
            if (i9 != 2) {
                return false;
            }
            onCancel();
        }
        parcel2.writeNoException();
        return true;
    }
}
