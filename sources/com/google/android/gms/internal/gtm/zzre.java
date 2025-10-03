package com.google.android.gms.internal.gtm;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import org.apache.commons.lang3.CharEncoding;

/* loaded from: classes2.dex */
public final class zzre {
    public static final byte[] zzbbh;
    private static final ByteBuffer zzbbi;
    private static final zzqe zzbbj;
    static final Charset UTF_8 = Charset.forName("UTF-8");
    private static final Charset ISO_8859_1 = Charset.forName(CharEncoding.ISO_8859_1);

    static {
        byte[] bArr = new byte[0];
        zzbbh = bArr;
        zzbbi = ByteBuffer.wrap(bArr);
        zzbbj = zzqe.zza(bArr, 0, bArr.length, false);
    }

    public static <T> T checkNotNull(T t8) {
        t8.getClass();
        return t8;
    }

    public static int hashCode(byte[] bArr) {
        int length = bArr.length;
        int iZza = zza(length, bArr, 0, length);
        if (iZza == 0) {
            return 1;
        }
        return iZza;
    }

    public static <T> T zza(T t8, String str) {
        if (t8 != null) {
            return t8;
        }
        throw new NullPointerException(str);
    }

    public static Object zzb(Object obj, Object obj2) {
        return ((zzsk) obj).zzpg().zza((zzsk) obj2).zzpl();
    }

    public static boolean zzf(zzsk zzskVar) {
        return false;
    }

    public static boolean zzi(byte[] bArr) {
        return zztz.zzi(bArr);
    }

    public static String zzj(byte[] bArr) {
        return new String(bArr, UTF_8);
    }

    public static int zzk(boolean z8) {
        return z8 ? 1231 : 1237;
    }

    public static int zzz(long j9) {
        return (int) (j9 ^ (j9 >>> 32));
    }

    public static int zza(int i9, byte[] bArr, int i10, int i11) {
        for (int i12 = i10; i12 < i10 + i11; i12++) {
            i9 = (i9 * 31) + bArr[i12];
        }
        return i9;
    }
}
