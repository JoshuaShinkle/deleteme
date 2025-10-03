package org.jsoup.parser;

import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.google.android.gms.plus.PlusShare;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import org.jsoup.nodes.AbstractC5690g;
import org.jsoup.nodes.C5685b;
import org.jsoup.nodes.C5686c;
import org.jsoup.nodes.C5687d;
import org.jsoup.nodes.C5688e;
import org.jsoup.nodes.C5692i;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Token;
import p060e8.C4771c;
import p060e8.C4772d;
import p080g8.C4973b;
import p090h8.C5031b;
import p090h8.C5032c;
import p090h8.C5034e;

/* renamed from: org.jsoup.parser.a */
/* loaded from: classes.dex */
public class C5795a extends AbstractC5797c {

    /* renamed from: k */
    public HtmlTreeBuilderState f20166k;

    /* renamed from: l */
    public HtmlTreeBuilderState f20167l;

    /* renamed from: m */
    public boolean f20168m;

    /* renamed from: n */
    public Element f20169n;

    /* renamed from: o */
    public C4973b f20170o;

    /* renamed from: p */
    public Element f20171p;

    /* renamed from: q */
    public ArrayList<Element> f20172q;

    /* renamed from: r */
    public List<String> f20173r;

    /* renamed from: s */
    public Token.C5725g f20174s;

    /* renamed from: t */
    public boolean f20175t;

    /* renamed from: u */
    public boolean f20176u;

    /* renamed from: v */
    public boolean f20177v;

    /* renamed from: w */
    public String[] f20178w = {null};

    /* renamed from: x */
    public static final String[] f20163x = {"applet", "caption", "html", "marquee", "object", "table", "td", "th"};

    /* renamed from: y */
    public static final String[] f20164y = {"ol", "ul"};

    /* renamed from: z */
    public static final String[] f20165z = {"button"};

    /* renamed from: A */
    public static final String[] f20159A = {"html", "table"};

    /* renamed from: B */
    public static final String[] f20160B = {"optgroup", "option"};

    /* renamed from: C */
    public static final String[] f20161C = {"dd", "dt", "li", "optgroup", "option", TtmlNode.TAG_P, "rp", "rt"};

    /* renamed from: D */
    public static final String[] f20162D = {"address", "applet", "area", "article", "aside", "base", "basefont", "bgsound", "blockquote", TtmlNode.TAG_BODY, TtmlNode.TAG_BR, "button", "caption", TtmlNode.CENTER, "col", "colgroup", "command", "dd", "details", "dir", TtmlNode.TAG_DIV, "dl", "dt", "embed", "fieldset", "figcaption", "figure", "footer", "form", "frame", "frameset", "h1", "h2", "h3", "h4", "h5", "h6", TtmlNode.TAG_HEAD, "header", "hgroup", "hr", "html", "iframe", "img", "input", "isindex", "li", "link", "listing", "marquee", "menu", "meta", "nav", "noembed", "noframes", "noscript", "object", "ol", TtmlNode.TAG_P, "param", "plaintext", "pre", "script", "section", "select", TtmlNode.TAG_STYLE, "summary", "table", "tbody", "td", "textarea", "tfoot", "th", "thead", PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_TITLE, "tr", "ul", "wbr", "xmp"};

    /* renamed from: A */
    public List<String> m23044A() {
        return this.f20173r;
    }

    /* renamed from: A0 */
    public HtmlTreeBuilderState m23045A0() {
        return this.f20166k;
    }

    /* renamed from: B */
    public ArrayList<Element> m23046B() {
        return this.f20201d;
    }

    /* renamed from: B0 */
    public void m23047B0(HtmlTreeBuilderState htmlTreeBuilderState) {
        this.f20166k = htmlTreeBuilderState;
    }

    /* renamed from: C */
    public boolean m23048C(String str) {
        return m23051F(str, f20165z);
    }

    /* renamed from: D */
    public boolean m23049D(String str) {
        return m23051F(str, f20164y);
    }

    /* renamed from: E */
    public boolean m23050E(String str) {
        return m23051F(str, null);
    }

    /* renamed from: F */
    public boolean m23051F(String str, String[] strArr) {
        return m23054I(str, f20163x, strArr);
    }

    /* renamed from: G */
    public boolean m23052G(String[] strArr) {
        return m23055J(strArr, f20163x, null);
    }

    /* renamed from: H */
    public boolean m23053H(String str) {
        for (int size = this.f20201d.size() - 1; size >= 0; size--) {
            String strMo22827v = this.f20201d.get(size).mo22827v();
            if (strMo22827v.equals(str)) {
                return true;
            }
            if (!C4771c.m18981c(strMo22827v, f20160B)) {
                return false;
            }
        }
        C4772d.m18995a("Should not be reachable");
        return false;
    }

    /* renamed from: I */
    public final boolean m23054I(String str, String[] strArr, String[] strArr2) {
        String[] strArr3 = this.f20178w;
        strArr3[0] = str;
        return m23055J(strArr3, strArr, strArr2);
    }

    /* renamed from: J */
    public final boolean m23055J(String[] strArr, String[] strArr2, String[] strArr3) {
        int size = this.f20201d.size() - 1;
        int i9 = size > 100 ? size - 100 : 0;
        while (size >= i9) {
            String strMo22827v = this.f20201d.get(size).mo22827v();
            if (C4771c.m18981c(strMo22827v, strArr)) {
                return true;
            }
            if (C4771c.m18981c(strMo22827v, strArr2)) {
                return false;
            }
            if (strArr3 != null && C4771c.m18981c(strMo22827v, strArr3)) {
                return false;
            }
            size--;
        }
        return false;
    }

    /* renamed from: K */
    public boolean m23056K(String str) {
        return m23054I(str, f20159A, null);
    }

    /* renamed from: L */
    public Element m23057L(Token.C5726h c5726h) {
        if (!c5726h.m23030z()) {
            Element element = new Element(C5034e.m19652l(c5726h.m23015A(), this.f20205h), this.f20202e, this.f20205h.m19643a(c5726h.f20085j));
            m23058M(element);
            return element;
        }
        Element elementM23061P = m23061P(c5726h);
        this.f20201d.add(elementM23061P);
        this.f20199b.m23139u(TokeniserState.Data);
        this.f20199b.m23129k(this.f20174s.mo23003m().m23016B(elementM23061P.m22874v0()));
        return elementM23061P;
    }

    /* renamed from: M */
    public void m23058M(Element element) {
        m23065T(element);
        this.f20201d.add(element);
    }

    /* renamed from: N */
    public void m23059N(Token.C5721c c5721c) {
        String strM22874v0 = m23140a().m22874v0();
        String strM23006q = c5721c.m23006q();
        m23140a().m22850T(c5721c.m22996f() ? new C5686c(strM23006q) : (strM22874v0.equals("script") || strM22874v0.equals(TtmlNode.TAG_STYLE)) ? new C5688e(strM23006q) : new C5692i(strM23006q));
    }

    /* renamed from: O */
    public void m23060O(Token.C5722d c5722d) {
        m23065T(new C5687d(c5722d.m23007p()));
    }

    /* renamed from: P */
    public Element m23061P(Token.C5726h c5726h) {
        C5034e c5034eM19652l = C5034e.m19652l(c5726h.m23015A(), this.f20205h);
        Element element = new Element(c5034eM19652l, this.f20202e, c5726h.f20085j);
        m23065T(element);
        if (c5726h.m23030z()) {
            if (!c5034eM19652l.m19658f()) {
                c5034eM19652l.m19661j();
            } else if (!c5034eM19652l.m19656d()) {
                this.f20199b.m23135q("Tag cannot be self closing; not a void tag");
            }
        }
        return element;
    }

    /* renamed from: Q */
    public C4973b m23062Q(Token.C5726h c5726h, boolean z8) {
        C4973b c4973b = new C4973b(C5034e.m19652l(c5726h.m23015A(), this.f20205h), this.f20202e, c5726h.f20085j);
        m23114x0(c4973b);
        m23065T(c4973b);
        if (z8) {
            this.f20201d.add(c4973b);
        }
        return c4973b;
    }

    /* renamed from: R */
    public void m23063R(AbstractC5690g abstractC5690g) {
        Element elementM23085j;
        Element elementM23115y = m23115y("table");
        boolean z8 = false;
        if (elementM23115y == null) {
            elementM23085j = this.f20201d.get(0);
        } else if (elementM23115y.m22869p0() != null) {
            elementM23085j = elementM23115y.m22869p0();
            z8 = true;
        } else {
            elementM23085j = m23085j(elementM23115y);
        }
        if (!z8) {
            elementM23085j.m22850T(abstractC5690g);
        } else {
            C4772d.m19004j(elementM23115y);
            elementM23115y.m22852X(abstractC5690g);
        }
    }

    /* renamed from: S */
    public void m23064S() {
        this.f20172q.add(null);
    }

    /* renamed from: T */
    public final void m23065T(AbstractC5690g abstractC5690g) {
        C4973b c4973b;
        if (this.f20201d.size() == 0) {
            this.f20200c.m22850T(abstractC5690g);
        } else if (m23069X()) {
            m23063R(abstractC5690g);
        } else {
            m23140a().m22850T(abstractC5690g);
        }
        if (abstractC5690g instanceof Element) {
            Element element = (Element) abstractC5690g;
            if (!element.m22873u0().m19657e() || (c4973b = this.f20170o) == null) {
                return;
            }
            c4973b.m19251y0(element);
        }
    }

    /* renamed from: U */
    public void m23066U(Element element, Element element2) {
        int iLastIndexOf = this.f20201d.lastIndexOf(element);
        C4772d.m18998d(iLastIndexOf != -1);
        this.f20201d.add(iLastIndexOf + 1, element2);
    }

    /* renamed from: V */
    public Element m23067V(String str) {
        Element element = new Element(C5034e.m19652l(str, this.f20205h), this.f20202e);
        m23058M(element);
        return element;
    }

    /* renamed from: W */
    public final boolean m23068W(ArrayList<Element> arrayList, Element element) {
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            if (arrayList.get(size) == element) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: X */
    public boolean m23069X() {
        return this.f20176u;
    }

    /* renamed from: Y */
    public boolean m23070Y() {
        return this.f20177v;
    }

    /* renamed from: Z */
    public boolean m23071Z(Element element) {
        return m23068W(this.f20172q, element);
    }

    /* renamed from: a0 */
    public final boolean m23072a0(Element element, Element element2) {
        return element.mo22827v().equals(element2.mo22827v()) && element.mo19257e().equals(element2.mo19257e());
    }

    @Override // org.jsoup.parser.AbstractC5797c
    /* renamed from: b */
    public C5032c mo23073b() {
        return C5032c.f17363c;
    }

    /* renamed from: b0 */
    public boolean m23074b0(Element element) {
        return C4771c.m18981c(element.mo22827v(), f20162D);
    }

    @Override // org.jsoup.parser.AbstractC5797c
    /* renamed from: c */
    public void mo23075c(Reader reader, String str, ParseErrorList parseErrorList, C5032c c5032c) {
        super.mo23075c(reader, str, parseErrorList, c5032c);
        this.f20166k = HtmlTreeBuilderState.Initial;
        this.f20167l = null;
        this.f20168m = false;
        this.f20169n = null;
        this.f20170o = null;
        this.f20171p = null;
        this.f20172q = new ArrayList<>();
        this.f20173r = new ArrayList();
        this.f20174s = new Token.C5725g();
        this.f20175t = true;
        this.f20176u = false;
        this.f20177v = false;
    }

    /* renamed from: c0 */
    public Element m23076c0() {
        if (this.f20172q.size() <= 0) {
            return null;
        }
        return this.f20172q.get(r0.size() - 1);
    }

    /* renamed from: d0 */
    public void m23077d0() {
        this.f20167l = this.f20166k;
    }

    @Override // org.jsoup.parser.AbstractC5797c
    /* renamed from: e */
    public boolean mo23078e(Token token) {
        this.f20203f = token;
        return this.f20166k.mo22972j(token, this);
    }

    /* renamed from: e0 */
    public void m23079e0(Element element) {
        if (this.f20168m) {
            return;
        }
        String strMo19254a = element.mo19254a("href");
        if (strMo19254a.length() != 0) {
            this.f20202e = strMo19254a;
            this.f20168m = true;
            this.f20200c.m22943L(strMo19254a);
        }
    }

    /* renamed from: f0 */
    public void m23080f0() {
        this.f20173r = new ArrayList();
    }

    /* renamed from: g0 */
    public boolean m23081g0(Element element) {
        return m23068W(this.f20201d, element);
    }

    @Override // org.jsoup.parser.AbstractC5797c
    /* renamed from: h */
    public /* bridge */ /* synthetic */ boolean mo23082h(String str, C5685b c5685b) {
        return super.mo23082h(str, c5685b);
    }

    /* renamed from: h0 */
    public HtmlTreeBuilderState m23083h0() {
        return this.f20167l;
    }

    /* renamed from: i0 */
    public Element m23084i0() {
        return this.f20201d.remove(this.f20201d.size() - 1);
    }

    /* renamed from: j */
    public Element m23085j(Element element) {
        for (int size = this.f20201d.size() - 1; size >= 0; size--) {
            if (this.f20201d.get(size) == element) {
                return this.f20201d.get(size - 1);
            }
        }
        return null;
    }

    /* renamed from: j0 */
    public void m23086j0(String str) {
        for (int size = this.f20201d.size() - 1; size >= 0 && !this.f20201d.get(size).mo22827v().equals(str); size--) {
            this.f20201d.remove(size);
        }
    }

    /* renamed from: k */
    public void m23087k() {
        while (!this.f20172q.isEmpty() && m23104s0() != null) {
        }
    }

    /* renamed from: k0 */
    public void m23088k0(String str) {
        for (int size = this.f20201d.size() - 1; size >= 0; size--) {
            Element element = this.f20201d.get(size);
            this.f20201d.remove(size);
            if (element.mo22827v().equals(str)) {
                return;
            }
        }
    }

    /* renamed from: l */
    public final void m23089l(String... strArr) {
        for (int size = this.f20201d.size() - 1; size >= 0; size--) {
            Element element = this.f20201d.get(size);
            if (C4771c.m18980b(element.mo22827v(), strArr) || element.mo22827v().equals("html")) {
                return;
            }
            this.f20201d.remove(size);
        }
    }

    /* renamed from: l0 */
    public void m23090l0(String... strArr) {
        for (int size = this.f20201d.size() - 1; size >= 0; size--) {
            Element element = this.f20201d.get(size);
            this.f20201d.remove(size);
            if (C4771c.m18981c(element.mo22827v(), strArr)) {
                return;
            }
        }
    }

    /* renamed from: m */
    public void m23091m() {
        m23089l("tbody", "tfoot", "thead", "template");
    }

    /* renamed from: m0 */
    public boolean m23092m0(Token token, HtmlTreeBuilderState htmlTreeBuilderState) {
        this.f20203f = token;
        return htmlTreeBuilderState.mo22972j(token, this);
    }

    /* renamed from: n */
    public void m23093n() {
        m23089l("table");
    }

    /* renamed from: n0 */
    public void m23094n0(Element element) {
        this.f20201d.add(element);
    }

    /* renamed from: o */
    public void m23095o() {
        m23089l("tr", "template");
    }

    /* renamed from: o0 */
    public void m23096o0(Element element) {
        int size = this.f20172q.size() - 1;
        int i9 = 0;
        while (true) {
            if (size >= 0) {
                Element element2 = this.f20172q.get(size);
                if (element2 == null) {
                    break;
                }
                if (m23072a0(element, element2)) {
                    i9++;
                }
                if (i9 == 3) {
                    this.f20172q.remove(size);
                    break;
                }
                size--;
            } else {
                break;
            }
        }
        this.f20172q.add(element);
    }

    /* renamed from: p */
    public void m23097p(HtmlTreeBuilderState htmlTreeBuilderState) {
        if (this.f20204g.m22989a()) {
            this.f20204g.add(new C5031b(this.f20198a.m19615F(), "Unexpected token [%s] when in state [%s]", this.f20203f.m23004o(), htmlTreeBuilderState));
        }
    }

    /* renamed from: p0 */
    public void m23098p0() {
        Element elementM23076c0 = m23076c0();
        if (elementM23076c0 == null || m23081g0(elementM23076c0)) {
            return;
        }
        boolean z8 = true;
        int size = this.f20172q.size() - 1;
        int i9 = size;
        while (i9 != 0) {
            i9--;
            elementM23076c0 = this.f20172q.get(i9);
            if (elementM23076c0 == null || m23081g0(elementM23076c0)) {
                z8 = false;
                break;
            }
        }
        while (true) {
            if (!z8) {
                i9++;
                elementM23076c0 = this.f20172q.get(i9);
            }
            C4772d.m19004j(elementM23076c0);
            Element elementM23067V = m23067V(elementM23076c0.mo22827v());
            elementM23067V.mo19257e().m22912d(elementM23076c0.mo19257e());
            this.f20172q.set(i9, elementM23067V);
            if (i9 == size) {
                return;
            } else {
                z8 = false;
            }
        }
    }

    /* renamed from: q */
    public void m23099q(boolean z8) {
        this.f20175t = z8;
    }

    /* renamed from: q0 */
    public void m23100q0(Element element) {
        for (int size = this.f20172q.size() - 1; size >= 0; size--) {
            if (this.f20172q.get(size) == element) {
                this.f20172q.remove(size);
                return;
            }
        }
    }

    /* renamed from: r */
    public boolean m23101r() {
        return this.f20175t;
    }

    /* renamed from: r0 */
    public boolean m23102r0(Element element) {
        for (int size = this.f20201d.size() - 1; size >= 0; size--) {
            if (this.f20201d.get(size) == element) {
                this.f20201d.remove(size);
                return true;
            }
        }
        return false;
    }

    /* renamed from: s */
    public void m23103s() {
        m23105t(null);
    }

    /* renamed from: s0 */
    public Element m23104s0() {
        int size = this.f20172q.size();
        if (size > 0) {
            return this.f20172q.remove(size - 1);
        }
        return null;
    }

    /* renamed from: t */
    public void m23105t(String str) {
        while (str != null && !m23140a().mo22827v().equals(str) && C4771c.m18981c(m23140a().mo22827v(), f20161C)) {
            m23084i0();
        }
    }

    /* renamed from: t0 */
    public void m23106t0(Element element, Element element2) {
        m23108u0(this.f20172q, element, element2);
    }

    public String toString() {
        return "TreeBuilder{currentToken=" + this.f20203f + ", state=" + this.f20166k + ", currentElement=" + m23140a() + '}';
    }

    /* renamed from: u */
    public Element m23107u(String str) {
        for (int size = this.f20172q.size() - 1; size >= 0; size--) {
            Element element = this.f20172q.get(size);
            if (element == null) {
                return null;
            }
            if (element.mo22827v().equals(str)) {
                return element;
            }
        }
        return null;
    }

    /* renamed from: u0 */
    public final void m23108u0(ArrayList<Element> arrayList, Element element, Element element2) {
        int iLastIndexOf = arrayList.lastIndexOf(element);
        C4772d.m18998d(iLastIndexOf != -1);
        arrayList.set(iLastIndexOf, element2);
    }

    /* renamed from: v */
    public String m23109v() {
        return this.f20202e;
    }

    /* renamed from: v0 */
    public void m23110v0(Element element, Element element2) {
        m23108u0(this.f20201d, element, element2);
    }

    /* renamed from: w */
    public Document m23111w() {
        return this.f20200c;
    }

    /* renamed from: w0 */
    public void m23112w0() {
        boolean z8 = false;
        for (int size = this.f20201d.size() - 1; size >= 0; size--) {
            Element element = this.f20201d.get(size);
            if (size == 0) {
                element = this.f20171p;
                z8 = true;
            }
            String strMo22827v = element.mo22827v();
            if ("select".equals(strMo22827v)) {
                m23047B0(HtmlTreeBuilderState.InSelect);
                return;
            }
            if ("td".equals(strMo22827v) || ("th".equals(strMo22827v) && !z8)) {
                m23047B0(HtmlTreeBuilderState.InCell);
                return;
            }
            if ("tr".equals(strMo22827v)) {
                m23047B0(HtmlTreeBuilderState.InRow);
                return;
            }
            if ("tbody".equals(strMo22827v) || "thead".equals(strMo22827v) || "tfoot".equals(strMo22827v)) {
                m23047B0(HtmlTreeBuilderState.InTableBody);
                return;
            }
            if ("caption".equals(strMo22827v)) {
                m23047B0(HtmlTreeBuilderState.InCaption);
                return;
            }
            if ("colgroup".equals(strMo22827v)) {
                m23047B0(HtmlTreeBuilderState.InColumnGroup);
                return;
            }
            if ("table".equals(strMo22827v)) {
                m23047B0(HtmlTreeBuilderState.InTable);
                return;
            }
            if (TtmlNode.TAG_HEAD.equals(strMo22827v)) {
                m23047B0(HtmlTreeBuilderState.InBody);
                return;
            }
            if (TtmlNode.TAG_BODY.equals(strMo22827v)) {
                m23047B0(HtmlTreeBuilderState.InBody);
                return;
            }
            if ("frameset".equals(strMo22827v)) {
                m23047B0(HtmlTreeBuilderState.InFrameset);
                return;
            } else if ("html".equals(strMo22827v)) {
                m23047B0(HtmlTreeBuilderState.BeforeHead);
                return;
            } else {
                if (z8) {
                    m23047B0(HtmlTreeBuilderState.InBody);
                    return;
                }
            }
        }
    }

    /* renamed from: x */
    public C4973b m23113x() {
        return this.f20170o;
    }

    /* renamed from: x0 */
    public void m23114x0(C4973b c4973b) {
        this.f20170o = c4973b;
    }

    /* renamed from: y */
    public Element m23115y(String str) {
        for (int size = this.f20201d.size() - 1; size >= 0; size--) {
            Element element = this.f20201d.get(size);
            if (element.mo22827v().equals(str)) {
                return element;
            }
        }
        return null;
    }

    /* renamed from: y0 */
    public void m23116y0(boolean z8) {
        this.f20176u = z8;
    }

    /* renamed from: z */
    public Element m23117z() {
        return this.f20169n;
    }

    /* renamed from: z0 */
    public void m23118z0(Element element) {
        this.f20169n = element;
    }
}
