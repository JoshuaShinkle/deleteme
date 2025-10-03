package com.bumptech.glide.integration.okhttp3;

import android.content.Context;
import com.bumptech.glide.Registry;
import com.bumptech.glide.integration.okhttp3.C0823b;
import java.io.InputStream;
import p073g1.C4813g;
import p172q1.InterfaceC6123c;
import p207u0.C6356f;
import p207u0.ComponentCallbacks2C6355e;

@Deprecated
/* loaded from: classes.dex */
public class OkHttpGlideModule implements InterfaceC6123c {
    @Override // p172q1.InterfaceC6122b
    /* renamed from: a */
    public void mo3824a(Context context, C6356f c6356f) {
    }

    @Override // p172q1.InterfaceC6126f
    /* renamed from: b */
    public void mo3825b(Context context, ComponentCallbacks2C6355e componentCallbacks2C6355e, Registry registry) {
        registry.m3822s(C4813g.class, InputStream.class, new C0823b.a());
    }
}
