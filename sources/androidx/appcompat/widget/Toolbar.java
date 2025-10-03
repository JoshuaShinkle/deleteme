package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.Layout;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.ContextThemeWrapper;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AbstractC0119a;
import androidx.appcompat.view.menu.C0137g;
import androidx.appcompat.view.menu.C0139i;
import androidx.appcompat.view.menu.InterfaceC0143m;
import androidx.appcompat.view.menu.SubMenuC0148r;
import androidx.appcompat.widget.ActionMenuView;
import androidx.customview.view.AbsSavedState;
import java.util.ArrayList;
import java.util.List;
import p010b.C0560a;
import p010b.C0569j;
import p020c.C0694a;
import p042d0.C4621e;
import p042d0.C4627h;
import p042d0.C4647u;
import p071g.C4801g;
import p071g.InterfaceC4797c;

/* loaded from: classes.dex */
public class Toolbar extends ViewGroup {
    private static final String TAG = "Toolbar";
    private InterfaceC0143m.a mActionMenuPresenterCallback;
    int mButtonGravity;
    ImageButton mCollapseButtonView;
    private CharSequence mCollapseDescription;
    private Drawable mCollapseIcon;
    private boolean mCollapsible;
    private int mContentInsetEndWithActions;
    private int mContentInsetStartWithNavigation;
    private C0234i0 mContentInsets;
    private boolean mEatingHover;
    private boolean mEatingTouch;
    View mExpandedActionView;
    private C0213d mExpandedMenuPresenter;
    private int mGravity;
    private final ArrayList<View> mHiddenViews;
    private ImageView mLogoView;
    private int mMaxButtonHeight;
    private C0137g.a mMenuBuilderCallback;
    private ActionMenuView mMenuView;
    private final ActionMenuView.InterfaceC0167e mMenuViewItemClickListener;
    private ImageButton mNavButtonView;
    InterfaceC0215f mOnMenuItemClickListener;
    private ActionMenuPresenter mOuterActionMenuPresenter;
    private Context mPopupContext;
    private int mPopupTheme;
    private final Runnable mShowOverflowMenuRunnable;
    private CharSequence mSubtitleText;
    private int mSubtitleTextAppearance;
    private ColorStateList mSubtitleTextColor;
    private TextView mSubtitleTextView;
    private final int[] mTempMargins;
    private final ArrayList<View> mTempViews;
    private int mTitleMarginBottom;
    private int mTitleMarginEnd;
    private int mTitleMarginStart;
    private int mTitleMarginTop;
    private CharSequence mTitleText;
    private int mTitleTextAppearance;
    private ColorStateList mTitleTextColor;
    private TextView mTitleTextView;
    private C0252r0 mWrapper;

    /* renamed from: androidx.appcompat.widget.Toolbar$a */
    public class C0210a implements ActionMenuView.InterfaceC0167e {
        public C0210a() {
        }

        @Override // androidx.appcompat.widget.ActionMenuView.InterfaceC0167e
        public boolean onMenuItemClick(MenuItem menuItem) {
            Toolbar.this.getClass();
            return false;
        }
    }

    /* renamed from: androidx.appcompat.widget.Toolbar$b */
    public class RunnableC0211b implements Runnable {
        public RunnableC0211b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            Toolbar.this.showOverflowMenu();
        }
    }

    /* renamed from: androidx.appcompat.widget.Toolbar$c */
    public class ViewOnClickListenerC0212c implements View.OnClickListener {
        public ViewOnClickListenerC0212c() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            Toolbar.this.collapseActionView();
        }
    }

    /* renamed from: androidx.appcompat.widget.Toolbar$d */
    public class C0213d implements InterfaceC0143m {

        /* renamed from: b */
        public C0137g f1011b;

        /* renamed from: c */
        public C0139i f1012c;

        public C0213d() {
        }

        @Override // androidx.appcompat.view.menu.InterfaceC0143m
        public boolean collapseItemActionView(C0137g c0137g, C0139i c0139i) {
            KeyEvent.Callback callback = Toolbar.this.mExpandedActionView;
            if (callback instanceof InterfaceC4797c) {
                ((InterfaceC4797c) callback).onActionViewCollapsed();
            }
            Toolbar toolbar = Toolbar.this;
            toolbar.removeView(toolbar.mExpandedActionView);
            Toolbar toolbar2 = Toolbar.this;
            toolbar2.removeView(toolbar2.mCollapseButtonView);
            Toolbar toolbar3 = Toolbar.this;
            toolbar3.mExpandedActionView = null;
            toolbar3.addChildrenForExpandedActionView();
            this.f1012c = null;
            Toolbar.this.requestLayout();
            c0139i.m538r(false);
            return true;
        }

        @Override // androidx.appcompat.view.menu.InterfaceC0143m
        public boolean expandItemActionView(C0137g c0137g, C0139i c0139i) {
            Toolbar.this.ensureCollapseButtonView();
            ViewParent parent = Toolbar.this.mCollapseButtonView.getParent();
            Toolbar toolbar = Toolbar.this;
            if (parent != toolbar) {
                if (parent instanceof ViewGroup) {
                    ((ViewGroup) parent).removeView(toolbar.mCollapseButtonView);
                }
                Toolbar toolbar2 = Toolbar.this;
                toolbar2.addView(toolbar2.mCollapseButtonView);
            }
            Toolbar.this.mExpandedActionView = c0139i.getActionView();
            this.f1012c = c0139i;
            ViewParent parent2 = Toolbar.this.mExpandedActionView.getParent();
            Toolbar toolbar3 = Toolbar.this;
            if (parent2 != toolbar3) {
                if (parent2 instanceof ViewGroup) {
                    ((ViewGroup) parent2).removeView(toolbar3.mExpandedActionView);
                }
                C0214e c0214eGenerateDefaultLayoutParams = Toolbar.this.generateDefaultLayoutParams();
                Toolbar toolbar4 = Toolbar.this;
                c0214eGenerateDefaultLayoutParams.f376a = (toolbar4.mButtonGravity & 112) | 8388611;
                c0214eGenerateDefaultLayoutParams.f1014b = 2;
                toolbar4.mExpandedActionView.setLayoutParams(c0214eGenerateDefaultLayoutParams);
                Toolbar toolbar5 = Toolbar.this;
                toolbar5.addView(toolbar5.mExpandedActionView);
            }
            Toolbar.this.removeChildrenForExpandedActionView();
            Toolbar.this.requestLayout();
            c0139i.m538r(true);
            KeyEvent.Callback callback = Toolbar.this.mExpandedActionView;
            if (callback instanceof InterfaceC4797c) {
                ((InterfaceC4797c) callback).onActionViewExpanded();
            }
            return true;
        }

        @Override // androidx.appcompat.view.menu.InterfaceC0143m
        public boolean flagActionItems() {
            return false;
        }

        @Override // androidx.appcompat.view.menu.InterfaceC0143m
        public int getId() {
            return 0;
        }

        @Override // androidx.appcompat.view.menu.InterfaceC0143m
        public void initForMenu(Context context, C0137g c0137g) {
            C0139i c0139i;
            C0137g c0137g2 = this.f1011b;
            if (c0137g2 != null && (c0139i = this.f1012c) != null) {
                c0137g2.collapseItemActionView(c0139i);
            }
            this.f1011b = c0137g;
        }

        @Override // androidx.appcompat.view.menu.InterfaceC0143m
        public void onCloseMenu(C0137g c0137g, boolean z8) {
        }

        @Override // androidx.appcompat.view.menu.InterfaceC0143m
        public void onRestoreInstanceState(Parcelable parcelable) {
        }

        @Override // androidx.appcompat.view.menu.InterfaceC0143m
        public Parcelable onSaveInstanceState() {
            return null;
        }

        @Override // androidx.appcompat.view.menu.InterfaceC0143m
        public boolean onSubMenuSelected(SubMenuC0148r subMenuC0148r) {
            return false;
        }

        @Override // androidx.appcompat.view.menu.InterfaceC0143m
        public void updateMenuView(boolean z8) {
            if (this.f1012c != null) {
                C0137g c0137g = this.f1011b;
                boolean z9 = false;
                if (c0137g != null) {
                    int size = c0137g.size();
                    int i9 = 0;
                    while (true) {
                        if (i9 >= size) {
                            break;
                        }
                        if (this.f1011b.getItem(i9) == this.f1012c) {
                            z9 = true;
                            break;
                        }
                        i9++;
                    }
                }
                if (z9) {
                    return;
                }
                collapseItemActionView(this.f1011b, this.f1012c);
            }
        }
    }

    /* renamed from: androidx.appcompat.widget.Toolbar$f */
    public interface InterfaceC0215f {
    }

    public Toolbar(Context context) {
        this(context, null);
    }

    private void addCustomViewsWithGravity(List<View> list, int i9) {
        boolean z8 = C4647u.m18567s(this) == 1;
        int childCount = getChildCount();
        int iM18420b = C4621e.m18420b(i9, C4647u.m18567s(this));
        list.clear();
        if (!z8) {
            for (int i10 = 0; i10 < childCount; i10++) {
                View childAt = getChildAt(i10);
                C0214e c0214e = (C0214e) childAt.getLayoutParams();
                if (c0214e.f1014b == 0 && shouldLayout(childAt) && getChildHorizontalGravity(c0214e.f376a) == iM18420b) {
                    list.add(childAt);
                }
            }
            return;
        }
        for (int i11 = childCount - 1; i11 >= 0; i11--) {
            View childAt2 = getChildAt(i11);
            C0214e c0214e2 = (C0214e) childAt2.getLayoutParams();
            if (c0214e2.f1014b == 0 && shouldLayout(childAt2) && getChildHorizontalGravity(c0214e2.f376a) == iM18420b) {
                list.add(childAt2);
            }
        }
    }

    private void addSystemView(View view, boolean z8) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        C0214e c0214eGenerateDefaultLayoutParams = layoutParams == null ? generateDefaultLayoutParams() : !checkLayoutParams(layoutParams) ? generateLayoutParams(layoutParams) : (C0214e) layoutParams;
        c0214eGenerateDefaultLayoutParams.f1014b = 1;
        if (!z8 || this.mExpandedActionView == null) {
            addView(view, c0214eGenerateDefaultLayoutParams);
        } else {
            view.setLayoutParams(c0214eGenerateDefaultLayoutParams);
            this.mHiddenViews.add(view);
        }
    }

    private void ensureContentInsets() {
        if (this.mContentInsets == null) {
            this.mContentInsets = new C0234i0();
        }
    }

    private void ensureLogoView() {
        if (this.mLogoView == null) {
            this.mLogoView = new AppCompatImageView(getContext());
        }
    }

    private void ensureMenu() {
        ensureMenuView();
        if (this.mMenuView.m645n() == null) {
            C0137g c0137g = (C0137g) this.mMenuView.getMenu();
            if (this.mExpandedMenuPresenter == null) {
                this.mExpandedMenuPresenter = new C0213d();
            }
            this.mMenuView.setExpandedActionViewsExclusive(true);
            c0137g.addMenuPresenter(this.mExpandedMenuPresenter, this.mPopupContext);
        }
    }

    private void ensureMenuView() {
        if (this.mMenuView == null) {
            ActionMenuView actionMenuView = new ActionMenuView(getContext());
            this.mMenuView = actionMenuView;
            actionMenuView.setPopupTheme(this.mPopupTheme);
            this.mMenuView.setOnMenuItemClickListener(this.mMenuViewItemClickListener);
            this.mMenuView.m646o(this.mActionMenuPresenterCallback, this.mMenuBuilderCallback);
            C0214e c0214eGenerateDefaultLayoutParams = generateDefaultLayoutParams();
            c0214eGenerateDefaultLayoutParams.f376a = (this.mButtonGravity & 112) | 8388613;
            this.mMenuView.setLayoutParams(c0214eGenerateDefaultLayoutParams);
            addSystemView(this.mMenuView, false);
        }
    }

    private void ensureNavButtonView() {
        if (this.mNavButtonView == null) {
            this.mNavButtonView = new AppCompatImageButton(getContext(), null, C0560a.toolbarNavigationButtonStyle);
            C0214e c0214eGenerateDefaultLayoutParams = generateDefaultLayoutParams();
            c0214eGenerateDefaultLayoutParams.f376a = (this.mButtonGravity & 112) | 8388611;
            this.mNavButtonView.setLayoutParams(c0214eGenerateDefaultLayoutParams);
        }
    }

    private int getChildHorizontalGravity(int i9) {
        int iM18567s = C4647u.m18567s(this);
        int iM18420b = C4621e.m18420b(i9, iM18567s) & 7;
        return (iM18420b == 1 || iM18420b == 3 || iM18420b == 5) ? iM18420b : iM18567s == 1 ? 5 : 3;
    }

    private int getChildTop(View view, int i9) {
        C0214e c0214e = (C0214e) view.getLayoutParams();
        int measuredHeight = view.getMeasuredHeight();
        int i10 = i9 > 0 ? (measuredHeight - i9) / 2 : 0;
        int childVerticalGravity = getChildVerticalGravity(c0214e.f376a);
        if (childVerticalGravity == 48) {
            return getPaddingTop() - i10;
        }
        if (childVerticalGravity == 80) {
            return (((getHeight() - getPaddingBottom()) - measuredHeight) - ((ViewGroup.MarginLayoutParams) c0214e).bottomMargin) - i10;
        }
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        int height = getHeight();
        int iMax = (((height - paddingTop) - paddingBottom) - measuredHeight) / 2;
        int i11 = ((ViewGroup.MarginLayoutParams) c0214e).topMargin;
        if (iMax < i11) {
            iMax = i11;
        } else {
            int i12 = (((height - paddingBottom) - measuredHeight) - iMax) - paddingTop;
            int i13 = ((ViewGroup.MarginLayoutParams) c0214e).bottomMargin;
            if (i12 < i13) {
                iMax = Math.max(0, iMax - (i13 - i12));
            }
        }
        return paddingTop + iMax;
    }

    private int getChildVerticalGravity(int i9) {
        int i10 = i9 & 112;
        return (i10 == 16 || i10 == 48 || i10 == 80) ? i10 : this.mGravity & 112;
    }

    private int getHorizontalMargins(View view) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        return C4627h.m18429b(marginLayoutParams) + C4627h.m18428a(marginLayoutParams);
    }

    private MenuInflater getMenuInflater() {
        return new C4801g(getContext());
    }

    private int getVerticalMargins(View view) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        return marginLayoutParams.topMargin + marginLayoutParams.bottomMargin;
    }

    private int getViewListMeasuredWidth(List<View> list, int[] iArr) {
        int i9 = iArr[0];
        int i10 = iArr[1];
        int size = list.size();
        int i11 = 0;
        int measuredWidth = 0;
        while (i11 < size) {
            View view = list.get(i11);
            C0214e c0214e = (C0214e) view.getLayoutParams();
            int i12 = ((ViewGroup.MarginLayoutParams) c0214e).leftMargin - i9;
            int i13 = ((ViewGroup.MarginLayoutParams) c0214e).rightMargin - i10;
            int iMax = Math.max(0, i12);
            int iMax2 = Math.max(0, i13);
            int iMax3 = Math.max(0, -i12);
            int iMax4 = Math.max(0, -i13);
            measuredWidth += iMax + view.getMeasuredWidth() + iMax2;
            i11++;
            i10 = iMax4;
            i9 = iMax3;
        }
        return measuredWidth;
    }

    private boolean isChildOrHidden(View view) {
        return view.getParent() == this || this.mHiddenViews.contains(view);
    }

    private int layoutChildLeft(View view, int i9, int[] iArr, int i10) {
        C0214e c0214e = (C0214e) view.getLayoutParams();
        int i11 = ((ViewGroup.MarginLayoutParams) c0214e).leftMargin - iArr[0];
        int iMax = i9 + Math.max(0, i11);
        iArr[0] = Math.max(0, -i11);
        int childTop = getChildTop(view, i10);
        int measuredWidth = view.getMeasuredWidth();
        view.layout(iMax, childTop, iMax + measuredWidth, view.getMeasuredHeight() + childTop);
        return iMax + measuredWidth + ((ViewGroup.MarginLayoutParams) c0214e).rightMargin;
    }

    private int layoutChildRight(View view, int i9, int[] iArr, int i10) {
        C0214e c0214e = (C0214e) view.getLayoutParams();
        int i11 = ((ViewGroup.MarginLayoutParams) c0214e).rightMargin - iArr[1];
        int iMax = i9 - Math.max(0, i11);
        iArr[1] = Math.max(0, -i11);
        int childTop = getChildTop(view, i10);
        int measuredWidth = view.getMeasuredWidth();
        view.layout(iMax - measuredWidth, childTop, iMax, view.getMeasuredHeight() + childTop);
        return iMax - (measuredWidth + ((ViewGroup.MarginLayoutParams) c0214e).leftMargin);
    }

    private int measureChildCollapseMargins(View view, int i9, int i10, int i11, int i12, int[] iArr) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        int i13 = marginLayoutParams.leftMargin - iArr[0];
        int i14 = marginLayoutParams.rightMargin - iArr[1];
        int iMax = Math.max(0, i13) + Math.max(0, i14);
        iArr[0] = Math.max(0, -i13);
        iArr[1] = Math.max(0, -i14);
        view.measure(ViewGroup.getChildMeasureSpec(i9, getPaddingLeft() + getPaddingRight() + iMax + i10, marginLayoutParams.width), ViewGroup.getChildMeasureSpec(i11, getPaddingTop() + getPaddingBottom() + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin + i12, marginLayoutParams.height));
        return view.getMeasuredWidth() + iMax;
    }

    private void measureChildConstrained(View view, int i9, int i10, int i11, int i12, int i13) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        int childMeasureSpec = ViewGroup.getChildMeasureSpec(i9, getPaddingLeft() + getPaddingRight() + marginLayoutParams.leftMargin + marginLayoutParams.rightMargin + i10, marginLayoutParams.width);
        int childMeasureSpec2 = ViewGroup.getChildMeasureSpec(i11, getPaddingTop() + getPaddingBottom() + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin + i12, marginLayoutParams.height);
        int mode = View.MeasureSpec.getMode(childMeasureSpec2);
        if (mode != 1073741824 && i13 >= 0) {
            if (mode != 0) {
                i13 = Math.min(View.MeasureSpec.getSize(childMeasureSpec2), i13);
            }
            childMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(i13, 1073741824);
        }
        view.measure(childMeasureSpec, childMeasureSpec2);
    }

    private void postShowOverflowMenu() {
        removeCallbacks(this.mShowOverflowMenuRunnable);
        post(this.mShowOverflowMenuRunnable);
    }

    private boolean shouldCollapse() {
        if (!this.mCollapsible) {
            return false;
        }
        int childCount = getChildCount();
        for (int i9 = 0; i9 < childCount; i9++) {
            View childAt = getChildAt(i9);
            if (shouldLayout(childAt) && childAt.getMeasuredWidth() > 0 && childAt.getMeasuredHeight() > 0) {
                return false;
            }
        }
        return true;
    }

    private boolean shouldLayout(View view) {
        return (view == null || view.getParent() != this || view.getVisibility() == 8) ? false : true;
    }

    public void addChildrenForExpandedActionView() {
        for (int size = this.mHiddenViews.size() - 1; size >= 0; size--) {
            addView(this.mHiddenViews.get(size));
        }
        this.mHiddenViews.clear();
    }

    public boolean canShowOverflowMenu() {
        ActionMenuView actionMenuView;
        return getVisibility() == 0 && (actionMenuView = this.mMenuView) != null && actionMenuView.m643k();
    }

    @Override // android.view.ViewGroup
    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return super.checkLayoutParams(layoutParams) && (layoutParams instanceof C0214e);
    }

    public void collapseActionView() {
        C0213d c0213d = this.mExpandedMenuPresenter;
        C0139i c0139i = c0213d == null ? null : c0213d.f1012c;
        if (c0139i != null) {
            c0139i.collapseActionView();
        }
    }

    public void dismissPopupMenus() {
        ActionMenuView actionMenuView = this.mMenuView;
        if (actionMenuView != null) {
            actionMenuView.m634b();
        }
    }

    public void ensureCollapseButtonView() {
        if (this.mCollapseButtonView == null) {
            AppCompatImageButton appCompatImageButton = new AppCompatImageButton(getContext(), null, C0560a.toolbarNavigationButtonStyle);
            this.mCollapseButtonView = appCompatImageButton;
            appCompatImageButton.setImageDrawable(this.mCollapseIcon);
            this.mCollapseButtonView.setContentDescription(this.mCollapseDescription);
            C0214e c0214eGenerateDefaultLayoutParams = generateDefaultLayoutParams();
            c0214eGenerateDefaultLayoutParams.f376a = (this.mButtonGravity & 112) | 8388611;
            c0214eGenerateDefaultLayoutParams.f1014b = 2;
            this.mCollapseButtonView.setLayoutParams(c0214eGenerateDefaultLayoutParams);
            this.mCollapseButtonView.setOnClickListener(new ViewOnClickListenerC0212c());
        }
    }

    public CharSequence getCollapseContentDescription() {
        ImageButton imageButton = this.mCollapseButtonView;
        if (imageButton != null) {
            return imageButton.getContentDescription();
        }
        return null;
    }

    public Drawable getCollapseIcon() {
        ImageButton imageButton = this.mCollapseButtonView;
        if (imageButton != null) {
            return imageButton.getDrawable();
        }
        return null;
    }

    public int getContentInsetEnd() {
        C0234i0 c0234i0 = this.mContentInsets;
        if (c0234i0 != null) {
            return c0234i0.m888a();
        }
        return 0;
    }

    public int getContentInsetEndWithActions() {
        int i9 = this.mContentInsetEndWithActions;
        return i9 != Integer.MIN_VALUE ? i9 : getContentInsetEnd();
    }

    public int getContentInsetLeft() {
        C0234i0 c0234i0 = this.mContentInsets;
        if (c0234i0 != null) {
            return c0234i0.m889b();
        }
        return 0;
    }

    public int getContentInsetRight() {
        C0234i0 c0234i0 = this.mContentInsets;
        if (c0234i0 != null) {
            return c0234i0.m890c();
        }
        return 0;
    }

    public int getContentInsetStart() {
        C0234i0 c0234i0 = this.mContentInsets;
        if (c0234i0 != null) {
            return c0234i0.m891d();
        }
        return 0;
    }

    public int getContentInsetStartWithNavigation() {
        int i9 = this.mContentInsetStartWithNavigation;
        return i9 != Integer.MIN_VALUE ? i9 : getContentInsetStart();
    }

    public int getCurrentContentInsetEnd() {
        C0137g c0137gM645n;
        ActionMenuView actionMenuView = this.mMenuView;
        return actionMenuView != null && (c0137gM645n = actionMenuView.m645n()) != null && c0137gM645n.hasVisibleItems() ? Math.max(getContentInsetEnd(), Math.max(this.mContentInsetEndWithActions, 0)) : getContentInsetEnd();
    }

    public int getCurrentContentInsetLeft() {
        return C4647u.m18567s(this) == 1 ? getCurrentContentInsetEnd() : getCurrentContentInsetStart();
    }

    public int getCurrentContentInsetRight() {
        return C4647u.m18567s(this) == 1 ? getCurrentContentInsetStart() : getCurrentContentInsetEnd();
    }

    public int getCurrentContentInsetStart() {
        return getNavigationIcon() != null ? Math.max(getContentInsetStart(), Math.max(this.mContentInsetStartWithNavigation, 0)) : getContentInsetStart();
    }

    public Drawable getLogo() {
        ImageView imageView = this.mLogoView;
        if (imageView != null) {
            return imageView.getDrawable();
        }
        return null;
    }

    public CharSequence getLogoDescription() {
        ImageView imageView = this.mLogoView;
        if (imageView != null) {
            return imageView.getContentDescription();
        }
        return null;
    }

    public Menu getMenu() {
        ensureMenu();
        return this.mMenuView.getMenu();
    }

    public CharSequence getNavigationContentDescription() {
        ImageButton imageButton = this.mNavButtonView;
        if (imageButton != null) {
            return imageButton.getContentDescription();
        }
        return null;
    }

    public Drawable getNavigationIcon() {
        ImageButton imageButton = this.mNavButtonView;
        if (imageButton != null) {
            return imageButton.getDrawable();
        }
        return null;
    }

    public ActionMenuPresenter getOuterActionMenuPresenter() {
        return this.mOuterActionMenuPresenter;
    }

    public Drawable getOverflowIcon() {
        ensureMenu();
        return this.mMenuView.getOverflowIcon();
    }

    public Context getPopupContext() {
        return this.mPopupContext;
    }

    public int getPopupTheme() {
        return this.mPopupTheme;
    }

    public CharSequence getSubtitle() {
        return this.mSubtitleText;
    }

    public final TextView getSubtitleTextView() {
        return this.mSubtitleTextView;
    }

    public CharSequence getTitle() {
        return this.mTitleText;
    }

    public int getTitleMarginBottom() {
        return this.mTitleMarginBottom;
    }

    public int getTitleMarginEnd() {
        return this.mTitleMarginEnd;
    }

    public int getTitleMarginStart() {
        return this.mTitleMarginStart;
    }

    public int getTitleMarginTop() {
        return this.mTitleMarginTop;
    }

    public final TextView getTitleTextView() {
        return this.mTitleTextView;
    }

    public InterfaceC0251r getWrapper() {
        if (this.mWrapper == null) {
            this.mWrapper = new C0252r0(this, true);
        }
        return this.mWrapper;
    }

    public boolean hasExpandedActionView() {
        C0213d c0213d = this.mExpandedMenuPresenter;
        return (c0213d == null || c0213d.f1012c == null) ? false : true;
    }

    public boolean hideOverflowMenu() {
        ActionMenuView actionMenuView = this.mMenuView;
        return actionMenuView != null && actionMenuView.m640h();
    }

    public void inflateMenu(int i9) {
        getMenuInflater().inflate(i9, getMenu());
    }

    public boolean isOverflowMenuShowPending() {
        ActionMenuView actionMenuView = this.mMenuView;
        return actionMenuView != null && actionMenuView.m641i();
    }

    public boolean isOverflowMenuShowing() {
        ActionMenuView actionMenuView = this.mMenuView;
        return actionMenuView != null && actionMenuView.m642j();
    }

    public boolean isTitleTruncated() {
        Layout layout;
        TextView textView = this.mTitleTextView;
        if (textView == null || (layout = textView.getLayout()) == null) {
            return false;
        }
        int lineCount = layout.getLineCount();
        for (int i9 = 0; i9 < lineCount; i9++) {
            if (layout.getEllipsisCount(i9) > 0) {
                return true;
            }
        }
        return false;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        removeCallbacks(this.mShowOverflowMenuRunnable);
    }

    @Override // android.view.View
    public boolean onHoverEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 9) {
            this.mEatingHover = false;
        }
        if (!this.mEatingHover) {
            boolean zOnHoverEvent = super.onHoverEvent(motionEvent);
            if (actionMasked == 9 && !zOnHoverEvent) {
                this.mEatingHover = true;
            }
        }
        if (actionMasked == 10 || actionMasked == 3) {
            this.mEatingHover = false;
        }
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:105:0x029f A[LOOP:0: B:104:0x029d->B:105:0x029f, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:108:0x02c1 A[LOOP:1: B:107:0x02bf->B:108:0x02c1, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:112:0x02eb  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x02fa A[LOOP:2: B:116:0x02f8->B:117:0x02fa, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:19:0x005f  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0076  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00b3  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00ca  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00e7  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0100  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0105  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x011d  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x012b  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x012d  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0130  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0134  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x0137  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x0168  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x01a6  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x01b7  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x0227  */
    @Override // android.view.ViewGroup, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void onLayout(boolean z8, int i9, int i10, int i11, int i12) {
        int iLayoutChildLeft;
        int iLayoutChildRight;
        int iMax;
        boolean zShouldLayout;
        boolean zShouldLayout2;
        int i13;
        int measuredHeight;
        int i14;
        int i15;
        int i16;
        int i17;
        int i18;
        int paddingTop;
        int i19;
        int i20;
        int i21;
        int i22;
        int i23;
        int i24;
        int size;
        int i25;
        int size2;
        int i26;
        int i27;
        int size3;
        boolean z9 = C4647u.m18567s(this) == 1;
        int width = getWidth();
        int height = getHeight();
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int paddingTop2 = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        int i28 = width - paddingRight;
        int[] iArr = this.mTempMargins;
        iArr[1] = 0;
        iArr[0] = 0;
        int iM18568t = C4647u.m18568t(this);
        int iMin = iM18568t >= 0 ? Math.min(iM18568t, i12 - i10) : 0;
        if (!shouldLayout(this.mNavButtonView)) {
            iLayoutChildLeft = paddingLeft;
        } else {
            if (z9) {
                iLayoutChildRight = layoutChildRight(this.mNavButtonView, i28, iArr, iMin);
                iLayoutChildLeft = paddingLeft;
                if (shouldLayout(this.mCollapseButtonView)) {
                    if (z9) {
                        iLayoutChildRight = layoutChildRight(this.mCollapseButtonView, iLayoutChildRight, iArr, iMin);
                    } else {
                        iLayoutChildLeft = layoutChildLeft(this.mCollapseButtonView, iLayoutChildLeft, iArr, iMin);
                    }
                }
                if (shouldLayout(this.mMenuView)) {
                    if (z9) {
                        iLayoutChildLeft = layoutChildLeft(this.mMenuView, iLayoutChildLeft, iArr, iMin);
                    } else {
                        iLayoutChildRight = layoutChildRight(this.mMenuView, iLayoutChildRight, iArr, iMin);
                    }
                }
                int currentContentInsetLeft = getCurrentContentInsetLeft();
                int currentContentInsetRight = getCurrentContentInsetRight();
                iArr[0] = Math.max(0, currentContentInsetLeft - iLayoutChildLeft);
                iArr[1] = Math.max(0, currentContentInsetRight - (i28 - iLayoutChildRight));
                iMax = Math.max(iLayoutChildLeft, currentContentInsetLeft);
                int iMin2 = Math.min(iLayoutChildRight, i28 - currentContentInsetRight);
                if (shouldLayout(this.mExpandedActionView)) {
                    if (z9) {
                        iMin2 = layoutChildRight(this.mExpandedActionView, iMin2, iArr, iMin);
                    } else {
                        iMax = layoutChildLeft(this.mExpandedActionView, iMax, iArr, iMin);
                    }
                }
                if (shouldLayout(this.mLogoView)) {
                    if (z9) {
                        iMin2 = layoutChildRight(this.mLogoView, iMin2, iArr, iMin);
                    } else {
                        iMax = layoutChildLeft(this.mLogoView, iMax, iArr, iMin);
                    }
                }
                zShouldLayout = shouldLayout(this.mTitleTextView);
                zShouldLayout2 = shouldLayout(this.mSubtitleTextView);
                if (zShouldLayout) {
                    i13 = paddingRight;
                    measuredHeight = 0;
                } else {
                    C0214e c0214e = (C0214e) this.mTitleTextView.getLayoutParams();
                    i13 = paddingRight;
                    measuredHeight = ((ViewGroup.MarginLayoutParams) c0214e).topMargin + this.mTitleTextView.getMeasuredHeight() + ((ViewGroup.MarginLayoutParams) c0214e).bottomMargin + 0;
                }
                if (zShouldLayout2) {
                    i14 = width;
                } else {
                    C0214e c0214e2 = (C0214e) this.mSubtitleTextView.getLayoutParams();
                    i14 = width;
                    measuredHeight += ((ViewGroup.MarginLayoutParams) c0214e2).topMargin + this.mSubtitleTextView.getMeasuredHeight() + ((ViewGroup.MarginLayoutParams) c0214e2).bottomMargin;
                }
                if (!zShouldLayout || zShouldLayout2) {
                    TextView textView = !zShouldLayout ? this.mTitleTextView : this.mSubtitleTextView;
                    TextView textView2 = !zShouldLayout2 ? this.mSubtitleTextView : this.mTitleTextView;
                    C0214e c0214e3 = (C0214e) textView.getLayoutParams();
                    C0214e c0214e4 = (C0214e) textView2.getLayoutParams();
                    boolean z10 = (zShouldLayout && this.mTitleTextView.getMeasuredWidth() > 0) || (zShouldLayout2 && this.mSubtitleTextView.getMeasuredWidth() > 0);
                    i15 = this.mGravity & 112;
                    i16 = paddingLeft;
                    if (i15 == 48) {
                        i17 = iMax;
                        i18 = iMin;
                        paddingTop = getPaddingTop() + ((ViewGroup.MarginLayoutParams) c0214e3).topMargin + this.mTitleMarginTop;
                    } else if (i15 != 80) {
                        int iMax2 = (((height - paddingTop2) - paddingBottom) - measuredHeight) / 2;
                        int i29 = ((ViewGroup.MarginLayoutParams) c0214e3).topMargin;
                        i18 = iMin;
                        int i30 = this.mTitleMarginTop;
                        i17 = iMax;
                        if (iMax2 < i29 + i30) {
                            iMax2 = i29 + i30;
                        } else {
                            int i31 = (((height - paddingBottom) - measuredHeight) - iMax2) - paddingTop2;
                            int i32 = ((ViewGroup.MarginLayoutParams) c0214e3).bottomMargin;
                            int i33 = this.mTitleMarginBottom;
                            if (i31 < i32 + i33) {
                                iMax2 = Math.max(0, iMax2 - ((((ViewGroup.MarginLayoutParams) c0214e4).bottomMargin + i33) - i31));
                            }
                        }
                        paddingTop = paddingTop2 + iMax2;
                    } else {
                        i17 = iMax;
                        i18 = iMin;
                        paddingTop = (((height - paddingBottom) - ((ViewGroup.MarginLayoutParams) c0214e4).bottomMargin) - this.mTitleMarginBottom) - measuredHeight;
                    }
                    if (!z9) {
                        if (z10) {
                            i20 = this.mTitleMarginStart;
                            i19 = 0;
                        } else {
                            i19 = 0;
                            i20 = 0;
                        }
                        int i34 = i20 - iArr[i19];
                        iMax = i17 + Math.max(i19, i34);
                        iArr[i19] = Math.max(i19, -i34);
                        if (zShouldLayout) {
                            C0214e c0214e5 = (C0214e) this.mTitleTextView.getLayoutParams();
                            int measuredWidth = this.mTitleTextView.getMeasuredWidth() + iMax;
                            int measuredHeight2 = this.mTitleTextView.getMeasuredHeight() + paddingTop;
                            this.mTitleTextView.layout(iMax, paddingTop, measuredWidth, measuredHeight2);
                            i21 = measuredWidth + this.mTitleMarginEnd;
                            paddingTop = measuredHeight2 + ((ViewGroup.MarginLayoutParams) c0214e5).bottomMargin;
                        } else {
                            i21 = iMax;
                        }
                        if (zShouldLayout2) {
                            int i35 = paddingTop + ((ViewGroup.MarginLayoutParams) ((C0214e) this.mSubtitleTextView.getLayoutParams())).topMargin;
                            int measuredWidth2 = this.mSubtitleTextView.getMeasuredWidth() + iMax;
                            this.mSubtitleTextView.layout(iMax, i35, measuredWidth2, this.mSubtitleTextView.getMeasuredHeight() + i35);
                            i22 = measuredWidth2 + this.mTitleMarginEnd;
                        } else {
                            i22 = iMax;
                        }
                        if (z10) {
                            iMax = Math.max(i21, i22);
                        }
                        addCustomViewsWithGravity(this.mTempViews, 3);
                        size = this.mTempViews.size();
                        for (i25 = i19; i25 < size; i25++) {
                            iMax = layoutChildLeft(this.mTempViews.get(i25), iMax, iArr, i18);
                        }
                        int i36 = i18;
                        addCustomViewsWithGravity(this.mTempViews, 5);
                        size2 = this.mTempViews.size();
                        for (i26 = i19; i26 < size2; i26++) {
                            iMin2 = layoutChildRight(this.mTempViews.get(i26), iMin2, iArr, i36);
                        }
                        addCustomViewsWithGravity(this.mTempViews, 1);
                        int viewListMeasuredWidth = getViewListMeasuredWidth(this.mTempViews, iArr);
                        i27 = (i16 + (((i14 - i16) - i13) / 2)) - (viewListMeasuredWidth / 2);
                        int i37 = viewListMeasuredWidth + i27;
                        if (i27 >= iMax) {
                            iMax = i37 > iMin2 ? i27 - (i37 - iMin2) : i27;
                        }
                        size3 = this.mTempViews.size();
                        while (i19 < size3) {
                            iMax = layoutChildLeft(this.mTempViews.get(i19), iMax, iArr, i36);
                            i19++;
                        }
                        this.mTempViews.clear();
                    }
                    int i38 = (z10 ? this.mTitleMarginStart : 0) - iArr[1];
                    iMin2 -= Math.max(0, i38);
                    iArr[1] = Math.max(0, -i38);
                    if (zShouldLayout) {
                        C0214e c0214e6 = (C0214e) this.mTitleTextView.getLayoutParams();
                        int measuredWidth3 = iMin2 - this.mTitleTextView.getMeasuredWidth();
                        int measuredHeight3 = this.mTitleTextView.getMeasuredHeight() + paddingTop;
                        this.mTitleTextView.layout(measuredWidth3, paddingTop, iMin2, measuredHeight3);
                        i23 = measuredWidth3 - this.mTitleMarginEnd;
                        paddingTop = measuredHeight3 + ((ViewGroup.MarginLayoutParams) c0214e6).bottomMargin;
                    } else {
                        i23 = iMin2;
                    }
                    if (zShouldLayout2) {
                        int i39 = paddingTop + ((ViewGroup.MarginLayoutParams) ((C0214e) this.mSubtitleTextView.getLayoutParams())).topMargin;
                        this.mSubtitleTextView.layout(iMin2 - this.mSubtitleTextView.getMeasuredWidth(), i39, iMin2, this.mSubtitleTextView.getMeasuredHeight() + i39);
                        i24 = iMin2 - this.mTitleMarginEnd;
                    } else {
                        i24 = iMin2;
                    }
                    if (z10) {
                        iMin2 = Math.min(i23, i24);
                    }
                    iMax = i17;
                } else {
                    i16 = paddingLeft;
                    i18 = iMin;
                }
                i19 = 0;
                addCustomViewsWithGravity(this.mTempViews, 3);
                size = this.mTempViews.size();
                while (i25 < size) {
                }
                int i362 = i18;
                addCustomViewsWithGravity(this.mTempViews, 5);
                size2 = this.mTempViews.size();
                while (i26 < size2) {
                }
                addCustomViewsWithGravity(this.mTempViews, 1);
                int viewListMeasuredWidth2 = getViewListMeasuredWidth(this.mTempViews, iArr);
                i27 = (i16 + (((i14 - i16) - i13) / 2)) - (viewListMeasuredWidth2 / 2);
                int i372 = viewListMeasuredWidth2 + i27;
                if (i27 >= iMax) {
                }
                size3 = this.mTempViews.size();
                while (i19 < size3) {
                }
                this.mTempViews.clear();
            }
            iLayoutChildLeft = layoutChildLeft(this.mNavButtonView, paddingLeft, iArr, iMin);
        }
        iLayoutChildRight = i28;
        if (shouldLayout(this.mCollapseButtonView)) {
        }
        if (shouldLayout(this.mMenuView)) {
        }
        int currentContentInsetLeft2 = getCurrentContentInsetLeft();
        int currentContentInsetRight2 = getCurrentContentInsetRight();
        iArr[0] = Math.max(0, currentContentInsetLeft2 - iLayoutChildLeft);
        iArr[1] = Math.max(0, currentContentInsetRight2 - (i28 - iLayoutChildRight));
        iMax = Math.max(iLayoutChildLeft, currentContentInsetLeft2);
        int iMin22 = Math.min(iLayoutChildRight, i28 - currentContentInsetRight2);
        if (shouldLayout(this.mExpandedActionView)) {
        }
        if (shouldLayout(this.mLogoView)) {
        }
        zShouldLayout = shouldLayout(this.mTitleTextView);
        zShouldLayout2 = shouldLayout(this.mSubtitleTextView);
        if (zShouldLayout) {
        }
        if (zShouldLayout2) {
        }
        if (zShouldLayout) {
            if (!zShouldLayout) {
            }
            if (!zShouldLayout2) {
            }
            C0214e c0214e32 = (C0214e) textView.getLayoutParams();
            C0214e c0214e42 = (C0214e) textView2.getLayoutParams();
            if (zShouldLayout) {
                i15 = this.mGravity & 112;
                i16 = paddingLeft;
                if (i15 == 48) {
                }
                if (!z9) {
                }
            } else {
                i15 = this.mGravity & 112;
                i16 = paddingLeft;
                if (i15 == 48) {
                }
                if (!z9) {
                }
            }
        }
        addCustomViewsWithGravity(this.mTempViews, 3);
        size = this.mTempViews.size();
        while (i25 < size) {
        }
        int i3622 = i18;
        addCustomViewsWithGravity(this.mTempViews, 5);
        size2 = this.mTempViews.size();
        while (i26 < size2) {
        }
        addCustomViewsWithGravity(this.mTempViews, 1);
        int viewListMeasuredWidth22 = getViewListMeasuredWidth(this.mTempViews, iArr);
        i27 = (i16 + (((i14 - i16) - i13) / 2)) - (viewListMeasuredWidth22 / 2);
        int i3722 = viewListMeasuredWidth22 + i27;
        if (i27 >= iMax) {
        }
        size3 = this.mTempViews.size();
        while (i19 < size3) {
        }
        this.mTempViews.clear();
    }

    @Override // android.view.View
    public void onMeasure(int i9, int i10) {
        int measuredWidth;
        int iMax;
        int iCombineMeasuredStates;
        int measuredWidth2;
        int measuredHeight;
        int iCombineMeasuredStates2;
        int iMax2;
        int[] iArr = this.mTempMargins;
        boolean zM1068b = C0258u0.m1068b(this);
        int i11 = !zM1068b ? 1 : 0;
        if (shouldLayout(this.mNavButtonView)) {
            measureChildConstrained(this.mNavButtonView, i9, 0, i10, 0, this.mMaxButtonHeight);
            measuredWidth = this.mNavButtonView.getMeasuredWidth() + getHorizontalMargins(this.mNavButtonView);
            iMax = Math.max(0, this.mNavButtonView.getMeasuredHeight() + getVerticalMargins(this.mNavButtonView));
            iCombineMeasuredStates = View.combineMeasuredStates(0, this.mNavButtonView.getMeasuredState());
        } else {
            measuredWidth = 0;
            iMax = 0;
            iCombineMeasuredStates = 0;
        }
        if (shouldLayout(this.mCollapseButtonView)) {
            measureChildConstrained(this.mCollapseButtonView, i9, 0, i10, 0, this.mMaxButtonHeight);
            measuredWidth = this.mCollapseButtonView.getMeasuredWidth() + getHorizontalMargins(this.mCollapseButtonView);
            iMax = Math.max(iMax, this.mCollapseButtonView.getMeasuredHeight() + getVerticalMargins(this.mCollapseButtonView));
            iCombineMeasuredStates = View.combineMeasuredStates(iCombineMeasuredStates, this.mCollapseButtonView.getMeasuredState());
        }
        int currentContentInsetStart = getCurrentContentInsetStart();
        int iMax3 = 0 + Math.max(currentContentInsetStart, measuredWidth);
        iArr[zM1068b ? 1 : 0] = Math.max(0, currentContentInsetStart - measuredWidth);
        if (shouldLayout(this.mMenuView)) {
            measureChildConstrained(this.mMenuView, i9, iMax3, i10, 0, this.mMaxButtonHeight);
            measuredWidth2 = this.mMenuView.getMeasuredWidth() + getHorizontalMargins(this.mMenuView);
            iMax = Math.max(iMax, this.mMenuView.getMeasuredHeight() + getVerticalMargins(this.mMenuView));
            iCombineMeasuredStates = View.combineMeasuredStates(iCombineMeasuredStates, this.mMenuView.getMeasuredState());
        } else {
            measuredWidth2 = 0;
        }
        int currentContentInsetEnd = getCurrentContentInsetEnd();
        int iMax4 = iMax3 + Math.max(currentContentInsetEnd, measuredWidth2);
        iArr[i11] = Math.max(0, currentContentInsetEnd - measuredWidth2);
        if (shouldLayout(this.mExpandedActionView)) {
            iMax4 += measureChildCollapseMargins(this.mExpandedActionView, i9, iMax4, i10, 0, iArr);
            iMax = Math.max(iMax, this.mExpandedActionView.getMeasuredHeight() + getVerticalMargins(this.mExpandedActionView));
            iCombineMeasuredStates = View.combineMeasuredStates(iCombineMeasuredStates, this.mExpandedActionView.getMeasuredState());
        }
        if (shouldLayout(this.mLogoView)) {
            iMax4 += measureChildCollapseMargins(this.mLogoView, i9, iMax4, i10, 0, iArr);
            iMax = Math.max(iMax, this.mLogoView.getMeasuredHeight() + getVerticalMargins(this.mLogoView));
            iCombineMeasuredStates = View.combineMeasuredStates(iCombineMeasuredStates, this.mLogoView.getMeasuredState());
        }
        int childCount = getChildCount();
        for (int i12 = 0; i12 < childCount; i12++) {
            View childAt = getChildAt(i12);
            if (((C0214e) childAt.getLayoutParams()).f1014b == 0 && shouldLayout(childAt)) {
                iMax4 += measureChildCollapseMargins(childAt, i9, iMax4, i10, 0, iArr);
                iMax = Math.max(iMax, childAt.getMeasuredHeight() + getVerticalMargins(childAt));
                iCombineMeasuredStates = View.combineMeasuredStates(iCombineMeasuredStates, childAt.getMeasuredState());
            }
        }
        int i13 = this.mTitleMarginTop + this.mTitleMarginBottom;
        int i14 = this.mTitleMarginStart + this.mTitleMarginEnd;
        if (shouldLayout(this.mTitleTextView)) {
            measureChildCollapseMargins(this.mTitleTextView, i9, iMax4 + i14, i10, i13, iArr);
            int measuredWidth3 = this.mTitleTextView.getMeasuredWidth() + getHorizontalMargins(this.mTitleTextView);
            measuredHeight = this.mTitleTextView.getMeasuredHeight() + getVerticalMargins(this.mTitleTextView);
            iCombineMeasuredStates2 = View.combineMeasuredStates(iCombineMeasuredStates, this.mTitleTextView.getMeasuredState());
            iMax2 = measuredWidth3;
        } else {
            measuredHeight = 0;
            iCombineMeasuredStates2 = iCombineMeasuredStates;
            iMax2 = 0;
        }
        if (shouldLayout(this.mSubtitleTextView)) {
            iMax2 = Math.max(iMax2, measureChildCollapseMargins(this.mSubtitleTextView, i9, iMax4 + i14, i10, measuredHeight + i13, iArr));
            measuredHeight += this.mSubtitleTextView.getMeasuredHeight() + getVerticalMargins(this.mSubtitleTextView);
            iCombineMeasuredStates2 = View.combineMeasuredStates(iCombineMeasuredStates2, this.mSubtitleTextView.getMeasuredState());
        }
        setMeasuredDimension(View.resolveSizeAndState(Math.max(iMax4 + iMax2 + getPaddingLeft() + getPaddingRight(), getSuggestedMinimumWidth()), i9, (-16777216) & iCombineMeasuredStates2), shouldCollapse() ? 0 : View.resolveSizeAndState(Math.max(Math.max(iMax, measuredHeight) + getPaddingTop() + getPaddingBottom(), getSuggestedMinimumHeight()), i10, iCombineMeasuredStates2 << 16));
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        MenuItem menuItemFindItem;
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        ActionMenuView actionMenuView = this.mMenuView;
        C0137g c0137gM645n = actionMenuView != null ? actionMenuView.m645n() : null;
        int i9 = savedState.f1006b;
        if (i9 != 0 && this.mExpandedMenuPresenter != null && c0137gM645n != null && (menuItemFindItem = c0137gM645n.findItem(i9)) != null) {
            menuItemFindItem.expandActionView();
        }
        if (savedState.f1007c) {
            postShowOverflowMenu();
        }
    }

    @Override // android.view.View
    public void onRtlPropertiesChanged(int i9) {
        super.onRtlPropertiesChanged(i9);
        ensureContentInsets();
        this.mContentInsets.m893f(i9 == 1);
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        C0139i c0139i;
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        C0213d c0213d = this.mExpandedMenuPresenter;
        if (c0213d != null && (c0139i = c0213d.f1012c) != null) {
            savedState.f1006b = c0139i.getItemId();
        }
        savedState.f1007c = isOverflowMenuShowing();
        return savedState;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            this.mEatingTouch = false;
        }
        if (!this.mEatingTouch) {
            boolean zOnTouchEvent = super.onTouchEvent(motionEvent);
            if (actionMasked == 0 && !zOnTouchEvent) {
                this.mEatingTouch = true;
            }
        }
        if (actionMasked == 1 || actionMasked == 3) {
            this.mEatingTouch = false;
        }
        return true;
    }

    public void removeChildrenForExpandedActionView() {
        for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
            View childAt = getChildAt(childCount);
            if (((C0214e) childAt.getLayoutParams()).f1014b != 2 && childAt != this.mMenuView) {
                removeViewAt(childCount);
                this.mHiddenViews.add(childAt);
            }
        }
    }

    public void setCollapseContentDescription(int i9) {
        setCollapseContentDescription(i9 != 0 ? getContext().getText(i9) : null);
    }

    public void setCollapseIcon(int i9) {
        setCollapseIcon(C0694a.m3458b(getContext(), i9));
    }

    public void setCollapsible(boolean z8) {
        this.mCollapsible = z8;
        requestLayout();
    }

    public void setContentInsetEndWithActions(int i9) {
        if (i9 < 0) {
            i9 = Integer.MIN_VALUE;
        }
        if (i9 != this.mContentInsetEndWithActions) {
            this.mContentInsetEndWithActions = i9;
            if (getNavigationIcon() != null) {
                requestLayout();
            }
        }
    }

    public void setContentInsetStartWithNavigation(int i9) {
        if (i9 < 0) {
            i9 = Integer.MIN_VALUE;
        }
        if (i9 != this.mContentInsetStartWithNavigation) {
            this.mContentInsetStartWithNavigation = i9;
            if (getNavigationIcon() != null) {
                requestLayout();
            }
        }
    }

    public void setContentInsetsAbsolute(int i9, int i10) {
        ensureContentInsets();
        this.mContentInsets.m892e(i9, i10);
    }

    public void setContentInsetsRelative(int i9, int i10) {
        ensureContentInsets();
        this.mContentInsets.m894g(i9, i10);
    }

    public void setLogo(int i9) {
        setLogo(C0694a.m3458b(getContext(), i9));
    }

    public void setLogoDescription(int i9) {
        setLogoDescription(getContext().getText(i9));
    }

    public void setMenu(C0137g c0137g, ActionMenuPresenter actionMenuPresenter) {
        if (c0137g == null && this.mMenuView == null) {
            return;
        }
        ensureMenuView();
        C0137g c0137gM645n = this.mMenuView.m645n();
        if (c0137gM645n == c0137g) {
            return;
        }
        if (c0137gM645n != null) {
            c0137gM645n.removeMenuPresenter(this.mOuterActionMenuPresenter);
            c0137gM645n.removeMenuPresenter(this.mExpandedMenuPresenter);
        }
        if (this.mExpandedMenuPresenter == null) {
            this.mExpandedMenuPresenter = new C0213d();
        }
        actionMenuPresenter.m629z(true);
        if (c0137g != null) {
            c0137g.addMenuPresenter(actionMenuPresenter, this.mPopupContext);
            c0137g.addMenuPresenter(this.mExpandedMenuPresenter, this.mPopupContext);
        } else {
            actionMenuPresenter.initForMenu(this.mPopupContext, null);
            this.mExpandedMenuPresenter.initForMenu(this.mPopupContext, null);
            actionMenuPresenter.updateMenuView(true);
            this.mExpandedMenuPresenter.updateMenuView(true);
        }
        this.mMenuView.setPopupTheme(this.mPopupTheme);
        this.mMenuView.setPresenter(actionMenuPresenter);
        this.mOuterActionMenuPresenter = actionMenuPresenter;
    }

    public void setMenuCallbacks(InterfaceC0143m.a aVar, C0137g.a aVar2) {
        this.mActionMenuPresenterCallback = aVar;
        this.mMenuBuilderCallback = aVar2;
        ActionMenuView actionMenuView = this.mMenuView;
        if (actionMenuView != null) {
            actionMenuView.m646o(aVar, aVar2);
        }
    }

    public void setNavigationContentDescription(int i9) {
        setNavigationContentDescription(i9 != 0 ? getContext().getText(i9) : null);
    }

    public void setNavigationIcon(int i9) {
        setNavigationIcon(C0694a.m3458b(getContext(), i9));
    }

    public void setNavigationOnClickListener(View.OnClickListener onClickListener) {
        ensureNavButtonView();
        this.mNavButtonView.setOnClickListener(onClickListener);
    }

    public void setOnMenuItemClickListener(InterfaceC0215f interfaceC0215f) {
    }

    public void setOverflowIcon(Drawable drawable) {
        ensureMenu();
        this.mMenuView.setOverflowIcon(drawable);
    }

    public void setPopupTheme(int i9) {
        if (this.mPopupTheme != i9) {
            this.mPopupTheme = i9;
            if (i9 == 0) {
                this.mPopupContext = getContext();
            } else {
                this.mPopupContext = new ContextThemeWrapper(getContext(), i9);
            }
        }
    }

    public void setSubtitle(int i9) {
        setSubtitle(getContext().getText(i9));
    }

    public void setSubtitleTextAppearance(Context context, int i9) {
        this.mSubtitleTextAppearance = i9;
        TextView textView = this.mSubtitleTextView;
        if (textView != null) {
            textView.setTextAppearance(context, i9);
        }
    }

    public void setSubtitleTextColor(int i9) {
        setSubtitleTextColor(ColorStateList.valueOf(i9));
    }

    public void setTitle(int i9) {
        setTitle(getContext().getText(i9));
    }

    public void setTitleMargin(int i9, int i10, int i11, int i12) {
        this.mTitleMarginStart = i9;
        this.mTitleMarginTop = i10;
        this.mTitleMarginEnd = i11;
        this.mTitleMarginBottom = i12;
        requestLayout();
    }

    public void setTitleMarginBottom(int i9) {
        this.mTitleMarginBottom = i9;
        requestLayout();
    }

    public void setTitleMarginEnd(int i9) {
        this.mTitleMarginEnd = i9;
        requestLayout();
    }

    public void setTitleMarginStart(int i9) {
        this.mTitleMarginStart = i9;
        requestLayout();
    }

    public void setTitleMarginTop(int i9) {
        this.mTitleMarginTop = i9;
        requestLayout();
    }

    public void setTitleTextAppearance(Context context, int i9) {
        this.mTitleTextAppearance = i9;
        TextView textView = this.mTitleTextView;
        if (textView != null) {
            textView.setTextAppearance(context, i9);
        }
    }

    public void setTitleTextColor(int i9) {
        setTitleTextColor(ColorStateList.valueOf(i9));
    }

    public boolean showOverflowMenu() {
        ActionMenuView actionMenuView = this.mMenuView;
        return actionMenuView != null && actionMenuView.m647p();
    }

    /* renamed from: androidx.appcompat.widget.Toolbar$e */
    public static class C0214e extends AbstractC0119a.a {

        /* renamed from: b */
        public int f1014b;

        public C0214e(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.f1014b = 0;
        }

        /* renamed from: a */
        public void m780a(ViewGroup.MarginLayoutParams marginLayoutParams) {
            ((ViewGroup.MarginLayoutParams) this).leftMargin = marginLayoutParams.leftMargin;
            ((ViewGroup.MarginLayoutParams) this).topMargin = marginLayoutParams.topMargin;
            ((ViewGroup.MarginLayoutParams) this).rightMargin = marginLayoutParams.rightMargin;
            ((ViewGroup.MarginLayoutParams) this).bottomMargin = marginLayoutParams.bottomMargin;
        }

        public C0214e(int i9, int i10) {
            super(i9, i10);
            this.f1014b = 0;
            this.f376a = 8388627;
        }

        public C0214e(C0214e c0214e) {
            super((AbstractC0119a.a) c0214e);
            this.f1014b = 0;
            this.f1014b = c0214e.f1014b;
        }

        public C0214e(AbstractC0119a.a aVar) {
            super(aVar);
            this.f1014b = 0;
        }

        public C0214e(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
            this.f1014b = 0;
            m780a(marginLayoutParams);
        }

        public C0214e(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.f1014b = 0;
        }
    }

    public Toolbar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, C0560a.toolbarStyle);
    }

    @Override // android.view.ViewGroup
    public C0214e generateDefaultLayoutParams() {
        return new C0214e(-2, -2);
    }

    public void setCollapseContentDescription(CharSequence charSequence) {
        if (!TextUtils.isEmpty(charSequence)) {
            ensureCollapseButtonView();
        }
        ImageButton imageButton = this.mCollapseButtonView;
        if (imageButton != null) {
            imageButton.setContentDescription(charSequence);
        }
    }

    public void setCollapseIcon(Drawable drawable) {
        if (drawable != null) {
            ensureCollapseButtonView();
            this.mCollapseButtonView.setImageDrawable(drawable);
        } else {
            ImageButton imageButton = this.mCollapseButtonView;
            if (imageButton != null) {
                imageButton.setImageDrawable(this.mCollapseIcon);
            }
        }
    }

    public void setLogo(Drawable drawable) {
        if (drawable != null) {
            ensureLogoView();
            if (!isChildOrHidden(this.mLogoView)) {
                addSystemView(this.mLogoView, true);
            }
        } else {
            ImageView imageView = this.mLogoView;
            if (imageView != null && isChildOrHidden(imageView)) {
                removeView(this.mLogoView);
                this.mHiddenViews.remove(this.mLogoView);
            }
        }
        ImageView imageView2 = this.mLogoView;
        if (imageView2 != null) {
            imageView2.setImageDrawable(drawable);
        }
    }

    public void setLogoDescription(CharSequence charSequence) {
        if (!TextUtils.isEmpty(charSequence)) {
            ensureLogoView();
        }
        ImageView imageView = this.mLogoView;
        if (imageView != null) {
            imageView.setContentDescription(charSequence);
        }
    }

    public void setNavigationContentDescription(CharSequence charSequence) {
        if (!TextUtils.isEmpty(charSequence)) {
            ensureNavButtonView();
        }
        ImageButton imageButton = this.mNavButtonView;
        if (imageButton != null) {
            imageButton.setContentDescription(charSequence);
        }
    }

    public void setNavigationIcon(Drawable drawable) {
        if (drawable != null) {
            ensureNavButtonView();
            if (!isChildOrHidden(this.mNavButtonView)) {
                addSystemView(this.mNavButtonView, true);
            }
        } else {
            ImageButton imageButton = this.mNavButtonView;
            if (imageButton != null && isChildOrHidden(imageButton)) {
                removeView(this.mNavButtonView);
                this.mHiddenViews.remove(this.mNavButtonView);
            }
        }
        ImageButton imageButton2 = this.mNavButtonView;
        if (imageButton2 != null) {
            imageButton2.setImageDrawable(drawable);
        }
    }

    public void setSubtitle(CharSequence charSequence) {
        if (TextUtils.isEmpty(charSequence)) {
            TextView textView = this.mSubtitleTextView;
            if (textView != null && isChildOrHidden(textView)) {
                removeView(this.mSubtitleTextView);
                this.mHiddenViews.remove(this.mSubtitleTextView);
            }
        } else {
            if (this.mSubtitleTextView == null) {
                Context context = getContext();
                AppCompatTextView appCompatTextView = new AppCompatTextView(context);
                this.mSubtitleTextView = appCompatTextView;
                appCompatTextView.setSingleLine();
                this.mSubtitleTextView.setEllipsize(TextUtils.TruncateAt.END);
                int i9 = this.mSubtitleTextAppearance;
                if (i9 != 0) {
                    this.mSubtitleTextView.setTextAppearance(context, i9);
                }
                ColorStateList colorStateList = this.mSubtitleTextColor;
                if (colorStateList != null) {
                    this.mSubtitleTextView.setTextColor(colorStateList);
                }
            }
            if (!isChildOrHidden(this.mSubtitleTextView)) {
                addSystemView(this.mSubtitleTextView, true);
            }
        }
        TextView textView2 = this.mSubtitleTextView;
        if (textView2 != null) {
            textView2.setText(charSequence);
        }
        this.mSubtitleText = charSequence;
    }

    public void setSubtitleTextColor(ColorStateList colorStateList) {
        this.mSubtitleTextColor = colorStateList;
        TextView textView = this.mSubtitleTextView;
        if (textView != null) {
            textView.setTextColor(colorStateList);
        }
    }

    public void setTitle(CharSequence charSequence) {
        if (TextUtils.isEmpty(charSequence)) {
            TextView textView = this.mTitleTextView;
            if (textView != null && isChildOrHidden(textView)) {
                removeView(this.mTitleTextView);
                this.mHiddenViews.remove(this.mTitleTextView);
            }
        } else {
            if (this.mTitleTextView == null) {
                Context context = getContext();
                AppCompatTextView appCompatTextView = new AppCompatTextView(context);
                this.mTitleTextView = appCompatTextView;
                appCompatTextView.setSingleLine();
                this.mTitleTextView.setEllipsize(TextUtils.TruncateAt.END);
                int i9 = this.mTitleTextAppearance;
                if (i9 != 0) {
                    this.mTitleTextView.setTextAppearance(context, i9);
                }
                ColorStateList colorStateList = this.mTitleTextColor;
                if (colorStateList != null) {
                    this.mTitleTextView.setTextColor(colorStateList);
                }
            }
            if (!isChildOrHidden(this.mTitleTextView)) {
                addSystemView(this.mTitleTextView, true);
            }
        }
        TextView textView2 = this.mTitleTextView;
        if (textView2 != null) {
            textView2.setText(charSequence);
        }
        this.mTitleText = charSequence;
    }

    public void setTitleTextColor(ColorStateList colorStateList) {
        this.mTitleTextColor = colorStateList;
        TextView textView = this.mTitleTextView;
        if (textView != null) {
            textView.setTextColor(colorStateList);
        }
    }

    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new C0209a();

        /* renamed from: b */
        public int f1006b;

        /* renamed from: c */
        public boolean f1007c;

        /* renamed from: androidx.appcompat.widget.Toolbar$SavedState$a */
        public class C0209a implements Parcelable.ClassLoaderCreator<SavedState> {
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
            this.f1006b = parcel.readInt();
            this.f1007c = parcel.readInt() != 0;
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i9) {
            super.writeToParcel(parcel, i9);
            parcel.writeInt(this.f1006b);
            parcel.writeInt(this.f1007c ? 1 : 0);
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }
    }

    public Toolbar(Context context, AttributeSet attributeSet, int i9) {
        super(context, attributeSet, i9);
        this.mGravity = 8388627;
        this.mTempViews = new ArrayList<>();
        this.mHiddenViews = new ArrayList<>();
        this.mTempMargins = new int[2];
        this.mMenuViewItemClickListener = new C0210a();
        this.mShowOverflowMenuRunnable = new RunnableC0211b();
        Context context2 = getContext();
        int[] iArr = C0569j.Toolbar;
        C0250q0 c0250q0M1004v = C0250q0.m1004v(context2, attributeSet, iArr, i9, 0);
        C4647u.m18528X(this, context, iArr, attributeSet, c0250q0M1004v.m1022r(), i9, 0);
        this.mTitleTextAppearance = c0250q0M1004v.m1018n(C0569j.Toolbar_titleTextAppearance, 0);
        this.mSubtitleTextAppearance = c0250q0M1004v.m1018n(C0569j.Toolbar_subtitleTextAppearance, 0);
        this.mGravity = c0250q0M1004v.m1016l(C0569j.Toolbar_android_gravity, this.mGravity);
        this.mButtonGravity = c0250q0M1004v.m1016l(C0569j.Toolbar_buttonGravity, 48);
        int iM1009e = c0250q0M1004v.m1009e(C0569j.Toolbar_titleMargin, 0);
        int i10 = C0569j.Toolbar_titleMargins;
        iM1009e = c0250q0M1004v.m1023s(i10) ? c0250q0M1004v.m1009e(i10, iM1009e) : iM1009e;
        this.mTitleMarginBottom = iM1009e;
        this.mTitleMarginTop = iM1009e;
        this.mTitleMarginEnd = iM1009e;
        this.mTitleMarginStart = iM1009e;
        int iM1009e2 = c0250q0M1004v.m1009e(C0569j.Toolbar_titleMarginStart, -1);
        if (iM1009e2 >= 0) {
            this.mTitleMarginStart = iM1009e2;
        }
        int iM1009e3 = c0250q0M1004v.m1009e(C0569j.Toolbar_titleMarginEnd, -1);
        if (iM1009e3 >= 0) {
            this.mTitleMarginEnd = iM1009e3;
        }
        int iM1009e4 = c0250q0M1004v.m1009e(C0569j.Toolbar_titleMarginTop, -1);
        if (iM1009e4 >= 0) {
            this.mTitleMarginTop = iM1009e4;
        }
        int iM1009e5 = c0250q0M1004v.m1009e(C0569j.Toolbar_titleMarginBottom, -1);
        if (iM1009e5 >= 0) {
            this.mTitleMarginBottom = iM1009e5;
        }
        this.mMaxButtonHeight = c0250q0M1004v.m1010f(C0569j.Toolbar_maxButtonHeight, -1);
        int iM1009e6 = c0250q0M1004v.m1009e(C0569j.Toolbar_contentInsetStart, Integer.MIN_VALUE);
        int iM1009e7 = c0250q0M1004v.m1009e(C0569j.Toolbar_contentInsetEnd, Integer.MIN_VALUE);
        int iM1010f = c0250q0M1004v.m1010f(C0569j.Toolbar_contentInsetLeft, 0);
        int iM1010f2 = c0250q0M1004v.m1010f(C0569j.Toolbar_contentInsetRight, 0);
        ensureContentInsets();
        this.mContentInsets.m892e(iM1010f, iM1010f2);
        if (iM1009e6 != Integer.MIN_VALUE || iM1009e7 != Integer.MIN_VALUE) {
            this.mContentInsets.m894g(iM1009e6, iM1009e7);
        }
        this.mContentInsetStartWithNavigation = c0250q0M1004v.m1009e(C0569j.Toolbar_contentInsetStartWithNavigation, Integer.MIN_VALUE);
        this.mContentInsetEndWithActions = c0250q0M1004v.m1009e(C0569j.Toolbar_contentInsetEndWithActions, Integer.MIN_VALUE);
        this.mCollapseIcon = c0250q0M1004v.m1011g(C0569j.Toolbar_collapseIcon);
        this.mCollapseDescription = c0250q0M1004v.m1020p(C0569j.Toolbar_collapseContentDescription);
        CharSequence charSequenceM1020p = c0250q0M1004v.m1020p(C0569j.Toolbar_title);
        if (!TextUtils.isEmpty(charSequenceM1020p)) {
            setTitle(charSequenceM1020p);
        }
        CharSequence charSequenceM1020p2 = c0250q0M1004v.m1020p(C0569j.Toolbar_subtitle);
        if (!TextUtils.isEmpty(charSequenceM1020p2)) {
            setSubtitle(charSequenceM1020p2);
        }
        this.mPopupContext = getContext();
        setPopupTheme(c0250q0M1004v.m1018n(C0569j.Toolbar_popupTheme, 0));
        Drawable drawableM1011g = c0250q0M1004v.m1011g(C0569j.Toolbar_navigationIcon);
        if (drawableM1011g != null) {
            setNavigationIcon(drawableM1011g);
        }
        CharSequence charSequenceM1020p3 = c0250q0M1004v.m1020p(C0569j.Toolbar_navigationContentDescription);
        if (!TextUtils.isEmpty(charSequenceM1020p3)) {
            setNavigationContentDescription(charSequenceM1020p3);
        }
        Drawable drawableM1011g2 = c0250q0M1004v.m1011g(C0569j.Toolbar_logo);
        if (drawableM1011g2 != null) {
            setLogo(drawableM1011g2);
        }
        CharSequence charSequenceM1020p4 = c0250q0M1004v.m1020p(C0569j.Toolbar_logoDescription);
        if (!TextUtils.isEmpty(charSequenceM1020p4)) {
            setLogoDescription(charSequenceM1020p4);
        }
        int i11 = C0569j.Toolbar_titleTextColor;
        if (c0250q0M1004v.m1023s(i11)) {
            setTitleTextColor(c0250q0M1004v.m1007c(i11));
        }
        int i12 = C0569j.Toolbar_subtitleTextColor;
        if (c0250q0M1004v.m1023s(i12)) {
            setSubtitleTextColor(c0250q0M1004v.m1007c(i12));
        }
        int i13 = C0569j.Toolbar_menu;
        if (c0250q0M1004v.m1023s(i13)) {
            inflateMenu(c0250q0M1004v.m1018n(i13, 0));
        }
        c0250q0M1004v.m1024w();
    }

    @Override // android.view.ViewGroup
    public C0214e generateLayoutParams(AttributeSet attributeSet) {
        return new C0214e(getContext(), attributeSet);
    }

    @Override // android.view.ViewGroup
    public C0214e generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        if (layoutParams instanceof C0214e) {
            return new C0214e((C0214e) layoutParams);
        }
        if (layoutParams instanceof AbstractC0119a.a) {
            return new C0214e((AbstractC0119a.a) layoutParams);
        }
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            return new C0214e((ViewGroup.MarginLayoutParams) layoutParams);
        }
        return new C0214e(layoutParams);
    }
}
