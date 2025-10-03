package com.cyberlink.you.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.cyberlink.p030U.R;
import com.cyberlink.you.BaseActivity;
import com.cyberlink.you.friends.FriendsClient;
import com.cyberlink.you.utility.CLUtility;
import p015b4.C0681k;

/* loaded from: classes.dex */
public class ResetCLAccountPasswordActivity extends BaseActivity {

    /* renamed from: d */
    public TextView f8675d;

    /* renamed from: e */
    public ProgressBar f8676e;

    /* renamed from: f */
    public FriendsClient f8677f;

    /* renamed from: g */
    public String f8678g;

    /* renamed from: h */
    public int f8679h;

    /* renamed from: c */
    public String f8674c = ResetCLAccountPasswordActivity.class.getSimpleName();

    /* renamed from: i */
    public C0681k.a<String> f8680i = new C1657a();

    /* renamed from: j */
    public View.OnClickListener f8681j = new ViewOnClickListenerC1658b();

    /* renamed from: k */
    public View.OnClickListener f8682k = new ViewOnClickListenerC1659c();

    /* renamed from: com.cyberlink.you.activity.ResetCLAccountPasswordActivity$a */
    public class C1657a implements C0681k.a<String> {
        public C1657a() {
        }

        @Override // p015b4.C0681k.a
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onSuccess(String str) {
            Log.d(ResetCLAccountPasswordActivity.this.f8674c, "SendResetRequestListener onSuccess");
            ResetCLAccountPasswordActivity.this.f8676e.setVisibility(8);
            ResetCLAccountPasswordActivity resetCLAccountPasswordActivity = ResetCLAccountPasswordActivity.this;
            Toast.makeText(resetCLAccountPasswordActivity, resetCLAccountPasswordActivity.getString(R.string.login_email_check_email_info), 1).show();
        }

        @Override // p015b4.C0681k.a
        public void onError(String str) {
            Log.d(ResetCLAccountPasswordActivity.this.f8674c, "SendResetRequestListener onError" + str);
            ResetCLAccountPasswordActivity.this.f8676e.setVisibility(8);
            ResetCLAccountPasswordActivity resetCLAccountPasswordActivity = ResetCLAccountPasswordActivity.this;
            Toast.makeText(resetCLAccountPasswordActivity, C0681k.m3399e(resetCLAccountPasswordActivity, str), 1).show();
        }
    }

    /* renamed from: com.cyberlink.you.activity.ResetCLAccountPasswordActivity$b */
    public class ViewOnClickListenerC1658b implements View.OnClickListener {
        public ViewOnClickListenerC1658b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ResetCLAccountPasswordActivity.this.m9577r();
        }
    }

    /* renamed from: com.cyberlink.you.activity.ResetCLAccountPasswordActivity$c */
    public class ViewOnClickListenerC1659c implements View.OnClickListener {
        public ViewOnClickListenerC1659c() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ResetCLAccountPasswordActivity.this.m9574n();
        }
    }

    /* renamed from: com.cyberlink.you.activity.ResetCLAccountPasswordActivity$d */
    public class ViewOnClickListenerC1660d implements View.OnClickListener {
        public ViewOnClickListenerC1660d() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ResetCLAccountPasswordActivity.this.m9577r();
        }
    }

    /* renamed from: n */
    public final void m9574n() {
        String string = this.f8675d.getText().toString();
        this.f8678g = string;
        if (!CLUtility.m16605x1(string)) {
            Toast.makeText(this, getString(R.string.empty_email_warning), 1).show();
        } else {
            C0681k.m3396b(this.f8678g, this.f8680i);
            this.f8676e.setVisibility(0);
        }
    }

    /* renamed from: o */
    public final void m9575o() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            this.f8679h = extras.getInt("registerType", 0);
            this.f8678g = extras.getString("registerEmail", "");
        }
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        m9577r();
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_reset_cl_account_password);
        this.f8677f = new FriendsClient(true);
        m9575o();
        m9576q();
        String str = this.f8678g;
        if (str == null || str.isEmpty()) {
            return;
        }
        this.f8675d.setText(this.f8678g);
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        this.f8677f.m15717U0();
    }

    /* renamed from: q */
    public final void m9576q() {
        this.f8676e = (ProgressBar) findViewById(R.id.loading);
        this.f8675d = (TextView) findViewById(R.id.EmailEditText);
        findViewById(R.id.ResetPWBackBtn).setOnClickListener(this.f8681j);
        findViewById(R.id.ResetPWSendBtn).setOnClickListener(this.f8682k);
        findViewById(R.id.ResetPWAlreadyTextView).setOnClickListener(new ViewOnClickListenerC1660d());
    }

    /* renamed from: r */
    public final void m9577r() {
        CLUtility.m16602w2(this, false);
        int i9 = this.f8679h;
        if (i9 == 0 || i9 == 1) {
            Intent intent = new Intent(this, (Class<?>) EmailConnectActivity.class);
            intent.putExtra("registerType", 1);
            intent.putExtra("registerEmail", this.f8678g);
            setResult(-1, intent);
            startActivity(intent);
        } else if (i9 == 5 || i9 == 4) {
            Intent intent2 = new Intent(this, (Class<?>) SignInCLAccountActivity.class);
            intent2.putExtra("registerEmail", this.f8678g);
            setResult(-1, intent2);
            startActivity(intent2);
        }
        finish();
    }
}
