package com.cyberlink.you.activity.friend;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import androidx.fragment.app.Fragment;
import com.cyberlink.p030U.R;
import com.cyberlink.you.Globals;
import com.cyberlink.you.activity.GroupInfoActivity;
import com.cyberlink.you.activity.OrganizationJoinActivity;
import com.cyberlink.you.activity.PCLoginActivity;
import com.cyberlink.you.activity.QRCodeInviteActivity;
import com.cyberlink.you.activity.UserProfileActivity;
import com.cyberlink.you.activity.friend.C2143a;
import com.cyberlink.you.activity.friend.FriendProfileActivity;
import com.cyberlink.you.activity.zxing.CaptureActivity;
import com.cyberlink.you.friends.DepartmentFriend;
import com.cyberlink.you.friends.Friend;
import com.cyberlink.you.friends.InvitationFriend;
import com.cyberlink.you.friends.SuggestionFriend;
import com.cyberlink.you.utility.Permission.Permission;
import com.cyberlink.you.utility.ULogUtility;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.zxing.client.android.Intents;
import com.perfectcorp.utility.PromisedTask;
import com.perfectcorp.ycl.p040bc.model.DepartmentList;
import com.perfectcorp.ycl.p040bc.model.OrgContactList;
import com.perfectcorp.ycl.p040bc.model.network.NetworkLive;
import com.perfectcorp.ycl.p040bc.model.network.NetworkManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import p014b3.C0632l;
import p116k4.C5183t0;
import p116k4.C5187v0;
import p127l4.C5287b;
import p127l4.InterfaceC5288c;
import p147n5.C5369g;
import p201t3.C6288b;
import p201t3.C6289c;
import p209u2.AbstractC6381r;

/* renamed from: com.cyberlink.you.activity.friend.a */
/* loaded from: classes.dex */
public class C2143a {

    /* renamed from: a */
    public static final String f10660a = "a";

    /* renamed from: com.cyberlink.you.activity.friend.a$a */
    public class a implements InterfaceC5288c {

        /* renamed from: a */
        public final /* synthetic */ Object f10661a;

        /* renamed from: b */
        public final /* synthetic */ int f10662b;

        public a(Object obj, int i9) {
            this.f10661a = obj;
            this.f10662b = i9;
        }

        @Override // p127l4.InterfaceC5288c
        /* renamed from: a */
        public void mo6907a(boolean z8) {
            if (z8) {
                C5183t0.m20262b(C2143a.m12411k(this.f10661a), Permission.CAMERA);
            } else {
                C5187v0.m20267c(R.string.permission_denied);
            }
        }

        @Override // p127l4.InterfaceC5288c
        /* renamed from: b */
        public void mo6908b() {
            Object obj = this.f10661a;
            C2143a.m12396A(obj, CaptureActivity.m13980y(C2143a.m12411k(obj)), this.f10662b);
        }
    }

    /* renamed from: com.cyberlink.you.activity.friend.a$b */
    public class b extends AbstractC6381r<Void, Integer> {

        /* renamed from: c */
        public final /* synthetic */ List f10663c;

        /* renamed from: d */
        public final /* synthetic */ C0632l f10664d;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public b(Handler handler, List list, C0632l c0632l) {
            super(handler);
            this.f10663c = list;
            this.f10664d = c0632l;
        }

        @Override // p209u2.AbstractC6381r
        /* renamed from: k, reason: merged with bridge method [inline-methods] */
        public void m24503g(Void r22) {
            DepartmentList departmentList = new DepartmentList();
            departmentList.totalSize = this.f10663c.size();
            departmentList.results = this.f10663c;
            Globals.m7388i0().m7625u4(C5369g.GSON.toJson(departmentList));
            C0632l c0632l = this.f10664d;
            if (c0632l != null) {
                c0632l.m3330S(false);
                this.f10664d.m3329R(this.f10663c);
                this.f10664d.notifyDataSetChanged();
            }
        }

        @Override // p209u2.AbstractC6381r
        /* renamed from: l, reason: merged with bridge method [inline-methods] */
        public void m24504h(Integer num) {
            if (num.intValue() == NetworkManager.NetworkErrorCode.E_NOT_INITIALIZED.getValue()) {
                ULogUtility.m16670f(C2143a.f10660a, "[queryDepartment] api does not initialize");
                return;
            }
            if (!Arrays.asList(401, 403, 404).contains(num)) {
                C5187v0.m20267c(R.string.error_server_response);
                return;
            }
            Globals.m7388i0().m7625u4("");
            C0632l c0632l = this.f10664d;
            if (c0632l != null) {
                c0632l.m3330S(false);
                this.f10664d.m3329R(new ArrayList());
                this.f10664d.notifyDataSetChanged();
            }
        }
    }

    /* renamed from: com.cyberlink.you.activity.friend.a$c */
    public class c extends PromisedTask.AbstractC4504d<DepartmentList> {

        /* renamed from: a */
        public final /* synthetic */ int f10665a;

        /* renamed from: b */
        public final /* synthetic */ List f10666b;

        /* renamed from: c */
        public final /* synthetic */ String f10667c;

        /* renamed from: d */
        public final /* synthetic */ String f10668d;

        /* renamed from: e */
        public final /* synthetic */ AbstractC6381r f10669e;

        public c(int i9, List list, String str, String str2, AbstractC6381r abstractC6381r) {
            this.f10665a = i9;
            this.f10666b = list;
            this.f10667c = str;
            this.f10668d = str2;
            this.f10669e = abstractC6381r;
        }

        @Override // com.perfectcorp.utility.PromisedTask.AbstractC4504d
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onDone(DepartmentList departmentList) {
            List<DepartmentList.Department> list;
            if (departmentList != null && (this.f10665a + 1) * 50 < departmentList.totalSize) {
                this.f10666b.addAll(departmentList.results);
                C2143a.m12422v(this.f10667c, this.f10666b, this.f10665a + 1, this.f10668d, this.f10669e);
                return;
            }
            if (departmentList != null && (list = departmentList.results) != null && list.size() > 0) {
                this.f10666b.addAll(departmentList.results);
            }
            this.f10669e.m24505c();
        }

        @Override // com.perfectcorp.utility.PromisedTask
        public void onError(int i9) {
            this.f10669e.m24508f(Integer.valueOf(i9));
        }
    }

    /* renamed from: com.cyberlink.you.activity.friend.a$d */
    public class d extends AbstractC6381r<Void, Integer> {

        /* renamed from: c */
        public final /* synthetic */ C0632l f10670c;

        /* renamed from: d */
        public final /* synthetic */ List f10671d;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public d(Handler handler, C0632l c0632l, List list) {
            super(handler);
            this.f10670c = c0632l;
            this.f10671d = list;
        }

        @Override // p209u2.AbstractC6381r
        /* renamed from: k, reason: merged with bridge method [inline-methods] */
        public void m24503g(Void r72) {
            C0632l c0632l = this.f10670c;
            if (c0632l != null) {
                c0632l.m3330S(false);
                ArrayList arrayList = new ArrayList();
                for (OrgContactList.OrgContacts.ContactInfo contactInfo : this.f10671d) {
                    long jLongValue = contactInfo.totalContacts.longValue() - contactInfo.hiddenContacts.longValue();
                    if (!contactInfo.hidden.booleanValue() && jLongValue > 0) {
                        arrayList.add(contactInfo);
                    }
                }
                this.f10670c.m3331T(arrayList);
                this.f10670c.notifyDataSetChanged();
            }
        }

        @Override // p209u2.AbstractC6381r
        /* renamed from: l, reason: merged with bridge method [inline-methods] */
        public void m24504h(Integer num) {
            if (num.intValue() == NetworkManager.NetworkErrorCode.E_NOT_INITIALIZED.getValue()) {
                ULogUtility.m16670f(C2143a.f10660a, "[queryDepartment] api does not initialize");
                return;
            }
            if (!Arrays.asList(401, 403, 404).contains(num)) {
                C5187v0.m20267c(R.string.error_server_response);
                return;
            }
            Globals.m7388i0().m7625u4("");
            C0632l c0632l = this.f10670c;
            if (c0632l != null) {
                c0632l.m3330S(false);
                this.f10670c.m3331T(new ArrayList());
                this.f10670c.notifyDataSetChanged();
            }
        }
    }

    /* renamed from: com.cyberlink.you.activity.friend.a$e */
    public class e extends PromisedTask.AbstractC4504d<OrgContactList> {

        /* renamed from: a */
        public final /* synthetic */ int f10672a;

        /* renamed from: b */
        public final /* synthetic */ List f10673b;

        /* renamed from: c */
        public final /* synthetic */ String f10674c;

        /* renamed from: d */
        public final /* synthetic */ String f10675d;

        /* renamed from: e */
        public final /* synthetic */ AbstractC6381r f10676e;

        public e(int i9, List list, String str, String str2, AbstractC6381r abstractC6381r) {
            this.f10672a = i9;
            this.f10673b = list;
            this.f10674c = str;
            this.f10675d = str2;
            this.f10676e = abstractC6381r;
        }

        @Override // com.perfectcorp.utility.PromisedTask.AbstractC4504d
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onDone(OrgContactList orgContactList) {
            List<OrgContactList.OrgContacts.ContactInfo> list;
            ULogUtility.m16670f(C2143a.f10660a, "test");
            OrgContactList.OrgContacts orgContacts = orgContactList.directories;
            if (orgContacts != null && (this.f10672a + 1) * 50 < orgContacts.totalSize) {
                this.f10673b.addAll(orgContacts.results);
                C2143a.m12420t(this.f10674c, this.f10675d, this.f10673b, this.f10672a + 1, this.f10676e);
                return;
            }
            if (orgContacts != null && (list = orgContacts.results) != null && list.size() > 0) {
                this.f10673b.addAll(orgContactList.directories.results);
            }
            this.f10676e.m24505c();
        }

        @Override // com.perfectcorp.utility.PromisedTask
        public void onError(int i9) {
            this.f10676e.m24508f(Integer.valueOf(i9));
        }
    }

    /* renamed from: A */
    public static void m12396A(Object obj, Intent intent, int i9) {
        if (obj instanceof Activity) {
            ((Activity) obj).startActivityForResult(intent, i9);
        } else {
            ((Fragment) obj).startActivityForResult(intent, i9);
        }
    }

    /* renamed from: B */
    public static void m12397B(Activity activity, int i9, Intent intent, int i10) {
        m12399D(activity, activity.getApplicationContext(), i9, intent, i10);
    }

    /* renamed from: C */
    public static void m12398C(Fragment fragment, int i9, Intent intent, int i10) {
        m12399D(fragment, fragment.getContext(), i9, intent, i10);
    }

    /* renamed from: D */
    public static void m12399D(Object obj, Context context, int i9, Intent intent, int i10) {
        if (i9 != -1 || intent == null) {
            return;
        }
        int intExtra = intent.getIntExtra("ACTIVITY_INTERACTION_KEY", -1);
        if (intExtra != -1) {
            if (intExtra == 2) {
                m12426z(obj, new Intent(context, (Class<?>) QRCodeInviteActivity.class));
                return;
            }
            return;
        }
        String stringExtra = intent.getStringExtra(Intents.Scan.RESULT);
        if (stringExtra == null) {
            C5187v0.m20267c(R.string.qrcode_scan_failed);
            return;
        }
        if (stringExtra.contains("/ti/")) {
            Intent intent2 = new Intent(context, (Class<?>) FriendProfileActivity.class);
            intent2.putExtra("type", FriendProfileActivity.FPA_PROFILE_TYPE.PROFILE_TYPE_VIEW_QRCODE_SCAN_RESULT.name());
            intent2.putExtra(Intents.Scan.RESULT, stringExtra);
            m12396A(obj, intent2, i10);
            return;
        }
        if (stringExtra.contains("/au/")) {
            Intent intent3 = new Intent(context, (Class<?>) PCLoginActivity.class);
            intent3.putExtra("pcLoginUrl", stringExtra);
            m12426z(obj, intent3);
        } else if (stringExtra.contains("/gi/")) {
            GroupInfoActivity.m8433T0(m12411k(obj), stringExtra);
        } else {
            if (!stringExtra.contains("/org/")) {
                C5187v0.m20267c(R.string.qrcode_scan_failed);
                return;
            }
            Intent intent4 = new Intent(context, (Class<?>) OrganizationJoinActivity.class);
            intent4.putExtra("inviteURL", stringExtra);
            m12426z(obj, intent4);
        }
    }

    /* renamed from: E */
    public static String m12400E(String str, Long l9) {
        if (l9 == null) {
            l9 = 0L;
        }
        return str + " (" + l9 + ")";
    }

    /* renamed from: j */
    public static void m12410j(Activity activity, Object obj, String str) {
        if (obj == null) {
            return;
        }
        if (obj instanceof SuggestionFriend) {
            SuggestionFriend suggestionFriend = (SuggestionFriend) obj;
            if (suggestionFriend.f13759c == Globals.m7388i0().m7568k1().longValue()) {
                activity.startActivity(new Intent(activity, (Class<?>) UserProfileActivity.class));
                return;
            }
            if (suggestionFriend.f13768l) {
                m12423w(activity, suggestionFriend.f13759c);
                return;
            }
            Intent intent = new Intent(activity, (Class<?>) FriendProfileActivity.class);
            intent.putExtra("suggestionFriend", suggestionFriend);
            intent.putExtra("type", FriendProfileActivity.FPA_PROFILE_TYPE.PROFILE_TYPE_SUGGESTIONS.name());
            activity.startActivityForResult(intent, 2);
            return;
        }
        if (!(obj instanceof DepartmentFriend)) {
            if (obj instanceof InvitationFriend) {
                Intent intent2 = new Intent(activity, (Class<?>) FriendProfileActivity.class);
                intent2.putExtra("InvitationFriend", (InvitationFriend) obj);
                intent2.putExtra("type", FriendProfileActivity.FPA_PROFILE_TYPE.PROFILE_TYPE_SENT_INVITATION.name());
                activity.startActivityForResult(intent2, 1);
                return;
            }
            return;
        }
        DepartmentFriend departmentFriend = (DepartmentFriend) obj;
        if (departmentFriend.f13626b == Globals.m7388i0().m7568k1().longValue()) {
            activity.startActivity(new Intent(activity, (Class<?>) UserProfileActivity.class));
            return;
        }
        if (!departmentFriend.f13641q) {
            m12421u(activity, departmentFriend);
            return;
        }
        String str2 = departmentFriend.f13629e;
        Intent intent3 = new Intent(activity, (Class<?>) DepartmentMemberAddActivity.class);
        intent3.putExtra("department_name", str2);
        intent3.putExtra("department_id", departmentFriend.f13640p);
        intent3.putExtra("department_is_contacts", true);
        intent3.putExtra("department_root_name", str);
        activity.startActivityForResult(intent3, 8);
    }

    /* renamed from: k */
    public static Activity m12411k(Object obj) {
        return obj instanceof Activity ? (Activity) obj : ((Fragment) obj).getActivity();
    }

    /* renamed from: l */
    public static void m12412l(Activity activity, int i9) {
        m12414n(activity, i9);
    }

    /* renamed from: m */
    public static void m12413m(Fragment fragment, int i9) {
        m12414n(fragment, i9);
    }

    /* renamed from: n */
    public static void m12414n(Object obj, int i9) {
        C5287b.m20583f(Permission.CAMERA, new a(obj, i9), m12411k(obj));
    }

    /* renamed from: o */
    public static boolean m12415o(long j9) {
        return j9 >= 600 && ((int) (j9 / 100)) % 10 == 6;
    }

    /* renamed from: p */
    public static /* synthetic */ void m12416p(DepartmentFriend departmentFriend, Activity activity, Friend friend) {
        if (TextUtils.isEmpty(departmentFriend.f13631g)) {
            departmentFriend.f13631g = friend.f13647e;
        }
        departmentFriend.f13627c = friend.f13661s;
        departmentFriend.f13630f = friend.m15620a();
        departmentFriend.f13632h = friend.f13650h;
        departmentFriend.f13634j = friend.f13663u;
        departmentFriend.f13637m = friend.f13658p;
        departmentFriend.f13633i = friend.f13662t;
        Intent intent = new Intent(activity, (Class<?>) FriendProfileActivity.class);
        intent.putExtra("departmentFriend", departmentFriend);
        intent.putExtra("friendObj", friend);
        intent.putExtra("type", FriendProfileActivity.FPA_PROFILE_TYPE.PROFILE_TYPE_DEPARTMENTS.name());
        if (departmentFriend.f13635k) {
            activity.startActivityForResult(intent, 1);
        } else {
            activity.startActivityForResult(intent, 2);
        }
    }

    /* renamed from: r */
    public static /* synthetic */ void m12418r(Activity activity, Friend friend) {
        Intent intent = new Intent(activity, (Class<?>) FriendProfileActivity.class);
        intent.putExtra("friendObj", friend);
        intent.putExtra("type", FriendProfileActivity.FPA_PROFILE_TYPE.PROFILE_TYPE_VIEW_FRIEND.name());
        activity.startActivity(intent);
    }

    /* renamed from: t */
    public static void m12420t(String str, String str2, List<OrgContactList.OrgContacts.ContactInfo> list, int i9, AbstractC6381r<Void, Integer> abstractC6381r) {
        NetworkLive.listContacts(str, str2, i9, 50).done(new e(i9, list, str, str2, abstractC6381r));
    }

    /* renamed from: u */
    public static void m12421u(final Activity activity, final DepartmentFriend departmentFriend) {
        long j9 = departmentFriend.f13626b;
        if (j9 > 0) {
            C6289c.m24104c(String.valueOf(j9), new C6288b.d() { // from class: b3.s1
                @Override // p201t3.C6288b.d
                public final void onComplete(Object obj) {
                    C2143a.m12416p(departmentFriend, activity, (Friend) obj);
                }
            }, new C6288b.h() { // from class: b3.t1
                @Override // p201t3.C6288b.h
                public final void onError(String str) {
                    C5187v0.m20267c(R.string.error_server_response);
                }
            }).m24088p();
            return;
        }
        Intent intent = new Intent(activity, (Class<?>) FriendProfileActivity.class);
        intent.putExtra("departmentFriend", departmentFriend);
        intent.putExtra("type", FriendProfileActivity.FPA_PROFILE_TYPE.PROFILE_TYPE_DEPARTMENTS.name());
        activity.startActivityForResult(intent, 2);
    }

    /* renamed from: v */
    public static void m12422v(String str, List<DepartmentList.Department> list, int i9, String str2, AbstractC6381r<Void, Integer> abstractC6381r) {
        NetworkLive.listDepartment(str, i9, 50, str2).done(new c(i9, list, str, str2, abstractC6381r));
    }

    /* renamed from: w */
    public static void m12423w(final Activity activity, long j9) {
        C6289c.m24104c(String.valueOf(j9), new C6288b.d() { // from class: b3.q1
            @Override // p201t3.C6288b.d
            public final void onComplete(Object obj) {
                C2143a.m12418r(activity, (Friend) obj);
            }
        }, new C6288b.h() { // from class: b3.r1
            @Override // p201t3.C6288b.h
            public final void onError(String str) {
                C5187v0.m20267c(R.string.error_server_response);
            }
        }).m24088p();
    }

    /* renamed from: x */
    public static void m12424x(String str, C0632l c0632l) {
        if (c0632l != null) {
            c0632l.m3330S(true);
        }
        ArrayList arrayList = new ArrayList();
        arrayList.clear();
        m12422v(str, arrayList, 0, AppMeasurementSdk.ConditionalUserProperty.NAME, new b(new Handler(Looper.getMainLooper()), arrayList, c0632l));
    }

    /* renamed from: y */
    public static void m12425y(String str, C0632l c0632l) {
        if (c0632l != null) {
            c0632l.m3330S(true);
        }
        ArrayList arrayList = new ArrayList();
        arrayList.clear();
        m12420t(str, null, arrayList, 0, new d(new Handler(Looper.getMainLooper()), c0632l, arrayList));
    }

    /* renamed from: z */
    public static void m12426z(Object obj, Intent intent) {
        m12396A(obj, intent, -1);
    }
}
