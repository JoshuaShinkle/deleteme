package org.xbill.DNS;

import p110j8.C5106a;

/* loaded from: classes3.dex */
public class NSAPRecord extends Record {
    private static final long serialVersionUID = -1037209403185658593L;
    private byte[] address;

    @Override // org.xbill.DNS.Record
    /* renamed from: c */
    public String mo23194c() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("0x");
        stringBuffer.append(C5106a.m19954a(this.address));
        return stringBuffer.toString();
    }

    @Override // org.xbill.DNS.Record
    /* renamed from: d */
    public void mo23195d(C5858d c5858d, C5856b c5856b, boolean z8) {
        c5858d.m23237e(this.address);
    }
}
