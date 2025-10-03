package com.cyberlink.clgpuimage;

import android.graphics.PointF;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

/* loaded from: classes.dex */
public class CLMakeupLiveBlushFilter extends C0936w0 {

    /* renamed from: n */
    public static final float[] f4201n = {BitmapDescriptorFactory.HUE_RED, 1.0f, 1.0f, 1.0f, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, 1.0f, BitmapDescriptorFactory.HUE_RED};

    /* renamed from: o */
    public static final char[] f4202o = {250, 141, 141, 139, 242, 251, 142, 141, 254, 232, 143, 254, 252, 171, 232, 137, 136, 140, 242, 141, 242, 136, 247, 162, 250, 141, 141, 139, 242, 251, 142, 141, 254, 232, 143, 254, 252, 171, 232, 242, 247, 137, 142, 141, 237, 254, 129, 141, 142, 139, 254, 186, 136, 136, 139, 253, 242, 247, 250, 141, 254, 162, 250, 141, 141, 139, 242, 251, 142, 141, 254, 232, 143, 254, 252, 171, 232, 242, 247, 137, 142, 141, 237, 254, 246, 137, 245, 250, 141, 254, 237, 254, 129, 141, 142, 139, 254, 186, 136, 136, 139, 253, 242, 247, 250, 141, 254, 162, 143, 250, 139, 130, 242, 247, 240, 232, 143, 254, 252, 169, 232, 141, 254, 129, 141, 142, 139, 254, 186, 136, 136, 139, 253, 242, 247, 250, 141, 254, 162, 143, 250, 139, 130, 242, 247, 240, 232, 143, 254, 252, 169, 232, 181, 136, 235, 136, 141, 250, 141, 254, 186, 136, 136, 139, 253, 242, 247, 250, 141, 254, 162, 143, 250, 139, 130, 242, 247, 240, 232, 143, 254, 252, 169, 232, 179, 254, 255, 141, 185, 245, 142, 140, 241, 181, 136, 235, 136, 141, 250, 141, 254, 186, 136, 136, 139, 253, 242, 247, 250, 141, 254, 162, 143, 250, 139, 130, 242, 247, 240, 232, 143, 254, 252, 169, 232, 235, 242, 240, 241, 141, 185, 245, 142, 140, 241, 181, 136, 235, 136, 141, 250, 141, 254, 186, 136, 136, 139, 253, 242, 247, 250, 141, 254, 162, 142, 247, 242, 255, 136, 139, 246, 232, 143, 254, 252, 169, 232, 245, 254, 255, 141, 248, 251, 245, 142, 140, 241, 248, 139, 136, 242, 162, 142, 247, 242, 255, 136, 139, 246, 232, 143, 254, 252, 169, 232, 139, 242, 240, 241, 141, 248, 251, 245, 142, 140, 241, 248, 139, 136, 242, 162, 142, 247, 242, 255, 136, 139, 246, 232, 143, 254, 252, 169, 232, 245, 254, 255, 141, 248, 251, 245, 142, 140, 241, 248, 140, 141, 139, 254, 141, 252, 241, 162, 142, 247, 242, 255, 136, 139, 246, 232, 143, 254, 252, 169, 232, 139, 242, 240, 241, 141, 248, 251, 245, 142, 140, 241, 248, 140, 141, 139, 254, 141, 252, 241, 162, 142, 247, 242, 255, 136, 139, 246, 232, 143, 254, 252, 169, 232, 186, 136, 140, 248, 236, 242, 247, 162, 142, 247, 242, 255, 136, 139, 246, 232, 143, 254, 252, 169, 232, 247, 254, 240, 236, 242, 247, 248, 186, 136, 140, 162, 142, 247, 242, 255, 136, 139, 246, 232, 143, 254, 252, 169, 232, 235, 136, 141, 250, 141, 254, 186, 254, 247, 141, 254, 139, 162, 143, 136, 242, 253, 232, 246, 250, 242, 247, 159, 144, 132, 240, 245, 248, 233, 136, 140, 242, 141, 242, 136, 247, 232, 164, 232, 137, 136, 140, 242, 141, 242, 136, 247, 162, 141, 254, 129, 141, 142, 139, 254, 186, 136, 136, 139, 253, 242, 247, 250, 141, 254, 232, 164, 232, 242, 247, 137, 142, 141, 237, 254, 129, 141, 142, 139, 254, 186, 136, 136, 139, 253, 242, 247, 250, 141, 254, 149, 129, 130, 162, 143, 254, 252, 169, 232, 141, 246, 137, 232, 164, 232, 242, 247, 137, 142, 141, 237, 254, 246, 137, 245, 250, 141, 254, 237, 254, 129, 141, 142, 139, 254, 186, 136, 136, 139, 253, 242, 247, 250, 141, 254, 149, 129, 130, 232, 148, 232, 235, 136, 141, 250, 141, 254, 186, 254, 247, 141, 254, 139, 162, 181, 136, 235, 136, 141, 250, 141, 254, 186, 136, 136, 139, 253, 242, 247, 250, 141, 254, 232, 164, 232, 235, 136, 141, 250, 141, 254, 186, 254, 247, 141, 254, 139, 232, 146, 232, 143, 254, 252, 169, 159, 141, 246, 137, 149, 129, 147, 232, 141, 246, 137, 149, 129, 144, 145, 186, 136, 140, 248, 236, 242, 247, 232, 146, 232, 143, 254, 252, 169, 159, 141, 246, 137, 149, 130, 147, 232, 141, 246, 137, 149, 130, 144, 145, 247, 254, 240, 236, 242, 247, 248, 186, 136, 140, 162, 179, 254, 255, 141, 185, 245, 142, 140, 241, 181, 136, 235, 136, 141, 250, 141, 254, 186, 136, 136, 139, 253, 242, 247, 250, 141, 254, 232, 164, 232, 159, 181, 136, 235, 136, 141, 250, 141, 254, 186, 136, 136, 139, 253, 242, 247, 250, 141, 254, 232, 148, 232, 245, 254, 255, 141, 248, 251, 245, 142, 140, 241, 248, 139, 136, 242, 144, 145, 245, 254, 255, 141, 248, 251, 245, 142, 140, 241, 248, 140, 141, 139, 254, 141, 252, 241, 162, 235, 242, 240, 241, 141, 185, 245, 142, 140, 241, 181, 136, 235, 136, 141, 250, 141, 254, 186, 136, 136, 139, 253, 242, 247, 250, 141, 254, 232, 164, 232, 159, 181, 136, 235, 136, 141, 250, 141, 254, 186, 136, 136, 139, 253, 242, 247, 250, 141, 254, 232, 148, 232, 139, 242, 240, 241, 141, 248, 251, 245, 142, 140, 241, 248, 139, 136, 242, 144, 145, 139, 242, 240, 241, 141, 248, 251, 245, 142, 140, 241, 248, 140, 141, 139, 254, 141, 252, 241, 162, 179, 254, 255, 141, 185, 245, 142, 140, 241, 181, 136, 235, 136, 141, 250, 141, 254, 186, 136, 136, 139, 253, 242, 247, 250, 141, 254, 149, 130, 232, 164, 232, 168, 149, 151, 232, 148, 232, 179, 254, 255, 141, 185, 245, 142, 140, 241, 181, 136, 235, 136, 141, 250, 141, 254, 186, 136, 136, 139, 253, 242, 247, 250, 141, 254, 149, 130, 162, 235, 242, 240, 241, 141, 185, 245, 142, 140, 241, 181, 136, 235, 136, 141, 250, 141, 254, 186, 136, 136, 139, 253, 242, 247, 250, 141, 254, 149, 130, 232, 164, 232, 168, 149, 151, 232, 148, 232, 235, 242, 240, 241, 141, 185, 245, 142, 140, 241, 181, 136, 235, 136, 141, 250, 141, 254, 186, 136, 136, 139, 253, 242, 247, 250, 141, 254, 149, 130, 162, 134};

    /* renamed from: p */
    public static final char[] f4203p = {137, 139, 254, 252, 242, 140, 242, 136, 247, 232, 246, 254, 253, 242, 142, 246, 137, 232, 255, 245, 136, 250, 141, 162, 142, 247, 242, 255, 136, 139, 246, 232, 140, 250, 246, 137, 245, 254, 139, 169, 187, 232, 242, 247, 137, 142, 141, 176, 246, 250, 240, 254, 237, 254, 129, 141, 142, 139, 254, 162, 142, 247, 242, 255, 136, 139, 246, 232, 140, 250, 246, 137, 245, 254, 139, 169, 187, 232, 245, 254, 255, 141, 248, 251, 245, 142, 140, 241, 248, 141, 254, 129, 141, 142, 139, 254, 162, 142, 247, 242, 255, 136, 139, 246, 232, 140, 250, 246, 137, 245, 254, 139, 169, 187, 232, 139, 242, 240, 241, 141, 248, 251, 245, 142, 140, 241, 248, 141, 254, 129, 141, 142, 139, 254, 162, 143, 250, 139, 130, 242, 247, 240, 232, 143, 254, 252, 169, 232, 141, 254, 129, 141, 142, 139, 254, 186, 136, 136, 139, 253, 242, 247, 250, 141, 254, 162, 143, 250, 139, 130, 242, 247, 240, 232, 143, 254, 252, 169, 232, 181, 136, 235, 136, 141, 250, 141, 254, 186, 136, 136, 139, 253, 242, 247, 250, 141, 254, 162, 143, 250, 139, 130, 242, 247, 240, 232, 143, 254, 252, 169, 232, 179, 254, 255, 141, 185, 245, 142, 140, 241, 181, 136, 235, 136, 141, 250, 141, 254, 186, 136, 136, 139, 253, 242, 247, 250, 141, 254, 162, 143, 250, 139, 130, 242, 247, 240, 232, 143, 254, 252, 169, 232, 235, 242, 240, 241, 141, 185, 245, 142, 140, 241, 181, 136, 235, 136, 141, 250, 141, 254, 186, 136, 136, 139, 253, 242, 247, 250, 141, 254, 162, 142, 247, 242, 255, 136, 139, 246, 232, 255, 245, 136, 250, 141, 232, 251, 245, 142, 140, 241, 248, 140, 141, 139, 254, 247, 240, 141, 241, 162, 142, 247, 242, 255, 136, 139, 246, 232, 143, 254, 252, 170, 232, 251, 245, 142, 140, 241, 248, 252, 136, 245, 136, 139, 162, 142, 247, 242, 255, 136, 139, 246, 232, 255, 245, 136, 250, 141, 232, 180, 242, 253, 248, 225, 248, 136, 255, 248, 245, 254, 255, 141, 248, 139, 242, 240, 241, 141, 162, 143, 136, 242, 253, 232, 246, 250, 242, 247, 159, 144, 132, 255, 245, 136, 250, 141, 232, 250, 245, 137, 241, 250, 248, 245, 254, 255, 141, 232, 164, 232, 141, 254, 129, 141, 142, 139, 254, 169, 187, 159, 245, 254, 255, 141, 248, 251, 245, 142, 140, 241, 248, 141, 254, 129, 141, 142, 139, 254, 147, 232, 179, 254, 255, 141, 185, 245, 142, 140, 241, 181, 136, 235, 136, 141, 250, 141, 254, 186, 136, 136, 139, 253, 242, 247, 250, 141, 254, 144, 149, 250, 162, 255, 245, 136, 250, 141, 232, 250, 245, 137, 241, 250, 248, 139, 242, 240, 241, 141, 232, 164, 232, 141, 254, 129, 141, 142, 139, 254, 169, 187, 159, 139, 242, 240, 241, 141, 248, 251, 245, 142, 140, 241, 248, 141, 254, 129, 141, 142, 139, 254, 147, 232, 235, 242, 240, 241, 141, 185, 245, 142, 140, 241, 181, 136, 235, 136, 141, 250, 141, 254, 186, 136, 136, 139, 253, 242, 247, 250, 141, 254, 144, 149, 250, 162, 255, 245, 136, 250, 141, 232, 250, 245, 137, 241, 250, 232, 164, 232, 251, 245, 142, 140, 241, 248, 140, 141, 139, 254, 247, 240, 141, 241, 232, 145, 232, 246, 242, 129, 159, 250, 245, 137, 241, 250, 248, 245, 254, 255, 141, 147, 232, 250, 245, 137, 241, 250, 248, 139, 242, 240, 241, 141, 147, 232, 140, 141, 254, 137, 159, 180, 242, 253, 248, 225, 248, 136, 255, 248, 245, 254, 255, 141, 248, 139, 242, 240, 241, 141, 147, 232, 181, 136, 235, 136, 141, 250, 141, 254, 186, 136, 136, 139, 253, 242, 247, 250, 141, 254, 149, 129, 144, 144, 162, 143, 254, 252, 170, 232, 252, 136, 245, 136, 139, 232, 164, 232, 246, 242, 129, 159, 141, 254, 129, 141, 142, 139, 254, 169, 187, 159, 242, 247, 137, 142, 141, 176, 246, 250, 240, 254, 237, 254, 129, 141, 142, 139, 254, 147, 232, 141, 254, 129, 141, 142, 139, 254, 186, 136, 136, 139, 253, 242, 247, 250, 141, 254, 144, 149, 139, 240, 251, 147, 232, 251, 245, 142, 140, 241, 248, 252, 136, 245, 136, 139, 147, 232, 250, 245, 137, 241, 250, 144, 162, 240, 245, 248, 189, 139, 250, 240, 186, 136, 245, 136, 139, 232, 164, 232, 143, 254, 252, 171, 159, 252, 136, 245, 136, 139, 147, 232, 168, 149, 151, 144, 162, 134};

    public static class LiveBlushMakeupdata {
        public float m_cos_val;
        public float m_environment_brightest_reference_normalized_luma;
        public float m_environment_darkest_reference_normalized_luma;
        public boolean m_is_flipped;
        public int m_rotation;
        public float m_sin_val;
        public PointF m_center = new PointF();
        public C0868a m_left_blush_roi = new C0868a();
        public C0868a m_right_blush_roi = new C0868a();

        public void ComputeData(PointF pointF, PointF pointF2, PointF pointF3, PointF pointF4, PointF pointF5, PointF pointF6, PointF pointF7, PointF pointF8, PointF pointF9, PointF pointF10) {
            double d9 = -Math.atan2(pointF10.y - pointF5.y, pointF10.x - pointF5.x);
            this.m_cos_val = (float) Math.cos(d9);
            this.m_sin_val = (float) Math.sin(d9);
            PointF pointF11 = this.m_center;
            pointF11.x = (pointF2.x + pointF7.x) * 0.5f;
            pointF11.y = (pointF2.y + pointF7.y) * 0.5f;
            this.m_left_blush_roi.m4137a();
            this.m_left_blush_roi.m4138b(CLMakeupLiveBlushFilter.m4135A(this.m_cos_val, this.m_sin_val, this.m_center, pointF));
            this.m_left_blush_roi.m4138b(CLMakeupLiveBlushFilter.m4135A(this.m_cos_val, this.m_sin_val, this.m_center, pointF2));
            this.m_left_blush_roi.m4138b(CLMakeupLiveBlushFilter.m4135A(this.m_cos_val, this.m_sin_val, this.m_center, pointF3));
            this.m_left_blush_roi.m4138b(CLMakeupLiveBlushFilter.m4135A(this.m_cos_val, this.m_sin_val, this.m_center, pointF4));
            this.m_left_blush_roi.m4138b(CLMakeupLiveBlushFilter.m4135A(this.m_cos_val, this.m_sin_val, this.m_center, pointF5));
            this.m_right_blush_roi.m4137a();
            this.m_right_blush_roi.m4138b(CLMakeupLiveBlushFilter.m4135A(this.m_cos_val, this.m_sin_val, this.m_center, pointF6));
            this.m_right_blush_roi.m4138b(CLMakeupLiveBlushFilter.m4135A(this.m_cos_val, this.m_sin_val, this.m_center, pointF7));
            this.m_right_blush_roi.m4138b(CLMakeupLiveBlushFilter.m4135A(this.m_cos_val, this.m_sin_val, this.m_center, pointF8));
            this.m_right_blush_roi.m4138b(CLMakeupLiveBlushFilter.m4135A(this.m_cos_val, this.m_sin_val, this.m_center, pointF9));
            this.m_right_blush_roi.m4138b(CLMakeupLiveBlushFilter.m4135A(this.m_cos_val, this.m_sin_val, this.m_center, pointF10));
        }

        public void Copy(LiveBlushMakeupdata liveBlushMakeupdata) {
            this.m_cos_val = liveBlushMakeupdata.m_cos_val;
            this.m_sin_val = liveBlushMakeupdata.m_sin_val;
            PointF pointF = this.m_center;
            PointF pointF2 = liveBlushMakeupdata.m_center;
            pointF.x = pointF2.x;
            pointF.y = pointF2.y;
            C0868a c0868a = this.m_left_blush_roi;
            C0868a c0868a2 = liveBlushMakeupdata.m_left_blush_roi;
            c0868a.f4204a = c0868a2.f4204a;
            c0868a.f4206c = c0868a2.f4206c;
            c0868a.f4205b = c0868a2.f4205b;
            c0868a.f4207d = c0868a2.f4207d;
            C0868a c0868a3 = this.m_right_blush_roi;
            C0868a c0868a4 = liveBlushMakeupdata.m_right_blush_roi;
            c0868a3.f4204a = c0868a4.f4204a;
            c0868a3.f4206c = c0868a4.f4206c;
            c0868a3.f4205b = c0868a4.f4205b;
            c0868a3.f4207d = c0868a4.f4207d;
            this.m_environment_darkest_reference_normalized_luma = liveBlushMakeupdata.m_environment_darkest_reference_normalized_luma;
            this.m_environment_brightest_reference_normalized_luma = liveBlushMakeupdata.m_environment_brightest_reference_normalized_luma;
            this.m_rotation = liveBlushMakeupdata.m_rotation;
            this.m_is_flipped = liveBlushMakeupdata.m_is_flipped;
        }

        public void SetBlushFeaturePts(PointF pointF, PointF pointF2, PointF pointF3, PointF pointF4, PointF pointF5, PointF pointF6, PointF pointF7, PointF pointF8, PointF pointF9, PointF pointF10) {
            ComputeData(CLMakeupLiveBlushFilter.m4136z(pointF), CLMakeupLiveBlushFilter.m4136z(pointF2), CLMakeupLiveBlushFilter.m4136z(pointF3), CLMakeupLiveBlushFilter.m4136z(pointF4), CLMakeupLiveBlushFilter.m4136z(pointF5), CLMakeupLiveBlushFilter.m4136z(pointF6), CLMakeupLiveBlushFilter.m4136z(pointF7), CLMakeupLiveBlushFilter.m4136z(pointF8), CLMakeupLiveBlushFilter.m4136z(pointF9), CLMakeupLiveBlushFilter.m4136z(pointF10));
        }
    }

    /* renamed from: com.cyberlink.clgpuimage.CLMakeupLiveBlushFilter$a */
    public static class C0868a {

        /* renamed from: a */
        public float f4204a;

        /* renamed from: b */
        public float f4205b;

        /* renamed from: c */
        public float f4206c;

        /* renamed from: d */
        public float f4207d;

        /* renamed from: a */
        public void m4137a() {
            this.f4204a = Float.MAX_VALUE;
            this.f4205b = Float.MAX_VALUE;
            this.f4206c = Float.MIN_VALUE;
            this.f4207d = Float.MIN_VALUE;
        }

        /* renamed from: b */
        public void m4138b(PointF pointF) {
            this.f4204a = Math.min(this.f4204a, pointF.x);
            this.f4205b = Math.min(this.f4205b, pointF.y);
            this.f4206c = Math.max(this.f4206c, pointF.x);
            this.f4207d = Math.max(this.f4207d, pointF.y);
        }
    }

    /* renamed from: A */
    public static PointF m4135A(float f9, float f10, PointF pointF, PointF pointF2) {
        PointF pointF3 = new PointF();
        float f11 = pointF.x;
        float f12 = f11 + ((pointF2.x - f11) * f9);
        float f13 = pointF2.y;
        float f14 = pointF.y;
        pointF3.x = f12 + ((f13 - f14) * (-f10));
        pointF3.y = ((pointF2.x - pointF.x) * f10) + f14 + ((f13 - f14) * f9);
        return pointF3;
    }

    /* renamed from: z */
    public static PointF m4136z(PointF pointF) {
        return new PointF(pointF.x, 1.0f - pointF.y);
    }
}
