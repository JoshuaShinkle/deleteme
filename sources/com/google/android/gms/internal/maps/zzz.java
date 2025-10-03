package com.google.android.gms.internal.maps;

import android.os.IInterface;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.maps.model.Cap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PatternItem;
import java.util.List;

/* loaded from: classes2.dex */
public interface zzz extends IInterface {
    int getColor();

    Cap getEndCap();

    String getId();

    int getJointType();

    List<PatternItem> getPattern();

    List<LatLng> getPoints();

    Cap getStartCap();

    float getWidth();

    float getZIndex();

    boolean isClickable();

    boolean isGeodesic();

    boolean isVisible();

    void remove();

    void setClickable(boolean z8);

    void setColor(int i9);

    void setEndCap(Cap cap);

    void setGeodesic(boolean z8);

    void setJointType(int i9);

    void setPattern(List<PatternItem> list);

    void setPoints(List<LatLng> list);

    void setStartCap(Cap cap);

    void setVisible(boolean z8);

    void setWidth(float f9);

    void setZIndex(float f9);

    boolean zzb(zzz zzzVar);

    void zze(IObjectWrapper iObjectWrapper);

    int zzj();

    IObjectWrapper zzk();
}
