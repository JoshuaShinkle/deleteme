package androidx.legacy.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

@Deprecated
/* loaded from: classes.dex */
public class Space extends View {
    @Deprecated
    public Space(Context context, AttributeSet attributeSet, int i9) {
        super(context, attributeSet, i9);
        if (getVisibility() == 0) {
            setVisibility(4);
        }
    }

    /* renamed from: a */
    public static int m2046a(int i9, int i10) {
        int mode = View.MeasureSpec.getMode(i10);
        int size = View.MeasureSpec.getSize(i10);
        return mode != Integer.MIN_VALUE ? mode != 1073741824 ? i9 : size : Math.min(i9, size);
    }

    @Override // android.view.View
    @SuppressLint({"MissingSuperCall"})
    @Deprecated
    public void draw(Canvas canvas) {
    }

    @Override // android.view.View
    @Deprecated
    public void onMeasure(int i9, int i10) {
        setMeasuredDimension(m2046a(getSuggestedMinimumWidth(), i9), m2046a(getSuggestedMinimumHeight(), i10));
    }

    @Deprecated
    public Space(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    @Deprecated
    public Space(Context context) {
        this(context, null);
    }
}
