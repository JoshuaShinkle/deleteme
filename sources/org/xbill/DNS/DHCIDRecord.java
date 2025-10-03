package org.xbill.DNS;

import p110j8.C5108c;

/* loaded from: classes3.dex */
public class DHCIDRecord extends Record {
    private static final long serialVersionUID = -8214820200808997707L;
    private byte[] data;

    @Override // org.xbill.DNS.Record
    /* renamed from: c */
    public String mo23194c() {
        return C5108c.m19958b(this.data);
    }

    @Override // org.xbill.DNS.Record
    /* renamed from: d */
    public void mo23195d(C5858d c5858d, C5856b c5856b, boolean z8) {
        c5858d.m23237e(this.data);
    }
}
