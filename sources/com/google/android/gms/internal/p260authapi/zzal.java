package com.google.android.gms.internal.p260authapi;

import android.util.Base64;
import java.util.Random;

/* loaded from: classes2.dex */
public final class zzal {
    private static final Random zzcy = new Random();

    public static String zzs() {
        byte[] bArr = new byte[16];
        zzcy.nextBytes(bArr);
        return Base64.encodeToString(bArr, 11);
    }
}
