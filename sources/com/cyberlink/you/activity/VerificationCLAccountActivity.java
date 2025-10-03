package com.cyberlink.you.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.cyberlink.p030U.R;
import com.cyberlink.you.BaseActivity;
import com.cyberlink.you.Globals;
import com.cyberlink.you.activity.splash.SplashActivity;
import com.cyberlink.you.activity.ulauncher.ULauncherActivity;
import com.cyberlink.you.feedback.C3031d;
import com.cyberlink.you.friends.FriendsClient;
import com.cyberlink.you.friends.UserInfo;
import com.cyberlink.you.p036ui.C3123g;
import com.cyberlink.you.utility.CLUtility;
import com.cyberlink.you.utility.ULogUtility;
import com.google.android.gms.common.Scopes;
import java.util.ArrayList;
import java.util.Date;
import org.json.JSONException;
import org.json.JSONObject;
import p015b4.AsyncTaskC0682l;
import p015b4.C0681k;
import p015b4.C0683m;
import p015b4.C0684n;
import p116k4.C5154j;
import p116k4.C5170o0;
import p116k4.C5179r0;
import p201t3.C6301o;
import p209u2.C6385v;
import p236x2.C6566a;

/* loaded from: classes.dex */
public class VerificationCLAccountActivity extends BaseActivity {

    /* renamed from: d */
    public String f9433d;

    /* renamed from: e */
    public String f9434e;

    /* renamed from: f */
    public TextView f9435f;

    /* renamed from: g */
    public TextView f9436g;

    /* renamed from: h */
    public TextView f9437h;

    /* renamed from: i */
    public FriendsClient f9438i;

    /* renamed from: j */
    public View f9439j;

    /* renamed from: k */
    public int f9440k;

    /* renamed from: l */
    public AsyncTask<Void, Void, Boolean> f9441l;

    /* renamed from: c */
    public String f9432c = VerificationCLAccountActivity.class.getSimpleName();

    /* renamed from: m */
    public C0681k.a<String> f9442m = new C1816a();

    /* renamed from: n */
    public View.OnClickListener f9443n = new View.OnClickListener() { // from class: com.cyberlink.you.activity.uk
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f11441b.m10670k0(view);
        }
    };

    /* renamed from: o */
    public View.OnClickListener f9444o = new ViewOnClickListenerC1817b();

    /* renamed from: p */
    public C0681k.a<C0683m> f9445p = new C1818c();

    /* renamed from: q */
    public C0681k.a<C0684n> f9446q = new C1819d();

    /* renamed from: r */
    public AsyncTaskC0682l.a f9447r = new C1821f();

    public enum DouAccountStatus {
        NONE,
        NOT_VERIFIED,
        VERIFIED
    }

    /* renamed from: com.cyberlink.you.activity.VerificationCLAccountActivity$a */
    public class C1816a implements C0681k.a<String> {
        public C1816a() {
        }

        @Override // p015b4.C0681k.a
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onSuccess(String str) {
            VerificationCLAccountActivity verificationCLAccountActivity = VerificationCLAccountActivity.this;
            verificationCLAccountActivity.m10704y0(verificationCLAccountActivity.getString(R.string.success));
        }

        @Override // p015b4.C0681k.a
        public void onError(String str) {
            VerificationCLAccountActivity.this.m10705z0(str);
        }
    }

    /* renamed from: com.cyberlink.you.activity.VerificationCLAccountActivity$b */
    public class ViewOnClickListenerC1817b implements View.OnClickListener {
        public ViewOnClickListenerC1817b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            VerificationCLAccountActivity.this.f9439j.setVisibility(0);
            C0681k.m3403i(VerificationCLAccountActivity.this.f9433d, VerificationCLAccountActivity.this.f9442m);
        }
    }

    /* renamed from: com.cyberlink.you.activity.VerificationCLAccountActivity$c */
    public class C1818c implements C0681k.a<C0683m> {
        public C1818c() {
        }

        @Override // p015b4.C0681k.a
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onSuccess(C0683m c0683m) {
            C0681k.m3401g(c0683m.m3410a(), c0683m.m3411b(), VerificationCLAccountActivity.this.f9446q);
        }

        @Override // p015b4.C0681k.a
        public void onError(String str) {
            VerificationCLAccountActivity.this.f9439j.setVisibility(8);
            if (str.equals("403")) {
                VerificationCLAccountActivity.this.m10690C0();
            } else {
                VerificationCLAccountActivity.this.m10705z0(str);
            }
        }
    }

    /* renamed from: com.cyberlink.you.activity.VerificationCLAccountActivity$d */
    public class C1819d implements C0681k.a<C0684n> {
        public C1819d() {
        }

        @Override // p015b4.C0681k.a
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onSuccess(C0684n c0684n) throws JSONException {
            String strM3419e = c0684n.m3419e();
            Globals.m7388i0().m7405B2(strM3419e);
            String strM3417c = c0684n.m3417c();
            DouAccountStatus douAccountStatusValueOf = DouAccountStatus.valueOf(c0684n.m3416b());
            if (C5170o0.m20169d(strM3417c)) {
                ULogUtility.m16688x("VerificationCLAccount - register", "DoU token is empty");
                Log.d(VerificationCLAccountActivity.this.f9432c, "[checkDouAccount] Sign in by email, DoU token is empty");
            }
            Log.d(VerificationCLAccountActivity.this.f9432c, "[checkDouAccount] Sign in by email, DoU account status : " + douAccountStatusValueOf.toString());
            ULogUtility.m16688x("VerificationCLAccount - register", "DoU account status : " + douAccountStatusValueOf.toString());
            if (C5170o0.m20169d(strM3417c) || douAccountStatusValueOf == DouAccountStatus.NONE) {
                VerificationCLAccountActivity.this.startActivity(new Intent(VerificationCLAccountActivity.this, (Class<?>) BindCLAccountActivity.class));
                VerificationCLAccountActivity.this.finish();
                return;
            }
            if (!C5170o0.m20169d(strM3417c)) {
                ULogUtility.m16688x("VerificationCLAccount - register", "setDoUToken : " + strM3417c);
                Globals.m7388i0().m7447K2(strM3417c);
            }
            if (!c0684n.m3422h().booleanValue()) {
                VerificationCLAccountActivity.this.m10702w0();
            }
            Globals.m7388i0().m7509X3(new Date().getTime());
            Globals.m7388i0().m7400A2(System.currentTimeMillis() + 1814400000);
            Globals.m7388i0().m7505W3(strM3419e, "Email", false);
            VerificationCLAccountActivity.this.m10694Y();
        }

        @Override // p015b4.C0681k.a
        public void onError(String str) {
            VerificationCLAccountActivity.this.f9439j.setVisibility(8);
            VerificationCLAccountActivity.this.m10705z0(str);
        }
    }

    /* renamed from: com.cyberlink.you.activity.VerificationCLAccountActivity$e */
    public class AsyncTaskC1820e extends AsyncTask<Void, Void, Boolean> {

        /* renamed from: a */
        public final /* synthetic */ String f9456a;

        public AsyncTaskC1820e(String str) {
            this.f9456a = str;
        }

        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public Boolean doInBackground(Void... voidArr) throws JSONException {
            String strM7493U0 = Globals.m7388i0().m7493U0();
            if (VerificationCLAccountActivity.this.f9438i != null) {
                FriendsClient unused = VerificationCLAccountActivity.this.f9438i;
                FriendsClient.m15641F0(strM7493U0, this.f9456a);
            }
            return Boolean.TRUE;
        }

        @Override // android.os.AsyncTask
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(Boolean bool) {
            Globals.m7388i0().m7601q4(Boolean.FALSE);
            FriendsClient friendsClient = VerificationCLAccountActivity.this.f9438i;
            VerificationCLAccountActivity verificationCLAccountActivity = VerificationCLAccountActivity.this;
            new AsyncTaskC0682l(friendsClient, verificationCLAccountActivity, verificationCLAccountActivity.f9447r).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, this.f9456a);
        }
    }

    /* renamed from: com.cyberlink.you.activity.VerificationCLAccountActivity$f */
    public class C1821f implements AsyncTaskC0682l.a {
        public C1821f() {
        }

        @Override // p015b4.AsyncTaskC0682l.a
        public void onComplete() {
            C6566a.m25162u("Launch_App");
            if (VerificationCLAccountActivity.this.f9439j != null) {
                VerificationCLAccountActivity.this.f9439j.setVisibility(8);
            }
            Intent intent = new Intent(VerificationCLAccountActivity.this.getApplicationContext(), (Class<?>) ULauncherActivity.class);
            intent.setFlags(268468224);
            VerificationCLAccountActivity.this.startActivity(intent);
            VerificationCLAccountActivity.this.finish();
        }
    }

    /* renamed from: com.cyberlink.you.activity.VerificationCLAccountActivity$g */
    public class AsyncTaskC1822g extends AsyncTask<Void, Void, Void> {
        public AsyncTaskC1822g() {
        }

        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public Void doInBackground(Void... voidArr) throws JSONException {
            Thread.currentThread().setName("BindDouAccount AsyncTask");
            ArrayList arrayList = new ArrayList();
            arrayList.add(new C6301o(Scopes.EMAIL, VerificationCLAccountActivity.this.f9433d));
            arrayList.add(new C6301o("password", VerificationCLAccountActivity.this.f9434e));
            arrayList.add(new C6301o("token", Globals.m7388i0().m7449L()));
            FriendsClient unused = VerificationCLAccountActivity.this.f9438i;
            Pair<String, String> pairM15676n = FriendsClient.m15676n("user", "bindDou", arrayList);
            String str = (String) pairM15676n.first;
            String str2 = (String) pairM15676n.second;
            if (str == null) {
                VerificationCLAccountActivity verificationCLAccountActivity = VerificationCLAccountActivity.this;
                verificationCLAccountActivity.m10704y0(verificationCLAccountActivity.getString(R.string.feedback_unknown_error));
                return null;
            }
            switch (str) {
                case "200":
                    try {
                        String string = new JSONObject(str2).getString("accountToken");
                        if (!TextUtils.isEmpty(string)) {
                            VerificationCLAccountActivity.this.m10693X(string);
                            break;
                        }
                    } catch (JSONException e9) {
                        e9.printStackTrace();
                        return null;
                    }
                    break;
                case "403":
                    VerificationCLAccountActivity.this.m10690C0();
                    break;
                case "423":
                    VerificationCLAccountActivity.this.m10703x0();
                    break;
                default:
                    VerificationCLAccountActivity.this.m10705z0(str);
                    break;
            }
            return null;
        }

        @Override // android.os.AsyncTask
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(Void r12) {
        }
    }

    /* renamed from: com.cyberlink.you.activity.VerificationCLAccountActivity$h */
    public class AsyncTaskC1823h extends AsyncTask<Void, Void, Boolean> {

        /* renamed from: a */
        public final /* synthetic */ String f9460a;

        public AsyncTaskC1823h(String str) {
            this.f9460a = str;
        }

        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public Boolean doInBackground(Void... voidArr) throws JSONException {
            boolean z8;
            Thread.currentThread().setName("BindUAccount AsyncTask");
            ArrayList arrayList = new ArrayList();
            arrayList.add(new C6301o("token", Globals.m7388i0().m7449L()));
            arrayList.add(new C6301o("accountSource", "DoU"));
            arrayList.add(new C6301o("accountToken", this.f9460a));
            FriendsClient unused = VerificationCLAccountActivity.this.f9438i;
            Pair<String, String> pairM15676n = FriendsClient.m15676n("user", "bindAccount", arrayList);
            String str = (String) pairM15676n.first;
            String str2 = (String) pairM15676n.second;
            if ("200".equals(str)) {
                try {
                    String string = new JSONObject(str2).getJSONObject("result").getString("douToken");
                    ULogUtility.m16688x("VerificationCLAccount - bind", "setDoUToken : " + string);
                    Globals.m7388i0().m7447K2(string);
                    Globals.m7388i0().m7400A2(System.currentTimeMillis() + 1814400000);
                    z8 = true;
                } catch (JSONException e9) {
                    e9.printStackTrace();
                }
                return Boolean.valueOf(z8);
            }
            VerificationCLAccountActivity.this.m10705z0(str);
            z8 = false;
            return Boolean.valueOf(z8);
        }

        @Override // android.os.AsyncTask
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(Boolean bool) {
            if (bool.booleanValue()) {
                VerificationCLAccountActivity.this.m10694Y();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: i0 */
    public /* synthetic */ void m10666i0(DialogInterface dialogInterface, int i9) {
        if (CLUtility.m16425C1(this)) {
            Intent intent = new Intent(getApplicationContext(), (Class<?>) SplashActivity.class);
            intent.addFlags(268468224);
            startActivity(intent);
        }
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: j0 */
    public /* synthetic */ void m10668j0(View view) {
        int i9 = this.f9440k;
        if (i9 == 0 || i9 == 1) {
            m10695Z();
        } else if (i9 == 4 || i9 == 5) {
            m10691T();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: k0 */
    public /* synthetic */ void m10670k0(View view) {
        m10701u0();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: l0 */
    public /* synthetic */ void m10672l0(String str, String str2, String str3, String str4) {
        if (C5170o0.m20170e(str3)) {
            return;
        }
        Log.d(this.f9432c, "setAutoAcceptInviteToFalse status : " + str3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: m0 */
    public /* synthetic */ void m10673m0() {
        ULogUtility.m16688x("Verification CL account", "show blocked email dialog");
        View view = this.f9439j;
        if (view != null) {
            view.setVisibility(8);
        }
        if (m7364e()) {
            return;
        }
        AlertDialog.Builder builderM16382a = C3123g.m16382a(m10697c0());
        builderM16382a.setMessage(Html.fromHtml(m10697c0().getString(R.string.invalid_email_address, this.f9433d), 0));
        builderM16382a.setPositiveButton(R.string.cancel, new DialogInterface.OnClickListener() { // from class: com.cyberlink.you.activity.cl
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i9) {
                dialogInterface.dismiss();
            }
        });
        builderM16382a.setNegativeButton(R.string.sign_up_learn_more, new DialogInterface.OnClickListener() { // from class: com.cyberlink.you.activity.tk
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i9) {
                this.f11414b.m10677o0(dialogInterface, i9);
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
    public /* synthetic */ void m10677o0(DialogInterface dialogInterface, int i9) {
        try {
            startActivity(new Intent("android.intent.action.VIEW", Uri.parse(getString(R.string.sign_up_learn_more_url))));
        } catch (ActivityNotFoundException e9) {
            C5154j.m20076j(e9);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: p0 */
    public /* synthetic */ void m10678p0(String str) {
        View view = this.f9439j;
        if (view != null) {
            view.setVisibility(8);
        }
        Toast.makeText(this, str, 1).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: q0 */
    public /* synthetic */ void m10680q0(String str) {
        View view = this.f9439j;
        if (view != null) {
            view.setVisibility(8);
        }
        Toast.makeText(this, C0681k.m3399e(this, str), 1).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: s0 */
    public /* synthetic */ void m10684s0() {
        View view = this.f9439j;
        if (view != null) {
            view.setVisibility(8);
        }
        if (m7364e()) {
            return;
        }
        AlertDialog.Builder builderM16382a = C3123g.m16382a(m10697c0());
        builderM16382a.setMessage(Html.fromHtml(m10697c0().getString(R.string.exist_cl_account_not_activate, this.f9433d), 0));
        builderM16382a.setPositiveButton(m10697c0().getString(R.string.ok), new DialogInterface.OnClickListener() { // from class: com.cyberlink.you.activity.bl
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i9) {
                dialogInterface.dismiss();
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

    /* renamed from: C0 */
    public final void m10690C0() {
        runOnUiThread(new Runnable() { // from class: com.cyberlink.you.activity.zk
            @Override // java.lang.Runnable
            public final void run() {
                this.f12315b.m10684s0();
            }
        });
    }

    /* renamed from: T */
    public final void m10691T() {
        ULogUtility.m16688x("VerificationCLAccount", "doBindCLAccount");
        this.f9439j.setVisibility(0);
        m10692V();
    }

    /* renamed from: V */
    public final void m10692V() {
        new AsyncTaskC1822g().executeOnExecutor(C6385v.f21554b, new Void[0]);
    }

    /* renamed from: X */
    public final void m10693X(String str) {
        new AsyncTaskC1823h(str).executeOnExecutor(C6385v.f21554b, new Void[0]);
    }

    /* renamed from: Y */
    public final void m10694Y() {
        String strM7449L = Globals.m7388i0().m7449L();
        if (!Globals.m7388i0().m7610s1().booleanValue()) {
            new AsyncTaskC0682l(this.f9438i, this, this.f9447r).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, strM7449L);
            return;
        }
        AsyncTaskC1820e asyncTaskC1820e = new AsyncTaskC1820e(strM7449L);
        this.f9441l = asyncTaskC1820e;
        asyncTaskC1820e.executeOnExecutor(C6385v.f21554b, new Void[0]);
    }

    /* renamed from: Z */
    public final void m10695Z() {
        ULogUtility.m16688x("VerificationCLAccount", "doRegisterCLAccount");
        this.f9439j.setVisibility(0);
        C0681k.m3404j(this.f9433d, this.f9434e, this.f9445p);
    }

    /* renamed from: b0 */
    public final void m10696b0(String str) {
        ULogUtility.m16688x("VerificationCLAccountByAccessToken", "doRegisterCLAccountByAccessToken");
        this.f9439j.setVisibility(0);
        C0681k.m3405k(str, this.f9445p);
    }

    /* renamed from: c0 */
    public final Activity m10697c0() {
        return this;
    }

    /* renamed from: d0 */
    public final void m10698d0() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            this.f9433d = extras.getString("registerEmail");
            this.f9440k = extras.getInt("registerType", 0);
            this.f9434e = extras.getString("password");
        }
    }

    /* renamed from: e0 */
    public final void m10699e0(Intent intent) {
        Uri data = intent.getData();
        if (data != null) {
            String scheme = data.getScheme();
            if ("sso".equalsIgnoreCase(data.getHost()) && getString(R.string.cyberlink_scheme).equalsIgnoreCase(scheme)) {
                if (!Globals.m7388i0().m7464O1()) {
                    if (this.f9433d == null) {
                        this.f9435f.setText(Html.fromHtml(getString(R.string.email_cl_account_verification_description_v3)));
                    }
                    m10696b0(new C3031d(data.toString(), 2).m15504h("token"));
                    return;
                }
                findViewById(R.id.noticeBackground).setVisibility(0);
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                UserInfo userInfoM16497V0 = CLUtility.m16497V0(m10697c0());
                builder.setMessage(getString(R.string.error_login_through_token_when_already_sign_in, (userInfoM16497V0 == null || C5170o0.m20170e(userInfoM16497V0.f13778c)) ? "" : userInfoM16497V0.f13778c, Globals.m7388i0().m7498V0()));
                builder.setPositiveButton(R.string.contiune, new DialogInterface.OnClickListener() { // from class: com.cyberlink.you.activity.sk
                    @Override // android.content.DialogInterface.OnClickListener
                    public final void onClick(DialogInterface dialogInterface, int i9) {
                        this.f11357b.m10666i0(dialogInterface, i9);
                    }
                });
                builder.setCancelable(false);
                builder.show();
            }
        }
    }

    /* renamed from: g0 */
    public final void m10700g0() {
        this.f9439j = findViewById(R.id.loading);
        findViewById(R.id.VerificationCLAccountBackBtn).setOnClickListener(this.f9443n);
        this.f9435f = (TextView) findViewById(R.id.VerificationCLAccountDescription);
        this.f9435f.setText(Html.fromHtml(getString(R.string.email_cl_account_verification_description_v2, this.f9433d)));
        TextView textView = (TextView) findViewById(R.id.VerificationCLAccountResend);
        this.f9436g = textView;
        textView.setOnClickListener(this.f9444o);
        TextView textView2 = (TextView) findViewById(R.id.VerificationCLAccountVerified);
        this.f9437h = textView2;
        textView2.setOnClickListener(new View.OnClickListener() { // from class: com.cyberlink.you.activity.vk
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f11830b.m10668j0(view);
            }
        });
        C5179r0.m20247b(this.f9436g, 1);
        C5179r0.m20247b(this.f9437h, 1);
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        m10701u0();
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_verification_cl_account);
        m10698d0();
        m10700g0();
        this.f9438i = new FriendsClient(true);
        m10699e0(getIntent());
    }

    @Override // android.app.Activity
    public void onNewIntent(Intent intent) {
        m10699e0(intent);
    }

    /* renamed from: u0 */
    public final void m10701u0() {
        FriendsClient friendsClient = this.f9438i;
        if (friendsClient != null) {
            friendsClient.m15717U0();
        }
        CLUtility.m16602w2(this, false);
        int i9 = this.f9440k;
        if (i9 == 0 || i9 == 1) {
            Intent intent = new Intent(this, (Class<?>) RegisterActivity.class);
            setResult(-1, intent);
            startActivity(intent);
        } else if (i9 == 4 || i9 == 5) {
            Intent intent2 = new Intent(this, (Class<?>) BindCLAccountActivity.class);
            setResult(-1, intent2);
            startActivity(intent2);
        }
        finish();
    }

    /* renamed from: w0 */
    public final void m10702w0() throws JSONException {
        String string;
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("friend.autoAccept.enabled", "0");
            jSONObject.put("friend.autoInvite.enabled", "0");
            string = jSONObject.toString();
        } catch (Exception unused) {
            Log.e(this.f9432c, "[getAttrsString] Fail");
            string = null;
        }
        if (string == null) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(new C6301o("token", Globals.m7388i0().m7449L()));
        arrayList.add(new C6301o("attrs", string));
        this.f9438i.m15734m("user", "updateUser", arrayList, new FriendsClient.InterfaceC3051i() { // from class: com.cyberlink.you.activity.wk
            @Override // com.cyberlink.you.friends.FriendsClient.InterfaceC3051i
            /* renamed from: a */
            public final void mo134a(String str, String str2, String str3, String str4) {
                this.f12237a.m10672l0(str, str2, str3, str4);
            }
        });
    }

    /* renamed from: x0 */
    public final void m10703x0() {
        runOnUiThread(new Runnable() { // from class: com.cyberlink.you.activity.al
            @Override // java.lang.Runnable
            public final void run() {
                this.f9730b.m10673m0();
            }
        });
    }

    /* renamed from: y0 */
    public final void m10704y0(final String str) {
        runOnUiThread(new Runnable() { // from class: com.cyberlink.you.activity.xk
            @Override // java.lang.Runnable
            public final void run() {
                this.f12260b.m10678p0(str);
            }
        });
    }

    /* renamed from: z0 */
    public final void m10705z0(final String str) {
        runOnUiThread(new Runnable() { // from class: com.cyberlink.you.activity.yk
            @Override // java.lang.Runnable
            public final void run() {
                this.f12287b.m10680q0(str);
            }
        });
    }
}
