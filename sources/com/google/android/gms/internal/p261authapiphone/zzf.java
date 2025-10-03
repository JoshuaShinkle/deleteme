package com.google.android.gms.internal.p261authapiphone;

import android.os.IBinder;
import android.os.Parcel;

/* loaded from: classes2.dex */
public final class zzf extends zza implements zze {
    public zzf(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.auth.api.phone.internal.ISmsRetrieverApiService");
    }

    @Override // com.google.android.gms.internal.p261authapiphone.zze
    public final void zza(zzg zzgVar) {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(parcelObtainAndWriteInterfaceToken, zzgVar);
        transactAndReadExceptionReturnVoid(1, parcelObtainAndWriteInterfaceToken);
    }
}
