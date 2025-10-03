package com.google.firebase.messaging;

import android.annotation.TargetApi;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.AdaptiveIconDrawable;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import com.google.firebase.messaging.Constants;
import java.util.concurrent.atomic.AtomicInteger;
import net.sqlcipher.database.SQLiteDatabase;
import org.json.JSONException;
import p188s.C6232g;
import p197t.C6273a;

/* loaded from: classes2.dex */
public final class CommonNotificationBuilder {
    public static final String FCM_FALLBACK_NOTIFICATION_CHANNEL = "fcm_fallback_notification_channel";
    public static final String FCM_FALLBACK_NOTIFICATION_CHANNEL_LABEL = "fcm_fallback_notification_channel_label";
    public static final String METADATA_DEFAULT_CHANNEL_ID = "com.google.firebase.messaging.default_notification_channel_id";
    public static final String METADATA_DEFAULT_COLOR = "com.google.firebase.messaging.default_notification_color";
    public static final String METADATA_DEFAULT_ICON = "com.google.firebase.messaging.default_notification_icon";
    private static final AtomicInteger requestCodeProvider = new AtomicInteger((int) SystemClock.elapsedRealtime());

    public static class DisplayNotificationInfo {

        /* renamed from: id */
        public final int f15584id;
        public final C6232g.e notificationBuilder;
        public final String tag;

        public DisplayNotificationInfo(C6232g.e eVar, String str, int i9) {
            this.notificationBuilder = eVar;
            this.tag = str;
            this.f15584id = i9;
        }
    }

    private CommonNotificationBuilder() {
    }

    private static PendingIntent createContentIntent(Context context, NotificationParams notificationParams, String str, PackageManager packageManager) {
        Intent intentCreateTargetIntent = createTargetIntent(str, notificationParams, packageManager);
        if (intentCreateTargetIntent == null) {
            return null;
        }
        intentCreateTargetIntent.addFlags(67108864);
        intentCreateTargetIntent.putExtras(notificationParams.paramsWithReservedKeysRemoved());
        PendingIntent activity = PendingIntent.getActivity(context, generatePendingIntentRequestCode(), intentCreateTargetIntent, 1073741824);
        return shouldUploadMetrics(notificationParams) ? wrapContentIntent(context, notificationParams, activity) : activity;
    }

    private static PendingIntent createDeleteIntent(Context context, NotificationParams notificationParams) {
        if (shouldUploadMetrics(notificationParams)) {
            return createMessagingPendingIntent(context, new Intent(Constants.IntentActionKeys.NOTIFICATION_DISMISS).putExtras(notificationParams.paramsForAnalyticsIntent()));
        }
        return null;
    }

    private static PendingIntent createMessagingPendingIntent(Context context, Intent intent) {
        return PendingIntent.getBroadcast(context, generatePendingIntentRequestCode(), new Intent("com.google.firebase.MESSAGING_EVENT").setComponent(new ComponentName(context, "com.google.firebase.iid.FirebaseInstanceIdReceiver")).putExtra(Constants.IntentKeys.WRAPPED_INTENT, intent), 1073741824);
    }

    public static DisplayNotificationInfo createNotificationInfo(Context context, NotificationParams notificationParams) throws PackageManager.NameNotFoundException {
        Bundle manifestMetadata = getManifestMetadata(context.getPackageManager(), context.getPackageName());
        return createNotificationInfo(context, context.getPackageName(), notificationParams, getOrCreateChannel(context, notificationParams.getNotificationChannelId(), manifestMetadata), context.getResources(), context.getPackageManager(), manifestMetadata);
    }

    private static Intent createTargetIntent(String str, NotificationParams notificationParams, PackageManager packageManager) {
        String string = notificationParams.getString(Constants.MessageNotificationKeys.CLICK_ACTION);
        if (!TextUtils.isEmpty(string)) {
            Intent intent = new Intent(string);
            intent.setPackage(str);
            intent.setFlags(SQLiteDatabase.CREATE_IF_NECESSARY);
            return intent;
        }
        Uri link = notificationParams.getLink();
        if (link != null) {
            Intent intent2 = new Intent("android.intent.action.VIEW");
            intent2.setPackage(str);
            intent2.setData(link);
            return intent2;
        }
        Intent launchIntentForPackage = packageManager.getLaunchIntentForPackage(str);
        if (launchIntentForPackage == null) {
            Log.w(Constants.TAG, "No activity found to launch app");
        }
        return launchIntentForPackage;
    }

    private static int generatePendingIntentRequestCode() {
        return requestCodeProvider.incrementAndGet();
    }

    private static Integer getColor(Context context, String str, Bundle bundle) {
        if (!TextUtils.isEmpty(str)) {
            try {
                return Integer.valueOf(Color.parseColor(str));
            } catch (IllegalArgumentException unused) {
                StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 56);
                sb.append("Color is invalid: ");
                sb.append(str);
                sb.append(". Notification will use default color.");
                Log.w(Constants.TAG, sb.toString());
            }
        }
        int i9 = bundle.getInt(METADATA_DEFAULT_COLOR, 0);
        if (i9 == 0) {
            return null;
        }
        try {
            return Integer.valueOf(C6273a.m24024c(context, i9));
        } catch (Resources.NotFoundException unused2) {
            Log.w(Constants.TAG, "Cannot find the color resource referenced in AndroidManifest.");
            return null;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v2, types: [int] */
    /* JADX WARN: Type inference failed for: r0v6 */
    /* JADX WARN: Type inference failed for: r0v7 */
    private static int getConsolidatedDefaults(NotificationParams notificationParams) {
        boolean z8 = notificationParams.getBoolean(Constants.MessageNotificationKeys.DEFAULT_SOUND);
        ?? r02 = z8;
        if (notificationParams.getBoolean(Constants.MessageNotificationKeys.DEFAULT_VIBRATE_TIMINGS)) {
            r02 = (z8 ? 1 : 0) | 2;
        }
        return notificationParams.getBoolean(Constants.MessageNotificationKeys.DEFAULT_LIGHT_SETTINGS) ? r02 | 4 : r02;
    }

    private static Bundle getManifestMetadata(PackageManager packageManager, String str) throws PackageManager.NameNotFoundException {
        try {
            ApplicationInfo applicationInfo = packageManager.getApplicationInfo(str, 128);
            if (applicationInfo != null) {
                Bundle bundle = applicationInfo.metaData;
                if (bundle != null) {
                    return bundle;
                }
            }
        } catch (PackageManager.NameNotFoundException e9) {
            String strValueOf = String.valueOf(e9);
            StringBuilder sb = new StringBuilder(strValueOf.length() + 35);
            sb.append("Couldn't get own application info: ");
            sb.append(strValueOf);
            Log.w(Constants.TAG, sb.toString());
        }
        return Bundle.EMPTY;
    }

    @TargetApi(26)
    private static String getOrCreateChannel(Context context, String str, Bundle bundle) {
        try {
            if (context.getPackageManager().getApplicationInfo(context.getPackageName(), 0).targetSdkVersion < 26) {
                return null;
            }
            NotificationManager notificationManager = (NotificationManager) context.getSystemService(NotificationManager.class);
            if (!TextUtils.isEmpty(str)) {
                if (notificationManager.getNotificationChannel(str) != null) {
                    return str;
                }
                StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 122);
                sb.append("Notification Channel requested (");
                sb.append(str);
                sb.append(") has not been created by the app. Manifest configuration, or default, value will be used.");
                Log.w(Constants.TAG, sb.toString());
            }
            String string = bundle.getString(METADATA_DEFAULT_CHANNEL_ID);
            if (TextUtils.isEmpty(string)) {
                Log.w(Constants.TAG, "Missing Default Notification Channel metadata in AndroidManifest. Default value will be used.");
            } else {
                if (notificationManager.getNotificationChannel(string) != null) {
                    return string;
                }
                Log.w(Constants.TAG, "Notification Channel set in AndroidManifest.xml has not been created by the app. Default value will be used.");
            }
            if (notificationManager.getNotificationChannel(FCM_FALLBACK_NOTIFICATION_CHANNEL) == null) {
                notificationManager.createNotificationChannel(new NotificationChannel(FCM_FALLBACK_NOTIFICATION_CHANNEL, context.getString(context.getResources().getIdentifier(FCM_FALLBACK_NOTIFICATION_CHANNEL_LABEL, "string", context.getPackageName())), 3));
            }
            return FCM_FALLBACK_NOTIFICATION_CHANNEL;
        } catch (PackageManager.NameNotFoundException unused) {
            return null;
        }
    }

    private static int getSmallIcon(PackageManager packageManager, Resources resources, String str, String str2, Bundle bundle) {
        if (!TextUtils.isEmpty(str2)) {
            int identifier = resources.getIdentifier(str2, "drawable", str);
            if (identifier != 0 && isValidIcon(resources, identifier)) {
                return identifier;
            }
            int identifier2 = resources.getIdentifier(str2, "mipmap", str);
            if (identifier2 != 0 && isValidIcon(resources, identifier2)) {
                return identifier2;
            }
            StringBuilder sb = new StringBuilder(String.valueOf(str2).length() + 61);
            sb.append("Icon resource ");
            sb.append(str2);
            sb.append(" not found. Notification will use default icon.");
            Log.w(Constants.TAG, sb.toString());
        }
        int i9 = bundle.getInt(METADATA_DEFAULT_ICON, 0);
        if (i9 == 0 || !isValidIcon(resources, i9)) {
            try {
                i9 = packageManager.getApplicationInfo(str, 0).icon;
            } catch (PackageManager.NameNotFoundException e9) {
                String strValueOf = String.valueOf(e9);
                StringBuilder sb2 = new StringBuilder(strValueOf.length() + 35);
                sb2.append("Couldn't get own application info: ");
                sb2.append(strValueOf);
                Log.w(Constants.TAG, sb2.toString());
            }
        }
        return (i9 == 0 || !isValidIcon(resources, i9)) ? android.R.drawable.sym_def_app_icon : i9;
    }

    private static Uri getSound(String str, NotificationParams notificationParams, Resources resources) {
        String soundResourceName = notificationParams.getSoundResourceName();
        if (TextUtils.isEmpty(soundResourceName)) {
            return null;
        }
        if ("default".equals(soundResourceName) || resources.getIdentifier(soundResourceName, "raw", str) == 0) {
            return RingtoneManager.getDefaultUri(2);
        }
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 24 + String.valueOf(soundResourceName).length());
        sb.append("android.resource://");
        sb.append(str);
        sb.append("/raw/");
        sb.append(soundResourceName);
        return Uri.parse(sb.toString());
    }

    private static String getTag(NotificationParams notificationParams) {
        String string = notificationParams.getString(Constants.MessageNotificationKeys.TAG);
        if (!TextUtils.isEmpty(string)) {
            return string;
        }
        long jUptimeMillis = SystemClock.uptimeMillis();
        StringBuilder sb = new StringBuilder(37);
        sb.append("FCM-Notification:");
        sb.append(jUptimeMillis);
        return sb.toString();
    }

    @TargetApi(26)
    private static boolean isValidIcon(Resources resources, int i9) {
        if (Build.VERSION.SDK_INT != 26) {
            return true;
        }
        try {
            if (!(resources.getDrawable(i9, null) instanceof AdaptiveIconDrawable)) {
                return true;
            }
            StringBuilder sb = new StringBuilder(77);
            sb.append("Adaptive icons cannot be used in notifications. Ignoring icon id: ");
            sb.append(i9);
            Log.e(Constants.TAG, sb.toString());
            return false;
        } catch (Resources.NotFoundException unused) {
            StringBuilder sb2 = new StringBuilder(66);
            sb2.append("Couldn't find resource ");
            sb2.append(i9);
            sb2.append(", treating it as an invalid icon");
            Log.e(Constants.TAG, sb2.toString());
            return false;
        }
    }

    public static boolean shouldUploadMetrics(NotificationParams notificationParams) {
        return notificationParams.getBoolean(Constants.AnalyticsKeys.ENABLED);
    }

    private static PendingIntent wrapContentIntent(Context context, NotificationParams notificationParams, PendingIntent pendingIntent) {
        return createMessagingPendingIntent(context, new Intent(Constants.IntentActionKeys.NOTIFICATION_OPEN).putExtras(notificationParams.paramsForAnalyticsIntent()).putExtra(Constants.IntentKeys.PENDING_INTENT, pendingIntent));
    }

    public static DisplayNotificationInfo createNotificationInfo(Context context, String str, NotificationParams notificationParams, String str2, Resources resources, PackageManager packageManager, Bundle bundle) throws JSONException {
        C6232g.e eVar = new C6232g.e(context, str2);
        String possiblyLocalizedString = notificationParams.getPossiblyLocalizedString(resources, str, Constants.MessageNotificationKeys.TITLE);
        if (!TextUtils.isEmpty(possiblyLocalizedString)) {
            eVar.m23846l(possiblyLocalizedString);
        }
        String possiblyLocalizedString2 = notificationParams.getPossiblyLocalizedString(resources, str, Constants.MessageNotificationKeys.BODY);
        if (!TextUtils.isEmpty(possiblyLocalizedString2)) {
            eVar.m23845k(possiblyLocalizedString2);
            eVar.m23860z(new C6232g.c().m23828g(possiblyLocalizedString2));
        }
        eVar.m23858x(getSmallIcon(packageManager, resources, str, notificationParams.getString(Constants.MessageNotificationKeys.ICON), bundle));
        Uri sound = getSound(str, notificationParams, resources);
        if (sound != null) {
            eVar.m23859y(sound);
        }
        eVar.m23844j(createContentIntent(context, notificationParams, str, packageManager));
        PendingIntent pendingIntentCreateDeleteIntent = createDeleteIntent(context, notificationParams);
        if (pendingIntentCreateDeleteIntent != null) {
            eVar.m23848n(pendingIntentCreateDeleteIntent);
        }
        Integer color = getColor(context, notificationParams.getString(Constants.MessageNotificationKeys.COLOR), bundle);
        if (color != null) {
            eVar.m23843i(color.intValue());
        }
        eVar.m23840f(!notificationParams.getBoolean(Constants.MessageNotificationKeys.STICKY));
        eVar.m23853s(notificationParams.getBoolean(Constants.MessageNotificationKeys.LOCAL_ONLY));
        String string = notificationParams.getString(Constants.MessageNotificationKeys.TICKER);
        if (string != null) {
            eVar.m23832A(string);
        }
        Integer notificationPriority = notificationParams.getNotificationPriority();
        if (notificationPriority != null) {
            eVar.m23856v(notificationPriority.intValue());
        }
        Integer visibility = notificationParams.getVisibility();
        if (visibility != null) {
            eVar.m23834C(visibility.intValue());
        }
        Integer notificationCount = notificationParams.getNotificationCount();
        if (notificationCount != null) {
            eVar.m23854t(notificationCount.intValue());
        }
        Long l9 = notificationParams.getLong(Constants.MessageNotificationKeys.EVENT_TIME);
        if (l9 != null) {
            eVar.m23857w(true);
            eVar.m23835D(l9.longValue());
        }
        long[] vibrateTimings = notificationParams.getVibrateTimings();
        if (vibrateTimings != null) {
            eVar.m23833B(vibrateTimings);
        }
        int[] lightSettings = notificationParams.getLightSettings();
        if (lightSettings != null) {
            eVar.m23852r(lightSettings[0], lightSettings[1], lightSettings[2]);
        }
        eVar.m23847m(getConsolidatedDefaults(notificationParams));
        return new DisplayNotificationInfo(eVar, getTag(notificationParams), 0);
    }
}
