package com.google.android.material.shape;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.material.internal.Experimental;

@Experimental("The shapes API is currently experimental and subject to change")
/* loaded from: classes2.dex */
public class EdgeTreatment {
    public void getEdgePath(float f9, float f10, ShapePath shapePath) {
        shapePath.lineTo(f9, BitmapDescriptorFactory.HUE_RED);
    }
}
