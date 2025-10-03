package com.google.android.material.shadow;

import android.graphics.drawable.Drawable;

/* loaded from: classes2.dex */
public interface ShadowViewDelegate {
    float getRadius();

    boolean isCompatPaddingEnabled();

    void setBackgroundDrawable(Drawable drawable);

    void setShadowPadding(int i9, int i10, int i11, int i12);
}
