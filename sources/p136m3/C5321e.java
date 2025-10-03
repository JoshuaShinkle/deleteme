package p136m3;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.PowerManager;
import android.telephony.PhoneNumberUtils;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import com.cyberlink.clrtc.C1019i;
import com.cyberlink.clrtc.C1073o;
import com.cyberlink.clrtc.InterfaceC1081o7;
import com.cyberlink.clrtc.InterfaceC1113s;
import com.cyberlink.clrtc.NileError;
import com.cyberlink.clrtc.NileNetwork;
import com.cyberlink.meeting.clrtc.MeetingManager;
import com.cyberlink.meeting.doserver.C1260a;
import com.cyberlink.meeting.model.Meeting;
import com.cyberlink.meeting.model.SubscriptionInfo;
import com.cyberlink.meeting.page.RemindMeetingActivity;
import com.cyberlink.meeting.page.p032m.MeetingActivity;
import com.cyberlink.p030U.R;
import com.cyberlink.you.Globals;
import com.cyberlink.you.bulletin.TopicCommentObj;
import com.cyberlink.you.chat.C2898i;
import com.cyberlink.you.chat.C2904l;
import com.cyberlink.you.chat.C2907m0;
import com.cyberlink.you.chat.C2925v;
import com.cyberlink.you.chat.NotificationHelper;
import com.cyberlink.you.chat.XMPPManager;
import com.cyberlink.you.chat.p034e.C2889b;
import com.cyberlink.you.database.ArchiveMessageObj$Type;
import com.cyberlink.you.database.C2950b0;
import com.cyberlink.you.database.C2973l0;
import com.cyberlink.you.database.GroupAlbumObj;
import com.cyberlink.you.database.MessageObj;
import com.cyberlink.you.database.PollOptionObj;
import com.cyberlink.you.database.TopicObj;
import com.cyberlink.you.feedback.PromisedTask;
import com.cyberlink.you.friends.C3061a;
import com.cyberlink.you.friends.C3062b;
import com.cyberlink.you.friends.Friend;
import com.cyberlink.you.friends.FriendsClient;
import com.cyberlink.you.friends.Group;
import com.cyberlink.you.utility.CLUtility;
import com.cyberlink.you.utility.ULogUtility;
import com.google.android.exoplayer2.source.smoothstreaming.SsMediaSource;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.gms.plus.PlusShare;
import com.google.firebase.messaging.Constants;
import com.perfectcorp.utility.C4507b;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import net.sqlcipher.database.SQLiteDatabase;
import org.apache.commons.lang3.CharUtils;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.p159io.IOUtils;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.util.C5616j;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import p003a2.C0012b;
import p116k4.C5154j;
import p116k4.C5170o0;
import p116k4.C5172p;
import p116k4.C5180s;
import p201t3.C6301o;
import p209u2.AbstractC6381r;
import p209u2.C6385v;
import p209u2.C6389z;
import p218v2.C6456d;
import p236x2.C6566a;

/* renamed from: m3.e */
/* loaded from: classes.dex */
public class C5321e {

    /* renamed from: e */
    public static final ExecutorService f18077e = Executors.newCachedThreadPool();

    /* renamed from: a */
    public final FriendsClient f18078a;

    /* renamed from: b */
    public PowerManager.WakeLock f18079b;

    /* renamed from: c */
    public final C6389z<m> f18080c;

    /* renamed from: d */
    public final HashSet<String> f18081d;

    /* renamed from: m3.e$a */
    public static /* synthetic */ class a {

        /* renamed from: a */
        public static final /* synthetic */ int[] f18082a;

        static {
            int[] iArr = new int[NileError.values().length];
            f18082a = iArr;
            try {
                iArr[NileError.CALL_PREJOIN_FAILED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f18082a[NileError.CALL_CONNECTION_BROKEN.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f18082a[NileError.CALL_PREJOIN_TIMEOUT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    /* renamed from: m3.e$b */
    public class b implements C3062b.b {
        public b() {
        }

        @Override // com.cyberlink.you.friends.C3062b.b
        /* renamed from: a */
        public void mo9343a() {
            Log.d("XMPPEventManager", "[handleMediaCreateEvent] Do syncAllMedia fail");
        }

        @Override // com.cyberlink.you.friends.C3062b.b
        /* renamed from: b */
        public void mo9344b(int i9, int i10) {
        }
    }

    /* renamed from: m3.e$c */
    public class c extends C6389z.a<m> {

        /* renamed from: a */
        public final /* synthetic */ C2904l f18084a;

        /* renamed from: b */
        public final /* synthetic */ Map f18085b;

        public c(C2904l c2904l, Map map) {
            this.f18084a = c2904l;
            this.f18085b = map;
        }

        @Override // p209u2.C6389z.a
        /* renamed from: a */
        public void mo5634a(String str, Throwable th) {
            C5154j.m20076j(th);
        }

        @Override // p209u2.C6389z.a
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public void mo5635b(m mVar) {
            mVar.mo8241A0(this.f18084a, this.f18085b);
        }
    }

    /* renamed from: m3.e$d */
    public class d extends FriendsClient.AbstractC3053k {

        /* renamed from: a */
        public final /* synthetic */ String f18087a;

        /* renamed from: b */
        public final /* synthetic */ C2904l f18088b;

        public d(String str, C2904l c2904l) {
            this.f18087a = str;
            this.f18088b = c2904l;
        }

        @Override // com.cyberlink.you.friends.FriendsClient.AbstractC3053k
        /* renamed from: a */
        public void mo12361a(Friend friend) {
            Map<String, String> mapM20828w0;
            Globals.m7388i0().m7629v2(this.f18087a);
            C2904l c2904l = this.f18088b;
            if (c2904l == null || (mapM20828w0 = C5321e.m20828w0(c2904l)) == null) {
                return;
            }
            C5321e.m20824o().m20896x0(this.f18088b, mapM20828w0);
        }

        @Override // com.cyberlink.you.friends.FriendsClient.AbstractC3053k
        /* renamed from: b */
        public void mo12362b(String str, String str2) throws JSONException {
            ULogUtility.m16664G("getUserInfo failed: " + str + ", " + str2, "XMPPEventManager");
            Globals.m7388i0().m7560j(this.f18087a);
        }
    }

    /* renamed from: m3.e$e */
    public class e extends AbstractC6381r<Group, Void> {

        /* renamed from: c */
        public final /* synthetic */ String f18090c;

        /* renamed from: d */
        public final /* synthetic */ String f18091d;

        /* renamed from: e */
        public final /* synthetic */ String f18092e;

        /* renamed from: f */
        public final /* synthetic */ Friend f18093f;

        /* renamed from: g */
        public final /* synthetic */ String f18094g;

        /* renamed from: h */
        public final /* synthetic */ String f18095h;

        /* renamed from: i */
        public final /* synthetic */ String f18096i;

        /* renamed from: j */
        public final /* synthetic */ boolean f18097j;

        /* renamed from: k */
        public final /* synthetic */ boolean f18098k;

        /* renamed from: l */
        public final /* synthetic */ String f18099l;

        /* renamed from: m */
        public final /* synthetic */ String f18100m;

        public e(String str, String str2, String str3, Friend friend, String str4, String str5, String str6, boolean z8, boolean z9, String str7, String str8) {
            this.f18090c = str;
            this.f18091d = str2;
            this.f18092e = str3;
            this.f18093f = friend;
            this.f18094g = str4;
            this.f18095h = str5;
            this.f18096i = str6;
            this.f18097j = z8;
            this.f18098k = z9;
            this.f18099l = str7;
            this.f18100m = str8;
        }

        @Override // p209u2.AbstractC6381r
        /* renamed from: k, reason: merged with bridge method [inline-methods] */
        public void m24503g(Group group) {
            C5321e.this.m20900z0(this.f18090c, this.f18091d, group, this.f18092e, this.f18093f, this.f18094g, this.f18095h, this.f18096i, this.f18097j, this.f18098k, this.f18099l, this.f18100m);
        }

        @Override // p209u2.AbstractC6381r
        /* renamed from: l, reason: merged with bridge method [inline-methods] */
        public void m24504h(Void r22) {
            ULogUtility.m16676l("MeetingManager", "[" + this.f18092e + "] handleMeetingInvite create group fail");
        }
    }

    /* renamed from: m3.e$f */
    public class f extends PromisedTask.AbstractC3021b<Boolean> {

        /* renamed from: j */
        public final /* synthetic */ String f18102j;

        public f(String str) {
            this.f18102j = str;
        }

        @Override // com.cyberlink.you.feedback.PromisedTask
        /* renamed from: k */
        public void mo5702k(int i9, String str) {
            ULogUtility.m16676l("MeetingManager", "[" + this.f18102j + "] sendCalleeIsBusy failed, error code = " + i9 + " | errorBody = " + str);
        }

        @Override // com.cyberlink.you.feedback.PromisedTask.AbstractC3021b
        /* renamed from: r, reason: merged with bridge method [inline-methods] */
        public void mo5703q(Boolean bool) {
            if (bool.booleanValue()) {
                return;
            }
            ULogUtility.m16676l("MeetingManager", "[" + this.f18102j + "] sendCalleeIsBusy failed.");
        }
    }

    /* renamed from: m3.e$g */
    public class g extends PromisedTask.AbstractC3021b<Meeting> {

        /* renamed from: j */
        public final /* synthetic */ Group f18104j;

        /* renamed from: k */
        public final /* synthetic */ String f18105k;

        /* renamed from: l */
        public final /* synthetic */ Friend f18106l;

        /* renamed from: m */
        public final /* synthetic */ String f18107m;

        /* renamed from: n */
        public final /* synthetic */ boolean f18108n;

        /* renamed from: o */
        public final /* synthetic */ boolean f18109o;

        /* renamed from: p */
        public final /* synthetic */ String f18110p;

        /* renamed from: q */
        public final /* synthetic */ String f18111q;

        /* renamed from: r */
        public final /* synthetic */ String f18112r;

        /* renamed from: s */
        public final /* synthetic */ String f18113s;

        public g(Group group, String str, Friend friend, String str2, boolean z8, boolean z9, String str3, String str4, String str5, String str6) {
            this.f18104j = group;
            this.f18105k = str;
            this.f18106l = friend;
            this.f18107m = str2;
            this.f18108n = z8;
            this.f18109o = z9;
            this.f18110p = str3;
            this.f18111q = str4;
            this.f18112r = str5;
            this.f18113s = str6;
        }

        @Override // com.cyberlink.you.feedback.PromisedTask
        /* renamed from: k */
        public void mo5702k(int i9, String str) {
            if (C1260a.m5671h(i9, str) == R.string.clm_error_pwd) {
                ULogUtility.m16680p("MeetingManager", "This meeting had password and invite event had no MServerAddress or MServerToke, use old flow to handle invite.");
                MeetingManager.m5602B(this.f18105k);
                C5321e.this.m20834C0(this.f18104j, this.f18105k, this.f18106l, this.f18107m, this.f18112r, this.f18113s, false, false, this.f18109o, this.f18110p, this.f18111q);
            } else {
                ULogUtility.m16676l("MeetingManager", "request Do Server to get MServer address and token failed");
                MeetingManager.m5631x(this.f18105k);
                if (C5321e.this.f18079b.isHeld()) {
                    ULogUtility.m16680p("WakeLock", "release wakeLock - query meeting info fail.");
                    C5321e.this.f18079b.release();
                }
            }
        }

        @Override // com.cyberlink.you.feedback.PromisedTask.AbstractC3021b
        /* renamed from: r, reason: merged with bridge method [inline-methods] */
        public void mo5703q(Meeting meeting) {
            C5321e.this.m20898y0(this.f18104j, this.f18105k, this.f18106l, this.f18107m, meeting.mserverAddr, meeting.token, this.f18108n, this.f18109o, this.f18110p, this.f18111q);
        }
    }

    /* renamed from: m3.e$h */
    public class h implements InterfaceC1081o7 {

        /* renamed from: b */
        public final /* synthetic */ String f18115b;

        /* renamed from: c */
        public final /* synthetic */ Group f18116c;

        /* renamed from: d */
        public final /* synthetic */ Friend f18117d;

        /* renamed from: e */
        public final /* synthetic */ String f18118e;

        /* renamed from: f */
        public final /* synthetic */ String f18119f;

        /* renamed from: g */
        public final /* synthetic */ String f18120g;

        /* renamed from: h */
        public final /* synthetic */ boolean f18121h;

        /* renamed from: i */
        public final /* synthetic */ String f18122i;

        /* renamed from: j */
        public final /* synthetic */ String f18123j;

        /* renamed from: k */
        public final /* synthetic */ Runnable f18124k;

        public h(String str, Group group, Friend friend, String str2, String str3, String str4, boolean z8, String str5, String str6, Runnable runnable) {
            this.f18115b = str;
            this.f18116c = group;
            this.f18117d = friend;
            this.f18118e = str2;
            this.f18119f = str3;
            this.f18120g = str4;
            this.f18121h = z8;
            this.f18122i = str5;
            this.f18123j = str6;
            this.f18124k = runnable;
        }

        @Override // com.cyberlink.clrtc.InterfaceC1081o7
        /* renamed from: I */
        public void mo5041I() {
            ULogUtility.m16680p("MeetingManager", "[" + this.f18115b + "] preJoinMeeting - onCalleeAnsweredFromAnotherDevice");
            NotificationHelper.m14096l();
            this.f18124k.run();
        }

        @Override // com.cyberlink.clrtc.InterfaceC1081o7
        /* renamed from: T */
        public void mo5042T() {
            ULogUtility.m16680p("MeetingManager", "[" + this.f18115b + "] preJoinMeeting - onCalleeRejectFromAnotherDevice");
            NotificationHelper.m14096l();
            this.f18124k.run();
        }

        @Override // com.cyberlink.clrtc.InterfaceC1081o7
        /* renamed from: V */
        public void mo5043V() {
            ULogUtility.m16680p("MeetingManager", "[" + this.f18115b + "] preJoinMeeting - onCalleeBusyFromAnotherDevice");
            NotificationHelper.m14096l();
            this.f18124k.run();
        }

        @Override // com.cyberlink.clrtc.InterfaceC1081o7
        /* renamed from: b0 */
        public void mo5044b0() {
            ULogUtility.m16680p("MeetingManager", "[" + this.f18115b + "] preJoinMeeting - onCallerHangUp");
            NotificationHelper.m14096l();
            this.f18124k.run();
            if (this.f18121h) {
                String str = this.f18122i;
                if (!C5170o0.m20170e(this.f18123j)) {
                    str = this.f18122i + "(#" + this.f18123j + ")";
                }
                NotificationHelper.m14073Q(this.f18116c, str);
            }
        }

        @Override // com.cyberlink.clrtc.InterfaceC1081o7
        /* renamed from: j */
        public void mo5045j() {
        }

        @Override // com.cyberlink.clrtc.InterfaceC1081o7
        /* renamed from: r0 */
        public void mo5046r0(C0012b c0012b, boolean z8, boolean z9) {
            ULogUtility.m16680p("MeetingManager", "[" + this.f18115b + "] preJoinMeeting - onPreJoinCompleted, " + z8);
            MeetingManager.m5602B(this.f18115b);
            if (MeetingManager.m5615h(this.f18115b) != MeetingManager.MeetingStatus.IN_MEETING) {
                C5321e.this.m20834C0(this.f18116c, this.f18115b, this.f18117d, this.f18118e, this.f18119f, this.f18120g, true, z8, this.f18121h, this.f18122i, this.f18123j);
                return;
            }
            ULogUtility.m16676l("MeetingManager", "[" + this.f18115b + "] The meeting had start activity, not start activity again.");
        }
    }

    /* renamed from: m3.e$i */
    public class i implements InterfaceC1113s {

        /* renamed from: b */
        public final /* synthetic */ Runnable f18126b;

        /* renamed from: c */
        public final /* synthetic */ boolean f18127c;

        /* renamed from: d */
        public final /* synthetic */ Group f18128d;

        /* renamed from: e */
        public final /* synthetic */ String f18129e;

        /* renamed from: f */
        public final /* synthetic */ Friend f18130f;

        /* renamed from: g */
        public final /* synthetic */ String f18131g;

        /* renamed from: h */
        public final /* synthetic */ String f18132h;

        /* renamed from: i */
        public final /* synthetic */ String f18133i;

        /* renamed from: j */
        public final /* synthetic */ NileNetwork f18134j;

        /* renamed from: k */
        public final /* synthetic */ boolean f18135k;

        /* renamed from: m3.e$i$a */
        public class a extends PromisedTask.AbstractC3021b<Meeting> {
            public a() {
            }

            @Override // com.cyberlink.you.feedback.PromisedTask
            /* renamed from: k */
            public void mo5702k(int i9, String str) {
                i.this.m20907h(false, null, false);
            }

            @Override // com.cyberlink.you.feedback.PromisedTask.AbstractC3021b
            /* renamed from: r, reason: merged with bridge method [inline-methods] */
            public void mo5703q(Meeting meeting) {
                i.this.m20907h(true, meeting.creator.displayName, false);
            }
        }

        /* renamed from: m3.e$i$b */
        public class b extends AbstractC6381r<Map<Long, List<String>>, Void> {

            /* renamed from: c */
            public final /* synthetic */ List f18138c;

            public b(List list) {
                this.f18138c = list;
            }

            @Override // p209u2.AbstractC6381r
            /* renamed from: k, reason: merged with bridge method [inline-methods] */
            public void m24503g(Map<Long, List<String>> map) {
                ArrayList arrayList = new ArrayList();
                for (C0012b c0012b : this.f18138c) {
                    List<String> list = map.get(Long.valueOf(c0012b.f66b.f65d));
                    if (list == null) {
                        arrayList.add(c0012b);
                    } else {
                        i.this.f18134j.m4902I8(c0012b, list);
                    }
                }
                if (arrayList.isEmpty()) {
                    return;
                }
                i.this.f18134j.m4975x8(arrayList, false);
            }
        }

        public i(Runnable runnable, boolean z8, Group group, String str, Friend friend, String str2, String str3, String str4, NileNetwork nileNetwork, boolean z9) {
            this.f18126b = runnable;
            this.f18127c = z8;
            this.f18128d = group;
            this.f18129e = str;
            this.f18130f = friend;
            this.f18131g = str2;
            this.f18132h = str3;
            this.f18133i = str4;
            this.f18134j = nileNetwork;
            this.f18135k = z9;
        }

        @Override // com.cyberlink.clrtc.InterfaceC1113s
        /* renamed from: C0 */
        public void mo5103C0(boolean z8) {
            MeetingManager.m5629v(this.f18129e, z8);
        }

        @Override // com.cyberlink.clrtc.InterfaceC1113s
        public void OnBREventTime(int i9, int i10) {
            MeetingManager.m5627t(this.f18129e, i9, i10);
        }

        @Override // com.cyberlink.clrtc.InterfaceC1113s
        /* renamed from: Z */
        public void mo5118Z(List<C0012b> list) {
            HashSet hashSet = new HashSet();
            for (C0012b c0012b : list) {
                if (c0012b.f66b.f65d > 0 && c0012b.m85d() != null) {
                    hashSet.add(Long.valueOf(c0012b.f66b.f65d));
                }
            }
            if (hashSet.isEmpty()) {
                return;
            }
            C2889b.m14298h().m14310n(hashSet, new b(list));
        }

        @Override // com.cyberlink.clrtc.InterfaceC1113s
        /* renamed from: d */
        public void mo5121d() {
            ULogUtility.m16680p("MeetingManager", "[" + this.f18129e + "] preJoinMeeting - onCalleeTimeout");
            NotificationHelper.m14096l();
            this.f18126b.run();
        }

        @Override // com.cyberlink.clrtc.InterfaceC1113s
        /* renamed from: e0 */
        public void mo5124e0(NileNetwork.Status status, int i9) {
            ULogUtility.m16680p("MeetingManager", "[" + this.f18129e + "] onStatusChanged : " + status);
            if (status == NileNetwork.Status.REACH_LIMIT) {
                C1260a.m5680r(this.f18129e).m15439e(new a());
            }
        }

        /* renamed from: h */
        public final void m20907h(boolean z8, String str, boolean z9) {
            NotificationHelper.m14096l();
            this.f18126b.run();
            if (!this.f18127c) {
                C5321e.this.m20836D0(this.f18128d, this.f18129e, this.f18130f, this.f18131g, this.f18132h, this.f18133i, z8, str, this.f18134j.m4977z3(), z9, this.f18135k);
                return;
            }
            ULogUtility.m16683s("MeetingManager", "[" + this.f18129e + "] The meeting is handle by GCM, the meeting maybe is canceled or end | not show pre-join failed page");
        }

        @Override // com.cyberlink.clrtc.InterfaceC1113s
        /* renamed from: i0 */
        public void mo5129i0(List<Integer> list) {
            MeetingManager.m5628u(this.f18129e, list);
        }

        @Override // com.cyberlink.clrtc.InterfaceC1113s
        /* renamed from: l */
        public void mo5133l(boolean z8) {
            ULogUtility.m16680p("MeetingManager", "[" + this.f18129e + "] onOldApp");
            m20907h(false, null, true);
        }

        @Override // com.cyberlink.clrtc.InterfaceC1113s
        /* renamed from: o */
        public void mo5136o(List<Pair<String, String>> list) {
            MeetingManager.m5626s(this.f18129e, list);
        }

        @Override // com.cyberlink.clrtc.InterfaceC1113s
        /* renamed from: z */
        public void mo5150z(NileError nileError, int i9) {
            ULogUtility.m16680p("MeetingManager", "[" + this.f18129e + "] onCallError : " + nileError);
            int i10 = a.f18082a[nileError.ordinal()];
            if (i10 == 1 || i10 == 2 || i10 == 3) {
                m20907h(false, null, false);
            }
        }
    }

    /* renamed from: m3.e$j */
    public class j extends PromisedTask.AbstractC3021b<SubscriptionInfo> {
        public j() {
        }

        @Override // com.cyberlink.you.feedback.PromisedTask
        /* renamed from: k */
        public void mo5702k(int i9, String str) {
        }

        @Override // com.cyberlink.you.feedback.PromisedTask.AbstractC3021b
        /* renamed from: r, reason: merged with bridge method [inline-methods] */
        public void mo5703q(SubscriptionInfo subscriptionInfo) {
            Globals.m7388i0().m7476Q3("pro".equalsIgnoreCase(subscriptionInfo.plan));
            Globals.m7388i0().m7642x3(Integer.parseInt(subscriptionInfo.attendeeCapacity));
            Globals.m7388i0().m7647y3(Integer.parseInt(subscriptionInfo.maximumLength));
            Globals.m7388i0().m7646y2(subscriptionInfo.androidOnHoldProductIds);
        }
    }

    /* renamed from: m3.e$k */
    public class k implements Runnable {

        /* renamed from: b */
        public final JSONArray f18141b = new JSONArray();

        /* renamed from: c */
        public int f18142c = 1;

        public k() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: b */
        public /* synthetic */ void m20912b(String str, String str2, String str3, String str4) {
            if (!"200".equals(str3)) {
                Log.e("XMPPEventManager", "[QueryUserInviteListTask] fail statusCode=" + str3);
                return;
            }
            int iM16553k1 = CLUtility.m16553k1(str4);
            int iM16494U0 = CLUtility.m16494U0(str4);
            if (iM16553k1 <= 0 || iM16494U0 <= 0) {
                m20913c();
                return;
            }
            JSONArray jSONArrayM20196r = C5172p.m20196r(str4);
            if (jSONArrayM20196r != null) {
                this.f18141b.put(jSONArrayM20196r);
            }
            if (iM16553k1 == iM16494U0) {
                m20913c();
                return;
            }
            int iM16559m = CLUtility.m16559m(iM16553k1, 100);
            int i9 = this.f18142c;
            if (i9 < iM16559m) {
                this.f18142c = i9 + 1;
                run();
            }
        }

        /* renamed from: c */
        public final void m20913c() {
            if (C5321e.this.f18078a != null) {
                C5321e.this.f18078a.m15722a1(this.f18141b, FriendsClient.InvitationFriendType.SENT);
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            ArrayList arrayList = new ArrayList();
            arrayList.add(new C6301o("token", Globals.m7388i0().m7449L()));
            arrayList.add(new C6301o("pageIndex", String.valueOf(this.f18142c)));
            arrayList.add(new C6301o("pageSize", String.valueOf(100)));
            arrayList.add(new C6301o("inviteStatus", "Inviting"));
            arrayList.add(new C6301o("inviteStatus", "Declined"));
            C5321e.this.f18078a.m15734m("invite", C5321e.this.f18078a.m15718W(FriendsClient.InvitationFriendType.SENT), arrayList, new FriendsClient.InterfaceC3051i() { // from class: m3.f
                @Override // com.cyberlink.you.friends.FriendsClient.InterfaceC3051i
                /* renamed from: a */
                public final void mo134a(String str, String str2, String str3, String str4) {
                    this.f18145a.m20912b(str, str2, str3, str4);
                }
            });
        }
    }

    /* renamed from: m3.e$l */
    public static class l {

        /* renamed from: a */
        public static final C5321e f18144a = new C5321e(null);
    }

    /* renamed from: m3.e$m */
    public interface m extends C6389z.b {
        /* renamed from: A0 */
        boolean mo8241A0(C2904l c2904l, Map<String, String> map);
    }

    public /* synthetic */ C5321e(b bVar) {
        this();
    }

    /* renamed from: E0 */
    public static void m20812E0(Group group, C2904l c2904l, String str, String str2, int i9) {
        MessageObj.MemberStatus memberStatus;
        Log.d("XMPPEventManager", "storeGroupStatusMessageToDB start. eventType : " + str);
        String strM14446v = c2904l.m14446v();
        String strValueOf = String.valueOf(group.f13727n);
        Date dateM14422j = c2904l.m14422j();
        MessageObj.MessageType messageTypeM14389D = c2904l.m14389D();
        String str3 = group.f13717d;
        String str4 = (messageTypeM14389D.equals(MessageObj.MessageType.Photo) || messageTypeM14389D.equals(MessageObj.MessageType.PhotoNote) || messageTypeM14389D.equals(MessageObj.MessageType.Audio)) ? "3" : "0";
        str.hashCode();
        switch (str) {
            case "group.member.created":
                memberStatus = MessageObj.MemberStatus.MemberCreate;
                break;
            case "group.member.deleted":
                memberStatus = MessageObj.MemberStatus.MemberDeleted;
                break;
            case "group.member.leaved":
                memberStatus = MessageObj.MemberStatus.MemberLeave;
                break;
            case "media.album.updated":
                memberStatus = MessageObj.MemberStatus.AlbumUpdate;
                break;
            case "group.admin.created":
                memberStatus = MessageObj.MemberStatus.AdminCreate;
                break;
            case "group.admin.deleted":
                memberStatus = MessageObj.MemberStatus.AdminDeleted;
                break;
            case "group.display.name.updated":
                memberStatus = MessageObj.MemberStatus.DisplayNameUpdated;
                break;
            case "group.member.created.v2":
                memberStatus = MessageObj.MemberStatus.MemberCreateV2;
                break;
            case "media.album.deleted":
                memberStatus = MessageObj.MemberStatus.AlbumDelete;
                break;
            default:
                return;
        }
        C2950b0.m14916o().m15157B(new MessageObj(-1L, strM14446v, strValueOf, dateM14422j.getTime(), messageTypeM14389D, str2, 0, c2904l.m14430n(), str3, "0", MessageObj.TTLStatus.NO_TTL, -1L, -1, 0L, memberStatus, c2904l.m14395J(), c2904l.m14452z(), str4, "", "", "", c2904l.m14393H(), i9));
    }

    /* renamed from: J0 */
    public static void m20813J0(Group group) {
        ArrayList arrayList = new ArrayList();
        arrayList.add("LastRead");
        C2950b0.m14912k().m15063B(String.valueOf(group.f13727n), group, arrayList);
    }

    /* renamed from: m */
    public static Map<String, String> m20823m(String str, String str2) {
        HashMap map = new HashMap();
        map.put("eventType", "group.member.leaved");
        map.put("groupId", str);
        map.put("userId", str2);
        return map;
    }

    /* renamed from: o */
    public static C5321e m20824o() {
        return l.f18144a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: s0 */
    public /* synthetic */ void m20825s0() {
        C1260a.m5672i(Globals.m7388i0().m7506X()).m15439e(new j());
    }

    /* renamed from: t0 */
    public static /* synthetic */ void m20826t0(long j9) {
        C2950b0.m14913l().m15095f(j9);
        C2950b0.m14912k().m15072i(j9);
        C2950b0.m14916o().m15170i(j9);
        C2950b0.m14906e().m14980j(j9);
        C2950b0.m14905d().m14946i();
        C2950b0.m14921t().m15233n();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: u0 */
    public /* synthetic */ void m20827u0(String str) {
        MeetingManager.MeetingStatus meetingStatusM5615h = MeetingManager.m5615h(str);
        ULogUtility.m16680p("MeetingManager", "[" + str + "] resetMeeting status = " + meetingStatusM5615h);
        if (meetingStatusM5615h.equals(MeetingManager.MeetingStatus.PRE_JOIN)) {
            MeetingManager.m5631x(str);
        } else if (MeetingManager.m5615h(str).equals(MeetingManager.MeetingStatus.START_ACTIVITY)) {
            MeetingManager.m5602B(str);
            MeetingManager.m5630w(str, MeetingManager.MeetingStatus.HANG_UP);
        }
        if (this.f18079b.isHeld()) {
            ULogUtility.m16680p("WakeLock", "release wakeLock - resetPreJoinMeetingRunnable");
            this.f18079b.release();
        }
    }

    /* renamed from: w0 */
    public static Map<String, String> m20828w0(C2904l c2904l) {
        C2898i c2898i = (C2898i) c2904l.m14426l("event", "urn:xmpp:custom:event");
        if (c2898i == null) {
            return null;
        }
        Log.d("XMPPEventManager", String.format("event packet(%1$s):\n%2$s", String.valueOf(Globals.m7388i0().m7568k1()), c2904l.toString()));
        return c2898i.m14373e();
    }

    /* renamed from: A */
    public final boolean m20829A(String str) {
        Friend friendM15727f0;
        if (str == null || (friendM15727f0 = this.f18078a.m15727f0(str)) == null || C2950b0.m14899A().m15001A(str) == null) {
            return false;
        }
        C2950b0.m14899A().m15019k(friendM15727f0, true, true);
        return true;
    }

    /* renamed from: A0 */
    public void m20830A0(C2904l c2904l) {
        Map<String, String> mapM20828w0 = m20828w0(c2904l);
        if (mapM20828w0 == null) {
            return;
        }
        try {
            m20849L(c2904l, mapM20828w0);
        } catch (Exception e9) {
            Log.d("XMPPEventManager", Log.getStackTraceString(e9));
        }
    }

    /* renamed from: B */
    public final boolean m20831B(String str, String str2) {
        Friend friendM15727f0;
        if (str2 == null || (friendM15727f0 = this.f18078a.m15727f0(str2)) == null) {
            return false;
        }
        if (C6456d.m24714D().m24748G()) {
            f18077e.submit(new k());
        }
        if (str.equals("friend.friend.blocked")) {
            friendM15727f0.f13655m = true;
        } else if (str.equals("friend.friend.unblocked")) {
            friendM15727f0.f13655m = false;
        }
        if (C2950b0.m14899A().m15001A(str2) != null) {
            C2950b0.m14899A().m15019k(friendM15727f0, true, true);
        } else {
            if (C2950b0.m14899A().m15003C(str2) == null) {
                return false;
            }
            C2950b0.m14899A().m15019k(friendM15727f0, false, true);
        }
        return true;
    }

    /* renamed from: B0 */
    public void m20832B0(m mVar) {
        this.f18080c.m24541g(mVar);
    }

    /* renamed from: C */
    public final boolean m20833C(String str, String str2) {
        if (((str2 == null || !str.equals("invite.friend.created")) && !str.equals("invite.friend.canceled")) || !Globals.m7388i0().m7474Q1(str2) || !C6456d.m24714D().m24748G()) {
            return true;
        }
        f18077e.submit(new k());
        return true;
    }

    /* renamed from: C0 */
    public final void m20834C0(Group group, String str, Friend friend, String str2, String str3, String str4, boolean z8, boolean z9, boolean z10, String str5, String str6) {
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
        bundle.putBoolean("isSecure", z9);
        bundle.putBoolean("isFromPhoneLine", z10);
        bundle.putString("callNumber", str6);
        bundle.putString("callDisplayName", str5);
        bundle.putString("callUserId", String.valueOf(friend.f13645c));
        Intent intent = new Intent(applicationContext, (Class<?>) MeetingActivity.class);
        intent.putExtras(bundle);
        intent.addFlags(SQLiteDatabase.CREATE_IF_NECESSARY);
        if (z8 && MeetingManager.m5621n(MeetingManager.m5615h(str))) {
            MeetingManager.m5631x(str);
            ULogUtility.m16676l("XMPPEventManager", "[" + str + "] Meeting check when start meeting is failed, not start activity.");
            if (this.f18079b.isHeld()) {
                ULogUtility.m16680p("WakeLock", "release wakeLock - meeting status check failed when start meeting activity.");
                this.f18079b.release();
                return;
            }
            return;
        }
        MeetingManager.m5630w(str, MeetingManager.MeetingStatus.START_ACTIVITY);
        if (this.f18079b.isHeld()) {
            ULogUtility.m16680p("WakeLock", "wakeLock is already held - release before acquire");
            this.f18079b.release();
        }
        if (Globals.m7396z1()) {
            try {
                ULogUtility.m16680p("WakeLock", "acquire wakelock - acquire 3000 ms to start MeetingActivity");
                this.f18079b.acquire(3000L);
            } catch (Exception e9) {
                ULogUtility.m16676l("WakeLock", "wakeLock exception on startMeetingActivity | error:" + e9);
            }
        }
        NotificationHelper.m14098n();
        if (Build.VERSION.SDK_INT < 29 || !Globals.m7396z1()) {
            applicationContext.startActivity(intent);
        } else {
            NotificationHelper.m14071O(group, friend.m15621b(), applicationContext.getString(MimeTypes.BASE_TYPE_AUDIO.equals(str2) ? R.string.incoming_voice_call : R.string.incoming_video_call), intent, friend.f13647e);
        }
    }

    /* renamed from: D */
    public final boolean m20835D(String str, Map<String, String> map, String str2, String str3, String str4, C2904l c2904l) throws NumberFormatException {
        Group groupM15077n;
        Object obj;
        Object obj2;
        boolean z8;
        int i9;
        C2904l c2904l2;
        boolean z9;
        if (str2 == null) {
            return false;
        }
        try {
            long j9 = Long.parseLong(str2);
            String strM14428m = c2904l.m14428m();
            long time = c2904l.m14386A().getTime();
            if (str.equals("group.member.leaved") || str.equals("group.member.deleted")) {
                if (str3 != null && Globals.m7388i0().m7474Q1(str3)) {
                    if (FriendsClient.m15659Y(j9).isEmpty()) {
                        m20893v0(C2950b0.m14912k().m15077n(str2), j9);
                        return true;
                    }
                    ULogUtility.m16664G("No need to leave group: " + str2, "XMPPEventManager");
                    return true;
                }
                if (str3 != null && !Globals.m7388i0().m7474Q1(str3)) {
                    C2950b0.m14913l().m15094e(Long.valueOf(j9), Long.valueOf(Long.parseLong(str3)));
                }
            } else if (str.equals("group.admin.deleted")) {
                String str5 = map.get("adminId");
                str3 = str5;
                C2950b0.m14910i().m15042e(Long.valueOf(j9), Long.valueOf(Long.parseLong(str5)));
            }
            String str6 = str3;
            if ("group.member.created".equals(str)) {
                groupM15077n = C2950b0.m14912k().m15077n(str2);
            } else {
                groupM15077n = FriendsClient.m15650P(str2);
                if (groupM15077n == null && c2904l.m14399N()) {
                    groupM15077n = C2950b0.m14912k().m15077n(str2);
                }
            }
            if (groupM15077n == null) {
                return false;
            }
            if (groupM15077n.f13711J && ("group.member.leaved".equals(str) || "group.member.created".equals(str) || "group.member.created.v2".equals(str) || "group.member.deleted".equals(str) || "group.admin.created".equals(str) || "group.admin.deleted".equals(str))) {
                C2889b.m14298h().m14312p(j9, time);
            }
            C2950b0.m14912k().m15070g(groupM15077n, true);
            if (str.equals("group.display.name.updated")) {
                String str7 = map.get("displayName");
                String strM22347l = C5616j.m22347l(strM14428m);
                if (strM22347l == null) {
                    return false;
                }
                Friend friendM15003C = C2950b0.m14899A().m15003C(strM22347l);
                if (friendM15003C == null && (friendM15003C = this.f18078a.m15727f0(strM22347l)) == null) {
                    return false;
                }
                groupM15077n.m15752j(friendM15003C.m15621b());
                obj = "group.member.deleted";
                m20812E0(groupM15077n, c2904l, str, str7, 0);
            } else {
                obj = "group.member.deleted";
            }
            if (str.equals("group.member.created")) {
                if (str6 == null || !Globals.m7388i0().m7474Q1(str6) || c2904l.m14386A() == null || groupM15077n.m15748d() >= c2904l.m14386A().getTime()) {
                    obj2 = "group.admin.deleted";
                } else {
                    obj2 = "group.admin.deleted";
                    groupM15077n.m15753k(c2904l.m14386A().getTime());
                    m20813J0(groupM15077n);
                }
                C2950b0.m14913l().m15101l(Long.valueOf(j9), Long.valueOf(Long.parseLong(str6)));
                if (groupM15077n.f13716c.equals("Dual")) {
                    return true;
                }
            } else {
                obj2 = "group.admin.deleted";
            }
            if (str.equals("group.member.leaved") || str.equals("group.member.created") || str.equals("group.member.created.v2") || str.equals(obj)) {
                Friend friendM15003C2 = C2950b0.m14899A().m15003C(str6);
                if (friendM15003C2 == null) {
                    friendM15003C2 = this.f18078a.m15727f0(str6);
                    if (friendM15003C2 == null) {
                        return false;
                    }
                    z8 = false;
                    C2950b0.m14899A().m15018j(friendM15003C2, false);
                } else {
                    z8 = false;
                }
                String strM15621b = friendM15003C2.m15621b();
                String strM22347l2 = C5616j.m22347l(strM14428m);
                if (strM22347l2 == null) {
                    return z8;
                }
                Friend friendM15003C3 = C2950b0.m14899A().m15003C(strM22347l2);
                if (friendM15003C3 == null && (friendM15003C3 = this.f18078a.m15727f0(strM22347l2)) == null) {
                    return z8;
                }
                if (str.equals("group.member.created.v2")) {
                    i9 = Integer.parseInt(map.get("totalSize"));
                    Log.d("XMPPEventManager", "[newGIEvent] handleGroupUpdate - in / totalSize: " + i9);
                } else {
                    i9 = 0;
                }
                groupM15077n.m15752j(friendM15003C3.m15621b());
                c2904l2 = c2904l;
                m20812E0(groupM15077n, c2904l2, str, strM15621b, i9);
            } else {
                c2904l2 = c2904l;
                i9 = 0;
            }
            if (str.equals("group.admin.created") || str.equals(obj2)) {
                String str8 = map.get("adminId");
                Friend friendM15003C4 = C2950b0.m14899A().m15003C(str8);
                if (friendM15003C4 == null) {
                    friendM15003C4 = this.f18078a.m15727f0(str8);
                    if (friendM15003C4 == null) {
                        return false;
                    }
                    z9 = false;
                    C2950b0.m14899A().m15018j(friendM15003C4, false);
                } else {
                    z9 = false;
                }
                String strM22347l3 = C5616j.m22347l(strM14428m);
                if (strM22347l3 == null) {
                    return z9;
                }
                Friend friendM15003C5 = C2950b0.m14899A().m15003C(strM22347l3);
                if (friendM15003C5 == null && (friendM15003C5 = this.f18078a.m15727f0(strM22347l3)) == null) {
                    return z9;
                }
                if (str.equals("group.admin.created")) {
                    C2950b0.m14910i().m15047j(Long.valueOf(j9), Long.valueOf(Long.parseLong(str8)));
                }
                groupM15077n.m15752j(friendM15003C5.m15621b());
                m20812E0(groupM15077n, c2904l2, str, friendM15003C4.m15621b(), i9);
            }
            if (str.equals("group.notification.changed.event")) {
                groupM15077n.f13733t = Boolean.valueOf(map.get("isDisabled")).booleanValue();
                C2950b0.m14912k().m15089z(str2, groupM15077n);
            }
            if (str.equals("group.reminder.set.event")) {
                groupM15077n.f13714M = true;
                C2950b0.m14912k().m15062A(String.valueOf(groupM15077n.f13727n), groupM15077n, "setAsReminder");
                return true;
            }
            if (!str.equals("group.reminder.unset.event")) {
                return true;
            }
            groupM15077n.f13714M = false;
            C2950b0.m14912k().m15062A(String.valueOf(groupM15077n.f13727n), groupM15077n, "setAsReminder");
            return true;
        } catch (NumberFormatException e9) {
            C5154j.m20076j(e9);
            return false;
        }
    }

    /* renamed from: D0 */
    public final void m20836D0(Group group, String str, Friend friend, String str2, String str3, String str4, boolean z8, String str5, int i9, boolean z9, boolean z10) {
        ULogUtility.m16680p("MeetingManager", "[" + str + "] start preJoinFailedActivity.");
        if (Globals.m7396z1()) {
            try {
                if (this.f18079b.isHeld()) {
                    ULogUtility.m16680p("WakeLock", "wakeLock is already held - release before acquire");
                    this.f18079b.release();
                }
                ULogUtility.m16680p("WakeLock", "acquire wakelock - acquire 3000 ms to start MeetingActivity");
                this.f18079b.acquire(3000L);
            } catch (Exception e9) {
                ULogUtility.m16676l("WakeLock", "wakeLock exception on startMeetingActivity | error:" + e9);
            }
        }
        MeetingManager.m5606F(group, str, friend, str2, str3, str4, true, z8, str5, i9, z9);
    }

    /* renamed from: E */
    public final boolean m20837E(Map<String, String> map) {
        String str = map.get("notificationType");
        if ("SUBSCRIPTION_ON_HOLD".equals(str)) {
            Globals.m7388i0().m7646y2(Collections.singletonList(map.get("productId")));
            Globals.m7388i0().m7476Q3(false);
        } else if ("SUBSCRIPTION_RECOVERED".equals(str)) {
            Globals.m7388i0().m7646y2(new ArrayList());
            Globals.m7388i0().m7476Q3(true);
        } else if ("SUBSCRIPTION_EXPIRED".equals(str)) {
            m20877l();
        }
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:38:0x008e  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0091  */
    /* renamed from: F */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final boolean m20838F(Map<String, String> map) {
        int iIntValue;
        File file;
        File file2;
        String str = map.get("modules");
        if (str == null || str.isEmpty()) {
            return false;
        }
        try {
            iIntValue = Integer.valueOf(map.get("days")).intValue();
        } catch (NumberFormatException unused) {
            iIntValue = 0;
        }
        if (iIntValue <= 0) {
            return false;
        }
        for (String str2 : str.split(",")) {
            str2.hashCode();
            switch (str2) {
                case "M":
                    file = new File(CLUtility.m16502X(), "CLRTC");
                    file2 = file;
                    StringBuilder sb = new StringBuilder();
                    sb.append(Globals.m7380c2() ? "B" : "P");
                    sb.append(str2);
                    C1073o.m5038t(Globals.m7372O(), file2, Globals.m7388i0().m7568k1().longValue(), sb.toString(), iIntValue);
                    break;
                case "U":
                    file = ULogUtility.m16678n(ULogUtility.LogFileType.Log, true);
                    file2 = file;
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append(Globals.m7380c2() ? "B" : "P");
                    sb2.append(str2);
                    C1073o.m5038t(Globals.m7372O(), file2, Globals.m7388i0().m7568k1().longValue(), sb2.toString(), iIntValue);
                    break;
                case "W":
                    file2 = new File(C4507b.m18107h(true));
                    StringBuilder sb22 = new StringBuilder();
                    sb22.append(Globals.m7380c2() ? "B" : "P");
                    sb22.append(str2);
                    C1073o.m5038t(Globals.m7372O(), file2, Globals.m7388i0().m7568k1().longValue(), sb22.toString(), iIntValue);
                    break;
            }
        }
        return true;
    }

    /* renamed from: F0 */
    public final TopicCommentObj m20839F0(Map<String, String> map) {
        JSONArray jSONArrayM20196r;
        String str = map.get("postId");
        String str2 = map.get("groupId");
        ArrayList arrayList = new ArrayList();
        arrayList.add(new C6301o("token", Globals.m7388i0().m7449L()));
        arrayList.add(new C6301o("postId", str));
        Pair<String, String> pairM15731j = this.f18078a.m15731j("groupbulletin", "queryPost", arrayList);
        String str3 = (String) pairM15731j.first;
        String str4 = (String) pairM15731j.second;
        TopicCommentObj topicCommentObjM20182d = null;
        if ("200".equals(str3) && str2 != null && (jSONArrayM20196r = C5172p.m20196r(str4)) != null) {
            for (int i9 = 0; i9 < jSONArrayM20196r.length(); i9++) {
                try {
                    topicCommentObjM20182d = C5172p.m20182d(jSONArrayM20196r.getJSONObject(i9));
                    if (topicCommentObjM20182d != null) {
                        C2950b0.m14905d().m14947j(topicCommentObjM20182d, TopicCommentObj.m14027m());
                    }
                } catch (JSONException e9) {
                    e9.printStackTrace();
                }
            }
        }
        return topicCommentObjM20182d;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:13:0x00b2  */
    /* renamed from: G */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final boolean m20840G(Map<String, String> map) {
        String str = map.get("mediaId");
        String str2 = map.get("commentId");
        ArrayList arrayList = new ArrayList();
        arrayList.add(new C6301o("token", Globals.m7388i0().m7449L()));
        arrayList.add(new C6301o("mediaCommentId", str2));
        Pair<String, String> pairM15731j = this.f18078a.m15731j("media", "mediaCommentInfo", arrayList);
        String str3 = (String) pairM15731j.first;
        char c9 = 0;
        if (!"200".equals(str3)) {
            Log.d("XMPPEventManager", "[queryCommentInfoTask] status code is '" + str3 + ";");
            return false;
        }
        C3062b.m15816p(str, (String) pairM15731j.second);
        C2973l0 c2973l0M14725v = C2950b0.m14914m().m14725v(Long.valueOf(str).longValue());
        C3061a c3061aM15223l = C2950b0.m14920s().m15223l(Long.valueOf(str2).longValue());
        String str4 = map.get("commentCount");
        if (str4 != null) {
            c2973l0M14725v.m15123J(Integer.valueOf(str4).intValue());
        }
        if (c3061aM15223l != null) {
            String strM15784f = c3061aM15223l.m15784f();
            strM15784f.hashCode();
            switch (strM15784f.hashCode()) {
                case -1083944904:
                    if (!strM15784f.equals("CommentDoodle")) {
                        c9 = 65535;
                        break;
                    }
                    break;
                case -523565044:
                    if (strM15784f.equals("CommentText")) {
                        c9 = 1;
                        break;
                    }
                    break;
                case 942868709:
                    if (strM15784f.equals("CommentMedia")) {
                        c9 = 2;
                        break;
                    }
                    break;
            }
            switch (c9) {
                case 0:
                    c2973l0M14725v.m15115B(c2973l0M14725v.m15133e() + 1);
                    break;
                case 1:
                    c2973l0M14725v.m15117D(c2973l0M14725v.m15135g() + 1);
                    break;
                case 2:
                    c2973l0M14725v.m15116C(c2973l0M14725v.m15134f() + 1);
                    break;
            }
        }
        C2950b0.m14914m().m14712i(c2973l0M14725v);
        return true;
    }

    /* renamed from: G0 */
    public final TopicObj m20841G0(Map<String, String> map) {
        String str = map.get("topicId");
        return this.f18078a.m15726d0(map.get("groupId"), str);
    }

    /* renamed from: H */
    public final boolean m20842H(String str, String str2, String str3, String str4, String str5, C2904l c2904l, String str6, String str7) throws NumberFormatException {
        String strM20867d;
        boolean z8;
        String str8;
        if (str2 == null || str4 == null) {
            return false;
        }
        long j9 = !C5170o0.m20170e(str7) ? (Long.parseLong(str7) / 1000) * 1000 : -1L;
        Message.Type typeM14448w = c2904l.m14448w();
        Group groupM15077n = C2950b0.m14912k().m15077n(str2);
        if (groupM15077n == null) {
            groupM15077n = FriendsClient.m15650P(str2);
            if (groupM15077n == null) {
                return false;
            }
            C2950b0.m14912k().m15070g(groupM15077n, true);
        }
        boolean zEquals = c2904l.m14430n().equals(String.valueOf(Globals.m7388i0().m7568k1()));
        if (str.equals("media.album.deleted") && zEquals) {
            groupM15077n.m15752j(m20883p(str5, typeM14448w));
            strM20867d = str6;
            m20812E0(groupM15077n, c2904l, str, strM20867d, 0);
            z8 = true;
        } else {
            strM20867d = str6;
            z8 = false;
        }
        GroupAlbumObj groupAlbumObjM15056i = C2950b0.m14911j().m15056i(str4);
        if (groupAlbumObjM15056i == null) {
            groupAlbumObjM15056i = this.f18078a.m15708O(str4, str2);
            if (groupAlbumObjM15056i == null || groupAlbumObjM15056i.m14677d().equals("GroupCover")) {
                return false;
            }
            C2950b0.m14911j().m15053f(groupAlbumObjM15056i);
            str8 = (groupAlbumObjM15056i.m14677d().equals("Chat") && groupAlbumObjM15056i.m14676c().equals(groupM15077n.f13718e)) ? groupM15077n.f13718e : str4;
            C3062b.m15824x(str8, new b());
        } else {
            str8 = str4;
        }
        if (str3 != null) {
            if (str.equals("media.media.deleted")) {
                C2950b0.m14914m().m14716m(str3);
                return true;
            }
            if (groupAlbumObjM15056i.m14677d().equals("Chat") && groupAlbumObjM15056i.m14676c().equals(groupM15077n.f13718e)) {
                str8 = groupM15077n.f13718e;
            }
            if ("media.media.created".equals(str)) {
                if (j9 == -1) {
                    return true;
                }
                C2950b0.m14914m().m14702L(new C2973l0(-1L, str8, Long.parseLong(str3), "", "", "", j9, "", new C2973l0.a(), new C2973l0.a(), 0, 0, 0, 0, 0, -1, 0L), false);
                return true;
            }
            long j10 = Long.parseLong(str3);
            C2973l0 c2973l0M14725v = C2950b0.m14914m().m14725v(j10);
            if (c2973l0M14725v == null) {
                c2973l0M14725v = (C2973l0) FriendsClient.m15657X(str8, j10).first;
            }
            if (c2973l0M14725v == null) {
                return false;
            }
            if (str.equals("media.comment.created") || str.equals("media.album.created") || str.equals("media.comment.updated")) {
                return true;
            }
            groupM15077n.m15752j(m20880n(c2973l0M14725v.m15136h()));
        } else if (str.equals("media.album.deleted")) {
            C2950b0.m14911j().m15054g(str8);
            groupM15077n.m15752j(m20883p(str5, typeM14448w));
        }
        if (!str.equals("media.album.deleted")) {
            strM20867d = m20867d(str8, str3);
        } else if (str3 != null) {
            C2950b0.m14914m().m14716m(str3);
        }
        if (z8) {
            return true;
        }
        m20812E0(groupM15077n, c2904l, str, strM20867d, 0);
        return true;
    }

    /* renamed from: H0 */
    public final void m20843H0(TopicObj topicObj) {
        TopicObj topicObjM14984n = C2950b0.m14906e().m14984n(topicObj.m14849o());
        int iM14850p = topicObjM14984n != null ? topicObjM14984n.m14850p() : 0;
        if (topicObj.m14854t()) {
            if (topicObj.m14858x()) {
                return;
            }
            topicObj.m14834F(-1);
            C2950b0.m14906e().m14989s(topicObj.m14849o(), topicObj, "Unread");
            return;
        }
        if (iM14850p == -1) {
            topicObj.m14834F(1);
        } else {
            topicObj.m14834F(iM14850p + 1);
        }
        C2950b0.m14906e().m14989s(topicObj.m14849o(), topicObj, "Unread");
    }

    /* renamed from: I */
    public final boolean m20844I(Map<String, String> map) {
        NotificationHelper.m14096l();
        String str = map.get("callId");
        ULogUtility.m16683s("MeetingManager", "[" + str + "] handleMeetingHangupEvent.");
        MeetingManager.MeetingStatus meetingStatusM5615h = MeetingManager.m5615h(str);
        if (meetingStatusM5615h == MeetingManager.MeetingStatus.PRE_JOIN) {
            MeetingManager.m5631x(str);
        } else if (meetingStatusM5615h == MeetingManager.MeetingStatus.START_ACTIVITY) {
            MeetingManager.m5602B(str);
            MeetingManager.m5630w(str, MeetingManager.MeetingStatus.HANG_UP);
        }
        if (meetingStatusM5615h == MeetingManager.MeetingStatus.UNKNOWN || !this.f18079b.isHeld()) {
            return false;
        }
        ULogUtility.m16680p("WakeLock", "release wakeLock - handleMeetingHangupEvent");
        this.f18079b.release();
        return false;
    }

    /* renamed from: I0 */
    public final void m20845I0(Map<String, String> map, TopicObj topicObj, TopicCommentObj topicCommentObj) {
        Group groupM15077n;
        if (topicObj == null && topicCommentObj == null) {
            return;
        }
        long jM14840e = topicObj != null ? topicObj.m14840e() : topicCommentObj.m14030e();
        String str = map.get("groupId");
        if (str == null || (groupM15077n = C2950b0.m14912k().m15077n(str)) == null) {
            return;
        }
        String str2 = groupM15077n.f13739z;
        if (str2 != null && !str2.isEmpty()) {
            try {
                if (jM14840e < new JSONObject(groupM15077n.f13739z).getLong("time")) {
                    return;
                }
            } catch (Exception e9) {
                e9.printStackTrace();
            }
        }
        if (topicObj != null) {
            groupM15077n.f13739z = C5180s.m20252e(Globals.m7388i0(), topicObj);
        } else {
            groupM15077n.f13739z = C5180s.m20249b(Globals.m7388i0(), topicCommentObj);
        }
        C2950b0.m14912k().m15062A(String.valueOf(groupM15077n.f13727n), groupM15077n, "LastMsg");
        CLUtility.m16585s1(groupM15077n, false);
    }

    /* renamed from: J */
    public void m20846J(String str, String str2, String str3, String str4, String str5, String str6, boolean z8, boolean z9, String str7, String str8) {
        String str9;
        if (Globals.m7396z1() && (!Globals.m7388i0().m7456M1() || !NotificationHelper.m14059C())) {
            ULogUtility.m16680p("XMPPEventManager", "[" + str3 + "] isIncomingCallNotificationEnable() : " + Globals.m7388i0().m7456M1());
            ULogUtility.m16680p("XMPPEventManager", "[" + str3 + "] isSystemNotificationEnable() : " + NotificationHelper.m14059C());
            StringBuilder sb = new StringBuilder();
            sb.append("[");
            sb.append(str3);
            sb.append("] handleMeetingInvite, notification is off");
            ULogUtility.m16680p("XMPPEventManager", sb.toString());
            return;
        }
        ULogUtility.m16680p("MeetingManager", "[" + str3 + "] handleMeetingInvite, dumpMeetingStatusMapInfo = " + MeetingManager.m5611d());
        if (str.equals(Globals.m7388i0().m7587o0())) {
            ULogUtility.m16676l("MeetingManager", "[" + str3 + "] handleMeetingInvite | not handle self invite");
            return;
        }
        Friend friendM15004D = C2950b0.m14899A().m15004D(str);
        if (!z9 && friendM15004D == null) {
            ULogUtility.m16683s("MeetingManager", "[" + str3 + "] handleMeetingInvite | callerInfo is not in db, query from cloud. callerJid = " + str);
            friendM15004D = new FriendsClient().m15730i0(str);
            if (friendM15004D == null) {
                ULogUtility.m16676l("MeetingManager", "[" + str3 + "] handleMeetingInvite | unknown caller, callerId = " + str);
                return;
            }
            C2950b0.m14899A().m15018j(friendM15004D, false);
        }
        Friend friend = friendM15004D;
        if (!str2.equals(Globals.m7388i0().m7587o0())) {
            Group groupM15081r = C2950b0.m14912k().m15081r(str2);
            if (groupM15081r != null) {
                m20900z0(str, str2, groupM15081r, str3, friend, str4, str5, str6, z8, z9, str7, str8);
                return;
            }
            ULogUtility.m16676l("MeetingManager", "[" + str3 + "] handleMeetingInvite | group is not exist jid : " + str2);
            return;
        }
        if (!z9) {
            this.f18078a.m15719Z(friend, new e(str, str2, str3, friend, str4, str5, str6, z8, z9, str7, str8));
            return;
        }
        Group group = new Group();
        if (C5170o0.m20170e(str8)) {
            str9 = str7;
        } else {
            str9 = str7 + "(#" + str8 + ")";
        }
        group.f13717d = str9;
        group.f13716c = "Dual";
        Friend friend2 = new Friend();
        if (friend != null) {
            friend2.f13645c = friend.f13645c;
            if (friend.f13658p) {
                group.f13724k = friend.f13647e;
            }
        }
        friend2.m15624e(str9);
        m20900z0(str, str2, group, str3, friend2, str4, str5, str6, z8, z9, str7, str8);
    }

    /* renamed from: K */
    public final boolean m20847K(C2904l c2904l, String str, Map<String, String> map) throws JSONException {
        String str2 = map.get(TtmlNode.ATTR_ID);
        ULogUtility.m16683s("MeetingManager", "[" + str2 + "] handleMeetingStartEndEvent | eventType = " + str);
        if (str.equals("meeting.meeting.end")) {
            MeetingManager.MeetingStatus meetingStatusM5615h = MeetingManager.m5615h(str2);
            if (meetingStatusM5615h == MeetingManager.MeetingStatus.PRE_JOIN) {
                MeetingManager.m5631x(str2);
            } else if (meetingStatusM5615h == MeetingManager.MeetingStatus.START_ACTIVITY) {
                MeetingManager.m5602B(str2);
                MeetingManager.m5630w(str2, MeetingManager.MeetingStatus.MEETING_END);
            }
            if (meetingStatusM5615h != MeetingManager.MeetingStatus.UNKNOWN && this.f18079b.isHeld()) {
                ULogUtility.m16680p("WakeLock", "release wakeLock - handleMeetingEndEvent");
                this.f18079b.release();
            }
        }
        String str3 = map.get("actor");
        Friend friendM15003C = C2950b0.m14899A().m15003C(str3);
        if (friendM15003C == null) {
            ULogUtility.m16680p("MeetingManager", "[" + str2 + "] handleMeetingStartEndEvent | actor is not in db, query from cloud. actorUserId = " + str3);
            friendM15003C = new FriendsClient().m15727f0(str3);
            if (friendM15003C == null) {
                ULogUtility.m16676l("MeetingManager", "[" + str2 + "] handleMeetingStartEndEvent | unknown actorUserId, actorUserId = " + str3);
                return false;
            }
            C2950b0.m14899A().m15018j(friendM15003C, false);
        }
        String strM14418h = c2904l.m14418h();
        Group groupM15081r = C2950b0.m14912k().m15081r(strM14418h);
        if (groupM15081r == null) {
            strM14418h = c2904l.m14388C();
            groupM15081r = C2950b0.m14912k().m15081r(strM14418h);
        }
        if (groupM15081r == null) {
            ULogUtility.m16676l("XMPPEventManager", "[" + str2 + "] handleMeetingStartEndEvent |  can not find group, groupJid = " + strM14418h);
            return false;
        }
        boolean zEquals = str3.equals(String.valueOf(Globals.m7388i0().m7568k1()));
        String strM7587o0 = zEquals ? Globals.m7388i0().m7587o0() : friendM15003C.f13648f;
        String strM7587o02 = zEquals ? groupM15081r.f13723j : Globals.m7388i0().m7587o0();
        String str4 = str.equals("meeting.meeting.end") ? TtmlNode.END : "meeting";
        String str5 = map.get("callType");
        boolean zM20170e = C5170o0.m20170e(str5);
        String str6 = MimeTypes.BASE_TYPE_AUDIO;
        if (!zM20170e && str5.equals("VIDEO")) {
            str6 = MimeTypes.BASE_TYPE_VIDEO;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("callerId", strM7587o0);
            jSONObject.put("calleeId", strM7587o02);
            jSONObject.put("callId", str2);
            jSONObject.put("statusV2", str4);
            jSONObject.put("duration", 0);
            jSONObject.put("callType", str6);
            jSONObject.put("startTime", map.get("startTime"));
            if (str.equals("meeting.meeting.end")) {
                jSONObject.put("endTime", map.get("endTime"));
            }
        } catch (JSONException e9) {
            e9.printStackTrace();
        }
        String string = jSONObject.toString();
        MessageObj.MessageType messageType = MessageObj.MessageType.Event;
        MessageObj messageObjM14449w0 = c2904l.m14449w0(String.valueOf(groupM15081r.f13727n));
        messageObjM14449w0.m14756R(messageType);
        messageObjM14449w0.m14757S(string);
        messageObjM14449w0.m14762X("0");
        messageObjM14449w0.m14758T(c2904l.m14446v());
        C2950b0.m14916o().m15157B(messageObjM14449w0);
        XMPPManager.m14184g0().m14268s1(groupM15081r, messageObjM14449w0);
        return true;
    }

    /* renamed from: K0 */
    public final void m20848K0(TopicObj topicObj, int i9) {
        Group groupM15077n = C2950b0.m14912k().m15077n(String.valueOf(topicObj.m14843h()));
        if (groupM15077n != null) {
            ArrayList arrayList = new ArrayList();
            arrayList.add("unreadPollsCount");
            groupM15077n.f13708G += i9;
            C2950b0.m14912k().m15063B(String.valueOf(groupM15077n.f13727n), groupM15077n, arrayList);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:288:0x047b  */
    /* renamed from: L */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final boolean m20849L(C2904l c2904l, Map<String, String> map) {
        char c9;
        String strM14428m = c2904l.m14428m();
        String strM14446v = c2904l.m14446v();
        long time = c2904l.m14386A().getTime();
        synchronized (m20824o()) {
            if (C2950b0.m14916o().m15179r(strM14446v) != null) {
                Log.d("XMPPEventManager", "Database(message) already has this record.");
                return false;
            }
            String str = map.get("eventType");
            switch (str.hashCode()) {
                case -2138412222:
                    if (!str.equals("user.status.updated")) {
                        c9 = 65535;
                        break;
                    } else {
                        c9 = 24;
                        break;
                    }
                case -2126782280:
                    if (str.equals("sticker.user.pack.updated")) {
                        c9 = 'W';
                        break;
                    }
                    break;
                case -1936932574:
                    if (str.equals("bulletin.topic.created")) {
                        c9 = '=';
                        break;
                    }
                    break;
                case -1868147997:
                    if (str.equals("group.member.created")) {
                        c9 = 11;
                        break;
                    }
                    break;
                case -1824655482:
                    if (str.equals("bulletin.post.liked")) {
                        c9 = 'C';
                        break;
                    }
                    break;
                case -1701679130:
                    if (str.equals("user.device.update.publickey")) {
                        c9 = 29;
                        break;
                    }
                    break;
                case -1681592280:
                    if (str.equals("group.reminder.unset.event")) {
                        c9 = 22;
                        break;
                    }
                    break;
                case -1645150902:
                    if (str.equals("group.group.created")) {
                        c9 = '\b';
                        break;
                    }
                    break;
                case -1605464499:
                    if (str.equals("bulletin.post.unliked")) {
                        c9 = 'D';
                        break;
                    }
                    break;
                case -1555824044:
                    if (str.equals("bulletin.post.updated")) {
                        c9 = 'P';
                        break;
                    }
                    break;
                case -1524614164:
                    if (str.equals("friend.friend.blocked")) {
                        c9 = 7;
                        break;
                    }
                    break;
                case -1490302318:
                    if (str.equals("meeting.reminder.notify")) {
                        c9 = ':';
                        break;
                    }
                    break;
                case -1415024045:
                    if (str.equals("bulletin.topic.deleted")) {
                        c9 = '>';
                        break;
                    }
                    break;
                case -1346239468:
                    if (str.equals("group.member.deleted")) {
                        c9 = CharUtils.f19105CR;
                        break;
                    }
                    break;
                case -1270911290:
                    if (str.equals("invite.friend.denied")) {
                        c9 = '+';
                        break;
                    }
                    break;
                case -1267404515:
                    if (str.equals("invite.friend.created")) {
                        c9 = ')';
                        break;
                    }
                    break;
                case -1223755950:
                    if (str.equals("system.force.init")) {
                        c9 = ClassUtils.PACKAGE_SEPARATOR_CHAR;
                        break;
                    }
                    break;
                case -1092279544:
                    if (str.equals("bulletin.poll.option.uncasted")) {
                        c9 = 'J';
                        break;
                    }
                    break;
                case -1065518524:
                    if (str.equals("group.group.hided")) {
                        c9 = 15;
                        break;
                    }
                    break;
                case -1061631694:
                    if (str.equals("group.member.leaved")) {
                        c9 = '\n';
                        break;
                    }
                    break;
                case -1012497142:
                    if (str.equals("dou.org.membership.changed")) {
                        c9 = 'Y';
                        break;
                    }
                    break;
                case -1009547174:
                    if (str.equals("group.group.disabled")) {
                        c9 = 14;
                        break;
                    }
                    break;
                case -975653893:
                    if (str.equals("bulletin.poll.option.comment.updated")) {
                        c9 = 'N';
                        break;
                    }
                    break;
                case -912614478:
                    if (str.equals("media.album.updated")) {
                        c9 = '&';
                        break;
                    }
                    break;
                case -903869683:
                    if (str.equals("iap.android.notified")) {
                        c9 = '[';
                        break;
                    }
                    break;
                case -863797531:
                    if (str.equals("sticker.user.pack.created")) {
                        c9 = 'X';
                        break;
                    }
                    break;
                case -861363076:
                    if (str.equals("meeting.meeting.start")) {
                        c9 = ';';
                        break;
                    }
                    break;
                case -826276900:
                    if (str.equals("user.coverart.updated")) {
                        c9 = 26;
                        break;
                    }
                    break;
                case -800201132:
                    if (str.equals("media.media.created")) {
                        c9 = ' ';
                        break;
                    }
                    break;
                case -772340311:
                    if (str.equals("user.avatar.updated")) {
                        c9 = 25;
                        break;
                    }
                    break;
                case -743762398:
                    if (str.equals("media.comment.updated")) {
                        c9 = '%';
                        break;
                    }
                    break;
                case -714851814:
                    if (str.equals("group.admin.created")) {
                        c9 = 17;
                        break;
                    }
                    break;
                case -630054315:
                    if (str.equals("system.force.reconnect")) {
                        c9 = '-';
                        break;
                    }
                    break;
                case -560772247:
                    if (str.equals("bulletin.topic.sticked")) {
                        c9 = 'E';
                        break;
                    }
                    break;
                case -474621720:
                    if (str.equals("friend.friend.created")) {
                        c9 = 0;
                        break;
                    }
                    break;
                case -471004516:
                    if (str.equals("chat.message.deleted")) {
                        c9 = 31;
                        break;
                    }
                    break;
                case -306809838:
                    if (str.equals("invite.friend.accepted")) {
                        c9 = ',';
                        break;
                    }
                    break;
                case -292839295:
                    if (str.equals("bulletin.post.created")) {
                        c9 = '?';
                        break;
                    }
                    break;
                case -278292603:
                    if (str.equals("media.media.deleted")) {
                        c9 = '#';
                        break;
                    }
                    break;
                case -277693585:
                    if (str.equals("bulletin.poll.option.casted")) {
                        c9 = 'I';
                        break;
                    }
                    break;
                case -228547230:
                    if (str.equals("friend.friend.hided")) {
                        c9 = 3;
                        break;
                    }
                    break;
                case -220662488:
                    if (str.equals("suggestion.suggestion.created")) {
                        c9 = '0';
                        break;
                    }
                    break;
                case -192943285:
                    if (str.equals("group.admin.deleted")) {
                        c9 = 18;
                        break;
                    }
                    break;
                case 461331:
                    if (str.equals("group.display.name.updated")) {
                        c9 = 19;
                        break;
                    }
                    break;
                case 11667875:
                    if (str.equals("group.group.enabled")) {
                        c9 = 16;
                        break;
                    }
                    break;
                case 47286809:
                    if (str.equals("friend.friend.deleted")) {
                        c9 = 1;
                        break;
                    }
                    break;
                case 153883326:
                    if (str.equals("client.rtc.bye")) {
                        c9 = '7';
                        break;
                    }
                    break;
                case 189290855:
                    if (str.equals("group.member.created.v2")) {
                        c9 = '\f';
                        break;
                    }
                    break;
                case 189687211:
                    if (str.equals("user.preference.updated")) {
                        c9 = 27;
                        break;
                    }
                    break;
                case 229069234:
                    if (str.equals("bulletin.post.deleted")) {
                        c9 = '@';
                        break;
                    }
                    break;
                case 247054131:
                    if (str.equals("friend.friend.unblocked")) {
                        c9 = 6;
                        break;
                    }
                    break;
                case 260435767:
                    if (str.equals("supervise.log.request")) {
                        c9 = 'V';
                        break;
                    }
                    break;
                case 301246041:
                    if (str.equals("suggestion.suggestion.deleted")) {
                        c9 = '1';
                        break;
                    }
                    break;
                case 350370271:
                    if (str.equals("media.album.created")) {
                        c9 = '\"';
                        break;
                    }
                    break;
                case 519222351:
                    if (str.equals("media.comment.created")) {
                        c9 = '!';
                        break;
                    }
                    break;
                case 596368569:
                    if (str.equals("dou.permission.changed")) {
                        c9 = 'Z';
                        break;
                    }
                    break;
                case 673160263:
                    if (str.equals("user.display.name.updated")) {
                        c9 = 23;
                        break;
                    }
                    break;
                case 687329364:
                    if (str.equals("org.membership.self.created")) {
                        c9 = 'S';
                        break;
                    }
                    break;
                case 872278800:
                    if (str.equals("media.album.deleted")) {
                        c9 = '$';
                        break;
                    }
                    break;
                case 878321484:
                    if (str.equals("system.force.logout")) {
                        c9 = IOUtils.DIR_SEPARATOR_UNIX;
                        break;
                    }
                    break;
                case 879535989:
                    if (str.equals("meeting.meeting.end")) {
                        c9 = '<';
                        break;
                    }
                    break;
                case 946682189:
                    if (str.equals("chat.message.recalled")) {
                        c9 = 30;
                        break;
                    }
                    break;
                case 1018813168:
                    if (str.equals("friend.friend.broke.up")) {
                        c9 = 2;
                        break;
                    }
                    break;
                case 1041130880:
                    if (str.equals("media.comment.deleted")) {
                        c9 = '\'';
                        break;
                    }
                    break;
                case 1045409518:
                    if (str.equals("bulletin.topic.unliked")) {
                        c9 = 'B';
                        break;
                    }
                    break;
                case 1095049973:
                    if (str.equals("bulletin.topic.updated")) {
                        c9 = 'O';
                        break;
                    }
                    break;
                case 1099704914:
                    if (str.equals("bulletin.topic.lastRead")) {
                        c9 = 'M';
                        break;
                    }
                    break;
                case 1169179300:
                    if (str.equals("bulletin.topic.casted")) {
                        c9 = 'K';
                        break;
                    }
                    break;
                case 1179217906:
                    if (str.equals("bulletin.topic.closed")) {
                        c9 = 'G';
                        break;
                    }
                    break;
                case 1209237893:
                    if (str.equals("org.membership.self.deleted")) {
                        c9 = 'T';
                        break;
                    }
                    break;
                case 1232334874:
                    if (str.equals("org.membership.created")) {
                        c9 = 'Q';
                        break;
                    }
                    break;
                case 1326583123:
                    if (str.equals("client.rtc.candidate")) {
                        c9 = '6';
                        break;
                    }
                    break;
                case 1386831645:
                    if (str.equals("group.group.updated")) {
                        c9 = '\t';
                        break;
                    }
                    break;
                case 1418851467:
                    if (str.equals("friend.nickname.updated")) {
                        c9 = 4;
                        break;
                    }
                    break;
                case 1447518907:
                    if (str.equals("account.email.verified")) {
                        c9 = 'U';
                        break;
                    }
                    break;
                case 1559154488:
                    if (str.equals("client.rtc.accept")) {
                        c9 = '2';
                        break;
                    }
                    break;
                case 1569806830:
                    if (str.equals("client.rtc.answer")) {
                        c9 = '5';
                        break;
                    }
                    break;
                case 1716541700:
                    if (str.equals("invite.friend.canceled")) {
                        c9 = '*';
                        break;
                    }
                    break;
                case 1754243403:
                    if (str.equals("org.membership.deleted")) {
                        c9 = 'R';
                        break;
                    }
                    break;
                case 1758041277:
                    if (str.equals("client.rtc.hangup")) {
                        c9 = '9';
                        break;
                    }
                    break;
                case 1776528225:
                    if (str.equals("group.reminder.set.event")) {
                        c9 = 21;
                        break;
                    }
                    break;
                case 1794212992:
                    if (str.equals("media.transcode.failed")) {
                        c9 = '(';
                        break;
                    }
                    break;
                case 1798916409:
                    if (str.equals("client.rtc.invite")) {
                        c9 = '8';
                        break;
                    }
                    break;
                case 1819312732:
                    if (str.equals("friend.friend.showed")) {
                        c9 = 5;
                        break;
                    }
                    break;
                case 1846782000:
                    if (str.equals("bulletin.topic.unsticked")) {
                        c9 = 'F';
                        break;
                    }
                    break;
                case 1864432172:
                    if (str.equals("client.rtc.offer")) {
                        c9 = '4';
                        break;
                    }
                    break;
                case 2026580972:
                    if (str.equals("group.notification.changed.event")) {
                        c9 = 20;
                        break;
                    }
                    break;
                case 2047543725:
                    if (str.equals("user.organization.update.event")) {
                        c9 = 28;
                        break;
                    }
                    break;
                case 2047905231:
                    if (str.equals("client.rtc.reject")) {
                        c9 = '3';
                        break;
                    }
                    break;
                case 2078126333:
                    if (str.equals("bulletin.topic.uncasted")) {
                        c9 = 'L';
                        break;
                    }
                    break;
                case 2104043874:
                    if (str.equals("bulletin.topic.reopened")) {
                        c9 = 'H';
                        break;
                    }
                    break;
                case 2124467303:
                    if (str.equals("bulletin.topic.liked")) {
                        c9 = 'A';
                        break;
                    }
                    break;
                default:
                    c9 = 65535;
                    break;
            }
            switch (c9) {
                case 0:
                    return m20897y(map.get("actor"), map.get("userId"));
                case 1:
                    return m20899z(map.get("userId"));
                case 2:
                    return m20895x(strM14428m, map.get("userId"));
                case 3:
                    return m20829A(map.get("userId"));
                case 4:
                    m20881n0(str, map.get("userId"), c2904l);
                    return true;
                case 5:
                case 6:
                case 7:
                    return m20831B(str, map.get("userId"));
                case '\b':
                case '\t':
                case '\n':
                case 11:
                case '\f':
                case '\r':
                case 14:
                case 15:
                case 16:
                case 17:
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                    return m20835D(str, map, map.get("groupId"), map.get("userId"), map.get("actor"), c2904l);
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                case 28:
                    m20881n0(str, C5616j.m22346k(strM14428m), c2904l);
                    return true;
                case 29:
                    return m20879m0(map.get("actor"), map.get("sn"), c2904l);
                case 30:
                    return m20850M(map.get(Constants.FirelogAnalytics.PARAM_MESSAGE_ID), time, map.get("actor"));
                case 31:
                    return m20889s(map.get(Constants.FirelogAnalytics.PARAM_MESSAGE_ID), time, map.get("actor"));
                case ' ':
                    return m20842H(str, map.get("groupId"), map.get("mediaId"), map.get("albumId"), strM14428m, c2904l, map.get("albumName"), map.get("lastModified"));
                case '!':
                    return m20840G(map);
                case '\"':
                case '#':
                case '$':
                case '%':
                    return m20842H(str, map.get("groupId"), map.get("mediaId"), map.get("albumId"), strM14428m, c2904l, map.get("albumName"), "");
                case '&':
                    return m20885q(str, map, c2904l);
                case '\'':
                    return m20887r(map);
                case '(':
                    return m20878l0(map.get("mediaId"));
                case ')':
                case '*':
                case '+':
                    return m20833C(str, map.get("actor"));
                case ',':
                    return m20831B(str, map.get("actor"));
                case '-':
                    return m20894w(map.get("xmppServer"), map.get("xmppPort"));
                case '.':
                    return m20891u();
                case '/':
                    return m20892v();
                case '0':
                    return m20865b0();
                case '1':
                    return m20866c0();
                case '2':
                    return false;
                case '3':
                    return false;
                case '4':
                    return false;
                case '5':
                    return false;
                case '6':
                    return false;
                case '7':
                    return false;
                case '8':
                    return m20863Z(c2904l, map);
                case '9':
                    return m20844I(map);
                case ':':
                    return m20862Y(c2904l, map);
                case ';':
                case '<':
                    return m20847K(c2904l, str, map);
                case '=':
                    return m20870f0(map);
                case '>':
                    return m20871g0(map);
                case '?':
                    return m20859V(map);
                case '@':
                    return m20860W(map);
                case 'A':
                case 'B':
                    return m20873i0(map);
                case 'C':
                case 'D':
                    return m20861X(map);
                case 'E':
                    return m20874j0(map, true);
                case 'F':
                    return m20874j0(map, false);
                case 'G':
                    return m20869e0(map, true);
                case 'H':
                    return m20869e0(map, false);
                case 'I':
                    return m20858U(map, true);
                case 'J':
                    return m20858U(map, false);
                case 'K':
                    return m20868d0(map, true);
                case 'L':
                    return m20868d0(map, false);
                case 'M':
                    return m20872h0(map);
                case 'N':
                    return m20857T(map);
                case 'O':
                    return m20876k0(map);
                case 'P':
                    m20839F0(map);
                    return false;
                case 'Q':
                    return m20852O(map);
                case 'R':
                    return m20853P(map);
                case 'S':
                    return m20854Q(map);
                case 'T':
                    return m20855R(map);
                case 'U':
                    return m20890t(map);
                case 'V':
                    return m20838F(map);
                case 'W':
                case 'X':
                    return m20864a0();
                case 'Y':
                    return m20851N(c2904l, map);
                case 'Z':
                    return m20856S(map);
                case '[':
                    return m20837E(map);
                default:
                    ULogUtility.m16664G("unknown event: " + str, "XMPPEventManager");
                    return false;
            }
        }
    }

    /* renamed from: M */
    public final boolean m20850M(String str, long j9, String str2) throws JSONException {
        Group groupM15077n;
        if (str == null) {
            return false;
        }
        C2950b0.m14904c().m14870e(str, Long.parseLong(str2), ArchiveMessageObj$Type.DELETE, j9);
        MessageObj messageObjM15179r = C2950b0.m14916o().m15179r(str);
        ULogUtility.m16664G("isDeleteMessage(" + str + ") Success : " + C2950b0.m14916o().m15169h(str), "XMPPEventManager");
        if (messageObjM15179r != null) {
            JSONObject jSONObjectM7487T = Globals.m7388i0().m7487T();
            try {
                jSONObjectM7487T.put(str, messageObjM15179r.m14788z().getTime());
            } catch (JSONException e9) {
                e9.printStackTrace();
            }
            Globals.m7388i0().m7440I2(jSONObjectM7487T);
        }
        if (messageObjM15179r != null) {
            C5180s.m20255h(messageObjM15179r.m14772j());
        }
        if (messageObjM15179r == null || (groupM15077n = C2950b0.m14912k().m15077n(messageObjM15179r.m14772j())) == null || messageObjM15179r.m14788z().getTime() <= groupM15077n.m15748d()) {
            return true;
        }
        ULogUtility.m16662E("updateGroup:" + groupM15077n.f13727n + " Unread count", "XMPPEventManager");
        C2907m0.m14454I().m14503e0(groupM15077n.f13723j, false, messageObjM15179r.m14788z().getTime());
        return true;
    }

    /* renamed from: N */
    public final boolean m20851N(C2904l c2904l, Map<String, String> map) {
        if (c2904l.m14396K()) {
            return false;
        }
        String str = map.get("organizationId");
        if (C5170o0.m20170e(str)) {
            str = "";
        }
        Globals.m7388i0().m7444J3(str);
        C6566a.m25167z();
        return true;
    }

    /* renamed from: O */
    public final boolean m20852O(Map<String, String> map) {
        String str = map.get("userId");
        try {
            C2950b0.m14918q().m15204k(Long.valueOf(map.get("organizationId")), Long.valueOf(str));
            return false;
        } catch (NumberFormatException e9) {
            e9.printStackTrace();
            return false;
        }
    }

    /* renamed from: P */
    public final boolean m20853P(Map<String, String> map) {
        String str = map.get("userId");
        C2950b0.m14918q().m15198e(map.get("organizationId"), str);
        return false;
    }

    /* renamed from: Q */
    public final boolean m20854Q(Map<String, String> map) {
        String str = map.get("organizationId");
        String strM7449L = Globals.m7388i0().m7449L();
        ArrayList arrayList = new ArrayList();
        arrayList.add(new C6301o("token", strM7449L));
        arrayList.add(new C6301o("organizationId", str));
        arrayList.add(new C6301o("pageSize", String.valueOf(20)));
        Pair<String, String> pairM15731j = this.f18078a.m15731j("organization", "listOrgMember", arrayList);
        String str2 = (String) pairM15731j.first;
        String str3 = (String) pairM15731j.second;
        if (!"200".equals(str2)) {
            return false;
        }
        int iM16553k1 = CLUtility.m16553k1(str3);
        int iM16494U0 = CLUtility.m16494U0(str3);
        int iM16559m = CLUtility.m16559m(iM16553k1, 20);
        if (iM16553k1 == -1 || iM16494U0 == -1) {
            return false;
        }
        if (iM16553k1 == iM16494U0) {
            C3062b.m15819s(str3, str);
            return false;
        }
        C3062b.m15819s(str3, str);
        for (int i9 = 2; i9 <= iM16559m; i9++) {
            ArrayList arrayList2 = new ArrayList();
            arrayList2.add(new C6301o("token", strM7449L));
            arrayList2.add(new C6301o("organizationId", str));
            arrayList2.add(new C6301o("pageIndex", String.valueOf(i9)));
            arrayList2.add(new C6301o("pageSize", String.valueOf(20)));
            Pair<String, String> pairM15731j2 = this.f18078a.m15731j("organization", "listOrgMember", arrayList2);
            String str4 = (String) pairM15731j2.first;
            String str5 = (String) pairM15731j2.second;
            if (str4 != null && str4.equals("200")) {
                C3062b.m15819s(str5, str);
            }
        }
        return false;
    }

    /* renamed from: R */
    public final boolean m20855R(Map<String, String> map) {
        try {
            C2950b0.m14918q().m15199f(map.get("organizationId"));
            return false;
        } catch (NumberFormatException e9) {
            e9.printStackTrace();
            return false;
        }
    }

    /* renamed from: S */
    public final boolean m20856S(Map<String, String> map) {
        String str = map.get("isBroadcaster");
        StringBuilder sb = new StringBuilder();
        String strM7455M0 = Globals.m7388i0().m7455M0();
        if (!TextUtils.isEmpty(strM7455M0)) {
            for (String str2 : strM7455M0.split(",")) {
                if (!"BROADCASTER".equals(str2) && !TextUtils.isEmpty(str2)) {
                    sb.append(str2);
                    sb.append(",");
                }
            }
        }
        if (Boolean.valueOf(str).booleanValue()) {
            sb.append("BROADCASTER");
            sb.append(",");
        }
        Globals.m7388i0().m7458M3(sb.length() > 0 ? sb.toString().substring(0, sb.length() - 1) : "");
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x0072  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x008a  */
    /* renamed from: T */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final boolean m20857T(Map<String, String> map) throws JSONException {
        String str = map.get("pollOptionId");
        String str2 = map.get("comment");
        String str3 = map.get("userId");
        PollOptionObj pollOptionObjM14892e = C2950b0.m14921t().m14892e(Long.parseLong(str));
        if (pollOptionObjM14892e != null) {
            JSONArray jSONArray = null;
            if (str2 != null && str3 != null) {
                int i9 = -1;
                if (pollOptionObjM14892e.f13048i != null) {
                    try {
                        JSONArray jSONArray2 = new JSONArray(pollOptionObjM14892e.f13048i);
                        int i10 = 0;
                        while (true) {
                            try {
                                if (i10 < jSONArray2.length()) {
                                    String string = jSONArray2.getJSONObject(i10).getString("userId");
                                    if (string != null && string.equals(str3)) {
                                        i9 = i10;
                                        break;
                                    }
                                    i10++;
                                } else {
                                    break;
                                }
                            } catch (JSONException e9) {
                                e = e9;
                                jSONArray = jSONArray2;
                                e.printStackTrace();
                                C5317a c5317a = new C5317a(Long.parseLong(str3), str2);
                                if (jSONArray != null) {
                                }
                                if (jSONArray != null) {
                                }
                                ArrayList arrayList = new ArrayList();
                                arrayList.add("optionComment");
                                C2950b0.m14921t().m14898k(Long.parseLong(str), pollOptionObjM14892e, arrayList);
                                return false;
                            }
                        }
                        jSONArray = jSONArray2;
                    } catch (JSONException e10) {
                        e = e10;
                    }
                } else {
                    jSONArray = new JSONArray();
                }
                C5317a c5317a2 = new C5317a(Long.parseLong(str3), str2);
                if (jSONArray != null) {
                    if (i9 >= 0) {
                        try {
                            jSONArray.put(i9, c5317a2.m20811a());
                        } catch (JSONException e11) {
                            e11.printStackTrace();
                        }
                    } else {
                        jSONArray.put(c5317a2.m20811a());
                    }
                }
            }
            if (jSONArray != null) {
                pollOptionObjM14892e.f13048i = jSONArray.toString();
                Log.d("XMPPEventManager", "[handlePollCommentUpdate] pollOptionObj.optionComment = " + pollOptionObjM14892e.f13048i);
            }
            ArrayList arrayList2 = new ArrayList();
            arrayList2.add("optionComment");
            C2950b0.m14921t().m14898k(Long.parseLong(str), pollOptionObjM14892e, arrayList2);
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x0087  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x008e  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00ee  */
    /* renamed from: U */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final boolean m20858U(Map<String, String> map, boolean z8) throws JSONException {
        TopicObj topicObjM14984n;
        JSONArray jSONArray;
        int i9;
        String str = map.get("topicId");
        String str2 = map.get("numberOfPolls");
        String str3 = map.get("pollOptionId");
        String str4 = map.get("actor");
        boolean zM7474Q1 = Globals.m7388i0().m7474Q1(str4);
        JSONArray jSONArray2 = new JSONArray();
        PollOptionObj pollOptionObjM14892e = C2950b0.m14921t().m14892e(Long.parseLong(str3));
        if (pollOptionObjM14892e != null) {
            pollOptionObjM14892e.f13045f = Integer.parseInt(str2);
            if (zM7474Q1) {
                pollOptionObjM14892e.f13046g = z8;
            }
            if (pollOptionObjM14892e.f13048i != null) {
                try {
                    jSONArray = new JSONArray(pollOptionObjM14892e.f13048i);
                    i9 = -1;
                    for (int i10 = 0; i10 < jSONArray.length(); i10++) {
                        try {
                            String string = jSONArray.getJSONObject(i10).getString("userId");
                            if (string != null && string.equals(str4)) {
                                i9 = i10;
                            }
                        } catch (JSONException e9) {
                            e = e9;
                            e.printStackTrace();
                            if (!z8) {
                            }
                            pollOptionObjM14892e.f13048i = jSONArray.toString();
                            ArrayList arrayList = new ArrayList();
                            arrayList.add("numberOfPolls");
                            arrayList.add("isVoted");
                            arrayList.add("optionComment");
                            C2950b0.m14921t().m14898k(Long.parseLong(str3), pollOptionObjM14892e, arrayList);
                            topicObjM14984n = C2950b0.m14906e().m14984n(Long.parseLong(str));
                            if (topicObjM14984n != null) {
                                topicObjM14984n.f13109x = C2950b0.m14921t().m15237r(str);
                                if (zM7474Q1) {
                                }
                                ArrayList arrayList2 = new ArrayList();
                                arrayList2.add("isVoted");
                                arrayList2.add("numberOfVoters");
                                C2950b0.m14906e().m14990t(Long.valueOf(str).longValue(), topicObjM14984n, arrayList2);
                            }
                            return false;
                        }
                    }
                } catch (JSONException e10) {
                    e = e10;
                    jSONArray = jSONArray2;
                    i9 = -1;
                }
            } else {
                jSONArray = jSONArray2;
                i9 = -1;
            }
            if (!z8) {
                C5317a c5317a = new C5317a(Long.parseLong(str4), "");
                if (i9 >= 0) {
                    try {
                        jSONArray.put(i9, c5317a.m20811a());
                    } catch (JSONException e11) {
                        e11.printStackTrace();
                    }
                } else {
                    jSONArray.put(c5317a.m20811a());
                }
            } else if (i9 != -1) {
                jSONArray = CLUtility.m16538g2(jSONArray, i9);
            }
            pollOptionObjM14892e.f13048i = jSONArray.toString();
            ArrayList arrayList3 = new ArrayList();
            arrayList3.add("numberOfPolls");
            arrayList3.add("isVoted");
            arrayList3.add("optionComment");
            C2950b0.m14921t().m14898k(Long.parseLong(str3), pollOptionObjM14892e, arrayList3);
        }
        topicObjM14984n = C2950b0.m14906e().m14984n(Long.parseLong(str));
        if (topicObjM14984n != null && !topicObjM14984n.f13107v) {
            topicObjM14984n.f13109x = C2950b0.m14921t().m15237r(str);
            if (zM7474Q1) {
                topicObjM14984n.f13110y = z8;
            }
            ArrayList arrayList22 = new ArrayList();
            arrayList22.add("isVoted");
            arrayList22.add("numberOfVoters");
            C2950b0.m14906e().m14990t(Long.valueOf(str).longValue(), topicObjM14984n, arrayList22);
        }
        return false;
    }

    /* renamed from: V */
    public final boolean m20859V(Map<String, String> map) {
        TopicObj topicObjM20841G0;
        String str = map.get("postId");
        if ((!C5170o0.m20170e(str) && C2950b0.m14905d().m14952o(Long.parseLong(str)) != null) || (topicObjM20841G0 = m20841G0(map)) == null) {
            return false;
        }
        String str2 = map.get("actor");
        TopicCommentObj topicCommentObjM20839F0 = m20839F0(map);
        if (topicCommentObjM20839F0 != null && topicCommentObjM20839F0.m14030e() >= Globals.m7388i0().m7502W0().getTime() && !str2.equals(String.valueOf(Globals.m7388i0().m7568k1())) && topicObjM20841G0.f13106u.equals("Topic")) {
            m20843H0(topicObjM20841G0);
        }
        if (topicObjM20841G0.f13106u.equals("Topic")) {
            m20845I0(map, null, topicCommentObjM20839F0);
        }
        return false;
    }

    /* renamed from: W */
    public final boolean m20860W(Map<String, String> map) {
        m20841G0(map);
        String str = map.get("postId");
        String str2 = map.get("topicId");
        TopicObj topicObjM14984n = C2950b0.m14906e().m14984n(Long.valueOf(str2).longValue());
        TopicCommentObj topicCommentObjM14952o = C2950b0.m14905d().m14952o(Long.valueOf(str).longValue());
        boolean z8 = true;
        if (topicObjM14984n != null && topicCommentObjM14952o != null) {
            if (!topicObjM14984n.f13103r && topicCommentObjM14952o.m14035j()) {
                topicObjM14984n.m14834F(Math.max(0, topicObjM14984n.m14850p() - 1));
            }
            ArrayList arrayList = new ArrayList();
            arrayList.add("Unread");
            C2950b0.m14906e().m14990t(topicObjM14984n.m14849o(), topicObjM14984n, arrayList);
        }
        C2950b0.m14905d().m14948k(str);
        C5180s.m20255h(map.get("groupId"));
        if (topicObjM14984n != null && topicCommentObjM14952o != null && topicCommentObjM14952o.m14035j()) {
            if (!topicObjM14984n.f13103r) {
                C2907m0.m14454I().m14508k0(NumberUtils.toLong(str2), topicObjM14984n.f13103r, false, false);
            } else if (topicObjM14984n.m14850p() == -1) {
                for (TopicCommentObj topicCommentObj : C2950b0.m14905d().m14953p(Long.valueOf(str2).longValue())) {
                    if (topicCommentObj.m14033h() != topicCommentObjM14952o.m14033h() && topicCommentObj.m14035j()) {
                        z8 = false;
                    }
                }
                if (z8) {
                    C2907m0.m14454I().m14508k0(NumberUtils.toLong(str2), topicObjM14984n.f13103r, false, false);
                }
            }
        }
        return false;
    }

    /* renamed from: X */
    public final boolean m20861X(Map<String, String> map) {
        JSONArray jSONArrayM20196r;
        String str = map.get("postId");
        String str2 = map.get("groupId");
        ArrayList arrayList = new ArrayList();
        arrayList.add(new C6301o("token", Globals.m7388i0().m7449L()));
        arrayList.add(new C6301o("postId", str));
        Pair<String, String> pairM15731j = this.f18078a.m15731j("groupbulletin", "queryPost", arrayList);
        String str3 = (String) pairM15731j.first;
        String str4 = (String) pairM15731j.second;
        if ("200".equals(str3) && str2 != null && (jSONArrayM20196r = C5172p.m20196r(str4)) != null) {
            for (int i9 = 0; i9 < jSONArrayM20196r.length(); i9++) {
                try {
                    TopicCommentObj topicCommentObjM20182d = C5172p.m20182d(jSONArrayM20196r.getJSONObject(i9));
                    if (topicCommentObjM20182d != null) {
                        ArrayList arrayList2 = new ArrayList();
                        arrayList2.add("LikeCount");
                        arrayList2.add("isLiked");
                        C2950b0.m14905d().m14959v(topicCommentObjM20182d.m14033h(), topicCommentObjM20182d, arrayList2);
                    }
                } catch (JSONException e9) {
                    e9.printStackTrace();
                }
            }
        }
        return false;
    }

    /* renamed from: Y */
    public final boolean m20862Y(C2904l c2904l, Map<String, String> map) {
        ULogUtility.m16680p("MeetingManager", "handleRemindMeeting info : " + map);
        String str = map.get("eventId");
        if ((m20884p0(c2904l) && m20886q0(c2904l.m14386A().getTime())) || c2904l.m14396K()) {
            ULogUtility.m16663F("XMPPEventManager", "handleRemindMeeting. (Archive)the meeting time is invalid");
            return false;
        }
        Context applicationContext = Globals.m7388i0().getApplicationContext();
        Intent intent = new Intent(applicationContext, (Class<?>) RemindMeetingActivity.class);
        intent.putExtra(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_TITLE, map.get(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_TITLE));
        intent.putExtra("startTime", map.get("startDate"));
        intent.putExtra("eventId", str);
        intent.addFlags(SQLiteDatabase.CREATE_IF_NECESSARY);
        if ((Build.VERSION.SDK_INT < 29 || !Globals.m7396z1()) && !MeetingManager.m5619l()) {
            applicationContext.startActivity(intent);
            return true;
        }
        NotificationHelper.m14074R(intent);
        return true;
    }

    /* renamed from: Z */
    public final boolean m20863Z(C2904l c2904l, Map<String, String> map) {
        if ((m20884p0(c2904l) && m20882o0(c2904l.m14386A().getTime())) || m20888r0(c2904l.m14386A().getTime()) || c2904l.m14396K()) {
            ULogUtility.m16680p("XMPPEventManager", "handleRtcInvite. (Archive)the call time is invalid");
            return false;
        }
        String str = "RTC event " + c2904l.m14428m() + StringUtils.SPACE + map.toString();
        String str2 = map.get("callerId");
        String str3 = map.get("calleeId");
        String str4 = map.get("callType");
        String str5 = map.get("callId");
        String str6 = map.get("MServerAddr");
        String str7 = map.get("MServerToken");
        boolean zContainsKey = map.containsKey("fromPSTN");
        String strUnescapeXml = map.containsKey("callerName") ? StringEscapeUtils.unescapeXml(map.get("callerName")) : "";
        String str8 = map.containsKey("callerExt") ? map.get("callerExt") : "";
        String number = (C5170o0.m20170e(strUnescapeXml) || !PhoneNumberUtils.isGlobalPhoneNumber(strUnescapeXml) || !C5170o0.m20170e(str8) || (strUnescapeXml.length() == 8 && Locale.getDefault().getCountry().equals(Locale.TAIWAN.getCountry()))) ? strUnescapeXml : PhoneNumberUtils.formatNumber(strUnescapeXml, Locale.getDefault().getCountry());
        if (MimeTypes.BASE_TYPE_AUDIO.equals(str4) || MimeTypes.BASE_TYPE_VIDEO.equals(str4)) {
            m20846J(str2, str3, str5, str4, str6, str7, false, zContainsKey, number, str8);
        }
        Log.d("XMPPEventManager", str);
        return true;
    }

    /* renamed from: a0 */
    public final boolean m20864a0() {
        Globals.m7388i0().m7579m1();
        return false;
    }

    /* renamed from: b0 */
    public final boolean m20865b0() {
        Globals.m7388i0().m7411C3(true);
        Globals.m7388i0().m7401A3(true);
        return true;
    }

    /* renamed from: c0 */
    public final boolean m20866c0() {
        Globals.m7388i0().m7401A3(true);
        return true;
    }

    /* renamed from: d */
    public final String m20867d(String str, String str2) {
        HashMap map = new HashMap();
        map.put("albumId", str);
        map.put("mediaId", str2);
        JSONObject jSONObject = new JSONObject(map);
        return jSONObject.toString().substring(1, jSONObject.toString().length() - 1);
    }

    /* renamed from: d0 */
    public final boolean m20868d0(Map<String, String> map, boolean z8) {
        String str = map.get("topicId");
        String str2 = map.get("numberOfVoters");
        boolean zM7474Q1 = Globals.m7388i0().m7474Q1(map.get("actor"));
        TopicObj topicObjM14984n = C2950b0.m14906e().m14984n(Long.valueOf(str).longValue());
        if (topicObjM14984n == null) {
            return false;
        }
        if (zM7474Q1) {
            topicObjM14984n.f13110y = z8;
        }
        topicObjM14984n.f13109x = Integer.valueOf(str2).intValue();
        ArrayList arrayList = new ArrayList();
        arrayList.add("numberOfVoters");
        arrayList.add("isVoted");
        C2950b0.m14906e().m14990t(Long.valueOf(str).longValue(), topicObjM14984n, arrayList);
        return false;
    }

    /* renamed from: e0 */
    public final boolean m20869e0(Map<String, String> map, boolean z8) {
        String str = map.get("topicId");
        TopicObj topicObjM14984n = C2950b0.m14906e().m14984n(Long.valueOf(str).longValue());
        if (topicObjM14984n == null) {
            return false;
        }
        topicObjM14984n.f13100o = z8;
        ArrayList arrayList = new ArrayList();
        arrayList.add("isClosed");
        C2950b0.m14906e().m14990t(Long.valueOf(str).longValue(), topicObjM14984n, arrayList);
        return false;
    }

    /* renamed from: f0 */
    public final boolean m20870f0(Map<String, String> map) {
        TopicObj topicObjM20841G0;
        String str = map.get("topicId");
        if ((!C5170o0.m20170e(str) && C2950b0.m14906e().m14984n(Long.parseLong(str)) != null) || (topicObjM20841G0 = m20841G0(map)) == null) {
            return false;
        }
        String str2 = map.get("actor");
        if (topicObjM20841G0.f13106u.equals("Poll")) {
            m20848K0(topicObjM20841G0, 1);
        } else {
            if (topicObjM20841G0.m14840e() >= Globals.m7388i0().m7502W0().getTime() && !str2.equals(String.valueOf(Globals.m7388i0().m7568k1()))) {
                m20843H0(topicObjM20841G0);
            }
            m20845I0(map, topicObjM20841G0, null);
        }
        return false;
    }

    /* renamed from: g0 */
    public final boolean m20871g0(Map<String, String> map) {
        String str = map.get("topicId");
        String str2 = map.get("groupId");
        TopicObj topicObjM14984n = C2950b0.m14906e().m14984n(NumberUtils.toLong(str));
        C2950b0.m14906e().m14979i(str);
        C2950b0.m14905d().m14949l(str);
        C2950b0.m14921t().m15235p(str);
        C5180s.m20255h(str2);
        if (topicObjM14984n != null) {
            if (topicObjM14984n.f13106u.equals("Poll")) {
                m20848K0(topicObjM14984n, -1);
                C2907m0.m14454I().m14504f0(topicObjM14984n, false);
            } else {
                C2907m0.m14454I().m14508k0(NumberUtils.toLong(str), topicObjM14984n.f13103r, true, false);
            }
        }
        return false;
    }

    /* renamed from: h0 */
    public final boolean m20872h0(Map<String, String> map) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        TopicObj topicObjM14984n;
        try {
            long j9 = Long.parseLong(map.get("groupId"));
            long j10 = Long.parseLong(map.get("topicId"));
            long j11 = Long.parseLong(map.get("lastRead"));
            if (j11 < Globals.m7388i0().m7502W0().getTime() || (topicObjM14984n = C2950b0.m14906e().m14984n(j10)) == null) {
                return false;
            }
            C2950b0.m14905d().m14958u(j10, j11);
            int iM14955r = C2950b0.m14905d().m14955r(j10);
            int iM14954q = C2950b0.m14905d().m14954q(j10, j11);
            if (iM14955r == 0 && iM14954q > 0 && topicObjM14984n.m14854t()) {
                topicObjM14984n.m14834F(-1);
            } else {
                topicObjM14984n.m14834F(iM14955r);
            }
            topicObjM14984n.m14831C(j11);
            ArrayList arrayList = new ArrayList();
            arrayList.add("Unread");
            arrayList.add("lastReadTime");
            C2950b0.m14906e().m14990t(topicObjM14984n.m14849o(), topicObjM14984n, arrayList);
            C2907m0.m14454I().m14507j0(j9, j10, iM14955r);
            return true;
        } catch (NumberFormatException e9) {
            C5154j.m20076j(e9);
            return false;
        }
    }

    /* renamed from: i0 */
    public final boolean m20873i0(Map<String, String> map) {
        JSONArray jSONArrayM20196r;
        String str = map.get("topicId");
        String str2 = map.get("groupId");
        ArrayList arrayList = new ArrayList();
        arrayList.add(new C6301o("token", Globals.m7388i0().m7449L()));
        arrayList.add(new C6301o("topicId", str));
        Pair<String, String> pairM15731j = this.f18078a.m15731j("groupbulletin", "queryTopic", arrayList);
        String str3 = (String) pairM15731j.first;
        String str4 = (String) pairM15731j.second;
        if ("200".equals(str3) && str2 != null && (jSONArrayM20196r = C5172p.m20196r(str4)) != null) {
            for (int i9 = 0; i9 < jSONArrayM20196r.length(); i9++) {
                try {
                    TopicObj topicObjM20204z = C5172p.m20204z(jSONArrayM20196r.getJSONObject(i9), Long.valueOf(str2).longValue());
                    if (topicObjM20204z != null) {
                        ArrayList arrayList2 = new ArrayList();
                        arrayList2.add("LikeCount");
                        arrayList2.add("isLiked");
                        C2950b0.m14906e().m14990t(topicObjM20204z.m14849o(), topicObjM20204z, arrayList2);
                        ULogUtility.m16670f("XMPPEventManager", "handleTopicLikedChanged topicObj :" + topicObjM20204z.toString());
                    }
                } catch (JSONException e9) {
                    e9.printStackTrace();
                }
            }
        }
        return false;
    }

    /* renamed from: j0 */
    public final boolean m20874j0(Map<String, String> map, boolean z8) {
        String str = map.get("topicId");
        String str2 = map.get("lastStickyTime");
        TopicObj topicObjM14984n = C2950b0.m14906e().m14984n(Long.valueOf(str).longValue());
        if (topicObjM14984n == null) {
            return false;
        }
        topicObjM14984n.f13097l = z8;
        topicObjM14984n.f13096k = Long.parseLong(str2);
        ArrayList arrayList = new ArrayList();
        arrayList.add("isSticky");
        arrayList.add("lastStickyTime");
        C2950b0.m14906e().m14990t(Long.valueOf(str).longValue(), topicObjM14984n, arrayList);
        return false;
    }

    /* renamed from: k */
    public void m20875k(m mVar) {
        this.f18080c.m24539c(mVar);
    }

    /* renamed from: k0 */
    public final boolean m20876k0(Map<String, String> map) {
        TopicObj topicObjM15726d0;
        String str = map.get("topicId");
        String str2 = map.get("groupId");
        if (C2950b0.m14906e().m14984n(Long.valueOf(str).longValue()) != null && (topicObjM15726d0 = this.f18078a.m15726d0(str2, str)) != null) {
            C2950b0.m14906e().m14978h(topicObjM15726d0, TopicObj.m14828m());
        }
        return false;
    }

    /* renamed from: l */
    public final void m20877l() {
        C6385v.m24526d(new Runnable() { // from class: m3.c
            @Override // java.lang.Runnable
            public final void run() {
                this.f18074b.m20825s0();
            }
        });
    }

    /* renamed from: l0 */
    public final boolean m20878l0(String str) {
        if (str == null) {
            return false;
        }
        String strM14584a0 = C2925v.m14584a0(str);
        MessageObj messageObjM15179r = C2950b0.m14916o().m15179r(strM14584a0);
        messageObjM15179r.m14762X("3");
        C2950b0.m14916o().m15159D(strM14584a0, messageObjM15179r, "Status");
        return true;
    }

    /* renamed from: m0 */
    public final boolean m20879m0(String str, String str2, C2904l c2904l) throws NumberFormatException {
        if (C5170o0.m20170e(str2) || this.f18081d.contains(str2)) {
            return true;
        }
        this.f18081d.add(str2);
        try {
            long j9 = Long.parseLong(str);
            List<Long> listM15096g = C2950b0.m14913l().m15096g(j9);
            if (listM15096g != null && !listM15096g.isEmpty()) {
                if (c2904l.m14396K()) {
                    ULogUtility.m16670f("CLSM", "Do not handle device public key change from history : " + j9);
                    return false;
                }
                long time = c2904l.m14386A().getTime();
                ULogUtility.m16670f("CLSM", "UID[" + str + "] key changed, renew groups: " + Arrays.toString(listM15096g.toArray()));
                Iterator<Long> it = listM15096g.iterator();
                while (it.hasNext()) {
                    C2889b.m14298h().m14312p(it.next().longValue(), time);
                }
                return true;
            }
            ULogUtility.m16683s("CLSM", "UID[" + str + "] key changed w/ no target");
            return false;
        } catch (NumberFormatException e9) {
            C5154j.m20076j(e9);
            return false;
        }
    }

    /* renamed from: n */
    public final String m20880n(String str) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new C6301o("token", Globals.m7388i0().m7449L()));
        arrayList.add(new C6301o("userId", str));
        Pair<String, String> pairM15731j = this.f18078a.m15731j("user", "userInfoV2", arrayList);
        String str2 = (String) pairM15731j.first;
        if (str2 != null && str2.equals("200")) {
            return C5172p.m20184f(C5172p.m20195q((String) pairM15731j.second)).m15621b();
        }
        Log.d("XMPPEventManager", "statusCode = " + str2);
        return null;
    }

    /* renamed from: n0 */
    public void m20881n0(String str, String str2, C2904l c2904l) {
        if (str2 == null) {
            return;
        }
        this.f18078a.m15729h0(str2, new d(str2, c2904l), Arrays.asList("friend.nickname.updated", "user.display.name.updated").contains(str));
    }

    /* renamed from: o0 */
    public final boolean m20882o0(long j9) {
        return FriendsClient.m15646K() - j9 >= 70000;
    }

    /* renamed from: p */
    public final String m20883p(String str, Message.Type type) {
        Friend friendM15727f0;
        String strM22346k = type.equals(Message.Type.chat) ? C5616j.m22346k(str) : C5616j.m22347l(str);
        if (strM22346k == null || (friendM15727f0 = this.f18078a.m15727f0(strM22346k)) == null) {
            return null;
        }
        return friendM15727f0.m15621b();
    }

    /* renamed from: p0 */
    public final boolean m20884p0(C2904l c2904l) {
        XMPPManager.HandleType handleTypeM14438r = c2904l.m14438r();
        return (handleTypeM14438r == XMPPManager.HandleType.XMPP && c2904l.m14399N()) || handleTypeM14438r == XMPPManager.HandleType.GCM || handleTypeM14438r == XMPPManager.HandleType.HEART_BEAT;
    }

    /* renamed from: q */
    public final boolean m20885q(String str, Map<String, String> map, C2904l c2904l) {
        String str2 = map.get("groupId");
        String str3 = map.get("albumId");
        if (str2 == null || str3 == null) {
            return false;
        }
        if (!c2904l.m14396K()) {
            GroupAlbumObj groupAlbumObjM15056i = C2950b0.m14911j().m15056i(str3);
            String str4 = map.get("albumName");
            String str5 = map.get("albumRename");
            if (!str4.equals(str5)) {
                Log.d("XMPPEventManager", "[handleAlbumUpdate] !albumName.equals(albumRename)");
                if (groupAlbumObjM15056i == null) {
                    return false;
                }
                groupAlbumObjM15056i.m14684k(str5);
                C2950b0.m14911j().m15061n(str3, groupAlbumObjM15056i, "AlbumName");
            }
        }
        Group groupM15077n = C2950b0.m14912k().m15077n(str2);
        if (groupM15077n == null) {
            groupM15077n = FriendsClient.m15650P(str2);
            if (groupM15077n == null) {
                return false;
            }
            C2950b0.m14912k().m15070g(groupM15077n, true);
        }
        if (str.equals("media.album.updated")) {
            groupM15077n.m15752j(m20883p(c2904l.m14428m(), c2904l.m14448w()));
            m20812E0(groupM15077n, c2904l, str, new JSONObject(map).toString(), 0);
        }
        return true;
    }

    /* renamed from: q0 */
    public final boolean m20886q0(long j9) {
        return FriendsClient.m15646K() - j9 >= 300000;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:15:0x006e  */
    /* renamed from: r */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final boolean m20887r(Map<String, String> map) {
        C3061a c3061aM15223l;
        String str = map.get("mediaId");
        String str2 = map.get("commentId");
        char c9 = 0;
        if (str2 == null || (c3061aM15223l = C2950b0.m14920s().m15223l(Long.valueOf(str2).longValue())) == null) {
            return false;
        }
        C2950b0.m14920s().m15221j(Long.valueOf(str2).longValue());
        if (str == null) {
            return false;
        }
        C2973l0 c2973l0M14725v = C2950b0.m14914m().m14725v(Long.valueOf(str).longValue());
        String str3 = map.get("commentCount");
        if (str3 != null) {
            c2973l0M14725v.m15123J(Integer.valueOf(str3).intValue());
            String strM15784f = c3061aM15223l.m15784f();
            strM15784f.hashCode();
            switch (strM15784f.hashCode()) {
                case -1083944904:
                    if (!strM15784f.equals("CommentDoodle")) {
                        c9 = 65535;
                        break;
                    }
                    break;
                case -523565044:
                    if (strM15784f.equals("CommentText")) {
                        c9 = 1;
                        break;
                    }
                    break;
                case 942868709:
                    if (strM15784f.equals("CommentMedia")) {
                        c9 = 2;
                        break;
                    }
                    break;
            }
            switch (c9) {
                case 0:
                    c2973l0M14725v.m15115B(c2973l0M14725v.m15133e() - 1);
                    break;
                case 1:
                    c2973l0M14725v.m15117D(c2973l0M14725v.m15135g() - 1);
                    break;
                case 2:
                    c2973l0M14725v.m15116C(c2973l0M14725v.m15134f() - 1);
                    break;
            }
        }
        C2950b0.m14914m().m14712i(c2973l0M14725v);
        return true;
    }

    /* renamed from: r0 */
    public final boolean m20888r0(long j9) {
        return j9 < Globals.m7388i0().m7502W0().getTime();
    }

    /* renamed from: s */
    public final boolean m20889s(String str, long j9, String str2) throws JSONException {
        if (str == null) {
            return false;
        }
        C2950b0.m14904c().m14870e(str, Long.parseLong(str2), ArchiveMessageObj$Type.DELETE, j9);
        MessageObj messageObjM15179r = C2950b0.m14916o().m15179r(str);
        if (messageObjM15179r != null) {
            JSONObject jSONObjectM7487T = Globals.m7388i0().m7487T();
            try {
                jSONObjectM7487T.put(str, messageObjM15179r.m14788z().getTime());
            } catch (JSONException e9) {
                e9.printStackTrace();
            }
            Globals.m7388i0().m7440I2(jSONObjectM7487T);
            Globals.m7388i0().m7480R2(messageObjM15179r.m14772j());
        }
        C2950b0.m14916o().m15169h(str);
        return true;
    }

    /* renamed from: t */
    public final boolean m20890t(Map<String, String> map) {
        Log.d("XMPPEventManager", "[handleEmailVerified] start");
        if (!"1".equals(map.get("isVerified"))) {
            return false;
        }
        Log.d("XMPPEventManager", "[handleEmailVerified] setIsVerifyEmail = true");
        Globals.m7388i0().m7564j3(true);
        return false;
    }

    /* renamed from: u */
    public boolean m20891u() {
        Log.w("XMPPEventManager", "Force Init");
        FriendsClient.m15652S0();
        XMPPManager.m14184g0().m14250j1();
        XMPPManager.f12475D = false;
        XMPPManager.m14184g0().m14223U();
        C6456d.m24714D().m24755P();
        return true;
    }

    /* renamed from: v */
    public final boolean m20892v() {
        Globals.m7388i0().m7402B();
        return true;
    }

    /* renamed from: v0 */
    public void m20893v0(Group group, final long j9) {
        C2950b0.m14901C(new Runnable() { // from class: m3.b
            @Override // java.lang.Runnable
            public final void run() {
                C5321e.m20826t0(j9);
            }
        });
        NotificationManager notificationManager = (NotificationManager) Globals.m7388i0().getSystemService("notification");
        if (group != null && notificationManager != null) {
            notificationManager.cancel(group.f13723j, 1);
        }
        if (group != null) {
            C2907m0.m14454I().m14514x(group.f13723j);
            this.f18078a.m15716T0(group.f13727n, false);
            C2907m0.m14454I().m14513w(group.f13723j, true);
        }
        C2907m0.m14454I().m14512v(j9);
    }

    /* renamed from: w */
    public final boolean m20894w(String str, String str2) {
        if (str == null || str2 == null) {
            return false;
        }
        this.f18078a.m15700H(str, str2);
        if (C6456d.m24714D().f21746p != null) {
            Intent intent = new Intent();
            intent.putExtra("noConnectivity", true);
            intent.putExtra("ForceReconnect", true);
            C6456d.m24714D().f21746p.onReceive(Globals.m7388i0().getApplicationContext(), intent);
        }
        return true;
    }

    /* renamed from: x */
    public final boolean m20895x(String str, String str2) {
        String strM22346k;
        if (str == null || str2 == null || (strM22346k = C5616j.m22346k(str)) == null) {
            return false;
        }
        String strValueOf = String.valueOf(Globals.m7388i0().m7568k1());
        if (str2.equals(strValueOf)) {
            str2 = strM22346k;
        }
        if (!strValueOf.equals(strM22346k)) {
            Group groupM15081r = C2950b0.m14912k().m15081r(str);
            ((NotificationManager) Globals.m7388i0().getSystemService("notification")).cancel(str, 1);
            C2907m0.m14454I().m14514x(str);
            C2907m0.m14454I().m14513w(str, true);
            if (groupM15081r != null) {
                C2950b0.m14916o().m15170i(groupM15081r.f13727n);
            }
        }
        Friend friendM15727f0 = this.f18078a.m15727f0(str2);
        if (friendM15727f0 != null && C2950b0.m14899A().m15001A(str2) != null) {
            C2950b0.m14899A().m15019k(friendM15727f0, true, true);
            return true;
        }
        return false;
    }

    /* renamed from: x0 */
    public void m20896x0(C2904l c2904l, Map<String, String> map) {
        this.f18080c.m24540f(new c(c2904l, map));
    }

    /* renamed from: y */
    public final boolean m20897y(String str, String str2) {
        Friend friendM15704L;
        if (str == null || str2 == null) {
            return false;
        }
        String strValueOf = String.valueOf(Globals.m7388i0().m7568k1());
        if (str.equals(strValueOf) && !str2.equals(strValueOf)) {
            str = str2;
        } else if (str.equals(strValueOf) || !str2.equals(strValueOf)) {
            return false;
        }
        if (C2950b0.m14899A().m15001A(str) != null || (friendM15704L = this.f18078a.m15704L(str)) == null) {
            return false;
        }
        C2950b0.m14899A().m15019k(friendM15704L, true, true);
        return true;
    }

    /* renamed from: y0 */
    public final void m20898y0(Group group, final String str, Friend friend, String str2, String str3, String str4, boolean z8, boolean z9, String str5, String str6) {
        ULogUtility.m16680p("MeetingManager", "[" + str + "] start preJoinMeeting");
        Context applicationContext = Globals.m7388i0().getApplicationContext();
        if (MeetingManager.m5621n(MeetingManager.m5615h(str))) {
            MeetingManager.m5631x(str);
            ULogUtility.m16676l("MeetingManager", "[" + str + "] meeting status check fail when pre-join meeting, not pre-join meeting.");
            if (this.f18079b.isHeld()) {
                ULogUtility.m16680p("WakeLock", "release wakeLock - meeting status check failed when pre-join meeting.");
                this.f18079b.release();
                return;
            }
            return;
        }
        NileNetwork nileNetworkM5610c = MeetingManager.m5610c(str);
        if (Globals.m7396z1()) {
            if (this.f18079b.isHeld()) {
                ULogUtility.m16680p("WakeLock", "wakeLock is already held - release before acquire");
                this.f18079b.release();
            }
            ULogUtility.m16680p("WakeLock", "acquire wakelock - acquire 30000 ms to pre-join meeting");
            this.f18079b.acquire(SsMediaSource.DEFAULT_LIVE_PRESENTATION_DELAY_MS);
        }
        Runnable runnable = new Runnable() { // from class: m3.d
            @Override // java.lang.Runnable
            public final void run() {
                this.f18075b.m20827u0(str);
            }
        };
        nileNetworkM5610c.m4965s8(new h(str, group, friend, str2, str3, str4, z9, str5, str6, runnable));
        nileNetworkM5610c.m4955n8(new i(runnable, z8, group, str, friend, str2, str3, str4, nileNetworkM5610c, z9));
        nileNetworkM5610c.m4951l8(false, null);
        nileNetworkM5610c.m4976y8(false, null);
        nileNetworkM5610c.m4906K7(new C1019i.b().m5005f(str3).m5006g(str4).m5007h(Globals.m7388i0().m7568k1().longValue()).m5008i(MeetingActivity.m6553v9(applicationContext)).m5002c(str).m5003d(MeetingActivity.m6553v9(applicationContext)).m5004e(Globals.m7388i0().m7498V0()).m5001b(TextUtils.isEmpty(Globals.m7388i0().m7442J0()) ? 0 : Integer.parseInt(Globals.m7388i0().m7442J0())).m5000a());
    }

    /* renamed from: z */
    public final boolean m20899z(String str) {
        Friend friendM15003C;
        if (str == null || (friendM15003C = C2950b0.m14899A().m15003C(str)) == null) {
            return false;
        }
        C2950b0.m14899A().m15019k(friendM15003C, false, true);
        return true;
    }

    /* renamed from: z0 */
    public final void m20900z0(String str, String str2, Group group, String str3, Friend friend, String str4, String str5, String str6, boolean z8, boolean z9, String str7, String str8) {
        Context applicationContext = Globals.m7388i0().getApplicationContext();
        boolean z10 = C5170o0.m20170e(str5) || C5170o0.m20170e(str6);
        if (!MeetingManager.m5619l()) {
            MeetingManager.m5630w(str3, MeetingManager.MeetingStatus.PRE_JOIN);
            MeetingManager.m5607G(group, str3, friend, str4, str5, str6, true, z8);
            if (!z10) {
                m20898y0(group, str3, friend, str4, str5, str6, z8, z9, str7, str8);
                return;
            }
            ULogUtility.m16680p("MeetingManager", "[" + str3 + "] no MServerAddress or MServerToken in invite event, query Do server joinMeeting API.");
            new C1260a(applicationContext);
            if (Globals.m7396z1()) {
                if (this.f18079b.isHeld()) {
                    ULogUtility.m16680p("WakeLock", "wakeLock is already held - release before acquire");
                    this.f18079b.release();
                }
                ULogUtility.m16680p("WakeLock", "acquire wakelock - acquire 30000 ms to query meeting info from Do Server");
                this.f18079b.acquire(SsMediaSource.DEFAULT_LIVE_PRESENTATION_DELAY_MS);
            }
            C1260a.m5673k(str3, null, Globals.m7388i0().m7506X()).m15439e(new g(group, str3, friend, str4, z8, z9, str7, str8, str5, str6));
            return;
        }
        ULogUtility.m16680p("MeetingManager", "[" + str3 + "] user is in meeting, meetingStatusMap:" + MeetingManager.m5611d());
        if (z8) {
            ULogUtility.m16680p("MeetingManager", "[" + str3 + "] prepareToPreJoinMeeting | handle by GCM and not allow to send busy message, return now.");
            return;
        }
        if (MeetingManager.m5620m(str3)) {
            ULogUtility.m16680p("MeetingManager", "[" + str3 + "] prepareToPreJoinMeeting | current meeting Id is same as need pre-join meeting, not send busy event/message.");
            return;
        }
        if (group == null) {
            ULogUtility.m16676l("MeetingManager", "[" + str3 + "] prepareToPreJoinMeeting | group is not exist, not send busy message.");
            return;
        }
        C1260a c1260a = new C1260a(applicationContext);
        ULogUtility.m16680p("MeetingManager", "[" + str3 + "] send busy event");
        c1260a.m5688B(group, str3, str, str2, friend.f13645c, str4, "busy");
        if (group.f13716c.equals("Dual")) {
            ULogUtility.m16680p("MeetingManager", "[" + str3 + "] send busy message");
            c1260a.m5689C(group, str3, str, str2, str4, "busy");
        }
        if (z10) {
            return;
        }
        String host = Uri.parse(str5).getHost();
        ULogUtility.m16683s("MeetingManager", "[" + str3 + "] send CalleeIsBusy http request to notify M server, MServerToken = " + str6);
        C1260a.m5686x(host, str3, str6).m15439e(new f(str3));
    }

    public C5321e() {
        this.f18078a = new FriendsClient();
        this.f18080c = new C6389z<>("XMPPEventManager");
        this.f18081d = new HashSet<>();
        this.f18079b = ((PowerManager) Globals.m7388i0().getBaseContext().getSystemService("power")).newWakeLock(1, "XMPPEventManager");
    }
}
