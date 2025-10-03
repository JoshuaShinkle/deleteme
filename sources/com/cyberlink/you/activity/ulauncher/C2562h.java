package com.cyberlink.you.activity.ulauncher;

import android.content.Context;
import android.os.AsyncTask;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.cyberlink.p030U.R;
import com.cyberlink.you.chat.C2907m0;
import com.cyberlink.you.database.C2950b0;
import com.cyberlink.you.friends.ChatListGroup;
import com.cyberlink.you.friends.Friend;
import com.cyberlink.you.friends.FriendGroup;
import com.cyberlink.you.friends.Group;
import com.cyberlink.you.utility.CLUtility;
import com.cyberlink.you.utility.ULogUtility;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import p075g3.C4837b;
import p116k4.AbstractC5161l0;
import p116k4.C5170o0;
import p116k4.C5179r0;
import p173q2.C6127a;
import p209u2.C6385v;

/* renamed from: com.cyberlink.you.activity.ulauncher.h */
/* loaded from: classes.dex */
public class C2562h extends BaseExpandableListAdapter {

    /* renamed from: d */
    public g[] f11741d;

    /* renamed from: f */
    public e f11743f;

    /* renamed from: g */
    public Context f11744g;

    /* renamed from: k */
    public AsyncTask<Void, Void, List<Object>> f11748k;

    /* renamed from: b */
    public SparseArray<List<Object>> f11739b = new SparseArray<>();

    /* renamed from: c */
    public SparseArray<List<Object>> f11740c = new SparseArray<>();

    /* renamed from: e */
    public int f11742e = 0;

    /* renamed from: h */
    public boolean f11745h = false;

    /* renamed from: i */
    public final Object f11746i = new Object();

    /* renamed from: j */
    public C4837b f11747j = new C4837b();

    /* renamed from: com.cyberlink.you.activity.ulauncher.h$a */
    public class a extends g {
        public a(int i9, Filter filter, String str) {
            super(i9, filter, str);
        }

        @Override // com.cyberlink.you.activity.ulauncher.C2562h.g
        /* renamed from: c */
        public boolean mo13459c() {
            List list = (List) C2562h.this.f11739b.get(2);
            return list != null && list.size() > 0;
        }
    }

    /* renamed from: com.cyberlink.you.activity.ulauncher.h$b */
    public class b extends f<ChatListGroup> {
        public b() {
            super(C2562h.this, null);
        }

        @Override // p116k4.AbstractC5161l0
        /* renamed from: a */
        public List<ChatListGroup> mo3351a() {
            return (List) C2562h.this.f11739b.get(2);
        }

        @Override // p116k4.AbstractC5161l0
        /* renamed from: b */
        public Object mo13230b(String str) {
            List<Friend> listM15032x = C2950b0.m14899A().m15032x();
            ArrayList arrayList = new ArrayList();
            for (Friend friend : listM15032x) {
                if (m20104d(friend.m15620a()).contains(str) || m20104d(friend.m15621b()).contains(str)) {
                    arrayList.add(Long.valueOf(friend.f13645c));
                }
            }
            return arrayList;
        }

        @Override // p116k4.AbstractC5161l0
        /* renamed from: f, reason: merged with bridge method [inline-methods] */
        public boolean mo3352c(ChatListGroup chatListGroup, String str, Object obj) {
            if (m20104d(chatListGroup.f13717d).contains(str)) {
                return true;
            }
            List<Long> listM15098i = C2950b0.m14913l().m15098i(Long.valueOf(chatListGroup.f13727n));
            if (listM15098i == null) {
                return false;
            }
            listM15098i.retainAll((List) obj);
            return listM15098i.size() > 0;
        }

        @Override // android.widget.Filter
        public void publishResults(CharSequence charSequence, Filter.FilterResults filterResults) {
            List<Object> listM19175d = C2562h.this.f11747j.m19175d(charSequence.toString(), (List) filterResults.values);
            synchronized (C2562h.this.f11746i) {
                C2562h.this.f11740c.put(2, listM19175d);
            }
            m13466e();
        }

        public /* synthetic */ b(C2562h c2562h, a aVar) {
            this();
        }
    }

    /* renamed from: com.cyberlink.you.activity.ulauncher.h$c */
    public class c extends f<Friend> {
        public c() {
            super(C2562h.this, null);
        }

        @Override // p116k4.AbstractC5161l0
        /* renamed from: a */
        public List<Friend> mo3351a() {
            return (List) C2562h.this.f11739b.get(1);
        }

        @Override // p116k4.AbstractC5161l0
        /* renamed from: f, reason: merged with bridge method [inline-methods] */
        public boolean mo3352c(Friend friend, String str, Object obj) {
            return (m20104d(friend.m15621b()) + StringUtils.SPACE + m20104d(friend.m15620a())).contains(str);
        }

        @Override // android.widget.Filter
        public void publishResults(CharSequence charSequence, Filter.FilterResults filterResults) {
            List<Object> list = (List) filterResults.values;
            C2562h.this.m13457x(list, C2562h.this.f11747j.m19173b(charSequence.toString()));
            synchronized (C2562h.this.f11746i) {
                C2562h.this.f11740c.put(1, list);
            }
            m13466e();
        }

        public /* synthetic */ c(C2562h c2562h, a aVar) {
            this();
        }
    }

    /* renamed from: com.cyberlink.you.activity.ulauncher.h$d */
    public class d extends f<Group> {

        /* renamed from: com.cyberlink.you.activity.ulauncher.h$d$a */
        public class a extends AsyncTask<Void, Void, List<Object>> {

            /* renamed from: a */
            public final /* synthetic */ CharSequence f11753a;

            /* renamed from: b */
            public final /* synthetic */ Filter.FilterResults f11754b;

            public a(CharSequence charSequence, Filter.FilterResults filterResults) {
                this.f11753a = charSequence;
                this.f11754b = filterResults;
            }

            @Override // android.os.AsyncTask
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public List<Object> doInBackground(Void... voidArr) {
                return C2562h.this.f11747j.m19176e(this.f11753a.toString(), (List) this.f11754b.values);
            }

            @Override // android.os.AsyncTask
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public void onPostExecute(List<Object> list) {
                synchronized (C2562h.this.f11746i) {
                    C2562h.this.f11740c.put(3, list);
                }
                d.this.m13466e();
            }
        }

        public d() {
            super(C2562h.this, null);
        }

        @Override // p116k4.AbstractC5161l0
        /* renamed from: a */
        public List<Group> mo3351a() {
            return (List) C2562h.this.f11739b.get(3);
        }

        @Override // p116k4.AbstractC5161l0
        /* renamed from: b */
        public Object mo13230b(String str) {
            return C2950b0.m14899A().m15028t(str);
        }

        @Override // p116k4.AbstractC5161l0
        /* renamed from: f, reason: merged with bridge method [inline-methods] */
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
            if (C2562h.this.f11748k != null) {
                C2562h.this.f11748k.cancel(true);
            }
            C2562h.this.f11748k = new a(charSequence, filterResults).executeOnExecutor(C6385v.f21553a, new Void[0]);
        }

        public /* synthetic */ d(C2562h c2562h, a aVar) {
            this();
        }
    }

    /* renamed from: com.cyberlink.you.activity.ulauncher.h$e */
    public interface e {
        /* renamed from: a */
        void mo13465a();
    }

    /* renamed from: com.cyberlink.you.activity.ulauncher.h$f */
    public abstract class f<T> extends AbstractC5161l0<T> {
        public f() {
        }

        /* renamed from: e */
        public final void m13466e() {
            C2562h.this.notifyDataSetChanged();
            C2562h.m13440g(C2562h.this);
            if (C2562h.this.f11742e < 3 || C2562h.this.f11743f == null) {
                return;
            }
            C2562h.this.f11743f.mo13465a();
        }

        public /* synthetic */ f(C2562h c2562h, a aVar) {
            this();
        }
    }

    /* renamed from: com.cyberlink.you.activity.ulauncher.h$g */
    public static class g {

        /* renamed from: a */
        public int f11757a;

        /* renamed from: b */
        public Filter f11758b;

        /* renamed from: c */
        public String f11759c;

        public g() {
        }

        public g(int i9, Filter filter, String str) {
            this.f11757a = i9;
            this.f11758b = filter;
            this.f11759c = str;
        }

        /* renamed from: c */
        public boolean mo13459c() {
            return this.f11758b != null;
        }

        /* renamed from: d */
        public String m13469d(int i9) {
            return this.f11759c + " (" + i9 + ")";
        }
    }

    public C2562h(Context context) {
        this.f11744g = context;
        a aVar = null;
        this.f11741d = new g[]{new g(), new g(1, new c(this, aVar), context.getString(R.string.friends_friends_title_frieds)), new a(2, new b(this, aVar), context.getString(R.string.chat)), new g(3, new d(this, aVar), context.getString(R.string.friends_friends_title_groups))};
    }

    /* renamed from: g */
    public static /* synthetic */ int m13440g(C2562h c2562h) {
        int i9 = c2562h.f11742e;
        c2562h.f11742e = i9 + 1;
        return i9;
    }

    @Override // android.widget.ExpandableListAdapter
    public Object getChild(int i9, int i10) {
        List<Object> list = m13447n().get(m13458y(Integer.valueOf(i9)));
        if (list == null) {
            return null;
        }
        return list.get(i10);
    }

    @Override // android.widget.ExpandableListAdapter
    public long getChildId(int i9, int i10) {
        return i10;
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x00d4  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x010b  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x011e  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x013a  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0157  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0190  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0192  */
    @Override // android.widget.ExpandableListAdapter
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public View getChildView(int i9, int i10, boolean z8, View view, ViewGroup viewGroup) {
        int i11;
        View viewInflate;
        boolean z9;
        int iM14483G;
        int iM13458y = m13458y(Integer.valueOf(i9));
        List<Object> list = m13447n().get(iM13458y);
        if (iM13458y == 2) {
            View viewInflate2 = (view == null || !(view.getTag() instanceof ChatListGroup)) ? LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_item_search_chat, viewGroup, false) : view;
            TextView textView = (TextView) viewInflate2.findViewById(R.id.FriendsGroupsTextView_GroupName);
            ImageView imageView = (ImageView) viewInflate2.findViewById(R.id.FriendsGroupsCircleImageView_DisplayImage);
            TextView textView2 = (TextView) viewInflate2.findViewById(R.id.FriendsGroupsTextView_MemberCount);
            TextView textView3 = (TextView) viewInflate2.findViewById(R.id.chatGroupLastMessageUpperView);
            TextView textView4 = (TextView) viewInflate2.findViewById(R.id.chatGroupTimeView);
            TextView textView5 = (TextView) viewInflate2.findViewById(R.id.chatGroupUnreadCountView);
            View viewFindViewById = viewInflate2.findViewById(R.id.bulletin_notification_disable_unread);
            View viewFindViewById2 = viewInflate2.findViewById(R.id.UnreadLayout);
            ImageView imageView2 = (ImageView) viewInflate2.findViewById(R.id.searchChatLockView);
            if (list == null || list.size() <= i10) {
                view = viewInflate2;
            } else {
                ChatListGroup chatListGroup = (ChatListGroup) list.get(i10);
                viewInflate2.setTag(chatListGroup);
                if (!C5170o0.m20169d(chatListGroup.f13739z)) {
                    String strM15745a = chatListGroup.m15745a();
                    view = viewInflate2;
                    long jM15749e = chatListGroup.m15749e();
                    if (!strM15745a.isEmpty() && jM15749e > 0) {
                        textView3.setText(strM15745a);
                        textView4.setText(CLUtility.m16450I2(new Date(jM15749e)));
                        z9 = true;
                    }
                    if (!z9) {
                        textView3.setText("");
                        textView4.setText("");
                    }
                    C6127a.m23470k(this.f11744g, imageView, chatListGroup);
                    if (chatListGroup.f13728o == 1 && C5170o0.m20169d(chatListGroup.f13717d)) {
                        chatListGroup.f13717d = this.f11744g.getString(R.string.group_empty_room);
                    }
                    textView.setText(chatListGroup.f13717d);
                    if (chatListGroup.f13716c.equals("Dual")) {
                        textView2.setVisibility(0);
                        m13454u(textView2, chatListGroup.f13728o, 999L, "999+");
                    } else {
                        textView2.setVisibility(8);
                    }
                    iM14483G = C2907m0.m14454I().m14483G(chatListGroup.f13723j);
                    int iM14481E = C2907m0.m14454I().m14481E(chatListGroup.f13727n, false);
                    if (iM14483G <= 0) {
                        m13454u(textView5, iM14483G, 99L, "N");
                        textView5.setVisibility(0);
                        viewFindViewById.setVisibility(8);
                        viewFindViewById2.setVisibility(0);
                        textView5.setBackgroundResource(R.drawable.chat_unread);
                    } else if (iM14481E != 0) {
                        boolean z10 = iM14481E == -1;
                        textView5.setVisibility(z10 ? 8 : 0);
                        if (!z10) {
                            m13454u(textView5, iM14481E, 99L, "N");
                            textView5.setBackgroundResource(R.drawable.bulletin_unread);
                        }
                        viewFindViewById.setVisibility(z10 ? 0 : 8);
                        viewFindViewById2.setVisibility(0);
                    } else {
                        viewFindViewById2.setVisibility(4);
                    }
                    imageView2.setVisibility(!chatListGroup.f13711J ? 0 : 8);
                } else {
                    view = viewInflate2;
                }
                z9 = false;
                if (!z9) {
                }
                C6127a.m23470k(this.f11744g, imageView, chatListGroup);
                if (chatListGroup.f13728o == 1) {
                    chatListGroup.f13717d = this.f11744g.getString(R.string.group_empty_room);
                }
                textView.setText(chatListGroup.f13717d);
                if (chatListGroup.f13716c.equals("Dual")) {
                }
                iM14483G = C2907m0.m14454I().m14483G(chatListGroup.f13723j);
                int iM14481E2 = C2907m0.m14454I().m14481E(chatListGroup.f13727n, false);
                if (iM14483G <= 0) {
                }
                imageView2.setVisibility(!chatListGroup.f13711J ? 0 : 8);
            }
        } else {
            if (iM13458y == 3) {
                View viewInflate3 = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_item_search_chat_groups, viewGroup, false);
                TextView textView6 = (TextView) viewInflate3.findViewById(R.id.FriendsGroupsTextView_GroupName);
                ImageView imageView3 = (ImageView) viewInflate3.findViewById(R.id.FriendsGroupsCircleImageView_DisplayImage);
                TextView textView7 = (TextView) viewInflate3.findViewById(R.id.FriendsGroupsTextView_MemberCount);
                TextView textView8 = (TextView) viewInflate3.findViewById(R.id.chatGroupLastMessageUpperView);
                if (list == null || list.size() <= i10) {
                    return viewInflate3;
                }
                Group group = (Group) list.get(i10);
                viewInflate3.setTag(group);
                textView6.setText(group.f13717d);
                textView7.setText(String.valueOf(group.f13728o));
                C6127a.m23470k(this.f11744g, imageView3, group);
                if (group.f13710I == null) {
                    textView8.setVisibility(8);
                    return viewInflate3;
                }
                String str = String.format(this.f11744g.getString(R.string.match_group_memeber), group.f13710I);
                textView8.setVisibility(0);
                textView8.setText(str);
                return viewInflate3;
            }
            if (iM13458y == 1) {
                if (view == null || !(view.getTag() instanceof Friend)) {
                    i11 = 0;
                    viewInflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_item_search_friend, viewGroup, false);
                } else {
                    viewInflate = view;
                    i11 = 0;
                }
                TextView textView9 = (TextView) viewInflate.findViewById(R.id.FriendsFriendsTextView_DisplayName);
                ImageView imageView4 = (ImageView) viewInflate.findViewById(R.id.FriendsFriendsCircleImageView_DisplayImage);
                View viewFindViewById3 = viewInflate.findViewById(R.id.FriendsFriendsView_Goto);
                if (list == null || list.size() <= i10) {
                    return viewInflate;
                }
                Friend friend = (Friend) list.get(i10);
                viewInflate.setTag(friend);
                boolean zM13452s = m13452s(friend);
                if (friend.m15621b() != null) {
                    String strM15621b = friend.m15621b();
                    if (zM13452s) {
                        strM15621b = strM15621b + " (" + ((FriendGroup) friend).f13670B + ")";
                    }
                    textView9.setText(strM15621b);
                }
                C6127a.m23469j(this.f11744g, imageView4, friend);
                viewFindViewById3.setVisibility(zM13452s ? i11 : 8);
                return viewInflate;
            }
        }
        return view;
    }

    @Override // android.widget.ExpandableListAdapter
    public int getChildrenCount(int i9) {
        List<Object> list = m13447n().get(m13458y(Integer.valueOf(i9)));
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    @Override // android.widget.ExpandableListAdapter
    public Object getGroup(int i9) {
        return m13447n().get(m13458y(Integer.valueOf(i9)));
    }

    @Override // android.widget.ExpandableListAdapter
    public int getGroupCount() {
        return m13449p();
    }

    @Override // android.widget.ExpandableListAdapter
    public long getGroupId(int i9) {
        return i9;
    }

    @Override // android.widget.ExpandableListAdapter
    public View getGroupView(int i9, boolean z8, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_item_friends_titles, viewGroup, false);
        }
        int iM13458y = m13458y(Integer.valueOf(i9));
        List<Object> list = m13447n().get(iM13458y);
        if (list != null) {
            int size = list.size();
            if (iM13458y == 1) {
                for (int i10 = 0; i10 < list.size(); i10++) {
                    Object obj = list.get(i10);
                    if (!m13452s(obj)) {
                        break;
                    }
                    size = (size - 1) + ((FriendGroup) obj).f13670B;
                }
            }
            TextView textView = (TextView) view.findViewById(R.id.FriendsTitleDisplayName);
            if (textView == null) {
                ULogUtility.m16670f("SearchChatAdapter", "getGroupView:" + view.toString());
                if (view instanceof RelativeLayout) {
                    RelativeLayout relativeLayout = (RelativeLayout) view;
                    for (int i11 = 0; i11 < relativeLayout.getChildCount(); i11++) {
                        ULogUtility.m16670f("SearchChatAdapter", "getGroupView Child Tag:" + relativeLayout.getChildAt(i11).getTag());
                    }
                }
            } else {
                textView.setText(m13448o(iM13458y, size));
                C5179r0.m20247b(textView, 1);
            }
        }
        ((ImageView) view.findViewById(R.id.FriendsTitleExpand)).setImageResource(z8 ? R.drawable.icon_expand_up : R.drawable.icon_expand_down);
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

    /* renamed from: j */
    public void m13443j(int i9, List<Object> list) {
        synchronized (this.f11746i) {
            this.f11739b.get(i9).addAll(list);
        }
    }

    /* renamed from: k */
    public void m13444k(int i9, List<Object> list) {
        synchronized (this.f11746i) {
            this.f11739b.put(i9, new ArrayList(list));
            this.f11740c.put(i9, new ArrayList());
        }
    }

    /* renamed from: l */
    public void m13445l(int i9) {
        synchronized (this.f11746i) {
            this.f11739b.get(i9).clear();
        }
    }

    /* renamed from: m */
    public void m13446m(int i9, String str) {
        this.f11742e = 0;
        Filter filter = this.f11741d[i9].f11758b;
        if (filter != null) {
            filter.filter(str);
        }
    }

    /* renamed from: n */
    public final SparseArray<List<Object>> m13447n() {
        return m13453t() ? this.f11740c : this.f11739b;
    }

    /* renamed from: o */
    public final String m13448o(int i9, int i10) {
        return i9 == 0 ? "" : this.f11741d[i9].m13469d(i10);
    }

    /* renamed from: p */
    public final int m13449p() {
        int size = this.f11739b.size();
        List<Object> list = this.f11739b.get(2);
        return (list == null || !list.isEmpty()) ? size : size - 1;
    }

    /* renamed from: q */
    public int m13450q() {
        return getChildrenCount(2) + getChildrenCount(1) + getChildrenCount(3);
    }

    /* renamed from: r */
    public List<Integer> m13451r() {
        ArrayList arrayList = new ArrayList();
        for (g gVar : this.f11741d) {
            int i9 = gVar.f11757a;
            if (gVar.mo13459c()) {
                arrayList.add(Integer.valueOf(i9));
            }
        }
        return arrayList;
    }

    /* renamed from: s */
    public final boolean m13452s(Object obj) {
        return obj instanceof FriendGroup;
    }

    /* renamed from: t */
    public final boolean m13453t() {
        return this.f11745h;
    }

    /* renamed from: u */
    public final void m13454u(TextView textView, long j9, long j10, String str) {
        if (j9 <= j10) {
            str = String.valueOf(j9);
        }
        textView.setText(str);
    }

    /* renamed from: v */
    public void m13455v(boolean z8) {
        this.f11745h = z8;
        if (z8) {
            return;
        }
        AsyncTask<Void, Void, List<Object>> asyncTask = this.f11748k;
        if (asyncTask != null) {
            asyncTask.cancel(true);
        }
        int size = this.f11740c.size();
        for (int i9 = 0; i9 < size; i9++) {
            List<Object> list = this.f11740c.get(i9);
            if (list != null) {
                list.clear();
            }
        }
    }

    /* renamed from: w */
    public void m13456w(e eVar) {
        this.f11743f = eVar;
    }

    /* renamed from: x */
    public void m13457x(List<Object> list, Comparator<Object> comparator) {
        if (list == null) {
            return;
        }
        synchronized (this.f11746i) {
            Collections.sort(list, comparator);
        }
    }

    /* renamed from: y */
    public int m13458y(Integer num) {
        List<Integer> listM13451r = m13451r();
        if (listM13451r != null && listM13451r.size() > num.intValue()) {
            return listM13451r.get(num.intValue()).intValue();
        }
        return -1;
    }
}
