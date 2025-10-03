package com.cyberlink.you.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.cyberlink.p030U.R;
import com.cyberlink.you.BaseActivity;
import com.cyberlink.you.p036ui.C3123g;
import java.util.concurrent.atomic.AtomicBoolean;
import p126l3.C5284c;
import p145n3.C5361c;
import p145n3.InterfaceC5360b;

/* loaded from: classes.dex */
public class FaceVerificationActivity extends BaseActivity {

    /* renamed from: c */
    public TextureView f7685c;

    /* renamed from: d */
    public ImageView f7686d;

    /* renamed from: e */
    public ImageView f7687e;

    /* renamed from: f */
    public C5284c f7688f;

    /* renamed from: g */
    public View f7689g;

    /* renamed from: h */
    public View f7690h;

    /* renamed from: i */
    public View f7691i;

    /* renamed from: j */
    public View f7692j;

    /* renamed from: k */
    public String f7693k;

    /* renamed from: l */
    public String f7694l;

    /* renamed from: s */
    public long f7701s;

    /* renamed from: m */
    public final int f7695m = 20;

    /* renamed from: n */
    public final int f7696n = 15;

    /* renamed from: o */
    public final int f7697o = 10;

    /* renamed from: p */
    public long f7698p = 0;

    /* renamed from: q */
    public boolean f7699q = false;

    /* renamed from: r */
    public final AtomicBoolean f7700r = new AtomicBoolean(false);

    /* renamed from: t */
    public final InterfaceC5360b<Bitmap> f7702t = new C1478a();

    /* renamed from: u */
    public final View.OnClickListener f7703u = new View.OnClickListener() { // from class: com.cyberlink.you.activity.n3
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f10914b.m8190w(view);
        }
    };

    /* renamed from: com.cyberlink.you.activity.FaceVerificationActivity$a */
    public class C1478a implements InterfaceC5360b<Bitmap> {
        public C1478a() {
        }

        @Override // p145n3.InterfaceC5360b
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void mo8198a(Bitmap bitmap) {
            FaceVerificationActivity.m8187q(FaceVerificationActivity.this);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: B */
    public /* synthetic */ void m8180B(View view) {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            boolean z8 = extras.getBoolean("faceIdCapacity", true);
            boolean z9 = extras.getBoolean("bindFaceId", true);
            if (!z8) {
                m8196r("faceIdCapacity", extras);
            } else if (z9) {
                m8195E();
            } else {
                m8196r("bindFaceId", extras);
            }
        }
    }

    /* renamed from: q */
    public static /* synthetic */ C5361c m8187q(FaceVerificationActivity faceVerificationActivity) {
        faceVerificationActivity.getClass();
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: u */
    public /* synthetic */ void m8188u(Bundle bundle, DialogInterface dialogInterface, int i9) {
        dialogInterface.dismiss();
        Intent intent = getIntent();
        intent.putExtras(bundle);
        setResult(-1, intent);
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: v */
    public /* synthetic */ void m8189v(String str, Bundle bundle, DialogInterface dialogInterface, int i9) {
        dialogInterface.dismiss();
        if ("bindFaceId".equals(str)) {
            Intent intent = getIntent();
            bundle.putInt("joinMeetingErrorCode", 403);
            bundle.putString("meetingErrorBody", "Invalid face");
            bundle.putBoolean("bindFaceId", true);
            intent.putExtras(bundle);
            setResult(-1, intent);
            finish();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: w */
    public /* synthetic */ void m8190w(View view) {
        m8193C();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: y */
    public /* synthetic */ void m8191y(View view) {
        this.f7691i.setVisibility(8);
        this.f7692j.setVisibility(8);
        this.f7689g.setVisibility(0);
        this.f7700r.set(false);
        this.f7701s = System.currentTimeMillis();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: z */
    public /* synthetic */ void m8192z(View view) {
        m8194D();
        Intent intent = new Intent();
        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            extras = new Bundle();
        }
        extras.putInt("joinMeetingErrorCode", 403);
        extras.putString("meetingErrorBody", "Invalid face");
        intent.putExtras(extras);
        setResult(-1, intent);
        finish();
    }

    /* renamed from: C */
    public final void m8193C() {
        m8194D();
        finish();
    }

    /* renamed from: D */
    public final void m8194D() {
        C5284c c5284c = this.f7688f;
        if (c5284c != null) {
            c5284c.m20571e();
        }
    }

    /* renamed from: E */
    public final void m8195E() {
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        m8193C();
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_face_verification);
        this.f7685c = (TextureView) findViewById(R.id.face_preview);
        this.f7689g = findViewById(R.id.verifyState);
        this.f7690h = findViewById(R.id.passState);
        this.f7691i = findViewById(R.id.failState);
        this.f7692j = findViewById(R.id.failChoose);
        this.f7686d = (ImageView) findViewById(R.id.face_OK);
        this.f7687e = (ImageView) findViewById(R.id.face_detect);
        findViewById(R.id.FaceVerificationBackBtn).setOnClickListener(this.f7703u);
        m8197s();
        findViewById(R.id.retryAgain).setOnClickListener(new View.OnClickListener() { // from class: com.cyberlink.you.activity.k3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f10809b.m8191y(view);
            }
        });
        findViewById(R.id.verifyByPassword).setOnClickListener(new View.OnClickListener() { // from class: com.cyberlink.you.activity.l3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f10841b.m8192z(view);
            }
        });
        findViewById(R.id.startVerifyBtn).setOnClickListener(new View.OnClickListener() { // from class: com.cyberlink.you.activity.m3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f10878b.m8180B(view);
            }
        });
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        m8194D();
    }

    /* renamed from: r */
    public final void m8196r(final String str, final Bundle bundle) {
        String string;
        String string2;
        String string3;
        String string4;
        if ("faceIdCapacity".equals(str)) {
            string = getString(R.string.clm_error_fail_title);
            string2 = getString(R.string.face_fail_android_version_v2);
            string3 = getString(R.string.face_fail_android_version_guideline_v2);
            string4 = getString(R.string.close);
        } else if ("bindFaceId".equals(str)) {
            string = getString(R.string.clm_error_fail_title);
            string2 = getString(R.string.clm_error_face_reason_v2);
            string3 = getString(R.string.face_fail_join_meeting_set_up_v2);
            string4 = getString(R.string.not_now);
        } else {
            string = "";
            string2 = "";
            string3 = string2;
            string4 = string3;
        }
        AlertDialog.Builder builderM16382a = C3123g.m16382a(this);
        builderM16382a.setTitle(string).setMessage(string2).setCancelable(false);
        builderM16382a.setNegativeButton(string3, new DialogInterface.OnClickListener() { // from class: com.cyberlink.you.activity.o3
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i9) {
                this.f10996b.m8188u(bundle, dialogInterface, i9);
            }
        });
        builderM16382a.setPositiveButton(string4, new DialogInterface.OnClickListener() { // from class: com.cyberlink.you.activity.p3
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i9) {
                this.f11028b.m8189v(str, bundle, dialogInterface, i9);
            }
        });
        AlertDialog alertDialogShow = builderM16382a.show();
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -1, 2.0f);
        Button button = alertDialogShow.getButton(-1);
        if (button != null) {
            ((LinearLayout) button.getParent()).setOrientation(1);
            button.setLayoutParams(layoutParams);
            button.setTextColor(getResources().getColor(R.color.face_verification_blue));
        }
        Button button2 = alertDialogShow.getButton(-2);
        if (button2 != null) {
            button2.setLayoutParams(layoutParams);
            button2.setTextColor(getResources().getColor(R.color.face_verification_blue));
        }
    }

    /* renamed from: s */
    public final void m8197s() {
        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            finish();
        } else {
            this.f7693k = extras.getString("meetingId");
            this.f7694l = extras.getString("displayName");
        }
    }
}
