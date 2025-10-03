package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import p010b.C0565f;
import p010b.C0569j;
import p042d0.C4647u;

/* loaded from: classes.dex */
public class ActionBarContainer extends FrameLayout {

    /* renamed from: b */
    public boolean f648b;

    /* renamed from: c */
    public View f649c;

    /* renamed from: d */
    public View f650d;

    /* renamed from: e */
    public View f651e;

    /* renamed from: f */
    public Drawable f652f;

    /* renamed from: g */
    public Drawable f653g;

    /* renamed from: h */
    public Drawable f654h;

    /* renamed from: i */
    public boolean f655i;

    /* renamed from: j */
    public boolean f656j;

    /* renamed from: k */
    public int f657k;

    public ActionBarContainer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        C4647u.m18534b0(this, new C0219b(this));
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C0569j.ActionBar);
        this.f652f = typedArrayObtainStyledAttributes.getDrawable(C0569j.ActionBar_background);
        this.f653g = typedArrayObtainStyledAttributes.getDrawable(C0569j.ActionBar_backgroundStacked);
        this.f657k = typedArrayObtainStyledAttributes.getDimensionPixelSize(C0569j.ActionBar_height, -1);
        boolean z8 = true;
        if (getId() == C0565f.split_action_bar) {
            this.f655i = true;
            this.f654h = typedArrayObtainStyledAttributes.getDrawable(C0569j.ActionBar_backgroundSplit);
        }
        typedArrayObtainStyledAttributes.recycle();
        if (!this.f655i ? this.f652f != null || this.f653g != null : this.f654h != null) {
            z8 = false;
        }
        setWillNotDraw(z8);
    }

    /* renamed from: a */
    public final int m577a(View view) {
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) view.getLayoutParams();
        return view.getMeasuredHeight() + layoutParams.topMargin + layoutParams.bottomMargin;
    }

    /* renamed from: b */
    public final boolean m578b(View view) {
        return view == null || view.getVisibility() == 8 || view.getMeasuredHeight() == 0;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        Drawable drawable = this.f652f;
        if (drawable != null && drawable.isStateful()) {
            this.f652f.setState(getDrawableState());
        }
        Drawable drawable2 = this.f653g;
        if (drawable2 != null && drawable2.isStateful()) {
            this.f653g.setState(getDrawableState());
        }
        Drawable drawable3 = this.f654h;
        if (drawable3 == null || !drawable3.isStateful()) {
            return;
        }
        this.f654h.setState(getDrawableState());
    }

    public View getTabContainer() {
        return this.f649c;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
        Drawable drawable = this.f652f;
        if (drawable != null) {
            drawable.jumpToCurrentState();
        }
        Drawable drawable2 = this.f653g;
        if (drawable2 != null) {
            drawable2.jumpToCurrentState();
        }
        Drawable drawable3 = this.f654h;
        if (drawable3 != null) {
            drawable3.jumpToCurrentState();
        }
    }

    @Override // android.view.View
    public void onFinishInflate() {
        super.onFinishInflate();
        this.f650d = findViewById(C0565f.action_bar);
        this.f651e = findViewById(C0565f.action_context_bar);
    }

    @Override // android.view.View
    public boolean onHoverEvent(MotionEvent motionEvent) {
        super.onHoverEvent(motionEvent);
        return true;
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return this.f648b || super.onInterceptTouchEvent(motionEvent);
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0048 A[PHI: r0
      0x0048: PHI (r0v8 boolean) = (r0v1 boolean), (r0v1 boolean), (r0v0 boolean) binds: [B:31:0x00a5, B:33:0x00a9, B:15:0x0039] A[DONT_GENERATE, DONT_INLINE]] */
    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void onLayout(boolean z8, int i9, int i10, int i11, int i12) {
        Drawable drawable;
        super.onLayout(z8, i9, i10, i11, i12);
        View view = this.f649c;
        boolean z9 = true;
        boolean z10 = false;
        boolean z11 = (view == null || view.getVisibility() == 8) ? false : true;
        if (view != null && view.getVisibility() != 8) {
            int measuredHeight = getMeasuredHeight();
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) view.getLayoutParams();
            int measuredHeight2 = measuredHeight - view.getMeasuredHeight();
            int i13 = layoutParams.bottomMargin;
            view.layout(i9, measuredHeight2 - i13, i11, measuredHeight - i13);
        }
        if (this.f655i) {
            Drawable drawable2 = this.f654h;
            if (drawable2 != null) {
                drawable2.setBounds(0, 0, getMeasuredWidth(), getMeasuredHeight());
            } else {
                z9 = z10;
            }
        } else {
            if (this.f652f != null) {
                if (this.f650d.getVisibility() == 0) {
                    this.f652f.setBounds(this.f650d.getLeft(), this.f650d.getTop(), this.f650d.getRight(), this.f650d.getBottom());
                } else {
                    View view2 = this.f651e;
                    if (view2 == null || view2.getVisibility() != 0) {
                        this.f652f.setBounds(0, 0, 0, 0);
                    } else {
                        this.f652f.setBounds(this.f651e.getLeft(), this.f651e.getTop(), this.f651e.getRight(), this.f651e.getBottom());
                    }
                }
                z10 = true;
            }
            this.f656j = z11;
            if (z11 && (drawable = this.f653g) != null) {
                drawable.setBounds(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
            }
        }
        if (z9) {
            invalidate();
        }
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int i9, int i10) {
        int i11;
        if (this.f650d == null && View.MeasureSpec.getMode(i10) == Integer.MIN_VALUE && (i11 = this.f657k) >= 0) {
            i10 = View.MeasureSpec.makeMeasureSpec(Math.min(i11, View.MeasureSpec.getSize(i10)), Integer.MIN_VALUE);
        }
        super.onMeasure(i9, i10);
        if (this.f650d == null) {
            return;
        }
        int mode = View.MeasureSpec.getMode(i10);
        View view = this.f649c;
        if (view == null || view.getVisibility() == 8 || mode == 1073741824) {
            return;
        }
        setMeasuredDimension(getMeasuredWidth(), Math.min((!m578b(this.f650d) ? m577a(this.f650d) : !m578b(this.f651e) ? m577a(this.f651e) : 0) + m577a(this.f649c), mode == Integer.MIN_VALUE ? View.MeasureSpec.getSize(i10) : Integer.MAX_VALUE));
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        super.onTouchEvent(motionEvent);
        return true;
    }

    public void setPrimaryBackground(Drawable drawable) {
        Drawable drawable2 = this.f652f;
        if (drawable2 != null) {
            drawable2.setCallback(null);
            unscheduleDrawable(this.f652f);
        }
        this.f652f = drawable;
        if (drawable != null) {
            drawable.setCallback(this);
            View view = this.f650d;
            if (view != null) {
                this.f652f.setBounds(view.getLeft(), this.f650d.getTop(), this.f650d.getRight(), this.f650d.getBottom());
            }
        }
        boolean z8 = true;
        if (!this.f655i ? this.f652f != null || this.f653g != null : this.f654h != null) {
            z8 = false;
        }
        setWillNotDraw(z8);
        invalidate();
        invalidateOutline();
    }

    public void setSplitBackground(Drawable drawable) {
        Drawable drawable2;
        Drawable drawable3 = this.f654h;
        if (drawable3 != null) {
            drawable3.setCallback(null);
            unscheduleDrawable(this.f654h);
        }
        this.f654h = drawable;
        boolean z8 = false;
        if (drawable != null) {
            drawable.setCallback(this);
            if (this.f655i && (drawable2 = this.f654h) != null) {
                drawable2.setBounds(0, 0, getMeasuredWidth(), getMeasuredHeight());
            }
        }
        if (!this.f655i ? !(this.f652f != null || this.f653g != null) : this.f654h == null) {
            z8 = true;
        }
        setWillNotDraw(z8);
        invalidate();
        invalidateOutline();
    }

    public void setStackedBackground(Drawable drawable) {
        Drawable drawable2;
        Drawable drawable3 = this.f653g;
        if (drawable3 != null) {
            drawable3.setCallback(null);
            unscheduleDrawable(this.f653g);
        }
        this.f653g = drawable;
        if (drawable != null) {
            drawable.setCallback(this);
            if (this.f656j && (drawable2 = this.f653g) != null) {
                drawable2.setBounds(this.f649c.getLeft(), this.f649c.getTop(), this.f649c.getRight(), this.f649c.getBottom());
            }
        }
        boolean z8 = true;
        if (!this.f655i ? this.f652f != null || this.f653g != null : this.f654h != null) {
            z8 = false;
        }
        setWillNotDraw(z8);
        invalidate();
        invalidateOutline();
    }

    public void setTabContainer(C0236j0 c0236j0) {
        View view = this.f649c;
        if (view != null) {
            removeView(view);
        }
        this.f649c = c0236j0;
        if (c0236j0 != null) {
            addView(c0236j0);
            ViewGroup.LayoutParams layoutParams = c0236j0.getLayoutParams();
            layoutParams.width = -1;
            layoutParams.height = -2;
            c0236j0.setAllowCollapse(false);
        }
    }

    public void setTransitioning(boolean z8) {
        this.f648b = z8;
        setDescendantFocusability(z8 ? 393216 : 262144);
    }

    @Override // android.view.View
    public void setVisibility(int i9) {
        super.setVisibility(i9);
        boolean z8 = i9 == 0;
        Drawable drawable = this.f652f;
        if (drawable != null) {
            drawable.setVisible(z8, false);
        }
        Drawable drawable2 = this.f653g;
        if (drawable2 != null) {
            drawable2.setVisible(z8, false);
        }
        Drawable drawable3 = this.f654h;
        if (drawable3 != null) {
            drawable3.setVisible(z8, false);
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public ActionMode startActionModeForChild(View view, ActionMode.Callback callback) {
        return null;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public ActionMode startActionModeForChild(View view, ActionMode.Callback callback, int i9) {
        if (i9 != 0) {
            return super.startActionModeForChild(view, callback, i9);
        }
        return null;
    }

    @Override // android.view.View
    public boolean verifyDrawable(Drawable drawable) {
        return (drawable == this.f652f && !this.f655i) || (drawable == this.f653g && this.f656j) || ((drawable == this.f654h && this.f655i) || super.verifyDrawable(drawable));
    }
}
