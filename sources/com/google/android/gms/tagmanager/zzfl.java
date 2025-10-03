package com.google.android.gms.tagmanager;

import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.DefaultClock;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;

/* loaded from: classes2.dex */
final class zzfl implements zzej {
    private final long zzabf;
    private final int zzabg;
    private double zzabh;
    private final Object zzabj;
    private long zzakm;
    private final Clock zzsd;

    private zzfl(int i9, long j9) {
        this.zzabj = new Object();
        this.zzabg = 60;
        this.zzabh = 60;
        this.zzabf = AdaptiveTrackSelection.DEFAULT_MIN_TIME_BETWEEN_BUFFER_REEVALUTATION_MS;
        this.zzsd = DefaultClock.getInstance();
    }

    @Override // com.google.android.gms.tagmanager.zzej
    public final boolean zzfm() {
        synchronized (this.zzabj) {
            long jCurrentTimeMillis = this.zzsd.currentTimeMillis();
            double d9 = this.zzabh;
            int i9 = this.zzabg;
            if (d9 < i9) {
                double d10 = (jCurrentTimeMillis - this.zzakm) / this.zzabf;
                if (d10 > FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
                    this.zzabh = Math.min(i9, d9 + d10);
                }
            }
            this.zzakm = jCurrentTimeMillis;
            double d11 = this.zzabh;
            if (d11 >= 1.0d) {
                this.zzabh = d11 - 1.0d;
                return true;
            }
            zzdi.zzac("No more tokens available.");
            return false;
        }
    }

    public zzfl() {
        this(60, AdaptiveTrackSelection.DEFAULT_MIN_TIME_BETWEEN_BUFFER_REEVALUTATION_MS);
    }
}
