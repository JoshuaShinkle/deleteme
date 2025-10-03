package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import p010b.C0569j;
import p042d0.C4621e;
import p042d0.C4647u;

/* loaded from: classes.dex */
public class LinearLayoutCompat extends ViewGroup {
    private static final String ACCESSIBILITY_CLASS_NAME = "androidx.appcompat.widget.LinearLayoutCompat";
    public static final int HORIZONTAL = 0;
    private static final int INDEX_BOTTOM = 2;
    private static final int INDEX_CENTER_VERTICAL = 0;
    private static final int INDEX_FILL = 3;
    private static final int INDEX_TOP = 1;
    public static final int SHOW_DIVIDER_BEGINNING = 1;
    public static final int SHOW_DIVIDER_END = 4;
    public static final int SHOW_DIVIDER_MIDDLE = 2;
    public static final int SHOW_DIVIDER_NONE = 0;
    public static final int VERTICAL = 1;
    private static final int VERTICAL_GRAVITY_COUNT = 4;
    private boolean mBaselineAligned;
    private int mBaselineAlignedChildIndex;
    private int mBaselineChildTop;
    private Drawable mDivider;
    private int mDividerHeight;
    private int mDividerPadding;
    private int mDividerWidth;
    private int mGravity;
    private int[] mMaxAscent;
    private int[] mMaxDescent;
    private int mOrientation;
    private int mShowDividers;
    private int mTotalLength;
    private boolean mUseLargestChild;
    private float mWeightSum;

    public LinearLayoutCompat(Context context) {
        this(context, null);
    }

    private void forceUniformHeight(int i9, int i10) {
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), 1073741824);
        for (int i11 = 0; i11 < i9; i11++) {
            View virtualChildAt = getVirtualChildAt(i11);
            if (virtualChildAt.getVisibility() != 8) {
                C0183a c0183a = (C0183a) virtualChildAt.getLayoutParams();
                if (((ViewGroup.MarginLayoutParams) c0183a).height == -1) {
                    int i12 = ((ViewGroup.MarginLayoutParams) c0183a).width;
                    ((ViewGroup.MarginLayoutParams) c0183a).width = virtualChildAt.getMeasuredWidth();
                    measureChildWithMargins(virtualChildAt, i10, 0, iMakeMeasureSpec, 0);
                    ((ViewGroup.MarginLayoutParams) c0183a).width = i12;
                }
            }
        }
    }

    private void forceUniformWidth(int i9, int i10) {
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 1073741824);
        for (int i11 = 0; i11 < i9; i11++) {
            View virtualChildAt = getVirtualChildAt(i11);
            if (virtualChildAt.getVisibility() != 8) {
                C0183a c0183a = (C0183a) virtualChildAt.getLayoutParams();
                if (((ViewGroup.MarginLayoutParams) c0183a).width == -1) {
                    int i12 = ((ViewGroup.MarginLayoutParams) c0183a).height;
                    ((ViewGroup.MarginLayoutParams) c0183a).height = virtualChildAt.getMeasuredHeight();
                    measureChildWithMargins(virtualChildAt, iMakeMeasureSpec, 0, i10, 0);
                    ((ViewGroup.MarginLayoutParams) c0183a).height = i12;
                }
            }
        }
    }

    private void setChildFrame(View view, int i9, int i10, int i11, int i12) {
        view.layout(i9, i10, i11 + i9, i12 + i10);
    }

    @Override // android.view.ViewGroup
    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof C0183a;
    }

    public void drawDividersHorizontal(Canvas canvas) {
        int right;
        int left;
        int i9;
        int virtualChildCount = getVirtualChildCount();
        boolean zM1068b = C0258u0.m1068b(this);
        for (int i10 = 0; i10 < virtualChildCount; i10++) {
            View virtualChildAt = getVirtualChildAt(i10);
            if (virtualChildAt != null && virtualChildAt.getVisibility() != 8 && hasDividerBeforeChildAt(i10)) {
                C0183a c0183a = (C0183a) virtualChildAt.getLayoutParams();
                drawVerticalDivider(canvas, zM1068b ? virtualChildAt.getRight() + ((ViewGroup.MarginLayoutParams) c0183a).rightMargin : (virtualChildAt.getLeft() - ((ViewGroup.MarginLayoutParams) c0183a).leftMargin) - this.mDividerWidth);
            }
        }
        if (hasDividerBeforeChildAt(virtualChildCount)) {
            View virtualChildAt2 = getVirtualChildAt(virtualChildCount - 1);
            if (virtualChildAt2 != null) {
                C0183a c0183a2 = (C0183a) virtualChildAt2.getLayoutParams();
                if (zM1068b) {
                    left = virtualChildAt2.getLeft() - ((ViewGroup.MarginLayoutParams) c0183a2).leftMargin;
                    i9 = this.mDividerWidth;
                    right = left - i9;
                } else {
                    right = virtualChildAt2.getRight() + ((ViewGroup.MarginLayoutParams) c0183a2).rightMargin;
                }
            } else if (zM1068b) {
                right = getPaddingLeft();
            } else {
                left = getWidth() - getPaddingRight();
                i9 = this.mDividerWidth;
                right = left - i9;
            }
            drawVerticalDivider(canvas, right);
        }
    }

    public void drawDividersVertical(Canvas canvas) {
        int virtualChildCount = getVirtualChildCount();
        for (int i9 = 0; i9 < virtualChildCount; i9++) {
            View virtualChildAt = getVirtualChildAt(i9);
            if (virtualChildAt != null && virtualChildAt.getVisibility() != 8 && hasDividerBeforeChildAt(i9)) {
                drawHorizontalDivider(canvas, (virtualChildAt.getTop() - ((ViewGroup.MarginLayoutParams) ((C0183a) virtualChildAt.getLayoutParams())).topMargin) - this.mDividerHeight);
            }
        }
        if (hasDividerBeforeChildAt(virtualChildCount)) {
            View virtualChildAt2 = getVirtualChildAt(virtualChildCount - 1);
            drawHorizontalDivider(canvas, virtualChildAt2 == null ? (getHeight() - getPaddingBottom()) - this.mDividerHeight : virtualChildAt2.getBottom() + ((ViewGroup.MarginLayoutParams) ((C0183a) virtualChildAt2.getLayoutParams())).bottomMargin);
        }
    }

    public void drawHorizontalDivider(Canvas canvas, int i9) {
        this.mDivider.setBounds(getPaddingLeft() + this.mDividerPadding, i9, (getWidth() - getPaddingRight()) - this.mDividerPadding, this.mDividerHeight + i9);
        this.mDivider.draw(canvas);
    }

    public void drawVerticalDivider(Canvas canvas, int i9) {
        this.mDivider.setBounds(i9, getPaddingTop() + this.mDividerPadding, this.mDividerWidth + i9, (getHeight() - getPaddingBottom()) - this.mDividerPadding);
        this.mDivider.draw(canvas);
    }

    @Override // android.view.View
    public int getBaseline() {
        int i9;
        if (this.mBaselineAlignedChildIndex < 0) {
            return super.getBaseline();
        }
        int childCount = getChildCount();
        int i10 = this.mBaselineAlignedChildIndex;
        if (childCount <= i10) {
            throw new RuntimeException("mBaselineAlignedChildIndex of LinearLayout set to an index that is out of bounds.");
        }
        View childAt = getChildAt(i10);
        int baseline = childAt.getBaseline();
        if (baseline == -1) {
            if (this.mBaselineAlignedChildIndex == 0) {
                return -1;
            }
            throw new RuntimeException("mBaselineAlignedChildIndex of LinearLayout points to a View that doesn't know how to get its baseline.");
        }
        int bottom = this.mBaselineChildTop;
        if (this.mOrientation == 1 && (i9 = this.mGravity & 112) != 48) {
            if (i9 == 16) {
                bottom += ((((getBottom() - getTop()) - getPaddingTop()) - getPaddingBottom()) - this.mTotalLength) / 2;
            } else if (i9 == 80) {
                bottom = ((getBottom() - getTop()) - getPaddingBottom()) - this.mTotalLength;
            }
        }
        return bottom + ((ViewGroup.MarginLayoutParams) ((C0183a) childAt.getLayoutParams())).topMargin + baseline;
    }

    public int getBaselineAlignedChildIndex() {
        return this.mBaselineAlignedChildIndex;
    }

    public int getChildrenSkipCount(View view, int i9) {
        return 0;
    }

    public Drawable getDividerDrawable() {
        return this.mDivider;
    }

    public int getDividerPadding() {
        return this.mDividerPadding;
    }

    public int getDividerWidth() {
        return this.mDividerWidth;
    }

    public int getGravity() {
        return this.mGravity;
    }

    public int getLocationOffset(View view) {
        return 0;
    }

    public int getNextLocationOffset(View view) {
        return 0;
    }

    public int getOrientation() {
        return this.mOrientation;
    }

    public int getShowDividers() {
        return this.mShowDividers;
    }

    public View getVirtualChildAt(int i9) {
        return getChildAt(i9);
    }

    public int getVirtualChildCount() {
        return getChildCount();
    }

    public float getWeightSum() {
        return this.mWeightSum;
    }

    public boolean hasDividerBeforeChildAt(int i9) {
        if (i9 == 0) {
            return (this.mShowDividers & 1) != 0;
        }
        if (i9 == getChildCount()) {
            return (this.mShowDividers & 4) != 0;
        }
        if ((this.mShowDividers & 2) == 0) {
            return false;
        }
        for (int i10 = i9 - 1; i10 >= 0; i10--) {
            if (getChildAt(i10).getVisibility() != 8) {
                return true;
            }
        }
        return false;
    }

    public boolean isBaselineAligned() {
        return this.mBaselineAligned;
    }

    public boolean isMeasureWithLargestChildEnabled() {
        return this.mUseLargestChild;
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x00af  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00b8  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00eb  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x00ff  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void layoutHorizontal(int i9, int i10, int i11, int i12) {
        int i13;
        int i14;
        int i15;
        int i16;
        int i17;
        boolean z8;
        int i18;
        int i19;
        int measuredHeight;
        boolean zM1068b = C0258u0.m1068b(this);
        int paddingTop = getPaddingTop();
        int i20 = i12 - i10;
        int paddingBottom = i20 - getPaddingBottom();
        int paddingBottom2 = (i20 - paddingTop) - getPaddingBottom();
        int virtualChildCount = getVirtualChildCount();
        int i21 = this.mGravity;
        int i22 = i21 & 112;
        boolean z9 = this.mBaselineAligned;
        int[] iArr = this.mMaxAscent;
        int[] iArr2 = this.mMaxDescent;
        int iM18420b = C4621e.m18420b(8388615 & i21, C4647u.m18567s(this));
        boolean z10 = true;
        int paddingLeft = iM18420b != 1 ? iM18420b != 5 ? getPaddingLeft() : ((getPaddingLeft() + i11) - i9) - this.mTotalLength : getPaddingLeft() + (((i11 - i9) - this.mTotalLength) / 2);
        if (zM1068b) {
            i13 = virtualChildCount - 1;
            i14 = -1;
        } else {
            i13 = 0;
            i14 = 1;
        }
        int childrenSkipCount = 0;
        while (childrenSkipCount < virtualChildCount) {
            int i23 = i13 + (i14 * childrenSkipCount);
            View virtualChildAt = getVirtualChildAt(i23);
            if (virtualChildAt == null) {
                paddingLeft += measureNullChild(i23);
                z8 = z10;
                i15 = paddingTop;
                i16 = virtualChildCount;
                i17 = i22;
            } else if (virtualChildAt.getVisibility() != 8) {
                int measuredWidth = virtualChildAt.getMeasuredWidth();
                int measuredHeight2 = virtualChildAt.getMeasuredHeight();
                C0183a c0183a = (C0183a) virtualChildAt.getLayoutParams();
                int i24 = childrenSkipCount;
                if (z9) {
                    i16 = virtualChildCount;
                    int baseline = ((ViewGroup.MarginLayoutParams) c0183a).height != -1 ? virtualChildAt.getBaseline() : -1;
                    i18 = c0183a.f850b;
                    if (i18 < 0) {
                        i18 = i22;
                    }
                    i19 = i18 & 112;
                    i17 = i22;
                    if (i19 == 16) {
                        if (i19 == 48) {
                            measuredHeight = ((ViewGroup.MarginLayoutParams) c0183a).topMargin + paddingTop;
                            if (baseline != -1) {
                                z8 = true;
                                measuredHeight += iArr[1] - baseline;
                            }
                        } else if (i19 != 80) {
                            measuredHeight = paddingTop;
                        } else {
                            measuredHeight = (paddingBottom - measuredHeight2) - ((ViewGroup.MarginLayoutParams) c0183a).bottomMargin;
                            if (baseline != -1) {
                                measuredHeight -= iArr2[2] - (virtualChildAt.getMeasuredHeight() - baseline);
                            }
                        }
                        z8 = true;
                    } else {
                        z8 = true;
                        measuredHeight = ((((paddingBottom2 - measuredHeight2) / 2) + paddingTop) + ((ViewGroup.MarginLayoutParams) c0183a).topMargin) - ((ViewGroup.MarginLayoutParams) c0183a).bottomMargin;
                    }
                    if (hasDividerBeforeChildAt(i23)) {
                        paddingLeft += this.mDividerWidth;
                    }
                    int i25 = ((ViewGroup.MarginLayoutParams) c0183a).leftMargin + paddingLeft;
                    i15 = paddingTop;
                    setChildFrame(virtualChildAt, i25 + getLocationOffset(virtualChildAt), measuredHeight, measuredWidth, measuredHeight2);
                    int nextLocationOffset = i25 + measuredWidth + ((ViewGroup.MarginLayoutParams) c0183a).rightMargin + getNextLocationOffset(virtualChildAt);
                    childrenSkipCount = i24 + getChildrenSkipCount(virtualChildAt, i23);
                    paddingLeft = nextLocationOffset;
                    childrenSkipCount++;
                    virtualChildCount = i16;
                    i22 = i17;
                    z10 = z8;
                    paddingTop = i15;
                } else {
                    i16 = virtualChildCount;
                }
                i18 = c0183a.f850b;
                if (i18 < 0) {
                }
                i19 = i18 & 112;
                i17 = i22;
                if (i19 == 16) {
                }
                if (hasDividerBeforeChildAt(i23)) {
                }
                int i252 = ((ViewGroup.MarginLayoutParams) c0183a).leftMargin + paddingLeft;
                i15 = paddingTop;
                setChildFrame(virtualChildAt, i252 + getLocationOffset(virtualChildAt), measuredHeight, measuredWidth, measuredHeight2);
                int nextLocationOffset2 = i252 + measuredWidth + ((ViewGroup.MarginLayoutParams) c0183a).rightMargin + getNextLocationOffset(virtualChildAt);
                childrenSkipCount = i24 + getChildrenSkipCount(virtualChildAt, i23);
                paddingLeft = nextLocationOffset2;
                childrenSkipCount++;
                virtualChildCount = i16;
                i22 = i17;
                z10 = z8;
                paddingTop = i15;
            } else {
                i15 = paddingTop;
                i16 = virtualChildCount;
                i17 = i22;
                z8 = true;
            }
            childrenSkipCount++;
            virtualChildCount = i16;
            i22 = i17;
            z10 = z8;
            paddingTop = i15;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x009f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void layoutVertical(int i9, int i10, int i11, int i12) {
        int i13;
        int i14;
        int i15;
        int i16;
        int paddingLeft = getPaddingLeft();
        int i17 = i11 - i9;
        int paddingRight = i17 - getPaddingRight();
        int paddingRight2 = (i17 - paddingLeft) - getPaddingRight();
        int virtualChildCount = getVirtualChildCount();
        int i18 = this.mGravity;
        int i19 = i18 & 112;
        int i20 = i18 & 8388615;
        int paddingTop = i19 != 16 ? i19 != 80 ? getPaddingTop() : ((getPaddingTop() + i12) - i10) - this.mTotalLength : getPaddingTop() + (((i12 - i10) - this.mTotalLength) / 2);
        int childrenSkipCount = 0;
        while (childrenSkipCount < virtualChildCount) {
            View virtualChildAt = getVirtualChildAt(childrenSkipCount);
            if (virtualChildAt == null) {
                paddingTop += measureNullChild(childrenSkipCount);
            } else {
                if (virtualChildAt.getVisibility() != 8) {
                    int measuredWidth = virtualChildAt.getMeasuredWidth();
                    int measuredHeight = virtualChildAt.getMeasuredHeight();
                    C0183a c0183a = (C0183a) virtualChildAt.getLayoutParams();
                    int i21 = c0183a.f850b;
                    if (i21 < 0) {
                        i21 = i20;
                    }
                    int iM18420b = C4621e.m18420b(i21, C4647u.m18567s(this)) & 7;
                    if (iM18420b == 1) {
                        i13 = ((paddingRight2 - measuredWidth) / 2) + paddingLeft + ((ViewGroup.MarginLayoutParams) c0183a).leftMargin;
                        i14 = ((ViewGroup.MarginLayoutParams) c0183a).rightMargin;
                    } else if (iM18420b != 5) {
                        i15 = ((ViewGroup.MarginLayoutParams) c0183a).leftMargin + paddingLeft;
                        int i22 = i15;
                        if (hasDividerBeforeChildAt(childrenSkipCount)) {
                            paddingTop += this.mDividerHeight;
                        }
                        int i23 = paddingTop + ((ViewGroup.MarginLayoutParams) c0183a).topMargin;
                        setChildFrame(virtualChildAt, i22, i23 + getLocationOffset(virtualChildAt), measuredWidth, measuredHeight);
                        int nextLocationOffset = i23 + measuredHeight + ((ViewGroup.MarginLayoutParams) c0183a).bottomMargin + getNextLocationOffset(virtualChildAt);
                        childrenSkipCount += getChildrenSkipCount(virtualChildAt, childrenSkipCount);
                        paddingTop = nextLocationOffset;
                        i16 = 1;
                    } else {
                        i13 = paddingRight - measuredWidth;
                        i14 = ((ViewGroup.MarginLayoutParams) c0183a).rightMargin;
                    }
                    i15 = i13 - i14;
                    int i222 = i15;
                    if (hasDividerBeforeChildAt(childrenSkipCount)) {
                    }
                    int i232 = paddingTop + ((ViewGroup.MarginLayoutParams) c0183a).topMargin;
                    setChildFrame(virtualChildAt, i222, i232 + getLocationOffset(virtualChildAt), measuredWidth, measuredHeight);
                    int nextLocationOffset2 = i232 + measuredHeight + ((ViewGroup.MarginLayoutParams) c0183a).bottomMargin + getNextLocationOffset(virtualChildAt);
                    childrenSkipCount += getChildrenSkipCount(virtualChildAt, childrenSkipCount);
                    paddingTop = nextLocationOffset2;
                    i16 = 1;
                }
                childrenSkipCount += i16;
            }
            i16 = 1;
            childrenSkipCount += i16;
        }
    }

    public void measureChildBeforeLayout(View view, int i9, int i10, int i11, int i12, int i13) {
        measureChildWithMargins(view, i10, i11, i12, i13);
    }

    /* JADX WARN: Removed duplicated region for block: B:197:0x0452  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0177  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x0199  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x01c5  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x01cd  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x01d8  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void measureHorizontal(int i9, int i10) {
        int[] iArr;
        int i11;
        int iMax;
        int i12;
        int i13;
        int iMax2;
        int iMax3;
        int i14;
        int i15;
        float f9;
        int i16;
        int baseline;
        int i17;
        int i18;
        int i19;
        char c9;
        int i20;
        int i21;
        boolean z8;
        boolean z9;
        View view;
        int i22;
        boolean z10;
        int measuredHeight;
        int childrenSkipCount;
        int baseline2;
        int i23;
        this.mTotalLength = 0;
        int virtualChildCount = getVirtualChildCount();
        int mode = View.MeasureSpec.getMode(i9);
        int mode2 = View.MeasureSpec.getMode(i10);
        if (this.mMaxAscent == null || this.mMaxDescent == null) {
            this.mMaxAscent = new int[4];
            this.mMaxDescent = new int[4];
        }
        int[] iArr2 = this.mMaxAscent;
        int[] iArr3 = this.mMaxDescent;
        iArr2[3] = -1;
        iArr2[2] = -1;
        iArr2[1] = -1;
        iArr2[0] = -1;
        iArr3[3] = -1;
        iArr3[2] = -1;
        iArr3[1] = -1;
        iArr3[0] = -1;
        boolean z11 = this.mBaselineAligned;
        boolean z12 = this.mUseLargestChild;
        int i24 = 1073741824;
        boolean z13 = mode == 1073741824;
        int childrenSkipCount2 = 0;
        int iMax4 = 0;
        int iMax5 = 0;
        int iMax6 = 0;
        int iMax7 = 0;
        boolean z14 = false;
        int iCombineMeasuredStates = 0;
        boolean z15 = false;
        boolean z16 = true;
        float f10 = 0.0f;
        while (true) {
            iArr = iArr3;
            if (childrenSkipCount2 >= virtualChildCount) {
                break;
            }
            View virtualChildAt = getVirtualChildAt(childrenSkipCount2);
            if (virtualChildAt == null) {
                this.mTotalLength += measureNullChild(childrenSkipCount2);
            } else if (virtualChildAt.getVisibility() == 8) {
                childrenSkipCount2 += getChildrenSkipCount(virtualChildAt, childrenSkipCount2);
            } else {
                if (hasDividerBeforeChildAt(childrenSkipCount2)) {
                    this.mTotalLength += this.mDividerWidth;
                }
                C0183a c0183a = (C0183a) virtualChildAt.getLayoutParams();
                float f11 = c0183a.f849a;
                float f12 = f10 + f11;
                if (mode == i24 && ((ViewGroup.MarginLayoutParams) c0183a).width == 0 && f11 > BitmapDescriptorFactory.HUE_RED) {
                    if (z13) {
                        this.mTotalLength += ((ViewGroup.MarginLayoutParams) c0183a).leftMargin + ((ViewGroup.MarginLayoutParams) c0183a).rightMargin;
                    } else {
                        int i25 = this.mTotalLength;
                        this.mTotalLength = Math.max(i25, ((ViewGroup.MarginLayoutParams) c0183a).leftMargin + i25 + ((ViewGroup.MarginLayoutParams) c0183a).rightMargin);
                    }
                    if (z11) {
                        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
                        virtualChildAt.measure(iMakeMeasureSpec, iMakeMeasureSpec);
                        i21 = childrenSkipCount2;
                        z8 = z12;
                        z9 = z11;
                        view = virtualChildAt;
                    } else {
                        i21 = childrenSkipCount2;
                        z8 = z12;
                        z9 = z11;
                        view = virtualChildAt;
                        z14 = true;
                        i22 = 1073741824;
                        if (mode2 == i22 && ((ViewGroup.MarginLayoutParams) c0183a).height == -1) {
                            z10 = true;
                            z15 = true;
                        } else {
                            z10 = false;
                        }
                        int i26 = ((ViewGroup.MarginLayoutParams) c0183a).topMargin + ((ViewGroup.MarginLayoutParams) c0183a).bottomMargin;
                        measuredHeight = view.getMeasuredHeight() + i26;
                        iCombineMeasuredStates = View.combineMeasuredStates(iCombineMeasuredStates, view.getMeasuredState());
                        if (z9 && (baseline2 = view.getBaseline()) != -1) {
                            i23 = c0183a.f850b;
                            if (i23 < 0) {
                                i23 = this.mGravity;
                            }
                            int i27 = (((i23 & 112) >> 4) & (-2)) >> 1;
                            iArr2[i27] = Math.max(iArr2[i27], baseline2);
                            iArr[i27] = Math.max(iArr[i27], measuredHeight - baseline2);
                        }
                        iMax5 = Math.max(iMax5, measuredHeight);
                        z16 = !z16 && ((ViewGroup.MarginLayoutParams) c0183a).height == -1;
                        if (c0183a.f849a <= BitmapDescriptorFactory.HUE_RED) {
                            if (!z10) {
                                i26 = measuredHeight;
                            }
                            iMax7 = Math.max(iMax7, i26);
                        } else {
                            int i28 = iMax7;
                            if (!z10) {
                                i26 = measuredHeight;
                            }
                            iMax6 = Math.max(iMax6, i26);
                            iMax7 = i28;
                        }
                        int i29 = i21;
                        childrenSkipCount = getChildrenSkipCount(view, i29) + i29;
                        f10 = f12;
                        int i30 = childrenSkipCount + 1;
                        iArr3 = iArr;
                        z12 = z8;
                        z11 = z9;
                        i24 = i22;
                        childrenSkipCount2 = i30;
                    }
                } else {
                    if (((ViewGroup.MarginLayoutParams) c0183a).width != 0 || f11 <= BitmapDescriptorFactory.HUE_RED) {
                        c9 = 65534;
                        i20 = Integer.MIN_VALUE;
                    } else {
                        c9 = 65534;
                        ((ViewGroup.MarginLayoutParams) c0183a).width = -2;
                        i20 = 0;
                    }
                    i21 = childrenSkipCount2;
                    int i31 = i20;
                    z8 = z12;
                    z9 = z11;
                    measureChildBeforeLayout(virtualChildAt, i21, i9, f12 == BitmapDescriptorFactory.HUE_RED ? this.mTotalLength : 0, i10, 0);
                    if (i31 != Integer.MIN_VALUE) {
                        ((ViewGroup.MarginLayoutParams) c0183a).width = i31;
                    }
                    int measuredWidth = virtualChildAt.getMeasuredWidth();
                    if (z13) {
                        view = virtualChildAt;
                        this.mTotalLength += ((ViewGroup.MarginLayoutParams) c0183a).leftMargin + measuredWidth + ((ViewGroup.MarginLayoutParams) c0183a).rightMargin + getNextLocationOffset(view);
                    } else {
                        view = virtualChildAt;
                        int i32 = this.mTotalLength;
                        this.mTotalLength = Math.max(i32, i32 + measuredWidth + ((ViewGroup.MarginLayoutParams) c0183a).leftMargin + ((ViewGroup.MarginLayoutParams) c0183a).rightMargin + getNextLocationOffset(view));
                    }
                    if (z8) {
                        iMax4 = Math.max(measuredWidth, iMax4);
                    }
                }
                i22 = 1073741824;
                if (mode2 == i22) {
                    z10 = false;
                    int i262 = ((ViewGroup.MarginLayoutParams) c0183a).topMargin + ((ViewGroup.MarginLayoutParams) c0183a).bottomMargin;
                    measuredHeight = view.getMeasuredHeight() + i262;
                    iCombineMeasuredStates = View.combineMeasuredStates(iCombineMeasuredStates, view.getMeasuredState());
                    if (z9) {
                        i23 = c0183a.f850b;
                        if (i23 < 0) {
                        }
                        int i272 = (((i23 & 112) >> 4) & (-2)) >> 1;
                        iArr2[i272] = Math.max(iArr2[i272], baseline2);
                        iArr[i272] = Math.max(iArr[i272], measuredHeight - baseline2);
                    }
                    iMax5 = Math.max(iMax5, measuredHeight);
                    if (z16) {
                        if (c0183a.f849a <= BitmapDescriptorFactory.HUE_RED) {
                        }
                        int i292 = i21;
                        childrenSkipCount = getChildrenSkipCount(view, i292) + i292;
                        f10 = f12;
                    }
                }
                int i302 = childrenSkipCount + 1;
                iArr3 = iArr;
                z12 = z8;
                z11 = z9;
                i24 = i22;
                childrenSkipCount2 = i302;
            }
            z8 = z12;
            z9 = z11;
            int i33 = i24;
            childrenSkipCount = childrenSkipCount2;
            i22 = i33;
            int i3022 = childrenSkipCount + 1;
            iArr3 = iArr;
            z12 = z8;
            z11 = z9;
            i24 = i22;
            childrenSkipCount2 = i3022;
        }
        boolean z17 = z12;
        boolean z18 = z11;
        int i34 = iMax5;
        int i35 = iMax6;
        int i36 = iMax7;
        int i37 = iCombineMeasuredStates;
        if (this.mTotalLength > 0 && hasDividerBeforeChildAt(virtualChildCount)) {
            this.mTotalLength += this.mDividerWidth;
        }
        int i38 = iArr2[1];
        if (i38 == -1 && iArr2[0] == -1 && iArr2[2] == -1 && iArr2[3] == -1) {
            iMax = i34;
            i11 = i37;
        } else {
            i11 = i37;
            iMax = Math.max(i34, Math.max(iArr2[3], Math.max(iArr2[0], Math.max(i38, iArr2[2]))) + Math.max(iArr[3], Math.max(iArr[0], Math.max(iArr[1], iArr[2]))));
        }
        if (z17 && (mode == Integer.MIN_VALUE || mode == 0)) {
            this.mTotalLength = 0;
            int childrenSkipCount3 = 0;
            while (childrenSkipCount3 < virtualChildCount) {
                View virtualChildAt2 = getVirtualChildAt(childrenSkipCount3);
                if (virtualChildAt2 == null) {
                    this.mTotalLength += measureNullChild(childrenSkipCount3);
                } else if (virtualChildAt2.getVisibility() == 8) {
                    childrenSkipCount3 += getChildrenSkipCount(virtualChildAt2, childrenSkipCount3);
                } else {
                    C0183a c0183a2 = (C0183a) virtualChildAt2.getLayoutParams();
                    if (z13) {
                        this.mTotalLength += ((ViewGroup.MarginLayoutParams) c0183a2).leftMargin + iMax4 + ((ViewGroup.MarginLayoutParams) c0183a2).rightMargin + getNextLocationOffset(virtualChildAt2);
                    } else {
                        int i39 = this.mTotalLength;
                        i19 = iMax;
                        this.mTotalLength = Math.max(i39, i39 + iMax4 + ((ViewGroup.MarginLayoutParams) c0183a2).leftMargin + ((ViewGroup.MarginLayoutParams) c0183a2).rightMargin + getNextLocationOffset(virtualChildAt2));
                        childrenSkipCount3++;
                        iMax = i19;
                    }
                }
                i19 = iMax;
                childrenSkipCount3++;
                iMax = i19;
            }
        }
        int i40 = iMax;
        int paddingLeft = this.mTotalLength + getPaddingLeft() + getPaddingRight();
        this.mTotalLength = paddingLeft;
        int iResolveSizeAndState = View.resolveSizeAndState(Math.max(paddingLeft, getSuggestedMinimumWidth()), i9, 0);
        int i41 = (16777215 & iResolveSizeAndState) - this.mTotalLength;
        if (z14 || (i41 != 0 && f10 > BitmapDescriptorFactory.HUE_RED)) {
            float f13 = this.mWeightSum;
            if (f13 > BitmapDescriptorFactory.HUE_RED) {
                f10 = f13;
            }
            iArr2[3] = -1;
            iArr2[2] = -1;
            iArr2[1] = -1;
            iArr2[0] = -1;
            iArr[3] = -1;
            iArr[2] = -1;
            iArr[1] = -1;
            iArr[0] = -1;
            this.mTotalLength = 0;
            int i42 = i35;
            int iMax8 = -1;
            int iCombineMeasuredStates2 = i11;
            int i43 = 0;
            while (i43 < virtualChildCount) {
                View virtualChildAt3 = getVirtualChildAt(i43);
                if (virtualChildAt3 == null || virtualChildAt3.getVisibility() == 8) {
                    i14 = i41;
                    i15 = virtualChildCount;
                } else {
                    C0183a c0183a3 = (C0183a) virtualChildAt3.getLayoutParams();
                    float f14 = c0183a3.f849a;
                    if (f14 > BitmapDescriptorFactory.HUE_RED) {
                        int i44 = (int) ((i41 * f14) / f10);
                        float f15 = f10 - f14;
                        int i45 = i41 - i44;
                        i15 = virtualChildCount;
                        int childMeasureSpec = ViewGroup.getChildMeasureSpec(i10, getPaddingTop() + getPaddingBottom() + ((ViewGroup.MarginLayoutParams) c0183a3).topMargin + ((ViewGroup.MarginLayoutParams) c0183a3).bottomMargin, ((ViewGroup.MarginLayoutParams) c0183a3).height);
                        if (((ViewGroup.MarginLayoutParams) c0183a3).width == 0) {
                            i18 = 1073741824;
                            if (mode == 1073741824) {
                                if (i44 <= 0) {
                                    i44 = 0;
                                }
                                virtualChildAt3.measure(View.MeasureSpec.makeMeasureSpec(i44, 1073741824), childMeasureSpec);
                            }
                            iCombineMeasuredStates2 = View.combineMeasuredStates(iCombineMeasuredStates2, virtualChildAt3.getMeasuredState() & (-16777216));
                            f10 = f15;
                            i14 = i45;
                        } else {
                            i18 = 1073741824;
                        }
                        int measuredWidth2 = virtualChildAt3.getMeasuredWidth() + i44;
                        if (measuredWidth2 < 0) {
                            measuredWidth2 = 0;
                        }
                        virtualChildAt3.measure(View.MeasureSpec.makeMeasureSpec(measuredWidth2, i18), childMeasureSpec);
                        iCombineMeasuredStates2 = View.combineMeasuredStates(iCombineMeasuredStates2, virtualChildAt3.getMeasuredState() & (-16777216));
                        f10 = f15;
                        i14 = i45;
                    } else {
                        i14 = i41;
                        i15 = virtualChildCount;
                    }
                    if (z13) {
                        this.mTotalLength += virtualChildAt3.getMeasuredWidth() + ((ViewGroup.MarginLayoutParams) c0183a3).leftMargin + ((ViewGroup.MarginLayoutParams) c0183a3).rightMargin + getNextLocationOffset(virtualChildAt3);
                        f9 = f10;
                    } else {
                        int i46 = this.mTotalLength;
                        f9 = f10;
                        this.mTotalLength = Math.max(i46, virtualChildAt3.getMeasuredWidth() + i46 + ((ViewGroup.MarginLayoutParams) c0183a3).leftMargin + ((ViewGroup.MarginLayoutParams) c0183a3).rightMargin + getNextLocationOffset(virtualChildAt3));
                    }
                    boolean z19 = mode2 != 1073741824 && ((ViewGroup.MarginLayoutParams) c0183a3).height == -1;
                    int i47 = ((ViewGroup.MarginLayoutParams) c0183a3).topMargin + ((ViewGroup.MarginLayoutParams) c0183a3).bottomMargin;
                    int measuredHeight2 = virtualChildAt3.getMeasuredHeight() + i47;
                    iMax8 = Math.max(iMax8, measuredHeight2);
                    if (!z19) {
                        i47 = measuredHeight2;
                    }
                    int iMax9 = Math.max(i42, i47);
                    if (z16) {
                        i16 = -1;
                        boolean z20 = ((ViewGroup.MarginLayoutParams) c0183a3).height == -1;
                        if (z18 && (baseline = virtualChildAt3.getBaseline()) != i16) {
                            i17 = c0183a3.f850b;
                            if (i17 < 0) {
                                i17 = this.mGravity;
                            }
                            int i48 = (((i17 & 112) >> 4) & (-2)) >> 1;
                            iArr2[i48] = Math.max(iArr2[i48], baseline);
                            iArr[i48] = Math.max(iArr[i48], measuredHeight2 - baseline);
                        }
                        z16 = z20;
                        i42 = iMax9;
                        f10 = f9;
                    } else {
                        i16 = -1;
                    }
                    if (z18) {
                        i17 = c0183a3.f850b;
                        if (i17 < 0) {
                        }
                        int i482 = (((i17 & 112) >> 4) & (-2)) >> 1;
                        iArr2[i482] = Math.max(iArr2[i482], baseline);
                        iArr[i482] = Math.max(iArr[i482], measuredHeight2 - baseline);
                    }
                    z16 = z20;
                    i42 = iMax9;
                    f10 = f9;
                }
                i43++;
                i41 = i14;
                virtualChildCount = i15;
            }
            i12 = i10;
            i13 = virtualChildCount;
            this.mTotalLength += getPaddingLeft() + getPaddingRight();
            int i49 = iArr2[1];
            iMax2 = (i49 == -1 && iArr2[0] == -1 && iArr2[2] == -1 && iArr2[3] == -1) ? iMax8 : Math.max(iMax8, Math.max(iArr2[3], Math.max(iArr2[0], Math.max(i49, iArr2[2]))) + Math.max(iArr[3], Math.max(iArr[0], Math.max(iArr[1], iArr[2]))));
            iMax3 = i42;
            i11 = iCombineMeasuredStates2;
        } else {
            iMax3 = Math.max(i35, i36);
            if (z17 && mode != 1073741824) {
                for (int i50 = 0; i50 < virtualChildCount; i50++) {
                    View virtualChildAt4 = getVirtualChildAt(i50);
                    if (virtualChildAt4 != null && virtualChildAt4.getVisibility() != 8 && ((C0183a) virtualChildAt4.getLayoutParams()).f849a > BitmapDescriptorFactory.HUE_RED) {
                        virtualChildAt4.measure(View.MeasureSpec.makeMeasureSpec(iMax4, 1073741824), View.MeasureSpec.makeMeasureSpec(virtualChildAt4.getMeasuredHeight(), 1073741824));
                    }
                }
            }
            i12 = i10;
            i13 = virtualChildCount;
            iMax2 = i40;
        }
        if (z16 || mode2 == 1073741824) {
            iMax3 = iMax2;
        }
        setMeasuredDimension(iResolveSizeAndState | (i11 & (-16777216)), View.resolveSizeAndState(Math.max(iMax3 + getPaddingTop() + getPaddingBottom(), getSuggestedMinimumHeight()), i12, i11 << 16));
        if (z15) {
            forceUniformHeight(i13, i9);
        }
    }

    public int measureNullChild(int i9) {
        return 0;
    }

    /* JADX WARN: Removed duplicated region for block: B:150:0x0325  */
    /* JADX WARN: Removed duplicated region for block: B:156:0x0333  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void measureVertical(int i9, int i10) {
        int i11;
        int iCombineMeasuredStates;
        int i12;
        int iMax;
        int i13;
        int i14;
        int i15;
        boolean z8;
        int i16;
        int i17;
        int i18;
        int i19;
        int i20;
        int i21;
        int i22;
        int i23;
        int i24;
        View view;
        int iMax2;
        boolean z9;
        int iMax3;
        this.mTotalLength = 0;
        int virtualChildCount = getVirtualChildCount();
        int mode = View.MeasureSpec.getMode(i9);
        int mode2 = View.MeasureSpec.getMode(i10);
        int i25 = this.mBaselineAlignedChildIndex;
        boolean z10 = this.mUseLargestChild;
        int i26 = 0;
        int i27 = 0;
        int i28 = 0;
        int iMax4 = 0;
        int i29 = 0;
        int childrenSkipCount = 0;
        boolean z11 = false;
        boolean z12 = false;
        boolean z13 = true;
        float f9 = 0.0f;
        while (true) {
            int i30 = 8;
            int i31 = iMax4;
            if (childrenSkipCount >= virtualChildCount) {
                int i32 = i26;
                int i33 = i28;
                int i34 = i29;
                int i35 = virtualChildCount;
                int i36 = mode2;
                int iMax5 = i27;
                if (this.mTotalLength > 0) {
                    i11 = i35;
                    if (hasDividerBeforeChildAt(i11)) {
                        this.mTotalLength += this.mDividerHeight;
                    }
                } else {
                    i11 = i35;
                }
                if (z10 && (i36 == Integer.MIN_VALUE || i36 == 0)) {
                    this.mTotalLength = 0;
                    int childrenSkipCount2 = 0;
                    while (childrenSkipCount2 < i11) {
                        View virtualChildAt = getVirtualChildAt(childrenSkipCount2);
                        if (virtualChildAt == null) {
                            this.mTotalLength += measureNullChild(childrenSkipCount2);
                        } else if (virtualChildAt.getVisibility() == i30) {
                            childrenSkipCount2 += getChildrenSkipCount(virtualChildAt, childrenSkipCount2);
                        } else {
                            C0183a c0183a = (C0183a) virtualChildAt.getLayoutParams();
                            int i37 = this.mTotalLength;
                            this.mTotalLength = Math.max(i37, i37 + i33 + ((ViewGroup.MarginLayoutParams) c0183a).topMargin + ((ViewGroup.MarginLayoutParams) c0183a).bottomMargin + getNextLocationOffset(virtualChildAt));
                        }
                        childrenSkipCount2++;
                        i30 = 8;
                    }
                }
                int paddingTop = this.mTotalLength + getPaddingTop() + getPaddingBottom();
                this.mTotalLength = paddingTop;
                int iResolveSizeAndState = View.resolveSizeAndState(Math.max(paddingTop, getSuggestedMinimumHeight()), i10, 0);
                int i38 = (16777215 & iResolveSizeAndState) - this.mTotalLength;
                if (z11 || (i38 != 0 && f9 > BitmapDescriptorFactory.HUE_RED)) {
                    float f10 = this.mWeightSum;
                    if (f10 > BitmapDescriptorFactory.HUE_RED) {
                        f9 = f10;
                    }
                    this.mTotalLength = 0;
                    int i39 = i38;
                    int i40 = i34;
                    iCombineMeasuredStates = i32;
                    int i41 = 0;
                    while (i41 < i11) {
                        View virtualChildAt2 = getVirtualChildAt(i41);
                        if (virtualChildAt2.getVisibility() == 8) {
                            i13 = i39;
                        } else {
                            C0183a c0183a2 = (C0183a) virtualChildAt2.getLayoutParams();
                            float f11 = c0183a2.f849a;
                            if (f11 > BitmapDescriptorFactory.HUE_RED) {
                                int i42 = (int) ((i39 * f11) / f9);
                                float f12 = f9 - f11;
                                i13 = i39 - i42;
                                int childMeasureSpec = ViewGroup.getChildMeasureSpec(i9, getPaddingLeft() + getPaddingRight() + ((ViewGroup.MarginLayoutParams) c0183a2).leftMargin + ((ViewGroup.MarginLayoutParams) c0183a2).rightMargin, ((ViewGroup.MarginLayoutParams) c0183a2).width);
                                if (((ViewGroup.MarginLayoutParams) c0183a2).height == 0) {
                                    i16 = 1073741824;
                                    if (i36 == 1073741824) {
                                        if (i42 <= 0) {
                                            i42 = 0;
                                        }
                                        virtualChildAt2.measure(childMeasureSpec, View.MeasureSpec.makeMeasureSpec(i42, 1073741824));
                                    }
                                    iCombineMeasuredStates = View.combineMeasuredStates(iCombineMeasuredStates, virtualChildAt2.getMeasuredState() & (-256));
                                    f9 = f12;
                                } else {
                                    i16 = 1073741824;
                                }
                                int measuredHeight = virtualChildAt2.getMeasuredHeight() + i42;
                                if (measuredHeight < 0) {
                                    measuredHeight = 0;
                                }
                                virtualChildAt2.measure(childMeasureSpec, View.MeasureSpec.makeMeasureSpec(measuredHeight, i16));
                                iCombineMeasuredStates = View.combineMeasuredStates(iCombineMeasuredStates, virtualChildAt2.getMeasuredState() & (-256));
                                f9 = f12;
                            } else {
                                i13 = i39;
                            }
                            int i43 = ((ViewGroup.MarginLayoutParams) c0183a2).leftMargin + ((ViewGroup.MarginLayoutParams) c0183a2).rightMargin;
                            int measuredWidth = virtualChildAt2.getMeasuredWidth() + i43;
                            iMax5 = Math.max(iMax5, measuredWidth);
                            float f13 = f9;
                            if (mode != 1073741824) {
                                i14 = iCombineMeasuredStates;
                                i15 = -1;
                                z8 = ((ViewGroup.MarginLayoutParams) c0183a2).width == -1;
                                if (!z8) {
                                    i43 = measuredWidth;
                                }
                                int iMax6 = Math.max(i40, i43);
                                boolean z14 = !z13 && ((ViewGroup.MarginLayoutParams) c0183a2).width == i15;
                                int i44 = this.mTotalLength;
                                this.mTotalLength = Math.max(i44, virtualChildAt2.getMeasuredHeight() + i44 + ((ViewGroup.MarginLayoutParams) c0183a2).topMargin + ((ViewGroup.MarginLayoutParams) c0183a2).bottomMargin + getNextLocationOffset(virtualChildAt2));
                                z13 = z14;
                                iCombineMeasuredStates = i14;
                                i40 = iMax6;
                                f9 = f13;
                            } else {
                                i14 = iCombineMeasuredStates;
                                i15 = -1;
                            }
                            if (!z8) {
                            }
                            int iMax62 = Math.max(i40, i43);
                            if (z13) {
                                int i442 = this.mTotalLength;
                                this.mTotalLength = Math.max(i442, virtualChildAt2.getMeasuredHeight() + i442 + ((ViewGroup.MarginLayoutParams) c0183a2).topMargin + ((ViewGroup.MarginLayoutParams) c0183a2).bottomMargin + getNextLocationOffset(virtualChildAt2));
                                z13 = z14;
                                iCombineMeasuredStates = i14;
                                i40 = iMax62;
                                f9 = f13;
                            }
                        }
                        i41++;
                        i39 = i13;
                    }
                    i12 = i9;
                    this.mTotalLength += getPaddingTop() + getPaddingBottom();
                    iMax = i40;
                } else {
                    iMax = Math.max(i34, i31);
                    if (z10 && i36 != 1073741824) {
                        for (int i45 = 0; i45 < i11; i45++) {
                            View virtualChildAt3 = getVirtualChildAt(i45);
                            if (virtualChildAt3 != null && virtualChildAt3.getVisibility() != 8 && ((C0183a) virtualChildAt3.getLayoutParams()).f849a > BitmapDescriptorFactory.HUE_RED) {
                                virtualChildAt3.measure(View.MeasureSpec.makeMeasureSpec(virtualChildAt3.getMeasuredWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(i33, 1073741824));
                            }
                        }
                    }
                    i12 = i9;
                    iCombineMeasuredStates = i32;
                }
                if (z13 || mode == 1073741824) {
                    iMax = iMax5;
                }
                setMeasuredDimension(View.resolveSizeAndState(Math.max(iMax + getPaddingLeft() + getPaddingRight(), getSuggestedMinimumWidth()), i12, iCombineMeasuredStates), iResolveSizeAndState);
                if (z12) {
                    forceUniformWidth(i11, i10);
                    return;
                }
                return;
            }
            View virtualChildAt4 = getVirtualChildAt(childrenSkipCount);
            if (virtualChildAt4 == null) {
                this.mTotalLength += measureNullChild(childrenSkipCount);
                i20 = virtualChildCount;
                i21 = mode2;
                iMax4 = i31;
            } else {
                int i46 = i26;
                if (virtualChildAt4.getVisibility() == 8) {
                    childrenSkipCount += getChildrenSkipCount(virtualChildAt4, childrenSkipCount);
                    i20 = virtualChildCount;
                    iMax4 = i31;
                    i26 = i46;
                    i21 = mode2;
                } else {
                    if (hasDividerBeforeChildAt(childrenSkipCount)) {
                        this.mTotalLength += this.mDividerHeight;
                    }
                    C0183a c0183a3 = (C0183a) virtualChildAt4.getLayoutParams();
                    float f14 = c0183a3.f849a;
                    float f15 = f9 + f14;
                    if (mode2 == 1073741824 && ((ViewGroup.MarginLayoutParams) c0183a3).height == 0 && f14 > BitmapDescriptorFactory.HUE_RED) {
                        int i47 = this.mTotalLength;
                        this.mTotalLength = Math.max(i47, ((ViewGroup.MarginLayoutParams) c0183a3).topMargin + i47 + ((ViewGroup.MarginLayoutParams) c0183a3).bottomMargin);
                        iMax2 = i28;
                        view = virtualChildAt4;
                        i23 = i29;
                        z11 = true;
                        i18 = i46;
                        i19 = i27;
                        i20 = virtualChildCount;
                        i21 = mode2;
                        i22 = i31;
                        i24 = childrenSkipCount;
                    } else {
                        int i48 = i27;
                        if (((ViewGroup.MarginLayoutParams) c0183a3).height != 0 || f14 <= BitmapDescriptorFactory.HUE_RED) {
                            i17 = Integer.MIN_VALUE;
                        } else {
                            ((ViewGroup.MarginLayoutParams) c0183a3).height = -2;
                            i17 = 0;
                        }
                        i18 = i46;
                        int i49 = i17;
                        i19 = i48;
                        int i50 = i28;
                        i20 = virtualChildCount;
                        i21 = mode2;
                        i22 = i31;
                        i23 = i29;
                        i24 = childrenSkipCount;
                        measureChildBeforeLayout(virtualChildAt4, childrenSkipCount, i9, 0, i10, f15 == BitmapDescriptorFactory.HUE_RED ? this.mTotalLength : 0);
                        if (i49 != Integer.MIN_VALUE) {
                            ((ViewGroup.MarginLayoutParams) c0183a3).height = i49;
                        }
                        int measuredHeight2 = virtualChildAt4.getMeasuredHeight();
                        int i51 = this.mTotalLength;
                        view = virtualChildAt4;
                        this.mTotalLength = Math.max(i51, i51 + measuredHeight2 + ((ViewGroup.MarginLayoutParams) c0183a3).topMargin + ((ViewGroup.MarginLayoutParams) c0183a3).bottomMargin + getNextLocationOffset(view));
                        iMax2 = z10 ? Math.max(measuredHeight2, i50) : i50;
                    }
                    if (i25 >= 0 && i25 == i24 + 1) {
                        this.mBaselineChildTop = this.mTotalLength;
                    }
                    if (i24 < i25 && c0183a3.f849a > BitmapDescriptorFactory.HUE_RED) {
                        throw new RuntimeException("A child of LinearLayout with index less than mBaselineAlignedChildIndex has weight > 0, which won't work.  Either remove the weight, or don't set mBaselineAlignedChildIndex.");
                    }
                    if (mode == 1073741824 || ((ViewGroup.MarginLayoutParams) c0183a3).width != -1) {
                        z9 = false;
                    } else {
                        z9 = true;
                        z12 = true;
                    }
                    int i52 = ((ViewGroup.MarginLayoutParams) c0183a3).leftMargin + ((ViewGroup.MarginLayoutParams) c0183a3).rightMargin;
                    int measuredWidth2 = view.getMeasuredWidth() + i52;
                    int iMax7 = Math.max(i19, measuredWidth2);
                    int iCombineMeasuredStates2 = View.combineMeasuredStates(i18, view.getMeasuredState());
                    z13 = z13 && ((ViewGroup.MarginLayoutParams) c0183a3).width == -1;
                    if (c0183a3.f849a > BitmapDescriptorFactory.HUE_RED) {
                        if (!z9) {
                            i52 = measuredWidth2;
                        }
                        iMax4 = Math.max(i22, i52);
                        iMax3 = i23;
                    } else {
                        if (!z9) {
                            i52 = measuredWidth2;
                        }
                        iMax3 = Math.max(i23, i52);
                        iMax4 = i22;
                    }
                    int childrenSkipCount3 = getChildrenSkipCount(view, i24) + i24;
                    i28 = iMax2;
                    f9 = f15;
                    i29 = iMax3;
                    i26 = iCombineMeasuredStates2;
                    childrenSkipCount = childrenSkipCount3;
                    i27 = iMax7;
                }
            }
            childrenSkipCount++;
            mode2 = i21;
            virtualChildCount = i20;
        }
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        if (this.mDivider == null) {
            return;
        }
        if (this.mOrientation == 1) {
            drawDividersVertical(canvas);
        } else {
            drawDividersHorizontal(canvas);
        }
    }

    @Override // android.view.View
    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName(ACCESSIBILITY_CLASS_NAME);
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName(ACCESSIBILITY_CLASS_NAME);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean z8, int i9, int i10, int i11, int i12) {
        if (this.mOrientation == 1) {
            layoutVertical(i9, i10, i11, i12);
        } else {
            layoutHorizontal(i9, i10, i11, i12);
        }
    }

    @Override // android.view.View
    public void onMeasure(int i9, int i10) {
        if (this.mOrientation == 1) {
            measureVertical(i9, i10);
        } else {
            measureHorizontal(i9, i10);
        }
    }

    public void setBaselineAligned(boolean z8) {
        this.mBaselineAligned = z8;
    }

    public void setBaselineAlignedChildIndex(int i9) {
        if (i9 >= 0 && i9 < getChildCount()) {
            this.mBaselineAlignedChildIndex = i9;
            return;
        }
        throw new IllegalArgumentException("base aligned child index out of range (0, " + getChildCount() + ")");
    }

    public void setDividerDrawable(Drawable drawable) {
        if (drawable == this.mDivider) {
            return;
        }
        this.mDivider = drawable;
        if (drawable != null) {
            this.mDividerWidth = drawable.getIntrinsicWidth();
            this.mDividerHeight = drawable.getIntrinsicHeight();
        } else {
            this.mDividerWidth = 0;
            this.mDividerHeight = 0;
        }
        setWillNotDraw(drawable == null);
        requestLayout();
    }

    public void setDividerPadding(int i9) {
        this.mDividerPadding = i9;
    }

    public void setGravity(int i9) {
        if (this.mGravity != i9) {
            if ((8388615 & i9) == 0) {
                i9 |= 8388611;
            }
            if ((i9 & 112) == 0) {
                i9 |= 48;
            }
            this.mGravity = i9;
            requestLayout();
        }
    }

    public void setHorizontalGravity(int i9) {
        int i10 = i9 & 8388615;
        int i11 = this.mGravity;
        if ((8388615 & i11) != i10) {
            this.mGravity = i10 | ((-8388616) & i11);
            requestLayout();
        }
    }

    public void setMeasureWithLargestChildEnabled(boolean z8) {
        this.mUseLargestChild = z8;
    }

    public void setOrientation(int i9) {
        if (this.mOrientation != i9) {
            this.mOrientation = i9;
            requestLayout();
        }
    }

    public void setShowDividers(int i9) {
        if (i9 != this.mShowDividers) {
            requestLayout();
        }
        this.mShowDividers = i9;
    }

    public void setVerticalGravity(int i9) {
        int i10 = i9 & 112;
        int i11 = this.mGravity;
        if ((i11 & 112) != i10) {
            this.mGravity = i10 | (i11 & (-113));
            requestLayout();
        }
    }

    public void setWeightSum(float f9) {
        this.mWeightSum = Math.max(BitmapDescriptorFactory.HUE_RED, f9);
    }

    @Override // android.view.ViewGroup
    public boolean shouldDelayChildPressedState() {
        return false;
    }

    public LinearLayoutCompat(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    @Override // android.view.ViewGroup
    public C0183a generateDefaultLayoutParams() {
        int i9 = this.mOrientation;
        if (i9 == 0) {
            return new C0183a(-2, -2);
        }
        if (i9 == 1) {
            return new C0183a(-1, -2);
        }
        return null;
    }

    public LinearLayoutCompat(Context context, AttributeSet attributeSet, int i9) {
        super(context, attributeSet, i9);
        this.mBaselineAligned = true;
        this.mBaselineAlignedChildIndex = -1;
        this.mBaselineChildTop = 0;
        this.mGravity = 8388659;
        int[] iArr = C0569j.LinearLayoutCompat;
        C0250q0 c0250q0M1004v = C0250q0.m1004v(context, attributeSet, iArr, i9, 0);
        C4647u.m18528X(this, context, iArr, attributeSet, c0250q0M1004v.m1022r(), i9, 0);
        int iM1015k = c0250q0M1004v.m1015k(C0569j.LinearLayoutCompat_android_orientation, -1);
        if (iM1015k >= 0) {
            setOrientation(iM1015k);
        }
        int iM1015k2 = c0250q0M1004v.m1015k(C0569j.LinearLayoutCompat_android_gravity, -1);
        if (iM1015k2 >= 0) {
            setGravity(iM1015k2);
        }
        boolean zM1005a = c0250q0M1004v.m1005a(C0569j.LinearLayoutCompat_android_baselineAligned, true);
        if (!zM1005a) {
            setBaselineAligned(zM1005a);
        }
        this.mWeightSum = c0250q0M1004v.m1013i(C0569j.LinearLayoutCompat_android_weightSum, -1.0f);
        this.mBaselineAlignedChildIndex = c0250q0M1004v.m1015k(C0569j.LinearLayoutCompat_android_baselineAlignedChildIndex, -1);
        this.mUseLargestChild = c0250q0M1004v.m1005a(C0569j.LinearLayoutCompat_measureWithLargestChild, false);
        setDividerDrawable(c0250q0M1004v.m1011g(C0569j.LinearLayoutCompat_divider));
        this.mShowDividers = c0250q0M1004v.m1015k(C0569j.LinearLayoutCompat_showDividers, 0);
        this.mDividerPadding = c0250q0M1004v.m1010f(C0569j.LinearLayoutCompat_dividerPadding, 0);
        c0250q0M1004v.m1024w();
    }

    @Override // android.view.ViewGroup
    public C0183a generateLayoutParams(AttributeSet attributeSet) {
        return new C0183a(getContext(), attributeSet);
    }

    @Override // android.view.ViewGroup
    public C0183a generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return new C0183a(layoutParams);
    }

    /* renamed from: androidx.appcompat.widget.LinearLayoutCompat$a */
    public static class C0183a extends ViewGroup.MarginLayoutParams {

        /* renamed from: a */
        public float f849a;

        /* renamed from: b */
        public int f850b;

        public C0183a(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.f850b = -1;
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C0569j.LinearLayoutCompat_Layout);
            this.f849a = typedArrayObtainStyledAttributes.getFloat(C0569j.LinearLayoutCompat_Layout_android_layout_weight, BitmapDescriptorFactory.HUE_RED);
            this.f850b = typedArrayObtainStyledAttributes.getInt(C0569j.LinearLayoutCompat_Layout_android_layout_gravity, -1);
            typedArrayObtainStyledAttributes.recycle();
        }

        public C0183a(int i9, int i10) {
            super(i9, i10);
            this.f850b = -1;
            this.f849a = BitmapDescriptorFactory.HUE_RED;
        }

        public C0183a(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.f850b = -1;
        }
    }
}
