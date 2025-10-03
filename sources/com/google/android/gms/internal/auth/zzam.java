package com.google.android.gms.internal.auth;

import android.os.Parcel;
import com.google.android.gms.auth.api.proxy.ProxyResponse;

/* loaded from: classes2.dex */
public abstract class zzam extends zzb implements zzal {
    public zzam() {
        super("com.google.android.gms.auth.api.internal.IAuthCallbacks");
    }

    @Override // com.google.android.gms.internal.auth.zzb
    public final boolean dispatchTransaction(int i9, Parcel parcel, Parcel parcel2, int i10) {
        if (i9 == 1) {
            zza((ProxyResponse) zzc.zza(parcel, ProxyResponse.CREATOR));
        } else {
            if (i9 != 2) {
                return false;
            }
            zzb(parcel.readString());
        }
        parcel2.writeNoException();
        return true;
    }
}
