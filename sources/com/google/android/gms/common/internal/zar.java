package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class(creator = "ResolveAccountRequestCreator")
/* loaded from: classes2.dex */
public final class zar extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zar> CREATOR = new zat();

    @SafeParcelable.VersionField(m17523id = 1)
    private final int zaa;

    @SafeParcelable.Field(getter = "getAccount", m17520id = 2)
    private final Account zab;

    @SafeParcelable.Field(getter = "getSessionId", m17520id = 3)
    private final int zac;

    @SafeParcelable.Field(getter = "getSignInAccountHint", m17520id = 4)
    private final GoogleSignInAccount zad;

    @SafeParcelable.Constructor
    public zar(@SafeParcelable.Param(m17521id = 1) int i9, @SafeParcelable.Param(m17521id = 2) Account account, @SafeParcelable.Param(m17521id = 3) int i10, @SafeParcelable.Param(m17521id = 4) GoogleSignInAccount googleSignInAccount) {
        this.zaa = i9;
        this.zab = account;
        this.zac = i10;
        this.zad = googleSignInAccount;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i9) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zaa);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zab, i9, false);
        SafeParcelWriter.writeInt(parcel, 3, this.zac);
        SafeParcelWriter.writeParcelable(parcel, 4, this.zad, i9, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    public zar(Account account, int i9, GoogleSignInAccount googleSignInAccount) {
        this(2, account, i9, googleSignInAccount);
    }
}
