package org.xbill.DNS;

import org.apache.commons.lang3.StringUtils;
import p110j8.C5106a;

/* loaded from: classes3.dex */
public class TLSARecord extends Record {
    private static final long serialVersionUID = 356494267028580169L;
    private byte[] certificateAssociationData;
    private int certificateUsage;
    private int matchingType;
    private int selector;

    @Override // org.xbill.DNS.Record
    /* renamed from: c */
    public String mo23194c() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(this.certificateUsage);
        stringBuffer.append(StringUtils.SPACE);
        stringBuffer.append(this.selector);
        stringBuffer.append(StringUtils.SPACE);
        stringBuffer.append(this.matchingType);
        stringBuffer.append(StringUtils.SPACE);
        stringBuffer.append(C5106a.m19954a(this.certificateAssociationData));
        return stringBuffer.toString();
    }

    @Override // org.xbill.DNS.Record
    /* renamed from: d */
    public void mo23195d(C5858d c5858d, C5856b c5856b, boolean z8) {
        c5858d.m23243k(this.certificateUsage);
        c5858d.m23243k(this.selector);
        c5858d.m23243k(this.matchingType);
        c5858d.m23237e(this.certificateAssociationData);
    }
}
