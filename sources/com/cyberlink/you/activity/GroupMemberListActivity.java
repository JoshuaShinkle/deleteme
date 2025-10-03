package com.cyberlink.you.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.cyberlink.p030U.R;
import com.cyberlink.you.BaseActivity;
import com.cyberlink.you.Globals;
import com.cyberlink.you.activity.friend.FriendProfileActivity;
import com.cyberlink.you.activity.selectfromfriendgroup.SelectFromFriendGroupActivity;
import com.cyberlink.you.friends.Friend;
import com.cyberlink.you.friends.FriendsClient;
import com.cyberlink.you.friends.Group;
import com.cyberlink.you.utility.CLUtility;
import com.google.android.exoplayer2.extractor.p037ts.TsExtractor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import p116k4.C5152i0;
import p116k4.C5172p;
import p173q2.C6127a;
import p201t3.C6301o;
import p209u2.C6385v;

/* loaded from: classes.dex */
public class GroupMemberListActivity extends BaseActivity {

    /* renamed from: c */
    public FriendsClient f7876c;

    /* renamed from: d */
    public TextView f7877d;

    /* renamed from: e */
    public EditText f7878e;

    /* renamed from: f */
    public View f7879f;

    /* renamed from: g */
    public View f7880g;

    /* renamed from: l */
    public ListView f7885l;

    /* renamed from: m */
    public C1517h f7886m;

    /* renamed from: n */
    public ProgressDialog f7887n;

    /* renamed from: t */
    public FriendsClient.InterfaceC3051i f7893t;

    /* renamed from: h */
    public Group f7881h = null;

    /* renamed from: i */
    public int f7882i = 0;

    /* renamed from: j */
    public List<Friend> f7883j = null;

    /* renamed from: k */
    public boolean f7884k = true;

    /* renamed from: o */
    public AdapterView.OnItemClickListener f7888o = new C1510a();

    /* renamed from: p */
    public View.OnClickListener f7889p = new View.OnClickListener() { // from class: com.cyberlink.you.activity.p6
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f11036b.m8533E(view);
        }
    };

    /* renamed from: q */
    public View.OnClickListener f7890q = new ViewOnClickListenerC1511b();

    /* renamed from: r */
    public View.OnClickListener f7891r = new ViewOnClickListenerC1512c();

    /* renamed from: s */
    public TextWatcher f7892s = new C1513d();

    /* renamed from: com.cyberlink.you.activity.GroupMemberListActivity$a */
    public class C1510a implements AdapterView.OnItemClickListener {
        public C1510a() {
        }

        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView<?> adapterView, View view, int i9, long j9) {
            Friend item = GroupMemberListActivity.this.f7886m.getItem(i9);
            if (item == null) {
                return;
            }
            Bundle bundle = new Bundle();
            bundle.putParcelable("friendObj", item);
            bundle.putString("type", FriendProfileActivity.FPA_PROFILE_TYPE.PROFILE_TYPE_VIEW_FRIEND.name());
            Intent intent = new Intent();
            intent.setClass(GroupMemberListActivity.this.m8552z(), FriendProfileActivity.class);
            intent.putExtras(bundle);
            GroupMemberListActivity.this.m8552z().startActivityForResult(intent, 1);
        }
    }

    /* renamed from: com.cyberlink.you.activity.GroupMemberListActivity$b */
    public class ViewOnClickListenerC1511b implements View.OnClickListener {
        public ViewOnClickListenerC1511b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (GroupMemberListActivity.this.f7886m == null) {
                return;
            }
            Intent intent = new Intent(GroupMemberListActivity.this.getApplicationContext(), (Class<?>) SelectFromFriendGroupActivity.class);
            Bundle bundle = new Bundle();
            List<Friend> listM8565g = GroupMemberListActivity.this.f7886m.m8565g();
            ArrayList arrayList = new ArrayList(listM8565g.size());
            arrayList.addAll(listM8565g);
            Globals.C1408b.m7655b().m7657d("INTENT_CANNOT_MODIFIED_USERS_LIST", arrayList);
            Globals.C1408b.m7655b().m7657d("INTENT_PREV_SELECTED_USERS", arrayList);
            bundle.putParcelable("Group", GroupMemberListActivity.this.f7881h);
            intent.putExtras(bundle);
            GroupMemberListActivity.this.startActivityForResult(intent, 0);
        }
    }

    /* renamed from: com.cyberlink.you.activity.GroupMemberListActivity$c */
    public class ViewOnClickListenerC1512c implements View.OnClickListener {
        public ViewOnClickListenerC1512c() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (GroupMemberListActivity.this.f7886m == null) {
                return;
            }
            GroupMemberListActivity.this.startActivity(new Intent(GroupMemberListActivity.this.getApplicationContext(), (Class<?>) OfficialAccountActivity.class));
        }
    }

    /* renamed from: com.cyberlink.you.activity.GroupMemberListActivity$d */
    public class C1513d implements TextWatcher {
        public C1513d() {
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i9, int i10, int i11) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i9, int i10, int i11) {
            if (GroupMemberListActivity.this.f7886m != null) {
                GroupMemberListActivity.this.f7886m.getFilter().filter(charSequence.toString());
            }
        }
    }

    /* renamed from: com.cyberlink.you.activity.GroupMemberListActivity$e */
    public class AsyncTaskC1514e extends AsyncTask<Void, Void, Void> {
        public AsyncTaskC1514e() {
        }

        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public Void doInBackground(Void... voidArr) {
            GroupMemberListActivity groupMemberListActivity = GroupMemberListActivity.this;
            groupMemberListActivity.f7883j = groupMemberListActivity.f7881h.m15747c();
            return null;
        }

        @Override // android.os.AsyncTask
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(Void r62) {
            List<Friend> list = GroupMemberListActivity.this.f7883j;
            if (list != null) {
                Collections.sort(list, new Friend.C3041b());
                GroupMemberListActivity groupMemberListActivity = GroupMemberListActivity.this;
                GroupMemberListActivity groupMemberListActivity2 = GroupMemberListActivity.this;
                groupMemberListActivity.f7886m = groupMemberListActivity2.new C1517h(groupMemberListActivity2.m8552z(), R.layout.view_item_friends_group_edit, GroupMemberListActivity.this.f7883j);
                GroupMemberListActivity.this.f7885l.setAdapter((ListAdapter) GroupMemberListActivity.this.f7886m);
                GroupMemberListActivity.this.f7885l.setOnItemClickListener(GroupMemberListActivity.this.f7888o);
                GroupMemberListActivity.this.f7886m.getFilter().filter(GroupMemberListActivity.this.f7878e.getText().toString());
            }
        }
    }

    /* renamed from: com.cyberlink.you.activity.GroupMemberListActivity$f */
    public class AsyncTaskC1515f extends AsyncTask<Void, Void, Void> {
        public AsyncTaskC1515f() {
        }

        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public Void doInBackground(Void... voidArr) {
            GroupMemberListActivity groupMemberListActivity = GroupMemberListActivity.this;
            groupMemberListActivity.f7883j = groupMemberListActivity.f7881h.m15746b();
            return null;
        }

        @Override // android.os.AsyncTask
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(Void r62) {
            List<Friend> list = GroupMemberListActivity.this.f7883j;
            if (list != null) {
                Collections.sort(list, new Friend.C3041b());
                GroupMemberListActivity groupMemberListActivity = GroupMemberListActivity.this;
                GroupMemberListActivity groupMemberListActivity2 = GroupMemberListActivity.this;
                groupMemberListActivity.f7886m = groupMemberListActivity2.new C1517h(groupMemberListActivity2.m8552z(), R.layout.view_item_friends_group_edit, GroupMemberListActivity.this.f7883j);
                GroupMemberListActivity.this.f7885l.setAdapter((ListAdapter) GroupMemberListActivity.this.f7886m);
                GroupMemberListActivity.this.f7885l.setOnItemClickListener(GroupMemberListActivity.this.f7888o);
                GroupMemberListActivity.this.f7886m.getFilter().filter(GroupMemberListActivity.this.f7878e.getText().toString());
            }
        }
    }

    /* renamed from: com.cyberlink.you.activity.GroupMemberListActivity$g */
    public class C1516g implements FriendsClient.InterfaceC3051i {

        /* renamed from: a */
        public boolean f7900a = false;

        /* renamed from: b */
        public final /* synthetic */ List f7901b;

        public C1516g(List list) {
            this.f7901b = list;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: c */
        public /* synthetic */ void m8558c(List list) {
            if (this.f7900a) {
                Iterator it = list.iterator();
                while (it.hasNext()) {
                    GroupMemberListActivity.this.f7886m.m8563e((Friend) it.next());
                }
                GroupMemberListActivity.this.f7886m.m8567i();
                GroupMemberListActivity.this.f7886m.getFilter().filter(GroupMemberListActivity.this.f7878e.getText().toString());
                GroupMemberListActivity.this.f7886m.notifyDataSetChanged();
                GroupMemberListActivity.this.m8549I();
            }
            if (GroupMemberListActivity.this.isFinishing()) {
                return;
            }
            C5152i0.m20065b(GroupMemberListActivity.this.f7887n);
        }

        @Override // com.cyberlink.you.friends.FriendsClient.InterfaceC3051i
        /* renamed from: a */
        public void mo134a(String str, String str2, String str3, String str4) {
            if (str3 != null && str3.equals("200")) {
                GroupMemberListActivity.this.f7881h.m15758p(C5172p.m20186h(C5172p.m20195q(str4)));
                this.f7900a = true;
            }
            Activity activityM8552z = GroupMemberListActivity.this.m8552z();
            final List list = this.f7901b;
            activityM8552z.runOnUiThread(new Runnable() { // from class: com.cyberlink.you.activity.q6
                @Override // java.lang.Runnable
                public final void run() {
                    this.f11073b.m8558c(list);
                }
            });
        }
    }

    /* renamed from: com.cyberlink.you.activity.GroupMemberListActivity$h */
    public class C1517h extends ArrayAdapter<Friend> implements Filterable {

        /* renamed from: b */
        public List<Friend> f7903b;

        /* renamed from: c */
        public List<Friend> f7904c;

        /* renamed from: d */
        public a f7905d;

        /* renamed from: e */
        public final Object f7906e;

        /* renamed from: com.cyberlink.you.activity.GroupMemberListActivity$h$a */
        public class a extends Filter {
            public a() {
            }

            @Override // android.widget.Filter
            public Filter.FilterResults performFiltering(CharSequence charSequence) {
                String lowerCase = charSequence.toString().toLowerCase(Locale.getDefault());
                Filter.FilterResults filterResults = new Filter.FilterResults();
                ArrayList arrayList = new ArrayList();
                synchronized (C1517h.this.f7906e) {
                    for (Friend friend : C1517h.this.f7903b) {
                        if (friend.m15621b().toLowerCase(Locale.getDefault()).contains(lowerCase)) {
                            arrayList.add(friend);
                        }
                    }
                }
                filterResults.values = arrayList;
                filterResults.count = arrayList.size();
                return filterResults;
            }

            @Override // android.widget.Filter
            public void publishResults(CharSequence charSequence, Filter.FilterResults filterResults) {
                C1517h.this.f7904c = (ArrayList) filterResults.values;
                C1517h.this.notifyDataSetChanged();
                if (C1517h.this.f7904c.size() > 0) {
                    GroupMemberListActivity.this.f7879f.setVisibility(8);
                } else {
                    GroupMemberListActivity.this.f7879f.setVisibility(0);
                }
            }

            public /* synthetic */ a(C1517h c1517h, C1510a c1510a) {
                this();
            }
        }

        public C1517h(Context context, int i9, List<Friend> list) {
            super(context, i9, list);
            this.f7906e = new Object();
            this.f7903b = list;
            this.f7904c = list;
        }

        /* renamed from: e */
        public void m8563e(Friend friend) {
            synchronized (this.f7906e) {
                List<Friend> list = this.f7903b;
                if (list != null && !list.contains(friend)) {
                    this.f7903b.add(friend);
                }
            }
        }

        @Override // android.widget.ArrayAdapter, android.widget.Adapter
        /* renamed from: f, reason: merged with bridge method [inline-methods] */
        public Friend getItem(int i9) {
            return this.f7904c.get(i9);
        }

        /* renamed from: g */
        public List<Friend> m8565g() {
            List<Friend> list;
            synchronized (this.f7906e) {
                list = this.f7903b;
            }
            return list;
        }

        @Override // android.widget.ArrayAdapter, android.widget.Adapter
        public int getCount() {
            return this.f7904c.size();
        }

        @Override // android.widget.ArrayAdapter, android.widget.Filterable
        public Filter getFilter() {
            if (this.f7905d == null) {
                this.f7905d = new a(this, null);
            }
            return this.f7905d;
        }

        @Override // android.widget.ArrayAdapter, android.widget.Adapter
        public View getView(int i9, View view, ViewGroup viewGroup) {
            C1518i c1518i;
            TextView textView;
            if (view == null) {
                view = ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(R.layout.view_item_friends_group_edit, viewGroup, false);
                c1518i = new C1518i(GroupMemberListActivity.this, null);
                c1518i.f7910b = (TextView) view.findViewById(R.id.GroupEditFriendTextView);
                c1518i.f7909a = (ImageView) view.findViewById(R.id.GroupEditCircleImageView);
                Button button = (Button) view.findViewById(R.id.GroupEditItemBtn);
                c1518i.f7911c = button;
                button.setVisibility(8);
                view.setTag(c1518i);
            } else {
                c1518i = (C1518i) view.getTag();
            }
            Friend friend = this.f7904c.get(i9);
            c1518i.f7911c.setTag(friend);
            if (friend.m15621b() != null && (textView = c1518i.f7910b) != null) {
                textView.setText(friend.m15621b());
            }
            C6127a.m23469j(GroupMemberListActivity.this, c1518i.f7909a, friend);
            return view;
        }

        @Override // android.widget.ArrayAdapter
        /* renamed from: h, reason: merged with bridge method [inline-methods] */
        public void remove(Friend friend) {
            synchronized (this.f7906e) {
                this.f7903b.remove(friend);
                this.f7904c.remove(friend);
            }
            notifyDataSetChanged();
        }

        /* renamed from: i */
        public void m8567i() {
            synchronized (this.f7906e) {
                List<Friend> list = this.f7903b;
                if (list != null) {
                    Collections.sort(list, new Friend.C3041b());
                }
            }
        }
    }

    /* renamed from: com.cyberlink.you.activity.GroupMemberListActivity$i */
    public class C1518i {

        /* renamed from: a */
        public ImageView f7909a;

        /* renamed from: b */
        public TextView f7910b;

        /* renamed from: c */
        public Button f7911c;

        public C1518i() {
        }

        public /* synthetic */ C1518i(GroupMemberListActivity groupMemberListActivity, C1510a c1510a) {
            this();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: E */
    public /* synthetic */ void m8533E(View view) {
        m8548H();
    }

    /* renamed from: B */
    public final void m8545B() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            this.f7881h = (Group) extras.getParcelable("Group");
            this.f7882i = extras.getInt("type", 0);
            this.f7883j = extras.getParcelableArrayList("originalMembers");
            this.f7884k = extras.getBoolean("isGroupMember", true);
        }
    }

    /* renamed from: C */
    public final void m8546C() {
        List<Friend> list = this.f7883j;
        if (list != null) {
            Collections.sort(list, new Friend.C3041b());
            C1517h c1517h = new C1517h(m8552z(), R.layout.view_item_friends_group_edit, this.f7883j);
            this.f7886m = c1517h;
            this.f7885l.setAdapter((ListAdapter) c1517h);
            this.f7885l.setOnItemClickListener(this.f7888o);
            this.f7886m.getFilter().filter(this.f7878e.getText().toString());
            return;
        }
        this.f7880g.setVisibility(8);
        int i9 = this.f7882i;
        if (i9 == 0) {
            new AsyncTaskC1514e().executeOnExecutor(C6385v.f21554b, new Void[0]);
        } else if (i9 == 2) {
            new AsyncTaskC1515f().executeOnExecutor(C6385v.f21554b, new Void[0]);
        } else {
            Log.d("GroupMemberListActivity", "Get Extras from bundle fail: originalMembers");
            finish();
        }
    }

    /* renamed from: D */
    public final void m8547D() {
        EditText editText = (EditText) findViewById(R.id.SearchEditText);
        this.f7878e = editText;
        editText.addTextChangedListener(this.f7892s);
        this.f7877d = (TextView) findViewById(R.id.GroupMemberListTitle);
        this.f7885l = (ListView) findViewById(R.id.GroupMemberListListView);
        this.f7879f = findViewById(R.id.GroupMemberListSearchEmptyView);
        findViewById(R.id.GroupMemberListBackBtn).setOnClickListener(this.f7889p);
        View viewFindViewById = findViewById(R.id.GroupMemberListAddFriendArea);
        this.f7880g = viewFindViewById;
        if (this.f7884k) {
            viewFindViewById.setVisibility(0);
        } else {
            viewFindViewById.setVisibility(4);
        }
        m8549I();
        int i9 = this.f7882i;
        if (i9 == 0) {
            this.f7880g.setOnClickListener(this.f7890q);
        } else if (i9 == 1) {
            this.f7880g.setOnClickListener(this.f7891r);
        }
    }

    /* renamed from: H */
    public final void m8548H() {
        CLUtility.m16589t1(this);
        m8552z().setResult(-1, new Intent());
        finish();
    }

    /* renamed from: I */
    public final void m8549I() {
        int i9 = this.f7882i;
        if (i9 == 0) {
            Group group = this.f7881h;
            if (group != null) {
                this.f7877d.setText(getString(R.string.members_D, Long.valueOf(group.f13728o)));
            }
        } else if (i9 == 2) {
            Group group2 = this.f7881h;
            if (group2 != null) {
                this.f7877d.setText(getString(R.string.admins_D, Long.valueOf(group2.f13729p)));
            }
        } else if (i9 == 1) {
            this.f7877d.setText(R.string.official_accounts);
        }
        m8550j();
    }

    /* renamed from: j */
    public final void m8550j() {
        this.f7877d.setMaxWidth(Globals.m7388i0().getResources().getDisplayMetrics().widthPixels - (((int) Math.ceil(((r0.widthPixels / 1080.0f) * 128.0f) * (150 / TsExtractor.TS_STREAM_TYPE_SPLICE_INFO))) * 2));
    }

    @Override // android.app.Activity
    public void onActivityResult(int i9, int i10, Intent intent) {
        super.onActivityResult(i9, i10, intent);
        if (i9 == 0) {
            if (i10 != -1 || intent.getExtras() == null) {
                return;
            }
            m8551y(Globals.C1408b.m7655b().m7656c("INTENT_RESULT_SELECTED_USERS_LIST"));
            return;
        }
        if (i9 == 1 && i10 == -1) {
            Intent intent2 = new Intent();
            Bundle extras = intent.getExtras();
            if (extras != null) {
                Group group = (Group) extras.getParcelable("StartOtherGroup");
                if (group != null) {
                    intent2.putExtra("StartOtherGroup", group);
                }
                try {
                    intent2.putExtra("type", extras.getString("type"));
                } catch (Exception e9) {
                    Log.e("GroupMemberListActivity", "[onActivityResult] REQUEST_SHOW_MEMBER_LIST: " + e9.getMessage());
                }
                try {
                    intent2.putExtra("userId", extras.getLong("userId"));
                } catch (Exception e10) {
                    Log.e("GroupMemberListActivity", "[onActivityResult] REQUEST_SHOW_MEMBER_LIST: " + e10.getMessage());
                }
            }
            m8552z().setResult(-1, intent2);
            finish();
        }
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        m8548H();
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_group_member_list);
        this.f7876c = new FriendsClient(true);
        m8545B();
        m8547D();
        m8546C();
    }

    /* renamed from: y */
    public final void m8551y(List<Friend> list) {
        if (list == null || list.size() <= 0) {
            return;
        }
        this.f7887n = ProgressDialog.show(this, "", getString(R.string.processing));
        ArrayList arrayList = new ArrayList();
        arrayList.add(new C6301o("token", Globals.m7388i0().m7449L()));
        arrayList.add(new C6301o("groupId", Long.toString(this.f7881h.f13727n)));
        Iterator<Friend> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(new C6301o("userId", Long.toString(it.next().f13645c)));
        }
        this.f7893t = new C1516g(list);
        arrayList.add(new C6301o("addOrJoin2Group", "add2Group"));
        this.f7876c.m15734m("group", "addMembers", arrayList, this.f7893t);
    }

    /* renamed from: z */
    public final Activity m8552z() {
        return this;
    }
}
