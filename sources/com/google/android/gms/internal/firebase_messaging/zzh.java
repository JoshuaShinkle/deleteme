package com.google.android.gms.internal.firebase_messaging;

import com.google.firebase.crashlytics.internal.metadata.UserMetadata;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayDeque;
import java.util.Queue;

/* loaded from: classes2.dex */
public final class zzh {
    private static final OutputStream zza = new zzg();

    private static byte[] zza(Queue<byte[]> queue, int i9) {
        byte[] bArr = new byte[i9];
        int i10 = i9;
        while (i10 > 0) {
            byte[] bArrRemove = queue.remove();
            int iMin = Math.min(i10, bArrRemove.length);
            System.arraycopy(bArrRemove, 0, bArr, i9 - i10, iMin);
            i10 -= iMin;
        }
        return bArr;
    }

    public static byte[] zza(InputStream inputStream) throws IOException {
        zze.zza(inputStream);
        ArrayDeque arrayDeque = new ArrayDeque(20);
        int iZza = UserMetadata.MAX_INTERNAL_KEY_SIZE;
        int i9 = 0;
        while (i9 < 2147483639) {
            int iMin = Math.min(iZza, 2147483639 - i9);
            byte[] bArr = new byte[iMin];
            arrayDeque.add(bArr);
            int i10 = 0;
            while (i10 < iMin) {
                int i11 = inputStream.read(bArr, i10, iMin - i10);
                if (i11 == -1) {
                    return zza(arrayDeque, i9);
                }
                i10 += i11;
                i9 += i11;
            }
            iZza = zzl.zza(iZza, 2);
        }
        if (inputStream.read() == -1) {
            return zza(arrayDeque, 2147483639);
        }
        throw new OutOfMemoryError("input is too large to fit in a byte array");
    }

    public static InputStream zza(InputStream inputStream, long j9) {
        return new zzj(inputStream, 1048577L);
    }
}
