package p175q4;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.style.ReplacementSpan;
import android.view.View;
import android.view.ViewGroup;

/* renamed from: q4.i */
/* loaded from: classes.dex */
public class C6161i extends ReplacementSpan {

    /* renamed from: b */
    public View f20796b;

    /* renamed from: c */
    public int f20797c;

    public C6161i(View view, int i9) {
        this.f20797c = i9;
        this.f20796b = view;
        view.setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
    }

    /* renamed from: a */
    public final void m23613a() {
        this.f20796b.measure(View.MeasureSpec.makeMeasureSpec(this.f20797c, Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(0, 0));
        View view = this.f20796b;
        view.layout(0, 0, view.getMeasuredWidth(), this.f20796b.getMeasuredHeight());
    }

    @Override // android.text.style.ReplacementSpan
    public void draw(Canvas canvas, CharSequence charSequence, int i9, int i10, float f9, int i11, int i12, int i13, Paint paint) {
        m23613a();
        canvas.save();
        canvas.translate(f9, (i13 - this.f20796b.getBottom()) - (((i13 - i11) - this.f20796b.getBottom()) / 2));
        this.f20796b.draw(canvas);
        canvas.restore();
    }

    @Override // android.text.style.ReplacementSpan
    public int getSize(Paint paint, CharSequence charSequence, int i9, int i10, Paint.FontMetricsInt fontMetricsInt) {
        m23613a();
        if (fontMetricsInt != null) {
            int measuredHeight = this.f20796b.getMeasuredHeight();
            int i11 = fontMetricsInt.descent;
            int i12 = fontMetricsInt.ascent;
            int i13 = measuredHeight - (i11 - i12);
            if (i13 > 0) {
                int i14 = i13 / 2;
                int i15 = i13 - i14;
                fontMetricsInt.descent = i11 + i15;
                fontMetricsInt.ascent = i12 - i14;
                fontMetricsInt.bottom += i15;
                fontMetricsInt.top -= i14;
            }
        }
        return this.f20796b.getRight();
    }
}
