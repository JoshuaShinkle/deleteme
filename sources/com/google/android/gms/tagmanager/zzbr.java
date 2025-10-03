package com.google.android.gms.tagmanager;

import android.annotation.TargetApi;
import android.os.Build;
import java.io.File;

/* loaded from: classes2.dex */
final class zzbr {
    private static int version() {
        try {
            return Integer.parseInt(Build.VERSION.SDK);
        } catch (NumberFormatException unused) {
            String strValueOf = String.valueOf(Build.VERSION.SDK);
            zzdi.zzav(strValueOf.length() != 0 ? "Invalid version number: ".concat(strValueOf) : new String("Invalid version number: "));
            return 0;
        }
    }

    @TargetApi(9)
    public static boolean zzbb(String str) {
        if (version() < 9) {
            return false;
        }
        File file = new File(str);
        file.setReadable(false, false);
        file.setWritable(false, false);
        file.setReadable(true, true);
        file.setWritable(true, true);
        return true;
    }
}
