package org.xbill.DNS;

/* renamed from: org.xbill.DNS.c */
/* loaded from: classes3.dex */
public final class C5857c {

    /* renamed from: a */
    public static C5861g f20257a;

    /* renamed from: org.xbill.DNS.c$a */
    public static class a extends C5861g {
        public a() {
            super("DClass", 2);
            m23258i("CLASS");
        }

        @Override // org.xbill.DNS.C5861g
        /* renamed from: d */
        public void mo23232d(int i9) {
            C5857c.m23230a(i9);
        }
    }

    static {
        a aVar = new a();
        f20257a = aVar;
        aVar.m23251a(1, "IN");
        f20257a.m23251a(3, "CH");
        f20257a.m23252b(3, "CHAOS");
        f20257a.m23251a(4, "HS");
        f20257a.m23252b(4, "HESIOD");
        f20257a.m23251a(254, "NONE");
        f20257a.m23251a(255, "ANY");
    }

    /* renamed from: a */
    public static void m23230a(int i9) {
        if (i9 < 0 || i9 > 65535) {
            throw new InvalidDClassException(i9);
        }
    }

    /* renamed from: b */
    public static String m23231b(int i9) {
        return f20257a.m23254e(i9);
    }
}
