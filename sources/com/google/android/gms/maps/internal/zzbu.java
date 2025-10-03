package com.google.android.gms.maps.internal;

import android.os.IBinder;
import android.os.Parcel;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.StreetViewPanoramaCamera;
import com.google.android.gms.maps.model.StreetViewPanoramaLocation;
import com.google.android.gms.maps.model.StreetViewPanoramaOrientation;
import com.google.android.gms.maps.model.StreetViewSource;

/* loaded from: classes2.dex */
public final class zzbu extends com.google.android.gms.internal.maps.zza implements IStreetViewPanoramaDelegate {
    public zzbu(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate");
    }

    @Override // com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate
    public final void animateTo(StreetViewPanoramaCamera streetViewPanoramaCamera, long j9) {
        Parcel parcelZza = zza();
        com.google.android.gms.internal.maps.zzc.zza(parcelZza, streetViewPanoramaCamera);
        parcelZza.writeLong(j9);
        zzb(9, parcelZza);
    }

    @Override // com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate
    public final void enablePanning(boolean z8) {
        Parcel parcelZza = zza();
        com.google.android.gms.internal.maps.zzc.writeBoolean(parcelZza, z8);
        zzb(2, parcelZza);
    }

    @Override // com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate
    public final void enableStreetNames(boolean z8) {
        Parcel parcelZza = zza();
        com.google.android.gms.internal.maps.zzc.writeBoolean(parcelZza, z8);
        zzb(4, parcelZza);
    }

    @Override // com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate
    public final void enableUserNavigation(boolean z8) {
        Parcel parcelZza = zza();
        com.google.android.gms.internal.maps.zzc.writeBoolean(parcelZza, z8);
        zzb(3, parcelZza);
    }

    @Override // com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate
    public final void enableZoom(boolean z8) {
        Parcel parcelZza = zza();
        com.google.android.gms.internal.maps.zzc.writeBoolean(parcelZza, z8);
        zzb(1, parcelZza);
    }

    @Override // com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate
    public final StreetViewPanoramaCamera getPanoramaCamera() {
        Parcel parcelZza = zza(10, zza());
        StreetViewPanoramaCamera streetViewPanoramaCamera = (StreetViewPanoramaCamera) com.google.android.gms.internal.maps.zzc.zza(parcelZza, StreetViewPanoramaCamera.CREATOR);
        parcelZza.recycle();
        return streetViewPanoramaCamera;
    }

    @Override // com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate
    public final StreetViewPanoramaLocation getStreetViewPanoramaLocation() {
        Parcel parcelZza = zza(14, zza());
        StreetViewPanoramaLocation streetViewPanoramaLocation = (StreetViewPanoramaLocation) com.google.android.gms.internal.maps.zzc.zza(parcelZza, StreetViewPanoramaLocation.CREATOR);
        parcelZza.recycle();
        return streetViewPanoramaLocation;
    }

    @Override // com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate
    public final boolean isPanningGesturesEnabled() {
        Parcel parcelZza = zza(6, zza());
        boolean zZza = com.google.android.gms.internal.maps.zzc.zza(parcelZza);
        parcelZza.recycle();
        return zZza;
    }

    @Override // com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate
    public final boolean isStreetNamesEnabled() {
        Parcel parcelZza = zza(8, zza());
        boolean zZza = com.google.android.gms.internal.maps.zzc.zza(parcelZza);
        parcelZza.recycle();
        return zZza;
    }

    @Override // com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate
    public final boolean isUserNavigationEnabled() {
        Parcel parcelZza = zza(7, zza());
        boolean zZza = com.google.android.gms.internal.maps.zzc.zza(parcelZza);
        parcelZza.recycle();
        return zZza;
    }

    @Override // com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate
    public final boolean isZoomGesturesEnabled() {
        Parcel parcelZza = zza(5, zza());
        boolean zZza = com.google.android.gms.internal.maps.zzc.zza(parcelZza);
        parcelZza.recycle();
        return zZza;
    }

    @Override // com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate
    public final IObjectWrapper orientationToPoint(StreetViewPanoramaOrientation streetViewPanoramaOrientation) {
        Parcel parcelZza = zza();
        com.google.android.gms.internal.maps.zzc.zza(parcelZza, streetViewPanoramaOrientation);
        Parcel parcelZza2 = zza(19, parcelZza);
        IObjectWrapper iObjectWrapperAsInterface = IObjectWrapper.Stub.asInterface(parcelZza2.readStrongBinder());
        parcelZza2.recycle();
        return iObjectWrapperAsInterface;
    }

    @Override // com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate
    public final StreetViewPanoramaOrientation pointToOrientation(IObjectWrapper iObjectWrapper) {
        Parcel parcelZza = zza();
        com.google.android.gms.internal.maps.zzc.zza(parcelZza, iObjectWrapper);
        Parcel parcelZza2 = zza(18, parcelZza);
        StreetViewPanoramaOrientation streetViewPanoramaOrientation = (StreetViewPanoramaOrientation) com.google.android.gms.internal.maps.zzc.zza(parcelZza2, StreetViewPanoramaOrientation.CREATOR);
        parcelZza2.recycle();
        return streetViewPanoramaOrientation;
    }

    @Override // com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate
    public final void setOnStreetViewPanoramaCameraChangeListener(zzbh zzbhVar) {
        Parcel parcelZza = zza();
        com.google.android.gms.internal.maps.zzc.zza(parcelZza, zzbhVar);
        zzb(16, parcelZza);
    }

    @Override // com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate
    public final void setOnStreetViewPanoramaChangeListener(zzbj zzbjVar) {
        Parcel parcelZza = zza();
        com.google.android.gms.internal.maps.zzc.zza(parcelZza, zzbjVar);
        zzb(15, parcelZza);
    }

    @Override // com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate
    public final void setOnStreetViewPanoramaClickListener(zzbl zzblVar) {
        Parcel parcelZza = zza();
        com.google.android.gms.internal.maps.zzc.zza(parcelZza, zzblVar);
        zzb(17, parcelZza);
    }

    @Override // com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate
    public final void setOnStreetViewPanoramaLongClickListener(zzbn zzbnVar) {
        Parcel parcelZza = zza();
        com.google.android.gms.internal.maps.zzc.zza(parcelZza, zzbnVar);
        zzb(20, parcelZza);
    }

    @Override // com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate
    public final void setPosition(LatLng latLng) {
        Parcel parcelZza = zza();
        com.google.android.gms.internal.maps.zzc.zza(parcelZza, latLng);
        zzb(12, parcelZza);
    }

    @Override // com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate
    public final void setPositionWithID(String str) {
        Parcel parcelZza = zza();
        parcelZza.writeString(str);
        zzb(11, parcelZza);
    }

    @Override // com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate
    public final void setPositionWithRadius(LatLng latLng, int i9) {
        Parcel parcelZza = zza();
        com.google.android.gms.internal.maps.zzc.zza(parcelZza, latLng);
        parcelZza.writeInt(i9);
        zzb(13, parcelZza);
    }

    @Override // com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate
    public final void setPositionWithRadiusAndSource(LatLng latLng, int i9, StreetViewSource streetViewSource) {
        Parcel parcelZza = zza();
        com.google.android.gms.internal.maps.zzc.zza(parcelZza, latLng);
        parcelZza.writeInt(i9);
        com.google.android.gms.internal.maps.zzc.zza(parcelZza, streetViewSource);
        zzb(22, parcelZza);
    }

    @Override // com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate
    public final void setPositionWithSource(LatLng latLng, StreetViewSource streetViewSource) {
        Parcel parcelZza = zza();
        com.google.android.gms.internal.maps.zzc.zza(parcelZza, latLng);
        com.google.android.gms.internal.maps.zzc.zza(parcelZza, streetViewSource);
        zzb(21, parcelZza);
    }
}
