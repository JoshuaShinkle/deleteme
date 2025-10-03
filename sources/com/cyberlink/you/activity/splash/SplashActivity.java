package com.cyberlink.you.activity.splash;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import com.cyberlink.meeting.clrtc.MeetingManager;
import com.cyberlink.p030U.R;
import com.cyberlink.you.BaseActivity;
import com.cyberlink.you.Globals;
import com.cyberlink.you.activity.GDPRActivity;
import com.cyberlink.you.activity.PasswordActivity;
import com.cyberlink.you.activity.RegisterActivity;
import com.cyberlink.you.activity.chatdialog.ChatDialogActivity;
import com.cyberlink.you.activity.ulauncher.ULauncherActivity;
import com.cyberlink.you.chat.XMPPManager;
import com.cyberlink.you.database.C2950b0;
import com.cyberlink.you.database.C2956d0;
import com.cyberlink.you.database.StickerPackObj;
import com.cyberlink.you.friends.FriendsClient;
import com.cyberlink.you.friends.Group;
import com.cyberlink.you.module.appinvite.DeepLinkReceiver;
import com.cyberlink.you.p036ui.C3123g;
import com.cyberlink.you.utility.CLUtility;
import com.cyberlink.you.utility.ULogUtility;
import com.google.android.exoplayer2.C3322C;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import org.apache.commons.lang3.time.StopWatch;
import org.json.JSONException;
import p005a4.C0032a;
import p055e3.C4714a;
import p065f3.AsyncTaskC4780b;
import p065f3.RunnableC4779a;
import p065f3.RunnableC4786h;
import p116k4.C5164m0;
import p116k4.C5187v0;
import p193s4.C6263a;
import p201t3.C6301o;
import p209u2.AbstractC6381r;
import p209u2.C6369f;
import p209u2.C6385v;

/* loaded from: classes.dex */
public class SplashActivity extends BaseActivity {

    /* renamed from: n */
    public static final String f11360n = "SplashActivity";

    /* renamed from: o */
    public static String f11361o = "NeedClearAppData";

    /* renamed from: p */
    public static String f11362p = "DO_NOT_MOVE_BUILD_IN_STICKERS";

    /* renamed from: d */
    public C2493f f11364d;

    /* renamed from: e */
    public long f11365e;

    /* renamed from: j */
    public View f11370j;

    /* renamed from: k */
    public AlertDialog f11371k;

    /* renamed from: m */
    public BroadcastReceiver f11373m;

    /* renamed from: c */
    public Boolean f11363c = Boolean.FALSE;

    /* renamed from: f */
    public boolean f11366f = false;

    /* renamed from: g */
    public boolean f11367g = false;

    /* renamed from: h */
    public boolean f11368h = false;

    /* renamed from: i */
    public boolean f11369i = false;

    /* renamed from: l */
    public C2956d0.b f11372l = new C2488a();

    /* renamed from: com.cyberlink.you.activity.splash.SplashActivity$a */
    public class C2488a implements C2956d0.b {
        public C2488a() {
        }

        @Override // com.cyberlink.you.database.C2956d0.b
        public void onComplete() {
            SplashActivity.this.m12849X(false);
        }

        @Override // com.cyberlink.you.database.C2956d0.b
        public void onStart() {
            SplashActivity.this.m12849X(true);
        }
    }

    /* renamed from: com.cyberlink.you.activity.splash.SplashActivity$b */
    public class AsyncTaskC2489b extends AsyncTask<Void, Void, Integer> {

        /* renamed from: com.cyberlink.you.activity.splash.SplashActivity$b$a */
        public class a extends Thread {

            /* renamed from: com.cyberlink.you.activity.splash.SplashActivity$b$a$a, reason: collision with other inner class name */
            public class C6850a implements FriendsClient.InterfaceC3052j {
                public C6850a() {
                }

                @Override // com.cyberlink.you.friends.FriendsClient.InterfaceC3052j
                /* renamed from: a */
                public void mo12854a() {
                    ULogUtility.m16670f(SplashActivity.f11360n, "CheckForceUpdate loadCommandUrl onFail");
                    if (!FriendsClient.m15682q0() || SplashActivity.this.f11368h) {
                        return;
                    }
                    SplashActivity.this.f11368h = true;
                    Globals.m7388i0().m7412D();
                    ULogUtility.m16670f(SplashActivity.f11360n, "[navigateTask][onFail] forceUpdateApp");
                }

                @Override // com.cyberlink.you.friends.FriendsClient.InterfaceC3052j
                public void onSuccess() {
                    ULogUtility.m16670f(SplashActivity.f11360n, "CheckForceUpdate loadCommandUrl onSuccess");
                    boolean unused = SplashActivity.this.f11369i;
                }
            }

            public a() {
            }

            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                Thread.currentThread().setName("CheckForceUpdate");
                ULogUtility.m16670f(SplashActivity.f11360n, "CheckForceUpdate start");
                FriendsClient.m15635C0(new C6850a(), true);
                if (Boolean.valueOf(FriendsClient.m15682q0()).booleanValue() && !SplashActivity.this.f11368h) {
                    SplashActivity.this.f11368h = true;
                    Globals.m7388i0().m7412D();
                    ULogUtility.m16670f(SplashActivity.f11360n, "[navigateTask][Resume] forceUpdateApp");
                }
                ULogUtility.m16670f(SplashActivity.f11360n, "CheckForceUpdate end");
            }
        }

        public AsyncTaskC2489b() {
        }

        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public Integer doInBackground(Void... voidArr) {
            Thread.currentThread().setName("Navigate");
            ULogUtility.m16670f(SplashActivity.f11360n, "navigateToNextStep start");
            String strM7449L = Globals.m7388i0().m7449L();
            if (strM7449L == null || strM7449L.equals("")) {
                SplashActivity.this.f11369i = false;
                Globals.m7388i0().m7595p4();
            } else {
                Log.d(SplashActivity.f11360n, "[Splash] get CL id success, CLtoken=" + strM7449L);
                SplashActivity.this.f11369i = true;
            }
            new a().start();
            if (Globals.m7388i0().m7404B1()) {
                ULogUtility.m16670f(SplashActivity.f11360n, "return due to DATABASE_CORRUPT");
                return 0;
            }
            SplashActivity.this.m12841J();
            Globals globalsM7388i0 = Globals.m7388i0();
            Globals.m7388i0();
            globalsM7388i0.m7441I3(Globals.m7368J());
            ULogUtility.m16670f(SplashActivity.f11360n, "navigateToNextStep end");
            return null;
        }

        @Override // android.os.AsyncTask
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(Integer num) {
            if (num == null || !num.equals(0)) {
                SplashActivity.this.finish();
            } else {
                ULogUtility.m16670f(SplashActivity.f11360n, "navigateToNextStep DATABASE_CORRUPT ");
                SplashActivity.this.m12843N();
            }
        }
    }

    /* renamed from: com.cyberlink.you.activity.splash.SplashActivity$c */
    public class C2490c extends AbstractC6381r<Intent, Void> {

        /* renamed from: c */
        public final /* synthetic */ AtomicReference f11378c;

        public C2490c(AtomicReference atomicReference) {
            this.f11378c = atomicReference;
        }

        @Override // p209u2.AbstractC6381r
        /* renamed from: k, reason: merged with bridge method [inline-methods] */
        public void m24503g(Intent intent) {
            if (intent == null) {
                m24504h(null);
            } else {
                ULogUtility.m16670f(SplashActivity.f11360n, "[navigateToNextActivity] get intent");
                this.f11378c.set(intent);
            }
        }

        @Override // p209u2.AbstractC6381r
        /* renamed from: l, reason: merged with bridge method [inline-methods] */
        public void m24504h(Void r32) {
            ULogUtility.m16670f(SplashActivity.f11360n, "[navigateToNextActivity] get intent error , lead to ULauncherActivity");
            Intent intent = new Intent(SplashActivity.this.getApplicationContext(), (Class<?>) ULauncherActivity.class);
            intent.setFlags(67108864);
            this.f11378c.set(intent);
        }
    }

    /* renamed from: com.cyberlink.you.activity.splash.SplashActivity$d */
    public class C2491d extends AbstractC6381r<Void, Void> {
        public C2491d(Handler handler) {
            super(handler);
        }

        @Override // p209u2.AbstractC6381r
        /* renamed from: k, reason: merged with bridge method [inline-methods] */
        public void m24503g(Void r22) {
            SplashActivity.this.f11364d.f11382a = true;
            SplashActivity.this.m12850Z();
        }
    }

    /* renamed from: com.cyberlink.you.activity.splash.SplashActivity$e */
    public class C2492e implements FriendsClient.InterfaceC3051i {

        /* renamed from: a */
        public final /* synthetic */ FriendsClient f11381a;

        public C2492e(FriendsClient friendsClient) {
            this.f11381a = friendsClient;
        }

        @Override // com.cyberlink.you.friends.FriendsClient.InterfaceC3051i
        /* renamed from: a */
        public void mo134a(String str, String str2, String str3, String str4) {
            this.f11381a.m15717U0();
            if (str3 == null || !str3.equals("200")) {
                return;
            }
            Log.d(SplashActivity.f11360n, "[updateSticker] user.pack.update success");
            Globals.m7388i0().m7547f4(true);
        }
    }

    /* renamed from: com.cyberlink.you.activity.splash.SplashActivity$f */
    public class C2493f {

        /* renamed from: a */
        public boolean f11382a;

        public C2493f() {
        }

        /* renamed from: a */
        public boolean m12858a() {
            return this.f11382a;
        }
    }

    /* renamed from: C */
    public static String m12820C() {
        return Globals.m7388i0().getApplicationContext().getSharedPreferences("U", 0).getString("stickerPacksOrder", "");
    }

    /* renamed from: D */
    public static String m12821D() {
        boolean z8;
        String strM12820C = m12820C();
        Log.d(f11360n, "[updateSticker] userOldStickerOrder = " + strM12820C);
        long jM20107l = C5164m0.m20107l();
        if (!strM12820C.equals("")) {
            String strSubstring = strM12820C.substring(1, strM12820C.length() - 1);
            String strValueOf = String.valueOf(jM20107l);
            if (strSubstring.contains(strValueOf)) {
                return strSubstring;
            }
            return strValueOf + "," + strSubstring;
        }
        List<StickerPackObj> listM15291i = C2950b0.m14925x().m15291i();
        StringBuilder sb = new StringBuilder();
        if (listM15291i != null) {
            z8 = false;
            for (int i9 = 0; i9 < listM15291i.size(); i9++) {
                long jM14803g = listM15291i.get(i9).m14803g();
                sb.append(jM14803g);
                if (i9 != listM15291i.size() - 1) {
                    sb.append(",");
                }
                if (jM14803g == jM20107l) {
                    z8 = true;
                }
            }
        } else {
            z8 = false;
        }
        if (!z8) {
            sb.insert(0, jM20107l + ",");
        }
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: H */
    public /* synthetic */ void m12822H(DialogInterface dialogInterface, int i9) {
        dialogInterface.dismiss();
        m12841J();
        finish();
    }

    /* renamed from: Y */
    public static void m12824Y() {
        int iM15295m = C2950b0.m14925x().m15295m();
        String str = f11360n;
        Log.d(str, "[updateSticker] DB sticker count = " + iM15295m);
        if (iM15295m <= 0) {
            Log.d(str, "[updateSticker] Don't update with server");
            Globals.m7388i0().m7547f4(true);
            return;
        }
        String strM12821D = m12821D();
        Log.d(str, "[updateSticker] userOrderStickerList = " + strM12821D);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new C6301o("token", Globals.m7388i0().m7449L()));
        arrayList.add(new C6301o("list", strM12821D));
        FriendsClient friendsClient = new FriendsClient();
        friendsClient.m15734m("sticker", "user.pack.update", arrayList, new C2492e(friendsClient));
    }

    /* renamed from: B */
    public final boolean m12839B(Uri uri) {
        if (uri != null && uri.getHost() != null && uri.getHost().equals("verifyEmail")) {
            Log.d(f11360n, "[checkVerifiedURL] url = " + uri);
            boolean booleanQueryParameter = uri.getBooleanQueryParameter("isVerified", false);
            if (booleanQueryParameter && !Globals.m7388i0().m7583n0()) {
                Globals.m7388i0().m7564j3(booleanQueryParameter);
                return true;
            }
        }
        return false;
    }

    /* renamed from: E */
    public final void m12840E() {
        Intent intent = getIntent();
        if (C6263a.m24007e(intent)) {
            C0032a c0032a = new C0032a(intent);
            if (c0032a.m128i()) {
                c0032a.m132m(this);
            } else {
                c0032a.m131l();
            }
        }
    }

    /* renamed from: J */
    public final void m12841J() {
        Intent intent;
        String str = f11360n;
        ULogUtility.m16670f(str, "navigateToNextActivity start");
        Intent intent2 = getIntent();
        Uri data = getIntent().getData();
        Group group = (Group) intent2.getParcelableExtra("Group");
        String action = intent2.getAction() != null ? intent2.getAction() : "";
        if (!this.f11369i) {
            ULogUtility.m16670f(str, "[navigateToNextActivity] lead to RegisterActivity");
            Intent intent3 = getIntent();
            String stringExtra = intent3.getStringExtra("meetingLtiEventId");
            String stringExtra2 = intent3.getStringExtra("meetingLtiToken");
            String stringExtra3 = intent3.getStringExtra("meetingLtiUserName");
            ULogUtility.m16683s("SplashActivity", "EventId: " + stringExtra + " / ltiToken: " + stringExtra2 + " / UserName: " + stringExtra3);
            intent = new Intent(getApplicationContext(), (Class<?>) RegisterActivity.class);
            intent.setFlags(67108864);
            Bundle bundle = new Bundle();
            bundle.putParcelable("Group", group);
            bundle.putString("meetingLtiEventId", stringExtra);
            bundle.putString("meetingLtiToken", stringExtra2);
            bundle.putString("meetingLtiUserName", stringExtra3);
            intent.putExtras(bundle);
        } else if (group != null) {
            ULogUtility.m16670f(str, "[navigateToNextActivity] lead to ChatDialogActivity");
            m12839B(data);
            intent = new Intent(getApplicationContext(), (Class<?>) ChatDialogActivity.class);
            intent.setFlags(67108864);
            Bundle bundle2 = new Bundle();
            bundle2.putParcelable("Group", group);
            intent.putExtras(bundle2);
        } else if (m12847T(intent2, action)) {
            ULogUtility.m16670f(str, "[navigateToNextActivity] shareMediaFromOtherApp");
            AtomicReference atomicReference = new AtomicReference();
            C4714a.m18867b(intent2, new C2490c(atomicReference));
            intent = (Intent) atomicReference.get();
        } else {
            ULogUtility.m16670f(str, "[navigateToNextActivity] lead to ULauncherActivity");
            boolean zM12839B = m12839B(data);
            intent = new Intent(getApplicationContext(), (Class<?>) ULauncherActivity.class);
            intent.setFlags(67108864);
            intent.putExtra("isVerified", zM12839B);
        }
        startActivity(intent);
        GDPRActivity.m8215r(this);
        if (Globals.m7388i0().m7468P0() != 0) {
            ULogUtility.m16670f(str, "[navigateToNextActivity] show PasswordActivity");
            Intent intent4 = new Intent(this, (Class<?>) PasswordActivity.class);
            intent4.putExtra("type", PasswordActivity.PasswordMode.FIX_LOCK.toString());
            startActivity(intent4);
        }
        ULogUtility.m16670f(str, "navigateToNextActivity end");
    }

    /* renamed from: L */
    public final void m12842L() {
        if (!this.f11366f && !this.f11367g) {
            this.f11367g = true;
            new AsyncTaskC2489b().executeOnExecutor(C6385v.f21554b, new Void[0]);
            return;
        }
        ULogUtility.m16670f(f11360n, "navigateToNextStep return , mIsPaused : " + this.f11366f + " mIsNavTaskDone : " + this.f11367g);
    }

    /* renamed from: N */
    public final void m12843N() {
        AlertDialog.Builder builderM16382a = C3123g.m16382a(this);
        builderM16382a.setCancelable(false);
        builderM16382a.setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() { // from class: f3.f
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i9) {
                this.f16631b.m12822H(dialogInterface, i9);
            }
        });
        builderM16382a.setMessage(getString(R.string.error_database_corrupt));
        this.f11371k = builderM16382a.show();
    }

    /* renamed from: O */
    public final void m12844O() throws JSONException, IOException {
        Log.d(f11360n, "[updateSticker] parseAndStoreBuiltInStickerInfo");
        if (!Globals.m7388i0().m7533d1()) {
            m12824Y();
        }
        CLUtility.m16498V1(this);
    }

    /* renamed from: P */
    public final void m12845P() {
        this.f11373m = new DeepLinkReceiver();
        IntentFilter intentFilter = new IntentFilter("com.cyberlink.you.appinvite.deeplink");
        if (Build.VERSION.SDK_INT >= 34) {
            registerReceiver(this.f11373m, intentFilter, 2);
        } else {
            registerReceiver(this.f11373m, intentFilter);
        }
    }

    /* renamed from: Q */
    public final void m12846Q() {
        File filesDir = getFilesDir();
        File cacheDir = getCacheDir();
        C6369f.m24464f(new File(filesDir, "AvatarCache"));
        C6369f.m24464f(new File(cacheDir, "PNGcache"));
        C6369f.m24464f(new File(cacheDir, "imagecache"));
    }

    /* renamed from: T */
    public final boolean m12847T(Intent intent, String str) {
        return ("android.intent.action.SEND".equals(str) || "android.intent.action.SEND_MULTIPLE".equals(str)) && intent.getExtras() != null;
    }

    /* renamed from: V, reason: merged with bridge method [inline-methods] */
    public final void m12823I(boolean z8) {
        this.f11370j.setVisibility(z8 ? 0 : 8);
    }

    /* renamed from: X */
    public final void m12849X(final boolean z8) {
        runOnUiThread(new Runnable() { // from class: f3.g
            @Override // java.lang.Runnable
            public final void run() {
                this.f16632b.m12823I(z8);
            }
        });
    }

    /* renamed from: Z */
    public final void m12850Z() {
        if (this.f11363c.booleanValue()) {
            return;
        }
        String str = f11360n;
        ULogUtility.m16670f(str, "[taskCompleteCallback] bAllComplete : " + this.f11364d.m12858a());
        if (this.f11364d.m12858a()) {
            this.f11363c = Boolean.TRUE;
            long jNanoTime = System.nanoTime();
            long j9 = (jNanoTime - this.f11365e) / C3322C.MICROS_PER_SECOND;
            ULogUtility.m16670f(str, "[taskCompleteCallback] mStartTime : " + this.f11365e);
            ULogUtility.m16670f(str, "[taskCompleteCallback] endTime : " + jNanoTime);
            ULogUtility.m16670f(str, "[taskCompleteCallback] usedTime : " + j9);
            m12842L();
        }
    }

    /* renamed from: b0 */
    public final void m12851b0() {
        BroadcastReceiver broadcastReceiver = this.f11373m;
        if (broadcastReceiver != null) {
            unregisterReceiver(broadcastReceiver);
        }
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        super.onCreate(bundle);
        if (m12847T(getIntent(), getIntent().getAction()) && MeetingManager.m5619l()) {
            Log.d(f11360n, "not start SplashActivity when in meeting.");
            C5187v0.m20267c(R.string.share_media_in_meeting);
            finish();
            return;
        }
        m12840E();
        setContentView(R.layout.activity_splash);
        this.f11370j = findViewById(R.id.dbUpgradeProgress);
        if (C2956d0.m14993d()) {
            m12823I(true);
        }
        C2956d0.m14996g(this.f11372l);
        if (getIntent().getBooleanExtra(f11361o, false)) {
            Globals.m7388i0().m7638x();
            XMPPManager.m14184g0().m14223U();
        }
        new AsyncTaskC4780b(getApplicationContext()).executeOnExecutor(C6385v.f21553a, new Void[0]);
        C6385v.m24526d(new Runnable() { // from class: f3.c
            @Override // java.lang.Runnable
            public final void run() throws JSONException, IOException {
                this.f16629b.m12844O();
            }
        });
        C6385v.m24525c(new Runnable() { // from class: f3.d
            @Override // java.lang.Runnable
            public final void run() {
                ULogUtility.m16667c();
            }
        });
        C6385v.m24525c(new Runnable() { // from class: f3.e
            @Override // java.lang.Runnable
            public final void run() {
                this.f16630b.m12846Q();
            }
        });
        this.f11364d = new C2493f();
        this.f11365e = System.nanoTime();
        this.f11363c = Boolean.FALSE;
        if ((getIntent().getFlags() & 4194304) == 0 && isTaskRoot()) {
            Globals.m7388i0().m7417E(this);
            C6385v.m24525c(new RunnableC4779a());
            C6385v.m24525c(new RunnableC4786h(new C2491d(m7363d())));
            stopWatch.stop();
            ULogUtility.m16683s(f11360n, "onCreate took " + stopWatch.getTime() + " ms");
            return;
        }
        Intent intent = getIntent();
        String action = intent.getAction() != null ? intent.getAction() : "";
        if (("android.intent.action.SEND".equals(action) || "android.intent.action.SEND_MULTIPLE".equals(action)) && intent.getExtras() != null) {
            String str = f11360n;
            ULogUtility.m16670f(str, "start ULauncherActivity due to share files from other app");
            C4714a.m18870e(intent);
            Intent intent2 = new Intent(getApplicationContext(), (Class<?>) ULauncherActivity.class);
            intent2.setFlags(67108864);
            startActivity(intent2);
            GDPRActivity.m8215r(this);
            if (Globals.m7388i0().m7468P0() != 0) {
                ULogUtility.m16670f(str, "start PasswordActivity");
                Intent intent3 = new Intent(this, (Class<?>) PasswordActivity.class);
                intent3.putExtra("type", PasswordActivity.PasswordMode.FIX_LOCK.toString());
                startActivity(intent3);
            }
        }
        ULogUtility.m16670f(f11360n, "Finish due to FLAG_ACTIVITY_BROUGHT_TO_FRONT or not task root");
        finish();
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onDestroy() {
        AlertDialog alertDialog = this.f11371k;
        if (alertDialog != null && alertDialog.isShowing()) {
            this.f11371k.dismiss();
        }
        super.onDestroy();
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onPause() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        this.f11366f = true;
        super.onPause();
        stopWatch.stop();
        Log.v(f11360n, "onPause took " + stopWatch.getTime() + " ms");
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onResume() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        super.onResume();
        if (this.f11366f) {
            this.f11366f = false;
            if (!this.f11367g) {
                m12842L();
            }
        }
        stopWatch.stop();
        Log.v(f11360n, "onResume took " + stopWatch.getTime() + " ms");
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onStart() {
        super.onStart();
        m12845P();
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onStop() {
        super.onStop();
        ULogUtility.m16683s(f11360n, "onStop");
        m12851b0();
    }
}
