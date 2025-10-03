package p098i6;

import java.net.IDN;
import java.net.InetAddress;
import java.util.Locale;
import kotlin.text.C5255l;
import kotlin.text.StringsKt__StringsKt;
import p007a6.C0042f;
import p204t6.C6322c;

/* renamed from: i6.a */
/* loaded from: classes2.dex */
public final class C5054a {
    /* renamed from: a */
    public static final boolean m19755a(String str) {
        int length = str.length();
        for (int i9 = 0; i9 < length; i9++) {
            char cCharAt = str.charAt(i9);
            if (C0042f.m159f(cCharAt, 31) <= 0 || C0042f.m159f(cCharAt, 127) >= 0 || StringsKt__StringsKt.m20461M(" #%/:?@[\\]", cCharAt, 0, false, 6, null) != -1) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: b */
    public static final boolean m19756b(String str, int i9, int i10, byte[] bArr, int i11) {
        int i12 = i11;
        while (i9 < i10) {
            if (i12 == bArr.length) {
                return false;
            }
            if (i12 != i11) {
                if (str.charAt(i9) != '.') {
                    return false;
                }
                i9++;
            }
            int i13 = i9;
            int i14 = 0;
            while (i13 < i10) {
                char cCharAt = str.charAt(i13);
                if (C0042f.m159f(cCharAt, 48) < 0 || C0042f.m159f(cCharAt, 57) > 0) {
                    break;
                }
                if ((i14 == 0 && i9 != i13) || (i14 = ((i14 * 10) + cCharAt) - 48) > 255) {
                    return false;
                }
                i13++;
            }
            if (i13 - i9 == 0) {
                return false;
            }
            bArr[i12] = (byte) i14;
            i12++;
            i9 = i13;
        }
        return i12 == i11 + 4;
    }

    /* JADX WARN: Code restructure failed: missing block: B:41:0x0096, code lost:
    
        if (r13 == 16) goto L45;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x0098, code lost:
    
        if (r14 != (-1)) goto L44;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x009a, code lost:
    
        return null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x009b, code lost:
    
        r0 = r13 - r14;
        java.lang.System.arraycopy(r9, r14, r9, 16 - r0, r0);
        java.util.Arrays.fill(r9, r14, (16 - r13) + r14, (byte) 0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x00ac, code lost:
    
        return java.net.InetAddress.getByAddress(r9);
     */
    /* JADX WARN: Removed duplicated region for block: B:31:0x006b  */
    /* renamed from: c */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final InetAddress m19757c(String str, int i9, int i10) {
        int i11;
        byte[] bArr = new byte[16];
        int i12 = i9;
        int i13 = -1;
        int i14 = -1;
        int i15 = 0;
        while (true) {
            if (i12 < i10) {
                if (i15 != 16) {
                    int i16 = i12 + 2;
                    if (i16 <= i10 && C5255l.m20524w(str, "::", i12, false, 4, null)) {
                        if (i13 == -1) {
                            i15 += 2;
                            if (i16 != i10) {
                                i14 = i16;
                                i13 = i15;
                                i12 = i14;
                                int i17 = 0;
                                while (i12 < i10) {
                                }
                                i11 = i12 - i14;
                                if (i11 == 0) {
                                    break;
                                }
                                break;
                                break;
                            }
                            i13 = i15;
                            break;
                        }
                        return null;
                    }
                    if (i15 != 0) {
                        if (C5255l.m20524w(str, ":", i12, false, 4, null)) {
                            i12++;
                        } else {
                            if (!C5255l.m20524w(str, ".", i12, false, 4, null) || !m19756b(str, i14, i10, bArr, i15 - 2)) {
                                return null;
                            }
                            i15 += 2;
                        }
                    }
                    i14 = i12;
                    i12 = i14;
                    int i172 = 0;
                    while (i12 < i10) {
                        int iM19768H = C5057d.m19768H(str.charAt(i12));
                        if (iM19768H == -1) {
                            break;
                        }
                        i172 = (i172 << 4) + iM19768H;
                        i12++;
                    }
                    i11 = i12 - i14;
                    if (i11 == 0 || i11 > 4) {
                        break;
                    }
                    int i18 = i15 + 1;
                    bArr[i15] = (byte) ((i172 >>> 8) & 255);
                    i15 = i18 + 1;
                    bArr[i18] = (byte) (i172 & 255);
                } else {
                    return null;
                }
            } else {
                break;
            }
        }
        return null;
    }

    /* renamed from: d */
    public static final String m19758d(byte[] bArr) {
        int i9 = -1;
        int i10 = 0;
        int i11 = 0;
        int i12 = 0;
        while (i11 < bArr.length) {
            int i13 = i11;
            while (i13 < 16 && bArr[i13] == 0 && bArr[i13 + 1] == 0) {
                i13 += 2;
            }
            int i14 = i13 - i11;
            if (i14 > i12 && i14 >= 4) {
                i9 = i11;
                i12 = i14;
            }
            i11 = i13 + 2;
        }
        C6322c c6322c = new C6322c();
        while (i10 < bArr.length) {
            if (i10 == i9) {
                c6322c.writeByte(58);
                i10 += i12;
                if (i10 == 16) {
                    c6322c.writeByte(58);
                }
            } else {
                if (i10 > 0) {
                    c6322c.writeByte(58);
                }
                c6322c.mo24223l((C5057d.m19790d(bArr[i10], 255) << 8) | C5057d.m19790d(bArr[i10 + 1], 255));
                i10 += 2;
            }
        }
        return c6322c.m24197J();
    }

    /* renamed from: e */
    public static final String m19759e(String str) {
        C0042f.m158e(str, "<this>");
        if (!StringsKt__StringsKt.m20451C(str, ":", false, 2, null)) {
            try {
                String ascii = IDN.toASCII(str);
                C0042f.m157d(ascii, "toASCII(host)");
                Locale locale = Locale.US;
                C0042f.m157d(locale, "US");
                String lowerCase = ascii.toLowerCase(locale);
                C0042f.m157d(lowerCase, "this as java.lang.String).toLowerCase(locale)");
                if (lowerCase.length() == 0) {
                    return null;
                }
                if (m19755a(lowerCase)) {
                    return null;
                }
                return lowerCase;
            } catch (IllegalArgumentException unused) {
                return null;
            }
        }
        InetAddress inetAddressM19757c = (C5255l.m20525x(str, "[", false, 2, null) && C5255l.m20512k(str, "]", false, 2, null)) ? m19757c(str, 1, str.length() - 1) : m19757c(str, 0, str.length());
        if (inetAddressM19757c == null) {
            return null;
        }
        byte[] address = inetAddressM19757c.getAddress();
        if (address.length == 16) {
            C0042f.m157d(address, "address");
            return m19758d(address);
        }
        if (address.length == 4) {
            return inetAddressM19757c.getHostAddress();
        }
        throw new AssertionError("Invalid IPv6 address: '" + str + '\'');
    }
}
