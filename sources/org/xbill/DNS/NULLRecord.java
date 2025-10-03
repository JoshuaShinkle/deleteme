package org.xbill.DNS;

/* loaded from: classes3.dex */
public class NULLRecord extends Record {
    private static final long serialVersionUID = -5796493183235216538L;
    private byte[] data;

    @Override // org.xbill.DNS.Record
    /* renamed from: c */
    public String mo23194c() {
        return Record.m23221h(this.data);
    }

    @Override // org.xbill.DNS.Record
    /* renamed from: d */
    public void mo23195d(C5858d c5858d, C5856b c5856b, boolean z8) {
        c5858d.m23237e(this.data);
    }
}
