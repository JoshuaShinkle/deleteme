package com.google.android.gms.plus.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.auth.api.credentials.CredentialsApi;
import com.google.android.gms.common.annotation.KeepName;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@KeepName
@SafeParcelable.Class(creator = "PlusCommonExtrasCreator")
/* loaded from: classes2.dex */
public class PlusCommonExtras extends AbstractSafeParcelable {
    public static final Parcelable.Creator<PlusCommonExtras> CREATOR = new zzl();

    @SafeParcelable.VersionField(getter = "getVersionCode", m17523id = CredentialsApi.ACTIVITY_RESULT_ADD_ACCOUNT)
    private final int zzw;

    @SafeParcelable.Field(getter = "getGpsrc", m17520id = 1)
    private String zzx;

    @SafeParcelable.Field(getter = "getClientCallingPackage", m17520id = 2)
    private String zzy;

    public PlusCommonExtras() {
        this.zzw = 1;
        this.zzx = "";
        this.zzy = "";
    }

    @SafeParcelable.Constructor
    public PlusCommonExtras(@SafeParcelable.Param(m17521id = CredentialsApi.ACTIVITY_RESULT_ADD_ACCOUNT) int i9, @SafeParcelable.Param(m17521id = 1) String str, @SafeParcelable.Param(m17521id = 2) String str2) {
        this.zzw = i9;
        this.zzx = str;
        this.zzy = str2;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof PlusCommonExtras)) {
            return false;
        }
        PlusCommonExtras plusCommonExtras = (PlusCommonExtras) obj;
        return this.zzw == plusCommonExtras.zzw && Objects.equal(this.zzx, plusCommonExtras.zzx) && Objects.equal(this.zzy, plusCommonExtras.zzy);
    }

    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(this.zzw), this.zzx, this.zzy);
    }

    public String toString() {
        return Objects.toStringHelper(this).add("versionCode", Integer.valueOf(this.zzw)).add("Gpsrc", this.zzx).add("ClientCallingPackage", this.zzy).toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i9) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.zzx, false);
        SafeParcelWriter.writeString(parcel, 2, this.zzy, false);
        SafeParcelWriter.writeInt(parcel, CredentialsApi.ACTIVITY_RESULT_ADD_ACCOUNT, this.zzw);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}
