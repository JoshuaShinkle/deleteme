package com.cyberlink.you.activity.friend;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.cyberlink.p030U.R;
import com.cyberlink.you.BaseActivity;
import com.cyberlink.you.Globals;
import com.cyberlink.you.activity.friend.FriendAddActivity;
import com.cyberlink.you.friends.DepartmentFriend;
import com.cyberlink.you.friends.FriendsClient;
import com.cyberlink.you.friends.SuggestionFriend;
import com.cyberlink.you.friends.UserInfo;
import com.cyberlink.you.p036ui.C3122f;
import com.cyberlink.you.utility.CLUtility;
import com.cyberlink.you.widgetpool.PeopleSearchView;
import com.perfectcorp.ycl.p040bc.model.DepartmentList;
import com.perfectcorp.ycl.p040bc.model.OrgContactList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.json.JSONException;
import org.json.JSONObject;
import p014b3.C0632l;
import p116k4.C5173p0;
import p147n5.C5369g;
import p193s4.C6263a;
import p201t3.C6301o;

/* loaded from: classes.dex */
public class FriendAddActivity extends BaseActivity {

    /* renamed from: u */
    public static final ExecutorService f10535u = Executors.newCachedThreadPool();

    /* renamed from: j */
    public LinearLayout f10543j;

    /* renamed from: l */
    public ProgressBar f10545l;

    /* renamed from: m */
    public TextView f10546m;

    /* renamed from: n */
    public String f10547n;

    /* renamed from: c */
    public FriendsClient f10536c = null;

    /* renamed from: d */
    public long f10537d = -1;

    /* renamed from: e */
    public C0632l f10538e = null;

    /* renamed from: f */
    public ArrayList<SuggestionFriend> f10539f = null;

    /* renamed from: g */
    public ArrayList<DepartmentList.Department> f10540g = null;

    /* renamed from: h */
    public ArrayList<OrgContactList.OrgContacts.ContactInfo> f10541h = null;

    /* renamed from: i */
    public boolean f10542i = false;

    /* renamed from: k */
    public String f10544k = null;

    /* renamed from: o */
    public final Comparator<SuggestionFriend> f10548o = new Comparator() { // from class: b3.z
        @Override // java.util.Comparator
        public final int compare(Object obj, Object obj2) {
            return FriendAddActivity.m12206I((SuggestionFriend) obj, (SuggestionFriend) obj2);
        }
    };

    /* renamed from: p */
    public View.OnClickListener f10549p = new View.OnClickListener() { // from class: b3.a0
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f3147b.m12207J(view);
        }
    };

    /* renamed from: q */
    public View.OnClickListener f10550q = new View.OnClickListener() { // from class: b3.b0
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f3151b.m12208L(view);
        }
    };

    /* renamed from: r */
    public View.OnClickListener f10551r = new View.OnClickListener() { // from class: b3.c0
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f3155b.m12209N(view);
        }
    };

    /* renamed from: s */
    public ExpandableListView.OnChildClickListener f10552s = new ExpandableListView.OnChildClickListener() { // from class: b3.d0
        @Override // android.widget.ExpandableListView.OnChildClickListener
        public final boolean onChildClick(ExpandableListView expandableListView, View view, int i9, int i10, long j9) {
            return this.f3163a.m12210O(expandableListView, view, i9, i10, j9);
        }
    };

    /* renamed from: t */
    public C5173p0.b f10553t = new C5173p0.b() { // from class: b3.e0
        @Override // p116k4.C5173p0.b
        /* renamed from: a */
        public final void mo3298a(boolean z8) {
            this.f3168a.m12212Q(z8);
        }
    };

    /* renamed from: com.cyberlink.you.activity.friend.FriendAddActivity$a */
    public class AsyncTaskC2123a extends AsyncTask<Void, Void, Void> {
        public AsyncTaskC2123a() {
        }

        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public Void doInBackground(Void... voidArr) {
            if (FriendAddActivity.this.f10542i) {
                return null;
            }
            Thread.currentThread().setName("FriendAddActivity.getInviteLink AsyncTask");
            FriendAddActivity.this.m12233X();
            return null;
        }
    }

    /* renamed from: com.cyberlink.you.activity.friend.FriendAddActivity$b */
    public class C2124b implements PeopleSearchView.InterfaceC3209g {
        public C2124b() {
        }

        @Override // com.cyberlink.you.widgetpool.PeopleSearchView.InterfaceC3209g
        /* renamed from: a */
        public void mo12236a() {
            FriendAddActivity.this.f10538e.notifyDataSetChanged();
            FriendAddActivity.this.f10546m.setVisibility(8);
            FriendAddActivity.this.f10545l.setVisibility(0);
        }

        @Override // com.cyberlink.you.widgetpool.PeopleSearchView.InterfaceC3209g
        /* renamed from: b */
        public void mo12237b(String str) {
            FriendAddActivity.this.m12228C();
            FriendAddActivity.this.f10538e.getFilter().filter(str);
        }

        @Override // com.cyberlink.you.widgetpool.PeopleSearchView.InterfaceC3209g
        /* renamed from: c */
        public void mo12238c(List<DepartmentFriend> list) {
            FriendAddActivity.this.f10545l.setVisibility(8);
            FriendAddActivity.this.f10538e.m3333V(list);
            FriendAddActivity.this.f10538e.notifyDataSetChanged();
            if (FriendAddActivity.this.f10538e.m3348x()) {
                FriendAddActivity.this.f10546m.setVisibility(0);
            } else {
                FriendAddActivity.this.f10546m.setVisibility(8);
            }
        }

        @Override // com.cyberlink.you.widgetpool.PeopleSearchView.InterfaceC3209g
        /* renamed from: d */
        public void mo12239d(List<SuggestionFriend> list) {
            FriendAddActivity.this.f10545l.setVisibility(8);
            FriendAddActivity.this.f10538e.m3338n(list);
            if (FriendAddActivity.this.f10538e.m3348x()) {
                FriendAddActivity.this.f10546m.setVisibility(0);
            } else {
                FriendAddActivity.this.f10546m.setVisibility(8);
            }
        }

        @Override // com.cyberlink.you.widgetpool.PeopleSearchView.InterfaceC3209g
        /* renamed from: e */
        public void mo12240e() {
            FriendAddActivity.this.f10538e.m3339o();
        }

        @Override // com.cyberlink.you.widgetpool.PeopleSearchView.InterfaceC3209g
        public void onCancel() {
            FriendAddActivity.this.m12229D();
            FriendAddActivity.this.f10538e.notifyDataSetChanged();
            FriendAddActivity.this.f10545l.setVisibility(8);
            FriendAddActivity.this.f10546m.setVisibility(8);
            FriendAddActivity.this.f10538e.getFilter().filter("");
        }
    }

    /* renamed from: I */
    public static /* synthetic */ int m12206I(SuggestionFriend suggestionFriend, SuggestionFriend suggestionFriend2) {
        return Long.compare(suggestionFriend2.f13765i, suggestionFriend.f13765i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: J */
    public /* synthetic */ void m12207J(View view) {
        new C3122f().m16376e(this, this.f10544k).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: L */
    public /* synthetic */ void m12208L(View view) {
        C2143a.m12412l(this, 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: N */
    public /* synthetic */ void m12209N(View view) {
        CLUtility.m16589t1(this);
        onBackPressed();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: O */
    public /* synthetic */ boolean m12210O(ExpandableListView expandableListView, View view, int i9, int i10, long j9) {
        Object child = this.f10538e.getChild(i9, i10);
        int iM3344t = this.f10538e.m3344t(i9);
        if (FRIEND_ADD_TYPE.DEPARTMENT.m12202b() == iM3344t) {
            DepartmentList.Department department = (DepartmentList.Department) child;
            m12232V(department.name, department.f15980id, department.memberCount, false);
        } else if (FRIEND_ADD_TYPE.ORG_CONTACTS.m12202b() == iM3344t) {
            OrgContactList.OrgContacts.ContactInfo contactInfo = (OrgContactList.OrgContacts.ContactInfo) child;
            m12232V(contactInfo.name, contactInfo.f15986id, contactInfo.totalContacts, true);
        } else if (FRIEND_ADD_TYPE.ORG_CONTACTS_SEARCH.m12202b() == iM3344t) {
            C2143a.m12410j(this, (DepartmentFriend) child, "");
        } else if (Arrays.asList(Integer.valueOf(FRIEND_ADD_TYPE.SUGGESTION.m12202b()), Integer.valueOf(FRIEND_ADD_TYPE.SUGGESTION_ON_U.m12202b())).contains(Integer.valueOf(iM3344t))) {
            C2143a.m12410j(this, (SuggestionFriend) child, "");
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: P */
    public /* synthetic */ void m12211P(List list) {
        this.f10539f.clear();
        this.f10539f.addAll(list);
        this.f10538e.m3334W(list);
        this.f10538e.notifyDataSetChanged();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: Q */
    public /* synthetic */ void m12212Q(boolean z8) {
        if (z8) {
            final ArrayList<SuggestionFriend> arrayListM20211g = C5173p0.m20207e().m20211g();
            m12234Y(arrayListM20211g);
            runOnUiThread(new Runnable() { // from class: b3.f0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f3172b.m12211P(arrayListM20211g);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: T */
    public /* synthetic */ void m12213T(String str, String str2, String str3, String str4) throws JSONException {
        if (!"200".equals(str3)) {
            Log.d("FriendAddActivity", "Invite failed with status code " + str3);
            return;
        }
        try {
            String string = new JSONObject(str4).getString("inviteURL");
            getApplicationContext().getSharedPreferences("U", 0).edit().putString("inviteFriendLink", string).apply();
            UserInfo userInfoM16497V0 = CLUtility.m16497V0(this);
            this.f10544k = String.format(getString(R.string.invite_sms_new), userInfoM16497V0 == null ? "" : userInfoM16497V0.f13778c, string);
        } catch (JSONException e9) {
            e9.printStackTrace();
        }
    }

    /* renamed from: C */
    public final void m12228C() {
        this.f10538e.m3332U(true);
        this.f10543j.setVisibility(8);
    }

    /* renamed from: D */
    public final void m12229D() {
        this.f10538e.m3332U(false);
        this.f10543j.setVisibility(0);
    }

    /* renamed from: E */
    public final void m12230E(boolean z8) {
        if (z8) {
            m12234Y(this.f10539f);
        }
        String strM7497V = Globals.m7388i0().m7497V();
        if (!TextUtils.isEmpty(strM7497V)) {
            this.f10540g = (ArrayList) ((DepartmentList) C5369g.GSON.fromJson(strM7497V, DepartmentList.class)).results;
        }
        if (this.f10540g == null) {
            this.f10540g = new ArrayList<>();
        }
        if (this.f10541h == null) {
            this.f10541h = new ArrayList<>();
        }
        this.f10538e = new C0632l(this, this.f10536c, this.f10539f, this.f10540g, this.f10541h);
        ExpandableListView expandableListView = (ExpandableListView) findViewById(R.id.DepartmentList);
        expandableListView.setAdapter(this.f10538e);
        expandableListView.setOnChildClickListener(this.f10552s);
    }

    /* renamed from: H */
    public final void m12231H() {
        this.f10545l = (ProgressBar) findViewById(R.id.progressbar_searching);
        this.f10543j = (LinearLayout) findViewById(R.id.friendAddContent);
        this.f10546m = (TextView) findViewById(R.id.text_no_result);
        findViewById(R.id.img_title_back).setOnClickListener(this.f10551r);
        findViewById(R.id.FriendInvitationSelectorLayout).setOnClickListener(this.f10549p);
        findViewById(R.id.FriendInvitationSelectorQrLayout).setOnClickListener(this.f10550q);
        PeopleSearchView peopleSearchView = (PeopleSearchView) findViewById(R.id.search_people);
        peopleSearchView.requestFocus();
        peopleSearchView.setFriendsClient(this.f10536c);
        peopleSearchView.setUpdateListListener(new C2124b());
    }

    /* renamed from: V */
    public final void m12232V(String str, String str2, Long l9, boolean z8) {
        if (m7364e() || l9 == null || l9.longValue() == 0) {
            return;
        }
        if (TextUtils.isEmpty(str)) {
            str = getString(R.string.ungrouped);
        }
        if (!z8) {
            str = C2143a.m12400E(str, l9);
        }
        Intent intent = new Intent(this, (Class<?>) DepartmentMemberAddActivity.class);
        intent.putExtra("department_name", str);
        intent.putExtra("department_id", str2);
        intent.putExtra("department_is_contacts", z8);
        startActivityForResult(intent, 8);
    }

    /* renamed from: X */
    public final void m12233X() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new C6301o("token", Globals.m7388i0().m7449L()));
        this.f10536c.m15734m("invite", "genInviteURL", arrayList, new FriendsClient.InterfaceC3051i() { // from class: b3.y
            @Override // com.cyberlink.you.friends.FriendsClient.InterfaceC3051i
            /* renamed from: a */
            public final void mo134a(String str, String str2, String str3, String str4) throws JSONException {
                this.f3274a.m12213T(str, str2, str3, str4);
            }
        });
    }

    /* renamed from: Y */
    public final void m12234Y(List<SuggestionFriend> list) {
        Collections.sort(list, this.f10548o);
        long j9 = list.size() > 0 ? list.get(0).f13765i : -1L;
        if (j9 > this.f10537d) {
            Globals.m7388i0().m7636w3(j9);
        }
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService("input_method");
        View currentFocus = getCurrentFocus();
        if (inputMethodManager != null && currentFocus != null) {
            inputMethodManager.hideSoftInputFromWindow(currentFocus.getWindowToken(), 0);
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    @Override // android.app.Activity
    public void onActivityResult(int i9, int i10, Intent intent) {
        Bundle extras;
        SuggestionFriend suggestionFriend;
        super.onActivityResult(i9, i10, intent);
        if (i9 == 0) {
            C2143a.m12397B(this, i10, intent, 2);
            return;
        }
        if (i9 == 2) {
            if (i10 != -1 || intent == null || (extras = intent.getExtras()) == null || (suggestionFriend = (SuggestionFriend) extras.getParcelable("suggestionFriend")) == null) {
                return;
            }
            this.f10538e.m3302G(suggestionFriend);
            return;
        }
        if (i9 == 4) {
            if (i10 != -1) {
                Log.d("FriendAddActivity", "Invitation denied");
                return;
            }
            Log.d("FriendAddActivity", "Invitation send : " + Arrays.toString(C6263a.m24006d(i10, intent)));
            return;
        }
        if (i9 == 8 && i10 == -1 && intent != null) {
            boolean booleanExtra = intent.getBooleanExtra("department_error", false);
            boolean booleanExtra2 = intent.getBooleanExtra("department_update", false);
            if (booleanExtra) {
                onBackPressed();
            }
            if (booleanExtra2) {
                C2143a.m12424x(this.f10547n, this.f10538e);
            }
        }
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_friend_add);
        boolean z8 = true;
        this.f10536c = new FriendsClient(true);
        this.f10537d = Globals.m7388i0().m7398A0();
        m12231H();
        new AsyncTaskC2123a().executeOnExecutor(f10535u, new Void[0]);
        ArrayList<SuggestionFriend> arrayListM20211g = C5173p0.m20207e().m20211g();
        this.f10539f = arrayListM20211g;
        Collections.sort(arrayListM20211g, this.f10548o);
        if (!Globals.m7388i0().m7626v() && !this.f10539f.isEmpty()) {
            z8 = false;
        }
        m12230E(!z8);
        C5173p0.m20207e().m20208c(this.f10553t);
        if (z8) {
            Globals.m7388i0().m7401A3(false);
            C5173p0.m20207e().m20215k(false);
        }
        this.f10547n = Globals.m7388i0().m7506X();
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        this.f10542i = true;
        C5173p0.m20207e().m20214j(this.f10553t);
        FriendsClient friendsClient = this.f10536c;
        if (friendsClient != null) {
            friendsClient.m15717U0();
        }
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        CLUtility.m16589t1(this);
        C2143a.m12424x(this.f10547n, this.f10538e);
        C2143a.m12425y(this.f10547n, this.f10538e);
    }
}
