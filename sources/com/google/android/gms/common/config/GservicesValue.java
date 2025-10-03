package com.google.android.gms.common.config;

import android.os.Binder;
import android.os.StrictMode;
import android.util.Log;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.util.VisibleForTesting;

@KeepForSdk
/* loaded from: classes2.dex */
public abstract class GservicesValue<T> {
    private static final Object zzc = new Object();
    protected final String zza;
    protected final Object zzb;
    private Object zzd = null;

    public GservicesValue(String str, Object obj) {
        this.zza = str;
        this.zzb = obj;
    }

    @KeepForSdk
    public static boolean isInitialized() {
        synchronized (zzc) {
        }
        return false;
    }

    @KeepForSdk
    public static GservicesValue<Float> value(String str, Float f9) {
        return new zzd(str, f9);
    }

    @KeepForSdk
    public static GservicesValue<Integer> value(String str, Integer num) {
        return new zzc(str, num);
    }

    @KeepForSdk
    public static GservicesValue<Long> value(String str, Long l9) {
        return new zzb(str, l9);
    }

    @KeepForSdk
    public static GservicesValue<String> value(String str, String str2) {
        return new zze(str, str2);
    }

    @KeepForSdk
    public static GservicesValue<Boolean> value(String str, boolean z8) {
        return new zza(str, Boolean.valueOf(z8));
    }

    @KeepForSdk
    public final T get() {
        T t8 = (T) this.zzd;
        if (t8 != null) {
            return t8;
        }
        StrictMode.ThreadPolicy threadPolicyAllowThreadDiskReads = StrictMode.allowThreadDiskReads();
        Object obj = zzc;
        synchronized (obj) {
        }
        synchronized (obj) {
            try {
            } catch (Throwable th) {
                StrictMode.setThreadPolicy(threadPolicyAllowThreadDiskReads);
                throw th;
            }
        }
        try {
            T t9 = (T) zza(this.zza);
            StrictMode.setThreadPolicy(threadPolicyAllowThreadDiskReads);
            return t9;
        } catch (SecurityException unused) {
            long jClearCallingIdentity = Binder.clearCallingIdentity();
            try {
                T t10 = (T) zza(this.zza);
                StrictMode.setThreadPolicy(threadPolicyAllowThreadDiskReads);
                return t10;
            } finally {
                Binder.restoreCallingIdentity(jClearCallingIdentity);
            }
        }
    }

    @KeepForSdk
    @Deprecated
    public final T getBinderSafe() {
        return get();
    }

    @VisibleForTesting
    @KeepForSdk
    public void override(T t8) {
        Log.w("GservicesValue", "GservicesValue.override(): test should probably call initForTests() first");
        this.zzd = t8;
        Object obj = zzc;
        synchronized (obj) {
            synchronized (obj) {
            }
        }
    }

    @VisibleForTesting
    @KeepForSdk
    public void resetOverride() {
        this.zzd = null;
    }

    public abstract Object zza(String str);
}
