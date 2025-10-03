package com.cyberlink.meeting.doserver;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.cyberlink.meeting.doserver.NetworkManager;
import com.cyberlink.meeting.model.InviteeList;
import com.cyberlink.meeting.model.Meeting;
import com.cyberlink.meeting.model.PhoneExtensionData;
import com.cyberlink.meeting.model.SubscriptionInfo;
import com.cyberlink.p030U.R;
import com.cyberlink.you.Globals;
import com.cyberlink.you.chat.C2925v;
import com.cyberlink.you.chat.XMPPManager;
import com.cyberlink.you.database.C2950b0;
import com.cyberlink.you.database.MessageObj;
import com.cyberlink.you.feedback.C3031d;
import com.cyberlink.you.feedback.Model;
import com.cyberlink.you.feedback.PromisedTask;
import com.cyberlink.you.friends.Group;
import com.cyberlink.you.utility.ULogUtility;
import com.google.android.gms.plus.PlusShare;
import com.google.common.net.HttpHeaders;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.jivesoftware.smack.packet.Message;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import p116k4.C5170o0;
import p116k4.C5180s;
import p147n5.C5369g;
import p182r2.C6196d0;
import p182r2.C6201i;
import p209u2.AbstractC6381r;
import p209u2.ThreadFactoryC6373j;
import p218v2.C6456d;

/* renamed from: com.cyberlink.meeting.doserver.a */
/* loaded from: classes.dex */
public class C1260a {

    /* renamed from: e */
    public static final Long f6268e = 0L;

    /* renamed from: a */
    public final Context f6269a;

    /* renamed from: b */
    public Map<String, WeakReference<XMPPManager.InterfaceC2873x>> f6270b = new HashMap();

    /* renamed from: c */
    public final ExecutorService f6271c = Executors.newSingleThreadExecutor(new ThreadFactoryC6373j("MeetingMsgPool"));

    /* renamed from: d */
    public XMPPManager.InterfaceC2849a0 f6272d = new p();

    /* renamed from: com.cyberlink.meeting.doserver.a$a */
    public class a extends PromisedTask<NetworkManager, Void, C3031d> {

        /* renamed from: j */
        public final /* synthetic */ String f6273j;

        /* renamed from: k */
        public final /* synthetic */ String f6274k;

        /* renamed from: l */
        public final /* synthetic */ String f6275l;

        /* renamed from: m */
        public final /* synthetic */ JSONObject f6276m;

        /* renamed from: n */
        public final /* synthetic */ List f6277n;

        /* renamed from: o */
        public final /* synthetic */ String f6278o;

        /* renamed from: p */
        public final /* synthetic */ String f6279p;

        /* renamed from: q */
        public final /* synthetic */ String f6280q;

        /* renamed from: r */
        public final /* synthetic */ JSONObject f6281r;

        /* renamed from: s */
        public final /* synthetic */ Boolean f6282s;

        public a(String str, String str2, String str3, JSONObject jSONObject, List list, String str4, String str5, String str6, JSONObject jSONObject2, Boolean bool) {
            this.f6273j = str;
            this.f6274k = str2;
            this.f6275l = str3;
            this.f6276m = jSONObject;
            this.f6277n = list;
            this.f6278o = str4;
            this.f6279p = str5;
            this.f6280q = str6;
            this.f6281r = jSONObject2;
            this.f6282s = bool;
        }

        @Override // com.cyberlink.you.feedback.PromisedTask
        /* renamed from: q, reason: merged with bridge method [inline-methods] */
        public C3031d mo5659d(NetworkManager networkManager) {
            String str = networkManager.f6237a.f6251a.f6258a;
            if (str == null) {
                throw new PromisedTask.CustomErrorException("scheduleMeeting API is not initialized", NetworkManager.NetworkErrorCode.E_NOT_INITIALIZED.m5658a());
            }
            C3031d c3031d = new C3031d(str);
            c3031d.m15499c(HttpHeaders.AUTHORIZATION, "Bearer " + this.f6273j);
            c3031d.m15500d("schedule", "");
            c3031d.m15500d(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_TITLE, this.f6274k);
            if (!C5170o0.m20170e(this.f6275l)) {
                c3031d.m15500d("password", this.f6275l);
            }
            JSONObject jSONObject = this.f6276m;
            if (jSONObject != null) {
                c3031d.m15500d("invitationOnly", jSONObject.toString());
            }
            Iterator it = this.f6277n.iterator();
            while (it.hasNext()) {
                c3031d.m15500d("hostId", (Long) it.next());
            }
            c3031d.m15500d("startDate", this.f6278o);
            c3031d.m15500d("endDate", this.f6279p);
            if (!C5170o0.m20170e(this.f6280q)) {
                c3031d.m15500d(RemoteConfigConstants.RequestFieldKey.TIME_ZONE, this.f6280q);
            }
            JSONObject jSONObject2 = this.f6281r;
            if (jSONObject2 != null) {
                c3031d.m15500d("waitingRoom", jSONObject2.toString());
            }
            c3031d.m15500d("notificationEnabled", this.f6282s);
            return c3031d;
        }
    }

    /* renamed from: com.cyberlink.meeting.doserver.a$b */
    public class b extends PromisedTask<String, Void, InviteeList> {
        @Override // com.cyberlink.you.feedback.PromisedTask
        /* renamed from: q, reason: merged with bridge method [inline-methods] */
        public InviteeList mo5659d(String str) {
            if (str == null) {
                throw new PromisedTask.CustomErrorException("empty response", -2147483645);
            }
            InviteeList inviteeList = (InviteeList) C5369g.GSON.fromJson(str, InviteeList.class);
            if (inviteeList != null) {
                return inviteeList;
            }
            throw new PromisedTask.CustomErrorException("listMeetingInvitees parse error", -2147483645);
        }
    }

    /* renamed from: com.cyberlink.meeting.doserver.a$c */
    public class c extends PromisedTask<NetworkManager, Void, C3031d> {

        /* renamed from: j */
        public final /* synthetic */ String f6283j;

        /* renamed from: k */
        public final /* synthetic */ String f6284k;

        /* renamed from: l */
        public final /* synthetic */ int f6285l;

        /* renamed from: m */
        public final /* synthetic */ int f6286m;

        public c(String str, String str2, int i9, int i10) {
            this.f6283j = str;
            this.f6284k = str2;
            this.f6285l = i9;
            this.f6286m = i10;
        }

        @Override // com.cyberlink.you.feedback.PromisedTask
        /* renamed from: q, reason: merged with bridge method [inline-methods] */
        public C3031d mo5659d(NetworkManager networkManager) {
            NetworkManager.C1259f.b bVar = networkManager.f6237a.f6251a;
            if (bVar.f6260c == null) {
                throw new PromisedTask.CustomErrorException("queryMeeting API is not initialized", NetworkManager.NetworkErrorCode.E_NOT_INITIALIZED.m5658a());
            }
            C3031d c3031d = new C3031d(bVar.f6261d);
            if (!C5170o0.m20170e(this.f6283j)) {
                c3031d.m15499c(HttpHeaders.AUTHORIZATION, "Bearer " + this.f6283j);
            }
            if (!C5170o0.m20170e(this.f6284k)) {
                c3031d.m15500d("eventId", this.f6284k);
            }
            c3031d.m15500d("pageIndex", Integer.valueOf(this.f6285l));
            c3031d.m15500d("pageSize", Integer.valueOf(this.f6286m));
            c3031d.m15500d("full", "");
            return c3031d;
        }
    }

    /* renamed from: com.cyberlink.meeting.doserver.a$d */
    public class d extends PromisedTask.AbstractC3021b<InviteeList> {

        /* renamed from: j */
        public final /* synthetic */ List f6287j;

        /* renamed from: k */
        public final /* synthetic */ int f6288k;

        /* renamed from: l */
        public final /* synthetic */ String f6289l;

        /* renamed from: m */
        public final /* synthetic */ String f6290m;

        /* renamed from: n */
        public final /* synthetic */ AbstractC6381r f6291n;

        public d(List list, int i9, String str, String str2, AbstractC6381r abstractC6381r) {
            this.f6287j = list;
            this.f6288k = i9;
            this.f6289l = str;
            this.f6290m = str2;
            this.f6291n = abstractC6381r;
        }

        @Override // com.cyberlink.you.feedback.PromisedTask
        /* renamed from: k */
        public void mo5702k(int i9, String str) {
            super.mo5702k(i9, str);
            this.f6291n.m24508f(Integer.valueOf(i9));
        }

        @Override // com.cyberlink.you.feedback.PromisedTask.AbstractC3021b
        /* renamed from: r, reason: merged with bridge method [inline-methods] */
        public void mo5703q(InviteeList inviteeList) {
            ArrayList<InviteeList.Invitee> arrayList;
            if (inviteeList == null || (arrayList = inviteeList.results) == null) {
                return;
            }
            this.f6287j.addAll(arrayList);
            int i9 = this.f6288k;
            if ((i9 + 1) * 50 < inviteeList.totalSize) {
                C1260a.m5679q(this.f6289l, this.f6290m, i9 + 1, this.f6287j, this.f6291n);
            } else {
                this.f6291n.m24506d(this.f6287j);
            }
        }
    }

    /* renamed from: com.cyberlink.meeting.doserver.a$e */
    public class e extends PromisedTask<String, Void, PhoneExtensionData> {
        @Override // com.cyberlink.you.feedback.PromisedTask
        /* renamed from: q, reason: merged with bridge method [inline-methods] */
        public PhoneExtensionData mo5659d(String str) {
            if (str == null) {
                throw new PromisedTask.CustomErrorException("empty response", -2147483645);
            }
            try {
                PhoneExtensionData phoneExtensionData = (PhoneExtensionData) C5369g.GSON.fromJson(str, PhoneExtensionData.class);
                if (phoneExtensionData != null) {
                    return phoneExtensionData;
                }
                throw new PromisedTask.CustomErrorException("queryUserByExtension parse error", -2147483645);
            } catch (Exception unused) {
                throw new PromisedTask.CustomErrorException("queryUserByExtension jason response parse error", -2147483645);
            }
        }
    }

    /* renamed from: com.cyberlink.meeting.doserver.a$f */
    public class f extends PromisedTask<NetworkManager, Void, C3031d> {

        /* renamed from: j */
        public final /* synthetic */ String f6292j;

        /* renamed from: k */
        public final /* synthetic */ String f6293k;

        public f(String str, String str2) {
            this.f6292j = str;
            this.f6293k = str2;
        }

        @Override // com.cyberlink.you.feedback.PromisedTask
        /* renamed from: q, reason: merged with bridge method [inline-methods] */
        public C3031d mo5659d(NetworkManager networkManager) {
            String str = networkManager.f6237a.f6251a.f6263f;
            if (str == null) {
                throw new PromisedTask.CustomErrorException("queryUserByExtension API is not initialized", NetworkManager.NetworkErrorCode.E_NOT_INITIALIZED.m5658a());
            }
            C3031d c3031d = new C3031d(str);
            if (!C5170o0.m20170e(this.f6292j)) {
                c3031d.m15499c(HttpHeaders.AUTHORIZATION, "Bearer " + this.f6292j);
            }
            c3031d.m15500d("extension", this.f6293k);
            return c3031d;
        }
    }

    /* renamed from: com.cyberlink.meeting.doserver.a$g */
    public class g extends PromisedTask<String, Void, SubscriptionInfo> {
        @Override // com.cyberlink.you.feedback.PromisedTask
        /* renamed from: q, reason: merged with bridge method [inline-methods] */
        public SubscriptionInfo mo5659d(String str) {
            if (str == null) {
                throw new PromisedTask.CustomErrorException("empty response", -2147483645);
            }
            try {
                SubscriptionInfo subscriptionInfo = (SubscriptionInfo) C5369g.GSON.fromJson(str, SubscriptionInfo.class);
                if (subscriptionInfo != null) {
                    return subscriptionInfo;
                }
                throw new PromisedTask.CustomErrorException("getSubscriptionInfo parse error", -2147483645);
            } catch (Exception unused) {
                throw new PromisedTask.CustomErrorException("getSubscriptionInfo jason response parse error", -2147483645);
            }
        }
    }

    /* renamed from: com.cyberlink.meeting.doserver.a$h */
    public class h extends PromisedTask<NetworkManager, Void, C3031d> {

        /* renamed from: j */
        public final /* synthetic */ String f6294j;

        public h(String str) {
            this.f6294j = str;
        }

        @Override // com.cyberlink.you.feedback.PromisedTask
        /* renamed from: q, reason: merged with bridge method [inline-methods] */
        public C3031d mo5659d(NetworkManager networkManager) {
            String str = networkManager.f6237a.f6253c.f6267a;
            if (str == null) {
                throw new PromisedTask.CustomErrorException("queryFaceMeToken API is not initialized", NetworkManager.NetworkErrorCode.E_NOT_INITIALIZED.m5658a());
            }
            C3031d c3031d = new C3031d(str);
            if (!C5170o0.m20170e(this.f6294j)) {
                c3031d.m15499c(HttpHeaders.AUTHORIZATION, "Bearer " + this.f6294j);
            }
            return c3031d;
        }
    }

    /* renamed from: com.cyberlink.meeting.doserver.a$i */
    public class i extends PromisedTask<String, Void, Meeting> {
        @Override // com.cyberlink.you.feedback.PromisedTask
        /* renamed from: q, reason: merged with bridge method [inline-methods] */
        public Meeting mo5659d(String str) {
            if (str == null) {
                throw new PromisedTask.CustomErrorException("empty response", -2147483645);
            }
            Meeting meeting = (Meeting) Model.parseFromJSON(Meeting.class, str);
            if (meeting != null) {
                return meeting;
            }
            throw new PromisedTask.CustomErrorException("createMeeting jason response parse error", -2147483645);
        }
    }

    /* renamed from: com.cyberlink.meeting.doserver.a$j */
    public class j extends PromisedTask<String, Void, JSONArray> {
        @Override // com.cyberlink.you.feedback.PromisedTask
        /* renamed from: q, reason: merged with bridge method [inline-methods] */
        public JSONArray mo5659d(String str) {
            if (str == null) {
                throw new PromisedTask.CustomErrorException("empty response", -2147483645);
            }
            try {
                return new JSONObject(str).getJSONArray("results");
            } catch (Exception unused) {
                throw new PromisedTask.CustomErrorException("queryPhoneNumberList jason response parse error", -2147483645);
            }
        }
    }

    /* renamed from: com.cyberlink.meeting.doserver.a$k */
    public class k extends PromisedTask<NetworkManager, Void, C3031d> {

        /* renamed from: j */
        public final /* synthetic */ String f6295j;

        public k(String str) {
            this.f6295j = str;
        }

        @Override // com.cyberlink.you.feedback.PromisedTask
        /* renamed from: q, reason: merged with bridge method [inline-methods] */
        public C3031d mo5659d(NetworkManager networkManager) {
            String str = networkManager.f6237a.f6251a.f6264g;
            if (str == null) {
                throw new PromisedTask.CustomErrorException("queryPhoneNumberList API is not initialized", NetworkManager.NetworkErrorCode.E_NOT_INITIALIZED.m5658a());
            }
            C3031d c3031d = new C3031d(str);
            if (!C5170o0.m20170e(this.f6295j)) {
                c3031d.m15499c(HttpHeaders.AUTHORIZATION, "Bearer " + this.f6295j);
            }
            return c3031d;
        }
    }

    /* renamed from: com.cyberlink.meeting.doserver.a$l */
    public class l extends PromisedTask<String, Void, String> {
        @Override // com.cyberlink.you.feedback.PromisedTask
        /* renamed from: q, reason: merged with bridge method [inline-methods] */
        public String mo5659d(String str) {
            if (str == null) {
                throw new PromisedTask.CustomErrorException("empty response", -2147483645);
            }
            try {
                return new JSONObject(str).getString("url");
            } catch (Exception unused) {
                throw new PromisedTask.CustomErrorException("queryRollCallUrl jason response parse error", -2147483645);
            }
        }
    }

    /* renamed from: com.cyberlink.meeting.doserver.a$m */
    public class m extends PromisedTask<NetworkManager, Void, C3031d> {

        /* renamed from: j */
        public final /* synthetic */ String f6296j;

        /* renamed from: k */
        public final /* synthetic */ boolean f6297k;

        /* renamed from: l */
        public final /* synthetic */ long f6298l;

        public m(String str, boolean z8, long j9) {
            this.f6296j = str;
            this.f6297k = z8;
            this.f6298l = j9;
        }

        @Override // com.cyberlink.you.feedback.PromisedTask
        /* renamed from: q, reason: merged with bridge method [inline-methods] */
        public C3031d mo5659d(NetworkManager networkManager) {
            String str = networkManager.f6237a.f6254d.f6265a;
            if (str == null) {
                throw new PromisedTask.CustomErrorException("queryRollCallUrl API is not initialized", NetworkManager.NetworkErrorCode.E_NOT_INITIALIZED.m5658a());
            }
            C3031d c3031d = new C3031d(str);
            if (!C5170o0.m20170e(this.f6296j)) {
                c3031d.m15499c(HttpHeaders.AUTHORIZATION, "Bearer " + this.f6296j);
            }
            if (this.f6297k) {
                c3031d.m15500d("meeting", "");
                c3031d.m15500d("eventId", Long.valueOf(this.f6298l));
            } else {
                c3031d.m15500d("liveId", Long.valueOf(this.f6298l));
            }
            return c3031d;
        }
    }

    /* renamed from: com.cyberlink.meeting.doserver.a$n */
    public class n extends PromisedTask<String, Void, String> {
        @Override // com.cyberlink.you.feedback.PromisedTask
        /* renamed from: q, reason: merged with bridge method [inline-methods] */
        public String mo5659d(String str) {
            if (str == null) {
                throw new PromisedTask.CustomErrorException("empty response", -2147483645);
            }
            try {
                return new JSONObject(str).getString("token");
            } catch (Exception unused) {
                throw new PromisedTask.CustomErrorException("querySSOSession jason response parse error", -2147483645);
            }
        }
    }

    /* renamed from: com.cyberlink.meeting.doserver.a$o */
    public class o extends PromisedTask<NetworkManager, Void, C3031d> {

        /* renamed from: j */
        public final /* synthetic */ String f6299j;

        public o(String str) {
            this.f6299j = str;
        }

        @Override // com.cyberlink.you.feedback.PromisedTask
        /* renamed from: q, reason: merged with bridge method [inline-methods] */
        public C3031d mo5659d(NetworkManager networkManager) {
            String str = networkManager.f6237a.f6255e.f6266a;
            if (str == null) {
                throw new PromisedTask.CustomErrorException("querySSOSession API is not initialized", NetworkManager.NetworkErrorCode.E_NOT_INITIALIZED.m5658a());
            }
            C3031d c3031d = new C3031d(str);
            if (!C5170o0.m20170e(this.f6299j)) {
                c3031d.m15499c(HttpHeaders.AUTHORIZATION, "Bearer " + this.f6299j);
            }
            return c3031d;
        }
    }

    /* renamed from: com.cyberlink.meeting.doserver.a$p */
    public class p implements XMPPManager.InterfaceC2849a0 {
        public p() {
        }

        @Override // com.cyberlink.you.chat.XMPPManager.InterfaceC2849a0
        /* renamed from: F */
        public void mo5716F(String str) {
            m5717b(str, false);
        }

        /* renamed from: b */
        public final void m5717b(String str, boolean z8) {
            WeakReference weakReference = (WeakReference) C1260a.this.f6270b.get(str);
            if (weakReference == null) {
                return;
            }
            XMPPManager.InterfaceC2873x interfaceC2873x = (XMPPManager.InterfaceC2873x) weakReference.get();
            if (interfaceC2873x == null) {
                ULogUtility.m16684t("MeetingClient", "Invite message server ack and callback gone: " + str);
            } else if (z8) {
                ULogUtility.m16680p("MeetingClient", "Invite message server ack and callback success: " + str);
                interfaceC2873x.onSuccess();
            } else {
                ULogUtility.m16680p("MeetingClient", "Invite message server nack and callback fail: " + str);
                interfaceC2873x.mo5816a();
            }
            C1260a.this.f6270b.remove(str);
            XMPPManager.m14184g0().m14232Y0(C1260a.this.f6272d);
        }

        @Override // com.cyberlink.you.chat.XMPPManager.InterfaceC2849a0
        /* renamed from: p */
        public void mo5718p(String str, Date date) {
            m5717b(str, true);
        }
    }

    /* renamed from: com.cyberlink.meeting.doserver.a$q */
    public class q extends PromisedTask<NetworkManager, Void, C3031d> {

        /* renamed from: j */
        public final /* synthetic */ String f6301j;

        /* renamed from: k */
        public final /* synthetic */ String f6302k;

        /* renamed from: l */
        public final /* synthetic */ long f6303l;

        /* renamed from: m */
        public final /* synthetic */ String f6304m;

        /* renamed from: n */
        public final /* synthetic */ String f6305n;

        /* renamed from: o */
        public final /* synthetic */ long f6306o;

        /* renamed from: p */
        public final /* synthetic */ boolean f6307p;

        /* renamed from: q */
        public final /* synthetic */ String f6308q;

        public q(String str, String str2, long j9, String str3, String str4, long j10, boolean z8, String str5) {
            this.f6301j = str;
            this.f6302k = str2;
            this.f6303l = j9;
            this.f6304m = str3;
            this.f6305n = str4;
            this.f6306o = j10;
            this.f6307p = z8;
            this.f6308q = str5;
        }

        @Override // com.cyberlink.you.feedback.PromisedTask
        /* renamed from: q, reason: merged with bridge method [inline-methods] */
        public C3031d mo5659d(NetworkManager networkManager) {
            String str = networkManager.f6237a.f6251a.f6258a;
            if (str == null) {
                throw new PromisedTask.CustomErrorException("createMeeting API is not initialized", NetworkManager.NetworkErrorCode.E_NOT_INITIALIZED.m5658a());
            }
            C3031d c3031d = new C3031d(str);
            c3031d.m15499c(HttpHeaders.AUTHORIZATION, "Bearer " + this.f6301j);
            c3031d.m15500d(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_TITLE, this.f6302k);
            long j9 = this.f6303l;
            Long l9 = C1260a.f6268e;
            if (j9 != l9.longValue()) {
                c3031d.m15500d("groupId", Long.valueOf(this.f6303l));
            }
            if (!C5170o0.m20170e(this.f6304m)) {
                c3031d.m15500d("password", this.f6304m);
            }
            if (!C5170o0.m20170e(this.f6305n)) {
                c3031d.m15500d("callType", this.f6305n);
            }
            if (this.f6306o != l9.longValue()) {
                c3031d.m15500d("calleeId", Long.valueOf(this.f6306o));
            }
            if (this.f6307p) {
                c3031d.m15500d("voipEnabled", Boolean.TRUE);
            }
            if (!C5170o0.m20170e(this.f6308q)) {
                c3031d.m15500d(RemoteConfigConstants.RequestFieldKey.TIME_ZONE, this.f6308q);
            }
            return c3031d;
        }
    }

    /* renamed from: com.cyberlink.meeting.doserver.a$r */
    public class r extends PromisedTask<String, Void, Meeting> {
        @Override // com.cyberlink.you.feedback.PromisedTask
        /* renamed from: q, reason: merged with bridge method [inline-methods] */
        public Meeting mo5659d(String str) {
            if (str == null) {
                throw new PromisedTask.CustomErrorException("empty response", -2147483645);
            }
            Meeting meeting = (Meeting) Model.parseFromJSON(Meeting.class, str);
            if (meeting != null) {
                return meeting;
            }
            throw new PromisedTask.CustomErrorException("joinMeeting jason response parse error", -2147483645);
        }
    }

    /* renamed from: com.cyberlink.meeting.doserver.a$s */
    public class s extends PromisedTask<NetworkManager, Void, C3031d> {

        /* renamed from: j */
        public final /* synthetic */ String f6309j;

        /* renamed from: k */
        public final /* synthetic */ String f6310k;

        /* renamed from: l */
        public final /* synthetic */ String f6311l;

        /* renamed from: m */
        public final /* synthetic */ String f6312m;

        /* renamed from: n */
        public final /* synthetic */ String f6313n;

        /* renamed from: o */
        public final /* synthetic */ List f6314o;

        /* renamed from: p */
        public final /* synthetic */ int f6315p;

        /* renamed from: q */
        public final /* synthetic */ int f6316q;

        /* renamed from: r */
        public final /* synthetic */ String f6317r;

        public s(String str, String str2, String str3, String str4, String str5, List list, int i9, int i10, String str6) {
            this.f6309j = str;
            this.f6310k = str2;
            this.f6311l = str3;
            this.f6312m = str4;
            this.f6313n = str5;
            this.f6314o = list;
            this.f6315p = i9;
            this.f6316q = i10;
            this.f6317r = str6;
        }

        @Override // com.cyberlink.you.feedback.PromisedTask
        /* renamed from: q, reason: merged with bridge method [inline-methods] */
        public C3031d mo5659d(NetworkManager networkManager) {
            String str = networkManager.f6237a.f6251a.f6259b;
            if (str == null) {
                throw new PromisedTask.CustomErrorException("joinMeeting API is not initialized", NetworkManager.NetworkErrorCode.E_NOT_INITIALIZED.m5658a());
            }
            C3031d c3031d = new C3031d(str);
            c3031d.m15500d("eventId", this.f6309j);
            if (!C5170o0.m20170e(this.f6310k)) {
                c3031d.m15500d("password", this.f6310k);
            }
            if (!C5170o0.m20170e(this.f6311l)) {
                c3031d.m15500d("ltiAccessToken", this.f6311l);
            }
            if (!C5170o0.m20170e(this.f6312m)) {
                c3031d.m15499c(HttpHeaders.AUTHORIZATION, "Bearer " + this.f6312m);
            }
            if (TextUtils.isEmpty(this.f6313n)) {
                List list = this.f6314o;
                if (list != null && !list.isEmpty() && this.f6315p != -1 && this.f6316q != -1) {
                    Iterator it = this.f6314o.iterator();
                    while (it.hasNext()) {
                        c3031d.m15500d("faceFeatures", (String) it.next());
                    }
                    c3031d.m15500d("featureType", Integer.valueOf(this.f6315p));
                    c3031d.m15500d("featureSubType", Integer.valueOf(this.f6316q));
                }
            } else {
                c3031d.m15500d("userEmail", this.f6313n);
                c3031d.m15500d("userPassword", this.f6317r);
            }
            return c3031d;
        }
    }

    /* renamed from: com.cyberlink.meeting.doserver.a$t */
    public class t extends PromisedTask<String, Void, Meeting> {
        @Override // com.cyberlink.you.feedback.PromisedTask
        /* renamed from: q, reason: merged with bridge method [inline-methods] */
        public Meeting mo5659d(String str) {
            if (str == null) {
                throw new PromisedTask.CustomErrorException("empty response", -2147483645);
            }
            Meeting meeting = (Meeting) Model.parseFromJSON(Meeting.class, str);
            if (meeting != null) {
                return meeting;
            }
            throw new PromisedTask.CustomErrorException("queryMeeting jason response parse error", -2147483645);
        }
    }

    /* renamed from: com.cyberlink.meeting.doserver.a$u */
    public class u extends PromisedTask<NetworkManager, Void, C3031d> {

        /* renamed from: j */
        public final /* synthetic */ String f6318j;

        public u(String str) {
            this.f6318j = str;
        }

        @Override // com.cyberlink.you.feedback.PromisedTask
        /* renamed from: q, reason: merged with bridge method [inline-methods] */
        public C3031d mo5659d(NetworkManager networkManager) {
            String str = networkManager.f6237a.f6251a.f6260c;
            if (str == null) {
                throw new PromisedTask.CustomErrorException("queryMeeting API is not initialized", NetworkManager.NetworkErrorCode.E_NOT_INITIALIZED.m5658a());
            }
            C3031d c3031d = new C3031d(str);
            if (!C5170o0.m20170e(this.f6318j)) {
                c3031d.m15500d("eventId", this.f6318j);
            }
            return c3031d;
        }
    }

    /* renamed from: com.cyberlink.meeting.doserver.a$v */
    public class v extends PromisedTask<String, Void, Boolean> {
        @Override // com.cyberlink.you.feedback.PromisedTask
        /* renamed from: q, reason: merged with bridge method [inline-methods] */
        public Boolean mo5659d(String str) {
            if (str != null) {
                return Boolean.valueOf("ok".equals(str));
            }
            throw new PromisedTask.CustomErrorException("empty response", -2147483645);
        }
    }

    /* renamed from: com.cyberlink.meeting.doserver.a$w */
    public class w extends PromisedTask<NetworkManager, Void, C3031d> {

        /* renamed from: j */
        public final /* synthetic */ String f6319j;

        /* renamed from: k */
        public final /* synthetic */ String f6320k;

        /* renamed from: l */
        public final /* synthetic */ String f6321l;

        public w(String str, String str2, String str3) {
            this.f6319j = str;
            this.f6320k = str2;
            this.f6321l = str3;
        }

        @Override // com.cyberlink.you.feedback.PromisedTask
        /* renamed from: q, reason: merged with bridge method [inline-methods] */
        public C3031d mo5659d(NetworkManager networkManager) {
            C3031d c3031d = new C3031d("https://" + this.f6319j + "/CLM-Mserver-BKC/CalleeIsBusy");
            c3031d.m15499c("event_id", this.f6320k);
            c3031d.m15499c("join_token", this.f6321l);
            c3031d.m15499c("uid", Globals.m7388i0().m7568k1());
            return c3031d;
        }
    }

    /* renamed from: com.cyberlink.meeting.doserver.a$x */
    public class x extends PromisedTask<String, Void, Meeting> {
        @Override // com.cyberlink.you.feedback.PromisedTask
        /* renamed from: q, reason: merged with bridge method [inline-methods] */
        public Meeting mo5659d(String str) {
            if (str == null) {
                throw new PromisedTask.CustomErrorException("empty response", -2147483645);
            }
            Meeting meeting = (Meeting) Model.parseFromJSON(Meeting.class, str);
            if (meeting != null) {
                return meeting;
            }
            throw new PromisedTask.CustomErrorException("scheduleMeeting jason response parse error", -2147483645);
        }
    }

    /* renamed from: com.cyberlink.meeting.doserver.a$y */
    public static class y implements Runnable {

        /* renamed from: b */
        public MessageObj f6322b;

        /* renamed from: c */
        public String f6323c;

        /* renamed from: d */
        public String f6324d;

        /* renamed from: e */
        public Group f6325e;

        /* renamed from: f */
        public boolean f6326f;

        public y(MessageObj messageObj, Group group, String str, boolean z8) {
            this.f6322b = messageObj;
            this.f6325e = group;
            this.f6323c = str;
            this.f6326f = z8;
            String strM14747I = messageObj.m14747I("eventType");
            this.f6324d = strM14747I;
            if (strM14747I == null) {
                this.f6324d = "Unknown Event";
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            Message messageM14593f = C2925v.m14593f(this.f6325e.f13716c, this.f6323c, this.f6322b);
            if (!this.f6325e.f13716c.equals("Dual") && this.f6326f) {
                messageM14593f.m22100Z(Message.Type.chat);
            }
            XMPPManager.m14184g0().m14241e1(messageM14593f, null);
        }
    }

    public C1260a(Context context) {
        this.f6269a = context;
    }

    /* renamed from: e */
    public static PromisedTask<?, ?, Meeting> m5670e(String str, String str2, long j9, String str3, String str4, String str5, long j10, boolean z8) {
        return NetworkManager.m5646h().m15447o(new q(str2, str, j9, str3, str5, j10, z8, str4)).m15447o(NetworkManager.m5653r(10)).m15447o(new i());
    }

    /* renamed from: h */
    public static int m5671h(int i9, String str) {
        if (i9 != 403 || C5170o0.m20170e(str)) {
            if (i9 == 404) {
                return R.string.clm_error_invalid_id;
            }
            if (!C6456d.m24714D().m24748G()) {
                return R.string.error_server_response;
            }
        } else {
            if (str.contains("Incorrect password")) {
                return R.string.clm_error_pwd;
            }
            if (str.contains("Meeting has ended")) {
                return R.string.clm_error_meeting_ended;
            }
            if (str.contains("Require sign in")) {
                return R.string.clm_error_sign_in;
            }
            if (str.contains("You are not invited to the meeting")) {
                return R.string.clm_error_not_invited;
            }
            if (str.contains("Require face verification")) {
                return R.string.clm_error_face_verification;
            }
            if (str.contains("Face verification is not supported")) {
                return R.string.clm_error_face_support;
            }
            if (str.contains("Invalid face")) {
                return R.string.clm_error_face_invalid;
            }
            if (str.contains("Invalid user")) {
                return R.string.clm_error_user_invalid;
            }
        }
        return R.string.clm_error_old_version;
    }

    /* renamed from: i */
    public static PromisedTask<?, ?, SubscriptionInfo> m5672i(String str) {
        return NetworkManager.m5646h().m15447o(new h(str)).m15447o(NetworkManager.m5651p()).m15447o(new g());
    }

    /* renamed from: k */
    public static PromisedTask<?, ?, Meeting> m5673k(String str, String str2, String str3) {
        return m5674l(str3, str, str2, null, -1, -1, null, null);
    }

    /* renamed from: l */
    public static PromisedTask<?, ?, Meeting> m5674l(String str, String str2, String str3, List<String> list, int i9, int i10, String str4, String str5) {
        return m5676n(str, str2, str3, null, list, i10, i10, str4, str5);
    }

    /* renamed from: m */
    public static PromisedTask<?, ?, Meeting> m5675m(String str, String str2, String str3, String str4) {
        return m5676n(str4, str, str2, str3, null, -1, -1, null, null);
    }

    /* renamed from: n */
    public static PromisedTask<?, ?, Meeting> m5676n(String str, String str2, String str3, String str4, List<String> list, int i9, int i10, String str5, String str6) {
        return NetworkManager.m5646h().m15447o(new s(str2, str3, str4, str, str5, list, i9, i10, str6)).m15447o(NetworkManager.m5652q()).m15447o(new r());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: o */
    public /* synthetic */ void m5677o(List list, String str) {
        ArrayList arrayList = new ArrayList();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            MessageObj messageObj = (MessageObj) it.next();
            messageObj.m14762X("2");
            m5696j(messageObj);
            C2950b0.m14916o().m15157B(messageObj);
            C5180s.m20256i(this.f6269a, messageObj);
            arrayList.add(new C6201i(str, messageObj));
        }
        C6196d0.m23692d().m23699i(arrayList);
    }

    /* renamed from: p */
    public static PromisedTask<?, ?, InviteeList> m5678p(String str, String str2, int i9, int i10) {
        return NetworkManager.m5646h().m15447o(new c(str, str2, i9, i10)).m15447o(NetworkManager.m5652q()).m15447o(new b());
    }

    /* renamed from: q */
    public static void m5679q(String str, String str2, int i9, List<InviteeList.Invitee> list, AbstractC6381r<List<InviteeList.Invitee>, Integer> abstractC6381r) {
        m5678p(str, str2, i9, 50).m15439e(new d(list, i9, str, str2, abstractC6381r));
    }

    /* renamed from: r */
    public static PromisedTask<?, ?, Meeting> m5680r(String str) {
        return NetworkManager.m5646h().m15447o(new u(str)).m15447o(NetworkManager.m5652q()).m15447o(new t());
    }

    /* renamed from: s */
    public static PromisedTask<?, ?, JSONArray> m5681s(String str) {
        return NetworkManager.m5646h().m15447o(new k(str)).m15447o(NetworkManager.m5651p()).m15447o(new j());
    }

    /* renamed from: t */
    public static PromisedTask<?, ?, String> m5682t(String str, long j9, boolean z8) {
        return NetworkManager.m5646h().m15447o(new m(str, z8, j9)).m15447o(NetworkManager.m5651p()).m15447o(new l());
    }

    /* renamed from: u */
    public static PromisedTask<?, ?, String> m5683u(String str) {
        return NetworkManager.m5646h().m15447o(new o(str)).m15447o(NetworkManager.m5651p()).m15447o(new n());
    }

    /* renamed from: v */
    public static PromisedTask<?, ?, PhoneExtensionData> m5684v(String str, String str2) {
        return NetworkManager.m5646h().m15447o(new f(str, str2)).m15447o(NetworkManager.m5651p()).m15447o(new e());
    }

    /* renamed from: w */
    public static PromisedTask<?, ?, Meeting> m5685w(String str, String str2, String str3, JSONObject jSONObject, List<Long> list, String str4, String str5, String str6, JSONObject jSONObject2, Boolean bool) {
        return NetworkManager.m5646h().m15447o(new a(str, str2, str3, jSONObject, list, str4, str5, str6, jSONObject2, bool)).m15447o(NetworkManager.m5652q()).m15447o(new x());
    }

    /* renamed from: x */
    public static PromisedTask<?, ?, Boolean> m5686x(String str, String str2, String str3) {
        return NetworkManager.m5646h().m15447o(new w(str, str2, str3)).m15447o(NetworkManager.m5652q()).m15447o(new v());
    }

    /* renamed from: A */
    public void m5687A(Group group, String str, String str2, String str3, String str4, String str5) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("eventType", "client.rtc.hangup");
            jSONObject.put("callerId", str2);
            jSONObject.put("calleeId", str3);
            jSONObject.put("callId", str);
            jSONObject.put("status", m5693d(str5));
            jSONObject.put("statusV2", str5);
            jSONObject.put("callType", str4);
            MessageObj messageObjM14558I = C2925v.m14558I(String.valueOf(group.f13727n), MessageObj.MessageType.RTC, jSONObject.toString(), 0, null);
            this.f6271c.execute(new y(messageObjM14558I, group, group.f13723j, false));
            ULogUtility.m16680p("MeetingClient", "sendHangupMeetingEventToCallee:" + messageObjM14558I);
        } catch (JSONException e9) {
            ULogUtility.m16676l("MeetingClient", "sendHangupMeetingEventToCallee prepare jsonObj error : " + e9);
        }
    }

    /* renamed from: B */
    public void m5688B(Group group, String str, String str2, String str3, long j9, String str4, String str5) {
        String str6;
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("eventType", "client.rtc.hangup");
            jSONObject.put("callerId", str2);
            jSONObject.put("calleeId", str3);
            jSONObject.put("callId", str);
            jSONObject.put("status", m5693d(str5));
            jSONObject.put("statusV2", str5);
            jSONObject.put("callType", str4);
            MessageObj messageObjM14558I = C2925v.m14558I(String.valueOf(group.f13727n), MessageObj.MessageType.RTC, jSONObject.toString(), 0, null);
            if (group.f13716c.equals("Dual")) {
                str6 = group.f13723j;
            } else {
                str6 = group.f13723j + "/" + j9;
            }
            this.f6271c.execute(new y(messageObjM14558I, group, str6, true));
            ULogUtility.m16680p("MeetingClient", "sendHangupMeetingEventToCallerAndOtherPlatform:" + messageObjM14558I);
        } catch (JSONException e9) {
            ULogUtility.m16676l("MeetingClient", "sendHangupMeetingEventToCallerAndOtherPlatform prepare jsonObj error : " + e9);
        }
    }

    /* renamed from: C */
    public void m5689C(Group group, String str, String str2, String str3, String str4, String str5) {
        MessageObj messageObjM5694f = m5694f(group, str, str2, str3, str4, str5);
        if (messageObjM5694f == null) {
            Log.e("MeetingClient", "sendHangupMeetingMessage, gen hangupMeetingMessage fail");
            return;
        }
        String str6 = group.f13723j;
        ArrayList arrayList = new ArrayList();
        arrayList.add(messageObjM5694f);
        m5697y(str6, arrayList);
        ULogUtility.m16680p("MeetingClient", "sendHangupMeetingMessage:" + messageObjM5694f);
    }

    /* renamed from: D */
    public void m5690D(Group group, String str, String str2, String str3, String str4, XMPPManager.InterfaceC2873x interfaceC2873x) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("eventType", "client.rtc.invite");
            jSONObject.put("callerId", Globals.m7388i0().m7587o0());
            jSONObject.put("calleeId", group.f13723j);
            jSONObject.put("callId", str);
            jSONObject.put("callType", str2);
            jSONObject.put("MServerAddr", str3);
            jSONObject.put("MServerToken", str4);
            MessageObj messageObjM14558I = C2925v.m14558I(String.valueOf(group.f13727n), MessageObj.MessageType.RTC, jSONObject.toString(), 0, null);
            this.f6270b.put(messageObjM14558I.m14777o(), new WeakReference<>(interfaceC2873x));
            XMPPManager.m14184g0().m14232Y0(this.f6272d);
            XMPPManager.m14184g0().m14206G(this.f6272d);
            this.f6271c.execute(new y(messageObjM14558I, group, group.f13723j, false));
            ULogUtility.m16680p("MeetingClient", "sendInviteMeetingEvent:" + messageObjM14558I);
        } catch (JSONException e9) {
            ULogUtility.m16676l("MeetingClient", "sendInviteMeetingEvent prepare jsonObj error : " + e9);
        }
    }

    /* renamed from: E */
    public void m5691E(Group group, String str, String str2, String str3, String str4, long j9) {
        String str5;
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("eventType", "client.rtc.pickup");
            jSONObject.put("callerId", str2);
            jSONObject.put("calleeId", str3);
            jSONObject.put("callId", str);
            jSONObject.put("callType", str4);
            if (group.f13716c.equals("Dual")) {
                str5 = group.f13723j;
            } else {
                str5 = group.f13723j + "/" + j9;
            }
            MessageObj messageObjM14558I = C2925v.m14558I(String.valueOf(group.f13727n), MessageObj.MessageType.RTC, jSONObject.toString(), 0, null);
            this.f6271c.execute(new y(messageObjM14558I, group, str5, true));
            ULogUtility.m16680p("MeetingClient", "sendPickupMeetingEvent:" + messageObjM14558I);
        } catch (JSONException e9) {
            ULogUtility.m16676l("MeetingClient", "sendPickupMeetingEvent prepare jsonObj error : " + e9);
        }
    }

    /* renamed from: F */
    public void m5692F(Group group, String str) {
        MessageObj messageObjM5695g = m5695g(group, str);
        if (messageObjM5695g == null) {
            Log.e("MeetingClient", "sendHangupMeetingMessage gen sendRejectMessage fail");
            return;
        }
        String str2 = group.f13723j;
        ArrayList arrayList = new ArrayList();
        arrayList.add(messageObjM5695g);
        m5697y(str2, arrayList);
        ULogUtility.m16680p("MeetingClient", "sendRejectMessage:" + messageObjM5695g);
    }

    /* renamed from: d */
    public final String m5693d(String str) {
        return "unreached".equals(str) ? "timeout" : str;
    }

    /* renamed from: f */
    public final MessageObj m5694f(Group group, String str, String str2, String str3, String str4, String str5) throws JSONException {
        if (group == null) {
            Log.e("MeetingClient", "genHangUpMeetingMessage, group is null");
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("callerId", str2);
            jSONObject.put("calleeId", str3);
            jSONObject.put("callId", str);
            jSONObject.put("status", m5693d(str5));
            jSONObject.put("statusV2", str5);
            jSONObject.put("duration", 0);
            jSONObject.put("callType", str4);
        } catch (JSONException e9) {
            ULogUtility.m16676l("MeetingClient", "sendHangupMeetingMessage prepare jsonObj error : " + e9);
        }
        MessageObj messageObjM14558I = C2925v.m14558I(String.valueOf(group.f13727n), MessageObj.MessageType.Call, jSONObject.toString(), 0, null);
        messageObjM14558I.m14758T(str);
        return messageObjM14558I;
    }

    /* renamed from: g */
    public final MessageObj m5695g(Group group, String str) {
        if (group != null) {
            return C2925v.m14558I(String.valueOf(group.f13727n), MessageObj.MessageType.Text, str, 0, null);
        }
        Log.e("MeetingClient", "genSendRejectMessage, group is null");
        return null;
    }

    /* renamed from: j */
    public final void m5696j(MessageObj messageObj) {
        if (XMPPManager.m14184g0().m14204A0() || messageObj == null) {
            return;
        }
        if (Globals.m7388i0().m7587o0().equals(messageObj.m14747I("calleeId"))) {
            messageObj.m14762X("0");
        }
    }

    /* renamed from: y */
    public final void m5697y(final String str, final List<MessageObj> list) {
        this.f6271c.execute(new Runnable() { // from class: n2.a
            @Override // java.lang.Runnable
            public final void run() {
                this.f18245b.m5677o(list, str);
            }
        });
    }

    /* renamed from: z */
    public void m5698z(Group group, String str, String str2, String str3, String str4, String str5, String str6) {
        ArrayList arrayList = new ArrayList();
        MessageObj messageObjM5694f = m5694f(group, str, str2, str3, str4, str5);
        MessageObj messageObjM5695g = m5695g(group, str6);
        if (messageObjM5694f != null) {
            arrayList.add(messageObjM5694f);
        } else {
            Log.e("MeetingClient", "sendHangupMeetingAndRejectMessage, gen hangupMeetingMessage fail");
        }
        if (messageObjM5695g != null) {
            arrayList.add(messageObjM5695g);
        } else {
            Log.e("MeetingClient", "sendHangupMeetingAndRejectMessage, gen rejectMessageObj fail");
        }
        if (messageObjM5694f != null && messageObjM5695g != null && messageObjM5694f.m14788z().equals(messageObjM5695g.m14788z())) {
            messageObjM5695g.m14761W(new Date(messageObjM5695g.m14788z().getTime() + 1));
        }
        if (arrayList.size() > 0) {
            m5697y(group.f13723j, arrayList);
            ULogUtility.m16680p("MeetingClient", "sendHangupMeetingMessage:" + messageObjM5694f);
            ULogUtility.m16680p("MeetingClient", "sendRejectMessage:" + messageObjM5695g);
        }
    }
}
