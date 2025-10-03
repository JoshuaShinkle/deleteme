package com.google.android.gms.internal.maps;

import android.os.IBinder;
import android.os.Parcel;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.maps.model.LatLng;

/* loaded from: classes2.dex */
public final class zzv extends zza implements zzt {
    public zzv(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.maps.model.internal.IMarkerDelegate");
    }

    @Override // com.google.android.gms.internal.maps.zzt
    public final float getAlpha() {
        Parcel parcelZza = zza(26, zza());
        float f9 = parcelZza.readFloat();
        parcelZza.recycle();
        return f9;
    }

    @Override // com.google.android.gms.internal.maps.zzt
    public final String getId() {
        Parcel parcelZza = zza(2, zza());
        String string = parcelZza.readString();
        parcelZza.recycle();
        return string;
    }

    @Override // com.google.android.gms.internal.maps.zzt
    public final LatLng getPosition() {
        Parcel parcelZza = zza(4, zza());
        LatLng latLng = (LatLng) zzc.zza(parcelZza, LatLng.CREATOR);
        parcelZza.recycle();
        return latLng;
    }

    @Override // com.google.android.gms.internal.maps.zzt
    public final float getRotation() {
        Parcel parcelZza = zza(23, zza());
        float f9 = parcelZza.readFloat();
        parcelZza.recycle();
        return f9;
    }

    @Override // com.google.android.gms.internal.maps.zzt
    public final String getSnippet() {
        Parcel parcelZza = zza(8, zza());
        String string = parcelZza.readString();
        parcelZza.recycle();
        return string;
    }

    @Override // com.google.android.gms.internal.maps.zzt
    public final String getTitle() {
        Parcel parcelZza = zza(6, zza());
        String string = parcelZza.readString();
        parcelZza.recycle();
        return string;
    }

    @Override // com.google.android.gms.internal.maps.zzt
    public final float getZIndex() {
        Parcel parcelZza = zza(28, zza());
        float f9 = parcelZza.readFloat();
        parcelZza.recycle();
        return f9;
    }

    @Override // com.google.android.gms.internal.maps.zzt
    public final void hideInfoWindow() {
        zzb(12, zza());
    }

    @Override // com.google.android.gms.internal.maps.zzt
    public final boolean isDraggable() {
        Parcel parcelZza = zza(10, zza());
        boolean zZza = zzc.zza(parcelZza);
        parcelZza.recycle();
        return zZza;
    }

    @Override // com.google.android.gms.internal.maps.zzt
    public final boolean isFlat() {
        Parcel parcelZza = zza(21, zza());
        boolean zZza = zzc.zza(parcelZza);
        parcelZza.recycle();
        return zZza;
    }

    @Override // com.google.android.gms.internal.maps.zzt
    public final boolean isInfoWindowShown() {
        Parcel parcelZza = zza(13, zza());
        boolean zZza = zzc.zza(parcelZza);
        parcelZza.recycle();
        return zZza;
    }

    @Override // com.google.android.gms.internal.maps.zzt
    public final boolean isVisible() {
        Parcel parcelZza = zza(15, zza());
        boolean zZza = zzc.zza(parcelZza);
        parcelZza.recycle();
        return zZza;
    }

    @Override // com.google.android.gms.internal.maps.zzt
    public final void remove() {
        zzb(1, zza());
    }

    @Override // com.google.android.gms.internal.maps.zzt
    public final void setAlpha(float f9) {
        Parcel parcelZza = zza();
        parcelZza.writeFloat(f9);
        zzb(25, parcelZza);
    }

    @Override // com.google.android.gms.internal.maps.zzt
    public final void setAnchor(float f9, float f10) {
        Parcel parcelZza = zza();
        parcelZza.writeFloat(f9);
        parcelZza.writeFloat(f10);
        zzb(19, parcelZza);
    }

    @Override // com.google.android.gms.internal.maps.zzt
    public final void setDraggable(boolean z8) {
        Parcel parcelZza = zza();
        zzc.writeBoolean(parcelZza, z8);
        zzb(9, parcelZza);
    }

    @Override // com.google.android.gms.internal.maps.zzt
    public final void setFlat(boolean z8) {
        Parcel parcelZza = zza();
        zzc.writeBoolean(parcelZza, z8);
        zzb(20, parcelZza);
    }

    @Override // com.google.android.gms.internal.maps.zzt
    public final void setInfoWindowAnchor(float f9, float f10) {
        Parcel parcelZza = zza();
        parcelZza.writeFloat(f9);
        parcelZza.writeFloat(f10);
        zzb(24, parcelZza);
    }

    @Override // com.google.android.gms.internal.maps.zzt
    public final void setPosition(LatLng latLng) {
        Parcel parcelZza = zza();
        zzc.zza(parcelZza, latLng);
        zzb(3, parcelZza);
    }

    @Override // com.google.android.gms.internal.maps.zzt
    public final void setRotation(float f9) {
        Parcel parcelZza = zza();
        parcelZza.writeFloat(f9);
        zzb(22, parcelZza);
    }

    @Override // com.google.android.gms.internal.maps.zzt
    public final void setSnippet(String str) {
        Parcel parcelZza = zza();
        parcelZza.writeString(str);
        zzb(7, parcelZza);
    }

    @Override // com.google.android.gms.internal.maps.zzt
    public final void setTitle(String str) {
        Parcel parcelZza = zza();
        parcelZza.writeString(str);
        zzb(5, parcelZza);
    }

    @Override // com.google.android.gms.internal.maps.zzt
    public final void setVisible(boolean z8) {
        Parcel parcelZza = zza();
        zzc.writeBoolean(parcelZza, z8);
        zzb(14, parcelZza);
    }

    @Override // com.google.android.gms.internal.maps.zzt
    public final void setZIndex(float f9) {
        Parcel parcelZza = zza();
        parcelZza.writeFloat(f9);
        zzb(27, parcelZza);
    }

    @Override // com.google.android.gms.internal.maps.zzt
    public final void showInfoWindow() {
        zzb(11, zza());
    }

    @Override // com.google.android.gms.internal.maps.zzt
    public final void zze(IObjectWrapper iObjectWrapper) {
        Parcel parcelZza = zza();
        zzc.zza(parcelZza, iObjectWrapper);
        zzb(29, parcelZza);
    }

    @Override // com.google.android.gms.internal.maps.zzt
    public final void zzg(IObjectWrapper iObjectWrapper) {
        Parcel parcelZza = zza();
        zzc.zza(parcelZza, iObjectWrapper);
        zzb(18, parcelZza);
    }

    @Override // com.google.android.gms.internal.maps.zzt
    public final boolean zzj(zzt zztVar) {
        Parcel parcelZza = zza();
        zzc.zza(parcelZza, zztVar);
        Parcel parcelZza2 = zza(16, parcelZza);
        boolean zZza = zzc.zza(parcelZza2);
        parcelZza2.recycle();
        return zZza;
    }

    @Override // com.google.android.gms.internal.maps.zzt
    public final IObjectWrapper zzk() {
        Parcel parcelZza = zza(30, zza());
        IObjectWrapper iObjectWrapperAsInterface = IObjectWrapper.Stub.asInterface(parcelZza.readStrongBinder());
        parcelZza.recycle();
        return iObjectWrapperAsInterface;
    }

    @Override // com.google.android.gms.internal.maps.zzt
    public final int zzj() {
        Parcel parcelZza = zza(17, zza());
        int i9 = parcelZza.readInt();
        parcelZza.recycle();
        return i9;
    }
}
