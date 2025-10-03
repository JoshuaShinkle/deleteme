package com.cyberlink.you.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.TextView;
import com.cyberlink.p030U.R;
import com.cyberlink.you.BaseActivity;
import com.cyberlink.you.Globals;
import com.cyberlink.you.friends.FriendsClient;
import com.cyberlink.you.p036ui.C3123g;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;
import p201t3.C6301o;
import p209u2.C6385v;

/* loaded from: classes.dex */
public class PCLoginActivity extends BaseActivity {

    /* renamed from: c */
    public View f8205c;

    /* renamed from: d */
    public View f8206d;

    /* renamed from: e */
    public TextView f8207e;

    /* renamed from: f */
    public AlertDialog f8208f;

    /* renamed from: g */
    public ProgressDialog f8209g;

    /* renamed from: h */
    public FriendsClient f8210h;

    /* renamed from: i */
    public String f8211i;

    /* renamed from: j */
    public AsyncTask<Void, Void, Pair<String, String>> f8212j;

    /* renamed from: k */
    public View.OnClickListener f8213k = new View.OnClickListener() { // from class: com.cyberlink.you.activity.p7
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f11037b.m8843w(view);
        }
    };

    /* renamed from: l */
    public View.OnClickListener f8214l = new View.OnClickListener() { // from class: com.cyberlink.you.activity.q7
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f11075b.m8844y(view);
        }
    };

    /* renamed from: m */
    public final DialogInterface.OnClickListener f8215m = new DialogInterfaceOnClickListenerC1579a();

    /* renamed from: n */
    public boolean f8216n;

    /* renamed from: com.cyberlink.you.activity.PCLoginActivity$a */
    public class DialogInterfaceOnClickListenerC1579a implements DialogInterface.OnClickListener {
        public DialogInterfaceOnClickListenerC1579a() {
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i9) {
            dialogInterface.dismiss();
            PCLoginActivity.this.finish();
            PCLoginActivity.this.f8208f = null;
        }
    }

    /* renamed from: com.cyberlink.you.activity.PCLoginActivity$b */
    public class AsyncTaskC1580b extends AsyncTask<Void, Void, String> {
        public AsyncTaskC1580b() {
        }

        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public String doInBackground(Void... voidArr) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(new C6301o("token", Globals.m7388i0().m7449L()));
            arrayList.add(new C6301o("loginURL", PCLoginActivity.this.f8211i));
            Pair<String, String> pairM15731j = new FriendsClient().m15731j("user", "loginURLInfo", arrayList);
            if (!"200".equals((String) pairM15731j.first)) {
                return "";
            }
            try {
                return new JSONObject((String) pairM15731j.second).getString("deviceName");
            } catch (JSONException e9) {
                e9.printStackTrace();
                return "";
            }
        }

        @Override // android.os.AsyncTask
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(String str) {
            if (str != null) {
                PCLoginActivity.this.f8207e.setText(str);
            }
        }
    }

    /* renamed from: com.cyberlink.you.activity.PCLoginActivity$c */
    public class AsyncTaskC1581c extends AsyncTask<Void, Void, Pair<String, String>> {
        public AsyncTaskC1581c() {
        }

        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public Pair<String, String> doInBackground(Void... voidArr) {
            Thread.currentThread().setName("PCLoginActivity.loginOnPC AsyncTask");
            ArrayList arrayList = new ArrayList();
            arrayList.add(new C6301o("token", Globals.m7388i0().m7449L()));
            arrayList.add(new C6301o("loginURL", PCLoginActivity.this.f8211i));
            return PCLoginActivity.this.f8210h.m15731j("user", "bindLoginURL", arrayList);
        }

        @Override // android.os.AsyncTask
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(Pair<String, String> pair) {
            Object obj;
            if (PCLoginActivity.this.f8209g != null && PCLoginActivity.this.f8209g.isShowing()) {
                PCLoginActivity.this.f8209g.dismiss();
            }
            PCLoginActivity.this.f8216n = false;
            if (pair == null || (obj = pair.first) == null) {
                if (PCLoginActivity.this.f8205c != null) {
                    PCLoginActivity.this.f8205c.setEnabled(true);
                    return;
                }
                return;
            }
            String str = (String) obj;
            if (str.equals("200")) {
                PCLoginActivity.this.m8845B();
            } else {
                if (!str.equals("404") || PCLoginActivity.this.f8205c == null) {
                    return;
                }
                PCLoginActivity.this.f8205c.setEnabled(true);
            }
        }

        @Override // android.os.AsyncTask
        public void onPreExecute() {
            if (PCLoginActivity.this.f8205c != null) {
                PCLoginActivity.this.f8205c.setEnabled(false);
            }
            PCLoginActivity pCLoginActivity = PCLoginActivity.this;
            pCLoginActivity.f8209g = ProgressDialog.show(pCLoginActivity, "", pCLoginActivity.getString(R.string.loading), true);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: w */
    public /* synthetic */ void m8843w(View view) {
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: y */
    public /* synthetic */ void m8844y(View view) {
        m8846z();
    }

    /* renamed from: B */
    public final void m8845B() {
        if (this.f8208f == null) {
            AlertDialog.Builder builderM16382a = C3123g.m16382a(this);
            builderM16382a.setMessage(getString(R.string.pc_login_success_hint));
            builderM16382a.setPositiveButton(getString(R.string.ok), this.f8215m);
            AlertDialog alertDialogCreate = builderM16382a.create();
            this.f8208f = alertDialogCreate;
            alertDialogCreate.requestWindowFeature(1);
            this.f8208f.show();
        }
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        Uri data;
        super.onCreate(bundle);
        setContentView(R.layout.activity_pc_login);
        View viewFindViewById = findViewById(R.id.loginBtn);
        this.f8205c = viewFindViewById;
        viewFindViewById.setOnClickListener(this.f8214l);
        View viewFindViewById2 = findViewById(R.id.back);
        this.f8206d = viewFindViewById2;
        viewFindViewById2.setOnClickListener(this.f8213k);
        this.f8207e = (TextView) findViewById(R.id.pcLoginDeviceName);
        Intent intent = getIntent();
        if (intent != null) {
            String stringExtra = intent.getStringExtra("pcLoginUrl");
            this.f8211i = stringExtra;
            if (stringExtra == null && (data = intent.getData()) != null && "au".equals(data.getHost())) {
                this.f8211i = data.getQueryParameter("url");
            }
        }
        if (this.f8211i == null) {
            finish();
        }
        this.f8210h = new FriendsClient(true);
        String str = this.f8211i;
        if (str == null || str.isEmpty() || this.f8207e == null) {
            return;
        }
        new AsyncTaskC1580b().executeOnExecutor(C6385v.f21554b, new Void[0]);
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onDestroy() {
        this.f8205c.setOnClickListener(null);
        this.f8206d.setOnClickListener(null);
        this.f8213k = null;
        this.f8214l = null;
        this.f8205c = null;
        this.f8206d = null;
        this.f8208f = null;
        this.f8209g = null;
        FriendsClient friendsClient = this.f8210h;
        if (friendsClient != null) {
            friendsClient.m15717U0();
        }
        AsyncTask<Void, Void, Pair<String, String>> asyncTask = this.f8212j;
        if (asyncTask != null) {
            asyncTask.cancel(false);
        }
        super.onDestroy();
    }

    /* renamed from: z */
    public final void m8846z() {
        if (this.f8216n) {
            return;
        }
        this.f8216n = true;
        AsyncTaskC1581c asyncTaskC1581c = new AsyncTaskC1581c();
        this.f8212j = asyncTaskC1581c;
        asyncTaskC1581c.executeOnExecutor(C6385v.f21554b, new Void[0]);
    }
}
