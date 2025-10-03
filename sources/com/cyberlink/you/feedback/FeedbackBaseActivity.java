package com.cyberlink.you.feedback;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import com.cyberlink.p030U.R;
import com.cyberlink.you.BaseActivity;
import com.cyberlink.you.feedback.TopBarFragment;
import com.cyberlink.you.p036ui.C3123g;
import com.cyberlink.you.utility.CLUtility;

/* loaded from: classes.dex */
public class FeedbackBaseActivity extends BaseActivity implements TopBarFragment.InterfaceC3023a {

    /* renamed from: c */
    public TopBarFragment f13353c = null;

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: o */
    public /* synthetic */ void m15393o(DialogInterface dialogInterface, int i9) {
        m15398q();
    }

    /* renamed from: b */
    public void mo15390b() {
        m15398q();
    }

    /* renamed from: j */
    public TopBarFragment m15394j() {
        if (this.f13353c == null) {
            this.f13353c = (TopBarFragment) getFragmentManager().findFragmentById(R.id.fragment_topbar_panel);
        }
        return this.f13353c;
    }

    /* renamed from: k */
    public void m15395k() {
        TopBarFragment topBarFragmentM15394j = m15394j();
        if (topBarFragmentM15394j != null) {
            topBarFragmentM15394j.m15455g(this);
        }
        m15400s();
    }

    /* renamed from: l */
    public void m15396l(int i9) {
        m15397n(getString(i9));
    }

    /* renamed from: n */
    public void m15397n(String str) {
        TopBarFragment topBarFragmentM15394j = m15394j();
        if (topBarFragmentM15394j != null) {
            topBarFragmentM15394j.m15458j(str);
        }
        m15395k();
    }

    public void onRightBtnClick(View view) {
    }

    /* renamed from: q */
    public void m15398q() {
        CLUtility.m16589t1(this);
        if (CLUtility.m16425C1(this)) {
            if (getClass().getName().equals(EditFeedbackActivity.class.getName())) {
                startActivity(getPackageManager().getLaunchIntentForPackage(getPackageName()));
            } else {
                startActivity(new Intent(this, (Class<?>) EditFeedbackActivity.class));
            }
        }
        finish();
    }

    /* renamed from: r */
    public void m15399r(String str, String str2, Activity activity) {
        AlertDialog.Builder builderM16382a = C3123g.m16382a(activity);
        builderM16382a.setTitle(str);
        builderM16382a.setMessage(str2);
        builderM16382a.setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() { // from class: p3.e
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i9) {
                this.f20685b.m15393o(dialogInterface, i9);
            }
        });
        builderM16382a.create().show();
    }

    /* renamed from: s */
    public void m15400s() {
        TopBarFragment topBarFragmentM15394j = m15394j();
        if (topBarFragmentM15394j != null) {
            topBarFragmentM15394j.m15461m();
        }
    }
}
