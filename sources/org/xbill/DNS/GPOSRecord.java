package org.xbill.DNS;

import org.apache.commons.lang3.StringUtils;

/* loaded from: classes3.dex */
public class GPOSRecord extends Record {
    private static final long serialVersionUID = -6349714958085750705L;
    private byte[] altitude;
    private byte[] latitude;
    private byte[] longitude;

    @Override // org.xbill.DNS.Record
    /* renamed from: c */
    public String mo23194c() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(Record.m23220a(this.longitude, true));
        stringBuffer.append(StringUtils.SPACE);
        stringBuffer.append(Record.m23220a(this.latitude, true));
        stringBuffer.append(StringUtils.SPACE);
        stringBuffer.append(Record.m23220a(this.altitude, true));
        return stringBuffer.toString();
    }

    @Override // org.xbill.DNS.Record
    /* renamed from: d */
    public void mo23195d(C5858d c5858d, C5856b c5856b, boolean z8) {
        c5858d.m23239g(this.longitude);
        c5858d.m23239g(this.latitude);
        c5858d.m23239g(this.altitude);
    }
}
