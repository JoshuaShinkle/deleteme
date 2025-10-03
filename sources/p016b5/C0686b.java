package p016b5;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;

/* renamed from: b5.b */
/* loaded from: classes2.dex */
public class C0686b extends ViewGroup {

    /* renamed from: b */
    public int f3334b;

    public C0686b(Context context) {
        super(context);
        this.f3334b = 48;
        setLayoutParams(new AbsListView.LayoutParams(-1, -2));
    }

    public int getGravity() {
        return this.f3334b;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean z8, int i9, int i10, int i11, int i12) {
        View childAt = getChildAt(0);
        if (childAt == null) {
            return;
        }
        if (this.f3334b == 48) {
            childAt.layout(0, 0, getMeasuredWidth(), childAt.getMeasuredHeight());
        } else {
            childAt.layout(0, getMeasuredHeight() - childAt.getMeasuredHeight(), getMeasuredWidth(), getMeasuredHeight());
        }
    }

    @Override // android.view.View
    public void onMeasure(int i9, int i10) {
        int size = View.MeasureSpec.getSize(i10);
        int size2 = View.MeasureSpec.getSize(i9);
        int mode = View.MeasureSpec.getMode(i10);
        View childAt = getChildAt(0);
        if (childAt == null) {
            setMeasuredDimension(0, size2);
            return;
        }
        if (childAt.isLayoutRequested()) {
            measureChild(childAt, i9, View.MeasureSpec.makeMeasureSpec(0, 0));
        }
        if (mode == 0 && (size = getLayoutParams().height) <= 0) {
            size = childAt.getMeasuredHeight();
        }
        setMeasuredDimension(size2, size);
    }

    public void setGravity(int i9) {
        this.f3334b = i9;
    }
}
