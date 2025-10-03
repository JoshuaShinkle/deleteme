package com.cyberlink.uno.unocore;

import android.content.Context;
import android.util.Log;
import com.cyberlink.uno.log.UNOFileLog;
import com.cyberlink.uno.unocore.C1398j;
import com.google.firebase.crashlytics.internal.metadata.UserMetadata;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import p191s2.AbstractC6256d;

/* renamed from: com.cyberlink.uno.unocore.f */
/* loaded from: classes.dex */
public final class C1394f {

    /* renamed from: i */
    public static String f7199i;

    /* renamed from: j */
    public static final AbstractC6256d f7200j = new c();

    /* renamed from: a */
    public C1391c f7201a;

    /* renamed from: b */
    public ScheduledExecutorService f7202b;

    /* renamed from: c */
    public C1397i f7203c;

    /* renamed from: d */
    public C1395g f7204d;

    /* renamed from: e */
    public C1393e f7205e;

    /* renamed from: f */
    public long f7206f;

    /* renamed from: g */
    public int f7207g;

    /* renamed from: h */
    public long f7208h;

    /* renamed from: com.cyberlink.uno.unocore.f$a */
    public class a implements C1398j.b {
        public a() {
        }

        @Override // com.cyberlink.uno.unocore.C1398j.b
        /* renamed from: a */
        public void mo7276a(C1395g c1395g) {
            C1394f.this.f7204d = c1395g;
            C1394f.this.m7274q();
        }

        @Override // com.cyberlink.uno.unocore.C1398j.b
        public void onFailure(Exception exc) {
            C1394f.this.m7274q();
        }
    }

    /* renamed from: com.cyberlink.uno.unocore.f$b */
    public class b implements Runnable {
        public b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            C1394f.this.m7267j();
        }
    }

    /* renamed from: com.cyberlink.uno.unocore.f$c */
    public static final class c extends AbstractC6256d {
        @Override // p191s2.AbstractC6256d
        /* renamed from: a */
        public C1394f mo7277a() {
            return d.f7211a;
        }
    }

    /* renamed from: com.cyberlink.uno.unocore.f$d */
    public static class d {

        /* renamed from: a */
        public static final C1394f f7211a = new C1394f(null);
    }

    public /* synthetic */ C1394f(a aVar) {
        this();
    }

    /* renamed from: e */
    public static boolean m7259e(String str) {
        if (str == null || str.length() <= 0) {
            return false;
        }
        try {
            new URL(str);
            return true;
        } catch (MalformedURLException unused) {
            return false;
        }
    }

    /* renamed from: b */
    public void m7260b() {
        List<Event> listM7299n = this.f7203c.m7299n();
        int size = listM7299n.size();
        int i9 = 0;
        while (i9 < size) {
            int iM7272o = m7272o(listM7299n.subList(i9, size - i9));
            if (iM7272o <= 0) {
                Log.e("UNOCore", "Drop events caused by json error!!");
                return;
            }
            i9 += iM7272o;
        }
    }

    /* renamed from: c */
    public int m7261c() {
        return this.f7203c.m7296i();
    }

    /* renamed from: d */
    public synchronized void m7262d(Context context, String str, String str2, Map<String, Object> map) {
        String str3;
        try {
            if (context == null) {
                throw new IllegalArgumentException("valid context is required");
            }
            if (!m7259e(str)) {
                throw new IllegalArgumentException("valid serverURL is required");
            }
            if (str2 == null || str2.length() == 0) {
                throw new IllegalArgumentException("valid deviceID is required");
            }
            if (this.f7205e != null && (this.f7201a.m7240g() != context || !this.f7201a.m7243j().equals(str) || ((str3 = f7199i) != null && !str3.equals(str2)))) {
                throw new IllegalStateException("UNOCore cannot be reinitialized with different values");
            }
            if (this.f7205e == null) {
                f7199i = str2;
                this.f7203c = new C1397i(context);
                this.f7204d = new C1395g();
                this.f7201a.m7245l(context);
                this.f7201a.m7247o(str);
                this.f7201a.m7248p(this.f7203c);
                C1391c.m7233m(str2);
                this.f7201a.m7246n(map);
                this.f7205e = new C1393e(this.f7203c);
                this.f7208h = System.nanoTime();
                this.f7201a.m7236c();
                this.f7201a.m7239f(new a());
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    /* renamed from: f */
    public synchronized void m7263f() {
        Log.v("UNOCore", "onStart activityCount:" + this.f7207g);
        if (this.f7205e == null) {
            throw new IllegalStateException("init must be called before onStart");
        }
        int i9 = this.f7207g + 1;
        this.f7207g = i9;
        if (i9 == 1) {
            m7264g();
        }
    }

    /* renamed from: g */
    public void m7264g() throws ExecutionException, JSONException, InterruptedException {
        this.f7206f = System.nanoTime();
        UNOFileLog.m7214k(UNOFileLog.LogType.MESSAGE, "onStartHelper enter");
        m7269l();
        this.f7201a.m7234a();
    }

    /* renamed from: h */
    public synchronized void m7265h() {
        if (this.f7205e == null) {
            throw new IllegalStateException("init must be called before onStop");
        }
        int i9 = this.f7207g;
        if (i9 == 0) {
            throw new IllegalStateException("must call onStart before onStop");
        }
        int i10 = i9 - 1;
        this.f7207g = i10;
        if (i10 == 0) {
            m7266i();
        }
    }

    /* renamed from: i */
    public void m7266i() throws ExecutionException, JSONException, InterruptedException {
        this.f7201a.m7237d(m7271n());
        this.f7206f = 0L;
        if (this.f7205e.m7257b() > 0) {
            m7260b();
        }
        UNOFileLog.m7214k(UNOFileLog.LogType.MESSAGE, "onStopHelper App");
        m7269l();
        UNOFileLog.m7206c();
    }

    /* renamed from: j */
    public synchronized void m7267j() {
        if (this.f7207g > 0) {
            this.f7201a.m7250r(m7271n());
            if (this.f7205e.m7257b() > 0) {
                m7260b();
            }
        }
    }

    /* renamed from: k */
    public void m7268k() throws ExecutionException, InterruptedException {
        if (UNOFileLog.m7208e()) {
            StringBuilder sb = new StringBuilder(this.f7201a.m7241h());
            sb.append("[url]");
            sb.append("  ");
            sb.append(this.f7201a.m7243j());
            sb.append(",");
            sb.append("[maxLogSize]");
            sb.append("  ");
            sb.append(this.f7204d.m7278a());
            sb.append(",");
            sb.append("[startupInterval]");
            sb.append("  ");
            sb.append(this.f7204d.m7279b());
            sb.append(",");
            sb.append("[updateInterval]");
            sb.append("  ");
            sb.append(this.f7204d.m7280c());
            sb.append(",");
            Map<String, Object> mapM7242i = this.f7201a.m7242i();
            if (mapM7242i != null) {
                for (Map.Entry<String, Object> entry : mapM7242i.entrySet()) {
                    if (entry.getValue() instanceof CharSequence) {
                        sb.append("[");
                        sb.append(entry.getKey());
                        sb.append("]");
                        sb.append("  ");
                        sb.append(entry.getValue());
                        sb.append(",");
                    }
                }
            }
            UNOFileLog.m7217n(UNOFileLog.LogType.INFORMATION, sb.toString().substring(0, sb.length() - 1), true);
        }
    }

    /* renamed from: l */
    public void m7269l() throws ExecutionException, JSONException, InterruptedException {
        if (UNOFileLog.m7208e()) {
            UNOFileLog.m7217n(UNOFileLog.LogType.MESSAGE, "print all events start", true);
            List<Event> listM7300o = this.f7203c.m7300o();
            JSONObject jSONObject = new JSONObject();
            JSONArray jSONArray = new JSONArray();
            for (int i9 = 0; i9 < listM7300o.size(); i9++) {
                JSONObject jSONObjectM7221c = listM7300o.get(i9).m7221c();
                if (jSONObjectM7221c.length() > 0) {
                    jSONArray.put(jSONObjectM7221c);
                }
            }
            try {
                jSONObject.put("event", jSONArray);
            } catch (JSONException e9) {
                UNOFileLog.m7216m(UNOFileLog.LogType.EXCEPTIONEVENT, "Convert events from event queue to json object error", e9, true);
            }
            UNOFileLog.m7217n(UNOFileLog.LogType.EVENTQUEUE, jSONObject.toString(), true);
            for (String str : this.f7203c.m7292e()) {
                UNOFileLog.m7217n(UNOFileLog.LogType.CONNECTIONQUEUE, str, true);
            }
            UNOFileLog.m7217n(UNOFileLog.LogType.MESSAGE, "print all events end", true);
        }
    }

    /* renamed from: m */
    public synchronized void m7270m(String str, int i9, String str2) {
        C1393e c1393e = this.f7205e;
        if (c1393e == null) {
            throw new IllegalStateException("init must be called before recordEvent");
        }
        c1393e.m7256a(str, str2, i9, System.currentTimeMillis());
        m7273p();
    }

    /* renamed from: n */
    public int m7271n() {
        long jNanoTime = System.nanoTime();
        long j9 = jNanoTime - this.f7206f;
        this.f7206f = jNanoTime;
        return (int) Math.round(j9 / 1.0E9d);
    }

    /* renamed from: o */
    public final int m7272o(List<Event> list) {
        return this.f7201a.m7244k(list, this.f7204d.m7278a() * UserMetadata.MAX_ATTRIBUTE_SIZE);
    }

    /* renamed from: p */
    public void m7273p() {
        if (this.f7201a.m7238e(this.f7205e.m7257b()) > this.f7204d.m7278a() * UserMetadata.MAX_ATTRIBUTE_SIZE) {
            m7260b();
        }
    }

    /* renamed from: q */
    public void m7274q() {
        this.f7202b.scheduleWithFixedDelay(new b(), Math.max(1L, this.f7204d.m7279b() - TimeUnit.NANOSECONDS.toSeconds(System.nanoTime() - this.f7208h)), this.f7204d.m7280c(), TimeUnit.SECONDS);
    }

    /* renamed from: r */
    public void m7275r(Map<String, Object> map) throws ExecutionException, InterruptedException {
        this.f7201a.m7246n(map);
        this.f7201a.m7236c();
        UNOFileLog.m7217n(UNOFileLog.LogType.MESSAGE, "uno connectionQueue config has been changed", true);
        m7268k();
    }

    public C1394f() {
        this.f7201a = new C1391c();
        this.f7202b = Executors.newSingleThreadScheduledExecutor();
    }
}
