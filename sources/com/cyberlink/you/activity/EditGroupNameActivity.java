package com.cyberlink.you.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.cyberlink.p030U.R;
import com.cyberlink.you.BaseActivity;
import com.cyberlink.you.Globals;
import com.cyberlink.you.database.C2950b0;
import com.cyberlink.you.friends.FriendsClient;
import com.cyberlink.you.friends.Group;
import com.cyberlink.you.utility.CLUtility;
import java.util.ArrayList;
import p116k4.C5187v0;
import p201t3.C6301o;

/* loaded from: classes.dex */
public class EditGroupNameActivity extends BaseActivity {

    /* renamed from: c */
    public Button f7607c;

    /* renamed from: d */
    public EditText f7608d;

    /* renamed from: e */
    public TextView f7609e;

    /* renamed from: f */
    public ProgressDialog f7610f;

    /* renamed from: g */
    public Group f7611g;

    /* renamed from: com.cyberlink.you.activity.EditGroupNameActivity$a */
    public class C1465a implements TextWatcher {
        public C1465a() {
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
            EditGroupNameActivity.this.f7609e.setText(Integer.toString(editable.length()) + "/" + Integer.toString(EditGroupNameActivity.this.getResources().getInteger(R.integer.MaxDisplayNameLength)));
            EditGroupNameActivity.this.f7607c.setEnabled(editable.length() > 0);
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i9, int i10, int i11) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i9, int i10, int i11) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: B */
    public /* synthetic */ void m8048B(View view) {
        if (this.f7608d.getText().toString().trim().length() == 0) {
            C5187v0.m20267c(R.string.group_empty_title);
            return;
        }
        CLUtility.m16589t1(this);
        FriendsClient friendsClient = new FriendsClient();
        this.f7610f = ProgressDialog.show(this, "", getString(R.string.loading), true);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new C6301o("token", Globals.m7388i0().m7449L()));
        arrayList.add(new C6301o("groupId", String.valueOf(this.f7611g.f13727n)));
        arrayList.add(new C6301o("displayName", this.f7608d.getText().toString()));
        friendsClient.m15734m("group", "update", arrayList, new FriendsClient.InterfaceC3051i() { // from class: com.cyberlink.you.activity.t2
            @Override // com.cyberlink.you.friends.FriendsClient.InterfaceC3051i
            /* renamed from: a */
            public final void mo134a(String str, String str2, String str3, String str4) {
                this.f11388a.m8059z(str, str2, str3, str4);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: v */
    public /* synthetic */ void m8056v(View view) {
        CLUtility.m16589t1(this);
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: w */
    public /* synthetic */ void m8057w(View view) {
        this.f7608d.setText("");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: y */
    public /* synthetic */ void m8058y() {
        Intent intent = new Intent();
        intent.putExtra("displayName", this.f7611g.f13717d);
        setResult(-1, intent);
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: z */
    public /* synthetic */ void m8059z(String str, String str2, String str3, String str4) {
        m8060r();
        if ("200".equals(str3)) {
            this.f7611g.f13717d = this.f7608d.getText().toString();
            C2950b0.m14912k().m15089z(String.valueOf(this.f7611g.f13727n), this.f7611g);
            runOnUiThread(new Runnable() { // from class: com.cyberlink.you.activity.u2
                @Override // java.lang.Runnable
                public final void run() {
                    this.f11421b.m8058y();
                }
            });
        }
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_edit_group_name);
        m8061s();
        m8062u();
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        m8060r();
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        CLUtility.m16606x2(this);
    }

    /* renamed from: r */
    public final void m8060r() {
        ProgressDialog progressDialog = this.f7610f;
        if (progressDialog != null) {
            progressDialog.dismiss();
            this.f7610f = null;
        }
    }

    /* renamed from: s */
    public final void m8061s() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            this.f7611g = (Group) extras.getParcelable("Group");
        }
    }

    /* renamed from: u */
    public final void m8062u() {
        this.f7607c = (Button) findViewById(R.id.btnOK);
        this.f7608d = (EditText) findViewById(R.id.GroupEditMainGroupNameEditText);
        this.f7609e = (TextView) findViewById(R.id.ShowWordsCount);
        findViewById(R.id.GroupEditMainBackBtn).setOnClickListener(new View.OnClickListener() { // from class: com.cyberlink.you.activity.q2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f11069b.m8056v(view);
            }
        });
        findViewById(R.id.ProfileInfoUpdateXImage).setOnClickListener(new View.OnClickListener() { // from class: com.cyberlink.you.activity.r2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f11131b.m8057w(view);
            }
        });
        this.f7608d.addTextChangedListener(new C1465a());
        this.f7608d.setText(this.f7611g.f13717d);
        EditText editText = this.f7608d;
        editText.setSelection(editText.length());
        this.f7607c.setOnClickListener(new View.OnClickListener() { // from class: com.cyberlink.you.activity.s2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f11161b.m8048B(view);
            }
        });
    }
}
