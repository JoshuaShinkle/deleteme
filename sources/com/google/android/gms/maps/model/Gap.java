package com.google.android.gms.maps.model;

/* loaded from: classes2.dex */
public final class Gap extends PatternItem {
    public final float length;

    public Gap(float f9) {
        super(2, Float.valueOf(Math.max(f9, BitmapDescriptorFactory.HUE_RED)));
        this.length = Math.max(f9, BitmapDescriptorFactory.HUE_RED);
    }

    @Override // com.google.android.gms.maps.model.PatternItem
    public final String toString() {
        float f9 = this.length;
        StringBuilder sb = new StringBuilder(29);
        sb.append("[Gap: length=");
        sb.append(f9);
        sb.append("]");
        return sb.toString();
    }
}
