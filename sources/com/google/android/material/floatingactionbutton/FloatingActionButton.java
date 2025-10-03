package com.google.android.material.floatingactionbutton;

import android.animation.Animator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.appcompat.widget.C0227f;
import androidx.appcompat.widget.C0231h;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.material.C3476R;
import com.google.android.material.animation.MotionSpec;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.expandable.ExpandableTransformationWidget;
import com.google.android.material.expandable.ExpandableWidgetHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButtonImpl;
import com.google.android.material.internal.DescendantOffsetUtils;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.internal.ViewUtils;
import com.google.android.material.internal.VisibilityAwareImageButton;
import com.google.android.material.resources.MaterialResources;
import com.google.android.material.shadow.ShadowViewDelegate;
import com.google.android.material.stateful.ExtendableSavedState;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;
import p042d0.C4647u;
import p224w.C6494a;

@CoordinatorLayout.InterfaceC0305d(Behavior.class)
/* loaded from: classes2.dex */
public class FloatingActionButton extends VisibilityAwareImageButton implements ExpandableTransformationWidget {
    private static final int AUTO_MINI_LARGEST_SCREEN_WIDTH = 470;
    private static final String EXPANDABLE_WIDGET_HELPER_KEY = "expandableWidgetHelper";
    private static final String LOG_TAG = "FloatingActionButton";
    public static final int NO_CUSTOM_SIZE = 0;
    public static final int SIZE_AUTO = -1;
    public static final int SIZE_MINI = 1;
    public static final int SIZE_NORMAL = 0;
    private ColorStateList backgroundTint;
    private PorterDuff.Mode backgroundTintMode;
    private int borderWidth;
    boolean compatPadding;
    private int customSize;
    private final ExpandableWidgetHelper expandableWidgetHelper;
    private final C0231h imageHelper;
    private PorterDuff.Mode imageMode;
    private int imagePadding;
    private ColorStateList imageTint;
    private FloatingActionButtonImpl impl;
    private int maxImageSize;
    private ColorStateList rippleColor;
    final Rect shadowPadding;
    private int size;
    private final Rect touchArea;

    public static class BaseBehavior<T extends FloatingActionButton> extends CoordinatorLayout.AbstractC0304c<T> {
        private static final boolean AUTO_HIDE_DEFAULT = true;
        private boolean autoHideEnabled;
        private OnVisibilityChangedListener internalAutoHideListener;
        private Rect tmpRect;

        public BaseBehavior() {
            this.autoHideEnabled = true;
        }

        private static boolean isBottomSheet(View view) {
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            if (layoutParams instanceof CoordinatorLayout.C0307f) {
                return ((CoordinatorLayout.C0307f) layoutParams).m1439f() instanceof BottomSheetBehavior;
            }
            return false;
        }

        private void offsetIfNeeded(CoordinatorLayout coordinatorLayout, FloatingActionButton floatingActionButton) {
            Rect rect = floatingActionButton.shadowPadding;
            if (rect == null || rect.centerX() <= 0 || rect.centerY() <= 0) {
                return;
            }
            CoordinatorLayout.C0307f c0307f = (CoordinatorLayout.C0307f) floatingActionButton.getLayoutParams();
            int i9 = 0;
            int i10 = floatingActionButton.getRight() >= coordinatorLayout.getWidth() - ((ViewGroup.MarginLayoutParams) c0307f).rightMargin ? rect.right : floatingActionButton.getLeft() <= ((ViewGroup.MarginLayoutParams) c0307f).leftMargin ? -rect.left : 0;
            if (floatingActionButton.getBottom() >= coordinatorLayout.getHeight() - ((ViewGroup.MarginLayoutParams) c0307f).bottomMargin) {
                i9 = rect.bottom;
            } else if (floatingActionButton.getTop() <= ((ViewGroup.MarginLayoutParams) c0307f).topMargin) {
                i9 = -rect.top;
            }
            if (i9 != 0) {
                C4647u.m18519O(floatingActionButton, i9);
            }
            if (i10 != 0) {
                C4647u.m18518N(floatingActionButton, i10);
            }
        }

        private boolean shouldUpdateVisibility(View view, FloatingActionButton floatingActionButton) {
            return this.autoHideEnabled && ((CoordinatorLayout.C0307f) floatingActionButton.getLayoutParams()).m1438e() == view.getId() && floatingActionButton.getUserSetVisibility() == 0;
        }

        private boolean updateFabVisibilityForAppBarLayout(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, FloatingActionButton floatingActionButton) {
            if (!shouldUpdateVisibility(appBarLayout, floatingActionButton)) {
                return false;
            }
            if (this.tmpRect == null) {
                this.tmpRect = new Rect();
            }
            Rect rect = this.tmpRect;
            DescendantOffsetUtils.getDescendantRect(coordinatorLayout, appBarLayout, rect);
            if (rect.bottom <= appBarLayout.getMinimumHeightForVisibleOverlappingContent()) {
                floatingActionButton.hide(this.internalAutoHideListener, false);
                return true;
            }
            floatingActionButton.show(this.internalAutoHideListener, false);
            return true;
        }

        private boolean updateFabVisibilityForBottomSheet(View view, FloatingActionButton floatingActionButton) {
            if (!shouldUpdateVisibility(view, floatingActionButton)) {
                return false;
            }
            if (view.getTop() < (floatingActionButton.getHeight() / 2) + ((ViewGroup.MarginLayoutParams) ((CoordinatorLayout.C0307f) floatingActionButton.getLayoutParams())).topMargin) {
                floatingActionButton.hide(this.internalAutoHideListener, false);
                return true;
            }
            floatingActionButton.show(this.internalAutoHideListener, false);
            return true;
        }

        public boolean isAutoHideEnabled() {
            return this.autoHideEnabled;
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.AbstractC0304c
        public void onAttachedToLayoutParams(CoordinatorLayout.C0307f c0307f) {
            if (c0307f.f1728h == 0) {
                c0307f.f1728h = 80;
            }
        }

        public void setAutoHideEnabled(boolean z8) {
            this.autoHideEnabled = z8;
        }

        public void setInternalAutoHideListener(OnVisibilityChangedListener onVisibilityChangedListener) {
            this.internalAutoHideListener = onVisibilityChangedListener;
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.AbstractC0304c
        public boolean getInsetDodgeRect(CoordinatorLayout coordinatorLayout, FloatingActionButton floatingActionButton, Rect rect) {
            Rect rect2 = floatingActionButton.shadowPadding;
            rect.set(floatingActionButton.getLeft() + rect2.left, floatingActionButton.getTop() + rect2.top, floatingActionButton.getRight() - rect2.right, floatingActionButton.getBottom() - rect2.bottom);
            return true;
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.AbstractC0304c
        public boolean onDependentViewChanged(CoordinatorLayout coordinatorLayout, FloatingActionButton floatingActionButton, View view) {
            if (view instanceof AppBarLayout) {
                updateFabVisibilityForAppBarLayout(coordinatorLayout, (AppBarLayout) view, floatingActionButton);
                return false;
            }
            if (!isBottomSheet(view)) {
                return false;
            }
            updateFabVisibilityForBottomSheet(view, floatingActionButton);
            return false;
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.AbstractC0304c
        public boolean onLayoutChild(CoordinatorLayout coordinatorLayout, FloatingActionButton floatingActionButton, int i9) {
            List<View> dependencies = coordinatorLayout.getDependencies(floatingActionButton);
            int size = dependencies.size();
            for (int i10 = 0; i10 < size; i10++) {
                View view = dependencies.get(i10);
                if (!(view instanceof AppBarLayout)) {
                    if (isBottomSheet(view) && updateFabVisibilityForBottomSheet(view, floatingActionButton)) {
                        break;
                    }
                } else {
                    if (updateFabVisibilityForAppBarLayout(coordinatorLayout, (AppBarLayout) view, floatingActionButton)) {
                        break;
                    }
                }
            }
            coordinatorLayout.onLayoutChild(floatingActionButton, i9);
            offsetIfNeeded(coordinatorLayout, floatingActionButton);
            return true;
        }

        public BaseBehavior(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C3476R.styleable.FloatingActionButton_Behavior_Layout);
            this.autoHideEnabled = typedArrayObtainStyledAttributes.getBoolean(C3476R.styleable.FloatingActionButton_Behavior_Layout_behavior_autoHide, true);
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    public static class Behavior extends BaseBehavior<FloatingActionButton> {
        public Behavior() {
        }

        @Override // com.google.android.material.floatingactionbutton.FloatingActionButton.BaseBehavior
        public /* bridge */ /* synthetic */ boolean getInsetDodgeRect(CoordinatorLayout coordinatorLayout, FloatingActionButton floatingActionButton, Rect rect) {
            return super.getInsetDodgeRect(coordinatorLayout, floatingActionButton, rect);
        }

        @Override // com.google.android.material.floatingactionbutton.FloatingActionButton.BaseBehavior
        public /* bridge */ /* synthetic */ boolean isAutoHideEnabled() {
            return super.isAutoHideEnabled();
        }

        @Override // com.google.android.material.floatingactionbutton.FloatingActionButton.BaseBehavior, androidx.coordinatorlayout.widget.CoordinatorLayout.AbstractC0304c
        public /* bridge */ /* synthetic */ void onAttachedToLayoutParams(CoordinatorLayout.C0307f c0307f) {
            super.onAttachedToLayoutParams(c0307f);
        }

        @Override // com.google.android.material.floatingactionbutton.FloatingActionButton.BaseBehavior
        public /* bridge */ /* synthetic */ boolean onDependentViewChanged(CoordinatorLayout coordinatorLayout, FloatingActionButton floatingActionButton, View view) {
            return super.onDependentViewChanged(coordinatorLayout, floatingActionButton, view);
        }

        @Override // com.google.android.material.floatingactionbutton.FloatingActionButton.BaseBehavior
        public /* bridge */ /* synthetic */ boolean onLayoutChild(CoordinatorLayout coordinatorLayout, FloatingActionButton floatingActionButton, int i9) {
            return super.onLayoutChild(coordinatorLayout, floatingActionButton, i9);
        }

        @Override // com.google.android.material.floatingactionbutton.FloatingActionButton.BaseBehavior
        public /* bridge */ /* synthetic */ void setAutoHideEnabled(boolean z8) {
            super.setAutoHideEnabled(z8);
        }

        @Override // com.google.android.material.floatingactionbutton.FloatingActionButton.BaseBehavior
        public /* bridge */ /* synthetic */ void setInternalAutoHideListener(OnVisibilityChangedListener onVisibilityChangedListener) {
            super.setInternalAutoHideListener(onVisibilityChangedListener);
        }

        public Behavior(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }
    }

    public static abstract class OnVisibilityChangedListener {
        public void onHidden(FloatingActionButton floatingActionButton) {
        }

        public void onShown(FloatingActionButton floatingActionButton) {
        }
    }

    public class ShadowDelegateImpl implements ShadowViewDelegate {
        public ShadowDelegateImpl() {
        }

        @Override // com.google.android.material.shadow.ShadowViewDelegate
        public float getRadius() {
            return FloatingActionButton.this.getSizeDimension() / 2.0f;
        }

        @Override // com.google.android.material.shadow.ShadowViewDelegate
        public boolean isCompatPaddingEnabled() {
            return FloatingActionButton.this.compatPadding;
        }

        @Override // com.google.android.material.shadow.ShadowViewDelegate
        public void setBackgroundDrawable(Drawable drawable) {
            FloatingActionButton.super.setBackgroundDrawable(drawable);
        }

        @Override // com.google.android.material.shadow.ShadowViewDelegate
        public void setShadowPadding(int i9, int i10, int i11, int i12) {
            FloatingActionButton.this.shadowPadding.set(i9, i10, i11, i12);
            FloatingActionButton floatingActionButton = FloatingActionButton.this;
            floatingActionButton.setPadding(i9 + floatingActionButton.imagePadding, i10 + FloatingActionButton.this.imagePadding, i11 + FloatingActionButton.this.imagePadding, i12 + FloatingActionButton.this.imagePadding);
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface Size {
    }

    public FloatingActionButton(Context context) {
        this(context, null);
    }

    private FloatingActionButtonImpl createImpl() {
        return new FloatingActionButtonImplLollipop(this, new ShadowDelegateImpl());
    }

    private FloatingActionButtonImpl getImpl() {
        if (this.impl == null) {
            this.impl = createImpl();
        }
        return this.impl;
    }

    private void offsetRectWithShadow(Rect rect) {
        int i9 = rect.left;
        Rect rect2 = this.shadowPadding;
        rect.left = i9 + rect2.left;
        rect.top += rect2.top;
        rect.right -= rect2.right;
        rect.bottom -= rect2.bottom;
    }

    private void onApplySupportImageTint() {
        Drawable drawable = getDrawable();
        if (drawable == null) {
            return;
        }
        ColorStateList colorStateList = this.imageTint;
        if (colorStateList == null) {
            C6494a.m24838a(drawable);
            return;
        }
        int colorForState = colorStateList.getColorForState(getDrawableState(), 0);
        PorterDuff.Mode mode = this.imageMode;
        if (mode == null) {
            mode = PorterDuff.Mode.SRC_IN;
        }
        drawable.mutate().setColorFilter(C0227f.m820e(colorForState, mode));
    }

    private static int resolveAdjustedSize(int i9, int i10) {
        int mode = View.MeasureSpec.getMode(i10);
        int size = View.MeasureSpec.getSize(i10);
        if (mode == Integer.MIN_VALUE) {
            return Math.min(i9, size);
        }
        if (mode == 0) {
            return i9;
        }
        if (mode == 1073741824) {
            return size;
        }
        throw new IllegalArgumentException();
    }

    private FloatingActionButtonImpl.InternalVisibilityChangedListener wrapOnVisibilityChangedListener(final OnVisibilityChangedListener onVisibilityChangedListener) {
        if (onVisibilityChangedListener == null) {
            return null;
        }
        return new FloatingActionButtonImpl.InternalVisibilityChangedListener() { // from class: com.google.android.material.floatingactionbutton.FloatingActionButton.1
            @Override // com.google.android.material.floatingactionbutton.FloatingActionButtonImpl.InternalVisibilityChangedListener
            public void onHidden() {
                onVisibilityChangedListener.onHidden(FloatingActionButton.this);
            }

            @Override // com.google.android.material.floatingactionbutton.FloatingActionButtonImpl.InternalVisibilityChangedListener
            public void onShown() {
                onVisibilityChangedListener.onShown(FloatingActionButton.this);
            }
        };
    }

    public void addOnHideAnimationListener(Animator.AnimatorListener animatorListener) {
        getImpl().addOnHideAnimationListener(animatorListener);
    }

    public void addOnShowAnimationListener(Animator.AnimatorListener animatorListener) {
        getImpl().addOnShowAnimationListener(animatorListener);
    }

    public void clearCustomSize() {
        setCustomSize(0);
    }

    @Override // android.widget.ImageView, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        getImpl().onDrawableStateChanged(getDrawableState());
    }

    @Override // android.view.View
    public ColorStateList getBackgroundTintList() {
        return this.backgroundTint;
    }

    @Override // android.view.View
    public PorterDuff.Mode getBackgroundTintMode() {
        return this.backgroundTintMode;
    }

    public float getCompatElevation() {
        return getImpl().getElevation();
    }

    public float getCompatHoveredFocusedTranslationZ() {
        return getImpl().getHoveredFocusedTranslationZ();
    }

    public float getCompatPressedTranslationZ() {
        return getImpl().getPressedTranslationZ();
    }

    public Drawable getContentBackground() {
        return getImpl().getContentBackground();
    }

    @Deprecated
    public boolean getContentRect(Rect rect) {
        if (!C4647u.m18513I(this)) {
            return false;
        }
        rect.set(0, 0, getWidth(), getHeight());
        offsetRectWithShadow(rect);
        return true;
    }

    public int getCustomSize() {
        return this.customSize;
    }

    @Override // com.google.android.material.expandable.ExpandableTransformationWidget
    public int getExpandedComponentIdHint() {
        return this.expandableWidgetHelper.getExpandedComponentIdHint();
    }

    public MotionSpec getHideMotionSpec() {
        return getImpl().getHideMotionSpec();
    }

    public void getMeasuredContentRect(Rect rect) {
        rect.set(0, 0, getMeasuredWidth(), getMeasuredHeight());
        offsetRectWithShadow(rect);
    }

    @Deprecated
    public int getRippleColor() {
        ColorStateList colorStateList = this.rippleColor;
        if (colorStateList != null) {
            return colorStateList.getDefaultColor();
        }
        return 0;
    }

    public ColorStateList getRippleColorStateList() {
        return this.rippleColor;
    }

    public MotionSpec getShowMotionSpec() {
        return getImpl().getShowMotionSpec();
    }

    public int getSize() {
        return this.size;
    }

    public int getSizeDimension() {
        return getSizeDimension(this.size);
    }

    public ColorStateList getSupportBackgroundTintList() {
        return getBackgroundTintList();
    }

    public PorterDuff.Mode getSupportBackgroundTintMode() {
        return getBackgroundTintMode();
    }

    public ColorStateList getSupportImageTintList() {
        return this.imageTint;
    }

    public PorterDuff.Mode getSupportImageTintMode() {
        return this.imageMode;
    }

    public boolean getUseCompatPadding() {
        return this.compatPadding;
    }

    public void hide() {
        hide(null);
    }

    @Override // com.google.android.material.expandable.ExpandableWidget
    public boolean isExpanded() {
        return this.expandableWidgetHelper.isExpanded();
    }

    public boolean isOrWillBeHidden() {
        return getImpl().isOrWillBeHidden();
    }

    public boolean isOrWillBeShown() {
        return getImpl().isOrWillBeShown();
    }

    @Override // android.widget.ImageView, android.view.View
    public void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
        getImpl().jumpDrawableToCurrentState();
    }

    @Override // android.widget.ImageView, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        getImpl().onAttachedToWindow();
    }

    @Override // android.widget.ImageView, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        getImpl().onDetachedFromWindow();
    }

    @Override // android.widget.ImageView, android.view.View
    public void onMeasure(int i9, int i10) {
        int sizeDimension = getSizeDimension();
        this.imagePadding = (sizeDimension - this.maxImageSize) / 2;
        getImpl().updatePadding();
        int iMin = Math.min(resolveAdjustedSize(sizeDimension, i9), resolveAdjustedSize(sizeDimension, i10));
        Rect rect = this.shadowPadding;
        setMeasuredDimension(rect.left + iMin + rect.right, iMin + rect.top + rect.bottom);
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof ExtendableSavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        ExtendableSavedState extendableSavedState = (ExtendableSavedState) parcelable;
        super.onRestoreInstanceState(extendableSavedState.getSuperState());
        this.expandableWidgetHelper.onRestoreInstanceState(extendableSavedState.extendableStates.get(EXPANDABLE_WIDGET_HELPER_KEY));
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        ExtendableSavedState extendableSavedState = new ExtendableSavedState(super.onSaveInstanceState());
        extendableSavedState.extendableStates.put(EXPANDABLE_WIDGET_HELPER_KEY, this.expandableWidgetHelper.onSaveInstanceState());
        return extendableSavedState;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0 && getContentRect(this.touchArea) && !this.touchArea.contains((int) motionEvent.getX(), (int) motionEvent.getY())) {
            return false;
        }
        return super.onTouchEvent(motionEvent);
    }

    public void removeOnHideAnimationListener(Animator.AnimatorListener animatorListener) {
        getImpl().removeOnHideAnimationListener(animatorListener);
    }

    public void removeOnShowAnimationListener(Animator.AnimatorListener animatorListener) {
        getImpl().removeOnShowAnimationListener(animatorListener);
    }

    @Override // android.view.View
    public void setBackgroundColor(int i9) {
        Log.i(LOG_TAG, "Setting a custom background is not supported.");
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        Log.i(LOG_TAG, "Setting a custom background is not supported.");
    }

    @Override // android.view.View
    public void setBackgroundResource(int i9) {
        Log.i(LOG_TAG, "Setting a custom background is not supported.");
    }

    @Override // android.view.View
    public void setBackgroundTintList(ColorStateList colorStateList) {
        if (this.backgroundTint != colorStateList) {
            this.backgroundTint = colorStateList;
            getImpl().setBackgroundTintList(colorStateList);
        }
    }

    @Override // android.view.View
    public void setBackgroundTintMode(PorterDuff.Mode mode) {
        if (this.backgroundTintMode != mode) {
            this.backgroundTintMode = mode;
            getImpl().setBackgroundTintMode(mode);
        }
    }

    public void setCompatElevation(float f9) {
        getImpl().setElevation(f9);
    }

    public void setCompatElevationResource(int i9) {
        setCompatElevation(getResources().getDimension(i9));
    }

    public void setCompatHoveredFocusedTranslationZ(float f9) {
        getImpl().setHoveredFocusedTranslationZ(f9);
    }

    public void setCompatHoveredFocusedTranslationZResource(int i9) {
        setCompatHoveredFocusedTranslationZ(getResources().getDimension(i9));
    }

    public void setCompatPressedTranslationZ(float f9) {
        getImpl().setPressedTranslationZ(f9);
    }

    public void setCompatPressedTranslationZResource(int i9) {
        setCompatPressedTranslationZ(getResources().getDimension(i9));
    }

    public void setCustomSize(int i9) {
        if (i9 < 0) {
            throw new IllegalArgumentException("Custom size must be non-negative");
        }
        this.customSize = i9;
    }

    @Override // com.google.android.material.expandable.ExpandableWidget
    public boolean setExpanded(boolean z8) {
        return this.expandableWidgetHelper.setExpanded(z8);
    }

    @Override // com.google.android.material.expandable.ExpandableTransformationWidget
    public void setExpandedComponentIdHint(int i9) {
        this.expandableWidgetHelper.setExpandedComponentIdHint(i9);
    }

    public void setHideMotionSpec(MotionSpec motionSpec) {
        getImpl().setHideMotionSpec(motionSpec);
    }

    public void setHideMotionSpecResource(int i9) {
        setHideMotionSpec(MotionSpec.createFromResource(getContext(), i9));
    }

    @Override // android.widget.ImageView
    public void setImageDrawable(Drawable drawable) {
        super.setImageDrawable(drawable);
        getImpl().updateImageMatrixScale();
    }

    @Override // android.widget.ImageView
    public void setImageResource(int i9) {
        this.imageHelper.m879g(i9);
    }

    public void setRippleColor(int i9) {
        setRippleColor(ColorStateList.valueOf(i9));
    }

    public void setShowMotionSpec(MotionSpec motionSpec) {
        getImpl().setShowMotionSpec(motionSpec);
    }

    public void setShowMotionSpecResource(int i9) {
        setShowMotionSpec(MotionSpec.createFromResource(getContext(), i9));
    }

    public void setSize(int i9) {
        this.customSize = 0;
        if (i9 != this.size) {
            this.size = i9;
            requestLayout();
        }
    }

    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        setBackgroundTintList(colorStateList);
    }

    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        setBackgroundTintMode(mode);
    }

    public void setSupportImageTintList(ColorStateList colorStateList) {
        if (this.imageTint != colorStateList) {
            this.imageTint = colorStateList;
            onApplySupportImageTint();
        }
    }

    public void setSupportImageTintMode(PorterDuff.Mode mode) {
        if (this.imageMode != mode) {
            this.imageMode = mode;
            onApplySupportImageTint();
        }
    }

    public void setUseCompatPadding(boolean z8) {
        if (this.compatPadding != z8) {
            this.compatPadding = z8;
            getImpl().onCompatShadowChanged();
        }
    }

    public void show() {
        show(null);
    }

    public FloatingActionButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, C3476R.attr.floatingActionButtonStyle);
    }

    private int getSizeDimension(int i9) {
        int i10 = this.customSize;
        if (i10 != 0) {
            return i10;
        }
        Resources resources = getResources();
        return i9 != -1 ? i9 != 1 ? resources.getDimensionPixelSize(C3476R.dimen.design_fab_size_normal) : resources.getDimensionPixelSize(C3476R.dimen.design_fab_size_mini) : Math.max(resources.getConfiguration().screenWidthDp, resources.getConfiguration().screenHeightDp) < AUTO_MINI_LARGEST_SCREEN_WIDTH ? getSizeDimension(1) : getSizeDimension(0);
    }

    public void hide(OnVisibilityChangedListener onVisibilityChangedListener) {
        hide(onVisibilityChangedListener, true);
    }

    public void setRippleColor(ColorStateList colorStateList) {
        if (this.rippleColor != colorStateList) {
            this.rippleColor = colorStateList;
            getImpl().setRippleColor(this.rippleColor);
        }
    }

    public void show(OnVisibilityChangedListener onVisibilityChangedListener) {
        show(onVisibilityChangedListener, true);
    }

    public FloatingActionButton(Context context, AttributeSet attributeSet, int i9) {
        super(context, attributeSet, i9);
        this.shadowPadding = new Rect();
        this.touchArea = new Rect();
        TypedArray typedArrayObtainStyledAttributes = ThemeEnforcement.obtainStyledAttributes(context, attributeSet, C3476R.styleable.FloatingActionButton, i9, C3476R.style.Widget_Design_FloatingActionButton, new int[0]);
        this.backgroundTint = MaterialResources.getColorStateList(context, typedArrayObtainStyledAttributes, C3476R.styleable.FloatingActionButton_backgroundTint);
        this.backgroundTintMode = ViewUtils.parseTintMode(typedArrayObtainStyledAttributes.getInt(C3476R.styleable.FloatingActionButton_backgroundTintMode, -1), null);
        this.rippleColor = MaterialResources.getColorStateList(context, typedArrayObtainStyledAttributes, C3476R.styleable.FloatingActionButton_rippleColor);
        this.size = typedArrayObtainStyledAttributes.getInt(C3476R.styleable.FloatingActionButton_fabSize, -1);
        this.customSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(C3476R.styleable.FloatingActionButton_fabCustomSize, 0);
        this.borderWidth = typedArrayObtainStyledAttributes.getDimensionPixelSize(C3476R.styleable.FloatingActionButton_borderWidth, 0);
        float dimension = typedArrayObtainStyledAttributes.getDimension(C3476R.styleable.FloatingActionButton_elevation, BitmapDescriptorFactory.HUE_RED);
        float dimension2 = typedArrayObtainStyledAttributes.getDimension(C3476R.styleable.FloatingActionButton_hoveredFocusedTranslationZ, BitmapDescriptorFactory.HUE_RED);
        float dimension3 = typedArrayObtainStyledAttributes.getDimension(C3476R.styleable.FloatingActionButton_pressedTranslationZ, BitmapDescriptorFactory.HUE_RED);
        this.compatPadding = typedArrayObtainStyledAttributes.getBoolean(C3476R.styleable.FloatingActionButton_useCompatPadding, false);
        this.maxImageSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(C3476R.styleable.FloatingActionButton_maxImageSize, 0);
        MotionSpec motionSpecCreateFromAttribute = MotionSpec.createFromAttribute(context, typedArrayObtainStyledAttributes, C3476R.styleable.FloatingActionButton_showMotionSpec);
        MotionSpec motionSpecCreateFromAttribute2 = MotionSpec.createFromAttribute(context, typedArrayObtainStyledAttributes, C3476R.styleable.FloatingActionButton_hideMotionSpec);
        typedArrayObtainStyledAttributes.recycle();
        C0231h c0231h = new C0231h(this);
        this.imageHelper = c0231h;
        c0231h.m878f(attributeSet, i9);
        this.expandableWidgetHelper = new ExpandableWidgetHelper(this);
        getImpl().setBackgroundDrawable(this.backgroundTint, this.backgroundTintMode, this.rippleColor, this.borderWidth);
        getImpl().setElevation(dimension);
        getImpl().setHoveredFocusedTranslationZ(dimension2);
        getImpl().setPressedTranslationZ(dimension3);
        getImpl().setMaxImageSize(this.maxImageSize);
        getImpl().setShowMotionSpec(motionSpecCreateFromAttribute);
        getImpl().setHideMotionSpec(motionSpecCreateFromAttribute2);
        setScaleType(ImageView.ScaleType.MATRIX);
    }

    public void hide(OnVisibilityChangedListener onVisibilityChangedListener, boolean z8) {
        getImpl().hide(wrapOnVisibilityChangedListener(onVisibilityChangedListener), z8);
    }

    public void show(OnVisibilityChangedListener onVisibilityChangedListener, boolean z8) {
        getImpl().show(wrapOnVisibilityChangedListener(onVisibilityChangedListener), z8);
    }
}
