package p162p0;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import p021c0.C0702h;
import p092i0.C5039b;

/* renamed from: p0.b */
/* loaded from: classes.dex */
public class C5869b extends Drawable implements Animatable {

    /* renamed from: h */
    public static final Interpolator f20283h = new LinearInterpolator();

    /* renamed from: i */
    public static final Interpolator f20284i = new C5039b();

    /* renamed from: j */
    public static final int[] f20285j = {-16777216};

    /* renamed from: b */
    public final c f20286b;

    /* renamed from: c */
    public float f20287c;

    /* renamed from: d */
    public Resources f20288d;

    /* renamed from: e */
    public Animator f20289e;

    /* renamed from: f */
    public float f20290f;

    /* renamed from: g */
    public boolean f20291g;

    /* renamed from: p0.b$a */
    public class a implements ValueAnimator.AnimatorUpdateListener {

        /* renamed from: a */
        public final /* synthetic */ c f20292a;

        public a(c cVar) {
            this.f20292a = cVar;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            float fFloatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
            C5869b.this.m23285n(fFloatValue, this.f20292a);
            C5869b.this.m23273b(fFloatValue, this.f20292a, false);
            C5869b.this.invalidateSelf();
        }
    }

    /* renamed from: p0.b$b */
    public class b implements Animator.AnimatorListener {

        /* renamed from: a */
        public final /* synthetic */ c f20294a;

        public b(c cVar) {
            this.f20294a = cVar;
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationRepeat(Animator animator) {
            C5869b.this.m23273b(1.0f, this.f20294a, true);
            this.f20294a.m23286A();
            this.f20294a.m23298l();
            C5869b c5869b = C5869b.this;
            if (!c5869b.f20291g) {
                c5869b.f20290f += 1.0f;
                return;
            }
            c5869b.f20291g = false;
            animator.cancel();
            animator.setDuration(1332L);
            animator.start();
            this.f20294a.m23310x(false);
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            C5869b.this.f20290f = BitmapDescriptorFactory.HUE_RED;
        }
    }

    /* renamed from: p0.b$c */
    public static class c {

        /* renamed from: a */
        public final RectF f20296a = new RectF();

        /* renamed from: b */
        public final Paint f20297b;

        /* renamed from: c */
        public final Paint f20298c;

        /* renamed from: d */
        public final Paint f20299d;

        /* renamed from: e */
        public float f20300e;

        /* renamed from: f */
        public float f20301f;

        /* renamed from: g */
        public float f20302g;

        /* renamed from: h */
        public float f20303h;

        /* renamed from: i */
        public int[] f20304i;

        /* renamed from: j */
        public int f20305j;

        /* renamed from: k */
        public float f20306k;

        /* renamed from: l */
        public float f20307l;

        /* renamed from: m */
        public float f20308m;

        /* renamed from: n */
        public boolean f20309n;

        /* renamed from: o */
        public Path f20310o;

        /* renamed from: p */
        public float f20311p;

        /* renamed from: q */
        public float f20312q;

        /* renamed from: r */
        public int f20313r;

        /* renamed from: s */
        public int f20314s;

        /* renamed from: t */
        public int f20315t;

        /* renamed from: u */
        public int f20316u;

        public c() {
            Paint paint = new Paint();
            this.f20297b = paint;
            Paint paint2 = new Paint();
            this.f20298c = paint2;
            Paint paint3 = new Paint();
            this.f20299d = paint3;
            this.f20300e = BitmapDescriptorFactory.HUE_RED;
            this.f20301f = BitmapDescriptorFactory.HUE_RED;
            this.f20302g = BitmapDescriptorFactory.HUE_RED;
            this.f20303h = 5.0f;
            this.f20311p = 1.0f;
            this.f20315t = 255;
            paint.setStrokeCap(Paint.Cap.SQUARE);
            paint.setAntiAlias(true);
            paint.setStyle(Paint.Style.STROKE);
            paint2.setStyle(Paint.Style.FILL);
            paint2.setAntiAlias(true);
            paint3.setColor(0);
        }

        /* renamed from: A */
        public void m23286A() {
            this.f20306k = this.f20300e;
            this.f20307l = this.f20301f;
            this.f20308m = this.f20302g;
        }

        /* renamed from: a */
        public void m23287a(Canvas canvas, Rect rect) {
            RectF rectF = this.f20296a;
            float f9 = this.f20312q;
            float fMin = (this.f20303h / 2.0f) + f9;
            if (f9 <= BitmapDescriptorFactory.HUE_RED) {
                fMin = (Math.min(rect.width(), rect.height()) / 2.0f) - Math.max((this.f20313r * this.f20311p) / 2.0f, this.f20303h / 2.0f);
            }
            rectF.set(rect.centerX() - fMin, rect.centerY() - fMin, rect.centerX() + fMin, rect.centerY() + fMin);
            float f10 = this.f20300e;
            float f11 = this.f20302g;
            float f12 = (f10 + f11) * 360.0f;
            float f13 = ((this.f20301f + f11) * 360.0f) - f12;
            this.f20297b.setColor(this.f20316u);
            this.f20297b.setAlpha(this.f20315t);
            float f14 = this.f20303h / 2.0f;
            rectF.inset(f14, f14);
            canvas.drawCircle(rectF.centerX(), rectF.centerY(), rectF.width() / 2.0f, this.f20299d);
            float f15 = -f14;
            rectF.inset(f15, f15);
            canvas.drawArc(rectF, f12, f13, false, this.f20297b);
            m23288b(canvas, f12, f13, rectF);
        }

        /* renamed from: b */
        public void m23288b(Canvas canvas, float f9, float f10, RectF rectF) {
            if (this.f20309n) {
                Path path = this.f20310o;
                if (path == null) {
                    Path path2 = new Path();
                    this.f20310o = path2;
                    path2.setFillType(Path.FillType.EVEN_ODD);
                } else {
                    path.reset();
                }
                float fMin = Math.min(rectF.width(), rectF.height()) / 2.0f;
                float f11 = (this.f20313r * this.f20311p) / 2.0f;
                this.f20310o.moveTo(BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED);
                this.f20310o.lineTo(this.f20313r * this.f20311p, BitmapDescriptorFactory.HUE_RED);
                Path path3 = this.f20310o;
                float f12 = this.f20313r;
                float f13 = this.f20311p;
                path3.lineTo((f12 * f13) / 2.0f, this.f20314s * f13);
                this.f20310o.offset((fMin + rectF.centerX()) - f11, rectF.centerY() + (this.f20303h / 2.0f));
                this.f20310o.close();
                this.f20298c.setColor(this.f20316u);
                this.f20298c.setAlpha(this.f20315t);
                canvas.save();
                canvas.rotate(f9 + f10, rectF.centerX(), rectF.centerY());
                canvas.drawPath(this.f20310o, this.f20298c);
                canvas.restore();
            }
        }

        /* renamed from: c */
        public int m23289c() {
            return this.f20315t;
        }

        /* renamed from: d */
        public float m23290d() {
            return this.f20301f;
        }

        /* renamed from: e */
        public int m23291e() {
            return this.f20304i[m23292f()];
        }

        /* renamed from: f */
        public int m23292f() {
            return (this.f20305j + 1) % this.f20304i.length;
        }

        /* renamed from: g */
        public float m23293g() {
            return this.f20300e;
        }

        /* renamed from: h */
        public int m23294h() {
            return this.f20304i[this.f20305j];
        }

        /* renamed from: i */
        public float m23295i() {
            return this.f20307l;
        }

        /* renamed from: j */
        public float m23296j() {
            return this.f20308m;
        }

        /* renamed from: k */
        public float m23297k() {
            return this.f20306k;
        }

        /* renamed from: l */
        public void m23298l() {
            m23306t(m23292f());
        }

        /* renamed from: m */
        public void m23299m() {
            this.f20306k = BitmapDescriptorFactory.HUE_RED;
            this.f20307l = BitmapDescriptorFactory.HUE_RED;
            this.f20308m = BitmapDescriptorFactory.HUE_RED;
            m23311y(BitmapDescriptorFactory.HUE_RED);
            m23308v(BitmapDescriptorFactory.HUE_RED);
            m23309w(BitmapDescriptorFactory.HUE_RED);
        }

        /* renamed from: n */
        public void m23300n(int i9) {
            this.f20315t = i9;
        }

        /* renamed from: o */
        public void m23301o(float f9, float f10) {
            this.f20313r = (int) f9;
            this.f20314s = (int) f10;
        }

        /* renamed from: p */
        public void m23302p(float f9) {
            if (f9 != this.f20311p) {
                this.f20311p = f9;
            }
        }

        /* renamed from: q */
        public void m23303q(float f9) {
            this.f20312q = f9;
        }

        /* renamed from: r */
        public void m23304r(int i9) {
            this.f20316u = i9;
        }

        /* renamed from: s */
        public void m23305s(ColorFilter colorFilter) {
            this.f20297b.setColorFilter(colorFilter);
        }

        /* renamed from: t */
        public void m23306t(int i9) {
            this.f20305j = i9;
            this.f20316u = this.f20304i[i9];
        }

        /* renamed from: u */
        public void m23307u(int[] iArr) {
            this.f20304i = iArr;
            m23306t(0);
        }

        /* renamed from: v */
        public void m23308v(float f9) {
            this.f20301f = f9;
        }

        /* renamed from: w */
        public void m23309w(float f9) {
            this.f20302g = f9;
        }

        /* renamed from: x */
        public void m23310x(boolean z8) {
            if (this.f20309n != z8) {
                this.f20309n = z8;
            }
        }

        /* renamed from: y */
        public void m23311y(float f9) {
            this.f20300e = f9;
        }

        /* renamed from: z */
        public void m23312z(float f9) {
            this.f20303h = f9;
            this.f20297b.setStrokeWidth(f9);
        }
    }

    public C5869b(Context context) {
        this.f20288d = ((Context) C0702h.m3468b(context)).getResources();
        c cVar = new c();
        this.f20286b = cVar;
        cVar.m23307u(f20285j);
        m23282k(2.5f);
        m23284m();
    }

    /* renamed from: a */
    public final void m23272a(float f9, c cVar) {
        m23285n(f9, cVar);
        float fFloor = (float) (Math.floor(cVar.m23296j() / 0.8f) + 1.0d);
        cVar.m23311y(cVar.m23297k() + (((cVar.m23295i() - 0.01f) - cVar.m23297k()) * f9));
        cVar.m23308v(cVar.m23295i());
        cVar.m23309w(cVar.m23296j() + ((fFloor - cVar.m23296j()) * f9));
    }

    /* renamed from: b */
    public void m23273b(float f9, c cVar, boolean z8) {
        float interpolation;
        float interpolation2;
        if (this.f20291g) {
            m23272a(f9, cVar);
            return;
        }
        if (f9 != 1.0f || z8) {
            float fM23296j = cVar.m23296j();
            if (f9 < 0.5f) {
                interpolation = cVar.m23297k();
                interpolation2 = (f20284i.getInterpolation(f9 / 0.5f) * 0.79f) + 0.01f + interpolation;
            } else {
                float fM23297k = cVar.m23297k() + 0.79f;
                interpolation = fM23297k - (((1.0f - f20284i.getInterpolation((f9 - 0.5f) / 0.5f)) * 0.79f) + 0.01f);
                interpolation2 = fM23297k;
            }
            float f10 = fM23296j + (0.20999998f * f9);
            float f11 = (f9 + this.f20290f) * 216.0f;
            cVar.m23311y(interpolation);
            cVar.m23308v(interpolation2);
            cVar.m23309w(f10);
            m23279h(f11);
        }
    }

    /* renamed from: c */
    public final int m23274c(float f9, int i9, int i10) {
        return ((((i9 >> 24) & 255) + ((int) ((((i10 >> 24) & 255) - r0) * f9))) << 24) | ((((i9 >> 16) & 255) + ((int) ((((i10 >> 16) & 255) - r1) * f9))) << 16) | ((((i9 >> 8) & 255) + ((int) ((((i10 >> 8) & 255) - r2) * f9))) << 8) | ((i9 & 255) + ((int) (f9 * ((i10 & 255) - r8))));
    }

    /* renamed from: d */
    public void m23275d(boolean z8) {
        this.f20286b.m23310x(z8);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        Rect bounds = getBounds();
        canvas.save();
        canvas.rotate(this.f20287c, bounds.exactCenterX(), bounds.exactCenterY());
        this.f20286b.m23287a(canvas, bounds);
        canvas.restore();
    }

    /* renamed from: e */
    public void m23276e(float f9) {
        this.f20286b.m23302p(f9);
        invalidateSelf();
    }

    /* renamed from: f */
    public void m23277f(int... iArr) {
        this.f20286b.m23307u(iArr);
        this.f20286b.m23306t(0);
        invalidateSelf();
    }

    /* renamed from: g */
    public void m23278g(float f9) {
        this.f20286b.m23309w(f9);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public int getAlpha() {
        return this.f20286b.m23289c();
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    /* renamed from: h */
    public final void m23279h(float f9) {
        this.f20287c = f9;
    }

    /* renamed from: i */
    public final void m23280i(float f9, float f10, float f11, float f12) {
        c cVar = this.f20286b;
        float f13 = this.f20288d.getDisplayMetrics().density;
        cVar.m23312z(f10 * f13);
        cVar.m23303q(f9 * f13);
        cVar.m23306t(0);
        cVar.m23301o(f11 * f13, f12 * f13);
    }

    @Override // android.graphics.drawable.Animatable
    public boolean isRunning() {
        return this.f20289e.isRunning();
    }

    /* renamed from: j */
    public void m23281j(float f9, float f10) {
        this.f20286b.m23311y(f9);
        this.f20286b.m23308v(f10);
        invalidateSelf();
    }

    /* renamed from: k */
    public void m23282k(float f9) {
        this.f20286b.m23312z(f9);
        invalidateSelf();
    }

    /* renamed from: l */
    public void m23283l(int i9) {
        if (i9 == 0) {
            m23280i(11.0f, 3.0f, 12.0f, 6.0f);
        } else {
            m23280i(7.5f, 2.5f, 10.0f, 5.0f);
        }
        invalidateSelf();
    }

    /* renamed from: m */
    public final void m23284m() {
        c cVar = this.f20286b;
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(BitmapDescriptorFactory.HUE_RED, 1.0f);
        valueAnimatorOfFloat.addUpdateListener(new a(cVar));
        valueAnimatorOfFloat.setRepeatCount(-1);
        valueAnimatorOfFloat.setRepeatMode(1);
        valueAnimatorOfFloat.setInterpolator(f20283h);
        valueAnimatorOfFloat.addListener(new b(cVar));
        this.f20289e = valueAnimatorOfFloat;
    }

    /* renamed from: n */
    public void m23285n(float f9, c cVar) {
        if (f9 > 0.75f) {
            cVar.m23304r(m23274c((f9 - 0.75f) / 0.25f, cVar.m23294h(), cVar.m23291e()));
        } else {
            cVar.m23304r(cVar.m23294h());
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i9) {
        this.f20286b.m23300n(i9);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.f20286b.m23305s(colorFilter);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Animatable
    public void start() {
        this.f20289e.cancel();
        this.f20286b.m23286A();
        if (this.f20286b.m23290d() != this.f20286b.m23293g()) {
            this.f20291g = true;
            this.f20289e.setDuration(666L);
            this.f20289e.start();
        } else {
            this.f20286b.m23306t(0);
            this.f20286b.m23299m();
            this.f20289e.setDuration(1332L);
            this.f20289e.start();
        }
    }

    @Override // android.graphics.drawable.Animatable
    public void stop() {
        this.f20289e.cancel();
        m23279h(BitmapDescriptorFactory.HUE_RED);
        this.f20286b.m23310x(false);
        this.f20286b.m23306t(0);
        this.f20286b.m23299m();
        invalidateSelf();
    }
}
