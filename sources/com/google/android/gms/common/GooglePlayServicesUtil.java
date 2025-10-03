package com.google.android.gms.common;

import android.app.Activity;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import androidx.annotation.RecentlyNonNull;
import androidx.annotation.RecentlyNullable;
import androidx.fragment.app.Fragment;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.HideFirstParty;
import com.google.android.gms.common.util.VisibleForTesting;

/* loaded from: classes2.dex */
public final class GooglePlayServicesUtil extends GooglePlayServicesUtilLight {

    @RecentlyNonNull
    public static final String GMS_ERROR_DIALOG = "GooglePlayServicesErrorDialog";

    @RecentlyNonNull
    @Deprecated
    public static final String GOOGLE_PLAY_SERVICES_PACKAGE = "com.google.android.gms";

    @RecentlyNonNull
    @Deprecated
    public static final int GOOGLE_PLAY_SERVICES_VERSION_CODE = GooglePlayServicesUtilLight.GOOGLE_PLAY_SERVICES_VERSION_CODE;

    @RecentlyNonNull
    public static final String GOOGLE_PLAY_STORE_PACKAGE = "com.android.vending";

    private GooglePlayServicesUtil() {
    }

    @RecentlyNullable
    @Deprecated
    public static Dialog getErrorDialog(@RecentlyNonNull int i9, @RecentlyNonNull Activity activity, @RecentlyNonNull int i10) {
        return getErrorDialog(i9, activity, i10, null);
    }

    @RecentlyNonNull
    @Deprecated
    public static PendingIntent getErrorPendingIntent(@RecentlyNonNull int i9, @RecentlyNonNull Context context, @RecentlyNonNull int i10) {
        return GooglePlayServicesUtilLight.getErrorPendingIntent(i9, context, i10);
    }

    @RecentlyNonNull
    @VisibleForTesting
    @Deprecated
    public static String getErrorString(@RecentlyNonNull int i9) {
        return GooglePlayServicesUtilLight.getErrorString(i9);
    }

    @RecentlyNonNull
    public static Context getRemoteContext(@RecentlyNonNull Context context) {
        return GooglePlayServicesUtilLight.getRemoteContext(context);
    }

    @RecentlyNonNull
    public static Resources getRemoteResource(@RecentlyNonNull Context context) {
        return GooglePlayServicesUtilLight.getRemoteResource(context);
    }

    @RecentlyNonNull
    @HideFirstParty
    @Deprecated
    public static int isGooglePlayServicesAvailable(@RecentlyNonNull Context context) {
        return GooglePlayServicesUtilLight.isGooglePlayServicesAvailable(context);
    }

    @RecentlyNonNull
    @Deprecated
    public static boolean isUserRecoverableError(@RecentlyNonNull int i9) {
        return GooglePlayServicesUtilLight.isUserRecoverableError(i9);
    }

    @RecentlyNonNull
    @Deprecated
    public static boolean showErrorDialogFragment(@RecentlyNonNull int i9, @RecentlyNonNull Activity activity, @RecentlyNonNull int i10, DialogInterface.OnCancelListener onCancelListener) {
        return showErrorDialogFragment(i9, activity, null, i10, onCancelListener);
    }

    @Deprecated
    public static void showErrorNotification(@RecentlyNonNull int i9, @RecentlyNonNull Context context) {
        GoogleApiAvailability googleApiAvailability = GoogleApiAvailability.getInstance();
        if (GooglePlayServicesUtilLight.isPlayServicesPossiblyUpdating(context, i9) || GooglePlayServicesUtilLight.isPlayStorePossiblyUpdating(context, i9)) {
            googleApiAvailability.zaa(context);
        } else {
            googleApiAvailability.showErrorNotification(context, i9);
        }
    }

    @RecentlyNullable
    @Deprecated
    public static Dialog getErrorDialog(@RecentlyNonNull int i9, @RecentlyNonNull Activity activity, @RecentlyNonNull int i10, DialogInterface.OnCancelListener onCancelListener) {
        if (GooglePlayServicesUtilLight.isPlayServicesPossiblyUpdating(activity, i9)) {
            i9 = 18;
        }
        return GoogleApiAvailability.getInstance().getErrorDialog(activity, i9, i10, onCancelListener);
    }

    @RecentlyNonNull
    @KeepForSdk
    @Deprecated
    public static int isGooglePlayServicesAvailable(@RecentlyNonNull Context context, @RecentlyNonNull int i9) {
        return GooglePlayServicesUtilLight.isGooglePlayServicesAvailable(context, i9);
    }

    @RecentlyNonNull
    public static boolean showErrorDialogFragment(@RecentlyNonNull int i9, @RecentlyNonNull Activity activity, Fragment fragment, @RecentlyNonNull int i10, DialogInterface.OnCancelListener onCancelListener) {
        if (GooglePlayServicesUtilLight.isPlayServicesPossiblyUpdating(activity, i9)) {
            i9 = 18;
        }
        GoogleApiAvailability googleApiAvailability = GoogleApiAvailability.getInstance();
        if (fragment == null) {
            return googleApiAvailability.showErrorDialogFragment(activity, i9, i10, onCancelListener);
        }
        Dialog dialogZaa = GoogleApiAvailability.zaa(activity, i9, com.google.android.gms.common.internal.zab.zaa(fragment, GoogleApiAvailability.getInstance().getErrorResolutionIntent(activity, i9, "d"), i10), onCancelListener);
        if (dialogZaa == null) {
            return false;
        }
        GoogleApiAvailability.zaa(activity, dialogZaa, GMS_ERROR_DIALOG, onCancelListener);
        return true;
    }

    @RecentlyNonNull
    @Deprecated
    public static boolean showErrorDialogFragment(@RecentlyNonNull int i9, @RecentlyNonNull Activity activity, @RecentlyNonNull int i10) {
        return showErrorDialogFragment(i9, activity, i10, null);
    }
}
