package com.cyberlink.you.feedback;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.cyberlink.p030U.R;
import com.cyberlink.you.Globals;
import com.cyberlink.you.feedback.EditFeedbackActivity;
import com.cyberlink.you.feedback.NetworkFeedback;
import com.cyberlink.you.friends.FriendsClient;
import com.cyberlink.you.p036ui.C3123g;
import java.util.ArrayList;
import java.util.Iterator;
import p116k4.AbstractC5146g0;
import p116k4.C5170o0;
import p116k4.C5178r;
import p116k4.C5179r0;
import p116k4.C5182t;
import p116k4.C5187v0;
import p135m2.C5314a;

/* loaded from: classes.dex */
public class EditFeedbackActivity extends FeedbackBaseActivity {

    /* renamed from: d */
    public TextView f13342d;

    /* renamed from: e */
    public TextView f13343e;

    /* renamed from: f */
    public LinearLayout f13344f;

    /* renamed from: g */
    public NetworkFeedback.FeedbackConfig f13345g;

    /* renamed from: h */
    public ArrayList<C3028a> f13346h = new ArrayList<>();

    /* renamed from: i */
    public ArrayList<Uri> f13347i = new ArrayList<>();

    /* renamed from: j */
    public String f13348j = "FeedbackAttachedImages";

    /* renamed from: k */
    public View.OnClickListener f13349k = new View.OnClickListener() { // from class: p3.a
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f20682b.m15383E(view);
        }
    };

    /* renamed from: l */
    public String f13350l = null;

    /* renamed from: m */
    public String f13351m = null;

    /* renamed from: n */
    public View.OnClickListener f13352n = new View.OnClickListener() { // from class: p3.b
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f20683b.m15384H(view);
        }
    };

    /* renamed from: B */
    public static String m15380B() {
        String strM15642G = FriendsClient.m15642G("info", "appFeedbackUrl");
        if (C5170o0.m20170e(strM15642G)) {
            return null;
        }
        return strM15642G;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: C */
    public /* synthetic */ void m15381C(DialogInterface dialogInterface, int i9) {
        m15398q();
    }

    /* renamed from: D */
    public static /* synthetic */ void m15382D(DialogInterface dialogInterface, int i9) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: E */
    public /* synthetic */ void m15383E(View view) {
        C5178r.m20243l(this, 48138);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: H */
    public /* synthetic */ void m15384H(View view) {
        ArrayList arrayList = new ArrayList();
        TextView textView = this.f13342d;
        if (textView != null) {
            String string = textView.getText().toString();
            this.f13350l = string;
            if (string.isEmpty()) {
                Toast.makeText(this, Globals.m7375Z0(R.string.bc_feedback_dialog_missing_description), 1).show();
                return;
            }
        }
        TextView textView2 = this.f13343e;
        if (textView2 != null) {
            String strTrim = textView2.getText().toString().trim();
            this.f13351m = strTrim;
            if (strTrim.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(this.f13351m).matches()) {
                Toast.makeText(this, Globals.m7375Z0(R.string.bc_feedback_dialog_missing_email), 1).show();
                return;
            }
        }
        ArrayList<C3028a> arrayList2 = this.f13346h;
        if (arrayList2 != null && !arrayList2.isEmpty()) {
            Iterator<C3028a> it = this.f13346h.iterator();
            while (it.hasNext()) {
                C3028a next = it.next();
                if (next != null && next.m15486h() != null) {
                    arrayList.add(next.m15486h());
                }
            }
        }
        C3030c.m15496a(this, this.f13345g, this.f13350l, this.f13351m, arrayList, "");
    }

    /* renamed from: I */
    public final void m15389I(String str, String str2) {
        AlertDialog.Builder builderM16382a = C3123g.m16382a(this);
        builderM16382a.setTitle(str);
        builderM16382a.setMessage(str2);
        builderM16382a.setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() { // from class: p3.c
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i9) {
                this.f20684b.m15381C(dialogInterface, i9);
            }
        });
        builderM16382a.setNegativeButton(getString(R.string.btn_no), new DialogInterface.OnClickListener() { // from class: p3.d
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i9) {
                EditFeedbackActivity.m15382D(dialogInterface, i9);
            }
        });
        builderM16382a.create().show();
    }

    @Override // com.cyberlink.you.feedback.FeedbackBaseActivity, com.cyberlink.you.feedback.TopBarFragment.InterfaceC3023a
    /* renamed from: b */
    public void mo15390b() {
        TextView textView = this.f13342d;
        if (textView == null || this.f13343e == null || this.f13346h == null) {
            m15398q();
            return;
        }
        this.f13350l = textView.getText().toString();
        this.f13351m = this.f13343e.getText().toString();
        C3028a c3028a = this.f13346h.isEmpty() ? null : this.f13346h.get(0);
        Uri uriM15486h = c3028a != null ? c3028a.m15486h() : null;
        if (this.f13350l.isEmpty() && uriM15486h == null) {
            m15398q();
        } else {
            m15389I(Globals.m7375Z0(R.string.u_app_name), Globals.m7375Z0(R.string.feedback_warning));
        }
    }

    @Override // android.app.Activity
    public void onActivityResult(int i9, int i10, Intent intent) {
        super.onActivityResult(i9, i10, intent);
        if (i9 != 48138) {
            if (i9 == 48160 && i10 == -1) {
                m15398q();
                return;
            }
            return;
        }
        if (i10 != -1) {
            Log.v("EditFeedbackActivity", "User canceled PickFromGallery");
        } else {
            m15391z().m15488j(intent.getData(), true);
        }
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        mo15390b();
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        String strM15380B = m15380B();
        if (strM15380B == null) {
            C5187v0.m20270f(R.string.error_server_response);
            finish();
            return;
        }
        setContentView(R.layout.activity_edit_feedback);
        if (bundle != null && bundle.containsKey(this.f13348j)) {
            this.f13347i = bundle.getParcelableArrayList(this.f13348j);
        }
        NetworkFeedback.FeedbackConfig feedbackConfig = new NetworkFeedback.FeedbackConfig();
        this.f13345g = feedbackConfig;
        feedbackConfig.apiUri = strM15380B;
        feedbackConfig.product = "U";
        feedbackConfig.version = "1.0";
        feedbackConfig.f13354sr = "YOU250505-04";
        feedbackConfig.hwid = AbstractC5146g0.m20044b(this);
        this.f13345g.phoneid = Settings.Secure.getString(getContentResolver(), "android_id");
        this.f13345g.appversion = Globals.m7391n1();
        this.f13345g.extrainfo = "uid:" + Globals.m7388i0().m7568k1() + "; token:" + Globals.m7388i0().m7449L() + "; build:" + Globals.m7368J() + "; device:" + Build.DEVICE + "; product:" + Build.PRODUCT + "; board:" + Build.BOARD + "; hardware:" + Build.HARDWARE + ";\n" + C5314a.m20792b() + "; " + C5314a.m20791a();
        m15396l(R.string.setting_feedback);
        TextView textView = (TextView) findViewById(R.id.edit_feedback_text);
        this.f13342d = textView;
        if (textView != null) {
            textView.setHint(R.string.bc_feedback_hint);
        }
        this.f13343e = (TextView) findViewById(R.id.edit_feedback_email);
        this.f13343e.setText(Globals.m7388i0().m7498V0());
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.edit_feedback_image_layout);
        this.f13344f = linearLayout;
        if (linearLayout != null) {
            linearLayout.setOnClickListener(this.f13349k);
        }
        ArrayList<Uri> arrayList = this.f13347i;
        if (arrayList != null && !arrayList.isEmpty()) {
            Iterator<Uri> it = this.f13347i.iterator();
            while (it.hasNext()) {
                Uri next = it.next();
                if (next != null) {
                    m15391z().m15488j(next, true);
                }
            }
        }
        C5182t.m20260d(this, (TextView) findViewById(R.id.edit_feedback_privacy_policy), getString(R.string.feedback_agree_to_provide_data, C5182t.m20257a()));
        TextView textView2 = (TextView) findViewById(R.id.edit_feedback_submit);
        textView2.setOnClickListener(this.f13352n);
        C5179r0.m20247b(textView2, 1);
    }

    @Override // com.cyberlink.you.feedback.FeedbackBaseActivity, com.cyberlink.you.feedback.TopBarFragment.InterfaceC3023a
    public void onRightBtnClick(View view) {
    }

    @Override // android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        this.f13347i.clear();
        ArrayList<C3028a> arrayList = this.f13346h;
        if (arrayList != null && !arrayList.isEmpty()) {
            Iterator<C3028a> it = this.f13346h.iterator();
            while (it.hasNext()) {
                this.f13347i.add(it.next().m15486h());
            }
        }
        ArrayList<Uri> arrayList2 = this.f13347i;
        if (arrayList2 == null || arrayList2.isEmpty()) {
            return;
        }
        bundle.putParcelableArrayList(this.f13348j, this.f13347i);
    }

    /* renamed from: z */
    public final C3028a m15391z() {
        C3028a c3028a = new C3028a(this, false);
        this.f13346h.add(c3028a);
        ViewGroup viewGroup = (ViewGroup) findViewById(R.id.edit_feedback_image_panel);
        viewGroup.addView(c3028a.m15487i(LayoutInflater.from(this), viewGroup));
        return c3028a;
    }
}
