package p100i8;

import org.jsoup.nodes.AbstractC5690g;
import org.jsoup.nodes.Element;
import org.jsoup.select.AbstractC5800b;
import org.jsoup.select.Elements;

/* renamed from: i8.a */
/* loaded from: classes.dex */
public class C5062a {

    /* renamed from: i8.a$a */
    public static class a implements InterfaceC5064c {

        /* renamed from: a */
        public final Element f17475a;

        /* renamed from: b */
        public final Elements f17476b;

        /* renamed from: c */
        public final AbstractC5800b f17477c;

        public a(Element element, Elements elements, AbstractC5800b abstractC5800b) {
            this.f17475a = element;
            this.f17476b = elements;
            this.f17477c = abstractC5800b;
        }

        @Override // p100i8.InterfaceC5064c
        /* renamed from: a */
        public void mo19830a(AbstractC5690g abstractC5690g, int i9) {
            if (abstractC5690g instanceof Element) {
                Element element = (Element) abstractC5690g;
                if (this.f17477c.mo23160a(this.f17475a, element)) {
                    this.f17476b.add(element);
                }
            }
        }

        @Override // p100i8.InterfaceC5064c
        /* renamed from: b */
        public void mo19831b(AbstractC5690g abstractC5690g, int i9) {
        }
    }

    /* renamed from: a */
    public static Elements m19829a(AbstractC5800b abstractC5800b, Element element) {
        Elements elements = new Elements();
        C5063b.m19832a(new a(element, elements, abstractC5800b), element);
        return elements;
    }
}
