package com.cyberlink.you.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import com.cyberlink.p030U.R;
import com.cyberlink.you.BaseActivity;
import com.cyberlink.you.Globals;
import com.cyberlink.you.utility.CLUtility;

/* loaded from: classes.dex */
public class ProxyConfigActivity extends BaseActivity {

    /* renamed from: c */
    public EditText f8560c;

    /* renamed from: d */
    public EditText f8561d;

    /* renamed from: e */
    public EditText f8562e;

    /* renamed from: f */
    public EditText f8563f;

    /* renamed from: g */
    public View.OnClickListener f8564g = new View.OnClickListener() { // from class: com.cyberlink.you.activity.ac
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f9720b.m9423r(view);
        }
    };

    /* renamed from: h */
    public View.OnClickListener f8565h = new ViewOnClickListenerC1640a();

    /* renamed from: i */
    public View.OnClickListener f8566i = new ViewOnClickListenerC1641b();

    /* renamed from: com.cyberlink.you.activity.ProxyConfigActivity$a */
    public class ViewOnClickListenerC1640a implements View.OnClickListener {
        public ViewOnClickListenerC1640a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            CLUtility.m16589t1(ProxyConfigActivity.this);
            Globals.m7388i0().m7481R3("Proxy_IP", ProxyConfigActivity.this.f8560c.getText().toString().trim());
            Globals.m7388i0().m7481R3("Proxy_Port", ProxyConfigActivity.this.f8561d.getText().toString().trim());
            Globals.m7388i0().m7481R3("Proxy_User_Name", ProxyConfigActivity.this.f8562e.getText().toString().trim());
            Globals.m7388i0().m7481R3("Proxy_User_Password", ProxyConfigActivity.this.f8563f.getText().toString().trim());
            ProxyConfigActivity.this.m9425s();
        }
    }

    /* renamed from: com.cyberlink.you.activity.ProxyConfigActivity$b */
    public class ViewOnClickListenerC1641b implements View.OnClickListener {
        public ViewOnClickListenerC1641b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ProxyConfigActivity.this.f8560c.setText("");
            ProxyConfigActivity.this.f8561d.setText("");
            ProxyConfigActivity.this.f8562e.setText("");
            ProxyConfigActivity.this.f8563f.setText("");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: r */
    public /* synthetic */ void m9423r(View view) {
        m9425s();
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        m9425s();
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_proxy_config);
        m9424q();
    }

    /* renamed from: q */
    public final void m9424q() {
        findViewById(R.id.PrivacySettingBackBtn).setOnClickListener(this.f8564g);
        findViewById(R.id.PrivacySettingTopBarSave).setOnClickListener(this.f8565h);
        findViewById(R.id.ProxyDataReset).setOnClickListener(this.f8566i);
        EditText editText = (EditText) findViewById(R.id.ProxyIPEditText);
        this.f8560c = editText;
        editText.setText(Globals.m7388i0().m7473Q0("Proxy_IP"));
        EditText editText2 = (EditText) findViewById(R.id.ProxyPortEditText);
        this.f8561d = editText2;
        editText2.setText(Globals.m7388i0().m7473Q0("Proxy_Port"));
        EditText editText3 = (EditText) findViewById(R.id.ProxyUserNameEditText);
        this.f8562e = editText3;
        editText3.setText(Globals.m7388i0().m7473Q0("Proxy_User_Name"));
        EditText editText4 = (EditText) findViewById(R.id.ProxyUserPasswordEditText);
        this.f8563f = editText4;
        editText4.setText(Globals.m7388i0().m7473Q0("Proxy_User_Password"));
    }

    /* renamed from: s */
    public final void m9425s() {
        finish();
    }
}
