package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.os.IBinder;
import android.os.Parcel;

/* loaded from: classes2.dex */
public final class zzv extends com.google.android.gms.internal.common.zza implements IAccountAccessor {
    public zzv(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.common.internal.IAccountAccessor");
    }

    @Override // com.google.android.gms.common.internal.IAccountAccessor
    public final Account zzb() {
        Parcel parcelZzB = zzB(2, zza());
        Account account = (Account) com.google.android.gms.internal.common.zzc.zza(parcelZzB, Account.CREATOR);
        parcelZzB.recycle();
        return account;
    }
}
