package org.xbill.DNS;

/* loaded from: classes3.dex */
abstract class SingleNameBase extends Record {
    private static final long serialVersionUID = -18595042501413L;
    protected Name singleName;

    @Override // org.xbill.DNS.Record
    /* renamed from: c */
    public String mo23194c() {
        return this.singleName.toString();
    }

    @Override // org.xbill.DNS.Record
    /* renamed from: d */
    public void mo23195d(C5858d c5858d, C5856b c5856b, boolean z8) {
        this.singleName.m23213l(c5858d, null, z8);
    }
}
