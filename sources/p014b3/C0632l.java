package p014b3;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import com.cyberlink.p030U.R;
import com.cyberlink.you.Globals;
import com.cyberlink.you.activity.friend.C2143a;
import com.cyberlink.you.activity.friend.FRIEND_ADD_TYPE;
import com.cyberlink.you.database.C2950b0;
import com.cyberlink.you.friends.DepartmentFriend;
import com.cyberlink.you.friends.Friend;
import com.cyberlink.you.friends.FriendsClient;
import com.cyberlink.you.friends.InvitationFriend;
import com.cyberlink.you.friends.SuggestionFriend;
import com.cyberlink.you.p036ui.C3123g;
import com.cyberlink.you.utility.CLUtility;
import com.perfectcorp.ycl.p040bc.model.DepartmentList;
import com.perfectcorp.ycl.p040bc.model.OrgContactList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import p116k4.AbstractC5161l0;
import p116k4.C5152i0;
import p116k4.C5168n1;
import p116k4.C5170o0;
import p116k4.C5173p0;
import p116k4.C5187v0;
import p173q2.C6127a;
import p201t3.C6301o;

/* renamed from: b3.l */
/* loaded from: classes.dex */
public class C0632l extends BaseExpandableListAdapter implements Filterable {

    /* renamed from: b */
    public final Activity f3197b;

    /* renamed from: c */
    public final FriendsClient f3198c;

    /* renamed from: e */
    public List<DepartmentList.Department> f3200e;

    /* renamed from: f */
    public List<OrgContactList.OrgContacts.ContactInfo> f3201f;

    /* renamed from: h */
    public List<SuggestionFriend> f3203h;

    /* renamed from: i */
    public List<SuggestionFriend> f3204i;

    /* renamed from: g */
    public List<DepartmentFriend> f3202g = new ArrayList();

    /* renamed from: j */
    public List<SuggestionFriend> f3205j = new ArrayList();

    /* renamed from: k */
    public List<InvitationFriend> f3206k = new ArrayList();

    /* renamed from: l */
    public List<Integer> f3207l = new ArrayList();

    /* renamed from: m */
    public List<List<?>> f3208m = new ArrayList();

    /* renamed from: n */
    public boolean f3209n = false;

    /* renamed from: o */
    public boolean f3210o = true;

    /* renamed from: p */
    public b f3211p = new b();

    /* renamed from: d */
    public long f3199d = Globals.m7388i0().m7398A0();

    /* renamed from: b3.l$b */
    public class b extends AbstractC5161l0<SuggestionFriend> {
        public b() {
        }

        @Override // p116k4.AbstractC5161l0
        /* renamed from: a */
        public List<SuggestionFriend> mo3351a() {
            return C0632l.this.f3203h;
        }

        @Override // p116k4.AbstractC5161l0
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public boolean mo3352c(SuggestionFriend suggestionFriend, String str, Object obj) {
            return m20104d(suggestionFriend.f13761e).contains(str) || m20104d(suggestionFriend.f13767k).contains(str);
        }

        @Override // android.widget.Filter
        public void publishResults(CharSequence charSequence, Filter.FilterResults filterResults) {
            C0632l.this.f3204i = (ArrayList) filterResults.values;
            C0632l.this.notifyDataSetChanged();
        }
    }

    public C0632l(Activity activity, FriendsClient friendsClient, List<SuggestionFriend> list, List<DepartmentList.Department> list2, List<OrgContactList.OrgContacts.ContactInfo> list3) {
        this.f3197b = activity;
        this.f3198c = friendsClient;
        this.f3203h = new ArrayList(list);
        this.f3204i = list;
        this.f3200e = list2;
        this.f3201f = list3;
        m3327P();
        m3326O();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: D */
    public /* synthetic */ void m3299D(DepartmentFriend departmentFriend, View view) {
        m3335X(departmentFriend);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: E */
    public /* synthetic */ void m3300E(SuggestionFriend suggestionFriend, View view) {
        if (suggestionFriend.f13768l) {
            return;
        }
        m3336Y(suggestionFriend);
    }

    /* renamed from: F */
    public static /* synthetic */ void m3301F(ProgressDialog progressDialog, String str, String str2, String str3, String str4) {
        if ("200".equals(str3)) {
            C5187v0.m20267c(R.string.success);
        }
        C5152i0.m20065b(progressDialog);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: H */
    public /* synthetic */ void m3303H(final SuggestionFriend suggestionFriend, ProgressDialog progressDialog, String str, String str2, String str3, String str4) {
        if ("200".equals(str3)) {
            C5187v0.m20267c(R.string.success);
            this.f3197b.runOnUiThread(new Runnable() { // from class: b3.b
                @Override // java.lang.Runnable
                public final void run() {
                    this.f3149b.m3302G(suggestionFriend);
                }
            });
        }
        C5152i0.m20065b(progressDialog);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: I */
    public /* synthetic */ void m3304I(EditText editText, SuggestionFriend suggestionFriend, Dialog dialog, View view) {
        String string = editText.getText().toString();
        Globals.m7388i0().m7462N3(string);
        m3347w(suggestionFriend, string);
        dialog.dismiss();
        CLUtility.m16589t1(this.f3197b);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: J */
    public /* synthetic */ void m3305J(Dialog dialog, View view) {
        dialog.dismiss();
        CLUtility.m16589t1(this.f3197b);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: L */
    public /* synthetic */ void m3307L(EditText editText, DepartmentFriend departmentFriend, Dialog dialog, View view) {
        String string = editText.getText().toString();
        Globals.m7388i0().m7462N3(string);
        m3346v(departmentFriend, string);
        dialog.dismiss();
        CLUtility.m16589t1(this.f3197b);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: M */
    public /* synthetic */ void m3308M(Dialog dialog, View view) {
        dialog.dismiss();
        CLUtility.m16589t1(this.f3197b);
    }

    /* renamed from: A */
    public boolean m3323A() {
        return this.f3201f.size() == 0;
    }

    /* renamed from: B */
    public boolean m3324B() {
        return this.f3205j.size() == 0;
    }

    /* renamed from: C */
    public boolean m3325C() {
        return this.f3202g.size() == 0;
    }

    /* renamed from: O */
    public final void m3326O() {
        this.f3208m.clear();
        for (Integer num : this.f3207l) {
            if (num.intValue() == FRIEND_ADD_TYPE.DEPARTMENT.m12202b()) {
                this.f3208m.add(this.f3200e);
            } else if (num.intValue() == FRIEND_ADD_TYPE.ORG_CONTACTS.m12202b()) {
                this.f3208m.add(this.f3201f);
            } else if (num.intValue() == FRIEND_ADD_TYPE.ORG_CONTACTS_SEARCH.m12202b()) {
                this.f3208m.add(this.f3202g);
            } else if (num.intValue() == FRIEND_ADD_TYPE.SUGGESTION.m12202b()) {
                this.f3208m.add(this.f3204i);
            } else {
                if (num.intValue() != FRIEND_ADD_TYPE.SUGGESTION_ON_U.m12202b()) {
                    throw new IllegalArgumentException("refreshData key [ " + num + " ] is not valid");
                }
                this.f3208m.add(this.f3205j);
            }
        }
    }

    /* renamed from: P */
    public final void m3327P() {
        this.f3207l.clear();
        if (!m3349y() && !this.f3209n) {
            this.f3207l.add(Integer.valueOf(FRIEND_ADD_TYPE.DEPARTMENT.m12202b()));
        }
        if (!m3323A() && !this.f3209n) {
            this.f3207l.add(Integer.valueOf(FRIEND_ADD_TYPE.ORG_CONTACTS.m12202b()));
        }
        if (!m3325C() && this.f3209n) {
            this.f3207l.add(Integer.valueOf(FRIEND_ADD_TYPE.ORG_CONTACTS_SEARCH.m12202b()));
        }
        if (!m3350z()) {
            this.f3207l.add(Integer.valueOf(FRIEND_ADD_TYPE.SUGGESTION.m12202b()));
        }
        if (!this.f3209n || m3324B()) {
            return;
        }
        this.f3207l.add(Integer.valueOf(FRIEND_ADD_TYPE.SUGGESTION_ON_U.m12202b()));
    }

    /* renamed from: Q, reason: merged with bridge method [inline-methods] */
    public void m3302G(SuggestionFriend suggestionFriend) {
        boolean zRemove = this.f3203h.remove(suggestionFriend);
        boolean zRemove2 = this.f3205j.remove(suggestionFriend);
        if (zRemove) {
            C5173p0.m20207e().m20209d(this.f3203h);
            this.f3204i.remove(suggestionFriend);
        }
        if (zRemove || zRemove2) {
            notifyDataSetChanged();
        }
    }

    /* renamed from: R */
    public void m3329R(List<DepartmentList.Department> list) {
        this.f3200e = list;
    }

    /* renamed from: S */
    public void m3330S(boolean z8) {
        this.f3210o = z8;
    }

    /* renamed from: T */
    public void m3331T(List<OrgContactList.OrgContacts.ContactInfo> list) {
        this.f3201f = list;
    }

    /* renamed from: U */
    public void m3332U(boolean z8) {
        this.f3209n = z8;
    }

    /* renamed from: V */
    public void m3333V(List<DepartmentFriend> list) {
        this.f3202g = list;
    }

    /* renamed from: W */
    public void m3334W(List<SuggestionFriend> list) {
        this.f3203h.clear();
        this.f3203h.addAll(list);
    }

    /* renamed from: X */
    public void m3335X(final DepartmentFriend departmentFriend) {
        if (departmentFriend.f13626b <= 0) {
            if (C5170o0.m20170e(departmentFriend.f13638n)) {
                C5187v0.m20267c(R.string.error_server_response);
                return;
            } else {
                CLUtility.m16418A2(this.f3197b, null, CLUtility.InviteType.EMail, new String[]{departmentFriend.f13638n});
                return;
            }
        }
        final Dialog dialogM16384c = C3123g.m16384c(this.f3197b);
        View viewInflate = LayoutInflater.from(this.f3197b).inflate(R.layout.dialog_friend_invitation, (ViewGroup) null);
        TextView textView = (TextView) viewInflate.findViewById(R.id.invitationDialogTitle);
        final EditText editText = (EditText) viewInflate.findViewById(R.id.invitationEdit);
        textView.setText(this.f3197b.getString(R.string.friend_invitation_info, departmentFriend.f13629e));
        editText.setText(Globals.m7388i0().m7561j0());
        viewInflate.findViewById(R.id.sendInvitationBtn).setOnClickListener(new View.OnClickListener() { // from class: b3.d
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f3159b.m3307L(editText, departmentFriend, dialogM16384c, view);
            }
        });
        viewInflate.findViewById(R.id.cancelinvitationBtn).setOnClickListener(new View.OnClickListener() { // from class: b3.e
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f3166b.m3308M(dialogM16384c, view);
            }
        });
        viewInflate.findViewById(R.id.invitationXImage).setOnClickListener(new View.OnClickListener() { // from class: b3.f
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                editText.setText("");
            }
        });
        dialogM16384c.setContentView(viewInflate);
        CLUtility.m16590t2(this.f3197b, dialogM16384c);
        dialogM16384c.show();
    }

    /* renamed from: Y */
    public void m3336Y(final SuggestionFriend suggestionFriend) {
        final Dialog dialogM16384c = C3123g.m16384c(this.f3197b);
        View viewInflate = LayoutInflater.from(this.f3197b).inflate(R.layout.dialog_friend_invitation, (ViewGroup) null);
        TextView textView = (TextView) viewInflate.findViewById(R.id.invitationDialogTitle);
        final EditText editText = (EditText) viewInflate.findViewById(R.id.invitationEdit);
        textView.setText(this.f3197b.getString(R.string.friend_invitation_info, suggestionFriend.f13761e));
        editText.setText(Globals.m7388i0().m7561j0());
        viewInflate.findViewById(R.id.sendInvitationBtn).setOnClickListener(new View.OnClickListener() { // from class: b3.g
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f3175b.m3304I(editText, suggestionFriend, dialogM16384c, view);
            }
        });
        viewInflate.findViewById(R.id.cancelinvitationBtn).setOnClickListener(new View.OnClickListener() { // from class: b3.h
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f3182b.m3305J(dialogM16384c, view);
            }
        });
        viewInflate.findViewById(R.id.invitationXImage).setOnClickListener(new View.OnClickListener() { // from class: b3.i
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                editText.setText("");
            }
        });
        dialogM16384c.setContentView(viewInflate);
        CLUtility.m16590t2(this.f3197b, dialogM16384c);
        dialogM16384c.show();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:8:0x001b  */
    /* renamed from: Z */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void m3337Z(TextView textView, SuggestionFriend suggestionFriend) {
        String str = suggestionFriend.f13762f;
        if (str == null || str.isEmpty()) {
            textView.setVisibility(8);
        }
        char c9 = 0;
        textView.setVisibility(0);
        String str2 = suggestionFriend.f13762f;
        str2.hashCode();
        switch (str2.hashCode()) {
            case -2053572135:
                if (!str2.equals("GroupMember")) {
                    c9 = 65535;
                    break;
                }
                break;
            case -1526893321:
                if (str2.equals("FriendInvitation")) {
                    c9 = 1;
                    break;
                }
                break;
            case -1300765023:
                if (str2.equals("FacebookId")) {
                    c9 = 2;
                    break;
                }
                break;
            case 1418010492:
                if (str2.equals("ContactEmail")) {
                    c9 = 3;
                    break;
                }
                break;
            case 1428033870:
                if (str2.equals("ContactPhone")) {
                    c9 = 4;
                    break;
                }
                break;
        }
        switch (c9) {
            case 0:
                textView.setText(R.string.suggestion_friend_source_group_friend);
                break;
            case 1:
                textView.setText(R.string.suggestion_friend_source_invitation);
                break;
            case 2:
                textView.setText(R.string.suggestion_friend_source_facebook);
                break;
            case 3:
                textView.setText(R.string.suggestion_friend_source_email);
                break;
            case 4:
                textView.setText(R.string.suggestion_friend_source_phone_contact);
                break;
            default:
                textView.setText(R.string.suggestion_friend_source_others);
                break;
        }
    }

    @Override // android.widget.ExpandableListAdapter
    public Object getChild(int i9, int i10) {
        return this.f3208m.get(i9).get(i10);
    }

    @Override // android.widget.ExpandableListAdapter
    public long getChildId(int i9, int i10) {
        return i10;
    }

    @Override // android.widget.ExpandableListAdapter
    public View getChildView(int i9, int i10, boolean z8, View view, ViewGroup viewGroup) {
        View viewInflate = LayoutInflater.from(viewGroup.getContext()).inflate(FRIEND_ADD_TYPE.m12201a(m3344t(i9)).m12203c(), viewGroup, false);
        if (FRIEND_ADD_TYPE.DEPARTMENT.m12202b() == m3344t(i9)) {
            ((TextView) viewInflate.findViewById(R.id.Department)).setText(m3342r(this.f3200e.get(i10).name));
            ((TextView) viewInflate.findViewById(R.id.DepartmentCount)).setText("(" + this.f3200e.get(i10).memberCount + ")");
        } else if (FRIEND_ADD_TYPE.ORG_CONTACTS.m12202b() == m3344t(i9)) {
            ((TextView) viewInflate.findViewById(R.id.Department)).setText(m3342r(this.f3201f.get(i10).name));
            ((TextView) viewInflate.findViewById(R.id.DepartmentCount)).setText("(" + (this.f3201f.get(i10).totalContacts.longValue() - this.f3201f.get(i10).hiddenContacts.longValue()) + ")");
        } else if (FRIEND_ADD_TYPE.ORG_CONTACTS_SEARCH.m12202b() == m3344t(i9)) {
            ImageView imageView = (ImageView) viewInflate.findViewById(R.id.DepartmentMemberAvatar);
            Button button = (Button) viewInflate.findViewById(R.id.DepartmentMemberAddBtn);
            TextView textView = (TextView) viewInflate.findViewById(R.id.DepartmentMemberDisplayName);
            viewInflate.findViewById(R.id.DepartmentMemberHintIcon).setVisibility(8);
            final DepartmentFriend departmentFriend = this.f3202g.get(i10);
            if (departmentFriend != null) {
                textView.setText(m3342r(departmentFriend.f13630f));
                if (departmentFriend.f13626b > 0) {
                    Friend friendM15003C = C2950b0.m14899A().m15003C(Long.toString(departmentFriend.f13626b));
                    if (friendM15003C == null || !C5168n1.m20149f(friendM15003C.f13645c)) {
                        C6127a.m23474o(this.f3197b, imageView, m3340p(departmentFriend.f13626b), R.drawable.pic_default);
                    } else {
                        C6127a.m23469j(this.f3197b, imageView, friendM15003C);
                    }
                } else {
                    C6127a.m23468i(this.f3197b, imageView, departmentFriend);
                }
                if (departmentFriend.f13626b == Globals.m7388i0().m7568k1().longValue() || departmentFriend.f13637m || departmentFriend.f13626b <= 0) {
                    button.setVisibility(8);
                } else {
                    button.setVisibility(0);
                    button.setOnClickListener(new View.OnClickListener() { // from class: b3.a
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view2) {
                            this.f3145b.m3299D(departmentFriend, view2);
                        }
                    });
                }
            }
        } else {
            if (Arrays.asList(Integer.valueOf(FRIEND_ADD_TYPE.SUGGESTION.m12202b()), Integer.valueOf(FRIEND_ADD_TYPE.SUGGESTION_ON_U.m12202b())).contains(Integer.valueOf(m3344t(i9)))) {
                Button button2 = (Button) viewInflate.findViewById(R.id.FriendInvitationSuggestionAddBtn);
                ImageView imageView2 = (ImageView) viewInflate.findViewById(R.id.FriendInvitationSuggestionDisplayImage);
                TextView textView2 = (TextView) viewInflate.findViewById(R.id.FriendInvitationSuggestionDisplayName);
                TextView textView3 = (TextView) viewInflate.findViewById(R.id.FriendInvitationSuggestionSourceType);
                ViewGroup viewGroup2 = (ViewGroup) viewInflate.findViewById(R.id.FriendInvitationSuggestionNewIconImg);
                final SuggestionFriend suggestionFriend = (SuggestionFriend) getChild(i9, i10);
                button2.setOnClickListener(new View.OnClickListener() { // from class: b3.c
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view2) {
                        this.f3153b.m3300E(suggestionFriend, view2);
                    }
                });
                C6127a.m23472m(this.f3197b, imageView2, suggestionFriend);
                textView2.setText(suggestionFriend.f13761e);
                m3337Z(textView3, suggestionFriend);
                button2.setVisibility((suggestionFriend.f13768l || (suggestionFriend.f13759c == Globals.m7388i0().m7568k1().longValue())) ? 4 : 0);
                button2.setTag(suggestionFriend);
                if (suggestionFriend.f13768l || suggestionFriend.f13765i <= this.f3199d) {
                    viewGroup2.setVisibility(8);
                } else {
                    viewGroup2.setVisibility(0);
                }
            }
        }
        return viewInflate;
    }

    @Override // android.widget.ExpandableListAdapter
    public int getChildrenCount(int i9) {
        return this.f3208m.get(i9).size();
    }

    @Override // android.widget.Filterable
    public Filter getFilter() {
        return this.f3211p;
    }

    @Override // android.widget.ExpandableListAdapter
    public Object getGroup(int i9) {
        return this.f3208m.get(i9);
    }

    @Override // android.widget.ExpandableListAdapter
    public int getGroupCount() {
        return this.f3208m.size();
    }

    @Override // android.widget.ExpandableListAdapter
    public long getGroupId(int i9) {
        return i9;
    }

    @Override // android.widget.ExpandableListAdapter
    public View getGroupView(int i9, boolean z8, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_item_department_titles, viewGroup, false);
        }
        if (this.f3210o && FRIEND_ADD_TYPE.DEPARTMENT.m12202b() == m3344t(i9)) {
            view.findViewById(R.id.departmentProgressbar).setVisibility(0);
        } else if (this.f3210o && FRIEND_ADD_TYPE.ORG_CONTACTS.m12202b() == m3344t(i9)) {
            view.findViewById(R.id.departmentProgressbar).setVisibility(0);
        } else {
            view.findViewById(R.id.departmentProgressbar).setVisibility(4);
        }
        ((TextView) view.findViewById(R.id.DepartmentTitleDisplayName)).setText(m3343s(i9));
        ((ImageView) view.findViewById(R.id.DepartmentTitleExpand)).setImageResource(z8 ? R.drawable.icon_expand_up : R.drawable.icon_expand_down);
        ExpandableListView expandableListView = (ExpandableListView) viewGroup;
        if (Globals.m7388i0().m7492U().charAt(m3344t(i9)) == '1') {
            expandableListView.expandGroup(i9);
        } else {
            expandableListView.collapseGroup(i9);
        }
        return view;
    }

    @Override // android.widget.ExpandableListAdapter
    public boolean hasStableIds() {
        return true;
    }

    @Override // android.widget.ExpandableListAdapter
    public boolean isChildSelectable(int i9, int i10) {
        return true;
    }

    /* renamed from: n */
    public void m3338n(List<SuggestionFriend> list) {
        boolean z8;
        ArrayList arrayList = new ArrayList();
        this.f3206k = this.f3198c.m15702J(FriendsClient.InvitationFriendType.SENT);
        for (SuggestionFriend suggestionFriend : list) {
            Iterator<InvitationFriend> it = this.f3206k.iterator();
            while (true) {
                if (it.hasNext()) {
                    if (it.next().f13746g == suggestionFriend.f13759c) {
                        z8 = false;
                        break;
                    }
                } else {
                    z8 = true;
                    break;
                }
            }
            if (z8) {
                arrayList.add(suggestionFriend);
            }
        }
        this.f3205j.addAll(arrayList);
        notifyDataSetChanged();
    }

    @Override // android.widget.BaseExpandableListAdapter
    public void notifyDataSetChanged() {
        m3327P();
        m3326O();
        super.notifyDataSetChanged();
    }

    /* renamed from: o */
    public void m3339o() {
        this.f3202g.clear();
        this.f3205j.clear();
        notifyDataSetChanged();
    }

    @Override // android.widget.BaseExpandableListAdapter, android.widget.ExpandableListAdapter
    public void onGroupCollapsed(int i9) {
        Globals.m7388i0().m7619t4(m3344t(i9), false);
    }

    @Override // android.widget.BaseExpandableListAdapter, android.widget.ExpandableListAdapter
    public void onGroupExpanded(int i9) {
        Globals.m7388i0().m7619t4(m3344t(i9), true);
    }

    /* renamed from: p */
    public final String m3340p(long j9) {
        String strM15642G = FriendsClient.m15642G("info", C2143a.m12415o(j9) ? "userAvatarHostGCP" : "userAvatarHost");
        if (C5170o0.m20170e(strM15642G)) {
            return null;
        }
        return strM15642G + "/01/" + (j9 % 5203) + "/" + j9 + "/avatar.jpg";
    }

    /* renamed from: q */
    public long m3341q() {
        long jLongValue = 0;
        for (OrgContactList.OrgContacts.ContactInfo contactInfo : this.f3201f) {
            Long l9 = contactInfo.totalContacts;
            if (l9 != null && contactInfo.hiddenContacts != null) {
                jLongValue += l9.longValue() - contactInfo.hiddenContacts.longValue();
            }
        }
        return jLongValue;
    }

    /* renamed from: r */
    public final String m3342r(String str) {
        return TextUtils.isEmpty(str) ? this.f3197b.getString(R.string.ungrouped) : str;
    }

    /* renamed from: s */
    public final String m3343s(int i9) {
        int iM3344t = m3344t(i9);
        String string = this.f3197b.getString(FRIEND_ADD_TYPE.m12201a(iM3344t).m12204d());
        return iM3344t == FRIEND_ADD_TYPE.DEPARTMENT.m12202b() ? C2143a.m12400E(string, Long.valueOf(m3345u())) : (iM3344t != FRIEND_ADD_TYPE.ORG_CONTACTS.m12202b() || this.f3209n) ? (iM3344t != FRIEND_ADD_TYPE.SUGGESTION.m12202b() || this.f3209n) ? string : C2143a.m12400E(string.replace(":", ""), Long.valueOf(this.f3204i.size())) : C2143a.m12400E(string, Long.valueOf(m3341q()));
    }

    /* renamed from: t */
    public int m3344t(int i9) {
        return this.f3207l.get(i9).intValue();
    }

    /* renamed from: u */
    public long m3345u() {
        Iterator<DepartmentList.Department> it = this.f3200e.iterator();
        long jLongValue = 0;
        while (it.hasNext()) {
            jLongValue += it.next().memberCount.longValue();
        }
        return jLongValue;
    }

    /* renamed from: v */
    public final void m3346v(DepartmentFriend departmentFriend, String str) {
        Activity activity = this.f3197b;
        final ProgressDialog progressDialogShow = ProgressDialog.show(activity, "", activity.getString(R.string.processing));
        ArrayList arrayList = new ArrayList();
        arrayList.add(new C6301o("token", Globals.m7388i0().m7449L()));
        arrayList.add(new C6301o("inviteeId", String.valueOf(departmentFriend.f13626b)));
        arrayList.add(new C6301o("message", str));
        this.f3198c.m15734m("invite", "inviteFriend", arrayList, new FriendsClient.InterfaceC3051i() { // from class: b3.k
            @Override // com.cyberlink.you.friends.FriendsClient.InterfaceC3051i
            /* renamed from: a */
            public final void mo134a(String str2, String str3, String str4, String str5) {
                C0632l.m3301F(progressDialogShow, str2, str3, str4, str5);
            }
        });
    }

    /* renamed from: w */
    public final void m3347w(final SuggestionFriend suggestionFriend, String str) {
        Activity activity = this.f3197b;
        final ProgressDialog progressDialogShow = ProgressDialog.show(activity, "", activity.getString(R.string.processing));
        ArrayList arrayList = new ArrayList();
        arrayList.add(new C6301o("token", Globals.m7388i0().m7449L()));
        arrayList.add(new C6301o("inviteeId", String.valueOf(suggestionFriend.f13759c)));
        arrayList.add(new C6301o("message", str));
        this.f3198c.m15734m("invite", "inviteFriend", arrayList, new FriendsClient.InterfaceC3051i() { // from class: b3.j
            @Override // com.cyberlink.you.friends.FriendsClient.InterfaceC3051i
            /* renamed from: a */
            public final void mo134a(String str2, String str3, String str4, String str5) {
                this.f3189a.m3303H(suggestionFriend, progressDialogShow, str2, str3, str4, str5);
            }
        });
    }

    /* renamed from: x */
    public boolean m3348x() {
        return this.f3207l.size() == 0;
    }

    /* renamed from: y */
    public boolean m3349y() {
        return this.f3200e.size() == 0;
    }

    /* renamed from: z */
    public boolean m3350z() {
        return this.f3204i.size() == 0;
    }
}
