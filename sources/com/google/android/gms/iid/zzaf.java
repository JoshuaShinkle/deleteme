package com.google.android.gms.iid;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.ConditionVariable;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.Parcelable;
import android.os.Process;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.exoplayer2.source.smoothstreaming.SsMediaSource;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.messaging.Constants;
import java.io.IOException;
import java.security.KeyPair;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import p132m.C5302a;

@ShowFirstParty
/* loaded from: classes2.dex */
public class zzaf {
    private PendingIntent zzaf;
    private Messenger zzaj;
    private Map<String, Object> zzcz = new C5302a();
    private Messenger zzda;
    private MessengerCompat zzdb;
    private Context zzl;
    private static final zzaj<Boolean> zzct = zzai.zzy().zzd("gcm_iid_use_messenger_ipc", true);
    private static String zzcu = null;
    private static boolean zzcv = false;
    private static int zzcw = 0;
    private static int zzcx = 0;
    private static int zzcp = 0;
    private static BroadcastReceiver zzcy = null;

    public zzaf(Context context) {
        this.zzl = context;
    }

    private static boolean zzd(PackageManager packageManager, String str, String str2) {
        if (packageManager.checkPermission("com.google.android.c2dm.permission.SEND", str) == 0) {
            return zzd(packageManager, str);
        }
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 56 + String.valueOf(str2).length());
        sb.append("Possible malicious package ");
        sb.append(str);
        sb.append(" declares ");
        sb.append(str2);
        sb.append(" without permission");
        Log.w("InstanceID", sb.toString());
        return false;
    }

    private final synchronized void zzg(Intent intent) {
        if (this.zzaf == null) {
            Intent intent2 = new Intent();
            intent2.setPackage("com.google.example.invalidpackage");
            this.zzaf = PendingIntent.getBroadcast(this.zzl, 0, intent2, 0);
        }
        intent.putExtra("app", this.zzaf);
    }

    public static String zzi(Bundle bundle) {
        if (bundle == null) {
            throw new IOException("SERVICE_NOT_AVAILABLE");
        }
        String string = bundle.getString("registration_id");
        if (string == null) {
            string = bundle.getString("unregistered");
        }
        if (string != null) {
            return string;
        }
        String string2 = bundle.getString(Constants.IPC_BUNDLE_KEY_SEND_ERROR);
        if (string2 != null) {
            throw new IOException(string2);
        }
        String strValueOf = String.valueOf(bundle);
        StringBuilder sb = new StringBuilder(strValueOf.length() + 29);
        sb.append("Unexpected response from GCM ");
        sb.append(strValueOf);
        Log.w("InstanceID", sb.toString(), new Throwable());
        throw new IOException("SERVICE_NOT_AVAILABLE");
    }

    private final Bundle zzj(Bundle bundle) throws RemoteException, IOException {
        Bundle bundleZzk = zzk(bundle);
        if (bundleZzk == null || !bundleZzk.containsKey("google.messenger")) {
            return bundleZzk;
        }
        Bundle bundleZzk2 = zzk(bundle);
        if (bundleZzk2 == null || !bundleZzk2.containsKey("google.messenger")) {
            return bundleZzk2;
        }
        return null;
    }

    @ShowFirstParty
    public static boolean zzk(Context context) {
        if (zzcu != null) {
            zzl(context);
        }
        return zzcv;
    }

    @ShowFirstParty
    public static String zzl(Context context) {
        boolean z8;
        String str = zzcu;
        if (str != null) {
            return str;
        }
        zzcw = Process.myUid();
        PackageManager packageManager = context.getPackageManager();
        boolean z9 = true;
        if (!PlatformVersion.isAtLeastO()) {
            Iterator<ResolveInfo> it = packageManager.queryIntentServices(new Intent("com.google.android.c2dm.intent.REGISTER"), 0).iterator();
            while (true) {
                if (!it.hasNext()) {
                    z8 = false;
                    break;
                }
                if (zzd(packageManager, it.next().serviceInfo.packageName, "com.google.android.c2dm.intent.REGISTER")) {
                    zzcv = false;
                    z8 = true;
                    break;
                }
            }
            if (z8) {
                return zzcu;
            }
        }
        Iterator<ResolveInfo> it2 = packageManager.queryBroadcastReceivers(new Intent("com.google.iid.TOKEN_REQUEST"), 0).iterator();
        while (true) {
            if (!it2.hasNext()) {
                z9 = false;
                break;
            }
            if (zzd(packageManager, it2.next().activityInfo.packageName, "com.google.iid.TOKEN_REQUEST")) {
                zzcv = true;
                break;
            }
        }
        if (z9) {
            return zzcu;
        }
        Log.w("InstanceID", "Failed to resolve IID implementation package, falling back");
        if (zzd(packageManager, "com.google.android.gms")) {
            zzcv = PlatformVersion.isAtLeastO();
            return zzcu;
        }
        if (PlatformVersion.isAtLeastLollipop() || !zzd(packageManager, "com.google.android.gsf")) {
            Log.w("InstanceID", "Google Play services is missing, unable to get tokens");
            return null;
        }
        zzcv = false;
        return zzcu;
    }

    private static int zzm(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(zzl(context), 0).versionCode;
        } catch (PackageManager.NameNotFoundException unused) {
            return -1;
        }
    }

    private static synchronized String zzx() {
        int i9;
        i9 = zzcp;
        zzcp = i9 + 1;
        return Integer.toString(i9);
    }

    public final void zze(Message message) {
        if (message == null) {
            return;
        }
        Object obj = message.obj;
        if (!(obj instanceof Intent)) {
            Log.w("InstanceID", "Dropping invalid message");
            return;
        }
        Intent intent = (Intent) obj;
        intent.setExtrasClassLoader(MessengerCompat.class.getClassLoader());
        if (intent.hasExtra("google.messenger")) {
            Parcelable parcelableExtra = intent.getParcelableExtra("google.messenger");
            if (parcelableExtra instanceof MessengerCompat) {
                this.zzdb = (MessengerCompat) parcelableExtra;
            }
            if (parcelableExtra instanceof Messenger) {
                this.zzda = (Messenger) parcelableExtra;
            }
        }
        zzh((Intent) message.obj);
    }

    public final void zzh(Intent intent) {
        String strSubstring;
        if (intent == null) {
            if (Log.isLoggable("InstanceID", 3)) {
                Log.d("InstanceID", "Unexpected response: null");
                return;
            }
            return;
        }
        String action = intent.getAction();
        if (!"com.google.android.c2dm.intent.REGISTRATION".equals(action) && !"com.google.android.gms.iid.InstanceID".equals(action)) {
            if (Log.isLoggable("InstanceID", 3)) {
                String strValueOf = String.valueOf(intent.getAction());
                Log.d("InstanceID", strValueOf.length() != 0 ? "Unexpected response ".concat(strValueOf) : new String("Unexpected response "));
                return;
            }
            return;
        }
        String stringExtra = intent.getStringExtra("registration_id");
        if (stringExtra == null) {
            stringExtra = intent.getStringExtra("unregistered");
        }
        if (stringExtra != null) {
            Matcher matcher = Pattern.compile("\\|ID\\|([^|]+)\\|:?+(.*)").matcher(stringExtra);
            if (!matcher.matches()) {
                if (Log.isLoggable("InstanceID", 3)) {
                    Log.d("InstanceID", stringExtra.length() != 0 ? "Unexpected response string: ".concat(stringExtra) : new String("Unexpected response string: "));
                    return;
                }
                return;
            } else {
                String strGroup = matcher.group(1);
                String strGroup2 = matcher.group(2);
                Bundle extras = intent.getExtras();
                extras.putString("registration_id", strGroup2);
                zzd(strGroup, (Object) extras);
                return;
            }
        }
        String stringExtra2 = intent.getStringExtra(Constants.IPC_BUNDLE_KEY_SEND_ERROR);
        if (stringExtra2 == null) {
            String strValueOf2 = String.valueOf(intent.getExtras());
            StringBuilder sb = new StringBuilder(strValueOf2.length() + 49);
            sb.append("Unexpected response, no error or registration id ");
            sb.append(strValueOf2);
            Log.w("InstanceID", sb.toString());
            return;
        }
        if (Log.isLoggable("InstanceID", 3)) {
            Log.d("InstanceID", stringExtra2.length() != 0 ? "Received InstanceID error ".concat(stringExtra2) : new String("Received InstanceID error "));
        }
        String str = null;
        if (stringExtra2.startsWith("|")) {
            String[] strArrSplit = stringExtra2.split("\\|");
            if (!"ID".equals(strArrSplit[1])) {
                Log.w("InstanceID", stringExtra2.length() != 0 ? "Unexpected structured response ".concat(stringExtra2) : new String("Unexpected structured response "));
            }
            if (strArrSplit.length > 2) {
                String str2 = strArrSplit[2];
                strSubstring = strArrSplit[3];
                if (strSubstring.startsWith(":")) {
                    strSubstring = strSubstring.substring(1);
                }
                str = str2;
            } else {
                strSubstring = "UNKNOWN";
            }
            stringExtra2 = strSubstring;
            intent.putExtra(Constants.IPC_BUNDLE_KEY_SEND_ERROR, stringExtra2);
        }
        if (str != null) {
            zzd(str, (Object) stringExtra2);
            return;
        }
        synchronized (getClass()) {
            for (String str3 : this.zzcz.keySet()) {
                Object obj = this.zzcz.get(str3);
                this.zzcz.put(str3, stringExtra2);
                zzd(obj, stringExtra2);
            }
        }
    }

    private static boolean zzd(PackageManager packageManager, String str) throws PackageManager.NameNotFoundException {
        try {
            ApplicationInfo applicationInfo = packageManager.getApplicationInfo(str, 0);
            zzcu = applicationInfo.packageName;
            zzcx = applicationInfo.uid;
            return true;
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }

    private final Bundle zzk(Bundle bundle) throws RemoteException, IOException {
        Bundle bundle2;
        ConditionVariable conditionVariable = new ConditionVariable();
        String strZzx = zzx();
        synchronized (getClass()) {
            this.zzcz.put(strZzx, conditionVariable);
        }
        if (this.zzaj == null) {
            zzl(this.zzl);
            this.zzaj = new Messenger(new zzag(this, Looper.getMainLooper()));
        }
        if (zzcu != null) {
            Intent intent = new Intent(zzcv ? "com.google.iid.TOKEN_REQUEST" : "com.google.android.c2dm.intent.REGISTER");
            intent.setPackage(zzcu);
            intent.putExtras(bundle);
            zzg(intent);
            StringBuilder sb = new StringBuilder(String.valueOf(strZzx).length() + 5);
            sb.append("|ID|");
            sb.append(strZzx);
            sb.append("|");
            intent.putExtra("kid", sb.toString());
            StringBuilder sb2 = new StringBuilder(String.valueOf(strZzx).length() + 5);
            sb2.append("|ID|");
            sb2.append(strZzx);
            sb2.append("|");
            intent.putExtra("X-kid", sb2.toString());
            boolean zEquals = "com.google.android.gsf".equals(zzcu);
            String stringExtra = intent.getStringExtra("useGsf");
            if (stringExtra != null) {
                zEquals = "1".equals(stringExtra);
            }
            if (Log.isLoggable("InstanceID", 3)) {
                String strValueOf = String.valueOf(intent.getExtras());
                StringBuilder sb3 = new StringBuilder(strValueOf.length() + 8);
                sb3.append("Sending ");
                sb3.append(strValueOf);
                Log.d("InstanceID", sb3.toString());
            }
            if (this.zzda != null) {
                intent.putExtra("google.messenger", this.zzaj);
                Message messageObtain = Message.obtain();
                messageObtain.obj = intent;
                try {
                    this.zzda.send(messageObtain);
                } catch (RemoteException unused) {
                    if (Log.isLoggable("InstanceID", 3)) {
                        Log.d("InstanceID", "Messenger failed, fallback to startService");
                    }
                }
            } else if (zEquals) {
                synchronized (zzaf.class) {
                    if (zzcy == null) {
                        zzcy = new zzah(this);
                        if (Log.isLoggable("InstanceID", 3)) {
                            Log.d("InstanceID", "Registered GSF callback receiver");
                        }
                        IntentFilter intentFilter = new IntentFilter("com.google.android.c2dm.intent.REGISTRATION");
                        intentFilter.addCategory(this.zzl.getPackageName());
                        this.zzl.registerReceiver(zzcy, intentFilter, "com.google.android.c2dm.permission.SEND", null);
                    }
                }
                this.zzl.sendBroadcast(intent);
            } else {
                intent.putExtra("google.messenger", this.zzaj);
                intent.putExtra("messenger2", "1");
                if (this.zzdb != null) {
                    Message messageObtain2 = Message.obtain();
                    messageObtain2.obj = intent;
                    try {
                        this.zzdb.send(messageObtain2);
                    } catch (RemoteException unused2) {
                        if (Log.isLoggable("InstanceID", 3)) {
                            Log.d("InstanceID", "Messenger failed, fallback to startService");
                        }
                    }
                } else if (zzcv) {
                    this.zzl.sendBroadcast(intent);
                } else {
                    this.zzl.startService(intent);
                }
            }
            conditionVariable.block(SsMediaSource.DEFAULT_LIVE_PRESENTATION_DELAY_MS);
            synchronized (getClass()) {
                Object objRemove = this.zzcz.remove(strZzx);
                if (objRemove instanceof Bundle) {
                    bundle2 = (Bundle) objRemove;
                } else {
                    if (objRemove instanceof String) {
                        throw new IOException((String) objRemove);
                    }
                    String strValueOf2 = String.valueOf(objRemove);
                    StringBuilder sb4 = new StringBuilder(strValueOf2.length() + 12);
                    sb4.append("No response ");
                    sb4.append(strValueOf2);
                    Log.w("InstanceID", sb4.toString());
                    throw new IOException(InstanceID.ERROR_TIMEOUT);
                }
            }
            return bundle2;
        }
        throw new IOException(InstanceID.ERROR_MISSING_INSTANCEID_SERVICE);
    }

    private final void zzd(String str, Object obj) {
        synchronized (getClass()) {
            Object obj2 = this.zzcz.get(str);
            this.zzcz.put(str, obj);
            zzd(obj2, obj);
        }
    }

    private static void zzd(Object obj, Object obj2) throws RemoteException {
        if (obj instanceof ConditionVariable) {
            ((ConditionVariable) obj).open();
        }
        if (obj instanceof Messenger) {
            Messenger messenger = (Messenger) obj;
            Message messageObtain = Message.obtain();
            messageObtain.obj = obj2;
            try {
                messenger.send(messageObtain);
            } catch (RemoteException e9) {
                String strValueOf = String.valueOf(e9);
                StringBuilder sb = new StringBuilder(strValueOf.length() + 24);
                sb.append("Failed to send response ");
                sb.append(strValueOf);
                Log.w("InstanceID", sb.toString());
            }
        }
    }

    public final Bundle zzd(Bundle bundle, KeyPair keyPair) {
        int iZzm = zzm(this.zzl);
        bundle.putString("gmsv", Integer.toString(iZzm));
        bundle.putString("osv", Integer.toString(Build.VERSION.SDK_INT));
        bundle.putString("app_ver", Integer.toString(InstanceID.zzg(this.zzl)));
        bundle.putString("app_ver_name", InstanceID.zzh(this.zzl));
        bundle.putString("cliv", "iid-12451000");
        bundle.putString("appid", InstanceID.zzd(keyPair));
        if (iZzm >= 12000000 && zzct.get().booleanValue()) {
            try {
                return (Bundle) Tasks.await(new zzr(this.zzl).zzd(1, bundle));
            } catch (InterruptedException | ExecutionException e9) {
                if (Log.isLoggable("InstanceID", 3)) {
                    String strValueOf = String.valueOf(e9);
                    StringBuilder sb = new StringBuilder(strValueOf.length() + 22);
                    sb.append("Error making request: ");
                    sb.append(strValueOf);
                    Log.d("InstanceID", sb.toString());
                }
                if ((e9.getCause() instanceof zzaa) && ((zzaa) e9.getCause()).getErrorCode() == 4) {
                    return zzj(bundle);
                }
                return null;
            }
        }
        return zzj(bundle);
    }
}
