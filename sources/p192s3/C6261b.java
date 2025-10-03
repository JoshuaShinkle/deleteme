package p192s3;

import android.content.Context;
import android.content.res.TypedArray;
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
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.lang.ref.WeakReference;
import java.util.Timer;
import java.util.TimerTask;
import p183r3.C6219a;
import p218v2.C6453a0;

/* renamed from: s3.b */
/* loaded from: classes.dex */
public class C6261b extends C6219a {

    /* renamed from: A */
    public long f21104A;

    /* renamed from: B */
    public long f21105B;

    /* renamed from: C */
    public boolean f21106C;

    /* renamed from: D */
    public Context f21107D;

    /* renamed from: E */
    public Timer f21108E;

    /* renamed from: F */
    public View.OnClickListener f21109F;

    /* renamed from: G */
    public Object f21110G;

    /* renamed from: H */
    public Handler f21111H;

    /* renamed from: I */
    public boolean f21112I;

    /* renamed from: J */
    public int f21113J;

    /* renamed from: K */
    public boolean f21114K;

    /* renamed from: L */
    public boolean f21115L;

    /* renamed from: M */
    public boolean f21116M;

    /* renamed from: N */
    public boolean f21117N;

    /* renamed from: O */
    public boolean f21118O;

    /* renamed from: b */
    public Matrix f21119b;

    /* renamed from: c */
    public Matrix f21120c;

    /* renamed from: d */
    public int f21121d;

    /* renamed from: e */
    public float f21122e;

    /* renamed from: f */
    public float f21123f;

    /* renamed from: g */
    public float f21124g;

    /* renamed from: h */
    public float f21125h;

    /* renamed from: i */
    public float f21126i;

    /* renamed from: j */
    public float f21127j;

    /* renamed from: k */
    public float f21128k;

    /* renamed from: l */
    public float f21129l;

    /* renamed from: m */
    public float f21130m;

    /* renamed from: n */
    public float f21131n;

    /* renamed from: o */
    public PointF f21132o;

    /* renamed from: p */
    public PointF f21133p;

    /* renamed from: q */
    public PointF f21134q;

    /* renamed from: r */
    public float[] f21135r;

    /* renamed from: s */
    public float f21136s;

    /* renamed from: t */
    public float f21137t;

    /* renamed from: u */
    public float f21138u;

    /* renamed from: v */
    public float f21139v;

    /* renamed from: w */
    public float f21140w;

    /* renamed from: x */
    public float f21141x;

    /* renamed from: y */
    public PointF f21142y;

    /* renamed from: z */
    public float f21143z;

    /* renamed from: s3.b$b */
    public class b extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        public b() {
        }

        /* JADX WARN: Removed duplicated region for block: B:30:0x00c5  */
        /* JADX WARN: Removed duplicated region for block: B:52:? A[RETURN, SYNTHETIC] */
        @Override // android.view.ScaleGestureDetector.SimpleOnScaleGestureListener, android.view.ScaleGestureDetector.OnScaleGestureListener
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
            float f9;
            float f10;
            float f11;
            float fMin = (float) Math.min(Math.max(0.95f, scaleGestureDetector.getScaleFactor()), 1.05d);
            C6261b c6261b = C6261b.this;
            float f12 = c6261b.f21138u;
            float f13 = f12 * fMin;
            c6261b.f21138u = f13;
            float f14 = c6261b.f21140w;
            if (f13 <= f14) {
                f14 = c6261b.f21139v;
                if (f13 < f14) {
                    c6261b.f21138u = f14;
                }
                f9 = c6261b.f21130m;
                f10 = c6261b.f21138u;
                c6261b.f21124g = ((f9 * f10) - f9) - ((c6261b.f21122e * 2.0f) * f10);
                f11 = c6261b.f21131n;
                c6261b.f21125h = ((f11 * f10) - f11) - ((c6261b.f21123f * 2.0f) * f10);
                if (c6261b.f21126i * f10 <= f9 && c6261b.f21127j * f10 > f11) {
                    c6261b.f21119b.postScale(fMin, fMin, scaleGestureDetector.getFocusX(), scaleGestureDetector.getFocusY());
                    C6261b c6261b2 = C6261b.this;
                    c6261b2.f21119b.getValues(c6261b2.f21135r);
                    C6261b c6261b3 = C6261b.this;
                    float[] fArr = c6261b3.f21135r;
                    float f15 = fArr[2];
                    float f16 = fArr[5];
                    if (fMin >= 1.0f) {
                        return true;
                    }
                    float f17 = c6261b3.f21124g;
                    if (f15 < (-f17)) {
                        c6261b3.f21119b.postTranslate(-(f15 + f17), BitmapDescriptorFactory.HUE_RED);
                    } else if (f15 > BitmapDescriptorFactory.HUE_RED) {
                        c6261b3.f21119b.postTranslate(-f15, BitmapDescriptorFactory.HUE_RED);
                    }
                    C6261b c6261b4 = C6261b.this;
                    float f18 = c6261b4.f21125h;
                    if (f16 < (-f18)) {
                        c6261b4.f21119b.postTranslate(BitmapDescriptorFactory.HUE_RED, -(f16 + f18));
                        return true;
                    }
                    if (f16 <= BitmapDescriptorFactory.HUE_RED) {
                        return true;
                    }
                    c6261b4.f21119b.postTranslate(BitmapDescriptorFactory.HUE_RED, -f16);
                    return true;
                }
                c6261b.f21119b.postScale(fMin, fMin, f9 / 2.0f, f11 / 2.0f);
                if (fMin < 1.0f) {
                    return true;
                }
                C6261b c6261b5 = C6261b.this;
                c6261b5.f21119b.getValues(c6261b5.f21135r);
                C6261b c6261b6 = C6261b.this;
                float[] fArr2 = c6261b6.f21135r;
                float f19 = fArr2[2];
                float f20 = fArr2[5];
                if (fMin >= 1.0f) {
                    return true;
                }
                float fRound = Math.round(c6261b6.f21126i * c6261b6.f21138u);
                C6261b c6261b7 = C6261b.this;
                if (fRound < c6261b7.f21130m) {
                    float f21 = c6261b7.f21125h;
                    if (f20 < (-f21)) {
                        c6261b7.f21119b.postTranslate(BitmapDescriptorFactory.HUE_RED, -(f20 + f21));
                        return true;
                    }
                    if (f20 <= BitmapDescriptorFactory.HUE_RED) {
                        return true;
                    }
                    c6261b7.f21119b.postTranslate(BitmapDescriptorFactory.HUE_RED, -f20);
                    return true;
                }
                float f22 = c6261b7.f21124g;
                if (f19 < (-f22)) {
                    c6261b7.f21119b.postTranslate(-(f19 + f22), BitmapDescriptorFactory.HUE_RED);
                    return true;
                }
                if (f19 <= BitmapDescriptorFactory.HUE_RED) {
                    return true;
                }
                c6261b7.f21119b.postTranslate(-f19, BitmapDescriptorFactory.HUE_RED);
                return true;
            }
            c6261b.f21138u = f14;
            fMin = f14 / f12;
            f9 = c6261b.f21130m;
            f10 = c6261b.f21138u;
            c6261b.f21124g = ((f9 * f10) - f9) - ((c6261b.f21122e * 2.0f) * f10);
            f11 = c6261b.f21131n;
            c6261b.f21125h = ((f11 * f10) - f11) - ((c6261b.f21123f * 2.0f) * f10);
            if (c6261b.f21126i * f10 <= f9) {
            }
            c6261b.f21119b.postScale(fMin, fMin, f9 / 2.0f, f11 / 2.0f);
            if (fMin < 1.0f) {
            }
        }

        @Override // android.view.ScaleGestureDetector.SimpleOnScaleGestureListener, android.view.ScaleGestureDetector.OnScaleGestureListener
        public boolean onScaleBegin(ScaleGestureDetector scaleGestureDetector) {
            if (C6261b.this.f21114K) {
                C6261b.this.f21121d = 3;
                return true;
            }
            C6261b.this.f21121d = 2;
            return true;
        }
    }

    /* renamed from: s3.b$c */
    public class c extends TimerTask {
        public c() {
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            C6261b.this.f21111H.sendEmptyMessage(0);
        }
    }

    /* renamed from: s3.b$d */
    public static class d extends Handler {

        /* renamed from: a */
        public final WeakReference<C6261b> f21146a;

        public d(C6261b c6261b) {
            this.f21146a = new WeakReference<>(c6261b);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            this.f21146a.get().performClick();
            if (this.f21146a.get().f21109F != null) {
                this.f21146a.get().f21109F.onClick(this.f21146a.get());
            }
        }
    }

    public C6261b(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f21119b = new Matrix();
        this.f21120c = new Matrix();
        this.f21121d = 0;
        this.f21132o = new PointF();
        this.f21133p = new PointF();
        this.f21134q = new PointF();
        this.f21138u = 1.0f;
        this.f21139v = 1.0f;
        this.f21140w = 3.0f;
        this.f21141x = 1.0f;
        this.f21142y = new PointF(BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED);
        this.f21143z = BitmapDescriptorFactory.HUE_RED;
        this.f21104A = 0L;
        this.f21105B = 0L;
        this.f21106C = false;
        this.f21111H = null;
        this.f21112I = false;
        this.f21113J = -1;
        this.f21114K = false;
        this.f21115L = false;
        this.f21116M = false;
        this.f21117N = false;
        this.f21118O = false;
        super.setClickable(true);
        this.f21107D = context;
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, C6453a0.CustomTouchImageView, 0, 0);
            this.f21114K = typedArrayObtainStyledAttributes.getBoolean(0, this.f21114K);
            typedArrayObtainStyledAttributes.recycle();
        }
        m23993j();
    }

    /* renamed from: e */
    public final void m23988e() {
        float f9 = this.f21130m;
        float f10 = this.f21138u;
        this.f21124g = ((f9 * f10) - f9) - ((this.f21122e * 2.0f) * f10);
        float f11 = this.f21131n;
        this.f21125h = ((f11 * f10) - f11) - ((this.f21123f * 2.0f) * f10);
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x006f A[PHI: r0
      0x006f: PHI (r0v11 float) = (r0v9 float), (r0v10 float) binds: [B:27:0x006d, B:31:0x007a] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0071  */
    /* renamed from: f */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void m23989f(float f9, float f10) {
        float f11;
        float fRound = Math.round(this.f21126i * this.f21138u);
        float fRound2 = Math.round(this.f21127j * this.f21138u);
        m23992i();
        if (fRound < this.f21130m) {
            float f12 = this.f21137t;
            if (f12 + f10 > BitmapDescriptorFactory.HUE_RED) {
                f10 = -f12;
                f9 = 0.0f;
            } else {
                float f13 = f12 + f10;
                float f14 = this.f21125h;
                if (f13 < (-f14)) {
                    f12 += f14;
                    f10 = -f12;
                }
                f9 = 0.0f;
            }
        } else if (fRound2 < this.f21131n) {
            float f15 = this.f21136s;
            if (f15 + f9 > BitmapDescriptorFactory.HUE_RED) {
                f9 = -f15;
                f10 = 0.0f;
            } else {
                float f16 = f15 + f9;
                float f17 = this.f21124g;
                if (f16 < (-f17)) {
                    f15 += f17;
                    f9 = -f15;
                }
                f10 = 0.0f;
            }
        } else {
            float f18 = this.f21136s;
            if (f18 + f9 > BitmapDescriptorFactory.HUE_RED) {
                f9 = -f18;
                f11 = this.f21137t;
                if (f11 + f10 <= BitmapDescriptorFactory.HUE_RED) {
                    f10 = -f11;
                } else {
                    float f19 = f11 + f10;
                    float f20 = this.f21125h;
                    if (f19 < (-f20)) {
                        f11 += f20;
                        f10 = -f11;
                    }
                }
            } else {
                float f21 = f18 + f9;
                float f22 = this.f21124g;
                if (f21 < (-f22)) {
                    f18 += f22;
                    f9 = -f18;
                }
                f11 = this.f21137t;
                if (f11 + f10 <= BitmapDescriptorFactory.HUE_RED) {
                }
            }
        }
        this.f21119b.postTranslate(f9, f10);
        m23990g();
    }

    /* renamed from: g */
    public final void m23990g() {
        m23992i();
        float fRound = Math.round(this.f21126i * this.f21138u);
        float fRound2 = Math.round(this.f21127j * this.f21138u);
        this.f21118O = false;
        this.f21116M = false;
        this.f21117N = false;
        this.f21115L = false;
        float f9 = this.f21136s;
        if ((-f9) < 10.0f) {
            this.f21115L = true;
        }
        float f10 = this.f21130m;
        if ((fRound >= f10 && (f9 + fRound) - f10 < 10.0f) || (fRound <= f10 && (-f9) + fRound <= f10)) {
            this.f21117N = true;
        }
        float f11 = this.f21137t;
        if ((-f11) < 10.0f) {
            this.f21116M = true;
        }
        if (Math.abs(((-f11) + this.f21131n) - fRound2) < 10.0f) {
            this.f21118O = true;
        }
    }

    /* renamed from: h */
    public final double m23991h(PointF pointF, PointF pointF2) {
        return Math.sqrt(Math.pow(pointF.x - pointF2.x, 2.0d) + Math.pow(pointF.y - pointF2.y, 2.0d));
    }

    /* renamed from: i */
    public final void m23992i() {
        this.f21119b.getValues(this.f21135r);
        float[] fArr = this.f21135r;
        this.f21136s = fArr[2];
        this.f21137t = fArr[5];
    }

    /* renamed from: j */
    public void m23993j() {
        this.f21111H = new d(this);
        this.f21119b.setTranslate(1.0f, 1.0f);
        this.f21135r = new float[9];
        setImageMatrix(this.f21119b);
        setScaleType(ImageView.ScaleType.MATRIX);
        this.f21110G = new ScaleGestureDetector(this.f21107D, new b());
    }

    /* renamed from: k */
    public final void m23994k(PointF pointF, C6262c c6262c) {
        pointF.set((c6262c.mo23983c(0) + c6262c.mo23983c(1)) / 2.0f, (c6262c.mo23984e(0) + c6262c.mo23984e(1)) / 2.0f);
    }

    /* renamed from: l */
    public final PointF m23995l(C6262c c6262c) {
        return new PointF((c6262c.mo23983c(0) + c6262c.mo23983c(1)) / 2.0f, (c6262c.mo23984e(0) + c6262c.mo23984e(1)) / 2.0f);
    }

    /* renamed from: m */
    public final void m23996m() {
        if (Math.abs(this.f21136s + (this.f21124g / 2.0f)) > 0.5f) {
            this.f21119b.postTranslate(-(this.f21136s + (this.f21124g / 2.0f)), BitmapDescriptorFactory.HUE_RED);
        }
        if (Math.abs(this.f21137t + (this.f21125h / 2.0f)) > 0.5f) {
            this.f21119b.postTranslate(BitmapDescriptorFactory.HUE_RED, -(this.f21137t + (this.f21125h / 2.0f)));
        }
    }

    /* renamed from: n */
    public final float m23997n(C6262c c6262c) {
        float fMo23983c = c6262c.mo23983c(0) - c6262c.mo23983c(1);
        float fMo23984e = c6262c.mo23984e(0) - c6262c.mo23984e(1);
        return (float) Math.sqrt((fMo23983c * fMo23983c) + (fMo23984e * fMo23984e));
    }

    @Override // android.widget.ImageView, android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.f21106C) {
            PointF pointF = this.f21142y;
            float f9 = pointF.x;
            float f10 = this.f21143z;
            float f11 = f9 * f10;
            float f12 = pointF.y * f10;
            if (f11 > this.f21130m || f12 > this.f21131n) {
                return;
            }
            this.f21143z = f10 * 0.9f;
            if (Math.abs(f11) >= 0.1d || Math.abs(f12) >= 0.1d) {
                m23989f(f11, f12);
                setImageMatrix(this.f21119b);
            }
        }
    }

    @Override // android.widget.ImageView, android.view.View
    public void onMeasure(int i9, int i10) {
        super.onMeasure(i9, i10);
        this.f21130m = View.MeasureSpec.getSize(i9);
        float size = View.MeasureSpec.getSize(i10);
        this.f21131n = size;
        float fMin = Math.min(this.f21130m / this.f21128k, size / this.f21129l);
        this.f21119b.setScale(fMin, fMin);
        setImageMatrix(this.f21119b);
        this.f21138u = 1.0f;
        float f9 = this.f21131n - (this.f21129l * fMin);
        float f10 = this.f21130m - (fMin * this.f21128k);
        float f11 = f9 / 2.0f;
        this.f21123f = f11;
        float f12 = f10 / 2.0f;
        this.f21122e = f12;
        this.f21119b.postTranslate(f12, f11);
        this.f21126i = this.f21130m - (this.f21122e * 2.0f);
        this.f21127j = this.f21131n - (this.f21123f * 2.0f);
        m23988e();
        setImageMatrix(this.f21119b);
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
        C6262c c6262cM23998g = C6262c.m23998g(motionEvent);
        Object obj = this.f21110G;
        if (obj != null) {
            ((ScaleGestureDetector) obj).onTouchEvent(motionEvent);
        }
        m23992i();
        PointF pointF = new PointF(c6262cM23998g.m24000b(), c6262cM23998g.m24001d());
        int iM23999a = c6262cM23998g.m23999a() & 255;
        if (iM23999a == 0) {
            this.f21106C = false;
            this.f21120c.set(this.f21119b);
            this.f21132o.set(c6262cM23998g.m24000b(), c6262cM23998g.m24001d());
            this.f21134q.set(this.f21132o);
            if (!this.f21114K) {
                this.f21121d = 1;
            }
            this.f21113J = motionEvent.getPointerId(0);
        } else if (iM23999a == 1) {
            this.f21106C = true;
            this.f21121d = 0;
            int iAbs = (int) Math.abs(c6262cM23998g.m24000b() - this.f21134q.x);
            int iAbs2 = (int) Math.abs(c6262cM23998g.m24001d() - this.f21134q.y);
            if (iAbs < 10 && iAbs2 < 10) {
                long jCurrentTimeMillis = System.currentTimeMillis();
                if (jCurrentTimeMillis - this.f21104A <= 600) {
                    Timer timer = this.f21108E;
                    if (timer != null) {
                        timer.cancel();
                    }
                    float f11 = this.f21138u;
                    if (f11 == 1.0f) {
                        float f12 = this.f21140w / f11;
                        Matrix matrix = this.f21119b;
                        PointF pointF2 = this.f21134q;
                        matrix.postScale(f12, f12, pointF2.x, pointF2.y);
                        this.f21138u = this.f21140w;
                    } else {
                        Matrix matrix2 = this.f21119b;
                        float f13 = this.f21139v;
                        matrix2.postScale(f13 / f11, f13 / f11, this.f21130m / 2.0f, this.f21131n / 2.0f);
                        this.f21138u = this.f21139v;
                    }
                    m23988e();
                    m23989f(BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED);
                    this.f21104A = 0L;
                } else {
                    this.f21104A = jCurrentTimeMillis;
                    Timer timer2 = new Timer();
                    this.f21108E = timer2;
                    timer2.schedule(new c(), 300L);
                }
                if (this.f21138u == this.f21139v) {
                    m23996m();
                }
            }
        } else if (iM23999a == 2) {
            this.f21106C = false;
            int i9 = this.f21121d;
            if (i9 == 1 || i9 == 3) {
                for (int i10 = 0; i10 < motionEvent.getPointerCount(); i10++) {
                    if (motionEvent.getPointerId(i10) == this.f21113J) {
                        pointF.x = motionEvent.getX(i10);
                        pointF.y = motionEvent.getY(i10);
                    }
                }
                float f14 = pointF.x;
                PointF pointF3 = this.f21132o;
                float f15 = f14 - pointF3.x;
                float f16 = pointF.y - pointF3.y;
                long jCurrentTimeMillis2 = System.currentTimeMillis();
                this.f21143z = (((float) m23991h(pointF, this.f21132o)) / (jCurrentTimeMillis2 - this.f21105B)) * 0.9f;
                this.f21105B = jCurrentTimeMillis2;
                m23989f(f15, f16);
                this.f21142y.set(f15, f16);
                this.f21132o.set(pointF.x, pointF.y);
            } else if (this.f21110G == null && (i9 == 2 || i9 == 3)) {
                float fM23997n = m23997n(c6262cM23998g);
                if (motionEvent.getPointerCount() >= 2 && 10.0f <= Math.abs(this.f21141x - fM23997n) && Math.abs(this.f21141x - fM23997n) <= 50.0f) {
                    float f17 = fM23997n / this.f21141x;
                    this.f21141x = fM23997n;
                    float f18 = this.f21138u;
                    float f19 = f18 * f17;
                    this.f21138u = f19;
                    float f20 = this.f21140w;
                    if (f19 > f20) {
                        this.f21138u = f20;
                    } else {
                        f20 = this.f21139v;
                        if (f19 < f20) {
                            this.f21138u = f20;
                        }
                        m23988e();
                        float f21 = this.f21126i;
                        float f22 = this.f21138u;
                        f9 = f21 * f22;
                        f10 = this.f21130m;
                        if (f9 > f10 || this.f21127j * f22 <= this.f21131n) {
                            this.f21119b.postScale(f17, f17, f10 / 2.0f, this.f21131n / 2.0f);
                            if (f17 < 1.0f) {
                                m23992i();
                                if (f17 < 1.0f) {
                                    m23996m();
                                }
                            }
                        } else {
                            PointF pointFM23995l = m23995l(c6262cM23998g);
                            this.f21119b.postScale(f17, f17, pointFM23995l.x, pointFM23995l.y);
                            m23992i();
                            if (f17 < 1.0f) {
                                float f23 = this.f21136s;
                                float f24 = this.f21124g;
                                if (f23 < (-f24)) {
                                    this.f21119b.postTranslate(-(f23 + f24), BitmapDescriptorFactory.HUE_RED);
                                } else if (f23 > BitmapDescriptorFactory.HUE_RED) {
                                    this.f21119b.postTranslate(-f23, BitmapDescriptorFactory.HUE_RED);
                                }
                                float f25 = this.f21137t;
                                float f26 = this.f21125h;
                                if (f25 < (-f26)) {
                                    this.f21119b.postTranslate(BitmapDescriptorFactory.HUE_RED, -(f25 + f26));
                                } else if (f25 > BitmapDescriptorFactory.HUE_RED) {
                                    this.f21119b.postTranslate(BitmapDescriptorFactory.HUE_RED, -f25);
                                }
                            }
                        }
                        m23990g();
                    }
                    f17 = f20 / f18;
                    m23988e();
                    float f212 = this.f21126i;
                    float f222 = this.f21138u;
                    f9 = f212 * f222;
                    f10 = this.f21130m;
                    if (f9 > f10) {
                        this.f21119b.postScale(f17, f17, f10 / 2.0f, this.f21131n / 2.0f);
                        if (f17 < 1.0f) {
                        }
                        m23990g();
                    }
                }
            }
        } else if (iM23999a == 5) {
            float fM23997n2 = m23997n(c6262cM23998g);
            this.f21141x = fM23997n2;
            if (fM23997n2 > 10.0f) {
                this.f21120c.set(this.f21119b);
                m23994k(this.f21133p, c6262cM23998g);
                if (this.f21114K) {
                    this.f21121d = 3;
                } else {
                    this.f21121d = 2;
                }
            }
        } else if (iM23999a == 6) {
            this.f21121d = 0;
            this.f21143z = BitmapDescriptorFactory.HUE_RED;
            this.f21120c.set(this.f21119b);
            this.f21141x = m23997n(c6262cM23998g);
        }
        setImageMatrix(this.f21119b);
        invalidate();
        return true;
    }

    @Override // android.widget.ImageView
    public void setImageBitmap(Bitmap bitmap) {
        super.setImageBitmap(bitmap);
        if (bitmap != null) {
            this.f21128k = bitmap.getWidth();
            this.f21129l = bitmap.getHeight();
        }
    }

    @Override // p183r3.C6219a, android.widget.ImageView
    public void setImageDrawable(Drawable drawable) {
        super.setImageDrawable(drawable);
        if (drawable != null) {
            this.f21128k = drawable.getMinimumWidth();
            this.f21129l = drawable.getMinimumHeight();
        }
    }

    @Override // android.view.View
    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.f21109F = onClickListener;
    }

    public void setZoomToOriginalSize(boolean z8) {
        this.f21112I = z8;
    }
}
