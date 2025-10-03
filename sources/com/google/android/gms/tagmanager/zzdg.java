package com.google.android.gms.tagmanager;

import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;

@VisibleForTesting
/* loaded from: classes2.dex */
final class zzdg implements zzej {
    private long zzabi;
    private final Clock zzsd;
    private final String zzup;
    private final Object zzabj = new Object();
    private final int zzabg = 5;
    private double zzabh = Math.min(1, 5);
    private final long zzabf = 900000;
    private final long zzaia = DefaultRenderersFactory.DEFAULT_ALLOWED_VIDEO_JOINING_TIME_MS;

    public zzdg(int i9, int i10, long j9, long j10, String str, Clock clock) {
        this.zzup = str;
        this.zzsd = clock;
    }

    @Override // com.google.android.gms.tagmanager.zzej
    public final boolean zzfm() {
        synchronized (this.zzabj) {
            long jCurrentTimeMillis = this.zzsd.currentTimeMillis();
            if (jCurrentTimeMillis - this.zzabi < this.zzaia) {
                String str = this.zzup;
                StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 34);
                sb.append("Excessive ");
                sb.append(str);
                sb.append(" detected; call ignored.");
                zzdi.zzac(sb.toString());
                return false;
            }
            double d9 = this.zzabh;
            int i9 = this.zzabg;
            if (d9 < i9) {
                double d10 = (jCurrentTimeMillis - r3) / this.zzabf;
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
            String str2 = this.zzup;
            StringBuilder sb2 = new StringBuilder(String.valueOf(str2).length() + 34);
            sb2.append("Excessive ");
            sb2.append(str2);
            sb2.append(" detected; call ignored.");
            zzdi.zzac(sb2.toString());
            return false;
        }
    }
}
