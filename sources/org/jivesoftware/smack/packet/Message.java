package org.jivesoftware.smack.packet;

import com.google.android.exoplayer2.text.ttml.TtmlNode;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import org.jivesoftware.smack.util.C5618l;

/* loaded from: classes.dex */
public class Message extends AbstractC5594b {

    /* renamed from: l */
    public Type f19236l;

    /* renamed from: m */
    public String f19237m;

    /* renamed from: n */
    public String f19238n;

    /* renamed from: o */
    public String f19239o;

    /* renamed from: p */
    public Date f19240p;

    /* renamed from: q */
    public Date f19241q;

    /* renamed from: r */
    public boolean f19242r;

    /* renamed from: s */
    public int f19243s;

    /* renamed from: t */
    public String f19244t;

    /* renamed from: u */
    public final Set<C5589c> f19245u;

    /* renamed from: v */
    public final Set<C5588b> f19246v;

    public enum Type {
        normal,
        chat,
        groupchat,
        headline,
        error;

        /* renamed from: a */
        public static Type m22107a(String str) {
            try {
                return valueOf(str);
            } catch (Exception unused) {
                return normal;
            }
        }
    }

    /* renamed from: org.jivesoftware.smack.packet.Message$b */
    public static class C5588b {

        /* renamed from: a */
        public String f19253a;

        /* renamed from: b */
        public String f19254b;

        /* renamed from: c */
        public String m22110c() {
            return this.f19254b;
        }

        /* renamed from: d */
        public String m22111d() {
            return this.f19253a;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            C5588b c5588b = (C5588b) obj;
            return this.f19254b.equals(c5588b.f19254b) && this.f19253a.equals(c5588b.f19253a);
        }

        public int hashCode() {
            return ((this.f19254b.hashCode() + 31) * 31) + this.f19253a.hashCode();
        }

        public C5588b(String str, String str2) {
            if (str == null) {
                throw new NullPointerException("Language cannot be null.");
            }
            if (str2 == null) {
                throw new NullPointerException("Message cannot be null.");
            }
            this.f19254b = str;
            this.f19253a = str2;
        }
    }

    /* renamed from: org.jivesoftware.smack.packet.Message$c */
    public static class C5589c {

        /* renamed from: a */
        public String f19255a;

        /* renamed from: b */
        public String f19256b;

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            C5589c c5589c = (C5589c) obj;
            return this.f19256b.equals(c5589c.f19256b) && this.f19255a.equals(c5589c.f19255a);
        }

        public int hashCode() {
            return ((this.f19256b.hashCode() + 31) * 31) + this.f19255a.hashCode();
        }

        public C5589c(String str, String str2) {
            if (str == null) {
                throw new NullPointerException("Language cannot be null.");
            }
            if (str2 == null) {
                throw new NullPointerException("Subject cannot be null.");
            }
            this.f19256b = str;
            this.f19255a = str2;
        }
    }

    public Message() {
        this.f19236l = Type.normal;
        this.f19237m = null;
        this.f19240p = null;
        this.f19241q = null;
        this.f19242r = false;
        this.f19243s = 1;
        this.f19244t = null;
        this.f19245u = new HashSet();
        this.f19246v = new HashSet();
    }

    /* renamed from: A */
    public String m22075A(String str) {
        C5588b c5588bM22078D = m22078D(str);
        if (c5588bM22078D == null) {
            return null;
        }
        return c5588bM22078D.f19253a;
    }

    /* renamed from: B */
    public String m22076B() {
        return this.f19238n;
    }

    /* renamed from: C */
    public int m22077C() {
        return this.f19243s;
    }

    /* renamed from: D */
    public final C5588b m22078D(String str) {
        String strM22104x = m22104x(str);
        for (C5588b c5588b : this.f19246v) {
            if (strM22104x.equals(c5588b.f19254b)) {
                return c5588b;
            }
        }
        return null;
    }

    /* renamed from: E */
    public final C5589c m22079E(String str) {
        String strM22104x = m22104x(str);
        for (C5589c c5589c : this.f19245u) {
            if (strM22104x.equals(c5589c.f19256b)) {
                return c5589c;
            }
        }
        return null;
    }

    /* renamed from: F */
    public Date m22080F() {
        return this.f19241q;
    }

    /* renamed from: G */
    public String m22081G() {
        return this.f19244t;
    }

    /* renamed from: H */
    public String m22082H() {
        return m22083I(null);
    }

    /* renamed from: I */
    public String m22083I(String str) {
        C5589c c5589cM22079E = m22079E(str);
        if (c5589cM22079E == null) {
            return null;
        }
        return c5589cM22079E.f19255a;
    }

    /* renamed from: J */
    public Collection<C5589c> m22084J() {
        return Collections.unmodifiableCollection(this.f19245u);
    }

    /* renamed from: K */
    public Date m22085K() {
        return this.f19240p;
    }

    /* renamed from: L */
    public Type m22086L() {
        return this.f19236l;
    }

    /* renamed from: M */
    public boolean m22087M() {
        return this.f19242r;
    }

    /* renamed from: N */
    public boolean m22088N(String str) {
        String strM22104x = m22104x(str);
        for (C5588b c5588b : this.f19246v) {
            if (strM22104x.equals(c5588b.f19254b)) {
                return this.f19246v.remove(c5588b);
            }
        }
        return false;
    }

    /* renamed from: O */
    public boolean m22089O(String str) {
        String strM22104x = m22104x(str);
        for (C5589c c5589c : this.f19245u) {
            if (strM22104x.equals(c5589c.f19256b)) {
                return this.f19245u.remove(c5589c);
            }
        }
        return false;
    }

    /* renamed from: P */
    public void m22090P(String str) {
        if (str == null) {
            m22088N("");
        } else {
            m22102v(null, str);
        }
    }

    /* renamed from: Q */
    public void m22091Q(String str) {
        this.f19239o = str;
    }

    /* renamed from: R */
    public void m22092R(String str) {
        this.f19238n = str;
    }

    /* renamed from: S */
    public void m22093S(int i9) {
        this.f19243s = i9;
    }

    /* renamed from: T */
    public void m22094T(boolean z8) {
        this.f19242r = z8;
    }

    /* renamed from: U */
    public void m22095U(Date date) {
        this.f19241q = date;
    }

    /* renamed from: V */
    public void m22096V(String str) {
        this.f19244t = str;
    }

    /* renamed from: W */
    public void m22097W(String str) {
        if (str == null) {
            m22089O("");
        } else {
            m22103w(null, str);
        }
    }

    /* renamed from: X */
    public void m22098X(Date date) {
        this.f19240p = date;
    }

    /* renamed from: Y */
    public void m22099Y(String str) {
        this.f19237m = str;
    }

    /* renamed from: Z */
    public void m22100Z(Type type) {
        if (type == null) {
            throw new IllegalArgumentException("Type cannot be null.");
        }
        this.f19236l = type;
    }

    @Override // org.jivesoftware.smack.packet.AbstractC5594b
    /* renamed from: a0, reason: merged with bridge method [inline-methods] */
    public C5618l mo22057u() {
        XMPPError xMPPErrorM22156e;
        C5618l c5618l = new C5618l();
        c5618l.m22364o("message");
        c5618l.m22372w(mo22056m());
        c5618l.m22371v(m22076B());
        mo14052a(c5618l);
        Type type = this.f19236l;
        if (type != Type.normal) {
            c5618l.m22354e("type", type);
        }
        Date date = this.f19240p;
        if (date != null) {
            c5618l.m22367r("ts", String.valueOf(date.getTime()));
        }
        Date date2 = this.f19241q;
        if (date2 != null) {
            c5618l.m22367r("st", String.valueOf(date2.getTime()));
        }
        String str = this.f19239o;
        if (str != null) {
            c5618l.m22367r("cv", str);
        }
        c5618l.m22367r("mv", String.valueOf(m22077C()));
        c5618l.m22370u();
        C5589c c5589cM22079E = m22079E(null);
        if (c5589cM22079E != null) {
            c5618l.m22361l("subject", c5589cM22079E.f19255a);
        }
        for (C5589c c5589c : m22084J()) {
            if (!c5589c.equals(c5589cM22079E)) {
                c5618l.m22364o("subject").m22371v(c5589c.f19256b).m22370u();
                c5618l.m22363n(c5589c.f19255a);
                c5618l.m22356g("subject");
            }
        }
        C5588b c5588bM22078D = m22078D(null);
        if (c5588bM22078D != null) {
            c5618l.m22361l(TtmlNode.TAG_BODY, c5588bM22078D.f19253a);
        }
        for (C5588b c5588b : m22105y()) {
            if (!c5588b.equals(c5588bM22078D)) {
                c5618l.m22364o(TtmlNode.TAG_BODY).m22371v(c5588b.m22110c()).m22370u();
                c5618l.m22363n(c5588b.m22111d());
                c5618l.m22356g(TtmlNode.TAG_BODY);
            }
        }
        c5618l.m22368s("thread", this.f19237m);
        if (this.f19236l == Type.error && (xMPPErrorM22156e = m22156e()) != null) {
            c5618l.append(xMPPErrorM22156e.m22144e());
        }
        c5618l.append(m22159i());
        c5618l.m22356g("message");
        return c5618l;
    }

    @Override // org.jivesoftware.smack.packet.AbstractC5594b
    public boolean equals(Object obj) {
        String str;
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            Message message = (Message) obj;
            if (super.equals(message) && this.f19246v.size() == message.f19246v.size() && this.f19246v.containsAll(message.f19246v) && ((str = this.f19238n) == null ? message.f19238n == null : str.equals(message.f19238n)) && this.f19245u.size() == message.f19245u.size() && this.f19245u.containsAll(message.f19245u)) {
                String str2 = this.f19237m;
                if (str2 == null ? message.f19237m == null : str2.equals(message.f19237m)) {
                    return this.f19236l == message.f19236l;
                }
                return false;
            }
        }
        return false;
    }

    @Override // org.jivesoftware.smack.packet.AbstractC5594b
    public int hashCode() {
        Type type = this.f19236l;
        int iHashCode = (((type != null ? type.hashCode() : 0) * 31) + this.f19245u.hashCode()) * 31;
        String str = this.f19237m;
        int iHashCode2 = (iHashCode + (str != null ? str.hashCode() : 0)) * 31;
        String str2 = this.f19238n;
        return ((iHashCode2 + (str2 != null ? str2.hashCode() : 0)) * 31) + this.f19246v.hashCode();
    }

    /* renamed from: v */
    public C5588b m22102v(String str, String str2) {
        C5588b c5588b = new C5588b(m22104x(str), str2);
        this.f19246v.add(c5588b);
        return c5588b;
    }

    /* renamed from: w */
    public C5589c m22103w(String str, String str2) {
        C5589c c5589c = new C5589c(m22104x(str), str2);
        this.f19245u.add(c5589c);
        return c5589c;
    }

    /* renamed from: x */
    public final String m22104x(String str) {
        String str2;
        if ("".equals(str)) {
            str = null;
        }
        return (str != null || (str2 = this.f19238n) == null) ? str == null ? AbstractC5594b.m22151d() : str : str2;
    }

    /* renamed from: y */
    public Collection<C5588b> m22105y() {
        return Collections.unmodifiableCollection(this.f19246v);
    }

    /* renamed from: z */
    public String m22106z() {
        return m22075A(null);
    }

    public Message(String str, Type type) {
        this.f19236l = Type.normal;
        this.f19237m = null;
        this.f19240p = null;
        this.f19241q = null;
        this.f19242r = false;
        this.f19243s = 1;
        this.f19244t = null;
        this.f19245u = new HashSet();
        this.f19246v = new HashSet();
        m22167t(str);
        if (type != null) {
            this.f19236l = type;
        }
    }
}
