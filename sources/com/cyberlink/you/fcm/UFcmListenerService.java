package com.cyberlink.you.fcm;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.util.Log;
import android.util.Pair;
import com.cyberlink.meeting.page.p032m.MeetingActivity;
import com.cyberlink.p030U.R;
import com.cyberlink.you.Globals;
import com.cyberlink.you.activity.NoticesActivity;
import com.cyberlink.you.activity.QueryMessageActivity;
import com.cyberlink.you.activity.chatdialog.ChatDialogActivity;
import com.cyberlink.you.chat.NotificationHelper;
import com.cyberlink.you.chat.XMPPManager;
import com.cyberlink.you.database.C2950b0;
import com.cyberlink.you.fcm.UFcmListenerService;
import com.cyberlink.you.friends.FriendsClient;
import com.cyberlink.you.friends.Group;
import com.cyberlink.you.utility.CLUtility;
import com.cyberlink.you.utility.ULogUtility;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.firebase.messaging.Constants;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import net.sqlcipher.database.SQLiteDatabase;
import org.json.JSONArray;
import org.json.JSONException;
import p095i3.C5049b;
import p116k4.C5170o0;
import p116k4.C5172p;
import p136m3.C5321e;
import p173q2.C6127a;
import p188s.C6232g;
import p201t3.C6301o;
import p209u2.AbstractC6381r;
import p209u2.C6385v;
import p236x2.C6566a;

/* loaded from: classes.dex */
public class UFcmListenerService extends FirebaseMessagingService {

    /* renamed from: b */
    public static String f13333b = "UFcmListenerService";

    /* renamed from: c */
    public static ThreadPoolExecutor f13334c = new ThreadPoolExecutor(1, 1, Long.MAX_VALUE, TimeUnit.SECONDS, new LinkedBlockingQueue());

    /* renamed from: com.cyberlink.you.fcm.UFcmListenerService$a */
    public class C3009a extends AbstractC6381r<Bitmap, Void> {

        /* renamed from: c */
        public final /* synthetic */ String f13335c;

        /* renamed from: d */
        public final /* synthetic */ String f13336d;

        /* renamed from: e */
        public final /* synthetic */ String f13337e;

        /* renamed from: f */
        public final /* synthetic */ Intent f13338f;

        /* renamed from: g */
        public final /* synthetic */ boolean f13339g;

        /* renamed from: h */
        public final /* synthetic */ String f13340h;

        public C3009a(String str, String str2, String str3, Intent intent, boolean z8, String str4) {
            this.f13335c = str;
            this.f13336d = str2;
            this.f13337e = str3;
            this.f13338f = intent;
            this.f13339g = z8;
            this.f13340h = str4;
        }

        @Override // p209u2.AbstractC6381r
        /* renamed from: k, reason: merged with bridge method [inline-methods] */
        public void m24503g(Bitmap bitmap) throws Resources.NotFoundException {
            UFcmListenerService uFcmListenerService = UFcmListenerService.this;
            try {
                String string = this.f13335c;
                String string2 = this.f13336d;
                String str = this.f13337e;
                boolean zM7439I1 = Globals.m7388i0().m7439I1();
                NotificationManager notificationManager = (NotificationManager) uFcmListenerService.getSystemService("notification");
                PendingIntent activity = PendingIntent.getActivity(uFcmListenerService, 0, this.f13338f, Build.VERSION.SDK_INT >= 31 ? 335544320 : SQLiteDatabase.CREATE_IF_NECESSARY);
                if (this.f13339g) {
                    string = uFcmListenerService.getResources().getString(R.string.u_app_name);
                    string2 = uFcmListenerService.getResources().getString(R.string.privacy_password_notification_content);
                    str = string2;
                }
                C6232g.e eVarM23843i = new C6232g.e(uFcmListenerService, NotificationHelper.m14104t()).m23858x(R.drawable.ic_stat_name_s).m23840f(true).m23847m(Globals.m7388i0().m7482S(zM7439I1)).m23846l(string).m23860z(new C6232g.c().m23828g(this.f13336d)).m23845k(string2).m23832A(str).m23844j(activity).m23859y(Globals.m7388i0().m7433H0(zM7439I1)).m23843i(Globals.m7388i0().m7423F0());
                if (bitmap != null) {
                    eVarM23843i.m23851q(bitmap);
                }
                notificationManager.cancel(2);
                notificationManager.notify(this.f13340h, 1, eVarM23843i.m23837b());
                Globals.m7388i0().m7420E2(this.f13340h);
                ULogUtility.m16659B("GCM", "message content:" + string2);
                ULogUtility.m16659B("GCM", "Notify success.");
                C5049b.m19736o();
                if (Globals.m7388i0().m7460N1()) {
                    return;
                }
                CLUtility.m16486R2(uFcmListenerService);
            } catch (Exception e9) {
                ULogUtility.m16659B("GCM", "Notify fail. exception=" + Log.getStackTraceString(e9));
            }
        }
    }

    /* renamed from: f */
    public static void m15364f(final boolean z8, final String str, final AbstractC6381r<Bitmap, Void> abstractC6381r) {
        C6385v.m24526d(new Runnable() { // from class: o3.b
            @Override // java.lang.Runnable
            public final void run() {
                UFcmListenerService.m15366m(z8, str, abstractC6381r);
            }
        });
    }

    /* renamed from: m */
    public static /* synthetic */ void m15366m(boolean z8, String str, final AbstractC6381r abstractC6381r) {
        if (z8) {
            str = null;
        }
        final Bitmap bitmapM23462c = C6127a.m23462c(Globals.m7372O(), str, R.drawable.ic_launcher);
        C6385v.m24527e(new Runnable() { // from class: o3.c
            @Override // java.lang.Runnable
            public final void run() {
                abstractC6381r.m24506d(bitmapM23462c);
            }
        });
    }

    /* renamed from: n */
    public static /* synthetic */ void m15367n(String str, String str2, String str3, String str4, String str5, String str6, boolean z8, String str7, String str8) {
        C5321e.m20824o().m20846J(str, str2, str3, str4, str5, str6, true, z8, str7, str8);
    }

    /* JADX WARN: Removed duplicated region for block: B:124:0x03cf  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x0289  */
    /* renamed from: d */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void m15368d(Map<String, String> map) throws Resources.NotFoundException {
        String str;
        Group group;
        Group group2;
        Object obj;
        Group groupM20186h;
        Intent intent;
        String str2;
        String string;
        Object obj2;
        if (map == null) {
            return;
        }
        if (!CLUtility.m16433E1()) {
            ULogUtility.m16683s(f13333b, "Notification is disable.");
            return;
        }
        if (m15371h(map)) {
            return;
        }
        String str3 = map.get(Constants.FirelogAnalytics.PARAM_MESSAGE_ID);
        if (!C5170o0.m20170e(str3)) {
            C5049b.m19734m(str3);
        }
        if (C5170o0.m20170e(str3)) {
            ULogUtility.m16659B("GCM", "Message id is empty.");
            return;
        }
        if (NotificationHelper.m14109y(str3, false).equals(NotificationHelper.HasNotifiedResultType.HAS_NOTIFIED)) {
            ULogUtility.m16659B("GCM", "Not notify. (This message was notified)");
            return;
        }
        NotificationHelper.m14067K(str3);
        String str4 = map.get("receiverId");
        if (C5170o0.m20170e(str4) || !str4.equals(String.valueOf(Globals.m7388i0().m7568k1()))) {
            ULogUtility.m16659B("GCM", "Receiver id is empty, or does not equal with my user id.");
            return;
        }
        String str5 = map.get("type");
        if (C5170o0.m20170e(str5) || !str5.equals("message")) {
            ULogUtility.m16683s(f13333b, "Type id is empty, or does not equal with \"message\".");
            return;
        }
        String str6 = map.get("avatar");
        boolean zM7507X1 = Globals.m7388i0().m7507X1();
        String str7 = map.get("userId");
        if (C5170o0.m20170e(str7)) {
            str = "type";
            String str8 = map.get("groupId");
            Group groupM15077n = !C5170o0.m20170e(str8) ? C2950b0.m14912k().m15077n(str8) : null;
            if (groupM15077n == null) {
                ULogUtility.m16659B("GCM", "Start query group info from server.");
                FriendsClient friendsClient = new FriendsClient();
                ArrayList arrayList = new ArrayList();
                group2 = groupM15077n;
                arrayList.add(new C6301o("token", Globals.m7388i0().m7449L()));
                arrayList.add(new C6301o("groupId", str8));
                Pair<String, String> pairM15731j = friendsClient.m15731j("group", "groupInfo", arrayList);
                Object obj3 = pairM15731j.first;
                if (obj3 == null || !((String) obj3).equals("200") || (obj = pairM15731j.second) == null) {
                    ULogUtility.m16659B("GCM", "Fail to get group info from server. statusCode=" + ((String) pairM15731j.first));
                } else {
                    JSONArray jSONArrayM20196r = C5172p.m20196r((String) obj);
                    if (jSONArrayM20196r == null || jSONArrayM20196r.length() <= 0) {
                        ULogUtility.m16659B("GCM", "Get empty content from server. content=" + ((String) pairM15731j.second));
                    } else {
                        try {
                            groupM20186h = C5172p.m20186h(jSONArrayM20196r.getJSONObject(0));
                            if (groupM20186h != null) {
                                try {
                                    Group groupM15077n2 = C2950b0.m14912k().m15077n(String.valueOf(groupM20186h.f13727n));
                                    if (groupM15077n2 != null) {
                                        groupM20186h.f13714M = groupM15077n2.f13714M;
                                    }
                                } catch (JSONException e9) {
                                    e = e9;
                                    ULogUtility.m16659B("GCM", "Fail to parse group info from server. statusCode=" + ((String) pairM15731j.second));
                                    e.printStackTrace();
                                    group = groupM20186h;
                                    if (group == null) {
                                    }
                                }
                            }
                            ULogUtility.m16659B("GCM", "Get group info from server.");
                        } catch (JSONException e10) {
                            e = e10;
                            groupM20186h = group2;
                        }
                        group = groupM20186h;
                    }
                }
                groupM20186h = group2;
                group = groupM20186h;
            } else {
                ULogUtility.m16659B("GCM", "Get group info from db.");
                group = groupM15077n;
            }
        } else {
            String str9 = str7 + "@u.cyberlink.com";
            groupM20186h = C2950b0.m14912k().m15081r(str9);
            if (groupM20186h == null) {
                ULogUtility.m16659B("GCM", "Start query group info from server.");
                FriendsClient friendsClient2 = new FriendsClient();
                ArrayList arrayList2 = new ArrayList();
                group2 = groupM20186h;
                str = "type";
                arrayList2.add(new C6301o("token", Globals.m7388i0().m7449L()));
                arrayList2.add(new C6301o("jid", str9));
                Pair<String, String> pairM15731j2 = friendsClient2.m15731j("group", "groupInfo", arrayList2);
                if (!"200".equals(pairM15731j2.first) || (obj2 = pairM15731j2.second) == null) {
                    ULogUtility.m16659B("GCM", "Fail to get group info from server. statusCode=" + ((String) pairM15731j2.first));
                } else {
                    JSONArray jSONArrayM20196r2 = C5172p.m20196r((String) obj2);
                    if (jSONArrayM20196r2 == null || jSONArrayM20196r2.length() <= 0) {
                        ULogUtility.m16659B("GCM", "Get empty content from server. content=" + ((String) pairM15731j2.second));
                    } else {
                        try {
                            groupM20186h = C5172p.m20186h(jSONArrayM20196r2.getJSONObject(0));
                            if (groupM20186h != null) {
                                try {
                                    Group groupM15077n3 = C2950b0.m14912k().m15077n(String.valueOf(groupM20186h.f13727n));
                                    if (groupM15077n3 != null) {
                                        groupM20186h.f13714M = groupM15077n3.f13714M;
                                    }
                                } catch (JSONException e11) {
                                    e = e11;
                                    ULogUtility.m16659B("GCM", "Fail to parse group info from server. statusCode=" + ((String) pairM15731j2.second));
                                    e.printStackTrace();
                                    group = groupM20186h;
                                    if (group == null) {
                                    }
                                }
                            }
                            ULogUtility.m16659B("GCM", "Get group info from server.");
                        } catch (JSONException e12) {
                            e = e12;
                            groupM20186h = group2;
                        }
                    }
                }
                groupM20186h = group2;
            } else {
                str = "type";
                ULogUtility.m16659B("GCM", "Get group info from db.");
            }
            group = groupM20186h;
        }
        if (group == null) {
            ULogUtility.m16659B("GCM", "Group is null.");
            return;
        }
        String str10 = map.get("callId");
        String str11 = map.get("callType");
        if (C5170o0.m20170e(str10) || !C5170o0.m20170e(str11)) {
            Intent intent2 = new Intent(this, (Class<?>) QueryMessageActivity.class);
            intent2.addFlags(335577088);
            Bundle bundle = new Bundle();
            bundle.putParcelable("Group", group);
            bundle.putString(Constants.FirelogAnalytics.PARAM_MESSAGE_ID, str3);
            bundle.putSerializable("handleType", XMPPManager.HandleType.GCM);
            intent2.putExtras(bundle);
            intent = intent2;
        } else {
            if (!str11.equals(MimeTypes.BASE_TYPE_VIDEO) && !str11.equals(MimeTypes.BASE_TYPE_AUDIO)) {
                return;
            }
            ULogUtility.m16659B("GCM", "receive meeting invited | meetingId:" + str10);
            intent = new Intent(this, (Class<?>) MeetingActivity.class);
            intent.addFlags(343965696);
            Bundle bundle2 = new Bundle();
            bundle2.putString("action", "join");
            bundle2.putString("meetingId", str10);
            bundle2.putSerializable("inviteCallType", MeetingActivity.InviteCallType.CALLEE);
            bundle2.putString(str, str11);
            bundle2.putParcelable("group", group);
            intent.putExtras(bundle2);
        }
        String str12 = zM7507X1 ? group.f13717d : "U";
        String string2 = getResources().getString(R.string.notification_default_string);
        if (zM7507X1) {
            string2 = map.get("message");
        }
        if (ChatDialogActivity.f9790V2 == group.f13727n) {
            ULogUtility.m16659B("GCM", "The group is opening in chat room now. currentGroupId=" + group.f13727n);
            return;
        }
        ULogUtility.m16659B("GCM", "Start send message notification.");
        if (Globals.m7388i0().m7534d2()) {
            StringBuilder sb = new StringBuilder();
            sb.append("(");
            XMPPManager.HandleType handleType = XMPPManager.HandleType.GCM;
            sb.append(handleType);
            sb.append(")");
            sb.append(str12);
            string = sb.toString();
            str2 = "(" + handleType + ")" + string2;
        } else {
            str2 = string2;
            string = str12;
        }
        NotificationHelper.m14080X(group, str2, XMPPManager.HandleType.GCM, str6, group.f13716c.equals("Dual") ? null : string, System.currentTimeMillis());
        m15378r(group.f13723j, string, str2, str2, intent, str6);
    }

    /* renamed from: e */
    public final void m15369e(Map<String, String> map) {
        StringBuilder sb = new StringBuilder();
        sb.append("FCM got a message. data: ");
        for (String str : map.keySet()) {
            sb.append("\n");
            sb.append(str);
            sb.append(": ");
            sb.append(map.get(str));
        }
        ULogUtility.m16683s(f13333b, sb.toString());
    }

    /* renamed from: g */
    public final long m15370g(Map<String, String> map) {
        try {
            String str = map.get("nt");
            if (C5170o0.m20170e(str)) {
                return 0L;
            }
            return Long.parseLong(str);
        } catch (Exception unused) {
            ULogUtility.m16676l(f13333b, "parse nt from string to long fail.");
            return 0L;
        }
    }

    /* renamed from: h */
    public final boolean m15371h(Map<String, String> map) {
        if (map != null) {
            String str = map.get("Title");
            String str2 = map.get("Msg");
            String str3 = map.get("TickerText");
            if (!C5170o0.m20170e(str) && !C5170o0.m20170e(str2) && !C5170o0.m20170e(str3)) {
                ULogUtility.m16683s(f13333b, "Start send notice notification.");
                m15377q(str, str2, str3);
                return true;
            }
        }
        return false;
    }

    /* renamed from: i */
    public final void m15372i(Map<String, String> map) throws Resources.NotFoundException {
        ULogUtility.m16680p(f13333b, "handleFcmData() IN");
        m15376p(map.get("nt"));
        if (Globals.m7388i0().m7483S0().equals("v2")) {
            if (m15374k(map)) {
                m15373j(map);
            }
            ULogUtility.m16683s(f13333b, "GCM triggers to query archive messages via HTTP");
            C5049b.m19729h();
            m15371h(map);
        } else {
            m15368d(map);
        }
        ULogUtility.m16680p(f13333b, "handleFcmData() OUT");
    }

    /* renamed from: j */
    public final void m15373j(Map<String, String> map) {
        ULogUtility.m16680p(f13333b, "handleMeetingGcm");
        final String str = map.get("callId");
        final String str2 = map.get("callerId");
        final String str3 = map.get("calleeId");
        final String str4 = map.get("callType");
        final String str5 = map.get("mServerAddr");
        final String str6 = map.get("mServerToken");
        final boolean zContainsKey = map.containsKey("fromPSTN");
        String str7 = map.containsKey("callerName") ? map.get("callerName") : "";
        final String str8 = map.containsKey("callerExt") ? map.get("callerExt") : "";
        final String number = (C5170o0.m20170e(str7) || !PhoneNumberUtils.isGlobalPhoneNumber(str7) || !C5170o0.m20170e(str8) || (str7.length() == 8 && Locale.getDefault().getCountry().equals(Locale.TAIWAN.getCountry()))) ? str7 : PhoneNumberUtils.formatNumber(str7, Locale.getDefault().getCountry());
        f13334c.execute(new Runnable() { // from class: o3.a
            @Override // java.lang.Runnable
            public final void run() {
                UFcmListenerService.m15367n(str2, str3, str, str4, str5, str6, zContainsKey, number, str8);
            }
        });
    }

    /* renamed from: k */
    public final boolean m15374k(Map<String, String> map) {
        if (C5170o0.m20170e(map.get("callType")) || C5170o0.m20170e(map.get("callId")) || C5170o0.m20170e(map.get("callerId")) || C5170o0.m20170e(map.get("calleeId")) || C5170o0.m20170e(map.get("nt"))) {
            return false;
        }
        String str = map.get("callType");
        long jM15370g = m15370g(map);
        long jM15646K = FriendsClient.m15646K();
        long jAbs = Math.abs(jM15646K - jM15370g);
        ULogUtility.m16683s(f13333b, "check meeting invite event received time, nt = " + jM15370g + " | estimatedServerTime = " + jM15646K + " | receivedTimeDif = " + jAbs);
        if (jM15370g <= 0 || jAbs >= 60000) {
            return false;
        }
        return str.equals(MimeTypes.BASE_TYPE_AUDIO) || str.equals(MimeTypes.BASE_TYPE_VIDEO);
    }

    /* renamed from: o */
    public final void m15375o(long j9, long j10) {
        C6566a.m25166y(this);
        C6566a.m25156o(j10 - j9);
        C6566a.m25165x(this);
    }

    @Override // com.google.firebase.messaging.FirebaseMessagingService
    public void onMessageReceived(RemoteMessage remoteMessage) throws Resources.NotFoundException {
        Map<String, String> data = remoteMessage.getData();
        m15369e(data);
        m15372i(data);
    }

    /* renamed from: p */
    public final void m15376p(String str) {
        try {
            m15375o(Long.parseLong(str), FriendsClient.m15646K());
        } catch (NumberFormatException unused) {
        }
    }

    /* renamed from: q */
    public final void m15377q(String str, String str2, String str3) {
        boolean zM7439I1 = Globals.m7388i0().m7439I1();
        NotificationManager notificationManager = (NotificationManager) getSystemService("notification");
        Intent intent = new Intent(this, (Class<?>) NoticesActivity.class);
        intent.putExtra("handleType", XMPPManager.HandleType.NOTICE);
        PendingIntent activity = PendingIntent.getActivity(this, 0, intent, Build.VERSION.SDK_INT >= 31 ? 201326592 : 0);
        Globals.m7388i0().m7416D3(true);
        C6232g.e eVarM23843i = new C6232g.e(this, NotificationHelper.m14104t()).m23858x(R.drawable.ic_stat_name_s).m23840f(true).m23847m(Globals.m7388i0().m7482S(zM7439I1)).m23846l(str).m23860z(new C6232g.c().m23828g(str2)).m23845k(str2).m23832A(str3).m23859y(Globals.m7388i0().m7433H0(zM7439I1)).m23843i(Globals.m7388i0().m7423F0());
        Bitmap bitmapM23462c = C6127a.m23462c(this, null, R.drawable.ic_launcher);
        if (bitmapM23462c != null) {
            eVarM23843i.m23851q(bitmapM23462c);
        }
        eVarM23843i.m23844j(activity);
        notificationManager.notify(0, eVarM23843i.m23837b());
        ULogUtility.m16659B("GCM", "Notify notice message success.");
    }

    /* renamed from: r */
    public final void m15378r(String str, String str2, String str3, String str4, Intent intent, String str5) {
        boolean z8 = !Globals.m7388i0().m7507X1();
        m15364f(z8, str5, new C3009a(str2, str3, str4, intent, z8, str));
    }
}
