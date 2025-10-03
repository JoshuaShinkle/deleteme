package com.cyberlink.you.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
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
import com.cyberlink.you.activity.SelectUsersActivity;
import com.cyberlink.you.database.C2950b0;
import com.cyberlink.you.friends.Friend;
import com.cyberlink.you.utility.CLUtility;
import com.cyberlink.you.widgetpool.tokenautocomplete.PeopleCompleteView;
import com.cyberlink.you.widgetpool.tokenautocomplete.TokenCompleteTextView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import p042d0.C4619d;
import p116k4.C5179r0;
import p132m.C5305d;
import p173q2.C6127a;

/* loaded from: classes.dex */
public class SelectUsersActivity extends BaseActivity {

    /* renamed from: c */
    public List<Friend> f8864c;

    /* renamed from: d */
    public List<Friend> f8865d;

    /* renamed from: e */
    public List<Friend> f8866e;

    /* renamed from: f */
    public List<Friend> f8867f;

    /* renamed from: g */
    public ListView f8868g;

    /* renamed from: h */
    public View f8869h;

    /* renamed from: i */
    public PeopleCompleteView f8870i;

    /* renamed from: j */
    public Button f8871j;

    /* renamed from: k */
    public Button f8872k;

    /* renamed from: l */
    public Button f8873l;

    /* renamed from: n */
    public String f8875n;

    /* renamed from: o */
    public C1705g f8876o;

    /* renamed from: m */
    public int f8874m = 0;

    /* renamed from: p */
    public C4619d f8877p = null;

    /* renamed from: q */
    public C5305d<Boolean> f8878q = new C5305d<>();

    /* renamed from: r */
    public boolean f8879r = false;

    /* renamed from: s */
    public String f8880s = "";

    /* renamed from: t */
    public View.OnClickListener f8881t = new View.OnClickListener() { // from class: com.cyberlink.you.activity.le
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f10858b.m9794X(view);
        }
    };

    /* renamed from: u */
    public AdapterView.OnItemClickListener f8882u = new C1699a();

    /* renamed from: v */
    public View.OnClickListener f8883v = new ViewOnClickListenerC1700b();

    /* renamed from: w */
    public View.OnClickListener f8884w = new ViewOnClickListenerC1701c();

    /* renamed from: x */
    public View.OnClickListener f8885x = new ViewOnClickListenerC1702d();

    /* renamed from: com.cyberlink.you.activity.SelectUsersActivity$a */
    public class C1699a implements AdapterView.OnItemClickListener {
        public C1699a() {
        }

        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView<?> adapterView, View view, int i9, long j9) {
            C1706h c1706h = (C1706h) view.getTag();
            if (c1706h.f8905c.isEnabled()) {
                long j10 = SelectUsersActivity.this.f8876o.getItem(i9).f13645c;
                boolean zM9817Q = SelectUsersActivity.this.m9817Q(j10);
                SelectUsersActivity.this.f8878q.m20726j(j10, Boolean.valueOf(!zM9817Q));
                c1706h.f8905c.setChecked(!zM9817Q);
                Friend friendM15003C = C2950b0.m14899A().m15003C(String.valueOf(j10));
                if (zM9817Q) {
                    SelectUsersActivity.this.f8867f.remove(friendM15003C);
                    SelectUsersActivity.this.f8870i.m17426S(friendM15003C);
                } else if (SelectUsersActivity.this.f8864c == null || !SelectUsersActivity.this.f8864c.contains(friendM15003C)) {
                    SelectUsersActivity.m9802q(SelectUsersActivity.this);
                    SelectUsersActivity.this.f8867f.add(friendM15003C);
                    SelectUsersActivity.this.f8870i.m17434p(friendM15003C);
                }
                SelectUsersActivity.this.m9820c0();
                SelectUsersActivity.this.m9821d0();
            }
        }
    }

    /* renamed from: com.cyberlink.you.activity.SelectUsersActivity$b */
    public class ViewOnClickListenerC1700b implements View.OnClickListener {

        /* renamed from: com.cyberlink.you.activity.SelectUsersActivity$b$a */
        public class a extends AsyncTask<Void, Void, Void> {

            /* renamed from: a */
            public final /* synthetic */ ArrayList f8888a;

            /* renamed from: b */
            public final /* synthetic */ ProgressDialog f8889b;

            /* renamed from: c */
            public final /* synthetic */ Intent f8890c;

            /* renamed from: d */
            public final /* synthetic */ Bundle f8891d;

            public a(ArrayList arrayList, ProgressDialog progressDialog, Intent intent, Bundle bundle) {
                this.f8888a = arrayList;
                this.f8889b = progressDialog;
                this.f8890c = intent;
                this.f8891d = bundle;
            }

            @Override // android.os.AsyncTask
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public Void doInBackground(Void... voidArr) {
                for (int i9 = 0; i9 < SelectUsersActivity.this.f8878q.m20730n(); i9++) {
                    long jM20725i = SelectUsersActivity.this.f8878q.m20725i(i9);
                    if (SelectUsersActivity.this.m9817Q(jM20725i)) {
                        Friend friendM15003C = C2950b0.m14899A().m15003C(String.valueOf(jM20725i));
                        boolean z8 = SelectUsersActivity.this.f8864c != null && SelectUsersActivity.this.f8864c.contains(friendM15003C);
                        if (friendM15003C != null && !z8) {
                            this.f8888a.add(friendM15003C);
                        }
                    }
                }
                Globals.C1408b.m7655b().m7657d("INTENT_RESULT_SELECTED_USERS_LIST", this.f8888a);
                return null;
            }

            @Override // android.os.AsyncTask
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public void onPostExecute(Void r32) {
                if (this.f8889b.isShowing()) {
                    this.f8889b.dismiss();
                }
                this.f8890c.putExtras(this.f8891d);
                SelectUsersActivity.this.setResult(-1, this.f8890c);
                SelectUsersActivity.this.finish();
            }
        }

        public ViewOnClickListenerC1700b() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: b */
        public /* synthetic */ void m9823b() {
            CLUtility.m16589t1(SelectUsersActivity.this.m9813L());
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            view.setClickable(false);
            SelectUsersActivity.this.m9813L().runOnUiThread(new Runnable() { // from class: com.cyberlink.you.activity.ne
                @Override // java.lang.Runnable
                public final void run() {
                    this.f10929b.m9823b();
                }
            });
            Intent intent = new Intent();
            Bundle bundle = new Bundle();
            new a(new ArrayList(SelectUsersActivity.this.f8878q.m20730n()), ProgressDialog.show(SelectUsersActivity.this.m9813L(), "", SelectUsersActivity.this.getString(R.string.loading), true), intent, bundle).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        }
    }

    /* renamed from: com.cyberlink.you.activity.SelectUsersActivity$c */
    public class ViewOnClickListenerC1701c implements View.OnClickListener {
        public ViewOnClickListenerC1701c() {
        }

        /* renamed from: b */
        public static /* synthetic */ void m9827b(ProgressDialog progressDialog) {
            if (progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            final ProgressDialog progressDialogShow = ProgressDialog.show(SelectUsersActivity.this.m9813L(), "", SelectUsersActivity.this.getString(R.string.loading), true);
            CLUtility.m16589t1(SelectUsersActivity.this.m9813L());
            SelectUsersActivity.this.f8870i.m17442x(new TokenCompleteTextView.InterfaceC3246b() { // from class: com.cyberlink.you.activity.oe
                @Override // com.cyberlink.you.widgetpool.tokenautocomplete.TokenCompleteTextView.InterfaceC3246b
                public final void onComplete() {
                    SelectUsersActivity.ViewOnClickListenerC1701c.m9827b(progressDialogShow);
                }
            });
            SelectUsersActivity.this.f8878q.m20718a();
            SelectUsersActivity.this.f8867f.clear();
            SelectUsersActivity.this.m9810H();
            SelectUsersActivity.this.f8876o.notifyDataSetChanged();
            SelectUsersActivity.this.m9820c0();
            SelectUsersActivity.this.m9821d0();
        }
    }

    /* renamed from: com.cyberlink.you.activity.SelectUsersActivity$d */
    public class ViewOnClickListenerC1702d implements View.OnClickListener {
        public ViewOnClickListenerC1702d() {
        }

        /* renamed from: b */
        public static /* synthetic */ void m9829b(ProgressDialog progressDialog) {
            if (progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            CLUtility.m16589t1(SelectUsersActivity.this.m9813L());
            final ProgressDialog progressDialogShow = ProgressDialog.show(SelectUsersActivity.this.m9813L(), "", SelectUsersActivity.this.getString(R.string.loading), true);
            ArrayList arrayList = new ArrayList();
            for (Friend friend : SelectUsersActivity.this.f8866e) {
                if (SelectUsersActivity.this.f8864c == null || !SelectUsersActivity.this.f8864c.contains(friend)) {
                    if (!SelectUsersActivity.this.f8867f.contains(friend)) {
                        SelectUsersActivity.this.f8878q.m20726j(friend.f13645c, Boolean.TRUE);
                        SelectUsersActivity.m9802q(SelectUsersActivity.this);
                        SelectUsersActivity.this.f8867f.add(friend);
                        arrayList.add(friend);
                    }
                }
            }
            SelectUsersActivity.this.f8870i.m17436r(arrayList, new TokenCompleteTextView.InterfaceC3246b() { // from class: com.cyberlink.you.activity.pe
                @Override // com.cyberlink.you.widgetpool.tokenautocomplete.TokenCompleteTextView.InterfaceC3246b
                public final void onComplete() {
                    SelectUsersActivity.ViewOnClickListenerC1702d.m9829b(progressDialogShow);
                }
            });
            SelectUsersActivity.this.f8876o.notifyDataSetChanged();
            SelectUsersActivity.this.m9820c0();
            SelectUsersActivity.this.m9821d0();
        }
    }

    /* renamed from: com.cyberlink.you.activity.SelectUsersActivity$e */
    public class C1703e extends GestureDetector.SimpleOnGestureListener {
        public C1703e() {
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onSingleTapUp(MotionEvent motionEvent) {
            CLUtility.m16589t1(SelectUsersActivity.this.m9813L());
            return false;
        }
    }

    /* renamed from: com.cyberlink.you.activity.SelectUsersActivity$f */
    public class C1704f implements TokenCompleteTextView.InterfaceC3249e<Friend> {
        public C1704f() {
        }

        @Override // com.cyberlink.you.widgetpool.tokenautocomplete.TokenCompleteTextView.InterfaceC3249e
        /* renamed from: a */
        public void mo5801a(String str) {
            if (SelectUsersActivity.this.f8876o != null) {
                SelectUsersActivity.this.f8876o.getFilter().filter(str);
            }
        }

        @Override // com.cyberlink.you.widgetpool.tokenautocomplete.TokenCompleteTextView.InterfaceC3249e
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public void mo5802b(Friend friend) {
        }

        @Override // com.cyberlink.you.widgetpool.tokenautocomplete.TokenCompleteTextView.InterfaceC3249e
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public void mo5803c(Friend friend) {
            SelectUsersActivity.this.f8878q.m20726j(friend.f13645c, Boolean.FALSE);
            SelectUsersActivity.this.f8867f.remove(friend);
            SelectUsersActivity.this.f8876o.notifyDataSetChanged();
            SelectUsersActivity.m9803r(SelectUsersActivity.this);
            SelectUsersActivity.this.m9820c0();
            SelectUsersActivity.this.m9821d0();
        }
    }

    /* renamed from: com.cyberlink.you.activity.SelectUsersActivity$g */
    public class C1705g extends BaseAdapter implements Filterable {

        /* renamed from: b */
        public Context f8897b;

        /* renamed from: c */
        public List<Friend> f8898c;

        /* renamed from: d */
        public List<Friend> f8899d;

        /* renamed from: e */
        public a f8900e;

        /* renamed from: com.cyberlink.you.activity.SelectUsersActivity$g$a */
        public class a extends Filter {
            public a() {
            }

            @Override // android.widget.Filter
            public Filter.FilterResults performFiltering(CharSequence charSequence) {
                String lowerCase = charSequence.toString().toLowerCase(Locale.getDefault());
                Filter.FilterResults filterResults = new Filter.FilterResults();
                ArrayList arrayList = new ArrayList();
                for (Friend friend : C1705g.this.f8898c) {
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
                C1705g.this.f8899d = (ArrayList) filterResults.values;
                C1705g.this.notifyDataSetChanged();
                if (C1705g.this.f8899d.size() > 0) {
                    SelectUsersActivity.this.f8869h.setVisibility(8);
                } else {
                    SelectUsersActivity.this.f8869h.setVisibility(0);
                }
            }

            public /* synthetic */ a(C1705g c1705g, C1699a c1699a) {
                this();
            }
        }

        public C1705g(Context context, List<Friend> list) {
            this.f8897b = context;
            this.f8898c = list;
            this.f8899d = list;
        }

        @Override // android.widget.Adapter
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public Friend getItem(int i9) {
            return this.f8899d.get(i9);
        }

        @Override // android.widget.Adapter
        public int getCount() {
            return this.f8899d.size();
        }

        @Override // android.widget.Filterable
        public Filter getFilter() {
            if (this.f8900e == null) {
                this.f8900e = new a(this, null);
            }
            return this.f8900e;
        }

        @Override // android.widget.Adapter
        public long getItemId(int i9) {
            return 0L;
        }

        @Override // android.widget.Adapter
        public View getView(int i9, View view, ViewGroup viewGroup) {
            C1706h c1706h;
            boolean z8 = false;
            if (view == null) {
                view = ((LayoutInflater) this.f8897b.getSystemService("layout_inflater")).inflate(R.layout.view_item_firend_group_create, viewGroup, false);
                c1706h = new C1706h(SelectUsersActivity.this, null);
                c1706h.f8904b = (TextView) view.findViewById(R.id.GroupCreateMemberName);
                c1706h.f8903a = (ImageView) view.findViewById(R.id.GroupCreateImageView);
                c1706h.f8905c = (CheckBox) view.findViewById(R.id.GroupCreateMemberCheckBox);
                view.setTag(c1706h);
            } else {
                c1706h = (C1706h) view.getTag();
            }
            Friend friend = this.f8899d.get(i9);
            if (friend.m15621b() != null) {
                c1706h.f8904b.setText(friend.m15621b());
            }
            C6127a.m23469j(SelectUsersActivity.this, c1706h.f8903a, friend);
            c1706h.f8905c.setChecked(SelectUsersActivity.this.m9817Q(friend.f13645c));
            if (SelectUsersActivity.this.f8864c != null && SelectUsersActivity.this.f8864c.contains(friend)) {
                z8 = true;
            }
            c1706h.f8905c.setEnabled(!z8);
            return view;
        }
    }

    /* renamed from: com.cyberlink.you.activity.SelectUsersActivity$h */
    public class C1706h {

        /* renamed from: a */
        public ImageView f8903a;

        /* renamed from: b */
        public TextView f8904b;

        /* renamed from: c */
        public CheckBox f8905c;

        public C1706h() {
        }

        public /* synthetic */ C1706h(SelectUsersActivity selectUsersActivity, C1699a c1699a) {
            this();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: T */
    public /* synthetic */ boolean m9792T(View view, MotionEvent motionEvent) {
        C4619d c4619d = this.f8877p;
        return c4619d != null && c4619d.m18406a(motionEvent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: V */
    public /* synthetic */ boolean m9793V(TextView textView, int i9, KeyEvent keyEvent) {
        CLUtility.m16589t1(m9813L());
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: X */
    public /* synthetic */ void m9794X(View view) {
        m9818Z();
    }

    /* renamed from: Y */
    public static /* synthetic */ void m9795Y(ProgressDialog progressDialog) {
        if (progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    /* renamed from: q */
    public static /* synthetic */ int m9802q(SelectUsersActivity selectUsersActivity) {
        int i9 = selectUsersActivity.f8874m;
        selectUsersActivity.f8874m = i9 + 1;
        return i9;
    }

    /* renamed from: r */
    public static /* synthetic */ int m9803r(SelectUsersActivity selectUsersActivity) {
        int i9 = selectUsersActivity.f8874m;
        selectUsersActivity.f8874m = i9 - 1;
        return i9;
    }

    /* renamed from: H */
    public final void m9810H() {
        List<Friend> list = this.f8865d;
        if (list != null) {
            for (Friend friend : list) {
                List<Friend> list2 = this.f8864c;
                if (list2 != null && list2.contains(friend)) {
                    this.f8878q.m20726j(friend.f13645c, Boolean.TRUE);
                    this.f8867f.add(friend);
                }
            }
        }
    }

    /* renamed from: I */
    public final int m9811I() {
        List<Friend> list = this.f8864c;
        if (list != null) {
            Iterator<Friend> it = list.iterator();
            int i9 = 0;
            while (it.hasNext()) {
                if (this.f8866e.contains(it.next())) {
                    i9++;
                }
            }
            if (i9 != 0) {
                return this.f8866e.size() - i9;
            }
        }
        return this.f8866e.size();
    }

    /* renamed from: J */
    public final void m9812J() {
        List<Friend> list = this.f8864c;
        if (list == null) {
            return;
        }
        Iterator<Friend> it = list.iterator();
        while (it.hasNext()) {
            if (!this.f8866e.contains(it.next())) {
                it.remove();
            }
        }
    }

    /* renamed from: L */
    public final Activity m9813L() {
        return this;
    }

    /* renamed from: N */
    public final void m9814N() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            this.f8875n = extras.getString("INTENT_TITLE", getString(R.string.select_friends_title));
            this.f8866e = Globals.C1408b.m7655b().m7656c("INTENT_USER_LIST");
            this.f8865d = Globals.C1408b.m7655b().m7656c("INTENT_PREV_SELECTED_USERS_LIST");
            List<Friend> listM7656c = Globals.C1408b.m7655b().m7656c("INTENT_CANNOT_MODIFIED_USERS_LIST");
            this.f8864c = listM7656c;
            if (listM7656c == null) {
                this.f8864c = new ArrayList();
            }
            m9812J();
            this.f8880s = extras.getString("page", "");
        }
    }

    /* renamed from: O */
    public final void m9815O() {
        if (this.f8866e == null) {
            List<Friend> listM15026r = C2950b0.m14899A().m15026r();
            Collections.sort(listM15026r, new Friend.C3041b());
            this.f8866e = listM15026r;
        } else {
            long jLongValue = Globals.m7388i0().m7568k1().longValue();
            Friend friend = new Friend();
            friend.f13645c = jLongValue;
            this.f8866e.remove(friend);
            this.f8865d.remove(friend);
            this.f8864c.remove(friend);
        }
        List<Friend> list = this.f8865d;
        if (list != null && list.size() > 0) {
            for (Friend friend2 : this.f8865d) {
                List<Friend> list2 = this.f8864c;
                if (list2 == null || !list2.contains(friend2)) {
                    this.f8874m++;
                    this.f8878q.m20726j(friend2.f13645c, Boolean.TRUE);
                    this.f8870i.m17434p(friend2);
                    this.f8867f.add(friend2);
                }
            }
            this.f8879r = true;
        }
        if (!this.f8879r) {
            m9819b0();
        }
        m9820c0();
        m9821d0();
        C1705g c1705g = new C1705g(m9813L(), this.f8866e);
        this.f8876o = c1705g;
        this.f8868g.setAdapter((ListAdapter) c1705g);
        this.f8868g.setOnItemClickListener(this.f8882u);
        this.f8868g.setOnTouchListener(new View.OnTouchListener() { // from class: com.cyberlink.you.activity.ke
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                return this.f10828b.m9792T(view, motionEvent);
            }
        });
        this.f8869h.setVisibility(this.f8866e.size() > 0 ? 8 : 0);
    }

    /* renamed from: P */
    public final void m9816P() {
        ((TextView) findViewById(R.id.textViewTitle)).setText(this.f8875n);
        PeopleCompleteView peopleCompleteView = (PeopleCompleteView) findViewById(R.id.token_complete);
        this.f8870i = peopleCompleteView;
        peopleCompleteView.setTokenListener(new C1704f());
        this.f8870i.setPrefix(getString(R.string.group_add_member_search_hint));
        this.f8870i.setOnEditorActionListener(new TextView.OnEditorActionListener() { // from class: com.cyberlink.you.activity.je
            @Override // android.widget.TextView.OnEditorActionListener
            public final boolean onEditorAction(TextView textView, int i9, KeyEvent keyEvent) {
                return this.f10788b.m9793V(textView, i9, keyEvent);
            }
        });
        findViewById(R.id.barLayout).setVisibility(0);
        this.f8868g = (ListView) findViewById(R.id.ChatAddMemberListView);
        findViewById(R.id.ChatAddMemberBackBtn).setOnClickListener(this.f8881t);
        Button button = (Button) findViewById(R.id.buttonDone);
        this.f8871j = button;
        button.setOnClickListener(this.f8883v);
        Button button2 = (Button) findViewById(R.id.selectAllBtn);
        this.f8873l = button2;
        button2.setOnClickListener(this.f8885x);
        Button button3 = (Button) findViewById(R.id.deselectBtn);
        this.f8872k = button3;
        button3.setOnClickListener(this.f8884w);
        this.f8869h = findViewById(R.id.ChatAddMemberSearchEmptyView);
    }

    /* renamed from: Q */
    public final boolean m9817Q(long j9) {
        Boolean boolM20722e = this.f8878q.m20722e(j9);
        return boolM20722e != null && boolM20722e.booleanValue();
    }

    /* renamed from: Z */
    public final void m9818Z() {
        CLUtility.m16589t1(m9813L());
        setResult(0, new Intent());
        finish();
    }

    /* renamed from: b0 */
    public void m9819b0() {
        final ProgressDialog progressDialogShow = ProgressDialog.show(m9813L(), "", getString(R.string.loading), true);
        ArrayList arrayList = new ArrayList();
        for (Friend friend : this.f8866e) {
            this.f8878q.m20726j(friend.f13645c, Boolean.TRUE);
            List<Friend> list = this.f8864c;
            if (list == null || !list.contains(friend)) {
                this.f8874m++;
                arrayList.add(friend);
            }
            this.f8867f.add(friend);
        }
        this.f8870i.m17436r(arrayList, new TokenCompleteTextView.InterfaceC3246b() { // from class: com.cyberlink.you.activity.me
            @Override // com.cyberlink.you.widgetpool.tokenautocomplete.TokenCompleteTextView.InterfaceC3246b
            public final void onComplete() {
                SelectUsersActivity.m9795Y(progressDialogShow);
            }
        });
    }

    /* renamed from: c0 */
    public final void m9820c0() {
        if (this.f8874m > 0) {
            this.f8871j.setText(getString(R.string.done_btn) + " (" + this.f8874m + ")");
            this.f8871j.setEnabled(true);
        } else if ("scheduleMeetingPage".equals(this.f8880s)) {
            this.f8871j.setText(R.string.done_btn);
            this.f8871j.setEnabled(true);
        } else {
            this.f8871j.setText(R.string.done_btn);
            this.f8871j.setEnabled(false);
        }
        C5179r0.m20247b(this.f8871j, 1);
    }

    /* renamed from: d0 */
    public final void m9821d0() {
        List<Friend> list;
        if (this.f8866e != null && (list = this.f8864c) != null && list.size() == this.f8866e.size()) {
            this.f8873l.setVisibility(0);
            this.f8872k.setVisibility(8);
            this.f8873l.setEnabled(false);
            this.f8872k.setEnabled(false);
            return;
        }
        if (this.f8866e == null || this.f8874m != m9811I()) {
            this.f8873l.setVisibility(0);
            this.f8872k.setVisibility(8);
            this.f8873l.setEnabled(true);
            this.f8872k.setEnabled(false);
            return;
        }
        this.f8873l.setVisibility(8);
        this.f8872k.setVisibility(0);
        this.f8873l.setEnabled(false);
        this.f8872k.setEnabled(true);
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        m9818Z();
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_chat_add_member);
        this.f8877p = new C4619d(this, new C1703e());
        this.f8867f = new ArrayList(this.f8878q.m20730n());
        m9814N();
        m9816P();
        m9815O();
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onDestroy() {
        this.f8870i.setTokenListener(null);
        this.f8868g.setOnTouchListener(null);
        super.onDestroy();
    }

    @Override // android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
    }
}
