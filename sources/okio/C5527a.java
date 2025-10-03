package okio;

import com.google.common.base.Ascii;
import com.google.common.primitives.UnsignedBytes;
import java.util.Arrays;
import okio.ByteString;
import p007a6.C0042f;
import p204t6.C6345z;

/* renamed from: okio.a */
/* loaded from: classes.dex */
public final class C5527a {

    /* renamed from: a */
    public static final byte[] f19101a;

    /* renamed from: b */
    public static final byte[] f19102b;

    static {
        ByteString.C5526a c5526a = ByteString.f19095d;
        f19101a = c5526a.m21901d("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/").m21879e();
        f19102b = c5526a.m21901d("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_").m21879e();
    }

    /* renamed from: a */
    public static final byte[] m21908a(String str) {
        int i9;
        char cCharAt;
        C0042f.m158e(str, "<this>");
        int length = str.length();
        while (length > 0 && ((cCharAt = str.charAt(length - 1)) == '=' || cCharAt == '\n' || cCharAt == '\r' || cCharAt == ' ' || cCharAt == '\t')) {
            length--;
        }
        int i10 = (int) ((length * 6) / 8);
        byte[] bArr = new byte[i10];
        int i11 = 0;
        int i12 = 0;
        int i13 = 0;
        int i14 = 0;
        while (true) {
            if (i11 >= length) {
                int i15 = i12 % 4;
                if (i15 == 1) {
                    return null;
                }
                if (i15 == 2) {
                    bArr[i14] = (byte) ((i13 << 12) >> 16);
                    i14++;
                } else if (i15 == 3) {
                    int i16 = i13 << 6;
                    int i17 = i14 + 1;
                    bArr[i14] = (byte) (i16 >> 16);
                    i14 = i17 + 1;
                    bArr[i17] = (byte) (i16 >> 8);
                }
                if (i14 == i10) {
                    return bArr;
                }
                byte[] bArrCopyOf = Arrays.copyOf(bArr, i14);
                C0042f.m157d(bArrCopyOf, "copyOf(this, newSize)");
                return bArrCopyOf;
            }
            char cCharAt2 = str.charAt(i11);
            if ('A' <= cCharAt2 && cCharAt2 < '[') {
                i9 = cCharAt2 - 'A';
            } else {
                if ('a' <= cCharAt2 && cCharAt2 < '{') {
                    i9 = cCharAt2 - 'G';
                } else {
                    if ('0' <= cCharAt2 && cCharAt2 < ':') {
                        i9 = cCharAt2 + 4;
                    } else if (cCharAt2 == '+' || cCharAt2 == '-') {
                        i9 = 62;
                    } else if (cCharAt2 == '/' || cCharAt2 == '_') {
                        i9 = 63;
                    } else {
                        if (cCharAt2 != '\n' && cCharAt2 != '\r' && cCharAt2 != ' ' && cCharAt2 != '\t') {
                            return null;
                        }
                        i11++;
                    }
                }
            }
            i13 = (i13 << 6) | i9;
            i12++;
            if (i12 % 4 == 0) {
                int i18 = i14 + 1;
                bArr[i14] = (byte) (i13 >> 16);
                int i19 = i18 + 1;
                bArr[i18] = (byte) (i13 >> 8);
                bArr[i19] = (byte) i13;
                i14 = i19 + 1;
            }
            i11++;
        }
    }

    /* renamed from: b */
    public static final String m21909b(byte[] bArr, byte[] bArr2) {
        C0042f.m158e(bArr, "<this>");
        C0042f.m158e(bArr2, "map");
        byte[] bArr3 = new byte[((bArr.length + 2) / 3) * 4];
        int length = bArr.length - (bArr.length % 3);
        int i9 = 0;
        int i10 = 0;
        while (i9 < length) {
            int i11 = i9 + 1;
            byte b9 = bArr[i9];
            int i12 = i11 + 1;
            byte b10 = bArr[i11];
            int i13 = i12 + 1;
            byte b11 = bArr[i12];
            int i14 = i10 + 1;
            bArr3[i10] = bArr2[(b9 & UnsignedBytes.MAX_VALUE) >> 2];
            int i15 = i14 + 1;
            bArr3[i14] = bArr2[((b9 & 3) << 4) | ((b10 & UnsignedBytes.MAX_VALUE) >> 4)];
            int i16 = i15 + 1;
            bArr3[i15] = bArr2[((b10 & Ascii.f15389SI) << 2) | ((b11 & UnsignedBytes.MAX_VALUE) >> 6)];
            i10 = i16 + 1;
            bArr3[i16] = bArr2[b11 & 63];
            i9 = i13;
        }
        int length2 = bArr.length - length;
        if (length2 == 1) {
            byte b12 = bArr[i9];
            int i17 = i10 + 1;
            bArr3[i10] = bArr2[(b12 & UnsignedBytes.MAX_VALUE) >> 2];
            int i18 = i17 + 1;
            bArr3[i17] = bArr2[(b12 & 3) << 4];
            bArr3[i18] = 61;
            bArr3[i18 + 1] = 61;
        } else if (length2 == 2) {
            int i19 = i9 + 1;
            byte b13 = bArr[i9];
            byte b14 = bArr[i19];
            int i20 = i10 + 1;
            bArr3[i10] = bArr2[(b13 & UnsignedBytes.MAX_VALUE) >> 2];
            int i21 = i20 + 1;
            bArr3[i20] = bArr2[((b13 & 3) << 4) | ((b14 & UnsignedBytes.MAX_VALUE) >> 4)];
            bArr3[i21] = bArr2[(b14 & Ascii.f15389SI) << 2];
            bArr3[i21 + 1] = 61;
        }
        return C6345z.m24299b(bArr3);
    }

    /* renamed from: c */
    public static /* synthetic */ String m21910c(byte[] bArr, byte[] bArr2, int i9, Object obj) {
        if ((i9 & 1) != 0) {
            bArr2 = f19101a;
        }
        return m21909b(bArr, bArr2);
    }
}
