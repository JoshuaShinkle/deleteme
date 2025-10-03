package org.xbill.DNS;

import p110j8.C5106a;
import p110j8.C5107b;

/* loaded from: classes3.dex */
public class NSEC3Record extends Record {

    /* renamed from: c */
    public static final C5107b f20248c = new C5107b("0123456789ABCDEFGHIJKLMNOPQRSTUV=", false, false);
    private static final long serialVersionUID = -7123504635968932855L;
    private int flags;
    private int hashAlg;
    private int iterations;
    private byte[] next;
    private byte[] salt;
    private TypeBitmap types;

    @Override // org.xbill.DNS.Record
    /* renamed from: c */
    public String mo23194c() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(this.hashAlg);
        stringBuffer.append(' ');
        stringBuffer.append(this.flags);
        stringBuffer.append(' ');
        stringBuffer.append(this.iterations);
        stringBuffer.append(' ');
        byte[] bArr = this.salt;
        if (bArr == null) {
            stringBuffer.append('-');
        } else {
            stringBuffer.append(C5106a.m19954a(bArr));
        }
        stringBuffer.append(' ');
        stringBuffer.append(f20248c.m19956b(this.next));
        if (!this.types.m23227a()) {
            stringBuffer.append(' ');
            stringBuffer.append(this.types.toString());
        }
        return stringBuffer.toString();
    }

    @Override // org.xbill.DNS.Record
    /* renamed from: d */
    public void mo23195d(C5858d c5858d, C5856b c5856b, boolean z8) {
        c5858d.m23243k(this.hashAlg);
        c5858d.m23243k(this.flags);
        c5858d.m23240h(this.iterations);
        byte[] bArr = this.salt;
        if (bArr != null) {
            c5858d.m23243k(bArr.length);
            c5858d.m23237e(this.salt);
        } else {
            c5858d.m23243k(0);
        }
        c5858d.m23243k(this.next.length);
        c5858d.m23237e(this.next);
        this.types.m23228c(c5858d);
    }
}
