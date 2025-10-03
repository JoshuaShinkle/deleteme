package com.cyberlink.you.fingerpaint.kernel;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

/* loaded from: classes.dex */
public class FingerPaintArrow extends FingerPaintObject {
    private static final long serialVersionUID = 0;
    private float[] mStartPoint = {BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED};
    private float[] mEndPoint = {BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED};

    @Override // com.cyberlink.you.fingerpaint.kernel.FingerPaintObject
    /* renamed from: a */
    public void mo15554a(Canvas canvas, float f9, float f10) {
        float[] fArr = this.mEndPoint;
        float f11 = fArr[0];
        float[] fArr2 = this.mStartPoint;
        float f12 = f11 - fArr2[0];
        float f13 = fArr[1] - fArr2[1];
        float fSqrt = (float) Math.sqrt((f12 * f12) + (f13 * f13));
        float f14 = this.mSize;
        float f15 = f14 * 2.0f;
        float f16 = 0.9f * fSqrt;
        float f17 = f14 / 2.0f;
        float f18 = (-f14) / 2.0f;
        float[] fArr3 = {BitmapDescriptorFactory.HUE_RED, f17, f16, f17, f16, f15 / 2.0f, fSqrt, BitmapDescriptorFactory.HUE_RED, f16, (-f15) / 2.0f, f16, f18, BitmapDescriptorFactory.HUE_RED, f18};
        Matrix matrix = new Matrix();
        matrix.reset();
        matrix.setSinCos(f13 / fSqrt, f12 / fSqrt);
        float[] fArr4 = this.mStartPoint;
        matrix.postTranslate(fArr4[0], fArr4[1]);
        matrix.mapPoints(fArr3);
        Path path = new Path();
        path.moveTo(fArr3[0], fArr3[1]);
        for (int i9 = 2; i9 <= 12; i9 += 2) {
            path.lineTo(fArr3[i9], fArr3[i9 + 1]);
        }
        path.lineTo(fArr3[0], fArr3[1]);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setDither(true);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setColor(mo15558c());
        paint.setStrokeWidth(1.0f);
        canvas.drawPath(path, paint);
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
    /* renamed from: k */
    public void mo15557k(float f9, float f10) {
        float[] fArr = this.mEndPoint;
        fArr[0] = f9;
        fArr[1] = f10;
    }
}
