package com.google.android.material.bottomsheet;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.customview.view.AbsSavedState;
import androidx.customview.widget.C0342c;
import com.google.android.gms.auth.api.credentials.CredentialsApi;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.material.C3476R;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import p042d0.C4647u;
import p242y.C6587a;

/* loaded from: classes2.dex */
public class BottomSheetBehavior<V extends View> extends CoordinatorLayout.AbstractC0304c<V> {
    private static final float HIDE_FRICTION = 0.1f;
    private static final float HIDE_THRESHOLD = 0.5f;
    public static final int PEEK_HEIGHT_AUTO = -1;
    public static final int STATE_COLLAPSED = 4;
    public static final int STATE_DRAGGING = 1;
    public static final int STATE_EXPANDED = 3;
    public static final int STATE_HALF_EXPANDED = 6;
    public static final int STATE_HIDDEN = 5;
    public static final int STATE_SETTLING = 2;
    int activePointerId;
    private BottomSheetCallback callback;
    int collapsedOffset;
    private final C0342c.c dragCallback;
    private boolean fitToContents;
    int fitToContentsOffset;
    int halfExpandedOffset;
    boolean hideable;
    private boolean ignoreEvents;
    private Map<View, Integer> importantForAccessibilityMap;
    private int initialY;
    private int lastNestedScrollDy;
    private int lastPeekHeight;
    private float maximumVelocity;
    private boolean nestedScrolled;
    WeakReference<View> nestedScrollingChildRef;
    int parentHeight;
    private int peekHeight;
    private boolean peekHeightAuto;
    private int peekHeightMin;
    private boolean skipCollapsed;
    int state;
    boolean touchingScrollingChild;
    private VelocityTracker velocityTracker;
    C0342c viewDragHelper;
    WeakReference<V> viewRef;

    public static abstract class BottomSheetCallback {
        public abstract void onSlide(View view, float f9);

        public abstract void onStateChanged(View view, int i9);
    }

    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.ClassLoaderCreator<SavedState>() { // from class: com.google.android.material.bottomsheet.BottomSheetBehavior.SavedState.1
            @Override // android.os.Parcelable.Creator
            public SavedState[] newArray(int i9) {
                return new SavedState[i9];
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.ClassLoaderCreator
            public SavedState createFromParcel(Parcel parcel, ClassLoader classLoader) {
                return new SavedState(parcel, classLoader);
            }

            @Override // android.os.Parcelable.Creator
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel, (ClassLoader) null);
            }
        };
        final int state;

        public SavedState(Parcel parcel) {
            this(parcel, (ClassLoader) null);
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i9) {
            super.writeToParcel(parcel, i9);
            parcel.writeInt(this.state);
        }

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.state = parcel.readInt();
        }

        public SavedState(Parcelable parcelable, int i9) {
            super(parcelable);
            this.state = i9;
        }
    }

    public class SettleRunnable implements Runnable {
        private final int targetState;
        private final View view;

        public SettleRunnable(View view, int i9) {
            this.view = view;
            this.targetState = i9;
        }

        @Override // java.lang.Runnable
        public void run() {
            C0342c c0342c = BottomSheetBehavior.this.viewDragHelper;
            if (c0342c == null || !c0342c.m1688n(true)) {
                BottomSheetBehavior.this.setStateInternal(this.targetState);
            } else {
                C4647u.m18525U(this.view, this);
            }
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface State {
    }

    public BottomSheetBehavior() {
        this.fitToContents = true;
        this.state = 4;
        this.dragCallback = new C0342c.c() { // from class: com.google.android.material.bottomsheet.BottomSheetBehavior.2
            @Override // androidx.customview.widget.C0342c.c
            public int clampViewPositionHorizontal(View view, int i9, int i10) {
                return view.getLeft();
            }

            @Override // androidx.customview.widget.C0342c.c
            public int clampViewPositionVertical(View view, int i9, int i10) {
                int expandedOffset = BottomSheetBehavior.this.getExpandedOffset();
                BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.this;
                return C6587a.m25200b(i9, expandedOffset, bottomSheetBehavior.hideable ? bottomSheetBehavior.parentHeight : bottomSheetBehavior.collapsedOffset);
            }

            @Override // androidx.customview.widget.C0342c.c
            public int getViewVerticalDragRange(View view) {
                BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.this;
                return bottomSheetBehavior.hideable ? bottomSheetBehavior.parentHeight : bottomSheetBehavior.collapsedOffset;
            }

            @Override // androidx.customview.widget.C0342c.c
            public void onViewDragStateChanged(int i9) {
                if (i9 == 1) {
                    BottomSheetBehavior.this.setStateInternal(1);
                }
            }

            @Override // androidx.customview.widget.C0342c.c
            public void onViewPositionChanged(View view, int i9, int i10, int i11, int i12) {
                BottomSheetBehavior.this.dispatchOnSlide(i10);
            }

            /* JADX WARN: Removed duplicated region for block: B:10:0x0022 A[PHI: r2
              0x0022: PHI (r2v7 int) = (r2v0 int), (r2v6 int), (r2v0 int) binds: [B:37:0x00a2, B:31:0x0089, B:8:0x001e] A[DONT_GENERATE, DONT_INLINE]] */
            @Override // androidx.customview.widget.C0342c.c
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public void onViewReleased(View view, float f9, float f10) {
                int i9;
                int i10 = 0;
                int i11 = 6;
                int i12 = 3;
                if (f10 >= BitmapDescriptorFactory.HUE_RED) {
                    BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.this;
                    if (bottomSheetBehavior.hideable && bottomSheetBehavior.shouldHide(view, f10) && (view.getTop() > BottomSheetBehavior.this.collapsedOffset || Math.abs(f9) < Math.abs(f10))) {
                        i9 = BottomSheetBehavior.this.parentHeight;
                        i12 = 5;
                    } else if (f10 == BitmapDescriptorFactory.HUE_RED || Math.abs(f9) > Math.abs(f10)) {
                        int top = view.getTop();
                        if (!BottomSheetBehavior.this.fitToContents) {
                            BottomSheetBehavior bottomSheetBehavior2 = BottomSheetBehavior.this;
                            int i13 = bottomSheetBehavior2.halfExpandedOffset;
                            if (top < i13) {
                                if (top >= Math.abs(top - bottomSheetBehavior2.collapsedOffset)) {
                                    i10 = BottomSheetBehavior.this.halfExpandedOffset;
                                }
                            } else if (Math.abs(top - i13) < Math.abs(top - BottomSheetBehavior.this.collapsedOffset)) {
                                i10 = BottomSheetBehavior.this.halfExpandedOffset;
                            } else {
                                i10 = BottomSheetBehavior.this.collapsedOffset;
                                i11 = 4;
                            }
                        } else if (Math.abs(top - BottomSheetBehavior.this.fitToContentsOffset) < Math.abs(top - BottomSheetBehavior.this.collapsedOffset)) {
                            i10 = BottomSheetBehavior.this.fitToContentsOffset;
                            i11 = 3;
                        } else {
                            i10 = BottomSheetBehavior.this.collapsedOffset;
                            i11 = 4;
                        }
                        i9 = i10;
                        i12 = i11;
                    } else {
                        i9 = BottomSheetBehavior.this.collapsedOffset;
                        i12 = 4;
                    }
                } else if (BottomSheetBehavior.this.fitToContents) {
                    i9 = BottomSheetBehavior.this.fitToContentsOffset;
                } else {
                    int top2 = view.getTop();
                    int i14 = BottomSheetBehavior.this.halfExpandedOffset;
                    if (top2 > i14) {
                        i10 = i14;
                    } else {
                        i11 = 3;
                    }
                    i9 = i10;
                    i12 = i11;
                }
                if (!BottomSheetBehavior.this.viewDragHelper.m1671N(view.getLeft(), i9)) {
                    BottomSheetBehavior.this.setStateInternal(i12);
                } else {
                    BottomSheetBehavior.this.setStateInternal(2);
                    C4647u.m18525U(view, new SettleRunnable(view, i12));
                }
            }

            @Override // androidx.customview.widget.C0342c.c
            public boolean tryCaptureView(View view, int i9) {
                WeakReference<V> weakReference;
                View view2;
                BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.this;
                int i10 = bottomSheetBehavior.state;
                if (i10 == 1 || bottomSheetBehavior.touchingScrollingChild) {
                    return false;
                }
                return ((i10 == 3 && bottomSheetBehavior.activePointerId == i9 && (view2 = bottomSheetBehavior.nestedScrollingChildRef.get()) != null && view2.canScrollVertically(-1)) || (weakReference = BottomSheetBehavior.this.viewRef) == null || weakReference.get() != view) ? false : true;
            }
        };
    }

    private void calculateCollapsedOffset() {
        if (this.fitToContents) {
            this.collapsedOffset = Math.max(this.parentHeight - this.lastPeekHeight, this.fitToContentsOffset);
        } else {
            this.collapsedOffset = this.parentHeight - this.lastPeekHeight;
        }
    }

    public static <V extends View> BottomSheetBehavior<V> from(V v8) {
        ViewGroup.LayoutParams layoutParams = v8.getLayoutParams();
        if (!(layoutParams instanceof CoordinatorLayout.C0307f)) {
            throw new IllegalArgumentException("The view is not a child of CoordinatorLayout");
        }
        CoordinatorLayout.AbstractC0304c abstractC0304cM1439f = ((CoordinatorLayout.C0307f) layoutParams).m1439f();
        if (abstractC0304cM1439f instanceof BottomSheetBehavior) {
            return (BottomSheetBehavior) abstractC0304cM1439f;
        }
        throw new IllegalArgumentException("The view is not associated with BottomSheetBehavior");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getExpandedOffset() {
        if (this.fitToContents) {
            return this.fitToContentsOffset;
        }
        return 0;
    }

    private float getYVelocity() {
        VelocityTracker velocityTracker = this.velocityTracker;
        if (velocityTracker == null) {
            return BitmapDescriptorFactory.HUE_RED;
        }
        velocityTracker.computeCurrentVelocity(CredentialsApi.ACTIVITY_RESULT_ADD_ACCOUNT, this.maximumVelocity);
        return this.velocityTracker.getYVelocity(this.activePointerId);
    }

    private void reset() {
        this.activePointerId = -1;
        VelocityTracker velocityTracker = this.velocityTracker;
        if (velocityTracker != null) {
            velocityTracker.recycle();
            this.velocityTracker = null;
        }
    }

    private void updateImportantForAccessibility(boolean z8) {
        WeakReference<V> weakReference = this.viewRef;
        if (weakReference == null) {
            return;
        }
        ViewParent parent = weakReference.get().getParent();
        if (parent instanceof CoordinatorLayout) {
            CoordinatorLayout coordinatorLayout = (CoordinatorLayout) parent;
            int childCount = coordinatorLayout.getChildCount();
            if (z8) {
                if (this.importantForAccessibilityMap != null) {
                    return;
                } else {
                    this.importantForAccessibilityMap = new HashMap(childCount);
                }
            }
            for (int i9 = 0; i9 < childCount; i9++) {
                View childAt = coordinatorLayout.getChildAt(i9);
                if (childAt != this.viewRef.get()) {
                    if (z8) {
                        this.importantForAccessibilityMap.put(childAt, Integer.valueOf(childAt.getImportantForAccessibility()));
                        C4647u.m18548i0(childAt, 4);
                    } else {
                        Map<View, Integer> map = this.importantForAccessibilityMap;
                        if (map != null && map.containsKey(childAt)) {
                            C4647u.m18548i0(childAt, this.importantForAccessibilityMap.get(childAt).intValue());
                        }
                    }
                }
            }
            if (z8) {
                return;
            }
            this.importantForAccessibilityMap = null;
        }
    }

    public void dispatchOnSlide(int i9) {
        BottomSheetCallback bottomSheetCallback;
        V v8 = this.viewRef.get();
        if (v8 == null || (bottomSheetCallback = this.callback) == null) {
            return;
        }
        if (i9 > this.collapsedOffset) {
            bottomSheetCallback.onSlide(v8, (r2 - i9) / (this.parentHeight - r2));
        } else {
            bottomSheetCallback.onSlide(v8, (r2 - i9) / (r2 - getExpandedOffset()));
        }
    }

    public View findScrollingChild(View view) {
        if (C4647u.m18514J(view)) {
            return view;
        }
        if (!(view instanceof ViewGroup)) {
            return null;
        }
        ViewGroup viewGroup = (ViewGroup) view;
        int childCount = viewGroup.getChildCount();
        for (int i9 = 0; i9 < childCount; i9++) {
            View viewFindScrollingChild = findScrollingChild(viewGroup.getChildAt(i9));
            if (viewFindScrollingChild != null) {
                return viewFindScrollingChild;
            }
        }
        return null;
    }

    public final int getPeekHeight() {
        if (this.peekHeightAuto) {
            return -1;
        }
        return this.peekHeight;
    }

    public int getPeekHeightMin() {
        return this.peekHeightMin;
    }

    public boolean getSkipCollapsed() {
        return this.skipCollapsed;
    }

    public final int getState() {
        return this.state;
    }

    public boolean isFitToContents() {
        return this.fitToContents;
    }

    public boolean isHideable() {
        return this.hideable;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.AbstractC0304c
    public boolean onInterceptTouchEvent(CoordinatorLayout coordinatorLayout, V v8, MotionEvent motionEvent) {
        C0342c c0342c;
        if (!v8.isShown()) {
            this.ignoreEvents = true;
            return false;
        }
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            reset();
        }
        if (this.velocityTracker == null) {
            this.velocityTracker = VelocityTracker.obtain();
        }
        this.velocityTracker.addMovement(motionEvent);
        if (actionMasked == 0) {
            int x8 = (int) motionEvent.getX();
            this.initialY = (int) motionEvent.getY();
            WeakReference<View> weakReference = this.nestedScrollingChildRef;
            View view = weakReference != null ? weakReference.get() : null;
            if (view != null && coordinatorLayout.isPointInChildBounds(view, x8, this.initialY)) {
                this.activePointerId = motionEvent.getPointerId(motionEvent.getActionIndex());
                this.touchingScrollingChild = true;
            }
            this.ignoreEvents = this.activePointerId == -1 && !coordinatorLayout.isPointInChildBounds(v8, x8, this.initialY);
        } else if (actionMasked == 1 || actionMasked == 3) {
            this.touchingScrollingChild = false;
            this.activePointerId = -1;
            if (this.ignoreEvents) {
                this.ignoreEvents = false;
                return false;
            }
        }
        if (!this.ignoreEvents && (c0342c = this.viewDragHelper) != null && c0342c.m1672O(motionEvent)) {
            return true;
        }
        WeakReference<View> weakReference2 = this.nestedScrollingChildRef;
        View view2 = weakReference2 != null ? weakReference2.get() : null;
        return (actionMasked != 2 || view2 == null || this.ignoreEvents || this.state == 1 || coordinatorLayout.isPointInChildBounds(view2, (int) motionEvent.getX(), (int) motionEvent.getY()) || this.viewDragHelper == null || Math.abs(((float) this.initialY) - motionEvent.getY()) <= ((float) this.viewDragHelper.m1698z())) ? false : true;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.AbstractC0304c
    public boolean onLayoutChild(CoordinatorLayout coordinatorLayout, V v8, int i9) {
        if (C4647u.m18561p(coordinatorLayout) && !C4647u.m18561p(v8)) {
            v8.setFitsSystemWindows(true);
        }
        int top = v8.getTop();
        coordinatorLayout.onLayoutChild(v8, i9);
        this.parentHeight = coordinatorLayout.getHeight();
        if (this.peekHeightAuto) {
            if (this.peekHeightMin == 0) {
                this.peekHeightMin = coordinatorLayout.getResources().getDimensionPixelSize(C3476R.dimen.design_bottom_sheet_peek_height_min);
            }
            this.lastPeekHeight = Math.max(this.peekHeightMin, this.parentHeight - ((coordinatorLayout.getWidth() * 9) / 16));
        } else {
            this.lastPeekHeight = this.peekHeight;
        }
        this.fitToContentsOffset = Math.max(0, this.parentHeight - v8.getHeight());
        this.halfExpandedOffset = this.parentHeight / 2;
        calculateCollapsedOffset();
        int i10 = this.state;
        if (i10 == 3) {
            C4647u.m18519O(v8, getExpandedOffset());
        } else if (i10 == 6) {
            C4647u.m18519O(v8, this.halfExpandedOffset);
        } else if (this.hideable && i10 == 5) {
            C4647u.m18519O(v8, this.parentHeight);
        } else if (i10 == 4) {
            C4647u.m18519O(v8, this.collapsedOffset);
        } else if (i10 == 1 || i10 == 2) {
            C4647u.m18519O(v8, top - v8.getTop());
        }
        if (this.viewDragHelper == null) {
            this.viewDragHelper = C0342c.m1657p(coordinatorLayout, this.dragCallback);
        }
        this.viewRef = new WeakReference<>(v8);
        this.nestedScrollingChildRef = new WeakReference<>(findScrollingChild(v8));
        return true;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.AbstractC0304c
    public boolean onNestedPreFling(CoordinatorLayout coordinatorLayout, V v8, View view, float f9, float f10) {
        return view == this.nestedScrollingChildRef.get() && (this.state != 3 || super.onNestedPreFling(coordinatorLayout, v8, view, f9, f10));
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.AbstractC0304c
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, V v8, View view, int i9, int i10, int[] iArr, int i11) {
        if (i11 != 1 && view == this.nestedScrollingChildRef.get()) {
            int top = v8.getTop();
            int i12 = top - i10;
            if (i10 > 0) {
                if (i12 < getExpandedOffset()) {
                    int expandedOffset = top - getExpandedOffset();
                    iArr[1] = expandedOffset;
                    C4647u.m18519O(v8, -expandedOffset);
                    setStateInternal(3);
                } else {
                    iArr[1] = i10;
                    C4647u.m18519O(v8, -i10);
                    setStateInternal(1);
                }
            } else if (i10 < 0 && !view.canScrollVertically(-1)) {
                int i13 = this.collapsedOffset;
                if (i12 <= i13 || this.hideable) {
                    iArr[1] = i10;
                    C4647u.m18519O(v8, -i10);
                    setStateInternal(1);
                } else {
                    int i14 = top - i13;
                    iArr[1] = i14;
                    C4647u.m18519O(v8, -i14);
                    setStateInternal(4);
                }
            }
            dispatchOnSlide(v8.getTop());
            this.lastNestedScrollDy = i10;
            this.nestedScrolled = true;
        }
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.AbstractC0304c
    public void onRestoreInstanceState(CoordinatorLayout coordinatorLayout, V v8, Parcelable parcelable) {
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(coordinatorLayout, v8, savedState.getSuperState());
        int i9 = savedState.state;
        if (i9 == 1 || i9 == 2) {
            this.state = 4;
        } else {
            this.state = i9;
        }
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.AbstractC0304c
    public Parcelable onSaveInstanceState(CoordinatorLayout coordinatorLayout, V v8) {
        return new SavedState(super.onSaveInstanceState(coordinatorLayout, v8), this.state);
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.AbstractC0304c
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, V v8, View view, View view2, int i9, int i10) {
        this.lastNestedScrollDy = 0;
        this.nestedScrolled = false;
        return (i9 & 2) != 0;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.AbstractC0304c
    public void onStopNestedScroll(CoordinatorLayout coordinatorLayout, V v8, View view, int i9) {
        int expandedOffset;
        int i10 = 3;
        if (v8.getTop() == getExpandedOffset()) {
            setStateInternal(3);
            return;
        }
        if (view == this.nestedScrollingChildRef.get() && this.nestedScrolled) {
            if (this.lastNestedScrollDy > 0) {
                expandedOffset = getExpandedOffset();
            } else if (this.hideable && shouldHide(v8, getYVelocity())) {
                expandedOffset = this.parentHeight;
                i10 = 5;
            } else {
                if (this.lastNestedScrollDy == 0) {
                    int top = v8.getTop();
                    if (!this.fitToContents) {
                        int i11 = this.halfExpandedOffset;
                        if (top < i11) {
                            if (top < Math.abs(top - this.collapsedOffset)) {
                                expandedOffset = 0;
                            } else {
                                expandedOffset = this.halfExpandedOffset;
                            }
                        } else if (Math.abs(top - i11) < Math.abs(top - this.collapsedOffset)) {
                            expandedOffset = this.halfExpandedOffset;
                        } else {
                            expandedOffset = this.collapsedOffset;
                        }
                        i10 = 6;
                    } else if (Math.abs(top - this.fitToContentsOffset) < Math.abs(top - this.collapsedOffset)) {
                        expandedOffset = this.fitToContentsOffset;
                    } else {
                        expandedOffset = this.collapsedOffset;
                    }
                } else {
                    expandedOffset = this.collapsedOffset;
                }
                i10 = 4;
            }
            if (this.viewDragHelper.m1673P(v8, v8.getLeft(), expandedOffset)) {
                setStateInternal(2);
                C4647u.m18525U(v8, new SettleRunnable(v8, i10));
            } else {
                setStateInternal(i10);
            }
            this.nestedScrolled = false;
        }
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.AbstractC0304c
    public boolean onTouchEvent(CoordinatorLayout coordinatorLayout, V v8, MotionEvent motionEvent) {
        if (!v8.isShown()) {
            return false;
        }
        int actionMasked = motionEvent.getActionMasked();
        if (this.state == 1 && actionMasked == 0) {
            return true;
        }
        C0342c c0342c = this.viewDragHelper;
        if (c0342c != null) {
            c0342c.m1663F(motionEvent);
        }
        if (actionMasked == 0) {
            reset();
        }
        if (this.velocityTracker == null) {
            this.velocityTracker = VelocityTracker.obtain();
        }
        this.velocityTracker.addMovement(motionEvent);
        if (actionMasked == 2 && !this.ignoreEvents && Math.abs(this.initialY - motionEvent.getY()) > this.viewDragHelper.m1698z()) {
            this.viewDragHelper.m1677c(v8, motionEvent.getPointerId(motionEvent.getActionIndex()));
        }
        return !this.ignoreEvents;
    }

    public void setBottomSheetCallback(BottomSheetCallback bottomSheetCallback) {
        this.callback = bottomSheetCallback;
    }

    public void setFitToContents(boolean z8) {
        if (this.fitToContents == z8) {
            return;
        }
        this.fitToContents = z8;
        if (this.viewRef != null) {
            calculateCollapsedOffset();
        }
        setStateInternal((this.fitToContents && this.state == 6) ? 3 : this.state);
    }

    public void setHideable(boolean z8) {
        this.hideable = z8;
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0015  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void setPeekHeight(int i9) {
        WeakReference<V> weakReference;
        V v8;
        boolean z8 = true;
        if (i9 == -1) {
            if (this.peekHeightAuto) {
                z8 = false;
            } else {
                this.peekHeightAuto = true;
            }
        } else if (this.peekHeightAuto || this.peekHeight != i9) {
            this.peekHeightAuto = false;
            this.peekHeight = Math.max(0, i9);
            this.collapsedOffset = this.parentHeight - i9;
        }
        if (!z8 || this.state != 4 || (weakReference = this.viewRef) == null || (v8 = weakReference.get()) == null) {
            return;
        }
        v8.requestLayout();
    }

    public void setSkipCollapsed(boolean z8) {
        this.skipCollapsed = z8;
    }

    public final void setState(final int i9) {
        if (i9 == this.state) {
            return;
        }
        WeakReference<V> weakReference = this.viewRef;
        if (weakReference == null) {
            if (i9 == 4 || i9 == 3 || i9 == 6 || (this.hideable && i9 == 5)) {
                this.state = i9;
                return;
            }
            return;
        }
        final V v8 = weakReference.get();
        if (v8 == null) {
            return;
        }
        ViewParent parent = v8.getParent();
        if (parent != null && parent.isLayoutRequested() && C4647u.m18512H(v8)) {
            v8.post(new Runnable() { // from class: com.google.android.material.bottomsheet.BottomSheetBehavior.1
                @Override // java.lang.Runnable
                public void run() {
                    BottomSheetBehavior.this.startSettlingAnimation(v8, i9);
                }
            });
        } else {
            startSettlingAnimation(v8, i9);
        }
    }

    public void setStateInternal(int i9) {
        BottomSheetCallback bottomSheetCallback;
        if (this.state == i9) {
            return;
        }
        this.state = i9;
        if (i9 == 6 || i9 == 3) {
            updateImportantForAccessibility(true);
        } else if (i9 == 5 || i9 == 4) {
            updateImportantForAccessibility(false);
        }
        V v8 = this.viewRef.get();
        if (v8 == null || (bottomSheetCallback = this.callback) == null) {
            return;
        }
        bottomSheetCallback.onStateChanged(v8, i9);
    }

    public boolean shouldHide(View view, float f9) {
        if (this.skipCollapsed) {
            return true;
        }
        return view.getTop() >= this.collapsedOffset && Math.abs((((float) view.getTop()) + (f9 * 0.1f)) - ((float) this.collapsedOffset)) / ((float) this.peekHeight) > HIDE_THRESHOLD;
    }

    public void startSettlingAnimation(View view, int i9) {
        int expandedOffset;
        int i10;
        if (i9 == 4) {
            expandedOffset = this.collapsedOffset;
        } else if (i9 == 6) {
            expandedOffset = this.halfExpandedOffset;
            if (this.fitToContents && expandedOffset <= (i10 = this.fitToContentsOffset)) {
                i9 = 3;
                expandedOffset = i10;
            }
        } else if (i9 == 3) {
            expandedOffset = getExpandedOffset();
        } else {
            if (!this.hideable || i9 != 5) {
                throw new IllegalArgumentException("Illegal state argument: " + i9);
            }
            expandedOffset = this.parentHeight;
        }
        if (!this.viewDragHelper.m1673P(view, view.getLeft(), expandedOffset)) {
            setStateInternal(i9);
        } else {
            setStateInternal(2);
            C4647u.m18525U(view, new SettleRunnable(view, i9));
        }
    }

    public BottomSheetBehavior(Context context, AttributeSet attributeSet) {
        int i9;
        super(context, attributeSet);
        this.fitToContents = true;
        this.state = 4;
        this.dragCallback = new C0342c.c() { // from class: com.google.android.material.bottomsheet.BottomSheetBehavior.2
            @Override // androidx.customview.widget.C0342c.c
            public int clampViewPositionHorizontal(View view, int i92, int i10) {
                return view.getLeft();
            }

            @Override // androidx.customview.widget.C0342c.c
            public int clampViewPositionVertical(View view, int i92, int i10) {
                int expandedOffset = BottomSheetBehavior.this.getExpandedOffset();
                BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.this;
                return C6587a.m25200b(i92, expandedOffset, bottomSheetBehavior.hideable ? bottomSheetBehavior.parentHeight : bottomSheetBehavior.collapsedOffset);
            }

            @Override // androidx.customview.widget.C0342c.c
            public int getViewVerticalDragRange(View view) {
                BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.this;
                return bottomSheetBehavior.hideable ? bottomSheetBehavior.parentHeight : bottomSheetBehavior.collapsedOffset;
            }

            @Override // androidx.customview.widget.C0342c.c
            public void onViewDragStateChanged(int i92) {
                if (i92 == 1) {
                    BottomSheetBehavior.this.setStateInternal(1);
                }
            }

            @Override // androidx.customview.widget.C0342c.c
            public void onViewPositionChanged(View view, int i92, int i10, int i11, int i12) {
                BottomSheetBehavior.this.dispatchOnSlide(i10);
            }

            /* JADX WARN: Removed duplicated region for block: B:10:0x0022 A[PHI: r2
              0x0022: PHI (r2v7 int) = (r2v0 int), (r2v6 int), (r2v0 int) binds: [B:37:0x00a2, B:31:0x0089, B:8:0x001e] A[DONT_GENERATE, DONT_INLINE]] */
            @Override // androidx.customview.widget.C0342c.c
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public void onViewReleased(View view, float f9, float f10) {
                int i92;
                int i10 = 0;
                int i11 = 6;
                int i12 = 3;
                if (f10 >= BitmapDescriptorFactory.HUE_RED) {
                    BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.this;
                    if (bottomSheetBehavior.hideable && bottomSheetBehavior.shouldHide(view, f10) && (view.getTop() > BottomSheetBehavior.this.collapsedOffset || Math.abs(f9) < Math.abs(f10))) {
                        i92 = BottomSheetBehavior.this.parentHeight;
                        i12 = 5;
                    } else if (f10 == BitmapDescriptorFactory.HUE_RED || Math.abs(f9) > Math.abs(f10)) {
                        int top = view.getTop();
                        if (!BottomSheetBehavior.this.fitToContents) {
                            BottomSheetBehavior bottomSheetBehavior2 = BottomSheetBehavior.this;
                            int i13 = bottomSheetBehavior2.halfExpandedOffset;
                            if (top < i13) {
                                if (top >= Math.abs(top - bottomSheetBehavior2.collapsedOffset)) {
                                    i10 = BottomSheetBehavior.this.halfExpandedOffset;
                                }
                            } else if (Math.abs(top - i13) < Math.abs(top - BottomSheetBehavior.this.collapsedOffset)) {
                                i10 = BottomSheetBehavior.this.halfExpandedOffset;
                            } else {
                                i10 = BottomSheetBehavior.this.collapsedOffset;
                                i11 = 4;
                            }
                        } else if (Math.abs(top - BottomSheetBehavior.this.fitToContentsOffset) < Math.abs(top - BottomSheetBehavior.this.collapsedOffset)) {
                            i10 = BottomSheetBehavior.this.fitToContentsOffset;
                            i11 = 3;
                        } else {
                            i10 = BottomSheetBehavior.this.collapsedOffset;
                            i11 = 4;
                        }
                        i92 = i10;
                        i12 = i11;
                    } else {
                        i92 = BottomSheetBehavior.this.collapsedOffset;
                        i12 = 4;
                    }
                } else if (BottomSheetBehavior.this.fitToContents) {
                    i92 = BottomSheetBehavior.this.fitToContentsOffset;
                } else {
                    int top2 = view.getTop();
                    int i14 = BottomSheetBehavior.this.halfExpandedOffset;
                    if (top2 > i14) {
                        i10 = i14;
                    } else {
                        i11 = 3;
                    }
                    i92 = i10;
                    i12 = i11;
                }
                if (!BottomSheetBehavior.this.viewDragHelper.m1671N(view.getLeft(), i92)) {
                    BottomSheetBehavior.this.setStateInternal(i12);
                } else {
                    BottomSheetBehavior.this.setStateInternal(2);
                    C4647u.m18525U(view, new SettleRunnable(view, i12));
                }
            }

            @Override // androidx.customview.widget.C0342c.c
            public boolean tryCaptureView(View view, int i92) {
                WeakReference<V> weakReference;
                View view2;
                BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.this;
                int i10 = bottomSheetBehavior.state;
                if (i10 == 1 || bottomSheetBehavior.touchingScrollingChild) {
                    return false;
                }
                return ((i10 == 3 && bottomSheetBehavior.activePointerId == i92 && (view2 = bottomSheetBehavior.nestedScrollingChildRef.get()) != null && view2.canScrollVertically(-1)) || (weakReference = BottomSheetBehavior.this.viewRef) == null || weakReference.get() != view) ? false : true;
            }
        };
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C3476R.styleable.BottomSheetBehavior_Layout);
        int i10 = C3476R.styleable.BottomSheetBehavior_Layout_behavior_peekHeight;
        TypedValue typedValuePeekValue = typedArrayObtainStyledAttributes.peekValue(i10);
        if (typedValuePeekValue != null && (i9 = typedValuePeekValue.data) == -1) {
            setPeekHeight(i9);
        } else {
            setPeekHeight(typedArrayObtainStyledAttributes.getDimensionPixelSize(i10, -1));
        }
        setHideable(typedArrayObtainStyledAttributes.getBoolean(C3476R.styleable.BottomSheetBehavior_Layout_behavior_hideable, false));
        setFitToContents(typedArrayObtainStyledAttributes.getBoolean(C3476R.styleable.BottomSheetBehavior_Layout_behavior_fitToContents, true));
        setSkipCollapsed(typedArrayObtainStyledAttributes.getBoolean(C3476R.styleable.BottomSheetBehavior_Layout_behavior_skipCollapsed, false));
        typedArrayObtainStyledAttributes.recycle();
        this.maximumVelocity = ViewConfiguration.get(context).getScaledMaximumFlingVelocity();
    }
}
