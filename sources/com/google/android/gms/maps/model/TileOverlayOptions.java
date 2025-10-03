package com.google.android.gms.maps.model;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.maps.zzaf;
import com.google.android.gms.internal.maps.zzag;

@SafeParcelable.Class(creator = "TileOverlayOptionsCreator")
@SafeParcelable.Reserved({1})
/* loaded from: classes2.dex */
public final class TileOverlayOptions extends AbstractSafeParcelable {
    public static final Parcelable.Creator<TileOverlayOptions> CREATOR = new zzu();

    @SafeParcelable.Field(getter = "getZIndex", m17520id = 4)
    private float zzcs;

    @SafeParcelable.Field(getter = "isVisible", m17520id = 3)
    private boolean zzct;

    @SafeParcelable.Field(getter = "getTransparency", m17520id = 6)
    private float zzda;

    @SafeParcelable.Field(getter = "getTileProviderDelegate", m17520id = 2, type = "android.os.IBinder")
    private zzaf zzei;
    private TileProvider zzej;

    @SafeParcelable.Field(defaultValue = "true", getter = "getFadeIn", m17520id = 5)
    private boolean zzek;

    public TileOverlayOptions() {
        this.zzct = true;
        this.zzek = true;
        this.zzda = BitmapDescriptorFactory.HUE_RED;
    }

    public final TileOverlayOptions fadeIn(boolean z8) {
        this.zzek = z8;
        return this;
    }

    public final boolean getFadeIn() {
        return this.zzek;
    }

    public final TileProvider getTileProvider() {
        return this.zzej;
    }

    public final float getTransparency() {
        return this.zzda;
    }

    public final float getZIndex() {
        return this.zzcs;
    }

    public final boolean isVisible() {
        return this.zzct;
    }

    public final TileOverlayOptions tileProvider(TileProvider tileProvider) {
        this.zzej = tileProvider;
        this.zzei = tileProvider == null ? null : new zzt(this, tileProvider);
        return this;
    }

    public final TileOverlayOptions transparency(float f9) {
        Preconditions.checkArgument(f9 >= BitmapDescriptorFactory.HUE_RED && f9 <= 1.0f, "Transparency must be in the range [0..1]");
        this.zzda = f9;
        return this;
    }

    public final TileOverlayOptions visible(boolean z8) {
        this.zzct = z8;
        return this;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i9) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeIBinder(parcel, 2, this.zzei.asBinder(), false);
        SafeParcelWriter.writeBoolean(parcel, 3, isVisible());
        SafeParcelWriter.writeFloat(parcel, 4, getZIndex());
        SafeParcelWriter.writeBoolean(parcel, 5, getFadeIn());
        SafeParcelWriter.writeFloat(parcel, 6, getTransparency());
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    public final TileOverlayOptions zIndex(float f9) {
        this.zzcs = f9;
        return this;
    }

    @SafeParcelable.Constructor
    public TileOverlayOptions(@SafeParcelable.Param(m17521id = 2) IBinder iBinder, @SafeParcelable.Param(m17521id = 3) boolean z8, @SafeParcelable.Param(m17521id = 4) float f9, @SafeParcelable.Param(m17521id = 5) boolean z9, @SafeParcelable.Param(m17521id = 6) float f10) {
        this.zzct = true;
        this.zzek = true;
        this.zzda = BitmapDescriptorFactory.HUE_RED;
        zzaf zzafVarZzk = zzag.zzk(iBinder);
        this.zzei = zzafVarZzk;
        this.zzej = zzafVarZzk == null ? null : new zzs(this);
        this.zzct = z8;
        this.zzcs = f9;
        this.zzek = z9;
        this.zzda = f10;
    }
}
