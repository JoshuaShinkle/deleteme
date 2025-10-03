package p236x2;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.util.Log;
import com.cyberlink.you.Globals;
import com.cyberlink.you.database.MessageObj;
import com.mixpanel.android.mpmetrics.C4478c;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import p116k4.C5154j;
import p116k4.C5176q0;
import p191s2.C6253a;
import p218v2.C6478z;

/* renamed from: x2.a */
/* loaded from: classes.dex */
public class C6566a {

    /* renamed from: a */
    public static String f22074a = null;

    /* renamed from: b */
    public static boolean f22075b = false;

    /* renamed from: c */
    public static C4478c f22076c;

    /* renamed from: A */
    public static void m25141A(Map<String, Object> map) {
        if (m25150i()) {
            return;
        }
        C6253a.m23963i(map);
    }

    /* renamed from: a */
    public static String m25142a(Context context) {
        ApplicationInfo applicationInfo = context.getApplicationInfo();
        int i9 = applicationInfo.labelRes;
        return i9 == 0 ? applicationInfo.nonLocalizedLabel.toString() : context.getString(i9);
    }

    /* renamed from: b */
    public static Map<String, Object> m25143b() {
        HashMap map = new HashMap();
        map.put("SR", "");
        map.put("CHANNEL", "");
        map.put("VERSIONTYPE", "for Android");
        map.put("SID", Globals.m7388i0().m7568k1());
        map.put("CUSTOMERNO", Globals.m7388i0().m7442J0());
        map.put("ADMODE", Locale.getDefault().getCountry());
        map.put("LANGUAGE", Locale.getDefault().getLanguage());
        return map;
    }

    /* renamed from: c */
    public static String m25144c(MessageObj.MessageType messageType) {
        if (messageType.equals(MessageObj.MessageType.Text) || messageType.equals(MessageObj.MessageType.ReplyText)) {
            return "Chat.MsgSend.Text";
        }
        if (messageType.equals(MessageObj.MessageType.Photo)) {
            return "Chat.MsgSend.Photo";
        }
        if (messageType.equals(MessageObj.MessageType.Video)) {
            return "Chat.MsgSend.Video";
        }
        if (messageType.equals(MessageObj.MessageType.Sticker) || messageType.equals(MessageObj.MessageType.AnimSticker) || messageType.equals(MessageObj.MessageType.AnimPngSticker)) {
            return "Chat.MsgSend.Sticker";
        }
        return null;
    }

    /* renamed from: d */
    public static String m25145d() {
        Locale locale = Locale.getDefault();
        return locale.getLanguage() + "_" + locale.getCountry();
    }

    /* renamed from: e */
    public static String m25146e(boolean z8) {
        return z8 ? "Fail" : "Success";
    }

    /* renamed from: f */
    public static String m25147f(MessageObj.MessageType messageType) {
        if (messageType.equals(MessageObj.MessageType.Text) || messageType.equals(MessageObj.MessageType.ReplyText)) {
            return "Text";
        }
        if (messageType.equals(MessageObj.MessageType.Photo) || messageType.equals(MessageObj.MessageType.PhotoNote)) {
            return "LocalPhoto";
        }
        if (messageType.equals(MessageObj.MessageType.Video)) {
            return "LocalVideo";
        }
        if (messageType.equals(MessageObj.MessageType.Sticker) || messageType.equals(MessageObj.MessageType.AnimSticker) || messageType.equals(MessageObj.MessageType.AnimPngSticker)) {
            return "Sticker";
        }
        return null;
    }

    /* renamed from: g */
    public static void m25148g(Context context) {
        if (m25149h()) {
            return;
        }
        if (f22074a == null) {
            f22074a = m25142a(context);
        }
        C6253a.m23956b(context, "https://dna.cyberlink.com", m25143b());
        Globals.m7388i0().m7554h4(Globals.m7388i0().m7539e2());
        C6253a.m23962h(f22074a, "Stat_SysLocale_" + m25145d());
        if (m25149h()) {
            return;
        }
        f22076c = C4478c.m17942l(context, "e1f4afe7288c7ab3209b7a78c41546ac", true);
        f22075b = true;
    }

    /* renamed from: h */
    public static boolean m25149h() {
        boolean zM7414D1 = Globals.m7388i0().m7414D1();
        if (zM7414D1) {
            C6478z.m24810b("LogAgentHelper", "[LogEvent]isDebuggable=true");
        }
        return zM7414D1;
    }

    /* renamed from: i */
    public static boolean m25150i() {
        return m25149h() || !f22075b;
    }

    /* renamed from: j */
    public static void m25151j(boolean z8) {
        if (m25150i()) {
            return;
        }
        String strM25146e = m25146e(z8);
        HashMap map = new HashMap();
        map.put("Status", strM25146e);
        m25155n("Chat.MsgSend", map);
        C6253a.m23962h(f22074a, "Chat.MsgSend");
    }

    /* renamed from: k */
    public static void m25152k(boolean z8, MessageObj.MessageType messageType) {
        String strM25144c;
        if (m25150i() || (strM25144c = m25144c(messageType)) == null) {
            return;
        }
        String strM25146e = m25146e(z8);
        HashMap map = new HashMap();
        map.put("Status", strM25146e);
        m25155n(strM25144c, map);
        m25151j(z8);
        C6253a.m23962h(f22074a, strM25144c);
    }

    /* renamed from: l */
    public static void m25153l(String str, String str2) {
        if (m25150i()) {
            return;
        }
        HashMap map = new HashMap();
        map.put("duration", str2);
        m25155n(str, map);
        C6253a.m23962h(f22074a, str);
    }

    /* renamed from: m */
    public static void m25154m(String str) {
        if (m25150i()) {
            return;
        }
        C6253a.m23962h(f22074a, str);
        try {
            C4478c c4478c = f22076c;
            if (c4478c != null) {
                c4478c.m17950F(str, null);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    /* renamed from: n */
    public static void m25155n(String str, Map<String, String> map) {
        if (m25150i()) {
            return;
        }
        try {
            if (f22076c != null) {
                HashMap map2 = new HashMap();
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    map2.put(entry.getKey(), entry.getValue());
                }
                f22076c.m17950F(str, map2);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    /* renamed from: o */
    public static void m25156o(long j9) {
        if (m25150i()) {
            return;
        }
        HashMap map = new HashMap();
        map.put("FCMReceivedTimeLevel", C5176q0.m20226e(j9));
        m25155n("U_FCMMessage_Event", map);
        C6253a.m23962h(f22074a, "U_FCMMessage_Event");
    }

    /* renamed from: p */
    public static void m25157p(String str, String str2) {
        if (m25150i()) {
            return;
        }
        String str3 = str.equals("ChatRoom") ? "Chat.Multi.Menu" : str.equals("Circle") ? "Chat.Pgroup.Menu" : "Chat.Single.Menu";
        HashMap map = new HashMap();
        map.put("Item", str2);
        Log.d("LogEvent", "[LogEvent]logMenuEventWithItemParam event = " + str3);
        Log.d("LogEvent", "[LogEvent] logMenuEventWithItemParam var1 = " + str2);
        m25155n(str3, map);
        C6253a.m23962h(f22074a, str3);
    }

    /* renamed from: q */
    public static void m25158q(boolean z8, String str) {
        if (m25150i()) {
            return;
        }
        String str2 = z8 ? "MyMsg.ExpressMenu" : "Msg.ExpressMenu";
        HashMap map = new HashMap();
        map.put("Item", str);
        Log.d("LogEvent", "[LogEvent] logMsgExpressEventWithItemParam event = " + str2);
        Log.d("LogEvent", "[LogEvent] logMsgExpressEventWithItemParam var1 = " + str);
        m25155n(str2, map);
        C6253a.m23962h(f22074a, str2);
    }

    /* renamed from: r */
    public static void m25159r(String str) {
        if (m25150i()) {
            return;
        }
        HashMap map = new HashMap();
        map.put("Action", str);
        Log.d("LogEvent", "[LogEvent] logPhotoVwithActionParam event = photoV.action");
        Log.d("LogEvent", "[LogEvent] logPhotoVwithActionParam var1 = " + str);
        m25155n("photoV.action", map);
        C6253a.m23962h(f22074a, "photoV.action");
    }

    /* renamed from: s */
    public static void m25160s(String str, MessageObj.MessageType messageType) {
        if (m25150i()) {
            return;
        }
        HashMap map = new HashMap();
        String strM25147f = m25147f(messageType);
        if (strM25147f == null) {
            return;
        }
        map.put(strM25147f, str);
        m25155n("Chat_MsgSend_RoundTrip_Time", map);
        C6253a.m23962h(f22074a, "Chat_MsgSend_RoundTrip_Time");
    }

    /* renamed from: t */
    public static void m25161t(String str) {
        if (m25150i() || str == null) {
            return;
        }
        HashMap map = new HashMap();
        map.put("deviceModelInfo", str);
        Log.d("LogAgentHelper", "[logServerTranscodeInfoEvent] deviceModelNumber =  " + str);
        m25155n("Send_Video_ServerTranscode", map);
        C6253a.m23962h(f22074a, "Send_Video_ServerTranscode");
    }

    /* renamed from: u */
    public static void m25162u(String str) {
        String str2;
        if (m25150i()) {
            return;
        }
        Log.d("LogAgentHelper", "logUNOEvent:" + str);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        if ("Launch_App".equals(str)) {
            str2 = simpleDateFormat.format(new Date());
        } else {
            str2 = simpleDateFormat.format(new Date()) + "_1";
        }
        C6253a.m23962h(str, str2);
    }

    /* renamed from: v */
    public static void m25163v(String str) {
        if (m25150i() || str == null) {
            return;
        }
        HashMap map = new HashMap();
        map.put("Transcode_Type", str);
        Log.d("LogAgentHelper", "[logServerTranscodeInfoEvent] Type =  " + str);
        m25155n("Send_Video_Transcode", map);
        C6253a.m23962h(f22074a, "Send_Video_Transcode");
    }

    /* renamed from: w */
    public static void m25164w(String str, String str2) {
        if (m25150i()) {
            return;
        }
        HashMap map = new HashMap();
        map.put("duration", str);
        map.put("durationV2", str2);
        m25155n("U_XmppServerOpenConnectionTime", map);
        C6253a.m23962h(f22074a, "U_XmppServerOpenConnectionTime");
    }

    /* renamed from: x */
    public static void m25165x(Context context) {
        if (m25150i()) {
            return;
        }
        try {
            C6253a.m23960f();
        } catch (IllegalStateException e9) {
            C5154j.m20076j(e9);
        }
        C4478c c4478c = f22076c;
        if (c4478c != null) {
            c4478c.m17951h();
        }
    }

    /* renamed from: y */
    public static void m25166y(Context context) {
        if (m25150i()) {
            return;
        }
        C6253a.m23959e();
    }

    /* renamed from: z */
    public static void m25167z() {
        m25141A(m25143b());
    }
}
