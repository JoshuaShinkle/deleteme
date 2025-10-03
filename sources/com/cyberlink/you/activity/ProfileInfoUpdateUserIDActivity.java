package com.cyberlink.you.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.cyberlink.p030U.R;
import com.cyberlink.you.BaseActivity;
import com.cyberlink.you.Globals;
import com.cyberlink.you.friends.FriendsClient;
import com.cyberlink.you.friends.UserInfo;
import com.cyberlink.you.utility.CLUtility;
import com.cyberlink.you.utility.ULogUtility;
import java.util.ArrayList;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;
import p116k4.C5172p;
import p116k4.C5187v0;
import p201t3.C6301o;

/* loaded from: classes.dex */
public class ProfileInfoUpdateUserIDActivity extends BaseActivity {

    /* renamed from: c */
    public EditText f8547c;

    /* renamed from: d */
    public Button f8548d;

    /* renamed from: e */
    public FriendsClient f8549e;

    /* renamed from: g */
    public int f8551g;

    /* renamed from: f */
    public final String f8550f = "ProfInfoUpdateUserIDACT";

    /* renamed from: h */
    public TextWatcher f8552h = new C1639f();

    /* renamed from: com.cyberlink.you.activity.ProfileInfoUpdateUserIDActivity$a */
    public class ViewOnClickListenerC1634a implements View.OnClickListener {
        public ViewOnClickListenerC1634a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ProfileInfoUpdateUserIDActivity.this.f8547c.setText("");
        }
    }

    /* renamed from: com.cyberlink.you.activity.ProfileInfoUpdateUserIDActivity$b */
    public class ViewOnClickListenerC1635b implements View.OnClickListener {
        public ViewOnClickListenerC1635b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (ProfileInfoUpdateUserIDActivity.this.f8551g == 0) {
                ProfileInfoUpdateUserIDActivity.this.m9412u();
            } else if (ProfileInfoUpdateUserIDActivity.this.f8551g == 1) {
                ProfileInfoUpdateUserIDActivity.this.m9413v();
            }
        }
    }

    /* renamed from: com.cyberlink.you.activity.ProfileInfoUpdateUserIDActivity$c */
    public class ViewOnClickListenerC1636c implements View.OnClickListener {
        public ViewOnClickListenerC1636c() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ProfileInfoUpdateUserIDActivity.this.m9415y();
        }
    }

    /* renamed from: com.cyberlink.you.activity.ProfileInfoUpdateUserIDActivity$d */
    public class C1637d implements FriendsClient.InterfaceC3051i {

        /* renamed from: com.cyberlink.you.activity.ProfileInfoUpdateUserIDActivity$d$a */
        public class a implements Runnable {
            public a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                C5187v0.m20267c(R.string.profile_valid_user_id);
                ProfileInfoUpdateUserIDActivity.this.f8551g = 1;
                ProfileInfoUpdateUserIDActivity.this.f8548d.setText(R.string.profile_create_userid_confirm);
            }
        }

        public C1637d() {
        }

        @Override // com.cyberlink.you.friends.FriendsClient.InterfaceC3051i
        /* renamed from: a */
        public void mo134a(String str, String str2, String str3, String str4) {
            JSONObject jSONObject;
            if (str3 == null || !str3.equals("200")) {
                return;
            }
            try {
                jSONObject = new JSONObject(str4);
            } catch (JSONException unused) {
                Log.e("ProfInfoUpdateUserIDACT", "[ParseSelfInfo] Parse error. JSONstr=" + str4);
                jSONObject = null;
            }
            Boolean bool = Boolean.FALSE;
            if (jSONObject != null) {
                try {
                    bool = (Boolean) jSONObject.get("result");
                } catch (JSONException unused2) {
                    Log.e("ProfInfoUpdateUserIDACT", "[ParseSelfInfo] 'results' missing. JSONstr=" + str4);
                }
            }
            if (bool.booleanValue()) {
                ProfileInfoUpdateUserIDActivity.this.m9414w().runOnUiThread(new a());
            } else {
                C5187v0.m20267c(R.string.profile_invalid_user_id);
            }
        }
    }

    /* renamed from: com.cyberlink.you.activity.ProfileInfoUpdateUserIDActivity$e */
    public class C1638e implements FriendsClient.InterfaceC3051i {
        public C1638e() {
        }

        @Override // com.cyberlink.you.friends.FriendsClient.InterfaceC3051i
        /* renamed from: a */
        public void mo134a(String str, String str2, String str3, String str4) throws JSONException {
            if (str3 == null || !str3.equals("200")) {
                return;
            }
            UserInfo userInfoM9416z = ProfileInfoUpdateUserIDActivity.this.m9416z(str4);
            if (userInfoM9416z == null) {
                ULogUtility.m16676l("ProfInfoUpdateUserIDACT", "[doSetUserID] paresSelfInfo is null");
                return;
            }
            CLUtility.m16527e(ProfileInfoUpdateUserIDActivity.this.m9414w(), userInfoM9416z);
            C5187v0.m20267c(R.string.profile_update_user_id_success);
            Intent intent = new Intent();
            intent.putExtra("Data", userInfoM9416z.f13781f);
            ProfileInfoUpdateUserIDActivity.this.setResult(-1, intent);
            ProfileInfoUpdateUserIDActivity.this.finish();
        }
    }

    /* renamed from: com.cyberlink.you.activity.ProfileInfoUpdateUserIDActivity$f */
    public class C1639f implements TextWatcher {
        public C1639f() {
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i9, int i10, int i11) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i9, int i10, int i11) {
            if (ProfileInfoUpdateUserIDActivity.this.f8551g == 1) {
                ProfileInfoUpdateUserIDActivity.this.f8551g = 0;
                ProfileInfoUpdateUserIDActivity.this.f8548d.setText(R.string.profile_create_userid_check);
            }
        }
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        m9415y();
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_profile_info_update_userid);
        this.f8547c = (EditText) findViewById(R.id.ProfileInfoUpdateEdit);
        this.f8548d = (Button) findViewById(R.id.ProfileInfoUpdateSaveBtn);
        this.f8549e = new FriendsClient(true);
        this.f8551g = 0;
        findViewById(R.id.ProfileInfoUpdateXImage).setOnClickListener(new ViewOnClickListenerC1634a());
        this.f8548d.setOnClickListener(new ViewOnClickListenerC1635b());
        this.f8547c.addTextChangedListener(this.f8552h);
        findViewById(R.id.ProfileInfoUpdateBackBtn).setOnClickListener(new ViewOnClickListenerC1636c());
    }

    /* renamed from: u */
    public final void m9412u() {
        CLUtility.m16602w2(m9414w(), false);
        if (this.f8547c.getText().length() < 6 || this.f8547c.getText().length() > 20) {
            C5187v0.m20267c(R.string.profile_invalid_user_id);
            return;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(new C6301o("token", Globals.m7388i0().m7449L()));
        arrayList.add(new C6301o("publicId", this.f8547c.getText().toString()));
        this.f8549e.m15734m("user", "validatePublicId", arrayList, new C1637d());
    }

    /* renamed from: v */
    public final void m9413v() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new C6301o("token", Globals.m7388i0().m7449L()));
        arrayList.add(new C6301o("publicId", this.f8547c.getText().toString().toLowerCase(Locale.getDefault())));
        this.f8549e.m15734m("user", "updateUser", arrayList, new C1638e());
    }

    /* renamed from: w */
    public final Activity m9414w() {
        return this;
    }

    /* renamed from: y */
    public final void m9415y() {
        CLUtility.m16602w2(m9414w(), false);
        finish();
    }

    /* renamed from: z */
    public final UserInfo m9416z(String str) throws JSONException {
        JSONObject jSONObject;
        JSONObject jSONObject2;
        try {
            jSONObject = new JSONObject(str);
        } catch (JSONException unused) {
            Log.e("ProfInfoUpdateUserIDACT", "[SuggestionListFriends] Parse error. JSONstr=" + str);
            jSONObject = null;
        }
        if (jSONObject != null) {
            try {
                jSONObject2 = jSONObject.getJSONObject("result");
            } catch (JSONException unused2) {
                Log.e("ProfInfoUpdateUserIDACT", "[SuggestionListFriends] 'results' missing. JSONstr=" + str);
            }
        } else {
            jSONObject2 = null;
        }
        if (jSONObject2 != null) {
            return C5172p.m20197s(jSONObject2);
        }
        return null;
    }
}
