package com.cyberlink.you.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import com.cyberlink.p030U.R;
import com.cyberlink.you.BaseActivity;
import com.cyberlink.you.Globals;
import com.cyberlink.you.friends.FriendsClient;
import com.cyberlink.you.utility.CLUtility;
import com.google.firebase.messaging.Constants;
import java.util.ArrayList;
import p116k4.C5179r0;
import p201t3.C6301o;
import p209u2.C6385v;

/* loaded from: classes.dex */
public class RecallMessageAlertActivity extends BaseActivity {

    /* renamed from: d */
    public FriendsClient f8605d;

    /* renamed from: e */
    public boolean[] f8606e;

    /* renamed from: f */
    public String[] f8607f;

    /* renamed from: g */
    public String f8608g;

    /* renamed from: h */
    public AsyncTask<Void, Void, Boolean> f8609h;

    /* renamed from: k */
    public boolean f8612k;

    /* renamed from: c */
    public String f8604c = "RecallMessageAlertActivity";

    /* renamed from: i */
    public View.OnClickListener f8610i = new ViewOnClickListenerC1646a();

    /* renamed from: j */
    public View.OnClickListener f8611j = new ViewOnClickListenerC1647b();

    /* renamed from: com.cyberlink.you.activity.RecallMessageAlertActivity$a */
    public class ViewOnClickListenerC1646a implements View.OnClickListener {
        public ViewOnClickListenerC1646a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            RecallMessageAlertActivity.this.finish();
        }
    }

    /* renamed from: com.cyberlink.you.activity.RecallMessageAlertActivity$b */
    public class ViewOnClickListenerC1647b implements View.OnClickListener {
        public ViewOnClickListenerC1647b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            RecallMessageAlertActivity.this.m9510u();
        }
    }

    /* renamed from: com.cyberlink.you.activity.RecallMessageAlertActivity$c */
    public class AsyncTaskC1648c extends AsyncTask<Void, Void, Boolean> {

        /* renamed from: a */
        public final /* synthetic */ ProgressDialog f8615a;

        public AsyncTaskC1648c(ProgressDialog progressDialog) {
            this.f8615a = progressDialog;
        }

        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public Boolean doInBackground(Void... voidArr) {
            Thread.currentThread().setName("recallMessage AsyncTask");
            ArrayList arrayList = null;
            boolean z8 = false;
            ArrayList arrayList2 = null;
            for (int i9 = 0; i9 < RecallMessageAlertActivity.this.f8607f.length; i9++) {
                if (RecallMessageAlertActivity.this.f8606e[i9]) {
                    if (arrayList == null) {
                        arrayList = new ArrayList();
                    }
                    arrayList.add(new C6301o(Constants.FirelogAnalytics.PARAM_MESSAGE_ID, RecallMessageAlertActivity.this.f8607f[i9]));
                } else {
                    if (arrayList2 == null) {
                        arrayList2 = new ArrayList();
                    }
                    arrayList2.add(new C6301o(Constants.FirelogAnalytics.PARAM_MESSAGE_ID, RecallMessageAlertActivity.this.f8607f[i9]));
                }
            }
            if (arrayList != null) {
                arrayList.add(new C6301o("token", Globals.m7388i0().m7449L()));
                String str = (String) RecallMessageAlertActivity.this.f8605d.m15731j("chat", "cancelScheduleSend", arrayList).first;
                if (str != null && (str.equals("200") || str.equals("400"))) {
                    CLUtility.m16594u2(RecallMessageAlertActivity.this.f8608g);
                    z8 = true;
                }
            } else {
                z8 = true;
            }
            if (arrayList2 != null) {
                arrayList2.add(new C6301o("token", Globals.m7388i0().m7449L()));
                String str2 = (String) RecallMessageAlertActivity.this.f8605d.m15731j("chat", "recallMessage", arrayList2).first;
                if (str2 == null || !str2.equals("200")) {
                    z8 &= false;
                } else {
                    Log.d(RecallMessageAlertActivity.this.f8604c, "[recallMessage] Success");
                }
            }
            return Boolean.valueOf(z8);
        }

        @Override // android.os.AsyncTask
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(Boolean bool) {
            ProgressDialog progressDialog = this.f8615a;
            if (progressDialog != null && progressDialog.isShowing()) {
                this.f8615a.dismiss();
            }
            RecallMessageAlertActivity.this.f8612k = false;
            if (bool.booleanValue()) {
                Intent intent = new Intent();
                intent.putExtra("message", RecallMessageAlertActivity.this.f8607f);
                RecallMessageAlertActivity.this.setResult(-1, intent);
            }
            RecallMessageAlertActivity.this.finish();
        }
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        boolean[] zArr;
        super.onCreate(bundle);
        setContentView(R.layout.activity_recall_message_alert);
        Intent intent = getIntent();
        this.f8607f = intent.getStringArrayExtra("message");
        this.f8606e = intent.getBooleanArrayExtra("isScheduleSend");
        this.f8608g = intent.getStringExtra("chatId");
        String[] strArr = this.f8607f;
        if (strArr == null || (zArr = this.f8606e) == null || strArr.length != zArr.length) {
            finish();
        }
        this.f8605d = new FriendsClient(true);
        m9509s();
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        AsyncTask<Void, Void, Boolean> asyncTask = this.f8609h;
        if (asyncTask != null) {
            asyncTask.cancel(false);
        }
    }

    /* renamed from: r */
    public final Activity m9508r() {
        return this;
    }

    /* renamed from: s */
    public final void m9509s() {
        findViewById(R.id.RecallMessageAlertBackBtn).setOnClickListener(this.f8610i);
        findViewById(R.id.RecallMessageAlertCancel).setOnClickListener(this.f8610i);
        findViewById(R.id.RecallMessageAlertRecall).setOnClickListener(this.f8611j);
        C5179r0.m20247b((TextView) findViewById(R.id.RecallMessageAlertRecall), 1);
    }

    /* renamed from: u */
    public final void m9510u() {
        ProgressDialog progressDialogShow = ProgressDialog.show(m9508r(), "", getString(R.string.processing), true);
        if (this.f8612k) {
            return;
        }
        this.f8612k = true;
        AsyncTaskC1648c asyncTaskC1648c = new AsyncTaskC1648c(progressDialogShow);
        this.f8609h = asyncTaskC1648c;
        asyncTaskC1648c.executeOnExecutor(C6385v.f21554b, new Void[0]);
    }
}
