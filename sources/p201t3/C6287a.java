package p201t3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;
import com.cyberlink.p030U.R;
import com.cyberlink.you.database.C2950b0;
import com.cyberlink.you.friends.Friend;
import com.cyberlink.you.friends.Group;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import p173q2.C6127a;
import p209u2.C6383t;
import p244y1.C6597b;

/* renamed from: t3.a */
/* loaded from: classes.dex */
public class C6287a extends C6597b<Friend> {

    /* renamed from: t3.a$b */
    public class b extends Filter {
        public b() {
        }

        @Override // android.widget.Filter
        public Filter.FilterResults performFiltering(CharSequence charSequence) {
            String lowerCase = charSequence.toString().toLowerCase(Locale.getDefault());
            Filter.FilterResults filterResults = new Filter.FilterResults();
            ArrayList arrayList = new ArrayList();
            for (Friend friend : C6287a.this.f22176e) {
                if (friend != null && friend.m15621b().toLowerCase(Locale.getDefault()).contains(lowerCase)) {
                    arrayList.add(friend);
                }
            }
            filterResults.values = arrayList;
            filterResults.count = arrayList.size();
            return filterResults;
        }

        @Override // android.widget.Filter
        public void publishResults(CharSequence charSequence, Filter.FilterResults filterResults) {
            C6287a.this.f22175d = (ArrayList) filterResults.values;
            C6287a.this.notifyDataSetChanged();
        }
    }

    /* renamed from: t3.a$c */
    public static class c {

        /* renamed from: a */
        public final ImageView f21201a;

        /* renamed from: b */
        public final TextView f21202b;

        /* renamed from: c */
        public final TextView f21203c;

        /* renamed from: d */
        public final CheckBox f21204d;

        /* renamed from: e */
        public final ImageView f21205e;

        public c(ImageView imageView, TextView textView, TextView textView2, CheckBox checkBox, ImageView imageView2) {
            this.f21201a = imageView;
            this.f21202b = textView;
            this.f21203c = textView2;
            this.f21204d = checkBox;
            this.f21205e = imageView2;
        }
    }

    public C6287a(Context context, int i9, List<Friend> list) {
        super(context, i9, list);
    }

    @Override // android.widget.ArrayAdapter, android.widget.Filterable
    public Filter getFilter() {
        if (this.f22177f == null) {
            this.f22177f = new b();
        }
        return this.f22177f;
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public View getView(int i9, View view, ViewGroup viewGroup) {
        c cVar;
        if (view == null) {
            view = ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(R.layout.view_item_friends_friends, viewGroup, false);
            cVar = new c((ImageView) view.findViewById(R.id.FriendsFriendsCircleImageView_DisplayImage), (TextView) view.findViewById(R.id.FriendsFriendsTextView_DisplayName), (TextView) view.findViewById(R.id.FriendsFriendsTextView_DisplayMood), (CheckBox) view.findViewById(R.id.chatGroupItemCheckBox), (ImageView) view.findViewById(R.id.friendsFriendsLockView));
            view.setTag(cVar);
        } else {
            cVar = (c) view.getTag();
        }
        Friend friend = (Friend) this.f22175d.get(i9);
        if (friend.m15621b() != null) {
            cVar.f21202b.setText(friend.m15621b());
        }
        C6127a.m23469j(getContext(), cVar.f21201a, friend);
        if (m25234c()) {
            cVar.f21204d.setVisibility(0);
            cVar.f21204d.setChecked(this.f22174c.contains(friend));
        } else {
            cVar.f21204d.setVisibility(8);
        }
        Group groupM15081r = C2950b0.m14912k().m15081r(friend.f13648f);
        if (groupM15081r != null) {
            cVar.f21205e.setVisibility(groupM15081r.f13711J ? 0 : 8);
        }
        return view;
    }

    /* renamed from: j */
    public Friend m24072j(String str) {
        for (T t8 : this.f22176e) {
            if (C6383t.m24513b(t8.f13648f, str)) {
                return t8;
            }
        }
        return null;
    }
}
