package com.google.android.gms.internal.play_billing;

import com.google.firebase.messaging.Constants;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import org.apache.commons.lang3.CharEncoding;

/* loaded from: classes2.dex */
public final class zzcg {
    static final Charset zza = Charset.forName("US-ASCII");
    static final Charset zzb = Charset.forName("UTF-8");
    static final Charset zzc = Charset.forName(CharEncoding.ISO_8859_1);
    public static final byte[] zzd;
    public static final ByteBuffer zze;
    public static final zzbe zzf;

    static {
        byte[] bArr = new byte[0];
        zzd = bArr;
        zze = ByteBuffer.wrap(bArr);
        int i9 = zzbe.zza;
        zzbc zzbcVar = new zzbc(bArr, 0, 0, false, null);
        try {
            zzbcVar.zza(0);
            zzf = zzbcVar;
        } catch (zzci e9) {
            throw new IllegalArgumentException(e9);
        }
    }

    public static int zza(boolean z8) {
        return z8 ? 1231 : 1237;
    }

    public static int zzb(int i9, byte[] bArr, int i10, int i11) {
        for (int i12 = 0; i12 < i11; i12++) {
            i9 = (i9 * 31) + bArr[i12];
        }
        return i9;
    }

    public static Object zzc(Object obj, String str) {
        if (obj != null) {
            return obj;
        }
        throw new NullPointerException(Constants.FirelogAnalytics.PARAM_MESSAGE_TYPE);
    }

    public static String zzd(byte[] bArr) {
        return new String(bArr, zzb);
    }
}
