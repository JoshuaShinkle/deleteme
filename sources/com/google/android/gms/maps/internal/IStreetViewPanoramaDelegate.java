package com.google.android.gms.maps.internal;

import android.os.IInterface;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.StreetViewPanoramaCamera;
import com.google.android.gms.maps.model.StreetViewPanoramaLocation;
import com.google.android.gms.maps.model.StreetViewPanoramaOrientation;
import com.google.android.gms.maps.model.StreetViewSource;

/* loaded from: classes2.dex */
public interface IStreetViewPanoramaDelegate extends IInterface {
    void animateTo(StreetViewPanoramaCamera streetViewPanoramaCamera, long j9);

    void enablePanning(boolean z8);

    void enableStreetNames(boolean z8);

    void enableUserNavigation(boolean z8);

    void enableZoom(boolean z8);

    StreetViewPanoramaCamera getPanoramaCamera();

    StreetViewPanoramaLocation getStreetViewPanoramaLocation();

    boolean isPanningGesturesEnabled();

    boolean isStreetNamesEnabled();

    boolean isUserNavigationEnabled();

    boolean isZoomGesturesEnabled();

    IObjectWrapper orientationToPoint(StreetViewPanoramaOrientation streetViewPanoramaOrientation);

    StreetViewPanoramaOrientation pointToOrientation(IObjectWrapper iObjectWrapper);

    void setOnStreetViewPanoramaCameraChangeListener(zzbh zzbhVar);

    void setOnStreetViewPanoramaChangeListener(zzbj zzbjVar);

    void setOnStreetViewPanoramaClickListener(zzbl zzblVar);

    void setOnStreetViewPanoramaLongClickListener(zzbn zzbnVar);

    void setPosition(LatLng latLng);

    void setPositionWithID(String str);

    void setPositionWithRadius(LatLng latLng, int i9);

    void setPositionWithRadiusAndSource(LatLng latLng, int i9, StreetViewSource streetViewSource);

    void setPositionWithSource(LatLng latLng, StreetViewSource streetViewSource);
}
