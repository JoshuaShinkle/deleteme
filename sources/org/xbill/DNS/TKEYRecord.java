package org.xbill.DNS;

import java.util.Date;
import org.apache.commons.lang3.StringUtils;
import p110j8.C5108c;

/* loaded from: classes3.dex */
public class TKEYRecord extends Record {
    private static final long serialVersionUID = 8828458121926391756L;
    private Name alg;
    private int error;
    private byte[] key;
    private int mode;
    private byte[] other;
    private Date timeExpire;
    private Date timeInception;

    @Override // org.xbill.DNS.Record
    /* renamed from: c */
    public String mo23194c() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(this.alg);
        stringBuffer.append(StringUtils.SPACE);
        if (C5862h.m23259a("multiline")) {
            stringBuffer.append("(\n\t");
        }
        stringBuffer.append(C5860f.m23249a(this.timeInception));
        stringBuffer.append(StringUtils.SPACE);
        stringBuffer.append(C5860f.m23249a(this.timeExpire));
        stringBuffer.append(StringUtils.SPACE);
        stringBuffer.append(m23225i());
        stringBuffer.append(StringUtils.SPACE);
        stringBuffer.append(C5863i.m23263a(this.error));
        if (C5862h.m23259a("multiline")) {
            stringBuffer.append("\n");
            byte[] bArr = this.key;
            if (bArr != null) {
                stringBuffer.append(C5108c.m19957a(bArr, 64, "\t", false));
                stringBuffer.append("\n");
            }
            byte[] bArr2 = this.other;
            if (bArr2 != null) {
                stringBuffer.append(C5108c.m19957a(bArr2, 64, "\t", false));
            }
            stringBuffer.append(" )");
        } else {
            stringBuffer.append(StringUtils.SPACE);
            byte[] bArr3 = this.key;
            if (bArr3 != null) {
                stringBuffer.append(C5108c.m19958b(bArr3));
                stringBuffer.append(StringUtils.SPACE);
            }
            byte[] bArr4 = this.other;
            if (bArr4 != null) {
                stringBuffer.append(C5108c.m19958b(bArr4));
            }
        }
        return stringBuffer.toString();
    }

    @Override // org.xbill.DNS.Record
    /* renamed from: d */
    public void mo23195d(C5858d c5858d, C5856b c5856b, boolean z8) {
        this.alg.m23213l(c5858d, null, z8);
        c5858d.m23242j(this.timeInception.getTime() / 1000);
        c5858d.m23242j(this.timeExpire.getTime() / 1000);
        c5858d.m23240h(this.mode);
        c5858d.m23240h(this.error);
        byte[] bArr = this.key;
        if (bArr != null) {
            c5858d.m23240h(bArr.length);
            c5858d.m23237e(this.key);
        } else {
            c5858d.m23240h(0);
        }
        byte[] bArr2 = this.other;
        if (bArr2 == null) {
            c5858d.m23240h(0);
        } else {
            c5858d.m23240h(bArr2.length);
            c5858d.m23237e(this.other);
        }
    }

    /* renamed from: i */
    public String m23225i() {
        int i9 = this.mode;
        return i9 != 1 ? i9 != 2 ? i9 != 3 ? i9 != 4 ? i9 != 5 ? Integer.toString(i9) : "DELETE" : "RESOLVERASSIGNED" : "GSSAPI" : "DIFFIEHELLMAN" : "SERVERASSIGNED";
    }
}
