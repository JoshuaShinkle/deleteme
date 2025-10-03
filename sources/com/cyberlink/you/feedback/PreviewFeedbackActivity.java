package com.cyberlink.you.feedback;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.LocaleList;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.DecelerateInterpolator;
import android.widget.TextView;
import android.widget.Toast;
import com.cyberlink.p030U.R;
import com.cyberlink.you.Globals;
import com.cyberlink.you.feedback.C3029b;
import com.cyberlink.you.feedback.C3032e;
import com.cyberlink.you.feedback.NetworkFeedback;
import com.cyberlink.you.feedback.PromisedTask;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import java.util.TimeZone;
import p116k4.C5152i0;

/* loaded from: classes.dex */
public class PreviewFeedbackActivity extends FeedbackBaseActivity {

    /* renamed from: d */
    public NetworkFeedback.FeedbackConfig f13391d;

    /* renamed from: e */
    public NetworkFeedback.C3012c f13392e;

    /* renamed from: f */
    public ArrayList<Uri> f13393f;

    /* renamed from: h */
    public TextView f13395h;

    /* renamed from: i */
    public TextView f13396i;

    /* renamed from: j */
    public TextView f13397j;

    /* renamed from: k */
    public TextView f13398k;

    /* renamed from: l */
    public TextView f13399l;

    /* renamed from: m */
    public TextView f13400m;

    /* renamed from: o */
    public String f13402o;

    /* renamed from: p */
    public PromisedTask.AbstractC3021b<NetworkFeedback.C3013d> f13403p;

    /* renamed from: q */
    public Dialog f13404q;

    /* renamed from: g */
    public String f13394g = "PreviewFeedbackActivity";

    /* renamed from: n */
    public ArrayList<C3028a> f13401n = new ArrayList<>();

    /* renamed from: com.cyberlink.you.feedback.PreviewFeedbackActivity$a */
    public class C3018a extends PromisedTask<Void, Void, Void> {

        /* renamed from: j */
        public final /* synthetic */ String f13405j;

        /* renamed from: com.cyberlink.you.feedback.PreviewFeedbackActivity$a$a */
        public class a extends PromisedTask.AbstractC3021b<NetworkFeedback.C3013d> {
            public a() {
            }

            @Override // com.cyberlink.you.feedback.PromisedTask
            /* renamed from: j */
            public void mo15434j() {
                PreviewFeedbackActivity.this.m15427H();
                super.mo15434j();
            }

            @Override // com.cyberlink.you.feedback.PromisedTask
            /* renamed from: k */
            public void mo5702k(int i9, String str) {
                PreviewFeedbackActivity.this.m15427H();
                super.mo5702k(i9, str);
                Toast.makeText(PreviewFeedbackActivity.this, Globals.m7375Z0(R.string.feedback_unknown_error), 1).show();
            }

            @Override // com.cyberlink.you.feedback.PromisedTask.AbstractC3021b
            /* renamed from: r, reason: merged with bridge method [inline-methods] */
            public void mo5703q(NetworkFeedback.C3013d c3013d) {
                PreviewFeedbackActivity.this.m15427H();
                if (!"OK".equals(c3013d != null ? c3013d.status : "")) {
                    mo5702k(-2147483647, null);
                } else {
                    PreviewFeedbackActivity.this.setResult(-1);
                    PreviewFeedbackActivity.this.m15399r(Globals.m7375Z0(R.string.feedback_thank_you), Globals.m7375Z0(R.string.feedback_thank_you_description), PreviewFeedbackActivity.this);
                }
            }
        }

        public C3018a(String str) {
            this.f13405j = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: s */
        public /* synthetic */ void m15432s() {
            PreviewFeedbackActivity.this.m15427H();
        }

        @Override // com.cyberlink.you.feedback.PromisedTask
        /* renamed from: r, reason: merged with bridge method [inline-methods] */
        public Void mo5659d(Void r62) {
            if (PreviewFeedbackActivity.this.f13392e.f13374r == null) {
                PreviewFeedbackActivity.this.f13392e.f13374r = new ArrayList<>();
            }
            if (PreviewFeedbackActivity.this.f13393f != null && !PreviewFeedbackActivity.this.f13393f.isEmpty()) {
                Iterator it = PreviewFeedbackActivity.this.f13393f.iterator();
                while (it.hasNext()) {
                    Uri uri = (Uri) it.next();
                    it.remove();
                    if (uri != null) {
                        PreviewFeedbackActivity.this.f13392e.f13374r.add(C3029b.m15491b(uri, C3029b.a.f13478d));
                    }
                }
            }
            if (PreviewFeedbackActivity.this.f13392e.f13374r != null) {
                Iterator<C3032e.a> it2 = PreviewFeedbackActivity.this.f13392e.f13374r.iterator();
                long j9 = 0;
                while (it2.hasNext()) {
                    j9 += it2.next().f13493b;
                }
                Log.i("Attachment size: ", Integer.toString((int) j9));
                if (j9 > 5242880) {
                    PreviewFeedbackActivity.this.runOnUiThread(new Runnable() { // from class: p3.j
                        @Override // java.lang.Runnable
                        public final void run() {
                            this.f20695b.m15432s();
                        }
                    });
                    PreviewFeedbackActivity.this.m15399r(Globals.m7375Z0(R.string.feedback_error), Globals.m7375Z0(R.string.feedback_file_oversize), PreviewFeedbackActivity.this);
                    return null;
                }
            }
            PreviewFeedbackActivity.this.f13403p = new a();
            NetworkFeedback.m15401a(this.f13405j, PreviewFeedbackActivity.this.f13392e, PreviewFeedbackActivity.this).m15439e(PreviewFeedbackActivity.this.f13403p);
            return null;
        }
    }

    /* renamed from: com.cyberlink.you.feedback.PreviewFeedbackActivity$b */
    public class C3019b implements Animator.AnimatorListener {

        /* renamed from: a */
        public final /* synthetic */ Runnable f13408a;

        public C3019b(Runnable runnable) {
            this.f13408a = runnable;
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            Runnable runnable = this.f13408a;
            if (runnable != null) {
                runnable.run();
            }
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationRepeat(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: L */
    public /* synthetic */ void m15417L() {
        Dialog dialog;
        if (isFinishing() || (dialog = this.f13404q) == null || !dialog.isShowing()) {
            return;
        }
        Log.d("BaseAct closeProgress", "closeProgress");
        this.f13404q.dismiss();
        this.f13404q = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: N */
    public /* synthetic */ void m15418N(DialogInterface dialogInterface) {
        this.f13404q = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: O */
    public /* synthetic */ void m15419O(DialogInterface.OnClickListener onClickListener, DialogInterface dialogInterface) {
        onClickListener.onClick(this.f13404q, -2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: P */
    public /* synthetic */ void m15420P(Float f9, int i9, final DialogInterface.OnClickListener onClickListener, Runnable runnable) {
        if (this.f13404q != null) {
            if ((!((ProgressDialog) r0).isIndeterminate()) ^ (f9 != null)) {
                Log.e("BaseAct showProgress", "dismiss current ProgressDialog");
                this.f13404q.dismiss();
                this.f13404q = null;
            }
        }
        if (this.f13404q == null) {
            ProgressDialog progressDialog = new ProgressDialog(this);
            this.f13404q = progressDialog;
            progressDialog.setMessage(getString(i9));
            this.f13404q.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: p3.g
                @Override // android.content.DialogInterface.OnDismissListener
                public final void onDismiss(DialogInterface dialogInterface) {
                    this.f20691b.m15418N(dialogInterface);
                }
            });
            if (f9 != null) {
                ((ProgressDialog) this.f13404q).setIndeterminate(false);
                ((ProgressDialog) this.f13404q).setProgressStyle(1);
                ((ProgressDialog) this.f13404q).setProgressNumberFormat(null);
            } else {
                ((ProgressDialog) this.f13404q).setIndeterminate(true);
                ((ProgressDialog) this.f13404q).setProgressStyle(0);
            }
            if (onClickListener != null) {
                this.f13404q.setCancelable(true);
                ((ProgressDialog) this.f13404q).setButton(-2, getResources().getText(android.R.string.cancel), onClickListener);
                this.f13404q.setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: p3.h
                    @Override // android.content.DialogInterface.OnCancelListener
                    public final void onCancel(DialogInterface dialogInterface) {
                        this.f20692b.m15419O(onClickListener, dialogInterface);
                    }
                });
            } else {
                this.f13404q.setCancelable(false);
            }
            ((ProgressDialog) this.f13404q).setMax(10000);
            this.f13404q.show();
        }
        if (f9 != null) {
            ObjectAnimator duration = ObjectAnimator.ofInt(this.f13404q, "progress", (int) (f9.floatValue() * 10000.0f)).setDuration(1000L);
            duration.addListener(new C3019b(runnable));
            duration.setInterpolator(new DecelerateInterpolator());
            duration.start();
        }
    }

    /* renamed from: E */
    public final C3028a m15426E() {
        C3028a c3028a = new C3028a(this, true);
        this.f13401n.add(c3028a);
        ViewGroup viewGroup = (ViewGroup) findViewById(R.id.bc_feedback_image_panel);
        viewGroup.addView(c3028a.m15487i(LayoutInflater.from(this), viewGroup));
        return c3028a;
    }

    /* renamed from: H */
    public void m15427H() {
        runOnUiThread(new Runnable() { // from class: p3.i
            @Override // java.lang.Runnable
            public final void run() {
                this.f20694b.m15417L();
            }
        });
    }

    /* renamed from: I */
    public String m15428I() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) getApplicationContext().getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        int i9 = displayMetrics.widthPixels;
        return displayMetrics.heightPixels + "x" + i9;
    }

    /* renamed from: J */
    public final void m15429J(String str, String str2) {
        String country = LocaleList.getDefault().get(0).getCountry();
        NetworkFeedback.C3012c c3012c = new NetworkFeedback.C3012c(this.f13391d);
        this.f13392e = c3012c;
        c3012c.f13359c = "for Android";
        c3012c.f13360d = TimeZone.getDefault().getID() + "/" + country;
        NetworkFeedback.C3012c c3012c2 = this.f13392e;
        c3012c2.f13361e = "Android";
        c3012c2.f13362f = Build.VERSION.RELEASE;
        c3012c2.f13364h = Locale.getDefault().getLanguage() + "_" + Locale.getDefault().getCountry();
        NetworkFeedback.C3012c c3012c3 = this.f13392e;
        c3012c3.f13365i = Build.MODEL;
        c3012c3.f13366j = Build.BRAND + "(" + Build.MANUFACTURER + ")";
        this.f13392e.f13367k = m15428I();
        NetworkFeedback.C3012c c3012c4 = this.f13392e;
        c3012c4.f13371o = str2;
        c3012c4.f13372p = str;
        if (c3012c4.f13374r == null) {
            c3012c4.f13374r = new ArrayList<>();
        }
        String str3 = this.f13391d.umaid;
    }

    /* renamed from: Q */
    public void m15430Q(final int i9, final Float f9, final DialogInterface.OnClickListener onClickListener, final Runnable runnable) {
        runOnUiThread(new Runnable() { // from class: p3.f
            @Override // java.lang.Runnable
            public final void run() {
                this.f20686b.m15420P(f9, i9, onClickListener, runnable);
            }
        });
    }

    @Override // android.app.Activity
    public void onActivityResult(int i9, int i10, Intent intent) {
        super.onActivityResult(i9, i10, intent);
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        ArrayList<Uri> arrayList;
        super.onCreate(bundle);
        setContentView(R.layout.bc_activity_preview_feedback);
        Intent intent = getIntent();
        this.f13391d = (NetworkFeedback.FeedbackConfig) intent.getSerializableExtra("FeedbackConfig");
        String stringExtra = intent.getStringExtra("FeedbackDesc");
        String stringExtra2 = intent.getStringExtra("FeedbackEmail");
        this.f13393f = Model.parseFromJSONArray(Uri.class, intent.getStringExtra("FeedbackImage"));
        this.f13402o = intent.getStringExtra("FeedbackProjectFile");
        m15396l(R.string.bc_feedback_preview_title);
        m15394j().m15462n(-469762048, R.drawable.icon_back, R.drawable.bc_image_selector_top_bar_btn_send);
        TextView textView = (TextView) findViewById(R.id.bc_feedback_description);
        this.f13395h = textView;
        if (textView != null) {
            textView.setText(stringExtra);
        }
        TextView textView2 = (TextView) findViewById(R.id.bc_feedback_email);
        this.f13396i = textView2;
        if (textView2 != null) {
            textView2.setText(stringExtra2);
        }
        TextView textView3 = (TextView) findViewById(R.id.bc_feedback_appver);
        this.f13397j = textView3;
        if (textView3 != null) {
            String strM7391n1 = Globals.m7391n1();
            NetworkFeedback.FeedbackConfig feedbackConfig = this.f13391d;
            if (feedbackConfig != null) {
                strM7391n1 = feedbackConfig.appversion;
            }
            this.f13397j.setText(strM7391n1);
        }
        TextView textView4 = (TextView) findViewById(R.id.bc_feedback_devicemodel);
        this.f13398k = textView4;
        if (textView4 != null) {
            textView4.setText(Build.MODEL);
        }
        TextView textView5 = (TextView) findViewById(R.id.bc_feedback_osver);
        this.f13399l = textView5;
        if (textView5 != null) {
            textView5.setText(Build.VERSION.RELEASE);
        }
        TextView textView6 = (TextView) findViewById(R.id.bc_feedback_time);
        this.f13400m = textView6;
        if (textView6 != null) {
            this.f13400m.setText(new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss").format(new Date()));
        }
        if (((ViewGroup) findViewById(R.id.bc_feedback_image_panel)) != null && (arrayList = this.f13393f) != null) {
            Iterator<Uri> it = arrayList.iterator();
            while (it.hasNext()) {
                Uri next = it.next();
                if (next != null) {
                    m15426E().m15488j(next, true);
                }
            }
        }
        m15429J(stringExtra, stringExtra2);
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        C5152i0.m20065b(this.f13404q);
    }

    @Override // com.cyberlink.you.feedback.FeedbackBaseActivity, com.cyberlink.you.feedback.TopBarFragment.InterfaceC3023a
    public void onRightBtnClick(View view) {
        NetworkFeedback.FeedbackConfig feedbackConfig = this.f13391d;
        String str = feedbackConfig != null ? feedbackConfig.apiUri : null;
        m15430Q(R.string.setting_feedback, null, null, null);
        new C3018a(str).m15440f(null);
    }
}
