package p007a6;

import kotlin.jvm.internal.Lambda;

/* renamed from: a6.h */
/* loaded from: classes2.dex */
public class C0044h {
    /* renamed from: a */
    public String m169a(InterfaceC0041e interfaceC0041e) {
        String string = interfaceC0041e.getClass().getGenericInterfaces()[0].toString();
        return string.startsWith("kotlin.jvm.functions.") ? string.substring(21) : string;
    }

    /* renamed from: b */
    public String m170b(Lambda lambda) {
        return m169a(lambda);
    }
}
