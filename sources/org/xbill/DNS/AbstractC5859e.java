package org.xbill.DNS;

import com.google.common.primitives.UnsignedBytes;
import java.util.Arrays;

/* renamed from: org.xbill.DNS.e */
/* loaded from: classes3.dex */
public abstract class AbstractC5859e {

    /* renamed from: a */
    public final int f20261a;

    /* renamed from: org.xbill.DNS.e$a */
    public static class a {

        /* renamed from: a */
        public static C5861g f20262a;

        static {
            C5861g c5861g = new C5861g("EDNS Option Codes", 2);
            f20262a = c5861g;
            c5861g.m23256g(65535);
            f20262a.m23258i("CODE");
            f20262a.m23257h(true);
            f20262a.m23251a(3, "NSID");
            f20262a.m23251a(8, "CLIENT_SUBNET");
        }

        /* renamed from: a */
        public static String m23248a(int i9) {
            return f20262a.m23254e(i9);
        }
    }

    /* renamed from: a */
    public byte[] m23244a() {
        C5858d c5858d = new C5858d();
        m23246c(c5858d);
        return c5858d.m23236d();
    }

    /* renamed from: b */
    public abstract String m23245b();

    /* renamed from: c */
    public abstract void m23246c(C5858d c5858d);

    /* renamed from: d */
    public void m23247d(C5858d c5858d) {
        c5858d.m23240h(this.f20261a);
        int iM23234b = c5858d.m23234b();
        c5858d.m23240h(0);
        m23246c(c5858d);
        c5858d.m23241i((c5858d.m23234b() - iM23234b) - 2, iM23234b);
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof AbstractC5859e)) {
            return false;
        }
        AbstractC5859e abstractC5859e = (AbstractC5859e) obj;
        if (this.f20261a != abstractC5859e.f20261a) {
            return false;
        }
        return Arrays.equals(m23244a(), abstractC5859e.m23244a());
    }

    public int hashCode() {
        int i9 = 0;
        for (byte b9 : m23244a()) {
            i9 += (i9 << 3) + (b9 & UnsignedBytes.MAX_VALUE);
        }
        return i9;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("{");
        stringBuffer.append(a.m23248a(this.f20261a));
        stringBuffer.append(": ");
        stringBuffer.append(m23245b());
        stringBuffer.append("}");
        return stringBuffer.toString();
    }
}
