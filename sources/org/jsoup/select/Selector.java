package org.jsoup.select;

import org.jsoup.nodes.Element;
import p060e8.C4772d;
import p100i8.C5062a;

/* loaded from: classes.dex */
public class Selector {

    public static class SelectorParseException extends IllegalStateException {
        public SelectorParseException(String str, Object... objArr) {
            super(String.format(str, objArr));
        }
    }

    /* renamed from: a */
    public static Elements m23155a(String str, Element element) {
        C4772d.m19002h(str);
        return m23156b(C5801c.m23164t(str), element);
    }

    /* renamed from: b */
    public static Elements m23156b(AbstractC5800b abstractC5800b, Element element) {
        C4772d.m19004j(abstractC5800b);
        C4772d.m19004j(element);
        return C5062a.m19829a(abstractC5800b, element);
    }
}
