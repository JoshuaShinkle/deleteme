package org.xbill.DNS;

import org.apache.commons.lang3.StringUtils;
import p110j8.C5106a;

/* loaded from: classes3.dex */
public class SSHFPRecord extends Record {
    private static final long serialVersionUID = -8104701402654687025L;
    private int alg;
    private int digestType;
    private byte[] fingerprint;

    @Override // org.xbill.DNS.Record
    /* renamed from: c */
    public String mo23194c() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(this.alg);
        stringBuffer.append(StringUtils.SPACE);
        stringBuffer.append(this.digestType);
        stringBuffer.append(StringUtils.SPACE);
        stringBuffer.append(C5106a.m19954a(this.fingerprint));
        return stringBuffer.toString();
    }

    @Override // org.xbill.DNS.Record
    /* renamed from: d */
    public void mo23195d(C5858d c5858d, C5856b c5856b, boolean z8) {
        c5858d.m23243k(this.alg);
        c5858d.m23243k(this.digestType);
        c5858d.m23237e(this.fingerprint);
    }
}
