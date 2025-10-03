package com.google.android.gms.internal.maps;

import android.os.IInterface;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PatternItem;
import java.util.List;

/* loaded from: classes2.dex */
public interface zzw extends IInterface {
    int getFillColor();

    List getHoles();

    String getId();

    List<LatLng> getPoints();

    int getStrokeColor();

    int getStrokeJointType();

    List<PatternItem> getStrokePattern();

    float getStrokeWidth();

    float getZIndex();

    boolean isClickable();

    boolean isGeodesic();

    boolean isVisible();

    void remove();

    void setClickable(boolean z8);

    void setFillColor(int i9);

    void setGeodesic(boolean z8);

    void setHoles(List list);

    void setPoints(List<LatLng> list);

    void setStrokeColor(int i9);

    void setStrokeJointType(int i9);

    void setStrokePattern(List<PatternItem> list);

    void setStrokeWidth(float f9);

    void setVisible(boolean z8);

    void setZIndex(float f9);

    boolean zzb(zzw zzwVar);

    void zze(IObjectWrapper iObjectWrapper);

    int zzj();

    IObjectWrapper zzk();
}
