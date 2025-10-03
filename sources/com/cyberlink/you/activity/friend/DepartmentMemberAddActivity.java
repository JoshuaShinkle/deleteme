package com.cyberlink.you.activity.friend;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.recyclerview.widget.C0476e;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.cyberlink.p030U.R;
import com.cyberlink.you.BaseActivity;
import com.cyberlink.you.Globals;
import com.cyberlink.you.activity.friend.FriendProfileActivity;
import com.cyberlink.you.database.C2950b0;
import com.cyberlink.you.friends.DepartmentFriend;
import com.cyberlink.you.friends.Friend;
import com.cyberlink.you.friends.FriendsClient;
import com.cyberlink.you.friends.InvitationFriend;
import com.cyberlink.you.utility.ULogUtility;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.perfectcorp.utility.PromisedTask;
import com.perfectcorp.ycl.p040bc.model.DepartmentMemberList;
import com.perfectcorp.ycl.p040bc.model.OrgContactList;
import com.perfectcorp.ycl.p040bc.model.network.NetworkLive;
import com.perfectcorp.ycl.p040bc.model.network.NetworkManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import p014b3.C0656t;
import p116k4.C5170o0;
import p116k4.C5187v0;

/* loaded from: classes.dex */
public class DepartmentMemberAddActivity extends BaseActivity {

    /* renamed from: w */
    public static final String f10505w = "DepartmentMemberAddActivity";

    /* renamed from: h */
    public String f10511h;

    /* renamed from: m */
    public C0656t f10516m;

    /* renamed from: p */
    public ProgressBar f10519p;

    /* renamed from: q */
    public TextView f10520q;

    /* renamed from: r */
    public TextView f10521r;

    /* renamed from: s */
    public TextView f10522s;

    /* renamed from: c */
    public String f10506c = "";

    /* renamed from: d */
    public String f10507d = null;

    /* renamed from: e */
    public boolean f10508e = false;

    /* renamed from: f */
    public String f10509f = null;

    /* renamed from: g */
    public FriendsClient f10510g = null;

    /* renamed from: i */
    public final int f10512i = 50;

    /* renamed from: j */
    public int f10513j = 0;

    /* renamed from: k */
    public int f10514k = 0;

    /* renamed from: l */
    public final List<DepartmentFriend> f10515l = new ArrayList();

    /* renamed from: n */
    public boolean f10517n = false;

    /* renamed from: o */
    public boolean f10518o = false;

    /* renamed from: t */
    public final View.OnClickListener f10523t = new View.OnClickListener() { // from class: b3.u
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f3263b.m12169Y(view);
        }
    };

    /* renamed from: u */
    public final View.OnClickListener f10524u = new View.OnClickListener() { // from class: b3.v
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f3266b.m12170Z(view);
        }
    };

    /* renamed from: v */
    public final RecyclerView.AbstractC0459t f10525v = new C2120a();

    /* renamed from: com.cyberlink.you.activity.friend.DepartmentMemberAddActivity$a */
    public class C2120a extends RecyclerView.AbstractC0459t {
        public C2120a() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AbstractC0459t
        /* renamed from: b */
        public void mo2543b(RecyclerView recyclerView, int i9, int i10) {
            super.mo2543b(recyclerView, i9, i10);
            if (DepartmentMemberAddActivity.this.m12187V() + 1 == DepartmentMemberAddActivity.this.f10516m.getItemCount() && DepartmentMemberAddActivity.this.f10519p.getVisibility() == 8 && DepartmentMemberAddActivity.this.f10513j * 50 < DepartmentMemberAddActivity.this.f10514k) {
                DepartmentMemberAddActivity departmentMemberAddActivity = DepartmentMemberAddActivity.this;
                departmentMemberAddActivity.m12192e0(departmentMemberAddActivity.f10511h, DepartmentMemberAddActivity.this.f10507d, DepartmentMemberAddActivity.this.f10513j, AppMeasurementSdk.ConditionalUserProperty.NAME);
            }
        }
    }

    /* renamed from: com.cyberlink.you.activity.friend.DepartmentMemberAddActivity$b */
    public class C2121b extends PromisedTask.AbstractC4504d<DepartmentMemberList> {
        public C2121b() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: b */
        public /* synthetic */ void m12196b(DialogInterface dialogInterface, int i9) {
            DepartmentMemberAddActivity.this.m12189b0();
        }

        @Override // com.perfectcorp.utility.PromisedTask.AbstractC4504d
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public void onDone(DepartmentMemberList departmentMemberList) {
            List<DepartmentMemberList.UserInfo> list;
            if (DepartmentMemberAddActivity.this.m7364e()) {
                Log.e(DepartmentMemberAddActivity.f10505w, "activity is destroyed, stop loadDepartment");
                return;
            }
            if (departmentMemberList != null && (list = departmentMemberList.results) != null && list.size() > 0) {
                if (DepartmentMemberAddActivity.this.f10514k != departmentMemberList.totalSize && DepartmentMemberAddActivity.this.f10514k > 0) {
                    DepartmentMemberAddActivity.this.f10517n = true;
                    int iIndexOf = DepartmentMemberAddActivity.this.f10506c.indexOf(40);
                    DepartmentMemberAddActivity departmentMemberAddActivity = DepartmentMemberAddActivity.this;
                    departmentMemberAddActivity.f10506c = departmentMemberAddActivity.f10506c.substring(0, iIndexOf);
                    DepartmentMemberAddActivity.this.f10506c = DepartmentMemberAddActivity.this.f10506c + "(" + departmentMemberList.totalSize + ")";
                    DepartmentMemberAddActivity.this.f10521r.setText(DepartmentMemberAddActivity.this.f10506c);
                }
                DepartmentMemberAddActivity.this.f10514k = departmentMemberList.totalSize;
                DepartmentMemberAddActivity.this.f10515l.addAll(DepartmentMemberAddActivity.this.m12186T(departmentMemberList.results));
                DepartmentMemberAddActivity.this.m12193g0();
                DepartmentMemberAddActivity.this.m12194i0();
                if (DepartmentMemberAddActivity.this.f10516m != null) {
                    DepartmentMemberAddActivity.this.f10516m.m3370s(DepartmentMemberAddActivity.this.f10515l);
                    DepartmentMemberAddActivity.this.f10516m.notifyDataSetChanged();
                }
            }
            DepartmentMemberAddActivity.m12161E(DepartmentMemberAddActivity.this);
            DepartmentMemberAddActivity.this.f10519p.setVisibility(8);
        }

        @Override // com.perfectcorp.utility.PromisedTask
        public void onError(int i9) {
            if (DepartmentMemberAddActivity.this.m7364e()) {
                Log.e(DepartmentMemberAddActivity.f10505w, "activity is destroyed, stop loadDepartment");
                return;
            }
            if (i9 == NetworkManager.NetworkErrorCode.E_NOT_INITIALIZED.getValue()) {
                ULogUtility.m16670f(DepartmentMemberAddActivity.f10505w, "[queryDepartmentMembers] api does not initialize");
                return;
            }
            if (Arrays.asList(401, 403, 404).contains(Integer.valueOf(i9))) {
                Globals.m7388i0().m7625u4("");
                DepartmentMemberAddActivity.this.f10518o = true;
                new AlertDialog.Builder(DepartmentMemberAddActivity.this).setMessage(R.string.organization_error_message).setPositiveButton(R.string.close, new DialogInterface.OnClickListener() { // from class: b3.w
                    @Override // android.content.DialogInterface.OnClickListener
                    public final void onClick(DialogInterface dialogInterface, int i10) {
                        this.f3268b.m12196b(dialogInterface, i10);
                    }
                }).show();
            } else {
                C5187v0.m20267c(R.string.error_server_response);
            }
            DepartmentMemberAddActivity.this.f10519p.setVisibility(8);
        }
    }

    /* renamed from: com.cyberlink.you.activity.friend.DepartmentMemberAddActivity$c */
    public class C2122c extends PromisedTask.AbstractC4504d<OrgContactList> {
        public C2122c() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: b */
        public /* synthetic */ void m12199b(DialogInterface dialogInterface, int i9) {
            DepartmentMemberAddActivity.this.m12189b0();
        }

        @Override // com.perfectcorp.utility.PromisedTask.AbstractC4504d
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public void onDone(OrgContactList orgContactList) {
            if (DepartmentMemberAddActivity.this.m7364e()) {
                Log.e(DepartmentMemberAddActivity.f10505w, "activity is destroyed, stop loadDepartment");
                return;
            }
            if (orgContactList != null && orgContactList.directories.results != null && orgContactList.contacts.results != null) {
                if (DepartmentMemberAddActivity.this.f10514k != orgContactList.directories.totalSize + orgContactList.contacts.totalSize && DepartmentMemberAddActivity.this.f10514k > 0) {
                    DepartmentMemberAddActivity.this.f10517n = true;
                }
                DepartmentMemberAddActivity.this.f10514k = orgContactList.directories.totalSize + orgContactList.contacts.totalSize;
                DepartmentMemberAddActivity.this.f10521r.setText(DepartmentMemberAddActivity.this.f10506c);
                if (DepartmentMemberAddActivity.this.f10514k > 0) {
                    List<OrgContactList.OrgContacts.ContactInfo> list = orgContactList.directories.results;
                    ArrayList arrayList = new ArrayList();
                    for (OrgContactList.OrgContacts.ContactInfo contactInfo : list) {
                        if (!contactInfo.hidden.booleanValue()) {
                            arrayList.add(contactInfo);
                        }
                    }
                    DepartmentMemberAddActivity.this.f10515l.addAll(DepartmentMemberAddActivity.this.m12185Q(arrayList));
                    List<OrgContactList.OrgContacts.ContactInfo> list2 = orgContactList.contacts.results;
                    ArrayList arrayList2 = new ArrayList();
                    for (OrgContactList.OrgContacts.ContactInfo contactInfo2 : list2) {
                        if (!contactInfo2.hidden.booleanValue()) {
                            arrayList2.add(contactInfo2);
                        }
                    }
                    DepartmentMemberAddActivity.this.f10515l.addAll(DepartmentMemberAddActivity.this.m12185Q(arrayList2));
                    DepartmentMemberAddActivity.this.m12193g0();
                    DepartmentMemberAddActivity.this.m12194i0();
                    if (DepartmentMemberAddActivity.this.f10516m != null) {
                        DepartmentMemberAddActivity.this.f10516m.m3370s(DepartmentMemberAddActivity.this.f10515l);
                        DepartmentMemberAddActivity.this.f10516m.notifyDataSetChanged();
                    }
                } else {
                    DepartmentMemberAddActivity.this.f10522s.setVisibility(0);
                }
            }
            DepartmentMemberAddActivity.m12161E(DepartmentMemberAddActivity.this);
            DepartmentMemberAddActivity.this.f10519p.setVisibility(8);
        }

        @Override // com.perfectcorp.utility.PromisedTask
        public void onError(int i9) {
            if (DepartmentMemberAddActivity.this.m7364e()) {
                Log.e(DepartmentMemberAddActivity.f10505w, "activity is destroyed, stop loadDepartment");
                return;
            }
            if (i9 == NetworkManager.NetworkErrorCode.E_NOT_INITIALIZED.getValue()) {
                ULogUtility.m16670f(DepartmentMemberAddActivity.f10505w, "[queryDepartmentMembers] api does not initialize");
                return;
            }
            if (Arrays.asList(401, 403, 404).contains(Integer.valueOf(i9))) {
                Globals.m7388i0().m7625u4("");
                DepartmentMemberAddActivity.this.f10518o = true;
                new AlertDialog.Builder(DepartmentMemberAddActivity.this).setMessage(R.string.organization_error_message).setPositiveButton(R.string.close, new DialogInterface.OnClickListener() { // from class: b3.x
                    @Override // android.content.DialogInterface.OnClickListener
                    public final void onClick(DialogInterface dialogInterface, int i10) {
                        this.f3271b.m12199b(dialogInterface, i10);
                    }
                }).show();
            } else {
                C5187v0.m20267c(R.string.error_server_response);
            }
            DepartmentMemberAddActivity.this.f10519p.setVisibility(8);
        }
    }

    /* renamed from: E */
    public static /* synthetic */ int m12161E(DepartmentMemberAddActivity departmentMemberAddActivity) {
        int i9 = departmentMemberAddActivity.f10513j;
        departmentMemberAddActivity.f10513j = i9 + 1;
        return i9;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: Y */
    public /* synthetic */ void m12169Y(View view) {
        m12189b0();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: Z */
    public /* synthetic */ void m12170Z(View view) {
        m12190c0();
    }

    /* renamed from: Q */
    public final List<DepartmentFriend> m12185Q(List<OrgContactList.OrgContacts.ContactInfo> list) {
        ArrayList arrayList = new ArrayList();
        for (OrgContactList.OrgContacts.ContactInfo contactInfo : list) {
            DepartmentFriend departmentFriend = new DepartmentFriend();
            if (C5170o0.m20170e(contactInfo.directoryKey)) {
                departmentFriend.f13641q = false;
                departmentFriend.f13629e = contactInfo.displayName;
                Long l9 = contactInfo.uid;
                if (l9 != null) {
                    departmentFriend.f13626b = l9.longValue();
                }
                if (!C5170o0.m20170e(contactInfo.avatar)) {
                    departmentFriend.f13631g = contactInfo.avatar;
                }
                if (!C5170o0.m20170e(contactInfo.email)) {
                    departmentFriend.f13638n = contactInfo.email;
                }
                if (!C5170o0.m20170e(contactInfo.jobTitle)) {
                    departmentFriend.f13639o = contactInfo.jobTitle;
                }
            } else {
                departmentFriend.f13641q = true;
                departmentFriend.f13629e = contactInfo.name;
                departmentFriend.f13640p = contactInfo.f15986id;
                departmentFriend.f13642r = contactInfo.totalContacts.longValue() - contactInfo.hiddenContacts.longValue();
            }
            arrayList.add(departmentFriend);
        }
        return arrayList;
    }

    /* renamed from: T */
    public final List<DepartmentFriend> m12186T(List<DepartmentMemberList.UserInfo> list) {
        ArrayList arrayList = new ArrayList();
        for (DepartmentMemberList.UserInfo userInfo : list) {
            DepartmentFriend departmentFriend = new DepartmentFriend();
            String str = userInfo.uId;
            if (str == null) {
                str = "-1";
            }
            departmentFriend.f13626b = Long.valueOf(str).longValue();
            departmentFriend.f13629e = userInfo.displayName;
            departmentFriend.f13631g = userInfo.avatar;
            departmentFriend.f13641q = false;
            arrayList.add(departmentFriend);
        }
        return arrayList;
    }

    /* renamed from: V */
    public final int m12187V() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.DepartmentMemberList);
        if (this.f10516m == null || !(recyclerView.getLayoutManager() instanceof LinearLayoutManager)) {
            return 0;
        }
        return ((LinearLayoutManager) recyclerView.getLayoutManager()).m2286a2();
    }

    /* renamed from: X */
    public final void m12188X() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.DepartmentMemberList);
        C0656t c0656t = new C0656t(this, this.f10510g, this.f10515l, C5170o0.m20170e(this.f10509f) ? this.f10506c : this.f10509f);
        this.f10516m = c0656t;
        recyclerView.setAdapter(c0656t);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new C0476e(this, linearLayoutManager.m2303n2()));
        recyclerView.addOnScrollListener(this.f10525v);
        ((ImageView) findViewById(R.id.SelectDepartmentBackBtn)).setOnClickListener(this.f10523t);
        ImageView imageView = (ImageView) findViewById(R.id.SelectDepartmentBackToTopBtn);
        if (this.f10508e) {
            imageView.setOnClickListener(this.f10524u);
        } else {
            imageView.setVisibility(8);
        }
        TextView textView = (TextView) findViewById(R.id.DepartmentRootTitle);
        this.f10520q = textView;
        textView.setVisibility(8);
        TextView textView2 = (TextView) findViewById(R.id.DepartmentTitle);
        this.f10521r = textView2;
        textView2.setText(this.f10506c);
        this.f10519p = (ProgressBar) findViewById(R.id.departmentMemberProgressbar);
        this.f10522s = (TextView) findViewById(R.id.noResultsTextView);
    }

    /* renamed from: b0 */
    public final void m12189b0() {
        Intent intent = new Intent();
        intent.putExtra("department_update", this.f10517n);
        intent.putExtra("department_error", this.f10518o);
        setResult(-1, intent);
        finish();
    }

    /* renamed from: c0 */
    public final void m12190c0() {
        Intent intent = new Intent(this, (Class<?>) FriendAddActivity.class);
        intent.addFlags(67108864);
        startActivity(intent);
    }

    /* renamed from: d0 */
    public final void m12191d0(String str, String str2, int i9) {
        this.f10519p.setVisibility(0);
        NetworkLive.listContacts(str, str2, i9, 50).done(new C2122c());
    }

    /* renamed from: e0 */
    public final void m12192e0(String str, String str2, int i9, String str3) {
        this.f10519p.setVisibility(0);
        NetworkLive.listDepartmentMembers(str, str2, i9, 50, str3).done(new C2121b());
    }

    /* renamed from: g0 */
    public final void m12193g0() {
        for (Friend friend : C2950b0.m14899A().m15024p()) {
            Iterator<DepartmentFriend> it = this.f10515l.iterator();
            while (true) {
                if (it.hasNext()) {
                    DepartmentFriend next = it.next();
                    if (next.f13626b == friend.f13645c) {
                        next.f13637m = true;
                        break;
                    }
                }
            }
        }
    }

    /* renamed from: i0 */
    public final void m12194i0() {
        for (InvitationFriend invitationFriend : this.f10510g.m15702J(FriendsClient.InvitationFriendType.SENT)) {
            Iterator<DepartmentFriend> it = this.f10515l.iterator();
            while (true) {
                if (it.hasNext()) {
                    DepartmentFriend next = it.next();
                    if (next.f13626b == invitationFriend.f13746g) {
                        next.f13635k = true;
                        next.f13628d = invitationFriend.f13744e;
                        break;
                    }
                }
            }
        }
    }

    @Override // android.app.Activity
    public void onActivityResult(int i9, int i10, Intent intent) {
        super.onActivityResult(i9, i10, intent);
        if (i9 == 1) {
            if (i10 == -1) {
                if (FriendProfileActivity.FPA_RETURN_TYPE.RETURN_CANCEL.name().equals(intent.getStringExtra("type"))) {
                    this.f10516m.m3368k((DepartmentFriend) intent.getParcelableExtra("departmentFriend"));
                    return;
                }
                return;
            }
            return;
        }
        if (i9 == 2 && i10 == -1) {
            if (FriendProfileActivity.FPA_RETURN_TYPE.RETURN_ADD_FRIEND.name().equals(intent.getStringExtra("type"))) {
                this.f10516m.m3361m((DepartmentFriend) intent.getParcelableExtra("departmentFriend"));
            }
        }
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        m12189b0();
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_department_member_add);
        this.f10510g = new FriendsClient(true);
        Intent intent = getIntent();
        if (intent != null) {
            this.f10506c = intent.getStringExtra("department_name");
            this.f10507d = intent.getStringExtra("department_id");
            this.f10508e = intent.getBooleanExtra("department_is_contacts", false);
            this.f10509f = intent.getStringExtra("department_root_name");
        }
        m12188X();
        String strM7506X = Globals.m7388i0().m7506X();
        this.f10511h = strM7506X;
        if (this.f10508e) {
            m12191d0(strM7506X, this.f10507d, this.f10513j);
        } else {
            m12192e0(strM7506X, this.f10507d, this.f10513j, AppMeasurementSdk.ConditionalUserProperty.NAME);
        }
    }
}
