package androidx.viewpager.widget;

import android.R;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.FocusFinder;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SoundEffectConstants;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.animation.Interpolator;
import android.widget.EdgeEffect;
import android.widget.Scroller;
import androidx.customview.view.AbsSavedState;
import com.google.android.gms.auth.api.credentials.CredentialsApi;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.firebase.crashlytics.internal.metadata.UserMetadata;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import p042d0.C4613a;
import p042d0.C4628h0;
import p042d0.C4647u;
import p042d0.InterfaceC4643q;
import p052e0.C4704m;
import p189s0.AbstractC6243a;
import p197t.C6273a;

/* loaded from: classes.dex */
public class ViewPager extends ViewGroup {

    /* renamed from: g0 */
    public static final int[] f2980g0 = {R.attr.layout_gravity};

    /* renamed from: h0 */
    public static final Comparator<C0553f> f2981h0 = new C0548a();

    /* renamed from: i0 */
    public static final Interpolator f2982i0 = new InterpolatorC0549b();

    /* renamed from: j0 */
    public static final C0559l f2983j0 = new C0559l();

    /* renamed from: A */
    public boolean f2984A;

    /* renamed from: B */
    public int f2985B;

    /* renamed from: C */
    public int f2986C;

    /* renamed from: D */
    public int f2987D;

    /* renamed from: E */
    public float f2988E;

    /* renamed from: F */
    public float f2989F;

    /* renamed from: G */
    public float f2990G;

    /* renamed from: H */
    public float f2991H;

    /* renamed from: I */
    public int f2992I;

    /* renamed from: J */
    public VelocityTracker f2993J;

    /* renamed from: K */
    public int f2994K;

    /* renamed from: L */
    public int f2995L;

    /* renamed from: M */
    public int f2996M;

    /* renamed from: N */
    public int f2997N;

    /* renamed from: O */
    public boolean f2998O;

    /* renamed from: P */
    public EdgeEffect f2999P;

    /* renamed from: Q */
    public EdgeEffect f3000Q;

    /* renamed from: R */
    public boolean f3001R;

    /* renamed from: S */
    public boolean f3002S;

    /* renamed from: T */
    public boolean f3003T;

    /* renamed from: U */
    public int f3004U;

    /* renamed from: V */
    public List<InterfaceC0557j> f3005V;

    /* renamed from: W */
    public InterfaceC0557j f3006W;

    /* renamed from: a0 */
    public InterfaceC0557j f3007a0;

    /* renamed from: b */
    public int f3008b;

    /* renamed from: b0 */
    public List<InterfaceC0556i> f3009b0;

    /* renamed from: c */
    public final ArrayList<C0553f> f3010c;

    /* renamed from: c0 */
    public int f3011c0;

    /* renamed from: d */
    public final C0553f f3012d;

    /* renamed from: d0 */
    public ArrayList<View> f3013d0;

    /* renamed from: e */
    public final Rect f3014e;

    /* renamed from: e0 */
    public final Runnable f3015e0;

    /* renamed from: f */
    public AbstractC6243a f3016f;

    /* renamed from: f0 */
    public int f3017f0;

    /* renamed from: g */
    public int f3018g;

    /* renamed from: h */
    public int f3019h;

    /* renamed from: i */
    public Parcelable f3020i;

    /* renamed from: j */
    public ClassLoader f3021j;

    /* renamed from: k */
    public Scroller f3022k;

    /* renamed from: l */
    public boolean f3023l;

    /* renamed from: m */
    public C0558k f3024m;

    /* renamed from: n */
    public int f3025n;

    /* renamed from: o */
    public Drawable f3026o;

    /* renamed from: p */
    public int f3027p;

    /* renamed from: q */
    public int f3028q;

    /* renamed from: r */
    public float f3029r;

    /* renamed from: s */
    public float f3030s;

    /* renamed from: t */
    public int f3031t;

    /* renamed from: u */
    public int f3032u;

    /* renamed from: v */
    public boolean f3033v;

    /* renamed from: w */
    public boolean f3034w;

    /* renamed from: x */
    public boolean f3035x;

    /* renamed from: y */
    public int f3036y;

    /* renamed from: z */
    public boolean f3037z;

    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new C0547a();

        /* renamed from: b */
        public int f3038b;

        /* renamed from: c */
        public Parcelable f3039c;

        /* renamed from: d */
        public ClassLoader f3040d;

        /* renamed from: androidx.viewpager.widget.ViewPager$SavedState$a */
        public static class C0547a implements Parcelable.ClassLoaderCreator<SavedState> {
            @Override // android.os.Parcelable.Creator
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel, null);
            }

            @Override // android.os.Parcelable.ClassLoaderCreator
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public SavedState createFromParcel(Parcel parcel, ClassLoader classLoader) {
                return new SavedState(parcel, classLoader);
            }

            @Override // android.os.Parcelable.Creator
            /* renamed from: c, reason: merged with bridge method [inline-methods] */
            public SavedState[] newArray(int i9) {
                return new SavedState[i9];
            }
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        public String toString() {
            return "FragmentPager.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " position=" + this.f3038b + "}";
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i9) {
            super.writeToParcel(parcel, i9);
            parcel.writeInt(this.f3038b);
            parcel.writeParcelable(this.f3039c, i9);
        }

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            classLoader = classLoader == null ? getClass().getClassLoader() : classLoader;
            this.f3038b = parcel.readInt();
            this.f3039c = parcel.readParcelable(classLoader);
            this.f3040d = classLoader;
        }
    }

    /* renamed from: androidx.viewpager.widget.ViewPager$a */
    public static class C0548a implements Comparator<C0553f> {
        @Override // java.util.Comparator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(C0553f c0553f, C0553f c0553f2) {
            return c0553f.f3045b - c0553f2.f3045b;
        }
    }

    /* renamed from: androidx.viewpager.widget.ViewPager$b */
    public static class InterpolatorC0549b implements Interpolator {
        @Override // android.animation.TimeInterpolator
        public float getInterpolation(float f9) {
            float f10 = f9 - 1.0f;
            return (f10 * f10 * f10 * f10 * f10) + 1.0f;
        }
    }

    /* renamed from: androidx.viewpager.widget.ViewPager$c */
    public class RunnableC0550c implements Runnable {
        public RunnableC0550c() {
        }

        @Override // java.lang.Runnable
        public void run() throws Resources.NotFoundException {
            ViewPager.this.setScrollState(0);
            ViewPager.this.m3171D();
        }
    }

    /* renamed from: androidx.viewpager.widget.ViewPager$d */
    public class C0551d implements InterfaceC4643q {

        /* renamed from: a */
        public final Rect f3042a = new Rect();

        public C0551d() {
        }

        @Override // p042d0.InterfaceC4643q
        public C4628h0 onApplyWindowInsets(View view, C4628h0 c4628h0) {
            C4628h0 c4628h0M18520P = C4647u.m18520P(view, c4628h0);
            if (c4628h0M18520P.m18443l()) {
                return c4628h0M18520P;
            }
            Rect rect = this.f3042a;
            rect.left = c4628h0M18520P.m18437e();
            rect.top = c4628h0M18520P.m18439g();
            rect.right = c4628h0M18520P.m18438f();
            rect.bottom = c4628h0M18520P.m18436d();
            int childCount = ViewPager.this.getChildCount();
            for (int i9 = 0; i9 < childCount; i9++) {
                C4628h0 c4628h0M18537d = C4647u.m18537d(ViewPager.this.getChildAt(i9), c4628h0M18520P);
                rect.left = Math.min(c4628h0M18537d.m18437e(), rect.left);
                rect.top = Math.min(c4628h0M18537d.m18439g(), rect.top);
                rect.right = Math.min(c4628h0M18537d.m18438f(), rect.right);
                rect.bottom = Math.min(c4628h0M18537d.m18436d(), rect.bottom);
            }
            return c4628h0M18520P.m18444m(rect.left, rect.top, rect.right, rect.bottom);
        }
    }

    @Target({ElementType.TYPE})
    @Inherited
    @Retention(RetentionPolicy.RUNTIME)
    /* renamed from: androidx.viewpager.widget.ViewPager$e */
    public @interface InterfaceC0552e {
    }

    /* renamed from: androidx.viewpager.widget.ViewPager$f */
    public static class C0553f {

        /* renamed from: a */
        public Object f3044a;

        /* renamed from: b */
        public int f3045b;

        /* renamed from: c */
        public boolean f3046c;

        /* renamed from: d */
        public float f3047d;

        /* renamed from: e */
        public float f3048e;
    }

    /* renamed from: androidx.viewpager.widget.ViewPager$h */
    public class C0555h extends C4613a {
        public C0555h() {
        }

        /* renamed from: a */
        public final boolean m3214a() {
            AbstractC6243a abstractC6243a = ViewPager.this.f3016f;
            return abstractC6243a != null && abstractC6243a.getCount() > 1;
        }

        @Override // p042d0.C4613a
        public void onInitializeAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
            AbstractC6243a abstractC6243a;
            super.onInitializeAccessibilityEvent(view, accessibilityEvent);
            accessibilityEvent.setClassName(ViewPager.class.getName());
            accessibilityEvent.setScrollable(m3214a());
            if (accessibilityEvent.getEventType() != 4096 || (abstractC6243a = ViewPager.this.f3016f) == null) {
                return;
            }
            accessibilityEvent.setItemCount(abstractC6243a.getCount());
            accessibilityEvent.setFromIndex(ViewPager.this.f3018g);
            accessibilityEvent.setToIndex(ViewPager.this.f3018g);
        }

        @Override // p042d0.C4613a
        public void onInitializeAccessibilityNodeInfo(View view, C4704m c4704m) {
            super.onInitializeAccessibilityNodeInfo(view, c4704m);
            c4704m.m18781U(ViewPager.class.getName());
            c4704m.m18814o0(m3214a());
            if (ViewPager.this.canScrollHorizontally(1)) {
                c4704m.m18787a(4096);
            }
            if (ViewPager.this.canScrollHorizontally(-1)) {
                c4704m.m18787a(UserMetadata.MAX_INTERNAL_KEY_SIZE);
            }
        }

        @Override // p042d0.C4613a
        public boolean performAccessibilityAction(View view, int i9, Bundle bundle) {
            if (super.performAccessibilityAction(view, i9, bundle)) {
                return true;
            }
            if (i9 == 4096) {
                if (!ViewPager.this.canScrollHorizontally(1)) {
                    return false;
                }
                ViewPager viewPager = ViewPager.this;
                viewPager.setCurrentItem(viewPager.f3018g + 1);
                return true;
            }
            if (i9 != 8192 || !ViewPager.this.canScrollHorizontally(-1)) {
                return false;
            }
            ViewPager viewPager2 = ViewPager.this;
            viewPager2.setCurrentItem(viewPager2.f3018g - 1);
            return true;
        }
    }

    /* renamed from: androidx.viewpager.widget.ViewPager$i */
    public interface InterfaceC0556i {
        void onAdapterChanged(ViewPager viewPager, AbstractC6243a abstractC6243a, AbstractC6243a abstractC6243a2);
    }

    /* renamed from: androidx.viewpager.widget.ViewPager$j */
    public interface InterfaceC0557j {
        void onPageScrollStateChanged(int i9);

        void onPageScrolled(int i9, float f9, int i10);

        void onPageSelected(int i9);
    }

    /* renamed from: androidx.viewpager.widget.ViewPager$k */
    public class C0558k extends DataSetObserver {
        public C0558k() {
        }

        @Override // android.database.DataSetObserver
        public void onChanged() throws Resources.NotFoundException {
            ViewPager.this.m3192h();
        }

        @Override // android.database.DataSetObserver
        public void onInvalidated() throws Resources.NotFoundException {
            ViewPager.this.m3192h();
        }
    }

    /* renamed from: androidx.viewpager.widget.ViewPager$l */
    public static class C0559l implements Comparator<View> {
        @Override // java.util.Comparator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(View view, View view2) {
            C0554g c0554g = (C0554g) view.getLayoutParams();
            C0554g c0554g2 = (C0554g) view2.getLayoutParams();
            boolean z8 = c0554g.f3049a;
            return z8 != c0554g2.f3049a ? z8 ? 1 : -1 : c0554g.f3053e - c0554g2.f3053e;
        }
    }

    public ViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f3010c = new ArrayList<>();
        this.f3012d = new C0553f();
        this.f3014e = new Rect();
        this.f3019h = -1;
        this.f3020i = null;
        this.f3021j = null;
        this.f3029r = -3.4028235E38f;
        this.f3030s = Float.MAX_VALUE;
        this.f3036y = 1;
        this.f2992I = -1;
        this.f3001R = true;
        this.f3002S = false;
        this.f3015e0 = new RunnableC0550c();
        this.f3017f0 = 0;
        m3205u();
    }

    private int getClientWidth() {
        return (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight();
    }

    private void setScrollingCacheEnabled(boolean z8) {
        if (this.f3034w != z8) {
            this.f3034w = z8;
        }
    }

    /* renamed from: v */
    public static boolean m3167v(View view) {
        return view.getClass().getAnnotation(InterfaceC0552e.class) != null;
    }

    /* renamed from: A */
    public boolean m3168A() {
        AbstractC6243a abstractC6243a = this.f3016f;
        if (abstractC6243a == null || this.f3018g >= abstractC6243a.getCount() - 1) {
            return false;
        }
        m3180M(this.f3018g + 1, true);
        return true;
    }

    /* renamed from: B */
    public final boolean m3169B(int i9) {
        if (this.f3010c.size() == 0) {
            if (this.f3001R) {
                return false;
            }
            this.f3003T = false;
            m3207x(0, BitmapDescriptorFactory.HUE_RED, 0);
            if (this.f3003T) {
                return false;
            }
            throw new IllegalStateException("onPageScrolled did not call superclass implementation");
        }
        C0553f c0553fM3203s = m3203s();
        int clientWidth = getClientWidth();
        int i10 = this.f3025n;
        int i11 = clientWidth + i10;
        float f9 = clientWidth;
        int i12 = c0553fM3203s.f3045b;
        float f10 = ((i9 / f9) - c0553fM3203s.f3048e) / (c0553fM3203s.f3047d + (i10 / f9));
        this.f3003T = false;
        m3207x(i12, f10, (int) (i11 * f10));
        if (this.f3003T) {
            return true;
        }
        throw new IllegalStateException("onPageScrolled did not call superclass implementation");
    }

    /* renamed from: C */
    public final boolean m3170C(float f9) {
        boolean z8;
        boolean z9;
        float f10 = this.f2988E - f9;
        this.f2988E = f9;
        float scrollX = getScrollX() + f10;
        float clientWidth = getClientWidth();
        float f11 = this.f3029r * clientWidth;
        float f12 = this.f3030s * clientWidth;
        boolean z10 = false;
        C0553f c0553f = this.f3010c.get(0);
        ArrayList<C0553f> arrayList = this.f3010c;
        C0553f c0553f2 = arrayList.get(arrayList.size() - 1);
        if (c0553f.f3045b != 0) {
            f11 = c0553f.f3048e * clientWidth;
            z8 = false;
        } else {
            z8 = true;
        }
        if (c0553f2.f3045b != this.f3016f.getCount() - 1) {
            f12 = c0553f2.f3048e * clientWidth;
            z9 = false;
        } else {
            z9 = true;
        }
        if (scrollX < f11) {
            if (z8) {
                this.f2999P.onPull(Math.abs(f11 - scrollX) / clientWidth);
                z10 = true;
            }
            scrollX = f11;
        } else if (scrollX > f12) {
            if (z9) {
                this.f3000Q.onPull(Math.abs(scrollX - f12) / clientWidth);
                z10 = true;
            }
            scrollX = f12;
        }
        int i9 = (int) scrollX;
        this.f2988E += scrollX - i9;
        scrollTo(i9, getScrollY());
        m3169B(i9);
        return z10;
    }

    /* renamed from: D */
    public void m3171D() throws Resources.NotFoundException {
        m3172E(this.f3018g);
    }

    /* JADX WARN: Code restructure failed: missing block: B:27:0x0066, code lost:
    
        r8 = null;
     */
    /* JADX WARN: Removed duplicated region for block: B:64:0x00f7 A[PHI: r7 r10 r15
      0x00f7: PHI (r7v6 int) = (r7v5 int), (r7v4 int), (r7v9 int) binds: [B:62:0x00ec, B:59:0x00d6, B:53:0x00c0] A[DONT_GENERATE, DONT_INLINE]
      0x00f7: PHI (r10v9 int) = (r10v1 int), (r10v8 int), (r10v12 int) binds: [B:62:0x00ec, B:59:0x00d6, B:53:0x00c0] A[DONT_GENERATE, DONT_INLINE]
      0x00f7: PHI (r15v7 float) = (r15v5 float), (r15v6 float), (r15v4 float) binds: [B:62:0x00ec, B:59:0x00d6, B:53:0x00c0] A[DONT_GENERATE, DONT_INLINE]] */
    /* renamed from: E */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void m3172E(int i9) throws Resources.NotFoundException {
        C0553f c0553fM3204t;
        String hexString;
        C0553f c0553fM3185a;
        C0553f c0553fM3202r;
        C0553f c0553f;
        int i10 = this.f3018g;
        if (i10 != i9) {
            c0553fM3204t = m3204t(i10);
            this.f3018g = i9;
        } else {
            c0553fM3204t = null;
        }
        if (this.f3016f == null) {
            m3184Q();
            return;
        }
        if (this.f3035x) {
            m3184Q();
            return;
        }
        if (getWindowToken() == null) {
            return;
        }
        this.f3016f.startUpdate((ViewGroup) this);
        int i11 = this.f3036y;
        int iMax = Math.max(0, this.f3018g - i11);
        int count = this.f3016f.getCount();
        int iMin = Math.min(count - 1, this.f3018g + i11);
        if (count != this.f3008b) {
            try {
                hexString = getResources().getResourceName(getId());
            } catch (Resources.NotFoundException unused) {
                hexString = Integer.toHexString(getId());
            }
            throw new IllegalStateException("The application's PagerAdapter changed the adapter's contents without calling PagerAdapter#notifyDataSetChanged! Expected adapter item count: " + this.f3008b + ", found: " + count + " Pager id: " + hexString + " Pager class: " + getClass() + " Problematic adapter: " + this.f3016f.getClass());
        }
        int i12 = 0;
        while (true) {
            if (i12 >= this.f3010c.size()) {
                break;
            }
            c0553fM3185a = this.f3010c.get(i12);
            int i13 = c0553fM3185a.f3045b;
            int i14 = this.f3018g;
            if (i13 >= i14) {
                if (i13 != i14) {
                    break;
                }
            } else {
                i12++;
            }
        }
        if (c0553fM3185a == null && count > 0) {
            c0553fM3185a = m3185a(this.f3018g, i12);
        }
        if (c0553fM3185a != null) {
            int i15 = i12 - 1;
            C0553f c0553f2 = i15 >= 0 ? this.f3010c.get(i15) : null;
            int clientWidth = getClientWidth();
            float paddingLeft = clientWidth <= 0 ? 0.0f : (2.0f - c0553fM3185a.f3047d) + (getPaddingLeft() / clientWidth);
            float f9 = 0.0f;
            for (int i16 = this.f3018g - 1; i16 >= 0; i16--) {
                if (f9 >= paddingLeft && i16 < iMax) {
                    if (c0553f2 == null) {
                        break;
                    }
                    if (i16 == c0553f2.f3045b && !c0553f2.f3046c) {
                        this.f3010c.remove(i15);
                        this.f3016f.destroyItem((ViewGroup) this, i16, c0553f2.f3044a);
                        i15--;
                        i12--;
                        if (i15 >= 0) {
                            c0553f = this.f3010c.get(i15);
                        }
                        c0553f2 = c0553f;
                    }
                } else if (c0553f2 == null || i16 != c0553f2.f3045b) {
                    f9 += m3185a(i16, i15 + 1).f3047d;
                    i12++;
                    c0553f = i15 >= 0 ? this.f3010c.get(i15) : null;
                    c0553f2 = c0553f;
                } else {
                    f9 += c0553f2.f3047d;
                    i15--;
                    if (i15 >= 0) {
                        c0553f = this.f3010c.get(i15);
                    }
                    c0553f2 = c0553f;
                }
            }
            float f10 = c0553fM3185a.f3047d;
            int i17 = i12 + 1;
            if (f10 < 2.0f) {
                C0553f c0553f3 = i17 < this.f3010c.size() ? this.f3010c.get(i17) : null;
                float paddingRight = clientWidth <= 0 ? 0.0f : (getPaddingRight() / clientWidth) + 2.0f;
                int i18 = this.f3018g;
                while (true) {
                    i18++;
                    if (i18 >= count) {
                        break;
                    }
                    if (f10 >= paddingRight && i18 > iMin) {
                        if (c0553f3 == null) {
                            break;
                        }
                        if (i18 == c0553f3.f3045b && !c0553f3.f3046c) {
                            this.f3010c.remove(i17);
                            this.f3016f.destroyItem((ViewGroup) this, i18, c0553f3.f3044a);
                            if (i17 < this.f3010c.size()) {
                                c0553f3 = this.f3010c.get(i17);
                            }
                        }
                    } else if (c0553f3 == null || i18 != c0553f3.f3045b) {
                        C0553f c0553fM3185a2 = m3185a(i18, i17);
                        i17++;
                        f10 += c0553fM3185a2.f3047d;
                        c0553f3 = i17 < this.f3010c.size() ? this.f3010c.get(i17) : null;
                    } else {
                        f10 += c0553f3.f3047d;
                        i17++;
                        if (i17 < this.f3010c.size()) {
                            c0553f3 = this.f3010c.get(i17);
                        }
                    }
                }
            }
            m3189e(c0553fM3185a, i12, c0553fM3204t);
            this.f3016f.setPrimaryItem((ViewGroup) this, this.f3018g, c0553fM3185a.f3044a);
        }
        this.f3016f.finishUpdate((ViewGroup) this);
        int childCount = getChildCount();
        for (int i19 = 0; i19 < childCount; i19++) {
            View childAt = getChildAt(i19);
            C0554g c0554g = (C0554g) childAt.getLayoutParams();
            c0554g.f3054f = i19;
            if (!c0554g.f3049a && c0554g.f3051c == BitmapDescriptorFactory.HUE_RED && (c0553fM3202r = m3202r(childAt)) != null) {
                c0554g.f3051c = c0553fM3202r.f3047d;
                c0554g.f3053e = c0553fM3202r.f3045b;
            }
        }
        m3184Q();
        if (hasFocus()) {
            View viewFindFocus = findFocus();
            C0553f c0553fM3201q = viewFindFocus != null ? m3201q(viewFindFocus) : null;
            if (c0553fM3201q == null || c0553fM3201q.f3045b != this.f3018g) {
                for (int i20 = 0; i20 < getChildCount(); i20++) {
                    View childAt2 = getChildAt(i20);
                    C0553f c0553fM3202r2 = m3202r(childAt2);
                    if (c0553fM3202r2 != null && c0553fM3202r2.f3045b == this.f3018g && childAt2.requestFocus(2)) {
                        return;
                    }
                }
            }
        }
    }

    /* renamed from: F */
    public final void m3173F(int i9, int i10, int i11, int i12) {
        if (i10 > 0 && !this.f3010c.isEmpty()) {
            if (!this.f3022k.isFinished()) {
                this.f3022k.setFinalX(getCurrentItem() * getClientWidth());
                return;
            } else {
                scrollTo((int) ((getScrollX() / (((i10 - getPaddingLeft()) - getPaddingRight()) + i12)) * (((i9 - getPaddingLeft()) - getPaddingRight()) + i11)), getScrollY());
                return;
            }
        }
        C0553f c0553fM3204t = m3204t(this.f3018g);
        int iMin = (int) ((c0553fM3204t != null ? Math.min(c0553fM3204t.f3048e, this.f3030s) : BitmapDescriptorFactory.HUE_RED) * ((i9 - getPaddingLeft()) - getPaddingRight()));
        if (iMin != getScrollX()) {
            m3191g(false);
            scrollTo(iMin, getScrollY());
        }
    }

    /* renamed from: G */
    public final void m3174G() {
        int i9 = 0;
        while (i9 < getChildCount()) {
            if (!((C0554g) getChildAt(i9).getLayoutParams()).f3049a) {
                removeViewAt(i9);
                i9--;
            }
            i9++;
        }
    }

    /* renamed from: H */
    public void m3175H(InterfaceC0556i interfaceC0556i) {
        List<InterfaceC0556i> list = this.f3009b0;
        if (list != null) {
            list.remove(interfaceC0556i);
        }
    }

    /* renamed from: I */
    public void m3176I(InterfaceC0557j interfaceC0557j) {
        List<InterfaceC0557j> list = this.f3005V;
        if (list != null) {
            list.remove(interfaceC0557j);
        }
    }

    /* renamed from: J */
    public final void m3177J(boolean z8) {
        ViewParent parent = getParent();
        if (parent != null) {
            parent.requestDisallowInterceptTouchEvent(z8);
        }
    }

    /* renamed from: K */
    public final boolean m3178K() {
        this.f2992I = -1;
        m3198n();
        this.f2999P.onRelease();
        this.f3000Q.onRelease();
        return this.f2999P.isFinished() || this.f3000Q.isFinished();
    }

    /* renamed from: L */
    public final void m3179L(int i9, boolean z8, int i10, boolean z9) throws Resources.NotFoundException {
        C0553f c0553fM3204t = m3204t(i9);
        int clientWidth = c0553fM3204t != null ? (int) (getClientWidth() * Math.max(this.f3029r, Math.min(c0553fM3204t.f3048e, this.f3030s))) : 0;
        if (z8) {
            m3183P(clientWidth, 0, i10);
            if (z9) {
                m3195k(i9);
                return;
            }
            return;
        }
        if (z9) {
            m3195k(i9);
        }
        m3191g(false);
        scrollTo(clientWidth, 0);
        m3169B(clientWidth);
    }

    /* renamed from: M */
    public void m3180M(int i9, boolean z8) {
        this.f3035x = false;
        m3181N(i9, z8, false);
    }

    /* renamed from: N */
    public void m3181N(int i9, boolean z8, boolean z9) throws Resources.NotFoundException {
        m3182O(i9, z8, z9, 0);
    }

    /* renamed from: O */
    public void m3182O(int i9, boolean z8, boolean z9, int i10) throws Resources.NotFoundException {
        AbstractC6243a abstractC6243a = this.f3016f;
        if (abstractC6243a == null || abstractC6243a.getCount() <= 0) {
            setScrollingCacheEnabled(false);
            return;
        }
        if (!z9 && this.f3018g == i9 && this.f3010c.size() != 0) {
            setScrollingCacheEnabled(false);
            return;
        }
        if (i9 < 0) {
            i9 = 0;
        } else if (i9 >= this.f3016f.getCount()) {
            i9 = this.f3016f.getCount() - 1;
        }
        int i11 = this.f3036y;
        int i12 = this.f3018g;
        if (i9 > i12 + i11 || i9 < i12 - i11) {
            for (int i13 = 0; i13 < this.f3010c.size(); i13++) {
                this.f3010c.get(i13).f3046c = true;
            }
        }
        boolean z10 = this.f3018g != i9;
        if (!this.f3001R) {
            m3172E(i9);
            m3179L(i9, z8, i10, z10);
        } else {
            this.f3018g = i9;
            if (z10) {
                m3195k(i9);
            }
            requestLayout();
        }
    }

    /* renamed from: P */
    public void m3183P(int i9, int i10, int i11) throws Resources.NotFoundException {
        int scrollX;
        if (getChildCount() == 0) {
            setScrollingCacheEnabled(false);
            return;
        }
        Scroller scroller = this.f3022k;
        if ((scroller == null || scroller.isFinished()) ? false : true) {
            scrollX = this.f3023l ? this.f3022k.getCurrX() : this.f3022k.getStartX();
            this.f3022k.abortAnimation();
            setScrollingCacheEnabled(false);
        } else {
            scrollX = getScrollX();
        }
        int i12 = scrollX;
        int scrollY = getScrollY();
        int i13 = i9 - i12;
        int i14 = i10 - scrollY;
        if (i13 == 0 && i14 == 0) {
            m3191g(false);
            m3171D();
            setScrollState(0);
            return;
        }
        setScrollingCacheEnabled(true);
        setScrollState(2);
        int clientWidth = getClientWidth();
        int i15 = clientWidth / 2;
        float f9 = clientWidth;
        float f10 = i15;
        float fM3197m = f10 + (m3197m(Math.min(1.0f, (Math.abs(i13) * 1.0f) / f9)) * f10);
        int iAbs = Math.abs(i11);
        int iMin = Math.min(iAbs > 0 ? Math.round(Math.abs(fM3197m / iAbs) * 1000.0f) * 4 : (int) (((Math.abs(i13) / ((f9 * this.f3016f.getPageWidth(this.f3018g)) + this.f3025n)) + 1.0f) * 100.0f), 600);
        this.f3023l = false;
        this.f3022k.startScroll(i12, scrollY, i13, i14, iMin);
        C4647u.m18524T(this);
    }

    /* renamed from: Q */
    public final void m3184Q() {
        if (this.f3011c0 != 0) {
            ArrayList<View> arrayList = this.f3013d0;
            if (arrayList == null) {
                this.f3013d0 = new ArrayList<>();
            } else {
                arrayList.clear();
            }
            int childCount = getChildCount();
            for (int i9 = 0; i9 < childCount; i9++) {
                this.f3013d0.add(getChildAt(i9));
            }
            Collections.sort(this.f3013d0, f2983j0);
        }
    }

    /* renamed from: a */
    public C0553f m3185a(int i9, int i10) {
        C0553f c0553f = new C0553f();
        c0553f.f3045b = i9;
        c0553f.f3044a = this.f3016f.instantiateItem((ViewGroup) this, i9);
        c0553f.f3047d = this.f3016f.getPageWidth(i9);
        if (i10 < 0 || i10 >= this.f3010c.size()) {
            this.f3010c.add(c0553f);
        } else {
            this.f3010c.add(i10, c0553f);
        }
        return c0553f;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void addFocusables(ArrayList<View> arrayList, int i9, int i10) {
        C0553f c0553fM3202r;
        int size = arrayList.size();
        int descendantFocusability = getDescendantFocusability();
        if (descendantFocusability != 393216) {
            for (int i11 = 0; i11 < getChildCount(); i11++) {
                View childAt = getChildAt(i11);
                if (childAt.getVisibility() == 0 && (c0553fM3202r = m3202r(childAt)) != null && c0553fM3202r.f3045b == this.f3018g) {
                    childAt.addFocusables(arrayList, i9, i10);
                }
            }
        }
        if ((descendantFocusability != 262144 || size == arrayList.size()) && isFocusable()) {
            if ((i10 & 1) == 1 && isInTouchMode() && !isFocusableInTouchMode()) {
                return;
            }
            arrayList.add(this);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void addTouchables(ArrayList<View> arrayList) {
        C0553f c0553fM3202r;
        for (int i9 = 0; i9 < getChildCount(); i9++) {
            View childAt = getChildAt(i9);
            if (childAt.getVisibility() == 0 && (c0553fM3202r = m3202r(childAt)) != null && c0553fM3202r.f3045b == this.f3018g) {
                childAt.addTouchables(arrayList);
            }
        }
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int i9, ViewGroup.LayoutParams layoutParams) {
        if (!checkLayoutParams(layoutParams)) {
            layoutParams = generateLayoutParams(layoutParams);
        }
        C0554g c0554g = (C0554g) layoutParams;
        boolean zM3167v = c0554g.f3049a | m3167v(view);
        c0554g.f3049a = zM3167v;
        if (!this.f3033v) {
            super.addView(view, i9, layoutParams);
        } else {
            if (zM3167v) {
                throw new IllegalStateException("Cannot add pager decor view during layout");
            }
            c0554g.f3052d = true;
            addViewInLayout(view, i9, layoutParams);
        }
    }

    /* renamed from: b */
    public void m3186b(InterfaceC0556i interfaceC0556i) {
        if (this.f3009b0 == null) {
            this.f3009b0 = new ArrayList();
        }
        this.f3009b0.add(interfaceC0556i);
    }

    /* renamed from: c */
    public void m3187c(InterfaceC0557j interfaceC0557j) {
        if (this.f3005V == null) {
            this.f3005V = new ArrayList();
        }
        this.f3005V.add(interfaceC0557j);
    }

    @Override // android.view.View
    public boolean canScrollHorizontally(int i9) {
        if (this.f3016f == null) {
            return false;
        }
        int clientWidth = getClientWidth();
        int scrollX = getScrollX();
        return i9 < 0 ? scrollX > ((int) (((float) clientWidth) * this.f3029r)) : i9 > 0 && scrollX < ((int) (((float) clientWidth) * this.f3030s));
    }

    @Override // android.view.ViewGroup
    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return (layoutParams instanceof C0554g) && super.checkLayoutParams(layoutParams);
    }

    @Override // android.view.View
    public void computeScroll() {
        this.f3023l = true;
        if (this.f3022k.isFinished() || !this.f3022k.computeScrollOffset()) {
            m3191g(true);
            return;
        }
        int scrollX = getScrollX();
        int scrollY = getScrollY();
        int currX = this.f3022k.getCurrX();
        int currY = this.f3022k.getCurrY();
        if (scrollX != currX || scrollY != currY) {
            scrollTo(currX, currY);
            if (!m3169B(currX)) {
                this.f3022k.abortAnimation();
                scrollTo(0, currY);
            }
        }
        C4647u.m18524T(this);
    }

    /* renamed from: d */
    public boolean m3188d(int i9) {
        boolean z8;
        boolean zRequestFocus;
        View viewFindFocus = findFocus();
        boolean zM3209z = false;
        if (viewFindFocus == this) {
            viewFindFocus = null;
        } else if (viewFindFocus != null) {
            ViewParent parent = viewFindFocus.getParent();
            while (true) {
                if (!(parent instanceof ViewGroup)) {
                    z8 = false;
                    break;
                }
                if (parent == this) {
                    z8 = true;
                    break;
                }
                parent = parent.getParent();
            }
            if (!z8) {
                StringBuilder sb = new StringBuilder();
                sb.append(viewFindFocus.getClass().getSimpleName());
                for (ViewParent parent2 = viewFindFocus.getParent(); parent2 instanceof ViewGroup; parent2 = parent2.getParent()) {
                    sb.append(" => ");
                    sb.append(parent2.getClass().getSimpleName());
                }
                Log.e("ViewPager", "arrowScroll tried to find focus based on non-child current focused view " + sb.toString());
                viewFindFocus = null;
            }
        }
        View viewFindNextFocus = FocusFinder.getInstance().findNextFocus(this, viewFindFocus, i9);
        if (viewFindNextFocus != null && viewFindNextFocus != viewFindFocus) {
            if (i9 == 17) {
                zRequestFocus = (viewFindFocus == null || m3200p(this.f3014e, viewFindNextFocus).left < m3200p(this.f3014e, viewFindFocus).left) ? viewFindNextFocus.requestFocus() : m3209z();
            } else if (i9 == 66) {
                zRequestFocus = (viewFindFocus == null || m3200p(this.f3014e, viewFindNextFocus).left > m3200p(this.f3014e, viewFindFocus).left) ? viewFindNextFocus.requestFocus() : m3168A();
            }
            zM3209z = zRequestFocus;
        } else if (i9 == 17 || i9 == 1) {
            zM3209z = m3209z();
        } else if (i9 == 66 || i9 == 2) {
            zM3209z = m3168A();
        }
        if (zM3209z) {
            playSoundEffect(SoundEffectConstants.getContantForFocusDirection(i9));
        }
        return zM3209z;
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        return super.dispatchKeyEvent(keyEvent) || m3199o(keyEvent);
    }

    @Override // android.view.View
    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        C0553f c0553fM3202r;
        if (accessibilityEvent.getEventType() == 4096) {
            return super.dispatchPopulateAccessibilityEvent(accessibilityEvent);
        }
        int childCount = getChildCount();
        for (int i9 = 0; i9 < childCount; i9++) {
            View childAt = getChildAt(i9);
            if (childAt.getVisibility() == 0 && (c0553fM3202r = m3202r(childAt)) != null && c0553fM3202r.f3045b == this.f3018g && childAt.dispatchPopulateAccessibilityEvent(accessibilityEvent)) {
                return true;
            }
        }
        return false;
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        AbstractC6243a abstractC6243a;
        super.draw(canvas);
        int overScrollMode = getOverScrollMode();
        boolean zDraw = false;
        if (overScrollMode == 0 || (overScrollMode == 1 && (abstractC6243a = this.f3016f) != null && abstractC6243a.getCount() > 1)) {
            if (!this.f2999P.isFinished()) {
                int iSave = canvas.save();
                int height = (getHeight() - getPaddingTop()) - getPaddingBottom();
                int width = getWidth();
                canvas.rotate(270.0f);
                canvas.translate((-height) + getPaddingTop(), this.f3029r * width);
                this.f2999P.setSize(height, width);
                zDraw = false | this.f2999P.draw(canvas);
                canvas.restoreToCount(iSave);
            }
            if (!this.f3000Q.isFinished()) {
                int iSave2 = canvas.save();
                int width2 = getWidth();
                int height2 = (getHeight() - getPaddingTop()) - getPaddingBottom();
                canvas.rotate(90.0f);
                canvas.translate(-getPaddingTop(), (-(this.f3030s + 1.0f)) * width2);
                this.f3000Q.setSize(height2, width2);
                zDraw |= this.f3000Q.draw(canvas);
                canvas.restoreToCount(iSave2);
            }
        } else {
            this.f2999P.finish();
            this.f3000Q.finish();
        }
        if (zDraw) {
            C4647u.m18524T(this);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        Drawable drawable = this.f3026o;
        if (drawable == null || !drawable.isStateful()) {
            return;
        }
        drawable.setState(getDrawableState());
    }

    /* renamed from: e */
    public final void m3189e(C0553f c0553f, int i9, C0553f c0553f2) {
        int i10;
        int i11;
        C0553f c0553f3;
        C0553f c0553f4;
        int count = this.f3016f.getCount();
        int clientWidth = getClientWidth();
        float f9 = clientWidth > 0 ? this.f3025n / clientWidth : BitmapDescriptorFactory.HUE_RED;
        if (c0553f2 != null) {
            int i12 = c0553f2.f3045b;
            int i13 = c0553f.f3045b;
            if (i12 < i13) {
                float pageWidth = c0553f2.f3048e + c0553f2.f3047d + f9;
                int i14 = i12 + 1;
                int i15 = 0;
                while (i14 <= c0553f.f3045b && i15 < this.f3010c.size()) {
                    C0553f c0553f5 = this.f3010c.get(i15);
                    while (true) {
                        c0553f4 = c0553f5;
                        if (i14 <= c0553f4.f3045b || i15 >= this.f3010c.size() - 1) {
                            break;
                        }
                        i15++;
                        c0553f5 = this.f3010c.get(i15);
                    }
                    while (i14 < c0553f4.f3045b) {
                        pageWidth += this.f3016f.getPageWidth(i14) + f9;
                        i14++;
                    }
                    c0553f4.f3048e = pageWidth;
                    pageWidth += c0553f4.f3047d + f9;
                    i14++;
                }
            } else if (i12 > i13) {
                int size = this.f3010c.size() - 1;
                float pageWidth2 = c0553f2.f3048e;
                while (true) {
                    i12--;
                    if (i12 < c0553f.f3045b || size < 0) {
                        break;
                    }
                    C0553f c0553f6 = this.f3010c.get(size);
                    while (true) {
                        c0553f3 = c0553f6;
                        if (i12 >= c0553f3.f3045b || size <= 0) {
                            break;
                        }
                        size--;
                        c0553f6 = this.f3010c.get(size);
                    }
                    while (i12 > c0553f3.f3045b) {
                        pageWidth2 -= this.f3016f.getPageWidth(i12) + f9;
                        i12--;
                    }
                    pageWidth2 -= c0553f3.f3047d + f9;
                    c0553f3.f3048e = pageWidth2;
                }
            }
        }
        int size2 = this.f3010c.size();
        float pageWidth3 = c0553f.f3048e;
        int i16 = c0553f.f3045b;
        int i17 = i16 - 1;
        this.f3029r = i16 == 0 ? pageWidth3 : -3.4028235E38f;
        int i18 = count - 1;
        this.f3030s = i16 == i18 ? (c0553f.f3047d + pageWidth3) - 1.0f : Float.MAX_VALUE;
        int i19 = i9 - 1;
        while (i19 >= 0) {
            C0553f c0553f7 = this.f3010c.get(i19);
            while (true) {
                i11 = c0553f7.f3045b;
                if (i17 <= i11) {
                    break;
                }
                pageWidth3 -= this.f3016f.getPageWidth(i17) + f9;
                i17--;
            }
            pageWidth3 -= c0553f7.f3047d + f9;
            c0553f7.f3048e = pageWidth3;
            if (i11 == 0) {
                this.f3029r = pageWidth3;
            }
            i19--;
            i17--;
        }
        float pageWidth4 = c0553f.f3048e + c0553f.f3047d + f9;
        int i20 = c0553f.f3045b + 1;
        int i21 = i9 + 1;
        while (i21 < size2) {
            C0553f c0553f8 = this.f3010c.get(i21);
            while (true) {
                i10 = c0553f8.f3045b;
                if (i20 >= i10) {
                    break;
                }
                pageWidth4 += this.f3016f.getPageWidth(i20) + f9;
                i20++;
            }
            if (i10 == i18) {
                this.f3030s = (c0553f8.f3047d + pageWidth4) - 1.0f;
            }
            c0553f8.f3048e = pageWidth4;
            pageWidth4 += c0553f8.f3047d + f9;
            i21++;
            i20++;
        }
        this.f3002S = false;
    }

    /* renamed from: f */
    public boolean m3190f(View view, boolean z8, int i9, int i10, int i11) {
        int i12;
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int scrollX = view.getScrollX();
            int scrollY = view.getScrollY();
            for (int childCount = viewGroup.getChildCount() - 1; childCount >= 0; childCount--) {
                View childAt = viewGroup.getChildAt(childCount);
                int i13 = i10 + scrollX;
                if (i13 >= childAt.getLeft() && i13 < childAt.getRight() && (i12 = i11 + scrollY) >= childAt.getTop() && i12 < childAt.getBottom() && m3190f(childAt, true, i9, i13 - childAt.getLeft(), i12 - childAt.getTop())) {
                    return true;
                }
            }
        }
        return z8 && view.canScrollHorizontally(-i9);
    }

    /* renamed from: g */
    public final void m3191g(boolean z8) {
        boolean z9 = this.f3017f0 == 2;
        if (z9) {
            setScrollingCacheEnabled(false);
            if (!this.f3022k.isFinished()) {
                this.f3022k.abortAnimation();
                int scrollX = getScrollX();
                int scrollY = getScrollY();
                int currX = this.f3022k.getCurrX();
                int currY = this.f3022k.getCurrY();
                if (scrollX != currX || scrollY != currY) {
                    scrollTo(currX, currY);
                    if (currX != scrollX) {
                        m3169B(currX);
                    }
                }
            }
        }
        this.f3035x = false;
        for (int i9 = 0; i9 < this.f3010c.size(); i9++) {
            C0553f c0553f = this.f3010c.get(i9);
            if (c0553f.f3046c) {
                c0553f.f3046c = false;
                z9 = true;
            }
        }
        if (z9) {
            if (z8) {
                C4647u.m18525U(this, this.f3015e0);
            } else {
                this.f3015e0.run();
            }
        }
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new C0554g();
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return generateDefaultLayoutParams();
    }

    public AbstractC6243a getAdapter() {
        return this.f3016f;
    }

    @Override // android.view.ViewGroup
    public int getChildDrawingOrder(int i9, int i10) {
        if (this.f3011c0 == 2) {
            i10 = (i9 - 1) - i10;
        }
        return ((C0554g) this.f3013d0.get(i10).getLayoutParams()).f3054f;
    }

    public int getCurrentItem() {
        return this.f3018g;
    }

    public int getOffscreenPageLimit() {
        return this.f3036y;
    }

    public int getPageMargin() {
        return this.f3025n;
    }

    /* renamed from: h */
    public void m3192h() throws Resources.NotFoundException {
        int count = this.f3016f.getCount();
        this.f3008b = count;
        boolean z8 = this.f3010c.size() < (this.f3036y * 2) + 1 && this.f3010c.size() < count;
        int iMax = this.f3018g;
        int i9 = 0;
        boolean z9 = false;
        while (i9 < this.f3010c.size()) {
            C0553f c0553f = this.f3010c.get(i9);
            int itemPosition = this.f3016f.getItemPosition(c0553f.f3044a);
            if (itemPosition != -1) {
                if (itemPosition == -2) {
                    this.f3010c.remove(i9);
                    i9--;
                    if (!z9) {
                        this.f3016f.startUpdate((ViewGroup) this);
                        z9 = true;
                    }
                    this.f3016f.destroyItem((ViewGroup) this, c0553f.f3045b, c0553f.f3044a);
                    int i10 = this.f3018g;
                    if (i10 == c0553f.f3045b) {
                        iMax = Math.max(0, Math.min(i10, count - 1));
                    }
                } else {
                    int i11 = c0553f.f3045b;
                    if (i11 != itemPosition) {
                        if (i11 == this.f3018g) {
                            iMax = itemPosition;
                        }
                        c0553f.f3045b = itemPosition;
                    }
                }
                z8 = true;
            }
            i9++;
        }
        if (z9) {
            this.f3016f.finishUpdate((ViewGroup) this);
        }
        Collections.sort(this.f3010c, f2981h0);
        if (z8) {
            int childCount = getChildCount();
            for (int i12 = 0; i12 < childCount; i12++) {
                C0554g c0554g = (C0554g) getChildAt(i12).getLayoutParams();
                if (!c0554g.f3049a) {
                    c0554g.f3051c = BitmapDescriptorFactory.HUE_RED;
                }
            }
            m3181N(iMax, false, true);
            requestLayout();
        }
    }

    /* renamed from: i */
    public final int m3193i(int i9, float f9, int i10, int i11) {
        if (Math.abs(i11) <= this.f2996M || Math.abs(i10) <= this.f2994K) {
            i9 += (int) (f9 + (i9 >= this.f3018g ? 0.4f : 0.6f));
        } else if (i10 <= 0) {
            i9++;
        }
        if (this.f3010c.size() <= 0) {
            return i9;
        }
        return Math.max(this.f3010c.get(0).f3045b, Math.min(i9, this.f3010c.get(r4.size() - 1).f3045b));
    }

    /* renamed from: j */
    public final void m3194j(int i9, float f9, int i10) {
        InterfaceC0557j interfaceC0557j = this.f3006W;
        if (interfaceC0557j != null) {
            interfaceC0557j.onPageScrolled(i9, f9, i10);
        }
        List<InterfaceC0557j> list = this.f3005V;
        if (list != null) {
            int size = list.size();
            for (int i11 = 0; i11 < size; i11++) {
                InterfaceC0557j interfaceC0557j2 = this.f3005V.get(i11);
                if (interfaceC0557j2 != null) {
                    interfaceC0557j2.onPageScrolled(i9, f9, i10);
                }
            }
        }
        InterfaceC0557j interfaceC0557j3 = this.f3007a0;
        if (interfaceC0557j3 != null) {
            interfaceC0557j3.onPageScrolled(i9, f9, i10);
        }
    }

    /* renamed from: k */
    public final void m3195k(int i9) {
        InterfaceC0557j interfaceC0557j = this.f3006W;
        if (interfaceC0557j != null) {
            interfaceC0557j.onPageSelected(i9);
        }
        List<InterfaceC0557j> list = this.f3005V;
        if (list != null) {
            int size = list.size();
            for (int i10 = 0; i10 < size; i10++) {
                InterfaceC0557j interfaceC0557j2 = this.f3005V.get(i10);
                if (interfaceC0557j2 != null) {
                    interfaceC0557j2.onPageSelected(i9);
                }
            }
        }
        InterfaceC0557j interfaceC0557j3 = this.f3007a0;
        if (interfaceC0557j3 != null) {
            interfaceC0557j3.onPageSelected(i9);
        }
    }

    /* renamed from: l */
    public final void m3196l(int i9) {
        InterfaceC0557j interfaceC0557j = this.f3006W;
        if (interfaceC0557j != null) {
            interfaceC0557j.onPageScrollStateChanged(i9);
        }
        List<InterfaceC0557j> list = this.f3005V;
        if (list != null) {
            int size = list.size();
            for (int i10 = 0; i10 < size; i10++) {
                InterfaceC0557j interfaceC0557j2 = this.f3005V.get(i10);
                if (interfaceC0557j2 != null) {
                    interfaceC0557j2.onPageScrollStateChanged(i9);
                }
            }
        }
        InterfaceC0557j interfaceC0557j3 = this.f3007a0;
        if (interfaceC0557j3 != null) {
            interfaceC0557j3.onPageScrollStateChanged(i9);
        }
    }

    /* renamed from: m */
    public float m3197m(float f9) {
        return (float) Math.sin((f9 - 0.5f) * 0.47123894f);
    }

    /* renamed from: n */
    public final void m3198n() {
        this.f3037z = false;
        this.f2984A = false;
        VelocityTracker velocityTracker = this.f2993J;
        if (velocityTracker != null) {
            velocityTracker.recycle();
            this.f2993J = null;
        }
    }

    /* renamed from: o */
    public boolean m3199o(KeyEvent keyEvent) {
        if (keyEvent.getAction() == 0) {
            int keyCode = keyEvent.getKeyCode();
            if (keyCode == 21) {
                return keyEvent.hasModifiers(2) ? m3209z() : m3188d(17);
            }
            if (keyCode == 22) {
                return keyEvent.hasModifiers(2) ? m3168A() : m3188d(66);
            }
            if (keyCode == 61) {
                if (keyEvent.hasNoModifiers()) {
                    return m3188d(2);
                }
                if (keyEvent.hasModifiers(1)) {
                    return m3188d(1);
                }
            }
        }
        return false;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.f3001R = true;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        removeCallbacks(this.f3015e0);
        Scroller scroller = this.f3022k;
        if (scroller != null && !scroller.isFinished()) {
            this.f3022k.abortAnimation();
        }
        super.onDetachedFromWindow();
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        int i9;
        float f9;
        float f10;
        super.onDraw(canvas);
        if (this.f3025n <= 0 || this.f3026o == null || this.f3010c.size() <= 0 || this.f3016f == null) {
            return;
        }
        int scrollX = getScrollX();
        float width = getWidth();
        float f11 = this.f3025n / width;
        int i10 = 0;
        C0553f c0553f = this.f3010c.get(0);
        float f12 = c0553f.f3048e;
        int size = this.f3010c.size();
        int i11 = c0553f.f3045b;
        int i12 = this.f3010c.get(size - 1).f3045b;
        while (i11 < i12) {
            while (true) {
                i9 = c0553f.f3045b;
                if (i11 <= i9 || i10 >= size) {
                    break;
                }
                i10++;
                c0553f = this.f3010c.get(i10);
            }
            if (i11 == i9) {
                float f13 = c0553f.f3048e;
                float f14 = c0553f.f3047d;
                f9 = (f13 + f14) * width;
                f12 = f13 + f14 + f11;
            } else {
                float pageWidth = this.f3016f.getPageWidth(i11);
                f9 = (f12 + pageWidth) * width;
                f12 += pageWidth + f11;
            }
            if (this.f3025n + f9 > scrollX) {
                f10 = f11;
                this.f3026o.setBounds(Math.round(f9), this.f3027p, Math.round(this.f3025n + f9), this.f3028q);
                this.f3026o.draw(canvas);
            } else {
                f10 = f11;
            }
            if (f9 > scrollX + r2) {
                return;
            }
            i11++;
            f11 = f10;
        }
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) throws Resources.NotFoundException {
        int action = motionEvent.getAction() & 255;
        if (action == 3 || action == 1) {
            m3178K();
            return false;
        }
        if (action != 0) {
            if (this.f3037z) {
                return true;
            }
            if (this.f2984A) {
                return false;
            }
        }
        if (action == 0) {
            float x8 = motionEvent.getX();
            this.f2990G = x8;
            this.f2988E = x8;
            float y8 = motionEvent.getY();
            this.f2991H = y8;
            this.f2989F = y8;
            this.f2992I = motionEvent.getPointerId(0);
            this.f2984A = false;
            this.f3023l = true;
            this.f3022k.computeScrollOffset();
            if (this.f3017f0 != 2 || Math.abs(this.f3022k.getFinalX() - this.f3022k.getCurrX()) <= this.f2997N) {
                m3191g(false);
                this.f3037z = false;
            } else {
                this.f3022k.abortAnimation();
                this.f3035x = false;
                m3171D();
                this.f3037z = true;
                m3177J(true);
                setScrollState(1);
            }
        } else if (action == 2) {
            int i9 = this.f2992I;
            if (i9 != -1) {
                int iFindPointerIndex = motionEvent.findPointerIndex(i9);
                float x9 = motionEvent.getX(iFindPointerIndex);
                float f9 = x9 - this.f2988E;
                float fAbs = Math.abs(f9);
                float y9 = motionEvent.getY(iFindPointerIndex);
                float fAbs2 = Math.abs(y9 - this.f2991H);
                if (f9 != BitmapDescriptorFactory.HUE_RED && !m3206w(this.f2988E, f9) && m3190f(this, false, (int) f9, (int) x9, (int) y9)) {
                    this.f2988E = x9;
                    this.f2989F = y9;
                    this.f2984A = true;
                    return false;
                }
                int i10 = this.f2987D;
                if (fAbs > i10 && fAbs * 0.5f > fAbs2) {
                    this.f3037z = true;
                    m3177J(true);
                    setScrollState(1);
                    float f10 = this.f2990G;
                    float f11 = this.f2987D;
                    this.f2988E = f9 > BitmapDescriptorFactory.HUE_RED ? f10 + f11 : f10 - f11;
                    this.f2989F = y9;
                    setScrollingCacheEnabled(true);
                } else if (fAbs2 > i10) {
                    this.f2984A = true;
                }
                if (this.f3037z && m3170C(x9)) {
                    C4647u.m18524T(this);
                }
            }
        } else if (action == 6) {
            m3208y(motionEvent);
        }
        if (this.f2993J == null) {
            this.f2993J = VelocityTracker.obtain();
        }
        this.f2993J.addMovement(motionEvent);
        return this.f3037z;
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0071  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x008e  */
    @Override // android.view.ViewGroup, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void onLayout(boolean z8, int i9, int i10, int i11, int i12) throws Resources.NotFoundException {
        boolean z9;
        C0553f c0553fM3202r;
        int iMax;
        int measuredWidth;
        int iMax2;
        int measuredHeight;
        int childCount = getChildCount();
        int i13 = i11 - i9;
        int i14 = i12 - i10;
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();
        int paddingBottom = getPaddingBottom();
        int scrollX = getScrollX();
        int i15 = 0;
        for (int i16 = 0; i16 < childCount; i16++) {
            View childAt = getChildAt(i16);
            if (childAt.getVisibility() != 8) {
                C0554g c0554g = (C0554g) childAt.getLayoutParams();
                if (c0554g.f3049a) {
                    int i17 = c0554g.f3050b;
                    int i18 = i17 & 7;
                    int i19 = i17 & 112;
                    if (i18 != 1) {
                        if (i18 == 3) {
                            measuredWidth = childAt.getMeasuredWidth() + paddingLeft;
                        } else if (i18 != 5) {
                            measuredWidth = paddingLeft;
                        } else {
                            iMax = (i13 - paddingRight) - childAt.getMeasuredWidth();
                            paddingRight += childAt.getMeasuredWidth();
                        }
                        if (i19 == 16) {
                            if (i19 == 48) {
                                measuredHeight = childAt.getMeasuredHeight() + paddingTop;
                            } else if (i19 != 80) {
                                measuredHeight = paddingTop;
                            } else {
                                iMax2 = (i14 - paddingBottom) - childAt.getMeasuredHeight();
                                paddingBottom += childAt.getMeasuredHeight();
                            }
                            int i20 = paddingLeft + scrollX;
                            childAt.layout(i20, paddingTop, childAt.getMeasuredWidth() + i20, paddingTop + childAt.getMeasuredHeight());
                            i15++;
                            paddingTop = measuredHeight;
                            paddingLeft = measuredWidth;
                        } else {
                            iMax2 = Math.max((i14 - childAt.getMeasuredHeight()) / 2, paddingTop);
                        }
                        int i21 = iMax2;
                        measuredHeight = paddingTop;
                        paddingTop = i21;
                        int i202 = paddingLeft + scrollX;
                        childAt.layout(i202, paddingTop, childAt.getMeasuredWidth() + i202, paddingTop + childAt.getMeasuredHeight());
                        i15++;
                        paddingTop = measuredHeight;
                        paddingLeft = measuredWidth;
                    } else {
                        iMax = Math.max((i13 - childAt.getMeasuredWidth()) / 2, paddingLeft);
                    }
                    int i22 = iMax;
                    measuredWidth = paddingLeft;
                    paddingLeft = i22;
                    if (i19 == 16) {
                    }
                    int i212 = iMax2;
                    measuredHeight = paddingTop;
                    paddingTop = i212;
                    int i2022 = paddingLeft + scrollX;
                    childAt.layout(i2022, paddingTop, childAt.getMeasuredWidth() + i2022, paddingTop + childAt.getMeasuredHeight());
                    i15++;
                    paddingTop = measuredHeight;
                    paddingLeft = measuredWidth;
                }
            }
        }
        int i23 = (i13 - paddingLeft) - paddingRight;
        for (int i24 = 0; i24 < childCount; i24++) {
            View childAt2 = getChildAt(i24);
            if (childAt2.getVisibility() != 8) {
                C0554g c0554g2 = (C0554g) childAt2.getLayoutParams();
                if (!c0554g2.f3049a && (c0553fM3202r = m3202r(childAt2)) != null) {
                    float f9 = i23;
                    int i25 = ((int) (c0553fM3202r.f3048e * f9)) + paddingLeft;
                    if (c0554g2.f3052d) {
                        c0554g2.f3052d = false;
                        childAt2.measure(View.MeasureSpec.makeMeasureSpec((int) (f9 * c0554g2.f3051c), 1073741824), View.MeasureSpec.makeMeasureSpec((i14 - paddingTop) - paddingBottom, 1073741824));
                    }
                    childAt2.layout(i25, paddingTop, childAt2.getMeasuredWidth() + i25, childAt2.getMeasuredHeight() + paddingTop);
                }
            }
        }
        this.f3027p = paddingTop;
        this.f3028q = i14 - paddingBottom;
        this.f3004U = i15;
        if (this.f3001R) {
            z9 = false;
            m3179L(this.f3018g, false, 0, false);
        } else {
            z9 = false;
        }
        this.f3001R = z9;
    }

    @Override // android.view.View
    public void onMeasure(int i9, int i10) throws Resources.NotFoundException {
        C0554g c0554g;
        C0554g c0554g2;
        int i11;
        setMeasuredDimension(View.getDefaultSize(0, i9), View.getDefaultSize(0, i10));
        int measuredWidth = getMeasuredWidth();
        this.f2986C = Math.min(measuredWidth / 10, this.f2985B);
        int paddingLeft = (measuredWidth - getPaddingLeft()) - getPaddingRight();
        int measuredHeight = (getMeasuredHeight() - getPaddingTop()) - getPaddingBottom();
        int childCount = getChildCount();
        int i12 = 0;
        while (true) {
            boolean z8 = true;
            int i13 = 1073741824;
            if (i12 >= childCount) {
                break;
            }
            View childAt = getChildAt(i12);
            if (childAt.getVisibility() != 8 && (c0554g2 = (C0554g) childAt.getLayoutParams()) != null && c0554g2.f3049a) {
                int i14 = c0554g2.f3050b;
                int i15 = i14 & 7;
                int i16 = i14 & 112;
                boolean z9 = i16 == 48 || i16 == 80;
                if (i15 != 3 && i15 != 5) {
                    z8 = false;
                }
                int i17 = Integer.MIN_VALUE;
                if (z9) {
                    i11 = Integer.MIN_VALUE;
                    i17 = 1073741824;
                } else {
                    i11 = z8 ? 1073741824 : Integer.MIN_VALUE;
                }
                int i18 = ((ViewGroup.LayoutParams) c0554g2).width;
                if (i18 != -2) {
                    if (i18 == -1) {
                        i18 = paddingLeft;
                    }
                    i17 = 1073741824;
                } else {
                    i18 = paddingLeft;
                }
                int i19 = ((ViewGroup.LayoutParams) c0554g2).height;
                if (i19 == -2) {
                    i19 = measuredHeight;
                    i13 = i11;
                } else if (i19 == -1) {
                    i19 = measuredHeight;
                }
                childAt.measure(View.MeasureSpec.makeMeasureSpec(i18, i17), View.MeasureSpec.makeMeasureSpec(i19, i13));
                if (z9) {
                    measuredHeight -= childAt.getMeasuredHeight();
                } else if (z8) {
                    paddingLeft -= childAt.getMeasuredWidth();
                }
            }
            i12++;
        }
        this.f3031t = View.MeasureSpec.makeMeasureSpec(paddingLeft, 1073741824);
        this.f3032u = View.MeasureSpec.makeMeasureSpec(measuredHeight, 1073741824);
        this.f3033v = true;
        m3171D();
        this.f3033v = false;
        int childCount2 = getChildCount();
        for (int i20 = 0; i20 < childCount2; i20++) {
            View childAt2 = getChildAt(i20);
            if (childAt2.getVisibility() != 8 && ((c0554g = (C0554g) childAt2.getLayoutParams()) == null || !c0554g.f3049a)) {
                childAt2.measure(View.MeasureSpec.makeMeasureSpec((int) (paddingLeft * c0554g.f3051c), 1073741824), this.f3032u);
            }
        }
    }

    @Override // android.view.ViewGroup
    public boolean onRequestFocusInDescendants(int i9, Rect rect) {
        int i10;
        int i11;
        int i12;
        C0553f c0553fM3202r;
        int childCount = getChildCount();
        if ((i9 & 2) != 0) {
            i11 = childCount;
            i10 = 0;
            i12 = 1;
        } else {
            i10 = childCount - 1;
            i11 = -1;
            i12 = -1;
        }
        while (i10 != i11) {
            View childAt = getChildAt(i10);
            if (childAt.getVisibility() == 0 && (c0553fM3202r = m3202r(childAt)) != null && c0553fM3202r.f3045b == this.f3018g && childAt.requestFocus(i9, rect)) {
                return true;
            }
            i10 += i12;
        }
        return false;
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) throws Resources.NotFoundException {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        AbstractC6243a abstractC6243a = this.f3016f;
        if (abstractC6243a != null) {
            abstractC6243a.restoreState(savedState.f3039c, savedState.f3040d);
            m3181N(savedState.f3038b, false, true);
        } else {
            this.f3019h = savedState.f3038b;
            this.f3020i = savedState.f3039c;
            this.f3021j = savedState.f3040d;
        }
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.f3038b = this.f3018g;
        AbstractC6243a abstractC6243a = this.f3016f;
        if (abstractC6243a != null) {
            savedState.f3039c = abstractC6243a.saveState();
        }
        return savedState;
    }

    @Override // android.view.View
    public void onSizeChanged(int i9, int i10, int i11, int i12) {
        super.onSizeChanged(i9, i10, i11, i12);
        if (i9 != i11) {
            int i13 = this.f3025n;
            m3173F(i9, i11, i13, i13);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:53:0x00dc  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public boolean onTouchEvent(MotionEvent motionEvent) throws Resources.NotFoundException {
        AbstractC6243a abstractC6243a;
        if (this.f2998O) {
            return true;
        }
        boolean zM3178K = false;
        if ((motionEvent.getAction() == 0 && motionEvent.getEdgeFlags() != 0) || (abstractC6243a = this.f3016f) == null || abstractC6243a.getCount() == 0) {
            return false;
        }
        if (this.f2993J == null) {
            this.f2993J = VelocityTracker.obtain();
        }
        this.f2993J.addMovement(motionEvent);
        int action = motionEvent.getAction() & 255;
        if (action == 0) {
            this.f3022k.abortAnimation();
            this.f3035x = false;
            m3171D();
            float x8 = motionEvent.getX();
            this.f2990G = x8;
            this.f2988E = x8;
            float y8 = motionEvent.getY();
            this.f2991H = y8;
            this.f2989F = y8;
            this.f2992I = motionEvent.getPointerId(0);
        } else if (action != 1) {
            if (action != 2) {
                if (action != 3) {
                    if (action == 5) {
                        int actionIndex = motionEvent.getActionIndex();
                        this.f2988E = motionEvent.getX(actionIndex);
                        this.f2992I = motionEvent.getPointerId(actionIndex);
                    } else if (action == 6) {
                        m3208y(motionEvent);
                        this.f2988E = motionEvent.getX(motionEvent.findPointerIndex(this.f2992I));
                    }
                } else if (this.f3037z) {
                    m3179L(this.f3018g, true, 0, false);
                    zM3178K = m3178K();
                }
            } else if (!this.f3037z) {
                int iFindPointerIndex = motionEvent.findPointerIndex(this.f2992I);
                if (iFindPointerIndex == -1) {
                    zM3178K = m3178K();
                } else {
                    float x9 = motionEvent.getX(iFindPointerIndex);
                    float fAbs = Math.abs(x9 - this.f2988E);
                    float y9 = motionEvent.getY(iFindPointerIndex);
                    float fAbs2 = Math.abs(y9 - this.f2989F);
                    if (fAbs > this.f2987D && fAbs > fAbs2) {
                        this.f3037z = true;
                        m3177J(true);
                        float f9 = this.f2990G;
                        this.f2988E = x9 - f9 > BitmapDescriptorFactory.HUE_RED ? f9 + this.f2987D : f9 - this.f2987D;
                        this.f2989F = y9;
                        setScrollState(1);
                        setScrollingCacheEnabled(true);
                        ViewParent parent = getParent();
                        if (parent != null) {
                            parent.requestDisallowInterceptTouchEvent(true);
                        }
                    }
                    if (this.f3037z) {
                    }
                }
            } else if (this.f3037z) {
                zM3178K = false | m3170C(motionEvent.getX(motionEvent.findPointerIndex(this.f2992I)));
            }
        } else if (this.f3037z) {
            VelocityTracker velocityTracker = this.f2993J;
            velocityTracker.computeCurrentVelocity(CredentialsApi.ACTIVITY_RESULT_ADD_ACCOUNT, this.f2995L);
            int xVelocity = (int) velocityTracker.getXVelocity(this.f2992I);
            this.f3035x = true;
            int clientWidth = getClientWidth();
            int scrollX = getScrollX();
            C0553f c0553fM3203s = m3203s();
            float f10 = clientWidth;
            m3182O(m3193i(c0553fM3203s.f3045b, ((scrollX / f10) - c0553fM3203s.f3048e) / (c0553fM3203s.f3047d + (this.f3025n / f10)), xVelocity, (int) (motionEvent.getX(motionEvent.findPointerIndex(this.f2992I)) - this.f2990G)), true, true, xVelocity);
            zM3178K = m3178K();
        }
        if (zM3178K) {
            C4647u.m18524T(this);
        }
        return true;
    }

    /* renamed from: p */
    public final Rect m3200p(Rect rect, View view) {
        if (rect == null) {
            rect = new Rect();
        }
        if (view == null) {
            rect.set(0, 0, 0, 0);
            return rect;
        }
        rect.left = view.getLeft();
        rect.right = view.getRight();
        rect.top = view.getTop();
        rect.bottom = view.getBottom();
        ViewParent parent = view.getParent();
        while ((parent instanceof ViewGroup) && parent != this) {
            ViewGroup viewGroup = (ViewGroup) parent;
            rect.left += viewGroup.getLeft();
            rect.right += viewGroup.getRight();
            rect.top += viewGroup.getTop();
            rect.bottom += viewGroup.getBottom();
            parent = viewGroup.getParent();
        }
        return rect;
    }

    /* renamed from: q */
    public C0553f m3201q(View view) {
        while (true) {
            Object parent = view.getParent();
            if (parent == this) {
                return m3202r(view);
            }
            if (parent == null || !(parent instanceof View)) {
                return null;
            }
            view = (View) parent;
        }
    }

    /* renamed from: r */
    public C0553f m3202r(View view) {
        for (int i9 = 0; i9 < this.f3010c.size(); i9++) {
            C0553f c0553f = this.f3010c.get(i9);
            if (this.f3016f.isViewFromObject(view, c0553f.f3044a)) {
                return c0553f;
            }
        }
        return null;
    }

    @Override // android.view.ViewGroup, android.view.ViewManager
    public void removeView(View view) {
        if (this.f3033v) {
            removeViewInLayout(view);
        } else {
            super.removeView(view);
        }
    }

    /* renamed from: s */
    public final C0553f m3203s() {
        int i9;
        int clientWidth = getClientWidth();
        float f9 = BitmapDescriptorFactory.HUE_RED;
        float scrollX = clientWidth > 0 ? getScrollX() / clientWidth : 0.0f;
        float f10 = clientWidth > 0 ? this.f3025n / clientWidth : 0.0f;
        int i10 = 0;
        boolean z8 = true;
        C0553f c0553f = null;
        int i11 = -1;
        float f11 = 0.0f;
        while (i10 < this.f3010c.size()) {
            C0553f c0553f2 = this.f3010c.get(i10);
            if (!z8 && c0553f2.f3045b != (i9 = i11 + 1)) {
                c0553f2 = this.f3012d;
                c0553f2.f3048e = f9 + f11 + f10;
                c0553f2.f3045b = i9;
                c0553f2.f3047d = this.f3016f.getPageWidth(i9);
                i10--;
            }
            C0553f c0553f3 = c0553f2;
            f9 = c0553f3.f3048e;
            float f12 = c0553f3.f3047d + f9 + f10;
            if (!z8 && scrollX < f9) {
                return c0553f;
            }
            if (scrollX < f12 || i10 == this.f3010c.size() - 1) {
                return c0553f3;
            }
            int i12 = c0553f3.f3045b;
            float f13 = c0553f3.f3047d;
            i10++;
            z8 = false;
            i11 = i12;
            f11 = f13;
            c0553f = c0553f3;
        }
        return c0553f;
    }

    public void setAdapter(AbstractC6243a abstractC6243a) {
        AbstractC6243a abstractC6243a2 = this.f3016f;
        if (abstractC6243a2 != null) {
            abstractC6243a2.setViewPagerObserver(null);
            this.f3016f.startUpdate((ViewGroup) this);
            for (int i9 = 0; i9 < this.f3010c.size(); i9++) {
                C0553f c0553f = this.f3010c.get(i9);
                this.f3016f.destroyItem((ViewGroup) this, c0553f.f3045b, c0553f.f3044a);
            }
            this.f3016f.finishUpdate((ViewGroup) this);
            this.f3010c.clear();
            m3174G();
            this.f3018g = 0;
            scrollTo(0, 0);
        }
        AbstractC6243a abstractC6243a3 = this.f3016f;
        this.f3016f = abstractC6243a;
        this.f3008b = 0;
        if (abstractC6243a != null) {
            if (this.f3024m == null) {
                this.f3024m = new C0558k();
            }
            this.f3016f.setViewPagerObserver(this.f3024m);
            this.f3035x = false;
            boolean z8 = this.f3001R;
            this.f3001R = true;
            this.f3008b = this.f3016f.getCount();
            if (this.f3019h >= 0) {
                this.f3016f.restoreState(this.f3020i, this.f3021j);
                m3181N(this.f3019h, false, true);
                this.f3019h = -1;
                this.f3020i = null;
                this.f3021j = null;
            } else if (z8) {
                requestLayout();
            } else {
                m3171D();
            }
        }
        List<InterfaceC0556i> list = this.f3009b0;
        if (list == null || list.isEmpty()) {
            return;
        }
        int size = this.f3009b0.size();
        for (int i10 = 0; i10 < size; i10++) {
            this.f3009b0.get(i10).onAdapterChanged(this, abstractC6243a3, abstractC6243a);
        }
    }

    public void setCurrentItem(int i9) {
        this.f3035x = false;
        m3181N(i9, !this.f3001R, false);
    }

    public void setOffscreenPageLimit(int i9) {
        if (i9 < 1) {
            Log.w("ViewPager", "Requested offscreen page limit " + i9 + " too small; defaulting to 1");
            i9 = 1;
        }
        if (i9 != this.f3036y) {
            this.f3036y = i9;
            m3171D();
        }
    }

    @Deprecated
    public void setOnPageChangeListener(InterfaceC0557j interfaceC0557j) {
        this.f3006W = interfaceC0557j;
    }

    public void setPageMargin(int i9) {
        int i10 = this.f3025n;
        this.f3025n = i9;
        int width = getWidth();
        m3173F(width, width, i9, i10);
        requestLayout();
    }

    public void setPageMarginDrawable(Drawable drawable) {
        this.f3026o = drawable;
        if (drawable != null) {
            refreshDrawableState();
        }
        setWillNotDraw(drawable == null);
        invalidate();
    }

    public void setScrollState(int i9) {
        if (this.f3017f0 == i9) {
            return;
        }
        this.f3017f0 = i9;
        m3196l(i9);
    }

    /* renamed from: t */
    public C0553f m3204t(int i9) {
        for (int i10 = 0; i10 < this.f3010c.size(); i10++) {
            C0553f c0553f = this.f3010c.get(i10);
            if (c0553f.f3045b == i9) {
                return c0553f;
            }
        }
        return null;
    }

    /* renamed from: u */
    public void m3205u() {
        setWillNotDraw(false);
        setDescendantFocusability(262144);
        setFocusable(true);
        Context context = getContext();
        this.f3022k = new Scroller(context, f2982i0);
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        float f9 = context.getResources().getDisplayMetrics().density;
        this.f2987D = viewConfiguration.getScaledPagingTouchSlop();
        this.f2994K = (int) (400.0f * f9);
        this.f2995L = viewConfiguration.getScaledMaximumFlingVelocity();
        this.f2999P = new EdgeEffect(context);
        this.f3000Q = new EdgeEffect(context);
        this.f2996M = (int) (25.0f * f9);
        this.f2997N = (int) (2.0f * f9);
        this.f2985B = (int) (f9 * 16.0f);
        C4647u.m18530Z(this, new C0555h());
        if (C4647u.m18563q(this) == 0) {
            C4647u.m18548i0(this, 1);
        }
        C4647u.m18554l0(this, new C0551d());
    }

    @Override // android.view.View
    public boolean verifyDrawable(Drawable drawable) {
        return super.verifyDrawable(drawable) || drawable == this.f3026o;
    }

    /* renamed from: w */
    public final boolean m3206w(float f9, float f10) {
        return (f9 < ((float) this.f2986C) && f10 > BitmapDescriptorFactory.HUE_RED) || (f9 > ((float) (getWidth() - this.f2986C)) && f10 < BitmapDescriptorFactory.HUE_RED);
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0063  */
    /* renamed from: x */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void m3207x(int i9, float f9, int i10) {
        int iMax;
        int width;
        int left;
        if (this.f3004U > 0) {
            int scrollX = getScrollX();
            int paddingLeft = getPaddingLeft();
            int paddingRight = getPaddingRight();
            int width2 = getWidth();
            int childCount = getChildCount();
            for (int i11 = 0; i11 < childCount; i11++) {
                View childAt = getChildAt(i11);
                C0554g c0554g = (C0554g) childAt.getLayoutParams();
                if (c0554g.f3049a) {
                    int i12 = c0554g.f3050b & 7;
                    if (i12 != 1) {
                        if (i12 == 3) {
                            width = childAt.getWidth() + paddingLeft;
                        } else if (i12 != 5) {
                            width = paddingLeft;
                        } else {
                            iMax = (width2 - paddingRight) - childAt.getMeasuredWidth();
                            paddingRight += childAt.getMeasuredWidth();
                        }
                        left = (paddingLeft + scrollX) - childAt.getLeft();
                        if (left != 0) {
                            childAt.offsetLeftAndRight(left);
                        }
                        paddingLeft = width;
                    } else {
                        iMax = Math.max((width2 - childAt.getMeasuredWidth()) / 2, paddingLeft);
                    }
                    int i13 = iMax;
                    width = paddingLeft;
                    paddingLeft = i13;
                    left = (paddingLeft + scrollX) - childAt.getLeft();
                    if (left != 0) {
                    }
                    paddingLeft = width;
                }
            }
        }
        m3194j(i9, f9, i10);
        this.f3003T = true;
    }

    /* renamed from: y */
    public final void m3208y(MotionEvent motionEvent) {
        int actionIndex = motionEvent.getActionIndex();
        if (motionEvent.getPointerId(actionIndex) == this.f2992I) {
            int i9 = actionIndex == 0 ? 1 : 0;
            this.f2988E = motionEvent.getX(i9);
            this.f2992I = motionEvent.getPointerId(i9);
            VelocityTracker velocityTracker = this.f2993J;
            if (velocityTracker != null) {
                velocityTracker.clear();
            }
        }
    }

    /* renamed from: z */
    public boolean m3209z() {
        int i9 = this.f3018g;
        if (i9 <= 0) {
            return false;
        }
        m3180M(i9 - 1, true);
        return true;
    }

    /* renamed from: androidx.viewpager.widget.ViewPager$g */
    public static class C0554g extends ViewGroup.LayoutParams {

        /* renamed from: a */
        public boolean f3049a;

        /* renamed from: b */
        public int f3050b;

        /* renamed from: c */
        public float f3051c;

        /* renamed from: d */
        public boolean f3052d;

        /* renamed from: e */
        public int f3053e;

        /* renamed from: f */
        public int f3054f;

        public C0554g() {
            super(-1, -1);
            this.f3051c = BitmapDescriptorFactory.HUE_RED;
        }

        public C0554g(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.f3051c = BitmapDescriptorFactory.HUE_RED;
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, ViewPager.f2980g0);
            this.f3050b = typedArrayObtainStyledAttributes.getInteger(0, 48);
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new C0554g(getContext(), attributeSet);
    }

    public void setPageMarginDrawable(int i9) {
        setPageMarginDrawable(C6273a.m24025d(getContext(), i9));
    }
}
