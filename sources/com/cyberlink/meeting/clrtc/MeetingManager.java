package com.cyberlink.meeting.clrtc;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Pair;
import com.cyberlink.clrtc.C1121t;
import com.cyberlink.clrtc.NileNetwork;
import com.cyberlink.meeting.clrtc.MeetingManager;
import com.cyberlink.meeting.model.BreakoutRoom;
import com.cyberlink.meeting.page.p032m.MeetingActivity;
import com.cyberlink.meeting.page.p032m.PreJoinMeetingFailActivity;
import com.cyberlink.util.DeviceCapability;
import com.cyberlink.you.Globals;
import com.cyberlink.you.chat.p034e.C2889b;
import com.cyberlink.you.friends.Friend;
import com.cyberlink.you.friends.Group;
import com.cyberlink.you.utility.CLUtility;
import com.cyberlink.you.utility.ULogUtility;
import com.google.android.exoplayer2.source.smoothstreaming.SsMediaSource;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import net.sqlcipher.database.SQLiteDatabase;
import org.webrtc.EglBase;
import p116k4.AbstractC5146g0;
import p116k4.C5154j;
import p135m2.C5314a;
import p209u2.C6389z;

/* loaded from: classes.dex */
public class MeetingManager {

    /* renamed from: a */
    public static final HashMap<String, MeetingStatus> f6208a = new HashMap<>();

    /* renamed from: b */
    public static final HashMap<String, ArrayList<BreakoutRoom>> f6209b = new HashMap<>();

    /* renamed from: c */
    public static final HashMap<String, List<Integer>> f6210c = new HashMap<>();

    /* renamed from: d */
    public static final HashMap<String, Boolean> f6211d = new HashMap<>();

    /* renamed from: e */
    public static final C6389z<InterfaceC1253c> f6212e = new C6389z<>("MeetingManager");

    /* renamed from: f */
    public static List<Integer> f6213f = new ArrayList();

    /* renamed from: g */
    public static List<Integer> f6214g = new ArrayList();

    /* renamed from: h */
    public static final Object f6215h = new Object();

    /* renamed from: i */
    public static final HashMap<String, NileNetwork> f6216i = new HashMap<>();

    /* renamed from: j */
    public static final HashMap<String, Runnable> f6217j = new HashMap<>();

    /* renamed from: k */
    public static final Handler f6218k = new Handler(Looper.getMainLooper());

    public enum MeetingStatus {
        UNKNOWN,
        PRE_JOIN,
        START_ACTIVITY,
        IN_MEETING,
        HANG_UP,
        MEETING_END,
        REMOVE_FROM_MAP,
        PRE_JOIN_FAILED
    }

    /* renamed from: com.cyberlink.meeting.clrtc.MeetingManager$a */
    public class C1251a extends C6389z.a<InterfaceC1253c> {

        /* renamed from: a */
        public final /* synthetic */ String f6228a;

        /* renamed from: b */
        public final /* synthetic */ MeetingStatus f6229b;

        public C1251a(String str, MeetingStatus meetingStatus) {
            this.f6228a = str;
            this.f6229b = meetingStatus;
        }

        @Override // p209u2.C6389z.a
        /* renamed from: a */
        public void mo5634a(String str, Throwable th) {
            C5154j.m20076j(th);
        }

        @Override // p209u2.C6389z.a
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public void mo5635b(InterfaceC1253c interfaceC1253c) {
            interfaceC1253c.mo5638R(this.f6228a, this.f6229b);
        }
    }

    /* renamed from: com.cyberlink.meeting.clrtc.MeetingManager$b */
    public class C1252b extends C6389z.a<InterfaceC1253c> {

        /* renamed from: a */
        public final /* synthetic */ String f6230a;

        /* renamed from: b */
        public final /* synthetic */ String f6231b;

        public C1252b(String str, String str2) {
            this.f6230a = str;
            this.f6231b = str2;
        }

        @Override // p209u2.C6389z.a
        /* renamed from: a */
        public void mo5634a(String str, Throwable th) {
            C5154j.m20076j(th);
        }

        @Override // p209u2.C6389z.a
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public void mo5635b(InterfaceC1253c interfaceC1253c) {
            interfaceC1253c.mo5639S(this.f6230a, this.f6231b);
        }
    }

    /* renamed from: com.cyberlink.meeting.clrtc.MeetingManager$c */
    public interface InterfaceC1253c extends C6389z.b {
        /* renamed from: R */
        void mo5638R(String str, MeetingStatus meetingStatus);

        /* renamed from: S */
        void mo5639S(String str, String str2);
    }

    /* renamed from: A */
    public static void m5601A(InterfaceC1253c interfaceC1253c) {
        f6212e.m24541g(interfaceC1253c);
    }

    /* renamed from: B */
    public static void m5602B(String str) {
        HashMap<String, Runnable> map = f6217j;
        synchronized (map) {
            Runnable runnable = map.get(str);
            if (runnable != null) {
                ULogUtility.m16680p("MeetingManager", "[" + str + "] removePreJoinTimeoutRunnable.");
                f6218k.removeCallbacks(runnable);
                map.remove(str);
            }
        }
    }

    /* renamed from: C */
    public static void m5603C(String str, String str2) {
        HashMap<String, MeetingStatus> map = f6208a;
        synchronized (map) {
            MeetingStatus meetingStatus = map.get(str);
            if (meetingStatus != null) {
                map.put(str2, meetingStatus);
                map.remove(str);
                m5624q(str, str2);
                ULogUtility.m16670f("MeetingManager", "[" + str2 + "] replaceMeetingStatus oldMeetingId = " + str + " newMeetingId = " + str2);
            }
            HashMap<String, NileNetwork> map2 = f6216i;
            NileNetwork nileNetwork = map2.get(str);
            if (nileNetwork != null) {
                map2.put(str2, nileNetwork);
                map2.remove(str);
                ULogUtility.m16670f("MeetingManager", "[" + str2 + "] replaceNileNetwork oldMeetingId = " + str + " newMeetingId = " + str2);
            }
        }
    }

    /* renamed from: D */
    public static void m5604D(List<Integer> list) {
        f6214g = new ArrayList(list);
    }

    /* renamed from: E */
    public static void m5605E(List<Integer> list) {
        f6213f = new ArrayList(list);
    }

    /* renamed from: F */
    public static void m5606F(Group group, String str, Friend friend, String str2, String str3, String str4, boolean z8, boolean z9, String str5, int i9, boolean z10) {
        m5625r(str, MeetingStatus.PRE_JOIN_FAILED);
        Context applicationContext = Globals.m7388i0().getApplicationContext();
        Bundle bundle = new Bundle();
        bundle.putString("action", "join");
        bundle.putString("meetingId", str);
        bundle.putSerializable("inviteCallType", MeetingActivity.InviteCallType.CALLEE);
        bundle.putParcelable("group", group);
        bundle.putParcelable("inviter", friend);
        bundle.putString("type", str2);
        bundle.putString("meetingMServerAddress", str3);
        bundle.putString("meetingMServerToken", str4);
        bundle.putBoolean("isPreJoinMeeting", z8);
        if (z9 && str5 != null) {
            bundle.putBoolean("c.c.u.m.REACH_LIMIT", true);
            bundle.putString("c.c.u.m.HOST_NAME", str5);
            bundle.putInt("c.c.u.m.LIMIT_COUNT", i9);
        } else if (z10) {
            bundle.putBoolean("c.c.u.m.OLD_APP", true);
        }
        Intent intent = new Intent(applicationContext, (Class<?>) PreJoinMeetingFailActivity.class);
        intent.putExtras(bundle);
        intent.addFlags(SQLiteDatabase.CREATE_IF_NECESSARY);
        applicationContext.startActivity(intent);
    }

    /* renamed from: G */
    public static void m5607G(final Group group, final String str, final Friend friend, final String str2, final String str3, final String str4, final boolean z8, final boolean z9) {
        ULogUtility.m16680p("MeetingManager", "[" + str + "] startPreJoinTimeoutRunnable");
        Runnable runnable = new Runnable() { // from class: m2.c
            @Override // java.lang.Runnable
            public final void run() {
                MeetingManager.m5623p(str, z9, group, friend, str2, str3, str4, z8);
            }
        };
        synchronized (f6208a) {
            if (m5621n(m5615h(str))) {
                ULogUtility.m16676l("MeetingManager", "[" + str + "]  not start pre-join meeting timeout runnable because meeting is hang up or end.");
                return;
            }
            HashMap<String, Runnable> map = f6217j;
            synchronized (map) {
                ULogUtility.m16680p("MeetingManager", "[" + str + "] put pre join runnable into map");
                map.put(str, runnable);
            }
            f6218k.postDelayed(runnable, SsMediaSource.DEFAULT_LIVE_PRESENTATION_DELAY_MS);
        }
    }

    /* renamed from: b */
    public static void m5609b(InterfaceC1253c interfaceC1253c) {
        f6212e.m24539c(interfaceC1253c);
    }

    /* renamed from: c */
    public static NileNetwork m5610c(String str) {
        NileNetwork nileNetwork;
        synchronized (f6215h) {
            ULogUtility.m16683s("MeetingManager", "[" + str + "] createNileNetwork IN");
            if (m5616i(str) != null) {
                ULogUtility.m16683s("MeetingManager", "[" + str + "] releaseNileNetwork because it's exist.");
                m5632y(str);
            }
            C5314a.m20793c();
            ULogUtility.m16683s("MeetingManager", "[" + str + "] " + C5314a.m20792b());
            File file = new File(CLUtility.m16502X(), "CLRTC");
            DeviceCapability.C1399a c1399aM7311e = DeviceCapability.m7311e();
            nileNetwork = new NileNetwork(Globals.m7372O(), EglBase.create().getEglBaseContext(), file, (c1399aM7311e.m7324e() || C1121t.m5155d()) ? 16000 : 48000, c1399aM7311e.m7326g() || C1121t.m5156e());
            nileNetwork.m4935d8(ULogUtility.m16678n(ULogUtility.LogFileType.Log, true));
            nileNetwork.m4941g8(AbstractC5146g0.m20043a(Globals.m7372O()));
            nileNetwork.m4937e8(DeviceCapability.m7313g());
            if (Globals.m7388i0().m7464O1() && C2889b.m14298h().m14314r()) {
                nileNetwork.m4967t8(C2889b.m14298h().m14307j(true), C2889b.m14298h().m14307j(false));
            }
            f6216i.put(str, nileNetwork);
            ULogUtility.m16683s("MeetingManager", "[" + str + "] createNileNetwork OUT");
        }
        return nileNetwork;
    }

    /* renamed from: d */
    public static synchronized String m5611d() {
        String string;
        HashMap<String, MeetingStatus> map = f6208a;
        synchronized (map) {
            string = map.toString();
        }
        return string;
    }

    /* renamed from: e */
    public static ArrayList<BreakoutRoom> m5612e(String str) {
        ArrayList<BreakoutRoom> arrayList;
        HashMap<String, ArrayList<BreakoutRoom>> map = f6209b;
        synchronized (map) {
            arrayList = map.get(str);
        }
        return arrayList;
    }

    /* renamed from: f */
    public static List<Integer> m5613f(String str) {
        List<Integer> list;
        HashMap<String, List<Integer>> map = f6210c;
        synchronized (map) {
            list = map.get(str);
        }
        return list;
    }

    /* renamed from: g */
    public static Boolean m5614g(String str) {
        Boolean bool;
        HashMap<String, Boolean> map = f6211d;
        synchronized (map) {
            bool = map.get(str);
        }
        return bool;
    }

    /* renamed from: h */
    public static MeetingStatus m5615h(String str) {
        MeetingStatus meetingStatus;
        HashMap<String, MeetingStatus> map = f6208a;
        synchronized (map) {
            meetingStatus = map.get(str);
            if (meetingStatus == null) {
                meetingStatus = MeetingStatus.UNKNOWN;
            }
        }
        return meetingStatus;
    }

    /* renamed from: i */
    public static NileNetwork m5616i(String str) {
        NileNetwork nileNetwork;
        synchronized (f6215h) {
            nileNetwork = f6216i.get(str);
        }
        return nileNetwork;
    }

    /* renamed from: j */
    public static List<Integer> m5617j() {
        return f6214g;
    }

    /* renamed from: k */
    public static List<Integer> m5618k() {
        return f6213f;
    }

    /* renamed from: l */
    public static synchronized boolean m5619l() {
        HashMap<String, MeetingStatus> map = f6208a;
        synchronized (map) {
            Iterator<String> it = map.keySet().iterator();
            while (it.hasNext()) {
                if (m5620m(it.next())) {
                    return true;
                }
            }
            return false;
        }
    }

    /* renamed from: m */
    public static synchronized boolean m5620m(String str) {
        HashMap<String, MeetingStatus> map = f6208a;
        synchronized (map) {
            MeetingStatus meetingStatus = map.get(str);
            if (meetingStatus != MeetingStatus.START_ACTIVITY && meetingStatus != MeetingStatus.IN_MEETING && meetingStatus != MeetingStatus.PRE_JOIN) {
                return false;
            }
            ULogUtility.m16683s("MeetingManager", "[isInMeeting] user is in meeting, meetingId = " + str);
            return true;
        }
    }

    /* renamed from: n */
    public static boolean m5621n(MeetingStatus meetingStatus) {
        return meetingStatus == MeetingStatus.UNKNOWN || meetingStatus == MeetingStatus.HANG_UP || meetingStatus == MeetingStatus.MEETING_END;
    }

    /* renamed from: o */
    public static synchronized boolean m5622o() {
        HashMap<String, MeetingStatus> map = f6208a;
        synchronized (map) {
            for (String str : map.keySet()) {
                if (f6208a.get(str) == MeetingStatus.IN_MEETING) {
                    ULogUtility.m16683s("MeetingManager", "[isMeetingStart] meetingId = " + str);
                    return true;
                }
            }
            return false;
        }
    }

    /* renamed from: p */
    public static /* synthetic */ void m5623p(String str, boolean z8, Group group, Friend friend, String str2, String str3, String str4, boolean z9) {
        ULogUtility.m16680p("MeetingManager", "[" + str + "] preJoin timeout, release NileNetwork and meetingStatus.");
        m5631x(str);
        if (!z8) {
            m5606F(group, str, friend, str2, str3, str4, z9, false, null, 0, false);
            return;
        }
        ULogUtility.m16683s("MeetingManager", "[" + str + "] The meeting is handle by GCM, the meeting maybe is canceled or end | not show pre-join failed page");
    }

    /* renamed from: q */
    public static void m5624q(String str, String str2) {
        f6212e.m24540f(new C1252b(str, str2));
    }

    /* renamed from: r */
    public static void m5625r(String str, MeetingStatus meetingStatus) {
        f6212e.m24540f(new C1251a(str, meetingStatus));
    }

    /* renamed from: s */
    public static void m5626s(String str, List<Pair<String, String>> list) {
        synchronized (f6209b) {
            ArrayList<BreakoutRoom> arrayList = new ArrayList<>();
            for (Pair<String, String> pair : list) {
                BreakoutRoom breakoutRoom = new BreakoutRoom();
                breakoutRoom.f6329d = (String) pair.first;
                breakoutRoom.f6327b = (String) pair.second;
                breakoutRoom.f6330e = str;
                arrayList.add(breakoutRoom);
            }
            f6209b.put(str, arrayList);
        }
    }

    /* renamed from: t */
    public static void m5627t(String str, int i9, int i10) {
        HashMap<String, ArrayList<BreakoutRoom>> map = f6209b;
        synchronized (map) {
            ArrayList<BreakoutRoom> arrayList = map.get(str);
            if (arrayList != null) {
                long jCurrentTimeMillis = (System.currentTimeMillis() / 1000) - i9;
                Iterator<BreakoutRoom> it = arrayList.iterator();
                while (it.hasNext()) {
                    BreakoutRoom next = it.next();
                    next.f6331f = jCurrentTimeMillis;
                    next.f6332g = i10;
                }
            }
        }
    }

    /* renamed from: u */
    public static void m5628u(String str, List<Integer> list) {
        HashMap<String, List<Integer>> map = f6210c;
        synchronized (map) {
            map.put(str, list);
        }
    }

    /* renamed from: v */
    public static void m5629v(String str, boolean z8) {
        HashMap<String, Boolean> map = f6211d;
        synchronized (map) {
            map.put(str, Boolean.valueOf(z8));
        }
    }

    /* renamed from: w */
    public static void m5630w(String str, MeetingStatus meetingStatus) {
        HashMap<String, MeetingStatus> map = f6208a;
        synchronized (map) {
            map.put(str, meetingStatus);
            m5625r(str, meetingStatus);
            ULogUtility.m16670f("MeetingManager", "[" + str + "] putMeetingStatus, status = " + meetingStatus);
        }
    }

    /* renamed from: x */
    public static void m5631x(String str) {
        ULogUtility.m16680p("MeetingManager", "[" + str + "] releaseMeeting.");
        m5602B(str);
        m5633z(str);
        m5632y(str);
    }

    /* renamed from: y */
    public static void m5632y(String str) {
        synchronized (f6215h) {
            ULogUtility.m16683s("MeetingManager", "[" + str + "] releaseNileNetwork IN");
            HashMap<String, NileNetwork> map = f6216i;
            NileNetwork nileNetwork = map.get(str);
            if (nileNetwork != null) {
                ULogUtility.m16680p("MeetingManager", "[" + str + "] releaseNileNetwork.");
                nileNetwork.m4965s8(null);
                nileNetwork.m4955n8(null);
                nileNetwork.m4911O7();
            }
            map.remove(str);
            ULogUtility.m16683s("MeetingManager", "[" + str + "] releaseNileNetwork OUT");
        }
    }

    /* renamed from: z */
    public static void m5633z(String str) {
        HashMap<String, MeetingStatus> map = f6208a;
        synchronized (map) {
            map.remove(str);
            m5625r(str, MeetingStatus.REMOVE_FROM_MAP);
            ULogUtility.m16670f("MeetingManager", "[" + str + "] removeMeetingStatus.");
        }
        HashMap<String, ArrayList<BreakoutRoom>> map2 = f6209b;
        synchronized (map2) {
            map2.remove(str);
        }
        HashMap<String, List<Integer>> map3 = f6210c;
        synchronized (map3) {
            map3.remove(str);
        }
        HashMap<String, Boolean> map4 = f6211d;
        synchronized (map4) {
            map4.remove(str);
        }
    }
}
