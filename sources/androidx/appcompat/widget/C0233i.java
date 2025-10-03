package androidx.appcompat.widget;

import android.R;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Shader;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.graphics.drawable.shapes.Shape;
import android.util.AttributeSet;
import android.widget.ProgressBar;
import p224w.InterfaceC6502f;

/* renamed from: androidx.appcompat.widget.i */
/* loaded from: classes.dex */
public class C0233i {

    /* renamed from: c */
    public static final int[] f1084c = {R.attr.indeterminateDrawable, R.attr.progressDrawable};

    /* renamed from: a */
    public final ProgressBar f1085a;

    /* renamed from: b */
    public Bitmap f1086b;

    public C0233i(ProgressBar progressBar) {
        this.f1085a = progressBar;
    }

    /* renamed from: a */
    public final Shape m883a() {
        return new RoundRectShape(new float[]{5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f}, null, null);
    }

    /* renamed from: b */
    public Bitmap m884b() {
        return this.f1086b;
    }

    /* renamed from: c */
    public void mo885c(AttributeSet attributeSet, int i9) {
        C0250q0 c0250q0M1004v = C0250q0.m1004v(this.f1085a.getContext(), attributeSet, f1084c, i9, 0);
        Drawable drawableM1012h = c0250q0M1004v.m1012h(0);
        if (drawableM1012h != null) {
            this.f1085a.setIndeterminateDrawable(m887e(drawableM1012h));
        }
        Drawable drawableM1012h2 = c0250q0M1004v.m1012h(1);
        if (drawableM1012h2 != null) {
            this.f1085a.setProgressDrawable(m886d(drawableM1012h2, false));
        }
        c0250q0M1004v.m1024w();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: d */
    public final Drawable m886d(Drawable drawable, boolean z8) {
        if (drawable instanceof InterfaceC6502f) {
            InterfaceC6502f interfaceC6502f = (InterfaceC6502f) drawable;
            Drawable drawableM24873b = interfaceC6502f.m24873b();
            if (drawableM24873b != null) {
                interfaceC6502f.m24872a(m886d(drawableM24873b, z8));
            }
        } else {
            if (drawable instanceof LayerDrawable) {
                LayerDrawable layerDrawable = (LayerDrawable) drawable;
                int numberOfLayers = layerDrawable.getNumberOfLayers();
                Drawable[] drawableArr = new Drawable[numberOfLayers];
                for (int i9 = 0; i9 < numberOfLayers; i9++) {
                    int id = layerDrawable.getId(i9);
                    drawableArr[i9] = m886d(layerDrawable.getDrawable(i9), id == 16908301 || id == 16908303);
                }
                LayerDrawable layerDrawable2 = new LayerDrawable(drawableArr);
                for (int i10 = 0; i10 < numberOfLayers; i10++) {
                    layerDrawable2.setId(i10, layerDrawable.getId(i10));
                }
                return layerDrawable2;
            }
            if (drawable instanceof BitmapDrawable) {
                BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
                Bitmap bitmap = bitmapDrawable.getBitmap();
                if (this.f1086b == null) {
                    this.f1086b = bitmap;
                }
                ShapeDrawable shapeDrawable = new ShapeDrawable(m883a());
                shapeDrawable.getPaint().setShader(new BitmapShader(bitmap, Shader.TileMode.REPEAT, Shader.TileMode.CLAMP));
                shapeDrawable.getPaint().setColorFilter(bitmapDrawable.getPaint().getColorFilter());
                return z8 ? new ClipDrawable(shapeDrawable, 3, 1) : shapeDrawable;
            }
        }
        return drawable;
    }

    /* renamed from: e */
    public final Drawable m887e(Drawable drawable) {
        if (!(drawable instanceof AnimationDrawable)) {
            return drawable;
        }
        AnimationDrawable animationDrawable = (AnimationDrawable) drawable;
        int numberOfFrames = animationDrawable.getNumberOfFrames();
        AnimationDrawable animationDrawable2 = new AnimationDrawable();
        animationDrawable2.setOneShot(animationDrawable.isOneShot());
        for (int i9 = 0; i9 < numberOfFrames; i9++) {
            Drawable drawableM886d = m886d(animationDrawable.getFrame(i9), true);
            drawableM886d.setLevel(10000);
            animationDrawable2.addFrame(drawableM886d, animationDrawable.getDuration(i9));
        }
        animationDrawable2.setLevel(10000);
        return animationDrawable2;
    }
}
