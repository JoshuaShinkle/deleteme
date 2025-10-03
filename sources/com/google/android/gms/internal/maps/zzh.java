package com.google.android.gms.internal.maps;

import android.os.IInterface;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PatternItem;
import java.util.List;

/* loaded from: classes2.dex */
public interface zzh extends IInterface {
    LatLng getCenter();

    int getFillColor();

    String getId();

    double getRadius();

    int getStrokeColor();

    List<PatternItem> getStrokePattern();

    float getStrokeWidth();

    float getZIndex();

    boolean isClickable();

    boolean isVisible();

    void remove();

    void setCenter(LatLng latLng);

    void setClickable(boolean z8);

    void setFillColor(int i9);

    void setRadius(double d9);

    void setStrokeColor(int i9);

    void setStrokePattern(List<PatternItem> list);

    void setStrokeWidth(float f9);

    void setVisible(boolean z8);

    void setZIndex(float f9);

    boolean zzb(zzh zzhVar);

    void zze(IObjectWrapper iObjectWrapper);

    int zzj();

    IObjectWrapper zzk();
}
