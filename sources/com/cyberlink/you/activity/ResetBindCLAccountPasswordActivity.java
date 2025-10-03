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
import p015b4.C0681k;

/* loaded from: classes.dex */
public class ResetBindCLAccountPasswordActivity extends BaseActivity {

    /* renamed from: d */
    public ProgressBar f8662d;

    /* renamed from: e */
    public FriendsClient f8663e;

    /* renamed from: f */
    public String f8664f;

    /* renamed from: g */
    public int f8665g;

    /* renamed from: c */
    public String f8661c = ResetBindCLAccountPasswordActivity.class.getSimpleName();

    /* renamed from: h */
    public C0681k.a<String> f8666h = new C1653a();

    /* renamed from: i */
    public View.OnClickListener f8667i = new ViewOnClickListenerC1654b();

    /* renamed from: j */
    public View.OnClickListener f8668j = new ViewOnClickListenerC1655c();

    /* renamed from: k */
    public View.OnClickListener f8669k = new ViewOnClickListenerC1656d();

    /* renamed from: com.cyberlink.you.activity.ResetBindCLAccountPasswordActivity$a */
    public class C1653a implements C0681k.a<String> {
        public C1653a() {
        }

        @Override // p015b4.C0681k.a
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onSuccess(String str) {
            Log.d(ResetBindCLAccountPasswordActivity.this.f8661c, "SendResetPW onSuccess");
            ResetBindCLAccountPasswordActivity.this.f8662d.setVisibility(8);
            ResetBindCLAccountPasswordActivity resetBindCLAccountPasswordActivity = ResetBindCLAccountPasswordActivity.this;
            Toast.makeText(resetBindCLAccountPasswordActivity, resetBindCLAccountPasswordActivity.getString(R.string.login_email_check_email_info), 1).show();
        }

        @Override // p015b4.C0681k.a
        public void onError(String str) {
            Log.d(ResetBindCLAccountPasswordActivity.this.f8661c, "SendResetPW onError" + str);
            ResetBindCLAccountPasswordActivity.this.f8662d.setVisibility(8);
            ResetBindCLAccountPasswordActivity resetBindCLAccountPasswordActivity = ResetBindCLAccountPasswordActivity.this;
            Toast.makeText(resetBindCLAccountPasswordActivity, C0681k.m3399e(resetBindCLAccountPasswordActivity, str), 1).show();
        }
    }

    /* renamed from: com.cyberlink.you.activity.ResetBindCLAccountPasswordActivity$b */
    public class ViewOnClickListenerC1654b implements View.OnClickListener {
        public ViewOnClickListenerC1654b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ResetBindCLAccountPasswordActivity.this.m9568s();
        }
    }

    /* renamed from: com.cyberlink.you.activity.ResetBindCLAccountPasswordActivity$c */
    public class ViewOnClickListenerC1655c implements View.OnClickListener {
        public ViewOnClickListenerC1655c() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            C0681k.m3396b(ResetBindCLAccountPasswordActivity.this.f8664f, ResetBindCLAccountPasswordActivity.this.f8666h);
            ResetBindCLAccountPasswordActivity.this.f8662d.setVisibility(0);
        }
    }

    /* renamed from: com.cyberlink.you.activity.ResetBindCLAccountPasswordActivity$d */
    public class ViewOnClickListenerC1656d implements View.OnClickListener {
        public ViewOnClickListenerC1656d() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (ResetBindCLAccountPasswordActivity.this.f8665g == 0) {
                Intent intent = new Intent(ResetBindCLAccountPasswordActivity.this, (Class<?>) EmailConnectActivity.class);
                intent.putExtra("registerType", 1);
                intent.putExtra("registerEmail", ResetBindCLAccountPasswordActivity.this.f8664f);
                ResetBindCLAccountPasswordActivity.this.setResult(-1, intent);
                ResetBindCLAccountPasswordActivity.this.startActivity(intent);
            } else if (ResetBindCLAccountPasswordActivity.this.f8665g == 4) {
                Intent intent2 = new Intent(ResetBindCLAccountPasswordActivity.this, (Class<?>) SignInCLAccountActivity.class);
                intent2.putExtra("registerEmail", ResetBindCLAccountPasswordActivity.this.f8664f);
                ResetBindCLAccountPasswordActivity.this.setResult(-1, intent2);
                ResetBindCLAccountPasswordActivity.this.startActivity(intent2);
            }
            ResetBindCLAccountPasswordActivity.this.finish();
        }
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        m9568s();
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_reset_bind_cl_account_password);
        this.f8663e = new FriendsClient(true);
        m9566q();
        m9567r();
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        this.f8663e.m15717U0();
    }

    /* renamed from: q */
    public final void m9566q() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            this.f8665g = extras.getInt("registerType", 0);
            this.f8664f = extras.getString("registerEmail", "");
        }
    }

    /* renamed from: r */
    public final void m9567r() {
        this.f8662d = (ProgressBar) findViewById(R.id.loading);
        findViewById(R.id.ResetPWBackBtn).setOnClickListener(this.f8667i);
        findViewById(R.id.ResendPWTextView).setOnClickListener(this.f8668j);
        findViewById(R.id.ResetPWAlreadyBtn).setOnClickListener(this.f8669k);
        ((TextView) findViewById(R.id.ResetPWInfo)).setText(getString(R.string.email_cl_account_forget_password_info_v3, this.f8664f));
    }

    /* renamed from: s */
    public final void m9568s() {
        Intent intent = new Intent(this, (Class<?>) ExistCLAccountActivity.class);
        intent.putExtra("registerType", this.f8665g);
        intent.putExtra("registerEmail", this.f8664f);
        startActivity(intent);
        finish();
    }
}
