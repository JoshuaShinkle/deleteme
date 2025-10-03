package com.google.android.gms.auth.api.signin.internal;

import android.os.IBinder;
import android.os.Parcel;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

/* loaded from: classes2.dex */
public final class zzu extends com.google.android.gms.internal.p260authapi.zzd implements zzv {
    public zzu(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.auth.api.signin.internal.ISignInService");
    }

    @Override // com.google.android.gms.auth.api.signin.internal.zzv
    public final void zzc(zzt zztVar, GoogleSignInOptions googleSignInOptions) {
        Parcel parcelZzc = zzc();
        com.google.android.gms.internal.p260authapi.zzf.zzc(parcelZzc, zztVar);
        com.google.android.gms.internal.p260authapi.zzf.zzc(parcelZzc, googleSignInOptions);
        zzc(101, parcelZzc);
    }

    @Override // com.google.android.gms.auth.api.signin.internal.zzv
    public final void zzd(zzt zztVar, GoogleSignInOptions googleSignInOptions) {
        Parcel parcelZzc = zzc();
        com.google.android.gms.internal.p260authapi.zzf.zzc(parcelZzc, zztVar);
        com.google.android.gms.internal.p260authapi.zzf.zzc(parcelZzc, googleSignInOptions);
        zzc(102, parcelZzc);
    }

    @Override // com.google.android.gms.auth.api.signin.internal.zzv
    public final void zze(zzt zztVar, GoogleSignInOptions googleSignInOptions) {
        Parcel parcelZzc = zzc();
        com.google.android.gms.internal.p260authapi.zzf.zzc(parcelZzc, zztVar);
        com.google.android.gms.internal.p260authapi.zzf.zzc(parcelZzc, googleSignInOptions);
        zzc(103, parcelZzc);
    }
}
