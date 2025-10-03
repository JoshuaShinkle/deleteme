package com.bumptech.glide.integration.okhttp3;

import android.content.Context;
import com.bumptech.glide.Registry;
import com.bumptech.glide.integration.okhttp3.C0823b;
import java.io.InputStream;
import p073g1.C4813g;
import p172q1.AbstractC6124d;
import p207u0.ComponentCallbacks2C6355e;

/* renamed from: com.bumptech.glide.integration.okhttp3.a */
/* loaded from: classes.dex */
public final class C0822a extends AbstractC6124d {
    @Override // p172q1.AbstractC6124d, p172q1.InterfaceC6126f
    /* renamed from: b */
    public void mo3825b(Context context, ComponentCallbacks2C6355e componentCallbacks2C6355e, Registry registry) {
        registry.m3822s(C4813g.class, InputStream.class, new C0823b.a());
    }
}
