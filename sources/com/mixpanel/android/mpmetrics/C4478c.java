package com.mixpanel.android.mpmetrics;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import com.mixpanel.android.mpmetrics.C4476a;
import com.mixpanel.android.mpmetrics.C4480e;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.Future;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import p006a5.C0035c;
import p112k0.C5111a;
import p256z4.C6825a;
import p256z4.C6826b;
import p256z4.C6827c;
import p256z4.C6828d;
import p256z4.C6829e;

/* renamed from: com.mixpanel.android.mpmetrics.c */
/* loaded from: classes2.dex */
public class C4478c {

    /* renamed from: m */
    public static final Map<String, Map<Context, C4478c>> f15765m = new HashMap();

    /* renamed from: n */
    public static final C4480e f15766n = new C4480e();

    /* renamed from: o */
    public static Future<SharedPreferences> f15767o;

    /* renamed from: a */
    public final Context f15768a;

    /* renamed from: b */
    public final C4476a f15769b;

    /* renamed from: c */
    public final C6826b f15770c;

    /* renamed from: d */
    public final Boolean f15771d;

    /* renamed from: e */
    public final String f15772e;

    /* renamed from: f */
    public final e f15773f;

    /* renamed from: g */
    public final Map<String, Object> f15774g;

    /* renamed from: h */
    public final C6828d f15775h;

    /* renamed from: i */
    public final Map<String, String> f15776i;

    /* renamed from: j */
    public final Map<String, Long> f15777j;

    /* renamed from: k */
    public C4479d f15778k;

    /* renamed from: l */
    public final C6829e f15779l;

    /* renamed from: com.mixpanel.android.mpmetrics.c$a */
    public class a implements C4480e.b {
        public a() {
        }

        @Override // com.mixpanel.android.mpmetrics.C4480e.b
        /* renamed from: a */
        public void mo17967a(SharedPreferences sharedPreferences) {
            String strM25497n = C6828d.m25497n(sharedPreferences);
            if (strM25497n != null) {
                C4478c.this.m17964w(strM25497n);
            }
        }
    }

    /* renamed from: com.mixpanel.android.mpmetrics.c$b */
    public class b extends BroadcastReceiver {
        public b() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) throws JSONException {
            JSONObject jSONObject = new JSONObject();
            Bundle bundleExtra = intent.getBundleExtra("event_args");
            if (bundleExtra != null) {
                for (String str : bundleExtra.keySet()) {
                    try {
                        jSONObject.put(str, bundleExtra.get(str));
                    } catch (JSONException e9) {
                        C0035c.m142d("MixpanelAPI.AL", "failed to add key \"" + str + "\" to properties for tracking bolts event", e9);
                    }
                }
            }
            C4478c.this.m17948D("$" + intent.getStringExtra("event_name"), jSONObject);
        }
    }

    /* renamed from: com.mixpanel.android.mpmetrics.c$c */
    public interface c {
        /* renamed from: a */
        void mo17934a(C4478c c4478c);
    }

    /* renamed from: com.mixpanel.android.mpmetrics.c$d */
    public interface d {
        /* renamed from: a */
        void mo17968a();

        /* renamed from: b */
        boolean mo17969b();

        /* renamed from: c */
        void mo17970c(String str, double d9);

        /* renamed from: d */
        void mo17971d();
    }

    /* renamed from: com.mixpanel.android.mpmetrics.c$e */
    public class e implements d {
        public e() {
        }

        @Override // com.mixpanel.android.mpmetrics.C4478c.d
        /* renamed from: a */
        public void mo17968a() {
            m17975h("$transactions");
        }

        @Override // com.mixpanel.android.mpmetrics.C4478c.d
        /* renamed from: b */
        public boolean mo17969b() {
            return m17972e() != null;
        }

        @Override // com.mixpanel.android.mpmetrics.C4478c.d
        /* renamed from: c */
        public void mo17970c(String str, double d9) {
            if (C4478c.this.m17960s()) {
                return;
            }
            HashMap map = new HashMap();
            map.put(str, Double.valueOf(d9));
            m17973f(map);
        }

        @Override // com.mixpanel.android.mpmetrics.C4478c.d
        /* renamed from: d */
        public void mo17971d() {
            try {
                C4478c.this.m17965x(m17974g("$delete", JSONObject.NULL));
            } catch (JSONException unused) {
                C0035c.m141c("MixpanelAPI.API", "Exception deleting a user");
            }
        }

        /* renamed from: e */
        public String m17972e() {
            return C4478c.this.f15775h.m25514m();
        }

        /* renamed from: f */
        public void m17973f(Map<String, ? extends Number> map) {
            if (C4478c.this.m17960s()) {
                return;
            }
            try {
                C4478c.this.m17965x(m17974g("$add", new JSONObject(map)));
            } catch (JSONException e9) {
                C0035c.m142d("MixpanelAPI.API", "Exception incrementing properties", e9);
            }
        }

        /* renamed from: g */
        public final JSONObject m17974g(String str, Object obj) throws JSONException {
            JSONObject jSONObject = new JSONObject();
            String strM17972e = m17972e();
            String strM17953j = C4478c.this.m17953j();
            jSONObject.put(str, obj);
            jSONObject.put("$token", C4478c.this.f15772e);
            jSONObject.put("$time", System.currentTimeMillis());
            jSONObject.put("$had_persisted_distinct_id", C4478c.this.f15775h.m25512k());
            if (strM17953j != null) {
                jSONObject.put("$device_id", strM17953j);
            }
            if (strM17972e != null) {
                jSONObject.put("$distinct_id", strM17972e);
                jSONObject.put("$user_id", strM17972e);
            }
            jSONObject.put("$mp_metadata", C4478c.this.f15779l.m25528b());
            return jSONObject;
        }

        /* renamed from: h */
        public void m17975h(String str) {
            if (C4478c.this.m17960s()) {
                return;
            }
            try {
                JSONArray jSONArray = new JSONArray();
                jSONArray.put(str);
                C4478c.this.m17965x(m17974g("$unset", jSONArray));
            } catch (JSONException e9) {
                C0035c.m142d("MixpanelAPI.API", "Exception unsetting a property", e9);
            }
        }

        public /* synthetic */ e(C4478c c4478c, C6827c c6827c) {
            this();
        }
    }

    public C4478c(Context context, Future<SharedPreferences> future, String str, boolean z8, JSONObject jSONObject, String str2, boolean z9) {
        this(context, future, str, C6826b.m25465k(context), z8, jSONObject, str2, z9);
    }

    /* renamed from: f */
    public static void m17940f(c cVar) {
        Map<String, Map<Context, C4478c>> map = f15765m;
        synchronized (map) {
            Iterator<Map<Context, C4478c>> it = map.values().iterator();
            while (it.hasNext()) {
                Iterator<C4478c> it2 = it.next().values().iterator();
                while (it2.hasNext()) {
                    cVar.mo17934a(it2.next());
                }
            }
        }
    }

    /* renamed from: g */
    public static void m17941g(Context context) throws IllegalAccessException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException {
        if (!(context instanceof Activity)) {
            C0035c.m139a("MixpanelAPI.AL", "Context is not an instance of Activity. To detect inbound App Links, pass an instance of an Activity to getInstance.");
            return;
        }
        try {
            Class.forName("bolts.AppLinks").getMethod("getTargetUrlFromInboundIntent", Context.class, Intent.class).invoke(null, context, ((Activity) context).getIntent());
        } catch (ClassNotFoundException e9) {
            C0035c.m139a("MixpanelAPI.AL", "Please install the Bolts library >= 1.1.2 to track App Links: " + e9.getMessage());
        } catch (IllegalAccessException e10) {
            C0035c.m139a("MixpanelAPI.AL", "Unable to detect inbound App Links: " + e10.getMessage());
        } catch (NoSuchMethodException e11) {
            C0035c.m139a("MixpanelAPI.AL", "Please install the Bolts library >= 1.1.2 to track App Links: " + e11.getMessage());
        } catch (InvocationTargetException e12) {
            C0035c.m140b("MixpanelAPI.AL", "Failed to invoke bolts.AppLinks.getTargetUrlFromInboundIntent() -- Unable to detect inbound App Links", e12);
        }
    }

    /* renamed from: l */
    public static C4478c m17942l(Context context, String str, boolean z8) {
        return m17943m(context, str, false, null, null, z8);
    }

    /* renamed from: m */
    public static C4478c m17943m(Context context, String str, boolean z8, JSONObject jSONObject, String str2, boolean z9) {
        C4478c c4478c;
        if (str == null || context == null) {
            return null;
        }
        Map<String, Map<Context, C4478c>> map = f15765m;
        synchronized (map) {
            Context applicationContext = context.getApplicationContext();
            if (f15767o == null) {
                f15767o = f15766n.m17982a(context, "com.mixpanel.android.mpmetrics.ReferralInfo", null);
            }
            String str3 = str2 != null ? str2 : str;
            Map<Context, C4478c> map2 = map.get(str3);
            if (map2 == null) {
                map2 = new HashMap<>();
                map.put(str3, map2);
            }
            Map<Context, C4478c> map3 = map2;
            c4478c = map3.get(applicationContext);
            if (c4478c == null && C6825a.m25464a(applicationContext)) {
                C4478c c4478c2 = new C4478c(applicationContext, f15767o, str, z8, jSONObject, str2, z9);
                m17944y(context, c4478c2);
                map3.put(applicationContext, c4478c2);
                c4478c = c4478c2;
            }
            m17941g(context);
        }
        return c4478c;
    }

    /* renamed from: y */
    public static void m17944y(Context context, C4478c c4478c) throws IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
        try {
            Object obj = C5111a.f17576f;
            C5111a.class.getMethod("registerReceiver", BroadcastReceiver.class, IntentFilter.class).invoke(C5111a.class.getMethod("getInstance", Context.class).invoke(null, context), c4478c.new b(), new IntentFilter("com.parse.bolts.measurement_event"));
        } catch (ClassNotFoundException e9) {
            C0035c.m139a("MixpanelAPI.AL", "To enable App Links tracking, add implementation 'androidx.localbroadcastmanager:localbroadcastmanager:1.0.0': " + e9.getMessage());
        } catch (IllegalAccessException e10) {
            C0035c.m139a("MixpanelAPI.AL", "App Links tracking will not be enabled due to this exception: " + e10.getMessage());
        } catch (NoSuchMethodException e11) {
            C0035c.m139a("MixpanelAPI.AL", "To enable App Links tracking, add implementation 'androidx.localbroadcastmanager:localbroadcastmanager:1.0.0': " + e11.getMessage());
        } catch (InvocationTargetException e12) {
            C0035c.m140b("MixpanelAPI.AL", "Failed to invoke LocalBroadcastManager.registerReceiver() -- App Links tracking will not be enabled due to this exception", e12);
        }
    }

    /* renamed from: A */
    public void m17945A(JSONObject jSONObject) {
        if (m17960s()) {
            return;
        }
        this.f15775h.m25526z(jSONObject);
    }

    /* renamed from: B */
    public boolean m17946B() {
        return !this.f15770c.m25471c();
    }

    /* renamed from: C */
    public final void m17947C(String str, String str2, String str3, JSONObject jSONObject, boolean z8) throws JSONException {
        String str4;
        String str5;
        JSONObject jSONObjectM17957p = m17957p();
        String str6 = null;
        if (jSONObjectM17957p != null) {
            try {
                str4 = (String) jSONObjectM17957p.get("mp_lib");
                try {
                    str5 = (String) jSONObjectM17957p.get("$lib_version");
                    str6 = str4;
                } catch (JSONException unused) {
                }
            } catch (JSONException unused2) {
                str4 = null;
            }
        } else {
            str5 = null;
        }
        str4 = str6;
        str6 = str5;
        JSONObject jSONObject2 = new JSONObject();
        if (str4 == null) {
            str4 = "Android";
        }
        jSONObject2.put("mp_lib", str4);
        jSONObject2.put("distinct_id", str3);
        if (str6 == null) {
            str6 = "7.3.2";
        }
        jSONObject2.put("$lib_version", str6);
        jSONObject2.put("Project Token", str3);
        if (jSONObject != null) {
            Iterator<String> itKeys = jSONObject.keys();
            while (itKeys.hasNext()) {
                String next = itKeys.next();
                jSONObject2.put(next, jSONObject.get(next));
            }
        }
        this.f15769b.m17902e(new C4476a.a(str, jSONObject2, str2));
        if (z8) {
            JSONObject jSONObject3 = new JSONObject();
            JSONObject jSONObject4 = new JSONObject();
            jSONObject4.put(str, 1);
            jSONObject3.put("$add", jSONObject4);
            jSONObject3.put("$token", str2);
            jSONObject3.put("$distinct_id", str3);
            this.f15769b.m17908l(new C4476a.e(jSONObject3, str2));
        }
        this.f15769b.m17909m(new C4476a.c(str2));
    }

    /* renamed from: D */
    public void m17948D(String str, JSONObject jSONObject) {
        if (m17960s()) {
            return;
        }
        m17949E(str, jSONObject, false);
    }

    /* renamed from: E */
    public void m17949E(String str, JSONObject jSONObject, boolean z8) {
        Long l9;
        if (m17960s()) {
            return;
        }
        if (!z8 || this.f15771d.booleanValue()) {
            synchronized (this.f15777j) {
                l9 = this.f15777j.get(str);
                this.f15777j.remove(str);
                this.f15775h.m25498A(str);
            }
            try {
                JSONObject jSONObject2 = new JSONObject();
                for (Map.Entry<String, String> entry : this.f15775h.m25515o().entrySet()) {
                    jSONObject2.put(entry.getKey(), entry.getValue());
                }
                this.f15775h.m25505d(jSONObject2);
                double dCurrentTimeMillis = System.currentTimeMillis() / 1000.0d;
                String strM17954k = m17954k();
                String strM17953j = m17953j();
                String strM17959r = m17959r();
                jSONObject2.put("time", System.currentTimeMillis());
                jSONObject2.put("distinct_id", strM17954k);
                jSONObject2.put("$had_persisted_distinct_id", this.f15775h.m25512k());
                if (strM17953j != null) {
                    jSONObject2.put("$device_id", strM17953j);
                }
                if (strM17959r != null) {
                    jSONObject2.put("$user_id", strM17959r);
                }
                if (l9 != null) {
                    jSONObject2.put("$duration", dCurrentTimeMillis - (l9.longValue() / 1000.0d));
                }
                if (jSONObject != null) {
                    Iterator<String> itKeys = jSONObject.keys();
                    while (itKeys.hasNext()) {
                        String next = itKeys.next();
                        jSONObject2.put(next, jSONObject.opt(next));
                    }
                }
                this.f15769b.m17902e(new C4476a.a(str, jSONObject2, this.f15772e, z8, this.f15779l.m25527a()));
            } catch (JSONException e9) {
                C0035c.m142d("MixpanelAPI.API", "Exception tracking event " + str, e9);
            }
        }
    }

    /* renamed from: F */
    public void m17950F(String str, Map<String, Object> map) {
        if (m17960s()) {
            return;
        }
        if (map == null) {
            m17948D(str, null);
            return;
        }
        try {
            m17948D(str, new JSONObject(map));
        } catch (NullPointerException unused) {
            C0035c.m149k("MixpanelAPI.API", "Can't have null keys in the properties of trackMap!");
        }
    }

    /* renamed from: h */
    public void m17951h() {
        if (m17960s()) {
            return;
        }
        this.f15769b.m17909m(new C4476a.c(this.f15772e));
    }

    /* renamed from: i */
    public C4476a m17952i() {
        return C4476a.m17899g(this.f15768a);
    }

    /* renamed from: j */
    public String m17953j() {
        return this.f15775h.m25509h();
    }

    /* renamed from: k */
    public String m17954k() {
        return this.f15775h.m25510i();
    }

    /* renamed from: n */
    public d m17955n() {
        return this.f15773f;
    }

    /* renamed from: o */
    public C6828d m17956o(Context context, Future<SharedPreferences> future, String str, String str2) {
        a aVar = new a();
        if (str2 != null) {
            str = str2;
        }
        C4480e c4480e = f15766n;
        return new C6828d(future, c4480e.m17982a(context, "com.mixpanel.android.mpmetrics.MixpanelAPI_" + str, aVar), c4480e.m17982a(context, "com.mixpanel.android.mpmetrics.MixpanelAPI.TimeEvents_" + str, null), c4480e.m17982a(context, "com.mixpanel.android.mpmetrics.Mixpanel", null));
    }

    /* renamed from: p */
    public JSONObject m17957p() {
        JSONObject jSONObject = new JSONObject();
        this.f15775h.m25505d(jSONObject);
        return jSONObject;
    }

    /* renamed from: q */
    public Boolean m17958q() {
        return this.f15771d;
    }

    /* renamed from: r */
    public String m17959r() {
        return this.f15775h.m25511j();
    }

    /* renamed from: s */
    public boolean m17960s() {
        return this.f15775h.m25513l(this.f15772e);
    }

    /* renamed from: t */
    public void m17961t() {
        if (this.f15770c.m25477i()) {
            m17951h();
        }
    }

    /* renamed from: u */
    public void m17962u() {
        this.f15779l.m25530d();
    }

    /* renamed from: v */
    public void m17963v() {
        m17952i().m17901d(new C4476a.c(this.f15772e));
        if (m17955n().mo17969b()) {
            m17955n().mo17971d();
            m17955n().mo17968a();
        }
        this.f15775h.m25506e();
        synchronized (this.f15777j) {
            this.f15777j.clear();
            this.f15775h.m25508g();
        }
        this.f15775h.m25507f();
        this.f15775h.m25501D(true, this.f15772e);
    }

    /* renamed from: w */
    public final void m17964w(String str) {
        this.f15769b.m17910n(new C4476a.f(str, this.f15772e));
    }

    /* renamed from: x */
    public final void m17965x(JSONObject jSONObject) {
        if (m17960s()) {
            return;
        }
        this.f15769b.m17908l(new C4476a.e(jSONObject, this.f15772e));
    }

    @TargetApi(14)
    /* renamed from: z */
    public void m17966z() {
        if (!(this.f15768a.getApplicationContext() instanceof Application)) {
            C0035c.m143e("MixpanelAPI.API", "Context is not an Application, Mixpanel won't be able to automatically flush on an app background.");
            return;
        }
        Application application = (Application) this.f15768a.getApplicationContext();
        C4479d c4479d = new C4479d(this, this.f15770c);
        this.f15778k = c4479d;
        application.registerActivityLifecycleCallbacks(c4479d);
    }

    public C4478c(Context context, Future<SharedPreferences> future, String str, C6826b c6826b, boolean z8, JSONObject jSONObject, String str2, boolean z9) throws JSONException, PackageManager.NameNotFoundException {
        this.f15768a = context;
        this.f15772e = str;
        this.f15773f = new e(this, null);
        this.f15774g = new HashMap();
        this.f15770c = c6826b;
        this.f15771d = Boolean.valueOf(z9);
        HashMap map = new HashMap();
        map.put("$android_lib_version", "7.3.2");
        map.put("$android_os", "Android");
        String str3 = Build.VERSION.RELEASE;
        map.put("$android_os_version", str3 == null ? "UNKNOWN" : str3);
        String str4 = Build.MANUFACTURER;
        map.put("$android_manufacturer", str4 == null ? "UNKNOWN" : str4);
        String str5 = Build.BRAND;
        map.put("$android_brand", str5 == null ? "UNKNOWN" : str5);
        String str6 = Build.MODEL;
        map.put("$android_model", str6 != null ? str6 : "UNKNOWN");
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            map.put("$android_app_version", packageInfo.versionName);
            map.put("$android_app_version_code", Integer.toString(packageInfo.versionCode));
        } catch (PackageManager.NameNotFoundException e9) {
            C0035c.m142d("MixpanelAPI.API", "Exception getting app version name", e9);
        }
        this.f15776i = Collections.unmodifiableMap(map);
        this.f15779l = new C6829e();
        this.f15769b = m17952i();
        C6828d c6828dM17956o = m17956o(context, future, str, str2);
        this.f15775h = c6828dM17956o;
        this.f15777j = c6828dM17956o.m25517q();
        if (z8 && (m17960s() || !c6828dM17956o.m25518r(str))) {
            m17963v();
        }
        if (jSONObject != null) {
            m17945A(jSONObject);
        }
        boolean zExists = MPDbAdapter.m17878q(this.f15768a).m17886p().exists();
        m17966z();
        if (c6828dM17956o.m25520t(zExists, this.f15772e) && this.f15771d.booleanValue()) {
            m17949E("$ae_first_open", null, true);
            c6828dM17956o.m25499B(this.f15772e);
        }
        if (m17946B() && this.f15771d.booleanValue()) {
            m17948D("$app_open", null);
        }
        if (!c6828dM17956o.m25519s(this.f15772e) && !z8 && !m17960s()) {
            try {
                m17947C("Integration", "85053bf24bba75239b16a601d9387e17", str, null, false);
                c6828dM17956o.m25500C(this.f15772e);
            } catch (JSONException unused) {
            }
        }
        if (this.f15775h.m25521u((String) map.get("$android_app_version_code")) && this.f15771d.booleanValue()) {
            try {
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("$ae_updated_version", map.get("$android_app_version"));
                m17949E("$ae_updated", jSONObject2, true);
            } catch (JSONException unused2) {
            }
        }
        if (!this.f15770c.m25472d()) {
            C4477b.m17932a();
        }
        if (this.f15770c.m25484q()) {
            this.f15769b.m17911o(new File(this.f15768a.getApplicationInfo().dataDir));
        }
    }
}
