package com.google.android.gms.internal.maps;

import android.os.IInterface;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.maps.model.LatLng;

/* loaded from: classes2.dex */
public interface zzt extends IInterface {
    float getAlpha();

    String getId();

    LatLng getPosition();

    float getRotation();

    String getSnippet();

    String getTitle();

    float getZIndex();

    void hideInfoWindow();

    boolean isDraggable();

    boolean isFlat();

    boolean isInfoWindowShown();

    boolean isVisible();

    void remove();

    void setAlpha(float f9);

    void setAnchor(float f9, float f10);

    void setDraggable(boolean z8);

    void setFlat(boolean z8);

    void setInfoWindowAnchor(float f9, float f10);

    void setPosition(LatLng latLng);

    void setRotation(float f9);

    void setSnippet(String str);

    void setTitle(String str);

    void setVisible(boolean z8);

    void setZIndex(float f9);

    void showInfoWindow();

    void zze(IObjectWrapper iObjectWrapper);

    void zzg(IObjectWrapper iObjectWrapper);

    int zzj();

    boolean zzj(zzt zztVar);

    IObjectWrapper zzk();
}
