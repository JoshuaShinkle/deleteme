package com.cyberlink.you.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.cyberlink.p030U.R;
import com.cyberlink.you.BaseActivity;
import com.cyberlink.you.friends.FriendsClient;
import com.cyberlink.you.utility.CLUtility;
import java.util.Locale;
import p015b4.C0681k;
import p116k4.C5179r0;

/* loaded from: classes.dex */
public class ExistCLAccountActivity extends BaseActivity {

    /* renamed from: d */
    public String f7676d;

    /* renamed from: e */
    public int f7677e;

    /* renamed from: f */
    public ProgressBar f7678f;

    /* renamed from: g */
    public FriendsClient f7679g;

    /* renamed from: c */
    public final String f7675c = "ExistCLAccountActivity";

    /* renamed from: h */
    public C0681k.a<String> f7680h = new C1477d();

    /* renamed from: com.cyberlink.you.activity.ExistCLAccountActivity$a */
    public class ViewOnClickListenerC1474a implements View.OnClickListener {
        public ViewOnClickListenerC1474a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ExistCLAccountActivity.this.m8177s();
        }
    }

    /* renamed from: com.cyberlink.you.activity.ExistCLAccountActivity$b */
    public class ViewOnClickListenerC1475b implements View.OnClickListener {
        public ViewOnClickListenerC1475b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ExistCLAccountActivity.this.m8178u();
        }
    }

    /* renamed from: com.cyberlink.you.activity.ExistCLAccountActivity$c */
    public class ViewOnClickListenerC1476c implements View.OnClickListener {
        public ViewOnClickListenerC1476c() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            C0681k.m3396b(ExistCLAccountActivity.this.f7676d, ExistCLAccountActivity.this.f7680h);
            ExistCLAccountActivity.this.f7678f.setVisibility(0);
        }
    }

    /* renamed from: com.cyberlink.you.activity.ExistCLAccountActivity$d */
    public class C1477d implements C0681k.a<String> {
        public C1477d() {
        }

        @Override // p015b4.C0681k.a
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onSuccess(String str) {
            Log.d("ExistCLAccountActivity", "SendResetRequestListener onSuccess");
            ExistCLAccountActivity.this.f7678f.setVisibility(8);
            Intent intent = new Intent(ExistCLAccountActivity.this, (Class<?>) ResetBindCLAccountPasswordActivity.class);
            intent.putExtra("registerType", ExistCLAccountActivity.this.f7677e);
            intent.putExtra("registerEmail", ExistCLAccountActivity.this.f7676d);
            ExistCLAccountActivity.this.startActivity(intent);
            ExistCLAccountActivity.this.finish();
        }

        @Override // p015b4.C0681k.a
        public void onError(String str) {
            Log.d("ExistCLAccountActivity", "SendResetRequestListener onError" + str);
            ExistCLAccountActivity.this.f7678f.setVisibility(8);
            ExistCLAccountActivity existCLAccountActivity = ExistCLAccountActivity.this;
            Toast.makeText(existCLAccountActivity, C0681k.m3399e(existCLAccountActivity, str), 1).show();
        }
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        m8177s();
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_exist_cl_account);
        this.f7679g = new FriendsClient(true);
        m8175q();
        m8176r();
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        this.f7679g.m15717U0();
    }

    /* renamed from: q */
    public final void m8175q() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            this.f7677e = extras.getInt("registerType", 0);
            this.f7676d = extras.getString("registerEmail");
        }
    }

    /* renamed from: r */
    public final void m8176r() {
        this.f7678f = (ProgressBar) findViewById(R.id.loading);
        findViewById(R.id.ExistCLAccountBackBtn).setOnClickListener(new ViewOnClickListenerC1474a());
        findViewById(R.id.SignInCLAccountTextView).setOnClickListener(new ViewOnClickListenerC1475b());
        findViewById(R.id.BindCLAccountResetBtn).setOnClickListener(new ViewOnClickListenerC1476c());
        if (Locale.getDefault().getLanguage().equals("es")) {
            TextView textView = (TextView) findViewById(R.id.ExistCLAccountTitleTextView);
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
            layoutParams.addRule(1, R.id.ExistCLAccountBackBtn);
            textView.setLayoutParams(layoutParams);
            textView.setGravity(17);
            C5179r0.m20247b(textView, 1);
        }
        C5179r0.m20247b((TextView) findViewById(R.id.ExistCLAccountInfoTextView), 2);
        C5179r0.m20247b((TextView) findViewById(R.id.ExistCLAccountReasonTextView), 4);
        C5179r0.m20247b((TextView) findViewById(R.id.ExistCLAccountResetHintTextView), 2);
    }

    /* renamed from: s */
    public final void m8177s() {
        CLUtility.m16602w2(this, false);
        int i9 = this.f7677e;
        if (i9 == 0) {
            Intent intent = new Intent(this, (Class<?>) EmailConnectActivity.class);
            intent.putExtra("registerType", 1);
            intent.putExtra("registerEmail", this.f7676d);
            setResult(-1, intent);
            startActivity(intent);
        } else if (i9 == 4) {
            Intent intent2 = new Intent(this, (Class<?>) EmailConnectActivity.class);
            intent2.putExtra("registerType", 4);
            setResult(-1, intent2);
            startActivity(intent2);
        }
        finish();
    }

    /* renamed from: u */
    public final void m8178u() {
        int i9 = this.f7677e;
        if (i9 == 0) {
            Intent intent = new Intent(this, (Class<?>) EmailConnectActivity.class);
            intent.putExtra("registerType", 1);
            intent.putExtra("registerEmail", this.f7676d);
            startActivity(intent);
        } else if (i9 == 4) {
            Intent intent2 = new Intent(this, (Class<?>) SignInCLAccountActivity.class);
            intent2.putExtra("registerEmail", this.f7676d);
            intent2.putExtra("activityName", "existCLAccountActivity");
            startActivity(intent2);
        }
        finish();
    }
}
