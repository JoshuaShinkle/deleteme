package org.xbill.DNS;

import org.apache.commons.lang3.StringUtils;

/* loaded from: classes3.dex */
abstract class U16NameBase extends Record {
    private static final long serialVersionUID = -8315884183112502995L;
    protected Name nameField;
    protected int u16Field;

    @Override // org.xbill.DNS.Record
    /* renamed from: c */
    public String mo23194c() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(this.u16Field);
        stringBuffer.append(StringUtils.SPACE);
        stringBuffer.append(this.nameField);
        return stringBuffer.toString();
    }

    @Override // org.xbill.DNS.Record
    /* renamed from: d */
    public void mo23195d(C5858d c5858d, C5856b c5856b, boolean z8) {
        c5858d.m23240h(this.u16Field);
        this.nameField.m23213l(c5858d, null, z8);
    }
}
