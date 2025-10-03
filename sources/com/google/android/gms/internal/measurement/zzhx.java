package com.google.android.gms.internal.measurement;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import org.apache.commons.lang3.CharEncoding;

/* loaded from: classes2.dex */
public final class zzhx {
    public static final byte[] zzb;
    private static final ByteBuffer zzd;
    private static final zzgy zze;
    static final Charset zza = Charset.forName("UTF-8");
    private static final Charset zzc = Charset.forName(CharEncoding.ISO_8859_1);

    static {
        byte[] bArr = new byte[0];
        zzb = bArr;
        zzd = ByteBuffer.wrap(bArr);
        zze = zzgy.zza(bArr, 0, bArr.length, false);
    }

    public static int zza(long j9) {
        return (int) (j9 ^ (j9 >>> 32));
    }

    public static int zza(boolean z8) {
        return z8 ? 1231 : 1237;
    }

    public static <T> T zza(T t8) {
        t8.getClass();
        return t8;
    }

    public static String zzb(byte[] bArr) {
        return new String(bArr, zza);
    }

    public static int zzc(byte[] bArr) {
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

    public static boolean zza(byte[] bArr) {
        return zzkw.zza(bArr);
    }

    public static int zza(int i9, byte[] bArr, int i10, int i11) {
        for (int i12 = i10; i12 < i10 + i11; i12++) {
            i9 = (i9 * 31) + bArr[i12];
        }
        return i9;
    }

    public static boolean zza(zzjg zzjgVar) {
        if (!(zzjgVar instanceof zzge)) {
            return false;
        }
        return false;
    }

    public static Object zza(Object obj, Object obj2) {
        return ((zzjg) obj).zzbt().zza((zzjg) obj2).zzx();
    }
}
