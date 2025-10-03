package org.xbill.DNS;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

/* loaded from: classes3.dex */
public class LOCRecord extends Record {

    /* renamed from: c */
    public static NumberFormat f20246c = null;

    /* renamed from: d */
    public static NumberFormat f20247d = null;
    private static final long serialVersionUID = 9058224788126750409L;
    private long altitude;
    private long hPrecision;
    private long latitude;
    private long longitude;
    private long size;
    private long vPrecision;

    static {
        DecimalFormat decimalFormat = new DecimalFormat();
        f20246c = decimalFormat;
        decimalFormat.setMinimumIntegerDigits(2);
        DecimalFormat decimalFormat2 = new DecimalFormat();
        f20247d = decimalFormat2;
        decimalFormat2.setMinimumIntegerDigits(3);
    }

    @Override // org.xbill.DNS.Record
    /* renamed from: c */
    public String mo23194c() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(m23199i(this.latitude, 'N', 'S'));
        stringBuffer.append(StringUtils.SPACE);
        stringBuffer.append(m23199i(this.longitude, 'E', 'W'));
        stringBuffer.append(StringUtils.SPACE);
        m23200j(stringBuffer, f20246c, this.altitude - 10000000, 100L);
        stringBuffer.append("m ");
        m23200j(stringBuffer, f20246c, this.size, 100L);
        stringBuffer.append("m ");
        m23200j(stringBuffer, f20246c, this.hPrecision, 100L);
        stringBuffer.append("m ");
        m23200j(stringBuffer, f20246c, this.vPrecision, 100L);
        stringBuffer.append("m");
        return stringBuffer.toString();
    }

    @Override // org.xbill.DNS.Record
    /* renamed from: d */
    public void mo23195d(C5858d c5858d, C5856b c5856b, boolean z8) {
        c5858d.m23243k(0);
        c5858d.m23243k(m23201k(this.size));
        c5858d.m23243k(m23201k(this.hPrecision));
        c5858d.m23243k(m23201k(this.vPrecision));
        c5858d.m23242j(this.latitude);
        c5858d.m23242j(this.longitude);
        c5858d.m23242j(this.altitude);
    }

    /* renamed from: i */
    public final String m23199i(long j9, char c9, char c10) {
        StringBuffer stringBuffer = new StringBuffer();
        long j10 = j9 - 2147483648L;
        if (j10 < 0) {
            j10 = -j10;
            c9 = c10;
        }
        stringBuffer.append(j10 / DateUtils.MILLIS_PER_HOUR);
        long j11 = j10 % DateUtils.MILLIS_PER_HOUR;
        stringBuffer.append(StringUtils.SPACE);
        stringBuffer.append(j11 / 60000);
        stringBuffer.append(StringUtils.SPACE);
        m23200j(stringBuffer, f20247d, j11 % 60000, 1000L);
        stringBuffer.append(StringUtils.SPACE);
        stringBuffer.append(c9);
        return stringBuffer.toString();
    }

    /* renamed from: j */
    public final void m23200j(StringBuffer stringBuffer, NumberFormat numberFormat, long j9, long j10) {
        stringBuffer.append(j9 / j10);
        long j11 = j9 % j10;
        if (j11 != 0) {
            stringBuffer.append(".");
            stringBuffer.append(numberFormat.format(j11));
        }
    }

    /* renamed from: k */
    public final int m23201k(long j9) {
        byte b9 = 0;
        while (j9 > 9) {
            b9 = (byte) (b9 + 1);
            j9 /= 10;
        }
        return (int) ((j9 << 4) + b9);
    }
}
