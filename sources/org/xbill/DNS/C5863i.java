package org.xbill.DNS;

/* renamed from: org.xbill.DNS.i */
/* loaded from: classes3.dex */
public final class C5863i {

    /* renamed from: a */
    public static C5861g f20274a = new C5861g("DNS Rcode", 2);

    /* renamed from: b */
    public static C5861g f20275b = new C5861g("TSIG rcode", 2);

    static {
        f20274a.m23256g(4095);
        f20274a.m23258i("RESERVED");
        f20274a.m23257h(true);
        f20274a.m23251a(0, "NOERROR");
        f20274a.m23251a(1, "FORMERR");
        f20274a.m23251a(2, "SERVFAIL");
        f20274a.m23251a(3, "NXDOMAIN");
        f20274a.m23251a(4, "NOTIMP");
        f20274a.m23252b(4, "NOTIMPL");
        f20274a.m23251a(5, "REFUSED");
        f20274a.m23251a(6, "YXDOMAIN");
        f20274a.m23251a(7, "YXRRSET");
        f20274a.m23251a(8, "NXRRSET");
        f20274a.m23251a(9, "NOTAUTH");
        f20274a.m23251a(10, "NOTZONE");
        f20274a.m23251a(16, "BADVERS");
        f20275b.m23256g(65535);
        f20275b.m23258i("RESERVED");
        f20275b.m23257h(true);
        f20275b.m23253c(f20274a);
        f20275b.m23251a(16, "BADSIG");
        f20275b.m23251a(17, "BADKEY");
        f20275b.m23251a(18, "BADTIME");
        f20275b.m23251a(19, "BADMODE");
    }

    /* renamed from: a */
    public static String m23263a(int i9) {
        return f20275b.m23254e(i9);
    }
}
