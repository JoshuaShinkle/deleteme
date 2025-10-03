package com.google.android.gms.maps.model;

import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.ObjectWrapper;

/* loaded from: classes2.dex */
public final class GroundOverlay {
    private final com.google.android.gms.internal.maps.zzk zzcw;

    public GroundOverlay(com.google.android.gms.internal.maps.zzk zzkVar) {
        this.zzcw = (com.google.android.gms.internal.maps.zzk) Preconditions.checkNotNull(zzkVar);
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof GroundOverlay)) {
            return false;
        }
        try {
            return this.zzcw.zzb(((GroundOverlay) obj).zzcw);
        } catch (RemoteException e9) {
            throw new RuntimeRemoteException(e9);
        }
    }

    public final float getBearing() {
        try {
            return this.zzcw.getBearing();
        } catch (RemoteException e9) {
            throw new RuntimeRemoteException(e9);
        }
    }

    public final LatLngBounds getBounds() {
        try {
            return this.zzcw.getBounds();
        } catch (RemoteException e9) {
            throw new RuntimeRemoteException(e9);
        }
    }

    public final float getHeight() {
        try {
            return this.zzcw.getHeight();
        } catch (RemoteException e9) {
            throw new RuntimeRemoteException(e9);
        }
    }

    public final String getId() {
        try {
            return this.zzcw.getId();
        } catch (RemoteException e9) {
            throw new RuntimeRemoteException(e9);
        }
    }

    public final LatLng getPosition() {
        try {
            return this.zzcw.getPosition();
        } catch (RemoteException e9) {
            throw new RuntimeRemoteException(e9);
        }
    }

    public final Object getTag() {
        try {
            return ObjectWrapper.unwrap(this.zzcw.zzk());
        } catch (RemoteException e9) {
            throw new RuntimeRemoteException(e9);
        }
    }

    public final float getTransparency() {
        try {
            return this.zzcw.getTransparency();
        } catch (RemoteException e9) {
            throw new RuntimeRemoteException(e9);
        }
    }

    public final float getWidth() {
        try {
            return this.zzcw.getWidth();
        } catch (RemoteException e9) {
            throw new RuntimeRemoteException(e9);
        }
    }

    public final float getZIndex() {
        try {
            return this.zzcw.getZIndex();
        } catch (RemoteException e9) {
            throw new RuntimeRemoteException(e9);
        }
    }

    public final int hashCode() {
        try {
            return this.zzcw.zzj();
        } catch (RemoteException e9) {
            throw new RuntimeRemoteException(e9);
        }
    }

    public final boolean isClickable() {
        try {
            return this.zzcw.isClickable();
        } catch (RemoteException e9) {
            throw new RuntimeRemoteException(e9);
        }
    }

    public final boolean isVisible() {
        try {
            return this.zzcw.isVisible();
        } catch (RemoteException e9) {
            throw new RuntimeRemoteException(e9);
        }
    }

    public final void remove() {
        try {
            this.zzcw.remove();
        } catch (RemoteException e9) {
            throw new RuntimeRemoteException(e9);
        }
    }

    public final void setBearing(float f9) {
        try {
            this.zzcw.setBearing(f9);
        } catch (RemoteException e9) {
            throw new RuntimeRemoteException(e9);
        }
    }

    public final void setClickable(boolean z8) {
        try {
            this.zzcw.setClickable(z8);
        } catch (RemoteException e9) {
            throw new RuntimeRemoteException(e9);
        }
    }

    public final void setDimensions(float f9) {
        try {
            this.zzcw.setDimensions(f9);
        } catch (RemoteException e9) {
            throw new RuntimeRemoteException(e9);
        }
    }

    public final void setImage(BitmapDescriptor bitmapDescriptor) {
        Preconditions.checkNotNull(bitmapDescriptor, "imageDescriptor must not be null");
        try {
            this.zzcw.zzf(bitmapDescriptor.zzb());
        } catch (RemoteException e9) {
            throw new RuntimeRemoteException(e9);
        }
    }

    public final void setPosition(LatLng latLng) {
        try {
            this.zzcw.setPosition(latLng);
        } catch (RemoteException e9) {
            throw new RuntimeRemoteException(e9);
        }
    }

    public final void setPositionFromBounds(LatLngBounds latLngBounds) {
        try {
            this.zzcw.setPositionFromBounds(latLngBounds);
        } catch (RemoteException e9) {
            throw new RuntimeRemoteException(e9);
        }
    }

    public final void setTag(Object obj) {
        try {
            this.zzcw.zze(ObjectWrapper.wrap(obj));
        } catch (RemoteException e9) {
            throw new RuntimeRemoteException(e9);
        }
    }

    public final void setTransparency(float f9) {
        try {
            this.zzcw.setTransparency(f9);
        } catch (RemoteException e9) {
            throw new RuntimeRemoteException(e9);
        }
    }

    public final void setVisible(boolean z8) {
        try {
            this.zzcw.setVisible(z8);
        } catch (RemoteException e9) {
            throw new RuntimeRemoteException(e9);
        }
    }

    public final void setZIndex(float f9) {
        try {
            this.zzcw.setZIndex(f9);
        } catch (RemoteException e9) {
            throw new RuntimeRemoteException(e9);
        }
    }

    public final void setDimensions(float f9, float f10) {
        try {
            this.zzcw.zza(f9, f10);
        } catch (RemoteException e9) {
            throw new RuntimeRemoteException(e9);
        }
    }
}
