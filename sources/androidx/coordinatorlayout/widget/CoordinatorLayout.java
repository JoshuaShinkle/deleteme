package androidx.coordinatorlayout.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import androidx.customview.view.AbsSavedState;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.ClassUtils;
import p021c0.C0697c;
import p021c0.C0701g;
import p021c0.InterfaceC0699e;
import p042d0.C4621e;
import p042d0.C4628h0;
import p042d0.C4642p;
import p042d0.C4647u;
import p042d0.InterfaceC4639n;
import p042d0.InterfaceC4643q;
import p170q.C6116a;
import p170q.C6117b;
import p170q.C6118c;
import p197t.C6273a;
import p224w.C6494a;

/* loaded from: classes.dex */
public class CoordinatorLayout extends ViewGroup implements InterfaceC4639n {
    static final Class<?>[] CONSTRUCTOR_PARAMS;
    static final int EVENT_NESTED_SCROLL = 1;
    static final int EVENT_PRE_DRAW = 0;
    static final int EVENT_VIEW_REMOVED = 2;
    static final String TAG = "CoordinatorLayout";
    static final Comparator<View> TOP_SORTED_CHILDREN_COMPARATOR;
    private static final int TYPE_ON_INTERCEPT = 0;
    private static final int TYPE_ON_TOUCH = 1;
    static final String WIDGET_PACKAGE_NAME;
    static final ThreadLocal<Map<String, Constructor<AbstractC0304c>>> sConstructors;
    private static final InterfaceC0699e<Rect> sRectPool;
    private InterfaceC4643q mApplyWindowInsetsListener;
    private View mBehaviorTouchView;
    private final C0310a<View> mChildDag;
    private final List<View> mDependencySortedChildren;
    private boolean mDisallowInterceptReset;
    private boolean mDrawStatusBarBackground;
    private boolean mIsAttachedToWindow;
    private int[] mKeylines;
    private C4628h0 mLastInsets;
    private boolean mNeedsPreDrawListener;
    private final C4642p mNestedScrollingParentHelper;
    private View mNestedScrollingTarget;
    ViewGroup.OnHierarchyChangeListener mOnHierarchyChangeListener;
    private ViewTreeObserverOnPreDrawListenerC0308g mOnPreDrawListener;
    private Paint mScrimPaint;
    private Drawable mStatusBarBackground;
    private final List<View> mTempDependenciesList;
    private final int[] mTempIntPair;
    private final List<View> mTempList1;

    /* renamed from: androidx.coordinatorlayout.widget.CoordinatorLayout$a */
    public class C0302a implements InterfaceC4643q {
        public C0302a() {
        }

        @Override // p042d0.InterfaceC4643q
        public C4628h0 onApplyWindowInsets(View view, C4628h0 c4628h0) {
            return CoordinatorLayout.this.setWindowInsets(c4628h0);
        }
    }

    /* renamed from: androidx.coordinatorlayout.widget.CoordinatorLayout$b */
    public interface InterfaceC0303b {
        AbstractC0304c getBehavior();
    }

    /* renamed from: androidx.coordinatorlayout.widget.CoordinatorLayout$c */
    public static abstract class AbstractC0304c<V extends View> {
        public AbstractC0304c() {
        }

        public static Object getTag(View view) {
            return ((C0307f) view.getLayoutParams()).f1738r;
        }

        public static void setTag(View view, Object obj) {
            ((C0307f) view.getLayoutParams()).f1738r = obj;
        }

        public boolean blocksInteractionBelow(CoordinatorLayout coordinatorLayout, V v8) {
            return getScrimOpacity(coordinatorLayout, v8) > BitmapDescriptorFactory.HUE_RED;
        }

        public boolean getInsetDodgeRect(CoordinatorLayout coordinatorLayout, V v8, Rect rect) {
            return false;
        }

        public int getScrimColor(CoordinatorLayout coordinatorLayout, V v8) {
            return -16777216;
        }

        public float getScrimOpacity(CoordinatorLayout coordinatorLayout, V v8) {
            return BitmapDescriptorFactory.HUE_RED;
        }

        public boolean layoutDependsOn(CoordinatorLayout coordinatorLayout, V v8, View view) {
            return false;
        }

        public C4628h0 onApplyWindowInsets(CoordinatorLayout coordinatorLayout, V v8, C4628h0 c4628h0) {
            return c4628h0;
        }

        public void onAttachedToLayoutParams(C0307f c0307f) {
        }

        public boolean onDependentViewChanged(CoordinatorLayout coordinatorLayout, V v8, View view) {
            return false;
        }

        public void onDependentViewRemoved(CoordinatorLayout coordinatorLayout, V v8, View view) {
        }

        public void onDetachedFromLayoutParams() {
        }

        public boolean onInterceptTouchEvent(CoordinatorLayout coordinatorLayout, V v8, MotionEvent motionEvent) {
            return false;
        }

        public boolean onLayoutChild(CoordinatorLayout coordinatorLayout, V v8, int i9) {
            return false;
        }

        public boolean onMeasureChild(CoordinatorLayout coordinatorLayout, V v8, int i9, int i10, int i11, int i12) {
            return false;
        }

        public boolean onNestedFling(CoordinatorLayout coordinatorLayout, V v8, View view, float f9, float f10, boolean z8) {
            return false;
        }

        public boolean onNestedPreFling(CoordinatorLayout coordinatorLayout, V v8, View view, float f9, float f10) {
            return false;
        }

        @Deprecated
        public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, V v8, View view, int i9, int i10, int[] iArr) {
        }

        public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, V v8, View view, int i9, int i10, int[] iArr, int i11) {
            if (i11 == 0) {
                onNestedPreScroll(coordinatorLayout, v8, view, i9, i10, iArr);
            }
        }

        @Deprecated
        public void onNestedScroll(CoordinatorLayout coordinatorLayout, V v8, View view, int i9, int i10, int i11, int i12) {
        }

        public void onNestedScroll(CoordinatorLayout coordinatorLayout, V v8, View view, int i9, int i10, int i11, int i12, int i13) {
            if (i13 == 0) {
                onNestedScroll(coordinatorLayout, v8, view, i9, i10, i11, i12);
            }
        }

        @Deprecated
        public void onNestedScrollAccepted(CoordinatorLayout coordinatorLayout, V v8, View view, View view2, int i9) {
        }

        public void onNestedScrollAccepted(CoordinatorLayout coordinatorLayout, V v8, View view, View view2, int i9, int i10) {
            if (i10 == 0) {
                onNestedScrollAccepted(coordinatorLayout, v8, view, view2, i9);
            }
        }

        public boolean onRequestChildRectangleOnScreen(CoordinatorLayout coordinatorLayout, V v8, Rect rect, boolean z8) {
            return false;
        }

        public void onRestoreInstanceState(CoordinatorLayout coordinatorLayout, V v8, Parcelable parcelable) {
        }

        public Parcelable onSaveInstanceState(CoordinatorLayout coordinatorLayout, V v8) {
            return View.BaseSavedState.EMPTY_STATE;
        }

        @Deprecated
        public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, V v8, View view, View view2, int i9) {
            return false;
        }

        public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, V v8, View view, View view2, int i9, int i10) {
            if (i10 == 0) {
                return onStartNestedScroll(coordinatorLayout, v8, view, view2, i9);
            }
            return false;
        }

        @Deprecated
        public void onStopNestedScroll(CoordinatorLayout coordinatorLayout, V v8, View view) {
        }

        public void onStopNestedScroll(CoordinatorLayout coordinatorLayout, V v8, View view, int i9) {
            if (i9 == 0) {
                onStopNestedScroll(coordinatorLayout, v8, view);
            }
        }

        public boolean onTouchEvent(CoordinatorLayout coordinatorLayout, V v8, MotionEvent motionEvent) {
            return false;
        }

        public AbstractC0304c(Context context, AttributeSet attributeSet) {
        }
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Deprecated
    /* renamed from: androidx.coordinatorlayout.widget.CoordinatorLayout$d */
    public @interface InterfaceC0305d {
        Class<? extends AbstractC0304c> value();
    }

    /* renamed from: androidx.coordinatorlayout.widget.CoordinatorLayout$e */
    public class ViewGroupOnHierarchyChangeListenerC0306e implements ViewGroup.OnHierarchyChangeListener {
        public ViewGroupOnHierarchyChangeListenerC0306e() {
        }

        @Override // android.view.ViewGroup.OnHierarchyChangeListener
        public void onChildViewAdded(View view, View view2) {
            ViewGroup.OnHierarchyChangeListener onHierarchyChangeListener = CoordinatorLayout.this.mOnHierarchyChangeListener;
            if (onHierarchyChangeListener != null) {
                onHierarchyChangeListener.onChildViewAdded(view, view2);
            }
        }

        @Override // android.view.ViewGroup.OnHierarchyChangeListener
        public void onChildViewRemoved(View view, View view2) {
            CoordinatorLayout.this.onChildViewsChanged(2);
            ViewGroup.OnHierarchyChangeListener onHierarchyChangeListener = CoordinatorLayout.this.mOnHierarchyChangeListener;
            if (onHierarchyChangeListener != null) {
                onHierarchyChangeListener.onChildViewRemoved(view, view2);
            }
        }
    }

    /* renamed from: androidx.coordinatorlayout.widget.CoordinatorLayout$g */
    public class ViewTreeObserverOnPreDrawListenerC0308g implements ViewTreeObserver.OnPreDrawListener {
        public ViewTreeObserverOnPreDrawListenerC0308g() {
        }

        @Override // android.view.ViewTreeObserver.OnPreDrawListener
        public boolean onPreDraw() {
            CoordinatorLayout.this.onChildViewsChanged(0);
            return true;
        }
    }

    /* renamed from: androidx.coordinatorlayout.widget.CoordinatorLayout$h */
    public static class C0309h implements Comparator<View> {
        @Override // java.util.Comparator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(View view, View view2) {
            float fM18506B = C4647u.m18506B(view);
            float fM18506B2 = C4647u.m18506B(view2);
            if (fM18506B > fM18506B2) {
                return -1;
            }
            return fM18506B < fM18506B2 ? 1 : 0;
        }
    }

    static {
        Package r02 = CoordinatorLayout.class.getPackage();
        WIDGET_PACKAGE_NAME = r02 != null ? r02.getName() : null;
        TOP_SORTED_CHILDREN_COMPARATOR = new C0309h();
        CONSTRUCTOR_PARAMS = new Class[]{Context.class, AttributeSet.class};
        sConstructors = new ThreadLocal<>();
        sRectPool = new C0701g(12);
    }

    public CoordinatorLayout(Context context) {
        this(context, null);
    }

    private static Rect acquireTempRect() {
        Rect rectMo3465b = sRectPool.mo3465b();
        return rectMo3465b == null ? new Rect() : rectMo3465b;
    }

    private static int clamp(int i9, int i10, int i11) {
        return i9 < i10 ? i10 : i9 > i11 ? i11 : i9;
    }

    private void constrainChildRect(C0307f c0307f, Rect rect, int i9, int i10) {
        int width = getWidth();
        int height = getHeight();
        int iMax = Math.max(getPaddingLeft() + ((ViewGroup.MarginLayoutParams) c0307f).leftMargin, Math.min(rect.left, ((width - getPaddingRight()) - i9) - ((ViewGroup.MarginLayoutParams) c0307f).rightMargin));
        int iMax2 = Math.max(getPaddingTop() + ((ViewGroup.MarginLayoutParams) c0307f).topMargin, Math.min(rect.top, ((height - getPaddingBottom()) - i10) - ((ViewGroup.MarginLayoutParams) c0307f).bottomMargin));
        rect.set(iMax, iMax2, i9 + iMax, i10 + iMax2);
    }

    private C4628h0 dispatchApplyWindowInsetsToBehaviors(C4628h0 c4628h0) {
        AbstractC0304c abstractC0304cM1439f;
        if (c4628h0.m18443l()) {
            return c4628h0;
        }
        int childCount = getChildCount();
        for (int i9 = 0; i9 < childCount; i9++) {
            View childAt = getChildAt(i9);
            if (C4647u.m18561p(childAt) && (abstractC0304cM1439f = ((C0307f) childAt.getLayoutParams()).m1439f()) != null) {
                c4628h0 = abstractC0304cM1439f.onApplyWindowInsets(this, childAt, c4628h0);
                if (c4628h0.m18443l()) {
                    break;
                }
            }
        }
        return c4628h0;
    }

    private void getDesiredAnchoredChildRectWithoutConstraints(View view, int i9, Rect rect, Rect rect2, C0307f c0307f, int i10, int i11) {
        int iM18420b = C4621e.m18420b(resolveAnchoredChildGravity(c0307f.f1723c), i9);
        int iM18420b2 = C4621e.m18420b(resolveGravity(c0307f.f1724d), i9);
        int i12 = iM18420b & 7;
        int i13 = iM18420b & 112;
        int i14 = iM18420b2 & 7;
        int i15 = iM18420b2 & 112;
        int iWidth = i14 != 1 ? i14 != 5 ? rect.left : rect.right : rect.left + (rect.width() / 2);
        int iHeight = i15 != 16 ? i15 != 80 ? rect.top : rect.bottom : rect.top + (rect.height() / 2);
        if (i12 == 1) {
            iWidth -= i10 / 2;
        } else if (i12 != 5) {
            iWidth -= i10;
        }
        if (i13 == 16) {
            iHeight -= i11 / 2;
        } else if (i13 != 80) {
            iHeight -= i11;
        }
        rect2.set(iWidth, iHeight, i10 + iWidth, i11 + iHeight);
    }

    private int getKeyline(int i9) {
        int[] iArr = this.mKeylines;
        if (iArr == null) {
            Log.e(TAG, "No keylines defined for " + this + " - attempted index lookup " + i9);
            return 0;
        }
        if (i9 >= 0 && i9 < iArr.length) {
            return iArr[i9];
        }
        Log.e(TAG, "Keyline index " + i9 + " out of range for " + this);
        return 0;
    }

    private void getTopSortedChildren(List<View> list) {
        list.clear();
        boolean zIsChildrenDrawingOrderEnabled = isChildrenDrawingOrderEnabled();
        int childCount = getChildCount();
        for (int i9 = childCount - 1; i9 >= 0; i9--) {
            list.add(getChildAt(zIsChildrenDrawingOrderEnabled ? getChildDrawingOrder(childCount, i9) : i9));
        }
        Comparator<View> comparator = TOP_SORTED_CHILDREN_COMPARATOR;
        if (comparator != null) {
            Collections.sort(list, comparator);
        }
    }

    private boolean hasDependencies(View view) {
        return this.mChildDag.m1464j(view);
    }

    private void layoutChild(View view, int i9) {
        C0307f c0307f = (C0307f) view.getLayoutParams();
        Rect rectAcquireTempRect = acquireTempRect();
        rectAcquireTempRect.set(getPaddingLeft() + ((ViewGroup.MarginLayoutParams) c0307f).leftMargin, getPaddingTop() + ((ViewGroup.MarginLayoutParams) c0307f).topMargin, (getWidth() - getPaddingRight()) - ((ViewGroup.MarginLayoutParams) c0307f).rightMargin, (getHeight() - getPaddingBottom()) - ((ViewGroup.MarginLayoutParams) c0307f).bottomMargin);
        if (this.mLastInsets != null && C4647u.m18561p(this) && !C4647u.m18561p(view)) {
            rectAcquireTempRect.left += this.mLastInsets.m18437e();
            rectAcquireTempRect.top += this.mLastInsets.m18439g();
            rectAcquireTempRect.right -= this.mLastInsets.m18438f();
            rectAcquireTempRect.bottom -= this.mLastInsets.m18436d();
        }
        Rect rectAcquireTempRect2 = acquireTempRect();
        C4621e.m18419a(resolveGravity(c0307f.f1723c), view.getMeasuredWidth(), view.getMeasuredHeight(), rectAcquireTempRect, rectAcquireTempRect2, i9);
        view.layout(rectAcquireTempRect2.left, rectAcquireTempRect2.top, rectAcquireTempRect2.right, rectAcquireTempRect2.bottom);
        releaseTempRect(rectAcquireTempRect);
        releaseTempRect(rectAcquireTempRect2);
    }

    private void layoutChildWithAnchor(View view, View view2, int i9) {
        Rect rectAcquireTempRect = acquireTempRect();
        Rect rectAcquireTempRect2 = acquireTempRect();
        try {
            getDescendantRect(view2, rectAcquireTempRect);
            getDesiredAnchoredChildRect(view, i9, rectAcquireTempRect, rectAcquireTempRect2);
            view.layout(rectAcquireTempRect2.left, rectAcquireTempRect2.top, rectAcquireTempRect2.right, rectAcquireTempRect2.bottom);
        } finally {
            releaseTempRect(rectAcquireTempRect);
            releaseTempRect(rectAcquireTempRect2);
        }
    }

    private void layoutChildWithKeyline(View view, int i9, int i10) {
        C0307f c0307f = (C0307f) view.getLayoutParams();
        int iM18420b = C4621e.m18420b(resolveKeylineGravity(c0307f.f1723c), i10);
        int i11 = iM18420b & 7;
        int i12 = iM18420b & 112;
        int width = getWidth();
        int height = getHeight();
        int measuredWidth = view.getMeasuredWidth();
        int measuredHeight = view.getMeasuredHeight();
        if (i10 == 1) {
            i9 = width - i9;
        }
        int keyline = getKeyline(i9) - measuredWidth;
        if (i11 == 1) {
            keyline += measuredWidth / 2;
        } else if (i11 == 5) {
            keyline += measuredWidth;
        }
        int i13 = 0;
        if (i12 == 16) {
            i13 = 0 + (measuredHeight / 2);
        } else if (i12 == 80) {
            i13 = measuredHeight + 0;
        }
        int iMax = Math.max(getPaddingLeft() + ((ViewGroup.MarginLayoutParams) c0307f).leftMargin, Math.min(keyline, ((width - getPaddingRight()) - measuredWidth) - ((ViewGroup.MarginLayoutParams) c0307f).rightMargin));
        int iMax2 = Math.max(getPaddingTop() + ((ViewGroup.MarginLayoutParams) c0307f).topMargin, Math.min(i13, ((height - getPaddingBottom()) - measuredHeight) - ((ViewGroup.MarginLayoutParams) c0307f).bottomMargin));
        view.layout(iMax, iMax2, measuredWidth + iMax, measuredHeight + iMax2);
    }

    private void offsetChildByInset(View view, Rect rect, int i9) {
        boolean z8;
        boolean z9;
        int width;
        int i10;
        int i11;
        int i12;
        int height;
        int i13;
        int i14;
        int i15;
        if (C4647u.m18513I(view) && view.getWidth() > 0 && view.getHeight() > 0) {
            C0307f c0307f = (C0307f) view.getLayoutParams();
            AbstractC0304c abstractC0304cM1439f = c0307f.m1439f();
            Rect rectAcquireTempRect = acquireTempRect();
            Rect rectAcquireTempRect2 = acquireTempRect();
            rectAcquireTempRect2.set(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
            if (abstractC0304cM1439f == null || !abstractC0304cM1439f.getInsetDodgeRect(this, view, rectAcquireTempRect)) {
                rectAcquireTempRect.set(rectAcquireTempRect2);
            } else if (!rectAcquireTempRect2.contains(rectAcquireTempRect)) {
                throw new IllegalArgumentException("Rect should be within the child's bounds. Rect:" + rectAcquireTempRect.toShortString() + " | Bounds:" + rectAcquireTempRect2.toShortString());
            }
            releaseTempRect(rectAcquireTempRect2);
            if (rectAcquireTempRect.isEmpty()) {
                releaseTempRect(rectAcquireTempRect);
                return;
            }
            int iM18420b = C4621e.m18420b(c0307f.f1728h, i9);
            boolean z10 = true;
            if ((iM18420b & 48) != 48 || (i14 = (rectAcquireTempRect.top - ((ViewGroup.MarginLayoutParams) c0307f).topMargin) - c0307f.f1730j) >= (i15 = rect.top)) {
                z8 = false;
            } else {
                setInsetOffsetY(view, i15 - i14);
                z8 = true;
            }
            if ((iM18420b & 80) == 80 && (height = ((getHeight() - rectAcquireTempRect.bottom) - ((ViewGroup.MarginLayoutParams) c0307f).bottomMargin) + c0307f.f1730j) < (i13 = rect.bottom)) {
                setInsetOffsetY(view, height - i13);
                z8 = true;
            }
            if (!z8) {
                setInsetOffsetY(view, 0);
            }
            if ((iM18420b & 3) != 3 || (i11 = (rectAcquireTempRect.left - ((ViewGroup.MarginLayoutParams) c0307f).leftMargin) - c0307f.f1729i) >= (i12 = rect.left)) {
                z9 = false;
            } else {
                setInsetOffsetX(view, i12 - i11);
                z9 = true;
            }
            if ((iM18420b & 5) != 5 || (width = ((getWidth() - rectAcquireTempRect.right) - ((ViewGroup.MarginLayoutParams) c0307f).rightMargin) + c0307f.f1729i) >= (i10 = rect.right)) {
                z10 = z9;
            } else {
                setInsetOffsetX(view, width - i10);
            }
            if (!z10) {
                setInsetOffsetX(view, 0);
            }
            releaseTempRect(rectAcquireTempRect);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static AbstractC0304c parseBehavior(Context context, AttributeSet attributeSet, String str) throws NoSuchMethodException, SecurityException {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (str.startsWith(".")) {
            str = context.getPackageName() + str;
        } else if (str.indexOf(46) < 0) {
            String str2 = WIDGET_PACKAGE_NAME;
            if (!TextUtils.isEmpty(str2)) {
                str = str2 + ClassUtils.PACKAGE_SEPARATOR_CHAR + str;
            }
        }
        try {
            ThreadLocal<Map<String, Constructor<AbstractC0304c>>> threadLocal = sConstructors;
            Map map = (Map) threadLocal.get();
            if (map == null) {
                map = new HashMap();
                threadLocal.set(map);
            }
            Constructor<?> constructor = (Constructor) map.get(str);
            if (constructor == null) {
                constructor = context.getClassLoader().loadClass(str).getConstructor(CONSTRUCTOR_PARAMS);
                constructor.setAccessible(true);
                map.put(str, constructor);
            }
            return (AbstractC0304c) constructor.newInstance(context, attributeSet);
        } catch (Exception e9) {
            throw new RuntimeException("Could not inflate Behavior subclass " + str, e9);
        }
    }

    private boolean performIntercept(MotionEvent motionEvent, int i9) {
        int actionMasked = motionEvent.getActionMasked();
        List<View> list = this.mTempList1;
        getTopSortedChildren(list);
        int size = list.size();
        MotionEvent motionEventObtain = null;
        boolean zOnInterceptTouchEvent = false;
        boolean z8 = false;
        for (int i10 = 0; i10 < size; i10++) {
            View view = list.get(i10);
            C0307f c0307f = (C0307f) view.getLayoutParams();
            AbstractC0304c abstractC0304cM1439f = c0307f.m1439f();
            if (!(zOnInterceptTouchEvent || z8) || actionMasked == 0) {
                if (!zOnInterceptTouchEvent && abstractC0304cM1439f != null) {
                    if (i9 == 0) {
                        zOnInterceptTouchEvent = abstractC0304cM1439f.onInterceptTouchEvent(this, view, motionEvent);
                    } else if (i9 == 1) {
                        zOnInterceptTouchEvent = abstractC0304cM1439f.onTouchEvent(this, view, motionEvent);
                    }
                    if (zOnInterceptTouchEvent) {
                        this.mBehaviorTouchView = view;
                    }
                }
                boolean zM1436c = c0307f.m1436c();
                boolean zM1442i = c0307f.m1442i(this, view);
                z8 = zM1442i && !zM1436c;
                if (zM1442i && !z8) {
                    break;
                }
            } else if (abstractC0304cM1439f != null) {
                if (motionEventObtain == null) {
                    long jUptimeMillis = SystemClock.uptimeMillis();
                    motionEventObtain = MotionEvent.obtain(jUptimeMillis, jUptimeMillis, 3, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, 0);
                }
                if (i9 == 0) {
                    abstractC0304cM1439f.onInterceptTouchEvent(this, view, motionEventObtain);
                } else if (i9 == 1) {
                    abstractC0304cM1439f.onTouchEvent(this, view, motionEventObtain);
                }
            }
        }
        list.clear();
        return zOnInterceptTouchEvent;
    }

    private void prepareChildren() {
        this.mDependencySortedChildren.clear();
        this.mChildDag.m1457c();
        int childCount = getChildCount();
        for (int i9 = 0; i9 < childCount; i9++) {
            View childAt = getChildAt(i9);
            C0307f resolvedLayoutParams = getResolvedLayoutParams(childAt);
            resolvedLayoutParams.m1437d(this, childAt);
            this.mChildDag.m1456b(childAt);
            for (int i10 = 0; i10 < childCount; i10++) {
                if (i10 != i9) {
                    View childAt2 = getChildAt(i10);
                    if (resolvedLayoutParams.m1435b(this, childAt, childAt2)) {
                        if (!this.mChildDag.m1458d(childAt2)) {
                            this.mChildDag.m1456b(childAt2);
                        }
                        this.mChildDag.m1455a(childAt2, childAt);
                    }
                }
            }
        }
        this.mDependencySortedChildren.addAll(this.mChildDag.m1463i());
        Collections.reverse(this.mDependencySortedChildren);
    }

    private static void releaseTempRect(Rect rect) {
        rect.setEmpty();
        sRectPool.mo3464a(rect);
    }

    private void resetTouchBehaviors(boolean z8) {
        int childCount = getChildCount();
        for (int i9 = 0; i9 < childCount; i9++) {
            View childAt = getChildAt(i9);
            AbstractC0304c abstractC0304cM1439f = ((C0307f) childAt.getLayoutParams()).m1439f();
            if (abstractC0304cM1439f != null) {
                long jUptimeMillis = SystemClock.uptimeMillis();
                MotionEvent motionEventObtain = MotionEvent.obtain(jUptimeMillis, jUptimeMillis, 3, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, 0);
                if (z8) {
                    abstractC0304cM1439f.onInterceptTouchEvent(this, childAt, motionEventObtain);
                } else {
                    abstractC0304cM1439f.onTouchEvent(this, childAt, motionEventObtain);
                }
                motionEventObtain.recycle();
            }
        }
        for (int i10 = 0; i10 < childCount; i10++) {
            ((C0307f) getChildAt(i10).getLayoutParams()).m1446m();
        }
        this.mBehaviorTouchView = null;
        this.mDisallowInterceptReset = false;
    }

    private static int resolveAnchoredChildGravity(int i9) {
        if (i9 == 0) {
            return 17;
        }
        return i9;
    }

    private static int resolveGravity(int i9) {
        if ((i9 & 7) == 0) {
            i9 |= 8388611;
        }
        return (i9 & 112) == 0 ? i9 | 48 : i9;
    }

    private static int resolveKeylineGravity(int i9) {
        if (i9 == 0) {
            return 8388661;
        }
        return i9;
    }

    private void setInsetOffsetX(View view, int i9) {
        C0307f c0307f = (C0307f) view.getLayoutParams();
        int i10 = c0307f.f1729i;
        if (i10 != i9) {
            C4647u.m18518N(view, i9 - i10);
            c0307f.f1729i = i9;
        }
    }

    private void setInsetOffsetY(View view, int i9) {
        C0307f c0307f = (C0307f) view.getLayoutParams();
        int i10 = c0307f.f1730j;
        if (i10 != i9) {
            C4647u.m18519O(view, i9 - i10);
            c0307f.f1730j = i9;
        }
    }

    private void setupForInsets() {
        if (!C4647u.m18561p(this)) {
            C4647u.m18554l0(this, null);
            return;
        }
        if (this.mApplyWindowInsetsListener == null) {
            this.mApplyWindowInsetsListener = new C0302a();
        }
        C4647u.m18554l0(this, this.mApplyWindowInsetsListener);
        setSystemUiVisibility(1280);
    }

    public void addPreDrawListener() {
        if (this.mIsAttachedToWindow) {
            if (this.mOnPreDrawListener == null) {
                this.mOnPreDrawListener = new ViewTreeObserverOnPreDrawListenerC0308g();
            }
            getViewTreeObserver().addOnPreDrawListener(this.mOnPreDrawListener);
        }
        this.mNeedsPreDrawListener = true;
    }

    @Override // android.view.ViewGroup
    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return (layoutParams instanceof C0307f) && super.checkLayoutParams(layoutParams);
    }

    public void dispatchDependentViewsChanged(View view) {
        List listM1461g = this.mChildDag.m1461g(view);
        if (listM1461g == null || listM1461g.isEmpty()) {
            return;
        }
        for (int i9 = 0; i9 < listM1461g.size(); i9++) {
            View view2 = (View) listM1461g.get(i9);
            AbstractC0304c abstractC0304cM1439f = ((C0307f) view2.getLayoutParams()).m1439f();
            if (abstractC0304cM1439f != null) {
                abstractC0304cM1439f.onDependentViewChanged(this, view2, view);
            }
        }
    }

    public boolean doViewsOverlap(View view, View view2) {
        boolean z8 = false;
        if (view.getVisibility() != 0 || view2.getVisibility() != 0) {
            return false;
        }
        Rect rectAcquireTempRect = acquireTempRect();
        getChildRect(view, view.getParent() != this, rectAcquireTempRect);
        Rect rectAcquireTempRect2 = acquireTempRect();
        getChildRect(view2, view2.getParent() != this, rectAcquireTempRect2);
        try {
            if (rectAcquireTempRect.left <= rectAcquireTempRect2.right && rectAcquireTempRect.top <= rectAcquireTempRect2.bottom && rectAcquireTempRect.right >= rectAcquireTempRect2.left) {
                if (rectAcquireTempRect.bottom >= rectAcquireTempRect2.top) {
                    z8 = true;
                }
            }
            return z8;
        } finally {
            releaseTempRect(rectAcquireTempRect);
            releaseTempRect(rectAcquireTempRect2);
        }
    }

    @Override // android.view.ViewGroup
    public boolean drawChild(Canvas canvas, View view, long j9) {
        C0307f c0307f = (C0307f) view.getLayoutParams();
        AbstractC0304c abstractC0304c = c0307f.f1721a;
        if (abstractC0304c != null) {
            float scrimOpacity = abstractC0304c.getScrimOpacity(this, view);
            if (scrimOpacity > BitmapDescriptorFactory.HUE_RED) {
                if (this.mScrimPaint == null) {
                    this.mScrimPaint = new Paint();
                }
                this.mScrimPaint.setColor(c0307f.f1721a.getScrimColor(this, view));
                this.mScrimPaint.setAlpha(clamp(Math.round(scrimOpacity * 255.0f), 0, 255));
                int iSave = canvas.save();
                if (view.isOpaque()) {
                    canvas.clipRect(view.getLeft(), view.getTop(), view.getRight(), view.getBottom(), Region.Op.DIFFERENCE);
                }
                canvas.drawRect(getPaddingLeft(), getPaddingTop(), getWidth() - getPaddingRight(), getHeight() - getPaddingBottom(), this.mScrimPaint);
                canvas.restoreToCount(iSave);
            }
        }
        return super.drawChild(canvas, view, j9);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        int[] drawableState = getDrawableState();
        Drawable drawable = this.mStatusBarBackground;
        boolean state = false;
        if (drawable != null && drawable.isStateful()) {
            state = false | drawable.setState(drawableState);
        }
        if (state) {
            invalidate();
        }
    }

    public void ensurePreDrawListener() {
        int childCount = getChildCount();
        boolean z8 = false;
        int i9 = 0;
        while (true) {
            if (i9 >= childCount) {
                break;
            }
            if (hasDependencies(getChildAt(i9))) {
                z8 = true;
                break;
            }
            i9++;
        }
        if (z8 != this.mNeedsPreDrawListener) {
            if (z8) {
                addPreDrawListener();
            } else {
                removePreDrawListener();
            }
        }
    }

    public void getChildRect(View view, boolean z8, Rect rect) {
        if (view.isLayoutRequested() || view.getVisibility() == 8) {
            rect.setEmpty();
        } else if (z8) {
            getDescendantRect(view, rect);
        } else {
            rect.set(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
        }
    }

    public List<View> getDependencies(View view) {
        List<View> listM1462h = this.mChildDag.m1462h(view);
        this.mTempDependenciesList.clear();
        if (listM1462h != null) {
            this.mTempDependenciesList.addAll(listM1462h);
        }
        return this.mTempDependenciesList;
    }

    public final List<View> getDependencySortedChildren() {
        prepareChildren();
        return Collections.unmodifiableList(this.mDependencySortedChildren);
    }

    public List<View> getDependents(View view) {
        List listM1461g = this.mChildDag.m1461g(view);
        this.mTempDependenciesList.clear();
        if (listM1461g != null) {
            this.mTempDependenciesList.addAll(listM1461g);
        }
        return this.mTempDependenciesList;
    }

    public void getDescendantRect(View view, Rect rect) {
        C0311b.m1466a(this, view, rect);
    }

    public void getDesiredAnchoredChildRect(View view, int i9, Rect rect, Rect rect2) {
        C0307f c0307f = (C0307f) view.getLayoutParams();
        int measuredWidth = view.getMeasuredWidth();
        int measuredHeight = view.getMeasuredHeight();
        getDesiredAnchoredChildRectWithoutConstraints(view, i9, rect, rect2, c0307f, measuredWidth, measuredHeight);
        constrainChildRect(c0307f, rect2, measuredWidth, measuredHeight);
    }

    public void getLastChildRect(View view, Rect rect) {
        rect.set(((C0307f) view.getLayoutParams()).m1441h());
    }

    public final C4628h0 getLastWindowInsets() {
        return this.mLastInsets;
    }

    @Override // android.view.ViewGroup
    public int getNestedScrollAxes() {
        return this.mNestedScrollingParentHelper.m18495a();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public C0307f getResolvedLayoutParams(View view) {
        C0307f c0307f = (C0307f) view.getLayoutParams();
        if (!c0307f.f1722b) {
            if (view instanceof InterfaceC0303b) {
                AbstractC0304c behavior = ((InterfaceC0303b) view).getBehavior();
                if (behavior == null) {
                    Log.e(TAG, "Attached behavior class is null");
                }
                c0307f.m1448o(behavior);
                c0307f.f1722b = true;
            } else {
                InterfaceC0305d interfaceC0305d = null;
                for (Class<?> superclass = view.getClass(); superclass != null; superclass = superclass.getSuperclass()) {
                    interfaceC0305d = (InterfaceC0305d) superclass.getAnnotation(InterfaceC0305d.class);
                    if (interfaceC0305d != null) {
                        break;
                    }
                }
                if (interfaceC0305d != null) {
                    try {
                        c0307f.m1448o(interfaceC0305d.value().getDeclaredConstructor(new Class[0]).newInstance(new Object[0]));
                    } catch (Exception e9) {
                        Log.e(TAG, "Default behavior class " + interfaceC0305d.value().getName() + " could not be instantiated. Did you forget a default constructor?", e9);
                    }
                }
                c0307f.f1722b = true;
            }
        }
        return c0307f;
    }

    public Drawable getStatusBarBackground() {
        return this.mStatusBarBackground;
    }

    @Override // android.view.View
    public int getSuggestedMinimumHeight() {
        return Math.max(super.getSuggestedMinimumHeight(), getPaddingTop() + getPaddingBottom());
    }

    @Override // android.view.View
    public int getSuggestedMinimumWidth() {
        return Math.max(super.getSuggestedMinimumWidth(), getPaddingLeft() + getPaddingRight());
    }

    public boolean isPointInChildBounds(View view, int i9, int i10) {
        Rect rectAcquireTempRect = acquireTempRect();
        getDescendantRect(view, rectAcquireTempRect);
        try {
            return rectAcquireTempRect.contains(i9, i10);
        } finally {
            releaseTempRect(rectAcquireTempRect);
        }
    }

    public void offsetChildToAnchor(View view, int i9) {
        AbstractC0304c abstractC0304cM1439f;
        C0307f c0307f = (C0307f) view.getLayoutParams();
        if (c0307f.f1731k != null) {
            Rect rectAcquireTempRect = acquireTempRect();
            Rect rectAcquireTempRect2 = acquireTempRect();
            Rect rectAcquireTempRect3 = acquireTempRect();
            getDescendantRect(c0307f.f1731k, rectAcquireTempRect);
            getChildRect(view, false, rectAcquireTempRect2);
            int measuredWidth = view.getMeasuredWidth();
            int measuredHeight = view.getMeasuredHeight();
            getDesiredAnchoredChildRectWithoutConstraints(view, i9, rectAcquireTempRect, rectAcquireTempRect3, c0307f, measuredWidth, measuredHeight);
            boolean z8 = (rectAcquireTempRect3.left == rectAcquireTempRect2.left && rectAcquireTempRect3.top == rectAcquireTempRect2.top) ? false : true;
            constrainChildRect(c0307f, rectAcquireTempRect3, measuredWidth, measuredHeight);
            int i10 = rectAcquireTempRect3.left - rectAcquireTempRect2.left;
            int i11 = rectAcquireTempRect3.top - rectAcquireTempRect2.top;
            if (i10 != 0) {
                C4647u.m18518N(view, i10);
            }
            if (i11 != 0) {
                C4647u.m18519O(view, i11);
            }
            if (z8 && (abstractC0304cM1439f = c0307f.m1439f()) != null) {
                abstractC0304cM1439f.onDependentViewChanged(this, view, c0307f.f1731k);
            }
            releaseTempRect(rectAcquireTempRect);
            releaseTempRect(rectAcquireTempRect2);
            releaseTempRect(rectAcquireTempRect3);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        resetTouchBehaviors(false);
        if (this.mNeedsPreDrawListener) {
            if (this.mOnPreDrawListener == null) {
                this.mOnPreDrawListener = new ViewTreeObserverOnPreDrawListenerC0308g();
            }
            getViewTreeObserver().addOnPreDrawListener(this.mOnPreDrawListener);
        }
        if (this.mLastInsets == null && C4647u.m18561p(this)) {
            C4647u.m18527W(this);
        }
        this.mIsAttachedToWindow = true;
    }

    /* JADX WARN: Removed duplicated region for block: B:46:0x00ca  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void onChildViewsChanged(int i9) {
        int i10;
        boolean zOnDependentViewChanged;
        int iM18567s = C4647u.m18567s(this);
        int size = this.mDependencySortedChildren.size();
        Rect rectAcquireTempRect = acquireTempRect();
        Rect rectAcquireTempRect2 = acquireTempRect();
        Rect rectAcquireTempRect3 = acquireTempRect();
        for (int i11 = 0; i11 < size; i11++) {
            View view = this.mDependencySortedChildren.get(i11);
            C0307f c0307f = (C0307f) view.getLayoutParams();
            if (i9 != 0 || view.getVisibility() != 8) {
                for (int i12 = 0; i12 < i11; i12++) {
                    if (c0307f.f1732l == this.mDependencySortedChildren.get(i12)) {
                        offsetChildToAnchor(view, iM18567s);
                    }
                }
                getChildRect(view, true, rectAcquireTempRect2);
                if (c0307f.f1727g != 0 && !rectAcquireTempRect2.isEmpty()) {
                    int iM18420b = C4621e.m18420b(c0307f.f1727g, iM18567s);
                    int i13 = iM18420b & 112;
                    if (i13 == 48) {
                        rectAcquireTempRect.top = Math.max(rectAcquireTempRect.top, rectAcquireTempRect2.bottom);
                    } else if (i13 == 80) {
                        rectAcquireTempRect.bottom = Math.max(rectAcquireTempRect.bottom, getHeight() - rectAcquireTempRect2.top);
                    }
                    int i14 = iM18420b & 7;
                    if (i14 == 3) {
                        rectAcquireTempRect.left = Math.max(rectAcquireTempRect.left, rectAcquireTempRect2.right);
                    } else if (i14 == 5) {
                        rectAcquireTempRect.right = Math.max(rectAcquireTempRect.right, getWidth() - rectAcquireTempRect2.left);
                    }
                }
                if (c0307f.f1728h != 0 && view.getVisibility() == 0) {
                    offsetChildByInset(view, rectAcquireTempRect, iM18567s);
                }
                if (i9 != 2) {
                    getLastChildRect(view, rectAcquireTempRect3);
                    if (!rectAcquireTempRect3.equals(rectAcquireTempRect2)) {
                        recordLastChildRect(view, rectAcquireTempRect2);
                        for (i10 = i11 + 1; i10 < size; i10++) {
                            View view2 = this.mDependencySortedChildren.get(i10);
                            C0307f c0307f2 = (C0307f) view2.getLayoutParams();
                            AbstractC0304c abstractC0304cM1439f = c0307f2.m1439f();
                            if (abstractC0304cM1439f != null && abstractC0304cM1439f.layoutDependsOn(this, view2, view)) {
                                if (i9 == 0 && c0307f2.m1440g()) {
                                    c0307f2.m1444k();
                                } else {
                                    if (i9 != 2) {
                                        zOnDependentViewChanged = abstractC0304cM1439f.onDependentViewChanged(this, view2, view);
                                    } else {
                                        abstractC0304cM1439f.onDependentViewRemoved(this, view2, view);
                                        zOnDependentViewChanged = true;
                                    }
                                    if (i9 == 1) {
                                        c0307f2.m1449p(zOnDependentViewChanged);
                                    }
                                }
                            }
                        }
                    }
                } else {
                    while (i10 < size) {
                    }
                }
            }
        }
        releaseTempRect(rectAcquireTempRect);
        releaseTempRect(rectAcquireTempRect2);
        releaseTempRect(rectAcquireTempRect3);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        resetTouchBehaviors(false);
        if (this.mNeedsPreDrawListener && this.mOnPreDrawListener != null) {
            getViewTreeObserver().removeOnPreDrawListener(this.mOnPreDrawListener);
        }
        View view = this.mNestedScrollingTarget;
        if (view != null) {
            onStopNestedScroll(view);
        }
        this.mIsAttachedToWindow = false;
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (!this.mDrawStatusBarBackground || this.mStatusBarBackground == null) {
            return;
        }
        C4628h0 c4628h0 = this.mLastInsets;
        int iM18439g = c4628h0 != null ? c4628h0.m18439g() : 0;
        if (iM18439g > 0) {
            this.mStatusBarBackground.setBounds(0, 0, getWidth(), iM18439g);
            this.mStatusBarBackground.draw(canvas);
        }
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            resetTouchBehaviors(true);
        }
        boolean zPerformIntercept = performIntercept(motionEvent, 0);
        if (actionMasked == 1 || actionMasked == 3) {
            resetTouchBehaviors(true);
        }
        return zPerformIntercept;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean z8, int i9, int i10, int i11, int i12) {
        AbstractC0304c abstractC0304cM1439f;
        int iM18567s = C4647u.m18567s(this);
        int size = this.mDependencySortedChildren.size();
        for (int i13 = 0; i13 < size; i13++) {
            View view = this.mDependencySortedChildren.get(i13);
            if (view.getVisibility() != 8 && ((abstractC0304cM1439f = ((C0307f) view.getLayoutParams()).m1439f()) == null || !abstractC0304cM1439f.onLayoutChild(this, view, iM18567s))) {
                onLayoutChild(view, iM18567s);
            }
        }
    }

    public void onLayoutChild(View view, int i9) {
        C0307f c0307f = (C0307f) view.getLayoutParams();
        if (c0307f.m1434a()) {
            throw new IllegalStateException("An anchor may not be changed after CoordinatorLayout measurement begins before layout is complete.");
        }
        View view2 = c0307f.f1731k;
        if (view2 != null) {
            layoutChildWithAnchor(view, view2, i9);
            return;
        }
        int i10 = c0307f.f1725e;
        if (i10 >= 0) {
            layoutChildWithKeyline(view, i10, i9);
        } else {
            layoutChild(view, i9);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:39:0x00f1  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00fb  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x011d  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void onMeasure(int i9, int i10) {
        int i11;
        int iMax;
        int iMakeMeasureSpec;
        int iMakeMeasureSpec2;
        AbstractC0304c abstractC0304cM1439f;
        C0307f c0307f;
        int i12;
        int i13;
        int i14;
        int i15;
        int i16;
        int i17;
        prepareChildren();
        ensurePreDrawListener();
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();
        int paddingBottom = getPaddingBottom();
        int iM18567s = C4647u.m18567s(this);
        boolean z8 = iM18567s == 1;
        int mode = View.MeasureSpec.getMode(i9);
        int size = View.MeasureSpec.getSize(i9);
        int mode2 = View.MeasureSpec.getMode(i10);
        int size2 = View.MeasureSpec.getSize(i10);
        int i18 = paddingLeft + paddingRight;
        int i19 = paddingTop + paddingBottom;
        int suggestedMinimumWidth = getSuggestedMinimumWidth();
        int suggestedMinimumHeight = getSuggestedMinimumHeight();
        boolean z9 = this.mLastInsets != null && C4647u.m18561p(this);
        int size3 = this.mDependencySortedChildren.size();
        int i20 = suggestedMinimumWidth;
        int i21 = suggestedMinimumHeight;
        int iCombineMeasuredStates = 0;
        int i22 = 0;
        while (i22 < size3) {
            View view = this.mDependencySortedChildren.get(i22);
            if (view.getVisibility() == 8) {
                i16 = i22;
                i13 = size3;
                i14 = paddingLeft;
            } else {
                C0307f c0307f2 = (C0307f) view.getLayoutParams();
                int i23 = c0307f2.f1725e;
                if (i23 < 0 || mode == 0) {
                    i11 = iCombineMeasuredStates;
                } else {
                    int keyline = getKeyline(i23);
                    int iM18420b = C4621e.m18420b(resolveKeylineGravity(c0307f2.f1723c), iM18567s) & 7;
                    i11 = iCombineMeasuredStates;
                    if ((iM18420b == 3 && !z8) || (iM18420b == 5 && z8)) {
                        iMax = Math.max(0, (size - paddingRight) - keyline);
                    } else if ((iM18420b == 5 && !z8) || (iM18420b == 3 && z8)) {
                        iMax = Math.max(0, keyline - paddingLeft);
                    }
                    if (z9 || C4647u.m18561p(view)) {
                        iMakeMeasureSpec = i9;
                        iMakeMeasureSpec2 = i10;
                    } else {
                        int iM18437e = this.mLastInsets.m18437e() + this.mLastInsets.m18438f();
                        int iM18439g = this.mLastInsets.m18439g() + this.mLastInsets.m18436d();
                        iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(size - iM18437e, mode);
                        iMakeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(size2 - iM18439g, mode2);
                    }
                    abstractC0304cM1439f = c0307f2.m1439f();
                    if (abstractC0304cM1439f == null) {
                        c0307f = c0307f2;
                        i15 = i11;
                        i16 = i22;
                        i12 = i21;
                        i14 = paddingLeft;
                        i17 = i20;
                        i13 = size3;
                        if (!abstractC0304cM1439f.onMeasureChild(this, view, iMakeMeasureSpec, iMax, iMakeMeasureSpec2, 0)) {
                        }
                        C0307f c0307f3 = c0307f;
                        int iMax2 = Math.max(i17, i18 + view.getMeasuredWidth() + ((ViewGroup.MarginLayoutParams) c0307f3).leftMargin + ((ViewGroup.MarginLayoutParams) c0307f3).rightMargin);
                        int iMax3 = Math.max(i12, i19 + view.getMeasuredHeight() + ((ViewGroup.MarginLayoutParams) c0307f3).topMargin + ((ViewGroup.MarginLayoutParams) c0307f3).bottomMargin);
                        iCombineMeasuredStates = View.combineMeasuredStates(i15, view.getMeasuredState());
                        i20 = iMax2;
                        i21 = iMax3;
                    } else {
                        c0307f = c0307f2;
                        i12 = i21;
                        i13 = size3;
                        i14 = paddingLeft;
                        i15 = i11;
                        i16 = i22;
                        i17 = i20;
                    }
                    onMeasureChild(view, iMakeMeasureSpec, iMax, iMakeMeasureSpec2, 0);
                    C0307f c0307f32 = c0307f;
                    int iMax22 = Math.max(i17, i18 + view.getMeasuredWidth() + ((ViewGroup.MarginLayoutParams) c0307f32).leftMargin + ((ViewGroup.MarginLayoutParams) c0307f32).rightMargin);
                    int iMax32 = Math.max(i12, i19 + view.getMeasuredHeight() + ((ViewGroup.MarginLayoutParams) c0307f32).topMargin + ((ViewGroup.MarginLayoutParams) c0307f32).bottomMargin);
                    iCombineMeasuredStates = View.combineMeasuredStates(i15, view.getMeasuredState());
                    i20 = iMax22;
                    i21 = iMax32;
                }
                iMax = 0;
                if (z9) {
                    iMakeMeasureSpec = i9;
                    iMakeMeasureSpec2 = i10;
                    abstractC0304cM1439f = c0307f2.m1439f();
                    if (abstractC0304cM1439f == null) {
                    }
                    onMeasureChild(view, iMakeMeasureSpec, iMax, iMakeMeasureSpec2, 0);
                    C0307f c0307f322 = c0307f;
                    int iMax222 = Math.max(i17, i18 + view.getMeasuredWidth() + ((ViewGroup.MarginLayoutParams) c0307f322).leftMargin + ((ViewGroup.MarginLayoutParams) c0307f322).rightMargin);
                    int iMax322 = Math.max(i12, i19 + view.getMeasuredHeight() + ((ViewGroup.MarginLayoutParams) c0307f322).topMargin + ((ViewGroup.MarginLayoutParams) c0307f322).bottomMargin);
                    iCombineMeasuredStates = View.combineMeasuredStates(i15, view.getMeasuredState());
                    i20 = iMax222;
                    i21 = iMax322;
                }
            }
            i22 = i16 + 1;
            paddingLeft = i14;
            size3 = i13;
        }
        int i24 = iCombineMeasuredStates;
        setMeasuredDimension(View.resolveSizeAndState(i20, i9, (-16777216) & i24), View.resolveSizeAndState(i21, i10, i24 << 16));
    }

    public void onMeasureChild(View view, int i9, int i10, int i11, int i12) {
        measureChildWithMargins(view, i9, i10, i11, i12);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public boolean onNestedFling(View view, float f9, float f10, boolean z8) {
        AbstractC0304c abstractC0304cM1439f;
        int childCount = getChildCount();
        boolean zOnNestedFling = false;
        for (int i9 = 0; i9 < childCount; i9++) {
            View childAt = getChildAt(i9);
            if (childAt.getVisibility() != 8) {
                C0307f c0307f = (C0307f) childAt.getLayoutParams();
                if (c0307f.m1443j(0) && (abstractC0304cM1439f = c0307f.m1439f()) != null) {
                    zOnNestedFling |= abstractC0304cM1439f.onNestedFling(this, childAt, view, f9, f10, z8);
                }
            }
        }
        if (zOnNestedFling) {
            onChildViewsChanged(1);
        }
        return zOnNestedFling;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public boolean onNestedPreFling(View view, float f9, float f10) {
        AbstractC0304c abstractC0304cM1439f;
        int childCount = getChildCount();
        boolean zOnNestedPreFling = false;
        for (int i9 = 0; i9 < childCount; i9++) {
            View childAt = getChildAt(i9);
            if (childAt.getVisibility() != 8) {
                C0307f c0307f = (C0307f) childAt.getLayoutParams();
                if (c0307f.m1443j(0) && (abstractC0304cM1439f = c0307f.m1439f()) != null) {
                    zOnNestedPreFling |= abstractC0304cM1439f.onNestedPreFling(this, childAt, view, f9, f10);
                }
            }
        }
        return zOnNestedPreFling;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void onNestedPreScroll(View view, int i9, int i10, int[] iArr) {
        onNestedPreScroll(view, i9, i10, iArr, 0);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void onNestedScroll(View view, int i9, int i10, int i11, int i12) {
        onNestedScroll(view, i9, i10, i11, i12, 0);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void onNestedScrollAccepted(View view, View view2, int i9) {
        onNestedScrollAccepted(view, view2, i9, 0);
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        Parcelable parcelable2;
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        SparseArray<Parcelable> sparseArray = savedState.f1718b;
        int childCount = getChildCount();
        for (int i9 = 0; i9 < childCount; i9++) {
            View childAt = getChildAt(i9);
            int id = childAt.getId();
            AbstractC0304c abstractC0304cM1439f = getResolvedLayoutParams(childAt).m1439f();
            if (id != -1 && abstractC0304cM1439f != null && (parcelable2 = sparseArray.get(id)) != null) {
                abstractC0304cM1439f.onRestoreInstanceState(this, childAt, parcelable2);
            }
        }
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        Parcelable parcelableOnSaveInstanceState;
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        SparseArray<Parcelable> sparseArray = new SparseArray<>();
        int childCount = getChildCount();
        for (int i9 = 0; i9 < childCount; i9++) {
            View childAt = getChildAt(i9);
            int id = childAt.getId();
            AbstractC0304c abstractC0304cM1439f = ((C0307f) childAt.getLayoutParams()).m1439f();
            if (id != -1 && abstractC0304cM1439f != null && (parcelableOnSaveInstanceState = abstractC0304cM1439f.onSaveInstanceState(this, childAt)) != null) {
                sparseArray.append(id, parcelableOnSaveInstanceState);
            }
        }
        savedState.f1718b = sparseArray;
        return savedState;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public boolean onStartNestedScroll(View view, View view2, int i9) {
        return onStartNestedScroll(view, view2, i9, 0);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void onStopNestedScroll(View view) {
        onStopNestedScroll(view, 0);
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x002b A[PHI: r3
      0x002b: PHI (r3v4 boolean) = (r3v2 boolean), (r3v5 boolean) binds: [B:9:0x0022, B:5:0x0012] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0031  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0037  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x004c  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0054  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public boolean onTouchEvent(MotionEvent motionEvent) {
        boolean zPerformIntercept;
        boolean zOnTouchEvent;
        MotionEvent motionEventObtain;
        int actionMasked = motionEvent.getActionMasked();
        if (this.mBehaviorTouchView == null) {
            zPerformIntercept = performIntercept(motionEvent, 1);
            if (!zPerformIntercept) {
                zOnTouchEvent = false;
            }
            motionEventObtain = null;
            if (this.mBehaviorTouchView != null) {
                zOnTouchEvent |= super.onTouchEvent(motionEvent);
            } else if (zPerformIntercept) {
                long jUptimeMillis = SystemClock.uptimeMillis();
                motionEventObtain = MotionEvent.obtain(jUptimeMillis, jUptimeMillis, 3, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, 0);
                super.onTouchEvent(motionEventObtain);
            }
            if (motionEventObtain != null) {
                motionEventObtain.recycle();
            }
            if (actionMasked != 1 || actionMasked == 3) {
                resetTouchBehaviors(false);
            }
            return zOnTouchEvent;
        }
        zPerformIntercept = false;
        AbstractC0304c abstractC0304cM1439f = ((C0307f) this.mBehaviorTouchView.getLayoutParams()).m1439f();
        if (abstractC0304cM1439f != null) {
            zOnTouchEvent = abstractC0304cM1439f.onTouchEvent(this, this.mBehaviorTouchView, motionEvent);
        }
        motionEventObtain = null;
        if (this.mBehaviorTouchView != null) {
        }
        if (motionEventObtain != null) {
        }
        if (actionMasked != 1) {
            resetTouchBehaviors(false);
        }
        return zOnTouchEvent;
    }

    public void recordLastChildRect(View view, Rect rect) {
        ((C0307f) view.getLayoutParams()).m1450q(rect);
    }

    public void removePreDrawListener() {
        if (this.mIsAttachedToWindow && this.mOnPreDrawListener != null) {
            getViewTreeObserver().removeOnPreDrawListener(this.mOnPreDrawListener);
        }
        this.mNeedsPreDrawListener = false;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public boolean requestChildRectangleOnScreen(View view, Rect rect, boolean z8) {
        AbstractC0304c abstractC0304cM1439f = ((C0307f) view.getLayoutParams()).m1439f();
        if (abstractC0304cM1439f == null || !abstractC0304cM1439f.onRequestChildRectangleOnScreen(this, view, rect, z8)) {
            return super.requestChildRectangleOnScreen(view, rect, z8);
        }
        return true;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void requestDisallowInterceptTouchEvent(boolean z8) {
        super.requestDisallowInterceptTouchEvent(z8);
        if (!z8 || this.mDisallowInterceptReset) {
            return;
        }
        resetTouchBehaviors(false);
        this.mDisallowInterceptReset = true;
    }

    @Override // android.view.View
    public void setFitsSystemWindows(boolean z8) {
        super.setFitsSystemWindows(z8);
        setupForInsets();
    }

    @Override // android.view.ViewGroup
    public void setOnHierarchyChangeListener(ViewGroup.OnHierarchyChangeListener onHierarchyChangeListener) {
        this.mOnHierarchyChangeListener = onHierarchyChangeListener;
    }

    public void setStatusBarBackground(Drawable drawable) {
        Drawable drawable2 = this.mStatusBarBackground;
        if (drawable2 != drawable) {
            if (drawable2 != null) {
                drawable2.setCallback(null);
            }
            Drawable drawableMutate = drawable != null ? drawable.mutate() : null;
            this.mStatusBarBackground = drawableMutate;
            if (drawableMutate != null) {
                if (drawableMutate.isStateful()) {
                    this.mStatusBarBackground.setState(getDrawableState());
                }
                C6494a.m24844g(this.mStatusBarBackground, C4647u.m18567s(this));
                this.mStatusBarBackground.setVisible(getVisibility() == 0, false);
                this.mStatusBarBackground.setCallback(this);
            }
            C4647u.m18524T(this);
        }
    }

    public void setStatusBarBackgroundColor(int i9) {
        setStatusBarBackground(new ColorDrawable(i9));
    }

    public void setStatusBarBackgroundResource(int i9) {
        setStatusBarBackground(i9 != 0 ? C6273a.m24025d(getContext(), i9) : null);
    }

    @Override // android.view.View
    public void setVisibility(int i9) {
        super.setVisibility(i9);
        boolean z8 = i9 == 0;
        Drawable drawable = this.mStatusBarBackground;
        if (drawable == null || drawable.isVisible() == z8) {
            return;
        }
        this.mStatusBarBackground.setVisible(z8, false);
    }

    public final C4628h0 setWindowInsets(C4628h0 c4628h0) {
        if (C0697c.m3461a(this.mLastInsets, c4628h0)) {
            return c4628h0;
        }
        this.mLastInsets = c4628h0;
        boolean z8 = c4628h0 != null && c4628h0.m18439g() > 0;
        this.mDrawStatusBarBackground = z8;
        setWillNotDraw(!z8 && getBackground() == null);
        C4628h0 c4628h0DispatchApplyWindowInsetsToBehaviors = dispatchApplyWindowInsetsToBehaviors(c4628h0);
        requestLayout();
        return c4628h0DispatchApplyWindowInsetsToBehaviors;
    }

    @Override // android.view.View
    public boolean verifyDrawable(Drawable drawable) {
        return super.verifyDrawable(drawable) || drawable == this.mStatusBarBackground;
    }

    public CoordinatorLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, C6116a.coordinatorLayoutStyle);
    }

    @Override // android.view.ViewGroup
    public C0307f generateDefaultLayoutParams() {
        return new C0307f(-2, -2);
    }

    @Override // p042d0.InterfaceC4639n
    public void onNestedPreScroll(View view, int i9, int i10, int[] iArr, int i11) {
        AbstractC0304c abstractC0304cM1439f;
        int childCount = getChildCount();
        boolean z8 = false;
        int iMax = 0;
        int iMax2 = 0;
        for (int i12 = 0; i12 < childCount; i12++) {
            View childAt = getChildAt(i12);
            if (childAt.getVisibility() != 8) {
                C0307f c0307f = (C0307f) childAt.getLayoutParams();
                if (c0307f.m1443j(i11) && (abstractC0304cM1439f = c0307f.m1439f()) != null) {
                    int[] iArr2 = this.mTempIntPair;
                    iArr2[1] = 0;
                    iArr2[0] = 0;
                    abstractC0304cM1439f.onNestedPreScroll(this, childAt, view, i9, i10, iArr2, i11);
                    int[] iArr3 = this.mTempIntPair;
                    iMax = i9 > 0 ? Math.max(iMax, iArr3[0]) : Math.min(iMax, iArr3[0]);
                    int[] iArr4 = this.mTempIntPair;
                    iMax2 = i10 > 0 ? Math.max(iMax2, iArr4[1]) : Math.min(iMax2, iArr4[1]);
                    z8 = true;
                }
            }
        }
        iArr[0] = iMax;
        iArr[1] = iMax2;
        if (z8) {
            onChildViewsChanged(1);
        }
    }

    @Override // p042d0.InterfaceC4639n
    public void onNestedScroll(View view, int i9, int i10, int i11, int i12, int i13) {
        AbstractC0304c abstractC0304cM1439f;
        int childCount = getChildCount();
        boolean z8 = false;
        for (int i14 = 0; i14 < childCount; i14++) {
            View childAt = getChildAt(i14);
            if (childAt.getVisibility() != 8) {
                C0307f c0307f = (C0307f) childAt.getLayoutParams();
                if (c0307f.m1443j(i13) && (abstractC0304cM1439f = c0307f.m1439f()) != null) {
                    abstractC0304cM1439f.onNestedScroll(this, childAt, view, i9, i10, i11, i12, i13);
                    z8 = true;
                }
            }
        }
        if (z8) {
            onChildViewsChanged(1);
        }
    }

    @Override // p042d0.InterfaceC4639n
    public void onNestedScrollAccepted(View view, View view2, int i9, int i10) {
        AbstractC0304c abstractC0304cM1439f;
        this.mNestedScrollingParentHelper.m18497c(view, view2, i9, i10);
        this.mNestedScrollingTarget = view2;
        int childCount = getChildCount();
        for (int i11 = 0; i11 < childCount; i11++) {
            View childAt = getChildAt(i11);
            C0307f c0307f = (C0307f) childAt.getLayoutParams();
            if (c0307f.m1443j(i10) && (abstractC0304cM1439f = c0307f.m1439f()) != null) {
                abstractC0304cM1439f.onNestedScrollAccepted(this, childAt, view, view2, i9, i10);
            }
        }
    }

    @Override // p042d0.InterfaceC4639n
    public boolean onStartNestedScroll(View view, View view2, int i9, int i10) {
        int childCount = getChildCount();
        boolean z8 = false;
        for (int i11 = 0; i11 < childCount; i11++) {
            View childAt = getChildAt(i11);
            if (childAt.getVisibility() != 8) {
                C0307f c0307f = (C0307f) childAt.getLayoutParams();
                AbstractC0304c abstractC0304cM1439f = c0307f.m1439f();
                if (abstractC0304cM1439f != null) {
                    boolean zOnStartNestedScroll = abstractC0304cM1439f.onStartNestedScroll(this, childAt, view, view2, i9, i10);
                    z8 |= zOnStartNestedScroll;
                    c0307f.m1451r(i10, zOnStartNestedScroll);
                } else {
                    c0307f.m1451r(i10, false);
                }
            }
        }
        return z8;
    }

    @Override // p042d0.InterfaceC4639n
    public void onStopNestedScroll(View view, int i9) {
        this.mNestedScrollingParentHelper.m18499e(view, i9);
        int childCount = getChildCount();
        for (int i10 = 0; i10 < childCount; i10++) {
            View childAt = getChildAt(i10);
            C0307f c0307f = (C0307f) childAt.getLayoutParams();
            if (c0307f.m1443j(i9)) {
                AbstractC0304c abstractC0304cM1439f = c0307f.m1439f();
                if (abstractC0304cM1439f != null) {
                    abstractC0304cM1439f.onStopNestedScroll(this, childAt, view, i9);
                }
                c0307f.m1445l(i9);
                c0307f.m1444k();
            }
        }
        this.mNestedScrollingTarget = null;
    }

    public CoordinatorLayout(Context context, AttributeSet attributeSet, int i9) {
        TypedArray typedArrayObtainStyledAttributes;
        super(context, attributeSet, i9);
        this.mDependencySortedChildren = new ArrayList();
        this.mChildDag = new C0310a<>();
        this.mTempList1 = new ArrayList();
        this.mTempDependenciesList = new ArrayList();
        this.mTempIntPair = new int[2];
        this.mNestedScrollingParentHelper = new C4642p(this);
        if (i9 == 0) {
            typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C6118c.CoordinatorLayout, 0, C6117b.Widget_Support_CoordinatorLayout);
        } else {
            typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C6118c.CoordinatorLayout, i9, 0);
        }
        int resourceId = typedArrayObtainStyledAttributes.getResourceId(C6118c.CoordinatorLayout_keylines, 0);
        if (resourceId != 0) {
            Resources resources = context.getResources();
            this.mKeylines = resources.getIntArray(resourceId);
            float f9 = resources.getDisplayMetrics().density;
            int length = this.mKeylines.length;
            for (int i10 = 0; i10 < length; i10++) {
                this.mKeylines[i10] = (int) (r1[i10] * f9);
            }
        }
        this.mStatusBarBackground = typedArrayObtainStyledAttributes.getDrawable(C6118c.CoordinatorLayout_statusBarBackground);
        typedArrayObtainStyledAttributes.recycle();
        setupForInsets();
        super.setOnHierarchyChangeListener(new ViewGroupOnHierarchyChangeListenerC0306e());
    }

    @Override // android.view.ViewGroup
    public C0307f generateLayoutParams(AttributeSet attributeSet) {
        return new C0307f(getContext(), attributeSet);
    }

    @Override // android.view.ViewGroup
    public C0307f generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        if (layoutParams instanceof C0307f) {
            return new C0307f((C0307f) layoutParams);
        }
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            return new C0307f((ViewGroup.MarginLayoutParams) layoutParams);
        }
        return new C0307f(layoutParams);
    }

    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new C0301a();

        /* renamed from: b */
        public SparseArray<Parcelable> f1718b;

        /* renamed from: androidx.coordinatorlayout.widget.CoordinatorLayout$SavedState$a */
        public static class C0301a implements Parcelable.ClassLoaderCreator<SavedState> {
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
            int i9 = parcel.readInt();
            int[] iArr = new int[i9];
            parcel.readIntArray(iArr);
            Parcelable[] parcelableArray = parcel.readParcelableArray(classLoader);
            this.f1718b = new SparseArray<>(i9);
            for (int i10 = 0; i10 < i9; i10++) {
                this.f1718b.append(iArr[i10], parcelableArray[i10]);
            }
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i9) {
            super.writeToParcel(parcel, i9);
            SparseArray<Parcelable> sparseArray = this.f1718b;
            int size = sparseArray != null ? sparseArray.size() : 0;
            parcel.writeInt(size);
            int[] iArr = new int[size];
            Parcelable[] parcelableArr = new Parcelable[size];
            for (int i10 = 0; i10 < size; i10++) {
                iArr[i10] = this.f1718b.keyAt(i10);
                parcelableArr[i10] = this.f1718b.valueAt(i10);
            }
            parcel.writeIntArray(iArr);
            parcel.writeParcelableArray(parcelableArr, i9);
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }
    }

    /* renamed from: androidx.coordinatorlayout.widget.CoordinatorLayout$f */
    public static class C0307f extends ViewGroup.MarginLayoutParams {

        /* renamed from: a */
        public AbstractC0304c f1721a;

        /* renamed from: b */
        public boolean f1722b;

        /* renamed from: c */
        public int f1723c;

        /* renamed from: d */
        public int f1724d;

        /* renamed from: e */
        public int f1725e;

        /* renamed from: f */
        public int f1726f;

        /* renamed from: g */
        public int f1727g;

        /* renamed from: h */
        public int f1728h;

        /* renamed from: i */
        public int f1729i;

        /* renamed from: j */
        public int f1730j;

        /* renamed from: k */
        public View f1731k;

        /* renamed from: l */
        public View f1732l;

        /* renamed from: m */
        public boolean f1733m;

        /* renamed from: n */
        public boolean f1734n;

        /* renamed from: o */
        public boolean f1735o;

        /* renamed from: p */
        public boolean f1736p;

        /* renamed from: q */
        public final Rect f1737q;

        /* renamed from: r */
        public Object f1738r;

        public C0307f(int i9, int i10) {
            super(i9, i10);
            this.f1722b = false;
            this.f1723c = 0;
            this.f1724d = 0;
            this.f1725e = -1;
            this.f1726f = -1;
            this.f1727g = 0;
            this.f1728h = 0;
            this.f1737q = new Rect();
        }

        /* renamed from: a */
        public boolean m1434a() {
            return this.f1731k == null && this.f1726f != -1;
        }

        /* renamed from: b */
        public boolean m1435b(CoordinatorLayout coordinatorLayout, View view, View view2) {
            AbstractC0304c abstractC0304c;
            return view2 == this.f1732l || m1452s(view2, C4647u.m18567s(coordinatorLayout)) || ((abstractC0304c = this.f1721a) != null && abstractC0304c.layoutDependsOn(coordinatorLayout, view, view2));
        }

        /* renamed from: c */
        public boolean m1436c() {
            if (this.f1721a == null) {
                this.f1733m = false;
            }
            return this.f1733m;
        }

        /* renamed from: d */
        public View m1437d(CoordinatorLayout coordinatorLayout, View view) {
            if (this.f1726f == -1) {
                this.f1732l = null;
                this.f1731k = null;
                return null;
            }
            if (this.f1731k == null || !m1453t(view, coordinatorLayout)) {
                m1447n(view, coordinatorLayout);
            }
            return this.f1731k;
        }

        /* renamed from: e */
        public int m1438e() {
            return this.f1726f;
        }

        /* renamed from: f */
        public AbstractC0304c m1439f() {
            return this.f1721a;
        }

        /* renamed from: g */
        public boolean m1440g() {
            return this.f1736p;
        }

        /* renamed from: h */
        public Rect m1441h() {
            return this.f1737q;
        }

        /* renamed from: i */
        public boolean m1442i(CoordinatorLayout coordinatorLayout, View view) {
            boolean z8 = this.f1733m;
            if (z8) {
                return true;
            }
            AbstractC0304c abstractC0304c = this.f1721a;
            boolean zBlocksInteractionBelow = (abstractC0304c != null ? abstractC0304c.blocksInteractionBelow(coordinatorLayout, view) : false) | z8;
            this.f1733m = zBlocksInteractionBelow;
            return zBlocksInteractionBelow;
        }

        /* renamed from: j */
        public boolean m1443j(int i9) {
            if (i9 == 0) {
                return this.f1734n;
            }
            if (i9 != 1) {
                return false;
            }
            return this.f1735o;
        }

        /* renamed from: k */
        public void m1444k() {
            this.f1736p = false;
        }

        /* renamed from: l */
        public void m1445l(int i9) {
            m1451r(i9, false);
        }

        /* renamed from: m */
        public void m1446m() {
            this.f1733m = false;
        }

        /* renamed from: n */
        public final void m1447n(View view, CoordinatorLayout coordinatorLayout) {
            View viewFindViewById = coordinatorLayout.findViewById(this.f1726f);
            this.f1731k = viewFindViewById;
            if (viewFindViewById == null) {
                if (coordinatorLayout.isInEditMode()) {
                    this.f1732l = null;
                    this.f1731k = null;
                    return;
                }
                throw new IllegalStateException("Could not find CoordinatorLayout descendant view with id " + coordinatorLayout.getResources().getResourceName(this.f1726f) + " to anchor view " + view);
            }
            if (viewFindViewById == coordinatorLayout) {
                if (!coordinatorLayout.isInEditMode()) {
                    throw new IllegalStateException("View can not be anchored to the the parent CoordinatorLayout");
                }
                this.f1732l = null;
                this.f1731k = null;
                return;
            }
            for (ViewParent parent = viewFindViewById.getParent(); parent != coordinatorLayout && parent != null; parent = parent.getParent()) {
                if (parent == view) {
                    if (!coordinatorLayout.isInEditMode()) {
                        throw new IllegalStateException("Anchor must not be a descendant of the anchored view");
                    }
                    this.f1732l = null;
                    this.f1731k = null;
                    return;
                }
                if (parent instanceof View) {
                    viewFindViewById = parent;
                }
            }
            this.f1732l = viewFindViewById;
        }

        /* renamed from: o */
        public void m1448o(AbstractC0304c abstractC0304c) {
            AbstractC0304c abstractC0304c2 = this.f1721a;
            if (abstractC0304c2 != abstractC0304c) {
                if (abstractC0304c2 != null) {
                    abstractC0304c2.onDetachedFromLayoutParams();
                }
                this.f1721a = abstractC0304c;
                this.f1738r = null;
                this.f1722b = true;
                if (abstractC0304c != null) {
                    abstractC0304c.onAttachedToLayoutParams(this);
                }
            }
        }

        /* renamed from: p */
        public void m1449p(boolean z8) {
            this.f1736p = z8;
        }

        /* renamed from: q */
        public void m1450q(Rect rect) {
            this.f1737q.set(rect);
        }

        /* renamed from: r */
        public void m1451r(int i9, boolean z8) {
            if (i9 == 0) {
                this.f1734n = z8;
            } else {
                if (i9 != 1) {
                    return;
                }
                this.f1735o = z8;
            }
        }

        /* renamed from: s */
        public final boolean m1452s(View view, int i9) {
            int iM18420b = C4621e.m18420b(((C0307f) view.getLayoutParams()).f1727g, i9);
            return iM18420b != 0 && (C4621e.m18420b(this.f1728h, i9) & iM18420b) == iM18420b;
        }

        /* renamed from: t */
        public final boolean m1453t(View view, CoordinatorLayout coordinatorLayout) {
            if (this.f1731k.getId() != this.f1726f) {
                return false;
            }
            View view2 = this.f1731k;
            for (ViewParent parent = view2.getParent(); parent != coordinatorLayout; parent = parent.getParent()) {
                if (parent == null || parent == view) {
                    this.f1732l = null;
                    this.f1731k = null;
                    return false;
                }
                if (parent instanceof View) {
                    view2 = parent;
                }
            }
            this.f1732l = view2;
            return true;
        }

        public C0307f(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.f1722b = false;
            this.f1723c = 0;
            this.f1724d = 0;
            this.f1725e = -1;
            this.f1726f = -1;
            this.f1727g = 0;
            this.f1728h = 0;
            this.f1737q = new Rect();
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C6118c.CoordinatorLayout_Layout);
            this.f1723c = typedArrayObtainStyledAttributes.getInteger(C6118c.CoordinatorLayout_Layout_android_layout_gravity, 0);
            this.f1726f = typedArrayObtainStyledAttributes.getResourceId(C6118c.CoordinatorLayout_Layout_layout_anchor, -1);
            this.f1724d = typedArrayObtainStyledAttributes.getInteger(C6118c.CoordinatorLayout_Layout_layout_anchorGravity, 0);
            this.f1725e = typedArrayObtainStyledAttributes.getInteger(C6118c.CoordinatorLayout_Layout_layout_keyline, -1);
            this.f1727g = typedArrayObtainStyledAttributes.getInt(C6118c.CoordinatorLayout_Layout_layout_insetEdge, 0);
            this.f1728h = typedArrayObtainStyledAttributes.getInt(C6118c.CoordinatorLayout_Layout_layout_dodgeInsetEdges, 0);
            int i9 = C6118c.CoordinatorLayout_Layout_layout_behavior;
            boolean zHasValue = typedArrayObtainStyledAttributes.hasValue(i9);
            this.f1722b = zHasValue;
            if (zHasValue) {
                this.f1721a = CoordinatorLayout.parseBehavior(context, attributeSet, typedArrayObtainStyledAttributes.getString(i9));
            }
            typedArrayObtainStyledAttributes.recycle();
            AbstractC0304c abstractC0304c = this.f1721a;
            if (abstractC0304c != null) {
                abstractC0304c.onAttachedToLayoutParams(this);
            }
        }

        public C0307f(C0307f c0307f) {
            super((ViewGroup.MarginLayoutParams) c0307f);
            this.f1722b = false;
            this.f1723c = 0;
            this.f1724d = 0;
            this.f1725e = -1;
            this.f1726f = -1;
            this.f1727g = 0;
            this.f1728h = 0;
            this.f1737q = new Rect();
        }

        public C0307f(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
            this.f1722b = false;
            this.f1723c = 0;
            this.f1724d = 0;
            this.f1725e = -1;
            this.f1726f = -1;
            this.f1727g = 0;
            this.f1728h = 0;
            this.f1737q = new Rect();
        }

        public C0307f(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.f1722b = false;
            this.f1723c = 0;
            this.f1724d = 0;
            this.f1725e = -1;
            this.f1726f = -1;
            this.f1727g = 0;
            this.f1728h = 0;
            this.f1737q = new Rect();
        }
    }
}
