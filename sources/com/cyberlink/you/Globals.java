package com.cyberlink.you;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.PowerManager;
import android.os.StrictMode;
import android.os.strictmode.Violation;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import android.webkit.WebView;
import com.cyberlink.meeting.doserver.NetworkManager;
import com.cyberlink.p030U.R;
import com.cyberlink.uno.log.UNOFileLog;
import com.cyberlink.util.DeviceCapability;
import com.cyberlink.you.Globals;
import com.cyberlink.you.activity.GDPRActivity;
import com.cyberlink.you.activity.OldVersionNotifyActivity;
import com.cyberlink.you.activity.SettingActivity;
import com.cyberlink.you.activity.friend.C2143a;
import com.cyberlink.you.activity.splash.SplashActivity;
import com.cyberlink.you.chat.AsyncTaskC2897h0;
import com.cyberlink.you.chat.C2907m0;
import com.cyberlink.you.chat.NotificationHelper;
import com.cyberlink.you.chat.p034e.C2889b;
import com.cyberlink.you.database.C2950b0;
import com.cyberlink.you.effect.C3008b;
import com.cyberlink.you.friends.C3062b;
import com.cyberlink.you.friends.Friend;
import com.cyberlink.you.friends.FriendsClient;
import com.cyberlink.you.friends.Group;
import com.cyberlink.you.friends.UserInfo;
import com.cyberlink.you.utility.CLUtility;
import com.cyberlink.you.utility.ULogUtility;
import com.cyberlink.you.utility.UploadMultipleChatMediaHelperQueue;
import com.google.android.gms.analytics.Tracker;
import com.google.firebase.FirebaseApp;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import p047d5.C4677a;
import p074g2.C4831a;
import p116k4.AbstractC5146g0;
import p116k4.C5127a;
import p116k4.C5143f0;
import p116k4.C5154j;
import p116k4.C5170o0;
import p173q2.C6132f;
import p182r2.C6196d0;
import p193s4.C6264b;
import p201t3.C6301o;
import p201t3.C6303q;
import p209u2.C6374k;
import p209u2.C6383t;
import p209u2.C6385v;
import p218v2.C6452a;
import p227w2.C6518a;
import p236x2.C6566a;

/* loaded from: classes.dex */
public class Globals extends Application {

    /* renamed from: A */
    public static C5143f0 f7282A = null;

    /* renamed from: B */
    public static C5143f0 f7283B = null;

    /* renamed from: C */
    public static C5143f0 f7284C = null;

    /* renamed from: D */
    public static C5143f0 f7285D = null;

    /* renamed from: k */
    public static boolean f7294k = true;

    /* renamed from: p */
    public static Globals f7299p;

    /* renamed from: s */
    public static String f7302s;

    /* renamed from: t */
    public static String f7303t;

    /* renamed from: u */
    public static String f7304u;

    /* renamed from: x */
    public static C5143f0 f7307x;

    /* renamed from: y */
    public static C5143f0 f7308y;

    /* renamed from: z */
    public static C5143f0 f7309z;

    /* renamed from: b */
    public Map<Integer, C3008b.d> f7310b;

    /* renamed from: c */
    public String f7311c = "";

    /* renamed from: d */
    public C6303q f7312d = null;

    /* renamed from: e */
    public HashMap<Object, Tracker> f7313e = new HashMap<>();

    /* renamed from: f */
    public boolean f7314f = false;

    /* renamed from: g */
    public boolean f7315g = false;

    /* renamed from: h */
    public AsyncTaskC2897h0 f7316h = null;

    /* renamed from: i */
    public Map<String, Friend> f7317i = null;

    /* renamed from: j */
    public Bitmap f7318j;

    /* renamed from: l */
    public static final String f7295l = System.getProperty("line.separator");

    /* renamed from: m */
    public static int f7296m = 1;

    /* renamed from: n */
    public static int f7297n = 1;

    /* renamed from: o */
    public static final boolean f7298o = true;

    /* renamed from: q */
    public static Boolean f7300q = Boolean.FALSE;

    /* renamed from: r */
    public static int f7301r = 0;

    /* renamed from: v */
    public static final C6452a f7305v = new C6452a();

    /* renamed from: w */
    public static ArrayList<String> f7306w = new ArrayList<>();

    /* renamed from: E */
    public static String f7286E = null;

    /* renamed from: F */
    public static long f7287F = 0;

    /* renamed from: G */
    public static long f7288G = 0;

    /* renamed from: H */
    public static boolean f7289H = false;

    /* renamed from: I */
    public static boolean f7290I = false;

    /* renamed from: J */
    public static boolean f7291J = false;

    /* renamed from: K */
    public static String f7292K = "";

    /* renamed from: L */
    public static String f7293L = "";

    /* renamed from: com.cyberlink.you.Globals$a */
    public class C1407a implements FriendsClient.InterfaceC3051i {

        /* renamed from: a */
        public List<Long> f7319a = new ArrayList();

        /* renamed from: b */
        public List<Long> f7320b = new ArrayList();

        /* renamed from: c */
        public final /* synthetic */ String f7321c;

        /* renamed from: d */
        public final /* synthetic */ FriendsClient f7322d;

        public C1407a(String str, FriendsClient friendsClient) {
            this.f7321c = str;
            this.f7322d = friendsClient;
        }

        @Override // com.cyberlink.you.friends.FriendsClient.InterfaceC3051i
        /* renamed from: a */
        public void mo134a(String str, String str2, String str3, String str4) {
            if ("200".equals(str3)) {
                int iM16553k1 = CLUtility.m16553k1(str4);
                if (!m7653c(str4)) {
                    Log.e("Globals", "[getUserStickerOrderListAndSaveToDB] parasJson fail at page 1");
                }
                for (int i9 = 2; (i9 - 1) * 200 < iM16553k1; i9++) {
                    ArrayList arrayList = new ArrayList();
                    arrayList.add(new C6301o("token", this.f7321c));
                    arrayList.add(new C6301o("pageIndex", String.valueOf(i9)));
                    arrayList.add(new C6301o("pageSize", String.valueOf(200)));
                    Pair<String, String> pairM15731j = this.f7322d.m15731j("sticker", "user.pack.list", arrayList);
                    String str5 = (String) pairM15731j.first;
                    String str6 = (String) pairM15731j.second;
                    if ("200".equals(str5) && !m7653c(str6)) {
                        Log.e("Globals", "[getUserStickerOrderListAndSaveToDB] parasJson fail at page " + i9);
                    }
                }
                C2950b0.m14925x().m15300r(m7652b());
                C3062b.m15820t(this.f7319a);
                ArrayList arrayList2 = new ArrayList();
                Iterator<Long> it = this.f7320b.iterator();
                while (it.hasNext()) {
                    arrayList2.add(String.valueOf(it.next().longValue()));
                }
                ArrayList unused = Globals.f7306w = arrayList2;
            }
            this.f7322d.m15717U0();
        }

        /* renamed from: b */
        public final String m7652b() {
            StringBuilder sb = new StringBuilder();
            sb.append("[");
            Iterator<Long> it = this.f7319a.iterator();
            while (it.hasNext()) {
                sb.append(it.next().longValue());
                sb.append(",");
            }
            sb.delete(sb.length() - 1, sb.length());
            sb.append("]");
            return sb.toString();
        }

        /* renamed from: c */
        public final boolean m7653c(String str) throws JSONException {
            try {
                JSONArray jSONArray = new JSONObject(str).getJSONArray("results");
                for (int i9 = 0; i9 < jSONArray.length(); i9++) {
                    JSONObject jSONObject = (JSONObject) jSONArray.get(i9);
                    long j9 = jSONObject.getLong("packId");
                    if ("Purchase".equals(jSONObject.getString("purchaseType"))) {
                        this.f7320b.add(Long.valueOf(j9));
                    }
                    this.f7319a.add(Long.valueOf(j9));
                }
                return true;
            } catch (JSONException unused) {
                return false;
            }
        }
    }

    /* renamed from: com.cyberlink.you.Globals$b */
    public static class C1408b<T> {

        /* renamed from: a */
        public Map<String, List<T>> f7324a;

        /* renamed from: com.cyberlink.you.Globals$b$a */
        public static class a {

            /* renamed from: a */
            public static final C1408b f7325a = new C1408b(null);
        }

        public /* synthetic */ C1408b(C1407a c1407a) {
            this();
        }

        /* renamed from: b */
        public static C1408b m7655b() {
            return a.f7325a;
        }

        /* renamed from: c */
        public List<T> m7656c(String str) {
            List<T> list = this.f7324a.get(str);
            this.f7324a.remove(str);
            return list;
        }

        /* renamed from: d */
        public void m7657d(String str, List<T> list) {
            this.f7324a.put(str, list);
        }

        public C1408b() {
            this.f7324a = new HashMap();
        }
    }

    /* renamed from: J */
    public static String m7368J() {
        return f7304u;
    }

    /* renamed from: J2 */
    public static void m7369J2(String str) {
        f7307x.m20040m("DoInitUrl", str);
    }

    /* renamed from: K */
    public static String m7370K() {
        return m7380c2() ? "http://apptest.cyberlink.com/service/V2/init" : "http://app.cyberlink.com/service/V2/init";
    }

    /* renamed from: N */
    public static String m7371N() {
        return m7388i0().m7463O0().equals("CHN") ? m7380c2() ? "https://um-demo2-api.presenterlink.com/api/init" : "https://um-api.presenterlink.com/api/init" : !C5170o0.m20170e(m7386h0()) ? m7386h0() : m7380c2() ? "https://u-demo2-api.cyberlink.com/api/init" : "https://u-api.cyberlink.com/api/init";
    }

    /* renamed from: O */
    public static Context m7372O() {
        return f7299p.getApplicationContext();
    }

    /* renamed from: W */
    public static String m7373W() {
        return f7307x.m20035h("DoInitUrl", "");
    }

    /* renamed from: X0 */
    public static Resources m7374X0() {
        return m7372O().getResources();
    }

    /* renamed from: Z0 */
    public static String m7375Z0(int i9) {
        return m7374X0().getString(i9);
    }

    /* renamed from: a3 */
    public static void m7377a3(String str) {
        f7307x.m20040m("InitUrl", str);
    }

    /* renamed from: c2 */
    public static boolean m7380c2() {
        return false;
    }

    /* renamed from: g1 */
    public static int m7383g1() {
        return f7301r;
    }

    /* renamed from: g4 */
    public static void m7384g4(int i9) {
        f7301r = i9;
    }

    /* renamed from: h0 */
    public static String m7386h0() {
        return f7307x.m20035h("InitUrl", "");
    }

    /* renamed from: h1 */
    public static String m7387h1() {
        return f7302s;
    }

    /* renamed from: i0 */
    public static Globals m7388i0() {
        return f7299p;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: l2 */
    public /* synthetic */ void m7389l2(boolean z8) {
        boolean zBooleanValue = m7409C1().booleanValue();
        boolean zBooleanValue2 = m7489T1().booleanValue();
        m7641x2();
        String strM7649z0 = m7649z0();
        f7284C.m20028a();
        File file = new File(getApplicationContext().getCacheDir().getParent());
        if (file.exists()) {
            for (String str : file.list()) {
                if (!str.equals("lib") && !str.equals("files")) {
                    if (CLUtility.m16447I(new File(file, str))) {
                        Log.i("Globals", "Delete folder : " + str + " complete.");
                    } else {
                        Log.i("Globals", "Delete folder : " + str + " fail.");
                    }
                }
            }
        }
        if (z8) {
            zBooleanValue = !zBooleanValue;
        }
        m7435H2(zBooleanValue);
        m7496U3(zBooleanValue2);
        m7630v3(strM7649z0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: m2 */
    public /* synthetic */ void m7390m2() {
        C6132f.m23491a(this).m24388b();
    }

    /* renamed from: n1 */
    public static String m7391n1() {
        return f7303t;
    }

    /* renamed from: n2 */
    public static /* synthetic */ void m7392n2(Violation violation) {
        ULogUtility.m16680p("Globals", "NonSdkApiUsage:" + violation.getMessage());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: o2 */
    public /* synthetic */ void m7393o2(Context context) {
        try {
            this.f7311c = new WebView(context).getSettings().getUserAgentString();
            ULogUtility.m16676l("Globals", "[UserAgent - Post to Main] gen UserAgent is : " + this.f7311c);
        } catch (NoClassDefFoundError e9) {
            ULogUtility.m16676l("Globals", "[UserAgent - Post to Main] NoClassDefFoundError");
            e9.printStackTrace();
        }
        f7300q = Boolean.FALSE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: p2 */
    public /* synthetic */ void m7394p2() {
        if (!f7305v.m24701c() || m7460N1()) {
            return;
        }
        UploadMultipleChatMediaHelperQueue.m16892F();
    }

    /* renamed from: y1 */
    public static void m7395y1() {
    }

    /* renamed from: z1 */
    public static boolean m7396z1() {
        return f7305v.m24700b();
    }

    /* renamed from: A */
    public final void m7397A() {
        if (Build.VERSION.SDK_INT >= 28) {
            StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectNonSdkApiUsage().penaltyListener(C6385v.f21553a, new StrictMode.OnVmViolationListener() { // from class: v2.y
                @Override // android.os.StrictMode.OnVmViolationListener
                public final void onVmViolation(Violation violation) {
                    Globals.m7392n2(violation);
                }
            }).build());
        }
    }

    /* renamed from: A0 */
    public long m7398A0() {
        return f7307x.m20034g("suggestionLastCreateTime", -1L);
    }

    /* renamed from: A1 */
    public boolean m7399A1(Friend friend, String str) {
        String strM7532d0 = m7388i0().m7532d0();
        if (friend != null && strM7532d0 != null) {
            Log.d("Globals", "[isContainGCMFilter] GCMFilterText =  " + strM7532d0);
            String[] strArrSplit = strM7532d0.split(",");
            int length = strArrSplit.length;
            for (int i9 = 0; i9 < length; i9++) {
                if (m7596q(friend, str, strArrSplit[i9].toLowerCase(Locale.getDefault()).trim())) {
                    return true;
                }
            }
        }
        return false;
    }

    /* renamed from: A2 */
    public void m7400A2(long j9) {
        f7307x.m20039l("bindAccountCheckTime", j9);
    }

    /* renamed from: A3 */
    public void m7401A3(boolean z8) {
        f7307x.m20036i("needToUpdateSuggestionList", z8);
    }

    /* renamed from: B */
    public void m7402B() {
        m7407C(false);
    }

    /* renamed from: B0 */
    public int m7403B0() {
        return f7307x.m20032e("meetingAttendee", 0);
    }

    /* renamed from: B1 */
    public boolean m7404B1() {
        return f7307x.m20031d("isDatabaseCorrupt", false);
    }

    /* renamed from: B2 */
    public void m7405B2(String str) {
        f7307x.m20040m("CLtoken", str);
        if (str != null) {
            C2889b.m14298h().m14314r();
        }
    }

    /* renamed from: B3 */
    public void m7406B3(boolean z8) {
        f7307x.m20036i("needToUpdateTopSticker", z8);
    }

    /* renamed from: C */
    public void m7407C(boolean z8) {
        ULogUtility.m16680p("Globals", "forceLogout");
        m7635w2();
        FriendsClient.m15652S0();
        Hashtable hashtable = new Hashtable();
        SharedPreferences sharedPreferences = getSharedPreferences("U", 0);
        String string = sharedPreferences.getString("meetingLtiEventId", "");
        String string2 = sharedPreferences.getString("meetingLtiToken", "");
        String string3 = sharedPreferences.getString("meetingLtiUserName", "");
        ULogUtility.m16683s("Globals", "EventId: " + string + " / ltiToken: " + string2 + " / UserName: " + string3);
        hashtable.put("meetingLtiEventId", string);
        hashtable.put("meetingLtiToken", string2);
        hashtable.put("meetingLtiUserName", string3);
        ArrayList arrayList = new ArrayList();
        arrayList.add("notificationChannelVersion");
        f7307x.m20029b(arrayList);
        f7309z.m20028a();
        f7282A.m20028a();
        m7644y(z8);
        f7291J = false;
        C2907m0.m14454I().m14496X();
        C6196d0.m23692d().m23694c();
        C2889b.m14298h().m14313q();
        m7595p4();
        m7617t2(hashtable);
    }

    /* renamed from: C0 */
    public int m7408C0() {
        return f7307x.m20032e("meetingDuration", 0);
    }

    /* renamed from: C1 */
    public Boolean m7409C1() {
        return Boolean.valueOf(f7284C.m20031d("isDatabaseEncryptEnable", false));
    }

    /* renamed from: C2 */
    public void m7410C2(String str, String str2) {
        ULogUtility.m16664G("setCV:" + str, str2);
        f7307x.m20040m("cv", str);
    }

    /* renamed from: C3 */
    public void m7411C3(boolean z8) {
        f7307x.m20036i("isNewAddFriend", z8);
    }

    /* renamed from: D */
    public void m7412D() {
        Intent intent = new Intent(getApplicationContext(), (Class<?>) OldVersionNotifyActivity.class);
        intent.addFlags(268468224);
        startActivity(intent);
    }

    /* renamed from: D0 */
    public boolean m7413D0() {
        return f7307x.m20031d("needToUpdateNewSticker", true);
    }

    /* renamed from: D1 */
    public boolean m7414D1() {
        return (getApplicationInfo().flags & 2) != 0;
    }

    /* renamed from: D2 */
    public void m7415D2(int i9) {
        f7307x.m20037j("CountToShowUpgradePro", i9);
    }

    /* renamed from: D3 */
    public void m7416D3(boolean z8) {
        f7307x.m20036i("isNewNotice", z8);
    }

    /* renamed from: E */
    public String m7417E(final Context context) {
        String strM20035h = f7307x.m20035h("useragent", "");
        if (!strM20035h.isEmpty()) {
            this.f7311c = strM20035h;
            f7300q = Boolean.TRUE;
            ULogUtility.m16670f("Globals", "[UserAgent] is not empty : " + this.f7311c);
            return this.f7311c;
        }
        f7300q = Boolean.FALSE;
        if (this.f7311c.isEmpty()) {
            if (Thread.currentThread().getName().equalsIgnoreCase("main")) {
                try {
                    this.f7311c = new WebView(context).getSettings().getUserAgentString();
                    ULogUtility.m16670f("Globals", "[UserAgent - Main] gen UserAgent is : " + this.f7311c);
                } catch (Exception e9) {
                    ULogUtility.m16676l("Globals", "[UserAgent - Main] Exception");
                    e9.printStackTrace();
                } catch (NoClassDefFoundError e10) {
                    ULogUtility.m16676l("Globals", "[UserAgent - Main] NoClassDefFoundError");
                    e10.printStackTrace();
                }
            } else {
                f7300q = Boolean.TRUE;
                ((Activity) context).runOnUiThread(new Runnable() { // from class: v2.x
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f21807b.m7393o2(context);
                    }
                });
            }
        }
        for (int i9 = 0; f7300q.booleanValue() && i9 < 10; i9++) {
            try {
                Thread.sleep(100L);
            } catch (Exception e11) {
                Log.e("Globals", Log.getStackTraceString(e11));
            }
        }
        String str = this.f7311c;
        if (str != null && !str.isEmpty()) {
            ULogUtility.m16670f("Globals", "[UserAgent] save to local : " + this.f7311c);
            f7307x.m20040m("useragent", this.f7311c);
        }
        return this.f7311c;
    }

    /* renamed from: E0 */
    public boolean m7418E0() {
        return f7307x.m20031d("needToUpdateTopSticker", true);
    }

    /* renamed from: E1 */
    public boolean m7419E1() {
        return f7307x.m20031d("isEnableServerTransCode", true);
    }

    /* renamed from: E2 */
    public void m7420E2(String str) {
        f7309z.m20040m("currentNotification", str);
    }

    /* renamed from: E3 */
    public void m7421E3(boolean z8) {
        f7307x.m20036i("isNewSetting", z8);
    }

    /* renamed from: F */
    public int m7422F() {
        int iM20032e = f7307x.m20032e("notificationId", 0);
        f7307x.m20037j("notificationId", iM20032e + 1);
        return iM20032e;
    }

    @SuppressLint({"NewApi"})
    /* renamed from: F0 */
    public int m7423F0() {
        Context applicationContext = m7388i0().getApplicationContext();
        return applicationContext.getResources().getColor(R.color.you_color_normal_blue, applicationContext.getTheme());
    }

    /* renamed from: F1 */
    public boolean m7424F1() {
        return f7298o && !m7479R1();
    }

    /* renamed from: F2 */
    public void m7425F2(boolean z8) {
        f7291J = z8;
    }

    /* renamed from: F3 */
    public void m7426F3(boolean z8) {
        f7307x.m20036i("isNewSticker", z8);
    }

    /* renamed from: G */
    public List<String> m7427G() {
        String strM20035h = f7307x.m20035h("accountHoldProduct", "[]");
        ArrayList arrayList = new ArrayList();
        try {
            JSONArray jSONArray = new JSONArray(strM20035h);
            for (int i9 = 0; i9 < jSONArray.length(); i9++) {
                arrayList.add(jSONArray.getString(i9));
            }
        } catch (JSONException e9) {
            e9.printStackTrace();
        }
        return arrayList;
    }

    /* renamed from: G0 */
    public int m7428G0() {
        return f7307x.m20032e("notificationChannelVersion", 0);
    }

    /* renamed from: G1 */
    public boolean m7429G1() {
        return f7307x.m20031d("FaceId", false);
    }

    /* renamed from: G2 */
    public void m7430G2(boolean z8) {
        f7307x.m20036i("isDatabaseCorrupt", z8);
    }

    /* renamed from: G3 */
    public void m7431G3(boolean z8) {
        f7307x.m20036i("setting_notification", z8);
    }

    /* renamed from: H */
    public int m7432H(Group group) {
        int iM20032e = f7283B.m20032e(String.valueOf(group.f13727n), -1);
        if (iM20032e != -1) {
            return iM20032e;
        }
        int iM7422F = m7388i0().m7422F();
        f7283B.m20037j(String.valueOf(group.f13727n), iM7422F);
        return iM7422F;
    }

    /* renamed from: H0 */
    public Uri m7433H0(boolean z8) {
        if (!f7307x.m20031d("setting_notification_sound", SettingActivity.f8943R) || z8) {
            return null;
        }
        if (!f7307x.m20035h("notificationSoundV2", "").isEmpty()) {
            return Uri.parse(f7307x.m20035h("notificationSoundV2", ""));
        }
        Uri uri = Uri.parse("android.resource://" + getPackageName() + "/raw/messagenotify_futuristic");
        m7436H3(uri);
        return uri;
    }

    /* renamed from: H1 */
    public boolean m7434H1() {
        return f7307x.m20031d("faceIdDebug", false);
    }

    /* renamed from: H2 */
    public final void m7435H2(boolean z8) {
        f7284C.m20036i("isDatabaseEncryptEnable", z8);
    }

    /* renamed from: H3 */
    public void m7436H3(Uri uri) {
        f7307x.m20040m("notificationSoundV2", uri.toString());
        NotificationHelper.m14086b0(uri);
    }

    /* renamed from: I */
    public boolean m7437I() {
        return f7307x.m20031d("isAppPasswordLocked", false);
    }

    /* renamed from: I0 */
    public String m7438I0() {
        return f7307x.m20035h("oldBuildNumber", "");
    }

    /* renamed from: I1 */
    public boolean m7439I1() {
        long jM20034g = f7307x.m20034g("AlertTime", 0L);
        long jM20034g2 = f7307x.m20034g("PervoiusAlertTime", 0L);
        long jCurrentTimeMillis = System.currentTimeMillis();
        f7307x.m20039l("PervoiusAlertTime", jCurrentTimeMillis);
        if (jCurrentTimeMillis - jM20034g >= 1000) {
            if (jCurrentTimeMillis - jM20034g2 < 3000) {
                return true;
            }
            f7307x.m20039l("AlertTime", jCurrentTimeMillis);
        }
        return false;
    }

    /* renamed from: I2 */
    public void m7440I2(JSONObject jSONObject) {
        f7282A.m20040m("DeletedMessageList", jSONObject.toString());
    }

    /* renamed from: I3 */
    public void m7441I3(String str) {
        f7307x.m20040m("oldBuildNumber", str);
    }

    /* renamed from: J0 */
    public String m7442J0() {
        return f7307x.m20035h("organizationId", "");
    }

    /* renamed from: J1 */
    public boolean m7443J1() {
        return f7307x.m20031d("isGCMFilter", false);
    }

    /* renamed from: J3 */
    public void m7444J3(String str) {
        f7307x.m20040m("organizationId", str);
    }

    /* renamed from: K0 */
    public List<String> m7445K0() {
        String strM20035h = f7307x.m20035h("listOrganization", "");
        ArrayList arrayList = new ArrayList();
        if (strM20035h != null) {
            try {
                JSONArray jSONArray = new JSONArray(strM20035h);
                for (int i9 = 0; i9 < jSONArray.length(); i9++) {
                    arrayList.add(jSONArray.getString(i9));
                }
            } catch (JSONException e9) {
                e9.printStackTrace();
            }
        }
        return arrayList;
    }

    /* renamed from: K1 */
    public boolean m7446K1() {
        return f7294k;
    }

    /* renamed from: K2 */
    public void m7447K2(String str) {
        f7307x.m20040m("DoUtoken", str);
    }

    /* renamed from: K3 */
    public void m7448K3(String str) {
        f7307x.m20040m("listOrganization", str);
    }

    /* renamed from: L */
    public String m7449L() {
        return f7307x.m20035h("CLtoken", "");
    }

    /* renamed from: L0 */
    public String m7450L0() {
        return f7307x.m20035h("PSTN", "");
    }

    /* renamed from: L1 */
    public boolean m7451L1() {
        return f7307x.m20031d("heartbeatEnableDebug", true);
    }

    /* renamed from: L2 */
    public void m7452L2(boolean z8) {
        f7307x.m20036i("FaceId", z8);
    }

    /* renamed from: L3 */
    public void m7453L3(String str) {
        f7307x.m20040m("PSTN", str);
    }

    /* renamed from: M */
    public String m7454M() {
        return f7307x.m20035h("cv", "0");
    }

    /* renamed from: M0 */
    public String m7455M0() {
        return f7307x.m20035h("permissions", "");
    }

    /* renamed from: M1 */
    public boolean m7456M1() {
        return f7307x.m20031d("setting_notification_incoming_call", SettingActivity.f8942Q);
    }

    /* renamed from: M2 */
    public void m7457M2(boolean z8) {
        f7307x.m20036i("faceIdDebug", z8);
    }

    /* renamed from: M3 */
    public void m7458M3(String str) {
        f7307x.m20040m("permissions", str);
    }

    /* renamed from: N0 */
    public String m7459N0() {
        return f7307x.m20035h("invitedMessage", "");
    }

    /* renamed from: N1 */
    public boolean m7460N1() {
        return ((KeyguardManager) getSystemService("keyguard")).inKeyguardRestrictedInputMode();
    }

    @Deprecated
    /* renamed from: N2 */
    public void m7461N2(long j9, boolean z8) throws JSONException {
        long jM7515Z = m7515Z();
        if (z8 || j9 > jM7515Z) {
            f7308y.m20039l("finishTime", j9);
        }
        m7613s4();
    }

    /* renamed from: N3 */
    public void m7462N3(String str) {
        f7307x.m20040m("invitedMessage", str);
    }

    /* renamed from: O0 */
    public String m7463O0() {
        return f7307x.m20035h("preferServer", "TW");
    }

    /* renamed from: O1 */
    public boolean m7464O1() {
        String strM7449L = m7449L();
        String strM7587o0 = m7587o0();
        return (strM7449L == null || strM7449L.equals("") || strM7587o0 == null || strM7587o0.equals("")) ? false : true;
    }

    /* renamed from: O2 */
    public void m7465O2(int i9) {
        f7307x.m20037j("FontSize", i9);
    }

    /* renamed from: O3 */
    public void m7466O3(String str) {
        f7307x.m20040m("preferServer", str);
    }

    /* renamed from: P */
    public int m7467P() {
        return f7307x.m20032e("CountToShowUpgradePro", 0);
    }

    /* renamed from: P0 */
    public int m7468P0() {
        return f7307x.m20032e("privacyPassword", 0);
    }

    /* renamed from: P1 */
    public boolean m7469P1() {
        return f7307x.m20031d("isMessengerPro", false);
    }

    /* renamed from: P2 */
    public void m7470P2(boolean z8) {
        f7307x.m20036i("isFontSizePressed", z8);
    }

    /* renamed from: P3 */
    public void m7471P3(int i9) {
        f7307x.m20037j("privacyPassword", i9);
    }

    /* renamed from: Q */
    public String m7472Q() {
        return f7309z.m20035h("currentNotification", "");
    }

    /* renamed from: Q0 */
    public String m7473Q0(String str) {
        return f7285D.m20035h(str, "");
    }

    /* renamed from: Q1 */
    public boolean m7474Q1(String str) {
        return String.valueOf(f7307x.m20034g("UserID", 0L)).equals(str);
    }

    /* renamed from: Q2 */
    public void m7475Q2(String str) {
        f7307x.m20040m("GCMFilter", str);
    }

    /* renamed from: Q3 */
    public void m7476Q3(boolean z8) {
        f7307x.m20036i("proUser", z8);
    }

    /* renamed from: R */
    public SimpleDateFormat m7477R() {
        return new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS", Locale.getDefault());
    }

    /* renamed from: R0 */
    public ArrayList<String> m7478R0() {
        Log.d("Globals", "[getPurchaseList] sPurchaseList = " + f7306w);
        return f7306w;
    }

    /* renamed from: R1 */
    public final boolean m7479R1() {
        int iM20032e = f7307x.m20032e("isPlatformHoudini", -1);
        if (iM20032e == 0) {
            return false;
        }
        if (iM20032e == 1) {
            return true;
        }
        boolean zM16437F1 = CLUtility.m16437F1();
        f7307x.m20037j("isPlatformHoudini", zM16437F1 ? 1 : 0);
        return zM16437F1;
    }

    /* renamed from: R2 */
    public void m7480R2(String str) {
        ArrayList<String> arrayListM20033f = f7307x.m20033f("GroupLastMsgUpdateList");
        if (!arrayListM20033f.contains(str)) {
            arrayListM20033f.add(str);
        }
        f7307x.m20038k("GroupLastMsgUpdateList", arrayListM20033f);
    }

    /* renamed from: R3 */
    public void m7481R3(String str, String str2) {
        f7285D.m20040m(str, str2);
    }

    /* renamed from: S */
    public int m7482S(boolean z8) {
        return (!f7307x.m20031d("setting_notification_vibrate", SettingActivity.f8944S) || z8) ? -4 : -2;
    }

    /* renamed from: S0 */
    public String m7483S0() {
        String strM20035h = f7307x.m20035h("queryMessageAPIVersion", "v2");
        if (!C6383t.m24512a(strM20035h, "v2")) {
            C5154j.m20075i("Got unexpected message version: " + strM20035h);
        }
        return strM20035h;
    }

    /* renamed from: S1 */
    public boolean m7484S1() {
        return f7307x.m20031d("proUser", false);
    }

    /* renamed from: S2 */
    public void m7485S2(long j9) {
        f7307x.m20039l("groupToDoId", j9);
    }

    /* renamed from: S3 */
    public void m7486S3(AsyncTaskC2897h0 asyncTaskC2897h0) {
        this.f7316h = asyncTaskC2897h0;
    }

    /* renamed from: T */
    public JSONObject m7487T() {
        String strM20035h = f7282A.m20035h("DeletedMessageList", "");
        if (strM20035h == null || strM20035h.isEmpty()) {
            return new JSONObject();
        }
        try {
            return new JSONObject(strM20035h);
        } catch (JSONException e9) {
            e9.printStackTrace();
            return new JSONObject();
        }
    }

    /* renamed from: T0 */
    public AsyncTaskC2897h0 m7488T0() {
        return this.f7316h;
    }

    /* renamed from: T1 */
    public Boolean m7489T1() {
        return !f7284C.m20030c("isRefreshedDatabaseFor7160") ? Boolean.FALSE : Boolean.valueOf(f7284C.m20031d("isRefreshedDatabaseFor7160", false));
    }

    /* renamed from: T2 */
    public void m7490T2(boolean z8) {
        f7289H = z8;
    }

    /* renamed from: T3 */
    public void m7491T3(String str) {
        if (C6383t.m24512a(str, "v2")) {
            f7307x.m20040m("queryMessageAPIVersion", str);
            return;
        }
        C5154j.m20075i("Set unexpected message version: " + str);
        f7307x.m20040m("queryMessageAPIVersion", "v2");
    }

    /* renamed from: U */
    public String m7492U() {
        return f7307x.m20035h("department_friend_expanded_status", "S11111").length() < 6 ? "S11111" : f7307x.m20035h("department_friend_expanded_status", "S11111");
    }

    /* renamed from: U0 */
    public String m7493U0() {
        return f7307x.m20035h("regId", "");
    }

    /* renamed from: U1 */
    public boolean m7494U1() {
        return ((PowerManager) getSystemService("power")).isInteractive();
    }

    /* renamed from: U2 */
    public void m7495U2(boolean z8) {
        f7294k = z8;
        Log.d("Globals", "Heartbeat enable changed=" + f7294k);
    }

    /* renamed from: U3 */
    public void m7496U3(boolean z8) {
        f7284C.m20036i("isRefreshedDatabaseFor7160", z8);
    }

    /* renamed from: V */
    public String m7497V() {
        return f7307x.m20035h("department_cache", "");
    }

    /* renamed from: V0 */
    public String m7498V0() {
        return m7388i0().m7409C1().booleanValue() ? C5127a.m19997a(f7307x.m20035h("RegisterEmail", "")) : f7307x.m20035h("RegisterEmail", "");
    }

    /* renamed from: V1 */
    public Boolean m7499V1() {
        return Boolean.valueOf(f7307x.m20031d("SearchChatEnable", false));
    }

    /* renamed from: V2 */
    public void m7500V2(boolean z8) {
        f7307x.m20036i("heartbeatEnableDebug", z8);
        Log.d("Globals", "Heartbeat enable changed=" + f7294k);
    }

    /* renamed from: V3 */
    public void m7501V3(String str) {
        if (!m7388i0().m7409C1().booleanValue()) {
            f7307x.m20040m("RegisterEmail", str);
        } else {
            f7307x.m20040m("RegisterEmail", C5127a.m19998b(str));
        }
    }

    /* renamed from: W0 */
    public Date m7502W0() {
        return new Date(f7307x.m20034g("registrationTime", 0L) + FriendsClient.f13679k);
    }

    /* renamed from: W1 */
    public Boolean m7503W1() {
        return Boolean.valueOf(f7307x.m20031d("isSecurityPINEnable", false));
    }

    /* renamed from: W2 */
    public void m7504W2(int i9) {
        f7307x.m20037j("heartbeatResult", i9);
    }

    /* renamed from: W3 */
    public void m7505W3(String str, String str2, boolean z8) {
        f7307x.m20040m("accountToken", str);
        f7307x.m20040m("accountSource", str2);
        m7558i3(z8);
    }

    /* renamed from: X */
    public String m7506X() {
        return f7307x.m20035h("DoUtoken", "");
    }

    /* renamed from: X1 */
    public boolean m7507X1() {
        return f7307x.m20031d("setting_notification_show_preview", SettingActivity.f8945T);
    }

    /* renamed from: X2 */
    public void m7508X2(boolean z8) {
        f7307x.m20036i("hideChatListEnableDebug", z8);
        Log.d("Globals", "[setHideChatLisEnableDebug]User can hide chat list =" + z8);
    }

    /* renamed from: X3 */
    public void m7509X3(long j9) {
        f7307x.m20039l("registrationTime", j9);
    }

    /* renamed from: Y */
    public Bitmap m7510Y() {
        if (this.f7318j == null) {
            this.f7318j = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas();
            canvas.setBitmap(this.f7318j);
            canvas.drawColor(getResources().getColor(R.color.edit_image_backgroup_color));
            canvas.setBitmap(null);
        }
        return this.f7318j;
    }

    /* renamed from: Y0 */
    public String m7511Y0() {
        return m7587o0() + "/" + m7598q1();
    }

    /* renamed from: Y1 */
    public boolean m7512Y1() {
        return f7307x.m20031d("stickerNewTabPressed", false);
    }

    /* renamed from: Y2 */
    public void m7513Y2(boolean z8) {
        f7307x.m20036i("hideGroupEnableDebug", z8);
        Log.d("Globals", "[hideGroupEnableDebug]User can hide group =" + z8);
    }

    /* renamed from: Y3 */
    public void m7514Y3(String str) {
        f7307x.m20040m("scheduleMeetingWaitingRoomOption", str);
    }

    /* renamed from: Z */
    public long m7515Z() {
        long jM20034g = f7308y.m20034g("finishTime", 0L);
        return jM20034g == 0 ? f7307x.m20034g("finishTime", 0L) : jM20034g;
    }

    /* renamed from: Z1 */
    public boolean m7516Z1() {
        return f7307x.m20031d("stickerThumbLoaded", false);
    }

    /* renamed from: Z2 */
    public void m7517Z2(int i9) {
        f7307x.m20037j("InfoFontSize", i9);
    }

    /* renamed from: Z3 */
    public void m7518Z3(boolean z8) {
        f7307x.m20036i("SearchChatEnable", z8);
    }

    /* renamed from: a0 */
    public int m7519a0() {
        return f7307x.m20032e("FontSize", f7296m);
    }

    /* renamed from: a1 */
    public String m7520a1() {
        return f7307x.m20035h("scheduleMeetingWaitingRoomOption", "");
    }

    /* renamed from: a2 */
    public boolean m7521a2() {
        return f7307x.m20031d("stickerTopTabPressed", false);
    }

    /* renamed from: a4 */
    public void m7522a4(int i9) {
        f7307x.m20037j("privacySecurityPIN", i9);
    }

    @Override // android.content.ContextWrapper
    public void attachBaseContext(Context context) {
        super.attachBaseContext(context);
        C6264b.m24008a(this);
    }

    /* renamed from: b0 */
    public boolean m7523b0() {
        return f7307x.m20031d("isFontSizePressed", false);
    }

    /* renamed from: b1 */
    public int m7524b1() {
        return f7307x.m20032e("privacySecurityPIN", 0);
    }

    /* renamed from: b2 */
    public boolean m7525b2() {
        return f7307x.m20031d("isSyncStickerListWithServer", false);
    }

    /* renamed from: b3 */
    public void m7526b3(boolean z8) {
        f7307x.m20036i("isCallingOtherApp", z8);
    }

    /* renamed from: b4 */
    public void m7527b4(boolean z8) {
        f7307x.m20036i("isSecurityPINEnable", z8);
    }

    /* renamed from: c0 */
    public long m7528c0() {
        return f7307x.m20034g("expandedStatus", 63L);
    }

    /* renamed from: c1 */
    public C6303q m7529c1() {
        return this.f7312d;
    }

    /* renamed from: c3 */
    public void m7530c3(boolean z8) {
        f7290I = z8;
    }

    /* renamed from: c4 */
    public void m7531c4(boolean z8) {
        f7307x.m20036i("stickerNewTabPressed", z8);
    }

    /* renamed from: d0 */
    public String m7532d0() {
        return f7307x.m20035h("GCMFilter", "");
    }

    /* renamed from: d1 */
    public boolean m7533d1() {
        return f7307x.m20031d("stickerUpdatedWithServer", false);
    }

    /* renamed from: d2 */
    public boolean m7534d2() {
        if (!this.f7314f) {
            this.f7315g = m7548g();
            this.f7314f = true;
        }
        return this.f7315g;
    }

    /* renamed from: d3 */
    public void m7535d3(boolean z8) {
        f7307x.m20036i("isEnableServerTransCode", z8);
    }

    /* renamed from: d4 */
    public void m7536d4(boolean z8) {
        f7307x.m20036i("stickerThumbLoaded", z8);
    }

    /* renamed from: e0 */
    public ArrayList<String> m7537e0() {
        return f7307x.m20033f("GroupLastMsgUpdateList");
    }

    /* renamed from: e1 */
    public String m7538e1() {
        return f7292K;
    }

    /* renamed from: e2 */
    public boolean m7539e2() {
        return f7307x.m20031d("isEnableUNOLog", false);
    }

    /* renamed from: e3 */
    public void m7540e3(boolean z8) {
        f7307x.m20036i("isGCMFilter", z8);
    }

    /* renamed from: e4 */
    public void m7541e4(boolean z8) {
        f7307x.m20036i("stickerTopTabPressed", z8);
    }

    /* renamed from: f */
    public boolean m7542f() {
        return f7307x.m20031d("skipFAQDialog", false);
    }

    /* renamed from: f0 */
    public long m7543f0() {
        return f7307x.m20034g("groupToDoId", -1L);
    }

    /* renamed from: f1 */
    public String m7544f1() {
        return f7293L;
    }

    /* renamed from: f2 */
    public boolean m7545f2() {
        return f7307x.m20031d("buyProFreeTrialBefore", false);
    }

    /* renamed from: f3 */
    public void m7546f3(boolean z8) {
        f7307x.m20036i("isMessengerPro", z8);
    }

    /* renamed from: f4 */
    public void m7547f4(boolean z8) {
        f7307x.m20036i("stickerUpdatedWithServer", z8);
    }

    /* renamed from: g */
    public final boolean m7548g() {
        return new File(CLUtility.m16561m1(C6518a.f21930b) + "debug").exists();
    }

    /* renamed from: g0 */
    public int m7549g0() {
        return f7307x.m20032e("InfoFontSize", f7297n);
    }

    /* renamed from: g2 */
    public boolean m7550g2() {
        return f7307x.m20031d("useTWServer", false);
    }

    /* renamed from: g3 */
    public void m7551g3(boolean z8) {
        f7307x.m20036i("skipFAQDialog", z8);
    }

    /* renamed from: h2 */
    public Boolean m7552h2() {
        return Boolean.valueOf(f7284C.m20031d("isVerifyServerCertificateEnable", false));
    }

    /* renamed from: h3 */
    public void m7553h3(boolean z8) {
        f7307x.m20036i("isSyncStickerListWithServer", z8);
    }

    /* renamed from: h4 */
    public void m7554h4(boolean z8) {
        if (z8) {
            UNOFileLog.m7210g(new File(CLUtility.m16569o1()).getAbsolutePath());
        } else {
            UNOFileLog.m7206c();
        }
        f7307x.m20036i("isEnableUNOLog", z8);
    }

    /* renamed from: i */
    public void m7555i(String str) {
        if (!f7306w.contains(str)) {
            f7306w.add(str);
        }
        Log.d("Globals", "[addToPurchaseList]new sPurchaseList = " + f7306w);
    }

    /* renamed from: i1 */
    public JSONObject m7556i1() {
        String strM20035h = f7307x.m20035h("updateUserTask", "");
        try {
            if (strM20035h.isEmpty()) {
                return null;
            }
            return new JSONObject(strM20035h);
        } catch (JSONException e9) {
            e9.printStackTrace();
            return null;
        }
    }

    /* renamed from: i2 */
    public boolean m7557i2() {
        return f7307x.m20031d("setting_notification_vibrate", SettingActivity.f8944S) && !m7439I1();
    }

    /* renamed from: i3 */
    public void m7558i3(boolean z8) {
        f7307x.m20036i("useTWServer", z8);
    }

    /* renamed from: i4 */
    public void m7559i4() {
        f7307x.m20036i("buyProFreeTrialBefore", true);
    }

    /* renamed from: j */
    public void m7560j(String str) throws JSONException {
        String strM20035h = f7307x.m20035h("updateUserTask", "");
        try {
            JSONObject jSONObject = strM20035h.isEmpty() ? new JSONObject() : new JSONObject(strM20035h);
            jSONObject.put(str, "");
            f7307x.m20040m("updateUserTask", jSONObject.toString());
        } catch (JSONException e9) {
            e9.printStackTrace();
        }
    }

    /* renamed from: j0 */
    public String m7561j0() {
        UserInfo userInfoM16497V0;
        String strM7459N0 = m7459N0();
        return (!strM7459N0.isEmpty() || (userInfoM16497V0 = CLUtility.m16497V0(getApplicationContext())) == null) ? strM7459N0 : getString(R.string.friend_invitation_introduction_new, userInfoM16497V0.f13778c);
    }

    /* renamed from: j1 */
    public String m7562j1() {
        if (C5170o0.m20170e(this.f7311c)) {
            this.f7311c = f7307x.m20035h("useragent", "");
        }
        return this.f7311c;
    }

    /* renamed from: j2 */
    public boolean m7563j2() {
        return f7307x.m20031d("enableWaitingRoomNew", false);
    }

    /* renamed from: j3 */
    public void m7564j3(boolean z8) {
        f7307x.m20036i("isVerifyEmail", z8);
    }

    /* renamed from: j4 */
    public void m7565j4(long j9) {
        f7307x.m20039l("UserID", j9);
    }

    /* renamed from: k */
    public void m7566k(String str) {
        new C5143f0(getApplicationContext(), "NoticeListCache", 0).m20040m("noticeList", str);
    }

    /* renamed from: k0 */
    public boolean m7567k0() {
        return f7307x.m20031d("isCallingOtherApp", false);
    }

    /* renamed from: k1 */
    public Long m7568k1() {
        return Long.valueOf(f7307x.m20034g("UserID", 0L));
    }

    /* renamed from: k2 */
    public boolean m7569k2() {
        return f7307x.m20031d("is_5_0_NewUser", false);
    }

    /* renamed from: k3 */
    public void m7570k3(String str) {
        if (!m7388i0().m7409C1().booleanValue()) {
            f7307x.m20040m("JID", str);
        } else {
            f7307x.m20040m("JID", C5127a.m19998b(str));
        }
    }

    /* renamed from: k4 */
    public void m7571k4(boolean z8) {
        f7284C.m20036i("isVerifyServerCertificateEnable", z8);
    }

    /* renamed from: l */
    public void m7572l(String str, String str2) {
        new C5143f0(getApplicationContext(), "StrickerShopList_" + str, 0).m20040m("stickerPackList", str2);
        if (str.equals("Top")) {
            m7406B3(false);
        } else if (str.equals("New")) {
            m7651z3(false);
        }
    }

    /* renamed from: l0 */
    public boolean m7573l0() {
        return f7290I;
    }

    /* renamed from: l1 */
    public Map<String, Friend> m7574l1() {
        if (this.f7317i == null) {
            this.f7317i = C2950b0.m14899A().m15005E();
        }
        return this.f7317i;
    }

    /* renamed from: l3 */
    public void m7575l3(long j9) {
        f7307x.m20039l("lastNoticeCheckDate", j9);
    }

    /* renamed from: l4 */
    public void m7576l4(boolean z8) {
        f7307x.m20036i("enableWaitingRoomNew", z8);
    }

    /* renamed from: m */
    public void m7577m(String str) {
        new C5143f0(getApplicationContext(), "SuggestionListCache", 0).m20040m("suggestionFriendList", str);
    }

    /* renamed from: m0 */
    public boolean m7578m0() {
        return f7291J;
    }

    /* renamed from: m1 */
    public void m7579m1() {
        String strM7449L = m7388i0().m7449L();
        ArrayList arrayList = new ArrayList();
        arrayList.add(new C6301o("token", strM7449L));
        arrayList.add(new C6301o("pageIndex", String.valueOf(1)));
        arrayList.add(new C6301o("pageSize", String.valueOf(200)));
        FriendsClient friendsClient = new FriendsClient();
        friendsClient.m15734m("sticker", "user.pack.list", arrayList, new C1407a(strM7449L, friendsClient));
    }

    /* renamed from: m3 */
    public void m7580m3(long j9) {
        f7307x.m20039l("LastNoticeMaxId", j9);
    }

    /* renamed from: m4 */
    public void m7581m4(long j9) {
        f7287F = j9;
        f7307x.m20039l("XMPPExpiration", j9);
    }

    /* renamed from: n */
    public boolean m7582n() {
        return f7307x.m20031d("hideChatListEnableDebug", false);
    }

    /* renamed from: n0 */
    public boolean m7583n0() {
        return f7307x.m20031d("isVerifyEmail", false);
    }

    /* renamed from: n3 */
    public void m7584n3(int i9) {
        f7307x.m20037j("lastPaintColorSetting", i9);
    }

    /* renamed from: n4 */
    public void m7585n4(long j9) {
        f7288G = j9;
        f7307x.m20039l("XMPPLastTime", j9);
    }

    /* renamed from: o */
    public boolean m7586o() {
        return f7307x.m20031d("hideGroupEnableDebug", false);
    }

    /* renamed from: o0 */
    public String m7587o0() {
        return m7388i0().m7409C1().booleanValue() ? C5127a.m19997a(f7307x.m20035h("JID", "")) : f7307x.m20035h("JID", "");
    }

    /* renamed from: o1 */
    public long m7588o1() {
        if (f7287F == 0) {
            f7287F = f7307x.m20034g("XMPPExpiration", 0L);
        }
        return f7287F;
    }

    /* renamed from: o3 */
    public void m7589o3(int i9) {
        f7307x.m20037j("lastPaintSizeSetting", i9);
    }

    /* renamed from: o4 */
    public void m7590o4(String str) {
        f7286E = str;
        f7307x.m20040m("XMPPSessionId", str);
    }

    @Override // android.app.Application
    public void onCreate() throws PackageManager.NameNotFoundException {
        super.onCreate();
        m7397A();
        f7299p = this;
        ULogUtility.m16670f("Globals", "onCreate()");
        m7640x1();
        f7307x = new C5143f0(this, "U", 0);
        f7284C = new C5143f0(this, "DatabasePref", 0);
        f7285D = new C5143f0(this, "ProxyPref", 0);
        getExternalCacheDir();
        NotificationHelper.m14082Z();
        m7395y1();
        if (m7380c2()) {
            ULogUtility.m16672h();
        }
        DeviceCapability.m7314h(this, R.raw.devices_list, "black_list", "devices_list.json");
        C6374k.m24486e(this);
        if (!GDPRActivity.m8213o(m7388i0())) {
            C5154j.m20069c(this);
        }
        FirebaseApp.initializeApp(this);
        C4831a.m19170d(R.xml.remote_config_defaults, false);
        f7308y = new C5143f0(this, "FinishTimePref", 0);
        f7309z = new C5143f0(this, "NotificationPref", 0);
        f7282A = new C5143f0(this, "DeletedMessagePref", 0);
        f7283B = new C5143f0(getApplicationContext(), "NotificationIdMapPref", 0);
        m7490T2(false);
        C2889b.m14298h().m14314r();
        try {
            if (!GDPRActivity.m8213o(m7388i0())) {
                C6566a.m25148g(this);
            }
            new Handler().postDelayed(new Runnable() { // from class: v2.u
                @Override // java.lang.Runnable
                public final void run() {
                    this.f21803b.m7394p2();
                }
            }, 1000L);
            MonitorService.m7659j(getApplicationContext(), new Intent());
            this.f7312d = new C6303q(((int) Runtime.getRuntime().maxMemory()) / 8);
            if (m7388i0().m7567k0()) {
                m7388i0().m7526b3(false);
                if (m7388i0().m7468P0() != 0) {
                    m7388i0().m7650z2(true);
                }
            }
        } catch (Exception e9) {
            ULogUtility.m16676l("Globals", "Globals onCreate error:" + e9.getMessage());
        }
        registerActivityLifecycleCallbacks(f7305v);
        C4677a.m18689O(m7380c2());
        C4677a.m18688N(NetworkManager.m5645g());
        C4677a.m18690P(m7388i0().m7463O0());
        C4677a.m18711o(this);
        NetworkManager.m5648j();
        NotificationHelper.m14057A();
        C6196d0.m23692d();
        m7623u2();
        if (TextUtils.isEmpty(m7388i0().m7449L())) {
            return;
        }
        CLUtility.m16518b2(true);
        if (f7307x.m20030c("organizationId")) {
            C6566a.m25162u("Launch_App");
        }
    }

    @Override // android.app.Application
    public void onTerminate() {
        super.onTerminate();
        C1408b.m7655b().f7324a.clear();
    }

    /* renamed from: p */
    public boolean m7591p() {
        return f7307x.m20030c("organizationId") && f7307x.m20030c("proUser") && TextUtils.isEmpty(m7388i0().m7442J0()) && !m7484S1() && m7427G().isEmpty();
    }

    /* renamed from: p0 */
    public long m7592p0() {
        return f7307x.m20034g("lastNoticeCheckDate", 0L);
    }

    /* renamed from: p1 */
    public long m7593p1() {
        if (f7288G == 0) {
            f7288G = f7307x.m20034g("XMPPLastTime", 0L);
        }
        return f7288G;
    }

    /* renamed from: p3 */
    public void m7594p3(String str) {
        f7307x.m20040m("lastShowAccountHoldTime", str);
    }

    /* renamed from: p4 */
    public void m7595p4() {
        f7307x.m20036i("is_5_0_NewUser", true);
    }

    /* renamed from: q */
    public boolean m7596q(Friend friend, String str, String str2) {
        if (str2 == null || str2.isEmpty() || ((friend.m15620a() == null || !friend.m15620a().toLowerCase(Locale.getDefault()).contains(str2)) && ((friend.m15621b() == null || !friend.m15621b().toLowerCase(Locale.getDefault()).contains(str2)) && (str == null || !str.toLowerCase(Locale.getDefault()).contains(str2))))) {
            Log.d("Globals", "[checkGCMFilterContext] false");
            return false;
        }
        Log.d("Globals", "[checkGCMFilterContext] true");
        return true;
    }

    /* renamed from: q0 */
    public long m7597q0() {
        return f7307x.m20034g("LastNoticeMaxId", 0L);
    }

    /* renamed from: q1 */
    public final String m7598q1() {
        String strM20035h = f7307x.m20035h("XMPPResourceId", "");
        if (!strM20035h.isEmpty()) {
            return strM20035h;
        }
        String strM20043a = AbstractC5146g0.m20043a(m7388i0());
        f7307x.m20040m("XMPPResourceId", strM20043a);
        return strM20043a;
    }

    /* renamed from: q2 */
    public String m7599q2(String str) {
        return new C5143f0(getApplicationContext(), "StrickerShopList_" + str, 0).m20035h("stickerPackList", "");
    }

    /* renamed from: q3 */
    public void m7600q3(String str) {
        f7307x.m20040m("lastShowDowngradeVideoTime", str);
    }

    /* renamed from: q4 */
    public void m7601q4(Boolean bool) {
        f7307x.m20036i("isFirstInit", bool.booleanValue());
    }

    /* renamed from: r */
    public boolean m7602r() {
        return f7307x.m20031d("isNewAddFriend", false);
    }

    /* renamed from: r0 */
    public int m7603r0() {
        return f7307x.m20032e("lastPaintColorSetting", -11156201);
    }

    /* renamed from: r1 */
    public String m7604r1() {
        if (f7286E == null) {
            f7286E = f7307x.m20035h("XMPPSessionId", null);
        }
        return f7286E;
    }

    /* renamed from: r2 */
    public String m7605r2() {
        return new C5143f0(getApplicationContext(), "NoticeListCache", 0).m20035h("noticeList", "");
    }

    /* renamed from: r3 */
    public void m7606r3(String str) {
        f7307x.m20040m("lastShowUpgradeProTime", str);
    }

    /* renamed from: r4 */
    public void m7607r4() {
        UserInfo userInfoM16497V0;
        if (m7464O1()) {
            C4677a.m18711o(this);
            if (!C5170o0.m20170e(C4677a.m18708l()) || (userInfoM16497V0 = CLUtility.m16497V0(this)) == null) {
                return;
            }
            C4677a.m18711o(this);
            C4677a.m18686L(userInfoM16497V0.f13778c);
        }
    }

    /* renamed from: s */
    public boolean m7608s() {
        return f7307x.m20031d("isNewNotice", false);
    }

    /* renamed from: s0 */
    public int m7609s0() {
        return f7307x.m20032e("lastPaintSizeSetting", 5);
    }

    /* renamed from: s1 */
    public Boolean m7610s1() {
        return Boolean.valueOf(f7307x.m20031d("isFirstInit", true));
    }

    /* renamed from: s2 */
    public String m7611s2() {
        return new C5143f0(getApplicationContext(), "SuggestionListCache", 0).m20035h("suggestionFriendList", "");
    }

    /* renamed from: s3 */
    public void m7612s3(long j9) {
        f7307x.m20039l("lastStickerCheckDate", j9);
    }

    /* renamed from: s4 */
    public void m7613s4() throws JSONException {
        long jM7515Z = m7515Z();
        JSONObject jSONObjectM7487T = m7388i0().m7487T();
        JSONObject jSONObject = new JSONObject();
        Iterator<String> itKeys = jSONObjectM7487T.keys();
        while (itKeys.hasNext()) {
            String next = itKeys.next();
            try {
                long j9 = jSONObjectM7487T.getLong(next);
                if (j9 > jM7515Z) {
                    jSONObject.put(next, j9);
                }
            } catch (JSONException e9) {
                e9.printStackTrace();
            }
        }
        if (jSONObject.length() > 0) {
            m7388i0().m7440I2(jSONObject);
        }
    }

    /* renamed from: t */
    public boolean m7614t() {
        return f7307x.m20031d("isNewSetting", false);
    }

    /* renamed from: t0 */
    public String m7615t0() {
        return f7307x.m20035h("lastShowAccountHoldTime", "");
    }

    /* renamed from: t1 */
    public boolean m7616t1(String str) {
        for (String str2 : m7455M0().split(",")) {
            if (str2.equals(str)) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: t2 */
    public void m7617t2(Map<String, String> map) {
        Intent intent = new Intent(getApplicationContext(), (Class<?>) SplashActivity.class);
        intent.addFlags(268468224);
        intent.putExtra(SplashActivity.f11361o, true);
        if (map != null) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                intent.putExtra(entry.getKey(), entry.getValue());
            }
        }
        startActivity(intent);
    }

    /* renamed from: t3 */
    public void m7618t3(long j9) {
        f7307x.m20039l("lastStickerDate", j9);
    }

    /* renamed from: t4 */
    public synchronized void m7619t4(int i9, boolean z8) {
        char[] charArray = m7492U().toCharArray();
        if (charArray.length <= 4) {
            char[] cArr = new char[6];
            cArr[0] = 'S';
            cArr[1] = '1';
            cArr[2] = '1';
            cArr[3] = '1';
            cArr[4] = '1';
            cArr[5] = '1';
            cArr[i9] = z8 ? '1' : '0';
            f7307x.m20040m("department_friend_expanded_status", new String(cArr));
        } else {
            charArray[i9] = z8 ? '1' : '0';
            f7307x.m20040m("department_friend_expanded_status", new String(charArray));
        }
    }

    /* renamed from: u */
    public boolean m7620u() {
        return f7307x.m20031d("isNewSticker", false);
    }

    /* renamed from: u0 */
    public String m7621u0() {
        return f7307x.m20035h("lastShowDowngradeVideoTime", "");
    }

    /* renamed from: u1 */
    public boolean m7622u1() {
        return f7289H;
    }

    /* renamed from: u2 */
    public final void m7623u2() {
        String strM7506X = m7388i0().m7506X();
        if (TextUtils.isEmpty(strM7506X) || !TextUtils.isEmpty(m7388i0().m7497V())) {
            return;
        }
        C2143a.m12424x(strM7506X, null);
    }

    /* renamed from: u3 */
    public void m7624u3(long j9) {
        f7307x.m20039l("lastTopStickerCheckDate", j9);
    }

    /* renamed from: u4 */
    public void m7625u4(String str) {
        f7307x.m20040m("department_cache", str);
    }

    /* renamed from: v */
    public boolean m7626v() {
        return f7307x.m20031d("needToUpdateSuggestionList", false);
    }

    /* renamed from: v0 */
    public String m7627v0() {
        return f7307x.m20035h("lastShowUpgradeProTime", "");
    }

    /* renamed from: v1 */
    public int m7628v1() {
        int iM7428G0 = m7428G0() + 1;
        f7307x.m20037j("notificationChannelVersion", iM7428G0);
        return iM7428G0;
    }

    /* renamed from: v2 */
    public void m7629v2(String str) {
        String strM20035h = f7307x.m20035h("updateUserTask", "");
        try {
            JSONObject jSONObject = strM20035h.isEmpty() ? new JSONObject() : new JSONObject(strM20035h);
            if (jSONObject.has(str)) {
                jSONObject.remove(str);
                f7307x.m20040m("updateUserTask", jSONObject.toString());
            }
        } catch (JSONException e9) {
            e9.printStackTrace();
        }
    }

    /* renamed from: v3 */
    public void m7630v3(String str) {
        f7307x.m20040m("", str);
    }

    /* renamed from: v4 */
    public synchronized void m7631v4(long j9, boolean z8) {
        long jM7528c0 = m7528c0();
        f7307x.m20039l("expandedStatus", z8 ? j9 | jM7528c0 : (j9 ^ 63) & jM7528c0);
    }

    /* renamed from: w */
    public void m7632w(String str) {
        new C5143f0(getApplicationContext(), "StrickerShopList_" + str, 0).m20041n("stickerPackList");
    }

    /* renamed from: w0 */
    public long m7633w0() {
        return f7307x.m20034g("lastStickerCheckDate", 0L);
    }

    /* renamed from: w1 */
    public void m7634w1() {
        f7307x.m20038k("GroupLastMsgUpdateList", new ArrayList<>());
    }

    /* renamed from: w2 */
    public void m7635w2() {
        f7286E = null;
        f7287F = 0L;
        f7288G = 0L;
        f7307x.m20040m("XMPPSessionId", null);
        f7307x.m20039l("XMPPExpiration", 0L);
        f7307x.m20039l("XMPPLastTime", 0L);
    }

    /* renamed from: w3 */
    public void m7636w3(long j9) {
        f7307x.m20039l("suggestionLastCreateTime", j9);
    }

    /* renamed from: w4 */
    public void m7637w4() {
        this.f7315g = m7548g();
    }

    /* renamed from: x */
    public void m7638x() {
        m7644y(false);
    }

    /* renamed from: x0 */
    public long m7639x0() {
        return f7307x.m20034g("lastStickerDate", 0L);
    }

    /* renamed from: x1 */
    public final void m7640x1() throws PackageManager.NameNotFoundException {
        f7302s = getPackageName();
        try {
            PackageInfo packageInfo = getPackageManager().getPackageInfo(f7302s, 0);
            f7303t = packageInfo.versionName;
            f7304u = String.valueOf(packageInfo.versionCode);
        } catch (PackageManager.NameNotFoundException e9) {
            C5154j.m20076j(e9);
        }
    }

    /* renamed from: x2 */
    public final void m7641x2() {
        String packageName = m7372O().getPackageName();
        if (m7649z0().isEmpty()) {
            try {
                String str = m7372O().getPackageManager().getPackageInfo(packageName, 0).versionName;
                m7630v3(str);
                ULogUtility.m16670f("Globals", "[retrieveLastVersion] record last version: " + str);
            } catch (PackageManager.NameNotFoundException e9) {
                C5154j.m20076j(e9);
            }
        }
    }

    /* renamed from: x3 */
    public void m7642x3(int i9) {
        f7307x.m20037j("meetingAttendee", i9);
    }

    /* renamed from: x4 */
    public void m7643x4(Friend friend) {
        Map<String, Friend> map = this.f7317i;
        if (map != null) {
            map.put(String.valueOf(friend.f13645c), friend);
        }
    }

    /* renamed from: y */
    public void m7644y(final boolean z8) {
        Log.d("Globals", "clearApplicationData begin");
        C2950b0.m14902a(new Runnable() { // from class: v2.v
            @Override // java.lang.Runnable
            public final void run() {
                this.f21804b.m7389l2(z8);
            }
        });
        C6385v.m24525c(new Runnable() { // from class: v2.w
            @Override // java.lang.Runnable
            public final void run() {
                this.f21806b.m7390m2();
            }
        });
        f7307x.m20041n(SplashActivity.f11362p);
        Log.d("Globals", "clearApplicationData end");
    }

    /* renamed from: y0 */
    public long m7645y0() {
        return f7307x.m20034g("lastTopStickerCheckDate", 0L);
    }

    /* renamed from: y2 */
    public void m7646y2(List<String> list) {
        f7307x.m20040m("accountHoldProduct", new JSONArray((Collection) list).toString());
    }

    /* renamed from: y3 */
    public void m7647y3(int i9) {
        f7307x.m20037j("meetingDuration", i9);
    }

    /* renamed from: z */
    public void m7648z() {
        this.f7315g = true;
        this.f7314f = true;
    }

    /* renamed from: z0 */
    public String m7649z0() {
        return f7307x.m20035h("", "");
    }

    /* renamed from: z2 */
    public void m7650z2(boolean z8) {
        f7307x.m20036i("isAppPasswordLocked", z8);
    }

    /* renamed from: z3 */
    public void m7651z3(boolean z8) {
        f7307x.m20036i("needToUpdateNewSticker", z8);
    }
}
