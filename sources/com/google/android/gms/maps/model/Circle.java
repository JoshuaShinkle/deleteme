package com.google.android.gms.maps.model;

import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.util.List;

/* loaded from: classes2.dex */
public final class Circle {
    private final com.google.android.gms.internal.maps.zzh zzco;

    public Circle(com.google.android.gms.internal.maps.zzh zzhVar) {
        this.zzco = (com.google.android.gms.internal.maps.zzh) Preconditions.checkNotNull(zzhVar);
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof Circle)) {
            return false;
        }
        try {
            return this.zzco.zzb(((Circle) obj).zzco);
        } catch (RemoteException e9) {
            throw new RuntimeRemoteException(e9);
        }
    }

    public final LatLng getCenter() {
        try {
            return this.zzco.getCenter();
        } catch (RemoteException e9) {
            throw new RuntimeRemoteException(e9);
        }
    }

    public final int getFillColor() {
        try {
            return this.zzco.getFillColor();
        } catch (RemoteException e9) {
            throw new RuntimeRemoteException(e9);
        }
    }

    public final String getId() {
        try {
            return this.zzco.getId();
        } catch (RemoteException e9) {
            throw new RuntimeRemoteException(e9);
        }
    }

    public final double getRadius() {
        try {
            return this.zzco.getRadius();
        } catch (RemoteException e9) {
            throw new RuntimeRemoteException(e9);
        }
    }

    public final int getStrokeColor() {
        try {
            return this.zzco.getStrokeColor();
        } catch (RemoteException e9) {
            throw new RuntimeRemoteException(e9);
        }
    }

    public final List<PatternItem> getStrokePattern() {
        try {
            return PatternItem.zza(this.zzco.getStrokePattern());
        } catch (RemoteException e9) {
            throw new RuntimeRemoteException(e9);
        }
    }

    public final float getStrokeWidth() {
        try {
            return this.zzco.getStrokeWidth();
        } catch (RemoteException e9) {
            throw new RuntimeRemoteException(e9);
        }
    }

    public final Object getTag() {
        try {
            return ObjectWrapper.unwrap(this.zzco.zzk());
        } catch (RemoteException e9) {
            throw new RuntimeRemoteException(e9);
        }
    }

    public final float getZIndex() {
        try {
            return this.zzco.getZIndex();
        } catch (RemoteException e9) {
            throw new RuntimeRemoteException(e9);
        }
    }

    public final int hashCode() {
        try {
            return this.zzco.zzj();
        } catch (RemoteException e9) {
            throw new RuntimeRemoteException(e9);
        }
    }

    public final boolean isClickable() {
        try {
            return this.zzco.isClickable();
        } catch (RemoteException e9) {
            throw new RuntimeRemoteException(e9);
        }
    }

    public final boolean isVisible() {
        try {
            return this.zzco.isVisible();
        } catch (RemoteException e9) {
            throw new RuntimeRemoteException(e9);
        }
    }

    public final void remove() {
        try {
            this.zzco.remove();
        } catch (RemoteException e9) {
            throw new RuntimeRemoteException(e9);
        }
    }

    public final void setCenter(LatLng latLng) {
        try {
            this.zzco.setCenter(latLng);
        } catch (RemoteException e9) {
            throw new RuntimeRemoteException(e9);
        }
    }

    public final void setClickable(boolean z8) {
        try {
            this.zzco.setClickable(z8);
        } catch (RemoteException e9) {
            throw new RuntimeRemoteException(e9);
        }
    }

    public final void setFillColor(int i9) {
        try {
            this.zzco.setFillColor(i9);
        } catch (RemoteException e9) {
            throw new RuntimeRemoteException(e9);
        }
    }

    public final void setRadius(double d9) {
        try {
            this.zzco.setRadius(d9);
        } catch (RemoteException e9) {
            throw new RuntimeRemoteException(e9);
        }
    }

    public final void setStrokeColor(int i9) {
        try {
            this.zzco.setStrokeColor(i9);
        } catch (RemoteException e9) {
            throw new RuntimeRemoteException(e9);
        }
    }

    public final void setStrokePattern(List<PatternItem> list) {
        try {
            this.zzco.setStrokePattern(list);
        } catch (RemoteException e9) {
            throw new RuntimeRemoteException(e9);
        }
    }

    public final void setStrokeWidth(float f9) {
        try {
            this.zzco.setStrokeWidth(f9);
        } catch (RemoteException e9) {
            throw new RuntimeRemoteException(e9);
        }
    }

    public final void setTag(Object obj) {
        try {
            this.zzco.zze(ObjectWrapper.wrap(obj));
        } catch (RemoteException e9) {
            throw new RuntimeRemoteException(e9);
        }
    }

    public final void setVisible(boolean z8) {
        try {
            this.zzco.setVisible(z8);
        } catch (RemoteException e9) {
            throw new RuntimeRemoteException(e9);
        }
    }

    public final void setZIndex(float f9) {
        try {
            this.zzco.setZIndex(f9);
        } catch (RemoteException e9) {
            throw new RuntimeRemoteException(e9);
        }
    }
}
