package org.xbill.DNS;

import com.google.common.primitives.UnsignedBytes;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.Arrays;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.p159io.IOUtils;
import p110j8.C5106a;

/* loaded from: classes3.dex */
public abstract class Record implements Cloneable, Comparable, Serializable {

    /* renamed from: b */
    public static final DecimalFormat f20256b;
    private static final long serialVersionUID = 2694906050116005466L;
    protected int dclass;
    protected Name name;
    protected long ttl;
    protected int type;

    static {
        DecimalFormat decimalFormat = new DecimalFormat();
        f20256b = decimalFormat;
        decimalFormat.setMinimumIntegerDigits(3);
    }

    /* renamed from: a */
    public static String m23220a(byte[] bArr, boolean z8) {
        StringBuffer stringBuffer = new StringBuffer();
        if (z8) {
            stringBuffer.append('\"');
        }
        for (byte b9 : bArr) {
            int i9 = b9 & UnsignedBytes.MAX_VALUE;
            if (i9 < 32 || i9 >= 127) {
                stringBuffer.append(IOUtils.DIR_SEPARATOR_WINDOWS);
                stringBuffer.append(f20256b.format(i9));
            } else if (i9 == 34 || i9 == 92) {
                stringBuffer.append(IOUtils.DIR_SEPARATOR_WINDOWS);
                stringBuffer.append((char) i9);
            } else {
                stringBuffer.append((char) i9);
            }
        }
        if (z8) {
            stringBuffer.append('\"');
        }
        return stringBuffer.toString();
    }

    /* renamed from: h */
    public static String m23221h(byte[] bArr) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("\\# ");
        stringBuffer.append(bArr.length);
        stringBuffer.append(StringUtils.SPACE);
        stringBuffer.append(C5106a.m19954a(bArr));
        return stringBuffer.toString();
    }

    /* renamed from: b */
    public byte[] m23222b() {
        C5858d c5858d = new C5858d();
        mo23195d(c5858d, null, true);
        return c5858d.m23236d();
    }

    /* renamed from: c */
    public abstract String mo23194c();

    @Override // java.lang.Comparable
    public int compareTo(Object obj) {
        Record record = (Record) obj;
        if (this == record) {
            return 0;
        }
        int iCompareTo = this.name.compareTo(record.name);
        if (iCompareTo != 0) {
            return iCompareTo;
        }
        int i9 = this.dclass - record.dclass;
        if (i9 != 0) {
            return i9;
        }
        int i10 = this.type - record.type;
        if (i10 != 0) {
            return i10;
        }
        byte[] bArrM23222b = m23222b();
        byte[] bArrM23222b2 = record.m23222b();
        for (int i11 = 0; i11 < bArrM23222b.length && i11 < bArrM23222b2.length; i11++) {
            int i12 = (bArrM23222b[i11] & UnsignedBytes.MAX_VALUE) - (bArrM23222b2[i11] & UnsignedBytes.MAX_VALUE);
            if (i12 != 0) {
                return i12;
            }
        }
        return bArrM23222b.length - bArrM23222b2.length;
    }

    /* renamed from: d */
    public abstract void mo23195d(C5858d c5858d, C5856b c5856b, boolean z8);

    /* renamed from: e */
    public final void m23223e(C5858d c5858d, boolean z8) {
        this.name.m23214m(c5858d);
        c5858d.m23240h(this.type);
        c5858d.m23240h(this.dclass);
        if (z8) {
            c5858d.m23242j(0L);
        } else {
            c5858d.m23242j(this.ttl);
        }
        int iM23234b = c5858d.m23234b();
        c5858d.m23240h(0);
        mo23195d(c5858d, null, true);
        c5858d.m23241i((c5858d.m23234b() - iM23234b) - 2, iM23234b);
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof Record)) {
            Record record = (Record) obj;
            if (this.type == record.type && this.dclass == record.dclass && this.name.equals(record.name)) {
                return Arrays.equals(m23222b(), record.m23222b());
            }
        }
        return false;
    }

    /* renamed from: g */
    public final byte[] m23224g(boolean z8) {
        C5858d c5858d = new C5858d();
        m23223e(c5858d, z8);
        return c5858d.m23236d();
    }

    public int hashCode() {
        int i9 = 0;
        for (byte b9 : m23224g(true)) {
            i9 += (i9 << 3) + (b9 & UnsignedBytes.MAX_VALUE);
        }
        return i9;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(this.name);
        if (stringBuffer.length() < 8) {
            stringBuffer.append("\t");
        }
        if (stringBuffer.length() < 16) {
            stringBuffer.append("\t");
        }
        stringBuffer.append("\t");
        if (C5862h.m23259a("BINDTTL")) {
            stringBuffer.append(C5864j.m23265b(this.ttl));
        } else {
            stringBuffer.append(this.ttl);
        }
        stringBuffer.append("\t");
        if (this.dclass != 1 || !C5862h.m23259a("noPrintIN")) {
            stringBuffer.append(C5857c.m23231b(this.dclass));
            stringBuffer.append("\t");
        }
        stringBuffer.append(C5865k.m23267b(this.type));
        String strMo23194c = mo23194c();
        if (!strMo23194c.equals("")) {
            stringBuffer.append("\t");
            stringBuffer.append(strMo23194c);
        }
        return stringBuffer.toString();
    }
}
