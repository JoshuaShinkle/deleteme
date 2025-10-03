package p218v2;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import com.cyberlink.you.Globals;
import com.cyberlink.you.activity.GDPRActivity;
import com.cyberlink.you.activity.LockScreenActivity;
import com.cyberlink.you.activity.PasswordActivity;
import com.cyberlink.you.activity.QueryMessageActivity;
import com.cyberlink.you.activity.splash.SplashActivity;
import com.cyberlink.you.activity.ulauncher.ULauncherActivity;
import com.cyberlink.you.chat.NotificationHelper;
import com.cyberlink.you.utility.ULogUtility;
import com.google.android.exoplayer2.source.smoothstreaming.SsMediaSource;
import java.util.Arrays;
import java.util.HashSet;
import java.util.concurrent.atomic.AtomicInteger;
import p236x2.C6566a;

/* renamed from: v2.a */
/* loaded from: classes.dex */
public final class C6452a implements Application.ActivityLifecycleCallbacks {

    /* renamed from: d */
    public static long f21719d = -1;

    /* renamed from: e */
    public static final HashSet<Class> f21720e = new HashSet<>(Arrays.asList(LockScreenActivity.class, QueryMessageActivity.class));

    /* renamed from: b */
    public final AtomicInteger f21721b = new AtomicInteger(0);

    /* renamed from: c */
    public CountDownTimer f21722c = null;

    /* renamed from: v2.a$a */
    public class a extends CountDownTimer {
        public a(long j9, long j10) {
            super(j9, j10);
        }

        @Override // android.os.CountDownTimer
        public void onFinish() {
            ULogUtility.m16680p("ActivityLife", "Disconnect XMPP right now");
            C6456d.m24714D().m24751K("ActivityLife");
        }

        @Override // android.os.CountDownTimer
        public void onTick(long j9) {
            ULogUtility.m16683s("ActivityLife", "Disconnect XMPP in " + Math.round(j9 / 1000.0f) + " seconds");
        }
    }

    /* renamed from: d */
    public static void m24698d(Activity activity, String str) {
    }

    /* renamed from: a */
    public final void m24699a() {
        CountDownTimer countDownTimer = this.f21722c;
        if (countDownTimer != null) {
            countDownTimer.cancel();
            this.f21722c = null;
        }
    }

    /* renamed from: b */
    public boolean m24700b() {
        return this.f21721b.get() == 0;
    }

    /* renamed from: c */
    public boolean m24701c() {
        return this.f21721b.get() > 0;
    }

    /* renamed from: e */
    public final void m24702e() {
        m24699a();
        this.f21722c = new a(180000L, SsMediaSource.DEFAULT_LIVE_PRESENTATION_DELAY_MS).start();
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityCreated(Activity activity, Bundle bundle) {
        m24698d(activity, "[onCreate]");
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityDestroyed(Activity activity) {
        m24698d(activity, "[onDestroy]");
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPaused(Activity activity) {
        m24698d(activity, "[onPause]");
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityResumed(Activity activity) {
        m24698d(activity, "[onResume]");
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        m24698d(activity, "[onSaveInstanceState]");
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStarted(Activity activity) {
        Intent intent;
        m24698d(activity, "[onStart]");
        if (f21720e.contains(activity.getClass())) {
            return;
        }
        m24699a();
        if (this.f21721b.incrementAndGet() != 1) {
            PasswordActivity.m8860y(activity);
            return;
        }
        long jCurrentTimeMillis = System.currentTimeMillis();
        long j9 = f21719d;
        if (j9 != -1 && jCurrentTimeMillis - j9 > SsMediaSource.DEFAULT_LIVE_PRESENTATION_DELAY_MS) {
            C6566a.m25162u("Launch_App");
        }
        ULogUtility.m16670f("ActivityLife", "App bring to foreground: " + activity.getClass().getSimpleName());
        C6456d.m24714D().m24743A("ActivityLife");
        if (activity instanceof SplashActivity) {
            return;
        }
        if (!(activity instanceof ULauncherActivity) || (intent = activity.getIntent()) == null || intent.getAction() == null || !("android.intent.action.SEND".equals(intent.getAction()) || "android.intent.action.SEND_MULTIPLE".equals(intent.getAction()))) {
            GDPRActivity.m8215r(activity);
            PasswordActivity.m8860y(activity);
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStopped(Activity activity) {
        m24698d(activity, "[onStop]");
        if (f21720e.contains(activity.getClass())) {
            return;
        }
        if (this.f21721b.decrementAndGet() != 0) {
            m24698d(activity, this.f21721b.get() + " activities remaining");
            return;
        }
        f21719d = System.currentTimeMillis();
        ULogUtility.m16670f("ActivityLife", "App enter background: " + activity.getClass().getSimpleName());
        NotificationHelper.m14097m();
        m24702e();
        if (Globals.m7388i0().m7567k0()) {
            Globals.m7388i0().m7650z2(false);
        } else if (Globals.m7388i0().m7468P0() != 0) {
            Globals.m7388i0().m7650z2(true);
        } else {
            Globals.m7388i0().m7650z2(false);
        }
    }
}
