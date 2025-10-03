package com.cyberlink.you.widgetpool.clhorizontalgridview;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Handler;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.util.TypedValue;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewDebug;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.widget.Checkable;
import android.widget.ListAdapter;
import com.cyberlink.you.widgetpool.clhorizontalgridview.AdapterView;
import com.google.android.gms.auth.api.credentials.CredentialsApi;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.util.ArrayList;
import p132m.C5305d;
import p132m.C5309h;
import p218v2.C6453a0;

/* loaded from: classes.dex */
public abstract class AbsListView extends AdapterView<ListAdapter> implements ViewTreeObserver.OnTouchModeChangeListener {

    /* renamed from: e1 */
    public static final Interpolator f14831e1 = new LinearInterpolator();

    /* renamed from: A */
    public int f14832A;

    /* renamed from: A0 */
    public Runnable f14833A0;

    /* renamed from: B */
    public SparseBooleanArray f14834B;

    /* renamed from: B0 */
    public int f14835B0;

    /* renamed from: C */
    public C5305d<Integer> f14836C;

    /* renamed from: C0 */
    public int f14837C0;

    /* renamed from: D */
    public int f14838D;

    /* renamed from: D0 */
    public boolean f14839D0;

    /* renamed from: E */
    public AdapterView<ListAdapter>.C3227c f14840E;

    /* renamed from: E0 */
    public int f14841E0;

    /* renamed from: F */
    public ListAdapter f14842F;

    /* renamed from: F0 */
    public int f14843F0;

    /* renamed from: G */
    public boolean f14844G;

    /* renamed from: G0 */
    public float f14845G0;

    /* renamed from: H */
    public boolean f14846H;

    /* renamed from: H0 */
    public Runnable f14847H0;

    /* renamed from: I */
    public Drawable f14848I;

    /* renamed from: I0 */
    public Runnable f14849I0;

    /* renamed from: J */
    public int f14850J;

    /* renamed from: J0 */
    public int f14851J0;

    /* renamed from: K */
    public Rect f14852K;

    /* renamed from: K0 */
    public int f14853K0;

    /* renamed from: L */
    public final C3221k f14854L;

    /* renamed from: L0 */
    public float f14855L0;

    /* renamed from: M */
    public int f14856M;

    /* renamed from: M0 */
    public final boolean[] f14857M0;

    /* renamed from: N */
    public int f14858N;

    /* renamed from: N0 */
    public int f14859N0;

    /* renamed from: O */
    public int f14860O;

    /* renamed from: O0 */
    public int f14861O0;

    /* renamed from: P */
    public int f14862P;

    /* renamed from: P0 */
    public int f14863P0;

    /* renamed from: Q */
    public Rect f14864Q;

    /* renamed from: Q0 */
    public C3233a f14865Q0;

    /* renamed from: R */
    public int f14866R;

    /* renamed from: R0 */
    public C3233a f14867R0;

    /* renamed from: S */
    public View f14868S;

    /* renamed from: S0 */
    public int f14869S0;

    /* renamed from: T */
    public View f14870T;

    /* renamed from: T0 */
    public int f14871T0;

    /* renamed from: U */
    public boolean f14872U;

    /* renamed from: U0 */
    public int f14873U0;

    /* renamed from: V */
    public boolean f14874V;

    /* renamed from: V0 */
    public boolean f14875V0;

    /* renamed from: W */
    public int f14876W;

    /* renamed from: W0 */
    public int f14877W0;

    /* renamed from: X0 */
    public int f14878X0;

    /* renamed from: Y0 */
    public int f14879Y0;

    /* renamed from: Z0 */
    public int f14880Z0;

    /* renamed from: a0 */
    public int f14881a0;

    /* renamed from: a1 */
    public boolean f14882a1;

    /* renamed from: b0 */
    public int f14883b0;

    /* renamed from: b1 */
    public int f14884b1;

    /* renamed from: c0 */
    public int f14885c0;

    /* renamed from: c1 */
    public SavedState f14886c1;

    /* renamed from: d0 */
    public int f14887d0;

    /* renamed from: d1 */
    public float f14888d1;

    /* renamed from: e0 */
    public int f14889e0;

    /* renamed from: f0 */
    public int f14890f0;

    /* renamed from: g0 */
    public int f14891g0;

    /* renamed from: h0 */
    public VelocityTracker f14892h0;

    /* renamed from: i0 */
    public RunnableC3216f f14893i0;

    /* renamed from: j0 */
    public RunnableC3220j f14894j0;

    /* renamed from: k0 */
    public int f14895k0;

    /* renamed from: l0 */
    public boolean f14896l0;

    /* renamed from: m0 */
    public boolean f14897m0;

    /* renamed from: n0 */
    public InterfaceC3218h f14898n0;

    /* renamed from: o0 */
    public boolean f14899o0;

    /* renamed from: p0 */
    public Rect f14900p0;

    /* renamed from: q0 */
    public int f14901q0;

    /* renamed from: r0 */
    public ContextMenu.ContextMenuInfo f14902r0;

    /* renamed from: s0 */
    public int f14903s0;

    /* renamed from: t0 */
    public int f14904t0;

    /* renamed from: u0 */
    public boolean f14905u0;

    /* renamed from: v0 */
    public boolean f14906v0;

    /* renamed from: w0 */
    public RunnableC3214d f14907w0;

    /* renamed from: x0 */
    public Runnable f14908x0;

    /* renamed from: y0 */
    public RunnableC3213c f14909y0;

    /* renamed from: z */
    public int f14910z;

    /* renamed from: z0 */
    public RunnableC3219i f14911z0;

    public static class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new C3210a();

        /* renamed from: b */
        public long f14912b;

        /* renamed from: c */
        public long f14913c;

        /* renamed from: d */
        public int f14914d;

        /* renamed from: e */
        public int f14915e;

        /* renamed from: f */
        public int f14916f;

        /* renamed from: g */
        public String f14917g;

        /* renamed from: h */
        public boolean f14918h;

        /* renamed from: i */
        public int f14919i;

        /* renamed from: j */
        public SparseBooleanArray f14920j;

        /* renamed from: k */
        public C5305d<Integer> f14921k;

        /* renamed from: com.cyberlink.you.widgetpool.clhorizontalgridview.AbsListView$SavedState$a */
        public class C3210a implements Parcelable.Creator<SavedState> {
            @Override // android.os.Parcelable.Creator
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel, null);
            }

            @Override // android.os.Parcelable.Creator
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public SavedState[] newArray(int i9) {
                return new SavedState[i9];
            }
        }

        public /* synthetic */ SavedState(Parcel parcel, RunnableC3211a runnableC3211a) {
            this(parcel);
        }

        public String toString() {
            return "AbsListView.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " selectedId=" + this.f14912b + " firstId=" + this.f14913c + " viewLeft=" + this.f14914d + " position=" + this.f14915e + " height=" + this.f14916f + " filter=" + this.f14917g + " checkState=" + this.f14920j + "}";
        }

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i9) {
            super.writeToParcel(parcel, i9);
            parcel.writeLong(this.f14912b);
            parcel.writeLong(this.f14913c);
            parcel.writeInt(this.f14914d);
            parcel.writeInt(this.f14915e);
            parcel.writeInt(this.f14916f);
            parcel.writeString(this.f14917g);
            parcel.writeByte(this.f14918h ? (byte) 1 : (byte) 0);
            parcel.writeInt(this.f14919i);
            parcel.writeSparseBooleanArray(this.f14920j);
            C5305d<Integer> c5305d = this.f14921k;
            int iM20730n = c5305d != null ? c5305d.m20730n() : 0;
            parcel.writeInt(iM20730n);
            for (int i10 = 0; i10 < iM20730n; i10++) {
                parcel.writeLong(this.f14921k.m20725i(i10));
                parcel.writeInt(this.f14921k.m20731o(i10).intValue());
            }
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        public SavedState(Parcel parcel) {
            super(parcel);
            this.f14912b = parcel.readLong();
            this.f14913c = parcel.readLong();
            this.f14914d = parcel.readInt();
            this.f14915e = parcel.readInt();
            this.f14916f = parcel.readInt();
            this.f14917g = parcel.readString();
            this.f14918h = parcel.readByte() != 0;
            this.f14919i = parcel.readInt();
            this.f14920j = parcel.readSparseBooleanArray();
            int i9 = parcel.readInt();
            if (i9 > 0) {
                this.f14921k = new C5305d<>();
                for (int i10 = 0; i10 < i9; i10++) {
                    this.f14921k.m20726j(parcel.readLong(), Integer.valueOf(parcel.readInt()));
                }
            }
        }
    }

    /* renamed from: com.cyberlink.you.widgetpool.clhorizontalgridview.AbsListView$a */
    public class RunnableC3211a implements Runnable {

        /* renamed from: b */
        public final /* synthetic */ View f14922b;

        /* renamed from: c */
        public final /* synthetic */ RunnableC3219i f14923c;

        public RunnableC3211a(View view, RunnableC3219i runnableC3219i) {
            this.f14922b = view;
            this.f14923c = runnableC3219i;
        }

        @Override // java.lang.Runnable
        public void run() {
            AbsListView.this.f14889e0 = -1;
            this.f14922b.setPressed(false);
            AbsListView.this.setPressed(false);
            if (AbsListView.this.f14970l) {
                return;
            }
            this.f14923c.run();
        }
    }

    /* renamed from: com.cyberlink.you.widgetpool.clhorizontalgridview.AbsListView$b */
    public class RunnableC3212b implements Runnable {
        public RunnableC3212b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            AbsListView absListView = AbsListView.this;
            if (absListView.f14872U) {
                absListView.f14874V = false;
                absListView.f14872U = false;
                absListView.setChildrenDrawnWithCacheEnabled(false);
                if ((AbsListView.this.getPersistentDrawingCache() & 2) == 0) {
                    AbsListView.this.setChildrenDrawingCacheEnabled(false);
                }
                if (AbsListView.this.isAlwaysDrawnWithCacheEnabled()) {
                    return;
                }
                AbsListView.this.invalidate();
            }
        }
    }

    /* renamed from: com.cyberlink.you.widgetpool.clhorizontalgridview.AbsListView$c */
    public class RunnableC3213c extends C3224n implements Runnable {
        public RunnableC3213c() {
            super(AbsListView.this, null);
        }

        @Override // java.lang.Runnable
        public void run() {
            AbsListView absListView;
            int i9;
            boolean zM17148o0;
            if (!AbsListView.this.isPressed() || (i9 = (absListView = AbsListView.this).f14973o) < 0) {
                return;
            }
            View childAt = absListView.getChildAt(i9 - absListView.f14960b);
            AbsListView absListView2 = AbsListView.this;
            if (absListView2.f14970l) {
                absListView2.setPressed(false);
                if (childAt != null) {
                    childAt.setPressed(false);
                    return;
                }
                return;
            }
            if (m17191b()) {
                AbsListView absListView3 = AbsListView.this;
                zM17148o0 = absListView3.m17148o0(childAt, absListView3.f14973o, absListView3.f14974p);
            } else {
                zM17148o0 = false;
            }
            if (zM17148o0) {
                AbsListView.this.setPressed(false);
                childAt.setPressed(false);
            }
        }

        public /* synthetic */ RunnableC3213c(AbsListView absListView, RunnableC3211a runnableC3211a) {
            this();
        }
    }

    /* renamed from: com.cyberlink.you.widgetpool.clhorizontalgridview.AbsListView$d */
    public class RunnableC3214d extends C3224n implements Runnable {
        public RunnableC3214d() {
            super(AbsListView.this, null);
        }

        /* JADX WARN: Removed duplicated region for block: B:9:0x0029  */
        @Override // java.lang.Runnable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public void run() {
            boolean zM17148o0;
            AbsListView absListView = AbsListView.this;
            View childAt = absListView.getChildAt(absListView.f14876W - absListView.f14960b);
            if (childAt != null) {
                AbsListView absListView2 = AbsListView.this;
                int i9 = absListView2.f14876W;
                long itemId = absListView2.f14842F.getItemId(i9);
                if (m17191b()) {
                    AbsListView absListView3 = AbsListView.this;
                    zM17148o0 = !absListView3.f14970l ? absListView3.m17148o0(childAt, i9, itemId) : false;
                }
                if (!zM17148o0) {
                    AbsListView.this.f14889e0 = 2;
                    return;
                }
                AbsListView absListView4 = AbsListView.this;
                absListView4.f14889e0 = -1;
                absListView4.setPressed(false);
                childAt.setPressed(false);
            }
        }

        public /* synthetic */ RunnableC3214d(AbsListView absListView, RunnableC3211a runnableC3211a) {
            this();
        }
    }

    /* renamed from: com.cyberlink.you.widgetpool.clhorizontalgridview.AbsListView$e */
    public final class RunnableC3215e implements Runnable {
        public RunnableC3215e() {
        }

        @Override // java.lang.Runnable
        public void run() {
            Drawable current;
            AbsListView absListView = AbsListView.this;
            if (absListView.f14889e0 == 0) {
                absListView.f14889e0 = 1;
                View childAt = absListView.getChildAt(absListView.f14876W - absListView.f14960b);
                if (childAt == null || childAt.hasFocusable()) {
                    return;
                }
                AbsListView absListView2 = AbsListView.this;
                absListView2.f14838D = 0;
                if (absListView2.f14970l) {
                    absListView2.f14889e0 = 2;
                    return;
                }
                childAt.setPressed(true);
                AbsListView.this.setPressed(true);
                AbsListView.this.mo17144k0();
                AbsListView absListView3 = AbsListView.this;
                absListView3.m17152r0(absListView3.f14876W, childAt);
                AbsListView.this.refreshDrawableState();
                int longPressTimeout = ViewConfiguration.getLongPressTimeout();
                boolean zIsLongClickable = AbsListView.this.isLongClickable();
                Drawable drawable = AbsListView.this.f14848I;
                if (drawable != null && (current = drawable.getCurrent()) != null && (current instanceof TransitionDrawable)) {
                    if (zIsLongClickable) {
                        ((TransitionDrawable) current).startTransition(longPressTimeout);
                    } else {
                        ((TransitionDrawable) current).resetTransition();
                    }
                }
                if (!zIsLongClickable) {
                    AbsListView.this.f14889e0 = 2;
                    return;
                }
                if (AbsListView.this.f14907w0 == null) {
                    AbsListView absListView4 = AbsListView.this;
                    absListView4.f14907w0 = new RunnableC3214d(absListView4, null);
                }
                AbsListView.this.f14907w0.m17190a();
                AbsListView absListView5 = AbsListView.this;
                absListView5.postDelayed(absListView5.f14907w0, longPressTimeout);
            }
        }
    }

    /* renamed from: com.cyberlink.you.widgetpool.clhorizontalgridview.AbsListView$f */
    public class RunnableC3216f implements Runnable {

        /* renamed from: b */
        public final C3234b f14929b;

        /* renamed from: c */
        public int f14930c;

        /* renamed from: d */
        public final Runnable f14931d = new a();

        /* renamed from: com.cyberlink.you.widgetpool.clhorizontalgridview.AbsListView$f$a */
        public class a implements Runnable {
            public a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                int i9 = AbsListView.this.f14859N0;
                VelocityTracker velocityTracker = AbsListView.this.f14892h0;
                C3234b c3234b = RunnableC3216f.this.f14929b;
                if (velocityTracker == null || i9 == -1) {
                    return;
                }
                velocityTracker.computeCurrentVelocity(CredentialsApi.ACTIVITY_RESULT_ADD_ACCOUNT, AbsListView.this.f14853K0);
                float f9 = -velocityTracker.getXVelocity(i9);
                if (Math.abs(f9) >= AbsListView.this.f14851J0 && c3234b.m17255h(f9, BitmapDescriptorFactory.HUE_RED)) {
                    AbsListView.this.postDelayed(this, 40L);
                    return;
                }
                RunnableC3216f.this.m17164c();
                AbsListView absListView = AbsListView.this;
                absListView.f14889e0 = 3;
                absListView.m17155u0(1);
            }
        }

        public RunnableC3216f() {
            this.f14929b = new C3234b(AbsListView.this.getContext());
        }

        /* renamed from: b */
        public void m17163b(int i9) {
            this.f14929b.m17256i(AbsListView.this.getScrollX(), 0, AbsListView.this.f14863P0);
            int overScrollMode = AbsListView.this.getOverScrollMode();
            if (overScrollMode == 0 || (overScrollMode == 1 && !AbsListView.this.m17127R())) {
                AbsListView.this.f14889e0 = 6;
                int iM17252e = (int) this.f14929b.m17252e();
                if (i9 > 0) {
                    AbsListView.this.f14865Q0.m17241e(iM17252e);
                } else {
                    AbsListView.this.f14867R0.m17241e(iM17252e);
                }
            } else {
                AbsListView absListView = AbsListView.this;
                absListView.f14889e0 = -1;
                RunnableC3220j runnableC3220j = absListView.f14894j0;
                if (runnableC3220j != null) {
                    runnableC3220j.m17174c();
                }
            }
            AbsListView.this.invalidate();
            AbsListView.this.post(this);
        }

        /* renamed from: c */
        public void m17164c() {
            AbsListView absListView = AbsListView.this;
            absListView.f14889e0 = -1;
            absListView.removeCallbacks(this);
            AbsListView.this.removeCallbacks(this.f14931d);
            AbsListView.this.m17155u0(0);
            AbsListView.this.m17125P();
            this.f14929b.m17248a();
        }

        /* renamed from: d */
        public void m17165d() {
            AbsListView.this.postDelayed(this.f14931d, 40L);
        }

        /* renamed from: e */
        public void m17166e(int i9) {
            int i10 = i9 < 0 ? Integer.MAX_VALUE : 0;
            this.f14930c = i10;
            this.f14929b.m17258k(null);
            this.f14929b.m17250c(i10, 0, i9, 0, 0, Integer.MAX_VALUE, 0, Integer.MAX_VALUE);
            AbsListView absListView = AbsListView.this;
            absListView.f14889e0 = 4;
            absListView.post(this);
        }

        /* renamed from: f */
        public void m17167f(int i9) {
            this.f14929b.m17258k(null);
            this.f14929b.m17251d(AbsListView.this.getScrollX(), 0, i9, 0, 0, 0, 0, AbsListView.this.getWidth(), Integer.MIN_VALUE, Integer.MAX_VALUE);
            AbsListView absListView = AbsListView.this;
            absListView.f14889e0 = 6;
            absListView.invalidate();
            AbsListView.this.post(this);
        }

        /* renamed from: g */
        public void m17168g(int i9, int i10, boolean z8) {
            int i11 = i9 < 0 ? Integer.MAX_VALUE : 0;
            this.f14930c = i11;
            this.f14929b.m17258k(z8 ? AbsListView.f14831e1 : null);
            this.f14929b.m17260m(i11, 0, i9, 0, i10);
            AbsListView absListView = AbsListView.this;
            absListView.f14889e0 = 4;
            absListView.post(this);
        }

        /* renamed from: h */
        public void m17169h() {
            if (!this.f14929b.m17259l(AbsListView.this.getScrollX(), 0, 0, 0, 0, 0)) {
                AbsListView absListView = AbsListView.this;
                absListView.f14889e0 = -1;
                absListView.m17155u0(0);
            } else {
                AbsListView absListView2 = AbsListView.this;
                absListView2.f14889e0 = 6;
                absListView2.invalidate();
                AbsListView.this.post(this);
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            int iMax;
            int i9 = AbsListView.this.f14889e0;
            boolean z8 = false;
            if (i9 != 3) {
                if (i9 != 4) {
                    if (i9 != 6) {
                        m17164c();
                        return;
                    }
                    C3234b c3234b = this.f14929b;
                    if (!c3234b.m17249b()) {
                        m17164c();
                        return;
                    }
                    int scrollX = AbsListView.this.getScrollX();
                    int iM17253f = c3234b.m17253f();
                    AbsListView absListView = AbsListView.this;
                    if (!absListView.overScrollBy(iM17253f - scrollX, 0, scrollX, 0, 0, 0, absListView.f14863P0, 0, false)) {
                        AbsListView.this.invalidate();
                        AbsListView.this.post(this);
                        return;
                    }
                    boolean z9 = scrollX <= 0 && iM17253f > 0;
                    if (scrollX >= 0 && iM17253f < 0) {
                        z8 = true;
                    }
                    if (!z9 && !z8) {
                        m17169h();
                        return;
                    }
                    int iM17252e = (int) c3234b.m17252e();
                    if (z8) {
                        iM17252e = -iM17252e;
                    }
                    c3234b.m17248a();
                    m17166e(iM17252e);
                    return;
                }
            } else if (this.f14929b.m17254g()) {
                return;
            }
            AbsListView absListView2 = AbsListView.this;
            if (absListView2.f14970l) {
                absListView2.mo17144k0();
            }
            AbsListView absListView3 = AbsListView.this;
            if (absListView3.f14976r == 0 || absListView3.getChildCount() == 0) {
                m17164c();
                return;
            }
            C3234b c3234b2 = this.f14929b;
            boolean zM17249b = c3234b2.m17249b();
            int iM17253f2 = c3234b2.m17253f();
            int i10 = this.f14930c - iM17253f2;
            if (i10 > 0) {
                AbsListView absListView4 = AbsListView.this;
                absListView4.f14876W = absListView4.f14960b;
                AbsListView.this.f14881a0 = absListView4.getChildAt(0).getLeft();
                iMax = Math.min(((AbsListView.this.getWidth() - AbsListView.this.getPaddingRight()) - AbsListView.this.getPaddingLeft()) - 1, i10);
            } else {
                int childCount = AbsListView.this.getChildCount() - 1;
                AbsListView absListView5 = AbsListView.this;
                absListView5.f14876W = absListView5.f14960b + childCount;
                AbsListView.this.f14881a0 = absListView5.getChildAt(childCount).getLeft();
                iMax = Math.max(-(((AbsListView.this.getWidth() - AbsListView.this.getPaddingRight()) - AbsListView.this.getPaddingLeft()) - 1), i10);
            }
            AbsListView absListView6 = AbsListView.this;
            View childAt = absListView6.getChildAt(absListView6.f14876W - absListView6.f14960b);
            int left = childAt != null ? childAt.getLeft() : 0;
            boolean zM17119I0 = AbsListView.this.m17119I0(iMax, iMax);
            if (zM17119I0 && iMax != 0) {
                z8 = true;
            }
            if (z8) {
                if (childAt != null) {
                    int i11 = -(iMax - (childAt.getLeft() - left));
                    AbsListView absListView7 = AbsListView.this;
                    absListView7.overScrollBy(i11, 0, absListView7.getScrollX(), 0, 0, 0, AbsListView.this.f14863P0, 0, false);
                }
                if (zM17249b) {
                    m17163b(iMax);
                    return;
                }
                return;
            }
            if (!zM17249b || z8) {
                m17164c();
                return;
            }
            if (zM17119I0) {
                AbsListView.this.invalidate();
            }
            this.f14930c = iM17253f2;
            AbsListView.this.post(this);
        }
    }

    /* renamed from: com.cyberlink.you.widgetpool.clhorizontalgridview.AbsListView$h */
    public interface InterfaceC3218h {
        /* renamed from: a */
        void mo17170a(AbsListView absListView, int i9);

        /* renamed from: b */
        void mo17171b(AbsListView absListView, int i9, int i10, int i11);
    }

    /* renamed from: com.cyberlink.you.widgetpool.clhorizontalgridview.AbsListView$i */
    public class RunnableC3219i extends C3224n implements Runnable {

        /* renamed from: d */
        public int f14938d;

        public RunnableC3219i() {
            super(AbsListView.this, null);
        }

        @Override // java.lang.Runnable
        public void run() {
            AbsListView absListView = AbsListView.this;
            if (absListView.f14970l) {
                return;
            }
            ListAdapter listAdapter = absListView.f14842F;
            int i9 = this.f14938d;
            if (listAdapter == null || absListView.f14976r <= 0 || i9 == -1 || i9 >= listAdapter.getCount() || !m17191b()) {
                return;
            }
            AbsListView absListView2 = AbsListView.this;
            View childAt = absListView2.getChildAt(i9 - absListView2.f14960b);
            if (childAt != null) {
                AbsListView.this.mo17149p(childAt, i9, listAdapter.getItemId(i9));
            }
        }

        public /* synthetic */ RunnableC3219i(AbsListView absListView, RunnableC3211a runnableC3211a) {
            this();
        }
    }

    /* renamed from: com.cyberlink.you.widgetpool.clhorizontalgridview.AbsListView$j */
    public class RunnableC3220j implements Runnable {

        /* renamed from: b */
        public int f14940b;

        /* renamed from: c */
        public int f14941c;

        /* renamed from: d */
        public int f14942d;

        /* renamed from: e */
        public int f14943e;

        /* renamed from: f */
        public int f14944f;

        /* renamed from: g */
        public final int f14945g;

        /* renamed from: h */
        public int f14946h;

        /* renamed from: com.cyberlink.you.widgetpool.clhorizontalgridview.AbsListView$j$a */
        public class a implements Runnable {

            /* renamed from: b */
            public final /* synthetic */ int f14948b;

            public a(int i9) {
                this.f14948b = i9;
            }

            @Override // java.lang.Runnable
            public void run() {
                RunnableC3220j.this.m17173b(this.f14948b);
            }
        }

        public RunnableC3220j() {
            this.f14945g = ViewConfiguration.get(AbsListView.this.getContext()).getScaledFadingEdgeLength();
        }

        /* renamed from: a */
        public void m17172a(int i9, int i10, int i11) {
            AbsListView absListView = AbsListView.this;
            int i12 = absListView.f14960b;
            int childCount = (absListView.getChildCount() + i12) - 1;
            AbsListView absListView2 = AbsListView.this;
            int i13 = absListView2.f14864Q.left;
            int width = absListView2.getWidth() - AbsListView.this.f14864Q.right;
            if (i9 < i12 || i9 > childCount) {
                Log.w("AbsListView", "scrollToVisible called with targetPos " + i9 + " not visible [" + i12 + ", " + childCount + "]");
            }
            if (i10 < i12 || i10 > childCount) {
                i10 = -1;
            }
            View childAt = AbsListView.this.getChildAt(i9 - i12);
            int left = childAt.getLeft();
            int right = childAt.getRight();
            int iMin = right > width ? right - width : 0;
            if (left < i13) {
                iMin = left - i13;
            }
            if (iMin == 0) {
                return;
            }
            if (i10 >= 0) {
                View childAt2 = AbsListView.this.getChildAt(i10 - i12);
                int left2 = childAt2.getLeft();
                int right2 = childAt2.getRight();
                int iAbs = Math.abs(iMin);
                if (iMin < 0 && right2 + iAbs > width) {
                    iMin = Math.max(0, right2 - width);
                } else if (iMin > 0 && left2 - iAbs < i13) {
                    iMin = Math.min(0, left2 - i13);
                }
            }
            AbsListView.this.m17114D0(iMin, i11);
        }

        /* renamed from: b */
        public void m17173b(int i9) {
            int i10;
            m17174c();
            AbsListView absListView = AbsListView.this;
            if (absListView.f14970l) {
                absListView.f14849I0 = new a(i9);
                return;
            }
            int childCount = absListView.getChildCount();
            if (childCount == 0) {
                return;
            }
            AbsListView absListView2 = AbsListView.this;
            int i11 = absListView2.f14960b;
            int i12 = (childCount + i11) - 1;
            int iMax = Math.max(0, Math.min(absListView2.getCount() - 1, i9));
            if (iMax < i11) {
                i10 = (i11 - iMax) + 1;
                this.f14940b = 2;
            } else if (iMax <= i12) {
                m17172a(iMax, -1, 200);
                return;
            } else {
                i10 = (iMax - i12) + 1;
                this.f14940b = 1;
            }
            if (i10 > 0) {
                this.f14944f = 200 / i10;
            } else {
                this.f14944f = 200;
            }
            this.f14941c = iMax;
            this.f14942d = -1;
            this.f14943e = -1;
            AbsListView.this.post(this);
        }

        /* renamed from: c */
        public void m17174c() {
            AbsListView.this.removeCallbacks(this);
        }

        @Override // java.lang.Runnable
        public void run() {
            int width = AbsListView.this.getWidth();
            AbsListView absListView = AbsListView.this;
            int i9 = absListView.f14960b;
            int i10 = this.f14940b;
            if (i10 == 1) {
                int childCount = absListView.getChildCount() - 1;
                int i11 = i9 + childCount;
                if (childCount < 0) {
                    return;
                }
                if (i11 == this.f14943e) {
                    AbsListView.this.post(this);
                    return;
                }
                View childAt = AbsListView.this.getChildAt(childCount);
                int width2 = childAt.getWidth();
                int left = width - childAt.getLeft();
                AbsListView absListView2 = AbsListView.this;
                AbsListView.this.m17115E0((width2 - left) + (i11 < absListView2.f14976r - 1 ? Math.max(absListView2.f14864Q.right, this.f14945g) : absListView2.f14864Q.right), this.f14944f, true);
                this.f14943e = i11;
                if (i11 < this.f14941c) {
                    AbsListView.this.post(this);
                    return;
                }
                return;
            }
            int i12 = 0;
            if (i10 == 2) {
                if (i9 == this.f14943e) {
                    absListView.post(this);
                    return;
                }
                View childAt2 = absListView.getChildAt(0);
                if (childAt2 == null) {
                    return;
                }
                AbsListView.this.m17115E0(childAt2.getLeft() - (i9 > 0 ? Math.max(this.f14945g, AbsListView.this.f14864Q.left) : AbsListView.this.f14864Q.left), this.f14944f, true);
                this.f14943e = i9;
                if (i9 > this.f14941c) {
                    AbsListView.this.post(this);
                    return;
                }
                return;
            }
            if (i10 == 3) {
                int childCount2 = absListView.getChildCount();
                if (i9 == this.f14942d || childCount2 <= 1) {
                    return;
                }
                int i13 = childCount2 + i9;
                AbsListView absListView3 = AbsListView.this;
                if (i13 >= absListView3.f14976r) {
                    return;
                }
                int i14 = i9 + 1;
                if (i14 == this.f14943e) {
                    absListView3.post(this);
                    return;
                }
                View childAt3 = absListView3.getChildAt(1);
                int width3 = childAt3.getWidth();
                int left2 = childAt3.getLeft();
                int iMax = Math.max(AbsListView.this.f14864Q.right, this.f14945g);
                if (i14 < this.f14942d) {
                    AbsListView.this.m17115E0(Math.max(0, (width3 + left2) - iMax), this.f14944f, true);
                    this.f14943e = i14;
                    AbsListView.this.post(this);
                    return;
                } else {
                    if (left2 > iMax) {
                        AbsListView.this.m17115E0(left2 - iMax, this.f14944f, true);
                        return;
                    }
                    return;
                }
            }
            if (i10 == 4) {
                int childCount3 = absListView.getChildCount() - 2;
                if (childCount3 < 0) {
                    return;
                }
                int i15 = i9 + childCount3;
                if (i15 == this.f14943e) {
                    AbsListView.this.post(this);
                    return;
                }
                View childAt4 = AbsListView.this.getChildAt(childCount3);
                int width4 = childAt4.getWidth();
                int left3 = childAt4.getLeft();
                int i16 = width - left3;
                int iMax2 = Math.max(AbsListView.this.f14864Q.left, this.f14945g);
                this.f14943e = i15;
                if (i15 > this.f14942d) {
                    AbsListView.this.m17115E0(-(i16 - iMax2), this.f14944f, true);
                    AbsListView.this.post(this);
                    return;
                }
                int i17 = width - iMax2;
                int i18 = left3 + width4;
                if (i17 > i18) {
                    AbsListView.this.m17115E0(-(i17 - i18), this.f14944f, true);
                    return;
                }
                return;
            }
            if (i10 != 5) {
                return;
            }
            if (this.f14943e == i9) {
                absListView.post(this);
                return;
            }
            this.f14943e = i9;
            int childCount4 = absListView.getChildCount();
            int i19 = this.f14941c;
            int i20 = (i9 + childCount4) - 1;
            if (i19 < i9) {
                i12 = (i9 - i19) + 1;
            } else if (i19 > i20) {
                i12 = i19 - i20;
            }
            float fMin = Math.min(Math.abs(i12 / childCount4), 1.0f);
            if (i19 < i9) {
                AbsListView.this.m17115E0((int) ((-AbsListView.this.getHeight()) * fMin), (int) (this.f14944f * fMin), true);
                AbsListView.this.post(this);
            } else if (i19 > i20) {
                AbsListView.this.m17115E0((int) (AbsListView.this.getHeight() * fMin), (int) (this.f14944f * fMin), true);
                AbsListView.this.post(this);
            } else {
                AbsListView.this.m17115E0(AbsListView.this.getChildAt(i19 - i9).getLeft() - this.f14946h, (int) (this.f14944f * (Math.abs(r0) / AbsListView.this.getHeight())), true);
            }
        }
    }

    /* renamed from: com.cyberlink.you.widgetpool.clhorizontalgridview.AbsListView$k */
    public class C3221k {

        /* renamed from: a */
        public int f14950a;

        /* renamed from: b */
        public View[] f14951b = new View[0];

        /* renamed from: c */
        public ArrayList<View>[] f14952c;

        /* renamed from: d */
        public int f14953d;

        /* renamed from: e */
        public ArrayList<View> f14954e;

        /* renamed from: f */
        public ArrayList<View> f14955f;

        /* renamed from: g */
        public C5309h<View> f14956g;

        public C3221k() {
        }

        /* renamed from: a */
        public static /* synthetic */ InterfaceC3222l m17175a(C3221k c3221k, InterfaceC3222l interfaceC3222l) {
            c3221k.getClass();
            return interfaceC3222l;
        }

        /* renamed from: b */
        public void m17176b(View view, int i9) {
            C3217g c3217g = (C3217g) view.getLayoutParams();
            if (c3217g == null) {
                return;
            }
            c3217g.f14936c = i9;
            int i10 = c3217g.f14934a;
            if (m17189o(i10)) {
                if (this.f14953d == 1) {
                    this.f14954e.add(view);
                    return;
                } else {
                    this.f14952c[i10].add(view);
                    return;
                }
            }
            if (i10 != -2) {
                if (this.f14955f == null) {
                    this.f14955f = new ArrayList<>();
                }
                this.f14955f.add(view);
            }
        }

        /* renamed from: c */
        public void m17177c() {
            int i9 = this.f14953d;
            if (i9 == 1) {
                ArrayList<View> arrayList = this.f14954e;
                int size = arrayList.size();
                for (int i10 = 0; i10 < size; i10++) {
                    AbsListView.this.removeDetachedView(arrayList.remove((size - 1) - i10), false);
                }
            } else {
                for (int i11 = 0; i11 < i9; i11++) {
                    ArrayList<View> arrayList2 = this.f14952c[i11];
                    int size2 = arrayList2.size();
                    for (int i12 = 0; i12 < size2; i12++) {
                        AbsListView.this.removeDetachedView(arrayList2.remove((size2 - 1) - i12), false);
                    }
                }
            }
            C5309h<View> c5309h = this.f14956g;
            if (c5309h != null) {
                c5309h.m20757b();
            }
        }

        /* renamed from: d */
        public void m17178d() {
            C5309h<View> c5309h = this.f14956g;
            if (c5309h != null) {
                c5309h.m20757b();
            }
        }

        /* renamed from: e */
        public void m17179e(int i9, int i10) {
            if (this.f14951b.length < i9) {
                this.f14951b = new View[i9];
            }
            this.f14950a = i10;
            View[] viewArr = this.f14951b;
            for (int i11 = 0; i11 < i9; i11++) {
                View childAt = AbsListView.this.getChildAt(i11);
                C3217g c3217g = (C3217g) childAt.getLayoutParams();
                if (c3217g != null && c3217g.f14934a != -2) {
                    viewArr[i11] = childAt;
                }
            }
        }

        /* renamed from: f */
        public View m17180f(int i9) {
            int i10 = i9 - this.f14950a;
            View[] viewArr = this.f14951b;
            if (i10 < 0 || i10 >= viewArr.length) {
                return null;
            }
            View view = viewArr[i10];
            viewArr[i10] = null;
            return view;
        }

        /* renamed from: g */
        public View m17181g(int i9) {
            if (this.f14953d == 1) {
                return AbsListView.m17111z0(this.f14954e, i9);
            }
            int itemViewType = AbsListView.this.f14842F.getItemViewType(i9);
            if (itemViewType < 0) {
                return null;
            }
            ArrayList<View>[] arrayListArr = this.f14952c;
            if (itemViewType < arrayListArr.length) {
                return AbsListView.m17111z0(arrayListArr[itemViewType], i9);
            }
            return null;
        }

        /* renamed from: h */
        public View m17182h(int i9) {
            int iM20762h;
            C5309h<View> c5309h = this.f14956g;
            if (c5309h == null || (iM20762h = c5309h.m20762h(i9)) < 0) {
                return null;
            }
            View viewM20769o = this.f14956g.m20769o(iM20762h);
            this.f14956g.m20767m(iM20762h);
            return viewM20769o;
        }

        /* renamed from: i */
        public void m17183i() {
            int i9 = this.f14953d;
            if (i9 == 1) {
                ArrayList<View> arrayList = this.f14954e;
                int size = arrayList.size();
                for (int i10 = 0; i10 < size; i10++) {
                    arrayList.get(i10).forceLayout();
                }
            } else {
                for (int i11 = 0; i11 < i9; i11++) {
                    ArrayList<View> arrayList2 = this.f14952c[i11];
                    int size2 = arrayList2.size();
                    for (int i12 = 0; i12 < size2; i12++) {
                        arrayList2.get(i12).forceLayout();
                    }
                }
            }
            C5309h<View> c5309h = this.f14956g;
            if (c5309h != null) {
                int iM20768n = c5309h.m20768n();
                for (int i13 = 0; i13 < iM20768n; i13++) {
                    this.f14956g.m20769o(i13).forceLayout();
                }
            }
        }

        /* renamed from: j */
        public final void m17184j() {
            int length = this.f14951b.length;
            int i9 = this.f14953d;
            ArrayList<View>[] arrayListArr = this.f14952c;
            for (int i10 = 0; i10 < i9; i10++) {
                ArrayList<View> arrayList = arrayListArr[i10];
                int size = arrayList.size();
                int i11 = size - length;
                int i12 = size - 1;
                int i13 = 0;
                while (i13 < i11) {
                    AbsListView.this.removeDetachedView(arrayList.remove(i12), false);
                    i13++;
                    i12--;
                }
            }
        }

        /* renamed from: k */
        public void m17185k() {
            ArrayList<View> arrayList = this.f14955f;
            if (arrayList == null) {
                return;
            }
            int size = arrayList.size();
            for (int i9 = 0; i9 < size; i9++) {
                AbsListView.this.removeDetachedView(this.f14955f.get(i9), false);
            }
            this.f14955f.clear();
        }

        /* renamed from: l */
        public void m17186l() {
            View[] viewArr = this.f14951b;
            boolean z8 = this.f14953d > 1;
            ArrayList<View> arrayList = this.f14954e;
            for (int length = viewArr.length - 1; length >= 0; length--) {
                View view = viewArr[length];
                if (view != null) {
                    C3217g c3217g = (C3217g) view.getLayoutParams();
                    int i9 = c3217g.f14934a;
                    viewArr[length] = null;
                    if (m17189o(i9)) {
                        if (z8) {
                            arrayList = this.f14952c[i9];
                        }
                        c3217g.f14936c = this.f14950a + length;
                        arrayList.add(view);
                    } else if (i9 != -2) {
                        AbsListView.this.removeDetachedView(view, false);
                    }
                }
            }
            m17184j();
        }

        /* renamed from: m */
        public void m17187m(int i9) {
            int i10 = this.f14953d;
            if (i10 == 1) {
                ArrayList<View> arrayList = this.f14954e;
                int size = arrayList.size();
                for (int i11 = 0; i11 < size; i11++) {
                    arrayList.get(i11).setDrawingCacheBackgroundColor(i9);
                }
            } else {
                for (int i12 = 0; i12 < i10; i12++) {
                    ArrayList<View> arrayList2 = this.f14952c[i12];
                    int size2 = arrayList2.size();
                    for (int i13 = 0; i13 < size2; i13++) {
                        arrayList2.get(i13).setDrawingCacheBackgroundColor(i9);
                    }
                }
            }
            for (View view : this.f14951b) {
                if (view != null) {
                    view.setDrawingCacheBackgroundColor(i9);
                }
            }
        }

        /* renamed from: n */
        public void m17188n(int i9) {
            if (i9 < 1) {
                throw new IllegalArgumentException("Can't have a viewTypeCount < 1");
            }
            ArrayList<View>[] arrayListArr = new ArrayList[i9];
            for (int i10 = 0; i10 < i9; i10++) {
                arrayListArr[i10] = new ArrayList<>();
            }
            this.f14953d = i9;
            this.f14954e = arrayListArr[0];
            this.f14952c = arrayListArr;
        }

        /* renamed from: o */
        public boolean m17189o(int i9) {
            return i9 >= 0;
        }
    }

    /* renamed from: com.cyberlink.you.widgetpool.clhorizontalgridview.AbsListView$l */
    public interface InterfaceC3222l {
    }

    /* renamed from: com.cyberlink.you.widgetpool.clhorizontalgridview.AbsListView$m */
    public interface InterfaceC3223m {
        void adjustListItemSelectionBounds(Rect rect);
    }

    /* renamed from: com.cyberlink.you.widgetpool.clhorizontalgridview.AbsListView$n */
    public class C3224n {

        /* renamed from: b */
        public int f14958b;

        public C3224n() {
        }

        /* renamed from: a */
        public void m17190a() {
            this.f14958b = AbsListView.this.getWindowAttachCount();
        }

        /* renamed from: b */
        public boolean m17191b() {
            return AbsListView.this.hasWindowFocus() && AbsListView.this.getWindowAttachCount() == this.f14958b;
        }

        public /* synthetic */ C3224n(AbsListView absListView, RunnableC3211a runnableC3211a) {
            this();
        }
    }

    public AbsListView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.absListViewStyle);
    }

    /* renamed from: B0 */
    public static void m17089B0(View view, int i9) {
        view.scrollTo(i9, view.getScrollY());
    }

    /* renamed from: a0 */
    public static int m17102a0(Rect rect, Rect rect2, int i9) {
        int iWidth;
        int iHeight;
        int iWidth2;
        int i10;
        int iHeight2;
        int i11;
        if (i9 == 1 || i9 == 2) {
            iWidth = rect.right + (rect.width() / 2);
            iHeight = (rect.height() / 2) + rect.top;
            iWidth2 = rect2.left + (rect2.width() / 2);
            i10 = rect2.top;
            iHeight2 = rect2.height() / 2;
        } else {
            if (i9 != 17) {
                if (i9 == 33) {
                    iWidth = rect.left + (rect.width() / 2);
                    iHeight = rect.top;
                    iWidth2 = rect2.left + (rect2.width() / 2);
                    i11 = rect2.bottom;
                } else if (i9 == 66) {
                    iWidth = rect.right;
                    iHeight = (rect.height() / 2) + rect.top;
                    iWidth2 = rect2.left;
                    i10 = rect2.top;
                    iHeight2 = rect2.height() / 2;
                } else {
                    if (i9 != 130) {
                        throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT, FOCUS_FORWARD, FOCUS_BACKWARD}.");
                    }
                    iWidth = rect.left + (rect.width() / 2);
                    iHeight = rect.bottom;
                    iWidth2 = rect2.left + (rect2.width() / 2);
                    i11 = rect2.top;
                }
                int i12 = iWidth2 - iWidth;
                int i13 = i11 - iHeight;
                return (i13 * i13) + (i12 * i12);
            }
            iWidth = rect.left;
            iHeight = (rect.height() / 2) + rect.top;
            iWidth2 = rect2.right;
            i10 = rect2.top;
            iHeight2 = rect2.height() / 2;
        }
        i11 = iHeight2 + i10;
        int i122 = iWidth2 - iWidth;
        int i132 = i11 - iHeight;
        return (i132 * i132) + (i122 * i122);
    }

    /* renamed from: h0 */
    public static boolean m17103h0(View view) {
        return view.isHardwareAccelerated();
    }

    /* renamed from: z0 */
    public static View m17111z0(ArrayList<View> arrayList, int i9) {
        int size = arrayList.size();
        if (size <= 0) {
            return null;
        }
        for (int i10 = 0; i10 < size; i10++) {
            View view = arrayList.get(i10);
            if (((C3217g) view.getLayoutParams()).f14936c == i9) {
                arrayList.remove(i10);
                return view;
            }
        }
        return arrayList.remove(size - 1);
    }

    /* renamed from: A0 */
    public final void m17112A0(int i9) {
        int i10;
        int i11;
        int i12;
        int i13;
        int i14;
        VelocityTracker velocityTracker;
        int i15 = i9 - this.f14885c0;
        int i16 = i15 - this.f14891g0;
        int i17 = this.f14890f0;
        int i18 = i17 != Integer.MIN_VALUE ? i9 - i17 : i16;
        int i19 = this.f14889e0;
        if (i19 == 3) {
            if (i9 != i17) {
                int i20 = this.f14876W;
                int childCount = i20 >= 0 ? i20 - this.f14960b : getChildCount() / 2;
                View childAt = getChildAt(childCount);
                int left = childAt != null ? childAt.getLeft() : 0;
                boolean zM17119I0 = i18 != 0 ? m17119I0(i16, i18) : false;
                View childAt2 = getChildAt(childCount);
                if (childAt2 != null) {
                    int left2 = childAt2.getLeft();
                    if (zM17119I0) {
                        int i21 = (-i18) - (left2 - left);
                        overScrollBy(i21, 0, getScrollX(), 0, 0, 0, this.f14861O0, 0, true);
                        if (Math.abs(this.f14861O0) == Math.abs(getScrollX()) && (velocityTracker = this.f14892h0) != null) {
                            velocityTracker.clear();
                        }
                        int overScrollMode = getOverScrollMode();
                        if (overScrollMode == 0 || (overScrollMode == 1 && !m17127R())) {
                            this.f14873U0 = 0;
                            this.f14889e0 = 5;
                            if (i15 > 0) {
                                this.f14865Q0.m17242f(i21 / getWidth());
                                if (!this.f14867R0.m17240d()) {
                                    this.f14867R0.m17243g();
                                }
                                invalidate(this.f14865Q0.m17239c(false));
                            } else if (i15 < 0) {
                                this.f14867R0.m17242f(i21 / getWidth());
                                if (!this.f14865Q0.m17240d()) {
                                    this.f14865Q0.m17243g();
                                }
                                invalidate(this.f14867R0.m17239c(true));
                            }
                        }
                    }
                    this.f14885c0 = i9;
                }
                this.f14890f0 = i9;
                return;
            }
            return;
        }
        if (i19 != 5 || i9 == i17) {
            return;
        }
        int scrollX = getScrollX();
        int i22 = scrollX - i18;
        int i23 = i9 > this.f14890f0 ? 1 : -1;
        if (this.f14873U0 == 0) {
            this.f14873U0 = i23;
        }
        int i24 = -i18;
        if ((i22 >= 0 || scrollX < 0) && (i22 <= 0 || scrollX > 0)) {
            i10 = i24;
            i11 = 0;
        } else {
            int i25 = -scrollX;
            i11 = i18 + i25;
            i10 = i25;
        }
        if (i10 != 0) {
            i12 = i11;
            int i26 = i10;
            i13 = i23;
            overScrollBy(i10, 0, getScrollX(), 0, 0, 0, this.f14861O0, 0, true);
            int overScrollMode2 = getOverScrollMode();
            if (overScrollMode2 == 0 || (overScrollMode2 == 1 && !m17127R())) {
                if (i15 > 0) {
                    this.f14865Q0.m17242f(i26 / getWidth());
                    if (!this.f14867R0.m17240d()) {
                        this.f14867R0.m17243g();
                    }
                    invalidate(this.f14865Q0.m17239c(false));
                } else if (i15 < 0) {
                    this.f14867R0.m17242f(i26 / getWidth());
                    if (!this.f14865Q0.m17240d()) {
                        this.f14865Q0.m17243g();
                    }
                    invalidate(this.f14867R0.m17239c(true));
                }
            }
        } else {
            i12 = i11;
            i13 = i23;
        }
        if (i12 != 0) {
            if (getScrollX() != 0) {
                i14 = 0;
                m17089B0(this, 0);
            } else {
                i14 = 0;
            }
            m17119I0(i12, i12);
            this.f14889e0 = 3;
            int iM17132W = m17132W(i9);
            this.f14891g0 = i14;
            View childAt3 = getChildAt(iM17132W - this.f14960b);
            this.f14881a0 = childAt3 != null ? childAt3.getLeft() : i14;
            this.f14885c0 = i9;
            this.f14876W = iM17132W;
        }
        this.f14890f0 = i9;
        this.f14873U0 = i13;
    }

    /* renamed from: C0 */
    public boolean m17113C0() {
        return (hasFocus() && !isInTouchMode()) || m17118H0();
    }

    /* renamed from: D0 */
    public void m17114D0(int i9, int i10) {
        m17115E0(i9, i10, false);
    }

    /* renamed from: E0 */
    public void m17115E0(int i9, int i10, boolean z8) {
        if (this.f14893i0 == null) {
            this.f14893i0 = new RunnableC3216f();
        }
        int i11 = this.f14960b;
        int childCount = getChildCount();
        int i12 = i11 + childCount;
        int paddingLeft = getPaddingLeft();
        int width = getWidth() - getPaddingRight();
        if (i9 != 0 && this.f14976r != 0 && childCount != 0 && ((i11 != 0 || getChildAt(0).getLeft() != paddingLeft || i9 >= 0) && (i12 != this.f14976r || getChildAt(childCount - 1).getRight() != width || i9 <= 0))) {
            m17155u0(2);
            this.f14893i0.m17168g(i9, i10, z8);
            return;
        }
        this.f14893i0.m17164c();
        RunnableC3220j runnableC3220j = this.f14894j0;
        if (runnableC3220j != null) {
            runnableC3220j.m17174c();
        }
    }

    /* renamed from: F0 */
    public void m17116F0(int i9) {
        if (this.f14894j0 == null) {
            this.f14894j0 = new RunnableC3220j();
        }
        this.f14894j0.m17173b(i9);
    }

    /* renamed from: G0 */
    public final boolean m17117G0(int i9) {
        int i10 = i9 - this.f14885c0;
        int iAbs = Math.abs(i10);
        boolean z8 = getScrollX() != 0;
        if (!z8 && iAbs <= this.f14843F0) {
            return false;
        }
        m17129T();
        if (z8) {
            this.f14889e0 = 5;
            this.f14891g0 = 0;
        } else {
            this.f14889e0 = 3;
            this.f14891g0 = i10 > 0 ? this.f14843F0 : -this.f14843F0;
        }
        Handler handler = getHandler();
        if (handler != null) {
            handler.removeCallbacks(this.f14907w0);
        }
        setPressed(false);
        View childAt = getChildAt(this.f14876W - this.f14960b);
        if (childAt != null) {
            childAt.setPressed(false);
        }
        m17155u0(1);
        ViewParent parent = getParent();
        if (parent != null) {
            parent.requestDisallowInterceptTouchEvent(true);
        }
        m17112A0(i9);
        return true;
    }

    /* renamed from: H0 */
    public boolean m17118H0() {
        int i9 = this.f14889e0;
        return i9 == 1 || i9 == 2;
    }

    /* renamed from: I0 */
    public boolean m17119I0(int i9, int i10) {
        int i11;
        int i12;
        int i13;
        int i14;
        int childCount = getChildCount();
        if (childCount == 0) {
            return true;
        }
        int left = getChildAt(0).getLeft();
        int i15 = childCount - 1;
        int right = getChildAt(i15).getRight();
        Rect rect = this.f14864Q;
        int i16 = 0 - left;
        int width = right - (getWidth() - 0);
        int width2 = (getWidth() - getPaddingRight()) - getPaddingLeft();
        int iMax = i9 < 0 ? Math.max(-(width2 - 1), i9) : Math.min(width2 - 1, i9);
        int iMax2 = i10 < 0 ? Math.max(-(width2 - 1), i10) : Math.min(width2 - 1, i10);
        int i17 = this.f14960b;
        if (i17 == 0) {
            this.f14869S0 = left - rect.left;
        } else {
            this.f14869S0 += iMax2;
        }
        int i18 = i17 + childCount;
        int i19 = this.f14976r;
        if (i18 == i19) {
            this.f14871T0 = rect.right + right;
        } else {
            this.f14871T0 += iMax2;
        }
        boolean z8 = i17 == 0 && left >= rect.left && iMax2 >= 0;
        boolean z9 = i18 == i19 && right <= getWidth() - rect.right && iMax2 <= 0;
        if (z8 || z9) {
            return iMax2 != 0;
        }
        boolean z10 = iMax2 < 0;
        boolean zIsInTouchMode = isInTouchMode();
        if (zIsInTouchMode) {
            m17137c0();
        }
        int headerViewsCount = getHeaderViewsCount();
        int footerViewsCount = this.f14976r - getFooterViewsCount();
        if (z10) {
            int i20 = -iMax2;
            int i21 = 0;
            i12 = 0;
            while (i21 < childCount) {
                View childAt = getChildAt(i21);
                if (childAt.getRight() >= i20) {
                    break;
                }
                i12++;
                int i22 = i17 + i21;
                if (i22 < headerViewsCount || i22 >= footerViewsCount) {
                    i14 = childCount;
                } else {
                    i14 = childCount;
                    this.f14854L.m17176b(childAt, i22);
                }
                i21++;
                childCount = i14;
            }
            i11 = 0;
        } else {
            int width3 = getWidth() - iMax2;
            i11 = 0;
            i12 = 0;
            while (i15 >= 0) {
                View childAt2 = getChildAt(i15);
                if (childAt2.getLeft() <= width3) {
                    break;
                }
                i12++;
                int i23 = i17 + i15;
                if (i23 >= headerViewsCount && i23 < footerViewsCount) {
                    this.f14854L.m17176b(childAt2, i23);
                }
                int i24 = i15;
                i15--;
                i11 = i24;
            }
        }
        this.f14883b0 = this.f14881a0 + iMax;
        this.f14983y = true;
        if (i12 > 0) {
            detachViewsFromParent(i11, i12);
            this.f14854L.m17185k();
        }
        if (!awakenScrollBars()) {
            invalidate();
        }
        m17146m0(iMax2);
        if (z10) {
            this.f14960b += i12;
        }
        int iAbs = Math.abs(iMax2);
        if (i16 < iAbs || width < iAbs) {
            mo17131V(z10);
        }
        if (zIsInTouchMode || (i13 = this.f14973o) == -1) {
            int i25 = this.f14850J;
            if (i25 != -1) {
                int i26 = i25 - this.f14960b;
                if (i26 >= 0 && i26 < getChildCount()) {
                    m17152r0(-1, getChildAt(i26));
                }
            } else {
                this.f14852K.setEmpty();
            }
        } else {
            int i27 = i13 - this.f14960b;
            if (i27 >= 0 && i27 < getChildCount()) {
                m17152r0(this.f14973o, getChildAt(i27));
            }
        }
        this.f14983y = false;
        m17141g0();
        return false;
    }

    /* renamed from: J0 */
    public final void m17120J0() {
        int i9 = this.f14960b;
        int childCount = getChildCount();
        for (int i10 = 0; i10 < childCount; i10++) {
            KeyEvent.Callback childAt = getChildAt(i10);
            int i11 = i9 + i10;
            if (childAt instanceof Checkable) {
                ((Checkable) childAt).setChecked(this.f14834B.get(i11));
            }
        }
    }

    /* renamed from: K0 */
    public void m17121K0() {
        if (this.f14868S != null) {
            boolean z8 = this.f14960b > 0;
            if (!z8 && getChildCount() > 0) {
                z8 = getChildAt(0).getLeft() < this.f14864Q.left;
            }
            this.f14868S.setVisibility(z8 ? 0 : 4);
        }
        if (this.f14870T != null) {
            int childCount = getChildCount();
            boolean z9 = this.f14960b + childCount < this.f14976r;
            if (!z9 && childCount > 0) {
                z9 = getChildAt(childCount - 1).getRight() > getRight() - this.f14864Q.right;
            }
            this.f14870T.setVisibility(z9 ? 0 : 4);
        }
    }

    /* renamed from: L0 */
    public void m17122L0() {
        if (this.f14848I != null) {
            if (m17113C0()) {
                this.f14848I.setState(getDrawableState());
            } else {
                this.f14848I.setState(new int[]{0});
            }
        }
    }

    /* renamed from: M0 */
    public final void m17123M0() {
        setSelector(getResources().getDrawable(R.drawable.list_selector_background));
    }

    /* renamed from: O */
    public void m17124O() {
        SparseBooleanArray sparseBooleanArray = this.f14834B;
        if (sparseBooleanArray != null) {
            sparseBooleanArray.clear();
        }
        C5305d<Integer> c5305d = this.f14836C;
        if (c5305d != null) {
            c5305d.m20718a();
        }
        this.f14832A = 0;
    }

    /* renamed from: P */
    public final void m17125P() {
        if (m17103h0(this)) {
            return;
        }
        if (this.f14847H0 == null) {
            this.f14847H0 = new RunnableC3212b();
        }
        post(this.f14847H0);
    }

    /* renamed from: Q */
    public void m17126Q() {
        boolean z8;
        this.f14834B.clear();
        int i9 = 0;
        while (i9 < this.f14836C.m20730n()) {
            long jM20725i = this.f14836C.m20725i(i9);
            int iIntValue = this.f14836C.m20731o(i9).intValue();
            if (jM20725i != this.f14842F.getItemId(iIntValue)) {
                int iMax = Math.max(0, iIntValue - 20);
                int iMin = Math.min(iIntValue + 20, this.f14976r);
                while (true) {
                    if (iMax >= iMin) {
                        z8 = false;
                        break;
                    } else {
                        if (jM20725i == this.f14842F.getItemId(iMax)) {
                            this.f14834B.put(iMax, true);
                            this.f14836C.m20729m(i9, Integer.valueOf(iMax));
                            z8 = true;
                            break;
                        }
                        iMax++;
                    }
                }
                if (!z8) {
                    this.f14836C.m20720c(jM20725i);
                    i9--;
                    this.f14832A--;
                }
            } else {
                this.f14834B.put(iIntValue, true);
            }
            i9++;
        }
    }

    /* renamed from: R */
    public final boolean m17127R() {
        int childCount = getChildCount();
        if (childCount == 0) {
            return true;
        }
        if (childCount != this.f14976r) {
            return false;
        }
        return getChildAt(0).getLeft() >= this.f14864Q.left && getChildAt(childCount - 1).getRight() <= getWidth() - this.f14864Q.right;
    }

    /* renamed from: S */
    public ContextMenu.ContextMenuInfo m17128S(View view, int i9, long j9) {
        return new AdapterView.ContextMenuContextMenuInfoC3226b(view, i9, j9);
    }

    /* renamed from: T */
    public final void m17129T() {
        if (!this.f14897m0 || this.f14872U || m17103h0(this)) {
            return;
        }
        setChildrenDrawnWithCacheEnabled(true);
        setChildrenDrawingCacheEnabled(true);
        this.f14874V = true;
        this.f14872U = true;
    }

    /* renamed from: U */
    public final void m17130U(Canvas canvas) {
        if (this.f14852K.isEmpty()) {
            return;
        }
        Drawable drawable = this.f14848I;
        drawable.setBounds(this.f14852K);
        drawable.draw(canvas);
    }

    /* renamed from: V */
    public abstract void mo17131V(boolean z8);

    /* renamed from: W */
    public int m17132W(int i9) {
        if (getChildCount() == 0) {
            return -1;
        }
        int iMo17133X = mo17133X(i9);
        return iMo17133X != -1 ? iMo17133X : (this.f14960b + r0) - 1;
    }

    /* renamed from: X */
    public abstract int mo17133X(int i9);

    /* renamed from: Y */
    public final void m17134Y() {
        C3233a c3233a = this.f14865Q0;
        if (c3233a != null) {
            c3233a.m17238b();
            this.f14867R0.m17238b();
        }
    }

    @Override // android.view.ViewGroup
    /* renamed from: Z, reason: merged with bridge method [inline-methods] */
    public C3217g generateLayoutParams(AttributeSet attributeSet) {
        return new C3217g(getContext(), attributeSet);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void addTouchables(ArrayList<View> arrayList) {
        int childCount = getChildCount();
        int i9 = this.f14960b;
        ListAdapter listAdapter = this.f14842F;
        if (listAdapter == null) {
            return;
        }
        for (int i10 = 0; i10 < childCount; i10++) {
            View childAt = getChildAt(i10);
            if (listAdapter.isEnabled(i9 + i10)) {
                arrayList.add(childAt);
            }
            childAt.addTouchables(arrayList);
        }
    }

    /* renamed from: b0 */
    public void m17136b0() {
        ListAdapter listAdapter;
        int i9 = this.f14976r;
        int i10 = this.f14884b1;
        this.f14884b1 = i9;
        if (this.f14910z != 0 && (listAdapter = this.f14842F) != null && listAdapter.hasStableIds()) {
            m17126Q();
        }
        this.f14854L.m17178d();
        if (i9 > 0) {
            if (this.f14965g) {
                this.f14965g = false;
                this.f14886c1 = null;
                int i11 = this.f14835B0;
                if (i11 == 2) {
                    this.f14838D = 3;
                    return;
                }
                if (i11 == 1) {
                    if (this.f14875V0) {
                        this.f14875V0 = false;
                        this.f14838D = 3;
                        return;
                    }
                    int childCount = getChildCount();
                    int width = getWidth() - getPaddingRight();
                    View childAt = getChildAt(childCount - 1);
                    int right = childAt != null ? childAt.getRight() : width;
                    if (this.f14960b + childCount >= i10 && right <= width) {
                        this.f14838D = 3;
                        return;
                    }
                    awakenScrollBars();
                }
                int i12 = this.f14966h;
                if (i12 != 0) {
                    if (i12 == 1) {
                        this.f14838D = 5;
                        this.f14962d = Math.min(Math.max(0, this.f14962d), i9 - 1);
                        return;
                    }
                } else {
                    if (isInTouchMode()) {
                        this.f14838D = 5;
                        this.f14962d = Math.min(Math.max(0, this.f14962d), i9 - 1);
                        return;
                    }
                    int iM17199h = m17199h();
                    if (iM17199h >= 0 && mo17205n(iM17199h, true) == iM17199h) {
                        this.f14962d = iM17199h;
                        if (this.f14964f == getHeight()) {
                            this.f14838D = 5;
                        } else {
                            this.f14838D = 2;
                        }
                        setNextSelectedPositionInt(iM17199h);
                        return;
                    }
                }
            }
            if (!isInTouchMode()) {
                int selectedItemPosition = getSelectedItemPosition();
                if (selectedItemPosition >= i9) {
                    selectedItemPosition = i9 - 1;
                }
                if (selectedItemPosition < 0) {
                    selectedItemPosition = 0;
                }
                int iMo17205n = mo17205n(selectedItemPosition, true);
                if (iMo17205n >= 0) {
                    setNextSelectedPositionInt(iMo17205n);
                    return;
                }
                int iMo17205n2 = mo17205n(selectedItemPosition, false);
                if (iMo17205n2 >= 0) {
                    setNextSelectedPositionInt(iMo17205n2);
                    return;
                }
            } else if (this.f14901q0 >= 0) {
                return;
            }
        }
        this.f14838D = this.f14896l0 ? 3 : 1;
        this.f14973o = -1;
        this.f14974p = Long.MIN_VALUE;
        this.f14971m = -1;
        this.f14972n = Long.MIN_VALUE;
        this.f14965g = false;
        this.f14886c1 = null;
        this.f14850J = -1;
        m17198g();
    }

    /* renamed from: c0 */
    public void m17137c0() {
        int i9 = this.f14973o;
        if (i9 != -1) {
            if (this.f14838D != 4) {
                this.f14901q0 = i9;
            }
            int i10 = this.f14971m;
            if (i10 >= 0 && i10 != i9) {
                this.f14901q0 = i10;
            }
            setSelectedPositionInt(-1);
            setNextSelectedPositionInt(-1);
            this.f14895k0 = 0;
        }
    }

    @Override // android.view.ViewGroup
    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof C3217g;
    }

    @Override // android.view.View
    public int computeHorizontalScrollExtent() {
        int childCount = getChildCount();
        if (childCount <= 0) {
            return 0;
        }
        if (!this.f14899o0) {
            return 1;
        }
        int i9 = childCount * 100;
        View childAt = getChildAt(0);
        int left = childAt.getLeft();
        int width = childAt.getWidth();
        if (width > 0) {
            i9 += (left * 100) / width;
        }
        View childAt2 = getChildAt(childCount - 1);
        int right = childAt2.getRight();
        int width2 = childAt2.getWidth();
        return width2 > 0 ? i9 - (((right - getWidth()) * 100) / width2) : i9;
    }

    @Override // android.view.View
    public int computeHorizontalScrollOffset() {
        int i9 = this.f14960b;
        int childCount = getChildCount();
        if (i9 >= 0 && childCount > 0) {
            if (!this.f14899o0) {
                int i10 = this.f14976r;
                return (int) (i9 + (childCount * ((i9 != 0 ? i9 + childCount == i10 ? i10 : (childCount / 2) + i9 : 0) / i10)));
            }
            View childAt = getChildAt(0);
            int left = childAt.getLeft();
            int width = childAt.getWidth();
            if (width > 0) {
                return Math.max(((i9 * 100) - ((left * 100) / width)) + ((int) ((getScrollX() / getWidth()) * this.f14976r * 100.0f)), 0);
            }
        }
        return 0;
    }

    @Override // android.view.View
    public int computeHorizontalScrollRange() {
        if (!this.f14899o0) {
            return this.f14976r;
        }
        int iMax = Math.max(this.f14976r * 100, 0);
        return getScrollX() != 0 ? iMax + Math.abs((int) ((getScrollX() / getWidth()) * this.f14976r * 100.0f)) : iMax;
    }

    /* renamed from: d0 */
    public final void m17138d0() {
        setClickable(true);
        setFocusableInTouchMode(true);
        setWillNotDraw(false);
        setAlwaysDrawnWithCacheEnabled(false);
        setScrollingCacheEnabled(true);
        ViewConfiguration viewConfiguration = ViewConfiguration.get(getContext());
        this.f14843F0 = viewConfiguration.getScaledTouchSlop();
        this.f14851J0 = viewConfiguration.getScaledMinimumFlingVelocity();
        this.f14853K0 = viewConfiguration.getScaledMaximumFlingVelocity();
        this.f14861O0 = viewConfiguration.getScaledOverscrollDistance();
        this.f14863P0 = viewConfiguration.getScaledOverflingDistance();
        this.f14845G0 = getContext().getResources().getDisplayMetrics().density;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas canvas) {
        boolean z8 = this.f14846H;
        if (!z8) {
            m17130U(canvas);
        }
        super.dispatchDraw(canvas);
        if (z8) {
            m17130U(canvas);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchSetPressed(boolean z8) {
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (this.f14865Q0 != null) {
            int scrollX = getScrollX();
            if (!this.f14865Q0.m17240d()) {
                int iSave = canvas.save();
                Rect rect = this.f14864Q;
                int i9 = rect.top + this.f14877W0;
                int height = (getHeight() - i9) - (rect.bottom + this.f14878X0);
                int iMin = Math.min(0, this.f14869S0 + scrollX);
                canvas.translate(iMin, i9);
                this.f14865Q0.m17245i(getWidth(), height);
                if (this.f14865Q0.m17237a(canvas)) {
                    this.f14865Q0.m17244h(iMin, i9);
                    invalidate(this.f14865Q0.m17239c(false));
                }
                canvas.restoreToCount(iSave);
            }
            if (this.f14867R0.m17240d()) {
                return;
            }
            int iSave2 = canvas.save();
            Rect rect2 = this.f14864Q;
            int i10 = rect2.top + this.f14877W0;
            int i11 = rect2.bottom + this.f14878X0;
            int width = getWidth();
            int height2 = (getHeight() - i10) - i11;
            int iMax = Math.max(width, scrollX + this.f14871T0);
            int i12 = (-height2) + i10;
            canvas.translate(iMax, i12);
            canvas.rotate(180.0f, BitmapDescriptorFactory.HUE_RED, height2);
            this.f14867R0.m17245i(width, height2);
            if (this.f14867R0.m17237a(canvas)) {
                this.f14867R0.m17244h(iMax, i12 + height2);
                invalidate(this.f14867R0.m17239c(true));
            }
            canvas.restoreToCount(iSave2);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        m17122L0();
    }

    /* renamed from: e0 */
    public final void m17139e0() {
        VelocityTracker velocityTracker = this.f14892h0;
        if (velocityTracker == null) {
            this.f14892h0 = VelocityTracker.obtain();
        } else {
            velocityTracker.clear();
        }
    }

    /* renamed from: f0 */
    public final void m17140f0() {
        if (this.f14892h0 == null) {
            this.f14892h0 = VelocityTracker.obtain();
        }
    }

    /* renamed from: g0 */
    public void m17141g0() {
        InterfaceC3218h interfaceC3218h = this.f14898n0;
        if (interfaceC3218h != null) {
            interfaceC3218h.mo17171b(this, this.f14960b, getChildCount(), this.f14976r);
        }
        onScrollChanged(0, 0, 0, 0);
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new C3217g(-1, -2, 0);
    }

    @ViewDebug.ExportedProperty(category = "drawing")
    public int getCacheColorHint() {
        return this.f14837C0;
    }

    public int getCheckedItemCount() {
        return this.f14832A;
    }

    public long[] getCheckedItemIds() {
        C5305d<Integer> c5305d;
        if (this.f14910z == 0 || (c5305d = this.f14836C) == null || this.f14842F == null) {
            return new long[0];
        }
        int iM20730n = c5305d.m20730n();
        long[] jArr = new long[iM20730n];
        for (int i9 = 0; i9 < iM20730n; i9++) {
            jArr[i9] = c5305d.m20725i(i9);
        }
        return jArr;
    }

    public int getCheckedItemPosition() {
        SparseBooleanArray sparseBooleanArray;
        if (this.f14910z == 1 && (sparseBooleanArray = this.f14834B) != null && sparseBooleanArray.size() == 1) {
            return this.f14834B.keyAt(0);
        }
        return -1;
    }

    public SparseBooleanArray getCheckedItemPositions() {
        if (this.f14910z != 0) {
            return this.f14834B;
        }
        return null;
    }

    public int getChoiceMode() {
        return this.f14910z;
    }

    @Override // android.view.View
    public ContextMenu.ContextMenuInfo getContextMenuInfo() {
        return this.f14902r0;
    }

    @Override // android.view.View
    public void getFocusedRect(Rect rect) {
        View selectedView = getSelectedView();
        if (selectedView == null || selectedView.getParent() != this) {
            super.getFocusedRect(rect);
        } else {
            selectedView.getFocusedRect(rect);
            offsetDescendantRectToMyCoords(selectedView, rect);
        }
    }

    public int getFooterViewsCount() {
        return 0;
    }

    public int getHeaderViewsCount() {
        return 0;
    }

    @Override // android.view.View
    public float getLeftFadingEdgeStrength() {
        int childCount = getChildCount();
        float leftFadingEdgeStrength = super.getLeftFadingEdgeStrength();
        if (childCount == 0) {
            return leftFadingEdgeStrength;
        }
        if (this.f14960b > 0) {
            return 1.0f;
        }
        return getChildAt(0).getLeft() < getPaddingLeft() ? (-(r0 - getPaddingLeft())) / getVerticalFadingEdgeLength() : leftFadingEdgeStrength;
    }

    public int getListPaddingBottom() {
        return this.f14864Q.bottom;
    }

    public int getListPaddingLeft() {
        return this.f14864Q.left;
    }

    public int getListPaddingRight() {
        return this.f14864Q.right;
    }

    public int getListPaddingTop() {
        return this.f14864Q.top;
    }

    @Override // android.view.View
    public float getRightFadingEdgeStrength() {
        int childCount = getChildCount();
        float rightFadingEdgeStrength = super.getRightFadingEdgeStrength();
        if (childCount == 0) {
            return rightFadingEdgeStrength;
        }
        if ((this.f14960b + childCount) - 1 < this.f14976r - 1) {
            return 1.0f;
        }
        return getChildAt(childCount - 1).getRight() > getWidth() - getPaddingRight() ? ((r0 - r2) + getPaddingRight()) / getVerticalFadingEdgeLength() : rightFadingEdgeStrength;
    }

    @Override // com.cyberlink.you.widgetpool.clhorizontalgridview.AdapterView
    @ViewDebug.ExportedProperty
    public View getSelectedView() {
        int i9;
        if (this.f14976r <= 0 || (i9 = this.f14973o) < 0) {
            return null;
        }
        return getChildAt(i9 - this.f14960b);
    }

    public Drawable getSelector() {
        return this.f14848I;
    }

    @Override // android.view.View
    public int getSolidColor() {
        return this.f14837C0;
    }

    public int getTranscriptMode() {
        return this.f14835B0;
    }

    public float getVerticalScrollFactor() {
        if (this.f14888d1 == BitmapDescriptorFactory.HUE_RED) {
            TypedValue typedValue = new TypedValue();
            if (!getContext().getTheme().resolveAttribute(R.attr.listPreferredItemHeight, typedValue, true)) {
                throw new IllegalStateException("Expected theme to define listPreferredItemHeight.");
            }
            this.f14888d1 = typedValue.getDimension(getContext().getResources().getDisplayMetrics());
        }
        return this.f14888d1;
    }

    @ViewDebug.ExportedProperty
    /* renamed from: i0 */
    public boolean m17142i0() {
        return this.f14896l0;
    }

    /* renamed from: j0 */
    public void m17143j0() {
        if (isEnabled() && isClickable()) {
            Drawable drawable = this.f14848I;
            Rect rect = this.f14852K;
            if (drawable != null) {
                if ((isFocused() || m17118H0()) && !rect.isEmpty()) {
                    View childAt = getChildAt(this.f14973o - this.f14960b);
                    if (childAt != null) {
                        if (childAt.hasFocusable()) {
                            return;
                        } else {
                            childAt.setPressed(true);
                        }
                    }
                    setPressed(true);
                    boolean zIsLongClickable = isLongClickable();
                    Drawable current = drawable.getCurrent();
                    if (current != null && (current instanceof TransitionDrawable)) {
                        if (zIsLongClickable) {
                            ((TransitionDrawable) current).startTransition(ViewConfiguration.getLongPressTimeout());
                        } else {
                            ((TransitionDrawable) current).resetTransition();
                        }
                    }
                    if (!zIsLongClickable || this.f14970l) {
                        return;
                    }
                    if (this.f14909y0 == null) {
                        this.f14909y0 = new RunnableC3213c(this, null);
                    }
                    this.f14909y0.m17190a();
                    postDelayed(this.f14909y0, ViewConfiguration.getLongPressTimeout());
                }
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
        Drawable drawable = this.f14848I;
        if (drawable != null) {
            drawable.jumpToCurrentState();
        }
    }

    /* renamed from: k0 */
    public void mo17144k0() {
    }

    /* renamed from: l0 */
    public View m17145l0(int i9, boolean[] zArr) {
        View view;
        zArr[0] = false;
        View viewM17182h = this.f14854L.m17182h(i9);
        if (viewM17182h != null) {
            return viewM17182h;
        }
        View viewM17181g = this.f14854L.m17181g(i9);
        if (viewM17181g != null) {
            view = this.f14842F.getView(i9, viewM17181g, this);
            if (view != viewM17181g) {
                this.f14854L.m17176b(viewM17181g, i9);
                int i10 = this.f14837C0;
                if (i10 != 0) {
                    view.setDrawingCacheBackgroundColor(i10);
                }
            } else {
                zArr[0] = true;
            }
        } else {
            view = this.f14842F.getView(i9, null, this);
            int i11 = this.f14837C0;
            if (i11 != 0) {
                view.setDrawingCacheBackgroundColor(i11);
            }
        }
        if (this.f14844G) {
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            C3217g c3217g = layoutParams == null ? (C3217g) generateDefaultLayoutParams() : !checkLayoutParams(layoutParams) ? (C3217g) generateLayoutParams(layoutParams) : (C3217g) layoutParams;
            c3217g.f14937d = this.f14842F.getItemId(i9);
            view.setLayoutParams(c3217g);
        }
        return view;
    }

    /* renamed from: m0 */
    public void m17146m0(int i9) {
        int childCount = getChildCount();
        for (int i10 = 0; i10 < childCount; i10++) {
            getChildAt(i10).offsetLeftAndRight(i9);
        }
    }

    /* renamed from: n0 */
    public final void m17147n0(MotionEvent motionEvent) {
        int action = (motionEvent.getAction() & 65280) >> 8;
        if (motionEvent.getPointerId(action) == this.f14859N0) {
            int i9 = action == 0 ? 1 : 0;
            this.f14885c0 = (int) motionEvent.getX(i9);
            this.f14887d0 = (int) motionEvent.getY(i9);
            this.f14891g0 = 0;
            this.f14859N0 = motionEvent.getPointerId(i9);
        }
    }

    /* renamed from: o0 */
    public boolean m17148o0(View view, int i9, long j9) {
        if (this.f14910z == 3) {
            return true;
        }
        this.f14902r0 = m17128S(view, i9, j9);
        boolean zShowContextMenuForChild = super.showContextMenuForChild(this);
        if (zShowContextMenuForChild) {
            performHapticFeedback(0);
        }
        return zShowContextMenuForChild;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.f14842F != null && this.f14840E == null) {
            AdapterView<ListAdapter>.C3227c c3227c = new AdapterView.C3227c();
            this.f14840E = c3227c;
            this.f14842F.registerDataSetObserver(c3227c);
            this.f14970l = true;
            this.f14977s = this.f14976r;
            this.f14976r = this.f14842F.getCount();
        }
        this.f14882a1 = true;
    }

    @Override // android.view.ViewGroup, android.view.View
    public int[] onCreateDrawableState(int i9) {
        if (this.f14839D0) {
            return super.onCreateDrawableState(i9);
        }
        int i10 = ViewGroup.ENABLED_STATE_SET[0];
        int[] iArrOnCreateDrawableState = super.onCreateDrawableState(i9 + 1);
        int length = iArrOnCreateDrawableState.length - 1;
        while (true) {
            if (length < 0) {
                length = -1;
                break;
            }
            if (iArrOnCreateDrawableState[length] == i10) {
                break;
            }
            length--;
        }
        if (length >= 0) {
            System.arraycopy(iArrOnCreateDrawableState, length + 1, iArrOnCreateDrawableState, length, (iArrOnCreateDrawableState.length - length) - 1);
        }
        return iArrOnCreateDrawableState;
    }

    @Override // com.cyberlink.you.widgetpool.clhorizontalgridview.AdapterView, android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.f14854L.m17177c();
        ListAdapter listAdapter = this.f14842F;
        if (listAdapter != null) {
            listAdapter.unregisterDataSetObserver(this.f14840E);
            this.f14840E = null;
        }
        RunnableC3216f runnableC3216f = this.f14893i0;
        if (runnableC3216f != null) {
            removeCallbacks(runnableC3216f);
        }
        RunnableC3220j runnableC3220j = this.f14894j0;
        if (runnableC3220j != null) {
            runnableC3220j.m17174c();
        }
        Runnable runnable = this.f14847H0;
        if (runnable != null) {
            removeCallbacks(runnable);
        }
        RunnableC3219i runnableC3219i = this.f14911z0;
        if (runnableC3219i != null) {
            removeCallbacks(runnableC3219i);
        }
        Runnable runnable2 = this.f14833A0;
        if (runnable2 != null) {
            removeCallbacks(runnable2);
            this.f14833A0 = null;
        }
        this.f14882a1 = false;
    }

    @Override // android.view.View
    public void onFocusChanged(boolean z8, int i9, Rect rect) {
        ListAdapter listAdapter;
        super.onFocusChanged(z8, i9, rect);
        if (!z8 || this.f14973o >= 0 || isInTouchMode()) {
            return;
        }
        if (!this.f14882a1 && (listAdapter = this.f14842F) != null) {
            this.f14970l = true;
            this.f14977s = this.f14976r;
            this.f14976r = listAdapter.getCount();
        }
        m17158x0();
    }

    @Override // android.view.View
    public boolean onGenericMotionEvent(MotionEvent motionEvent) {
        if ((motionEvent.getSource() & 2) != 0 && motionEvent.getAction() == 8 && this.f14889e0 == -1) {
            float axisValue = motionEvent.getAxisValue(9);
            if (axisValue != BitmapDescriptorFactory.HUE_RED) {
                int verticalScrollFactor = (int) (axisValue * getVerticalScrollFactor());
                if (!m17119I0(verticalScrollFactor, verticalScrollFactor)) {
                    return true;
                }
            }
        }
        return super.onGenericMotionEvent(motionEvent);
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x0052  */
    @Override // android.view.ViewGroup
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        RunnableC3220j runnableC3220j = this.f14894j0;
        if (runnableC3220j != null) {
            runnableC3220j.m17174c();
        }
        if (!this.f14882a1) {
            return false;
        }
        int i9 = action & 255;
        if (i9 == 0) {
            int i10 = this.f14889e0;
            if (i10 == 6 || i10 == 5) {
                this.f14891g0 = 0;
                return true;
            }
            int x8 = (int) motionEvent.getX();
            int y8 = (int) motionEvent.getY();
            this.f14859N0 = motionEvent.getPointerId(0);
            int iMo17133X = mo17133X(x8);
            if (i10 != 4 && iMo17133X >= 0) {
                this.f14881a0 = getChildAt(iMo17133X - this.f14960b).getLeft();
                this.f14885c0 = x8;
                this.f14887d0 = y8;
                this.f14876W = iMo17133X;
                this.f14889e0 = 0;
                m17125P();
            }
            this.f14890f0 = Integer.MIN_VALUE;
            m17139e0();
            this.f14892h0.addMovement(motionEvent);
            if (i10 == 4) {
                return true;
            }
        } else if (i9 == 1) {
            this.f14889e0 = -1;
            this.f14859N0 = -1;
            m17154t0();
            m17155u0(0);
        } else if (i9 != 2) {
            if (i9 != 3) {
                if (i9 == 6) {
                    m17147n0(motionEvent);
                }
            }
        } else if (this.f14889e0 == 0) {
            int iFindPointerIndex = motionEvent.findPointerIndex(this.f14859N0);
            if (iFindPointerIndex == -1) {
                this.f14859N0 = motionEvent.getPointerId(0);
                iFindPointerIndex = 0;
            }
            int x9 = (int) motionEvent.getX(iFindPointerIndex);
            m17140f0();
            this.f14892h0.addMovement(motionEvent);
            if (m17117G0(x9)) {
                return true;
            }
        }
        return false;
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i9, KeyEvent keyEvent) {
        return false;
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyUp(int i9, KeyEvent keyEvent) {
        int i10;
        ListAdapter listAdapter;
        if (i9 == 23 || i9 == 66) {
            if (!isEnabled()) {
                return true;
            }
            if (isClickable() && isPressed() && (i10 = this.f14973o) >= 0 && (listAdapter = this.f14842F) != null && i10 < listAdapter.getCount()) {
                View childAt = getChildAt(this.f14973o - this.f14960b);
                if (childAt != null) {
                    mo17149p(childAt, this.f14973o, this.f14974p);
                    childAt.setPressed(false);
                }
                setPressed(false);
                return true;
            }
        }
        return super.onKeyUp(i9, keyEvent);
    }

    @Override // com.cyberlink.you.widgetpool.clhorizontalgridview.AdapterView, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z8, int i9, int i10, int i11, int i12) {
        super.onLayout(z8, i9, i10, i11, i12);
        this.f14968j = true;
        if (z8) {
            int childCount = getChildCount();
            for (int i13 = 0; i13 < childCount; i13++) {
                getChildAt(i13).forceLayout();
            }
            this.f14854L.m17183i();
        }
        mo17144k0();
        this.f14968j = false;
        this.f14903s0 = (i11 - i9) / 3;
    }

    @Override // android.view.View
    public void onMeasure(int i9, int i10) {
        if (this.f14848I == null) {
            m17123M0();
        }
        Rect rect = this.f14864Q;
        rect.left = this.f14856M + getPaddingLeft();
        rect.top = this.f14858N + getPaddingTop();
        rect.right = this.f14860O + getPaddingRight();
        rect.bottom = this.f14862P + getPaddingBottom();
        if (this.f14835B0 == 1) {
            int childCount = getChildCount();
            int width = getWidth() - getPaddingRight();
            View childAt = getChildAt(childCount - 1);
            this.f14875V0 = this.f14960b + childCount >= this.f14884b1 && (childAt != null ? childAt.getRight() : width) <= width;
        }
    }

    @Override // android.view.View
    public void onOverScrolled(int i9, int i10, boolean z8, boolean z9) {
        int scrollX = getScrollX();
        int scrollY = getScrollY();
        if (scrollX != i9) {
            onScrollChanged(i9, scrollY, scrollX, scrollY);
            m17089B0(this, i9);
            awakenScrollBars();
        }
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        this.f14970l = true;
        this.f14964f = savedState.f14916f;
        long j9 = savedState.f14912b;
        if (j9 >= 0) {
            this.f14965g = true;
            this.f14886c1 = savedState;
            this.f14963e = j9;
            this.f14962d = savedState.f14915e;
            this.f14961c = savedState.f14914d;
            this.f14966h = 0;
        } else if (savedState.f14913c >= 0) {
            setSelectedPositionInt(-1);
            setNextSelectedPositionInt(-1);
            this.f14850J = -1;
            this.f14965g = true;
            this.f14886c1 = savedState;
            this.f14963e = savedState.f14913c;
            this.f14962d = savedState.f14915e;
            this.f14961c = savedState.f14914d;
            this.f14966h = 1;
        }
        SparseBooleanArray sparseBooleanArray = savedState.f14920j;
        if (sparseBooleanArray != null) {
            this.f14834B = sparseBooleanArray;
        }
        C5305d<Integer> c5305d = savedState.f14921k;
        if (c5305d != null) {
            this.f14836C = c5305d;
        }
        this.f14832A = savedState.f14919i;
        requestLayout();
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        SavedState savedState2 = this.f14886c1;
        if (savedState2 != null) {
            savedState.f14912b = savedState2.f14912b;
            savedState.f14913c = savedState2.f14913c;
            savedState.f14914d = savedState2.f14914d;
            savedState.f14915e = savedState2.f14915e;
            savedState.f14916f = savedState2.f14916f;
            savedState.f14917g = savedState2.f14917g;
            savedState.f14918h = savedState2.f14918h;
            savedState.f14919i = savedState2.f14919i;
            savedState.f14920j = savedState2.f14920j;
            savedState.f14921k = savedState2.f14921k;
            return savedState;
        }
        boolean z8 = getChildCount() > 0 && this.f14976r > 0;
        long selectedItemId = getSelectedItemId();
        savedState.f14912b = selectedItemId;
        savedState.f14916f = getHeight();
        if (selectedItemId >= 0) {
            savedState.f14914d = this.f14895k0;
            savedState.f14915e = getSelectedItemPosition();
            savedState.f14913c = -1L;
        } else if (!z8 || this.f14960b <= 0) {
            savedState.f14914d = 0;
            savedState.f14913c = -1L;
            savedState.f14915e = 0;
        } else {
            savedState.f14914d = getChildAt(0).getLeft();
            int i9 = this.f14960b;
            int i10 = this.f14976r;
            if (i9 >= i10) {
                i9 = i10 - 1;
            }
            savedState.f14915e = i9;
            savedState.f14913c = this.f14842F.getItemId(i9);
        }
        savedState.f14917g = null;
        savedState.f14918h = false;
        SparseBooleanArray sparseBooleanArray = this.f14834B;
        if (sparseBooleanArray != null) {
            savedState.f14920j = sparseBooleanArray.clone();
        }
        if (this.f14836C != null) {
            C5305d<Integer> c5305d = new C5305d<>();
            int iM20730n = this.f14836C.m20730n();
            for (int i11 = 0; i11 < iM20730n; i11++) {
                c5305d.m20726j(this.f14836C.m20725i(i11), this.f14836C.m20731o(i11));
            }
            savedState.f14921k = c5305d;
        }
        savedState.f14919i = this.f14832A;
        return savedState;
    }

    @Override // android.view.View
    public void onSizeChanged(int i9, int i10, int i11, int i12) {
        if (getChildCount() > 0) {
            this.f14970l = true;
            m17207q();
        }
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        Drawable current;
        int i9;
        int i10 = 0;
        if (!isEnabled()) {
            return isClickable() || isLongClickable();
        }
        RunnableC3220j runnableC3220j = this.f14894j0;
        if (runnableC3220j != null) {
            runnableC3220j.m17174c();
        }
        if (!this.f14882a1) {
            return false;
        }
        int action = motionEvent.getAction();
        m17140f0();
        this.f14892h0.addMovement(motionEvent);
        int i11 = action & 255;
        if (i11 != 0) {
            if (i11 == 1) {
                int i12 = this.f14889e0;
                if (i12 == 0 || i12 == 1 || i12 == 2) {
                    int i13 = this.f14876W;
                    View childAt = getChildAt(i13 - this.f14960b);
                    float y8 = motionEvent.getY();
                    boolean z8 = y8 > ((float) this.f14864Q.top) && y8 < ((float) (getHeight() - this.f14864Q.bottom));
                    if (childAt != null && !childAt.hasFocusable() && z8) {
                        if (this.f14889e0 != 0) {
                            childAt.setPressed(false);
                        }
                        if (this.f14911z0 == null) {
                            this.f14911z0 = new RunnableC3219i(this, null);
                        }
                        RunnableC3219i runnableC3219i = this.f14911z0;
                        runnableC3219i.f14938d = i13;
                        runnableC3219i.m17190a();
                        this.f14901q0 = i13;
                        int i14 = this.f14889e0;
                        if (i14 == 0 || i14 == 1) {
                            Handler handler = getHandler();
                            if (handler != null) {
                                handler.removeCallbacks(this.f14889e0 == 0 ? this.f14908x0 : this.f14907w0);
                            }
                            this.f14838D = 0;
                            if (this.f14970l || !this.f14842F.isEnabled(i13)) {
                                this.f14889e0 = -1;
                                m17122L0();
                            } else {
                                this.f14889e0 = 1;
                                setSelectedPositionInt(this.f14876W);
                                mo17144k0();
                                childAt.setPressed(true);
                                m17152r0(this.f14876W, childAt);
                                setPressed(true);
                                Drawable drawable = this.f14848I;
                                if (drawable != null && (current = drawable.getCurrent()) != null && (current instanceof TransitionDrawable)) {
                                    ((TransitionDrawable) current).resetTransition();
                                }
                                Runnable runnable = this.f14833A0;
                                if (runnable != null) {
                                    removeCallbacks(runnable);
                                }
                                RunnableC3211a runnableC3211a = new RunnableC3211a(childAt, runnableC3219i);
                                this.f14833A0 = runnableC3211a;
                                postDelayed(runnableC3211a, ViewConfiguration.getPressedStateDuration());
                            }
                            return true;
                        }
                        if (!this.f14970l && this.f14842F.isEnabled(i13)) {
                            runnableC3219i.run();
                        }
                    }
                    this.f14889e0 = -1;
                    m17122L0();
                } else if (i12 == 3) {
                    int childCount = getChildCount();
                    if (childCount > 0) {
                        int left = getChildAt(0).getLeft();
                        int right = getChildAt(childCount - 1).getRight();
                        int i15 = this.f14864Q.left;
                        int width = getWidth() - this.f14864Q.right;
                        int i16 = this.f14960b;
                        if (i16 != 0 || left < i15 || i16 + childCount >= this.f14976r || right > getWidth() - width) {
                            VelocityTracker velocityTracker = this.f14892h0;
                            velocityTracker.computeCurrentVelocity(CredentialsApi.ACTIVITY_RESULT_ADD_ACCOUNT, this.f14853K0);
                            int xVelocity = (int) (velocityTracker.getXVelocity(this.f14859N0) * this.f14855L0);
                            if (Math.abs(xVelocity) <= this.f14851J0 || (((i9 = this.f14960b) == 0 && left == i15 - this.f14861O0) || (i9 + childCount == this.f14976r && right == width + this.f14861O0))) {
                                this.f14889e0 = -1;
                                m17155u0(0);
                                RunnableC3216f runnableC3216f = this.f14893i0;
                                if (runnableC3216f != null) {
                                    runnableC3216f.m17164c();
                                }
                                RunnableC3220j runnableC3220j2 = this.f14894j0;
                                if (runnableC3220j2 != null) {
                                    runnableC3220j2.m17174c();
                                }
                            } else {
                                if (this.f14893i0 == null) {
                                    this.f14893i0 = new RunnableC3216f();
                                }
                                m17155u0(2);
                                this.f14893i0.m17166e(-xVelocity);
                            }
                        } else {
                            this.f14889e0 = -1;
                            m17155u0(0);
                        }
                    } else {
                        this.f14889e0 = -1;
                        m17155u0(0);
                    }
                } else if (i12 == 5) {
                    if (this.f14893i0 == null) {
                        this.f14893i0 = new RunnableC3216f();
                    }
                    VelocityTracker velocityTracker2 = this.f14892h0;
                    velocityTracker2.computeCurrentVelocity(CredentialsApi.ACTIVITY_RESULT_ADD_ACCOUNT, this.f14853K0);
                    int xVelocity2 = (int) velocityTracker2.getXVelocity(this.f14859N0);
                    m17155u0(2);
                    if (Math.abs(xVelocity2) > this.f14851J0) {
                        this.f14893i0.m17167f(-xVelocity2);
                    } else {
                        this.f14893i0.m17169h();
                    }
                }
                setPressed(false);
                C3233a c3233a = this.f14865Q0;
                if (c3233a != null) {
                    c3233a.m17243g();
                    this.f14867R0.m17243g();
                }
                invalidate();
                Handler handler2 = getHandler();
                if (handler2 != null) {
                    handler2.removeCallbacks(this.f14907w0);
                }
                m17154t0();
                this.f14859N0 = -1;
            } else if (i11 == 2) {
                int iFindPointerIndex = motionEvent.findPointerIndex(this.f14859N0);
                if (iFindPointerIndex == -1) {
                    this.f14859N0 = motionEvent.getPointerId(0);
                } else {
                    i10 = iFindPointerIndex;
                }
                int x8 = (int) motionEvent.getX(i10);
                if (this.f14970l) {
                    mo17144k0();
                }
                int i17 = this.f14889e0;
                if (i17 == 0 || i17 == 1 || i17 == 2) {
                    m17117G0(x8);
                } else if (i17 == 3 || i17 == 5) {
                    m17112A0(x8);
                }
            } else if (i11 == 3) {
                int i18 = this.f14889e0;
                if (i18 == 5) {
                    if (this.f14893i0 == null) {
                        this.f14893i0 = new RunnableC3216f();
                    }
                    this.f14893i0.m17169h();
                } else if (i18 != 6) {
                    this.f14889e0 = -1;
                    setPressed(false);
                    View childAt2 = getChildAt(this.f14876W - this.f14960b);
                    if (childAt2 != null) {
                        childAt2.setPressed(false);
                    }
                    m17125P();
                    Handler handler3 = getHandler();
                    if (handler3 != null) {
                        handler3.removeCallbacks(this.f14907w0);
                    }
                    m17154t0();
                }
                C3233a c3233a2 = this.f14865Q0;
                if (c3233a2 != null) {
                    c3233a2.m17243g();
                    this.f14867R0.m17243g();
                }
                this.f14859N0 = -1;
            } else if (i11 == 5) {
                int actionIndex = motionEvent.getActionIndex();
                int pointerId = motionEvent.getPointerId(actionIndex);
                int x9 = (int) motionEvent.getX(actionIndex);
                int y9 = (int) motionEvent.getY(actionIndex);
                this.f14891g0 = 0;
                this.f14859N0 = pointerId;
                this.f14885c0 = x9;
                this.f14887d0 = y9;
                int iM17150p0 = m17150p0(x9, y9);
                if (iM17150p0 >= 0) {
                    this.f14881a0 = getChildAt(iM17150p0 - this.f14960b).getLeft();
                    this.f14876W = iM17150p0;
                }
                this.f14890f0 = x9;
            } else if (i11 == 6) {
                m17147n0(motionEvent);
                int i19 = this.f14885c0;
                int iM17150p02 = m17150p0(i19, this.f14887d0);
                if (iM17150p02 >= 0) {
                    this.f14881a0 = getChildAt(iM17150p02 - this.f14960b).getLeft();
                    this.f14876W = iM17150p02;
                }
                this.f14890f0 = i19;
            }
        } else if (this.f14889e0 != 6) {
            this.f14859N0 = motionEvent.getPointerId(0);
            int x10 = (int) motionEvent.getX();
            int y10 = (int) motionEvent.getY();
            int iM17150p03 = m17150p0(x10, y10);
            if (!this.f14970l) {
                if (this.f14889e0 != 4 && iM17150p03 >= 0 && getAdapter().isEnabled(iM17150p03)) {
                    this.f14889e0 = 0;
                    if (this.f14908x0 == null) {
                        this.f14908x0 = new RunnableC3215e();
                    }
                    postDelayed(this.f14908x0, ViewConfiguration.getTapTimeout());
                } else if (this.f14889e0 == 4) {
                    m17129T();
                    this.f14889e0 = 3;
                    this.f14891g0 = 0;
                    iM17150p03 = mo17133X(x10);
                    this.f14893i0.m17165d();
                }
            }
            if (iM17150p03 >= 0) {
                this.f14881a0 = getChildAt(iM17150p03 - this.f14960b).getLeft();
            }
            this.f14885c0 = x10;
            this.f14887d0 = y10;
            this.f14876W = iM17150p03;
            this.f14890f0 = Integer.MIN_VALUE;
        } else {
            this.f14893i0.m17164c();
            RunnableC3220j runnableC3220j3 = this.f14894j0;
            if (runnableC3220j3 != null) {
                runnableC3220j3.m17174c();
            }
            this.f14889e0 = 5;
            int x11 = (int) motionEvent.getX();
            this.f14890f0 = x11;
            this.f14885c0 = x11;
            this.f14887d0 = (int) motionEvent.getY();
            this.f14891g0 = 0;
            this.f14859N0 = motionEvent.getPointerId(0);
            this.f14873U0 = 0;
        }
        return true;
    }

    @Override // android.view.ViewTreeObserver.OnTouchModeChangeListener
    public void onTouchModeChanged(boolean z8) {
        if (z8) {
            m17137c0();
            if (getWidth() > 0 && getChildCount() > 0) {
                mo17144k0();
            }
            m17122L0();
            return;
        }
        int i9 = this.f14889e0;
        if (i9 == 5 || i9 == 6) {
            RunnableC3216f runnableC3216f = this.f14893i0;
            if (runnableC3216f != null) {
                runnableC3216f.m17164c();
            }
            RunnableC3220j runnableC3220j = this.f14894j0;
            if (runnableC3220j != null) {
                runnableC3220j.m17174c();
            }
            if (getScrollX() != 0) {
                m17089B0(this, 0);
                m17134Y();
                invalidate();
            }
        }
    }

    @Override // android.view.View
    public void onWindowFocusChanged(boolean z8) {
        super.onWindowFocusChanged(z8);
        int i9 = !isInTouchMode() ? 1 : 0;
        if (z8) {
            int i10 = this.f14904t0;
            if (i9 != i10 && i10 != -1) {
                if (i9 == 1) {
                    m17158x0();
                } else {
                    m17137c0();
                    this.f14838D = 0;
                    mo17144k0();
                }
            }
        } else {
            setChildrenDrawingCacheEnabled(false);
            RunnableC3216f runnableC3216f = this.f14893i0;
            if (runnableC3216f != null) {
                removeCallbacks(runnableC3216f);
                this.f14893i0.m17164c();
                RunnableC3220j runnableC3220j = this.f14894j0;
                if (runnableC3220j != null) {
                    runnableC3220j.m17174c();
                }
                if (getScrollX() != 0) {
                    m17089B0(this, 0);
                    m17134Y();
                    invalidate();
                }
            }
            if (i9 == 1) {
                this.f14901q0 = this.f14973o;
            }
        }
        this.f14904t0 = i9;
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x009a  */
    @Override // com.cyberlink.you.widgetpool.clhorizontalgridview.AdapterView
    /* renamed from: p */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public boolean mo17149p(View view, int i9, long j9) {
        int i10 = this.f14910z;
        boolean z8 = false;
        if (i10 != 0) {
            if (i10 == 2) {
                boolean z9 = !this.f14834B.get(i9, false);
                this.f14834B.put(i9, z9);
                if (this.f14836C != null && this.f14842F.hasStableIds()) {
                    if (z9) {
                        this.f14836C.m20726j(this.f14842F.getItemId(i9), Integer.valueOf(i9));
                    } else {
                        this.f14836C.m20720c(this.f14842F.getItemId(i9));
                    }
                }
                if (z9) {
                    this.f14832A++;
                } else {
                    this.f14832A--;
                }
            } else {
                if (i10 == 1) {
                    if (!this.f14834B.get(i9, false)) {
                        this.f14834B.clear();
                        this.f14834B.put(i9, true);
                        if (this.f14836C != null && this.f14842F.hasStableIds()) {
                            this.f14836C.m20718a();
                            this.f14836C.m20726j(this.f14842F.getItemId(i9), Integer.valueOf(i9));
                        }
                        this.f14832A = 1;
                    } else if (this.f14834B.size() == 0 || !this.f14834B.valueAt(0)) {
                        this.f14832A = 0;
                    }
                }
                if (z8) {
                    m17120J0();
                }
                z8 = true;
            }
            z8 = true;
            if (z8) {
            }
            z8 = true;
        }
        return super.mo17149p(view, i9, j9) | z8;
    }

    /* renamed from: p0 */
    public int m17150p0(int i9, int i10) {
        Rect rect = this.f14900p0;
        if (rect == null) {
            rect = new Rect();
            this.f14900p0 = rect;
        }
        for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
            View childAt = getChildAt(childCount);
            if (childAt.getVisibility() == 0) {
                childAt.getHitRect(rect);
                if (rect.contains(i9, i10)) {
                    return this.f14960b + childCount;
                }
            }
        }
        return -1;
    }

    /* renamed from: q0 */
    public final void m17151q0(int i9, int i10, int i11, int i12) {
        this.f14852K.set(i9 - this.f14856M, i10 - this.f14858N, i11 + this.f14860O, i12 + this.f14862P);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: r0 */
    public void m17152r0(int i9, View view) {
        if (i9 != -1) {
            this.f14850J = i9;
        }
        Rect rect = this.f14852K;
        rect.set(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
        if (view instanceof InterfaceC3223m) {
            ((InterfaceC3223m) view).adjustListItemSelectionBounds(rect);
        }
        m17151q0(rect.left, rect.top, rect.right, rect.bottom);
        boolean z8 = this.f14839D0;
        if (view.isEnabled() != z8) {
            this.f14839D0 = !z8;
            if (getSelectedItemPosition() != -1) {
                refreshDrawableState();
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void requestDisallowInterceptTouchEvent(boolean z8) {
        if (z8) {
            m17154t0();
        }
        super.requestDisallowInterceptTouchEvent(z8);
    }

    @Override // android.view.View, android.view.ViewParent
    public void requestLayout() {
        if (this.f14983y || this.f14968j) {
            return;
        }
        super.requestLayout();
    }

    /* renamed from: s0 */
    public int m17153s0() {
        int i9 = this.f14973o;
        if (i9 < 0) {
            i9 = this.f14901q0;
        }
        return Math.min(Math.max(0, i9), this.f14976r - 1);
    }

    @Override // android.view.View, android.view.accessibility.AccessibilityEventSource
    public void sendAccessibilityEvent(int i9) {
        if (i9 == 4096) {
            int firstVisiblePosition = getFirstVisiblePosition();
            int lastVisiblePosition = getLastVisiblePosition();
            if (this.f14879Y0 == firstVisiblePosition && this.f14880Z0 == lastVisiblePosition) {
                return;
            }
            this.f14879Y0 = firstVisiblePosition;
            this.f14880Z0 = lastVisiblePosition;
        }
        super.sendAccessibilityEvent(i9);
    }

    public void setCacheColorHint(int i9) {
        if (i9 != this.f14837C0) {
            this.f14837C0 = i9;
            int childCount = getChildCount();
            for (int i10 = 0; i10 < childCount; i10++) {
                getChildAt(i10).setDrawingCacheBackgroundColor(i9);
            }
            this.f14854L.m17187m(i9);
        }
    }

    public void setChoiceMode(int i9) {
        ListAdapter listAdapter;
        this.f14910z = i9;
        if (i9 != 0) {
            if (this.f14834B == null) {
                this.f14834B = new SparseBooleanArray();
            }
            if (this.f14836C == null && (listAdapter = this.f14842F) != null && listAdapter.hasStableIds()) {
                this.f14836C = new C5305d<>();
            }
            if (this.f14910z == 3) {
                m17124O();
                setLongClickable(true);
            }
        }
    }

    public void setDrawSelectorOnTop(boolean z8) {
        this.f14846H = z8;
    }

    public void setFriction(float f9) {
        if (this.f14893i0 == null) {
            this.f14893i0 = new RunnableC3216f();
        }
        this.f14893i0.f14929b.m17257j(f9);
    }

    public void setOnScrollListener(InterfaceC3218h interfaceC3218h) {
        this.f14898n0 = interfaceC3218h;
        m17141g0();
    }

    @Override // android.view.View
    public void setOverScrollMode(int i9) {
        if (i9 == 2) {
            this.f14865Q0 = null;
            this.f14867R0 = null;
        } else if (this.f14865Q0 == null) {
            Context context = getContext();
            try {
                this.f14865Q0 = new C3233a(context);
                this.f14867R0 = new C3233a(context);
            } catch (OutOfMemoryError e9) {
                e9.printStackTrace();
                this.f14865Q0 = null;
                this.f14867R0 = null;
            }
        }
        super.setOverScrollMode(i9);
    }

    public void setRecyclerListener(InterfaceC3222l interfaceC3222l) {
        C3221k.m17175a(this.f14854L, interfaceC3222l);
    }

    public void setScrollingCacheEnabled(boolean z8) {
        if (this.f14897m0 && !z8) {
            m17125P();
        }
        this.f14897m0 = z8;
    }

    public abstract void setSelectionInt(int i9);

    public void setSelector(int i9) {
        setSelector(getResources().getDrawable(i9));
    }

    public void setSmoothScrollbarEnabled(boolean z8) {
        this.f14899o0 = z8;
    }

    public void setStackFromRight(boolean z8) {
        if (this.f14896l0 != z8) {
            this.f14896l0 = z8;
            m17156v0();
        }
    }

    public void setTranscriptMode(int i9) {
        this.f14835B0 = i9;
    }

    public void setVelocityScale(float f9) {
        this.f14855L0 = f9;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public boolean showContextMenuForChild(View view) {
        int iM17202k = m17202k(view);
        if (iM17202k < 0) {
            return false;
        }
        this.f14902r0 = m17128S(getChildAt(iM17202k - this.f14960b), iM17202k, this.f14842F.getItemId(iM17202k));
        return super.showContextMenuForChild(view);
    }

    /* renamed from: t0 */
    public final void m17154t0() {
        VelocityTracker velocityTracker = this.f14892h0;
        if (velocityTracker != null) {
            velocityTracker.recycle();
            this.f14892h0 = null;
        }
    }

    /* renamed from: u0 */
    public void m17155u0(int i9) {
        InterfaceC3218h interfaceC3218h;
        if (i9 == this.f14841E0 || (interfaceC3218h = this.f14898n0) == null) {
            return;
        }
        this.f14841E0 = i9;
        interfaceC3218h.mo17170a(this, i9);
    }

    /* renamed from: v0 */
    public void m17156v0() {
        if (getChildCount() > 0) {
            m17157w0();
            requestLayout();
            invalidate();
        }
    }

    @Override // android.view.View
    public boolean verifyDrawable(Drawable drawable) {
        return this.f14848I == drawable || super.verifyDrawable(drawable);
    }

    /* renamed from: w0 */
    public void m17157w0() {
        removeAllViewsInLayout();
        this.f14960b = 0;
        this.f14970l = false;
        this.f14849I0 = null;
        this.f14965g = false;
        this.f14886c1 = null;
        this.f14978t = -1;
        this.f14979u = Long.MIN_VALUE;
        setSelectedPositionInt(-1);
        setNextSelectedPositionInt(-1);
        this.f14895k0 = 0;
        this.f14850J = -1;
        this.f14852K.setEmpty();
        invalidate();
    }

    /* JADX WARN: Removed duplicated region for block: B:42:0x00ac  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x00d4  */
    /* JADX WARN: Removed duplicated region for block: B:56:? A[RETURN, SYNTHETIC] */
    /* renamed from: x0 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public boolean m17158x0() {
        boolean z8;
        int left;
        int i9;
        RunnableC3220j runnableC3220j;
        int iMo17205n;
        int childCount = getChildCount();
        if (childCount <= 0) {
            return false;
        }
        int verticalFadingEdgeLength = this.f14864Q.left;
        int right = (getRight() - getLeft()) - this.f14864Q.right;
        int i10 = this.f14960b;
        int i11 = this.f14901q0;
        if (i11 >= i10 && i11 < i10 + childCount) {
            View childAt = getChildAt(i11 - i10);
            left = childAt.getLeft();
            int right2 = childAt.getRight();
            if (left < verticalFadingEdgeLength) {
                left = verticalFadingEdgeLength + getVerticalFadingEdgeLength();
            } else if (right2 > right) {
                left = (right - childAt.getMeasuredHeight()) - getVerticalFadingEdgeLength();
            }
        } else {
            if (i11 >= i10) {
                int i12 = this.f14976r;
                int i13 = i10 + childCount;
                int i14 = i13 - 1;
                int i15 = childCount - 1;
                int i16 = i15;
                int i17 = 0;
                while (true) {
                    if (i16 < 0) {
                        z8 = false;
                        i11 = i14;
                        left = i17;
                        break;
                    }
                    View childAt2 = getChildAt(i16);
                    int left2 = childAt2.getLeft();
                    int right3 = childAt2.getRight();
                    if (i16 == i15) {
                        if (i13 < i12 || right3 > right) {
                            right -= getVerticalFadingEdgeLength();
                        }
                        i17 = left2;
                    }
                    if (right3 <= right) {
                        i11 = i10 + i16;
                        z8 = false;
                        left = left2;
                        break;
                    }
                    i16--;
                }
                i9 = -1;
                this.f14901q0 = -1;
                removeCallbacks(this.f14893i0);
                runnableC3220j = this.f14894j0;
                if (runnableC3220j != null) {
                    runnableC3220j.m17174c();
                }
                this.f14889e0 = -1;
                m17125P();
                this.f14961c = left;
                iMo17205n = mo17205n(i11, z8);
                if (iMo17205n >= i10 && iMo17205n <= getLastVisiblePosition()) {
                    this.f14838D = 4;
                    m17122L0();
                    setSelectionInt(iMo17205n);
                    m17141g0();
                    i9 = iMo17205n;
                }
                m17155u0(0);
                return i9 < 0;
            }
            int i18 = 0;
            int i19 = 0;
            while (true) {
                if (i18 >= childCount) {
                    left = i19;
                    i11 = i10;
                    break;
                }
                left = getChildAt(i18).getLeft();
                if (i18 == 0) {
                    if (i10 > 0 || left < verticalFadingEdgeLength) {
                        verticalFadingEdgeLength += getVerticalFadingEdgeLength();
                    }
                    i19 = left;
                }
                if (left >= verticalFadingEdgeLength) {
                    i11 = i18 + i10;
                    break;
                }
                i18++;
            }
        }
        z8 = true;
        i9 = -1;
        this.f14901q0 = -1;
        removeCallbacks(this.f14893i0);
        runnableC3220j = this.f14894j0;
        if (runnableC3220j != null) {
        }
        this.f14889e0 = -1;
        m17125P();
        this.f14961c = left;
        iMo17205n = mo17205n(i11, z8);
        if (iMo17205n >= i10) {
            this.f14838D = 4;
            m17122L0();
            setSelectionInt(iMo17205n);
            m17141g0();
            i9 = iMo17205n;
        }
        m17155u0(0);
        if (i9 < 0) {
        }
    }

    /* renamed from: y0 */
    public boolean m17159y0() {
        if (this.f14973o >= 0 || !m17158x0()) {
            return false;
        }
        m17122L0();
        return true;
    }

    /* renamed from: com.cyberlink.you.widgetpool.clhorizontalgridview.AbsListView$g */
    public static class C3217g extends ViewGroup.LayoutParams {

        /* renamed from: a */
        @ViewDebug.ExportedProperty(category = "list", mapping = {@ViewDebug.IntToString(from = -1, to = "ITEM_VIEW_TYPE_IGNORE"), @ViewDebug.IntToString(from = -2, to = "ITEM_VIEW_TYPE_HEADER_OR_FOOTER")})
        public int f14934a;

        /* renamed from: b */
        @ViewDebug.ExportedProperty(category = "list")
        public boolean f14935b;

        /* renamed from: c */
        public int f14936c;

        /* renamed from: d */
        public long f14937d;

        public C3217g(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.f14937d = -1L;
        }

        public C3217g(int i9, int i10) {
            super(i9, i10);
            this.f14937d = -1L;
        }

        public C3217g(int i9, int i10, int i11) {
            super(i9, i10);
            this.f14937d = -1L;
            this.f14934a = i11;
        }

        public C3217g(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.f14937d = -1L;
        }
    }

    public AbsListView(Context context, AttributeSet attributeSet, int i9) {
        super(context, attributeSet, i9);
        this.f14910z = 0;
        this.f14838D = 0;
        this.f14846H = false;
        this.f14850J = -1;
        this.f14852K = new Rect();
        this.f14854L = new C3221k();
        this.f14856M = 0;
        this.f14858N = 0;
        this.f14860O = 0;
        this.f14862P = 0;
        this.f14864Q = new Rect();
        this.f14866R = 0;
        this.f14889e0 = -1;
        this.f14895k0 = 0;
        this.f14899o0 = true;
        this.f14901q0 = -1;
        this.f14902r0 = null;
        this.f14904t0 = -1;
        this.f14905u0 = false;
        this.f14906v0 = false;
        this.f14841E0 = 0;
        this.f14855L0 = 1.0f;
        this.f14857M0 = new boolean[1];
        this.f14859N0 = -1;
        this.f14873U0 = 0;
        this.f14888d1 = BitmapDescriptorFactory.HUE_RED;
        m17138d0();
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C6453a0.AbsListView, i9, 0);
        Drawable drawable = typedArrayObtainStyledAttributes.getDrawable(3);
        if (drawable != null) {
            setSelector(drawable);
        }
        this.f14846H = typedArrayObtainStyledAttributes.getBoolean(2, false);
        setStackFromRight(typedArrayObtainStyledAttributes.getBoolean(6, false));
        setScrollingCacheEnabled(typedArrayObtainStyledAttributes.getBoolean(4, true));
        setTranscriptMode(typedArrayObtainStyledAttributes.getInt(8, 0));
        setCacheColorHint(typedArrayObtainStyledAttributes.getColor(0, 0));
        setSmoothScrollbarEnabled(typedArrayObtainStyledAttributes.getBoolean(5, true));
        setChoiceMode(typedArrayObtainStyledAttributes.getInt(1, 0));
        typedArrayObtainStyledAttributes.recycle();
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return new C3217g(layoutParams);
    }

    @Override // com.cyberlink.you.widgetpool.clhorizontalgridview.AdapterView
    public void setAdapter(ListAdapter listAdapter) {
        if (listAdapter != null) {
            boolean zHasStableIds = this.f14842F.hasStableIds();
            this.f14844G = zHasStableIds;
            if (this.f14910z != 0 && zHasStableIds && this.f14836C == null) {
                this.f14836C = new C5305d<>();
            }
        }
        SparseBooleanArray sparseBooleanArray = this.f14834B;
        if (sparseBooleanArray != null) {
            sparseBooleanArray.clear();
        }
        C5305d<Integer> c5305d = this.f14836C;
        if (c5305d != null) {
            c5305d.m20718a();
        }
    }

    public void setSelector(Drawable drawable) {
        Drawable drawable2 = this.f14848I;
        if (drawable2 != null) {
            drawable2.setCallback(null);
            unscheduleDrawable(this.f14848I);
        }
        this.f14848I = drawable;
        Rect rect = new Rect();
        drawable.getPadding(rect);
        this.f14856M = rect.left;
        this.f14858N = rect.top;
        this.f14860O = rect.right;
        this.f14862P = rect.bottom;
        drawable.setCallback(this);
        m17122L0();
    }
}
