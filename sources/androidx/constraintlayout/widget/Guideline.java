package androidx.constraintlayout.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;

/* loaded from: classes.dex */
public class Guideline extends View {
    public Guideline(Context context) {
        super(context);
        super.setVisibility(8);
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
    }

    @Override // android.view.View
    public void onMeasure(int i9, int i10) {
        setMeasuredDimension(0, 0);
    }

    public void setGuidelineBegin(int i9) {
        ConstraintLayout.C0298a c0298a = (ConstraintLayout.C0298a) getLayoutParams();
        c0298a.f1584a = i9;
        setLayoutParams(c0298a);
    }

    public void setGuidelineEnd(int i9) {
        ConstraintLayout.C0298a c0298a = (ConstraintLayout.C0298a) getLayoutParams();
        c0298a.f1586b = i9;
        setLayoutParams(c0298a);
    }

    public void setGuidelinePercent(float f9) {
        ConstraintLayout.C0298a c0298a = (ConstraintLayout.C0298a) getLayoutParams();
        c0298a.f1588c = f9;
        setLayoutParams(c0298a);
    }

    @Override // android.view.View
    public void setVisibility(int i9) {
    }

    public Guideline(Context context, AttributeSet attributeSet, int i9) {
        super(context, attributeSet, i9);
        super.setVisibility(8);
    }
}
