package com.google.android.material.appbar;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.widget.LinearLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.customview.view.AbsSavedState;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.material.C3476R;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.internal.ThemeEnforcement;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import p021c0.C0697c;
import p042d0.C4628h0;
import p042d0.C4647u;
import p042d0.InterfaceC4635l;
import p042d0.InterfaceC4643q;
import p242y.C6587a;

@CoordinatorLayout.InterfaceC0305d(Behavior.class)
/* loaded from: classes2.dex */
public class AppBarLayout extends LinearLayout {
    private static final int INVALID_SCROLL_RANGE = -1;
    static final int PENDING_ACTION_ANIMATE_ENABLED = 4;
    static final int PENDING_ACTION_COLLAPSED = 2;
    static final int PENDING_ACTION_EXPANDED = 1;
    static final int PENDING_ACTION_FORCE = 8;
    static final int PENDING_ACTION_NONE = 0;
    private int downPreScrollRange;
    private int downScrollRange;
    private boolean haveChildWithInterpolator;
    private C4628h0 lastInsets;
    private boolean liftOnScroll;
    private boolean liftable;
    private boolean liftableOverride;
    private boolean lifted;
    private List<BaseOnOffsetChangedListener> listeners;
    private int pendingAction;
    private int[] tmpStatesArray;
    private int totalScrollRange;

    public static class BaseBehavior<T extends AppBarLayout> extends HeaderBehavior<T> {
        private static final int INVALID_POSITION = -1;
        private static final int MAX_OFFSET_ANIMATION_DURATION = 600;
        private WeakReference<View> lastNestedScrollingChildRef;
        private int lastStartedType;
        private ValueAnimator offsetAnimator;
        private int offsetDelta;
        private int offsetToChildIndexOnLayout;
        private boolean offsetToChildIndexOnLayoutIsMinHeight;
        private float offsetToChildIndexOnLayoutPerc;
        private BaseDragCallback onDragCallback;

        public static abstract class BaseDragCallback<T extends AppBarLayout> {
            public abstract boolean canDrag(T t8);
        }

        public BaseBehavior() {
            this.offsetToChildIndexOnLayout = -1;
        }

        private void animateOffsetTo(CoordinatorLayout coordinatorLayout, T t8, int i9, float f9) {
            int iAbs = Math.abs(getTopBottomOffsetForScrollingSibling() - i9);
            float fAbs = Math.abs(f9);
            animateOffsetWithDuration(coordinatorLayout, t8, i9, fAbs > BitmapDescriptorFactory.HUE_RED ? Math.round((iAbs / fAbs) * 1000.0f) * 3 : (int) (((iAbs / t8.getHeight()) + 1.0f) * 150.0f));
        }

        private void animateOffsetWithDuration(final CoordinatorLayout coordinatorLayout, final T t8, int i9, int i10) {
            int topBottomOffsetForScrollingSibling = getTopBottomOffsetForScrollingSibling();
            if (topBottomOffsetForScrollingSibling == i9) {
                ValueAnimator valueAnimator = this.offsetAnimator;
                if (valueAnimator == null || !valueAnimator.isRunning()) {
                    return;
                }
                this.offsetAnimator.cancel();
                return;
            }
            ValueAnimator valueAnimator2 = this.offsetAnimator;
            if (valueAnimator2 == null) {
                ValueAnimator valueAnimator3 = new ValueAnimator();
                this.offsetAnimator = valueAnimator3;
                valueAnimator3.setInterpolator(AnimationUtils.DECELERATE_INTERPOLATOR);
                this.offsetAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.google.android.material.appbar.AppBarLayout.BaseBehavior.1
                    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                    public void onAnimationUpdate(ValueAnimator valueAnimator4) {
                        BaseBehavior.this.setHeaderTopBottomOffset(coordinatorLayout, t8, ((Integer) valueAnimator4.getAnimatedValue()).intValue());
                    }
                });
            } else {
                valueAnimator2.cancel();
            }
            this.offsetAnimator.setDuration(Math.min(i10, MAX_OFFSET_ANIMATION_DURATION));
            this.offsetAnimator.setIntValues(topBottomOffsetForScrollingSibling, i9);
            this.offsetAnimator.start();
        }

        private boolean canScrollChildren(CoordinatorLayout coordinatorLayout, T t8, View view) {
            return t8.hasScrollableChildren() && coordinatorLayout.getHeight() - view.getHeight() <= t8.getHeight();
        }

        private static boolean checkFlag(int i9, int i10) {
            return (i9 & i10) == i10;
        }

        private View findFirstScrollingChild(CoordinatorLayout coordinatorLayout) {
            int childCount = coordinatorLayout.getChildCount();
            for (int i9 = 0; i9 < childCount; i9++) {
                View childAt = coordinatorLayout.getChildAt(i9);
                if (childAt instanceof InterfaceC4635l) {
                    return childAt;
                }
            }
            return null;
        }

        private static View getAppBarChildOnOffset(AppBarLayout appBarLayout, int i9) {
            int iAbs = Math.abs(i9);
            int childCount = appBarLayout.getChildCount();
            for (int i10 = 0; i10 < childCount; i10++) {
                View childAt = appBarLayout.getChildAt(i10);
                if (iAbs >= childAt.getTop() && iAbs <= childAt.getBottom()) {
                    return childAt;
                }
            }
            return null;
        }

        private int getChildIndexOnOffset(T t8, int i9) {
            int childCount = t8.getChildCount();
            for (int i10 = 0; i10 < childCount; i10++) {
                View childAt = t8.getChildAt(i10);
                int top = childAt.getTop();
                int bottom = childAt.getBottom();
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                if (checkFlag(layoutParams.getScrollFlags(), 32)) {
                    top -= ((LinearLayout.LayoutParams) layoutParams).topMargin;
                    bottom += ((LinearLayout.LayoutParams) layoutParams).bottomMargin;
                }
                int i11 = -i9;
                if (top <= i11 && bottom >= i11) {
                    return i10;
                }
            }
            return -1;
        }

        private int interpolateOffset(T t8, int i9) {
            int iAbs = Math.abs(i9);
            int childCount = t8.getChildCount();
            int topInset = 0;
            int i10 = 0;
            while (true) {
                if (i10 >= childCount) {
                    break;
                }
                View childAt = t8.getChildAt(i10);
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                Interpolator scrollInterpolator = layoutParams.getScrollInterpolator();
                if (iAbs < childAt.getTop() || iAbs > childAt.getBottom()) {
                    i10++;
                } else if (scrollInterpolator != null) {
                    int scrollFlags = layoutParams.getScrollFlags();
                    if ((scrollFlags & 1) != 0) {
                        topInset = 0 + childAt.getHeight() + ((LinearLayout.LayoutParams) layoutParams).topMargin + ((LinearLayout.LayoutParams) layoutParams).bottomMargin;
                        if ((scrollFlags & 2) != 0) {
                            topInset -= C4647u.m18568t(childAt);
                        }
                    }
                    if (C4647u.m18561p(childAt)) {
                        topInset -= t8.getTopInset();
                    }
                    if (topInset > 0) {
                        float f9 = topInset;
                        return Integer.signum(i9) * (childAt.getTop() + Math.round(f9 * scrollInterpolator.getInterpolation((iAbs - childAt.getTop()) / f9)));
                    }
                }
            }
            return i9;
        }

        private boolean shouldJumpElevationState(CoordinatorLayout coordinatorLayout, T t8) {
            List<View> dependents = coordinatorLayout.getDependents(t8);
            int size = dependents.size();
            for (int i9 = 0; i9 < size; i9++) {
                CoordinatorLayout.AbstractC0304c abstractC0304cM1439f = ((CoordinatorLayout.C0307f) dependents.get(i9).getLayoutParams()).m1439f();
                if (abstractC0304cM1439f instanceof ScrollingViewBehavior) {
                    return ((ScrollingViewBehavior) abstractC0304cM1439f).getOverlayTop() != 0;
                }
            }
            return false;
        }

        private void snapToChildIfNeeded(CoordinatorLayout coordinatorLayout, T t8) {
            int topBottomOffsetForScrollingSibling = getTopBottomOffsetForScrollingSibling();
            int childIndexOnOffset = getChildIndexOnOffset(t8, topBottomOffsetForScrollingSibling);
            if (childIndexOnOffset >= 0) {
                View childAt = t8.getChildAt(childIndexOnOffset);
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                int scrollFlags = layoutParams.getScrollFlags();
                if ((scrollFlags & 17) == 17) {
                    int i9 = -childAt.getTop();
                    int iM18568t = -childAt.getBottom();
                    if (childIndexOnOffset == t8.getChildCount() - 1) {
                        iM18568t += t8.getTopInset();
                    }
                    if (checkFlag(scrollFlags, 2)) {
                        iM18568t += C4647u.m18568t(childAt);
                    } else if (checkFlag(scrollFlags, 5)) {
                        int iM18568t2 = C4647u.m18568t(childAt) + iM18568t;
                        if (topBottomOffsetForScrollingSibling < iM18568t2) {
                            i9 = iM18568t2;
                        } else {
                            iM18568t = iM18568t2;
                        }
                    }
                    if (checkFlag(scrollFlags, 32)) {
                        i9 += ((LinearLayout.LayoutParams) layoutParams).topMargin;
                        iM18568t -= ((LinearLayout.LayoutParams) layoutParams).bottomMargin;
                    }
                    if (topBottomOffsetForScrollingSibling < (iM18568t + i9) / 2) {
                        i9 = iM18568t;
                    }
                    animateOffsetTo(coordinatorLayout, t8, C6587a.m25200b(i9, -t8.getTotalScrollRange(), 0), BitmapDescriptorFactory.HUE_RED);
                }
            }
        }

        private void stopNestedScrollIfNeeded(int i9, T t8, View view, int i10) {
            if (i10 == 1) {
                int topBottomOffsetForScrollingSibling = getTopBottomOffsetForScrollingSibling();
                if ((i9 >= 0 || topBottomOffsetForScrollingSibling != 0) && (i9 <= 0 || topBottomOffsetForScrollingSibling != (-t8.getDownNestedScrollRange()))) {
                    return;
                }
                C4647u.m18566r0(view, 1);
            }
        }

        /* JADX WARN: Removed duplicated region for block: B:18:0x0041  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        private void updateAppBarLayoutDrawableState(CoordinatorLayout coordinatorLayout, T t8, int i9, int i10, boolean z8) {
            boolean z9;
            View viewFindFirstScrollingChild;
            View appBarChildOnOffset = getAppBarChildOnOffset(t8, i9);
            if (appBarChildOnOffset != null) {
                int scrollFlags = ((LayoutParams) appBarChildOnOffset.getLayoutParams()).getScrollFlags();
                if ((scrollFlags & 1) != 0) {
                    int iM18568t = C4647u.m18568t(appBarChildOnOffset);
                    z9 = i10 <= 0 || (scrollFlags & 12) == 0 ? !((scrollFlags & 2) == 0 || (-i9) < (appBarChildOnOffset.getBottom() - iM18568t) - t8.getTopInset()) : (-i9) >= (appBarChildOnOffset.getBottom() - iM18568t) - t8.getTopInset();
                }
                if (t8.isLiftOnScroll() && (viewFindFirstScrollingChild = findFirstScrollingChild(coordinatorLayout)) != null) {
                    z9 = viewFindFirstScrollingChild.getScrollY() > 0;
                }
                boolean liftedState = t8.setLiftedState(z9);
                if (z8 || (liftedState && shouldJumpElevationState(coordinatorLayout, t8))) {
                    t8.jumpDrawablesToCurrentState();
                }
            }
        }

        @Override // com.google.android.material.appbar.HeaderBehavior
        public int getTopBottomOffsetForScrollingSibling() {
            return getTopAndBottomOffset() + this.offsetDelta;
        }

        public boolean isOffsetAnimatorRunning() {
            ValueAnimator valueAnimator = this.offsetAnimator;
            return valueAnimator != null && valueAnimator.isRunning();
        }

        public void setDragCallback(BaseDragCallback baseDragCallback) {
            this.onDragCallback = baseDragCallback;
        }

        @Override // com.google.android.material.appbar.HeaderBehavior
        public boolean canDragView(T t8) {
            BaseDragCallback baseDragCallback = this.onDragCallback;
            if (baseDragCallback != null) {
                return baseDragCallback.canDrag(t8);
            }
            WeakReference<View> weakReference = this.lastNestedScrollingChildRef;
            if (weakReference == null) {
                return true;
            }
            View view = weakReference.get();
            return (view == null || !view.isShown() || view.canScrollVertically(-1)) ? false : true;
        }

        @Override // com.google.android.material.appbar.HeaderBehavior
        public int getMaxDragOffset(T t8) {
            return -t8.getDownNestedScrollRange();
        }

        @Override // com.google.android.material.appbar.HeaderBehavior
        public int getScrollRangeForDragFling(T t8) {
            return t8.getTotalScrollRange();
        }

        @Override // com.google.android.material.appbar.HeaderBehavior
        public void onFlingFinished(CoordinatorLayout coordinatorLayout, T t8) {
            snapToChildIfNeeded(coordinatorLayout, t8);
        }

        @Override // com.google.android.material.appbar.ViewOffsetBehavior, androidx.coordinatorlayout.widget.CoordinatorLayout.AbstractC0304c
        public boolean onLayoutChild(CoordinatorLayout coordinatorLayout, T t8, int i9) {
            boolean zOnLayoutChild = super.onLayoutChild(coordinatorLayout, (CoordinatorLayout) t8, i9);
            int pendingAction = t8.getPendingAction();
            int i10 = this.offsetToChildIndexOnLayout;
            if (i10 >= 0 && (pendingAction & 8) == 0) {
                View childAt = t8.getChildAt(i10);
                setHeaderTopBottomOffset(coordinatorLayout, t8, (-childAt.getBottom()) + (this.offsetToChildIndexOnLayoutIsMinHeight ? C4647u.m18568t(childAt) + t8.getTopInset() : Math.round(childAt.getHeight() * this.offsetToChildIndexOnLayoutPerc)));
            } else if (pendingAction != 0) {
                boolean z8 = (pendingAction & 4) != 0;
                if ((pendingAction & 2) != 0) {
                    int i11 = -t8.getUpNestedPreScrollRange();
                    if (z8) {
                        animateOffsetTo(coordinatorLayout, t8, i11, BitmapDescriptorFactory.HUE_RED);
                    } else {
                        setHeaderTopBottomOffset(coordinatorLayout, t8, i11);
                    }
                } else if ((pendingAction & 1) != 0) {
                    if (z8) {
                        animateOffsetTo(coordinatorLayout, t8, 0, BitmapDescriptorFactory.HUE_RED);
                    } else {
                        setHeaderTopBottomOffset(coordinatorLayout, t8, 0);
                    }
                }
            }
            t8.resetPendingAction();
            this.offsetToChildIndexOnLayout = -1;
            setTopAndBottomOffset(C6587a.m25200b(getTopAndBottomOffset(), -t8.getTotalScrollRange(), 0));
            updateAppBarLayoutDrawableState(coordinatorLayout, t8, getTopAndBottomOffset(), 0, true);
            t8.dispatchOffsetUpdates(getTopAndBottomOffset());
            return zOnLayoutChild;
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.AbstractC0304c
        public boolean onMeasureChild(CoordinatorLayout coordinatorLayout, T t8, int i9, int i10, int i11, int i12) {
            if (((ViewGroup.MarginLayoutParams) ((CoordinatorLayout.C0307f) t8.getLayoutParams())).height != -2) {
                return super.onMeasureChild(coordinatorLayout, (CoordinatorLayout) t8, i9, i10, i11, i12);
            }
            coordinatorLayout.onMeasureChild(t8, i9, i10, View.MeasureSpec.makeMeasureSpec(0, 0), i12);
            return true;
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.AbstractC0304c
        public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, T t8, View view, int i9, int i10, int[] iArr, int i11) {
            int i12;
            int downNestedPreScrollRange;
            if (i10 != 0) {
                if (i10 < 0) {
                    i12 = -t8.getTotalScrollRange();
                    downNestedPreScrollRange = t8.getDownNestedPreScrollRange() + i12;
                } else {
                    i12 = -t8.getUpNestedPreScrollRange();
                    downNestedPreScrollRange = 0;
                }
                int i13 = i12;
                int i14 = downNestedPreScrollRange;
                if (i13 != i14) {
                    iArr[1] = scroll(coordinatorLayout, t8, i10, i13, i14);
                    stopNestedScrollIfNeeded(i10, t8, view, i11);
                }
            }
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.AbstractC0304c
        public void onNestedScroll(CoordinatorLayout coordinatorLayout, T t8, View view, int i9, int i10, int i11, int i12, int i13) {
            if (i12 < 0) {
                scroll(coordinatorLayout, t8, i12, -t8.getDownNestedScrollRange(), 0);
                stopNestedScrollIfNeeded(i12, t8, view, i13);
            }
            if (t8.isLiftOnScroll()) {
                t8.setLiftedState(view.getScrollY() > 0);
            }
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.AbstractC0304c
        public void onRestoreInstanceState(CoordinatorLayout coordinatorLayout, T t8, Parcelable parcelable) {
            if (!(parcelable instanceof SavedState)) {
                super.onRestoreInstanceState(coordinatorLayout, (CoordinatorLayout) t8, parcelable);
                this.offsetToChildIndexOnLayout = -1;
                return;
            }
            SavedState savedState = (SavedState) parcelable;
            super.onRestoreInstanceState(coordinatorLayout, (CoordinatorLayout) t8, savedState.getSuperState());
            this.offsetToChildIndexOnLayout = savedState.firstVisibleChildIndex;
            this.offsetToChildIndexOnLayoutPerc = savedState.firstVisibleChildPercentageShown;
            this.offsetToChildIndexOnLayoutIsMinHeight = savedState.firstVisibleChildAtMinimumHeight;
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.AbstractC0304c
        public Parcelable onSaveInstanceState(CoordinatorLayout coordinatorLayout, T t8) {
            Parcelable parcelableOnSaveInstanceState = super.onSaveInstanceState(coordinatorLayout, (CoordinatorLayout) t8);
            int topAndBottomOffset = getTopAndBottomOffset();
            int childCount = t8.getChildCount();
            for (int i9 = 0; i9 < childCount; i9++) {
                View childAt = t8.getChildAt(i9);
                int bottom = childAt.getBottom() + topAndBottomOffset;
                if (childAt.getTop() + topAndBottomOffset <= 0 && bottom >= 0) {
                    SavedState savedState = new SavedState(parcelableOnSaveInstanceState);
                    savedState.firstVisibleChildIndex = i9;
                    savedState.firstVisibleChildAtMinimumHeight = bottom == C4647u.m18568t(childAt) + t8.getTopInset();
                    savedState.firstVisibleChildPercentageShown = bottom / childAt.getHeight();
                    return savedState;
                }
            }
            return parcelableOnSaveInstanceState;
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.AbstractC0304c
        public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, T t8, View view, View view2, int i9, int i10) {
            ValueAnimator valueAnimator;
            boolean z8 = (i9 & 2) != 0 && (t8.isLiftOnScroll() || canScrollChildren(coordinatorLayout, t8, view));
            if (z8 && (valueAnimator = this.offsetAnimator) != null) {
                valueAnimator.cancel();
            }
            this.lastNestedScrollingChildRef = null;
            this.lastStartedType = i10;
            return z8;
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.AbstractC0304c
        public void onStopNestedScroll(CoordinatorLayout coordinatorLayout, T t8, View view, int i9) {
            if (this.lastStartedType == 0 || i9 == 1) {
                snapToChildIfNeeded(coordinatorLayout, t8);
            }
            this.lastNestedScrollingChildRef = new WeakReference<>(view);
        }

        @Override // com.google.android.material.appbar.HeaderBehavior
        public int setHeaderTopBottomOffset(CoordinatorLayout coordinatorLayout, T t8, int i9, int i10, int i11) {
            int topBottomOffsetForScrollingSibling = getTopBottomOffsetForScrollingSibling();
            int i12 = 0;
            if (i10 == 0 || topBottomOffsetForScrollingSibling < i10 || topBottomOffsetForScrollingSibling > i11) {
                this.offsetDelta = 0;
            } else {
                int iM25200b = C6587a.m25200b(i9, i10, i11);
                if (topBottomOffsetForScrollingSibling != iM25200b) {
                    int iInterpolateOffset = t8.hasChildWithInterpolator() ? interpolateOffset(t8, iM25200b) : iM25200b;
                    boolean topAndBottomOffset = setTopAndBottomOffset(iInterpolateOffset);
                    i12 = topBottomOffsetForScrollingSibling - iM25200b;
                    this.offsetDelta = iM25200b - iInterpolateOffset;
                    if (!topAndBottomOffset && t8.hasChildWithInterpolator()) {
                        coordinatorLayout.dispatchDependentViewsChanged(t8);
                    }
                    t8.dispatchOffsetUpdates(getTopAndBottomOffset());
                    updateAppBarLayoutDrawableState(coordinatorLayout, t8, iM25200b, iM25200b < topBottomOffsetForScrollingSibling ? -1 : 1, false);
                }
            }
            return i12;
        }

        public BaseBehavior(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.offsetToChildIndexOnLayout = -1;
        }

        public static class SavedState extends AbsSavedState {
            public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.ClassLoaderCreator<SavedState>() { // from class: com.google.android.material.appbar.AppBarLayout.BaseBehavior.SavedState.1
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
                    return new SavedState(parcel, null);
                }
            };
            boolean firstVisibleChildAtMinimumHeight;
            int firstVisibleChildIndex;
            float firstVisibleChildPercentageShown;

            public SavedState(Parcel parcel, ClassLoader classLoader) {
                super(parcel, classLoader);
                this.firstVisibleChildIndex = parcel.readInt();
                this.firstVisibleChildPercentageShown = parcel.readFloat();
                this.firstVisibleChildAtMinimumHeight = parcel.readByte() != 0;
            }

            @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
            public void writeToParcel(Parcel parcel, int i9) {
                super.writeToParcel(parcel, i9);
                parcel.writeInt(this.firstVisibleChildIndex);
                parcel.writeFloat(this.firstVisibleChildPercentageShown);
                parcel.writeByte(this.firstVisibleChildAtMinimumHeight ? (byte) 1 : (byte) 0);
            }

            public SavedState(Parcelable parcelable) {
                super(parcelable);
            }
        }
    }

    public interface BaseOnOffsetChangedListener<T extends AppBarLayout> {
        void onOffsetChanged(T t8, int i9);
    }

    public static class Behavior extends BaseBehavior<AppBarLayout> {

        public static abstract class DragCallback extends BaseBehavior.BaseDragCallback<AppBarLayout> {
        }

        public Behavior() {
        }

        @Override // com.google.android.material.appbar.ViewOffsetBehavior
        public /* bridge */ /* synthetic */ int getLeftAndRightOffset() {
            return super.getLeftAndRightOffset();
        }

        @Override // com.google.android.material.appbar.ViewOffsetBehavior
        public /* bridge */ /* synthetic */ int getTopAndBottomOffset() {
            return super.getTopAndBottomOffset();
        }

        @Override // com.google.android.material.appbar.AppBarLayout.BaseBehavior
        public /* bridge */ /* synthetic */ boolean onLayoutChild(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, int i9) {
            return super.onLayoutChild(coordinatorLayout, (CoordinatorLayout) appBarLayout, i9);
        }

        @Override // com.google.android.material.appbar.AppBarLayout.BaseBehavior
        public /* bridge */ /* synthetic */ boolean onMeasureChild(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, int i9, int i10, int i11, int i12) {
            return super.onMeasureChild(coordinatorLayout, (CoordinatorLayout) appBarLayout, i9, i10, i11, i12);
        }

        @Override // com.google.android.material.appbar.AppBarLayout.BaseBehavior
        public /* bridge */ /* synthetic */ void onNestedPreScroll(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, View view, int i9, int i10, int[] iArr, int i11) {
            super.onNestedPreScroll(coordinatorLayout, (CoordinatorLayout) appBarLayout, view, i9, i10, iArr, i11);
        }

        @Override // com.google.android.material.appbar.AppBarLayout.BaseBehavior
        public /* bridge */ /* synthetic */ void onNestedScroll(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, View view, int i9, int i10, int i11, int i12, int i13) {
            super.onNestedScroll(coordinatorLayout, (CoordinatorLayout) appBarLayout, view, i9, i10, i11, i12, i13);
        }

        @Override // com.google.android.material.appbar.AppBarLayout.BaseBehavior
        public /* bridge */ /* synthetic */ void onRestoreInstanceState(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, Parcelable parcelable) {
            super.onRestoreInstanceState(coordinatorLayout, (CoordinatorLayout) appBarLayout, parcelable);
        }

        @Override // com.google.android.material.appbar.AppBarLayout.BaseBehavior
        public /* bridge */ /* synthetic */ Parcelable onSaveInstanceState(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout) {
            return super.onSaveInstanceState(coordinatorLayout, (CoordinatorLayout) appBarLayout);
        }

        @Override // com.google.android.material.appbar.AppBarLayout.BaseBehavior
        public /* bridge */ /* synthetic */ boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, View view, View view2, int i9, int i10) {
            return super.onStartNestedScroll(coordinatorLayout, (CoordinatorLayout) appBarLayout, view, view2, i9, i10);
        }

        @Override // com.google.android.material.appbar.AppBarLayout.BaseBehavior
        public /* bridge */ /* synthetic */ void onStopNestedScroll(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, View view, int i9) {
            super.onStopNestedScroll(coordinatorLayout, (CoordinatorLayout) appBarLayout, view, i9);
        }

        @Override // com.google.android.material.appbar.AppBarLayout.BaseBehavior
        public /* bridge */ /* synthetic */ void setDragCallback(BaseBehavior.BaseDragCallback baseDragCallback) {
            super.setDragCallback(baseDragCallback);
        }

        @Override // com.google.android.material.appbar.ViewOffsetBehavior
        public /* bridge */ /* synthetic */ boolean setLeftAndRightOffset(int i9) {
            return super.setLeftAndRightOffset(i9);
        }

        @Override // com.google.android.material.appbar.ViewOffsetBehavior
        public /* bridge */ /* synthetic */ boolean setTopAndBottomOffset(int i9) {
            return super.setTopAndBottomOffset(i9);
        }

        public Behavior(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }
    }

    public interface OnOffsetChangedListener extends BaseOnOffsetChangedListener<AppBarLayout> {
        @Override // com.google.android.material.appbar.AppBarLayout.BaseOnOffsetChangedListener
        void onOffsetChanged(AppBarLayout appBarLayout, int i9);
    }

    public static class ScrollingViewBehavior extends HeaderScrollingViewBehavior {
        public ScrollingViewBehavior() {
        }

        private static int getAppBarLayoutOffset(AppBarLayout appBarLayout) {
            CoordinatorLayout.AbstractC0304c abstractC0304cM1439f = ((CoordinatorLayout.C0307f) appBarLayout.getLayoutParams()).m1439f();
            if (abstractC0304cM1439f instanceof BaseBehavior) {
                return ((BaseBehavior) abstractC0304cM1439f).getTopBottomOffsetForScrollingSibling();
            }
            return 0;
        }

        private void offsetChildAsNeeded(View view, View view2) {
            CoordinatorLayout.AbstractC0304c abstractC0304cM1439f = ((CoordinatorLayout.C0307f) view2.getLayoutParams()).m1439f();
            if (abstractC0304cM1439f instanceof BaseBehavior) {
                C4647u.m18519O(view, (((view2.getBottom() - view.getTop()) + ((BaseBehavior) abstractC0304cM1439f).offsetDelta) + getVerticalLayoutGap()) - getOverlapPixelsForOffset(view2));
            }
        }

        private void updateLiftedStateIfNeeded(View view, View view2) {
            if (view2 instanceof AppBarLayout) {
                AppBarLayout appBarLayout = (AppBarLayout) view2;
                if (appBarLayout.isLiftOnScroll()) {
                    appBarLayout.setLiftedState(view.getScrollY() > 0);
                }
            }
        }

        @Override // com.google.android.material.appbar.HeaderScrollingViewBehavior
        public /* bridge */ /* synthetic */ View findFirstDependency(List list) {
            return findFirstDependency((List<View>) list);
        }

        @Override // com.google.android.material.appbar.ViewOffsetBehavior
        public /* bridge */ /* synthetic */ int getLeftAndRightOffset() {
            return super.getLeftAndRightOffset();
        }

        @Override // com.google.android.material.appbar.HeaderScrollingViewBehavior
        public float getOverlapRatioForOffset(View view) {
            int i9;
            if (view instanceof AppBarLayout) {
                AppBarLayout appBarLayout = (AppBarLayout) view;
                int totalScrollRange = appBarLayout.getTotalScrollRange();
                int downNestedPreScrollRange = appBarLayout.getDownNestedPreScrollRange();
                int appBarLayoutOffset = getAppBarLayoutOffset(appBarLayout);
                if ((downNestedPreScrollRange == 0 || totalScrollRange + appBarLayoutOffset > downNestedPreScrollRange) && (i9 = totalScrollRange - downNestedPreScrollRange) != 0) {
                    return (appBarLayoutOffset / i9) + 1.0f;
                }
            }
            return BitmapDescriptorFactory.HUE_RED;
        }

        @Override // com.google.android.material.appbar.HeaderScrollingViewBehavior
        public int getScrollRange(View view) {
            return view instanceof AppBarLayout ? ((AppBarLayout) view).getTotalScrollRange() : super.getScrollRange(view);
        }

        @Override // com.google.android.material.appbar.ViewOffsetBehavior
        public /* bridge */ /* synthetic */ int getTopAndBottomOffset() {
            return super.getTopAndBottomOffset();
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.AbstractC0304c
        public boolean layoutDependsOn(CoordinatorLayout coordinatorLayout, View view, View view2) {
            return view2 instanceof AppBarLayout;
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.AbstractC0304c
        public boolean onDependentViewChanged(CoordinatorLayout coordinatorLayout, View view, View view2) {
            offsetChildAsNeeded(view, view2);
            updateLiftedStateIfNeeded(view, view2);
            return false;
        }

        @Override // com.google.android.material.appbar.ViewOffsetBehavior, androidx.coordinatorlayout.widget.CoordinatorLayout.AbstractC0304c
        public /* bridge */ /* synthetic */ boolean onLayoutChild(CoordinatorLayout coordinatorLayout, View view, int i9) {
            return super.onLayoutChild(coordinatorLayout, view, i9);
        }

        @Override // com.google.android.material.appbar.HeaderScrollingViewBehavior, androidx.coordinatorlayout.widget.CoordinatorLayout.AbstractC0304c
        public /* bridge */ /* synthetic */ boolean onMeasureChild(CoordinatorLayout coordinatorLayout, View view, int i9, int i10, int i11, int i12) {
            return super.onMeasureChild(coordinatorLayout, view, i9, i10, i11, i12);
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.AbstractC0304c
        public boolean onRequestChildRectangleOnScreen(CoordinatorLayout coordinatorLayout, View view, Rect rect, boolean z8) {
            AppBarLayout appBarLayoutFindFirstDependency = findFirstDependency(coordinatorLayout.getDependencies(view));
            if (appBarLayoutFindFirstDependency != null) {
                rect.offset(view.getLeft(), view.getTop());
                Rect rect2 = this.tempRect1;
                rect2.set(0, 0, coordinatorLayout.getWidth(), coordinatorLayout.getHeight());
                if (!rect2.contains(rect)) {
                    appBarLayoutFindFirstDependency.setExpanded(false, !z8);
                    return true;
                }
            }
            return false;
        }

        @Override // com.google.android.material.appbar.ViewOffsetBehavior
        public /* bridge */ /* synthetic */ boolean setLeftAndRightOffset(int i9) {
            return super.setLeftAndRightOffset(i9);
        }

        @Override // com.google.android.material.appbar.ViewOffsetBehavior
        public /* bridge */ /* synthetic */ boolean setTopAndBottomOffset(int i9) {
            return super.setTopAndBottomOffset(i9);
        }

        public ScrollingViewBehavior(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C3476R.styleable.ScrollingViewBehavior_Layout);
            setOverlayTop(typedArrayObtainStyledAttributes.getDimensionPixelSize(C3476R.styleable.ScrollingViewBehavior_Layout_behavior_overlapTop, 0));
            typedArrayObtainStyledAttributes.recycle();
        }

        @Override // com.google.android.material.appbar.HeaderScrollingViewBehavior
        public AppBarLayout findFirstDependency(List<View> list) {
            int size = list.size();
            for (int i9 = 0; i9 < size; i9++) {
                View view = list.get(i9);
                if (view instanceof AppBarLayout) {
                    return (AppBarLayout) view;
                }
            }
            return null;
        }
    }

    public AppBarLayout(Context context) {
        this(context, null);
    }

    private boolean hasCollapsibleChild() {
        int childCount = getChildCount();
        for (int i9 = 0; i9 < childCount; i9++) {
            if (((LayoutParams) getChildAt(i9).getLayoutParams()).isCollapsible()) {
                return true;
            }
        }
        return false;
    }

    private void invalidateScrollRanges() {
        this.totalScrollRange = -1;
        this.downPreScrollRange = -1;
        this.downScrollRange = -1;
    }

    private boolean setLiftableState(boolean z8) {
        if (this.liftable == z8) {
            return false;
        }
        this.liftable = z8;
        refreshDrawableState();
        return true;
    }

    public void addOnOffsetChangedListener(BaseOnOffsetChangedListener baseOnOffsetChangedListener) {
        if (this.listeners == null) {
            this.listeners = new ArrayList();
        }
        if (baseOnOffsetChangedListener == null || this.listeners.contains(baseOnOffsetChangedListener)) {
            return;
        }
        this.listeners.add(baseOnOffsetChangedListener);
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup
    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams;
    }

    public void dispatchOffsetUpdates(int i9) {
        List<BaseOnOffsetChangedListener> list = this.listeners;
        if (list != null) {
            int size = list.size();
            for (int i10 = 0; i10 < size; i10++) {
                BaseOnOffsetChangedListener baseOnOffsetChangedListener = this.listeners.get(i10);
                if (baseOnOffsetChangedListener != null) {
                    baseOnOffsetChangedListener.onOffsetChanged(this, i9);
                }
            }
        }
    }

    public int getDownNestedPreScrollRange() {
        int i9 = this.downPreScrollRange;
        if (i9 != -1) {
            return i9;
        }
        int iM18568t = 0;
        for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
            View childAt = getChildAt(childCount);
            LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
            int measuredHeight = childAt.getMeasuredHeight();
            int i10 = layoutParams.scrollFlags;
            if ((i10 & 5) != 5) {
                if (iM18568t > 0) {
                    break;
                }
            } else {
                int i11 = iM18568t + ((LinearLayout.LayoutParams) layoutParams).topMargin + ((LinearLayout.LayoutParams) layoutParams).bottomMargin;
                iM18568t = (i10 & 8) != 0 ? i11 + C4647u.m18568t(childAt) : i11 + (measuredHeight - ((i10 & 2) != 0 ? C4647u.m18568t(childAt) : getTopInset()));
            }
        }
        int iMax = Math.max(0, iM18568t);
        this.downPreScrollRange = iMax;
        return iMax;
    }

    public int getDownNestedScrollRange() {
        int i9 = this.downScrollRange;
        if (i9 != -1) {
            return i9;
        }
        int childCount = getChildCount();
        int i10 = 0;
        int iM18568t = 0;
        while (true) {
            if (i10 >= childCount) {
                break;
            }
            View childAt = getChildAt(i10);
            LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
            int measuredHeight = childAt.getMeasuredHeight() + ((LinearLayout.LayoutParams) layoutParams).topMargin + ((LinearLayout.LayoutParams) layoutParams).bottomMargin;
            int i11 = layoutParams.scrollFlags;
            if ((i11 & 1) == 0) {
                break;
            }
            iM18568t += measuredHeight;
            if ((i11 & 2) != 0) {
                iM18568t -= C4647u.m18568t(childAt) + getTopInset();
                break;
            }
            i10++;
        }
        int iMax = Math.max(0, iM18568t);
        this.downScrollRange = iMax;
        return iMax;
    }

    public final int getMinimumHeightForVisibleOverlappingContent() {
        int topInset = getTopInset();
        int iM18568t = C4647u.m18568t(this);
        if (iM18568t == 0) {
            int childCount = getChildCount();
            iM18568t = childCount >= 1 ? C4647u.m18568t(getChildAt(childCount - 1)) : 0;
            if (iM18568t == 0) {
                return getHeight() / 3;
            }
        }
        return (iM18568t * 2) + topInset;
    }

    public int getPendingAction() {
        return this.pendingAction;
    }

    @Deprecated
    public float getTargetElevation() {
        return BitmapDescriptorFactory.HUE_RED;
    }

    public final int getTopInset() {
        C4628h0 c4628h0 = this.lastInsets;
        if (c4628h0 != null) {
            return c4628h0.m18439g();
        }
        return 0;
    }

    public final int getTotalScrollRange() {
        int i9 = this.totalScrollRange;
        if (i9 != -1) {
            return i9;
        }
        int childCount = getChildCount();
        int i10 = 0;
        int iM18568t = 0;
        while (true) {
            if (i10 >= childCount) {
                break;
            }
            View childAt = getChildAt(i10);
            LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
            int measuredHeight = childAt.getMeasuredHeight();
            int i11 = layoutParams.scrollFlags;
            if ((i11 & 1) == 0) {
                break;
            }
            iM18568t += measuredHeight + ((LinearLayout.LayoutParams) layoutParams).topMargin + ((LinearLayout.LayoutParams) layoutParams).bottomMargin;
            if ((i11 & 2) != 0) {
                iM18568t -= C4647u.m18568t(childAt);
                break;
            }
            i10++;
        }
        int iMax = Math.max(0, iM18568t - getTopInset());
        this.totalScrollRange = iMax;
        return iMax;
    }

    public int getUpNestedPreScrollRange() {
        return getTotalScrollRange();
    }

    public boolean hasChildWithInterpolator() {
        return this.haveChildWithInterpolator;
    }

    public boolean hasScrollableChildren() {
        return getTotalScrollRange() != 0;
    }

    public boolean isLiftOnScroll() {
        return this.liftOnScroll;
    }

    @Override // android.view.ViewGroup, android.view.View
    public int[] onCreateDrawableState(int i9) {
        if (this.tmpStatesArray == null) {
            this.tmpStatesArray = new int[4];
        }
        int[] iArr = this.tmpStatesArray;
        int[] iArrOnCreateDrawableState = super.onCreateDrawableState(i9 + iArr.length);
        boolean z8 = this.liftable;
        int i10 = C3476R.attr.state_liftable;
        if (!z8) {
            i10 = -i10;
        }
        iArr[0] = i10;
        iArr[1] = (z8 && this.lifted) ? C3476R.attr.state_lifted : -C3476R.attr.state_lifted;
        int i11 = C3476R.attr.state_collapsible;
        if (!z8) {
            i11 = -i11;
        }
        iArr[2] = i11;
        iArr[3] = (z8 && this.lifted) ? C3476R.attr.state_collapsed : -C3476R.attr.state_collapsed;
        return View.mergeDrawableStates(iArrOnCreateDrawableState, iArr);
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z8, int i9, int i10, int i11, int i12) {
        super.onLayout(z8, i9, i10, i11, i12);
        invalidateScrollRanges();
        this.haveChildWithInterpolator = false;
        int childCount = getChildCount();
        int i13 = 0;
        while (true) {
            if (i13 >= childCount) {
                break;
            }
            if (((LayoutParams) getChildAt(i13).getLayoutParams()).getScrollInterpolator() != null) {
                this.haveChildWithInterpolator = true;
                break;
            }
            i13++;
        }
        if (this.liftableOverride) {
            return;
        }
        setLiftableState(this.liftOnScroll || hasCollapsibleChild());
    }

    @Override // android.widget.LinearLayout, android.view.View
    public void onMeasure(int i9, int i10) {
        super.onMeasure(i9, i10);
        invalidateScrollRanges();
    }

    public C4628h0 onWindowInsetChanged(C4628h0 c4628h0) {
        C4628h0 c4628h02 = C4647u.m18561p(this) ? c4628h0 : null;
        if (!C0697c.m3461a(this.lastInsets, c4628h02)) {
            this.lastInsets = c4628h02;
            invalidateScrollRanges();
        }
        return c4628h0;
    }

    public void removeOnOffsetChangedListener(BaseOnOffsetChangedListener baseOnOffsetChangedListener) {
        List<BaseOnOffsetChangedListener> list = this.listeners;
        if (list == null || baseOnOffsetChangedListener == null) {
            return;
        }
        list.remove(baseOnOffsetChangedListener);
    }

    public void resetPendingAction() {
        this.pendingAction = 0;
    }

    public void setExpanded(boolean z8) {
        setExpanded(z8, C4647u.m18513I(this));
    }

    public void setLiftOnScroll(boolean z8) {
        this.liftOnScroll = z8;
    }

    public boolean setLiftable(boolean z8) {
        this.liftableOverride = true;
        return setLiftableState(z8);
    }

    public boolean setLifted(boolean z8) {
        return setLiftedState(z8);
    }

    public boolean setLiftedState(boolean z8) {
        if (this.lifted == z8) {
            return false;
        }
        this.lifted = z8;
        refreshDrawableState();
        return true;
    }

    @Override // android.widget.LinearLayout
    public void setOrientation(int i9) {
        if (i9 != 1) {
            throw new IllegalArgumentException("AppBarLayout is always vertical and does not support horizontal orientation");
        }
        super.setOrientation(i9);
    }

    @Deprecated
    public void setTargetElevation(float f9) throws Resources.NotFoundException {
        ViewUtilsLollipop.setDefaultAppBarLayoutStateListAnimator(this, f9);
    }

    public AppBarLayout(Context context, AttributeSet attributeSet) throws Resources.NotFoundException {
        super(context, attributeSet);
        this.totalScrollRange = -1;
        this.downPreScrollRange = -1;
        this.downScrollRange = -1;
        this.pendingAction = 0;
        setOrientation(1);
        ViewUtilsLollipop.setBoundsViewOutlineProvider(this);
        int i9 = C3476R.style.Widget_Design_AppBarLayout;
        ViewUtilsLollipop.setStateListAnimatorFromAttrs(this, attributeSet, 0, i9);
        TypedArray typedArrayObtainStyledAttributes = ThemeEnforcement.obtainStyledAttributes(context, attributeSet, C3476R.styleable.AppBarLayout, 0, i9, new int[0]);
        C4647u.m18534b0(this, typedArrayObtainStyledAttributes.getDrawable(C3476R.styleable.AppBarLayout_android_background));
        int i10 = C3476R.styleable.AppBarLayout_expanded;
        if (typedArrayObtainStyledAttributes.hasValue(i10)) {
            setExpanded(typedArrayObtainStyledAttributes.getBoolean(i10, false), false, false);
        }
        if (typedArrayObtainStyledAttributes.hasValue(C3476R.styleable.AppBarLayout_elevation)) {
            ViewUtilsLollipop.setDefaultAppBarLayoutStateListAnimator(this, typedArrayObtainStyledAttributes.getDimensionPixelSize(r10, 0));
        }
        int i11 = C3476R.styleable.AppBarLayout_android_keyboardNavigationCluster;
        if (typedArrayObtainStyledAttributes.hasValue(i11)) {
            setKeyboardNavigationCluster(typedArrayObtainStyledAttributes.getBoolean(i11, false));
        }
        int i12 = C3476R.styleable.AppBarLayout_android_touchscreenBlocksFocus;
        if (typedArrayObtainStyledAttributes.hasValue(i12)) {
            setTouchscreenBlocksFocus(typedArrayObtainStyledAttributes.getBoolean(i12, false));
        }
        this.liftOnScroll = typedArrayObtainStyledAttributes.getBoolean(C3476R.styleable.AppBarLayout_liftOnScroll, false);
        typedArrayObtainStyledAttributes.recycle();
        C4647u.m18554l0(this, new InterfaceC4643q() { // from class: com.google.android.material.appbar.AppBarLayout.1
            @Override // p042d0.InterfaceC4643q
            public C4628h0 onApplyWindowInsets(View view, C4628h0 c4628h0) {
                return AppBarLayout.this.onWindowInsetChanged(c4628h0);
            }
        });
    }

    public void setExpanded(boolean z8, boolean z9) {
        setExpanded(z8, z9, true);
    }

    private void setExpanded(boolean z8, boolean z9, boolean z10) {
        this.pendingAction = (z8 ? 1 : 2) | (z9 ? 4 : 0) | (z10 ? 8 : 0);
        requestLayout();
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup
    public LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-1, -2);
    }

    public void removeOnOffsetChangedListener(OnOffsetChangedListener onOffsetChangedListener) {
        removeOnOffsetChangedListener((BaseOnOffsetChangedListener) onOffsetChangedListener);
    }

    public void addOnOffsetChangedListener(OnOffsetChangedListener onOffsetChangedListener) {
        addOnOffsetChangedListener((BaseOnOffsetChangedListener) onOffsetChangedListener);
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup
    public LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup
    public LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        if (layoutParams instanceof LinearLayout.LayoutParams) {
            return new LayoutParams((LinearLayout.LayoutParams) layoutParams);
        }
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            return new LayoutParams((ViewGroup.MarginLayoutParams) layoutParams);
        }
        return new LayoutParams(layoutParams);
    }

    public static class LayoutParams extends LinearLayout.LayoutParams {
        static final int COLLAPSIBLE_FLAGS = 10;
        static final int FLAG_QUICK_RETURN = 5;
        static final int FLAG_SNAP = 17;
        public static final int SCROLL_FLAG_ENTER_ALWAYS = 4;
        public static final int SCROLL_FLAG_ENTER_ALWAYS_COLLAPSED = 8;
        public static final int SCROLL_FLAG_EXIT_UNTIL_COLLAPSED = 2;
        public static final int SCROLL_FLAG_SCROLL = 1;
        public static final int SCROLL_FLAG_SNAP = 16;
        public static final int SCROLL_FLAG_SNAP_MARGINS = 32;
        int scrollFlags;
        Interpolator scrollInterpolator;

        @Retention(RetentionPolicy.SOURCE)
        public @interface ScrollFlags {
        }

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.scrollFlags = 1;
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C3476R.styleable.AppBarLayout_Layout);
            this.scrollFlags = typedArrayObtainStyledAttributes.getInt(C3476R.styleable.AppBarLayout_Layout_layout_scrollFlags, 0);
            int i9 = C3476R.styleable.AppBarLayout_Layout_layout_scrollInterpolator;
            if (typedArrayObtainStyledAttributes.hasValue(i9)) {
                this.scrollInterpolator = android.view.animation.AnimationUtils.loadInterpolator(context, typedArrayObtainStyledAttributes.getResourceId(i9, 0));
            }
            typedArrayObtainStyledAttributes.recycle();
        }

        public int getScrollFlags() {
            return this.scrollFlags;
        }

        public Interpolator getScrollInterpolator() {
            return this.scrollInterpolator;
        }

        public boolean isCollapsible() {
            int i9 = this.scrollFlags;
            return (i9 & 1) == 1 && (i9 & 10) != 0;
        }

        public void setScrollFlags(int i9) {
            this.scrollFlags = i9;
        }

        public void setScrollInterpolator(Interpolator interpolator) {
            this.scrollInterpolator = interpolator;
        }

        public LayoutParams(int i9, int i10) {
            super(i9, i10);
            this.scrollFlags = 1;
        }

        public LayoutParams(int i9, int i10, float f9) {
            super(i9, i10, f9);
            this.scrollFlags = 1;
        }

        public LayoutParams(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.scrollFlags = 1;
        }

        public LayoutParams(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
            this.scrollFlags = 1;
        }

        public LayoutParams(LinearLayout.LayoutParams layoutParams) {
            super(layoutParams);
            this.scrollFlags = 1;
        }

        public LayoutParams(LayoutParams layoutParams) {
            super((LinearLayout.LayoutParams) layoutParams);
            this.scrollFlags = 1;
            this.scrollFlags = layoutParams.scrollFlags;
            this.scrollInterpolator = layoutParams.scrollInterpolator;
        }
    }
}
