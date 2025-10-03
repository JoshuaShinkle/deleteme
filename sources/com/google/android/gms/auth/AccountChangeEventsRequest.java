package com.google.android.gms.auth;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class(creator = "AccountChangeEventsRequestCreator")
/* loaded from: classes2.dex */
public class AccountChangeEventsRequest extends AbstractSafeParcelable {
    public static final Parcelable.Creator<AccountChangeEventsRequest> CREATOR = new zzb();

    @SafeParcelable.VersionField(m17523id = 1)
    private final int zze;

    @SafeParcelable.Field(m17520id = 3)
    @Deprecated
    private String zzg;

    @SafeParcelable.Field(m17520id = 2)
    private int zzi;

    @SafeParcelable.Field(m17520id = 4)
    private Account zzk;

    @SafeParcelable.Constructor
    public AccountChangeEventsRequest(@SafeParcelable.Param(m17521id = 1) int i9, @SafeParcelable.Param(m17521id = 2) int i10, @SafeParcelable.Param(m17521id = 3) String str, @SafeParcelable.Param(m17521id = 4) Account account) {
        this.zze = i9;
        this.zzi = i10;
        this.zzg = str;
        if (account != null || TextUtils.isEmpty(str)) {
            this.zzk = account;
        } else {
            this.zzk = new Account(str, "com.google");
        }
    }

    public Account getAccount() {
        return this.zzk;
    }

    @Deprecated
    public String getAccountName() {
        return this.zzg;
    }

    public int getEventIndex() {
        return this.zzi;
    }

    public AccountChangeEventsRequest setAccount(Account account) {
        this.zzk = account;
        return this;
    }

    @Deprecated
    public AccountChangeEventsRequest setAccountName(String str) {
        this.zzg = str;
        return this;
    }

    public AccountChangeEventsRequest setEventIndex(int i9) {
        this.zzi = i9;
        return this;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i9) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zze);
        SafeParcelWriter.writeInt(parcel, 2, this.zzi);
        SafeParcelWriter.writeString(parcel, 3, this.zzg, false);
        SafeParcelWriter.writeParcelable(parcel, 4, this.zzk, i9, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    public AccountChangeEventsRequest() {
        this.zze = 1;
    }
}
