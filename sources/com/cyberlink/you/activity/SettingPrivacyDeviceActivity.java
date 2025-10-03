package com.cyberlink.you.activity;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.cyberlink.p030U.R;
import com.cyberlink.you.BaseActivity;
import com.cyberlink.you.Globals;
import com.cyberlink.you.friends.FriendsClient;
import com.cyberlink.you.p036ui.DialogC3133q;
import com.cyberlink.you.utility.CLUtility;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import p116k4.AbstractC5146g0;
import p116k4.C5170o0;
import p116k4.C5172p;
import p116k4.C5187v0;
import p201t3.C6301o;
import p209u2.C6383t;

/* loaded from: classes.dex */
public class SettingPrivacyDeviceActivity extends BaseActivity {

    /* renamed from: d */
    public C1731c f9007d;

    /* renamed from: e */
    public FriendsClient f9008e;

    /* renamed from: c */
    public final String f9006c = "SettingPrivacyDeviceActivity";

    /* renamed from: f */
    public AsyncTask<Void, Void, List<C1732d>> f9009f = null;

    /* renamed from: g */
    public AsyncTask<Void, Void, Boolean> f9010g = null;

    /* renamed from: h */
    public C1731c.a f9011h = new C1730b();

    /* renamed from: i */
    public View.OnClickListener f9012i = new View.OnClickListener() { // from class: com.cyberlink.you.activity.nf
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f10930b.m9948s(view);
        }
    };

    /* renamed from: com.cyberlink.you.activity.SettingPrivacyDeviceActivity$a */
    public class AsyncTaskC1729a extends AsyncTask<Void, Void, List<C1732d>> {
        public AsyncTaskC1729a() {
        }

        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public List<C1732d> doInBackground(Void... voidArr) throws JSONException {
            ArrayList arrayList = new ArrayList();
            arrayList.add(new C6301o("token", Globals.m7388i0().m7449L()));
            Pair<String, String> pairM15731j = SettingPrivacyDeviceActivity.this.f9008e.m15731j("user", "listSession", arrayList);
            if (!"200".equals(pairM15731j.first)) {
                return null;
            }
            ArrayList arrayList2 = new ArrayList();
            JSONArray jSONArrayM20196r = C5172p.m20196r((String) pairM15731j.second);
            if (jSONArrayM20196r != null) {
                for (int i9 = 0; i9 < jSONArrayM20196r.length(); i9++) {
                    try {
                        JSONObject jSONObject = jSONArrayM20196r.getJSONObject(i9);
                        arrayList2.add(SettingPrivacyDeviceActivity.this.new C1732d(jSONObject.getLong("sessionId"), jSONObject.getString("version"), jSONObject.getString("platform"), jSONObject.getString("uuid"), jSONObject.getString("deviceName")));
                    } catch (JSONException e9) {
                        e9.printStackTrace();
                    }
                }
            }
            return arrayList2;
        }

        @Override // android.os.AsyncTask
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(List<C1732d> list) {
            if (list != null && list.size() > 0) {
                SettingPrivacyDeviceActivity.this.f9007d.addAll(list);
                SettingPrivacyDeviceActivity.this.f9007d.notifyDataSetChanged();
            }
            SettingPrivacyDeviceActivity.this.f9009f = null;
        }
    }

    /* renamed from: com.cyberlink.you.activity.SettingPrivacyDeviceActivity$b */
    public class C1730b implements C1731c.a {

        /* renamed from: com.cyberlink.you.activity.SettingPrivacyDeviceActivity$b$a */
        public class a extends AsyncTask<Void, Void, Boolean> {

            /* renamed from: a */
            public final /* synthetic */ C1732d f9015a;

            /* renamed from: b */
            public final /* synthetic */ DialogC3133q f9016b;

            public a(C1732d c1732d, DialogC3133q dialogC3133q) {
                this.f9015a = c1732d;
                this.f9016b = dialogC3133q;
            }

            @Override // android.os.AsyncTask
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public Boolean doInBackground(Void... voidArr) {
                ArrayList arrayList = new ArrayList();
                arrayList.add(new C6301o("token", Globals.m7388i0().m7449L()));
                arrayList.add(new C6301o("sessionId", Long.toString(this.f9015a.f9024a)));
                return Boolean.valueOf("200".equals(SettingPrivacyDeviceActivity.this.f9008e.m15731j("user", "logoutSession", arrayList).first));
            }

            @Override // android.os.AsyncTask
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public void onPostExecute(Boolean bool) {
                this.f9016b.dismiss();
                if (bool.booleanValue()) {
                    SettingPrivacyDeviceActivity.this.f9007d.remove(this.f9015a);
                    SettingPrivacyDeviceActivity.this.f9007d.notifyDataSetChanged();
                } else {
                    C5187v0.m20267c(R.string.sign_out_unsuccessful);
                }
                SettingPrivacyDeviceActivity.this.f9010g = null;
            }
        }

        public C1730b() {
        }

        @Override // com.cyberlink.you.activity.SettingPrivacyDeviceActivity.C1731c.a
        /* renamed from: a */
        public void mo9954a(int i9) {
            C1732d c1732d = (C1732d) SettingPrivacyDeviceActivity.this.f9007d.getItem(i9);
            if (c1732d == null || SettingPrivacyDeviceActivity.this.f9010g != null) {
                return;
            }
            DialogC3133q dialogC3133qM16411b = new DialogC3133q.b(SettingPrivacyDeviceActivity.this).m16411b();
            SettingPrivacyDeviceActivity.this.f9010g = new a(c1732d, dialogC3133qM16411b);
            SettingPrivacyDeviceActivity.this.f9010g.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        }
    }

    /* renamed from: com.cyberlink.you.activity.SettingPrivacyDeviceActivity$c */
    public static class C1731c extends ArrayAdapter<C1732d> {

        /* renamed from: b */
        public LayoutInflater f9018b;

        /* renamed from: c */
        public a f9019c;

        /* renamed from: com.cyberlink.you.activity.SettingPrivacyDeviceActivity$c$a */
        public interface a {
            /* renamed from: a */
            void mo9954a(int i9);
        }

        /* renamed from: com.cyberlink.you.activity.SettingPrivacyDeviceActivity$c$b */
        public class b {

            /* renamed from: a */
            public TextView f9020a;

            /* renamed from: b */
            public TextView f9021b;

            /* renamed from: c */
            public TextView f9022c;

            public b() {
            }

            public /* synthetic */ b(C1731c c1731c, AsyncTaskC1729a asyncTaskC1729a) {
                this();
            }
        }

        public C1731c(Context context, int i9, a aVar) {
            super(context, i9);
            this.f9019c = aVar;
            this.f9018b = (LayoutInflater) context.getSystemService("layout_inflater");
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: c */
        public /* synthetic */ void m9958c(int i9, View view) {
            a aVar = this.f9019c;
            if (aVar != null) {
                aVar.mo9954a(i9);
            }
        }

        /* renamed from: b */
        public final boolean m9959b(String str) {
            String strM20043a = AbstractC5146g0.m20043a(Globals.m7388i0());
            boolean zM16445H1 = CLUtility.m16445H1(Globals.m7388i0().getApplicationContext());
            if (Globals.m7388i0().m7569k2() && zM16445H1) {
                strM20043a = "A-1-" + C6383t.m24519h(AbstractC5146g0.m20043a(Globals.m7388i0()).getBytes());
            }
            return str.equalsIgnoreCase(strM20043a);
        }

        @Override // android.widget.ArrayAdapter, android.widget.Adapter
        public View getView(final int i9, View view, ViewGroup viewGroup) {
            b bVar;
            if (view == null) {
                view = this.f9018b.inflate(R.layout.view_item_privacy_device_item, (ViewGroup) null);
                bVar = new b(this, null);
                bVar.f9020a = (TextView) view.findViewById(R.id.deviceName);
                bVar.f9021b = (TextView) view.findViewById(R.id.deviceInfo);
                bVar.f9022c = (TextView) view.findViewById(R.id.signOut);
                view.setTag(bVar);
            } else {
                bVar = (b) view.getTag();
            }
            C1732d c1732d = (C1732d) getItem(i9);
            if (c1732d != null) {
                if (C5170o0.m20170e(c1732d.f9028e)) {
                    bVar.f9020a.setText(c1732d.f9026c);
                } else {
                    bVar.f9020a.setText(c1732d.f9028e);
                }
                bVar.f9021b.setText(c1732d.f9026c);
                bVar.f9022c.setVisibility(m9959b(c1732d.f9027d) ? 8 : 0);
                bVar.f9022c.setOnClickListener(new View.OnClickListener() { // from class: com.cyberlink.you.activity.of
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view2) {
                        this.f11014b.m9958c(i9, view2);
                    }
                });
            }
            return view;
        }
    }

    /* renamed from: com.cyberlink.you.activity.SettingPrivacyDeviceActivity$d */
    public class C1732d {

        /* renamed from: a */
        public long f9024a;

        /* renamed from: b */
        public String f9025b;

        /* renamed from: c */
        public String f9026c;

        /* renamed from: d */
        public String f9027d;

        /* renamed from: e */
        public String f9028e;

        public C1732d(long j9, String str, String str2, String str3, String str4) {
            this.f9024a = j9;
            this.f9025b = str;
            this.f9026c = str2;
            this.f9027d = str3;
            this.f9028e = str4;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: s */
    public /* synthetic */ void m9948s(View view) {
        m9951u();
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        m9951u();
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_setting_privacy_device);
        m9950r();
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onDestroy() {
        FriendsClient friendsClient = this.f9008e;
        if (friendsClient != null) {
            friendsClient.m15717U0();
        }
        super.onDestroy();
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        m9950r();
    }

    /* renamed from: q */
    public final Activity m9949q() {
        return this;
    }

    /* renamed from: r */
    public final void m9950r() {
        findViewById(R.id.backBtn).setOnClickListener(this.f9012i);
        this.f9008e = new FriendsClient(true);
        this.f9007d = new C1731c(m9949q(), R.layout.view_item_privacy_device_item, this.f9011h);
        ((ListView) findViewById(R.id.devicesListView)).setAdapter((ListAdapter) this.f9007d);
        if (this.f9009f == null) {
            AsyncTaskC1729a asyncTaskC1729a = new AsyncTaskC1729a();
            this.f9009f = asyncTaskC1729a;
            asyncTaskC1729a.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        }
    }

    /* renamed from: u */
    public final void m9951u() {
        finish();
    }
}
