package com.cyberlink.you.alarm;

import android.content.Context;
import android.content.Intent;
import android.util.Pair;
import androidx.core.app.JobIntentService;
import com.cyberlink.you.Globals;
import com.cyberlink.you.chat.C2904l;
import com.cyberlink.you.chat.NotificationHelper;
import com.cyberlink.you.chat.XMPPManager;
import com.cyberlink.you.database.C2950b0;
import com.cyberlink.you.friends.FriendsClient;
import com.cyberlink.you.friends.Group;
import com.cyberlink.you.utility.ULogUtility;
import java.util.ArrayList;
import java.util.List;
import org.jivesoftware.smack.packet.AbstractC5594b;
import org.jivesoftware.smack.packet.Message;
import p095i3.C5049b;
import p116k4.C5154j;
import p201t3.C6301o;

/* loaded from: classes.dex */
public class AlarmService extends JobIntentService {

    /* renamed from: k */
    public static boolean f12355k = false;

    /* renamed from: com.cyberlink.you.alarm.AlarmService$a */
    public class C2825a extends Thread {
        public C2825a() {
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            List<Group> listM15713R;
            Thread.currentThread().setName("Heartbeat");
            long jLongValue = Globals.m7388i0().m7568k1().longValue();
            String strM7587o0 = Globals.m7388i0().m7587o0();
            ULogUtility.m16690z("V1 Heartbeat thread start.");
            if (jLongValue == 0 || strM7587o0.isEmpty()) {
                return;
            }
            ArrayList arrayList = new ArrayList();
            arrayList.add(new C6301o("u", String.valueOf(jLongValue)));
            arrayList.add(new C6301o("r", strM7587o0));
            ULogUtility.m16690z("Heartbeat api start query. isFailBefore=" + AlarmService.f12355k);
            Pair<String, String> pairM15731j = !AlarmService.f12355k ? new FriendsClient().m15731j("chat", "heartbeat", arrayList) : Pair.create(null, null);
            ULogUtility.m16690z("Heartbeat api end query.");
            String str = (String) pairM15731j.first;
            String str2 = (String) pairM15731j.second;
            ULogUtility.m16690z("Heartbeat api query status code = " + str + " result = " + str2);
            if (str == null || !str.equals("200") || str2 == null || !str2.equals("0")) {
                for (int i9 = 0; i9 < 3; i9++) {
                    AbstractC5594b abstractC5594bM19732k = C5049b.m19732k();
                    if (abstractC5594bM19732k != null) {
                        ULogUtility.m16690z("last packet = " + abstractC5594bM19732k.toString());
                        boolean unused = AlarmService.f12355k = false;
                        XMPPManager.HandleType handleType = XMPPManager.HandleType.HEART_BEAT;
                        C2904l c2904l = new C2904l((Message) abstractC5594bM19732k, handleType);
                        if (c2904l.m14422j().after(Globals.m7388i0().m7502W0())) {
                            ULogUtility.m16690z("Last packet try to find group from DB.");
                            Group groupM15081r = C2950b0.m14912k().m15081r(c2904l.m14418h());
                            if (groupM15081r == null && (listM15713R = new FriendsClient().m15713R(c2904l.m14418h())) != null && !listM15713R.isEmpty()) {
                                groupM15081r = listM15713R.get(0);
                            }
                            if (groupM15081r != null && c2904l.m14418h().equals(groupM15081r.f13723j)) {
                                ULogUtility.m16690z("Last packet start send notification.");
                                if (c2904l.m14430n().equals(String.valueOf(Globals.m7388i0().m7568k1()))) {
                                    return;
                                }
                                NotificationHelper.m14068L(groupM15081r, c2904l, handleType);
                                return;
                            }
                            if (groupM15081r == null || c2904l.m14418h().equals(groupM15081r.f13723j)) {
                                ULogUtility.m16690z("Last packet can not find group from DB.");
                                return;
                            } else {
                                ULogUtility.m16690z("Last packet is sent by myself.");
                                return;
                            }
                        }
                        ULogUtility.m16690z("Last packet is expired. registration time = " + Globals.m7388i0().m7502W0());
                    } else {
                        ULogUtility.m16690z("last packet = null");
                    }
                    if (i9 == 2) {
                        boolean unused2 = AlarmService.f12355k = true;
                    }
                }
            }
        }
    }

    /* renamed from: l */
    public static void m14001l(Context context, Intent intent) {
        try {
            JobIntentService.m1472d(context, AlarmService.class, 1001, intent);
        } catch (Exception e9) {
            C5154j.m20076j(e9);
        }
    }

    @Override // androidx.core.app.JobIntentService
    /* renamed from: g */
    public void mo1477g(Intent intent) {
        ULogUtility.m16690z("Alarm service start.");
        if (!Globals.m7396z1()) {
            Globals.m7388i0().m7495U2(false);
        }
        if (Globals.m7388i0().m7483S0().equals("v2")) {
            C5049b.m19730i();
        } else if (!Globals.m7388i0().m7483S0().equals("v1")) {
            ULogUtility.m16690z("Query Message version is wrong. Not to do heartbeat.");
        } else if (Globals.m7388i0().m7446K1()) {
            ULogUtility.m16690z("start init V1 heartbeat runnable");
            new C2825a().start();
        } else {
            ULogUtility.m16690z("Heartbeat is disable. Not to do heartbeat.");
        }
        stopSelf();
    }
}
