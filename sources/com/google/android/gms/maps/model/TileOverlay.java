package com.google.android.gms.maps.model;

import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.maps.zzac;

/* loaded from: classes2.dex */
public final class TileOverlay {
    private final zzac zzeh;

    public TileOverlay(zzac zzacVar) {
        this.zzeh = (zzac) Preconditions.checkNotNull(zzacVar);
    }

    public final void clearTileCache() {
        try {
            this.zzeh.clearTileCache();
        } catch (RemoteException e9) {
            throw new RuntimeRemoteException(e9);
        }
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof TileOverlay)) {
            return false;
        }
        try {
            return this.zzeh.zza(((TileOverlay) obj).zzeh);
        } catch (RemoteException e9) {
            throw new RuntimeRemoteException(e9);
        }
    }

    public final boolean getFadeIn() {
        try {
            return this.zzeh.getFadeIn();
        } catch (RemoteException e9) {
            throw new RuntimeRemoteException(e9);
        }
    }

    public final String getId() {
        try {
            return this.zzeh.getId();
        } catch (RemoteException e9) {
            throw new RuntimeRemoteException(e9);
        }
    }

    public final float getTransparency() {
        try {
            return this.zzeh.getTransparency();
        } catch (RemoteException e9) {
            throw new RuntimeRemoteException(e9);
        }
    }

    public final float getZIndex() {
        try {
            return this.zzeh.getZIndex();
        } catch (RemoteException e9) {
            throw new RuntimeRemoteException(e9);
        }
    }

    public final int hashCode() {
        try {
            return this.zzeh.zzj();
        } catch (RemoteException e9) {
            throw new RuntimeRemoteException(e9);
        }
    }

    public final boolean isVisible() {
        try {
            return this.zzeh.isVisible();
        } catch (RemoteException e9) {
            throw new RuntimeRemoteException(e9);
        }
    }

    public final void remove() {
        try {
            this.zzeh.remove();
        } catch (RemoteException e9) {
            throw new RuntimeRemoteException(e9);
        }
    }

    public final void setFadeIn(boolean z8) {
        try {
            this.zzeh.setFadeIn(z8);
        } catch (RemoteException e9) {
            throw new RuntimeRemoteException(e9);
        }
    }

    public final void setTransparency(float f9) {
        try {
            this.zzeh.setTransparency(f9);
        } catch (RemoteException e9) {
            throw new RuntimeRemoteException(e9);
        }
    }

    public final void setVisible(boolean z8) {
        try {
            this.zzeh.setVisible(z8);
        } catch (RemoteException e9) {
            throw new RuntimeRemoteException(e9);
        }
    }

    public final void setZIndex(float f9) {
        try {
            this.zzeh.setZIndex(f9);
        } catch (RemoteException e9) {
            throw new RuntimeRemoteException(e9);
        }
    }
}
