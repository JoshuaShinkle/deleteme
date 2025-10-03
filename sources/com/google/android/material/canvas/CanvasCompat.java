package com.google.android.material.canvas;

import android.graphics.Canvas;
import android.graphics.RectF;

/* loaded from: classes2.dex */
public class CanvasCompat {
    private CanvasCompat() {
    }

    public static int saveLayerAlpha(Canvas canvas, RectF rectF, int i9) {
        return canvas.saveLayerAlpha(rectF, i9);
    }

    public static int saveLayerAlpha(Canvas canvas, float f9, float f10, float f11, float f12, int i9) {
        return canvas.saveLayerAlpha(f9, f10, f11, f12, i9);
    }
}
