package com.google.android.gms.plus.internal;

import android.os.IBinder;
import android.os.Parcel;
import com.google.android.gms.common.internal.ICancelToken;
import java.util.List;

/* loaded from: classes2.dex */
public final class zzg extends com.google.android.gms.internal.plus.zza implements zzf {
    public zzg(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.plus.internal.IPlusService");
    }

    @Override // com.google.android.gms.plus.internal.zzf
    public final String getAccountName() {
        Parcel parcelTransactAndReadException = transactAndReadException(5, obtainAndWriteInterfaceToken());
        String string = parcelTransactAndReadException.readString();
        parcelTransactAndReadException.recycle();
        return string;
    }

    @Override // com.google.android.gms.plus.internal.zzf
    public final ICancelToken zza(zzb zzbVar, int i9, int i10, int i11, String str) {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.plus.zzc.zza(parcelObtainAndWriteInterfaceToken, zzbVar);
        parcelObtainAndWriteInterfaceToken.writeInt(i9);
        parcelObtainAndWriteInterfaceToken.writeInt(i10);
        parcelObtainAndWriteInterfaceToken.writeInt(-1);
        parcelObtainAndWriteInterfaceToken.writeString(str);
        Parcel parcelTransactAndReadException = transactAndReadException(16, parcelObtainAndWriteInterfaceToken);
        ICancelToken iCancelTokenAsInterface = ICancelToken.Stub.asInterface(parcelTransactAndReadException.readStrongBinder());
        parcelTransactAndReadException.recycle();
        return iCancelTokenAsInterface;
    }

    @Override // com.google.android.gms.plus.internal.zzf
    public final void zza() {
        transactAndReadExceptionReturnVoid(6, obtainAndWriteInterfaceToken());
    }

    @Override // com.google.android.gms.plus.internal.zzf
    public final void zza(zzb zzbVar) {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.plus.zzc.zza(parcelObtainAndWriteInterfaceToken, zzbVar);
        transactAndReadExceptionReturnVoid(19, parcelObtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.plus.internal.zzf
    public final void zza(zzb zzbVar, List<String> list) {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.plus.zzc.zza(parcelObtainAndWriteInterfaceToken, zzbVar);
        parcelObtainAndWriteInterfaceToken.writeStringList(list);
        transactAndReadExceptionReturnVoid(34, parcelObtainAndWriteInterfaceToken);
    }
}
