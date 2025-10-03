package com.google.android.gms.maps.internal;

import android.os.IInterface;

/* loaded from: classes2.dex */
public interface IUiSettingsDelegate extends IInterface {
    boolean isCompassEnabled();

    boolean isIndoorLevelPickerEnabled();

    boolean isMapToolbarEnabled();

    boolean isMyLocationButtonEnabled();

    boolean isRotateGesturesEnabled();

    boolean isScrollGesturesEnabled();

    boolean isScrollGesturesEnabledDuringRotateOrZoom();

    boolean isTiltGesturesEnabled();

    boolean isZoomControlsEnabled();

    boolean isZoomGesturesEnabled();

    void setAllGesturesEnabled(boolean z8);

    void setCompassEnabled(boolean z8);

    void setIndoorLevelPickerEnabled(boolean z8);

    void setMapToolbarEnabled(boolean z8);

    void setMyLocationButtonEnabled(boolean z8);

    void setRotateGesturesEnabled(boolean z8);

    void setScrollGesturesEnabled(boolean z8);

    void setScrollGesturesEnabledDuringRotateOrZoom(boolean z8);

    void setTiltGesturesEnabled(boolean z8);

    void setZoomControlsEnabled(boolean z8);

    void setZoomGesturesEnabled(boolean z8);
}
