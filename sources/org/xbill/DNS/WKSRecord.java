package org.xbill.DNS;

import org.apache.commons.lang3.StringUtils;

/* loaded from: classes3.dex */
public class WKSRecord extends Record {
    private static final long serialVersionUID = -9104259763909119805L;
    private byte[] address;
    private int protocol;
    private int[] services;

    @Override // org.xbill.DNS.Record
    /* renamed from: c */
    public String mo23194c() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(C5855a.m23229a(this.address));
        stringBuffer.append(StringUtils.SPACE);
        stringBuffer.append(this.protocol);
        for (int i9 = 0; i9 < this.services.length; i9++) {
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append(StringUtils.SPACE);
            stringBuffer2.append(this.services[i9]);
            stringBuffer.append(stringBuffer2.toString());
        }
        return stringBuffer.toString();
    }

    @Override // org.xbill.DNS.Record
    /* renamed from: d */
    public void mo23195d(C5858d c5858d, C5856b c5856b, boolean z8) {
        c5858d.m23237e(this.address);
        c5858d.m23243k(this.protocol);
        int[] iArr = this.services;
        byte[] bArr = new byte[(iArr[iArr.length - 1] / 8) + 1];
        int i9 = 0;
        while (true) {
            int[] iArr2 = this.services;
            if (i9 >= iArr2.length) {
                c5858d.m23237e(bArr);
                return;
            }
            int i10 = iArr2[i9];
            int i11 = i10 / 8;
            bArr[i11] = (byte) ((1 << (7 - (i10 % 8))) | bArr[i11]);
            i9++;
        }
    }
}
