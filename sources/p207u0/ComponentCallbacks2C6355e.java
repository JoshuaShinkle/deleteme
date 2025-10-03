package p207u0;

import android.content.ComponentCallbacks2;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.AssetFileDescriptor;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import com.bumptech.glide.MemoryCategory;
import com.bumptech.glide.Registry;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.C0837f;
import com.bumptech.glide.load.resource.bitmap.C0849a;
import com.bumptech.glide.load.resource.bitmap.C0850b;
import java.io.File;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import p022c1.InterfaceC0705b;
import p022c1.InterfaceC0707d;
import p043d1.InterfaceC4661i;
import p063f1.C4777a;
import p073g1.C4807a;
import p073g1.C4808b;
import p073g1.C4809c;
import p073g1.C4810d;
import p073g1.C4811e;
import p073g1.C4812f;
import p073g1.C4813g;
import p073g1.C4817k;
import p073g1.C4825s;
import p073g1.C4826t;
import p073g1.C4827u;
import p073g1.C4828v;
import p073g1.C4829w;
import p073g1.C4830x;
import p083h1.C4979a;
import p083h1.C4980b;
import p083h1.C4981c;
import p083h1.C4982d;
import p083h1.C4983e;
import p103j1.C5068a;
import p103j1.C5069b;
import p103j1.C5070c;
import p103j1.C5073f;
import p103j1.C5077j;
import p103j1.C5080m;
import p103j1.C5084q;
import p103j1.C5087t;
import p103j1.C5089v;
import p113k1.C5112a;
import p124l1.C5275d;
import p124l1.C5276e;
import p134m1.C5312a;
import p143n1.C5348a;
import p143n1.C5350c;
import p143n1.C5351d;
import p143n1.C5355h;
import p143n1.C5357j;
import p152o1.C5394a;
import p152o1.C5395b;
import p152o1.C5396c;
import p152o1.C5397d;
import p163p1.C5881l;
import p163p1.InterfaceC5873d;
import p172q1.C6125e;
import p172q1.InterfaceC6123c;
import p190s1.C6250g;
import p199t1.C6280e;
import p199t1.InterfaceC6283h;
import p225w0.InterfaceC6503a;
import p226w1.C6516i;
import p226w1.C6517j;
import p243y0.InterfaceC6593f;
import p252z0.C6812k;

/* renamed from: u0.e */
/* loaded from: classes.dex */
public class ComponentCallbacks2C6355e implements ComponentCallbacks2 {

    /* renamed from: m */
    public static volatile ComponentCallbacks2C6355e f21424m;

    /* renamed from: n */
    public static volatile boolean f21425n;

    /* renamed from: b */
    public final C0837f f21426b;

    /* renamed from: c */
    public final InterfaceC0707d f21427c;

    /* renamed from: d */
    public final InterfaceC4661i f21428d;

    /* renamed from: e */
    public final C4777a f21429e;

    /* renamed from: f */
    public final C6357g f21430f;

    /* renamed from: g */
    public final Registry f21431g;

    /* renamed from: h */
    public final InterfaceC0705b f21432h;

    /* renamed from: i */
    public final C5881l f21433i;

    /* renamed from: j */
    public final InterfaceC5873d f21434j;

    /* renamed from: k */
    public final List<C6359i> f21435k = new ArrayList();

    /* renamed from: l */
    public MemoryCategory f21436l = MemoryCategory.NORMAL;

    public ComponentCallbacks2C6355e(Context context, C0837f c0837f, InterfaceC4661i interfaceC4661i, InterfaceC0707d interfaceC0707d, InterfaceC0705b interfaceC0705b, C5881l c5881l, InterfaceC5873d interfaceC5873d, int i9, C6250g c6250g, Map<Class<?>, AbstractC6360j<?, ?>> map) {
        this.f21426b = c0837f;
        this.f21427c = interfaceC0707d;
        this.f21432h = interfaceC0705b;
        this.f21428d = interfaceC4661i;
        this.f21433i = c5881l;
        this.f21434j = interfaceC5873d;
        this.f21429e = new C4777a(interfaceC4661i, interfaceC0707d, (DecodeFormat) c6250g.m23942s().m25209c(C0849a.f3895f));
        Resources resources = context.getResources();
        Registry registry = new Registry();
        this.f21431g = registry;
        if (Build.VERSION.SDK_INT >= 27) {
            registry.m3819p(new C5080m());
        }
        registry.m3819p(new C5077j());
        C0849a c0849a = new C0849a(registry.m3810g(), resources.getDisplayMetrics(), interfaceC0707d, interfaceC0705b);
        C5348a c5348a = new C5348a(context, registry.m3810g(), interfaceC0707d, interfaceC0705b);
        InterfaceC6593f<ParcelFileDescriptor, Bitmap> interfaceC6593fM19915g = C5089v.m19915g(interfaceC0707d);
        C5073f c5073f = new C5073f(c0849a);
        C0850b c0850b = new C0850b(c0849a, interfaceC0705b);
        C5275d c5275d = new C5275d(context);
        C4825s.c cVar = new C4825s.c(resources);
        C4825s.d dVar = new C4825s.d(resources);
        C4825s.b bVar = new C4825s.b(resources);
        C4825s.a aVar = new C4825s.a(resources);
        C5070c c5070c = new C5070c(interfaceC0705b);
        C5394a c5394a = new C5394a();
        C5397d c5397d = new C5397d();
        ContentResolver contentResolver = context.getContentResolver();
        Registry registryM3821r = registry.m3806c(ByteBuffer.class, new C4809c()).m3806c(InputStream.class, new C4826t(interfaceC0705b)).m3808e("Bitmap", ByteBuffer.class, Bitmap.class, c5073f).m3808e("Bitmap", InputStream.class, Bitmap.class, c0850b).m3808e("Bitmap", ParcelFileDescriptor.class, Bitmap.class, interfaceC6593fM19915g).m3808e("Bitmap", AssetFileDescriptor.class, Bitmap.class, C5089v.m19911c(interfaceC0707d)).m3804a(Bitmap.class, Bitmap.class, C4828v.a.m19161c()).m3808e("Bitmap", Bitmap.class, Bitmap.class, new C5087t()).m3807d(Bitmap.class, c5070c).m3808e("BitmapDrawable", ByteBuffer.class, BitmapDrawable.class, new C5068a(resources, c5073f)).m3808e("BitmapDrawable", InputStream.class, BitmapDrawable.class, new C5068a(resources, c0850b)).m3808e("BitmapDrawable", ParcelFileDescriptor.class, BitmapDrawable.class, new C5068a(resources, interfaceC6593fM19915g)).m3807d(BitmapDrawable.class, new C5069b(interfaceC0707d, c5070c)).m3808e("Gif", InputStream.class, C5350c.class, new C5357j(registry.m3810g(), c5348a, interfaceC0705b)).m3808e("Gif", ByteBuffer.class, C5350c.class, c5348a).m3807d(C5350c.class, new C5351d()).m3804a(InterfaceC6503a.class, InterfaceC6503a.class, C4828v.a.m19161c()).m3808e("Bitmap", InterfaceC6503a.class, Bitmap.class, new C5355h(interfaceC0707d)).m3805b(Uri.class, Drawable.class, c5275d).m3805b(Uri.class, Bitmap.class, new C5084q(c5275d, interfaceC0707d)).m3821r(new C5112a.a()).m3804a(File.class, ByteBuffer.class, new C4810d.b()).m3804a(File.class, InputStream.class, new C4812f.e()).m3805b(File.class, File.class, new C5312a()).m3804a(File.class, ParcelFileDescriptor.class, new C4812f.b()).m3804a(File.class, File.class, C4828v.a.m19161c()).m3821r(new C6812k.a(interfaceC0705b));
        Class cls = Integer.TYPE;
        registryM3821r.m3804a(cls, InputStream.class, cVar).m3804a(cls, ParcelFileDescriptor.class, bVar).m3804a(Integer.class, InputStream.class, cVar).m3804a(Integer.class, ParcelFileDescriptor.class, bVar).m3804a(Integer.class, Uri.class, dVar).m3804a(cls, AssetFileDescriptor.class, aVar).m3804a(Integer.class, AssetFileDescriptor.class, aVar).m3804a(cls, Uri.class, dVar).m3804a(String.class, InputStream.class, new C4811e.c()).m3804a(Uri.class, InputStream.class, new C4811e.c()).m3804a(String.class, InputStream.class, new C4827u.c()).m3804a(String.class, ParcelFileDescriptor.class, new C4827u.b()).m3804a(String.class, AssetFileDescriptor.class, new C4827u.a()).m3804a(Uri.class, InputStream.class, new C4980b.a()).m3804a(Uri.class, InputStream.class, new C4807a.c(context.getAssets())).m3804a(Uri.class, ParcelFileDescriptor.class, new C4807a.b(context.getAssets())).m3804a(Uri.class, InputStream.class, new C4981c.a(context)).m3804a(Uri.class, InputStream.class, new C4982d.a(context)).m3804a(Uri.class, InputStream.class, new C4829w.d(contentResolver)).m3804a(Uri.class, ParcelFileDescriptor.class, new C4829w.b(contentResolver)).m3804a(Uri.class, AssetFileDescriptor.class, new C4829w.a(contentResolver)).m3804a(Uri.class, InputStream.class, new C4830x.a()).m3804a(URL.class, InputStream.class, new C4983e.a()).m3804a(Uri.class, File.class, new C4817k.a(context)).m3804a(C4813g.class, InputStream.class, new C4979a.a()).m3804a(byte[].class, ByteBuffer.class, new C4808b.a()).m3804a(byte[].class, InputStream.class, new C4808b.d()).m3804a(Uri.class, Uri.class, C4828v.a.m19161c()).m3804a(Drawable.class, Drawable.class, C4828v.a.m19161c()).m3805b(Drawable.class, Drawable.class, new C5276e()).m3820q(Bitmap.class, BitmapDrawable.class, new C5395b(resources)).m3820q(Bitmap.class, byte[].class, c5394a).m3820q(Drawable.class, byte[].class, new C5396c(interfaceC0707d, c5394a, c5397d)).m3820q(C5350c.class, byte[].class, c5397d);
        this.f21430f = new C6357g(context, interfaceC0705b, registry, new C6280e(), c6250g, map, c0837f, i9);
    }

    /* renamed from: a */
    public static void m24380a(Context context) throws PackageManager.NameNotFoundException {
        if (f21425n) {
            throw new IllegalStateException("You cannot call Glide.get() in registerComponents(), use the provided Glide instance instead");
        }
        f21425n = true;
        m24384n(context);
        f21425n = false;
    }

    /* renamed from: d */
    public static ComponentCallbacks2C6355e m24381d(Context context) {
        if (f21424m == null) {
            synchronized (ComponentCallbacks2C6355e.class) {
                if (f21424m == null) {
                    m24380a(context);
                }
            }
        }
        return f21424m;
    }

    /* renamed from: e */
    public static AbstractC6351a m24382e() {
        try {
            return (AbstractC6351a) C6352b.class.getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (ClassNotFoundException unused) {
            if (Log.isLoggable("Glide", 5)) {
                Log.w("Glide", "Failed to find GeneratedAppGlideModule. You should include an annotationProcessor compile dependency on com.github.bumptech.glide:compiler in your application and a @GlideModule annotated AppGlideModule implementation or LibraryGlideModules will be silently ignored");
            }
            return null;
        } catch (IllegalAccessException e9) {
            m24386r(e9);
            return null;
        } catch (InstantiationException e10) {
            m24386r(e10);
            return null;
        } catch (NoSuchMethodException e11) {
            m24386r(e11);
            return null;
        } catch (InvocationTargetException e12) {
            m24386r(e12);
            return null;
        }
    }

    /* renamed from: m */
    public static C5881l m24383m(Context context) {
        C6516i.m24939e(context, "You cannot start a load on a not yet attached View or a Fragment where getActivity() returns null (which usually occurs when getActivity() is called before the Fragment is attached or after the Fragment is destroyed).");
        return m24381d(context).m24396l();
    }

    /* renamed from: n */
    public static void m24384n(Context context) throws PackageManager.NameNotFoundException {
        m24385o(context, new C6356f());
    }

    /* renamed from: o */
    public static void m24385o(Context context, C6356f c6356f) throws PackageManager.NameNotFoundException {
        Context applicationContext = context.getApplicationContext();
        AbstractC6351a abstractC6351aM24382e = m24382e();
        List<InterfaceC6123c> listEmptyList = Collections.emptyList();
        if (abstractC6351aM24382e == null || abstractC6351aM24382e.mo7185c()) {
            listEmptyList = new C6125e(applicationContext).m23459a();
        }
        if (abstractC6351aM24382e != null && !abstractC6351aM24382e.mo24377d().isEmpty()) {
            Set<Class<?>> setMo24377d = abstractC6351aM24382e.mo24377d();
            Iterator<InterfaceC6123c> it = listEmptyList.iterator();
            while (it.hasNext()) {
                InterfaceC6123c next = it.next();
                if (setMo24377d.contains(next.getClass())) {
                    if (Log.isLoggable("Glide", 3)) {
                        Log.d("Glide", "AppGlideModule excludes manifest GlideModule: " + next);
                    }
                    it.remove();
                }
            }
        }
        if (Log.isLoggable("Glide", 3)) {
            Iterator<InterfaceC6123c> it2 = listEmptyList.iterator();
            while (it2.hasNext()) {
                Log.d("Glide", "Discovered GlideModule from manifest: " + it2.next().getClass());
            }
        }
        c6356f.m24404d(abstractC6351aM24382e != null ? abstractC6351aM24382e.mo24378e() : null);
        Iterator<InterfaceC6123c> it3 = listEmptyList.iterator();
        while (it3.hasNext()) {
            it3.next().mo3824a(applicationContext, c6356f);
        }
        if (abstractC6351aM24382e != null) {
            abstractC6351aM24382e.mo3824a(applicationContext, c6356f);
        }
        ComponentCallbacks2C6355e componentCallbacks2C6355eM24401a = c6356f.m24401a(applicationContext);
        Iterator<InterfaceC6123c> it4 = listEmptyList.iterator();
        while (it4.hasNext()) {
            it4.next().mo3825b(applicationContext, componentCallbacks2C6355eM24401a, componentCallbacks2C6355eM24401a.f21431g);
        }
        if (abstractC6351aM24382e != null) {
            abstractC6351aM24382e.mo3825b(applicationContext, componentCallbacks2C6355eM24401a, componentCallbacks2C6355eM24401a.f21431g);
        }
        applicationContext.registerComponentCallbacks(componentCallbacks2C6355eM24401a);
        f21424m = componentCallbacks2C6355eM24401a;
    }

    /* renamed from: r */
    public static void m24386r(Exception exc) {
        throw new IllegalStateException("GeneratedAppGlideModuleImpl is implemented incorrectly. If you've manually implemented this class, remove your implementation. The Annotation processor will generate a correct implementation.", exc);
    }

    /* renamed from: u */
    public static C6359i m24387u(Context context) {
        return m24383m(context).m23337d(context);
    }

    /* renamed from: b */
    public void m24388b() {
        C6517j.m24940a();
        this.f21426b.m3933e();
    }

    /* renamed from: c */
    public void m24389c() {
        C6517j.m24941b();
        this.f21428d.m18618b();
        this.f21427c.mo3486b();
        this.f21432h.mo3479b();
    }

    /* renamed from: f */
    public InterfaceC0705b m24390f() {
        return this.f21432h;
    }

    /* renamed from: g */
    public InterfaceC0707d m24391g() {
        return this.f21427c;
    }

    /* renamed from: h */
    public InterfaceC5873d m24392h() {
        return this.f21434j;
    }

    /* renamed from: i */
    public Context m24393i() {
        return this.f21430f.getBaseContext();
    }

    /* renamed from: j */
    public C6357g m24394j() {
        return this.f21430f;
    }

    /* renamed from: k */
    public Registry m24395k() {
        return this.f21431g;
    }

    /* renamed from: l */
    public C5881l m24396l() {
        return this.f21433i;
    }

    @Override // android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
    }

    @Override // android.content.ComponentCallbacks
    public void onLowMemory() {
        m24389c();
    }

    @Override // android.content.ComponentCallbacks2
    public void onTrimMemory(int i9) {
        m24399s(i9);
    }

    /* renamed from: p */
    public void m24397p(C6359i c6359i) {
        synchronized (this.f21435k) {
            if (this.f21435k.contains(c6359i)) {
                throw new IllegalStateException("Cannot register already registered manager");
            }
            this.f21435k.add(c6359i);
        }
    }

    /* renamed from: q */
    public boolean m24398q(InterfaceC6283h<?> interfaceC6283h) {
        synchronized (this.f21435k) {
            Iterator<C6359i> it = this.f21435k.iterator();
            while (it.hasNext()) {
                if (it.next().m24440v(interfaceC6283h)) {
                    return true;
                }
            }
            return false;
        }
    }

    /* renamed from: s */
    public void m24399s(int i9) {
        C6517j.m24941b();
        this.f21428d.mo18610a(i9);
        this.f21427c.mo3485a(i9);
        this.f21432h.mo3478a(i9);
    }

    /* renamed from: t */
    public void m24400t(C6359i c6359i) {
        synchronized (this.f21435k) {
            if (!this.f21435k.contains(c6359i)) {
                throw new IllegalStateException("Cannot unregister not yet registered manager");
            }
            this.f21435k.remove(c6359i);
        }
    }
}
