package com.google.android.gms.signin.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zas;

@SafeParcelable.Class(creator = "SignInResponseCreator")
/* loaded from: classes2.dex */
public final class zam extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zam> CREATOR = new zal();

    @SafeParcelable.VersionField(m17523id = 1)
    private final int zaa;

    @SafeParcelable.Field(getter = "getConnectionResult", m17520id = 2)
    private final ConnectionResult zab;

    @SafeParcelable.Field(getter = "getResolveAccountResponse", m17520id = 3)
    private final zas zac;

    @SafeParcelable.Constructor
    public zam(@SafeParcelable.Param(m17521id = 1) int i9, @SafeParcelable.Param(m17521id = 2) ConnectionResult connectionResult, @SafeParcelable.Param(m17521id = 3) zas zasVar) {
        this.zaa = i9;
        this.zab = connectionResult;
        this.zac = zasVar;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i9) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zaa);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zab, i9, false);
        SafeParcelWriter.writeParcelable(parcel, 3, this.zac, i9, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    public final ConnectionResult zaa() {
        return this.zab;
    }

    public final zas zab() {
        return this.zac;
    }

    public zam(int i9) {
        this(new ConnectionResult(8, null), null);
    }

    private zam(ConnectionResult connectionResult, zas zasVar) {
        this(1, connectionResult, null);
    }
}
