package com.cyberlink.you.activity.ulauncher;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.Filter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.cyberlink.p030U.R;
import com.cyberlink.you.Globals;
import com.cyberlink.you.database.C2950b0;
import com.cyberlink.you.friends.Friend;
import com.cyberlink.you.friends.FriendGroup;
import com.cyberlink.you.friends.Group;
import com.cyberlink.you.friends.InvitationFriend;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import p075g3.C4837b;
import p116k4.AbstractC5161l0;
import p116k4.C5170o0;
import p116k4.C5179r0;
import p173q2.C6127a;
import p209u2.C6385v;

/* renamed from: com.cyberlink.you.activity.ulauncher.d */
/* loaded from: classes.dex */
public class C2558d extends BaseExpandableListAdapter {

    /* renamed from: b */
    public final d[] f11600b;

    /* renamed from: c */
    public final d[] f11601c;

    /* renamed from: h */
    public b f11606h;

    /* renamed from: i */
    public f f11607i;

    /* renamed from: k */
    public AsyncTask<Void, Void, List<Object>> f11609k;

    /* renamed from: d */
    @SuppressLint({"UseSparseArrays"})
    public Map<Integer, List<Object>> f11602d = Collections.synchronizedMap(new HashMap());

    /* renamed from: e */
    @SuppressLint({"UseSparseArrays"})
    public Map<Integer, List<Object>> f11603e = Collections.synchronizedMap(new HashMap());

    /* renamed from: f */
    public boolean f11604f = false;

    /* renamed from: g */
    public boolean f11605g = false;

    /* renamed from: j */
    public C4837b f11608j = new C4837b();

    /* renamed from: com.cyberlink.you.activity.ulauncher.d$b */
    public interface b {
        /* renamed from: a */
        void mo13215a(Group group);

        /* renamed from: b */
        void mo13216b(InvitationFriend invitationFriend);

        /* renamed from: c */
        void mo13217c(Friend friend);

        /* renamed from: d */
        void mo13218d(InvitationFriend invitationFriend);

        /* renamed from: e */
        void mo13219e(Group group);

        /* renamed from: f */
        void mo13220f(InvitationFriend invitationFriend);
    }

    /* renamed from: com.cyberlink.you.activity.ulauncher.d$c */
    public abstract class c<T> extends AbstractC5161l0<T> {
        public c() {
        }

        /* renamed from: e */
        public abstract int mo13221e();

        /* renamed from: f */
        public final void m13222f(List<Object> list) {
            C2558d.this.f11603e.put(Integer.valueOf(mo13221e()), list);
            final C2558d c2558d = C2558d.this;
            C6385v.m24527e(new Runnable() { // from class: g3.e1
                @Override // java.lang.Runnable
                public final void run() {
                    c2558d.notifyDataSetChanged();
                }
            });
        }
    }

    /* renamed from: com.cyberlink.you.activity.ulauncher.d$d */
    public class d {

        /* renamed from: a */
        public int f11611a;

        /* renamed from: b */
        public Filter f11612b;

        /* renamed from: c */
        public String f11613c;

        public d(int i9, Filter filter, String str) {
            this.f11611a = i9;
            this.f11612b = filter;
            this.f11613c = str;
        }

        /* renamed from: c */
        public String m13225c(int i9) {
            return this.f11613c + " (" + i9 + ")";
        }
    }

    /* renamed from: com.cyberlink.you.activity.ulauncher.d$e */
    public class e extends c<Friend> {

        /* renamed from: c */
        public final int f11615c;

        public e(int i9) {
            super();
            this.f11615c = i9;
        }

        @Override // p116k4.AbstractC5161l0
        /* renamed from: a */
        public List<Friend> mo3351a() {
            return (List) C2558d.this.f11602d.get(Integer.valueOf(mo13221e()));
        }

        @Override // com.cyberlink.you.activity.ulauncher.C2558d.c
        /* renamed from: e */
        public int mo13221e() {
            return this.f11615c;
        }

        @Override // p116k4.AbstractC5161l0
        /* renamed from: g, reason: merged with bridge method [inline-methods] */
        public boolean mo3352c(Friend friend, String str, Object obj) {
            return (m20104d(friend.m15621b()) + StringUtils.SPACE + m20104d(friend.m15620a())).contains(str);
        }

        @Override // android.widget.Filter
        public void publishResults(CharSequence charSequence, Filter.FilterResults filterResults) {
            List<Object> list = (List) filterResults.values;
            C2558d.this.m13198Z(list, C2558d.this.f11608j.m19173b(charSequence.toString()));
            m13222f(list);
        }
    }

    /* renamed from: com.cyberlink.you.activity.ulauncher.d$f */
    public interface f {
        /* renamed from: a */
        void mo13227a(int i9, int i10);

        /* renamed from: b */
        void mo13228b(int i9, int i10);

        /* renamed from: c */
        void mo13229c();
    }

    /* renamed from: com.cyberlink.you.activity.ulauncher.d$g */
    public class g extends c<Group> {

        /* renamed from: com.cyberlink.you.activity.ulauncher.d$g$a */
        public class a extends AsyncTask<Void, Void, List<Object>> {

            /* renamed from: a */
            public final /* synthetic */ CharSequence f11618a;

            /* renamed from: b */
            public final /* synthetic */ Filter.FilterResults f11619b;

            public a(CharSequence charSequence, Filter.FilterResults filterResults) {
                this.f11618a = charSequence;
                this.f11619b = filterResults;
            }

            @Override // android.os.AsyncTask
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public List<Object> doInBackground(Void... voidArr) {
                return C2558d.this.f11608j.m19176e(this.f11618a.toString(), (List) this.f11619b.values);
            }

            @Override // android.os.AsyncTask
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public void onPostExecute(List<Object> list) {
                g.this.m13222f(list);
            }
        }

        public g() {
            super();
        }

        @Override // p116k4.AbstractC5161l0
        /* renamed from: a */
        public List<Group> mo3351a() {
            return (List) C2558d.this.f11602d.get(Integer.valueOf(mo13221e()));
        }

        @Override // p116k4.AbstractC5161l0
        /* renamed from: b */
        public Object mo13230b(String str) {
            return C2950b0.m14899A().m15028t(str);
        }

        @Override // com.cyberlink.you.activity.ulauncher.C2558d.c
        /* renamed from: e */
        public int mo13221e() {
            return 1;
        }

        @Override // p116k4.AbstractC5161l0
        /* renamed from: g, reason: merged with bridge method [inline-methods] */
        public boolean mo3352c(Group group, String str, Object obj) {
            Map map = (Map) obj;
            boolean zContainsKey = map.containsKey(Long.valueOf(group.f13727n));
            boolean zContains = m20104d(group.f13717d).contains(str);
            if (zContainsKey) {
                group.f13710I = (String) map.get(Long.valueOf(group.f13727n));
            }
            return zContainsKey || zContains;
        }

        @Override // android.widget.Filter
        public void publishResults(CharSequence charSequence, Filter.FilterResults filterResults) {
            if (C2558d.this.f11609k != null) {
                C2558d.this.f11609k.cancel(true);
            }
            C2558d.this.f11609k = new a(charSequence, filterResults).executeOnExecutor(C6385v.f21553a, new Void[0]);
        }
    }

    /* renamed from: com.cyberlink.you.activity.ulauncher.d$h */
    public class h extends c<InvitationFriend> {

        /* renamed from: c */
        public final int f11621c;

        public h(int i9) {
            super();
            this.f11621c = i9;
        }

        @Override // p116k4.AbstractC5161l0
        /* renamed from: a */
        public List<InvitationFriend> mo3351a() {
            return (List) C2558d.this.f11602d.get(Integer.valueOf(mo13221e()));
        }

        @Override // com.cyberlink.you.activity.ulauncher.C2558d.c
        /* renamed from: e */
        public int mo13221e() {
            return this.f11621c;
        }

        @Override // p116k4.AbstractC5161l0
        /* renamed from: g, reason: merged with bridge method [inline-methods] */
        public boolean mo3352c(InvitationFriend invitationFriend, String str, Object obj) {
            return m20104d(invitationFriend.f13741b).contains(str);
        }

        @Override // android.widget.Filter
        public void publishResults(CharSequence charSequence, Filter.FilterResults filterResults) {
            m13222f((List) filterResults.values);
        }
    }

    public C2558d(Context context, b bVar, f fVar) {
        this.f11606h = bVar;
        this.f11607i = fVar;
        this.f11600b = new d[]{new d(16, new e(16), context.getString(R.string.friends_friends_title_newfriends)), new d(8, new h(8), context.getString(R.string.friend_fragment_list_title_contact_request_received)), new d(4, new h(4), context.getString(R.string.friend_fragment_list_title_contact_request_sent)), new d(1, new g(), context.getString(R.string.friends_friends_title_groups)), new d(2, new e(2), context.getString(R.string.friend_fragment_list_title_contacts)), new d(32, new e(32), context.getString(R.string.friends_friends_title_organization))};
        this.f11601c = new d[]{new d(16, new e(16), context.getString(R.string.friends_friends_title_newfriends)), new d(8, new h(8), context.getString(R.string.friend_fragment_list_title_contact_request_received)), new d(4, new h(4), context.getString(R.string.friend_fragment_list_title_contact_request_sent)), new d(2, new e(2), context.getString(R.string.friend_fragment_list_title_contacts)), new d(1, new g(), context.getString(R.string.friends_friends_title_groups)), new d(32, new e(32), context.getString(R.string.friends_friends_title_organization))};
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: K */
    public /* synthetic */ void m13162K(Group group, View view) {
        b bVar = this.f11606h;
        if (bVar != null) {
            bVar.mo13219e(group);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: L */
    public /* synthetic */ void m13163L(Group group, View view) {
        b bVar = this.f11606h;
        if (bVar != null) {
            bVar.mo13215a(group);
            m13190R(1, group).notifyDataSetChanged();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: M */
    public /* synthetic */ void m13164M(Friend friend, View view) {
        b bVar = this.f11606h;
        if (bVar != null) {
            bVar.mo13217c(friend);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: N */
    public /* synthetic */ void m13165N(InvitationFriend invitationFriend, View view) {
        b bVar = this.f11606h;
        if (bVar != null) {
            bVar.mo13220f(invitationFriend);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: O */
    public /* synthetic */ void m13166O(InvitationFriend invitationFriend, View view) {
        b bVar = this.f11606h;
        if (bVar != null) {
            bVar.mo13218d(invitationFriend);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: P */
    public /* synthetic */ void m13167P(InvitationFriend invitationFriend, View view) {
        b bVar = this.f11606h;
        if (bVar != null) {
            bVar.mo13216b(invitationFriend);
        }
    }

    /* renamed from: A */
    public InvitationFriend m13179A(long j9) {
        return m13212x(8, j9);
    }

    /* renamed from: B */
    public final int m13180B() {
        int size = this.f11602d.size();
        int[] iArr = {4, 8, 16, 32};
        for (int i9 = 0; i9 < 4; i9++) {
            List<Object> list = this.f11602d.get(Integer.valueOf(iArr[i9]));
            if (list != null && list.isEmpty()) {
                size--;
            }
        }
        return size;
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x004a  */
    /* renamed from: C */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public List<Integer> m13181C() {
        ArrayList arrayList = new ArrayList();
        for (d dVar : this.f11604f ? this.f11601c : this.f11600b) {
            int i9 = dVar.f11611a;
            if (i9 == 1 || i9 == 2) {
                arrayList.add(Integer.valueOf(i9));
            } else if (i9 == 4 || i9 == 8 || i9 == 16) {
                List<Object> list = this.f11602d.get(Integer.valueOf(i9));
                if (list != null && list.size() > 0) {
                    arrayList.add(Integer.valueOf(i9));
                }
            } else if (i9 != 32) {
            }
        }
        return arrayList;
    }

    /* renamed from: D */
    public boolean m13182D() {
        return this.f11605g;
    }

    /* renamed from: E */
    public final boolean m13183E(Object obj) {
        return obj instanceof FriendGroup;
    }

    /* renamed from: F */
    public boolean m13184F() {
        return this.f11604f;
    }

    /* renamed from: G */
    public final void m13185G(int i9, Friend friend) {
        boolean z8;
        if (friend == null) {
            return;
        }
        List list = (List) m13210v(i9);
        if (list != null) {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                if (((Friend) it.next()).f13645c == friend.f13645c) {
                    z8 = true;
                    break;
                }
            }
            z8 = false;
        } else {
            z8 = false;
        }
        if (!z8) {
            m13202n(i9, friend);
        }
        notifyDataSetChanged();
    }

    /* renamed from: H */
    public void m13186H(Friend friend) {
        m13185G(2, friend);
    }

    /* renamed from: I */
    public void m13187I(Friend friend) {
        List list = (List) m13210v(16);
        if (friend.f13659q < 0) {
            return;
        }
        if (friend.f13659q + 86400 < System.currentTimeMillis() / 1000) {
            return;
        }
        if (list != null) {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                if (((Friend) it.next()).f13645c == friend.f13645c) {
                    return;
                }
            }
        }
        m13202n(16, friend);
    }

    /* renamed from: J */
    public void m13188J(Friend friend) {
        m13185G(32, friend);
    }

    /* renamed from: Q */
    public final boolean m13189Q(List<Object> list, int i9) {
        return list == null || i9 < 0 || list.size() <= i9;
    }

    /* renamed from: R */
    public C2558d m13190R(int i9, Object obj) {
        if (obj != null) {
            this.f11602d.get(Integer.valueOf(i9)).remove(obj);
        }
        return this;
    }

    /* renamed from: S */
    public final void m13191S(int i9, long j9) {
        List list = (List) m13210v(i9);
        if (list == null) {
            return;
        }
        Iterator it = list.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Friend friend = (Friend) it.next();
            if (friend.f13645c == j9) {
                m13190R(i9, friend);
                break;
            }
        }
        notifyDataSetChanged();
    }

    /* renamed from: T */
    public void m13192T(long j9) {
        m13191S(2, j9);
    }

    /* renamed from: U */
    public void m13193U(long j9) {
        m13191S(16, j9);
    }

    /* renamed from: V */
    public void m13194V(long j9) {
        m13191S(32, j9);
    }

    /* renamed from: W */
    public void m13195W() {
        this.f11608j.m19178h();
    }

    /* renamed from: X */
    public void m13196X(boolean z8) {
        this.f11604f = z8;
        if (z8) {
            return;
        }
        AsyncTask<Void, Void, List<Object>> asyncTask = this.f11609k;
        if (asyncTask != null) {
            asyncTask.cancel(true);
        }
        Iterator<Integer> it = this.f11603e.keySet().iterator();
        while (it.hasNext()) {
            this.f11603e.get(Integer.valueOf(it.next().intValue())).clear();
        }
    }

    /* renamed from: Y */
    public <T> C2558d m13197Y(int i9, Comparator<? super T> comparator) {
        Collections.sort(this.f11602d.get(Integer.valueOf(i9)), comparator);
        return this;
    }

    /* renamed from: Z */
    public void m13198Z(List<Object> list, Comparator<Object> comparator) {
        if (list == null) {
            return;
        }
        Collections.sort(list, comparator);
    }

    /* renamed from: a0 */
    public int m13199a0(int i9) {
        List<Integer> listM13181C = m13181C();
        if (i9 < 0 || i9 >= listM13181C.size()) {
            return -1;
        }
        return listM13181C.get(i9).intValue();
    }

    @Override // android.widget.ExpandableListAdapter
    public Object getChild(int i9, int i10) {
        List<Object> group = getGroup(i9);
        if (group == null) {
            return null;
        }
        return group.get(i10);
    }

    @Override // android.widget.ExpandableListAdapter
    public long getChildId(int i9, int i10) {
        return i10;
    }

    @Override // android.widget.ExpandableListAdapter
    public View getChildView(int i9, int i10, boolean z8, View view, ViewGroup viewGroup) {
        int iM13199a0 = m13199a0(i9);
        List<Object> group = getGroup(i9);
        if (iM13199a0 == 1) {
            if (view == null || !(view.getTag() instanceof Group)) {
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_item_friends_groups, viewGroup, false);
            }
            View viewFindViewById = view.findViewById(R.id.FriendsGroups_HideBtn);
            View viewFindViewById2 = view.findViewById(R.id.FriendsGroups_LeaveBtn);
            TextView textView = (TextView) view.findViewById(R.id.FriendsGroupsTextView_GroupName);
            TextView textView2 = (TextView) view.findViewById(R.id.FriendsGroupsTextView_MemberCount);
            ImageView imageView = (ImageView) view.findViewById(R.id.FriendsGroupsCircleImageView_DisplayImage);
            if (m13189Q(group, i10)) {
                return view;
            }
            final Group group2 = (Group) group.get(i10);
            view.setTag(group2);
            textView.setText(group2.f13717d);
            textView2.setText(String.valueOf(group2.f13728o));
            C6127a.m23470k(viewGroup.getContext(), imageView, group2);
            if (m13182D()) {
                viewFindViewById2.setVisibility(0);
                viewFindViewById2.setOnClickListener(new View.OnClickListener() { // from class: g3.y0
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view2) {
                        this.f17046b.m13162K(group2, view2);
                    }
                });
                if (Globals.m7388i0().m7586o()) {
                    viewFindViewById.setVisibility(0);
                    viewFindViewById.setOnClickListener(new View.OnClickListener() { // from class: g3.z0
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view2) {
                            this.f17051b.m13163L(group2, view2);
                        }
                    });
                } else {
                    viewFindViewById.setVisibility(8);
                }
            } else {
                viewFindViewById2.setVisibility(8);
                viewFindViewById.setVisibility(8);
            }
        } else if (iM13199a0 == 2 || iM13199a0 == 16 || iM13199a0 == 32) {
            if (view == null || !(view.getTag() instanceof Friend)) {
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_item_friends_friends, viewGroup, false);
            }
            View viewFindViewById3 = view.findViewById(R.id.FriendsFriendsView_Goto);
            Button button = (Button) view.findViewById(R.id.FriendsFriendsBlockBtn);
            TextView textView3 = (TextView) view.findViewById(R.id.FriendsFriendsTextView_DisplayName);
            ImageView imageView2 = (ImageView) view.findViewById(R.id.FriendsFriendsCircleImageView_DisplayImage);
            if (m13189Q(group, i10)) {
                return view;
            }
            final Friend friend = (Friend) group.get(i10);
            view.setTag(friend);
            boolean zM13183E = m13183E(friend);
            String strM15621b = friend.m15621b();
            if (zM13183E) {
                strM15621b = strM15621b + " (" + ((FriendGroup) friend).f13670B + ")";
            }
            textView3.setText(strM15621b);
            C6127a.m23469j(viewGroup.getContext(), imageView2, friend);
            if (!m13182D() || zM13183E) {
                button.setVisibility(8);
            } else {
                button.setVisibility(0);
                button.setOnClickListener(new View.OnClickListener() { // from class: g3.a1
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view2) {
                        this.f16829b.m13164M(friend, view2);
                    }
                });
            }
            viewFindViewById3.setVisibility(zM13183E ? 0 : 8);
        } else if (iM13199a0 == 4) {
            if (view == null || !(view.getTag() instanceof InvitationFriend)) {
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_item_friends_inviting, viewGroup, false);
            }
            view.findViewById(R.id.FriendsInvitingBtn).setVisibility(8);
            view.findViewById(R.id.FriendsInvitingTextView_message).setVisibility(8);
            Button button2 = (Button) view.findViewById(R.id.FriendsInvitingPinkBtn);
            TextView textView4 = (TextView) view.findViewById(R.id.FriendsInvitingTextView_DisplayName);
            ImageView imageView3 = (ImageView) view.findViewById(R.id.FriendsInvitingCircleImageView_DisplayImage);
            if (m13189Q(group, i10)) {
                return view;
            }
            final InvitationFriend invitationFriend = (InvitationFriend) group.get(i10);
            view.setTag(invitationFriend);
            textView4.setText(invitationFriend.m15763a());
            C6127a.m23471l(viewGroup.getContext(), imageView3, invitationFriend);
            if (m13182D()) {
                button2.setVisibility(0);
                button2.setText(R.string.friends_friends_title_inviting_cancel);
                button2.setOnClickListener(new View.OnClickListener() { // from class: g3.b1
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view2) {
                        this.f16836b.m13165N(invitationFriend, view2);
                    }
                });
            } else {
                button2.setVisibility(8);
            }
        } else if (iM13199a0 == 8) {
            if (view == null || !(view.getTag() instanceof InvitationFriend)) {
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_item_friends_inviting, viewGroup, false);
            }
            Button button3 = (Button) view.findViewById(R.id.FriendsInvitingBtn);
            Button button4 = (Button) view.findViewById(R.id.FriendsInvitingPinkBtn);
            TextView textView5 = (TextView) view.findViewById(R.id.FriendsInvitingTextView_DisplayName);
            TextView textView6 = (TextView) view.findViewById(R.id.FriendsInvitingTextView_message);
            ImageView imageView4 = (ImageView) view.findViewById(R.id.FriendsInvitingCircleImageView_DisplayImage);
            if (m13189Q(group, i10)) {
                return view;
            }
            final InvitationFriend invitationFriend2 = (InvitationFriend) group.get(i10);
            view.setTag(invitationFriend2);
            textView5.setText(invitationFriend2.m15763a());
            if (C5170o0.m20169d(invitationFriend2.f13752m)) {
                textView6.setVisibility(8);
            } else {
                textView6.setVisibility(0);
                textView6.setText(invitationFriend2.f13752m);
            }
            C6127a.m23471l(viewGroup.getContext(), imageView4, invitationFriend2);
            button3.setVisibility(0);
            button3.setText(R.string.friends_friends_title_request_accept);
            button3.setOnClickListener(new View.OnClickListener() { // from class: g3.c1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    this.f16844b.m13166O(invitationFriend2, view2);
                }
            });
            button4.setVisibility(0);
            button4.setText(R.string.friends_friends_title_request_not_now);
            button4.setOnClickListener(new View.OnClickListener() { // from class: g3.d1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    this.f16850b.m13167P(invitationFriend2, view2);
                }
            });
        }
        return view;
    }

    @Override // android.widget.ExpandableListAdapter
    public int getChildrenCount(int i9) {
        List<Object> group = getGroup(i9);
        if (group == null) {
            return 0;
        }
        return group.size();
    }

    @Override // android.widget.ExpandableListAdapter
    public int getGroupCount() {
        return m13180B();
    }

    @Override // android.widget.ExpandableListAdapter
    public long getGroupId(int i9) {
        return i9;
    }

    @Override // android.widget.ExpandableListAdapter
    public View getGroupView(int i9, boolean z8, View view, ViewGroup viewGroup) {
        View viewInflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_item_friends_titles, viewGroup, false);
        TextView textView = (TextView) viewInflate.findViewById(R.id.FriendsTitleDisplayName);
        ImageView imageView = (ImageView) viewInflate.findViewById(R.id.FriendsTitleExpand);
        int iM13199a0 = m13199a0(i9);
        List<Object> list = m13207s().get(Integer.valueOf(iM13199a0));
        int size = list.size();
        if (iM13199a0 == 2) {
            for (int i10 = 0; i10 < list.size(); i10++) {
                if (!m13183E(list.get(i10))) {
                    break;
                }
                size += ((FriendGroup) r4).f13670B - 1;
            }
        }
        textView.setText(m13211w(iM13199a0, size));
        C5179r0.m20247b(textView, 1);
        imageView.setImageResource(z8 ? R.drawable.icon_expand_up : R.drawable.icon_expand_down);
        return size == 0 ? new FrameLayout(Globals.m7372O()) : viewInflate;
    }

    @Override // android.widget.ExpandableListAdapter
    public boolean hasStableIds() {
        return true;
    }

    @Override // android.widget.ExpandableListAdapter
    public boolean isChildSelectable(int i9, int i10) {
        return true;
    }

    /* renamed from: l */
    public C2558d m13200l(int i9, Object obj) {
        if (obj != null) {
            this.f11602d.get(Integer.valueOf(i9)).add(obj);
        }
        return this;
    }

    /* renamed from: m */
    public C2558d m13201m(int i9, List<Object> list) {
        if (list != null) {
            this.f11602d.get(Integer.valueOf(i9)).addAll(list);
        }
        return this;
    }

    /* renamed from: n */
    public final void m13202n(int i9, Friend friend) {
        m13200l(i9, friend);
        m13197Y(i9, new FriendGroup.C3042a());
        notifyDataSetChanged();
    }

    @Override // android.widget.BaseExpandableListAdapter
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        f fVar = this.f11607i;
        if (fVar != null) {
            fVar.mo13229c();
        }
    }

    /* renamed from: o */
    public C2558d m13203o(int i9) {
        this.f11602d.put(Integer.valueOf(i9), new ArrayList());
        this.f11603e.put(Integer.valueOf(i9), new ArrayList());
        return this;
    }

    @Override // android.widget.BaseExpandableListAdapter, android.widget.ExpandableListAdapter
    public void onGroupCollapsed(int i9) {
        f fVar = this.f11607i;
        if (fVar != null) {
            fVar.mo13227a(i9, m13199a0(i9));
        }
    }

    @Override // android.widget.BaseExpandableListAdapter, android.widget.ExpandableListAdapter
    public void onGroupExpanded(int i9) {
        f fVar = this.f11607i;
        if (fVar != null) {
            fVar.mo13228b(i9, m13199a0(i9));
        }
    }

    /* renamed from: p */
    public C2558d m13204p(int i9) {
        this.f11602d.get(Integer.valueOf(i9)).clear();
        return this;
    }

    /* renamed from: q */
    public void m13205q(int i9, String str) {
        d dVarM13208t = m13208t(i9);
        if (dVarM13208t != null) {
            dVarM13208t.f11612b.filter(str);
        }
    }

    /* renamed from: r */
    public void m13206r(String str) {
        for (d dVar : this.f11600b) {
            m13205q(dVar.f11611a, str);
        }
    }

    /* renamed from: s */
    public Map<Integer, List<Object>> m13207s() {
        return m13184F() ? this.f11603e : this.f11602d;
    }

    /* renamed from: t */
    public final d m13208t(int i9) {
        for (d dVar : this.f11600b) {
            if (dVar.f11611a == i9) {
                return dVar;
            }
        }
        Log.e("FriendCategoryAdapter", "Missing type = " + i9);
        return null;
    }

    @Override // android.widget.ExpandableListAdapter
    /* renamed from: u, reason: merged with bridge method [inline-methods] */
    public List<Object> getGroup(int i9) {
        return m13207s().get(Integer.valueOf(m13199a0(i9)));
    }

    /* renamed from: v */
    public Object m13210v(int i9) {
        return this.f11602d.get(Integer.valueOf(i9));
    }

    /* renamed from: w */
    public final String m13211w(int i9, int i10) {
        d dVarM13208t = m13208t(i9);
        return dVarM13208t == null ? "" : dVarM13208t.m13225c(i10);
    }

    /* renamed from: x */
    public final InvitationFriend m13212x(int i9, long j9) {
        List<InvitationFriend> list = (List) m13210v(i9);
        if (list == null) {
            return null;
        }
        for (InvitationFriend invitationFriend : list) {
            if (invitationFriend.f13746g == j9) {
                return invitationFriend;
            }
        }
        return null;
    }

    /* renamed from: y */
    public InvitationFriend m13213y(long j9) {
        return m13212x(4, j9);
    }

    /* renamed from: z */
    public int m13214z(int i9) {
        List<Object> list = this.f11602d.get(Integer.valueOf(i9));
        if (list == null) {
            return 0;
        }
        return list.size();
    }
}
