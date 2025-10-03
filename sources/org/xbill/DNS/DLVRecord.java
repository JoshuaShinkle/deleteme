package org.xbill.DNS;

import org.apache.commons.lang3.StringUtils;
import p110j8.C5106a;

/* loaded from: classes3.dex */
public class DLVRecord extends Record {
    private static final long serialVersionUID = 1960742375677534148L;
    private int alg;
    private byte[] digest;
    private int digestid;
    private int footprint;

    @Override // org.xbill.DNS.Record
    /* renamed from: c */
    public String mo23194c() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(this.footprint);
        stringBuffer.append(StringUtils.SPACE);
        stringBuffer.append(this.alg);
        stringBuffer.append(StringUtils.SPACE);
        stringBuffer.append(this.digestid);
        if (this.digest != null) {
            stringBuffer.append(StringUtils.SPACE);
            stringBuffer.append(C5106a.m19954a(this.digest));
        }
        return stringBuffer.toString();
    }

    @Override // org.xbill.DNS.Record
    /* renamed from: d */
    public void mo23195d(C5858d c5858d, C5856b c5856b, boolean z8) {
        c5858d.m23240h(this.footprint);
        c5858d.m23243k(this.alg);
        c5858d.m23243k(this.digestid);
        byte[] bArr = this.digest;
        if (bArr != null) {
            c5858d.m23237e(bArr);
        }
    }
}
