package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import java.lang.ref.WeakReference;

/* renamed from: androidx.appcompat.widget.t0 */
/* loaded from: classes.dex */
public class C0256t0 extends Resources {

    /* renamed from: b */
    public static boolean f1217b = false;

    /* renamed from: a */
    public final WeakReference<Context> f1218a;

    public C0256t0(Context context, Resources resources) {
        super(resources.getAssets(), resources.getDisplayMetrics(), resources.getConfiguration());
        this.f1218a = new WeakReference<>(context);
    }

    /* renamed from: a */
    public static boolean m1063a() {
        return f1217b;
    }

    /* renamed from: b */
    public static boolean m1064b() {
        m1063a();
        return false;
    }

    /* renamed from: c */
    public final Drawable m1065c(int i9) {
        return super.getDrawable(i9);
    }

    @Override // android.content.res.Resources
    public Drawable getDrawable(int i9) {
        Context context = this.f1218a.get();
        return context != null ? C0230g0.m848g().m865s(context, this, i9) : super.getDrawable(i9);
    }
}
