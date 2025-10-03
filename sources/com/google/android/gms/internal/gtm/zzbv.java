package com.google.android.gms.internal.gtm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.PersistableBundle;
import com.google.android.gms.common.internal.Preconditions;

/* loaded from: classes2.dex */
public final class zzbv extends zzan {
    private boolean zzyv;
    private boolean zzyw;
    private final AlarmManager zzyx;
    private Integer zzyy;

    public zzbv(zzap zzapVar) {
        super(zzapVar);
        this.zzyx = (AlarmManager) getContext().getSystemService("alarm");
    }

    private final int getJobId() {
        if (this.zzyy == null) {
            String strValueOf = String.valueOf(getContext().getPackageName());
            this.zzyy = Integer.valueOf((strValueOf.length() != 0 ? "analytics".concat(strValueOf) : new String("analytics")).hashCode());
        }
        return this.zzyy.intValue();
    }

    private final PendingIntent zzfe() {
        Context context = getContext();
        return PendingIntent.getBroadcast(context, 0, new Intent("com.google.android.gms.analytics.ANALYTICS_DISPATCH").setComponent(new ComponentName(context, "com.google.android.gms.analytics.AnalyticsReceiver")), 0);
    }

    public final void cancel() {
        this.zzyw = false;
        this.zzyx.cancel(zzfe());
        JobScheduler jobScheduler = (JobScheduler) getContext().getSystemService("jobscheduler");
        int jobId = getJobId();
        zza("Cancelling job. JobID", Integer.valueOf(jobId));
        jobScheduler.cancel(jobId);
    }

    @Override // com.google.android.gms.internal.gtm.zzan
    public final void zzaw() throws PackageManager.NameNotFoundException {
        try {
            cancel();
            if (zzbq.zzeq() > 0) {
                Context context = getContext();
                ActivityInfo receiverInfo = context.getPackageManager().getReceiverInfo(new ComponentName(context, "com.google.android.gms.analytics.AnalyticsReceiver"), 0);
                if (receiverInfo == null || !receiverInfo.enabled) {
                    return;
                }
                zzq("Receiver registered for local dispatch.");
                this.zzyv = true;
            }
        } catch (PackageManager.NameNotFoundException unused) {
        }
    }

    public final boolean zzez() {
        return this.zzyw;
    }

    public final boolean zzfc() {
        return this.zzyv;
    }

    public final void zzfd() {
        zzdb();
        Preconditions.checkState(this.zzyv, "Receiver not registered");
        long jZzeq = zzbq.zzeq();
        if (jZzeq > 0) {
            cancel();
            zzcn().elapsedRealtime();
            this.zzyw = true;
            zzby.zzaaq.get().booleanValue();
            zzq("Scheduling upload with JobScheduler");
            Context context = getContext();
            ComponentName componentName = new ComponentName(context, "com.google.android.gms.analytics.AnalyticsJobService");
            int jobId = getJobId();
            PersistableBundle persistableBundle = new PersistableBundle();
            persistableBundle.putString("action", "com.google.android.gms.analytics.ANALYTICS_DISPATCH");
            JobInfo jobInfoBuild = new JobInfo.Builder(jobId, componentName).setMinimumLatency(jZzeq).setOverrideDeadline(jZzeq << 1).setExtras(persistableBundle).build();
            zza("Scheduling job. JobID", Integer.valueOf(jobId));
            zzdb.zza(context, jobInfoBuild, "com.google.android.gms", "DispatchAlarm");
        }
    }
}
