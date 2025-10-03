package org.xbill.DNS;

import java.net.InetAddress;
import org.apache.commons.lang3.StringUtils;

/* loaded from: classes3.dex */
public class A6Record extends Record {
    private static final long serialVersionUID = -8815026887337346789L;
    private Name prefix;
    private int prefixBits;
    private InetAddress suffix;

    @Override // org.xbill.DNS.Record
    /* renamed from: c */
    public String mo23194c() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(this.prefixBits);
        if (this.suffix != null) {
            stringBuffer.append(StringUtils.SPACE);
            stringBuffer.append(this.suffix.getHostAddress());
        }
        if (this.prefix != null) {
            stringBuffer.append(StringUtils.SPACE);
            stringBuffer.append(this.prefix);
        }
        return stringBuffer.toString();
    }

    @Override // org.xbill.DNS.Record
    /* renamed from: d */
    public void mo23195d(C5858d c5858d, C5856b c5856b, boolean z8) {
        c5858d.m23243k(this.prefixBits);
        InetAddress inetAddress = this.suffix;
        if (inetAddress != null) {
            int i9 = ((128 - this.prefixBits) + 7) / 8;
            c5858d.m23238f(inetAddress.getAddress(), 16 - i9, i9);
        }
        Name name = this.prefix;
        if (name != null) {
            name.m23213l(c5858d, null, z8);
        }
    }
}
