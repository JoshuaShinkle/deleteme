package org.xbill.DNS;

import org.apache.commons.lang3.StringUtils;

/* loaded from: classes3.dex */
public class SOARecord extends Record {
    private static final long serialVersionUID = 1049740098229303931L;
    private Name admin;
    private long expire;
    private Name host;
    private long minimum;
    private long refresh;
    private long retry;
    private long serial;

    @Override // org.xbill.DNS.Record
    /* renamed from: c */
    public String mo23194c() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(this.host);
        stringBuffer.append(StringUtils.SPACE);
        stringBuffer.append(this.admin);
        if (C5862h.m23259a("multiline")) {
            stringBuffer.append(" (\n\t\t\t\t\t");
            stringBuffer.append(this.serial);
            stringBuffer.append("\t; serial\n\t\t\t\t\t");
            stringBuffer.append(this.refresh);
            stringBuffer.append("\t; refresh\n\t\t\t\t\t");
            stringBuffer.append(this.retry);
            stringBuffer.append("\t; retry\n\t\t\t\t\t");
            stringBuffer.append(this.expire);
            stringBuffer.append("\t; expire\n\t\t\t\t\t");
            stringBuffer.append(this.minimum);
            stringBuffer.append(" )\t; minimum");
        } else {
            stringBuffer.append(StringUtils.SPACE);
            stringBuffer.append(this.serial);
            stringBuffer.append(StringUtils.SPACE);
            stringBuffer.append(this.refresh);
            stringBuffer.append(StringUtils.SPACE);
            stringBuffer.append(this.retry);
            stringBuffer.append(StringUtils.SPACE);
            stringBuffer.append(this.expire);
            stringBuffer.append(StringUtils.SPACE);
            stringBuffer.append(this.minimum);
        }
        return stringBuffer.toString();
    }

    @Override // org.xbill.DNS.Record
    /* renamed from: d */
    public void mo23195d(C5858d c5858d, C5856b c5856b, boolean z8) {
        this.host.m23213l(c5858d, c5856b, z8);
        this.admin.m23213l(c5858d, c5856b, z8);
        c5858d.m23242j(this.serial);
        c5858d.m23242j(this.refresh);
        c5858d.m23242j(this.retry);
        c5858d.m23242j(this.expire);
        c5858d.m23242j(this.minimum);
    }
}
