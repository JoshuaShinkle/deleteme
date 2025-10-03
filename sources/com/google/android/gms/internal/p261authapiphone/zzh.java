package com.google.android.gms.internal.p261authapiphone;

import android.os.Parcel;
import com.google.android.gms.common.api.Status;

/* loaded from: classes2.dex */
public abstract class zzh extends zzb implements zzg {
    public zzh() {
        super("com.google.android.gms.auth.api.phone.internal.ISmsRetrieverResultCallback");
    }

    @Override // com.google.android.gms.internal.p261authapiphone.zzb
    public final boolean dispatchTransaction(int i9, Parcel parcel, Parcel parcel2, int i10) {
        if (i9 != 1) {
            return false;
        }
        zza((Status) zzc.zza(parcel, Status.CREATOR));
        return true;
    }
}
