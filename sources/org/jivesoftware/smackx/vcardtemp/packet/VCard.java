package org.jivesoftware.smackx.vcardtemp.packet;

import com.google.zxing.client.android.Intents;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Logger;
import org.jivesoftware.smack.packet.AbstractC5586IQ;
import org.jivesoftware.smack.util.C5616j;

/* loaded from: classes.dex */
public class VCard extends AbstractC5586IQ {

    /* renamed from: E */
    public static final Logger f19916E = Logger.getLogger(VCard.class.getName());

    /* renamed from: A */
    public String f19917A;

    /* renamed from: B */
    public String f19918B;

    /* renamed from: t */
    public String f19925t;

    /* renamed from: u */
    public String f19926u;

    /* renamed from: v */
    public String f19927v;

    /* renamed from: w */
    public String f19928w;

    /* renamed from: x */
    public String f19929x;

    /* renamed from: y */
    public String f19930y;

    /* renamed from: z */
    public String f19931z;

    /* renamed from: p */
    public Map<String, String> f19921p = new HashMap();

    /* renamed from: q */
    public Map<String, String> f19922q = new HashMap();

    /* renamed from: r */
    public Map<String, String> f19923r = new HashMap();

    /* renamed from: s */
    public Map<String, String> f19924s = new HashMap();

    /* renamed from: C */
    public Map<String, String> f19919C = new HashMap();

    /* renamed from: D */
    public Map<String, String> f19920D = new HashMap();

    /* renamed from: org.jivesoftware.smackx.vcardtemp.packet.VCard$a */
    public interface InterfaceC5674a {
        /* renamed from: a */
        void mo22776a();
    }

    /* renamed from: org.jivesoftware.smackx.vcardtemp.packet.VCard$b */
    public class C5675b {

        /* renamed from: a */
        public final StringBuilder f19932a;

        /* renamed from: org.jivesoftware.smackx.vcardtemp.packet.VCard$b$a */
        public class a implements InterfaceC5674a {
            public a() {
            }

            @Override // org.jivesoftware.smackx.vcardtemp.packet.VCard.InterfaceC5674a
            /* renamed from: a */
            public void mo22776a() {
                C5675b.this.m22792p();
            }
        }

        /* renamed from: org.jivesoftware.smackx.vcardtemp.packet.VCard$b$b */
        public class b implements InterfaceC5674a {
            public b() {
            }

            @Override // org.jivesoftware.smackx.vcardtemp.packet.VCard.InterfaceC5674a
            /* renamed from: a */
            public void mo22776a() {
                C5675b c5675b = C5675b.this;
                c5675b.m22789m("BINVAL", VCard.this.f19918B);
                C5675b c5675b2 = C5675b.this;
                c5675b2.m22789m(Intents.WifiConnect.TYPE, C5616j.m22341f(VCard.this.f19917A));
            }
        }

        /* renamed from: org.jivesoftware.smackx.vcardtemp.packet.VCard$b$c */
        public class c implements InterfaceC5674a {

            /* renamed from: a */
            public final /* synthetic */ String f19936a;

            /* renamed from: b */
            public final /* synthetic */ String f19937b;

            public c(String str, String str2) {
                this.f19936a = str;
                this.f19937b = str2;
            }

            @Override // org.jivesoftware.smackx.vcardtemp.packet.VCard.InterfaceC5674a
            /* renamed from: a */
            public void mo22776a() {
                C5675b.this.m22783g(this.f19936a);
                C5675b.this.m22783g("INTERNET");
                C5675b.this.m22783g("PREF");
                C5675b.this.m22789m("USERID", C5616j.m22341f(this.f19937b));
            }
        }

        /* renamed from: org.jivesoftware.smackx.vcardtemp.packet.VCard$b$d */
        public class d implements InterfaceC5674a {

            /* renamed from: a */
            public final /* synthetic */ Map.Entry f19939a;

            /* renamed from: b */
            public final /* synthetic */ String f19940b;

            public d(Map.Entry entry, String str) {
                this.f19939a = entry;
                this.f19940b = str;
            }

            @Override // org.jivesoftware.smackx.vcardtemp.packet.VCard.InterfaceC5674a
            /* renamed from: a */
            public void mo22776a() {
                C5675b.this.m22783g(this.f19939a.getKey());
                C5675b.this.m22783g(this.f19940b);
                C5675b.this.m22789m("NUMBER", C5616j.m22341f((String) this.f19939a.getValue()));
            }
        }

        /* renamed from: org.jivesoftware.smackx.vcardtemp.packet.VCard$b$e */
        public class e implements InterfaceC5674a {

            /* renamed from: a */
            public final /* synthetic */ String f19942a;

            /* renamed from: b */
            public final /* synthetic */ Map f19943b;

            public e(String str, Map map) {
                this.f19942a = str;
                this.f19943b = map;
            }

            @Override // org.jivesoftware.smackx.vcardtemp.packet.VCard.InterfaceC5674a
            /* renamed from: a */
            public void mo22776a() {
                C5675b.this.m22783g(this.f19942a);
                for (Map.Entry entry : this.f19943b.entrySet()) {
                    C5675b.this.m22789m((String) entry.getKey(), C5616j.m22341f((String) entry.getValue()));
                }
            }
        }

        /* renamed from: org.jivesoftware.smackx.vcardtemp.packet.VCard$b$f */
        public class f implements InterfaceC5674a {
            public f() {
            }

            @Override // org.jivesoftware.smackx.vcardtemp.packet.VCard.InterfaceC5674a
            /* renamed from: a */
            public void mo22776a() {
                C5675b c5675b = C5675b.this;
                c5675b.m22789m("ORGNAME", C5616j.m22341f(VCard.this.f19930y));
                C5675b c5675b2 = C5675b.this;
                c5675b2.m22789m("ORGUNIT", C5616j.m22341f(VCard.this.f19931z));
            }
        }

        /* renamed from: org.jivesoftware.smackx.vcardtemp.packet.VCard$b$g */
        public class g implements InterfaceC5674a {
            public g() {
            }

            @Override // org.jivesoftware.smackx.vcardtemp.packet.VCard.InterfaceC5674a
            /* renamed from: a */
            public void mo22776a() {
                C5675b c5675b = C5675b.this;
                c5675b.m22789m("FAMILY", C5616j.m22341f(VCard.this.f19926u));
                C5675b c5675b2 = C5675b.this;
                c5675b2.m22789m("GIVEN", C5616j.m22341f(VCard.this.f19925t));
                C5675b c5675b3 = C5675b.this;
                c5675b3.m22789m("MIDDLE", C5616j.m22341f(VCard.this.f19927v));
            }
        }

        /* renamed from: org.jivesoftware.smackx.vcardtemp.packet.VCard$b$h */
        public class h implements InterfaceC5674a {

            /* renamed from: a */
            public final /* synthetic */ CharSequence f19947a;

            public h(CharSequence charSequence) {
                this.f19947a = charSequence;
            }

            @Override // org.jivesoftware.smackx.vcardtemp.packet.VCard.InterfaceC5674a
            /* renamed from: a */
            public void mo22776a() {
                C5675b.this.f19932a.append(this.f19947a.toString().trim());
            }
        }

        public C5675b(StringBuilder sb) {
            this.f19932a = sb;
        }

        /* renamed from: e */
        public final void m22781e(Map<String, String> map, String str) {
            if (map.size() > 0) {
                m22791o("ADR", true, new e(str, map));
            }
        }

        /* renamed from: f */
        public final void m22782f(String str, String str2) {
            if (str != null) {
                m22791o("EMAIL", true, new c(str2, str));
            }
        }

        /* renamed from: g */
        public final void m22783g(Object obj) {
            StringBuilder sb = this.f19932a;
            sb.append('<');
            sb.append(obj);
            sb.append("/>");
        }

        /* renamed from: h */
        public final void m22784h() {
            for (Map.Entry entry : VCard.this.f19919C.entrySet()) {
                m22789m(((String) entry.getKey()).toString(), C5616j.m22341f((String) entry.getValue()));
            }
            for (Map.Entry entry2 : VCard.this.f19920D.entrySet()) {
                m22789m(((String) entry2.getKey()).toString(), (CharSequence) entry2.getValue());
            }
        }

        /* renamed from: i */
        public final void m22785i() {
            m22791o("N", true, new g());
        }

        /* renamed from: j */
        public final void m22786j() {
            if (VCard.this.m22760b0()) {
                m22791o("ORG", true, new f());
            }
        }

        /* renamed from: k */
        public final void m22787k(Map<String, String> map, String str) {
            Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
            while (it.hasNext()) {
                m22791o("TEL", true, new d(it.next(), str));
            }
        }

        /* renamed from: l */
        public final void m22788l() {
            if (VCard.this.f19918B == null) {
                return;
            }
            m22791o("PHOTO", true, new b());
        }

        /* renamed from: m */
        public final void m22789m(String str, CharSequence charSequence) {
            if (charSequence == null) {
                return;
            }
            m22791o(str, true, new h(charSequence));
        }

        /* renamed from: n */
        public final void m22790n(String str, String str2, String str3, boolean z8, InterfaceC5674a interfaceC5674a) {
            StringBuilder sb = this.f19932a;
            sb.append('<');
            sb.append(str);
            if (str2 != null) {
                StringBuilder sb2 = this.f19932a;
                sb2.append(' ');
                sb2.append(str2);
                sb2.append('=');
                sb2.append('\'');
                sb2.append(str3);
                sb2.append('\'');
            }
            if (!z8) {
                this.f19932a.append("/>\n");
                return;
            }
            this.f19932a.append('>');
            interfaceC5674a.mo22776a();
            StringBuilder sb3 = this.f19932a;
            sb3.append("</");
            sb3.append(str);
            sb3.append(">\n");
        }

        /* renamed from: o */
        public final void m22791o(String str, boolean z8, InterfaceC5674a interfaceC5674a) {
            m22790n(str, null, null, z8, interfaceC5674a);
        }

        /* renamed from: p */
        public final void m22792p() {
            if (VCard.this.m22759a0()) {
                m22785i();
            }
            m22786j();
            m22784h();
            m22788l();
            m22782f(VCard.this.f19929x, "WORK");
            m22782f(VCard.this.f19928w, "HOME");
            m22787k(VCard.this.f19922q, "WORK");
            m22787k(VCard.this.f19921p, "HOME");
            m22781e(VCard.this.f19924s, "WORK");
            m22781e(VCard.this.f19923r, "HOME");
        }

        /* renamed from: q */
        public void m22793q() {
            m22790n("vCard", "xmlns", "vcard-temp", VCard.this.m22758Z(), new a());
        }
    }

    @Override // org.jivesoftware.smack.packet.AbstractC5586IQ
    /* renamed from: Y, reason: merged with bridge method [inline-methods] */
    public String mo9675y() {
        StringBuilder sb = new StringBuilder();
        new C5675b(sb).m22793q();
        return sb.toString();
    }

    /* renamed from: Z */
    public final boolean m22758Z() {
        return m22759a0() || m22760b0() || this.f19928w != null || this.f19929x != null || this.f19919C.size() > 0 || this.f19920D.size() > 0 || this.f19923r.size() > 0 || this.f19921p.size() > 0 || this.f19924s.size() > 0 || this.f19922q.size() > 0 || this.f19918B != null;
    }

    /* renamed from: a0 */
    public final boolean m22759a0() {
        return (this.f19925t == null && this.f19926u == null && this.f19927v == null) ? false : true;
    }

    /* renamed from: b0 */
    public final boolean m22760b0() {
        return (this.f19930y == null && this.f19931z == null) ? false : true;
    }

    /* renamed from: c0 */
    public void m22761c0(String str, String str2) {
        this.f19923r.put(str, str2);
    }

    /* renamed from: d0 */
    public void m22762d0(String str, String str2) {
        this.f19924s.put(str, str2);
    }

    /* renamed from: e0 */
    public void m22763e0(String str, String str2) {
        this.f19918B = str;
        this.f19917A = str2;
    }

    @Override // org.jivesoftware.smack.packet.AbstractC5594b
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        VCard vCard = (VCard) obj;
        String str = this.f19928w;
        if (str == null ? vCard.f19928w != null : !str.equals(vCard.f19928w)) {
            return false;
        }
        String str2 = this.f19929x;
        if (str2 == null ? vCard.f19929x != null : !str2.equals(vCard.f19929x)) {
            return false;
        }
        String str3 = this.f19925t;
        if (str3 == null ? vCard.f19925t != null : !str3.equals(vCard.f19925t)) {
            return false;
        }
        if (!this.f19923r.equals(vCard.f19923r) || !this.f19921p.equals(vCard.f19921p)) {
            return false;
        }
        String str4 = this.f19926u;
        if (str4 == null ? vCard.f19926u != null : !str4.equals(vCard.f19926u)) {
            return false;
        }
        String str5 = this.f19927v;
        if (str5 == null ? vCard.f19927v != null : !str5.equals(vCard.f19927v)) {
            return false;
        }
        String str6 = this.f19930y;
        if (str6 == null ? vCard.f19930y != null : !str6.equals(vCard.f19930y)) {
            return false;
        }
        String str7 = this.f19931z;
        if (str7 == null ? vCard.f19931z != null : !str7.equals(vCard.f19931z)) {
            return false;
        }
        if (!this.f19919C.equals(vCard.f19919C) || !this.f19924s.equals(vCard.f19924s)) {
            return false;
        }
        String str8 = this.f19918B;
        if (str8 == null ? vCard.f19918B == null : str8.equals(vCard.f19918B)) {
            return this.f19922q.equals(vCard.f19922q);
        }
        return false;
    }

    /* renamed from: f0 */
    public void m22764f0(String str) {
        this.f19928w = str;
    }

    /* renamed from: g0 */
    public void m22765g0(String str) {
        this.f19929x = str;
    }

    /* renamed from: h0 */
    public void m22766h0(String str, String str2) {
        m22767i0(str, str2, false);
    }

    @Override // org.jivesoftware.smack.packet.AbstractC5594b
    public int hashCode() {
        int iHashCode = ((((((this.f19921p.hashCode() * 29) + this.f19922q.hashCode()) * 29) + this.f19923r.hashCode()) * 29) + this.f19924s.hashCode()) * 29;
        String str = this.f19925t;
        int iHashCode2 = (iHashCode + (str != null ? str.hashCode() : 0)) * 29;
        String str2 = this.f19926u;
        int iHashCode3 = (iHashCode2 + (str2 != null ? str2.hashCode() : 0)) * 29;
        String str3 = this.f19927v;
        int iHashCode4 = (iHashCode3 + (str3 != null ? str3.hashCode() : 0)) * 29;
        String str4 = this.f19928w;
        int iHashCode5 = (iHashCode4 + (str4 != null ? str4.hashCode() : 0)) * 29;
        String str5 = this.f19929x;
        int iHashCode6 = (iHashCode5 + (str5 != null ? str5.hashCode() : 0)) * 29;
        String str6 = this.f19930y;
        int iHashCode7 = (iHashCode6 + (str6 != null ? str6.hashCode() : 0)) * 29;
        String str7 = this.f19931z;
        int iHashCode8 = (((iHashCode7 + (str7 != null ? str7.hashCode() : 0)) * 29) + this.f19919C.hashCode()) * 29;
        String str8 = this.f19918B;
        return iHashCode8 + (str8 != null ? str8.hashCode() : 0);
    }

    /* renamed from: i0 */
    public void m22767i0(String str, String str2, boolean z8) {
        if (z8) {
            this.f19920D.put(str, str2);
        } else {
            this.f19919C.put(str, str2);
        }
    }

    /* renamed from: j0 */
    public void m22768j0(String str) {
        this.f19925t = str;
        m22775q0();
    }

    /* renamed from: k0 */
    public void m22769k0(String str) {
        this.f19926u = str;
        m22775q0();
    }

    /* renamed from: l0 */
    public void m22770l0(String str) {
        this.f19927v = str;
        m22775q0();
    }

    /* renamed from: m0 */
    public void m22771m0(String str) {
        this.f19930y = str;
    }

    /* renamed from: n0 */
    public void m22772n0(String str) {
        this.f19931z = str;
    }

    /* renamed from: o0 */
    public void m22773o0(String str, String str2) {
        this.f19921p.put(str, str2);
    }

    /* renamed from: p0 */
    public void m22774p0(String str, String str2) {
        this.f19922q.put(str, str2);
    }

    /* renamed from: q0 */
    public final void m22775q0() {
        StringBuilder sb = new StringBuilder();
        String str = this.f19925t;
        if (str != null) {
            sb.append(C5616j.m22341f(str));
            sb.append(' ');
        }
        String str2 = this.f19927v;
        if (str2 != null) {
            sb.append(C5616j.m22341f(str2));
            sb.append(' ');
        }
        String str3 = this.f19926u;
        if (str3 != null) {
            sb.append(C5616j.m22341f(str3));
        }
        m22766h0("FN", sb.toString());
    }

    @Override // org.jivesoftware.smack.packet.AbstractC5594b
    public String toString() {
        return mo9675y();
    }
}
