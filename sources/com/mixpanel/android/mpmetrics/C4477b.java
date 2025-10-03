package com.mixpanel.android.mpmetrics;

import android.os.Process;
import com.mixpanel.android.mpmetrics.C4478c;
import java.lang.Thread;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.mixpanel.android.mpmetrics.b */
/* loaded from: classes2.dex */
public class C4477b implements Thread.UncaughtExceptionHandler {

    /* renamed from: b */
    public static C4477b f15761b;

    /* renamed from: a */
    public final Thread.UncaughtExceptionHandler f15762a = Thread.getDefaultUncaughtExceptionHandler();

    /* renamed from: com.mixpanel.android.mpmetrics.b$a */
    public class a implements C4478c.c {

        /* renamed from: a */
        public final /* synthetic */ Throwable f15763a;

        public a(Throwable th) {
            this.f15763a = th;
        }

        @Override // com.mixpanel.android.mpmetrics.C4478c.c
        /* renamed from: a */
        public void mo17934a(C4478c c4478c) throws JSONException {
            if (c4478c.m17958q().booleanValue()) {
                try {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("$ae_crashed_reason", this.f15763a.toString());
                    c4478c.m17949E("$ae_crashed", jSONObject, true);
                } catch (JSONException unused) {
                }
            }
        }
    }

    public C4477b() {
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    /* renamed from: a */
    public static void m17932a() {
        if (f15761b == null) {
            synchronized (C4477b.class) {
                if (f15761b == null) {
                    f15761b = new C4477b();
                }
            }
        }
    }

    /* renamed from: b */
    public final void m17933b() throws InterruptedException {
        try {
            Thread.sleep(400L);
        } catch (InterruptedException e9) {
            e9.printStackTrace();
        }
        Process.killProcess(Process.myPid());
        System.exit(10);
    }

    @Override // java.lang.Thread.UncaughtExceptionHandler
    public void uncaughtException(Thread thread, Throwable th) throws InterruptedException {
        C4478c.m17940f(new a(th));
        Thread.UncaughtExceptionHandler uncaughtExceptionHandler = this.f15762a;
        if (uncaughtExceptionHandler != null) {
            uncaughtExceptionHandler.uncaughtException(thread, th);
        } else {
            m17933b();
        }
    }
}
