package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import org.apache.commons.lang3.time.DateUtils;

/* loaded from: classes2.dex */
final class zzki {
    private final Clock zza;
    private long zzb;

    public zzki(Clock clock) {
        Preconditions.checkNotNull(clock);
        this.zza = clock;
    }

    public final void zza() {
        this.zzb = this.zza.elapsedRealtime();
    }

    public final void zzb() {
        this.zzb = 0L;
    }

    public final boolean zza(long j9) {
        return this.zzb == 0 || this.zza.elapsedRealtime() - this.zzb >= DateUtils.MILLIS_PER_HOUR;
    }
}
