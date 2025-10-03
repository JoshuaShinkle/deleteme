package org.xbill.DNS;

import com.google.common.primitives.UnsignedBytes;
import java.security.PublicKey;
import org.apache.commons.lang3.StringUtils;
import p110j8.C5108c;

/* loaded from: classes3.dex */
abstract class KEYBase extends Record {
    private static final long serialVersionUID = 3469321722693285454L;
    protected int alg;
    protected int flags;
    protected byte[] key;
    protected int proto;
    protected int footprint = -1;
    protected PublicKey publicKey = null;

    @Override // org.xbill.DNS.Record
    /* renamed from: c */
    public String mo23194c() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(this.flags);
        stringBuffer.append(StringUtils.SPACE);
        stringBuffer.append(this.proto);
        stringBuffer.append(StringUtils.SPACE);
        stringBuffer.append(this.alg);
        if (this.key != null) {
            if (C5862h.m23259a("multiline")) {
                stringBuffer.append(" (\n");
                stringBuffer.append(C5108c.m19957a(this.key, 64, "\t", true));
                stringBuffer.append(" ; key_tag = ");
                stringBuffer.append(m23198i());
            } else {
                stringBuffer.append(StringUtils.SPACE);
                stringBuffer.append(C5108c.m19958b(this.key));
            }
        }
        return stringBuffer.toString();
    }

    @Override // org.xbill.DNS.Record
    /* renamed from: d */
    public void mo23195d(C5858d c5858d, C5856b c5856b, boolean z8) {
        c5858d.m23240h(this.flags);
        c5858d.m23243k(this.proto);
        c5858d.m23243k(this.alg);
        byte[] bArr = this.key;
        if (bArr != null) {
            c5858d.m23237e(bArr);
        }
    }

    /* renamed from: i */
    public int m23198i() {
        int i9;
        int i10;
        int i11 = this.footprint;
        if (i11 >= 0) {
            return i11;
        }
        C5858d c5858d = new C5858d();
        int i12 = 0;
        mo23195d(c5858d, null, false);
        byte[] bArrM23236d = c5858d.m23236d();
        if (this.alg == 1) {
            int i13 = bArrM23236d[bArrM23236d.length - 3] & UnsignedBytes.MAX_VALUE;
            i10 = bArrM23236d[bArrM23236d.length - 2] & UnsignedBytes.MAX_VALUE;
            i9 = i13 << 8;
        } else {
            i9 = 0;
            while (i12 < bArrM23236d.length - 1) {
                i9 += ((bArrM23236d[i12] & UnsignedBytes.MAX_VALUE) << 8) + (bArrM23236d[i12 + 1] & UnsignedBytes.MAX_VALUE);
                i12 += 2;
            }
            if (i12 < bArrM23236d.length) {
                i9 += (bArrM23236d[i12] & UnsignedBytes.MAX_VALUE) << 8;
            }
            i10 = (i9 >> 16) & 65535;
        }
        int i14 = (i9 + i10) & 65535;
        this.footprint = i14;
        return i14;
    }
}
