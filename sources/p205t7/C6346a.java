package p205t7;

import org.jivesoftware.smack.packet.InterfaceC5595c;

/* renamed from: t7.a */
/* loaded from: classes.dex */
public class C6346a implements InterfaceC5595c {

    /* renamed from: a */
    public c f21385a;

    /* renamed from: b */
    public a f21386b;

    /* renamed from: c */
    public d f21387c;

    /* renamed from: d */
    public String f21388d;

    /* renamed from: e */
    public e f21389e;

    /* renamed from: f */
    public b f21390f;

    /* renamed from: t7.a$a */
    public static class a {

        /* renamed from: a */
        public String f21391a;

        /* renamed from: b */
        public String f21392b;

        /* renamed from: c */
        public String f21393c;

        /* renamed from: a */
        public String m24313a() {
            return this.f21392b;
        }

        /* renamed from: b */
        public String m24314b() {
            return this.f21391a;
        }

        /* renamed from: c */
        public String m24315c() {
            return this.f21393c;
        }

        /* renamed from: d */
        public void m24316d(String str) {
            this.f21392b = str;
        }

        /* renamed from: e */
        public void m24317e(String str) {
            this.f21391a = str;
        }

        /* renamed from: f */
        public void m24318f(String str) {
            this.f21393c = str;
        }

        /* renamed from: g */
        public String m24319g() {
            StringBuilder sb = new StringBuilder();
            sb.append("<decline ");
            if (m24315c() != null) {
                sb.append(" to=\"");
                sb.append(m24315c());
                sb.append("\"");
            }
            if (m24313a() != null) {
                sb.append(" from=\"");
                sb.append(m24313a());
                sb.append("\"");
            }
            sb.append(">");
            if (m24314b() != null) {
                sb.append("<reason>");
                sb.append(m24314b());
                sb.append("</reason>");
            }
            sb.append("</decline>");
            return sb.toString();
        }
    }

    /* renamed from: t7.a$b */
    public static class b {

        /* renamed from: a */
        public String f21394a;

        /* renamed from: b */
        public String f21395b;

        /* renamed from: a */
        public String m24320a() {
            return this.f21395b;
        }

        /* renamed from: b */
        public String m24321b() {
            return this.f21394a;
        }

        /* renamed from: c */
        public void m24322c(String str) {
            this.f21395b = str;
        }

        /* renamed from: d */
        public void m24323d(String str) {
            this.f21394a = str;
        }

        /* renamed from: e */
        public String m24324e() {
            StringBuilder sb = new StringBuilder();
            sb.append("<destroy");
            if (m24320a() != null) {
                sb.append(" jid=\"");
                sb.append(m24320a());
                sb.append("\"");
            }
            if (m24321b() == null) {
                sb.append("/>");
            } else {
                sb.append(">");
                if (m24321b() != null) {
                    sb.append("<reason>");
                    sb.append(m24321b());
                    sb.append("</reason>");
                }
                sb.append("</destroy>");
            }
            return sb.toString();
        }
    }

    /* renamed from: t7.a$c */
    public static class c {

        /* renamed from: a */
        public String f21396a;

        /* renamed from: b */
        public String f21397b;

        /* renamed from: c */
        public String f21398c;

        /* renamed from: a */
        public String m24325a() {
            return this.f21397b;
        }

        /* renamed from: b */
        public String m24326b() {
            return this.f21396a;
        }

        /* renamed from: c */
        public String m24327c() {
            return this.f21398c;
        }

        /* renamed from: d */
        public void m24328d(String str) {
            this.f21397b = str;
        }

        /* renamed from: e */
        public void m24329e(String str) {
            this.f21396a = str;
        }

        /* renamed from: f */
        public void m24330f(String str) {
            this.f21398c = str;
        }

        /* renamed from: g */
        public String m24331g() {
            StringBuilder sb = new StringBuilder();
            sb.append("<invite ");
            if (m24327c() != null) {
                sb.append(" to=\"");
                sb.append(m24327c());
                sb.append("\"");
            }
            if (m24325a() != null) {
                sb.append(" from=\"");
                sb.append(m24325a());
                sb.append("\"");
            }
            sb.append(">");
            if (m24326b() != null) {
                sb.append("<reason>");
                sb.append(m24326b());
                sb.append("</reason>");
            }
            sb.append("</invite>");
            return sb.toString();
        }
    }

    /* renamed from: t7.a$d */
    public static class d {

        /* renamed from: a */
        public String f21399a;

        /* renamed from: b */
        public String f21400b;

        /* renamed from: c */
        public String f21401c;

        /* renamed from: d */
        public String f21402d;

        /* renamed from: e */
        public String f21403e;

        /* renamed from: f */
        public String f21404f;

        public d(String str, String str2) {
            this.f21401c = str;
            this.f21404f = str2;
        }

        /* renamed from: a */
        public String m24332a() {
            String str = this.f21399a;
            return str == null ? "" : str;
        }

        /* renamed from: b */
        public String m24333b() {
            return this.f21401c;
        }

        /* renamed from: c */
        public String m24334c() {
            return this.f21402d;
        }

        /* renamed from: d */
        public String m24335d() {
            return this.f21403e;
        }

        /* renamed from: e */
        public String m24336e() {
            String str = this.f21400b;
            return str == null ? "" : str;
        }

        /* renamed from: f */
        public String m24337f() {
            return this.f21404f;
        }

        /* renamed from: g */
        public void m24338g(String str) {
            this.f21399a = str;
        }

        /* renamed from: h */
        public void m24339h(String str) {
            this.f21402d = str;
        }

        /* renamed from: i */
        public void m24340i(String str) {
            this.f21403e = str;
        }

        /* renamed from: j */
        public void m24341j(String str) {
            this.f21400b = str;
        }

        /* renamed from: k */
        public String m24342k() {
            StringBuilder sb = new StringBuilder();
            sb.append("<item");
            if (m24333b() != null) {
                sb.append(" affiliation=\"");
                sb.append(m24333b());
                sb.append("\"");
            }
            if (m24334c() != null) {
                sb.append(" jid=\"");
                sb.append(m24334c());
                sb.append("\"");
            }
            if (m24335d() != null) {
                sb.append(" nick=\"");
                sb.append(m24335d());
                sb.append("\"");
            }
            if (m24337f() != null) {
                sb.append(" role=\"");
                sb.append(m24337f());
                sb.append("\"");
            }
            if (m24336e() == null && m24332a() == null) {
                sb.append("/>");
            } else {
                sb.append(">");
                if (m24336e() != null) {
                    sb.append("<reason>");
                    sb.append(m24336e());
                    sb.append("</reason>");
                }
                if (m24332a() != null) {
                    sb.append("<actor jid=\"");
                    sb.append(m24332a());
                    sb.append("\"/>");
                }
                sb.append("</item>");
            }
            return sb.toString();
        }
    }

    /* renamed from: t7.a$e */
    public static class e {

        /* renamed from: a */
        public String f21405a;

        public e(String str) {
            this.f21405a = str;
        }

        /* renamed from: a */
        public String m24343a() {
            return this.f21405a;
        }

        /* renamed from: b */
        public String m24344b() {
            return "<status code=\"" + m24343a() + "\"/>";
        }
    }

    @Override // org.jivesoftware.smack.packet.InterfaceC5595c
    /* renamed from: b */
    public String mo191b() {
        return "x";
    }

    /* renamed from: c */
    public a m24300c() {
        return this.f21386b;
    }

    /* renamed from: d */
    public b m24301d() {
        return this.f21390f;
    }

    /* renamed from: e */
    public c m24302e() {
        return this.f21385a;
    }

    /* renamed from: f */
    public d m24303f() {
        return this.f21387c;
    }

    /* renamed from: g */
    public String m24304g() {
        return this.f21388d;
    }

    @Override // org.jivesoftware.smack.packet.InterfaceC5595c
    public String getNamespace() {
        return "http://jabber.org/protocol/muc#user";
    }

    /* renamed from: h */
    public e m24305h() {
        return this.f21389e;
    }

    /* renamed from: i */
    public void m24306i(a aVar) {
        this.f21386b = aVar;
    }

    /* renamed from: j */
    public void m24307j(b bVar) {
        this.f21390f = bVar;
    }

    /* renamed from: k */
    public void m24308k(c cVar) {
        this.f21385a = cVar;
    }

    /* renamed from: l */
    public void m24309l(d dVar) {
        this.f21387c = dVar;
    }

    /* renamed from: m */
    public void m24310m(String str) {
        this.f21388d = str;
    }

    /* renamed from: n */
    public void m24311n(e eVar) {
        this.f21389e = eVar;
    }

    @Override // org.jivesoftware.smack.packet.InterfaceC5595c
    /* renamed from: o, reason: merged with bridge method [inline-methods] */
    public String mo190a() {
        StringBuilder sb = new StringBuilder();
        sb.append("<");
        sb.append(mo191b());
        sb.append(" xmlns=\"");
        sb.append(getNamespace());
        sb.append("\">");
        if (m24302e() != null) {
            sb.append(m24302e().m24331g());
        }
        if (m24300c() != null) {
            sb.append(m24300c().m24319g());
        }
        if (m24303f() != null) {
            sb.append(m24303f().m24342k());
        }
        if (m24304g() != null) {
            sb.append("<password>");
            sb.append(m24304g());
            sb.append("</password>");
        }
        if (m24305h() != null) {
            sb.append(m24305h().m24344b());
        }
        if (m24301d() != null) {
            sb.append(m24301d().m24324e());
        }
        sb.append("</");
        sb.append(mo191b());
        sb.append(">");
        return sb.toString();
    }
}
