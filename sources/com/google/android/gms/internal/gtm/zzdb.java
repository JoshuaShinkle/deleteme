package com.google.android.gms.internal.gtm;

import android.annotation.TargetApi;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.Context;
import android.os.UserHandle;
import android.util.Log;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@TargetApi(24)
/* loaded from: classes2.dex */
public final class zzdb {
    private static final Method zzacz = zzgk();
    private static final Method zzada = zzgl();
    private static volatile zzdd zzadb = zzdc.zzadc;
    private final JobScheduler zzacy;

    private zzdb(JobScheduler jobScheduler) {
        this.zzacy = jobScheduler;
    }

    private final int zza(JobInfo jobInfo, String str, int i9, String str2) {
        Method method = zzacz;
        if (method != null) {
            try {
                return ((Integer) method.invoke(this.zzacy, jobInfo, str, Integer.valueOf(i9), str2)).intValue();
            } catch (IllegalAccessException | InvocationTargetException e9) {
                Log.e(str2, "error calling scheduleAsPackage", e9);
            }
        }
        return this.zzacy.schedule(jobInfo);
    }

    private static Method zzgk() {
        try {
            return JobScheduler.class.getDeclaredMethod("scheduleAsPackage", JobInfo.class, String.class, Integer.TYPE, String.class);
        } catch (NoSuchMethodException unused) {
            if (!Log.isLoggable("JobSchedulerCompat", 6)) {
                return null;
            }
            Log.e("JobSchedulerCompat", "No scheduleAsPackage method available, falling back to schedule");
            return null;
        }
    }

    private static Method zzgl() {
        try {
            return UserHandle.class.getDeclaredMethod("myUserId", null);
        } catch (NoSuchMethodException unused) {
            if (Log.isLoggable("JobSchedulerCompat", 6)) {
                Log.e("JobSchedulerCompat", "No myUserId method available");
            }
            return null;
        }
    }

    private static int zzgm() {
        Method method = zzada;
        if (method != null) {
            try {
                return ((Integer) method.invoke(null, new Object[0])).intValue();
            } catch (IllegalAccessException | InvocationTargetException e9) {
                if (Log.isLoggable("JobSchedulerCompat", 6)) {
                    Log.e("JobSchedulerCompat", "myUserId invocation illegal", e9);
                }
            }
        }
        return 0;
    }

    public static final /* synthetic */ boolean zzgn() {
        return false;
    }

    public static int zza(Context context, JobInfo jobInfo, String str, String str2) {
        JobScheduler jobScheduler = (JobScheduler) context.getSystemService("jobscheduler");
        if (zzacz != null && zzadb.zzgo() && context.checkSelfPermission("android.permission.UPDATE_DEVICE_STATS") == 0) {
            return new zzdb(jobScheduler).zza(jobInfo, str, zzgm(), str2);
        }
        return jobScheduler.schedule(jobInfo);
    }
}
