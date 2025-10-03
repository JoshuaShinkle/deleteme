package org.jivesoftware.smack.packet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;

/* loaded from: classes.dex */
public class XMPPError {

    /* renamed from: a */
    public final Type f19300a;

    /* renamed from: b */
    public final String f19301b;

    /* renamed from: c */
    public String f19302c;

    /* renamed from: d */
    public List<InterfaceC5595c> f19303d;

    public enum Type {
        WAIT,
        CANCEL,
        MODIFY,
        AUTH,
        CONTINUE
    }

    /* renamed from: org.jivesoftware.smack.packet.XMPPError$a */
    public static class C5591a {

        /* renamed from: b */
        public static final C5591a f19310b = new C5591a("internal-server-error");

        /* renamed from: c */
        public static final C5591a f19311c = new C5591a("forbidden");

        /* renamed from: d */
        public static final C5591a f19312d = new C5591a("bad-request");

        /* renamed from: e */
        public static final C5591a f19313e = new C5591a("conflict");

        /* renamed from: f */
        public static final C5591a f19314f = new C5591a("feature-not-implemented");

        /* renamed from: g */
        public static final C5591a f19315g = new C5591a("gone");

        /* renamed from: h */
        public static final C5591a f19316h = new C5591a("item-not-found");

        /* renamed from: i */
        public static final C5591a f19317i = new C5591a("jid-malformed");

        /* renamed from: j */
        public static final C5591a f19318j = new C5591a("not-acceptable");

        /* renamed from: k */
        public static final C5591a f19319k = new C5591a("not-allowed");

        /* renamed from: l */
        public static final C5591a f19320l = new C5591a("not-authorized");

        /* renamed from: m */
        public static final C5591a f19321m = new C5591a("payment-required");

        /* renamed from: n */
        public static final C5591a f19322n = new C5591a("recipient-unavailable");

        /* renamed from: o */
        public static final C5591a f19323o = new C5591a("redirect");

        /* renamed from: p */
        public static final C5591a f19324p = new C5591a("registration-required");

        /* renamed from: q */
        public static final C5591a f19325q = new C5591a("remote-server-error");

        /* renamed from: r */
        public static final C5591a f19326r = new C5591a("remote-server-not-found");

        /* renamed from: s */
        public static final C5591a f19327s = new C5591a("remote-server-timeout");

        /* renamed from: t */
        public static final C5591a f19328t = new C5591a("resource-constraint");

        /* renamed from: u */
        public static final C5591a f19329u = new C5591a("service-unavailable");

        /* renamed from: v */
        public static final C5591a f19330v = new C5591a("subscription-required");

        /* renamed from: w */
        public static final C5591a f19331w = new C5591a("undefined-condition");

        /* renamed from: x */
        public static final C5591a f19332x = new C5591a("unexpected-request");

        /* renamed from: y */
        public static final C5591a f19333y = new C5591a("request-timeout");

        /* renamed from: a */
        public final String f19334a;

        public C5591a(String str) {
            this.f19334a = str;
        }

        public boolean equals(Object obj) {
            return obj != null && toString().equals(obj.toString());
        }

        public int hashCode() {
            return this.f19334a.hashCode();
        }

        public String toString() {
            return this.f19334a;
        }
    }

    /* renamed from: org.jivesoftware.smack.packet.XMPPError$b */
    public static class C5592b {

        /* renamed from: c */
        public static Map<C5591a, C5592b> f19335c;

        /* renamed from: a */
        public final Type f19336a;

        /* renamed from: b */
        public final C5591a f19337b;

        static {
            HashMap map = new HashMap();
            f19335c = map;
            C5591a c5591a = C5591a.f19310b;
            Type type = Type.WAIT;
            map.put(c5591a, new C5592b(c5591a, type));
            Map<C5591a, C5592b> map2 = f19335c;
            C5591a c5591a2 = C5591a.f19311c;
            Type type2 = Type.AUTH;
            map2.put(c5591a2, new C5592b(c5591a2, type2));
            Map<C5591a, C5592b> map3 = f19335c;
            C5591a c5591a3 = C5591a.f19312d;
            Type type3 = Type.MODIFY;
            map3.put(c5591a3, new C5592b(c5591a3, type3));
            Map<C5591a, C5592b> map4 = f19335c;
            C5591a c5591a4 = C5591a.f19316h;
            Type type4 = Type.CANCEL;
            map4.put(c5591a4, new C5592b(c5591a4, type4));
            Map<C5591a, C5592b> map5 = f19335c;
            C5591a c5591a5 = C5591a.f19313e;
            map5.put(c5591a5, new C5592b(c5591a5, type4));
            Map<C5591a, C5592b> map6 = f19335c;
            C5591a c5591a6 = C5591a.f19314f;
            map6.put(c5591a6, new C5592b(c5591a6, type4));
            Map<C5591a, C5592b> map7 = f19335c;
            C5591a c5591a7 = C5591a.f19315g;
            map7.put(c5591a7, new C5592b(c5591a7, type3));
            Map<C5591a, C5592b> map8 = f19335c;
            C5591a c5591a8 = C5591a.f19317i;
            map8.put(c5591a8, new C5592b(c5591a8, type3));
            Map<C5591a, C5592b> map9 = f19335c;
            C5591a c5591a9 = C5591a.f19318j;
            map9.put(c5591a9, new C5592b(c5591a9, type3));
            Map<C5591a, C5592b> map10 = f19335c;
            C5591a c5591a10 = C5591a.f19319k;
            map10.put(c5591a10, new C5592b(c5591a10, type4));
            Map<C5591a, C5592b> map11 = f19335c;
            C5591a c5591a11 = C5591a.f19320l;
            map11.put(c5591a11, new C5592b(c5591a11, type2));
            Map<C5591a, C5592b> map12 = f19335c;
            C5591a c5591a12 = C5591a.f19321m;
            map12.put(c5591a12, new C5592b(c5591a12, type2));
            Map<C5591a, C5592b> map13 = f19335c;
            C5591a c5591a13 = C5591a.f19322n;
            map13.put(c5591a13, new C5592b(c5591a13, type));
            Map<C5591a, C5592b> map14 = f19335c;
            C5591a c5591a14 = C5591a.f19323o;
            map14.put(c5591a14, new C5592b(c5591a14, type3));
            Map<C5591a, C5592b> map15 = f19335c;
            C5591a c5591a15 = C5591a.f19324p;
            map15.put(c5591a15, new C5592b(c5591a15, type2));
            Map<C5591a, C5592b> map16 = f19335c;
            C5591a c5591a16 = C5591a.f19326r;
            map16.put(c5591a16, new C5592b(c5591a16, type4));
            Map<C5591a, C5592b> map17 = f19335c;
            C5591a c5591a17 = C5591a.f19327s;
            map17.put(c5591a17, new C5592b(c5591a17, type));
            Map<C5591a, C5592b> map18 = f19335c;
            C5591a c5591a18 = C5591a.f19325q;
            map18.put(c5591a18, new C5592b(c5591a18, type4));
            Map<C5591a, C5592b> map19 = f19335c;
            C5591a c5591a19 = C5591a.f19328t;
            map19.put(c5591a19, new C5592b(c5591a19, type));
            Map<C5591a, C5592b> map20 = f19335c;
            C5591a c5591a20 = C5591a.f19329u;
            map20.put(c5591a20, new C5592b(c5591a20, type4));
            Map<C5591a, C5592b> map21 = f19335c;
            C5591a c5591a21 = C5591a.f19330v;
            map21.put(c5591a21, new C5592b(c5591a21, type2));
            Map<C5591a, C5592b> map22 = f19335c;
            C5591a c5591a22 = C5591a.f19331w;
            map22.put(c5591a22, new C5592b(c5591a22, type));
            Map<C5591a, C5592b> map23 = f19335c;
            C5591a c5591a23 = C5591a.f19332x;
            map23.put(c5591a23, new C5592b(c5591a23, type));
            Map<C5591a, C5592b> map24 = f19335c;
            C5591a c5591a24 = C5591a.f19333y;
            map24.put(c5591a24, new C5592b(c5591a24, type4));
        }

        public C5592b(C5591a c5591a, Type type) {
            this.f19336a = type;
            this.f19337b = c5591a;
        }

        /* renamed from: b */
        public static C5592b m22146b(C5591a c5591a) {
            return f19335c.get(c5591a);
        }

        /* renamed from: a */
        public Type m22147a() {
            return this.f19336a;
        }
    }

    public XMPPError(C5591a c5591a) {
        this.f19303d = null;
        C5592b c5592bM22146b = C5592b.m22146b(c5591a);
        this.f19301b = c5591a.f19334a;
        if (c5592bM22146b != null) {
            this.f19300a = c5592bM22146b.m22147a();
        } else {
            this.f19300a = null;
        }
    }

    /* renamed from: a */
    public synchronized void m22140a(InterfaceC5595c interfaceC5595c) {
        if (this.f19303d == null) {
            this.f19303d = new ArrayList();
        }
        this.f19303d.add(interfaceC5595c);
    }

    /* renamed from: b */
    public String m22141b() {
        return this.f19301b;
    }

    /* renamed from: c */
    public synchronized List<InterfaceC5595c> m22142c() {
        List<InterfaceC5595c> list = this.f19303d;
        if (list == null) {
            return Collections.emptyList();
        }
        return Collections.unmodifiableList(list);
    }

    /* renamed from: d */
    public Type m22143d() {
        return this.f19300a;
    }

    /* renamed from: e */
    public CharSequence m22144e() {
        StringBuilder sb = new StringBuilder();
        sb.append("<error");
        if (this.f19300a != null) {
            sb.append(" type=\"");
            sb.append(this.f19300a.name().toLowerCase(Locale.US));
            sb.append("\"");
        }
        sb.append(">");
        if (this.f19301b != null) {
            sb.append("<");
            sb.append(this.f19301b);
            sb.append(" xmlns=\"urn:ietf:params:xml:ns:xmpp-stanzas\"/>");
        }
        if (this.f19302c != null) {
            sb.append("<text xml:lang=\"en\" xmlns=\"urn:ietf:params:xml:ns:xmpp-stanzas\">");
            sb.append(this.f19302c);
            sb.append("</text>");
        }
        Iterator<InterfaceC5595c> it = m22142c().iterator();
        while (it.hasNext()) {
            sb.append(it.next().mo190a());
        }
        sb.append("</error>");
        return sb.toString();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        String str = this.f19301b;
        if (str != null) {
            sb.append(str);
        }
        if (this.f19302c != null) {
            sb.append(StringUtils.SPACE);
            sb.append(this.f19302c);
        }
        return sb.toString();
    }

    public XMPPError(Type type, String str, String str2, List<InterfaceC5595c> list) {
        this.f19300a = type;
        this.f19301b = str;
        this.f19302c = str2;
        this.f19303d = list;
    }
}
