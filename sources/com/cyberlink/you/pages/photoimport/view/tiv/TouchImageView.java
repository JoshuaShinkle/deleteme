package com.cyberlink.you.pages.photoimport.view.tiv;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.ImageView;
import com.cyberlink.you.pages.photoimport.view.RecyclingImageView;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.lang.ref.WeakReference;
import java.util.Timer;
import java.util.TimerTask;
import p076g4.C4958b;

/* loaded from: classes.dex */
public class TouchImageView extends RecyclingImageView {

    /* renamed from: A */
    public long f14286A;

    /* renamed from: B */
    public long f14287B;

    /* renamed from: C */
    public boolean f14288C;

    /* renamed from: D */
    public Context f14289D;

    /* renamed from: E */
    public Timer f14290E;

    /* renamed from: F */
    public View.OnClickListener f14291F;

    /* renamed from: G */
    public Object f14292G;

    /* renamed from: H */
    public Handler f14293H;

    /* renamed from: I */
    public boolean f14294I;

    /* renamed from: J */
    public int f14295J;

    /* renamed from: K */
    public boolean f14296K;

    /* renamed from: L */
    public boolean f14297L;

    /* renamed from: M */
    public boolean f14298M;

    /* renamed from: N */
    public boolean f14299N;

    /* renamed from: O */
    public boolean f14300O;

    /* renamed from: b */
    public Matrix f14301b;

    /* renamed from: c */
    public Matrix f14302c;

    /* renamed from: d */
    public int f14303d;

    /* renamed from: e */
    public float f14304e;

    /* renamed from: f */
    public float f14305f;

    /* renamed from: g */
    public float f14306g;

    /* renamed from: h */
    public float f14307h;

    /* renamed from: i */
    public float f14308i;

    /* renamed from: j */
    public float f14309j;

    /* renamed from: k */
    public float f14310k;

    /* renamed from: l */
    public float f14311l;

    /* renamed from: m */
    public float f14312m;

    /* renamed from: n */
    public float f14313n;

    /* renamed from: o */
    public PointF f14314o;

    /* renamed from: p */
    public PointF f14315p;

    /* renamed from: q */
    public PointF f14316q;

    /* renamed from: r */
    public float[] f14317r;

    /* renamed from: s */
    public float f14318s;

    /* renamed from: t */
    public float f14319t;

    /* renamed from: u */
    public float f14320u;

    /* renamed from: v */
    public float f14321v;

    /* renamed from: w */
    public float f14322w;

    /* renamed from: x */
    public float f14323x;

    /* renamed from: y */
    public PointF f14324y;

    /* renamed from: z */
    public float f14325z;

    /* renamed from: com.cyberlink.you.pages.photoimport.view.tiv.TouchImageView$b */
    public class C3110b extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        public C3110b() {
        }

        /* JADX WARN: Removed duplicated region for block: B:31:0x00c9  */
        @Override // android.view.ScaleGestureDetector.SimpleOnScaleGestureListener, android.view.ScaleGestureDetector.OnScaleGestureListener
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
            float f9;
            float f10;
            float f11;
            float fMin = (float) Math.min(Math.max(0.95f, scaleGestureDetector.getScaleFactor()), 1.05d);
            TouchImageView touchImageView = TouchImageView.this;
            float f12 = touchImageView.f14320u;
            float f13 = f12 * fMin;
            touchImageView.f14320u = f13;
            float f14 = touchImageView.f14322w;
            if (f13 <= f14) {
                f14 = touchImageView.f14321v;
                if (f13 < f14) {
                    touchImageView.f14320u = f14;
                }
                f9 = touchImageView.f14312m;
                f10 = touchImageView.f14320u;
                touchImageView.f14306g = ((f9 * f10) - f9) - ((touchImageView.f14304e * 2.0f) * f10);
                f11 = touchImageView.f14313n;
                touchImageView.f14307h = ((f11 * f10) - f11) - ((touchImageView.f14305f * 2.0f) * f10);
                if (touchImageView.f14308i * f10 <= f9 && touchImageView.f14309j * f10 > f11) {
                    touchImageView.f14301b.postScale(fMin, fMin, scaleGestureDetector.getFocusX(), scaleGestureDetector.getFocusY());
                    TouchImageView touchImageView2 = TouchImageView.this;
                    touchImageView2.f14301b.getValues(touchImageView2.f14317r);
                    TouchImageView touchImageView3 = TouchImageView.this;
                    float[] fArr = touchImageView3.f14317r;
                    float f15 = fArr[2];
                    float f16 = fArr[5];
                    if (fMin < 1.0f) {
                        float f17 = touchImageView3.f14306g;
                        if (f15 < (-f17)) {
                            touchImageView3.f14301b.postTranslate(-(f15 + f17), BitmapDescriptorFactory.HUE_RED);
                        } else if (f15 > BitmapDescriptorFactory.HUE_RED) {
                            touchImageView3.f14301b.postTranslate(-f15, BitmapDescriptorFactory.HUE_RED);
                        }
                        TouchImageView touchImageView4 = TouchImageView.this;
                        float f18 = touchImageView4.f14307h;
                        if (f16 < (-f18)) {
                            touchImageView4.f14301b.postTranslate(BitmapDescriptorFactory.HUE_RED, -(f16 + f18));
                        } else if (f16 > BitmapDescriptorFactory.HUE_RED) {
                            touchImageView4.f14301b.postTranslate(BitmapDescriptorFactory.HUE_RED, -f16);
                        }
                    }
                    TouchImageView.this.m16265j();
                    return true;
                }
                touchImageView.f14301b.postScale(fMin, fMin, f9 / 2.0f, f11 / 2.0f);
                if (fMin < 1.0f) {
                    TouchImageView touchImageView5 = TouchImageView.this;
                    touchImageView5.f14301b.getValues(touchImageView5.f14317r);
                    TouchImageView touchImageView6 = TouchImageView.this;
                    float[] fArr2 = touchImageView6.f14317r;
                    float f19 = fArr2[2];
                    float f20 = fArr2[5];
                    if (fMin < 1.0f) {
                        float fRound = Math.round(touchImageView6.f14308i * touchImageView6.f14320u);
                        TouchImageView touchImageView7 = TouchImageView.this;
                        if (fRound < touchImageView7.f14312m) {
                            float f21 = touchImageView7.f14307h;
                            if (f20 < (-f21)) {
                                touchImageView7.f14301b.postTranslate(BitmapDescriptorFactory.HUE_RED, -(f20 + f21));
                            } else if (f20 > BitmapDescriptorFactory.HUE_RED) {
                                touchImageView7.f14301b.postTranslate(BitmapDescriptorFactory.HUE_RED, -f20);
                            }
                        } else {
                            float f22 = touchImageView7.f14306g;
                            if (f19 < (-f22)) {
                                touchImageView7.f14301b.postTranslate(-(f19 + f22), BitmapDescriptorFactory.HUE_RED);
                            } else if (f19 > BitmapDescriptorFactory.HUE_RED) {
                                touchImageView7.f14301b.postTranslate(-f19, BitmapDescriptorFactory.HUE_RED);
                            }
                        }
                    }
                }
                TouchImageView.this.m16265j();
                return true;
            }
            touchImageView.f14320u = f14;
            fMin = f14 / f12;
            f9 = touchImageView.f14312m;
            f10 = touchImageView.f14320u;
            touchImageView.f14306g = ((f9 * f10) - f9) - ((touchImageView.f14304e * 2.0f) * f10);
            f11 = touchImageView.f14313n;
            touchImageView.f14307h = ((f11 * f10) - f11) - ((touchImageView.f14305f * 2.0f) * f10);
            if (touchImageView.f14308i * f10 <= f9) {
            }
            touchImageView.f14301b.postScale(fMin, fMin, f9 / 2.0f, f11 / 2.0f);
            if (fMin < 1.0f) {
            }
            TouchImageView.this.m16265j();
            return true;
        }

        @Override // android.view.ScaleGestureDetector.SimpleOnScaleGestureListener, android.view.ScaleGestureDetector.OnScaleGestureListener
        public boolean onScaleBegin(ScaleGestureDetector scaleGestureDetector) {
            if (TouchImageView.this.f14296K) {
                TouchImageView.this.f14303d = 3;
                return true;
            }
            TouchImageView.this.f14303d = 2;
            return true;
        }
    }

    /* renamed from: com.cyberlink.you.pages.photoimport.view.tiv.TouchImageView$c */
    public class C3111c extends TimerTask {
        public C3111c() {
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            TouchImageView.this.f14293H.sendEmptyMessage(0);
        }
    }

    /* renamed from: com.cyberlink.you.pages.photoimport.view.tiv.TouchImageView$d */
    public static class HandlerC3112d extends Handler {

        /* renamed from: a */
        public final WeakReference<TouchImageView> f14328a;

        public HandlerC3112d(TouchImageView touchImageView) {
            this.f14328a = new WeakReference<>(touchImageView);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            this.f14328a.get().performClick();
            if (this.f14328a.get().f14291F != null) {
                this.f14328a.get().f14291F.onClick(this.f14328a.get());
            }
        }
    }

    public TouchImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f14301b = new Matrix();
        this.f14302c = new Matrix();
        this.f14303d = 0;
        this.f14314o = new PointF();
        this.f14315p = new PointF();
        this.f14316q = new PointF();
        this.f14320u = 1.0f;
        this.f14321v = 1.0f;
        this.f14322w = 3.0f;
        this.f14323x = 1.0f;
        this.f14324y = new PointF(BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED);
        this.f14325z = BitmapDescriptorFactory.HUE_RED;
        this.f14286A = 0L;
        this.f14287B = 0L;
        this.f14288C = false;
        this.f14293H = null;
        this.f14294I = false;
        this.f14295J = -1;
        this.f14296K = false;
        this.f14297L = false;
        this.f14298M = false;
        this.f14299N = false;
        this.f14300O = false;
        super.setClickable(true);
        this.f14289D = context;
        if (attributeSet != null) {
            this.f14296K = false;
        }
        m16268m();
    }

    /* renamed from: f */
    public final void m16261f() {
        float f9 = this.f14312m;
        float f10 = this.f14320u;
        this.f14306g = ((f9 * f10) - f9) - ((this.f14304e * 2.0f) * f10);
        float f11 = this.f14313n;
        this.f14307h = ((f11 * f10) - f11) - ((this.f14305f * 2.0f) * f10);
    }

    /* renamed from: g */
    public final float m16262g(float f9) {
        float f10 = this.f14318s;
        if (f10 + f9 > BitmapDescriptorFactory.HUE_RED) {
            return BitmapDescriptorFactory.HUE_RED - f10;
        }
        float f11 = f10 + f9;
        float f12 = this.f14306g;
        return f11 < (-f12) ? (-f12) - f10 : f9;
    }

    /* renamed from: h */
    public final float m16263h(float f9) {
        float f10 = this.f14319t;
        if (f10 + f9 > BitmapDescriptorFactory.HUE_RED) {
            return BitmapDescriptorFactory.HUE_RED - f10;
        }
        float f11 = f10 + f9;
        float f12 = this.f14307h;
        return f11 < (-f12) ? (-f12) - f10 : f9;
    }

    /* renamed from: i */
    public final void m16264i(float f9, float f10) {
        float fM16262g;
        float fM16263h;
        float fRound = Math.round(this.f14308i * this.f14320u);
        float fRound2 = Math.round(this.f14309j * this.f14320u);
        m16267l();
        if (fRound < this.f14312m) {
            fM16262g = ((-this.f14306g) / 2.0f) - this.f14318s;
            fM16263h = m16263h(f10);
        } else if (fRound2 < this.f14313n) {
            fM16263h = ((-this.f14307h) / 2.0f) - this.f14319t;
            fM16262g = m16262g(f9);
        } else {
            fM16262g = m16262g(f9);
            fM16263h = m16263h(f10);
        }
        this.f14301b.postTranslate(fM16262g, fM16263h);
        m16265j();
    }

    /* renamed from: j */
    public final void m16265j() {
        m16267l();
        float fRound = Math.round(this.f14308i * this.f14320u);
        float fRound2 = Math.round(this.f14309j * this.f14320u);
        this.f14300O = false;
        this.f14298M = false;
        this.f14299N = false;
        this.f14297L = false;
        float f9 = this.f14318s;
        if ((-f9) < 10.0f) {
            this.f14297L = true;
        }
        float f10 = this.f14312m;
        if ((fRound >= f10 && (f9 + fRound) - f10 < 10.0f) || (fRound <= f10 && (-f9) + fRound <= f10)) {
            this.f14299N = true;
        }
        float f11 = this.f14319t;
        if ((-f11) < 10.0f) {
            this.f14298M = true;
        }
        if (Math.abs(((-f11) + this.f14313n) - fRound2) < 10.0f) {
            this.f14300O = true;
        }
    }

    /* renamed from: k */
    public final double m16266k(PointF pointF, PointF pointF2) {
        return Math.sqrt(Math.pow(pointF.x - pointF2.x, 2.0d) + Math.pow(pointF.y - pointF2.y, 2.0d));
    }

    /* renamed from: l */
    public final void m16267l() {
        this.f14301b.getValues(this.f14317r);
        float[] fArr = this.f14317r;
        this.f14318s = fArr[2];
        this.f14319t = fArr[5];
    }

    /* renamed from: m */
    public void m16268m() {
        this.f14293H = new HandlerC3112d(this);
        this.f14301b.setTranslate(1.0f, 1.0f);
        this.f14317r = new float[9];
        setImageMatrix(this.f14301b);
        setScaleType(ImageView.ScaleType.MATRIX);
        this.f14292G = new ScaleGestureDetector(this.f14289D, new C3110b());
        m16265j();
    }

    /* renamed from: n */
    public boolean m16269n() {
        return this.f14320u > 1.0f;
    }

    /* renamed from: o */
    public final void m16270o(PointF pointF, C4958b c4958b) {
        pointF.set((c4958b.mo19227c(0) + c4958b.mo19227c(1)) / 2.0f, (c4958b.mo19228e(0) + c4958b.mo19228e(1)) / 2.0f);
    }

    @Override // android.widget.ImageView, android.view.View
    public void onDraw(Canvas canvas) {
        try {
            super.onDraw(canvas);
            if (this.f14288C) {
                PointF pointF = this.f14324y;
                float f9 = pointF.x;
                float f10 = this.f14325z;
                float f11 = f9 * f10;
                float f12 = pointF.y * f10;
                if (f11 <= this.f14312m && f12 <= this.f14313n) {
                    this.f14325z = f10 * 0.9f;
                    if (Math.abs(f11) >= 0.1d || Math.abs(f12) >= 0.1d) {
                        m16264i(f11, f12);
                        setImageMatrix(this.f14301b);
                    }
                }
            }
        } catch (Exception e9) {
            e9.printStackTrace();
        }
    }

    @Override // android.widget.ImageView, android.view.View
    public void onMeasure(int i9, int i10) {
        super.onMeasure(i9, i10);
        this.f14312m = View.MeasureSpec.getSize(i9);
        float size = View.MeasureSpec.getSize(i10);
        this.f14313n = size;
        float fMin = Math.min(this.f14312m / this.f14310k, size / this.f14311l);
        this.f14301b.setScale(fMin, fMin);
        setImageMatrix(this.f14301b);
        this.f14320u = 1.0f;
        float f9 = this.f14313n - (this.f14311l * fMin);
        float f10 = this.f14312m - (fMin * this.f14310k);
        float f11 = f9 / 2.0f;
        this.f14305f = f11;
        float f12 = f10 / 2.0f;
        this.f14304e = f12;
        this.f14301b.postTranslate(f12, f11);
        this.f14308i = this.f14312m - (this.f14304e * 2.0f);
        this.f14309j = this.f14313n - (this.f14305f * 2.0f);
        m16261f();
        setImageMatrix(this.f14301b);
    }

    /* JADX WARN: Removed duplicated region for block: B:65:0x012c  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x0139  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public boolean onTouchEvent(MotionEvent motionEvent) {
        float f9;
        float f10;
        C4958b c4958bM19229g = C4958b.m19229g(motionEvent);
        Object obj = this.f14292G;
        if (obj != null) {
            ((ScaleGestureDetector) obj).onTouchEvent(motionEvent);
        }
        m16267l();
        PointF pointF = new PointF(c4958bM19229g.m19231b(), c4958bM19229g.m19232d());
        int iM19230a = c4958bM19229g.m19230a() & 255;
        if (iM19230a == 0) {
            this.f14288C = false;
            this.f14302c.set(this.f14301b);
            this.f14314o.set(c4958bM19229g.m19231b(), c4958bM19229g.m19232d());
            this.f14316q.set(this.f14314o);
            if (!this.f14296K) {
                this.f14303d = 1;
            }
            this.f14295J = motionEvent.getPointerId(0);
        } else if (iM19230a == 1) {
            this.f14288C = true;
            this.f14303d = 0;
            int iAbs = (int) Math.abs(c4958bM19229g.m19231b() - this.f14316q.x);
            int iAbs2 = (int) Math.abs(c4958bM19229g.m19232d() - this.f14316q.y);
            if (iAbs < 10 && iAbs2 < 10) {
                long jCurrentTimeMillis = System.currentTimeMillis();
                if (jCurrentTimeMillis - this.f14286A <= 600) {
                    Timer timer = this.f14290E;
                    if (timer != null) {
                        timer.cancel();
                    }
                    float f11 = this.f14320u;
                    if (f11 == 1.0f) {
                        float f12 = this.f14322w / f11;
                        Matrix matrix = this.f14301b;
                        PointF pointF2 = this.f14316q;
                        matrix.postScale(f12, f12, pointF2.x, pointF2.y);
                        this.f14320u = this.f14322w;
                    } else {
                        Matrix matrix2 = this.f14301b;
                        float f13 = this.f14321v;
                        matrix2.postScale(f13 / f11, f13 / f11, this.f14312m / 2.0f, this.f14313n / 2.0f);
                        this.f14320u = this.f14321v;
                    }
                    m16261f();
                    m16264i(BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED);
                    this.f14286A = 0L;
                } else {
                    this.f14286A = jCurrentTimeMillis;
                    Timer timer2 = new Timer();
                    this.f14290E = timer2;
                    timer2.schedule(new C3111c(), 300L);
                }
                if (this.f14320u == this.f14321v) {
                    m16274s();
                }
            }
        } else if (iM19230a == 2) {
            this.f14288C = false;
            int i9 = this.f14303d;
            if (i9 == 1 || i9 == 3) {
                for (int i10 = 0; i10 < motionEvent.getPointerCount(); i10++) {
                    if (motionEvent.getPointerId(i10) == this.f14295J) {
                        pointF.x = motionEvent.getX(i10);
                        pointF.y = motionEvent.getY(i10);
                    }
                }
                float f14 = pointF.x;
                PointF pointF3 = this.f14314o;
                float f15 = f14 - pointF3.x;
                float f16 = pointF.y - pointF3.y;
                long jCurrentTimeMillis2 = System.currentTimeMillis();
                this.f14325z = (((float) m16266k(pointF, this.f14314o)) / (jCurrentTimeMillis2 - this.f14287B)) * 0.9f;
                this.f14287B = jCurrentTimeMillis2;
                m16264i(f15, f16);
                this.f14324y.set(f15, f16);
                this.f14314o.set(pointF.x, pointF.y);
            } else if (this.f14292G == null && (i9 == 2 || i9 == 3)) {
                float fM16275t = m16275t(c4958bM19229g);
                if (motionEvent.getPointerCount() >= 2 && 10.0f <= Math.abs(this.f14323x - fM16275t) && Math.abs(this.f14323x - fM16275t) <= 50.0f) {
                    float f17 = fM16275t / this.f14323x;
                    this.f14323x = fM16275t;
                    float f18 = this.f14320u;
                    float f19 = f18 * f17;
                    this.f14320u = f19;
                    float f20 = this.f14322w;
                    if (f19 > f20) {
                        this.f14320u = f20;
                    } else {
                        f20 = this.f14321v;
                        if (f19 < f20) {
                            this.f14320u = f20;
                        }
                        m16261f();
                        float f21 = this.f14308i;
                        float f22 = this.f14320u;
                        f9 = f21 * f22;
                        f10 = this.f14312m;
                        if (f9 > f10 || this.f14309j * f22 <= this.f14313n) {
                            this.f14301b.postScale(f17, f17, f10 / 2.0f, this.f14313n / 2.0f);
                            if (f17 < 1.0f) {
                                m16267l();
                                if (f17 < 1.0f) {
                                    m16274s();
                                }
                            }
                        } else {
                            PointF pointFM16271p = m16271p(c4958bM19229g);
                            this.f14301b.postScale(f17, f17, pointFM16271p.x, pointFM16271p.y);
                            m16267l();
                            if (f17 < 1.0f) {
                                float f23 = this.f14318s;
                                float f24 = this.f14306g;
                                if (f23 < (-f24)) {
                                    this.f14301b.postTranslate(-(f23 + f24), BitmapDescriptorFactory.HUE_RED);
                                } else if (f23 > BitmapDescriptorFactory.HUE_RED) {
                                    this.f14301b.postTranslate(-f23, BitmapDescriptorFactory.HUE_RED);
                                }
                                float f25 = this.f14319t;
                                float f26 = this.f14307h;
                                if (f25 < (-f26)) {
                                    this.f14301b.postTranslate(BitmapDescriptorFactory.HUE_RED, -(f25 + f26));
                                } else if (f25 > BitmapDescriptorFactory.HUE_RED) {
                                    this.f14301b.postTranslate(BitmapDescriptorFactory.HUE_RED, -f25);
                                }
                            }
                        }
                        m16265j();
                    }
                    f17 = f20 / f18;
                    m16261f();
                    float f212 = this.f14308i;
                    float f222 = this.f14320u;
                    f9 = f212 * f222;
                    f10 = this.f14312m;
                    if (f9 > f10) {
                        this.f14301b.postScale(f17, f17, f10 / 2.0f, this.f14313n / 2.0f);
                        if (f17 < 1.0f) {
                        }
                        m16265j();
                    }
                }
            }
        } else if (iM19230a == 5) {
            float fM16275t2 = m16275t(c4958bM19229g);
            this.f14323x = fM16275t2;
            if (fM16275t2 > 10.0f) {
                this.f14302c.set(this.f14301b);
                m16270o(this.f14315p, c4958bM19229g);
                if (this.f14296K) {
                    this.f14303d = 3;
                } else {
                    this.f14303d = 2;
                }
            }
        } else if (iM19230a == 6) {
            this.f14303d = 0;
            this.f14325z = BitmapDescriptorFactory.HUE_RED;
            this.f14302c.set(this.f14301b);
            this.f14323x = m16275t(c4958bM19229g);
        }
        setImageMatrix(this.f14301b);
        invalidate();
        return true;
    }

    /* renamed from: p */
    public final PointF m16271p(C4958b c4958b) {
        return new PointF((c4958b.mo19227c(0) + c4958b.mo19227c(1)) / 2.0f, (c4958b.mo19228e(0) + c4958b.mo19228e(1)) / 2.0f);
    }

    /* renamed from: q */
    public boolean m16272q() {
        return this.f14303d == 0 && this.f14320u == this.f14321v;
    }

    /* renamed from: r */
    public void m16273r() {
        m16267l();
        Matrix matrix = this.f14301b;
        float f9 = this.f14321v;
        float f10 = this.f14320u;
        matrix.postScale(f9 / f10, f9 / f10, this.f14312m / 2.0f, this.f14313n / 2.0f);
        this.f14320u = this.f14321v;
        m16261f();
        m16264i(BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED);
        m16274s();
        setImageMatrix(this.f14301b);
        invalidate();
    }

    /* renamed from: s */
    public final void m16274s() {
        if (Math.abs(this.f14318s + (this.f14306g / 2.0f)) > 0.5f) {
            this.f14301b.postTranslate(-(this.f14318s + (this.f14306g / 2.0f)), BitmapDescriptorFactory.HUE_RED);
        }
        if (Math.abs(this.f14319t + (this.f14307h / 2.0f)) > 0.5f) {
            this.f14301b.postTranslate(BitmapDescriptorFactory.HUE_RED, -(this.f14319t + (this.f14307h / 2.0f)));
        }
    }

    @Override // android.widget.ImageView
    public void setImageBitmap(Bitmap bitmap) {
        super.setImageBitmap(bitmap);
        if (bitmap != null) {
            this.f14310k = bitmap.getWidth();
            this.f14311l = bitmap.getHeight();
        }
    }

    @Override // com.cyberlink.you.pages.photoimport.view.RecyclingImageView, android.widget.ImageView
    public void setImageDrawable(Drawable drawable) {
        super.setImageDrawable(drawable);
        if (drawable != null) {
            this.f14310k = drawable.getMinimumWidth();
            this.f14311l = drawable.getMinimumHeight();
        }
    }

    @Override // android.view.View
    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.f14291F = onClickListener;
    }

    public void setZoomToOriginalSize(boolean z8) {
        this.f14294I = z8;
    }

    /* renamed from: t */
    public final float m16275t(C4958b c4958b) {
        float fMo19227c = c4958b.mo19227c(0) - c4958b.mo19227c(1);
        float fMo19228e = c4958b.mo19228e(0) - c4958b.mo19228e(1);
        return (float) Math.sqrt((fMo19227c * fMo19227c) + (fMo19228e * fMo19228e));
    }
}
