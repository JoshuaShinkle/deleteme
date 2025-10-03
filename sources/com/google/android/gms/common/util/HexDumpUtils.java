package com.google.android.gms.common.util;

import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.common.primitives.UnsignedBytes;

@KeepForSdk
/* loaded from: classes2.dex */
public final class HexDumpUtils {
    @KeepForSdk
    public static String dump(byte[] bArr, int i9, int i10, boolean z8) {
        int length;
        if (bArr == null || (length = bArr.length) == 0 || i9 < 0 || i10 <= 0 || i9 + i10 > length) {
            return null;
        }
        StringBuilder sb = new StringBuilder((z8 ? 75 : 57) * ((i10 + 15) / 16));
        int i11 = i10;
        int i12 = 0;
        int i13 = 0;
        while (i11 > 0) {
            if (i12 == 0) {
                if (i10 < 65536) {
                    sb.append(String.format("%04X:", Integer.valueOf(i9)));
                } else {
                    sb.append(String.format("%08X:", Integer.valueOf(i9)));
                }
                i13 = i9;
            } else if (i12 == 8) {
                sb.append(" -");
            }
            sb.append(String.format(" %02X", Integer.valueOf(bArr[i9] & UnsignedBytes.MAX_VALUE)));
            i11--;
            i12++;
            if (z8 && (i12 == 16 || i11 == 0)) {
                int i14 = 16 - i12;
                if (i14 > 0) {
                    for (int i15 = 0; i15 < i14; i15++) {
                        sb.append("   ");
                    }
                }
                if (i14 >= 8) {
                    sb.append("  ");
                }
                sb.append("  ");
                for (int i16 = 0; i16 < i12; i16++) {
                    char c9 = (char) bArr[i13 + i16];
                    if (c9 < ' ' || c9 > '~') {
                        c9 = '.';
                    }
                    sb.append(c9);
                }
            }
            if (i12 == 16 || i11 == 0) {
                sb.append('\n');
                i12 = 0;
            }
            i9++;
        }
        return sb.toString();
    }
}
