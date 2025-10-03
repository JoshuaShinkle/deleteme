package com.google.android.gms.common.util;

import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
/* loaded from: classes2.dex */
public class MurmurHash3 {
    private MurmurHash3() {
    }

    @KeepForSdk
    public static int murmurhash3_x86_32(byte[] bArr, int i9, int i10, int i11) {
        int i12 = (i10 & (-4)) + i9;
        while (i9 < i12) {
            int i13 = ((bArr[i9] & 255) | ((bArr[i9 + 1] & 255) << 8) | ((bArr[i9 + 2] & 255) << 16) | (bArr[i9 + 3] << 24)) * (-862048943);
            int i14 = i11 ^ (((i13 << 15) | (i13 >>> 17)) * 461845907);
            i11 = (((i14 >>> 19) | (i14 << 13)) * 5) - 430675100;
            i9 += 4;
        }
        int i15 = i10 & 3;
        if (i15 == 1) {
            int i16 = ((bArr[i12] & 255) | i) * (-862048943);
            i11 ^= ((i16 >>> 17) | (i16 << 15)) * 461845907;
        } else {
            if (i15 != 2) {
                i = i15 == 3 ? (bArr[i12 + 2] & 255) << 16 : 0;
            }
            i |= (bArr[i12 + 1] & 255) << 8;
            int i162 = ((bArr[i12] & 255) | i) * (-862048943);
            i11 ^= ((i162 >>> 17) | (i162 << 15)) * 461845907;
        }
        int i17 = i11 ^ i10;
        int i18 = (i17 ^ (i17 >>> 16)) * (-2048144789);
        int i19 = (i18 ^ (i18 >>> 13)) * (-1028477387);
        return i19 ^ (i19 >>> 16);
    }
}
