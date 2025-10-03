package p256z4;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import java.security.GeneralSecurityException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import p006a5.C0035c;
import p006a5.InterfaceC0036d;

/* renamed from: z4.b */
/* loaded from: classes2.dex */
public class C6826b {

    /* renamed from: t */
    public static boolean f22664t = false;

    /* renamed from: u */
    public static C6826b f22665u;

    /* renamed from: v */
    public static final Object f22666v = new Object();

    /* renamed from: a */
    public final int f22667a;

    /* renamed from: b */
    public final int f22668b;

    /* renamed from: c */
    public final boolean f22669c;

    /* renamed from: d */
    public final long f22670d;

    /* renamed from: e */
    public final int f22671e;

    /* renamed from: f */
    public int f22672f;

    /* renamed from: g */
    public final boolean f22673g;

    /* renamed from: h */
    public final boolean f22674h;

    /* renamed from: i */
    public boolean f22675i = true;

    /* renamed from: j */
    public String f22676j;

    /* renamed from: k */
    public String f22677k;

    /* renamed from: l */
    public String f22678l;

    /* renamed from: m */
    public int f22679m;

    /* renamed from: n */
    public final String f22680n;

    /* renamed from: o */
    public final int f22681o;

    /* renamed from: p */
    public final int f22682p;

    /* renamed from: q */
    public boolean f22683q;

    /* renamed from: r */
    public final boolean f22684r;

    /* renamed from: s */
    public SSLSocketFactory f22685s;

    public C6826b(Bundle bundle, Context context) throws NoSuchAlgorithmException, KeyManagementException {
        long jFloatValue;
        SSLSocketFactory socketFactory = null;
        try {
            SSLContext sSLContext = SSLContext.getInstance("TLS");
            sSLContext.init(null, null, null);
            socketFactory = sSLContext.getSocketFactory();
        } catch (GeneralSecurityException e9) {
            C0035c.m144f("MixpanelAPI.Conf", "System has no SSL support. Built-in events editor will not be available", e9);
        }
        this.f22685s = socketFactory;
        boolean z8 = bundle.getBoolean("com.mixpanel.android.MPConfig.EnableDebugLogging", false);
        f22664t = z8;
        if (z8) {
            C0035c.m145g(2);
        }
        if (bundle.containsKey("com.mixpanel.android.MPConfig.DebugFlushInterval")) {
            C0035c.m149k("MixpanelAPI.Conf", "We do not support com.mixpanel.android.MPConfig.DebugFlushInterval anymore. There will only be one flush interval. Please, update your AndroidManifest.xml.");
        }
        this.f22667a = bundle.getInt("com.mixpanel.android.MPConfig.BulkUploadLimit", 40);
        this.f22668b = bundle.getInt("com.mixpanel.android.MPConfig.FlushInterval", 60000);
        this.f22679m = bundle.getInt("com.mixpanel.android.MPConfig.FlushBatchSize", 50);
        this.f22669c = bundle.getBoolean("com.mixpanel.android.MPConfig.FlushOnBackground", true);
        this.f22671e = bundle.getInt("com.mixpanel.android.MPConfig.MinimumDatabaseLimit", 20971520);
        this.f22672f = bundle.getInt("com.mixpanel.android.MPConfig.MaximumDatabaseLimit", Integer.MAX_VALUE);
        this.f22680n = bundle.getString("com.mixpanel.android.MPConfig.ResourcePackageName");
        this.f22673g = bundle.getBoolean("com.mixpanel.android.MPConfig.DisableAppOpenEvent", true);
        this.f22674h = bundle.getBoolean("com.mixpanel.android.MPConfig.DisableExceptionHandler", false);
        this.f22681o = bundle.getInt("com.mixpanel.android.MPConfig.MinimumSessionDuration", 10000);
        this.f22682p = bundle.getInt("com.mixpanel.android.MPConfig.SessionTimeoutDuration", Integer.MAX_VALUE);
        this.f22683q = bundle.getBoolean("com.mixpanel.android.MPConfig.UseIpAddressForGeolocation", true);
        this.f22684r = bundle.getBoolean("com.mixpanel.android.MPConfig.RemoveLegacyResidualFiles", false);
        Object obj = bundle.get("com.mixpanel.android.MPConfig.DataExpiration");
        if (obj != null) {
            try {
                if (obj instanceof Integer) {
                    jFloatValue = ((Integer) obj).intValue();
                } else {
                    if (!(obj instanceof Float)) {
                        throw new NumberFormatException(obj.toString() + " is not a number.");
                    }
                    jFloatValue = (long) ((Float) obj).floatValue();
                }
            } catch (Exception e10) {
                C0035c.m142d("MixpanelAPI.Conf", "Error parsing com.mixpanel.android.MPConfig.DataExpiration meta-data value", e10);
            }
        } else {
            jFloatValue = 432000000;
        }
        this.f22670d = jFloatValue;
        boolean zContainsKey = true ^ bundle.containsKey("com.mixpanel.android.MPConfig.UseIpAddressForGeolocation");
        String string = bundle.getString("com.mixpanel.android.MPConfig.EventsEndpoint");
        if (string != null) {
            m25489w(zContainsKey ? string : m25473e(string, m25488u()));
        } else {
            m25490x("https://api.mixpanel.com");
        }
        String string2 = bundle.getString("com.mixpanel.android.MPConfig.PeopleEndpoint");
        if (string2 != null) {
            m25467A(zContainsKey ? string2 : m25473e(string2, m25488u()));
        } else {
            m25468B("https://api.mixpanel.com");
        }
        String string3 = bundle.getString("com.mixpanel.android.MPConfig.GroupsEndpoint");
        if (string3 != null) {
            m25491y(zContainsKey ? string3 : m25473e(string3, m25488u()));
        } else {
            m25492z("https://api.mixpanel.com");
        }
        C0035c.m147i("MixpanelAPI.Conf", toString());
    }

    /* renamed from: k */
    public static C6826b m25465k(Context context) {
        synchronized (f22666v) {
            if (f22665u == null) {
                f22665u = m25466v(context.getApplicationContext());
            }
        }
        return f22665u;
    }

    /* renamed from: v */
    public static C6826b m25466v(Context context) {
        String packageName = context.getPackageName();
        try {
            Bundle bundle = context.getPackageManager().getApplicationInfo(packageName, 128).metaData;
            if (bundle == null) {
                bundle = new Bundle();
            }
            return new C6826b(bundle, context);
        } catch (PackageManager.NameNotFoundException e9) {
            throw new RuntimeException("Can't configure Mixpanel with package name " + packageName, e9);
        }
    }

    /* renamed from: A */
    public final void m25467A(String str) {
        this.f22677k = str;
    }

    /* renamed from: B */
    public final void m25468B(String str) {
        m25467A(m25473e(str + "/engage/", m25488u()));
    }

    /* renamed from: a */
    public int m25469a() {
        return this.f22667a;
    }

    /* renamed from: b */
    public long m25470b() {
        return this.f22670d;
    }

    /* renamed from: c */
    public boolean m25471c() {
        return this.f22673g;
    }

    /* renamed from: d */
    public boolean m25472d() {
        return this.f22674h;
    }

    /* renamed from: e */
    public final String m25473e(String str, boolean z8) {
        if (str.contains("?ip=")) {
            StringBuilder sb = new StringBuilder();
            sb.append(str.substring(0, str.indexOf("?ip=")));
            sb.append("?ip=");
            sb.append(z8 ? "1" : "0");
            return sb.toString();
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append(str);
        sb2.append("?ip=");
        sb2.append(z8 ? "1" : "0");
        return sb2.toString();
    }

    /* renamed from: f */
    public String m25474f() {
        return this.f22676j;
    }

    /* renamed from: g */
    public int m25475g() {
        return this.f22679m;
    }

    /* renamed from: h */
    public int m25476h() {
        return this.f22668b;
    }

    /* renamed from: i */
    public boolean m25477i() {
        return this.f22669c;
    }

    /* renamed from: j */
    public String m25478j() {
        return this.f22678l;
    }

    /* renamed from: l */
    public int m25479l() {
        return this.f22672f;
    }

    /* renamed from: m */
    public int m25480m() {
        return this.f22671e;
    }

    /* renamed from: n */
    public int m25481n() {
        return this.f22681o;
    }

    /* renamed from: o */
    public synchronized InterfaceC0036d m25482o() {
        return null;
    }

    /* renamed from: p */
    public String m25483p() {
        return this.f22677k;
    }

    /* renamed from: q */
    public boolean m25484q() {
        return this.f22684r;
    }

    /* renamed from: r */
    public synchronized SSLSocketFactory m25485r() {
        return this.f22685s;
    }

    /* renamed from: s */
    public int m25486s() {
        return this.f22682p;
    }

    /* renamed from: t */
    public boolean m25487t() {
        return this.f22675i;
    }

    public String toString() {
        return "Mixpanel (7.3.2) configured with:\n    TrackAutomaticEvents: " + m25487t() + "\n    BulkUploadLimit " + m25469a() + "\n    FlushInterval " + m25476h() + "\n    FlushInterval " + m25475g() + "\n    DataExpiration " + m25470b() + "\n    MinimumDatabaseLimit " + m25480m() + "\n    MaximumDatabaseLimit " + m25479l() + "\n    DisableAppOpenEvent " + m25471c() + "\n    EnableDebugLogging " + f22664t + "\n    EventsEndpoint " + m25474f() + "\n    PeopleEndpoint " + m25483p() + "\n    MinimumSessionDuration: " + m25481n() + "\n    SessionTimeoutDuration: " + m25486s() + "\n    DisableExceptionHandler: " + m25472d() + "\n    FlushOnBackground: " + m25477i();
    }

    /* renamed from: u */
    public final boolean m25488u() {
        return this.f22683q;
    }

    /* renamed from: w */
    public final void m25489w(String str) {
        this.f22676j = str;
    }

    /* renamed from: x */
    public final void m25490x(String str) {
        m25489w(m25473e(str + "/track/", m25488u()));
    }

    /* renamed from: y */
    public final void m25491y(String str) {
        this.f22678l = str;
    }

    /* renamed from: z */
    public final void m25492z(String str) {
        m25491y(m25473e(str + "/groups/", m25488u()));
    }
}
