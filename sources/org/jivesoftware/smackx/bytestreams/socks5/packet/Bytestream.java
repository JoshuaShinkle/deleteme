package org.jivesoftware.smackx.bytestreams.socks5.packet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.jivesoftware.smack.packet.AbstractC5586IQ;
import org.jivesoftware.smack.packet.InterfaceC5595c;

/* loaded from: classes.dex */
public class Bytestream extends AbstractC5586IQ {

    /* renamed from: p */
    public String f19597p;

    /* renamed from: q */
    public Mode f19598q = Mode.tcp;

    /* renamed from: r */
    public final List<C5625b> f19599r = new ArrayList();

    /* renamed from: s */
    public C5626c f19600s;

    /* renamed from: t */
    public C5624a f19601t;

    public enum Mode {
        tcp,
        udp;

        /* renamed from: a */
        public static Mode m22424a(String str) {
            try {
                return valueOf(str);
            } catch (Exception unused) {
                return tcp;
            }
        }
    }

    /* renamed from: org.jivesoftware.smackx.bytestreams.socks5.packet.Bytestream$a */
    public static class C5624a implements InterfaceC5595c {

        /* renamed from: c */
        public static String f19605c = "activate";

        /* renamed from: a */
        public String f19606a = "";

        /* renamed from: b */
        public final String f19607b;

        public C5624a(String str) {
            this.f19607b = str;
        }

        @Override // org.jivesoftware.smack.packet.InterfaceC5595c
        /* renamed from: b */
        public String mo191b() {
            return f19605c;
        }

        /* renamed from: c */
        public String m22425c() {
            return this.f19607b;
        }

        @Override // org.jivesoftware.smack.packet.InterfaceC5595c
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public String mo190a() {
            return "<" + mo191b() + ">" + m22425c() + "</" + mo191b() + ">";
        }

        @Override // org.jivesoftware.smack.packet.InterfaceC5595c
        public String getNamespace() {
            return this.f19606a;
        }
    }

    /* renamed from: org.jivesoftware.smackx.bytestreams.socks5.packet.Bytestream$b */
    public static class C5625b implements InterfaceC5595c {

        /* renamed from: d */
        public static String f19608d = "";

        /* renamed from: e */
        public static String f19609e = "streamhost";

        /* renamed from: a */
        public final String f19610a;

        /* renamed from: b */
        public final String f19611b;

        /* renamed from: c */
        public int f19612c = 0;

        public C5625b(String str, String str2) {
            this.f19610a = str;
            this.f19611b = str2;
        }

        @Override // org.jivesoftware.smack.packet.InterfaceC5595c
        /* renamed from: b */
        public String mo191b() {
            return f19609e;
        }

        /* renamed from: c */
        public String m22427c() {
            return this.f19611b;
        }

        /* renamed from: d */
        public String m22428d() {
            return this.f19610a;
        }

        /* renamed from: e */
        public int m22429e() {
            return this.f19612c;
        }

        /* renamed from: f */
        public void m22430f(int i9) {
            this.f19612c = i9;
        }

        @Override // org.jivesoftware.smack.packet.InterfaceC5595c
        /* renamed from: g, reason: merged with bridge method [inline-methods] */
        public String mo190a() {
            StringBuilder sb = new StringBuilder();
            sb.append("<");
            sb.append(mo191b());
            sb.append(StringUtils.SPACE);
            sb.append("jid=\"");
            sb.append(m22428d());
            sb.append("\" ");
            sb.append("host=\"");
            sb.append(m22427c());
            sb.append("\" ");
            if (m22429e() != 0) {
                sb.append("port=\"");
                sb.append(m22429e());
                sb.append("\"");
            } else {
                sb.append("zeroconf=\"_jabber.bytestreams\"");
            }
            sb.append("/>");
            return sb.toString();
        }

        @Override // org.jivesoftware.smack.packet.InterfaceC5595c
        public String getNamespace() {
            return f19608d;
        }
    }

    /* renamed from: org.jivesoftware.smackx.bytestreams.socks5.packet.Bytestream$c */
    public static class C5626c implements InterfaceC5595c {

        /* renamed from: c */
        public static String f19613c = "streamhost-used";

        /* renamed from: a */
        public String f19614a = "";

        /* renamed from: b */
        public final String f19615b;

        public C5626c(String str) {
            this.f19615b = str;
        }

        @Override // org.jivesoftware.smack.packet.InterfaceC5595c
        /* renamed from: b */
        public String mo191b() {
            return f19613c;
        }

        /* renamed from: c */
        public String m22432c() {
            return this.f19615b;
        }

        @Override // org.jivesoftware.smack.packet.InterfaceC5595c
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public String mo190a() {
            return "<" + mo191b() + StringUtils.SPACE + "jid=\"" + m22432c() + "\" />";
        }

        @Override // org.jivesoftware.smack.packet.InterfaceC5595c
        public String getNamespace() {
            return this.f19614a;
        }
    }

    /* renamed from: G */
    public C5625b m22410G(String str, String str2) {
        return m22411H(str, str2, 0);
    }

    /* renamed from: H */
    public C5625b m22411H(String str, String str2, int i9) {
        C5625b c5625b = new C5625b(str, str2);
        c5625b.m22430f(i9);
        m22412I(c5625b);
        return c5625b;
    }

    /* renamed from: I */
    public void m22412I(C5625b c5625b) {
        this.f19599r.add(c5625b);
    }

    /* renamed from: J */
    public int m22413J() {
        return this.f19599r.size();
    }

    @Override // org.jivesoftware.smack.packet.AbstractC5586IQ
    /* renamed from: K, reason: merged with bridge method [inline-methods] */
    public String mo9675y() {
        StringBuilder sb = new StringBuilder();
        sb.append("<query xmlns=\"http://jabber.org/protocol/bytestreams\"");
        if (m22066B().equals(AbstractC5586IQ.a.f19232c)) {
            if (m22416M() != null) {
                sb.append(" sid=\"");
                sb.append(m22416M());
                sb.append("\"");
            }
            if (m22415L() != null) {
                sb.append(" mode = \"");
                sb.append(m22415L());
                sb.append("\"");
            }
            sb.append(">");
            if (m22418O() == null) {
                Iterator<C5625b> it = m22417N().iterator();
                while (it.hasNext()) {
                    sb.append(it.next().mo190a());
                }
            } else {
                sb.append(m22418O().mo190a());
            }
        } else {
            if (!m22066B().equals(AbstractC5586IQ.a.f19233d)) {
                if (!m22066B().equals(AbstractC5586IQ.a.f19231b)) {
                    return null;
                }
                sb.append("/>");
                return sb.toString();
            }
            sb.append(">");
            if (m22419P() != null) {
                sb.append(m22419P().mo190a());
            } else if (m22413J() > 0) {
                Iterator<C5625b> it2 = this.f19599r.iterator();
                while (it2.hasNext()) {
                    sb.append(it2.next().mo190a());
                }
            }
        }
        sb.append("</query>");
        return sb.toString();
    }

    /* renamed from: L */
    public Mode m22415L() {
        return this.f19598q;
    }

    /* renamed from: M */
    public String m22416M() {
        return this.f19597p;
    }

    /* renamed from: N */
    public Collection<C5625b> m22417N() {
        return Collections.unmodifiableCollection(this.f19599r);
    }

    /* renamed from: O */
    public C5624a m22418O() {
        return this.f19601t;
    }

    /* renamed from: P */
    public C5626c m22419P() {
        return this.f19600s;
    }

    /* renamed from: Q */
    public void m22420Q(Mode mode) {
        this.f19598q = mode;
    }

    /* renamed from: R */
    public void m22421R(String str) {
        this.f19597p = str;
    }

    /* renamed from: S */
    public void m22422S(String str) {
        this.f19601t = new C5624a(str);
    }

    /* renamed from: T */
    public void m22423T(String str) {
        this.f19600s = new C5626c(str);
    }
}
