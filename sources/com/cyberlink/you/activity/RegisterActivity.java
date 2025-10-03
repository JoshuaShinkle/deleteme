package com.cyberlink.you.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.cyberlink.p030U.R;
import com.cyberlink.you.BaseActivity;
import com.cyberlink.you.p036ui.SelectServerDialog;
import com.cyberlink.you.utility.ULogUtility;
import p116k4.C5166n;
import p116k4.C5170o0;
import p116k4.C5179r0;
import p209u2.C6366c;
import p236x2.C6566a;

/* loaded from: classes.dex */
public class RegisterActivity extends BaseActivity {

    /* renamed from: c */
    public C6366c f8617c = new C6366c(10);

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: q */
    public /* synthetic */ void m9518q(View view) {
        startActivity(new Intent(this, (Class<?>) JoinCLMWActivity.class));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: r */
    public /* synthetic */ void m9519r(View view) {
        C6566a.m25154m("login.newUser");
        m9526z(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: s */
    public /* synthetic */ void m9520s(View view) {
        C6566a.m25154m("login.oldUser");
        m9526z(1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: u */
    public /* synthetic */ void m9521u(View view) {
        if (this.f8617c.m24458a()) {
            C5166n.m20140k(this);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: v */
    public /* synthetic */ void m9522v(View view) {
        if (this.f8617c.m24458a()) {
            new SelectServerDialog().m16355h(this);
        }
    }

    /* renamed from: o */
    public final void m9523o() {
        C5179r0.m20247b((TextView) findViewById(R.id.RegistratioTitleTextView), 2);
        C5179r0.m20247b((TextView) findViewById(R.id.RegistratioJoinMeetingTextView), 2);
        TextView textView = (TextView) findViewById(R.id.btnJoinMeetingsOrWebinars);
        C5179r0.m20247b(textView, 1);
        textView.setOnClickListener(new View.OnClickListener() { // from class: com.cyberlink.you.activity.uc
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f11435b.m9518q(view);
            }
        });
        TextView textView2 = (TextView) findViewById(R.id.RegistrationSignUpBtn);
        C5179r0.m20247b(textView2, 1);
        textView2.setOnClickListener(new View.OnClickListener() { // from class: com.cyberlink.you.activity.vc
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f11817b.m9519r(view);
            }
        });
        findViewById(R.id.LoginBtn).setOnClickListener(new View.OnClickListener() { // from class: com.cyberlink.you.activity.wc
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f11849b.m9520s(view);
            }
        });
        findViewById(R.id.RegisterPage).setVisibility(0);
        Intent intent = getIntent();
        String stringExtra = intent.getStringExtra("meetingLtiEventId");
        String stringExtra2 = intent.getStringExtra("meetingLtiToken");
        String stringExtra3 = intent.getStringExtra("meetingLtiUserName");
        ULogUtility.m16683s("RegisterActivity", "EventId: " + stringExtra + " / ltiToken: " + stringExtra2 + " / UserName: " + stringExtra3);
        if (C5170o0.m20170e(stringExtra) || C5170o0.m20170e(stringExtra2) || C5170o0.m20170e(stringExtra3)) {
            return;
        }
        Intent intent2 = new Intent(this, (Class<?>) JoinCLMWActivity.class);
        intent2.putExtra("meetingLtiEventId", stringExtra);
        intent2.putExtra("meetingLtiToken", stringExtra2);
        intent2.putExtra("meetingLtiUserName", stringExtra3);
        startActivity(intent2);
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_register);
        m9524w();
        m9525y();
        m9523o();
        GDPRActivity.m8216u(this, (TextView) findViewById(R.id.RegistrationPolicy), false);
    }

    /* renamed from: w */
    public final void m9524w() {
        findViewById(R.id.RegistrationLogoImg).setOnClickListener(new View.OnClickListener() { // from class: com.cyberlink.you.activity.xc
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f12252b.m9521u(view);
            }
        });
    }

    /* renamed from: y */
    public final void m9525y() {
        findViewById(R.id.RegistratioTitleTextView).setOnClickListener(new View.OnClickListener() { // from class: com.cyberlink.you.activity.yc
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f12277b.m9522v(view);
            }
        });
    }

    /* renamed from: z */
    public final void m9526z(int i9) {
        Intent intent = new Intent(getApplicationContext(), (Class<?>) EmailConnectActivity.class);
        intent.putExtra("registerType", i9);
        startActivity(intent);
        finish();
    }
}
