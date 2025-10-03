package p207u0;

import android.content.Context;
import android.content.ContextWrapper;
import android.os.Handler;
import android.os.Looper;
import android.widget.ImageView;
import com.bumptech.glide.Registry;
import com.bumptech.glide.load.engine.C0837f;
import java.util.Map;
import p022c1.InterfaceC0705b;
import p190s1.C6250g;
import p199t1.AbstractC6284i;
import p199t1.C6280e;

/* renamed from: u0.g */
/* loaded from: classes.dex */
public class C6357g extends ContextWrapper {

    /* renamed from: i */
    public static final AbstractC6360j<?, ?> f21452i = new C6354d();

    /* renamed from: a */
    public final Handler f21453a;

    /* renamed from: b */
    public final InterfaceC0705b f21454b;

    /* renamed from: c */
    public final Registry f21455c;

    /* renamed from: d */
    public final C6280e f21456d;

    /* renamed from: e */
    public final C6250g f21457e;

    /* renamed from: f */
    public final Map<Class<?>, AbstractC6360j<?, ?>> f21458f;

    /* renamed from: g */
    public final C0837f f21459g;

    /* renamed from: h */
    public final int f21460h;

    public C6357g(Context context, InterfaceC0705b interfaceC0705b, Registry registry, C6280e c6280e, C6250g c6250g, Map<Class<?>, AbstractC6360j<?, ?>> map, C0837f c0837f, int i9) {
        super(context.getApplicationContext());
        this.f21454b = interfaceC0705b;
        this.f21455c = registry;
        this.f21456d = c6280e;
        this.f21457e = c6250g;
        this.f21458f = map;
        this.f21459g = c0837f;
        this.f21460h = i9;
        this.f21453a = new Handler(Looper.getMainLooper());
    }

    /* renamed from: a */
    public <X> AbstractC6284i<ImageView, X> m24405a(ImageView imageView, Class<X> cls) {
        return this.f21456d.m24054a(imageView, cls);
    }

    /* renamed from: b */
    public InterfaceC0705b m24406b() {
        return this.f21454b;
    }

    /* renamed from: c */
    public C6250g m24407c() {
        return this.f21457e;
    }

    /* renamed from: d */
    public <T> AbstractC6360j<?, T> m24408d(Class<T> cls) {
        AbstractC6360j<?, T> abstractC6360j = (AbstractC6360j) this.f21458f.get(cls);
        if (abstractC6360j == null) {
            for (Map.Entry<Class<?>, AbstractC6360j<?, ?>> entry : this.f21458f.entrySet()) {
                if (entry.getKey().isAssignableFrom(cls)) {
                    abstractC6360j = (AbstractC6360j) entry.getValue();
                }
            }
        }
        return abstractC6360j == null ? (AbstractC6360j<?, T>) f21452i : abstractC6360j;
    }

    /* renamed from: e */
    public C0837f m24409e() {
        return this.f21459g;
    }

    /* renamed from: f */
    public int m24410f() {
        return this.f21460h;
    }

    /* renamed from: g */
    public Handler m24411g() {
        return this.f21453a;
    }

    /* renamed from: h */
    public Registry m24412h() {
        return this.f21455c;
    }
}
