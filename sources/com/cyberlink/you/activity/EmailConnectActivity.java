package com.cyberlink.you.activity;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.LocaleList;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.util.Patterns;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.cyberlink.meeting.doserver.NetworkManager;
import com.cyberlink.p030U.R;
import com.cyberlink.you.BaseActivity;
import com.cyberlink.you.Globals;
import com.cyberlink.you.activity.EmailConnectActivity;
import com.cyberlink.you.activity.ulauncher.ULauncherActivity;
import com.cyberlink.you.chat.XMPPArchiveHelper;
import com.cyberlink.you.friends.FriendsClient;
import com.cyberlink.you.friends.UserInfo;
import com.cyberlink.you.p036ui.C3123g;
import com.cyberlink.you.utility.CLUtility;
import com.cyberlink.you.utility.ULogUtility;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.regex.Pattern;
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

/* loaded from: classes.dex */
public class EmailConnectActivity extends BaseActivity {

    /* renamed from: c */
    public TextView f7617c;

    /* renamed from: d */
    public TextView f7618d;

    /* renamed from: e */
    public AutoCompleteTextView f7619e;

    /* renamed from: f */
    public EditText f7620f;

    /* renamed from: g */
    public CheckBox f7621g;

    /* renamed from: h */
    public EditText f7622h;

    /* renamed from: i */
    public CheckBox f7623i;

    /* renamed from: j */
    public TextView f7624j;

    /* renamed from: k */
    public View f7625k;

    /* renamed from: l */
    public View f7626l;

    /* renamed from: m */
    public View f7627m;

    /* renamed from: n */
    public View f7628n;

    /* renamed from: o */
    public TextView f7629o;

    /* renamed from: p */
    public ProgressBar f7630p;

    /* renamed from: q */
    public FriendsClient f7631q;

    /* renamed from: r */
    public String f7632r;

    /* renamed from: s */
    public String f7633s;

    /* renamed from: t */
    public InterfaceC1471f f7634t;

    /* renamed from: u */
    public int f7635u;

    /* renamed from: v */
    public AsyncTask<Void, Void, Boolean> f7636v;

    /* renamed from: w */
    public Button f7637w;

    /* renamed from: x */
    public View f7638x;

    /* renamed from: y */
    public TextView f7639y;

    /* renamed from: z */
    public View.OnClickListener f7640z = new View.OnClickListener() { // from class: com.cyberlink.you.activity.y2
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f12266b.m8067D0(view);
        }
    };

    /* renamed from: A */
    public View.OnClickListener f7613A = new ViewOnClickListenerC1466a();

    /* renamed from: B */
    public View.OnClickListener f7614B = new View.OnClickListener() { // from class: com.cyberlink.you.activity.z2
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f12293b.m8070F0(view);
        }
    };

    /* renamed from: C */
    public TextWatcher f7615C = new C1467b();

    /* renamed from: D */
    public C0681k.a<String> f7616D = new C1468c();

    public enum DouAccountStatus {
        NONE,
        NOT_VERIFIED,
        VERIFIED
    }

    /* renamed from: com.cyberlink.you.activity.EmailConnectActivity$a */
    public class ViewOnClickListenerC1466a implements View.OnClickListener {
        public ViewOnClickListenerC1466a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            String string = EmailConnectActivity.this.f7619e.getText().toString();
            Intent intent = new Intent(EmailConnectActivity.this, (Class<?>) ResetCLAccountPasswordActivity.class);
            intent.putExtra("registerType", EmailConnectActivity.this.f7635u);
            if (CLUtility.m16605x1(string.trim())) {
                intent.putExtra("registerEmail", string);
            }
            EmailConnectActivity.this.startActivity(intent);
            EmailConnectActivity.this.finish();
        }
    }

    /* renamed from: com.cyberlink.you.activity.EmailConnectActivity$b */
    public class C1467b implements TextWatcher {
        public C1467b() {
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
            if (EmailConnectActivity.this.f7635u == 1) {
                if (EmailConnectActivity.this.f7619e.length() <= 0 || EmailConnectActivity.this.f7620f.length() <= 0) {
                    EmailConnectActivity.this.f7637w.setEnabled(false);
                    return;
                } else {
                    EmailConnectActivity.this.f7637w.setEnabled(true);
                    return;
                }
            }
            if (EmailConnectActivity.this.f7635u == 0) {
                EmailConnectActivity.this.f7637w.setEnabled(EmailConnectActivity.this.m8120n0());
            } else if (EmailConnectActivity.this.f7619e.length() <= 0 || EmailConnectActivity.this.f7620f.length() <= 0 || EmailConnectActivity.this.f7622h.length() <= 0) {
                EmailConnectActivity.this.f7637w.setEnabled(false);
            } else {
                EmailConnectActivity.this.f7637w.setEnabled(true);
            }
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i9, int i10, int i11) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i9, int i10, int i11) {
        }
    }

    /* renamed from: com.cyberlink.you.activity.EmailConnectActivity$c */
    public class C1468c implements C0681k.a<String> {
        public C1468c() {
        }

        @Override // p015b4.C0681k.a
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onSuccess(String str) {
            Intent intent = new Intent(EmailConnectActivity.this, (Class<?>) VerificationCLAccountActivity.class);
            intent.putExtra("registerEmail", EmailConnectActivity.this.f7632r);
            intent.putExtra("password", EmailConnectActivity.this.f7633s);
            intent.putExtra("registerType", 1);
            EmailConnectActivity.this.startActivity(intent);
            EmailConnectActivity.this.finish();
        }

        @Override // p015b4.C0681k.a
        public void onError(String str) {
            EmailConnectActivity.this.f7630p.setVisibility(8);
            EmailConnectActivity.this.m8118O0(str);
        }
    }

    /* renamed from: com.cyberlink.you.activity.EmailConnectActivity$d */
    public interface InterfaceC1469d {
        /* renamed from: a */
        void mo8130a();

        /* renamed from: b */
        void mo8131b(String str);
    }

    /* renamed from: com.cyberlink.you.activity.EmailConnectActivity$e */
    public class C1470e implements CompoundButton.OnCheckedChangeListener {

        /* renamed from: a */
        public final EditText f7648a;

        public C1470e(EditText editText) {
            this.f7648a = editText;
        }

        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton compoundButton, boolean z8) {
            EditText editText = this.f7648a;
            if (editText == null) {
                return;
            }
            int selectionStart = editText.getSelectionStart();
            int selectionEnd = this.f7648a.getSelectionEnd();
            this.f7648a.setTransformationMethod(z8 ? null : PasswordTransformationMethod.getInstance());
            this.f7648a.setSelection(selectionStart, selectionEnd);
        }
    }

    /* renamed from: com.cyberlink.you.activity.EmailConnectActivity$f */
    public interface InterfaceC1471f {
        /* renamed from: a */
        void mo8132a();

        void onResume();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: C0 */
    public /* synthetic */ void m8065C0(View view) {
        this.f7628n.setSelected(!r2.isSelected());
        this.f7637w.setEnabled(m8120n0());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: D0 */
    public /* synthetic */ void m8067D0(View view) {
        m8114K0();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: F0 */
    public /* synthetic */ void m8070F0(View view) {
        AlertDialog.Builder builderM16382a = C3123g.m16382a(m8123q0());
        builderM16382a.setMessage(Html.fromHtml(m8123q0().getString(R.string.what_is_cyberlink_account_detail), 0));
        builderM16382a.setPositiveButton(m8123q0().getString(R.string.ok), new DialogInterface.OnClickListener() { // from class: com.cyberlink.you.activity.e3
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

    /* renamed from: G0 */
    public static /* synthetic */ void m8071G0(String str, String str2, String str3, String str4) {
        if (C5170o0.m20170e(str3)) {
            return;
        }
        Log.d("EmailConnectActivity", "setAutoAcceptInviteToFalse status : " + str3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: I0 */
    public /* synthetic */ void m8075I0(DialogInterface dialogInterface, int i9) {
        try {
            startActivity(new Intent("android.intent.action.VIEW", Uri.parse(getString(R.string.sign_up_learn_more_url))));
        } catch (ActivityNotFoundException e9) {
            C5154j.m20076j(e9);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: J0 */
    public /* synthetic */ void m8077J0(DialogInterface dialogInterface, int i9) {
        dialogInterface.dismiss();
        ProgressBar progressBar = this.f7630p;
        if (progressBar != null) {
            progressBar.setVisibility(0);
        }
        C0681k.m3403i(this.f7632r, this.f7616D);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: y0 */
    public /* synthetic */ void m8111y0(View view) {
        CLUtility.m16589t1(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: z0 */
    public /* synthetic */ void m8113z0(View view) {
        this.f7626l.setSelected(!r2.isSelected());
    }

    /* renamed from: K0 */
    public final void m8114K0() {
        CLUtility.m16589t1(this);
        int i9 = this.f7635u;
        if (i9 == 0 || i9 == 1) {
            Intent intent = new Intent(this, (Class<?>) RegisterActivity.class);
            setResult(-1, intent);
            startActivity(intent);
        } else if (i9 == 4) {
            Intent intent2 = new Intent(this, (Class<?>) BindCLAccountActivity.class);
            setResult(-1, intent2);
            startActivity(intent2);
        }
        finish();
    }

    /* renamed from: L0 */
    public final void m8115L0() throws JSONException {
        String string;
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("friend.autoAccept.enabled", "0");
            jSONObject.put("friend.autoInvite.enabled", "0");
            string = jSONObject.toString();
        } catch (Exception unused) {
            Log.e("EmailConnectActivity", "[getAttrsString] Fail");
            string = null;
        }
        if (string == null) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(new C6301o("token", Globals.m7388i0().m7449L()));
        arrayList.add(new C6301o("attrs", string));
        this.f7631q.m15734m("user", "updateUser", arrayList, new FriendsClient.InterfaceC3051i() { // from class: com.cyberlink.you.activity.c3
            @Override // com.cyberlink.you.friends.FriendsClient.InterfaceC3051i
            /* renamed from: a */
            public final void mo134a(String str, String str2, String str3, String str4) {
                EmailConnectActivity.m8071G0(str, str2, str3, str4);
            }
        });
    }

    /* renamed from: M0 */
    public final void m8116M0() {
        Button button = this.f7637w;
        if (button != null) {
            button.setEnabled(true);
        }
        AlertDialog.Builder builderM16382a = C3123g.m16382a(m8123q0());
        builderM16382a.setMessage(Html.fromHtml(m8123q0().getString(R.string.invalid_email_address, this.f7632r), 0));
        builderM16382a.setPositiveButton(R.string.cancel, new DialogInterface.OnClickListener() { // from class: com.cyberlink.you.activity.a3
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i9) {
                dialogInterface.dismiss();
            }
        });
        builderM16382a.setNegativeButton(R.string.sign_up_learn_more, new DialogInterface.OnClickListener() { // from class: com.cyberlink.you.activity.b3
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i9) {
                this.f9738b.m8075I0(dialogInterface, i9);
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

    /* renamed from: N0 */
    public final void m8117N0(String str) {
        Toast.makeText(this, str, 1).show();
    }

    /* renamed from: O0 */
    public final void m8118O0(String str) {
        Toast.makeText(this, C0681k.m3399e(this, str), 1).show();
    }

    /* renamed from: P0 */
    public final void m8119P0() {
        AlertDialog.Builder builderM16382a = C3123g.m16382a(m8123q0());
        builderM16382a.setMessage(Html.fromHtml(m8123q0().getString(R.string.exist_cl_account_not_activate, this.f7632r), 0));
        builderM16382a.setPositiveButton(m8123q0().getString(R.string.activate_email), new DialogInterface.OnClickListener() { // from class: com.cyberlink.you.activity.d3
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i9) {
                this.f10360b.m8077J0(dialogInterface, i9);
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

    /* renamed from: n0 */
    public final boolean m8120n0() {
        return this.f7628n.isSelected() && this.f7619e.length() > 0 && this.f7620f.length() > 0 && this.f7622h.length() > 0 && this.f7624j.length() > 0;
    }

    /* renamed from: o0 */
    public final void m8121o0(C0681k.a<C0683m> aVar) {
        this.f7630p.setVisibility(0);
        String string = this.f7620f.getText().toString();
        this.f7633s = string;
        C0681k.m3404j(this.f7632r, string, aVar);
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_email_connect);
        this.f7631q = new FriendsClient(true);
        this.f7634t = m8125s0();
        m8126u0();
        m8122p0();
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        AsyncTask<Void, Void, Boolean> asyncTask = this.f7636v;
        if (asyncTask != null) {
            asyncTask.cancel(false);
        }
        this.f7631q.m15717U0();
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyUp(int i9, KeyEvent keyEvent) {
        if (i9 != 4) {
            return super.onKeyUp(i9, keyEvent);
        }
        m8114K0();
        return true;
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        this.f7634t.onResume();
    }

    /* renamed from: p0 */
    public final void m8122p0() {
        AutoCompleteTextView autoCompleteTextView;
        Bundle extras = getIntent().getExtras();
        if (extras == null || !extras.containsKey("registerEmail") || extras.getString("registerEmail", "").isEmpty() || (autoCompleteTextView = this.f7619e) == null) {
            return;
        }
        autoCompleteTextView.setText(extras.getString("registerEmail", ""));
        EditText editText = this.f7620f;
        if (editText != null) {
            editText.requestFocus();
        }
    }

    /* renamed from: q0 */
    public final Activity m8123q0() {
        return this;
    }

    /* renamed from: r0 */
    public final String[] m8124r0() {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        Account[] accounts = AccountManager.get(this).getAccounts();
        HashSet hashSet = new HashSet();
        for (Account account : accounts) {
            if (pattern.matcher(account.name).matches()) {
                hashSet.add(account.name);
            }
        }
        return (String[]) hashSet.toArray(new String[0]);
    }

    /* renamed from: s0 */
    public final InterfaceC1471f m8125s0() {
        int i9 = getIntent().getExtras().getInt("registerType", 0);
        this.f7635u = i9;
        ViewOnClickListenerC1466a viewOnClickListenerC1466a = null;
        if (i9 == 0 || i9 == 4) {
            return new C1473h(this, viewOnClickListenerC1466a);
        }
        if (i9 == 1) {
            return new C1472g(this, viewOnClickListenerC1466a);
        }
        throw new IllegalArgumentException("wrong int value for register type");
    }

    /* renamed from: u0 */
    public final void m8126u0() {
        findViewById(R.id.EmailConnectSetupArea).setOnClickListener(new View.OnClickListener() { // from class: com.cyberlink.you.activity.v2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f11805b.m8111y0(view);
            }
        });
        this.f7630p = (ProgressBar) findViewById(R.id.loading);
        this.f7617c = (TextView) findViewById(R.id.RegTitleTextView);
        this.f7618d = (TextView) findViewById(R.id.InfoTextView);
        AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.EmailConnectEditText);
        this.f7619e = autoCompleteTextView;
        autoCompleteTextView.addTextChangedListener(this.f7615C);
        EditText editText = (EditText) findViewById(R.id.PasswordEditText);
        this.f7620f = editText;
        editText.addTextChangedListener(this.f7615C);
        CheckBox checkBox = (CheckBox) findViewById(R.id.PasswordEditTextPeek);
        this.f7621g = checkBox;
        checkBox.setOnCheckedChangeListener(new C1470e(this.f7620f));
        EditText editText2 = (EditText) findViewById(R.id.PasswordCheckEditText);
        this.f7622h = editText2;
        editText2.addTextChangedListener(this.f7615C);
        CheckBox checkBox2 = (CheckBox) findViewById(R.id.PasswordCheckEditTextPeek);
        this.f7623i = checkBox2;
        checkBox2.setOnCheckedChangeListener(new C1470e(this.f7622h));
        TextView textView = (TextView) findViewById(R.id.NameEditText);
        this.f7624j = textView;
        textView.addTextChangedListener(this.f7615C);
        findViewById(R.id.RegistrationBackBtn).setOnClickListener(this.f7640z);
        View viewFindViewById = findViewById(R.id.itemCheckBox);
        this.f7626l = viewFindViewById;
        viewFindViewById.setSelected(!GDPRActivity.m8212n());
        View viewFindViewById2 = findViewById(R.id.EDMArea);
        this.f7625k = viewFindViewById2;
        viewFindViewById2.setOnClickListener(new View.OnClickListener() { // from class: com.cyberlink.you.activity.w2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f11836b.m8113z0(view);
            }
        });
        Button button = (Button) findViewById(R.id.EmailConnectContinueBtn);
        this.f7637w = button;
        button.setEnabled(false);
        View viewFindViewById3 = findViewById(R.id.policyCheckBox);
        this.f7628n = viewFindViewById3;
        viewFindViewById3.setSelected(true);
        View viewFindViewById4 = findViewById(R.id.PrivacyPolicyArea);
        this.f7627m = viewFindViewById4;
        viewFindViewById4.setOnClickListener(new View.OnClickListener() { // from class: com.cyberlink.you.activity.x2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f12243b.m8065C0(view);
            }
        });
        TextView textView2 = (TextView) findViewById(R.id.RegEmailPolicy);
        GDPRActivity.m8216u(this, textView2, true);
        this.f7627m.setVisibility(textView2.getVisibility());
        this.f7638x = findViewById(R.id.CLAccountNoticeArea);
        TextView textView3 = (TextView) findViewById(R.id.WhatIsCLAccountTextView);
        this.f7639y = textView3;
        textView3.setOnClickListener(this.f7614B);
        TextView textView4 = (TextView) findViewById(R.id.EmailConnectForgetPW);
        this.f7629o = textView4;
        textView4.setOnClickListener(this.f7613A);
        this.f7634t.mo8132a();
    }

    /* renamed from: w0 */
    public final boolean m8127w0(String str, boolean z8) {
        ArrayList arrayList = new ArrayList();
        if (!z8) {
            arrayList.add("!@#$%^&*()");
        }
        arrayList.add("' or 1=1--");
        arrayList.add("<script src='1.js'");
        arrayList.add("%n%p%c%d");
        arrayList.add("|reboot");
        arrayList.add("../../../etc/passwd");
        arrayList.add("<tag><a>1</a><!--");
        arrayList.add("A.putDouble(\"a\",1)");
        Iterator it = arrayList.iterator();
        boolean zFind = false;
        while (it.hasNext()) {
            zFind |= Pattern.compile(Pattern.quote((String) it.next()), 2).matcher(str).find();
        }
        return zFind;
    }

    /* renamed from: x0 */
    public final boolean m8128x0(String str, String str2, String str3) {
        CLUtility.m16589t1(this);
        if (!CLUtility.m16605x1(str.trim()) || m8127w0(str.trim(), false)) {
            m8117N0(getString(R.string.registration_email_invalid));
            return false;
        }
        if (str2.length() < 6) {
            m8117N0(getString(R.string.registration_cl_account_password_rule));
            return false;
        }
        if (!str2.equals(str3)) {
            m8117N0(getString(R.string.registration_email_password_dont_match));
            return false;
        }
        if (!m8127w0(str2, true)) {
            return true;
        }
        m8117N0(getString(R.string.registration_password_invalid));
        return false;
    }

    /* renamed from: com.cyberlink.you.activity.EmailConnectActivity$h */
    public class C1473h implements InterfaceC1471f {

        /* renamed from: a */
        public View.OnClickListener f7665a;

        /* renamed from: b */
        public C0681k.a<String> f7666b;

        /* renamed from: c */
        public C0681k.a<C0684n> f7667c;

        /* renamed from: d */
        public C0681k.a<String> f7668d;

        /* renamed from: com.cyberlink.you.activity.EmailConnectActivity$h$a */
        public class a implements View.OnClickListener {

            /* renamed from: com.cyberlink.you.activity.EmailConnectActivity$h$a$a, reason: collision with other inner class name */
            public class C6845a implements InterfaceC1469d {
                public C6845a() {
                }

                /* JADX INFO: Access modifiers changed from: private */
                /* renamed from: e */
                public /* synthetic */ void m8164e() {
                    EmailConnectActivity.this.f7630p.setVisibility(8);
                    EmailConnectActivity emailConnectActivity = EmailConnectActivity.this;
                    emailConnectActivity.m8117N0(emailConnectActivity.getString(R.string.error_server_response));
                }

                /* JADX INFO: Access modifiers changed from: private */
                /* renamed from: f */
                public /* synthetic */ void m8165f(String str) {
                    if (EmailConnectActivity.this.m8127w0(str, false)) {
                        EmailConnectActivity.this.f7630p.setVisibility(8);
                        EmailConnectActivity emailConnectActivity = EmailConnectActivity.this;
                        emailConnectActivity.m8117N0(emailConnectActivity.getString(R.string.registration_display_name_invalid));
                        return;
                    }
                    EmailConnectActivity emailConnectActivity2 = EmailConnectActivity.this;
                    emailConnectActivity2.f7632r = emailConnectActivity2.f7619e.getText().toString();
                    EmailConnectActivity emailConnectActivity3 = EmailConnectActivity.this;
                    emailConnectActivity3.f7633s = emailConnectActivity3.f7620f.getText().toString();
                    String string = EmailConnectActivity.this.f7622h.getText().toString();
                    EmailConnectActivity emailConnectActivity4 = EmailConnectActivity.this;
                    if (!emailConnectActivity4.m8128x0(emailConnectActivity4.f7632r, EmailConnectActivity.this.f7633s, string)) {
                        EmailConnectActivity.this.f7630p.setVisibility(8);
                        return;
                    }
                    C0681k.m3406l(EmailConnectActivity.this.f7632r, EmailConnectActivity.this.f7633s, str, EmailConnectActivity.this.f7626l.isSelected(), Locale.getDefault().getLanguage() + "_" + Locale.getDefault().getCountry(), EmailConnectActivity.this.f7635u == 4 ? Globals.m7388i0().m7449L() : null, LocaleList.getDefault().get(0).getCountry(), C1473h.this.f7666b);
                }

                @Override // com.cyberlink.you.activity.EmailConnectActivity.InterfaceC1469d
                /* renamed from: a */
                public void mo8130a() {
                    EmailConnectActivity.this.runOnUiThread(new Runnable() { // from class: com.cyberlink.you.activity.i3
                        @Override // java.lang.Runnable
                        public final void run() {
                            this.f10741b.m8164e();
                        }
                    });
                }

                @Override // com.cyberlink.you.activity.EmailConnectActivity.InterfaceC1469d
                /* renamed from: b */
                public void mo8131b(final String str) {
                    EmailConnectActivity.this.runOnUiThread(new Runnable() { // from class: com.cyberlink.you.activity.j3
                        @Override // java.lang.Runnable
                        public final void run() {
                            this.f10772b.m8165f(str);
                        }
                    });
                }
            }

            public a() {
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                view.setEnabled(false);
                EmailConnectActivity.this.f7630p.setVisibility(0);
                C1473h.this.m8161g(new C6845a());
            }
        }

        /* renamed from: com.cyberlink.you.activity.EmailConnectActivity$h$b */
        public class b implements C0681k.a<String> {
            public b() {
            }

            @Override // p015b4.C0681k.a
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onSuccess(String str) {
                C0681k.m3401g("DoU", str, C1473h.this.f7667c);
            }

            @Override // p015b4.C0681k.a
            public void onError(String str) {
                str.hashCode();
                switch (str) {
                    case "403":
                        ULogUtility.m16688x("Sign up by email", "account not activate");
                        C0681k.m3403i(EmailConnectActivity.this.f7632r, C1473h.this.f7668d);
                        break;
                    case "423":
                        ULogUtility.m16688x("Sign up by email", "show blocked email dialog");
                        EmailConnectActivity.this.f7630p.setVisibility(8);
                        EmailConnectActivity.this.m8116M0();
                        break;
                    case "400.1":
                        ULogUtility.m16688x("Sign up by email", "duplicate account");
                        Intent intent = new Intent(EmailConnectActivity.this, (Class<?>) ExistCLAccountActivity.class);
                        intent.putExtra("registerType", EmailConnectActivity.this.f7635u);
                        intent.putExtra("registerEmail", EmailConnectActivity.this.f7632r);
                        EmailConnectActivity.this.startActivity(intent);
                        EmailConnectActivity.this.finish();
                        break;
                    default:
                        EmailConnectActivity.this.f7630p.setVisibility(8);
                        EmailConnectActivity.this.m8118O0(str);
                        break;
                }
            }
        }

        /* renamed from: com.cyberlink.you.activity.EmailConnectActivity$h$c */
        public class c implements C0681k.a<C0684n> {
            public c() {
            }

            @Override // p015b4.C0681k.a
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onSuccess(C0684n c0684n) {
                Log.d("EmailConnectActivity", "Step 3.call serverAPI 2.31 user.sendEmailVerification");
                String strM3419e = c0684n.m3419e();
                Globals.m7388i0().m7405B2(strM3419e);
                Globals.m7388i0().m7509X3(new Date().getTime());
                Globals.m7388i0().m7400A2(System.currentTimeMillis() + 1814400000);
                Globals.m7388i0().m7505W3(strM3419e, "Email", false);
                C0681k.m3403i(EmailConnectActivity.this.f7632r, C1473h.this.f7668d);
            }

            @Override // p015b4.C0681k.a
            public void onError(String str) {
                EmailConnectActivity.this.f7630p.setVisibility(8);
                EmailConnectActivity.this.m8118O0(str);
            }
        }

        /* renamed from: com.cyberlink.you.activity.EmailConnectActivity$h$d */
        public class d implements C0681k.a<String> {
            public d() {
            }

            @Override // p015b4.C0681k.a
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onSuccess(String str) {
                Log.d("EmailConnectActivity", "Step 4. start VerificationCodeActivity");
                Intent intent = new Intent(EmailConnectActivity.this, (Class<?>) VerificationCLAccountActivity.class);
                intent.putExtra("registerEmail", EmailConnectActivity.this.f7632r);
                intent.putExtra("password", EmailConnectActivity.this.f7633s);
                intent.putExtra("registerType", EmailConnectActivity.this.f7635u);
                EmailConnectActivity.this.startActivity(intent);
                EmailConnectActivity.this.finish();
            }

            @Override // p015b4.C0681k.a
            public void onError(String str) {
                EmailConnectActivity.this.f7630p.setVisibility(8);
                EmailConnectActivity.this.m8118O0(str);
            }
        }

        public C1473h() {
            this.f7665a = new a();
            this.f7666b = new b();
            this.f7667c = new c();
            this.f7668d = new d();
        }

        /* renamed from: h */
        public static /* synthetic */ void m8160h(InterfaceC1469d interfaceC1469d, String str, String str2, String str3, String str4) throws JSONException {
            if (str3 == null || !str3.equals("200")) {
                ULogUtility.m16688x("Bind CL Account", "Get display name error : " + str3);
                interfaceC1469d.mo8130a();
                return;
            }
            try {
                String string = new JSONObject(str4).getJSONObject("result").getString("displayName");
                ULogUtility.m16688x("Bind CL Account", "Get display name success : " + string);
                interfaceC1469d.mo8131b(string);
            } catch (JSONException e9) {
                ULogUtility.m16688x("Bind CL Account", "Parse display name error");
                e9.printStackTrace();
                interfaceC1469d.mo8130a();
            }
        }

        @Override // com.cyberlink.you.activity.EmailConnectActivity.InterfaceC1471f
        /* renamed from: a */
        public void mo8132a() {
            EmailConnectActivity.this.f7637w.setOnClickListener(this.f7665a);
            EmailConnectActivity.this.f7637w.setText(R.string.contiune);
            EmailConnectActivity.this.f7622h.setVisibility(0);
            EmailConnectActivity.this.f7625k.setVisibility(0);
            EmailConnectActivity.this.f7629o.setVisibility(8);
            if (EmailConnectActivity.this.f7635u == 0) {
                EmailConnectActivity.this.f7617c.setText(EmailConnectActivity.this.getString(R.string.registration_phone_setup_title));
                EmailConnectActivity.this.f7618d.setText(EmailConnectActivity.this.getString(R.string.email_sign_up));
                EmailConnectActivity.this.f7638x.setVisibility(8);
                EmailConnectActivity.this.f7624j.setVisibility(0);
            } else {
                EmailConnectActivity.this.f7617c.setText(EmailConnectActivity.this.getString(R.string.account_binding));
                EmailConnectActivity.this.f7618d.setText(EmailConnectActivity.this.getString(R.string.enter_email_password));
                EmailConnectActivity.this.f7638x.setVisibility(0);
                EmailConnectActivity.this.f7624j.setVisibility(8);
            }
            EmailConnectActivity.this.f7620f.setHint(EmailConnectActivity.this.getString(R.string.hint_cl_account_password_with_limit_count));
        }

        /* renamed from: g */
        public final void m8161g(final InterfaceC1469d interfaceC1469d) {
            if (EmailConnectActivity.this.f7624j.getVisibility() == 0) {
                interfaceC1469d.mo8131b(EmailConnectActivity.this.f7624j.getText().toString());
                return;
            }
            UserInfo userInfoM16497V0 = CLUtility.m16497V0(EmailConnectActivity.this);
            if (userInfoM16497V0 == null || !C5170o0.m20170e(userInfoM16497V0.f13778c)) {
                ArrayList arrayList = new ArrayList();
                arrayList.add(new C6301o("token", Globals.m7388i0().m7449L()));
                EmailConnectActivity.this.f7631q.m15734m("user", "updateUser", arrayList, new FriendsClient.InterfaceC3051i() { // from class: com.cyberlink.you.activity.h3
                    @Override // com.cyberlink.you.friends.FriendsClient.InterfaceC3051i
                    /* renamed from: a */
                    public final void mo134a(String str, String str2, String str3, String str4) throws JSONException {
                        EmailConnectActivity.C1473h.m8160h(interfaceC1469d, str, str2, str3, str4);
                    }
                });
            } else {
                ULogUtility.m16688x("Bind CL Account", "Get display from local : " + userInfoM16497V0.f13778c);
                interfaceC1469d.mo8131b(userInfoM16497V0.f13778c);
            }
        }

        @Override // com.cyberlink.you.activity.EmailConnectActivity.InterfaceC1471f
        public void onResume() {
            EmailConnectActivity.this.f7630p.setVisibility(8);
        }

        public /* synthetic */ C1473h(EmailConnectActivity emailConnectActivity, ViewOnClickListenerC1466a viewOnClickListenerC1466a) {
            this();
        }
    }

    /* renamed from: com.cyberlink.you.activity.EmailConnectActivity$g */
    public class C1472g implements InterfaceC1471f {

        /* renamed from: a */
        public boolean f7650a;

        /* renamed from: b */
        public String f7651b;

        /* renamed from: c */
        public String f7652c;

        /* renamed from: d */
        public boolean f7653d;

        /* renamed from: e */
        public View.OnClickListener f7654e;

        /* renamed from: f */
        public C0681k.a<C0683m> f7655f;

        /* renamed from: g */
        public C0681k.a<C0684n> f7656g;

        /* renamed from: h */
        public AsyncTaskC0682l.a f7657h;

        /* renamed from: com.cyberlink.you.activity.EmailConnectActivity$g$a */
        public class a implements View.OnClickListener {
            public a() {
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                CLUtility.m16589t1(EmailConnectActivity.this);
                EmailConnectActivity emailConnectActivity = EmailConnectActivity.this;
                emailConnectActivity.f7632r = emailConnectActivity.f7619e.getText().toString();
                String string = EmailConnectActivity.this.f7620f.getText().toString();
                if (CLUtility.m16605x1(EmailConnectActivity.this.f7632r.trim())) {
                    EmailConnectActivity emailConnectActivity2 = EmailConnectActivity.this;
                    if (!emailConnectActivity2.m8127w0(emailConnectActivity2.f7632r.trim(), false)) {
                        if (string.length() < 6) {
                            EmailConnectActivity emailConnectActivity3 = EmailConnectActivity.this;
                            emailConnectActivity3.m8117N0(emailConnectActivity3.getString(R.string.registration_email_password_range_rule, 6, 20));
                            return;
                        } else if (EmailConnectActivity.this.m8127w0(string, true)) {
                            EmailConnectActivity emailConnectActivity4 = EmailConnectActivity.this;
                            emailConnectActivity4.m8117N0(emailConnectActivity4.getString(R.string.registration_password_invalid));
                            return;
                        } else {
                            EmailConnectActivity.this.f7630p.setVisibility(0);
                            C1472g c1472g = C1472g.this;
                            EmailConnectActivity.this.m8121o0(c1472g.f7655f);
                            return;
                        }
                    }
                }
                EmailConnectActivity emailConnectActivity5 = EmailConnectActivity.this;
                emailConnectActivity5.m8117N0(emailConnectActivity5.getString(R.string.registration_email_invalid));
            }
        }

        /* renamed from: com.cyberlink.you.activity.EmailConnectActivity$g$b */
        public class b implements C0681k.a<C0683m> {
            public b() {
            }

            @Override // p015b4.C0681k.a
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onSuccess(C0683m c0683m) {
                C1472g.this.f7651b = c0683m.m3411b();
                C1472g.this.f7652c = c0683m.m3410a();
                C1472g.this.f7650a = c0683m.m3414f();
                C1472g.this.f7653d = c0683m.m3412d();
                boolean zM3413e = c0683m.m3413e();
                Log.d("EmailConnectActivity", "Step 3.call serverAPI 2.1 register - isVerifyPin:" + c0683m.m3414f());
                Log.d("EmailConnectActivity", "[checkDouAccount] accountSource : " + C1472g.this.f7652c + " needVerifyEmail : " + zM3413e);
                Globals.m7388i0().m7564j3(c0683m.m3413e());
                if (C1472g.this.f7652c.equals("Email") && zM3413e) {
                    ULogUtility.m16688x("Sign in by email", "Sign in to U with a non verified e-mail address.");
                    EmailConnectActivity emailConnectActivity = EmailConnectActivity.this;
                    emailConnectActivity.m8117N0(emailConnectActivity.getString(R.string.email_u_sign_in_is_not_verified));
                }
                if (C1472g.this.f7653d) {
                    C1472g.this.m8150s();
                } else {
                    C0681k.m3401g(C1472g.this.f7652c, C1472g.this.f7651b, C1472g.this.f7656g);
                }
            }

            @Override // p015b4.C0681k.a
            public void onError(String str) {
                EmailConnectActivity.this.f7630p.setVisibility(8);
                if (str.equals("403")) {
                    ULogUtility.m16688x("Sign in by email", "account not activate");
                    EmailConnectActivity.this.m8119P0();
                } else if (!str.equals("423")) {
                    EmailConnectActivity.this.m8118O0(str);
                } else {
                    ULogUtility.m16688x("Sign in by email", "show blocked email dialog");
                    EmailConnectActivity.this.m8116M0();
                }
            }
        }

        /* renamed from: com.cyberlink.you.activity.EmailConnectActivity$g$c */
        public class c implements C0681k.a<C0684n> {

            /* renamed from: com.cyberlink.you.activity.EmailConnectActivity$g$c$a */
            public class a extends AsyncTask<Void, Void, Boolean> {

                /* renamed from: a */
                public final /* synthetic */ String f7662a;

                public a(String str) {
                    this.f7662a = str;
                }

                @Override // android.os.AsyncTask
                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public Boolean doInBackground(Void... voidArr) throws JSONException {
                    String strM7493U0 = Globals.m7388i0().m7493U0();
                    if (EmailConnectActivity.this.f7631q != null) {
                        FriendsClient unused = EmailConnectActivity.this.f7631q;
                        FriendsClient.m15641F0(strM7493U0, this.f7662a);
                    }
                    return Boolean.TRUE;
                }

                @Override // android.os.AsyncTask
                /* renamed from: b, reason: merged with bridge method [inline-methods] */
                public void onPostExecute(Boolean bool) {
                    Globals.m7388i0().m7601q4(Boolean.FALSE);
                    C1472g.this.m8149r(this.f7662a);
                }
            }

            public c() {
            }

            @Override // p015b4.C0681k.a
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onSuccess(C0684n c0684n) throws JSONException {
                String strM3419e = c0684n.m3419e();
                Globals.m7388i0().m7405B2(strM3419e);
                String strM3417c = c0684n.m3417c();
                String strM3420f = c0684n.m3420f();
                String strM3418d = c0684n.m3418d();
                DouAccountStatus douAccountStatusValueOf = DouAccountStatus.valueOf(c0684n.m3416b());
                if (C5170o0.m20169d(strM3417c)) {
                    Log.d("EmailConnectActivity", "[checkDouAccount] Sign in by email, DoU token is empty");
                    ULogUtility.m16688x("Sign in by email", "DoU token is empty");
                }
                Log.d("EmailConnectActivity", "[checkDouAccount] Sign in by email, DoU account status : " + douAccountStatusValueOf.toString());
                ULogUtility.m16688x("Sign in by email", "DoU account status : " + douAccountStatusValueOf.toString());
                if (C5170o0.m20169d(strM3417c) || douAccountStatusValueOf == DouAccountStatus.NONE) {
                    EmailConnectActivity.this.startActivity(new Intent(EmailConnectActivity.this, (Class<?>) BindCLAccountActivity.class));
                    EmailConnectActivity.this.finish();
                    return;
                }
                Globals.m7388i0().m7535d3(c0684n.m3421g().booleanValue());
                if (!C5170o0.m20169d(strM3418d)) {
                    Globals.m7377a3(strM3418d);
                }
                if (!C5170o0.m20169d(strM3417c)) {
                    Globals.m7388i0().m7447K2(strM3417c);
                    ULogUtility.m16688x("Sign in by email", "DoU account token : " + strM3417c);
                }
                if (!c0684n.m3422h().booleanValue()) {
                    EmailConnectActivity.this.m8115L0();
                }
                Globals.m7388i0().m7509X3(new Date().getTime());
                Globals.m7388i0().m7400A2(System.currentTimeMillis() + 1814400000);
                Globals.m7388i0().m7505W3(strM3419e, "Email", "TW".equalsIgnoreCase(strM3420f));
                if (!Globals.m7388i0().m7610s1().booleanValue()) {
                    C1472g.this.m8149r(strM3419e);
                    return;
                }
                EmailConnectActivity.this.f7636v = new a(strM3419e);
                EmailConnectActivity.this.f7636v.executeOnExecutor(C6385v.f21553a, new Void[0]);
            }

            @Override // p015b4.C0681k.a
            public void onError(String str) {
                EmailConnectActivity.this.f7630p.setVisibility(8);
                EmailConnectActivity.this.m8118O0(str);
            }
        }

        /* renamed from: com.cyberlink.you.activity.EmailConnectActivity$g$d */
        public class d implements AsyncTaskC0682l.a {
            public d() {
            }

            @Override // p015b4.AsyncTaskC0682l.a
            public void onComplete() {
                CLUtility.m16518b2(true);
                if (EmailConnectActivity.this.f7630p != null) {
                    EmailConnectActivity.this.f7630p.setVisibility(8);
                }
                NetworkManager.m5644f();
                Intent intent = new Intent(EmailConnectActivity.this.getApplicationContext(), (Class<?>) ULauncherActivity.class);
                intent.setFlags(268468224);
                EmailConnectActivity.this.startActivity(intent);
                XMPPArchiveHelper.m14121C();
                EmailConnectActivity.this.finish();
            }
        }

        public C1472g() {
            this.f7650a = false;
            this.f7651b = "";
            this.f7652c = "";
            this.f7653d = false;
            this.f7654e = new a();
            this.f7655f = new b();
            this.f7656g = new c();
            this.f7657h = new d();
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: p */
        public /* synthetic */ void m8146p(Dialog dialog, View view) {
            dialog.dismiss();
            C0681k.m3401g(this.f7652c, this.f7651b, this.f7656g);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: q */
        public /* synthetic */ void m8147q(Dialog dialog, View view) {
            dialog.dismiss();
            EmailConnectActivity.this.f7630p.setVisibility(8);
        }

        @Override // com.cyberlink.you.activity.EmailConnectActivity.InterfaceC1471f
        /* renamed from: a */
        public void mo8132a() {
            EmailConnectActivity.this.f7637w.setOnClickListener(this.f7654e);
            EmailConnectActivity.this.f7637w.setText(R.string.contiune);
            EmailConnectActivity.this.findViewById(R.id.EmailConnectPWCheckArea).setVisibility(8);
            EmailConnectActivity.this.f7624j.setVisibility(8);
            EmailConnectActivity.this.f7627m.setVisibility(8);
            EmailConnectActivity.this.f7617c.setText(EmailConnectActivity.this.getString(R.string.registration_sign_in_with_email));
            if (Locale.getDefault().getLanguage().equals("es")) {
                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
                layoutParams.addRule(1, R.id.RegistrationBackBtn);
                EmailConnectActivity.this.f7617c.setLayoutParams(layoutParams);
                EmailConnectActivity.this.f7617c.setGravity(17);
                C5179r0.m20247b(EmailConnectActivity.this.f7617c, 1);
            }
            EmailConnectActivity.this.f7618d.setText(EmailConnectActivity.this.getString(R.string.email_sign_in_cl_account));
            EmailConnectActivity.this.f7620f.setHint(EmailConnectActivity.this.getString(R.string.password));
            EmailConnectActivity.this.f7629o.setVisibility(0);
            EmailConnectActivity.this.f7638x.setVisibility(8);
            EmailConnectActivity emailConnectActivity = EmailConnectActivity.this;
            EmailConnectActivity.this.f7619e.setAdapter(new ArrayAdapter(emailConnectActivity, R.layout.view_item_email_drop_down, emailConnectActivity.m8124r0()));
            EmailConnectActivity.this.f7619e.setThreshold(1);
        }

        /* renamed from: o */
        public final boolean m8148o() {
            return Globals.m7388i0().m7524b1() != 0 && this.f7650a;
        }

        @Override // com.cyberlink.you.activity.EmailConnectActivity.InterfaceC1471f
        public void onResume() {
            EmailConnectActivity.this.f7630p.setVisibility(8);
            if (m8148o()) {
                C0681k.m3401g("Email", this.f7651b, this.f7656g);
            }
        }

        /* renamed from: r */
        public final void m8149r(String str) {
            new AsyncTaskC0682l(EmailConnectActivity.this.f7631q, EmailConnectActivity.this, this.f7657h).executeOnExecutor(C6385v.f21554b, str);
        }

        /* renamed from: s */
        public final void m8150s() {
            final Dialog dialog = new Dialog(EmailConnectActivity.this, android.R.style.Theme.Holo.Light.Dialog);
            dialog.requestWindowFeature(1);
            dialog.setContentView(R.layout.dialog_alert_is_loggedin);
            dialog.findViewById(R.id.ok).setOnClickListener(new View.OnClickListener() { // from class: com.cyberlink.you.activity.f3
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f10426b.m8146p(dialog, view);
                }
            });
            dialog.findViewById(R.id.cancel).setVisibility(0);
            dialog.findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() { // from class: com.cyberlink.you.activity.g3
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f10681b.m8147q(dialog, view);
                }
            });
            dialog.setCancelable(false);
            dialog.setCanceledOnTouchOutside(false);
            dialog.show();
        }

        public /* synthetic */ C1472g(EmailConnectActivity emailConnectActivity, ViewOnClickListenerC1466a viewOnClickListenerC1466a) {
            this();
        }
    }
}
