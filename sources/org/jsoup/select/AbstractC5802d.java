package org.jsoup.select;

import java.util.Iterator;
import org.jsoup.nodes.Element;

/* renamed from: org.jsoup.select.d */
/* loaded from: classes.dex */
public abstract class AbstractC5802d extends AbstractC5800b {

    /* renamed from: a */
    public AbstractC5800b f20236a;

    /* renamed from: org.jsoup.select.d$a */
    public static class a extends AbstractC5802d {
        public a(AbstractC5800b abstractC5800b) {
            this.f20236a = abstractC5800b;
        }

        @Override // org.jsoup.select.AbstractC5800b
        /* renamed from: a */
        public boolean mo23160a(Element element, Element element2) {
            Iterator<Element> it = element2.m22859f0().iterator();
            while (it.hasNext()) {
                Element next = it.next();
                if (next != element2 && this.f20236a.mo23160a(element, next)) {
                    return true;
                }
            }
            return false;
        }

        public String toString() {
            return String.format(":has(%s)", this.f20236a);
        }
    }

    /* renamed from: org.jsoup.select.d$b */
    public static class b extends AbstractC5802d {
        public b(AbstractC5800b abstractC5800b) {
            this.f20236a = abstractC5800b;
        }

        @Override // org.jsoup.select.AbstractC5800b
        /* renamed from: a */
        public boolean mo23160a(Element element, Element element2) {
            Element elementM22869p0;
            return (element == element2 || (elementM22869p0 = element2.m22869p0()) == null || !this.f20236a.mo23160a(element, elementM22869p0)) ? false : true;
        }

        public String toString() {
            return String.format(":ImmediateParent%s", this.f20236a);
        }
    }

    /* renamed from: org.jsoup.select.d$c */
    public static class c extends AbstractC5802d {
        public c(AbstractC5800b abstractC5800b) {
            this.f20236a = abstractC5800b;
        }

        @Override // org.jsoup.select.AbstractC5800b
        /* renamed from: a */
        public boolean mo23160a(Element element, Element element2) {
            Element elementM22870r0;
            return (element == element2 || (elementM22870r0 = element2.m22870r0()) == null || !this.f20236a.mo23160a(element, elementM22870r0)) ? false : true;
        }

        public String toString() {
            return String.format(":prev%s", this.f20236a);
        }
    }

    /* renamed from: org.jsoup.select.d$d */
    public static class d extends AbstractC5802d {
        public d(AbstractC5800b abstractC5800b) {
            this.f20236a = abstractC5800b;
        }

        @Override // org.jsoup.select.AbstractC5800b
        /* renamed from: a */
        public boolean mo23160a(Element element, Element element2) {
            return !this.f20236a.mo23160a(element, element2);
        }

        public String toString() {
            return String.format(":not%s", this.f20236a);
        }
    }

    /* renamed from: org.jsoup.select.d$e */
    public static class e extends AbstractC5802d {
        public e(AbstractC5800b abstractC5800b) {
            this.f20236a = abstractC5800b;
        }

        @Override // org.jsoup.select.AbstractC5800b
        /* renamed from: a */
        public boolean mo23160a(Element element, Element element2) {
            if (element == element2) {
                return false;
            }
            for (Element elementM22869p0 = element2.m22869p0(); !this.f20236a.mo23160a(element, elementM22869p0); elementM22869p0 = elementM22869p0.m22869p0()) {
                if (elementM22869p0 == element) {
                    return false;
                }
            }
            return true;
        }

        public String toString() {
            return String.format(":parent%s", this.f20236a);
        }
    }

    /* renamed from: org.jsoup.select.d$f */
    public static class f extends AbstractC5802d {
        public f(AbstractC5800b abstractC5800b) {
            this.f20236a = abstractC5800b;
        }

        @Override // org.jsoup.select.AbstractC5800b
        /* renamed from: a */
        public boolean mo23160a(Element element, Element element2) {
            if (element == element2) {
                return false;
            }
            for (Element elementM22870r0 = element2.m22870r0(); elementM22870r0 != null; elementM22870r0 = elementM22870r0.m22870r0()) {
                if (this.f20236a.mo23160a(element, elementM22870r0)) {
                    return true;
                }
            }
            return false;
        }

        public String toString() {
            return String.format(":prev*%s", this.f20236a);
        }
    }

    /* renamed from: org.jsoup.select.d$g */
    public static class g extends AbstractC5800b {
        @Override // org.jsoup.select.AbstractC5800b
        /* renamed from: a */
        public boolean mo23160a(Element element, Element element2) {
            return element == element2;
        }
    }
}
