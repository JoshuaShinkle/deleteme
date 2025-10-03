package org.xbill.DNS;

/* loaded from: classes3.dex */
public class ARecord extends Record {
    private static final long serialVersionUID = -2172609200849142323L;
    private int addr;

    /* renamed from: i */
    public static final byte[] m23197i(int i9) {
        return new byte[]{(byte) ((i9 >>> 24) & 255), (byte) ((i9 >>> 16) & 255), (byte) ((i9 >>> 8) & 255), (byte) (i9 & 255)};
    }

    @Override // org.xbill.DNS.Record
    /* renamed from: c */
    public String mo23194c() {
        return C5855a.m23229a(m23197i(this.addr));
    }

    @Override // org.xbill.DNS.Record
    /* renamed from: d */
    public void mo23195d(C5858d c5858d, C5856b c5856b, boolean z8) {
        c5858d.m23242j(this.addr & 4294967295L);
    }
}
