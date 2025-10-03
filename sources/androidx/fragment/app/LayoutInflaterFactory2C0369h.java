package androidx.fragment.app;

import android.R;
import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.Looper;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.ScaleAnimation;
import android.view.animation.Transformation;
import androidx.activity.AbstractC0095b;
import androidx.activity.InterfaceC0096c;
import androidx.activity.OnBackPressedDispatcher;
import androidx.fragment.app.AbstractC0368g;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.C0396q;
import androidx.lifecycle.InterfaceC0385f;
import androidx.lifecycle.InterfaceC0397r;
import androidx.lifecycle.Lifecycle;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import p021c0.C0695a;
import p021c0.C0696b;
import p042d0.ViewTreeObserverOnPreDrawListenerC4644r;
import p132m.C5303b;

/* renamed from: androidx.fragment.app.h */
/* loaded from: classes.dex */
public final class LayoutInflaterFactory2C0369h extends AbstractC0368g implements LayoutInflater.Factory2 {

    /* renamed from: I */
    public static boolean f2016I = false;

    /* renamed from: J */
    public static final Interpolator f2017J = new DecelerateInterpolator(2.5f);

    /* renamed from: K */
    public static final Interpolator f2018K = new DecelerateInterpolator(1.5f);

    /* renamed from: A */
    public ArrayList<C0362a> f2019A;

    /* renamed from: B */
    public ArrayList<Boolean> f2020B;

    /* renamed from: C */
    public ArrayList<Fragment> f2021C;

    /* renamed from: F */
    public ArrayList<m> f2024F;

    /* renamed from: G */
    public C0370i f2025G;

    /* renamed from: d */
    public ArrayList<k> f2027d;

    /* renamed from: e */
    public boolean f2028e;

    /* renamed from: i */
    public ArrayList<C0362a> f2032i;

    /* renamed from: j */
    public ArrayList<Fragment> f2033j;

    /* renamed from: k */
    public OnBackPressedDispatcher f2034k;

    /* renamed from: m */
    public ArrayList<C0362a> f2036m;

    /* renamed from: n */
    public ArrayList<Integer> f2037n;

    /* renamed from: o */
    public ArrayList<AbstractC0368g.a> f2038o;

    /* renamed from: r */
    public AbstractC0367f f2041r;

    /* renamed from: s */
    public AbstractC0364c f2042s;

    /* renamed from: t */
    public Fragment f2043t;

    /* renamed from: u */
    public Fragment f2044u;

    /* renamed from: v */
    public boolean f2045v;

    /* renamed from: w */
    public boolean f2046w;

    /* renamed from: x */
    public boolean f2047x;

    /* renamed from: y */
    public boolean f2048y;

    /* renamed from: z */
    public boolean f2049z;

    /* renamed from: f */
    public int f2029f = 0;

    /* renamed from: g */
    public final ArrayList<Fragment> f2030g = new ArrayList<>();

    /* renamed from: h */
    public final HashMap<String, Fragment> f2031h = new HashMap<>();

    /* renamed from: l */
    public final AbstractC0095b f2035l = new a(false);

    /* renamed from: p */
    public final CopyOnWriteArrayList<i> f2039p = new CopyOnWriteArrayList<>();

    /* renamed from: q */
    public int f2040q = 0;

    /* renamed from: D */
    public Bundle f2022D = null;

    /* renamed from: E */
    public SparseArray<Parcelable> f2023E = null;

    /* renamed from: H */
    public Runnable f2026H = new b();

    /* renamed from: androidx.fragment.app.h$a */
    public class a extends AbstractC0095b {
        public a(boolean z8) {
            super(z8);
        }

        @Override // androidx.activity.AbstractC0095b
        /* renamed from: b */
        public void mo215b() {
            LayoutInflaterFactory2C0369h.this.m1872E0();
        }
    }

    /* renamed from: androidx.fragment.app.h$b */
    public class b implements Runnable {
        public b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            LayoutInflaterFactory2C0369h.this.m1947q0();
        }
    }

    /* renamed from: androidx.fragment.app.h$c */
    public class c implements Animation.AnimationListener {

        /* renamed from: a */
        public final /* synthetic */ ViewGroup f2052a;

        /* renamed from: b */
        public final /* synthetic */ Fragment f2053b;

        /* renamed from: androidx.fragment.app.h$c$a */
        public class a implements Runnable {
            public a() {
            }

            @Override // java.lang.Runnable
            public void run() throws Resources.NotFoundException {
                if (c.this.f2053b.getAnimatingAway() != null) {
                    c.this.f2053b.setAnimatingAway(null);
                    c cVar = c.this;
                    LayoutInflaterFactory2C0369h layoutInflaterFactory2C0369h = LayoutInflaterFactory2C0369h.this;
                    Fragment fragment = cVar.f2053b;
                    layoutInflaterFactory2C0369h.m1902U0(fragment, fragment.getStateAfterAnimating(), 0, 0, false);
                }
            }
        }

        public c(ViewGroup viewGroup, Fragment fragment) {
            this.f2052a = viewGroup;
            this.f2053b = fragment;
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationEnd(Animation animation) {
            this.f2052a.post(new a());
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationRepeat(Animation animation) {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationStart(Animation animation) {
        }
    }

    /* renamed from: androidx.fragment.app.h$d */
    public class d extends AnimatorListenerAdapter {

        /* renamed from: a */
        public final /* synthetic */ ViewGroup f2056a;

        /* renamed from: b */
        public final /* synthetic */ View f2057b;

        /* renamed from: c */
        public final /* synthetic */ Fragment f2058c;

        public d(ViewGroup viewGroup, View view, Fragment fragment) {
            this.f2056a = viewGroup;
            this.f2057b = view;
            this.f2058c = fragment;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) throws Resources.NotFoundException {
            this.f2056a.endViewTransition(this.f2057b);
            Animator animator2 = this.f2058c.getAnimator();
            this.f2058c.setAnimator(null);
            if (animator2 == null || this.f2056a.indexOfChild(this.f2057b) >= 0) {
                return;
            }
            LayoutInflaterFactory2C0369h layoutInflaterFactory2C0369h = LayoutInflaterFactory2C0369h.this;
            Fragment fragment = this.f2058c;
            layoutInflaterFactory2C0369h.m1902U0(fragment, fragment.getStateAfterAnimating(), 0, 0, false);
        }
    }

    /* renamed from: androidx.fragment.app.h$e */
    public class e extends AnimatorListenerAdapter {

        /* renamed from: a */
        public final /* synthetic */ ViewGroup f2060a;

        /* renamed from: b */
        public final /* synthetic */ View f2061b;

        /* renamed from: c */
        public final /* synthetic */ Fragment f2062c;

        public e(ViewGroup viewGroup, View view, Fragment fragment) {
            this.f2060a = viewGroup;
            this.f2061b = view;
            this.f2062c = fragment;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.f2060a.endViewTransition(this.f2061b);
            animator.removeListener(this);
            Fragment fragment = this.f2062c;
            View view = fragment.mView;
            if (view == null || !fragment.mHidden) {
                return;
            }
            view.setVisibility(8);
        }
    }

    /* renamed from: androidx.fragment.app.h$f */
    public class f extends C0366e {
        public f() {
        }

        @Override // androidx.fragment.app.C0366e
        /* renamed from: a */
        public Fragment mo1840a(ClassLoader classLoader, String str) {
            AbstractC0367f abstractC0367f = LayoutInflaterFactory2C0369h.this.f2041r;
            return abstractC0367f.m1811a(abstractC0367f.m1842e(), str, null);
        }
    }

    /* renamed from: androidx.fragment.app.h$i */
    public static final class i {

        /* renamed from: a */
        public final boolean f2072a;
    }

    /* renamed from: androidx.fragment.app.h$j */
    public static class j {

        /* renamed from: a */
        public static final int[] f2073a = {R.attr.name, R.attr.id, R.attr.tag};
    }

    /* renamed from: androidx.fragment.app.h$k */
    public interface k {
        /* renamed from: a */
        boolean mo1792a(ArrayList<C0362a> arrayList, ArrayList<Boolean> arrayList2);
    }

    /* renamed from: androidx.fragment.app.h$l */
    public class l implements k {

        /* renamed from: a */
        public final String f2074a;

        /* renamed from: b */
        public final int f2075b;

        /* renamed from: c */
        public final int f2076c;

        public l(String str, int i9, int i10) {
            this.f2074a = str;
            this.f2075b = i9;
            this.f2076c = i10;
        }

        @Override // androidx.fragment.app.LayoutInflaterFactory2C0369h.k
        /* renamed from: a */
        public boolean mo1792a(ArrayList<C0362a> arrayList, ArrayList<Boolean> arrayList2) {
            Fragment fragment = LayoutInflaterFactory2C0369h.this.f2044u;
            if (fragment == null || this.f2075b >= 0 || this.f2074a != null || !fragment.getChildFragmentManager().mo1854k()) {
                return LayoutInflaterFactory2C0369h.this.m1910Y0(arrayList, arrayList2, this.f2074a, this.f2075b, this.f2076c);
            }
            return false;
        }
    }

    /* renamed from: androidx.fragment.app.h$m */
    public static class m implements Fragment.InterfaceC0357e {

        /* renamed from: a */
        public final boolean f2078a;

        /* renamed from: b */
        public final C0362a f2079b;

        /* renamed from: c */
        public int f2080c;

        public m(C0362a c0362a, boolean z8) {
            this.f2078a = z8;
            this.f2079b = c0362a;
        }

        @Override // androidx.fragment.app.Fragment.InterfaceC0357e
        /* renamed from: a */
        public void mo1763a() {
            this.f2080c++;
        }

        @Override // androidx.fragment.app.Fragment.InterfaceC0357e
        /* renamed from: b */
        public void mo1764b() {
            int i9 = this.f2080c - 1;
            this.f2080c = i9;
            if (i9 != 0) {
                return;
            }
            this.f2079b.f2003s.m1931j1();
        }

        /* renamed from: c */
        public void m1966c() throws Resources.NotFoundException {
            C0362a c0362a = this.f2079b;
            c0362a.f2003s.m1863A(c0362a, this.f2078a, false, false);
        }

        /* renamed from: d */
        public void m1967d() throws Resources.NotFoundException {
            boolean z8 = this.f2080c > 0;
            LayoutInflaterFactory2C0369h layoutInflaterFactory2C0369h = this.f2079b.f2003s;
            int size = layoutInflaterFactory2C0369h.f2030g.size();
            for (int i9 = 0; i9 < size; i9++) {
                Fragment fragment = layoutInflaterFactory2C0369h.f2030g.get(i9);
                fragment.setOnStartEnterTransitionListener(null);
                if (z8 && fragment.isPostponed()) {
                    fragment.startPostponedEnterTransition();
                }
            }
            C0362a c0362a = this.f2079b;
            c0362a.f2003s.m1863A(c0362a, this.f2078a, !z8, true);
        }

        /* renamed from: e */
        public boolean m1968e() {
            return this.f2080c == 0;
        }
    }

    /* renamed from: N0 */
    public static g m1858N0(float f9, float f10) {
        AlphaAnimation alphaAnimation = new AlphaAnimation(f9, f10);
        alphaAnimation.setInterpolator(f2018K);
        alphaAnimation.setDuration(220L);
        return new g(alphaAnimation);
    }

    /* renamed from: P0 */
    public static g m1859P0(float f9, float f10, float f11, float f12) {
        AnimationSet animationSet = new AnimationSet(false);
        ScaleAnimation scaleAnimation = new ScaleAnimation(f9, f10, f9, f10, 1, 0.5f, 1, 0.5f);
        scaleAnimation.setInterpolator(f2017J);
        scaleAnimation.setDuration(220L);
        animationSet.addAnimation(scaleAnimation);
        AlphaAnimation alphaAnimation = new AlphaAnimation(f11, f12);
        alphaAnimation.setInterpolator(f2018K);
        alphaAnimation.setDuration(220L);
        animationSet.addAnimation(alphaAnimation);
        return new g(animationSet);
    }

    /* renamed from: f1 */
    public static int m1860f1(int i9) {
        if (i9 == 4097) {
            return 8194;
        }
        if (i9 != 4099) {
            return i9 != 8194 ? 0 : 4097;
        }
        return 4099;
    }

    /* renamed from: q1 */
    public static int m1861q1(int i9, boolean z8) {
        if (i9 == 4097) {
            return z8 ? 1 : 2;
        }
        if (i9 == 4099) {
            return z8 ? 5 : 6;
        }
        if (i9 != 8194) {
            return -1;
        }
        return z8 ? 3 : 4;
    }

    /* renamed from: s0 */
    public static void m1862s0(ArrayList<C0362a> arrayList, ArrayList<Boolean> arrayList2, int i9, int i10) {
        while (i9 < i10) {
            C0362a c0362a = arrayList.get(i9);
            if (arrayList2.get(i9).booleanValue()) {
                c0362a.m1803s(-1);
                c0362a.m1808x(i9 == i10 + (-1));
            } else {
                c0362a.m1803s(1);
                c0362a.m1807w();
            }
            i9++;
        }
    }

    /* renamed from: A */
    public void m1863A(C0362a c0362a, boolean z8, boolean z9, boolean z10) throws Resources.NotFoundException {
        if (z8) {
            c0362a.m1808x(z10);
        } else {
            c0362a.m1807w();
        }
        ArrayList arrayList = new ArrayList(1);
        ArrayList arrayList2 = new ArrayList(1);
        arrayList.add(c0362a);
        arrayList2.add(Boolean.valueOf(z8));
        if (z9) {
            C0373l.m1988B(this, arrayList, arrayList2, 0, 1, true);
        }
        if (z10) {
            m1898S0(this.f2040q, true);
        }
        for (Fragment fragment : this.f2031h.values()) {
            if (fragment != null && fragment.mView != null && fragment.mIsNewlyAdded && c0362a.m1786A(fragment.mContainerId)) {
                float f9 = fragment.mPostponedAlpha;
                if (f9 > BitmapDescriptorFactory.HUE_RED) {
                    fragment.mView.setAlpha(f9);
                }
                if (z10) {
                    fragment.mPostponedAlpha = BitmapDescriptorFactory.HUE_RED;
                } else {
                    fragment.mPostponedAlpha = -1.0f;
                    fragment.mIsNewlyAdded = false;
                }
            }
        }
    }

    /* renamed from: A0 */
    public C0370i m1864A0(Fragment fragment) {
        return this.f2025G.m1973f(fragment);
    }

    /* renamed from: B */
    public void m1865B(Fragment fragment) throws Resources.NotFoundException {
        Animator animator;
        if (fragment.mView != null) {
            g gVarM1886L0 = m1886L0(fragment, fragment.getNextTransition(), !fragment.mHidden, fragment.getNextTransitionStyle());
            if (gVarM1886L0 == null || (animator = gVarM1886L0.f2066b) == null) {
                if (gVarM1886L0 != null) {
                    fragment.mView.startAnimation(gVarM1886L0.f2065a);
                    gVarM1886L0.f2065a.start();
                }
                fragment.mView.setVisibility((!fragment.mHidden || fragment.isHideReplaced()) ? 0 : 8);
                if (fragment.isHideReplaced()) {
                    fragment.setHideReplaced(false);
                }
            } else {
                animator.setTarget(fragment.mView);
                if (!fragment.mHidden) {
                    fragment.mView.setVisibility(0);
                } else if (fragment.isHideReplaced()) {
                    fragment.setHideReplaced(false);
                } else {
                    ViewGroup viewGroup = fragment.mContainer;
                    View view = fragment.mView;
                    viewGroup.startViewTransition(view);
                    gVarM1886L0.f2066b.addListener(new e(viewGroup, view, fragment));
                }
                gVarM1886L0.f2066b.start();
            }
        }
        if (fragment.mAdded && m1878H0(fragment)) {
            this.f2045v = true;
        }
        fragment.mHiddenChanged = false;
        fragment.onHiddenChanged(fragment.mHidden);
    }

    /* renamed from: B0 */
    public LayoutInflater.Factory2 m1866B0() {
        return this;
    }

    /* renamed from: C */
    public void m1867C(Fragment fragment) {
        if (f2016I) {
            Log.v("FragmentManager", "detach: " + fragment);
        }
        if (fragment.mDetached) {
            return;
        }
        fragment.mDetached = true;
        if (fragment.mAdded) {
            if (f2016I) {
                Log.v("FragmentManager", "remove from detach: " + fragment);
            }
            synchronized (this.f2030g) {
                this.f2030g.remove(fragment);
            }
            if (m1878H0(fragment)) {
                this.f2045v = true;
            }
            fragment.mAdded = false;
        }
    }

    /* renamed from: C0 */
    public Fragment m1868C0() {
        return this.f2044u;
    }

    /* renamed from: D */
    public void m1869D() {
        this.f2046w = false;
        this.f2047x = false;
        m1930j0(2);
    }

    /* renamed from: D0 */
    public C0396q m1870D0(Fragment fragment) {
        return this.f2025G.m1975i(fragment);
    }

    /* renamed from: E */
    public void m1871E(Configuration configuration) {
        for (int i9 = 0; i9 < this.f2030g.size(); i9++) {
            Fragment fragment = this.f2030g.get(i9);
            if (fragment != null) {
                fragment.performConfigurationChanged(configuration);
            }
        }
    }

    /* renamed from: E0 */
    public void m1872E0() {
        m1947q0();
        if (this.f2035l.m216c()) {
            mo1854k();
        } else {
            this.f2034k.m213c();
        }
    }

    /* renamed from: F */
    public boolean m1873F(MenuItem menuItem) {
        if (this.f2040q < 1) {
            return false;
        }
        for (int i9 = 0; i9 < this.f2030g.size(); i9++) {
            Fragment fragment = this.f2030g.get(i9);
            if (fragment != null && fragment.performContextItemSelected(menuItem)) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: F0 */
    public void m1874F0(Fragment fragment) {
        if (f2016I) {
            Log.v("FragmentManager", "hide: " + fragment);
        }
        if (fragment.mHidden) {
            return;
        }
        fragment.mHidden = true;
        fragment.mHiddenChanged = true ^ fragment.mHiddenChanged;
    }

    /* renamed from: G */
    public void m1875G() {
        this.f2046w = false;
        this.f2047x = false;
        m1930j0(1);
    }

    /* renamed from: G0 */
    public boolean m1876G0() {
        return this.f2048y;
    }

    /* renamed from: H */
    public boolean m1877H(Menu menu, MenuInflater menuInflater) {
        if (this.f2040q < 1) {
            return false;
        }
        ArrayList<Fragment> arrayList = null;
        boolean z8 = false;
        for (int i9 = 0; i9 < this.f2030g.size(); i9++) {
            Fragment fragment = this.f2030g.get(i9);
            if (fragment != null && fragment.performCreateOptionsMenu(menu, menuInflater)) {
                if (arrayList == null) {
                    arrayList = new ArrayList<>();
                }
                arrayList.add(fragment);
                z8 = true;
            }
        }
        if (this.f2033j != null) {
            for (int i10 = 0; i10 < this.f2033j.size(); i10++) {
                Fragment fragment2 = this.f2033j.get(i10);
                if (arrayList == null || !arrayList.contains(fragment2)) {
                    fragment2.onDestroyOptionsMenu();
                }
            }
        }
        this.f2033j = arrayList;
        return z8;
    }

    /* renamed from: H0 */
    public final boolean m1878H0(Fragment fragment) {
        return (fragment.mHasMenu && fragment.mMenuVisible) || fragment.mChildFragmentManager.m1960x();
    }

    /* renamed from: I */
    public void m1879I() {
        this.f2048y = true;
        m1947q0();
        m1930j0(0);
        this.f2041r = null;
        this.f2042s = null;
        this.f2043t = null;
        if (this.f2034k != null) {
            this.f2035l.m217d();
            this.f2034k = null;
        }
    }

    /* renamed from: I0 */
    public boolean m1880I0(Fragment fragment) {
        if (fragment == null) {
            return true;
        }
        LayoutInflaterFactory2C0369h layoutInflaterFactory2C0369h = fragment.mFragmentManager;
        return fragment == layoutInflaterFactory2C0369h.m1868C0() && m1880I0(layoutInflaterFactory2C0369h.f2043t);
    }

    /* renamed from: J */
    public void m1881J() {
        m1930j0(1);
    }

    /* renamed from: J0 */
    public boolean m1882J0(int i9) {
        return this.f2040q >= i9;
    }

    /* renamed from: K */
    public void m1883K() {
        for (int i9 = 0; i9 < this.f2030g.size(); i9++) {
            Fragment fragment = this.f2030g.get(i9);
            if (fragment != null) {
                fragment.performLowMemory();
            }
        }
    }

    /* renamed from: K0 */
    public boolean m1884K0() {
        return this.f2046w || this.f2047x;
    }

    /* renamed from: L */
    public void m1885L(boolean z8) {
        for (int size = this.f2030g.size() - 1; size >= 0; size--) {
            Fragment fragment = this.f2030g.get(size);
            if (fragment != null) {
                fragment.performMultiWindowModeChanged(z8);
            }
        }
    }

    /* renamed from: L0 */
    public g m1886L0(Fragment fragment, int i9, boolean z8, int i10) throws Resources.NotFoundException {
        int iM1861q1;
        int nextAnim = fragment.getNextAnim();
        boolean z9 = false;
        fragment.setNextAnim(0);
        ViewGroup viewGroup = fragment.mContainer;
        if (viewGroup != null && viewGroup.getLayoutTransition() != null) {
            return null;
        }
        Animation animationOnCreateAnimation = fragment.onCreateAnimation(i9, z8, nextAnim);
        if (animationOnCreateAnimation != null) {
            return new g(animationOnCreateAnimation);
        }
        Animator animatorOnCreateAnimator = fragment.onCreateAnimator(i9, z8, nextAnim);
        if (animatorOnCreateAnimator != null) {
            return new g(animatorOnCreateAnimator);
        }
        if (nextAnim != 0) {
            boolean zEquals = "anim".equals(this.f2041r.m1842e().getResources().getResourceTypeName(nextAnim));
            if (zEquals) {
                try {
                    Animation animationLoadAnimation = AnimationUtils.loadAnimation(this.f2041r.m1842e(), nextAnim);
                    if (animationLoadAnimation != null) {
                        return new g(animationLoadAnimation);
                    }
                    z9 = true;
                } catch (Resources.NotFoundException e9) {
                    throw e9;
                } catch (RuntimeException unused) {
                }
            }
            if (!z9) {
                try {
                    Animator animatorLoadAnimator = AnimatorInflater.loadAnimator(this.f2041r.m1842e(), nextAnim);
                    if (animatorLoadAnimator != null) {
                        return new g(animatorLoadAnimator);
                    }
                } catch (RuntimeException e10) {
                    if (zEquals) {
                        throw e10;
                    }
                    Animation animationLoadAnimation2 = AnimationUtils.loadAnimation(this.f2041r.m1842e(), nextAnim);
                    if (animationLoadAnimation2 != null) {
                        return new g(animationLoadAnimation2);
                    }
                }
            }
        }
        if (i9 == 0 || (iM1861q1 = m1861q1(i9, z8)) < 0) {
            return null;
        }
        switch (iM1861q1) {
            case 1:
                return m1859P0(1.125f, 1.0f, BitmapDescriptorFactory.HUE_RED, 1.0f);
            case 2:
                return m1859P0(1.0f, 0.975f, 1.0f, BitmapDescriptorFactory.HUE_RED);
            case 3:
                return m1859P0(0.975f, 1.0f, BitmapDescriptorFactory.HUE_RED, 1.0f);
            case 4:
                return m1859P0(1.0f, 1.075f, 1.0f, BitmapDescriptorFactory.HUE_RED);
            case 5:
                return m1858N0(BitmapDescriptorFactory.HUE_RED, 1.0f);
            case 6:
                return m1858N0(1.0f, BitmapDescriptorFactory.HUE_RED);
            default:
                if (i10 == 0 && this.f2041r.mo1770l()) {
                    this.f2041r.mo1769k();
                }
                return null;
        }
    }

    /* renamed from: M */
    public void m1887M(Fragment fragment, Bundle bundle, boolean z8) {
        Fragment fragment2 = this.f2043t;
        if (fragment2 != null) {
            AbstractC0368g fragmentManager = fragment2.getFragmentManager();
            if (fragmentManager instanceof LayoutInflaterFactory2C0369h) {
                ((LayoutInflaterFactory2C0369h) fragmentManager).m1887M(fragment, bundle, true);
            }
        }
        Iterator<i> it = this.f2039p.iterator();
        while (it.hasNext()) {
            i next = it.next();
            if (!z8 || next.f2072a) {
                next.getClass();
                throw null;
            }
        }
    }

    /* renamed from: M0 */
    public void m1888M0(Fragment fragment) {
        if (this.f2031h.get(fragment.mWho) != null) {
            return;
        }
        this.f2031h.put(fragment.mWho, fragment);
        if (fragment.mRetainInstanceChangedWhileDetached) {
            if (fragment.mRetainInstance) {
                m1948r(fragment);
            } else {
                m1918c1(fragment);
            }
            fragment.mRetainInstanceChangedWhileDetached = false;
        }
        if (f2016I) {
            Log.v("FragmentManager", "Added fragment to active set " + fragment);
        }
    }

    /* renamed from: N */
    public void m1889N(Fragment fragment, Context context, boolean z8) {
        Fragment fragment2 = this.f2043t;
        if (fragment2 != null) {
            AbstractC0368g fragmentManager = fragment2.getFragmentManager();
            if (fragmentManager instanceof LayoutInflaterFactory2C0369h) {
                ((LayoutInflaterFactory2C0369h) fragmentManager).m1889N(fragment, context, true);
            }
        }
        Iterator<i> it = this.f2039p.iterator();
        while (it.hasNext()) {
            i next = it.next();
            if (!z8 || next.f2072a) {
                next.getClass();
                throw null;
            }
        }
    }

    /* renamed from: O */
    public void m1890O(Fragment fragment, Bundle bundle, boolean z8) {
        Fragment fragment2 = this.f2043t;
        if (fragment2 != null) {
            AbstractC0368g fragmentManager = fragment2.getFragmentManager();
            if (fragmentManager instanceof LayoutInflaterFactory2C0369h) {
                ((LayoutInflaterFactory2C0369h) fragmentManager).m1890O(fragment, bundle, true);
            }
        }
        Iterator<i> it = this.f2039p.iterator();
        while (it.hasNext()) {
            i next = it.next();
            if (!z8 || next.f2072a) {
                next.getClass();
                throw null;
            }
        }
    }

    /* renamed from: O0 */
    public void m1891O0(Fragment fragment) {
        if (this.f2031h.get(fragment.mWho) == null) {
            return;
        }
        if (f2016I) {
            Log.v("FragmentManager", "Removed fragment from active set " + fragment);
        }
        for (Fragment fragment2 : this.f2031h.values()) {
            if (fragment2 != null && fragment.mWho.equals(fragment2.mTargetWho)) {
                fragment2.mTarget = fragment;
                fragment2.mTargetWho = null;
            }
        }
        this.f2031h.put(fragment.mWho, null);
        m1918c1(fragment);
        String str = fragment.mTargetWho;
        if (str != null) {
            fragment.mTarget = this.f2031h.get(str);
        }
        fragment.initState();
    }

    /* renamed from: P */
    public void m1892P(Fragment fragment, boolean z8) {
        Fragment fragment2 = this.f2043t;
        if (fragment2 != null) {
            AbstractC0368g fragmentManager = fragment2.getFragmentManager();
            if (fragmentManager instanceof LayoutInflaterFactory2C0369h) {
                ((LayoutInflaterFactory2C0369h) fragmentManager).m1892P(fragment, true);
            }
        }
        Iterator<i> it = this.f2039p.iterator();
        while (it.hasNext()) {
            i next = it.next();
            if (!z8 || next.f2072a) {
                next.getClass();
                throw null;
            }
        }
    }

    /* renamed from: Q */
    public void m1893Q(Fragment fragment, boolean z8) {
        Fragment fragment2 = this.f2043t;
        if (fragment2 != null) {
            AbstractC0368g fragmentManager = fragment2.getFragmentManager();
            if (fragmentManager instanceof LayoutInflaterFactory2C0369h) {
                ((LayoutInflaterFactory2C0369h) fragmentManager).m1893Q(fragment, true);
            }
        }
        Iterator<i> it = this.f2039p.iterator();
        while (it.hasNext()) {
            i next = it.next();
            if (!z8 || next.f2072a) {
                next.getClass();
                throw null;
            }
        }
    }

    /* renamed from: Q0 */
    public final void m1894Q0(C5303b<Fragment> c5303b) {
        int size = c5303b.size();
        for (int i9 = 0; i9 < size; i9++) {
            Fragment fragmentM20711h = c5303b.m20711h(i9);
            if (!fragmentM20711h.mAdded) {
                View viewRequireView = fragmentM20711h.requireView();
                fragmentM20711h.mPostponedAlpha = viewRequireView.getAlpha();
                viewRequireView.setAlpha(BitmapDescriptorFactory.HUE_RED);
            }
        }
    }

    /* renamed from: R */
    public void m1895R(Fragment fragment, boolean z8) {
        Fragment fragment2 = this.f2043t;
        if (fragment2 != null) {
            AbstractC0368g fragmentManager = fragment2.getFragmentManager();
            if (fragmentManager instanceof LayoutInflaterFactory2C0369h) {
                ((LayoutInflaterFactory2C0369h) fragmentManager).m1895R(fragment, true);
            }
        }
        Iterator<i> it = this.f2039p.iterator();
        while (it.hasNext()) {
            i next = it.next();
            if (!z8 || next.f2072a) {
                next.getClass();
                throw null;
            }
        }
    }

    /* renamed from: R0 */
    public void m1896R0(Fragment fragment) throws Resources.NotFoundException {
        if (fragment == null) {
            return;
        }
        if (!this.f2031h.containsKey(fragment.mWho)) {
            if (f2016I) {
                Log.v("FragmentManager", "Ignoring moving " + fragment + " to state " + this.f2040q + "since it is not added to " + this);
                return;
            }
            return;
        }
        int iMin = this.f2040q;
        if (fragment.mRemoving) {
            iMin = fragment.isInBackStack() ? Math.min(iMin, 1) : Math.min(iMin, 0);
        }
        m1902U0(fragment, iMin, fragment.getNextTransition(), fragment.getNextTransitionStyle(), false);
        if (fragment.mView != null) {
            Fragment fragmentM1959w0 = m1959w0(fragment);
            if (fragmentM1959w0 != null) {
                View view = fragmentM1959w0.mView;
                ViewGroup viewGroup = fragment.mContainer;
                int iIndexOfChild = viewGroup.indexOfChild(view);
                int iIndexOfChild2 = viewGroup.indexOfChild(fragment.mView);
                if (iIndexOfChild2 < iIndexOfChild) {
                    viewGroup.removeViewAt(iIndexOfChild2);
                    viewGroup.addView(fragment.mView, iIndexOfChild);
                }
            }
            if (fragment.mIsNewlyAdded && fragment.mContainer != null) {
                float f9 = fragment.mPostponedAlpha;
                if (f9 > BitmapDescriptorFactory.HUE_RED) {
                    fragment.mView.setAlpha(f9);
                }
                fragment.mPostponedAlpha = BitmapDescriptorFactory.HUE_RED;
                fragment.mIsNewlyAdded = false;
                g gVarM1886L0 = m1886L0(fragment, fragment.getNextTransition(), true, fragment.getNextTransitionStyle());
                if (gVarM1886L0 != null) {
                    Animation animation = gVarM1886L0.f2065a;
                    if (animation != null) {
                        fragment.mView.startAnimation(animation);
                    } else {
                        gVarM1886L0.f2066b.setTarget(fragment.mView);
                        gVarM1886L0.f2066b.start();
                    }
                }
            }
        }
        if (fragment.mHiddenChanged) {
            m1865B(fragment);
        }
    }

    /* renamed from: S */
    public void m1897S(Fragment fragment, Context context, boolean z8) {
        Fragment fragment2 = this.f2043t;
        if (fragment2 != null) {
            AbstractC0368g fragmentManager = fragment2.getFragmentManager();
            if (fragmentManager instanceof LayoutInflaterFactory2C0369h) {
                ((LayoutInflaterFactory2C0369h) fragmentManager).m1897S(fragment, context, true);
            }
        }
        Iterator<i> it = this.f2039p.iterator();
        while (it.hasNext()) {
            i next = it.next();
            if (!z8 || next.f2072a) {
                next.getClass();
                throw null;
            }
        }
    }

    /* renamed from: S0 */
    public void m1898S0(int i9, boolean z8) throws Resources.NotFoundException {
        AbstractC0367f abstractC0367f;
        if (this.f2041r == null && i9 != 0) {
            throw new IllegalStateException("No activity");
        }
        if (z8 || i9 != this.f2040q) {
            this.f2040q = i9;
            int size = this.f2030g.size();
            for (int i10 = 0; i10 < size; i10++) {
                m1896R0(this.f2030g.get(i10));
            }
            for (Fragment fragment : this.f2031h.values()) {
                if (fragment != null && (fragment.mRemoving || fragment.mDetached)) {
                    if (!fragment.mIsNewlyAdded) {
                        m1896R0(fragment);
                    }
                }
            }
            m1942o1();
            if (this.f2045v && (abstractC0367f = this.f2041r) != null && this.f2040q == 4) {
                abstractC0367f.mo1776r();
                this.f2045v = false;
            }
        }
    }

    /* renamed from: T */
    public void m1899T(Fragment fragment, Bundle bundle, boolean z8) {
        Fragment fragment2 = this.f2043t;
        if (fragment2 != null) {
            AbstractC0368g fragmentManager = fragment2.getFragmentManager();
            if (fragmentManager instanceof LayoutInflaterFactory2C0369h) {
                ((LayoutInflaterFactory2C0369h) fragmentManager).m1899T(fragment, bundle, true);
            }
        }
        Iterator<i> it = this.f2039p.iterator();
        while (it.hasNext()) {
            i next = it.next();
            if (!z8 || next.f2072a) {
                next.getClass();
                throw null;
            }
        }
    }

    /* renamed from: T0 */
    public void m1900T0(Fragment fragment) throws Resources.NotFoundException {
        m1902U0(fragment, this.f2040q, 0, 0, false);
    }

    /* renamed from: U */
    public void m1901U(Fragment fragment, boolean z8) {
        Fragment fragment2 = this.f2043t;
        if (fragment2 != null) {
            AbstractC0368g fragmentManager = fragment2.getFragmentManager();
            if (fragmentManager instanceof LayoutInflaterFactory2C0369h) {
                ((LayoutInflaterFactory2C0369h) fragmentManager).m1901U(fragment, true);
            }
        }
        Iterator<i> it = this.f2039p.iterator();
        while (it.hasNext()) {
            i next = it.next();
            if (!z8 || next.f2072a) {
                next.getClass();
                throw null;
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:149:0x02dc  */
    /* JADX WARN: Removed duplicated region for block: B:155:0x02fd  */
    /* JADX WARN: Removed duplicated region for block: B:217:0x0407  */
    /* JADX WARN: Removed duplicated region for block: B:272:0x04db  */
    /* JADX WARN: Removed duplicated region for block: B:276:? A[RETURN, SYNTHETIC] */
    /* renamed from: U0 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void m1902U0(Fragment fragment, int i9, int i10, int i11, boolean z8) throws Resources.NotFoundException {
        int i12;
        Fragment fragment2;
        int i13;
        ViewGroup viewGroup;
        int i14;
        String str;
        String str2;
        int i15;
        ViewGroup viewGroup2;
        String resourceName;
        int i16;
        int i17;
        int i18 = 1;
        boolean zIsChangingConfigurations = true;
        if (!fragment.mAdded || fragment.mDetached) {
            i12 = i9;
            if (i12 > 1) {
                i12 = 1;
            }
        } else {
            i12 = i9;
        }
        if (fragment.mRemoving && i12 > (i17 = fragment.mState)) {
            i12 = (i17 == 0 && fragment.isInBackStack()) ? 1 : fragment.mState;
        }
        if (fragment.mDeferStart && fragment.mState < 3 && i12 > 2) {
            i12 = 2;
        }
        Lifecycle.State state = fragment.mMaxState;
        int iMin = state == Lifecycle.State.CREATED ? Math.min(i12, 1) : Math.min(i12, state.ordinal());
        int i19 = fragment.mState;
        if (i19 > iMin) {
            if (i19 > iMin) {
                if (i19 != 1) {
                    if (i19 != 2) {
                        if (i19 == 3) {
                            i14 = 3;
                        } else if (i19 == 4) {
                            if (iMin < 4) {
                                if (f2016I) {
                                    Log.v("FragmentManager", "movefrom RESUMED: " + fragment);
                                }
                                fragment.performPause();
                                m1895R(fragment, false);
                            }
                            i14 = 3;
                        }
                        if (iMin < i14) {
                            if (f2016I) {
                                Log.v("FragmentManager", "movefrom STARTED: " + fragment);
                            }
                            fragment.performStop();
                            m1907X(fragment, false);
                        }
                        i13 = 2;
                    } else {
                        i13 = 2;
                    }
                    if (iMin < i13) {
                        if (f2016I) {
                            Log.v("FragmentManager", "movefrom ACTIVITY_CREATED: " + fragment);
                        }
                        if (fragment.mView != null && this.f2041r.mo1772n(fragment) && fragment.mSavedViewState == null) {
                            m1929i1(fragment);
                        }
                        fragment.performDestroyView();
                        m1911Z(fragment, false);
                        View view = fragment.mView;
                        if (view != null && (viewGroup = fragment.mContainer) != null) {
                            viewGroup.endViewTransition(view);
                            fragment.mView.clearAnimation();
                            if (fragment.getParentFragment() == null || !fragment.getParentFragment().mRemoving) {
                                g gVarM1886L0 = (this.f2040q <= 0 || this.f2048y || fragment.mView.getVisibility() != 0 || fragment.mPostponedAlpha < BitmapDescriptorFactory.HUE_RED) ? null : m1886L0(fragment, i10, false, i11);
                                fragment.mPostponedAlpha = BitmapDescriptorFactory.HUE_RED;
                                if (gVarM1886L0 != null) {
                                    m1952t(fragment, gVarM1886L0, iMin);
                                }
                                fragment.mContainer.removeView(fragment.mView);
                            }
                        }
                        fragment.mContainer = null;
                        fragment.mView = null;
                        fragment.mViewLifecycleOwner = null;
                        fragment.mViewLifecycleOwnerLiveData.mo2061l(null);
                        fragment.mInnerView = null;
                        fragment.mInLayout = false;
                    }
                    if (iMin < 1) {
                    }
                } else if (iMin < 1) {
                    if (this.f2048y) {
                        if (fragment.getAnimatingAway() != null) {
                            View animatingAway = fragment.getAnimatingAway();
                            fragment.setAnimatingAway(null);
                            animatingAway.clearAnimation();
                        } else if (fragment.getAnimator() != null) {
                            Animator animator = fragment.getAnimator();
                            fragment.setAnimator(null);
                            animator.cancel();
                        }
                    }
                    if (fragment.getAnimatingAway() == null && fragment.getAnimator() == null) {
                        if (f2016I) {
                            Log.v("FragmentManager", "movefrom CREATED: " + fragment);
                        }
                        Object[] objArr = fragment.mRemoving && !fragment.isInBackStack();
                        if (objArr == true || this.f2025G.m1978l(fragment)) {
                            AbstractC0367f abstractC0367f = this.f2041r;
                            if (abstractC0367f instanceof InterfaceC0397r) {
                                zIsChangingConfigurations = this.f2025G.m1976j();
                            } else if (abstractC0367f.m1842e() instanceof Activity) {
                                zIsChangingConfigurations = true ^ ((Activity) this.f2041r.m1842e()).isChangingConfigurations();
                            }
                            if (objArr != false || zIsChangingConfigurations) {
                                this.f2025G.m1972e(fragment);
                            }
                            fragment.performDestroy();
                            m1892P(fragment, false);
                        } else {
                            fragment.mState = 0;
                        }
                        fragment.performDetach();
                        m1893Q(fragment, false);
                        if (!z8) {
                            if (objArr == true || this.f2025G.m1978l(fragment)) {
                                m1891O0(fragment);
                            } else {
                                fragment.mHost = null;
                                fragment.mParentFragment = null;
                                fragment.mFragmentManager = null;
                                String str3 = fragment.mTargetWho;
                                if (str3 != null && (fragment2 = this.f2031h.get(str3)) != null && fragment2.getRetainInstance()) {
                                    fragment.mTarget = fragment2;
                                }
                            }
                        }
                    } else {
                        fragment.setStateAfterAnimating(iMin);
                    }
                }
            }
            if (fragment.mState == i18) {
                Log.w("FragmentManager", "moveToState: Fragment state for " + fragment + " not updated inline; expected state " + i18 + " found " + fragment.mState);
                fragment.mState = i18;
                return;
            }
            return;
        }
        if (fragment.mFromLayout && !fragment.mInLayout) {
            return;
        }
        if (fragment.getAnimatingAway() != null || fragment.getAnimator() != null) {
            fragment.setAnimatingAway(null);
            fragment.setAnimator(null);
            m1902U0(fragment, fragment.getStateAfterAnimating(), 0, 0, true);
        }
        int i20 = fragment.mState;
        if (i20 != 0) {
            if (i20 != 1) {
                if (i20 == 2) {
                    i15 = 2;
                } else if (i20 == 3) {
                    i16 = 3;
                    if (iMin > i16) {
                        if (f2016I) {
                            Log.v("FragmentManager", "moveto RESUMED: " + fragment);
                        }
                        fragment.performResume();
                        m1901U(fragment, false);
                        fragment.mSavedFragmentState = null;
                        fragment.mSavedViewState = null;
                    }
                }
            }
            if (iMin > i15) {
                if (f2016I) {
                    Log.v("FragmentManager", "moveto STARTED: " + fragment);
                }
                fragment.performStart();
                m1905W(fragment, false);
            }
            i16 = 3;
            if (iMin > i16) {
            }
        } else if (iMin > 0) {
            if (f2016I) {
                Log.v("FragmentManager", "moveto CREATED: " + fragment);
            }
            Bundle bundle = fragment.mSavedFragmentState;
            if (bundle != null) {
                bundle.setClassLoader(this.f2041r.m1842e().getClassLoader());
                fragment.mSavedViewState = fragment.mSavedFragmentState.getSparseParcelableArray("android:view_state");
                Fragment fragmentMo1850g = mo1850g(fragment.mSavedFragmentState, "android:target_state");
                String str4 = fragmentMo1850g != null ? fragmentMo1850g.mWho : null;
                fragment.mTargetWho = str4;
                if (str4 != null) {
                    fragment.mTargetRequestCode = fragment.mSavedFragmentState.getInt("android:target_req_state", 0);
                }
                Boolean bool = fragment.mSavedUserVisibleHint;
                if (bool != null) {
                    fragment.mUserVisibleHint = bool.booleanValue();
                    fragment.mSavedUserVisibleHint = null;
                } else {
                    fragment.mUserVisibleHint = fragment.mSavedFragmentState.getBoolean("android:user_visible_hint", true);
                }
                if (!fragment.mUserVisibleHint) {
                    fragment.mDeferStart = true;
                    if (iMin > 2) {
                        iMin = 2;
                    }
                }
            }
            AbstractC0367f abstractC0367f2 = this.f2041r;
            fragment.mHost = abstractC0367f2;
            Fragment fragment3 = this.f2043t;
            fragment.mParentFragment = fragment3;
            fragment.mFragmentManager = fragment3 != null ? fragment3.mChildFragmentManager : abstractC0367f2.f2013f;
            Fragment fragment4 = fragment.mTarget;
            if (fragment4 != null) {
                Fragment fragment5 = this.f2031h.get(fragment4.mWho);
                Fragment fragment6 = fragment.mTarget;
                if (fragment5 != fragment6) {
                    throw new IllegalStateException("Fragment " + fragment + " declared target fragment " + fragment.mTarget + " that does not belong to this FragmentManager!");
                }
                if (fragment6.mState < 1) {
                    str = "Fragment ";
                    str2 = " declared target fragment ";
                    m1902U0(fragment6, 1, 0, 0, true);
                } else {
                    str = "Fragment ";
                    str2 = " declared target fragment ";
                }
                fragment.mTargetWho = fragment.mTarget.mWho;
                fragment.mTarget = null;
            } else {
                str = "Fragment ";
                str2 = " declared target fragment ";
            }
            String str5 = fragment.mTargetWho;
            if (str5 != null) {
                Fragment fragment7 = this.f2031h.get(str5);
                if (fragment7 == null) {
                    throw new IllegalStateException(str + fragment + str2 + fragment.mTargetWho + " that does not belong to this FragmentManager!");
                }
                if (fragment7.mState < 1) {
                    m1902U0(fragment7, 1, 0, 0, true);
                }
            }
            m1897S(fragment, this.f2041r.m1842e(), false);
            fragment.performAttach();
            Fragment fragment8 = fragment.mParentFragment;
            if (fragment8 == null) {
                this.f2041r.mo1765g(fragment);
            } else {
                fragment8.onAttachFragment(fragment);
            }
            m1889N(fragment, this.f2041r.m1842e(), false);
            if (fragment.mIsCreated) {
                fragment.restoreChildFragmentState(fragment.mSavedFragmentState);
                fragment.mState = 1;
            } else {
                m1899T(fragment, fragment.mSavedFragmentState, false);
                fragment.performCreate(fragment.mSavedFragmentState);
                m1890O(fragment, fragment.mSavedFragmentState, false);
            }
        }
        if (iMin > 0) {
            m1944p0(fragment);
        }
        if (iMin > 1) {
            if (f2016I) {
                Log.v("FragmentManager", "moveto ACTIVITY_CREATED: " + fragment);
            }
            if (!fragment.mFromLayout) {
                int i21 = fragment.mContainerId;
                if (i21 != 0) {
                    if (i21 == -1) {
                        m1945p1(new IllegalArgumentException("Cannot create fragment " + fragment + " for a container view with no id"));
                    }
                    viewGroup2 = (ViewGroup) this.f2042s.mo1761b(fragment.mContainerId);
                    if (viewGroup2 == null && !fragment.mRestored) {
                        try {
                            resourceName = fragment.getResources().getResourceName(fragment.mContainerId);
                        } catch (Resources.NotFoundException unused) {
                            resourceName = "unknown";
                        }
                        m1945p1(new IllegalArgumentException("No view found for id 0x" + Integer.toHexString(fragment.mContainerId) + " (" + resourceName + ") for fragment " + fragment));
                    }
                } else {
                    viewGroup2 = null;
                }
                fragment.mContainer = viewGroup2;
                fragment.performCreateView(fragment.performGetLayoutInflater(fragment.mSavedFragmentState), viewGroup2, fragment.mSavedFragmentState);
                View view2 = fragment.mView;
                if (view2 != null) {
                    fragment.mInnerView = view2;
                    view2.setSaveFromParentEnabled(false);
                    if (viewGroup2 != null) {
                        viewGroup2.addView(fragment.mView);
                    }
                    if (fragment.mHidden) {
                        fragment.mView.setVisibility(8);
                    }
                    fragment.onViewCreated(fragment.mView, fragment.mSavedFragmentState);
                    m1909Y(fragment, fragment.mView, fragment.mSavedFragmentState, false);
                    fragment.mIsNewlyAdded = fragment.mView.getVisibility() == 0 && fragment.mContainer != null;
                } else {
                    fragment.mInnerView = null;
                }
            }
            fragment.performActivityCreated(fragment.mSavedFragmentState);
            m1887M(fragment, fragment.mSavedFragmentState, false);
            if (fragment.mView != null) {
                fragment.restoreViewState(fragment.mSavedFragmentState);
            }
            fragment.mSavedFragmentState = null;
        }
        i15 = 2;
        if (iMin > i15) {
        }
        i16 = 3;
        if (iMin > i16) {
        }
        i18 = iMin;
        if (fragment.mState == i18) {
        }
    }

    /* renamed from: V */
    public void m1903V(Fragment fragment, Bundle bundle, boolean z8) {
        Fragment fragment2 = this.f2043t;
        if (fragment2 != null) {
            AbstractC0368g fragmentManager = fragment2.getFragmentManager();
            if (fragmentManager instanceof LayoutInflaterFactory2C0369h) {
                ((LayoutInflaterFactory2C0369h) fragmentManager).m1903V(fragment, bundle, true);
            }
        }
        Iterator<i> it = this.f2039p.iterator();
        while (it.hasNext()) {
            i next = it.next();
            if (!z8 || next.f2072a) {
                next.getClass();
                throw null;
            }
        }
    }

    /* renamed from: V0 */
    public void m1904V0() {
        this.f2046w = false;
        this.f2047x = false;
        int size = this.f2030g.size();
        for (int i9 = 0; i9 < size; i9++) {
            Fragment fragment = this.f2030g.get(i9);
            if (fragment != null) {
                fragment.noteStateNotSaved();
            }
        }
    }

    /* renamed from: W */
    public void m1905W(Fragment fragment, boolean z8) {
        Fragment fragment2 = this.f2043t;
        if (fragment2 != null) {
            AbstractC0368g fragmentManager = fragment2.getFragmentManager();
            if (fragmentManager instanceof LayoutInflaterFactory2C0369h) {
                ((LayoutInflaterFactory2C0369h) fragmentManager).m1905W(fragment, true);
            }
        }
        Iterator<i> it = this.f2039p.iterator();
        while (it.hasNext()) {
            i next = it.next();
            if (!z8 || next.f2072a) {
                next.getClass();
                throw null;
            }
        }
    }

    /* renamed from: W0 */
    public void m1906W0(Fragment fragment) {
        if (fragment.mDeferStart) {
            if (this.f2028e) {
                this.f2049z = true;
            } else {
                fragment.mDeferStart = false;
                m1902U0(fragment, this.f2040q, 0, 0, false);
            }
        }
    }

    /* renamed from: X */
    public void m1907X(Fragment fragment, boolean z8) {
        Fragment fragment2 = this.f2043t;
        if (fragment2 != null) {
            AbstractC0368g fragmentManager = fragment2.getFragmentManager();
            if (fragmentManager instanceof LayoutInflaterFactory2C0369h) {
                ((LayoutInflaterFactory2C0369h) fragmentManager).m1907X(fragment, true);
            }
        }
        Iterator<i> it = this.f2039p.iterator();
        while (it.hasNext()) {
            i next = it.next();
            if (!z8 || next.f2072a) {
                next.getClass();
                throw null;
            }
        }
    }

    /* renamed from: X0 */
    public final boolean m1908X0(String str, int i9, int i10) {
        m1947q0();
        m1941o0(true);
        Fragment fragment = this.f2044u;
        if (fragment != null && i9 < 0 && str == null && fragment.getChildFragmentManager().mo1854k()) {
            return true;
        }
        boolean zM1910Y0 = m1910Y0(this.f2019A, this.f2020B, str, i9, i10);
        if (zM1910Y0) {
            this.f2028e = true;
            try {
                m1916b1(this.f2019A, this.f2020B);
            } finally {
                m1964z();
            }
        }
        m1950r1();
        m1934l0();
        m1958w();
        return zM1910Y0;
    }

    /* renamed from: Y */
    public void m1909Y(Fragment fragment, View view, Bundle bundle, boolean z8) {
        Fragment fragment2 = this.f2043t;
        if (fragment2 != null) {
            AbstractC0368g fragmentManager = fragment2.getFragmentManager();
            if (fragmentManager instanceof LayoutInflaterFactory2C0369h) {
                ((LayoutInflaterFactory2C0369h) fragmentManager).m1909Y(fragment, view, bundle, true);
            }
        }
        Iterator<i> it = this.f2039p.iterator();
        while (it.hasNext()) {
            i next = it.next();
            if (!z8 || next.f2072a) {
                next.getClass();
                throw null;
            }
        }
    }

    /* renamed from: Y0 */
    public boolean m1910Y0(ArrayList<C0362a> arrayList, ArrayList<Boolean> arrayList2, String str, int i9, int i10) {
        int i11;
        ArrayList<C0362a> arrayList3 = this.f2032i;
        if (arrayList3 == null) {
            return false;
        }
        if (str == null && i9 < 0 && (i10 & 1) == 0) {
            int size = arrayList3.size() - 1;
            if (size < 0) {
                return false;
            }
            arrayList.add(this.f2032i.remove(size));
            arrayList2.add(Boolean.TRUE);
        } else {
            if (str != null || i9 >= 0) {
                int size2 = arrayList3.size() - 1;
                while (size2 >= 0) {
                    C0362a c0362a = this.f2032i.get(size2);
                    if ((str != null && str.equals(c0362a.m1810z())) || (i9 >= 0 && i9 == c0362a.f2005u)) {
                        break;
                    }
                    size2--;
                }
                if (size2 < 0) {
                    return false;
                }
                if ((i10 & 1) != 0) {
                    while (true) {
                        size2--;
                        if (size2 < 0) {
                            break;
                        }
                        C0362a c0362a2 = this.f2032i.get(size2);
                        if (str == null || !str.equals(c0362a2.m1810z())) {
                            if (i9 < 0 || i9 != c0362a2.f2005u) {
                                break;
                            }
                        }
                    }
                }
                i11 = size2;
            } else {
                i11 = -1;
            }
            if (i11 == this.f2032i.size() - 1) {
                return false;
            }
            for (int size3 = this.f2032i.size() - 1; size3 > i11; size3--) {
                arrayList.add(this.f2032i.remove(size3));
                arrayList2.add(Boolean.TRUE);
            }
        }
        return true;
    }

    /* renamed from: Z */
    public void m1911Z(Fragment fragment, boolean z8) {
        Fragment fragment2 = this.f2043t;
        if (fragment2 != null) {
            AbstractC0368g fragmentManager = fragment2.getFragmentManager();
            if (fragmentManager instanceof LayoutInflaterFactory2C0369h) {
                ((LayoutInflaterFactory2C0369h) fragmentManager).m1911Z(fragment, true);
            }
        }
        Iterator<i> it = this.f2039p.iterator();
        while (it.hasNext()) {
            i next = it.next();
            if (!z8 || next.f2072a) {
                next.getClass();
                throw null;
            }
        }
    }

    /* renamed from: Z0 */
    public final int m1912Z0(ArrayList<C0362a> arrayList, ArrayList<Boolean> arrayList2, int i9, int i10, C5303b<Fragment> c5303b) throws Resources.NotFoundException {
        int i11 = i10;
        for (int i12 = i10 - 1; i12 >= i9; i12--) {
            C0362a c0362a = arrayList.get(i12);
            boolean zBooleanValue = arrayList2.get(i12).booleanValue();
            if (c0362a.m1788D() && !c0362a.m1787B(arrayList, i12 + 1, i10)) {
                if (this.f2024F == null) {
                    this.f2024F = new ArrayList<>();
                }
                m mVar = new m(c0362a, zBooleanValue);
                this.f2024F.add(mVar);
                c0362a.m1790F(mVar);
                if (zBooleanValue) {
                    c0362a.m1807w();
                } else {
                    c0362a.m1808x(false);
                }
                i11--;
                if (i12 != i11) {
                    arrayList.remove(i12);
                    arrayList.add(i11, c0362a);
                }
                m1940o(c5303b);
            }
        }
        return i11;
    }

    @Override // androidx.fragment.app.AbstractC0368g
    /* renamed from: a */
    public AbstractC0372k mo1844a() {
        return new C0362a(this);
    }

    /* renamed from: a0 */
    public boolean m1913a0(MenuItem menuItem) {
        if (this.f2040q < 1) {
            return false;
        }
        for (int i9 = 0; i9 < this.f2030g.size(); i9++) {
            Fragment fragment = this.f2030g.get(i9);
            if (fragment != null && fragment.performOptionsItemSelected(menuItem)) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: a1 */
    public void m1914a1(Fragment fragment) {
        if (f2016I) {
            Log.v("FragmentManager", "remove: " + fragment + " nesting=" + fragment.mBackStackNesting);
        }
        boolean z8 = !fragment.isInBackStack();
        if (!fragment.mDetached || z8) {
            synchronized (this.f2030g) {
                this.f2030g.remove(fragment);
            }
            if (m1878H0(fragment)) {
                this.f2045v = true;
            }
            fragment.mAdded = false;
            fragment.mRemoving = true;
        }
    }

    @Override // androidx.fragment.app.AbstractC0368g
    /* renamed from: b */
    public void mo1845b(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        int size;
        int size2;
        int size3;
        int size4;
        String str2 = str + "    ";
        if (!this.f2031h.isEmpty()) {
            printWriter.print(str);
            printWriter.print("Active Fragments in ");
            printWriter.print(Integer.toHexString(System.identityHashCode(this)));
            printWriter.println(":");
            for (Fragment fragment : this.f2031h.values()) {
                printWriter.print(str);
                printWriter.println(fragment);
                if (fragment != null) {
                    fragment.dump(str2, fileDescriptor, printWriter, strArr);
                }
            }
        }
        int size5 = this.f2030g.size();
        if (size5 > 0) {
            printWriter.print(str);
            printWriter.println("Added Fragments:");
            for (int i9 = 0; i9 < size5; i9++) {
                Fragment fragment2 = this.f2030g.get(i9);
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(i9);
                printWriter.print(": ");
                printWriter.println(fragment2.toString());
            }
        }
        ArrayList<Fragment> arrayList = this.f2033j;
        if (arrayList != null && (size4 = arrayList.size()) > 0) {
            printWriter.print(str);
            printWriter.println("Fragments Created Menus:");
            for (int i10 = 0; i10 < size4; i10++) {
                Fragment fragment3 = this.f2033j.get(i10);
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(i10);
                printWriter.print(": ");
                printWriter.println(fragment3.toString());
            }
        }
        ArrayList<C0362a> arrayList2 = this.f2032i;
        if (arrayList2 != null && (size3 = arrayList2.size()) > 0) {
            printWriter.print(str);
            printWriter.println("Back Stack:");
            for (int i11 = 0; i11 < size3; i11++) {
                C0362a c0362a = this.f2032i.get(i11);
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(i11);
                printWriter.print(": ");
                printWriter.println(c0362a.toString());
                c0362a.m1805u(str2, printWriter);
            }
        }
        synchronized (this) {
            ArrayList<C0362a> arrayList3 = this.f2036m;
            if (arrayList3 != null && (size2 = arrayList3.size()) > 0) {
                printWriter.print(str);
                printWriter.println("Back Stack Indices:");
                for (int i12 = 0; i12 < size2; i12++) {
                    Object obj = (C0362a) this.f2036m.get(i12);
                    printWriter.print(str);
                    printWriter.print("  #");
                    printWriter.print(i12);
                    printWriter.print(": ");
                    printWriter.println(obj);
                }
            }
            ArrayList<Integer> arrayList4 = this.f2037n;
            if (arrayList4 != null && arrayList4.size() > 0) {
                printWriter.print(str);
                printWriter.print("mAvailBackStackIndices: ");
                printWriter.println(Arrays.toString(this.f2037n.toArray()));
            }
        }
        ArrayList<k> arrayList5 = this.f2027d;
        if (arrayList5 != null && (size = arrayList5.size()) > 0) {
            printWriter.print(str);
            printWriter.println("Pending Actions:");
            for (int i13 = 0; i13 < size; i13++) {
                Object obj2 = (k) this.f2027d.get(i13);
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(i13);
                printWriter.print(": ");
                printWriter.println(obj2);
            }
        }
        printWriter.print(str);
        printWriter.println("FragmentManager misc state:");
        printWriter.print(str);
        printWriter.print("  mHost=");
        printWriter.println(this.f2041r);
        printWriter.print(str);
        printWriter.print("  mContainer=");
        printWriter.println(this.f2042s);
        if (this.f2043t != null) {
            printWriter.print(str);
            printWriter.print("  mParent=");
            printWriter.println(this.f2043t);
        }
        printWriter.print(str);
        printWriter.print("  mCurState=");
        printWriter.print(this.f2040q);
        printWriter.print(" mStateSaved=");
        printWriter.print(this.f2046w);
        printWriter.print(" mStopped=");
        printWriter.print(this.f2047x);
        printWriter.print(" mDestroyed=");
        printWriter.println(this.f2048y);
        if (this.f2045v) {
            printWriter.print(str);
            printWriter.print("  mNeedMenuInvalidate=");
            printWriter.println(this.f2045v);
        }
    }

    /* renamed from: b0 */
    public void m1915b0(Menu menu) {
        if (this.f2040q < 1) {
            return;
        }
        for (int i9 = 0; i9 < this.f2030g.size(); i9++) {
            Fragment fragment = this.f2030g.get(i9);
            if (fragment != null) {
                fragment.performOptionsMenuClosed(menu);
            }
        }
    }

    /* renamed from: b1 */
    public final void m1916b1(ArrayList<C0362a> arrayList, ArrayList<Boolean> arrayList2) throws Resources.NotFoundException {
        if (arrayList == null || arrayList.isEmpty()) {
            return;
        }
        if (arrayList2 == null || arrayList.size() != arrayList2.size()) {
            throw new IllegalStateException("Internal error with the back stack records");
        }
        m1955u0(arrayList, arrayList2);
        int size = arrayList.size();
        int i9 = 0;
        int i10 = 0;
        while (i9 < size) {
            if (!arrayList.get(i9).f2104q) {
                if (i10 != i9) {
                    m1953t0(arrayList, arrayList2, i10, i9);
                }
                i10 = i9 + 1;
                if (arrayList2.get(i9).booleanValue()) {
                    while (i10 < size && arrayList2.get(i10).booleanValue() && !arrayList.get(i10).f2104q) {
                        i10++;
                    }
                }
                m1953t0(arrayList, arrayList2, i9, i10);
                i9 = i10 - 1;
            }
            i9++;
        }
        if (i10 != size) {
            m1953t0(arrayList, arrayList2, i10, size);
        }
    }

    @Override // androidx.fragment.app.AbstractC0368g
    /* renamed from: c */
    public boolean mo1846c() throws Resources.NotFoundException {
        boolean zM1947q0 = m1947q0();
        m1961x0();
        return zM1947q0;
    }

    /* renamed from: c0 */
    public final void m1917c0(Fragment fragment) {
        if (fragment == null || this.f2031h.get(fragment.mWho) != fragment) {
            return;
        }
        fragment.performPrimaryNavigationFragmentChanged();
    }

    /* renamed from: c1 */
    public void m1918c1(Fragment fragment) {
        if (m1884K0()) {
            if (f2016I) {
                Log.v("FragmentManager", "Ignoring removeRetainedFragment as the state is already saved");
            }
        } else if (this.f2025G.m1977k(fragment) && f2016I) {
            Log.v("FragmentManager", "Updating retained Fragments: Removed " + fragment);
        }
    }

    @Override // androidx.fragment.app.AbstractC0368g
    /* renamed from: d */
    public Fragment mo1847d(int i9) {
        for (int size = this.f2030g.size() - 1; size >= 0; size--) {
            Fragment fragment = this.f2030g.get(size);
            if (fragment != null && fragment.mFragmentId == i9) {
                return fragment;
            }
        }
        for (Fragment fragment2 : this.f2031h.values()) {
            if (fragment2 != null && fragment2.mFragmentId == i9) {
                return fragment2;
            }
        }
        return null;
    }

    /* renamed from: d0 */
    public void m1919d0() {
        m1930j0(3);
    }

    /* renamed from: d1 */
    public void m1920d1() {
        if (this.f2038o != null) {
            for (int i9 = 0; i9 < this.f2038o.size(); i9++) {
                this.f2038o.get(i9).onBackStackChanged();
            }
        }
    }

    @Override // androidx.fragment.app.AbstractC0368g
    /* renamed from: e */
    public Fragment mo1848e(String str) {
        if (str != null) {
            for (int size = this.f2030g.size() - 1; size >= 0; size--) {
                Fragment fragment = this.f2030g.get(size);
                if (fragment != null && str.equals(fragment.mTag)) {
                    return fragment;
                }
            }
        }
        if (str == null) {
            return null;
        }
        for (Fragment fragment2 : this.f2031h.values()) {
            if (fragment2 != null && str.equals(fragment2.mTag)) {
                return fragment2;
            }
        }
        return null;
    }

    /* renamed from: e0 */
    public void m1921e0(boolean z8) {
        for (int size = this.f2030g.size() - 1; size >= 0; size--) {
            Fragment fragment = this.f2030g.get(size);
            if (fragment != null) {
                fragment.performPictureInPictureModeChanged(z8);
            }
        }
    }

    /* renamed from: e1 */
    public void m1922e1(Parcelable parcelable) {
        FragmentState next;
        if (parcelable == null) {
            return;
        }
        FragmentManagerState fragmentManagerState = (FragmentManagerState) parcelable;
        if (fragmentManagerState.f1983b == null) {
            return;
        }
        for (Fragment fragment : this.f2025G.m1974h()) {
            if (f2016I) {
                Log.v("FragmentManager", "restoreSaveState: re-attaching retained " + fragment);
            }
            Iterator<FragmentState> it = fragmentManagerState.f1983b.iterator();
            while (true) {
                if (it.hasNext()) {
                    next = it.next();
                    if (next.f1989c.equals(fragment.mWho)) {
                        break;
                    }
                } else {
                    next = null;
                    break;
                }
            }
            if (next == null) {
                if (f2016I) {
                    Log.v("FragmentManager", "Discarding retained Fragment " + fragment + " that was not found in the set of active Fragments " + fragmentManagerState.f1983b);
                }
                m1902U0(fragment, 1, 0, 0, false);
                fragment.mRemoving = true;
                m1902U0(fragment, 0, 0, 0, false);
            } else {
                next.f2001o = fragment;
                fragment.mSavedViewState = null;
                fragment.mBackStackNesting = 0;
                fragment.mInLayout = false;
                fragment.mAdded = false;
                Fragment fragment2 = fragment.mTarget;
                fragment.mTargetWho = fragment2 != null ? fragment2.mWho : null;
                fragment.mTarget = null;
                Bundle bundle = next.f2000n;
                if (bundle != null) {
                    bundle.setClassLoader(this.f2041r.m1842e().getClassLoader());
                    fragment.mSavedViewState = next.f2000n.getSparseParcelableArray("android:view_state");
                    fragment.mSavedFragmentState = next.f2000n;
                }
            }
        }
        this.f2031h.clear();
        Iterator<FragmentState> it2 = fragmentManagerState.f1983b.iterator();
        while (it2.hasNext()) {
            FragmentState next2 = it2.next();
            if (next2 != null) {
                Fragment fragmentM1780a = next2.m1780a(this.f2041r.m1842e().getClassLoader(), mo1851h());
                fragmentM1780a.mFragmentManager = this;
                if (f2016I) {
                    Log.v("FragmentManager", "restoreSaveState: active (" + fragmentM1780a.mWho + "): " + fragmentM1780a);
                }
                this.f2031h.put(fragmentM1780a.mWho, fragmentM1780a);
                next2.f2001o = null;
            }
        }
        this.f2030g.clear();
        ArrayList<String> arrayList = fragmentManagerState.f1984c;
        if (arrayList != null) {
            Iterator<String> it3 = arrayList.iterator();
            while (it3.hasNext()) {
                String next3 = it3.next();
                Fragment fragment3 = this.f2031h.get(next3);
                if (fragment3 == null) {
                    m1945p1(new IllegalStateException("No instantiated fragment for (" + next3 + ")"));
                }
                fragment3.mAdded = true;
                if (f2016I) {
                    Log.v("FragmentManager", "restoreSaveState: added (" + next3 + "): " + fragment3);
                }
                if (this.f2030g.contains(fragment3)) {
                    throw new IllegalStateException("Already added " + fragment3);
                }
                synchronized (this.f2030g) {
                    this.f2030g.add(fragment3);
                }
            }
        }
        if (fragmentManagerState.f1985d != null) {
            this.f2032i = new ArrayList<>(fragmentManagerState.f1985d.length);
            int i9 = 0;
            while (true) {
                BackStackState[] backStackStateArr = fragmentManagerState.f1985d;
                if (i9 >= backStackStateArr.length) {
                    break;
                }
                C0362a c0362aM1755a = backStackStateArr[i9].m1755a(this);
                if (f2016I) {
                    Log.v("FragmentManager", "restoreAllState: back stack #" + i9 + " (index " + c0362aM1755a.f2005u + "): " + c0362aM1755a);
                    PrintWriter printWriter = new PrintWriter(new C0696b("FragmentManager"));
                    c0362aM1755a.m1806v("  ", printWriter, false);
                    printWriter.close();
                }
                this.f2032i.add(c0362aM1755a);
                int i10 = c0362aM1755a.f2005u;
                if (i10 >= 0) {
                    m1933k1(i10, c0362aM1755a);
                }
                i9++;
            }
        } else {
            this.f2032i = null;
        }
        String str = fragmentManagerState.f1986e;
        if (str != null) {
            Fragment fragment4 = this.f2031h.get(str);
            this.f2044u = fragment4;
            m1917c0(fragment4);
        }
        this.f2029f = fragmentManagerState.f1987f;
    }

    @Override // androidx.fragment.app.AbstractC0368g
    /* renamed from: f */
    public int mo1849f() {
        ArrayList<C0362a> arrayList = this.f2032i;
        if (arrayList != null) {
            return arrayList.size();
        }
        return 0;
    }

    /* renamed from: f0 */
    public boolean m1923f0(Menu menu) {
        if (this.f2040q < 1) {
            return false;
        }
        boolean z8 = false;
        for (int i9 = 0; i9 < this.f2030g.size(); i9++) {
            Fragment fragment = this.f2030g.get(i9);
            if (fragment != null && fragment.performPrepareOptionsMenu(menu)) {
                z8 = true;
            }
        }
        return z8;
    }

    @Override // androidx.fragment.app.AbstractC0368g
    /* renamed from: g */
    public Fragment mo1850g(Bundle bundle, String str) {
        String string = bundle.getString(str);
        if (string == null) {
            return null;
        }
        Fragment fragment = this.f2031h.get(string);
        if (fragment == null) {
            m1945p1(new IllegalStateException("Fragment no longer exists for key " + str + ": unique id " + string));
        }
        return fragment;
    }

    /* renamed from: g0 */
    public void m1924g0() {
        m1950r1();
        m1917c0(this.f2044u);
    }

    /* renamed from: g1 */
    public Parcelable m1925g1() {
        ArrayList<String> arrayList;
        int size;
        m1961x0();
        m1936m0();
        m1947q0();
        this.f2046w = true;
        BackStackState[] backStackStateArr = null;
        if (this.f2031h.isEmpty()) {
            return null;
        }
        ArrayList<FragmentState> arrayList2 = new ArrayList<>(this.f2031h.size());
        boolean z8 = false;
        for (Fragment fragment : this.f2031h.values()) {
            if (fragment != null) {
                if (fragment.mFragmentManager != this) {
                    m1945p1(new IllegalStateException("Failure saving state: active " + fragment + " was removed from the FragmentManager"));
                }
                FragmentState fragmentState = new FragmentState(fragment);
                arrayList2.add(fragmentState);
                if (fragment.mState <= 0 || fragmentState.f2000n != null) {
                    fragmentState.f2000n = fragment.mSavedFragmentState;
                } else {
                    fragmentState.f2000n = m1927h1(fragment);
                    String str = fragment.mTargetWho;
                    if (str != null) {
                        Fragment fragment2 = this.f2031h.get(str);
                        if (fragment2 == null) {
                            m1945p1(new IllegalStateException("Failure saving state: " + fragment + " has target not in fragment manager: " + fragment.mTargetWho));
                        }
                        if (fragmentState.f2000n == null) {
                            fragmentState.f2000n = new Bundle();
                        }
                        mo1855l(fragmentState.f2000n, "android:target_state", fragment2);
                        int i9 = fragment.mTargetRequestCode;
                        if (i9 != 0) {
                            fragmentState.f2000n.putInt("android:target_req_state", i9);
                        }
                    }
                }
                if (f2016I) {
                    Log.v("FragmentManager", "Saved state of " + fragment + ": " + fragmentState.f2000n);
                }
                z8 = true;
            }
        }
        if (!z8) {
            if (f2016I) {
                Log.v("FragmentManager", "saveAllState: no fragments!");
            }
            return null;
        }
        int size2 = this.f2030g.size();
        if (size2 > 0) {
            arrayList = new ArrayList<>(size2);
            Iterator<Fragment> it = this.f2030g.iterator();
            while (it.hasNext()) {
                Fragment next = it.next();
                arrayList.add(next.mWho);
                if (next.mFragmentManager != this) {
                    m1945p1(new IllegalStateException("Failure saving state: active " + next + " was removed from the FragmentManager"));
                }
                if (f2016I) {
                    Log.v("FragmentManager", "saveAllState: adding fragment (" + next.mWho + "): " + next);
                }
            }
        } else {
            arrayList = null;
        }
        ArrayList<C0362a> arrayList3 = this.f2032i;
        if (arrayList3 != null && (size = arrayList3.size()) > 0) {
            backStackStateArr = new BackStackState[size];
            for (int i10 = 0; i10 < size; i10++) {
                backStackStateArr[i10] = new BackStackState(this.f2032i.get(i10));
                if (f2016I) {
                    Log.v("FragmentManager", "saveAllState: adding back stack #" + i10 + ": " + this.f2032i.get(i10));
                }
            }
        }
        FragmentManagerState fragmentManagerState = new FragmentManagerState();
        fragmentManagerState.f1983b = arrayList2;
        fragmentManagerState.f1984c = arrayList;
        fragmentManagerState.f1985d = backStackStateArr;
        Fragment fragment3 = this.f2044u;
        if (fragment3 != null) {
            fragmentManagerState.f1986e = fragment3.mWho;
        }
        fragmentManagerState.f1987f = this.f2029f;
        return fragmentManagerState;
    }

    @Override // androidx.fragment.app.AbstractC0368g
    /* renamed from: h */
    public C0366e mo1851h() {
        if (super.mo1851h() == AbstractC0368g.f2014c) {
            Fragment fragment = this.f2043t;
            if (fragment != null) {
                return fragment.mFragmentManager.mo1851h();
            }
            m1857n(new f());
        }
        return super.mo1851h();
    }

    /* renamed from: h0 */
    public void m1926h0() {
        this.f2046w = false;
        this.f2047x = false;
        m1930j0(4);
    }

    /* renamed from: h1 */
    public Bundle m1927h1(Fragment fragment) {
        if (this.f2022D == null) {
            this.f2022D = new Bundle();
        }
        fragment.performSaveInstanceState(this.f2022D);
        m1903V(fragment, this.f2022D, false);
        Bundle bundle = null;
        if (!this.f2022D.isEmpty()) {
            Bundle bundle2 = this.f2022D;
            this.f2022D = null;
            bundle = bundle2;
        }
        if (fragment.mView != null) {
            m1929i1(fragment);
        }
        if (fragment.mSavedViewState != null) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            bundle.putSparseParcelableArray("android:view_state", fragment.mSavedViewState);
        }
        if (!fragment.mUserVisibleHint) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            bundle.putBoolean("android:user_visible_hint", fragment.mUserVisibleHint);
        }
        return bundle;
    }

    @Override // androidx.fragment.app.AbstractC0368g
    /* renamed from: i */
    public List<Fragment> mo1852i() {
        List<Fragment> list;
        if (this.f2030g.isEmpty()) {
            return Collections.emptyList();
        }
        synchronized (this.f2030g) {
            list = (List) this.f2030g.clone();
        }
        return list;
    }

    /* renamed from: i0 */
    public void m1928i0() {
        this.f2046w = false;
        this.f2047x = false;
        m1930j0(3);
    }

    /* renamed from: i1 */
    public void m1929i1(Fragment fragment) {
        if (fragment.mInnerView == null) {
            return;
        }
        SparseArray<Parcelable> sparseArray = this.f2023E;
        if (sparseArray == null) {
            this.f2023E = new SparseArray<>();
        } else {
            sparseArray.clear();
        }
        fragment.mInnerView.saveHierarchyState(this.f2023E);
        if (this.f2023E.size() > 0) {
            fragment.mSavedViewState = this.f2023E;
            this.f2023E = null;
        }
    }

    @Override // androidx.fragment.app.AbstractC0368g
    /* renamed from: j */
    public void mo1853j(int i9, int i10) {
        if (i9 >= 0) {
            m1938n0(new l(null, i9, i10), false);
            return;
        }
        throw new IllegalArgumentException("Bad id: " + i9);
    }

    /* renamed from: j0 */
    public final void m1930j0(int i9) {
        try {
            this.f2028e = true;
            m1898S0(i9, false);
            this.f2028e = false;
            m1947q0();
        } catch (Throwable th) {
            this.f2028e = false;
            throw th;
        }
    }

    /* renamed from: j1 */
    public void m1931j1() {
        synchronized (this) {
            ArrayList<m> arrayList = this.f2024F;
            boolean z8 = false;
            boolean z9 = (arrayList == null || arrayList.isEmpty()) ? false : true;
            ArrayList<k> arrayList2 = this.f2027d;
            if (arrayList2 != null && arrayList2.size() == 1) {
                z8 = true;
            }
            if (z9 || z8) {
                this.f2041r.m1843f().removeCallbacks(this.f2026H);
                this.f2041r.m1843f().post(this.f2026H);
                m1950r1();
            }
        }
    }

    @Override // androidx.fragment.app.AbstractC0368g
    /* renamed from: k */
    public boolean mo1854k() {
        m1962y();
        return m1908X0(null, -1, 0);
    }

    /* renamed from: k0 */
    public void m1932k0() {
        this.f2047x = true;
        m1930j0(2);
    }

    /* renamed from: k1 */
    public void m1933k1(int i9, C0362a c0362a) {
        synchronized (this) {
            if (this.f2036m == null) {
                this.f2036m = new ArrayList<>();
            }
            int size = this.f2036m.size();
            if (i9 < size) {
                if (f2016I) {
                    Log.v("FragmentManager", "Setting back stack index " + i9 + " to " + c0362a);
                }
                this.f2036m.set(i9, c0362a);
            } else {
                while (size < i9) {
                    this.f2036m.add(null);
                    if (this.f2037n == null) {
                        this.f2037n = new ArrayList<>();
                    }
                    if (f2016I) {
                        Log.v("FragmentManager", "Adding available back stack index " + size);
                    }
                    this.f2037n.add(Integer.valueOf(size));
                    size++;
                }
                if (f2016I) {
                    Log.v("FragmentManager", "Adding back stack index " + i9 + " with " + c0362a);
                }
                this.f2036m.add(c0362a);
            }
        }
    }

    @Override // androidx.fragment.app.AbstractC0368g
    /* renamed from: l */
    public void mo1855l(Bundle bundle, String str, Fragment fragment) {
        if (fragment.mFragmentManager != this) {
            m1945p1(new IllegalStateException("Fragment " + fragment + " is not currently in the FragmentManager"));
        }
        bundle.putString(str, fragment.mWho);
    }

    /* renamed from: l0 */
    public void m1934l0() {
        if (this.f2049z) {
            this.f2049z = false;
            m1942o1();
        }
    }

    /* renamed from: l1 */
    public void m1935l1(Fragment fragment, Lifecycle.State state) {
        if (this.f2031h.get(fragment.mWho) == fragment && (fragment.mHost == null || fragment.getFragmentManager() == this)) {
            fragment.mMaxState = state;
            return;
        }
        throw new IllegalArgumentException("Fragment " + fragment + " is not an active fragment of FragmentManager " + this);
    }

    @Override // androidx.fragment.app.AbstractC0368g
    /* renamed from: m */
    public Fragment.SavedState mo1856m(Fragment fragment) {
        Bundle bundleM1927h1;
        if (fragment.mFragmentManager != this) {
            m1945p1(new IllegalStateException("Fragment " + fragment + " is not currently in the FragmentManager"));
        }
        if (fragment.mState <= 0 || (bundleM1927h1 = m1927h1(fragment)) == null) {
            return null;
        }
        return new Fragment.SavedState(bundleM1927h1);
    }

    /* renamed from: m0 */
    public final void m1936m0() throws Resources.NotFoundException {
        for (Fragment fragment : this.f2031h.values()) {
            if (fragment != null) {
                if (fragment.getAnimatingAway() != null) {
                    int stateAfterAnimating = fragment.getStateAfterAnimating();
                    View animatingAway = fragment.getAnimatingAway();
                    Animation animation = animatingAway.getAnimation();
                    if (animation != null) {
                        animation.cancel();
                        animatingAway.clearAnimation();
                    }
                    fragment.setAnimatingAway(null);
                    m1902U0(fragment, stateAfterAnimating, 0, 0, false);
                } else if (fragment.getAnimator() != null) {
                    fragment.getAnimator().end();
                }
            }
        }
    }

    /* renamed from: m1 */
    public void m1937m1(Fragment fragment) {
        if (fragment == null || (this.f2031h.get(fragment.mWho) == fragment && (fragment.mHost == null || fragment.getFragmentManager() == this))) {
            Fragment fragment2 = this.f2044u;
            this.f2044u = fragment;
            m1917c0(fragment2);
            m1917c0(this.f2044u);
            return;
        }
        throw new IllegalArgumentException("Fragment " + fragment + " is not an active fragment of FragmentManager " + this);
    }

    /* renamed from: n0 */
    public void m1938n0(k kVar, boolean z8) {
        if (!z8) {
            m1962y();
        }
        synchronized (this) {
            if (!this.f2048y && this.f2041r != null) {
                if (this.f2027d == null) {
                    this.f2027d = new ArrayList<>();
                }
                this.f2027d.add(kVar);
                m1931j1();
                return;
            }
            if (!z8) {
                throw new IllegalStateException("Activity has been destroyed");
            }
        }
    }

    /* renamed from: n1 */
    public void m1939n1(Fragment fragment) {
        if (f2016I) {
            Log.v("FragmentManager", "show: " + fragment);
        }
        if (fragment.mHidden) {
            fragment.mHidden = false;
            fragment.mHiddenChanged = !fragment.mHiddenChanged;
        }
    }

    /* renamed from: o */
    public final void m1940o(C5303b<Fragment> c5303b) throws Resources.NotFoundException {
        int i9 = this.f2040q;
        if (i9 < 1) {
            return;
        }
        int iMin = Math.min(i9, 3);
        int size = this.f2030g.size();
        for (int i10 = 0; i10 < size; i10++) {
            Fragment fragment = this.f2030g.get(i10);
            if (fragment.mState < iMin) {
                m1902U0(fragment, iMin, fragment.getNextAnim(), fragment.getNextTransition(), false);
                if (fragment.mView != null && !fragment.mHidden && fragment.mIsNewlyAdded) {
                    c5303b.add(fragment);
                }
            }
        }
    }

    /* renamed from: o0 */
    public final void m1941o0(boolean z8) {
        if (this.f2028e) {
            throw new IllegalStateException("FragmentManager is already executing transactions");
        }
        if (this.f2041r == null) {
            throw new IllegalStateException("Fragment host has been destroyed");
        }
        if (Looper.myLooper() != this.f2041r.m1843f().getLooper()) {
            throw new IllegalStateException("Must be called from main thread of fragment host");
        }
        if (!z8) {
            m1962y();
        }
        if (this.f2019A == null) {
            this.f2019A = new ArrayList<>();
            this.f2020B = new ArrayList<>();
        }
        this.f2028e = true;
        try {
            m1955u0(null, null);
        } finally {
            this.f2028e = false;
        }
    }

    /* renamed from: o1 */
    public void m1942o1() {
        for (Fragment fragment : this.f2031h.values()) {
            if (fragment != null) {
                m1906W0(fragment);
            }
        }
    }

    @Override // android.view.LayoutInflater.Factory2
    public View onCreateView(View view, String str, Context context, AttributeSet attributeSet) throws Resources.NotFoundException {
        if (!"fragment".equals(str)) {
            return null;
        }
        String attributeValue = attributeSet.getAttributeValue(null, "class");
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, j.f2073a);
        if (attributeValue == null) {
            attributeValue = typedArrayObtainStyledAttributes.getString(0);
        }
        String str2 = attributeValue;
        int resourceId = typedArrayObtainStyledAttributes.getResourceId(1, -1);
        String string = typedArrayObtainStyledAttributes.getString(2);
        typedArrayObtainStyledAttributes.recycle();
        if (str2 == null || !C0366e.m1837b(context.getClassLoader(), str2)) {
            return null;
        }
        int id = view != null ? view.getId() : 0;
        if (id == -1 && resourceId == -1 && string == null) {
            throw new IllegalArgumentException(attributeSet.getPositionDescription() + ": Must specify unique android:id, android:tag, or have a parent with an id for " + str2);
        }
        Fragment fragmentMo1847d = resourceId != -1 ? mo1847d(resourceId) : null;
        if (fragmentMo1847d == null && string != null) {
            fragmentMo1847d = mo1848e(string);
        }
        if (fragmentMo1847d == null && id != -1) {
            fragmentMo1847d = mo1847d(id);
        }
        if (f2016I) {
            Log.v("FragmentManager", "onCreateView: id=0x" + Integer.toHexString(resourceId) + " fname=" + str2 + " existing=" + fragmentMo1847d);
        }
        if (fragmentMo1847d == null) {
            fragmentMo1847d = mo1851h().mo1840a(context.getClassLoader(), str2);
            fragmentMo1847d.mFromLayout = true;
            fragmentMo1847d.mFragmentId = resourceId != 0 ? resourceId : id;
            fragmentMo1847d.mContainerId = id;
            fragmentMo1847d.mTag = string;
            fragmentMo1847d.mInLayout = true;
            fragmentMo1847d.mFragmentManager = this;
            AbstractC0367f abstractC0367f = this.f2041r;
            fragmentMo1847d.mHost = abstractC0367f;
            fragmentMo1847d.onInflate(abstractC0367f.m1842e(), attributeSet, fragmentMo1847d.mSavedFragmentState);
            m1946q(fragmentMo1847d, true);
        } else {
            if (fragmentMo1847d.mInLayout) {
                throw new IllegalArgumentException(attributeSet.getPositionDescription() + ": Duplicate id 0x" + Integer.toHexString(resourceId) + ", tag " + string + ", or parent id 0x" + Integer.toHexString(id) + " with another fragment for " + str2);
            }
            fragmentMo1847d.mInLayout = true;
            AbstractC0367f abstractC0367f2 = this.f2041r;
            fragmentMo1847d.mHost = abstractC0367f2;
            fragmentMo1847d.onInflate(abstractC0367f2.m1842e(), attributeSet, fragmentMo1847d.mSavedFragmentState);
        }
        Fragment fragment = fragmentMo1847d;
        if (this.f2040q >= 1 || !fragment.mFromLayout) {
            m1900T0(fragment);
        } else {
            m1902U0(fragment, 1, 0, 0, false);
        }
        View view2 = fragment.mView;
        if (view2 != null) {
            if (resourceId != 0) {
                view2.setId(resourceId);
            }
            if (fragment.mView.getTag() == null) {
                fragment.mView.setTag(string);
            }
            return fragment.mView;
        }
        throw new IllegalStateException("Fragment " + str2 + " did not create a view.");
    }

    /* renamed from: p */
    public void m1943p(C0362a c0362a) {
        if (this.f2032i == null) {
            this.f2032i = new ArrayList<>();
        }
        this.f2032i.add(c0362a);
    }

    /* renamed from: p0 */
    public void m1944p0(Fragment fragment) {
        if (!fragment.mFromLayout || fragment.mPerformedCreateView) {
            return;
        }
        fragment.performCreateView(fragment.performGetLayoutInflater(fragment.mSavedFragmentState), null, fragment.mSavedFragmentState);
        View view = fragment.mView;
        if (view == null) {
            fragment.mInnerView = null;
            return;
        }
        fragment.mInnerView = view;
        view.setSaveFromParentEnabled(false);
        if (fragment.mHidden) {
            fragment.mView.setVisibility(8);
        }
        fragment.onViewCreated(fragment.mView, fragment.mSavedFragmentState);
        m1909Y(fragment, fragment.mView, fragment.mSavedFragmentState, false);
    }

    /* renamed from: p1 */
    public final void m1945p1(RuntimeException runtimeException) {
        Log.e("FragmentManager", runtimeException.getMessage());
        Log.e("FragmentManager", "Activity state:");
        PrintWriter printWriter = new PrintWriter(new C0696b("FragmentManager"));
        AbstractC0367f abstractC0367f = this.f2041r;
        if (abstractC0367f != null) {
            try {
                abstractC0367f.mo1766h("  ", null, printWriter, new String[0]);
                throw runtimeException;
            } catch (Exception e9) {
                Log.e("FragmentManager", "Failed dumping state", e9);
                throw runtimeException;
            }
        }
        try {
            mo1845b("  ", null, printWriter, new String[0]);
            throw runtimeException;
        } catch (Exception e10) {
            Log.e("FragmentManager", "Failed dumping state", e10);
            throw runtimeException;
        }
    }

    /* renamed from: q */
    public void m1946q(Fragment fragment, boolean z8) throws Resources.NotFoundException {
        if (f2016I) {
            Log.v("FragmentManager", "add: " + fragment);
        }
        m1888M0(fragment);
        if (fragment.mDetached) {
            return;
        }
        if (this.f2030g.contains(fragment)) {
            throw new IllegalStateException("Fragment already added: " + fragment);
        }
        synchronized (this.f2030g) {
            this.f2030g.add(fragment);
        }
        fragment.mAdded = true;
        fragment.mRemoving = false;
        if (fragment.mView == null) {
            fragment.mHiddenChanged = false;
        }
        if (m1878H0(fragment)) {
            this.f2045v = true;
        }
        if (z8) {
            m1900T0(fragment);
        }
    }

    /* renamed from: q0 */
    public boolean m1947q0() {
        m1941o0(true);
        boolean z8 = false;
        while (m1965z0(this.f2019A, this.f2020B)) {
            this.f2028e = true;
            try {
                m1916b1(this.f2019A, this.f2020B);
                m1964z();
                z8 = true;
            } catch (Throwable th) {
                m1964z();
                throw th;
            }
        }
        m1950r1();
        m1934l0();
        m1958w();
        return z8;
    }

    /* renamed from: r */
    public void m1948r(Fragment fragment) {
        if (m1884K0()) {
            if (f2016I) {
                Log.v("FragmentManager", "Ignoring addRetainedFragment as the state is already saved");
            }
        } else if (this.f2025G.m1971d(fragment) && f2016I) {
            Log.v("FragmentManager", "Updating retained Fragments: Added " + fragment);
        }
    }

    /* renamed from: r0 */
    public void m1949r0(k kVar, boolean z8) {
        if (z8 && (this.f2041r == null || this.f2048y)) {
            return;
        }
        m1941o0(z8);
        if (kVar.mo1792a(this.f2019A, this.f2020B)) {
            this.f2028e = true;
            try {
                m1916b1(this.f2019A, this.f2020B);
            } finally {
                m1964z();
            }
        }
        m1950r1();
        m1934l0();
        m1958w();
    }

    /* renamed from: r1 */
    public final void m1950r1() {
        ArrayList<k> arrayList = this.f2027d;
        if (arrayList == null || arrayList.isEmpty()) {
            this.f2035l.m219f(mo1849f() > 0 && m1880I0(this.f2043t));
        } else {
            this.f2035l.m219f(true);
        }
    }

    /* renamed from: s */
    public int m1951s(C0362a c0362a) {
        synchronized (this) {
            ArrayList<Integer> arrayList = this.f2037n;
            if (arrayList != null && arrayList.size() > 0) {
                int iIntValue = this.f2037n.remove(r0.size() - 1).intValue();
                if (f2016I) {
                    Log.v("FragmentManager", "Adding back stack index " + iIntValue + " with " + c0362a);
                }
                this.f2036m.set(iIntValue, c0362a);
                return iIntValue;
            }
            if (this.f2036m == null) {
                this.f2036m = new ArrayList<>();
            }
            int size = this.f2036m.size();
            if (f2016I) {
                Log.v("FragmentManager", "Setting back stack index " + size + " to " + c0362a);
            }
            this.f2036m.add(c0362a);
            return size;
        }
    }

    /* renamed from: t */
    public final void m1952t(Fragment fragment, g gVar, int i9) {
        View view = fragment.mView;
        ViewGroup viewGroup = fragment.mContainer;
        viewGroup.startViewTransition(view);
        fragment.setStateAfterAnimating(i9);
        if (gVar.f2065a != null) {
            h hVar = new h(gVar.f2065a, viewGroup, view);
            fragment.setAnimatingAway(fragment.mView);
            hVar.setAnimationListener(new c(viewGroup, fragment));
            fragment.mView.startAnimation(hVar);
            return;
        }
        Animator animator = gVar.f2066b;
        fragment.setAnimator(animator);
        animator.addListener(new d(viewGroup, view, fragment));
        animator.setTarget(fragment.mView);
        animator.start();
    }

    /* renamed from: t0 */
    public final void m1953t0(ArrayList<C0362a> arrayList, ArrayList<Boolean> arrayList2, int i9, int i10) throws Resources.NotFoundException {
        int i11;
        int i12;
        int i13 = i9;
        boolean z8 = arrayList.get(i13).f2104q;
        ArrayList<Fragment> arrayList3 = this.f2021C;
        if (arrayList3 == null) {
            this.f2021C = new ArrayList<>();
        } else {
            arrayList3.clear();
        }
        this.f2021C.addAll(this.f2030g);
        Fragment fragmentM1868C0 = m1868C0();
        boolean z9 = false;
        for (int i14 = i13; i14 < i10; i14++) {
            C0362a c0362a = arrayList.get(i14);
            fragmentM1868C0 = !arrayList2.get(i14).booleanValue() ? c0362a.m1809y(this.f2021C, fragmentM1868C0) : c0362a.m1791G(this.f2021C, fragmentM1868C0);
            z9 = z9 || c0362a.f2095h;
        }
        this.f2021C.clear();
        if (!z8) {
            C0373l.m1988B(this, arrayList, arrayList2, i9, i10, false);
        }
        m1862s0(arrayList, arrayList2, i9, i10);
        if (z8) {
            C5303b<Fragment> c5303b = new C5303b<>();
            m1940o(c5303b);
            int iM1912Z0 = m1912Z0(arrayList, arrayList2, i9, i10, c5303b);
            m1894Q0(c5303b);
            i11 = iM1912Z0;
        } else {
            i11 = i10;
        }
        if (i11 != i13 && z8) {
            C0373l.m1988B(this, arrayList, arrayList2, i9, i11, true);
            m1898S0(this.f2040q, true);
        }
        while (i13 < i10) {
            C0362a c0362a2 = arrayList.get(i13);
            if (arrayList2.get(i13).booleanValue() && (i12 = c0362a2.f2005u) >= 0) {
                m1963y0(i12);
                c0362a2.f2005u = -1;
            }
            c0362a2.m1789E();
            i13++;
        }
        if (z9) {
            m1920d1();
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("FragmentManager{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append(" in ");
        Fragment fragment = this.f2043t;
        if (fragment != null) {
            C0695a.m3459a(fragment, sb);
        } else {
            C0695a.m3459a(this.f2041r, sb);
        }
        sb.append("}}");
        return sb.toString();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: u */
    public void m1954u(AbstractC0367f abstractC0367f, AbstractC0364c abstractC0364c, Fragment fragment) {
        if (this.f2041r != null) {
            throw new IllegalStateException("Already attached");
        }
        this.f2041r = abstractC0367f;
        this.f2042s = abstractC0364c;
        this.f2043t = fragment;
        if (fragment != null) {
            m1950r1();
        }
        if (abstractC0367f instanceof InterfaceC0096c) {
            InterfaceC0096c interfaceC0096c = (InterfaceC0096c) abstractC0367f;
            OnBackPressedDispatcher onBackPressedDispatcher = interfaceC0096c.getOnBackPressedDispatcher();
            this.f2034k = onBackPressedDispatcher;
            InterfaceC0385f interfaceC0385f = interfaceC0096c;
            if (fragment != null) {
                interfaceC0385f = fragment;
            }
            onBackPressedDispatcher.m211a(interfaceC0385f, this.f2035l);
        }
        if (fragment != null) {
            this.f2025G = fragment.mFragmentManager.m1864A0(fragment);
        } else if (abstractC0367f instanceof InterfaceC0397r) {
            this.f2025G = C0370i.m1969g(((InterfaceC0397r) abstractC0367f).getViewModelStore());
        } else {
            this.f2025G = new C0370i(false);
        }
    }

    /* renamed from: u0 */
    public final void m1955u0(ArrayList<C0362a> arrayList, ArrayList<Boolean> arrayList2) throws Resources.NotFoundException {
        int iIndexOf;
        int iIndexOf2;
        ArrayList<m> arrayList3 = this.f2024F;
        int size = arrayList3 == null ? 0 : arrayList3.size();
        int i9 = 0;
        while (i9 < size) {
            m mVar = this.f2024F.get(i9);
            if (arrayList != null && !mVar.f2078a && (iIndexOf2 = arrayList.indexOf(mVar.f2079b)) != -1 && arrayList2.get(iIndexOf2).booleanValue()) {
                this.f2024F.remove(i9);
                i9--;
                size--;
                mVar.m1966c();
            } else if (mVar.m1968e() || (arrayList != null && mVar.f2079b.m1787B(arrayList, 0, arrayList.size()))) {
                this.f2024F.remove(i9);
                i9--;
                size--;
                if (arrayList == null || mVar.f2078a || (iIndexOf = arrayList.indexOf(mVar.f2079b)) == -1 || !arrayList2.get(iIndexOf).booleanValue()) {
                    mVar.m1967d();
                } else {
                    mVar.m1966c();
                }
            }
            i9++;
        }
    }

    /* renamed from: v */
    public void m1956v(Fragment fragment) {
        if (f2016I) {
            Log.v("FragmentManager", "attach: " + fragment);
        }
        if (fragment.mDetached) {
            fragment.mDetached = false;
            if (fragment.mAdded) {
                return;
            }
            if (this.f2030g.contains(fragment)) {
                throw new IllegalStateException("Fragment already added: " + fragment);
            }
            if (f2016I) {
                Log.v("FragmentManager", "add from attach: " + fragment);
            }
            synchronized (this.f2030g) {
                this.f2030g.add(fragment);
            }
            fragment.mAdded = true;
            if (m1878H0(fragment)) {
                this.f2045v = true;
            }
        }
    }

    /* renamed from: v0 */
    public Fragment m1957v0(String str) {
        Fragment fragmentFindFragmentByWho;
        for (Fragment fragment : this.f2031h.values()) {
            if (fragment != null && (fragmentFindFragmentByWho = fragment.findFragmentByWho(str)) != null) {
                return fragmentFindFragmentByWho;
            }
        }
        return null;
    }

    /* renamed from: w */
    public final void m1958w() {
        this.f2031h.values().removeAll(Collections.singleton(null));
    }

    /* renamed from: w0 */
    public final Fragment m1959w0(Fragment fragment) {
        ViewGroup viewGroup = fragment.mContainer;
        View view = fragment.mView;
        if (viewGroup != null && view != null) {
            for (int iIndexOf = this.f2030g.indexOf(fragment) - 1; iIndexOf >= 0; iIndexOf--) {
                Fragment fragment2 = this.f2030g.get(iIndexOf);
                if (fragment2.mContainer == viewGroup && fragment2.mView != null) {
                    return fragment2;
                }
            }
        }
        return null;
    }

    /* renamed from: x */
    public boolean m1960x() {
        boolean zM1878H0 = false;
        for (Fragment fragment : this.f2031h.values()) {
            if (fragment != null) {
                zM1878H0 = m1878H0(fragment);
            }
            if (zM1878H0) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: x0 */
    public final void m1961x0() throws Resources.NotFoundException {
        if (this.f2024F != null) {
            while (!this.f2024F.isEmpty()) {
                this.f2024F.remove(0).m1967d();
            }
        }
    }

    /* renamed from: y */
    public final void m1962y() {
        if (m1884K0()) {
            throw new IllegalStateException("Can not perform this action after onSaveInstanceState");
        }
    }

    /* renamed from: y0 */
    public void m1963y0(int i9) {
        synchronized (this) {
            this.f2036m.set(i9, null);
            if (this.f2037n == null) {
                this.f2037n = new ArrayList<>();
            }
            if (f2016I) {
                Log.v("FragmentManager", "Freeing back stack index " + i9);
            }
            this.f2037n.add(Integer.valueOf(i9));
        }
    }

    /* renamed from: z */
    public final void m1964z() {
        this.f2028e = false;
        this.f2020B.clear();
        this.f2019A.clear();
    }

    /* renamed from: z0 */
    public final boolean m1965z0(ArrayList<C0362a> arrayList, ArrayList<Boolean> arrayList2) {
        synchronized (this) {
            ArrayList<k> arrayList3 = this.f2027d;
            if (arrayList3 != null && arrayList3.size() != 0) {
                int size = this.f2027d.size();
                boolean zMo1792a = false;
                for (int i9 = 0; i9 < size; i9++) {
                    zMo1792a |= this.f2027d.get(i9).mo1792a(arrayList, arrayList2);
                }
                this.f2027d.clear();
                this.f2041r.m1843f().removeCallbacks(this.f2026H);
                return zMo1792a;
            }
            return false;
        }
    }

    /* renamed from: androidx.fragment.app.h$g */
    public static class g {

        /* renamed from: a */
        public final Animation f2065a;

        /* renamed from: b */
        public final Animator f2066b;

        public g(Animation animation) {
            this.f2065a = animation;
            this.f2066b = null;
            if (animation == null) {
                throw new IllegalStateException("Animation cannot be null");
            }
        }

        public g(Animator animator) {
            this.f2065a = null;
            this.f2066b = animator;
            if (animator == null) {
                throw new IllegalStateException("Animator cannot be null");
            }
        }
    }

    /* renamed from: androidx.fragment.app.h$h */
    public static class h extends AnimationSet implements Runnable {

        /* renamed from: b */
        public final ViewGroup f2067b;

        /* renamed from: c */
        public final View f2068c;

        /* renamed from: d */
        public boolean f2069d;

        /* renamed from: e */
        public boolean f2070e;

        /* renamed from: f */
        public boolean f2071f;

        public h(Animation animation, ViewGroup viewGroup, View view) {
            super(false);
            this.f2071f = true;
            this.f2067b = viewGroup;
            this.f2068c = view;
            addAnimation(animation);
            viewGroup.post(this);
        }

        @Override // android.view.animation.AnimationSet, android.view.animation.Animation
        public boolean getTransformation(long j9, Transformation transformation) {
            this.f2071f = true;
            if (this.f2069d) {
                return !this.f2070e;
            }
            if (!super.getTransformation(j9, transformation)) {
                this.f2069d = true;
                ViewTreeObserverOnPreDrawListenerC4644r.m18500a(this.f2067b, this);
            }
            return true;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.f2069d || !this.f2071f) {
                this.f2067b.endViewTransition(this.f2068c);
                this.f2070e = true;
            } else {
                this.f2071f = false;
                this.f2067b.post(this);
            }
        }

        @Override // android.view.animation.Animation
        public boolean getTransformation(long j9, Transformation transformation, float f9) {
            this.f2071f = true;
            if (this.f2069d) {
                return !this.f2070e;
            }
            if (!super.getTransformation(j9, transformation, f9)) {
                this.f2069d = true;
                ViewTreeObserverOnPreDrawListenerC4644r.m18500a(this.f2067b, this);
            }
            return true;
        }
    }

    @Override // android.view.LayoutInflater.Factory
    public View onCreateView(String str, Context context, AttributeSet attributeSet) {
        return onCreateView(null, str, context, attributeSet);
    }
}
