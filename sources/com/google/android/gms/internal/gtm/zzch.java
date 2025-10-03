package com.google.android.gms.internal.gtm;

import android.annotation.SuppressLint;
import android.util.Log;
import com.google.android.gms.analytics.Logger;
import com.google.android.gms.common.util.VisibleForTesting;

@VisibleForTesting
@Deprecated
/* loaded from: classes2.dex */
public final class zzch {
    private static volatile Logger zzabk = new zzbr();

    @VisibleForTesting
    public static Logger getLogger() {
        return zzabk;
    }

    private static boolean isLoggable(int i9) {
        return zzabk != null && zzabk.getLogLevel() <= i9;
    }

    @VisibleForTesting
    public static void setLogger(Logger logger) {
        zzabk = logger;
    }

    @SuppressLint({"LogTagMismatch"})
    public static void zzab(String str) {
        zzci zzciVarZzfn = zzci.zzfn();
        if (zzciVarZzfn != null) {
            zzciVarZzfn.zzq(str);
        } else if (isLoggable(0)) {
            Log.v(zzby.zzzb.get(), str);
        }
        Logger logger = zzabk;
        if (logger != null) {
            logger.verbose(str);
        }
    }

    @SuppressLint({"LogTagMismatch"})
    public static void zzac(String str) {
        zzci zzciVarZzfn = zzci.zzfn();
        if (zzciVarZzfn != null) {
            zzciVarZzfn.zzt(str);
        } else if (isLoggable(2)) {
            Log.w(zzby.zzzb.get(), str);
        }
        Logger logger = zzabk;
        if (logger != null) {
            logger.warn(str);
        }
    }

    @SuppressLint({"LogTagMismatch"})
    public static void zzf(String str, Object obj) {
        String string;
        zzci zzciVarZzfn = zzci.zzfn();
        if (zzciVarZzfn != null) {
            zzciVarZzfn.zze(str, obj);
        } else if (isLoggable(3)) {
            if (obj != null) {
                String strValueOf = String.valueOf(obj);
                StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 1 + strValueOf.length());
                sb.append(str);
                sb.append(":");
                sb.append(strValueOf);
                string = sb.toString();
            } else {
                string = str;
            }
            Log.e(zzby.zzzb.get(), string);
        }
        Logger logger = zzabk;
        if (logger != null) {
            logger.error(str);
        }
    }
}
