package p150o;

import java.util.HashSet;
import java.util.Iterator;

/* renamed from: o.d */
/* loaded from: classes.dex */
public class C5387d {

    /* renamed from: a */
    public HashSet<C5387d> f18286a = new HashSet<>(2);

    /* renamed from: b */
    public int f18287b = 0;

    /* renamed from: a */
    public void m21091a(C5387d c5387d) {
        this.f18286a.add(c5387d);
    }

    /* renamed from: b */
    public void m21092b() {
        this.f18287b = 1;
        Iterator<C5387d> it = this.f18286a.iterator();
        while (it.hasNext()) {
            it.next().mo1373f();
        }
    }

    /* renamed from: c */
    public void m21093c() {
        this.f18287b = 0;
        Iterator<C5387d> it = this.f18286a.iterator();
        while (it.hasNext()) {
            it.next().m21093c();
        }
    }

    /* renamed from: d */
    public boolean m21094d() {
        return this.f18287b == 1;
    }

    /* renamed from: e */
    public void mo1372e() {
        this.f18287b = 0;
        this.f18286a.clear();
    }

    /* renamed from: f */
    public void mo1373f() {
    }
}
