package org.xbill.DNS;

import org.apache.commons.lang3.StringUtils;

/* loaded from: classes3.dex */
public class PXRecord extends Record {
    private static final long serialVersionUID = 1811540008806660667L;
    private Name map822;
    private Name mapX400;
    private int preference;

    @Override // org.xbill.DNS.Record
    /* renamed from: c */
    public String mo23194c() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(this.preference);
        stringBuffer.append(StringUtils.SPACE);
        stringBuffer.append(this.map822);
        stringBuffer.append(StringUtils.SPACE);
        stringBuffer.append(this.mapX400);
        return stringBuffer.toString();
    }

    @Override // org.xbill.DNS.Record
    /* renamed from: d */
    public void mo23195d(C5858d c5858d, C5856b c5856b, boolean z8) {
        c5858d.m23240h(this.preference);
        this.map822.m23213l(c5858d, null, z8);
        this.mapX400.m23213l(c5858d, null, z8);
    }
}
