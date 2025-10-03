package p090h8;

import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.gms.plus.PlusShare;
import java.util.HashMap;
import java.util.Map;
import p060e8.C4772d;

/* renamed from: h8.e */
/* loaded from: classes.dex */
public class C5034e {

    /* renamed from: j */
    public static final Map<String, C5034e> f17371j = new HashMap();

    /* renamed from: k */
    public static final String[] f17372k;

    /* renamed from: l */
    public static final String[] f17373l;

    /* renamed from: m */
    public static final String[] f17374m;

    /* renamed from: n */
    public static final String[] f17375n;

    /* renamed from: o */
    public static final String[] f17376o;

    /* renamed from: p */
    public static final String[] f17377p;

    /* renamed from: q */
    public static final String[] f17378q;

    /* renamed from: a */
    public String f17379a;

    /* renamed from: b */
    public boolean f17380b = true;

    /* renamed from: c */
    public boolean f17381c = true;

    /* renamed from: d */
    public boolean f17382d = true;

    /* renamed from: e */
    public boolean f17383e = false;

    /* renamed from: f */
    public boolean f17384f = false;

    /* renamed from: g */
    public boolean f17385g = false;

    /* renamed from: h */
    public boolean f17386h = false;

    /* renamed from: i */
    public boolean f17387i = false;

    static {
        String[] strArr = {"html", TtmlNode.TAG_HEAD, TtmlNode.TAG_BODY, "frameset", "script", "noscript", TtmlNode.TAG_STYLE, "meta", "link", PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_TITLE, "frame", "noframes", "section", "nav", "aside", "hgroup", "header", "footer", TtmlNode.TAG_P, "h1", "h2", "h3", "h4", "h5", "h6", "ul", "ol", "pre", TtmlNode.TAG_DIV, "blockquote", "hr", "address", "figure", "figcaption", "form", "fieldset", "ins", "del", "dl", "dt", "dd", "li", "table", "caption", "thead", "tfoot", "tbody", "colgroup", "col", "tr", "th", "td", MimeTypes.BASE_TYPE_VIDEO, MimeTypes.BASE_TYPE_AUDIO, "canvas", "details", "menu", "plaintext", "template", "article", "main", "svg", "math"};
        f17372k = strArr;
        f17373l = new String[]{"object", "base", "font", TtmlNode.TAG_TT, "i", "b", "u", "big", "small", "em", "strong", "dfn", "code", "samp", "kbd", "var", "cite", "abbr", "time", "acronym", "mark", "ruby", "rt", "rp", "a", "img", TtmlNode.TAG_BR, "wbr", "map", "q", "sub", "sup", "bdo", "iframe", "embed", TtmlNode.TAG_SPAN, "input", "select", "textarea", "label", "button", "optgroup", "option", "legend", "datalist", "keygen", "output", "progress", "meter", "area", "param", "source", "track", "summary", "command", "device", "area", "basefont", "bgsound", "menuitem", "param", "source", "track", "data", "bdi", "s"};
        f17374m = new String[]{"meta", "link", "base", "frame", "img", TtmlNode.TAG_BR, "wbr", "embed", "hr", "input", "keygen", "col", "command", "device", "area", "basefont", "bgsound", "menuitem", "param", "source", "track"};
        f17375n = new String[]{PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_TITLE, "a", TtmlNode.TAG_P, "h1", "h2", "h3", "h4", "h5", "h6", "pre", "address", "li", "th", "td", "script", TtmlNode.TAG_STYLE, "ins", "del", "s"};
        f17376o = new String[]{"pre", "plaintext", PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_TITLE, "textarea"};
        f17377p = new String[]{"button", "fieldset", "input", "keygen", "object", "output", "select", "textarea"};
        f17378q = new String[]{"input", "keygen", "object", "select", "textarea"};
        for (String str : strArr) {
            m19650i(new C5034e(str));
        }
        for (String str2 : f17373l) {
            C5034e c5034e = new C5034e(str2);
            c5034e.f17380b = false;
            c5034e.f17381c = false;
            m19650i(c5034e);
        }
        for (String str3 : f17374m) {
            C5034e c5034e2 = f17371j.get(str3);
            C4772d.m19004j(c5034e2);
            c5034e2.f17382d = false;
            c5034e2.f17383e = true;
        }
        for (String str4 : f17375n) {
            C5034e c5034e3 = f17371j.get(str4);
            C4772d.m19004j(c5034e3);
            c5034e3.f17381c = false;
        }
        for (String str5 : f17376o) {
            C5034e c5034e4 = f17371j.get(str5);
            C4772d.m19004j(c5034e4);
            c5034e4.f17385g = true;
        }
        for (String str6 : f17377p) {
            C5034e c5034e5 = f17371j.get(str6);
            C4772d.m19004j(c5034e5);
            c5034e5.f17386h = true;
        }
        for (String str7 : f17378q) {
            C5034e c5034e6 = f17371j.get(str7);
            C4772d.m19004j(c5034e6);
            c5034e6.f17387i = true;
        }
    }

    public C5034e(String str) {
        this.f17379a = str;
    }

    /* renamed from: i */
    public static void m19650i(C5034e c5034e) {
        f17371j.put(c5034e.f17379a, c5034e);
    }

    /* renamed from: k */
    public static C5034e m19651k(String str) {
        return m19652l(str, C5032c.f17364d);
    }

    /* renamed from: l */
    public static C5034e m19652l(String str, C5032c c5032c) {
        C4772d.m19004j(str);
        Map<String, C5034e> map = f17371j;
        C5034e c5034e = map.get(str);
        if (c5034e != null) {
            return c5034e;
        }
        String strM19644b = c5032c.m19644b(str);
        C4772d.m19002h(strM19644b);
        C5034e c5034e2 = map.get(strM19644b);
        if (c5034e2 != null) {
            return c5034e2;
        }
        C5034e c5034e3 = new C5034e(strM19644b);
        c5034e3.f17380b = false;
        return c5034e3;
    }

    /* renamed from: a */
    public boolean m19653a() {
        return this.f17381c;
    }

    /* renamed from: b */
    public String m19654b() {
        return this.f17379a;
    }

    /* renamed from: c */
    public boolean m19655c() {
        return this.f17380b;
    }

    /* renamed from: d */
    public boolean m19656d() {
        return this.f17383e;
    }

    /* renamed from: e */
    public boolean m19657e() {
        return this.f17386h;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C5034e)) {
            return false;
        }
        C5034e c5034e = (C5034e) obj;
        return this.f17379a.equals(c5034e.f17379a) && this.f17382d == c5034e.f17382d && this.f17383e == c5034e.f17383e && this.f17381c == c5034e.f17381c && this.f17380b == c5034e.f17380b && this.f17385g == c5034e.f17385g && this.f17384f == c5034e.f17384f && this.f17386h == c5034e.f17386h && this.f17387i == c5034e.f17387i;
    }

    /* renamed from: f */
    public boolean m19658f() {
        return f17371j.containsKey(this.f17379a);
    }

    /* renamed from: g */
    public boolean m19659g() {
        return this.f17383e || this.f17384f;
    }

    /* renamed from: h */
    public boolean m19660h() {
        return this.f17385g;
    }

    public int hashCode() {
        return (((((((((((((((this.f17379a.hashCode() * 31) + (this.f17380b ? 1 : 0)) * 31) + (this.f17381c ? 1 : 0)) * 31) + (this.f17382d ? 1 : 0)) * 31) + (this.f17383e ? 1 : 0)) * 31) + (this.f17384f ? 1 : 0)) * 31) + (this.f17385g ? 1 : 0)) * 31) + (this.f17386h ? 1 : 0)) * 31) + (this.f17387i ? 1 : 0);
    }

    /* renamed from: j */
    public C5034e m19661j() {
        this.f17384f = true;
        return this;
    }

    public String toString() {
        return this.f17379a;
    }
}
