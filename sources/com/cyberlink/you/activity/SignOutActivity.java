package com.cyberlink.you.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import com.cyberlink.p030U.R;
import com.cyberlink.you.BaseActivity;
import com.cyberlink.you.Globals;
import com.cyberlink.you.friends.FriendsClient;
import com.cyberlink.you.p036ui.C3123g;
import java.util.ArrayList;
import p116k4.C5152i0;
import p201t3.C6301o;
import p209u2.C6385v;

/* loaded from: classes.dex */
public class SignOutActivity extends BaseActivity {

    /* renamed from: c */
    public View.OnClickListener f9114c = new View.OnClickListener() { // from class: com.cyberlink.you.activity.gg
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f10696b.m10102r(view);
        }
    };

    /* renamed from: d */
    public View.OnClickListener f9115d = new View.OnClickListener() { // from class: com.cyberlink.you.activity.hg
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f10727b.m10107y(view);
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: r */
    public /* synthetic */ void m10102r(View view) {
        m10109z();
    }

    /* renamed from: s */
    public static /* synthetic */ void m10103s() {
        Globals.m7388i0().m7402B();
    }

    /* renamed from: u */
    public static /* synthetic */ void m10104u(DialogInterface dialogInterface, int i9) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: v */
    public /* synthetic */ void m10105v() {
        AlertDialog.Builder builderM16382a = C3123g.m16382a(this);
        builderM16382a.setTitle(getString(R.string.feedback_error));
        builderM16382a.setMessage(getString(R.string.sign_out_unsuccessful));
        builderM16382a.setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() { // from class: com.cyberlink.you.activity.lg
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i9) {
                SignOutActivity.m10104u(dialogInterface, i9);
            }
        });
        builderM16382a.create().show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: w */
    public /* synthetic */ void m10106w(FriendsClient friendsClient, ProgressDialog progressDialog, String str, String str2, String str3, String str4) {
        friendsClient.m15717U0();
        C5152i0.m20065b(progressDialog);
        if (str3 == null || !str3.equals("200")) {
            C6385v.m24527e(new Runnable() { // from class: com.cyberlink.you.activity.kg
                @Override // java.lang.Runnable
                public final void run() {
                    this.f10831b.m10105v();
                }
            });
        } else {
            C6385v.m24527e(new Runnable() { // from class: com.cyberlink.you.activity.jg
                @Override // java.lang.Runnable
                public final void run() {
                    SignOutActivity.m10103s();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: y */
    public /* synthetic */ void m10107y(View view) {
        final FriendsClient friendsClient = new FriendsClient();
        ArrayList arrayList = new ArrayList();
        arrayList.add(new C6301o("token", Globals.m7388i0().m7449L()));
        final ProgressDialog progressDialogShow = ProgressDialog.show(this, "", getString(R.string.loading), true);
        friendsClient.m15734m("user", "signOut", arrayList, new FriendsClient.InterfaceC3051i() { // from class: com.cyberlink.you.activity.ig
            @Override // com.cyberlink.you.friends.FriendsClient.InterfaceC3051i
            /* renamed from: a */
            public final void mo134a(String str, String str2, String str3, String str4) {
                this.f10757a.m10106w(friendsClient, progressDialogShow, str, str2, str3, str4);
            }
        });
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        m10109z();
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_sign_out);
        m10108q();
    }

    /* renamed from: q */
    public final void m10108q() {
        ((ImageView) findViewById(R.id.SignOutSettingBackBtn)).setOnClickListener(this.f9114c);
        findViewById(R.id.SignOutTextView).setOnClickListener(this.f9115d);
    }

    /* renamed from: z */
    public final void m10109z() {
        finish();
    }
}
