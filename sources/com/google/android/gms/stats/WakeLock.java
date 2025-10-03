package com.google.android.gms.stats;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.PowerManager;
import android.os.WorkSource;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.providers.PooledExecutorsProvider;
import com.google.android.gms.common.stats.StatsUtils;
import com.google.android.gms.common.stats.WakeLockTracker;
import com.google.android.gms.common.util.Strings;
import com.google.android.gms.common.util.WorkSourceUtil;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@ShowFirstParty
@KeepForSdk
/* loaded from: classes2.dex */
public class WakeLock {
    private static ScheduledExecutorService zzn;
    private static volatile zza zzo = new com.google.android.gms.stats.zza();
    private final Object zza;
    private final PowerManager.WakeLock zzb;
    private WorkSource zzc;
    private final int zzd;
    private final String zze;
    private final String zzf;
    private final String zzg;
    private final Context zzh;
    private boolean zzi;
    private final Map<String, Integer[]> zzj;
    private final Set<Future<?>> zzk;
    private int zzl;
    private AtomicInteger zzm;

    public interface zza {
    }

    @KeepForSdk
    public WakeLock(Context context, int i9, String str) {
        this(context, i9, str, null, context == null ? null : context.getPackageName());
    }

    private final List<String> zza() {
        return WorkSourceUtil.getNames(this.zzc);
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0056 A[Catch: all -> 0x0096, TryCatch #0 {, blocks: (B:4:0x000d, B:6:0x0016, B:11:0x0029, B:13:0x002e, B:15:0x0038, B:22:0x005e, B:23:0x007d, B:16:0x0047, B:18:0x0056, B:20:0x005a, B:8:0x001a, B:10:0x0022), top: B:31:0x000d }] */
    /* JADX WARN: Removed duplicated region for block: B:22:0x005e A[Catch: all -> 0x0096, TryCatch #0 {, blocks: (B:4:0x000d, B:6:0x0016, B:11:0x0029, B:13:0x002e, B:15:0x0038, B:22:0x005e, B:23:0x007d, B:16:0x0047, B:18:0x0056, B:20:0x005a, B:8:0x001a, B:10:0x0022), top: B:31:0x000d }] */
    @KeepForSdk
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void acquire(long j9) {
        this.zzm.incrementAndGet();
        String strZza = zza((String) null);
        synchronized (this.zza) {
            boolean z8 = false;
            if ((!this.zzj.isEmpty() || this.zzl > 0) && !this.zzb.isHeld()) {
                this.zzj.clear();
                this.zzl = 0;
            }
            if (this.zzi) {
                Integer[] numArr = this.zzj.get(strZza);
                if (numArr == null) {
                    this.zzj.put(strZza, new Integer[]{1});
                    z8 = true;
                } else {
                    numArr[0] = Integer.valueOf(numArr[0].intValue() + 1);
                }
                if (!z8) {
                }
            } else if (!this.zzi && this.zzl == 0) {
                WakeLockTracker.getInstance().registerEvent(this.zzh, StatsUtils.getEventKey(this.zzb, strZza), 7, this.zze, strZza, null, this.zzd, zza(), j9);
                this.zzl++;
            }
        }
        this.zzb.acquire();
        if (j9 > 0) {
            zzn.schedule(new zzb(this), j9, TimeUnit.MILLISECONDS);
        }
    }

    @KeepForSdk
    public boolean isHeld() {
        return this.zzb.isHeld();
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0052 A[Catch: all -> 0x007e, TryCatch #0 {, blocks: (B:7:0x0021, B:9:0x0027, B:21:0x005a, B:22:0x0079, B:12:0x0033, B:14:0x003b, B:15:0x0042, B:17:0x0052, B:19:0x0056), top: B:28:0x0021 }] */
    /* JADX WARN: Removed duplicated region for block: B:21:0x005a A[Catch: all -> 0x007e, TryCatch #0 {, blocks: (B:7:0x0021, B:9:0x0027, B:21:0x005a, B:22:0x0079, B:12:0x0033, B:14:0x003b, B:15:0x0042, B:17:0x0052, B:19:0x0056), top: B:28:0x0021 }] */
    @KeepForSdk
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void release() {
        boolean z8;
        if (this.zzm.decrementAndGet() < 0) {
            Log.e("WakeLock", String.valueOf(this.zze).concat(" release without a matched acquire!"));
        }
        String strZza = zza((String) null);
        synchronized (this.zza) {
            if (this.zzi) {
                Integer[] numArr = this.zzj.get(strZza);
                if (numArr == null) {
                    z8 = false;
                    if (!z8) {
                    }
                } else if (numArr[0].intValue() == 1) {
                    this.zzj.remove(strZza);
                    z8 = true;
                    if (!z8) {
                    }
                } else {
                    numArr[0] = Integer.valueOf(numArr[0].intValue() - 1);
                    z8 = false;
                    if (!z8) {
                    }
                }
            } else if (!this.zzi && this.zzl == 1) {
                WakeLockTracker.getInstance().registerEvent(this.zzh, StatsUtils.getEventKey(this.zzb, strZza), 8, this.zze, strZza, null, this.zzd, zza());
                this.zzl--;
            }
        }
        zza(0);
    }

    @KeepForSdk
    public void setReferenceCounted(boolean z8) {
        this.zzb.setReferenceCounted(z8);
        this.zzi = z8;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(int i9) {
        if (this.zzb.isHeld()) {
            try {
                this.zzb.release();
            } catch (RuntimeException e9) {
                if (!e9.getClass().equals(RuntimeException.class)) {
                    throw e9;
                }
                Log.e("WakeLock", String.valueOf(this.zze).concat(" was already released!"), e9);
            }
            this.zzb.isHeld();
        }
    }

    private WakeLock(Context context, int i9, String str, String str2, String str3) {
        this(context, i9, str, null, str3, null);
    }

    @SuppressLint({"UnwrappedWakeLock"})
    private WakeLock(Context context, int i9, String str, String str2, String str3, String str4) {
        this.zza = this;
        this.zzi = true;
        this.zzj = new HashMap();
        this.zzk = Collections.synchronizedSet(new HashSet());
        this.zzm = new AtomicInteger(0);
        Preconditions.checkNotNull(context, "WakeLock: context must not be null");
        Preconditions.checkNotEmpty(str, "WakeLock: wakeLockName must not be empty");
        this.zzd = i9;
        this.zzf = null;
        this.zzg = null;
        Context applicationContext = context.getApplicationContext();
        this.zzh = applicationContext;
        if (!"com.google.android.gms".equals(context.getPackageName())) {
            String strValueOf = String.valueOf(str);
            this.zze = strValueOf.length() != 0 ? "*gcore*:".concat(strValueOf) : new String("*gcore*:");
        } else {
            this.zze = str;
        }
        PowerManager.WakeLock wakeLockNewWakeLock = ((PowerManager) context.getSystemService("power")).newWakeLock(i9, str);
        this.zzb = wakeLockNewWakeLock;
        if (WorkSourceUtil.hasWorkSourcePermission(context)) {
            WorkSource workSourceFromPackage = WorkSourceUtil.fromPackage(context, Strings.isEmptyOrWhitespace(str3) ? context.getPackageName() : str3);
            this.zzc = workSourceFromPackage;
            if (workSourceFromPackage != null && WorkSourceUtil.hasWorkSourcePermission(applicationContext)) {
                WorkSource workSource = this.zzc;
                if (workSource != null) {
                    workSource.add(workSourceFromPackage);
                } else {
                    this.zzc = workSourceFromPackage;
                }
                try {
                    wakeLockNewWakeLock.setWorkSource(this.zzc);
                } catch (ArrayIndexOutOfBoundsException | IllegalArgumentException e9) {
                    Log.wtf("WakeLock", e9.toString());
                }
            }
        }
        if (zzn == null) {
            zzn = PooledExecutorsProvider.getInstance().newSingleThreadScheduledExecutor();
        }
    }

    private final String zza(String str) {
        return (!this.zzi || TextUtils.isEmpty(str)) ? this.zzf : str;
    }
}
