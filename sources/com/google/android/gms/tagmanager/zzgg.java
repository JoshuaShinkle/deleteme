package com.google.android.gms.tagmanager;

import com.google.android.gms.analytics.Logger;

/* loaded from: classes2.dex */
final class zzgg implements Logger {
    @Override // com.google.android.gms.analytics.Logger
    public final void error(String str) {
        zzdi.zzav(str);
    }

    @Override // com.google.android.gms.analytics.Logger
    public final int getLogLevel() {
        int i9 = zzdi.zzyr;
        if (i9 == 2) {
            return 0;
        }
        if (i9 == 3 || i9 == 4) {
            return 1;
        }
        return i9 != 5 ? 3 : 2;
    }

    @Override // com.google.android.gms.analytics.Logger
    public final void info(String str) {
        zzdi.zzaw(str);
    }

    @Override // com.google.android.gms.analytics.Logger
    public final void setLogLevel(int i9) {
        zzdi.zzac("GA uses GTM logger. Please use TagManager.setLogLevel(int) instead.");
    }

    @Override // com.google.android.gms.analytics.Logger
    public final void verbose(String str) {
        zzdi.zzab(str);
    }

    @Override // com.google.android.gms.analytics.Logger
    public final void warn(String str) {
        zzdi.zzac(str);
    }

    @Override // com.google.android.gms.analytics.Logger
    public final void error(Exception exc) {
        zzdi.zza("", exc);
    }
}
