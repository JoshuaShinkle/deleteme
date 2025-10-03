package com.google.android.gms.internal.measurement;

import android.database.ContentObserver;
import android.os.Handler;

/* loaded from: classes2.dex */
final class zzcz extends ContentObserver {
    public zzcz(zzcx zzcxVar, Handler handler) {
        super(null);
    }

    @Override // android.database.ContentObserver
    public final void onChange(boolean z8) {
        zzdc.zza();
    }
}
