package androidx.appcompat.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.LinearLayoutCompat;
import p010b.C0565f;
import p042d0.C4621e;
import p042d0.C4647u;

/* loaded from: classes.dex */
public class AlertDialogLayout extends LinearLayoutCompat {
    public AlertDialogLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    /* renamed from: b */
    public static int m660b(View view) {
        int iM18568t = C4647u.m18568t(view);
        if (iM18568t > 0) {
            return iM18568t;
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            if (viewGroup.getChildCount() == 1) {
                return m660b(viewGroup.getChildAt(0));
            }
        }
        return 0;
    }

    /* renamed from: c */
    public final boolean m661c(int i9, int i10) {
        int iCombineMeasuredStates;
        int iM660b;
        int measuredHeight;
        int measuredHeight2;
        int childCount = getChildCount();
        View view = null;
        View view2 = null;
        View view3 = null;
        for (int i11 = 0; i11 < childCount; i11++) {
            View childAt = getChildAt(i11);
            if (childAt.getVisibility() != 8) {
                int id = childAt.getId();
                if (id == C0565f.topPanel) {
                    view = childAt;
                } else if (id == C0565f.buttonPanel) {
                    view2 = childAt;
                } else {
                    if ((id != C0565f.contentPanel && id != C0565f.customPanel) || view3 != null) {
                        return false;
                    }
                    view3 = childAt;
                }
            }
        }
        int mode = View.MeasureSpec.getMode(i10);
        int size = View.MeasureSpec.getSize(i10);
        int mode2 = View.MeasureSpec.getMode(i9);
        int paddingTop = getPaddingTop() + getPaddingBottom();
        if (view != null) {
            view.measure(i9, 0);
            paddingTop += view.getMeasuredHeight();
            iCombineMeasuredStates = View.combineMeasuredStates(0, view.getMeasuredState());
        } else {
            iCombineMeasuredStates = 0;
        }
        if (view2 != null) {
            view2.measure(i9, 0);
            iM660b = m660b(view2);
            measuredHeight = view2.getMeasuredHeight() - iM660b;
            paddingTop += iM660b;
            iCombineMeasuredStates = View.combineMeasuredStates(iCombineMeasuredStates, view2.getMeasuredState());
        } else {
            iM660b = 0;
            measuredHeight = 0;
        }
        if (view3 != null) {
            view3.measure(i9, mode == 0 ? 0 : View.MeasureSpec.makeMeasureSpec(Math.max(0, size - paddingTop), mode));
            measuredHeight2 = view3.getMeasuredHeight();
            paddingTop += measuredHeight2;
            iCombineMeasuredStates = View.combineMeasuredStates(iCombineMeasuredStates, view3.getMeasuredState());
        } else {
            measuredHeight2 = 0;
        }
        int i12 = size - paddingTop;
        if (view2 != null) {
            int i13 = paddingTop - iM660b;
            int iMin = Math.min(i12, measuredHeight);
            if (iMin > 0) {
                i12 -= iMin;
                iM660b += iMin;
            }
            view2.measure(i9, View.MeasureSpec.makeMeasureSpec(iM660b, 1073741824));
            paddingTop = i13 + view2.getMeasuredHeight();
            iCombineMeasuredStates = View.combineMeasuredStates(iCombineMeasuredStates, view2.getMeasuredState());
        }
        if (view3 != null && i12 > 0) {
            view3.measure(i9, View.MeasureSpec.makeMeasureSpec(measuredHeight2 + i12, mode));
            paddingTop = (paddingTop - measuredHeight2) + view3.getMeasuredHeight();
            iCombineMeasuredStates = View.combineMeasuredStates(iCombineMeasuredStates, view3.getMeasuredState());
        }
        int iMax = 0;
        for (int i14 = 0; i14 < childCount; i14++) {
            View childAt2 = getChildAt(i14);
            if (childAt2.getVisibility() != 8) {
                iMax = Math.max(iMax, childAt2.getMeasuredWidth());
            }
        }
        setMeasuredDimension(View.resolveSizeAndState(iMax + getPaddingLeft() + getPaddingRight(), i9, iCombineMeasuredStates), View.resolveSizeAndState(paddingTop, i10, 0));
        if (mode2 == 1073741824) {
            return true;
        }
        forceUniformWidth(childCount, i10);
        return true;
    }

    public final void forceUniformWidth(int i9, int i10) {
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 1073741824);
        for (int i11 = 0; i11 < i9; i11++) {
            View childAt = getChildAt(i11);
            if (childAt.getVisibility() != 8) {
                LinearLayoutCompat.C0183a c0183a = (LinearLayoutCompat.C0183a) childAt.getLayoutParams();
                if (((ViewGroup.MarginLayoutParams) c0183a).width == -1) {
                    int i12 = ((ViewGroup.MarginLayoutParams) c0183a).height;
                    ((ViewGroup.MarginLayoutParams) c0183a).height = childAt.getMeasuredHeight();
                    measureChildWithMargins(childAt, iMakeMeasureSpec, 0, i10, 0);
                    ((ViewGroup.MarginLayoutParams) c0183a).height = i12;
                }
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x00a8  */
    @Override // androidx.appcompat.widget.LinearLayoutCompat, android.view.ViewGroup, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void onLayout(boolean z8, int i9, int i10, int i11, int i12) {
        int i13;
        int i14;
        int i15;
        int paddingLeft = getPaddingLeft();
        int i16 = i11 - i9;
        int paddingRight = i16 - getPaddingRight();
        int paddingRight2 = (i16 - paddingLeft) - getPaddingRight();
        int measuredHeight = getMeasuredHeight();
        int childCount = getChildCount();
        int gravity = getGravity();
        int i17 = gravity & 112;
        int i18 = gravity & 8388615;
        int paddingTop = i17 != 16 ? i17 != 80 ? getPaddingTop() : ((getPaddingTop() + i12) - i10) - measuredHeight : getPaddingTop() + (((i12 - i10) - measuredHeight) / 2);
        Drawable dividerDrawable = getDividerDrawable();
        int intrinsicHeight = dividerDrawable == null ? 0 : dividerDrawable.getIntrinsicHeight();
        for (int i19 = 0; i19 < childCount; i19++) {
            View childAt = getChildAt(i19);
            if (childAt != null && childAt.getVisibility() != 8) {
                int measuredWidth = childAt.getMeasuredWidth();
                int measuredHeight2 = childAt.getMeasuredHeight();
                LinearLayoutCompat.C0183a c0183a = (LinearLayoutCompat.C0183a) childAt.getLayoutParams();
                int i20 = c0183a.f850b;
                if (i20 < 0) {
                    i20 = i18;
                }
                int iM18420b = C4621e.m18420b(i20, C4647u.m18567s(this)) & 7;
                if (iM18420b == 1) {
                    i13 = ((paddingRight2 - measuredWidth) / 2) + paddingLeft + ((ViewGroup.MarginLayoutParams) c0183a).leftMargin;
                    i14 = ((ViewGroup.MarginLayoutParams) c0183a).rightMargin;
                } else if (iM18420b != 5) {
                    i15 = ((ViewGroup.MarginLayoutParams) c0183a).leftMargin + paddingLeft;
                    if (hasDividerBeforeChildAt(i19)) {
                        paddingTop += intrinsicHeight;
                    }
                    int i21 = paddingTop + ((ViewGroup.MarginLayoutParams) c0183a).topMargin;
                    setChildFrame(childAt, i15, i21, measuredWidth, measuredHeight2);
                    paddingTop = i21 + measuredHeight2 + ((ViewGroup.MarginLayoutParams) c0183a).bottomMargin;
                } else {
                    i13 = paddingRight - measuredWidth;
                    i14 = ((ViewGroup.MarginLayoutParams) c0183a).rightMargin;
                }
                i15 = i13 - i14;
                if (hasDividerBeforeChildAt(i19)) {
                }
                int i212 = paddingTop + ((ViewGroup.MarginLayoutParams) c0183a).topMargin;
                setChildFrame(childAt, i15, i212, measuredWidth, measuredHeight2);
                paddingTop = i212 + measuredHeight2 + ((ViewGroup.MarginLayoutParams) c0183a).bottomMargin;
            }
        }
    }

    @Override // androidx.appcompat.widget.LinearLayoutCompat, android.view.View
    public void onMeasure(int i9, int i10) {
        if (m661c(i9, i10)) {
            return;
        }
        super.onMeasure(i9, i10);
    }

    public final void setChildFrame(View view, int i9, int i10, int i11, int i12) {
        view.layout(i9, i10, i11 + i9, i12 + i10);
    }
}
