package p080g8;

import java.util.Collections;
import java.util.List;
import org.jsoup.nodes.AbstractC5690g;
import org.jsoup.nodes.C5685b;
import p060e8.C4772d;

/* renamed from: g8.c */
/* loaded from: classes.dex */
public abstract class AbstractC4974c extends AbstractC5690g {

    /* renamed from: e */
    public static final List<AbstractC5690g> f17074e = Collections.emptyList();

    /* renamed from: d */
    public Object f17075d;

    /* renamed from: R */
    public String m19252R() {
        return mo19255c(mo22827v());
    }

    /* renamed from: S */
    public final void m19253S() {
        if (mo19263r()) {
            return;
        }
        Object obj = this.f17075d;
        C5685b c5685b = new C5685b();
        this.f17075d = c5685b;
        if (obj != null) {
            c5685b.m22925t(mo22827v(), (String) obj);
        }
    }

    @Override // org.jsoup.nodes.AbstractC5690g
    /* renamed from: a */
    public String mo19254a(String str) {
        m19253S();
        return super.mo19254a(str);
    }

    @Override // org.jsoup.nodes.AbstractC5690g
    /* renamed from: c */
    public String mo19255c(String str) {
        C4772d.m19004j(str);
        return !mo19263r() ? str.equals(mo22827v()) ? (String) this.f17075d : "" : super.mo19255c(str);
    }

    @Override // org.jsoup.nodes.AbstractC5690g
    /* renamed from: d */
    public AbstractC5690g mo19256d(String str, String str2) {
        if (mo19263r() || !str.equals(mo22827v())) {
            m19253S();
            super.mo19256d(str, str2);
        } else {
            this.f17075d = str2;
        }
        return this;
    }

    @Override // org.jsoup.nodes.AbstractC5690g
    /* renamed from: e */
    public final C5685b mo19257e() {
        m19253S();
        return (C5685b) this.f17075d;
    }

    @Override // org.jsoup.nodes.AbstractC5690g
    /* renamed from: g */
    public String mo19258g() {
        return m22954s() ? m22935C().mo19258g() : "";
    }

    @Override // org.jsoup.nodes.AbstractC5690g
    /* renamed from: j */
    public int mo19259j() {
        return 0;
    }

    @Override // org.jsoup.nodes.AbstractC5690g
    /* renamed from: n */
    public void mo19260n(String str) {
    }

    @Override // org.jsoup.nodes.AbstractC5690g
    /* renamed from: o */
    public List<AbstractC5690g> mo19261o() {
        return f17074e;
    }

    @Override // org.jsoup.nodes.AbstractC5690g
    /* renamed from: q */
    public boolean mo19262q(String str) {
        m19253S();
        return super.mo19262q(str);
    }

    @Override // org.jsoup.nodes.AbstractC5690g
    /* renamed from: r */
    public final boolean mo19263r() {
        return this.f17075d instanceof C5685b;
    }
}
