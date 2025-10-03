package org.xbill.DNS;

import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

/* loaded from: classes3.dex */
public class OPTRecord extends Record {
    private static final long serialVersionUID = -6254521894809367938L;
    private List options;

    @Override // org.xbill.DNS.Record
    /* renamed from: c */
    public String mo23194c() {
        StringBuffer stringBuffer = new StringBuffer();
        List list = this.options;
        if (list != null) {
            stringBuffer.append(list);
            stringBuffer.append(StringUtils.SPACE);
        }
        stringBuffer.append(" ; payload ");
        stringBuffer.append(m23218k());
        stringBuffer.append(", xrcode ");
        stringBuffer.append(m23216i());
        stringBuffer.append(", version ");
        stringBuffer.append(m23219l());
        stringBuffer.append(", flags ");
        stringBuffer.append(m23217j());
        return stringBuffer.toString();
    }

    @Override // org.xbill.DNS.Record
    /* renamed from: d */
    public void mo23195d(C5858d c5858d, C5856b c5856b, boolean z8) {
        List list = this.options;
        if (list == null) {
            return;
        }
        Iterator it = list.iterator();
        while (it.hasNext()) {
            ((AbstractC5859e) it.next()).m23247d(c5858d);
        }
    }

    /* renamed from: i */
    public int m23216i() {
        return (int) (this.ttl >>> 24);
    }

    /* renamed from: j */
    public int m23217j() {
        return (int) (this.ttl & 65535);
    }

    /* renamed from: k */
    public int m23218k() {
        return this.dclass;
    }

    /* renamed from: l */
    public int m23219l() {
        return (int) ((this.ttl >>> 16) & 255);
    }
}
