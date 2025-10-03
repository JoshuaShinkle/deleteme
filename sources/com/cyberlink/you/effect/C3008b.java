package com.cyberlink.you.effect;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.cyberlink.clgpuimage.IBeautyFilter2;
import com.cyberlink.you.Globals;
import com.cyberlink.you.kernelctrl.dataeditcenter.DevelopSetting;
import com.cyberlink.you.kernelctrl.glviewengine.C3080a;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import p209u2.C6370g;
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
import p228w3.InterfaceC6541w;

/* renamed from: com.cyberlink.you.effect.b */
/* loaded from: classes.dex */
public class C3008b {

    /* renamed from: a */
    public Context f13298a;

    /* renamed from: b */
    public String f13299b;

    /* renamed from: com.cyberlink.you.effect.b$b */
    public class b {

        /* renamed from: a */
        public boolean f13300a = false;

        /* renamed from: b */
        public boolean f13301b = false;

        /* renamed from: c */
        public boolean f13302c = true;

        public b() {
        }
    }

    /* renamed from: com.cyberlink.you.effect.b$c */
    public class c {

        /* renamed from: a */
        public String f13304a;

        /* renamed from: b */
        public String f13305b;

        /* renamed from: c */
        public String f13306c;

        /* renamed from: d */
        public String f13307d;

        /* renamed from: e */
        public String f13308e;

        /* renamed from: f */
        public String f13309f;

        /* renamed from: g */
        public String f13310g;

        /* renamed from: h */
        public String f13311h;

        /* renamed from: i */
        public String f13312i;

        /* renamed from: j */
        public String f13313j;

        /* renamed from: k */
        public String f13314k;

        /* renamed from: l */
        public String f13315l;

        /* renamed from: m */
        public String f13316m;

        /* renamed from: n */
        public String f13317n;

        /* renamed from: o */
        public String f13318o;

        public c() {
        }
    }

    /* renamed from: com.cyberlink.you.effect.b$d */
    public class d {

        /* renamed from: a */
        public c f13320a;

        /* renamed from: b */
        public DevelopSetting f13321b;

        /* renamed from: c */
        public String f13322c;

        /* renamed from: d */
        public float f13323d;

        /* renamed from: e */
        public f f13324e;

        /* renamed from: f */
        public b f13325f;

        /* renamed from: g */
        public boolean f13326g;

        public d() {
        }
    }

    /* renamed from: com.cyberlink.you.effect.b$e */
    public static class e {

        /* renamed from: a */
        public static final C3008b f13328a = new C3008b();
    }

    /* renamed from: com.cyberlink.you.effect.b$f */
    public class f {

        /* renamed from: a */
        public boolean f13329a = true;

        /* renamed from: b */
        public boolean f13330b = true;

        /* renamed from: c */
        public boolean f13331c = true;

        public f() {
        }
    }

    /* renamed from: G */
    public static C3008b m15318G() {
        return e.f13328a;
    }

    /* renamed from: A */
    public final C6539u m15319A(NodeList nodeList) {
        if (nodeList == null || nodeList.item(0) == null) {
            return null;
        }
        C6539u c6539u = new C6539u();
        Element element = (Element) nodeList.item(0);
        NodeList elementsByTagName = element.getElementsByTagName("temperature");
        if (elementsByTagName != null && elementsByTagName.item(0) != null) {
            c6539u.m25100c(m15342i(elementsByTagName));
        }
        NodeList elementsByTagName2 = element.getElementsByTagName("tint");
        if (elementsByTagName2 == null || elementsByTagName2.item(0) == null) {
            return c6539u;
        }
        c6539u.m25101d(m15342i(elementsByTagName2));
        return c6539u;
    }

    /* renamed from: B */
    public final C6533o m15320B(NodeList nodeList) {
        if (nodeList == null || nodeList.item(0) == null) {
            return null;
        }
        float fM15342i = m15342i(nodeList);
        C6533o c6533o = new C6533o();
        c6533o.m25065b(fM15342i);
        return c6533o;
    }

    /* renamed from: C */
    public final C6537s m15321C(NodeList nodeList) {
        Bitmap bitmapM15337d = m15337d(nodeList);
        if (bitmapM15337d == null) {
            return null;
        }
        C6537s c6537s = new C6537s();
        c6537s.m25085b(bitmapM15337d);
        return c6537s;
    }

    /* renamed from: D */
    public final C6538t m15322D(NodeList nodeList) {
        if (nodeList == null || nodeList.item(0) == null) {
            return null;
        }
        C6538t c6538t = new C6538t();
        Element element = (Element) nodeList.item(0);
        NodeList elementsByTagName = element.getElementsByTagName(TtmlNode.ATTR_TTS_COLOR);
        if (elementsByTagName != null && elementsByTagName.item(0) != null) {
            c6538t.m25093h(m15343j(elementsByTagName));
        }
        NodeList elementsByTagName2 = element.getElementsByTagName(TtmlNode.START);
        if (elementsByTagName2 != null && elementsByTagName2.item(0) != null) {
            c6538t.m25097l(m15342i(elementsByTagName2));
        }
        NodeList elementsByTagName3 = element.getElementsByTagName(TtmlNode.END);
        if (elementsByTagName3 == null || elementsByTagName3.item(0) == null) {
            return c6538t;
        }
        c6538t.m25094i(m15342i(elementsByTagName3));
        return c6538t;
    }

    /* renamed from: E */
    public final C6540v m15323E(NodeList nodeList) {
        float[] fArrM15343j;
        if (nodeList == null || (fArrM15343j = m15343j(nodeList)) == null) {
            return null;
        }
        C6540v c6540v = new C6540v();
        c6540v.m25103b(fArrM15343j);
        return c6540v;
    }

    /* renamed from: F */
    public final String m15324F(NodeList nodeList) {
        return m15330M(nodeList).replaceAll("[{}\\s+]", "");
    }

    /* renamed from: H */
    public final int[] m15325H(NodeList nodeList) {
        String[] strArrSplit = m15330M(nodeList).replaceAll("[\\[\\()\\s+]", "").split(",");
        int[] iArr = new int[strArrSplit.length];
        for (int i9 = 0; i9 < strArrSplit.length; i9++) {
            try {
                iArr[i9] = (int) Math.round(Double.parseDouble(strArrSplit[i9].trim()));
            } catch (NumberFormatException unused) {
                return null;
            }
        }
        return iArr;
    }

    /* renamed from: I */
    public final c m15326I(NodeList nodeList) {
        c cVar = new c();
        if (nodeList != null && nodeList.item(0) != null) {
            Element element = (Element) nodeList.item(0);
            cVar.f13305b = m15330M(element.getElementsByTagName("cht"));
            cVar.f13306c = m15330M(element.getElementsByTagName("chs"));
            cVar.f13304a = m15330M(element.getElementsByTagName("enu"));
            cVar.f13307d = m15330M(element.getElementsByTagName("jpn"));
            cVar.f13308e = m15330M(element.getElementsByTagName("kor"));
            cVar.f13309f = m15330M(element.getElementsByTagName("deu"));
            cVar.f13310g = m15330M(element.getElementsByTagName("esp"));
            cVar.f13311h = m15330M(element.getElementsByTagName("fra"));
            cVar.f13312i = m15330M(element.getElementsByTagName("ita"));
            cVar.f13313j = m15330M(element.getElementsByTagName("plk"));
            cVar.f13314k = m15330M(element.getElementsByTagName("ptg"));
            cVar.f13315l = m15330M(element.getElementsByTagName("ptb"));
            cVar.f13316m = m15330M(element.getElementsByTagName("rus"));
            cVar.f13317n = m15330M(element.getElementsByTagName("nld"));
            cVar.f13318o = m15330M(element.getElementsByTagName("def"));
        }
        return cVar;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0, types: [java.io.InputStream, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r4v1, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r4v3 */
    /* JADX WARN: Type inference failed for: r4v4 */
    /* renamed from: J */
    public final d m15327J(InputStream inputStream) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        d dVarM15328K;
        try {
            if (inputStream == 0) {
                return null;
            }
            try {
                Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse((InputStream) inputStream);
                document.getDocumentElement().normalize();
                dVarM15328K = m15328K(document);
                C6370g.m24480b(inputStream);
                inputStream = 1;
            } catch (Exception e9) {
                e9.printStackTrace();
                C6370g.m24480b(inputStream);
                inputStream = 0;
                dVarM15328K = null;
            }
            if (inputStream == 0) {
                return null;
            }
            return dVarM15328K;
        } catch (Throwable th) {
            C6370g.m24480b(inputStream);
            throw th;
        }
    }

    /* renamed from: K */
    public final d m15328K(Document document) {
        d dVar = new d();
        dVar.f13322c = m15324F(document.getElementsByTagName("guid"));
        dVar.f13320a = m15326I(document.getElementsByTagName(AppMeasurementSdk.ConditionalUserProperty.NAME));
        dVar.f13323d = m15331N(document.getElementsByTagName("version")).floatValue();
        dVar.f13324e = m15329L(document.getElementsByTagName("smart_focus_mode"));
        dVar.f13325f = m15335b(document.getElementsByTagName("mode"));
        dVar.f13326g = m15336c(document.getElementsByTagName("beauty_filter"));
        DevelopSetting developSettingM15338e = m15338e(document.getElementsByTagName(TtmlNode.TAG_BODY), dVar.f13323d);
        dVar.f13321b = developSettingM15338e;
        developSettingM15338e.m15966f(dVar.f13323d);
        dVar.f13321b.m15965e(Boolean.valueOf(dVar.f13326g));
        return dVar;
    }

    /* renamed from: L */
    public final f m15329L(NodeList nodeList) {
        int[] iArrM15325H;
        f fVar = new f();
        if (nodeList != null && nodeList.item(0) != null && (iArrM15325H = m15325H(nodeList)) != null) {
            int i9 = 0;
            while (true) {
                if (i9 >= iArrM15325H.length) {
                    break;
                }
                boolean z8 = iArrM15325H[i9] > 0;
                if (i9 == 0) {
                    fVar.f13329a = z8;
                } else if (i9 == 1) {
                    fVar.f13330b = z8;
                } else if (i9 == 2) {
                    fVar.f13331c = z8;
                    break;
                }
                i9++;
            }
        }
        return fVar;
    }

    /* renamed from: M */
    public final String m15330M(NodeList nodeList) {
        NodeList childNodes;
        return (nodeList == null || nodeList.item(0) == null || (childNodes = nodeList.item(0).getChildNodes()) == null || childNodes.item(0) == null) ? "" : childNodes.item(0).getNodeValue().trim();
    }

    /* renamed from: N */
    public final Float m15331N(NodeList nodeList) {
        return Float.valueOf(m15342i(nodeList));
    }

    /* renamed from: O */
    public d m15332O(String str) {
        return m15334a(str);
    }

    /* renamed from: P */
    public d m15333P(String str, String str2) {
        this.f13299b = str2;
        return m15334a(str);
    }

    /* renamed from: a */
    public final d m15334a(String str) throws IOException {
        InputStream inputStreamOpen;
        if (str.indexOf("asset://") == 0) {
            try {
                inputStreamOpen = this.f13298a.getAssets().open(str.substring(8));
            } catch (Exception e9) {
                e9.printStackTrace();
                return null;
            }
        } else {
            try {
                inputStreamOpen = new BufferedInputStream(new FileInputStream(str));
            } catch (Exception e10) {
                e10.printStackTrace();
                return null;
            }
        }
        return m15327J(inputStreamOpen);
    }

    /* renamed from: b */
    public final b m15335b(NodeList nodeList) {
        b bVar = new b();
        if (nodeList != null && nodeList.item(0) != null) {
            Element element = (Element) nodeList.item(0);
            NodeList elementsByTagName = element.getElementsByTagName("live");
            if (elementsByTagName != null && elementsByTagName.item(0) != null) {
                bVar.f13300a = m15339f(elementsByTagName);
            }
            NodeList elementsByTagName2 = element.getElementsByTagName("capture");
            if (elementsByTagName2 != null && elementsByTagName2.item(0) != null) {
                bVar.f13301b = m15339f(elementsByTagName2);
            }
            NodeList elementsByTagName3 = element.getElementsByTagName("edit");
            if (elementsByTagName3 != null && elementsByTagName3.item(0) != null) {
                bVar.f13302c = m15339f(elementsByTagName3);
            }
        }
        return bVar;
    }

    /* renamed from: c */
    public final boolean m15336c(NodeList nodeList) {
        return m15339f(nodeList);
    }

    /* renamed from: d */
    public final Bitmap m15337d(NodeList nodeList) {
        String strM15330M = m15330M(nodeList);
        if (strM15330M.length() == 0) {
            return null;
        }
        String strTrim = strM15330M.trim();
        if (strTrim.indexOf("asset://") == 0) {
            try {
                return BitmapFactory.decodeStream(new BufferedInputStream(this.f13298a.getAssets().open(strTrim.substring(8))));
            } catch (IOException e9) {
                e9.printStackTrace();
                return null;
            } catch (OutOfMemoryError e10) {
                e10.printStackTrace();
                return null;
            }
        }
        try {
            return BitmapFactory.decodeFile(this.f13299b + File.separator + strTrim);
        } catch (OutOfMemoryError e11) {
            e11.printStackTrace();
            return null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x02ce  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x02e6  */
    /* renamed from: e */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final DevelopSetting m15338e(NodeList nodeList, float f9) {
        IBeautyFilter2.FilterType filterType;
        IBeautyFilter2.EffectMode effectModeValueOf;
        DevelopSetting developSetting = new DevelopSetting();
        if (nodeList != null && nodeList.item(0) != null) {
            Element element = (Element) nodeList.item(0);
            if (f9 == 5.0f) {
                developSetting.f13895b.put(DevelopSetting.GPUImageFilterParamType.ToneCurveRGB, m15345l(element.getElementsByTagName("gpu_curve")));
                developSetting.f13895b.put(DevelopSetting.GPUImageFilterParamType.Vignette, m15322D(element.getElementsByTagName("gpu_vignette")));
                developSetting.f13895b.put(DevelopSetting.GPUImageFilterParamType.Saturation, m15320B(element.getElementsByTagName("gpu_saturation")));
                developSetting.f13895b.put(DevelopSetting.GPUImageFilterParamType.WhiteBalanceMatrix, m15323E(element.getElementsByTagName("gpu_wb_matrix")));
                developSetting.f13895b.put(DevelopSetting.GPUImageFilterParamType.HSL, m15346m(element.getElementsByTagName("gpu_hsl")));
                developSetting.f13895b.put(DevelopSetting.GPUImageFilterParamType.Vibrance, m15321C(element.getElementsByTagName("gpu_vibrancy")));
                developSetting.f13895b.put(DevelopSetting.GPUImageFilterParamType.Clarity, m15344k(element.getElementsByTagName("gpu_clarity_ex")));
            } else if (f9 == 6.0f) {
                developSetting.f13895b.put(DevelopSetting.GPUImageFilterParamType.WhiteBalance, m15319A(element.getElementsByTagName("GPUImageWhiteBalanceFilter")));
                developSetting.f13895b.put(DevelopSetting.GPUImageFilterParamType.Exposure, m15350q(element.getElementsByTagName("GPUImageExposureFilter")));
                developSetting.f13895b.put(DevelopSetting.GPUImageFilterParamType.HighlightShadow, m15352s(element.getElementsByTagName("GPUImageHighlightShadowFilter")));
                developSetting.f13895b.put(DevelopSetting.GPUImageFilterParamType.Brightness, m15347n(element.getElementsByTagName("GPUImageBrightnessFilter")));
                developSetting.f13895b.put(DevelopSetting.GPUImageFilterParamType.Contrast, m15349p(element.getElementsByTagName("GPUImageContrastFilter")));
                developSetting.f13895b.put(DevelopSetting.GPUImageFilterParamType.Clarity, m15348o(element.getElementsByTagName("GPUImageClarityFilter")));
                developSetting.f13895b.put(DevelopSetting.GPUImageFilterParamType.Saturation, m15355v(element.getElementsByTagName("GPUImageSaturationFilter")));
                developSetting.f13895b.put(DevelopSetting.GPUImageFilterParamType.SplitTone, m15357x(element.getElementsByTagName("GPUImageSplitToneFilter")));
                developSetting.f13895b.put(DevelopSetting.GPUImageFilterParamType.Sepia, m15356w(element.getElementsByTagName("GPUImageSepiaFilter")));
                developSetting.f13895b.put(DevelopSetting.GPUImageFilterParamType.Monochrome, m15353t(element.getElementsByTagName("GPUImageMonochromeFilter")));
                developSetting.f13895b.put(DevelopSetting.GPUImageFilterParamType.RGB, m15354u(element.getElementsByTagName("GPUImageRGBFilter")));
                developSetting.f13895b.put(DevelopSetting.GPUImageFilterParamType.Vignette, m15359z(element.getElementsByTagName("GPUImageVignetteFilter")));
                developSetting.f13895b.put(DevelopSetting.GPUImageFilterParamType.HSVEx, m15351r(element.getElementsByTagName("GPUImageHSVExFilter")));
                developSetting.f13895b.put(DevelopSetting.GPUImageFilterParamType.ToneCurveRGB, m15358y(element.getElementsByTagName("GPUImageToneCurveRGBFilter")));
                NodeList elementsByTagName = element.getElementsByTagName("CLCandyColorFilter");
                Map<DevelopSetting.GPUImageFilterParamType, InterfaceC6541w> map = developSetting.f13895b;
                DevelopSetting.GPUImageFilterParamType gPUImageFilterParamType = DevelopSetting.GPUImageFilterParamType.CLCandyColor;
                map.put(gPUImageFilterParamType, m15341h(elementsByTagName, gPUImageFilterParamType));
                NodeList elementsByTagName2 = element.getElementsByTagName("CLAestheticColorFilter");
                Map<DevelopSetting.GPUImageFilterParamType, InterfaceC6541w> map2 = developSetting.f13895b;
                DevelopSetting.GPUImageFilterParamType gPUImageFilterParamType2 = DevelopSetting.GPUImageFilterParamType.CLAestheticColor;
                map2.put(gPUImageFilterParamType2, m15341h(elementsByTagName2, gPUImageFilterParamType2));
                NodeList elementsByTagName3 = element.getElementsByTagName("CLGentleColorFilter");
                Map<DevelopSetting.GPUImageFilterParamType, InterfaceC6541w> map3 = developSetting.f13895b;
                DevelopSetting.GPUImageFilterParamType gPUImageFilterParamType3 = DevelopSetting.GPUImageFilterParamType.CLGentleColor;
                map3.put(gPUImageFilterParamType3, m15341h(elementsByTagName3, gPUImageFilterParamType3));
                NodeList elementsByTagName4 = element.getElementsByTagName("CLForestColorFilter");
                Map<DevelopSetting.GPUImageFilterParamType, InterfaceC6541w> map4 = developSetting.f13895b;
                DevelopSetting.GPUImageFilterParamType gPUImageFilterParamType4 = DevelopSetting.GPUImageFilterParamType.CLForestColor;
                map4.put(gPUImageFilterParamType4, m15341h(elementsByTagName4, gPUImageFilterParamType4));
                NodeList elementsByTagName5 = element.getElementsByTagName("CLCoolColorFilter");
                Map<DevelopSetting.GPUImageFilterParamType, InterfaceC6541w> map5 = developSetting.f13895b;
                DevelopSetting.GPUImageFilterParamType gPUImageFilterParamType5 = DevelopSetting.GPUImageFilterParamType.CLCoolColor;
                map5.put(gPUImageFilterParamType5, m15341h(elementsByTagName5, gPUImageFilterParamType5));
                NodeList elementsByTagName6 = element.getElementsByTagName("CLVintageColorFilter");
                Map<DevelopSetting.GPUImageFilterParamType, InterfaceC6541w> map6 = developSetting.f13895b;
                DevelopSetting.GPUImageFilterParamType gPUImageFilterParamType6 = DevelopSetting.GPUImageFilterParamType.CLVintageColor;
                map6.put(gPUImageFilterParamType6, m15341h(elementsByTagName6, gPUImageFilterParamType6));
                NodeList elementsByTagName7 = element.getElementsByTagName("CLRedColorFilter");
                Map<DevelopSetting.GPUImageFilterParamType, InterfaceC6541w> map7 = developSetting.f13895b;
                DevelopSetting.GPUImageFilterParamType gPUImageFilterParamType7 = DevelopSetting.GPUImageFilterParamType.CLRedColor;
                map7.put(gPUImageFilterParamType7, m15341h(elementsByTagName7, gPUImageFilterParamType7));
                developSetting.f13895b.put(DevelopSetting.GPUImageFilterParamType.CLSmooth, m15340g(element.getElementsByTagName("CLSmoothFilter")));
                NodeList elementsByTagName8 = element.getElementsByTagName("CLFreshColorFilter");
                Map<DevelopSetting.GPUImageFilterParamType, InterfaceC6541w> map8 = developSetting.f13895b;
                DevelopSetting.GPUImageFilterParamType gPUImageFilterParamType8 = DevelopSetting.GPUImageFilterParamType.CLFreshColor;
                map8.put(gPUImageFilterParamType8, m15341h(elementsByTagName8, gPUImageFilterParamType8));
                NodeList elementsByTagName9 = element.getElementsByTagName("CLSoftlightColorFilter");
                Map<DevelopSetting.GPUImageFilterParamType, InterfaceC6541w> map9 = developSetting.f13895b;
                DevelopSetting.GPUImageFilterParamType gPUImageFilterParamType9 = DevelopSetting.GPUImageFilterParamType.CLSoftlightColor;
                map9.put(gPUImageFilterParamType9, m15341h(elementsByTagName9, gPUImageFilterParamType9));
                NodeList elementsByTagName10 = element.getElementsByTagName("CLWarmColorFilter");
                Map<DevelopSetting.GPUImageFilterParamType, InterfaceC6541w> map10 = developSetting.f13895b;
                DevelopSetting.GPUImageFilterParamType gPUImageFilterParamType10 = DevelopSetting.GPUImageFilterParamType.CLWarmColor;
                map10.put(gPUImageFilterParamType10, m15341h(elementsByTagName10, gPUImageFilterParamType10));
                NodeList elementsByTagName11 = element.getElementsByTagName("CLElegantColorFilter");
                Map<DevelopSetting.GPUImageFilterParamType, InterfaceC6541w> map11 = developSetting.f13895b;
                DevelopSetting.GPUImageFilterParamType gPUImageFilterParamType11 = DevelopSetting.GPUImageFilterParamType.CLElegantColor;
                map11.put(gPUImageFilterParamType11, m15341h(elementsByTagName11, gPUImageFilterParamType11));
                NodeList elementsByTagName12 = element.getElementsByTagName("CLRetroColorFilter");
                Map<DevelopSetting.GPUImageFilterParamType, InterfaceC6541w> map12 = developSetting.f13895b;
                DevelopSetting.GPUImageFilterParamType gPUImageFilterParamType12 = DevelopSetting.GPUImageFilterParamType.CLRetroColor;
                map12.put(gPUImageFilterParamType12, m15341h(elementsByTagName12, gPUImageFilterParamType12));
                NodeList elementsByTagName13 = element.getElementsByTagName("CLLightColorFilter");
                Map<DevelopSetting.GPUImageFilterParamType, InterfaceC6541w> map13 = developSetting.f13895b;
                DevelopSetting.GPUImageFilterParamType gPUImageFilterParamType13 = DevelopSetting.GPUImageFilterParamType.CLLightColor;
                map13.put(gPUImageFilterParamType13, m15341h(elementsByTagName13, gPUImageFilterParamType13));
                NodeList elementsByTagName14 = element.getElementsByTagName("CLBlackWhiteColorFilter");
                Map<DevelopSetting.GPUImageFilterParamType, InterfaceC6541w> map14 = developSetting.f13895b;
                DevelopSetting.GPUImageFilterParamType gPUImageFilterParamType14 = DevelopSetting.GPUImageFilterParamType.CLBlackWhiteColor;
                map14.put(gPUImageFilterParamType14, m15341h(elementsByTagName14, gPUImageFilterParamType14));
                NodeList elementsByTagName15 = element.getElementsByTagName("CLAphroditeColorFilter");
                if (elementsByTagName15 != null && elementsByTagName15.item(0) != null) {
                    Element element2 = (Element) elementsByTagName15.item(0);
                    NodeList elementsByTagName16 = element2.getElementsByTagName("FilterType");
                    if (elementsByTagName16 == null || elementsByTagName16.item(0) == null) {
                        filterType = null;
                        NodeList elementsByTagName17 = element2.getElementsByTagName("EffectMode");
                        effectModeValueOf = (elementsByTagName17 != null || elementsByTagName17.item(0) == null) ? null : IBeautyFilter2.EffectMode.valueOf(m15330M(elementsByTagName17));
                        if (filterType != null && effectModeValueOf != null) {
                            developSetting.f13895b.put(DevelopSetting.GPUImageFilterParamType.CLAphroditeColorFilter, new C6527i(null, false, filterType, effectModeValueOf));
                        }
                    } else {
                        String strM15330M = m15330M(elementsByTagName16);
                        if (strM15330M.equalsIgnoreCase("LIVE_SMOOTH")) {
                            filterType = IBeautyFilter2.FilterType.LIVE_SMOOTH;
                        } else if (strM15330M.equalsIgnoreCase("ENABLE_SMOOTH")) {
                            filterType = IBeautyFilter2.FilterType.ENABLE_SMOOTH;
                        } else if (strM15330M.equalsIgnoreCase("DISABLE_SMOOTH")) {
                            filterType = IBeautyFilter2.FilterType.DISABLE_SMOOTH;
                        }
                        NodeList elementsByTagName172 = element2.getElementsByTagName("EffectMode");
                        if (elementsByTagName172 != null) {
                            if (filterType != null) {
                                developSetting.f13895b.put(DevelopSetting.GPUImageFilterParamType.CLAphroditeColorFilter, new C6527i(null, false, filterType, effectModeValueOf));
                            }
                        }
                    }
                }
            }
        }
        return developSetting;
    }

    /* renamed from: f */
    public final boolean m15339f(NodeList nodeList) {
        return Boolean.parseBoolean(m15330M(nodeList));
    }

    /* renamed from: g */
    public final InterfaceC6541w m15340g(NodeList nodeList) {
        return m15341h(nodeList, null);
    }

    /* renamed from: h */
    public final InterfaceC6541w m15341h(NodeList nodeList, DevelopSetting.GPUImageFilterParamType gPUImageFilterParamType) {
        if (nodeList == null || nodeList.item(0) == null) {
            return null;
        }
        String strM15330M = m15330M(nodeList);
        IBeautyFilter2.FilterType filterType = strM15330M.equalsIgnoreCase("LIVE_SMOOTH") ? IBeautyFilter2.FilterType.LIVE_SMOOTH : strM15330M.equalsIgnoreCase("ENABLE_SMOOTH") ? IBeautyFilter2.FilterType.ENABLE_SMOOTH : strM15330M.equalsIgnoreCase("DISABLE_SMOOTH") ? IBeautyFilter2.FilterType.DISABLE_SMOOTH : null;
        IBeautyFilter2.EffectMode effectMode = gPUImageFilterParamType != null ? C3080a.f13980j.get(gPUImageFilterParamType) : null;
        return effectMode == null ? new C6527i(null, false, filterType) : new C6527i(null, false, filterType, effectMode);
    }

    /* renamed from: i */
    public final float m15342i(NodeList nodeList) {
        return Float.parseFloat(m15330M(nodeList));
    }

    /* renamed from: j */
    public final float[] m15343j(NodeList nodeList) {
        String strM15330M = m15330M(nodeList);
        if (strM15330M.length() == 0) {
            return null;
        }
        String[] strArrSplit = strM15330M.replaceAll("[\\[\\]()\\s+]", "").split(",");
        float[] fArr = new float[strArrSplit.length];
        for (int i9 = 0; i9 < strArrSplit.length; i9++) {
            try {
                fArr[i9] = Float.parseFloat(strArrSplit[i9].trim());
            } catch (NumberFormatException unused) {
                return null;
            }
        }
        return fArr;
    }

    /* renamed from: k */
    public final C6524f m15344k(NodeList nodeList) {
        if (nodeList == null || nodeList.item(0) == null) {
            return null;
        }
        float fM15342i = m15342i(nodeList);
        C6524f c6524f = new C6524f();
        c6524f.m24990b(fM15342i);
        return c6524f;
    }

    /* renamed from: l */
    public final C6536r m15345l(NodeList nodeList) {
        float[] fArrM15343j;
        if (nodeList == null || (fArrM15343j = m15343j(nodeList)) == null) {
            return null;
        }
        C6536r c6536r = new C6536r();
        c6536r.m25083b(fArrM15343j);
        return c6536r;
    }

    /* renamed from: m */
    public final C6528j m15346m(NodeList nodeList) {
        Bitmap bitmapM15337d = m15337d(nodeList);
        if (bitmapM15337d == null) {
            return null;
        }
        C6528j c6528j = new C6528j();
        c6528j.m24998b(bitmapM15337d);
        return c6528j;
    }

    /* renamed from: n */
    public final C6523e m15347n(NodeList nodeList) {
        if (nodeList == null || nodeList.item(0) == null) {
            return null;
        }
        C6523e c6523e = new C6523e();
        NodeList elementsByTagName = ((Element) nodeList.item(0)).getElementsByTagName("intensity");
        if (elementsByTagName == null || elementsByTagName.item(0) == null) {
            return c6523e;
        }
        c6523e.m24988b(m15342i(elementsByTagName));
        return c6523e;
    }

    /* renamed from: o */
    public final C6524f m15348o(NodeList nodeList) {
        if (nodeList == null || nodeList.item(0) == null) {
            return null;
        }
        C6524f c6524f = new C6524f();
        NodeList elementsByTagName = ((Element) nodeList.item(0)).getElementsByTagName("intensity");
        if (elementsByTagName == null || elementsByTagName.item(0) == null) {
            return c6524f;
        }
        c6524f.m24990b(m15342i(elementsByTagName));
        return c6524f;
    }

    /* renamed from: p */
    public final C6525g m15349p(NodeList nodeList) {
        if (nodeList == null || nodeList.item(0) == null) {
            return null;
        }
        C6525g c6525g = new C6525g();
        NodeList elementsByTagName = ((Element) nodeList.item(0)).getElementsByTagName("intensity");
        if (elementsByTagName == null || elementsByTagName.item(0) == null) {
            return c6525g;
        }
        c6525g.m24992b(m15342i(elementsByTagName));
        return c6525g;
    }

    /* renamed from: q */
    public final C6526h m15350q(NodeList nodeList) {
        if (nodeList == null || nodeList.item(0) == null) {
            return null;
        }
        C6526h c6526h = new C6526h();
        NodeList elementsByTagName = ((Element) nodeList.item(0)).getElementsByTagName("intensity");
        if (elementsByTagName == null || elementsByTagName.item(0) == null) {
            return c6526h;
        }
        c6526h.m24994b(m15342i(elementsByTagName));
        return c6526h;
    }

    /* renamed from: r */
    public final C6529k m15351r(NodeList nodeList) {
        if (nodeList == null || nodeList.item(0) == null) {
            return null;
        }
        C6529k c6529k = new C6529k();
        Element element = (Element) nodeList.item(0);
        NodeList elementsByTagName = element.getElementsByTagName("hue_red");
        if (elementsByTagName != null && elementsByTagName.item(0) != null) {
            c6529k.m25002D(m15342i(elementsByTagName));
        }
        NodeList elementsByTagName2 = element.getElementsByTagName("hue_orange");
        if (elementsByTagName2 != null && elementsByTagName2.item(0) != null) {
            c6529k.m25000B(m15342i(elementsByTagName2));
        }
        NodeList elementsByTagName3 = element.getElementsByTagName("hue_yellow");
        if (elementsByTagName3 != null && elementsByTagName3.item(0) != null) {
            c6529k.m25003E(m15342i(elementsByTagName3));
        }
        NodeList elementsByTagName4 = element.getElementsByTagName("hue_green");
        if (elementsByTagName4 != null && elementsByTagName4.item(0) != null) {
            c6529k.m25045z(m15342i(elementsByTagName4));
        }
        NodeList elementsByTagName5 = element.getElementsByTagName("hue_aqua");
        if (elementsByTagName5 != null && elementsByTagName5.item(0) != null) {
            c6529k.m25044y(m15342i(elementsByTagName5));
        }
        NodeList elementsByTagName6 = element.getElementsByTagName("hue_blue");
        if (elementsByTagName6 != null && elementsByTagName6.item(0) != null) {
            c6529k.m25045z(m15342i(elementsByTagName6));
        }
        NodeList elementsByTagName7 = element.getElementsByTagName("hue_purple");
        if (elementsByTagName7 != null && elementsByTagName7.item(0) != null) {
            c6529k.m25001C(m15342i(elementsByTagName7));
        }
        NodeList elementsByTagName8 = element.getElementsByTagName("hue_magenta");
        if (elementsByTagName8 != null && elementsByTagName8.item(0) != null) {
            c6529k.m24999A(m15342i(elementsByTagName8));
        }
        NodeList elementsByTagName9 = element.getElementsByTagName("saturation_red");
        if (elementsByTagName9 != null && elementsByTagName9.item(0) != null) {
            c6529k.m25018T(m15342i(elementsByTagName9));
        }
        NodeList elementsByTagName10 = element.getElementsByTagName("saturation_orange");
        if (elementsByTagName10 != null && elementsByTagName10.item(0) != null) {
            c6529k.m25016R(m15342i(elementsByTagName10));
        }
        NodeList elementsByTagName11 = element.getElementsByTagName("saturation_yellow");
        if (elementsByTagName11 != null && elementsByTagName11.item(0) != null) {
            c6529k.m25019U(m15342i(elementsByTagName11));
        }
        NodeList elementsByTagName12 = element.getElementsByTagName("saturation_green");
        if (elementsByTagName12 != null && elementsByTagName12.item(0) != null) {
            c6529k.m25014P(m15342i(elementsByTagName12));
        }
        NodeList elementsByTagName13 = element.getElementsByTagName("saturation_aqua");
        if (elementsByTagName13 != null && elementsByTagName13.item(0) != null) {
            c6529k.m25012N(m15342i(elementsByTagName13));
        }
        NodeList elementsByTagName14 = element.getElementsByTagName("saturation_blue");
        if (elementsByTagName14 != null && elementsByTagName14.item(0) != null) {
            c6529k.m25013O(m15342i(elementsByTagName14));
        }
        NodeList elementsByTagName15 = element.getElementsByTagName("saturation_purple");
        if (elementsByTagName15 != null && elementsByTagName15.item(0) != null) {
            c6529k.m25017S(m15342i(elementsByTagName15));
        }
        NodeList elementsByTagName16 = element.getElementsByTagName("saturation_magenta");
        if (elementsByTagName16 != null && elementsByTagName16.item(0) != null) {
            c6529k.m25015Q(m15342i(elementsByTagName16));
        }
        NodeList elementsByTagName17 = element.getElementsByTagName("lightness_red");
        if (elementsByTagName17 != null && elementsByTagName17.item(0) != null) {
            c6529k.m25010L(m15342i(elementsByTagName17));
        }
        NodeList elementsByTagName18 = element.getElementsByTagName("lightness_orange");
        if (elementsByTagName18 != null && elementsByTagName18.item(0) != null) {
            c6529k.m25008J(m15342i(elementsByTagName18));
        }
        NodeList elementsByTagName19 = element.getElementsByTagName("lightness_yellow");
        if (elementsByTagName19 != null && elementsByTagName19.item(0) != null) {
            c6529k.m25011M(m15342i(elementsByTagName19));
        }
        NodeList elementsByTagName20 = element.getElementsByTagName("lightness_green");
        if (elementsByTagName20 != null && elementsByTagName20.item(0) != null) {
            c6529k.m25006H(m15342i(elementsByTagName20));
        }
        NodeList elementsByTagName21 = element.getElementsByTagName("lightness_aqua");
        if (elementsByTagName21 != null && elementsByTagName21.item(0) != null) {
            c6529k.m25004F(m15342i(elementsByTagName21));
        }
        NodeList elementsByTagName22 = element.getElementsByTagName("lightness_blue");
        if (elementsByTagName22 != null && elementsByTagName22.item(0) != null) {
            c6529k.m25005G(m15342i(elementsByTagName22));
        }
        NodeList elementsByTagName23 = element.getElementsByTagName("lightness_purple");
        if (elementsByTagName23 != null && elementsByTagName23.item(0) != null) {
            c6529k.m25009K(m15342i(elementsByTagName23));
        }
        NodeList elementsByTagName24 = element.getElementsByTagName("lightness_magenta");
        if (elementsByTagName24 == null || elementsByTagName24.item(0) == null) {
            return c6529k;
        }
        c6529k.m25007I(m15342i(elementsByTagName24));
        return c6529k;
    }

    /* renamed from: s */
    public final C6530l m15352s(NodeList nodeList) {
        if (nodeList == null || nodeList.item(0) == null) {
            return null;
        }
        C6530l c6530l = new C6530l();
        Element element = (Element) nodeList.item(0);
        NodeList elementsByTagName = element.getElementsByTagName("highlight");
        if (elementsByTagName != null && elementsByTagName.item(0) != null) {
            c6530l.m25048c(m15342i(elementsByTagName));
        }
        NodeList elementsByTagName2 = element.getElementsByTagName("shadow");
        if (elementsByTagName2 == null || elementsByTagName2.item(0) == null) {
            return c6530l;
        }
        c6530l.m25049d(m15342i(elementsByTagName2));
        return c6530l;
    }

    /* renamed from: t */
    public final C6531m m15353t(NodeList nodeList) {
        if (nodeList == null || nodeList.item(0) == null) {
            return null;
        }
        C6531m c6531m = new C6531m();
        Element element = (Element) nodeList.item(0);
        NodeList elementsByTagName = element.getElementsByTagName("intensity");
        if (elementsByTagName != null && elementsByTagName.item(0) != null) {
            c6531m.m25056g(m15342i(elementsByTagName));
        }
        NodeList elementsByTagName2 = element.getElementsByTagName("red");
        if (elementsByTagName2 != null && elementsByTagName2.item(0) != null) {
            c6531m.m25057h(m15342i(elementsByTagName2));
        }
        NodeList elementsByTagName3 = element.getElementsByTagName("green");
        if (elementsByTagName3 != null && elementsByTagName3.item(0) != null) {
            c6531m.m25055f(m15342i(elementsByTagName3));
        }
        NodeList elementsByTagName4 = element.getElementsByTagName("blue");
        if (elementsByTagName4 == null || elementsByTagName4.item(0) == null) {
            return c6531m;
        }
        c6531m.m25054e(m15342i(elementsByTagName4));
        return c6531m;
    }

    /* renamed from: u */
    public final C6532n m15354u(NodeList nodeList) {
        if (nodeList == null || nodeList.item(0) == null) {
            return null;
        }
        C6532n c6532n = new C6532n();
        Element element = (Element) nodeList.item(0);
        NodeList elementsByTagName = element.getElementsByTagName("red");
        if (elementsByTagName != null && elementsByTagName.item(0) != null) {
            c6532n.m25063f(m15342i(elementsByTagName));
        }
        NodeList elementsByTagName2 = element.getElementsByTagName("green");
        if (elementsByTagName2 != null && elementsByTagName2.item(0) != null) {
            c6532n.m25062e(m15342i(elementsByTagName2));
        }
        NodeList elementsByTagName3 = element.getElementsByTagName("blue");
        if (elementsByTagName3 == null || elementsByTagName3.item(0) == null) {
            return c6532n;
        }
        c6532n.m25061d(m15342i(elementsByTagName3));
        return c6532n;
    }

    /* renamed from: v */
    public final C6533o m15355v(NodeList nodeList) {
        if (nodeList == null || nodeList.item(0) == null) {
            return null;
        }
        C6533o c6533o = new C6533o();
        NodeList elementsByTagName = ((Element) nodeList.item(0)).getElementsByTagName("intensity");
        if (elementsByTagName == null || elementsByTagName.item(0) == null) {
            return c6533o;
        }
        c6533o.m25065b(m15342i(elementsByTagName));
        return c6533o;
    }

    /* renamed from: w */
    public final C6534p m15356w(NodeList nodeList) {
        if (nodeList == null || nodeList.item(0) == null) {
            return null;
        }
        C6534p c6534p = new C6534p();
        NodeList elementsByTagName = ((Element) nodeList.item(0)).getElementsByTagName("intensity");
        if (elementsByTagName == null || elementsByTagName.item(0) == null) {
            return c6534p;
        }
        c6534p.m25067b(m15342i(elementsByTagName));
        return c6534p;
    }

    /* renamed from: x */
    public final C6535q m15357x(NodeList nodeList) {
        if (nodeList == null || nodeList.item(0) == null) {
            return null;
        }
        C6535q c6535q = new C6535q();
        Element element = (Element) nodeList.item(0);
        NodeList elementsByTagName = element.getElementsByTagName("balance");
        if (elementsByTagName != null && elementsByTagName.item(0) != null) {
            c6535q.m25075h(m15342i(elementsByTagName));
        }
        NodeList elementsByTagName2 = element.getElementsByTagName("hilight_red");
        if (elementsByTagName2 != null && elementsByTagName2.item(0) != null) {
            c6535q.m25078k(m15342i(elementsByTagName2));
        }
        NodeList elementsByTagName3 = element.getElementsByTagName("hilight_green");
        if (elementsByTagName3 != null && elementsByTagName3.item(0) != null) {
            c6535q.m25077j(m15342i(elementsByTagName3));
        }
        NodeList elementsByTagName4 = element.getElementsByTagName("hilight_blue");
        if (elementsByTagName4 != null && elementsByTagName4.item(0) != null) {
            c6535q.m25076i(m15342i(elementsByTagName4));
        }
        NodeList elementsByTagName5 = element.getElementsByTagName("shadow_red");
        if (elementsByTagName5 != null && elementsByTagName5.item(0) != null) {
            c6535q.m25081n(m15342i(elementsByTagName5));
        }
        NodeList elementsByTagName6 = element.getElementsByTagName("shadow_green");
        if (elementsByTagName6 != null && elementsByTagName6.item(0) != null) {
            c6535q.m25080m(m15342i(elementsByTagName6));
        }
        NodeList elementsByTagName7 = element.getElementsByTagName("shadow_blue");
        if (elementsByTagName7 == null || elementsByTagName7.item(0) == null) {
            return c6535q;
        }
        c6535q.m25079l(m15342i(elementsByTagName7));
        return c6535q;
    }

    /* renamed from: y */
    public final C6536r m15358y(NodeList nodeList) {
        float[] fArrM15343j;
        if (nodeList == null || nodeList.item(0) == null) {
            return null;
        }
        C6536r c6536r = new C6536r();
        NodeList elementsByTagName = ((Element) nodeList.item(0)).getElementsByTagName("curve");
        if (elementsByTagName == null || elementsByTagName.item(0) == null || (fArrM15343j = m15343j(elementsByTagName)) == null) {
            return c6536r;
        }
        c6536r.m25083b(fArrM15343j);
        return c6536r;
    }

    /* renamed from: z */
    public final C6538t m15359z(NodeList nodeList) {
        if (nodeList == null || nodeList.item(0) == null) {
            return null;
        }
        C6538t c6538t = new C6538t();
        Element element = (Element) nodeList.item(0);
        NodeList elementsByTagName = element.getElementsByTagName(TtmlNode.START);
        if (elementsByTagName != null && elementsByTagName.item(0) != null) {
            c6538t.m25097l(m15342i(elementsByTagName));
        }
        NodeList elementsByTagName2 = element.getElementsByTagName("red");
        if (elementsByTagName2 != null && elementsByTagName2.item(0) != null) {
            c6538t.m25096k(m15342i(elementsByTagName2));
        }
        NodeList elementsByTagName3 = element.getElementsByTagName("green");
        if (elementsByTagName3 != null && elementsByTagName3.item(0) != null) {
            c6538t.m25095j(m15342i(elementsByTagName3));
        }
        NodeList elementsByTagName4 = element.getElementsByTagName("blue");
        if (elementsByTagName4 == null || elementsByTagName4.item(0) == null) {
            return c6538t;
        }
        c6538t.m25092g(m15342i(elementsByTagName4));
        return c6538t;
    }

    public C3008b() {
        this.f13298a = Globals.m7388i0().getApplicationContext();
    }
}
