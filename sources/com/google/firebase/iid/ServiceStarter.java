package com.google.firebase.iid;

import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.os.Parcelable;
import android.util.Log;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.firebase.messaging.Constants;
import java.util.ArrayDeque;
import java.util.Queue;

@KeepForSdk
/* loaded from: classes2.dex */
public class ServiceStarter {
    public static final String ACTION_MESSAGING_EVENT = "com.google.firebase.MESSAGING_EVENT";

    @KeepForSdk
    public static final int ERROR_UNKNOWN = 500;
    public static final int SUCCESS = -1;
    private static ServiceStarter instance;
    private String firebaseMessagingServiceClassName = null;
    private Boolean hasWakeLockPermission = null;
    private Boolean hasAccessNetworkStatePermission = null;
    private final Queue<Intent> messagingEvents = new ArrayDeque();

    private ServiceStarter() {
    }

    public static PendingIntent createMessagingPendingIntent(Context context, int i9, Intent intent, int i10) {
        return PendingIntent.getBroadcast(context, i9, wrapServiceIntent(context, "com.google.firebase.MESSAGING_EVENT", intent), i10);
    }

    private int doStartService(Context context, Intent intent) {
        ComponentName componentNameStartService;
        String strResolveServiceClassName = resolveServiceClassName(context, intent);
        if (strResolveServiceClassName != null) {
            if (Log.isLoggable("FirebaseInstanceId", 3)) {
                Log.d("FirebaseInstanceId", strResolveServiceClassName.length() != 0 ? "Restricting intent to a specific service: ".concat(strResolveServiceClassName) : new String("Restricting intent to a specific service: "));
            }
            intent.setClassName(context.getPackageName(), strResolveServiceClassName);
        }
        try {
            if (hasWakeLockPermission(context)) {
                componentNameStartService = WakeLockHolder.startWakefulService(context, intent);
            } else {
                componentNameStartService = context.startService(intent);
                Log.d("FirebaseInstanceId", "Missing wake lock permission, service start may be delayed");
            }
            if (componentNameStartService != null) {
                return -1;
            }
            Log.e("FirebaseInstanceId", "Error while delivering the message: ServiceIntent not found.");
            return 404;
        } catch (IllegalStateException e9) {
            String strValueOf = String.valueOf(e9);
            StringBuilder sb = new StringBuilder(strValueOf.length() + 45);
            sb.append("Failed to start service while in background: ");
            sb.append(strValueOf);
            Log.e("FirebaseInstanceId", sb.toString());
            return 402;
        } catch (SecurityException e10) {
            Log.e("FirebaseInstanceId", "Error while delivering the message to the serviceIntent", e10);
            return 401;
        }
    }

    @KeepForSdk
    public static synchronized ServiceStarter getInstance() {
        if (instance == null) {
            instance = new ServiceStarter();
        }
        return instance;
    }

    private synchronized String resolveServiceClassName(Context context, Intent intent) {
        ServiceInfo serviceInfo;
        String str;
        String str2 = this.firebaseMessagingServiceClassName;
        if (str2 != null) {
            return str2;
        }
        ResolveInfo resolveInfoResolveService = context.getPackageManager().resolveService(intent, 0);
        if (resolveInfoResolveService != null && (serviceInfo = resolveInfoResolveService.serviceInfo) != null) {
            if (context.getPackageName().equals(serviceInfo.packageName) && (str = serviceInfo.name) != null) {
                if (str.startsWith(".")) {
                    String strValueOf = String.valueOf(context.getPackageName());
                    String strValueOf2 = String.valueOf(serviceInfo.name);
                    this.firebaseMessagingServiceClassName = strValueOf2.length() != 0 ? strValueOf.concat(strValueOf2) : new String(strValueOf);
                } else {
                    this.firebaseMessagingServiceClassName = serviceInfo.name;
                }
                return this.firebaseMessagingServiceClassName;
            }
            String str3 = serviceInfo.packageName;
            String str4 = serviceInfo.name;
            StringBuilder sb = new StringBuilder(String.valueOf(str3).length() + 94 + String.valueOf(str4).length());
            sb.append("Error resolving target intent service, skipping classname enforcement. Resolved service was: ");
            sb.append(str3);
            sb.append("/");
            sb.append(str4);
            Log.e("FirebaseInstanceId", sb.toString());
            return null;
        }
        Log.e("FirebaseInstanceId", "Failed to resolve target intent service, skipping classname enforcement");
        return null;
    }

    public static void setForTesting(ServiceStarter serviceStarter) {
        instance = serviceStarter;
    }

    @KeepForSdk
    public static void startMessagingServiceViaReceiver(Context context, Intent intent) {
        context.sendBroadcast(wrapServiceIntent(context, "com.google.firebase.MESSAGING_EVENT", intent));
    }

    public static Intent unwrapServiceIntent(Intent intent) {
        Parcelable parcelableExtra = intent.getParcelableExtra(Constants.IntentKeys.WRAPPED_INTENT);
        if (parcelableExtra instanceof Intent) {
            return (Intent) parcelableExtra;
        }
        return null;
    }

    private static Intent wrapServiceIntent(Context context, String str, Intent intent) {
        Intent intent2 = new Intent(context, (Class<?>) FirebaseInstanceIdReceiver.class);
        intent2.setAction(str);
        intent2.putExtra(Constants.IntentKeys.WRAPPED_INTENT, intent);
        return intent2;
    }

    @KeepForSdk
    public Intent getMessagingEvent() {
        return this.messagingEvents.poll();
    }

    public boolean hasAccessNetworkStatePermission(Context context) {
        if (this.hasAccessNetworkStatePermission == null) {
            this.hasAccessNetworkStatePermission = Boolean.valueOf(context.checkCallingOrSelfPermission("android.permission.ACCESS_NETWORK_STATE") == 0);
        }
        if (!this.hasWakeLockPermission.booleanValue() && Log.isLoggable("FirebaseInstanceId", 3)) {
            Log.d("FirebaseInstanceId", "Missing Permission: android.permission.ACCESS_NETWORK_STATE this should normally be included by the manifest merger, but may needed to be manually added to your manifest");
        }
        return this.hasAccessNetworkStatePermission.booleanValue();
    }

    public boolean hasWakeLockPermission(Context context) {
        if (this.hasWakeLockPermission == null) {
            this.hasWakeLockPermission = Boolean.valueOf(context.checkCallingOrSelfPermission("android.permission.WAKE_LOCK") == 0);
        }
        if (!this.hasWakeLockPermission.booleanValue() && Log.isLoggable("FirebaseInstanceId", 3)) {
            Log.d("FirebaseInstanceId", "Missing Permission: android.permission.WAKE_LOCK this should normally be included by the manifest merger, but may needed to be manually added to your manifest");
        }
        return this.hasWakeLockPermission.booleanValue();
    }

    public int startMessagingService(Context context, Intent intent) {
        if (Log.isLoggable("FirebaseInstanceId", 3)) {
            Log.d("FirebaseInstanceId", "Starting service");
        }
        this.messagingEvents.offer(intent);
        Intent intent2 = new Intent("com.google.firebase.MESSAGING_EVENT");
        intent2.setPackage(context.getPackageName());
        return doStartService(context, intent2);
    }
}
