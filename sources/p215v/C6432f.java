package p215v;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.fonts.FontVariationAxis;
import android.net.Uri;
import android.os.CancellationSignal;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.util.Map;
import p001a0.C0004b;
import p206u.C6347a;

/* renamed from: v.f */
/* loaded from: classes.dex */
public class C6432f extends C6431e {

    /* renamed from: g */
    public final Class<?> f21667g;

    /* renamed from: h */
    public final Constructor<?> f21668h;

    /* renamed from: i */
    public final Method f21669i;

    /* renamed from: j */
    public final Method f21670j;

    /* renamed from: k */
    public final Method f21671k;

    /* renamed from: l */
    public final Method f21672l;

    /* renamed from: m */
    public final Method f21673m;

    public C6432f() {
        Class<?> clsM24618s;
        Constructor<?> constructorM24619t;
        Method methodM24615p;
        Method methodM24616q;
        Method methodM24620u;
        Method methodM24614o;
        Method methodMo24617r;
        try {
            clsM24618s = m24618s();
            constructorM24619t = m24619t(clsM24618s);
            methodM24615p = m24615p(clsM24618s);
            methodM24616q = m24616q(clsM24618s);
            methodM24620u = m24620u(clsM24618s);
            methodM24614o = m24614o(clsM24618s);
            methodMo24617r = mo24617r(clsM24618s);
        } catch (ClassNotFoundException | NoSuchMethodException e9) {
            Log.e("TypefaceCompatApi26Impl", "Unable to collect necessary methods for class " + e9.getClass().getName(), e9);
            clsM24618s = null;
            constructorM24619t = null;
            methodM24615p = null;
            methodM24616q = null;
            methodM24620u = null;
            methodM24614o = null;
            methodMo24617r = null;
        }
        this.f21667g = clsM24618s;
        this.f21668h = constructorM24619t;
        this.f21669i = methodM24615p;
        this.f21670j = methodM24616q;
        this.f21671k = methodM24620u;
        this.f21672l = methodM24614o;
        this.f21673m = methodMo24617r;
    }

    /* renamed from: i */
    private Object m24605i() {
        try {
            return this.f21668h.newInstance(new Object[0]);
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException unused) {
            return null;
        }
    }

    @Override // p215v.C6431e, p215v.C6444r
    /* renamed from: a */
    public Typeface mo24604a(Context context, C6347a.b bVar, Resources resources, int i9) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        if (!m24613n()) {
            return super.mo24604a(context, bVar, resources, i9);
        }
        Object objM24605i = m24605i();
        if (objM24605i == null) {
            return null;
        }
        for (C6347a.c cVar : bVar.m24353a()) {
            if (!m24610k(context, objM24605i, cVar.m24354a(), cVar.m24356c(), cVar.m24358e(), cVar.m24359f() ? 1 : 0, FontVariationAxis.fromFontVariationSettings(cVar.m24357d()))) {
                m24609j(objM24605i);
                return null;
            }
        }
        if (m24612m(objM24605i)) {
            return mo24608g(objM24605i);
        }
        return null;
    }

    @Override // p215v.C6444r
    /* renamed from: b */
    public Typeface mo24606b(Context context, CancellationSignal cancellationSignal, C0004b.f[] fVarArr, int i9) throws IllegalAccessException, IOException, IllegalArgumentException, InvocationTargetException {
        Typeface typefaceMo24608g;
        if (fVarArr.length < 1) {
            return null;
        }
        if (!m24613n()) {
            C0004b.f fVarMo24630e = mo24630e(fVarArr, i9);
            try {
                ParcelFileDescriptor parcelFileDescriptorOpenFileDescriptor = context.getContentResolver().openFileDescriptor(fVarMo24630e.m37c(), "r", cancellationSignal);
                if (parcelFileDescriptorOpenFileDescriptor == null) {
                    if (parcelFileDescriptorOpenFileDescriptor != null) {
                        parcelFileDescriptorOpenFileDescriptor.close();
                    }
                    return null;
                }
                try {
                    Typeface typefaceBuild = new Typeface.Builder(parcelFileDescriptorOpenFileDescriptor.getFileDescriptor()).setWeight(fVarMo24630e.m38d()).setItalic(fVarMo24630e.m39e()).build();
                    parcelFileDescriptorOpenFileDescriptor.close();
                    return typefaceBuild;
                } finally {
                }
            } catch (IOException unused) {
                return null;
            }
        }
        Map<Uri, ByteBuffer> mapM27i = C0004b.m27i(context, fVarArr, cancellationSignal);
        Object objM24605i = m24605i();
        if (objM24605i == null) {
            return null;
        }
        boolean z8 = false;
        for (C0004b.f fVar : fVarArr) {
            ByteBuffer byteBuffer = mapM27i.get(fVar.m37c());
            if (byteBuffer != null) {
                if (!m24611l(objM24605i, byteBuffer, fVar.m36b(), fVar.m38d(), fVar.m39e() ? 1 : 0)) {
                    m24609j(objM24605i);
                    return null;
                }
                z8 = true;
            }
        }
        if (!z8) {
            m24609j(objM24605i);
            return null;
        }
        if (m24612m(objM24605i) && (typefaceMo24608g = mo24608g(objM24605i)) != null) {
            return Typeface.create(typefaceMo24608g, i9);
        }
        return null;
    }

    @Override // p215v.C6444r
    /* renamed from: c */
    public Typeface mo24607c(Context context, Resources resources, int i9, String str, int i10) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        if (!m24613n()) {
            return super.mo24607c(context, resources, i9, str, i10);
        }
        Object objM24605i = m24605i();
        if (objM24605i == null) {
            return null;
        }
        if (!m24610k(context, objM24605i, str, 0, -1, -1, null)) {
            m24609j(objM24605i);
            return null;
        }
        if (m24612m(objM24605i)) {
            return mo24608g(objM24605i);
        }
        return null;
    }

    /* renamed from: g */
    public Typeface mo24608g(Object obj) throws ArrayIndexOutOfBoundsException, IllegalArgumentException, NegativeArraySizeException {
        try {
            Object objNewInstance = Array.newInstance(this.f21667g, 1);
            Array.set(objNewInstance, 0, obj);
            return (Typeface) this.f21673m.invoke(null, objNewInstance, -1, -1);
        } catch (IllegalAccessException | InvocationTargetException unused) {
            return null;
        }
    }

    /* renamed from: j */
    public final void m24609j(Object obj) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        try {
            this.f21672l.invoke(obj, new Object[0]);
        } catch (IllegalAccessException | InvocationTargetException unused) {
        }
    }

    /* renamed from: k */
    public final boolean m24610k(Context context, Object obj, String str, int i9, int i10, int i11, FontVariationAxis[] fontVariationAxisArr) {
        try {
            return ((Boolean) this.f21669i.invoke(obj, context.getAssets(), str, 0, Boolean.FALSE, Integer.valueOf(i9), Integer.valueOf(i10), Integer.valueOf(i11), fontVariationAxisArr)).booleanValue();
        } catch (IllegalAccessException | InvocationTargetException unused) {
            return false;
        }
    }

    /* renamed from: l */
    public final boolean m24611l(Object obj, ByteBuffer byteBuffer, int i9, int i10, int i11) {
        try {
            return ((Boolean) this.f21670j.invoke(obj, byteBuffer, Integer.valueOf(i9), null, Integer.valueOf(i10), Integer.valueOf(i11))).booleanValue();
        } catch (IllegalAccessException | InvocationTargetException unused) {
            return false;
        }
    }

    /* renamed from: m */
    public final boolean m24612m(Object obj) {
        try {
            return ((Boolean) this.f21671k.invoke(obj, new Object[0])).booleanValue();
        } catch (IllegalAccessException | InvocationTargetException unused) {
            return false;
        }
    }

    /* renamed from: n */
    public final boolean m24613n() {
        if (this.f21669i == null) {
            Log.w("TypefaceCompatApi26Impl", "Unable to collect necessary private methods. Fallback to legacy implementation.");
        }
        return this.f21669i != null;
    }

    /* renamed from: o */
    public Method m24614o(Class<?> cls) {
        return cls.getMethod("abortCreation", new Class[0]);
    }

    /* renamed from: p */
    public Method m24615p(Class<?> cls) {
        Class<?> cls2 = Integer.TYPE;
        return cls.getMethod("addFontFromAssetManager", AssetManager.class, String.class, cls2, Boolean.TYPE, cls2, cls2, cls2, FontVariationAxis[].class);
    }

    /* renamed from: q */
    public Method m24616q(Class<?> cls) {
        Class<?> cls2 = Integer.TYPE;
        return cls.getMethod("addFontFromBuffer", ByteBuffer.class, cls2, FontVariationAxis[].class, cls2, cls2);
    }

    /* renamed from: r */
    public Method mo24617r(Class<?> cls) throws NoSuchMethodException, SecurityException {
        Class cls2 = Integer.TYPE;
        Method declaredMethod = Typeface.class.getDeclaredMethod("createFromFamiliesWithDefault", Array.newInstance(cls, 1).getClass(), cls2, cls2);
        declaredMethod.setAccessible(true);
        return declaredMethod;
    }

    /* renamed from: s */
    public Class<?> m24618s() {
        return Class.forName("android.graphics.FontFamily");
    }

    /* renamed from: t */
    public Constructor<?> m24619t(Class<?> cls) {
        return cls.getConstructor(new Class[0]);
    }

    /* renamed from: u */
    public Method m24620u(Class<?> cls) {
        return cls.getMethod("freeze", new Class[0]);
    }
}
