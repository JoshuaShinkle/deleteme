package org.xbill.DNS;

import java.net.InetAddress;

/* loaded from: classes3.dex */
public class AAAARecord extends Record {
    private static final long serialVersionUID = -4588601512069748050L;
    private InetAddress address;

    @Override // org.xbill.DNS.Record
    /* renamed from: c */
    public String mo23194c() {
        return this.address.getHostAddress();
    }

    @Override // org.xbill.DNS.Record
    /* renamed from: d */
    public void mo23195d(C5858d c5858d, C5856b c5856b, boolean z8) {
        c5858d.m23237e(this.address.getAddress());
    }
}
