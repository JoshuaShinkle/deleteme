package androidx.core.widget;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.FocusFinder;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.animation.AnimationUtils;
import android.widget.EdgeEffect;
import android.widget.FrameLayout;
import android.widget.OverScroller;
import android.widget.ScrollView;
import com.google.android.exoplayer2.extractor.p037ts.TsExtractor;
import com.google.android.gms.auth.api.credentials.CredentialsApi;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.util.ArrayList;
import net.sqlcipher.database.SQLiteDatabase;
import p042d0.C4613a;
import p042d0.C4637m;
import p042d0.C4642p;
import p042d0.C4647u;
import p042d0.InterfaceC4633k;
import p042d0.InterfaceC4641o;
import p052e0.C4704m;
import p052e0.C4706o;

/* loaded from: classes.dex */
public class NestedScrollView extends FrameLayout implements InterfaceC4641o, InterfaceC4633k {

    /* renamed from: B */
    public static final C0324a f1791B = new C0324a();

    /* renamed from: C */
    public static final int[] f1792C = {R.attr.fillViewport};

    /* renamed from: A */
    public InterfaceC0325b f1793A;

    /* renamed from: b */
    public long f1794b;

    /* renamed from: c */
    public final Rect f1795c;

    /* renamed from: d */
    public OverScroller f1796d;

    /* renamed from: e */
    public EdgeEffect f1797e;

    /* renamed from: f */
    public EdgeEffect f1798f;

    /* renamed from: g */
    public int f1799g;

    /* renamed from: h */
    public boolean f1800h;

    /* renamed from: i */
    public boolean f1801i;

    /* renamed from: j */
    public View f1802j;

    /* renamed from: k */
    public boolean f1803k;

    /* renamed from: l */
    public VelocityTracker f1804l;

    /* renamed from: m */
    public boolean f1805m;

    /* renamed from: n */
    public boolean f1806n;

    /* renamed from: o */
    public int f1807o;

    /* renamed from: p */
    public int f1808p;

    /* renamed from: q */
    public int f1809q;

    /* renamed from: r */
    public int f1810r;

    /* renamed from: s */
    public final int[] f1811s;

    /* renamed from: t */
    public final int[] f1812t;

    /* renamed from: u */
    public int f1813u;

    /* renamed from: v */
    public int f1814v;

    /* renamed from: w */
    public SavedState f1815w;

    /* renamed from: x */
    public final C4642p f1816x;

    /* renamed from: y */
    public final C4637m f1817y;

    /* renamed from: z */
    public float f1818z;

    public static class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new C0323a();

        /* renamed from: b */
        public int f1819b;

        /* renamed from: androidx.core.widget.NestedScrollView$SavedState$a */
        public class C0323a implements Parcelable.Creator<SavedState> {
            @Override // android.os.Parcelable.Creator
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            @Override // android.os.Parcelable.Creator
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public SavedState[] newArray(int i9) {
                return new SavedState[i9];
            }
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        public String toString() {
            return "HorizontalScrollView.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " scrollPosition=" + this.f1819b + "}";
        }

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i9) {
            super.writeToParcel(parcel, i9);
            parcel.writeInt(this.f1819b);
        }

        public SavedState(Parcel parcel) {
            super(parcel);
            this.f1819b = parcel.readInt();
        }
    }

    /* renamed from: androidx.core.widget.NestedScrollView$a */
    public static class C0324a extends C4613a {
        @Override // p042d0.C4613a
        public void onInitializeAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
            super.onInitializeAccessibilityEvent(view, accessibilityEvent);
            NestedScrollView nestedScrollView = (NestedScrollView) view;
            accessibilityEvent.setClassName(ScrollView.class.getName());
            accessibilityEvent.setScrollable(nestedScrollView.getScrollRange() > 0);
            accessibilityEvent.setScrollX(nestedScrollView.getScrollX());
            accessibilityEvent.setScrollY(nestedScrollView.getScrollY());
            C4706o.m18840a(accessibilityEvent, nestedScrollView.getScrollX());
            C4706o.m18841b(accessibilityEvent, nestedScrollView.getScrollRange());
        }

        @Override // p042d0.C4613a
        public void onInitializeAccessibilityNodeInfo(View view, C4704m c4704m) {
            int scrollRange;
            super.onInitializeAccessibilityNodeInfo(view, c4704m);
            NestedScrollView nestedScrollView = (NestedScrollView) view;
            c4704m.m18781U(ScrollView.class.getName());
            if (!nestedScrollView.isEnabled() || (scrollRange = nestedScrollView.getScrollRange()) <= 0) {
                return;
            }
            c4704m.m18814o0(true);
            if (nestedScrollView.getScrollY() > 0) {
                c4704m.m18789b(C4704m.a.f16438q);
                c4704m.m18789b(C4704m.a.f16412B);
            }
            if (nestedScrollView.getScrollY() < scrollRange) {
                c4704m.m18789b(C4704m.a.f16437p);
                c4704m.m18789b(C4704m.a.f16414D);
            }
        }

        @Override // p042d0.C4613a
        public boolean performAccessibilityAction(View view, int i9, Bundle bundle) {
            if (super.performAccessibilityAction(view, i9, bundle)) {
                return true;
            }
            NestedScrollView nestedScrollView = (NestedScrollView) view;
            if (!nestedScrollView.isEnabled()) {
                return false;
            }
            if (i9 != 4096) {
                if (i9 == 8192 || i9 == 16908344) {
                    int iMax = Math.max(nestedScrollView.getScrollY() - ((nestedScrollView.getHeight() - nestedScrollView.getPaddingBottom()) - nestedScrollView.getPaddingTop()), 0);
                    if (iMax == nestedScrollView.getScrollY()) {
                        return false;
                    }
                    nestedScrollView.m1527J(0, iMax, true);
                    return true;
                }
                if (i9 != 16908346) {
                    return false;
                }
            }
            int iMin = Math.min(nestedScrollView.getScrollY() + ((nestedScrollView.getHeight() - nestedScrollView.getPaddingBottom()) - nestedScrollView.getPaddingTop()), nestedScrollView.getScrollRange());
            if (iMin == nestedScrollView.getScrollY()) {
                return false;
            }
            nestedScrollView.m1527J(0, iMin, true);
            return true;
        }
    }

    /* renamed from: androidx.core.widget.NestedScrollView$b */
    public interface InterfaceC0325b {
        /* renamed from: a */
        void m1554a(NestedScrollView nestedScrollView, int i9, int i10, int i11, int i12);
    }

    public NestedScrollView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    /* renamed from: d */
    public static int m1516d(int i9, int i10, int i11) {
        if (i10 >= i11 || i9 < 0) {
            return 0;
        }
        return i10 + i9 > i11 ? i11 - i10 : i9;
    }

    private float getVerticalScrollFactorCompat() {
        if (this.f1818z == BitmapDescriptorFactory.HUE_RED) {
            TypedValue typedValue = new TypedValue();
            Context context = getContext();
            if (!context.getTheme().resolveAttribute(R.attr.listPreferredItemHeight, typedValue, true)) {
                throw new IllegalStateException("Expected theme to define listPreferredItemHeight.");
            }
            this.f1818z = typedValue.getDimension(context.getResources().getDisplayMetrics());
        }
        return this.f1818z;
    }

    /* renamed from: v */
    public static boolean m1517v(View view, View view2) {
        if (view == view2) {
            return true;
        }
        Object parent = view.getParent();
        return (parent instanceof ViewGroup) && m1517v((View) parent, view2);
    }

    /* renamed from: A */
    public boolean m1518A(int i9) {
        boolean z8 = i9 == 130;
        int height = getHeight();
        if (z8) {
            this.f1795c.top = getScrollY() + height;
            int childCount = getChildCount();
            if (childCount > 0) {
                View childAt = getChildAt(childCount - 1);
                int bottom = childAt.getBottom() + ((FrameLayout.LayoutParams) childAt.getLayoutParams()).bottomMargin + getPaddingBottom();
                Rect rect = this.f1795c;
                if (rect.top + height > bottom) {
                    rect.top = bottom - height;
                }
            }
        } else {
            this.f1795c.top = getScrollY() - height;
            Rect rect2 = this.f1795c;
            if (rect2.top < 0) {
                rect2.top = 0;
            }
        }
        Rect rect3 = this.f1795c;
        int i10 = rect3.top;
        int i11 = height + i10;
        rect3.bottom = i11;
        return m1521D(i9, i10, i11);
    }

    /* renamed from: B */
    public final void m1519B() {
        VelocityTracker velocityTracker = this.f1804l;
        if (velocityTracker != null) {
            velocityTracker.recycle();
            this.f1804l = null;
        }
    }

    /* renamed from: C */
    public final void m1520C(boolean z8) {
        if (z8) {
            m1528K(2, 1);
        } else {
            stopNestedScroll(1);
        }
        this.f1814v = getScrollY();
        C4647u.m18524T(this);
    }

    /* renamed from: D */
    public final boolean m1521D(int i9, int i10, int i11) {
        int height = getHeight();
        int scrollY = getScrollY();
        int i12 = height + scrollY;
        boolean z8 = false;
        boolean z9 = i9 == 33;
        View viewM1539m = m1539m(z9, i10, i11);
        if (viewM1539m == null) {
            viewM1539m = this;
        }
        if (i10 < scrollY || i11 > i12) {
            m1535h(z9 ? i10 - scrollY : i11 - i12);
            z8 = true;
        }
        if (viewM1539m != findFocus()) {
            viewM1539m.requestFocus(i9);
        }
        return z8;
    }

    /* renamed from: E */
    public final void m1522E(View view) {
        view.getDrawingRect(this.f1795c);
        offsetDescendantRectToMyCoords(view, this.f1795c);
        int iM1532e = m1532e(this.f1795c);
        if (iM1532e != 0) {
            scrollBy(0, iM1532e);
        }
    }

    /* renamed from: F */
    public final boolean m1523F(Rect rect, boolean z8) {
        int iM1532e = m1532e(rect);
        boolean z9 = iM1532e != 0;
        if (z9) {
            if (z8) {
                scrollBy(0, iM1532e);
            } else {
                m1524G(0, iM1532e);
            }
        }
        return z9;
    }

    /* renamed from: G */
    public final void m1524G(int i9, int i10) {
        m1525H(i9, i10, SQLiteDatabase.MAX_SQL_CACHE_SIZE, false);
    }

    /* renamed from: H */
    public final void m1525H(int i9, int i10, int i11, boolean z8) {
        if (getChildCount() == 0) {
            return;
        }
        if (AnimationUtils.currentAnimationTimeMillis() - this.f1794b > 250) {
            View childAt = getChildAt(0);
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) childAt.getLayoutParams();
            int height = childAt.getHeight() + layoutParams.topMargin + layoutParams.bottomMargin;
            int height2 = (getHeight() - getPaddingTop()) - getPaddingBottom();
            int scrollY = getScrollY();
            this.f1796d.startScroll(getScrollX(), scrollY, 0, Math.max(0, Math.min(i10 + scrollY, Math.max(0, height - height2))) - scrollY, i11);
            m1520C(z8);
        } else {
            if (!this.f1796d.isFinished()) {
                m1529a();
            }
            scrollBy(i9, i10);
        }
        this.f1794b = AnimationUtils.currentAnimationTimeMillis();
    }

    /* renamed from: I */
    public void m1526I(int i9, int i10, int i11, boolean z8) {
        m1525H(i9 - getScrollX(), i10 - getScrollY(), i11, z8);
    }

    /* renamed from: J */
    public void m1527J(int i9, int i10, boolean z8) {
        m1526I(i9, i10, SQLiteDatabase.MAX_SQL_CACHE_SIZE, z8);
    }

    /* renamed from: K */
    public boolean m1528K(int i9, int i10) {
        return this.f1817y.m18490q(i9, i10);
    }

    /* renamed from: a */
    public final void m1529a() {
        this.f1796d.abortAnimation();
        stopNestedScroll(1);
    }

    @Override // android.view.ViewGroup
    public void addView(View view) {
        if (getChildCount() > 0) {
            throw new IllegalStateException("ScrollView can host only one direct child");
        }
        super.addView(view);
    }

    /* renamed from: b */
    public boolean m1530b(int i9) {
        View viewFindFocus = findFocus();
        if (viewFindFocus == this) {
            viewFindFocus = null;
        }
        View viewFindNextFocus = FocusFinder.getInstance().findNextFocus(this, viewFindFocus, i9);
        int maxScrollAmount = getMaxScrollAmount();
        if (viewFindNextFocus == null || !m1548w(viewFindNextFocus, maxScrollAmount, getHeight())) {
            if (i9 == 33 && getScrollY() < maxScrollAmount) {
                maxScrollAmount = getScrollY();
            } else if (i9 == 130 && getChildCount() > 0) {
                View childAt = getChildAt(0);
                maxScrollAmount = Math.min((childAt.getBottom() + ((FrameLayout.LayoutParams) childAt.getLayoutParams()).bottomMargin) - ((getScrollY() + getHeight()) - getPaddingBottom()), maxScrollAmount);
            }
            if (maxScrollAmount == 0) {
                return false;
            }
            if (i9 != 130) {
                maxScrollAmount = -maxScrollAmount;
            }
            m1535h(maxScrollAmount);
        } else {
            viewFindNextFocus.getDrawingRect(this.f1795c);
            offsetDescendantRectToMyCoords(viewFindNextFocus, this.f1795c);
            m1535h(m1532e(this.f1795c));
            viewFindNextFocus.requestFocus(i9);
        }
        if (viewFindFocus == null || !viewFindFocus.isFocused() || !m1547u(viewFindFocus)) {
            return true;
        }
        int descendantFocusability = getDescendantFocusability();
        setDescendantFocusability(131072);
        requestFocus();
        setDescendantFocusability(descendantFocusability);
        return true;
    }

    /* renamed from: c */
    public final boolean m1531c() {
        if (getChildCount() <= 0) {
            return false;
        }
        View childAt = getChildAt(0);
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) childAt.getLayoutParams();
        return (childAt.getHeight() + layoutParams.topMargin) + layoutParams.bottomMargin > (getHeight() - getPaddingTop()) - getPaddingBottom();
    }

    @Override // android.view.View
    public int computeHorizontalScrollExtent() {
        return super.computeHorizontalScrollExtent();
    }

    @Override // android.view.View
    public int computeHorizontalScrollOffset() {
        return super.computeHorizontalScrollOffset();
    }

    @Override // android.view.View
    public int computeHorizontalScrollRange() {
        return super.computeHorizontalScrollRange();
    }

    @Override // android.view.View
    public void computeScroll() {
        if (this.f1796d.isFinished()) {
            return;
        }
        this.f1796d.computeScrollOffset();
        int currY = this.f1796d.getCurrY();
        int i9 = currY - this.f1814v;
        this.f1814v = currY;
        int[] iArr = this.f1812t;
        boolean z8 = false;
        iArr[1] = 0;
        m1533f(0, i9, iArr, null, 1);
        int i10 = i9 - this.f1812t[1];
        int scrollRange = getScrollRange();
        if (i10 != 0) {
            int scrollY = getScrollY();
            m1551z(0, i10, getScrollX(), scrollY, 0, scrollRange, 0, 0, false);
            int scrollY2 = getScrollY() - scrollY;
            int i11 = i10 - scrollY2;
            int[] iArr2 = this.f1812t;
            iArr2[1] = 0;
            m1534g(0, scrollY2, 0, i11, this.f1811s, 1, iArr2);
            i10 = i11 - this.f1812t[1];
        }
        if (i10 != 0) {
            int overScrollMode = getOverScrollMode();
            if (overScrollMode == 0 || (overScrollMode == 1 && scrollRange > 0)) {
                z8 = true;
            }
            if (z8) {
                m1537k();
                if (i10 < 0) {
                    if (this.f1797e.isFinished()) {
                        this.f1797e.onAbsorb((int) this.f1796d.getCurrVelocity());
                    }
                } else if (this.f1798f.isFinished()) {
                    this.f1798f.onAbsorb((int) this.f1796d.getCurrVelocity());
                }
            }
            m1529a();
        }
        if (this.f1796d.isFinished()) {
            stopNestedScroll(1);
        } else {
            C4647u.m18524T(this);
        }
    }

    @Override // android.view.View
    public int computeVerticalScrollExtent() {
        return super.computeVerticalScrollExtent();
    }

    @Override // android.view.View
    public int computeVerticalScrollOffset() {
        return Math.max(0, super.computeVerticalScrollOffset());
    }

    @Override // android.view.View
    public int computeVerticalScrollRange() {
        int childCount = getChildCount();
        int height = (getHeight() - getPaddingBottom()) - getPaddingTop();
        if (childCount == 0) {
            return height;
        }
        View childAt = getChildAt(0);
        int bottom = childAt.getBottom() + ((FrameLayout.LayoutParams) childAt.getLayoutParams()).bottomMargin;
        int scrollY = getScrollY();
        int iMax = Math.max(0, bottom - height);
        return scrollY < 0 ? bottom - scrollY : scrollY > iMax ? bottom + (scrollY - iMax) : bottom;
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        return super.dispatchKeyEvent(keyEvent) || m1538l(keyEvent);
    }

    @Override // android.view.View
    public boolean dispatchNestedFling(float f9, float f10, boolean z8) {
        return this.f1817y.m18474a(f9, f10, z8);
    }

    @Override // android.view.View
    public boolean dispatchNestedPreFling(float f9, float f10) {
        return this.f1817y.m18475b(f9, f10);
    }

    @Override // android.view.View
    public boolean dispatchNestedPreScroll(int i9, int i10, int[] iArr, int[] iArr2) {
        return m1533f(i9, i10, iArr, iArr2, 0);
    }

    @Override // android.view.View
    public boolean dispatchNestedScroll(int i9, int i10, int i11, int i12, int[] iArr) {
        return this.f1817y.m18479f(i9, i10, i11, i12, iArr);
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        int paddingLeft;
        super.draw(canvas);
        if (this.f1797e != null) {
            int scrollY = getScrollY();
            int paddingLeft2 = 0;
            if (!this.f1797e.isFinished()) {
                int iSave = canvas.save();
                int width = getWidth();
                int height = getHeight();
                int iMin = Math.min(0, scrollY);
                if (getClipToPadding()) {
                    width -= getPaddingLeft() + getPaddingRight();
                    paddingLeft = getPaddingLeft() + 0;
                } else {
                    paddingLeft = 0;
                }
                if (getClipToPadding()) {
                    height -= getPaddingTop() + getPaddingBottom();
                    iMin += getPaddingTop();
                }
                canvas.translate(paddingLeft, iMin);
                this.f1797e.setSize(width, height);
                if (this.f1797e.draw(canvas)) {
                    C4647u.m18524T(this);
                }
                canvas.restoreToCount(iSave);
            }
            if (this.f1798f.isFinished()) {
                return;
            }
            int iSave2 = canvas.save();
            int width2 = getWidth();
            int height2 = getHeight();
            int iMax = Math.max(getScrollRange(), scrollY) + height2;
            if (getClipToPadding()) {
                width2 -= getPaddingLeft() + getPaddingRight();
                paddingLeft2 = 0 + getPaddingLeft();
            }
            if (getClipToPadding()) {
                height2 -= getPaddingTop() + getPaddingBottom();
                iMax -= getPaddingBottom();
            }
            canvas.translate(paddingLeft2 - width2, iMax);
            canvas.rotate(180.0f, width2, BitmapDescriptorFactory.HUE_RED);
            this.f1798f.setSize(width2, height2);
            if (this.f1798f.draw(canvas)) {
                C4647u.m18524T(this);
            }
            canvas.restoreToCount(iSave2);
        }
    }

    /* renamed from: e */
    public int m1532e(Rect rect) {
        if (getChildCount() == 0) {
            return 0;
        }
        int height = getHeight();
        int scrollY = getScrollY();
        int i9 = scrollY + height;
        int verticalFadingEdgeLength = getVerticalFadingEdgeLength();
        if (rect.top > 0) {
            scrollY += verticalFadingEdgeLength;
        }
        View childAt = getChildAt(0);
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) childAt.getLayoutParams();
        int i10 = rect.bottom < (childAt.getHeight() + layoutParams.topMargin) + layoutParams.bottomMargin ? i9 - verticalFadingEdgeLength : i9;
        int i11 = rect.bottom;
        if (i11 > i10 && rect.top > scrollY) {
            return Math.min((rect.height() > height ? rect.top - scrollY : rect.bottom - i10) + 0, (childAt.getBottom() + layoutParams.bottomMargin) - i9);
        }
        if (rect.top >= scrollY || i11 >= i10) {
            return 0;
        }
        return Math.max(rect.height() > height ? 0 - (i10 - rect.bottom) : 0 - (scrollY - rect.top), -getScrollY());
    }

    /* renamed from: f */
    public boolean m1533f(int i9, int i10, int[] iArr, int[] iArr2, int i11) {
        return this.f1817y.m18477d(i9, i10, iArr, iArr2, i11);
    }

    /* renamed from: g */
    public void m1534g(int i9, int i10, int i11, int i12, int[] iArr, int i13, int[] iArr2) {
        this.f1817y.m18478e(i9, i10, i11, i12, iArr, i13, iArr2);
    }

    @Override // android.view.View
    public float getBottomFadingEdgeStrength() {
        if (getChildCount() == 0) {
            return BitmapDescriptorFactory.HUE_RED;
        }
        View childAt = getChildAt(0);
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) childAt.getLayoutParams();
        int verticalFadingEdgeLength = getVerticalFadingEdgeLength();
        int bottom = ((childAt.getBottom() + layoutParams.bottomMargin) - getScrollY()) - (getHeight() - getPaddingBottom());
        if (bottom < verticalFadingEdgeLength) {
            return bottom / verticalFadingEdgeLength;
        }
        return 1.0f;
    }

    public int getMaxScrollAmount() {
        return (int) (getHeight() * 0.5f);
    }

    @Override // android.view.ViewGroup
    public int getNestedScrollAxes() {
        return this.f1816x.m18495a();
    }

    public int getScrollRange() {
        if (getChildCount() <= 0) {
            return 0;
        }
        View childAt = getChildAt(0);
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) childAt.getLayoutParams();
        return Math.max(0, ((childAt.getHeight() + layoutParams.topMargin) + layoutParams.bottomMargin) - ((getHeight() - getPaddingTop()) - getPaddingBottom()));
    }

    @Override // android.view.View
    public float getTopFadingEdgeStrength() {
        if (getChildCount() == 0) {
            return BitmapDescriptorFactory.HUE_RED;
        }
        int verticalFadingEdgeLength = getVerticalFadingEdgeLength();
        int scrollY = getScrollY();
        if (scrollY < verticalFadingEdgeLength) {
            return scrollY / verticalFadingEdgeLength;
        }
        return 1.0f;
    }

    /* renamed from: h */
    public final void m1535h(int i9) {
        if (i9 != 0) {
            if (this.f1806n) {
                m1524G(0, i9);
            } else {
                scrollBy(0, i9);
            }
        }
    }

    @Override // android.view.View
    public boolean hasNestedScrollingParent() {
        return m1542p(0);
    }

    /* renamed from: i */
    public final void m1536i() {
        this.f1803k = false;
        m1519B();
        stopNestedScroll(0);
        EdgeEffect edgeEffect = this.f1797e;
        if (edgeEffect != null) {
            edgeEffect.onRelease();
            this.f1798f.onRelease();
        }
    }

    @Override // android.view.View
    public boolean isNestedScrollingEnabled() {
        return this.f1817y.m18486m();
    }

    @Override // p042d0.InterfaceC4641o
    /* renamed from: j */
    public void mo595j(View view, int i9, int i10, int i11, int i12, int i13, int[] iArr) {
        m1549x(i12, i13, iArr);
    }

    /* renamed from: k */
    public final void m1537k() {
        if (getOverScrollMode() == 2) {
            this.f1797e = null;
            this.f1798f = null;
        } else if (this.f1797e == null) {
            Context context = getContext();
            this.f1797e = new EdgeEffect(context);
            this.f1798f = new EdgeEffect(context);
        }
    }

    /* renamed from: l */
    public boolean m1538l(KeyEvent keyEvent) {
        this.f1795c.setEmpty();
        boolean zM1531c = m1531c();
        int i9 = TsExtractor.TS_STREAM_TYPE_HDMV_DTS;
        if (!zM1531c) {
            if (!isFocused() || keyEvent.getKeyCode() == 4) {
                return false;
            }
            View viewFindFocus = findFocus();
            if (viewFindFocus == this) {
                viewFindFocus = null;
            }
            View viewFindNextFocus = FocusFinder.getInstance().findNextFocus(this, viewFindFocus, TsExtractor.TS_STREAM_TYPE_HDMV_DTS);
            return (viewFindNextFocus == null || viewFindNextFocus == this || !viewFindNextFocus.requestFocus(TsExtractor.TS_STREAM_TYPE_HDMV_DTS)) ? false : true;
        }
        if (keyEvent.getAction() != 0) {
            return false;
        }
        int keyCode = keyEvent.getKeyCode();
        if (keyCode == 19) {
            return !keyEvent.isAltPressed() ? m1530b(33) : m1541o(33);
        }
        if (keyCode == 20) {
            return !keyEvent.isAltPressed() ? m1530b(TsExtractor.TS_STREAM_TYPE_HDMV_DTS) : m1541o(TsExtractor.TS_STREAM_TYPE_HDMV_DTS);
        }
        if (keyCode != 62) {
            return false;
        }
        if (keyEvent.isShiftPressed()) {
            i9 = 33;
        }
        m1518A(i9);
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x004f  */
    /* renamed from: m */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final View m1539m(boolean z8, int i9, int i10) {
        ArrayList<View> focusables = getFocusables(2);
        int size = focusables.size();
        View view = null;
        boolean z9 = false;
        for (int i11 = 0; i11 < size; i11++) {
            View view2 = focusables.get(i11);
            int top = view2.getTop();
            int bottom = view2.getBottom();
            if (i9 < bottom && top < i10) {
                boolean z10 = i9 < top && bottom < i10;
                if (view == null) {
                    view = view2;
                    z9 = z10;
                } else {
                    boolean z11 = (z8 && top < view.getTop()) || (!z8 && bottom > view.getBottom());
                    if (z9) {
                        if (z10 && z11) {
                            view = view2;
                        }
                    } else if (z10) {
                        view = view2;
                        z9 = true;
                    } else if (z11) {
                    }
                }
            }
        }
        return view;
    }

    @Override // android.view.ViewGroup
    public void measureChild(View view, int i9, int i10) {
        view.measure(ViewGroup.getChildMeasureSpec(i9, getPaddingLeft() + getPaddingRight(), view.getLayoutParams().width), View.MeasureSpec.makeMeasureSpec(0, 0));
    }

    @Override // android.view.ViewGroup
    public void measureChildWithMargins(View view, int i9, int i10, int i11, int i12) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        view.measure(ViewGroup.getChildMeasureSpec(i9, getPaddingLeft() + getPaddingRight() + marginLayoutParams.leftMargin + marginLayoutParams.rightMargin + i10, marginLayoutParams.width), View.MeasureSpec.makeMeasureSpec(marginLayoutParams.topMargin + marginLayoutParams.bottomMargin, 0));
    }

    /* renamed from: n */
    public void m1540n(int i9) {
        if (getChildCount() > 0) {
            this.f1796d.fling(getScrollX(), getScrollY(), 0, i9, 0, 0, Integer.MIN_VALUE, Integer.MAX_VALUE, 0, 0);
            m1520C(true);
        }
    }

    /* renamed from: o */
    public boolean m1541o(int i9) {
        int childCount;
        boolean z8 = i9 == 130;
        int height = getHeight();
        Rect rect = this.f1795c;
        rect.top = 0;
        rect.bottom = height;
        if (z8 && (childCount = getChildCount()) > 0) {
            View childAt = getChildAt(childCount - 1);
            this.f1795c.bottom = childAt.getBottom() + ((FrameLayout.LayoutParams) childAt.getLayoutParams()).bottomMargin + getPaddingBottom();
            Rect rect2 = this.f1795c;
            rect2.top = rect2.bottom - height;
        }
        Rect rect3 = this.f1795c;
        return m1521D(i9, rect3.top, rect3.bottom);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.f1801i = false;
    }

    @Override // android.view.View
    public boolean onGenericMotionEvent(MotionEvent motionEvent) {
        if ((motionEvent.getSource() & 2) != 0 && motionEvent.getAction() == 8 && !this.f1803k) {
            float axisValue = motionEvent.getAxisValue(9);
            if (axisValue != BitmapDescriptorFactory.HUE_RED) {
                int verticalScrollFactorCompat = (int) (axisValue * getVerticalScrollFactorCompat());
                int scrollRange = getScrollRange();
                int scrollY = getScrollY();
                int i9 = scrollY - verticalScrollFactorCompat;
                if (i9 < 0) {
                    scrollRange = 0;
                } else if (i9 <= scrollRange) {
                    scrollRange = i9;
                }
                if (scrollRange != scrollY) {
                    super.scrollTo(getScrollX(), scrollRange);
                    return true;
                }
            }
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x007d  */
    @Override // android.view.ViewGroup
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 2 && this.f1803k) {
            return true;
        }
        int i9 = action & 255;
        if (i9 == 0) {
            int y8 = (int) motionEvent.getY();
            if (m1543q((int) motionEvent.getX(), y8)) {
                this.f1799g = y8;
                this.f1810r = motionEvent.getPointerId(0);
                m1544r();
                this.f1804l.addMovement(motionEvent);
                this.f1796d.computeScrollOffset();
                this.f1803k = !this.f1796d.isFinished();
                m1528K(2, 0);
            } else {
                this.f1803k = false;
                m1519B();
            }
        } else if (i9 == 1) {
            this.f1803k = false;
            this.f1810r = -1;
            m1519B();
            if (this.f1796d.springBack(getScrollX(), getScrollY(), 0, 0, 0, getScrollRange())) {
                C4647u.m18524T(this);
            }
            stopNestedScroll(0);
        } else if (i9 == 2) {
            int i10 = this.f1810r;
            if (i10 != -1) {
                int iFindPointerIndex = motionEvent.findPointerIndex(i10);
                if (iFindPointerIndex == -1) {
                    Log.e("NestedScrollView", "Invalid pointerId=" + i10 + " in onInterceptTouchEvent");
                } else {
                    int y9 = (int) motionEvent.getY(iFindPointerIndex);
                    if (Math.abs(y9 - this.f1799g) > this.f1807o && (2 & getNestedScrollAxes()) == 0) {
                        this.f1803k = true;
                        this.f1799g = y9;
                        m1546t();
                        this.f1804l.addMovement(motionEvent);
                        this.f1813u = 0;
                        ViewParent parent = getParent();
                        if (parent != null) {
                            parent.requestDisallowInterceptTouchEvent(true);
                        }
                    }
                }
            }
        } else if (i9 != 3) {
            if (i9 == 6) {
                m1550y(motionEvent);
            }
        }
        return this.f1803k;
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z8, int i9, int i10, int i11, int i12) {
        super.onLayout(z8, i9, i10, i11, i12);
        int measuredHeight = 0;
        this.f1800h = false;
        View view = this.f1802j;
        if (view != null && m1517v(view, this)) {
            m1522E(this.f1802j);
        }
        this.f1802j = null;
        if (!this.f1801i) {
            if (this.f1815w != null) {
                scrollTo(getScrollX(), this.f1815w.f1819b);
                this.f1815w = null;
            }
            if (getChildCount() > 0) {
                View childAt = getChildAt(0);
                FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) childAt.getLayoutParams();
                measuredHeight = childAt.getMeasuredHeight() + layoutParams.topMargin + layoutParams.bottomMargin;
            }
            int paddingTop = ((i12 - i10) - getPaddingTop()) - getPaddingBottom();
            int scrollY = getScrollY();
            int iM1516d = m1516d(scrollY, paddingTop, measuredHeight);
            if (iM1516d != scrollY) {
                scrollTo(getScrollX(), iM1516d);
            }
        }
        scrollTo(getScrollX(), getScrollY());
        this.f1801i = true;
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int i9, int i10) {
        super.onMeasure(i9, i10);
        if (this.f1805m && View.MeasureSpec.getMode(i10) != 0 && getChildCount() > 0) {
            View childAt = getChildAt(0);
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) childAt.getLayoutParams();
            int measuredHeight = childAt.getMeasuredHeight();
            int measuredHeight2 = (((getMeasuredHeight() - getPaddingTop()) - getPaddingBottom()) - layoutParams.topMargin) - layoutParams.bottomMargin;
            if (measuredHeight < measuredHeight2) {
                childAt.measure(ViewGroup.getChildMeasureSpec(i9, getPaddingLeft() + getPaddingRight() + layoutParams.leftMargin + layoutParams.rightMargin, layoutParams.width), View.MeasureSpec.makeMeasureSpec(measuredHeight2, 1073741824));
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public boolean onNestedFling(View view, float f9, float f10, boolean z8) {
        if (z8) {
            return false;
        }
        dispatchNestedFling(BitmapDescriptorFactory.HUE_RED, f10, true);
        m1540n((int) f10);
        return true;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public boolean onNestedPreFling(View view, float f9, float f10) {
        return dispatchNestedPreFling(f9, f10);
    }

    @Override // p042d0.InterfaceC4639n
    public void onNestedPreScroll(View view, int i9, int i10, int[] iArr, int i11) {
        m1533f(i9, i10, iArr, null, i11);
    }

    @Override // p042d0.InterfaceC4639n
    public void onNestedScroll(View view, int i9, int i10, int i11, int i12, int i13) {
        m1549x(i12, i13, null);
    }

    @Override // p042d0.InterfaceC4639n
    public void onNestedScrollAccepted(View view, View view2, int i9, int i10) {
        this.f1816x.m18497c(view, view2, i9, i10);
        m1528K(2, i10);
    }

    @Override // android.view.View
    public void onOverScrolled(int i9, int i10, boolean z8, boolean z9) {
        super.scrollTo(i9, i10);
    }

    @Override // android.view.ViewGroup
    public boolean onRequestFocusInDescendants(int i9, Rect rect) {
        if (i9 == 2) {
            i9 = TsExtractor.TS_STREAM_TYPE_HDMV_DTS;
        } else if (i9 == 1) {
            i9 = 33;
        }
        View viewFindNextFocus = rect == null ? FocusFinder.getInstance().findNextFocus(this, null, i9) : FocusFinder.getInstance().findNextFocusFromRect(this, rect, i9);
        if (viewFindNextFocus == null || m1547u(viewFindNextFocus)) {
            return false;
        }
        return viewFindNextFocus.requestFocus(i9, rect);
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        this.f1815w = savedState;
        requestLayout();
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.f1819b = getScrollY();
        return savedState;
    }

    @Override // android.view.View
    public void onScrollChanged(int i9, int i10, int i11, int i12) {
        super.onScrollChanged(i9, i10, i11, i12);
        InterfaceC0325b interfaceC0325b = this.f1793A;
        if (interfaceC0325b != null) {
            interfaceC0325b.m1554a(this, i9, i10, i11, i12);
        }
    }

    @Override // android.view.View
    public void onSizeChanged(int i9, int i10, int i11, int i12) {
        super.onSizeChanged(i9, i10, i11, i12);
        View viewFindFocus = findFocus();
        if (viewFindFocus == null || this == viewFindFocus || !m1548w(viewFindFocus, 0, i12)) {
            return;
        }
        viewFindFocus.getDrawingRect(this.f1795c);
        offsetDescendantRectToMyCoords(viewFindFocus, this.f1795c);
        m1535h(m1532e(this.f1795c));
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public boolean onStartNestedScroll(View view, View view2, int i9) {
        return onStartNestedScroll(view, view2, i9, 0);
    }

    @Override // p042d0.InterfaceC4639n
    public boolean onStartNestedScroll(View view, View view2, int i9, int i10) {
        return (i9 & 2) != 0;
    }

    @Override // p042d0.InterfaceC4639n
    public void onStopNestedScroll(View view, int i9) {
        this.f1816x.m18499e(view, i9);
        stopNestedScroll(i9);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        ViewParent parent;
        m1546t();
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            this.f1813u = 0;
        }
        MotionEvent motionEventObtain = MotionEvent.obtain(motionEvent);
        motionEventObtain.offsetLocation(BitmapDescriptorFactory.HUE_RED, this.f1813u);
        if (actionMasked != 0) {
            if (actionMasked == 1) {
                VelocityTracker velocityTracker = this.f1804l;
                velocityTracker.computeCurrentVelocity(CredentialsApi.ACTIVITY_RESULT_ADD_ACCOUNT, this.f1809q);
                int yVelocity = (int) velocityTracker.getYVelocity(this.f1810r);
                if (Math.abs(yVelocity) >= this.f1808p) {
                    int i9 = -yVelocity;
                    float f9 = i9;
                    if (!dispatchNestedPreFling(BitmapDescriptorFactory.HUE_RED, f9)) {
                        dispatchNestedFling(BitmapDescriptorFactory.HUE_RED, f9, true);
                        m1540n(i9);
                    }
                } else if (this.f1796d.springBack(getScrollX(), getScrollY(), 0, 0, 0, getScrollRange())) {
                    C4647u.m18524T(this);
                }
                this.f1810r = -1;
                m1536i();
            } else if (actionMasked == 2) {
                int iFindPointerIndex = motionEvent.findPointerIndex(this.f1810r);
                if (iFindPointerIndex == -1) {
                    Log.e("NestedScrollView", "Invalid pointerId=" + this.f1810r + " in onTouchEvent");
                } else {
                    int y8 = (int) motionEvent.getY(iFindPointerIndex);
                    int i10 = this.f1799g - y8;
                    if (!this.f1803k && Math.abs(i10) > this.f1807o) {
                        ViewParent parent2 = getParent();
                        if (parent2 != null) {
                            parent2.requestDisallowInterceptTouchEvent(true);
                        }
                        this.f1803k = true;
                        i10 = i10 > 0 ? i10 - this.f1807o : i10 + this.f1807o;
                    }
                    int i11 = i10;
                    if (this.f1803k) {
                        if (m1533f(0, i11, this.f1812t, this.f1811s, 0)) {
                            i11 -= this.f1812t[1];
                            this.f1813u += this.f1811s[1];
                        }
                        int i12 = i11;
                        this.f1799g = y8 - this.f1811s[1];
                        int scrollY = getScrollY();
                        int scrollRange = getScrollRange();
                        int overScrollMode = getOverScrollMode();
                        boolean z8 = overScrollMode == 0 || (overScrollMode == 1 && scrollRange > 0);
                        if (m1551z(0, i12, 0, getScrollY(), 0, scrollRange, 0, 0, true) && !m1542p(0)) {
                            this.f1804l.clear();
                        }
                        int scrollY2 = getScrollY() - scrollY;
                        int[] iArr = this.f1812t;
                        iArr[1] = 0;
                        m1534g(0, scrollY2, 0, i12 - scrollY2, this.f1811s, 0, iArr);
                        int i13 = this.f1799g;
                        int i14 = this.f1811s[1];
                        this.f1799g = i13 - i14;
                        this.f1813u += i14;
                        if (z8) {
                            int i15 = i12 - this.f1812t[1];
                            m1537k();
                            int i16 = scrollY + i15;
                            if (i16 < 0) {
                                C0329d.m1593a(this.f1797e, i15 / getHeight(), motionEvent.getX(iFindPointerIndex) / getWidth());
                                if (!this.f1798f.isFinished()) {
                                    this.f1798f.onRelease();
                                }
                            } else if (i16 > scrollRange) {
                                C0329d.m1593a(this.f1798f, i15 / getHeight(), 1.0f - (motionEvent.getX(iFindPointerIndex) / getWidth()));
                                if (!this.f1797e.isFinished()) {
                                    this.f1797e.onRelease();
                                }
                            }
                            EdgeEffect edgeEffect = this.f1797e;
                            if (edgeEffect != null && (!edgeEffect.isFinished() || !this.f1798f.isFinished())) {
                                C4647u.m18524T(this);
                            }
                        }
                    }
                }
            } else if (actionMasked == 3) {
                if (this.f1803k && getChildCount() > 0 && this.f1796d.springBack(getScrollX(), getScrollY(), 0, 0, 0, getScrollRange())) {
                    C4647u.m18524T(this);
                }
                this.f1810r = -1;
                m1536i();
            } else if (actionMasked == 5) {
                int actionIndex = motionEvent.getActionIndex();
                this.f1799g = (int) motionEvent.getY(actionIndex);
                this.f1810r = motionEvent.getPointerId(actionIndex);
            } else if (actionMasked == 6) {
                m1550y(motionEvent);
                this.f1799g = (int) motionEvent.getY(motionEvent.findPointerIndex(this.f1810r));
            }
        } else {
            if (getChildCount() == 0) {
                return false;
            }
            boolean z9 = !this.f1796d.isFinished();
            this.f1803k = z9;
            if (z9 && (parent = getParent()) != null) {
                parent.requestDisallowInterceptTouchEvent(true);
            }
            if (!this.f1796d.isFinished()) {
                m1529a();
            }
            this.f1799g = (int) motionEvent.getY();
            this.f1810r = motionEvent.getPointerId(0);
            m1528K(2, 0);
        }
        VelocityTracker velocityTracker2 = this.f1804l;
        if (velocityTracker2 != null) {
            velocityTracker2.addMovement(motionEventObtain);
        }
        motionEventObtain.recycle();
        return true;
    }

    /* renamed from: p */
    public boolean m1542p(int i9) {
        return this.f1817y.m18485l(i9);
    }

    /* renamed from: q */
    public final boolean m1543q(int i9, int i10) {
        if (getChildCount() <= 0) {
            return false;
        }
        int scrollY = getScrollY();
        View childAt = getChildAt(0);
        return i10 >= childAt.getTop() - scrollY && i10 < childAt.getBottom() - scrollY && i9 >= childAt.getLeft() && i9 < childAt.getRight();
    }

    /* renamed from: r */
    public final void m1544r() {
        VelocityTracker velocityTracker = this.f1804l;
        if (velocityTracker == null) {
            this.f1804l = VelocityTracker.obtain();
        } else {
            velocityTracker.clear();
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void requestChildFocus(View view, View view2) {
        if (this.f1800h) {
            this.f1802j = view2;
        } else {
            m1522E(view2);
        }
        super.requestChildFocus(view, view2);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public boolean requestChildRectangleOnScreen(View view, Rect rect, boolean z8) {
        rect.offset(view.getLeft() - view.getScrollX(), view.getTop() - view.getScrollY());
        return m1523F(rect, z8);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void requestDisallowInterceptTouchEvent(boolean z8) {
        if (z8) {
            m1519B();
        }
        super.requestDisallowInterceptTouchEvent(z8);
    }

    @Override // android.view.View, android.view.ViewParent
    public void requestLayout() {
        this.f1800h = true;
        super.requestLayout();
    }

    /* renamed from: s */
    public final void m1545s() {
        this.f1796d = new OverScroller(getContext());
        setFocusable(true);
        setDescendantFocusability(262144);
        setWillNotDraw(false);
        ViewConfiguration viewConfiguration = ViewConfiguration.get(getContext());
        this.f1807o = viewConfiguration.getScaledTouchSlop();
        this.f1808p = viewConfiguration.getScaledMinimumFlingVelocity();
        this.f1809q = viewConfiguration.getScaledMaximumFlingVelocity();
    }

    @Override // android.view.View
    public void scrollTo(int i9, int i10) {
        if (getChildCount() > 0) {
            View childAt = getChildAt(0);
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) childAt.getLayoutParams();
            int width = (getWidth() - getPaddingLeft()) - getPaddingRight();
            int width2 = childAt.getWidth() + layoutParams.leftMargin + layoutParams.rightMargin;
            int height = (getHeight() - getPaddingTop()) - getPaddingBottom();
            int height2 = childAt.getHeight() + layoutParams.topMargin + layoutParams.bottomMargin;
            int iM1516d = m1516d(i9, width, width2);
            int iM1516d2 = m1516d(i10, height, height2);
            if (iM1516d == getScrollX() && iM1516d2 == getScrollY()) {
                return;
            }
            super.scrollTo(iM1516d, iM1516d2);
        }
    }

    public void setFillViewport(boolean z8) {
        if (z8 != this.f1805m) {
            this.f1805m = z8;
            requestLayout();
        }
    }

    @Override // android.view.View
    public void setNestedScrollingEnabled(boolean z8) {
        this.f1817y.m18487n(z8);
    }

    public void setOnScrollChangeListener(InterfaceC0325b interfaceC0325b) {
        this.f1793A = interfaceC0325b;
    }

    public void setSmoothScrollingEnabled(boolean z8) {
        this.f1806n = z8;
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup
    public boolean shouldDelayChildPressedState() {
        return true;
    }

    @Override // android.view.View
    public boolean startNestedScroll(int i9) {
        return m1528K(i9, 0);
    }

    @Override // p042d0.InterfaceC4633k
    public void stopNestedScroll(int i9) {
        this.f1817y.m18492s(i9);
    }

    /* renamed from: t */
    public final void m1546t() {
        if (this.f1804l == null) {
            this.f1804l = VelocityTracker.obtain();
        }
    }

    /* renamed from: u */
    public final boolean m1547u(View view) {
        return !m1548w(view, 0, getHeight());
    }

    /* renamed from: w */
    public final boolean m1548w(View view, int i9, int i10) {
        view.getDrawingRect(this.f1795c);
        offsetDescendantRectToMyCoords(view, this.f1795c);
        return this.f1795c.bottom + i9 >= getScrollY() && this.f1795c.top - i9 <= getScrollY() + i10;
    }

    /* renamed from: x */
    public final void m1549x(int i9, int i10, int[] iArr) {
        int scrollY = getScrollY();
        scrollBy(0, i9);
        int scrollY2 = getScrollY() - scrollY;
        if (iArr != null) {
            iArr[1] = iArr[1] + scrollY2;
        }
        this.f1817y.m18478e(0, scrollY2, 0, i9 - scrollY2, null, i10, iArr);
    }

    /* renamed from: y */
    public final void m1550y(MotionEvent motionEvent) {
        int actionIndex = motionEvent.getActionIndex();
        if (motionEvent.getPointerId(actionIndex) == this.f1810r) {
            int i9 = actionIndex == 0 ? 1 : 0;
            this.f1799g = (int) motionEvent.getY(i9);
            this.f1810r = motionEvent.getPointerId(i9);
            VelocityTracker velocityTracker = this.f1804l;
            if (velocityTracker != null) {
                velocityTracker.clear();
            }
        }
    }

    /* renamed from: z */
    public boolean m1551z(int i9, int i10, int i11, int i12, int i13, int i14, int i15, int i16, boolean z8) {
        boolean z9;
        boolean z10;
        int overScrollMode = getOverScrollMode();
        boolean z11 = computeHorizontalScrollRange() > computeHorizontalScrollExtent();
        boolean z12 = computeVerticalScrollRange() > computeVerticalScrollExtent();
        boolean z13 = overScrollMode == 0 || (overScrollMode == 1 && z11);
        boolean z14 = overScrollMode == 0 || (overScrollMode == 1 && z12);
        int i17 = i11 + i9;
        int i18 = !z13 ? 0 : i15;
        int i19 = i12 + i10;
        int i20 = !z14 ? 0 : i16;
        int i21 = -i18;
        int i22 = i18 + i13;
        int i23 = -i20;
        int i24 = i20 + i14;
        if (i17 > i22) {
            i17 = i22;
            z9 = true;
        } else if (i17 < i21) {
            z9 = true;
            i17 = i21;
        } else {
            z9 = false;
        }
        if (i19 > i24) {
            i19 = i24;
            z10 = true;
        } else if (i19 < i23) {
            z10 = true;
            i19 = i23;
        } else {
            z10 = false;
        }
        if (z10 && !m1542p(1)) {
            this.f1796d.springBack(i17, i19, 0, 0, 0, getScrollRange());
        }
        onOverScrolled(i17, i19, z9, z10);
        return z9 || z10;
    }

    public NestedScrollView(Context context, AttributeSet attributeSet, int i9) {
        super(context, attributeSet, i9);
        this.f1795c = new Rect();
        this.f1800h = true;
        this.f1801i = false;
        this.f1802j = null;
        this.f1803k = false;
        this.f1806n = true;
        this.f1810r = -1;
        this.f1811s = new int[2];
        this.f1812t = new int[2];
        m1545s();
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, f1792C, i9, 0);
        setFillViewport(typedArrayObtainStyledAttributes.getBoolean(0, false));
        typedArrayObtainStyledAttributes.recycle();
        this.f1816x = new C4642p(this);
        this.f1817y = new C4637m(this);
        setNestedScrollingEnabled(true);
        C4647u.m18530Z(this, f1791B);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void onNestedPreScroll(View view, int i9, int i10, int[] iArr) {
        onNestedPreScroll(view, i9, i10, iArr, 0);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void onNestedScroll(View view, int i9, int i10, int i11, int i12) {
        m1549x(i12, 0, null);
    }

    @Override // android.view.View
    public void stopNestedScroll() {
        stopNestedScroll(0);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void onNestedScrollAccepted(View view, View view2, int i9) {
        onNestedScrollAccepted(view, view2, i9, 0);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void onStopNestedScroll(View view) {
        onStopNestedScroll(view, 0);
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int i9) {
        if (getChildCount() <= 0) {
            super.addView(view, i9);
            return;
        }
        throw new IllegalStateException("ScrollView can host only one direct child");
    }

    @Override // android.view.ViewGroup, android.view.ViewManager
    public void addView(View view, ViewGroup.LayoutParams layoutParams) {
        if (getChildCount() <= 0) {
            super.addView(view, layoutParams);
            return;
        }
        throw new IllegalStateException("ScrollView can host only one direct child");
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int i9, ViewGroup.LayoutParams layoutParams) {
        if (getChildCount() <= 0) {
            super.addView(view, i9, layoutParams);
            return;
        }
        throw new IllegalStateException("ScrollView can host only one direct child");
    }
}
