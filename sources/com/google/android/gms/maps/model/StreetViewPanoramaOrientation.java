package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;

@SafeParcelable.Class(creator = "StreetViewPanoramaOrientationCreator")
@SafeParcelable.Reserved({1})
/* loaded from: classes2.dex */
public class StreetViewPanoramaOrientation extends AbstractSafeParcelable {
    public static final Parcelable.Creator<StreetViewPanoramaOrientation> CREATOR = new zzp();

    @SafeParcelable.Field(m17520id = 3)
    public final float bearing;

    @SafeParcelable.Field(m17520id = 2)
    public final float tilt;

    public static final class Builder {
        public float bearing;
        public float tilt;

        public Builder() {
        }

        public Builder(StreetViewPanoramaOrientation streetViewPanoramaOrientation) {
            Preconditions.checkNotNull(streetViewPanoramaOrientation, "StreetViewPanoramaOrientation");
            this.bearing = streetViewPanoramaOrientation.bearing;
            this.tilt = streetViewPanoramaOrientation.tilt;
        }

        public final Builder bearing(float f9) {
            this.bearing = f9;
            return this;
        }

        public final StreetViewPanoramaOrientation build() {
            return new StreetViewPanoramaOrientation(this.tilt, this.bearing);
        }

        public final Builder tilt(float f9) {
            this.tilt = f9;
            return this;
        }
    }

    @SafeParcelable.Constructor
    public StreetViewPanoramaOrientation(@SafeParcelable.Param(m17521id = 2) float f9, @SafeParcelable.Param(m17521id = 3) float f10) {
        boolean z8 = -90.0f <= f9 && f9 <= 90.0f;
        StringBuilder sb = new StringBuilder(62);
        sb.append("Tilt needs to be between -90 and 90 inclusive: ");
        sb.append(f9);
        Preconditions.checkArgument(z8, sb.toString());
        this.tilt = f9 + BitmapDescriptorFactory.HUE_RED;
        this.bearing = (((double) f10) <= FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE ? (f10 % 360.0f) + 360.0f : f10) % 360.0f;
    }

    public static Builder builder() {
        return new Builder();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof StreetViewPanoramaOrientation)) {
            return false;
        }
        StreetViewPanoramaOrientation streetViewPanoramaOrientation = (StreetViewPanoramaOrientation) obj;
        return Float.floatToIntBits(this.tilt) == Float.floatToIntBits(streetViewPanoramaOrientation.tilt) && Float.floatToIntBits(this.bearing) == Float.floatToIntBits(streetViewPanoramaOrientation.bearing);
    }

    public int hashCode() {
        return Objects.hashCode(Float.valueOf(this.tilt), Float.valueOf(this.bearing));
    }

    public String toString() {
        return Objects.toStringHelper(this).add("tilt", Float.valueOf(this.tilt)).add("bearing", Float.valueOf(this.bearing)).toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i9) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeFloat(parcel, 2, this.tilt);
        SafeParcelWriter.writeFloat(parcel, 3, this.bearing);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    public static Builder builder(StreetViewPanoramaOrientation streetViewPanoramaOrientation) {
        return new Builder(streetViewPanoramaOrientation);
    }
}
