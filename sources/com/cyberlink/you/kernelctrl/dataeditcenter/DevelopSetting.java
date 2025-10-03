package com.cyberlink.you.kernelctrl.dataeditcenter;

import java.util.HashMap;
import java.util.Map;
import p228w3.InterfaceC6541w;

/* loaded from: classes.dex */
public final class DevelopSetting {

    /* renamed from: a */
    public EffectMode f13894a = null;

    /* renamed from: b */
    public Map<GPUImageFilterParamType, InterfaceC6541w> f13895b = new HashMap();

    /* renamed from: c */
    public float f13896c;

    /* renamed from: d */
    public Boolean f13897d;

    public enum EffectMode {
        ALL,
        FOREGROUND,
        BACKGROUND
    }

    public enum GPUImageFilterParamType {
        WhiteBalance,
        Exposure,
        HighlightShadow,
        Brightness,
        Contrast,
        Clarity,
        Saturation,
        SplitTone,
        Sepia,
        Monochrome,
        RGB,
        Vignette,
        HSVEx,
        ToneCurveRGB,
        WhiteBalanceMatrix,
        HSL,
        Vibrance,
        LensFlare,
        Overlays,
        AutoTone,
        Blur,
        Bokeh,
        Hdr,
        CLHighlightShadow,
        CLContrast,
        CLCandyColor,
        CLAestheticColor,
        CLGentleColor,
        CLForestColor,
        CLCoolColor,
        CLVintageColor,
        CLRedColor,
        CLSmooth,
        CLFreshColor,
        CLWarmColor,
        CLSoftlightColor,
        CLElegantColor,
        CLRetroColor,
        CLLightColor,
        CLBlackWhiteColor,
        CLAphroditeColorFilter
    }

    /* renamed from: a */
    public static DevelopSetting m15961a() {
        return new DevelopSetting();
    }

    /* renamed from: b */
    public Boolean m15962b() {
        return this.f13897d;
    }

    /* renamed from: c */
    public InterfaceC6541w m15963c(GPUImageFilterParamType gPUImageFilterParamType) {
        if (this.f13895b.containsKey(gPUImageFilterParamType)) {
            return this.f13895b.get(gPUImageFilterParamType);
        }
        return null;
    }

    /* renamed from: d */
    public float m15964d() {
        return this.f13896c;
    }

    /* renamed from: e */
    public void m15965e(Boolean bool) {
        this.f13897d = bool;
    }

    public boolean equals(Object obj) {
        return obj == this || (obj instanceof DevelopSetting);
    }

    /* renamed from: f */
    public void m15966f(float f9) {
        this.f13896c = f9;
    }
}
