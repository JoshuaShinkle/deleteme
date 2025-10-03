package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.maps.model.StreetViewPanoramaOrientation;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;

@SafeParcelable.Class(creator = "StreetViewPanoramaCameraCreator")
@SafeParcelable.Reserved({1})
/* loaded from: classes2.dex */
public class StreetViewPanoramaCamera extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Parcelable.Creator<StreetViewPanoramaCamera> CREATOR = new zzm();

    @SafeParcelable.Field(m17520id = 4)
    public final float bearing;

    @SafeParcelable.Field(m17520id = 3)
    public final float tilt;

    @SafeParcelable.Field(m17520id = 2)
    public final float zoom;
    private final StreetViewPanoramaOrientation zzeg;

    public static final class Builder {
        public float bearing;
        public float tilt;
        public float zoom;

        public Builder() {
        }

        public Builder(StreetViewPanoramaCamera streetViewPanoramaCamera) {
            Preconditions.checkNotNull(streetViewPanoramaCamera, "StreetViewPanoramaCamera");
            this.zoom = streetViewPanoramaCamera.zoom;
            this.bearing = streetViewPanoramaCamera.bearing;
            this.tilt = streetViewPanoramaCamera.tilt;
        }

        public final Builder bearing(float f9) {
            this.bearing = f9;
            return this;
        }

        public final StreetViewPanoramaCamera build() {
            return new StreetViewPanoramaCamera(this.zoom, this.tilt, this.bearing);
        }

        public final Builder orientation(StreetViewPanoramaOrientation streetViewPanoramaOrientation) {
            Preconditions.checkNotNull(streetViewPanoramaOrientation, "StreetViewPanoramaOrientation");
            this.tilt = streetViewPanoramaOrientation.tilt;
            this.bearing = streetViewPanoramaOrientation.bearing;
            return this;
        }

        public final Builder tilt(float f9) {
            this.tilt = f9;
            return this;
        }

        public final Builder zoom(float f9) {
            this.zoom = f9;
            return this;
        }
    }

    @SafeParcelable.Constructor
    public StreetViewPanoramaCamera(@SafeParcelable.Param(m17521id = 2) float f9, @SafeParcelable.Param(m17521id = 3) float f10, @SafeParcelable.Param(m17521id = 4) float f11) {
        boolean z8 = -90.0f <= f10 && f10 <= 90.0f;
        StringBuilder sb = new StringBuilder(62);
        sb.append("Tilt needs to be between -90 and 90 inclusive: ");
        sb.append(f10);
        Preconditions.checkArgument(z8, sb.toString());
        this.zoom = ((double) f9) <= FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE ? 0.0f : f9;
        this.tilt = BitmapDescriptorFactory.HUE_RED + f10;
        this.bearing = (((double) f11) <= FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE ? (f11 % 360.0f) + 360.0f : f11) % 360.0f;
        this.zzeg = new StreetViewPanoramaOrientation.Builder().tilt(f10).bearing(f11).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof StreetViewPanoramaCamera)) {
            return false;
        }
        StreetViewPanoramaCamera streetViewPanoramaCamera = (StreetViewPanoramaCamera) obj;
        return Float.floatToIntBits(this.zoom) == Float.floatToIntBits(streetViewPanoramaCamera.zoom) && Float.floatToIntBits(this.tilt) == Float.floatToIntBits(streetViewPanoramaCamera.tilt) && Float.floatToIntBits(this.bearing) == Float.floatToIntBits(streetViewPanoramaCamera.bearing);
    }

    public StreetViewPanoramaOrientation getOrientation() {
        return this.zzeg;
    }

    public int hashCode() {
        return Objects.hashCode(Float.valueOf(this.zoom), Float.valueOf(this.tilt), Float.valueOf(this.bearing));
    }

    public String toString() {
        return Objects.toStringHelper(this).add("zoom", Float.valueOf(this.zoom)).add("tilt", Float.valueOf(this.tilt)).add("bearing", Float.valueOf(this.bearing)).toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i9) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeFloat(parcel, 2, this.zoom);
        SafeParcelWriter.writeFloat(parcel, 3, this.tilt);
        SafeParcelWriter.writeFloat(parcel, 4, this.bearing);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    public static Builder builder(StreetViewPanoramaCamera streetViewPanoramaCamera) {
        return new Builder(streetViewPanoramaCamera);
    }
}
