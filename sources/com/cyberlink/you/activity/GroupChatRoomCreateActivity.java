package com.cyberlink.you.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.cyberlink.p030U.R;
import com.cyberlink.you.BaseActivity;
import com.cyberlink.you.Globals;
import com.cyberlink.you.database.C2950b0;
import com.cyberlink.you.friends.Friend;
import com.cyberlink.you.friends.FriendsClient;
import com.cyberlink.you.friends.Group;
import com.cyberlink.you.utility.CLUtility;
import com.cyberlink.you.widgetpool.tokenautocomplete.PeopleCompleteView;
import com.cyberlink.you.widgetpool.tokenautocomplete.TokenCompleteTextView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import org.json.JSONException;
import org.json.JSONObject;
import p116k4.AbstractC5161l0;
import p116k4.C5172p;
import p173q2.C6127a;
import p201t3.C6301o;

/* loaded from: classes.dex */
public class GroupChatRoomCreateActivity extends BaseActivity {

    /* renamed from: c */
    public FriendsClient f7728c;

    /* renamed from: d */
    public List<Friend> f7729d;

    /* renamed from: e */
    public ListView f7730e;

    /* renamed from: f */
    public C1486e f7731f;

    /* renamed from: g */
    public TextView f7732g;

    /* renamed from: h */
    public PeopleCompleteView f7733h;

    /* renamed from: i */
    public View f7734i;

    /* renamed from: j */
    public View f7735j;

    /* renamed from: k */
    public TextView f7736k;

    /* renamed from: m */
    public View f7738m;

    /* renamed from: l */
    public Set<Long> f7737l = Collections.synchronizedSet(new TreeSet());

    /* renamed from: n */
    public View.OnClickListener f7739n = new View.OnClickListener() { // from class: com.cyberlink.you.activity.a4
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f9705b.m8242D(view);
        }
    };

    /* renamed from: o */
    public View.OnClickListener f7740o = new ViewOnClickListenerC1482a();

    /* renamed from: p */
    public AdapterView.OnItemClickListener f7741p = new C1483b();

    /* renamed from: q */
    public AbsListView.OnScrollListener f7742q = new C1485d();

    /* renamed from: com.cyberlink.you.activity.GroupChatRoomCreateActivity$a */
    public class ViewOnClickListenerC1482a implements View.OnClickListener {
        public ViewOnClickListenerC1482a() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: c */
        public /* synthetic */ void m8263c() {
            GroupChatRoomCreateActivity.this.f7738m.setVisibility(8);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: d */
        public /* synthetic */ void m8264d(String str, String str2, String str3, String str4) {
            if ("200".equals(str3)) {
                Group groupM8258E = GroupChatRoomCreateActivity.this.m8258E(str4);
                Group groupM15077n = C2950b0.m14912k().m15077n(String.valueOf(groupM8258E.f13727n));
                if (groupM15077n != null) {
                    groupM8258E.f13714M = groupM15077n.f13714M;
                }
                C2950b0.m14912k().m15069f(groupM8258E);
                Intent intent = new Intent();
                intent.putExtra("Group", groupM8258E);
                GroupChatRoomCreateActivity.this.setResult(-1, intent);
                GroupChatRoomCreateActivity.this.finish();
            }
            GroupChatRoomCreateActivity.this.runOnUiThread(new Runnable() { // from class: com.cyberlink.you.activity.c4
                @Override // java.lang.Runnable
                public final void run() {
                    this.f9770b.m8263c();
                }
            });
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (GroupChatRoomCreateActivity.this.f7737l.size() <= 0) {
                return;
            }
            GroupChatRoomCreateActivity.this.f7738m.setVisibility(0);
            ArrayList arrayList = new ArrayList();
            arrayList.add(new C6301o("token", Globals.m7388i0().m7449L()));
            int size = GroupChatRoomCreateActivity.this.f7737l.size();
            Iterator it = GroupChatRoomCreateActivity.this.f7737l.iterator();
            while (it.hasNext()) {
                arrayList.add(new C6301o("userId", Long.toString(((Long) it.next()).longValue())));
            }
            if (size == 1) {
                arrayList.add(new C6301o("groupType", "Dual"));
            } else {
                arrayList.add(new C6301o("groupType", "ChatRoom"));
            }
            GroupChatRoomCreateActivity.this.f7728c.m15734m("group", "create", arrayList, new FriendsClient.InterfaceC3051i() { // from class: com.cyberlink.you.activity.b4
                @Override // com.cyberlink.you.friends.FriendsClient.InterfaceC3051i
                /* renamed from: a */
                public final void mo134a(String str, String str2, String str3, String str4) {
                    this.f9739a.m8264d(str, str2, str3, str4);
                }
            });
        }
    }

    /* renamed from: com.cyberlink.you.activity.GroupChatRoomCreateActivity$b */
    public class C1483b implements AdapterView.OnItemClickListener {
        public C1483b() {
        }

        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView<?> adapterView, View view, int i9, long j9) {
            C1487f c1487f = (C1487f) view.getTag();
            Friend friendM8275d = GroupChatRoomCreateActivity.this.f7731f.m8275d(i9);
            Long lValueOf = Long.valueOf(friendM8275d.f13645c);
            boolean z8 = !GroupChatRoomCreateActivity.this.f7737l.contains(lValueOf);
            if (GroupChatRoomCreateActivity.this.f7737l.contains(lValueOf)) {
                GroupChatRoomCreateActivity.this.f7737l.remove(lValueOf);
            } else {
                GroupChatRoomCreateActivity.this.f7737l.add(lValueOf);
            }
            c1487f.f7757c.setChecked(z8);
            if (z8) {
                GroupChatRoomCreateActivity.this.f7733h.m17434p(friendM8275d);
            } else {
                GroupChatRoomCreateActivity.this.f7733h.m17426S(friendM8275d);
            }
            GroupChatRoomCreateActivity.this.m8259H();
        }
    }

    /* renamed from: com.cyberlink.you.activity.GroupChatRoomCreateActivity$c */
    public class C1484c implements TokenCompleteTextView.InterfaceC3249e<Friend> {
        public C1484c() {
        }

        @Override // com.cyberlink.you.widgetpool.tokenautocomplete.TokenCompleteTextView.InterfaceC3249e
        /* renamed from: a */
        public void mo5801a(String str) {
            if (GroupChatRoomCreateActivity.this.f7731f != null) {
                GroupChatRoomCreateActivity.this.f7731f.getFilter().filter(str);
            }
        }

        @Override // com.cyberlink.you.widgetpool.tokenautocomplete.TokenCompleteTextView.InterfaceC3249e
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public void mo5802b(Friend friend) {
        }

        @Override // com.cyberlink.you.widgetpool.tokenautocomplete.TokenCompleteTextView.InterfaceC3249e
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public void mo5803c(Friend friend) {
            GroupChatRoomCreateActivity.this.f7737l.remove(Long.valueOf(friend.f13645c));
            GroupChatRoomCreateActivity.this.f7731f.notifyDataSetChanged();
            GroupChatRoomCreateActivity.this.m8259H();
        }
    }

    /* renamed from: com.cyberlink.you.activity.GroupChatRoomCreateActivity$d */
    public class C1485d implements AbsListView.OnScrollListener {

        /* renamed from: a */
        public boolean f7746a = false;

        /* renamed from: b */
        public int f7747b = -1;

        /* renamed from: c */
        public String f7748c = null;

        public C1485d() {
        }

        /* renamed from: a */
        public final String m8267a(String str) {
            return str.length() > 0 ? String.valueOf(Character.toChars(str.codePointAt(0))) : "";
        }

        /* renamed from: b */
        public final void m8268b() {
            if (GroupChatRoomCreateActivity.this.f7735j != null && GroupChatRoomCreateActivity.this.f7735j.isShown()) {
                GroupChatRoomCreateActivity.this.f7735j.setVisibility(4);
            }
            m8269c();
        }

        /* renamed from: c */
        public final void m8269c() {
            this.f7747b = -1;
            this.f7748c = null;
        }

        /* renamed from: d */
        public final void m8270d() {
            Friend friend = (Friend) GroupChatRoomCreateActivity.this.f7731f.getItem(GroupChatRoomCreateActivity.this.f7730e.getFirstVisiblePosition());
            if (friend != null) {
                String strM15621b = friend.m15621b();
                if (GroupChatRoomCreateActivity.this.f7736k != null) {
                    String strM8267a = m8267a(strM15621b);
                    if (strM8267a.equals(this.f7748c)) {
                        return;
                    }
                    GroupChatRoomCreateActivity.this.f7736k.setText(strM8267a);
                    m8271e();
                    this.f7748c = strM8267a;
                }
            }
        }

        /* renamed from: e */
        public final void m8271e() {
            if (GroupChatRoomCreateActivity.this.f7735j != null && !GroupChatRoomCreateActivity.this.f7735j.isShown()) {
                GroupChatRoomCreateActivity.this.f7735j.setVisibility(0);
            }
            m8269c();
        }

        @Override // android.widget.AbsListView.OnScrollListener
        public void onScroll(AbsListView absListView, int i9, int i10, int i11) {
            int firstVisiblePosition;
            if (this.f7746a && this.f7747b != (firstVisiblePosition = GroupChatRoomCreateActivity.this.f7730e.getFirstVisiblePosition())) {
                this.f7747b = firstVisiblePosition;
                m8270d();
            }
        }

        @Override // android.widget.AbsListView.OnScrollListener
        public void onScrollStateChanged(AbsListView absListView, int i9) {
            if (i9 == 0) {
                this.f7746a = false;
                m8268b();
            } else {
                if (i9 != 1) {
                    return;
                }
                CLUtility.m16589t1(GroupChatRoomCreateActivity.this.m8260z());
                this.f7746a = true;
                m8269c();
            }
        }
    }

    /* renamed from: com.cyberlink.you.activity.GroupChatRoomCreateActivity$e */
    public class C1486e extends ArrayAdapter<Friend> implements Filterable {

        /* renamed from: b */
        public List<Friend> f7750b;

        /* renamed from: c */
        public List<Friend> f7751c;

        /* renamed from: d */
        public a f7752d;

        /* renamed from: com.cyberlink.you.activity.GroupChatRoomCreateActivity$e$a */
        public class a extends AbstractC5161l0<Friend> {
            public a() {
            }

            @Override // p116k4.AbstractC5161l0
            /* renamed from: a */
            public List<Friend> mo3351a() {
                return C1486e.this.f7750b;
            }

            @Override // p116k4.AbstractC5161l0
            /* renamed from: e, reason: merged with bridge method [inline-methods] */
            public boolean mo3352c(Friend friend, String str, Object obj) {
                return m20104d(friend.m15621b()).contains(str);
            }

            @Override // android.widget.Filter
            public void publishResults(CharSequence charSequence, Filter.FilterResults filterResults) {
                C1486e.this.f7751c = (ArrayList) filterResults.values;
                C1486e.this.notifyDataSetChanged();
                GroupChatRoomCreateActivity.this.f7734i.setVisibility(C1486e.this.f7751c.size() > 0 ? 8 : 0);
            }

            public /* synthetic */ a(C1486e c1486e, ViewOnClickListenerC1482a viewOnClickListenerC1482a) {
                this();
            }
        }

        public C1486e(Context context, int i9, List<Friend> list) {
            super(context, i9, list);
            this.f7750b = list;
            this.f7751c = list;
        }

        /* renamed from: d */
        public Friend m8275d(int i9) {
            return this.f7751c.get(i9);
        }

        @Override // android.widget.ArrayAdapter, android.widget.Adapter
        public int getCount() {
            return this.f7751c.size();
        }

        @Override // android.widget.ArrayAdapter, android.widget.Filterable
        public Filter getFilter() {
            if (this.f7752d == null) {
                this.f7752d = new a(this, null);
            }
            return this.f7752d;
        }

        @Override // android.widget.ArrayAdapter, android.widget.Adapter
        public View getView(int i9, View view, ViewGroup viewGroup) {
            View view2;
            C1487f c1487f;
            if (view == null) {
                c1487f = new C1487f(GroupChatRoomCreateActivity.this, getContext(), viewGroup, null);
                view2 = c1487f.f7758d;
                view2.setTag(c1487f);
            } else {
                view2 = view;
                c1487f = (C1487f) view.getTag();
            }
            Friend friend = this.f7751c.get(i9);
            c1487f.f7756b.setText(friend.m15621b());
            C6127a.m23469j(GroupChatRoomCreateActivity.this, c1487f.f7755a, friend);
            c1487f.f7757c.setChecked(GroupChatRoomCreateActivity.this.f7737l.contains(Long.valueOf(friend.f13645c)));
            return view2;
        }
    }

    /* renamed from: com.cyberlink.you.activity.GroupChatRoomCreateActivity$f */
    public class C1487f {

        /* renamed from: a */
        public ImageView f7755a;

        /* renamed from: b */
        public TextView f7756b;

        /* renamed from: c */
        public CheckBox f7757c;

        /* renamed from: d */
        public View f7758d;

        public /* synthetic */ C1487f(GroupChatRoomCreateActivity groupChatRoomCreateActivity, Context context, ViewGroup viewGroup, ViewOnClickListenerC1482a viewOnClickListenerC1482a) {
            this(context, viewGroup);
        }

        public C1487f(Context context, ViewGroup viewGroup) {
            View viewInflate = LayoutInflater.from(context).inflate(R.layout.view_item_firend_group_create, viewGroup, false);
            this.f7758d = viewInflate;
            this.f7756b = (TextView) viewInflate.findViewById(R.id.GroupCreateMemberName);
            this.f7755a = (ImageView) this.f7758d.findViewById(R.id.GroupCreateImageView);
            this.f7757c = (CheckBox) this.f7758d.findViewById(R.id.GroupCreateMemberCheckBox);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: D */
    public /* synthetic */ void m8242D(View view) {
        finish();
    }

    /* renamed from: B */
    public final void m8256B() {
        List<Friend> listM15026r = C2950b0.m14899A().m15026r();
        this.f7729d = listM15026r;
        Collections.sort(listM15026r, new Friend.C3041b());
        C1486e c1486e = new C1486e(m8260z(), R.layout.view_item_firend_group_create, this.f7729d);
        this.f7731f = c1486e;
        this.f7730e.setAdapter((ListAdapter) c1486e);
        this.f7730e.setOnItemClickListener(this.f7741p);
        this.f7730e.setOnScrollListener(this.f7742q);
    }

    /* renamed from: C */
    public final void m8257C() {
        PeopleCompleteView peopleCompleteView = (PeopleCompleteView) findViewById(R.id.token_complete);
        this.f7733h = peopleCompleteView;
        peopleCompleteView.setTokenListener(new C1484c());
        this.f7730e = (ListView) findViewById(R.id.ChatAddMemberListView);
        this.f7732g = (TextView) findViewById(R.id.buttonDone);
        this.f7734i = findViewById(R.id.ChatAddMemberSearchEmptyView);
        this.f7735j = findViewById(R.id.AlphabeticScrollTextLayout);
        this.f7736k = (TextView) findViewById(R.id.AlphabeticScrollTextView);
        findViewById(R.id.ChatAddMemberBackBtn).setOnClickListener(this.f7739n);
        this.f7732g.setOnClickListener(this.f7740o);
        this.f7738m = findViewById(R.id.loading);
        m8259H();
    }

    /* renamed from: E */
    public final Group m8258E(String str) {
        try {
            try {
                return C5172p.m20186h(new JSONObject(str).getJSONObject("result"));
            } catch (JSONException unused) {
                Log.e("GroupChatRoomCreateACT", "[FriendListFriends] 'results' missing. JSONstr=" + str);
                return null;
            }
        } catch (JSONException unused2) {
            Log.e("GroupChatRoomCreateACT", "[FriendListFriends] Parse error. JSONstr=" + str);
            return null;
        }
    }

    /* renamed from: H */
    public final void m8259H() {
        if (this.f7732g == null) {
            return;
        }
        int size = this.f7737l.size();
        this.f7732g.setText(getResources().getString(R.string.done_btn) + " (" + size + ")");
        this.f7732g.setEnabled(size > 0);
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_chat_add_member);
        this.f7728c = new FriendsClient(true);
        m8257C();
        m8256B();
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        this.f7733h.setPrefix(getString(R.string.group_add_member_search_hint));
    }

    /* renamed from: z */
    public final Activity m8260z() {
        return this;
    }
}
