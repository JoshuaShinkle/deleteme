package com.google.android.material.shape;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.material.internal.Experimental;

@Experimental("The shapes API is currently experimental and subject to change")
/* loaded from: classes2.dex */
public class TriangleEdgeTreatment extends EdgeTreatment {
    private final boolean inside;
    private final float size;

    public TriangleEdgeTreatment(float f9, boolean z8) {
        this.size = f9;
        this.inside = z8;
    }

    @Override // com.google.android.material.shape.EdgeTreatment
    public void getEdgePath(float f9, float f10, ShapePath shapePath) {
        float f11 = f9 / 2.0f;
        shapePath.lineTo(f11 - (this.size * f10), BitmapDescriptorFactory.HUE_RED);
        shapePath.lineTo(f11, (this.inside ? this.size : -this.size) * f10);
        shapePath.lineTo(f11 + (this.size * f10), BitmapDescriptorFactory.HUE_RED);
        shapePath.lineTo(f9, BitmapDescriptorFactory.HUE_RED);
    }
}
