package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

@SafeParcelable.Class(creator = "PolylineOptionsCreator")
@SafeParcelable.Reserved({1})
/* loaded from: classes2.dex */
public final class PolylineOptions extends AbstractSafeParcelable {
    public static final Parcelable.Creator<PolylineOptions> CREATOR = new zzl();

    @SafeParcelable.Field(getter = "getColor", m17520id = 4)
    private int color;

    @SafeParcelable.Field(getter = "getWidth", m17520id = 3)
    private float width;

    @SafeParcelable.Field(getter = "getZIndex", m17520id = 5)
    private float zzcs;

    @SafeParcelable.Field(getter = "isVisible", m17520id = 6)
    private boolean zzct;

    @SafeParcelable.Field(getter = "isClickable", m17520id = 8)
    private boolean zzcu;

    @SafeParcelable.Field(getter = "getPoints", m17520id = 2)
    private final List<LatLng> zzdx;

    @SafeParcelable.Field(getter = "isGeodesic", m17520id = 7)
    private boolean zzdz;

    @SafeParcelable.Field(getter = "getStartCap", m17520id = 9)
    private Cap zzec;

    @SafeParcelable.Field(getter = "getEndCap", m17520id = 10)
    private Cap zzed;

    @SafeParcelable.Field(getter = "getJointType", m17520id = 11)
    private int zzee;

    @SafeParcelable.Field(getter = "getPattern", m17520id = 12)
    private List<PatternItem> zzef;

    public PolylineOptions() {
        this.width = 10.0f;
        this.color = -16777216;
        this.zzcs = BitmapDescriptorFactory.HUE_RED;
        this.zzct = true;
        this.zzdz = false;
        this.zzcu = false;
        this.zzec = new ButtCap();
        this.zzed = new ButtCap();
        this.zzee = 0;
        this.zzef = null;
        this.zzdx = new ArrayList();
    }

    public final PolylineOptions add(LatLng latLng) {
        this.zzdx.add(latLng);
        return this;
    }

    public final PolylineOptions addAll(Iterable<LatLng> iterable) {
        Iterator<LatLng> it = iterable.iterator();
        while (it.hasNext()) {
            this.zzdx.add(it.next());
        }
        return this;
    }

    public final PolylineOptions clickable(boolean z8) {
        this.zzcu = z8;
        return this;
    }

    public final PolylineOptions color(int i9) {
        this.color = i9;
        return this;
    }

    public final PolylineOptions endCap(Cap cap) {
        this.zzed = (Cap) Preconditions.checkNotNull(cap, "endCap must not be null");
        return this;
    }

    public final PolylineOptions geodesic(boolean z8) {
        this.zzdz = z8;
        return this;
    }

    public final int getColor() {
        return this.color;
    }

    public final Cap getEndCap() {
        return this.zzed;
    }

    public final int getJointType() {
        return this.zzee;
    }

    public final List<PatternItem> getPattern() {
        return this.zzef;
    }

    public final List<LatLng> getPoints() {
        return this.zzdx;
    }

    public final Cap getStartCap() {
        return this.zzec;
    }

    public final float getWidth() {
        return this.width;
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

    public final PolylineOptions jointType(int i9) {
        this.zzee = i9;
        return this;
    }

    public final PolylineOptions pattern(List<PatternItem> list) {
        this.zzef = list;
        return this;
    }

    public final PolylineOptions startCap(Cap cap) {
        this.zzec = (Cap) Preconditions.checkNotNull(cap, "startCap must not be null");
        return this;
    }

    public final PolylineOptions visible(boolean z8) {
        this.zzct = z8;
        return this;
    }

    public final PolylineOptions width(float f9) {
        this.width = f9;
        return this;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i9) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeTypedList(parcel, 2, getPoints(), false);
        SafeParcelWriter.writeFloat(parcel, 3, getWidth());
        SafeParcelWriter.writeInt(parcel, 4, getColor());
        SafeParcelWriter.writeFloat(parcel, 5, getZIndex());
        SafeParcelWriter.writeBoolean(parcel, 6, isVisible());
        SafeParcelWriter.writeBoolean(parcel, 7, isGeodesic());
        SafeParcelWriter.writeBoolean(parcel, 8, isClickable());
        SafeParcelWriter.writeParcelable(parcel, 9, getStartCap(), i9, false);
        SafeParcelWriter.writeParcelable(parcel, 10, getEndCap(), i9, false);
        SafeParcelWriter.writeInt(parcel, 11, getJointType());
        SafeParcelWriter.writeTypedList(parcel, 12, getPattern(), false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    public final PolylineOptions zIndex(float f9) {
        this.zzcs = f9;
        return this;
    }

    public final PolylineOptions add(LatLng... latLngArr) {
        this.zzdx.addAll(Arrays.asList(latLngArr));
        return this;
    }

    @SafeParcelable.Constructor
    public PolylineOptions(@SafeParcelable.Param(m17521id = 2) List list, @SafeParcelable.Param(m17521id = 3) float f9, @SafeParcelable.Param(m17521id = 4) int i9, @SafeParcelable.Param(m17521id = 5) float f10, @SafeParcelable.Param(m17521id = 6) boolean z8, @SafeParcelable.Param(m17521id = 7) boolean z9, @SafeParcelable.Param(m17521id = 8) boolean z10, @SafeParcelable.Param(m17521id = 9) Cap cap, @SafeParcelable.Param(m17521id = 10) Cap cap2, @SafeParcelable.Param(m17521id = 11) int i10, @SafeParcelable.Param(m17521id = 12) List<PatternItem> list2) {
        this.width = 10.0f;
        this.color = -16777216;
        this.zzcs = BitmapDescriptorFactory.HUE_RED;
        this.zzct = true;
        this.zzdz = false;
        this.zzcu = false;
        this.zzec = new ButtCap();
        this.zzed = new ButtCap();
        this.zzee = 0;
        this.zzef = null;
        this.zzdx = list;
        this.width = f9;
        this.color = i9;
        this.zzcs = f10;
        this.zzct = z8;
        this.zzdz = z9;
        this.zzcu = z10;
        if (cap != null) {
            this.zzec = cap;
        }
        if (cap2 != null) {
            this.zzed = cap2;
        }
        this.zzee = i10;
        this.zzef = list2;
    }
}
