package com.cyberlink.you.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
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
import com.cyberlink.you.BaseActivity;
import com.cyberlink.you.friends.CorpAccount;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import p116k4.C5148h;
import p116k4.C5179r0;
import p173q2.C6127a;

/* loaded from: classes.dex */
public class OfficialAccountActivity extends BaseActivity {

    /* renamed from: d */
    public ExpandableListView f8116d;

    /* renamed from: e */
    public C1566e f8117e;

    /* renamed from: g */
    public View f8119g;

    /* renamed from: h */
    public EditText f8120h;

    /* renamed from: c */
    public final String f8115c = "OfficialAccountActivity";

    /* renamed from: f */
    public ArrayList<String> f8118f = new ArrayList<>();

    /* renamed from: i */
    public String f8121i = null;

    /* renamed from: j */
    public View.OnClickListener f8122j = new ViewOnClickListenerC1562a();

    /* renamed from: k */
    public C5148h.b f8123k = new C1563b();

    /* renamed from: l */
    public TextWatcher f8124l = new C1564c();

    /* renamed from: m */
    public ExpandableListView.OnChildClickListener f8125m = new C1565d();

    /* renamed from: com.cyberlink.you.activity.OfficialAccountActivity$a */
    public class ViewOnClickListenerC1562a implements View.OnClickListener {
        public ViewOnClickListenerC1562a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            OfficialAccountActivity.this.finish();
        }
    }

    /* renamed from: com.cyberlink.you.activity.OfficialAccountActivity$b */
    public class C1563b implements C5148h.b {

        /* renamed from: com.cyberlink.you.activity.OfficialAccountActivity$b$a */
        public class a implements Runnable {

            /* renamed from: b */
            public final /* synthetic */ boolean f8128b;

            /* renamed from: c */
            public final /* synthetic */ List f8129c;

            public a(boolean z8, List list) {
                this.f8128b = z8;
                this.f8129c = list;
            }

            @Override // java.lang.Runnable
            public void run() {
                if (this.f8128b) {
                    OfficialAccountActivity.this.m8780o(this.f8129c);
                }
            }
        }

        public C1563b() {
        }

        @Override // p116k4.C5148h.b
        /* renamed from: a */
        public void mo8784a(boolean z8, List<CorpAccount> list) {
            OfficialAccountActivity.this.m8781q().runOnUiThread(new a(z8, list));
        }
    }

    /* renamed from: com.cyberlink.you.activity.OfficialAccountActivity$c */
    public class C1564c implements TextWatcher {
        public C1564c() {
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i9, int i10, int i11) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i9, int i10, int i11) {
            if (OfficialAccountActivity.this.f8117e != null) {
                OfficialAccountActivity.this.f8117e.getFilter().filter(charSequence.toString());
            }
        }
    }

    /* renamed from: com.cyberlink.you.activity.OfficialAccountActivity$d */
    public class C1565d implements ExpandableListView.OnChildClickListener {
        public C1565d() {
        }

        @Override // android.widget.ExpandableListView.OnChildClickListener
        public boolean onChildClick(ExpandableListView expandableListView, View view, int i9, int i10, long j9) {
            OfficialAccountActivity.this.m8783s((CorpAccount) OfficialAccountActivity.this.f8117e.getChild(i9, i10));
            return true;
        }
    }

    /* renamed from: com.cyberlink.you.activity.OfficialAccountActivity$e */
    public class C1566e extends BaseExpandableListAdapter implements Filterable {

        /* renamed from: b */
        public Context f8133b;

        /* renamed from: c */
        public Object f8134c = new Object();

        /* renamed from: d */
        public a f8135d;

        /* renamed from: e */
        public List<C1567f> f8136e;

        /* renamed from: f */
        public List<C1567f> f8137f;

        /* renamed from: com.cyberlink.you.activity.OfficialAccountActivity$e$a */
        public class a extends Filter {
            public a() {
            }

            @Override // android.widget.Filter
            public Filter.FilterResults performFiltering(CharSequence charSequence) {
                String lowerCase = charSequence.toString().toLowerCase(Locale.getDefault());
                Filter.FilterResults filterResults = new Filter.FilterResults();
                synchronized (C1566e.this.f8134c) {
                    List<C1567f> list = C1566e.this.f8136e;
                    ArrayList arrayList = new ArrayList();
                    for (C1567f c1567f : list) {
                        C1567f c1567f2 = new C1567f(OfficialAccountActivity.this, null);
                        List<Object> list2 = c1567f.f8141b;
                        ArrayList arrayList2 = new ArrayList();
                        Iterator<Object> it = list2.iterator();
                        while (it.hasNext()) {
                            CorpAccount corpAccount = (CorpAccount) it.next();
                            if (corpAccount.f13616e.toLowerCase(Locale.getDefault()).contains(lowerCase)) {
                                arrayList2.add(corpAccount);
                            }
                        }
                        c1567f2.f8140a = c1567f.f8140a;
                        c1567f2.f8141b = arrayList2;
                        arrayList.add(c1567f2);
                    }
                    filterResults.values = arrayList;
                    filterResults.count = arrayList.size();
                }
                return filterResults;
            }

            @Override // android.widget.Filter
            public void publishResults(CharSequence charSequence, Filter.FilterResults filterResults) {
                ArrayList arrayList = (ArrayList) filterResults.values;
                List<C1567f> list = C1566e.this.f8137f;
                if (list != null && arrayList != null) {
                    list.clear();
                    C1566e c1566e = C1566e.this;
                    c1566e.f8137f = arrayList;
                    c1566e.notifyDataSetChanged();
                    return;
                }
                Log.d("OfficialAccountActivity", "m_listOfficialCategoryFilter = " + C1566e.this.f8137f);
                Log.d("OfficialAccountActivity", "result listFilterCategories = " + arrayList);
            }

            public /* synthetic */ a(C1566e c1566e, ViewOnClickListenerC1562a viewOnClickListenerC1562a) {
                this();
            }
        }

        public C1566e(Context context, List<C1567f> list) {
            this.f8133b = context;
            m8785a(list);
        }

        /* renamed from: a */
        public void m8785a(List<C1567f> list) {
            if (this.f8136e == null) {
                this.f8136e = new ArrayList();
            }
            this.f8136e.clear();
            if (this.f8137f == null) {
                this.f8137f = new ArrayList();
            }
            this.f8137f.clear();
            if (this.f8136e == null || this.f8137f == null) {
                return;
            }
            for (C1567f c1567f : list) {
                this.f8136e.add(c1567f);
                this.f8137f.add(c1567f);
            }
        }

        @Override // android.widget.ExpandableListAdapter
        public Object getChild(int i9, int i10) {
            C1567f c1567f;
            List<Object> list;
            List<C1567f> list2 = this.f8137f;
            if (list2 == null || i9 >= list2.size() || (c1567f = this.f8137f.get(i9)) == null || (list = c1567f.f8141b) == null || i10 >= list.size()) {
                return null;
            }
            return c1567f.f8141b.get(i10);
        }

        @Override // android.widget.ExpandableListAdapter
        public long getChildId(int i9, int i10) {
            return i10;
        }

        @Override // android.widget.ExpandableListAdapter
        public View getChildView(int i9, int i10, boolean z8, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = ((LayoutInflater) this.f8133b.getSystemService("layout_inflater")).inflate(R.layout.view_item_friends_friends, viewGroup, false);
            }
            TextView textView = (TextView) view.findViewById(R.id.FriendsFriendsTextView_DisplayName);
            TextView textView2 = (TextView) view.findViewById(R.id.FriendsFriendsTextView_DisplayMood);
            ImageView imageView = (ImageView) view.findViewById(R.id.FriendsFriendsCircleImageView_DisplayImage);
            Button button = (Button) view.findViewById(R.id.FriendsFriendsBlockBtn);
            if (this.f8137f != null) {
                CorpAccount corpAccount = (CorpAccount) getChild(i9, i10);
                if (corpAccount != null) {
                    String str = corpAccount.f13616e;
                    if (str != null && textView != null) {
                        textView.setText(str);
                    }
                    String str2 = corpAccount.f13615d;
                    if (str2 != null && textView2 != null) {
                        textView2.setText(str2);
                    }
                    C6127a.m23467h(OfficialAccountActivity.this, imageView, corpAccount);
                }
                button.setVisibility(8);
            }
            return view;
        }

        @Override // android.widget.ExpandableListAdapter
        public int getChildrenCount(int i9) {
            C1567f c1567f;
            List<Object> list;
            List<C1567f> list2 = this.f8137f;
            if (list2 == null || i9 >= list2.size() || (c1567f = this.f8137f.get(i9)) == null || (list = c1567f.f8141b) == null) {
                return 0;
            }
            return list.size();
        }

        @Override // android.widget.Filterable
        public Filter getFilter() {
            if (this.f8135d == null) {
                this.f8135d = new a(this, null);
            }
            return this.f8135d;
        }

        @Override // android.widget.ExpandableListAdapter
        public Object getGroup(int i9) {
            List<C1567f> list = this.f8137f;
            if (list == null || i9 >= list.size()) {
                return null;
            }
            return this.f8137f.get(i9);
        }

        @Override // android.widget.ExpandableListAdapter
        public int getGroupCount() {
            List<C1567f> list = this.f8137f;
            if (list != null) {
                return list.size();
            }
            return 0;
        }

        @Override // android.widget.ExpandableListAdapter
        public long getGroupId(int i9) {
            return i9;
        }

        @Override // android.widget.ExpandableListAdapter
        public View getGroupView(int i9, boolean z8, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = ((LayoutInflater) this.f8133b.getSystemService("layout_inflater")).inflate(R.layout.view_item_friends_titles, viewGroup, false);
            }
            C1567f c1567f = (C1567f) getGroup(i9);
            if (c1567f != null) {
                TextView textView = (TextView) view.findViewById(R.id.FriendsTitleDisplayName);
                if (textView != null) {
                    textView.setText(c1567f.f8140a + "(" + c1567f.f8141b.size() + ")");
                    C5179r0.m20247b(textView, 1);
                } else {
                    Log.d("OfficialAccountActivity", "[getGroupView] txtViewGroupTitle = null");
                }
            } else {
                Log.d("OfficialAccountActivity", "[getGroupView] listObject = null");
            }
            View viewFindViewById = view.findViewById(R.id.FriendsTitleExpand);
            if (viewFindViewById.getVisibility() != 8) {
                viewFindViewById.setVisibility(8);
            }
            ((ExpandableListView) viewGroup).expandGroup(i9);
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
    }

    /* renamed from: com.cyberlink.you.activity.OfficialAccountActivity$f */
    public class C1567f {

        /* renamed from: a */
        public String f8140a;

        /* renamed from: b */
        public List<Object> f8141b;

        public C1567f() {
        }

        public /* synthetic */ C1567f(OfficialAccountActivity officialAccountActivity, ViewOnClickListenerC1562a viewOnClickListenerC1562a) {
            this();
        }
    }

    /* renamed from: n */
    public final void m8779n(CorpAccount corpAccount) {
        String str = this.f8121i;
        if (str == null || !str.equals(String.valueOf(corpAccount.f13614c))) {
            return;
        }
        m8783s(corpAccount);
        this.f8121i = null;
    }

    /* renamed from: o */
    public final void m8780o(List<CorpAccount> list) {
        if (list == null || list.size() == 0) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        HashMap map = new HashMap();
        for (CorpAccount corpAccount : list) {
            if (!map.containsKey(corpAccount.f13618g)) {
                map.put(corpAccount.f13618g, new ArrayList());
                this.f8118f.add(corpAccount.f13618g);
            }
            ((List) map.get(corpAccount.f13618g)).add(corpAccount);
            m8779n(corpAccount);
        }
        Collections.sort(this.f8118f);
        Iterator<String> it = this.f8118f.iterator();
        while (it.hasNext()) {
            String next = it.next();
            if (map.containsKey(next)) {
                C1567f c1567f = new C1567f(this, null);
                c1567f.f8140a = next;
                c1567f.f8141b = (List) map.get(next);
                arrayList.add(c1567f);
            }
        }
        this.f8117e.m8785a(arrayList);
        this.f8117e.notifyDataSetChanged();
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_official_account);
        View viewFindViewById = findViewById(R.id.OfficialAccountBackBtn);
        this.f8119g = viewFindViewById;
        viewFindViewById.setOnClickListener(this.f8122j);
        EditText editText = (EditText) findViewById(R.id.SearchEditText);
        this.f8120h = editText;
        editText.addTextChangedListener(this.f8124l);
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("userId")) {
            this.f8121i = intent.getStringExtra("userId");
        }
        m8782r();
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onDestroy() {
        this.f8119g.setOnClickListener(null);
        this.f8120h.removeTextChangedListener(this.f8124l);
        super.onDestroy();
    }

    /* renamed from: q */
    public final Activity m8781q() {
        return this;
    }

    /* renamed from: r */
    public final void m8782r() {
        this.f8116d = (ExpandableListView) findViewById(R.id.OfficialAccountExpandableListView);
        C1566e c1566e = new C1566e(m8781q(), new ArrayList());
        this.f8117e = c1566e;
        this.f8116d.setAdapter(c1566e);
        this.f8116d.setOnChildClickListener(this.f8125m);
        C5148h.m20055e().m20056c(this.f8123k);
        C5148h.m20055e().m20060h(false);
    }

    /* renamed from: s */
    public final void m8783s(CorpAccount corpAccount) {
        Intent intent = new Intent(m8781q(), (Class<?>) OfficialAccountDetailActivity.class);
        intent.putExtra("data", corpAccount);
        startActivity(intent);
    }
}
