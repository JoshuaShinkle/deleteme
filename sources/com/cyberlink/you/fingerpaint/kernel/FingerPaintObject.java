package com.cyberlink.you.fingerpaint.kernel;

import android.graphics.Canvas;
import java.io.Serializable;

/* loaded from: classes.dex */
public abstract class FingerPaintObject implements Serializable {

    /* renamed from: b */
    public static final int[] f13556b = {-2818047, -833684, -1462768, -1513386, -11156201, -13057548, -15110676, -5958197, -1, -16777216};
    private static final long serialVersionUID = 0;
    protected int mColor;
    protected boolean mIsDelete;
    protected int mSelectedBorderColor;
    protected int mSelectedBorderSize;
    protected float mSize;
    protected int mType;

    public FingerPaintObject() {
        mo15560e();
    }

    /* renamed from: a */
    public abstract void mo15554a(Canvas canvas, float f9, float f10);

    /* renamed from: b */
    public abstract void mo15555b(float f9, float f10);

    /* renamed from: c */
    public int mo15558c() {
        return this.mColor;
    }

    /* renamed from: d */
    public float mo15559d() {
        return this.mSize;
    }

    /* renamed from: e */
    public void mo15560e() {
        this.mType = 0;
        this.mSize = 12.0f;
        this.mColor = f13556b[0];
        this.mSelectedBorderSize = 10;
        this.mSelectedBorderColor = -16776961;
        this.mIsDelete = false;
    }

    /* renamed from: f */
    public void m15561f(int i9) {
        this.mColor = i9;
    }

    /* renamed from: g */
    public void m15562g(float f9) {
        this.mSize = f9;
    }

    /* renamed from: h */
    public void m15563h(int i9) {
        this.mType = i9;
    }

    /* renamed from: i */
    public abstract void mo15556i(float f9, float f10);

    /* renamed from: j */
    public void mo15564j(float[] fArr, float[] fArr2, float[] fArr3, boolean z8) {
        mo15564j(fArr, fArr2, fArr3, z8);
    }

    /* renamed from: k */
    public abstract void mo15557k(float f9, float f10);

    /* renamed from: l */
    public void mo15565l(String str) {
        mo15565l(str);
    }
}
