package org.xbill.DNS;

import java.util.Date;
import org.apache.commons.lang3.StringUtils;
import p110j8.C5108c;

/* loaded from: classes3.dex */
abstract class SIGBase extends Record {
    private static final long serialVersionUID = -3738444391533812369L;
    protected int alg;
    protected int covered;
    protected Date expire;
    protected int footprint;
    protected int labels;
    protected long origttl;
    protected byte[] signature;
    protected Name signer;
    protected Date timeSigned;

    @Override // org.xbill.DNS.Record
    /* renamed from: c */
    public String mo23194c() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(C5865k.m23267b(this.covered));
        stringBuffer.append(StringUtils.SPACE);
        stringBuffer.append(this.alg);
        stringBuffer.append(StringUtils.SPACE);
        stringBuffer.append(this.labels);
        stringBuffer.append(StringUtils.SPACE);
        stringBuffer.append(this.origttl);
        stringBuffer.append(StringUtils.SPACE);
        if (C5862h.m23259a("multiline")) {
            stringBuffer.append("(\n\t");
        }
        stringBuffer.append(C5860f.m23249a(this.expire));
        stringBuffer.append(StringUtils.SPACE);
        stringBuffer.append(C5860f.m23249a(this.timeSigned));
        stringBuffer.append(StringUtils.SPACE);
        stringBuffer.append(this.footprint);
        stringBuffer.append(StringUtils.SPACE);
        stringBuffer.append(this.signer);
        if (C5862h.m23259a("multiline")) {
            stringBuffer.append("\n");
            stringBuffer.append(C5108c.m19957a(this.signature, 64, "\t", true));
        } else {
            stringBuffer.append(StringUtils.SPACE);
            stringBuffer.append(C5108c.m19958b(this.signature));
        }
        return stringBuffer.toString();
    }

    @Override // org.xbill.DNS.Record
    /* renamed from: d */
    public void mo23195d(C5858d c5858d, C5856b c5856b, boolean z8) {
        c5858d.m23240h(this.covered);
        c5858d.m23243k(this.alg);
        c5858d.m23243k(this.labels);
        c5858d.m23242j(this.origttl);
        c5858d.m23242j(this.expire.getTime() / 1000);
        c5858d.m23242j(this.timeSigned.getTime() / 1000);
        c5858d.m23240h(this.footprint);
        this.signer.m23213l(c5858d, null, z8);
        c5858d.m23237e(this.signature);
    }
}
