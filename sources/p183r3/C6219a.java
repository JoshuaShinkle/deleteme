package p183r3;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.util.AttributeSet;
import android.widget.ImageView;

/* renamed from: r3.a */
/* loaded from: classes.dex */
public class C6219a extends ImageView {
    public C6219a(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    /* renamed from: a */
    public static void m23771a(Drawable drawable, boolean z8) {
        if (drawable instanceof LayerDrawable) {
            LayerDrawable layerDrawable = (LayerDrawable) drawable;
            int numberOfLayers = layerDrawable.getNumberOfLayers();
            for (int i9 = 0; i9 < numberOfLayers; i9++) {
                m23771a(layerDrawable.getDrawable(i9), z8);
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
        m23771a(drawable, true);
        m23771a(drawable2, false);
    }
}
