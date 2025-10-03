package com.google.android.material.tabs;

import android.R;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.RippleDrawable;
import android.text.Layout;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AbstractC0119a;
import androidx.appcompat.widget.C0254s0;
import androidx.core.widget.C0337l;
import androidx.viewpager.widget.ViewPager;
import com.google.android.gms.auth.api.credentials.CredentialsApi;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.material.C3476R;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.internal.ViewUtils;
import com.google.android.material.resources.MaterialResources;
import com.google.android.material.ripple.RippleUtils;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import p010b.C0569j;
import p020c.C0694a;
import p021c0.C0700f;
import p021c0.C0701g;
import p021c0.InterfaceC0699e;
import p042d0.C4627h;
import p042d0.C4645s;
import p042d0.C4647u;
import p189s0.AbstractC6243a;
import p224w.C6494a;

@ViewPager.InterfaceC0552e
/* loaded from: classes2.dex */
public class TabLayout extends HorizontalScrollView {
    private static final int ANIMATION_DURATION = 300;
    static final int DEFAULT_GAP_TEXT_ICON = 8;
    private static final int DEFAULT_HEIGHT = 48;
    private static final int DEFAULT_HEIGHT_WITH_TEXT_ICON = 72;
    static final int FIXED_WRAP_GUTTER_MIN = 16;
    public static final int GRAVITY_CENTER = 1;
    public static final int GRAVITY_FILL = 0;
    public static final int INDICATOR_GRAVITY_BOTTOM = 0;
    public static final int INDICATOR_GRAVITY_CENTER = 1;
    public static final int INDICATOR_GRAVITY_STRETCH = 3;
    public static final int INDICATOR_GRAVITY_TOP = 2;
    private static final int INVALID_WIDTH = -1;
    private static final int MIN_INDICATOR_WIDTH = 24;
    public static final int MODE_FIXED = 1;
    public static final int MODE_SCROLLABLE = 0;
    private static final int TAB_MIN_WIDTH_MARGIN = 56;
    private static final InterfaceC0699e<Tab> tabPool = new C0701g(16);
    private AdapterChangeListener adapterChangeListener;
    private int contentInsetStart;
    private BaseOnTabSelectedListener currentVpSelectedListener;
    boolean inlineLabel;
    int mode;
    private TabLayoutOnPageChangeListener pageChangeListener;
    private AbstractC6243a pagerAdapter;
    private DataSetObserver pagerAdapterObserver;
    private final int requestedTabMaxWidth;
    private final int requestedTabMinWidth;
    private ValueAnimator scrollAnimator;
    private final int scrollableTabMinWidth;
    private BaseOnTabSelectedListener selectedListener;
    private final ArrayList<BaseOnTabSelectedListener> selectedListeners;
    private Tab selectedTab;
    private boolean setupViewPagerImplicitly;
    private final SlidingTabIndicator slidingTabIndicator;
    final int tabBackgroundResId;
    int tabGravity;
    ColorStateList tabIconTint;
    PorterDuff.Mode tabIconTintMode;
    int tabIndicatorAnimationDuration;
    boolean tabIndicatorFullWidth;
    int tabIndicatorGravity;
    int tabMaxWidth;
    int tabPaddingBottom;
    int tabPaddingEnd;
    int tabPaddingStart;
    int tabPaddingTop;
    ColorStateList tabRippleColorStateList;
    Drawable tabSelectedIndicator;
    int tabTextAppearance;
    ColorStateList tabTextColors;
    float tabTextMultiLineSize;
    float tabTextSize;
    private final RectF tabViewContentBounds;
    private final InterfaceC0699e<TabView> tabViewPool;
    private final ArrayList<Tab> tabs;
    boolean unboundedRipple;
    ViewPager viewPager;

    public class AdapterChangeListener implements ViewPager.InterfaceC0556i {
        private boolean autoRefresh;

        public AdapterChangeListener() {
        }

        @Override // androidx.viewpager.widget.ViewPager.InterfaceC0556i
        public void onAdapterChanged(ViewPager viewPager, AbstractC6243a abstractC6243a, AbstractC6243a abstractC6243a2) {
            TabLayout tabLayout = TabLayout.this;
            if (tabLayout.viewPager == viewPager) {
                tabLayout.setPagerAdapter(abstractC6243a2, this.autoRefresh);
            }
        }

        public void setAutoRefresh(boolean z8) {
            this.autoRefresh = z8;
        }
    }

    public interface BaseOnTabSelectedListener<T extends Tab> {
        void onTabReselected(T t8);

        void onTabSelected(T t8);

        void onTabUnselected(T t8);
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface Mode {
    }

    public interface OnTabSelectedListener extends BaseOnTabSelectedListener<Tab> {
    }

    public class PagerAdapterObserver extends DataSetObserver {
        public PagerAdapterObserver() {
        }

        @Override // android.database.DataSetObserver
        public void onChanged() {
            TabLayout.this.populateFromPagerAdapter();
        }

        @Override // android.database.DataSetObserver
        public void onInvalidated() {
            TabLayout.this.populateFromPagerAdapter();
        }
    }

    public class SlidingTabIndicator extends LinearLayout {
        private final GradientDrawable defaultSelectionIndicator;
        private ValueAnimator indicatorAnimator;
        private int indicatorLeft;
        private int indicatorRight;
        private int layoutDirection;
        private int selectedIndicatorHeight;
        private final Paint selectedIndicatorPaint;
        int selectedPosition;
        float selectionOffset;

        public SlidingTabIndicator(Context context) {
            super(context);
            this.selectedPosition = -1;
            this.layoutDirection = -1;
            this.indicatorLeft = -1;
            this.indicatorRight = -1;
            setWillNotDraw(false);
            this.selectedIndicatorPaint = new Paint();
            this.defaultSelectionIndicator = new GradientDrawable();
        }

        private void calculateTabViewContentBounds(TabView tabView, RectF rectF) {
            int contentWidth = tabView.getContentWidth();
            if (contentWidth < TabLayout.this.dpToPx(24)) {
                contentWidth = TabLayout.this.dpToPx(24);
            }
            int left = (tabView.getLeft() + tabView.getRight()) / 2;
            int i9 = contentWidth / 2;
            rectF.set(left - i9, BitmapDescriptorFactory.HUE_RED, left + i9, BitmapDescriptorFactory.HUE_RED);
        }

        private void updateIndicatorPosition() {
            int left;
            int right;
            View childAt = getChildAt(this.selectedPosition);
            if (childAt == null || childAt.getWidth() <= 0) {
                left = -1;
                right = -1;
            } else {
                left = childAt.getLeft();
                right = childAt.getRight();
                TabLayout tabLayout = TabLayout.this;
                if (!tabLayout.tabIndicatorFullWidth && (childAt instanceof TabView)) {
                    calculateTabViewContentBounds((TabView) childAt, tabLayout.tabViewContentBounds);
                    left = (int) TabLayout.this.tabViewContentBounds.left;
                    right = (int) TabLayout.this.tabViewContentBounds.right;
                }
                if (this.selectionOffset > BitmapDescriptorFactory.HUE_RED && this.selectedPosition < getChildCount() - 1) {
                    View childAt2 = getChildAt(this.selectedPosition + 1);
                    int left2 = childAt2.getLeft();
                    int right2 = childAt2.getRight();
                    TabLayout tabLayout2 = TabLayout.this;
                    if (!tabLayout2.tabIndicatorFullWidth && (childAt2 instanceof TabView)) {
                        calculateTabViewContentBounds((TabView) childAt2, tabLayout2.tabViewContentBounds);
                        left2 = (int) TabLayout.this.tabViewContentBounds.left;
                        right2 = (int) TabLayout.this.tabViewContentBounds.right;
                    }
                    float f9 = this.selectionOffset;
                    left = (int) ((left2 * f9) + ((1.0f - f9) * left));
                    right = (int) ((right2 * f9) + ((1.0f - f9) * right));
                }
            }
            setIndicatorPosition(left, right);
        }

        public void animateIndicatorToPosition(final int i9, int i10) {
            ValueAnimator valueAnimator = this.indicatorAnimator;
            if (valueAnimator != null && valueAnimator.isRunning()) {
                this.indicatorAnimator.cancel();
            }
            View childAt = getChildAt(i9);
            if (childAt == null) {
                updateIndicatorPosition();
                return;
            }
            int left = childAt.getLeft();
            int right = childAt.getRight();
            TabLayout tabLayout = TabLayout.this;
            if (!tabLayout.tabIndicatorFullWidth && (childAt instanceof TabView)) {
                calculateTabViewContentBounds((TabView) childAt, tabLayout.tabViewContentBounds);
                left = (int) TabLayout.this.tabViewContentBounds.left;
                right = (int) TabLayout.this.tabViewContentBounds.right;
            }
            final int i11 = left;
            final int i12 = right;
            final int i13 = this.indicatorLeft;
            final int i14 = this.indicatorRight;
            if (i13 == i11 && i14 == i12) {
                return;
            }
            ValueAnimator valueAnimator2 = new ValueAnimator();
            this.indicatorAnimator = valueAnimator2;
            valueAnimator2.setInterpolator(AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR);
            valueAnimator2.setDuration(i10);
            valueAnimator2.setFloatValues(BitmapDescriptorFactory.HUE_RED, 1.0f);
            valueAnimator2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.google.android.material.tabs.TabLayout.SlidingTabIndicator.1
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public void onAnimationUpdate(ValueAnimator valueAnimator3) {
                    float animatedFraction = valueAnimator3.getAnimatedFraction();
                    SlidingTabIndicator.this.setIndicatorPosition(AnimationUtils.lerp(i13, i11, animatedFraction), AnimationUtils.lerp(i14, i12, animatedFraction));
                }
            });
            valueAnimator2.addListener(new AnimatorListenerAdapter() { // from class: com.google.android.material.tabs.TabLayout.SlidingTabIndicator.2
                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animator) {
                    SlidingTabIndicator slidingTabIndicator = SlidingTabIndicator.this;
                    slidingTabIndicator.selectedPosition = i9;
                    slidingTabIndicator.selectionOffset = BitmapDescriptorFactory.HUE_RED;
                }
            });
            valueAnimator2.start();
        }

        public boolean childrenNeedLayout() {
            int childCount = getChildCount();
            for (int i9 = 0; i9 < childCount; i9++) {
                if (getChildAt(i9).getWidth() <= 0) {
                    return true;
                }
            }
            return false;
        }

        @Override // android.view.View
        public void draw(Canvas canvas) {
            Drawable drawable = TabLayout.this.tabSelectedIndicator;
            int height = 0;
            int intrinsicHeight = drawable != null ? drawable.getIntrinsicHeight() : 0;
            int i9 = this.selectedIndicatorHeight;
            if (i9 >= 0) {
                intrinsicHeight = i9;
            }
            int i10 = TabLayout.this.tabIndicatorGravity;
            if (i10 == 0) {
                height = getHeight() - intrinsicHeight;
                intrinsicHeight = getHeight();
            } else if (i10 == 1) {
                height = (getHeight() - intrinsicHeight) / 2;
                intrinsicHeight = (getHeight() + intrinsicHeight) / 2;
            } else if (i10 != 2) {
                intrinsicHeight = i10 != 3 ? 0 : getHeight();
            }
            int i11 = this.indicatorLeft;
            if (i11 >= 0 && this.indicatorRight > i11) {
                Drawable drawable2 = TabLayout.this.tabSelectedIndicator;
                if (drawable2 == null) {
                    drawable2 = this.defaultSelectionIndicator;
                }
                Drawable drawableM24849l = C6494a.m24849l(drawable2);
                drawableM24849l.setBounds(this.indicatorLeft, height, this.indicatorRight, intrinsicHeight);
                Paint paint = this.selectedIndicatorPaint;
                if (paint != null) {
                    C6494a.m24845h(drawableM24849l, paint.getColor());
                }
                drawableM24849l.draw(canvas);
            }
            super.draw(canvas);
        }

        public float getIndicatorPosition() {
            return this.selectedPosition + this.selectionOffset;
        }

        @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
        public void onLayout(boolean z8, int i9, int i10, int i11, int i12) {
            super.onLayout(z8, i9, i10, i11, i12);
            ValueAnimator valueAnimator = this.indicatorAnimator;
            if (valueAnimator == null || !valueAnimator.isRunning()) {
                updateIndicatorPosition();
                return;
            }
            this.indicatorAnimator.cancel();
            animateIndicatorToPosition(this.selectedPosition, Math.round((1.0f - this.indicatorAnimator.getAnimatedFraction()) * this.indicatorAnimator.getDuration()));
        }

        @Override // android.widget.LinearLayout, android.view.View
        public void onMeasure(int i9, int i10) {
            super.onMeasure(i9, i10);
            if (View.MeasureSpec.getMode(i9) != 1073741824) {
                return;
            }
            TabLayout tabLayout = TabLayout.this;
            boolean z8 = true;
            if (tabLayout.mode == 1 && tabLayout.tabGravity == 1) {
                int childCount = getChildCount();
                int iMax = 0;
                for (int i11 = 0; i11 < childCount; i11++) {
                    View childAt = getChildAt(i11);
                    if (childAt.getVisibility() == 0) {
                        iMax = Math.max(iMax, childAt.getMeasuredWidth());
                    }
                }
                if (iMax <= 0) {
                    return;
                }
                if (iMax * childCount <= getMeasuredWidth() - (TabLayout.this.dpToPx(16) * 2)) {
                    boolean z9 = false;
                    for (int i12 = 0; i12 < childCount; i12++) {
                        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) getChildAt(i12).getLayoutParams();
                        if (layoutParams.width != iMax || layoutParams.weight != BitmapDescriptorFactory.HUE_RED) {
                            layoutParams.width = iMax;
                            layoutParams.weight = BitmapDescriptorFactory.HUE_RED;
                            z9 = true;
                        }
                    }
                    z8 = z9;
                } else {
                    TabLayout tabLayout2 = TabLayout.this;
                    tabLayout2.tabGravity = 0;
                    tabLayout2.updateTabViews(false);
                }
                if (z8) {
                    super.onMeasure(i9, i10);
                }
            }
        }

        @Override // android.widget.LinearLayout, android.view.View
        public void onRtlPropertiesChanged(int i9) {
            super.onRtlPropertiesChanged(i9);
        }

        public void setIndicatorPosition(int i9, int i10) {
            if (i9 == this.indicatorLeft && i10 == this.indicatorRight) {
                return;
            }
            this.indicatorLeft = i9;
            this.indicatorRight = i10;
            C4647u.m18524T(this);
        }

        public void setIndicatorPositionFromTabPosition(int i9, float f9) {
            ValueAnimator valueAnimator = this.indicatorAnimator;
            if (valueAnimator != null && valueAnimator.isRunning()) {
                this.indicatorAnimator.cancel();
            }
            this.selectedPosition = i9;
            this.selectionOffset = f9;
            updateIndicatorPosition();
        }

        public void setSelectedIndicatorColor(int i9) {
            if (this.selectedIndicatorPaint.getColor() != i9) {
                this.selectedIndicatorPaint.setColor(i9);
                C4647u.m18524T(this);
            }
        }

        public void setSelectedIndicatorHeight(int i9) {
            if (this.selectedIndicatorHeight != i9) {
                this.selectedIndicatorHeight = i9;
                C4647u.m18524T(this);
            }
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface TabGravity {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface TabIndicatorGravity {
    }

    public static class TabLayoutOnPageChangeListener implements ViewPager.InterfaceC0557j {
        private int previousScrollState;
        private int scrollState;
        private final WeakReference<TabLayout> tabLayoutRef;

        public TabLayoutOnPageChangeListener(TabLayout tabLayout) {
            this.tabLayoutRef = new WeakReference<>(tabLayout);
        }

        @Override // androidx.viewpager.widget.ViewPager.InterfaceC0557j
        public void onPageScrollStateChanged(int i9) {
            this.previousScrollState = this.scrollState;
            this.scrollState = i9;
        }

        @Override // androidx.viewpager.widget.ViewPager.InterfaceC0557j
        public void onPageScrolled(int i9, float f9, int i10) {
            TabLayout tabLayout = this.tabLayoutRef.get();
            if (tabLayout != null) {
                int i11 = this.scrollState;
                tabLayout.setScrollPosition(i9, f9, i11 != 2 || this.previousScrollState == 1, (i11 == 2 && this.previousScrollState == 0) ? false : true);
            }
        }

        @Override // androidx.viewpager.widget.ViewPager.InterfaceC0557j
        public void onPageSelected(int i9) {
            TabLayout tabLayout = this.tabLayoutRef.get();
            if (tabLayout == null || tabLayout.getSelectedTabPosition() == i9 || i9 >= tabLayout.getTabCount()) {
                return;
            }
            int i10 = this.scrollState;
            tabLayout.selectTab(tabLayout.getTabAt(i9), i10 == 0 || (i10 == 2 && this.previousScrollState == 0));
        }

        public void reset() {
            this.scrollState = 0;
            this.previousScrollState = 0;
        }
    }

    public class TabView extends LinearLayout {
        private Drawable baseBackgroundDrawable;
        private ImageView customIconView;
        private TextView customTextView;
        private View customView;
        private int defaultMaxLines;
        private ImageView iconView;
        private Tab tab;
        private TextView textView;

        public TabView(Context context) {
            super(context);
            this.defaultMaxLines = 2;
            updateBackgroundDrawable(context);
            C4647u.m18556m0(this, TabLayout.this.tabPaddingStart, TabLayout.this.tabPaddingTop, TabLayout.this.tabPaddingEnd, TabLayout.this.tabPaddingBottom);
            setGravity(17);
            setOrientation(!TabLayout.this.inlineLabel ? 1 : 0);
            setClickable(true);
            C4647u.m18558n0(this, C4645s.m18502b(getContext(), CredentialsApi.ACTIVITY_RESULT_NO_HINTS_AVAILABLE));
        }

        private float approximateLineWidth(Layout layout, int i9, float f9) {
            return layout.getLineWidth(i9) * (f9 / layout.getPaint().getTextSize());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void drawBackground(Canvas canvas) {
            Drawable drawable = this.baseBackgroundDrawable;
            if (drawable != null) {
                drawable.setBounds(getLeft(), getTop(), getRight(), getBottom());
                this.baseBackgroundDrawable.draw(canvas);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public int getContentWidth() {
            View[] viewArr = {this.textView, this.iconView, this.customView};
            int iMax = 0;
            int iMin = 0;
            boolean z8 = false;
            for (int i9 = 0; i9 < 3; i9++) {
                View view = viewArr[i9];
                if (view != null && view.getVisibility() == 0) {
                    iMin = z8 ? Math.min(iMin, view.getLeft()) : view.getLeft();
                    iMax = z8 ? Math.max(iMax, view.getRight()) : view.getRight();
                    z8 = true;
                }
            }
            return iMax - iMin;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r3v0, types: [android.graphics.drawable.RippleDrawable] */
        public void updateBackgroundDrawable(Context context) {
            int i9 = TabLayout.this.tabBackgroundResId;
            if (i9 != 0) {
                Drawable drawableM3458b = C0694a.m3458b(context, i9);
                this.baseBackgroundDrawable = drawableM3458b;
                if (drawableM3458b != null && drawableM3458b.isStateful()) {
                    this.baseBackgroundDrawable.setState(getDrawableState());
                }
            } else {
                this.baseBackgroundDrawable = null;
            }
            GradientDrawable gradientDrawable = new GradientDrawable();
            gradientDrawable.setColor(0);
            if (TabLayout.this.tabRippleColorStateList != null) {
                GradientDrawable gradientDrawable2 = new GradientDrawable();
                gradientDrawable2.setCornerRadius(1.0E-5f);
                gradientDrawable2.setColor(-1);
                ColorStateList colorStateListConvertToRippleDrawableColor = RippleUtils.convertToRippleDrawableColor(TabLayout.this.tabRippleColorStateList);
                boolean z8 = TabLayout.this.unboundedRipple;
                if (z8) {
                    gradientDrawable = null;
                }
                gradientDrawable = new RippleDrawable(colorStateListConvertToRippleDrawableColor, gradientDrawable, z8 ? null : gradientDrawable2);
            }
            C4647u.m18534b0(this, gradientDrawable);
            TabLayout.this.invalidate();
        }

        private void updateTextAndIcon(TextView textView, ImageView imageView) {
            Tab tab = this.tab;
            Drawable drawableMutate = (tab == null || tab.getIcon() == null) ? null : C6494a.m24849l(this.tab.getIcon()).mutate();
            Tab tab2 = this.tab;
            CharSequence text = tab2 != null ? tab2.getText() : null;
            if (imageView != null) {
                if (drawableMutate != null) {
                    imageView.setImageDrawable(drawableMutate);
                    imageView.setVisibility(0);
                    setVisibility(0);
                } else {
                    imageView.setVisibility(8);
                    imageView.setImageDrawable(null);
                }
            }
            boolean z8 = !TextUtils.isEmpty(text);
            if (textView != null) {
                if (z8) {
                    textView.setText(text);
                    textView.setVisibility(0);
                    setVisibility(0);
                } else {
                    textView.setVisibility(8);
                    textView.setText((CharSequence) null);
                }
            }
            if (imageView != null) {
                ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) imageView.getLayoutParams();
                int iDpToPx = (z8 && imageView.getVisibility() == 0) ? TabLayout.this.dpToPx(8) : 0;
                if (TabLayout.this.inlineLabel) {
                    if (iDpToPx != C4627h.m18428a(marginLayoutParams)) {
                        C4627h.m18430c(marginLayoutParams, iDpToPx);
                        marginLayoutParams.bottomMargin = 0;
                        imageView.setLayoutParams(marginLayoutParams);
                        imageView.requestLayout();
                    }
                } else if (iDpToPx != marginLayoutParams.bottomMargin) {
                    marginLayoutParams.bottomMargin = iDpToPx;
                    C4627h.m18430c(marginLayoutParams, 0);
                    imageView.setLayoutParams(marginLayoutParams);
                    imageView.requestLayout();
                }
            }
            Tab tab3 = this.tab;
            C0254s0.m1061a(this, z8 ? null : tab3 != null ? tab3.contentDesc : null);
        }

        @Override // android.view.ViewGroup, android.view.View
        public void drawableStateChanged() {
            super.drawableStateChanged();
            int[] drawableState = getDrawableState();
            Drawable drawable = this.baseBackgroundDrawable;
            boolean state = false;
            if (drawable != null && drawable.isStateful()) {
                state = false | this.baseBackgroundDrawable.setState(drawableState);
            }
            if (state) {
                invalidate();
                TabLayout.this.invalidate();
            }
        }

        public Tab getTab() {
            return this.tab;
        }

        @Override // android.view.View
        public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
            super.onInitializeAccessibilityEvent(accessibilityEvent);
            accessibilityEvent.setClassName(AbstractC0119a.c.class.getName());
        }

        @Override // android.view.View
        @TargetApi(14)
        public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
            super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
            accessibilityNodeInfo.setClassName(AbstractC0119a.c.class.getName());
        }

        @Override // android.widget.LinearLayout, android.view.View
        public void onMeasure(int i9, int i10) {
            Layout layout;
            int size = View.MeasureSpec.getSize(i9);
            int mode = View.MeasureSpec.getMode(i9);
            int tabMaxWidth = TabLayout.this.getTabMaxWidth();
            if (tabMaxWidth > 0 && (mode == 0 || size > tabMaxWidth)) {
                i9 = View.MeasureSpec.makeMeasureSpec(TabLayout.this.tabMaxWidth, Integer.MIN_VALUE);
            }
            super.onMeasure(i9, i10);
            if (this.textView != null) {
                float f9 = TabLayout.this.tabTextSize;
                int i11 = this.defaultMaxLines;
                ImageView imageView = this.iconView;
                boolean z8 = true;
                if (imageView == null || imageView.getVisibility() != 0) {
                    TextView textView = this.textView;
                    if (textView != null && textView.getLineCount() > 1) {
                        f9 = TabLayout.this.tabTextMultiLineSize;
                    }
                } else {
                    i11 = 1;
                }
                float textSize = this.textView.getTextSize();
                int lineCount = this.textView.getLineCount();
                int iM1609d = C0337l.m1609d(this.textView);
                if (f9 != textSize || (iM1609d >= 0 && i11 != iM1609d)) {
                    if (TabLayout.this.mode == 1 && f9 > textSize && lineCount == 1 && ((layout = this.textView.getLayout()) == null || approximateLineWidth(layout, 0, f9) > (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight())) {
                        z8 = false;
                    }
                    if (z8) {
                        this.textView.setTextSize(0, f9);
                        this.textView.setMaxLines(i11);
                        super.onMeasure(i9, i10);
                    }
                }
            }
        }

        @Override // android.view.View
        public boolean performClick() {
            boolean zPerformClick = super.performClick();
            if (this.tab == null) {
                return zPerformClick;
            }
            if (!zPerformClick) {
                playSoundEffect(0);
            }
            this.tab.select();
            return true;
        }

        public void reset() {
            setTab(null);
            setSelected(false);
        }

        @Override // android.view.View
        public void setSelected(boolean z8) {
            if (isSelected() != z8) {
            }
            super.setSelected(z8);
            TextView textView = this.textView;
            if (textView != null) {
                textView.setSelected(z8);
            }
            ImageView imageView = this.iconView;
            if (imageView != null) {
                imageView.setSelected(z8);
            }
            View view = this.customView;
            if (view != null) {
                view.setSelected(z8);
            }
        }

        public void setTab(Tab tab) {
            if (tab != this.tab) {
                this.tab = tab;
                update();
            }
        }

        public final void update() {
            Tab tab = this.tab;
            Drawable drawableMutate = null;
            View customView = tab != null ? tab.getCustomView() : null;
            if (customView != null) {
                ViewParent parent = customView.getParent();
                if (parent != this) {
                    if (parent != null) {
                        ((ViewGroup) parent).removeView(customView);
                    }
                    addView(customView);
                }
                this.customView = customView;
                TextView textView = this.textView;
                if (textView != null) {
                    textView.setVisibility(8);
                }
                ImageView imageView = this.iconView;
                if (imageView != null) {
                    imageView.setVisibility(8);
                    this.iconView.setImageDrawable(null);
                }
                TextView textView2 = (TextView) customView.findViewById(R.id.text1);
                this.customTextView = textView2;
                if (textView2 != null) {
                    this.defaultMaxLines = C0337l.m1609d(textView2);
                }
                this.customIconView = (ImageView) customView.findViewById(R.id.icon);
            } else {
                View view = this.customView;
                if (view != null) {
                    removeView(view);
                    this.customView = null;
                }
                this.customTextView = null;
                this.customIconView = null;
            }
            boolean z8 = false;
            if (this.customView == null) {
                if (this.iconView == null) {
                    ImageView imageView2 = (ImageView) LayoutInflater.from(getContext()).inflate(C3476R.layout.design_layout_tab_icon, (ViewGroup) this, false);
                    addView(imageView2, 0);
                    this.iconView = imageView2;
                }
                if (tab != null && tab.getIcon() != null) {
                    drawableMutate = C6494a.m24849l(tab.getIcon()).mutate();
                }
                if (drawableMutate != null) {
                    C6494a.m24846i(drawableMutate, TabLayout.this.tabIconTint);
                    PorterDuff.Mode mode = TabLayout.this.tabIconTintMode;
                    if (mode != null) {
                        C6494a.m24847j(drawableMutate, mode);
                    }
                }
                if (this.textView == null) {
                    TextView textView3 = (TextView) LayoutInflater.from(getContext()).inflate(C3476R.layout.design_layout_tab_text, (ViewGroup) this, false);
                    addView(textView3);
                    this.textView = textView3;
                    this.defaultMaxLines = C0337l.m1609d(textView3);
                }
                C0337l.m1620o(this.textView, TabLayout.this.tabTextAppearance);
                ColorStateList colorStateList = TabLayout.this.tabTextColors;
                if (colorStateList != null) {
                    this.textView.setTextColor(colorStateList);
                }
                updateTextAndIcon(this.textView, this.iconView);
            } else {
                TextView textView4 = this.customTextView;
                if (textView4 != null || this.customIconView != null) {
                    updateTextAndIcon(textView4, this.customIconView);
                }
            }
            if (tab != null && !TextUtils.isEmpty(tab.contentDesc)) {
                setContentDescription(tab.contentDesc);
            }
            if (tab != null && tab.isSelected()) {
                z8 = true;
            }
            setSelected(z8);
        }

        public final void updateOrientation() {
            setOrientation(!TabLayout.this.inlineLabel ? 1 : 0);
            TextView textView = this.customTextView;
            if (textView == null && this.customIconView == null) {
                updateTextAndIcon(this.textView, this.iconView);
            } else {
                updateTextAndIcon(textView, this.customIconView);
            }
        }
    }

    public static class ViewPagerOnTabSelectedListener implements OnTabSelectedListener {
        private final ViewPager viewPager;

        public ViewPagerOnTabSelectedListener(ViewPager viewPager) {
            this.viewPager = viewPager;
        }

        @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
        public void onTabReselected(Tab tab) {
        }

        @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
        public void onTabSelected(Tab tab) {
            this.viewPager.setCurrentItem(tab.getPosition());
        }

        @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
        public void onTabUnselected(Tab tab) {
        }
    }

    public TabLayout(Context context) {
        this(context, null);
    }

    private void addTabFromItemView(TabItem tabItem) {
        Tab tabNewTab = newTab();
        CharSequence charSequence = tabItem.text;
        if (charSequence != null) {
            tabNewTab.setText(charSequence);
        }
        Drawable drawable = tabItem.icon;
        if (drawable != null) {
            tabNewTab.setIcon(drawable);
        }
        int i9 = tabItem.customLayout;
        if (i9 != 0) {
            tabNewTab.setCustomView(i9);
        }
        if (!TextUtils.isEmpty(tabItem.getContentDescription())) {
            tabNewTab.setContentDescription(tabItem.getContentDescription());
        }
        addTab(tabNewTab);
    }

    private void addTabView(Tab tab) {
        this.slidingTabIndicator.addView(tab.view, tab.getPosition(), createLayoutParamsForTabs());
    }

    private void addViewInternal(View view) {
        if (!(view instanceof TabItem)) {
            throw new IllegalArgumentException("Only TabItem instances can be added to TabLayout");
        }
        addTabFromItemView((TabItem) view);
    }

    private void animateToTab(int i9) {
        if (i9 == -1) {
            return;
        }
        if (getWindowToken() == null || !C4647u.m18513I(this) || this.slidingTabIndicator.childrenNeedLayout()) {
            setScrollPosition(i9, BitmapDescriptorFactory.HUE_RED, true);
            return;
        }
        int scrollX = getScrollX();
        int iCalculateScrollXForTab = calculateScrollXForTab(i9, BitmapDescriptorFactory.HUE_RED);
        if (scrollX != iCalculateScrollXForTab) {
            ensureScrollAnimator();
            this.scrollAnimator.setIntValues(scrollX, iCalculateScrollXForTab);
            this.scrollAnimator.start();
        }
        this.slidingTabIndicator.animateIndicatorToPosition(i9, this.tabIndicatorAnimationDuration);
    }

    private void applyModeAndGravity() {
        C4647u.m18556m0(this.slidingTabIndicator, this.mode == 0 ? Math.max(0, this.contentInsetStart - this.tabPaddingStart) : 0, 0, 0, 0);
        int i9 = this.mode;
        if (i9 == 0) {
            this.slidingTabIndicator.setGravity(8388611);
        } else if (i9 == 1) {
            this.slidingTabIndicator.setGravity(1);
        }
        updateTabViews(true);
    }

    private int calculateScrollXForTab(int i9, float f9) {
        if (this.mode != 0) {
            return 0;
        }
        View childAt = this.slidingTabIndicator.getChildAt(i9);
        int i10 = i9 + 1;
        View childAt2 = i10 < this.slidingTabIndicator.getChildCount() ? this.slidingTabIndicator.getChildAt(i10) : null;
        int width = childAt != null ? childAt.getWidth() : 0;
        int width2 = childAt2 != null ? childAt2.getWidth() : 0;
        int left = (childAt.getLeft() + (width / 2)) - (getWidth() / 2);
        int i11 = (int) ((width + width2) * 0.5f * f9);
        return C4647u.m18567s(this) == 0 ? left + i11 : left - i11;
    }

    private void configureTab(Tab tab, int i9) {
        tab.setPosition(i9);
        this.tabs.add(i9, tab);
        int size = this.tabs.size();
        while (true) {
            i9++;
            if (i9 >= size) {
                return;
            } else {
                this.tabs.get(i9).setPosition(i9);
            }
        }
    }

    private static ColorStateList createColorStateList(int i9, int i10) {
        return new ColorStateList(new int[][]{HorizontalScrollView.SELECTED_STATE_SET, HorizontalScrollView.EMPTY_STATE_SET}, new int[]{i10, i9});
    }

    private LinearLayout.LayoutParams createLayoutParamsForTabs() {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -1);
        updateTabViewLayoutParams(layoutParams);
        return layoutParams;
    }

    private TabView createTabView(Tab tab) {
        InterfaceC0699e<TabView> interfaceC0699e = this.tabViewPool;
        TabView tabViewMo3465b = interfaceC0699e != null ? interfaceC0699e.mo3465b() : null;
        if (tabViewMo3465b == null) {
            tabViewMo3465b = new TabView(getContext());
        }
        tabViewMo3465b.setTab(tab);
        tabViewMo3465b.setFocusable(true);
        tabViewMo3465b.setMinimumWidth(getTabMinWidth());
        if (TextUtils.isEmpty(tab.contentDesc)) {
            tabViewMo3465b.setContentDescription(tab.text);
        } else {
            tabViewMo3465b.setContentDescription(tab.contentDesc);
        }
        return tabViewMo3465b;
    }

    private void dispatchTabReselected(Tab tab) {
        for (int size = this.selectedListeners.size() - 1; size >= 0; size--) {
            this.selectedListeners.get(size).onTabReselected(tab);
        }
    }

    private void dispatchTabSelected(Tab tab) {
        for (int size = this.selectedListeners.size() - 1; size >= 0; size--) {
            this.selectedListeners.get(size).onTabSelected(tab);
        }
    }

    private void dispatchTabUnselected(Tab tab) {
        for (int size = this.selectedListeners.size() - 1; size >= 0; size--) {
            this.selectedListeners.get(size).onTabUnselected(tab);
        }
    }

    private void ensureScrollAnimator() {
        if (this.scrollAnimator == null) {
            ValueAnimator valueAnimator = new ValueAnimator();
            this.scrollAnimator = valueAnimator;
            valueAnimator.setInterpolator(AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR);
            this.scrollAnimator.setDuration(this.tabIndicatorAnimationDuration);
            this.scrollAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.google.android.material.tabs.TabLayout.1
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public void onAnimationUpdate(ValueAnimator valueAnimator2) {
                    TabLayout.this.scrollTo(((Integer) valueAnimator2.getAnimatedValue()).intValue(), 0);
                }
            });
        }
    }

    private int getDefaultHeight() {
        int size = this.tabs.size();
        boolean z8 = false;
        int i9 = 0;
        while (true) {
            if (i9 < size) {
                Tab tab = this.tabs.get(i9);
                if (tab != null && tab.getIcon() != null && !TextUtils.isEmpty(tab.getText())) {
                    z8 = true;
                    break;
                }
                i9++;
            } else {
                break;
            }
        }
        return (!z8 || this.inlineLabel) ? 48 : 72;
    }

    private int getTabMinWidth() {
        int i9 = this.requestedTabMinWidth;
        if (i9 != -1) {
            return i9;
        }
        if (this.mode == 0) {
            return this.scrollableTabMinWidth;
        }
        return 0;
    }

    private int getTabScrollRange() {
        return Math.max(0, ((this.slidingTabIndicator.getWidth() - getWidth()) - getPaddingLeft()) - getPaddingRight());
    }

    private void removeTabViewAt(int i9) {
        TabView tabView = (TabView) this.slidingTabIndicator.getChildAt(i9);
        this.slidingTabIndicator.removeViewAt(i9);
        if (tabView != null) {
            tabView.reset();
            this.tabViewPool.mo3464a(tabView);
        }
        requestLayout();
    }

    private void setSelectedTabView(int i9) {
        int childCount = this.slidingTabIndicator.getChildCount();
        if (i9 < childCount) {
            int i10 = 0;
            while (i10 < childCount) {
                View childAt = this.slidingTabIndicator.getChildAt(i10);
                boolean z8 = true;
                childAt.setSelected(i10 == i9);
                if (i10 != i9) {
                    z8 = false;
                }
                childAt.setActivated(z8);
                i10++;
            }
        }
    }

    private void updateAllTabs() {
        int size = this.tabs.size();
        for (int i9 = 0; i9 < size; i9++) {
            this.tabs.get(i9).updateView();
        }
    }

    private void updateTabViewLayoutParams(LinearLayout.LayoutParams layoutParams) {
        if (this.mode == 1 && this.tabGravity == 0) {
            layoutParams.width = 0;
            layoutParams.weight = 1.0f;
        } else {
            layoutParams.width = -2;
            layoutParams.weight = BitmapDescriptorFactory.HUE_RED;
        }
    }

    public void addOnTabSelectedListener(BaseOnTabSelectedListener baseOnTabSelectedListener) {
        if (this.selectedListeners.contains(baseOnTabSelectedListener)) {
            return;
        }
        this.selectedListeners.add(baseOnTabSelectedListener);
    }

    public void addTab(Tab tab) {
        addTab(tab, this.tabs.isEmpty());
    }

    @Override // android.widget.HorizontalScrollView, android.view.ViewGroup
    public void addView(View view) {
        addViewInternal(view);
    }

    public void clearOnTabSelectedListeners() {
        this.selectedListeners.clear();
    }

    public Tab createTabFromPool() {
        Tab tabMo3465b = tabPool.mo3465b();
        return tabMo3465b == null ? new Tab() : tabMo3465b;
    }

    public int dpToPx(int i9) {
        return Math.round(getResources().getDisplayMetrics().density * i9);
    }

    public int getSelectedTabPosition() {
        Tab tab = this.selectedTab;
        if (tab != null) {
            return tab.getPosition();
        }
        return -1;
    }

    public Tab getTabAt(int i9) {
        if (i9 < 0 || i9 >= getTabCount()) {
            return null;
        }
        return this.tabs.get(i9);
    }

    public int getTabCount() {
        return this.tabs.size();
    }

    public int getTabGravity() {
        return this.tabGravity;
    }

    public ColorStateList getTabIconTint() {
        return this.tabIconTint;
    }

    public int getTabIndicatorGravity() {
        return this.tabIndicatorGravity;
    }

    public int getTabMaxWidth() {
        return this.tabMaxWidth;
    }

    public int getTabMode() {
        return this.mode;
    }

    public ColorStateList getTabRippleColor() {
        return this.tabRippleColorStateList;
    }

    public Drawable getTabSelectedIndicator() {
        return this.tabSelectedIndicator;
    }

    public ColorStateList getTabTextColors() {
        return this.tabTextColors;
    }

    public boolean hasUnboundedRipple() {
        return this.unboundedRipple;
    }

    public boolean isInlineLabel() {
        return this.inlineLabel;
    }

    public boolean isTabIndicatorFullWidth() {
        return this.tabIndicatorFullWidth;
    }

    public Tab newTab() {
        Tab tabCreateTabFromPool = createTabFromPool();
        tabCreateTabFromPool.parent = this;
        tabCreateTabFromPool.view = createTabView(tabCreateTabFromPool);
        return tabCreateTabFromPool;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.viewPager == null) {
            ViewParent parent = getParent();
            if (parent instanceof ViewPager) {
                setupWithViewPager((ViewPager) parent, true, true);
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.setupViewPagerImplicitly) {
            setupWithViewPager(null);
            this.setupViewPagerImplicitly = false;
        }
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        for (int i9 = 0; i9 < this.slidingTabIndicator.getChildCount(); i9++) {
            View childAt = this.slidingTabIndicator.getChildAt(i9);
            if (childAt instanceof TabView) {
                ((TabView) childAt).drawBackground(canvas);
            }
        }
        super.onDraw(canvas);
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x0075  */
    @Override // android.widget.HorizontalScrollView, android.widget.FrameLayout, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void onMeasure(int i9, int i10) {
        int iDpToPx = dpToPx(getDefaultHeight()) + getPaddingTop() + getPaddingBottom();
        int mode = View.MeasureSpec.getMode(i10);
        if (mode == Integer.MIN_VALUE) {
            i10 = View.MeasureSpec.makeMeasureSpec(Math.min(iDpToPx, View.MeasureSpec.getSize(i10)), 1073741824);
        } else if (mode == 0) {
            i10 = View.MeasureSpec.makeMeasureSpec(iDpToPx, 1073741824);
        }
        int size = View.MeasureSpec.getSize(i9);
        if (View.MeasureSpec.getMode(i9) != 0) {
            int iDpToPx2 = this.requestedTabMaxWidth;
            if (iDpToPx2 <= 0) {
                iDpToPx2 = size - dpToPx(56);
            }
            this.tabMaxWidth = iDpToPx2;
        }
        super.onMeasure(i9, i10);
        boolean z8 = true;
        if (getChildCount() == 1) {
            boolean z9 = false;
            View childAt = getChildAt(0);
            int i11 = this.mode;
            if (i11 == 0) {
                if (childAt.getMeasuredWidth() >= getMeasuredWidth()) {
                }
                z9 = z8;
            } else if (i11 == 1) {
                if (childAt.getMeasuredWidth() == getMeasuredWidth()) {
                    z8 = false;
                }
                z9 = z8;
            }
            if (z9) {
                childAt.measure(View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 1073741824), ViewGroup.getChildMeasureSpec(i10, getPaddingTop() + getPaddingBottom(), childAt.getLayoutParams().height));
            }
        }
    }

    public void populateFromPagerAdapter() {
        int currentItem;
        removeAllTabs();
        AbstractC6243a abstractC6243a = this.pagerAdapter;
        if (abstractC6243a != null) {
            int count = abstractC6243a.getCount();
            for (int i9 = 0; i9 < count; i9++) {
                addTab(newTab().setText(this.pagerAdapter.getPageTitle(i9)), false);
            }
            ViewPager viewPager = this.viewPager;
            if (viewPager == null || count <= 0 || (currentItem = viewPager.getCurrentItem()) == getSelectedTabPosition() || currentItem >= getTabCount()) {
                return;
            }
            selectTab(getTabAt(currentItem));
        }
    }

    public boolean releaseFromTabPool(Tab tab) {
        return tabPool.mo3464a(tab);
    }

    public void removeAllTabs() {
        for (int childCount = this.slidingTabIndicator.getChildCount() - 1; childCount >= 0; childCount--) {
            removeTabViewAt(childCount);
        }
        Iterator<Tab> it = this.tabs.iterator();
        while (it.hasNext()) {
            Tab next = it.next();
            it.remove();
            next.reset();
            releaseFromTabPool(next);
        }
        this.selectedTab = null;
    }

    public void removeOnTabSelectedListener(BaseOnTabSelectedListener baseOnTabSelectedListener) {
        this.selectedListeners.remove(baseOnTabSelectedListener);
    }

    public void removeTab(Tab tab) {
        if (tab.parent != this) {
            throw new IllegalArgumentException("Tab does not belong to this TabLayout.");
        }
        removeTabAt(tab.getPosition());
    }

    public void removeTabAt(int i9) {
        Tab tab = this.selectedTab;
        int position = tab != null ? tab.getPosition() : 0;
        removeTabViewAt(i9);
        Tab tabRemove = this.tabs.remove(i9);
        if (tabRemove != null) {
            tabRemove.reset();
            releaseFromTabPool(tabRemove);
        }
        int size = this.tabs.size();
        for (int i10 = i9; i10 < size; i10++) {
            this.tabs.get(i10).setPosition(i10);
        }
        if (position == i9) {
            selectTab(this.tabs.isEmpty() ? null : this.tabs.get(Math.max(0, i9 - 1)));
        }
    }

    public void selectTab(Tab tab) {
        selectTab(tab, true);
    }

    public void setInlineLabel(boolean z8) {
        if (this.inlineLabel != z8) {
            this.inlineLabel = z8;
            for (int i9 = 0; i9 < this.slidingTabIndicator.getChildCount(); i9++) {
                View childAt = this.slidingTabIndicator.getChildAt(i9);
                if (childAt instanceof TabView) {
                    ((TabView) childAt).updateOrientation();
                }
            }
            applyModeAndGravity();
        }
    }

    public void setInlineLabelResource(int i9) {
        setInlineLabel(getResources().getBoolean(i9));
    }

    @Deprecated
    public void setOnTabSelectedListener(BaseOnTabSelectedListener baseOnTabSelectedListener) {
        BaseOnTabSelectedListener baseOnTabSelectedListener2 = this.selectedListener;
        if (baseOnTabSelectedListener2 != null) {
            removeOnTabSelectedListener(baseOnTabSelectedListener2);
        }
        this.selectedListener = baseOnTabSelectedListener;
        if (baseOnTabSelectedListener != null) {
            addOnTabSelectedListener(baseOnTabSelectedListener);
        }
    }

    public void setPagerAdapter(AbstractC6243a abstractC6243a, boolean z8) {
        DataSetObserver dataSetObserver;
        AbstractC6243a abstractC6243a2 = this.pagerAdapter;
        if (abstractC6243a2 != null && (dataSetObserver = this.pagerAdapterObserver) != null) {
            abstractC6243a2.unregisterDataSetObserver(dataSetObserver);
        }
        this.pagerAdapter = abstractC6243a;
        if (z8 && abstractC6243a != null) {
            if (this.pagerAdapterObserver == null) {
                this.pagerAdapterObserver = new PagerAdapterObserver();
            }
            abstractC6243a.registerDataSetObserver(this.pagerAdapterObserver);
        }
        populateFromPagerAdapter();
    }

    public void setScrollAnimatorListener(Animator.AnimatorListener animatorListener) {
        ensureScrollAnimator();
        this.scrollAnimator.addListener(animatorListener);
    }

    public void setScrollPosition(int i9, float f9, boolean z8) {
        setScrollPosition(i9, f9, z8, true);
    }

    public void setSelectedTabIndicator(Drawable drawable) {
        if (this.tabSelectedIndicator != drawable) {
            this.tabSelectedIndicator = drawable;
            C4647u.m18524T(this.slidingTabIndicator);
        }
    }

    public void setSelectedTabIndicatorColor(int i9) {
        this.slidingTabIndicator.setSelectedIndicatorColor(i9);
    }

    public void setSelectedTabIndicatorGravity(int i9) {
        if (this.tabIndicatorGravity != i9) {
            this.tabIndicatorGravity = i9;
            C4647u.m18524T(this.slidingTabIndicator);
        }
    }

    @Deprecated
    public void setSelectedTabIndicatorHeight(int i9) {
        this.slidingTabIndicator.setSelectedIndicatorHeight(i9);
    }

    public void setTabGravity(int i9) {
        if (this.tabGravity != i9) {
            this.tabGravity = i9;
            applyModeAndGravity();
        }
    }

    public void setTabIconTint(ColorStateList colorStateList) {
        if (this.tabIconTint != colorStateList) {
            this.tabIconTint = colorStateList;
            updateAllTabs();
        }
    }

    public void setTabIconTintResource(int i9) {
        setTabIconTint(C0694a.m3457a(getContext(), i9));
    }

    public void setTabIndicatorFullWidth(boolean z8) {
        this.tabIndicatorFullWidth = z8;
        C4647u.m18524T(this.slidingTabIndicator);
    }

    public void setTabMode(int i9) {
        if (i9 != this.mode) {
            this.mode = i9;
            applyModeAndGravity();
        }
    }

    public void setTabRippleColor(ColorStateList colorStateList) {
        if (this.tabRippleColorStateList != colorStateList) {
            this.tabRippleColorStateList = colorStateList;
            for (int i9 = 0; i9 < this.slidingTabIndicator.getChildCount(); i9++) {
                View childAt = this.slidingTabIndicator.getChildAt(i9);
                if (childAt instanceof TabView) {
                    ((TabView) childAt).updateBackgroundDrawable(getContext());
                }
            }
        }
    }

    public void setTabRippleColorResource(int i9) {
        setTabRippleColor(C0694a.m3457a(getContext(), i9));
    }

    public void setTabTextColors(ColorStateList colorStateList) {
        if (this.tabTextColors != colorStateList) {
            this.tabTextColors = colorStateList;
            updateAllTabs();
        }
    }

    @Deprecated
    public void setTabsFromPagerAdapter(AbstractC6243a abstractC6243a) {
        setPagerAdapter(abstractC6243a, false);
    }

    public void setUnboundedRipple(boolean z8) {
        if (this.unboundedRipple != z8) {
            this.unboundedRipple = z8;
            for (int i9 = 0; i9 < this.slidingTabIndicator.getChildCount(); i9++) {
                View childAt = this.slidingTabIndicator.getChildAt(i9);
                if (childAt instanceof TabView) {
                    ((TabView) childAt).updateBackgroundDrawable(getContext());
                }
            }
        }
    }

    public void setUnboundedRippleResource(int i9) {
        setUnboundedRipple(getResources().getBoolean(i9));
    }

    public void setupWithViewPager(ViewPager viewPager) {
        setupWithViewPager(viewPager, true);
    }

    @Override // android.widget.HorizontalScrollView, android.widget.FrameLayout, android.view.ViewGroup
    public boolean shouldDelayChildPressedState() {
        return getTabScrollRange() > 0;
    }

    public void updateTabViews(boolean z8) {
        for (int i9 = 0; i9 < this.slidingTabIndicator.getChildCount(); i9++) {
            View childAt = this.slidingTabIndicator.getChildAt(i9);
            childAt.setMinimumWidth(getTabMinWidth());
            updateTabViewLayoutParams((LinearLayout.LayoutParams) childAt.getLayoutParams());
            if (z8) {
                childAt.requestLayout();
            }
        }
    }

    public static class Tab {
        public static final int INVALID_POSITION = -1;
        private CharSequence contentDesc;
        private View customView;
        private Drawable icon;
        public TabLayout parent;
        private int position = -1;
        private Object tag;
        private CharSequence text;
        public TabView view;

        public CharSequence getContentDescription() {
            TabView tabView = this.view;
            if (tabView == null) {
                return null;
            }
            return tabView.getContentDescription();
        }

        public View getCustomView() {
            return this.customView;
        }

        public Drawable getIcon() {
            return this.icon;
        }

        public int getPosition() {
            return this.position;
        }

        public Object getTag() {
            return this.tag;
        }

        public CharSequence getText() {
            return this.text;
        }

        public boolean isSelected() {
            TabLayout tabLayout = this.parent;
            if (tabLayout != null) {
                return tabLayout.getSelectedTabPosition() == this.position;
            }
            throw new IllegalArgumentException("Tab not attached to a TabLayout");
        }

        public void reset() {
            this.parent = null;
            this.view = null;
            this.tag = null;
            this.icon = null;
            this.text = null;
            this.contentDesc = null;
            this.position = -1;
            this.customView = null;
        }

        public void select() {
            TabLayout tabLayout = this.parent;
            if (tabLayout == null) {
                throw new IllegalArgumentException("Tab not attached to a TabLayout");
            }
            tabLayout.selectTab(this);
        }

        public Tab setContentDescription(int i9) {
            TabLayout tabLayout = this.parent;
            if (tabLayout != null) {
                return setContentDescription(tabLayout.getResources().getText(i9));
            }
            throw new IllegalArgumentException("Tab not attached to a TabLayout");
        }

        public Tab setCustomView(View view) {
            this.customView = view;
            updateView();
            return this;
        }

        public Tab setIcon(Drawable drawable) {
            this.icon = drawable;
            updateView();
            return this;
        }

        public void setPosition(int i9) {
            this.position = i9;
        }

        public Tab setTag(Object obj) {
            this.tag = obj;
            return this;
        }

        public Tab setText(CharSequence charSequence) {
            if (TextUtils.isEmpty(this.contentDesc) && !TextUtils.isEmpty(charSequence)) {
                this.view.setContentDescription(charSequence);
            }
            this.text = charSequence;
            updateView();
            return this;
        }

        public void updateView() {
            TabView tabView = this.view;
            if (tabView != null) {
                tabView.update();
            }
        }

        public Tab setCustomView(int i9) {
            return setCustomView(LayoutInflater.from(this.view.getContext()).inflate(i9, (ViewGroup) this.view, false));
        }

        public Tab setIcon(int i9) {
            TabLayout tabLayout = this.parent;
            if (tabLayout != null) {
                return setIcon(C0694a.m3458b(tabLayout.getContext(), i9));
            }
            throw new IllegalArgumentException("Tab not attached to a TabLayout");
        }

        public Tab setContentDescription(CharSequence charSequence) {
            this.contentDesc = charSequence;
            updateView();
            return this;
        }

        public Tab setText(int i9) {
            TabLayout tabLayout = this.parent;
            if (tabLayout != null) {
                return setText(tabLayout.getResources().getText(i9));
            }
            throw new IllegalArgumentException("Tab not attached to a TabLayout");
        }
    }

    public TabLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, C3476R.attr.tabStyle);
    }

    public void addTab(Tab tab, int i9) {
        addTab(tab, i9, this.tabs.isEmpty());
    }

    @Override // android.widget.HorizontalScrollView, android.view.ViewGroup
    public void addView(View view, int i9) {
        addViewInternal(view);
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup
    public FrameLayout.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return generateDefaultLayoutParams();
    }

    public void selectTab(Tab tab, boolean z8) {
        Tab tab2 = this.selectedTab;
        if (tab2 == tab) {
            if (tab2 != null) {
                dispatchTabReselected(tab);
                animateToTab(tab.getPosition());
                return;
            }
            return;
        }
        int position = tab != null ? tab.getPosition() : -1;
        if (z8) {
            if ((tab2 == null || tab2.getPosition() == -1) && position != -1) {
                setScrollPosition(position, BitmapDescriptorFactory.HUE_RED, true);
            } else {
                animateToTab(position);
            }
            if (position != -1) {
                setSelectedTabView(position);
            }
        }
        this.selectedTab = tab;
        if (tab2 != null) {
            dispatchTabUnselected(tab2);
        }
        if (tab != null) {
            dispatchTabSelected(tab);
        }
    }

    public void setScrollPosition(int i9, float f9, boolean z8, boolean z9) {
        int iRound = Math.round(i9 + f9);
        if (iRound < 0 || iRound >= this.slidingTabIndicator.getChildCount()) {
            return;
        }
        if (z9) {
            this.slidingTabIndicator.setIndicatorPositionFromTabPosition(i9, f9);
        }
        ValueAnimator valueAnimator = this.scrollAnimator;
        if (valueAnimator != null && valueAnimator.isRunning()) {
            this.scrollAnimator.cancel();
        }
        scrollTo(calculateScrollXForTab(i9, f9), 0);
        if (z8) {
            setSelectedTabView(iRound);
        }
    }

    public void setupWithViewPager(ViewPager viewPager, boolean z8) {
        setupWithViewPager(viewPager, z8, false);
    }

    public TabLayout(Context context, AttributeSet attributeSet, int i9) throws Resources.NotFoundException {
        super(context, attributeSet, i9);
        this.tabs = new ArrayList<>();
        this.tabViewContentBounds = new RectF();
        this.tabMaxWidth = Integer.MAX_VALUE;
        this.selectedListeners = new ArrayList<>();
        this.tabViewPool = new C0700f(12);
        setHorizontalScrollBarEnabled(false);
        SlidingTabIndicator slidingTabIndicator = new SlidingTabIndicator(context);
        this.slidingTabIndicator = slidingTabIndicator;
        super.addView(slidingTabIndicator, 0, new FrameLayout.LayoutParams(-2, -1));
        int[] iArr = C3476R.styleable.TabLayout;
        int i10 = C3476R.style.Widget_Design_TabLayout;
        int i11 = C3476R.styleable.TabLayout_tabTextAppearance;
        TypedArray typedArrayObtainStyledAttributes = ThemeEnforcement.obtainStyledAttributes(context, attributeSet, iArr, i9, i10, i11);
        slidingTabIndicator.setSelectedIndicatorHeight(typedArrayObtainStyledAttributes.getDimensionPixelSize(C3476R.styleable.TabLayout_tabIndicatorHeight, -1));
        slidingTabIndicator.setSelectedIndicatorColor(typedArrayObtainStyledAttributes.getColor(C3476R.styleable.TabLayout_tabIndicatorColor, 0));
        setSelectedTabIndicator(MaterialResources.getDrawable(context, typedArrayObtainStyledAttributes, C3476R.styleable.TabLayout_tabIndicator));
        setSelectedTabIndicatorGravity(typedArrayObtainStyledAttributes.getInt(C3476R.styleable.TabLayout_tabIndicatorGravity, 0));
        setTabIndicatorFullWidth(typedArrayObtainStyledAttributes.getBoolean(C3476R.styleable.TabLayout_tabIndicatorFullWidth, true));
        int dimensionPixelSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(C3476R.styleable.TabLayout_tabPadding, 0);
        this.tabPaddingBottom = dimensionPixelSize;
        this.tabPaddingEnd = dimensionPixelSize;
        this.tabPaddingTop = dimensionPixelSize;
        this.tabPaddingStart = dimensionPixelSize;
        this.tabPaddingStart = typedArrayObtainStyledAttributes.getDimensionPixelSize(C3476R.styleable.TabLayout_tabPaddingStart, dimensionPixelSize);
        this.tabPaddingTop = typedArrayObtainStyledAttributes.getDimensionPixelSize(C3476R.styleable.TabLayout_tabPaddingTop, this.tabPaddingTop);
        this.tabPaddingEnd = typedArrayObtainStyledAttributes.getDimensionPixelSize(C3476R.styleable.TabLayout_tabPaddingEnd, this.tabPaddingEnd);
        this.tabPaddingBottom = typedArrayObtainStyledAttributes.getDimensionPixelSize(C3476R.styleable.TabLayout_tabPaddingBottom, this.tabPaddingBottom);
        int resourceId = typedArrayObtainStyledAttributes.getResourceId(i11, C3476R.style.TextAppearance_Design_Tab);
        this.tabTextAppearance = resourceId;
        TypedArray typedArrayObtainStyledAttributes2 = context.obtainStyledAttributes(resourceId, C0569j.TextAppearance);
        try {
            this.tabTextSize = typedArrayObtainStyledAttributes2.getDimensionPixelSize(C0569j.TextAppearance_android_textSize, 0);
            this.tabTextColors = MaterialResources.getColorStateList(context, typedArrayObtainStyledAttributes2, C0569j.TextAppearance_android_textColor);
            typedArrayObtainStyledAttributes2.recycle();
            int i12 = C3476R.styleable.TabLayout_tabTextColor;
            if (typedArrayObtainStyledAttributes.hasValue(i12)) {
                this.tabTextColors = MaterialResources.getColorStateList(context, typedArrayObtainStyledAttributes, i12);
            }
            int i13 = C3476R.styleable.TabLayout_tabSelectedTextColor;
            if (typedArrayObtainStyledAttributes.hasValue(i13)) {
                this.tabTextColors = createColorStateList(this.tabTextColors.getDefaultColor(), typedArrayObtainStyledAttributes.getColor(i13, 0));
            }
            this.tabIconTint = MaterialResources.getColorStateList(context, typedArrayObtainStyledAttributes, C3476R.styleable.TabLayout_tabIconTint);
            this.tabIconTintMode = ViewUtils.parseTintMode(typedArrayObtainStyledAttributes.getInt(C3476R.styleable.TabLayout_tabIconTintMode, -1), null);
            this.tabRippleColorStateList = MaterialResources.getColorStateList(context, typedArrayObtainStyledAttributes, C3476R.styleable.TabLayout_tabRippleColor);
            this.tabIndicatorAnimationDuration = typedArrayObtainStyledAttributes.getInt(C3476R.styleable.TabLayout_tabIndicatorAnimationDuration, ANIMATION_DURATION);
            this.requestedTabMinWidth = typedArrayObtainStyledAttributes.getDimensionPixelSize(C3476R.styleable.TabLayout_tabMinWidth, -1);
            this.requestedTabMaxWidth = typedArrayObtainStyledAttributes.getDimensionPixelSize(C3476R.styleable.TabLayout_tabMaxWidth, -1);
            this.tabBackgroundResId = typedArrayObtainStyledAttributes.getResourceId(C3476R.styleable.TabLayout_tabBackground, 0);
            this.contentInsetStart = typedArrayObtainStyledAttributes.getDimensionPixelSize(C3476R.styleable.TabLayout_tabContentStart, 0);
            this.mode = typedArrayObtainStyledAttributes.getInt(C3476R.styleable.TabLayout_tabMode, 1);
            this.tabGravity = typedArrayObtainStyledAttributes.getInt(C3476R.styleable.TabLayout_tabGravity, 0);
            this.inlineLabel = typedArrayObtainStyledAttributes.getBoolean(C3476R.styleable.TabLayout_tabInlineLabel, false);
            this.unboundedRipple = typedArrayObtainStyledAttributes.getBoolean(C3476R.styleable.TabLayout_tabUnboundedRipple, false);
            typedArrayObtainStyledAttributes.recycle();
            Resources resources = getResources();
            this.tabTextMultiLineSize = resources.getDimensionPixelSize(C3476R.dimen.design_tab_text_size_2line);
            this.scrollableTabMinWidth = resources.getDimensionPixelSize(C3476R.dimen.design_tab_scrollable_min_width);
            applyModeAndGravity();
        } catch (Throwable th) {
            typedArrayObtainStyledAttributes2.recycle();
            throw th;
        }
    }

    private void setupWithViewPager(ViewPager viewPager, boolean z8, boolean z9) {
        ViewPager viewPager2 = this.viewPager;
        if (viewPager2 != null) {
            TabLayoutOnPageChangeListener tabLayoutOnPageChangeListener = this.pageChangeListener;
            if (tabLayoutOnPageChangeListener != null) {
                viewPager2.m3176I(tabLayoutOnPageChangeListener);
            }
            AdapterChangeListener adapterChangeListener = this.adapterChangeListener;
            if (adapterChangeListener != null) {
                this.viewPager.m3175H(adapterChangeListener);
            }
        }
        BaseOnTabSelectedListener baseOnTabSelectedListener = this.currentVpSelectedListener;
        if (baseOnTabSelectedListener != null) {
            removeOnTabSelectedListener(baseOnTabSelectedListener);
            this.currentVpSelectedListener = null;
        }
        if (viewPager != null) {
            this.viewPager = viewPager;
            if (this.pageChangeListener == null) {
                this.pageChangeListener = new TabLayoutOnPageChangeListener(this);
            }
            this.pageChangeListener.reset();
            viewPager.m3187c(this.pageChangeListener);
            ViewPagerOnTabSelectedListener viewPagerOnTabSelectedListener = new ViewPagerOnTabSelectedListener(viewPager);
            this.currentVpSelectedListener = viewPagerOnTabSelectedListener;
            addOnTabSelectedListener(viewPagerOnTabSelectedListener);
            AbstractC6243a adapter = viewPager.getAdapter();
            if (adapter != null) {
                setPagerAdapter(adapter, z8);
            }
            if (this.adapterChangeListener == null) {
                this.adapterChangeListener = new AdapterChangeListener();
            }
            this.adapterChangeListener.setAutoRefresh(z8);
            viewPager.m3186b(this.adapterChangeListener);
            setScrollPosition(viewPager.getCurrentItem(), BitmapDescriptorFactory.HUE_RED, true);
        } else {
            this.viewPager = null;
            setPagerAdapter(null, false);
        }
        this.setupViewPagerImplicitly = z9;
    }

    public void addTab(Tab tab, boolean z8) {
        addTab(tab, this.tabs.size(), z8);
    }

    @Override // android.widget.HorizontalScrollView, android.view.ViewGroup, android.view.ViewManager
    public void addView(View view, ViewGroup.LayoutParams layoutParams) {
        addViewInternal(view);
    }

    public void addTab(Tab tab, int i9, boolean z8) {
        if (tab.parent == this) {
            configureTab(tab, i9);
            addTabView(tab);
            if (z8) {
                tab.select();
                return;
            }
            return;
        }
        throw new IllegalArgumentException("Tab belongs to a different TabLayout.");
    }

    @Override // android.widget.HorizontalScrollView, android.view.ViewGroup
    public void addView(View view, int i9, ViewGroup.LayoutParams layoutParams) {
        addViewInternal(view);
    }

    public void setSelectedTabIndicator(int i9) {
        if (i9 != 0) {
            setSelectedTabIndicator(C0694a.m3458b(getContext(), i9));
        } else {
            setSelectedTabIndicator((Drawable) null);
        }
    }

    public void setTabTextColors(int i9, int i10) {
        setTabTextColors(createColorStateList(i9, i10));
    }
}
