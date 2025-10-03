package org.xbill.DNS;

import java.net.InetAddress;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import p110j8.C5106a;

/* loaded from: classes3.dex */
public class APLRecord extends Record {
    private static final long serialVersionUID = -1348173791712935864L;
    private List elements;

    /* renamed from: org.xbill.DNS.APLRecord$a */
    public static class C5854a {

        /* renamed from: a */
        public final int f20241a;

        /* renamed from: b */
        public final boolean f20242b;

        /* renamed from: c */
        public final int f20243c;

        /* renamed from: d */
        public final Object f20244d;

        public boolean equals(Object obj) {
            if (obj == null || !(obj instanceof C5854a)) {
                return false;
            }
            C5854a c5854a = (C5854a) obj;
            return this.f20241a == c5854a.f20241a && this.f20242b == c5854a.f20242b && this.f20243c == c5854a.f20243c && this.f20244d.equals(c5854a.f20244d);
        }

        public int hashCode() {
            return this.f20244d.hashCode() + this.f20243c + (this.f20242b ? 1 : 0);
        }

        public String toString() {
            StringBuffer stringBuffer = new StringBuffer();
            if (this.f20242b) {
                stringBuffer.append("!");
            }
            stringBuffer.append(this.f20241a);
            stringBuffer.append(":");
            int i9 = this.f20241a;
            if (i9 == 1 || i9 == 2) {
                stringBuffer.append(((InetAddress) this.f20244d).getHostAddress());
            } else {
                stringBuffer.append(C5106a.m19954a((byte[]) this.f20244d));
            }
            stringBuffer.append("/");
            stringBuffer.append(this.f20243c);
            return stringBuffer.toString();
        }
    }

    /* renamed from: i */
    public static int m23196i(byte[] bArr) {
        for (int length = bArr.length - 1; length >= 0; length--) {
            if (bArr[length] != 0) {
                return length + 1;
            }
        }
        return 0;
    }

    @Override // org.xbill.DNS.Record
    /* renamed from: c */
    public String mo23194c() {
        StringBuffer stringBuffer = new StringBuffer();
        Iterator it = this.elements.iterator();
        while (it.hasNext()) {
            stringBuffer.append((C5854a) it.next());
            if (it.hasNext()) {
                stringBuffer.append(StringUtils.SPACE);
            }
        }
        return stringBuffer.toString();
    }

    @Override // org.xbill.DNS.Record
    /* renamed from: d */
    public void mo23195d(C5858d c5858d, C5856b c5856b, boolean z8) {
        byte[] address;
        int iM23196i;
        for (C5854a c5854a : this.elements) {
            int i9 = c5854a.f20241a;
            if (i9 == 1 || i9 == 2) {
                address = ((InetAddress) c5854a.f20244d).getAddress();
                iM23196i = m23196i(address);
            } else {
                address = (byte[]) c5854a.f20244d;
                iM23196i = address.length;
            }
            int i10 = c5854a.f20242b ? iM23196i | 128 : iM23196i;
            c5858d.m23240h(c5854a.f20241a);
            c5858d.m23243k(c5854a.f20243c);
            c5858d.m23243k(i10);
            c5858d.m23238f(address, 0, iM23196i);
        }
    }
}
