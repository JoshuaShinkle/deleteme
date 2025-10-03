package com.google.android.gms.internal.gtm;

import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;

@VisibleForTesting
/* loaded from: classes2.dex */
public final class zzcg {
    private final long zzabf;
    private final int zzabg;
    private double zzabh;
    private long zzabi;
    private final Object zzabj;
    private final Clock zzsd;
    private final String zzup;

    private zzcg(int i9, long j9, String str, Clock clock) {
        this.zzabj = new Object();
        this.zzabg = 60;
        this.zzabh = 60;
        this.zzabf = AdaptiveTrackSelection.DEFAULT_MIN_TIME_BETWEEN_BUFFER_REEVALUTATION_MS;
        this.zzup = str;
        this.zzsd = clock;
    }

    public final boolean zzfm() {
        synchronized (this.zzabj) {
            long jCurrentTimeMillis = this.zzsd.currentTimeMillis();
            double d9 = this.zzabh;
            int i9 = this.zzabg;
            if (d9 < i9) {
                double d10 = (jCurrentTimeMillis - this.zzabi) / this.zzabf;
                if (d10 > FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
                    this.zzabh = Math.min(i9, d9 + d10);
                }
            }
            this.zzabi = jCurrentTimeMillis;
            double d11 = this.zzabh;
            if (d11 >= 1.0d) {
                this.zzabh = d11 - 1.0d;
                return true;
            }
            String str = this.zzup;
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 34);
            sb.append("Excessive ");
            sb.append(str);
            sb.append(" detected; call ignored.");
            zzch.zzac(sb.toString());
            return false;
        }
    }

    public zzcg(String str, Clock clock) {
        this(60, AdaptiveTrackSelection.DEFAULT_MIN_TIME_BETWEEN_BUFFER_REEVALUTATION_MS, str, clock);
    }
}
