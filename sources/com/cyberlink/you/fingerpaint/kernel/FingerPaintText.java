package com.cyberlink.you.fingerpaint.kernel;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;

/* loaded from: classes.dex */
public class FingerPaintText extends FingerPaintObject {
    private static final long serialVersionUID = 0;
    private String mText = null;
    private float[] mStartPoint = new float[2];

    public FingerPaintText() {
        for (int i9 = 0; i9 < 2; i9++) {
            this.mStartPoint[i9] = 0.0f;
        }
    }

    @Override // com.cyberlink.you.fingerpaint.kernel.FingerPaintObject
    /* renamed from: a */
    public void mo15554a(Canvas canvas, float f9, float f10) {
        String str = this.mText;
        if (str == null || str.isEmpty()) {
            return;
        }
        float fMo15559d = mo15559d() * 2.0f;
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setDither(true);
        paint.setStyle(Paint.Style.FILL);
        paint.setTypeface(m15573m());
        paint.setTextSize(fMo15559d);
        paint.setColor(mo15558c());
        String str2 = this.mText;
        float[] fArr = this.mStartPoint;
        canvas.drawText(str2, fArr[0], fArr[1] + fMo15559d, paint);
    }

    @Override // com.cyberlink.you.fingerpaint.kernel.FingerPaintObject
    /* renamed from: b */
    public void mo15555b(float f9, float f10) {
    }

    @Override // com.cyberlink.you.fingerpaint.kernel.FingerPaintObject
    /* renamed from: i */
    public void mo15556i(float f9, float f10) {
        float[] fArr = this.mStartPoint;
        fArr[0] = f9;
        fArr[1] = f10;
    }

    @Override // com.cyberlink.you.fingerpaint.kernel.FingerPaintObject
    /* renamed from: k */
    public void mo15557k(float f9, float f10) {
    }

    @Override // com.cyberlink.you.fingerpaint.kernel.FingerPaintObject
    /* renamed from: l */
    public void mo15565l(String str) {
        this.mText = str;
    }

    /* renamed from: m */
    public final Typeface m15573m() {
        return Typeface.SERIF;
    }
}
