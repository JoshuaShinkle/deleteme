package com.google.android.gms.maps.internal;

import android.os.Parcel;
import com.google.android.gms.dynamic.IObjectWrapper;

/* loaded from: classes2.dex */
public abstract class zzi extends com.google.android.gms.internal.maps.zzb implements zzh {
    public zzi() {
        super("com.google.android.gms.maps.internal.IInfoWindowAdapter");
    }

    @Override // com.google.android.gms.internal.maps.zzb
    public final boolean dispatchTransaction(int i9, Parcel parcel, Parcel parcel2, int i10) {
        if (i9 == 1) {
            IObjectWrapper iObjectWrapperZzh = zzh(com.google.android.gms.internal.maps.zzu.zzg(parcel.readStrongBinder()));
            parcel2.writeNoException();
            com.google.android.gms.internal.maps.zzc.zza(parcel2, iObjectWrapperZzh);
        } else {
            if (i9 != 2) {
                return false;
            }
            IObjectWrapper iObjectWrapperZzi = zzi(com.google.android.gms.internal.maps.zzu.zzg(parcel.readStrongBinder()));
            parcel2.writeNoException();
            com.google.android.gms.internal.maps.zzc.zza(parcel2, iObjectWrapperZzi);
        }
        return true;
    }
}
