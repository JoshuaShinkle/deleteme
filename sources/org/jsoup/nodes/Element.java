package org.jsoup.nodes;

import com.google.android.exoplayer2.text.ttml.TtmlNode;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.helper.ChangeNotifyingArrayList;
import org.jsoup.nodes.Document;
import org.jsoup.select.AbstractC5800b;
import org.jsoup.select.Elements;
import org.jsoup.select.Selector;
import p060e8.C4771c;
import p060e8.C4772d;
import p070f8.C4794b;
import p090h8.C5034e;
import p100i8.C5062a;
import p100i8.C5063b;
import p100i8.InterfaceC5064c;

/* loaded from: classes.dex */
public class Element extends AbstractC5690g {

    /* renamed from: i */
    public static final List<AbstractC5690g> f19980i = Collections.emptyList();

    /* renamed from: j */
    public static final Pattern f19981j = Pattern.compile("\\s+");

    /* renamed from: d */
    public C5034e f19982d;

    /* renamed from: e */
    public WeakReference<List<Element>> f19983e;

    /* renamed from: f */
    public List<AbstractC5690g> f19984f;

    /* renamed from: g */
    public C5685b f19985g;

    /* renamed from: h */
    public String f19986h;

    public static final class NodeList extends ChangeNotifyingArrayList<AbstractC5690g> {
        private final Element owner;

        public NodeList(Element element, int i9) {
            super(i9);
            this.owner = element;
        }

        @Override // org.jsoup.helper.ChangeNotifyingArrayList
        /* renamed from: a */
        public void mo22821a() {
            this.owner.mo22875w();
        }
    }

    /* renamed from: org.jsoup.nodes.Element$a */
    public class C5682a implements InterfaceC5064c {

        /* renamed from: a */
        public final /* synthetic */ StringBuilder f19987a;

        public C5682a(StringBuilder sb) {
            this.f19987a = sb;
        }

        @Override // p100i8.InterfaceC5064c
        /* renamed from: a */
        public void mo19830a(AbstractC5690g abstractC5690g, int i9) {
            if (abstractC5690g instanceof C5692i) {
                Element.m22845U(this.f19987a, (C5692i) abstractC5690g);
            } else if (abstractC5690g instanceof Element) {
                Element element = (Element) abstractC5690g;
                if (this.f19987a.length() > 0) {
                    if ((element.m22866m0() || element.f19982d.m19654b().equals(TtmlNode.TAG_BR)) && !C5692i.m22958V(this.f19987a)) {
                        this.f19987a.append(' ');
                    }
                }
            }
        }

        @Override // p100i8.InterfaceC5064c
        /* renamed from: b */
        public void mo19831b(AbstractC5690g abstractC5690g, int i9) {
            if ((abstractC5690g instanceof Element) && ((Element) abstractC5690g).m22866m0() && (abstractC5690g.m22956u() instanceof C5692i) && !C5692i.m22958V(this.f19987a)) {
                this.f19987a.append(' ');
            }
        }
    }

    public Element(C5034e c5034e, String str, C5685b c5685b) {
        C4772d.m19004j(c5034e);
        C4772d.m19004j(str);
        this.f19984f = f19980i;
        this.f19986h = str;
        this.f19985g = c5685b;
        this.f19982d = c5034e;
    }

    /* renamed from: U */
    public static void m22845U(StringBuilder sb, C5692i c5692i) {
        String strM22959T = c5692i.m22959T();
        if (m22848q0(c5692i.f20011b) || (c5692i instanceof C5686c)) {
            sb.append(strM22959T);
        } else {
            C4771c.m18979a(sb, strM22959T, C5692i.m22958V(sb));
        }
    }

    /* renamed from: V */
    public static void m22846V(Element element, StringBuilder sb) {
        if (!element.f19982d.m19654b().equals(TtmlNode.TAG_BR) || C5692i.m22958V(sb)) {
            return;
        }
        sb.append(StringUtils.SPACE);
    }

    /* renamed from: l0 */
    public static <E extends Element> int m22847l0(Element element, List<E> list) {
        for (int i9 = 0; i9 < list.size(); i9++) {
            if (list.get(i9) == element) {
                return i9;
            }
        }
        return 0;
    }

    /* renamed from: q0 */
    public static boolean m22848q0(AbstractC5690g abstractC5690g) {
        if (abstractC5690g != null && (abstractC5690g instanceof Element)) {
            Element elementM22869p0 = (Element) abstractC5690g;
            int i9 = 0;
            while (!elementM22869p0.f19982d.m19660h()) {
                elementM22869p0 = elementM22869p0.m22869p0();
                i9++;
                if (i9 >= 6 || elementM22869p0 == null) {
                }
            }
            return true;
        }
        return false;
    }

    @Override // org.jsoup.nodes.AbstractC5690g
    /* renamed from: A */
    public void mo22849A(Appendable appendable, int i9, Document.OutputSettings outputSettings) throws IOException {
        if (this.f19984f.isEmpty() && this.f19982d.m19659g()) {
            return;
        }
        if (outputSettings.m22840k() && !this.f19984f.isEmpty() && (this.f19982d.m19653a() || (outputSettings.m22838i() && (this.f19984f.size() > 1 || (this.f19984f.size() == 1 && !(this.f19984f.get(0) instanceof C5692i)))))) {
            m22955t(appendable, i9, outputSettings);
        }
        appendable.append("</").append(m22874v0()).append('>');
    }

    /* renamed from: T */
    public Element m22850T(AbstractC5690g abstractC5690g) {
        C4772d.m19004j(abstractC5690g);
        m22939H(abstractC5690g);
        mo19261o();
        this.f19984f.add(abstractC5690g);
        abstractC5690g.m22945N(this.f19984f.size() - 1);
        return this;
    }

    /* renamed from: W */
    public Element m22851W(String str, String str2) {
        super.mo19256d(str, str2);
        return this;
    }

    /* renamed from: X */
    public Element m22852X(AbstractC5690g abstractC5690g) {
        return (Element) super.m22950h(abstractC5690g);
    }

    /* renamed from: Y */
    public Element m22853Y(int i9) {
        return m22854Z().get(i9);
    }

    /* renamed from: Z */
    public final List<Element> m22854Z() {
        List<Element> list;
        WeakReference<List<Element>> weakReference = this.f19983e;
        if (weakReference != null && (list = weakReference.get()) != null) {
            return list;
        }
        int size = this.f19984f.size();
        ArrayList arrayList = new ArrayList(size);
        for (int i9 = 0; i9 < size; i9++) {
            AbstractC5690g abstractC5690g = this.f19984f.get(i9);
            if (abstractC5690g instanceof Element) {
                arrayList.add((Element) abstractC5690g);
            }
        }
        this.f19983e = new WeakReference<>(arrayList);
        return arrayList;
    }

    /* renamed from: a0 */
    public Elements m22855a0() {
        return new Elements(m22854Z());
    }

    @Override // org.jsoup.nodes.AbstractC5690g
    /* renamed from: b0, reason: merged with bridge method [inline-methods] */
    public Element mo22826l() {
        return (Element) super.mo22826l();
    }

    /* renamed from: c0 */
    public String m22856c0() {
        StringBuilder sb = new StringBuilder();
        for (AbstractC5690g abstractC5690g : this.f19984f) {
            if (abstractC5690g instanceof C5688e) {
                sb.append(((C5688e) abstractC5690g).m22931T());
            } else if (abstractC5690g instanceof C5687d) {
                sb.append(((C5687d) abstractC5690g).m22930T());
            } else if (abstractC5690g instanceof Element) {
                sb.append(((Element) abstractC5690g).m22856c0());
            } else if (abstractC5690g instanceof C5686c) {
                sb.append(((C5686c) abstractC5690g).m22959T());
            }
        }
        return sb.toString();
    }

    @Override // org.jsoup.nodes.AbstractC5690g
    /* renamed from: d0, reason: merged with bridge method [inline-methods] */
    public Element mo22865m(AbstractC5690g abstractC5690g) {
        Element element = (Element) super.mo22865m(abstractC5690g);
        C5685b c5685b = this.f19985g;
        element.f19985g = c5685b != null ? c5685b.clone() : null;
        element.f19986h = this.f19986h;
        NodeList nodeList = new NodeList(element, this.f19984f.size());
        element.f19984f = nodeList;
        nodeList.addAll(this.f19984f);
        return element;
    }

    @Override // org.jsoup.nodes.AbstractC5690g
    /* renamed from: e */
    public C5685b mo19257e() {
        if (!mo19263r()) {
            this.f19985g = new C5685b();
        }
        return this.f19985g;
    }

    /* renamed from: e0 */
    public int m22858e0() {
        if (m22869p0() == null) {
            return 0;
        }
        return m22847l0(this, m22869p0().m22854Z());
    }

    /* renamed from: f0 */
    public Elements m22859f0() {
        return C5062a.m19829a(new AbstractC5800b.a(), this);
    }

    @Override // org.jsoup.nodes.AbstractC5690g
    /* renamed from: g */
    public String mo19258g() {
        return this.f19986h;
    }

    /* renamed from: g0 */
    public Elements m22860g0(String str) {
        C4772d.m19002h(str);
        return C5062a.m19829a(new AbstractC5800b.j0(C4794b.m19031b(str)), this);
    }

    /* renamed from: h0 */
    public boolean m22861h0(String str) {
        String strM22917l = mo19257e().m22917l("class");
        int length = strM22917l.length();
        int length2 = str.length();
        if (length != 0 && length >= length2) {
            if (length == length2) {
                return str.equalsIgnoreCase(strM22917l);
            }
            boolean z8 = false;
            int i9 = 0;
            for (int i10 = 0; i10 < length; i10++) {
                if (Character.isWhitespace(strM22917l.charAt(i10))) {
                    if (!z8) {
                        continue;
                    } else {
                        if (i10 - i9 == length2 && strM22917l.regionMatches(true, i9, str, 0, length2)) {
                            return true;
                        }
                        z8 = false;
                    }
                } else if (!z8) {
                    i9 = i10;
                    z8 = true;
                }
            }
            if (z8 && length - i9 == length2) {
                return strM22917l.regionMatches(true, i9, str, 0, length2);
            }
        }
        return false;
    }

    /* renamed from: i0 */
    public String m22862i0() {
        StringBuilder sbM18993o = C4771c.m18993o();
        m22863j0(sbM18993o);
        boolean zM22840k = m22953p().m22840k();
        String string = sbM18993o.toString();
        return zM22840k ? string.trim() : string;
    }

    @Override // org.jsoup.nodes.AbstractC5690g
    /* renamed from: j */
    public int mo19259j() {
        return this.f19984f.size();
    }

    /* renamed from: j0 */
    public final void m22863j0(StringBuilder sb) {
        Iterator<AbstractC5690g> it = this.f19984f.iterator();
        while (it.hasNext()) {
            it.next().m22957y(sb);
        }
    }

    /* renamed from: k0 */
    public String m22864k0() {
        return mo19257e().m22917l(TtmlNode.ATTR_ID);
    }

    /* renamed from: m0 */
    public boolean m22866m0() {
        return this.f19982d.m19655c();
    }

    @Override // org.jsoup.nodes.AbstractC5690g
    /* renamed from: n */
    public void mo19260n(String str) {
        this.f19986h = str;
    }

    /* renamed from: n0 */
    public String m22867n0() {
        StringBuilder sb = new StringBuilder();
        m22868o0(sb);
        return sb.toString().trim();
    }

    @Override // org.jsoup.nodes.AbstractC5690g
    /* renamed from: o */
    public List<AbstractC5690g> mo19261o() {
        if (this.f19984f == f19980i) {
            this.f19984f = new NodeList(this, 4);
        }
        return this.f19984f;
    }

    /* renamed from: o0 */
    public final void m22868o0(StringBuilder sb) {
        for (AbstractC5690g abstractC5690g : this.f19984f) {
            if (abstractC5690g instanceof C5692i) {
                m22845U(sb, (C5692i) abstractC5690g);
            } else if (abstractC5690g instanceof Element) {
                m22846V((Element) abstractC5690g, sb);
            }
        }
    }

    /* renamed from: p0 */
    public final Element m22869p0() {
        return (Element) this.f20011b;
    }

    @Override // org.jsoup.nodes.AbstractC5690g
    /* renamed from: r */
    public boolean mo19263r() {
        return this.f19985g != null;
    }

    /* renamed from: r0 */
    public Element m22870r0() {
        if (this.f20011b == null) {
            return null;
        }
        List<Element> listM22854Z = m22869p0().m22854Z();
        Integer numValueOf = Integer.valueOf(m22847l0(this, listM22854Z));
        C4772d.m19004j(numValueOf);
        if (numValueOf.intValue() > 0) {
            return listM22854Z.get(numValueOf.intValue() - 1);
        }
        return null;
    }

    /* renamed from: s0 */
    public Elements m22871s0(String str) {
        return Selector.m23155a(str, this);
    }

    /* renamed from: t0 */
    public Elements m22872t0() {
        if (this.f20011b == null) {
            return new Elements(0);
        }
        List<Element> listM22854Z = m22869p0().m22854Z();
        Elements elements = new Elements(listM22854Z.size() - 1);
        for (Element element : listM22854Z) {
            if (element != this) {
                elements.add(element);
            }
        }
        return elements;
    }

    @Override // org.jsoup.nodes.AbstractC5690g
    public String toString() {
        return mo22828x();
    }

    /* renamed from: u0 */
    public C5034e m22873u0() {
        return this.f19982d;
    }

    @Override // org.jsoup.nodes.AbstractC5690g
    /* renamed from: v */
    public String mo22827v() {
        return this.f19982d.m19654b();
    }

    /* renamed from: v0 */
    public String m22874v0() {
        return this.f19982d.m19654b();
    }

    @Override // org.jsoup.nodes.AbstractC5690g
    /* renamed from: w */
    public void mo22875w() {
        super.mo22875w();
        this.f19983e = null;
    }

    /* renamed from: w0 */
    public String m22876w0() {
        StringBuilder sb = new StringBuilder();
        C5063b.m19832a(new C5682a(sb), this);
        return sb.toString().trim();
    }

    /* renamed from: x0 */
    public List<C5692i> m22877x0() {
        ArrayList arrayList = new ArrayList();
        for (AbstractC5690g abstractC5690g : this.f19984f) {
            if (abstractC5690g instanceof C5692i) {
                arrayList.add((C5692i) abstractC5690g);
            }
        }
        return Collections.unmodifiableList(arrayList);
    }

    @Override // org.jsoup.nodes.AbstractC5690g
    /* renamed from: z */
    public void mo22878z(Appendable appendable, int i9, Document.OutputSettings outputSettings) throws IOException {
        if (outputSettings.m22840k() && ((this.f19982d.m19653a() || ((m22869p0() != null && m22869p0().m22873u0().m19653a()) || outputSettings.m22838i())) && (!(appendable instanceof StringBuilder) || ((StringBuilder) appendable).length() > 0))) {
            m22955t(appendable, i9, outputSettings);
        }
        appendable.append('<').append(m22874v0());
        C5685b c5685b = this.f19985g;
        if (c5685b != null) {
            c5685b.m22921p(appendable, outputSettings);
        }
        if (!this.f19984f.isEmpty() || !this.f19982d.m19659g()) {
            appendable.append('>');
        } else if (outputSettings.m22841l() == Document.OutputSettings.Syntax.html && this.f19982d.m19656d()) {
            appendable.append('>');
        } else {
            appendable.append(" />");
        }
    }

    public Element(C5034e c5034e, String str) {
        this(c5034e, str, null);
    }
}
