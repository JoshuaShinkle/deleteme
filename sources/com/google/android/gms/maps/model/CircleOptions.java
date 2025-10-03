package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.util.List;

@SafeParcelable.Class(creator = "CircleOptionsCreator")
@SafeParcelable.Reserved({1})
/* loaded from: classes2.dex */
public final class CircleOptions extends AbstractSafeParcelable {
    public static final Parcelable.Creator<CircleOptions> CREATOR = new zzc();

    @SafeParcelable.Field(getter = "getFillColor", m17520id = 6)
    private int fillColor;

    @SafeParcelable.Field(getter = "getStrokeColor", m17520id = 5)
    private int strokeColor;

    @SafeParcelable.Field(getter = "getCenter", m17520id = 2)
    private LatLng zzcp;

    @SafeParcelable.Field(getter = "getRadius", m17520id = 3)
    private double zzcq;

    @SafeParcelable.Field(getter = "getStrokeWidth", m17520id = 4)
    private float zzcr;

    @SafeParcelable.Field(getter = "getZIndex", m17520id = 7)
    private float zzcs;

    @SafeParcelable.Field(getter = "isVisible", m17520id = 8)
    private boolean zzct;

    @SafeParcelable.Field(getter = "isClickable", m17520id = 9)
    private boolean zzcu;

    @SafeParcelable.Field(getter = "getStrokePattern", m17520id = 10)
    private List<PatternItem> zzcv;

    public CircleOptions() {
        this.zzcp = null;
        this.zzcq = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
        this.zzcr = 10.0f;
        this.strokeColor = -16777216;
        this.fillColor = 0;
        this.zzcs = BitmapDescriptorFactory.HUE_RED;
        this.zzct = true;
        this.zzcu = false;
        this.zzcv = null;
    }

    public final CircleOptions center(LatLng latLng) {
        this.zzcp = latLng;
        return this;
    }

    public final CircleOptions clickable(boolean z8) {
        this.zzcu = z8;
        return this;
    }

    public final CircleOptions fillColor(int i9) {
        this.fillColor = i9;
        return this;
    }

    public final LatLng getCenter() {
        return this.zzcp;
    }

    public final int getFillColor() {
        return this.fillColor;
    }

    public final double getRadius() {
        return this.zzcq;
    }

    public final int getStrokeColor() {
        return this.strokeColor;
    }

    public final List<PatternItem> getStrokePattern() {
        return this.zzcv;
    }

    public final float getStrokeWidth() {
        return this.zzcr;
    }

    public final float getZIndex() {
        return this.zzcs;
    }

    public final boolean isClickable() {
        return this.zzcu;
    }

    public final boolean isVisible() {
        return this.zzct;
    }

    public final CircleOptions radius(double d9) {
        this.zzcq = d9;
        return this;
    }

    public final CircleOptions strokeColor(int i9) {
        this.strokeColor = i9;
        return this;
    }

    public final CircleOptions strokePattern(List<PatternItem> list) {
        this.zzcv = list;
        return this;
    }

    public final CircleOptions strokeWidth(float f9) {
        this.zzcr = f9;
        return this;
    }

    public final CircleOptions visible(boolean z8) {
        this.zzct = z8;
        return this;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i9) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, getCenter(), i9, false);
        SafeParcelWriter.writeDouble(parcel, 3, getRadius());
        SafeParcelWriter.writeFloat(parcel, 4, getStrokeWidth());
        SafeParcelWriter.writeInt(parcel, 5, getStrokeColor());
        SafeParcelWriter.writeInt(parcel, 6, getFillColor());
        SafeParcelWriter.writeFloat(parcel, 7, getZIndex());
        SafeParcelWriter.writeBoolean(parcel, 8, isVisible());
        SafeParcelWriter.writeBoolean(parcel, 9, isClickable());
        SafeParcelWriter.writeTypedList(parcel, 10, getStrokePattern(), false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    public final CircleOptions zIndex(float f9) {
        this.zzcs = f9;
        return this;
    }

    @SafeParcelable.Constructor
    public CircleOptions(@SafeParcelable.Param(m17521id = 2) LatLng latLng, @SafeParcelable.Param(m17521id = 3) double d9, @SafeParcelable.Param(m17521id = 4) float f9, @SafeParcelable.Param(m17521id = 5) int i9, @SafeParcelable.Param(m17521id = 6) int i10, @SafeParcelable.Param(m17521id = 7) float f10, @SafeParcelable.Param(m17521id = 8) boolean z8, @SafeParcelable.Param(m17521id = 9) boolean z9, @SafeParcelable.Param(m17521id = 10) List<PatternItem> list) {
        this.zzcp = latLng;
        this.zzcq = d9;
        this.zzcr = f9;
        this.strokeColor = i9;
        this.fillColor = i10;
        this.zzcs = f10;
        this.zzct = z8;
        this.zzcu = z9;
        this.zzcv = list;
    }
}
