package com.cyberlink.media.video;

import android.annotation.TargetApi;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.Layout;
import android.util.DisplayMetrics;
import android.widget.TextView;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

/* renamed from: com.cyberlink.media.video.d */
/* loaded from: classes.dex */
public final class C1240d {

    /* renamed from: p */
    public static final DisplayMetrics f6006p;

    /* renamed from: q */
    public static final C1240d f6007q;

    /* renamed from: a */
    public final Paint f6008a;

    /* renamed from: b */
    public final int f6009b;

    /* renamed from: c */
    public final Layout.Alignment f6010c;

    /* renamed from: d */
    public final float f6011d;

    /* renamed from: e */
    public final float f6012e;

    /* renamed from: f */
    public final float f6013f;

    /* renamed from: g */
    public final float f6014g;

    /* renamed from: h */
    public final float f6015h;

    /* renamed from: i */
    public final int f6016i;

    /* renamed from: j */
    public final int f6017j;

    /* renamed from: k */
    public final float f6018k;

    /* renamed from: l */
    public final float f6019l;

    /* renamed from: m */
    public final float f6020m;

    /* renamed from: n */
    public final int f6021n;

    /* renamed from: o */
    public final double f6022o;

    /* renamed from: com.cyberlink.media.video.d$b */
    public static class b {

        /* renamed from: a */
        public Paint f6023a;

        /* renamed from: b */
        public int f6024b;

        /* renamed from: c */
        public Layout.Alignment f6025c;

        /* renamed from: d */
        public float f6026d;

        /* renamed from: e */
        public float f6027e;

        /* renamed from: f */
        public float f6028f;

        /* renamed from: g */
        public float f6029g;

        /* renamed from: h */
        public float f6030h;

        /* renamed from: i */
        public int f6031i;

        /* renamed from: j */
        public int f6032j;

        /* renamed from: k */
        public float f6033k;

        /* renamed from: l */
        public float f6034l;

        /* renamed from: m */
        public float f6035m;

        /* renamed from: n */
        public int f6036n;

        /* renamed from: o */
        public double f6037o;

        public b() {
            Paint paint = new Paint();
            this.f6023a = paint;
            this.f6024b = 81;
            this.f6025c = Layout.Alignment.ALIGN_CENTER;
            this.f6031i = -1;
            this.f6032j = -16777216;
            this.f6036n = -16777216;
            this.f6037o = 1.0d;
            paint.setAntiAlias(true);
            this.f6023a.setStrokeJoin(Paint.Join.BEVEL);
        }

        /* renamed from: p */
        public C1240d m5476p() {
            return new C1240d(this);
        }

        @TargetApi(16)
        /* renamed from: q */
        public final void m5477q(TextView textView) {
            m5482v(textView.getShadowRadius(), textView.getShadowDx(), textView.getShadowDy(), textView.getShadowColor());
        }

        /* renamed from: r */
        public b m5478r(TextView textView) {
            m5477q(textView);
            return m5484x(textView.getTextSize()).m5479s(textView.getTextColors().getDefaultColor()).m5486z(textView.getTypeface()).m5481u(textView.getPaddingLeft(), textView.getPaddingTop(), textView.getPaddingRight(), textView.getPaddingBottom()).m5480t(textView.getGravity());
        }

        /* renamed from: s */
        public b m5479s(int i9) {
            this.f6031i = i9;
            return this;
        }

        /* renamed from: t */
        public b m5480t(int i9) {
            this.f6024b = i9 & 119;
            return this;
        }

        /* renamed from: u */
        public b m5481u(float f9, float f10, float f11, float f12) {
            this.f6026d = f9;
            this.f6027e = f10;
            this.f6028f = f11;
            this.f6029g = f12;
            return this;
        }

        /* renamed from: v */
        public b m5482v(float f9, float f10, float f11, int i9) {
            this.f6033k = f9;
            this.f6034l = f10;
            this.f6035m = f11;
            this.f6036n = i9;
            return this;
        }

        /* renamed from: w */
        public b m5483w(float f9) {
            this.f6023a.setStrokeWidth(f9);
            return this;
        }

        /* renamed from: x */
        public b m5484x(float f9) {
            this.f6023a.setTextSize(f9);
            return this;
        }

        /* renamed from: y */
        public b m5485y() {
            return m5481u(BitmapDescriptorFactory.HUE_RED, C1240d.f6006p.density * 14.0f, BitmapDescriptorFactory.HUE_RED, C1240d.f6006p.density * 14.0f).m5484x(C1240d.f6006p.scaledDensity * 24.0f).m5483w(C1240d.f6006p.density * 5.0f).m5482v(5.0f, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, this.f6032j);
        }

        /* renamed from: z */
        public b m5486z(Typeface typeface) {
            this.f6023a.setTypeface(typeface);
            return this;
        }
    }

    static {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        f6006p = displayMetrics;
        displayMetrics.setToDefaults();
        f6007q = new b().m5485y().m5476p();
    }

    public C1240d(b bVar) {
        this.f6008a = new Paint(bVar.f6023a);
        this.f6009b = bVar.f6024b;
        this.f6010c = bVar.f6025c;
        this.f6011d = bVar.f6026d;
        this.f6012e = bVar.f6027e;
        this.f6013f = bVar.f6028f;
        this.f6014g = bVar.f6029g;
        this.f6015h = bVar.f6030h;
        this.f6016i = bVar.f6031i;
        this.f6017j = bVar.f6032j;
        this.f6018k = bVar.f6033k;
        this.f6019l = bVar.f6034l;
        this.f6020m = bVar.f6035m;
        this.f6021n = bVar.f6036n;
        this.f6022o = bVar.f6037o;
    }
}
