package org.xbill.DNS;

/* loaded from: classes3.dex */
public class NSECRecord extends Record {
    private static final long serialVersionUID = -5165065768816265385L;
    private Name next;
    private TypeBitmap types;

    @Override // org.xbill.DNS.Record
    /* renamed from: c */
    public String mo23194c() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(this.next);
        if (!this.types.m23227a()) {
            stringBuffer.append(' ');
            stringBuffer.append(this.types.toString());
        }
        return stringBuffer.toString();
    }

    @Override // org.xbill.DNS.Record
    /* renamed from: d */
    public void mo23195d(C5858d c5858d, C5856b c5856b, boolean z8) {
        this.next.m23213l(c5858d, null, false);
        this.types.m23228c(c5858d);
    }
}
