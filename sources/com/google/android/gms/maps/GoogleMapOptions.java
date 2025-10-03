package com.google.android.gms.maps;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

@SafeParcelable.Class(creator = "GoogleMapOptionsCreator")
@SafeParcelable.Reserved({1})
/* loaded from: classes2.dex */
public final class GoogleMapOptions extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Parcelable.Creator<GoogleMapOptions> CREATOR = new zzaa();

    @SafeParcelable.Field(getter = "getMapType", m17520id = 4)
    private int mapType;

    @SafeParcelable.Field(defaultValue = "-1", getter = "getZOrderOnTopForParcel", m17520id = 2, type = "byte")
    private Boolean zzaj;

    @SafeParcelable.Field(defaultValue = "-1", getter = "getUseViewLifecycleInFragmentForParcel", m17520id = 3, type = "byte")
    private Boolean zzak;

    @SafeParcelable.Field(getter = "getCamera", m17520id = 5)
    private CameraPosition zzal;

    @SafeParcelable.Field(defaultValue = "-1", getter = "getZoomControlsEnabledForParcel", m17520id = 6, type = "byte")
    private Boolean zzam;

    @SafeParcelable.Field(defaultValue = "-1", getter = "getCompassEnabledForParcel", m17520id = 7, type = "byte")
    private Boolean zzan;

    @SafeParcelable.Field(defaultValue = "-1", getter = "getScrollGesturesEnabledForParcel", m17520id = 8, type = "byte")
    private Boolean zzao;

    @SafeParcelable.Field(defaultValue = "-1", getter = "getZoomGesturesEnabledForParcel", m17520id = 9, type = "byte")
    private Boolean zzap;

    @SafeParcelable.Field(defaultValue = "-1", getter = "getTiltGesturesEnabledForParcel", m17520id = 10, type = "byte")
    private Boolean zzaq;

    @SafeParcelable.Field(defaultValue = "-1", getter = "getRotateGesturesEnabledForParcel", m17520id = 11, type = "byte")
    private Boolean zzar;

    @SafeParcelable.Field(defaultValue = "-1", getter = "getLiteModeForParcel", m17520id = 12, type = "byte")
    private Boolean zzas;

    @SafeParcelable.Field(defaultValue = "-1", getter = "getMapToolbarEnabledForParcel", m17520id = 14, type = "byte")
    private Boolean zzat;

    @SafeParcelable.Field(defaultValue = "-1", getter = "getAmbientEnabledForParcel", m17520id = 15, type = "byte")
    private Boolean zzau;

    @SafeParcelable.Field(getter = "getMinZoomPreference", m17520id = 16)
    private Float zzav;

    @SafeParcelable.Field(getter = "getMaxZoomPreference", m17520id = 17)
    private Float zzaw;

    @SafeParcelable.Field(getter = "getLatLngBoundsForCameraTarget", m17520id = 18)
    private LatLngBounds zzax;

    @SafeParcelable.Field(defaultValue = "-1", getter = "getScrollGesturesEnabledDuringRotateOrZoomForParcel", m17520id = 19, type = "byte")
    private Boolean zzay;

    @SafeParcelable.Constructor
    public GoogleMapOptions(@SafeParcelable.Param(m17521id = 2) byte b9, @SafeParcelable.Param(m17521id = 3) byte b10, @SafeParcelable.Param(m17521id = 4) int i9, @SafeParcelable.Param(m17521id = 5) CameraPosition cameraPosition, @SafeParcelable.Param(m17521id = 6) byte b11, @SafeParcelable.Param(m17521id = 7) byte b12, @SafeParcelable.Param(m17521id = 8) byte b13, @SafeParcelable.Param(m17521id = 9) byte b14, @SafeParcelable.Param(m17521id = 10) byte b15, @SafeParcelable.Param(m17521id = 11) byte b16, @SafeParcelable.Param(m17521id = 12) byte b17, @SafeParcelable.Param(m17521id = 14) byte b18, @SafeParcelable.Param(m17521id = 15) byte b19, @SafeParcelable.Param(m17521id = 16) Float f9, @SafeParcelable.Param(m17521id = 17) Float f10, @SafeParcelable.Param(m17521id = 18) LatLngBounds latLngBounds, @SafeParcelable.Param(m17521id = 19) byte b20) {
        this.mapType = -1;
        this.zzav = null;
        this.zzaw = null;
        this.zzax = null;
        this.zzaj = com.google.android.gms.maps.internal.zza.zza(b9);
        this.zzak = com.google.android.gms.maps.internal.zza.zza(b10);
        this.mapType = i9;
        this.zzal = cameraPosition;
        this.zzam = com.google.android.gms.maps.internal.zza.zza(b11);
        this.zzan = com.google.android.gms.maps.internal.zza.zza(b12);
        this.zzao = com.google.android.gms.maps.internal.zza.zza(b13);
        this.zzap = com.google.android.gms.maps.internal.zza.zza(b14);
        this.zzaq = com.google.android.gms.maps.internal.zza.zza(b15);
        this.zzar = com.google.android.gms.maps.internal.zza.zza(b16);
        this.zzas = com.google.android.gms.maps.internal.zza.zza(b17);
        this.zzat = com.google.android.gms.maps.internal.zza.zza(b18);
        this.zzau = com.google.android.gms.maps.internal.zza.zza(b19);
        this.zzav = f9;
        this.zzaw = f10;
        this.zzax = latLngBounds;
        this.zzay = com.google.android.gms.maps.internal.zza.zza(b20);
    }

    public static GoogleMapOptions createFromAttributes(Context context, AttributeSet attributeSet) {
        if (context == null || attributeSet == null) {
            return null;
        }
        TypedArray typedArrayObtainAttributes = context.getResources().obtainAttributes(attributeSet, C3467R.styleable.MapAttrs);
        GoogleMapOptions googleMapOptions = new GoogleMapOptions();
        int i9 = C3467R.styleable.MapAttrs_mapType;
        if (typedArrayObtainAttributes.hasValue(i9)) {
            googleMapOptions.mapType(typedArrayObtainAttributes.getInt(i9, -1));
        }
        int i10 = C3467R.styleable.MapAttrs_zOrderOnTop;
        if (typedArrayObtainAttributes.hasValue(i10)) {
            googleMapOptions.zOrderOnTop(typedArrayObtainAttributes.getBoolean(i10, false));
        }
        int i11 = C3467R.styleable.MapAttrs_useViewLifecycle;
        if (typedArrayObtainAttributes.hasValue(i11)) {
            googleMapOptions.useViewLifecycleInFragment(typedArrayObtainAttributes.getBoolean(i11, false));
        }
        int i12 = C3467R.styleable.MapAttrs_uiCompass;
        if (typedArrayObtainAttributes.hasValue(i12)) {
            googleMapOptions.compassEnabled(typedArrayObtainAttributes.getBoolean(i12, true));
        }
        int i13 = C3467R.styleable.MapAttrs_uiRotateGestures;
        if (typedArrayObtainAttributes.hasValue(i13)) {
            googleMapOptions.rotateGesturesEnabled(typedArrayObtainAttributes.getBoolean(i13, true));
        }
        int i14 = C3467R.styleable.MapAttrs_uiScrollGesturesDuringRotateOrZoom;
        if (typedArrayObtainAttributes.hasValue(i14)) {
            googleMapOptions.scrollGesturesEnabledDuringRotateOrZoom(typedArrayObtainAttributes.getBoolean(i14, true));
        }
        int i15 = C3467R.styleable.MapAttrs_uiScrollGestures;
        if (typedArrayObtainAttributes.hasValue(i15)) {
            googleMapOptions.scrollGesturesEnabled(typedArrayObtainAttributes.getBoolean(i15, true));
        }
        int i16 = C3467R.styleable.MapAttrs_uiTiltGestures;
        if (typedArrayObtainAttributes.hasValue(i16)) {
            googleMapOptions.tiltGesturesEnabled(typedArrayObtainAttributes.getBoolean(i16, true));
        }
        int i17 = C3467R.styleable.MapAttrs_uiZoomGestures;
        if (typedArrayObtainAttributes.hasValue(i17)) {
            googleMapOptions.zoomGesturesEnabled(typedArrayObtainAttributes.getBoolean(i17, true));
        }
        int i18 = C3467R.styleable.MapAttrs_uiZoomControls;
        if (typedArrayObtainAttributes.hasValue(i18)) {
            googleMapOptions.zoomControlsEnabled(typedArrayObtainAttributes.getBoolean(i18, true));
        }
        int i19 = C3467R.styleable.MapAttrs_liteMode;
        if (typedArrayObtainAttributes.hasValue(i19)) {
            googleMapOptions.liteMode(typedArrayObtainAttributes.getBoolean(i19, false));
        }
        int i20 = C3467R.styleable.MapAttrs_uiMapToolbar;
        if (typedArrayObtainAttributes.hasValue(i20)) {
            googleMapOptions.mapToolbarEnabled(typedArrayObtainAttributes.getBoolean(i20, true));
        }
        int i21 = C3467R.styleable.MapAttrs_ambientEnabled;
        if (typedArrayObtainAttributes.hasValue(i21)) {
            googleMapOptions.ambientEnabled(typedArrayObtainAttributes.getBoolean(i21, false));
        }
        int i22 = C3467R.styleable.MapAttrs_cameraMinZoomPreference;
        if (typedArrayObtainAttributes.hasValue(i22)) {
            googleMapOptions.minZoomPreference(typedArrayObtainAttributes.getFloat(i22, Float.NEGATIVE_INFINITY));
        }
        if (typedArrayObtainAttributes.hasValue(i22)) {
            googleMapOptions.maxZoomPreference(typedArrayObtainAttributes.getFloat(C3467R.styleable.MapAttrs_cameraMaxZoomPreference, Float.POSITIVE_INFINITY));
        }
        googleMapOptions.latLngBoundsForCameraTarget(zza(context, attributeSet));
        googleMapOptions.camera(zzb(context, attributeSet));
        typedArrayObtainAttributes.recycle();
        return googleMapOptions;
    }

    public static LatLngBounds zza(Context context, AttributeSet attributeSet) {
        if (context == null || attributeSet == null) {
            return null;
        }
        TypedArray typedArrayObtainAttributes = context.getResources().obtainAttributes(attributeSet, C3467R.styleable.MapAttrs);
        int i9 = C3467R.styleable.MapAttrs_latLngBoundsSouthWestLatitude;
        Float fValueOf = typedArrayObtainAttributes.hasValue(i9) ? Float.valueOf(typedArrayObtainAttributes.getFloat(i9, BitmapDescriptorFactory.HUE_RED)) : null;
        int i10 = C3467R.styleable.MapAttrs_latLngBoundsSouthWestLongitude;
        Float fValueOf2 = typedArrayObtainAttributes.hasValue(i10) ? Float.valueOf(typedArrayObtainAttributes.getFloat(i10, BitmapDescriptorFactory.HUE_RED)) : null;
        int i11 = C3467R.styleable.MapAttrs_latLngBoundsNorthEastLatitude;
        Float fValueOf3 = typedArrayObtainAttributes.hasValue(i11) ? Float.valueOf(typedArrayObtainAttributes.getFloat(i11, BitmapDescriptorFactory.HUE_RED)) : null;
        int i12 = C3467R.styleable.MapAttrs_latLngBoundsNorthEastLongitude;
        Float fValueOf4 = typedArrayObtainAttributes.hasValue(i12) ? Float.valueOf(typedArrayObtainAttributes.getFloat(i12, BitmapDescriptorFactory.HUE_RED)) : null;
        typedArrayObtainAttributes.recycle();
        if (fValueOf == null || fValueOf2 == null || fValueOf3 == null || fValueOf4 == null) {
            return null;
        }
        return new LatLngBounds(new LatLng(fValueOf.floatValue(), fValueOf2.floatValue()), new LatLng(fValueOf3.floatValue(), fValueOf4.floatValue()));
    }

    public static CameraPosition zzb(Context context, AttributeSet attributeSet) {
        if (context == null || attributeSet == null) {
            return null;
        }
        TypedArray typedArrayObtainAttributes = context.getResources().obtainAttributes(attributeSet, C3467R.styleable.MapAttrs);
        int i9 = C3467R.styleable.MapAttrs_cameraTargetLat;
        LatLng latLng = new LatLng(typedArrayObtainAttributes.hasValue(i9) ? typedArrayObtainAttributes.getFloat(i9, BitmapDescriptorFactory.HUE_RED) : 0.0f, typedArrayObtainAttributes.hasValue(C3467R.styleable.MapAttrs_cameraTargetLng) ? typedArrayObtainAttributes.getFloat(r0, BitmapDescriptorFactory.HUE_RED) : 0.0f);
        CameraPosition.Builder builder = CameraPosition.builder();
        builder.target(latLng);
        int i10 = C3467R.styleable.MapAttrs_cameraZoom;
        if (typedArrayObtainAttributes.hasValue(i10)) {
            builder.zoom(typedArrayObtainAttributes.getFloat(i10, BitmapDescriptorFactory.HUE_RED));
        }
        int i11 = C3467R.styleable.MapAttrs_cameraBearing;
        if (typedArrayObtainAttributes.hasValue(i11)) {
            builder.bearing(typedArrayObtainAttributes.getFloat(i11, BitmapDescriptorFactory.HUE_RED));
        }
        int i12 = C3467R.styleable.MapAttrs_cameraTilt;
        if (typedArrayObtainAttributes.hasValue(i12)) {
            builder.tilt(typedArrayObtainAttributes.getFloat(i12, BitmapDescriptorFactory.HUE_RED));
        }
        typedArrayObtainAttributes.recycle();
        return builder.build();
    }

    public final GoogleMapOptions ambientEnabled(boolean z8) {
        this.zzau = Boolean.valueOf(z8);
        return this;
    }

    public final GoogleMapOptions camera(CameraPosition cameraPosition) {
        this.zzal = cameraPosition;
        return this;
    }

    public final GoogleMapOptions compassEnabled(boolean z8) {
        this.zzan = Boolean.valueOf(z8);
        return this;
    }

    public final Boolean getAmbientEnabled() {
        return this.zzau;
    }

    public final CameraPosition getCamera() {
        return this.zzal;
    }

    public final Boolean getCompassEnabled() {
        return this.zzan;
    }

    public final LatLngBounds getLatLngBoundsForCameraTarget() {
        return this.zzax;
    }

    public final Boolean getLiteMode() {
        return this.zzas;
    }

    public final Boolean getMapToolbarEnabled() {
        return this.zzat;
    }

    public final int getMapType() {
        return this.mapType;
    }

    public final Float getMaxZoomPreference() {
        return this.zzaw;
    }

    public final Float getMinZoomPreference() {
        return this.zzav;
    }

    public final Boolean getRotateGesturesEnabled() {
        return this.zzar;
    }

    public final Boolean getScrollGesturesEnabled() {
        return this.zzao;
    }

    public final Boolean getScrollGesturesEnabledDuringRotateOrZoom() {
        return this.zzay;
    }

    public final Boolean getTiltGesturesEnabled() {
        return this.zzaq;
    }

    public final Boolean getUseViewLifecycleInFragment() {
        return this.zzak;
    }

    public final Boolean getZOrderOnTop() {
        return this.zzaj;
    }

    public final Boolean getZoomControlsEnabled() {
        return this.zzam;
    }

    public final Boolean getZoomGesturesEnabled() {
        return this.zzap;
    }

    public final GoogleMapOptions latLngBoundsForCameraTarget(LatLngBounds latLngBounds) {
        this.zzax = latLngBounds;
        return this;
    }

    public final GoogleMapOptions liteMode(boolean z8) {
        this.zzas = Boolean.valueOf(z8);
        return this;
    }

    public final GoogleMapOptions mapToolbarEnabled(boolean z8) {
        this.zzat = Boolean.valueOf(z8);
        return this;
    }

    public final GoogleMapOptions mapType(int i9) {
        this.mapType = i9;
        return this;
    }

    public final GoogleMapOptions maxZoomPreference(float f9) {
        this.zzaw = Float.valueOf(f9);
        return this;
    }

    public final GoogleMapOptions minZoomPreference(float f9) {
        this.zzav = Float.valueOf(f9);
        return this;
    }

    public final GoogleMapOptions rotateGesturesEnabled(boolean z8) {
        this.zzar = Boolean.valueOf(z8);
        return this;
    }

    public final GoogleMapOptions scrollGesturesEnabled(boolean z8) {
        this.zzao = Boolean.valueOf(z8);
        return this;
    }

    public final GoogleMapOptions scrollGesturesEnabledDuringRotateOrZoom(boolean z8) {
        this.zzay = Boolean.valueOf(z8);
        return this;
    }

    public final GoogleMapOptions tiltGesturesEnabled(boolean z8) {
        this.zzaq = Boolean.valueOf(z8);
        return this;
    }

    public final String toString() {
        return Objects.toStringHelper(this).add("MapType", Integer.valueOf(this.mapType)).add("LiteMode", this.zzas).add("Camera", this.zzal).add("CompassEnabled", this.zzan).add("ZoomControlsEnabled", this.zzam).add("ScrollGesturesEnabled", this.zzao).add("ZoomGesturesEnabled", this.zzap).add("TiltGesturesEnabled", this.zzaq).add("RotateGesturesEnabled", this.zzar).add("ScrollGesturesEnabledDuringRotateOrZoom", this.zzay).add("MapToolbarEnabled", this.zzat).add("AmbientEnabled", this.zzau).add("MinZoomPreference", this.zzav).add("MaxZoomPreference", this.zzaw).add("LatLngBoundsForCameraTarget", this.zzax).add("ZOrderOnTop", this.zzaj).add("UseViewLifecycleInFragment", this.zzak).toString();
    }

    public final GoogleMapOptions useViewLifecycleInFragment(boolean z8) {
        this.zzak = Boolean.valueOf(z8);
        return this;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i9) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeByte(parcel, 2, com.google.android.gms.maps.internal.zza.zza(this.zzaj));
        SafeParcelWriter.writeByte(parcel, 3, com.google.android.gms.maps.internal.zza.zza(this.zzak));
        SafeParcelWriter.writeInt(parcel, 4, getMapType());
        SafeParcelWriter.writeParcelable(parcel, 5, getCamera(), i9, false);
        SafeParcelWriter.writeByte(parcel, 6, com.google.android.gms.maps.internal.zza.zza(this.zzam));
        SafeParcelWriter.writeByte(parcel, 7, com.google.android.gms.maps.internal.zza.zza(this.zzan));
        SafeParcelWriter.writeByte(parcel, 8, com.google.android.gms.maps.internal.zza.zza(this.zzao));
        SafeParcelWriter.writeByte(parcel, 9, com.google.android.gms.maps.internal.zza.zza(this.zzap));
        SafeParcelWriter.writeByte(parcel, 10, com.google.android.gms.maps.internal.zza.zza(this.zzaq));
        SafeParcelWriter.writeByte(parcel, 11, com.google.android.gms.maps.internal.zza.zza(this.zzar));
        SafeParcelWriter.writeByte(parcel, 12, com.google.android.gms.maps.internal.zza.zza(this.zzas));
        SafeParcelWriter.writeByte(parcel, 14, com.google.android.gms.maps.internal.zza.zza(this.zzat));
        SafeParcelWriter.writeByte(parcel, 15, com.google.android.gms.maps.internal.zza.zza(this.zzau));
        SafeParcelWriter.writeFloatObject(parcel, 16, getMinZoomPreference(), false);
        SafeParcelWriter.writeFloatObject(parcel, 17, getMaxZoomPreference(), false);
        SafeParcelWriter.writeParcelable(parcel, 18, getLatLngBoundsForCameraTarget(), i9, false);
        SafeParcelWriter.writeByte(parcel, 19, com.google.android.gms.maps.internal.zza.zza(this.zzay));
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    public final GoogleMapOptions zOrderOnTop(boolean z8) {
        this.zzaj = Boolean.valueOf(z8);
        return this;
    }

    public final GoogleMapOptions zoomControlsEnabled(boolean z8) {
        this.zzam = Boolean.valueOf(z8);
        return this;
    }

    public final GoogleMapOptions zoomGesturesEnabled(boolean z8) {
        this.zzap = Boolean.valueOf(z8);
        return this;
    }

    public GoogleMapOptions() {
        this.mapType = -1;
        this.zzav = null;
        this.zzaw = null;
        this.zzax = null;
    }
}
