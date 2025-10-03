package org.jsoup.select;

import java.util.Iterator;
import java.util.regex.Pattern;
import org.jsoup.nodes.AbstractC5690g;
import org.jsoup.nodes.C5684a;
import org.jsoup.nodes.C5687d;
import org.jsoup.nodes.C5689f;
import org.jsoup.nodes.C5691h;
import org.jsoup.nodes.C5692i;
import org.jsoup.nodes.C5693j;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import p060e8.C4772d;
import p070f8.C4794b;
import p090h8.C5034e;

/* renamed from: org.jsoup.select.b */
/* loaded from: classes.dex */
public abstract class AbstractC5800b {

    /* renamed from: org.jsoup.select.b$a */
    public static final class a extends AbstractC5800b {
        @Override // org.jsoup.select.AbstractC5800b
        /* renamed from: a */
        public boolean mo23160a(Element element, Element element2) {
            return true;
        }

        public String toString() {
            return "*";
        }
    }

    /* renamed from: org.jsoup.select.b$a0 */
    public static final class a0 extends o {
        public a0(int i9, int i10) {
            super(i9, i10);
        }

        @Override // org.jsoup.select.AbstractC5800b.o
        /* renamed from: b */
        public int mo23162b(Element element, Element element2) {
            return element2.m22869p0().m22855a0().size() - element2.m22858e0();
        }

        @Override // org.jsoup.select.AbstractC5800b.o
        /* renamed from: c */
        public String mo23163c() {
            return "nth-last-child";
        }
    }

    /* renamed from: org.jsoup.select.b$b */
    public static final class b extends AbstractC5800b {

        /* renamed from: a */
        public String f20211a;

        public b(String str) {
            this.f20211a = str;
        }

        @Override // org.jsoup.select.AbstractC5800b
        /* renamed from: a */
        public boolean mo23160a(Element element, Element element2) {
            return element2.mo19262q(this.f20211a);
        }

        public String toString() {
            return String.format("[%s]", this.f20211a);
        }
    }

    /* renamed from: org.jsoup.select.b$b0 */
    public static class b0 extends o {
        public b0(int i9, int i10) {
            super(i9, i10);
        }

        @Override // org.jsoup.select.AbstractC5800b.o
        /* renamed from: b */
        public int mo23162b(Element element, Element element2) {
            Elements elementsM22855a0 = element2.m22869p0().m22855a0();
            int i9 = 0;
            for (int iM22858e0 = element2.m22858e0(); iM22858e0 < elementsM22855a0.size(); iM22858e0++) {
                if (elementsM22855a0.get(iM22858e0).m22873u0().equals(element2.m22873u0())) {
                    i9++;
                }
            }
            return i9;
        }

        @Override // org.jsoup.select.AbstractC5800b.o
        /* renamed from: c */
        public String mo23163c() {
            return "nth-last-of-type";
        }
    }

    /* renamed from: org.jsoup.select.b$c */
    public static abstract class c extends AbstractC5800b {

        /* renamed from: a */
        public String f20212a;

        /* renamed from: b */
        public String f20213b;

        public c(String str, String str2) {
            C4772d.m19002h(str);
            C4772d.m19002h(str2);
            this.f20212a = C4794b.m19031b(str);
            if ((str2.startsWith("\"") && str2.endsWith("\"")) || (str2.startsWith("'") && str2.endsWith("'"))) {
                str2 = str2.substring(1, str2.length() - 1);
            }
            this.f20213b = C4794b.m19031b(str2);
        }
    }

    /* renamed from: org.jsoup.select.b$c0 */
    public static class c0 extends o {
        public c0(int i9, int i10) {
            super(i9, i10);
        }

        @Override // org.jsoup.select.AbstractC5800b.o
        /* renamed from: b */
        public int mo23162b(Element element, Element element2) {
            Iterator<Element> it = element2.m22869p0().m22855a0().iterator();
            int i9 = 0;
            while (it.hasNext()) {
                Element next = it.next();
                if (next.m22873u0().equals(element2.m22873u0())) {
                    i9++;
                }
                if (next == element2) {
                    break;
                }
            }
            return i9;
        }

        @Override // org.jsoup.select.AbstractC5800b.o
        /* renamed from: c */
        public String mo23163c() {
            return "nth-of-type";
        }
    }

    /* renamed from: org.jsoup.select.b$d */
    public static final class d extends AbstractC5800b {

        /* renamed from: a */
        public String f20214a;

        public d(String str) {
            C4772d.m19002h(str);
            this.f20214a = C4794b.m19030a(str);
        }

        @Override // org.jsoup.select.AbstractC5800b
        /* renamed from: a */
        public boolean mo23160a(Element element, Element element2) {
            Iterator<C5684a> it = element2.mo19257e().m22913e().iterator();
            while (it.hasNext()) {
                if (C4794b.m19030a(it.next().getKey()).startsWith(this.f20214a)) {
                    return true;
                }
            }
            return false;
        }

        public String toString() {
            return String.format("[^%s]", this.f20214a);
        }
    }

    /* renamed from: org.jsoup.select.b$d0 */
    public static final class d0 extends AbstractC5800b {
        @Override // org.jsoup.select.AbstractC5800b
        /* renamed from: a */
        public boolean mo23160a(Element element, Element element2) {
            Element elementM22869p0 = element2.m22869p0();
            return (elementM22869p0 == null || (elementM22869p0 instanceof Document) || element2.m22872t0().size() != 0) ? false : true;
        }

        public String toString() {
            return ":only-child";
        }
    }

    /* renamed from: org.jsoup.select.b$e */
    public static final class e extends c {
        public e(String str, String str2) {
            super(str, str2);
        }

        @Override // org.jsoup.select.AbstractC5800b
        /* renamed from: a */
        public boolean mo23160a(Element element, Element element2) {
            return element2.mo19262q(this.f20212a) && this.f20213b.equalsIgnoreCase(element2.mo19255c(this.f20212a).trim());
        }

        public String toString() {
            return String.format("[%s=%s]", this.f20212a, this.f20213b);
        }
    }

    /* renamed from: org.jsoup.select.b$e0 */
    public static final class e0 extends AbstractC5800b {
        @Override // org.jsoup.select.AbstractC5800b
        /* renamed from: a */
        public boolean mo23160a(Element element, Element element2) {
            Element elementM22869p0 = element2.m22869p0();
            if (elementM22869p0 == null || (elementM22869p0 instanceof Document)) {
                return false;
            }
            Iterator<Element> it = elementM22869p0.m22855a0().iterator();
            int i9 = 0;
            while (it.hasNext()) {
                if (it.next().m22873u0().equals(element2.m22873u0())) {
                    i9++;
                }
            }
            return i9 == 1;
        }

        public String toString() {
            return ":only-of-type";
        }
    }

    /* renamed from: org.jsoup.select.b$f */
    public static final class f extends c {
        public f(String str, String str2) {
            super(str, str2);
        }

        @Override // org.jsoup.select.AbstractC5800b
        /* renamed from: a */
        public boolean mo23160a(Element element, Element element2) {
            return element2.mo19262q(this.f20212a) && C4794b.m19030a(element2.mo19255c(this.f20212a)).contains(this.f20213b);
        }

        public String toString() {
            return String.format("[%s*=%s]", this.f20212a, this.f20213b);
        }
    }

    /* renamed from: org.jsoup.select.b$f0 */
    public static final class f0 extends AbstractC5800b {
        @Override // org.jsoup.select.AbstractC5800b
        /* renamed from: a */
        public boolean mo23160a(Element element, Element element2) {
            if (element instanceof Document) {
                element = element.m22853Y(0);
            }
            return element2 == element;
        }

        public String toString() {
            return ":root";
        }
    }

    /* renamed from: org.jsoup.select.b$g */
    public static final class g extends c {
        public g(String str, String str2) {
            super(str, str2);
        }

        @Override // org.jsoup.select.AbstractC5800b
        /* renamed from: a */
        public boolean mo23160a(Element element, Element element2) {
            return element2.mo19262q(this.f20212a) && C4794b.m19030a(element2.mo19255c(this.f20212a)).endsWith(this.f20213b);
        }

        public String toString() {
            return String.format("[%s$=%s]", this.f20212a, this.f20213b);
        }
    }

    /* renamed from: org.jsoup.select.b$g0 */
    public static final class g0 extends AbstractC5800b {
        @Override // org.jsoup.select.AbstractC5800b
        /* renamed from: a */
        public boolean mo23160a(Element element, Element element2) {
            if (element2 instanceof C5691h) {
                return true;
            }
            for (C5692i c5692i : element2.m22877x0()) {
                C5691h c5691h = new C5691h(C5034e.m19651k(element2.m22874v0()), element2.mo19258g(), element2.mo19257e());
                c5692i.m22941J(c5691h);
                c5691h.m22850T(c5692i);
            }
            return false;
        }

        public String toString() {
            return ":matchText";
        }
    }

    /* renamed from: org.jsoup.select.b$h */
    public static final class h extends AbstractC5800b {

        /* renamed from: a */
        public String f20215a;

        /* renamed from: b */
        public Pattern f20216b;

        public h(String str, Pattern pattern) {
            this.f20215a = C4794b.m19031b(str);
            this.f20216b = pattern;
        }

        @Override // org.jsoup.select.AbstractC5800b
        /* renamed from: a */
        public boolean mo23160a(Element element, Element element2) {
            return element2.mo19262q(this.f20215a) && this.f20216b.matcher(element2.mo19255c(this.f20215a)).find();
        }

        public String toString() {
            return String.format("[%s~=%s]", this.f20215a, this.f20216b.toString());
        }
    }

    /* renamed from: org.jsoup.select.b$h0 */
    public static final class h0 extends AbstractC5800b {

        /* renamed from: a */
        public Pattern f20217a;

        public h0(Pattern pattern) {
            this.f20217a = pattern;
        }

        @Override // org.jsoup.select.AbstractC5800b
        /* renamed from: a */
        public boolean mo23160a(Element element, Element element2) {
            return this.f20217a.matcher(element2.m22876w0()).find();
        }

        public String toString() {
            return String.format(":matches(%s)", this.f20217a);
        }
    }

    /* renamed from: org.jsoup.select.b$i */
    public static final class i extends c {
        public i(String str, String str2) {
            super(str, str2);
        }

        @Override // org.jsoup.select.AbstractC5800b
        /* renamed from: a */
        public boolean mo23160a(Element element, Element element2) {
            return !this.f20213b.equalsIgnoreCase(element2.mo19255c(this.f20212a));
        }

        public String toString() {
            return String.format("[%s!=%s]", this.f20212a, this.f20213b);
        }
    }

    /* renamed from: org.jsoup.select.b$i0 */
    public static final class i0 extends AbstractC5800b {

        /* renamed from: a */
        public Pattern f20218a;

        public i0(Pattern pattern) {
            this.f20218a = pattern;
        }

        @Override // org.jsoup.select.AbstractC5800b
        /* renamed from: a */
        public boolean mo23160a(Element element, Element element2) {
            return this.f20218a.matcher(element2.m22867n0()).find();
        }

        public String toString() {
            return String.format(":matchesOwn(%s)", this.f20218a);
        }
    }

    /* renamed from: org.jsoup.select.b$j */
    public static final class j extends c {
        public j(String str, String str2) {
            super(str, str2);
        }

        @Override // org.jsoup.select.AbstractC5800b
        /* renamed from: a */
        public boolean mo23160a(Element element, Element element2) {
            return element2.mo19262q(this.f20212a) && C4794b.m19030a(element2.mo19255c(this.f20212a)).startsWith(this.f20213b);
        }

        public String toString() {
            return String.format("[%s^=%s]", this.f20212a, this.f20213b);
        }
    }

    /* renamed from: org.jsoup.select.b$j0 */
    public static final class j0 extends AbstractC5800b {

        /* renamed from: a */
        public String f20219a;

        public j0(String str) {
            this.f20219a = str;
        }

        @Override // org.jsoup.select.AbstractC5800b
        /* renamed from: a */
        public boolean mo23160a(Element element, Element element2) {
            return element2.m22874v0().equalsIgnoreCase(this.f20219a);
        }

        public String toString() {
            return String.format("%s", this.f20219a);
        }
    }

    /* renamed from: org.jsoup.select.b$k */
    public static final class k extends AbstractC5800b {

        /* renamed from: a */
        public String f20220a;

        public k(String str) {
            this.f20220a = str;
        }

        @Override // org.jsoup.select.AbstractC5800b
        /* renamed from: a */
        public boolean mo23160a(Element element, Element element2) {
            return element2.m22861h0(this.f20220a);
        }

        public String toString() {
            return String.format(".%s", this.f20220a);
        }
    }

    /* renamed from: org.jsoup.select.b$k0 */
    public static final class k0 extends AbstractC5800b {

        /* renamed from: a */
        public String f20221a;

        public k0(String str) {
            this.f20221a = str;
        }

        @Override // org.jsoup.select.AbstractC5800b
        /* renamed from: a */
        public boolean mo23160a(Element element, Element element2) {
            return element2.m22874v0().endsWith(this.f20221a);
        }

        public String toString() {
            return String.format("%s", this.f20221a);
        }
    }

    /* renamed from: org.jsoup.select.b$l */
    public static final class l extends AbstractC5800b {

        /* renamed from: a */
        public String f20222a;

        public l(String str) {
            this.f20222a = C4794b.m19030a(str);
        }

        @Override // org.jsoup.select.AbstractC5800b
        /* renamed from: a */
        public boolean mo23160a(Element element, Element element2) {
            return C4794b.m19030a(element2.m22856c0()).contains(this.f20222a);
        }

        public String toString() {
            return String.format(":containsData(%s)", this.f20222a);
        }
    }

    /* renamed from: org.jsoup.select.b$m */
    public static final class m extends AbstractC5800b {

        /* renamed from: a */
        public String f20223a;

        public m(String str) {
            this.f20223a = C4794b.m19030a(str);
        }

        @Override // org.jsoup.select.AbstractC5800b
        /* renamed from: a */
        public boolean mo23160a(Element element, Element element2) {
            return C4794b.m19030a(element2.m22867n0()).contains(this.f20223a);
        }

        public String toString() {
            return String.format(":containsOwn(%s)", this.f20223a);
        }
    }

    /* renamed from: org.jsoup.select.b$n */
    public static final class n extends AbstractC5800b {

        /* renamed from: a */
        public String f20224a;

        public n(String str) {
            this.f20224a = C4794b.m19030a(str);
        }

        @Override // org.jsoup.select.AbstractC5800b
        /* renamed from: a */
        public boolean mo23160a(Element element, Element element2) {
            return C4794b.m19030a(element2.m22876w0()).contains(this.f20224a);
        }

        public String toString() {
            return String.format(":contains(%s)", this.f20224a);
        }
    }

    /* renamed from: org.jsoup.select.b$o */
    public static abstract class o extends AbstractC5800b {

        /* renamed from: a */
        public final int f20225a;

        /* renamed from: b */
        public final int f20226b;

        public o(int i9, int i10) {
            this.f20225a = i9;
            this.f20226b = i10;
        }

        @Override // org.jsoup.select.AbstractC5800b
        /* renamed from: a */
        public boolean mo23160a(Element element, Element element2) {
            Element elementM22869p0 = element2.m22869p0();
            if (elementM22869p0 == null || (elementM22869p0 instanceof Document)) {
                return false;
            }
            int iMo23162b = mo23162b(element, element2);
            int i9 = this.f20225a;
            if (i9 == 0) {
                return iMo23162b == this.f20226b;
            }
            int i10 = this.f20226b;
            return (iMo23162b - i10) * i9 >= 0 && (iMo23162b - i10) % i9 == 0;
        }

        /* renamed from: b */
        public abstract int mo23162b(Element element, Element element2);

        /* renamed from: c */
        public abstract String mo23163c();

        public String toString() {
            return this.f20225a == 0 ? String.format(":%s(%d)", mo23163c(), Integer.valueOf(this.f20226b)) : this.f20226b == 0 ? String.format(":%s(%dn)", mo23163c(), Integer.valueOf(this.f20225a)) : String.format(":%s(%dn%+d)", mo23163c(), Integer.valueOf(this.f20225a), Integer.valueOf(this.f20226b));
        }
    }

    /* renamed from: org.jsoup.select.b$p */
    public static final class p extends AbstractC5800b {

        /* renamed from: a */
        public String f20227a;

        public p(String str) {
            this.f20227a = str;
        }

        @Override // org.jsoup.select.AbstractC5800b
        /* renamed from: a */
        public boolean mo23160a(Element element, Element element2) {
            return this.f20227a.equals(element2.m22864k0());
        }

        public String toString() {
            return String.format("#%s", this.f20227a);
        }
    }

    /* renamed from: org.jsoup.select.b$q */
    public static final class q extends r {
        public q(int i9) {
            super(i9);
        }

        @Override // org.jsoup.select.AbstractC5800b
        /* renamed from: a */
        public boolean mo23160a(Element element, Element element2) {
            return element2.m22858e0() == this.f20228a;
        }

        public String toString() {
            return String.format(":eq(%d)", Integer.valueOf(this.f20228a));
        }
    }

    /* renamed from: org.jsoup.select.b$r */
    public static abstract class r extends AbstractC5800b {

        /* renamed from: a */
        public int f20228a;

        public r(int i9) {
            this.f20228a = i9;
        }
    }

    /* renamed from: org.jsoup.select.b$s */
    public static final class s extends r {
        public s(int i9) {
            super(i9);
        }

        @Override // org.jsoup.select.AbstractC5800b
        /* renamed from: a */
        public boolean mo23160a(Element element, Element element2) {
            return element2.m22858e0() > this.f20228a;
        }

        public String toString() {
            return String.format(":gt(%d)", Integer.valueOf(this.f20228a));
        }
    }

    /* renamed from: org.jsoup.select.b$t */
    public static final class t extends r {
        public t(int i9) {
            super(i9);
        }

        @Override // org.jsoup.select.AbstractC5800b
        /* renamed from: a */
        public boolean mo23160a(Element element, Element element2) {
            return element != element2 && element2.m22858e0() < this.f20228a;
        }

        public String toString() {
            return String.format(":lt(%d)", Integer.valueOf(this.f20228a));
        }
    }

    /* renamed from: org.jsoup.select.b$u */
    public static final class u extends AbstractC5800b {
        @Override // org.jsoup.select.AbstractC5800b
        /* renamed from: a */
        public boolean mo23160a(Element element, Element element2) {
            for (AbstractC5690g abstractC5690g : element2.m22952k()) {
                if (!(abstractC5690g instanceof C5687d) && !(abstractC5690g instanceof C5693j) && !(abstractC5690g instanceof C5689f)) {
                    return false;
                }
            }
            return true;
        }

        public String toString() {
            return ":empty";
        }
    }

    /* renamed from: org.jsoup.select.b$v */
    public static final class v extends AbstractC5800b {
        @Override // org.jsoup.select.AbstractC5800b
        /* renamed from: a */
        public boolean mo23160a(Element element, Element element2) {
            Element elementM22869p0 = element2.m22869p0();
            return (elementM22869p0 == null || (elementM22869p0 instanceof Document) || element2.m22858e0() != 0) ? false : true;
        }

        public String toString() {
            return ":first-child";
        }
    }

    /* renamed from: org.jsoup.select.b$w */
    public static final class w extends c0 {
        public w() {
            super(0, 1);
        }

        @Override // org.jsoup.select.AbstractC5800b.o
        public String toString() {
            return ":first-of-type";
        }
    }

    /* renamed from: org.jsoup.select.b$x */
    public static final class x extends AbstractC5800b {
        @Override // org.jsoup.select.AbstractC5800b
        /* renamed from: a */
        public boolean mo23160a(Element element, Element element2) {
            Element elementM22869p0 = element2.m22869p0();
            return (elementM22869p0 == null || (elementM22869p0 instanceof Document) || element2.m22858e0() != elementM22869p0.m22855a0().size() - 1) ? false : true;
        }

        public String toString() {
            return ":last-child";
        }
    }

    /* renamed from: org.jsoup.select.b$y */
    public static final class y extends b0 {
        public y() {
            super(0, 1);
        }

        @Override // org.jsoup.select.AbstractC5800b.o
        public String toString() {
            return ":last-of-type";
        }
    }

    /* renamed from: org.jsoup.select.b$z */
    public static final class z extends o {
        public z(int i9, int i10) {
            super(i9, i10);
        }

        @Override // org.jsoup.select.AbstractC5800b.o
        /* renamed from: b */
        public int mo23162b(Element element, Element element2) {
            return element2.m22858e0() + 1;
        }

        @Override // org.jsoup.select.AbstractC5800b.o
        /* renamed from: c */
        public String mo23163c() {
            return "nth-child";
        }
    }

    /* renamed from: a */
    public abstract boolean mo23160a(Element element, Element element2);
}
