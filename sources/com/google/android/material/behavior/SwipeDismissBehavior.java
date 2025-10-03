package com.google.android.material.behavior;

import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.customview.widget.C0342c;
import p042d0.C4647u;

/* loaded from: classes2.dex */
public class SwipeDismissBehavior<V extends View> extends CoordinatorLayout.AbstractC0304c<V> {
    private static final float DEFAULT_ALPHA_END_DISTANCE = 0.5f;
    private static final float DEFAULT_ALPHA_START_DISTANCE = 0.0f;
    private static final float DEFAULT_DRAG_DISMISS_THRESHOLD = 0.5f;
    public static final int STATE_DRAGGING = 1;
    public static final int STATE_IDLE = 0;
    public static final int STATE_SETTLING = 2;
    public static final int SWIPE_DIRECTION_ANY = 2;
    public static final int SWIPE_DIRECTION_END_TO_START = 1;
    public static final int SWIPE_DIRECTION_START_TO_END = 0;
    private boolean interceptingEvents;
    OnDismissListener listener;
    private boolean sensitivitySet;
    C0342c viewDragHelper;
    private float sensitivity = 0.0f;
    int swipeDirection = 2;
    float dragDismissThreshold = 0.5f;
    float alphaStartSwipeDistance = 0.0f;
    float alphaEndSwipeDistance = 0.5f;
    private final C0342c.c dragCallback = new C0342c.c() { // from class: com.google.android.material.behavior.SwipeDismissBehavior.1
        private static final int INVALID_POINTER_ID = -1;
        private int activePointerId = -1;
        private int originalCapturedViewLeft;

        private boolean shouldDismiss(View view, float f9) {
            if (f9 == 0.0f) {
                return Math.abs(view.getLeft() - this.originalCapturedViewLeft) >= Math.round(((float) view.getWidth()) * SwipeDismissBehavior.this.dragDismissThreshold);
            }
            boolean z8 = C4647u.m18567s(view) == 1;
            int i9 = SwipeDismissBehavior.this.swipeDirection;
            if (i9 == 2) {
                return true;
            }
            if (i9 == 0) {
                if (z8) {
                    if (f9 >= 0.0f) {
                        return false;
                    }
                } else if (f9 <= 0.0f) {
                    return false;
                }
                return true;
            }
            if (i9 != 1) {
                return false;
            }
            if (z8) {
                if (f9 <= 0.0f) {
                    return false;
                }
            } else if (f9 >= 0.0f) {
                return false;
            }
            return true;
        }

        @Override // androidx.customview.widget.C0342c.c
        public int clampViewPositionHorizontal(View view, int i9, int i10) {
            int width;
            int width2;
            int width3;
            boolean z8 = C4647u.m18567s(view) == 1;
            int i11 = SwipeDismissBehavior.this.swipeDirection;
            if (i11 == 0) {
                if (z8) {
                    width = this.originalCapturedViewLeft - view.getWidth();
                    width2 = this.originalCapturedViewLeft;
                } else {
                    width = this.originalCapturedViewLeft;
                    width3 = view.getWidth();
                    width2 = width3 + width;
                }
            } else if (i11 != 1) {
                width = this.originalCapturedViewLeft - view.getWidth();
                width2 = view.getWidth() + this.originalCapturedViewLeft;
            } else if (z8) {
                width = this.originalCapturedViewLeft;
                width3 = view.getWidth();
                width2 = width3 + width;
            } else {
                width = this.originalCapturedViewLeft - view.getWidth();
                width2 = this.originalCapturedViewLeft;
            }
            return SwipeDismissBehavior.clamp(width, i9, width2);
        }

        @Override // androidx.customview.widget.C0342c.c
        public int clampViewPositionVertical(View view, int i9, int i10) {
            return view.getTop();
        }

        @Override // androidx.customview.widget.C0342c.c
        public int getViewHorizontalDragRange(View view) {
            return view.getWidth();
        }

        @Override // androidx.customview.widget.C0342c.c
        public void onViewCaptured(View view, int i9) {
            this.activePointerId = i9;
            this.originalCapturedViewLeft = view.getLeft();
            ViewParent parent = view.getParent();
            if (parent != null) {
                parent.requestDisallowInterceptTouchEvent(true);
            }
        }

        @Override // androidx.customview.widget.C0342c.c
        public void onViewDragStateChanged(int i9) {
            OnDismissListener onDismissListener = SwipeDismissBehavior.this.listener;
            if (onDismissListener != null) {
                onDismissListener.onDragStateChanged(i9);
            }
        }

        @Override // androidx.customview.widget.C0342c.c
        public void onViewPositionChanged(View view, int i9, int i10, int i11, int i12) {
            float width = this.originalCapturedViewLeft + (view.getWidth() * SwipeDismissBehavior.this.alphaStartSwipeDistance);
            float width2 = this.originalCapturedViewLeft + (view.getWidth() * SwipeDismissBehavior.this.alphaEndSwipeDistance);
            float f9 = i9;
            if (f9 <= width) {
                view.setAlpha(1.0f);
            } else if (f9 >= width2) {
                view.setAlpha(0.0f);
            } else {
                view.setAlpha(SwipeDismissBehavior.clamp(0.0f, 1.0f - SwipeDismissBehavior.fraction(width, width2, f9), 1.0f));
            }
        }

        @Override // androidx.customview.widget.C0342c.c
        public void onViewReleased(View view, float f9, float f10) {
            int i9;
            boolean z8;
            OnDismissListener onDismissListener;
            this.activePointerId = -1;
            int width = view.getWidth();
            if (shouldDismiss(view, f9)) {
                int left = view.getLeft();
                int i10 = this.originalCapturedViewLeft;
                i9 = left < i10 ? i10 - width : i10 + width;
                z8 = true;
            } else {
                i9 = this.originalCapturedViewLeft;
                z8 = false;
            }
            if (SwipeDismissBehavior.this.viewDragHelper.m1671N(i9, view.getTop())) {
                C4647u.m18525U(view, new SettleRunnable(view, z8));
            } else {
                if (!z8 || (onDismissListener = SwipeDismissBehavior.this.listener) == null) {
                    return;
                }
                onDismissListener.onDismiss(view);
            }
        }

        @Override // androidx.customview.widget.C0342c.c
        public boolean tryCaptureView(View view, int i9) {
            return this.activePointerId == -1 && SwipeDismissBehavior.this.canSwipeDismissView(view);
        }
    };

    public interface OnDismissListener {
        void onDismiss(View view);

        void onDragStateChanged(int i9);
    }

    public class SettleRunnable implements Runnable {
        private final boolean dismiss;
        private final View view;

        public SettleRunnable(View view, boolean z8) {
            this.view = view;
            this.dismiss = z8;
        }

        @Override // java.lang.Runnable
        public void run() {
            OnDismissListener onDismissListener;
            C0342c c0342c = SwipeDismissBehavior.this.viewDragHelper;
            if (c0342c != null && c0342c.m1688n(true)) {
                C4647u.m18525U(this.view, this);
            } else {
                if (!this.dismiss || (onDismissListener = SwipeDismissBehavior.this.listener) == null) {
                    return;
                }
                onDismissListener.onDismiss(this.view);
            }
        }
    }

    public static float clamp(float f9, float f10, float f11) {
        return Math.min(Math.max(f9, f10), f11);
    }

    private void ensureViewDragHelper(ViewGroup viewGroup) {
        if (this.viewDragHelper == null) {
            this.viewDragHelper = this.sensitivitySet ? C0342c.m1656o(viewGroup, this.sensitivity, this.dragCallback) : C0342c.m1657p(viewGroup, this.dragCallback);
        }
    }

    public static float fraction(float f9, float f10, float f11) {
        return (f11 - f9) / (f10 - f9);
    }

    public boolean canSwipeDismissView(View view) {
        return true;
    }

    public int getDragState() {
        C0342c c0342c = this.viewDragHelper;
        if (c0342c != null) {
            return c0342c.m1658A();
        }
        return 0;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.AbstractC0304c
    public boolean onInterceptTouchEvent(CoordinatorLayout coordinatorLayout, V v8, MotionEvent motionEvent) {
        boolean zIsPointInChildBounds = this.interceptingEvents;
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            zIsPointInChildBounds = coordinatorLayout.isPointInChildBounds(v8, (int) motionEvent.getX(), (int) motionEvent.getY());
            this.interceptingEvents = zIsPointInChildBounds;
        } else if (actionMasked == 1 || actionMasked == 3) {
            this.interceptingEvents = false;
        }
        if (!zIsPointInChildBounds) {
            return false;
        }
        ensureViewDragHelper(coordinatorLayout);
        return this.viewDragHelper.m1672O(motionEvent);
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.AbstractC0304c
    public boolean onTouchEvent(CoordinatorLayout coordinatorLayout, V v8, MotionEvent motionEvent) {
        C0342c c0342c = this.viewDragHelper;
        if (c0342c == null) {
            return false;
        }
        c0342c.m1663F(motionEvent);
        return true;
    }

    public void setDragDismissDistance(float f9) {
        this.dragDismissThreshold = clamp(0.0f, f9, 1.0f);
    }

    public void setEndAlphaSwipeDistance(float f9) {
        this.alphaEndSwipeDistance = clamp(0.0f, f9, 1.0f);
    }

    public void setListener(OnDismissListener onDismissListener) {
        this.listener = onDismissListener;
    }

    public void setSensitivity(float f9) {
        this.sensitivity = f9;
        this.sensitivitySet = true;
    }

    public void setStartAlphaSwipeDistance(float f9) {
        this.alphaStartSwipeDistance = clamp(0.0f, f9, 1.0f);
    }

    public void setSwipeDirection(int i9) {
        this.swipeDirection = i9;
    }

    public static int clamp(int i9, int i10, int i11) {
        return Math.min(Math.max(i9, i10), i11);
    }
}
