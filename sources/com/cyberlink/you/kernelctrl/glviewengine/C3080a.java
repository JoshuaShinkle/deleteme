package com.cyberlink.you.kernelctrl.glviewengine;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.util.Log;
import com.cyberlink.clgpuimage.C0872a;
import com.cyberlink.clgpuimage.C0874a1;
import com.cyberlink.clgpuimage.C0877b1;
import com.cyberlink.clgpuimage.C0878c;
import com.cyberlink.clgpuimage.C0880c1;
import com.cyberlink.clgpuimage.C0886e1;
import com.cyberlink.clgpuimage.C0889f1;
import com.cyberlink.clgpuimage.C0890g;
import com.cyberlink.clgpuimage.C0892g1;
import com.cyberlink.clgpuimage.C0893h;
import com.cyberlink.clgpuimage.C0896i;
import com.cyberlink.clgpuimage.C0898i1;
import com.cyberlink.clgpuimage.C0901j1;
import com.cyberlink.clgpuimage.C0904k1;
import com.cyberlink.clgpuimage.C0907l1;
import com.cyberlink.clgpuimage.C0916o1;
import com.cyberlink.clgpuimage.C0919p1;
import com.cyberlink.clgpuimage.C0922q1;
import com.cyberlink.clgpuimage.C0924r0;
import com.cyberlink.clgpuimage.C0927s0;
import com.cyberlink.clgpuimage.C0930t0;
import com.cyberlink.clgpuimage.C0932u0;
import com.cyberlink.clgpuimage.C0934v0;
import com.cyberlink.clgpuimage.C0936w0;
import com.cyberlink.clgpuimage.C0938x0;
import com.cyberlink.clgpuimage.C0940y0;
import com.cyberlink.clgpuimage.C0942z0;
import com.cyberlink.clgpuimage.CLBlendModesFilter;
import com.cyberlink.clgpuimage.CLBokehEffectFilter;
import com.cyberlink.clgpuimage.CLFocusEffectFilter;
import com.cyberlink.clgpuimage.IBeautyFilter2;
import com.cyberlink.you.Globals;
import com.cyberlink.you.kernelctrl.dataeditcenter.DevelopSetting;
import com.cyberlink.you.kernelctrl.glviewengine.GLViewEngine;
import com.cyberlink.you.kernelctrl.gpuimage.CLLensFlareFilter;
import com.cyberlink.you.kernelctrl.gpuimage.GPUImagePanZoomFilter;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import p228w3.C6519a;
import p228w3.C6520b;
import p228w3.C6521c;
import p228w3.C6522d;
import p228w3.C6523e;
import p228w3.C6524f;
import p228w3.C6525g;
import p228w3.C6526h;
import p228w3.C6527i;
import p228w3.C6528j;
import p228w3.C6529k;
import p228w3.C6530l;
import p228w3.C6531m;
import p228w3.C6532n;
import p228w3.C6533o;
import p228w3.C6534p;
import p228w3.C6535q;
import p228w3.C6536r;
import p228w3.C6537s;
import p228w3.C6538t;
import p228w3.C6539u;
import p228w3.C6540v;
import p228w3.C6542x;
import p228w3.C6543y;
import p237x3.C6567a;
import p237x3.C6568b;

/* renamed from: com.cyberlink.you.kernelctrl.glviewengine.a */
/* loaded from: classes.dex */
public class C3080a {

    /* renamed from: j */
    public static Map<DevelopSetting.GPUImageFilterParamType, IBeautyFilter2.EffectMode> f13980j;

    /* renamed from: a */
    public String f13981a = "GPUImageFilterBuilder";

    /* renamed from: b */
    public final C0936w0 f13982b = new C0936w0();

    /* renamed from: c */
    public C0938x0 f13983c = null;

    /* renamed from: d */
    public DevelopSetting f13984d = null;

    /* renamed from: e */
    public IBeautyFilter2 f13985e = null;

    /* renamed from: f */
    public DevelopSetting f13986f = null;

    /* renamed from: g */
    public C0938x0 f13987g = null;

    /* renamed from: h */
    public C0936w0 f13988h = null;

    /* renamed from: i */
    public GPUImagePanZoomFilter f13989i = null;

    static {
        HashMap map = new HashMap();
        f13980j = map;
        map.put(DevelopSetting.GPUImageFilterParamType.CLCandyColor, IBeautyFilter2.EffectMode.PORTRAIT_CANDY);
        f13980j.put(DevelopSetting.GPUImageFilterParamType.CLAestheticColor, IBeautyFilter2.EffectMode.PORTRAIT_AESTHETIC);
        f13980j.put(DevelopSetting.GPUImageFilterParamType.CLGentleColor, IBeautyFilter2.EffectMode.PORTRAIT_GENTLE);
        f13980j.put(DevelopSetting.GPUImageFilterParamType.CLCoolColor, IBeautyFilter2.EffectMode.PORTRAIT_COOL);
        f13980j.put(DevelopSetting.GPUImageFilterParamType.CLRetroColor, IBeautyFilter2.EffectMode.PORTRAIT_RETRO);
        f13980j.put(DevelopSetting.GPUImageFilterParamType.CLForestColor, IBeautyFilter2.EffectMode.PORTRAIT_FOREST);
        f13980j.put(DevelopSetting.GPUImageFilterParamType.CLFreshColor, IBeautyFilter2.EffectMode.PORTRAIT_FRESH);
        f13980j.put(DevelopSetting.GPUImageFilterParamType.CLElegantColor, IBeautyFilter2.EffectMode.PORTRAIT_ELEGANT);
        f13980j.put(DevelopSetting.GPUImageFilterParamType.CLSoftlightColor, IBeautyFilter2.EffectMode.PORTRAIT_SOFTLIGHT);
        f13980j.put(DevelopSetting.GPUImageFilterParamType.CLVintageColor, IBeautyFilter2.EffectMode.PORTRAIT_VINTAGE);
        f13980j.put(DevelopSetting.GPUImageFilterParamType.CLBlackWhiteColor, IBeautyFilter2.EffectMode.PORTRAIT_BLACK_WHITE);
        f13980j.put(DevelopSetting.GPUImageFilterParamType.CLRedColor, IBeautyFilter2.EffectMode.PORTRAIT_RED);
        f13980j.put(DevelopSetting.GPUImageFilterParamType.CLWarmColor, IBeautyFilter2.EffectMode.PORTRAIT_WARM);
        f13980j.put(DevelopSetting.GPUImageFilterParamType.CLLightColor, IBeautyFilter2.EffectMode.PORTRAIT_LIGHT);
    }

    public C3080a() {
        Log.i(this.f13981a, "GPUImageFilterBuilder has been created, " + this);
    }

    /* renamed from: a */
    public C0936w0 m15991a(GLViewEngine.EffectParam effectParam) {
        DevelopSetting developSetting = effectParam.f13950a;
        if (this.f13986f == developSetting) {
            m15997g(effectParam, this.f13987g);
            this.f13987g.m4424R();
            return this.f13987g;
        }
        C0938x0 c0938x0 = new C0938x0(true, true);
        C6527i c6527i = (C6527i) developSetting.m15963c(DevelopSetting.GPUImageFilterParamType.CLSmooth);
        if (c6527i != null) {
            Log.d(this.f13981a, "create new filter (CLSmoothFilter)");
            C0896i c0896i = new C0896i(c6527i.m24996b());
            c0896i.m4342J(70.0f);
            c0938x0.m4417K(c0896i);
        }
        C0893h c0893h = new C0893h();
        c0893h.m4292C(-100.0f);
        c0893h.m4294E(40.0f);
        c0893h.m4293D(2.0f);
        c0938x0.m4417K(c0893h);
        c0938x0.m4243B(BitmapDescriptorFactory.HUE_RED);
        c0938x0.m4358A(effectParam.f13953d, effectParam.f13954e, true ^ effectParam.f13955f);
        m15997g(effectParam, c0938x0);
        this.f13986f = developSetting;
        this.f13987g = c0938x0;
        return c0938x0;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v5, types: [com.cyberlink.clgpuimage.a] */
    /* renamed from: b */
    public C0936w0 m15992b(GLViewEngine.EffectParam effectParam) {
        C6527i c6527i;
        Log.d(this.f13981a, "getBeautyFilter");
        DevelopSetting developSetting = effectParam.f13950a;
        float fMin = Math.min(Math.max(BitmapDescriptorFactory.HUE_RED, (float) (effectParam.f13951b * 100.0d)), 100.0f);
        if (this.f13985e != null && this.f13984d == developSetting) {
            Log.d(this.f13981a, "reuse old filter");
            this.f13985e.mo4179b(fMin);
            this.f13985e.mo4178a(fMin);
            return (C0936w0) this.f13985e;
        }
        C6527i c6527i2 = (C6527i) developSetting.m15963c(DevelopSetting.GPUImageFilterParamType.CLAphroditeColorFilter);
        if (c6527i2 == null) {
            Iterator<DevelopSetting.GPUImageFilterParamType> it = f13980j.keySet().iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                DevelopSetting.GPUImageFilterParamType next = it.next();
                C6527i c6527i3 = (C6527i) developSetting.m15963c(next);
                if (c6527i3 != null) {
                    Log.d(this.f13981a, "create new filter (CLAphroditeColorFilter) for " + next.toString());
                    c6527i2 = c6527i3;
                    break;
                }
                c6527i2 = c6527i3;
            }
        } else {
            Log.d(this.f13981a, "create new filter (CLAphroditeColorFilter) for CLAphroditeColorFilter");
        }
        C0896i c0872a = c6527i2 != null ? new C0872a(c6527i2.m24996b(), Globals.m7388i0().getApplicationContext().getAssets(), c6527i2.m24995a()) : null;
        if (c0872a == null && (c6527i = (C6527i) developSetting.m15963c(DevelopSetting.GPUImageFilterParamType.CLSmooth)) != null) {
            Log.d(this.f13981a, "create new filter (CLSmoothFilter)");
            c0872a = new C0896i(c6527i.m24996b());
        }
        if (c0872a == null) {
            return this.f13982b;
        }
        c0872a.mo4179b(fMin);
        c0872a.mo4178a(fMin);
        this.f13985e = c0872a;
        this.f13984d = developSetting;
        return c0872a;
    }

    /* renamed from: c */
    public C0936w0 m15993c(GLViewEngine.EffectParam effectParam) {
        return m15994d(effectParam, false);
    }

    /* renamed from: d */
    public C0936w0 m15994d(GLViewEngine.EffectParam effectParam, boolean z8) {
        boolean z9;
        CLBokehEffectFilter cLBokehEffectFilter;
        C0878c c0878c;
        CLBlendModesFilter cLBlendModesFilter;
        CLLensFlareFilter cLLensFlareFilter;
        C0898i1 c0898i1;
        C6567a c6567a;
        C6568b c6568b;
        C0934v0 c0934v0;
        C0919p1 c0919p1;
        Log.d(this.f13981a, "getFilter");
        DevelopSetting developSetting = effectParam.f13950a;
        if (developSetting.m15962b() == null ? false : developSetting.m15962b().booleanValue()) {
            return m15992b(effectParam);
        }
        double d9 = effectParam.f13951b;
        float fM15964d = developSetting.m15964d();
        C0938x0 c0938x0 = this.f13983c;
        if (c0938x0 != null && this.f13984d == developSetting) {
            if (effectParam.f13956g != GLViewEngine.EffectParam.ExtraFunc.AutoToneEdit) {
                Log.d(this.f13981a, "reuse old filter");
                this.f13983c.m4243B(1.0f - ((float) d9));
            } else if (effectParam.f13951b <= 0.7d) {
                ((C0893h) c0938x0.m4422P(C0893h.class)).m4294E((((float) d9) / 0.7f) * 80.0f);
                ((C0934v0) this.f13983c.m4422P(C0934v0.class)).m4399z(BitmapDescriptorFactory.HUE_RED);
            } else {
                ((C0893h) c0938x0.m4422P(C0893h.class)).m4294E(80.0f);
                ((C0934v0) this.f13983c.m4422P(C0934v0.class)).m4399z((((float) d9) - 0.7f) / 0.3f);
            }
            return this.f13983c;
        }
        if (c0938x0 != null && z8) {
            C6539u c6539u = (C6539u) developSetting.m15963c(DevelopSetting.GPUImageFilterParamType.WhiteBalance);
            if (c6539u == null || (c0919p1 = (C0919p1) this.f13983c.m4422P(C0919p1.class)) == null) {
                z9 = false;
            } else {
                c0919p1.m4371z(c6539u.m25098a());
                c0919p1.m4370A(c6539u.m25099b());
                z9 = true;
            }
            C6526h c6526h = (C6526h) developSetting.m15963c(DevelopSetting.GPUImageFilterParamType.Exposure);
            if (c6526h != null && (c0934v0 = (C0934v0) this.f13983c.m4422P(C0934v0.class)) != null) {
                c0934v0.m4399z(c6526h.m24993a());
                z9 = true;
            }
            C6530l c6530l = (C6530l) developSetting.m15963c(DevelopSetting.GPUImageFilterParamType.CLHighlightShadow);
            if (c6530l != null && (c6568b = (C6568b) this.f13983c.m4422P(C6568b.class)) != null) {
                c6568b.m25170z(c6530l.m25046a());
                c6568b.m25169A(c6530l.m25047b());
                z9 = true;
            }
            C6525g c6525g = (C6525g) developSetting.m15963c(DevelopSetting.GPUImageFilterParamType.CLContrast);
            if (c6525g != null && (c6567a = (C6567a) this.f13983c.m4422P(C6567a.class)) != null) {
                c6567a.m25168z(c6525g.m24991a());
                z9 = true;
            }
            C6521c c6521c = (C6521c) developSetting.m15963c(DevelopSetting.GPUImageFilterParamType.Hdr);
            if (c6521c != null) {
                C0893h c0893h = (C0893h) this.f13983c.m4422P(C0893h.class);
                if (c0893h != null) {
                    c0893h.m4294E(c6521c.m24983f());
                    c0893h.m4293D(c6521c.m24981d());
                    c0893h.m4292C(c6521c.m24979b());
                    z9 = true;
                }
                C0890g c0890g = (C0890g) this.f13983c.m4422P(C0890g.class);
                if (c0890g != null) {
                    c0890g.m4277I(c6521c.m24982e());
                    c0890g.m4276H(c6521c.m24980c());
                    c0890g.m4275G(c6521c.m24978a());
                    z9 = true;
                }
            }
            C6533o c6533o = (C6533o) developSetting.m15963c(DevelopSetting.GPUImageFilterParamType.Saturation);
            if (c6533o != null && (c0898i1 = (C0898i1) this.f13983c.m4422P(C0898i1.class)) != null) {
                c0898i1.m4351z(c6533o.m25064a());
                z9 = true;
            }
            C6542x c6542x = (C6542x) developSetting.m15963c(DevelopSetting.GPUImageFilterParamType.LensFlare);
            if (c6542x != null && (cLLensFlareFilter = (CLLensFlareFilter) this.f13983c.m4422P(CLLensFlareFilter.class)) != null) {
                cLLensFlareFilter.m16005z(c6542x.m25104a());
                cLLensFlareFilter.m16001A(c6542x.m25108e());
                cLLensFlareFilter.m16002B(c6542x.m25109f(), c6542x.m25110g());
                cLLensFlareFilter.m16003C(c6542x.m25106c());
                cLLensFlareFilter.m16004D(c6542x.m25107d());
                z9 = true;
            }
            C6543y c6543y = (C6543y) developSetting.m15963c(DevelopSetting.GPUImageFilterParamType.Overlays);
            if (c6543y != null && (cLBlendModesFilter = (CLBlendModesFilter) this.f13983c.m4422P(CLBlendModesFilter.class)) != null) {
                cLBlendModesFilter.m4053B(c6543y.m25111a());
                cLBlendModesFilter.m4055D(c6543y.m25113c(), c6543y.m25115e(), c6543y.m25116f());
                cLBlendModesFilter.m4054C(c6543y.m25114d());
                z9 = true;
            }
            C6519a c6519a = (C6519a) developSetting.m15963c(DevelopSetting.GPUImageFilterParamType.Blur);
            if (c6519a != null && (c0878c = (C0878c) this.f13983c.m4422P(C0878c.class)) != null) {
                c0878c.m4235I(c6519a.m24966f());
                CLFocusEffectFilter.FocusMode focusModeM24963c = c6519a.m24963c();
                c0878c.m4233G(focusModeM24963c);
                if (focusModeM24963c == CLFocusEffectFilter.FocusMode.CIRCLE) {
                    c0878c.m4231E(c6519a.m24961a());
                } else if (focusModeM24963c == CLFocusEffectFilter.FocusMode.LINEAR) {
                    c0878c.m4234H(c6519a.m24965e());
                } else if (focusModeM24963c == CLFocusEffectFilter.FocusMode.ELLIPSE) {
                    c0878c.m4232F(c6519a.m24962b());
                }
                z9 = true;
            }
            C6520b c6520b = (C6520b) developSetting.m15963c(DevelopSetting.GPUImageFilterParamType.Bokeh);
            if (c6520b != null && (cLBokehEffectFilter = (CLBokehEffectFilter) this.f13983c.m4422P(CLBokehEffectFilter.class)) != null) {
                cLBokehEffectFilter.m4089O(c6520b.m24976i());
                CLFocusEffectFilter.FocusMode focusModeM24971d = c6520b.m24971d();
                cLBokehEffectFilter.m4085K(focusModeM24971d);
                if (focusModeM24971d == CLFocusEffectFilter.FocusMode.CIRCLE) {
                    cLBokehEffectFilter.m4083I(c6520b.m24969b());
                } else if (focusModeM24971d == CLFocusEffectFilter.FocusMode.LINEAR) {
                    cLBokehEffectFilter.m4086L(c6520b.m24973f());
                } else if (focusModeM24971d == CLFocusEffectFilter.FocusMode.ELLIPSE) {
                    cLBokehEffectFilter.m4084J(c6520b.m24970c());
                }
                cLBokehEffectFilter.m4082H(c6520b.m24968a());
                cLBokehEffectFilter.m4087M(c6520b.m24974g());
                z9 = true;
            }
            if (z9) {
                this.f13983c.m4243B(1.0f - ((float) d9));
                this.f13984d = developSetting;
                return this.f13983c;
            }
        }
        Log.d(this.f13981a, "create new filter group, version=" + fM15964d);
        C0938x0 c0938x02 = new C0938x0(true, true);
        if (fM15964d == 6.0d) {
            C6539u c6539u2 = (C6539u) developSetting.m15963c(DevelopSetting.GPUImageFilterParamType.WhiteBalance);
            if (c6539u2 != null) {
                Log.d(this.f13981a, "create new filter (GPUImageWhiteBalanceFilter)");
                C0919p1 c0919p12 = new C0919p1();
                c0919p12.m4371z(c6539u2.m25098a());
                c0919p12.m4370A(c6539u2.m25099b());
                c0938x02.m4417K(c0919p12);
            }
            C6526h c6526h2 = (C6526h) developSetting.m15963c(DevelopSetting.GPUImageFilterParamType.Exposure);
            if (c6526h2 != null) {
                Log.d(this.f13981a, "create new filter (GPUImageExposureFilter)");
                C0934v0 c0934v02 = new C0934v0();
                c0934v02.m4399z(c6526h2.m24993a());
                c0938x02.m4417K(c0934v02);
            }
            C6530l c6530l2 = (C6530l) developSetting.m15963c(DevelopSetting.GPUImageFilterParamType.HighlightShadow);
            if (c6530l2 != null) {
                Log.d(this.f13981a, "create new filter (GPUImageHighlightShadowFilter)");
                C0880c1 c0880c1 = new C0880c1();
                c0880c1.m4238z(c6530l2.m25046a());
                c0880c1.m4237A(c6530l2.m25047b());
                c0938x02.m4417K(c0880c1);
            }
            C6530l c6530l3 = (C6530l) developSetting.m15963c(DevelopSetting.GPUImageFilterParamType.CLHighlightShadow);
            if (c6530l3 != null) {
                Log.d(this.f13981a, "create new filter (CLHighlightShadowFilter)");
                C6568b c6568b2 = new C6568b();
                c6568b2.m25170z(c6530l3.m25046a());
                c6568b2.m25169A(c6530l3.m25047b());
                c0938x02.m4417K(c6568b2);
            }
            C6523e c6523e = (C6523e) developSetting.m15963c(DevelopSetting.GPUImageFilterParamType.Brightness);
            if (c6523e != null) {
                Log.d(this.f13981a, "create new filter (GPUImageBrightnessFilter)");
                C0924r0 c0924r0 = new C0924r0();
                c0924r0.m4374z(c6523e.m24987a());
                c0938x02.m4417K(c0924r0);
            }
            C6525g c6525g2 = (C6525g) developSetting.m15963c(DevelopSetting.GPUImageFilterParamType.Contrast);
            if (c6525g2 != null) {
                Log.d(this.f13981a, "create new filter (GPUImageContrastFilter)");
                C0932u0 c0932u0 = new C0932u0();
                c0932u0.m4398z(c6525g2.m24991a());
                c0938x02.m4417K(c0932u0);
            }
            C6525g c6525g3 = (C6525g) developSetting.m15963c(DevelopSetting.GPUImageFilterParamType.CLContrast);
            if (c6525g3 != null) {
                Log.d(this.f13981a, "create new filter (CLContrastFilter)");
                C6567a c6567a2 = new C6567a();
                c6567a2.m25168z(c6525g3.m24991a());
                c0938x02.m4417K(c6567a2);
            }
            C6521c c6521c2 = (C6521c) developSetting.m15963c(DevelopSetting.GPUImageFilterParamType.Hdr);
            if (c6521c2 != null) {
                Log.d(this.f13981a, "create new filter (CLHdrGlowFilter)");
                C0893h c0893h2 = new C0893h();
                c0893h2.m4294E(c6521c2.m24983f());
                c0893h2.m4293D(c6521c2.m24981d());
                c0893h2.m4292C(c6521c2.m24979b());
                c0938x02.m4417K(c0893h2);
                Log.d(this.f13981a, "create new filter (CLHdrEdgeFilter)");
                C0890g c0890g2 = new C0890g();
                c0890g2.m4277I(c6521c2.m24982e());
                c0890g2.m4276H(c6521c2.m24980c());
                c0890g2.m4275G(c6521c2.m24978a());
                c0938x02.m4417K(c0890g2);
            }
            C6536r c6536r = (C6536r) developSetting.m15963c(DevelopSetting.GPUImageFilterParamType.ToneCurveRGB);
            if (c6536r != null && c6536r.m25082a() != null) {
                Log.d(this.f13981a, "create new filter (GPUImageToneCurveRGBFilter)");
                C0907l1 c0907l1 = new C0907l1();
                c0907l1.m4357z(c6536r.m25082a());
                c0938x02.m4417K(c0907l1);
            }
            C6524f c6524f = (C6524f) developSetting.m15963c(DevelopSetting.GPUImageFilterParamType.Clarity);
            if (c6524f != null) {
                Log.d(this.f13981a, "create new filter (GPUImageClarityFilter)");
                C0927s0 c0927s0 = new C0927s0();
                c0927s0.m4387H(c6524f.m24989a());
                c0938x02.m4417K(c0927s0);
            }
            C6533o c6533o2 = (C6533o) developSetting.m15963c(DevelopSetting.GPUImageFilterParamType.Saturation);
            if (c6533o2 != null) {
                Log.d(this.f13981a, "create new filter (GPUImageSaturationFilter)");
                C0898i1 c0898i12 = new C0898i1();
                c0898i12.m4351z(c6533o2.m25064a());
                c0938x02.m4417K(c0898i12);
            }
            C6529k c6529k = (C6529k) developSetting.m15963c(DevelopSetting.GPUImageFilterParamType.HSVEx);
            if (c6529k != null) {
                Log.d(this.f13981a, "create new filter (GPUImageHSVExFilter)");
                C0874a1 c0874a1 = new C0874a1();
                c0874a1.m4208C(0, c6529k.m25026g());
                c0874a1.m4208C(1, c6529k.m25024e());
                c0874a1.m4208C(2, c6529k.m25027h());
                c0874a1.m4208C(3, c6529k.m25022c());
                c0874a1.m4208C(4, c6529k.m25020a());
                c0874a1.m4208C(5, c6529k.m25021b());
                c0874a1.m4208C(6, c6529k.m25025f());
                c0874a1.m4208C(7, c6529k.m25023d());
                c0874a1.m4210E(0, c6529k.m25042w());
                c0874a1.m4210E(1, c6529k.m25040u());
                c0874a1.m4210E(2, c6529k.m25043x());
                c0874a1.m4210E(3, c6529k.m25038s());
                c0874a1.m4210E(4, c6529k.m25036q());
                c0874a1.m4210E(5, c6529k.m25037r());
                c0874a1.m4210E(6, c6529k.m25041v());
                c0874a1.m4210E(7, c6529k.m25039t());
                c0874a1.m4209D(0, c6529k.m25034o());
                c0874a1.m4209D(1, c6529k.m25032m());
                c0874a1.m4209D(2, c6529k.m25035p());
                c0874a1.m4209D(3, c6529k.m25030k());
                c0874a1.m4209D(4, c6529k.m25028i());
                c0874a1.m4209D(5, c6529k.m25029j());
                c0874a1.m4209D(6, c6529k.m25033n());
                c0874a1.m4209D(7, c6529k.m25031l());
                c0938x02.m4417K(c0874a1);
            }
            C6535q c6535q = (C6535q) developSetting.m15963c(DevelopSetting.GPUImageFilterParamType.SplitTone);
            if (c6535q != null) {
                Log.d(this.f13981a, "create new filter (GPUImageSplitToneFilter)");
                C0904k1 c0904k1 = new C0904k1();
                c0904k1.m4355z(c6535q.m25068a());
                c0904k1.m4353B(new float[]{c6535q.m25071d(), c6535q.m25070c(), c6535q.m25069b()});
                c0904k1.m4354C(new float[]{c6535q.m25074g(), c6535q.m25073f(), c6535q.m25072e()});
                c0938x02.m4417K(c0904k1);
            }
            C6534p c6534p = (C6534p) developSetting.m15963c(DevelopSetting.GPUImageFilterParamType.Sepia);
            if (c6534p != null) {
                Log.d(this.f13981a, "create new filter (GPUImageSepiaFilter)");
                C0901j1 c0901j1 = new C0901j1();
                c0901j1.m4396A(c6534p.m25066a());
                c0938x02.m4417K(c0901j1);
            }
            C6531m c6531m = (C6531m) developSetting.m15963c(DevelopSetting.GPUImageFilterParamType.Monochrome);
            if (c6531m != null) {
                Log.d(this.f13981a, "create new filter (GPUImageMonochromeFilter)");
                C0886e1 c0886e1 = new C0886e1();
                c0886e1.m4246B(c6531m.m25052c());
                c0886e1.m4245A(new float[]{c6531m.m25053d(), c6531m.m25051b(), c6531m.m25050a()});
                c0938x02.m4417K(c0886e1);
            }
            C6532n c6532n = (C6532n) developSetting.m15963c(DevelopSetting.GPUImageFilterParamType.RGB);
            if (c6532n != null) {
                Log.d(this.f13981a, "create new filter (GPUImageRGBFilter)");
                C0892g1 c0892g1 = new C0892g1();
                c0892g1.m4288B(c6532n.m25060c());
                c0892g1.m4287A(c6532n.m25059b());
                c0892g1.m4289z(c6532n.m25058a());
                c0938x02.m4417K(c0892g1);
            }
            C6538t c6538t = (C6538t) developSetting.m15963c(DevelopSetting.GPUImageFilterParamType.Vignette);
            if (c6538t != null) {
                Log.d(this.f13981a, "create new filter (GPUImageVignetteFilter)");
                C0916o1 c0916o1 = new C0916o1();
                c0916o1.m4365D(c6538t.m25091f());
                c0916o1.m4363B(new float[]{c6538t.m25090e(), c6538t.m25089d(), c6538t.m25086a()});
                c0938x02.m4417K(c0916o1);
            }
            C6542x c6542x2 = (C6542x) developSetting.m15963c(DevelopSetting.GPUImageFilterParamType.LensFlare);
            if (c6542x2 != null) {
                Log.d(this.f13981a, "create new filter (CLLensFlareFilter)");
                CLLensFlareFilter cLLensFlareFilter2 = new CLLensFlareFilter(c6542x2.m25105b());
                cLLensFlareFilter2.m16005z(c6542x2.m25104a());
                cLLensFlareFilter2.m16001A(c6542x2.m25108e());
                cLLensFlareFilter2.m16002B(c6542x2.m25109f(), c6542x2.m25110g());
                cLLensFlareFilter2.m16003C(c6542x2.m25106c());
                cLLensFlareFilter2.m16004D(c6542x2.m25107d());
                c0938x02.m4417K(cLLensFlareFilter2);
            }
            C6543y c6543y2 = (C6543y) developSetting.m15963c(DevelopSetting.GPUImageFilterParamType.Overlays);
            if (c6543y2 != null) {
                Log.d(this.f13981a, "create new filter (CLBlendModesFilter)");
                CLBlendModesFilter cLBlendModesFilter2 = new CLBlendModesFilter(c6543y2.m25111a(), c6543y2.m25112b());
                cLBlendModesFilter2.m4055D(c6543y2.m25113c(), c6543y2.m25115e(), c6543y2.m25116f());
                cLBlendModesFilter2.m4054C(c6543y2.m25114d());
                c0938x02.m4417K(cLBlendModesFilter2);
            }
            C6522d c6522d = (C6522d) developSetting.m15963c(DevelopSetting.GPUImageFilterParamType.AutoTone);
            if (c6522d != null) {
                Log.d(this.f13981a, "create new filter (hdrGlowFilter)");
                C0893h c0893h3 = new C0893h();
                c0893h3.m4292C(c6522d.m24984a());
                c0893h3.m4294E(c6522d.m24986c());
                c0893h3.m4293D(c6522d.m24985b());
                c0938x02.m4417K(c0893h3);
            }
            C6519a c6519a2 = (C6519a) developSetting.m15963c(DevelopSetting.GPUImageFilterParamType.Blur);
            if (c6519a2 != null) {
                C0878c c0878c2 = new C0878c();
                c0878c2.m4230D(c6519a2.m24967g(), c6519a2.m24964d());
                c0878c2.m4235I(c6519a2.m24966f());
                CLFocusEffectFilter.FocusMode focusModeM24963c2 = c6519a2.m24963c();
                c0878c2.m4233G(focusModeM24963c2);
                if (focusModeM24963c2 == CLFocusEffectFilter.FocusMode.CIRCLE) {
                    c0878c2.m4231E(c6519a2.m24961a());
                } else if (focusModeM24963c2 == CLFocusEffectFilter.FocusMode.LINEAR) {
                    c0878c2.m4234H(c6519a2.m24965e());
                } else if (focusModeM24963c2 == CLFocusEffectFilter.FocusMode.ELLIPSE) {
                    c0878c2.m4232F(c6519a2.m24962b());
                }
                c0938x02.m4417K(c0878c2);
            }
            C6520b c6520b2 = (C6520b) developSetting.m15963c(DevelopSetting.GPUImageFilterParamType.Bokeh);
            if (c6520b2 != null) {
                CLBokehEffectFilter cLBokehEffectFilter2 = new CLBokehEffectFilter(c6520b2.m24975h());
                cLBokehEffectFilter2.m4080F(c6520b2.m24977j(), c6520b2.m24972e());
                cLBokehEffectFilter2.m4089O(c6520b2.m24976i());
                CLFocusEffectFilter.FocusMode focusModeM24971d2 = c6520b2.m24971d();
                cLBokehEffectFilter2.m4085K(focusModeM24971d2);
                if (focusModeM24971d2 == CLFocusEffectFilter.FocusMode.CIRCLE) {
                    cLBokehEffectFilter2.m4083I(c6520b2.m24969b());
                } else if (focusModeM24971d2 == CLFocusEffectFilter.FocusMode.LINEAR) {
                    cLBokehEffectFilter2.m4086L(c6520b2.m24973f());
                } else if (focusModeM24971d2 == CLFocusEffectFilter.FocusMode.ELLIPSE) {
                    cLBokehEffectFilter2.m4084J(c6520b2.m24970c());
                }
                cLBokehEffectFilter2.m4082H(c6520b2.m24968a());
                cLBokehEffectFilter2.m4087M(c6520b2.m24974g());
                c0938x02.m4417K(cLBokehEffectFilter2);
            }
            List<C0936w0> listM4423Q = c0938x02.m4423Q();
            if (listM4423Q == null || listM4423Q.size() == 0) {
                c0938x02.m4417K(new C0936w0());
            }
        } else {
            c0938x02.m4417K(new C0940y0());
            c0938x02.m4417K(new C0922q1());
            C6540v c6540v = (C6540v) developSetting.m15963c(DevelopSetting.GPUImageFilterParamType.WhiteBalanceMatrix);
            if (c6540v != null && c6540v.m25102a() != null) {
                Log.d(this.f13981a, "create new filter (ColorMatrix) for WB");
                C0930t0 c0930t0 = new C0930t0();
                c0930t0.m4397z(c6540v.m25102a());
                c0938x02.m4417K(c0930t0);
            }
            C6536r c6536r2 = (C6536r) developSetting.m15963c(DevelopSetting.GPUImageFilterParamType.ToneCurveRGB);
            if (c6536r2 != null && c6536r2.m25082a() != null) {
                Log.d(this.f13981a, "create new filter (ToneCurveRGB)");
                C0907l1 c0907l12 = new C0907l1();
                c0907l12.m4357z(c6536r2.m25082a());
                c0938x02.m4417K(c0907l12);
            }
            C6524f c6524f2 = (C6524f) developSetting.m15963c(DevelopSetting.GPUImageFilterParamType.Clarity);
            if (c6524f2 != null) {
                Log.d(this.f13981a, "create new filter (Clarity)");
                C0927s0 c0927s02 = new C0927s0();
                c0927s02.m4387H(c6524f2.m24989a());
                c0938x02.m4417K(c0927s02);
            }
            C6533o c6533o3 = (C6533o) developSetting.m15963c(DevelopSetting.GPUImageFilterParamType.Saturation);
            if (c6533o3 != null) {
                Log.d(this.f13981a, "create new filter (Saturation) for Saturation");
                C0898i1 c0898i13 = new C0898i1();
                c0898i13.m4351z(c6533o3.m25064a());
                c0938x02.m4417K(c0898i13);
            }
            C6528j c6528j = (C6528j) developSetting.m15963c(DevelopSetting.GPUImageFilterParamType.HSL);
            if (c6528j != null && c6528j.m24997a() != null) {
                Log.d(this.f13981a, "create new filter (HSV) for HSL");
                C0877b1 c0877b1 = new C0877b1();
                c0877b1.mo4359z(c6528j.m24997a());
                c0938x02.m4417K(c0877b1);
            }
            C6537s c6537s = (C6537s) developSetting.m15963c(DevelopSetting.GPUImageFilterParamType.Vibrance);
            if (c6537s != null && c6537s.m25084a() != null) {
                Log.d(this.f13981a, "create new filter (HSV) for Vibrancy");
                C0877b1 c0877b12 = new C0877b1();
                c0877b12.mo4359z(c6537s.m25084a());
                c0938x02.m4417K(c0877b12);
            }
            C6538t c6538t2 = (C6538t) developSetting.m15963c(DevelopSetting.GPUImageFilterParamType.Vignette);
            if (c6538t2 != null) {
                Log.d(this.f13981a, "create new filter (Vignette) for Vignette");
                C0916o1 c0916o12 = new C0916o1();
                c0916o12.m4362A(new PointF(0.5f, 0.5f));
                c0916o12.m4363B(c6538t2.m25087b());
                c0916o12.m4365D(c6538t2.m25091f());
                c0916o12.m4364C(c6538t2.m25088c());
                c0938x02.m4417K(c0916o12);
            }
            c0938x02.m4417K(new C0889f1());
            c0938x02.m4417K(new C0942z0());
        }
        if (effectParam.f13956g != GLViewEngine.EffectParam.ExtraFunc.AutoToneEdit) {
            c0938x02.m4243B(1.0f - ((float) d9));
            c0938x02.m4358A(effectParam.f13953d, effectParam.f13954e, !effectParam.f13955f);
        } else if (effectParam.f13951b <= 0.7d) {
            ((C0893h) c0938x02.m4422P(C0893h.class)).m4294E((((float) d9) / 0.7f) * 80.0f);
            ((C0934v0) c0938x02.m4422P(C0934v0.class)).m4399z(BitmapDescriptorFactory.HUE_RED);
        } else {
            ((C0893h) c0938x02.m4422P(C0893h.class)).m4294E(80.0f);
            ((C0934v0) c0938x02.m4422P(C0934v0.class)).m4399z((((float) d9) - 0.7f) / 0.3f);
        }
        this.f13983c = c0938x02;
        this.f13984d = developSetting;
        return c0938x02;
    }

    /* renamed from: e */
    public C0936w0 m15995e(C0936w0 c0936w0, double d9, int i9, int i10, Matrix matrix, Bitmap bitmap, GLViewEngine.EffectParam.ExtraFunc extraFunc) {
        if (c0936w0 == null) {
            throw new IllegalArgumentException("baseFilter cannot be null");
        }
        if (this.f13988h != c0936w0) {
            this.f13988h = c0936w0;
            GPUImagePanZoomFilter gPUImagePanZoomFilter = new GPUImagePanZoomFilter(i9, i10, bitmap);
            this.f13989i = gPUImagePanZoomFilter;
            if (c0936w0 instanceof C0938x0) {
                C0938x0 c0938x0 = (C0938x0) c0936w0;
                c0938x0.m4425S();
                this.f13989i.m16008B(c0938x0.m4423Q(), m15996f(extraFunc));
            } else {
                gPUImagePanZoomFilter.m16008B(Arrays.asList(c0936w0), false);
            }
        }
        if ((c0936w0 instanceof IBeautyFilter2) || extraFunc == GLViewEngine.EffectParam.ExtraFunc.AutoToneCapture || extraFunc == GLViewEngine.EffectParam.ExtraFunc.AutoToneEdit) {
            this.f13989i.m16019M((float) d9);
        } else if (c0936w0 instanceof C0938x0) {
            this.f13989i.m16020N(1.0f - ((float) d9));
        } else {
            this.f13989i.m16018L();
        }
        this.f13989i.m16021O(matrix);
        return this.f13989i;
    }

    /* renamed from: f */
    public final boolean m15996f(GLViewEngine.EffectParam.ExtraFunc extraFunc) {
        return (extraFunc == GLViewEngine.EffectParam.ExtraFunc.AutoToneCapture || extraFunc == GLViewEngine.EffectParam.ExtraFunc.AutoToneEdit) ? false : true;
    }

    /* renamed from: g */
    public final void m15997g(GLViewEngine.EffectParam effectParam, C0938x0 c0938x0) {
        if (effectParam.f13956g == GLViewEngine.EffectParam.ExtraFunc.AutoToneCapture) {
            ((C0893h) c0938x0.m4422P(C0893h.class)).m4294E(((float) effectParam.f13951b) * 80.0f);
        }
    }
}
