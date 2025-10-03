package com.google.android.gms.maps.model;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.dynamic.IObjectWrapper;

@SafeParcelable.Class(creator = "MarkerOptionsCreator")
@SafeParcelable.Reserved({1})
/* loaded from: classes2.dex */
public final class MarkerOptions extends AbstractSafeParcelable {
    public static final Parcelable.Creator<MarkerOptions> CREATOR = new zzh();

    @SafeParcelable.Field(defaultValue = "1.0f", getter = "getAlpha", m17520id = 14)
    private float alpha;

    @SafeParcelable.Field(getter = "getPosition", m17520id = 2)
    private LatLng position;

    @SafeParcelable.Field(getter = "getZIndex", m17520id = 15)
    private float zzcs;

    @SafeParcelable.Field(getter = "isVisible", m17520id = 9)
    private boolean zzct;

    @SafeParcelable.Field(getter = "getAnchorU", m17520id = 6)
    private float zzdb;

    @SafeParcelable.Field(getter = "getAnchorV", m17520id = 7)
    private float zzdc;

    @SafeParcelable.Field(getter = "getTitle", m17520id = 3)
    private String zzdn;

    @SafeParcelable.Field(getter = "getSnippet", m17520id = 4)
    private String zzdo;

    @SafeParcelable.Field(getter = "getWrappedIconDescriptorImplBinder", m17520id = 5, type = "android.os.IBinder")
    private BitmapDescriptor zzdp;

    @SafeParcelable.Field(getter = "isDraggable", m17520id = 8)
    private boolean zzdq;

    @SafeParcelable.Field(getter = "isFlat", m17520id = 10)
    private boolean zzdr;

    @SafeParcelable.Field(getter = "getRotation", m17520id = 11)
    private float zzds;

    @SafeParcelable.Field(defaultValue = "0.5f", getter = "getInfoWindowAnchorU", m17520id = 12)
    private float zzdt;

    @SafeParcelable.Field(getter = "getInfoWindowAnchorV", m17520id = 13)
    private float zzdu;

    public MarkerOptions() {
        this.zzdb = 0.5f;
        this.zzdc = 1.0f;
        this.zzct = true;
        this.zzdr = false;
        this.zzds = BitmapDescriptorFactory.HUE_RED;
        this.zzdt = 0.5f;
        this.zzdu = BitmapDescriptorFactory.HUE_RED;
        this.alpha = 1.0f;
    }

    public final MarkerOptions alpha(float f9) {
        this.alpha = f9;
        return this;
    }

    public final MarkerOptions anchor(float f9, float f10) {
        this.zzdb = f9;
        this.zzdc = f10;
        return this;
    }

    public final MarkerOptions draggable(boolean z8) {
        this.zzdq = z8;
        return this;
    }

    public final MarkerOptions flat(boolean z8) {
        this.zzdr = z8;
        return this;
    }

    public final float getAlpha() {
        return this.alpha;
    }

    public final float getAnchorU() {
        return this.zzdb;
    }

    public final float getAnchorV() {
        return this.zzdc;
    }

    public final BitmapDescriptor getIcon() {
        return this.zzdp;
    }

    public final float getInfoWindowAnchorU() {
        return this.zzdt;
    }

    public final float getInfoWindowAnchorV() {
        return this.zzdu;
    }

    public final LatLng getPosition() {
        return this.position;
    }

    public final float getRotation() {
        return this.zzds;
    }

    public final String getSnippet() {
        return this.zzdo;
    }

    public final String getTitle() {
        return this.zzdn;
    }

    public final float getZIndex() {
        return this.zzcs;
    }

    public final MarkerOptions icon(BitmapDescriptor bitmapDescriptor) {
        this.zzdp = bitmapDescriptor;
        return this;
    }

    public final MarkerOptions infoWindowAnchor(float f9, float f10) {
        this.zzdt = f9;
        this.zzdu = f10;
        return this;
    }

    public final boolean isDraggable() {
        return this.zzdq;
    }

    public final boolean isFlat() {
        return this.zzdr;
    }

    public final boolean isVisible() {
        return this.zzct;
    }

    public final MarkerOptions position(LatLng latLng) {
        if (latLng == null) {
            throw new IllegalArgumentException("latlng cannot be null - a position is required.");
        }
        this.position = latLng;
        return this;
    }

    public final MarkerOptions rotation(float f9) {
        this.zzds = f9;
        return this;
    }

    public final MarkerOptions snippet(String str) {
        this.zzdo = str;
        return this;
    }

    public final MarkerOptions title(String str) {
        this.zzdn = str;
        return this;
    }

    public final MarkerOptions visible(boolean z8) {
        this.zzct = z8;
        return this;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i9) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, getPosition(), i9, false);
        SafeParcelWriter.writeString(parcel, 3, getTitle(), false);
        SafeParcelWriter.writeString(parcel, 4, getSnippet(), false);
        BitmapDescriptor bitmapDescriptor = this.zzdp;
        SafeParcelWriter.writeIBinder(parcel, 5, bitmapDescriptor == null ? null : bitmapDescriptor.zzb().asBinder(), false);
        SafeParcelWriter.writeFloat(parcel, 6, getAnchorU());
        SafeParcelWriter.writeFloat(parcel, 7, getAnchorV());
        SafeParcelWriter.writeBoolean(parcel, 8, isDraggable());
        SafeParcelWriter.writeBoolean(parcel, 9, isVisible());
        SafeParcelWriter.writeBoolean(parcel, 10, isFlat());
        SafeParcelWriter.writeFloat(parcel, 11, getRotation());
        SafeParcelWriter.writeFloat(parcel, 12, getInfoWindowAnchorU());
        SafeParcelWriter.writeFloat(parcel, 13, getInfoWindowAnchorV());
        SafeParcelWriter.writeFloat(parcel, 14, getAlpha());
        SafeParcelWriter.writeFloat(parcel, 15, getZIndex());
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    public final MarkerOptions zIndex(float f9) {
        this.zzcs = f9;
        return this;
    }

    @SafeParcelable.Constructor
    public MarkerOptions(@SafeParcelable.Param(m17521id = 2) LatLng latLng, @SafeParcelable.Param(m17521id = 3) String str, @SafeParcelable.Param(m17521id = 4) String str2, @SafeParcelable.Param(m17521id = 5) IBinder iBinder, @SafeParcelable.Param(m17521id = 6) float f9, @SafeParcelable.Param(m17521id = 7) float f10, @SafeParcelable.Param(m17521id = 8) boolean z8, @SafeParcelable.Param(m17521id = 9) boolean z9, @SafeParcelable.Param(m17521id = 10) boolean z10, @SafeParcelable.Param(m17521id = 11) float f11, @SafeParcelable.Param(m17521id = 12) float f12, @SafeParcelable.Param(m17521id = 13) float f13, @SafeParcelable.Param(m17521id = 14) float f14, @SafeParcelable.Param(m17521id = 15) float f15) {
        this.zzdb = 0.5f;
        this.zzdc = 1.0f;
        this.zzct = true;
        this.zzdr = false;
        this.zzds = BitmapDescriptorFactory.HUE_RED;
        this.zzdt = 0.5f;
        this.zzdu = BitmapDescriptorFactory.HUE_RED;
        this.alpha = 1.0f;
        this.position = latLng;
        this.zzdn = str;
        this.zzdo = str2;
        if (iBinder == null) {
            this.zzdp = null;
        } else {
            this.zzdp = new BitmapDescriptor(IObjectWrapper.Stub.asInterface(iBinder));
        }
        this.zzdb = f9;
        this.zzdc = f10;
        this.zzdq = z8;
        this.zzct = z9;
        this.zzdr = z10;
        this.zzds = f11;
        this.zzdt = f12;
        this.zzdu = f13;
        this.alpha = f14;
        this.zzcs = f15;
    }
}
