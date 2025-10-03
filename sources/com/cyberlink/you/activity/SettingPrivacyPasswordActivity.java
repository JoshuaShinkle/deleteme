package com.cyberlink.you.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.cyberlink.p030U.R;
import com.cyberlink.you.BaseActivity;
import com.cyberlink.you.Globals;
import com.cyberlink.you.friends.FriendsClient;
import com.cyberlink.you.p036ui.C3123g;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;
import p201t3.C6301o;
import p209u2.C6385v;

/* loaded from: classes.dex */
public class SettingPrivacyPasswordActivity extends BaseActivity {

    /* renamed from: d */
    public ImageView f9031d;

    /* renamed from: e */
    public View f9032e;

    /* renamed from: f */
    public View f9033f;

    /* renamed from: g */
    public String f9034g;

    /* renamed from: c */
    public final String f9030c = "SettingPasswordActivity";

    /* renamed from: h */
    public View.OnClickListener f9035h = new ViewOnClickListenerC1733a();

    /* renamed from: i */
    public View.OnClickListener f9036i = new ViewOnClickListenerC1734b();

    /* renamed from: j */
    public View.OnClickListener f9037j = new ViewOnClickListenerC1735c();

    /* renamed from: com.cyberlink.you.activity.SettingPrivacyPasswordActivity$a */
    public class ViewOnClickListenerC1733a implements View.OnClickListener {
        public ViewOnClickListenerC1733a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            SettingPrivacyPasswordActivity.this.m9972s();
        }
    }

    /* renamed from: com.cyberlink.you.activity.SettingPrivacyPasswordActivity$b */
    public class ViewOnClickListenerC1734b implements View.OnClickListener {

        /* renamed from: com.cyberlink.you.activity.SettingPrivacyPasswordActivity$b$a */
        public class a implements DialogInterface.OnClickListener {
            public a() {
            }

            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i9) {
                Intent intent = new Intent(SettingPrivacyPasswordActivity.this.m9969o(), (Class<?>) PasswordActivity.class);
                intent.putExtra("privacyType", "privacyPassword");
                SettingPrivacyPasswordActivity.this.startActivityForResult(intent, 1);
            }
        }

        /* renamed from: com.cyberlink.you.activity.SettingPrivacyPasswordActivity$b$b */
        public class b implements Runnable {
            public b() {
            }

            @Override // java.lang.Runnable
            public void run() {
                SettingPrivacyPasswordActivity.this.m9973u();
            }
        }

        /* renamed from: com.cyberlink.you.activity.SettingPrivacyPasswordActivity$b$c */
        public class c extends AsyncTask<Void, Void, Boolean> {
            public c() {
            }

            @Override // android.os.AsyncTask
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public Boolean doInBackground(Void... voidArr) throws JSONException {
                String str;
                FriendsClient friendsClient = new FriendsClient(true);
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put("profile.pin.enabled", "0");
                    ArrayList arrayList = new ArrayList();
                    arrayList.add(new C6301o("token", Globals.m7388i0().m7449L()));
                    arrayList.add(new C6301o("attrs", jSONObject.toString()));
                    Pair<String, String> pairM15731j = friendsClient.m15731j("user", "updateUser", arrayList);
                    if (pairM15731j != null && (str = (String) pairM15731j.first) != null && str.equals("200")) {
                        return Boolean.TRUE;
                    }
                    friendsClient.m15717U0();
                    return Boolean.FALSE;
                } catch (JSONException unused) {
                    return Boolean.FALSE;
                }
            }

            @Override // android.os.AsyncTask
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public void onPostExecute(Boolean bool) {
                if (bool.booleanValue()) {
                    Globals.m7388i0().m7522a4(0);
                    Globals.m7388i0().m7527b4(false);
                }
                SettingPrivacyPasswordActivity.this.m9973u();
            }
        }

        public ViewOnClickListenerC1734b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (!SettingPrivacyPasswordActivity.this.m9971r()) {
                if (!SettingPrivacyPasswordActivity.this.f9034g.equals("privacyPassword")) {
                    new c().executeOnExecutor(C6385v.f21554b, new Void[0]);
                    return;
                } else {
                    Globals.m7388i0().m7471P3(0);
                    SettingPrivacyPasswordActivity.this.m9969o().runOnUiThread(new b());
                    return;
                }
            }
            if (!SettingPrivacyPasswordActivity.this.f9034g.equals("privacyPassword")) {
                Intent intent = new Intent(SettingPrivacyPasswordActivity.this.m9969o(), (Class<?>) PasswordActivity.class);
                intent.putExtra("privacyType", "privacySecurityPIN");
                SettingPrivacyPasswordActivity.this.startActivityForResult(intent, 1);
            } else {
                AlertDialog.Builder builderM16382a = C3123g.m16382a(SettingPrivacyPasswordActivity.this);
                builderM16382a.setMessage(SettingPrivacyPasswordActivity.this.getString(R.string.setting_privacy_enable_warning_message));
                builderM16382a.setPositiveButton(SettingPrivacyPasswordActivity.this.getString(R.string.ok), new a());
                AlertDialog alertDialogShow = builderM16382a.show();
                alertDialogShow.show();
                ((TextView) alertDialogShow.findViewById(android.R.id.message)).setGravity(17);
            }
        }
    }

    /* renamed from: com.cyberlink.you.activity.SettingPrivacyPasswordActivity$c */
    public class ViewOnClickListenerC1735c implements View.OnClickListener {
        public ViewOnClickListenerC1735c() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (SettingPrivacyPasswordActivity.this.m9971r()) {
                Log.d("SettingPasswordActivity", "Change Password but not create password yet");
                return;
            }
            Intent intent = new Intent(SettingPrivacyPasswordActivity.this.m9969o(), (Class<?>) PasswordActivity.class);
            if (SettingPrivacyPasswordActivity.this.f9034g.equals("privacyPassword")) {
                intent.putExtra("privacyType", "privacyPassword");
            } else {
                intent.putExtra("privacyType", "privacySecurityPIN");
            }
            SettingPrivacyPasswordActivity.this.startActivity(intent);
        }
    }

    /* renamed from: o */
    public final Activity m9969o() {
        return this;
    }

    @Override // android.app.Activity
    public void onActivityResult(int i9, int i10, Intent intent) {
        super.onActivityResult(i9, i10, intent);
        if (i9 == 1 && i10 == -1) {
            setResult(-1, intent);
        }
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        m9972s();
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_setting_privacy_password);
        String stringExtra = getIntent().getStringExtra("privacyType");
        this.f9034g = stringExtra;
        if (stringExtra != null) {
            m9970q();
        }
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        m9973u();
    }

    /* renamed from: q */
    public final void m9970q() {
        findViewById(R.id.backBtn).setOnClickListener(this.f9035h);
        TextView textView = (TextView) findViewById(R.id.title);
        if (this.f9034g.equals("privacyPassword")) {
            textView.setText(getString(R.string.setting_privacy_password_title));
        } else {
            textView.setText(getString(R.string.setting_security_pin_title));
        }
        TextView textView2 = (TextView) findViewById(R.id.introduce);
        if (this.f9034g.equals("privacyPassword")) {
            textView2.setText(getString(R.string.setting_privacy_password_introduce));
        } else {
            textView2.setText(getString(R.string.setting_privacy_security_introduce));
        }
        View viewFindViewById = findViewById(R.id.SettingPrivacyPasswordCheckBoxArea);
        this.f9031d = (ImageView) viewFindViewById.findViewById(R.id.EditCheckImageView);
        TextView textView3 = (TextView) viewFindViewById.findViewById(R.id.EditCheckTextView);
        if (this.f9034g.equals("privacyPassword")) {
            textView3.setText(getResources().getString(R.string.setting_privacy_password_title));
        } else {
            textView3.setText(getResources().getString(R.string.setting_privacy_security_title));
        }
        viewFindViewById.setOnClickListener(this.f9036i);
        View viewFindViewById2 = findViewById(R.id.SettingPrivacyPasswordChangeArea);
        this.f9032e = viewFindViewById2;
        viewFindViewById2.setOnClickListener(this.f9037j);
        TextView textView4 = (TextView) this.f9032e.findViewById(R.id.EditGotoTitleTextView);
        if (this.f9034g.equals("privacyPassword")) {
            textView4.setText(getResources().getString(R.string.setting_privacy_change_password_item));
        } else {
            textView4.setText(getResources().getString(R.string.setting_privacy_change_security_item));
        }
        this.f9033f = findViewById(R.id.SettingPrivacyPasswordChangeNoteText);
    }

    /* renamed from: r */
    public final boolean m9971r() {
        if (this.f9034g.equals("privacyPassword")) {
            if (Globals.m7388i0().m7468P0() == 0) {
                return true;
            }
        } else if (Globals.m7388i0().m7524b1() == 0 && !Globals.m7388i0().m7503W1().booleanValue()) {
            return true;
        }
        return false;
    }

    /* renamed from: s */
    public final void m9972s() {
        finish();
    }

    /* renamed from: u */
    public final void m9973u() {
        if (m9971r()) {
            this.f9031d.setSelected(false);
            this.f9032e.setVisibility(8);
            this.f9033f.setVisibility(8);
        } else {
            this.f9031d.setSelected(true);
            this.f9032e.setVisibility(0);
            if (this.f9034g.equals("privacyPassword")) {
                this.f9033f.setVisibility(0);
            }
        }
    }
}
