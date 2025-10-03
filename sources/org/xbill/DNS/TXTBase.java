package org.xbill.DNS;

import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

/* loaded from: classes3.dex */
abstract class TXTBase extends Record {
    private static final long serialVersionUID = -4319510507246305931L;
    protected List strings;

    @Override // org.xbill.DNS.Record
    /* renamed from: c */
    public String mo23194c() {
        StringBuffer stringBuffer = new StringBuffer();
        Iterator it = this.strings.iterator();
        while (it.hasNext()) {
            stringBuffer.append(Record.m23220a((byte[]) it.next(), true));
            if (it.hasNext()) {
                stringBuffer.append(StringUtils.SPACE);
            }
        }
        return stringBuffer.toString();
    }

    @Override // org.xbill.DNS.Record
    /* renamed from: d */
    public void mo23195d(C5858d c5858d, C5856b c5856b, boolean z8) {
        Iterator it = this.strings.iterator();
        while (it.hasNext()) {
            c5858d.m23239g((byte[]) it.next());
        }
    }
}
