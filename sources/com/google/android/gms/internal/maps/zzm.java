package com.google.android.gms.internal.maps;

import android.os.IBinder;
import android.os.Parcel;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

/* loaded from: classes2.dex */
public final class zzm extends zza implements zzk {
    public zzm(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.maps.model.internal.IGroundOverlayDelegate");
    }

    @Override // com.google.android.gms.internal.maps.zzk
    public final float getBearing() {
        Parcel parcelZza = zza(12, zza());
        float f9 = parcelZza.readFloat();
        parcelZza.recycle();
        return f9;
    }

    @Override // com.google.android.gms.internal.maps.zzk
    public final LatLngBounds getBounds() {
        Parcel parcelZza = zza(10, zza());
        LatLngBounds latLngBounds = (LatLngBounds) zzc.zza(parcelZza, LatLngBounds.CREATOR);
        parcelZza.recycle();
        return latLngBounds;
    }

    @Override // com.google.android.gms.internal.maps.zzk
    public final float getHeight() {
        Parcel parcelZza = zza(8, zza());
        float f9 = parcelZza.readFloat();
        parcelZza.recycle();
        return f9;
    }

    @Override // com.google.android.gms.internal.maps.zzk
    public final String getId() {
        Parcel parcelZza = zza(2, zza());
        String string = parcelZza.readString();
        parcelZza.recycle();
        return string;
    }

    @Override // com.google.android.gms.internal.maps.zzk
    public final LatLng getPosition() {
        Parcel parcelZza = zza(4, zza());
        LatLng latLng = (LatLng) zzc.zza(parcelZza, LatLng.CREATOR);
        parcelZza.recycle();
        return latLng;
    }

    @Override // com.google.android.gms.internal.maps.zzk
    public final float getTransparency() {
        Parcel parcelZza = zza(18, zza());
        float f9 = parcelZza.readFloat();
        parcelZza.recycle();
        return f9;
    }

    @Override // com.google.android.gms.internal.maps.zzk
    public final float getWidth() {
        Parcel parcelZza = zza(7, zza());
        float f9 = parcelZza.readFloat();
        parcelZza.recycle();
        return f9;
    }

    @Override // com.google.android.gms.internal.maps.zzk
    public final float getZIndex() {
        Parcel parcelZza = zza(14, zza());
        float f9 = parcelZza.readFloat();
        parcelZza.recycle();
        return f9;
    }

    @Override // com.google.android.gms.internal.maps.zzk
    public final boolean isClickable() {
        Parcel parcelZza = zza(23, zza());
        boolean zZza = zzc.zza(parcelZza);
        parcelZza.recycle();
        return zZza;
    }

    @Override // com.google.android.gms.internal.maps.zzk
    public final boolean isVisible() {
        Parcel parcelZza = zza(16, zza());
        boolean zZza = zzc.zza(parcelZza);
        parcelZza.recycle();
        return zZza;
    }

    @Override // com.google.android.gms.internal.maps.zzk
    public final void remove() {
        zzb(1, zza());
    }

    @Override // com.google.android.gms.internal.maps.zzk
    public final void setBearing(float f9) {
        Parcel parcelZza = zza();
        parcelZza.writeFloat(f9);
        zzb(11, parcelZza);
    }

    @Override // com.google.android.gms.internal.maps.zzk
    public final void setClickable(boolean z8) {
        Parcel parcelZza = zza();
        zzc.writeBoolean(parcelZza, z8);
        zzb(22, parcelZza);
    }

    @Override // com.google.android.gms.internal.maps.zzk
    public final void setDimensions(float f9) {
        Parcel parcelZza = zza();
        parcelZza.writeFloat(f9);
        zzb(5, parcelZza);
    }

    @Override // com.google.android.gms.internal.maps.zzk
    public final void setPosition(LatLng latLng) {
        Parcel parcelZza = zza();
        zzc.zza(parcelZza, latLng);
        zzb(3, parcelZza);
    }

    @Override // com.google.android.gms.internal.maps.zzk
    public final void setPositionFromBounds(LatLngBounds latLngBounds) {
        Parcel parcelZza = zza();
        zzc.zza(parcelZza, latLngBounds);
        zzb(9, parcelZza);
    }

    @Override // com.google.android.gms.internal.maps.zzk
    public final void setTransparency(float f9) {
        Parcel parcelZza = zza();
        parcelZza.writeFloat(f9);
        zzb(17, parcelZza);
    }

    @Override // com.google.android.gms.internal.maps.zzk
    public final void setVisible(boolean z8) {
        Parcel parcelZza = zza();
        zzc.writeBoolean(parcelZza, z8);
        zzb(15, parcelZza);
    }

    @Override // com.google.android.gms.internal.maps.zzk
    public final void setZIndex(float f9) {
        Parcel parcelZza = zza();
        parcelZza.writeFloat(f9);
        zzb(13, parcelZza);
    }

    @Override // com.google.android.gms.internal.maps.zzk
    public final void zza(float f9, float f10) {
        Parcel parcelZza = zza();
        parcelZza.writeFloat(f9);
        parcelZza.writeFloat(f10);
        zzb(6, parcelZza);
    }

    @Override // com.google.android.gms.internal.maps.zzk
    public final boolean zzb(zzk zzkVar) {
        Parcel parcelZza = zza();
        zzc.zza(parcelZza, zzkVar);
        Parcel parcelZza2 = zza(19, parcelZza);
        boolean zZza = zzc.zza(parcelZza2);
        parcelZza2.recycle();
        return zZza;
    }

    @Override // com.google.android.gms.internal.maps.zzk
    public final void zze(IObjectWrapper iObjectWrapper) {
        Parcel parcelZza = zza();
        zzc.zza(parcelZza, iObjectWrapper);
        zzb(24, parcelZza);
    }

    @Override // com.google.android.gms.internal.maps.zzk
    public final void zzf(IObjectWrapper iObjectWrapper) {
        Parcel parcelZza = zza();
        zzc.zza(parcelZza, iObjectWrapper);
        zzb(21, parcelZza);
    }

    @Override // com.google.android.gms.internal.maps.zzk
    public final int zzj() {
        Parcel parcelZza = zza(20, zza());
        int i9 = parcelZza.readInt();
        parcelZza.recycle();
        return i9;
    }

    @Override // com.google.android.gms.internal.maps.zzk
    public final IObjectWrapper zzk() {
        Parcel parcelZza = zza(25, zza());
        IObjectWrapper iObjectWrapperAsInterface = IObjectWrapper.Stub.asInterface(parcelZza.readStrongBinder());
        parcelZza.recycle();
        return iObjectWrapperAsInterface;
    }
}
