package com.google.android.gms.maps.model;

import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.ObjectWrapper;

/* loaded from: classes2.dex */
public final class Marker {
    private final com.google.android.gms.internal.maps.zzt zzdm;

    public Marker(com.google.android.gms.internal.maps.zzt zztVar) {
        this.zzdm = (com.google.android.gms.internal.maps.zzt) Preconditions.checkNotNull(zztVar);
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof Marker)) {
            return false;
        }
        try {
            return this.zzdm.zzj(((Marker) obj).zzdm);
        } catch (RemoteException e9) {
            throw new RuntimeRemoteException(e9);
        }
    }

    public final float getAlpha() {
        try {
            return this.zzdm.getAlpha();
        } catch (RemoteException e9) {
            throw new RuntimeRemoteException(e9);
        }
    }

    public final String getId() {
        try {
            return this.zzdm.getId();
        } catch (RemoteException e9) {
            throw new RuntimeRemoteException(e9);
        }
    }

    public final LatLng getPosition() {
        try {
            return this.zzdm.getPosition();
        } catch (RemoteException e9) {
            throw new RuntimeRemoteException(e9);
        }
    }

    public final float getRotation() {
        try {
            return this.zzdm.getRotation();
        } catch (RemoteException e9) {
            throw new RuntimeRemoteException(e9);
        }
    }

    public final String getSnippet() {
        try {
            return this.zzdm.getSnippet();
        } catch (RemoteException e9) {
            throw new RuntimeRemoteException(e9);
        }
    }

    public final Object getTag() {
        try {
            return ObjectWrapper.unwrap(this.zzdm.zzk());
        } catch (RemoteException e9) {
            throw new RuntimeRemoteException(e9);
        }
    }

    public final String getTitle() {
        try {
            return this.zzdm.getTitle();
        } catch (RemoteException e9) {
            throw new RuntimeRemoteException(e9);
        }
    }

    public final float getZIndex() {
        try {
            return this.zzdm.getZIndex();
        } catch (RemoteException e9) {
            throw new RuntimeRemoteException(e9);
        }
    }

    public final int hashCode() {
        try {
            return this.zzdm.zzj();
        } catch (RemoteException e9) {
            throw new RuntimeRemoteException(e9);
        }
    }

    public final void hideInfoWindow() {
        try {
            this.zzdm.hideInfoWindow();
        } catch (RemoteException e9) {
            throw new RuntimeRemoteException(e9);
        }
    }

    public final boolean isDraggable() {
        try {
            return this.zzdm.isDraggable();
        } catch (RemoteException e9) {
            throw new RuntimeRemoteException(e9);
        }
    }

    public final boolean isFlat() {
        try {
            return this.zzdm.isFlat();
        } catch (RemoteException e9) {
            throw new RuntimeRemoteException(e9);
        }
    }

    public final boolean isInfoWindowShown() {
        try {
            return this.zzdm.isInfoWindowShown();
        } catch (RemoteException e9) {
            throw new RuntimeRemoteException(e9);
        }
    }

    public final boolean isVisible() {
        try {
            return this.zzdm.isVisible();
        } catch (RemoteException e9) {
            throw new RuntimeRemoteException(e9);
        }
    }

    public final void remove() {
        try {
            this.zzdm.remove();
        } catch (RemoteException e9) {
            throw new RuntimeRemoteException(e9);
        }
    }

    public final void setAlpha(float f9) {
        try {
            this.zzdm.setAlpha(f9);
        } catch (RemoteException e9) {
            throw new RuntimeRemoteException(e9);
        }
    }

    public final void setAnchor(float f9, float f10) {
        try {
            this.zzdm.setAnchor(f9, f10);
        } catch (RemoteException e9) {
            throw new RuntimeRemoteException(e9);
        }
    }

    public final void setDraggable(boolean z8) {
        try {
            this.zzdm.setDraggable(z8);
        } catch (RemoteException e9) {
            throw new RuntimeRemoteException(e9);
        }
    }

    public final void setFlat(boolean z8) {
        try {
            this.zzdm.setFlat(z8);
        } catch (RemoteException e9) {
            throw new RuntimeRemoteException(e9);
        }
    }

    public final void setIcon(BitmapDescriptor bitmapDescriptor) {
        try {
            if (bitmapDescriptor == null) {
                this.zzdm.zzg(null);
            } else {
                this.zzdm.zzg(bitmapDescriptor.zzb());
            }
        } catch (RemoteException e9) {
            throw new RuntimeRemoteException(e9);
        }
    }

    public final void setInfoWindowAnchor(float f9, float f10) {
        try {
            this.zzdm.setInfoWindowAnchor(f9, f10);
        } catch (RemoteException e9) {
            throw new RuntimeRemoteException(e9);
        }
    }

    public final void setPosition(LatLng latLng) {
        if (latLng == null) {
            throw new IllegalArgumentException("latlng cannot be null - a position is required.");
        }
        try {
            this.zzdm.setPosition(latLng);
        } catch (RemoteException e9) {
            throw new RuntimeRemoteException(e9);
        }
    }

    public final void setRotation(float f9) {
        try {
            this.zzdm.setRotation(f9);
        } catch (RemoteException e9) {
            throw new RuntimeRemoteException(e9);
        }
    }

    public final void setSnippet(String str) {
        try {
            this.zzdm.setSnippet(str);
        } catch (RemoteException e9) {
            throw new RuntimeRemoteException(e9);
        }
    }

    public final void setTag(Object obj) {
        try {
            this.zzdm.zze(ObjectWrapper.wrap(obj));
        } catch (RemoteException e9) {
            throw new RuntimeRemoteException(e9);
        }
    }

    public final void setTitle(String str) {
        try {
            this.zzdm.setTitle(str);
        } catch (RemoteException e9) {
            throw new RuntimeRemoteException(e9);
        }
    }

    public final void setVisible(boolean z8) {
        try {
            this.zzdm.setVisible(z8);
        } catch (RemoteException e9) {
            throw new RuntimeRemoteException(e9);
        }
    }

    public final void setZIndex(float f9) {
        try {
            this.zzdm.setZIndex(f9);
        } catch (RemoteException e9) {
            throw new RuntimeRemoteException(e9);
        }
    }

    public final void showInfoWindow() {
        try {
            this.zzdm.showInfoWindow();
        } catch (RemoteException e9) {
            throw new RuntimeRemoteException(e9);
        }
    }
}
