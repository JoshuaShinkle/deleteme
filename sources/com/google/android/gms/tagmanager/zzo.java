package com.google.android.gms.tagmanager;

import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.common.primitives.UnsignedBytes;

@ShowFirstParty
/* loaded from: classes2.dex */
public final class zzo {
    public static byte[] decode(String str) {
        int length = str.length();
        if (length % 2 != 0) {
            throw new IllegalArgumentException("purported base16 string has odd number of characters");
        }
        byte[] bArr = new byte[length / 2];
        for (int i9 = 0; i9 < length; i9 += 2) {
            int iDigit = Character.digit(str.charAt(i9), 16);
            int iDigit2 = Character.digit(str.charAt(i9 + 1), 16);
            if (iDigit == -1 || iDigit2 == -1) {
                throw new IllegalArgumentException("purported base16 string has illegal char");
            }
            bArr[i9 / 2] = (byte) ((iDigit << 4) + iDigit2);
        }
        return bArr;
    }

    public static String encode(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        for (byte b9 : bArr) {
            if ((b9 & 240) == 0) {
                sb.append("0");
            }
            sb.append(Integer.toHexString(b9 & UnsignedBytes.MAX_VALUE));
        }
        return sb.toString().toUpperCase();
    }
}
