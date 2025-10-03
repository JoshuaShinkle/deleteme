package p095i3;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.PowerManager;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import com.cyberlink.you.Globals;
import com.cyberlink.you.alarm.AlarmReceiver;
import com.cyberlink.you.chat.C2876a;
import com.cyberlink.you.chat.C2907m0;
import com.cyberlink.you.chat.XMPPManager;
import com.cyberlink.you.friends.FriendsClient;
import com.cyberlink.you.friends.Group;
import com.cyberlink.you.utility.CLUtility;
import com.cyberlink.you.utility.ULogUtility;
import com.google.android.gms.auth.api.credentials.CredentialsApi;
import com.google.firebase.messaging.Constants;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.jivesoftware.smack.packet.AbstractC5594b;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;
import p116k4.C5154j;
import p201t3.C6301o;
import p209u2.ThreadFactoryC6373j;
import p218v2.C6456d;

/* renamed from: i3.b */
/* loaded from: classes.dex */
public class C5049b {

    /* renamed from: a */
    public static final Object f17427a = new Object();

    /* renamed from: b */
    public static final ThreadPoolExecutor f17428b = new ThreadPoolExecutor(1, 1, 0, TimeUnit.MILLISECONDS, new ArrayBlockingQueue(1), new ThreadFactoryC6373j("HB"));

    /* renamed from: c */
    public static d f17429c = null;

    /* renamed from: e */
    public static boolean f17431e = false;

    /* renamed from: d */
    public static final PowerManager.WakeLock f17430d = ((PowerManager) Globals.m7388i0().getBaseContext().getSystemService("power")).newWakeLock(1, "Heartbeat");

    /* renamed from: i3.b$b */
    public static class b extends d implements Runnable {

        /* renamed from: c */
        public String f17432c;

        public b() {
            super();
            this.f17432c = ULogUtility.m16677m();
        }

        /* renamed from: f */
        public final void m19740f() {
            synchronized (C5049b.f17427a) {
                String str = this.f17432c;
                XMPPManager.HandleType handleType = XMPPManager.HandleType.GCM;
                C5049b.m19739r(str, handleType, "gcm thread start.");
                Globals.m7388i0().m7504W2(1);
                if (m19745c(this.f17432c, handleType)) {
                    Globals.m7388i0().m7504W2(0);
                    C2876a.m14293b(handleType);
                } else {
                    Globals.m7388i0().m7504W2(1);
                }
                C5049b.m19739r(this.f17432c, handleType, "gcm thread end.");
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            Thread.currentThread().setName("HB-doGCM");
            d unused = C5049b.f17429c = this;
            ULogUtility.m16689y("GCM thread start.");
            m19740f();
            C2907m0.m14454I().m14505g0();
            C5049b.m19736o();
            C5049b.m19735n();
            ULogUtility.m16689y("GCM thread End");
            Thread.currentThread().setName("HB");
        }
    }

    /* renamed from: i3.b$c */
    public static class c extends d implements Runnable {

        /* renamed from: c */
        public String f17433c;

        public c() {
            super();
            this.f17433c = ULogUtility.m16677m();
        }

        /* renamed from: f */
        public final void m19741f() {
            synchronized (C5049b.f17427a) {
                String str = this.f17433c;
                XMPPManager.HandleType handleType = XMPPManager.HandleType.HEART_BEAT;
                C5049b.m19739r(str, handleType, "V2 heartbeat thread start.");
                if (!Globals.m7388i0().m7446K1()) {
                    ULogUtility.m16690z("Heartbeat is disable due to App is in the foreground, return now.");
                    return;
                }
                if (!Globals.m7388i0().m7451L1()) {
                    ULogUtility.m16690z("Heartbeat is disable due to Heartbeat is off in log page, return now.");
                    return;
                }
                if (!C6456d.m24714D().m24748G()) {
                    C5049b.m19739r(this.f17433c, handleType, "Network is not available now, return now.");
                    return;
                }
                if (m19742g()) {
                    if (m19745c(this.f17433c, handleType)) {
                        Globals.m7388i0().m7504W2(0);
                        C2876a.m14293b(handleType);
                    } else {
                        Globals.m7388i0().m7504W2(1);
                    }
                }
                C5049b.m19739r(this.f17433c, handleType, "V2 heartbeat thread end.");
            }
        }

        /* renamed from: g */
        public final boolean m19742g() {
            String str;
            String str2;
            long jLongValue = Globals.m7388i0().m7568k1().longValue();
            String strM7454M = Globals.m7388i0().m7454M();
            if (jLongValue != 0) {
                ArrayList arrayList = new ArrayList();
                arrayList.add(new C6301o("u", String.valueOf(jLongValue)));
                arrayList.add(new C6301o("cv", strM7454M));
                String str3 = this.f17433c;
                XMPPManager.HandleType handleType = XMPPManager.HandleType.HEART_BEAT;
                C5049b.m19739r(str3, handleType, "Heartbeat triggers to query archive messages via HTTP, cv = " + strM7454M);
                Pair<String, String> pairM15676n = FriendsClient.m15676n("chat", "heartbeatV2", arrayList);
                str = (String) pairM15676n.first;
                str2 = (String) pairM15676n.second;
                C5049b.m19739r(this.f17433c, handleType, "Heartbeat api response status code = " + str + " result= " + str2);
            } else {
                str = null;
                str2 = null;
            }
            if ("200".equals(str) && "1".equals(str2)) {
                C5049b.m19739r(this.f17433c, XMPPManager.HandleType.HEART_BEAT, "Need to continue query messages.");
                return true;
            }
            C5049b.m19739r(this.f17433c, XMPPManager.HandleType.HEART_BEAT, "Heartbeat api return no new message.");
            return false;
        }

        @Override // java.lang.Runnable
        public void run() {
            Thread.currentThread().setName("HB-doHB");
            d unused = C5049b.f17429c = this;
            m19741f();
            C2907m0.m14454I().m14505g0();
            Thread.currentThread().setName("HB");
        }
    }

    /* renamed from: i3.b$d */
    public static abstract class d {

        /* renamed from: b */
        public boolean f17434b;

        public d() {
            this.f17434b = false;
        }

        /* renamed from: a */
        public final Pair<String, String> m19743a(String str, XMPPManager.HandleType handleType, List<AbstractC5594b> list, String str2) throws XmlPullParserException, IOException {
            String strM7449L = Globals.m7388i0().m7449L();
            if (TextUtils.isEmpty(strM7449L)) {
                return null;
            }
            ArrayList arrayList = new ArrayList();
            arrayList.add(new C6301o("token", strM7449L));
            arrayList.add(new C6301o("cv", str));
            long jCurrentTimeMillis = System.currentTimeMillis();
            C5049b.m19739r(str2, handleType, "queryMessage api start query");
            Pair<String, String> pairM15676n = FriendsClient.m15676n("chat", "queryMessageV2", arrayList);
            C5049b.m19739r(str2, handleType, "queryMessage api end | query cost = " + (System.currentTimeMillis() - jCurrentTimeMillis));
            String str3 = (String) pairM15676n.first;
            if ("200".equals(str3)) {
                String str4 = (String) pairM15676n.second;
                if (!TextUtils.isEmpty(str4)) {
                    C5049b.m19739r(str2, handleType, "queryMessage api result = " + str4);
                    String[] strArrM19747e = m19747e(str4);
                    String str5 = strArrM19747e[0];
                    String str6 = strArrM19747e[1];
                    String str7 = strArrM19747e[2];
                    if ("0".equals(str6) && str7.isEmpty()) {
                        C5049b.m19739r(str2, handleType, "Not empty but no message content");
                        boolean unused = C5049b.f17431e = true;
                        return null;
                    }
                    if (str5 == null || str6 == null || str7 == null) {
                        return null;
                    }
                    list.addAll(XMPPManager.m14187k(str7, handleType));
                    return Pair.create(str5, str6);
                }
                C5049b.m19739r(str2, handleType, "queryMessage api result is empty");
            } else {
                C5049b.m19739r(str2, handleType, "queryMessage api result status = " + str3);
            }
            return null;
        }

        /* renamed from: b */
        public final boolean m19744b() {
            return this.f17434b;
        }

        /* renamed from: c */
        public boolean m19745c(String str, XMPPManager.HandleType handleType) throws XmlPullParserException, IOException {
            String strM7454M = Globals.m7388i0().m7454M();
            if (m19744b()) {
                C5049b.m19739r(str, handleType, "query message force stop because archive start.");
                return false;
            }
            if (XMPPManager.m14184g0().m14204A0()) {
                C5049b.m19739r(str, handleType, "query message force stop because xmpp is connected");
                return false;
            }
            String str2 = "0";
            int i9 = 0;
            while (true) {
                if (str2 == null || str2.equals("1") || i9 >= 3) {
                    break;
                }
                if (m19744b()) {
                    C5049b.m19739r(str, handleType, "query message force stop because archive start.");
                    return false;
                }
                if (XMPPManager.m14184g0().m14204A0()) {
                    C5049b.m19739r(str, handleType, "query message force stop because xmpp is connected");
                    return false;
                }
                C5049b.m19739r(str, handleType, "query cv=" + strM7454M);
                ArrayList arrayList = new ArrayList();
                Pair<String, String> pairM19743a = m19743a(strM7454M, handleType, arrayList, str);
                if (pairM19743a != null) {
                    strM7454M = (String) pairM19743a.first;
                    str2 = (String) pairM19743a.second;
                    if (!C5049b.m19731j(strM7454M) || str2 == null) {
                        C5049b.m19739r(str, handleType, "query fail");
                    } else {
                        boolean zEquals = str2.equals("1");
                        ArrayList arrayList2 = new ArrayList();
                        if (!arrayList.isEmpty()) {
                            arrayList2.addAll(arrayList);
                        }
                        C5049b.m19739r(str, handleType, "query message handlePackets start.");
                        XMPPManager.m14184g0().m14271u0(arrayList2, handleType, true);
                        C5049b.m19739r(str, handleType, "query message handlePackets end.");
                        Globals.m7388i0().m7410C2(strM7454M, "Heartbeat");
                        if (m19744b()) {
                            C5049b.m19739r(str, handleType, "query message force stop because archive start.");
                            return false;
                        }
                        if (XMPPManager.m14184g0().m14204A0()) {
                            C5049b.m19739r(str, handleType, "query message force stop because xmpp is connected");
                            return false;
                        }
                        if (zEquals) {
                            C5049b.m19739r(str, handleType, "query stop");
                            break;
                        }
                    }
                } else {
                    C5049b.m19739r(str, handleType, "query fail cv and empty is null.");
                }
                i9++;
            }
            return i9 < 3;
        }

        /* renamed from: d */
        public void m19746d() {
            this.f17434b = true;
        }

        /* JADX WARN: Removed duplicated region for block: B:8:0x0021  */
        /* renamed from: e */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final String[] m19747e(String str) throws XmlPullParserException, IOException {
            String strSubstring;
            String attributeValue;
            String attributeValue2;
            XmlPullParser xmlPullParserNewPullParser;
            int eventType;
            String str2 = null;
            if (!str.isEmpty()) {
                int iIndexOf = str.indexOf(">");
                int iLastIndexOf = str.lastIndexOf("</");
                strSubstring = (iIndexOf == -1 || iLastIndexOf == -1) ? null : str.substring(iIndexOf + 1, iLastIndexOf);
            }
            try {
                XmlPullParserFactory xmlPullParserFactoryNewInstance = XmlPullParserFactory.newInstance();
                xmlPullParserFactoryNewInstance.setNamespaceAware(true);
                xmlPullParserNewPullParser = xmlPullParserFactoryNewInstance.newPullParser();
                xmlPullParserNewPullParser.setInput(new StringReader(str));
            } catch (Exception e9) {
                e = e9;
                attributeValue = null;
            }
            for (eventType = xmlPullParserNewPullParser.getEventType(); eventType != 1; eventType = xmlPullParserNewPullParser.next()) {
                if (eventType == 2 && xmlPullParserNewPullParser.getName().equals("messages")) {
                    attributeValue = xmlPullParserNewPullParser.getAttributeValue("", "cv");
                    try {
                        attributeValue2 = xmlPullParserNewPullParser.getAttributeValue("", "empty");
                    } catch (Exception e10) {
                        e = e10;
                        C5154j.m20076j(e);
                        attributeValue2 = null;
                        str2 = attributeValue;
                        return new String[]{str2, attributeValue2, strSubstring};
                    }
                    str2 = attributeValue;
                    break;
                }
            }
            attributeValue2 = null;
            return new String[]{str2, attributeValue2, strSubstring};
        }
    }

    /* renamed from: g */
    public static void m19728g() {
        PowerManager.WakeLock wakeLock = f17430d;
        synchronized (wakeLock) {
            if (!wakeLock.isHeld()) {
                wakeLock.acquire();
            }
        }
    }

    /* renamed from: h */
    public static void m19729h() {
        if (f17431e) {
            return;
        }
        try {
            m19728g();
            f17428b.execute(new b());
        } catch (RejectedExecutionException unused) {
            m19735n();
            ULogUtility.m16689y("task reject.");
        }
    }

    /* renamed from: i */
    public static void m19730i() {
        ULogUtility.m16690z("start add V2 heartbeat runnable to executor.");
        try {
            f17428b.execute(new c());
        } catch (RejectedExecutionException unused) {
            ULogUtility.m16690z("Heartbeat thread task reject.");
        }
    }

    /* renamed from: j */
    public static boolean m19731j(String str) throws NumberFormatException {
        if (str == null) {
            return false;
        }
        try {
            Integer.valueOf(str);
            return true;
        } catch (NumberFormatException e9) {
            e9.printStackTrace();
            return false;
        }
    }

    /* renamed from: k */
    public static AbstractC5594b m19732k() {
        List<AbstractC5594b> listM14187k;
        ArrayList arrayList = new ArrayList();
        arrayList.add(new C6301o("token", Globals.m7388i0().m7449L()));
        ULogUtility.m16690z("queryMessage api start query " + Globals.m7388i0().m7477R().format(new Date()));
        Pair<String, String> pairM15676n = FriendsClient.m15676n("chat", "queryMessage", arrayList);
        ULogUtility.m16690z("queryMessage api end query " + Globals.m7388i0().m7477R().format(new Date()));
        String str = (String) pairM15676n.first;
        if ("200".equals(str)) {
            String str2 = (String) pairM15676n.second;
            if (!TextUtils.isEmpty(str2)) {
                ULogUtility.m16690z("queryMessage api result =" + str2);
                listM14187k = XMPPManager.m14187k(m19738q(str2), XMPPManager.HandleType.HEART_BEAT);
                if (listM14187k != null || listM14187k.isEmpty()) {
                    return null;
                }
                return listM14187k.get(listM14187k.size() - 1);
            }
            ULogUtility.m16690z("queryMessage api result is empty");
        } else {
            ULogUtility.m16690z("queryMessage api result status = " + str);
        }
        listM14187k = null;
        if (listM14187k != null) {
        }
        return null;
    }

    /* renamed from: l */
    public static boolean m19733l(Group group) {
        if (group == null) {
            Log.i("Heartbeat", "[queryMessage] group is null.");
            return false;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(new C6301o("token", Globals.m7388i0().m7449L()));
        if (group.f13716c.equals("Dual")) {
            arrayList.add(new C6301o("userId", CLUtility.m16577q1(group.f13723j)));
        } else {
            arrayList.add(new C6301o("groupId", String.valueOf(group.f13727n)));
        }
        ULogUtility.m16689y("[Query 10] queryMessage api start query " + Globals.m7388i0().m7477R().format(new Date()));
        Pair<String, String> pairM15676n = FriendsClient.m15676n("chat", "queryMessage", arrayList);
        ULogUtility.m16689y("[Query 10]queryMessage api end query " + Globals.m7388i0().m7477R().format(new Date()));
        String str = (String) pairM15676n.first;
        if (!"200".equals(str)) {
            ULogUtility.m16689y("[Query 10] queryMessage api result status = " + str);
            return false;
        }
        String str2 = (String) pairM15676n.second;
        if (str2.isEmpty()) {
            ULogUtility.m16689y("[Query 10] queryMessage api result is empty");
            return false;
        }
        ULogUtility.m16689y("[Query 10] queryMessage api result =" + str2);
        String strM19738q = m19738q(str2);
        XMPPManager.HandleType handleType = XMPPManager.HandleType.GCM;
        XMPPManager.m14184g0().m14271u0(XMPPManager.m14187k(strM19738q, handleType), handleType, true);
        return true;
    }

    /* renamed from: m */
    public static boolean m19734m(String str) {
        FriendsClient friendsClient = new FriendsClient();
        ArrayList arrayList = new ArrayList();
        arrayList.add(new C6301o("token", Globals.m7388i0().m7449L()));
        arrayList.add(new C6301o(Constants.FirelogAnalytics.PARAM_MESSAGE_ID, str));
        ULogUtility.m16689y("[Query message by Id] queryMessage api start query " + Globals.m7388i0().m7477R().format(new Date()));
        Pair<String, String> pairM15731j = friendsClient.m15731j("chat", "queryMessage", arrayList);
        ULogUtility.m16689y("[Query message by Id] queryMessage api end query " + Globals.m7388i0().m7477R().format(new Date()));
        String str2 = (String) pairM15731j.first;
        if (!"200".equals(str2)) {
            ULogUtility.m16689y("[Query message by Id] queryMessage api result status = " + str2);
            return false;
        }
        String str3 = (String) pairM15731j.second;
        if (str3.isEmpty()) {
            ULogUtility.m16689y("[Query message by Id] queryMessage api result is empty");
            return false;
        }
        ULogUtility.m16689y("[Query message by Id] queryMessage api result =" + str3);
        String strM19738q = m19738q(str3);
        XMPPManager.HandleType handleType = XMPPManager.HandleType.GCM;
        XMPPManager.m14184g0().m14271u0(XMPPManager.m14187k(strM19738q, handleType), handleType, true);
        return true;
    }

    /* renamed from: n */
    public static void m19735n() {
        PowerManager.WakeLock wakeLock = f17430d;
        synchronized (wakeLock) {
            if (wakeLock.isHeld()) {
                wakeLock.release();
            }
        }
    }

    /* renamed from: o */
    public static void m19736o() {
        AlarmManager alarmManager;
        PendingIntent broadcast = PendingIntent.getBroadcast(Globals.m7388i0(), 0, new Intent(Globals.m7388i0(), (Class<?>) AlarmReceiver.class), Build.VERSION.SDK_INT >= 31 ? 201326592 : 0);
        if (broadcast == null || (alarmManager = (AlarmManager) Globals.m7388i0().getSystemService("alarm")) == null) {
            return;
        }
        try {
            alarmManager.cancel(broadcast);
            long jM15653U = FriendsClient.m15653U() * CredentialsApi.ACTIVITY_RESULT_ADD_ACCOUNT;
            alarmManager.setRepeating(0, System.currentTimeMillis() + jM15653U, jM15653U, broadcast);
        } catch (Exception e9) {
            Log.e("Heartbeat", "heartbeat AlarmManager update was not canceled. " + e9.toString());
        }
    }

    /* renamed from: p */
    public static void m19737p() {
        ULogUtility.m16670f("Heartbeat", "stopCurrentTask");
        f17431e = false;
        f17428b.getQueue().clear();
        d dVar = f17429c;
        if (dVar != null) {
            dVar.m19746d();
        }
    }

    /* renamed from: q */
    public static String m19738q(String str) {
        return (str.startsWith("<messages>") && str.endsWith("</messages>")) ? str.substring(10, str.length() - 11) : str;
    }

    /* renamed from: r */
    public static void m19739r(String str, XMPPManager.HandleType handleType, String str2) {
        if (handleType == XMPPManager.HandleType.GCM) {
            ULogUtility.m16689y("[" + str + "] " + str2);
            return;
        }
        ULogUtility.m16690z("[" + str + "] " + str2);
    }
}
