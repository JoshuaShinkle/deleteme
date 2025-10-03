package com.google.android.gms.internal.firebase_messaging;

import com.google.android.exoplayer2.C3322C;
import com.google.android.exoplayer2.extractor.ogg.DefaultOggSeeker;
import com.google.android.gms.auth.api.credentials.CredentialsApi;

/* loaded from: classes2.dex */
public final class zzl {
    private static final byte[] zza = {9, 9, 9, 8, 8, 8, 7, 7, 7, 6, 6, 6, 6, 5, 5, 5, 4, 4, 4, 3, 3, 3, 3, 2, 2, 2, 1, 1, 1, 0, 0, 0, 0};
    private static final int[] zzb = {1, 10, 100, CredentialsApi.ACTIVITY_RESULT_ADD_ACCOUNT, 10000, DefaultOggSeeker.MATCH_BYTE_RANGE, 1000000, 10000000, 100000000, 1000000000};
    private static final int[] zzc = {3, 31, 316, 3162, 31622, 316227, 3162277, 31622776, 316227766, Integer.MAX_VALUE};
    private static final int[] zzd = {1, 1, 2, 6, 24, 120, 720, 5040, 40320, 362880, 3628800, 39916800, 479001600};
    private static int[] zze = {Integer.MAX_VALUE, Integer.MAX_VALUE, C3322C.DEFAULT_BUFFER_SEGMENT_SIZE, 2345, 477, 193, 110, 75, 58, 49, 43, 39, 37, 35, 34, 34, 33};

    public static int zza(int i9, int i10) {
        long j9 = i9 << 1;
        if (j9 > 2147483647L) {
            return Integer.MAX_VALUE;
        }
        if (j9 < -2147483648L) {
            return Integer.MIN_VALUE;
        }
        return (int) j9;
    }
}
