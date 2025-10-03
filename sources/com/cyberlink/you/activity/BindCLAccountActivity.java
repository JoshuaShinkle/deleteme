package com.cyberlink.you.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.cyberlink.p030U.R;
import com.cyberlink.you.BaseActivity;
import p116k4.C5166n;
import p209u2.C6366c;

/* loaded from: classes.dex */
public class BindCLAccountActivity extends BaseActivity {

    /* renamed from: c */
    public C6366c f7383c = new C6366c(10);

    /* renamed from: com.cyberlink.you.activity.BindCLAccountActivity$a */
    public class ViewOnClickListenerC1417a implements View.OnClickListener {
        public ViewOnClickListenerC1417a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            Intent intent = new Intent(BindCLAccountActivity.this.getApplicationContext(), (Class<?>) EmailConnectActivity.class);
            intent.putExtra("registerType", 4);
            BindCLAccountActivity.this.startActivity(intent);
            BindCLAccountActivity.this.finish();
        }
    }

    /* renamed from: com.cyberlink.you.activity.BindCLAccountActivity$b */
    public class ViewOnClickListenerC1418b implements View.OnClickListener {
        public ViewOnClickListenerC1418b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (BindCLAccountActivity.this.f7383c.m24458a()) {
                C5166n.m20140k(BindCLAccountActivity.this);
            }
        }
    }

    /* renamed from: j */
    public final void m7768j() {
        findViewById(R.id.BindCLAccountSignUp).setOnClickListener(new ViewOnClickListenerC1417a());
        m7769k();
    }

    /* renamed from: k */
    public final void m7769k() {
        findViewById(R.id.BindCLAccountInfoLogoImg).setOnClickListener(new ViewOnClickListenerC1418b());
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_bind_cl_account);
        m7768j();
    }
}
