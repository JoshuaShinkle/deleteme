package com.google.android.gms.gcm;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;
import com.google.android.exoplayer2.source.smoothstreaming.SsMediaSource;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.iid.InstanceID;
import com.google.android.gms.iid.zzaf;
import com.google.firebase.messaging.Constants;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import p132m.C5302a;

@Deprecated
/* loaded from: classes2.dex */
public class GoogleCloudMessaging {
    public static final String ERROR_MAIN_THREAD = "MAIN_THREAD";
    public static final String ERROR_SERVICE_NOT_AVAILABLE = "SERVICE_NOT_AVAILABLE";
    public static final String INSTANCE_ID_SCOPE = "GCM";

    @Deprecated
    public static final String MESSAGE_TYPE_DELETED = "deleted_messages";

    @Deprecated
    public static final String MESSAGE_TYPE_MESSAGE = "gcm";

    @Deprecated
    public static final String MESSAGE_TYPE_SEND_ERROR = "send_error";

    @Deprecated
    public static final String MESSAGE_TYPE_SEND_EVENT = "send_event";
    private static GoogleCloudMessaging zzae;
    private static final AtomicInteger zzah = new AtomicInteger(1);
    private PendingIntent zzaf;
    private final Map<String, Handler> zzag = Collections.synchronizedMap(new C5302a());
    private final BlockingQueue<Intent> zzai = new LinkedBlockingQueue();
    private final Messenger zzaj = new Messenger(new zzf(this, Looper.getMainLooper()));
    private Context zzl;

    @Deprecated
    public static synchronized GoogleCloudMessaging getInstance(Context context) {
        if (zzae == null) {
            zze(context);
            GoogleCloudMessaging googleCloudMessaging = new GoogleCloudMessaging();
            zzae = googleCloudMessaging;
            googleCloudMessaging.zzl = context.getApplicationContext();
        }
        return zzae;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean zzd(Intent intent) {
        Handler handlerRemove;
        String stringExtra = intent.getStringExtra("In-Reply-To");
        if (stringExtra == null && intent.hasExtra(Constants.IPC_BUNDLE_KEY_SEND_ERROR)) {
            stringExtra = intent.getStringExtra(Constants.MessagePayloadKeys.MSGID);
        }
        if (stringExtra == null || (handlerRemove = this.zzag.remove(stringExtra)) == null) {
            return false;
        }
        Message messageObtain = Message.obtain();
        messageObtain.obj = intent;
        return handlerRemove.sendMessage(messageObtain);
    }

    public static void zze(Context context) {
        String packageName = context.getPackageName();
        StringBuilder sb = new StringBuilder(String.valueOf(packageName).length() + 48);
        sb.append("GCM SDK is deprecated, ");
        sb.append(packageName);
        sb.append(" should update to use FCM");
        Log.w("GCM", sb.toString());
    }

    public static int zzf(Context context) throws PackageManager.NameNotFoundException {
        String strZzl = zzaf.zzl(context);
        if (strZzl == null) {
            return -1;
        }
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(strZzl, 0);
            if (packageInfo != null) {
                return packageInfo.versionCode;
            }
            return -1;
        } catch (PackageManager.NameNotFoundException unused) {
            return -1;
        }
    }

    private final synchronized void zzh() {
        PendingIntent pendingIntent = this.zzaf;
        if (pendingIntent != null) {
            pendingIntent.cancel();
            this.zzaf = null;
        }
    }

    @Deprecated
    public void close() {
        zzae = null;
        zzd.zzk = null;
        zzh();
    }

    @Deprecated
    public String getMessageType(Intent intent) {
        if (!"com.google.android.c2dm.intent.RECEIVE".equals(intent.getAction())) {
            return null;
        }
        String stringExtra = intent.getStringExtra(Constants.MessagePayloadKeys.MESSAGE_TYPE);
        return stringExtra != null ? stringExtra : "gcm";
    }

    @Deprecated
    public synchronized String register(String... strArr) {
        return zzd(zzaf.zzk(this.zzl), strArr);
    }

    @Deprecated
    public void send(String str, String str2, Bundle bundle) throws IOException {
        send(str, str2, -1L, bundle);
    }

    @Deprecated
    public synchronized void unregister() {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            throw new IOException("MAIN_THREAD");
        }
        InstanceID.getInstance(this.zzl).deleteInstanceID();
    }

    @Deprecated
    public void send(String str, String str2, long j9, Bundle bundle) throws IOException {
        if (str == null) {
            throw new IllegalArgumentException("Missing 'to'");
        }
        String strZzl = zzaf.zzl(this.zzl);
        if (strZzl == null) {
            throw new IOException("SERVICE_NOT_AVAILABLE");
        }
        Intent intent = new Intent("com.google.android.gcm.intent.SEND");
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        zze(intent);
        intent.setPackage(strZzl);
        intent.putExtra(Constants.MessagePayloadKeys.f15585TO, str);
        intent.putExtra(Constants.MessagePayloadKeys.MSGID, str2);
        intent.putExtra(Constants.MessagePayloadKeys.TTL, Long.toString(j9));
        int iIndexOf = str.indexOf(64);
        String strSubstring = iIndexOf > 0 ? str.substring(0, iIndexOf) : str;
        InstanceID.getInstance(this.zzl);
        intent.putExtra("google.from", InstanceID.zzp().zzf("", strSubstring, "GCM"));
        if (!strZzl.contains(".gsf")) {
            this.zzl.sendOrderedBroadcast(intent, "com.google.android.gtalkservice.permission.GTALK_SERVICE");
            return;
        }
        Bundle bundle2 = new Bundle();
        for (String str3 : bundle.keySet()) {
            Object obj = bundle.get(str3);
            if (obj instanceof String) {
                String strValueOf = String.valueOf(str3);
                bundle2.putString(strValueOf.length() != 0 ? Constants.MessageNotificationKeys.RESERVED_PREFIX.concat(strValueOf) : new String(Constants.MessageNotificationKeys.RESERVED_PREFIX), (String) obj);
            }
        }
        bundle2.putString(Constants.MessagePayloadKeys.f15585TO, str);
        bundle2.putString(Constants.MessagePayloadKeys.MSGID, str2);
        InstanceID.getInstance(this.zzl).zze("GCM", "upstream", bundle2);
    }

    private final synchronized void zze(Intent intent) {
        if (this.zzaf == null) {
            Intent intent2 = new Intent();
            intent2.setPackage("com.google.example.invalidpackage");
            this.zzaf = PendingIntent.getBroadcast(this.zzl, 0, intent2, 0);
        }
        intent.putExtra("app", this.zzaf);
    }

    @ShowFirstParty
    @VisibleForTesting
    @Deprecated
    private final synchronized String zzd(boolean z8, String... strArr) {
        String strZzl = zzaf.zzl(this.zzl);
        if (strZzl != null) {
            if (strArr != null && strArr.length != 0) {
                StringBuilder sb = new StringBuilder(strArr[0]);
                for (int i9 = 1; i9 < strArr.length; i9++) {
                    sb.append(',');
                    sb.append(strArr[i9]);
                }
                String string = sb.toString();
                Bundle bundle = new Bundle();
                if (strZzl.contains(".gsf")) {
                    bundle.putString("legacy.sender", string);
                    return InstanceID.getInstance(this.zzl).getToken(string, "GCM", bundle);
                }
                bundle.putString("sender", string);
                Intent intentZzd = zzd(bundle, z8);
                if (intentZzd != null) {
                    String stringExtra = intentZzd.getStringExtra("registration_id");
                    if (stringExtra != null) {
                        return stringExtra;
                    }
                    String stringExtra2 = intentZzd.getStringExtra(Constants.IPC_BUNDLE_KEY_SEND_ERROR);
                    if (stringExtra2 != null) {
                        throw new IOException(stringExtra2);
                    }
                    throw new IOException("SERVICE_NOT_AVAILABLE");
                }
                throw new IOException("SERVICE_NOT_AVAILABLE");
            }
            throw new IllegalArgumentException("No senderIds");
        }
        throw new IOException("SERVICE_NOT_AVAILABLE");
    }

    @Deprecated
    private final Intent zzd(Bundle bundle, boolean z8) throws IOException {
        if (Looper.getMainLooper() != Looper.myLooper()) {
            if (zzf(this.zzl) >= 0) {
                Intent intent = new Intent(z8 ? "com.google.iid.TOKEN_REQUEST" : "com.google.android.c2dm.intent.REGISTER");
                intent.setPackage(zzaf.zzl(this.zzl));
                zze(intent);
                int andIncrement = zzah.getAndIncrement();
                StringBuilder sb = new StringBuilder(21);
                sb.append("google.rpc");
                sb.append(andIncrement);
                intent.putExtra(Constants.MessagePayloadKeys.MSGID, sb.toString());
                intent.putExtras(bundle);
                intent.putExtra("google.messenger", this.zzaj);
                if (z8) {
                    this.zzl.sendBroadcast(intent);
                } else {
                    this.zzl.startService(intent);
                }
                try {
                    return this.zzai.poll(SsMediaSource.DEFAULT_LIVE_PRESENTATION_DELAY_MS, TimeUnit.MILLISECONDS);
                } catch (InterruptedException e9) {
                    throw new IOException(e9.getMessage());
                }
            }
            throw new IOException("Google Play Services missing");
        }
        throw new IOException("MAIN_THREAD");
    }
}
