package com.google.android.gms.common.internal;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.RecentlyNonNull;
import com.google.android.gms.auth.api.credentials.CredentialsApi;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@KeepForSdk
@SafeParcelable.Class(creator = "ClientIdentityCreator")
@SafeParcelable.Reserved({CredentialsApi.ACTIVITY_RESULT_ADD_ACCOUNT})
/* loaded from: classes2.dex */
public class ClientIdentity extends AbstractSafeParcelable {

    @RecentlyNonNull
    @KeepForSdk
    public static final Parcelable.Creator<ClientIdentity> CREATOR = new zaa();

    @SafeParcelable.Field(defaultValueUnchecked = "0", m17520id = 1)
    private final int zaa;

    @SafeParcelable.Field(defaultValueUnchecked = "null", m17520id = 2)
    private final String zab;

    @SafeParcelable.Constructor
    public ClientIdentity(@RecentlyNonNull @SafeParcelable.Param(m17521id = 1) int i9, @SafeParcelable.Param(m17521id = 2) String str) {
        this.zaa = i9;
        this.zab = str;
    }

    @RecentlyNonNull
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ClientIdentity)) {
            return false;
        }
        ClientIdentity clientIdentity = (ClientIdentity) obj;
        return clientIdentity.zaa == this.zaa && Objects.equal(clientIdentity.zab, this.zab);
    }

    @RecentlyNonNull
    public int hashCode() {
        return this.zaa;
    }

    @RecentlyNonNull
    public String toString() {
        int i9 = this.zaa;
        String str = this.zab;
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 12);
        sb.append(i9);
        sb.append(":");
        sb.append(str);
        return sb.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@RecentlyNonNull Parcel parcel, @RecentlyNonNull int i9) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zaa);
        SafeParcelWriter.writeString(parcel, 2, this.zab, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}
