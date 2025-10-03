package androidx.transition;

import android.animation.TypeEvaluator;
import android.graphics.Rect;

/* renamed from: androidx.transition.j */
/* loaded from: classes.dex */
public class C0529j implements TypeEvaluator<Rect> {

    /* renamed from: a */
    public Rect f2925a;

    @Override // android.animation.TypeEvaluator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public Rect evaluate(float f9, Rect rect, Rect rect2) {
        int i9 = rect.left + ((int) ((rect2.left - r0) * f9));
        int i10 = rect.top + ((int) ((rect2.top - r1) * f9));
        int i11 = rect.right + ((int) ((rect2.right - r2) * f9));
        int i12 = rect.bottom + ((int) ((rect2.bottom - r6) * f9));
        Rect rect3 = this.f2925a;
        if (rect3 == null) {
            return new Rect(i9, i10, i11, i12);
        }
        rect3.set(i9, i10, i11, i12);
        return this.f2925a;
    }
}
