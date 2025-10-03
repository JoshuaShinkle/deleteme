package org.jsoup.select;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Element;
import p060e8.C4771c;

/* renamed from: org.jsoup.select.a */
/* loaded from: classes.dex */
public abstract class AbstractC5799a extends AbstractC5800b {

    /* renamed from: a */
    public final ArrayList<AbstractC5800b> f20209a;

    /* renamed from: b */
    public int f20210b;

    /* renamed from: org.jsoup.select.a$a */
    public static final class a extends AbstractC5799a {
        public a(Collection<AbstractC5800b> collection) {
            super(collection);
        }

        @Override // org.jsoup.select.AbstractC5800b
        /* renamed from: a */
        public boolean mo23160a(Element element, Element element2) {
            for (int i9 = 0; i9 < this.f20210b; i9++) {
                if (!this.f20209a.get(i9).mo23160a(element, element2)) {
                    return false;
                }
            }
            return true;
        }

        public String toString() {
            return C4771c.m18987i(this.f20209a, StringUtils.SPACE);
        }

        public a(AbstractC5800b... abstractC5800bArr) {
            this(Arrays.asList(abstractC5800bArr));
        }
    }

    public AbstractC5799a() {
        this.f20210b = 0;
        this.f20209a = new ArrayList<>();
    }

    /* renamed from: b */
    public void m23157b(AbstractC5800b abstractC5800b) {
        this.f20209a.set(this.f20210b - 1, abstractC5800b);
    }

    /* renamed from: c */
    public AbstractC5800b m23158c() {
        int i9 = this.f20210b;
        if (i9 > 0) {
            return this.f20209a.get(i9 - 1);
        }
        return null;
    }

    /* renamed from: d */
    public void m23159d() {
        this.f20210b = this.f20209a.size();
    }

    public AbstractC5799a(Collection<AbstractC5800b> collection) {
        this();
        this.f20209a.addAll(collection);
        m23159d();
    }

    /* renamed from: org.jsoup.select.a$b */
    public static final class b extends AbstractC5799a {
        public b(Collection<AbstractC5800b> collection) {
            if (this.f20210b > 1) {
                this.f20209a.add(new a(collection));
            } else {
                this.f20209a.addAll(collection);
            }
            m23159d();
        }

        @Override // org.jsoup.select.AbstractC5800b
        /* renamed from: a */
        public boolean mo23160a(Element element, Element element2) {
            for (int i9 = 0; i9 < this.f20210b; i9++) {
                if (this.f20209a.get(i9).mo23160a(element, element2)) {
                    return true;
                }
            }
            return false;
        }

        /* renamed from: e */
        public void m23161e(AbstractC5800b abstractC5800b) {
            this.f20209a.add(abstractC5800b);
            m23159d();
        }

        public String toString() {
            return C4771c.m18987i(this.f20209a, ", ");
        }

        public b(AbstractC5800b... abstractC5800bArr) {
            this(Arrays.asList(abstractC5800bArr));
        }

        public b() {
        }
    }
}
