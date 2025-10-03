package com.cyberlink.meeting.doserver;

import android.util.Log;
import android.util.Pair;
import com.cyberlink.you.Globals;
import com.cyberlink.you.feedback.C3031d;
import com.cyberlink.you.feedback.Model;
import com.cyberlink.you.feedback.PromisedTask;
import com.cyberlink.you.utility.ULogUtility;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.gms.actions.SearchIntents;
import com.google.android.gms.auth.api.credentials.CredentialsApi;
import com.google.common.net.HttpHeaders;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;
import p047d5.C4677a;
import p116k4.AbstractC5146g0;
import p116k4.C5170o0;

/* loaded from: classes.dex */
public class NetworkManager {

    /* renamed from: b */
    public static NetworkManager f6232b = null;

    /* renamed from: a */
    public C1259f f6237a;

    /* renamed from: e */
    public static final Object f6235e = new Object();

    /* renamed from: c */
    public static int f6233c = 5;

    /* renamed from: d */
    public static long f6234d = 5000;

    /* renamed from: f */
    public static ExecutorService f6236f = new ThreadPoolExecutor(f6233c, 100, f6234d, TimeUnit.MILLISECONDS, new LinkedBlockingQueue());

    public enum NetworkErrorCode {
        E_CONNECT_FAIL(-1),
        E_BAD_REQUEST(-2),
        E_NOT_INITIALIZED(-3),
        E_EMPTY_RESPONSE(-4),
        E_CONNECT_CANCELLED(-5);

        private final int value;

        NetworkErrorCode(int i9) {
            this.value = i9;
        }

        /* renamed from: a */
        public int m5658a() {
            return this.value;
        }
    }

    /* renamed from: com.cyberlink.meeting.doserver.NetworkManager$a */
    public class C1254a extends PromisedTask<Void, Void, Void> {
        public C1254a() {
        }

        @Override // com.cyberlink.you.feedback.PromisedTask
        /* renamed from: q, reason: merged with bridge method [inline-methods] */
        public Void mo5659d(Void r22) {
            synchronized (NetworkManager.this) {
                NetworkManager.f6232b.m5655k();
            }
            return null;
        }
    }

    /* renamed from: com.cyberlink.meeting.doserver.NetworkManager$b */
    public class C1255b extends PromisedTask<Void, Void, NetworkManager> {
        public C1255b() {
        }

        @Override // com.cyberlink.you.feedback.PromisedTask
        /* renamed from: q, reason: merged with bridge method [inline-methods] */
        public NetworkManager mo5659d(Void r22) {
            if (NetworkManager.this.m5656m()) {
                return NetworkManager.this;
            }
            synchronized (NetworkManager.f6235e) {
                if (!NetworkManager.this.m5656m()) {
                    NetworkManager.this.m5655k();
                }
            }
            if (!NetworkManager.this.m5656m()) {
                m15446n(NetworkErrorCode.E_NOT_INITIALIZED.m5658a(), null);
            }
            return NetworkManager.this;
        }
    }

    /* renamed from: com.cyberlink.meeting.doserver.NetworkManager$c */
    public class C1256c extends PromisedTask<String, Void, Void> {
        public C1256c() {
        }

        @Override // com.cyberlink.you.feedback.PromisedTask
        /* renamed from: q, reason: merged with bridge method [inline-methods] */
        public Void mo5659d(String str) {
            NetworkManager.this.m5657n(str);
            return null;
        }
    }

    /* renamed from: com.cyberlink.meeting.doserver.NetworkManager$f */
    public static class C1259f extends Model {

        /* renamed from: f */
        public static final Gson f6250f = new GsonBuilder().create();

        /* renamed from: a */
        @SerializedName("live.meeting")
        public b f6251a;

        /* renamed from: b */
        @SerializedName("live.info")
        public a f6252b;

        /* renamed from: c */
        @SerializedName("live.user")
        public e f6253c;

        /* renamed from: d */
        @SerializedName("live.rollcall")
        public c f6254d;

        /* renamed from: e */
        @SerializedName("live.session")
        public d f6255e;

        /* renamed from: com.cyberlink.meeting.doserver.NetworkManager$f$a */
        public static class a extends Model {

            /* renamed from: a */
            @SerializedName("user.site")
            public String f6256a;

            /* renamed from: b */
            @SerializedName("init.url")
            public String f6257b;
        }

        /* renamed from: com.cyberlink.meeting.doserver.NetworkManager$f$b */
        public static class b extends Model {

            /* renamed from: a */
            @SerializedName("create")
            public String f6258a;

            /* renamed from: b */
            @SerializedName("join")
            public String f6259b;

            /* renamed from: c */
            @SerializedName(SearchIntents.EXTRA_QUERY)
            public String f6260c;

            /* renamed from: d */
            @SerializedName("invitee.list")
            public String f6261d;

            /* renamed from: e */
            @SerializedName("faceme.token.get")
            public String f6262e;

            /* renamed from: f */
            @SerializedName("pstn.member.query")
            public String f6263f;

            /* renamed from: g */
            @SerializedName("pstn.u.phone.list")
            public String f6264g;
        }

        /* renamed from: com.cyberlink.meeting.doserver.NetworkManager$f$c */
        public static class c extends Model {

            /* renamed from: a */
            @SerializedName("url.query")
            public String f6265a;
        }

        /* renamed from: com.cyberlink.meeting.doserver.NetworkManager$f$d */
        public static class d extends Model {

            /* renamed from: a */
            @SerializedName("sso.token.get")
            public String f6266a;
        }

        /* renamed from: com.cyberlink.meeting.doserver.NetworkManager$f$e */
        public static class e extends Model {

            /* renamed from: a */
            @SerializedName("subscription.m.query")
            public String f6267a;
        }
    }

    /* renamed from: f */
    public static void m5644f() {
        if (f6232b == null) {
            f6232b = new NetworkManager();
        }
        f6232b.new C1254a().m15441g(f6236f, null);
        C4677a.m18688N(m5645g());
    }

    /* renamed from: g */
    public static Map<String, String> m5645g() {
        HashMap map = new HashMap();
        map.put("ap", "U");
        map.put("version", Globals.m7391n1());
        map.put("versionType", "for Android");
        map.put("apiVersion", "1.0");
        map.put("buildNumber", Globals.m7368J());
        map.put("locale", Locale.getDefault().getLanguage() + "_" + Locale.getDefault().getCountry());
        map.put("uuid", AbstractC5146g0.m20043a(Globals.m7388i0()));
        if (Globals.m7388i0().m7464O1()) {
            map.put("token", Globals.m7388i0().m7506X());
        }
        return map;
    }

    /* renamed from: h */
    public static PromisedTask<?, ?, NetworkManager> m5646h() {
        if (f6232b == null) {
            f6232b = new NetworkManager();
        }
        return m5649l(f6232b, f6236f);
    }

    /* renamed from: i */
    public static String m5647i() {
        return Globals.m7388i0().m7463O0().equals("CHN") ? Globals.m7380c2() ? "https://biz-demo-api.presenterlink.com/api/live/init" : "https://biz-api.presenterlink.com/api/live/init" : !C5170o0.m20170e(Globals.m7373W()) ? Globals.m7373W() : Globals.m7380c2() ? "https://live-demo-api.cyberlink.com/api/live/init" : "https://webinars-api.cyberlink.com/api/live/init";
    }

    /* renamed from: j */
    public static void m5648j() {
        m5646h();
    }

    /* renamed from: l */
    public static PromisedTask<?, ?, NetworkManager> m5649l(NetworkManager networkManager, ExecutorService executorService) {
        return networkManager.new C1255b().m15441g(executorService, null);
    }

    /* renamed from: o */
    public static void m5650o(JSONObject jSONObject, String str, Map<String, String> map) throws JSONException {
        if (!str.isEmpty()) {
            str = str + ".";
        }
        Iterator<String> itKeys = jSONObject.keys();
        while (itKeys.hasNext()) {
            String next = itKeys.next();
            try {
                if (jSONObject.get(next) instanceof JSONObject) {
                    m5650o((JSONObject) jSONObject.get(next), str + next, map);
                } else {
                    map.put(str + next, jSONObject.getString(next));
                }
            } catch (JSONException e9) {
                Log.e("MeetingNetworkManager", Log.getStackTraceString(e9));
            }
        }
    }

    /* renamed from: p */
    public static C1257d m5651p() {
        return new C1257d(null);
    }

    /* renamed from: q */
    public static C1258e m5652q() {
        return new C1258e();
    }

    /* renamed from: r */
    public static C1258e m5653r(int i9) {
        return new C1258e(i9);
    }

    /* JADX WARN: Removed duplicated region for block: B:45:0x00d8  */
    /* renamed from: e */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final C1259f m5654e(String str) {
        char c9;
        HashMap map = new HashMap();
        C1259f c1259f = new C1259f();
        c1259f.f6251a = new C1259f.b();
        c1259f.f6252b = new C1259f.a();
        c1259f.f6253c = new C1259f.e();
        c1259f.f6254d = new C1259f.c();
        c1259f.f6255e = new C1259f.d();
        try {
            m5650o(new JSONObject(str), "", map);
            for (String str2 : map.keySet()) {
                String str3 = (String) map.get(str2);
                switch (str2.hashCode()) {
                    case -2099144728:
                        if (str2.equals("live.session.sso.token.get")) {
                            c9 = 11;
                            break;
                        } else {
                            c9 = 65535;
                            break;
                        }
                    case -1927508488:
                        if (str2.equals("live.rollcall.url.query")) {
                            c9 = '\n';
                            break;
                        }
                        break;
                    case -1924377921:
                        if (str2.equals("live.meeting.join")) {
                            c9 = 1;
                            break;
                        }
                        break;
                    case -1851165339:
                        if (str2.equals("live.meeting.invitee.list")) {
                            c9 = 3;
                            break;
                        }
                        break;
                    case -1233560585:
                        if (str2.equals("live.user.subscription.m.query")) {
                            c9 = '\t';
                            break;
                        }
                        break;
                    case -1149517315:
                        if (str2.equals("live.meeting.faceme.token.get")) {
                            c9 = 4;
                            break;
                        }
                        break;
                    case -920750192:
                        if (str2.equals("live.meeting.pstn.member.query")) {
                            c9 = 5;
                            break;
                        }
                        break;
                    case -178694317:
                        if (str2.equals("live.meeting.pstn.u.phone.list")) {
                            c9 = 6;
                            break;
                        }
                        break;
                    case 455334735:
                        if (str2.equals("live.info.init.url")) {
                            c9 = '\b';
                            break;
                        }
                        break;
                    case 480466387:
                        if (str2.equals("live.meeting.query")) {
                            c9 = 2;
                            break;
                        }
                        break;
                    case 1605961041:
                        if (str2.equals("live.meeting.create")) {
                            c9 = 0;
                            break;
                        }
                        break;
                    case 1827601292:
                        if (str2.equals("live.info.user.site")) {
                            c9 = 7;
                            break;
                        }
                        break;
                    default:
                        c9 = 65535;
                        break;
                }
                switch (c9) {
                    case 0:
                        c1259f.f6251a.f6258a = str3;
                        break;
                    case 1:
                        c1259f.f6251a.f6259b = str3;
                        break;
                    case 2:
                        c1259f.f6251a.f6260c = str3;
                        break;
                    case 3:
                        c1259f.f6251a.f6261d = str3;
                        break;
                    case 4:
                        c1259f.f6251a.f6262e = str3;
                        break;
                    case 5:
                        c1259f.f6251a.f6263f = str3;
                        break;
                    case 6:
                        c1259f.f6251a.f6264g = str3;
                        break;
                    case 7:
                        c1259f.f6252b.f6256a = str3;
                        break;
                    case '\b':
                        c1259f.f6252b.f6257b = str3;
                        break;
                    case '\t':
                        c1259f.f6253c.f6267a = str3;
                        break;
                    case '\n':
                        c1259f.f6254d.f6265a = str3;
                        break;
                    case 11:
                        c1259f.f6255e.f6266a = str3;
                        break;
                }
            }
        } catch (JSONException e9) {
            ULogUtility.m16670f("MeetingNetworkManager", "convertToResponse / error = " + e9.toString());
        }
        return c1259f;
    }

    /* renamed from: k */
    public final void m5655k() {
        C3031d c3031d = new C3031d(m5647i());
        Map<String, String> mapM5645g = m5645g();
        for (String str : mapM5645g.keySet()) {
            c3031d.m15500d(str, mapM5645g.get(str));
        }
        c3031d.f13487f = "application/x-www-form-urlencoded";
        try {
            new C1258e().m15440f(c3031d).m15447o(new C1256c()).m15442h();
        } catch (NullPointerException unused) {
            ULogUtility.m16680p("MeetingNetworkManager", "NetworkManager is not ready");
        } catch (Exception e9) {
            e9.printStackTrace();
        }
    }

    /* renamed from: m */
    public final boolean m5656m() {
        return this.f6237a != null;
    }

    /* renamed from: n */
    public final boolean m5657n(String str) {
        C1259f c1259fM5654e = m5654e(str);
        this.f6237a = c1259fM5654e;
        if (c1259fM5654e == null) {
            return false;
        }
        C1259f.a aVar = c1259fM5654e.f6252b;
        if (aVar != null && !C5170o0.m20170e(aVar.f6257b)) {
            Globals.m7369J2(this.f6237a.f6252b.f6257b);
            C4677a.m18687M(this.f6237a.f6252b.f6257b);
        }
        ULogUtility.m16670f("MeetingNetworkManager", "initResponse = " + this.f6237a.toString());
        return true;
    }

    /* renamed from: com.cyberlink.meeting.doserver.NetworkManager$d */
    public static class C1257d extends PromisedTask<C3031d, Float, String> {

        /* renamed from: j */
        public String f6247j;

        public C1257d() {
            this.f6247j = ULogUtility.m16677m();
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Removed duplicated region for block: B:101:0x01ed A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.String] */
        /* JADX WARN: Type inference failed for: r0v1 */
        /* JADX WARN: Type inference failed for: r0v3, types: [java.io.InputStream] */
        /* JADX WARN: Type inference failed for: r2v0 */
        /* JADX WARN: Type inference failed for: r2v1, types: [java.io.InputStream] */
        /* JADX WARN: Type inference failed for: r2v2 */
        @Override // com.cyberlink.you.feedback.PromisedTask
        /* renamed from: q, reason: merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public String mo5659d(C3031d c3031d) throws Throwable {
            ?? r02 = "[WebRequest][";
            ?? r22 = 0;
            if (m15443i()) {
                NetworkErrorCode networkErrorCode = NetworkErrorCode.E_CONNECT_CANCELLED;
                m5664r(networkErrorCode.m5658a(), networkErrorCode.toString(), null);
                return null;
            }
            try {
                try {
                    if (c3031d == null) {
                        NetworkErrorCode networkErrorCode2 = NetworkErrorCode.E_BAD_REQUEST;
                        m5664r(networkErrorCode2.m5658a(), networkErrorCode2.toString(), null);
                        return null;
                    }
                    String strM15506j = c3031d.m15506j();
                    StringBuilder sb = new StringBuilder();
                    URL url = new URL(strM15506j);
                    ULogUtility.m16683s("MeetingNetworkManager", "[WebRequest][" + this.f6247j + "] GET URL: " + url.toString());
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setConnectTimeout(60000);
                    httpURLConnection.setReadTimeout(60000);
                    if (m15443i()) {
                        NetworkErrorCode networkErrorCode3 = NetworkErrorCode.E_CONNECT_CANCELLED;
                        m5664r(networkErrorCode3.m5658a(), networkErrorCode3.toString(), null);
                        return null;
                    }
                    httpURLConnection.setRequestMethod("GET");
                    httpURLConnection.setRequestProperty(HttpHeaders.USER_AGENT, Globals.m7388i0().m7562j1());
                    Iterator<Pair<String, String>> it = c3031d.m15503g().iterator();
                    while (it.hasNext()) {
                        Pair<String, String> next = it.next();
                        httpURLConnection.setRequestProperty((String) next.first, (String) next.second);
                    }
                    int responseCode = httpURLConnection.getResponseCode();
                    if (m15443i()) {
                        NetworkErrorCode networkErrorCode4 = NetworkErrorCode.E_CONNECT_CANCELLED;
                        m5664r(networkErrorCode4.m5658a(), networkErrorCode4.toString(), null);
                        return null;
                    }
                    try {
                        if (responseCode >= 400 && responseCode < 600) {
                            ULogUtility.m16676l("MeetingNetworkManager", "[WebRequest][" + this.f6247j + "] GET Fail: " + responseCode);
                            InputStream errorStream = httpURLConnection.getErrorStream();
                            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(errorStream, "utf-8"));
                            while (true) {
                                String line = bufferedReader.readLine();
                                if (line == null || m15443i()) {
                                    break;
                                }
                                sb.append(line);
                            }
                            bufferedReader.close();
                            m5664r(responseCode, httpURLConnection.getResponseMessage(), sb.toString());
                            if (errorStream != null) {
                                try {
                                    errorStream.close();
                                } catch (IOException e9) {
                                    e9.printStackTrace();
                                }
                            }
                            return null;
                        }
                        ULogUtility.m16683s("MeetingNetworkManager", "[WebRequest][" + this.f6247j + "] GET Success: " + responseCode);
                        InputStream inputStream = httpURLConnection.getInputStream();
                        BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
                        while (true) {
                            String line2 = bufferedReader2.readLine();
                            if (line2 == null || m15443i()) {
                                break;
                            }
                            sb.append(line2);
                        }
                        bufferedReader2.close();
                        if (m15443i()) {
                            NetworkErrorCode networkErrorCode5 = NetworkErrorCode.E_CONNECT_CANCELLED;
                            m5664r(networkErrorCode5.m5658a(), networkErrorCode5.toString(), null);
                            if (inputStream != null) {
                                try {
                                    inputStream.close();
                                } catch (IOException e10) {
                                    e10.printStackTrace();
                                }
                            }
                            return null;
                        }
                        String string = sb.toString();
                        if (string.length() != 0) {
                            if (inputStream != null) {
                                try {
                                    inputStream.close();
                                } catch (IOException e11) {
                                    e11.printStackTrace();
                                }
                            }
                            return string;
                        }
                        NetworkErrorCode networkErrorCode6 = NetworkErrorCode.E_EMPTY_RESPONSE;
                        m5664r(networkErrorCode6.m5658a(), networkErrorCode6.toString(), null);
                        if (inputStream != null) {
                            try {
                                inputStream.close();
                            } catch (IOException e12) {
                                e12.printStackTrace();
                            }
                        }
                        return null;
                    } catch (IOException e13) {
                        e = e13;
                        ULogUtility.m16676l("MeetingNetworkManager", "IOException : [" + this.f6247j + "]" + e.toString());
                        NetworkErrorCode networkErrorCode7 = NetworkErrorCode.E_BAD_REQUEST;
                        m5664r(networkErrorCode7.m5658a(), networkErrorCode7.toString(), null);
                        if (r02 != 0) {
                            try {
                                r02.close();
                            } catch (IOException e14) {
                                e14.printStackTrace();
                            }
                        }
                        return null;
                    }
                } catch (Throwable th) {
                    th = th;
                    r22 = "[WebRequest][";
                    if (r22 != 0) {
                        try {
                            r22.close();
                        } catch (IOException e15) {
                            e15.printStackTrace();
                        }
                    }
                    throw th;
                }
            } catch (IOException e16) {
                e = e16;
                r02 = 0;
            } catch (Throwable th2) {
                th = th2;
                if (r22 != 0) {
                }
                throw th;
            }
        }

        /* renamed from: r */
        public final void m5664r(int i9, String str, String str2) {
            if (str == null) {
                str = "Unknown reason";
            }
            m15446n(i9, str2);
            ULogUtility.m16676l("MeetingNetworkManager", "Get Network Fail: [" + this.f6247j + "]" + i9 + StringUtils.SPACE + str + StringUtils.SPACE + str2);
        }

        public /* synthetic */ C1257d(C1254a c1254a) {
            this();
        }
    }

    /* renamed from: com.cyberlink.meeting.doserver.NetworkManager$e */
    public static class C1258e extends PromisedTask<C3031d, Float, String> {

        /* renamed from: j */
        public String f6248j;

        /* renamed from: k */
        public int f6249k;

        public C1258e() {
            this.f6248j = ULogUtility.m16677m();
            this.f6249k = DefaultLoadControl.DEFAULT_MAX_BUFFER_MS;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Removed duplicated region for block: B:99:0x0294 A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Type inference failed for: r0v2 */
        /* JADX WARN: Type inference failed for: r0v3, types: [java.io.InputStream] */
        /* JADX WARN: Type inference failed for: r0v8 */
        /* JADX WARN: Type inference failed for: r6v0 */
        /* JADX WARN: Type inference failed for: r6v1 */
        /* JADX WARN: Type inference failed for: r6v2, types: [java.io.InputStream] */
        @Override // com.cyberlink.you.feedback.PromisedTask
        /* renamed from: q, reason: merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public String mo5659d(C3031d c3031d) throws Throwable {
            ?? r02;
            ?? r62 = 0;
            try {
                if (m15443i()) {
                    NetworkErrorCode networkErrorCode = NetworkErrorCode.E_CONNECT_CANCELLED;
                    m5666r(networkErrorCode.m5658a(), networkErrorCode.toString(), null);
                    return null;
                }
                try {
                    String strM15502f = c3031d.m15502f();
                    if (strM15502f == null) {
                        NetworkErrorCode networkErrorCode2 = NetworkErrorCode.E_BAD_REQUEST;
                        m5666r(networkErrorCode2.m5658a(), networkErrorCode2.toString(), null);
                        return null;
                    }
                    StringBuilder sb = new StringBuilder();
                    URL url = new URL(strM15502f);
                    ULogUtility.m16683s("MeetingNetworkManager", "[WebRequest][" + this.f6248j + "] POST URL: " + url.toString());
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setConnectTimeout(this.f6249k);
                    httpURLConnection.setReadTimeout(this.f6249k);
                    System.setProperty("http.maxConnections", "10");
                    if (m15443i()) {
                        NetworkErrorCode networkErrorCode3 = NetworkErrorCode.E_CONNECT_CANCELLED;
                        m5666r(networkErrorCode3.m5658a(), networkErrorCode3.toString(), null);
                        return null;
                    }
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setRequestProperty(HttpHeaders.USER_AGENT, Globals.m7388i0().m7562j1());
                    Iterator<Pair<String, String>> it = c3031d.m15503g().iterator();
                    while (it.hasNext()) {
                        Pair<String, String> next = it.next();
                        httpURLConnection.setRequestProperty((String) next.first, (String) next.second);
                    }
                    if (c3031d.f13487f.equals("application/x-www-form-urlencoded")) {
                        httpURLConnection.setRequestProperty(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded");
                        httpURLConnection.setDoOutput(true);
                        String strM15505i = c3031d.m15505i();
                        try {
                            DataOutputStream dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
                            dataOutputStream.writeBytes(strM15505i);
                            dataOutputStream.flush();
                            dataOutputStream.close();
                        } catch (UnknownHostException e9) {
                            ULogUtility.m16676l("MeetingNetworkManager", "UnknownHostException : [" + this.f6248j + "]" + e9.getMessage());
                            NetworkErrorCode networkErrorCode4 = NetworkErrorCode.E_BAD_REQUEST;
                            m5666r(networkErrorCode4.m5658a(), networkErrorCode4.toString(), null);
                            return null;
                        }
                    } else {
                        String str = "====" + Long.toHexString(System.currentTimeMillis()) + "====";
                        httpURLConnection.setRequestProperty(HttpHeaders.CONTENT_TYPE, "multipart/form-data; boundary=" + str);
                        httpURLConnection.setDoOutput(true);
                        try {
                            DataOutputStream dataOutputStream2 = new DataOutputStream(httpURLConnection.getOutputStream());
                            c3031d.m15507k(new PrintWriter(dataOutputStream2), dataOutputStream2, str);
                        } catch (UnknownHostException e10) {
                            ULogUtility.m16676l("MeetingNetworkManager", "UnknownHostException : [" + this.f6248j + "]" + e10.getMessage());
                            NetworkErrorCode networkErrorCode5 = NetworkErrorCode.E_BAD_REQUEST;
                            m5666r(networkErrorCode5.m5658a(), networkErrorCode5.toString(), null);
                            return null;
                        }
                    }
                    int responseCode = httpURLConnection.getResponseCode();
                    if (m15443i()) {
                        NetworkErrorCode networkErrorCode6 = NetworkErrorCode.E_CONNECT_CANCELLED;
                        m5666r(networkErrorCode6.m5658a(), networkErrorCode6.toString(), null);
                        return null;
                    }
                    r02 = 400;
                    try {
                        if (responseCode >= 400 && responseCode < 600) {
                            ULogUtility.m16676l("MeetingNetworkManager", "[WebRequest][" + this.f6248j + "] POST Fail: " + responseCode);
                            InputStream errorStream = httpURLConnection.getErrorStream();
                            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(errorStream, "utf-8"));
                            while (true) {
                                String line = bufferedReader.readLine();
                                if (line == null || m15443i()) {
                                    break;
                                }
                                sb.append(line);
                            }
                            bufferedReader.close();
                            m5666r(responseCode, httpURLConnection.getResponseMessage(), sb.toString());
                            if (errorStream != null) {
                                try {
                                    errorStream.close();
                                } catch (IOException e11) {
                                    e11.printStackTrace();
                                }
                            }
                            return null;
                        }
                        ULogUtility.m16683s("MeetingNetworkManager", "[WebRequest][" + this.f6248j + "] POST Success: " + responseCode);
                        InputStream inputStream = httpURLConnection.getInputStream();
                        BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
                        while (true) {
                            String line2 = bufferedReader2.readLine();
                            if (line2 == null || m15443i()) {
                                break;
                            }
                            sb.append(line2);
                        }
                        bufferedReader2.close();
                        if (!m15443i()) {
                            String string = sb.toString();
                            if (inputStream != null) {
                                try {
                                    inputStream.close();
                                } catch (IOException e12) {
                                    e12.printStackTrace();
                                }
                            }
                            return string;
                        }
                        NetworkErrorCode networkErrorCode7 = NetworkErrorCode.E_CONNECT_CANCELLED;
                        m5666r(networkErrorCode7.m5658a(), networkErrorCode7.toString(), null);
                        if (inputStream != null) {
                            try {
                                inputStream.close();
                            } catch (IOException e13) {
                                e13.printStackTrace();
                            }
                        }
                        return null;
                    } catch (IOException e14) {
                        e = e14;
                        ULogUtility.m16676l("MeetingNetworkManager", "IOException : [" + this.f6248j + "]" + e.toString());
                        NetworkErrorCode networkErrorCode8 = NetworkErrorCode.E_BAD_REQUEST;
                        m5666r(networkErrorCode8.m5658a(), networkErrorCode8.toString(), null);
                        if (r02 != 0) {
                            try {
                                r02.close();
                            } catch (IOException e15) {
                                e15.printStackTrace();
                            }
                        }
                        return null;
                    }
                } catch (IOException e16) {
                    e = e16;
                    r02 = 0;
                    ULogUtility.m16676l("MeetingNetworkManager", "IOException : [" + this.f6248j + "]" + e.toString());
                    NetworkErrorCode networkErrorCode82 = NetworkErrorCode.E_BAD_REQUEST;
                    m5666r(networkErrorCode82.m5658a(), networkErrorCode82.toString(), null);
                    if (r02 != 0) {
                    }
                    return null;
                } catch (Throwable th) {
                    th = th;
                    if (r62 != 0) {
                        try {
                            r62.close();
                        } catch (IOException e17) {
                            e17.printStackTrace();
                        }
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                r62 = "====";
            }
        }

        /* renamed from: r */
        public final void m5666r(int i9, String str, String str2) {
            if (str == null) {
                str = "Unknown reason";
            }
            m15446n(i9, str2);
            ULogUtility.m16676l("MeetingNetworkManager", "Post Network Fail: [" + this.f6248j + "]" + i9 + StringUtils.SPACE + str + StringUtils.SPACE + str2);
        }

        public C1258e(int i9) {
            this.f6248j = ULogUtility.m16677m();
            this.f6249k = i9 * CredentialsApi.ACTIVITY_RESULT_ADD_ACCOUNT;
        }
    }
}
