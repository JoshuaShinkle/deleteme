package com.cyberlink.you.fingerpaint.kernel;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class FingerPaintPath extends FingerPaintObject {
    private static final long serialVersionUID = 0;
    private float borderBottom;
    private float borderLeft;
    private float borderRight;
    private float borderTop;

    /* renamed from: c */
    public transient float f13557c;

    /* renamed from: d */
    public transient float f13558d;
    private ArrayList<float[]> mPointList;
    private float shiftX;
    private float shiftY;

    /* renamed from: e */
    public transient Path f13559e = null;
    private float[] totalShift = new float[2];
    private float[] mStartPoint = new float[2];
    private float[] mEndPoint = new float[2];

    public FingerPaintPath() {
        this.mPointList = null;
        this.mPointList = new ArrayList<>(0);
        for (int i9 = 0; i9 < 2; i9++) {
            this.mStartPoint[i9] = 0.0f;
            this.mEndPoint[i9] = 0.0f;
            this.totalShift[i9] = 0.0f;
        }
        mo15560e();
    }

    @Override // com.cyberlink.you.fingerpaint.kernel.FingerPaintObject
    /* renamed from: a */
    public void mo15554a(Canvas canvas, float f9, float f10) {
        if (this.f13559e == null) {
            m15568o();
        }
        if (this.f13559e == null || canvas == null) {
            return;
        }
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setDither(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setColor(mo15558c());
        paint.setStrokeWidth(mo15559d());
        canvas.drawPath(this.f13559e, paint);
    }

    @Override // com.cyberlink.you.fingerpaint.kernel.FingerPaintObject
    /* renamed from: b */
    public void mo15555b(float f9, float f10) {
        Path path = this.f13559e;
        if (path != null) {
            path.lineTo(this.f13557c, this.f13558d);
        }
    }

    @Override // com.cyberlink.you.fingerpaint.kernel.FingerPaintObject
    /* renamed from: c */
    public int mo15558c() {
        return this.mType == 2 ? this.mColor & (-2130706433) : this.mColor;
    }

    @Override // com.cyberlink.you.fingerpaint.kernel.FingerPaintObject
    /* renamed from: d */
    public float mo15559d() {
        return this.mType == 2 ? this.mSize + 10.0f : this.mSize;
    }

    @Override // com.cyberlink.you.fingerpaint.kernel.FingerPaintObject
    /* renamed from: e */
    public void mo15560e() {
        this.mType = 1;
        this.mSize = 12.0f;
        this.mColor = FingerPaintObject.f13556b[0];
        this.mSelectedBorderSize = 10;
        this.mSelectedBorderColor = -16776961;
        m15569p();
        this.borderLeft = BitmapDescriptorFactory.HUE_RED;
        this.borderTop = BitmapDescriptorFactory.HUE_RED;
        this.borderRight = BitmapDescriptorFactory.HUE_RED;
        this.borderBottom = BitmapDescriptorFactory.HUE_RED;
        this.shiftX = BitmapDescriptorFactory.HUE_RED;
        this.shiftY = BitmapDescriptorFactory.HUE_RED;
    }

    @Override // com.cyberlink.you.fingerpaint.kernel.FingerPaintObject
    /* renamed from: i */
    public void mo15556i(float f9, float f10) {
        m15566m(f9, f10);
        if (this.f13559e == null) {
            this.f13559e = new Path();
        }
        this.f13559e.reset();
        this.f13559e.moveTo(f9, f10);
        this.f13557c = f9;
        this.f13558d = f10;
    }

    @Override // com.cyberlink.you.fingerpaint.kernel.FingerPaintObject
    /* renamed from: j */
    public void mo15564j(float[] fArr, float[] fArr2, float[] fArr3, boolean z8) {
        if (z8) {
            float[] fArr4 = this.totalShift;
            fArr4[0] = -fArr3[0];
            fArr4[1] = -fArr3[1];
        } else {
            float[] fArr5 = this.totalShift;
            fArr5[0] = fArr3[0];
            fArr5[1] = fArr3[1];
        }
        Path path = this.f13559e;
        float[] fArr6 = this.totalShift;
        path.offset(fArr6[0], fArr6[1]);
        m15572s();
        m15571r();
        m15570q();
    }

    @Override // com.cyberlink.you.fingerpaint.kernel.FingerPaintObject
    /* renamed from: k */
    public void mo15557k(float f9, float f10) {
        m15566m(f9, f10);
        Path path = this.f13559e;
        if (path != null) {
            float f11 = this.f13557c;
            float f12 = this.f13558d;
            path.quadTo(f11, f12, (f9 + f11) / 2.0f, (f10 + f12) / 2.0f);
            this.f13557c = f9;
            this.f13558d = f10;
        }
    }

    /* renamed from: m */
    public final void m15566m(float f9, float f10) {
        this.mPointList.add(new float[]{f9, f10});
        m15567n();
    }

    /* renamed from: n */
    public final void m15567n() {
        for (int i9 = 0; i9 < this.mPointList.size(); i9++) {
            float[] fArr = this.mPointList.get(i9);
            float f9 = this.borderLeft;
            if (f9 == BitmapDescriptorFactory.HUE_RED && this.borderTop == BitmapDescriptorFactory.HUE_RED && this.borderRight == BitmapDescriptorFactory.HUE_RED && this.borderBottom == BitmapDescriptorFactory.HUE_RED) {
                float f10 = fArr[0];
                this.borderLeft = f10;
                float f11 = fArr[1];
                this.borderTop = f11;
                this.borderRight = f10;
                this.borderBottom = f11;
            } else {
                float f12 = fArr[0];
                if (f12 < f9) {
                    this.borderLeft = f12;
                }
                float f13 = fArr[1];
                if (f13 < this.borderTop) {
                    this.borderTop = f13;
                }
                if (f12 > this.borderRight) {
                    this.borderRight = f12;
                }
                if (f13 > this.borderBottom) {
                    this.borderBottom = f13;
                }
            }
        }
    }

    /* renamed from: o */
    public final void m15568o() {
        if (this.mPointList == null) {
            return;
        }
        Path path = this.f13559e;
        if (path != null) {
            path.reset();
            this.f13559e = null;
        }
        this.f13559e = new Path();
        float f9 = BitmapDescriptorFactory.HUE_RED;
        float f10 = 0.0f;
        for (int i9 = 0; i9 < this.mPointList.size(); i9++) {
            float[] fArr = this.mPointList.get(i9);
            if (i9 == 0) {
                this.f13559e.moveTo(fArr[0], fArr[1]);
            } else {
                this.f13559e.quadTo(f9, f10, (fArr[0] + f9) / 2.0f, (fArr[1] + f10) / 2.0f);
            }
            f9 = fArr[0];
            f10 = fArr[1];
        }
        this.f13559e.lineTo(f9, f10);
    }

    /* renamed from: p */
    public final void m15569p() {
        ArrayList<float[]> arrayList = this.mPointList;
        if (arrayList != null) {
            arrayList.clear();
        }
        Path path = this.f13559e;
        if (path != null) {
            path.reset();
        }
    }

    /* renamed from: q */
    public final void m15570q() {
        for (int i9 = 0; i9 < 2; i9++) {
            this.totalShift[i9] = 0.0f;
        }
    }

    /* renamed from: r */
    public final void m15571r() {
        float f9 = this.borderLeft;
        float[] fArr = this.totalShift;
        float f10 = fArr[0];
        this.borderLeft = f9 + f10;
        float f11 = this.borderTop;
        float f12 = fArr[1];
        this.borderTop = f11 + f12;
        this.borderRight += f10;
        this.borderBottom += f12;
    }

    /* renamed from: s */
    public final void m15572s() {
        for (int i9 = 0; i9 < this.mPointList.size(); i9++) {
            float[] fArr = this.mPointList.get(i9);
            float f9 = fArr[0];
            float[] fArr2 = this.totalShift;
            fArr[0] = f9 + fArr2[0];
            fArr[1] = fArr[1] + fArr2[1];
        }
    }
}
