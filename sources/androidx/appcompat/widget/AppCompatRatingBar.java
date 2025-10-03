package androidx.appcompat.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RatingBar;
import p010b.C0560a;

/* loaded from: classes.dex */
public class AppCompatRatingBar extends RatingBar {

    /* renamed from: b */
    public final C0233i f800b;

    public AppCompatRatingBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, C0560a.ratingBarStyle);
    }

    @Override // android.widget.RatingBar, android.widget.AbsSeekBar, android.widget.ProgressBar, android.view.View
    public synchronized void onMeasure(int i9, int i10) {
        super.onMeasure(i9, i10);
        Bitmap bitmapM884b = this.f800b.m884b();
        if (bitmapM884b != null) {
            setMeasuredDimension(View.resolveSizeAndState(bitmapM884b.getWidth() * getNumStars(), i9, 0), getMeasuredHeight());
        }
    }

    public AppCompatRatingBar(Context context, AttributeSet attributeSet, int i9) {
        super(context, attributeSet, i9);
        C0240l0.m934a(this, getContext());
        C0233i c0233i = new C0233i(this);
        this.f800b = c0233i;
        c0233i.mo885c(attributeSet, i9);
    }
}
