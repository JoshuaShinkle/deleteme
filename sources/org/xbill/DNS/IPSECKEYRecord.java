package org.xbill.DNS;

import java.net.InetAddress;
import org.apache.commons.lang3.StringUtils;
import p110j8.C5108c;

/* loaded from: classes3.dex */
public class IPSECKEYRecord extends Record {
    private static final long serialVersionUID = 3050449702765909687L;
    private int algorithmType;
    private Object gateway;
    private int gatewayType;
    private byte[] key;
    private int precedence;

    @Override // org.xbill.DNS.Record
    /* renamed from: c */
    public String mo23194c() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(this.precedence);
        stringBuffer.append(StringUtils.SPACE);
        stringBuffer.append(this.gatewayType);
        stringBuffer.append(StringUtils.SPACE);
        stringBuffer.append(this.algorithmType);
        stringBuffer.append(StringUtils.SPACE);
        int i9 = this.gatewayType;
        if (i9 == 0) {
            stringBuffer.append(".");
        } else if (i9 == 1 || i9 == 2) {
            stringBuffer.append(((InetAddress) this.gateway).getHostAddress());
        } else if (i9 == 3) {
            stringBuffer.append(this.gateway);
        }
        if (this.key != null) {
            stringBuffer.append(StringUtils.SPACE);
            stringBuffer.append(C5108c.m19958b(this.key));
        }
        return stringBuffer.toString();
    }

    @Override // org.xbill.DNS.Record
    /* renamed from: d */
    public void mo23195d(C5858d c5858d, C5856b c5856b, boolean z8) {
        c5858d.m23243k(this.precedence);
        c5858d.m23243k(this.gatewayType);
        c5858d.m23243k(this.algorithmType);
        int i9 = this.gatewayType;
        if (i9 == 1 || i9 == 2) {
            c5858d.m23237e(((InetAddress) this.gateway).getAddress());
        } else if (i9 == 3) {
            ((Name) this.gateway).m23213l(c5858d, null, z8);
        }
        byte[] bArr = this.key;
        if (bArr != null) {
            c5858d.m23237e(bArr);
        }
    }
}
