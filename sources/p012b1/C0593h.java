package p012b1;

import com.bumptech.glide.load.engine.C0838g;
import java.util.HashMap;
import java.util.Map;
import p243y0.InterfaceC6589b;

/* renamed from: b1.h */
/* loaded from: classes.dex */
public final class C0593h {

    /* renamed from: a */
    public final Map<InterfaceC6589b, C0838g<?>> f3121a = new HashMap();

    /* renamed from: b */
    public final Map<InterfaceC6589b, C0838g<?>> f3122b = new HashMap();

    /* renamed from: a */
    public C0838g<?> m3275a(InterfaceC6589b interfaceC6589b, boolean z8) {
        return m3276b(z8).get(interfaceC6589b);
    }

    /* renamed from: b */
    public final Map<InterfaceC6589b, C0838g<?>> m3276b(boolean z8) {
        return z8 ? this.f3122b : this.f3121a;
    }

    /* renamed from: c */
    public void m3277c(InterfaceC6589b interfaceC6589b, C0838g<?> c0838g) {
        m3276b(c0838g.m3953n()).put(interfaceC6589b, c0838g);
    }

    /* renamed from: d */
    public void m3278d(InterfaceC6589b interfaceC6589b, C0838g<?> c0838g) {
        Map<InterfaceC6589b, C0838g<?>> mapM3276b = m3276b(c0838g.m3953n());
        if (c0838g.equals(mapM3276b.get(interfaceC6589b))) {
            mapM3276b.remove(interfaceC6589b);
        }
    }
}
