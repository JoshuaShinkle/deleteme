package com.cyberlink.you.fingerpaint.kernel;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

/* loaded from: classes.dex */
public class FingerPaintRectangle extends FingerPaintObject {
    private static final long serialVersionUID = 0;
    private float[] mStartPoint = new float[2];
    private float[] mEndPoint = new float[2];
    private float[] mShift = new float[2];

    public FingerPaintRectangle() {
        for (int i9 = 0; i9 < 2; i9++) {
            this.mStartPoint[i9] = 0.0f;
            this.mEndPoint[i9] = 0.0f;
        }
    }

    @Override // com.cyberlink.you.fingerpaint.kernel.FingerPaintObject
    /* renamed from: a */
    public void mo15554a(Canvas canvas, float f9, float f10) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setDither(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setColor(mo15558c());
        if (f9 > 1.0f) {
            paint.setStrokeWidth((mo15559d() + f9) - 1.0f);
        } else {
            paint.setStrokeWidth(mo15559d());
        }
        RectF rectF = new RectF();
        rectF.left = Math.min(this.mStartPoint[0], this.mEndPoint[0]) + this.mShift[0];
        rectF.right = Math.max(this.mStartPoint[0], this.mEndPoint[0]) + this.mShift[0];
        rectF.top = Math.min(this.mStartPoint[1], this.mEndPoint[1]) + this.mShift[1];
        rectF.bottom = Math.max(this.mStartPoint[1], this.mEndPoint[1]) + this.mShift[1];
        canvas.drawRect(rectF, paint);
    }

    @Override // com.cyberlink.you.fingerpaint.kernel.FingerPaintObject
    /* renamed from: b */
    public void mo15555b(float f9, float f10) {
        float[] fArr = this.mEndPoint;
        fArr[0] = f9;
        fArr[1] = f10;
    }

    @Override // com.cyberlink.you.fingerpaint.kernel.FingerPaintObject
    /* renamed from: i */
    public void mo15556i(float f9, float f10) {
        float[] fArr = this.mStartPoint;
        fArr[0] = f9;
        fArr[1] = f10;
        float[] fArr2 = this.mEndPoint;
        fArr2[0] = f9;
        fArr2[1] = f10;
    }

    @Override // com.cyberlink.you.fingerpaint.kernel.FingerPaintObject
    /* renamed from: j */
    public void mo15564j(float[] fArr, float[] fArr2, float[] fArr3, boolean z8) {
        if (z8) {
            float[] fArr4 = this.mStartPoint;
            fArr4[0] = fArr[0];
            fArr4[1] = fArr[1];
            float[] fArr5 = this.mEndPoint;
            fArr5[0] = fArr2[0];
            fArr5[1] = fArr2[1];
            return;
        }
        float[] fArr6 = this.mStartPoint;
        fArr6[0] = fArr6[0] + fArr3[0];
        fArr6[1] = fArr6[1] + fArr3[1];
        float[] fArr7 = this.mEndPoint;
        fArr7[0] = fArr7[0] + fArr3[0];
        fArr7[1] = fArr7[1] + fArr3[1];
    }

    @Override // com.cyberlink.you.fingerpaint.kernel.FingerPaintObject
    /* renamed from: k */
    public void mo15557k(float f9, float f10) {
        float[] fArr = this.mEndPoint;
        fArr[0] = f9;
        fArr[1] = f10;
    }
}
