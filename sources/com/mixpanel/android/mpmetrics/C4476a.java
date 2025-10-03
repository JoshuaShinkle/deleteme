package com.mixpanel.android.mpmetrics;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.util.DisplayMetrics;
import com.google.android.gms.auth.api.credentials.CredentialsApi;
import com.mixpanel.android.mpmetrics.MPDbAdapter;
import com.mixpanel.android.util.C4481a;
import com.mixpanel.android.util.RemoteService;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;
import p006a5.C0033a;
import p006a5.C0034b;
import p006a5.C0035c;
import p256z4.C6826b;
import p256z4.C6830f;

/* renamed from: com.mixpanel.android.mpmetrics.a */
/* loaded from: classes2.dex */
public class C4476a {

    /* renamed from: d */
    public static final Map<Context, C4476a> f15738d = new HashMap();

    /* renamed from: a */
    public final h f15739a = m17900c();

    /* renamed from: b */
    public final Context f15740b;

    /* renamed from: c */
    public final C6826b f15741c;

    /* renamed from: com.mixpanel.android.mpmetrics.a$a */
    public static class a extends d {

        /* renamed from: c */
        public final String f15742c;

        /* renamed from: d */
        public final JSONObject f15743d;

        /* renamed from: e */
        public final boolean f15744e;

        public a(String str, JSONObject jSONObject, String str2) {
            this(str, jSONObject, str2, false, new JSONObject());
        }

        /* renamed from: c */
        public String m17912c() {
            return this.f15742c;
        }

        /* renamed from: d */
        public JSONObject m17913d() {
            return m17916b();
        }

        /* renamed from: e */
        public JSONObject m17914e() {
            return this.f15743d;
        }

        public a(String str, JSONObject jSONObject, String str2, boolean z8, JSONObject jSONObject2) {
            super(str2, jSONObject);
            this.f15742c = str;
            this.f15744e = z8;
            this.f15743d = jSONObject2;
        }
    }

    /* renamed from: com.mixpanel.android.mpmetrics.a$b */
    public static class b extends d {
        public String toString() {
            return m17916b().toString();
        }
    }

    /* renamed from: com.mixpanel.android.mpmetrics.a$c */
    public static class c {

        /* renamed from: a */
        public final String f15745a;

        public c(String str) {
            this.f15745a = str;
        }

        /* renamed from: a */
        public String m17915a() {
            return this.f15745a;
        }
    }

    /* renamed from: com.mixpanel.android.mpmetrics.a$d */
    public static class d extends c {

        /* renamed from: b */
        public final JSONObject f15746b;

        public d(String str, JSONObject jSONObject) {
            super(str);
            if (jSONObject != null && jSONObject.length() > 0) {
                Iterator<String> itKeys = jSONObject.keys();
                while (itKeys.hasNext()) {
                    String next = itKeys.next();
                    try {
                        jSONObject.get(next).toString();
                    } catch (AssertionError e9) {
                        jSONObject.remove(next);
                        C0035c.m142d("MixpanelAPI.Messages", "Removing people profile property from update (see https://github.com/mixpanel/mixpanel-android/issues/567)", e9);
                    } catch (JSONException unused) {
                    }
                }
            }
            this.f15746b = jSONObject;
        }

        /* renamed from: b */
        public JSONObject m17916b() {
            return this.f15746b;
        }
    }

    /* renamed from: com.mixpanel.android.mpmetrics.a$e */
    public static class e extends d {
        public e(JSONObject jSONObject, String str) {
            super(str, jSONObject);
        }

        /* renamed from: c */
        public boolean m17917c() {
            return !m17916b().has("$distinct_id");
        }

        public String toString() {
            return m17916b().toString();
        }
    }

    /* renamed from: com.mixpanel.android.mpmetrics.a$f */
    public static class f extends c {

        /* renamed from: b */
        public final String f15747b;

        public f(String str, String str2) {
            super(str2);
            this.f15747b = str;
        }

        /* renamed from: b */
        public String m17918b() {
            return this.f15747b;
        }

        public String toString() {
            return this.f15747b;
        }
    }

    /* renamed from: com.mixpanel.android.mpmetrics.a$g */
    public static class g extends c {

        /* renamed from: b */
        public final Map<String, String> f15748b;

        /* renamed from: b */
        public Map<String, String> m17919b() {
            return this.f15748b;
        }
    }

    /* renamed from: com.mixpanel.android.mpmetrics.a$h */
    public class h {

        /* renamed from: f */
        public C6830f f15754f;

        /* renamed from: a */
        public final Object f15749a = new Object();

        /* renamed from: c */
        public long f15751c = 0;

        /* renamed from: d */
        public long f15752d = 0;

        /* renamed from: e */
        public long f15753e = -1;

        /* renamed from: b */
        public Handler f15750b = m17925f();

        /* renamed from: com.mixpanel.android.mpmetrics.a$h$a */
        public class a extends Handler {

            /* renamed from: a */
            public MPDbAdapter f15756a;

            /* renamed from: b */
            public final long f15757b;

            /* renamed from: c */
            public long f15758c;

            /* renamed from: d */
            public int f15759d;

            public a(Looper looper) {
                super(looper);
                this.f15756a = null;
                h.this.f15754f = C6830f.m25531f(C4476a.this.f15740b);
                this.f15757b = C4476a.this.f15741c.m25476h();
            }

            /* renamed from: a */
            public final JSONObject m17928a() throws JSONException {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("mp_lib", "android");
                jSONObject.put("$lib_version", "7.3.2");
                jSONObject.put("$os", "Android");
                String str = Build.VERSION.RELEASE;
                if (str == null) {
                    str = "UNKNOWN";
                }
                jSONObject.put("$os_version", str);
                String str2 = Build.MANUFACTURER;
                if (str2 == null) {
                    str2 = "UNKNOWN";
                }
                jSONObject.put("$manufacturer", str2);
                String str3 = Build.BRAND;
                if (str3 == null) {
                    str3 = "UNKNOWN";
                }
                jSONObject.put("$brand", str3);
                String str4 = Build.MODEL;
                jSONObject.put("$model", str4 != null ? str4 : "UNKNOWN");
                DisplayMetrics displayMetricsM25536e = h.this.f15754f.m25536e();
                jSONObject.put("$screen_dpi", displayMetricsM25536e.densityDpi);
                jSONObject.put("$screen_height", displayMetricsM25536e.heightPixels);
                jSONObject.put("$screen_width", displayMetricsM25536e.widthPixels);
                String strM25533b = h.this.f15754f.m25533b();
                if (strM25533b != null) {
                    jSONObject.put("$app_version", strM25533b);
                    jSONObject.put("$app_version_string", strM25533b);
                }
                Integer numM25532a = h.this.f15754f.m25532a();
                if (numM25532a != null) {
                    String strValueOf = String.valueOf(numM25532a);
                    jSONObject.put("$app_release", strValueOf);
                    jSONObject.put("$app_build_number", strValueOf);
                }
                Boolean boolValueOf = Boolean.valueOf(h.this.f15754f.m25537g());
                if (boolValueOf != null) {
                    jSONObject.put("$has_nfc", boolValueOf.booleanValue());
                }
                Boolean boolValueOf2 = Boolean.valueOf(h.this.f15754f.m25538h());
                if (boolValueOf2 != null) {
                    jSONObject.put("$has_telephone", boolValueOf2.booleanValue());
                }
                String strM25535d = h.this.f15754f.m25535d();
                if (strM25535d != null && !strM25535d.trim().isEmpty()) {
                    jSONObject.put("$carrier", strM25535d);
                }
                Boolean boolM25540j = h.this.f15754f.m25540j();
                if (boolM25540j != null) {
                    jSONObject.put("$wifi", boolM25540j.booleanValue());
                }
                Boolean boolM25539i = h.this.f15754f.m25539i();
                if (boolM25539i != null) {
                    jSONObject.put("$bluetooth_enabled", boolM25539i);
                }
                String strM25534c = h.this.f15754f.m25534c();
                if (strM25534c != null) {
                    jSONObject.put("$bluetooth_version", strM25534c);
                }
                return jSONObject;
            }

            /* renamed from: b */
            public final JSONObject m17929b(a aVar) throws JSONException {
                JSONObject jSONObject = new JSONObject();
                JSONObject jSONObjectM17913d = aVar.m17913d();
                JSONObject jSONObjectM17928a = m17928a();
                jSONObjectM17928a.put("token", aVar.m17915a());
                if (jSONObjectM17913d != null) {
                    Iterator<String> itKeys = jSONObjectM17913d.keys();
                    while (itKeys.hasNext()) {
                        String next = itKeys.next();
                        jSONObjectM17928a.put(next, jSONObjectM17913d.get(next));
                    }
                }
                jSONObject.put("event", aVar.m17912c());
                jSONObject.put("properties", jSONObjectM17928a);
                jSONObject.put("$mp_metadata", aVar.m17914e());
                return jSONObject;
            }

            /* renamed from: c */
            public final void m17930c(MPDbAdapter mPDbAdapter, String str) throws Throwable {
                RemoteService remoteServiceM17904h = C4476a.this.m17904h();
                C4476a c4476a = C4476a.this;
                Context context = c4476a.f15740b;
                c4476a.f15741c.m25482o();
                if (!remoteServiceM17904h.mo17986c(context, null)) {
                    C4476a.this.m17905i("Not flushing data to Mixpanel because the device is not connected to the internet.");
                    return;
                }
                m17931d(mPDbAdapter, str, MPDbAdapter.Table.EVENTS, C4476a.this.f15741c.m25474f());
                m17931d(mPDbAdapter, str, MPDbAdapter.Table.PEOPLE, C4476a.this.f15741c.m25483p());
                m17931d(mPDbAdapter, str, MPDbAdapter.Table.GROUPS, C4476a.this.f15741c.m25478j());
            }

            /* JADX WARN: Removed duplicated region for block: B:41:0x016a  */
            /* JADX WARN: Removed duplicated region for block: B:57:0x0187 A[SYNTHETIC] */
            /* renamed from: d */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public final void m17931d(MPDbAdapter mPDbAdapter, String str, MPDbAdapter.Table table, String str2) throws Throwable {
                RemoteService remoteServiceM17904h = C4476a.this.m17904h();
                String[] strArrM17885o = mPDbAdapter.m17885o(table, str);
                int i9 = 0;
                Integer numValueOf = strArrM17885o != null ? Integer.valueOf(strArrM17885o[2]) : 0;
                while (strArrM17885o != null && numValueOf.intValue() > 0) {
                    String str3 = strArrM17885o[i9];
                    String str4 = strArrM17885o[1];
                    String strM137c = C0033a.m137c(str4);
                    HashMap map = new HashMap();
                    map.put("data", strM137c);
                    if (C6826b.f22664t) {
                        map.put("verbose", "1");
                    }
                    try {
                        try {
                            byte[] bArrMo17984a = remoteServiceM17904h.mo17984a(str2, map, C4476a.this.f15741c.m25485r());
                            if (bArrMo17984a == null) {
                                try {
                                    C4476a.this.m17905i("Response was null, unexpected failure posting to " + str2 + ".");
                                } catch (OutOfMemoryError e9) {
                                    e = e9;
                                    C0035c.m142d("MixpanelAPI.Messages", "Out of memory when posting to " + str2 + ".", e);
                                    if (i9 != 0) {
                                    }
                                } catch (MalformedURLException e10) {
                                    e = e10;
                                    C0035c.m142d("MixpanelAPI.Messages", "Cannot interpret " + str2 + " as a URL.", e);
                                    if (i9 != 0) {
                                    }
                                }
                            } else {
                                try {
                                    String str5 = new String(bArrMo17984a, "UTF-8");
                                    if (this.f15759d > 0) {
                                        this.f15759d = i9;
                                        removeMessages(2, str);
                                    }
                                    C4476a.this.m17905i("Successfully posted to " + str2 + ": \n" + str4);
                                    C4476a c4476a = C4476a.this;
                                    StringBuilder sb = new StringBuilder();
                                    sb.append("Response was ");
                                    sb.append(str5);
                                    c4476a.m17905i(sb.toString());
                                    i9 = 1;
                                } catch (UnsupportedEncodingException e11) {
                                    throw new RuntimeException("UTF not supported on this platform?", e11);
                                }
                            }
                        } catch (OutOfMemoryError e12) {
                            e = e12;
                            i9 = 1;
                        } catch (MalformedURLException e13) {
                            e = e13;
                            i9 = 1;
                        }
                    } catch (RemoteService.ServiceUnavailableException e14) {
                        C4476a.this.m17906j("Cannot post message to " + str2 + ".", e14);
                        this.f15758c = (long) (e14.m17987a() * CredentialsApi.ACTIVITY_RESULT_ADD_ACCOUNT);
                        i9 = 0;
                        if (i9 != 0) {
                        }
                    } catch (SocketTimeoutException e15) {
                        C4476a.this.m17906j("Cannot post message to " + str2 + ".", e15);
                        i9 = 0;
                        if (i9 != 0) {
                        }
                    } catch (IOException e16) {
                        C4476a.this.m17906j("Cannot post message to " + str2 + ".", e16);
                        i9 = 0;
                        if (i9 != 0) {
                        }
                    }
                    if (i9 != 0) {
                        removeMessages(2, str);
                        long jMax = Math.max(((long) Math.pow(2.0d, this.f15759d)) * 60000, this.f15758c);
                        this.f15758c = jMax;
                        this.f15758c = Math.min(jMax, 600000L);
                        Message messageObtain = Message.obtain();
                        messageObtain.what = 2;
                        messageObtain.obj = str;
                        sendMessageDelayed(messageObtain, this.f15758c);
                        this.f15759d++;
                        C4476a.this.m17905i("Retrying this batch of events in " + this.f15758c + " ms");
                        return;
                    }
                    C4476a.this.m17905i("Not retrying this batch of events, deleting them from DB.");
                    mPDbAdapter.m17883m(str3, table, str);
                    strArrM17885o = mPDbAdapter.m17885o(table, str);
                    if (strArrM17885o != null) {
                        numValueOf = Integer.valueOf(strArrM17885o[2]);
                    }
                    i9 = 0;
                }
            }

            @Override // android.os.Handler
            public void handleMessage(Message message) throws Throwable {
                String strM17915a;
                int iM17887r;
                String strM17915a2;
                String strM17915a3;
                if (this.f15756a == null) {
                    C4476a c4476a = C4476a.this;
                    MPDbAdapter mPDbAdapterM17907k = c4476a.m17907k(c4476a.f15740b);
                    this.f15756a = mPDbAdapterM17907k;
                    mPDbAdapterM17907k.m17882l(System.currentTimeMillis() - C4476a.this.f15741c.m25470b(), MPDbAdapter.Table.EVENTS);
                    this.f15756a.m17882l(System.currentTimeMillis() - C4476a.this.f15741c.m25470b(), MPDbAdapter.Table.PEOPLE);
                }
                try {
                    int i9 = message.what;
                    if (i9 == 0) {
                        e eVar = (e) message.obj;
                        MPDbAdapter.Table table = eVar.m17917c() ? MPDbAdapter.Table.ANONYMOUS_PEOPLE : MPDbAdapter.Table.PEOPLE;
                        C4476a.this.m17905i("Queuing people record for sending later");
                        C4476a.this.m17905i("    " + eVar.toString());
                        strM17915a2 = eVar.m17915a();
                        iM17887r = this.f15756a.m17880j(eVar.m17916b(), strM17915a2, table);
                        if (eVar.m17917c()) {
                            iM17887r = 0;
                        }
                    } else if (i9 == 3) {
                        b bVar = (b) message.obj;
                        C4476a.this.m17905i("Queuing group record for sending later");
                        C4476a.this.m17905i("    " + bVar.toString());
                        strM17915a2 = bVar.m17915a();
                        iM17887r = this.f15756a.m17880j(bVar.m17916b(), strM17915a2, MPDbAdapter.Table.GROUPS);
                    } else if (i9 == 1) {
                        a aVar = (a) message.obj;
                        try {
                            JSONObject jSONObjectM17929b = m17929b(aVar);
                            C4476a.this.m17905i("Queuing event for sending later");
                            C4476a.this.m17905i("    " + jSONObjectM17929b.toString());
                            strM17915a3 = aVar.m17915a();
                            try {
                                iM17887r = this.f15756a.m17880j(jSONObjectM17929b, strM17915a3, MPDbAdapter.Table.EVENTS);
                            } catch (JSONException e9) {
                                e = e9;
                                C0035c.m142d("MixpanelAPI.Messages", "Exception tracking event " + aVar.m17912c(), e);
                                iM17887r = -3;
                                strM17915a2 = strM17915a3;
                                if (iM17887r < C4476a.this.f15741c.m25469a()) {
                                    C4476a.this.m17905i("Flushing queue due to bulk upload limit (" + iM17887r + ") for project " + strM17915a2);
                                    h.this.m17927h();
                                    m17930c(this.f15756a, strM17915a2);
                                    return;
                                }
                                C4476a.this.m17905i("Flushing queue due to bulk upload limit (" + iM17887r + ") for project " + strM17915a2);
                                h.this.m17927h();
                                m17930c(this.f15756a, strM17915a2);
                                return;
                                if (iM17887r > 0) {
                                    return;
                                } else {
                                    return;
                                }
                            }
                        } catch (JSONException e10) {
                            e = e10;
                            strM17915a3 = null;
                        }
                        strM17915a2 = strM17915a3;
                    } else if (i9 == 4) {
                        f fVar = (f) message.obj;
                        String strM17918b = fVar.m17918b();
                        strM17915a2 = fVar.m17915a();
                        iM17887r = this.f15756a.m17887r(strM17915a2, strM17918b);
                    } else {
                        if (i9 == 7) {
                            strM17915a = ((c) message.obj).m17915a();
                            this.f15756a.m17881k(MPDbAdapter.Table.ANONYMOUS_PEOPLE, strM17915a);
                        } else {
                            if (i9 == 8) {
                                g gVar = (g) message.obj;
                                C0035c.m139a("MixpanelAPI.Messages", this.f15756a.m17888s(gVar.m17919b(), gVar.m17915a()) + " stored events were updated with new properties.");
                            } else if (i9 == 2) {
                                C4476a.this.m17905i("Flushing queue due to scheduled or forced flush");
                                h.this.m17927h();
                                strM17915a = (String) message.obj;
                                m17930c(this.f15756a, strM17915a);
                            } else if (i9 == 6) {
                                strM17915a = ((c) message.obj).m17915a();
                                this.f15756a.m17881k(MPDbAdapter.Table.EVENTS, strM17915a);
                                this.f15756a.m17881k(MPDbAdapter.Table.PEOPLE, strM17915a);
                                this.f15756a.m17881k(MPDbAdapter.Table.GROUPS, strM17915a);
                                this.f15756a.m17881k(MPDbAdapter.Table.ANONYMOUS_PEOPLE, strM17915a);
                            } else if (i9 == 5) {
                                C0035c.m149k("MixpanelAPI.Messages", "Worker received a hard kill. Dumping all events and force-killing. Thread id " + Thread.currentThread().getId());
                                synchronized (h.this.f15749a) {
                                    this.f15756a.m17884n();
                                    h.this.f15750b = null;
                                    Looper.myLooper().quit();
                                }
                            } else if (i9 == 9) {
                                C0034b.m138a((File) message.obj);
                            } else {
                                C0035c.m141c("MixpanelAPI.Messages", "Unexpected message received by Mixpanel worker: " + message);
                            }
                            iM17887r = -3;
                            strM17915a2 = null;
                        }
                        iM17887r = -3;
                        strM17915a2 = strM17915a;
                    }
                    if ((iM17887r < C4476a.this.f15741c.m25469a() || iM17887r == -2) && this.f15759d <= 0 && strM17915a2 != null) {
                        C4476a.this.m17905i("Flushing queue due to bulk upload limit (" + iM17887r + ") for project " + strM17915a2);
                        h.this.m17927h();
                        m17930c(this.f15756a, strM17915a2);
                        return;
                    }
                    if (iM17887r > 0 || hasMessages(2, strM17915a2)) {
                        return;
                    }
                    C4476a.this.m17905i("Queue depth " + iM17887r + " - Adding flush in " + this.f15757b);
                    if (this.f15757b >= 0) {
                        Message messageObtain = Message.obtain();
                        messageObtain.what = 2;
                        messageObtain.obj = strM17915a2;
                        messageObtain.arg1 = 1;
                        sendMessageDelayed(messageObtain, this.f15757b);
                    }
                } catch (RuntimeException e11) {
                    C0035c.m142d("MixpanelAPI.Messages", "Worker threw an unhandled exception", e11);
                    synchronized (h.this.f15749a) {
                        h.this.f15750b = null;
                        try {
                            Looper.myLooper().quit();
                            C0035c.m142d("MixpanelAPI.Messages", "Mixpanel will not process any more analytics messages", e11);
                        } catch (Exception e12) {
                            C0035c.m142d("MixpanelAPI.Messages", "Could not halt looper", e12);
                        }
                    }
                }
            }
        }

        public h() {
        }

        /* renamed from: f */
        public Handler m17925f() {
            HandlerThread handlerThread = new HandlerThread("com.mixpanel.android.AnalyticsWorker", 10);
            handlerThread.start();
            return new a(handlerThread.getLooper());
        }

        /* renamed from: g */
        public void m17926g(Message message) {
            synchronized (this.f15749a) {
                Handler handler = this.f15750b;
                if (handler == null) {
                    C4476a.this.m17905i("Dead mixpanel worker dropping a message: " + message.what);
                } else {
                    handler.sendMessage(message);
                }
            }
        }

        /* renamed from: h */
        public final void m17927h() {
            long jCurrentTimeMillis = System.currentTimeMillis();
            long j9 = this.f15751c;
            long j10 = 1 + j9;
            long j11 = this.f15753e;
            if (j11 > 0) {
                long j12 = ((jCurrentTimeMillis - j11) + (this.f15752d * j9)) / j10;
                this.f15752d = j12;
                C4476a.this.m17905i("Average send frequency approximately " + (j12 / 1000) + " seconds.");
            }
            this.f15753e = jCurrentTimeMillis;
            this.f15751c = j10;
        }
    }

    public C4476a(Context context) {
        this.f15740b = context;
        this.f15741c = m17903f(context);
        m17904h().mo17985b();
    }

    /* renamed from: g */
    public static C4476a m17899g(Context context) {
        C4476a c4476a;
        Map<Context, C4476a> map = f15738d;
        synchronized (map) {
            Context applicationContext = context.getApplicationContext();
            if (map.containsKey(applicationContext)) {
                c4476a = map.get(applicationContext);
            } else {
                c4476a = new C4476a(applicationContext);
                map.put(applicationContext, c4476a);
            }
        }
        return c4476a;
    }

    /* renamed from: c */
    public h m17900c() {
        return new h();
    }

    /* renamed from: d */
    public void m17901d(c cVar) {
        Message messageObtain = Message.obtain();
        messageObtain.what = 6;
        messageObtain.obj = cVar;
        this.f15739a.m17926g(messageObtain);
    }

    /* renamed from: e */
    public void m17902e(a aVar) {
        Message messageObtain = Message.obtain();
        messageObtain.what = 1;
        messageObtain.obj = aVar;
        this.f15739a.m17926g(messageObtain);
    }

    /* renamed from: f */
    public C6826b m17903f(Context context) {
        return C6826b.m25465k(context);
    }

    /* renamed from: h */
    public RemoteService m17904h() {
        return new C4481a();
    }

    /* renamed from: i */
    public final void m17905i(String str) {
        C0035c.m147i("MixpanelAPI.Messages", str + " (Thread " + Thread.currentThread().getId() + ")");
    }

    /* renamed from: j */
    public final void m17906j(String str, Throwable th) {
        C0035c.m148j("MixpanelAPI.Messages", str + " (Thread " + Thread.currentThread().getId() + ")", th);
    }

    /* renamed from: k */
    public MPDbAdapter m17907k(Context context) {
        return MPDbAdapter.m17878q(context);
    }

    /* renamed from: l */
    public void m17908l(e eVar) {
        Message messageObtain = Message.obtain();
        messageObtain.what = 0;
        messageObtain.obj = eVar;
        this.f15739a.m17926g(messageObtain);
    }

    /* renamed from: m */
    public void m17909m(c cVar) {
        Message messageObtain = Message.obtain();
        messageObtain.what = 2;
        messageObtain.obj = cVar.m17915a();
        messageObtain.arg1 = 0;
        this.f15739a.m17926g(messageObtain);
    }

    /* renamed from: n */
    public void m17910n(f fVar) {
        Message messageObtain = Message.obtain();
        messageObtain.what = 4;
        messageObtain.obj = fVar;
        this.f15739a.m17926g(messageObtain);
    }

    /* renamed from: o */
    public void m17911o(File file) {
        Message messageObtain = Message.obtain();
        messageObtain.what = 9;
        messageObtain.obj = file;
        this.f15739a.m17926g(messageObtain);
    }
}
