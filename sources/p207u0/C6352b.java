package p207u0;

import android.content.Context;
import android.util.Log;
import com.bumptech.glide.Registry;
import com.bumptech.glide.integration.okhttp3.C0822a;
import com.cyberlink.p033u.glide.UAppGlideModule;
import java.util.Collections;
import java.util.Set;

/* renamed from: u0.b */
/* loaded from: classes.dex */
public final class C6352b extends AbstractC6351a {

    /* renamed from: a */
    public final UAppGlideModule f21423a = new UAppGlideModule();

    public C6352b() {
        if (Log.isLoggable("Glide", 3)) {
            Log.d("Glide", "Discovered AppGlideModule from annotation: com.cyberlink.u.glide.UAppGlideModule");
            Log.d("Glide", "Discovered LibraryGlideModule from annotation: com.bumptech.glide.integration.okhttp3.OkHttpLibraryGlideModule");
        }
    }

    @Override // p172q1.AbstractC6121a, p172q1.InterfaceC6122b
    /* renamed from: a */
    public void mo3824a(Context context, C6356f c6356f) {
        this.f21423a.mo3824a(context, c6356f);
    }

    @Override // p172q1.AbstractC6124d, p172q1.InterfaceC6126f
    /* renamed from: b */
    public void mo3825b(Context context, ComponentCallbacks2C6355e componentCallbacks2C6355e, Registry registry) {
        new C0822a().mo3825b(context, componentCallbacks2C6355e, registry);
        this.f21423a.mo3825b(context, componentCallbacks2C6355e, registry);
    }

    @Override // p172q1.AbstractC6121a
    /* renamed from: c */
    public boolean mo7185c() {
        return this.f21423a.mo7185c();
    }

    @Override // p207u0.AbstractC6351a
    /* renamed from: d */
    public Set<Class<?>> mo24377d() {
        return Collections.emptySet();
    }

    @Override // p207u0.AbstractC6351a
    /* renamed from: f, reason: merged with bridge method [inline-methods] */
    public C6353c mo24378e() {
        return new C6353c();
    }
}
