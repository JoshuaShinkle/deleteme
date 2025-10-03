package com.google.android.gms.signin.internal;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class(creator = "AuthAccountResultCreator")
/* loaded from: classes2.dex */
public final class zab extends AbstractSafeParcelable implements Result {
    public static final Parcelable.Creator<zab> CREATOR = new zaa();

    @SafeParcelable.VersionField(m17523id = 1)
    private final int zaa;

    @SafeParcelable.Field(getter = "getConnectionResultCode", m17520id = 2)
    private int zab;

    @SafeParcelable.Field(getter = "getRawAuthResolutionIntent", m17520id = 3)
    private Intent zac;

    @SafeParcelable.Constructor
    public zab(@SafeParcelable.Param(m17521id = 1) int i9, @SafeParcelable.Param(m17521id = 2) int i10, @SafeParcelable.Param(m17521id = 3) Intent intent) {
        this.zaa = i9;
        this.zab = i10;
        this.zac = intent;
    }

    @Override // com.google.android.gms.common.api.Result
    public final Status getStatus() {
        return this.zab == 0 ? Status.RESULT_SUCCESS : Status.RESULT_CANCELED;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i9) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zaa);
        SafeParcelWriter.writeInt(parcel, 2, this.zab);
        SafeParcelWriter.writeParcelable(parcel, 3, this.zac, i9, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    public zab() {
        this(0, null);
    }

    private zab(int i9, Intent intent) {
        this(2, 0, null);
    }
}
