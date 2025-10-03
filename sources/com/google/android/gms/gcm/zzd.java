package com.google.android.gms.gcm;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.firebase.messaging.CommonNotificationBuilder;
import com.google.firebase.messaging.Constants;
import java.util.MissingFormatArgumentException;
import java.util.concurrent.atomic.AtomicInteger;
import net.sqlcipher.database.SQLiteDatabase;
import org.json.JSONArray;
import org.json.JSONException;
import p188s.C6232g;

/* loaded from: classes2.dex */
final class zzd {
    static zzd zzk;
    private final Context zzl;
    private String zzm;
    private final AtomicInteger zzn = new AtomicInteger((int) SystemClock.elapsedRealtime());

    private zzd(Context context) {
        this.zzl = context.getApplicationContext();
    }

    public static synchronized zzd zzd(Context context) {
        if (zzk == null) {
            zzk = new zzd(context);
        }
        return zzk;
    }

    private final Bundle zzf() throws PackageManager.NameNotFoundException {
        ApplicationInfo applicationInfo;
        Bundle bundle;
        try {
            applicationInfo = this.zzl.getPackageManager().getApplicationInfo(this.zzl.getPackageName(), 128);
        } catch (PackageManager.NameNotFoundException unused) {
            applicationInfo = null;
        }
        return (applicationInfo == null || (bundle = applicationInfo.metaData) == null) ? Bundle.EMPTY : bundle;
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0081  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0197  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x01c1  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x01c7  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x0220  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x0229  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x023e  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x0247  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x024c  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x0251  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x0264  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x0279  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final boolean zze(Bundle bundle) {
        int i9;
        int identifier;
        Uri defaultUri;
        Intent launchIntentForPackage;
        PendingIntent activity;
        String strZzd;
        String strZzd2;
        CharSequence charSequenceZze = zze(bundle, Constants.MessageNotificationKeys.TITLE);
        if (TextUtils.isEmpty(charSequenceZze)) {
            charSequenceZze = this.zzl.getApplicationInfo().loadLabel(this.zzl.getPackageManager());
        }
        String strZze = zze(bundle, Constants.MessageNotificationKeys.BODY);
        String strZzd3 = zzd(bundle, Constants.MessageNotificationKeys.ICON);
        if (TextUtils.isEmpty(strZzd3)) {
            i9 = this.zzl.getApplicationInfo().icon;
            if (i9 == 0) {
                i9 = android.R.drawable.sym_def_app_icon;
            }
            identifier = i9;
        } else {
            Resources resources = this.zzl.getResources();
            identifier = resources.getIdentifier(strZzd3, "drawable", this.zzl.getPackageName());
            if (identifier == 0 && (identifier = resources.getIdentifier(strZzd3, "mipmap", this.zzl.getPackageName())) == 0) {
                StringBuilder sb = new StringBuilder(String.valueOf(strZzd3).length() + 57);
                sb.append("Icon resource ");
                sb.append(strZzd3);
                sb.append(" not found. Notification will use app icon.");
                Log.w("GcmNotification", sb.toString());
                i9 = this.zzl.getApplicationInfo().icon;
                if (i9 == 0) {
                }
                identifier = i9;
            }
        }
        String strZzd4 = zzd(bundle, Constants.MessageNotificationKeys.COLOR);
        String strZzd5 = zzd(bundle, Constants.MessageNotificationKeys.SOUND_2);
        String str = null;
        if (TextUtils.isEmpty(strZzd5)) {
            defaultUri = null;
        } else if ("default".equals(strZzd5) || this.zzl.getResources().getIdentifier(strZzd5, "raw", this.zzl.getPackageName()) == 0) {
            defaultUri = RingtoneManager.getDefaultUri(2);
        } else {
            String packageName = this.zzl.getPackageName();
            StringBuilder sb2 = new StringBuilder(String.valueOf(packageName).length() + 24 + String.valueOf(strZzd5).length());
            sb2.append("android.resource://");
            sb2.append(packageName);
            sb2.append("/raw/");
            sb2.append(strZzd5);
            defaultUri = Uri.parse(sb2.toString());
        }
        String strZzd6 = zzd(bundle, Constants.MessageNotificationKeys.CLICK_ACTION);
        if (TextUtils.isEmpty(strZzd6)) {
            launchIntentForPackage = this.zzl.getPackageManager().getLaunchIntentForPackage(this.zzl.getPackageName());
            if (launchIntentForPackage == null) {
                Log.w("GcmNotification", "No activity found to launch app");
                activity = null;
            }
            strZzd = zzd(bundle, Constants.MessageNotificationKeys.CHANNEL);
            if (PlatformVersion.isAtLeastO() && this.zzl.getApplicationInfo().targetSdkVersion >= 26) {
                NotificationManager notificationManager = (NotificationManager) this.zzl.getSystemService(NotificationManager.class);
                if (TextUtils.isEmpty(strZzd)) {
                    strZzd = this.zzm;
                    if (strZzd == null) {
                        str = strZzd;
                    } else {
                        String string = zzf().getString("com.google.android.gms.gcm.default_notification_channel_id");
                        this.zzm = string;
                        if (TextUtils.isEmpty(string)) {
                            Log.w("GcmNotification", "Missing Default Notification Channel metadata in AndroidManifest. Default value will be used.");
                        } else if (notificationManager.getNotificationChannel(this.zzm) != null) {
                            str = this.zzm;
                        } else {
                            Log.w("GcmNotification", "Notification Channel set in AndroidManifest.xml has not been created by the app. Default value will be used.");
                        }
                        strZzd = CommonNotificationBuilder.FCM_FALLBACK_NOTIFICATION_CHANNEL;
                        if (notificationManager.getNotificationChannel(CommonNotificationBuilder.FCM_FALLBACK_NOTIFICATION_CHANNEL) == null) {
                            notificationManager.createNotificationChannel(new NotificationChannel(CommonNotificationBuilder.FCM_FALLBACK_NOTIFICATION_CHANNEL, this.zzl.getString(C3465R.string.gcm_fallback_notification_channel_label), 3));
                        }
                        this.zzm = CommonNotificationBuilder.FCM_FALLBACK_NOTIFICATION_CHANNEL;
                        str = strZzd;
                    }
                } else {
                    if (notificationManager.getNotificationChannel(strZzd) == null) {
                        StringBuilder sb3 = new StringBuilder(String.valueOf(strZzd).length() + 122);
                        sb3.append("Notification Channel requested (");
                        sb3.append(strZzd);
                        sb3.append(") has not been created by the app. Manifest configuration, or default, value will be used.");
                        Log.w("GcmNotification", sb3.toString());
                        strZzd = this.zzm;
                        if (strZzd == null) {
                        }
                    }
                    str = strZzd;
                }
            }
            C6232g.e eVarM23858x = new C6232g.e(this.zzl).m23840f(true).m23858x(identifier);
            if (!TextUtils.isEmpty(charSequenceZze)) {
                eVarM23858x.m23846l(charSequenceZze);
            }
            if (!TextUtils.isEmpty(strZze)) {
                eVarM23858x.m23845k(strZze);
                eVarM23858x.m23860z(new C6232g.c().m23828g(strZze));
            }
            if (!TextUtils.isEmpty(strZzd4)) {
                eVarM23858x.m23843i(Color.parseColor(strZzd4));
            }
            if (defaultUri != null) {
                eVarM23858x.m23859y(defaultUri);
            }
            if (activity != null) {
                eVarM23858x.m23844j(activity);
            }
            if (str != null) {
                eVarM23858x.m23842h(str);
            }
            Notification notificationM23837b = eVarM23858x.m23837b();
            strZzd2 = zzd(bundle, Constants.MessageNotificationKeys.TAG);
            if (Log.isLoggable("GcmNotification", 3)) {
                Log.d("GcmNotification", "Showing notification");
            }
            NotificationManager notificationManager2 = (NotificationManager) this.zzl.getSystemService("notification");
            if (TextUtils.isEmpty(strZzd2)) {
                long jUptimeMillis = SystemClock.uptimeMillis();
                StringBuilder sb4 = new StringBuilder(37);
                sb4.append("GCM-Notification:");
                sb4.append(jUptimeMillis);
                strZzd2 = sb4.toString();
            }
            notificationManager2.notify(strZzd2, 0, notificationM23837b);
            return true;
        }
        launchIntentForPackage = new Intent(strZzd6);
        launchIntentForPackage.setPackage(this.zzl.getPackageName());
        launchIntentForPackage.setFlags(SQLiteDatabase.CREATE_IF_NECESSARY);
        Bundle bundle2 = new Bundle(bundle);
        GcmListenerService.zzd(bundle2);
        launchIntentForPackage.putExtras(bundle2);
        for (String str2 : bundle2.keySet()) {
            if (str2.startsWith(Constants.MessageNotificationKeys.NOTIFICATION_PREFIX) || str2.startsWith(Constants.MessageNotificationKeys.NOTIFICATION_PREFIX_OLD)) {
                launchIntentForPackage.removeExtra(str2);
            }
        }
        activity = PendingIntent.getActivity(this.zzl, this.zzn.getAndIncrement(), launchIntentForPackage, 1073741824);
        strZzd = zzd(bundle, Constants.MessageNotificationKeys.CHANNEL);
        if (PlatformVersion.isAtLeastO()) {
            NotificationManager notificationManager3 = (NotificationManager) this.zzl.getSystemService(NotificationManager.class);
            if (TextUtils.isEmpty(strZzd)) {
            }
        }
        C6232g.e eVarM23858x2 = new C6232g.e(this.zzl).m23840f(true).m23858x(identifier);
        if (!TextUtils.isEmpty(charSequenceZze)) {
        }
        if (!TextUtils.isEmpty(strZze)) {
        }
        if (!TextUtils.isEmpty(strZzd4)) {
        }
        if (defaultUri != null) {
        }
        if (activity != null) {
        }
        if (str != null) {
        }
        Notification notificationM23837b2 = eVarM23858x2.m23837b();
        strZzd2 = zzd(bundle, Constants.MessageNotificationKeys.TAG);
        if (Log.isLoggable("GcmNotification", 3)) {
        }
        NotificationManager notificationManager22 = (NotificationManager) this.zzl.getSystemService("notification");
        if (TextUtils.isEmpty(strZzd2)) {
        }
        notificationManager22.notify(strZzd2, 0, notificationM23837b2);
        return true;
    }

    public static String zzd(Bundle bundle, String str) {
        String string = bundle.getString(str);
        return string == null ? bundle.getString(str.replace(Constants.MessageNotificationKeys.NOTIFICATION_PREFIX, Constants.MessageNotificationKeys.NOTIFICATION_PREFIX_OLD)) : string;
    }

    private final String zze(Bundle bundle, String str) {
        String strZzd = zzd(bundle, str);
        if (!TextUtils.isEmpty(strZzd)) {
            return strZzd;
        }
        String strValueOf = String.valueOf(str);
        String strZzd2 = zzd(bundle, Constants.MessageNotificationKeys.TEXT_RESOURCE_SUFFIX.length() != 0 ? strValueOf.concat(Constants.MessageNotificationKeys.TEXT_RESOURCE_SUFFIX) : new String(strValueOf));
        if (TextUtils.isEmpty(strZzd2)) {
            return null;
        }
        Resources resources = this.zzl.getResources();
        int identifier = resources.getIdentifier(strZzd2, "string", this.zzl.getPackageName());
        if (identifier == 0) {
            String strValueOf2 = String.valueOf(str);
            String strSubstring = (Constants.MessageNotificationKeys.TEXT_RESOURCE_SUFFIX.length() != 0 ? strValueOf2.concat(Constants.MessageNotificationKeys.TEXT_RESOURCE_SUFFIX) : new String(strValueOf2)).substring(6);
            StringBuilder sb = new StringBuilder(String.valueOf(strSubstring).length() + 49 + String.valueOf(strZzd2).length());
            sb.append(strSubstring);
            sb.append(" resource not found: ");
            sb.append(strZzd2);
            sb.append(" Default value will be used.");
            Log.w("GcmNotification", sb.toString());
            return null;
        }
        String strValueOf3 = String.valueOf(str);
        String strZzd3 = zzd(bundle, Constants.MessageNotificationKeys.TEXT_ARGS_SUFFIX.length() != 0 ? strValueOf3.concat(Constants.MessageNotificationKeys.TEXT_ARGS_SUFFIX) : new String(strValueOf3));
        if (TextUtils.isEmpty(strZzd3)) {
            return resources.getString(identifier);
        }
        try {
            JSONArray jSONArray = new JSONArray(strZzd3);
            int length = jSONArray.length();
            Object[] objArr = new String[length];
            for (int i9 = 0; i9 < length; i9++) {
                objArr[i9] = jSONArray.opt(i9);
            }
            return resources.getString(identifier, objArr);
        } catch (MissingFormatArgumentException e9) {
            StringBuilder sb2 = new StringBuilder(String.valueOf(strZzd2).length() + 58 + String.valueOf(strZzd3).length());
            sb2.append("Missing format argument for ");
            sb2.append(strZzd2);
            sb2.append(": ");
            sb2.append(strZzd3);
            sb2.append(" Default value will be used.");
            Log.w("GcmNotification", sb2.toString(), e9);
            return null;
        } catch (JSONException unused) {
            String strValueOf4 = String.valueOf(str);
            String strSubstring2 = (Constants.MessageNotificationKeys.TEXT_ARGS_SUFFIX.length() != 0 ? strValueOf4.concat(Constants.MessageNotificationKeys.TEXT_ARGS_SUFFIX) : new String(strValueOf4)).substring(6);
            StringBuilder sb3 = new StringBuilder(String.valueOf(strSubstring2).length() + 41 + String.valueOf(strZzd3).length());
            sb3.append("Malformed ");
            sb3.append(strSubstring2);
            sb3.append(": ");
            sb3.append(strZzd3);
            sb3.append("  Default value will be used.");
            Log.w("GcmNotification", sb3.toString());
            return null;
        }
    }
}
