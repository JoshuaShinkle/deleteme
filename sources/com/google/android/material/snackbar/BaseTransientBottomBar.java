package com.google.android.material.snackbar;

import android.accessibilityservice.AccessibilityServiceInfo;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityManager;
import android.widget.FrameLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.material.C3476R;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.behavior.SwipeDismissBehavior;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.SnackbarManager;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;
import p042d0.C4613a;
import p042d0.C4628h0;
import p042d0.C4647u;
import p042d0.InterfaceC4643q;
import p052e0.C4694c;
import p052e0.C4704m;

/* loaded from: classes2.dex */
public abstract class BaseTransientBottomBar<B extends BaseTransientBottomBar<B>> {
    static final int ANIMATION_DURATION = 250;
    static final int ANIMATION_FADE_DURATION = 180;
    public static final int LENGTH_INDEFINITE = -2;
    public static final int LENGTH_LONG = 0;
    public static final int LENGTH_SHORT = -1;
    static final int MSG_DISMISS = 1;
    static final int MSG_SHOW = 0;
    private final AccessibilityManager accessibilityManager;
    private Behavior behavior;
    private List<BaseCallback<B>> callbacks;
    private final com.google.android.material.snackbar.ContentViewCallback contentViewCallback;
    private final Context context;
    private int duration;
    final SnackbarManager.Callback managerCallback = new SnackbarManager.Callback() { // from class: com.google.android.material.snackbar.BaseTransientBottomBar.4
        @Override // com.google.android.material.snackbar.SnackbarManager.Callback
        public void dismiss(int i9) {
            Handler handler2 = BaseTransientBottomBar.handler;
            handler2.sendMessage(handler2.obtainMessage(1, i9, 0, BaseTransientBottomBar.this));
        }

        @Override // com.google.android.material.snackbar.SnackbarManager.Callback
        public void show() {
            Handler handler2 = BaseTransientBottomBar.handler;
            handler2.sendMessage(handler2.obtainMessage(0, BaseTransientBottomBar.this));
        }
    };
    private final ViewGroup targetParent;
    protected final SnackbarBaseLayout view;
    private static final boolean USE_OFFSET_API = false;
    private static final int[] SNACKBAR_STYLE_ATTR = {C3476R.attr.snackbarStyle};
    static final Handler handler = new Handler(Looper.getMainLooper(), new Handler.Callback() { // from class: com.google.android.material.snackbar.BaseTransientBottomBar.1
        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            int i9 = message.what;
            if (i9 == 0) {
                ((BaseTransientBottomBar) message.obj).showView();
                return true;
            }
            if (i9 != 1) {
                return false;
            }
            ((BaseTransientBottomBar) message.obj).hideView(message.arg1);
            return true;
        }
    });

    public static abstract class BaseCallback<B> {
        public static final int DISMISS_EVENT_ACTION = 1;
        public static final int DISMISS_EVENT_CONSECUTIVE = 4;
        public static final int DISMISS_EVENT_MANUAL = 3;
        public static final int DISMISS_EVENT_SWIPE = 0;
        public static final int DISMISS_EVENT_TIMEOUT = 2;

        @Retention(RetentionPolicy.SOURCE)
        public @interface DismissEvent {
        }

        public void onDismissed(B b9, int i9) {
        }

        public void onShown(B b9) {
        }
    }

    public static class Behavior extends SwipeDismissBehavior<View> {
        private final BehaviorDelegate delegate = new BehaviorDelegate(this);

        /* JADX INFO: Access modifiers changed from: private */
        public void setBaseTransientBottomBar(BaseTransientBottomBar<?> baseTransientBottomBar) {
            this.delegate.setBaseTransientBottomBar(baseTransientBottomBar);
        }

        @Override // com.google.android.material.behavior.SwipeDismissBehavior
        public boolean canSwipeDismissView(View view) {
            return this.delegate.canSwipeDismissView(view);
        }

        @Override // com.google.android.material.behavior.SwipeDismissBehavior, androidx.coordinatorlayout.widget.CoordinatorLayout.AbstractC0304c
        public boolean onInterceptTouchEvent(CoordinatorLayout coordinatorLayout, View view, MotionEvent motionEvent) {
            this.delegate.onInterceptTouchEvent(coordinatorLayout, view, motionEvent);
            return super.onInterceptTouchEvent(coordinatorLayout, view, motionEvent);
        }
    }

    public static class BehaviorDelegate {
        private SnackbarManager.Callback managerCallback;

        public BehaviorDelegate(SwipeDismissBehavior<?> swipeDismissBehavior) {
            swipeDismissBehavior.setStartAlphaSwipeDistance(0.1f);
            swipeDismissBehavior.setEndAlphaSwipeDistance(0.6f);
            swipeDismissBehavior.setSwipeDirection(0);
        }

        public boolean canSwipeDismissView(View view) {
            return view instanceof SnackbarBaseLayout;
        }

        public void onInterceptTouchEvent(CoordinatorLayout coordinatorLayout, View view, MotionEvent motionEvent) {
            int actionMasked = motionEvent.getActionMasked();
            if (actionMasked == 0) {
                if (coordinatorLayout.isPointInChildBounds(view, (int) motionEvent.getX(), (int) motionEvent.getY())) {
                    SnackbarManager.getInstance().pauseTimeout(this.managerCallback);
                }
            } else if (actionMasked == 1 || actionMasked == 3) {
                SnackbarManager.getInstance().restoreTimeoutIfPaused(this.managerCallback);
            }
        }

        public void setBaseTransientBottomBar(BaseTransientBottomBar<?> baseTransientBottomBar) {
            this.managerCallback = baseTransientBottomBar.managerCallback;
        }
    }

    @Deprecated
    public interface ContentViewCallback extends com.google.android.material.snackbar.ContentViewCallback {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface Duration {
    }

    public interface OnAttachStateChangeListener {
        void onViewAttachedToWindow(View view);

        void onViewDetachedFromWindow(View view);
    }

    public interface OnLayoutChangeListener {
        void onLayoutChange(View view, int i9, int i10, int i11, int i12);
    }

    public static class SnackbarBaseLayout extends FrameLayout {
        private final AccessibilityManager accessibilityManager;
        private OnAttachStateChangeListener onAttachStateChangeListener;
        private OnLayoutChangeListener onLayoutChangeListener;
        private final C4694c.a touchExplorationStateChangeListener;

        public SnackbarBaseLayout(Context context) {
            this(context, null);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setClickableOrFocusableBasedOnAccessibility(boolean z8) {
            setClickable(!z8);
            setFocusable(z8);
        }

        @Override // android.view.ViewGroup, android.view.View
        public void onAttachedToWindow() {
            super.onAttachedToWindow();
            OnAttachStateChangeListener onAttachStateChangeListener = this.onAttachStateChangeListener;
            if (onAttachStateChangeListener != null) {
                onAttachStateChangeListener.onViewAttachedToWindow(this);
            }
            C4647u.m18527W(this);
        }

        @Override // android.view.ViewGroup, android.view.View
        public void onDetachedFromWindow() {
            super.onDetachedFromWindow();
            OnAttachStateChangeListener onAttachStateChangeListener = this.onAttachStateChangeListener;
            if (onAttachStateChangeListener != null) {
                onAttachStateChangeListener.onViewDetachedFromWindow(this);
            }
            C4694c.m18748b(this.accessibilityManager, this.touchExplorationStateChangeListener);
        }

        @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
        public void onLayout(boolean z8, int i9, int i10, int i11, int i12) {
            super.onLayout(z8, i9, i10, i11, i12);
            OnLayoutChangeListener onLayoutChangeListener = this.onLayoutChangeListener;
            if (onLayoutChangeListener != null) {
                onLayoutChangeListener.onLayoutChange(this, i9, i10, i11, i12);
            }
        }

        public void setOnAttachStateChangeListener(OnAttachStateChangeListener onAttachStateChangeListener) {
            this.onAttachStateChangeListener = onAttachStateChangeListener;
        }

        public void setOnLayoutChangeListener(OnLayoutChangeListener onLayoutChangeListener) {
            this.onLayoutChangeListener = onLayoutChangeListener;
        }

        public SnackbarBaseLayout(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C3476R.styleable.SnackbarLayout);
            if (typedArrayObtainStyledAttributes.hasValue(C3476R.styleable.SnackbarLayout_elevation)) {
                C4647u.m18542f0(this, typedArrayObtainStyledAttributes.getDimensionPixelSize(r0, 0));
            }
            typedArrayObtainStyledAttributes.recycle();
            AccessibilityManager accessibilityManager = (AccessibilityManager) context.getSystemService("accessibility");
            this.accessibilityManager = accessibilityManager;
            C4694c.a aVar = new C4694c.a() { // from class: com.google.android.material.snackbar.BaseTransientBottomBar.SnackbarBaseLayout.1
                @Override // p052e0.C4694c.a
                public void onTouchExplorationStateChanged(boolean z8) {
                    SnackbarBaseLayout.this.setClickableOrFocusableBasedOnAccessibility(z8);
                }
            };
            this.touchExplorationStateChangeListener = aVar;
            C4694c.m18747a(accessibilityManager, aVar);
            setClickableOrFocusableBasedOnAccessibility(accessibilityManager.isTouchExplorationEnabled());
        }
    }

    public BaseTransientBottomBar(ViewGroup viewGroup, View view, com.google.android.material.snackbar.ContentViewCallback contentViewCallback) {
        if (viewGroup == null) {
            throw new IllegalArgumentException("Transient bottom bar must have non-null parent");
        }
        if (view == null) {
            throw new IllegalArgumentException("Transient bottom bar must have non-null content");
        }
        if (contentViewCallback == null) {
            throw new IllegalArgumentException("Transient bottom bar must have non-null callback");
        }
        this.targetParent = viewGroup;
        this.contentViewCallback = contentViewCallback;
        Context context = viewGroup.getContext();
        this.context = context;
        ThemeEnforcement.checkAppCompatTheme(context);
        SnackbarBaseLayout snackbarBaseLayout = (SnackbarBaseLayout) LayoutInflater.from(context).inflate(getSnackbarBaseLayoutResId(), viewGroup, false);
        this.view = snackbarBaseLayout;
        snackbarBaseLayout.addView(view);
        C4647u.m18532a0(snackbarBaseLayout, 1);
        C4647u.m18548i0(snackbarBaseLayout, 1);
        C4647u.m18544g0(snackbarBaseLayout, true);
        C4647u.m18554l0(snackbarBaseLayout, new InterfaceC4643q() { // from class: com.google.android.material.snackbar.BaseTransientBottomBar.2
            @Override // p042d0.InterfaceC4643q
            public C4628h0 onApplyWindowInsets(View view2, C4628h0 c4628h0) {
                view2.setPadding(view2.getPaddingLeft(), view2.getPaddingTop(), view2.getPaddingRight(), c4628h0.m18436d());
                return c4628h0;
            }
        });
        C4647u.m18530Z(snackbarBaseLayout, new C4613a() { // from class: com.google.android.material.snackbar.BaseTransientBottomBar.3
            @Override // p042d0.C4613a
            public void onInitializeAccessibilityNodeInfo(View view2, C4704m c4704m) {
                super.onInitializeAccessibilityNodeInfo(view2, c4704m);
                c4704m.m18787a(ExtractorMediaSource.DEFAULT_LOADING_CHECK_INTERVAL_BYTES);
                c4704m.m18788a0(true);
            }

            @Override // p042d0.C4613a
            public boolean performAccessibilityAction(View view2, int i9, Bundle bundle) {
                if (i9 != 1048576) {
                    return super.performAccessibilityAction(view2, i9, bundle);
                }
                BaseTransientBottomBar.this.dismiss();
                return true;
            }
        });
        this.accessibilityManager = (AccessibilityManager) context.getSystemService("accessibility");
    }

    private void animateViewOut(final int i9) {
        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setIntValues(0, getTranslationYBottom());
        valueAnimator.setInterpolator(AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR);
        valueAnimator.setDuration(250L);
        valueAnimator.addListener(new AnimatorListenerAdapter() { // from class: com.google.android.material.snackbar.BaseTransientBottomBar.10
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                BaseTransientBottomBar.this.onViewHidden(i9);
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
                BaseTransientBottomBar.this.contentViewCallback.animateContentOut(0, BaseTransientBottomBar.ANIMATION_FADE_DURATION);
            }
        });
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.google.android.material.snackbar.BaseTransientBottomBar.11
            private int previousAnimatedIntValue = 0;

            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator2) {
                int iIntValue = ((Integer) valueAnimator2.getAnimatedValue()).intValue();
                if (BaseTransientBottomBar.USE_OFFSET_API) {
                    C4647u.m18519O(BaseTransientBottomBar.this.view, iIntValue - this.previousAnimatedIntValue);
                } else {
                    BaseTransientBottomBar.this.view.setTranslationY(iIntValue);
                }
                this.previousAnimatedIntValue = iIntValue;
            }
        });
        valueAnimator.start();
    }

    private int getTranslationYBottom() {
        int height = this.view.getHeight();
        ViewGroup.LayoutParams layoutParams = this.view.getLayoutParams();
        return layoutParams instanceof ViewGroup.MarginLayoutParams ? height + ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin : height;
    }

    public B addCallback(BaseCallback<B> baseCallback) {
        if (baseCallback == null) {
            return this;
        }
        if (this.callbacks == null) {
            this.callbacks = new ArrayList();
        }
        this.callbacks.add(baseCallback);
        return this;
    }

    public void animateViewIn() {
        int translationYBottom = getTranslationYBottom();
        if (USE_OFFSET_API) {
            C4647u.m18519O(this.view, translationYBottom);
        } else {
            this.view.setTranslationY(translationYBottom);
        }
        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setIntValues(translationYBottom, 0);
        valueAnimator.setInterpolator(AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR);
        valueAnimator.setDuration(250L);
        valueAnimator.addListener(new AnimatorListenerAdapter() { // from class: com.google.android.material.snackbar.BaseTransientBottomBar.8
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                BaseTransientBottomBar.this.onViewShown();
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
                BaseTransientBottomBar.this.contentViewCallback.animateContentIn(70, BaseTransientBottomBar.ANIMATION_FADE_DURATION);
            }
        });
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener(translationYBottom) { // from class: com.google.android.material.snackbar.BaseTransientBottomBar.9
            private int previousAnimatedIntValue;
            final /* synthetic */ int val$translationYBottom;

            {
                this.val$translationYBottom = translationYBottom;
                this.previousAnimatedIntValue = translationYBottom;
            }

            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator2) {
                int iIntValue = ((Integer) valueAnimator2.getAnimatedValue()).intValue();
                if (BaseTransientBottomBar.USE_OFFSET_API) {
                    C4647u.m18519O(BaseTransientBottomBar.this.view, iIntValue - this.previousAnimatedIntValue);
                } else {
                    BaseTransientBottomBar.this.view.setTranslationY(iIntValue);
                }
                this.previousAnimatedIntValue = iIntValue;
            }
        });
        valueAnimator.start();
    }

    public void dismiss() {
        dispatchDismiss(3);
    }

    public void dispatchDismiss(int i9) {
        SnackbarManager.getInstance().dismiss(this.managerCallback, i9);
    }

    public Behavior getBehavior() {
        return this.behavior;
    }

    public Context getContext() {
        return this.context;
    }

    public int getDuration() {
        return this.duration;
    }

    public SwipeDismissBehavior<? extends View> getNewBehavior() {
        return new Behavior();
    }

    public int getSnackbarBaseLayoutResId() {
        return hasSnackbarStyleAttr() ? C3476R.layout.mtrl_layout_snackbar : C3476R.layout.design_layout_snackbar;
    }

    public View getView() {
        return this.view;
    }

    public boolean hasSnackbarStyleAttr() {
        TypedArray typedArrayObtainStyledAttributes = this.context.obtainStyledAttributes(SNACKBAR_STYLE_ATTR);
        int resourceId = typedArrayObtainStyledAttributes.getResourceId(0, -1);
        typedArrayObtainStyledAttributes.recycle();
        return resourceId != -1;
    }

    public final void hideView(int i9) {
        if (shouldAnimate() && this.view.getVisibility() == 0) {
            animateViewOut(i9);
        } else {
            onViewHidden(i9);
        }
    }

    public boolean isShown() {
        return SnackbarManager.getInstance().isCurrent(this.managerCallback);
    }

    public boolean isShownOrQueued() {
        return SnackbarManager.getInstance().isCurrentOrNext(this.managerCallback);
    }

    public void onViewHidden(int i9) {
        SnackbarManager.getInstance().onDismissed(this.managerCallback);
        List<BaseCallback<B>> list = this.callbacks;
        if (list != null) {
            for (int size = list.size() - 1; size >= 0; size--) {
                this.callbacks.get(size).onDismissed(this, i9);
            }
        }
        ViewParent parent = this.view.getParent();
        if (parent instanceof ViewGroup) {
            ((ViewGroup) parent).removeView(this.view);
        }
    }

    public void onViewShown() {
        SnackbarManager.getInstance().onShown(this.managerCallback);
        List<BaseCallback<B>> list = this.callbacks;
        if (list != null) {
            for (int size = list.size() - 1; size >= 0; size--) {
                this.callbacks.get(size).onShown(this);
            }
        }
    }

    public B removeCallback(BaseCallback<B> baseCallback) {
        List<BaseCallback<B>> list;
        if (baseCallback == null || (list = this.callbacks) == null) {
            return this;
        }
        list.remove(baseCallback);
        return this;
    }

    public B setBehavior(Behavior behavior) {
        this.behavior = behavior;
        return this;
    }

    public B setDuration(int i9) {
        this.duration = i9;
        return this;
    }

    public boolean shouldAnimate() {
        List<AccessibilityServiceInfo> enabledAccessibilityServiceList = this.accessibilityManager.getEnabledAccessibilityServiceList(1);
        return enabledAccessibilityServiceList != null && enabledAccessibilityServiceList.isEmpty();
    }

    public void show() {
        SnackbarManager.getInstance().show(getDuration(), this.managerCallback);
    }

    public final void showView() {
        if (this.view.getParent() == null) {
            ViewGroup.LayoutParams layoutParams = this.view.getLayoutParams();
            if (layoutParams instanceof CoordinatorLayout.C0307f) {
                CoordinatorLayout.C0307f c0307f = (CoordinatorLayout.C0307f) layoutParams;
                SwipeDismissBehavior<? extends View> newBehavior = this.behavior;
                if (newBehavior == null) {
                    newBehavior = getNewBehavior();
                }
                if (newBehavior instanceof Behavior) {
                    ((Behavior) newBehavior).setBaseTransientBottomBar(this);
                }
                newBehavior.setListener(new SwipeDismissBehavior.OnDismissListener() { // from class: com.google.android.material.snackbar.BaseTransientBottomBar.5
                    @Override // com.google.android.material.behavior.SwipeDismissBehavior.OnDismissListener
                    public void onDismiss(View view) {
                        view.setVisibility(8);
                        BaseTransientBottomBar.this.dispatchDismiss(0);
                    }

                    @Override // com.google.android.material.behavior.SwipeDismissBehavior.OnDismissListener
                    public void onDragStateChanged(int i9) {
                        if (i9 == 0) {
                            SnackbarManager.getInstance().restoreTimeoutIfPaused(BaseTransientBottomBar.this.managerCallback);
                        } else if (i9 == 1 || i9 == 2) {
                            SnackbarManager.getInstance().pauseTimeout(BaseTransientBottomBar.this.managerCallback);
                        }
                    }
                });
                c0307f.m1448o(newBehavior);
                c0307f.f1727g = 80;
            }
            this.targetParent.addView(this.view);
        }
        this.view.setOnAttachStateChangeListener(new OnAttachStateChangeListener() { // from class: com.google.android.material.snackbar.BaseTransientBottomBar.6
            @Override // com.google.android.material.snackbar.BaseTransientBottomBar.OnAttachStateChangeListener
            public void onViewAttachedToWindow(View view) {
            }

            @Override // com.google.android.material.snackbar.BaseTransientBottomBar.OnAttachStateChangeListener
            public void onViewDetachedFromWindow(View view) {
                if (BaseTransientBottomBar.this.isShownOrQueued()) {
                    BaseTransientBottomBar.handler.post(new Runnable() { // from class: com.google.android.material.snackbar.BaseTransientBottomBar.6.1
                        @Override // java.lang.Runnable
                        public void run() {
                            BaseTransientBottomBar.this.onViewHidden(3);
                        }
                    });
                }
            }
        });
        if (!C4647u.m18513I(this.view)) {
            this.view.setOnLayoutChangeListener(new OnLayoutChangeListener() { // from class: com.google.android.material.snackbar.BaseTransientBottomBar.7
                @Override // com.google.android.material.snackbar.BaseTransientBottomBar.OnLayoutChangeListener
                public void onLayoutChange(View view, int i9, int i10, int i11, int i12) {
                    BaseTransientBottomBar.this.view.setOnLayoutChangeListener(null);
                    if (BaseTransientBottomBar.this.shouldAnimate()) {
                        BaseTransientBottomBar.this.animateViewIn();
                    } else {
                        BaseTransientBottomBar.this.onViewShown();
                    }
                }
            });
        } else if (shouldAnimate()) {
            animateViewIn();
        } else {
            onViewShown();
        }
    }
}
