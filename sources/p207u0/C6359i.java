package p207u0;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import com.bumptech.glide.Priority;
import java.util.Iterator;
import p012b1.AbstractC0588c;
import p143n1.C5350c;
import p163p1.C5883n;
import p163p1.C5885p;
import p163p1.InterfaceC5872c;
import p163p1.InterfaceC5873d;
import p163p1.InterfaceC5877h;
import p163p1.InterfaceC5878i;
import p163p1.InterfaceC5882m;
import p190s1.C6250g;
import p190s1.InterfaceC6246c;
import p199t1.AbstractC6284i;
import p199t1.InterfaceC6283h;
import p208u1.InterfaceC6362b;
import p226w1.C6517j;

/* renamed from: u0.i */
/* loaded from: classes.dex */
public class C6359i implements InterfaceC5878i {

    /* renamed from: l */
    public static final C6250g f21482l = C6250g.m23909e(Bitmap.class).mo23507O();

    /* renamed from: m */
    public static final C6250g f21483m = C6250g.m23909e(C5350c.class).mo23507O();

    /* renamed from: n */
    public static final C6250g f21484n = C6250g.m23911i(AbstractC0588c.f3109c).mo23514Z(Priority.LOW).mo23522g0(true);

    /* renamed from: b */
    public final ComponentCallbacks2C6355e f21485b;

    /* renamed from: c */
    public final Context f21486c;

    /* renamed from: d */
    public final InterfaceC5877h f21487d;

    /* renamed from: e */
    public final C5883n f21488e;

    /* renamed from: f */
    public final InterfaceC5882m f21489f;

    /* renamed from: g */
    public final C5885p f21490g;

    /* renamed from: h */
    public final Runnable f21491h;

    /* renamed from: i */
    public final Handler f21492i;

    /* renamed from: j */
    public final InterfaceC5872c f21493j;

    /* renamed from: k */
    public C6250g f21494k;

    /* renamed from: u0.i$a */
    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            C6359i c6359i = C6359i.this;
            c6359i.f21487d.mo23314b(c6359i);
        }
    }

    /* renamed from: u0.i$b */
    public class b implements Runnable {

        /* renamed from: b */
        public final /* synthetic */ InterfaceC6283h f21496b;

        public b(InterfaceC6283h interfaceC6283h) {
            this.f21496b = interfaceC6283h;
        }

        @Override // java.lang.Runnable
        public void run() {
            C6359i.this.m24432m(this.f21496b);
        }
    }

    /* renamed from: u0.i$c */
    public static class c extends AbstractC6284i<View, Object> {
        public c(View view) {
            super(view);
        }

        @Override // p199t1.InterfaceC6283h
        /* renamed from: f */
        public void mo7183f(Object obj, InterfaceC6362b<? super Object> interfaceC6362b) {
        }
    }

    /* renamed from: u0.i$d */
    public static class d implements InterfaceC5872c.a {

        /* renamed from: a */
        public final C5883n f21498a;

        public d(C5883n c5883n) {
            this.f21498a = c5883n;
        }

        @Override // p163p1.InterfaceC5872c.a
        /* renamed from: a */
        public void mo23318a(boolean z8) {
            if (z8) {
                this.f21498a.m23350e();
            }
        }
    }

    public C6359i(ComponentCallbacks2C6355e componentCallbacks2C6355e, InterfaceC5877h interfaceC5877h, InterfaceC5882m interfaceC5882m, Context context) {
        this(componentCallbacks2C6355e, interfaceC5877h, interfaceC5882m, new C5883n(), componentCallbacks2C6355e.m24392h(), context);
    }

    /* renamed from: c */
    public <ResourceType> C6358h<ResourceType> mo23574c(Class<ResourceType> cls) {
        return new C6358h<>(this.f21485b, this, cls, this.f21486c);
    }

    /* renamed from: d */
    public C6358h<Bitmap> mo23575d() {
        return mo23574c(Bitmap.class).mo23566b(f21482l);
    }

    /* renamed from: k */
    public C6358h<Drawable> mo23576k() {
        return mo23574c(Drawable.class);
    }

    /* renamed from: l */
    public void m24431l(View view) {
        m24432m(new c(view));
    }

    /* renamed from: m */
    public void m24432m(InterfaceC6283h<?> interfaceC6283h) {
        if (interfaceC6283h == null) {
            return;
        }
        if (C6517j.m24957r()) {
            m24441w(interfaceC6283h);
        } else {
            this.f21492i.post(new b(interfaceC6283h));
        }
    }

    /* renamed from: n */
    public C6250g m24433n() {
        return this.f21494k;
    }

    /* renamed from: o */
    public <T> AbstractC6360j<?, T> m24434o(Class<T> cls) {
        return this.f21485b.m24394j().m24408d(cls);
    }

    @Override // p163p1.InterfaceC5878i
    public void onDestroy() {
        this.f21490g.onDestroy();
        Iterator<InterfaceC6283h<?>> it = this.f21490g.m23364d().iterator();
        while (it.hasNext()) {
            m24432m(it.next());
        }
        this.f21490g.m23363c();
        this.f21488e.m23348c();
        this.f21487d.mo23313a(this);
        this.f21487d.mo23313a(this.f21493j);
        this.f21492i.removeCallbacks(this.f21491h);
        this.f21485b.m24400t(this);
    }

    @Override // p163p1.InterfaceC5878i
    public void onStart() {
        m24438s();
        this.f21490g.onStart();
    }

    @Override // p163p1.InterfaceC5878i
    public void onStop() {
        m24437r();
        this.f21490g.onStop();
    }

    /* renamed from: p */
    public C6358h<Drawable> m24435p(Integer num) {
        return mo23576k().mo23568r(num);
    }

    /* renamed from: q */
    public C6358h<Drawable> m24436q(Object obj) {
        return mo23576k().mo23569s(obj);
    }

    /* renamed from: r */
    public void m24437r() {
        C6517j.m24941b();
        this.f21488e.m23349d();
    }

    /* renamed from: s */
    public void m24438s() {
        C6517j.m24941b();
        this.f21488e.m23351f();
    }

    /* renamed from: t */
    public void mo23577t(C6250g c6250g) {
        this.f21494k = c6250g.clone().mo23516b();
    }

    public String toString() {
        return super.toString() + "{tracker=" + this.f21488e + ", treeNode=" + this.f21489f + "}";
    }

    /* renamed from: u */
    public void m24439u(InterfaceC6283h<?> interfaceC6283h, InterfaceC6246c interfaceC6246c) {
        this.f21490g.m23365k(interfaceC6283h);
        this.f21488e.m23352g(interfaceC6246c);
    }

    /* renamed from: v */
    public boolean m24440v(InterfaceC6283h<?> interfaceC6283h) {
        InterfaceC6246c interfaceC6246cMo23901h = interfaceC6283h.mo23901h();
        if (interfaceC6246cMo23901h == null) {
            return true;
        }
        if (!this.f21488e.m23347b(interfaceC6246cMo23901h)) {
            return false;
        }
        this.f21490g.m23366l(interfaceC6283h);
        interfaceC6283h.mo23898a(null);
        return true;
    }

    /* renamed from: w */
    public final void m24441w(InterfaceC6283h<?> interfaceC6283h) {
        if (m24440v(interfaceC6283h) || this.f21485b.m24398q(interfaceC6283h) || interfaceC6283h.mo23901h() == null) {
            return;
        }
        InterfaceC6246c interfaceC6246cMo23901h = interfaceC6283h.mo23901h();
        interfaceC6283h.mo23898a(null);
        interfaceC6246cMo23901h.clear();
    }

    public C6359i(ComponentCallbacks2C6355e componentCallbacks2C6355e, InterfaceC5877h interfaceC5877h, InterfaceC5882m interfaceC5882m, C5883n c5883n, InterfaceC5873d interfaceC5873d, Context context) {
        this.f21490g = new C5885p();
        a aVar = new a();
        this.f21491h = aVar;
        Handler handler = new Handler(Looper.getMainLooper());
        this.f21492i = handler;
        this.f21485b = componentCallbacks2C6355e;
        this.f21487d = interfaceC5877h;
        this.f21489f = interfaceC5882m;
        this.f21488e = c5883n;
        this.f21486c = context;
        InterfaceC5872c interfaceC5872cMo23319a = interfaceC5873d.mo23319a(context.getApplicationContext(), new d(c5883n));
        this.f21493j = interfaceC5872cMo23319a;
        if (C6517j.m24956q()) {
            handler.post(aVar);
        } else {
            interfaceC5877h.mo23314b(this);
        }
        interfaceC5877h.mo23314b(interfaceC5872cMo23319a);
        mo23577t(componentCallbacks2C6355e.m24394j().m24407c());
        componentCallbacks2C6355e.m24397p(this);
    }
}
