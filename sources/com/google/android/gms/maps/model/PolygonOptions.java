package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

@SafeParcelable.Class(creator = "PolygonOptionsCreator")
@SafeParcelable.Reserved({1})
/* loaded from: classes2.dex */
public final class PolygonOptions extends AbstractSafeParcelable {
    public static final Parcelable.Creator<PolygonOptions> CREATOR = new zzk();

    @SafeParcelable.Field(getter = "getFillColor", m17520id = 6)
    private int fillColor;

    @SafeParcelable.Field(getter = "getStrokeColor", m17520id = 5)
    private int strokeColor;

    @SafeParcelable.Field(getter = "getStrokeWidth", m17520id = 4)
    private float zzcr;

    @SafeParcelable.Field(getter = "getZIndex", m17520id = 7)
    private float zzcs;

    @SafeParcelable.Field(getter = "isVisible", m17520id = 8)
    private boolean zzct;

    @SafeParcelable.Field(getter = "isClickable", m17520id = 10)
    private boolean zzcu;

    @SafeParcelable.Field(getter = "getStrokePattern", m17520id = 12)
    private List<PatternItem> zzcv;

    @SafeParcelable.Field(getter = "getPoints", m17520id = 2)
    private final List<LatLng> zzdx;

    @SafeParcelable.Field(getter = "getHolesForParcel", m17520id = 3, type = "java.util.List")
    private final List<List<LatLng>> zzdy;

    @SafeParcelable.Field(getter = "isGeodesic", m17520id = 9)
    private boolean zzdz;

    @SafeParcelable.Field(getter = "getStrokeJointType", m17520id = 11)
    private int zzea;

    public PolygonOptions() {
        this.zzcr = 10.0f;
        this.strokeColor = -16777216;
        this.fillColor = 0;
        this.zzcs = BitmapDescriptorFactory.HUE_RED;
        this.zzct = true;
        this.zzdz = false;
        this.zzcu = false;
        this.zzea = 0;
        this.zzcv = null;
        this.zzdx = new ArrayList();
        this.zzdy = new ArrayList();
    }

    public final PolygonOptions add(LatLng latLng) {
        this.zzdx.add(latLng);
        return this;
    }

    public final PolygonOptions addAll(Iterable<LatLng> iterable) {
        Iterator<LatLng> it = iterable.iterator();
        while (it.hasNext()) {
            this.zzdx.add(it.next());
        }
        return this;
    }

    public final PolygonOptions addHole(Iterable<LatLng> iterable) {
        ArrayList arrayList = new ArrayList();
        Iterator<LatLng> it = iterable.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next());
        }
        this.zzdy.add(arrayList);
        return this;
    }

    public final PolygonOptions clickable(boolean z8) {
        this.zzcu = z8;
        return this;
    }

    public final PolygonOptions fillColor(int i9) {
        this.fillColor = i9;
        return this;
    }

    public final PolygonOptions geodesic(boolean z8) {
        this.zzdz = z8;
        return this;
    }

    public final int getFillColor() {
        return this.fillColor;
    }

    public final List<List<LatLng>> getHoles() {
        return this.zzdy;
    }

    public final List<LatLng> getPoints() {
        return this.zzdx;
    }

    public final int getStrokeColor() {
        return this.strokeColor;
    }

    public final int getStrokeJointType() {
        return this.zzea;
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

    public final boolean isGeodesic() {
        return this.zzdz;
    }

    public final boolean isVisible() {
        return this.zzct;
    }

    public final PolygonOptions strokeColor(int i9) {
        this.strokeColor = i9;
        return this;
    }

    public final PolygonOptions strokeJointType(int i9) {
        this.zzea = i9;
        return this;
    }

    public final PolygonOptions strokePattern(List<PatternItem> list) {
        this.zzcv = list;
        return this;
    }

    public final PolygonOptions strokeWidth(float f9) {
        this.zzcr = f9;
        return this;
    }

    public final PolygonOptions visible(boolean z8) {
        this.zzct = z8;
        return this;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i9) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeTypedList(parcel, 2, getPoints(), false);
        SafeParcelWriter.writeList(parcel, 3, this.zzdy, false);
        SafeParcelWriter.writeFloat(parcel, 4, getStrokeWidth());
        SafeParcelWriter.writeInt(parcel, 5, getStrokeColor());
        SafeParcelWriter.writeInt(parcel, 6, getFillColor());
        SafeParcelWriter.writeFloat(parcel, 7, getZIndex());
        SafeParcelWriter.writeBoolean(parcel, 8, isVisible());
        SafeParcelWriter.writeBoolean(parcel, 9, isGeodesic());
        SafeParcelWriter.writeBoolean(parcel, 10, isClickable());
        SafeParcelWriter.writeInt(parcel, 11, getStrokeJointType());
        SafeParcelWriter.writeTypedList(parcel, 12, getStrokePattern(), false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    public final PolygonOptions zIndex(float f9) {
        this.zzcs = f9;
        return this;
    }

    public final PolygonOptions add(LatLng... latLngArr) {
        this.zzdx.addAll(Arrays.asList(latLngArr));
        return this;
    }

    @SafeParcelable.Constructor
    public PolygonOptions(@SafeParcelable.Param(m17521id = 2) List<LatLng> list, @SafeParcelable.Param(m17521id = 3) List list2, @SafeParcelable.Param(m17521id = 4) float f9, @SafeParcelable.Param(m17521id = 5) int i9, @SafeParcelable.Param(m17521id = 6) int i10, @SafeParcelable.Param(m17521id = 7) float f10, @SafeParcelable.Param(m17521id = 8) boolean z8, @SafeParcelable.Param(m17521id = 9) boolean z9, @SafeParcelable.Param(m17521id = 10) boolean z10, @SafeParcelable.Param(m17521id = 11) int i11, @SafeParcelable.Param(m17521id = 12) List<PatternItem> list3) {
        this.zzdx = list;
        this.zzdy = list2;
        this.zzcr = f9;
        this.strokeColor = i9;
        this.fillColor = i10;
        this.zzcs = f10;
        this.zzct = z8;
        this.zzdz = z9;
        this.zzcu = z10;
        this.zzea = i11;
        this.zzcv = list3;
    }
}
