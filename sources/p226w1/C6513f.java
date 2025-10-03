package p226w1;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/* renamed from: w1.f */
/* loaded from: classes.dex */
public class C6513f<T, Y> {

    /* renamed from: a */
    public final Map<T, Y> f21918a = new LinkedHashMap(100, 0.75f, true);

    /* renamed from: b */
    public final long f21919b;

    /* renamed from: c */
    public long f21920c;

    /* renamed from: d */
    public long f21921d;

    public C6513f(long j9) {
        this.f21919b = j9;
        this.f21920c = j9;
    }

    /* renamed from: b */
    public void m24924b() {
        m24930m(0L);
    }

    /* renamed from: f */
    public final void m24925f() {
        m24930m(this.f21920c);
    }

    /* renamed from: g */
    public synchronized Y m24926g(T t8) {
        return this.f21918a.get(t8);
    }

    /* renamed from: h */
    public synchronized long m24927h() {
        return this.f21920c;
    }

    /* renamed from: i */
    public int mo18614i(Y y8) {
        return 1;
    }

    /* renamed from: j */
    public void mo18615j(T t8, Y y8) {
    }

    /* renamed from: k */
    public synchronized Y m24928k(T t8, Y y8) {
        long jMo18614i = mo18614i(y8);
        if (jMo18614i >= this.f21920c) {
            mo18615j(t8, y8);
            return null;
        }
        if (y8 != null) {
            this.f21921d += jMo18614i;
        }
        Y yPut = this.f21918a.put(t8, y8);
        if (yPut != null) {
            this.f21921d -= mo18614i(yPut);
            if (!yPut.equals(y8)) {
                mo18615j(t8, yPut);
            }
        }
        m24925f();
        return yPut;
    }

    /* renamed from: l */
    public synchronized Y m24929l(T t8) {
        Y yRemove;
        yRemove = this.f21918a.remove(t8);
        if (yRemove != null) {
            this.f21921d -= mo18614i(yRemove);
        }
        return yRemove;
    }

    /* renamed from: m */
    public synchronized void m24930m(long j9) {
        while (this.f21921d > j9) {
            Iterator<Map.Entry<T, Y>> it = this.f21918a.entrySet().iterator();
            Map.Entry<T, Y> next = it.next();
            Y value = next.getValue();
            this.f21921d -= mo18614i(value);
            T key = next.getKey();
            it.remove();
            mo18615j(key, value);
        }
    }
}
