package com.cyberlink.you.activity.friend;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
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
import com.cyberlink.you.utility.CLUtility;
import com.google.android.gms.plus.PlusShare;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;
import p116k4.C5170o0;
import p116k4.C5172p;
import p116k4.C5179r0;
import p201t3.C6301o;

/* loaded from: classes.dex */
public class FriendInfoUpdateActivity extends BaseActivity {

    /* renamed from: k */
    public static final Object f10556k = new Object();

    /* renamed from: c */
    public final String f10557c = "FriendInfoUpdateACT";

    /* renamed from: d */
    public Friend f10558d;

    /* renamed from: e */
    public String f10559e;

    /* renamed from: f */
    public FriendsClient f10560f;

    /* renamed from: g */
    public EditText f10561g;

    /* renamed from: h */
    public Button f10562h;

    /* renamed from: i */
    public View f10563i;

    /* renamed from: j */
    public boolean f10564j;

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: B */
    public /* synthetic */ void m12241B() {
        this.f10562h.setText(getString(R.string.retry));
        this.f10562h.setEnabled(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: C */
    public /* synthetic */ void m12242C(String str, String str2, String str3, String str4) {
        synchronized (f10556k) {
            this.f10564j = true;
            if ("200".equals(str3)) {
                this.f10558d = m12259L(str4);
                m12263w().runOnUiThread(new Runnable() { // from class: b3.n0
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f3225b.m12258z();
                    }
                });
            } else {
                if (str3 != null) {
                    Log.e("FriendInfoUpdateACT", "[onFriendsClientCallBack] Submit fail, statusCode = " + str3);
                } else {
                    Log.e("FriendInfoUpdateACT", "[onFriendsClientCallBack] Submit fail, statusCode is null");
                }
                m12263w().runOnUiThread(new Runnable() { // from class: b3.o0
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f3229b.m12241B();
                    }
                });
                m12260N(false);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: D */
    public /* synthetic */ void m12243D() {
        this.f10562h.setEnabled(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: E */
    public /* synthetic */ void m12244E(View view) {
        if (C5179r0.m20246a()) {
            return;
        }
        m12262v();
        m12263w().runOnUiThread(new Runnable() { // from class: b3.k0
            @Override // java.lang.Runnable
            public final void run() {
                this.f3195b.m12243D();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: H */
    public /* synthetic */ void m12245H(View view) {
        this.f10561g.setText("");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: I */
    public /* synthetic */ void m12246I(View view) {
        CLUtility.m16589t1(m12263w());
        m12261u(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: J */
    public /* synthetic */ void m12247J(boolean z8) {
        View view = this.f10563i;
        if (view != null) {
            view.setVisibility(z8 ? 0 : 8);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: y */
    public /* synthetic */ void m12257y() {
        synchronized (f10556k) {
            if (!this.f10564j) {
                m12260N(true);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: z */
    public /* synthetic */ void m12258z() {
        C2950b0.m14899A().m15008H(Long.toString(this.f10558d.f13645c), this.f10558d, true);
        m12261u(true);
    }

    /* renamed from: L */
    public final Friend m12259L(String str) throws JSONException {
        JSONObject jSONObject;
        JSONObject jSONObject2;
        try {
            jSONObject = new JSONObject(str);
        } catch (JSONException unused) {
            Log.e("FriendInfoUpdateACT", "[ParseSelfInfo] Parse error. JSONstr=" + str);
            jSONObject = null;
        }
        if (jSONObject != null) {
            try {
                jSONObject2 = jSONObject.getJSONObject("result");
            } catch (JSONException unused2) {
                Log.e("FriendInfoUpdateACT", "[ParseSelfInfo] 'results' missing. JSONstr=" + str);
            }
        } else {
            jSONObject2 = null;
        }
        if (jSONObject2 != null) {
            return C5172p.m20184f(jSONObject2);
        }
        return null;
    }

    /* renamed from: N */
    public void m12260N(final boolean z8) {
        runOnUiThread(new Runnable() { // from class: b3.g0
            @Override // java.lang.Runnable
            public final void run() {
                this.f3179b.m12247J(z8);
            }
        });
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        m12261u(false);
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        EditText editText;
        super.onCreate(bundle);
        setContentView(R.layout.activity_friend_info_update);
        this.f10560f = new FriendsClient(true);
        this.f10561g = (EditText) findViewById(R.id.FriendInfoUpdateEdit);
        this.f10562h = (Button) findViewById(R.id.FriendInfoUpdateSaveBtn);
        this.f10563i = findViewById(R.id.waitingCursor);
        Intent intent = getIntent();
        String stringExtra = intent.getStringExtra(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_TITLE);
        this.f10559e = intent.getStringExtra(FirebaseAnalytics.Param.TERM);
        this.f10558d = (Friend) intent.getParcelableExtra("data");
        if (stringExtra != null) {
            ((TextView) findViewById(R.id.FriendInfoUpdateTitle)).setText(stringExtra);
        }
        if (this.f10558d == null) {
            finish();
            return;
        }
        if (this.f10559e.equals("nickname") && (editText = this.f10561g) != null) {
            editText.requestFocus();
            this.f10561g.setText(this.f10558d.m15621b());
            this.f10561g.setHint(this.f10558d.m15620a());
            EditText editText2 = this.f10561g;
            editText2.setSelection(editText2.getText().length());
        }
        this.f10562h.setOnClickListener(new View.OnClickListener() { // from class: b3.h0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f3184b.m12244E(view);
            }
        });
        findViewById(R.id.FriendInfoUpdateXImage).setOnClickListener(new View.OnClickListener() { // from class: b3.i0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f3187b.m12245H(view);
            }
        });
        findViewById(R.id.FriendInfoUpdateBackBtn).setOnClickListener(new View.OnClickListener() { // from class: b3.j0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f3192b.m12246I(view);
            }
        });
    }

    /* renamed from: u */
    public final void m12261u(boolean z8) {
        if (z8) {
            Intent intent = new Intent();
            intent.putExtra("data", this.f10558d);
            setResult(-1, intent);
            finish();
        } else {
            finish();
        }
        m12260N(false);
    }

    /* renamed from: v */
    public final void m12262v() {
        String string = this.f10561g.getText().toString();
        if (C5170o0.m20170e(string)) {
            string = this.f10558d.m15620a();
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(new C6301o("token", Globals.m7388i0().m7449L()));
        arrayList.add(new C6301o("userId", String.valueOf(this.f10558d.f13645c)));
        arrayList.add(new C6301o(this.f10559e, string));
        new Handler().postDelayed(new Runnable() { // from class: b3.l0
            @Override // java.lang.Runnable
            public final void run() {
                this.f3213b.m12257y();
            }
        }, 1000L);
        this.f10564j = false;
        this.f10560f.m15734m("friend", "update", arrayList, new FriendsClient.InterfaceC3051i() { // from class: b3.m0
            @Override // com.cyberlink.you.friends.FriendsClient.InterfaceC3051i
            /* renamed from: a */
            public final void mo134a(String str, String str2, String str3, String str4) {
                this.f3216a.m12242C(str, str2, str3, str4);
            }
        });
        CLUtility.m16589t1(m12263w());
    }

    /* renamed from: w */
    public final Activity m12263w() {
        return this;
    }
}
