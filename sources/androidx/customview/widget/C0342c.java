package androidx.customview.widget;

import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.widget.OverScroller;
import com.google.android.gms.auth.api.credentials.CredentialsApi;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.util.Arrays;
import p042d0.C4647u;

/* renamed from: androidx.customview.widget.c */
/* loaded from: classes.dex */
public class C0342c {

    /* renamed from: w */
    public static final Interpolator f1863w = new a();

    /* renamed from: a */
    public int f1864a;

    /* renamed from: b */
    public int f1865b;

    /* renamed from: d */
    public float[] f1867d;

    /* renamed from: e */
    public float[] f1868e;

    /* renamed from: f */
    public float[] f1869f;

    /* renamed from: g */
    public float[] f1870g;

    /* renamed from: h */
    public int[] f1871h;

    /* renamed from: i */
    public int[] f1872i;

    /* renamed from: j */
    public int[] f1873j;

    /* renamed from: k */
    public int f1874k;

    /* renamed from: l */
    public VelocityTracker f1875l;

    /* renamed from: m */
    public float f1876m;

    /* renamed from: n */
    public float f1877n;

    /* renamed from: o */
    public int f1878o;

    /* renamed from: p */
    public int f1879p;

    /* renamed from: q */
    public OverScroller f1880q;

    /* renamed from: r */
    public final c f1881r;

    /* renamed from: s */
    public View f1882s;

    /* renamed from: t */
    public boolean f1883t;

    /* renamed from: u */
    public final ViewGroup f1884u;

    /* renamed from: c */
    public int f1866c = -1;

    /* renamed from: v */
    public final Runnable f1885v = new b();

    /* renamed from: androidx.customview.widget.c$a */
    public static class a implements Interpolator {
        @Override // android.animation.TimeInterpolator
        public float getInterpolation(float f9) {
            float f10 = f9 - 1.0f;
            return (f10 * f10 * f10 * f10 * f10) + 1.0f;
        }
    }

    /* renamed from: androidx.customview.widget.c$b */
    public class b implements Runnable {
        public b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            C0342c.this.m1668K(0);
        }
    }

    /* renamed from: androidx.customview.widget.c$c */
    public static abstract class c {
        public int clampViewPositionHorizontal(View view, int i9, int i10) {
            return 0;
        }

        public int clampViewPositionVertical(View view, int i9, int i10) {
            return 0;
        }

        public int getOrderedChildIndex(int i9) {
            return i9;
        }

        public int getViewHorizontalDragRange(View view) {
            return 0;
        }

        public int getViewVerticalDragRange(View view) {
            return 0;
        }

        public void onEdgeDragStarted(int i9, int i10) {
        }

        public boolean onEdgeLock(int i9) {
            return false;
        }

        public void onEdgeTouched(int i9, int i10) {
        }

        public void onViewCaptured(View view, int i9) {
        }

        public void onViewDragStateChanged(int i9) {
        }

        public void onViewPositionChanged(View view, int i9, int i10, int i11, int i12) {
        }

        public void onViewReleased(View view, float f9, float f10) {
        }

        public abstract boolean tryCaptureView(View view, int i9);
    }

    public C0342c(Context context, ViewGroup viewGroup, c cVar) {
        if (viewGroup == null) {
            throw new IllegalArgumentException("Parent view may not be null");
        }
        if (cVar == null) {
            throw new IllegalArgumentException("Callback may not be null");
        }
        this.f1884u = viewGroup;
        this.f1881r = cVar;
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        this.f1878o = (int) ((context.getResources().getDisplayMetrics().density * 20.0f) + 0.5f);
        this.f1865b = viewConfiguration.getScaledTouchSlop();
        this.f1876m = viewConfiguration.getScaledMaximumFlingVelocity();
        this.f1877n = viewConfiguration.getScaledMinimumFlingVelocity();
        this.f1880q = new OverScroller(context, f1863w);
    }

    /* renamed from: o */
    public static C0342c m1656o(ViewGroup viewGroup, float f9, c cVar) {
        C0342c c0342cM1657p = m1657p(viewGroup, cVar);
        c0342cM1657p.f1865b = (int) (c0342cM1657p.f1865b * (1.0f / f9));
        return c0342cM1657p;
    }

    /* renamed from: p */
    public static C0342c m1657p(ViewGroup viewGroup, c cVar) {
        return new C0342c(viewGroup.getContext(), viewGroup, cVar);
    }

    /* renamed from: A */
    public int m1658A() {
        return this.f1864a;
    }

    /* renamed from: B */
    public boolean m1659B(int i9, int i10) {
        return m1662E(this.f1882s, i9, i10);
    }

    /* renamed from: C */
    public boolean m1660C(int i9) {
        return ((1 << i9) & this.f1874k) != 0;
    }

    /* renamed from: D */
    public final boolean m1661D(int i9) {
        if (m1660C(i9)) {
            return true;
        }
        Log.e("ViewDragHelper", "Ignoring pointerId=" + i9 + " because ACTION_DOWN was not received for this pointer before ACTION_MOVE. It likely happened because  ViewDragHelper did not receive all the events in the event stream.");
        return false;
    }

    /* renamed from: E */
    public boolean m1662E(View view, int i9, int i10) {
        return view != null && i9 >= view.getLeft() && i9 < view.getRight() && i10 >= view.getTop() && i10 < view.getBottom();
    }

    /* renamed from: F */
    public void m1663F(MotionEvent motionEvent) {
        int i9;
        int actionMasked = motionEvent.getActionMasked();
        int actionIndex = motionEvent.getActionIndex();
        if (actionMasked == 0) {
            m1676b();
        }
        if (this.f1875l == null) {
            this.f1875l = VelocityTracker.obtain();
        }
        this.f1875l.addMovement(motionEvent);
        int i10 = 0;
        if (actionMasked == 0) {
            float x8 = motionEvent.getX();
            float y8 = motionEvent.getY();
            int pointerId = motionEvent.getPointerId(0);
            View viewM1693u = m1693u((int) x8, (int) y8);
            m1666I(x8, y8, pointerId);
            m1674Q(viewM1693u, pointerId);
            int i11 = this.f1871h[pointerId];
            int i12 = this.f1879p;
            if ((i11 & i12) != 0) {
                this.f1881r.onEdgeTouched(i11 & i12, pointerId);
                return;
            }
            return;
        }
        if (actionMasked == 1) {
            if (this.f1864a == 1) {
                m1664G();
            }
            m1676b();
            return;
        }
        if (actionMasked == 2) {
            if (this.f1864a == 1) {
                if (m1661D(this.f1866c)) {
                    int iFindPointerIndex = motionEvent.findPointerIndex(this.f1866c);
                    float x9 = motionEvent.getX(iFindPointerIndex);
                    float y9 = motionEvent.getY(iFindPointerIndex);
                    float[] fArr = this.f1869f;
                    int i13 = this.f1866c;
                    int i14 = (int) (x9 - fArr[i13]);
                    int i15 = (int) (y9 - this.f1870g[i13]);
                    m1691s(this.f1882s.getLeft() + i14, this.f1882s.getTop() + i15, i14, i15);
                    m1667J(motionEvent);
                    return;
                }
                return;
            }
            int pointerCount = motionEvent.getPointerCount();
            while (i10 < pointerCount) {
                int pointerId2 = motionEvent.getPointerId(i10);
                if (m1661D(pointerId2)) {
                    float x10 = motionEvent.getX(i10);
                    float y10 = motionEvent.getY(i10);
                    float f9 = x10 - this.f1867d[pointerId2];
                    float f10 = y10 - this.f1868e[pointerId2];
                    m1665H(f9, f10, pointerId2);
                    if (this.f1864a != 1) {
                        View viewM1693u2 = m1693u((int) x10, (int) y10);
                        if (m1681g(viewM1693u2, f9, f10) && m1674Q(viewM1693u2, pointerId2)) {
                            break;
                        }
                    } else {
                        break;
                    }
                }
                i10++;
            }
            m1667J(motionEvent);
            return;
        }
        if (actionMasked == 3) {
            if (this.f1864a == 1) {
                m1689q(BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED);
            }
            m1676b();
            return;
        }
        if (actionMasked == 5) {
            int pointerId3 = motionEvent.getPointerId(actionIndex);
            float x11 = motionEvent.getX(actionIndex);
            float y11 = motionEvent.getY(actionIndex);
            m1666I(x11, y11, pointerId3);
            if (this.f1864a != 0) {
                if (m1659B((int) x11, (int) y11)) {
                    m1674Q(this.f1882s, pointerId3);
                    return;
                }
                return;
            } else {
                m1674Q(m1693u((int) x11, (int) y11), pointerId3);
                int i16 = this.f1871h[pointerId3];
                int i17 = this.f1879p;
                if ((i16 & i17) != 0) {
                    this.f1881r.onEdgeTouched(i16 & i17, pointerId3);
                    return;
                }
                return;
            }
        }
        if (actionMasked != 6) {
            return;
        }
        int pointerId4 = motionEvent.getPointerId(actionIndex);
        if (this.f1864a == 1 && pointerId4 == this.f1866c) {
            int pointerCount2 = motionEvent.getPointerCount();
            while (true) {
                if (i10 >= pointerCount2) {
                    i9 = -1;
                    break;
                }
                int pointerId5 = motionEvent.getPointerId(i10);
                if (pointerId5 != this.f1866c) {
                    View viewM1693u3 = m1693u((int) motionEvent.getX(i10), (int) motionEvent.getY(i10));
                    View view = this.f1882s;
                    if (viewM1693u3 == view && m1674Q(view, pointerId5)) {
                        i9 = this.f1866c;
                        break;
                    }
                }
                i10++;
            }
            if (i9 == -1) {
                m1664G();
            }
        }
        m1685k(pointerId4);
    }

    /* renamed from: G */
    public final void m1664G() {
        this.f1875l.computeCurrentVelocity(CredentialsApi.ACTIVITY_RESULT_ADD_ACCOUNT, this.f1876m);
        m1689q(m1682h(this.f1875l.getXVelocity(this.f1866c), this.f1877n, this.f1876m), m1682h(this.f1875l.getYVelocity(this.f1866c), this.f1877n, this.f1876m));
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v15 */
    /* JADX WARN: Type inference failed for: r0v16 */
    /* JADX WARN: Type inference failed for: r0v4, types: [int] */
    /* JADX WARN: Type inference failed for: r0v6 */
    /* JADX WARN: Type inference failed for: r0v7 */
    /* JADX WARN: Type inference failed for: r3v3, types: [androidx.customview.widget.c$c] */
    /* renamed from: H */
    public final void m1665H(float f9, float f10, int i9) {
        boolean zM1678d = m1678d(f9, f10, i9, 1);
        boolean z8 = zM1678d;
        if (m1678d(f10, f9, i9, 4)) {
            z8 = (zM1678d ? 1 : 0) | 4;
        }
        boolean z9 = z8;
        if (m1678d(f9, f10, i9, 2)) {
            z9 = (z8 ? 1 : 0) | 2;
        }
        ?? r02 = z9;
        if (m1678d(f10, f9, i9, 8)) {
            r02 = (z9 ? 1 : 0) | 8;
        }
        if (r02 != 0) {
            int[] iArr = this.f1872i;
            iArr[i9] = iArr[i9] | r02;
            this.f1881r.onEdgeDragStarted(r02, i9);
        }
    }

    /* renamed from: I */
    public final void m1666I(float f9, float f10, int i9) {
        m1692t(i9);
        float[] fArr = this.f1867d;
        this.f1869f[i9] = f9;
        fArr[i9] = f9;
        float[] fArr2 = this.f1868e;
        this.f1870g[i9] = f10;
        fArr2[i9] = f10;
        this.f1871h[i9] = m1697y((int) f9, (int) f10);
        this.f1874k |= 1 << i9;
    }

    /* renamed from: J */
    public final void m1667J(MotionEvent motionEvent) {
        int pointerCount = motionEvent.getPointerCount();
        for (int i9 = 0; i9 < pointerCount; i9++) {
            int pointerId = motionEvent.getPointerId(i9);
            if (m1661D(pointerId)) {
                float x8 = motionEvent.getX(i9);
                float y8 = motionEvent.getY(i9);
                this.f1869f[pointerId] = x8;
                this.f1870g[pointerId] = y8;
            }
        }
    }

    /* renamed from: K */
    public void m1668K(int i9) {
        this.f1884u.removeCallbacks(this.f1885v);
        if (this.f1864a != i9) {
            this.f1864a = i9;
            this.f1881r.onViewDragStateChanged(i9);
            if (this.f1864a == 0) {
                this.f1882s = null;
            }
        }
    }

    /* renamed from: L */
    public void m1669L(int i9) {
        this.f1879p = i9;
    }

    /* renamed from: M */
    public void m1670M(float f9) {
        this.f1877n = f9;
    }

    /* renamed from: N */
    public boolean m1671N(int i9, int i10) {
        if (this.f1883t) {
            return m1694v(i9, i10, (int) this.f1875l.getXVelocity(this.f1866c), (int) this.f1875l.getYVelocity(this.f1866c));
        }
        throw new IllegalStateException("Cannot settleCapturedViewAt outside of a call to Callback#onViewReleased");
    }

    /* JADX WARN: Removed duplicated region for block: B:54:0x00e6  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x00ff  */
    /* renamed from: O */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public boolean m1672O(MotionEvent motionEvent) {
        boolean z8;
        View viewM1693u;
        int actionMasked = motionEvent.getActionMasked();
        int actionIndex = motionEvent.getActionIndex();
        if (actionMasked == 0) {
            m1676b();
        }
        if (this.f1875l == null) {
            this.f1875l = VelocityTracker.obtain();
        }
        this.f1875l.addMovement(motionEvent);
        if (actionMasked == 0) {
            float x8 = motionEvent.getX();
            float y8 = motionEvent.getY();
            z8 = false;
            int pointerId = motionEvent.getPointerId(0);
            m1666I(x8, y8, pointerId);
            View viewM1693u2 = m1693u((int) x8, (int) y8);
            if (viewM1693u2 == this.f1882s && this.f1864a == 2) {
                m1674Q(viewM1693u2, pointerId);
            }
            int i9 = this.f1871h[pointerId];
            int i10 = this.f1879p;
            if ((i9 & i10) != 0) {
                this.f1881r.onEdgeTouched(i9 & i10, pointerId);
            }
        } else if (actionMasked == 1) {
            m1676b();
            z8 = false;
        } else {
            if (actionMasked != 2) {
                if (actionMasked != 3) {
                    if (actionMasked == 5) {
                        int pointerId2 = motionEvent.getPointerId(actionIndex);
                        float x9 = motionEvent.getX(actionIndex);
                        float y9 = motionEvent.getY(actionIndex);
                        m1666I(x9, y9, pointerId2);
                        int i11 = this.f1864a;
                        if (i11 == 0) {
                            int i12 = this.f1871h[pointerId2];
                            int i13 = this.f1879p;
                            if ((i12 & i13) != 0) {
                                this.f1881r.onEdgeTouched(i12 & i13, pointerId2);
                            }
                        } else if (i11 == 2 && (viewM1693u = m1693u((int) x9, (int) y9)) == this.f1882s) {
                            m1674Q(viewM1693u, pointerId2);
                        }
                    } else if (actionMasked == 6) {
                        m1685k(motionEvent.getPointerId(actionIndex));
                    }
                }
            } else if (this.f1867d != null && this.f1868e != null) {
                int pointerCount = motionEvent.getPointerCount();
                for (int i14 = 0; i14 < pointerCount; i14++) {
                    int pointerId3 = motionEvent.getPointerId(i14);
                    if (m1661D(pointerId3)) {
                        float x10 = motionEvent.getX(i14);
                        float y10 = motionEvent.getY(i14);
                        float f9 = x10 - this.f1867d[pointerId3];
                        float f10 = y10 - this.f1868e[pointerId3];
                        View viewM1693u3 = m1693u((int) x10, (int) y10);
                        boolean z9 = viewM1693u3 != null && m1681g(viewM1693u3, f9, f10);
                        if (z9) {
                            int left = viewM1693u3.getLeft();
                            int i15 = (int) f9;
                            int iClampViewPositionHorizontal = this.f1881r.clampViewPositionHorizontal(viewM1693u3, left + i15, i15);
                            int top = viewM1693u3.getTop();
                            int i16 = (int) f10;
                            int iClampViewPositionVertical = this.f1881r.clampViewPositionVertical(viewM1693u3, top + i16, i16);
                            int viewHorizontalDragRange = this.f1881r.getViewHorizontalDragRange(viewM1693u3);
                            int viewVerticalDragRange = this.f1881r.getViewVerticalDragRange(viewM1693u3);
                            if ((viewHorizontalDragRange == 0 || (viewHorizontalDragRange > 0 && iClampViewPositionHorizontal == left)) && (viewVerticalDragRange == 0 || (viewVerticalDragRange > 0 && iClampViewPositionVertical == top))) {
                                break;
                            }
                            m1665H(f9, f10, pointerId3);
                            if (this.f1864a == 1 || (z9 && m1674Q(viewM1693u3, pointerId3))) {
                                break;
                            }
                        }
                    }
                }
                m1667J(motionEvent);
            }
            z8 = false;
        }
        if (this.f1864a == 1) {
            return true;
        }
        return z8;
    }

    /* renamed from: P */
    public boolean m1673P(View view, int i9, int i10) {
        this.f1882s = view;
        this.f1866c = -1;
        boolean zM1694v = m1694v(i9, i10, 0, 0);
        if (!zM1694v && this.f1864a == 0 && this.f1882s != null) {
            this.f1882s = null;
        }
        return zM1694v;
    }

    /* renamed from: Q */
    public boolean m1674Q(View view, int i9) {
        if (view == this.f1882s && this.f1866c == i9) {
            return true;
        }
        if (view == null || !this.f1881r.tryCaptureView(view, i9)) {
            return false;
        }
        this.f1866c = i9;
        m1677c(view, i9);
        return true;
    }

    /* renamed from: a */
    public void m1675a() {
        m1676b();
        if (this.f1864a == 2) {
            int currX = this.f1880q.getCurrX();
            int currY = this.f1880q.getCurrY();
            this.f1880q.abortAnimation();
            int currX2 = this.f1880q.getCurrX();
            int currY2 = this.f1880q.getCurrY();
            this.f1881r.onViewPositionChanged(this.f1882s, currX2, currY2, currX2 - currX, currY2 - currY);
        }
        m1668K(0);
    }

    /* renamed from: b */
    public void m1676b() {
        this.f1866c = -1;
        m1684j();
        VelocityTracker velocityTracker = this.f1875l;
        if (velocityTracker != null) {
            velocityTracker.recycle();
            this.f1875l = null;
        }
    }

    /* renamed from: c */
    public void m1677c(View view, int i9) {
        if (view.getParent() == this.f1884u) {
            this.f1882s = view;
            this.f1866c = i9;
            this.f1881r.onViewCaptured(view, i9);
            m1668K(1);
            return;
        }
        throw new IllegalArgumentException("captureChildView: parameter must be a descendant of the ViewDragHelper's tracked parent view (" + this.f1884u + ")");
    }

    /* renamed from: d */
    public final boolean m1678d(float f9, float f10, int i9, int i10) {
        float fAbs = Math.abs(f9);
        float fAbs2 = Math.abs(f10);
        if ((this.f1871h[i9] & i10) != i10 || (this.f1879p & i10) == 0 || (this.f1873j[i9] & i10) == i10 || (this.f1872i[i9] & i10) == i10) {
            return false;
        }
        int i11 = this.f1865b;
        if (fAbs <= i11 && fAbs2 <= i11) {
            return false;
        }
        if (fAbs >= fAbs2 * 0.5f || !this.f1881r.onEdgeLock(i10)) {
            return (this.f1872i[i9] & i10) == 0 && fAbs > ((float) this.f1865b);
        }
        int[] iArr = this.f1873j;
        iArr[i9] = iArr[i9] | i10;
        return false;
    }

    /* renamed from: e */
    public boolean m1679e(int i9) {
        int length = this.f1867d.length;
        for (int i10 = 0; i10 < length; i10++) {
            if (m1680f(i9, i10)) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: f */
    public boolean m1680f(int i9, int i10) {
        if (!m1660C(i10)) {
            return false;
        }
        boolean z8 = (i9 & 1) == 1;
        boolean z9 = (i9 & 2) == 2;
        float f9 = this.f1869f[i10] - this.f1867d[i10];
        float f10 = this.f1870g[i10] - this.f1868e[i10];
        if (!z8 || !z9) {
            return z8 ? Math.abs(f9) > ((float) this.f1865b) : z9 && Math.abs(f10) > ((float) this.f1865b);
        }
        float f11 = (f9 * f9) + (f10 * f10);
        int i11 = this.f1865b;
        return f11 > ((float) (i11 * i11));
    }

    /* renamed from: g */
    public final boolean m1681g(View view, float f9, float f10) {
        if (view == null) {
            return false;
        }
        boolean z8 = this.f1881r.getViewHorizontalDragRange(view) > 0;
        boolean z9 = this.f1881r.getViewVerticalDragRange(view) > 0;
        if (!z8 || !z9) {
            return z8 ? Math.abs(f9) > ((float) this.f1865b) : z9 && Math.abs(f10) > ((float) this.f1865b);
        }
        float f11 = (f9 * f9) + (f10 * f10);
        int i9 = this.f1865b;
        return f11 > ((float) (i9 * i9));
    }

    /* renamed from: h */
    public final float m1682h(float f9, float f10, float f11) {
        float fAbs = Math.abs(f9);
        return fAbs < f10 ? BitmapDescriptorFactory.HUE_RED : fAbs > f11 ? f9 > BitmapDescriptorFactory.HUE_RED ? f11 : -f11 : f9;
    }

    /* renamed from: i */
    public final int m1683i(int i9, int i10, int i11) {
        int iAbs = Math.abs(i9);
        if (iAbs < i10) {
            return 0;
        }
        return iAbs > i11 ? i9 > 0 ? i11 : -i11 : i9;
    }

    /* renamed from: j */
    public final void m1684j() {
        float[] fArr = this.f1867d;
        if (fArr == null) {
            return;
        }
        Arrays.fill(fArr, BitmapDescriptorFactory.HUE_RED);
        Arrays.fill(this.f1868e, BitmapDescriptorFactory.HUE_RED);
        Arrays.fill(this.f1869f, BitmapDescriptorFactory.HUE_RED);
        Arrays.fill(this.f1870g, BitmapDescriptorFactory.HUE_RED);
        Arrays.fill(this.f1871h, 0);
        Arrays.fill(this.f1872i, 0);
        Arrays.fill(this.f1873j, 0);
        this.f1874k = 0;
    }

    /* renamed from: k */
    public final void m1685k(int i9) {
        if (this.f1867d == null || !m1660C(i9)) {
            return;
        }
        this.f1867d[i9] = 0.0f;
        this.f1868e[i9] = 0.0f;
        this.f1869f[i9] = 0.0f;
        this.f1870g[i9] = 0.0f;
        this.f1871h[i9] = 0;
        this.f1872i[i9] = 0;
        this.f1873j[i9] = 0;
        this.f1874k = (~(1 << i9)) & this.f1874k;
    }

    /* renamed from: l */
    public final int m1686l(int i9, int i10, int i11) {
        if (i9 == 0) {
            return 0;
        }
        int width = this.f1884u.getWidth();
        float f9 = width / 2;
        float fM1690r = f9 + (m1690r(Math.min(1.0f, Math.abs(i9) / width)) * f9);
        int iAbs = Math.abs(i10);
        return Math.min(iAbs > 0 ? Math.round(Math.abs(fM1690r / iAbs) * 1000.0f) * 4 : (int) (((Math.abs(i9) / i11) + 1.0f) * 256.0f), 600);
    }

    /* renamed from: m */
    public final int m1687m(View view, int i9, int i10, int i11, int i12) {
        float f9;
        float f10;
        float f11;
        float f12;
        int iM1683i = m1683i(i11, (int) this.f1877n, (int) this.f1876m);
        int iM1683i2 = m1683i(i12, (int) this.f1877n, (int) this.f1876m);
        int iAbs = Math.abs(i9);
        int iAbs2 = Math.abs(i10);
        int iAbs3 = Math.abs(iM1683i);
        int iAbs4 = Math.abs(iM1683i2);
        int i13 = iAbs3 + iAbs4;
        int i14 = iAbs + iAbs2;
        if (iM1683i != 0) {
            f9 = iAbs3;
            f10 = i13;
        } else {
            f9 = iAbs;
            f10 = i14;
        }
        float f13 = f9 / f10;
        if (iM1683i2 != 0) {
            f11 = iAbs4;
            f12 = i13;
        } else {
            f11 = iAbs2;
            f12 = i14;
        }
        return (int) ((m1686l(i9, iM1683i, this.f1881r.getViewHorizontalDragRange(view)) * f13) + (m1686l(i10, iM1683i2, this.f1881r.getViewVerticalDragRange(view)) * (f11 / f12)));
    }

    /* renamed from: n */
    public boolean m1688n(boolean z8) {
        if (this.f1864a == 2) {
            boolean zComputeScrollOffset = this.f1880q.computeScrollOffset();
            int currX = this.f1880q.getCurrX();
            int currY = this.f1880q.getCurrY();
            int left = currX - this.f1882s.getLeft();
            int top = currY - this.f1882s.getTop();
            if (left != 0) {
                C4647u.m18518N(this.f1882s, left);
            }
            if (top != 0) {
                C4647u.m18519O(this.f1882s, top);
            }
            if (left != 0 || top != 0) {
                this.f1881r.onViewPositionChanged(this.f1882s, currX, currY, left, top);
            }
            if (zComputeScrollOffset && currX == this.f1880q.getFinalX() && currY == this.f1880q.getFinalY()) {
                this.f1880q.abortAnimation();
                zComputeScrollOffset = false;
            }
            if (!zComputeScrollOffset) {
                if (z8) {
                    this.f1884u.post(this.f1885v);
                } else {
                    m1668K(0);
                }
            }
        }
        return this.f1864a == 2;
    }

    /* renamed from: q */
    public final void m1689q(float f9, float f10) {
        this.f1883t = true;
        this.f1881r.onViewReleased(this.f1882s, f9, f10);
        this.f1883t = false;
        if (this.f1864a == 1) {
            m1668K(0);
        }
    }

    /* renamed from: r */
    public final float m1690r(float f9) {
        return (float) Math.sin((f9 - 0.5f) * 0.47123894f);
    }

    /* renamed from: s */
    public final void m1691s(int i9, int i10, int i11, int i12) {
        int left = this.f1882s.getLeft();
        int top = this.f1882s.getTop();
        if (i11 != 0) {
            i9 = this.f1881r.clampViewPositionHorizontal(this.f1882s, i9, i11);
            C4647u.m18518N(this.f1882s, i9 - left);
        }
        int i13 = i9;
        if (i12 != 0) {
            i10 = this.f1881r.clampViewPositionVertical(this.f1882s, i10, i12);
            C4647u.m18519O(this.f1882s, i10 - top);
        }
        int i14 = i10;
        if (i11 == 0 && i12 == 0) {
            return;
        }
        this.f1881r.onViewPositionChanged(this.f1882s, i13, i14, i13 - left, i14 - top);
    }

    /* renamed from: t */
    public final void m1692t(int i9) {
        float[] fArr = this.f1867d;
        if (fArr == null || fArr.length <= i9) {
            int i10 = i9 + 1;
            float[] fArr2 = new float[i10];
            float[] fArr3 = new float[i10];
            float[] fArr4 = new float[i10];
            float[] fArr5 = new float[i10];
            int[] iArr = new int[i10];
            int[] iArr2 = new int[i10];
            int[] iArr3 = new int[i10];
            if (fArr != null) {
                System.arraycopy(fArr, 0, fArr2, 0, fArr.length);
                float[] fArr6 = this.f1868e;
                System.arraycopy(fArr6, 0, fArr3, 0, fArr6.length);
                float[] fArr7 = this.f1869f;
                System.arraycopy(fArr7, 0, fArr4, 0, fArr7.length);
                float[] fArr8 = this.f1870g;
                System.arraycopy(fArr8, 0, fArr5, 0, fArr8.length);
                int[] iArr4 = this.f1871h;
                System.arraycopy(iArr4, 0, iArr, 0, iArr4.length);
                int[] iArr5 = this.f1872i;
                System.arraycopy(iArr5, 0, iArr2, 0, iArr5.length);
                int[] iArr6 = this.f1873j;
                System.arraycopy(iArr6, 0, iArr3, 0, iArr6.length);
            }
            this.f1867d = fArr2;
            this.f1868e = fArr3;
            this.f1869f = fArr4;
            this.f1870g = fArr5;
            this.f1871h = iArr;
            this.f1872i = iArr2;
            this.f1873j = iArr3;
        }
    }

    /* renamed from: u */
    public View m1693u(int i9, int i10) {
        for (int childCount = this.f1884u.getChildCount() - 1; childCount >= 0; childCount--) {
            View childAt = this.f1884u.getChildAt(this.f1881r.getOrderedChildIndex(childCount));
            if (i9 >= childAt.getLeft() && i9 < childAt.getRight() && i10 >= childAt.getTop() && i10 < childAt.getBottom()) {
                return childAt;
            }
        }
        return null;
    }

    /* renamed from: v */
    public final boolean m1694v(int i9, int i10, int i11, int i12) {
        int left = this.f1882s.getLeft();
        int top = this.f1882s.getTop();
        int i13 = i9 - left;
        int i14 = i10 - top;
        if (i13 == 0 && i14 == 0) {
            this.f1880q.abortAnimation();
            m1668K(0);
            return false;
        }
        this.f1880q.startScroll(left, top, i13, i14, m1687m(this.f1882s, i13, i14, i11, i12));
        m1668K(2);
        return true;
    }

    /* renamed from: w */
    public View m1695w() {
        return this.f1882s;
    }

    /* renamed from: x */
    public int m1696x() {
        return this.f1878o;
    }

    /* renamed from: y */
    public final int m1697y(int i9, int i10) {
        int i11 = i9 < this.f1884u.getLeft() + this.f1878o ? 1 : 0;
        if (i10 < this.f1884u.getTop() + this.f1878o) {
            i11 |= 4;
        }
        if (i9 > this.f1884u.getRight() - this.f1878o) {
            i11 |= 2;
        }
        return i10 > this.f1884u.getBottom() - this.f1878o ? i11 | 8 : i11;
    }

    /* renamed from: z */
    public int m1698z() {
        return this.f1865b;
    }
}
