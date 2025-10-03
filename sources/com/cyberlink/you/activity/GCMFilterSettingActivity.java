package com.cyberlink.you.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.cyberlink.p030U.R;
import com.cyberlink.you.BaseActivity;
import com.cyberlink.you.Globals;
import com.cyberlink.you.utility.CLUtility;

/* loaded from: classes.dex */
public class GCMFilterSettingActivity extends BaseActivity {

    /* renamed from: d */
    public EditText f7706d;

    /* renamed from: e */
    public Button f7707e;

    /* renamed from: f */
    public String f7708f;

    /* renamed from: g */
    public TextView f7709g;

    /* renamed from: h */
    public TextView f7710h;

    /* renamed from: c */
    public final String f7705c = "GCMFilterSettingActivity";

    /* renamed from: i */
    public final String f7711i = "GCM Filter";

    /* renamed from: com.cyberlink.you.activity.GCMFilterSettingActivity$a */
    public class C1479a implements TextWatcher {
        public C1479a() {
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
            GCMFilterSettingActivity.this.f7709g.setText(editable.length() + "/" + GCMFilterSettingActivity.this.getResources().getInteger(R.integer.MaxGCMFilterLength));
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i9, int i10, int i11) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i9, int i10, int i11) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: q */
    public /* synthetic */ void m8204q(View view) {
        String string = this.f7706d.getText().toString();
        if (string.isEmpty()) {
            Globals.m7388i0().m7540e3(false);
        } else {
            Globals.m7388i0().m7540e3(true);
        }
        Globals.m7388i0().m7475Q2(string);
        m8207l();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: r */
    public /* synthetic */ void m8205r(View view) {
        this.f7706d.setText("");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: s */
    public /* synthetic */ void m8206s(View view) {
        CLUtility.m16589t1(m8208o());
        m8207l();
    }

    /* renamed from: l */
    public final void m8207l() {
        CLUtility.m16589t1(m8208o());
        setResult(-1, new Intent());
        finish();
    }

    /* renamed from: o */
    public final Activity m8208o() {
        return this;
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        m8207l();
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        String stringExtra = getIntent().getStringExtra("Title");
        this.f7708f = Globals.m7388i0().m7532d0();
        setContentView(R.layout.activity_gcm_filter_setting);
        this.f7706d = (EditText) findViewById(R.id.ProfileInfoUpdateEdit);
        this.f7707e = (Button) findViewById(R.id.ProfileInfoUpdateSaveBtn);
        this.f7709g = (TextView) findViewById(R.id.ShowWordsCount);
        TextView textView = (TextView) findViewById(R.id.ProfileInfoUpdateTitle);
        this.f7710h = textView;
        textView.setText("GCM Filter");
        if (stringExtra != null) {
            ((TextView) findViewById(R.id.ProfileInfoUpdateTitle)).setText(stringExtra);
        }
        this.f7706d.addTextChangedListener(new C1479a());
        String str = this.f7708f;
        if (str != null) {
            this.f7706d.setText(str);
        }
        this.f7707e.setOnClickListener(new View.OnClickListener() { // from class: com.cyberlink.you.activity.q3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f11070b.m8204q(view);
            }
        });
        findViewById(R.id.ProfileInfoUpdateXImage).setOnClickListener(new View.OnClickListener() { // from class: com.cyberlink.you.activity.r3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f11132b.m8205r(view);
            }
        });
        findViewById(R.id.ProfileInfoUpdateBackBtn).setOnClickListener(new View.OnClickListener() { // from class: com.cyberlink.you.activity.s3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f11162b.m8206s(view);
            }
        });
    }
}
