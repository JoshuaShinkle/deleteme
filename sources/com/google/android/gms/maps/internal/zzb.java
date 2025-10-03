package com.google.android.gms.maps.internal;

import android.os.IBinder;
import android.os.Parcel;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

/* loaded from: classes2.dex */
public final class zzb extends com.google.android.gms.internal.maps.zza implements ICameraUpdateFactoryDelegate {
    public zzb(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate");
    }

    @Override // com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate
    public final IObjectWrapper newCameraPosition(CameraPosition cameraPosition) {
        Parcel parcelZza = zza();
        com.google.android.gms.internal.maps.zzc.zza(parcelZza, cameraPosition);
        Parcel parcelZza2 = zza(7, parcelZza);
        IObjectWrapper iObjectWrapperAsInterface = IObjectWrapper.Stub.asInterface(parcelZza2.readStrongBinder());
        parcelZza2.recycle();
        return iObjectWrapperAsInterface;
    }

    @Override // com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate
    public final IObjectWrapper newLatLng(LatLng latLng) {
        Parcel parcelZza = zza();
        com.google.android.gms.internal.maps.zzc.zza(parcelZza, latLng);
        Parcel parcelZza2 = zza(8, parcelZza);
        IObjectWrapper iObjectWrapperAsInterface = IObjectWrapper.Stub.asInterface(parcelZza2.readStrongBinder());
        parcelZza2.recycle();
        return iObjectWrapperAsInterface;
    }

    @Override // com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate
    public final IObjectWrapper newLatLngBounds(LatLngBounds latLngBounds, int i9) {
        Parcel parcelZza = zza();
        com.google.android.gms.internal.maps.zzc.zza(parcelZza, latLngBounds);
        parcelZza.writeInt(i9);
        Parcel parcelZza2 = zza(10, parcelZza);
        IObjectWrapper iObjectWrapperAsInterface = IObjectWrapper.Stub.asInterface(parcelZza2.readStrongBinder());
        parcelZza2.recycle();
        return iObjectWrapperAsInterface;
    }

    @Override // com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate
    public final IObjectWrapper newLatLngBoundsWithSize(LatLngBounds latLngBounds, int i9, int i10, int i11) {
        Parcel parcelZza = zza();
        com.google.android.gms.internal.maps.zzc.zza(parcelZza, latLngBounds);
        parcelZza.writeInt(i9);
        parcelZza.writeInt(i10);
        parcelZza.writeInt(i11);
        Parcel parcelZza2 = zza(11, parcelZza);
        IObjectWrapper iObjectWrapperAsInterface = IObjectWrapper.Stub.asInterface(parcelZza2.readStrongBinder());
        parcelZza2.recycle();
        return iObjectWrapperAsInterface;
    }

    @Override // com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate
    public final IObjectWrapper newLatLngZoom(LatLng latLng, float f9) {
        Parcel parcelZza = zza();
        com.google.android.gms.internal.maps.zzc.zza(parcelZza, latLng);
        parcelZza.writeFloat(f9);
        Parcel parcelZza2 = zza(9, parcelZza);
        IObjectWrapper iObjectWrapperAsInterface = IObjectWrapper.Stub.asInterface(parcelZza2.readStrongBinder());
        parcelZza2.recycle();
        return iObjectWrapperAsInterface;
    }

    @Override // com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate
    public final IObjectWrapper scrollBy(float f9, float f10) {
        Parcel parcelZza = zza();
        parcelZza.writeFloat(f9);
        parcelZza.writeFloat(f10);
        Parcel parcelZza2 = zza(3, parcelZza);
        IObjectWrapper iObjectWrapperAsInterface = IObjectWrapper.Stub.asInterface(parcelZza2.readStrongBinder());
        parcelZza2.recycle();
        return iObjectWrapperAsInterface;
    }

    @Override // com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate
    public final IObjectWrapper zoomBy(float f9) {
        Parcel parcelZza = zza();
        parcelZza.writeFloat(f9);
        Parcel parcelZza2 = zza(5, parcelZza);
        IObjectWrapper iObjectWrapperAsInterface = IObjectWrapper.Stub.asInterface(parcelZza2.readStrongBinder());
        parcelZza2.recycle();
        return iObjectWrapperAsInterface;
    }

    @Override // com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate
    public final IObjectWrapper zoomByWithFocus(float f9, int i9, int i10) {
        Parcel parcelZza = zza();
        parcelZza.writeFloat(f9);
        parcelZza.writeInt(i9);
        parcelZza.writeInt(i10);
        Parcel parcelZza2 = zza(6, parcelZza);
        IObjectWrapper iObjectWrapperAsInterface = IObjectWrapper.Stub.asInterface(parcelZza2.readStrongBinder());
        parcelZza2.recycle();
        return iObjectWrapperAsInterface;
    }

    @Override // com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate
    public final IObjectWrapper zoomIn() {
        Parcel parcelZza = zza(1, zza());
        IObjectWrapper iObjectWrapperAsInterface = IObjectWrapper.Stub.asInterface(parcelZza.readStrongBinder());
        parcelZza.recycle();
        return iObjectWrapperAsInterface;
    }

    @Override // com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate
    public final IObjectWrapper zoomOut() {
        Parcel parcelZza = zza(2, zza());
        IObjectWrapper iObjectWrapperAsInterface = IObjectWrapper.Stub.asInterface(parcelZza.readStrongBinder());
        parcelZza.recycle();
        return iObjectWrapperAsInterface;
    }

    @Override // com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate
    public final IObjectWrapper zoomTo(float f9) {
        Parcel parcelZza = zza();
        parcelZza.writeFloat(f9);
        Parcel parcelZza2 = zza(4, parcelZza);
        IObjectWrapper iObjectWrapperAsInterface = IObjectWrapper.Stub.asInterface(parcelZza2.readStrongBinder());
        parcelZza2.recycle();
        return iObjectWrapperAsInterface;
    }
}
