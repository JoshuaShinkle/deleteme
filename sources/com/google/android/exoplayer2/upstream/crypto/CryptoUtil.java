package com.google.android.exoplayer2.upstream.crypto;

/* loaded from: classes.dex */
final class CryptoUtil {
    private CryptoUtil() {
    }

    public static long getFNV64Hash(String str) {
        long j9 = 0;
        if (str == null) {
            return 0L;
        }
        for (int i9 = 0; i9 < str.length(); i9++) {
            long jCharAt = j9 ^ str.charAt(i9);
            j9 = jCharAt + (jCharAt << 1) + (jCharAt << 4) + (jCharAt << 5) + (jCharAt << 7) + (jCharAt << 8) + (jCharAt << 40);
        }
        return j9;
    }
}
