package p218v2;

import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.NetworkRequest;
import android.net.wifi.SupplicantState;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.NetworkOnMainThreadException;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.Log;
import com.cyberlink.you.Globals;
import com.cyberlink.you.chat.XMPPManager;
import com.cyberlink.you.friends.FriendsClient;
import com.cyberlink.you.utility.ULogUtility;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import java.lang.reflect.InvocationTargetException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;
import org.apache.commons.lang3.StringUtils;
import p116k4.C5154j;
import p209u2.C6370g;
import p209u2.C6389z;

/* renamed from: v2.d */
/* loaded from: classes.dex */
public class C6456d {

    /* renamed from: x */
    public static boolean f21730x = true;

    /* renamed from: a */
    public boolean f21731a;

    /* renamed from: b */
    public final ConnectivityManager f21732b;

    /* renamed from: c */
    public final WifiManager f21733c;

    /* renamed from: d */
    public final TelephonyManager f21734d;

    /* renamed from: n */
    public final Handler f21744n;

    /* renamed from: e */
    public boolean f21735e = false;

    /* renamed from: f */
    public String f21736f = "";

    /* renamed from: g */
    public String f21737g = "";

    /* renamed from: h */
    public final Object f21738h = new Object();

    /* renamed from: i */
    public final List<String> f21739i = new ArrayList();

    /* renamed from: j */
    public boolean f21740j = false;

    /* renamed from: k */
    public boolean f21741k = false;

    /* renamed from: l */
    public final String f21742l = "com.sonymobile.SUPER_STAMINA";

    /* renamed from: m */
    public volatile boolean f21743m = false;

    /* renamed from: o */
    public final AtomicBoolean f21745o = new AtomicBoolean(false);

    /* renamed from: p */
    public BroadcastReceiver f21746p = new b();

    /* renamed from: q */
    public BroadcastReceiver f21747q = new c();

    /* renamed from: r */
    public BroadcastReceiver f21748r = new d();

    /* renamed from: s */
    public BroadcastReceiver f21749s = new e();

    /* renamed from: t */
    public BroadcastReceiver f21750t = new f();

    /* renamed from: u */
    public final C6389z<j> f21751u = new C6389z<>("ConnMgr");

    /* renamed from: v */
    public final List<String> f21752v = Arrays.asList("CL-WLAN", "CL-WLAN-AC", "CL-GUEST", "PF-WLAN", "PF-WLAN-AC");

    /* renamed from: w */
    public final Runnable f21753w = new h();

    /* renamed from: v2.d$a */
    public class a extends Thread {
        public a() {
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() throws InterruptedException {
            Thread.currentThread().setName("reconnect");
            ULogUtility.m16680p("ConnectionManager", "Start reconnect XMPP loop");
            while (true) {
                if (C6456d.this.m24748G()) {
                    ULogUtility.m16680p("ConnectionManager", "Try connect to XMPP , isConnected : " + XMPPManager.m14184g0().m14204A0());
                    if (!FriendsClient.m15680p0() || !XMPPManager.m14184g0().m14204A0()) {
                        if (((Boolean) XMPPManager.m14184g0().m14213M().first).booleanValue() && XMPPManager.m14184g0().m14277z0()) {
                            ULogUtility.m16680p("ConnectionManager", "XMPP is authorized");
                            C6456d.this.m24749I();
                            break;
                        } else {
                            ULogUtility.m16680p("ConnectionManager", "XMPP is authorize failed !!!");
                            try {
                                Thread.sleep(DefaultRenderersFactory.DEFAULT_ALLOWED_VIDEO_JOINING_TIME_MS);
                            } catch (InterruptedException unused) {
                                Thread.currentThread().interrupt();
                            }
                        }
                    } else {
                        ULogUtility.m16680p("ConnectionManager", "XMPP is connected");
                        C6456d.this.m24749I();
                        break;
                    }
                } else {
                    Thread.sleep(DefaultRenderersFactory.DEFAULT_ALLOWED_VIDEO_JOINING_TIME_MS);
                }
            }
            C6456d.this.f21745o.set(false);
        }
    }

    /* renamed from: v2.d$b */
    public class b extends BroadcastReceiver {

        /* renamed from: v2.d$b$a */
        public class a extends Thread {
            public a() {
            }

            @Override // java.lang.Thread, java.lang.Runnable
            public void run() throws ExecutionException, InterruptedException, TimeoutException {
                ULogUtility.m16680p("ConnectionManager", "Disconnect from XMPP");
                try {
                    XMPPManager.m14184g0().m14223U().get(5L, TimeUnit.SECONDS);
                } catch (InterruptedException | ExecutionException | TimeoutException e9) {
                    ULogUtility.m16676l("ConnectionManager", "Disconnect from XMPP exception" + e9);
                    e9.printStackTrace();
                }
                if (C6456d.this.f21731a) {
                    C6456d.this.m24750J();
                }
            }
        }

        public b() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String str;
            boolean zIsRoaming;
            String str2;
            if (intent.getExtras() != null) {
                C6456d.this.f21731a = !intent.getBooleanExtra("noConnectivity", false);
                Boolean boolValueOf = Boolean.valueOf(intent.getBooleanExtra("ForceReconnect", false));
                ULogUtility.m16680p("ConnectionManager", "Network connectivity : " + C6456d.this.f21731a);
                if (!C6456d.this.m24747F()) {
                    Log.v("ConnectionManager", "!isHeld() do nothing");
                    return;
                }
                if (C6456d.this.f21732b != null) {
                    NetworkInfo activeNetworkInfo = C6456d.this.f21732b.getActiveNetworkInfo();
                    boolean zIsActiveNetworkMetered = C6456d.this.f21732b.isActiveNetworkMetered();
                    str = "";
                    if (activeNetworkInfo != null) {
                        String typeName = activeNetworkInfo.getTypeName();
                        String ssid = typeName.equals("WIFI") ? C6456d.this.f21733c.getConnectionInfo().getSSID() : activeNetworkInfo.getSubtypeName();
                        str = ssid != null ? ssid : "";
                        zIsRoaming = activeNetworkInfo.isRoaming();
                        str2 = str;
                        str = typeName;
                    } else {
                        zIsRoaming = false;
                        str2 = "";
                    }
                    if (C6456d.this.f21731a) {
                        ULogUtility.m16680p("ConnectionManager", "Type: " + str + ", SubType: " + str2 + ", roaming: " + zIsRoaming + ", metered: " + zIsActiveNetworkMetered);
                    }
                    if (C6456d.f21730x) {
                        boolean unused = C6456d.f21730x = false;
                        C6456d c6456d = C6456d.this;
                        c6456d.f21735e = c6456d.f21731a;
                        C6456d.this.f21736f = str;
                        C6456d.this.f21737g = str2;
                        return;
                    }
                    if (!boolValueOf.booleanValue() && C6456d.this.f21731a == C6456d.this.f21735e && str.equals(C6456d.this.f21736f) && str2.equals(C6456d.this.f21737g)) {
                        Log.i("ConnectionManager", "Don't Need Reconnect");
                    } else {
                        if (activeNetworkInfo != null && activeNetworkInfo.getType() == 1) {
                            C6456d.this.m24752L();
                        }
                        new a().start();
                    }
                    C6456d c6456d2 = C6456d.this;
                    c6456d2.f21735e = c6456d2.f21731a;
                    C6456d.this.f21736f = str;
                    C6456d.this.f21737g = str2;
                }
            }
        }
    }

    /* renamed from: v2.d$c */
    public class c extends BroadcastReceiver {
        public c() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action == null) {
                return;
            }
            if (!action.equals("android.intent.action.SCREEN_OFF")) {
                if (action.equals("android.intent.action.SCREEN_ON") && C6456d.this.f21740j && C6456d.this.f21741k) {
                    Log.w("ConnectionManager", "Screen On: Stamina Enabled, reconnect");
                    C6456d.this.m24755P();
                    return;
                }
                return;
            }
            if (C6456d.this.f21740j) {
                if (C6456d.this.f21741k) {
                    Log.w("ConnectionManager", "Screen Off: Stamina Enabled, disconnect");
                    XMPPManager.f12475D = false;
                    XMPPManager.m14184g0().m14223U();
                    return;
                }
                return;
            }
            if (Build.MANUFACTURER.toUpperCase(Locale.US).contains("SONY")) {
                C6456d.this.f21735e = false;
                C6456d.this.f21736f = "";
                C6456d.this.f21737g = "";
            }
        }
    }

    /* renamed from: v2.d$d */
    public class d extends BroadcastReceiver {
        public d() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action != null && action.equals("com.sonymobile.SUPER_STAMINA")) {
                C6456d.this.f21740j = true;
                Bundle extras = intent.getExtras();
                if (extras != null) {
                    C6456d.this.f21741k = extras.getBoolean(RemoteConfigConstants.ResponseFieldKey.STATE);
                }
            }
        }
    }

    /* renamed from: v2.d$e */
    public class e extends BroadcastReceiver {
        public e() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            SupplicantState supplicantState;
            WifiInfo connectionInfo;
            String action = intent.getAction();
            if (action == null || !action.equals("android.net.wifi.supplicant.STATE_CHANGE") || (supplicantState = (SupplicantState) intent.getParcelableExtra("newState")) == null) {
                return;
            }
            String string = supplicantState.toString();
            if (supplicantState == SupplicantState.COMPLETED && C6456d.this.f21733c != null && (connectionInfo = C6456d.this.f21733c.getConnectionInfo()) != null) {
                String ssid = connectionInfo.getSSID();
                String bssid = connectionInfo.getBSSID();
                if (ssid != null && bssid != null) {
                    string = string + StringUtils.SPACE + ssid + StringUtils.SPACE + bssid.toUpperCase(Locale.US);
                }
            }
            ULogUtility.m16664G(string, "WiFi");
        }
    }

    /* renamed from: v2.d$f */
    public class f extends BroadcastReceiver {
        public f() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action == null) {
                return;
            }
            Log.w("ConnectionManager", action);
            ULogUtility.m16664G(action, "Time");
            if (action.equals("android.intent.action.DATE_CHANGED") || action.equals("android.intent.action.TIME_SET") || action.equals("android.intent.action.TIMEZONE_CHANGED")) {
                new Thread(new Runnable() { // from class: v2.e
                    @Override // java.lang.Runnable
                    public final void run() {
                        FriendsClient.m15660Y0();
                    }
                }).start();
            }
        }
    }

    /* renamed from: v2.d$g */
    public class g extends C6389z.a<j> {
        public g() {
        }

        @Override // p209u2.C6389z.a
        /* renamed from: a */
        public void mo5634a(String str, Throwable th) {
            C5154j.m20076j(th);
        }

        @Override // p209u2.C6389z.a
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public void mo5635b(j jVar) {
            jVar.onConnected();
        }
    }

    /* renamed from: v2.d$h */
    public class h implements Runnable {
        public h() {
        }

        @Override // java.lang.Runnable
        public void run() {
            NetworkInfo activeNetworkInfo;
            String extraInfo;
            if (!XMPPManager.m14184g0().m14204A0() && (activeNetworkInfo = C6456d.this.f21732b.getActiveNetworkInfo()) != null && activeNetworkInfo.getType() == 1 && activeNetworkInfo.getState() == NetworkInfo.State.CONNECTED && (extraInfo = activeNetworkInfo.getExtraInfo()) != null && C6456d.this.f21752v.contains(extraInfo)) {
                boolean zM24715H = false;
                for (int i9 = 0; i9 < 3 && !(zM24715H = C6456d.m24715H()); i9++) {
                }
                if (!zM24715H) {
                    C6456d.m24716N(C6456d.this.f21732b, C6456d.this.f21734d);
                }
            }
            C6456d.this.f21744n.postDelayed(this, 15000L);
        }
    }

    /* renamed from: v2.d$i */
    public class i extends ConnectivityManager.NetworkCallback {

        /* renamed from: a */
        public final /* synthetic */ ConnectivityManager f21763a;

        public i(ConnectivityManager connectivityManager) {
            this.f21763a = connectivityManager;
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public void onAvailable(Network network) {
            super.onAvailable(network);
            try {
                boolean zBindProcessToNetwork = this.f21763a.bindProcessToNetwork(network);
                StringBuilder sb = new StringBuilder();
                sb.append("Request Mobile Network ");
                sb.append(zBindProcessToNetwork ? "successfully" : "failed");
                Log.i("ConnectionManager", sb.toString());
            } catch (Exception e9) {
                Log.e("ConnectionManager", e9.toString());
            }
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public void onCapabilitiesChanged(Network network, NetworkCapabilities networkCapabilities) {
            super.onCapabilitiesChanged(network, networkCapabilities);
            Log.e("ConnectionManager", networkCapabilities.toString());
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public void onLosing(Network network, int i9) {
            super.onLosing(network, i9);
            Log.i("ConnectionManager", "onLosing");
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public void onLost(Network network) {
            super.onLost(network);
            Log.i("ConnectionManager", "onLost");
        }
    }

    /* renamed from: v2.d$j */
    public interface j extends C6389z.b {
        void onConnected();
    }

    /* renamed from: v2.d$k */
    public static class k {

        /* renamed from: a */
        public static final C6456d f21764a = new C6456d();
    }

    public C6456d() {
        this.f21731a = false;
        HandlerThread handlerThread = new HandlerThread("WiFi-Mobile");
        handlerThread.start();
        this.f21744n = new Handler(handlerThread.getLooper());
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        Globals.m7388i0().getApplicationContext().registerReceiver(this.f21746p, intentFilter);
        this.f21732b = (ConnectivityManager) Globals.m7388i0().getApplicationContext().getSystemService("connectivity");
        this.f21733c = (WifiManager) Globals.m7388i0().getApplicationContext().getSystemService("wifi");
        this.f21734d = (TelephonyManager) Globals.m7388i0().getApplicationContext().getSystemService("phone");
        IntentFilter intentFilter2 = new IntentFilter();
        intentFilter2.addAction("android.intent.action.SCREEN_OFF");
        intentFilter2.addAction("android.intent.action.SCREEN_ON");
        Globals.m7388i0().getApplicationContext().registerReceiver(this.f21747q, intentFilter2);
        IntentFilter intentFilter3 = new IntentFilter();
        intentFilter3.addAction("com.sonymobile.SUPER_STAMINA");
        Globals.m7388i0().getApplicationContext().registerReceiver(this.f21748r, intentFilter3, 2);
        IntentFilter intentFilter4 = new IntentFilter();
        intentFilter4.addAction("android.net.wifi.supplicant.STATE_CHANGE");
        Globals.m7388i0().getApplicationContext().registerReceiver(this.f21749s, intentFilter4);
        IntentFilter intentFilter5 = new IntentFilter();
        intentFilter5.addAction("android.intent.action.TIME_SET");
        intentFilter5.addAction("android.intent.action.DATE_CHANGED");
        intentFilter5.addAction("android.intent.action.TIMEZONE_CHANGED");
        Globals.m7388i0().getApplicationContext().registerReceiver(this.f21750t, intentFilter5);
        this.f21731a = m24748G();
        XMPPManager.m14184g0();
    }

    /* renamed from: D */
    public static C6456d m24714D() {
        return k.f21764a;
    }

    /* renamed from: H */
    public static boolean m24715H() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Socket socket = new Socket();
        try {
            try {
                socket.connect(new InetSocketAddress(InetAddress.getByName("8.8.8.8"), 53), 5000);
                C6370g.m24480b(socket);
                return true;
            } catch (Exception e9) {
                Log.e("ConnectionManager", e9.toString());
                C6370g.m24480b(socket);
                return false;
            }
        } catch (Throwable th) {
            C6370g.m24480b(socket);
            throw th;
        }
    }

    @TargetApi(23)
    /* renamed from: N */
    public static void m24716N(ConnectivityManager connectivityManager, TelephonyManager telephonyManager) {
        if (Build.VERSION.RELEASE.equals("6.0") || telephonyManager == null || telephonyManager.getSimState() != 5 || Settings.Global.getInt(Globals.m7388i0().getApplicationContext().getContentResolver(), "mobile_data", 1) != 1) {
            return;
        }
        try {
            Log.i("ConnectionManager", "Request Mobile Network");
            NetworkRequest.Builder builder = new NetworkRequest.Builder();
            builder.addCapability(12).addTransportType(0);
            connectivityManager.requestNetwork(builder.build(), new i(connectivityManager));
        } catch (Exception e9) {
            Log.e("ConnectionManager", e9.toString());
        }
    }

    /* renamed from: A */
    public void m24743A(String str) {
        if (str == null || str.isEmpty()) {
            return;
        }
        synchronized (this.f21738h) {
            if (!this.f21739i.contains(str)) {
                this.f21739i.add(str);
            }
            ULogUtility.m16670f("ConnectionManager", "Acquire Connection " + this.f21739i);
            m24754O(true);
            m24755P();
        }
    }

    /* renamed from: B */
    public void m24744B(j jVar) {
        this.f21751u.m24539c(jVar);
    }

    /* renamed from: C */
    public void m24745C() {
        synchronized (this.f21738h) {
            this.f21739i.clear();
            Log.d("ConnectionManager", "Force Release Connection");
            if (this.f21739i.isEmpty()) {
                XMPPManager.f12475D = false;
                try {
                    XMPPManager.m14184g0().m14225V();
                } catch (NetworkOnMainThreadException unused) {
                    Log.e("ConnectionManager", "NetworkOnMainThreadException. It should be call in background thread. ");
                }
            }
        }
    }

    /* renamed from: E */
    public String m24746E() {
        String typeName;
        String networkOperatorName;
        WifiInfo connectionInfo;
        String ssid;
        NetworkInfo activeNetworkInfo = this.f21732b.getActiveNetworkInfo();
        if (activeNetworkInfo == null || (typeName = activeNetworkInfo.getTypeName()) == null || typeName.isEmpty()) {
            return null;
        }
        if (typeName.toUpperCase(Locale.US).equals("WIFI")) {
            WifiManager wifiManager = this.f21733c;
            if (wifiManager == null || (connectionInfo = wifiManager.getConnectionInfo()) == null || (ssid = connectionInfo.getSSID()) == null || ssid.isEmpty()) {
                return typeName;
            }
            return typeName + StringUtils.SPACE + ssid + StringUtils.SPACE + connectionInfo.getLinkSpeed() + "Mbps";
        }
        String subtypeName = activeNetworkInfo.getSubtypeName();
        if (subtypeName != null && !subtypeName.isEmpty()) {
            typeName = typeName + StringUtils.SPACE + subtypeName;
        }
        TelephonyManager telephonyManager = this.f21734d;
        if (telephonyManager == null || (networkOperatorName = telephonyManager.getNetworkOperatorName()) == null || networkOperatorName.isEmpty()) {
            return typeName;
        }
        return typeName + StringUtils.SPACE + networkOperatorName;
    }

    /* renamed from: F */
    public final boolean m24747F() {
        boolean z8;
        synchronized (this.f21738h) {
            z8 = !this.f21739i.isEmpty();
        }
        return z8;
    }

    /* renamed from: G */
    public boolean m24748G() {
        ConnectivityManager connectivityManager = this.f21732b;
        return (connectivityManager == null || connectivityManager.getActiveNetworkInfo() == null) ? false : true;
    }

    /* renamed from: I */
    public final void m24749I() {
        this.f21751u.m24540f(new g());
    }

    /* renamed from: J */
    public final void m24750J() {
        if (this.f21745o.getAndSet(true)) {
            return;
        }
        new a().start();
    }

    /* renamed from: K */
    public void m24751K(String str) {
        if (str == null || str.isEmpty()) {
            return;
        }
        synchronized (this.f21738h) {
            if (this.f21739i.contains(str)) {
                this.f21739i.remove(str);
            } else {
                Log.w("ConnectionManager", str + " not acquired");
            }
            ULogUtility.m16670f("ConnectionManager", "Release Connection " + this.f21739i);
            if (this.f21739i.isEmpty()) {
                m24754O(false);
                XMPPManager.f12475D = false;
                XMPPManager.m14184g0().m14223U();
            }
        }
    }

    @TargetApi(23)
    /* renamed from: L */
    public final void m24752L() {
        if (Build.VERSION.RELEASE.equals("6.0") || this.f21732b.getBoundNetworkForProcess() == null) {
            return;
        }
        Log.i("ConnectionManager", "releaseMobileNetwork");
        this.f21732b.bindProcessToNetwork(null);
    }

    /* renamed from: M */
    public void m24753M(j jVar) {
        this.f21751u.m24541g(jVar);
    }

    /* renamed from: O */
    public final void m24754O(boolean z8) {
        this.f21743m = z8;
        this.f21744n.removeCallbacks(this.f21753w);
        if (!this.f21743m || Build.VERSION.RELEASE.equals("6.0")) {
            return;
        }
        this.f21744n.postDelayed(this.f21753w, 15000L);
    }

    /* renamed from: P */
    public void m24755P() {
        if (Globals.m7388i0().m7464O1() && !XMPPManager.m14184g0().m14204A0()) {
            XMPPManager.f12475D = true;
            m24750J();
        }
    }
}
