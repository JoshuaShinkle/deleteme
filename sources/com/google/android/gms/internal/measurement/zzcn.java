package com.google.android.gms.internal.measurement;

import android.database.ContentObserver;
import android.os.Handler;

/* loaded from: classes2.dex */
final class zzcn extends ContentObserver {
    public zzcn(Handler handler) {
        super(null);
    }

    @Override // android.database.ContentObserver
    public final void onChange(boolean z8) {
        zzck.zze.set(true);
    }
}
