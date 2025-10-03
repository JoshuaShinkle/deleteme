package com.google.android.gms.maps.model;

import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.maps.zzw;
import java.util.List;

/* loaded from: classes2.dex */
public final class Polygon {
    private final zzw zzdw;

    public Polygon(zzw zzwVar) {
        this.zzdw = (zzw) Preconditions.checkNotNull(zzwVar);
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof Polygon)) {
            return false;
        }
        try {
            return this.zzdw.zzb(((Polygon) obj).zzdw);
        } catch (RemoteException e9) {
            throw new RuntimeRemoteException(e9);
        }
    }

    public final int getFillColor() {
        try {
            return this.zzdw.getFillColor();
        } catch (RemoteException e9) {
            throw new RuntimeRemoteException(e9);
        }
    }

    public final List<List<LatLng>> getHoles() {
        try {
            return this.zzdw.getHoles();
        } catch (RemoteException e9) {
            throw new RuntimeRemoteException(e9);
        }
    }

    public final String getId() {
        try {
            return this.zzdw.getId();
        } catch (RemoteException e9) {
            throw new RuntimeRemoteException(e9);
        }
    }

    public final List<LatLng> getPoints() {
        try {
            return this.zzdw.getPoints();
        } catch (RemoteException e9) {
            throw new RuntimeRemoteException(e9);
        }
    }

    public final int getStrokeColor() {
        try {
            return this.zzdw.getStrokeColor();
        } catch (RemoteException e9) {
            throw new RuntimeRemoteException(e9);
        }
    }

    public final int getStrokeJointType() {
        try {
            return this.zzdw.getStrokeJointType();
        } catch (RemoteException e9) {
            throw new RuntimeRemoteException(e9);
        }
    }

    public final List<PatternItem> getStrokePattern() {
        try {
            return PatternItem.zza(this.zzdw.getStrokePattern());
        } catch (RemoteException e9) {
            throw new RuntimeRemoteException(e9);
        }
    }

    public final float getStrokeWidth() {
        try {
            return this.zzdw.getStrokeWidth();
        } catch (RemoteException e9) {
            throw new RuntimeRemoteException(e9);
        }
    }

    public final Object getTag() {
        try {
            return ObjectWrapper.unwrap(this.zzdw.zzk());
        } catch (RemoteException e9) {
            throw new RuntimeRemoteException(e9);
        }
    }

    public final float getZIndex() {
        try {
            return this.zzdw.getZIndex();
        } catch (RemoteException e9) {
            throw new RuntimeRemoteException(e9);
        }
    }

    public final int hashCode() {
        try {
            return this.zzdw.zzj();
        } catch (RemoteException e9) {
            throw new RuntimeRemoteException(e9);
        }
    }

    public final boolean isClickable() {
        try {
            return this.zzdw.isClickable();
        } catch (RemoteException e9) {
            throw new RuntimeRemoteException(e9);
        }
    }

    public final boolean isGeodesic() {
        try {
            return this.zzdw.isGeodesic();
        } catch (RemoteException e9) {
            throw new RuntimeRemoteException(e9);
        }
    }

    public final boolean isVisible() {
        try {
            return this.zzdw.isVisible();
        } catch (RemoteException e9) {
            throw new RuntimeRemoteException(e9);
        }
    }

    public final void remove() {
        try {
            this.zzdw.remove();
        } catch (RemoteException e9) {
            throw new RuntimeRemoteException(e9);
        }
    }

    public final void setClickable(boolean z8) {
        try {
            this.zzdw.setClickable(z8);
        } catch (RemoteException e9) {
            throw new RuntimeRemoteException(e9);
        }
    }

    public final void setFillColor(int i9) {
        try {
            this.zzdw.setFillColor(i9);
        } catch (RemoteException e9) {
            throw new RuntimeRemoteException(e9);
        }
    }

    public final void setGeodesic(boolean z8) {
        try {
            this.zzdw.setGeodesic(z8);
        } catch (RemoteException e9) {
            throw new RuntimeRemoteException(e9);
        }
    }

    public final void setHoles(List<? extends List<LatLng>> list) {
        try {
            this.zzdw.setHoles(list);
        } catch (RemoteException e9) {
            throw new RuntimeRemoteException(e9);
        }
    }

    public final void setPoints(List<LatLng> list) {
        try {
            this.zzdw.setPoints(list);
        } catch (RemoteException e9) {
            throw new RuntimeRemoteException(e9);
        }
    }

    public final void setStrokeColor(int i9) {
        try {
            this.zzdw.setStrokeColor(i9);
        } catch (RemoteException e9) {
            throw new RuntimeRemoteException(e9);
        }
    }

    public final void setStrokeJointType(int i9) {
        try {
            this.zzdw.setStrokeJointType(i9);
        } catch (RemoteException e9) {
            throw new RuntimeRemoteException(e9);
        }
    }

    public final void setStrokePattern(List<PatternItem> list) {
        try {
            this.zzdw.setStrokePattern(list);
        } catch (RemoteException e9) {
            throw new RuntimeRemoteException(e9);
        }
    }

    public final void setStrokeWidth(float f9) {
        try {
            this.zzdw.setStrokeWidth(f9);
        } catch (RemoteException e9) {
            throw new RuntimeRemoteException(e9);
        }
    }

    public final void setTag(Object obj) {
        try {
            this.zzdw.zze(ObjectWrapper.wrap(obj));
        } catch (RemoteException e9) {
            throw new RuntimeRemoteException(e9);
        }
    }

    public final void setVisible(boolean z8) {
        try {
            this.zzdw.setVisible(z8);
        } catch (RemoteException e9) {
            throw new RuntimeRemoteException(e9);
        }
    }

    public final void setZIndex(float f9) {
        try {
            this.zzdw.setZIndex(f9);
        } catch (RemoteException e9) {
            throw new RuntimeRemoteException(e9);
        }
    }
}
