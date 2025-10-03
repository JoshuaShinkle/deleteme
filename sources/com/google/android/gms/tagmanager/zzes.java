package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/* loaded from: classes2.dex */
final class zzes implements zzag {
    private boolean closed;
    private final String zzaec;
    private String zzafd;
    private zzdh<com.google.android.gms.internal.gtm.zzk> zzajf;
    private zzal zzajg;
    private final ScheduledExecutorService zzaji;
    private final zzev zzajj;
    private ScheduledFuture<?> zzajk;
    private final Context zzrm;

    public zzes(Context context, String str, zzal zzalVar) {
        this(context, str, zzalVar, null, null);
    }

    private final synchronized void zzjb() {
        if (this.closed) {
            throw new IllegalStateException("called method after closed");
        }
    }

    @Override // com.google.android.gms.common.api.Releasable
    public final synchronized void release() {
        zzjb();
        ScheduledFuture<?> scheduledFuture = this.zzajk;
        if (scheduledFuture != null) {
            scheduledFuture.cancel(false);
        }
        this.zzaji.shutdown();
        this.closed = true;
    }

    @Override // com.google.android.gms.tagmanager.zzag
    public final synchronized void zza(zzdh<com.google.android.gms.internal.gtm.zzk> zzdhVar) {
        zzjb();
        this.zzajf = zzdhVar;
    }

    @Override // com.google.android.gms.tagmanager.zzag
    public final synchronized void zzap(String str) {
        zzjb();
        this.zzafd = str;
    }

    @VisibleForTesting
    private zzes(Context context, String str, zzal zzalVar, zzew zzewVar, zzev zzevVar) {
        this.zzajg = zzalVar;
        this.zzrm = context;
        this.zzaec = str;
        this.zzaji = new zzet(this).zzjc();
        this.zzajj = new zzeu(this);
    }

    @Override // com.google.android.gms.tagmanager.zzag
    public final synchronized void zza(long j9, String str) {
        String str2 = this.zzaec;
        StringBuilder sb = new StringBuilder(String.valueOf(str2).length() + 55);
        sb.append("loadAfterDelay: containerId=");
        sb.append(str2);
        sb.append(" delay=");
        sb.append(j9);
        zzdi.zzab(sb.toString());
        zzjb();
        if (this.zzajf != null) {
            ScheduledFuture<?> scheduledFuture = this.zzajk;
            if (scheduledFuture != null) {
                scheduledFuture.cancel(false);
            }
            ScheduledExecutorService scheduledExecutorService = this.zzaji;
            zzer zzerVarZza = this.zzajj.zza(this.zzajg);
            zzerVarZza.zza(this.zzajf);
            zzerVarZza.zzap(this.zzafd);
            zzerVarZza.zzbi(str);
            this.zzajk = scheduledExecutorService.schedule(zzerVarZza, j9, TimeUnit.MILLISECONDS);
        } else {
            throw new IllegalStateException("callback must be set before loadAfterDelay() is called.");
        }
    }
}
