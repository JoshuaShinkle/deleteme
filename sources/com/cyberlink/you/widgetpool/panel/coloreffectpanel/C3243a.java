package com.cyberlink.you.widgetpool.panel.coloreffectpanel;

import com.cyberlink.clgpuimage.IBeautyFilter2;
import com.cyberlink.p030U.R;
import com.cyberlink.you.Globals;
import com.cyberlink.you.effect.C3007a;
import com.cyberlink.you.effect.C3008b;
import com.cyberlink.you.kernelctrl.dataeditcenter.DevelopSetting;
import com.cyberlink.you.widgetpool.panel.coloreffectpanel.ColorEffectUtility;
import p228w3.C6527i;

/* renamed from: com.cyberlink.you.widgetpool.panel.coloreffectpanel.a */
/* loaded from: classes.dex */
public class C3243a {

    /* renamed from: a */
    public ColorEffectUtility.ColorEffectMode f15142a;

    /* renamed from: com.cyberlink.you.widgetpool.panel.coloreffectpanel.a$a */
    public class a {

        /* renamed from: a */
        public String f15143a;

        /* renamed from: b */
        public String f15144b;

        public a() {
        }
    }

    public C3243a() {
        m17334g();
    }

    /* renamed from: a */
    public final C3008b.d m17328a(int i9, int i10) {
        if (i10 == 0) {
            return null;
        }
        return Globals.m7388i0().f7310b.get(Integer.valueOf((i9 * 100) + i10));
    }

    /* renamed from: b */
    public DevelopSetting m17329b(int i9, int i10) {
        if (i10 != 0) {
            C3008b.d dVarM17328a = m17328a(i9, i10);
            if (dVarM17328a != null) {
                return dVarM17328a.f13321b;
            }
            return null;
        }
        DevelopSetting developSettingM15961a = DevelopSetting.m15961a();
        ColorEffectUtility.ColorEffectMode colorEffectMode = this.f15142a;
        if (colorEffectMode == ColorEffectUtility.ColorEffectMode.Live) {
            developSettingM15961a.f13895b.put(DevelopSetting.GPUImageFilterParamType.CLSmooth, new C6527i(null, false, IBeautyFilter2.FilterType.LIVE_SMOOTH));
            developSettingM15961a.m15965e(Boolean.TRUE);
        } else if (colorEffectMode == ColorEffectUtility.ColorEffectMode.Capture && i9 == 0) {
            developSettingM15961a.f13895b.put(DevelopSetting.GPUImageFilterParamType.CLSmooth, new C6527i(null, false, IBeautyFilter2.FilterType.ENABLE_SMOOTH));
            developSettingM15961a.m15965e(Boolean.TRUE);
        }
        return developSettingM15961a;
    }

    /* renamed from: c */
    public int m17330c() {
        return ColorEffectUtility.m17318e(this.f15142a);
    }

    /* renamed from: d */
    public a m17331d(int i9, int i10) {
        String str;
        String str2;
        C3008b.d dVarM17328a;
        if (i10 == 0 || (dVarM17328a = m17328a(i9, i10)) == null) {
            str = "Natural";
            str2 = "null";
        } else {
            str = dVarM17328a.f13320a.f13318o;
            str2 = dVarM17328a.f13322c;
        }
        a aVar = new a();
        aVar.f15143a = str;
        aVar.f15144b = str2;
        return aVar;
    }

    /* renamed from: e */
    public String m17332e(int i9, int i10) {
        int iM17315b = ColorEffectUtility.m17315b(i10);
        if (iM17315b == 0 && i9 == 0) {
            return Globals.m7388i0().getString(R.string.common_natural);
        }
        C3008b.d dVarM17328a = m17328a(i9, iM17315b);
        return dVarM17328a != null ? C3007a.m15317a(dVarM17328a, false) : "";
    }

    /* renamed from: f */
    public String m17333f(int i9, int i10) {
        ColorEffectUtility.ColorEffectMode colorEffectMode = this.f15142a;
        if (colorEffectMode == ColorEffectUtility.ColorEffectMode.Capture || colorEffectMode == ColorEffectUtility.ColorEffectMode.CaptureEdit) {
            return "asset://livePreview/" + ColorEffectUtility.m17320g(i9, i10);
        }
        return "asset://livePreview/" + ColorEffectUtility.m17324k(i9, i10);
    }

    /* renamed from: g */
    public void m17334g() {
        this.f15142a = ColorEffectUtility.ColorEffectMode.Capture;
    }
}
