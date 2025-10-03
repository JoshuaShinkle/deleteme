package org.xbill.DNS;

import com.google.common.primitives.UnsignedBytes;
import java.util.Date;
import org.apache.commons.lang3.StringUtils;
import p110j8.C5108c;

/* loaded from: classes3.dex */
public class TSIGRecord extends Record {
    private static final long serialVersionUID = -88820909016649306L;
    private Name alg;
    private int error;
    private int fudge;
    private int originalID;
    private byte[] other;
    private byte[] signature;
    private Date timeSigned;

    @Override // org.xbill.DNS.Record
    /* renamed from: c */
    public String mo23194c() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(this.alg);
        stringBuffer.append(StringUtils.SPACE);
        if (C5862h.m23259a("multiline")) {
            stringBuffer.append("(\n\t");
        }
        stringBuffer.append(this.timeSigned.getTime() / 1000);
        stringBuffer.append(StringUtils.SPACE);
        stringBuffer.append(this.fudge);
        stringBuffer.append(StringUtils.SPACE);
        stringBuffer.append(this.signature.length);
        if (C5862h.m23259a("multiline")) {
            stringBuffer.append("\n");
            stringBuffer.append(C5108c.m19957a(this.signature, 64, "\t", false));
        } else {
            stringBuffer.append(StringUtils.SPACE);
            stringBuffer.append(C5108c.m19958b(this.signature));
        }
        stringBuffer.append(StringUtils.SPACE);
        stringBuffer.append(C5863i.m23263a(this.error));
        stringBuffer.append(StringUtils.SPACE);
        byte[] bArr = this.other;
        if (bArr == null) {
            stringBuffer.append(0);
        } else {
            stringBuffer.append(bArr.length);
            if (C5862h.m23259a("multiline")) {
                stringBuffer.append("\n\n\n\t");
            } else {
                stringBuffer.append(StringUtils.SPACE);
            }
            if (this.error == 18) {
                if (this.other.length != 6) {
                    stringBuffer.append("<invalid BADTIME other data>");
                } else {
                    stringBuffer.append("<server time: ");
                    stringBuffer.append(new Date((((r1[0] & UnsignedBytes.MAX_VALUE) << 40) + ((r1[1] & UnsignedBytes.MAX_VALUE) << 32) + ((r1[2] & UnsignedBytes.MAX_VALUE) << 24) + ((r1[3] & UnsignedBytes.MAX_VALUE) << 16) + ((r1[4] & UnsignedBytes.MAX_VALUE) << 8) + (r1[5] & UnsignedBytes.MAX_VALUE)) * 1000));
                    stringBuffer.append(">");
                }
            } else {
                stringBuffer.append("<");
                stringBuffer.append(C5108c.m19958b(this.other));
                stringBuffer.append(">");
            }
        }
        if (C5862h.m23259a("multiline")) {
            stringBuffer.append(" )");
        }
        return stringBuffer.toString();
    }

    @Override // org.xbill.DNS.Record
    /* renamed from: d */
    public void mo23195d(C5858d c5858d, C5856b c5856b, boolean z8) {
        this.alg.m23213l(c5858d, null, z8);
        long time = this.timeSigned.getTime() / 1000;
        c5858d.m23240h((int) (time >> 32));
        c5858d.m23242j(time & 4294967295L);
        c5858d.m23240h(this.fudge);
        c5858d.m23240h(this.signature.length);
        c5858d.m23237e(this.signature);
        c5858d.m23240h(this.originalID);
        c5858d.m23240h(this.error);
        byte[] bArr = this.other;
        if (bArr == null) {
            c5858d.m23240h(0);
        } else {
            c5858d.m23240h(bArr.length);
            c5858d.m23237e(this.other);
        }
    }
}
