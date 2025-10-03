package com.google.android.gms.maps.internal;

import android.os.IBinder;
import android.os.Parcel;

/* loaded from: classes2.dex */
public final class zzbx extends com.google.android.gms.internal.maps.zza implements IUiSettingsDelegate {
    public zzbx(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.maps.internal.IUiSettingsDelegate");
    }

    @Override // com.google.android.gms.maps.internal.IUiSettingsDelegate
    public final boolean isCompassEnabled() {
        Parcel parcelZza = zza(10, zza());
        boolean zZza = com.google.android.gms.internal.maps.zzc.zza(parcelZza);
        parcelZza.recycle();
        return zZza;
    }

    @Override // com.google.android.gms.maps.internal.IUiSettingsDelegate
    public final boolean isIndoorLevelPickerEnabled() {
        Parcel parcelZza = zza(17, zza());
        boolean zZza = com.google.android.gms.internal.maps.zzc.zza(parcelZza);
        parcelZza.recycle();
        return zZza;
    }

    @Override // com.google.android.gms.maps.internal.IUiSettingsDelegate
    public final boolean isMapToolbarEnabled() {
        Parcel parcelZza = zza(19, zza());
        boolean zZza = com.google.android.gms.internal.maps.zzc.zza(parcelZza);
        parcelZza.recycle();
        return zZza;
    }

    @Override // com.google.android.gms.maps.internal.IUiSettingsDelegate
    public final boolean isMyLocationButtonEnabled() {
        Parcel parcelZza = zza(11, zza());
        boolean zZza = com.google.android.gms.internal.maps.zzc.zza(parcelZza);
        parcelZza.recycle();
        return zZza;
    }

    @Override // com.google.android.gms.maps.internal.IUiSettingsDelegate
    public final boolean isRotateGesturesEnabled() {
        Parcel parcelZza = zza(15, zza());
        boolean zZza = com.google.android.gms.internal.maps.zzc.zza(parcelZza);
        parcelZza.recycle();
        return zZza;
    }

    @Override // com.google.android.gms.maps.internal.IUiSettingsDelegate
    public final boolean isScrollGesturesEnabled() {
        Parcel parcelZza = zza(12, zza());
        boolean zZza = com.google.android.gms.internal.maps.zzc.zza(parcelZza);
        parcelZza.recycle();
        return zZza;
    }

    @Override // com.google.android.gms.maps.internal.IUiSettingsDelegate
    public final boolean isScrollGesturesEnabledDuringRotateOrZoom() {
        Parcel parcelZza = zza(21, zza());
        boolean zZza = com.google.android.gms.internal.maps.zzc.zza(parcelZza);
        parcelZza.recycle();
        return zZza;
    }

    @Override // com.google.android.gms.maps.internal.IUiSettingsDelegate
    public final boolean isTiltGesturesEnabled() {
        Parcel parcelZza = zza(14, zza());
        boolean zZza = com.google.android.gms.internal.maps.zzc.zza(parcelZza);
        parcelZza.recycle();
        return zZza;
    }

    @Override // com.google.android.gms.maps.internal.IUiSettingsDelegate
    public final boolean isZoomControlsEnabled() {
        Parcel parcelZza = zza(9, zza());
        boolean zZza = com.google.android.gms.internal.maps.zzc.zza(parcelZza);
        parcelZza.recycle();
        return zZza;
    }

    @Override // com.google.android.gms.maps.internal.IUiSettingsDelegate
    public final boolean isZoomGesturesEnabled() {
        Parcel parcelZza = zza(13, zza());
        boolean zZza = com.google.android.gms.internal.maps.zzc.zza(parcelZza);
        parcelZza.recycle();
        return zZza;
    }

    @Override // com.google.android.gms.maps.internal.IUiSettingsDelegate
    public final void setAllGesturesEnabled(boolean z8) {
        Parcel parcelZza = zza();
        com.google.android.gms.internal.maps.zzc.writeBoolean(parcelZza, z8);
        zzb(8, parcelZza);
    }

    @Override // com.google.android.gms.maps.internal.IUiSettingsDelegate
    public final void setCompassEnabled(boolean z8) {
        Parcel parcelZza = zza();
        com.google.android.gms.internal.maps.zzc.writeBoolean(parcelZza, z8);
        zzb(2, parcelZza);
    }

    @Override // com.google.android.gms.maps.internal.IUiSettingsDelegate
    public final void setIndoorLevelPickerEnabled(boolean z8) {
        Parcel parcelZza = zza();
        com.google.android.gms.internal.maps.zzc.writeBoolean(parcelZza, z8);
        zzb(16, parcelZza);
    }

    @Override // com.google.android.gms.maps.internal.IUiSettingsDelegate
    public final void setMapToolbarEnabled(boolean z8) {
        Parcel parcelZza = zza();
        com.google.android.gms.internal.maps.zzc.writeBoolean(parcelZza, z8);
        zzb(18, parcelZza);
    }

    @Override // com.google.android.gms.maps.internal.IUiSettingsDelegate
    public final void setMyLocationButtonEnabled(boolean z8) {
        Parcel parcelZza = zza();
        com.google.android.gms.internal.maps.zzc.writeBoolean(parcelZza, z8);
        zzb(3, parcelZza);
    }

    @Override // com.google.android.gms.maps.internal.IUiSettingsDelegate
    public final void setRotateGesturesEnabled(boolean z8) {
        Parcel parcelZza = zza();
        com.google.android.gms.internal.maps.zzc.writeBoolean(parcelZza, z8);
        zzb(7, parcelZza);
    }

    @Override // com.google.android.gms.maps.internal.IUiSettingsDelegate
    public final void setScrollGesturesEnabled(boolean z8) {
        Parcel parcelZza = zza();
        com.google.android.gms.internal.maps.zzc.writeBoolean(parcelZza, z8);
        zzb(4, parcelZza);
    }

    @Override // com.google.android.gms.maps.internal.IUiSettingsDelegate
    public final void setScrollGesturesEnabledDuringRotateOrZoom(boolean z8) {
        Parcel parcelZza = zza();
        com.google.android.gms.internal.maps.zzc.writeBoolean(parcelZza, z8);
        zzb(20, parcelZza);
    }

    @Override // com.google.android.gms.maps.internal.IUiSettingsDelegate
    public final void setTiltGesturesEnabled(boolean z8) {
        Parcel parcelZza = zza();
        com.google.android.gms.internal.maps.zzc.writeBoolean(parcelZza, z8);
        zzb(6, parcelZza);
    }

    @Override // com.google.android.gms.maps.internal.IUiSettingsDelegate
    public final void setZoomControlsEnabled(boolean z8) {
        Parcel parcelZza = zza();
        com.google.android.gms.internal.maps.zzc.writeBoolean(parcelZza, z8);
        zzb(1, parcelZza);
    }

    @Override // com.google.android.gms.maps.internal.IUiSettingsDelegate
    public final void setZoomGesturesEnabled(boolean z8) {
        Parcel parcelZza = zza();
        com.google.android.gms.internal.maps.zzc.writeBoolean(parcelZza, z8);
        zzb(5, parcelZza);
    }
}
