package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;

@SafeParcelable.Class(creator = "StreetViewPanoramaLinkCreator")
@SafeParcelable.Reserved({1})
/* loaded from: classes2.dex */
public class StreetViewPanoramaLink extends AbstractSafeParcelable {
    public static final Parcelable.Creator<StreetViewPanoramaLink> CREATOR = new zzn();

    @SafeParcelable.Field(m17520id = 3)
    public final float bearing;

    @SafeParcelable.Field(m17520id = 2)
    public final String panoId;

    @SafeParcelable.Constructor
    public StreetViewPanoramaLink(@SafeParcelable.Param(m17521id = 2) String str, @SafeParcelable.Param(m17521id = 3) float f9) {
        this.panoId = str;
        this.bearing = (((double) f9) <= FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE ? (f9 % 360.0f) + 360.0f : f9) % 360.0f;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof StreetViewPanoramaLink)) {
            return false;
        }
        StreetViewPanoramaLink streetViewPanoramaLink = (StreetViewPanoramaLink) obj;
        return this.panoId.equals(streetViewPanoramaLink.panoId) && Float.floatToIntBits(this.bearing) == Float.floatToIntBits(streetViewPanoramaLink.bearing);
    }

    public int hashCode() {
        return Objects.hashCode(this.panoId, Float.valueOf(this.bearing));
    }

    public String toString() {
        return Objects.toStringHelper(this).add("panoId", this.panoId).add("bearing", Float.valueOf(this.bearing)).toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i9) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.panoId, false);
        SafeParcelWriter.writeFloat(parcel, 3, this.bearing);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}
