package com.google.android.gms.auth.account;

import android.accounts.Account;
import android.os.Parcel;

/* loaded from: classes2.dex */
public abstract class zzb extends com.google.android.gms.internal.auth.zzb implements zza {
    public zzb() {
        super("com.google.android.gms.auth.account.IWorkAccountCallback");
    }

    @Override // com.google.android.gms.internal.auth.zzb
    public final boolean dispatchTransaction(int i9, Parcel parcel, Parcel parcel2, int i10) {
        if (i9 == 1) {
            zzc((Account) com.google.android.gms.internal.auth.zzc.zza(parcel, Account.CREATOR));
        } else {
            if (i9 != 2) {
                return false;
            }
            zza(com.google.android.gms.internal.auth.zzc.zza(parcel));
        }
        return true;
    }
}
