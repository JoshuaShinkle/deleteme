package com.google.android.gms.common.internal;

import android.util.Log;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.errorprone.annotations.FormatMethod;
import com.google.errorprone.annotations.FormatString;

@KeepForSdk
/* loaded from: classes2.dex */
public final class GmsLogger {
    private final String zza;
    private final String zzb;

    @KeepForSdk
    public GmsLogger(String str) {
        this(str, null);
    }

    private final String zza(String str) {
        String str2 = this.zzb;
        return str2 == null ? str : str2.concat(str);
    }

    @FormatMethod
    private final String zzb(String str, Object... objArr) {
        String str2 = String.format(str, objArr);
        String str3 = this.zzb;
        return str3 == null ? str2 : str3.concat(str2);
    }

    @KeepForSdk
    public boolean canLog(int i9) {
        return Log.isLoggable(this.zza, i9);
    }

    @KeepForSdk
    public boolean canLogPii() {
        return false;
    }

    @KeepForSdk
    /* renamed from: d */
    public void m17509d(String str, String str2) {
        if (canLog(3)) {
            Log.d(str, zza(str2));
        }
    }

    @KeepForSdk
    /* renamed from: e */
    public void m17511e(String str, String str2) {
        if (canLog(6)) {
            Log.e(str, zza(str2));
        }
    }

    @KeepForSdk
    @FormatMethod
    public void efmt(String str, @FormatString String str2, Object... objArr) {
        if (canLog(6)) {
            Log.e(str, zzb(str2, objArr));
        }
    }

    @KeepForSdk
    /* renamed from: i */
    public void m17513i(String str, String str2) {
        if (canLog(4)) {
            Log.i(str, zza(str2));
        }
    }

    @KeepForSdk
    public void pii(String str, String str2) {
    }

    @KeepForSdk
    public void pii(String str, String str2, Throwable th) {
    }

    @KeepForSdk
    /* renamed from: v */
    public void m17515v(String str, String str2) {
        if (canLog(2)) {
            Log.v(str, zza(str2));
        }
    }

    @KeepForSdk
    /* renamed from: w */
    public void m17517w(String str, String str2) {
        if (canLog(5)) {
            Log.w(str, zza(str2));
        }
    }

    @KeepForSdk
    @FormatMethod
    public void wfmt(String str, @FormatString String str2, Object... objArr) {
        if (canLog(5)) {
            Log.w(this.zza, zzb(str2, objArr));
        }
    }

    @KeepForSdk
    public void wtf(String str, String str2, Throwable th) {
        if (canLog(7)) {
            Log.e(str, zza(str2), th);
            Log.wtf(str, zza(str2), th);
        }
    }

    @KeepForSdk
    public GmsLogger(String str, String str2) {
        Preconditions.checkNotNull(str, "log tag cannot be null");
        Preconditions.checkArgument(str.length() <= 23, "tag \"%s\" is longer than the %d character maximum", str, 23);
        this.zza = str;
        if (str2 == null || str2.length() <= 0) {
            this.zzb = null;
        } else {
            this.zzb = str2;
        }
    }

    @KeepForSdk
    /* renamed from: d */
    public void m17510d(String str, String str2, Throwable th) {
        if (canLog(3)) {
            Log.d(str, zza(str2), th);
        }
    }

    @KeepForSdk
    /* renamed from: e */
    public void m17512e(String str, String str2, Throwable th) {
        if (canLog(6)) {
            Log.e(str, zza(str2), th);
        }
    }

    @KeepForSdk
    /* renamed from: i */
    public void m17514i(String str, String str2, Throwable th) {
        if (canLog(4)) {
            Log.i(str, zza(str2), th);
        }
    }

    @KeepForSdk
    /* renamed from: v */
    public void m17516v(String str, String str2, Throwable th) {
        if (canLog(2)) {
            Log.v(str, zza(str2), th);
        }
    }

    @KeepForSdk
    /* renamed from: w */
    public void m17518w(String str, String str2, Throwable th) {
        if (canLog(5)) {
            Log.w(str, zza(str2), th);
        }
    }
}
