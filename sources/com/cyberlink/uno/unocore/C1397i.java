package com.cyberlink.uno.unocore;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.cyberlink.uno.unocore.i */
/* loaded from: classes.dex */
public class C1397i {

    /* renamed from: a */
    public final SharedPreferences f7215a;

    /* renamed from: com.cyberlink.uno.unocore.i$a */
    public class a implements Comparator<Event> {
        public a() {
        }

        @Override // java.util.Comparator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(Event event, Event event2) {
            return Long.valueOf(event.f7176d).compareTo(Long.valueOf(event2.f7176d));
        }
    }

    public C1397i(Context context) {
        if (context == null) {
            throw new IllegalArgumentException("must provide valid context");
        }
        this.f7215a = context.getSharedPreferences("COUNTLY_STORE", 0);
    }

    /* renamed from: k */
    public static String m7286k(Collection<String> collection, String str) {
        StringBuilder sb = new StringBuilder();
        Iterator<String> it = collection.iterator();
        int i9 = 0;
        while (it.hasNext()) {
            sb.append(it.next());
            i9++;
            if (i9 < collection.size()) {
                sb.append(str);
            }
        }
        return sb.toString();
    }

    /* renamed from: l */
    public static String m7287l(Collection<Event> collection, String str) {
        ArrayList arrayList = new ArrayList();
        Iterator<Event> it = collection.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().m7223f().toString());
        }
        return m7286k(arrayList, str);
    }

    /* renamed from: a */
    public synchronized void m7288a(String str) {
        if (str != null) {
            if (str.length() > 0) {
                ArrayList arrayList = new ArrayList(Arrays.asList(m7292e()));
                arrayList.add(str);
                this.f7215a.edit().putString("CONNECTIONS", m7286k(arrayList, "===")).commit();
            }
        }
    }

    /* renamed from: b */
    public synchronized void m7289b(Event event) {
        List<Event> listM7295h = m7295h();
        listM7295h.add(event);
        this.f7215a.edit().putString("EVENTS", m7287l(listM7295h, "===")).commit();
    }

    /* renamed from: c */
    public void m7290c(String str, String str2, int i9, long j9) {
        if (str == null || str.length() == 0) {
            throw new IllegalArgumentException("valid key required");
        }
        m7289b(new Event(str, str2, i9, j9));
    }

    /* renamed from: d */
    public final synchronized void m7291d() {
        this.f7215a.edit().remove("EVENTS").commit();
    }

    /* renamed from: e */
    public String[] m7292e() {
        String string = this.f7215a.getString("CONNECTIONS", "");
        return string.length() == 0 ? new String[0] : string.split("===");
    }

    /* renamed from: f */
    public final String[] m7293f() {
        String string = this.f7215a.getString("EVENTS", "");
        return string.length() == 0 ? new String[0] : string.split("===");
    }

    /* renamed from: g */
    public int m7294g() {
        return m7293f().length;
    }

    /* renamed from: h */
    public final List<Event> m7295h() {
        String[] strArrM7293f = m7293f();
        ArrayList arrayList = new ArrayList(strArrM7293f.length);
        for (String str : strArrM7293f) {
            try {
                Event eventM7219d = Event.m7219d(new JSONObject(str));
                if (eventM7219d != null) {
                    arrayList.add(eventM7219d);
                }
            } catch (JSONException e9) {
                Log.e("UNOCoreStore", "Cannot parse Event json", e9);
            }
        }
        Collections.sort(arrayList, new a());
        return arrayList;
    }

    /* renamed from: i */
    public int m7296i() {
        int i9 = this.f7215a.getInt("EVENTID", 0);
        int i10 = (i9 != 1000000 ? i9 : 0) + 1;
        this.f7215a.edit().putInt("EVENTID", i10).commit();
        return i10;
    }

    /* renamed from: j */
    public boolean m7297j() {
        return this.f7215a.getString("CONNECTIONS", "").length() == 0;
    }

    /* renamed from: m */
    public synchronized void m7298m(String str) {
        if (str != null) {
            if (str.length() > 0) {
                ArrayList arrayList = new ArrayList(Arrays.asList(m7292e()));
                if (arrayList.remove(str)) {
                    this.f7215a.edit().putString("CONNECTIONS", m7286k(arrayList, "===")).commit();
                }
            }
        }
    }

    /* renamed from: n */
    public synchronized List<Event> m7299n() {
        List<Event> listM7295h;
        listM7295h = m7295h();
        if (!listM7295h.isEmpty()) {
            m7291d();
        }
        return listM7295h;
    }

    /* renamed from: o */
    public synchronized List<Event> m7300o() {
        return m7295h();
    }
}
