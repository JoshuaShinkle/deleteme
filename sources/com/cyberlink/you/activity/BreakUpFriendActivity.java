package com.cyberlink.you.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.cyberlink.p030U.R;
import com.cyberlink.you.BaseActivity;
import com.cyberlink.you.Globals;
import com.cyberlink.you.friends.Friend;
import com.cyberlink.you.friends.FriendsClient;
import java.util.ArrayList;
import p173q2.C6127a;
import p201t3.C6301o;
import p209u2.C6385v;

/* loaded from: classes.dex */
public class BreakUpFriendActivity extends BaseActivity {

    /* renamed from: d */
    public Friend f7431d;

    /* renamed from: e */
    public RelativeLayout f7432e;

    /* renamed from: f */
    public RelativeLayout f7433f;

    /* renamed from: g */
    public AsyncTask<Void, Void, Boolean> f7434g;

    /* renamed from: h */
    public boolean f7435h;

    /* renamed from: c */
    public final String f7430c = "BreakUpFriendActivity";

    /* renamed from: i */
    public View.OnClickListener f7436i = new ViewOnClickListenerC1430a();

    /* renamed from: j */
    public View.OnClickListener f7437j = new ViewOnClickListenerC1431b();

    /* renamed from: k */
    public View.OnClickListener f7438k = new ViewOnClickListenerC1432c();

    /* renamed from: l */
    public View.OnClickListener f7439l = new ViewOnClickListenerC1433d();

    public enum Page {
        Alert,
        Confrim
    }

    /* renamed from: com.cyberlink.you.activity.BreakUpFriendActivity$a */
    public class ViewOnClickListenerC1430a implements View.OnClickListener {
        public ViewOnClickListenerC1430a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            BreakUpFriendActivity.this.m7807s(false);
        }
    }

    /* renamed from: com.cyberlink.you.activity.BreakUpFriendActivity$b */
    public class ViewOnClickListenerC1431b implements View.OnClickListener {
        public ViewOnClickListenerC1431b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            BreakUpFriendActivity.this.m7807s(false);
        }
    }

    /* renamed from: com.cyberlink.you.activity.BreakUpFriendActivity$c */
    public class ViewOnClickListenerC1432c implements View.OnClickListener {
        public ViewOnClickListenerC1432c() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            BreakUpFriendActivity.this.m7809v(Page.Confrim);
        }
    }

    /* renamed from: com.cyberlink.you.activity.BreakUpFriendActivity$d */
    public class ViewOnClickListenerC1433d implements View.OnClickListener {

        /* renamed from: com.cyberlink.you.activity.BreakUpFriendActivity$d$a */
        public class a extends AsyncTask<Void, Void, Boolean> {

            /* renamed from: a */
            public final /* synthetic */ ProgressDialog f7447a;

            public a(ProgressDialog progressDialog) {
                this.f7447a = progressDialog;
            }

            @Override // android.os.AsyncTask
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public Boolean doInBackground(Void... voidArr) {
                Thread.currentThread().setName("BreakUpFriendActivity.onConfirmButtonClick AsyncTask");
                ArrayList arrayList = new ArrayList();
                arrayList.add(new C6301o("token", Globals.m7388i0().m7449L()));
                arrayList.add(new C6301o("userId", String.valueOf(BreakUpFriendActivity.this.f7431d.f13645c)));
                String str = (String) new FriendsClient(true).m15731j("friend", "breakUp", arrayList).first;
                if (str != null && str.equals("200")) {
                    return Boolean.TRUE;
                }
                Log.d("BreakUpFriendActivity", "statusCode = " + str);
                return Boolean.FALSE;
            }

            @Override // android.os.AsyncTask
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public void onPostExecute(Boolean bool) {
                try {
                    ProgressDialog progressDialog = this.f7447a;
                    if (progressDialog != null && progressDialog.isShowing()) {
                        this.f7447a.dismiss();
                    }
                    BreakUpFriendActivity.this.f7435h = false;
                    if (bool.booleanValue()) {
                        BreakUpFriendActivity.this.m7807s(true);
                    }
                } catch (Exception e9) {
                    e9.printStackTrace();
                }
            }
        }

        public ViewOnClickListenerC1433d() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (BreakUpFriendActivity.this.f7435h) {
                return;
            }
            BreakUpFriendActivity.this.f7435h = true;
            ProgressDialog progressDialogShow = ProgressDialog.show(BreakUpFriendActivity.this.m7808u(), "", BreakUpFriendActivity.this.getString(R.string.processing), true);
            BreakUpFriendActivity.this.f7434g = new a(progressDialogShow);
            BreakUpFriendActivity.this.f7434g.executeOnExecutor(C6385v.f21553a, new Void[0]);
        }
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_break_up_alert);
        Friend friend = (Friend) m7808u().getIntent().getParcelableExtra("friend");
        this.f7431d = friend;
        if (friend == null) {
            Log.d("BreakUpFriendActivity", "[onCreate] mFriendInfo is null. Return directly.");
            finish();
            return;
        }
        this.f7432e = (RelativeLayout) findViewById(R.id.layoutBreakUpAlertPage);
        this.f7433f = (RelativeLayout) findViewById(R.id.layoutBreakUpConfirmPage);
        findViewById(R.id.BreakUpBackBtn).setOnClickListener(this.f7436i);
        findViewById(R.id.BreakUpAlertCancel).setOnClickListener(this.f7437j);
        findViewById(R.id.BreakUpConfirmCancel).setOnClickListener(this.f7437j);
        findViewById(R.id.BreakUpAlertBreakUp).setOnClickListener(this.f7438k);
        findViewById(R.id.BreakUpConfirmBtn).setOnClickListener(this.f7439l);
        C6127a.m23469j(this, (ImageView) findViewById(R.id.BreakUpAvatar), this.f7431d);
        ((TextView) findViewById(R.id.BreakUpAlertDisplayName)).setText(this.f7431d.m15621b());
        ((TextView) findViewById(R.id.BreakUpConfirmDisplayName)).setText(this.f7431d.m15621b());
        ((TextView) findViewById(R.id.BreakUpAlertNotificationMessage)).setText(String.format(getResources().getString(R.string.break_up_notification_message), this.f7431d.m15621b(), this.f7431d.m15621b()));
        m7809v(Page.Alert);
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        AsyncTask<Void, Void, Boolean> asyncTask = this.f7434g;
        if (asyncTask != null) {
            asyncTask.cancel(false);
        }
    }

    /* renamed from: s */
    public final void m7807s(boolean z8) {
        if (m7808u().getCallingActivity() != null) {
            Intent intent = new Intent();
            if (z8) {
                intent.putExtra("isBrokenUp", true);
            }
            m7808u().setResult(-1, intent);
        }
        finish();
    }

    /* renamed from: u */
    public final Activity m7808u() {
        return this;
    }

    /* renamed from: v */
    public final void m7809v(Page page) {
        if (page == Page.Confrim) {
            this.f7432e.setVisibility(8);
            this.f7433f.setVisibility(0);
        } else {
            this.f7432e.setVisibility(0);
            this.f7433f.setVisibility(8);
        }
    }
}
