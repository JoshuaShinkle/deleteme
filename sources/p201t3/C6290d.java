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
import com.cyberlink.you.friends.Group;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import p173q2.C6127a;
import p209u2.C6383t;
import p244y1.C6597b;

/* renamed from: t3.d */
/* loaded from: classes.dex */
public class C6290d extends C6597b<Group> {

    /* renamed from: t3.d$b */
    public class b extends Filter {
        public b() {
        }

        @Override // android.widget.Filter
        public Filter.FilterResults performFiltering(CharSequence charSequence) {
            String lowerCase = charSequence.toString().toLowerCase(Locale.getDefault());
            Filter.FilterResults filterResults = new Filter.FilterResults();
            ArrayList arrayList = new ArrayList();
            for (Group group : C6290d.this.f22176e) {
                if (group != null && group.f13717d.toLowerCase(Locale.getDefault()).contains(lowerCase)) {
                    arrayList.add(group);
                }
            }
            filterResults.values = arrayList;
            filterResults.count = arrayList.size();
            return filterResults;
        }

        @Override // android.widget.Filter
        public void publishResults(CharSequence charSequence, Filter.FilterResults filterResults) {
            C6290d.this.f22175d = (ArrayList) filterResults.values;
            C6290d.this.notifyDataSetChanged();
        }
    }

    /* renamed from: t3.d$c */
    public static class c {

        /* renamed from: a */
        public final ImageView f21226a;

        /* renamed from: b */
        public final TextView f21227b;

        /* renamed from: c */
        public final TextView f21228c;

        /* renamed from: d */
        public final CheckBox f21229d;

        /* renamed from: e */
        public final ImageView f21230e;

        public c(ImageView imageView, TextView textView, TextView textView2, CheckBox checkBox, ImageView imageView2) {
            this.f21226a = imageView;
            this.f21227b = textView;
            this.f21228c = textView2;
            this.f21229d = checkBox;
            this.f21230e = imageView2;
        }
    }

    public C6290d(Context context, int i9, List<Group> list) {
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
            view = ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(R.layout.view_item_friends_groups, viewGroup, false);
            cVar = new c((ImageView) view.findViewById(R.id.FriendsGroupsCircleImageView_DisplayImage), (TextView) view.findViewById(R.id.FriendsGroupsTextView_GroupName), (TextView) view.findViewById(R.id.FriendsGroupsTextView_MemberCount), (CheckBox) view.findViewById(R.id.chatGroupItemCheckBox), (ImageView) view.findViewById(R.id.friendsGroupLockView));
            view.setTag(cVar);
        } else {
            cVar = (c) view.getTag();
        }
        Group group = (Group) this.f22175d.get(i9);
        String str = group.f13717d;
        if (str != null) {
            cVar.f21227b.setText(str);
        }
        cVar.f21228c.setText(String.valueOf(group.f13728o));
        C6127a.m23470k(getContext(), cVar.f21226a, group);
        if (m25234c()) {
            cVar.f21229d.setVisibility(0);
            cVar.f21229d.setChecked(this.f22174c.contains(group));
        } else {
            cVar.f21229d.setVisibility(8);
        }
        cVar.f21230e.setVisibility(group.f13711J ? 0 : 8);
        return view;
    }

    /* renamed from: j */
    public Group m24107j(String str) {
        for (T t8 : this.f22176e) {
            if (C6383t.m24513b(t8.f13723j, str)) {
                return t8;
            }
        }
        return null;
    }
}
