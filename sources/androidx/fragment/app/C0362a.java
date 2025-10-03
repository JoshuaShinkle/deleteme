package androidx.fragment.app;

import android.util.Log;
import androidx.fragment.app.AbstractC0372k;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.LayoutInflaterFactory2C0369h;
import androidx.lifecycle.Lifecycle;
import java.io.PrintWriter;
import java.util.ArrayList;
import org.apache.commons.lang3.StringUtils;
import p021c0.C0696b;

/* renamed from: androidx.fragment.app.a */
/* loaded from: classes.dex */
public final class C0362a extends AbstractC0372k implements LayoutInflaterFactory2C0369h.k {

    /* renamed from: s */
    public final LayoutInflaterFactory2C0369h f2003s;

    /* renamed from: t */
    public boolean f2004t;

    /* renamed from: u */
    public int f2005u = -1;

    public C0362a(LayoutInflaterFactory2C0369h layoutInflaterFactory2C0369h) {
        this.f2003s = layoutInflaterFactory2C0369h;
    }

    /* renamed from: C */
    public static boolean m1785C(AbstractC0372k.a aVar) {
        Fragment fragment = aVar.f2107b;
        return (fragment == null || !fragment.mAdded || fragment.mView == null || fragment.mDetached || fragment.mHidden || !fragment.isPostponed()) ? false : true;
    }

    /* renamed from: A */
    public boolean m1786A(int i9) {
        int size = this.f2088a.size();
        for (int i10 = 0; i10 < size; i10++) {
            Fragment fragment = this.f2088a.get(i10).f2107b;
            int i11 = fragment != null ? fragment.mContainerId : 0;
            if (i11 != 0 && i11 == i9) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: B */
    public boolean m1787B(ArrayList<C0362a> arrayList, int i9, int i10) {
        if (i10 == i9) {
            return false;
        }
        int size = this.f2088a.size();
        int i11 = -1;
        for (int i12 = 0; i12 < size; i12++) {
            Fragment fragment = this.f2088a.get(i12).f2107b;
            int i13 = fragment != null ? fragment.mContainerId : 0;
            if (i13 != 0 && i13 != i11) {
                for (int i14 = i9; i14 < i10; i14++) {
                    C0362a c0362a = arrayList.get(i14);
                    int size2 = c0362a.f2088a.size();
                    for (int i15 = 0; i15 < size2; i15++) {
                        Fragment fragment2 = c0362a.f2088a.get(i15).f2107b;
                        if ((fragment2 != null ? fragment2.mContainerId : 0) == i13) {
                            return true;
                        }
                    }
                }
                i11 = i13;
            }
        }
        return false;
    }

    /* renamed from: D */
    public boolean m1788D() {
        for (int i9 = 0; i9 < this.f2088a.size(); i9++) {
            if (m1785C(this.f2088a.get(i9))) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: E */
    public void m1789E() {
        if (this.f2105r != null) {
            for (int i9 = 0; i9 < this.f2105r.size(); i9++) {
                this.f2105r.get(i9).run();
            }
            this.f2105r = null;
        }
    }

    /* renamed from: F */
    public void m1790F(Fragment.InterfaceC0357e interfaceC0357e) {
        for (int i9 = 0; i9 < this.f2088a.size(); i9++) {
            AbstractC0372k.a aVar = this.f2088a.get(i9);
            if (m1785C(aVar)) {
                aVar.f2107b.setOnStartEnterTransitionListener(interfaceC0357e);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0027  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x002d  */
    /* renamed from: G */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public Fragment m1791G(ArrayList<Fragment> arrayList, Fragment fragment) {
        for (int size = this.f2088a.size() - 1; size >= 0; size--) {
            AbstractC0372k.a aVar = this.f2088a.get(size);
            int i9 = aVar.f2106a;
            if (i9 == 1) {
                arrayList.remove(aVar.f2107b);
            } else if (i9 != 3) {
                switch (i9) {
                    case 6:
                        arrayList.add(aVar.f2107b);
                        break;
                    case 8:
                        fragment = null;
                        break;
                    case 9:
                        fragment = aVar.f2107b;
                        break;
                    case 10:
                        aVar.f2113h = aVar.f2112g;
                        break;
                }
            }
        }
        return fragment;
    }

    @Override // androidx.fragment.app.LayoutInflaterFactory2C0369h.k
    /* renamed from: a */
    public boolean mo1792a(ArrayList<C0362a> arrayList, ArrayList<Boolean> arrayList2) {
        if (LayoutInflaterFactory2C0369h.f2016I) {
            Log.v("FragmentManager", "Run: " + this);
        }
        arrayList.add(this);
        arrayList2.add(Boolean.FALSE);
        if (!this.f2095h) {
            return true;
        }
        this.f2003s.m1943p(this);
        return true;
    }

    @Override // androidx.fragment.app.AbstractC0372k
    /* renamed from: g */
    public int mo1793g() {
        return m1804t(false);
    }

    @Override // androidx.fragment.app.AbstractC0372k
    /* renamed from: h */
    public int mo1794h() {
        return m1804t(true);
    }

    @Override // androidx.fragment.app.AbstractC0372k
    /* renamed from: i */
    public void mo1795i() {
        m1985l();
        this.f2003s.m1949r0(this, false);
    }

    @Override // androidx.fragment.app.AbstractC0372k
    /* renamed from: j */
    public void mo1796j() {
        m1985l();
        this.f2003s.m1949r0(this, true);
    }

    @Override // androidx.fragment.app.AbstractC0372k
    /* renamed from: k */
    public AbstractC0372k mo1797k(Fragment fragment) {
        LayoutInflaterFactory2C0369h layoutInflaterFactory2C0369h = fragment.mFragmentManager;
        if (layoutInflaterFactory2C0369h == null || layoutInflaterFactory2C0369h == this.f2003s) {
            return super.mo1797k(fragment);
        }
        throw new IllegalStateException("Cannot detach Fragment attached to a different FragmentManager. Fragment " + fragment.toString() + " is already attached to a FragmentManager.");
    }

    @Override // androidx.fragment.app.AbstractC0372k
    /* renamed from: m */
    public void mo1798m(int i9, Fragment fragment, String str, int i10) {
        super.mo1798m(i9, fragment, str, i10);
        fragment.mFragmentManager = this.f2003s;
    }

    @Override // androidx.fragment.app.AbstractC0372k
    /* renamed from: n */
    public AbstractC0372k mo1799n(Fragment fragment) {
        LayoutInflaterFactory2C0369h layoutInflaterFactory2C0369h = fragment.mFragmentManager;
        if (layoutInflaterFactory2C0369h == null || layoutInflaterFactory2C0369h == this.f2003s) {
            return super.mo1799n(fragment);
        }
        throw new IllegalStateException("Cannot hide Fragment attached to a different FragmentManager. Fragment " + fragment.toString() + " is already attached to a FragmentManager.");
    }

    @Override // androidx.fragment.app.AbstractC0372k
    /* renamed from: o */
    public AbstractC0372k mo1800o(Fragment fragment) {
        LayoutInflaterFactory2C0369h layoutInflaterFactory2C0369h = fragment.mFragmentManager;
        if (layoutInflaterFactory2C0369h == null || layoutInflaterFactory2C0369h == this.f2003s) {
            return super.mo1800o(fragment);
        }
        throw new IllegalStateException("Cannot remove Fragment attached to a different FragmentManager. Fragment " + fragment.toString() + " is already attached to a FragmentManager.");
    }

    @Override // androidx.fragment.app.AbstractC0372k
    /* renamed from: q */
    public AbstractC0372k mo1801q(Fragment fragment, Lifecycle.State state) {
        if (fragment.mFragmentManager != this.f2003s) {
            throw new IllegalArgumentException("Cannot setMaxLifecycle for Fragment not attached to FragmentManager " + this.f2003s);
        }
        Lifecycle.State state2 = Lifecycle.State.CREATED;
        if (state.m2050a(state2)) {
            return super.mo1801q(fragment, state);
        }
        throw new IllegalArgumentException("Cannot set maximum Lifecycle below " + state2);
    }

    @Override // androidx.fragment.app.AbstractC0372k
    /* renamed from: r */
    public AbstractC0372k mo1802r(Fragment fragment) {
        LayoutInflaterFactory2C0369h layoutInflaterFactory2C0369h = fragment.mFragmentManager;
        if (layoutInflaterFactory2C0369h == null || layoutInflaterFactory2C0369h == this.f2003s) {
            return super.mo1802r(fragment);
        }
        throw new IllegalStateException("Cannot show Fragment attached to a different FragmentManager. Fragment " + fragment.toString() + " is already attached to a FragmentManager.");
    }

    /* renamed from: s */
    public void m1803s(int i9) {
        if (this.f2095h) {
            if (LayoutInflaterFactory2C0369h.f2016I) {
                Log.v("FragmentManager", "Bump nesting in " + this + " by " + i9);
            }
            int size = this.f2088a.size();
            for (int i10 = 0; i10 < size; i10++) {
                AbstractC0372k.a aVar = this.f2088a.get(i10);
                Fragment fragment = aVar.f2107b;
                if (fragment != null) {
                    fragment.mBackStackNesting += i9;
                    if (LayoutInflaterFactory2C0369h.f2016I) {
                        Log.v("FragmentManager", "Bump nesting of " + aVar.f2107b + " to " + aVar.f2107b.mBackStackNesting);
                    }
                }
            }
        }
    }

    /* renamed from: t */
    public int m1804t(boolean z8) {
        if (this.f2004t) {
            throw new IllegalStateException("commit already called");
        }
        if (LayoutInflaterFactory2C0369h.f2016I) {
            Log.v("FragmentManager", "Commit: " + this);
            PrintWriter printWriter = new PrintWriter(new C0696b("FragmentManager"));
            m1805u("  ", printWriter);
            printWriter.close();
        }
        this.f2004t = true;
        if (this.f2095h) {
            this.f2005u = this.f2003s.m1951s(this);
        } else {
            this.f2005u = -1;
        }
        this.f2003s.m1938n0(this, z8);
        return this.f2005u;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("BackStackEntry{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        if (this.f2005u >= 0) {
            sb.append(" #");
            sb.append(this.f2005u);
        }
        if (this.f2097j != null) {
            sb.append(StringUtils.SPACE);
            sb.append(this.f2097j);
        }
        sb.append("}");
        return sb.toString();
    }

    /* renamed from: u */
    public void m1805u(String str, PrintWriter printWriter) {
        m1806v(str, printWriter, true);
    }

    /* renamed from: v */
    public void m1806v(String str, PrintWriter printWriter, boolean z8) {
        String str2;
        if (z8) {
            printWriter.print(str);
            printWriter.print("mName=");
            printWriter.print(this.f2097j);
            printWriter.print(" mIndex=");
            printWriter.print(this.f2005u);
            printWriter.print(" mCommitted=");
            printWriter.println(this.f2004t);
            if (this.f2093f != 0) {
                printWriter.print(str);
                printWriter.print("mTransition=#");
                printWriter.print(Integer.toHexString(this.f2093f));
                printWriter.print(" mTransitionStyle=#");
                printWriter.println(Integer.toHexString(this.f2094g));
            }
            if (this.f2089b != 0 || this.f2090c != 0) {
                printWriter.print(str);
                printWriter.print("mEnterAnim=#");
                printWriter.print(Integer.toHexString(this.f2089b));
                printWriter.print(" mExitAnim=#");
                printWriter.println(Integer.toHexString(this.f2090c));
            }
            if (this.f2091d != 0 || this.f2092e != 0) {
                printWriter.print(str);
                printWriter.print("mPopEnterAnim=#");
                printWriter.print(Integer.toHexString(this.f2091d));
                printWriter.print(" mPopExitAnim=#");
                printWriter.println(Integer.toHexString(this.f2092e));
            }
            if (this.f2098k != 0 || this.f2099l != null) {
                printWriter.print(str);
                printWriter.print("mBreadCrumbTitleRes=#");
                printWriter.print(Integer.toHexString(this.f2098k));
                printWriter.print(" mBreadCrumbTitleText=");
                printWriter.println(this.f2099l);
            }
            if (this.f2100m != 0 || this.f2101n != null) {
                printWriter.print(str);
                printWriter.print("mBreadCrumbShortTitleRes=#");
                printWriter.print(Integer.toHexString(this.f2100m));
                printWriter.print(" mBreadCrumbShortTitleText=");
                printWriter.println(this.f2101n);
            }
        }
        if (this.f2088a.isEmpty()) {
            return;
        }
        printWriter.print(str);
        printWriter.println("Operations:");
        int size = this.f2088a.size();
        for (int i9 = 0; i9 < size; i9++) {
            AbstractC0372k.a aVar = this.f2088a.get(i9);
            switch (aVar.f2106a) {
                case 0:
                    str2 = "NULL";
                    break;
                case 1:
                    str2 = "ADD";
                    break;
                case 2:
                    str2 = "REPLACE";
                    break;
                case 3:
                    str2 = "REMOVE";
                    break;
                case 4:
                    str2 = "HIDE";
                    break;
                case 5:
                    str2 = "SHOW";
                    break;
                case 6:
                    str2 = "DETACH";
                    break;
                case 7:
                    str2 = "ATTACH";
                    break;
                case 8:
                    str2 = "SET_PRIMARY_NAV";
                    break;
                case 9:
                    str2 = "UNSET_PRIMARY_NAV";
                    break;
                case 10:
                    str2 = "OP_SET_MAX_LIFECYCLE";
                    break;
                default:
                    str2 = "cmd=" + aVar.f2106a;
                    break;
            }
            printWriter.print(str);
            printWriter.print("  Op #");
            printWriter.print(i9);
            printWriter.print(": ");
            printWriter.print(str2);
            printWriter.print(StringUtils.SPACE);
            printWriter.println(aVar.f2107b);
            if (z8) {
                if (aVar.f2108c != 0 || aVar.f2109d != 0) {
                    printWriter.print(str);
                    printWriter.print("enterAnim=#");
                    printWriter.print(Integer.toHexString(aVar.f2108c));
                    printWriter.print(" exitAnim=#");
                    printWriter.println(Integer.toHexString(aVar.f2109d));
                }
                if (aVar.f2110e != 0 || aVar.f2111f != 0) {
                    printWriter.print(str);
                    printWriter.print("popEnterAnim=#");
                    printWriter.print(Integer.toHexString(aVar.f2110e));
                    printWriter.print(" popExitAnim=#");
                    printWriter.println(Integer.toHexString(aVar.f2111f));
                }
            }
        }
    }

    /* renamed from: w */
    public void m1807w() {
        int size = this.f2088a.size();
        for (int i9 = 0; i9 < size; i9++) {
            AbstractC0372k.a aVar = this.f2088a.get(i9);
            Fragment fragment = aVar.f2107b;
            if (fragment != null) {
                fragment.setNextTransition(this.f2093f, this.f2094g);
            }
            switch (aVar.f2106a) {
                case 1:
                    fragment.setNextAnim(aVar.f2108c);
                    this.f2003s.m1946q(fragment, false);
                    break;
                case 2:
                default:
                    throw new IllegalArgumentException("Unknown cmd: " + aVar.f2106a);
                case 3:
                    fragment.setNextAnim(aVar.f2109d);
                    this.f2003s.m1914a1(fragment);
                    break;
                case 4:
                    fragment.setNextAnim(aVar.f2109d);
                    this.f2003s.m1874F0(fragment);
                    break;
                case 5:
                    fragment.setNextAnim(aVar.f2108c);
                    this.f2003s.m1939n1(fragment);
                    break;
                case 6:
                    fragment.setNextAnim(aVar.f2109d);
                    this.f2003s.m1867C(fragment);
                    break;
                case 7:
                    fragment.setNextAnim(aVar.f2108c);
                    this.f2003s.m1956v(fragment);
                    break;
                case 8:
                    this.f2003s.m1937m1(fragment);
                    break;
                case 9:
                    this.f2003s.m1937m1(null);
                    break;
                case 10:
                    this.f2003s.m1935l1(fragment, aVar.f2113h);
                    break;
            }
            if (!this.f2104q && aVar.f2106a != 1 && fragment != null) {
                this.f2003s.m1896R0(fragment);
            }
        }
        if (this.f2104q) {
            return;
        }
        LayoutInflaterFactory2C0369h layoutInflaterFactory2C0369h = this.f2003s;
        layoutInflaterFactory2C0369h.m1898S0(layoutInflaterFactory2C0369h.f2040q, true);
    }

    /* renamed from: x */
    public void m1808x(boolean z8) {
        for (int size = this.f2088a.size() - 1; size >= 0; size--) {
            AbstractC0372k.a aVar = this.f2088a.get(size);
            Fragment fragment = aVar.f2107b;
            if (fragment != null) {
                fragment.setNextTransition(LayoutInflaterFactory2C0369h.m1860f1(this.f2093f), this.f2094g);
            }
            switch (aVar.f2106a) {
                case 1:
                    fragment.setNextAnim(aVar.f2111f);
                    this.f2003s.m1914a1(fragment);
                    break;
                case 2:
                default:
                    throw new IllegalArgumentException("Unknown cmd: " + aVar.f2106a);
                case 3:
                    fragment.setNextAnim(aVar.f2110e);
                    this.f2003s.m1946q(fragment, false);
                    break;
                case 4:
                    fragment.setNextAnim(aVar.f2110e);
                    this.f2003s.m1939n1(fragment);
                    break;
                case 5:
                    fragment.setNextAnim(aVar.f2111f);
                    this.f2003s.m1874F0(fragment);
                    break;
                case 6:
                    fragment.setNextAnim(aVar.f2110e);
                    this.f2003s.m1956v(fragment);
                    break;
                case 7:
                    fragment.setNextAnim(aVar.f2111f);
                    this.f2003s.m1867C(fragment);
                    break;
                case 8:
                    this.f2003s.m1937m1(null);
                    break;
                case 9:
                    this.f2003s.m1937m1(fragment);
                    break;
                case 10:
                    this.f2003s.m1935l1(fragment, aVar.f2112g);
                    break;
            }
            if (!this.f2104q && aVar.f2106a != 3 && fragment != null) {
                this.f2003s.m1896R0(fragment);
            }
        }
        if (this.f2104q || !z8) {
            return;
        }
        LayoutInflaterFactory2C0369h layoutInflaterFactory2C0369h = this.f2003s;
        layoutInflaterFactory2C0369h.m1898S0(layoutInflaterFactory2C0369h.f2040q, true);
    }

    /* JADX WARN: Removed duplicated region for block: B:34:0x00b2  */
    /* renamed from: y */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public Fragment m1809y(ArrayList<Fragment> arrayList, Fragment fragment) {
        Fragment fragment2 = fragment;
        int i9 = 0;
        while (i9 < this.f2088a.size()) {
            AbstractC0372k.a aVar = this.f2088a.get(i9);
            int i10 = aVar.f2106a;
            if (i10 == 1) {
                arrayList.add(aVar.f2107b);
            } else if (i10 == 2) {
                Fragment fragment3 = aVar.f2107b;
                int i11 = fragment3.mContainerId;
                boolean z8 = false;
                for (int size = arrayList.size() - 1; size >= 0; size--) {
                    Fragment fragment4 = arrayList.get(size);
                    if (fragment4.mContainerId == i11) {
                        if (fragment4 == fragment3) {
                            z8 = true;
                        } else {
                            if (fragment4 == fragment2) {
                                this.f2088a.add(i9, new AbstractC0372k.a(9, fragment4));
                                i9++;
                                fragment2 = null;
                            }
                            AbstractC0372k.a aVar2 = new AbstractC0372k.a(3, fragment4);
                            aVar2.f2108c = aVar.f2108c;
                            aVar2.f2110e = aVar.f2110e;
                            aVar2.f2109d = aVar.f2109d;
                            aVar2.f2111f = aVar.f2111f;
                            this.f2088a.add(i9, aVar2);
                            arrayList.remove(fragment4);
                            i9++;
                        }
                    }
                }
                if (z8) {
                    this.f2088a.remove(i9);
                    i9--;
                } else {
                    aVar.f2106a = 1;
                    arrayList.add(fragment3);
                }
            } else if (i10 == 3 || i10 == 6) {
                arrayList.remove(aVar.f2107b);
                Fragment fragment5 = aVar.f2107b;
                if (fragment5 == fragment2) {
                    this.f2088a.add(i9, new AbstractC0372k.a(9, fragment5));
                    i9++;
                    fragment2 = null;
                }
            } else if (i10 != 7) {
                if (i10 == 8) {
                    this.f2088a.add(i9, new AbstractC0372k.a(9, fragment2));
                    i9++;
                    fragment2 = aVar.f2107b;
                }
            }
            i9++;
        }
        return fragment2;
    }

    /* renamed from: z */
    public String m1810z() {
        return this.f2097j;
    }
}
