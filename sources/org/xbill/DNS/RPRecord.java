package org.xbill.DNS;

import org.apache.commons.lang3.StringUtils;

/* loaded from: classes3.dex */
public class RPRecord extends Record {
    private static final long serialVersionUID = 8124584364211337460L;
    private Name mailbox;
    private Name textDomain;

    @Override // org.xbill.DNS.Record
    /* renamed from: c */
    public String mo23194c() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(this.mailbox);
        stringBuffer.append(StringUtils.SPACE);
        stringBuffer.append(this.textDomain);
        return stringBuffer.toString();
    }

    @Override // org.xbill.DNS.Record
    /* renamed from: d */
    public void mo23195d(C5858d c5858d, C5856b c5856b, boolean z8) {
        this.mailbox.m23213l(c5858d, null, z8);
        this.textDomain.m23213l(c5858d, null, z8);
    }
}
