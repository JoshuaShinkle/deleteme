package com.google.android.gms.internal.maps;

import android.os.IInterface;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

/* loaded from: classes2.dex */
public interface zzk extends IInterface {
    float getBearing();

    LatLngBounds getBounds();

    float getHeight();

    String getId();

    LatLng getPosition();

    float getTransparency();

    float getWidth();

    float getZIndex();

    boolean isClickable();

    boolean isVisible();

    void remove();

    void setBearing(float f9);

    void setClickable(boolean z8);

    void setDimensions(float f9);

    void setPosition(LatLng latLng);

    void setPositionFromBounds(LatLngBounds latLngBounds);

    void setTransparency(float f9);

    void setVisible(boolean z8);

    void setZIndex(float f9);

    void zza(float f9, float f10);

    boolean zzb(zzk zzkVar);

    void zze(IObjectWrapper iObjectWrapper);

    void zzf(IObjectWrapper iObjectWrapper);

    int zzj();

    IObjectWrapper zzk();
}
