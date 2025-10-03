package com.google.android.gms.auth.api.signin.internal;

import android.os.Parcel;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.Status;

/* loaded from: classes2.dex */
public abstract class zzs extends com.google.android.gms.internal.p260authapi.zzc implements zzt {
    public zzs() {
        super("com.google.android.gms.auth.api.signin.internal.ISignInCallbacks");
    }

    @Override // com.google.android.gms.internal.p260authapi.zzc
    public final boolean zzc(int i9, Parcel parcel, Parcel parcel2, int i10) {
        switch (i9) {
            case 101:
                zzc((GoogleSignInAccount) com.google.android.gms.internal.p260authapi.zzf.zzc(parcel, GoogleSignInAccount.CREATOR), (Status) com.google.android.gms.internal.p260authapi.zzf.zzc(parcel, Status.CREATOR));
                break;
            case 102:
                zze((Status) com.google.android.gms.internal.p260authapi.zzf.zzc(parcel, Status.CREATOR));
                break;
            case 103:
                zzf((Status) com.google.android.gms.internal.p260authapi.zzf.zzc(parcel, Status.CREATOR));
                break;
            default:
                return false;
        }
        parcel2.writeNoException();
        return true;
    }
}
