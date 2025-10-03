package androidx.constraintlayout.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import java.util.Arrays;
import p150o.C5384a;
import p161p.C5866a;
import p161p.C5867b;

/* loaded from: classes.dex */
public abstract class ConstraintHelper extends View {

    /* renamed from: b */
    public int[] f1533b;

    /* renamed from: c */
    public int f1534c;

    /* renamed from: d */
    public Context f1535d;

    /* renamed from: e */
    public C5384a f1536e;

    /* renamed from: f */
    public boolean f1537f;

    /* renamed from: g */
    public String f1538g;

    public ConstraintHelper(Context context) throws IllegalAccessException, IllegalArgumentException {
        super(context);
        this.f1533b = new int[32];
        this.f1537f = false;
        this.f1535d = context;
        mo1389b(null);
    }

    private void setIds(String str) throws IllegalAccessException, IllegalArgumentException {
        if (str == null) {
            return;
        }
        int i9 = 0;
        while (true) {
            int iIndexOf = str.indexOf(44, i9);
            if (iIndexOf == -1) {
                m1390a(str.substring(i9));
                return;
            } else {
                m1390a(str.substring(i9, iIndexOf));
                i9 = iIndexOf + 1;
            }
        }
    }

    /* renamed from: a */
    public final void m1390a(String str) throws IllegalAccessException, IllegalArgumentException {
        int iIntValue;
        Object objM1397c;
        if (str == null || this.f1535d == null) {
            return;
        }
        String strTrim = str.trim();
        try {
            iIntValue = C5866a.class.getField(strTrim).getInt(null);
        } catch (Exception unused) {
            iIntValue = 0;
        }
        if (iIntValue == 0) {
            iIntValue = this.f1535d.getResources().getIdentifier(strTrim, TtmlNode.ATTR_ID, this.f1535d.getPackageName());
        }
        if (iIntValue == 0 && isInEditMode() && (getParent() instanceof ConstraintLayout) && (objM1397c = ((ConstraintLayout) getParent()).m1397c(0, strTrim)) != null && (objM1397c instanceof Integer)) {
            iIntValue = ((Integer) objM1397c).intValue();
        }
        if (iIntValue != 0) {
            setTag(iIntValue, null);
            return;
        }
        Log.w("ConstraintHelper", "Could not find id of \"" + strTrim + "\"");
    }

    /* renamed from: b */
    public void mo1389b(AttributeSet attributeSet) throws IllegalAccessException, IllegalArgumentException {
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, C5867b.ConstraintLayout_Layout);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i9 = 0; i9 < indexCount; i9++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i9);
                if (index == C5867b.ConstraintLayout_Layout_constraint_referenced_ids) {
                    String string = typedArrayObtainStyledAttributes.getString(index);
                    this.f1538g = string;
                    setIds(string);
                }
            }
        }
    }

    /* renamed from: c */
    public void mo1391c(ConstraintLayout constraintLayout) {
    }

    /* renamed from: d */
    public void m1392d(ConstraintLayout constraintLayout) {
    }

    /* renamed from: e */
    public void mo1393e(ConstraintLayout constraintLayout) throws IllegalAccessException, IllegalArgumentException {
        if (isInEditMode()) {
            setIds(this.f1538g);
        }
        C5384a c5384a = this.f1536e;
        if (c5384a == null) {
            return;
        }
        c5384a.m21088J0();
        for (int i9 = 0; i9 < this.f1534c; i9++) {
            View viewM1399e = constraintLayout.m1399e(this.f1533b[i9]);
            if (viewM1399e != null) {
                this.f1536e.m21087I0(constraintLayout.m1400f(viewM1399e));
            }
        }
    }

    /* renamed from: f */
    public void m1394f() {
        if (this.f1536e == null) {
            return;
        }
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        if (layoutParams instanceof ConstraintLayout.C0298a) {
            ((ConstraintLayout.C0298a) layoutParams).f1607l0 = this.f1536e;
        }
    }

    public int[] getReferencedIds() {
        return Arrays.copyOf(this.f1533b, this.f1534c);
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
    }

    @Override // android.view.View
    public void onMeasure(int i9, int i10) {
        if (this.f1537f) {
            super.onMeasure(i9, i10);
        } else {
            setMeasuredDimension(0, 0);
        }
    }

    public void setReferencedIds(int[] iArr) {
        this.f1534c = 0;
        for (int i9 : iArr) {
            setTag(i9, null);
        }
    }

    @Override // android.view.View
    public void setTag(int i9, Object obj) {
        int i10 = this.f1534c + 1;
        int[] iArr = this.f1533b;
        if (i10 > iArr.length) {
            this.f1533b = Arrays.copyOf(iArr, iArr.length * 2);
        }
        int[] iArr2 = this.f1533b;
        int i11 = this.f1534c;
        iArr2[i11] = i9;
        this.f1534c = i11 + 1;
    }

    public ConstraintHelper(Context context, AttributeSet attributeSet, int i9) throws IllegalAccessException, IllegalArgumentException {
        super(context, attributeSet, i9);
        this.f1533b = new int[32];
        this.f1537f = false;
        this.f1535d = context;
        mo1389b(attributeSet);
    }
}
