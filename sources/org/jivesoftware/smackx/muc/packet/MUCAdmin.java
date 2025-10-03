package org.jivesoftware.smackx.muc.packet;

import java.util.ArrayList;
import java.util.List;
import org.jivesoftware.smack.packet.AbstractC5586IQ;

/* loaded from: classes.dex */
public class MUCAdmin extends AbstractC5586IQ {

    /* renamed from: p */
    public List<C5657a> f19755p = new ArrayList();

    /* renamed from: org.jivesoftware.smackx.muc.packet.MUCAdmin$a */
    public static class C5657a {

        /* renamed from: a */
        public String f19756a;

        /* renamed from: b */
        public String f19757b;

        /* renamed from: c */
        public String f19758c;

        /* renamed from: d */
        public String f19759d;

        /* renamed from: e */
        public String f19760e;

        /* renamed from: f */
        public String f19761f;

        public C5657a(String str, String str2) {
            this.f19758c = str;
            this.f19761f = str2;
        }

        /* renamed from: a */
        public String m22571a() {
            return this.f19756a;
        }

        /* renamed from: b */
        public String m22572b() {
            return this.f19758c;
        }

        /* renamed from: c */
        public String m22573c() {
            return this.f19759d;
        }

        /* renamed from: d */
        public String m22574d() {
            return this.f19760e;
        }

        /* renamed from: e */
        public String m22575e() {
            return this.f19757b;
        }

        /* renamed from: f */
        public String m22576f() {
            return this.f19761f;
        }

        /* renamed from: g */
        public void m22577g(String str) {
            this.f19756a = str;
        }

        /* renamed from: h */
        public void m22578h(String str) {
            this.f19759d = str;
        }

        /* renamed from: i */
        public void m22579i(String str) {
            this.f19760e = str;
        }

        /* renamed from: j */
        public void m22580j(String str) {
            this.f19757b = str;
        }

        /* renamed from: k */
        public String m22581k() {
            StringBuilder sb = new StringBuilder();
            sb.append("<item");
            if (m22572b() != null) {
                sb.append(" affiliation=\"");
                sb.append(m22572b());
                sb.append("\"");
            }
            if (m22573c() != null) {
                sb.append(" jid=\"");
                sb.append(m22573c());
                sb.append("\"");
            }
            if (m22574d() != null) {
                sb.append(" nick=\"");
                sb.append(m22574d());
                sb.append("\"");
            }
            if (m22576f() != null) {
                sb.append(" role=\"");
                sb.append(m22576f());
                sb.append("\"");
            }
            if (m22575e() == null && m22571a() == null) {
                sb.append("/>");
            } else {
                sb.append(">");
                if (m22575e() != null) {
                    sb.append("<reason>");
                    sb.append(m22575e());
                    sb.append("</reason>");
                }
                if (m22571a() != null) {
                    sb.append("<actor jid=\"");
                    sb.append(m22571a());
                    sb.append("\"/>");
                }
                sb.append("</item>");
            }
            return sb.toString();
        }
    }

    /* renamed from: G */
    public void m22569G(C5657a c5657a) {
        synchronized (this.f19755p) {
            this.f19755p.add(c5657a);
        }
    }

    @Override // org.jivesoftware.smack.packet.AbstractC5586IQ
    /* renamed from: H, reason: merged with bridge method [inline-methods] */
    public String mo9675y() {
        StringBuilder sb = new StringBuilder();
        sb.append("<query xmlns=\"http://jabber.org/protocol/muc#admin\">");
        synchronized (this.f19755p) {
            for (int i9 = 0; i9 < this.f19755p.size(); i9++) {
                sb.append(this.f19755p.get(i9).m22581k());
            }
        }
        sb.append(m22159i());
        sb.append("</query>");
        return sb.toString();
    }
}
