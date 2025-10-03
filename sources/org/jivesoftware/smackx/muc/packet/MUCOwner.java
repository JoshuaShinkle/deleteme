package org.jivesoftware.smackx.muc.packet;

import java.util.ArrayList;
import java.util.List;
import org.jivesoftware.smack.packet.AbstractC5586IQ;

/* loaded from: classes.dex */
public class MUCOwner extends AbstractC5586IQ {

    /* renamed from: p */
    public List<C5659b> f19762p = new ArrayList();

    /* renamed from: q */
    public C5658a f19763q;

    /* renamed from: org.jivesoftware.smackx.muc.packet.MUCOwner$a */
    public static class C5658a {

        /* renamed from: a */
        public String f19764a;

        /* renamed from: b */
        public String f19765b;

        /* renamed from: a */
        public String m22586a() {
            return this.f19765b;
        }

        /* renamed from: b */
        public String m22587b() {
            return this.f19764a;
        }

        /* renamed from: c */
        public void m22588c(String str) {
            this.f19765b = str;
        }

        /* renamed from: d */
        public void m22589d(String str) {
            this.f19764a = str;
        }

        /* renamed from: e */
        public String m22590e() {
            StringBuilder sb = new StringBuilder();
            sb.append("<destroy");
            if (m22586a() != null) {
                sb.append(" jid=\"");
                sb.append(m22586a());
                sb.append("\"");
            }
            if (m22587b() == null) {
                sb.append("/>");
            } else {
                sb.append(">");
                if (m22587b() != null) {
                    sb.append("<reason>");
                    sb.append(m22587b());
                    sb.append("</reason>");
                }
                sb.append("</destroy>");
            }
            return sb.toString();
        }
    }

    /* renamed from: org.jivesoftware.smackx.muc.packet.MUCOwner$b */
    public static class C5659b {

        /* renamed from: a */
        public String f19766a;

        /* renamed from: b */
        public String f19767b;

        /* renamed from: c */
        public String f19768c;

        /* renamed from: d */
        public String f19769d;

        /* renamed from: e */
        public String f19770e;

        /* renamed from: f */
        public String f19771f;

        public C5659b(String str) {
            this.f19768c = str;
        }

        /* renamed from: a */
        public String m22591a() {
            return this.f19766a;
        }

        /* renamed from: b */
        public String m22592b() {
            return this.f19768c;
        }

        /* renamed from: c */
        public String m22593c() {
            return this.f19769d;
        }

        /* renamed from: d */
        public String m22594d() {
            return this.f19770e;
        }

        /* renamed from: e */
        public String m22595e() {
            return this.f19767b;
        }

        /* renamed from: f */
        public String m22596f() {
            return this.f19771f;
        }

        /* renamed from: g */
        public void m22597g(String str) {
            this.f19766a = str;
        }

        /* renamed from: h */
        public void m22598h(String str) {
            this.f19769d = str;
        }

        /* renamed from: i */
        public void m22599i(String str) {
            this.f19770e = str;
        }

        /* renamed from: j */
        public void m22600j(String str) {
            this.f19767b = str;
        }

        /* renamed from: k */
        public void m22601k(String str) {
            this.f19771f = str;
        }

        /* renamed from: l */
        public String m22602l() {
            StringBuilder sb = new StringBuilder();
            sb.append("<item");
            if (m22592b() != null) {
                sb.append(" affiliation=\"");
                sb.append(m22592b());
                sb.append("\"");
            }
            if (m22593c() != null) {
                sb.append(" jid=\"");
                sb.append(m22593c());
                sb.append("\"");
            }
            if (m22594d() != null) {
                sb.append(" nick=\"");
                sb.append(m22594d());
                sb.append("\"");
            }
            if (m22596f() != null) {
                sb.append(" role=\"");
                sb.append(m22596f());
                sb.append("\"");
            }
            if (m22595e() == null && m22591a() == null) {
                sb.append("/>");
            } else {
                sb.append(">");
                if (m22595e() != null) {
                    sb.append("<reason>");
                    sb.append(m22595e());
                    sb.append("</reason>");
                }
                if (m22591a() != null) {
                    sb.append("<actor jid=\"");
                    sb.append(m22591a());
                    sb.append("\"/>");
                }
                sb.append("</item>");
            }
            return sb.toString();
        }
    }

    /* renamed from: G */
    public void m22582G(C5659b c5659b) {
        synchronized (this.f19762p) {
            this.f19762p.add(c5659b);
        }
    }

    @Override // org.jivesoftware.smack.packet.AbstractC5586IQ
    /* renamed from: H, reason: merged with bridge method [inline-methods] */
    public String mo9675y() {
        StringBuilder sb = new StringBuilder();
        sb.append("<query xmlns=\"http://jabber.org/protocol/muc#owner\">");
        synchronized (this.f19762p) {
            for (int i9 = 0; i9 < this.f19762p.size(); i9++) {
                sb.append(this.f19762p.get(i9).m22602l());
            }
        }
        if (m22584I() != null) {
            sb.append(m22584I().m22590e());
        }
        sb.append(m22159i());
        sb.append("</query>");
        return sb.toString();
    }

    /* renamed from: I */
    public C5658a m22584I() {
        return this.f19763q;
    }

    /* renamed from: J */
    public void m22585J(C5658a c5658a) {
        this.f19763q = c5658a;
    }
}
