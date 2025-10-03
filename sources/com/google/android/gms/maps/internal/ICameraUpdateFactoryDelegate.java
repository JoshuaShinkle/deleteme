package com.google.android.gms.maps.internal;

import android.os.IInterface;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

/* loaded from: classes2.dex */
public interface ICameraUpdateFactoryDelegate extends IInterface {
    IObjectWrapper newCameraPosition(CameraPosition cameraPosition);

    IObjectWrapper newLatLng(LatLng latLng);

    IObjectWrapper newLatLngBounds(LatLngBounds latLngBounds, int i9);

    IObjectWrapper newLatLngBoundsWithSize(LatLngBounds latLngBounds, int i9, int i10, int i11);

    IObjectWrapper newLatLngZoom(LatLng latLng, float f9);

    IObjectWrapper scrollBy(float f9, float f10);

    IObjectWrapper zoomBy(float f9);

    IObjectWrapper zoomByWithFocus(float f9, int i9, int i10);

    IObjectWrapper zoomIn();

    IObjectWrapper zoomOut();

    IObjectWrapper zoomTo(float f9);
}
