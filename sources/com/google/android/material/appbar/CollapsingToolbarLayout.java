package com.google.android.material.appbar;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import androidx.appcompat.widget.Toolbar;
import com.google.android.material.C3476R;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.internal.CollapsingTextHelper;
import com.google.android.material.internal.DescendantOffsetUtils;
import com.google.android.material.internal.ThemeEnforcement;
import p010b.C0568i;
import p021c0.C0697c;
import p042d0.C4628h0;
import p042d0.C4647u;
import p042d0.InterfaceC4643q;
import p197t.C6273a;
import p224w.C6494a;
import p242y.C6587a;

/* loaded from: classes2.dex */
public class CollapsingToolbarLayout extends FrameLayout {
    private static final int DEFAULT_SCRIM_ANIMATION_DURATION = 600;
    final CollapsingTextHelper collapsingTextHelper;
    private boolean collapsingTitleEnabled;
    private Drawable contentScrim;
    int currentOffset;
    private boolean drawCollapsingTitle;
    private View dummyView;
    private int expandedMarginBottom;
    private int expandedMarginEnd;
    private int expandedMarginStart;
    private int expandedMarginTop;
    C4628h0 lastInsets;
    private AppBarLayout.OnOffsetChangedListener onOffsetChangedListener;
    private boolean refreshToolbar;
    private int scrimAlpha;
    private long scrimAnimationDuration;
    private ValueAnimator scrimAnimator;
    private int scrimVisibleHeightTrigger;
    private boolean scrimsAreShown;
    Drawable statusBarScrim;
    private final Rect tmpRect;
    private Toolbar toolbar;
    private View toolbarDirectChild;
    private int toolbarId;

    public class OffsetUpdateListener implements AppBarLayout.OnOffsetChangedListener {
        public OffsetUpdateListener() {
        }

        @Override // com.google.android.material.appbar.AppBarLayout.OnOffsetChangedListener, com.google.android.material.appbar.AppBarLayout.BaseOnOffsetChangedListener
        public void onOffsetChanged(AppBarLayout appBarLayout, int i9) {
            CollapsingToolbarLayout collapsingToolbarLayout = CollapsingToolbarLayout.this;
            collapsingToolbarLayout.currentOffset = i9;
            C4628h0 c4628h0 = collapsingToolbarLayout.lastInsets;
            int iM18439g = c4628h0 != null ? c4628h0.m18439g() : 0;
            int childCount = CollapsingToolbarLayout.this.getChildCount();
            for (int i10 = 0; i10 < childCount; i10++) {
                View childAt = CollapsingToolbarLayout.this.getChildAt(i10);
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                ViewOffsetHelper viewOffsetHelper = CollapsingToolbarLayout.getViewOffsetHelper(childAt);
                int i11 = layoutParams.collapseMode;
                if (i11 == 1) {
                    viewOffsetHelper.setTopAndBottomOffset(C6587a.m25200b(-i9, 0, CollapsingToolbarLayout.this.getMaxOffsetForPinChild(childAt)));
                } else if (i11 == 2) {
                    viewOffsetHelper.setTopAndBottomOffset(Math.round((-i9) * layoutParams.parallaxMult));
                }
            }
            CollapsingToolbarLayout.this.updateScrimVisibility();
            CollapsingToolbarLayout collapsingToolbarLayout2 = CollapsingToolbarLayout.this;
            if (collapsingToolbarLayout2.statusBarScrim != null && iM18439g > 0) {
                C4647u.m18524T(collapsingToolbarLayout2);
            }
            CollapsingToolbarLayout.this.collapsingTextHelper.setExpansionFraction(Math.abs(i9) / ((CollapsingToolbarLayout.this.getHeight() - C4647u.m18568t(CollapsingToolbarLayout.this)) - iM18439g));
        }
    }

    public CollapsingToolbarLayout(Context context) {
        this(context, null);
    }

    private void animateScrim(int i9) {
        ensureToolbar();
        ValueAnimator valueAnimator = this.scrimAnimator;
        if (valueAnimator == null) {
            ValueAnimator valueAnimator2 = new ValueAnimator();
            this.scrimAnimator = valueAnimator2;
            valueAnimator2.setDuration(this.scrimAnimationDuration);
            this.scrimAnimator.setInterpolator(i9 > this.scrimAlpha ? AnimationUtils.FAST_OUT_LINEAR_IN_INTERPOLATOR : AnimationUtils.LINEAR_OUT_SLOW_IN_INTERPOLATOR);
            this.scrimAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.google.android.material.appbar.CollapsingToolbarLayout.2
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public void onAnimationUpdate(ValueAnimator valueAnimator3) {
                    CollapsingToolbarLayout.this.setScrimAlpha(((Integer) valueAnimator3.getAnimatedValue()).intValue());
                }
            });
        } else if (valueAnimator.isRunning()) {
            this.scrimAnimator.cancel();
        }
        this.scrimAnimator.setIntValues(this.scrimAlpha, i9);
        this.scrimAnimator.start();
    }

    private void ensureToolbar() {
        if (this.refreshToolbar) {
            Toolbar toolbar = null;
            this.toolbar = null;
            this.toolbarDirectChild = null;
            int i9 = this.toolbarId;
            if (i9 != -1) {
                Toolbar toolbar2 = (Toolbar) findViewById(i9);
                this.toolbar = toolbar2;
                if (toolbar2 != null) {
                    this.toolbarDirectChild = findDirectChild(toolbar2);
                }
            }
            if (this.toolbar == null) {
                int childCount = getChildCount();
                int i10 = 0;
                while (true) {
                    if (i10 >= childCount) {
                        break;
                    }
                    View childAt = getChildAt(i10);
                    if (childAt instanceof Toolbar) {
                        toolbar = (Toolbar) childAt;
                        break;
                    }
                    i10++;
                }
                this.toolbar = toolbar;
            }
            updateDummyView();
            this.refreshToolbar = false;
        }
    }

    private View findDirectChild(View view) {
        for (ViewParent parent = view.getParent(); parent != this && parent != null; parent = parent.getParent()) {
            if (parent instanceof View) {
                view = parent;
            }
        }
        return view;
    }

    private static int getHeightWithMargins(View view) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (!(layoutParams instanceof ViewGroup.MarginLayoutParams)) {
            return view.getHeight();
        }
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
        return view.getHeight() + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin;
    }

    public static ViewOffsetHelper getViewOffsetHelper(View view) {
        int i9 = C3476R.id.view_offset_helper;
        ViewOffsetHelper viewOffsetHelper = (ViewOffsetHelper) view.getTag(i9);
        if (viewOffsetHelper != null) {
            return viewOffsetHelper;
        }
        ViewOffsetHelper viewOffsetHelper2 = new ViewOffsetHelper(view);
        view.setTag(i9, viewOffsetHelper2);
        return viewOffsetHelper2;
    }

    private boolean isToolbarChild(View view) {
        View view2 = this.toolbarDirectChild;
        if (view2 == null || view2 == this) {
            if (view == this.toolbar) {
                return true;
            }
        } else if (view == view2) {
            return true;
        }
        return false;
    }

    private void updateContentDescriptionFromTitle() {
        setContentDescription(getTitle());
    }

    private void updateDummyView() {
        View view;
        if (!this.collapsingTitleEnabled && (view = this.dummyView) != null) {
            ViewParent parent = view.getParent();
            if (parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(this.dummyView);
            }
        }
        if (!this.collapsingTitleEnabled || this.toolbar == null) {
            return;
        }
        if (this.dummyView == null) {
            this.dummyView = new View(getContext());
        }
        if (this.dummyView.getParent() == null) {
            this.toolbar.addView(this.dummyView, -1, -1);
        }
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup
    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams;
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        Drawable drawable;
        super.draw(canvas);
        ensureToolbar();
        if (this.toolbar == null && (drawable = this.contentScrim) != null && this.scrimAlpha > 0) {
            drawable.mutate().setAlpha(this.scrimAlpha);
            this.contentScrim.draw(canvas);
        }
        if (this.collapsingTitleEnabled && this.drawCollapsingTitle) {
            this.collapsingTextHelper.draw(canvas);
        }
        if (this.statusBarScrim == null || this.scrimAlpha <= 0) {
            return;
        }
        C4628h0 c4628h0 = this.lastInsets;
        int iM18439g = c4628h0 != null ? c4628h0.m18439g() : 0;
        if (iM18439g > 0) {
            this.statusBarScrim.setBounds(0, -this.currentOffset, getWidth(), iM18439g - this.currentOffset);
            this.statusBarScrim.mutate().setAlpha(this.scrimAlpha);
            this.statusBarScrim.draw(canvas);
        }
    }

    @Override // android.view.ViewGroup
    public boolean drawChild(Canvas canvas, View view, long j9) {
        boolean z8;
        if (this.contentScrim == null || this.scrimAlpha <= 0 || !isToolbarChild(view)) {
            z8 = false;
        } else {
            this.contentScrim.mutate().setAlpha(this.scrimAlpha);
            this.contentScrim.draw(canvas);
            z8 = true;
        }
        return super.drawChild(canvas, view, j9) || z8;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        int[] drawableState = getDrawableState();
        Drawable drawable = this.statusBarScrim;
        boolean state = false;
        if (drawable != null && drawable.isStateful()) {
            state = false | drawable.setState(drawableState);
        }
        Drawable drawable2 = this.contentScrim;
        if (drawable2 != null && drawable2.isStateful()) {
            state |= drawable2.setState(drawableState);
        }
        CollapsingTextHelper collapsingTextHelper = this.collapsingTextHelper;
        if (collapsingTextHelper != null) {
            state |= collapsingTextHelper.setState(drawableState);
        }
        if (state) {
            invalidate();
        }
    }

    public int getCollapsedTitleGravity() {
        return this.collapsingTextHelper.getCollapsedTextGravity();
    }

    public Typeface getCollapsedTitleTypeface() {
        return this.collapsingTextHelper.getCollapsedTypeface();
    }

    public Drawable getContentScrim() {
        return this.contentScrim;
    }

    public int getExpandedTitleGravity() {
        return this.collapsingTextHelper.getExpandedTextGravity();
    }

    public int getExpandedTitleMarginBottom() {
        return this.expandedMarginBottom;
    }

    public int getExpandedTitleMarginEnd() {
        return this.expandedMarginEnd;
    }

    public int getExpandedTitleMarginStart() {
        return this.expandedMarginStart;
    }

    public int getExpandedTitleMarginTop() {
        return this.expandedMarginTop;
    }

    public Typeface getExpandedTitleTypeface() {
        return this.collapsingTextHelper.getExpandedTypeface();
    }

    public final int getMaxOffsetForPinChild(View view) {
        return ((getHeight() - getViewOffsetHelper(view).getLayoutTop()) - view.getHeight()) - ((FrameLayout.LayoutParams) ((LayoutParams) view.getLayoutParams())).bottomMargin;
    }

    public int getScrimAlpha() {
        return this.scrimAlpha;
    }

    public long getScrimAnimationDuration() {
        return this.scrimAnimationDuration;
    }

    public int getScrimVisibleHeightTrigger() {
        int i9 = this.scrimVisibleHeightTrigger;
        if (i9 >= 0) {
            return i9;
        }
        C4628h0 c4628h0 = this.lastInsets;
        int iM18439g = c4628h0 != null ? c4628h0.m18439g() : 0;
        int iM18568t = C4647u.m18568t(this);
        return iM18568t > 0 ? Math.min((iM18568t * 2) + iM18439g, getHeight()) : getHeight() / 3;
    }

    public Drawable getStatusBarScrim() {
        return this.statusBarScrim;
    }

    public CharSequence getTitle() {
        if (this.collapsingTitleEnabled) {
            return this.collapsingTextHelper.getText();
        }
        return null;
    }

    public boolean isTitleEnabled() {
        return this.collapsingTitleEnabled;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Object parent = getParent();
        if (parent instanceof AppBarLayout) {
            C4647u.m18544g0(this, C4647u.m18561p((View) parent));
            if (this.onOffsetChangedListener == null) {
                this.onOffsetChangedListener = new OffsetUpdateListener();
            }
            ((AppBarLayout) parent).addOnOffsetChangedListener(this.onOffsetChangedListener);
            C4647u.m18527W(this);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        ViewParent parent = getParent();
        AppBarLayout.OnOffsetChangedListener onOffsetChangedListener = this.onOffsetChangedListener;
        if (onOffsetChangedListener != null && (parent instanceof AppBarLayout)) {
            ((AppBarLayout) parent).removeOnOffsetChangedListener(onOffsetChangedListener);
        }
        super.onDetachedFromWindow();
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z8, int i9, int i10, int i11, int i12) {
        View view;
        super.onLayout(z8, i9, i10, i11, i12);
        C4628h0 c4628h0 = this.lastInsets;
        if (c4628h0 != null) {
            int iM18439g = c4628h0.m18439g();
            int childCount = getChildCount();
            for (int i13 = 0; i13 < childCount; i13++) {
                View childAt = getChildAt(i13);
                if (!C4647u.m18561p(childAt) && childAt.getTop() < iM18439g) {
                    C4647u.m18519O(childAt, iM18439g);
                }
            }
        }
        if (this.collapsingTitleEnabled && (view = this.dummyView) != null) {
            boolean z9 = C4647u.m18512H(view) && this.dummyView.getVisibility() == 0;
            this.drawCollapsingTitle = z9;
            if (z9) {
                boolean z10 = C4647u.m18567s(this) == 1;
                View view2 = this.toolbarDirectChild;
                if (view2 == null) {
                    view2 = this.toolbar;
                }
                int maxOffsetForPinChild = getMaxOffsetForPinChild(view2);
                DescendantOffsetUtils.getDescendantRect(this, this.dummyView, this.tmpRect);
                this.collapsingTextHelper.setCollapsedBounds(this.tmpRect.left + (z10 ? this.toolbar.getTitleMarginEnd() : this.toolbar.getTitleMarginStart()), this.tmpRect.top + maxOffsetForPinChild + this.toolbar.getTitleMarginTop(), this.tmpRect.right + (z10 ? this.toolbar.getTitleMarginStart() : this.toolbar.getTitleMarginEnd()), (this.tmpRect.bottom + maxOffsetForPinChild) - this.toolbar.getTitleMarginBottom());
                this.collapsingTextHelper.setExpandedBounds(z10 ? this.expandedMarginEnd : this.expandedMarginStart, this.tmpRect.top + this.expandedMarginTop, (i11 - i9) - (z10 ? this.expandedMarginStart : this.expandedMarginEnd), (i12 - i10) - this.expandedMarginBottom);
                this.collapsingTextHelper.recalculate();
            }
        }
        int childCount2 = getChildCount();
        for (int i14 = 0; i14 < childCount2; i14++) {
            getViewOffsetHelper(getChildAt(i14)).onViewLayout();
        }
        if (this.toolbar != null) {
            if (this.collapsingTitleEnabled && TextUtils.isEmpty(this.collapsingTextHelper.getText())) {
                setTitle(this.toolbar.getTitle());
            }
            View view3 = this.toolbarDirectChild;
            if (view3 == null || view3 == this) {
                setMinimumHeight(getHeightWithMargins(this.toolbar));
            } else {
                setMinimumHeight(getHeightWithMargins(view3));
            }
        }
        updateScrimVisibility();
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int i9, int i10) {
        ensureToolbar();
        super.onMeasure(i9, i10);
        int mode = View.MeasureSpec.getMode(i10);
        C4628h0 c4628h0 = this.lastInsets;
        int iM18439g = c4628h0 != null ? c4628h0.m18439g() : 0;
        if (mode != 0 || iM18439g <= 0) {
            return;
        }
        super.onMeasure(i9, View.MeasureSpec.makeMeasureSpec(getMeasuredHeight() + iM18439g, 1073741824));
    }

    @Override // android.view.View
    public void onSizeChanged(int i9, int i10, int i11, int i12) {
        super.onSizeChanged(i9, i10, i11, i12);
        Drawable drawable = this.contentScrim;
        if (drawable != null) {
            drawable.setBounds(0, 0, i9, i10);
        }
    }

    public C4628h0 onWindowInsetChanged(C4628h0 c4628h0) {
        C4628h0 c4628h02 = C4647u.m18561p(this) ? c4628h0 : null;
        if (!C0697c.m3461a(this.lastInsets, c4628h02)) {
            this.lastInsets = c4628h02;
            requestLayout();
        }
        return c4628h0.m18435c();
    }

    public void setCollapsedTitleGravity(int i9) {
        this.collapsingTextHelper.setCollapsedTextGravity(i9);
    }

    public void setCollapsedTitleTextAppearance(int i9) {
        this.collapsingTextHelper.setCollapsedTextAppearance(i9);
    }

    public void setCollapsedTitleTextColor(int i9) {
        setCollapsedTitleTextColor(ColorStateList.valueOf(i9));
    }

    public void setCollapsedTitleTypeface(Typeface typeface) {
        this.collapsingTextHelper.setCollapsedTypeface(typeface);
    }

    public void setContentScrim(Drawable drawable) {
        Drawable drawable2 = this.contentScrim;
        if (drawable2 != drawable) {
            if (drawable2 != null) {
                drawable2.setCallback(null);
            }
            Drawable drawableMutate = drawable != null ? drawable.mutate() : null;
            this.contentScrim = drawableMutate;
            if (drawableMutate != null) {
                drawableMutate.setBounds(0, 0, getWidth(), getHeight());
                this.contentScrim.setCallback(this);
                this.contentScrim.setAlpha(this.scrimAlpha);
            }
            C4647u.m18524T(this);
        }
    }

    public void setContentScrimColor(int i9) {
        setContentScrim(new ColorDrawable(i9));
    }

    public void setContentScrimResource(int i9) {
        setContentScrim(C6273a.m24025d(getContext(), i9));
    }

    public void setExpandedTitleColor(int i9) {
        setExpandedTitleTextColor(ColorStateList.valueOf(i9));
    }

    public void setExpandedTitleGravity(int i9) {
        this.collapsingTextHelper.setExpandedTextGravity(i9);
    }

    public void setExpandedTitleMargin(int i9, int i10, int i11, int i12) {
        this.expandedMarginStart = i9;
        this.expandedMarginTop = i10;
        this.expandedMarginEnd = i11;
        this.expandedMarginBottom = i12;
        requestLayout();
    }

    public void setExpandedTitleMarginBottom(int i9) {
        this.expandedMarginBottom = i9;
        requestLayout();
    }

    public void setExpandedTitleMarginEnd(int i9) {
        this.expandedMarginEnd = i9;
        requestLayout();
    }

    public void setExpandedTitleMarginStart(int i9) {
        this.expandedMarginStart = i9;
        requestLayout();
    }

    public void setExpandedTitleMarginTop(int i9) {
        this.expandedMarginTop = i9;
        requestLayout();
    }

    public void setExpandedTitleTextAppearance(int i9) {
        this.collapsingTextHelper.setExpandedTextAppearance(i9);
    }

    public void setExpandedTitleTextColor(ColorStateList colorStateList) {
        this.collapsingTextHelper.setExpandedTextColor(colorStateList);
    }

    public void setExpandedTitleTypeface(Typeface typeface) {
        this.collapsingTextHelper.setExpandedTypeface(typeface);
    }

    public void setScrimAlpha(int i9) {
        Toolbar toolbar;
        if (i9 != this.scrimAlpha) {
            if (this.contentScrim != null && (toolbar = this.toolbar) != null) {
                C4647u.m18524T(toolbar);
            }
            this.scrimAlpha = i9;
            C4647u.m18524T(this);
        }
    }

    public void setScrimAnimationDuration(long j9) {
        this.scrimAnimationDuration = j9;
    }

    public void setScrimVisibleHeightTrigger(int i9) {
        if (this.scrimVisibleHeightTrigger != i9) {
            this.scrimVisibleHeightTrigger = i9;
            updateScrimVisibility();
        }
    }

    public void setScrimsShown(boolean z8) {
        setScrimsShown(z8, C4647u.m18513I(this) && !isInEditMode());
    }

    public void setStatusBarScrim(Drawable drawable) {
        Drawable drawable2 = this.statusBarScrim;
        if (drawable2 != drawable) {
            if (drawable2 != null) {
                drawable2.setCallback(null);
            }
            Drawable drawableMutate = drawable != null ? drawable.mutate() : null;
            this.statusBarScrim = drawableMutate;
            if (drawableMutate != null) {
                if (drawableMutate.isStateful()) {
                    this.statusBarScrim.setState(getDrawableState());
                }
                C6494a.m24844g(this.statusBarScrim, C4647u.m18567s(this));
                this.statusBarScrim.setVisible(getVisibility() == 0, false);
                this.statusBarScrim.setCallback(this);
                this.statusBarScrim.setAlpha(this.scrimAlpha);
            }
            C4647u.m18524T(this);
        }
    }

    public void setStatusBarScrimColor(int i9) {
        setStatusBarScrim(new ColorDrawable(i9));
    }

    public void setStatusBarScrimResource(int i9) {
        setStatusBarScrim(C6273a.m24025d(getContext(), i9));
    }

    public void setTitle(CharSequence charSequence) {
        this.collapsingTextHelper.setText(charSequence);
        updateContentDescriptionFromTitle();
    }

    public void setTitleEnabled(boolean z8) {
        if (z8 != this.collapsingTitleEnabled) {
            this.collapsingTitleEnabled = z8;
            updateContentDescriptionFromTitle();
            updateDummyView();
            requestLayout();
        }
    }

    @Override // android.view.View
    public void setVisibility(int i9) {
        super.setVisibility(i9);
        boolean z8 = i9 == 0;
        Drawable drawable = this.statusBarScrim;
        if (drawable != null && drawable.isVisible() != z8) {
            this.statusBarScrim.setVisible(z8, false);
        }
        Drawable drawable2 = this.contentScrim;
        if (drawable2 == null || drawable2.isVisible() == z8) {
            return;
        }
        this.contentScrim.setVisible(z8, false);
    }

    public final void updateScrimVisibility() {
        if (this.contentScrim == null && this.statusBarScrim == null) {
            return;
        }
        setScrimsShown(getHeight() + this.currentOffset < getScrimVisibleHeightTrigger());
    }

    @Override // android.view.View
    public boolean verifyDrawable(Drawable drawable) {
        return super.verifyDrawable(drawable) || drawable == this.contentScrim || drawable == this.statusBarScrim;
    }

    public CollapsingToolbarLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public void setCollapsedTitleTextColor(ColorStateList colorStateList) {
        this.collapsingTextHelper.setCollapsedTextColor(colorStateList);
    }

    public void setScrimsShown(boolean z8, boolean z9) {
        if (this.scrimsAreShown != z8) {
            if (z9) {
                animateScrim(z8 ? 255 : 0);
            } else {
                setScrimAlpha(z8 ? 255 : 0);
            }
            this.scrimsAreShown = z8;
        }
    }

    public CollapsingToolbarLayout(Context context, AttributeSet attributeSet, int i9) {
        super(context, attributeSet, i9);
        this.refreshToolbar = true;
        this.tmpRect = new Rect();
        this.scrimVisibleHeightTrigger = -1;
        CollapsingTextHelper collapsingTextHelper = new CollapsingTextHelper(this);
        this.collapsingTextHelper = collapsingTextHelper;
        collapsingTextHelper.setTextSizeInterpolator(AnimationUtils.DECELERATE_INTERPOLATOR);
        TypedArray typedArrayObtainStyledAttributes = ThemeEnforcement.obtainStyledAttributes(context, attributeSet, C3476R.styleable.CollapsingToolbarLayout, i9, C3476R.style.Widget_Design_CollapsingToolbar, new int[0]);
        collapsingTextHelper.setExpandedTextGravity(typedArrayObtainStyledAttributes.getInt(C3476R.styleable.CollapsingToolbarLayout_expandedTitleGravity, 8388691));
        collapsingTextHelper.setCollapsedTextGravity(typedArrayObtainStyledAttributes.getInt(C3476R.styleable.CollapsingToolbarLayout_collapsedTitleGravity, 8388627));
        int dimensionPixelSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(C3476R.styleable.CollapsingToolbarLayout_expandedTitleMargin, 0);
        this.expandedMarginBottom = dimensionPixelSize;
        this.expandedMarginEnd = dimensionPixelSize;
        this.expandedMarginTop = dimensionPixelSize;
        this.expandedMarginStart = dimensionPixelSize;
        int i10 = C3476R.styleable.CollapsingToolbarLayout_expandedTitleMarginStart;
        if (typedArrayObtainStyledAttributes.hasValue(i10)) {
            this.expandedMarginStart = typedArrayObtainStyledAttributes.getDimensionPixelSize(i10, 0);
        }
        int i11 = C3476R.styleable.CollapsingToolbarLayout_expandedTitleMarginEnd;
        if (typedArrayObtainStyledAttributes.hasValue(i11)) {
            this.expandedMarginEnd = typedArrayObtainStyledAttributes.getDimensionPixelSize(i11, 0);
        }
        int i12 = C3476R.styleable.CollapsingToolbarLayout_expandedTitleMarginTop;
        if (typedArrayObtainStyledAttributes.hasValue(i12)) {
            this.expandedMarginTop = typedArrayObtainStyledAttributes.getDimensionPixelSize(i12, 0);
        }
        int i13 = C3476R.styleable.CollapsingToolbarLayout_expandedTitleMarginBottom;
        if (typedArrayObtainStyledAttributes.hasValue(i13)) {
            this.expandedMarginBottom = typedArrayObtainStyledAttributes.getDimensionPixelSize(i13, 0);
        }
        this.collapsingTitleEnabled = typedArrayObtainStyledAttributes.getBoolean(C3476R.styleable.CollapsingToolbarLayout_titleEnabled, true);
        setTitle(typedArrayObtainStyledAttributes.getText(C3476R.styleable.CollapsingToolbarLayout_title));
        collapsingTextHelper.setExpandedTextAppearance(C3476R.style.TextAppearance_Design_CollapsingToolbar_Expanded);
        collapsingTextHelper.setCollapsedTextAppearance(C0568i.TextAppearance_AppCompat_Widget_ActionBar_Title);
        int i14 = C3476R.styleable.CollapsingToolbarLayout_expandedTitleTextAppearance;
        if (typedArrayObtainStyledAttributes.hasValue(i14)) {
            collapsingTextHelper.setExpandedTextAppearance(typedArrayObtainStyledAttributes.getResourceId(i14, 0));
        }
        int i15 = C3476R.styleable.CollapsingToolbarLayout_collapsedTitleTextAppearance;
        if (typedArrayObtainStyledAttributes.hasValue(i15)) {
            collapsingTextHelper.setCollapsedTextAppearance(typedArrayObtainStyledAttributes.getResourceId(i15, 0));
        }
        this.scrimVisibleHeightTrigger = typedArrayObtainStyledAttributes.getDimensionPixelSize(C3476R.styleable.CollapsingToolbarLayout_scrimVisibleHeightTrigger, -1);
        this.scrimAnimationDuration = typedArrayObtainStyledAttributes.getInt(C3476R.styleable.CollapsingToolbarLayout_scrimAnimationDuration, DEFAULT_SCRIM_ANIMATION_DURATION);
        setContentScrim(typedArrayObtainStyledAttributes.getDrawable(C3476R.styleable.CollapsingToolbarLayout_contentScrim));
        setStatusBarScrim(typedArrayObtainStyledAttributes.getDrawable(C3476R.styleable.CollapsingToolbarLayout_statusBarScrim));
        this.toolbarId = typedArrayObtainStyledAttributes.getResourceId(C3476R.styleable.CollapsingToolbarLayout_toolbarId, -1);
        typedArrayObtainStyledAttributes.recycle();
        setWillNotDraw(false);
        C4647u.m18554l0(this, new InterfaceC4643q() { // from class: com.google.android.material.appbar.CollapsingToolbarLayout.1
            @Override // p042d0.InterfaceC4643q
            public C4628h0 onApplyWindowInsets(View view, C4628h0 c4628h0) {
                return CollapsingToolbarLayout.this.onWindowInsetChanged(c4628h0);
            }
        });
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup
    public LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-1, -1);
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup
    public FrameLayout.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup
    public FrameLayout.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return new LayoutParams(layoutParams);
    }

    public static class LayoutParams extends FrameLayout.LayoutParams {
        public static final int COLLAPSE_MODE_OFF = 0;
        public static final int COLLAPSE_MODE_PARALLAX = 2;
        public static final int COLLAPSE_MODE_PIN = 1;
        private static final float DEFAULT_PARALLAX_MULTIPLIER = 0.5f;
        int collapseMode;
        float parallaxMult;

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.collapseMode = 0;
            this.parallaxMult = DEFAULT_PARALLAX_MULTIPLIER;
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C3476R.styleable.CollapsingToolbarLayout_Layout);
            this.collapseMode = typedArrayObtainStyledAttributes.getInt(C3476R.styleable.CollapsingToolbarLayout_Layout_layout_collapseMode, 0);
            setParallaxMultiplier(typedArrayObtainStyledAttributes.getFloat(C3476R.styleable.CollapsingToolbarLayout_Layout_layout_collapseParallaxMultiplier, DEFAULT_PARALLAX_MULTIPLIER));
            typedArrayObtainStyledAttributes.recycle();
        }

        public int getCollapseMode() {
            return this.collapseMode;
        }

        public float getParallaxMultiplier() {
            return this.parallaxMult;
        }

        public void setCollapseMode(int i9) {
            this.collapseMode = i9;
        }

        public void setParallaxMultiplier(float f9) {
            this.parallaxMult = f9;
        }

        public LayoutParams(int i9, int i10) {
            super(i9, i10);
            this.collapseMode = 0;
            this.parallaxMult = DEFAULT_PARALLAX_MULTIPLIER;
        }

        public LayoutParams(int i9, int i10, int i11) {
            super(i9, i10, i11);
            this.collapseMode = 0;
            this.parallaxMult = DEFAULT_PARALLAX_MULTIPLIER;
        }

        public LayoutParams(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.collapseMode = 0;
            this.parallaxMult = DEFAULT_PARALLAX_MULTIPLIER;
        }

        public LayoutParams(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
            this.collapseMode = 0;
            this.parallaxMult = DEFAULT_PARALLAX_MULTIPLIER;
        }

        public LayoutParams(FrameLayout.LayoutParams layoutParams) {
            super(layoutParams);
            this.collapseMode = 0;
            this.parallaxMult = DEFAULT_PARALLAX_MULTIPLIER;
        }
    }
}
