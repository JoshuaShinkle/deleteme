package androidx.fragment.app;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import androidx.lifecycle.InterfaceC0397r;
import p021c0.C0702h;

/* renamed from: androidx.fragment.app.d */
/* loaded from: classes.dex */
public class C0365d {

    /* renamed from: a */
    public final AbstractC0367f<?> f2007a;

    public C0365d(AbstractC0367f<?> abstractC0367f) {
        this.f2007a = abstractC0367f;
    }

    /* renamed from: b */
    public static C0365d m1812b(AbstractC0367f<?> abstractC0367f) {
        return new C0365d((AbstractC0367f) C0702h.m3469c(abstractC0367f, "callbacks == null"));
    }

    /* renamed from: a */
    public void m1813a(Fragment fragment) {
        AbstractC0367f<?> abstractC0367f = this.f2007a;
        abstractC0367f.f2013f.m1954u(abstractC0367f, abstractC0367f, fragment);
    }

    /* renamed from: c */
    public void m1814c() {
        this.f2007a.f2013f.m1869D();
    }

    /* renamed from: d */
    public void m1815d(Configuration configuration) {
        this.f2007a.f2013f.m1871E(configuration);
    }

    /* renamed from: e */
    public boolean m1816e(MenuItem menuItem) {
        return this.f2007a.f2013f.m1873F(menuItem);
    }

    /* renamed from: f */
    public void m1817f() {
        this.f2007a.f2013f.m1875G();
    }

    /* renamed from: g */
    public boolean m1818g(Menu menu, MenuInflater menuInflater) {
        return this.f2007a.f2013f.m1877H(menu, menuInflater);
    }

    /* renamed from: h */
    public void m1819h() {
        this.f2007a.f2013f.m1879I();
    }

    /* renamed from: i */
    public void m1820i() {
        this.f2007a.f2013f.m1883K();
    }

    /* renamed from: j */
    public void m1821j(boolean z8) {
        this.f2007a.f2013f.m1885L(z8);
    }

    /* renamed from: k */
    public boolean m1822k(MenuItem menuItem) {
        return this.f2007a.f2013f.m1913a0(menuItem);
    }

    /* renamed from: l */
    public void m1823l(Menu menu) {
        this.f2007a.f2013f.m1915b0(menu);
    }

    /* renamed from: m */
    public void m1824m() {
        this.f2007a.f2013f.m1919d0();
    }

    /* renamed from: n */
    public void m1825n(boolean z8) {
        this.f2007a.f2013f.m1921e0(z8);
    }

    /* renamed from: o */
    public boolean m1826o(Menu menu) {
        return this.f2007a.f2013f.m1923f0(menu);
    }

    /* renamed from: p */
    public void m1827p() {
        this.f2007a.f2013f.m1926h0();
    }

    /* renamed from: q */
    public void m1828q() {
        this.f2007a.f2013f.m1928i0();
    }

    /* renamed from: r */
    public void m1829r() {
        this.f2007a.f2013f.m1932k0();
    }

    /* renamed from: s */
    public boolean m1830s() {
        return this.f2007a.f2013f.m1947q0();
    }

    /* renamed from: t */
    public Fragment m1831t(String str) {
        return this.f2007a.f2013f.m1957v0(str);
    }

    /* renamed from: u */
    public AbstractC0368g m1832u() {
        return this.f2007a.f2013f;
    }

    /* renamed from: v */
    public void m1833v() {
        this.f2007a.f2013f.m1904V0();
    }

    /* renamed from: w */
    public View m1834w(View view, String str, Context context, AttributeSet attributeSet) {
        return this.f2007a.f2013f.onCreateView(view, str, context, attributeSet);
    }

    /* renamed from: x */
    public void m1835x(Parcelable parcelable) {
        AbstractC0367f<?> abstractC0367f = this.f2007a;
        if (!(abstractC0367f instanceof InterfaceC0397r)) {
            throw new IllegalStateException("Your FragmentHostCallback must implement ViewModelStoreOwner to call restoreSaveState(). Call restoreAllState()  if you're still using retainNestedNonConfig().");
        }
        abstractC0367f.f2013f.m1922e1(parcelable);
    }

    /* renamed from: y */
    public Parcelable m1836y() {
        return this.f2007a.f2013f.m1925g1();
    }
}
