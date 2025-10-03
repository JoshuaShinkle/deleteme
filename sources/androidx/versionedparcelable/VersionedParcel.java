package androidx.versionedparcelable;

import android.os.Parcelable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import p132m.C5302a;
import p180r0.InterfaceC6182b;

/* loaded from: classes.dex */
public abstract class VersionedParcel {

    /* renamed from: a */
    public final C5302a<String, Method> f2977a;

    /* renamed from: b */
    public final C5302a<String, Method> f2978b;

    /* renamed from: c */
    public final C5302a<String, Class> f2979c;

    public static class ParcelException extends RuntimeException {
    }

    public VersionedParcel(C5302a<String, Method> c5302a, C5302a<String, Method> c5302a2, C5302a<String, Class> c5302a3) {
        this.f2977a = c5302a;
        this.f2978b = c5302a2;
        this.f2979c = c5302a3;
    }

    /* renamed from: A */
    public abstract void mo3127A(byte[] bArr);

    /* renamed from: B */
    public void m3128B(byte[] bArr, int i9) {
        mo3163w(i9);
        mo3127A(bArr);
    }

    /* renamed from: C */
    public abstract void mo3129C(CharSequence charSequence);

    /* renamed from: D */
    public void m3130D(CharSequence charSequence, int i9) {
        mo3163w(i9);
        mo3129C(charSequence);
    }

    /* renamed from: E */
    public abstract void mo3131E(int i9);

    /* renamed from: F */
    public void m3132F(int i9, int i10) {
        mo3163w(i10);
        mo3131E(i9);
    }

    /* renamed from: G */
    public abstract void mo3133G(Parcelable parcelable);

    /* renamed from: H */
    public void m3134H(Parcelable parcelable, int i9) {
        mo3163w(i9);
        mo3133G(parcelable);
    }

    /* renamed from: I */
    public abstract void mo3135I(String str);

    /* renamed from: J */
    public void m3136J(String str, int i9) {
        mo3163w(i9);
        mo3135I(str);
    }

    /* renamed from: K */
    public <T extends InterfaceC6182b> void m3137K(T t8, VersionedParcel versionedParcel) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        try {
            m3145e(t8.getClass()).invoke(null, t8, versionedParcel);
        } catch (ClassNotFoundException e9) {
            throw new RuntimeException("VersionedParcel encountered ClassNotFoundException", e9);
        } catch (IllegalAccessException e10) {
            throw new RuntimeException("VersionedParcel encountered IllegalAccessException", e10);
        } catch (NoSuchMethodException e11) {
            throw new RuntimeException("VersionedParcel encountered NoSuchMethodException", e11);
        } catch (InvocationTargetException e12) {
            if (!(e12.getCause() instanceof RuntimeException)) {
                throw new RuntimeException("VersionedParcel encountered InvocationTargetException", e12);
            }
            throw ((RuntimeException) e12.getCause());
        }
    }

    /* renamed from: L */
    public void m3138L(InterfaceC6182b interfaceC6182b) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        if (interfaceC6182b == null) {
            mo3135I(null);
            return;
        }
        m3140N(interfaceC6182b);
        VersionedParcel versionedParcelMo3142b = mo3142b();
        m3137K(interfaceC6182b, versionedParcelMo3142b);
        versionedParcelMo3142b.mo3141a();
    }

    /* renamed from: M */
    public void m3139M(InterfaceC6182b interfaceC6182b, int i9) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        mo3163w(i9);
        m3138L(interfaceC6182b);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: N */
    public final void m3140N(InterfaceC6182b interfaceC6182b) {
        try {
            mo3135I(m3143c(interfaceC6182b.getClass()).getName());
        } catch (ClassNotFoundException e9) {
            throw new RuntimeException(interfaceC6182b.getClass().getSimpleName() + " does not have a Parcelizer", e9);
        }
    }

    /* renamed from: a */
    public abstract void mo3141a();

    /* renamed from: b */
    public abstract VersionedParcel mo3142b();

    /* renamed from: c */
    public final Class m3143c(Class<? extends InterfaceC6182b> cls) throws ClassNotFoundException {
        Class cls2 = this.f2979c.get(cls.getName());
        if (cls2 != null) {
            return cls2;
        }
        Class<?> cls3 = Class.forName(String.format("%s.%sParcelizer", cls.getPackage().getName(), cls.getSimpleName()), false, cls.getClassLoader());
        this.f2979c.put(cls.getName(), cls3);
        return cls3;
    }

    /* renamed from: d */
    public final Method m3144d(String str) throws NoSuchMethodException, SecurityException {
        Method method = this.f2977a.get(str);
        if (method != null) {
            return method;
        }
        System.currentTimeMillis();
        Method declaredMethod = Class.forName(str, true, VersionedParcel.class.getClassLoader()).getDeclaredMethod("read", VersionedParcel.class);
        this.f2977a.put(str, declaredMethod);
        return declaredMethod;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: e */
    public final Method m3145e(Class cls) throws NoSuchMethodException, ClassNotFoundException, SecurityException {
        Method method = this.f2978b.get(cls.getName());
        if (method != null) {
            return method;
        }
        Class clsM3143c = m3143c(cls);
        System.currentTimeMillis();
        Method declaredMethod = clsM3143c.getDeclaredMethod("write", cls, VersionedParcel.class);
        this.f2978b.put(cls.getName(), declaredMethod);
        return declaredMethod;
    }

    /* renamed from: f */
    public boolean m3146f() {
        return false;
    }

    /* renamed from: g */
    public abstract boolean mo3147g();

    /* renamed from: h */
    public boolean m3148h(boolean z8, int i9) {
        return !mo3153m(i9) ? z8 : mo3147g();
    }

    /* renamed from: i */
    public abstract byte[] mo3149i();

    /* renamed from: j */
    public byte[] m3150j(byte[] bArr, int i9) {
        return !mo3153m(i9) ? bArr : mo3149i();
    }

    /* renamed from: k */
    public abstract CharSequence mo3151k();

    /* renamed from: l */
    public CharSequence m3152l(CharSequence charSequence, int i9) {
        return !mo3153m(i9) ? charSequence : mo3151k();
    }

    /* renamed from: m */
    public abstract boolean mo3153m(int i9);

    /* renamed from: n */
    public <T extends InterfaceC6182b> T m3154n(String str, VersionedParcel versionedParcel) {
        try {
            return (T) m3144d(str).invoke(null, versionedParcel);
        } catch (ClassNotFoundException e9) {
            throw new RuntimeException("VersionedParcel encountered ClassNotFoundException", e9);
        } catch (IllegalAccessException e10) {
            throw new RuntimeException("VersionedParcel encountered IllegalAccessException", e10);
        } catch (NoSuchMethodException e11) {
            throw new RuntimeException("VersionedParcel encountered NoSuchMethodException", e11);
        } catch (InvocationTargetException e12) {
            if (e12.getCause() instanceof RuntimeException) {
                throw ((RuntimeException) e12.getCause());
            }
            throw new RuntimeException("VersionedParcel encountered InvocationTargetException", e12);
        }
    }

    /* renamed from: o */
    public abstract int mo3155o();

    /* renamed from: p */
    public int m3156p(int i9, int i10) {
        return !mo3153m(i10) ? i9 : mo3155o();
    }

    /* renamed from: q */
    public abstract <T extends Parcelable> T mo3157q();

    /* renamed from: r */
    public <T extends Parcelable> T m3158r(T t8, int i9) {
        return !mo3153m(i9) ? t8 : (T) mo3157q();
    }

    /* renamed from: s */
    public abstract String mo3159s();

    /* renamed from: t */
    public String m3160t(String str, int i9) {
        return !mo3153m(i9) ? str : mo3159s();
    }

    /* renamed from: u */
    public <T extends InterfaceC6182b> T m3161u() {
        String strMo3159s = mo3159s();
        if (strMo3159s == null) {
            return null;
        }
        return (T) m3154n(strMo3159s, mo3142b());
    }

    /* renamed from: v */
    public <T extends InterfaceC6182b> T m3162v(T t8, int i9) {
        return !mo3153m(i9) ? t8 : (T) m3161u();
    }

    /* renamed from: w */
    public abstract void mo3163w(int i9);

    /* renamed from: x */
    public void m3164x(boolean z8, boolean z9) {
    }

    /* renamed from: y */
    public abstract void mo3165y(boolean z8);

    /* renamed from: z */
    public void m3166z(boolean z8, int i9) {
        mo3163w(i9);
        mo3165y(z8);
    }
}
