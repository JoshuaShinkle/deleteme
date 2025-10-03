package com.google.firebase.messaging;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.datatransport.Event;
import com.google.android.datatransport.Transport;
import com.google.firebase.FirebaseApp;
import com.google.firebase.analytics.connector.AnalyticsConnector;
import com.google.firebase.encoders.DataEncoder;
import com.google.firebase.encoders.EncodingException;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.json.JsonDataEncoderBuilder;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.Constants;
import com.google.firebase.messaging.FirelogAnalyticsEvent;

/* loaded from: classes2.dex */
public class MessagingAnalytics {
    private static final DataEncoder dataEncoder = new JsonDataEncoderBuilder().registerEncoder(FirelogAnalyticsEvent.FirelogAnalyticsEventWrapper.class, (ObjectEncoder) new FirelogAnalyticsEvent.FirelogAnalyticsEventWrapperEncoder()).registerEncoder(FirelogAnalyticsEvent.class, (ObjectEncoder) new FirelogAnalyticsEvent.FirelogAnalyticsEventEncoder()).build();

    public static boolean deliveryMetricsExportToBigQueryEnabled() {
        ApplicationInfo applicationInfo;
        Bundle bundle;
        try {
            FirebaseApp.getInstance();
            Context applicationContext = FirebaseApp.getInstance().getApplicationContext();
            SharedPreferences sharedPreferences = applicationContext.getSharedPreferences("com.google.firebase.messaging", 0);
            if (sharedPreferences.contains("export_to_big_query")) {
                return sharedPreferences.getBoolean("export_to_big_query", false);
            }
            try {
                PackageManager packageManager = applicationContext.getPackageManager();
                if (packageManager != null && (applicationInfo = packageManager.getApplicationInfo(applicationContext.getPackageName(), 128)) != null && (bundle = applicationInfo.metaData) != null && bundle.containsKey("delivery_metrics_exported_to_big_query_enabled")) {
                    return applicationInfo.metaData.getBoolean("delivery_metrics_exported_to_big_query_enabled", false);
                }
            } catch (PackageManager.NameNotFoundException unused) {
            }
            return false;
        } catch (IllegalStateException unused2) {
            Log.i(Constants.TAG, "FirebaseApp has not being initialized. Device might be in direct boot mode. Skip exporting delivery metrics to Big Query");
            return false;
        }
    }

    public static String getCollapseKey(Intent intent) {
        return intent.getStringExtra(Constants.MessagePayloadKeys.COLLAPSE_KEY);
    }

    public static String getComposerId(Intent intent) {
        return intent.getStringExtra(Constants.AnalyticsKeys.COMPOSER_ID);
    }

    public static String getComposerLabel(Intent intent) {
        return intent.getStringExtra(Constants.AnalyticsKeys.COMPOSER_LABEL);
    }

    public static String getInstanceId() {
        return FirebaseInstanceId.getInstance(FirebaseApp.getInstance()).getId();
    }

    public static String getMessageChannel(Intent intent) {
        return intent.getStringExtra(Constants.AnalyticsKeys.MESSAGE_CHANNEL);
    }

    public static String getMessageId(Intent intent) {
        String stringExtra = intent.getStringExtra(Constants.MessagePayloadKeys.MSGID);
        return stringExtra == null ? intent.getStringExtra(Constants.MessagePayloadKeys.MSGID_SERVER) : stringExtra;
    }

    public static String getMessageLabel(Intent intent) {
        return intent.getStringExtra(Constants.AnalyticsKeys.MESSAGE_LABEL);
    }

    private static int getMessagePriority(String str) {
        if ("high".equals(str)) {
            return 1;
        }
        return "normal".equals(str) ? 2 : 0;
    }

    public static String getMessageTime(Intent intent) {
        return intent.getStringExtra(Constants.AnalyticsKeys.MESSAGE_TIMESTAMP);
    }

    @Constants.FirelogAnalytics.MessageType
    public static String getMessageTypeForFirelog(Intent intent) {
        return (intent.getExtras() == null || !NotificationParams.isNotification(intent.getExtras())) ? Constants.FirelogAnalytics.MessageType.DATA_MESSAGE : Constants.FirelogAnalytics.MessageType.DISPLAY_NOTIFICATION;
    }

    public static String getMessageTypeForScion(Intent intent) {
        return (intent.getExtras() == null || !NotificationParams.isNotification(intent.getExtras())) ? "data" : Constants.ScionAnalytics.MessageType.DISPLAY_NOTIFICATION;
    }

    public static String getPackageName() {
        return FirebaseApp.getInstance().getApplicationContext().getPackageName();
    }

    public static int getPriority(Intent intent) {
        String stringExtra = intent.getStringExtra(Constants.MessagePayloadKeys.DELIVERED_PRIORITY);
        if (stringExtra == null) {
            if ("1".equals(intent.getStringExtra(Constants.MessagePayloadKeys.PRIORITY_REDUCED_V19))) {
                return 2;
            }
            stringExtra = intent.getStringExtra(Constants.MessagePayloadKeys.PRIORITY_V19);
        }
        return getMessagePriority(stringExtra);
    }

    public static String getProjectNumber() {
        FirebaseApp firebaseApp = FirebaseApp.getInstance();
        String gcmSenderId = firebaseApp.getOptions().getGcmSenderId();
        if (gcmSenderId != null) {
            return gcmSenderId;
        }
        String applicationId = firebaseApp.getOptions().getApplicationId();
        if (!applicationId.startsWith("1:")) {
            return applicationId;
        }
        String[] strArrSplit = applicationId.split(":");
        if (strArrSplit.length < 2) {
            return null;
        }
        String str = strArrSplit[1];
        if (str.isEmpty()) {
            return null;
        }
        return str;
    }

    public static String getTopic(Intent intent) {
        String stringExtra = intent.getStringExtra(Constants.MessagePayloadKeys.FROM);
        if (stringExtra == null || !stringExtra.startsWith("/topics/")) {
            return null;
        }
        return stringExtra;
    }

    public static int getTtl(Intent intent) {
        Object obj = intent.getExtras().get(Constants.MessagePayloadKeys.TTL);
        if (obj instanceof Integer) {
            return ((Integer) obj).intValue();
        }
        if (!(obj instanceof String)) {
            return 0;
        }
        try {
            return Integer.parseInt((String) obj);
        } catch (NumberFormatException unused) {
            String strValueOf = String.valueOf(obj);
            StringBuilder sb = new StringBuilder(strValueOf.length() + 13);
            sb.append("Invalid TTL: ");
            sb.append(strValueOf);
            Log.w(Constants.TAG, sb.toString());
            return 0;
        }
    }

    public static String getUseDeviceTime(Intent intent) {
        if (intent.hasExtra(Constants.AnalyticsKeys.MESSAGE_USE_DEVICE_TIME)) {
            return intent.getStringExtra(Constants.AnalyticsKeys.MESSAGE_USE_DEVICE_TIME);
        }
        return null;
    }

    private static boolean isDirectBootMessage(Intent intent) {
        return FirebaseMessagingService.ACTION_DIRECT_BOOT_REMOTE_INTENT.equals(intent.getAction());
    }

    public static void logNotificationDismiss(Intent intent) {
        logToScion(Constants.ScionAnalytics.EVENT_NOTIFICATION_DISMISS, intent);
    }

    public static void logNotificationForeground(Intent intent) {
        logToScion(Constants.ScionAnalytics.EVENT_NOTIFICATION_FOREGROUND, intent);
    }

    public static void logNotificationOpen(Intent intent) {
        setUserPropertyIfRequired(intent);
        logToScion(Constants.ScionAnalytics.EVENT_NOTIFICATION_OPEN, intent);
    }

    public static void logNotificationReceived(Intent intent, Transport<String> transport) {
        logToScion(Constants.ScionAnalytics.EVENT_NOTIFICATION_RECEIVE, intent);
        if (transport != null) {
            logToFirelog(Constants.FirelogAnalytics.EventType.MESSAGE_DELIVERED, intent, transport);
        }
    }

    private static void logToFirelog(@Constants.FirelogAnalytics.EventType String str, Intent intent, Transport<String> transport) {
        try {
            transport.send(Event.ofTelemetry(dataEncoder.encode(new FirelogAnalyticsEvent.FirelogAnalyticsEventWrapper(new FirelogAnalyticsEvent(str, intent)))));
        } catch (EncodingException unused) {
            Log.d(Constants.TAG, "Failed to encode big query analytics payload. Skip sending");
        }
    }

    public static void logToScion(String str, Intent intent) {
        Bundle bundle = new Bundle();
        String composerId = getComposerId(intent);
        if (composerId != null) {
            bundle.putString("_nmid", composerId);
        }
        String composerLabel = getComposerLabel(intent);
        if (composerLabel != null) {
            bundle.putString(Constants.ScionAnalytics.PARAM_MESSAGE_NAME, composerLabel);
        }
        String messageLabel = getMessageLabel(intent);
        if (!TextUtils.isEmpty(messageLabel)) {
            bundle.putString("label", messageLabel);
        }
        String messageChannel = getMessageChannel(intent);
        if (!TextUtils.isEmpty(messageChannel)) {
            bundle.putString(Constants.ScionAnalytics.PARAM_MESSAGE_CHANNEL, messageChannel);
        }
        String topic = getTopic(intent);
        if (topic != null) {
            bundle.putString(Constants.ScionAnalytics.PARAM_TOPIC, topic);
        }
        String messageTime = getMessageTime(intent);
        if (messageTime != null) {
            try {
                bundle.putInt(Constants.ScionAnalytics.PARAM_MESSAGE_TIME, Integer.parseInt(messageTime));
            } catch (NumberFormatException e9) {
                Log.w(Constants.TAG, "Error while parsing timestamp in GCM event", e9);
            }
        }
        String useDeviceTime = getUseDeviceTime(intent);
        if (useDeviceTime != null) {
            try {
                bundle.putInt(Constants.ScionAnalytics.PARAM_MESSAGE_DEVICE_TIME, Integer.parseInt(useDeviceTime));
            } catch (NumberFormatException e10) {
                Log.w(Constants.TAG, "Error while parsing use_device_time in GCM event", e10);
            }
        }
        String messageTypeForScion = getMessageTypeForScion(intent);
        if (Constants.ScionAnalytics.EVENT_NOTIFICATION_RECEIVE.equals(str) || Constants.ScionAnalytics.EVENT_NOTIFICATION_FOREGROUND.equals(str)) {
            bundle.putString(Constants.ScionAnalytics.PARAM_MESSAGE_TYPE, messageTypeForScion);
        }
        if (Log.isLoggable(Constants.TAG, 3)) {
            String strValueOf = String.valueOf(bundle);
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 37 + strValueOf.length());
            sb.append("Logging to scion event=");
            sb.append(str);
            sb.append(" scionPayload=");
            sb.append(strValueOf);
            Log.d(Constants.TAG, sb.toString());
        }
        AnalyticsConnector analyticsConnector = (AnalyticsConnector) FirebaseApp.getInstance().get(AnalyticsConnector.class);
        if (analyticsConnector != null) {
            analyticsConnector.logEvent("fcm", str, bundle);
        } else {
            Log.w(Constants.TAG, "Unable to log event: analytics library is missing");
        }
    }

    public static void setDeliveryMetricsExportToBigQuery(boolean z8) {
        FirebaseApp.getInstance().getApplicationContext().getSharedPreferences("com.google.firebase.messaging", 0).edit().putBoolean("export_to_big_query", z8).apply();
    }

    private static void setUserPropertyIfRequired(Intent intent) {
        if (intent == null) {
            return;
        }
        if (!"1".equals(intent.getStringExtra(Constants.AnalyticsKeys.TRACK_CONVERSIONS))) {
            if (Log.isLoggable(Constants.TAG, 3)) {
                Log.d(Constants.TAG, "Received event with track-conversion=false. Do not set user property");
                return;
            }
            return;
        }
        AnalyticsConnector analyticsConnector = (AnalyticsConnector) FirebaseApp.getInstance().get(AnalyticsConnector.class);
        if (Log.isLoggable(Constants.TAG, 3)) {
            Log.d(Constants.TAG, "Received event with track-conversion=true. Setting user property and reengagement event");
        }
        if (analyticsConnector == null) {
            Log.w(Constants.TAG, "Unable to set user property for conversion tracking:  analytics library is missing");
            return;
        }
        String stringExtra = intent.getStringExtra(Constants.AnalyticsKeys.COMPOSER_ID);
        analyticsConnector.setUserProperty("fcm", "_ln", stringExtra);
        Bundle bundle = new Bundle();
        bundle.putString("source", "Firebase");
        bundle.putString("medium", "notification");
        bundle.putString("campaign", stringExtra);
        analyticsConnector.logEvent("fcm", Constants.ScionAnalytics.EVENT_FIREBASE_CAMPAIGN, bundle);
    }

    public static boolean shouldUploadFirelogAnalytics(Intent intent) {
        if (intent == null || isDirectBootMessage(intent)) {
            return false;
        }
        return deliveryMetricsExportToBigQueryEnabled();
    }

    public static boolean shouldUploadScionMetrics(Intent intent) {
        if (intent == null || isDirectBootMessage(intent)) {
            return false;
        }
        return "1".equals(intent.getStringExtra(Constants.AnalyticsKeys.ENABLED));
    }
}
