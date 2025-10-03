package com.google.android.gms.internal.p260authapi;

import android.os.IBinder;
import android.os.Parcel;
import com.google.android.gms.auth.api.identity.BeginSignInRequest;
import com.google.android.gms.common.api.internal.IStatusCallback;

/* loaded from: classes2.dex */
public final class zzac extends zzd implements zzad {
    public zzac(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.auth.api.identity.internal.ISignInService");
    }

    @Override // com.google.android.gms.internal.p260authapi.zzad
    public final void zzc(zzab zzabVar, BeginSignInRequest beginSignInRequest) {
        Parcel parcelZzc = zzc();
        zzf.zzc(parcelZzc, zzabVar);
        zzf.zzc(parcelZzc, beginSignInRequest);
        zzc(1, parcelZzc);
    }

    @Override // com.google.android.gms.internal.p260authapi.zzad
    public final void zzc(IStatusCallback iStatusCallback, String str) {
        Parcel parcelZzc = zzc();
        zzf.zzc(parcelZzc, iStatusCallback);
        parcelZzc.writeString(str);
        zzc(2, parcelZzc);
    }
}
