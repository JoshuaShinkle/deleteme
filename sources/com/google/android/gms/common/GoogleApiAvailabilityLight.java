package com.google.android.gms.common;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.HideFirstParty;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.util.DeviceProperties;
import com.google.android.gms.common.wrappers.Wrappers;

@ShowFirstParty
@KeepForSdk
/* loaded from: classes2.dex */
public class GoogleApiAvailabilityLight {

    @KeepForSdk
    public static final String GOOGLE_PLAY_SERVICES_PACKAGE = "com.google.android.gms";

    @KeepForSdk
    public static final String GOOGLE_PLAY_STORE_PACKAGE = "com.android.vending";

    @KeepForSdk
    static final String TRACKING_SOURCE_DIALOG = "d";

    @KeepForSdk
    static final String TRACKING_SOURCE_NOTIFICATION = "n";

    @KeepForSdk
    public static final int GOOGLE_PLAY_SERVICES_VERSION_CODE = GooglePlayServicesUtilLight.GOOGLE_PLAY_SERVICES_VERSION_CODE;
    private static final GoogleApiAvailabilityLight zza = new GoogleApiAvailabilityLight();

    @KeepForSdk
    public GoogleApiAvailabilityLight() {
    }

    @KeepForSdk
    public static GoogleApiAvailabilityLight getInstance() {
        return zza;
    }

    @KeepForSdk
    public void cancelAvailabilityErrorNotifications(Context context) {
        GooglePlayServicesUtilLight.cancelAvailabilityErrorNotifications(context);
    }

    @ShowFirstParty
    @KeepForSdk
    public int getApkVersion(Context context) {
        return GooglePlayServicesUtilLight.getApkVersion(context);
    }

    @ShowFirstParty
    @KeepForSdk
    public int getClientVersion(Context context) {
        return GooglePlayServicesUtilLight.getClientVersion(context);
    }

    @ShowFirstParty
    @KeepForSdk
    @Deprecated
    public Intent getErrorResolutionIntent(int i9) {
        return getErrorResolutionIntent(null, i9, null);
    }

    @KeepForSdk
    public PendingIntent getErrorResolutionPendingIntent(Context context, int i9, int i10) {
        return getErrorResolutionPendingIntent(context, i9, i10, null);
    }

    @KeepForSdk
    public String getErrorString(int i9) {
        return GooglePlayServicesUtilLight.getErrorString(i9);
    }

    @HideFirstParty
    @KeepForSdk
    public int isGooglePlayServicesAvailable(Context context) {
        return isGooglePlayServicesAvailable(context, GOOGLE_PLAY_SERVICES_VERSION_CODE);
    }

    @ShowFirstParty
    @KeepForSdk
    public boolean isPlayServicesPossiblyUpdating(Context context, int i9) {
        return GooglePlayServicesUtilLight.isPlayServicesPossiblyUpdating(context, i9);
    }

    @ShowFirstParty
    @KeepForSdk
    public boolean isPlayStorePossiblyUpdating(Context context, int i9) {
        return GooglePlayServicesUtilLight.isPlayStorePossiblyUpdating(context, i9);
    }

    @KeepForSdk
    public boolean isUninstalledAppPossiblyUpdating(Context context, String str) {
        return GooglePlayServicesUtilLight.zza(context, str);
    }

    @KeepForSdk
    public boolean isUserResolvableError(int i9) {
        return GooglePlayServicesUtilLight.isUserRecoverableError(i9);
    }

    @KeepForSdk
    public void verifyGooglePlayServicesIsAvailable(Context context, int i9) {
        GooglePlayServicesUtilLight.ensurePlayServicesAvailable(context, i9);
    }

    @ShowFirstParty
    @KeepForSdk
    public Intent getErrorResolutionIntent(Context context, int i9, String str) {
        if (i9 != 1 && i9 != 2) {
            if (i9 != 3) {
                return null;
            }
            return com.google.android.gms.common.internal.zzt.zzc("com.google.android.gms");
        }
        if (context != null && DeviceProperties.isWearableWithoutPlayStore(context)) {
            return com.google.android.gms.common.internal.zzt.zza();
        }
        StringBuilder sb = new StringBuilder();
        sb.append("gcore_");
        sb.append(GOOGLE_PLAY_SERVICES_VERSION_CODE);
        sb.append("-");
        if (!TextUtils.isEmpty(str)) {
            sb.append(str);
        }
        sb.append("-");
        if (context != null) {
            sb.append(context.getPackageName());
        }
        sb.append("-");
        if (context != null) {
            try {
                sb.append(Wrappers.packageManager(context).getPackageInfo(context.getPackageName(), 0).versionCode);
            } catch (PackageManager.NameNotFoundException unused) {
            }
        }
        return com.google.android.gms.common.internal.zzt.zzb("com.google.android.gms", sb.toString());
    }

    @ShowFirstParty
    @KeepForSdk
    public PendingIntent getErrorResolutionPendingIntent(Context context, int i9, int i10, String str) {
        Intent errorResolutionIntent = getErrorResolutionIntent(context, i9, str);
        if (errorResolutionIntent == null) {
            return null;
        }
        return PendingIntent.getActivity(context, i10, errorResolutionIntent, com.google.android.gms.internal.common.zzd.zza | 134217728);
    }

    @KeepForSdk
    public int isGooglePlayServicesAvailable(Context context, int i9) {
        int iIsGooglePlayServicesAvailable = GooglePlayServicesUtilLight.isGooglePlayServicesAvailable(context, i9);
        if (GooglePlayServicesUtilLight.isPlayServicesPossiblyUpdating(context, iIsGooglePlayServicesAvailable)) {
            return 18;
        }
        return iIsGooglePlayServicesAvailable;
    }
}
