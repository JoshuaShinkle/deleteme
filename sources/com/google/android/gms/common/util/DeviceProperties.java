package com.google.android.gms.common.util;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import com.google.android.gms.common.GooglePlayServicesUtilLight;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
/* loaded from: classes2.dex */
public final class DeviceProperties {
    private static Boolean zza;
    private static Boolean zzb;
    private static Boolean zzc;
    private static Boolean zzd;
    private static Boolean zze;
    private static Boolean zzf;
    private static Boolean zzg;
    private static Boolean zzh;
    private static Boolean zzi;
    private static Boolean zzj;
    private static Boolean zzk;
    private static Boolean zzl;

    private DeviceProperties() {
    }

    @KeepForSdk
    public static boolean isAuto(Context context) {
        PackageManager packageManager = context.getPackageManager();
        if (zzi == null) {
            boolean z8 = false;
            if (PlatformVersion.isAtLeastO() && packageManager.hasSystemFeature("android.hardware.type.automotive")) {
                z8 = true;
            }
            zzi = Boolean.valueOf(z8);
        }
        return zzi.booleanValue();
    }

    @KeepForSdk
    public static boolean isBstar(Context context) {
        if (zzl == null) {
            boolean z8 = false;
            if (PlatformVersion.isAtLeastR() && context.getPackageManager().hasSystemFeature("com.google.android.play.feature.HPE_EXPERIENCE")) {
                z8 = true;
            }
            zzl = Boolean.valueOf(z8);
        }
        return zzl.booleanValue();
    }

    @KeepForSdk
    public static boolean isLatchsky(Context context) {
        if (zzf == null) {
            PackageManager packageManager = context.getPackageManager();
            boolean z8 = false;
            if (packageManager.hasSystemFeature("com.google.android.feature.services_updater") && packageManager.hasSystemFeature("cn.google.services")) {
                z8 = true;
            }
            zzf = Boolean.valueOf(z8);
        }
        return zzf.booleanValue();
    }

    @KeepForSdk
    public static boolean isPhone(Context context) {
        if (zza == null) {
            boolean z8 = false;
            if (!isTablet(context) && !isWearable(context) && !zzb(context)) {
                if (zzh == null) {
                    zzh = Boolean.valueOf(context.getPackageManager().hasSystemFeature("org.chromium.arc"));
                }
                if (!zzh.booleanValue() && !isAuto(context) && !isTv(context)) {
                    if (zzk == null) {
                        zzk = Boolean.valueOf(context.getPackageManager().hasSystemFeature("com.google.android.feature.AMATI_EXPERIENCE"));
                    }
                    if (!zzk.booleanValue() && !isBstar(context)) {
                        z8 = true;
                    }
                }
            }
            zza = Boolean.valueOf(z8);
        }
        return zza.booleanValue();
    }

    @KeepForSdk
    public static boolean isSevenInchTablet(Context context) {
        return zzc(context.getResources());
    }

    @KeepForSdk
    @TargetApi(21)
    public static boolean isSidewinder(Context context) {
        return zza(context);
    }

    @KeepForSdk
    public static boolean isTablet(Context context) {
        return isTablet(context.getResources());
    }

    @KeepForSdk
    public static boolean isTv(Context context) {
        PackageManager packageManager = context.getPackageManager();
        if (zzj == null) {
            boolean z8 = true;
            if (!packageManager.hasSystemFeature("com.google.android.tv") && !packageManager.hasSystemFeature("android.hardware.type.television") && !packageManager.hasSystemFeature("android.software.leanback")) {
                z8 = false;
            }
            zzj = Boolean.valueOf(z8);
        }
        return zzj.booleanValue();
    }

    @KeepForSdk
    public static boolean isUserBuild() {
        int i9 = GooglePlayServicesUtilLight.GOOGLE_PLAY_SERVICES_VERSION_CODE;
        return "user".equals(Build.TYPE);
    }

    @KeepForSdk
    @TargetApi(20)
    public static boolean isWearable(Context context) {
        PackageManager packageManager = context.getPackageManager();
        if (zzd == null) {
            boolean z8 = false;
            if (PlatformVersion.isAtLeastKitKatWatch() && packageManager.hasSystemFeature("android.hardware.type.watch")) {
                z8 = true;
            }
            zzd = Boolean.valueOf(z8);
        }
        return zzd.booleanValue();
    }

    @KeepForSdk
    @TargetApi(26)
    public static boolean isWearableWithoutPlayStore(Context context) {
        if (isWearable(context) && !PlatformVersion.isAtLeastN()) {
            return true;
        }
        if (zza(context)) {
            return !PlatformVersion.isAtLeastO() || PlatformVersion.isAtLeastR();
        }
        return false;
    }

    @TargetApi(21)
    public static boolean zza(Context context) {
        if (zze == null) {
            boolean z8 = false;
            if (PlatformVersion.isAtLeastLollipop() && context.getPackageManager().hasSystemFeature("cn.google")) {
                z8 = true;
            }
            zze = Boolean.valueOf(z8);
        }
        return zze.booleanValue();
    }

    public static boolean zzb(Context context) {
        if (zzg == null) {
            boolean z8 = true;
            if (!context.getPackageManager().hasSystemFeature("android.hardware.type.iot") && !context.getPackageManager().hasSystemFeature("android.hardware.type.embedded")) {
                z8 = false;
            }
            zzg = Boolean.valueOf(z8);
        }
        return zzg.booleanValue();
    }

    public static boolean zzc(Resources resources) {
        boolean z8 = false;
        if (resources == null) {
            return false;
        }
        if (zzc == null) {
            Configuration configuration = resources.getConfiguration();
            if ((configuration.screenLayout & 15) <= 3 && configuration.smallestScreenWidthDp >= 600) {
                z8 = true;
            }
            zzc = Boolean.valueOf(z8);
        }
        return zzc.booleanValue();
    }

    @KeepForSdk
    public static boolean isTablet(Resources resources) {
        if (resources == null) {
            return false;
        }
        if (zzb == null) {
            zzb = Boolean.valueOf((resources.getConfiguration().screenLayout & 15) > 3 || zzc(resources));
        }
        return zzb.booleanValue();
    }
}
