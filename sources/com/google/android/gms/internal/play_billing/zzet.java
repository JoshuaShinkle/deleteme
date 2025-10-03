package com.google.android.gms.internal.play_billing;

import com.google.common.base.Ascii;

/* loaded from: classes2.dex */
final class zzet extends zzes {
    @Override // com.google.android.gms.internal.play_billing.zzes
    public final int zza(int i9, byte[] bArr, int i10, int i11) {
        while (i10 < i11 && bArr[i10] >= 0) {
            i10++;
        }
        if (i10 >= i11) {
            return 0;
        }
        while (i10 < i11) {
            int i12 = i10 + 1;
            byte b9 = bArr[i10];
            if (b9 < 0) {
                if (b9 < -32) {
                    if (i12 >= i11) {
                        return b9;
                    }
                    if (b9 >= -62) {
                        i10 = i12 + 1;
                        if (bArr[i12] > -65) {
                        }
                    }
                    return -1;
                }
                if (b9 < -16) {
                    if (i12 >= i11 - 1) {
                        return zzev.zza(bArr, i12, i11);
                    }
                    int i13 = i12 + 1;
                    byte b10 = bArr[i12];
                    if (b10 <= -65 && ((b9 != -32 || b10 >= -96) && (b9 != -19 || b10 < -96))) {
                        i10 = i13 + 1;
                        if (bArr[i13] > -65) {
                        }
                    }
                } else {
                    if (i12 >= i11 - 2) {
                        return zzev.zza(bArr, i12, i11);
                    }
                    int i14 = i12 + 1;
                    byte b11 = bArr[i12];
                    if (b11 <= -65 && (((b9 << Ascii.f15383FS) + (b11 + 112)) >> 30) == 0) {
                        int i15 = i14 + 1;
                        if (bArr[i14] <= -65) {
                            i12 = i15 + 1;
                            if (bArr[i15] > -65) {
                            }
                        }
                    }
                }
                return -1;
            }
            i10 = i12;
        }
        return 0;
    }
}
