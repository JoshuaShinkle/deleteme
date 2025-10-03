package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import java.lang.ref.WeakReference;

/* renamed from: androidx.appcompat.widget.p0 */
/* loaded from: classes.dex */
public class C0248p0 extends C0232h0 {

    /* renamed from: b */
    public final WeakReference<Context> f1191b;

    public C0248p0(Context context, Resources resources) {
        super(resources);
        this.f1191b = new WeakReference<>(context);
    }

    @Override // androidx.appcompat.widget.C0232h0, android.content.res.Resources
    public Drawable getDrawable(int i9) {
        Drawable drawable = super.getDrawable(i9);
        Context context = this.f1191b.get();
        if (drawable != null && context != null) {
            C0230g0.m848g().m868w(context, i9, drawable);
        }
        return drawable;
    }
}
