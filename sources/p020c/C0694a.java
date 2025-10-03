package p020c;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.util.SparseArray;
import android.util.TypedValue;
import androidx.appcompat.widget.C0230g0;
import java.util.WeakHashMap;

@SuppressLint({"RestrictedAPI"})
/* renamed from: c.a */
/* loaded from: classes.dex */
public final class C0694a {

    /* renamed from: a */
    public static final ThreadLocal<TypedValue> f3348a = new ThreadLocal<>();

    /* renamed from: b */
    public static final WeakHashMap<Context, SparseArray<Object>> f3349b = new WeakHashMap<>(0);

    /* renamed from: c */
    public static final Object f3350c = new Object();

    /* renamed from: a */
    public static ColorStateList m3457a(Context context, int i9) {
        return context.getColorStateList(i9);
    }

    /* renamed from: b */
    public static Drawable m3458b(Context context, int i9) {
        return C0230g0.m848g().m858i(context, i9);
    }
}
