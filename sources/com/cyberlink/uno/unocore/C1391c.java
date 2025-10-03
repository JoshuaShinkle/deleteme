package com.cyberlink.uno.unocore;

import android.content.Context;
import android.util.Log;
import com.cyberlink.uno.log.UNOFileLog;
import com.cyberlink.uno.unocore.C1398j;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.cyberlink.uno.unocore.c */
/* loaded from: classes.dex */
public class C1391c {

    /* renamed from: m */
    public static String f7184m;

    /* renamed from: a */
    public C1397i f7185a;

    /* renamed from: b */
    public final ExecutorService f7186b = Executors.newSingleThreadExecutor(new a());

    /* renamed from: c */
    public Context f7187c;

    /* renamed from: d */
    public String f7188d;

    /* renamed from: e */
    public Future<?> f7189e;

    /* renamed from: f */
    public Map<String, Object> f7190f;

    /* renamed from: g */
    public Map<String, Object> f7191g;

    /* renamed from: h */
    public Map<String, Object> f7192h;

    /* renamed from: i */
    public Map<String, Object> f7193i;

    /* renamed from: j */
    public Map<String, Object> f7194j;

    /* renamed from: k */
    public int f7195k;

    /* renamed from: l */
    public C1398j f7196l;

    /* renamed from: com.cyberlink.uno.unocore.c$a */
    public class a implements ThreadFactory {
        public a() {
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            Thread thread = new Thread(runnable, "UNOCoreConnectionQueue");
            try {
                thread.setPriority(4);
            } catch (Throwable unused) {
            }
            return thread;
        }
    }

    /* renamed from: m */
    public static void m7233m(String str) {
        f7184m = str;
    }

    /* renamed from: a */
    public void m7234a() {
        m7235b();
        m7249q();
    }

    /* renamed from: b */
    public void m7235b() {
        String str;
        if (this.f7187c == null) {
            throw new IllegalStateException("context has not been set");
        }
        if (this.f7185a == null) {
            throw new IllegalStateException("unocore store has not been set");
        }
        if (this.f7196l == null || (str = this.f7188d) == null || !C1394f.m7259e(str)) {
            throw new IllegalStateException("server URL is not valid");
        }
    }

    /* renamed from: c */
    public boolean m7236c() {
        m7235b();
        HashMap map = this.f7193i != null ? new HashMap(this.f7193i) : new HashMap();
        for (Map.Entry<String, Object> entry : this.f7191g.entrySet()) {
            if (!(entry.getValue() instanceof String) && !(entry.getValue() instanceof Number)) {
                Log.d("ConnectionQueue", "The input dictionary of information should be string or number!!");
                return false;
            }
        }
        map.putAll(this.f7192h);
        map.putAll(this.f7190f);
        map.put("uuid", f7184m);
        for (Map.Entry<String, Object> entry2 : this.f7191g.entrySet()) {
            if (!map.containsKey(entry2.getKey())) {
                map.put(entry2.getKey(), entry2.getValue());
            }
        }
        this.f7194j = map;
        this.f7195k = new JSONObject(map).toString().length();
        return true;
    }

    /* renamed from: d */
    public void m7237d(int i9) {
        m7235b();
        m7249q();
    }

    /* renamed from: e */
    public int m7238e(int i9) {
        return (((((this.f7195k + 5) + 2) + 2) + 2) - 1) + (i9 * Event.m7218b());
    }

    /* renamed from: f */
    public void m7239f(C1398j.b bVar) {
        m7235b();
        this.f7196l.m7303a(bVar);
    }

    /* renamed from: g */
    public Context m7240g() {
        return this.f7187c;
    }

    /* renamed from: h */
    public int m7241h() {
        return this.f7195k;
    }

    /* renamed from: i */
    public Map<String, Object> m7242i() {
        return this.f7194j;
    }

    /* renamed from: j */
    public String m7243j() {
        return this.f7188d;
    }

    /* renamed from: k */
    public int m7244k(List<Event> list, int i9) throws JSONException {
        m7235b();
        HashMap map = this.f7194j != null ? new HashMap(this.f7194j) : new HashMap();
        int iM7220a = ((((this.f7195k + 5) + 2) + 2) + 2) - 1;
        int i10 = 0;
        for (int i11 = 0; i11 < list.size() && ((iM7220a = iM7220a + list.get(i11).m7220a()) <= i9 || i11 <= 0); i11++) {
            i10++;
        }
        try {
            JSONObject jSONObject = new JSONObject(map);
            JSONArray jSONArray = new JSONArray();
            for (int i12 = 0; i12 < i10; i12++) {
                JSONObject jSONObjectM7221c = list.get(i12).m7221c();
                if (jSONObjectM7221c.length() > 0) {
                    jSONArray.put(jSONObjectM7221c);
                }
            }
            jSONObject.put("event", jSONArray);
            this.f7185a.m7288a(jSONObject.toString());
            m7249q();
            return i10;
        } catch (JSONException e9) {
            UNOFileLog.m7215l(UNOFileLog.LogType.EXCEPTIONEVENT, "Get exception when convert Event to JSON", e9);
            Log.e("ConnectionQueue", "Got exception converting Events to JSON", e9);
            return 0;
        }
    }

    /* renamed from: l */
    public void m7245l(Context context) {
        this.f7187c = context;
        this.f7190f = C1392d.m7253c(context);
        this.f7191g = C1389a.m7230e(this.f7187c);
        this.f7192h = C1396h.m7285b(this.f7187c);
    }

    /* renamed from: n */
    public void m7246n(Map<String, Object> map) {
        this.f7193i = map;
    }

    /* renamed from: o */
    public void m7247o(String str) {
        this.f7188d = str;
        this.f7196l = new C1398j(str);
    }

    /* renamed from: p */
    public void m7248p(C1397i c1397i) {
        this.f7185a = c1397i;
    }

    /* renamed from: q */
    public void m7249q() {
        if (this.f7185a.m7297j()) {
            return;
        }
        Future<?> future = this.f7189e;
        if (future == null || future.isDone()) {
            this.f7189e = this.f7186b.submit(new RunnableC1390b(this.f7196l, this.f7185a));
        }
    }

    /* renamed from: r */
    public void m7250r(int i9) {
        m7235b();
        if (i9 > 0) {
            m7249q();
        }
    }
}
