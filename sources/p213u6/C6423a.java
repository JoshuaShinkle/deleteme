package p213u6;

import com.google.common.primitives.UnsignedBytes;
import java.io.EOFException;
import p007a6.C0042f;
import p204t6.C6322c;
import p204t6.C6334o;
import p204t6.C6338s;
import p204t6.C6345z;

/* renamed from: u6.a */
/* loaded from: classes.dex */
public final class C6423a {

    /* renamed from: a */
    public static final byte[] f21652a = C6345z.m24298a("0123456789abcdef");

    /* renamed from: a */
    public static final byte[] m24575a() {
        return f21652a;
    }

    /* renamed from: b */
    public static final String m24576b(C6322c c6322c, long j9) throws EOFException {
        C0042f.m158e(c6322c, "<this>");
        if (j9 > 0) {
            long j10 = j9 - 1;
            if (c6322c.m24236z(j10) == 13) {
                String strM24198K = c6322c.m24198K(j10);
                c6322c.skip(2L);
                return strM24198K;
            }
        }
        String strM24198K2 = c6322c.m24198K(j9);
        c6322c.skip(1L);
        return strM24198K2;
    }

    /* JADX WARN: Code restructure failed: missing block: B:28:0x0065, code lost:
    
        if (r19 == false) goto L30;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0067, code lost:
    
        return -2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0068, code lost:
    
        return r10;
     */
    /* renamed from: c */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final int m24577c(C6322c c6322c, C6334o c6334o, boolean z8) {
        int i9;
        int i10;
        int i11;
        int i12;
        C6338s c6338s;
        C0042f.m158e(c6322c, "<this>");
        C0042f.m158e(c6334o, "options");
        C6338s c6338s2 = c6322c.f21330b;
        if (c6338s2 != null) {
            byte[] bArr = c6338s2.f21367a;
            int i13 = c6338s2.f21368b;
            int i14 = c6338s2.f21369c;
            int[] iArrM24273e = c6334o.m24273e();
            C6338s c6338s3 = c6338s2;
            int i15 = -1;
            int i16 = 0;
            loop0: while (true) {
                int i17 = i16 + 1;
                int i18 = iArrM24273e[i16];
                int i19 = i17 + 1;
                int i20 = iArrM24273e[i17];
                if (i20 != -1) {
                    i15 = i20;
                }
                if (c6338s3 == null) {
                    break;
                }
                if (i18 >= 0) {
                    i9 = i13 + 1;
                    int i21 = bArr[i13] & UnsignedBytes.MAX_VALUE;
                    int i22 = i19 + i18;
                    while (i19 != i22) {
                        if (i21 == iArrM24273e[i19]) {
                            i10 = iArrM24273e[i19 + i18];
                            if (i9 == i14) {
                                c6338s3 = c6338s3.f21372f;
                                C0042f.m155b(c6338s3);
                                i9 = c6338s3.f21368b;
                                bArr = c6338s3.f21367a;
                                i14 = c6338s3.f21369c;
                                if (c6338s3 == c6338s2) {
                                    c6338s3 = null;
                                }
                            }
                        } else {
                            i19++;
                        }
                    }
                    return i15;
                }
                int i23 = i19 + (i18 * (-1));
                while (true) {
                    int i24 = i13 + 1;
                    int i25 = i19 + 1;
                    if ((bArr[i13] & UnsignedBytes.MAX_VALUE) != iArrM24273e[i19]) {
                        return i15;
                    }
                    boolean z9 = i25 == i23;
                    if (i24 == i14) {
                        C0042f.m155b(c6338s3);
                        C6338s c6338s4 = c6338s3.f21372f;
                        C0042f.m155b(c6338s4);
                        i12 = c6338s4.f21368b;
                        byte[] bArr2 = c6338s4.f21367a;
                        i11 = c6338s4.f21369c;
                        if (c6338s4 != c6338s2) {
                            c6338s = c6338s4;
                            bArr = bArr2;
                        } else {
                            if (!z9) {
                                break loop0;
                            }
                            bArr = bArr2;
                            c6338s = null;
                        }
                    } else {
                        C6338s c6338s5 = c6338s3;
                        i11 = i14;
                        i12 = i24;
                        c6338s = c6338s5;
                    }
                    if (z9) {
                        i10 = iArrM24273e[i25];
                        i9 = i12;
                        i14 = i11;
                        c6338s3 = c6338s;
                        break;
                    }
                    i13 = i12;
                    i14 = i11;
                    i19 = i25;
                    c6338s3 = c6338s;
                }
                if (i10 >= 0) {
                    return i10;
                }
                i16 = -i10;
                i13 = i9;
            }
        } else {
            return z8 ? -2 : -1;
        }
    }

    /* renamed from: d */
    public static /* synthetic */ int m24578d(C6322c c6322c, C6334o c6334o, boolean z8, int i9, Object obj) {
        if ((i9 & 2) != 0) {
            z8 = false;
        }
        return m24577c(c6322c, c6334o, z8);
    }
}
