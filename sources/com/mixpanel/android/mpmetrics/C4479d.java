package com.mixpanel.android.mpmetrics;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import java.lang.ref.WeakReference;
import org.json.JSONException;
import org.json.JSONObject;
import p256z4.C6826b;

@TargetApi(14)
/* renamed from: com.mixpanel.android.mpmetrics.d */
/* loaded from: classes2.dex */
public class C4479d implements Application.ActivityLifecycleCallbacks {

    /* renamed from: i */
    public static Double f15783i;

    /* renamed from: c */
    public Runnable f15785c;

    /* renamed from: f */
    public final C4478c f15788f;

    /* renamed from: g */
    public final C6826b f15789g;

    /* renamed from: h */
    public WeakReference<Activity> f15790h;

    /* renamed from: b */
    public final Handler f15784b = new Handler(Looper.getMainLooper());

    /* renamed from: d */
    public boolean f15786d = false;

    /* renamed from: e */
    public boolean f15787e = true;

    /* renamed from: com.mixpanel.android.mpmetrics.d$a */
    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() throws JSONException {
            if (C4479d.this.f15786d && C4479d.this.f15787e) {
                C4479d.this.f15786d = false;
                try {
                    double dCurrentTimeMillis = System.currentTimeMillis() - C4479d.f15783i.doubleValue();
                    if (dCurrentTimeMillis >= C4479d.this.f15789g.m25481n() && dCurrentTimeMillis < C4479d.this.f15789g.m25486s() && C4479d.this.f15788f.m17958q().booleanValue()) {
                        double dRound = Math.round((dCurrentTimeMillis / 1000.0d) * 10.0d) / 10.0d;
                        JSONObject jSONObject = new JSONObject();
                        jSONObject.put("$ae_session_length", dRound);
                        C4479d.this.f15788f.m17955n().mo17970c("$ae_total_app_sessions", 1.0d);
                        C4479d.this.f15788f.m17955n().mo17970c("$ae_total_app_session_length", dRound);
                        C4479d.this.f15788f.m17949E("$ae_session", jSONObject, true);
                    }
                } catch (JSONException e9) {
                    e9.printStackTrace();
                }
                C4479d.this.f15788f.m17961t();
            }
        }
    }

    public C4479d(C4478c c4478c, C6826b c6826b) {
        this.f15788f = c4478c;
        this.f15789g = c6826b;
        if (f15783i == null) {
            f15783i = Double.valueOf(System.currentTimeMillis());
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityCreated(Activity activity, Bundle bundle) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityDestroyed(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPaused(Activity activity) {
        this.f15787e = true;
        Runnable runnable = this.f15785c;
        if (runnable != null) {
            this.f15784b.removeCallbacks(runnable);
        }
        this.f15790h = null;
        Handler handler = this.f15784b;
        a aVar = new a();
        this.f15785c = aVar;
        handler.postDelayed(aVar, 500L);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityResumed(Activity activity) {
        this.f15790h = new WeakReference<>(activity);
        this.f15787e = false;
        boolean z8 = !this.f15786d;
        this.f15786d = true;
        Runnable runnable = this.f15785c;
        if (runnable != null) {
            this.f15784b.removeCallbacks(runnable);
        }
        if (z8) {
            f15783i = Double.valueOf(System.currentTimeMillis());
            this.f15788f.m17962u();
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStarted(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStopped(Activity activity) {
    }
}
