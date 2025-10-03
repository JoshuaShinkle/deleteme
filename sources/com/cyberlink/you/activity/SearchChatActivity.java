package com.cyberlink.you.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.cyberlink.p030U.R;
import com.cyberlink.you.BaseActivity;
import com.cyberlink.you.chat.C2899i0;
import com.cyberlink.you.database.C2950b0;
import com.cyberlink.you.database.MessageObj;
import com.cyberlink.you.friends.Group;
import com.cyberlink.you.utility.CLUtility;
import com.google.firebase.iid.ServiceStarter;
import java.util.ArrayList;
import java.util.List;
import p116k4.C5170o0;

/* loaded from: classes.dex */
public class SearchChatActivity extends BaseActivity {

    /* renamed from: r */
    public static Group f8773r;

    /* renamed from: d */
    public ImageView f8775d;

    /* renamed from: e */
    public C2899i0 f8776e;

    /* renamed from: f */
    public ListView f8777f;

    /* renamed from: g */
    public EditText f8778g;

    /* renamed from: h */
    public ProgressBar f8779h;

    /* renamed from: i */
    public ImageView f8780i;

    /* renamed from: j */
    public String f8781j;

    /* renamed from: c */
    public final String f8774c = "SearchChatActivity";

    /* renamed from: k */
    public Handler f8782k = new Handler();

    /* renamed from: l */
    public int f8783l = ServiceStarter.ERROR_UNKNOWN;

    /* renamed from: m */
    public TextWatcher f8784m = new C1682a();

    /* renamed from: n */
    public View.OnClickListener f8785n = new ViewOnClickListenerC1683b();

    /* renamed from: o */
    public final Runnable f8786o = new RunnableC1684c();

    /* renamed from: p */
    public AdapterView.OnItemClickListener f8787p = new C1686e();

    /* renamed from: q */
    public View.OnClickListener f8788q = new ViewOnClickListenerC1687f();

    /* renamed from: com.cyberlink.you.activity.SearchChatActivity$a */
    public class C1682a implements TextWatcher {
        public C1682a() {
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i9, int i10, int i11) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i9, int i10, int i11) {
            SearchChatActivity.this.f8781j = charSequence.toString();
            if (C5170o0.m20170e(SearchChatActivity.this.f8781j)) {
                SearchChatActivity.this.m9690B(8);
                SearchChatActivity.this.f8780i.setVisibility(4);
                if (SearchChatActivity.this.f8782k != null) {
                    SearchChatActivity.this.f8782k.removeCallbacks(SearchChatActivity.this.f8786o);
                }
                SearchChatActivity.this.f8776e.clear();
                SearchChatActivity.this.f8776e.notifyDataSetChanged();
                return;
            }
            SearchChatActivity.this.m9690B(0);
            SearchChatActivity.this.f8780i.setVisibility(0);
            if (SearchChatActivity.this.f8782k != null) {
                SearchChatActivity.this.f8782k.removeCallbacks(SearchChatActivity.this.f8786o);
                SearchChatActivity.this.f8782k.postDelayed(SearchChatActivity.this.f8786o, SearchChatActivity.this.f8783l);
            }
        }
    }

    /* renamed from: com.cyberlink.you.activity.SearchChatActivity$b */
    public class ViewOnClickListenerC1683b implements View.OnClickListener {
        public ViewOnClickListenerC1683b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (SearchChatActivity.this.f8778g != null) {
                SearchChatActivity.this.f8778g.setText("");
            }
        }
    }

    /* renamed from: com.cyberlink.you.activity.SearchChatActivity$c */
    public class RunnableC1684c implements Runnable {
        public RunnableC1684c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (C5170o0.m20170e(SearchChatActivity.this.f8781j)) {
                return;
            }
            List<MessageObj> listM15184w = C2950b0.m14916o().m15184w(String.valueOf(SearchChatActivity.f8773r.f13727n), SearchChatActivity.this.f8781j);
            SearchChatActivity.this.m9690B(8);
            if (listM15184w == null || listM15184w.size() == 0) {
                SearchChatActivity searchChatActivity = SearchChatActivity.this;
                Toast.makeText(searchChatActivity, searchChatActivity.getString(R.string.error_search_place_name, searchChatActivity.f8781j), 1).show();
            }
            SearchChatActivity.this.m9692z(listM15184w);
        }
    }

    /* renamed from: com.cyberlink.you.activity.SearchChatActivity$d */
    public class RunnableC1685d implements Runnable {

        /* renamed from: b */
        public final /* synthetic */ List f8792b;

        /* renamed from: c */
        public final /* synthetic */ long f8793c;

        public RunnableC1685d(List list, long j9) {
            this.f8792b = list;
            this.f8793c = j9;
        }

        @Override // java.lang.Runnable
        public void run() {
            SearchChatActivity.this.f8776e.clear();
            if (this.f8792b != null) {
                SearchChatActivity.this.f8776e.addAll(this.f8792b);
                Log.d("SearchChatActivity", "[timer] resetGroupList " + String.valueOf(System.currentTimeMillis() - this.f8793c) + "ms");
            }
            SearchChatActivity.this.f8776e.notifyDataSetChanged();
        }
    }

    /* renamed from: com.cyberlink.you.activity.SearchChatActivity$e */
    public class C1686e implements AdapterView.OnItemClickListener {
        public C1686e() {
        }

        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView<?> adapterView, View view, int i9, long j9) {
            MessageObj messageObj = (MessageObj) adapterView.getItemAtPosition(i9);
            CLUtility.m16602w2(SearchChatActivity.this, false);
            Intent intent = new Intent();
            intent.putExtra("messageID", messageObj.m14777o());
            intent.putExtra("SearchChat", SearchChatActivity.this.f8781j);
            SearchChatActivity.this.setResult(-1, intent);
            SearchChatActivity.this.finish();
        }
    }

    /* renamed from: com.cyberlink.you.activity.SearchChatActivity$f */
    public class ViewOnClickListenerC1687f implements View.OnClickListener {
        public ViewOnClickListenerC1687f() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            SearchChatActivity.this.m9691i();
        }
    }

    /* renamed from: B */
    public final void m9690B(int i9) {
        ProgressBar progressBar = this.f8779h;
        if (progressBar != null) {
            progressBar.setVisibility(i9);
        }
    }

    /* renamed from: i */
    public final void m9691i() {
        CLUtility.m16602w2(this, false);
        setResult(-1, new Intent());
        finish();
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        m9691i();
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_search_chat);
        f8773r = (Group) getIntent().getParcelableExtra("Group");
        this.f8775d = (ImageView) findViewById(R.id.ProfileInfoUpdateBackBtn);
        this.f8778g = (EditText) findViewById(R.id.SearchEditText);
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressbar_searching);
        this.f8779h = progressBar;
        progressBar.setIndeterminate(true);
        ImageView imageView = (ImageView) findViewById(R.id.SearchEditXImage);
        this.f8780i = imageView;
        imageView.setOnClickListener(this.f8785n);
        this.f8777f = (ListView) findViewById(R.id.SearchChatListView);
        C2899i0 c2899i0 = new C2899i0(this, R.layout.view_item_search_chat_item, new ArrayList());
        this.f8776e = c2899i0;
        this.f8777f.setAdapter((ListAdapter) c2899i0);
        this.f8777f.setOnItemClickListener(this.f8787p);
        this.f8775d.setOnClickListener(this.f8788q);
        EditText editText = this.f8778g;
        if (editText != null) {
            editText.addTextChangedListener(this.f8784m);
            this.f8778g.setHint(getResources().getString(R.string.search));
        }
        CLUtility.m16602w2(this, true);
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        this.f8777f.setEmptyView(null);
    }

    /* renamed from: z */
    public final void m9692z(List<MessageObj> list) {
        runOnUiThread(new RunnableC1685d(list, System.currentTimeMillis()));
    }
}
