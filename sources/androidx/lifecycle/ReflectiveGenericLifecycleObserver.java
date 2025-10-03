package androidx.lifecycle;

import androidx.lifecycle.C0380a;
import androidx.lifecycle.Lifecycle;
import java.lang.reflect.InvocationTargetException;

/* loaded from: classes.dex */
class ReflectiveGenericLifecycleObserver implements InterfaceC0383d {

    /* renamed from: a */
    public final Object f2207a;

    /* renamed from: b */
    public final C0380a.a f2208b;

    public ReflectiveGenericLifecycleObserver(Object obj) {
        this.f2207a = obj;
        this.f2208b = C0380a.f2210c.m2068c(obj.getClass());
    }

    @Override // androidx.lifecycle.InterfaceC0383d
    /* renamed from: c */
    public void mo209c(InterfaceC0385f interfaceC0385f, Lifecycle.Event event) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        this.f2208b.m2072a(interfaceC0385f, event, this.f2207a);
    }
}
