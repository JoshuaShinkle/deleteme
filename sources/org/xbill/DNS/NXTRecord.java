package org.xbill.DNS;

import java.util.BitSet;
import org.apache.commons.lang3.StringUtils;

/* loaded from: classes3.dex */
public class NXTRecord extends Record {
    private static final long serialVersionUID = -8851454400765507520L;
    private BitSet bitmap;
    private Name next;

    @Override // org.xbill.DNS.Record
    /* renamed from: c */
    public String mo23194c() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(this.next);
        int length = this.bitmap.length();
        for (short s8 = 0; s8 < length; s8 = (short) (s8 + 1)) {
            if (this.bitmap.get(s8)) {
                stringBuffer.append(StringUtils.SPACE);
                stringBuffer.append(C5865k.m23267b(s8));
            }
        }
        return stringBuffer.toString();
    }

    @Override // org.xbill.DNS.Record
    /* renamed from: d */
    public void mo23195d(C5858d c5858d, C5856b c5856b, boolean z8) {
        this.next.m23213l(c5858d, null, z8);
        int length = this.bitmap.length();
        int i9 = 0;
        for (int i10 = 0; i10 < length; i10++) {
            i9 |= this.bitmap.get(i10) ? 1 << (7 - (i10 % 8)) : 0;
            if (i10 % 8 == 7 || i10 == length - 1) {
                c5858d.m23243k(i9);
                i9 = 0;
            }
        }
    }
}
