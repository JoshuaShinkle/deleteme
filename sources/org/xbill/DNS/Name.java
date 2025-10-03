package org.xbill.DNS;

import com.google.common.primitives.UnsignedBytes;
import java.io.Serializable;
import java.text.DecimalFormat;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.p159io.IOUtils;

/* loaded from: classes3.dex */
public class Name implements Comparable, Serializable {

    /* renamed from: b */
    public static final byte[] f20249b = {0};

    /* renamed from: c */
    public static final byte[] f20250c = {1, 42};

    /* renamed from: d */
    public static final Name f20251d;

    /* renamed from: e */
    public static final Name f20252e;

    /* renamed from: f */
    public static final DecimalFormat f20253f;

    /* renamed from: g */
    public static final byte[] f20254g;

    /* renamed from: h */
    public static final Name f20255h;
    private static final long serialVersionUID = -7257019940971525644L;
    private int hashcode;
    private byte[] name;
    private long offsets;

    static {
        DecimalFormat decimalFormat = new DecimalFormat();
        f20253f = decimalFormat;
        f20254g = new byte[256];
        decimalFormat.setMinimumIntegerDigits(3);
        int i9 = 0;
        while (true) {
            byte[] bArr = f20254g;
            if (i9 >= bArr.length) {
                Name name = new Name();
                f20251d = name;
                name.m23203b(f20249b, 0, 1);
                Name name2 = new Name();
                f20252e = name2;
                name2.name = new byte[0];
                Name name3 = new Name();
                f20255h = name3;
                name3.m23203b(f20250c, 0, 1);
                return;
            }
            if (i9 < 65 || i9 > 90) {
                bArr[i9] = (byte) i9;
            } else {
                bArr[i9] = (byte) ((i9 - 65) + 97);
            }
            i9++;
        }
    }

    private Name() {
    }

    /* renamed from: a */
    public final void m23202a(byte[] bArr, int i9, int i10) throws NameTooLongException {
        byte[] bArr2 = this.name;
        int length = bArr2 == null ? 0 : bArr2.length - m23208g(0);
        int i11 = i9;
        int i12 = 0;
        for (int i13 = 0; i13 < i10; i13++) {
            int i14 = bArr[i11];
            if (i14 > 63) {
                throw new IllegalStateException("invalid label");
            }
            int i15 = i14 + 1;
            i11 += i15;
            i12 += i15;
        }
        int i16 = length + i12;
        if (i16 > 255) {
            throw new NameTooLongException();
        }
        int iM23206e = m23206e();
        int i17 = iM23206e + i10;
        if (i17 > 128) {
            throw new IllegalStateException("too many labels");
        }
        byte[] bArr3 = new byte[i16];
        if (length != 0) {
            System.arraycopy(this.name, m23208g(0), bArr3, 0, length);
        }
        System.arraycopy(bArr, i9, bArr3, length, i12);
        this.name = bArr3;
        for (int i18 = 0; i18 < i10; i18++) {
            m23210i(iM23206e + i18, length);
            length += bArr3[length] + 1;
        }
        m23209h(i17);
    }

    /* renamed from: b */
    public final void m23203b(byte[] bArr, int i9, int i10) {
        try {
            m23202a(bArr, i9, i10);
        } catch (NameTooLongException unused) {
        }
    }

    /* renamed from: c */
    public final String m23204c(byte[] bArr, int i9) {
        StringBuffer stringBuffer = new StringBuffer();
        int i10 = i9 + 1;
        int i11 = bArr[i9];
        for (int i12 = i10; i12 < i10 + i11; i12++) {
            int i13 = bArr[i12] & UnsignedBytes.MAX_VALUE;
            if (i13 <= 32 || i13 >= 127) {
                stringBuffer.append(IOUtils.DIR_SEPARATOR_WINDOWS);
                stringBuffer.append(f20253f.format(i13));
            } else if (i13 == 34 || i13 == 40 || i13 == 41 || i13 == 46 || i13 == 59 || i13 == 92 || i13 == 64 || i13 == 36) {
                stringBuffer.append(IOUtils.DIR_SEPARATOR_WINDOWS);
                stringBuffer.append((char) i13);
            } else {
                stringBuffer.append((char) i13);
            }
        }
        return stringBuffer.toString();
    }

    @Override // java.lang.Comparable
    public int compareTo(Object obj) {
        Name name = (Name) obj;
        if (this == name) {
            return 0;
        }
        int iM23207f = m23207f();
        int iM23207f2 = name.m23207f();
        int i9 = iM23207f > iM23207f2 ? iM23207f2 : iM23207f;
        for (int i10 = 1; i10 <= i9; i10++) {
            int iM23208g = m23208g(iM23207f - i10);
            int iM23208g2 = name.m23208g(iM23207f2 - i10);
            byte b9 = this.name[iM23208g];
            byte b10 = name.name[iM23208g2];
            for (int i11 = 0; i11 < b9 && i11 < b10; i11++) {
                byte[] bArr = f20254g;
                int i12 = bArr[this.name[(i11 + iM23208g) + 1] & UnsignedBytes.MAX_VALUE] - bArr[name.name[(i11 + iM23208g2) + 1] & UnsignedBytes.MAX_VALUE];
                if (i12 != 0) {
                    return i12;
                }
            }
            if (b9 != b10) {
                return b9 - b10;
            }
        }
        return iM23207f - iM23207f2;
    }

    /* renamed from: d */
    public final boolean m23205d(byte[] bArr, int i9) {
        int iM23207f = m23207f();
        int iM23208g = m23208g(0);
        for (int i10 = 0; i10 < iM23207f; i10++) {
            byte b9 = this.name[iM23208g];
            if (b9 != bArr[i9]) {
                return false;
            }
            iM23208g++;
            i9++;
            if (b9 > 63) {
                throw new IllegalStateException("invalid label");
            }
            int i11 = 0;
            while (i11 < b9) {
                byte[] bArr2 = f20254g;
                int i12 = iM23208g + 1;
                int i13 = i9 + 1;
                if (bArr2[this.name[iM23208g] & UnsignedBytes.MAX_VALUE] != bArr2[bArr[i9] & UnsignedBytes.MAX_VALUE]) {
                    return false;
                }
                i11++;
                i9 = i13;
                iM23208g = i12;
            }
        }
        return true;
    }

    /* renamed from: e */
    public final int m23206e() {
        return (int) (this.offsets & 255);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || !(obj instanceof Name)) {
            return false;
        }
        Name name = (Name) obj;
        if (name.hashcode == 0) {
            name.hashCode();
        }
        if (this.hashcode == 0) {
            hashCode();
        }
        if (name.hashcode == this.hashcode && name.m23207f() == m23207f()) {
            return m23205d(name.name, name.m23208g(0));
        }
        return false;
    }

    /* renamed from: f */
    public int m23207f() {
        return m23206e();
    }

    /* renamed from: g */
    public final int m23208g(int i9) {
        if (i9 == 0 && m23206e() == 0) {
            return 0;
        }
        if (i9 < 0 || i9 >= m23206e()) {
            throw new IllegalArgumentException("label out of range");
        }
        if (i9 < 7) {
            return ((int) (this.offsets >>> ((7 - i9) * 8))) & 255;
        }
        int iM23208g = m23208g(6);
        for (int i10 = 6; i10 < i9; i10++) {
            iM23208g += this.name[iM23208g] + 1;
        }
        return iM23208g;
    }

    /* renamed from: h */
    public final void m23209h(int i9) {
        this.offsets = (this.offsets & (-256)) | i9;
    }

    public int hashCode() {
        int i9 = this.hashcode;
        if (i9 != 0) {
            return i9;
        }
        int i10 = 0;
        int iM23208g = m23208g(0);
        while (true) {
            byte[] bArr = this.name;
            if (iM23208g >= bArr.length) {
                this.hashcode = i10;
                return i10;
            }
            i10 += (i10 << 3) + f20254g[bArr[iM23208g] & UnsignedBytes.MAX_VALUE];
            iM23208g++;
        }
    }

    /* renamed from: i */
    public final void m23210i(int i9, int i10) {
        if (i9 >= 7) {
            return;
        }
        int i11 = (7 - i9) * 8;
        this.offsets = (i10 << i11) | (this.offsets & (~(255 << i11)));
    }

    public boolean isAbsolute() {
        int iM23207f = m23207f();
        return iM23207f != 0 && this.name[m23208g(iM23207f - 1)] == 0;
    }

    /* JADX WARN: Code restructure failed: missing block: B:26:0x0057, code lost:
    
        return r2.toString();
     */
    /* renamed from: j */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public String m23211j(boolean z8) {
        int iM23207f = m23207f();
        if (iM23207f == 0) {
            return "@";
        }
        int i9 = 0;
        if (iM23207f == 1 && this.name[m23208g(0)] == 0) {
            return ".";
        }
        StringBuffer stringBuffer = new StringBuffer();
        int iM23208g = m23208g(0);
        while (true) {
            if (i9 >= iM23207f) {
                break;
            }
            byte b9 = this.name[iM23208g];
            if (b9 > 63) {
                throw new IllegalStateException("invalid label");
            }
            if (b9 != 0) {
                if (i9 > 0) {
                    stringBuffer.append(ClassUtils.PACKAGE_SEPARATOR_CHAR);
                }
                stringBuffer.append(m23204c(this.name, iM23208g));
                iM23208g += b9 + 1;
                i9++;
            } else if (!z8) {
                stringBuffer.append(ClassUtils.PACKAGE_SEPARATOR_CHAR);
            }
        }
    }

    /* renamed from: k */
    public void m23212k(C5858d c5858d, C5856b c5856b) {
        if (!isAbsolute()) {
            throw new IllegalArgumentException("toWire() called on non-absolute name");
        }
        int iM23207f = m23207f();
        for (int i9 = 0; i9 < iM23207f - 1; i9++) {
            if (i9 != 0) {
                new Name(this, i9);
            }
            int iM23208g = m23208g(i9);
            byte[] bArr = this.name;
            c5858d.m23238f(bArr, iM23208g, bArr[iM23208g] + 1);
        }
        c5858d.m23243k(0);
    }

    /* renamed from: l */
    public void m23213l(C5858d c5858d, C5856b c5856b, boolean z8) {
        if (z8) {
            m23214m(c5858d);
        } else {
            m23212k(c5858d, c5856b);
        }
    }

    /* renamed from: m */
    public void m23214m(C5858d c5858d) {
        c5858d.m23237e(m23215n());
    }

    /* renamed from: n */
    public byte[] m23215n() {
        int iM23207f = m23207f();
        if (iM23207f == 0) {
            return new byte[0];
        }
        byte[] bArr = new byte[this.name.length - m23208g(0)];
        int iM23208g = m23208g(0);
        int i9 = 0;
        for (int i10 = 0; i10 < iM23207f; i10++) {
            byte b9 = this.name[iM23208g];
            if (b9 > 63) {
                throw new IllegalStateException("invalid label");
            }
            iM23208g++;
            bArr[i9] = b9;
            i9++;
            int i11 = 0;
            while (i11 < b9) {
                bArr[i9] = f20254g[this.name[iM23208g] & UnsignedBytes.MAX_VALUE];
                i11++;
                i9++;
                iM23208g++;
            }
        }
        return bArr;
    }

    public String toString() {
        return m23211j(false);
    }

    public Name(Name name, int i9) {
        int iM23207f = name.m23207f();
        if (i9 > iM23207f) {
            throw new IllegalArgumentException("attempted to remove too many labels");
        }
        this.name = name.name;
        int i10 = iM23207f - i9;
        m23209h(i10);
        for (int i11 = 0; i11 < 7 && i11 < i10; i11++) {
            m23210i(i11, name.m23208g(i11 + i9));
        }
    }
}
