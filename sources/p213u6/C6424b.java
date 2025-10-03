package p213u6;

import com.google.common.base.Ascii;
import com.google.common.primitives.UnsignedBytes;
import okio.ByteString;
import p007a6.C0042f;
import p203t5.C6319g;
import p204t6.C6322c;

/* renamed from: u6.b */
/* loaded from: classes.dex */
public final class C6424b {

    /* renamed from: a */
    public static final char[] f21653a = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    /* JADX WARN: Removed duplicated region for block: B:162:0x0163  */
    /* JADX WARN: Removed duplicated region for block: B:164:0x0167  */
    /* JADX WARN: Removed duplicated region for block: B:241:0x020e  */
    /* JADX WARN: Removed duplicated region for block: B:243:0x0212  */
    /* JADX WARN: Removed duplicated region for block: B:258:0x0047 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:261:0x0081 A[EDGE_INSN: B:261:0x0081->B:55:0x0081 BREAK  A[LOOP:1: B:31:0x0051->B:59:0x0088, LOOP_LABEL: LOOP:0: B:3:0x0008->B:289:0x0008], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:268:0x00da A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:276:0x016c A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:287:0x0217 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:52:0x007b  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x007f  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x00d4  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x00d8  */
    /* renamed from: c */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final int m24581c(byte[] bArr, int i9) {
        byte b9;
        boolean z8;
        boolean z9;
        int i10;
        boolean z10;
        boolean z11;
        boolean z12;
        int length = bArr.length;
        int i11 = 0;
        int i12 = 0;
        int i13 = 0;
        loop0: while (i11 < length) {
            byte b10 = bArr[i11];
            if (b10 >= 0) {
                int i14 = i13 + 1;
                if (i13 == i9) {
                    return i12;
                }
                if (b10 != 10 && b10 != 13) {
                    if (!(b10 >= 0 && b10 < 32)) {
                        if (!(127 <= b10 && b10 < 160)) {
                            z9 = false;
                        }
                        if (z9) {
                        }
                        return -1;
                    }
                    z9 = true;
                    if (z9) {
                    }
                    return -1;
                }
                if (b10 == 65533) {
                    return -1;
                }
                i12 += b10 < 65536 ? 1 : 2;
                i11++;
                while (true) {
                    i13 = i14;
                    if (i11 >= length || (b9 = bArr[i11]) < 0) {
                        break;
                    }
                    i11++;
                    i14 = i13 + 1;
                    if (i13 == i9) {
                        return i12;
                    }
                    if (b9 != 10 && b9 != 13) {
                        if (b9 >= 0 && b9 < 32) {
                            z8 = true;
                            if (!z8) {
                                break loop0;
                            }
                        } else {
                            if (!(127 <= b9 && b9 < 160)) {
                                z8 = false;
                            }
                            if (!z8) {
                            }
                        }
                    } else {
                        if (b9 == 65533) {
                            break loop0;
                        }
                        i12 += b9 < 65536 ? 1 : 2;
                    }
                }
                return -1;
            }
            if ((b10 >> 5) == -2) {
                int i15 = i11 + 1;
                if (length <= i15) {
                    if (i13 == i9) {
                        return i12;
                    }
                    return -1;
                }
                byte b11 = bArr[i15];
                if (!((b11 & 192) == 128)) {
                    if (i13 == i9) {
                        return i12;
                    }
                    return -1;
                }
                int i16 = (b11 ^ UnsignedBytes.MAX_POWER_OF_TWO) ^ (b10 << 6);
                if (i16 < 128) {
                    if (i13 == i9) {
                        return i12;
                    }
                    return -1;
                }
                i10 = i13 + 1;
                if (i13 == i9) {
                    return i12;
                }
                if (i16 != 10 && i16 != 13) {
                    if (i16 >= 0 && i16 < 32) {
                        z12 = true;
                        if (z12) {
                        }
                        return -1;
                    }
                    if (!(127 <= i16 && i16 < 160)) {
                        z12 = false;
                    }
                    if (z12) {
                    }
                    return -1;
                }
                if (i16 == 65533) {
                    return -1;
                }
                i12 += i16 < 65536 ? 1 : 2;
                C6319g c6319g = C6319g.f21314a;
                i11 += 2;
            } else if ((b10 >> 4) == -2) {
                int i17 = i11 + 2;
                if (length <= i17) {
                    if (i13 == i9) {
                        return i12;
                    }
                    return -1;
                }
                byte b12 = bArr[i11 + 1];
                if (!((b12 & 192) == 128)) {
                    if (i13 == i9) {
                        return i12;
                    }
                    return -1;
                }
                byte b13 = bArr[i17];
                if (!((b13 & 192) == 128)) {
                    if (i13 == i9) {
                        return i12;
                    }
                    return -1;
                }
                int i18 = ((b13 ^ UnsignedBytes.MAX_POWER_OF_TWO) ^ (b12 << 6)) ^ (b10 << Ascii.f15382FF);
                if (i18 < 2048) {
                    if (i13 == i9) {
                        return i12;
                    }
                    return -1;
                }
                if (55296 <= i18 && i18 < 57344) {
                    if (i13 == i9) {
                        return i12;
                    }
                    return -1;
                }
                i10 = i13 + 1;
                if (i13 == i9) {
                    return i12;
                }
                if (i18 != 10 && i18 != 13) {
                    if (i18 >= 0 && i18 < 32) {
                        z11 = true;
                        if (z11) {
                        }
                        return -1;
                    }
                    if (!(127 <= i18 && i18 < 160)) {
                        z11 = false;
                    }
                    if (z11) {
                    }
                    return -1;
                }
                if (i18 == 65533) {
                    return -1;
                }
                i12 += i18 < 65536 ? 1 : 2;
                C6319g c6319g2 = C6319g.f21314a;
                i11 += 3;
            } else {
                if ((b10 >> 3) != -2) {
                    if (i13 == i9) {
                        return i12;
                    }
                    return -1;
                }
                int i19 = i11 + 3;
                if (length <= i19) {
                    if (i13 == i9) {
                        return i12;
                    }
                    return -1;
                }
                byte b14 = bArr[i11 + 1];
                if (!((b14 & 192) == 128)) {
                    if (i13 == i9) {
                        return i12;
                    }
                    return -1;
                }
                byte b15 = bArr[i11 + 2];
                if (!((b15 & 192) == 128)) {
                    if (i13 == i9) {
                        return i12;
                    }
                    return -1;
                }
                byte b16 = bArr[i19];
                if (!((b16 & 192) == 128)) {
                    if (i13 == i9) {
                        return i12;
                    }
                    return -1;
                }
                int i20 = (((b16 ^ UnsignedBytes.MAX_POWER_OF_TWO) ^ (b15 << 6)) ^ (b14 << Ascii.f15382FF)) ^ (b10 << Ascii.DC2);
                if (i20 > 1114111) {
                    if (i13 == i9) {
                        return i12;
                    }
                    return -1;
                }
                if (55296 <= i20 && i20 < 57344) {
                    if (i13 == i9) {
                        return i12;
                    }
                    return -1;
                }
                if (i20 < 65536) {
                    if (i13 == i9) {
                        return i12;
                    }
                    return -1;
                }
                i10 = i13 + 1;
                if (i13 == i9) {
                    return i12;
                }
                if (i20 != 10 && i20 != 13) {
                    if (i20 >= 0 && i20 < 32) {
                        z10 = true;
                        if (z10) {
                        }
                        return -1;
                    }
                    if (!(127 <= i20 && i20 < 160)) {
                        z10 = false;
                    }
                    if (z10) {
                    }
                    return -1;
                }
                if (i20 == 65533) {
                    return -1;
                }
                i12 += i20 < 65536 ? 1 : 2;
                C6319g c6319g3 = C6319g.f21314a;
                i11 += 4;
            }
            i13 = i10;
        }
        return i12;
    }

    /* renamed from: d */
    public static final void m24582d(ByteString byteString, C6322c c6322c, int i9, int i10) {
        C0042f.m158e(byteString, "<this>");
        C0042f.m158e(c6322c, "buffer");
        c6322c.write(byteString.m21879e(), i9, i10);
    }

    /* renamed from: e */
    public static final int m24583e(char c9) {
        if ('0' <= c9 && c9 < ':') {
            return c9 - '0';
        }
        char c10 = 'a';
        if (!('a' <= c9 && c9 < 'g')) {
            c10 = 'A';
            if (!('A' <= c9 && c9 < 'G')) {
                throw new IllegalArgumentException("Unexpected hex digit: " + c9);
            }
        }
        return (c9 - c10) + 10;
    }

    /* renamed from: f */
    public static final char[] m24584f() {
        return f21653a;
    }
}
