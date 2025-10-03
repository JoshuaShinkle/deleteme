package com.google.firebase;

import android.os.SystemClock;
import com.google.auto.value.AutoValue;

@AutoValue
/* loaded from: classes2.dex */
public abstract class StartupTime {
    public static StartupTime create(long j9, long j10, long j11) {
        return new AutoValue_StartupTime(j9, j10, j11);
    }

    public static StartupTime now() {
        return create(System.currentTimeMillis(), SystemClock.elapsedRealtime(), SystemClock.uptimeMillis());
    }

    public abstract long getElapsedRealtime();

    public abstract long getEpochMillis();

    public abstract long getUptimeMillis();
}
