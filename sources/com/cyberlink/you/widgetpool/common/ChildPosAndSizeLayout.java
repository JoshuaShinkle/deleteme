package com.cyberlink.you.widgetpool.common;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.FrameLayout;
import java.util.ArrayList;
import p218v2.C6453a0;

/* loaded from: classes.dex */
public class ChildPosAndSizeLayout extends ViewGroup {

    /* renamed from: b */
    public final ArrayList<View> f15071b;

    /* renamed from: c */
    public int f15072c;

    /* renamed from: d */
    public int f15073d;

    /* renamed from: e */
    public int f15074e;

    /* renamed from: f */
    public int f15075f;

    /* renamed from: g */
    public int f15076g;

    /* renamed from: h */
    public int f15077h;

    /* renamed from: com.cyberlink.you.widgetpool.common.ChildPosAndSizeLayout$a */
    public static class C3239a extends FrameLayout.LayoutParams {
        public C3239a(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public C3239a(int i9, int i10) {
            super(i9, i10);
        }

        public C3239a(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
        }
    }

    public ChildPosAndSizeLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    /* renamed from: b */
    public static int m17301b(int i9, int i10, int i11, int i12) {
        return i10 == -1 ? i9 : i11 == -1 ? i12 == -1 ? i9 : Math.round((i9 * (i10 - i12)) / i10) : Math.round((i9 * i11) / i10);
    }

    @Override // android.view.ViewGroup
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public C3239a generateDefaultLayoutParams() {
        return new C3239a(-1, -1);
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x00b5  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00d8  */
    /* renamed from: c */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void m17303c(int i9, int i10, int i11, int i12) {
        int i13;
        int i14;
        int i15;
        int i16;
        int i17;
        int i18;
        int i19;
        int i20;
        ChildPosAndSizeLayout childPosAndSizeLayout = this;
        int childCount = getChildCount();
        int paddingLeft = getPaddingLeft();
        int paddingRight = (i11 - i9) - getPaddingRight();
        int paddingTop = getPaddingTop();
        int paddingBottom = (i12 - i10) - getPaddingBottom();
        int i21 = paddingRight - paddingLeft;
        int i22 = paddingBottom - paddingTop;
        int iM17301b = m17301b(i21, childPosAndSizeLayout.f15072c, childPosAndSizeLayout.f15074e, childPosAndSizeLayout.f15076g);
        int iM17301b2 = m17301b(i22, childPosAndSizeLayout.f15073d, childPosAndSizeLayout.f15075f, childPosAndSizeLayout.f15077h);
        int i23 = (childPosAndSizeLayout.f15076g * i21) / childPosAndSizeLayout.f15072c;
        int i24 = (childPosAndSizeLayout.f15077h * i22) / childPosAndSizeLayout.f15073d;
        int i25 = 0;
        while (i25 < childCount) {
            View childAt = childPosAndSizeLayout.getChildAt(i25);
            if (childAt.getVisibility() != 8) {
                C3239a c3239a = (C3239a) childAt.getLayoutParams();
                int measuredWidth = childAt.getMeasuredWidth();
                int measuredHeight = childAt.getMeasuredHeight();
                i13 = childCount;
                int i26 = ((FrameLayout.LayoutParams) c3239a).gravity;
                i14 = i25;
                if (i26 == -1) {
                    i26 = 8388659;
                }
                int absoluteGravity = Gravity.getAbsoluteGravity(i26, 0);
                int i27 = i26 & 112;
                if (childPosAndSizeLayout.f15076g == -1) {
                    int i28 = absoluteGravity & 7;
                    if (i28 == 1) {
                        i15 = ((i21 - measuredWidth) / 2) + paddingLeft + ((FrameLayout.LayoutParams) c3239a).leftMargin;
                        i16 = ((FrameLayout.LayoutParams) c3239a).rightMargin;
                    } else if (i28 != 5) {
                        i17 = ((FrameLayout.LayoutParams) c3239a).leftMargin + paddingLeft;
                        if (childPosAndSizeLayout.f15077h != -1) {
                            if (i27 == 16) {
                                i18 = ((i22 - measuredHeight) / 2) + paddingTop + ((FrameLayout.LayoutParams) c3239a).topMargin;
                                i19 = ((FrameLayout.LayoutParams) c3239a).bottomMargin;
                            } else if (i27 == 48 || i27 != 80) {
                                int i29 = ((FrameLayout.LayoutParams) c3239a).topMargin;
                                i20 = i29 + paddingTop;
                                childAt.layout(i17, i20, measuredWidth + i17, i20 + measuredHeight);
                            } else {
                                i18 = paddingBottom - measuredHeight;
                                i19 = ((FrameLayout.LayoutParams) c3239a).bottomMargin;
                            }
                            i20 = i18 - i19;
                            childAt.layout(i17, i20, measuredWidth + i17, i20 + measuredHeight);
                        } else {
                            if (i27 == 16) {
                                i18 = ((iM17301b2 - measuredHeight) / 2) + i24 + ((FrameLayout.LayoutParams) c3239a).topMargin;
                                i19 = ((FrameLayout.LayoutParams) c3239a).bottomMargin;
                            } else if (i27 == 48 || i27 != 80) {
                                int i30 = ((FrameLayout.LayoutParams) c3239a).topMargin;
                                i20 = i30 + i24;
                                childAt.layout(i17, i20, measuredWidth + i17, i20 + measuredHeight);
                            } else {
                                i18 = (i24 + iM17301b2) - measuredHeight;
                                i19 = ((FrameLayout.LayoutParams) c3239a).bottomMargin;
                            }
                            i20 = i18 - i19;
                            childAt.layout(i17, i20, measuredWidth + i17, i20 + measuredHeight);
                        }
                    } else {
                        i15 = paddingRight - measuredWidth;
                        i16 = ((FrameLayout.LayoutParams) c3239a).rightMargin;
                    }
                    i17 = i15 - i16;
                    if (childPosAndSizeLayout.f15077h != -1) {
                    }
                } else {
                    int i31 = absoluteGravity & 7;
                    if (i31 == 1) {
                        i15 = ((iM17301b - measuredWidth) / 2) + i23 + ((FrameLayout.LayoutParams) c3239a).leftMargin;
                        i16 = ((FrameLayout.LayoutParams) c3239a).rightMargin;
                    } else if (i31 != 5) {
                        i17 = ((FrameLayout.LayoutParams) c3239a).leftMargin + i23;
                        if (childPosAndSizeLayout.f15077h != -1) {
                        }
                    } else {
                        i15 = (i23 + iM17301b) - measuredWidth;
                        i16 = ((FrameLayout.LayoutParams) c3239a).rightMargin;
                    }
                    i17 = i15 - i16;
                    if (childPosAndSizeLayout.f15077h != -1) {
                    }
                }
            } else {
                i13 = childCount;
                i14 = i25;
            }
            i25 = i14 + 1;
            childPosAndSizeLayout = this;
            childCount = i13;
        }
    }

    @Override // android.view.ViewGroup
    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof C3239a;
    }

    /* renamed from: d */
    public final void m17304d() {
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        int paddingLeft = (measuredWidth - getPaddingLeft()) - getPaddingRight();
        int paddingTop = (measuredHeight - getPaddingTop()) - getPaddingBottom();
        int iM17301b = m17301b(paddingLeft, this.f15072c, this.f15074e, this.f15076g);
        int iM17301b2 = m17301b(paddingTop, this.f15073d, this.f15075f, this.f15077h);
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        measureChildren(layoutParams.width == -1 ? View.MeasureSpec.makeMeasureSpec(iM17301b, 1073741824) : View.MeasureSpec.makeMeasureSpec(iM17301b, 0), layoutParams.height == -1 ? View.MeasureSpec.makeMeasureSpec(iM17301b2, 1073741824) : View.MeasureSpec.makeMeasureSpec(iM17301b2, 0));
    }

    /* renamed from: e */
    public void m17305e(int i9, int i10) {
        int i11;
        int i12;
        int i13;
        int i14;
        int i15;
        int i16;
        int i17;
        int i18;
        int childCount = getChildCount();
        boolean z8 = (View.MeasureSpec.getMode(i9) == 1073741824 && View.MeasureSpec.getMode(i10) == 1073741824) ? false : true;
        this.f15071b.clear();
        int size = View.MeasureSpec.getSize(i9);
        int size2 = View.MeasureSpec.getSize(i10);
        int i19 = getLayoutParams().width;
        int i20 = getLayoutParams().height;
        int i21 = 0;
        int iMax = 0;
        int iMax2 = 0;
        int i22 = 0;
        while (i22 < childCount) {
            View childAt = getChildAt(i22);
            if (childAt.getVisibility() != 8) {
                if (i19 == -1 && i20 == -2) {
                    i15 = i21;
                    i16 = iMax;
                    i17 = iMax2;
                    i11 = i22;
                    i13 = childCount;
                    i14 = i19;
                    measureChildWithMargins(childAt, View.MeasureSpec.makeMeasureSpec(m17301b(size, this.f15072c, this.f15074e, this.f15076g), 1073741824), 0, 0, 0);
                    i12 = i20;
                } else {
                    i15 = i21;
                    i16 = iMax;
                    i17 = iMax2;
                    i11 = i22;
                    i13 = childCount;
                    i14 = i19;
                    int i23 = i20;
                    if (i14 == -2) {
                        i18 = -1;
                        if (i23 == -1) {
                            i12 = i23;
                            measureChildWithMargins(childAt, 0, 0, View.MeasureSpec.makeMeasureSpec(m17301b(size2, this.f15073d, this.f15075f, this.f15077h), 1073741824), 0);
                        } else {
                            i12 = i23;
                        }
                    } else {
                        i12 = i23;
                        i18 = -1;
                    }
                    if (i14 == i18 && i12 == i18) {
                        measureChildWithMargins(childAt, View.MeasureSpec.makeMeasureSpec(m17301b(size, this.f15072c, this.f15074e, this.f15076g), 1073741824), 0, View.MeasureSpec.makeMeasureSpec(m17301b(size2, this.f15073d, this.f15075f, this.f15077h), 1073741824), 0);
                    } else if (i14 == -2 && i12 == -2) {
                        measureChildWithMargins(childAt, 0, 0, 0, 0);
                    } else {
                        measureChildWithMargins(childAt, i9, 0, i10, 0);
                    }
                }
                C3239a c3239a = (C3239a) childAt.getLayoutParams();
                iMax2 = Math.max(i17, childAt.getMeasuredWidth() + ((FrameLayout.LayoutParams) c3239a).leftMargin + ((FrameLayout.LayoutParams) c3239a).rightMargin);
                iMax = Math.max(i16, childAt.getMeasuredHeight() + ((FrameLayout.LayoutParams) c3239a).topMargin + ((FrameLayout.LayoutParams) c3239a).bottomMargin);
                int iCombineMeasuredStates = View.combineMeasuredStates(i15, childAt.getMeasuredState());
                if (z8 && (((FrameLayout.LayoutParams) c3239a).width == -1 || ((FrameLayout.LayoutParams) c3239a).height == -1)) {
                    this.f15071b.add(childAt);
                }
                i21 = iCombineMeasuredStates;
            } else {
                i11 = i22;
                i12 = i20;
                i13 = childCount;
                i14 = i19;
            }
            i22 = i11 + 1;
            i20 = i12;
            i19 = i14;
            childCount = i13;
        }
        int i24 = i21;
        setMeasuredDimension(View.resolveSizeAndState(Math.max(iMax2 + getPaddingLeft() + getPaddingRight(), getSuggestedMinimumWidth()), i9, i24), View.resolveSizeAndState(Math.max(iMax + getPaddingTop() + getPaddingBottom(), getSuggestedMinimumHeight()), i10, i24 << 16));
        int size3 = this.f15071b.size();
        if (size3 > 1) {
            for (int i25 = 0; i25 < size3; i25++) {
                View view = this.f15071b.get(i25);
                ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
                int i26 = marginLayoutParams.width;
                int iMakeMeasureSpec = i26 == -1 ? View.MeasureSpec.makeMeasureSpec((getMeasuredWidth() - marginLayoutParams.leftMargin) - marginLayoutParams.rightMargin, 1073741824) : ViewGroup.getChildMeasureSpec(i9, marginLayoutParams.leftMargin + marginLayoutParams.rightMargin, i26);
                int i27 = marginLayoutParams.height;
                view.measure(iMakeMeasureSpec, i27 == -1 ? View.MeasureSpec.makeMeasureSpec((getMeasuredHeight() - marginLayoutParams.topMargin) - marginLayoutParams.bottomMargin, 1073741824) : ViewGroup.getChildMeasureSpec(i10, marginLayoutParams.topMargin + marginLayoutParams.bottomMargin, i27));
            }
        }
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new C3239a(getContext(), attributeSet);
    }

    @Override // android.view.ViewGroup
    public void measureChildren(int i9, int i10) {
        int size = View.MeasureSpec.getSize(i9);
        int size2 = View.MeasureSpec.getSize(i10);
        int childCount = getChildCount();
        for (int i11 = 0; i11 < childCount; i11++) {
            View childAt = getChildAt(i11);
            if (childAt.getVisibility() != 8) {
                ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) childAt.getLayoutParams();
                int i12 = marginLayoutParams.width;
                int iMakeMeasureSpec = i12 == -1 ? View.MeasureSpec.makeMeasureSpec((size - marginLayoutParams.leftMargin) - marginLayoutParams.rightMargin, 1073741824) : ViewGroup.getChildMeasureSpec(i9, marginLayoutParams.leftMargin + marginLayoutParams.rightMargin, i12);
                int i13 = marginLayoutParams.height;
                childAt.measure(iMakeMeasureSpec, i13 == -1 ? View.MeasureSpec.makeMeasureSpec((size2 - marginLayoutParams.topMargin) - marginLayoutParams.bottomMargin, 1073741824) : ViewGroup.getChildMeasureSpec(i10, marginLayoutParams.topMargin + marginLayoutParams.bottomMargin, i13));
            }
        }
    }

    @Override // android.view.View
    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName(ChildPosAndSizeLayout.class.getName());
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName(ChildPosAndSizeLayout.class.getName());
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean z8, int i9, int i10, int i11, int i12) {
        m17303c(i9, i10, i11, i12);
    }

    @Override // android.view.View
    public void onMeasure(int i9, int i10) {
        m17305e(i9, i10);
        if (this.f15072c == 0 || this.f15073d == 0) {
            return;
        }
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        if (measuredHeight == 0 && measuredWidth != 0) {
            setMeasuredDimension(measuredWidth, (int) Math.round(measuredWidth * (this.f15073d / this.f15072c)));
        } else if (measuredWidth == 0 && measuredHeight != 0) {
            setMeasuredDimension((int) Math.round(measuredHeight * (this.f15072c / this.f15073d)), measuredHeight);
        }
        m17304d();
    }

    @Override // android.view.ViewGroup
    public boolean shouldDelayChildPressedState() {
        return false;
    }

    public ChildPosAndSizeLayout(Context context, AttributeSet attributeSet, int i9) {
        super(context, attributeSet, i9);
        this.f15071b = new ArrayList<>(1);
        this.f15072c = -1;
        this.f15073d = -1;
        this.f15074e = -1;
        this.f15075f = -1;
        this.f15076g = -1;
        this.f15077h = -1;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C6453a0.FixedAspectRatioLayoutArgs);
        this.f15072c = typedArrayObtainStyledAttributes.getInteger(1, -1);
        this.f15073d = typedArrayObtainStyledAttributes.getInteger(0, -1);
        typedArrayObtainStyledAttributes.recycle();
        TypedArray typedArrayObtainStyledAttributes2 = context.obtainStyledAttributes(attributeSet, C6453a0.ChildPosAndSizeLayoutArgs);
        this.f15074e = typedArrayObtainStyledAttributes2.getInteger(3, -1);
        this.f15075f = typedArrayObtainStyledAttributes2.getInteger(0, -1);
        this.f15076g = typedArrayObtainStyledAttributes2.getInteger(1, -1);
        this.f15077h = typedArrayObtainStyledAttributes2.getInteger(2, -1);
        typedArrayObtainStyledAttributes2.recycle();
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return new C3239a(layoutParams);
    }
}
