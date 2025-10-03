package com.google.android.gms.internal.appinvite;

import android.content.Intent;
import android.os.Parcel;
import com.google.android.gms.common.api.Status;

/* loaded from: classes2.dex */
public abstract class zzr extends zza implements zzo {
    public zzr() {
        super("com.google.android.gms.appinvite.internal.IAppInviteCallbacks");
    }

    @Override // com.google.android.gms.internal.appinvite.zza
    public final boolean dispatchTransaction(int i9, Parcel parcel, Parcel parcel2, int i10) {
        if (i9 == 1) {
            zza((Status) zzd.zza(parcel, Status.CREATOR));
        } else {
            if (i9 != 2) {
                return false;
            }
            zza((Status) zzd.zza(parcel, Status.CREATOR), (Intent) zzd.zza(parcel, Intent.CREATOR));
        }
        parcel2.writeNoException();
        return true;
    }
}
