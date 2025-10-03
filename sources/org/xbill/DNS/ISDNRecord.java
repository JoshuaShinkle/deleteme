package org.xbill.DNS;

import org.apache.commons.lang3.StringUtils;

/* loaded from: classes3.dex */
public class ISDNRecord extends Record {
    private static final long serialVersionUID = -8730801385178968798L;
    private byte[] address;
    private byte[] subAddress;

    @Override // org.xbill.DNS.Record
    /* renamed from: c */
    public String mo23194c() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(Record.m23220a(this.address, true));
        if (this.subAddress != null) {
            stringBuffer.append(StringUtils.SPACE);
            stringBuffer.append(Record.m23220a(this.subAddress, true));
        }
        return stringBuffer.toString();
    }

    @Override // org.xbill.DNS.Record
    /* renamed from: d */
    public void mo23195d(C5858d c5858d, C5856b c5856b, boolean z8) {
        c5858d.m23239g(this.address);
        byte[] bArr = this.subAddress;
        if (bArr != null) {
            c5858d.m23239g(bArr);
        }
    }
}
