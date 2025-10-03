package com.google.android.gms.maps;

import android.graphics.Point;
import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.RuntimeRemoteException;

/* loaded from: classes2.dex */
public final class CameraUpdateFactory {
    private static ICameraUpdateFactoryDelegate zzf;

    private CameraUpdateFactory() {
    }

    public static CameraUpdate newCameraPosition(CameraPosition cameraPosition) {
        try {
            return new CameraUpdate(zzc().newCameraPosition(cameraPosition));
        } catch (RemoteException e9) {
            throw new RuntimeRemoteException(e9);
        }
    }

    public static CameraUpdate newLatLng(LatLng latLng) {
        try {
            return new CameraUpdate(zzc().newLatLng(latLng));
        } catch (RemoteException e9) {
            throw new RuntimeRemoteException(e9);
        }
    }

    public static CameraUpdate newLatLngBounds(LatLngBounds latLngBounds, int i9) {
        try {
            return new CameraUpdate(zzc().newLatLngBounds(latLngBounds, i9));
        } catch (RemoteException e9) {
            throw new RuntimeRemoteException(e9);
        }
    }

    public static CameraUpdate newLatLngZoom(LatLng latLng, float f9) {
        try {
            return new CameraUpdate(zzc().newLatLngZoom(latLng, f9));
        } catch (RemoteException e9) {
            throw new RuntimeRemoteException(e9);
        }
    }

    public static CameraUpdate scrollBy(float f9, float f10) {
        try {
            return new CameraUpdate(zzc().scrollBy(f9, f10));
        } catch (RemoteException e9) {
            throw new RuntimeRemoteException(e9);
        }
    }

    public static CameraUpdate zoomBy(float f9) {
        try {
            return new CameraUpdate(zzc().zoomBy(f9));
        } catch (RemoteException e9) {
            throw new RuntimeRemoteException(e9);
        }
    }

    public static CameraUpdate zoomIn() {
        try {
            return new CameraUpdate(zzc().zoomIn());
        } catch (RemoteException e9) {
            throw new RuntimeRemoteException(e9);
        }
    }

    public static CameraUpdate zoomOut() {
        try {
            return new CameraUpdate(zzc().zoomOut());
        } catch (RemoteException e9) {
            throw new RuntimeRemoteException(e9);
        }
    }

    public static CameraUpdate zoomTo(float f9) {
        try {
            return new CameraUpdate(zzc().zoomTo(f9));
        } catch (RemoteException e9) {
            throw new RuntimeRemoteException(e9);
        }
    }

    public static void zza(ICameraUpdateFactoryDelegate iCameraUpdateFactoryDelegate) {
        zzf = (ICameraUpdateFactoryDelegate) Preconditions.checkNotNull(iCameraUpdateFactoryDelegate);
    }

    private static ICameraUpdateFactoryDelegate zzc() {
        return (ICameraUpdateFactoryDelegate) Preconditions.checkNotNull(zzf, "CameraUpdateFactory is not initialized");
    }

    public static CameraUpdate newLatLngBounds(LatLngBounds latLngBounds, int i9, int i10, int i11) {
        try {
            return new CameraUpdate(zzc().newLatLngBoundsWithSize(latLngBounds, i9, i10, i11));
        } catch (RemoteException e9) {
            throw new RuntimeRemoteException(e9);
        }
    }

    public static CameraUpdate zoomBy(float f9, Point point) {
        try {
            return new CameraUpdate(zzc().zoomByWithFocus(f9, point.x, point.y));
        } catch (RemoteException e9) {
            throw new RuntimeRemoteException(e9);
        }
    }
}
