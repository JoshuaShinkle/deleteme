package org.xbill.DNS;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/* renamed from: org.xbill.DNS.f */
/* loaded from: classes3.dex */
public final class C5860f {

    /* renamed from: a */
    public static NumberFormat f20263a;

    /* renamed from: b */
    public static NumberFormat f20264b;

    static {
        DecimalFormat decimalFormat = new DecimalFormat();
        f20263a = decimalFormat;
        decimalFormat.setMinimumIntegerDigits(2);
        DecimalFormat decimalFormat2 = new DecimalFormat();
        f20264b = decimalFormat2;
        decimalFormat2.setMinimumIntegerDigits(4);
        f20264b.setGroupingUsed(false);
    }

    /* renamed from: a */
    public static String m23249a(Date date) {
        GregorianCalendar gregorianCalendar = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
        StringBuffer stringBuffer = new StringBuffer();
        gregorianCalendar.setTime(date);
        stringBuffer.append(f20264b.format(gregorianCalendar.get(1)));
        stringBuffer.append(f20263a.format(gregorianCalendar.get(2) + 1));
        stringBuffer.append(f20263a.format(gregorianCalendar.get(5)));
        stringBuffer.append(f20263a.format(gregorianCalendar.get(11)));
        stringBuffer.append(f20263a.format(gregorianCalendar.get(12)));
        stringBuffer.append(f20263a.format(gregorianCalendar.get(13)));
        return stringBuffer.toString();
    }
}
