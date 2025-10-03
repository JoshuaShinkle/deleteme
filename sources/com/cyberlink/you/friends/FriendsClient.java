package com.cyberlink.you.friends;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.StrictMode;
import android.util.Log;
import android.util.Pair;
import com.cyberlink.you.Globals;
import com.cyberlink.you.activity.unbind.UnlinkEmailActivity;
import com.cyberlink.you.chat.p034e.C2889b;
import com.cyberlink.you.database.C2950b0;
import com.cyberlink.you.database.C2971k0;
import com.cyberlink.you.database.C2973l0;
import com.cyberlink.you.database.GroupAlbumObj;
import com.cyberlink.you.database.PollOptionObj;
import com.cyberlink.you.database.TopicObj;
import com.cyberlink.you.friends.C3061a;
import com.cyberlink.you.friends.FriendsClient;
import com.cyberlink.you.friends.InvitationFriend;
import com.cyberlink.you.utility.CLUtility;
import com.cyberlink.you.utility.ULogUtility;
import com.google.android.exoplayer2.source.smoothstreaming.SsMediaSource;
import com.google.android.gms.common.Scopes;
import com.google.common.net.HttpHeaders;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.charset.IllegalCharsetNameException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import okhttp3.AbstractC5524y;
import okhttp3.C5520u;
import okhttp3.C5522w;
import okhttp3.C5523x;
import okhttp3.C5525z;
import okhttp3.CertificatePinner;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import p116k4.AbstractC5146g0;
import p116k4.C5154j;
import p116k4.C5170o0;
import p116k4.C5172p;
import p116k4.C5180s;
import p132m.C5305d;
import p201t3.C6301o;
import p201t3.C6302p;
import p201t3.C6311y;
import p209u2.AbstractC6381r;
import p209u2.C6383t;
import p209u2.C6385v;
import p236x2.C6566a;

/* loaded from: classes.dex */
public class FriendsClient {

    /* renamed from: j */
    public static String f13678j;

    /* renamed from: a */
    public final boolean f13682a;

    /* renamed from: b */
    public String f13683b;

    /* renamed from: c */
    public ExecutorService f13684c;

    /* renamed from: d */
    public static final String f13672d = Globals.m7371N();

    /* renamed from: e */
    public static Map<String, Map<String, String>> f13673e = null;

    /* renamed from: f */
    public static final Object f13674f = new Object();

    /* renamed from: g */
    public static boolean f13675g = false;

    /* renamed from: h */
    public static boolean f13676h = false;

    /* renamed from: i */
    public static boolean f13677i = false;

    /* renamed from: k */
    public static long f13679k = AbstractC5146g0.m20047e("PreviousDelayTime", 0L, Globals.m7388i0().getApplicationContext()).longValue();

    /* renamed from: l */
    public static List<InterfaceC3052j> f13680l = new ArrayList();

    /* renamed from: m */
    public static C5305d<String> f13681m = new C5305d<>();

    public enum InvitationFriendType {
        SENT,
        RECEIVED
    }

    /* renamed from: com.cyberlink.you.friends.FriendsClient$a */
    public class C3043a extends Thread {
        @Override // java.lang.Thread, java.lang.Runnable
        public void run() throws JSONException {
            Thread.currentThread().setName("loadCommandUrl");
            String strM7449L = Globals.m7388i0().m7449L();
            String strM7493U0 = Globals.m7388i0().m7493U0();
            Globals.m7388i0().m7601q4(Boolean.TRUE);
            boolean zM15637D0 = FriendsClient.m15637D0(strM7493U0, strM7449L);
            synchronized (FriendsClient.f13674f) {
                if (zM15637D0) {
                    Log.d("FriendsClient", "init success.");
                    Iterator it = FriendsClient.f13680l.iterator();
                    while (it.hasNext()) {
                        ((InterfaceC3052j) it.next()).onSuccess();
                    }
                } else {
                    Log.d("FriendsClient", "init fail.");
                    Iterator it2 = FriendsClient.f13680l.iterator();
                    while (it2.hasNext()) {
                        ((InterfaceC3052j) it2.next()).mo12854a();
                    }
                }
                FriendsClient.f13680l.clear();
                boolean unused = FriendsClient.f13675g = false;
            }
        }
    }

    /* renamed from: com.cyberlink.you.friends.FriendsClient$b */
    public class C3044b extends Thread {
        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            Thread.currentThread().setName("initServerDelayTimeByServerAPI");
            FriendsClient.m15679o0();
        }
    }

    /* renamed from: com.cyberlink.you.friends.FriendsClient$c */
    public class C3045c extends Thread {

        /* renamed from: b */
        public final /* synthetic */ String f13688b;

        /* renamed from: c */
        public final /* synthetic */ String f13689c;

        public C3045c(String str, String str2) {
            this.f13688b = str;
            this.f13689c = str2;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() throws JSONException {
            Thread.currentThread().setName("loadCommandUrlFromServer");
            FriendsClient.m15641F0(this.f13688b, this.f13689c);
        }
    }

    /* renamed from: com.cyberlink.you.friends.FriendsClient$d */
    public class C3046d extends Thread {

        /* renamed from: b */
        public final /* synthetic */ String f13690b;

        /* renamed from: c */
        public final /* synthetic */ List f13691c;

        /* renamed from: d */
        public final /* synthetic */ InterfaceC3051i f13692d;

        public C3046d(String str, List list, InterfaceC3051i interfaceC3051i) {
            this.f13690b = str;
            this.f13691c = list;
            this.f13692d = interfaceC3051i;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            Thread.currentThread().setName("completeUploadMedia");
            Pair pairM15636D = FriendsClient.m15636D(this.f13690b, this.f13691c);
            String str = (String) pairM15636D.first;
            String str2 = (String) pairM15636D.second;
            C6302p.m24123l("", "", str, str2);
            this.f13692d.mo134a("", "", str, str2);
        }
    }

    /* renamed from: com.cyberlink.you.friends.FriendsClient$e */
    public class C3047e implements InterfaceC3052j {

        /* renamed from: a */
        public final /* synthetic */ SharedPreferences f13694a;

        public C3047e(SharedPreferences sharedPreferences) {
            this.f13694a = sharedPreferences;
        }

        @Override // com.cyberlink.you.friends.FriendsClient.InterfaceC3052j
        /* renamed from: a */
        public void mo12854a() {
            ULogUtility.m16689y(" > failed");
        }

        @Override // com.cyberlink.you.friends.FriendsClient.InterfaceC3052j
        public void onSuccess() {
            ULogUtility.m16689y(" > success");
            this.f13694a.edit().putBoolean("needUpdateRegId", false).apply();
        }
    }

    /* renamed from: com.cyberlink.you.friends.FriendsClient$f */
    public static /* synthetic */ class C3048f {

        /* renamed from: a */
        public static final /* synthetic */ int[] f13695a;

        static {
            int[] iArr = new int[InvitationFriendType.values().length];
            f13695a = iArr;
            try {
                iArr[InvitationFriendType.SENT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f13695a[InvitationFriendType.RECEIVED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    /* renamed from: com.cyberlink.you.friends.FriendsClient$g */
    public class RunnableC3049g implements Runnable {

        /* renamed from: b */
        public String f13696b;

        /* renamed from: c */
        public String f13697c;

        /* renamed from: d */
        public List<C6301o> f13698d;

        /* renamed from: e */
        public InterfaceC3051i f13699e;

        public RunnableC3049g(String str, String str2, List<C6301o> list, InterfaceC3051i interfaceC3051i) {
            this.f13696b = str;
            this.f13697c = str2;
            this.f13698d = list;
            this.f13699e = interfaceC3051i;
        }

        @Override // java.lang.Runnable
        public void run() {
            String strM15642G = FriendsClient.m15642G(this.f13696b, this.f13697c);
            if (strM15642G != null) {
                Pair pairM15636D = FriendsClient.m15636D(strM15642G, this.f13698d);
                String str = (String) pairM15636D.first;
                String str2 = (String) pairM15636D.second;
                if (this.f13699e != null) {
                    C6302p.m24125n(this.f13696b, this.f13697c, this.f13698d, str, str2);
                    this.f13699e.mo134a(this.f13696b, this.f13697c, str, str2);
                    return;
                }
                return;
            }
            Log.d("FriendsClient", "table=" + this.f13696b + " field=" + this.f13697c + " command does not exists.");
            InterfaceC3051i interfaceC3051i = this.f13699e;
            if (interfaceC3051i != null) {
                interfaceC3051i.mo134a(this.f13696b, this.f13697c, null, null);
            }
        }
    }

    /* renamed from: com.cyberlink.you.friends.FriendsClient$h */
    public static class C3050h {

        /* renamed from: a */
        public static C5522w f13701a;

        /* renamed from: b */
        public static synchronized C5522w m15741b() {
            if (f13701a == null) {
                C5522w.a aVar = new C5522w.a();
                TimeUnit timeUnit = TimeUnit.MILLISECONDS;
                C5522w.a aVarM21773H = aVar.m21777c(SsMediaSource.DEFAULT_LIVE_PRESENTATION_DELAY_MS, timeUnit).m21773H(SsMediaSource.DEFAULT_LIVE_PRESENTATION_DELAY_MS, timeUnit);
                if (Globals.m7388i0().m7552h2().booleanValue() && !m15742c()) {
                    aVarM21773H.m21776b(new CertificatePinner.C5477a().m21192a("*.cyberlink.com", "sha256/kUojm6oxfPdDaa0l0O/NC6tG/OjsKgCv8wY+Vvbk0BM=").m21192a("*.cyberlink.com", "sha256/klO23nT2ehFDXCfx3eHTDRESMz3asj1muO+4aIdjiuY=").m21192a("*.cyberlink.com", "sha256/grX4Ta9HpZx6tSHkmCrvpApTQGo67CYDnvprLg5yRME=").m21193b());
                }
                f13701a = aVarM21773H.m21775a();
            }
            return f13701a;
        }

        /* renamed from: c */
        public static boolean m15742c() throws ParseException {
            try {
                return new Date().after(new SimpleDateFormat("yyyy-MM-dd", Locale.US).parse("2020-06-11"));
            } catch (ParseException e9) {
                e9.printStackTrace();
                return false;
            }
        }
    }

    /* renamed from: com.cyberlink.you.friends.FriendsClient$i */
    public interface InterfaceC3051i {
        /* renamed from: a */
        void mo134a(String str, String str2, String str3, String str4);
    }

    /* renamed from: com.cyberlink.you.friends.FriendsClient$j */
    public interface InterfaceC3052j {
        /* renamed from: a */
        void mo12854a();

        void onSuccess();
    }

    /* renamed from: com.cyberlink.you.friends.FriendsClient$k */
    public static abstract class AbstractC3053k {
        /* renamed from: a */
        public abstract void mo12361a(Friend friend);

        /* renamed from: b */
        public abstract void mo12362b(String str, String str2);
    }

    public FriendsClient() {
        this(2);
    }

    /* renamed from: A */
    public static void m15633A() {
        String name = Thread.currentThread().getName();
        if (name.equals("main")) {
            Log.e("FriendsClient", "Call from " + name + ":\n\t" + m15656W0());
            return;
        }
        if (name.startsWith("Thread-")) {
            Log.d("FriendsClient", "Call from " + name + ":\n\t" + m15656W0());
            return;
        }
        Log.i("FriendsClient", "Call from " + name + " (" + Thread.currentThread().getThreadGroup().getName() + ")");
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x00dd  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x013b  */
    /* renamed from: C */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static Pair<String, String> m15634C(String str, List<C6301o> list) {
        C5523x c5523xM21813b;
        C5525z c5525zExecute;
        long jCurrentTimeMillis;
        String str2;
        String strM21231y = null;
        try {
            c5523xM21813b = new C5523x.a().m21820i(str).m21818g(AbstractC5524y.m21823d(C5520u.m21709f("application/x-www-form-urlencoded"), C6311y.m24132b(list, "UTF-8"))).m21815d(HttpHeaders.USER_AGENT, m15667e0(null)).m21813b();
        } catch (IllegalArgumentException e9) {
            e9.printStackTrace();
            c5523xM21813b = null;
        }
        String strM16677m = ULogUtility.m16677m();
        if (c5523xM21813b != null) {
            try {
                jCurrentTimeMillis = System.currentTimeMillis();
                m15633A();
                ULogUtility.m16683s("OkHttpPost", "[" + strM16677m + "] request " + str + m15640F(list));
                c5525zExecute = C3050h.m15741b().mo21256a(c5523xM21813b).execute();
            } catch (Exception e10) {
                e = e10;
                c5525zExecute = null;
            }
            try {
                m15655V0();
                int iM21853x = c5525zExecute.m21853x();
                String str3 = "[" + strM16677m + "] response [" + iM21853x + "] took " + (System.currentTimeMillis() - jCurrentTimeMillis) + " ms";
                if (iM21853x >= 200 && iM21853x < 300) {
                    ULogUtility.m16683s("OkHttpPost", str3);
                } else if (iM21853x < 500) {
                    ULogUtility.m16684t("OkHttpPost", str3);
                } else {
                    ULogUtility.m16676l("OkHttpPost", str3);
                }
            } catch (Exception e11) {
                e = e11;
                e.printStackTrace();
                ULogUtility.m16676l("OkHttpPost", "[" + strM16677m + "] Exception: " + e);
                if (c5525zExecute == null) {
                }
                return Pair.create(strM21231y, str2);
            }
        } else {
            c5525zExecute = null;
        }
        if (c5525zExecute == null) {
            String strValueOf = String.valueOf(c5525zExecute.m21853x());
            try {
                strM21231y = c5525zExecute.m21849f().m21231y();
            } catch (IOException e12) {
                e12.printStackTrace();
                ULogUtility.m16676l("OkHttpPost", "[" + strM16677m + "] httpResponse.body().string() exception.");
            } catch (IllegalCharsetNameException e13) {
                e13.printStackTrace();
                ULogUtility.m16676l("OkHttpPost", "[" + strM16677m + "] httpResponse.body() char set name exception, char set name is " + e13.getCharsetName());
            }
            if (strValueOf.equals("502")) {
                m15674l0();
            }
            str2 = strM21231y;
            strM21231y = strValueOf;
        } else {
            ULogUtility.m16676l("OkHttpPost", "[" + strM16677m + "] httpResponse is null.");
            str2 = null;
        }
        return Pair.create(strM21231y, str2);
    }

    /* JADX WARN: Removed duplicated region for block: B:8:0x000e A[Catch: all -> 0x001b, TryCatch #0 {, blocks: (B:5:0x0005, B:6:0x000a, B:8:0x000e, B:9:0x0016, B:10:0x0019), top: B:15:0x0005 }] */
    /* renamed from: C0 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static void m15635C0(InterfaceC3052j interfaceC3052j, boolean z8) {
        synchronized (f13674f) {
            if (interfaceC3052j != null) {
                f13680l.add(interfaceC3052j);
                if (!f13675g) {
                    new C3043a().start();
                }
                f13675g = true;
            } else {
                if (!f13675g) {
                }
                f13675g = true;
            }
        }
    }

    /* renamed from: D */
    public static Pair<String, String> m15636D(String str, List<C6301o> list) {
        return m15634C(str, list);
    }

    /* renamed from: D0 */
    public static boolean m15637D0(String str, String str2) throws JSONException {
        boolean z8 = false;
        if (f13677i) {
            return false;
        }
        if (f13676h) {
            return true;
        }
        boolean zM15639E0 = m15639E0();
        if (zM15639E0) {
            new C3044b().start();
            z8 = true;
        } else {
            zM15639E0 = m15641F0(str, str2);
        }
        if (str2 == null || !z8) {
            return zM15639E0;
        }
        new C3045c(str, str2).start();
        return true;
    }

    /* renamed from: E */
    public static Pair<String, String> m15638E(String str, List<C6301o> list) {
        return m15636D(str, list);
    }

    /* renamed from: E0 */
    public static boolean m15639E0() throws JSONException {
        boolean zM15644H0 = m15644H0(AbstractC5146g0.m20049g("CommandUrl", "", Globals.m7388i0().getApplicationContext()));
        f13676h = zM15644H0;
        return zM15644H0;
    }

    /* renamed from: F */
    public static String m15640F(List<C6301o> list) {
        if (Globals.m7388i0().m7409C1().booleanValue()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(" | params = {");
        if (list != null) {
            for (int i9 = 0; i9 < list.size(); i9++) {
                sb.append(m15651R0(list.get(i9)));
                if (i9 < list.size() - 1) {
                    sb.append(", ");
                }
            }
        }
        sb.append("}");
        return sb.toString();
    }

    /* renamed from: F0 */
    public static boolean m15641F0(String str, String str2) throws JSONException {
        boolean z8 = false;
        if (f13677i) {
            return false;
        }
        Pair<String, String> pairM15636D = m15636D(f13672d, m15654V(str, str2));
        Log.d("FriendsClient", "[loadCommandUrlFromServer] token= " + str2 + "\n regId = " + str);
        String str3 = (String) pairM15636D.first;
        String str4 = (String) pairM15636D.second;
        HashMap map = new HashMap();
        if (str3 != null) {
            if (str3.equals("200")) {
                boolean zM15644H0 = m15644H0(str4);
                Log.d("FriendsClient", "[loadCommandUrlFromServer] result= " + zM15644H0);
                f13676h = zM15644H0;
                AbstractC5146g0.m20052j("CommandUrl", str4, Globals.m7388i0().getApplicationContext());
                m15663b1();
                z8 = zM15644H0;
            } else if (str3.equals("409")) {
                f13678j = str4;
                f13677i = true;
            }
            map.put("Request_Result", "Success");
        } else {
            map.put("Request_Result", "Fail");
        }
        C6566a.m25155n("U_API_Init", map);
        if (z8) {
            m15677n0();
        }
        return z8;
    }

    /* renamed from: G */
    public static String m15642G(String str, String str2) {
        Log.d("FriendsClient", "findUrlByTableAndField: " + str + "-" + str2);
        if (str == null || str2 == null) {
            Log.e("FriendsClient", "findUrlByTableAndField: table or field is null");
            return null;
        }
        Map<String, Map<String, String>> map = f13673e;
        if (map == null || !map.containsKey(str)) {
            Log.d("FriendsClient", "findUrlByTableAndField: Need to load command url first.");
            return null;
        }
        Map<String, String> map2 = f13673e.get(str);
        if (map2 != null && map2.containsKey(str2)) {
            return map2.get(str2);
        }
        Log.e("FriendsClient", "findUrlByTableAndField: unknown field " + str2);
        return null;
    }

    /* renamed from: G0 */
    public static boolean m15643G0() {
        String strM7449L = Globals.m7388i0().m7449L();
        String strM7587o0 = Globals.m7388i0().m7587o0();
        return (strM7449L == null || strM7449L.isEmpty() || strM7587o0 == null || strM7587o0.isEmpty() || !m15641F0(Globals.m7388i0().m7493U0(), strM7449L)) ? false : true;
    }

    /* renamed from: H0 */
    public static boolean m15644H0(String str) throws JSONException {
        if (str == null || str.isEmpty()) {
            Log.d("FriendsClient", "[parseCommandUrl] parse fail. JSONstr = null or empty");
            return false;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            f13673e = new HashMap();
            Iterator<String> itKeys = jSONObject.keys();
            while (itKeys.hasNext()) {
                String next = itKeys.next();
                try {
                    JSONObject jSONObject2 = jSONObject.getJSONObject(next);
                    HashMap map = new HashMap();
                    m15647K0(jSONObject2, "", map);
                    try {
                        f13673e.put(next, map);
                    } catch (Exception e9) {
                        ULogUtility.m16687w("FriendsClient", "[parseCommandUrl(String)]", "Exception: ", e9.toString());
                    }
                } catch (JSONException unused) {
                    Log.d("FriendsClient", "[parseCommandUrl] table " + next + " parse fail. JSONstr=" + str);
                }
            }
            m15658X0();
            C2889b.m14298h().m14314r();
            return true;
        } catch (JSONException unused2) {
            Log.d("FriendsClient", "[parseCommandUrl] parse fail. JSONstr = " + str.substring(1, Math.min(50, str.length())));
            return false;
        }
    }

    /* renamed from: I0 */
    public static List<Friend> m15645I0(String str) {
        ArrayList arrayList = new ArrayList();
        JSONArray jSONArrayM20196r = C5172p.m20196r(str);
        if (jSONArrayM20196r == null) {
            return arrayList;
        }
        for (int i9 = 0; i9 < jSONArrayM20196r.length(); i9++) {
            try {
                Friend friendM20184f = C5172p.m20184f(jSONArrayM20196r.getJSONObject(i9));
                if (friendM20184f != null) {
                    arrayList.add(friendM20184f);
                }
            } catch (JSONException e9) {
                Log.e("FriendsClient", Log.getStackTraceString(e9));
            }
        }
        return arrayList;
    }

    /* renamed from: K */
    public static long m15646K() {
        return System.currentTimeMillis() + f13679k;
    }

    /* renamed from: K0 */
    public static void m15647K0(JSONObject jSONObject, String str, Map<String, String> map) {
        if (!str.equals("")) {
            str = str + ".";
        }
        Iterator<String> itKeys = jSONObject.keys();
        while (itKeys.hasNext()) {
            String next = itKeys.next();
            try {
                if (jSONObject.get(next) instanceof JSONObject) {
                    m15647K0((JSONObject) jSONObject.get(next), str + next, map);
                } else {
                    map.put(str + next, jSONObject.getString(next));
                }
            } catch (JSONException e9) {
                Log.e("FriendsClient", Log.getStackTraceString(e9));
            }
        }
    }

    /* renamed from: M */
    public static String m15648M() {
        FriendsClient friendsClient = new FriendsClient();
        ArrayList arrayList = new ArrayList();
        arrayList.add(new C6301o("token", Globals.m7388i0().m7449L()));
        Pair<String, String> pairM15731j = friendsClient.m15731j("user", "generateToken", arrayList);
        String str = (String) pairM15731j.first;
        if ("200".equals(str)) {
            try {
                return new JSONObject((String) pairM15731j.second).getString("result");
            } catch (JSONException e9) {
                e9.printStackTrace();
                return null;
            }
        }
        Log.d("FriendsClient", "statusCode = " + str);
        return null;
    }

    /* renamed from: M0 */
    public static List<Friend> m15649M0(String str, String str2) {
        ArrayList arrayList = new ArrayList();
        JSONArray jSONArrayM20196r = C5172p.m20196r(str);
        if (jSONArrayM20196r != null && jSONArrayM20196r.length() != 0) {
            ArrayList arrayList2 = new ArrayList();
            for (int i9 = 0; i9 < jSONArrayM20196r.length(); i9++) {
                try {
                    arrayList2.add(jSONArrayM20196r.getString(i9));
                } catch (JSONException e9) {
                    e9.printStackTrace();
                }
            }
            Map<String, Friend> mapM15002B = C2950b0.m14899A().m15002B(arrayList2);
            arrayList.addAll(mapM15002B.values());
            arrayList2.removeAll(mapM15002B.keySet());
            if (arrayList2.size() > 0 && !C5170o0.m20169d(str2)) {
                ArrayList arrayList3 = new ArrayList();
                int i10 = 0;
                while (i10 < arrayList2.size()) {
                    int size = arrayList2.size() - i10 <= 200 ? arrayList2.size() - i10 : 200;
                    arrayList3.clear();
                    arrayList3.add(new C6301o("token", Globals.m7388i0().m7449L()));
                    arrayList3.add(new C6301o("groupId", str2));
                    for (int i11 = 0; i11 < size; i11++) {
                        arrayList3.add(new C6301o("userId", (String) arrayList2.get(i10)));
                        i10++;
                    }
                    Pair<String, String> pairM15676n = m15676n("group", "membersV2", arrayList3);
                    if ("200".equals(pairM15676n.first)) {
                        ArrayList arrayList4 = new ArrayList(m15645I0((String) pairM15676n.second));
                        C2950b0.m14899A().m15020l(arrayList4, false);
                        arrayList.addAll(arrayList4);
                    }
                }
            }
        }
        return arrayList;
    }

    /* renamed from: P */
    public static Group m15650P(String str) {
        FriendsClient friendsClient = new FriendsClient();
        ArrayList arrayList = new ArrayList();
        arrayList.add(new C6301o("token", Globals.m7388i0().m7449L()));
        arrayList.add(new C6301o("groupId", str));
        Pair<String, String> pairM15731j = friendsClient.m15731j("group", "groupInfo", arrayList);
        Group groupM20186h = null;
        if (!"200".equals(pairM15731j.first)) {
            Log.d("FriendsClient", "statusCode = " + ((String) pairM15731j.first));
            return null;
        }
        JSONArray jSONArrayM20196r = C5172p.m20196r((String) pairM15731j.second);
        if (jSONArrayM20196r != null) {
            for (int i9 = 0; i9 < jSONArrayM20196r.length(); i9++) {
                try {
                    groupM20186h = C5172p.m20186h(jSONArrayM20196r.getJSONObject(i9));
                    Group groupM15077n = C2950b0.m14912k().m15077n(str);
                    if (groupM15077n != null && groupM20186h != null) {
                        groupM20186h.f13714M = groupM15077n.f13714M;
                    }
                } catch (JSONException e9) {
                    e9.printStackTrace();
                }
            }
        }
        return groupM20186h;
    }

    /* renamed from: R0 */
    public static String m15651R0(C6301o c6301o) {
        StringBuilder sb = new StringBuilder();
        sb.append("\"");
        sb.append(c6301o.m24110a());
        sb.append("\":\"");
        sb.append("token".equals(c6301o.m24110a()) ? "***" : c6301o.m24111b());
        sb.append("\"");
        return sb.toString();
    }

    /* renamed from: S0 */
    public static void m15652S0() {
        StrictMode.noteSlowCall("resetCommand");
        synchronized (f13674f) {
            f13675g = false;
            f13676h = false;
        }
        C5522w unused = C3050h.f13701a = null;
    }

    /* renamed from: U */
    public static int m15653U() {
        return 300;
    }

    /* renamed from: V */
    public static List<C6301o> m15654V(String str, String str2) {
        ArrayList arrayList = new ArrayList();
        try {
            String str3 = "for Android";
            String strM20043a = AbstractC5146g0.m20043a(Globals.m7388i0());
            boolean zM16445H1 = CLUtility.m16445H1(Globals.m7388i0().getApplicationContext());
            if (Globals.m7388i0().m7569k2() && zM16445H1) {
                str3 = "for AndriodTablet";
                strM20043a = "A-1-" + C6383t.m24519h(AbstractC5146g0.m20043a(Globals.m7388i0()).getBytes());
            }
            arrayList.add(new C6301o("ap", "U"));
            arrayList.add(new C6301o("version", Globals.m7391n1()));
            arrayList.add(new C6301o("versionType", str3));
            arrayList.add(new C6301o("buildNumber", Globals.m7368J()));
            arrayList.add(new C6301o("locale", Locale.getDefault().getLanguage() + "_" + Locale.getDefault().getCountry()));
            arrayList.add(new C6301o("uuid", strM20043a));
            String str4 = Build.MODEL;
            if (str4 != null) {
                arrayList.add(new C6301o("model", str4));
                arrayList.add(new C6301o("deviceName", str4));
            }
            String str5 = Build.MANUFACTURER;
            if (str5 != null) {
                arrayList.add(new C6301o("vender", str5));
            }
            arrayList.add(new C6301o("userAgent", Globals.m7388i0().m7562j1()));
            arrayList.add(new C6301o("apiVersion", "1.0"));
            if (str == null) {
                str = "";
            }
            arrayList.add(new C6301o("apnsToken", str));
            arrayList.add(new C6301o("token", str2));
        } catch (Exception e9) {
            e9.printStackTrace();
        }
        return arrayList;
    }

    /* renamed from: V0 */
    public static void m15655V0() {
    }

    /* renamed from: W0 */
    public static String m15656W0() {
        StringWriter stringWriter = new StringWriter();
        new Throwable().printStackTrace(new PrintWriter(stringWriter));
        return stringWriter.toString();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v8 */
    /* renamed from: X */
    public static Pair<C2973l0, C2971k0> m15657X(String str, long j9) throws JSONException {
        C2973l0 c2973l0;
        C2973l0 c2973l02;
        C2971k0 c2971k0;
        ArrayList arrayList = new ArrayList();
        arrayList.add(new C6301o("token", Globals.m7388i0().m7449L()));
        arrayList.add(new C6301o("mediaId", String.valueOf(j9)));
        Pair<String, String> pairM15676n = m15676n("media", "mediaInfo", arrayList);
        C2973l0 c2973l03 = null;
        if (!"200".equals((String) pairM15676n.first)) {
            return Pair.create(null, null);
        }
        JSONArray jSONArrayM20196r = C5172p.m20196r((String) pairM15676n.second);
        if (jSONArrayM20196r == null || jSONArrayM20196r.length() < 1) {
            c2973l0 = null;
        } else {
            try {
                JSONObject jSONObject = jSONArrayM20196r.getJSONObject(0);
                C2973l0 c2973l0M20188j = C5172p.m20188j(str, jSONObject);
                if (c2973l0M20188j != null) {
                    try {
                        C2950b0.m14914m().m14712i(c2973l0M20188j);
                        C3061a.a aVarM20192n = C5172p.m20192n(jSONObject);
                        if (!aVarM20192n.m15800a()) {
                            c2971k0 = new C2971k0(String.valueOf(c2973l0M20188j.m15144p()), aVarM20192n);
                            try {
                                C2950b0.m14915n().m15107f(c2971k0);
                                c2973l03 = c2971k0;
                            } catch (JSONException e9) {
                                e = e9;
                                c2973l03 = c2973l0M20188j;
                                c2973l02 = c2971k0;
                                C5154j.m20076j(e);
                                c2973l0 = c2973l02;
                                return Pair.create(c2973l03, c2973l0);
                            }
                        }
                        C5172p.m20192n(jSONObject);
                    } catch (JSONException e10) {
                        e = e10;
                        c2971k0 = c2973l03;
                    }
                }
                c2973l0 = c2973l03;
                c2973l03 = c2973l0M20188j;
            } catch (JSONException e11) {
                e = e11;
                c2973l02 = null;
            }
        }
        return Pair.create(c2973l03, c2973l0);
    }

    /* renamed from: X0 */
    public static void m15658X0() {
        String strM15642G = m15642G("info", "init.url");
        if (!C5170o0.m20170e(strM15642G)) {
            Globals.m7377a3(strM15642G);
        }
        Globals.m7388i0().m7558i3("TW".equalsIgnoreCase(m15642G("info", "user.site")));
        Globals.m7388i0().m7535d3("true".equalsIgnoreCase(m15642G("info", "transcode.enabled")));
        Globals.m7388i0().m7535d3("true".equalsIgnoreCase(m15642G("info", "contact.enabled")));
    }

    /* renamed from: Y */
    public static List<Friend> m15659Y(long j9) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(new C6301o("token", Globals.m7388i0().m7449L()));
        arrayList2.add(new C6301o("groupId", String.valueOf(j9)));
        Pair<String, String> pairM15676n = m15676n("group", "listMembersV2", arrayList2);
        if ("200".equals(pairM15676n.first)) {
            return m15649M0((String) pairM15676n.second, String.valueOf(j9));
        }
        if (pairM15676n.first == null) {
            Log.e("FriendsClient", "[getMembersCloud] fail.");
            return arrayList;
        }
        Log.e("FriendsClient", "[getMembersCloud] fail. response = " + ((String) pairM15676n.first));
        return arrayList;
    }

    /* renamed from: Y0 */
    public static void m15660Y0() {
        m15679o0();
    }

    /* renamed from: b1 */
    public static void m15663b1() {
        Globals.m7388i0().m7491T3(m15642G("chat", "queryMessageAPIVersion"));
    }

    /* renamed from: e0 */
    public static String m15667e0(String str) {
        String str2;
        Globals globalsM7388i0 = Globals.m7388i0();
        String packageName = globalsM7388i0.getPackageName();
        try {
            str2 = globalsM7388i0.getPackageManager().getPackageInfo(packageName, 0).versionName;
        } catch (Exception e9) {
            e9.printStackTrace();
            str2 = "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(packageName);
        sb.append("/");
        sb.append(str2);
        sb.append(" (");
        sb.append("U; Android ");
        sb.append(Build.VERSION.RELEASE);
        sb.append("; ");
        sb.append(Locale.getDefault());
        sb.append("; ");
        sb.append(Build.PRODUCT);
        sb.append(")");
        if (str != null) {
            sb.append(StringUtils.SPACE);
            sb.append(str);
        }
        return sb.toString();
    }

    /* renamed from: k0 */
    public static String m15672k0() {
        return f13678j;
    }

    /* renamed from: l */
    public static Pair<String, String> m15673l(String str, String str2, List<C6301o> list, boolean z8, boolean z9) {
        if (str == null || str2 == null) {
            return Pair.create(null, null);
        }
        if (!f13676h) {
            if (!m15637D0(Globals.m7388i0().m7493U0(), Globals.m7388i0().m7449L())) {
                if (z9) {
                    C6302p.m24124m(str, str2, null, null, false);
                }
                return Pair.create(null, null);
            }
        }
        String strM15642G = m15642G(str, str2);
        if (strM15642G == null) {
            Log.d("FriendsClient", "table=" + str + " field=" + str2 + " command does not exists.");
            return Pair.create(null, null);
        }
        if (Globals.m7380c2() && "userInfoV2".equals(str2)) {
            list.add(new C6301o("reserved", C6383t.m24519h((Globals.m7388i0().m7449L() + Globals.m7388i0().m7449L()).getBytes())));
        }
        Pair<String, String> pairM15636D = m15636D(strM15642G, list);
        String str3 = (String) pairM15636D.first;
        String str4 = (String) pairM15636D.second;
        if (z9) {
            if (z8) {
                C6302p.m24124m(str, str2, str3, str4, z8);
            } else {
                C6302p.m24125n(str, str2, list, str3, str4);
            }
        }
        return Pair.create(str3, str4);
    }

    /* renamed from: l0 */
    public static void m15674l0() {
        Log.e("FriendsClient", "server response 502");
        AbstractC5146g0.m20052j("CommandUrl", "", Globals.m7388i0().getApplicationContext());
        m15652S0();
    }

    /* renamed from: m0 */
    public static void m15675m0(String str) {
        if (str == null) {
            return;
        }
        long j9 = (Long.parseLong(str) * 1000) - System.currentTimeMillis();
        f13679k = j9;
        AbstractC5146g0.m20051i("PreviousDelayTime", Long.valueOf(j9), Globals.m7388i0().getApplicationContext());
    }

    /* renamed from: n */
    public static Pair<String, String> m15676n(String str, String str2, List<C6301o> list) {
        return m15673l(str, str2, list, false, false);
    }

    /* renamed from: n0 */
    public static void m15677n0() {
        m15675m0(m15642G("info", "timestamp"));
    }

    /* renamed from: o */
    public static boolean m15678o() {
        ULogUtility.m16689y("UpdateGCMRegId");
        String strM7449L = Globals.m7388i0().m7449L();
        String strM7587o0 = Globals.m7388i0().m7587o0();
        String strM7493U0 = Globals.m7388i0().m7493U0();
        if (C6383t.m24517f(strM7449L) || C6383t.m24517f(strM7587o0) || C6383t.m24517f(strM7493U0)) {
            ULogUtility.m16689y(" > abort");
            return false;
        }
        SharedPreferences sharedPreferences = Globals.m7388i0().getSharedPreferences("U", 0);
        if (sharedPreferences.getBoolean("needUpdateRegId", true)) {
            m15635C0(new C3047e(sharedPreferences), false);
        } else {
            ULogUtility.m16689y(" > no need");
        }
        return true;
    }

    /* renamed from: o0 */
    public static void m15679o0() {
        String strM7449L = Globals.m7388i0().m7449L();
        if (strM7449L == null || strM7449L.isEmpty()) {
            Log.d("FriendsClient", "[initServerDelayTimeByServerAPI] Fail by cltoken is empty");
            return;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(new C6301o("token", strM7449L));
        Pair<String, String> pairM15731j = new FriendsClient().m15731j("misc", "dateInfo", arrayList);
        String str = (String) pairM15731j.first;
        if (!"200".equals(str)) {
            Log.d("FriendsClient", "[initServerDelayTimeByServerAPI] Fail by statusCode = " + str);
            return;
        }
        String str2 = (String) pairM15731j.second;
        if (str2 == null) {
            Log.d("FriendsClient", "[initServerDelayTimeByServerAPI] Fail by result.second is null");
            return;
        }
        try {
            m15675m0(new JSONObject(str2).getString("timestamp"));
        } catch (JSONException unused) {
            Log.d("FriendsClient", "[initServerDelayTimeByServerAPI] Parse error. JSONstr=" + str2);
        }
    }

    /* renamed from: p0 */
    public static boolean m15680p0() {
        return f13676h;
    }

    /* renamed from: q0 */
    public static boolean m15682q0() {
        return f13677i;
    }

    /* renamed from: r0 */
    public static /* synthetic */ void m15684r0(String str, String str2, List list) {
        if (m15637D0(Globals.m7388i0().m7493U0(), Globals.m7388i0().m7449L())) {
            return;
        }
        C6302p.m24125n(str, str2, list, null, null);
    }

    /* renamed from: s0 */
    public static /* synthetic */ void m15686s0(InterfaceC3051i interfaceC3051i, String str, String str2, String str3, String str4) {
        if ("200".equals(str3)) {
            Log.i("FriendsClient", "[acceptInvite] Accept Success");
        } else {
            Log.e("FriendsClient", "[acceptInvite] Accept Fail");
        }
        if (interfaceC3051i != null) {
            interfaceC3051i.mo134a(str, str2, str3, str4);
        }
    }

    /* renamed from: t0 */
    public static /* synthetic */ void m15688t0(InterfaceC3051i interfaceC3051i, String str, String str2, String str3, String str4) {
        if ("200".equals(str3)) {
            Log.i("FriendsClient", "[blockSuggestList] blockSuggestList Success");
            Globals.m7388i0().m7401A3(true);
        } else {
            Log.e("FriendsClient", "[blockSuggestList] blockSuggestList Fail");
        }
        if (interfaceC3051i != null) {
            interfaceC3051i.mo134a(str, str2, str3, str4);
        }
    }

    /* renamed from: u0 */
    public static /* synthetic */ void m15690u0(InterfaceC3051i interfaceC3051i, String str, String str2, String str3, String str4) {
        if ("200".equals(str3)) {
            Log.i("FriendsClient", "[cancelInvite] Cancel Invitation Success");
            Globals.m7388i0().m7401A3(true);
        } else {
            Log.e("FriendsClient", "[cancelInvite] Cancel Invitation Fail");
        }
        if (interfaceC3051i != null) {
            interfaceC3051i.mo134a(str, str2, str3, str4);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: v0 */
    public /* synthetic */ void m15692v0(Friend friend, AbstractC6381r abstractC6381r) {
        Group groupM15081r = C2950b0.m14912k().m15081r(friend.f13648f);
        if (groupM15081r != null) {
            abstractC6381r.m24506d(groupM15081r);
            return;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(new C6301o("token", Globals.m7388i0().m7449L()));
        arrayList.add(new C6301o("displayName", friend.m15621b()));
        arrayList.add(new C6301o("userId", Long.toString(friend.f13645c)));
        arrayList.add(new C6301o("groupType", "Dual"));
        Pair<String, String> pairM15731j = m15731j("group", "create", arrayList);
        if ("200".equals(pairM15731j.first)) {
            try {
                groupM15081r = C5172p.m20186h(new JSONObject((String) pairM15731j.second).getJSONObject("result"));
                C2950b0.m14912k().m15070g(groupM15081r, true);
            } catch (Exception e9) {
                C5154j.m20076j(e9);
            }
        } else {
            C5154j.m20075i("Cannot create group: " + pairM15731j);
        }
        if (groupM15081r != null) {
            abstractC6381r.m24506d(groupM15081r);
        } else {
            abstractC6381r.m24507e();
        }
    }

    /* renamed from: w0 */
    public static /* synthetic */ void m15693w0(AbstractC3053k abstractC3053k, String str, String str2, String str3, String str4) {
        Group groupM15650P;
        if (!"200".equals(str3)) {
            abstractC3053k.mo12362b(str3, str4);
            return;
        }
        JSONObject jSONObjectM20195q = C5172p.m20195q(str4);
        Friend friendM20184f = C5172p.m20184f(jSONObjectM20195q);
        if (friendM20184f == null) {
            abstractC3053k.mo12362b(str3, str4);
            return;
        }
        C2950b0.m14899A().m15019k(friendM20184f, friendM20184f.f13658p, true);
        Globals.m7388i0().m7643x4(friendM20184f);
        if (Long.valueOf(friendM20184f.f13645c).equals(Globals.m7388i0().m7568k1())) {
            CLUtility.m16527e(Globals.m7372O(), C5172p.m20197s(jSONObjectM20195q));
        } else {
            Group groupM15081r = C2950b0.m14912k().m15081r(friendM20184f.f13648f);
            if (groupM15081r != null && (groupM15650P = m15650P(String.valueOf(groupM15081r.f13727n))) != null) {
                C2950b0.m14912k().m15070g(groupM15650P, true);
                C5180s.m20255h(String.valueOf(groupM15650P.f13727n));
            }
        }
        abstractC3053k.mo12361a(friendM20184f);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: x0 */
    public /* synthetic */ Thread m15694x0(Runnable runnable) {
        return new Thread(runnable, "FriendsClient (" + this.f13683b + ")");
    }

    /* renamed from: y0 */
    public static /* synthetic */ void m15695y0(InterfaceC3051i interfaceC3051i, String str, String str2, String str3, String str4) {
        if ("200".equals(str3)) {
            Log.i("FriendsClient", "[rejectInvite] Decline Success");
        } else {
            Log.e("FriendsClient", "[rejectInvite] Decline Fail");
        }
        if (interfaceC3051i != null) {
            interfaceC3051i.mo134a(str, str2, str3, str4);
        }
    }

    /* renamed from: z0 */
    public static /* synthetic */ void m15696z0(long j9, boolean z8, String str, String str2, String str3, String str4) {
        if ("200".equals(str3)) {
            return;
        }
        ULogUtility.m16670f("FriendsClient", "[setReminder] groupId = " + j9 + " reminder =" + z8 + "fail");
    }

    /* renamed from: A0 */
    public void m15697A0(long j9, InterfaceC3051i interfaceC3051i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new C6301o("token", Globals.m7388i0().m7449L()));
        arrayList.add(new C6301o("groupId", Long.toString(j9)));
        m15734m("group", "leave", arrayList, interfaceC3051i);
    }

    /* renamed from: B */
    public void m15698B(String str, List<C6301o> list, InterfaceC3051i interfaceC3051i) {
        new C3046d(str, list, interfaceC3051i).start();
    }

    /* renamed from: B0 */
    public boolean m15699B0(long j9) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new C6301o("token", Globals.m7388i0().m7449L()));
        arrayList.add(new C6301o("groupId", Long.toString(j9)));
        return "200".equals((String) m15731j("group", "leave", arrayList).first);
    }

    /* renamed from: H */
    public boolean m15700H(String str, String str2) {
        Map<String, String> map;
        Map<String, Map<String, String>> map2 = f13673e;
        if (map2 == null || (map = map2.get("chat")) == null) {
            return false;
        }
        map.put("xmpp.server", str);
        map.put("xmpp.port", str2);
        return true;
    }

    /* renamed from: I */
    public final SharedPreferences m15701I() {
        return Globals.m7388i0().getApplicationContext().getSharedPreferences("cached_invite_list", 0);
    }

    /* renamed from: J */
    public List<InvitationFriend> m15702J(InvitationFriendType invitationFriendType) {
        ArrayList arrayList = new ArrayList();
        String string = m15701I().getString(m15718W(invitationFriendType), "");
        if (!"".equals(string)) {
            try {
                JSONArray jSONArray = new JSONArray(string);
                int length = jSONArray.length();
                if (length > 0) {
                    for (int i9 = 0; i9 < length; i9++) {
                        List<InvitationFriend> listM15703J0 = m15703J0((JSONArray) jSONArray.get(i9));
                        if (listM15703J0 != null) {
                            arrayList.addAll(listM15703J0);
                        }
                    }
                    if (arrayList.size() > 0) {
                        Collections.sort(arrayList, new InvitationFriend.C3058b());
                    }
                }
            } catch (JSONException e9) {
                Log.e("FriendsClient", "[getCachedInviteList] " + e9.getMessage());
            }
        }
        return arrayList;
    }

    /* renamed from: J0 */
    public final List<InvitationFriend> m15703J0(JSONArray jSONArray) {
        if (jSONArray == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (int i9 = 0; i9 < jSONArray.length(); i9++) {
            try {
                InvitationFriend invitationFriendM20187i = C5172p.m20187i(jSONArray.getJSONObject(i9));
                if (invitationFriendM20187i != null) {
                    arrayList.add(invitationFriendM20187i);
                }
            } catch (JSONException e9) {
                Log.e("FriendsClient", "[parseInvitationFriendListFriends] 'invitatingJObj' parse error. " + e9.getMessage());
            }
        }
        return arrayList;
    }

    /* renamed from: L */
    public Friend m15704L(String str) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new C6301o("token", Globals.m7388i0().m7449L()));
        if (str != null && !str.isEmpty()) {
            arrayList.add(new C6301o("userId", str));
        }
        Pair<String, String> pairM15731j = m15731j("friend", "queryFriendV2", arrayList);
        String str2 = (String) pairM15731j.first;
        if ("200".equals(str2)) {
            return C5172p.m20184f(C5172p.m20195q((String) pairM15731j.second));
        }
        Log.d("FriendsClient", "statusCode = " + str2);
        return null;
    }

    /* renamed from: L0 */
    public String m15705L0(Context context) {
        String strM7449L = Globals.m7388i0().m7449L();
        return (String) m15636D(f13672d, m15654V(Globals.m7388i0().m7493U0(), strM7449L)).first;
    }

    /* renamed from: N */
    public List<Friend> m15706N(long j9, boolean z8) {
        List<Friend> listM15645I0;
        ArrayList arrayList = new ArrayList();
        arrayList.add(new C6301o("token", Globals.m7388i0().m7449L()));
        arrayList.add(new C6301o("groupId", String.valueOf(j9)));
        arrayList.add(new C6301o("pageIndex", String.valueOf(1)));
        arrayList.add(new C6301o("pageSize", String.valueOf(200)));
        Pair<String, String> pairM15731j = m15731j("group", "listAdmin", arrayList);
        if ("200".equals(pairM15731j.first)) {
            listM15645I0 = m15645I0((String) pairM15731j.second);
            int iM16553k1 = CLUtility.m16553k1((String) pairM15731j.second);
            int iM16494U0 = CLUtility.m16494U0((String) pairM15731j.second);
            int iM16559m = CLUtility.m16559m(iM16553k1, 200);
            if (iM16553k1 != -1 && iM16494U0 != -1 && iM16553k1 != iM16494U0) {
                for (int i9 = 2; i9 <= iM16559m; i9++) {
                    ArrayList arrayList2 = new ArrayList();
                    arrayList2.add(new C6301o("token", Globals.m7388i0().m7449L()));
                    arrayList2.add(new C6301o("groupId", String.valueOf(j9)));
                    arrayList2.add(new C6301o("pageIndex", String.valueOf(i9)));
                    arrayList2.add(new C6301o("pageSize", String.valueOf(200)));
                    Pair<String, String> pairM15731j2 = m15731j("group", "listAdmin", arrayList2);
                    if ("200".equals(pairM15731j2.first)) {
                        listM15645I0.addAll(m15645I0((String) pairM15731j2.second));
                    }
                }
            }
        } else {
            if (pairM15731j.first != null) {
                Log.e("FriendsClient", "[getGroupAdminFromServer] fail. result = " + ((String) pairM15731j.first));
            } else {
                Log.e("FriendsClient", "[getGroupAdminFromServer] fail.");
            }
            listM15645I0 = null;
        }
        if (z8 && listM15645I0 != null) {
            Iterator<Friend> it = listM15645I0.iterator();
            while (it.hasNext()) {
                C2950b0.m14910i().m15047j(Long.valueOf(j9), Long.valueOf(it.next().f13645c));
            }
        }
        return listM15645I0;
    }

    /* renamed from: N0 */
    public void m15707N0(long j9, final InterfaceC3051i interfaceC3051i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new C6301o("token", Globals.m7388i0().m7449L()));
        arrayList.add(new C6301o("inviteId", Long.toString(j9)));
        m15734m("invite", "declineInvitation", arrayList, new InterfaceC3051i() { // from class: t3.k
            @Override // com.cyberlink.you.friends.FriendsClient.InterfaceC3051i
            /* renamed from: a */
            public final void mo134a(String str, String str2, String str3, String str4) {
                FriendsClient.m15695y0(interfaceC3051i, str, str2, str3, str4);
            }
        });
    }

    /* renamed from: O */
    public GroupAlbumObj m15708O(String str, String str2) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new C6301o("token", Globals.m7388i0().m7449L()));
        arrayList.add(new C6301o("albumId", str));
        Pair<String, String> pairM15733k = m15733k("group", "albumInfo", arrayList, true);
        String str3 = (String) pairM15733k.first;
        GroupAlbumObj groupAlbumObjM20185g = null;
        if (!"200".equals(str3)) {
            Log.d("FriendsClient", "statusCode = " + str3);
            return null;
        }
        JSONArray jSONArrayM20196r = C5172p.m20196r((String) pairM15733k.second);
        if (jSONArrayM20196r != null) {
            for (int i9 = 0; i9 < jSONArrayM20196r.length(); i9++) {
                try {
                    groupAlbumObjM20185g = C5172p.m20185g(jSONArrayM20196r.getJSONObject(i9), str2);
                    break;
                } catch (JSONException e9) {
                    e9.printStackTrace();
                }
            }
        }
        return groupAlbumObjM20185g;
    }

    /* renamed from: O0 */
    public void m15709O0(long j9, long j10, InterfaceC3051i interfaceC3051i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new C6301o("token", Globals.m7388i0().m7449L()));
        arrayList.add(new C6301o("groupId", Long.toString(j9)));
        arrayList.add(new C6301o("userId", Long.toString(j10)));
        m15734m("group", "deleteAdmin", arrayList, interfaceC3051i);
    }

    /* renamed from: P0 */
    public void m15710P0(long j9, long j10, InterfaceC3051i interfaceC3051i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new C6301o("token", Globals.m7388i0().m7449L()));
        arrayList.add(new C6301o("groupId", Long.toString(j9)));
        arrayList.add(new C6301o("userId", Long.toString(j10)));
        m15734m("group", "deleteMembers", arrayList, interfaceC3051i);
    }

    /* renamed from: Q */
    public Group m15711Q(String str) {
        Group groupM15077n;
        FriendsClient friendsClient = new FriendsClient();
        ArrayList arrayList = new ArrayList();
        arrayList.add(new C6301o("token", Globals.m7388i0().m7449L()));
        arrayList.add(new C6301o("inviteURL", str));
        Pair<String, String> pairM15731j = friendsClient.m15731j("group", "groupInfo", arrayList);
        String str2 = (String) pairM15731j.first;
        Group groupM20186h = null;
        if (!"200".equals(str2)) {
            Log.d("FriendsClient", "statusCode = " + str2);
            return null;
        }
        JSONArray jSONArrayM20196r = C5172p.m20196r((String) pairM15731j.second);
        if (jSONArrayM20196r != null) {
            for (int i9 = 0; i9 < jSONArrayM20196r.length(); i9++) {
                try {
                    groupM20186h = C5172p.m20186h(jSONArrayM20196r.getJSONObject(i9));
                    if (groupM20186h != null && (groupM15077n = C2950b0.m14912k().m15077n(String.valueOf(groupM20186h.f13727n))) != null) {
                        groupM20186h.f13714M = groupM15077n.f13714M;
                    }
                } catch (JSONException e9) {
                    e9.printStackTrace();
                }
            }
        }
        return groupM20186h;
    }

    /* renamed from: Q0 */
    public final void m15712Q0(long j9, List<Friend> list) {
        List<Long> listM15098i = C2950b0.m14913l().m15098i(Long.valueOf(j9));
        if (listM15098i == null || listM15098i.size() <= 0) {
            return;
        }
        for (Friend friend : list) {
            Iterator<Long> it = listM15098i.iterator();
            while (true) {
                if (it.hasNext()) {
                    Long next = it.next();
                    if (next.longValue() == friend.f13645c) {
                        listM15098i.remove(next);
                        break;
                    }
                }
            }
        }
        Iterator<Long> it2 = listM15098i.iterator();
        while (it2.hasNext()) {
            C2950b0.m14913l().m15094e(Long.valueOf(j9), it2.next());
        }
    }

    /* renamed from: R */
    public List<Group> m15713R(String... strArr) {
        FriendsClient friendsClient = new FriendsClient();
        ArrayList arrayList = new ArrayList();
        arrayList.add(new C6301o("token", Globals.m7388i0().m7449L()));
        for (String str : strArr) {
            arrayList.add(new C6301o("jid", str));
        }
        Pair<String, String> pairM15731j = friendsClient.m15731j("group", "groupInfo", arrayList);
        String str2 = (String) pairM15731j.first;
        if (!"200".equals(str2)) {
            Log.d("FriendsClient", "statusCode = " + str2);
            return null;
        }
        ArrayList arrayList2 = new ArrayList();
        JSONArray jSONArrayM20196r = C5172p.m20196r((String) pairM15731j.second);
        if (jSONArrayM20196r != null) {
            for (int i9 = 0; i9 < jSONArrayM20196r.length(); i9++) {
                try {
                    Group groupM20186h = C5172p.m20186h(jSONArrayM20196r.getJSONObject(i9));
                    if (groupM20186h != null) {
                        Group groupM15077n = C2950b0.m14912k().m15077n(String.valueOf(groupM20186h.f13727n));
                        if (groupM15077n != null) {
                            groupM20186h.f13714M = groupM15077n.f13714M;
                        }
                        arrayList2.add(groupM20186h);
                    }
                } catch (JSONException e9) {
                    e9.printStackTrace();
                }
            }
        }
        return arrayList2;
    }

    /* renamed from: S */
    public List<Friend> m15714S(long j9, boolean z8) {
        List<Friend> listM15649M0;
        ArrayList arrayList = new ArrayList();
        arrayList.add(new C6301o("token", Globals.m7388i0().m7449L()));
        arrayList.add(new C6301o("groupId", String.valueOf(j9)));
        arrayList.add(new C6301o("pageIndex", String.valueOf(1)));
        arrayList.add(new C6301o("pageSize", String.valueOf(200)));
        Pair<String, String> pairM15731j = m15731j("group", "listMembersV2", arrayList);
        if ("200".equals(pairM15731j.first)) {
            listM15649M0 = m15649M0((String) pairM15731j.second, String.valueOf(j9));
        } else {
            Log.d("FriendsClient", "[queryGroupMemberFromServer] fail");
            listM15649M0 = null;
        }
        if (z8 && listM15649M0 != null) {
            m15712Q0(j9, listM15649M0);
            for (Friend friend : listM15649M0) {
                C2950b0.m14913l().m15101l(Long.valueOf(j9), Long.valueOf(friend.f13645c));
                C2950b0.m14899A().m15021m(friend);
            }
        }
        return listM15649M0;
    }

    /* renamed from: T */
    public List<Friend> m15715T(String str, long j9) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new C6301o("token", Globals.m7388i0().m7449L()));
        arrayList.add(new C6301o("inviteURL", str));
        arrayList.add(new C6301o("pageIndex", String.valueOf(1)));
        arrayList.add(new C6301o("pageSize", String.valueOf(200)));
        Pair<String, String> pairM15731j = m15731j("group", "listMembersV2", arrayList);
        if ("200".equals(pairM15731j.first)) {
            return m15649M0((String) pairM15731j.second, String.valueOf(j9));
        }
        if (pairM15731j.first != null) {
            Log.e("FriendsClient", "[queryGroupMemberFromServer] fail. result = " + ((String) pairM15731j.first));
        } else {
            Log.e("FriendsClient", "[queryGroupMemberFromServer] fail.");
        }
        return null;
    }

    /* renamed from: T0 */
    public void m15716T0(final long j9, final boolean z8) {
        InterfaceC3051i interfaceC3051i = new InterfaceC3051i() { // from class: t3.e
            @Override // com.cyberlink.you.friends.FriendsClient.InterfaceC3051i
            /* renamed from: a */
            public final void mo134a(String str, String str2, String str3, String str4) {
                FriendsClient.m15696z0(j9, z8, str, str2, str3, str4);
            }
        };
        ArrayList arrayList = new ArrayList();
        if (!z8) {
            arrayList.add(new C6301o("unset", ""));
        }
        arrayList.add(new C6301o("token", Globals.m7388i0().m7449L()));
        arrayList.add(new C6301o("groupId", String.valueOf(j9)));
        m15734m("group", "setAsReminder", arrayList, interfaceC3051i);
    }

    /* renamed from: U0 */
    public void m15717U0() {
        ExecutorService executorService = this.f13684c;
        if (executorService != null) {
            executorService.shutdown();
            this.f13684c = null;
        }
    }

    /* renamed from: W */
    public String m15718W(InvitationFriendType invitationFriendType) {
        return C3048f.f13695a[invitationFriendType.ordinal()] != 1 ? "listReceivedInvite" : "listSentUserInvite";
    }

    /* renamed from: Z */
    public void m15719Z(final Friend friend, final AbstractC6381r<Group, Void> abstractC6381r) {
        C6385v.m24526d(new Runnable() { // from class: t3.g
            @Override // java.lang.Runnable
            public final void run() {
                this.f21234b.m15692v0(friend, abstractC6381r);
            }
        });
    }

    /* renamed from: Z0 */
    public boolean m15720Z0(UserInfo userInfo) throws JSONException {
        JSONObject jSONObjectM15770a;
        String string;
        if (userInfo == null || (jSONObjectM15770a = userInfo.m15770a("Email")) == null) {
            return false;
        }
        try {
            string = jSONObjectM15770a.getString("accountId");
        } catch (JSONException e9) {
            e9.printStackTrace();
            string = "";
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(new C6301o("token", Globals.m7388i0().m7449L()));
        arrayList.add(new C6301o("accountId", string));
        String str = (String) m15731j("user", "unbindAccount", arrayList).first;
        if (!"200".equals(str)) {
            Log.d("FriendsClient", "[unBindEmail] fail.");
            return false;
        }
        Log.d("FriendsClient", "[unBindEmail] statusCode = " + str);
        return true;
    }

    /* renamed from: a0 */
    public List<TopicObj> m15721a0(long j9, boolean z8) {
        List<TopicObj> listM20174A;
        ArrayList arrayList = new ArrayList();
        String str = "token";
        arrayList.add(new C6301o("token", Globals.m7388i0().m7449L()));
        arrayList.add(new C6301o("groupId", String.valueOf(j9)));
        arrayList.add(new C6301o("pageIndex", String.valueOf(1)));
        arrayList.add(new C6301o("pageSize", String.valueOf(20)));
        Pair<String, String> pairM15731j = m15731j("groupbulletin", "listPolls", arrayList);
        if ("200".equals(pairM15731j.first)) {
            listM20174A = C5172p.m20174A((String) pairM15731j.second, j9);
            int iM16553k1 = CLUtility.m16553k1((String) pairM15731j.second);
            int iM16494U0 = CLUtility.m16494U0((String) pairM15731j.second);
            int iM16559m = CLUtility.m16559m(iM16553k1, 20);
            if (iM16553k1 != -1 && iM16494U0 != -1 && iM16553k1 != iM16494U0) {
                int i9 = 2;
                while (i9 <= iM16559m) {
                    ArrayList arrayList2 = new ArrayList();
                    arrayList2.add(new C6301o(str, Globals.m7388i0().m7449L()));
                    arrayList2.add(new C6301o("groupId", String.valueOf(j9)));
                    arrayList2.add(new C6301o("pageIndex", String.valueOf(i9)));
                    String str2 = str;
                    arrayList2.add(new C6301o("pageSize", String.valueOf(20)));
                    Pair<String, String> pairM15731j2 = m15731j("groupbulletin", "listPolls", arrayList2);
                    if ("200".equals(pairM15731j2.first)) {
                        listM20174A.addAll(C5172p.m20174A((String) pairM15731j2.second, j9));
                    }
                    i9++;
                    str = str2;
                }
            }
        } else {
            if (pairM15731j.first != null) {
                Log.e("FriendsClient", "[getPollListFromServer] fail. result = " + ((String) pairM15731j.first));
            } else {
                Log.e("FriendsClient", "[getPollListFromServer] fail.");
            }
            listM20174A = null;
        }
        if (z8 && listM20174A != null) {
            Iterator<TopicObj> it = listM20174A.iterator();
            while (it.hasNext()) {
                C2950b0.m14906e().m14978h(it.next(), TopicObj.m14828m());
            }
        }
        return listM20174A;
    }

    /* renamed from: a1 */
    public void m15722a1(JSONArray jSONArray, InvitationFriendType invitationFriendType) {
        String strM15718W = m15718W(invitationFriendType);
        SharedPreferences sharedPreferencesM15701I = m15701I();
        if (jSONArray.length() > 0) {
            sharedPreferencesM15701I.edit().putString(strM15718W, jSONArray.toString()).apply();
        } else {
            sharedPreferencesM15701I.edit().remove(strM15718W).apply();
        }
    }

    /* renamed from: b0 */
    public List<PollOptionObj> m15723b0(long j9, boolean z8) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(new C6301o("token", Globals.m7388i0().m7449L()));
        arrayList2.add(new C6301o("topicId", Long.toString(j9)));
        Pair<String, String> pairM15731j = m15731j("groupbulletin", "listPollOption", arrayList2);
        String str = (String) pairM15731j.first;
        if (!"200".equals(str)) {
            Log.d("FriendsClient", "statusCode = " + str);
            return arrayList;
        }
        List<PollOptionObj> listM20194p = C5172p.m20194p((String) pairM15731j.second);
        if (z8 && listM20194p != null) {
            Iterator<PollOptionObj> it = listM20194p.iterator();
            while (it.hasNext()) {
                C2950b0.m14921t().m15234o(it.next(), PollOptionObj.f13040k);
            }
        }
        return listM20194p;
    }

    /* renamed from: c0 */
    public UserInfo m15724c0() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new C6301o("token", Globals.m7388i0().m7449L()));
        Pair<String, String> pairM15731j = m15731j("user", "userInfoV2", arrayList);
        String str = (String) pairM15731j.first;
        if (!"200".equals(str)) {
            Log.d("FriendsClient", "statusCode = " + str);
            return null;
        }
        if (!Globals.m7388i0().m7409C1().booleanValue()) {
            ULogUtility.m16680p("FriendsClient", "getSelfInfo, result = " + ((String) pairM15731j.second));
        }
        return C5172p.m20197s(C5172p.m20195q((String) pairM15731j.second));
    }

    /* renamed from: c1 */
    public UnlinkEmailActivity.verifyPasswordStatus m15725c1(String str, String str2) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new C6301o("token", Globals.m7388i0().m7449L()));
        arrayList.add(new C6301o(Scopes.EMAIL, str));
        arrayList.add(new C6301o("password", str2));
        String str3 = (String) m15731j("user", "verifyEmailPassword", arrayList).first;
        Log.d("FriendsClient", "[verifyEmailPassword] statusCode = " + str3);
        return "401".equals(str3) ? UnlinkEmailActivity.verifyPasswordStatus.WRONG_PASSWORD : !"200".equals(str3) ? UnlinkEmailActivity.verifyPasswordStatus.SERVER_NETWORK_ERROR : UnlinkEmailActivity.verifyPasswordStatus.VERIFY_SUCCESS;
    }

    /* renamed from: d0 */
    public TopicObj m15726d0(String str, String str2) {
        JSONArray jSONArrayM20196r;
        ArrayList arrayList = new ArrayList();
        arrayList.add(new C6301o("token", Globals.m7388i0().m7449L()));
        arrayList.add(new C6301o("topicId", str2));
        Pair<String, String> pairM15731j = m15731j("groupbulletin", "queryTopic", arrayList);
        String str3 = (String) pairM15731j.first;
        String str4 = (String) pairM15731j.second;
        if (!"200".equals(str3) || (jSONArrayM20196r = C5172p.m20196r(str4)) == null) {
            return null;
        }
        TopicObj topicObjM20204z = null;
        for (int i9 = 0; i9 < jSONArrayM20196r.length(); i9++) {
            try {
                topicObjM20204z = C5172p.m20204z(jSONArrayM20196r.getJSONObject(i9), Long.parseLong(str));
                if (topicObjM20204z != null) {
                    C2950b0.m14906e().m14978h(topicObjM20204z, TopicObj.m14828m());
                }
            } catch (JSONException e9) {
                e9.printStackTrace();
                return null;
            }
        }
        return topicObjM20204z;
    }

    /* renamed from: f0 */
    public Friend m15727f0(String str) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new C6301o("token", Globals.m7388i0().m7449L()));
        if (str != null && !str.isEmpty()) {
            arrayList.add(new C6301o("userId", str));
        }
        Pair<String, String> pairM15731j = m15731j("user", "userInfoV2", arrayList);
        String str2 = (String) pairM15731j.first;
        if ("200".equals(str2)) {
            return C5172p.m20184f(C5172p.m20195q((String) pairM15731j.second));
        }
        Log.d("FriendsClient", "statusCode = " + str2);
        return null;
    }

    public void finalize() throws Throwable {
        super.finalize();
        m15717U0();
    }

    /* renamed from: g0 */
    public void m15728g0(String str, AbstractC3053k abstractC3053k) {
        m15729h0(str, abstractC3053k, false);
    }

    /* renamed from: h0 */
    public void m15729h0(String str, final AbstractC3053k abstractC3053k, boolean z8) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new C6301o("token", Globals.m7388i0().m7449L()));
        if (!C6383t.m24517f(str)) {
            arrayList.add(new C6301o("userId", str));
        }
        if (Globals.m7380c2()) {
            arrayList.add(new C6301o("reserved", C6383t.m24519h((Globals.m7388i0().m7449L() + Globals.m7388i0().m7449L()).getBytes())));
        }
        m15734m("user", z8 ? "userInfo" : "userInfoV2", arrayList, new InterfaceC3051i() { // from class: t3.f
            @Override // com.cyberlink.you.friends.FriendsClient.InterfaceC3051i
            /* renamed from: a */
            public final void mo134a(String str2, String str3, String str4, String str5) {
                FriendsClient.m15693w0(abstractC3053k, str2, str3, str4, str5);
            }
        });
    }

    /* renamed from: i0 */
    public Friend m15730i0(String str) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new C6301o("token", Globals.m7388i0().m7449L()));
        if (str != null && !str.isEmpty()) {
            arrayList.add(new C6301o("jid", str));
        }
        Pair<String, String> pairM15731j = m15731j("user", "userInfoV2", arrayList);
        String str2 = (String) pairM15731j.first;
        if ("200".equals(str2)) {
            return C5172p.m20184f(C5172p.m20195q((String) pairM15731j.second));
        }
        Log.d("FriendsClient", "statusCode = " + str2);
        return null;
    }

    /* renamed from: j */
    public Pair<String, String> m15731j(String str, String str2, List<C6301o> list) {
        return m15733k(str, str2, list, false);
    }

    /* renamed from: j0 */
    public List<C2973l0> m15732j0(String str) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new C6301o("token", Globals.m7388i0().m7449L()));
        arrayList.add(new C6301o("albumId", str));
        arrayList.add(new C6301o("pageIndex", String.valueOf(1)));
        arrayList.add(new C6301o("pageSize", String.valueOf(20)));
        Pair<String, String> pairM15731j = m15731j("media", "listVideo", arrayList);
        if (!"200".equals(pairM15731j.first)) {
            if (pairM15731j.first != null) {
                Log.e("FriendsClient", "[getVideoList] fail. result = " + ((String) pairM15731j.first));
            } else {
                Log.e("FriendsClient", "[getVideoList] fail.");
            }
            return null;
        }
        List<C2973l0> listM20178E = C5172p.m20178E(str, (String) pairM15731j.second);
        int iM16553k1 = CLUtility.m16553k1((String) pairM15731j.second);
        int iM16494U0 = CLUtility.m16494U0((String) pairM15731j.second);
        int iM16559m = CLUtility.m16559m(iM16553k1, 20);
        if (iM16553k1 == -1 || iM16494U0 == -1 || iM16553k1 == iM16494U0) {
            return listM20178E;
        }
        for (int i9 = 2; i9 <= iM16559m; i9++) {
            ArrayList arrayList2 = new ArrayList();
            arrayList2.add(new C6301o("token", Globals.m7388i0().m7449L()));
            arrayList2.add(new C6301o("albumId", str));
            arrayList2.add(new C6301o("pageIndex", String.valueOf(i9)));
            arrayList2.add(new C6301o("pageSize", String.valueOf(20)));
            Pair<String, String> pairM15731j2 = m15731j("media", "listVideo", arrayList2);
            if ("200".equals(pairM15731j2.first)) {
                listM20178E.addAll(C5172p.m20178E(str, (String) pairM15731j2.second));
            }
        }
        return listM20178E;
    }

    /* renamed from: k */
    public final Pair<String, String> m15733k(String str, String str2, List<C6301o> list, boolean z8) {
        return m15673l(str, str2, list, z8, this.f13682a);
    }

    /* renamed from: m */
    public boolean m15734m(final String str, final String str2, final List<C6301o> list, InterfaceC3051i interfaceC3051i) {
        if (str != null && str2 != null) {
            if (!f13676h) {
                Thread thread = new Thread(new Runnable() { // from class: t3.h
                    @Override // java.lang.Runnable
                    public final void run() {
                        FriendsClient.m15684r0(str, str2, list);
                    }
                });
                thread.start();
                try {
                    thread.join();
                } catch (InterruptedException e9) {
                    e9.printStackTrace();
                }
            }
            if (f13676h) {
                RunnableC3049g runnableC3049g = new RunnableC3049g(str, str2, list, interfaceC3051i);
                this.f13683b = str + "-" + str2;
                try {
                    this.f13684c.execute(runnableC3049g);
                    return true;
                } catch (Exception e10) {
                    if (e10.getMessage() == null) {
                        return true;
                    }
                    Log.e("FriendsClient", e10.getMessage());
                    return true;
                }
            }
            if (interfaceC3051i != null) {
                interfaceC3051i.mo134a(str, str2, null, null);
            }
        }
        return false;
    }

    /* renamed from: p */
    public void m15735p(long j9, final InterfaceC3051i interfaceC3051i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new C6301o("token", Globals.m7388i0().m7449L()));
        arrayList.add(new C6301o("inviteId", Long.toString(j9)));
        m15734m("invite", "acceptInvitation", arrayList, new InterfaceC3051i() { // from class: t3.l
            @Override // com.cyberlink.you.friends.FriendsClient.InterfaceC3051i
            /* renamed from: a */
            public final void mo134a(String str, String str2, String str3, String str4) {
                FriendsClient.m15686s0(interfaceC3051i, str, str2, str3, str4);
            }
        });
    }

    /* renamed from: w */
    public void m15736w(long j9, List<Friend> list, InterfaceC3051i interfaceC3051i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new C6301o("token", Globals.m7388i0().m7449L()));
        arrayList.add(new C6301o("groupId", Long.toString(j9)));
        Iterator<Friend> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(new C6301o("userId", Long.toString(it.next().f13645c)));
        }
        m15734m("group", "addAdmin", arrayList, interfaceC3051i);
    }

    /* renamed from: x */
    public void m15737x(long j9, List<Friend> list, InterfaceC3051i interfaceC3051i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new C6301o("token", Globals.m7388i0().m7449L()));
        arrayList.add(new C6301o("groupId", Long.toString(j9)));
        Iterator<Friend> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(new C6301o("userId", Long.toString(it.next().f13645c)));
        }
        arrayList.add(new C6301o("addOrJoin2Group", "add2Group"));
        m15734m("group", "addMembers", arrayList, interfaceC3051i);
    }

    /* renamed from: y */
    public void m15738y(long j9, final InterfaceC3051i interfaceC3051i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new C6301o("token", Globals.m7388i0().m7449L()));
        arrayList.add(new C6301o("userId", Long.toString(j9)));
        m15734m("friend", "blockSuggestion", arrayList, new InterfaceC3051i() { // from class: t3.m
            @Override // com.cyberlink.you.friends.FriendsClient.InterfaceC3051i
            /* renamed from: a */
            public final void mo134a(String str, String str2, String str3, String str4) {
                FriendsClient.m15688t0(interfaceC3051i, str, str2, str3, str4);
            }
        });
    }

    /* renamed from: z */
    public void m15739z(long j9, final InterfaceC3051i interfaceC3051i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new C6301o("token", Globals.m7388i0().m7449L()));
        arrayList.add(new C6301o("inviteId", Long.toString(j9)));
        m15734m("invite", "cancel", arrayList, new InterfaceC3051i() { // from class: t3.j
            @Override // com.cyberlink.you.friends.FriendsClient.InterfaceC3051i
            /* renamed from: a */
            public final void mo134a(String str, String str2, String str3, String str4) {
                FriendsClient.m15690u0(interfaceC3051i, str, str2, str3, str4);
            }
        });
    }

    public FriendsClient(boolean z8) {
        this(2, z8);
    }

    public FriendsClient(int i9) {
        this(i9, false);
    }

    public FriendsClient(int i9, boolean z8) {
        this.f13683b = "";
        this.f13682a = z8;
        this.f13684c = Executors.newFixedThreadPool(i9, new ThreadFactory() { // from class: t3.i
            @Override // java.util.concurrent.ThreadFactory
            public final Thread newThread(Runnable runnable) {
                return this.f21240a.m15694x0(runnable);
            }
        });
    }
}
