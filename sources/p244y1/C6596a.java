package p244y1;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.cyberlink.p030U.R;
import com.cyberlink.you.activity.GroupEditActivity;
import com.cyberlink.you.friends.Friend;
import com.cyberlink.you.friends.Group;
import com.cyberlink.you.friends.UserInfo;
import com.cyberlink.you.utility.CLUtility;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import p116k4.C5179r0;
import p173q2.C6127a;

/* renamed from: y1.a */
/* loaded from: classes.dex */
public class C6596a extends BaseExpandableListAdapter implements Filterable {

    /* renamed from: b */
    public Activity f22147b;

    /* renamed from: c */
    public Group f22148c;

    /* renamed from: d */
    public final ArrayList<Friend> f22149d;

    /* renamed from: e */
    public final ArrayList<Friend> f22150e;

    /* renamed from: f */
    public final ArrayList<Friend> f22151f;

    /* renamed from: g */
    public final ArrayList<Friend> f22152g;

    /* renamed from: h */
    public e f22153h;

    /* renamed from: i */
    public UserInfo f22154i;

    /* renamed from: j */
    public String f22155j;

    /* renamed from: k */
    public View.OnClickListener f22156k;

    /* renamed from: l */
    public View.OnClickListener f22157l;

    /* renamed from: m */
    public final View.OnClickListener f22158m;

    /* renamed from: n */
    public final View.OnClickListener f22159n;

    /* renamed from: y1.a$a */
    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (C5179r0.m20246a()) {
                return;
            }
            ArrayList<Friend> arrayList = new ArrayList<>(C6596a.this.f22149d);
            ((GroupEditActivity) C6596a.this.f22147b).m8374t1(new ArrayList<>(C6596a.this.f22150e), arrayList, 4);
        }
    }

    /* renamed from: y1.a$b */
    public class b implements View.OnClickListener {
        public b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (C5179r0.m20246a()) {
                return;
            }
            ((GroupEditActivity) C6596a.this.f22147b).m8373s1(new ArrayList<>(C6596a.this.f22150e), 5);
        }
    }

    /* renamed from: y1.a$c */
    public class c {

        /* renamed from: a */
        public RelativeLayout f22162a;

        /* renamed from: b */
        public ImageView f22163b;

        /* renamed from: c */
        public TextView f22164c;

        /* renamed from: d */
        public Button f22165d;

        /* renamed from: e */
        public RelativeLayout f22166e;

        /* renamed from: f */
        public TextView f22167f;

        public c() {
        }

        public /* synthetic */ c(C6596a c6596a, a aVar) {
            this();
        }
    }

    /* renamed from: y1.a$d */
    public class d {

        /* renamed from: a */
        public TextView f22169a;

        /* renamed from: b */
        public RelativeLayout f22170b;

        public d() {
        }

        public /* synthetic */ d(C6596a c6596a, a aVar) {
            this();
        }
    }

    /* renamed from: y1.a$e */
    public class e extends Filter {
        public e() {
        }

        @Override // android.widget.Filter
        public Filter.FilterResults performFiltering(CharSequence charSequence) {
            String lowerCase = charSequence.toString().toLowerCase(Locale.getDefault());
            Filter.FilterResults filterResults = new Filter.FilterResults();
            ArrayList arrayList = new ArrayList();
            Iterator it = C6596a.this.f22149d.iterator();
            while (it.hasNext()) {
                Friend friend = (Friend) it.next();
                if (friend.m15621b().toLowerCase(Locale.getDefault()).contains(lowerCase)) {
                    arrayList.add(friend);
                }
            }
            Iterator it2 = C6596a.this.f22150e.iterator();
            while (it2.hasNext()) {
                Friend friend2 = (Friend) it2.next();
                if (friend2.m15621b().toLowerCase(Locale.getDefault()).contains(lowerCase)) {
                    arrayList.add(friend2);
                }
            }
            filterResults.values = arrayList;
            filterResults.count = arrayList.size();
            return filterResults;
        }

        @Override // android.widget.Filter
        public void publishResults(CharSequence charSequence, Filter.FilterResults filterResults) {
            ArrayList<Friend> arrayList = (ArrayList) filterResults.values;
            C6596a.this.f22151f.clear();
            C6596a.this.f22152g.clear();
            for (Friend friend : arrayList) {
                if (C6596a.this.f22149d.contains(friend) && !C6596a.this.f22151f.contains(friend)) {
                    C6596a.this.f22151f.add(friend);
                }
                if (C6596a.this.f22150e.contains(friend) && !C6596a.this.f22152g.contains(friend)) {
                    C6596a.this.f22152g.add(friend);
                }
            }
            C6596a.this.notifyDataSetChanged();
        }

        public /* synthetic */ e(C6596a c6596a, a aVar) {
            this();
        }
    }

    public C6596a(Activity activity, Group group, String str, List<Friend> list, List<Friend> list2) {
        ArrayList<Friend> arrayList = new ArrayList<>();
        this.f22149d = arrayList;
        ArrayList<Friend> arrayList2 = new ArrayList<>();
        this.f22150e = arrayList2;
        ArrayList<Friend> arrayList3 = new ArrayList<>();
        this.f22151f = arrayList3;
        ArrayList<Friend> arrayList4 = new ArrayList<>();
        this.f22152g = arrayList4;
        this.f22155j = "Small";
        this.f22158m = new a();
        this.f22159n = new b();
        this.f22147b = activity;
        this.f22148c = group;
        this.f22155j = str;
        if (list != null) {
            arrayList.addAll(list);
            arrayList3.addAll(list);
        }
        if (list2 != null) {
            arrayList2.addAll(list2);
            arrayList4.addAll(list2);
        }
        this.f22154i = CLUtility.m16497V0(this.f22147b);
    }

    /* renamed from: f */
    public int m25217f(List<Friend> list) {
        int i9 = 0;
        if (list != null) {
            for (Friend friend : list) {
                if (!this.f22149d.contains(friend)) {
                    this.f22149d.add(friend);
                    i9++;
                }
                if (!this.f22151f.contains(friend)) {
                    this.f22151f.add(friend);
                }
            }
            notifyDataSetChanged();
        }
        return i9;
    }

    /* renamed from: g */
    public int m25218g(List<Friend> list) {
        int i9 = 0;
        if (list != null) {
            for (Friend friend : list) {
                if (!this.f22150e.contains(friend)) {
                    this.f22150e.add(friend);
                    i9++;
                }
                if (!this.f22152g.contains(friend)) {
                    this.f22152g.add(friend);
                }
            }
        }
        notifyDataSetChanged();
        return i9;
    }

    @Override // android.widget.ExpandableListAdapter
    public Object getChild(int i9, int i10) {
        if (m25222k() && i9 == 1) {
            return this.f22151f.get(i10);
        }
        return this.f22152g.get(i10);
    }

    @Override // android.widget.ExpandableListAdapter
    public long getChildId(int i9, int i10) {
        return i10;
    }

    @Override // android.widget.ExpandableListAdapter
    public View getChildView(int i9, int i10, boolean z8, View view, ViewGroup viewGroup) {
        c cVar;
        TextView textView;
        if (view == null) {
            view = ((LayoutInflater) this.f22147b.getSystemService("layout_inflater")).inflate(R.layout.view_item_friends_group_edit, viewGroup, false);
            cVar = new c(this, null);
            cVar.f22162a = (RelativeLayout) view.findViewById(R.id.relativeLayoutContainer);
            cVar.f22164c = (TextView) view.findViewById(R.id.GroupEditFriendTextView);
            cVar.f22163b = (ImageView) view.findViewById(R.id.GroupEditCircleImageView);
            cVar.f22165d = (Button) view.findViewById(R.id.GroupEditItemBtn);
            cVar.f22166e = (RelativeLayout) view.findViewById(R.id.relativeLayoutInviteBtn);
            cVar.f22167f = (TextView) view.findViewById(R.id.textViewInviteBtn);
            Button button = cVar.f22165d;
            if (button != null) {
                button.setTextColor(this.f22147b.getResources().getColor(R.color.you_color_inviting_pink));
                cVar.f22165d.setBackgroundResource(R.drawable.btn_red_cancel);
                cVar.f22165d.setText(this.f22147b.getString(R.string.friends_item_delete_btn));
            }
            view.setTag(cVar);
        } else {
            cVar = (c) view.getTag();
        }
        if (m25220i(i9) == 1 && i10 == getChildrenCount(i9) - 1) {
            cVar.f22162a.setVisibility(4);
            cVar.f22166e.setVisibility(0);
            m25230s(i9, cVar.f22166e, cVar.f22167f);
        } else {
            cVar.f22162a.setVisibility(0);
            cVar.f22166e.setVisibility(8);
            Friend friend = (Friend) getChild(i9, i10);
            boolean z9 = this.f22154i.f13777b == friend.f13645c;
            cVar.f22165d.setTag(friend);
            m25231t(i9, cVar.f22165d, z9);
            if (friend.m15621b() != null && (textView = cVar.f22164c) != null) {
                textView.setText(friend.m15621b());
            }
            C6127a.m23469j(this.f22147b, cVar.f22163b, friend);
        }
        return view;
    }

    @Override // android.widget.ExpandableListAdapter
    public int getChildrenCount(int i9) {
        int size = (m25222k() && i9 == 1) ? this.f22151f.size() : this.f22152g.size();
        if (size == 1) {
            return 2;
        }
        return size;
    }

    @Override // android.widget.Filterable
    public Filter getFilter() {
        if (this.f22153h == null) {
            this.f22153h = new e(this, null);
        }
        return this.f22153h;
    }

    @Override // android.widget.ExpandableListAdapter
    public Object getGroup(int i9) {
        return null;
    }

    @Override // android.widget.ExpandableListAdapter
    public int getGroupCount() {
        return m25222k() ? 2 : 1;
    }

    @Override // android.widget.ExpandableListAdapter
    public long getGroupId(int i9) {
        return i9;
    }

    @Override // android.widget.ExpandableListAdapter
    public View getGroupView(int i9, boolean z8, View view, ViewGroup viewGroup) {
        d dVar;
        if (view == null) {
            view = ((LayoutInflater) this.f22147b.getSystemService("layout_inflater")).inflate(R.layout.view_item_edit_members_header, viewGroup, false);
            dVar = new d(this, null);
            dVar.f22169a = (TextView) view.findViewById(R.id.expandableListViewTitle);
            dVar.f22170b = (RelativeLayout) view.findViewById(R.id.relativeLayoutAddUser);
            view.setTag(dVar);
        } else {
            dVar = (d) view.getTag();
        }
        if (m25222k() && i9 == 1) {
            dVar.f22169a.setText(this.f22147b.getString(R.string.admins_D, Integer.valueOf(this.f22149d.size())));
            dVar.f22170b.setOnClickListener(this.f22158m);
        } else {
            dVar.f22169a.setText(this.f22147b.getString(R.string.members_D, Integer.valueOf(this.f22150e.size())));
            dVar.f22170b.setOnClickListener(this.f22159n);
        }
        return view;
    }

    /* renamed from: h */
    public List<Friend> m25219h() {
        return this.f22149d;
    }

    @Override // android.widget.ExpandableListAdapter
    public boolean hasStableIds() {
        return true;
    }

    /* renamed from: i */
    public final int m25220i(int i9) {
        return (m25222k() && i9 == 1) ? this.f22151f.size() : this.f22152g.size();
    }

    @Override // android.widget.ExpandableListAdapter
    public boolean isChildSelectable(int i9, int i10) {
        return false;
    }

    /* renamed from: j */
    public List<Friend> m25221j() {
        return this.f22150e;
    }

    /* renamed from: k */
    public final boolean m25222k() {
        return "Community".equals(this.f22155j);
    }

    /* renamed from: l */
    public boolean m25223l(Friend friend) {
        this.f22149d.remove(friend);
        this.f22151f.remove(friend);
        notifyDataSetChanged();
        return true;
    }

    /* renamed from: m */
    public boolean m25224m(Friend friend) {
        this.f22150e.remove(friend);
        this.f22152g.remove(friend);
        notifyDataSetChanged();
        return true;
    }

    /* renamed from: n */
    public void m25225n(List<Friend> list) {
        this.f22149d.clear();
        if (list != null) {
            this.f22149d.addAll(list);
        }
        this.f22151f.clear();
        if (list != null) {
            this.f22151f.addAll(list);
        }
        notifyDataSetChanged();
    }

    /* renamed from: o */
    public void m25226o(String str) {
        this.f22155j = str;
        notifyDataSetChanged();
    }

    /* renamed from: p */
    public void m25227p(List<Friend> list) {
        this.f22150e.clear();
        this.f22150e.addAll(list);
        this.f22152g.clear();
        this.f22152g.addAll(list);
        notifyDataSetChanged();
    }

    /* renamed from: q */
    public void m25228q(View.OnClickListener onClickListener) {
        this.f22156k = onClickListener;
    }

    /* renamed from: r */
    public void m25229r(View.OnClickListener onClickListener) {
        this.f22157l = onClickListener;
    }

    /* renamed from: s */
    public final void m25230s(int i9, RelativeLayout relativeLayout, TextView textView) {
        if (!m25222k()) {
            textView.setText(R.string.group_invite_member_title);
            relativeLayout.setOnClickListener(this.f22159n);
        } else if (i9 == 1) {
            textView.setText(R.string.group_add_admin_title);
            relativeLayout.setOnClickListener(this.f22158m);
        } else {
            textView.setText(R.string.group_invite_member_title);
            relativeLayout.setOnClickListener(this.f22159n);
        }
    }

    /* renamed from: t */
    public final void m25231t(int i9, View view, boolean z8) {
        if (!m25222k()) {
            view.setOnClickListener(this.f22157l);
            if (this.f22148c == null) {
                if (z8) {
                    view.setVisibility(4);
                    return;
                } else {
                    view.setVisibility(0);
                    return;
                }
            }
            return;
        }
        if (i9 == 1) {
            view.setOnClickListener(this.f22156k);
            if (this.f22148c == null) {
                if (z8) {
                    view.setVisibility(4);
                    return;
                } else {
                    view.setVisibility(0);
                    return;
                }
            }
            if (m25220i(i9) == 1) {
                view.setVisibility(4);
                return;
            } else {
                view.setVisibility(0);
                return;
            }
        }
        view.setOnClickListener(this.f22157l);
        Group group = this.f22148c;
        if (group == null) {
            if (z8) {
                view.setVisibility(4);
                return;
            } else {
                view.setVisibility(0);
                return;
            }
        }
        if (z8 && group.f13704C && m25220i(1) == 1 && m25220i(i9) > 1) {
            view.setVisibility(4);
        } else {
            view.setVisibility(0);
        }
    }
}
