package com.cyberlink.you.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.cyberlink.p030U.R;
import com.cyberlink.you.BaseActivity;
import com.cyberlink.you.Globals;
import com.cyberlink.you.activity.ulauncher.ULauncherActivity;
import com.cyberlink.you.friends.FriendsClient;
import com.cyberlink.you.utility.CLUtility;
import net.sqlcipher.database.SQLiteDatabase;
import p015b4.C0681k;

/* loaded from: classes.dex */
public class ResetPasswordActivity extends BaseActivity {

    /* renamed from: v */
    public static final String f8693v = "ResetPasswordActivity";

    /* renamed from: c */
    public TextView f8694c;

    /* renamed from: d */
    public TextView f8695d;

    /* renamed from: e */
    public TextView f8696e;

    /* renamed from: f */
    public TextView f8697f;

    /* renamed from: g */
    public TextView f8698g;

    /* renamed from: h */
    public ProgressBar f8699h;

    /* renamed from: i */
    public FriendsClient f8700i;

    /* renamed from: j */
    public String f8701j;

    /* renamed from: k */
    public String f8702k;

    /* renamed from: l */
    public final int f8703l = 1;

    /* renamed from: m */
    public final int f8704m = 2;

    /* renamed from: n */
    public final int f8705n = 3;

    /* renamed from: o */
    public final String f8706o = "android.intent.category.APP_EMAIL";

    /* renamed from: p */
    public C0681k.a<String> f8707p = new C1662a();

    /* renamed from: q */
    public C0681k.a<String> f8708q = new C1663b();

    /* renamed from: r */
    public View.OnClickListener f8709r = new ViewOnClickListenerC1664c();

    /* renamed from: s */
    public View.OnClickListener f8710s = new ViewOnClickListenerC1665d();

    /* renamed from: t */
    public View.OnClickListener f8711t = new ViewOnClickListenerC1666e();

    /* renamed from: u */
    public View.OnClickListener f8712u = new ViewOnClickListenerC1667f();

    /* renamed from: com.cyberlink.you.activity.ResetPasswordActivity$a */
    public class C1662a implements C0681k.a<String> {
        public C1662a() {
        }

        @Override // p015b4.C0681k.a
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onSuccess(String str) {
            Log.d(ResetPasswordActivity.f8693v, "[resetEmailPassword] resetPasswordListener onSuccess");
            ResetPasswordActivity.this.f8699h.setVisibility(8);
            String strM7449L = Globals.m7388i0().m7449L();
            if (strM7449L == null || strM7449L.equals("")) {
                Intent intent = new Intent(ResetPasswordActivity.this, (Class<?>) RegisterActivity.class);
                intent.setFlags(67108864);
                ResetPasswordActivity.this.startActivity(intent);
            } else {
                if (ResetPasswordActivity.this.getCallingActivity() == null) {
                    Intent intent2 = new Intent(ResetPasswordActivity.this, (Class<?>) ULauncherActivity.class);
                    intent2.setFlags(268468224);
                    ResetPasswordActivity.this.startActivity(intent2);
                    ResetPasswordActivity.this.finish();
                }
                ResetPasswordActivity.this.finish();
            }
        }

        @Override // p015b4.C0681k.a
        public void onError(String str) {
            Log.d(ResetPasswordActivity.f8693v, "[resetEmailPassword] resetPasswordListener onError" + str);
            ResetPasswordActivity.this.f8699h.setVisibility(8);
            ResetPasswordActivity resetPasswordActivity = ResetPasswordActivity.this;
            Toast.makeText(resetPasswordActivity, C0681k.m3399e(resetPasswordActivity, str), 1).show();
        }
    }

    /* renamed from: com.cyberlink.you.activity.ResetPasswordActivity$b */
    public class C1663b implements C0681k.a<String> {
        public C1663b() {
        }

        @Override // p015b4.C0681k.a
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onSuccess(String str) {
            Log.d(ResetPasswordActivity.f8693v, "SendResetRequestListener onSuccess");
            ResetPasswordActivity.this.f8699h.setVisibility(8);
            ResetPasswordActivity.this.m9618i(2);
        }

        @Override // p015b4.C0681k.a
        public void onError(String str) {
            Log.d(ResetPasswordActivity.f8693v, "SendResetRequestListener onError" + str);
            ResetPasswordActivity.this.f8699h.setVisibility(8);
            ResetPasswordActivity resetPasswordActivity = ResetPasswordActivity.this;
            Toast.makeText(resetPasswordActivity, C0681k.m3399e(resetPasswordActivity, str), 1).show();
        }
    }

    /* renamed from: com.cyberlink.you.activity.ResetPasswordActivity$c */
    public class ViewOnClickListenerC1664c implements View.OnClickListener {
        public ViewOnClickListenerC1664c() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ResetPasswordActivity.this.m9615E();
        }
    }

    /* renamed from: com.cyberlink.you.activity.ResetPasswordActivity$d */
    public class ViewOnClickListenerC1665d implements View.OnClickListener {
        public ViewOnClickListenerC1665d() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ResetPasswordActivity resetPasswordActivity = ResetPasswordActivity.this;
            resetPasswordActivity.f8701j = resetPasswordActivity.f8694c.getText().toString();
            if (CLUtility.m16605x1(ResetPasswordActivity.this.f8701j)) {
                C0681k.m3397c(ResetPasswordActivity.this.f8701j, ResetPasswordActivity.this.f8708q);
                ResetPasswordActivity.this.f8699h.setVisibility(0);
            } else {
                ResetPasswordActivity resetPasswordActivity2 = ResetPasswordActivity.this;
                Toast.makeText(resetPasswordActivity2, resetPasswordActivity2.getString(R.string.empty_email_warning), 1).show();
            }
        }
    }

    /* renamed from: com.cyberlink.you.activity.ResetPasswordActivity$e */
    public class ViewOnClickListenerC1666e implements View.OnClickListener {
        public ViewOnClickListenerC1666e() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ResetPasswordActivity.this.m9616H();
        }
    }

    /* renamed from: com.cyberlink.you.activity.ResetPasswordActivity$f */
    public class ViewOnClickListenerC1667f implements View.OnClickListener {
        public ViewOnClickListenerC1667f() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            String string = ResetPasswordActivity.this.f8696e.getText().toString();
            if (ResetPasswordActivity.this.m9614D(string, ResetPasswordActivity.this.f8697f.getText().toString())) {
                C0681k.m3402h(ResetPasswordActivity.this.f8702k, string, ResetPasswordActivity.this.f8707p);
                ResetPasswordActivity.this.f8699h.setVisibility(0);
            }
        }
    }

    /* renamed from: C */
    public final void m9613C() {
        this.f8699h = (ProgressBar) findViewById(R.id.loading);
        this.f8694c = (TextView) findViewById(R.id.EmailEditText);
        this.f8695d = (TextView) findViewById(R.id.CheckEmailInfo);
        this.f8696e = (TextView) findViewById(R.id.resetPasswordEditText);
        this.f8697f = (TextView) findViewById(R.id.resetPasswordCheckEditText);
        this.f8698g = (TextView) findViewById(R.id.wrongInputTextView);
        findViewById(R.id.RegistrationBackBtn).setOnClickListener(this.f8709r);
    }

    /* renamed from: D */
    public final boolean m9614D(String str, String str2) {
        CLUtility.m16602w2(this, false);
        if (str.length() < 4) {
            m9617I(getString(R.string.registration_email_password_rule));
            return false;
        }
        if (str.equals(str2)) {
            return true;
        }
        m9617I(getString(R.string.registration_email_password_dont_match));
        return false;
    }

    /* renamed from: E */
    public final void m9615E() {
        CLUtility.m16602w2(this, false);
        setResult(-1, new Intent(this, (Class<?>) EmailConnectActivity.class));
        finish();
    }

    /* renamed from: H */
    public final void m9616H() {
        Intent intentMakeMainSelectorActivity = Intent.makeMainSelectorActivity("android.intent.action.MAIN", "android.intent.category.APP_EMAIL");
        intentMakeMainSelectorActivity.addFlags(SQLiteDatabase.CREATE_IF_NECESSARY);
        startActivity(intentMakeMainSelectorActivity);
    }

    /* renamed from: I */
    public final void m9617I(String str) {
        this.f8698g.setVisibility(0);
        this.f8698g.setText(str);
    }

    /* renamed from: i */
    public final void m9618i(int i9) {
        CLUtility.m16602w2(this, false);
        if (i9 == 1) {
            findViewById(R.id.InputEmailBtn).setOnClickListener(this.f8710s);
            return;
        }
        if (i9 == 2) {
            findViewById(R.id.InputEmailLayout).setVisibility(8);
            findViewById(R.id.CheckEmailLayout).setVisibility(0);
            this.f8695d.setText(this.f8701j);
            findViewById(R.id.CheckEmailBtn).setOnClickListener(this.f8711t);
            return;
        }
        if (i9 == 3) {
            findViewById(R.id.CheckEmailLayout).setVisibility(8);
            findViewById(R.id.InputEmailLayout).setVisibility(8);
            findViewById(R.id.resetPasswordLayout).setVisibility(0);
            findViewById(R.id.resetPasswordBtn).setOnClickListener(this.f8712u);
        }
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_regemail_forget_password);
        this.f8700i = new FriendsClient(true);
        m9613C();
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        this.f8700i.m15717U0();
    }

    @Override // android.app.Activity
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onStart() {
        super.onStart();
        Uri data = getIntent().getData();
        if (data == null || !data.getHost().equals("resetEmailPassword")) {
            m9618i(1);
            return;
        }
        String str = f8693v;
        Log.d(str, "[resetEmailPassword] url = " + data);
        this.f8702k = data.getQueryParameter("accountToken");
        Log.d(str, "[resetEmailPassword] sAccountToken = " + this.f8702k);
        m9618i(3);
    }
}
