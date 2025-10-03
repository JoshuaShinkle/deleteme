package com.google.android.material.shape;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.material.internal.Experimental;

@Experimental("The shapes API is currently experimental and subject to change")
/* loaded from: classes2.dex */
public class RoundedCornerTreatment extends CornerTreatment {
    private final float radius;

    public RoundedCornerTreatment(float f9) {
        this.radius = f9;
    }

    @Override // com.google.android.material.shape.CornerTreatment
    public void getCornerPath(float f9, float f10, ShapePath shapePath) {
        shapePath.reset(BitmapDescriptorFactory.HUE_RED, this.radius * f10);
        float f11 = this.radius;
        shapePath.addArc(BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, f11 * 2.0f * f10, f11 * 2.0f * f10, f9 + 180.0f, 90.0f);
    }
}
