package com.google.android.material.shape;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.material.internal.Experimental;

@Experimental("The shapes API is currently experimental and subject to change")
/* loaded from: classes2.dex */
public class CutCornerTreatment extends CornerTreatment {
    private final float size;

    public CutCornerTreatment(float f9) {
        this.size = f9;
    }

    @Override // com.google.android.material.shape.CornerTreatment
    public void getCornerPath(float f9, float f10, ShapePath shapePath) {
        shapePath.reset(BitmapDescriptorFactory.HUE_RED, this.size * f10);
        double d9 = f9;
        double d10 = f10;
        shapePath.lineTo((float) (Math.sin(d9) * this.size * d10), (float) (Math.cos(d9) * this.size * d10));
    }
}
