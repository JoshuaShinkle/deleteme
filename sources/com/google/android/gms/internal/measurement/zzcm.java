package com.google.android.gms.internal.measurement;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Process;
import android.os.UserManager;
import android.util.Log;

/* loaded from: classes2.dex */
public class zzcm {
    private static UserManager zza;
    private static volatile boolean zzb = !zza();
    private static boolean zzc = false;

    private zzcm() {
    }

    public static boolean zza() {
        return true;
    }

    public static boolean zza(Context context) {
        return !zza() || zzc(context);
    }

    @TargetApi(24)
    private static boolean zzb(Context context) {
        boolean z8;
        boolean z9 = true;
        int i9 = 1;
        while (true) {
            z8 = false;
            if (i9 > 2) {
                break;
            }
            if (zza == null) {
                zza = (UserManager) context.getSystemService(UserManager.class);
            }
            UserManager userManager = zza;
            if (userManager == null) {
                return true;
            }
            try {
                if (userManager.isUserUnlocked()) {
                    break;
                }
                if (userManager.isUserRunning(Process.myUserHandle())) {
                    z9 = false;
                }
            } catch (NullPointerException e9) {
                Log.w("DirectBootUtils", "Failed to check if user is unlocked.", e9);
                zza = null;
                i9++;
            }
        }
        z8 = z9;
        if (z8) {
            zza = null;
        }
        return z8;
    }

    @TargetApi(24)
    private static boolean zzc(Context context) {
        if (zzb) {
            return true;
        }
        synchronized (zzcm.class) {
            if (zzb) {
                return true;
            }
            boolean zZzb = zzb(context);
            if (zZzb) {
                zzb = zZzb;
            }
            return zZzb;
        }
    }
}
