package com.google.android.gms.maps.model;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.dynamic.IObjectWrapper;

@SafeParcelable.Class(creator = "GroundOverlayOptionsCreator")
@SafeParcelable.Reserved({1})
/* loaded from: classes2.dex */
public final class GroundOverlayOptions extends AbstractSafeParcelable {
    public static final Parcelable.Creator<GroundOverlayOptions> CREATOR = new zzd();
    public static final float NO_DIMENSION = -1.0f;

    @SafeParcelable.Field(getter = "getBearing", m17520id = 7)
    private float bearing;

    @SafeParcelable.Field(getter = "getHeight", m17520id = 5)
    private float height;

    @SafeParcelable.Field(getter = "getWidth", m17520id = 4)
    private float width;

    @SafeParcelable.Field(getter = "getZIndex", m17520id = 8)
    private float zzcs;

    @SafeParcelable.Field(getter = "isVisible", m17520id = 9)
    private boolean zzct;

    @SafeParcelable.Field(getter = "isClickable", m17520id = 13)
    private boolean zzcu;

    @SafeParcelable.Field(getter = "getWrappedImageDescriptorImplBinder", m17520id = 2, type = "android.os.IBinder")
    private BitmapDescriptor zzcx;

    @SafeParcelable.Field(getter = "getLocation", m17520id = 3)
    private LatLng zzcy;

    @SafeParcelable.Field(getter = "getBounds", m17520id = 6)
    private LatLngBounds zzcz;

    @SafeParcelable.Field(getter = "getTransparency", m17520id = 10)
    private float zzda;

    @SafeParcelable.Field(getter = "getAnchorU", m17520id = 11)
    private float zzdb;

    @SafeParcelable.Field(getter = "getAnchorV", m17520id = 12)
    private float zzdc;

    @SafeParcelable.Constructor
    public GroundOverlayOptions(@SafeParcelable.Param(m17521id = 2) IBinder iBinder, @SafeParcelable.Param(m17521id = 3) LatLng latLng, @SafeParcelable.Param(m17521id = 4) float f9, @SafeParcelable.Param(m17521id = 5) float f10, @SafeParcelable.Param(m17521id = 6) LatLngBounds latLngBounds, @SafeParcelable.Param(m17521id = 7) float f11, @SafeParcelable.Param(m17521id = 8) float f12, @SafeParcelable.Param(m17521id = 9) boolean z8, @SafeParcelable.Param(m17521id = 10) float f13, @SafeParcelable.Param(m17521id = 11) float f14, @SafeParcelable.Param(m17521id = 12) float f15, @SafeParcelable.Param(m17521id = 13) boolean z9) {
        this.zzct = true;
        this.zzda = BitmapDescriptorFactory.HUE_RED;
        this.zzdb = 0.5f;
        this.zzdc = 0.5f;
        this.zzcu = false;
        this.zzcx = new BitmapDescriptor(IObjectWrapper.Stub.asInterface(iBinder));
        this.zzcy = latLng;
        this.width = f9;
        this.height = f10;
        this.zzcz = latLngBounds;
        this.bearing = f11;
        this.zzcs = f12;
        this.zzct = z8;
        this.zzda = f13;
        this.zzdb = f14;
        this.zzdc = f15;
        this.zzcu = z9;
    }

    private final GroundOverlayOptions zza(LatLng latLng, float f9, float f10) {
        this.zzcy = latLng;
        this.width = f9;
        this.height = f10;
        return this;
    }

    public final GroundOverlayOptions anchor(float f9, float f10) {
        this.zzdb = f9;
        this.zzdc = f10;
        return this;
    }

    public final GroundOverlayOptions bearing(float f9) {
        this.bearing = ((f9 % 360.0f) + 360.0f) % 360.0f;
        return this;
    }

    public final GroundOverlayOptions clickable(boolean z8) {
        this.zzcu = z8;
        return this;
    }

    public final float getAnchorU() {
        return this.zzdb;
    }

    public final float getAnchorV() {
        return this.zzdc;
    }

    public final float getBearing() {
        return this.bearing;
    }

    public final LatLngBounds getBounds() {
        return this.zzcz;
    }

    public final float getHeight() {
        return this.height;
    }

    public final BitmapDescriptor getImage() {
        return this.zzcx;
    }

    public final LatLng getLocation() {
        return this.zzcy;
    }

    public final float getTransparency() {
        return this.zzda;
    }

    public final float getWidth() {
        return this.width;
    }

    public final float getZIndex() {
        return this.zzcs;
    }

    public final GroundOverlayOptions image(BitmapDescriptor bitmapDescriptor) {
        Preconditions.checkNotNull(bitmapDescriptor, "imageDescriptor must not be null");
        this.zzcx = bitmapDescriptor;
        return this;
    }

    public final boolean isClickable() {
        return this.zzcu;
    }

    public final boolean isVisible() {
        return this.zzct;
    }

    public final GroundOverlayOptions position(LatLng latLng, float f9) {
        Preconditions.checkState(this.zzcz == null, "Position has already been set using positionFromBounds");
        Preconditions.checkArgument(latLng != null, "Location must be specified");
        Preconditions.checkArgument(f9 >= BitmapDescriptorFactory.HUE_RED, "Width must be non-negative");
        return zza(latLng, f9, -1.0f);
    }

    public final GroundOverlayOptions positionFromBounds(LatLngBounds latLngBounds) {
        LatLng latLng = this.zzcy;
        boolean z8 = latLng == null;
        String strValueOf = String.valueOf(latLng);
        StringBuilder sb = new StringBuilder(strValueOf.length() + 46);
        sb.append("Position has already been set using position: ");
        sb.append(strValueOf);
        Preconditions.checkState(z8, sb.toString());
        this.zzcz = latLngBounds;
        return this;
    }

    public final GroundOverlayOptions transparency(float f9) {
        Preconditions.checkArgument(f9 >= BitmapDescriptorFactory.HUE_RED && f9 <= 1.0f, "Transparency must be in the range [0..1]");
        this.zzda = f9;
        return this;
    }

    public final GroundOverlayOptions visible(boolean z8) {
        this.zzct = z8;
        return this;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i9) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeIBinder(parcel, 2, this.zzcx.zzb().asBinder(), false);
        SafeParcelWriter.writeParcelable(parcel, 3, getLocation(), i9, false);
        SafeParcelWriter.writeFloat(parcel, 4, getWidth());
        SafeParcelWriter.writeFloat(parcel, 5, getHeight());
        SafeParcelWriter.writeParcelable(parcel, 6, getBounds(), i9, false);
        SafeParcelWriter.writeFloat(parcel, 7, getBearing());
        SafeParcelWriter.writeFloat(parcel, 8, getZIndex());
        SafeParcelWriter.writeBoolean(parcel, 9, isVisible());
        SafeParcelWriter.writeFloat(parcel, 10, getTransparency());
        SafeParcelWriter.writeFloat(parcel, 11, getAnchorU());
        SafeParcelWriter.writeFloat(parcel, 12, getAnchorV());
        SafeParcelWriter.writeBoolean(parcel, 13, isClickable());
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    public final GroundOverlayOptions zIndex(float f9) {
        this.zzcs = f9;
        return this;
    }

    public final GroundOverlayOptions position(LatLng latLng, float f9, float f10) {
        Preconditions.checkState(this.zzcz == null, "Position has already been set using positionFromBounds");
        Preconditions.checkArgument(latLng != null, "Location must be specified");
        Preconditions.checkArgument(f9 >= BitmapDescriptorFactory.HUE_RED, "Width must be non-negative");
        Preconditions.checkArgument(f10 >= BitmapDescriptorFactory.HUE_RED, "Height must be non-negative");
        return zza(latLng, f9, f10);
    }

    public GroundOverlayOptions() {
        this.zzct = true;
        this.zzda = BitmapDescriptorFactory.HUE_RED;
        this.zzdb = 0.5f;
        this.zzdc = 0.5f;
        this.zzcu = false;
    }
}
