package com.google.android.gms.auth.api.signin.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.RecentlyNonNull;
import com.google.android.gms.auth.api.signin.GoogleSignInOptionsExtension;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class(creator = "GoogleSignInOptionsExtensionCreator")
/* loaded from: classes2.dex */
public class GoogleSignInOptionsExtensionParcelable extends AbstractSafeParcelable {

    @RecentlyNonNull
    public static final Parcelable.Creator<GoogleSignInOptionsExtensionParcelable> CREATOR = new zaa();

    @SafeParcelable.VersionField(m17523id = 1)
    private final int zaa;

    @SafeParcelable.Field(getter = "getType", m17520id = 2)
    private int zab;

    @SafeParcelable.Field(getter = "getBundle", m17520id = 3)
    private Bundle zac;

    @SafeParcelable.Constructor
    public GoogleSignInOptionsExtensionParcelable(@SafeParcelable.Param(m17521id = 1) int i9, @SafeParcelable.Param(m17521id = 2) int i10, @SafeParcelable.Param(m17521id = 3) Bundle bundle) {
        this.zaa = i9;
        this.zab = i10;
        this.zac = bundle;
    }

    @RecentlyNonNull
    @KeepForSdk
    public int getType() {
        return this.zab;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@RecentlyNonNull Parcel parcel, @RecentlyNonNull int i9) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zaa);
        SafeParcelWriter.writeInt(parcel, 2, getType());
        SafeParcelWriter.writeBundle(parcel, 3, this.zac, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    public GoogleSignInOptionsExtensionParcelable(@RecentlyNonNull GoogleSignInOptionsExtension googleSignInOptionsExtension) {
        this(1, googleSignInOptionsExtension.getExtensionType(), googleSignInOptionsExtension.toBundle());
    }
}
