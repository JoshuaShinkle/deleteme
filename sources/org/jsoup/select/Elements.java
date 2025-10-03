package org.jsoup.select;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.jsoup.nodes.Element;

/* loaded from: classes.dex */
public class Elements extends ArrayList<Element> {
    public Elements() {
    }

    /* renamed from: a */
    public String m23151a(String str) {
        Iterator<Element> it = iterator();
        while (it.hasNext()) {
            Element next = it.next();
            if (next.mo19262q(str)) {
                return next.mo19255c(str);
            }
        }
        return "";
    }

    @Override // java.util.ArrayList
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public Elements clone() {
        Elements elements = new Elements(size());
        Iterator<Element> it = iterator();
        while (it.hasNext()) {
            elements.add(it.next().mo22826l());
        }
        return elements;
    }

    /* renamed from: c */
    public Element m23153c() {
        if (isEmpty()) {
            return null;
        }
        return get(0);
    }

    /* renamed from: d */
    public String m23154d() {
        StringBuilder sb = new StringBuilder();
        Iterator<Element> it = iterator();
        while (it.hasNext()) {
            Element next = it.next();
            if (sb.length() != 0) {
                sb.append("\n");
            }
            sb.append(next.mo22828x());
        }
        return sb.toString();
    }

    @Override // java.util.AbstractCollection
    public String toString() {
        return m23154d();
    }

    public Elements(int i9) {
        super(i9);
    }

    public Elements(List<Element> list) {
        super(list);
    }
}
