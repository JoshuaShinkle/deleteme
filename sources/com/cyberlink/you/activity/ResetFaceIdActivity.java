package com.cyberlink.you.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import com.cyberlink.p030U.R;
import com.cyberlink.you.BaseActivity;
import com.cyberlink.you.Globals;
import com.cyberlink.you.p036ui.C3123g;
import com.cyberlink.you.utility.CLUtility;
import com.perfectcorp.utility.PromisedTask;
import com.perfectcorp.ycl.p040bc.model.network.NetworkLive;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Pattern;

/* loaded from: classes.dex */
public class ResetFaceIdActivity extends BaseActivity {

    /* renamed from: c */
    public View f8687c;

    /* renamed from: d */
    public View f8688d;

    /* renamed from: e */
    public View f8689e;

    /* renamed from: f */
    public View f8690f;

    /* renamed from: g */
    public final View.OnClickListener f8691g = new View.OnClickListener() { // from class: com.cyberlink.you.activity.fd
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f10441b.m9589w(view);
        }
    };

    /* renamed from: com.cyberlink.you.activity.ResetFaceIdActivity$a */
    public class C1661a extends PromisedTask.AbstractC4504d {
        public C1661a() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: b */
        public /* synthetic */ void m9598b(DialogInterface dialogInterface, int i9) {
            ResetFaceIdActivity.this.m9595u(false);
        }

        @Override // com.perfectcorp.utility.PromisedTask.AbstractC4504d
        public void onDone(Object obj) {
            ResetFaceIdActivity.this.f8690f.setVisibility(8);
            ((TextView) ResetFaceIdActivity.this.f8688d.findViewById(R.id.errorMessage)).setText("");
            Globals.m7388i0().m7452L2(false);
            AlertDialog.Builder builderM16382a = C3123g.m16382a(ResetFaceIdActivity.this);
            builderM16382a.setTitle(ResetFaceIdActivity.this.getString(R.string.face_reset_success_title));
            builderM16382a.setMessage(ResetFaceIdActivity.this.getString(R.string.face_reset_success_description));
            builderM16382a.setPositiveButton(ResetFaceIdActivity.this.getString(R.string.close), new DialogInterface.OnClickListener() { // from class: com.cyberlink.you.activity.kd
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i9) {
                    this.f10827b.m9598b(dialogInterface, i9);
                }
            });
            builderM16382a.setCancelable(false);
            builderM16382a.show();
        }

        @Override // com.perfectcorp.utility.PromisedTask
        public void onError(int i9) {
            ResetFaceIdActivity.this.f8690f.setVisibility(8);
            if (i9 == 403) {
                ((TextView) ResetFaceIdActivity.this.f8688d.findViewById(R.id.errorMessage)).setText(R.string.registration_password_invalid);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: B */
    public /* synthetic */ void m9579B(View view) {
        AlertDialog.Builder builderM16382a = C3123g.m16382a(this);
        builderM16382a.setMessage(getString(R.string.face_reset_warning));
        builderM16382a.setPositiveButton(getString(R.string.reset), new DialogInterface.OnClickListener() { // from class: com.cyberlink.you.activity.id
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i9) {
                this.f10754b.m9590y(dialogInterface, i9);
            }
        });
        builderM16382a.setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() { // from class: com.cyberlink.you.activity.jd
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i9) {
                this.f10787b.m9591z(dialogInterface, i9);
            }
        });
        builderM16382a.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: C */
    public /* synthetic */ void m9580C(View view) {
        m9594s();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: w */
    public /* synthetic */ void m9589w(View view) {
        m9592D();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: y */
    public /* synthetic */ void m9590y(DialogInterface dialogInterface, int i9) {
        findViewById(R.id.content).setVisibility(8);
        this.f8688d.setVisibility(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: z */
    public /* synthetic */ void m9591z(DialogInterface dialogInterface, int i9) {
        m9592D();
    }

    /* renamed from: D */
    public final void m9592D() {
        m9595u(true);
    }

    /* renamed from: E */
    public final void m9593E(String str, String str2, String str3) {
        NetworkLive.resetUserFace(str, str2, str3).done(new C1661a());
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_reset_face_id);
        findViewById(R.id.RegistrationBackBtn).setOnClickListener(this.f8691g);
        this.f8687c = findViewById(R.id.resetFaceId);
        this.f8688d = findViewById(R.id.resetLayout);
        this.f8689e = findViewById(R.id.checkReset);
        this.f8690f = findViewById(R.id.check_account);
        ((TextView) this.f8688d.findViewById(R.id.email_account)).setText(Globals.m7388i0().m7498V0());
        this.f8687c.setOnClickListener(new View.OnClickListener() { // from class: com.cyberlink.you.activity.gd
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f10693b.m9579B(view);
            }
        });
        this.f8689e.setOnClickListener(new View.OnClickListener() { // from class: com.cyberlink.you.activity.hd
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f10724b.m9580C(view);
            }
        });
    }

    /* renamed from: s */
    public final void m9594s() {
        TextView textView = (TextView) this.f8688d.findViewById(R.id.email_account);
        TextView textView2 = (TextView) this.f8688d.findViewById(R.id.password);
        String string = textView.getText().toString();
        String string2 = textView2.getText().toString();
        TextView textView3 = (TextView) this.f8688d.findViewById(R.id.errorMessage);
        if (TextUtils.isEmpty(string2)) {
            textView3.setText(R.string.hint_password_enter);
        } else if (m9596v(string2)) {
            textView3.setText(R.string.registration_password_invalid);
        }
        m9593E(Globals.m7388i0().m7506X(), string, string2);
    }

    /* renamed from: u */
    public final void m9595u(boolean z8) {
        CLUtility.m16589t1(this);
        Globals.m7388i0().m7452L2(z8);
        setResult(-1, new Intent());
        finish();
    }

    /* renamed from: v */
    public final boolean m9596v(String str) {
        ArrayList arrayList = new ArrayList();
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
}
