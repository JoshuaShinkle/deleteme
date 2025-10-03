package org.jsoup.nodes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import org.jsoup.SerializationException;
import org.jsoup.nodes.Document;
import p060e8.C4771c;
import p060e8.C4772d;
import p100i8.C5063b;
import p100i8.InterfaceC5064c;

/* renamed from: org.jsoup.nodes.g */
/* loaded from: classes.dex */
public abstract class AbstractC5690g implements Cloneable {

    /* renamed from: b */
    public AbstractC5690g f20011b;

    /* renamed from: c */
    public int f20012c;

    /* renamed from: org.jsoup.nodes.g$a */
    public class a implements InterfaceC5064c {

        /* renamed from: a */
        public final /* synthetic */ String f20013a;

        public a(String str) {
            this.f20013a = str;
        }

        @Override // p100i8.InterfaceC5064c
        /* renamed from: a */
        public void mo19830a(AbstractC5690g abstractC5690g, int i9) {
            abstractC5690g.mo19260n(this.f20013a);
        }

        @Override // p100i8.InterfaceC5064c
        /* renamed from: b */
        public void mo19831b(AbstractC5690g abstractC5690g, int i9) {
        }
    }

    /* renamed from: org.jsoup.nodes.g$b */
    public static class b implements InterfaceC5064c {

        /* renamed from: a */
        public Appendable f20015a;

        /* renamed from: b */
        public Document.OutputSettings f20016b;

        public b(Appendable appendable, Document.OutputSettings outputSettings) {
            this.f20015a = appendable;
            this.f20016b = outputSettings;
            outputSettings.m22839j();
        }

        @Override // p100i8.InterfaceC5064c
        /* renamed from: a */
        public void mo19830a(AbstractC5690g abstractC5690g, int i9) {
            try {
                abstractC5690g.mo22878z(this.f20015a, i9, this.f20016b);
            } catch (IOException e9) {
                throw new SerializationException(e9);
            }
        }

        @Override // p100i8.InterfaceC5064c
        /* renamed from: b */
        public void mo19831b(AbstractC5690g abstractC5690g, int i9) {
            if (abstractC5690g.mo22827v().equals("#text")) {
                return;
            }
            try {
                abstractC5690g.mo22849A(this.f20015a, i9, this.f20016b);
            } catch (IOException e9) {
                throw new SerializationException(e9);
            }
        }
    }

    /* renamed from: A */
    public abstract void mo22849A(Appendable appendable, int i9, Document.OutputSettings outputSettings);

    /* renamed from: B */
    public Document m22934B() {
        AbstractC5690g abstractC5690gM22942K = m22942K();
        if (abstractC5690gM22942K instanceof Document) {
            return (Document) abstractC5690gM22942K;
        }
        return null;
    }

    /* renamed from: C */
    public AbstractC5690g m22935C() {
        return this.f20011b;
    }

    /* renamed from: D */
    public final AbstractC5690g m22936D() {
        return this.f20011b;
    }

    /* renamed from: E */
    public final void m22937E(int i9) {
        List<AbstractC5690g> listMo19261o = mo19261o();
        while (i9 < listMo19261o.size()) {
            listMo19261o.get(i9).m22945N(i9);
            i9++;
        }
    }

    /* renamed from: F */
    public void m22938F() {
        C4772d.m19004j(this.f20011b);
        this.f20011b.mo19250G(this);
    }

    /* renamed from: G */
    public void mo19250G(AbstractC5690g abstractC5690g) {
        C4772d.m18998d(abstractC5690g.f20011b == this);
        int i9 = abstractC5690g.f20012c;
        mo19261o().remove(i9);
        m22937E(i9);
        abstractC5690g.f20011b = null;
    }

    /* renamed from: H */
    public void m22939H(AbstractC5690g abstractC5690g) {
        abstractC5690g.m22944M(this);
    }

    /* renamed from: I */
    public void m22940I(AbstractC5690g abstractC5690g, AbstractC5690g abstractC5690g2) {
        C4772d.m18998d(abstractC5690g.f20011b == this);
        C4772d.m19004j(abstractC5690g2);
        AbstractC5690g abstractC5690g3 = abstractC5690g2.f20011b;
        if (abstractC5690g3 != null) {
            abstractC5690g3.mo19250G(abstractC5690g2);
        }
        int i9 = abstractC5690g.f20012c;
        mo19261o().set(i9, abstractC5690g2);
        abstractC5690g2.f20011b = this;
        abstractC5690g2.m22945N(i9);
        abstractC5690g.f20011b = null;
    }

    /* renamed from: J */
    public void m22941J(AbstractC5690g abstractC5690g) {
        C4772d.m19004j(abstractC5690g);
        C4772d.m19004j(this.f20011b);
        this.f20011b.m22940I(this, abstractC5690g);
    }

    /* renamed from: K */
    public AbstractC5690g m22942K() {
        AbstractC5690g abstractC5690g = this;
        while (true) {
            AbstractC5690g abstractC5690g2 = abstractC5690g.f20011b;
            if (abstractC5690g2 == null) {
                return abstractC5690g;
            }
            abstractC5690g = abstractC5690g2;
        }
    }

    /* renamed from: L */
    public void m22943L(String str) {
        C4772d.m19004j(str);
        m22948Q(new a(str));
    }

    /* renamed from: M */
    public void m22944M(AbstractC5690g abstractC5690g) {
        C4772d.m19004j(abstractC5690g);
        AbstractC5690g abstractC5690g2 = this.f20011b;
        if (abstractC5690g2 != null) {
            abstractC5690g2.mo19250G(this);
        }
        this.f20011b = abstractC5690g;
    }

    /* renamed from: N */
    public void m22945N(int i9) {
        this.f20012c = i9;
    }

    /* renamed from: O */
    public int m22946O() {
        return this.f20012c;
    }

    /* renamed from: P */
    public List<AbstractC5690g> m22947P() {
        AbstractC5690g abstractC5690g = this.f20011b;
        if (abstractC5690g == null) {
            return Collections.emptyList();
        }
        List<AbstractC5690g> listMo19261o = abstractC5690g.mo19261o();
        ArrayList arrayList = new ArrayList(listMo19261o.size() - 1);
        for (AbstractC5690g abstractC5690g2 : listMo19261o) {
            if (abstractC5690g2 != this) {
                arrayList.add(abstractC5690g2);
            }
        }
        return arrayList;
    }

    /* renamed from: Q */
    public AbstractC5690g m22948Q(InterfaceC5064c interfaceC5064c) {
        C4772d.m19004j(interfaceC5064c);
        C5063b.m19832a(interfaceC5064c, this);
        return this;
    }

    /* renamed from: a */
    public String mo19254a(String str) {
        C4772d.m19002h(str);
        return !mo19262q(str) ? "" : C4771c.m18991m(mo19258g(), mo19255c(str));
    }

    /* renamed from: b */
    public void m22949b(int i9, AbstractC5690g... abstractC5690gArr) {
        C4772d.m19000f(abstractC5690gArr);
        List<AbstractC5690g> listMo19261o = mo19261o();
        for (AbstractC5690g abstractC5690g : abstractC5690gArr) {
            m22939H(abstractC5690g);
        }
        listMo19261o.addAll(i9, Arrays.asList(abstractC5690gArr));
        m22937E(i9);
    }

    /* renamed from: c */
    public String mo19255c(String str) {
        C4772d.m19004j(str);
        if (!mo19263r()) {
            return "";
        }
        String strM22917l = mo19257e().m22917l(str);
        return strM22917l.length() > 0 ? strM22917l : str.startsWith("abs:") ? mo19254a(str.substring(4)) : "";
    }

    /* renamed from: d */
    public AbstractC5690g mo19256d(String str, String str2) {
        mo19257e().m22927v(str, str2);
        return this;
    }

    /* renamed from: e */
    public abstract C5685b mo19257e();

    public boolean equals(Object obj) {
        return this == obj;
    }

    /* renamed from: g */
    public abstract String mo19258g();

    /* renamed from: h */
    public AbstractC5690g m22950h(AbstractC5690g abstractC5690g) {
        C4772d.m19004j(abstractC5690g);
        C4772d.m19004j(this.f20011b);
        this.f20011b.m22949b(this.f20012c, abstractC5690g);
        return this;
    }

    /* renamed from: i */
    public AbstractC5690g m22951i(int i9) {
        return mo19261o().get(i9);
    }

    /* renamed from: j */
    public abstract int mo19259j();

    /* renamed from: k */
    public List<AbstractC5690g> m22952k() {
        return Collections.unmodifiableList(mo19261o());
    }

    @Override // 
    /* renamed from: l */
    public AbstractC5690g mo22826l() {
        AbstractC5690g abstractC5690gMo22865m = mo22865m(null);
        LinkedList linkedList = new LinkedList();
        linkedList.add(abstractC5690gMo22865m);
        while (!linkedList.isEmpty()) {
            AbstractC5690g abstractC5690g = (AbstractC5690g) linkedList.remove();
            int iMo19259j = abstractC5690g.mo19259j();
            for (int i9 = 0; i9 < iMo19259j; i9++) {
                List<AbstractC5690g> listMo19261o = abstractC5690g.mo19261o();
                AbstractC5690g abstractC5690gMo22865m2 = listMo19261o.get(i9).mo22865m(abstractC5690g);
                listMo19261o.set(i9, abstractC5690gMo22865m2);
                linkedList.add(abstractC5690gMo22865m2);
            }
        }
        return abstractC5690gMo22865m;
    }

    /* renamed from: m */
    public AbstractC5690g mo22865m(AbstractC5690g abstractC5690g) {
        try {
            AbstractC5690g abstractC5690g2 = (AbstractC5690g) super.clone();
            abstractC5690g2.f20011b = abstractC5690g;
            abstractC5690g2.f20012c = abstractC5690g == null ? 0 : this.f20012c;
            return abstractC5690g2;
        } catch (CloneNotSupportedException e9) {
            throw new RuntimeException(e9);
        }
    }

    /* renamed from: n */
    public abstract void mo19260n(String str);

    /* renamed from: o */
    public abstract List<AbstractC5690g> mo19261o();

    /* renamed from: p */
    public Document.OutputSettings m22953p() {
        Document documentM22934B = m22934B();
        if (documentM22934B == null) {
            documentM22934B = new Document("");
        }
        return documentM22934B.m22830z0();
    }

    /* renamed from: q */
    public boolean mo19262q(String str) {
        C4772d.m19004j(str);
        if (str.startsWith("abs:")) {
            String strSubstring = str.substring(4);
            if (mo19257e().m22919n(strSubstring) && !mo19254a(strSubstring).equals("")) {
                return true;
            }
        }
        return mo19257e().m22919n(str);
    }

    /* renamed from: r */
    public abstract boolean mo19263r();

    /* renamed from: s */
    public boolean m22954s() {
        return this.f20011b != null;
    }

    /* renamed from: t */
    public void m22955t(Appendable appendable, int i9, Document.OutputSettings outputSettings) throws IOException {
        appendable.append('\n').append(C4771c.m18990l(i9 * outputSettings.m22837h()));
    }

    public String toString() {
        return mo22828x();
    }

    /* renamed from: u */
    public AbstractC5690g m22956u() {
        AbstractC5690g abstractC5690g = this.f20011b;
        if (abstractC5690g == null) {
            return null;
        }
        List<AbstractC5690g> listMo19261o = abstractC5690g.mo19261o();
        int i9 = this.f20012c + 1;
        if (listMo19261o.size() > i9) {
            return listMo19261o.get(i9);
        }
        return null;
    }

    /* renamed from: v */
    public abstract String mo22827v();

    /* renamed from: w */
    public void mo22875w() {
    }

    /* renamed from: x */
    public String mo22828x() {
        StringBuilder sb = new StringBuilder(128);
        m22957y(sb);
        return sb.toString();
    }

    /* renamed from: y */
    public void m22957y(Appendable appendable) {
        C5063b.m19832a(new b(appendable, m22953p()), this);
    }

    /* renamed from: z */
    public abstract void mo22878z(Appendable appendable, int i9, Document.OutputSettings outputSettings);
}
