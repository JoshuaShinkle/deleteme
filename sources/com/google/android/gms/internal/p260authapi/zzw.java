package com.google.android.gms.internal.p260authapi;

import android.os.IBinder;
import android.os.Parcel;
import com.google.android.gms.auth.api.credentials.CredentialRequest;

/* loaded from: classes2.dex */
public final class zzw extends zzd implements zzx {
    public zzw(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.auth.api.credentials.internal.ICredentialsService");
    }

    @Override // com.google.android.gms.internal.p260authapi.zzx
    public final void zzc(zzv zzvVar, CredentialRequest credentialRequest) {
        Parcel parcelZzc = zzc();
        zzf.zzc(parcelZzc, zzvVar);
        zzf.zzc(parcelZzc, credentialRequest);
        zzc(1, parcelZzc);
    }

    @Override // com.google.android.gms.internal.p260authapi.zzx
    public final void zzc(zzv zzvVar, zzz zzzVar) {
        Parcel parcelZzc = zzc();
        zzf.zzc(parcelZzc, zzvVar);
        zzf.zzc(parcelZzc, zzzVar);
        zzc(2, parcelZzc);
    }

    @Override // com.google.android.gms.internal.p260authapi.zzx
    public final void zzc(zzv zzvVar, zzt zztVar) {
        Parcel parcelZzc = zzc();
        zzf.zzc(parcelZzc, zzvVar);
        zzf.zzc(parcelZzc, zztVar);
        zzc(3, parcelZzc);
    }

    @Override // com.google.android.gms.internal.p260authapi.zzx
    public final void zzc(zzv zzvVar) {
        Parcel parcelZzc = zzc();
        zzf.zzc(parcelZzc, zzvVar);
        zzc(4, parcelZzc);
    }
}
