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
public class ChildProportionLayout extends ViewGroup {

    /* renamed from: b */
    public final ArrayList<View> f15078b;

    /* renamed from: c */
    public int f15079c;

    /* renamed from: d */
    public int f15080d;

    public ChildProportionLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    /* renamed from: a */
    public static int m17306a(int i9, int i10, int i11, int i12) {
        return i10 == -1 ? i9 : i11 == -1 ? i12 == -1 ? i9 : Math.round((i9 * (i10 - i12)) / i10) : Math.round((i9 * i11) / i10);
    }

    /* renamed from: b */
    public void m17307b(int i9, int i10) {
        int i11;
        View view;
        int i12;
        int i13;
        C3240a c3240a;
        int childCount = getChildCount();
        int i14 = 1073741824;
        boolean z8 = (View.MeasureSpec.getMode(i9) == 1073741824 && View.MeasureSpec.getMode(i10) == 1073741824) ? false : true;
        this.f15078b.clear();
        int size = View.MeasureSpec.getSize(i9);
        int size2 = View.MeasureSpec.getSize(i10);
        int i15 = getLayoutParams().width;
        int i16 = getLayoutParams().height;
        int i17 = 0;
        int i18 = 0;
        int i19 = 0;
        int i20 = 0;
        while (i19 < childCount) {
            View childAt = getChildAt(i19);
            if (childAt.getVisibility() != 8) {
                C3240a c3240a2 = (C3240a) childAt.getLayoutParams();
                if (i15 == -1 && i16 == -2) {
                    view = childAt;
                    i12 = i17;
                    i13 = i18;
                    measureChildWithMargins(childAt, View.MeasureSpec.makeMeasureSpec(m17306a(size, this.f15079c, c3240a2.f15083c, c3240a2.f15081a), i14), 0, 0, 0);
                    c3240a = c3240a2;
                } else {
                    view = childAt;
                    i12 = i17;
                    i13 = i18;
                    if (i15 == -2 && i16 == -1) {
                        measureChildWithMargins(view, 0, 0, View.MeasureSpec.makeMeasureSpec(m17306a(size2, this.f15080d, c3240a2.f15084d, c3240a2.f15082b), 1073741824), 0);
                        c3240a = c3240a2;
                    } else {
                        if (i15 == -1 && i16 == -1) {
                            i11 = 1073741824;
                            c3240a = c3240a2;
                            measureChildWithMargins(view, View.MeasureSpec.makeMeasureSpec(m17306a(size, this.f15079c, c3240a2.f15083c, c3240a2.f15081a), 1073741824), 0, View.MeasureSpec.makeMeasureSpec(m17306a(size2, this.f15080d, c3240a2.f15084d, c3240a2.f15082b), 1073741824), 0);
                        } else {
                            c3240a = c3240a2;
                            i11 = 1073741824;
                            if (i15 == -2 && i16 == -2) {
                                measureChildWithMargins(view, 0, 0, 0, 0);
                            } else {
                                measureChildWithMargins(view, i9, 0, i10, 0);
                            }
                        }
                        int iMax = Math.max(i20, view.getMeasuredWidth() + ((FrameLayout.LayoutParams) c3240a).leftMargin + ((FrameLayout.LayoutParams) c3240a).rightMargin);
                        int iMax2 = Math.max(i13, view.getMeasuredHeight() + ((FrameLayout.LayoutParams) c3240a).topMargin + ((FrameLayout.LayoutParams) c3240a).bottomMargin);
                        int iCombineMeasuredStates = View.combineMeasuredStates(i12, view.getMeasuredState());
                        if (z8 && (((FrameLayout.LayoutParams) c3240a).width == -1 || ((FrameLayout.LayoutParams) c3240a).height == -1)) {
                            this.f15078b.add(view);
                        }
                        i20 = iMax;
                        i18 = iMax2;
                        i17 = iCombineMeasuredStates;
                    }
                }
                i11 = 1073741824;
                int iMax3 = Math.max(i20, view.getMeasuredWidth() + ((FrameLayout.LayoutParams) c3240a).leftMargin + ((FrameLayout.LayoutParams) c3240a).rightMargin);
                int iMax22 = Math.max(i13, view.getMeasuredHeight() + ((FrameLayout.LayoutParams) c3240a).topMargin + ((FrameLayout.LayoutParams) c3240a).bottomMargin);
                int iCombineMeasuredStates2 = View.combineMeasuredStates(i12, view.getMeasuredState());
                if (z8) {
                    this.f15078b.add(view);
                }
                i20 = iMax3;
                i18 = iMax22;
                i17 = iCombineMeasuredStates2;
            } else {
                i11 = i14;
            }
            i19++;
            i14 = i11;
        }
        int i21 = i17;
        setMeasuredDimension(View.resolveSizeAndState(Math.max(i20 + getPaddingLeft() + getPaddingRight(), getSuggestedMinimumWidth()), i9, i21), View.resolveSizeAndState(Math.max(i18 + getPaddingTop() + getPaddingBottom(), getSuggestedMinimumHeight()), i10, i21 << 16));
    }

    @Override // android.view.ViewGroup
    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof C3240a;
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new C3240a(-1, -1, -1, -1, -1, -1, 51);
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new C3240a(getContext(), attributeSet);
    }

    @Override // android.view.ViewGroup
    public void measureChildren(int i9, int i10) {
        int size = (View.MeasureSpec.getSize(i9) - getPaddingLeft()) - getPaddingRight();
        int size2 = (View.MeasureSpec.getSize(i10) - getPaddingTop()) - getPaddingBottom();
        int childCount = getChildCount();
        for (int i11 = 0; i11 < childCount; i11++) {
            View childAt = getChildAt(i11);
            if (childAt.getVisibility() != 8) {
                C3240a c3240a = (C3240a) childAt.getLayoutParams();
                int iM17306a = m17306a(size, this.f15079c, c3240a.f15083c, c3240a.f15081a);
                int iM17306a2 = m17306a(size2, this.f15080d, c3240a.f15084d, c3240a.f15082b);
                int i12 = ((FrameLayout.LayoutParams) c3240a).width;
                int iMakeMeasureSpec = i12 == -1 ? View.MeasureSpec.makeMeasureSpec((iM17306a - ((FrameLayout.LayoutParams) c3240a).leftMargin) - ((FrameLayout.LayoutParams) c3240a).rightMargin, 1073741824) : ViewGroup.getChildMeasureSpec(i9, ((FrameLayout.LayoutParams) c3240a).leftMargin + ((FrameLayout.LayoutParams) c3240a).rightMargin, i12);
                int i13 = ((FrameLayout.LayoutParams) c3240a).height;
                childAt.measure(iMakeMeasureSpec, i13 == -1 ? View.MeasureSpec.makeMeasureSpec((iM17306a2 - ((FrameLayout.LayoutParams) c3240a).topMargin) - ((FrameLayout.LayoutParams) c3240a).bottomMargin, 1073741824) : ViewGroup.getChildMeasureSpec(i10, ((FrameLayout.LayoutParams) c3240a).topMargin + ((FrameLayout.LayoutParams) c3240a).bottomMargin, i13));
            }
        }
    }

    @Override // android.view.View
    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName(ChildProportionLayout.class.getName());
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName(ChildProportionLayout.class.getName());
    }

    /* JADX WARN: Removed duplicated region for block: B:38:0x00cb  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00e8  */
    @Override // android.view.ViewGroup, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void onLayout(boolean z8, int i9, int i10, int i11, int i12) {
        int i13;
        int i14;
        int i15;
        int i16;
        int i17;
        int i18;
        int i19;
        int i20;
        ChildProportionLayout childProportionLayout = this;
        int childCount = getChildCount();
        int paddingLeft = getPaddingLeft();
        int paddingRight = (i11 - i9) - getPaddingRight();
        int paddingTop = getPaddingTop();
        int paddingBottom = (i12 - i10) - getPaddingBottom();
        int i21 = paddingRight - paddingLeft;
        int i22 = paddingBottom - paddingTop;
        int i23 = 0;
        while (i23 < childCount) {
            View childAt = childProportionLayout.getChildAt(i23);
            if (childAt.getVisibility() != 8) {
                C3240a c3240a = (C3240a) childAt.getLayoutParams();
                int measuredWidth = childAt.getMeasuredWidth();
                int measuredHeight = childAt.getMeasuredHeight();
                int iRound = Math.round((i21 * c3240a.f15081a) / childProportionLayout.f15079c);
                int iRound2 = Math.round((i22 * c3240a.f15082b) / childProportionLayout.f15080d);
                int i24 = ((FrameLayout.LayoutParams) c3240a).gravity;
                if (i24 == -1) {
                    i24 = 51;
                }
                int absoluteGravity = Gravity.getAbsoluteGravity(i24, 0);
                int i25 = i24 & 112;
                i13 = childCount;
                if (c3240a.f15081a == -1) {
                    int i26 = absoluteGravity & 7;
                    if (i26 == 1) {
                        i15 = ((i21 - measuredWidth) / 2) + paddingLeft + ((FrameLayout.LayoutParams) c3240a).leftMargin;
                        i16 = ((FrameLayout.LayoutParams) c3240a).rightMargin;
                    } else if (i26 != 5) {
                        i17 = ((FrameLayout.LayoutParams) c3240a).leftMargin + paddingLeft;
                        if (c3240a.f15082b == -1) {
                            if (i25 == 16) {
                                i19 = ((i22 - measuredHeight) / 2) + paddingTop + ((FrameLayout.LayoutParams) c3240a).topMargin;
                                iRound2 = ((FrameLayout.LayoutParams) c3240a).bottomMargin;
                            } else if (i25 != 80) {
                                i20 = ((FrameLayout.LayoutParams) c3240a).topMargin + paddingTop;
                                childAt.layout(i17, i20, measuredWidth + i17, measuredHeight + i20);
                            } else {
                                i19 = paddingBottom - measuredHeight;
                                iRound2 = ((FrameLayout.LayoutParams) c3240a).bottomMargin;
                            }
                            i20 = i19 - iRound2;
                            childAt.layout(i17, i20, measuredWidth + i17, measuredHeight + i20);
                        } else {
                            if (i25 != 16) {
                                if (i25 == 80) {
                                    if (c3240a.f15086f) {
                                        i19 = (paddingBottom - measuredHeight) - ((FrameLayout.LayoutParams) c3240a).bottomMargin;
                                    } else {
                                        i19 = paddingBottom - measuredHeight;
                                        iRound2 = ((FrameLayout.LayoutParams) c3240a).bottomMargin;
                                    }
                                    i20 = i19 - iRound2;
                                } else if (c3240a.f15086f) {
                                    i20 = ((FrameLayout.LayoutParams) c3240a).topMargin;
                                } else {
                                    i18 = ((FrameLayout.LayoutParams) c3240a).topMargin;
                                    i20 = i18 + iRound2;
                                }
                            } else if (c3240a.f15086f) {
                                i19 = (((i22 - iRound2) - measuredHeight) / 2) + ((FrameLayout.LayoutParams) c3240a).topMargin;
                                iRound2 = ((FrameLayout.LayoutParams) c3240a).bottomMargin;
                                i20 = i19 - iRound2;
                            } else {
                                i18 = ((((i22 - iRound2) - measuredHeight) / 2) + ((FrameLayout.LayoutParams) c3240a).topMargin) - ((FrameLayout.LayoutParams) c3240a).bottomMargin;
                                i20 = i18 + iRound2;
                            }
                            childAt.layout(i17, i20, measuredWidth + i17, measuredHeight + i20);
                        }
                    } else {
                        i15 = paddingRight - measuredWidth;
                        i16 = ((FrameLayout.LayoutParams) c3240a).rightMargin;
                    }
                    i17 = i15 - i16;
                    if (c3240a.f15082b == -1) {
                    }
                } else {
                    int i27 = absoluteGravity & 7;
                    if (i27 != 1) {
                        if (i27 != 5) {
                            if (c3240a.f15085e) {
                                i17 = ((FrameLayout.LayoutParams) c3240a).leftMargin;
                            } else {
                                i14 = ((FrameLayout.LayoutParams) c3240a).leftMargin;
                                i17 = i14 + iRound;
                            }
                        } else if (c3240a.f15085e) {
                            i17 = ((paddingRight - measuredWidth) - ((FrameLayout.LayoutParams) c3240a).rightMargin) - iRound;
                        } else {
                            i15 = paddingRight - measuredWidth;
                            i16 = ((FrameLayout.LayoutParams) c3240a).rightMargin;
                            i17 = i15 - i16;
                        }
                    } else if (c3240a.f15085e) {
                        i15 = (((i21 - iRound) - measuredWidth) / 2) + ((FrameLayout.LayoutParams) c3240a).leftMargin;
                        i16 = ((FrameLayout.LayoutParams) c3240a).rightMargin;
                        i17 = i15 - i16;
                    } else {
                        i14 = ((((i21 - iRound) - measuredWidth) / 2) + ((FrameLayout.LayoutParams) c3240a).leftMargin) - ((FrameLayout.LayoutParams) c3240a).rightMargin;
                        i17 = i14 + iRound;
                    }
                    if (c3240a.f15082b == -1) {
                    }
                }
            } else {
                i13 = childCount;
            }
            i23++;
            childProportionLayout = this;
            childCount = i13;
        }
    }

    @Override // android.view.View
    public void onMeasure(int i9, int i10) {
        m17307b(i9, i10);
        measureChildren(getMeasuredWidth(), getMeasuredHeight());
    }

    @Override // android.view.ViewGroup
    public boolean shouldDelayChildPressedState() {
        return false;
    }

    public ChildProportionLayout(Context context, AttributeSet attributeSet, int i9) {
        super(context, attributeSet, i9);
        this.f15078b = new ArrayList<>(1);
        this.f15079c = -1;
        this.f15080d = -1;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C6453a0.FixedAspectRatioLayoutArgs);
        this.f15079c = typedArrayObtainStyledAttributes.getInteger(1, -1);
        this.f15080d = typedArrayObtainStyledAttributes.getInteger(0, -1);
        typedArrayObtainStyledAttributes.recycle();
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return new C3240a(layoutParams);
    }

    /* renamed from: com.cyberlink.you.widgetpool.common.ChildProportionLayout$a */
    public static class C3240a extends FrameLayout.LayoutParams {

        /* renamed from: a */
        public int f15081a;

        /* renamed from: b */
        public int f15082b;

        /* renamed from: c */
        public int f15083c;

        /* renamed from: d */
        public int f15084d;

        /* renamed from: e */
        public boolean f15085e;

        /* renamed from: f */
        public boolean f15086f;

        public C3240a(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C6453a0.ChildPosAndSizeLayoutArgs);
            this.f15081a = typedArrayObtainStyledAttributes.getInteger(1, -1);
            this.f15082b = typedArrayObtainStyledAttributes.getInteger(2, -1);
            this.f15083c = typedArrayObtainStyledAttributes.getInteger(3, -1);
            this.f15084d = typedArrayObtainStyledAttributes.getInteger(0, -1);
            typedArrayObtainStyledAttributes.recycle();
            TypedArray typedArrayObtainStyledAttributes2 = context.obtainStyledAttributes(attributeSet, C6453a0.ChildProportionLayoutArgs);
            this.f15085e = typedArrayObtainStyledAttributes2.getBoolean(1, false);
            this.f15086f = typedArrayObtainStyledAttributes2.getBoolean(0, false);
            typedArrayObtainStyledAttributes2.recycle();
        }

        public C3240a(int i9, int i10, int i11, int i12, int i13, int i14, int i15) {
            this(i9, i10, i11, i12, false, false, i13, i14, i15);
        }

        public C3240a(int i9, int i10, int i11, int i12, boolean z8, boolean z9, int i13, int i14, int i15) {
            super(i13, i14, i15);
            this.f15081a = i9;
            this.f15082b = i10;
            this.f15083c = i11;
            this.f15084d = i12;
            this.f15085e = z8;
            this.f15086f = z9;
        }

        public C3240a(ViewGroup.LayoutParams layoutParams) {
            this(-1, -1, -1, -1, layoutParams.width, layoutParams.height, 51);
        }
    }
}
