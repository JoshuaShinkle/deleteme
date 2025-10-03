package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class(creator = "LatLngCreator")
@SafeParcelable.Reserved({1})
/* loaded from: classes2.dex */
public final class LatLng extends AbstractSafeParcelable implements ReflectedParcelable {

    @KeepForSdk
    public static final Parcelable.Creator<LatLng> CREATOR = new zzf();

    @SafeParcelable.Field(m17520id = 2)
    public final double latitude;

    @SafeParcelable.Field(m17520id = 3)
    public final double longitude;

    @SafeParcelable.Constructor
    public LatLng(@SafeParcelable.Param(m17521id = 2) double d9, @SafeParcelable.Param(m17521id = 3) double d10) {
        if (-180.0d > d10 || d10 >= 180.0d) {
            this.longitude = ((((d10 - 180.0d) % 360.0d) + 360.0d) % 360.0d) - 180.0d;
        } else {
            this.longitude = d10;
        }
        this.latitude = Math.max(-90.0d, Math.min(90.0d, d9));
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LatLng)) {
            return false;
        }
        LatLng latLng = (LatLng) obj;
        return Double.doubleToLongBits(this.latitude) == Double.doubleToLongBits(latLng.latitude) && Double.doubleToLongBits(this.longitude) == Double.doubleToLongBits(latLng.longitude);
    }

    public final int hashCode() {
        long jDoubleToLongBits = Double.doubleToLongBits(this.latitude);
        int i9 = ((int) (jDoubleToLongBits ^ (jDoubleToLongBits >>> 32))) + 31;
        long jDoubleToLongBits2 = Double.doubleToLongBits(this.longitude);
        return (i9 * 31) + ((int) ((jDoubleToLongBits2 >>> 32) ^ jDoubleToLongBits2));
    }

    public final String toString() {
        double d9 = this.latitude;
        double d10 = this.longitude;
        StringBuilder sb = new StringBuilder(60);
        sb.append("lat/lng: (");
        sb.append(d9);
        sb.append(",");
        sb.append(d10);
        sb.append(")");
        return sb.toString();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i9) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeDouble(parcel, 2, this.latitude);
        SafeParcelWriter.writeDouble(parcel, 3, this.longitude);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}
