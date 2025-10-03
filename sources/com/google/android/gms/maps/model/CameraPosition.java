package com.google.android.gms.maps.model;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;

@SafeParcelable.Class(creator = "CameraPositionCreator")
@SafeParcelable.Reserved({1})
/* loaded from: classes2.dex */
public final class CameraPosition extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Parcelable.Creator<CameraPosition> CREATOR = new zza();

    @SafeParcelable.Field(m17520id = 5)
    public final float bearing;

    @SafeParcelable.Field(m17520id = 2)
    public final LatLng target;

    @SafeParcelable.Field(m17520id = 4)
    public final float tilt;

    @SafeParcelable.Field(m17520id = 3)
    public final float zoom;

    public static final class Builder {
        private float bearing;
        private LatLng target;
        private float tilt;
        private float zoom;

        public Builder() {
        }

        public Builder(CameraPosition cameraPosition) {
            this.target = cameraPosition.target;
            this.zoom = cameraPosition.zoom;
            this.tilt = cameraPosition.tilt;
            this.bearing = cameraPosition.bearing;
        }

        public final Builder bearing(float f9) {
            this.bearing = f9;
            return this;
        }

        public final CameraPosition build() {
            return new CameraPosition(this.target, this.zoom, this.tilt, this.bearing);
        }

        public final Builder target(LatLng latLng) {
            this.target = latLng;
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
    public CameraPosition(@SafeParcelable.Param(m17521id = 2) LatLng latLng, @SafeParcelable.Param(m17521id = 3) float f9, @SafeParcelable.Param(m17521id = 4) float f10, @SafeParcelable.Param(m17521id = 5) float f11) {
        Preconditions.checkNotNull(latLng, "null camera target");
        Preconditions.checkArgument(BitmapDescriptorFactory.HUE_RED <= f10 && f10 <= 90.0f, "Tilt needs to be between 0 and 90 inclusive: %s", Float.valueOf(f10));
        this.target = latLng;
        this.zoom = f9;
        this.tilt = f10 + BitmapDescriptorFactory.HUE_RED;
        this.bearing = (((double) f11) <= FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE ? (f11 % 360.0f) + 360.0f : f11) % 360.0f;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static CameraPosition createFromAttributes(Context context, AttributeSet attributeSet) {
        return GoogleMapOptions.zzb(context, attributeSet);
    }

    public static final CameraPosition fromLatLngZoom(LatLng latLng, float f9) {
        return new CameraPosition(latLng, f9, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CameraPosition)) {
            return false;
        }
        CameraPosition cameraPosition = (CameraPosition) obj;
        return this.target.equals(cameraPosition.target) && Float.floatToIntBits(this.zoom) == Float.floatToIntBits(cameraPosition.zoom) && Float.floatToIntBits(this.tilt) == Float.floatToIntBits(cameraPosition.tilt) && Float.floatToIntBits(this.bearing) == Float.floatToIntBits(cameraPosition.bearing);
    }

    public final int hashCode() {
        return Objects.hashCode(this.target, Float.valueOf(this.zoom), Float.valueOf(this.tilt), Float.valueOf(this.bearing));
    }

    public final String toString() {
        return Objects.toStringHelper(this).add("target", this.target).add("zoom", Float.valueOf(this.zoom)).add("tilt", Float.valueOf(this.tilt)).add("bearing", Float.valueOf(this.bearing)).toString();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i9) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, this.target, i9, false);
        SafeParcelWriter.writeFloat(parcel, 3, this.zoom);
        SafeParcelWriter.writeFloat(parcel, 4, this.tilt);
        SafeParcelWriter.writeFloat(parcel, 5, this.bearing);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    public static Builder builder(CameraPosition cameraPosition) {
        return new Builder(cameraPosition);
    }
}
