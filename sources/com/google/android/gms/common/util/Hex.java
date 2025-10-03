package com.google.android.gms.common.util;

import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.common.base.Ascii;
import com.google.common.primitives.UnsignedBytes;

@ShowFirstParty
@KeepForSdk
/* loaded from: classes2.dex */
public class Hex {
    private static final char[] zza = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    private static final char[] zzb = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    @KeepForSdk
    public static String bytesToStringLowercase(byte[] bArr) {
        int length = bArr.length;
        char[] cArr = new char[length + length];
        int i9 = 0;
        for (byte b9 : bArr) {
            int i10 = b9 & UnsignedBytes.MAX_VALUE;
            int i11 = i9 + 1;
            char[] cArr2 = zzb;
            cArr[i9] = cArr2[i10 >>> 4];
            i9 = i11 + 1;
            cArr[i11] = cArr2[i10 & 15];
        }
        return new String(cArr);
    }

    @KeepForSdk
    public static String bytesToStringUppercase(byte[] bArr) {
        return bytesToStringUppercase(bArr, false);
    }

    @KeepForSdk
    public static byte[] stringToBytes(String str) {
        int length = str.length();
        if (length % 2 != 0) {
            throw new IllegalArgumentException("Hex string has odd number of characters");
        }
        byte[] bArr = new byte[length / 2];
        int i9 = 0;
        while (i9 < length) {
            int i10 = i9 + 2;
            bArr[i9 / 2] = (byte) Integer.parseInt(str.substring(i9, i10), 16);
            i9 = i10;
        }
        return bArr;
    }

    @KeepForSdk
    public static String bytesToStringUppercase(byte[] bArr, boolean z8) {
        int length = bArr.length;
        StringBuilder sb = new StringBuilder(length + length);
        for (int i9 = 0; i9 < length && (!z8 || i9 != length - 1 || (bArr[i9] & UnsignedBytes.MAX_VALUE) != 0); i9++) {
            char[] cArr = zza;
            sb.append(cArr[(bArr[i9] & 240) >>> 4]);
            sb.append(cArr[bArr[i9] & Ascii.f15389SI]);
        }
        return sb.toString();
    }
}
