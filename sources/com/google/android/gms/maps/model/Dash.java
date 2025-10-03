package com.google.android.gms.maps.model;

/* loaded from: classes2.dex */
public final class Dash extends PatternItem {
    public final float length;

    public Dash(float f9) {
        super(0, Float.valueOf(Math.max(f9, BitmapDescriptorFactory.HUE_RED)));
        this.length = Math.max(f9, BitmapDescriptorFactory.HUE_RED);
    }

    @Override // com.google.android.gms.maps.model.PatternItem
    public final String toString() {
        float f9 = this.length;
        StringBuilder sb = new StringBuilder(30);
        sb.append("[Dash: length=");
        sb.append(f9);
        sb.append("]");
        return sb.toString();
    }
}
