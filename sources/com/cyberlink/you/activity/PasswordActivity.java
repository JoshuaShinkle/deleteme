package com.cyberlink.you.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Pair;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import com.cyberlink.meeting.page.p032m.MeetingActivity;
import com.cyberlink.p030U.R;
import com.cyberlink.you.BaseActivity;
import com.cyberlink.you.Globals;
import com.cyberlink.you.activity.ulauncher.ULauncherActivity;
import com.cyberlink.you.friends.FriendsClient;
import com.cyberlink.you.p036ui.C3123g;
import com.cyberlink.you.utility.CLUtility;
import com.cyberlink.you.utility.ULogUtility;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import java.util.ArrayList;
import net.sqlcipher.database.SQLiteDatabase;
import org.json.JSONException;
import org.json.JSONObject;
import p116k4.C5179r0;
import p201t3.C6301o;
import p209u2.C6385v;

/* loaded from: classes.dex */
public class PasswordActivity extends BaseActivity {

    /* renamed from: e */
    public TextView f8222e;

    /* renamed from: f */
    public TextView f8223f;

    /* renamed from: g */
    public TextView f8224g;

    /* renamed from: h */
    public View f8225h;

    /* renamed from: j */
    public ArrayList<View> f8227j;

    /* renamed from: m */
    public AsyncTask<Void, Void, String> f8230m;

    /* renamed from: o */
    public boolean f8232o;

    /* renamed from: c */
    public final int f8220c = 4;

    /* renamed from: d */
    public StringBuilder f8221d = new StringBuilder();

    /* renamed from: i */
    public int f8226i = 0;

    /* renamed from: k */
    public PasswordMode f8228k = PasswordMode.CREATE;

    /* renamed from: l */
    public String f8229l = "privacyPassword";

    /* renamed from: n */
    public View.OnClickListener f8231n = new ViewOnClickListenerC1582a();

    public enum PasswordMode {
        CREATE,
        CONFIRM,
        MODIFY,
        FIX_LOCK
    }

    /* renamed from: com.cyberlink.you.activity.PasswordActivity$a */
    public class ViewOnClickListenerC1582a implements View.OnClickListener {
        public ViewOnClickListenerC1582a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.PasswordKeyboard_cancel /* 2131296784 */:
                    PasswordActivity.this.onBackPressed();
                    break;
                case R.id.PasswordKeyboard_modify /* 2131296785 */:
                    if (PasswordActivity.this.f8221d.length() > 0) {
                        PasswordActivity.this.f8221d.deleteCharAt(PasswordActivity.this.f8221d.length() - 1);
                        break;
                    }
                    break;
                default:
                    TextView textView = (TextView) view;
                    if (PasswordActivity.this.f8221d.length() < 4) {
                        PasswordActivity.this.f8221d.append(textView.getText());
                        break;
                    }
                    break;
            }
            PasswordActivity.this.m8868z();
            if (PasswordActivity.this.f8221d.length() == 4) {
                PasswordActivity.this.f8221d.append("U");
                String string = PasswordActivity.this.f8221d.toString();
                if (PasswordActivity.this.f8228k == PasswordMode.CREATE) {
                    PasswordActivity.this.m8866v(string);
                } else if (PasswordActivity.this.f8228k == PasswordMode.CONFIRM || PasswordActivity.this.f8228k == PasswordMode.MODIFY || PasswordActivity.this.f8228k == PasswordMode.FIX_LOCK) {
                    PasswordActivity.this.m8865u(string);
                }
                PasswordActivity.this.m8868z();
            }
        }
    }

    /* renamed from: com.cyberlink.you.activity.PasswordActivity$b */
    public class AsyncTaskC1583b extends AsyncTask<Void, Void, Boolean> {

        /* renamed from: a */
        public final /* synthetic */ String f8239a;

        public AsyncTaskC1583b(String str) {
            this.f8239a = str;
        }

        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public Boolean doInBackground(Void... voidArr) throws JSONException {
            String str;
            String str2;
            FriendsClient friendsClient = new FriendsClient(true);
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("profile.pin.enabled", "1");
                ArrayList arrayList = new ArrayList();
                arrayList.add(new C6301o("token", Globals.m7388i0().m7449L()));
                arrayList.add(new C6301o("attrs", jSONObject.toString()));
                Pair<String, String> pairM15731j = friendsClient.m15731j("user", "updateUser", arrayList);
                if (pairM15731j != null && ((str2 = (String) pairM15731j.first) == null || !str2.equals("200"))) {
                    return Boolean.FALSE;
                }
                String str3 = this.f8239a;
                String strSubstring = str3.substring(0, str3.length() - 1);
                ArrayList arrayList2 = new ArrayList();
                arrayList2.add(new C6301o("token", Globals.m7388i0().m7449L()));
                arrayList2.add(new C6301o("pin", strSubstring));
                Pair<String, String> pairM15731j2 = friendsClient.m15731j("user", "updatePin", arrayList2);
                if (pairM15731j2 != null && (str = (String) pairM15731j2.first) != null && str.equals("200")) {
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
            if (!bool.booleanValue()) {
                PasswordActivity.this.m8862C();
            } else {
                Globals.m7388i0().m7522a4(PasswordActivity.this.f8226i);
                PasswordActivity.this.finish();
            }
        }

        @Override // android.os.AsyncTask
        public void onPreExecute() {
        }
    }

    /* renamed from: com.cyberlink.you.activity.PasswordActivity$c */
    public class AsyncTaskC1584c extends AsyncTask<Void, Void, String> {

        /* renamed from: a */
        public final /* synthetic */ String f8241a;

        /* renamed from: b */
        public final /* synthetic */ int f8242b;

        /* renamed from: com.cyberlink.you.activity.PasswordActivity$c$a */
        public class a implements DialogInterface.OnClickListener {
            public a() {
            }

            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i9) {
                dialogInterface.dismiss();
            }
        }

        public AsyncTaskC1584c(String str, int i9) {
            this.f8241a = str;
            this.f8242b = i9;
        }

        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public String doInBackground(Void... voidArr) {
            Pair<String, String> pairM15731j;
            String str = this.f8241a;
            String strSubstring = str.substring(0, str.length() - 1);
            FriendsClient friendsClient = new FriendsClient(true);
            ArrayList arrayList = new ArrayList();
            Intent intent = PasswordActivity.this.getIntent();
            if (intent.hasExtra("phoneNumber") && intent.hasExtra(RemoteConfigConstants.RequestFieldKey.COUNTRY_CODE)) {
                String stringExtra = PasswordActivity.this.getIntent().getStringExtra(RemoteConfigConstants.RequestFieldKey.COUNTRY_CODE);
                String stringExtra2 = PasswordActivity.this.getIntent().getStringExtra("phoneNumber");
                arrayList.add(new C6301o(RemoteConfigConstants.RequestFieldKey.COUNTRY_CODE, stringExtra));
                arrayList.add(new C6301o("phoneNumber", stringExtra2));
                arrayList.add(new C6301o("pin", strSubstring));
                pairM15731j = friendsClient.m15731j("user", "verifyPin", arrayList);
            } else if (intent.hasExtra("accessToken")) {
                String stringExtra3 = PasswordActivity.this.getIntent().getStringExtra("accessToken");
                arrayList.add(new C6301o("accountSource", intent.getStringExtra("PasswordActivity.accountSource")));
                arrayList.add(new C6301o("accountToken", stringExtra3));
                arrayList.add(new C6301o("pin", strSubstring));
                pairM15731j = friendsClient.m15731j("user", "verifyPinForOthers", arrayList);
            } else {
                pairM15731j = null;
            }
            if (pairM15731j != null) {
                return (String) pairM15731j.first;
            }
            friendsClient.m15717U0();
            return null;
        }

        @Override // android.os.AsyncTask
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(String str) {
            PasswordActivity.this.f8232o = false;
            if (str != null) {
                if (str.equals("200")) {
                    Globals.m7388i0().m7650z2(false);
                    Globals.m7388i0().m7522a4(this.f8242b);
                    PasswordActivity.this.setResult(-1);
                    PasswordActivity.this.finish();
                    return;
                }
                if (str.equals("429") && !PasswordActivity.this.isFinishing()) {
                    AlertDialog.Builder builderM16382a = C3123g.m16382a(PasswordActivity.this);
                    builderM16382a.setCancelable(false);
                    builderM16382a.setPositiveButton(PasswordActivity.this.getString(R.string.ok), new a());
                    builderM16382a.setMessage(PasswordActivity.this.getString(R.string.privacy_security_pin_try_too_many));
                    builderM16382a.show();
                }
            }
            PasswordActivity.this.m8862C();
        }
    }

    /* renamed from: com.cyberlink.you.activity.PasswordActivity$d */
    public class ViewOnTouchListenerC1585d implements View.OnTouchListener {
        public ViewOnTouchListenerC1585d() {
        }

        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == 0) {
                PasswordActivity.this.f8225h.setPressed(true);
            } else if (motionEvent.getAction() == 1 || motionEvent.getAction() == 3) {
                PasswordActivity.this.f8225h.setPressed(false);
            }
            return false;
        }
    }

    /* renamed from: y */
    public static void m8860y(Activity activity) {
        if (!Globals.m7388i0().m7437I() || (activity instanceof PasswordActivity)) {
            return;
        }
        if (!(activity instanceof MeetingActivity) || ((MeetingActivity) activity).m6609Bf()) {
            Intent intent = new Intent(activity, (Class<?>) PasswordActivity.class);
            intent.putExtra("type", PasswordMode.FIX_LOCK.toString());
            activity.startActivity(intent);
        }
    }

    /* renamed from: B */
    public final void m8861B(String str) {
        new AsyncTaskC1583b(str).executeOnExecutor(C6385v.f21554b, new Void[0]);
    }

    /* renamed from: C */
    public final void m8862C() {
        this.f8223f.setTextColor(getResources().getColor(R.color.you_color_delete_red));
        this.f8223f.setText(getResources().getString(R.string.privacy_password_confirm_fail));
    }

    /* renamed from: D */
    public final void m8863D() {
        this.f8223f.setTextColor(getResources().getColor(R.color.you_color_normal_gray_text));
        this.f8223f.setText(getResources().getString(R.string.privacy_password_enter_new));
    }

    /* renamed from: E */
    public final void m8864E(String str, int i9) {
        if (this.f8232o) {
            return;
        }
        this.f8232o = true;
        AsyncTaskC1584c asyncTaskC1584c = new AsyncTaskC1584c(str, i9);
        this.f8230m = asyncTaskC1584c;
        asyncTaskC1584c.executeOnExecutor(C6385v.f21554b, new Void[0]);
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        if (this.f8228k != PasswordMode.FIX_LOCK || !this.f8229l.equals("privacyPassword")) {
            finish();
            return;
        }
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.HOME");
        intent.setFlags(SQLiteDatabase.CREATE_IF_NECESSARY);
        startActivity(intent);
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        String stringExtra;
        super.onCreate(bundle);
        ULogUtility.m16676l("PasswordActivity", "onCreate");
        Intent intent = getIntent();
        if (intent != null) {
            if (intent.hasExtra("type") && (stringExtra = intent.getStringExtra("type")) != null) {
                this.f8228k = PasswordMode.valueOf(stringExtra);
            }
            if (intent.hasExtra("privacyType")) {
                this.f8229l = intent.getStringExtra("privacyType");
            }
        }
        setContentView(R.layout.activity_password);
        findViewById(R.id.PasswordKeyboard_0).setOnClickListener(this.f8231n);
        findViewById(R.id.PasswordKeyboard_1).setOnClickListener(this.f8231n);
        findViewById(R.id.PasswordKeyboard_2).setOnClickListener(this.f8231n);
        findViewById(R.id.PasswordKeyboard_3).setOnClickListener(this.f8231n);
        findViewById(R.id.PasswordKeyboard_4).setOnClickListener(this.f8231n);
        findViewById(R.id.PasswordKeyboard_5).setOnClickListener(this.f8231n);
        findViewById(R.id.PasswordKeyboard_6).setOnClickListener(this.f8231n);
        findViewById(R.id.PasswordKeyboard_7).setOnClickListener(this.f8231n);
        findViewById(R.id.PasswordKeyboard_8).setOnClickListener(this.f8231n);
        findViewById(R.id.PasswordKeyboard_9).setOnClickListener(this.f8231n);
        this.f8224g = (TextView) findViewById(R.id.PasswordKeyboard_cancel);
        PasswordMode passwordMode = this.f8228k;
        PasswordMode passwordMode2 = PasswordMode.FIX_LOCK;
        if (passwordMode.equals(passwordMode2)) {
            this.f8224g.setText("");
        } else {
            this.f8224g.setOnClickListener(this.f8231n);
        }
        View viewFindViewById = findViewById(R.id.PasswordKeyboard_modify);
        this.f8225h = viewFindViewById.findViewById(R.id.PasswordKeyboard_modify_img);
        viewFindViewById.setOnClickListener(this.f8231n);
        viewFindViewById.setOnTouchListener(new ViewOnTouchListenerC1585d());
        this.f8222e = (TextView) findViewById(R.id.PrivacyPasswordInputNote);
        if (this.f8229l.equals("privacySecurityPIN")) {
            this.f8222e.setText(getString(R.string.privacy_security_pin_confirm));
        } else {
            this.f8222e.setText(getString(R.string.privacy_password_confirm));
        }
        C5179r0.m20247b(this.f8222e, 1);
        this.f8223f = (TextView) findViewById(R.id.PrivacyPasswordInputWarning);
        PasswordMode passwordMode3 = this.f8228k;
        if (passwordMode3 == PasswordMode.CREATE) {
            if (this.f8229l.equals("privacySecurityPIN")) {
                this.f8223f.setText(getResources().getString(R.string.privacy_security_pin_enter_new));
            } else {
                this.f8223f.setText(getResources().getString(R.string.privacy_password_enter_new));
            }
        } else if (passwordMode3.equals(PasswordMode.MODIFY) || this.f8228k.equals(passwordMode2)) {
            if (this.f8229l.equals("privacySecurityPIN")) {
                this.f8223f.setText(getResources().getString(R.string.privacy_security_pin_enter_for_fix));
                this.f8226i = Globals.m7388i0().m7524b1();
            } else {
                this.f8223f.setText(getResources().getString(R.string.privacy_password_enter_passcode_for_fix));
                this.f8226i = Globals.m7388i0().m7468P0();
            }
        }
        ArrayList<View> arrayList = new ArrayList<>();
        this.f8227j = arrayList;
        arrayList.add(findViewById(R.id.PrivacyPasswordCircle1));
        this.f8227j.add(findViewById(R.id.PrivacyPasswordCircle2));
        this.f8227j.add(findViewById(R.id.PrivacyPasswordCircle3));
        this.f8227j.add(findViewById(R.id.PrivacyPasswordCircle4));
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onDestroy() {
        this.f8227j.clear();
        this.f8227j = null;
        this.f8221d.setLength(0);
        this.f8221d = null;
        findViewById(R.id.PasswordKeyboard_0).setOnClickListener(null);
        findViewById(R.id.PasswordKeyboard_1).setOnClickListener(null);
        findViewById(R.id.PasswordKeyboard_2).setOnClickListener(null);
        findViewById(R.id.PasswordKeyboard_3).setOnClickListener(null);
        findViewById(R.id.PasswordKeyboard_4).setOnClickListener(null);
        findViewById(R.id.PasswordKeyboard_5).setOnClickListener(null);
        findViewById(R.id.PasswordKeyboard_6).setOnClickListener(null);
        findViewById(R.id.PasswordKeyboard_7).setOnClickListener(null);
        findViewById(R.id.PasswordKeyboard_8).setOnClickListener(null);
        findViewById(R.id.PasswordKeyboard_9).setOnClickListener(null);
        this.f8224g.setOnClickListener(null);
        View viewFindViewById = findViewById(R.id.PasswordKeyboard_modify);
        viewFindViewById.setOnClickListener(null);
        viewFindViewById.setOnTouchListener(null);
        this.f8225h = null;
        this.f8231n = null;
        AsyncTask<Void, Void, String> asyncTask = this.f8230m;
        if (asyncTask != null) {
            asyncTask.cancel(false);
        }
        super.onDestroy();
    }

    /* renamed from: u */
    public final void m8865u(String str) {
        int iHashCode = str.hashCode();
        PasswordMode passwordMode = this.f8228k;
        PasswordMode passwordMode2 = PasswordMode.FIX_LOCK;
        if (passwordMode == passwordMode2 && this.f8229l.equals("privacySecurityPIN")) {
            m8864E(str, iHashCode);
        } else if (iHashCode == this.f8226i) {
            PasswordMode passwordMode3 = this.f8228k;
            if (passwordMode3 == PasswordMode.MODIFY) {
                m8863D();
                m8867w();
            } else {
                if (passwordMode3 == passwordMode2) {
                    Globals.m7388i0().m7650z2(false);
                    if (CLUtility.m16425C1(this)) {
                        Intent intent = new Intent(getApplicationContext(), (Class<?>) ULauncherActivity.class);
                        intent.setFlags(268468224);
                        startActivity(intent);
                    }
                    finish();
                    return;
                }
                if (this.f8229l.equals("privacySecurityPIN")) {
                    m8861B(str);
                } else {
                    if (Globals.m7388i0().m7468P0() == 0) {
                        setResult(-1, new Intent().putExtra("newPrivacyPassword", true));
                    }
                    Globals.m7388i0().m7471P3(this.f8226i);
                    finish();
                }
            }
        } else {
            if (this.f8228k == PasswordMode.CONFIRM) {
                this.f8226i = 0;
                this.f8228k = PasswordMode.CREATE;
            }
            m8862C();
        }
        StringBuilder sb = this.f8221d;
        sb.delete(0, sb.length());
    }

    /* renamed from: v */
    public final void m8866v(String str) {
        this.f8226i = str.hashCode();
        this.f8228k = PasswordMode.CONFIRM;
        StringBuilder sb = this.f8221d;
        sb.delete(0, sb.length());
        this.f8223f.setTextColor(getResources().getColor(R.color.you_color_normal_gray_text));
        if (this.f8229l.equals("privacySecurityPIN")) {
            this.f8223f.setText(getResources().getString(R.string.privacy_security_pin_confirm_new));
        } else {
            this.f8223f.setText(getResources().getString(R.string.privacy_password_confirm_new));
        }
    }

    /* renamed from: w */
    public final void m8867w() {
        this.f8226i = 0;
        this.f8228k = PasswordMode.CREATE;
        this.f8223f.setTextColor(getResources().getColor(R.color.you_color_normal_gray_text));
        this.f8223f.setText(getResources().getString(R.string.privacy_password_enter_new));
    }

    /* renamed from: z */
    public final void m8868z() {
        int length = this.f8221d.length() >= 4 ? 4 : this.f8221d.length();
        int i9 = 0;
        while (i9 < length) {
            this.f8227j.get(i9).setSelected(true);
            i9++;
        }
        while (i9 < 4) {
            this.f8227j.get(i9).setSelected(false);
            i9++;
        }
    }
}
