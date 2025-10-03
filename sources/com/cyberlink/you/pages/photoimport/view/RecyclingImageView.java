package com.cyberlink.you.pages.photoimport.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.util.AttributeSet;
import android.widget.ImageView;
import p066f4.C4787a;

/* loaded from: classes.dex */
public class RecyclingImageView extends ImageView {
    public RecyclingImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    /* renamed from: a */
    public static void m16253a(Drawable drawable, boolean z8) {
        if (drawable instanceof C4787a) {
            ((C4787a) drawable).m19016c(z8);
            return;
        }
        if (drawable instanceof LayerDrawable) {
            LayerDrawable layerDrawable = (LayerDrawable) drawable;
            int numberOfLayers = layerDrawable.getNumberOfLayers();
            for (int i9 = 0; i9 < numberOfLayers; i9++) {
                m16253a(layerDrawable.getDrawable(i9), z8);
            }
        }
    }

    @Override // android.widget.ImageView, android.view.View
    public void onDetachedFromWindow() {
        setImageDrawable(null);
        super.onDetachedFromWindow();
    }

    @Override // android.widget.ImageView
    public void setImageDrawable(Drawable drawable) {
        Drawable drawable2 = getDrawable();
        super.setImageDrawable(drawable);
        m16253a(drawable, true);
        m16253a(drawable2, false);
    }
}
