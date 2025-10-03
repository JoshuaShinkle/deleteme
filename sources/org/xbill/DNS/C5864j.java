package org.xbill.DNS;

/* renamed from: org.xbill.DNS.j */
/* loaded from: classes3.dex */
public final class C5864j {
    /* renamed from: a */
    public static void m23264a(long j9) {
        if (j9 < 0 || j9 > 2147483647L) {
            throw new InvalidTTLException(j9);
        }
    }

    /* renamed from: b */
    public static String m23265b(long j9) {
        m23264a(j9);
        StringBuffer stringBuffer = new StringBuffer();
        long j10 = j9 % 60;
        long j11 = j9 / 60;
        long j12 = j11 % 60;
        long j13 = j11 / 60;
        long j14 = j13 % 24;
        long j15 = j13 / 24;
        long j16 = j15 % 7;
        long j17 = j15 / 7;
        if (j17 > 0) {
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append(j17);
            stringBuffer2.append("W");
            stringBuffer.append(stringBuffer2.toString());
        }
        if (j16 > 0) {
            StringBuffer stringBuffer3 = new StringBuffer();
            stringBuffer3.append(j16);
            stringBuffer3.append("D");
            stringBuffer.append(stringBuffer3.toString());
        }
        if (j14 > 0) {
            StringBuffer stringBuffer4 = new StringBuffer();
            stringBuffer4.append(j14);
            stringBuffer4.append("H");
            stringBuffer.append(stringBuffer4.toString());
        }
        if (j12 > 0) {
            StringBuffer stringBuffer5 = new StringBuffer();
            stringBuffer5.append(j12);
            stringBuffer5.append("M");
            stringBuffer.append(stringBuffer5.toString());
        }
        if (j10 > 0 || (j17 == 0 && j16 == 0 && j14 == 0 && j12 == 0)) {
            StringBuffer stringBuffer6 = new StringBuffer();
            stringBuffer6.append(j10);
            stringBuffer6.append("S");
            stringBuffer.append(stringBuffer6.toString());
        }
        return stringBuffer.toString();
    }
}
