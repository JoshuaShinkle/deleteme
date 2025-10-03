package com.google.android.gms.plus.internal;

import android.os.IBinder;
import android.os.Parcel;
import com.google.android.gms.dynamic.IObjectWrapper;

/* loaded from: classes2.dex */
public final class zze extends com.google.android.gms.internal.plus.zza implements zzd {
    public zze(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.plus.internal.IPlusOneButtonCreator");
    }

    @Override // com.google.android.gms.plus.internal.zzd
    public final IObjectWrapper zza(IObjectWrapper iObjectWrapper, int i9, int i10, String str, int i11) {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.plus.zzc.zza(parcelObtainAndWriteInterfaceToken, iObjectWrapper);
        parcelObtainAndWriteInterfaceToken.writeInt(i9);
        parcelObtainAndWriteInterfaceToken.writeInt(i10);
        parcelObtainAndWriteInterfaceToken.writeString(str);
        parcelObtainAndWriteInterfaceToken.writeInt(i11);
        Parcel parcelTransactAndReadException = transactAndReadException(1, parcelObtainAndWriteInterfaceToken);
        IObjectWrapper iObjectWrapperAsInterface = IObjectWrapper.Stub.asInterface(parcelTransactAndReadException.readStrongBinder());
        parcelTransactAndReadException.recycle();
        return iObjectWrapperAsInterface;
    }
}
