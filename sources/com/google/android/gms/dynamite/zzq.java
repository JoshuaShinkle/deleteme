package com.google.android.gms.dynamite;

import android.os.IBinder;
import android.os.Parcel;
import com.google.android.gms.dynamic.IObjectWrapper;

/* loaded from: classes2.dex */
public final class zzq extends com.google.android.gms.internal.common.zza {
    public zzq(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.dynamite.IDynamiteLoader");
    }

    public final int zze() {
        Parcel parcelZzB = zzB(6, zza());
        int i9 = parcelZzB.readInt();
        parcelZzB.recycle();
        return i9;
    }

    public final int zzf(IObjectWrapper iObjectWrapper, String str, boolean z8) {
        Parcel parcelZza = zza();
        com.google.android.gms.internal.common.zzc.zzf(parcelZza, iObjectWrapper);
        parcelZza.writeString(str);
        com.google.android.gms.internal.common.zzc.zzc(parcelZza, z8);
        Parcel parcelZzB = zzB(3, parcelZza);
        int i9 = parcelZzB.readInt();
        parcelZzB.recycle();
        return i9;
    }

    public final int zzg(IObjectWrapper iObjectWrapper, String str, boolean z8) {
        Parcel parcelZza = zza();
        com.google.android.gms.internal.common.zzc.zzf(parcelZza, iObjectWrapper);
        parcelZza.writeString(str);
        com.google.android.gms.internal.common.zzc.zzc(parcelZza, z8);
        Parcel parcelZzB = zzB(5, parcelZza);
        int i9 = parcelZzB.readInt();
        parcelZzB.recycle();
        return i9;
    }

    public final IObjectWrapper zzh(IObjectWrapper iObjectWrapper, String str, int i9) {
        Parcel parcelZza = zza();
        com.google.android.gms.internal.common.zzc.zzf(parcelZza, iObjectWrapper);
        parcelZza.writeString(str);
        parcelZza.writeInt(i9);
        Parcel parcelZzB = zzB(2, parcelZza);
        IObjectWrapper iObjectWrapperAsInterface = IObjectWrapper.Stub.asInterface(parcelZzB.readStrongBinder());
        parcelZzB.recycle();
        return iObjectWrapperAsInterface;
    }

    public final IObjectWrapper zzi(IObjectWrapper iObjectWrapper, String str, int i9, IObjectWrapper iObjectWrapper2) {
        Parcel parcelZza = zza();
        com.google.android.gms.internal.common.zzc.zzf(parcelZza, iObjectWrapper);
        parcelZza.writeString(str);
        parcelZza.writeInt(i9);
        com.google.android.gms.internal.common.zzc.zzf(parcelZza, iObjectWrapper2);
        Parcel parcelZzB = zzB(8, parcelZza);
        IObjectWrapper iObjectWrapperAsInterface = IObjectWrapper.Stub.asInterface(parcelZzB.readStrongBinder());
        parcelZzB.recycle();
        return iObjectWrapperAsInterface;
    }

    public final IObjectWrapper zzj(IObjectWrapper iObjectWrapper, String str, int i9) {
        Parcel parcelZza = zza();
        com.google.android.gms.internal.common.zzc.zzf(parcelZza, iObjectWrapper);
        parcelZza.writeString(str);
        parcelZza.writeInt(i9);
        Parcel parcelZzB = zzB(4, parcelZza);
        IObjectWrapper iObjectWrapperAsInterface = IObjectWrapper.Stub.asInterface(parcelZzB.readStrongBinder());
        parcelZzB.recycle();
        return iObjectWrapperAsInterface;
    }

    public final IObjectWrapper zzk(IObjectWrapper iObjectWrapper, String str, boolean z8, long j9) {
        Parcel parcelZza = zza();
        com.google.android.gms.internal.common.zzc.zzf(parcelZza, iObjectWrapper);
        parcelZza.writeString(str);
        com.google.android.gms.internal.common.zzc.zzc(parcelZza, z8);
        parcelZza.writeLong(j9);
        Parcel parcelZzB = zzB(7, parcelZza);
        IObjectWrapper iObjectWrapperAsInterface = IObjectWrapper.Stub.asInterface(parcelZzB.readStrongBinder());
        parcelZzB.recycle();
        return iObjectWrapperAsInterface;
    }
}
