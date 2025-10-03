package org.jivesoftware.smackx.disco.packet;

import com.google.android.gms.actions.SearchIntents;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import org.jivesoftware.smack.packet.AbstractC5586IQ;
import org.jivesoftware.smack.util.C5618l;

/* loaded from: classes.dex */
public class DiscoverInfo extends AbstractC5586IQ implements Cloneable {

    /* renamed from: p */
    public final List<C5636a> f19686p;

    /* renamed from: q */
    public final List<C5637b> f19687q;

    /* renamed from: r */
    public String f19688r;

    public DiscoverInfo() {
        this.f19686p = new LinkedList();
        this.f19687q = new LinkedList();
    }

    /* renamed from: G */
    public void m22496G(String str) {
        m22497H(new C5636a(str));
    }

    /* renamed from: H */
    public final void m22497H(C5636a c5636a) {
        this.f19686p.add(c5636a);
    }

    /* renamed from: I */
    public void m22498I(Collection<String> collection) {
        if (collection == null) {
            return;
        }
        Iterator<String> it = collection.iterator();
        while (it.hasNext()) {
            m22496G(it.next());
        }
    }

    /* renamed from: J */
    public void m22499J(Collection<C5637b> collection) {
        if (collection == null) {
            return;
        }
        this.f19687q.addAll(collection);
    }

    /* renamed from: K */
    public void m22500K(C5637b c5637b) {
        this.f19687q.add(c5637b);
    }

    /* renamed from: L, reason: merged with bridge method [inline-methods] */
    public DiscoverInfo clone() {
        return new DiscoverInfo(this);
    }

    /* renamed from: M */
    public String m22502M() {
        return this.f19688r;
    }

    /* renamed from: N */
    public void m22503N(String str) {
        this.f19688r = str;
    }

    @Override // org.jivesoftware.smack.packet.AbstractC5586IQ
    /* renamed from: y */
    public CharSequence mo9675y() {
        C5618l c5618l = new C5618l();
        c5618l.m22364o(SearchIntents.EXTRA_QUERY);
        c5618l.m22372w("http://jabber.org/protocol/disco#info");
        c5618l.m22367r("node", m22502M());
        c5618l.m22370u();
        Iterator<C5637b> it = this.f19687q.iterator();
        while (it.hasNext()) {
            c5618l.m22353d(it.next().m22509d());
        }
        Iterator<C5636a> it2 = this.f19686p.iterator();
        while (it2.hasNext()) {
            c5618l.m22353d(it2.next().m22505b());
        }
        c5618l.append(m22159i());
        c5618l.m22356g(SearchIntents.EXTRA_QUERY);
        return c5618l;
    }

    /* renamed from: org.jivesoftware.smackx.disco.packet.DiscoverInfo$a */
    public static class C5636a implements Cloneable {

        /* renamed from: b */
        public final String f19689b;

        public C5636a(C5636a c5636a) {
            this.f19689b = c5636a.f19689b;
        }

        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public C5636a clone() {
            return new C5636a(this);
        }

        /* renamed from: b */
        public C5618l m22505b() {
            C5618l c5618l = new C5618l();
            c5618l.m22364o("feature");
            c5618l.m22355f("var", this.f19689b);
            c5618l.m22358i();
            return c5618l;
        }

        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            if (obj == this) {
                return true;
            }
            if (obj.getClass() != getClass()) {
                return false;
            }
            return this.f19689b.equals(((C5636a) obj).f19689b);
        }

        public int hashCode() {
            return this.f19689b.hashCode() * 37;
        }

        public C5636a(String str) {
            if (str != null) {
                this.f19689b = str;
                return;
            }
            throw new IllegalArgumentException("variable cannot be null");
        }
    }

    /* renamed from: org.jivesoftware.smackx.disco.packet.DiscoverInfo$b */
    public static class C5637b implements Comparable<C5637b>, Cloneable {

        /* renamed from: b */
        public final String f19690b;

        /* renamed from: c */
        public String f19691c;

        /* renamed from: d */
        public final String f19692d;

        /* renamed from: e */
        public String f19693e;

        public C5637b(C5637b c5637b) {
            this(c5637b.f19690b, c5637b.f19691c, c5637b.f19692d);
            this.f19693e = c5637b.f19693e;
        }

        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public C5637b clone() {
            return new C5637b(this);
        }

        @Override // java.lang.Comparable
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public int compareTo(C5637b c5637b) {
            String str = c5637b.f19693e;
            if (str == null) {
                str = "";
            }
            String str2 = this.f19693e;
            if (str2 == null) {
                str2 = "";
            }
            String str3 = c5637b.f19692d;
            if (str3 == null) {
                str3 = "";
            }
            String str4 = this.f19692d;
            String str5 = str4 != null ? str4 : "";
            if (!this.f19690b.equals(c5637b.f19690b)) {
                return this.f19690b.compareTo(c5637b.f19690b);
            }
            if (!str5.equals(str3)) {
                return str5.compareTo(str3);
            }
            if (str2.equals(str)) {
                return 0;
            }
            return str2.compareTo(str);
        }

        /* renamed from: c */
        public void m22508c(String str) {
            this.f19693e = str;
        }

        /* renamed from: d */
        public C5618l m22509d() {
            C5618l c5618l = new C5618l();
            c5618l.m22364o("identity");
            c5618l.m22371v(this.f19693e);
            c5618l.m22355f("category", this.f19690b);
            c5618l.m22367r(AppMeasurementSdk.ConditionalUserProperty.NAME, this.f19691c);
            c5618l.m22367r("type", this.f19692d);
            c5618l.m22358i();
            return c5618l;
        }

        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            if (obj == this) {
                return true;
            }
            if (obj.getClass() != getClass()) {
                return false;
            }
            C5637b c5637b = (C5637b) obj;
            if (!this.f19690b.equals(c5637b.f19690b)) {
                return false;
            }
            String str = c5637b.f19693e;
            if (str == null) {
                str = "";
            }
            String str2 = this.f19693e;
            if (str2 == null) {
                str2 = "";
            }
            if (!str.equals(str2)) {
                return false;
            }
            String str3 = c5637b.f19692d;
            if (str3 == null) {
                str3 = "";
            }
            String str4 = this.f19692d;
            if (str4 == null) {
                str4 = "";
            }
            if (!str3.equals(str4)) {
                return false;
            }
            String str5 = c5637b.f19691c;
            return (this.f19691c != null ? str5 : "").equals(str5 == null ? "" : str5);
        }

        public int hashCode() {
            int iHashCode = (this.f19690b.hashCode() + 37) * 37;
            String str = this.f19693e;
            int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 37;
            String str2 = this.f19692d;
            int iHashCode3 = (iHashCode2 + (str2 == null ? 0 : str2.hashCode())) * 37;
            String str3 = this.f19691c;
            return iHashCode3 + (str3 != null ? str3.hashCode() : 0);
        }

        public C5637b(String str, String str2, String str3) {
            if (str != null && str3 != null) {
                this.f19690b = str;
                this.f19691c = str2;
                this.f19692d = str3;
                return;
            }
            throw new IllegalArgumentException("category and type cannot be null");
        }
    }

    public DiscoverInfo(DiscoverInfo discoverInfo) {
        super(discoverInfo);
        this.f19686p = new LinkedList();
        this.f19687q = new LinkedList();
        m22503N(discoverInfo.m22502M());
        Iterator<C5636a> it = discoverInfo.f19686p.iterator();
        while (it.hasNext()) {
            m22497H(it.next().clone());
        }
        Iterator<C5637b> it2 = discoverInfo.f19687q.iterator();
        while (it2.hasNext()) {
            m22500K(it2.next().clone());
        }
    }
}
