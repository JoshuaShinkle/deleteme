package p215v;

import android.graphics.Typeface;
import com.google.android.exoplayer2.C3322C;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* renamed from: v.g */
/* loaded from: classes.dex */
public class C6433g extends C6432f {
    @Override // p215v.C6432f
    /* renamed from: g */
    public Typeface mo24608g(Object obj) throws ArrayIndexOutOfBoundsException, IllegalArgumentException, NegativeArraySizeException {
        try {
            Object objNewInstance = Array.newInstance(this.f21667g, 1);
            Array.set(objNewInstance, 0, obj);
            return (Typeface) this.f21673m.invoke(null, objNewInstance, C3322C.SANS_SERIF_NAME, -1, -1);
        } catch (IllegalAccessException | InvocationTargetException e9) {
            throw new RuntimeException(e9);
        }
    }

    @Override // p215v.C6432f
    /* renamed from: r */
    public Method mo24617r(Class<?> cls) throws NoSuchMethodException, SecurityException {
        Class cls2 = Integer.TYPE;
        Method declaredMethod = Typeface.class.getDeclaredMethod("createFromFamiliesWithDefault", Array.newInstance(cls, 1).getClass(), String.class, cls2, cls2);
        declaredMethod.setAccessible(true);
        return declaredMethod;
    }
}
