package com.google.android.gms.maps;

import android.os.RemoteException;
import com.google.android.gms.maps.internal.IUiSettingsDelegate;
import com.google.android.gms.maps.model.RuntimeRemoteException;

/* loaded from: classes2.dex */
public final class UiSettings {
    private final IUiSettingsDelegate zzcj;

    public UiSettings(IUiSettingsDelegate iUiSettingsDelegate) {
        this.zzcj = iUiSettingsDelegate;
    }

    public final boolean isCompassEnabled() {
        try {
            return this.zzcj.isCompassEnabled();
        } catch (RemoteException e9) {
            throw new RuntimeRemoteException(e9);
        }
    }

    public final boolean isIndoorLevelPickerEnabled() {
        try {
            return this.zzcj.isIndoorLevelPickerEnabled();
        } catch (RemoteException e9) {
            throw new RuntimeRemoteException(e9);
        }
    }

    public final boolean isMapToolbarEnabled() {
        try {
            return this.zzcj.isMapToolbarEnabled();
        } catch (RemoteException e9) {
            throw new RuntimeRemoteException(e9);
        }
    }

    public final boolean isMyLocationButtonEnabled() {
        try {
            return this.zzcj.isMyLocationButtonEnabled();
        } catch (RemoteException e9) {
            throw new RuntimeRemoteException(e9);
        }
    }

    public final boolean isRotateGesturesEnabled() {
        try {
            return this.zzcj.isRotateGesturesEnabled();
        } catch (RemoteException e9) {
            throw new RuntimeRemoteException(e9);
        }
    }

    public final boolean isScrollGesturesEnabled() {
        try {
            return this.zzcj.isScrollGesturesEnabled();
        } catch (RemoteException e9) {
            throw new RuntimeRemoteException(e9);
        }
    }

    public final boolean isScrollGesturesEnabledDuringRotateOrZoom() {
        try {
            return this.zzcj.isScrollGesturesEnabled();
        } catch (RemoteException e9) {
            throw new RuntimeRemoteException(e9);
        }
    }

    public final boolean isTiltGesturesEnabled() {
        try {
            return this.zzcj.isTiltGesturesEnabled();
        } catch (RemoteException e9) {
            throw new RuntimeRemoteException(e9);
        }
    }

    public final boolean isZoomControlsEnabled() {
        try {
            return this.zzcj.isZoomControlsEnabled();
        } catch (RemoteException e9) {
            throw new RuntimeRemoteException(e9);
        }
    }

    public final boolean isZoomGesturesEnabled() {
        try {
            return this.zzcj.isZoomGesturesEnabled();
        } catch (RemoteException e9) {
            throw new RuntimeRemoteException(e9);
        }
    }

    public final void setAllGesturesEnabled(boolean z8) {
        try {
            this.zzcj.setAllGesturesEnabled(z8);
        } catch (RemoteException e9) {
            throw new RuntimeRemoteException(e9);
        }
    }

    public final void setCompassEnabled(boolean z8) {
        try {
            this.zzcj.setCompassEnabled(z8);
        } catch (RemoteException e9) {
            throw new RuntimeRemoteException(e9);
        }
    }

    public final void setIndoorLevelPickerEnabled(boolean z8) {
        try {
            this.zzcj.setIndoorLevelPickerEnabled(z8);
        } catch (RemoteException e9) {
            throw new RuntimeRemoteException(e9);
        }
    }

    public final void setMapToolbarEnabled(boolean z8) {
        try {
            this.zzcj.setMapToolbarEnabled(z8);
        } catch (RemoteException e9) {
            throw new RuntimeRemoteException(e9);
        }
    }

    public final void setMyLocationButtonEnabled(boolean z8) {
        try {
            this.zzcj.setMyLocationButtonEnabled(z8);
        } catch (RemoteException e9) {
            throw new RuntimeRemoteException(e9);
        }
    }

    public final void setRotateGesturesEnabled(boolean z8) {
        try {
            this.zzcj.setRotateGesturesEnabled(z8);
        } catch (RemoteException e9) {
            throw new RuntimeRemoteException(e9);
        }
    }

    public final void setScrollGesturesEnabled(boolean z8) {
        try {
            this.zzcj.setScrollGesturesEnabled(z8);
        } catch (RemoteException e9) {
            throw new RuntimeRemoteException(e9);
        }
    }

    public final void setScrollGesturesEnabledDuringRotateOrZoom(boolean z8) {
        try {
            this.zzcj.setScrollGesturesEnabledDuringRotateOrZoom(z8);
        } catch (RemoteException e9) {
            throw new RuntimeRemoteException(e9);
        }
    }

    public final void setTiltGesturesEnabled(boolean z8) {
        try {
            this.zzcj.setTiltGesturesEnabled(z8);
        } catch (RemoteException e9) {
            throw new RuntimeRemoteException(e9);
        }
    }

    public final void setZoomControlsEnabled(boolean z8) {
        try {
            this.zzcj.setZoomControlsEnabled(z8);
        } catch (RemoteException e9) {
            throw new RuntimeRemoteException(e9);
        }
    }

    public final void setZoomGesturesEnabled(boolean z8) {
        try {
            this.zzcj.setZoomGesturesEnabled(z8);
        } catch (RemoteException e9) {
            throw new RuntimeRemoteException(e9);
        }
    }
}
