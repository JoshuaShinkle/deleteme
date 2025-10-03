package com.google.android.gms.maps.model;

import com.google.android.gms.common.internal.Preconditions;

/* loaded from: classes2.dex */
public final class CustomCap extends Cap {
    public final BitmapDescriptor bitmapDescriptor;
    public final float refWidth;

    /* JADX WARN: Illegal instructions before constructor call */
    public CustomCap(BitmapDescriptor bitmapDescriptor, float f9) {
        BitmapDescriptor bitmapDescriptor2 = (BitmapDescriptor) Preconditions.checkNotNull(bitmapDescriptor, "bitmapDescriptor must not be null");
        if (f9 <= BitmapDescriptorFactory.HUE_RED) {
            throw new IllegalArgumentException("refWidth must be positive");
        }
        super(bitmapDescriptor2, f9);
        this.bitmapDescriptor = bitmapDescriptor;
        this.refWidth = f9;
    }

    @Override // com.google.android.gms.maps.model.Cap
    public final String toString() {
        String strValueOf = String.valueOf(this.bitmapDescriptor);
        float f9 = this.refWidth;
        StringBuilder sb = new StringBuilder(strValueOf.length() + 55);
        sb.append("[CustomCap: bitmapDescriptor=");
        sb.append(strValueOf);
        sb.append(" refWidth=");
        sb.append(f9);
        sb.append("]");
        return sb.toString();
    }

    public CustomCap(BitmapDescriptor bitmapDescriptor) {
        this(bitmapDescriptor, 10.0f);
    }
}
