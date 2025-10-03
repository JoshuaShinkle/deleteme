package androidx.constraintlayout.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import p161p.C5867b;

/* loaded from: classes.dex */
public class Placeholder extends View {

    /* renamed from: b */
    public int f1638b;

    /* renamed from: c */
    public View f1639c;

    /* renamed from: d */
    public int f1640d;

    public Placeholder(Context context, AttributeSet attributeSet, int i9) {
        super(context, attributeSet, i9);
        this.f1638b = -1;
        this.f1639c = null;
        this.f1640d = 4;
        m1414a(attributeSet);
    }

    /* renamed from: a */
    public final void m1414a(AttributeSet attributeSet) {
        super.setVisibility(this.f1640d);
        this.f1638b = -1;
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, C5867b.ConstraintLayout_placeholder);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i9 = 0; i9 < indexCount; i9++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i9);
                if (index == C5867b.ConstraintLayout_placeholder_content) {
                    this.f1638b = typedArrayObtainStyledAttributes.getResourceId(index, this.f1638b);
                } else if (index == C5867b.ConstraintLayout_placeholder_emptyVisibility) {
                    this.f1640d = typedArrayObtainStyledAttributes.getInt(index, this.f1640d);
                }
            }
        }
    }

    /* renamed from: b */
    public void m1415b(ConstraintLayout constraintLayout) {
        if (this.f1639c == null) {
            return;
        }
        ConstraintLayout.C0298a c0298a = (ConstraintLayout.C0298a) getLayoutParams();
        ConstraintLayout.C0298a c0298a2 = (ConstraintLayout.C0298a) this.f1639c.getLayoutParams();
        c0298a2.f1607l0.m1311x0(0);
        c0298a.f1607l0.m1313y0(c0298a2.f1607l0.m1236D());
        c0298a.f1607l0.m1267b0(c0298a2.f1607l0.m1298r());
        c0298a2.f1607l0.m1311x0(8);
    }

    /* renamed from: c */
    public void m1416c(ConstraintLayout constraintLayout) {
        if (this.f1638b == -1 && !isInEditMode()) {
            setVisibility(this.f1640d);
        }
        View viewFindViewById = constraintLayout.findViewById(this.f1638b);
        this.f1639c = viewFindViewById;
        if (viewFindViewById != null) {
            ((ConstraintLayout.C0298a) viewFindViewById.getLayoutParams()).f1585a0 = true;
            this.f1639c.setVisibility(0);
            setVisibility(0);
        }
    }

    public View getContent() {
        return this.f1639c;
    }

    public int getEmptyVisibility() {
        return this.f1640d;
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        if (isInEditMode()) {
            canvas.drawRGB(223, 223, 223);
            Paint paint = new Paint();
            paint.setARGB(255, 210, 210, 210);
            paint.setTextAlign(Paint.Align.CENTER);
            paint.setTypeface(Typeface.create(Typeface.DEFAULT, 0));
            Rect rect = new Rect();
            canvas.getClipBounds(rect);
            paint.setTextSize(rect.height());
            int iHeight = rect.height();
            int iWidth = rect.width();
            paint.setTextAlign(Paint.Align.LEFT);
            paint.getTextBounds("?", 0, 1, rect);
            canvas.drawText("?", ((iWidth / 2.0f) - (rect.width() / 2.0f)) - rect.left, ((iHeight / 2.0f) + (rect.height() / 2.0f)) - rect.bottom, paint);
        }
    }

    public void setContentId(int i9) {
        View viewFindViewById;
        if (this.f1638b == i9) {
            return;
        }
        View view = this.f1639c;
        if (view != null) {
            view.setVisibility(0);
            ((ConstraintLayout.C0298a) this.f1639c.getLayoutParams()).f1585a0 = false;
            this.f1639c = null;
        }
        this.f1638b = i9;
        if (i9 == -1 || (viewFindViewById = ((View) getParent()).findViewById(i9)) == null) {
            return;
        }
        viewFindViewById.setVisibility(8);
    }

    public void setEmptyVisibility(int i9) {
        this.f1640d = i9;
    }
}
