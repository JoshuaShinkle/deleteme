package androidx.media.session;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.os.RemoteException;
import android.support.v4.media.MediaBrowserCompat;
import android.support.v4.media.session.MediaControllerCompat;
import android.util.Log;
import android.view.KeyEvent;
import java.util.List;

/* loaded from: classes.dex */
public class MediaButtonReceiver extends BroadcastReceiver {

    /* renamed from: androidx.media.session.MediaButtonReceiver$a */
    public static class C0427a extends MediaBrowserCompat.ConnectionCallback {

        /* renamed from: a */
        public final Context f2356a;

        /* renamed from: b */
        public final Intent f2357b;

        /* renamed from: c */
        public final BroadcastReceiver.PendingResult f2358c;

        /* renamed from: d */
        public MediaBrowserCompat f2359d;

        public C0427a(Context context, Intent intent, BroadcastReceiver.PendingResult pendingResult) {
            this.f2356a = context;
            this.f2357b = intent;
            this.f2358c = pendingResult;
        }

        /* renamed from: a */
        public final void m2196a() {
            this.f2359d.disconnect();
            this.f2358c.finish();
        }

        /* renamed from: b */
        public void m2197b(MediaBrowserCompat mediaBrowserCompat) {
            this.f2359d = mediaBrowserCompat;
        }

        @Override // android.support.v4.media.MediaBrowserCompat.ConnectionCallback
        public void onConnected() {
            try {
                new MediaControllerCompat(this.f2356a, this.f2359d.getSessionToken()).dispatchMediaButtonEvent((KeyEvent) this.f2357b.getParcelableExtra("android.intent.extra.KEY_EVENT"));
            } catch (RemoteException e9) {
                Log.e("MediaButtonReceiver", "Failed to create a media controller", e9);
            }
            m2196a();
        }

        @Override // android.support.v4.media.MediaBrowserCompat.ConnectionCallback
        public void onConnectionFailed() {
            m2196a();
        }

        @Override // android.support.v4.media.MediaBrowserCompat.ConnectionCallback
        public void onConnectionSuspended() {
            m2196a();
        }
    }

    /* renamed from: a */
    public static ComponentName m2193a(Context context) {
        Intent intent = new Intent("android.intent.action.MEDIA_BUTTON");
        intent.setPackage(context.getPackageName());
        List<ResolveInfo> listQueryBroadcastReceivers = context.getPackageManager().queryBroadcastReceivers(intent, 0);
        if (listQueryBroadcastReceivers.size() == 1) {
            ActivityInfo activityInfo = listQueryBroadcastReceivers.get(0).activityInfo;
            return new ComponentName(activityInfo.packageName, activityInfo.name);
        }
        if (listQueryBroadcastReceivers.size() <= 1) {
            return null;
        }
        Log.w("MediaButtonReceiver", "More than one BroadcastReceiver that handles android.intent.action.MEDIA_BUTTON was found, returning null.");
        return null;
    }

    /* renamed from: b */
    public static ComponentName m2194b(Context context, String str) {
        PackageManager packageManager = context.getPackageManager();
        Intent intent = new Intent(str);
        intent.setPackage(context.getPackageName());
        List<ResolveInfo> listQueryIntentServices = packageManager.queryIntentServices(intent, 0);
        if (listQueryIntentServices.size() == 1) {
            ServiceInfo serviceInfo = listQueryIntentServices.get(0).serviceInfo;
            return new ComponentName(serviceInfo.packageName, serviceInfo.name);
        }
        if (listQueryIntentServices.isEmpty()) {
            return null;
        }
        throw new IllegalStateException("Expected 1 service that handles " + str + ", found " + listQueryIntentServices.size());
    }

    /* renamed from: c */
    public static void m2195c(Context context, Intent intent) {
        context.startForegroundService(intent);
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (intent == null || !"android.intent.action.MEDIA_BUTTON".equals(intent.getAction()) || !intent.hasExtra("android.intent.extra.KEY_EVENT")) {
            Log.d("MediaButtonReceiver", "Ignore unsupported intent: " + intent);
            return;
        }
        ComponentName componentNameM2194b = m2194b(context, "android.intent.action.MEDIA_BUTTON");
        if (componentNameM2194b != null) {
            intent.setComponent(componentNameM2194b);
            m2195c(context, intent);
            return;
        }
        ComponentName componentNameM2194b2 = m2194b(context, "android.media.browse.MediaBrowserService");
        if (componentNameM2194b2 == null) {
            throw new IllegalStateException("Could not find any Service that handles android.intent.action.MEDIA_BUTTON or implements a media browser service.");
        }
        BroadcastReceiver.PendingResult pendingResultGoAsync = goAsync();
        Context applicationContext = context.getApplicationContext();
        C0427a c0427a = new C0427a(applicationContext, intent, pendingResultGoAsync);
        MediaBrowserCompat mediaBrowserCompat = new MediaBrowserCompat(applicationContext, componentNameM2194b2, c0427a, null);
        c0427a.m2197b(mediaBrowserCompat);
        mediaBrowserCompat.connect();
    }
}
