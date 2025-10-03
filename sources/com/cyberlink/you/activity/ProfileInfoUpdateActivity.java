package com.cyberlink.you.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.cyberlink.p030U.R;
import com.cyberlink.you.BaseActivity;
import com.cyberlink.you.Globals;
import com.cyberlink.you.database.C2950b0;
import com.cyberlink.you.friends.Friend;
import com.cyberlink.you.friends.FriendsClient;
import com.cyberlink.you.friends.UserInfo;
import com.cyberlink.you.p036ui.C3123g;
import com.cyberlink.you.utility.CLUtility;
import com.cyberlink.you.utility.ULogUtility;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;
import p116k4.C5152i0;
import p116k4.C5172p;
import p201t3.C6301o;

/* loaded from: classes.dex */
public class ProfileInfoUpdateActivity extends BaseActivity {

    /* renamed from: c */
    public final String f8537c = "ProfileInfoUpdateACT";

    /* renamed from: d */
    public String f8538d;

    /* renamed from: e */
    public FriendsClient f8539e;

    /* renamed from: f */
    public EditText f8540f;

    /* renamed from: g */
    public Button f8541g;

    /* renamed from: h */
    public String f8542h;

    /* renamed from: i */
    public TextView f8543i;

    /* renamed from: j */
    public ProgressDialog f8544j;

    /* renamed from: k */
    public FriendsClient.InterfaceC3051i f8545k;

    /* renamed from: com.cyberlink.you.activity.ProfileInfoUpdateActivity$a */
    public class C1633a implements TextWatcher {
        public C1633a() {
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
            if (ProfileInfoUpdateActivity.this.f8538d.equals("statusMessage")) {
                ProfileInfoUpdateActivity.this.f8543i.setText(editable.length() + "/" + ProfileInfoUpdateActivity.this.getResources().getInteger(R.integer.MaxStatusMessageLength));
                return;
            }
            ProfileInfoUpdateActivity.this.f8543i.setText(editable.length() + "/" + ProfileInfoUpdateActivity.this.getResources().getInteger(R.integer.MaxUserNameLength));
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i9, int i10, int i11) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i9, int i10, int i11) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: C */
    public /* synthetic */ void m9384C(View view) {
        if (!this.f8538d.equals("publicId") || this.f8540f.getText().toString().equals("") || !this.f8542h.equals("")) {
            m9402v();
            return;
        }
        AlertDialog.Builder builderM16382a = C3123g.m16382a(this);
        builderM16382a.setTitle(getResources().getString(R.string.profile_update_public_id_dialog_title));
        builderM16382a.setMessage(getResources().getString(R.string.profile_update_public_id_dialog_message));
        builderM16382a.setPositiveButton(getResources().getString(R.string.profile_update_public_id_dialog_confirm), new DialogInterface.OnClickListener() { // from class: com.cyberlink.you.activity.wb
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i9) {
                this.f11848b.m9398z(dialogInterface, i9);
            }
        });
        builderM16382a.setNegativeButton(getResources().getString(R.string.profile_update_public_id_dialog_cancel), new DialogInterface.OnClickListener() { // from class: com.cyberlink.you.activity.xb
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i9) {
                dialogInterface.dismiss();
            }
        });
        builderM16382a.create().show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: D */
    public /* synthetic */ void m9385D(View view) {
        this.f8540f.setText("");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: E */
    public /* synthetic */ void m9386E(View view) {
        CLUtility.m16589t1(this);
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: w */
    public /* synthetic */ void m9396w(UserInfo userInfo) {
        String str = this.f8538d;
        str.hashCode();
        switch (str) {
            case "statusMessage":
                this.f8540f.setText(userInfo.f13780e);
                this.f8542h = userInfo.f13780e;
                break;
            case "publicId":
                this.f8540f.setText(userInfo.f13781f);
                this.f8542h = userInfo.f13781f;
                break;
            case "displayName":
                this.f8540f.setText(userInfo.f13778c);
                this.f8542h = userInfo.f13778c;
                break;
        }
        CLUtility.m16589t1(this);
        m9401r();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: y */
    public /* synthetic */ void m9397y(String str, String str2, String str3, String str4) throws JSONException {
        if (!isFinishing()) {
            C5152i0.m20065b(this.f8544j);
        }
        if ("200".equals(str3)) {
            final UserInfo userInfoM9399H = m9399H(str4);
            if (userInfoM9399H == null) {
                ULogUtility.m16676l("ProfileInfoUpdateACT", "[doUpdateToServerData] paresSelfInfo is null");
                return;
            }
            CLUtility.m16527e(this, userInfoM9399H);
            m9400I(String.valueOf(userInfoM9399H.f13777b));
            runOnUiThread(new Runnable() { // from class: com.cyberlink.you.activity.zb
                @Override // java.lang.Runnable
                public final void run() {
                    this.f12302b.m9396w(userInfoM9399H);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: z */
    public /* synthetic */ void m9398z(DialogInterface dialogInterface, int i9) {
        dialogInterface.dismiss();
        m9402v();
    }

    /* renamed from: H */
    public final UserInfo m9399H(String str) throws JSONException {
        JSONObject jSONObject;
        JSONObject jSONObject2;
        try {
            jSONObject = new JSONObject(str);
        } catch (JSONException unused) {
            Log.e("ProfileInfoUpdateACT", "[ParseSelfInfo] Parse error. JSONstr=" + str);
            jSONObject = null;
        }
        if (jSONObject != null) {
            try {
                jSONObject2 = jSONObject.getJSONObject("result");
            } catch (JSONException unused2) {
                Log.e("ProfileInfoUpdateACT", "[ParseSelfInfo] 'results' missing. JSONstr=" + str);
            }
        } else {
            jSONObject2 = null;
        }
        if (jSONObject2 != null) {
            return C5172p.m20197s(jSONObject2);
        }
        return null;
    }

    /* renamed from: I */
    public final void m9400I(String str) {
        Friend friendM15727f0 = this.f8539e.m15727f0(str);
        if (friendM15727f0 == null) {
            return;
        }
        C2950b0.m14899A().m15019k(friendM15727f0, false, true);
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Intent intent = getIntent();
        String stringExtra = intent.getStringExtra("Title");
        this.f8538d = intent.getStringExtra("KeyItem");
        this.f8542h = intent.getStringExtra("Data");
        if (this.f8538d.equals("statusMessage")) {
            setContentView(R.layout.activity_profile_info_update_status_message);
        } else {
            setContentView(R.layout.activity_profile_info_update);
        }
        this.f8539e = new FriendsClient(true);
        this.f8540f = (EditText) findViewById(R.id.ProfileInfoUpdateEdit);
        this.f8541g = (Button) findViewById(R.id.ProfileInfoUpdateSaveBtn);
        this.f8543i = (TextView) findViewById(R.id.ShowWordsCount);
        if (stringExtra != null) {
            ((TextView) findViewById(R.id.ProfileInfoUpdateTitle)).setText(stringExtra);
        }
        this.f8540f.addTextChangedListener(new C1633a());
        String str = this.f8542h;
        if (str != null) {
            this.f8540f.setText(str);
        }
        this.f8540f.requestFocus();
        this.f8541g.setOnClickListener(new View.OnClickListener() { // from class: com.cyberlink.you.activity.tb
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f11396b.m9384C(view);
            }
        });
        findViewById(R.id.ProfileInfoUpdateXImage).setOnClickListener(new View.OnClickListener() { // from class: com.cyberlink.you.activity.ub
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f11434b.m9385D(view);
            }
        });
        findViewById(R.id.ProfileInfoUpdateBackBtn).setOnClickListener(new View.OnClickListener() { // from class: com.cyberlink.you.activity.vb
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f11816b.m9386E(view);
            }
        });
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        C5152i0.m20065b(this.f8544j);
    }

    /* renamed from: r */
    public final void m9401r() {
        Intent intent = new Intent();
        intent.putExtra("Data", this.f8542h);
        setResult(-1, intent);
        finish();
    }

    /* renamed from: v */
    public final void m9402v() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new C6301o("token", Globals.m7388i0().m7449L()));
        arrayList.add(new C6301o(this.f8538d, this.f8540f.getText().toString()));
        this.f8544j = ProgressDialog.show(this, "", getString(R.string.processing), true);
        FriendsClient.InterfaceC3051i interfaceC3051i = new FriendsClient.InterfaceC3051i() { // from class: com.cyberlink.you.activity.yb
            @Override // com.cyberlink.you.friends.FriendsClient.InterfaceC3051i
            /* renamed from: a */
            public final void mo134a(String str, String str2, String str3, String str4) throws JSONException {
                this.f12276a.m9397y(str, str2, str3, str4);
            }
        };
        this.f8545k = interfaceC3051i;
        this.f8539e.m15734m("user", "updateUser", arrayList, interfaceC3051i);
    }
}
