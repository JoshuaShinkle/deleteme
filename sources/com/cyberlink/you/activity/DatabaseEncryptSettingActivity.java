package com.cyberlink.you.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.cyberlink.p030U.R;
import com.cyberlink.you.BaseActivity;
import com.cyberlink.you.Globals;
import com.cyberlink.you.friends.FriendsClient;
import com.cyberlink.you.p036ui.C3123g;
import java.util.ArrayList;
import p116k4.C5152i0;
import p116k4.C5179r0;
import p201t3.C6301o;
import p209u2.C6385v;

/* loaded from: classes.dex */
public class DatabaseEncryptSettingActivity extends BaseActivity {

    /* renamed from: c */
    public View.OnClickListener f7592c = new View.OnClickListener() { // from class: com.cyberlink.you.activity.x1
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f12242b.m7998C(view);
        }
    };

    /* renamed from: d */
    public View.OnClickListener f7593d = new View.OnClickListener() { // from class: com.cyberlink.you.activity.y1
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f12265b.m7999D(view);
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: B */
    public /* synthetic */ void m7997B(AlertDialog alertDialog, DialogInterface dialogInterface) throws Resources.NotFoundException {
        alertDialog.getButton(-2).setTextColor(getResources().getColor(R.color.you_color_text_waring_red, getTheme()));
        C5179r0.m20247b(alertDialog.getButton(-2), 1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: C */
    public /* synthetic */ void m7998C(View view) {
        AlertDialog.Builder builderM16382a = C3123g.m16382a(this);
        builderM16382a.setMessage(getString(R.string.setting_local_database_encryption_sign_out_warning));
        builderM16382a.setPositiveButton(getString(R.string.cancel), new DialogInterface.OnClickListener() { // from class: com.cyberlink.you.activity.z1
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i9) {
                dialogInterface.dismiss();
            }
        });
        builderM16382a.setNegativeButton(getString(R.string.setting_local_database_encryption_sign_out_continue), new DialogInterface.OnClickListener() { // from class: com.cyberlink.you.activity.a2
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i9) {
                this.f9704b.m8012z(dialogInterface, i9);
            }
        });
        final AlertDialog alertDialogCreate = builderM16382a.create();
        alertDialogCreate.setOnShowListener(new DialogInterface.OnShowListener() { // from class: com.cyberlink.you.activity.b2
            @Override // android.content.DialogInterface.OnShowListener
            public final void onShow(DialogInterface dialogInterface) throws Resources.NotFoundException {
                this.f9736a.m7997B(alertDialogCreate, dialogInterface);
            }
        });
        alertDialogCreate.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: D */
    public /* synthetic */ void m7999D(View view) {
        m8013E();
    }

    /* renamed from: v */
    public static /* synthetic */ void m8009v() {
        Globals.m7388i0().m7407C(true);
    }

    /* renamed from: w */
    public static /* synthetic */ void m8010w(DialogInterface dialogInterface, int i9) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: y */
    public /* synthetic */ void m8011y(FriendsClient friendsClient, ProgressDialog progressDialog, String str, String str2, String str3, String str4) {
        friendsClient.m15717U0();
        C5152i0.m20065b(progressDialog);
        if (str3 != null && str3.equals("200")) {
            C6385v.m24527e(new Runnable() { // from class: com.cyberlink.you.activity.d2
                @Override // java.lang.Runnable
                public final void run() {
                    DatabaseEncryptSettingActivity.m8009v();
                }
            });
            return;
        }
        AlertDialog.Builder builderM16382a = C3123g.m16382a(this);
        builderM16382a.setTitle(getString(R.string.feedback_error));
        builderM16382a.setMessage(getString(R.string.sign_out_unsuccessful));
        builderM16382a.setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() { // from class: com.cyberlink.you.activity.e2
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i9) {
                DatabaseEncryptSettingActivity.m8010w(dialogInterface, i9);
            }
        });
        builderM16382a.create().show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: z */
    public /* synthetic */ void m8012z(DialogInterface dialogInterface, int i9) {
        final FriendsClient friendsClient = new FriendsClient();
        ArrayList arrayList = new ArrayList();
        arrayList.add(new C6301o("token", Globals.m7388i0().m7449L()));
        final ProgressDialog progressDialogShow = ProgressDialog.show(this, "", getString(R.string.loading), true);
        friendsClient.m15734m("user", "signOut", arrayList, new FriendsClient.InterfaceC3051i() { // from class: com.cyberlink.you.activity.c2
            @Override // com.cyberlink.you.friends.FriendsClient.InterfaceC3051i
            /* renamed from: a */
            public final void mo134a(String str, String str2, String str3, String str4) {
                this.f9767a.m8011y(friendsClient, progressDialogShow, str, str2, str3, str4);
            }
        });
    }

    /* renamed from: E */
    public final void m8013E() {
        finish();
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        m8013E();
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_database_encrypt_setting);
        m8014s();
    }

    /* renamed from: s */
    public final void m8014s() {
        ((ImageView) findViewById(R.id.DatabaseEncryptSettingBackBtn)).setOnClickListener(this.f7593d);
        View viewFindViewById = findViewById(R.id.DatabaseEncryptSetting);
        viewFindViewById.setOnClickListener(this.f7592c);
        ((TextView) viewFindViewById.findViewById(R.id.EditCheckTextView)).setText(getResources().getString(R.string.setting_local_database_encryption));
        viewFindViewById.setSelected(Globals.m7388i0().m7409C1().booleanValue());
    }
}
