package org.xbill.DNS;

import org.apache.commons.lang3.StringUtils;

/* loaded from: classes3.dex */
public class MINFORecord extends Record {
    private static final long serialVersionUID = -3962147172340353796L;
    private Name errorAddress;
    private Name responsibleAddress;

    @Override // org.xbill.DNS.Record
    /* renamed from: c */
    public String mo23194c() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(this.responsibleAddress);
        stringBuffer.append(StringUtils.SPACE);
        stringBuffer.append(this.errorAddress);
        return stringBuffer.toString();
    }

    @Override // org.xbill.DNS.Record
    /* renamed from: d */
    public void mo23195d(C5858d c5858d, C5856b c5856b, boolean z8) {
        this.responsibleAddress.m23213l(c5858d, null, z8);
        this.errorAddress.m23213l(c5858d, null, z8);
    }
}
