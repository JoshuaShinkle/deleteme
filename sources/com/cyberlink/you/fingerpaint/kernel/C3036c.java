package com.cyberlink.you.fingerpaint.kernel;

import android.graphics.Canvas;
import android.util.Log;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.util.ArrayList;

/* renamed from: com.cyberlink.you.fingerpaint.kernel.c */
/* loaded from: classes.dex */
public class C3036c {

    /* renamed from: m */
    public static C3036c f13564m = new C3036c();

    /* renamed from: a */
    public ArrayList<FingerPaintObject> f13565a = new ArrayList<>(0);

    /* renamed from: b */
    public ArrayList<FingerPaintObject> f13566b = new ArrayList<>(0);

    /* renamed from: c */
    public ArrayList<AbstractC3034a> f13567c = new ArrayList<>(0);

    /* renamed from: d */
    public FingerPaintObject f13568d = null;

    /* renamed from: e */
    public int f13569e = 0;

    /* renamed from: f */
    public int f13570f = 0;

    /* renamed from: g */
    public int f13571g = 1;

    /* renamed from: h */
    public int f13572h = FingerPaintObject.f13556b[0];

    /* renamed from: i */
    public float f13573i = 12.0f;

    /* renamed from: j */
    public boolean f13574j = false;

    /* renamed from: k */
    public int f13575k = -1;

    /* renamed from: l */
    public FingerPaintObject f13576l = null;

    /* renamed from: m */
    public static C3036c m15579m() {
        return f13564m;
    }

    /* renamed from: a */
    public boolean m15580a() {
        return this.f13567c.size() > 0 && this.f13575k >= 0;
    }

    /* renamed from: b */
    public void m15581b() {
        Log.d("FingerPaintMgr", "clearEdit");
        this.f13566b.clear();
        m15594p();
        this.f13567c.clear();
        this.f13575k = -1;
        this.f13574j = this.f13565a.size() > 0;
    }

    /* renamed from: c */
    public final FingerPaintObject m15582c(int i9) {
        FingerPaintObject fingerPaintPath = (i9 == 1 || i9 == 2) ? new FingerPaintPath() : i9 == 3 ? new FingerPaintArrow() : i9 == 4 ? new FingerPaintRectangle() : i9 == 5 ? new FingerPaintText() : null;
        if (fingerPaintPath != null) {
            fingerPaintPath.mo15560e();
            fingerPaintPath.m15563h(i9);
            fingerPaintPath.m15561f(this.f13572h);
            fingerPaintPath.m15562g(this.f13573i);
        }
        return fingerPaintPath;
    }

    /* renamed from: d */
    public void m15583d(Canvas canvas, int i9) {
        int width;
        int height;
        float f9;
        float f10;
        int i10;
        Log.d("FingerPaintMgr", "drawAllObject start");
        canvas.save();
        if (i9 == 90 || i9 == 270) {
            int height2 = canvas.getHeight();
            int width2 = canvas.getWidth();
            canvas.rotate(-i9);
            if (i9 == 90) {
                canvas.translate(-canvas.getHeight(), BitmapDescriptorFactory.HUE_RED);
            } else {
                canvas.translate(BitmapDescriptorFactory.HUE_RED, -canvas.getWidth());
            }
            width = height2;
            height = width2;
        } else {
            width = canvas.getWidth();
            height = canvas.getHeight();
            if (i9 == 180) {
                canvas.rotate(-i9);
                canvas.translate(-canvas.getWidth(), -canvas.getHeight());
            }
        }
        int i11 = this.f13569e;
        if (i11 == 0 || (i10 = this.f13570f) == 0) {
            f9 = 1.0f;
            f10 = 1.0f;
        } else {
            f9 = width / i11;
            f10 = height / i10;
            Log.d("FingerPaintMgr", "original = (" + this.f13569e + "," + this.f13570f + ")");
            Log.d("FingerPaintMgr", "target = (" + width + "," + height + ")");
            Log.d("FingerPaintMgr", "scale = (" + f9 + "," + f10 + ")");
            canvas.scale(f9, f10);
        }
        for (int i12 = 0; i12 < this.f13566b.size(); i12++) {
            FingerPaintObject fingerPaintObject = this.f13566b.get(i12);
            if (fingerPaintObject != null && !fingerPaintObject.mIsDelete) {
                fingerPaintObject.mo15554a(canvas, Math.min(f9, f10), BitmapDescriptorFactory.HUE_RED);
            }
        }
        canvas.restore();
        Log.d("FingerPaintMgr", "drawAllObject end");
    }

    /* renamed from: e */
    public void m15584e(Canvas canvas) {
        if (this.f13568d != null) {
            Log.d("FingerPaintMgr", "drawCurObject");
            this.f13568d.mo15554a(canvas, 1.0f, BitmapDescriptorFactory.HUE_RED);
        }
    }

    /* renamed from: f */
    public void m15585f(float f9, float f10) {
        if (this.f13568d != null) {
            Log.d("FingerPaintMgr", "endCurObject");
            this.f13568d.mo15555b(f9, f10);
            m15586g(new C3035b(this.f13568d));
            this.f13574j = true;
        }
    }

    /* renamed from: g */
    public void m15586g(AbstractC3034a abstractC3034a) {
        if (abstractC3034a == null) {
            return;
        }
        Log.d("FingerPaintMgr", "executeAction start, index = " + this.f13575k + ", queue size = " + this.f13567c.size());
        abstractC3034a.mo15574a(this.f13566b);
        while (this.f13575k + 1 < this.f13567c.size()) {
            this.f13567c.remove(this.f13575k + 1);
        }
        this.f13567c.add(abstractC3034a);
        this.f13575k++;
        Log.d("FingerPaintMgr", "executeAction end, index = " + this.f13575k + ", queue size = " + this.f13567c.size());
    }

    /* renamed from: h */
    public int[] m15587h() {
        return new int[]{this.f13569e, this.f13570f};
    }

    /* renamed from: i */
    public int m15588i() {
        return this.f13572h;
    }

    /* renamed from: j */
    public int m15589j() {
        int i9 = this.f13572h;
        if (this.f13566b.size() <= 0) {
            return i9;
        }
        for (int i10 = 0; i10 < this.f13565a.size(); i10++) {
            FingerPaintObject fingerPaintObject = this.f13565a.get(i10);
            if (fingerPaintObject != null && !fingerPaintObject.mIsDelete) {
                return fingerPaintObject.mo15558c();
            }
        }
        return i9;
    }

    /* renamed from: k */
    public float m15590k() {
        return this.f13573i;
    }

    /* renamed from: l */
    public int m15591l() {
        return this.f13571g;
    }

    /* renamed from: n */
    public boolean m15592n() {
        return this.f13574j;
    }

    /* renamed from: o */
    public void m15593o() {
        this.f13565a.clear();
        this.f13566b.clear();
        this.f13567c.clear();
        m15594p();
        this.f13569e = 0;
        this.f13570f = 0;
        this.f13574j = false;
        this.f13575k = -1;
    }

    /* renamed from: p */
    public void m15594p() {
        Log.d("FingerPaintMgr", "resetCurObject");
        this.f13568d = null;
    }

    /* renamed from: q */
    public void m15595q() {
        Log.d("FingerPaintMgr", "restoreEdit");
        this.f13566b.clear();
        this.f13566b.addAll(this.f13565a);
        m15594p();
        this.f13567c.clear();
        this.f13575k = -1;
        this.f13574j = false;
    }

    /* renamed from: r */
    public void m15596r() {
        Log.d("FingerPaintMgr", "saveEdit");
        this.f13565a.clear();
        this.f13565a.addAll(this.f13566b);
        this.f13574j = false;
    }

    /* renamed from: s */
    public void m15597s(int i9, int i10) {
        this.f13569e = i9;
        this.f13570f = i10;
    }

    /* renamed from: t */
    public void m15598t(int i9) {
        this.f13572h = i9;
    }

    /* renamed from: u */
    public void m15599u(float f9) {
        this.f13573i = f9;
    }

    /* renamed from: v */
    public void m15600v(int i9) {
        this.f13571g = i9;
    }

    /* renamed from: w */
    public void m15601w(float f9, float f10, String str) {
        FingerPaintObject fingerPaintObjectM15582c = m15582c(this.f13571g);
        this.f13568d = fingerPaintObjectM15582c;
        if (fingerPaintObjectM15582c != null) {
            if (this.f13571g == 5) {
                fingerPaintObjectM15582c.mo15556i(f9, f10);
                this.f13568d.mo15565l(str);
            } else {
                Log.d("FingerPaintMgr", "startNewObject");
                this.f13568d.mo15556i(f9, f10);
            }
        }
    }

    /* renamed from: x */
    public void m15602x() {
        Log.d("FingerPaintMgr", "mCurActionIndex: " + this.f13575k);
        int i9 = this.f13575k;
        if (i9 < 0 || i9 >= this.f13567c.size()) {
            Log.d("FingerPaintMgr", "Undo out of bounds! index = " + this.f13575k + ", size = " + this.f13567c.size());
            return;
        }
        AbstractC3034a abstractC3034a = this.f13567c.get(this.f13575k);
        if (abstractC3034a != null) {
            FingerPaintObject fingerPaintObject = abstractC3034a.f13560a;
            float[] fArrMo15577d = abstractC3034a.mo15577d();
            float[] fArrMo15575b = abstractC3034a.mo15575b();
            float[] fArrMo15576c = abstractC3034a.mo15576c();
            if (fArrMo15576c[0] != BitmapDescriptorFactory.HUE_RED && fArrMo15576c[1] != BitmapDescriptorFactory.HUE_RED) {
                fingerPaintObject.mo15564j(fArrMo15577d, fArrMo15575b, fArrMo15576c, true);
            }
            abstractC3034a.mo15578e(this.f13566b);
            this.f13575k--;
        }
    }

    /* renamed from: y */
    public void m15603y(float f9, float f10) {
        if (this.f13568d != null) {
            Log.d("FingerPaintMgr", "updateCurObject");
            this.f13568d.mo15557k(f9, f10);
        }
    }
}
