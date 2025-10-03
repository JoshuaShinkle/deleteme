package p124l1;

import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import p012b1.InterfaceC0592g;
import p012b1.InterfaceC0595j;
import p143n1.C5350c;
import p226w1.C6516i;

/* renamed from: l1.b */
/* loaded from: classes.dex */
public abstract class AbstractC5273b<T extends Drawable> implements InterfaceC0595j<T>, InterfaceC0592g {

    /* renamed from: b */
    public final T f17876b;

    public AbstractC5273b(T t8) {
        this.f17876b = (T) C6516i.m24938d(t8);
    }

    @Override // p012b1.InterfaceC0592g
    /* renamed from: a */
    public void mo3274a() {
        T t8 = this.f17876b;
        if (t8 instanceof BitmapDrawable) {
            ((BitmapDrawable) t8).getBitmap().prepareToDraw();
        } else if (t8 instanceof C5350c) {
            ((C5350c) t8).m21013e().prepareToDraw();
        }
    }

    @Override // p012b1.InterfaceC0595j
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public final T get() {
        Drawable.ConstantState constantState = this.f17876b.getConstantState();
        return constantState == null ? this.f17876b : (T) constantState.newDrawable();
    }
}
