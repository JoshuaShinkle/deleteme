package com.cyberlink.you.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import com.cyberlink.p030U.R;
import com.cyberlink.you.BaseActivity;
import com.cyberlink.you.Globals;
import com.cyberlink.you.activity.ulauncher.ULauncherActivity;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import p116k4.C5186v;
import p116k4.C5187v0;
import p116k4.C5192y;
import p116k4.C5194z;
import p209u2.C6383t;
import p209u2.C6385v;

/* loaded from: classes.dex */
public class NoticesActivity extends BaseActivity {

    /* renamed from: d */
    public long f8088d;

    /* renamed from: f */
    public C1560e f8090f;

    /* renamed from: g */
    public ExpandableListView f8091g;

    /* renamed from: h */
    public View f8092h;

    /* renamed from: i */
    public AsyncTaskC1559d f8093i;

    /* renamed from: c */
    public final String f8087c = "NoticesActivity";

    /* renamed from: e */
    public List<C5194z.b> f8089e = new ArrayList();

    /* renamed from: j */
    public C5192y.b f8094j = new C1556a();

    /* renamed from: k */
    public View.OnClickListener f8095k = new ViewOnClickListenerC1557b();

    /* renamed from: l */
    public ExpandableListView.OnGroupClickListener f8096l = new C1558c();

    /* renamed from: com.cyberlink.you.activity.NoticesActivity$a */
    public class C1556a implements C5192y.b {

        /* renamed from: com.cyberlink.you.activity.NoticesActivity$a$a */
        public class a implements Runnable {

            /* renamed from: b */
            public final /* synthetic */ boolean f8098b;

            public a(boolean z8) {
                this.f8098b = z8;
            }

            @Override // java.lang.Runnable
            public void run() {
                if (!this.f8098b) {
                    C5187v0.m20267c(R.string.error_server_response);
                    return;
                }
                NoticesActivity.this.m8767y();
                NoticesActivity.this.f8091g.setVisibility(0);
                NoticesActivity.this.f8092h.setVisibility(8);
            }
        }

        public C1556a() {
        }

        @Override // p116k4.C5192y.b
        /* renamed from: a */
        public void mo8768a(boolean z8) throws JSONException {
            if (z8) {
                NoticesActivity.this.f8088d = Globals.m7388i0().m7597q0();
                C5194z.a aVarM20296f = C5194z.m20296f();
                if (aVarM20296f != null) {
                    Globals.m7388i0().m7580m3(aVarM20296f.m20305a());
                }
            }
            NoticesActivity.this.runOnUiThread(new a(z8));
        }
    }

    /* renamed from: com.cyberlink.you.activity.NoticesActivity$b */
    public class ViewOnClickListenerC1557b implements View.OnClickListener {
        public ViewOnClickListenerC1557b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            NoticesActivity.this.m8766w();
        }
    }

    /* renamed from: com.cyberlink.you.activity.NoticesActivity$c */
    public class C1558c implements ExpandableListView.OnGroupClickListener {

        /* renamed from: com.cyberlink.you.activity.NoticesActivity$c$a */
        public class a implements Runnable {
            public a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                NoticesActivity.this.f8090f.notifyDataSetChanged();
            }
        }

        public C1558c() {
        }

        @Override // android.widget.ExpandableListView.OnGroupClickListener
        public boolean onGroupClick(ExpandableListView expandableListView, View view, int i9, long j9) {
            NoticesActivity.this.runOnUiThread(new a());
            return false;
        }
    }

    /* renamed from: com.cyberlink.you.activity.NoticesActivity$d */
    public class AsyncTaskC1559d extends AsyncTask<Void, Void, C5194z.a> {
        public AsyncTaskC1559d() {
        }

        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public C5194z.a doInBackground(Void... voidArr) {
            if (isCancelled()) {
                return null;
            }
            Log.v("NoticesActivity", "checkNewNotice");
            return C5194z.m20296f();
        }

        /* renamed from: b */
        public final boolean m8770b(long j9) {
            if (NoticesActivity.this.f8090f.getGroupCount() == 0 || NoticesActivity.this.f8089e.isEmpty() || Globals.m7388i0().m7597q0() < j9) {
                return true;
            }
            if (j9 <= ((C5194z.b) NoticesActivity.this.f8089e.get(0)).m20309d()) {
                return false;
            }
            NoticesActivity noticesActivity = NoticesActivity.this;
            noticesActivity.f8088d = ((C5194z.b) noticesActivity.f8089e.get(0)).m20309d();
            Globals.m7388i0().m7580m3(NoticesActivity.this.f8088d);
            return true;
        }

        @Override // android.os.AsyncTask
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public void onCancelled(C5194z.a aVar) {
            Log.v("NoticesActivity", "checkNewNotice cancel");
        }

        @Override // android.os.AsyncTask
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(C5194z.a aVar) {
            if (NoticesActivity.this.m7364e()) {
                Log.v("NoticesActivity", "Closing, checkNewNotice cancel");
                return;
            }
            if (aVar != null && m8770b(aVar.m20305a())) {
                C5192y.m20281f().m20287i();
                if (NoticesActivity.this.f8089e.isEmpty()) {
                    NoticesActivity.this.f8092h.setVisibility(0);
                }
            }
            Log.v("NoticesActivity", "checkNewNotice done");
        }

        public /* synthetic */ AsyncTaskC1559d(NoticesActivity noticesActivity, C1556a c1556a) {
            this();
        }
    }

    /* renamed from: com.cyberlink.you.activity.NoticesActivity$e */
    public class C1560e extends BaseExpandableListAdapter {

        /* renamed from: b */
        public Context f8104b;

        /* renamed from: c */
        public List<C5194z.b> f8105c;

        /* renamed from: d */
        public ExpandableListView f8106d;

        /* renamed from: com.cyberlink.you.activity.NoticesActivity$e$a */
        public class a implements View.OnClickListener {

            /* renamed from: b */
            public final /* synthetic */ int f8108b;

            public a(int i9) {
                this.f8108b = i9;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                C1560e c1560e = C1560e.this;
                NoticesActivity.this.m8764i(c1560e.f8105c.get(this.f8108b).m20307b());
            }
        }

        public C1560e(Context context) {
            this.f8104b = context;
        }

        /* renamed from: a */
        public void m8773a(List<C5194z.b> list) {
            this.f8105c = list;
        }

        /* renamed from: b */
        public void m8774b(ExpandableListView expandableListView) {
            this.f8106d = expandableListView;
        }

        @Override // android.widget.ExpandableListAdapter
        public Object getChild(int i9, int i10) {
            List<C5194z.b> list = this.f8105c;
            if (list != null) {
                return list.get(i9);
            }
            return null;
        }

        @Override // android.widget.ExpandableListAdapter
        public long getChildId(int i9, int i10) {
            return i10;
        }

        @Override // android.widget.ExpandableListAdapter
        public View getChildView(int i9, int i10, boolean z8, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = ((LayoutInflater) this.f8104b.getSystemService("layout_inflater")).inflate(R.layout.view_item_notices_item, viewGroup, false);
            }
            C5194z.b bVar = this.f8105c.get(i9);
            if (bVar != null) {
                TextView textView = (TextView) view.findViewById(R.id.NoticesItemLongDesc);
                TextView textView2 = (TextView) view.findViewById(R.id.NoticesItemLink);
                textView2.setPaintFlags(textView2.getPaintFlags() | 8);
                if (bVar.m20306a() != null) {
                    textView2.setText(bVar.m20306a());
                }
                if (bVar.m20308c() != null) {
                    textView.setText(bVar.m20308c());
                }
                textView2.setOnClickListener(new a(i9));
            } else {
                Log.d("NoticesActivity", "[getChildView] Get notice item null by index " + i9);
            }
            return view;
        }

        @Override // android.widget.ExpandableListAdapter
        public int getChildrenCount(int i9) {
            return this.f8105c.get(i9) != null ? 1 : 0;
        }

        @Override // android.widget.ExpandableListAdapter
        public Object getGroup(int i9) {
            List<C5194z.b> list = this.f8105c;
            if (list != null) {
                return list.get(i9);
            }
            return null;
        }

        @Override // android.widget.ExpandableListAdapter
        public int getGroupCount() {
            return this.f8105c.size();
        }

        @Override // android.widget.ExpandableListAdapter
        public long getGroupId(int i9) {
            return i9;
        }

        @Override // android.widget.ExpandableListAdapter
        public View getGroupView(int i9, boolean z8, View view, ViewGroup viewGroup) {
            C1561f c1561f;
            if (view == null) {
                view = ((LayoutInflater) this.f8104b.getSystemService("layout_inflater")).inflate(R.layout.view_item_notices_title_item, viewGroup, false);
                c1561f = new C1561f(NoticesActivity.this, null);
                c1561f.f8110a = (TextView) view.findViewById(R.id.NoticesItemShortDesc);
                c1561f.f8111b = (TextView) view.findViewById(R.id.NoticesItemDate);
                c1561f.f8112c = (ImageView) view.findViewById(R.id.NoticesItemGroupRightImg);
                c1561f.f8113d = view.findViewById(R.id.NoticesItemNewIconImg);
                view.setTag(c1561f);
            } else {
                c1561f = (C1561f) view.getTag();
            }
            c1561f.f8110a.setText(this.f8105c.get(i9).m20311f());
            c1561f.f8111b.setText(this.f8105c.get(i9).m20310e());
            if (this.f8106d.isGroupExpanded(i9)) {
                c1561f.f8112c.setImageResource(R.drawable.icon_expand_up);
            } else {
                c1561f.f8112c.setImageResource(R.drawable.icon_expand_down);
            }
            if (NoticesActivity.this.f8088d < this.f8105c.get(i9).m20309d()) {
                c1561f.f8113d.setVisibility(0);
            } else {
                c1561f.f8113d.setVisibility(4);
            }
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

    /* renamed from: com.cyberlink.you.activity.NoticesActivity$f */
    public class C1561f {

        /* renamed from: a */
        public TextView f8110a;

        /* renamed from: b */
        public TextView f8111b;

        /* renamed from: c */
        public ImageView f8112c;

        /* renamed from: d */
        public View f8113d;

        public C1561f() {
        }

        public /* synthetic */ C1561f(NoticesActivity noticesActivity, C1556a c1556a) {
            this();
        }
    }

    /* renamed from: i */
    public final void m8764i(String str) {
        Intent intent;
        if (str != null) {
            try {
                if (!str.startsWith("u://")) {
                    Intent intent2 = new Intent("android.intent.action.VIEW");
                    intent2.setData(Uri.parse(str));
                    intent = intent2;
                } else if (!str.contains("stickershop")) {
                    return;
                } else {
                    intent = new Intent(getApplicationContext(), (Class<?>) StickerShopActivity.class);
                }
                startActivity(intent);
            } catch (Exception e9) {
                Log.d("NoticesActivity", "[BrowseTo] e = " + e9.getMessage());
            }
        }
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        m8766w();
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_notices);
        boolean zM20263a = C5186v.m20263a(this);
        this.f8091g = (ExpandableListView) findViewById(R.id.NoticesActivityExpandableListView);
        ((ImageView) findViewById(R.id.NoticesActivityBackBtn)).setOnClickListener(this.f8095k);
        this.f8092h = findViewById(R.id.NoticesActivityLoading);
        if (zM20263a) {
            this.f8091g.setVisibility(8);
            this.f8092h.setVisibility(0);
        }
        this.f8090f = new C1560e(getApplicationContext());
        m8767y();
        this.f8090f.m8774b(this.f8091g);
        this.f8091g.setAdapter(this.f8090f);
        this.f8091g.setOnGroupClickListener(this.f8096l);
        this.f8088d = Globals.m7388i0().m7597q0();
        C5192y.m20281f().m20282c(this.f8094j);
        if (!Globals.m7388i0().m7608s() && !zM20263a) {
            m8765v();
            return;
        }
        C5192y.m20281f().m20287i();
        List<C5194z.b> list = this.f8089e;
        if (list != null && list.size() <= 0) {
            this.f8092h.setVisibility(0);
        }
        Globals.m7388i0().m7416D3(false);
    }

    /* renamed from: v */
    public final void m8765v() {
        AsyncTaskC1559d asyncTaskC1559d = new AsyncTaskC1559d(this, null);
        this.f8093i = asyncTaskC1559d;
        asyncTaskC1559d.executeOnExecutor(C6385v.f21554b, new Void[0]);
    }

    /* renamed from: w */
    public final void m8766w() {
        C5192y.m20281f().m20286h(this.f8094j);
        AsyncTaskC1559d asyncTaskC1559d = this.f8093i;
        if (asyncTaskC1559d != null && asyncTaskC1559d.getStatus() != AsyncTask.Status.FINISHED) {
            this.f8093i.cancel(true);
        }
        if (getCallingActivity() == null) {
            Intent intent = new Intent();
            if (C6383t.m24517f(Globals.m7388i0().m7449L())) {
                intent.setClass(getApplicationContext(), RegisterActivity.class);
            } else {
                intent.setClass(getApplicationContext(), ULauncherActivity.class);
                intent.putExtra("Tab_Index", 4);
            }
            intent.setFlags(268468224);
            startActivity(intent);
        }
        finish();
    }

    /* renamed from: y */
    public final void m8767y() {
        List<C5194z.b> listM20295e = C5194z.m20295e();
        this.f8089e = listM20295e;
        if (listM20295e == null) {
            this.f8089e = new ArrayList();
        }
        this.f8090f.m8773a(this.f8089e);
        this.f8090f.notifyDataSetChanged();
    }
}
