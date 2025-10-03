package com.google.android.gms.internal.gtm;

import android.util.Log;
import com.google.android.gms.analytics.Logger;

/* loaded from: classes2.dex */
final class zzbr implements Logger {
    private boolean zzrv;
    private int zzyr = 2;

    @Override // com.google.android.gms.analytics.Logger
    public final void error(Exception exc) {
    }

    @Override // com.google.android.gms.analytics.Logger
    public final void error(String str) {
    }

    @Override // com.google.android.gms.analytics.Logger
    public final int getLogLevel() {
        return this.zzyr;
    }

    @Override // com.google.android.gms.analytics.Logger
    public final void info(String str) {
    }

    @Override // com.google.android.gms.analytics.Logger
    public final void setLogLevel(int i9) {
        this.zzyr = i9;
        if (this.zzrv) {
            return;
        }
        String str = zzby.zzzb.get();
        String str2 = zzby.zzzb.get();
        StringBuilder sb = new StringBuilder(String.valueOf(str2).length() + 91);
        sb.append("Logger is deprecated. To enable debug logging, please run:\nadb shell setprop log.tag.");
        sb.append(str2);
        sb.append(" DEBUG");
        Log.i(str, sb.toString());
        this.zzrv = true;
    }

    @Override // com.google.android.gms.analytics.Logger
    public final void verbose(String str) {
    }

    @Override // com.google.android.gms.analytics.Logger
    public final void warn(String str) {
    }
}
