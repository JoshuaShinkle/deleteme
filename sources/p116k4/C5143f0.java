package p116k4;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/* renamed from: k4.f0 */
/* loaded from: classes.dex */
public class C5143f0 {

    /* renamed from: a */
    public SharedPreferences f17661a;

    public C5143f0(Context context, String str, int i9) {
        this.f17661a = context.getSharedPreferences(str, i9);
    }

    /* renamed from: a */
    public boolean m20028a() {
        return this.f17661a.edit().clear().commit();
    }

    /* renamed from: b */
    public boolean m20029b(List<String> list) {
        Map<String, ?> all = this.f17661a.getAll();
        boolean zM20028a = m20028a();
        for (String str : list) {
            Object obj = all.get(str);
            if (obj instanceof Integer) {
                m20037j(str, ((Integer) obj).intValue());
            } else if (obj instanceof Long) {
                m20039l(str, ((Integer) obj).intValue());
            } else if (obj instanceof Boolean) {
                m20036i(str, ((Boolean) obj).booleanValue());
            } else if (obj instanceof String) {
                m20040m(str, (String) obj);
            }
        }
        return zM20028a;
    }

    /* renamed from: c */
    public boolean m20030c(String str) {
        return this.f17661a.contains(str);
    }

    /* renamed from: d */
    public boolean m20031d(String str, boolean z8) {
        return this.f17661a.getBoolean(str, z8);
    }

    /* renamed from: e */
    public int m20032e(String str, int i9) {
        return this.f17661a.getInt(str, i9);
    }

    /* renamed from: f */
    public ArrayList<String> m20033f(String str) {
        return new ArrayList<>(Arrays.asList(TextUtils.split(this.f17661a.getString(str, ""), "‚‗‚")));
    }

    /* renamed from: g */
    public long m20034g(String str, long j9) {
        return this.f17661a.getLong(str, j9);
    }

    /* renamed from: h */
    public String m20035h(String str, String str2) {
        return this.f17661a.getString(str, str2);
    }

    /* renamed from: i */
    public void m20036i(String str, boolean z8) {
        this.f17661a.edit().putBoolean(str, z8).apply();
    }

    /* renamed from: j */
    public void m20037j(String str, int i9) {
        this.f17661a.edit().putInt(str, i9).apply();
    }

    /* renamed from: k */
    public void m20038k(String str, ArrayList<String> arrayList) {
        this.f17661a.edit().putString(str, TextUtils.join("‚‗‚", (String[]) arrayList.toArray(new String[arrayList.size()]))).apply();
    }

    /* renamed from: l */
    public void m20039l(String str, long j9) {
        this.f17661a.edit().putLong(str, j9).apply();
    }

    /* renamed from: m */
    public void m20040m(String str, String str2) {
        this.f17661a.edit().putString(str, str2).apply();
    }

    /* renamed from: n */
    public void m20041n(String str) {
        this.f17661a.edit().remove(str).apply();
    }
}
