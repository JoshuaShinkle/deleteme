package com.cyberlink.you.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.cyberlink.p030U.R;
import com.cyberlink.you.BaseActivity;
import com.cyberlink.you.Globals;

/* loaded from: classes.dex */
public class PrivacyControlActivity extends BaseActivity {

    /* renamed from: c */
    public View f8532c;

    /* renamed from: d */
    public View.OnClickListener f8533d = new View.OnClickListener() { // from class: com.cyberlink.you.activity.pb
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f11040b.m9377o(view);
        }
    };

    /* renamed from: e */
    public View.OnClickListener f8534e = new View.OnClickListener() { // from class: com.cyberlink.you.activity.qb
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f11083b.m9378q(view);
        }
    };

    /* renamed from: f */
    public View.OnClickListener f8535f = new View.OnClickListener() { // from class: com.cyberlink.you.activity.rb
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f11142b.m9379r(view);
        }
    };

    /* renamed from: g */
    public View.OnClickListener f8536g = new View.OnClickListener() { // from class: com.cyberlink.you.activity.sb
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f11173b.m9380s(view);
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: o */
    public /* synthetic */ void m9377o(View view) {
        m9382u();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: q */
    public /* synthetic */ void m9378q(View view) {
        startActivity(new Intent(this, (Class<?>) DatabaseEncryptSettingActivity.class));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: r */
    public /* synthetic */ void m9379r(View view) {
        Globals.m7388i0().m7571k4(!Globals.m7388i0().m7552h2().booleanValue());
        this.f8532c.setSelected(Globals.m7388i0().m7552h2().booleanValue());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: s */
    public /* synthetic */ void m9380s(View view) {
        startActivity(new Intent(this, (Class<?>) SignOutActivity.class));
    }

    /* renamed from: n */
    public final void m9381n() {
        ((ImageView) findViewById(R.id.PrivacySettingBackBtn)).setOnClickListener(this.f8533d);
        findViewById(R.id.DatabaseEncryptSettingArea).setOnClickListener(this.f8534e);
        View viewFindViewById = findViewById(R.id.DatabaseEncryptItem);
        TextView textView = (TextView) viewFindViewById.findViewById(R.id.EditGotoTextView);
        viewFindViewById.findViewById(R.id.EditGotoImageView).setContentDescription("[AID]Setting_DatabaseEncrypt");
        ((TextView) viewFindViewById.findViewById(R.id.EditGotoTitleTextView)).setText(getResources().getString(R.string.setting_local_database_encryption));
        if (Globals.m7388i0().m7409C1().booleanValue()) {
            textView.setText(getResources().getString(R.string.setting_on));
        } else {
            textView.setText(getResources().getString(R.string.setting_off));
        }
        findViewById(R.id.ServerCertificateSettingArea).setOnClickListener(this.f8535f);
        View viewFindViewById2 = findViewById(R.id.ServerCertificateItemArea);
        ((TextView) viewFindViewById2.findViewById(R.id.EditCheckTextView)).setText(getResources().getString(R.string.setting_verify_server_certificate));
        View viewFindViewById3 = viewFindViewById2.findViewById(R.id.EditCheckImageView);
        this.f8532c = viewFindViewById3;
        viewFindViewById3.setSelected(Globals.m7388i0().m7552h2().booleanValue());
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        m9382u();
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_privacy_control);
        m9381n();
    }

    /* renamed from: u */
    public final void m9382u() {
        finish();
    }
}
