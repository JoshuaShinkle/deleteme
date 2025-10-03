package p042d0;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.Display;
import android.view.KeyEvent;
import android.view.PointerIcon;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import android.view.WindowInsets;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import p042d0.C4613a;
import p052e0.C4704m;
import p179r.C6179b;

/* renamed from: d0.u */
/* loaded from: classes.dex */
public class C4647u {

    /* renamed from: c */
    public static Field f16278c;

    /* renamed from: a */
    public static final AtomicInteger f16276a = new AtomicInteger(1);

    /* renamed from: b */
    public static WeakHashMap<View, C4620d0> f16277b = null;

    /* renamed from: d */
    public static boolean f16279d = false;

    /* renamed from: e */
    public static final int[] f16280e = {C6179b.accessibility_custom_action_0, C6179b.accessibility_custom_action_1, C6179b.accessibility_custom_action_2, C6179b.accessibility_custom_action_3, C6179b.accessibility_custom_action_4, C6179b.accessibility_custom_action_5, C6179b.accessibility_custom_action_6, C6179b.accessibility_custom_action_7, C6179b.accessibility_custom_action_8, C6179b.accessibility_custom_action_9, C6179b.accessibility_custom_action_10, C6179b.accessibility_custom_action_11, C6179b.accessibility_custom_action_12, C6179b.accessibility_custom_action_13, C6179b.accessibility_custom_action_14, C6179b.accessibility_custom_action_15, C6179b.accessibility_custom_action_16, C6179b.accessibility_custom_action_17, C6179b.accessibility_custom_action_18, C6179b.accessibility_custom_action_19, C6179b.accessibility_custom_action_20, C6179b.accessibility_custom_action_21, C6179b.accessibility_custom_action_22, C6179b.accessibility_custom_action_23, C6179b.accessibility_custom_action_24, C6179b.accessibility_custom_action_25, C6179b.accessibility_custom_action_26, C6179b.accessibility_custom_action_27, C6179b.accessibility_custom_action_28, C6179b.accessibility_custom_action_29, C6179b.accessibility_custom_action_30, C6179b.accessibility_custom_action_31};

    /* renamed from: f */
    public static e f16281f = new e();

    /* renamed from: d0.u$a */
    public class a implements View.OnApplyWindowInsetsListener {

        /* renamed from: a */
        public final /* synthetic */ InterfaceC4643q f16282a;

        public a(InterfaceC4643q interfaceC4643q) {
            this.f16282a = interfaceC4643q;
        }

        @Override // android.view.View.OnApplyWindowInsetsListener
        public WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
            return this.f16282a.onApplyWindowInsets(view, C4628h0.m18432o(windowInsets)).m18445n();
        }
    }

    /* renamed from: d0.u$b */
    public class b extends f<Boolean> {
        public b(int i9, Class cls, int i10) {
            super(i9, cls, i10);
        }

        @Override // p042d0.C4647u.f
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public Boolean mo18575c(View view) {
            return Boolean.valueOf(view.isScreenReaderFocusable());
        }
    }

    /* renamed from: d0.u$c */
    public class c extends f<CharSequence> {
        public c(int i9, Class cls, int i10, int i11) {
            super(i9, cls, i10, i11);
        }

        @Override // p042d0.C4647u.f
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public CharSequence mo18575c(View view) {
            return view.getAccessibilityPaneTitle();
        }
    }

    /* renamed from: d0.u$d */
    public class d extends f<Boolean> {
        public d(int i9, Class cls, int i10) {
            super(i9, cls, i10);
        }

        @Override // p042d0.C4647u.f
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public Boolean mo18575c(View view) {
            return Boolean.valueOf(view.isAccessibilityHeading());
        }
    }

    /* renamed from: d0.u$e */
    public static class e implements ViewTreeObserver.OnGlobalLayoutListener, View.OnAttachStateChangeListener {

        /* renamed from: b */
        public WeakHashMap<View, Boolean> f16283b = new WeakHashMap<>();

        /* renamed from: a */
        public final void m18579a(View view, boolean z8) {
            boolean z9 = view.getVisibility() == 0;
            if (z8 != z9) {
                if (z9) {
                    C4647u.m18517M(view, 16);
                }
                this.f16283b.put(view, Boolean.valueOf(z9));
            }
        }

        /* renamed from: b */
        public final void m18580b(View view) {
            view.getViewTreeObserver().addOnGlobalLayoutListener(this);
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            for (Map.Entry<View, Boolean> entry : this.f16283b.entrySet()) {
                m18579a(entry.getKey(), entry.getValue().booleanValue());
            }
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewAttachedToWindow(View view) {
            m18580b(view);
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewDetachedFromWindow(View view) {
        }
    }

    /* renamed from: d0.u$f */
    public static abstract class f<T> {

        /* renamed from: a */
        public final int f16284a;

        /* renamed from: b */
        public final Class<T> f16285b;

        /* renamed from: c */
        public final int f16286c;

        public f(int i9, Class<T> cls, int i10) {
            this(i9, cls, 0, i10);
        }

        /* renamed from: a */
        public final boolean m18581a() {
            return true;
        }

        /* renamed from: b */
        public final boolean m18582b() {
            return Build.VERSION.SDK_INT >= this.f16286c;
        }

        /* renamed from: c */
        public abstract T mo18575c(View view);

        /* renamed from: d */
        public T m18583d(View view) {
            if (m18582b()) {
                return mo18575c(view);
            }
            if (!m18581a()) {
                return null;
            }
            T t8 = (T) view.getTag(this.f16284a);
            if (this.f16285b.isInstance(t8)) {
                return t8;
            }
            return null;
        }

        public f(int i9, Class<T> cls, int i10, int i11) {
            this.f16284a = i9;
            this.f16285b = cls;
            this.f16286c = i11;
        }
    }

    /* renamed from: d0.u$g */
    public static class g {
        /* renamed from: a */
        public static C4628h0 m18584a(View view, C4628h0 c4628h0, Rect rect) {
            WindowInsets windowInsetsM18445n = c4628h0.m18445n();
            if (windowInsetsM18445n != null) {
                return C4628h0.m18432o(view.computeSystemWindowInsets(windowInsetsM18445n, rect));
            }
            rect.setEmpty();
            return c4628h0;
        }
    }

    /* renamed from: d0.u$h */
    public static class h {
        /* renamed from: a */
        public static WindowInsets m18585a(View view) {
            return view.getRootWindowInsets();
        }
    }

    /* renamed from: d0.u$i */
    public static class i {
        /* renamed from: a */
        public static void m18586a(View view, Context context, int[] iArr, AttributeSet attributeSet, TypedArray typedArray, int i9, int i10) {
            view.saveAttributeDataForStyleable(context, iArr, attributeSet, typedArray, i9, i10);
        }
    }

    /* renamed from: d0.u$j */
    public interface j {
        boolean onUnhandledKeyEvent(View view, KeyEvent keyEvent);
    }

    /* renamed from: d0.u$k */
    public static class k {

        /* renamed from: d */
        public static final ArrayList<WeakReference<View>> f16287d = new ArrayList<>();

        /* renamed from: a */
        public WeakHashMap<View, Boolean> f16288a = null;

        /* renamed from: b */
        public SparseArray<WeakReference<View>> f16289b = null;

        /* renamed from: c */
        public WeakReference<KeyEvent> f16290c = null;

        /* renamed from: a */
        public static k m18587a(View view) {
            int i9 = C6179b.tag_unhandled_key_event_manager;
            k kVar = (k) view.getTag(i9);
            if (kVar != null) {
                return kVar;
            }
            k kVar2 = new k();
            view.setTag(i9, kVar2);
            return kVar2;
        }

        /* renamed from: b */
        public boolean m18588b(View view, KeyEvent keyEvent) {
            if (keyEvent.getAction() == 0) {
                m18593g();
            }
            View viewM18589c = m18589c(view, keyEvent);
            if (keyEvent.getAction() == 0) {
                int keyCode = keyEvent.getKeyCode();
                if (viewM18589c != null && !KeyEvent.isModifierKey(keyCode)) {
                    m18590d().put(keyCode, new WeakReference<>(viewM18589c));
                }
            }
            return viewM18589c != null;
        }

        /* renamed from: c */
        public final View m18589c(View view, KeyEvent keyEvent) {
            WeakHashMap<View, Boolean> weakHashMap = this.f16288a;
            if (weakHashMap != null && weakHashMap.containsKey(view)) {
                if (view instanceof ViewGroup) {
                    ViewGroup viewGroup = (ViewGroup) view;
                    for (int childCount = viewGroup.getChildCount() - 1; childCount >= 0; childCount--) {
                        View viewM18589c = m18589c(viewGroup.getChildAt(childCount), keyEvent);
                        if (viewM18589c != null) {
                            return viewM18589c;
                        }
                    }
                }
                if (m18591e(view, keyEvent)) {
                    return view;
                }
            }
            return null;
        }

        /* renamed from: d */
        public final SparseArray<WeakReference<View>> m18590d() {
            if (this.f16289b == null) {
                this.f16289b = new SparseArray<>();
            }
            return this.f16289b;
        }

        /* renamed from: e */
        public final boolean m18591e(View view, KeyEvent keyEvent) {
            ArrayList arrayList = (ArrayList) view.getTag(C6179b.tag_unhandled_key_listeners);
            if (arrayList == null) {
                return false;
            }
            for (int size = arrayList.size() - 1; size >= 0; size--) {
                if (((j) arrayList.get(size)).onUnhandledKeyEvent(view, keyEvent)) {
                    return true;
                }
            }
            return false;
        }

        /* renamed from: f */
        public boolean m18592f(KeyEvent keyEvent) {
            WeakReference<View> weakReferenceValueAt;
            int iIndexOfKey;
            WeakReference<KeyEvent> weakReference = this.f16290c;
            if (weakReference != null && weakReference.get() == keyEvent) {
                return false;
            }
            this.f16290c = new WeakReference<>(keyEvent);
            SparseArray<WeakReference<View>> sparseArrayM18590d = m18590d();
            if (keyEvent.getAction() != 1 || (iIndexOfKey = sparseArrayM18590d.indexOfKey(keyEvent.getKeyCode())) < 0) {
                weakReferenceValueAt = null;
            } else {
                weakReferenceValueAt = sparseArrayM18590d.valueAt(iIndexOfKey);
                sparseArrayM18590d.removeAt(iIndexOfKey);
            }
            if (weakReferenceValueAt == null) {
                weakReferenceValueAt = sparseArrayM18590d.get(keyEvent.getKeyCode());
            }
            if (weakReferenceValueAt == null) {
                return false;
            }
            View view = weakReferenceValueAt.get();
            if (view != null && C4647u.m18512H(view)) {
                m18591e(view, keyEvent);
            }
            return true;
        }

        /* renamed from: g */
        public final void m18593g() {
            WeakHashMap<View, Boolean> weakHashMap = this.f16288a;
            if (weakHashMap != null) {
                weakHashMap.clear();
            }
            ArrayList<WeakReference<View>> arrayList = f16287d;
            if (arrayList.isEmpty()) {
                return;
            }
            synchronized (arrayList) {
                if (this.f16288a == null) {
                    this.f16288a = new WeakHashMap<>();
                }
                for (int size = arrayList.size() - 1; size >= 0; size--) {
                    ArrayList<WeakReference<View>> arrayList2 = f16287d;
                    View view = arrayList2.get(size).get();
                    if (view == null) {
                        arrayList2.remove(size);
                    } else {
                        this.f16288a.put(view, Boolean.TRUE);
                        for (ViewParent parent = view.getParent(); parent instanceof View; parent = parent.getParent()) {
                            this.f16288a.put((View) parent, Boolean.TRUE);
                        }
                    }
                }
            }
        }
    }

    /* renamed from: A */
    public static int m18505A(View view) {
        return view.getWindowSystemUiVisibility();
    }

    /* renamed from: B */
    public static float m18506B(View view) {
        return view.getZ();
    }

    /* renamed from: C */
    public static boolean m18507C(View view) {
        return m18543g(view) != null;
    }

    /* renamed from: D */
    public static boolean m18508D(View view) {
        return view.hasOnClickListeners();
    }

    /* renamed from: E */
    public static boolean m18509E(View view) {
        return view.hasOverlappingRendering();
    }

    /* renamed from: F */
    public static boolean m18510F(View view) {
        return view.hasTransientState();
    }

    /* renamed from: G */
    public static boolean m18511G(View view) {
        Boolean boolM18583d = m18531a().m18583d(view);
        if (boolM18583d == null) {
            return false;
        }
        return boolM18583d.booleanValue();
    }

    /* renamed from: H */
    public static boolean m18512H(View view) {
        return view.isAttachedToWindow();
    }

    /* renamed from: I */
    public static boolean m18513I(View view) {
        return view.isLaidOut();
    }

    /* renamed from: J */
    public static boolean m18514J(View view) {
        return view.isNestedScrollingEnabled();
    }

    /* renamed from: K */
    public static boolean m18515K(View view) {
        return view.isPaddingRelative();
    }

    /* renamed from: L */
    public static boolean m18516L(View view) {
        Boolean boolM18583d = m18529Y().m18583d(view);
        if (boolM18583d == null) {
            return false;
        }
        return boolM18583d.booleanValue();
    }

    /* renamed from: M */
    public static void m18517M(View view, int i9) {
        if (((AccessibilityManager) view.getContext().getSystemService("accessibility")).isEnabled()) {
            boolean z8 = m18549j(view) != null;
            if (m18547i(view) != 0 || (z8 && view.getVisibility() == 0)) {
                AccessibilityEvent accessibilityEventObtain = AccessibilityEvent.obtain();
                accessibilityEventObtain.setEventType(z8 ? 32 : 2048);
                accessibilityEventObtain.setContentChangeTypes(i9);
                view.sendAccessibilityEventUnchecked(accessibilityEventObtain);
                return;
            }
            if (view.getParent() != null) {
                try {
                    view.getParent().notifySubtreeAccessibilityStateChanged(view, view, i9);
                } catch (AbstractMethodError e9) {
                    Log.e("ViewCompat", view.getParent().getClass().getSimpleName() + " does not fully implement ViewParent", e9);
                }
            }
        }
    }

    /* renamed from: N */
    public static void m18518N(View view, int i9) {
        view.offsetLeftAndRight(i9);
    }

    /* renamed from: O */
    public static void m18519O(View view, int i9) {
        view.offsetTopAndBottom(i9);
    }

    /* renamed from: P */
    public static C4628h0 m18520P(View view, C4628h0 c4628h0) {
        WindowInsets windowInsetsM18445n = c4628h0.m18445n();
        if (windowInsetsM18445n != null) {
            WindowInsets windowInsetsOnApplyWindowInsets = view.onApplyWindowInsets(windowInsetsM18445n);
            if (!windowInsetsOnApplyWindowInsets.equals(windowInsetsM18445n)) {
                return C4628h0.m18432o(windowInsetsOnApplyWindowInsets);
            }
        }
        return c4628h0;
    }

    /* renamed from: Q */
    public static void m18521Q(View view, C4704m c4704m) {
        view.onInitializeAccessibilityNodeInfo(c4704m.m18828v0());
    }

    /* renamed from: R */
    public static f<CharSequence> m18522R() {
        return new c(C6179b.tag_accessibility_pane_title, CharSequence.class, 8, 28);
    }

    /* renamed from: S */
    public static boolean m18523S(View view, int i9, Bundle bundle) {
        return view.performAccessibilityAction(i9, bundle);
    }

    /* renamed from: T */
    public static void m18524T(View view) {
        view.postInvalidateOnAnimation();
    }

    /* renamed from: U */
    public static void m18525U(View view, Runnable runnable) {
        view.postOnAnimation(runnable);
    }

    /* renamed from: V */
    public static void m18526V(View view, Runnable runnable, long j9) {
        view.postOnAnimationDelayed(runnable, j9);
    }

    /* renamed from: W */
    public static void m18527W(View view) {
        view.requestApplyInsets();
    }

    /* renamed from: X */
    public static void m18528X(View view, @SuppressLint({"ContextFirst"}) Context context, int[] iArr, AttributeSet attributeSet, TypedArray typedArray, int i9, int i10) {
        if (Build.VERSION.SDK_INT >= 29) {
            i.m18586a(view, context, iArr, attributeSet, typedArray, i9, i10);
        }
    }

    /* renamed from: Y */
    public static f<Boolean> m18529Y() {
        return new b(C6179b.tag_screen_reader_focusable, Boolean.class, 28);
    }

    /* renamed from: Z */
    public static void m18530Z(View view, C4613a c4613a) {
        if (c4613a == null && (m18543g(view) instanceof C4613a.a)) {
            c4613a = new C4613a();
        }
        view.setAccessibilityDelegate(c4613a == null ? null : c4613a.getBridge());
    }

    /* renamed from: a */
    public static f<Boolean> m18531a() {
        return new d(C6179b.tag_accessibility_heading, Boolean.class, 28);
    }

    /* renamed from: a0 */
    public static void m18532a0(View view, int i9) {
        view.setAccessibilityLiveRegion(i9);
    }

    /* renamed from: b */
    public static C4620d0 m18533b(View view) {
        if (f16277b == null) {
            f16277b = new WeakHashMap<>();
        }
        C4620d0 c4620d0 = f16277b.get(view);
        if (c4620d0 != null) {
            return c4620d0;
        }
        C4620d0 c4620d02 = new C4620d0(view);
        f16277b.put(view, c4620d02);
        return c4620d02;
    }

    /* renamed from: b0 */
    public static void m18534b0(View view, Drawable drawable) {
        view.setBackground(drawable);
    }

    /* renamed from: c */
    public static C4628h0 m18535c(View view, C4628h0 c4628h0, Rect rect) {
        return g.m18584a(view, c4628h0, rect);
    }

    /* renamed from: c0 */
    public static void m18536c0(View view, ColorStateList colorStateList) {
        view.setBackgroundTintList(colorStateList);
    }

    /* renamed from: d */
    public static C4628h0 m18537d(View view, C4628h0 c4628h0) {
        WindowInsets windowInsetsM18445n = c4628h0.m18445n();
        return (windowInsetsM18445n == null || view.dispatchApplyWindowInsets(windowInsetsM18445n).equals(windowInsetsM18445n)) ? c4628h0 : C4628h0.m18432o(windowInsetsM18445n);
    }

    /* renamed from: d0 */
    public static void m18538d0(View view, PorterDuff.Mode mode) {
        view.setBackgroundTintMode(mode);
    }

    /* renamed from: e */
    public static boolean m18539e(View view, KeyEvent keyEvent) {
        if (Build.VERSION.SDK_INT >= 28) {
            return false;
        }
        return k.m18587a(view).m18588b(view, keyEvent);
    }

    /* renamed from: e0 */
    public static void m18540e0(View view, Rect rect) {
        view.setClipBounds(rect);
    }

    /* renamed from: f */
    public static boolean m18541f(View view, KeyEvent keyEvent) {
        if (Build.VERSION.SDK_INT >= 28) {
            return false;
        }
        return k.m18587a(view).m18592f(keyEvent);
    }

    /* renamed from: f0 */
    public static void m18542f0(View view, float f9) {
        view.setElevation(f9);
    }

    /* renamed from: g */
    public static View.AccessibilityDelegate m18543g(View view) {
        return Build.VERSION.SDK_INT >= 29 ? view.getAccessibilityDelegate() : m18545h(view);
    }

    @Deprecated
    /* renamed from: g0 */
    public static void m18544g0(View view, boolean z8) {
        view.setFitsSystemWindows(z8);
    }

    /* renamed from: h */
    public static View.AccessibilityDelegate m18545h(View view) {
        if (f16279d) {
            return null;
        }
        if (f16278c == null) {
            try {
                Field declaredField = View.class.getDeclaredField("mAccessibilityDelegate");
                f16278c = declaredField;
                declaredField.setAccessible(true);
            } catch (Throwable unused) {
                f16279d = true;
                return null;
            }
        }
        try {
            Object obj = f16278c.get(view);
            if (obj instanceof View.AccessibilityDelegate) {
                return (View.AccessibilityDelegate) obj;
            }
            return null;
        } catch (Throwable unused2) {
            f16279d = true;
            return null;
        }
    }

    /* renamed from: h0 */
    public static void m18546h0(View view, boolean z8) {
        view.setHasTransientState(z8);
    }

    /* renamed from: i */
    public static int m18547i(View view) {
        return view.getAccessibilityLiveRegion();
    }

    /* renamed from: i0 */
    public static void m18548i0(View view, int i9) {
        view.setImportantForAccessibility(i9);
    }

    /* renamed from: j */
    public static CharSequence m18549j(View view) {
        return m18522R().m18583d(view);
    }

    /* renamed from: j0 */
    public static void m18550j0(View view, int i9) {
        view.setImportantForAutofill(i9);
    }

    /* renamed from: k */
    public static ColorStateList m18551k(View view) {
        return view.getBackgroundTintList();
    }

    /* renamed from: k0 */
    public static void m18552k0(View view, Paint paint) {
        view.setLayerPaint(paint);
    }

    /* renamed from: l */
    public static PorterDuff.Mode m18553l(View view) {
        return view.getBackgroundTintMode();
    }

    /* renamed from: l0 */
    public static void m18554l0(View view, InterfaceC4643q interfaceC4643q) {
        if (interfaceC4643q == null) {
            view.setOnApplyWindowInsetsListener(null);
        } else {
            view.setOnApplyWindowInsetsListener(new a(interfaceC4643q));
        }
    }

    /* renamed from: m */
    public static Rect m18555m(View view) {
        return view.getClipBounds();
    }

    /* renamed from: m0 */
    public static void m18556m0(View view, int i9, int i10, int i11, int i12) {
        view.setPaddingRelative(i9, i10, i11, i12);
    }

    /* renamed from: n */
    public static Display m18557n(View view) {
        return view.getDisplay();
    }

    /* renamed from: n0 */
    public static void m18558n0(View view, C4645s c4645s) {
        view.setPointerIcon((PointerIcon) (c4645s != null ? c4645s.m18503a() : null));
    }

    /* renamed from: o */
    public static float m18559o(View view) {
        return view.getElevation();
    }

    /* renamed from: o0 */
    public static void m18560o0(View view, int i9, int i10) {
        view.setScrollIndicators(i9, i10);
    }

    /* renamed from: p */
    public static boolean m18561p(View view) {
        return view.getFitsSystemWindows();
    }

    /* renamed from: p0 */
    public static void m18562p0(View view, String str) {
        view.setTransitionName(str);
    }

    /* renamed from: q */
    public static int m18563q(View view) {
        return view.getImportantForAccessibility();
    }

    /* renamed from: q0 */
    public static void m18564q0(View view) {
        view.stopNestedScroll();
    }

    @SuppressLint({"InlinedApi"})
    /* renamed from: r */
    public static int m18565r(View view) {
        return view.getImportantForAutofill();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: r0 */
    public static void m18566r0(View view, int i9) {
        if (view instanceof InterfaceC4633k) {
            ((InterfaceC4633k) view).stopNestedScroll(i9);
        } else if (i9 == 0) {
            m18564q0(view);
        }
    }

    /* renamed from: s */
    public static int m18567s(View view) {
        return view.getLayoutDirection();
    }

    /* renamed from: t */
    public static int m18568t(View view) {
        return view.getMinimumHeight();
    }

    /* renamed from: u */
    public static int m18569u(View view) {
        return view.getMinimumWidth();
    }

    /* renamed from: v */
    public static int m18570v(View view) {
        return view.getPaddingEnd();
    }

    /* renamed from: w */
    public static int m18571w(View view) {
        return view.getPaddingStart();
    }

    /* renamed from: x */
    public static ViewParent m18572x(View view) {
        return view.getParentForAccessibility();
    }

    /* renamed from: y */
    public static C4628h0 m18573y(View view) {
        return C4628h0.m18432o(h.m18585a(view));
    }

    /* renamed from: z */
    public static String m18574z(View view) {
        return view.getTransitionName();
    }
}
