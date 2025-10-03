package org.xbill.DNS;

import org.apache.commons.lang3.StringUtils;
import p110j8.C5108c;

/* loaded from: classes3.dex */
public class CERTRecord extends Record {
    private static final long serialVersionUID = 4763014646517016835L;
    private int alg;
    private byte[] cert;
    private int certType;
    private int keyTag;

    @Override // org.xbill.DNS.Record
    /* renamed from: c */
    public String mo23194c() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(this.certType);
        stringBuffer.append(StringUtils.SPACE);
        stringBuffer.append(this.keyTag);
        stringBuffer.append(StringUtils.SPACE);
        stringBuffer.append(this.alg);
        if (this.cert != null) {
            if (C5862h.m23259a("multiline")) {
                stringBuffer.append(" (\n");
                stringBuffer.append(C5108c.m19957a(this.cert, 64, "\t", true));
            } else {
                stringBuffer.append(StringUtils.SPACE);
                stringBuffer.append(C5108c.m19958b(this.cert));
            }
        }
        return stringBuffer.toString();
    }

    @Override // org.xbill.DNS.Record
    /* renamed from: d */
    public void mo23195d(C5858d c5858d, C5856b c5856b, boolean z8) {
        c5858d.m23240h(this.certType);
        c5858d.m23240h(this.keyTag);
        c5858d.m23243k(this.alg);
        c5858d.m23237e(this.cert);
    }
}
