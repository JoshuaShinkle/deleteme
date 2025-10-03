package p071g;

import android.view.View;
import android.view.animation.Interpolator;
import java.util.ArrayList;
import java.util.Iterator;
import p042d0.C4620d0;
import p042d0.C4624f0;
import p042d0.InterfaceC4622e0;

/* renamed from: g.h */
/* loaded from: classes.dex */
public class C4802h {

    /* renamed from: c */
    public Interpolator f16715c;

    /* renamed from: d */
    public InterfaceC4622e0 f16716d;

    /* renamed from: e */
    public boolean f16717e;

    /* renamed from: b */
    public long f16714b = -1;

    /* renamed from: f */
    public final C4624f0 f16718f = new a();

    /* renamed from: a */
    public final ArrayList<C4620d0> f16713a = new ArrayList<>();

    /* renamed from: g.h$a */
    public class a extends C4624f0 {

        /* renamed from: a */
        public boolean f16719a = false;

        /* renamed from: b */
        public int f16720b = 0;

        public a() {
        }

        @Override // p042d0.InterfaceC4622e0
        /* renamed from: b */
        public void mo350b(View view) {
            int i9 = this.f16720b + 1;
            this.f16720b = i9;
            if (i9 == C4802h.this.f16713a.size()) {
                InterfaceC4622e0 interfaceC4622e0 = C4802h.this.f16716d;
                if (interfaceC4622e0 != null) {
                    interfaceC4622e0.mo350b(null);
                }
                m19070d();
            }
        }

        @Override // p042d0.C4624f0, p042d0.InterfaceC4622e0
        /* renamed from: c */
        public void mo351c(View view) {
            if (this.f16719a) {
                return;
            }
            this.f16719a = true;
            InterfaceC4622e0 interfaceC4622e0 = C4802h.this.f16716d;
            if (interfaceC4622e0 != null) {
                interfaceC4622e0.mo351c(null);
            }
        }

        /* renamed from: d */
        public void m19070d() {
            this.f16720b = 0;
            this.f16719a = false;
            C4802h.this.m19063b();
        }
    }

    /* renamed from: a */
    public void m19062a() {
        if (this.f16717e) {
            Iterator<C4620d0> it = this.f16713a.iterator();
            while (it.hasNext()) {
                it.next().m18409b();
            }
            this.f16717e = false;
        }
    }

    /* renamed from: b */
    public void m19063b() {
        this.f16717e = false;
    }

    /* renamed from: c */
    public C4802h m19064c(C4620d0 c4620d0) {
        if (!this.f16717e) {
            this.f16713a.add(c4620d0);
        }
        return this;
    }

    /* renamed from: d */
    public C4802h m19065d(C4620d0 c4620d0, C4620d0 c4620d02) {
        this.f16713a.add(c4620d0);
        c4620d02.m18415h(c4620d0.m18410c());
        this.f16713a.add(c4620d02);
        return this;
    }

    /* renamed from: e */
    public C4802h m19066e(long j9) {
        if (!this.f16717e) {
            this.f16714b = j9;
        }
        return this;
    }

    /* renamed from: f */
    public C4802h m19067f(Interpolator interpolator) {
        if (!this.f16717e) {
            this.f16715c = interpolator;
        }
        return this;
    }

    /* renamed from: g */
    public C4802h m19068g(InterfaceC4622e0 interfaceC4622e0) {
        if (!this.f16717e) {
            this.f16716d = interfaceC4622e0;
        }
        return this;
    }

    /* renamed from: h */
    public void m19069h() {
        if (this.f16717e) {
            return;
        }
        Iterator<C4620d0> it = this.f16713a.iterator();
        while (it.hasNext()) {
            C4620d0 next = it.next();
            long j9 = this.f16714b;
            if (j9 >= 0) {
                next.m18411d(j9);
            }
            Interpolator interpolator = this.f16715c;
            if (interpolator != null) {
                next.m18412e(interpolator);
            }
            if (this.f16716d != null) {
                next.m18413f(this.f16718f);
            }
            next.m18417j();
        }
        this.f16717e = true;
    }
}
