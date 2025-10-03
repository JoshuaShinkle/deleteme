package com.google.android.gms.internal.gtm;

import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;

/* loaded from: classes2.dex */
final class zzcv {
    private long startTime;
    private final Clock zzsd;

    public zzcv(Clock clock) {
        Preconditions.checkNotNull(clock);
        this.zzsd = clock;
    }

    public final void clear() {
        this.startTime = 0L;
    }

    public final void start() {
        this.startTime = this.zzsd.elapsedRealtime();
    }

    public final boolean zzj(long j9) {
        return this.startTime == 0 || this.zzsd.elapsedRealtime() - this.startTime > j9;
    }

    public zzcv(Clock clock, long j9) {
        Preconditions.checkNotNull(clock);
        this.zzsd = clock;
        this.startTime = j9;
    }
}
