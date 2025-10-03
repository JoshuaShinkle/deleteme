package com.cyberlink.you.activity.unbind;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import com.cyberlink.p030U.R;
import com.cyberlink.you.BaseActivity;
import com.cyberlink.you.Globals;
import com.cyberlink.you.activity.ResetPasswordActivity;
import com.cyberlink.you.activity.ulauncher.ULauncherActivity;
import com.cyberlink.you.friends.FriendsClient;
import com.cyberlink.you.friends.UserInfo;
import com.cyberlink.you.utility.CLUtility;
import p116k4.C5170o0;
import p116k4.C5187v0;
import p209u2.C6385v;

/* loaded from: classes.dex */
public class UnlinkEmailActivity extends BaseActivity {

    /* renamed from: d */
    public TextView f11762d;

    /* renamed from: e */
    public TextView f11763e;

    /* renamed from: f */
    public TextView f11764f;

    /* renamed from: g */
    public TextView f11765g;

    /* renamed from: h */
    public EditText f11766h;

    /* renamed from: i */
    public ProgressBar f11767i;

    /* renamed from: j */
    public FriendsClient f11768j;

    /* renamed from: k */
    public ScrollView f11769k;

    /* renamed from: l */
    public ScrollView f11770l;

    /* renamed from: m */
    public Button f11771m;

    /* renamed from: o */
    public AbstractC2568e f11773o;

    /* renamed from: c */
    public String f11761c = "UnlinkEmailActivity";

    /* renamed from: n */
    public final int f11772n = 0;

    /* renamed from: p */
    public View.OnClickListener f11774p = new ViewOnClickListenerC2564a();

    /* renamed from: q */
    public View.OnClickListener f11775q = new ViewOnClickListenerC2565b();

    /* renamed from: r */
    public View.OnClickListener f11776r = new ViewOnClickListenerC2566c();

    public enum Page {
        EmailInfo,
        UnlinkEmail
    }

    /* renamed from: com.cyberlink.you.activity.unbind.UnlinkEmailActivity$a */
    public class ViewOnClickListenerC2564a implements View.OnClickListener {
        public ViewOnClickListenerC2564a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            UnlinkEmailActivity.this.m13479u();
        }
    }

    /* renamed from: com.cyberlink.you.activity.unbind.UnlinkEmailActivity$b */
    public class ViewOnClickListenerC2565b implements View.OnClickListener {
        public ViewOnClickListenerC2565b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            UnlinkEmailActivity.this.m13480v(Page.UnlinkEmail);
        }
    }

    /* renamed from: com.cyberlink.you.activity.unbind.UnlinkEmailActivity$c */
    public class ViewOnClickListenerC2566c implements View.OnClickListener {
        public ViewOnClickListenerC2566c() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            UnlinkEmailActivity.this.startActivityForResult(new Intent(UnlinkEmailActivity.this, (Class<?>) ResetPasswordActivity.class), 0);
        }
    }

    /* renamed from: com.cyberlink.you.activity.unbind.UnlinkEmailActivity$e */
    public abstract class AbstractC2568e {
        public AbstractC2568e() {
        }

        /* renamed from: a */
        public abstract void mo13486a();

        /* renamed from: b */
        public abstract void mo13487b();

        public /* synthetic */ AbstractC2568e(UnlinkEmailActivity unlinkEmailActivity, ViewOnClickListenerC2564a viewOnClickListenerC2564a) {
            this();
        }
    }

    public enum verifyPasswordStatus {
        NONE,
        VERIFY_SUCCESS,
        WRONG_PASSWORD,
        SERVER_NETWORK_ERROR
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_email_unlink);
        this.f11768j = new FriendsClient(true);
        this.f11773o = m13477r();
        m13478s();
        m13480v(Page.EmailInfo);
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        this.f11768j.m15717U0();
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        this.f11773o.mo13486a();
    }

    /* renamed from: r */
    public final AbstractC2568e m13477r() {
        return new C2567d(this, null);
    }

    /* renamed from: s */
    public final void m13478s() {
        this.f11762d = (TextView) findViewById(R.id.UnlinkEmailTitleTextView);
        this.f11767i = (ProgressBar) findViewById(R.id.loading);
        this.f11763e = (TextView) findViewById(R.id.RegEmailForgetPasswordBtn);
        this.f11764f = (TextView) findViewById(R.id.EmailInfo);
        this.f11766h = (EditText) findViewById(R.id.PasswordEditText);
        this.f11769k = (ScrollView) findViewById(R.id.EmailInfoArea);
        this.f11770l = (ScrollView) findViewById(R.id.UnlinkEmailArea);
        this.f11765g = (TextView) findViewById(R.id.unlinkEmailInfo);
        findViewById(R.id.UnlinkEmailBackBtn).setOnClickListener(this.f11774p);
        this.f11771m = (Button) findViewById(R.id.EmailUnlinkContinueBtn);
        this.f11773o.mo13487b();
    }

    /* renamed from: u */
    public final void m13479u() {
        CLUtility.m16602w2(this, false);
        if (getCallingActivity() != null) {
            setResult(-1, new Intent());
            finish();
        } else {
            Intent intent = new Intent(this, (Class<?>) ULauncherActivity.class);
            intent.setFlags(268468224);
            startActivity(intent);
            finish();
        }
    }

    /* renamed from: v */
    public final void m13480v(Page page) {
        if (page == Page.EmailInfo) {
            this.f11762d.setText(getString(R.string.friends_invitation_selector_string_email));
            this.f11769k.setVisibility(0);
            this.f11770l.setVisibility(8);
            if (CLUtility.m16593u1()) {
                this.f11771m.setVisibility(0);
                this.f11771m.setOnClickListener(this.f11775q);
            } else {
                this.f11771m.setVisibility(8);
            }
        } else if (page == Page.UnlinkEmail) {
            this.f11762d.setText(getString(R.string.email_unlink_title));
            this.f11769k.setVisibility(8);
            this.f11770l.setVisibility(0);
            this.f11763e.setOnClickListener(this.f11776r);
            this.f11763e.setVisibility(0);
        }
        m13481w(page);
    }

    /* renamed from: w */
    public final void m13481w(Page page) {
        if (Globals.m7388i0().m7583n0()) {
            if (page == Page.EmailInfo) {
                this.f11764f.setText(Globals.m7388i0().m7498V0());
            } else if (page == Page.UnlinkEmail) {
                this.f11765g.setText(Globals.m7388i0().m7498V0());
            }
        }
    }

    /* renamed from: com.cyberlink.you.activity.unbind.UnlinkEmailActivity$d */
    public class C2567d extends AbstractC2568e {

        /* renamed from: b */
        public c f11783b;

        /* renamed from: c */
        public b f11784c;

        /* renamed from: d */
        public View.OnClickListener f11785d;

        /* renamed from: com.cyberlink.you.activity.unbind.UnlinkEmailActivity$d$a */
        public class a implements View.OnClickListener {
            public a() {
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                String string = UnlinkEmailActivity.this.f11766h.getText().toString();
                if (C5170o0.m20170e(string) || string.length() < 4) {
                    C5187v0.m20267c(R.string.registration_email_password_rule);
                    return;
                }
                C2567d.this.f11783b = C2567d.this.new c(Globals.m7388i0().m7498V0(), string);
                C2567d.this.f11783b.executeOnExecutor(C6385v.f21554b, new Void[0]);
            }
        }

        /* renamed from: com.cyberlink.you.activity.unbind.UnlinkEmailActivity$d$b */
        public class b extends AsyncTask<Void, Void, Boolean> {
            public b() {
            }

            @Override // android.os.AsyncTask
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public Boolean doInBackground(Void... voidArr) {
                Thread.currentThread().setName("updateGroupMember4AddNewMember AsyncTask");
                UserInfo userInfoM16497V0 = CLUtility.m16497V0(UnlinkEmailActivity.this);
                return (userInfoM16497V0 == null || UnlinkEmailActivity.this.f11768j == null) ? Boolean.FALSE : Boolean.valueOf(UnlinkEmailActivity.this.f11768j.m15720Z0(userInfoM16497V0));
            }

            @Override // android.os.AsyncTask
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public void onPostExecute(Boolean bool) {
                if (!bool.booleanValue()) {
                    C5187v0.m20267c(R.string.ulink_unsuccessful);
                    return;
                }
                Globals.m7388i0().m7501V3("");
                Globals.m7388i0().m7564j3(false);
                C5187v0.m20267c(R.string.ulink_successful);
                CLUtility.m16602w2(UnlinkEmailActivity.this, false);
                UnlinkEmailActivity.this.finish();
            }

            public /* synthetic */ b(C2567d c2567d, ViewOnClickListenerC2564a viewOnClickListenerC2564a) {
                this();
            }
        }

        /* renamed from: com.cyberlink.you.activity.unbind.UnlinkEmailActivity$d$c */
        public class c extends AsyncTask<Void, Void, verifyPasswordStatus> {

            /* renamed from: a */
            public String f11789a;

            /* renamed from: b */
            public String f11790b;

            public c(String str, String str2) {
                this.f11789a = str;
                this.f11790b = str2;
            }

            @Override // android.os.AsyncTask
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public verifyPasswordStatus doInBackground(Void... voidArr) {
                Thread.currentThread().setName("VerifyEmailPassword AsyncTask");
                return (this.f11789a == null || this.f11790b == null || UnlinkEmailActivity.this.f11768j == null) ? verifyPasswordStatus.NONE : UnlinkEmailActivity.this.f11768j.m15725c1(this.f11789a, this.f11790b);
            }

            @Override // android.os.AsyncTask
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public void onPostExecute(verifyPasswordStatus verifypasswordstatus) {
                if (verifypasswordstatus != null) {
                    if (verifypasswordstatus.equals(verifyPasswordStatus.VERIFY_SUCCESS)) {
                        C2567d c2567d = C2567d.this;
                        c2567d.f11784c = new b(c2567d, null);
                        C2567d.this.f11784c.executeOnExecutor(C6385v.f21554b, new Void[0]);
                    } else if (verifypasswordstatus.equals(verifyPasswordStatus.WRONG_PASSWORD)) {
                        Log.d(UnlinkEmailActivity.this.f11761c, "verifyEmailPasswordTask Fail: wrong password.");
                        C5187v0.m20267c(R.string.registration_email_password_dont_match);
                    } else {
                        Log.d(UnlinkEmailActivity.this.f11761c, "verifyEmailPasswordTask Fail: please check the server status or network.");
                        C5187v0.m20267c(R.string.error_server_response);
                    }
                }
            }
        }

        public C2567d() {
            super(UnlinkEmailActivity.this, null);
            this.f11785d = new a();
        }

        @Override // com.cyberlink.you.activity.unbind.UnlinkEmailActivity.AbstractC2568e
        /* renamed from: a */
        public void mo13486a() {
            UnlinkEmailActivity.this.f11767i.setVisibility(8);
        }

        @Override // com.cyberlink.you.activity.unbind.UnlinkEmailActivity.AbstractC2568e
        /* renamed from: b */
        public void mo13487b() {
            UnlinkEmailActivity.this.findViewById(R.id.EmailConnectContinueBtn).setOnClickListener(this.f11785d);
            UnlinkEmailActivity.this.f11763e.setVisibility(8);
        }

        public /* synthetic */ C2567d(UnlinkEmailActivity unlinkEmailActivity, ViewOnClickListenerC2564a viewOnClickListenerC2564a) {
            this();
        }
    }
}
