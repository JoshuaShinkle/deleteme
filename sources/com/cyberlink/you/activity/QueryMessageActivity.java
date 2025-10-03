package com.cyberlink.you.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import com.cyberlink.p030U.R;
import com.cyberlink.you.BaseActivity;
import com.cyberlink.you.Globals;
import com.cyberlink.you.activity.PasswordActivity;
import com.cyberlink.you.activity.chatdialog.ChatDialogActivity;
import com.cyberlink.you.chat.AsyncTaskC2897h0;
import com.cyberlink.you.friends.Group;
import com.cyberlink.you.utility.ULogUtility;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/* loaded from: classes.dex */
public class QueryMessageActivity extends BaseActivity {

    /* renamed from: c */
    public Group f8597c;

    /* renamed from: d */
    public Handler f8598d = new Handler();

    /* renamed from: e */
    public final Executor f8599e = Executors.newSingleThreadExecutor();

    /* renamed from: f */
    public AsyncTaskC2897h0.a f8600f = new C1644a();

    /* renamed from: g */
    public Runnable f8601g = new RunnableC1645b();

    /* renamed from: com.cyberlink.you.activity.QueryMessageActivity$a */
    public class C1644a implements AsyncTaskC2897h0.a {
        public C1644a() {
        }

        @Override // com.cyberlink.you.chat.AsyncTaskC2897h0.a
        public void onComplete() {
            QueryMessageActivity.this.m9500l(false);
            QueryMessageActivity.this.f8598d.removeCallbacks(QueryMessageActivity.this.f8601g);
        }
    }

    /* renamed from: com.cyberlink.you.activity.QueryMessageActivity$b */
    public class RunnableC1645b implements Runnable {
        public RunnableC1645b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            QueryMessageActivity.this.m9500l(true);
        }
    }

    /* renamed from: l */
    public final void m9500l(boolean z8) {
        Intent intent = new Intent(this, (Class<?>) ChatDialogActivity.class);
        intent.setFlags(268468224);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            extras.putBoolean("isQueryMsgTimeout", z8);
            intent.putExtras(extras);
        }
        startActivity(intent);
        if (Globals.m7388i0().m7437I()) {
            Intent intent2 = new Intent(this, (Class<?>) PasswordActivity.class);
            intent2.putExtra("type", PasswordActivity.PasswordMode.FIX_LOCK.toString());
            startActivity(intent2);
        }
        finish();
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ULogUtility.m16676l("QueryMessageActivity", "onCreate()");
        setContentView(R.layout.activity_query_message);
        this.f8597c = (Group) getIntent().getParcelableExtra("Group");
        LockScreenActivity.m8713k();
        this.f8598d.postDelayed(this.f8601g, DefaultRenderersFactory.DEFAULT_ALLOWED_VIDEO_JOINING_TIME_MS);
        AsyncTaskC2897h0 asyncTaskC2897h0 = new AsyncTaskC2897h0();
        Globals.m7388i0().m7486S3(asyncTaskC2897h0);
        asyncTaskC2897h0.m14365a(this.f8600f);
        asyncTaskC2897h0.executeOnExecutor(this.f8599e, this.f8597c);
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        Globals.m7388i0().m7488T0().m14370f(this.f8600f);
    }
}
