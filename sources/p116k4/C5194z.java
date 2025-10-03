package p116k4;

import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.util.Log;
import android.util.Pair;
import android.view.Display;
import android.view.WindowManager;
import com.cyberlink.you.Globals;
import com.cyberlink.you.friends.FriendsClient;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import p201t3.C6301o;
import p246y3.C6788a;

/* renamed from: k4.z */
/* loaded from: classes.dex */
public class C5194z {

    /* renamed from: a */
    public static c f17780a;

    /* renamed from: b */
    public static String f17781b;

    /* renamed from: c */
    public static a f17782c;

    /* renamed from: d */
    public static final String f17783d = Globals.m7370K();

    /* renamed from: k4.z$a */
    public static class a {

        /* renamed from: a */
        public String f17784a;

        /* renamed from: b */
        public long f17785b;

        /* renamed from: c */
        public long f17786c;

        public a(JSONObject jSONObject) throws JSONException {
            this.f17784a = "";
            this.f17785b = 0L;
            this.f17786c = 0L;
            try {
                this.f17784a = jSONObject.getString("status");
                JSONObject jSONObject2 = jSONObject.getJSONObject("noticeStatus");
                this.f17785b = jSONObject2.getLong("maxId");
                this.f17786c = jSONObject2.getLong("lastModified");
            } catch (Exception unused) {
                Log.e("NoticeUtility", "Parse CNoticeStatus Error");
            }
        }

        /* renamed from: a */
        public long m20305a() {
            return this.f17785b;
        }
    }

    /* renamed from: k4.z$b */
    public static class b {

        /* renamed from: a */
        public long f17787a;

        /* renamed from: b */
        public String f17788b;

        /* renamed from: c */
        public String f17789c;

        /* renamed from: d */
        public String f17790d;

        /* renamed from: e */
        public String f17791e;

        /* renamed from: f */
        public String f17792f;

        /* renamed from: g */
        public String f17793g;

        public b(JSONObject jSONObject) {
            try {
                this.f17787a = jSONObject.getLong("nid");
                this.f17788b = jSONObject.getString("shortdesc");
                this.f17789c = jSONObject.getString("desc");
                this.f17790d = jSONObject.getString("noticedate");
                this.f17791e = jSONObject.getString("thumbnail");
                this.f17792f = jSONObject.getString("actionname");
                this.f17793g = jSONObject.getString("actionurl");
            } catch (Exception unused) {
                Log.e("NoticeUtility", "Parse NoticeObject Error");
            }
        }

        /* renamed from: a */
        public String m20306a() {
            return this.f17792f;
        }

        /* renamed from: b */
        public String m20307b() {
            return this.f17793g;
        }

        /* renamed from: c */
        public String m20308c() {
            return this.f17789c;
        }

        /* renamed from: d */
        public long m20309d() {
            return this.f17787a;
        }

        /* renamed from: e */
        public String m20310e() {
            return this.f17790d;
        }

        /* renamed from: f */
        public String m20311f() {
            return this.f17788b;
        }

        /* renamed from: g */
        public String m20312g() throws JSONException {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("nid", this.f17787a);
                jSONObject.put("shortdesc", this.f17788b);
                jSONObject.put("desc", this.f17789c);
                jSONObject.put("noticedate", this.f17790d);
                jSONObject.put("thumbnail", this.f17791e);
                jSONObject.put("actionname", this.f17792f);
                jSONObject.put("actionurl", this.f17793g);
                return jSONObject.toString();
            } catch (JSONException e9) {
                e9.printStackTrace();
                return "";
            }
        }
    }

    /* renamed from: k4.z$c */
    public static class c {

        /* renamed from: a */
        public String f17794a;

        /* renamed from: b */
        public String f17795b;

        /* renamed from: c */
        public String f17796c;

        /* renamed from: d */
        public String f17797d;

        public c(JSONObject jSONObject) {
            try {
                this.f17794a = jSONObject.getString("testbeddomain");
                this.f17795b = jSONObject.getString("productiondomain");
                this.f17796c = jSONObject.getString("feedbackdomain");
                this.f17797d = jSONObject.getString("feedbacktestbeddomain");
            } catch (Exception unused) {
                Log.e("NoticeUtility", "Parse ServiceDomain Error");
            }
        }

        /* renamed from: a */
        public String m20313a() {
            return this.f17795b;
        }

        /* renamed from: b */
        public String m20314b() {
            return this.f17794a;
        }
    }

    /* renamed from: a */
    public static void m20291a(List<C6301o> list) {
        list.add(new C6301o("platform", "android"));
        list.add(new C6301o("product", "U"));
        list.add(new C6301o("version", "1.0"));
        list.add(new C6301o("versiontype", "for android"));
    }

    /* renamed from: b */
    public static String m20292b(Context context) throws JSONException {
        String str = f17783d;
        ArrayList arrayList = new ArrayList();
        String strM20044b = AbstractC5146g0.m20044b(context);
        m20291a(arrayList);
        arrayList.add(new C6301o("phoneid", Globals.m7388i0().m7493U0()));
        arrayList.add(new C6301o("timezone", m20298h()));
        arrayList.add(new C6301o("sr", "YOU141110-02"));
        arrayList.add(new C6301o("lang", C6788a.m25327a()));
        arrayList.add(new C6301o("model", Build.MODEL));
        arrayList.add(new C6301o("vendor", Build.MANUFACTURER));
        Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        Point point = new Point();
        defaultDisplay.getSize(point);
        arrayList.add(new C6301o("resolution", point.x + "X" + point.y));
        arrayList.add(new C6301o("hwid", strM20044b));
        arrayList.add(new C6301o("appversion", Globals.m7391n1()));
        try {
            JSONArray jSONArray = new JSONArray();
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("type", "categoryList");
            jSONObject.put("ver", "1.0");
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("type", "film");
            jSONObject2.put("ver", "1.0");
            jSONArray.put(jSONObject);
            jSONArray.put(jSONObject2);
            arrayList.add(new C6301o("ymkVer", jSONArray.toString()));
        } catch (Exception unused) {
            arrayList.add(new C6301o("ymkVer", ""));
        }
        Pair<String, String> pairM15638E = FriendsClient.m15638E(str, arrayList);
        m20304n();
        return (String) pairM15638E.second;
    }

    /* renamed from: c */
    public static String m20293c(int i9) {
        try {
            return m20294d(f17781b, i9);
        } catch (UnsupportedEncodingException e9) {
            e9.printStackTrace();
            return "";
        } catch (IOException e10) {
            e10.printStackTrace();
            return "";
        } catch (URISyntaxException e11) {
            e11.printStackTrace();
            return "";
        }
    }

    /* renamed from: d */
    public static String m20294d(String str, int i9) throws JSONException {
        String str2 = str + "/service/V2/getNotices";
        ArrayList arrayList = new ArrayList();
        m20291a(arrayList);
        arrayList.add(new C6301o("sdate", "2014/06/01"));
        arrayList.add(new C6301o("sindex", String.valueOf(i9)));
        arrayList.add(new C6301o("count", String.valueOf(30)));
        arrayList.add(new C6301o("lang", C6788a.m25327a()));
        try {
            JSONArray jSONArray = new JSONArray();
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("type", "categoryList");
            jSONObject.put("ver", "1.0");
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("type", "film");
            jSONObject2.put("ver", "1.0");
            jSONArray.put(jSONObject);
            jSONArray.put(jSONObject2);
            arrayList.add(new C6301o("ymkver", jSONArray.toString()));
        } catch (Exception unused) {
            arrayList.add(new C6301o("ymkver", ""));
        }
        Pair<String, String> pairM15638E = FriendsClient.m15638E(str2, arrayList);
        m20304n();
        return (String) pairM15638E.second;
    }

    /* renamed from: e */
    public static List<b> m20295e() {
        ArrayList arrayList = new ArrayList();
        String strM7605r2 = Globals.m7388i0().m7605r2();
        if (strM7605r2.equals("")) {
            return arrayList;
        }
        try {
            JSONObject jSONObject = new JSONObject(strM7605r2);
            int length = jSONObject.length();
            for (int i9 = 0; i9 < length; i9++) {
                arrayList.add(new b(new JSONObject(jSONObject.getString("Notice_" + i9))));
            }
        } catch (JSONException e9) {
            e9.printStackTrace();
            arrayList.clear();
        }
        return arrayList;
    }

    /* renamed from: f */
    public static a m20296f() throws JSONException {
        if (m20299i()) {
            try {
                String strM20297g = m20297g(f17781b);
                if (strM20297g != null && !strM20297g.isEmpty()) {
                    JSONObject jSONObject = new JSONObject(strM20297g);
                    String string = jSONObject.getString("status");
                    if (string == null) {
                        Log.d("NoticeUtility", "[getNoticeStatus] statusString == null");
                    } else {
                        String upperCase = string.toUpperCase(Locale.US);
                        if (upperCase.equals("OK")) {
                            f17782c = m20302l(jSONObject);
                        } else {
                            Log.d("NoticeUtility", "[getNoticeStatus] statusString: " + upperCase);
                        }
                    }
                }
            } catch (Exception e9) {
                Log.d("NoticeUtility", "[getNoticeStatus] " + e9.getMessage());
            }
        } else {
            Log.d("NoticeUtility", "[getNoticeStatus] Service Domain is null");
        }
        return f17782c;
    }

    /* renamed from: g */
    public static String m20297g(String str) {
        ArrayList arrayList = new ArrayList();
        m20291a(arrayList);
        Pair<String, String> pairM15638E = FriendsClient.m15638E(str + "/service/V2/getStatus", arrayList);
        m20304n();
        return (String) pairM15638E.second;
    }

    /* renamed from: h */
    public static String m20298h() {
        return TimeZone.getDefault().getID();
    }

    /* renamed from: i */
    public static boolean m20299i() throws JSONException {
        if (f17780a != null) {
            return true;
        }
        try {
            String strM20292b = m20292b(Globals.m7388i0().getApplicationContext());
            if (strM20292b != null && !strM20292b.isEmpty()) {
                String string = new JSONObject(strM20292b).getString("status");
                if (string == null) {
                    Log.d("NoticeUtility", "[initServiceServer] statusString == null");
                    return false;
                }
                String upperCase = string.toUpperCase(Locale.US);
                if (!upperCase.equals("OK")) {
                    Log.d("NoticeUtility", "[initServiceServer] statusString: " + upperCase);
                }
                c cVarM20300j = m20300j(strM20292b);
                f17780a = cVarM20300j;
                if (cVarM20300j != null) {
                    if (Globals.m7380c2()) {
                        f17781b = f17780a.m20314b();
                    } else {
                        f17781b = f17780a.m20313a();
                    }
                    return true;
                }
            }
        } catch (Exception e9) {
            Log.d("NoticeUtility", "[initServiceServer] " + e9.getMessage());
        }
        return false;
    }

    /* renamed from: j */
    public static c m20300j(String str) {
        try {
            return new c(new JSONObject(str));
        } catch (Exception e9) {
            e9.printStackTrace();
            return null;
        }
    }

    /* renamed from: k */
    public static List<b> m20301k(JSONArray jSONArray) {
        ArrayList arrayList = new ArrayList();
        for (int i9 = 0; i9 < jSONArray.length(); i9++) {
            try {
                arrayList.add(new b(jSONArray.getJSONObject(i9)));
            } catch (Exception e9) {
                Log.e("NoticeUtility", " parse notices object error" + e9.getMessage());
            }
        }
        return arrayList;
    }

    /* renamed from: l */
    public static a m20302l(JSONObject jSONObject) {
        try {
            return new a(jSONObject);
        } catch (Exception e9) {
            Log.d("NoticeUtility", "[parseNoticeStatusResult] parse notices statuse error : " + e9.getMessage());
            return null;
        }
    }

    /* renamed from: m */
    public static int m20303m(String str) {
        if (str == null || str.isEmpty()) {
            return -1;
        }
        try {
            return new JSONObject(str).getInt("totalCount");
        } catch (Exception e9) {
            Log.d("NoticeUtility", "[parseTotalCount] exception e = " + e9.getMessage());
            return -1;
        }
    }

    /* renamed from: n */
    public static void m20304n() {
    }
}
