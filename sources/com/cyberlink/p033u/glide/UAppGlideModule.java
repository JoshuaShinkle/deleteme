package com.cyberlink.p033u.glide;

import android.content.Context;
import com.bumptech.glide.Registry;
import com.cyberlink.p033u.glide.C1386a;
import com.cyberlink.p033u.glide.C1387b;
import java.io.InputStream;
import p043d1.C4658f;
import p172q1.AbstractC6121a;
import p207u0.C6356f;
import p207u0.ComponentCallbacks2C6355e;

/* loaded from: classes.dex */
public class UAppGlideModule extends AbstractC6121a {
    @Override // p172q1.AbstractC6121a, p172q1.InterfaceC6122b
    /* renamed from: a */
    public void mo3824a(Context context, C6356f c6356f) {
        super.mo3824a(context, c6356f);
        c6356f.m24403c(6);
        c6356f.m24402b(new C4658f(context, "glide_cache", 524288000L));
    }

    @Override // p172q1.AbstractC6124d, p172q1.InterfaceC6126f
    /* renamed from: b */
    public void mo3825b(Context context, ComponentCallbacks2C6355e componentCallbacks2C6355e, Registry registry) {
        super.mo3825b(context, componentCallbacks2C6355e, registry);
        registry.m3818o(C1387b.c.class, InputStream.class, new C1386a.c());
    }

    @Override // p172q1.AbstractC6121a
    /* renamed from: c */
    public boolean mo7185c() {
        return false;
    }
}
