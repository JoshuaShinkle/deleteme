package androidx.drawerlayout.widget;

import android.R;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;
import android.view.accessibility.AccessibilityEvent;
import androidx.customview.view.AbsSavedState;
import androidx.customview.widget.C0342c;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.util.ArrayList;
import java.util.List;
import p042d0.C4613a;
import p042d0.C4621e;
import p042d0.C4647u;
import p052e0.C4704m;
import p197t.C6273a;
import p224w.C6494a;

/* loaded from: classes.dex */
public class DrawerLayout extends ViewGroup {

    /* renamed from: M */
    public static final int[] f1887M = {R.attr.colorPrimaryDark};

    /* renamed from: N */
    public static final int[] f1888N = {R.attr.layout_gravity};

    /* renamed from: O */
    public static final boolean f1889O = true;

    /* renamed from: P */
    public static final boolean f1890P = true;

    /* renamed from: A */
    public Drawable f1891A;

    /* renamed from: B */
    public CharSequence f1892B;

    /* renamed from: C */
    public CharSequence f1893C;

    /* renamed from: D */
    public Object f1894D;

    /* renamed from: E */
    public boolean f1895E;

    /* renamed from: F */
    public Drawable f1896F;

    /* renamed from: G */
    public Drawable f1897G;

    /* renamed from: H */
    public Drawable f1898H;

    /* renamed from: I */
    public Drawable f1899I;

    /* renamed from: J */
    public final ArrayList<View> f1900J;

    /* renamed from: K */
    public Rect f1901K;

    /* renamed from: L */
    public Matrix f1902L;

    /* renamed from: b */
    public final C0346c f1903b;

    /* renamed from: c */
    public float f1904c;

    /* renamed from: d */
    public int f1905d;

    /* renamed from: e */
    public int f1906e;

    /* renamed from: f */
    public float f1907f;

    /* renamed from: g */
    public Paint f1908g;

    /* renamed from: h */
    public final C0342c f1909h;

    /* renamed from: i */
    public final C0342c f1910i;

    /* renamed from: j */
    public final C0349f f1911j;

    /* renamed from: k */
    public final C0349f f1912k;

    /* renamed from: l */
    public int f1913l;

    /* renamed from: m */
    public boolean f1914m;

    /* renamed from: n */
    public boolean f1915n;

    /* renamed from: o */
    public int f1916o;

    /* renamed from: p */
    public int f1917p;

    /* renamed from: q */
    public int f1918q;

    /* renamed from: r */
    public int f1919r;

    /* renamed from: s */
    public boolean f1920s;

    /* renamed from: t */
    public boolean f1921t;

    /* renamed from: u */
    public InterfaceC0347d f1922u;

    /* renamed from: v */
    public List<InterfaceC0347d> f1923v;

    /* renamed from: w */
    public float f1924w;

    /* renamed from: x */
    public float f1925x;

    /* renamed from: y */
    public Drawable f1926y;

    /* renamed from: z */
    public Drawable f1927z;

    /* renamed from: androidx.drawerlayout.widget.DrawerLayout$a */
    public class ViewOnApplyWindowInsetsListenerC0344a implements View.OnApplyWindowInsetsListener {
        public ViewOnApplyWindowInsetsListenerC0344a() {
        }

        @Override // android.view.View.OnApplyWindowInsetsListener
        public WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
            ((DrawerLayout) view).m1714M(windowInsets, windowInsets.getSystemWindowInsetTop() > 0);
            return windowInsets.consumeSystemWindowInsets();
        }
    }

    /* renamed from: androidx.drawerlayout.widget.DrawerLayout$b */
    public class C0345b extends C4613a {

        /* renamed from: a */
        public final Rect f1934a = new Rect();

        public C0345b() {
        }

        /* renamed from: a */
        public final void m1745a(C4704m c4704m, ViewGroup viewGroup) {
            int childCount = viewGroup.getChildCount();
            for (int i9 = 0; i9 < childCount; i9++) {
                View childAt = viewGroup.getChildAt(i9);
                if (DrawerLayout.m1701y(childAt)) {
                    c4704m.m18791c(childAt);
                }
            }
        }

        /* renamed from: b */
        public final void m1746b(C4704m c4704m, C4704m c4704m2) {
            Rect rect = this.f1934a;
            c4704m2.m18804j(rect);
            c4704m.m18776P(rect);
            c4704m2.m18806k(rect);
            c4704m.m18777Q(rect);
            c4704m.m18826u0(c4704m2.m18770G());
            c4704m.m18805j0(c4704m2.m18819r());
            c4704m.m18781U(c4704m2.m18810m());
            c4704m.m18785Y(c4704m2.m18813o());
            c4704m.m18790b0(c4704m2.m18832z());
            c4704m.m18782V(c4704m2.m18831y());
            c4704m.m18794d0(c4704m2.m18764A());
            c4704m.m18796e0(c4704m2.m18765B());
            c4704m.m18774N(c4704m2.m18827v());
            c4704m.m18816p0(c4704m2.m18769F());
            c4704m.m18801h0(c4704m2.m18766C());
            c4704m.m18787a(c4704m2.m18802i());
        }

        @Override // p042d0.C4613a
        public boolean dispatchPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
            if (accessibilityEvent.getEventType() != 32) {
                return super.dispatchPopulateAccessibilityEvent(view, accessibilityEvent);
            }
            List<CharSequence> text = accessibilityEvent.getText();
            View viewM1732n = DrawerLayout.this.m1732n();
            if (viewM1732n == null) {
                return true;
            }
            CharSequence charSequenceM1735q = DrawerLayout.this.m1735q(DrawerLayout.this.m1736r(viewM1732n));
            if (charSequenceM1735q == null) {
                return true;
            }
            text.add(charSequenceM1735q);
            return true;
        }

        @Override // p042d0.C4613a
        public void onInitializeAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
            super.onInitializeAccessibilityEvent(view, accessibilityEvent);
            accessibilityEvent.setClassName(DrawerLayout.class.getName());
        }

        @Override // p042d0.C4613a
        public void onInitializeAccessibilityNodeInfo(View view, C4704m c4704m) {
            if (DrawerLayout.f1889O) {
                super.onInitializeAccessibilityNodeInfo(view, c4704m);
            } else {
                C4704m c4704mM18760J = C4704m.m18760J(c4704m);
                super.onInitializeAccessibilityNodeInfo(view, c4704mM18760J);
                c4704m.m18820r0(view);
                Object objM18572x = C4647u.m18572x(view);
                if (objM18572x instanceof View) {
                    c4704m.m18809l0((View) objM18572x);
                }
                m1746b(c4704m, c4704mM18760J);
                c4704mM18760J.m18772L();
                m1745a(c4704m, (ViewGroup) view);
            }
            c4704m.m18781U(DrawerLayout.class.getName());
            c4704m.m18794d0(false);
            c4704m.m18796e0(false);
            c4704m.m18773M(C4704m.a.f16425d);
            c4704m.m18773M(C4704m.a.f16426e);
        }

        @Override // p042d0.C4613a
        public boolean onRequestSendAccessibilityEvent(ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent) {
            if (DrawerLayout.f1889O || DrawerLayout.m1701y(view)) {
                return super.onRequestSendAccessibilityEvent(viewGroup, view, accessibilityEvent);
            }
            return false;
        }
    }

    /* renamed from: androidx.drawerlayout.widget.DrawerLayout$c */
    public static final class C0346c extends C4613a {
        @Override // p042d0.C4613a
        public void onInitializeAccessibilityNodeInfo(View view, C4704m c4704m) {
            super.onInitializeAccessibilityNodeInfo(view, c4704m);
            if (DrawerLayout.m1701y(view)) {
                return;
            }
            c4704m.m18809l0(null);
        }
    }

    /* renamed from: androidx.drawerlayout.widget.DrawerLayout$d */
    public interface InterfaceC0347d {
        /* renamed from: a */
        void m1747a(View view);

        /* renamed from: b */
        void m1748b(View view);

        /* renamed from: c */
        void m1749c(int i9);

        /* renamed from: d */
        void m1750d(View view, float f9);
    }

    /* renamed from: androidx.drawerlayout.widget.DrawerLayout$f */
    public class C0349f extends C0342c.c {

        /* renamed from: a */
        public final int f1940a;

        /* renamed from: b */
        public C0342c f1941b;

        /* renamed from: c */
        public final Runnable f1942c = new a();

        /* renamed from: androidx.drawerlayout.widget.DrawerLayout$f$a */
        public class a implements Runnable {
            public a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                C0349f.this.m1752b();
            }
        }

        public C0349f(int i9) {
            this.f1940a = i9;
        }

        /* renamed from: a */
        public final void m1751a() {
            View viewM1730l = DrawerLayout.this.m1730l(this.f1940a == 3 ? 5 : 3);
            if (viewM1730l != null) {
                DrawerLayout.this.m1722d(viewM1730l);
            }
        }

        /* renamed from: b */
        public void m1752b() {
            View viewM1730l;
            int width;
            int iM1696x = this.f1941b.m1696x();
            boolean z8 = this.f1940a == 3;
            if (z8) {
                viewM1730l = DrawerLayout.this.m1730l(3);
                width = (viewM1730l != null ? -viewM1730l.getWidth() : 0) + iM1696x;
            } else {
                viewM1730l = DrawerLayout.this.m1730l(5);
                width = DrawerLayout.this.getWidth() - iM1696x;
            }
            if (viewM1730l != null) {
                if (((!z8 || viewM1730l.getLeft() >= width) && (z8 || viewM1730l.getLeft() <= width)) || DrawerLayout.this.m1734p(viewM1730l) != 0) {
                    return;
                }
                C0348e c0348e = (C0348e) viewM1730l.getLayoutParams();
                this.f1941b.m1673P(viewM1730l, width, viewM1730l.getTop());
                c0348e.f1938c = true;
                DrawerLayout.this.invalidate();
                m1751a();
                DrawerLayout.this.m1720b();
            }
        }

        /* renamed from: c */
        public void m1753c() {
            DrawerLayout.this.removeCallbacks(this.f1942c);
        }

        @Override // androidx.customview.widget.C0342c.c
        public int clampViewPositionHorizontal(View view, int i9, int i10) {
            if (DrawerLayout.this.m1721c(view, 3)) {
                return Math.max(-view.getWidth(), Math.min(i9, 0));
            }
            int width = DrawerLayout.this.getWidth();
            return Math.max(width - view.getWidth(), Math.min(i9, width));
        }

        @Override // androidx.customview.widget.C0342c.c
        public int clampViewPositionVertical(View view, int i9, int i10) {
            return view.getTop();
        }

        /* renamed from: d */
        public void m1754d(C0342c c0342c) {
            this.f1941b = c0342c;
        }

        @Override // androidx.customview.widget.C0342c.c
        public int getViewHorizontalDragRange(View view) {
            if (DrawerLayout.this.m1703B(view)) {
                return view.getWidth();
            }
            return 0;
        }

        @Override // androidx.customview.widget.C0342c.c
        public void onEdgeDragStarted(int i9, int i10) {
            View viewM1730l = (i9 & 1) == 1 ? DrawerLayout.this.m1730l(3) : DrawerLayout.this.m1730l(5);
            if (viewM1730l == null || DrawerLayout.this.m1734p(viewM1730l) != 0) {
                return;
            }
            this.f1941b.m1677c(viewM1730l, i10);
        }

        @Override // androidx.customview.widget.C0342c.c
        public boolean onEdgeLock(int i9) {
            return false;
        }

        @Override // androidx.customview.widget.C0342c.c
        public void onEdgeTouched(int i9, int i10) {
            DrawerLayout.this.postDelayed(this.f1942c, 160L);
        }

        @Override // androidx.customview.widget.C0342c.c
        public void onViewCaptured(View view, int i9) {
            ((C0348e) view.getLayoutParams()).f1938c = false;
            m1751a();
        }

        @Override // androidx.customview.widget.C0342c.c
        public void onViewDragStateChanged(int i9) {
            DrawerLayout.this.m1718Q(this.f1940a, i9, this.f1941b.m1695w());
        }

        @Override // androidx.customview.widget.C0342c.c
        public void onViewPositionChanged(View view, int i9, int i10, int i11, int i12) {
            float width = (DrawerLayout.this.m1721c(view, 3) ? i9 + r3 : DrawerLayout.this.getWidth() - i9) / view.getWidth();
            DrawerLayout.this.m1716O(view, width);
            view.setVisibility(width == BitmapDescriptorFactory.HUE_RED ? 4 : 0);
            DrawerLayout.this.invalidate();
        }

        @Override // androidx.customview.widget.C0342c.c
        public void onViewReleased(View view, float f9, float f10) {
            int i9;
            float fM1737s = DrawerLayout.this.m1737s(view);
            int width = view.getWidth();
            if (DrawerLayout.this.m1721c(view, 3)) {
                i9 = (f9 > BitmapDescriptorFactory.HUE_RED || (f9 == BitmapDescriptorFactory.HUE_RED && fM1737s > 0.5f)) ? 0 : -width;
            } else {
                int width2 = DrawerLayout.this.getWidth();
                if (f9 < BitmapDescriptorFactory.HUE_RED || (f9 == BitmapDescriptorFactory.HUE_RED && fM1737s > 0.5f)) {
                    width2 -= width;
                }
                i9 = width2;
            }
            this.f1941b.m1671N(i9, view.getTop());
            DrawerLayout.this.invalidate();
        }

        @Override // androidx.customview.widget.C0342c.c
        public boolean tryCaptureView(View view, int i9) {
            return DrawerLayout.this.m1703B(view) && DrawerLayout.this.m1721c(view, this.f1940a) && DrawerLayout.this.m1734p(view) == 0;
        }
    }

    public DrawerLayout(Context context, AttributeSet attributeSet, int i9) {
        super(context, attributeSet, i9);
        this.f1903b = new C0346c();
        this.f1906e = -1728053248;
        this.f1908g = new Paint();
        this.f1915n = true;
        this.f1916o = 3;
        this.f1917p = 3;
        this.f1918q = 3;
        this.f1919r = 3;
        this.f1896F = null;
        this.f1897G = null;
        this.f1898H = null;
        this.f1899I = null;
        setDescendantFocusability(262144);
        float f9 = getResources().getDisplayMetrics().density;
        this.f1905d = (int) ((64.0f * f9) + 0.5f);
        float f10 = 400.0f * f9;
        C0349f c0349f = new C0349f(3);
        this.f1911j = c0349f;
        C0349f c0349f2 = new C0349f(5);
        this.f1912k = c0349f2;
        C0342c c0342cM1656o = C0342c.m1656o(this, 1.0f, c0349f);
        this.f1909h = c0342cM1656o;
        c0342cM1656o.m1669L(1);
        c0342cM1656o.m1670M(f10);
        c0349f.m1754d(c0342cM1656o);
        C0342c c0342cM1656o2 = C0342c.m1656o(this, 1.0f, c0349f2);
        this.f1910i = c0342cM1656o2;
        c0342cM1656o2.m1669L(2);
        c0342cM1656o2.m1670M(f10);
        c0349f2.m1754d(c0342cM1656o2);
        setFocusableInTouchMode(true);
        C4647u.m18548i0(this, 1);
        C4647u.m18530Z(this, new C0345b());
        setMotionEventSplittingEnabled(false);
        if (C4647u.m18561p(this)) {
            setOnApplyWindowInsetsListener(new ViewOnApplyWindowInsetsListenerC0344a());
            setSystemUiVisibility(1280);
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(f1887M);
            try {
                this.f1926y = typedArrayObtainStyledAttributes.getDrawable(0);
            } finally {
                typedArrayObtainStyledAttributes.recycle();
            }
        }
        this.f1904c = f9 * 10.0f;
        this.f1900J = new ArrayList<>();
    }

    /* renamed from: u */
    public static String m1699u(int i9) {
        return (i9 & 3) == 3 ? "LEFT" : (i9 & 5) == 5 ? "RIGHT" : Integer.toHexString(i9);
    }

    /* renamed from: v */
    public static boolean m1700v(View view) {
        Drawable background = view.getBackground();
        return background != null && background.getOpacity() == -1;
    }

    /* renamed from: y */
    public static boolean m1701y(View view) {
        return (C4647u.m18563q(view) == 4 || C4647u.m18563q(view) == 2) ? false : true;
    }

    /* renamed from: A */
    public boolean m1702A(View view) {
        if (m1703B(view)) {
            return (((C0348e) view.getLayoutParams()).f1939d & 1) == 1;
        }
        throw new IllegalArgumentException("View " + view + " is not a drawer");
    }

    /* renamed from: B */
    public boolean m1703B(View view) {
        int iM18420b = C4621e.m18420b(((C0348e) view.getLayoutParams()).f1936a, C4647u.m18567s(view));
        return ((iM18420b & 3) == 0 && (iM18420b & 5) == 0) ? false : true;
    }

    /* renamed from: C */
    public boolean m1704C(View view) {
        if (m1703B(view)) {
            return ((C0348e) view.getLayoutParams()).f1937b > BitmapDescriptorFactory.HUE_RED;
        }
        throw new IllegalArgumentException("View " + view + " is not a drawer");
    }

    /* renamed from: D */
    public final boolean m1705D(float f9, float f10, View view) {
        if (this.f1901K == null) {
            this.f1901K = new Rect();
        }
        view.getHitRect(this.f1901K);
        return this.f1901K.contains((int) f9, (int) f10);
    }

    /* renamed from: E */
    public final boolean m1706E(Drawable drawable, int i9) {
        if (drawable == null || !C6494a.m24840c(drawable)) {
            return false;
        }
        C6494a.m24844g(drawable, i9);
        return true;
    }

    /* renamed from: F */
    public void m1707F(View view, float f9) {
        float fM1737s = m1737s(view);
        float width = view.getWidth();
        int i9 = ((int) (width * f9)) - ((int) (fM1737s * width));
        if (!m1721c(view, 3)) {
            i9 = -i9;
        }
        view.offsetLeftAndRight(i9);
        m1716O(view, f9);
    }

    /* renamed from: G */
    public void m1708G(View view) {
        m1709H(view, true);
    }

    /* renamed from: H */
    public void m1709H(View view, boolean z8) {
        if (!m1703B(view)) {
            throw new IllegalArgumentException("View " + view + " is not a sliding drawer");
        }
        C0348e c0348e = (C0348e) view.getLayoutParams();
        if (this.f1915n) {
            c0348e.f1937b = 1.0f;
            c0348e.f1939d = 1;
            m1717P(view, true);
        } else if (z8) {
            c0348e.f1939d |= 2;
            if (m1721c(view, 3)) {
                this.f1909h.m1673P(view, 0, view.getTop());
            } else {
                this.f1910i.m1673P(view, getWidth() - view.getWidth(), view.getTop());
            }
        } else {
            m1707F(view, 1.0f);
            m1718Q(c0348e.f1936a, 0, view);
            view.setVisibility(0);
        }
        invalidate();
    }

    /* renamed from: I */
    public void m1710I(InterfaceC0347d interfaceC0347d) {
        List<InterfaceC0347d> list;
        if (interfaceC0347d == null || (list = this.f1923v) == null) {
            return;
        }
        list.remove(interfaceC0347d);
    }

    /* renamed from: J */
    public final Drawable m1711J() {
        int iM18567s = C4647u.m18567s(this);
        if (iM18567s == 0) {
            Drawable drawable = this.f1896F;
            if (drawable != null) {
                m1706E(drawable, iM18567s);
                return this.f1896F;
            }
        } else {
            Drawable drawable2 = this.f1897G;
            if (drawable2 != null) {
                m1706E(drawable2, iM18567s);
                return this.f1897G;
            }
        }
        return this.f1898H;
    }

    /* renamed from: K */
    public final Drawable m1712K() {
        int iM18567s = C4647u.m18567s(this);
        if (iM18567s == 0) {
            Drawable drawable = this.f1897G;
            if (drawable != null) {
                m1706E(drawable, iM18567s);
                return this.f1897G;
            }
        } else {
            Drawable drawable2 = this.f1896F;
            if (drawable2 != null) {
                m1706E(drawable2, iM18567s);
                return this.f1896F;
            }
        }
        return this.f1899I;
    }

    /* renamed from: L */
    public final void m1713L() {
        if (f1890P) {
            return;
        }
        this.f1927z = m1711J();
        this.f1891A = m1712K();
    }

    /* renamed from: M */
    public void m1714M(Object obj, boolean z8) {
        this.f1894D = obj;
        this.f1895E = z8;
        setWillNotDraw(!z8 && getBackground() == null);
        requestLayout();
    }

    /* renamed from: N */
    public void m1715N(int i9, int i10) {
        View viewM1730l;
        int iM18420b = C4621e.m18420b(i10, C4647u.m18567s(this));
        if (i10 == 3) {
            this.f1916o = i9;
        } else if (i10 == 5) {
            this.f1917p = i9;
        } else if (i10 == 8388611) {
            this.f1918q = i9;
        } else if (i10 == 8388613) {
            this.f1919r = i9;
        }
        if (i9 != 0) {
            (iM18420b == 3 ? this.f1909h : this.f1910i).m1676b();
        }
        if (i9 != 1) {
            if (i9 == 2 && (viewM1730l = m1730l(iM18420b)) != null) {
                m1708G(viewM1730l);
                return;
            }
            return;
        }
        View viewM1730l2 = m1730l(iM18420b);
        if (viewM1730l2 != null) {
            m1722d(viewM1730l2);
        }
    }

    /* renamed from: O */
    public void m1716O(View view, float f9) {
        C0348e c0348e = (C0348e) view.getLayoutParams();
        if (f9 == c0348e.f1937b) {
            return;
        }
        c0348e.f1937b = f9;
        m1728j(view, f9);
    }

    /* renamed from: P */
    public final void m1717P(View view, boolean z8) {
        int childCount = getChildCount();
        for (int i9 = 0; i9 < childCount; i9++) {
            View childAt = getChildAt(i9);
            if ((z8 || m1703B(childAt)) && !(z8 && childAt == view)) {
                C4647u.m18548i0(childAt, 4);
            } else {
                C4647u.m18548i0(childAt, 1);
            }
        }
    }

    /* renamed from: Q */
    public void m1718Q(int i9, int i10, View view) {
        int i11;
        int iM1658A = this.f1909h.m1658A();
        int iM1658A2 = this.f1910i.m1658A();
        if (iM1658A == 1 || iM1658A2 == 1) {
            i11 = 1;
        } else {
            i11 = 2;
            if (iM1658A != 2 && iM1658A2 != 2) {
                i11 = 0;
            }
        }
        if (view != null && i10 == 0) {
            float f9 = ((C0348e) view.getLayoutParams()).f1937b;
            if (f9 == BitmapDescriptorFactory.HUE_RED) {
                m1726h(view);
            } else if (f9 == 1.0f) {
                m1727i(view);
            }
        }
        if (i11 != this.f1913l) {
            this.f1913l = i11;
            List<InterfaceC0347d> list = this.f1923v;
            if (list != null) {
                for (int size = list.size() - 1; size >= 0; size--) {
                    this.f1923v.get(size).m1749c(i11);
                }
            }
        }
    }

    /* renamed from: a */
    public void m1719a(InterfaceC0347d interfaceC0347d) {
        if (interfaceC0347d == null) {
            return;
        }
        if (this.f1923v == null) {
            this.f1923v = new ArrayList();
        }
        this.f1923v.add(interfaceC0347d);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void addFocusables(ArrayList<View> arrayList, int i9, int i10) {
        if (getDescendantFocusability() == 393216) {
            return;
        }
        int childCount = getChildCount();
        boolean z8 = false;
        for (int i11 = 0; i11 < childCount; i11++) {
            View childAt = getChildAt(i11);
            if (!m1703B(childAt)) {
                this.f1900J.add(childAt);
            } else if (m1702A(childAt)) {
                childAt.addFocusables(arrayList, i9, i10);
                z8 = true;
            }
        }
        if (!z8) {
            int size = this.f1900J.size();
            for (int i12 = 0; i12 < size; i12++) {
                View view = this.f1900J.get(i12);
                if (view.getVisibility() == 0) {
                    view.addFocusables(arrayList, i9, i10);
                }
            }
        }
        this.f1900J.clear();
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int i9, ViewGroup.LayoutParams layoutParams) {
        super.addView(view, i9, layoutParams);
        if (m1731m() != null || m1703B(view)) {
            C4647u.m18548i0(view, 4);
        } else {
            C4647u.m18548i0(view, 1);
        }
        if (f1889O) {
            return;
        }
        C4647u.m18530Z(view, this.f1903b);
    }

    /* renamed from: b */
    public void m1720b() {
        if (this.f1921t) {
            return;
        }
        long jUptimeMillis = SystemClock.uptimeMillis();
        MotionEvent motionEventObtain = MotionEvent.obtain(jUptimeMillis, jUptimeMillis, 3, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, 0);
        int childCount = getChildCount();
        for (int i9 = 0; i9 < childCount; i9++) {
            getChildAt(i9).dispatchTouchEvent(motionEventObtain);
        }
        motionEventObtain.recycle();
        this.f1921t = true;
    }

    /* renamed from: c */
    public boolean m1721c(View view, int i9) {
        return (m1736r(view) & i9) == i9;
    }

    @Override // android.view.ViewGroup
    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return (layoutParams instanceof C0348e) && super.checkLayoutParams(layoutParams);
    }

    @Override // android.view.View
    public void computeScroll() {
        int childCount = getChildCount();
        float fMax = BitmapDescriptorFactory.HUE_RED;
        for (int i9 = 0; i9 < childCount; i9++) {
            fMax = Math.max(fMax, ((C0348e) getChildAt(i9).getLayoutParams()).f1937b);
        }
        this.f1907f = fMax;
        boolean zM1688n = this.f1909h.m1688n(true);
        boolean zM1688n2 = this.f1910i.m1688n(true);
        if (zM1688n || zM1688n2) {
            C4647u.m18524T(this);
        }
    }

    /* renamed from: d */
    public void m1722d(View view) {
        m1723e(view, true);
    }

    @Override // android.view.View
    public boolean dispatchGenericMotionEvent(MotionEvent motionEvent) {
        if ((motionEvent.getSource() & 2) == 0 || motionEvent.getAction() == 10 || this.f1907f <= BitmapDescriptorFactory.HUE_RED) {
            return super.dispatchGenericMotionEvent(motionEvent);
        }
        int childCount = getChildCount();
        if (childCount == 0) {
            return false;
        }
        float x8 = motionEvent.getX();
        float y8 = motionEvent.getY();
        for (int i9 = childCount - 1; i9 >= 0; i9--) {
            View childAt = getChildAt(i9);
            if (m1705D(x8, y8, childAt) && !m1741z(childAt) && m1729k(motionEvent, childAt)) {
                return true;
            }
        }
        return false;
    }

    @Override // android.view.ViewGroup
    public boolean drawChild(Canvas canvas, View view, long j9) {
        int height = getHeight();
        boolean zM1741z = m1741z(view);
        int width = getWidth();
        int iSave = canvas.save();
        int i9 = 0;
        if (zM1741z) {
            int childCount = getChildCount();
            int i10 = 0;
            for (int i11 = 0; i11 < childCount; i11++) {
                View childAt = getChildAt(i11);
                if (childAt != view && childAt.getVisibility() == 0 && m1700v(childAt) && m1703B(childAt) && childAt.getHeight() >= height) {
                    if (m1721c(childAt, 3)) {
                        int right = childAt.getRight();
                        if (right > i10) {
                            i10 = right;
                        }
                    } else {
                        int left = childAt.getLeft();
                        if (left < width) {
                            width = left;
                        }
                    }
                }
            }
            canvas.clipRect(i10, 0, width, getHeight());
            i9 = i10;
        }
        boolean zDrawChild = super.drawChild(canvas, view, j9);
        canvas.restoreToCount(iSave);
        float f9 = this.f1907f;
        if (f9 > BitmapDescriptorFactory.HUE_RED && zM1741z) {
            this.f1908g.setColor((this.f1906e & 16777215) | (((int) ((((-16777216) & r2) >>> 24) * f9)) << 24));
            canvas.drawRect(i9, BitmapDescriptorFactory.HUE_RED, width, getHeight(), this.f1908g);
        } else if (this.f1927z != null && m1721c(view, 3)) {
            int intrinsicWidth = this.f1927z.getIntrinsicWidth();
            int right2 = view.getRight();
            float fMax = Math.max(BitmapDescriptorFactory.HUE_RED, Math.min(right2 / this.f1909h.m1696x(), 1.0f));
            this.f1927z.setBounds(right2, view.getTop(), intrinsicWidth + right2, view.getBottom());
            this.f1927z.setAlpha((int) (fMax * 255.0f));
            this.f1927z.draw(canvas);
        } else if (this.f1891A != null && m1721c(view, 5)) {
            int intrinsicWidth2 = this.f1891A.getIntrinsicWidth();
            int left2 = view.getLeft();
            float fMax2 = Math.max(BitmapDescriptorFactory.HUE_RED, Math.min((getWidth() - left2) / this.f1910i.m1696x(), 1.0f));
            this.f1891A.setBounds(left2 - intrinsicWidth2, view.getTop(), left2, view.getBottom());
            this.f1891A.setAlpha((int) (fMax2 * 255.0f));
            this.f1891A.draw(canvas);
        }
        return zDrawChild;
    }

    /* renamed from: e */
    public void m1723e(View view, boolean z8) {
        if (!m1703B(view)) {
            throw new IllegalArgumentException("View " + view + " is not a sliding drawer");
        }
        C0348e c0348e = (C0348e) view.getLayoutParams();
        if (this.f1915n) {
            c0348e.f1937b = BitmapDescriptorFactory.HUE_RED;
            c0348e.f1939d = 0;
        } else if (z8) {
            c0348e.f1939d |= 4;
            if (m1721c(view, 3)) {
                this.f1909h.m1673P(view, -view.getWidth(), view.getTop());
            } else {
                this.f1910i.m1673P(view, getWidth(), view.getTop());
            }
        } else {
            m1707F(view, BitmapDescriptorFactory.HUE_RED);
            m1718Q(c0348e.f1936a, 0, view);
            view.setVisibility(4);
        }
        invalidate();
    }

    /* renamed from: f */
    public void m1724f() {
        m1725g(false);
    }

    /* renamed from: g */
    public void m1725g(boolean z8) {
        int childCount = getChildCount();
        boolean zM1673P = false;
        for (int i9 = 0; i9 < childCount; i9++) {
            View childAt = getChildAt(i9);
            C0348e c0348e = (C0348e) childAt.getLayoutParams();
            if (m1703B(childAt) && (!z8 || c0348e.f1938c)) {
                zM1673P |= m1721c(childAt, 3) ? this.f1909h.m1673P(childAt, -childAt.getWidth(), childAt.getTop()) : this.f1910i.m1673P(childAt, getWidth(), childAt.getTop());
                c0348e.f1938c = false;
            }
        }
        this.f1911j.m1753c();
        this.f1912k.m1753c();
        if (zM1673P) {
            invalidate();
        }
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new C0348e(-1, -1);
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof C0348e ? new C0348e((C0348e) layoutParams) : layoutParams instanceof ViewGroup.MarginLayoutParams ? new C0348e((ViewGroup.MarginLayoutParams) layoutParams) : new C0348e(layoutParams);
    }

    public float getDrawerElevation() {
        return f1890P ? this.f1904c : BitmapDescriptorFactory.HUE_RED;
    }

    public Drawable getStatusBarBackgroundDrawable() {
        return this.f1926y;
    }

    /* renamed from: h */
    public void m1726h(View view) {
        View rootView;
        C0348e c0348e = (C0348e) view.getLayoutParams();
        if ((c0348e.f1939d & 1) == 1) {
            c0348e.f1939d = 0;
            List<InterfaceC0347d> list = this.f1923v;
            if (list != null) {
                for (int size = list.size() - 1; size >= 0; size--) {
                    this.f1923v.get(size).m1748b(view);
                }
            }
            m1717P(view, false);
            if (!hasWindowFocus() || (rootView = getRootView()) == null) {
                return;
            }
            rootView.sendAccessibilityEvent(32);
        }
    }

    /* renamed from: i */
    public void m1727i(View view) {
        C0348e c0348e = (C0348e) view.getLayoutParams();
        if ((c0348e.f1939d & 1) == 0) {
            c0348e.f1939d = 1;
            List<InterfaceC0347d> list = this.f1923v;
            if (list != null) {
                for (int size = list.size() - 1; size >= 0; size--) {
                    this.f1923v.get(size).m1747a(view);
                }
            }
            m1717P(view, true);
            if (hasWindowFocus()) {
                sendAccessibilityEvent(32);
            }
        }
    }

    /* renamed from: j */
    public void m1728j(View view, float f9) {
        List<InterfaceC0347d> list = this.f1923v;
        if (list != null) {
            for (int size = list.size() - 1; size >= 0; size--) {
                this.f1923v.get(size).m1750d(view, f9);
            }
        }
    }

    /* renamed from: k */
    public final boolean m1729k(MotionEvent motionEvent, View view) {
        if (!view.getMatrix().isIdentity()) {
            MotionEvent motionEventM1738t = m1738t(motionEvent, view);
            boolean zDispatchGenericMotionEvent = view.dispatchGenericMotionEvent(motionEventM1738t);
            motionEventM1738t.recycle();
            return zDispatchGenericMotionEvent;
        }
        float scrollX = getScrollX() - view.getLeft();
        float scrollY = getScrollY() - view.getTop();
        motionEvent.offsetLocation(scrollX, scrollY);
        boolean zDispatchGenericMotionEvent2 = view.dispatchGenericMotionEvent(motionEvent);
        motionEvent.offsetLocation(-scrollX, -scrollY);
        return zDispatchGenericMotionEvent2;
    }

    /* renamed from: l */
    public View m1730l(int i9) {
        int iM18420b = C4621e.m18420b(i9, C4647u.m18567s(this)) & 7;
        int childCount = getChildCount();
        for (int i10 = 0; i10 < childCount; i10++) {
            View childAt = getChildAt(i10);
            if ((m1736r(childAt) & 7) == iM18420b) {
                return childAt;
            }
        }
        return null;
    }

    /* renamed from: m */
    public View m1731m() {
        int childCount = getChildCount();
        for (int i9 = 0; i9 < childCount; i9++) {
            View childAt = getChildAt(i9);
            if ((((C0348e) childAt.getLayoutParams()).f1939d & 1) == 1) {
                return childAt;
            }
        }
        return null;
    }

    /* renamed from: n */
    public View m1732n() {
        int childCount = getChildCount();
        for (int i9 = 0; i9 < childCount; i9++) {
            View childAt = getChildAt(i9);
            if (m1703B(childAt) && m1704C(childAt)) {
                return childAt;
            }
        }
        return null;
    }

    /* renamed from: o */
    public int m1733o(int i9) {
        int iM18567s = C4647u.m18567s(this);
        if (i9 == 3) {
            int i10 = this.f1916o;
            if (i10 != 3) {
                return i10;
            }
            int i11 = iM18567s == 0 ? this.f1918q : this.f1919r;
            if (i11 != 3) {
                return i11;
            }
            return 0;
        }
        if (i9 == 5) {
            int i12 = this.f1917p;
            if (i12 != 3) {
                return i12;
            }
            int i13 = iM18567s == 0 ? this.f1919r : this.f1918q;
            if (i13 != 3) {
                return i13;
            }
            return 0;
        }
        if (i9 == 8388611) {
            int i14 = this.f1918q;
            if (i14 != 3) {
                return i14;
            }
            int i15 = iM18567s == 0 ? this.f1916o : this.f1917p;
            if (i15 != 3) {
                return i15;
            }
            return 0;
        }
        if (i9 != 8388613) {
            return 0;
        }
        int i16 = this.f1919r;
        if (i16 != 3) {
            return i16;
        }
        int i17 = iM18567s == 0 ? this.f1917p : this.f1916o;
        if (i17 != 3) {
            return i17;
        }
        return 0;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.f1915n = true;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.f1915n = true;
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (!this.f1895E || this.f1926y == null) {
            return;
        }
        Object obj = this.f1894D;
        int systemWindowInsetTop = obj != null ? ((WindowInsets) obj).getSystemWindowInsetTop() : 0;
        if (systemWindowInsetTop > 0) {
            this.f1926y.setBounds(0, 0, getWidth(), systemWindowInsetTop);
            this.f1926y.draw(canvas);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0031  */
    @Override // android.view.ViewGroup
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        boolean z8;
        View viewM1693u;
        int actionMasked = motionEvent.getActionMasked();
        boolean zM1672O = this.f1909h.m1672O(motionEvent) | this.f1910i.m1672O(motionEvent);
        if (actionMasked == 0) {
            float x8 = motionEvent.getX();
            float y8 = motionEvent.getY();
            this.f1924w = x8;
            this.f1925x = y8;
            z8 = this.f1907f > BitmapDescriptorFactory.HUE_RED && (viewM1693u = this.f1909h.m1693u((int) x8, (int) y8)) != null && m1741z(viewM1693u);
            this.f1920s = false;
            this.f1921t = false;
        } else if (actionMasked == 1) {
            m1725g(true);
            this.f1920s = false;
            this.f1921t = false;
            z8 = false;
        } else {
            if (actionMasked != 2) {
                if (actionMasked == 3) {
                }
            } else if (this.f1909h.m1679e(3)) {
                this.f1911j.m1753c();
                this.f1912k.m1753c();
            }
            z8 = false;
        }
        return zM1672O || z8 || m1739w() || this.f1921t;
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i9, KeyEvent keyEvent) {
        if (i9 != 4 || !m1740x()) {
            return super.onKeyDown(i9, keyEvent);
        }
        keyEvent.startTracking();
        return true;
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyUp(int i9, KeyEvent keyEvent) {
        if (i9 != 4) {
            return super.onKeyUp(i9, keyEvent);
        }
        View viewM1732n = m1732n();
        if (viewM1732n != null && m1734p(viewM1732n) == 0) {
            m1724f();
        }
        return viewM1732n != null;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean z8, int i9, int i10, int i11, int i12) {
        float f9;
        int i13;
        boolean z9 = true;
        this.f1914m = true;
        int i14 = i11 - i9;
        int childCount = getChildCount();
        int i15 = 0;
        while (i15 < childCount) {
            View childAt = getChildAt(i15);
            if (childAt.getVisibility() != 8) {
                C0348e c0348e = (C0348e) childAt.getLayoutParams();
                if (m1741z(childAt)) {
                    int i16 = ((ViewGroup.MarginLayoutParams) c0348e).leftMargin;
                    childAt.layout(i16, ((ViewGroup.MarginLayoutParams) c0348e).topMargin, childAt.getMeasuredWidth() + i16, ((ViewGroup.MarginLayoutParams) c0348e).topMargin + childAt.getMeasuredHeight());
                } else {
                    int measuredWidth = childAt.getMeasuredWidth();
                    int measuredHeight = childAt.getMeasuredHeight();
                    if (m1721c(childAt, 3)) {
                        float f10 = measuredWidth;
                        i13 = (-measuredWidth) + ((int) (c0348e.f1937b * f10));
                        f9 = (measuredWidth + i13) / f10;
                    } else {
                        float f11 = measuredWidth;
                        f9 = (i14 - r11) / f11;
                        i13 = i14 - ((int) (c0348e.f1937b * f11));
                    }
                    boolean z10 = f9 != c0348e.f1937b ? z9 : false;
                    int i17 = c0348e.f1936a & 112;
                    if (i17 == 16) {
                        int i18 = i12 - i10;
                        int i19 = (i18 - measuredHeight) / 2;
                        int i20 = ((ViewGroup.MarginLayoutParams) c0348e).topMargin;
                        if (i19 < i20) {
                            i19 = i20;
                        } else {
                            int i21 = i19 + measuredHeight;
                            int i22 = ((ViewGroup.MarginLayoutParams) c0348e).bottomMargin;
                            if (i21 > i18 - i22) {
                                i19 = (i18 - i22) - measuredHeight;
                            }
                        }
                        childAt.layout(i13, i19, measuredWidth + i13, measuredHeight + i19);
                    } else if (i17 != 80) {
                        int i23 = ((ViewGroup.MarginLayoutParams) c0348e).topMargin;
                        childAt.layout(i13, i23, measuredWidth + i13, measuredHeight + i23);
                    } else {
                        int i24 = i12 - i10;
                        childAt.layout(i13, (i24 - ((ViewGroup.MarginLayoutParams) c0348e).bottomMargin) - childAt.getMeasuredHeight(), measuredWidth + i13, i24 - ((ViewGroup.MarginLayoutParams) c0348e).bottomMargin);
                    }
                    if (z10) {
                        m1716O(childAt, f9);
                    }
                    int i25 = c0348e.f1937b > BitmapDescriptorFactory.HUE_RED ? 0 : 4;
                    if (childAt.getVisibility() != i25) {
                        childAt.setVisibility(i25);
                    }
                }
            }
            i15++;
            z9 = true;
        }
        this.f1914m = false;
        this.f1915n = false;
    }

    @Override // android.view.View
    @SuppressLint({"WrongConstant"})
    public void onMeasure(int i9, int i10) {
        int mode = View.MeasureSpec.getMode(i9);
        int mode2 = View.MeasureSpec.getMode(i10);
        int size = View.MeasureSpec.getSize(i9);
        int size2 = View.MeasureSpec.getSize(i10);
        if (mode != 1073741824 || mode2 != 1073741824) {
            if (!isInEditMode()) {
                throw new IllegalArgumentException("DrawerLayout must be measured with MeasureSpec.EXACTLY.");
            }
            if (mode != Integer.MIN_VALUE && mode == 0) {
                size = 300;
            }
            if (mode2 != Integer.MIN_VALUE && mode2 == 0) {
                size2 = 300;
            }
        }
        setMeasuredDimension(size, size2);
        boolean z8 = this.f1894D != null && C4647u.m18561p(this);
        int iM18567s = C4647u.m18567s(this);
        int childCount = getChildCount();
        boolean z9 = false;
        boolean z10 = false;
        for (int i11 = 0; i11 < childCount; i11++) {
            View childAt = getChildAt(i11);
            if (childAt.getVisibility() != 8) {
                C0348e c0348e = (C0348e) childAt.getLayoutParams();
                if (z8) {
                    int iM18420b = C4621e.m18420b(c0348e.f1936a, iM18567s);
                    if (C4647u.m18561p(childAt)) {
                        WindowInsets windowInsetsReplaceSystemWindowInsets = (WindowInsets) this.f1894D;
                        if (iM18420b == 3) {
                            windowInsetsReplaceSystemWindowInsets = windowInsetsReplaceSystemWindowInsets.replaceSystemWindowInsets(windowInsetsReplaceSystemWindowInsets.getSystemWindowInsetLeft(), windowInsetsReplaceSystemWindowInsets.getSystemWindowInsetTop(), 0, windowInsetsReplaceSystemWindowInsets.getSystemWindowInsetBottom());
                        } else if (iM18420b == 5) {
                            windowInsetsReplaceSystemWindowInsets = windowInsetsReplaceSystemWindowInsets.replaceSystemWindowInsets(0, windowInsetsReplaceSystemWindowInsets.getSystemWindowInsetTop(), windowInsetsReplaceSystemWindowInsets.getSystemWindowInsetRight(), windowInsetsReplaceSystemWindowInsets.getSystemWindowInsetBottom());
                        }
                        childAt.dispatchApplyWindowInsets(windowInsetsReplaceSystemWindowInsets);
                    } else {
                        WindowInsets windowInsetsReplaceSystemWindowInsets2 = (WindowInsets) this.f1894D;
                        if (iM18420b == 3) {
                            windowInsetsReplaceSystemWindowInsets2 = windowInsetsReplaceSystemWindowInsets2.replaceSystemWindowInsets(windowInsetsReplaceSystemWindowInsets2.getSystemWindowInsetLeft(), windowInsetsReplaceSystemWindowInsets2.getSystemWindowInsetTop(), 0, windowInsetsReplaceSystemWindowInsets2.getSystemWindowInsetBottom());
                        } else if (iM18420b == 5) {
                            windowInsetsReplaceSystemWindowInsets2 = windowInsetsReplaceSystemWindowInsets2.replaceSystemWindowInsets(0, windowInsetsReplaceSystemWindowInsets2.getSystemWindowInsetTop(), windowInsetsReplaceSystemWindowInsets2.getSystemWindowInsetRight(), windowInsetsReplaceSystemWindowInsets2.getSystemWindowInsetBottom());
                        }
                        ((ViewGroup.MarginLayoutParams) c0348e).leftMargin = windowInsetsReplaceSystemWindowInsets2.getSystemWindowInsetLeft();
                        ((ViewGroup.MarginLayoutParams) c0348e).topMargin = windowInsetsReplaceSystemWindowInsets2.getSystemWindowInsetTop();
                        ((ViewGroup.MarginLayoutParams) c0348e).rightMargin = windowInsetsReplaceSystemWindowInsets2.getSystemWindowInsetRight();
                        ((ViewGroup.MarginLayoutParams) c0348e).bottomMargin = windowInsetsReplaceSystemWindowInsets2.getSystemWindowInsetBottom();
                    }
                }
                if (m1741z(childAt)) {
                    childAt.measure(View.MeasureSpec.makeMeasureSpec((size - ((ViewGroup.MarginLayoutParams) c0348e).leftMargin) - ((ViewGroup.MarginLayoutParams) c0348e).rightMargin, 1073741824), View.MeasureSpec.makeMeasureSpec((size2 - ((ViewGroup.MarginLayoutParams) c0348e).topMargin) - ((ViewGroup.MarginLayoutParams) c0348e).bottomMargin, 1073741824));
                } else {
                    if (!m1703B(childAt)) {
                        throw new IllegalStateException("Child " + childAt + " at index " + i11 + " does not have a valid layout_gravity - must be Gravity.LEFT, Gravity.RIGHT or Gravity.NO_GRAVITY");
                    }
                    if (f1890P) {
                        float fM18559o = C4647u.m18559o(childAt);
                        float f9 = this.f1904c;
                        if (fM18559o != f9) {
                            C4647u.m18542f0(childAt, f9);
                        }
                    }
                    int iM1736r = m1736r(childAt) & 7;
                    boolean z11 = iM1736r == 3;
                    if ((z11 && z9) || (!z11 && z10)) {
                        throw new IllegalStateException("Child drawer has absolute gravity " + m1699u(iM1736r) + " but this DrawerLayout already has a drawer view along that edge");
                    }
                    if (z11) {
                        z9 = true;
                    } else {
                        z10 = true;
                    }
                    childAt.measure(ViewGroup.getChildMeasureSpec(i9, this.f1905d + ((ViewGroup.MarginLayoutParams) c0348e).leftMargin + ((ViewGroup.MarginLayoutParams) c0348e).rightMargin, ((ViewGroup.MarginLayoutParams) c0348e).width), ViewGroup.getChildMeasureSpec(i10, ((ViewGroup.MarginLayoutParams) c0348e).topMargin + ((ViewGroup.MarginLayoutParams) c0348e).bottomMargin, ((ViewGroup.MarginLayoutParams) c0348e).height));
                }
            }
        }
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        View viewM1730l;
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        int i9 = savedState.f1928b;
        if (i9 != 0 && (viewM1730l = m1730l(i9)) != null) {
            m1708G(viewM1730l);
        }
        int i10 = savedState.f1929c;
        if (i10 != 3) {
            m1715N(i10, 3);
        }
        int i11 = savedState.f1930d;
        if (i11 != 3) {
            m1715N(i11, 5);
        }
        int i12 = savedState.f1931e;
        if (i12 != 3) {
            m1715N(i12, 8388611);
        }
        int i13 = savedState.f1932f;
        if (i13 != 3) {
            m1715N(i13, 8388613);
        }
    }

    @Override // android.view.View
    public void onRtlPropertiesChanged(int i9) {
        m1713L();
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        int childCount = getChildCount();
        for (int i9 = 0; i9 < childCount; i9++) {
            C0348e c0348e = (C0348e) getChildAt(i9).getLayoutParams();
            int i10 = c0348e.f1939d;
            boolean z8 = i10 == 1;
            boolean z9 = i10 == 2;
            if (z8 || z9) {
                savedState.f1928b = c0348e.f1936a;
                break;
            }
        }
        savedState.f1929c = this.f1916o;
        savedState.f1930d = this.f1917p;
        savedState.f1931e = this.f1918q;
        savedState.f1932f = this.f1919r;
        return savedState;
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x005f  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public boolean onTouchEvent(MotionEvent motionEvent) {
        boolean z8;
        View viewM1731m;
        this.f1909h.m1663F(motionEvent);
        this.f1910i.m1663F(motionEvent);
        int action = motionEvent.getAction() & 255;
        if (action == 0) {
            float x8 = motionEvent.getX();
            float y8 = motionEvent.getY();
            this.f1924w = x8;
            this.f1925x = y8;
            this.f1920s = false;
            this.f1921t = false;
        } else if (action == 1) {
            float x9 = motionEvent.getX();
            float y9 = motionEvent.getY();
            View viewM1693u = this.f1909h.m1693u((int) x9, (int) y9);
            if (viewM1693u == null || !m1741z(viewM1693u)) {
                z8 = true;
                m1725g(z8);
                this.f1920s = false;
            } else {
                float f9 = x9 - this.f1924w;
                float f10 = y9 - this.f1925x;
                int iM1698z = this.f1909h.m1698z();
                if ((f9 * f9) + (f10 * f10) < iM1698z * iM1698z && (viewM1731m = m1731m()) != null && m1734p(viewM1731m) != 2) {
                    z8 = false;
                }
                m1725g(z8);
                this.f1920s = false;
            }
        } else if (action == 3) {
            m1725g(true);
            this.f1920s = false;
            this.f1921t = false;
        }
        return true;
    }

    /* renamed from: p */
    public int m1734p(View view) {
        if (m1703B(view)) {
            return m1733o(((C0348e) view.getLayoutParams()).f1936a);
        }
        throw new IllegalArgumentException("View " + view + " is not a drawer");
    }

    /* renamed from: q */
    public CharSequence m1735q(int i9) {
        int iM18420b = C4621e.m18420b(i9, C4647u.m18567s(this));
        if (iM18420b == 3) {
            return this.f1892B;
        }
        if (iM18420b == 5) {
            return this.f1893C;
        }
        return null;
    }

    /* renamed from: r */
    public int m1736r(View view) {
        return C4621e.m18420b(((C0348e) view.getLayoutParams()).f1936a, C4647u.m18567s(this));
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void requestDisallowInterceptTouchEvent(boolean z8) {
        super.requestDisallowInterceptTouchEvent(z8);
        this.f1920s = z8;
        if (z8) {
            m1725g(true);
        }
    }

    @Override // android.view.View, android.view.ViewParent
    public void requestLayout() {
        if (this.f1914m) {
            return;
        }
        super.requestLayout();
    }

    /* renamed from: s */
    public float m1737s(View view) {
        return ((C0348e) view.getLayoutParams()).f1937b;
    }

    public void setDrawerElevation(float f9) {
        this.f1904c = f9;
        for (int i9 = 0; i9 < getChildCount(); i9++) {
            View childAt = getChildAt(i9);
            if (m1703B(childAt)) {
                C4647u.m18542f0(childAt, this.f1904c);
            }
        }
    }

    @Deprecated
    public void setDrawerListener(InterfaceC0347d interfaceC0347d) {
        InterfaceC0347d interfaceC0347d2 = this.f1922u;
        if (interfaceC0347d2 != null) {
            m1710I(interfaceC0347d2);
        }
        if (interfaceC0347d != null) {
            m1719a(interfaceC0347d);
        }
        this.f1922u = interfaceC0347d;
    }

    public void setDrawerLockMode(int i9) {
        m1715N(i9, 3);
        m1715N(i9, 5);
    }

    public void setScrimColor(int i9) {
        this.f1906e = i9;
        invalidate();
    }

    public void setStatusBarBackground(Drawable drawable) {
        this.f1926y = drawable;
        invalidate();
    }

    public void setStatusBarBackgroundColor(int i9) {
        this.f1926y = new ColorDrawable(i9);
        invalidate();
    }

    /* renamed from: t */
    public final MotionEvent m1738t(MotionEvent motionEvent, View view) {
        float scrollX = getScrollX() - view.getLeft();
        float scrollY = getScrollY() - view.getTop();
        MotionEvent motionEventObtain = MotionEvent.obtain(motionEvent);
        motionEventObtain.offsetLocation(scrollX, scrollY);
        Matrix matrix = view.getMatrix();
        if (!matrix.isIdentity()) {
            if (this.f1902L == null) {
                this.f1902L = new Matrix();
            }
            matrix.invert(this.f1902L);
            motionEventObtain.transform(this.f1902L);
        }
        return motionEventObtain;
    }

    /* renamed from: w */
    public final boolean m1739w() {
        int childCount = getChildCount();
        for (int i9 = 0; i9 < childCount; i9++) {
            if (((C0348e) getChildAt(i9).getLayoutParams()).f1938c) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: x */
    public final boolean m1740x() {
        return m1732n() != null;
    }

    /* renamed from: z */
    public boolean m1741z(View view) {
        return ((C0348e) view.getLayoutParams()).f1936a == 0;
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new C0348e(getContext(), attributeSet);
    }

    public void setStatusBarBackground(int i9) {
        this.f1926y = i9 != 0 ? C6273a.m24025d(getContext(), i9) : null;
        invalidate();
    }

    /* renamed from: androidx.drawerlayout.widget.DrawerLayout$e */
    public static class C0348e extends ViewGroup.MarginLayoutParams {

        /* renamed from: a */
        public int f1936a;

        /* renamed from: b */
        public float f1937b;

        /* renamed from: c */
        public boolean f1938c;

        /* renamed from: d */
        public int f1939d;

        public C0348e(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.f1936a = 0;
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, DrawerLayout.f1888N);
            this.f1936a = typedArrayObtainStyledAttributes.getInt(0, 0);
            typedArrayObtainStyledAttributes.recycle();
        }

        public C0348e(int i9, int i10) {
            super(i9, i10);
            this.f1936a = 0;
        }

        public C0348e(C0348e c0348e) {
            super((ViewGroup.MarginLayoutParams) c0348e);
            this.f1936a = 0;
            this.f1936a = c0348e.f1936a;
        }

        public C0348e(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.f1936a = 0;
        }

        public C0348e(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
            this.f1936a = 0;
        }
    }

    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new C0343a();

        /* renamed from: b */
        public int f1928b;

        /* renamed from: c */
        public int f1929c;

        /* renamed from: d */
        public int f1930d;

        /* renamed from: e */
        public int f1931e;

        /* renamed from: f */
        public int f1932f;

        /* renamed from: androidx.drawerlayout.widget.DrawerLayout$SavedState$a */
        public static class C0343a implements Parcelable.ClassLoaderCreator<SavedState> {
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

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.f1928b = 0;
            this.f1928b = parcel.readInt();
            this.f1929c = parcel.readInt();
            this.f1930d = parcel.readInt();
            this.f1931e = parcel.readInt();
            this.f1932f = parcel.readInt();
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i9) {
            super.writeToParcel(parcel, i9);
            parcel.writeInt(this.f1928b);
            parcel.writeInt(this.f1929c);
            parcel.writeInt(this.f1930d);
            parcel.writeInt(this.f1931e);
            parcel.writeInt(this.f1932f);
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
            this.f1928b = 0;
        }
    }
}
