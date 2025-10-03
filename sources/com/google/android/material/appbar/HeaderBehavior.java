package com.google.android.material.appbar;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.OverScroller;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.gms.auth.api.credentials.CredentialsApi;
import p042d0.C4647u;
import p242y.C6587a;

/* loaded from: classes2.dex */
abstract class HeaderBehavior<V extends View> extends ViewOffsetBehavior<V> {
    private static final int INVALID_POINTER = -1;
    private int activePointerId;
    private Runnable flingRunnable;
    private boolean isBeingDragged;
    private int lastMotionY;
    OverScroller scroller;
    private int touchSlop;
    private VelocityTracker velocityTracker;

    public class FlingRunnable implements Runnable {
        private final V layout;
        private final CoordinatorLayout parent;

        public FlingRunnable(CoordinatorLayout coordinatorLayout, V v8) {
            this.parent = coordinatorLayout;
            this.layout = v8;
        }

        @Override // java.lang.Runnable
        public void run() {
            OverScroller overScroller;
            if (this.layout == null || (overScroller = HeaderBehavior.this.scroller) == null) {
                return;
            }
            if (!overScroller.computeScrollOffset()) {
                HeaderBehavior.this.onFlingFinished(this.parent, this.layout);
                return;
            }
            HeaderBehavior headerBehavior = HeaderBehavior.this;
            headerBehavior.setHeaderTopBottomOffset(this.parent, this.layout, headerBehavior.scroller.getCurrY());
            C4647u.m18525U(this.layout, this);
        }
    }

    public HeaderBehavior() {
        this.activePointerId = -1;
        this.touchSlop = -1;
    }

    private void ensureVelocityTracker() {
        if (this.velocityTracker == null) {
            this.velocityTracker = VelocityTracker.obtain();
        }
    }

    public boolean canDragView(V v8) {
        return false;
    }

    public final boolean fling(CoordinatorLayout coordinatorLayout, V v8, int i9, int i10, float f9) {
        Runnable runnable = this.flingRunnable;
        if (runnable != null) {
            v8.removeCallbacks(runnable);
            this.flingRunnable = null;
        }
        if (this.scroller == null) {
            this.scroller = new OverScroller(v8.getContext());
        }
        this.scroller.fling(0, getTopAndBottomOffset(), 0, Math.round(f9), 0, 0, i9, i10);
        if (!this.scroller.computeScrollOffset()) {
            onFlingFinished(coordinatorLayout, v8);
            return false;
        }
        FlingRunnable flingRunnable = new FlingRunnable(coordinatorLayout, v8);
        this.flingRunnable = flingRunnable;
        C4647u.m18525U(v8, flingRunnable);
        return true;
    }

    public int getMaxDragOffset(V v8) {
        return -v8.getHeight();
    }

    public int getScrollRangeForDragFling(V v8) {
        return v8.getHeight();
    }

    public int getTopBottomOffsetForScrollingSibling() {
        return getTopAndBottomOffset();
    }

    public void onFlingFinished(CoordinatorLayout coordinatorLayout, V v8) {
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x0051  */
    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.AbstractC0304c
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public boolean onInterceptTouchEvent(CoordinatorLayout coordinatorLayout, V v8, MotionEvent motionEvent) {
        int iFindPointerIndex;
        if (this.touchSlop < 0) {
            this.touchSlop = ViewConfiguration.get(coordinatorLayout.getContext()).getScaledTouchSlop();
        }
        if (motionEvent.getAction() == 2 && this.isBeingDragged) {
            return true;
        }
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            this.isBeingDragged = false;
            int x8 = (int) motionEvent.getX();
            int y8 = (int) motionEvent.getY();
            if (canDragView(v8) && coordinatorLayout.isPointInChildBounds(v8, x8, y8)) {
                this.lastMotionY = y8;
                this.activePointerId = motionEvent.getPointerId(0);
                ensureVelocityTracker();
            }
        } else if (actionMasked == 1) {
            this.isBeingDragged = false;
            this.activePointerId = -1;
            VelocityTracker velocityTracker = this.velocityTracker;
            if (velocityTracker != null) {
                velocityTracker.recycle();
                this.velocityTracker = null;
            }
        } else if (actionMasked == 2) {
            int i9 = this.activePointerId;
            if (i9 != -1 && (iFindPointerIndex = motionEvent.findPointerIndex(i9)) != -1) {
                int y9 = (int) motionEvent.getY(iFindPointerIndex);
                if (Math.abs(y9 - this.lastMotionY) > this.touchSlop) {
                    this.isBeingDragged = true;
                    this.lastMotionY = y9;
                }
            }
        } else if (actionMasked == 3) {
        }
        VelocityTracker velocityTracker2 = this.velocityTracker;
        if (velocityTracker2 != null) {
            velocityTracker2.addMovement(motionEvent);
        }
        return this.isBeingDragged;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.AbstractC0304c
    public boolean onTouchEvent(CoordinatorLayout coordinatorLayout, V v8, MotionEvent motionEvent) {
        if (this.touchSlop < 0) {
            this.touchSlop = ViewConfiguration.get(coordinatorLayout.getContext()).getScaledTouchSlop();
        }
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked != 0) {
            if (actionMasked == 1) {
                VelocityTracker velocityTracker = this.velocityTracker;
                if (velocityTracker != null) {
                    velocityTracker.addMovement(motionEvent);
                    this.velocityTracker.computeCurrentVelocity(CredentialsApi.ACTIVITY_RESULT_ADD_ACCOUNT);
                    fling(coordinatorLayout, v8, -getScrollRangeForDragFling(v8), 0, this.velocityTracker.getYVelocity(this.activePointerId));
                }
            } else if (actionMasked == 2) {
                int iFindPointerIndex = motionEvent.findPointerIndex(this.activePointerId);
                if (iFindPointerIndex == -1) {
                    return false;
                }
                int y8 = (int) motionEvent.getY(iFindPointerIndex);
                int i9 = this.lastMotionY - y8;
                if (!this.isBeingDragged) {
                    int iAbs = Math.abs(i9);
                    int i10 = this.touchSlop;
                    if (iAbs > i10) {
                        this.isBeingDragged = true;
                        i9 = i9 > 0 ? i9 - i10 : i9 + i10;
                    }
                }
                int i11 = i9;
                if (this.isBeingDragged) {
                    this.lastMotionY = y8;
                    scroll(coordinatorLayout, v8, i11, getMaxDragOffset(v8), 0);
                }
            } else if (actionMasked == 3) {
            }
            this.isBeingDragged = false;
            this.activePointerId = -1;
            VelocityTracker velocityTracker2 = this.velocityTracker;
            if (velocityTracker2 != null) {
                velocityTracker2.recycle();
                this.velocityTracker = null;
            }
        } else {
            int x8 = (int) motionEvent.getX();
            int y9 = (int) motionEvent.getY();
            if (!coordinatorLayout.isPointInChildBounds(v8, x8, y9) || !canDragView(v8)) {
                return false;
            }
            this.lastMotionY = y9;
            this.activePointerId = motionEvent.getPointerId(0);
            ensureVelocityTracker();
        }
        VelocityTracker velocityTracker3 = this.velocityTracker;
        if (velocityTracker3 != null) {
            velocityTracker3.addMovement(motionEvent);
        }
        return true;
    }

    public final int scroll(CoordinatorLayout coordinatorLayout, V v8, int i9, int i10, int i11) {
        return setHeaderTopBottomOffset(coordinatorLayout, v8, getTopBottomOffsetForScrollingSibling() - i9, i10, i11);
    }

    public int setHeaderTopBottomOffset(CoordinatorLayout coordinatorLayout, V v8, int i9) {
        return setHeaderTopBottomOffset(coordinatorLayout, v8, i9, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public int setHeaderTopBottomOffset(CoordinatorLayout coordinatorLayout, V v8, int i9, int i10, int i11) {
        int iM25200b;
        int topAndBottomOffset = getTopAndBottomOffset();
        if (i10 == 0 || topAndBottomOffset < i10 || topAndBottomOffset > i11 || topAndBottomOffset == (iM25200b = C6587a.m25200b(i9, i10, i11))) {
            return 0;
        }
        setTopAndBottomOffset(iM25200b);
        return topAndBottomOffset - iM25200b;
    }

    public HeaderBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.activePointerId = -1;
        this.touchSlop = -1;
    }
}
