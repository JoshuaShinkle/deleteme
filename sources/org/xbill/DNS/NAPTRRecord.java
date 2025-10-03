package org.xbill.DNS;

import org.apache.commons.lang3.StringUtils;

/* loaded from: classes3.dex */
public class NAPTRRecord extends Record {
    private static final long serialVersionUID = 5191232392044947002L;
    private byte[] flags;
    private int order;
    private int preference;
    private byte[] regexp;
    private Name replacement;
    private byte[] service;

    @Override // org.xbill.DNS.Record
    /* renamed from: c */
    public String mo23194c() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(this.order);
        stringBuffer.append(StringUtils.SPACE);
        stringBuffer.append(this.preference);
        stringBuffer.append(StringUtils.SPACE);
        stringBuffer.append(Record.m23220a(this.flags, true));
        stringBuffer.append(StringUtils.SPACE);
        stringBuffer.append(Record.m23220a(this.service, true));
        stringBuffer.append(StringUtils.SPACE);
        stringBuffer.append(Record.m23220a(this.regexp, true));
        stringBuffer.append(StringUtils.SPACE);
        stringBuffer.append(this.replacement);
        return stringBuffer.toString();
    }

    @Override // org.xbill.DNS.Record
    /* renamed from: d */
    public void mo23195d(C5858d c5858d, C5856b c5856b, boolean z8) {
        c5858d.m23240h(this.order);
        c5858d.m23240h(this.preference);
        c5858d.m23239g(this.flags);
        c5858d.m23239g(this.service);
        c5858d.m23239g(this.regexp);
        this.replacement.m23213l(c5858d, null, z8);
    }
}
