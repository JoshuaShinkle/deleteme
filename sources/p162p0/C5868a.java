package p162p0;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.view.animation.Animation;
import android.widget.ImageView;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import p042d0.C4647u;

/* renamed from: p0.a */
/* loaded from: classes.dex */
public class C5868a extends ImageView {

    /* renamed from: b */
    public Animation.AnimationListener f20278b;

    /* renamed from: c */
    public int f20279c;

    /* renamed from: p0.a$a */
    public class a extends OvalShape {

        /* renamed from: b */
        public RadialGradient f20280b;

        /* renamed from: c */
        public Paint f20281c = new Paint();

        public a(int i9) {
            C5868a.this.f20279c = i9;
            m23271a((int) rect().width());
        }

        /* renamed from: a */
        public final void m23271a(int i9) {
            float f9 = i9 / 2;
            RadialGradient radialGradient = new RadialGradient(f9, f9, C5868a.this.f20279c, new int[]{1023410176, 0}, (float[]) null, Shader.TileMode.CLAMP);
            this.f20280b = radialGradient;
            this.f20281c.setShader(radialGradient);
        }

        @Override // android.graphics.drawable.shapes.OvalShape, android.graphics.drawable.shapes.RectShape, android.graphics.drawable.shapes.Shape
        public void draw(Canvas canvas, Paint paint) {
            float width = C5868a.this.getWidth() / 2;
            float height = C5868a.this.getHeight() / 2;
            canvas.drawCircle(width, height, width, this.f20281c);
            canvas.drawCircle(width, height, r0 - C5868a.this.f20279c, paint);
        }

        @Override // android.graphics.drawable.shapes.RectShape, android.graphics.drawable.shapes.Shape
        public void onResize(float f9, float f10) {
            super.onResize(f9, f10);
            m23271a((int) f9);
        }
    }

    public C5868a(Context context, int i9) {
        ShapeDrawable shapeDrawable;
        super(context);
        float f9 = getContext().getResources().getDisplayMetrics().density;
        int i10 = (int) (1.75f * f9);
        int i11 = (int) (BitmapDescriptorFactory.HUE_RED * f9);
        this.f20279c = (int) (3.5f * f9);
        if (m23269a()) {
            shapeDrawable = new ShapeDrawable(new OvalShape());
            C4647u.m18542f0(this, f9 * 4.0f);
        } else {
            ShapeDrawable shapeDrawable2 = new ShapeDrawable(new a(this.f20279c));
            setLayerType(1, shapeDrawable2.getPaint());
            shapeDrawable2.getPaint().setShadowLayer(this.f20279c, i11, i10, 503316480);
            int i12 = this.f20279c;
            setPadding(i12, i12, i12, i12);
            shapeDrawable = shapeDrawable2;
        }
        shapeDrawable.getPaint().setColor(i9);
        C4647u.m18534b0(this, shapeDrawable);
    }

    /* renamed from: a */
    public final boolean m23269a() {
        return true;
    }

    /* renamed from: b */
    public void m23270b(Animation.AnimationListener animationListener) {
        this.f20278b = animationListener;
    }

    @Override // android.view.View
    public void onAnimationEnd() {
        super.onAnimationEnd();
        Animation.AnimationListener animationListener = this.f20278b;
        if (animationListener != null) {
            animationListener.onAnimationEnd(getAnimation());
        }
    }

    @Override // android.view.View
    public void onAnimationStart() {
        super.onAnimationStart();
        Animation.AnimationListener animationListener = this.f20278b;
        if (animationListener != null) {
            animationListener.onAnimationStart(getAnimation());
        }
    }

    @Override // android.widget.ImageView, android.view.View
    public void onMeasure(int i9, int i10) {
        super.onMeasure(i9, i10);
        if (m23269a()) {
            return;
        }
        setMeasuredDimension(getMeasuredWidth() + (this.f20279c * 2), getMeasuredHeight() + (this.f20279c * 2));
    }

    @Override // android.view.View
    public void setBackgroundColor(int i9) {
        if (getBackground() instanceof ShapeDrawable) {
            ((ShapeDrawable) getBackground()).getPaint().setColor(i9);
        }
    }
}
