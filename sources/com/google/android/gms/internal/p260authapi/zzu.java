package com.google.android.gms.internal.p260authapi;

import android.os.Parcel;
import com.google.android.gms.auth.api.credentials.Credential;
import com.google.android.gms.common.api.Status;

/* loaded from: classes2.dex */
public abstract class zzu extends zzc implements zzv {
    public zzu() {
        super("com.google.android.gms.auth.api.credentials.internal.ICredentialsCallbacks");
    }

    @Override // com.google.android.gms.internal.p260authapi.zzc
    public final boolean zzc(int i9, Parcel parcel, Parcel parcel2, int i10) {
        if (i9 == 1) {
            zzc((Status) zzf.zzc(parcel, Status.CREATOR), (Credential) zzf.zzc(parcel, Credential.CREATOR));
        } else if (i9 == 2) {
            zzd((Status) zzf.zzc(parcel, Status.CREATOR));
        } else {
            if (i9 != 3) {
                return false;
            }
            zzc((Status) zzf.zzc(parcel, Status.CREATOR), parcel.readString());
        }
        parcel2.writeNoException();
        return true;
    }
}
