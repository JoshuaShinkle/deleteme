package androidx.transition;

import android.animation.ObjectAnimator;
import android.animation.TypeConverter;
import android.graphics.Path;
import android.graphics.PointF;
import android.util.Property;

/* renamed from: androidx.transition.f */
/* loaded from: classes.dex */
public class C0521f {
    /* renamed from: a */
    public static <T> ObjectAnimator m3076a(T t8, Property<T, PointF> property, Path path) {
        return ObjectAnimator.ofObject(t8, property, (TypeConverter) null, path);
    }
}
