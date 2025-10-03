package p007a6;

import kotlin.jvm.internal.Lambda;
import p048d6.InterfaceC4684a;

/* renamed from: a6.g */
/* loaded from: classes2.dex */
public class C0043g {

    /* renamed from: a */
    public static final C0044h f136a;

    /* renamed from: b */
    public static final InterfaceC4684a[] f137b;

    static {
        C0044h c0044h = null;
        try {
            c0044h = (C0044h) Class.forName("kotlin.reflect.jvm.internal.ReflectionFactoryImpl").newInstance();
        } catch (ClassCastException | ClassNotFoundException | IllegalAccessException | InstantiationException unused) {
        }
        if (c0044h == null) {
            c0044h = new C0044h();
        }
        f136a = c0044h;
        f137b = new InterfaceC4684a[0];
    }

    /* renamed from: a */
    public static String m168a(Lambda lambda) {
        return f136a.m170b(lambda);
    }
}
