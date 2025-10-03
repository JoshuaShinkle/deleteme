package com.cyberlink.you.activity.selectfromfriendgroup;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import com.cyberlink.p030U.R;
import com.cyberlink.you.friends.Friend;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import p173q2.C6127a;

/* renamed from: com.cyberlink.you.activity.selectfromfriendgroup.b */
/* loaded from: classes.dex */
public class C2453b extends BaseAdapter implements Filterable {

    /* renamed from: b */
    public C2452a f11198b;

    /* renamed from: c */
    public Context f11199c;

    /* renamed from: d */
    public List<Friend> f11200d;

    /* renamed from: e */
    public List<Friend> f11201e;

    /* renamed from: f */
    public Map<Friend, Boolean> f11202f = new HashMap();

    /* renamed from: g */
    public b f11203g;

    /* renamed from: h */
    public List<Friend> f11204h;

    /* renamed from: com.cyberlink.you.activity.selectfromfriendgroup.b$b */
    public class b extends Filter {
        public b() {
        }

        @Override // android.widget.Filter
        public Filter.FilterResults performFiltering(CharSequence charSequence) {
            String lowerCase = charSequence.toString().toLowerCase(Locale.getDefault());
            Filter.FilterResults filterResults = new Filter.FilterResults();
            ArrayList arrayList = new ArrayList();
            for (Friend friend : C2453b.this.f11200d) {
                if (friend.m15621b().toLowerCase(Locale.getDefault()).contains(lowerCase)) {
                    arrayList.add(friend);
                }
            }
            filterResults.values = arrayList;
            filterResults.count = arrayList.size();
            return filterResults;
        }

        @Override // android.widget.Filter
        public void publishResults(CharSequence charSequence, Filter.FilterResults filterResults) {
            C2453b.this.f11201e = (ArrayList) filterResults.values;
            C2453b.this.notifyDataSetChanged();
            C2453b.this.f11198b.m12620k();
        }
    }

    /* renamed from: com.cyberlink.you.activity.selectfromfriendgroup.b$c */
    public class c {

        /* renamed from: a */
        public ImageView f11206a;

        /* renamed from: b */
        public TextView f11207b;

        /* renamed from: c */
        public CheckBox f11208c;

        public c() {
        }
    }

    public C2453b(C2452a c2452a, Context context, List<Friend> list) {
        this.f11198b = c2452a;
        this.f11199c = context;
        this.f11200d = list;
        this.f11201e = list;
        Iterator<Friend> it = list.iterator();
        while (it.hasNext()) {
            this.f11202f.put(it.next(), Boolean.FALSE);
        }
    }

    @Override // android.widget.Adapter
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public Friend getItem(int i9) {
        return this.f11201e.get(i9);
    }

    /* renamed from: e */
    public void m12579e(List<Friend> list) {
        this.f11204h = list;
    }

    /* renamed from: f */
    public void m12580f(int i9, boolean z8) {
        m12581g(getItem(i9), z8);
    }

    /* renamed from: g */
    public void m12581g(Friend friend, boolean z8) {
        this.f11202f.put(friend, Boolean.valueOf(z8));
        notifyDataSetChanged();
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.f11201e.size();
    }

    @Override // android.widget.Filterable
    public Filter getFilter() {
        if (this.f11203g == null) {
            this.f11203g = new b();
        }
        return this.f11203g;
    }

    @Override // android.widget.Adapter
    public long getItemId(int i9) {
        return 0L;
    }

    @Override // android.widget.Adapter
    public View getView(int i9, View view, ViewGroup viewGroup) {
        c cVar;
        if (view == null) {
            view = ((LayoutInflater) this.f11199c.getSystemService("layout_inflater")).inflate(R.layout.view_item_firend_group_create, viewGroup, false);
            cVar = new c();
            cVar.f11207b = (TextView) view.findViewById(R.id.GroupCreateMemberName);
            cVar.f11206a = (ImageView) view.findViewById(R.id.GroupCreateImageView);
            cVar.f11208c = (CheckBox) view.findViewById(R.id.GroupCreateMemberCheckBox);
            view.setTag(cVar);
        } else {
            cVar = (c) view.getTag();
        }
        Friend friend = this.f11201e.get(i9);
        if (friend.m15621b() != null) {
            cVar.f11207b.setText(friend.m15621b());
        }
        C6127a.m23469j(this.f11199c, cVar.f11206a, friend);
        cVar.f11208c.setChecked(this.f11202f.get(friend).booleanValue());
        if (this.f11204h != null) {
            cVar.f11208c.setEnabled(!r0.contains(friend));
        }
        return view;
    }
}
