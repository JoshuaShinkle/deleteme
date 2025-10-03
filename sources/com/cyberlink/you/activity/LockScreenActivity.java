package com.cyberlink.you.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.cyberlink.meeting.clrtc.MeetingManager;
import com.cyberlink.p030U.R;
import com.cyberlink.you.BaseActivity;
import com.cyberlink.you.Globals;
import com.cyberlink.you.activity.PasswordActivity;
import com.cyberlink.you.activity.chatdialog.ChatDialogActivity;
import com.cyberlink.you.activity.ulauncher.ULauncherActivity;
import com.cyberlink.you.chat.C2933z;
import com.cyberlink.you.chat.NotificationHelper;
import com.cyberlink.you.friends.Group;
import com.cyberlink.you.utility.CLUtility;
import com.cyberlink.you.utility.ULogUtility;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.messaging.Constants;
import java.util.ArrayList;
import p197t.C6273a;

/* loaded from: classes.dex */
public class LockScreenActivity extends BaseActivity {

    /* renamed from: g */
    public static boolean f8034g = false;

    /* renamed from: h */
    public static Activity f8035h;

    /* renamed from: c */
    public ListView f8036c;

    /* renamed from: d */
    public C2933z f8037d;

    /* renamed from: e */
    public AdapterView.OnItemClickListener f8038e = new C1547a();

    /* renamed from: f */
    public View.OnClickListener f8039f = new View.OnClickListener() { // from class: com.cyberlink.you.activity.g7
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f10685b.m8714n(view);
        }
    };

    /* renamed from: com.cyberlink.you.activity.LockScreenActivity$a */
    public class C1547a implements AdapterView.OnItemClickListener {
        public C1547a() {
        }

        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView<?> adapterView, View view, int i9, long j9) {
            Intent intent;
            Intent intent2 = null;
            if (!Globals.m7388i0().m7464O1()) {
                ULogUtility.m16676l("LockScreenActivity", "user is not login, navigate to splash activity for login.");
                Globals.m7388i0().m7617t2(null);
                LockScreenActivity.this.finish();
                return;
            }
            if (MeetingManager.m5619l()) {
                ULogUtility.m16680p("LockScreenActivity", "not start ChatDialogActivity when in meeting.");
                LockScreenActivity.this.finish();
                return;
            }
            Group group = (Group) adapterView.getItemAtPosition(i9);
            C2933z.b bVarM14667b = LockScreenActivity.this.f8037d.m14667b(i9);
            boolean z8 = bVarM14667b.f12879a;
            boolean z9 = bVarM14667b.f12880b;
            String str = bVarM14667b.f12881c;
            String str2 = bVarM14667b.f12882d;
            boolean zEquals = Globals.m7388i0().m7483S0().equals("v1");
            boolean z10 = bVarM14667b.f12888j;
            StringBuilder sb = new StringBuilder();
            sb.append("group is null:");
            sb.append(group == null);
            sb.append(" isGcm:");
            sb.append(z8);
            sb.append(" isHeartBeat:");
            sb.append(z9);
            sb.append(" isV1:");
            sb.append(zEquals);
            ULogUtility.m16676l("LockScreenActivity", sb.toString());
            if (group != null) {
                if (z8 && zEquals) {
                    intent = new Intent(Globals.m7388i0().getApplicationContext(), (Class<?>) QueryMessageActivity.class);
                } else if (z9 && zEquals) {
                    intent = new Intent(Globals.m7388i0().getApplicationContext(), (Class<?>) QueryMessageActivity.class);
                } else if (z10) {
                    intent = new Intent(Globals.m7388i0().getApplicationContext(), (Class<?>) ULauncherActivity.class);
                    intent.putExtra("Tab_Index", 3);
                } else {
                    intent = new Intent(Globals.m7388i0().getApplicationContext(), (Class<?>) ChatDialogActivity.class);
                    intent2 = new Intent(Globals.m7388i0().getApplicationContext(), (Class<?>) PasswordActivity.class);
                }
                Bundle bundle = new Bundle();
                bundle.putParcelable("Group", group);
                if (str != null) {
                    bundle.putString(Constants.FirelogAnalytics.PARAM_MESSAGE_ID, str);
                }
                if (str2 != null) {
                    bundle.putString(Constants.FirelogAnalytics.PARAM_TOPIC, str2);
                }
                intent.putExtras(bundle);
                intent.setFlags(268468224);
                LockScreenActivity.this.startActivity(intent);
                GDPRActivity.m8215r(LockScreenActivity.this);
                if (Globals.m7388i0().m7437I() && intent2 != null) {
                    intent2.putExtra("type", PasswordActivity.PasswordMode.FIX_LOCK.toString());
                    LockScreenActivity.this.startActivity(intent2);
                }
            }
            LockScreenActivity.this.finish();
        }
    }

    /* renamed from: k */
    public static void m8713k() {
        Activity activity;
        if (!f8034g || (activity = f8035h) == null) {
            return;
        }
        activity.finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: n */
    public /* synthetic */ void m8714n(View view) {
        finish();
    }

    /* renamed from: l */
    public final C2933z.b m8715l() {
        C2933z.b bVar = new C2933z.b();
        bVar.f12879a = getIntent().getBooleanExtra("isGcm", false);
        bVar.f12880b = getIntent().getBooleanExtra("isHeartBeat", false);
        bVar.f12881c = getIntent().getStringExtra(Constants.FirelogAnalytics.PARAM_MESSAGE_ID);
        bVar.f12882d = getIntent().getStringExtra("topicId");
        bVar.f12883e = getIntent().getStringExtra("callId");
        bVar.f12884f = getIntent().getStringExtra("callType");
        bVar.f12885g = getIntent().getStringExtra(FirebaseAnalytics.Param.CONTENT);
        bVar.f12887i = getIntent().getStringExtra("avatar");
        bVar.f12886h = getIntent().getLongExtra("time", System.currentTimeMillis());
        bVar.f12888j = getIntent().getBooleanExtra("PSTN", false);
        return bVar;
    }

    /* renamed from: o */
    public final void m8716o() {
        Group group;
        if (this.f8037d == null || (group = (Group) getIntent().getParcelableExtra("Group")) == null) {
            return;
        }
        C2933z.b bVarM8715l = m8715l();
        this.f8037d.m14671f(group, true, bVarM8715l);
        ULogUtility.m16680p("LockScreenActivity", "[updateAdapterView] message content:" + bVarM8715l.f12885g);
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (!NotificationHelper.m14058B()) {
            ULogUtility.m16676l("LockScreenActivity", "check isShowTextWhenScreenLock not pass, finish activity when onCreate.");
            finish();
            return;
        }
        Window window = getWindow();
        window.addFlags(2621440);
        window.clearFlags(67108864);
        window.addFlags(Integer.MIN_VALUE);
        window.setStatusBarColor(C6273a.m24024c(this, R.color.bg_lockScreen_activity));
        setContentView(R.layout.activity_lock_screen);
        findViewById(android.R.id.content).setOnClickListener(this.f8039f);
        findViewById(R.id.cleanButtom).setOnClickListener(this.f8039f);
        f8035h = this;
        f8034g = true;
        this.f8036c = (ListView) findViewById(R.id.ChatThreadListView);
        if (f8035h != null) {
            this.f8037d = new C2933z(f8035h, R.layout.view_item_lock_screen, new ArrayList());
        }
        this.f8036c.setAdapter((ListAdapter) this.f8037d);
        this.f8036c.setOnItemClickListener(this.f8038e);
        m8716o();
        ULogUtility.m16680p("LockScreenActivity", "onCreate()");
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        f8035h = null;
        f8034g = false;
        ListView listView = this.f8036c;
        if (listView != null) {
            listView.setEmptyView(null);
        }
        ULogUtility.m16680p("LockScreenActivity", "onDestroy()");
    }

    @Override // android.app.Activity
    public void onNewIntent(Intent intent) {
        CLUtility.m16486R2(Globals.m7388i0().getApplicationContext());
        super.onNewIntent(intent);
        setIntent(intent);
        m8716o();
        ULogUtility.m16680p("LockScreenActivity", "onNewIntent()");
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        ULogUtility.m16680p("LockScreenActivity", "onPause()");
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        ULogUtility.m16680p("LockScreenActivity", "onResume()");
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onStart() {
        super.onStart();
        ULogUtility.m16680p("LockScreenActivity", "onStart()");
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onStop() {
        super.onStop();
        ULogUtility.m16680p("LockScreenActivity", "onStop()");
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(boolean z8) {
        super.onWindowFocusChanged(z8);
        ULogUtility.m16683s("LockScreenActivity", "onWindowFocusChanged() | hasFocus = " + z8);
    }
}
