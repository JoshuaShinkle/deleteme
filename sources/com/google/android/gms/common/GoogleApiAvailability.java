package com.google.android.gms.common;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.util.TypedValue;
import android.widget.ProgressBar;
import androidx.annotation.RecentlyNonNull;
import androidx.annotation.RecentlyNullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.google.android.gms.base.C3455R;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.GoogleApiActivity;
import com.google.android.gms.common.api.HasApiKey;
import com.google.android.gms.common.api.internal.ApiKey;
import com.google.android.gms.common.api.internal.GoogleApiManager;
import com.google.android.gms.common.api.internal.LifecycleFragment;
import com.google.android.gms.common.api.internal.zabj;
import com.google.android.gms.common.api.internal.zabl;
import com.google.android.gms.common.api.internal.zabo;
import com.google.android.gms.common.internal.HideFirstParty;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.internal.zac;
import com.google.android.gms.common.util.DeviceProperties;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.internal.base.zap;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import p188s.C6232g;

/* loaded from: classes2.dex */
public class GoogleApiAvailability extends GoogleApiAvailabilityLight {
    public static final String GOOGLE_PLAY_SERVICES_PACKAGE = "com.google.android.gms";
    private String zac;
    private static final Object zaa = new Object();
    private static final GoogleApiAvailability zab = new GoogleApiAvailability();

    @RecentlyNonNull
    public static final int GOOGLE_PLAY_SERVICES_VERSION_CODE = GoogleApiAvailabilityLight.GOOGLE_PLAY_SERVICES_VERSION_CODE;

    @SuppressLint({"HandlerLeak"})
    public class zaa extends zap {
        private final Context zaa;

        public zaa(Context context) {
            super(Looper.myLooper() == null ? Looper.getMainLooper() : Looper.myLooper());
            this.zaa = context.getApplicationContext();
        }

        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            int i9 = message.what;
            if (i9 != 1) {
                StringBuilder sb = new StringBuilder(50);
                sb.append("Don't know how to handle this message: ");
                sb.append(i9);
                Log.w("GoogleApiAvailability", sb.toString());
                return;
            }
            int iIsGooglePlayServicesAvailable = GoogleApiAvailability.this.isGooglePlayServicesAvailable(this.zaa);
            if (GoogleApiAvailability.this.isUserResolvableError(iIsGooglePlayServicesAvailable)) {
                GoogleApiAvailability.this.showErrorNotification(this.zaa, iIsGooglePlayServicesAvailable);
            }
        }
    }

    public static GoogleApiAvailability getInstance() {
        return zab;
    }

    public Task<Void> checkApiAvailability(@RecentlyNonNull HasApiKey<?> hasApiKey, @RecentlyNonNull HasApiKey<?>... hasApiKeyArr) {
        return zaa(hasApiKey, hasApiKeyArr).onSuccessTask(zab.zaa);
    }

    @Override // com.google.android.gms.common.GoogleApiAvailabilityLight
    @RecentlyNonNull
    @ShowFirstParty
    @KeepForSdk
    public int getClientVersion(@RecentlyNonNull Context context) {
        return super.getClientVersion(context);
    }

    @RecentlyNullable
    public Dialog getErrorDialog(@RecentlyNonNull Activity activity, @RecentlyNonNull int i9, @RecentlyNonNull int i10) {
        return getErrorDialog(activity, i9, i10, (DialogInterface.OnCancelListener) null);
    }

    @Override // com.google.android.gms.common.GoogleApiAvailabilityLight
    @RecentlyNullable
    @ShowFirstParty
    @KeepForSdk
    public Intent getErrorResolutionIntent(Context context, @RecentlyNonNull int i9, String str) {
        return super.getErrorResolutionIntent(context, i9, str);
    }

    @Override // com.google.android.gms.common.GoogleApiAvailabilityLight
    @RecentlyNullable
    public PendingIntent getErrorResolutionPendingIntent(@RecentlyNonNull Context context, @RecentlyNonNull int i9, @RecentlyNonNull int i10) {
        return super.getErrorResolutionPendingIntent(context, i9, i10);
    }

    @Override // com.google.android.gms.common.GoogleApiAvailabilityLight
    public final String getErrorString(@RecentlyNonNull int i9) {
        return super.getErrorString(i9);
    }

    @Override // com.google.android.gms.common.GoogleApiAvailabilityLight
    @RecentlyNonNull
    @HideFirstParty
    public int isGooglePlayServicesAvailable(@RecentlyNonNull Context context) {
        return super.isGooglePlayServicesAvailable(context);
    }

    @Override // com.google.android.gms.common.GoogleApiAvailabilityLight
    @RecentlyNonNull
    public final boolean isUserResolvableError(@RecentlyNonNull int i9) {
        return super.isUserResolvableError(i9);
    }

    public Task<Void> makeGooglePlayServicesAvailable(@RecentlyNonNull Activity activity) {
        int i9 = GOOGLE_PLAY_SERVICES_VERSION_CODE;
        Preconditions.checkMainThread("makeGooglePlayServicesAvailable must be called from the main thread");
        int iIsGooglePlayServicesAvailable = isGooglePlayServicesAvailable(activity, i9);
        if (iIsGooglePlayServicesAvailable == 0) {
            return Tasks.forResult(null);
        }
        zabo zaboVarZaa = zabo.zaa(activity);
        zaboVarZaa.zab(new ConnectionResult(iIsGooglePlayServicesAvailable, null), 0);
        return zaboVarZaa.zac();
    }

    @TargetApi(26)
    public void setDefaultNotificationChannelId(@RecentlyNonNull Context context, @RecentlyNonNull String str) {
        if (PlatformVersion.isAtLeastO()) {
            Preconditions.checkNotNull(((NotificationManager) Preconditions.checkNotNull(context.getSystemService("notification"))).getNotificationChannel(str));
        }
        synchronized (zaa) {
            this.zac = str;
        }
    }

    @RecentlyNonNull
    public boolean showErrorDialogFragment(@RecentlyNonNull Activity activity, @RecentlyNonNull int i9, @RecentlyNonNull int i10) {
        return showErrorDialogFragment(activity, i9, i10, null);
    }

    public void showErrorNotification(@RecentlyNonNull Context context, @RecentlyNonNull int i9) {
        zaa(context, i9, (String) null, getErrorResolutionPendingIntent(context, i9, 0, "n"));
    }

    @RecentlyNonNull
    public final boolean zaa(@RecentlyNonNull Activity activity, @RecentlyNonNull LifecycleFragment lifecycleFragment, @RecentlyNonNull int i9, @RecentlyNonNull int i10, DialogInterface.OnCancelListener onCancelListener) {
        Dialog dialogZaa = zaa(activity, i9, com.google.android.gms.common.internal.zab.zaa(lifecycleFragment, getErrorResolutionIntent(activity, i9, "d"), 2), onCancelListener);
        if (dialogZaa == null) {
            return false;
        }
        zaa(activity, dialogZaa, GooglePlayServicesUtil.GMS_ERROR_DIALOG, onCancelListener);
        return true;
    }

    @RecentlyNullable
    public Dialog getErrorDialog(@RecentlyNonNull Fragment fragment, @RecentlyNonNull int i9, @RecentlyNonNull int i10) {
        return getErrorDialog(fragment, i9, i10, (DialogInterface.OnCancelListener) null);
    }

    @RecentlyNullable
    public PendingIntent getErrorResolutionPendingIntent(@RecentlyNonNull Context context, @RecentlyNonNull ConnectionResult connectionResult) {
        return connectionResult.hasResolution() ? connectionResult.getResolution() : getErrorResolutionPendingIntent(context, connectionResult.getErrorCode(), 0);
    }

    @Override // com.google.android.gms.common.GoogleApiAvailabilityLight
    @RecentlyNonNull
    @ShowFirstParty
    @KeepForSdk
    public int isGooglePlayServicesAvailable(@RecentlyNonNull Context context, @RecentlyNonNull int i9) {
        return super.isGooglePlayServicesAvailable(context, i9);
    }

    @RecentlyNonNull
    public boolean showErrorDialogFragment(@RecentlyNonNull Activity activity, @RecentlyNonNull int i9, @RecentlyNonNull int i10, DialogInterface.OnCancelListener onCancelListener) {
        Dialog errorDialog = getErrorDialog(activity, i9, i10, onCancelListener);
        if (errorDialog == null) {
            return false;
        }
        zaa(activity, errorDialog, GooglePlayServicesUtil.GMS_ERROR_DIALOG, onCancelListener);
        return true;
    }

    public Task<Void> checkApiAvailability(@RecentlyNonNull GoogleApi<?> googleApi, @RecentlyNonNull GoogleApi<?>... googleApiArr) {
        return zaa(googleApi, googleApiArr).onSuccessTask(com.google.android.gms.common.zaa.zaa);
    }

    @RecentlyNullable
    public Dialog getErrorDialog(@RecentlyNonNull Activity activity, @RecentlyNonNull int i9, @RecentlyNonNull int i10, DialogInterface.OnCancelListener onCancelListener) {
        return zaa(activity, i9, com.google.android.gms.common.internal.zab.zaa(activity, getErrorResolutionIntent(activity, i9, "d"), i10), onCancelListener);
    }

    public void showErrorNotification(@RecentlyNonNull Context context, @RecentlyNonNull ConnectionResult connectionResult) {
        zaa(context, connectionResult.getErrorCode(), (String) null, getErrorResolutionPendingIntent(context, connectionResult));
    }

    @TargetApi(20)
    private final void zaa(Context context, int i9, String str, PendingIntent pendingIntent) {
        int i10;
        Log.w("GoogleApiAvailability", String.format("GMS core API Availability. ConnectionResult=%s, tag=%s", Integer.valueOf(i9), null), new IllegalArgumentException());
        if (i9 == 18) {
            zaa(context);
            return;
        }
        if (pendingIntent == null) {
            if (i9 == 6) {
                Log.w("GoogleApiAvailability", "Missing resolution for ConnectionResult.RESOLUTION_REQUIRED. Call GoogleApiAvailability#showErrorNotification(Context, ConnectionResult) instead.");
                return;
            }
            return;
        }
        String strZab = zac.zab(context, i9);
        String strZad = zac.zad(context, i9);
        Resources resources = context.getResources();
        NotificationManager notificationManager = (NotificationManager) Preconditions.checkNotNull(context.getSystemService("notification"));
        C6232g.e eVarM23860z = new C6232g.e(context).m23853s(true).m23840f(true).m23846l(strZab).m23860z(new C6232g.c().m23828g(strZad));
        if (DeviceProperties.isWearable(context)) {
            Preconditions.checkState(PlatformVersion.isAtLeastKitKatWatch());
            eVarM23860z.m23858x(context.getApplicationInfo().icon).m23856v(2);
            if (DeviceProperties.isWearableWithoutPlayStore(context)) {
                eVarM23860z.m23836a(C3455R.drawable.common_full_open_on_phone, resources.getString(C3455R.string.common_open_on_phone), pendingIntent);
            } else {
                eVarM23860z.m23844j(pendingIntent);
            }
        } else {
            eVarM23860z.m23858x(android.R.drawable.stat_sys_warning).m23832A(resources.getString(C3455R.string.common_google_play_services_notification_ticker)).m23835D(System.currentTimeMillis()).m23844j(pendingIntent).m23845k(strZad);
        }
        if (PlatformVersion.isAtLeastO()) {
            Preconditions.checkState(PlatformVersion.isAtLeastO());
            String strZaa = zaa();
            if (strZaa == null) {
                strZaa = "com.google.android.gms.availability";
                NotificationChannel notificationChannel = notificationManager.getNotificationChannel("com.google.android.gms.availability");
                String strZaa2 = zac.zaa(context);
                if (notificationChannel == null) {
                    notificationManager.createNotificationChannel(new NotificationChannel("com.google.android.gms.availability", strZaa2, 4));
                } else if (!strZaa2.contentEquals(notificationChannel.getName())) {
                    notificationChannel.setName(strZaa2);
                    notificationManager.createNotificationChannel(notificationChannel);
                }
            }
            eVarM23860z.m23842h(strZaa);
        }
        Notification notificationM23837b = eVarM23860z.m23837b();
        if (i9 == 1 || i9 == 2 || i9 == 3) {
            GooglePlayServicesUtilLight.sCanceledAvailabilityNotification.set(false);
            i10 = 10436;
        } else {
            i10 = 39789;
        }
        notificationManager.notify(i10, notificationM23837b);
    }

    @RecentlyNullable
    public Dialog getErrorDialog(@RecentlyNonNull Fragment fragment, @RecentlyNonNull int i9, @RecentlyNonNull int i10, DialogInterface.OnCancelListener onCancelListener) {
        return zaa(fragment.requireContext(), i9, com.google.android.gms.common.internal.zab.zaa(fragment, getErrorResolutionIntent(fragment.requireContext(), i9, "d"), i10), onCancelListener);
    }

    @RecentlyNonNull
    public final boolean zaa(@RecentlyNonNull Context context, @RecentlyNonNull ConnectionResult connectionResult, @RecentlyNonNull int i9) {
        PendingIntent errorResolutionPendingIntent = getErrorResolutionPendingIntent(context, connectionResult);
        if (errorResolutionPendingIntent == null) {
            return false;
        }
        zaa(context, connectionResult.getErrorCode(), (String) null, GoogleApiActivity.zaa(context, errorResolutionPendingIntent, i9));
        return true;
    }

    @RecentlyNonNull
    public static Dialog zaa(@RecentlyNonNull Activity activity, @RecentlyNonNull DialogInterface.OnCancelListener onCancelListener) {
        ProgressBar progressBar = new ProgressBar(activity, null, android.R.attr.progressBarStyleLarge);
        progressBar.setIndeterminate(true);
        progressBar.setVisibility(0);
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setView(progressBar);
        builder.setMessage(zac.zac(activity, 18));
        builder.setPositiveButton("", (DialogInterface.OnClickListener) null);
        AlertDialog alertDialogCreate = builder.create();
        zaa(activity, alertDialogCreate, "GooglePlayServicesUpdatingDialog", onCancelListener);
        return alertDialogCreate;
    }

    public final zabj zaa(Context context, zabl zablVar) {
        IntentFilter intentFilter = new IntentFilter("android.intent.action.PACKAGE_ADDED");
        intentFilter.addDataScheme("package");
        zabj zabjVar = new zabj(zablVar);
        context.registerReceiver(zabjVar, intentFilter);
        zabjVar.zaa(context);
        if (isUninstalledAppPossiblyUpdating(context, "com.google.android.gms")) {
            return zabjVar;
        }
        zablVar.zaa();
        zabjVar.zaa();
        return null;
    }

    private static Task<Map<ApiKey<?>, String>> zaa(HasApiKey<?> hasApiKey, HasApiKey<?>... hasApiKeyArr) {
        Preconditions.checkNotNull(hasApiKey, "Requested API must not be null.");
        for (HasApiKey<?> hasApiKey2 : hasApiKeyArr) {
            Preconditions.checkNotNull(hasApiKey2, "Requested API must not be null.");
        }
        ArrayList arrayList = new ArrayList(hasApiKeyArr.length + 1);
        arrayList.add(hasApiKey);
        arrayList.addAll(Arrays.asList(hasApiKeyArr));
        return GoogleApiManager.zaa().zaa(arrayList);
    }

    private final String zaa() {
        String str;
        synchronized (zaa) {
            str = this.zac;
        }
        return str;
    }

    public static Dialog zaa(Context context, int i9, com.google.android.gms.common.internal.zab zabVar, DialogInterface.OnCancelListener onCancelListener) {
        if (i9 == 0) {
            return null;
        }
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(android.R.attr.alertDialogTheme, typedValue, true);
        AlertDialog.Builder builder = "Theme.Dialog.Alert".equals(context.getResources().getResourceEntryName(typedValue.resourceId)) ? new AlertDialog.Builder(context, 5) : null;
        if (builder == null) {
            builder = new AlertDialog.Builder(context);
        }
        builder.setMessage(zac.zac(context, i9));
        if (onCancelListener != null) {
            builder.setOnCancelListener(onCancelListener);
        }
        String strZae = zac.zae(context, i9);
        if (strZae != null) {
            builder.setPositiveButton(strZae, zabVar);
        }
        String strZaa = zac.zaa(context, i9);
        if (strZaa != null) {
            builder.setTitle(strZaa);
        }
        Log.w("GoogleApiAvailability", String.format("Creating dialog for Google Play services availability issue. ConnectionResult=%s", Integer.valueOf(i9)), new IllegalArgumentException());
        return builder.create();
    }

    public static void zaa(Activity activity, Dialog dialog, String str, DialogInterface.OnCancelListener onCancelListener) {
        if (activity instanceof FragmentActivity) {
            SupportErrorDialogFragment.newInstance(dialog, onCancelListener).show(((FragmentActivity) activity).getSupportFragmentManager(), str);
        } else {
            ErrorDialogFragment.newInstance(dialog, onCancelListener).show(activity.getFragmentManager(), str);
        }
    }

    public final void zaa(Context context) {
        new zaa(context).sendEmptyMessageDelayed(1, 120000L);
    }
}
