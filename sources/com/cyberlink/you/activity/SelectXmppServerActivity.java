package com.cyberlink.you.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.cyberlink.p030U.R;
import com.cyberlink.you.BaseActivity;
import com.cyberlink.you.chat.XMPPManager;
import java.util.Iterator;

/* loaded from: classes.dex */
public class SelectXmppServerActivity extends BaseActivity {

    /* renamed from: c */
    public ListView f8907c;

    /* renamed from: d */
    public ArrayAdapter<String> f8908d;

    /* renamed from: e */
    public String f8909e;

    /* renamed from: f */
    public XMPPManager.InterfaceC2869t f8910f = new C1707a();

    /* renamed from: com.cyberlink.you.activity.SelectXmppServerActivity$a */
    public class C1707a implements XMPPManager.InterfaceC2869t {

        /* renamed from: com.cyberlink.you.activity.SelectXmppServerActivity$a$a */
        public class a implements Runnable {

            /* renamed from: b */
            public final /* synthetic */ String f8912b;

            public a(String str) {
                this.f8912b = str;
            }

            @Override // java.lang.Runnable
            public void run() {
                Toast.makeText(SelectXmppServerActivity.this.getBaseContext(), this.f8912b, 0).show();
            }
        }

        public C1707a() {
        }

        @Override // com.cyberlink.you.chat.XMPPManager.InterfaceC2869t
        /* renamed from: a */
        public void mo9838a(String str) {
            SelectXmppServerActivity.this.f8907c.post(new a(str));
        }
    }

    /* renamed from: com.cyberlink.you.activity.SelectXmppServerActivity$b */
    public class C1708b extends ArrayAdapter<String> {
        public C1708b(Context context, int i9) {
            super(context, i9);
        }

        @Override // android.widget.ArrayAdapter, android.widget.Adapter
        public View getView(int i9, View view, ViewGroup viewGroup) {
            C1711e c1711e;
            TextView textView;
            if (view == null) {
                view = ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(R.layout.view_item_text, viewGroup, false);
                c1711e = new C1711e(SelectXmppServerActivity.this, null);
                c1711e.f8917a = (TextView) view.findViewById(R.id.textView1);
                view.setTag(c1711e);
            } else {
                c1711e = (C1711e) view.getTag();
            }
            if (c1711e != null && (textView = c1711e.f8917a) != null) {
                textView.setText((CharSequence) getItem(i9));
                if (SelectXmppServerActivity.this.f8909e == null || !c1711e.f8917a.getText().toString().toLowerCase().equals(SelectXmppServerActivity.this.f8909e.toLowerCase())) {
                    c1711e.f8917a.setTextColor(SelectXmppServerActivity.this.getResources().getColor(R.color.you_color_normal_gray_text));
                } else {
                    c1711e.f8917a.setTextColor(SelectXmppServerActivity.this.getResources().getColor(R.color.you_color_normal_green));
                }
            }
            return view;
        }
    }

    /* renamed from: com.cyberlink.you.activity.SelectXmppServerActivity$c */
    public class C1709c implements AdapterView.OnItemClickListener {
        public C1709c() {
        }

        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView<?> adapterView, View view, int i9, long j9) {
            XMPPManager.m14184g0().m14243g1(i9);
            XMPPManager.m14184g0().m14223U();
            SelectXmppServerActivity.this.f8909e = XMPPManager.m14184g0().m14240e0();
            SelectXmppServerActivity.this.f8908d.notifyDataSetChanged();
        }
    }

    /* renamed from: com.cyberlink.you.activity.SelectXmppServerActivity$d */
    public class ViewOnClickListenerC1710d implements View.OnClickListener {
        public ViewOnClickListenerC1710d() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            XMPPManager.m14184g0().m14243g1(-1);
            XMPPManager.m14184g0().m14223U();
            SelectXmppServerActivity.this.f8909e = XMPPManager.m14184g0().m14240e0();
            SelectXmppServerActivity.this.f8908d.notifyDataSetChanged();
        }
    }

    /* renamed from: com.cyberlink.you.activity.SelectXmppServerActivity$e */
    public class C1711e {

        /* renamed from: a */
        public TextView f8917a;

        public C1711e() {
        }

        public /* synthetic */ C1711e(SelectXmppServerActivity selectXmppServerActivity, C1707a c1707a) {
            this();
        }
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_select_xmpp_server);
        this.f8909e = XMPPManager.m14184g0().m14240e0();
        this.f8908d = new C1708b(this, R.layout.view_item_text);
        ListView listView = (ListView) findViewById(R.id.listViewXmppServer);
        this.f8907c = listView;
        listView.setAdapter((ListAdapter) this.f8908d);
        Iterator<String> it = XMPPManager.m14184g0().m14249j0().iterator();
        while (it.hasNext()) {
            this.f8908d.add(it.next());
        }
        this.f8907c.setOnItemClickListener(new C1709c());
        findViewById(R.id.buttonDisconnect).setOnClickListener(new ViewOnClickListenerC1710d());
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onStart() {
        XMPPManager.m14184g0().m14208I(this.f8910f);
        super.onStart();
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onStop() {
        XMPPManager.m14184g0().m14218R();
        super.onStop();
    }
}
