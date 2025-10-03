package com.cyberlink.you.activity.friend;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
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
import com.cyberlink.you.friends.Friend;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import p173q2.C6127a;
import p209u2.C6385v;

/* loaded from: classes.dex */
public class FriendSelectedActivity extends BaseActivity {

    /* renamed from: e */
    public EditText f10628e;

    /* renamed from: i */
    public View f10632i;

    /* renamed from: c */
    public Button f10626c = null;

    /* renamed from: d */
    public ListView f10627d = null;

    /* renamed from: f */
    public ArrayAdapter<Friend> f10629f = null;

    /* renamed from: g */
    public View f10630g = null;

    /* renamed from: h */
    public TextView f10631h = null;

    /* renamed from: j */
    public Map<Friend, Boolean> f10633j = new HashMap();

    /* renamed from: k */
    public String f10634k = null;

    /* renamed from: l */
    public int f10635l = 0;

    /* renamed from: m */
    public View.OnClickListener f10636m = new ViewOnClickListenerC2135a();

    /* renamed from: n */
    public View.OnClickListener f10637n = new ViewOnClickListenerC2136b();

    /* renamed from: o */
    public AdapterView.OnItemClickListener f10638o = new C2138d();

    /* renamed from: p */
    public TextWatcher f10639p = new C2139e();

    /* renamed from: q */
    public AbsListView.OnScrollListener f10640q = new C2140f();

    /* renamed from: com.cyberlink.you.activity.friend.FriendSelectedActivity$a */
    public class ViewOnClickListenerC2135a implements View.OnClickListener {
        public ViewOnClickListenerC2135a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            FriendSelectedActivity.this.m12383H();
        }
    }

    /* renamed from: com.cyberlink.you.activity.friend.FriendSelectedActivity$b */
    public class ViewOnClickListenerC2136b implements View.OnClickListener {
        public ViewOnClickListenerC2136b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ArrayList<? extends Parcelable> arrayList = new ArrayList<>();
            for (Map.Entry entry : FriendSelectedActivity.this.f10633j.entrySet()) {
                if (((Boolean) entry.getValue()).booleanValue()) {
                    arrayList.add((Friend) entry.getKey());
                }
            }
            Collections.sort(arrayList, new Friend.C3041b());
            Intent intent = new Intent();
            intent.putParcelableArrayListExtra("results", arrayList);
            FriendSelectedActivity.this.setResult(-1, intent);
            FriendSelectedActivity.this.finish();
        }
    }

    /* renamed from: com.cyberlink.you.activity.friend.FriendSelectedActivity$c */
    public class AsyncTaskC2137c extends AsyncTask<Void, Void, List<Friend>> {

        /* renamed from: a */
        public final /* synthetic */ List f10643a;

        public AsyncTaskC2137c(List list) {
            this.f10643a = list;
        }

        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public List<Friend> doInBackground(Void... voidArr) {
            Thread.currentThread().setName("FriendSelectedActivity.initListView");
            List list = this.f10643a;
            if (list != null) {
                Collections.sort(list, new Friend.C3041b());
            }
            return this.f10643a;
        }

        @Override // android.os.AsyncTask
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(List<Friend> list) {
            if (list == null) {
                Log.d("FriendSelectedActivity", "[initListView] null friend list view");
                return;
            }
            FriendSelectedActivity friendSelectedActivity = FriendSelectedActivity.this;
            FriendSelectedActivity friendSelectedActivity2 = FriendSelectedActivity.this;
            friendSelectedActivity.f10629f = friendSelectedActivity2.new C2141g(friendSelectedActivity2.m12379B(), R.layout.view_item_friends_selected, list);
            FriendSelectedActivity.this.f10627d.setAdapter((ListAdapter) FriendSelectedActivity.this.f10629f);
            FriendSelectedActivity.this.f10627d.setOnItemClickListener(FriendSelectedActivity.this.f10638o);
            FriendSelectedActivity.this.f10627d.setOnScrollListener(FriendSelectedActivity.this.f10640q);
            FriendSelectedActivity.this.f10629f.getFilter().filter(FriendSelectedActivity.this.f10628e.getText().toString());
        }
    }

    /* renamed from: com.cyberlink.you.activity.friend.FriendSelectedActivity$d */
    public class C2138d implements AdapterView.OnItemClickListener {
        public C2138d() {
        }

        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView<?> adapterView, View view, int i9, long j9) {
            Friend friend;
            if (FriendSelectedActivity.this.f10635l == 0) {
                Iterator it = FriendSelectedActivity.this.f10633j.entrySet().iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    Map.Entry entry = (Map.Entry) it.next();
                    if (((Boolean) entry.getValue()).booleanValue() && (friend = (Friend) entry.getKey()) != ((Friend) FriendSelectedActivity.this.f10629f.getItem(i9))) {
                        FriendSelectedActivity.this.f10633j.put(friend, Boolean.FALSE);
                        break;
                    }
                }
            }
            FriendSelectedActivity.this.f10633j.put((Friend) FriendSelectedActivity.this.f10629f.getItem(i9), Boolean.valueOf(!((Boolean) FriendSelectedActivity.this.f10633j.get(r1)).booleanValue()));
            if (FriendSelectedActivity.this.f10629f != null) {
                FriendSelectedActivity.this.f10629f.notifyDataSetChanged();
            }
            FriendSelectedActivity.this.m12384I();
        }
    }

    /* renamed from: com.cyberlink.you.activity.friend.FriendSelectedActivity$e */
    public class C2139e implements TextWatcher {
        public C2139e() {
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i9, int i10, int i11) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i9, int i10, int i11) {
            FriendSelectedActivity.this.f10629f.getFilter().filter(charSequence.toString());
        }
    }

    /* renamed from: com.cyberlink.you.activity.friend.FriendSelectedActivity$f */
    public class C2140f implements AbsListView.OnScrollListener {

        /* renamed from: a */
        public boolean f10647a = false;

        /* renamed from: b */
        public int f10648b = -1;

        /* renamed from: c */
        public String f10649c = null;

        public C2140f() {
        }

        /* renamed from: a */
        public final String m12387a(String str) {
            return str.length() > 0 ? String.valueOf(Character.toChars(str.codePointAt(0))) : "";
        }

        /* renamed from: b */
        public final void m12388b() {
            if (FriendSelectedActivity.this.f10630g != null && FriendSelectedActivity.this.f10630g.isShown()) {
                FriendSelectedActivity.this.f10630g.setVisibility(4);
            }
            m12389c();
        }

        /* renamed from: c */
        public final void m12389c() {
            this.f10648b = -1;
            this.f10649c = null;
        }

        /* renamed from: d */
        public final void m12390d() {
            Friend friend = (Friend) FriendSelectedActivity.this.f10629f.getItem(FriendSelectedActivity.this.f10627d.getFirstVisiblePosition());
            if (friend != null) {
                String strM15621b = friend.m15621b();
                if (FriendSelectedActivity.this.f10631h != null) {
                    String strM12387a = m12387a(strM15621b);
                    String str = this.f10649c;
                    if (str == null || !strM12387a.equals(str)) {
                        FriendSelectedActivity.this.f10631h.setText(strM12387a);
                        m12391e();
                        this.f10649c = strM12387a;
                    }
                }
            }
        }

        /* renamed from: e */
        public final void m12391e() {
            if (FriendSelectedActivity.this.f10630g != null && !FriendSelectedActivity.this.f10630g.isShown()) {
                FriendSelectedActivity.this.f10630g.setVisibility(0);
            }
            m12389c();
        }

        @Override // android.widget.AbsListView.OnScrollListener
        public void onScroll(AbsListView absListView, int i9, int i10, int i11) {
            int firstVisiblePosition;
            if (this.f10647a && this.f10648b != (firstVisiblePosition = FriendSelectedActivity.this.f10627d.getFirstVisiblePosition())) {
                this.f10648b = firstVisiblePosition;
                m12390d();
            }
        }

        @Override // android.widget.AbsListView.OnScrollListener
        public void onScrollStateChanged(AbsListView absListView, int i9) {
            if (i9 == 0) {
                this.f10647a = false;
                m12388b();
            } else if (i9 == 1) {
                this.f10647a = true;
                m12389c();
            }
        }
    }

    /* renamed from: com.cyberlink.you.activity.friend.FriendSelectedActivity$g */
    public class C2141g extends ArrayAdapter<Friend> implements Filterable {

        /* renamed from: b */
        public List<Friend> f10651b;

        /* renamed from: c */
        public List<Friend> f10652c;

        /* renamed from: d */
        public a f10653d;

        /* renamed from: com.cyberlink.you.activity.friend.FriendSelectedActivity$g$a */
        public class a extends Filter {
            public a() {
            }

            @Override // android.widget.Filter
            public Filter.FilterResults performFiltering(CharSequence charSequence) {
                String lowerCase = charSequence.toString().toLowerCase(Locale.getDefault());
                Filter.FilterResults filterResults = new Filter.FilterResults();
                ArrayList arrayList = new ArrayList();
                for (Friend friend : C2141g.this.f10651b) {
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
                C2141g.this.f10652c = (ArrayList) filterResults.values;
                C2141g.this.notifyDataSetChanged();
                if (C2141g.this.f10652c.size() > 0) {
                    FriendSelectedActivity.this.f10632i.setVisibility(8);
                } else {
                    FriendSelectedActivity.this.f10632i.setVisibility(0);
                }
            }

            public /* synthetic */ a(C2141g c2141g, ViewOnClickListenerC2135a viewOnClickListenerC2135a) {
                this();
            }
        }

        public C2141g(Context context, int i9, List<Friend> list) {
            super(context, i9, list);
            this.f10651b = list;
            this.f10652c = list;
            Iterator<Friend> it = list.iterator();
            while (it.hasNext()) {
                FriendSelectedActivity.this.f10633j.put(it.next(), Boolean.FALSE);
            }
        }

        @Override // android.widget.ArrayAdapter, android.widget.Adapter
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public Friend getItem(int i9) {
            return this.f10652c.get(i9);
        }

        @Override // android.widget.ArrayAdapter, android.widget.Adapter
        public int getCount() {
            return this.f10652c.size();
        }

        @Override // android.widget.ArrayAdapter, android.widget.Filterable
        public Filter getFilter() {
            if (this.f10653d == null) {
                this.f10653d = new a(this, null);
            }
            return this.f10653d;
        }

        @Override // android.widget.ArrayAdapter, android.widget.Adapter
        public View getView(int i9, View view, ViewGroup viewGroup) {
            C2142h c2142h;
            TextView textView;
            if (view == null) {
                view = ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(R.layout.view_item_friends_selected, viewGroup, false);
                c2142h = new C2142h(FriendSelectedActivity.this, null);
                c2142h.f10657b = (TextView) view.findViewById(R.id.userShowName);
                c2142h.f10656a = (ImageView) view.findViewById(R.id.userAvatar);
                c2142h.f10658c = (ImageView) view.findViewById(R.id.itemCheckBox);
                view.setTag(c2142h);
            } else {
                c2142h = (C2142h) view.getTag();
            }
            if (c2142h != null) {
                Friend item = getItem(i9);
                if (item != null && item.m15621b() != null && (textView = c2142h.f10657b) != null) {
                    textView.setText(item.m15621b());
                }
                C6127a.m23469j(FriendSelectedActivity.this, c2142h.f10656a, item);
                c2142h.f10658c.setSelected(((Boolean) FriendSelectedActivity.this.f10633j.get(item)).booleanValue());
            }
            return view;
        }
    }

    /* renamed from: com.cyberlink.you.activity.friend.FriendSelectedActivity$h */
    public class C2142h {

        /* renamed from: a */
        public ImageView f10656a;

        /* renamed from: b */
        public TextView f10657b;

        /* renamed from: c */
        public ImageView f10658c;

        public C2142h() {
        }

        public /* synthetic */ C2142h(FriendSelectedActivity friendSelectedActivity, ViewOnClickListenerC2135a viewOnClickListenerC2135a) {
            this();
        }
    }

    /* renamed from: B */
    public final Activity m12379B() {
        return this;
    }

    /* renamed from: C */
    public final int m12380C() {
        Iterator<Map.Entry<Friend, Boolean>> it = this.f10633j.entrySet().iterator();
        int i9 = 0;
        while (it.hasNext()) {
            if (it.next().getValue().booleanValue()) {
                i9++;
            }
        }
        return i9;
    }

    /* renamed from: D */
    public final void m12381D() {
        Intent intent = getIntent();
        this.f10635l = intent.getIntExtra("listSelectMode", 0);
        m12384I();
        new AsyncTaskC2137c(intent.getParcelableArrayListExtra("originalMembers")).executeOnExecutor(C6385v.f21553a, new Void[0]);
    }

    /* renamed from: E */
    public final void m12382E() {
        findViewById(R.id.SelectFriendBackBtn).setOnClickListener(this.f10636m);
        Button button = (Button) findViewById(R.id.SelectFriendDoneBtn);
        this.f10626c = button;
        button.setOnClickListener(this.f10637n);
        this.f10627d = (ListView) findViewById(R.id.SelectFriendListView);
        this.f10630g = findViewById(R.id.AlphabeticScrollTextLayout);
        this.f10631h = (TextView) findViewById(R.id.AlphabeticScrollTextView);
        EditText editText = (EditText) findViewById(R.id.SearchEditText);
        this.f10628e = editText;
        editText.addTextChangedListener(this.f10639p);
        this.f10632i = findViewById(R.id.SelectFriendSearchEmptyView);
    }

    /* renamed from: H */
    public final void m12383H() {
        finish();
    }

    /* renamed from: I */
    public final void m12384I() {
        int iM12380C = m12380C();
        if (this.f10635l == 1) {
            this.f10626c.setText(this.f10634k + " (" + iM12380C + ")");
        }
        this.f10626c.setEnabled(iM12380C > 0);
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        m12383H();
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_select_friend);
        Intent intent = getIntent();
        if (intent == null || !intent.hasExtra("buttonDisplayingString")) {
            this.f10634k = getString(R.string.done_btn);
        } else {
            this.f10634k = intent.getStringExtra("buttonDisplayingString");
        }
        m12382E();
        m12381D();
    }
}
