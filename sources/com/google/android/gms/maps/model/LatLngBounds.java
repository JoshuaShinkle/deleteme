package com.google.android.gms.maps.model;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.maps.GoogleMapOptions;

@SafeParcelable.Class(creator = "LatLngBoundsCreator")
@SafeParcelable.Reserved({1})
/* loaded from: classes2.dex */
public final class LatLngBounds extends AbstractSafeParcelable implements ReflectedParcelable {

    @KeepForSdk
    public static final Parcelable.Creator<LatLngBounds> CREATOR = new zze();

    @SafeParcelable.Field(m17520id = 3)
    public final LatLng northeast;

    @SafeParcelable.Field(m17520id = 2)
    public final LatLng southwest;

    public static final class Builder {
        private double zzdh = Double.POSITIVE_INFINITY;
        private double zzdi = Double.NEGATIVE_INFINITY;
        private double zzdj = Double.NaN;
        private double zzdk = Double.NaN;

        public final LatLngBounds build() {
            Preconditions.checkState(!Double.isNaN(this.zzdj), "no included points");
            return new LatLngBounds(new LatLng(this.zzdh, this.zzdj), new LatLng(this.zzdi, this.zzdk));
        }

        public final Builder include(LatLng latLng) {
            this.zzdh = Math.min(this.zzdh, latLng.latitude);
            this.zzdi = Math.max(this.zzdi, latLng.latitude);
            double d9 = latLng.longitude;
            if (!Double.isNaN(this.zzdj)) {
                double d10 = this.zzdj;
                double d11 = this.zzdk;
                boolean z8 = false;
                if (d10 > d11 ? d10 <= d9 || d9 <= d11 : d10 <= d9 && d9 <= d11) {
                    z8 = true;
                }
                if (!z8) {
                    if (LatLngBounds.zza(d10, d9) < LatLngBounds.zzb(this.zzdk, d9)) {
                        this.zzdj = d9;
                    }
                }
                return this;
            }
            this.zzdj = d9;
            this.zzdk = d9;
            return this;
        }
    }

    @SafeParcelable.Constructor
    public LatLngBounds(@SafeParcelable.Param(m17521id = 2) LatLng latLng, @SafeParcelable.Param(m17521id = 3) LatLng latLng2) {
        Preconditions.checkNotNull(latLng, "null southwest");
        Preconditions.checkNotNull(latLng2, "null northeast");
        double d9 = latLng2.latitude;
        double d10 = latLng.latitude;
        Preconditions.checkArgument(d9 >= d10, "southern latitude exceeds northern latitude (%s > %s)", Double.valueOf(d10), Double.valueOf(latLng2.latitude));
        this.southwest = latLng;
        this.northeast = latLng2;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static LatLngBounds createFromAttributes(Context context, AttributeSet attributeSet) {
        return GoogleMapOptions.zza(context, attributeSet);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static double zza(double d9, double d10) {
        return ((d9 - d10) + 360.0d) % 360.0d;
    }

    private final boolean zza(double d9) {
        double d10 = this.southwest.longitude;
        double d11 = this.northeast.longitude;
        return d10 <= d11 ? d10 <= d9 && d9 <= d11 : d10 <= d9 || d9 <= d11;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static double zzb(double d9, double d10) {
        return ((d10 - d9) + 360.0d) % 360.0d;
    }

    public final boolean contains(LatLng latLng) {
        double d9 = latLng.latitude;
        return ((this.southwest.latitude > d9 ? 1 : (this.southwest.latitude == d9 ? 0 : -1)) <= 0 && (d9 > this.northeast.latitude ? 1 : (d9 == this.northeast.latitude ? 0 : -1)) <= 0) && zza(latLng.longitude);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LatLngBounds)) {
            return false;
        }
        LatLngBounds latLngBounds = (LatLngBounds) obj;
        return this.southwest.equals(latLngBounds.southwest) && this.northeast.equals(latLngBounds.northeast);
    }

    public final LatLng getCenter() {
        LatLng latLng = this.southwest;
        double d9 = latLng.latitude;
        LatLng latLng2 = this.northeast;
        double d10 = (d9 + latLng2.latitude) / 2.0d;
        double d11 = latLng2.longitude;
        double d12 = latLng.longitude;
        if (d12 > d11) {
            d11 += 360.0d;
        }
        return new LatLng(d10, (d11 + d12) / 2.0d);
    }

    public final int hashCode() {
        return Objects.hashCode(this.southwest, this.northeast);
    }

    public final LatLngBounds including(LatLng latLng) {
        double dMin = Math.min(this.southwest.latitude, latLng.latitude);
        double dMax = Math.max(this.northeast.latitude, latLng.latitude);
        double d9 = this.northeast.longitude;
        double d10 = this.southwest.longitude;
        double d11 = latLng.longitude;
        if (!zza(d11)) {
            if (zza(d10, d11) < zzb(d9, d11)) {
                d10 = d11;
            } else {
                d9 = d11;
            }
        }
        return new LatLngBounds(new LatLng(dMin, d10), new LatLng(dMax, d9));
    }

    public final String toString() {
        return Objects.toStringHelper(this).add("southwest", this.southwest).add("northeast", this.northeast).toString();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i9) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, this.southwest, i9, false);
        SafeParcelWriter.writeParcelable(parcel, 3, this.northeast, i9, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}
