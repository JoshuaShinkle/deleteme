package com.google.android.gms.dynamite;

import android.os.IBinder;
import android.os.Parcel;
import com.google.android.gms.dynamic.IObjectWrapper;

/* loaded from: classes2.dex */
public final class zzr extends com.google.android.gms.internal.common.zza {
    public zzr(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.dynamite.IDynamiteLoaderV2");
    }

    public final IObjectWrapper zze(IObjectWrapper iObjectWrapper, String str, int i9, IObjectWrapper iObjectWrapper2) {
        Parcel parcelZza = zza();
        com.google.android.gms.internal.common.zzc.zzf(parcelZza, iObjectWrapper);
        parcelZza.writeString(str);
        parcelZza.writeInt(i9);
        com.google.android.gms.internal.common.zzc.zzf(parcelZza, iObjectWrapper2);
        Parcel parcelZzB = zzB(2, parcelZza);
        IObjectWrapper iObjectWrapperAsInterface = IObjectWrapper.Stub.asInterface(parcelZzB.readStrongBinder());
        parcelZzB.recycle();
        return iObjectWrapperAsInterface;
    }

    public final IObjectWrapper zzf(IObjectWrapper iObjectWrapper, String str, int i9, IObjectWrapper iObjectWrapper2) {
        Parcel parcelZza = zza();
        com.google.android.gms.internal.common.zzc.zzf(parcelZza, iObjectWrapper);
        parcelZza.writeString(str);
        parcelZza.writeInt(i9);
        com.google.android.gms.internal.common.zzc.zzf(parcelZza, iObjectWrapper2);
        Parcel parcelZzB = zzB(3, parcelZza);
        IObjectWrapper iObjectWrapperAsInterface = IObjectWrapper.Stub.asInterface(parcelZzB.readStrongBinder());
        parcelZzB.recycle();
        return iObjectWrapperAsInterface;
    }
}
