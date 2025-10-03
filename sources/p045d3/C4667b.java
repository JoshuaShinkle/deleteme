package p045d3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import com.cyberlink.p030U.R;
import com.cyberlink.you.Globals;
import com.cyberlink.you.activity.selectfromfriendgroup.C2454c;
import com.cyberlink.you.friends.Group;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import p173q2.C6127a;

/* renamed from: d3.b */
/* loaded from: classes.dex */
public class C4667b extends BaseAdapter implements Filterable {

    /* renamed from: b */
    public C2454c f16342b;

    /* renamed from: c */
    public Context f16343c;

    /* renamed from: d */
    public List<Group> f16344d;

    /* renamed from: e */
    public List<Group> f16345e;

    /* renamed from: f */
    public b f16346f;

    /* renamed from: d3.b$b */
    public class b extends Filter {
        public b() {
        }

        @Override // android.widget.Filter
        public Filter.FilterResults performFiltering(CharSequence charSequence) {
            String lowerCase = charSequence.toString().toLowerCase(Locale.getDefault());
            Filter.FilterResults filterResults = new Filter.FilterResults();
            ArrayList arrayList = new ArrayList();
            for (Group group : C4667b.this.f16344d) {
                if (group.f13717d.toLowerCase(Locale.getDefault()).contains(lowerCase)) {
                    arrayList.add(group);
                }
            }
            filterResults.values = arrayList;
            filterResults.count = arrayList.size();
            return filterResults;
        }

        @Override // android.widget.Filter
        public void publishResults(CharSequence charSequence, Filter.FilterResults filterResults) {
            C4667b.this.f16345e = (ArrayList) filterResults.values;
            C4667b.this.notifyDataSetChanged();
            C4667b.this.f16342b.m12620k();
        }
    }

    /* renamed from: d3.b$c */
    public class c {

        /* renamed from: a */
        public TextView f16348a;

        /* renamed from: b */
        public ImageView f16349b;

        /* renamed from: c */
        public TextView f16350c;

        /* renamed from: d */
        public TextView f16351d;

        public c() {
        }
    }

    public C4667b(C2454c c2454c, Context context, List<Group> list) {
        this.f16342b = c2454c;
        this.f16343c = context;
        this.f16344d = list;
        this.f16345e = list;
    }

    /* renamed from: d */
    public final int m18672d(int i9) {
        return this.f16343c.getResources().getDimensionPixelSize(i9);
    }

    @Override // android.widget.Adapter
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public Group getItem(int i9) {
        return this.f16345e.get(i9);
    }

    /* renamed from: f */
    public final void m18674f(TextView textView) {
        textView.setMaxWidth(((((Globals.m7388i0().getResources().getDisplayMetrics().widthPixels - m18672d(R.dimen.add_members_group_list_padding)) - m18672d(R.dimen.add_members_group_selected_count_width)) - m18672d(R.dimen.add_members_group_avatar_width)) - m18672d(R.dimen.add_members_group_display_name_margin)) - m18672d(R.dimen.add_members_group_member_count_width));
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.f16345e.size();
    }

    @Override // android.widget.Filterable
    public Filter getFilter() {
        if (this.f16346f == null) {
            this.f16346f = new b();
        }
        return this.f16346f;
    }

    @Override // android.widget.Adapter
    public long getItemId(int i9) {
        return 0L;
    }

    @Override // android.widget.Adapter
    public View getView(int i9, View view, ViewGroup viewGroup) {
        c cVar;
        if (view == null) {
            view = ((LayoutInflater) this.f16343c.getSystemService("layout_inflater")).inflate(R.layout.view_item_selectfromgroups, viewGroup, false);
            cVar = new c();
            cVar.f16348a = (TextView) view.findViewById(R.id.textViewSelectedCount);
            cVar.f16350c = (TextView) view.findViewById(R.id.textViewDisplayName);
            cVar.f16349b = (ImageView) view.findViewById(R.id.imageViewAvatar);
            cVar.f16351d = (TextView) view.findViewById(R.id.textViewMembersCount);
            view.setTag(cVar);
        } else {
            cVar = (c) view.getTag();
        }
        Group group = this.f16345e.get(i9);
        String str = group.f13717d;
        if (str != null) {
            cVar.f16350c.setText(str);
        }
        C6127a.m23470k(this.f16343c, cVar.f16349b, group);
        if (this.f16342b.m12598B(group.f13727n) > 0) {
            cVar.f16348a.setVisibility(0);
            cVar.f16348a.setText(String.valueOf(this.f16342b.m12598B(group.f13727n)));
        } else {
            cVar.f16348a.setVisibility(4);
        }
        cVar.f16351d.setText(String.valueOf(group.f13728o));
        m18674f(cVar.f16350c);
        return view;
    }
}
