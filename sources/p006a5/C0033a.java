package p006a5;

import com.google.common.primitives.UnsignedBytes;
import org.apache.commons.p159io.IOUtils;

/* renamed from: a5.a */
/* loaded from: classes2.dex */
public class C0033a {

    /* renamed from: a */
    public static final char[] f130a = new char[64];

    /* renamed from: b */
    public static final byte[] f131b;

    static {
        char c9 = 'A';
        int i9 = 0;
        while (c9 <= 'Z') {
            f130a[i9] = c9;
            c9 = (char) (c9 + 1);
            i9++;
        }
        char c10 = 'a';
        while (c10 <= 'z') {
            f130a[i9] = c10;
            c10 = (char) (c10 + 1);
            i9++;
        }
        char c11 = '0';
        while (c11 <= '9') {
            f130a[i9] = c11;
            c11 = (char) (c11 + 1);
            i9++;
        }
        char[] cArr = f130a;
        cArr[i9] = '+';
        cArr[i9 + 1] = IOUtils.DIR_SEPARATOR_UNIX;
        f131b = new byte[128];
        int i10 = 0;
        while (true) {
            byte[] bArr = f131b;
            if (i10 >= bArr.length) {
                break;
            }
            bArr[i10] = -1;
            i10++;
        }
        for (int i11 = 0; i11 < 64; i11++) {
            f131b[f130a[i11]] = (byte) i11;
        }
    }

    /* renamed from: a */
    public static char[] m135a(byte[] bArr) {
        return m136b(bArr, bArr.length);
    }

    /* renamed from: b */
    public static char[] m136b(byte[] bArr, int i9) {
        int i10;
        int i11;
        int i12;
        int i13 = ((i9 * 4) + 2) / 3;
        char[] cArr = new char[((i9 + 2) / 3) * 4];
        int i14 = 0;
        int i15 = 0;
        while (i14 < i9) {
            int i16 = i14 + 1;
            int i17 = bArr[i14] & UnsignedBytes.MAX_VALUE;
            if (i16 < i9) {
                i10 = i16 + 1;
                i11 = bArr[i16] & UnsignedBytes.MAX_VALUE;
            } else {
                i10 = i16;
                i11 = 0;
            }
            if (i10 < i9) {
                i12 = bArr[i10] & UnsignedBytes.MAX_VALUE;
                i10++;
            } else {
                i12 = 0;
            }
            int i18 = i17 >>> 2;
            int i19 = ((i17 & 3) << 4) | (i11 >>> 4);
            int i20 = ((i11 & 15) << 2) | (i12 >>> 6);
            int i21 = i12 & 63;
            int i22 = i15 + 1;
            char[] cArr2 = f130a;
            cArr[i15] = cArr2[i18];
            int i23 = i22 + 1;
            cArr[i22] = cArr2[i19];
            char c9 = '=';
            cArr[i23] = i23 < i13 ? cArr2[i20] : '=';
            int i24 = i23 + 1;
            if (i24 < i13) {
                c9 = cArr2[i21];
            }
            cArr[i24] = c9;
            i15 = i24 + 1;
            i14 = i10;
        }
        return cArr;
    }

    /* renamed from: c */
    public static String m137c(String str) {
        return new String(m135a(str.getBytes()));
    }
}
