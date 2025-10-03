package com.cyberlink.you.fingerpaint.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.inputmethod.InputMethodManager;
import android.widget.RelativeLayout;
import com.cyberlink.p030U.R;
import com.cyberlink.you.fingerpaint.FingerPaintActivity;
import com.cyberlink.you.fingerpaint.kernel.C3036c;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import p192s3.C6261b;

/* loaded from: classes.dex */
public class FingerPaintImageView extends C6261b {

    /* renamed from: A0 */
    public int f13577A0;

    /* renamed from: B0 */
    public int f13578B0;

    /* renamed from: P */
    public FingerPaintActivity f13579P;

    /* renamed from: Q */
    public Bitmap f13580Q;

    /* renamed from: R */
    public Bitmap f13581R;

    /* renamed from: S */
    public Canvas f13582S;

    /* renamed from: T */
    public Canvas f13583T;

    /* renamed from: U */
    public C3036c f13584U;

    /* renamed from: V */
    public float f13585V;

    /* renamed from: W */
    public float f13586W;

    /* renamed from: a0 */
    public boolean f13587a0;

    /* renamed from: b0 */
    public boolean f13588b0;

    /* renamed from: c0 */
    public float f13589c0;

    /* renamed from: d0 */
    public float f13590d0;

    /* renamed from: e0 */
    public boolean f13591e0;

    /* renamed from: f0 */
    public boolean f13592f0;

    /* renamed from: g0 */
    public Bitmap f13593g0;

    /* renamed from: h0 */
    public Bitmap f13594h0;

    /* renamed from: i0 */
    public Bitmap f13595i0;

    /* renamed from: j0 */
    public Bitmap f13596j0;

    /* renamed from: k0 */
    public Canvas f13597k0;

    /* renamed from: l0 */
    public Canvas f13598l0;

    /* renamed from: m0 */
    public Paint f13599m0;

    /* renamed from: n0 */
    public Paint f13600n0;

    /* renamed from: o0 */
    public Paint f13601o0;

    /* renamed from: p0 */
    public RectF f13602p0;

    /* renamed from: q0 */
    public float f13603q0;

    /* renamed from: r0 */
    public Matrix f13604r0;

    /* renamed from: s0 */
    public Xfermode f13605s0;

    /* renamed from: t0 */
    public Xfermode f13606t0;

    /* renamed from: u0 */
    public final int f13607u0;

    /* renamed from: v0 */
    public final float f13608v0;

    /* renamed from: w0 */
    public final float f13609w0;

    /* renamed from: x0 */
    public float f13610x0;

    /* renamed from: y0 */
    public int f13611y0;

    /* renamed from: z0 */
    public int f13612z0;

    public FingerPaintImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f13584U = C3036c.m15579m();
        this.f13587a0 = false;
        this.f13588b0 = false;
        this.f13603q0 = 1.2f;
        this.f13604r0 = null;
        this.f13605s0 = new PorterDuffXfermode(PorterDuff.Mode.SRC);
        this.f13606t0 = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);
        this.f13607u0 = getResources().getColor(R.color.smart_note_roi_border);
        this.f13608v0 = 1.5f;
        this.f13609w0 = getResources().getDisplayMetrics().density;
        float f9 = getResources().getDisplayMetrics().widthPixels / 2.5f;
        this.f13610x0 = f9;
        int i9 = (int) f9;
        this.f13611y0 = i9;
        this.f13612z0 = (int) f9;
        this.f13577A0 = i9 / 2;
        this.f13578B0 = 3;
        m15609t(context);
    }

    /* renamed from: o */
    public final void m15604o(float f9, float f10) {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.setMargins(Math.round(f9), Math.round(f10), 0, 0);
        this.f13579P.f13515N.setLayoutParams(layoutParams);
    }

    @Override // p192s3.C6261b, android.widget.ImageView, android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        if (this.f13580Q != null) {
            canvas.concat(this.f21119b);
            canvas.drawBitmap(this.f13580Q, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, (Paint) null);
        }
        this.f13584U.m15584e(canvas);
        canvas.restore();
        if (this.f13591e0) {
            this.f13584U.m15584e(this.f13583T);
            m15607r(canvas);
        }
        Canvas canvas2 = this.f13583T;
        if (canvas2 != null) {
            canvas2.drawColor(0, PorterDuff.Mode.CLEAR);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x0069  */
    @Override // p192s3.C6261b, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public boolean onTouchEvent(MotionEvent motionEvent) {
        float x8 = motionEvent.getX();
        float y8 = motionEvent.getY();
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            this.f13588b0 = false;
            this.f13591e0 = true;
            this.f13585V = x8;
            this.f13586W = y8;
            m15613x(x8, y8);
            invalidate();
        } else if (actionMasked == 1) {
            if (this.f13587a0) {
                m15614y(x8, y8);
                invalidate();
                this.f13587a0 = false;
            }
            this.f13591e0 = false;
        } else if (actionMasked == 2) {
            float fAbs = Math.abs(x8 - this.f13585V);
            float fAbs2 = Math.abs(y8 - this.f13586W);
            if (fAbs >= 4.0f || fAbs2 >= 4.0f) {
                this.f13585V = x8;
                this.f13586W = y8;
                boolean z8 = !this.f13588b0;
                this.f13587a0 = z8;
                if (z8) {
                    m15612w(x8, y8);
                    invalidate();
                }
            }
        } else if (actionMasked != 3) {
            if (actionMasked == 5) {
                this.f13588b0 = true;
                if (this.f13587a0) {
                    m15614y(x8, y8);
                    invalidate();
                    this.f13587a0 = false;
                    MotionEvent motionEventObtain = MotionEvent.obtain(motionEvent);
                    motionEventObtain.setAction(0);
                    super.onTouchEvent(motionEventObtain);
                } else {
                    this.f13591e0 = false;
                    this.f13584U.m15594p();
                }
            }
        }
        super.onTouchEvent(motionEvent);
        return true;
    }

    /* renamed from: p */
    public final void m15605p(float[] fArr) {
        Matrix matrix = new Matrix();
        if (this.f21119b.invert(matrix)) {
            matrix.mapPoints(fArr);
        }
    }

    /* renamed from: q */
    public final boolean m15606q() {
        String string = this.f13579P.f13515N.getText().toString();
        if (this.f13584U.m15591l() != 5 || !"".equals(string)) {
            this.f13579P.f13515N.setVisibility(4);
            ((InputMethodManager) this.f13579P.getSystemService("input_method")).hideSoftInputFromWindow(this.f13579P.f13515N.getWindowToken(), 0);
            this.f13579P.f13515N.clearFocus();
            return false;
        }
        this.f13579P.f13515N.setVisibility(0);
        this.f13579P.f13515N.setTextColor(this.f13584U.m15588i());
        this.f13579P.f13515N.setTextSize(this.f13584U.m15590k() / 2.0f);
        this.f13579P.f13515N.setPadding(0, 0, 0, 0);
        ((InputMethodManager) this.f13579P.getSystemService("input_method")).showSoftInput(this.f13579P.f13515N, 1);
        this.f13579P.f13515N.requestFocus();
        return true;
    }

    /* renamed from: r */
    public final void m15607r(Canvas canvas) {
        float f9;
        float[] fArr = {this.f13585V, this.f13586W};
        int i9 = this.f13611y0;
        float f10 = i9;
        float f11 = this.f13612z0;
        float f12 = this.f13609w0;
        float f13 = (f12 * 10.0f) + 0.5f;
        float f14 = (f12 * 10.0f) + 0.5f;
        float f15 = ((i9 - f13) / 2.0f) + 0.5f;
        RectF rectF = new RectF(f13, f14, f10, f11);
        RectF rectF2 = new RectF((canvas.getWidth() - f10) - f13, f14, canvas.getWidth() - f13, f11);
        if (this.f13592f0) {
            this.f13602p0 = rectF2;
            f9 = rectF2.left;
        } else {
            this.f13602p0 = rectF;
            f9 = 0.0f;
        }
        m15605p(fArr);
        m15608s(this.f13594h0, (int) fArr[0], (int) fArr[1]);
        if (this.f13602p0.contains(this.f13585V, this.f13586W)) {
            boolean z8 = this.f13592f0;
            if (z8) {
                this.f13602p0 = rectF;
                f9 = 0.0f;
            } else {
                this.f13602p0 = rectF2;
                f9 = rectF2.left;
            }
            this.f13592f0 = !z8;
        }
        canvas.drawBitmap(this.f13595i0, f9, BitmapDescriptorFactory.HUE_RED, (Paint) null);
        if (this.f13592f0) {
            this.f13602p0.left += f13;
        }
        canvas.drawRect(this.f13602p0, this.f13599m0);
        RectF rectF3 = this.f13602p0;
        float f16 = rectF3.left;
        float f17 = rectF3.top;
        float f18 = f15 / 6.0f;
        canvas.drawLine(f16 + f15, (f17 + f15) - f18, f16 + f15, f17 + f15 + f18, this.f13599m0);
        RectF rectF4 = this.f13602p0;
        float f19 = rectF4.left;
        float f20 = rectF4.top;
        canvas.drawLine((f19 + f15) - f18, f20 + f15, f19 + f15 + f18, f20 + f15, this.f13599m0);
        RectF rectF5 = this.f13602p0;
        float f21 = rectF5.left;
        float f22 = rectF5.top;
        canvas.drawLine((f21 + f15) - 3.0f, (f22 + f15) - f18, (f21 + f15) - 3.0f, (f22 + f15) - 3.0f, this.f13600n0);
        RectF rectF6 = this.f13602p0;
        float f23 = rectF6.left;
        float f24 = rectF6.top;
        canvas.drawLine(f23 + f15 + 3.0f, (f24 + f15) - f18, f23 + f15 + 3.0f, (f24 + f15) - 3.0f, this.f13600n0);
        RectF rectF7 = this.f13602p0;
        float f25 = rectF7.left;
        float f26 = rectF7.top;
        canvas.drawLine((f25 + f15) - 3.0f, (f26 + f15) - f18, f25 + f15 + 3.0f, (f26 + f15) - f18, this.f13600n0);
        RectF rectF8 = this.f13602p0;
        float f27 = rectF8.left;
        float f28 = rectF8.top;
        canvas.drawLine((f27 + f15) - 3.0f, f28 + f15 + 3.0f, (f27 + f15) - 3.0f, f28 + f15 + f18, this.f13600n0);
        RectF rectF9 = this.f13602p0;
        float f29 = rectF9.left;
        float f30 = rectF9.top;
        canvas.drawLine(f29 + f15 + 3.0f, f30 + f15 + 3.0f, f29 + f15 + 3.0f, f30 + f15 + f18, this.f13600n0);
        RectF rectF10 = this.f13602p0;
        float f31 = rectF10.left;
        float f32 = rectF10.top;
        canvas.drawLine((f31 + f15) - 3.0f, f32 + f15 + f18, f31 + f15 + 3.0f, f32 + f15 + f18, this.f13600n0);
        RectF rectF11 = this.f13602p0;
        float f33 = rectF11.left;
        float f34 = rectF11.top;
        canvas.drawLine((f33 + f15) - f18, (f34 + f15) - 3.0f, (f33 + f15) - 3.0f, (f34 + f15) - 3.0f, this.f13600n0);
        RectF rectF12 = this.f13602p0;
        float f35 = rectF12.left;
        float f36 = rectF12.top;
        canvas.drawLine((f35 + f15) - f18, f36 + f15 + 3.0f, (f35 + f15) - 3.0f, f36 + f15 + 3.0f, this.f13600n0);
        RectF rectF13 = this.f13602p0;
        float f37 = rectF13.left;
        float f38 = rectF13.top;
        canvas.drawLine((f37 + f15) - f18, (f38 + f15) - 3.0f, (f37 + f15) - f18, f38 + f15 + 3.0f, this.f13600n0);
        RectF rectF14 = this.f13602p0;
        float f39 = rectF14.left;
        float f40 = rectF14.top;
        canvas.drawLine(f39 + f15 + 3.0f, (f40 + f15) - 3.0f, f39 + f15 + f18, (f40 + f15) - 3.0f, this.f13600n0);
        RectF rectF15 = this.f13602p0;
        float f41 = rectF15.left;
        float f42 = rectF15.top;
        canvas.drawLine(f41 + f15 + 3.0f, f42 + f15 + 3.0f, f41 + f15 + f18, f42 + f15 + 3.0f, this.f13600n0);
        RectF rectF16 = this.f13602p0;
        float f43 = rectF16.left;
        float f44 = rectF16.top;
        canvas.drawLine(f43 + f15 + f18, (f44 + f15) - 3.0f, f43 + f15 + f18, f44 + f15 + 3.0f, this.f13600n0);
    }

    /* renamed from: s */
    public final void m15608s(Bitmap bitmap, int i9, int i10) {
        int i11;
        int i12;
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int i13 = this.f13577A0;
        int i14 = this.f13578B0;
        int i15 = (i9 - i13) - i14;
        int i16 = (i10 - i13) - i14;
        int i17 = this.f13611y0;
        int i18 = this.f13612z0;
        float[] fArr = new float[9];
        this.f21119b.getValues(fArr);
        float f9 = this.f13603q0;
        float f10 = fArr[0];
        if (f9 >= f10) {
            this.f13604r0.setScale(f9, f9);
        } else {
            this.f13604r0.setScale(f10, fArr[4]);
        }
        if (i15 < 0) {
            i17 += i15;
            i11 = this.f13611y0 - i17;
            i15 = 0;
        } else {
            if (i15 > (width - this.f13611y0) - 1) {
                i17 = width - i15;
            }
            i11 = 0;
        }
        if (i16 < 0) {
            i18 += i16;
            i12 = this.f13612z0 - i18;
            i16 = 0;
        } else {
            if (i16 > (height - this.f13612z0) - 1) {
                i18 = height - i16;
            }
            i12 = 0;
        }
        if (i17 > 0 && i18 > 0) {
            Rect rect = new Rect(i15, i16, i15 + i17, i16 + i18);
            Rect rect2 = new Rect(i11, i12, i17 + i11, i18 + i12);
            this.f13604r0.getValues(fArr);
            this.f13598l0.save();
            this.f13598l0.drawColor(-16777216);
            Canvas canvas = this.f13598l0;
            float f11 = fArr[0];
            float f12 = fArr[4];
            int i19 = this.f13577A0;
            canvas.scale(f11, f12, i19, i19);
            this.f13598l0.drawBitmap(bitmap, rect, rect2, this.f13601o0);
            this.f13598l0.drawColor(0);
            this.f13598l0.drawBitmap(this.f13580Q, rect, rect2, this.f13601o0);
            this.f13598l0.drawColor(0);
            this.f13598l0.drawBitmap(this.f13581R, rect, rect2, this.f13601o0);
            this.f13598l0.restore();
        }
        this.f13600n0.setXfermode(this.f13605s0);
        this.f13597k0.drawRect(this.f13602p0, this.f13600n0);
        this.f13600n0.setXfermode(this.f13606t0);
        this.f13597k0.drawBitmap(this.f13596j0, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, this.f13600n0);
        System.gc();
    }

    @Override // p192s3.C6261b, android.widget.ImageView
    public void setImageBitmap(Bitmap bitmap) {
        try {
            m15611v();
            Bitmap bitmapCreateScaledBitmap = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth(), bitmap.getHeight(), true);
            this.f13593g0 = bitmapCreateScaledBitmap;
            this.f13594h0 = Bitmap.createBitmap(bitmapCreateScaledBitmap).copy(Bitmap.Config.RGB_565, true);
            Bitmap bitmapCreateBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
            this.f13580Q = bitmapCreateBitmap;
            bitmapCreateBitmap.eraseColor(0);
            this.f13582S = new Canvas(this.f13580Q);
            Bitmap bitmapCreateBitmap2 = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
            this.f13581R = bitmapCreateBitmap2;
            bitmapCreateBitmap2.eraseColor(0);
            this.f13583T = new Canvas(this.f13581R);
            int[] iArrM15587h = this.f13584U.m15587h();
            if (iArrM15587h[0] == 0 && iArrM15587h[1] == 0) {
                this.f13584U.m15597s(this.f13582S.getWidth(), this.f13582S.getHeight());
            }
            this.f13584U.m15583d(this.f13582S, 0);
            super.setImageBitmap(bitmap);
        } catch (OutOfMemoryError e9) {
            e9.printStackTrace();
        }
    }

    /* renamed from: t */
    public final void m15609t(Context context) {
        this.f13591e0 = false;
        this.f13592f0 = false;
        this.f13595i0 = Bitmap.createBitmap(this.f13611y0, this.f13612z0, Bitmap.Config.ARGB_8888);
        this.f13597k0 = new Canvas(this.f13595i0);
        this.f13596j0 = Bitmap.createBitmap(this.f13611y0, this.f13612z0, Bitmap.Config.RGB_565);
        this.f13598l0 = new Canvas(this.f13596j0);
        Paint paint = new Paint();
        this.f13600n0 = paint;
        paint.setColor(-16777216);
        this.f13600n0.setStyle(Paint.Style.FILL);
        this.f13600n0.setAntiAlias(true);
        Paint paint2 = new Paint();
        this.f13599m0 = paint2;
        paint2.setColor(this.f13607u0);
        this.f13599m0.setStyle(Paint.Style.STROKE);
        this.f13599m0.setStrokeWidth((this.f13609w0 * 1.5f) + 0.5f);
        this.f13599m0.setAntiAlias(true);
        this.f13601o0 = new Paint();
        Matrix matrix = new Matrix();
        this.f13604r0 = matrix;
        float f9 = this.f13603q0;
        matrix.setScale(f9, f9);
        this.f13593g0 = null;
        this.f13594h0 = null;
        this.f13602p0 = null;
        this.f13580Q = null;
        this.f13582S = null;
        this.f13581R = null;
        this.f13583T = null;
        this.f13579P = (FingerPaintActivity) context;
    }

    /* renamed from: u */
    public void m15610u() {
        Bitmap bitmap = this.f13580Q;
        if (bitmap == null || this.f13582S == null) {
            return;
        }
        bitmap.eraseColor(0);
        this.f13584U.m15583d(this.f13582S, 0);
        invalidate();
    }

    /* renamed from: v */
    public void m15611v() {
        Bitmap bitmap = this.f13580Q;
        if (bitmap != null) {
            bitmap.recycle();
            this.f13580Q = null;
        }
        this.f13582S = null;
        System.gc();
    }

    /* renamed from: w */
    public final void m15612w(float f9, float f10) {
        float[] fArr = {f9, f10};
        m15605p(fArr);
        this.f13584U.m15603y(fArr[0], fArr[1]);
    }

    /* renamed from: x */
    public final void m15613x(float f9, float f10) {
        float[] fArr = {f9, f10};
        if (this.f13584U.m15591l() != 5) {
            m15606q();
            m15605p(fArr);
            this.f13584U.m15594p();
            this.f13584U.m15601w(fArr[0], fArr[1], "");
            return;
        }
        if (!m15606q()) {
            this.f13584U.m15601w(this.f13589c0, this.f13590d0, this.f13579P.f13515N.getText().toString());
            this.f13579P.f13515N.setText("");
            m15614y(f9, f10);
        } else {
            m15604o(f9, f10);
            m15605p(fArr);
            this.f13589c0 = fArr[0];
            this.f13590d0 = fArr[1];
        }
    }

    /* renamed from: y */
    public final void m15614y(float f9, float f10) {
        float[] fArr = {f9, f10};
        m15605p(fArr);
        this.f13584U.m15585f(fArr[0], fArr[1]);
        this.f13584U.m15584e(this.f13582S);
        this.f13584U.m15594p();
        this.f13579P.m15546n0();
    }
}
