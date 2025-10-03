package com.cyberlink.you.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.util.Pair;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.cyberlink.p030U.R;
import com.cyberlink.you.BaseActivity;
import com.cyberlink.you.Globals;
import com.cyberlink.you.activity.ulauncher.ULauncherActivity;
import com.cyberlink.you.friends.FriendsClient;
import com.cyberlink.you.p036ui.C3123g;
import com.cyberlink.you.utility.CLUtility;
import com.cyberlink.you.utility.ULogUtility;
import com.google.android.gms.common.Scopes;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;
import p015b4.AsyncTaskC0682l;
import p015b4.C0681k;
import p116k4.C5154j;
import p116k4.C5170o0;
import p201t3.C6301o;
import p209u2.C6385v;

/* loaded from: classes.dex */
public class SignInCLAccountActivity extends BaseActivity {

    /* renamed from: c */
    public ProgressBar f9087c;

    /* renamed from: d */
    public FriendsClient f9088d;

    /* renamed from: e */
    public AutoCompleteTextView f9089e;

    /* renamed from: f */
    public TextView f9090f;

    /* renamed from: g */
    public String f9091g;

    /* renamed from: h */
    public String f9092h;

    /* renamed from: i */
    public Button f9093i;

    /* renamed from: j */
    public Button f9094j;

    /* renamed from: k */
    public Button f9095k;

    /* renamed from: l */
    public AsyncTask<Void, Void, Boolean> f9096l;

    /* renamed from: m */
    public String f9097m;

    /* renamed from: n */
    public View.OnClickListener f9098n = new ViewOnClickListenerC1740a();

    /* renamed from: o */
    public View.OnClickListener f9099o = new View.OnClickListener() { // from class: com.cyberlink.you.activity.yf
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f12281b.m10062k0(view);
        }
    };

    /* renamed from: p */
    public View.OnClickListener f9100p = new ViewOnClickListenerC1741b();

    /* renamed from: q */
    public C0681k.a<String> f9101q = new C1744e();

    /* renamed from: r */
    public AsyncTaskC0682l.a f9102r = new C1745f();

    /* renamed from: s */
    public TextWatcher f9103s = new C1746g();

    /* renamed from: com.cyberlink.you.activity.SignInCLAccountActivity$a */
    public class ViewOnClickListenerC1740a implements View.OnClickListener {
        public ViewOnClickListenerC1740a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            Intent intent = new Intent(SignInCLAccountActivity.this, (Class<?>) ResetCLAccountPasswordActivity.class);
            intent.putExtra("registerType", 5);
            if (SignInCLAccountActivity.this.f9089e != null && CLUtility.m16605x1(SignInCLAccountActivity.this.f9089e.getText().toString().trim())) {
                intent.putExtra("registerEmail", SignInCLAccountActivity.this.f9089e.getText().toString());
            }
            SignInCLAccountActivity.this.startActivity(intent);
            SignInCLAccountActivity.this.finish();
        }
    }

    /* renamed from: com.cyberlink.you.activity.SignInCLAccountActivity$b */
    public class ViewOnClickListenerC1741b implements View.OnClickListener {
        public ViewOnClickListenerC1741b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            CLUtility.m16589t1(SignInCLAccountActivity.this);
            SignInCLAccountActivity signInCLAccountActivity = SignInCLAccountActivity.this;
            signInCLAccountActivity.f9091g = signInCLAccountActivity.f9089e.getText().toString();
            SignInCLAccountActivity signInCLAccountActivity2 = SignInCLAccountActivity.this;
            signInCLAccountActivity2.f9092h = signInCLAccountActivity2.f9090f.getText().toString();
            if (!CLUtility.m16605x1(SignInCLAccountActivity.this.f9091g.trim())) {
                SignInCLAccountActivity signInCLAccountActivity3 = SignInCLAccountActivity.this;
                signInCLAccountActivity3.m10087s0(signInCLAccountActivity3.getString(R.string.registration_email_invalid));
            } else if (SignInCLAccountActivity.this.f9092h.length() < 6) {
                SignInCLAccountActivity signInCLAccountActivity4 = SignInCLAccountActivity.this;
                signInCLAccountActivity4.m10087s0(signInCLAccountActivity4.getString(R.string.registration_email_password_range_rule, 6, 20));
            } else {
                SignInCLAccountActivity.this.f9087c.setVisibility(0);
                SignInCLAccountActivity.this.m10079X();
            }
        }
    }

    /* renamed from: com.cyberlink.you.activity.SignInCLAccountActivity$c */
    public class AsyncTaskC1742c extends AsyncTask<Void, Void, Void> {
        public AsyncTaskC1742c() {
        }

        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public Void doInBackground(Void... voidArr) throws JSONException {
            Thread.currentThread().setName("BindDouAccount AsyncTask");
            ArrayList arrayList = new ArrayList();
            arrayList.add(new C6301o(Scopes.EMAIL, SignInCLAccountActivity.this.f9091g));
            arrayList.add(new C6301o("password", SignInCLAccountActivity.this.f9092h));
            arrayList.add(new C6301o("token", Globals.m7388i0().m7449L()));
            FriendsClient unused = SignInCLAccountActivity.this.f9088d;
            Pair<String, String> pairM15676n = FriendsClient.m15676n("user", "bindDou", arrayList);
            String str = (String) pairM15676n.first;
            String str2 = (String) pairM15676n.second;
            if (str == null) {
                SignInCLAccountActivity signInCLAccountActivity = SignInCLAccountActivity.this;
                signInCLAccountActivity.m10087s0(signInCLAccountActivity.getString(R.string.feedback_unknown_error));
                return null;
            }
            switch (str) {
                case "200":
                    try {
                        String string = new JSONObject(str2).getString("accountToken");
                        if (string != null && !string.isEmpty()) {
                            SignInCLAccountActivity.this.m10080Y(string);
                            break;
                        }
                    } catch (JSONException e9) {
                        e9.printStackTrace();
                        return null;
                    }
                    break;
                case "403":
                    SignInCLAccountActivity.this.m10081Z();
                    break;
                case "423":
                    SignInCLAccountActivity.this.m10086r0();
                    break;
                default:
                    SignInCLAccountActivity.this.m10088u0(str);
                    break;
            }
            return null;
        }

        @Override // android.os.AsyncTask
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(Void r12) {
        }
    }

    /* renamed from: com.cyberlink.you.activity.SignInCLAccountActivity$d */
    public class AsyncTaskC1743d extends AsyncTask<Void, Void, Boolean> {

        /* renamed from: a */
        public final /* synthetic */ String f9107a;

        /* renamed from: com.cyberlink.you.activity.SignInCLAccountActivity$d$a */
        public class a extends AsyncTask<Void, Void, Boolean> {

            /* renamed from: a */
            public final /* synthetic */ String f9109a;

            public a(String str) {
                this.f9109a = str;
            }

            @Override // android.os.AsyncTask
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public Boolean doInBackground(Void... voidArr) throws JSONException {
                String strM7493U0 = Globals.m7388i0().m7493U0();
                if (SignInCLAccountActivity.this.f9088d != null) {
                    FriendsClient unused = SignInCLAccountActivity.this.f9088d;
                    FriendsClient.m15641F0(strM7493U0, this.f9109a);
                }
                return Boolean.TRUE;
            }

            @Override // android.os.AsyncTask
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public void onPostExecute(Boolean bool) {
                Globals.m7388i0().m7601q4(Boolean.FALSE);
                FriendsClient friendsClient = SignInCLAccountActivity.this.f9088d;
                SignInCLAccountActivity signInCLAccountActivity = SignInCLAccountActivity.this;
                new AsyncTaskC0682l(friendsClient, signInCLAccountActivity, signInCLAccountActivity.f9102r).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, this.f9109a);
            }
        }

        public AsyncTaskC1743d(String str) {
            this.f9107a = str;
        }

        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public Boolean doInBackground(Void... voidArr) throws JSONException {
            boolean z8;
            Thread.currentThread().setName("BindUAccount AsyncTask");
            ArrayList arrayList = new ArrayList();
            arrayList.add(new C6301o("token", Globals.m7388i0().m7449L()));
            arrayList.add(new C6301o("accountSource", "DoU"));
            arrayList.add(new C6301o("accountToken", this.f9107a));
            FriendsClient unused = SignInCLAccountActivity.this.f9088d;
            Pair<String, String> pairM15676n = FriendsClient.m15676n("user", "bindAccount", arrayList);
            String str = (String) pairM15676n.first;
            String str2 = (String) pairM15676n.second;
            if (str != null && str.equals("200")) {
                try {
                    String string = new JSONObject(str2).getJSONObject("result").getString("douToken");
                    ULogUtility.m16688x("Bind Dou token", "setDoUToken : " + string);
                    Globals.m7388i0().m7447K2(string);
                    Globals.m7388i0().m7400A2(System.currentTimeMillis() + 1814400000);
                    z8 = true;
                } catch (JSONException e9) {
                    e9.printStackTrace();
                }
                return Boolean.valueOf(z8);
            }
            SignInCLAccountActivity.this.m10088u0(C0681k.m3398d(str2));
            z8 = false;
            return Boolean.valueOf(z8);
        }

        @Override // android.os.AsyncTask
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(Boolean bool) {
            if (bool.booleanValue()) {
                String strM7449L = Globals.m7388i0().m7449L();
                if (Globals.m7388i0().m7610s1().booleanValue()) {
                    SignInCLAccountActivity.this.f9096l = new a(strM7449L);
                    SignInCLAccountActivity.this.f9096l.executeOnExecutor(C6385v.f21554b, new Void[0]);
                } else {
                    FriendsClient friendsClient = SignInCLAccountActivity.this.f9088d;
                    SignInCLAccountActivity signInCLAccountActivity = SignInCLAccountActivity.this;
                    new AsyncTaskC0682l(friendsClient, signInCLAccountActivity, signInCLAccountActivity.f9102r).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, strM7449L);
                }
            }
        }
    }

    /* renamed from: com.cyberlink.you.activity.SignInCLAccountActivity$e */
    public class C1744e implements C0681k.a<String> {
        public C1744e() {
        }

        @Override // p015b4.C0681k.a
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onSuccess(String str) {
            Intent intent = new Intent(SignInCLAccountActivity.this, (Class<?>) VerificationCLAccountActivity.class);
            intent.putExtra("registerEmail", SignInCLAccountActivity.this.f9091g);
            intent.putExtra("password", SignInCLAccountActivity.this.f9092h);
            intent.putExtra("registerType", 5);
            SignInCLAccountActivity.this.startActivity(intent);
            SignInCLAccountActivity.this.finish();
        }

        @Override // p015b4.C0681k.a
        public void onError(String str) {
            SignInCLAccountActivity.this.f9087c.setVisibility(8);
            SignInCLAccountActivity.this.m10088u0(str);
        }
    }

    /* renamed from: com.cyberlink.you.activity.SignInCLAccountActivity$f */
    public class C1745f implements AsyncTaskC0682l.a {
        public C1745f() {
        }

        @Override // p015b4.AsyncTaskC0682l.a
        public void onComplete() {
            if (SignInCLAccountActivity.this.f9087c != null) {
                SignInCLAccountActivity.this.f9087c.setVisibility(8);
            }
            Intent intent = new Intent(SignInCLAccountActivity.this.getApplicationContext(), (Class<?>) ULauncherActivity.class);
            intent.setFlags(268468224);
            SignInCLAccountActivity.this.startActivity(intent);
            SignInCLAccountActivity.this.finish();
        }
    }

    /* renamed from: com.cyberlink.you.activity.SignInCLAccountActivity$g */
    public class C1746g implements TextWatcher {
        public C1746g() {
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
            SignInCLAccountActivity.this.f9093i.setEnabled(SignInCLAccountActivity.this.f9089e.length() > 0 && SignInCLAccountActivity.this.f9090f.length() > 0);
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i9, int i10, int i11) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i9, int i10, int i11) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: e0 */
    public /* synthetic */ void m10055e0(DialogInterface dialogInterface, int i9) {
        dialogInterface.dismiss();
        ProgressBar progressBar = this.f9087c;
        if (progressBar != null) {
            progressBar.setVisibility(0);
        }
        C0681k.m3403i(this.f9091g, this.f9101q);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: g0 */
    public /* synthetic */ void m10056g0() {
        ProgressBar progressBar = this.f9087c;
        if (progressBar != null) {
            progressBar.setVisibility(8);
        }
        AlertDialog.Builder builderM16382a = C3123g.m16382a(m10082b0());
        builderM16382a.setMessage(Html.fromHtml(m10082b0().getString(R.string.exist_cl_account_not_activate, this.f9091g), 0));
        builderM16382a.setPositiveButton(m10082b0().getString(R.string.activate_email), new DialogInterface.OnClickListener() { // from class: com.cyberlink.you.activity.fg
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i9) {
                this.f10445b.m10055e0(dialogInterface, i9);
            }
        });
        AlertDialog alertDialogCreate = builderM16382a.create();
        alertDialogCreate.show();
        TextView textView = (TextView) alertDialogCreate.findViewById(android.R.id.message);
        if (textView != null) {
            textView.setTextSize(0, Globals.m7374X0().getDimensionPixelSize(R.dimen.t16dp));
            textView.setGravity(17);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: i0 */
    public /* synthetic */ void m10058i0(View view) {
        CLUtility.m16589t1(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: j0 */
    public /* synthetic */ void m10060j0(View view) {
        m10085q0();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: k0 */
    public /* synthetic */ void m10062k0(View view) {
        Intent intent = new Intent(getApplicationContext(), (Class<?>) EmailConnectActivity.class);
        intent.putExtra("registerType", 4);
        startActivity(intent);
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: m0 */
    public /* synthetic */ void m10065m0(DialogInterface dialogInterface, int i9) {
        try {
            startActivity(new Intent("android.intent.action.VIEW", Uri.parse(getString(R.string.sign_up_learn_more_url))));
        } catch (ActivityNotFoundException e9) {
            C5154j.m20076j(e9);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: n0 */
    public /* synthetic */ void m10067n0() {
        ULogUtility.m16688x("Sign in CL account", "show blocked email dialog");
        ProgressBar progressBar = this.f9087c;
        if (progressBar != null) {
            progressBar.setVisibility(8);
        }
        AlertDialog.Builder builderM16382a = C3123g.m16382a(m10082b0());
        builderM16382a.setMessage(Html.fromHtml(m10082b0().getString(R.string.invalid_email_address, this.f9091g), 0));
        builderM16382a.setPositiveButton(R.string.cancel, new DialogInterface.OnClickListener() { // from class: com.cyberlink.you.activity.dg
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i9) {
                dialogInterface.dismiss();
            }
        });
        builderM16382a.setNegativeButton(R.string.sign_up_learn_more, new DialogInterface.OnClickListener() { // from class: com.cyberlink.you.activity.eg
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i9) {
                this.f10410b.m10065m0(dialogInterface, i9);
            }
        });
        builderM16382a.setCancelable(false);
        AlertDialog alertDialogCreate = builderM16382a.create();
        alertDialogCreate.show();
        TextView textView = (TextView) alertDialogCreate.findViewById(android.R.id.message);
        if (textView != null) {
            textView.setTextSize(0, Globals.m7374X0().getDimensionPixelSize(R.dimen.t17dp));
            textView.setGravity(3);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: o0 */
    public /* synthetic */ void m10069o0(String str) {
        ProgressBar progressBar = this.f9087c;
        if (progressBar != null) {
            progressBar.setVisibility(8);
        }
        Toast.makeText(this, str, 1).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: p0 */
    public /* synthetic */ void m10070p0(String str) {
        ProgressBar progressBar = this.f9087c;
        if (progressBar != null) {
            progressBar.setVisibility(8);
        }
        Toast.makeText(this, C0681k.m3399e(this, str), 1).show();
    }

    /* renamed from: X */
    public final void m10079X() {
        new AsyncTaskC1742c().executeOnExecutor(C6385v.f21554b, new Void[0]);
    }

    /* renamed from: Y */
    public final void m10080Y(String str) {
        new AsyncTaskC1743d(str).executeOnExecutor(C6385v.f21554b, new Void[0]);
    }

    /* renamed from: Z */
    public final void m10081Z() {
        runOnUiThread(new Runnable() { // from class: com.cyberlink.you.activity.ag
            @Override // java.lang.Runnable
            public final void run() {
                this.f9724b.m10056g0();
            }
        });
    }

    /* renamed from: b0 */
    public final Activity m10082b0() {
        return this;
    }

    /* renamed from: c0 */
    public final void m10083c0() {
        AutoCompleteTextView autoCompleteTextView;
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            if (extras.containsKey("activityName")) {
                this.f9097m = extras.getString("activityName", "");
            }
            if (extras.containsKey("registerEmail")) {
                this.f9091g = extras.getString("registerEmail", "");
            }
            if (C5170o0.m20170e(this.f9091g) || (autoCompleteTextView = this.f9089e) == null) {
                return;
            }
            autoCompleteTextView.setText(this.f9091g);
            TextView textView = this.f9090f;
            if (textView != null) {
                textView.requestFocus();
            }
        }
    }

    /* renamed from: d0 */
    public final void m10084d0() {
        findViewById(R.id.SignInCLAccountArea).setOnClickListener(new View.OnClickListener() { // from class: com.cyberlink.you.activity.wf
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f12231b.m10058i0(view);
            }
        });
        findViewById(R.id.SignInCLAccountBackBtn).setOnClickListener(new View.OnClickListener() { // from class: com.cyberlink.you.activity.xf
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f12255b.m10060j0(view);
            }
        });
        AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.SignInCLAccountEmailEditText);
        this.f9089e = autoCompleteTextView;
        autoCompleteTextView.addTextChangedListener(this.f9103s);
        TextView textView = (TextView) findViewById(R.id.SignInCLAccountPasswordEditText);
        this.f9090f = textView;
        textView.addTextChangedListener(this.f9103s);
        this.f9087c = (ProgressBar) findViewById(R.id.loading);
        Button button = (Button) findViewById(R.id.SignInCLAccountSignIn);
        this.f9093i = button;
        button.setOnClickListener(this.f9100p);
        this.f9093i.setEnabled(false);
        Button button2 = (Button) findViewById(R.id.SignInCLAccountForgotPassword);
        this.f9095k = button2;
        button2.setOnClickListener(this.f9098n);
        Button button3 = (Button) findViewById(R.id.SignInCLAccountSignUp);
        this.f9094j = button3;
        button3.setOnClickListener(this.f9099o);
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        m10085q0();
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_sign_in_cl_account);
        this.f9088d = new FriendsClient(true);
        m10084d0();
        m10083c0();
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        AsyncTask<Void, Void, Boolean> asyncTask = this.f9096l;
        if (asyncTask != null) {
            asyncTask.cancel(false);
        }
        this.f9088d.m15717U0();
    }

    /* renamed from: q0 */
    public final void m10085q0() {
        Intent intent;
        if (C5170o0.m20170e(this.f9097m) || !this.f9097m.equals("existCLAccountActivity")) {
            intent = new Intent(getApplicationContext(), (Class<?>) BindCLAccountActivity.class);
        } else {
            intent = new Intent(getApplicationContext(), (Class<?>) ExistCLAccountActivity.class);
            intent.putExtra("registerType", 4);
            intent.putExtra("registerEmail", this.f9091g);
        }
        startActivity(intent);
        finish();
    }

    /* renamed from: r0 */
    public final void m10086r0() {
        runOnUiThread(new Runnable() { // from class: com.cyberlink.you.activity.zf
            @Override // java.lang.Runnable
            public final void run() {
                this.f12307b.m10067n0();
            }
        });
    }

    /* renamed from: s0 */
    public final void m10087s0(final String str) {
        runOnUiThread(new Runnable() { // from class: com.cyberlink.you.activity.bg
            @Override // java.lang.Runnable
            public final void run() {
                this.f9753b.m10069o0(str);
            }
        });
    }

    /* renamed from: u0 */
    public final void m10088u0(final String str) {
        runOnUiThread(new Runnable() { // from class: com.cyberlink.you.activity.cg
            @Override // java.lang.Runnable
            public final void run() {
                this.f9786b.m10070p0(str);
            }
        });
    }
}
