package kotlin;

import p007a6.C0042f;
import p203t5.InterfaceC6316d;
import p257z5.InterfaceC6831a;

/* renamed from: kotlin.a */
/* loaded from: classes2.dex */
public class C5216a {
    /* renamed from: a */
    public static final <T> InterfaceC6316d<T> m20351a(InterfaceC6831a<? extends T> interfaceC6831a) {
        C0042f.m158e(interfaceC6831a, "initializer");
        return new SynchronizedLazyImpl(interfaceC6831a, null, 2, null);
    }
}
