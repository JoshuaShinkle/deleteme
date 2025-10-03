package com.google.android.gms.maps;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.StreetViewPanoramaCamera;
import com.google.android.gms.maps.model.StreetViewSource;

@SafeParcelable.Class(creator = "StreetViewPanoramaOptionsCreator")
@SafeParcelable.Reserved({1})
/* loaded from: classes2.dex */
public final class StreetViewPanoramaOptions extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Parcelable.Creator<StreetViewPanoramaOptions> CREATOR = new zzai();

    @SafeParcelable.Field(getter = "getPanoramaId", m17520id = 3)
    private String panoId;

    @SafeParcelable.Field(getter = "getPosition", m17520id = 4)
    private LatLng position;

    @SafeParcelable.Field(getter = "getUseViewLifecycleInFragmentForParcel", m17520id = 10, type = "byte")
    private Boolean zzak;

    @SafeParcelable.Field(getter = "getZoomGesturesEnabledForParcel", m17520id = 7, type = "byte")
    private Boolean zzap;

    @SafeParcelable.Field(getter = "getStreetViewPanoramaCamera", m17520id = 2)
    private StreetViewPanoramaCamera zzbx;

    @SafeParcelable.Field(getter = "getRadius", m17520id = 5)
    private Integer zzby;

    @SafeParcelable.Field(getter = "getUserNavigationEnabledForParcel", m17520id = 6, type = "byte")
    private Boolean zzbz;

    @SafeParcelable.Field(getter = "getPanningGesturesEnabledForParcel", m17520id = 8, type = "byte")
    private Boolean zzca;

    @SafeParcelable.Field(getter = "getStreetNamesEnabledForParcel", m17520id = 9, type = "byte")
    private Boolean zzcb;

    @SafeParcelable.Field(getter = "getSource", m17520id = 11)
    private StreetViewSource zzcc;

    @SafeParcelable.Constructor
    public StreetViewPanoramaOptions(@SafeParcelable.Param(m17521id = 2) StreetViewPanoramaCamera streetViewPanoramaCamera, @SafeParcelable.Param(m17521id = 3) String str, @SafeParcelable.Param(m17521id = 4) LatLng latLng, @SafeParcelable.Param(m17521id = 5) Integer num, @SafeParcelable.Param(m17521id = 6) byte b9, @SafeParcelable.Param(m17521id = 7) byte b10, @SafeParcelable.Param(m17521id = 8) byte b11, @SafeParcelable.Param(m17521id = 9) byte b12, @SafeParcelable.Param(m17521id = 10) byte b13, @SafeParcelable.Param(m17521id = 11) StreetViewSource streetViewSource) {
        Boolean bool = Boolean.TRUE;
        this.zzbz = bool;
        this.zzap = bool;
        this.zzca = bool;
        this.zzcb = bool;
        this.zzcc = StreetViewSource.DEFAULT;
        this.zzbx = streetViewPanoramaCamera;
        this.position = latLng;
        this.zzby = num;
        this.panoId = str;
        this.zzbz = com.google.android.gms.maps.internal.zza.zza(b9);
        this.zzap = com.google.android.gms.maps.internal.zza.zza(b10);
        this.zzca = com.google.android.gms.maps.internal.zza.zza(b11);
        this.zzcb = com.google.android.gms.maps.internal.zza.zza(b12);
        this.zzak = com.google.android.gms.maps.internal.zza.zza(b13);
        this.zzcc = streetViewSource;
    }

    public final Boolean getPanningGesturesEnabled() {
        return this.zzca;
    }

    public final String getPanoramaId() {
        return this.panoId;
    }

    public final LatLng getPosition() {
        return this.position;
    }

    public final Integer getRadius() {
        return this.zzby;
    }

    public final StreetViewSource getSource() {
        return this.zzcc;
    }

    public final Boolean getStreetNamesEnabled() {
        return this.zzcb;
    }

    public final StreetViewPanoramaCamera getStreetViewPanoramaCamera() {
        return this.zzbx;
    }

    public final Boolean getUseViewLifecycleInFragment() {
        return this.zzak;
    }

    public final Boolean getUserNavigationEnabled() {
        return this.zzbz;
    }

    public final Boolean getZoomGesturesEnabled() {
        return this.zzap;
    }

    public final StreetViewPanoramaOptions panningGesturesEnabled(boolean z8) {
        this.zzca = Boolean.valueOf(z8);
        return this;
    }

    public final StreetViewPanoramaOptions panoramaCamera(StreetViewPanoramaCamera streetViewPanoramaCamera) {
        this.zzbx = streetViewPanoramaCamera;
        return this;
    }

    public final StreetViewPanoramaOptions panoramaId(String str) {
        this.panoId = str;
        return this;
    }

    public final StreetViewPanoramaOptions position(LatLng latLng) {
        this.position = latLng;
        return this;
    }

    public final StreetViewPanoramaOptions streetNamesEnabled(boolean z8) {
        this.zzcb = Boolean.valueOf(z8);
        return this;
    }

    public final String toString() {
        return Objects.toStringHelper(this).add("PanoramaId", this.panoId).add("Position", this.position).add("Radius", this.zzby).add("Source", this.zzcc).add("StreetViewPanoramaCamera", this.zzbx).add("UserNavigationEnabled", this.zzbz).add("ZoomGesturesEnabled", this.zzap).add("PanningGesturesEnabled", this.zzca).add("StreetNamesEnabled", this.zzcb).add("UseViewLifecycleInFragment", this.zzak).toString();
    }

    public final StreetViewPanoramaOptions useViewLifecycleInFragment(boolean z8) {
        this.zzak = Boolean.valueOf(z8);
        return this;
    }

    public final StreetViewPanoramaOptions userNavigationEnabled(boolean z8) {
        this.zzbz = Boolean.valueOf(z8);
        return this;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i9) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, getStreetViewPanoramaCamera(), i9, false);
        SafeParcelWriter.writeString(parcel, 3, getPanoramaId(), false);
        SafeParcelWriter.writeParcelable(parcel, 4, getPosition(), i9, false);
        SafeParcelWriter.writeIntegerObject(parcel, 5, getRadius(), false);
        SafeParcelWriter.writeByte(parcel, 6, com.google.android.gms.maps.internal.zza.zza(this.zzbz));
        SafeParcelWriter.writeByte(parcel, 7, com.google.android.gms.maps.internal.zza.zza(this.zzap));
        SafeParcelWriter.writeByte(parcel, 8, com.google.android.gms.maps.internal.zza.zza(this.zzca));
        SafeParcelWriter.writeByte(parcel, 9, com.google.android.gms.maps.internal.zza.zza(this.zzcb));
        SafeParcelWriter.writeByte(parcel, 10, com.google.android.gms.maps.internal.zza.zza(this.zzak));
        SafeParcelWriter.writeParcelable(parcel, 11, getSource(), i9, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    public final StreetViewPanoramaOptions zoomGesturesEnabled(boolean z8) {
        this.zzap = Boolean.valueOf(z8);
        return this;
    }

    public final StreetViewPanoramaOptions position(LatLng latLng, Integer num) {
        this.position = latLng;
        this.zzby = num;
        return this;
    }

    public final StreetViewPanoramaOptions position(LatLng latLng, Integer num, StreetViewSource streetViewSource) {
        this.position = latLng;
        this.zzby = num;
        this.zzcc = streetViewSource;
        return this;
    }

    public final StreetViewPanoramaOptions position(LatLng latLng, StreetViewSource streetViewSource) {
        this.position = latLng;
        this.zzcc = streetViewSource;
        return this;
    }

    public StreetViewPanoramaOptions() {
        Boolean bool = Boolean.TRUE;
        this.zzbz = bool;
        this.zzap = bool;
        this.zzca = bool;
        this.zzcb = bool;
        this.zzcc = StreetViewSource.DEFAULT;
    }
}
