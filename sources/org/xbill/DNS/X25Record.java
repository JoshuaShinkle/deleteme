package org.xbill.DNS;

/* loaded from: classes3.dex */
public class X25Record extends Record {
    private static final long serialVersionUID = 4267576252335579764L;
    private byte[] address;

    @Override // org.xbill.DNS.Record
    /* renamed from: c */
    public String mo23194c() {
        return Record.m23220a(this.address, true);
    }

    @Override // org.xbill.DNS.Record
    /* renamed from: d */
    public void mo23195d(C5858d c5858d, C5856b c5856b, boolean z8) {
        c5858d.m23239g(this.address);
    }
}
