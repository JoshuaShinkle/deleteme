package org.xbill.DNS;

import org.apache.commons.lang3.StringUtils;

/* loaded from: classes3.dex */
public class HINFORecord extends Record {
    private static final long serialVersionUID = -4732870630947452112L;
    private byte[] cpu;

    /* renamed from: os */
    private byte[] f20245os;

    @Override // org.xbill.DNS.Record
    /* renamed from: c */
    public String mo23194c() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(Record.m23220a(this.cpu, true));
        stringBuffer.append(StringUtils.SPACE);
        stringBuffer.append(Record.m23220a(this.f20245os, true));
        return stringBuffer.toString();
    }

    @Override // org.xbill.DNS.Record
    /* renamed from: d */
    public void mo23195d(C5858d c5858d, C5856b c5856b, boolean z8) {
        c5858d.m23239g(this.cpu);
        c5858d.m23239g(this.f20245os);
    }
}
