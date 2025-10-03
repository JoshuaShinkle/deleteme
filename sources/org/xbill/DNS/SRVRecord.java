package org.xbill.DNS;

import org.apache.commons.lang3.StringUtils;

/* loaded from: classes3.dex */
public class SRVRecord extends Record {
    private static final long serialVersionUID = -3886460132387522052L;
    private int port;
    private int priority;
    private Name target;
    private int weight;

    @Override // org.xbill.DNS.Record
    /* renamed from: c */
    public String mo23194c() {
        StringBuffer stringBuffer = new StringBuffer();
        StringBuffer stringBuffer2 = new StringBuffer();
        stringBuffer2.append(this.priority);
        stringBuffer2.append(StringUtils.SPACE);
        stringBuffer.append(stringBuffer2.toString());
        StringBuffer stringBuffer3 = new StringBuffer();
        stringBuffer3.append(this.weight);
        stringBuffer3.append(StringUtils.SPACE);
        stringBuffer.append(stringBuffer3.toString());
        StringBuffer stringBuffer4 = new StringBuffer();
        stringBuffer4.append(this.port);
        stringBuffer4.append(StringUtils.SPACE);
        stringBuffer.append(stringBuffer4.toString());
        stringBuffer.append(this.target);
        return stringBuffer.toString();
    }

    @Override // org.xbill.DNS.Record
    /* renamed from: d */
    public void mo23195d(C5858d c5858d, C5856b c5856b, boolean z8) {
        c5858d.m23240h(this.priority);
        c5858d.m23240h(this.weight);
        c5858d.m23240h(this.port);
        this.target.m23213l(c5858d, null, z8);
    }
}
