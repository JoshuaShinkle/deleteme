package org.jivesoftware.smackx.p160si.packet;

import java.util.Date;
import org.jivesoftware.smack.packet.AbstractC5586IQ;
import org.jivesoftware.smack.packet.InterfaceC5595c;
import org.jivesoftware.smack.util.C5616j;
import org.jivesoftware.smack.util.XmppDateTime;
import p009a8.C0055a;

/* loaded from: classes.dex */
public class StreamInitiation extends AbstractC5586IQ {

    /* renamed from: p */
    public String f19895p;

    /* renamed from: q */
    public String f19896q;

    /* renamed from: r */
    public C5670b f19897r;

    /* renamed from: s */
    public C5669a f19898s;

    /* renamed from: org.jivesoftware.smackx.si.packet.StreamInitiation$a */
    public class C5669a implements InterfaceC5595c {

        /* renamed from: a */
        public final C0055a f19899a;

        public C5669a(C0055a c0055a) {
            this.f19899a = c0055a;
        }

        @Override // org.jivesoftware.smack.packet.InterfaceC5595c
        /* renamed from: b */
        public String mo191b() {
            return "feature";
        }

        @Override // org.jivesoftware.smack.packet.InterfaceC5595c
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public String mo190a() {
            return "<feature xmlns=\"http://jabber.org/protocol/feature-neg\">" + ((CharSequence) this.f19899a.mo190a()) + "</feature>";
        }

        @Override // org.jivesoftware.smack.packet.InterfaceC5595c
        public String getNamespace() {
            return "http://jabber.org/protocol/feature-neg";
        }
    }

    /* renamed from: org.jivesoftware.smackx.si.packet.StreamInitiation$b */
    public static class C5670b implements InterfaceC5595c {

        /* renamed from: a */
        public final String f19901a;

        /* renamed from: b */
        public final long f19902b;

        /* renamed from: c */
        public String f19903c;

        /* renamed from: d */
        public Date f19904d;

        /* renamed from: e */
        public String f19905e;

        /* renamed from: f */
        public boolean f19906f;

        public C5670b(String str, long j9) {
            if (str == null) {
                throw new NullPointerException("name cannot be null");
            }
            this.f19901a = str;
            this.f19902b = j9;
        }

        @Override // org.jivesoftware.smack.packet.InterfaceC5595c
        /* renamed from: b */
        public String mo191b() {
            return "file";
        }

        /* renamed from: c */
        public Date m22722c() {
            return this.f19904d;
        }

        /* renamed from: d */
        public String m22723d() {
            return this.f19905e;
        }

        /* renamed from: e */
        public String m22724e() {
            return this.f19903c;
        }

        /* renamed from: f */
        public String m22725f() {
            return this.f19901a;
        }

        /* renamed from: g */
        public long m22726g() {
            return this.f19902b;
        }

        @Override // org.jivesoftware.smack.packet.InterfaceC5595c
        public String getNamespace() {
            return "http://jabber.org/protocol/si/profile/file-transfer";
        }

        /* renamed from: h */
        public boolean m22727h() {
            return this.f19906f;
        }

        /* renamed from: i */
        public void m22728i(Date date) {
            this.f19904d = date;
        }

        /* renamed from: j */
        public void m22729j(String str) {
            this.f19905e = str;
        }

        /* renamed from: k */
        public void m22730k(String str) {
            this.f19903c = str;
        }

        /* renamed from: l */
        public void m22731l(boolean z8) {
            this.f19906f = z8;
        }

        @Override // org.jivesoftware.smack.packet.InterfaceC5595c
        /* renamed from: m, reason: merged with bridge method [inline-methods] */
        public String mo190a() {
            StringBuilder sb = new StringBuilder();
            sb.append("<");
            sb.append(mo191b());
            sb.append(" xmlns=\"");
            sb.append(getNamespace());
            sb.append("\" ");
            if (m22725f() != null) {
                sb.append("name=\"");
                sb.append(C5616j.m22341f(m22725f()));
                sb.append("\" ");
            }
            if (m22726g() > 0) {
                sb.append("size=\"");
                sb.append(m22726g());
                sb.append("\" ");
            }
            if (m22722c() != null) {
                sb.append("date=\"");
                sb.append(XmppDateTime.m22269f(this.f19904d));
                sb.append("\" ");
            }
            if (m22724e() != null) {
                sb.append("hash=\"");
                sb.append(m22724e());
                sb.append("\" ");
            }
            String str = this.f19905e;
            if ((str == null || str.length() <= 0) && !this.f19906f) {
                sb.append("/>");
            } else {
                sb.append(">");
                if (m22723d() != null && this.f19905e.length() > 0) {
                    sb.append("<desc>");
                    sb.append(C5616j.m22341f(m22723d()));
                    sb.append("</desc>");
                }
                if (m22727h()) {
                    sb.append("<range/>");
                }
                sb.append("</");
                sb.append(mo191b());
                sb.append(">");
            }
            return sb.toString();
        }
    }

    @Override // org.jivesoftware.smack.packet.AbstractC5586IQ
    /* renamed from: G, reason: merged with bridge method [inline-methods] */
    public String mo9675y() {
        StringBuilder sb = new StringBuilder();
        if (m22066B().equals(AbstractC5586IQ.a.f19232c)) {
            sb.append("<si xmlns=\"http://jabber.org/protocol/si\" ");
            if (m22716I() != null) {
                sb.append("id=\"");
                sb.append(m22716I());
                sb.append("\" ");
            }
            if (m22715H() != null) {
                sb.append("mime-type=\"");
                sb.append(m22715H());
                sb.append("\" ");
            }
            sb.append("profile=\"http://jabber.org/protocol/si/profile/file-transfer\">");
            String strMo190a = this.f19897r.mo190a();
            if (strMo190a != null) {
                sb.append(strMo190a);
            }
        } else {
            if (!m22066B().equals(AbstractC5586IQ.a.f19233d)) {
                throw new IllegalArgumentException("IQ Type not understood");
            }
            sb.append("<si xmlns=\"http://jabber.org/protocol/si\">");
        }
        C5669a c5669a = this.f19898s;
        if (c5669a != null) {
            sb.append(c5669a.mo190a());
        }
        sb.append("</si>");
        return sb.toString();
    }

    /* renamed from: H */
    public String m22715H() {
        return this.f19896q;
    }

    /* renamed from: I */
    public String m22716I() {
        return this.f19895p;
    }

    /* renamed from: J */
    public void m22717J(C0055a c0055a) {
        this.f19898s = new C5669a(c0055a);
    }

    /* renamed from: K */
    public void m22718K(C5670b c5670b) {
        this.f19897r = c5670b;
    }

    /* renamed from: L */
    public void m22719L(String str) {
        this.f19896q = str;
    }

    /* renamed from: M */
    public void m22720M(String str) {
        this.f19895p = str;
    }
}
