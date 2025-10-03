package androidx.appcompat.widget;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.view.menu.C0137g;
import p010b.C0560a;
import p010b.C0565f;
import p010b.C0566g;
import p010b.C0569j;
import p042d0.C4620d0;
import p042d0.C4647u;
import p071g.AbstractC4796b;

/* loaded from: classes.dex */
public class ActionBarContextView extends AbstractC0217a {

    /* renamed from: j */
    public CharSequence f658j;

    /* renamed from: k */
    public CharSequence f659k;

    /* renamed from: l */
    public View f660l;

    /* renamed from: m */
    public View f661m;

    /* renamed from: n */
    public LinearLayout f662n;

    /* renamed from: o */
    public TextView f663o;

    /* renamed from: p */
    public TextView f664p;

    /* renamed from: q */
    public int f665q;

    /* renamed from: r */
    public int f666r;

    /* renamed from: s */
    public boolean f667s;

    /* renamed from: t */
    public int f668t;

    /* renamed from: androidx.appcompat.widget.ActionBarContextView$a */
    public class ViewOnClickListenerC0150a implements View.OnClickListener {

        /* renamed from: b */
        public final /* synthetic */ AbstractC4796b f669b;

        public ViewOnClickListenerC0150a(AbstractC4796b abstractC4796b) {
            this.f669b = abstractC4796b;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            this.f669b.mo438a();
        }
    }

    public ActionBarContextView(Context context) {
        this(context, null);
    }

    @Override // androidx.appcompat.widget.AbstractC0217a
    /* renamed from: f */
    public /* bridge */ /* synthetic */ C4620d0 mo579f(int i9, long j9) {
        return super.mo579f(i9, j9);
    }

    /* renamed from: g */
    public void m580g() {
        if (this.f660l == null) {
            m584k();
        }
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new ViewGroup.MarginLayoutParams(-1, -2);
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new ViewGroup.MarginLayoutParams(getContext(), attributeSet);
    }

    @Override // androidx.appcompat.widget.AbstractC0217a
    public /* bridge */ /* synthetic */ int getAnimatedVisibility() {
        return super.getAnimatedVisibility();
    }

    @Override // androidx.appcompat.widget.AbstractC0217a
    public /* bridge */ /* synthetic */ int getContentHeight() {
        return super.getContentHeight();
    }

    public CharSequence getSubtitle() {
        return this.f659k;
    }

    public CharSequence getTitle() {
        return this.f658j;
    }

    /* renamed from: h */
    public void m581h(AbstractC4796b abstractC4796b) {
        View view = this.f660l;
        if (view == null) {
            View viewInflate = LayoutInflater.from(getContext()).inflate(this.f668t, (ViewGroup) this, false);
            this.f660l = viewInflate;
            addView(viewInflate);
        } else if (view.getParent() == null) {
            addView(this.f660l);
        }
        this.f660l.findViewById(C0565f.action_mode_close_button).setOnClickListener(new ViewOnClickListenerC0150a(abstractC4796b));
        C0137g c0137g = (C0137g) abstractC4796b.mo440c();
        ActionMenuPresenter actionMenuPresenter = this.f1022e;
        if (actionMenuPresenter != null) {
            actionMenuPresenter.m621r();
        }
        ActionMenuPresenter actionMenuPresenter2 = new ActionMenuPresenter(getContext());
        this.f1022e = actionMenuPresenter2;
        actionMenuPresenter2.m618C(true);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(-2, -1);
        c0137g.addMenuPresenter(this.f1022e, this.f1020c);
        ActionMenuView actionMenuView = (ActionMenuView) this.f1022e.mo480h(this);
        this.f1021d = actionMenuView;
        C4647u.m18534b0(actionMenuView, null);
        addView(this.f1021d, layoutParams);
    }

    /* renamed from: i */
    public final void m582i() {
        if (this.f662n == null) {
            LayoutInflater.from(getContext()).inflate(C0566g.abc_action_bar_title_item, this);
            LinearLayout linearLayout = (LinearLayout) getChildAt(getChildCount() - 1);
            this.f662n = linearLayout;
            this.f663o = (TextView) linearLayout.findViewById(C0565f.action_bar_title);
            this.f664p = (TextView) this.f662n.findViewById(C0565f.action_bar_subtitle);
            if (this.f665q != 0) {
                this.f663o.setTextAppearance(getContext(), this.f665q);
            }
            if (this.f666r != 0) {
                this.f664p.setTextAppearance(getContext(), this.f666r);
            }
        }
        this.f663o.setText(this.f658j);
        this.f664p.setText(this.f659k);
        boolean z8 = !TextUtils.isEmpty(this.f658j);
        boolean z9 = !TextUtils.isEmpty(this.f659k);
        int i9 = 0;
        this.f664p.setVisibility(z9 ? 0 : 8);
        LinearLayout linearLayout2 = this.f662n;
        if (!z8 && !z9) {
            i9 = 8;
        }
        linearLayout2.setVisibility(i9);
        if (this.f662n.getParent() == null) {
            addView(this.f662n);
        }
    }

    /* renamed from: j */
    public boolean m583j() {
        return this.f667s;
    }

    /* renamed from: k */
    public void m584k() {
        removeAllViews();
        this.f661m = null;
        this.f1021d = null;
    }

    /* renamed from: l */
    public boolean m585l() {
        ActionMenuPresenter actionMenuPresenter = this.f1022e;
        if (actionMenuPresenter != null) {
            return actionMenuPresenter.m619D();
        }
        return false;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        ActionMenuPresenter actionMenuPresenter = this.f1022e;
        if (actionMenuPresenter != null) {
            actionMenuPresenter.m624u();
            this.f1022e.m625v();
        }
    }

    @Override // androidx.appcompat.widget.AbstractC0217a, android.view.View
    public /* bridge */ /* synthetic */ boolean onHoverEvent(MotionEvent motionEvent) {
        return super.onHoverEvent(motionEvent);
    }

    @Override // android.view.View
    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        if (accessibilityEvent.getEventType() != 32) {
            super.onInitializeAccessibilityEvent(accessibilityEvent);
            return;
        }
        accessibilityEvent.setSource(this);
        accessibilityEvent.setClassName(getClass().getName());
        accessibilityEvent.setPackageName(getContext().getPackageName());
        accessibilityEvent.setContentDescription(this.f658j);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean z8, int i9, int i10, int i11, int i12) {
        boolean zM1068b = C0258u0.m1068b(this);
        int paddingRight = zM1068b ? (i11 - i9) - getPaddingRight() : getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingTop2 = ((i12 - i10) - getPaddingTop()) - getPaddingBottom();
        View view = this.f660l;
        if (view != null && view.getVisibility() != 8) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.f660l.getLayoutParams();
            int i13 = zM1068b ? marginLayoutParams.rightMargin : marginLayoutParams.leftMargin;
            int i14 = zM1068b ? marginLayoutParams.leftMargin : marginLayoutParams.rightMargin;
            int iM784d = AbstractC0217a.m784d(paddingRight, i13, zM1068b);
            paddingRight = AbstractC0217a.m784d(iM784d + m786e(this.f660l, iM784d, paddingTop, paddingTop2, zM1068b), i14, zM1068b);
        }
        int iM786e = paddingRight;
        LinearLayout linearLayout = this.f662n;
        if (linearLayout != null && this.f661m == null && linearLayout.getVisibility() != 8) {
            iM786e += m786e(this.f662n, iM786e, paddingTop, paddingTop2, zM1068b);
        }
        int i15 = iM786e;
        View view2 = this.f661m;
        if (view2 != null) {
            m786e(view2, i15, paddingTop, paddingTop2, zM1068b);
        }
        int paddingLeft = zM1068b ? getPaddingLeft() : (i11 - i9) - getPaddingRight();
        ActionMenuView actionMenuView = this.f1021d;
        if (actionMenuView != null) {
            m786e(actionMenuView, paddingLeft, paddingTop, paddingTop2, !zM1068b);
        }
    }

    @Override // android.view.View
    public void onMeasure(int i9, int i10) {
        if (View.MeasureSpec.getMode(i9) != 1073741824) {
            throw new IllegalStateException(getClass().getSimpleName() + " can only be used with android:layout_width=\"match_parent\" (or fill_parent)");
        }
        if (View.MeasureSpec.getMode(i10) == 0) {
            throw new IllegalStateException(getClass().getSimpleName() + " can only be used with android:layout_height=\"wrap_content\"");
        }
        int size = View.MeasureSpec.getSize(i9);
        int size2 = this.f1023f;
        if (size2 <= 0) {
            size2 = View.MeasureSpec.getSize(i10);
        }
        int paddingTop = getPaddingTop() + getPaddingBottom();
        int paddingLeft = (size - getPaddingLeft()) - getPaddingRight();
        int iMin = size2 - paddingTop;
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(iMin, Integer.MIN_VALUE);
        View view = this.f660l;
        if (view != null) {
            int iM785c = m785c(view, paddingLeft, iMakeMeasureSpec, 0);
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.f660l.getLayoutParams();
            paddingLeft = iM785c - (marginLayoutParams.leftMargin + marginLayoutParams.rightMargin);
        }
        ActionMenuView actionMenuView = this.f1021d;
        if (actionMenuView != null && actionMenuView.getParent() == this) {
            paddingLeft = m785c(this.f1021d, paddingLeft, iMakeMeasureSpec, 0);
        }
        LinearLayout linearLayout = this.f662n;
        if (linearLayout != null && this.f661m == null) {
            if (this.f667s) {
                this.f662n.measure(View.MeasureSpec.makeMeasureSpec(0, 0), iMakeMeasureSpec);
                int measuredWidth = this.f662n.getMeasuredWidth();
                boolean z8 = measuredWidth <= paddingLeft;
                if (z8) {
                    paddingLeft -= measuredWidth;
                }
                this.f662n.setVisibility(z8 ? 0 : 8);
            } else {
                paddingLeft = m785c(linearLayout, paddingLeft, iMakeMeasureSpec, 0);
            }
        }
        View view2 = this.f661m;
        if (view2 != null) {
            ViewGroup.LayoutParams layoutParams = view2.getLayoutParams();
            int i11 = layoutParams.width;
            int i12 = i11 != -2 ? 1073741824 : Integer.MIN_VALUE;
            if (i11 >= 0) {
                paddingLeft = Math.min(i11, paddingLeft);
            }
            int i13 = layoutParams.height;
            int i14 = i13 == -2 ? Integer.MIN_VALUE : 1073741824;
            if (i13 >= 0) {
                iMin = Math.min(i13, iMin);
            }
            this.f661m.measure(View.MeasureSpec.makeMeasureSpec(paddingLeft, i12), View.MeasureSpec.makeMeasureSpec(iMin, i14));
        }
        if (this.f1023f > 0) {
            setMeasuredDimension(size, size2);
            return;
        }
        int childCount = getChildCount();
        int i15 = 0;
        for (int i16 = 0; i16 < childCount; i16++) {
            int measuredHeight = getChildAt(i16).getMeasuredHeight() + paddingTop;
            if (measuredHeight > i15) {
                i15 = measuredHeight;
            }
        }
        setMeasuredDimension(size, i15);
    }

    @Override // androidx.appcompat.widget.AbstractC0217a, android.view.View
    public /* bridge */ /* synthetic */ boolean onTouchEvent(MotionEvent motionEvent) {
        return super.onTouchEvent(motionEvent);
    }

    @Override // androidx.appcompat.widget.AbstractC0217a
    public void setContentHeight(int i9) {
        this.f1023f = i9;
    }

    public void setCustomView(View view) {
        LinearLayout linearLayout;
        View view2 = this.f661m;
        if (view2 != null) {
            removeView(view2);
        }
        this.f661m = view;
        if (view != null && (linearLayout = this.f662n) != null) {
            removeView(linearLayout);
            this.f662n = null;
        }
        if (view != null) {
            addView(view);
        }
        requestLayout();
    }

    public void setSubtitle(CharSequence charSequence) {
        this.f659k = charSequence;
        m582i();
    }

    public void setTitle(CharSequence charSequence) {
        this.f658j = charSequence;
        m582i();
    }

    public void setTitleOptional(boolean z8) {
        if (z8 != this.f667s) {
            requestLayout();
        }
        this.f667s = z8;
    }

    @Override // androidx.appcompat.widget.AbstractC0217a, android.view.View
    public /* bridge */ /* synthetic */ void setVisibility(int i9) {
        super.setVisibility(i9);
    }

    @Override // android.view.ViewGroup
    public boolean shouldDelayChildPressedState() {
        return false;
    }

    public ActionBarContextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, C0560a.actionModeStyle);
    }

    public ActionBarContextView(Context context, AttributeSet attributeSet, int i9) {
        super(context, attributeSet, i9);
        C0250q0 c0250q0M1004v = C0250q0.m1004v(context, attributeSet, C0569j.ActionMode, i9, 0);
        C4647u.m18534b0(this, c0250q0M1004v.m1011g(C0569j.ActionMode_background));
        this.f665q = c0250q0M1004v.m1018n(C0569j.ActionMode_titleTextStyle, 0);
        this.f666r = c0250q0M1004v.m1018n(C0569j.ActionMode_subtitleTextStyle, 0);
        this.f1023f = c0250q0M1004v.m1017m(C0569j.ActionMode_height, 0);
        this.f668t = c0250q0M1004v.m1018n(C0569j.ActionMode_closeItemLayout, C0566g.abc_action_mode_close_item_material);
        c0250q0M1004v.m1024w();
    }
}
