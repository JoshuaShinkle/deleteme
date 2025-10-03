package androidx.constraintlayout.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.solver.widgets.C0288a;
import androidx.constraintlayout.solver.widgets.C0292e;
import androidx.constraintlayout.solver.widgets.C0294g;
import androidx.constraintlayout.solver.widgets.C0296i;
import androidx.constraintlayout.solver.widgets.ConstraintAnchor;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import com.google.android.exoplayer2.C3322C;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import org.xmlpull.v1.XmlPullParserException;
import p161p.C5867b;

/* loaded from: classes.dex */
public class ConstraintLayout extends ViewGroup {

    /* renamed from: b */
    public SparseArray<View> f1539b;

    /* renamed from: c */
    public ArrayList<ConstraintHelper> f1540c;

    /* renamed from: d */
    public final ArrayList<ConstraintWidget> f1541d;

    /* renamed from: e */
    public C0292e f1542e;

    /* renamed from: f */
    public int f1543f;

    /* renamed from: g */
    public int f1544g;

    /* renamed from: h */
    public int f1545h;

    /* renamed from: i */
    public int f1546i;

    /* renamed from: j */
    public boolean f1547j;

    /* renamed from: k */
    public int f1548k;

    /* renamed from: l */
    public C0300a f1549l;

    /* renamed from: m */
    public int f1550m;

    /* renamed from: n */
    public HashMap<String, Integer> f1551n;

    /* renamed from: o */
    public int f1552o;

    /* renamed from: p */
    public int f1553p;

    /* renamed from: q */
    public int f1554q;

    /* renamed from: r */
    public int f1555r;

    /* renamed from: s */
    public int f1556s;

    /* renamed from: t */
    public int f1557t;

    public ConstraintLayout(Context context, AttributeSet attributeSet) throws XmlPullParserException, IOException {
        super(context, attributeSet);
        this.f1539b = new SparseArray<>();
        this.f1540c = new ArrayList<>(4);
        this.f1541d = new ArrayList<>(100);
        this.f1542e = new C0292e();
        this.f1543f = 0;
        this.f1544g = 0;
        this.f1545h = Integer.MAX_VALUE;
        this.f1546i = Integer.MAX_VALUE;
        this.f1547j = true;
        this.f1548k = 7;
        this.f1549l = null;
        this.f1550m = -1;
        this.f1551n = new HashMap<>();
        this.f1552o = -1;
        this.f1553p = -1;
        this.f1554q = -1;
        this.f1555r = -1;
        this.f1556s = 0;
        this.f1557t = 0;
        m1401g(attributeSet);
    }

    @Override // android.view.ViewGroup
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public C0298a generateDefaultLayoutParams() {
        return new C0298a(-2, -2);
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int i9, ViewGroup.LayoutParams layoutParams) {
        super.addView(view, i9, layoutParams);
    }

    @Override // android.view.ViewGroup
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public C0298a generateLayoutParams(AttributeSet attributeSet) {
        return new C0298a(getContext(), attributeSet);
    }

    /* renamed from: c */
    public Object m1397c(int i9, Object obj) {
        if (i9 != 0 || !(obj instanceof String)) {
            return null;
        }
        String str = (String) obj;
        HashMap<String, Integer> map = this.f1551n;
        if (map == null || !map.containsKey(str)) {
            return null;
        }
        return this.f1551n.get(str);
    }

    @Override // android.view.ViewGroup
    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof C0298a;
    }

    /* renamed from: d */
    public final ConstraintWidget m1398d(int i9) {
        if (i9 == 0) {
            return this.f1542e;
        }
        View viewFindViewById = this.f1539b.get(i9);
        if (viewFindViewById == null && (viewFindViewById = findViewById(i9)) != null && viewFindViewById != this && viewFindViewById.getParent() == this) {
            onViewAdded(viewFindViewById);
        }
        if (viewFindViewById == this) {
            return this.f1542e;
        }
        if (viewFindViewById == null) {
            return null;
        }
        return ((C0298a) viewFindViewById.getLayoutParams()).f1607l0;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas canvas) throws NumberFormatException {
        Object tag;
        super.dispatchDraw(canvas);
        if (isInEditMode()) {
            int childCount = getChildCount();
            float width = getWidth();
            float height = getHeight();
            for (int i9 = 0; i9 < childCount; i9++) {
                View childAt = getChildAt(i9);
                if (childAt.getVisibility() != 8 && (tag = childAt.getTag()) != null && (tag instanceof String)) {
                    String[] strArrSplit = ((String) tag).split(",");
                    if (strArrSplit.length == 4) {
                        int i10 = Integer.parseInt(strArrSplit[0]);
                        int i11 = Integer.parseInt(strArrSplit[1]);
                        int i12 = Integer.parseInt(strArrSplit[2]);
                        int i13 = (int) ((i10 / 1080.0f) * width);
                        int i14 = (int) ((i11 / 1920.0f) * height);
                        Paint paint = new Paint();
                        paint.setColor(-65536);
                        float f9 = i13;
                        float f10 = i14;
                        float f11 = i13 + ((int) ((i12 / 1080.0f) * width));
                        canvas.drawLine(f9, f10, f11, f10, paint);
                        float f12 = i14 + ((int) ((Integer.parseInt(strArrSplit[3]) / 1920.0f) * height));
                        canvas.drawLine(f11, f10, f11, f12, paint);
                        canvas.drawLine(f11, f12, f9, f12, paint);
                        canvas.drawLine(f9, f12, f9, f10, paint);
                        paint.setColor(-16711936);
                        canvas.drawLine(f9, f10, f11, f12, paint);
                        canvas.drawLine(f9, f12, f11, f10, paint);
                    }
                }
            }
        }
    }

    /* renamed from: e */
    public View m1399e(int i9) {
        return this.f1539b.get(i9);
    }

    /* renamed from: f */
    public final ConstraintWidget m1400f(View view) {
        if (view == this) {
            return this.f1542e;
        }
        if (view == null) {
            return null;
        }
        return ((C0298a) view.getLayoutParams()).f1607l0;
    }

    /* renamed from: g */
    public final void m1401g(AttributeSet attributeSet) throws XmlPullParserException, IOException {
        this.f1542e.m1260W(this);
        this.f1539b.put(getId(), this);
        this.f1549l = null;
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, C5867b.ConstraintLayout_Layout);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i9 = 0; i9 < indexCount; i9++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i9);
                if (index == C5867b.ConstraintLayout_Layout_android_minWidth) {
                    this.f1543f = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, this.f1543f);
                } else if (index == C5867b.ConstraintLayout_Layout_android_minHeight) {
                    this.f1544g = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, this.f1544g);
                } else if (index == C5867b.ConstraintLayout_Layout_android_maxWidth) {
                    this.f1545h = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, this.f1545h);
                } else if (index == C5867b.ConstraintLayout_Layout_android_maxHeight) {
                    this.f1546i = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, this.f1546i);
                } else if (index == C5867b.ConstraintLayout_Layout_layout_optimizationLevel) {
                    this.f1548k = typedArrayObtainStyledAttributes.getInt(index, this.f1548k);
                } else if (index == C5867b.ConstraintLayout_Layout_constraintSet) {
                    int resourceId = typedArrayObtainStyledAttributes.getResourceId(index, 0);
                    try {
                        C0300a c0300a = new C0300a();
                        this.f1549l = c0300a;
                        c0300a.m1422e(getContext(), resourceId);
                    } catch (Resources.NotFoundException unused) {
                        this.f1549l = null;
                    }
                    this.f1550m = resourceId;
                }
            }
            typedArrayObtainStyledAttributes.recycle();
        }
        this.f1542e.m1351c1(this.f1548k);
    }

    public int getMaxHeight() {
        return this.f1546i;
    }

    public int getMaxWidth() {
        return this.f1545h;
    }

    public int getMinHeight() {
        return this.f1544g;
    }

    public int getMinWidth() {
        return this.f1543f;
    }

    public int getOptimizationLevel() {
        return this.f1542e.m1340R0();
    }

    /* renamed from: h */
    public final void m1402h(int i9, int i10) {
        boolean z8;
        boolean z9;
        int baseline;
        int childMeasureSpec;
        int childMeasureSpec2;
        boolean z10;
        int paddingTop = getPaddingTop() + getPaddingBottom();
        int paddingLeft = getPaddingLeft() + getPaddingRight();
        int childCount = getChildCount();
        for (int i11 = 0; i11 < childCount; i11++) {
            View childAt = getChildAt(i11);
            if (childAt.getVisibility() != 8) {
                C0298a c0298a = (C0298a) childAt.getLayoutParams();
                ConstraintWidget constraintWidget = c0298a.f1607l0;
                if (!c0298a.f1582Y && !c0298a.f1583Z) {
                    constraintWidget.m1311x0(childAt.getVisibility());
                    int measuredWidth = ((ViewGroup.MarginLayoutParams) c0298a).width;
                    int measuredHeight = ((ViewGroup.MarginLayoutParams) c0298a).height;
                    boolean z11 = c0298a.f1579V;
                    if (z11 || (z10 = c0298a.f1580W) || (!z11 && c0298a.f1566I == 1) || measuredWidth == -1 || (!z10 && (c0298a.f1567J == 1 || measuredHeight == -1))) {
                        if (measuredWidth == 0) {
                            childMeasureSpec = ViewGroup.getChildMeasureSpec(i9, paddingLeft, -2);
                            z8 = true;
                        } else if (measuredWidth == -1) {
                            childMeasureSpec = ViewGroup.getChildMeasureSpec(i9, paddingLeft, -1);
                            z8 = false;
                        } else {
                            z8 = measuredWidth == -2;
                            childMeasureSpec = ViewGroup.getChildMeasureSpec(i9, paddingLeft, measuredWidth);
                        }
                        if (measuredHeight == 0) {
                            z9 = true;
                            childMeasureSpec2 = ViewGroup.getChildMeasureSpec(i10, paddingTop, -2);
                        } else if (measuredHeight == -1) {
                            childMeasureSpec2 = ViewGroup.getChildMeasureSpec(i10, paddingTop, -1);
                            z9 = false;
                        } else {
                            z9 = measuredHeight == -2;
                            childMeasureSpec2 = ViewGroup.getChildMeasureSpec(i10, paddingTop, measuredHeight);
                        }
                        childAt.measure(childMeasureSpec, childMeasureSpec2);
                        constraintWidget.m1315z0(measuredWidth == -2);
                        constraintWidget.m1269c0(measuredHeight == -2);
                        measuredWidth = childAt.getMeasuredWidth();
                        measuredHeight = childAt.getMeasuredHeight();
                    } else {
                        z8 = false;
                        z9 = false;
                    }
                    constraintWidget.m1313y0(measuredWidth);
                    constraintWidget.m1267b0(measuredHeight);
                    if (z8) {
                        constraintWidget.m1233B0(measuredWidth);
                    }
                    if (z9) {
                        constraintWidget.m1231A0(measuredHeight);
                    }
                    if (c0298a.f1581X && (baseline = childAt.getBaseline()) != -1) {
                        constraintWidget.m1259V(baseline);
                    }
                }
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:113:0x01e2  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x0220  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x0244  */
    /* JADX WARN: Removed duplicated region for block: B:134:0x0246  */
    /* JADX WARN: Removed duplicated region for block: B:137:0x024e  */
    /* JADX WARN: Removed duplicated region for block: B:138:0x0250  */
    /* JADX WARN: Removed duplicated region for block: B:141:0x0264  */
    /* JADX WARN: Removed duplicated region for block: B:143:0x0269  */
    /* JADX WARN: Removed duplicated region for block: B:145:0x026e  */
    /* JADX WARN: Removed duplicated region for block: B:146:0x0276  */
    /* JADX WARN: Removed duplicated region for block: B:148:0x027f  */
    /* JADX WARN: Removed duplicated region for block: B:149:0x0287  */
    /* JADX WARN: Removed duplicated region for block: B:152:0x0292  */
    /* JADX WARN: Removed duplicated region for block: B:155:0x029d  */
    /* renamed from: i */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void m1403i(int i9, int i10) {
        int i11;
        char c9;
        int i12;
        int i13;
        char c10;
        int i14;
        boolean z8;
        int childMeasureSpec;
        boolean z9;
        int i15;
        int childMeasureSpec2;
        int baseline;
        int paddingTop = getPaddingTop() + getPaddingBottom();
        int paddingLeft = getPaddingLeft() + getPaddingRight();
        int childCount = getChildCount();
        int i16 = 0;
        while (true) {
            i11 = 8;
            c9 = 65535;
            i12 = -2;
            if (i16 >= childCount) {
                break;
            }
            View childAt = getChildAt(i16);
            if (childAt.getVisibility() != 8) {
                C0298a c0298a = (C0298a) childAt.getLayoutParams();
                ConstraintWidget constraintWidget = c0298a.f1607l0;
                if (!c0298a.f1582Y && !c0298a.f1583Z) {
                    constraintWidget.m1311x0(childAt.getVisibility());
                    int i17 = ((ViewGroup.MarginLayoutParams) c0298a).width;
                    int i18 = ((ViewGroup.MarginLayoutParams) c0298a).height;
                    if (i17 == 0 || i18 == 0) {
                        constraintWidget.m1310x().m21093c();
                        constraintWidget.m1308w().m21093c();
                    } else {
                        boolean z10 = i17 == -2;
                        int childMeasureSpec3 = ViewGroup.getChildMeasureSpec(i9, paddingLeft, i17);
                        boolean z11 = i18 == -2;
                        childAt.measure(childMeasureSpec3, ViewGroup.getChildMeasureSpec(i10, paddingTop, i18));
                        constraintWidget.m1315z0(i17 == -2);
                        constraintWidget.m1269c0(i18 == -2);
                        int measuredWidth = childAt.getMeasuredWidth();
                        int measuredHeight = childAt.getMeasuredHeight();
                        constraintWidget.m1313y0(measuredWidth);
                        constraintWidget.m1267b0(measuredHeight);
                        if (z10) {
                            constraintWidget.m1233B0(measuredWidth);
                        }
                        if (z11) {
                            constraintWidget.m1231A0(measuredHeight);
                        }
                        if (c0298a.f1581X && (baseline = childAt.getBaseline()) != -1) {
                            constraintWidget.m1259V(baseline);
                        }
                        if (c0298a.f1579V && c0298a.f1580W) {
                            constraintWidget.m1310x().m21090h(measuredWidth);
                            constraintWidget.m1308w().m21090h(measuredHeight);
                        }
                    }
                }
            }
            i16++;
        }
        this.f1542e.m1353e1();
        int i19 = 0;
        while (i19 < childCount) {
            View childAt2 = getChildAt(i19);
            if (childAt2.getVisibility() == i11) {
                i13 = childCount;
                c10 = c9;
                i14 = i12;
            } else {
                C0298a c0298a2 = (C0298a) childAt2.getLayoutParams();
                ConstraintWidget constraintWidget2 = c0298a2.f1607l0;
                if (!c0298a2.f1582Y && !c0298a2.f1583Z) {
                    constraintWidget2.m1311x0(childAt2.getVisibility());
                    int iM1378k = ((ViewGroup.MarginLayoutParams) c0298a2).width;
                    int i20 = ((ViewGroup.MarginLayoutParams) c0298a2).height;
                    if (iM1378k == 0 || i20 == 0) {
                        ConstraintAnchor.Type type = ConstraintAnchor.Type.LEFT;
                        C0296i c0296iM1221f = constraintWidget2.mo1278h(type).m1221f();
                        ConstraintAnchor.Type type2 = ConstraintAnchor.Type.RIGHT;
                        C0296i c0296iM1221f2 = constraintWidget2.mo1278h(type2).m1221f();
                        boolean z12 = (constraintWidget2.mo1278h(type).m1224i() == null || constraintWidget2.mo1278h(type2).m1224i() == null) ? false : true;
                        ConstraintAnchor.Type type3 = ConstraintAnchor.Type.TOP;
                        C0296i c0296iM1221f3 = constraintWidget2.mo1278h(type3).m1221f();
                        ConstraintAnchor.Type type4 = ConstraintAnchor.Type.BOTTOM;
                        C0296i c0296iM1221f4 = constraintWidget2.mo1278h(type4).m1221f();
                        boolean z13 = (constraintWidget2.mo1278h(type3).m1224i() == null || constraintWidget2.mo1278h(type4).m1224i() == null) ? false : true;
                        if (iM1378k == 0 && i20 == 0 && z12 && z13) {
                            i13 = childCount;
                            i14 = i12;
                        } else {
                            ConstraintWidget.DimensionBehaviour dimensionBehaviourM1300s = this.f1542e.m1300s();
                            ConstraintWidget.DimensionBehaviour dimensionBehaviour = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
                            i13 = childCount;
                            boolean z14 = dimensionBehaviourM1300s != dimensionBehaviour;
                            boolean z15 = this.f1542e.m1232B() != dimensionBehaviour;
                            if (!z14) {
                                constraintWidget2.m1310x().m21093c();
                            }
                            if (!z15) {
                                constraintWidget2.m1308w().m21093c();
                            }
                            if (iM1378k == 0) {
                                if (z14 && constraintWidget2.m1253P() && z12 && c0296iM1221f.m21094d() && c0296iM1221f2.m21094d()) {
                                    iM1378k = (int) (c0296iM1221f2.m1378k() - c0296iM1221f.m1378k());
                                    constraintWidget2.m1310x().m21090h(iM1378k);
                                    childMeasureSpec = ViewGroup.getChildMeasureSpec(i9, paddingLeft, iM1378k);
                                    z8 = false;
                                    if (i20 != 0) {
                                    }
                                    childAt2.measure(childMeasureSpec, childMeasureSpec2);
                                    constraintWidget2.m1315z0(iM1378k != i14);
                                    constraintWidget2.m1269c0(i15 != i14);
                                    int measuredWidth2 = childAt2.getMeasuredWidth();
                                    int measuredHeight2 = childAt2.getMeasuredHeight();
                                    constraintWidget2.m1313y0(measuredWidth2);
                                    constraintWidget2.m1267b0(measuredHeight2);
                                    if (z8) {
                                    }
                                    if (z9) {
                                    }
                                    if (z14) {
                                    }
                                    if (z15) {
                                    }
                                    if (!c0298a2.f1581X) {
                                    }
                                } else {
                                    childMeasureSpec = ViewGroup.getChildMeasureSpec(i9, paddingLeft, -2);
                                    z8 = true;
                                    z14 = false;
                                    if (i20 != 0) {
                                        if (z15 && constraintWidget2.m1252O() && z13 && c0296iM1221f3.m21094d() && c0296iM1221f4.m21094d()) {
                                            int iM1378k2 = (int) (c0296iM1221f4.m1378k() - c0296iM1221f3.m1378k());
                                            constraintWidget2.m1308w().m21090h(iM1378k2);
                                            i15 = iM1378k2;
                                            z9 = false;
                                            childMeasureSpec2 = ViewGroup.getChildMeasureSpec(i10, paddingTop, iM1378k2);
                                            i14 = -2;
                                        } else {
                                            i14 = -2;
                                            i15 = i20;
                                            z9 = true;
                                            childMeasureSpec2 = ViewGroup.getChildMeasureSpec(i10, paddingTop, -2);
                                            z15 = false;
                                        }
                                    } else if (i20 == -1) {
                                        i15 = i20;
                                        childMeasureSpec2 = ViewGroup.getChildMeasureSpec(i10, paddingTop, -1);
                                        i14 = -2;
                                        z9 = false;
                                    } else {
                                        i14 = -2;
                                        z9 = i20 == -2;
                                        i15 = i20;
                                        childMeasureSpec2 = ViewGroup.getChildMeasureSpec(i10, paddingTop, i20);
                                    }
                                    childAt2.measure(childMeasureSpec, childMeasureSpec2);
                                    constraintWidget2.m1315z0(iM1378k != i14);
                                    constraintWidget2.m1269c0(i15 != i14);
                                    int measuredWidth22 = childAt2.getMeasuredWidth();
                                    int measuredHeight22 = childAt2.getMeasuredHeight();
                                    constraintWidget2.m1313y0(measuredWidth22);
                                    constraintWidget2.m1267b0(measuredHeight22);
                                    if (z8) {
                                        constraintWidget2.m1233B0(measuredWidth22);
                                    }
                                    if (z9) {
                                        constraintWidget2.m1231A0(measuredHeight22);
                                    }
                                    if (z14) {
                                        constraintWidget2.m1310x().m21089g();
                                    } else {
                                        constraintWidget2.m1310x().m21090h(measuredWidth22);
                                    }
                                    if (z15) {
                                        constraintWidget2.m1308w().m21089g();
                                    } else {
                                        constraintWidget2.m1308w().m21090h(measuredHeight22);
                                    }
                                    if (!c0298a2.f1581X) {
                                        int baseline2 = childAt2.getBaseline();
                                        c10 = 65535;
                                        if (baseline2 != -1) {
                                            constraintWidget2.m1259V(baseline2);
                                        }
                                    }
                                }
                            } else if (iM1378k == -1) {
                                childMeasureSpec = ViewGroup.getChildMeasureSpec(i9, paddingLeft, -1);
                                z8 = false;
                                if (i20 != 0) {
                                }
                                childAt2.measure(childMeasureSpec, childMeasureSpec2);
                                constraintWidget2.m1315z0(iM1378k != i14);
                                constraintWidget2.m1269c0(i15 != i14);
                                int measuredWidth222 = childAt2.getMeasuredWidth();
                                int measuredHeight222 = childAt2.getMeasuredHeight();
                                constraintWidget2.m1313y0(measuredWidth222);
                                constraintWidget2.m1267b0(measuredHeight222);
                                if (z8) {
                                }
                                if (z9) {
                                }
                                if (z14) {
                                }
                                if (z15) {
                                }
                                if (!c0298a2.f1581X) {
                                }
                            } else {
                                z8 = iM1378k == -2;
                                childMeasureSpec = ViewGroup.getChildMeasureSpec(i9, paddingLeft, iM1378k);
                                if (i20 != 0) {
                                }
                                childAt2.measure(childMeasureSpec, childMeasureSpec2);
                                constraintWidget2.m1315z0(iM1378k != i14);
                                constraintWidget2.m1269c0(i15 != i14);
                                int measuredWidth2222 = childAt2.getMeasuredWidth();
                                int measuredHeight2222 = childAt2.getMeasuredHeight();
                                constraintWidget2.m1313y0(measuredWidth2222);
                                constraintWidget2.m1267b0(measuredHeight2222);
                                if (z8) {
                                }
                                if (z9) {
                                }
                                if (z14) {
                                }
                                if (z15) {
                                }
                                if (!c0298a2.f1581X) {
                                }
                            }
                        }
                        c10 = 65535;
                    }
                }
            }
            i19++;
            i12 = i14;
            childCount = i13;
            i11 = 8;
            c9 = c10;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r25v0, types: [android.view.View, android.view.ViewGroup, androidx.constraintlayout.widget.ConstraintLayout] */
    /* JADX WARN: Type inference failed for: r3v0 */
    /* JADX WARN: Type inference failed for: r3v1, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r3v12 */
    /* JADX WARN: Type inference failed for: r3v13 */
    /* JADX WARN: Type inference failed for: r3v16 */
    /* JADX WARN: Type inference failed for: r3v2 */
    /* JADX WARN: Type inference failed for: r3v22 */
    /* JADX WARN: Type inference failed for: r3v32 */
    /* renamed from: j */
    public final void m1404j() throws IllegalAccessException, Resources.NotFoundException, IllegalArgumentException {
        float f9;
        int i9;
        int i10;
        ConstraintWidget constraintWidgetM1398d;
        ConstraintWidget constraintWidgetM1398d2;
        ConstraintWidget constraintWidgetM1398d3;
        ConstraintWidget constraintWidgetM1398d4;
        int i11;
        boolean zIsInEditMode = isInEditMode();
        int childCount = getChildCount();
        ?? r32 = 0;
        if (zIsInEditMode) {
            for (int i12 = 0; i12 < childCount; i12++) {
                View childAt = getChildAt(i12);
                try {
                    String resourceName = getResources().getResourceName(childAt.getId());
                    m1405k(0, resourceName, Integer.valueOf(childAt.getId()));
                    int iIndexOf = resourceName.indexOf(47);
                    if (iIndexOf != -1) {
                        resourceName = resourceName.substring(iIndexOf + 1);
                    }
                    m1398d(childAt.getId()).m1261X(resourceName);
                } catch (Resources.NotFoundException unused) {
                }
            }
        }
        for (int i13 = 0; i13 < childCount; i13++) {
            ConstraintWidget constraintWidgetM1400f = m1400f(getChildAt(i13));
            if (constraintWidgetM1400f != null) {
                constraintWidgetM1400f.mo1254Q();
            }
        }
        if (this.f1550m != -1) {
            for (int i14 = 0; i14 < childCount; i14++) {
                View childAt2 = getChildAt(i14);
                if (childAt2.getId() == this.f1550m && (childAt2 instanceof Constraints)) {
                    this.f1549l = ((Constraints) childAt2).getConstraintSet();
                }
            }
        }
        C0300a c0300a = this.f1549l;
        if (c0300a != null) {
            c0300a.m1418a(this);
        }
        this.f1542e.m21098M0();
        int size = this.f1540c.size();
        if (size > 0) {
            for (int i15 = 0; i15 < size; i15++) {
                this.f1540c.get(i15).mo1393e(this);
            }
        }
        for (int i16 = 0; i16 < childCount; i16++) {
            View childAt3 = getChildAt(i16);
            if (childAt3 instanceof Placeholder) {
                ((Placeholder) childAt3).m1416c(this);
            }
        }
        int i17 = 0;
        while (i17 < childCount) {
            View childAt4 = getChildAt(i17);
            ConstraintWidget constraintWidgetM1400f2 = m1400f(childAt4);
            if (constraintWidgetM1400f2 != null) {
                C0298a c0298a = (C0298a) childAt4.getLayoutParams();
                c0298a.m1410a();
                if (c0298a.f1609m0) {
                    c0298a.f1609m0 = r32;
                } else if (zIsInEditMode) {
                    try {
                        String resourceName2 = getResources().getResourceName(childAt4.getId());
                        m1405k(r32, resourceName2, Integer.valueOf(childAt4.getId()));
                        m1398d(childAt4.getId()).m1261X(resourceName2.substring(resourceName2.indexOf("id/") + 3));
                    } catch (Resources.NotFoundException unused2) {
                    }
                }
                constraintWidgetM1400f2.m1311x0(childAt4.getVisibility());
                if (c0298a.f1585a0) {
                    constraintWidgetM1400f2.m1311x0(8);
                }
                constraintWidgetM1400f2.m1260W(childAt4);
                this.f1542e.m21095I0(constraintWidgetM1400f2);
                if (!c0298a.f1580W || !c0298a.f1579V) {
                    this.f1541d.add(constraintWidgetM1400f2);
                }
                if (c0298a.f1582Y) {
                    C0294g c0294g = (C0294g) constraintWidgetM1400f2;
                    int i18 = c0298a.f1601i0;
                    int i19 = c0298a.f1603j0;
                    float f10 = c0298a.f1605k0;
                    if (f10 != -1.0f) {
                        c0294g.m1365L0(f10);
                    } else if (i18 != -1) {
                        c0294g.m1363J0(i18);
                    } else if (i19 != -1) {
                        c0294g.m1364K0(i19);
                    }
                } else if (c0298a.f1590d != -1 || c0298a.f1592e != -1 || c0298a.f1594f != -1 || c0298a.f1596g != -1 || c0298a.f1613q != -1 || c0298a.f1612p != -1 || c0298a.f1614r != -1 || c0298a.f1615s != -1 || c0298a.f1598h != -1 || c0298a.f1600i != -1 || c0298a.f1602j != -1 || c0298a.f1604k != -1 || c0298a.f1606l != -1 || c0298a.f1574Q != -1 || c0298a.f1575R != -1 || c0298a.f1608m != -1 || ((ViewGroup.MarginLayoutParams) c0298a).width == -1 || ((ViewGroup.MarginLayoutParams) c0298a).height == -1) {
                    int i20 = c0298a.f1587b0;
                    int i21 = c0298a.f1589c0;
                    int i22 = c0298a.f1591d0;
                    int i23 = c0298a.f1593e0;
                    int i24 = c0298a.f1595f0;
                    int i25 = c0298a.f1597g0;
                    float f11 = c0298a.f1599h0;
                    int i26 = c0298a.f1608m;
                    if (i26 != -1) {
                        ConstraintWidget constraintWidgetM1398d5 = m1398d(i26);
                        if (constraintWidgetM1398d5 != null) {
                            constraintWidgetM1400f2.m1274f(constraintWidgetM1398d5, c0298a.f1611o, c0298a.f1610n);
                        }
                    } else {
                        if (i20 != -1) {
                            ConstraintWidget constraintWidgetM1398d6 = m1398d(i20);
                            if (constraintWidgetM1398d6 != null) {
                                ConstraintAnchor.Type type = ConstraintAnchor.Type.LEFT;
                                f9 = f11;
                                i9 = i25;
                                i10 = i23;
                                constraintWidgetM1400f2.m1247J(type, constraintWidgetM1398d6, type, ((ViewGroup.MarginLayoutParams) c0298a).leftMargin, i24);
                            } else {
                                f9 = f11;
                                i9 = i25;
                                i10 = i23;
                            }
                        } else {
                            f9 = f11;
                            i9 = i25;
                            i10 = i23;
                            if (i21 != -1 && (constraintWidgetM1398d = m1398d(i21)) != null) {
                                constraintWidgetM1400f2.m1247J(ConstraintAnchor.Type.LEFT, constraintWidgetM1398d, ConstraintAnchor.Type.RIGHT, ((ViewGroup.MarginLayoutParams) c0298a).leftMargin, i24);
                            }
                        }
                        if (i22 != -1) {
                            ConstraintWidget constraintWidgetM1398d7 = m1398d(i22);
                            if (constraintWidgetM1398d7 != null) {
                                constraintWidgetM1400f2.m1247J(ConstraintAnchor.Type.RIGHT, constraintWidgetM1398d7, ConstraintAnchor.Type.LEFT, ((ViewGroup.MarginLayoutParams) c0298a).rightMargin, i9);
                            }
                        } else {
                            int i27 = i10;
                            if (i27 != -1 && (constraintWidgetM1398d2 = m1398d(i27)) != null) {
                                ConstraintAnchor.Type type2 = ConstraintAnchor.Type.RIGHT;
                                constraintWidgetM1400f2.m1247J(type2, constraintWidgetM1398d2, type2, ((ViewGroup.MarginLayoutParams) c0298a).rightMargin, i9);
                            }
                        }
                        int i28 = c0298a.f1598h;
                        if (i28 != -1) {
                            ConstraintWidget constraintWidgetM1398d8 = m1398d(i28);
                            if (constraintWidgetM1398d8 != null) {
                                ConstraintAnchor.Type type3 = ConstraintAnchor.Type.TOP;
                                constraintWidgetM1400f2.m1247J(type3, constraintWidgetM1398d8, type3, ((ViewGroup.MarginLayoutParams) c0298a).topMargin, c0298a.f1617u);
                            }
                        } else {
                            int i29 = c0298a.f1600i;
                            if (i29 != -1 && (constraintWidgetM1398d3 = m1398d(i29)) != null) {
                                constraintWidgetM1400f2.m1247J(ConstraintAnchor.Type.TOP, constraintWidgetM1398d3, ConstraintAnchor.Type.BOTTOM, ((ViewGroup.MarginLayoutParams) c0298a).topMargin, c0298a.f1617u);
                            }
                        }
                        int i30 = c0298a.f1602j;
                        if (i30 != -1) {
                            ConstraintWidget constraintWidgetM1398d9 = m1398d(i30);
                            if (constraintWidgetM1398d9 != null) {
                                constraintWidgetM1400f2.m1247J(ConstraintAnchor.Type.BOTTOM, constraintWidgetM1398d9, ConstraintAnchor.Type.TOP, ((ViewGroup.MarginLayoutParams) c0298a).bottomMargin, c0298a.f1619w);
                            }
                        } else {
                            int i31 = c0298a.f1604k;
                            if (i31 != -1 && (constraintWidgetM1398d4 = m1398d(i31)) != null) {
                                ConstraintAnchor.Type type4 = ConstraintAnchor.Type.BOTTOM;
                                constraintWidgetM1400f2.m1247J(type4, constraintWidgetM1398d4, type4, ((ViewGroup.MarginLayoutParams) c0298a).bottomMargin, c0298a.f1619w);
                            }
                        }
                        int i32 = c0298a.f1606l;
                        if (i32 != -1) {
                            View view = this.f1539b.get(i32);
                            ConstraintWidget constraintWidgetM1398d10 = m1398d(c0298a.f1606l);
                            if (constraintWidgetM1398d10 != null && view != null && (view.getLayoutParams() instanceof C0298a)) {
                                C0298a c0298a2 = (C0298a) view.getLayoutParams();
                                c0298a.f1581X = true;
                                c0298a2.f1581X = true;
                                ConstraintAnchor.Type type5 = ConstraintAnchor.Type.BASELINE;
                                constraintWidgetM1400f2.mo1278h(type5).m1216a(constraintWidgetM1398d10.mo1278h(type5), 0, -1, ConstraintAnchor.Strength.STRONG, 0, true);
                                constraintWidgetM1400f2.mo1278h(ConstraintAnchor.Type.TOP).m1228m();
                                constraintWidgetM1400f2.mo1278h(ConstraintAnchor.Type.BOTTOM).m1228m();
                            }
                        }
                        if (f9 >= BitmapDescriptorFactory.HUE_RED && f9 != 0.5f) {
                            constraintWidgetM1400f2.m1271d0(f9);
                        }
                        float f12 = c0298a.f1558A;
                        if (f12 >= BitmapDescriptorFactory.HUE_RED && f12 != 0.5f) {
                            constraintWidgetM1400f2.m1299r0(f12);
                        }
                    }
                    if (zIsInEditMode && ((i11 = c0298a.f1574Q) != -1 || c0298a.f1575R != -1)) {
                        constraintWidgetM1400f2.m1293o0(i11, c0298a.f1575R);
                    }
                    if (c0298a.f1579V) {
                        constraintWidgetM1400f2.m1277g0(ConstraintWidget.DimensionBehaviour.FIXED);
                        constraintWidgetM1400f2.m1313y0(((ViewGroup.MarginLayoutParams) c0298a).width);
                    } else if (((ViewGroup.MarginLayoutParams) c0298a).width == -1) {
                        constraintWidgetM1400f2.m1277g0(ConstraintWidget.DimensionBehaviour.MATCH_PARENT);
                        constraintWidgetM1400f2.mo1278h(ConstraintAnchor.Type.LEFT).f1339e = ((ViewGroup.MarginLayoutParams) c0298a).leftMargin;
                        constraintWidgetM1400f2.mo1278h(ConstraintAnchor.Type.RIGHT).f1339e = ((ViewGroup.MarginLayoutParams) c0298a).rightMargin;
                    } else {
                        constraintWidgetM1400f2.m1277g0(ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT);
                        constraintWidgetM1400f2.m1313y0(0);
                    }
                    if (c0298a.f1580W) {
                        r32 = 0;
                        constraintWidgetM1400f2.m1305u0(ConstraintWidget.DimensionBehaviour.FIXED);
                        constraintWidgetM1400f2.m1267b0(((ViewGroup.MarginLayoutParams) c0298a).height);
                    } else if (((ViewGroup.MarginLayoutParams) c0298a).height == -1) {
                        constraintWidgetM1400f2.m1305u0(ConstraintWidget.DimensionBehaviour.MATCH_PARENT);
                        constraintWidgetM1400f2.mo1278h(ConstraintAnchor.Type.TOP).f1339e = ((ViewGroup.MarginLayoutParams) c0298a).topMargin;
                        constraintWidgetM1400f2.mo1278h(ConstraintAnchor.Type.BOTTOM).f1339e = ((ViewGroup.MarginLayoutParams) c0298a).bottomMargin;
                        r32 = 0;
                    } else {
                        constraintWidgetM1400f2.m1305u0(ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT);
                        r32 = 0;
                        constraintWidgetM1400f2.m1267b0(0);
                    }
                    String str = c0298a.f1559B;
                    if (str != null) {
                        constraintWidgetM1400f2.m1262Y(str);
                    }
                    constraintWidgetM1400f2.m1281i0(c0298a.f1562E);
                    constraintWidgetM1400f2.m1309w0(c0298a.f1563F);
                    constraintWidgetM1400f2.m1273e0(c0298a.f1564G);
                    constraintWidgetM1400f2.m1301s0(c0298a.f1565H);
                    constraintWidgetM1400f2.m1279h0(c0298a.f1566I, c0298a.f1568K, c0298a.f1570M, c0298a.f1572O);
                    constraintWidgetM1400f2.m1307v0(c0298a.f1567J, c0298a.f1569L, c0298a.f1571N, c0298a.f1573P);
                }
            }
            i17++;
            r32 = r32;
        }
    }

    /* renamed from: k */
    public void m1405k(int i9, Object obj, Object obj2) {
        if (i9 == 0 && (obj instanceof String) && (obj2 instanceof Integer)) {
            if (this.f1551n == null) {
                this.f1551n = new HashMap<>();
            }
            String strSubstring = (String) obj;
            int iIndexOf = strSubstring.indexOf("/");
            if (iIndexOf != -1) {
                strSubstring = strSubstring.substring(iIndexOf + 1);
            }
            this.f1551n.put(strSubstring, Integer.valueOf(((Integer) obj2).intValue()));
        }
    }

    /* renamed from: l */
    public final void m1406l(int i9, int i10) {
        int iMin;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour;
        int mode = View.MeasureSpec.getMode(i9);
        int size = View.MeasureSpec.getSize(i9);
        int mode2 = View.MeasureSpec.getMode(i10);
        int size2 = View.MeasureSpec.getSize(i10);
        int paddingTop = getPaddingTop() + getPaddingBottom();
        int paddingLeft = getPaddingLeft() + getPaddingRight();
        ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = ConstraintWidget.DimensionBehaviour.FIXED;
        getLayoutParams();
        if (mode != Integer.MIN_VALUE) {
            if (mode == 0) {
                dimensionBehaviour = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
            } else if (mode != 1073741824) {
                dimensionBehaviour = dimensionBehaviour2;
            } else {
                iMin = Math.min(this.f1545h, size) - paddingLeft;
                dimensionBehaviour = dimensionBehaviour2;
            }
            iMin = 0;
        } else {
            iMin = size;
            dimensionBehaviour = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
        }
        if (mode2 != Integer.MIN_VALUE) {
            if (mode2 == 0) {
                dimensionBehaviour2 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
            } else if (mode2 == 1073741824) {
                size2 = Math.min(this.f1546i, size2) - paddingTop;
            }
            size2 = 0;
        } else {
            dimensionBehaviour2 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
        }
        this.f1542e.m1289m0(0);
        this.f1542e.m1287l0(0);
        this.f1542e.m1277g0(dimensionBehaviour);
        this.f1542e.m1313y0(iMin);
        this.f1542e.m1305u0(dimensionBehaviour2);
        this.f1542e.m1267b0(size2);
        this.f1542e.m1289m0((this.f1543f - getPaddingLeft()) - getPaddingRight());
        this.f1542e.m1287l0((this.f1544g - getPaddingTop()) - getPaddingBottom());
    }

    /* renamed from: m */
    public void m1407m(String str) {
        this.f1542e.mo1335K0();
    }

    /* renamed from: n */
    public final void m1408n() throws IllegalAccessException, Resources.NotFoundException, IllegalArgumentException {
        int childCount = getChildCount();
        boolean z8 = false;
        int i9 = 0;
        while (true) {
            if (i9 >= childCount) {
                break;
            }
            if (getChildAt(i9).isLayoutRequested()) {
                z8 = true;
                break;
            }
            i9++;
        }
        if (z8) {
            this.f1541d.clear();
            m1404j();
        }
    }

    /* renamed from: o */
    public final void m1409o() {
        int childCount = getChildCount();
        for (int i9 = 0; i9 < childCount; i9++) {
            View childAt = getChildAt(i9);
            if (childAt instanceof Placeholder) {
                ((Placeholder) childAt).m1415b(this);
            }
        }
        int size = this.f1540c.size();
        if (size > 0) {
            for (int i10 = 0; i10 < size; i10++) {
                this.f1540c.get(i10).m1392d(this);
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean z8, int i9, int i10, int i11, int i12) {
        View content;
        int childCount = getChildCount();
        boolean zIsInEditMode = isInEditMode();
        for (int i13 = 0; i13 < childCount; i13++) {
            View childAt = getChildAt(i13);
            C0298a c0298a = (C0298a) childAt.getLayoutParams();
            ConstraintWidget constraintWidget = c0298a.f1607l0;
            if ((childAt.getVisibility() != 8 || c0298a.f1582Y || c0298a.f1583Z || zIsInEditMode) && !c0298a.f1585a0) {
                int iM1294p = constraintWidget.m1294p();
                int iM1296q = constraintWidget.m1296q();
                int iM1236D = constraintWidget.m1236D() + iM1294p;
                int iM1298r = constraintWidget.m1298r() + iM1296q;
                childAt.layout(iM1294p, iM1296q, iM1236D, iM1298r);
                if ((childAt instanceof Placeholder) && (content = ((Placeholder) childAt).getContent()) != null) {
                    content.setVisibility(0);
                    content.layout(iM1294p, iM1296q, iM1236D, iM1298r);
                }
            }
        }
        int size = this.f1540c.size();
        if (size > 0) {
            for (int i14 = 0; i14 < size; i14++) {
                this.f1540c.get(i14).mo1391c(this);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:58:0x0117 A[PHI: r11
      0x0117: PHI (r11v1 int) = (r11v0 int), (r11v10 int), (r11v10 int) binds: [B:38:0x00ca, B:54:0x010a, B:56:0x010e] A[DONT_GENERATE, DONT_INLINE]] */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void onMeasure(int i9, int i10) throws IllegalAccessException, Resources.NotFoundException, IllegalArgumentException {
        boolean z8;
        int i11;
        boolean z9;
        boolean z10;
        int i12;
        int i13;
        int baseline;
        int i14;
        ConstraintLayout constraintLayout = this;
        int i15 = i9;
        System.currentTimeMillis();
        int mode = View.MeasureSpec.getMode(i9);
        int size = View.MeasureSpec.getSize(i9);
        int mode2 = View.MeasureSpec.getMode(i10);
        int size2 = View.MeasureSpec.getSize(i10);
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        constraintLayout.f1542e.m1235C0(paddingLeft);
        constraintLayout.f1542e.m1237D0(paddingTop);
        constraintLayout.f1542e.m1285k0(constraintLayout.f1545h);
        constraintLayout.f1542e.m1283j0(constraintLayout.f1546i);
        int iCombineMeasuredStates = 0;
        constraintLayout.f1542e.m1352d1(getLayoutDirection() == 1);
        m1406l(i9, i10);
        int iM1236D = constraintLayout.f1542e.m1236D();
        int iM1298r = constraintLayout.f1542e.m1298r();
        if (constraintLayout.f1547j) {
            constraintLayout.f1547j = false;
            m1408n();
            z8 = true;
        } else {
            z8 = false;
        }
        boolean z11 = (constraintLayout.f1548k & 8) == 8;
        if (z11) {
            constraintLayout.f1542e.m1349a1();
            constraintLayout.f1542e.m1347Y0(iM1236D, iM1298r);
            m1403i(i9, i10);
        } else {
            m1402h(i9, i10);
        }
        m1409o();
        if (getChildCount() > 0 && z8) {
            C0288a.m1316a(constraintLayout.f1542e);
        }
        C0292e c0292e = constraintLayout.f1542e;
        if (c0292e.f1471I0) {
            if (c0292e.f1472J0 && mode == Integer.MIN_VALUE) {
                int i16 = c0292e.f1474L0;
                if (i16 < size) {
                    c0292e.m1313y0(i16);
                }
                constraintLayout.f1542e.m1277g0(ConstraintWidget.DimensionBehaviour.FIXED);
            }
            C0292e c0292e2 = constraintLayout.f1542e;
            if (c0292e2.f1473K0 && mode2 == Integer.MIN_VALUE) {
                int i17 = c0292e2.f1475M0;
                if (i17 < size2) {
                    c0292e2.m1267b0(i17);
                }
                constraintLayout.f1542e.m1305u0(ConstraintWidget.DimensionBehaviour.FIXED);
            }
        }
        if ((constraintLayout.f1548k & 32) == 32) {
            int iM1236D2 = constraintLayout.f1542e.m1236D();
            int iM1298r2 = constraintLayout.f1542e.m1298r();
            if (constraintLayout.f1552o != iM1236D2 && mode == 1073741824) {
                C0288a.m1324i(constraintLayout.f1542e.f1470H0, 0, iM1236D2);
            }
            if (constraintLayout.f1553p != iM1298r2 && mode2 == 1073741824) {
                C0288a.m1324i(constraintLayout.f1542e.f1470H0, 1, iM1298r2);
            }
            C0292e c0292e3 = constraintLayout.f1542e;
            if (!c0292e3.f1472J0 || c0292e3.f1474L0 <= size) {
                iCombineMeasuredStates = 0;
            } else {
                iCombineMeasuredStates = 0;
                C0288a.m1324i(c0292e3.f1470H0, 0, size);
            }
            C0292e c0292e4 = constraintLayout.f1542e;
            if (!c0292e4.f1473K0 || c0292e4.f1475M0 <= size2) {
                i11 = 1;
            } else {
                i11 = 1;
                C0288a.m1324i(c0292e4.f1470H0, 1, size2);
            }
        }
        if (getChildCount() > 0) {
            constraintLayout.m1407m("First pass");
        }
        int size3 = constraintLayout.f1541d.size();
        int paddingBottom = paddingTop + getPaddingBottom();
        int paddingRight = paddingLeft + getPaddingRight();
        if (size3 > 0) {
            ConstraintWidget.DimensionBehaviour dimensionBehaviourM1300s = constraintLayout.f1542e.m1300s();
            ConstraintWidget.DimensionBehaviour dimensionBehaviour = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
            int i18 = dimensionBehaviourM1300s == dimensionBehaviour ? i11 : iCombineMeasuredStates;
            int i19 = constraintLayout.f1542e.m1232B() == dimensionBehaviour ? i11 : iCombineMeasuredStates;
            int iMax = Math.max(constraintLayout.f1542e.m1236D(), constraintLayout.f1543f);
            int iMax2 = Math.max(constraintLayout.f1542e.m1298r(), constraintLayout.f1544g);
            int i20 = iCombineMeasuredStates;
            int iMax3 = iMax;
            int i21 = i20;
            while (i21 < size3) {
                ConstraintWidget constraintWidget = constraintLayout.f1541d.get(i21);
                int i22 = size3;
                View view = (View) constraintWidget.m1288m();
                if (view == null) {
                    i13 = iM1236D;
                    i12 = iM1298r;
                } else {
                    i12 = iM1298r;
                    C0298a c0298a = (C0298a) view.getLayoutParams();
                    i13 = iM1236D;
                    if (!c0298a.f1583Z && !c0298a.f1582Y && view.getVisibility() != 8 && (!z11 || !constraintWidget.m1310x().m21094d() || !constraintWidget.m1308w().m21094d())) {
                        int i23 = ((ViewGroup.MarginLayoutParams) c0298a).width;
                        int childMeasureSpec = (i23 == -2 && c0298a.f1579V) ? ViewGroup.getChildMeasureSpec(i15, paddingRight, i23) : View.MeasureSpec.makeMeasureSpec(constraintWidget.m1236D(), 1073741824);
                        int i24 = ((ViewGroup.MarginLayoutParams) c0298a).height;
                        view.measure(childMeasureSpec, (i24 == -2 && c0298a.f1580W) ? ViewGroup.getChildMeasureSpec(i10, paddingBottom, i24) : View.MeasureSpec.makeMeasureSpec(constraintWidget.m1298r(), 1073741824));
                        int measuredWidth = view.getMeasuredWidth();
                        int measuredHeight = view.getMeasuredHeight();
                        if (measuredWidth != constraintWidget.m1236D()) {
                            constraintWidget.m1313y0(measuredWidth);
                            if (z11) {
                                constraintWidget.m1310x().m21090h(measuredWidth);
                            }
                            if (i18 != 0) {
                                i14 = iMax3;
                                if (constraintWidget.m1312y() > i14) {
                                    iMax3 = Math.max(i14, constraintWidget.m1312y() + constraintWidget.mo1278h(ConstraintAnchor.Type.RIGHT).m1219d());
                                }
                                i20 = 1;
                            } else {
                                i14 = iMax3;
                            }
                            iMax3 = i14;
                            i20 = 1;
                        }
                        if (measuredHeight != constraintWidget.m1298r()) {
                            constraintWidget.m1267b0(measuredHeight);
                            if (z11) {
                                constraintWidget.m1308w().m21090h(measuredHeight);
                            }
                            if (i19 != 0 && constraintWidget.m1286l() > iMax2) {
                                iMax2 = Math.max(iMax2, constraintWidget.m1286l() + constraintWidget.mo1278h(ConstraintAnchor.Type.BOTTOM).m1219d());
                            }
                            i20 = 1;
                        }
                        if (c0298a.f1581X && (baseline = view.getBaseline()) != -1 && baseline != constraintWidget.m1282j()) {
                            constraintWidget.m1259V(baseline);
                            i20 = 1;
                        }
                        iCombineMeasuredStates = View.combineMeasuredStates(iCombineMeasuredStates, view.getMeasuredState());
                    }
                    i21++;
                    constraintLayout = this;
                    i15 = i9;
                    iM1236D = i13;
                    size3 = i22;
                    iM1298r = i12;
                }
                iMax3 = iMax3;
                i21++;
                constraintLayout = this;
                i15 = i9;
                iM1236D = i13;
                size3 = i22;
                iM1298r = i12;
            }
            int i25 = size3;
            int i26 = iM1236D;
            int i27 = iM1298r;
            int i28 = iMax3;
            constraintLayout = this;
            if (i20 != 0) {
                constraintLayout.f1542e.m1313y0(i26);
                constraintLayout.f1542e.m1267b0(i27);
                if (z11) {
                    constraintLayout.f1542e.m1353e1();
                }
                constraintLayout.m1407m("2nd pass");
                if (constraintLayout.f1542e.m1236D() < i28) {
                    constraintLayout.f1542e.m1313y0(i28);
                    z9 = true;
                } else {
                    z9 = false;
                }
                if (constraintLayout.f1542e.m1298r() < iMax2) {
                    constraintLayout.f1542e.m1267b0(iMax2);
                    z10 = true;
                } else {
                    z10 = z9;
                }
                if (z10) {
                    constraintLayout.m1407m("3rd pass");
                }
            }
            for (int i29 = 0; i29 < i25; i29++) {
                ConstraintWidget constraintWidget2 = constraintLayout.f1541d.get(i29);
                View view2 = (View) constraintWidget2.m1288m();
                if (view2 != null && (view2.getMeasuredWidth() != constraintWidget2.m1236D() || view2.getMeasuredHeight() != constraintWidget2.m1298r())) {
                    if (constraintWidget2.m1234C() != 8) {
                        view2.measure(View.MeasureSpec.makeMeasureSpec(constraintWidget2.m1236D(), 1073741824), View.MeasureSpec.makeMeasureSpec(constraintWidget2.m1298r(), 1073741824));
                    }
                }
            }
        } else {
            iCombineMeasuredStates = 0;
        }
        int iM1236D3 = constraintLayout.f1542e.m1236D() + paddingRight;
        int iM1298r3 = constraintLayout.f1542e.m1298r() + paddingBottom;
        int iResolveSizeAndState = View.resolveSizeAndState(iM1236D3, i9, iCombineMeasuredStates);
        int iResolveSizeAndState2 = View.resolveSizeAndState(iM1298r3, i10, iCombineMeasuredStates << 16) & 16777215;
        int iMin = Math.min(constraintLayout.f1545h, iResolveSizeAndState & 16777215);
        int iMin2 = Math.min(constraintLayout.f1546i, iResolveSizeAndState2);
        if (constraintLayout.f1542e.m1344V0()) {
            iMin |= C3322C.DEFAULT_MUXED_BUFFER_SIZE;
        }
        if (constraintLayout.f1542e.m1342T0()) {
            iMin2 |= C3322C.DEFAULT_MUXED_BUFFER_SIZE;
        }
        constraintLayout.setMeasuredDimension(iMin, iMin2);
        constraintLayout.f1552o = iMin;
        constraintLayout.f1553p = iMin2;
    }

    @Override // android.view.ViewGroup
    public void onViewAdded(View view) {
        super.onViewAdded(view);
        ConstraintWidget constraintWidgetM1400f = m1400f(view);
        if ((view instanceof Guideline) && !(constraintWidgetM1400f instanceof C0294g)) {
            C0298a c0298a = (C0298a) view.getLayoutParams();
            C0294g c0294g = new C0294g();
            c0298a.f1607l0 = c0294g;
            c0298a.f1582Y = true;
            c0294g.m1366M0(c0298a.f1576S);
        }
        if (view instanceof ConstraintHelper) {
            ConstraintHelper constraintHelper = (ConstraintHelper) view;
            constraintHelper.m1394f();
            ((C0298a) view.getLayoutParams()).f1583Z = true;
            if (!this.f1540c.contains(constraintHelper)) {
                this.f1540c.add(constraintHelper);
            }
        }
        this.f1539b.put(view.getId(), view);
        this.f1547j = true;
    }

    @Override // android.view.ViewGroup
    public void onViewRemoved(View view) {
        super.onViewRemoved(view);
        this.f1539b.remove(view.getId());
        ConstraintWidget constraintWidgetM1400f = m1400f(view);
        this.f1542e.m21097L0(constraintWidgetM1400f);
        this.f1540c.remove(view);
        this.f1541d.remove(constraintWidgetM1400f);
        this.f1547j = true;
    }

    @Override // android.view.ViewGroup, android.view.ViewManager
    public void removeView(View view) {
        super.removeView(view);
    }

    @Override // android.view.View, android.view.ViewParent
    public void requestLayout() {
        super.requestLayout();
        this.f1547j = true;
        this.f1552o = -1;
        this.f1553p = -1;
        this.f1554q = -1;
        this.f1555r = -1;
        this.f1556s = 0;
        this.f1557t = 0;
    }

    public void setConstraintSet(C0300a c0300a) {
        this.f1549l = c0300a;
    }

    @Override // android.view.View
    public void setId(int i9) {
        this.f1539b.remove(getId());
        super.setId(i9);
        this.f1539b.put(getId(), this);
    }

    public void setMaxHeight(int i9) {
        if (i9 == this.f1546i) {
            return;
        }
        this.f1546i = i9;
        requestLayout();
    }

    public void setMaxWidth(int i9) {
        if (i9 == this.f1545h) {
            return;
        }
        this.f1545h = i9;
        requestLayout();
    }

    public void setMinHeight(int i9) {
        if (i9 == this.f1544g) {
            return;
        }
        this.f1544g = i9;
        requestLayout();
    }

    public void setMinWidth(int i9) {
        if (i9 == this.f1543f) {
            return;
        }
        this.f1543f = i9;
        requestLayout();
    }

    public void setOptimizationLevel(int i9) {
        this.f1542e.m1351c1(i9);
    }

    @Override // android.view.ViewGroup
    public boolean shouldDelayChildPressedState() {
        return false;
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return new C0298a(layoutParams);
    }

    public ConstraintLayout(Context context, AttributeSet attributeSet, int i9) throws XmlPullParserException, IOException {
        super(context, attributeSet, i9);
        this.f1539b = new SparseArray<>();
        this.f1540c = new ArrayList<>(4);
        this.f1541d = new ArrayList<>(100);
        this.f1542e = new C0292e();
        this.f1543f = 0;
        this.f1544g = 0;
        this.f1545h = Integer.MAX_VALUE;
        this.f1546i = Integer.MAX_VALUE;
        this.f1547j = true;
        this.f1548k = 7;
        this.f1549l = null;
        this.f1550m = -1;
        this.f1551n = new HashMap<>();
        this.f1552o = -1;
        this.f1553p = -1;
        this.f1554q = -1;
        this.f1555r = -1;
        this.f1556s = 0;
        this.f1557t = 0;
        m1401g(attributeSet);
    }

    /* renamed from: androidx.constraintlayout.widget.ConstraintLayout$a */
    public static class C0298a extends ViewGroup.MarginLayoutParams {

        /* renamed from: A */
        public float f1558A;

        /* renamed from: B */
        public String f1559B;

        /* renamed from: C */
        public float f1560C;

        /* renamed from: D */
        public int f1561D;

        /* renamed from: E */
        public float f1562E;

        /* renamed from: F */
        public float f1563F;

        /* renamed from: G */
        public int f1564G;

        /* renamed from: H */
        public int f1565H;

        /* renamed from: I */
        public int f1566I;

        /* renamed from: J */
        public int f1567J;

        /* renamed from: K */
        public int f1568K;

        /* renamed from: L */
        public int f1569L;

        /* renamed from: M */
        public int f1570M;

        /* renamed from: N */
        public int f1571N;

        /* renamed from: O */
        public float f1572O;

        /* renamed from: P */
        public float f1573P;

        /* renamed from: Q */
        public int f1574Q;

        /* renamed from: R */
        public int f1575R;

        /* renamed from: S */
        public int f1576S;

        /* renamed from: T */
        public boolean f1577T;

        /* renamed from: U */
        public boolean f1578U;

        /* renamed from: V */
        public boolean f1579V;

        /* renamed from: W */
        public boolean f1580W;

        /* renamed from: X */
        public boolean f1581X;

        /* renamed from: Y */
        public boolean f1582Y;

        /* renamed from: Z */
        public boolean f1583Z;

        /* renamed from: a */
        public int f1584a;

        /* renamed from: a0 */
        public boolean f1585a0;

        /* renamed from: b */
        public int f1586b;

        /* renamed from: b0 */
        public int f1587b0;

        /* renamed from: c */
        public float f1588c;

        /* renamed from: c0 */
        public int f1589c0;

        /* renamed from: d */
        public int f1590d;

        /* renamed from: d0 */
        public int f1591d0;

        /* renamed from: e */
        public int f1592e;

        /* renamed from: e0 */
        public int f1593e0;

        /* renamed from: f */
        public int f1594f;

        /* renamed from: f0 */
        public int f1595f0;

        /* renamed from: g */
        public int f1596g;

        /* renamed from: g0 */
        public int f1597g0;

        /* renamed from: h */
        public int f1598h;

        /* renamed from: h0 */
        public float f1599h0;

        /* renamed from: i */
        public int f1600i;

        /* renamed from: i0 */
        public int f1601i0;

        /* renamed from: j */
        public int f1602j;

        /* renamed from: j0 */
        public int f1603j0;

        /* renamed from: k */
        public int f1604k;

        /* renamed from: k0 */
        public float f1605k0;

        /* renamed from: l */
        public int f1606l;

        /* renamed from: l0 */
        public ConstraintWidget f1607l0;

        /* renamed from: m */
        public int f1608m;

        /* renamed from: m0 */
        public boolean f1609m0;

        /* renamed from: n */
        public int f1610n;

        /* renamed from: o */
        public float f1611o;

        /* renamed from: p */
        public int f1612p;

        /* renamed from: q */
        public int f1613q;

        /* renamed from: r */
        public int f1614r;

        /* renamed from: s */
        public int f1615s;

        /* renamed from: t */
        public int f1616t;

        /* renamed from: u */
        public int f1617u;

        /* renamed from: v */
        public int f1618v;

        /* renamed from: w */
        public int f1619w;

        /* renamed from: x */
        public int f1620x;

        /* renamed from: y */
        public int f1621y;

        /* renamed from: z */
        public float f1622z;

        /* renamed from: androidx.constraintlayout.widget.ConstraintLayout$a$a */
        public static class a {

            /* renamed from: a */
            public static final SparseIntArray f1623a;

            static {
                SparseIntArray sparseIntArray = new SparseIntArray();
                f1623a = sparseIntArray;
                sparseIntArray.append(C5867b.ConstraintLayout_Layout_layout_constraintLeft_toLeftOf, 8);
                sparseIntArray.append(C5867b.ConstraintLayout_Layout_layout_constraintLeft_toRightOf, 9);
                sparseIntArray.append(C5867b.ConstraintLayout_Layout_layout_constraintRight_toLeftOf, 10);
                sparseIntArray.append(C5867b.ConstraintLayout_Layout_layout_constraintRight_toRightOf, 11);
                sparseIntArray.append(C5867b.ConstraintLayout_Layout_layout_constraintTop_toTopOf, 12);
                sparseIntArray.append(C5867b.ConstraintLayout_Layout_layout_constraintTop_toBottomOf, 13);
                sparseIntArray.append(C5867b.ConstraintLayout_Layout_layout_constraintBottom_toTopOf, 14);
                sparseIntArray.append(C5867b.ConstraintLayout_Layout_layout_constraintBottom_toBottomOf, 15);
                sparseIntArray.append(C5867b.ConstraintLayout_Layout_layout_constraintBaseline_toBaselineOf, 16);
                sparseIntArray.append(C5867b.ConstraintLayout_Layout_layout_constraintCircle, 2);
                sparseIntArray.append(C5867b.ConstraintLayout_Layout_layout_constraintCircleRadius, 3);
                sparseIntArray.append(C5867b.ConstraintLayout_Layout_layout_constraintCircleAngle, 4);
                sparseIntArray.append(C5867b.ConstraintLayout_Layout_layout_editor_absoluteX, 49);
                sparseIntArray.append(C5867b.ConstraintLayout_Layout_layout_editor_absoluteY, 50);
                sparseIntArray.append(C5867b.ConstraintLayout_Layout_layout_constraintGuide_begin, 5);
                sparseIntArray.append(C5867b.ConstraintLayout_Layout_layout_constraintGuide_end, 6);
                sparseIntArray.append(C5867b.ConstraintLayout_Layout_layout_constraintGuide_percent, 7);
                sparseIntArray.append(C5867b.ConstraintLayout_Layout_android_orientation, 1);
                sparseIntArray.append(C5867b.ConstraintLayout_Layout_layout_constraintStart_toEndOf, 17);
                sparseIntArray.append(C5867b.ConstraintLayout_Layout_layout_constraintStart_toStartOf, 18);
                sparseIntArray.append(C5867b.ConstraintLayout_Layout_layout_constraintEnd_toStartOf, 19);
                sparseIntArray.append(C5867b.ConstraintLayout_Layout_layout_constraintEnd_toEndOf, 20);
                sparseIntArray.append(C5867b.ConstraintLayout_Layout_layout_goneMarginLeft, 21);
                sparseIntArray.append(C5867b.ConstraintLayout_Layout_layout_goneMarginTop, 22);
                sparseIntArray.append(C5867b.ConstraintLayout_Layout_layout_goneMarginRight, 23);
                sparseIntArray.append(C5867b.ConstraintLayout_Layout_layout_goneMarginBottom, 24);
                sparseIntArray.append(C5867b.ConstraintLayout_Layout_layout_goneMarginStart, 25);
                sparseIntArray.append(C5867b.ConstraintLayout_Layout_layout_goneMarginEnd, 26);
                sparseIntArray.append(C5867b.ConstraintLayout_Layout_layout_constraintHorizontal_bias, 29);
                sparseIntArray.append(C5867b.ConstraintLayout_Layout_layout_constraintVertical_bias, 30);
                sparseIntArray.append(C5867b.ConstraintLayout_Layout_layout_constraintDimensionRatio, 44);
                sparseIntArray.append(C5867b.ConstraintLayout_Layout_layout_constraintHorizontal_weight, 45);
                sparseIntArray.append(C5867b.ConstraintLayout_Layout_layout_constraintVertical_weight, 46);
                sparseIntArray.append(C5867b.ConstraintLayout_Layout_layout_constraintHorizontal_chainStyle, 47);
                sparseIntArray.append(C5867b.ConstraintLayout_Layout_layout_constraintVertical_chainStyle, 48);
                sparseIntArray.append(C5867b.ConstraintLayout_Layout_layout_constrainedWidth, 27);
                sparseIntArray.append(C5867b.ConstraintLayout_Layout_layout_constrainedHeight, 28);
                sparseIntArray.append(C5867b.ConstraintLayout_Layout_layout_constraintWidth_default, 31);
                sparseIntArray.append(C5867b.ConstraintLayout_Layout_layout_constraintHeight_default, 32);
                sparseIntArray.append(C5867b.ConstraintLayout_Layout_layout_constraintWidth_min, 33);
                sparseIntArray.append(C5867b.ConstraintLayout_Layout_layout_constraintWidth_max, 34);
                sparseIntArray.append(C5867b.ConstraintLayout_Layout_layout_constraintWidth_percent, 35);
                sparseIntArray.append(C5867b.ConstraintLayout_Layout_layout_constraintHeight_min, 36);
                sparseIntArray.append(C5867b.ConstraintLayout_Layout_layout_constraintHeight_max, 37);
                sparseIntArray.append(C5867b.ConstraintLayout_Layout_layout_constraintHeight_percent, 38);
                sparseIntArray.append(C5867b.ConstraintLayout_Layout_layout_constraintLeft_creator, 39);
                sparseIntArray.append(C5867b.ConstraintLayout_Layout_layout_constraintTop_creator, 40);
                sparseIntArray.append(C5867b.ConstraintLayout_Layout_layout_constraintRight_creator, 41);
                sparseIntArray.append(C5867b.ConstraintLayout_Layout_layout_constraintBottom_creator, 42);
                sparseIntArray.append(C5867b.ConstraintLayout_Layout_layout_constraintBaseline_creator, 43);
            }
        }

        public C0298a(Context context, AttributeSet attributeSet) throws NumberFormatException {
            int i9;
            super(context, attributeSet);
            this.f1584a = -1;
            this.f1586b = -1;
            this.f1588c = -1.0f;
            this.f1590d = -1;
            this.f1592e = -1;
            this.f1594f = -1;
            this.f1596g = -1;
            this.f1598h = -1;
            this.f1600i = -1;
            this.f1602j = -1;
            this.f1604k = -1;
            this.f1606l = -1;
            this.f1608m = -1;
            this.f1610n = 0;
            this.f1611o = BitmapDescriptorFactory.HUE_RED;
            this.f1612p = -1;
            this.f1613q = -1;
            this.f1614r = -1;
            this.f1615s = -1;
            this.f1616t = -1;
            this.f1617u = -1;
            this.f1618v = -1;
            this.f1619w = -1;
            this.f1620x = -1;
            this.f1621y = -1;
            this.f1622z = 0.5f;
            this.f1558A = 0.5f;
            this.f1559B = null;
            this.f1560C = BitmapDescriptorFactory.HUE_RED;
            this.f1561D = 1;
            this.f1562E = -1.0f;
            this.f1563F = -1.0f;
            this.f1564G = 0;
            this.f1565H = 0;
            this.f1566I = 0;
            this.f1567J = 0;
            this.f1568K = 0;
            this.f1569L = 0;
            this.f1570M = 0;
            this.f1571N = 0;
            this.f1572O = 1.0f;
            this.f1573P = 1.0f;
            this.f1574Q = -1;
            this.f1575R = -1;
            this.f1576S = -1;
            this.f1577T = false;
            this.f1578U = false;
            this.f1579V = true;
            this.f1580W = true;
            this.f1581X = false;
            this.f1582Y = false;
            this.f1583Z = false;
            this.f1585a0 = false;
            this.f1587b0 = -1;
            this.f1589c0 = -1;
            this.f1591d0 = -1;
            this.f1593e0 = -1;
            this.f1595f0 = -1;
            this.f1597g0 = -1;
            this.f1599h0 = 0.5f;
            this.f1607l0 = new ConstraintWidget();
            this.f1609m0 = false;
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C5867b.ConstraintLayout_Layout);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i10 = 0; i10 < indexCount; i10++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i10);
                int i11 = a.f1623a.get(index);
                switch (i11) {
                    case 1:
                        this.f1576S = typedArrayObtainStyledAttributes.getInt(index, this.f1576S);
                        break;
                    case 2:
                        int resourceId = typedArrayObtainStyledAttributes.getResourceId(index, this.f1608m);
                        this.f1608m = resourceId;
                        if (resourceId == -1) {
                            this.f1608m = typedArrayObtainStyledAttributes.getInt(index, -1);
                            break;
                        } else {
                            break;
                        }
                    case 3:
                        this.f1610n = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.f1610n);
                        break;
                    case 4:
                        float f9 = typedArrayObtainStyledAttributes.getFloat(index, this.f1611o) % 360.0f;
                        this.f1611o = f9;
                        if (f9 < BitmapDescriptorFactory.HUE_RED) {
                            this.f1611o = (360.0f - f9) % 360.0f;
                            break;
                        } else {
                            break;
                        }
                    case 5:
                        this.f1584a = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, this.f1584a);
                        break;
                    case 6:
                        this.f1586b = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, this.f1586b);
                        break;
                    case 7:
                        this.f1588c = typedArrayObtainStyledAttributes.getFloat(index, this.f1588c);
                        break;
                    case 8:
                        int resourceId2 = typedArrayObtainStyledAttributes.getResourceId(index, this.f1590d);
                        this.f1590d = resourceId2;
                        if (resourceId2 == -1) {
                            this.f1590d = typedArrayObtainStyledAttributes.getInt(index, -1);
                            break;
                        } else {
                            break;
                        }
                    case 9:
                        int resourceId3 = typedArrayObtainStyledAttributes.getResourceId(index, this.f1592e);
                        this.f1592e = resourceId3;
                        if (resourceId3 == -1) {
                            this.f1592e = typedArrayObtainStyledAttributes.getInt(index, -1);
                            break;
                        } else {
                            break;
                        }
                    case 10:
                        int resourceId4 = typedArrayObtainStyledAttributes.getResourceId(index, this.f1594f);
                        this.f1594f = resourceId4;
                        if (resourceId4 == -1) {
                            this.f1594f = typedArrayObtainStyledAttributes.getInt(index, -1);
                            break;
                        } else {
                            break;
                        }
                    case 11:
                        int resourceId5 = typedArrayObtainStyledAttributes.getResourceId(index, this.f1596g);
                        this.f1596g = resourceId5;
                        if (resourceId5 == -1) {
                            this.f1596g = typedArrayObtainStyledAttributes.getInt(index, -1);
                            break;
                        } else {
                            break;
                        }
                    case 12:
                        int resourceId6 = typedArrayObtainStyledAttributes.getResourceId(index, this.f1598h);
                        this.f1598h = resourceId6;
                        if (resourceId6 == -1) {
                            this.f1598h = typedArrayObtainStyledAttributes.getInt(index, -1);
                            break;
                        } else {
                            break;
                        }
                    case 13:
                        int resourceId7 = typedArrayObtainStyledAttributes.getResourceId(index, this.f1600i);
                        this.f1600i = resourceId7;
                        if (resourceId7 == -1) {
                            this.f1600i = typedArrayObtainStyledAttributes.getInt(index, -1);
                            break;
                        } else {
                            break;
                        }
                    case 14:
                        int resourceId8 = typedArrayObtainStyledAttributes.getResourceId(index, this.f1602j);
                        this.f1602j = resourceId8;
                        if (resourceId8 == -1) {
                            this.f1602j = typedArrayObtainStyledAttributes.getInt(index, -1);
                            break;
                        } else {
                            break;
                        }
                    case 15:
                        int resourceId9 = typedArrayObtainStyledAttributes.getResourceId(index, this.f1604k);
                        this.f1604k = resourceId9;
                        if (resourceId9 == -1) {
                            this.f1604k = typedArrayObtainStyledAttributes.getInt(index, -1);
                            break;
                        } else {
                            break;
                        }
                    case 16:
                        int resourceId10 = typedArrayObtainStyledAttributes.getResourceId(index, this.f1606l);
                        this.f1606l = resourceId10;
                        if (resourceId10 == -1) {
                            this.f1606l = typedArrayObtainStyledAttributes.getInt(index, -1);
                            break;
                        } else {
                            break;
                        }
                    case 17:
                        int resourceId11 = typedArrayObtainStyledAttributes.getResourceId(index, this.f1612p);
                        this.f1612p = resourceId11;
                        if (resourceId11 == -1) {
                            this.f1612p = typedArrayObtainStyledAttributes.getInt(index, -1);
                            break;
                        } else {
                            break;
                        }
                    case 18:
                        int resourceId12 = typedArrayObtainStyledAttributes.getResourceId(index, this.f1613q);
                        this.f1613q = resourceId12;
                        if (resourceId12 == -1) {
                            this.f1613q = typedArrayObtainStyledAttributes.getInt(index, -1);
                            break;
                        } else {
                            break;
                        }
                    case 19:
                        int resourceId13 = typedArrayObtainStyledAttributes.getResourceId(index, this.f1614r);
                        this.f1614r = resourceId13;
                        if (resourceId13 == -1) {
                            this.f1614r = typedArrayObtainStyledAttributes.getInt(index, -1);
                            break;
                        } else {
                            break;
                        }
                    case 20:
                        int resourceId14 = typedArrayObtainStyledAttributes.getResourceId(index, this.f1615s);
                        this.f1615s = resourceId14;
                        if (resourceId14 == -1) {
                            this.f1615s = typedArrayObtainStyledAttributes.getInt(index, -1);
                            break;
                        } else {
                            break;
                        }
                    case 21:
                        this.f1616t = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.f1616t);
                        break;
                    case 22:
                        this.f1617u = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.f1617u);
                        break;
                    case 23:
                        this.f1618v = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.f1618v);
                        break;
                    case 24:
                        this.f1619w = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.f1619w);
                        break;
                    case 25:
                        this.f1620x = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.f1620x);
                        break;
                    case 26:
                        this.f1621y = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.f1621y);
                        break;
                    case 27:
                        this.f1577T = typedArrayObtainStyledAttributes.getBoolean(index, this.f1577T);
                        break;
                    case 28:
                        this.f1578U = typedArrayObtainStyledAttributes.getBoolean(index, this.f1578U);
                        break;
                    case 29:
                        this.f1622z = typedArrayObtainStyledAttributes.getFloat(index, this.f1622z);
                        break;
                    case 30:
                        this.f1558A = typedArrayObtainStyledAttributes.getFloat(index, this.f1558A);
                        break;
                    case 31:
                        int i12 = typedArrayObtainStyledAttributes.getInt(index, 0);
                        this.f1566I = i12;
                        if (i12 == 1) {
                            Log.e("ConstraintLayout", "layout_constraintWidth_default=\"wrap\" is deprecated.\nUse layout_width=\"WRAP_CONTENT\" and layout_constrainedWidth=\"true\" instead.");
                            break;
                        } else {
                            break;
                        }
                    case 32:
                        int i13 = typedArrayObtainStyledAttributes.getInt(index, 0);
                        this.f1567J = i13;
                        if (i13 == 1) {
                            Log.e("ConstraintLayout", "layout_constraintHeight_default=\"wrap\" is deprecated.\nUse layout_height=\"WRAP_CONTENT\" and layout_constrainedHeight=\"true\" instead.");
                            break;
                        } else {
                            break;
                        }
                    case 33:
                        try {
                            this.f1568K = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.f1568K);
                            break;
                        } catch (Exception unused) {
                            if (typedArrayObtainStyledAttributes.getInt(index, this.f1568K) == -2) {
                                this.f1568K = -2;
                                break;
                            } else {
                                break;
                            }
                        }
                    case 34:
                        try {
                            this.f1570M = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.f1570M);
                            break;
                        } catch (Exception unused2) {
                            if (typedArrayObtainStyledAttributes.getInt(index, this.f1570M) == -2) {
                                this.f1570M = -2;
                                break;
                            } else {
                                break;
                            }
                        }
                    case 35:
                        this.f1572O = Math.max(BitmapDescriptorFactory.HUE_RED, typedArrayObtainStyledAttributes.getFloat(index, this.f1572O));
                        break;
                    case 36:
                        try {
                            this.f1569L = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.f1569L);
                            break;
                        } catch (Exception unused3) {
                            if (typedArrayObtainStyledAttributes.getInt(index, this.f1569L) == -2) {
                                this.f1569L = -2;
                                break;
                            } else {
                                break;
                            }
                        }
                    case 37:
                        try {
                            this.f1571N = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.f1571N);
                            break;
                        } catch (Exception unused4) {
                            if (typedArrayObtainStyledAttributes.getInt(index, this.f1571N) == -2) {
                                this.f1571N = -2;
                                break;
                            } else {
                                break;
                            }
                        }
                    case 38:
                        this.f1573P = Math.max(BitmapDescriptorFactory.HUE_RED, typedArrayObtainStyledAttributes.getFloat(index, this.f1573P));
                        break;
                    default:
                        switch (i11) {
                            case 44:
                                String string = typedArrayObtainStyledAttributes.getString(index);
                                this.f1559B = string;
                                this.f1560C = Float.NaN;
                                this.f1561D = -1;
                                if (string != null) {
                                    int length = string.length();
                                    int iIndexOf = this.f1559B.indexOf(44);
                                    if (iIndexOf <= 0 || iIndexOf >= length - 1) {
                                        i9 = 0;
                                    } else {
                                        String strSubstring = this.f1559B.substring(0, iIndexOf);
                                        if (strSubstring.equalsIgnoreCase("W")) {
                                            this.f1561D = 0;
                                        } else if (strSubstring.equalsIgnoreCase("H")) {
                                            this.f1561D = 1;
                                        }
                                        i9 = iIndexOf + 1;
                                    }
                                    int iIndexOf2 = this.f1559B.indexOf(58);
                                    if (iIndexOf2 < 0 || iIndexOf2 >= length - 1) {
                                        String strSubstring2 = this.f1559B.substring(i9);
                                        if (strSubstring2.length() > 0) {
                                            this.f1560C = Float.parseFloat(strSubstring2);
                                            break;
                                        } else {
                                            break;
                                        }
                                    } else {
                                        String strSubstring3 = this.f1559B.substring(i9, iIndexOf2);
                                        String strSubstring4 = this.f1559B.substring(iIndexOf2 + 1);
                                        if (strSubstring3.length() <= 0 || strSubstring4.length() <= 0) {
                                            break;
                                        } else {
                                            try {
                                                float f10 = Float.parseFloat(strSubstring3);
                                                float f11 = Float.parseFloat(strSubstring4);
                                                if (f10 <= BitmapDescriptorFactory.HUE_RED || f11 <= BitmapDescriptorFactory.HUE_RED) {
                                                    break;
                                                } else if (this.f1561D == 1) {
                                                    this.f1560C = Math.abs(f11 / f10);
                                                    break;
                                                } else {
                                                    this.f1560C = Math.abs(f10 / f11);
                                                    break;
                                                }
                                            } catch (NumberFormatException unused5) {
                                                break;
                                            }
                                        }
                                    }
                                } else {
                                    break;
                                }
                                break;
                            case 45:
                                this.f1562E = typedArrayObtainStyledAttributes.getFloat(index, this.f1562E);
                                break;
                            case 46:
                                this.f1563F = typedArrayObtainStyledAttributes.getFloat(index, this.f1563F);
                                break;
                            case 47:
                                this.f1564G = typedArrayObtainStyledAttributes.getInt(index, 0);
                                break;
                            case 48:
                                this.f1565H = typedArrayObtainStyledAttributes.getInt(index, 0);
                                break;
                            case 49:
                                this.f1574Q = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, this.f1574Q);
                                break;
                            case 50:
                                this.f1575R = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, this.f1575R);
                                break;
                        }
                }
            }
            typedArrayObtainStyledAttributes.recycle();
            m1410a();
        }

        /* renamed from: a */
        public void m1410a() {
            this.f1582Y = false;
            this.f1579V = true;
            this.f1580W = true;
            int i9 = ((ViewGroup.MarginLayoutParams) this).width;
            if (i9 == -2 && this.f1577T) {
                this.f1579V = false;
                this.f1566I = 1;
            }
            int i10 = ((ViewGroup.MarginLayoutParams) this).height;
            if (i10 == -2 && this.f1578U) {
                this.f1580W = false;
                this.f1567J = 1;
            }
            if (i9 == 0 || i9 == -1) {
                this.f1579V = false;
                if (i9 == 0 && this.f1566I == 1) {
                    ((ViewGroup.MarginLayoutParams) this).width = -2;
                    this.f1577T = true;
                }
            }
            if (i10 == 0 || i10 == -1) {
                this.f1580W = false;
                if (i10 == 0 && this.f1567J == 1) {
                    ((ViewGroup.MarginLayoutParams) this).height = -2;
                    this.f1578U = true;
                }
            }
            if (this.f1588c == -1.0f && this.f1584a == -1 && this.f1586b == -1) {
                return;
            }
            this.f1582Y = true;
            this.f1579V = true;
            this.f1580W = true;
            if (!(this.f1607l0 instanceof C0294g)) {
                this.f1607l0 = new C0294g();
            }
            ((C0294g) this.f1607l0).m1366M0(this.f1576S);
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0048  */
        /* JADX WARN: Removed duplicated region for block: B:19:0x004f  */
        /* JADX WARN: Removed duplicated region for block: B:22:0x0056  */
        /* JADX WARN: Removed duplicated region for block: B:25:0x005c  */
        /* JADX WARN: Removed duplicated region for block: B:28:0x0062  */
        /* JADX WARN: Removed duplicated region for block: B:35:0x0078  */
        /* JADX WARN: Removed duplicated region for block: B:36:0x0080  */
        @Override // android.view.ViewGroup.MarginLayoutParams, android.view.ViewGroup.LayoutParams
        @TargetApi(17)
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public void resolveLayoutDirection(int i9) {
            int i10;
            int i11;
            int i12;
            int i13;
            float f9;
            int i14 = ((ViewGroup.MarginLayoutParams) this).leftMargin;
            int i15 = ((ViewGroup.MarginLayoutParams) this).rightMargin;
            super.resolveLayoutDirection(i9);
            this.f1591d0 = -1;
            this.f1593e0 = -1;
            this.f1587b0 = -1;
            this.f1589c0 = -1;
            this.f1595f0 = this.f1616t;
            this.f1597g0 = this.f1618v;
            this.f1599h0 = this.f1622z;
            this.f1601i0 = this.f1584a;
            this.f1603j0 = this.f1586b;
            this.f1605k0 = this.f1588c;
            boolean z8 = false;
            if (1 == getLayoutDirection()) {
                int i16 = this.f1612p;
                if (i16 != -1) {
                    this.f1591d0 = i16;
                } else {
                    int i17 = this.f1613q;
                    if (i17 != -1) {
                        this.f1593e0 = i17;
                    }
                    i10 = this.f1614r;
                    if (i10 != -1) {
                        this.f1589c0 = i10;
                        z8 = true;
                    }
                    i11 = this.f1615s;
                    if (i11 != -1) {
                        this.f1587b0 = i11;
                        z8 = true;
                    }
                    i12 = this.f1620x;
                    if (i12 != -1) {
                        this.f1597g0 = i12;
                    }
                    i13 = this.f1621y;
                    if (i13 != -1) {
                        this.f1595f0 = i13;
                    }
                    if (z8) {
                        this.f1599h0 = 1.0f - this.f1622z;
                    }
                    if (this.f1582Y && this.f1576S == 1) {
                        f9 = this.f1588c;
                        if (f9 == -1.0f) {
                            this.f1605k0 = 1.0f - f9;
                            this.f1601i0 = -1;
                            this.f1603j0 = -1;
                        } else {
                            int i18 = this.f1584a;
                            if (i18 != -1) {
                                this.f1603j0 = i18;
                                this.f1601i0 = -1;
                                this.f1605k0 = -1.0f;
                            } else {
                                int i19 = this.f1586b;
                                if (i19 != -1) {
                                    this.f1601i0 = i19;
                                    this.f1603j0 = -1;
                                    this.f1605k0 = -1.0f;
                                }
                            }
                        }
                    }
                }
                z8 = true;
                i10 = this.f1614r;
                if (i10 != -1) {
                }
                i11 = this.f1615s;
                if (i11 != -1) {
                }
                i12 = this.f1620x;
                if (i12 != -1) {
                }
                i13 = this.f1621y;
                if (i13 != -1) {
                }
                if (z8) {
                }
                if (this.f1582Y) {
                    f9 = this.f1588c;
                    if (f9 == -1.0f) {
                    }
                }
            } else {
                int i20 = this.f1612p;
                if (i20 != -1) {
                    this.f1589c0 = i20;
                }
                int i21 = this.f1613q;
                if (i21 != -1) {
                    this.f1587b0 = i21;
                }
                int i22 = this.f1614r;
                if (i22 != -1) {
                    this.f1591d0 = i22;
                }
                int i23 = this.f1615s;
                if (i23 != -1) {
                    this.f1593e0 = i23;
                }
                int i24 = this.f1620x;
                if (i24 != -1) {
                    this.f1595f0 = i24;
                }
                int i25 = this.f1621y;
                if (i25 != -1) {
                    this.f1597g0 = i25;
                }
            }
            if (this.f1614r == -1 && this.f1615s == -1 && this.f1613q == -1 && this.f1612p == -1) {
                int i26 = this.f1594f;
                if (i26 != -1) {
                    this.f1591d0 = i26;
                    if (((ViewGroup.MarginLayoutParams) this).rightMargin <= 0 && i15 > 0) {
                        ((ViewGroup.MarginLayoutParams) this).rightMargin = i15;
                    }
                } else {
                    int i27 = this.f1596g;
                    if (i27 != -1) {
                        this.f1593e0 = i27;
                        if (((ViewGroup.MarginLayoutParams) this).rightMargin <= 0 && i15 > 0) {
                            ((ViewGroup.MarginLayoutParams) this).rightMargin = i15;
                        }
                    }
                }
                int i28 = this.f1590d;
                if (i28 != -1) {
                    this.f1587b0 = i28;
                    if (((ViewGroup.MarginLayoutParams) this).leftMargin > 0 || i14 <= 0) {
                        return;
                    }
                    ((ViewGroup.MarginLayoutParams) this).leftMargin = i14;
                    return;
                }
                int i29 = this.f1592e;
                if (i29 != -1) {
                    this.f1589c0 = i29;
                    if (((ViewGroup.MarginLayoutParams) this).leftMargin > 0 || i14 <= 0) {
                        return;
                    }
                    ((ViewGroup.MarginLayoutParams) this).leftMargin = i14;
                }
            }
        }

        public C0298a(int i9, int i10) {
            super(i9, i10);
            this.f1584a = -1;
            this.f1586b = -1;
            this.f1588c = -1.0f;
            this.f1590d = -1;
            this.f1592e = -1;
            this.f1594f = -1;
            this.f1596g = -1;
            this.f1598h = -1;
            this.f1600i = -1;
            this.f1602j = -1;
            this.f1604k = -1;
            this.f1606l = -1;
            this.f1608m = -1;
            this.f1610n = 0;
            this.f1611o = BitmapDescriptorFactory.HUE_RED;
            this.f1612p = -1;
            this.f1613q = -1;
            this.f1614r = -1;
            this.f1615s = -1;
            this.f1616t = -1;
            this.f1617u = -1;
            this.f1618v = -1;
            this.f1619w = -1;
            this.f1620x = -1;
            this.f1621y = -1;
            this.f1622z = 0.5f;
            this.f1558A = 0.5f;
            this.f1559B = null;
            this.f1560C = BitmapDescriptorFactory.HUE_RED;
            this.f1561D = 1;
            this.f1562E = -1.0f;
            this.f1563F = -1.0f;
            this.f1564G = 0;
            this.f1565H = 0;
            this.f1566I = 0;
            this.f1567J = 0;
            this.f1568K = 0;
            this.f1569L = 0;
            this.f1570M = 0;
            this.f1571N = 0;
            this.f1572O = 1.0f;
            this.f1573P = 1.0f;
            this.f1574Q = -1;
            this.f1575R = -1;
            this.f1576S = -1;
            this.f1577T = false;
            this.f1578U = false;
            this.f1579V = true;
            this.f1580W = true;
            this.f1581X = false;
            this.f1582Y = false;
            this.f1583Z = false;
            this.f1585a0 = false;
            this.f1587b0 = -1;
            this.f1589c0 = -1;
            this.f1591d0 = -1;
            this.f1593e0 = -1;
            this.f1595f0 = -1;
            this.f1597g0 = -1;
            this.f1599h0 = 0.5f;
            this.f1607l0 = new ConstraintWidget();
            this.f1609m0 = false;
        }

        public C0298a(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.f1584a = -1;
            this.f1586b = -1;
            this.f1588c = -1.0f;
            this.f1590d = -1;
            this.f1592e = -1;
            this.f1594f = -1;
            this.f1596g = -1;
            this.f1598h = -1;
            this.f1600i = -1;
            this.f1602j = -1;
            this.f1604k = -1;
            this.f1606l = -1;
            this.f1608m = -1;
            this.f1610n = 0;
            this.f1611o = BitmapDescriptorFactory.HUE_RED;
            this.f1612p = -1;
            this.f1613q = -1;
            this.f1614r = -1;
            this.f1615s = -1;
            this.f1616t = -1;
            this.f1617u = -1;
            this.f1618v = -1;
            this.f1619w = -1;
            this.f1620x = -1;
            this.f1621y = -1;
            this.f1622z = 0.5f;
            this.f1558A = 0.5f;
            this.f1559B = null;
            this.f1560C = BitmapDescriptorFactory.HUE_RED;
            this.f1561D = 1;
            this.f1562E = -1.0f;
            this.f1563F = -1.0f;
            this.f1564G = 0;
            this.f1565H = 0;
            this.f1566I = 0;
            this.f1567J = 0;
            this.f1568K = 0;
            this.f1569L = 0;
            this.f1570M = 0;
            this.f1571N = 0;
            this.f1572O = 1.0f;
            this.f1573P = 1.0f;
            this.f1574Q = -1;
            this.f1575R = -1;
            this.f1576S = -1;
            this.f1577T = false;
            this.f1578U = false;
            this.f1579V = true;
            this.f1580W = true;
            this.f1581X = false;
            this.f1582Y = false;
            this.f1583Z = false;
            this.f1585a0 = false;
            this.f1587b0 = -1;
            this.f1589c0 = -1;
            this.f1591d0 = -1;
            this.f1593e0 = -1;
            this.f1595f0 = -1;
            this.f1597g0 = -1;
            this.f1599h0 = 0.5f;
            this.f1607l0 = new ConstraintWidget();
            this.f1609m0 = false;
        }
    }
}
