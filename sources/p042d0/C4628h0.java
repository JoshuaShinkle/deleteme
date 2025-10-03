package p042d0;

import android.graphics.Rect;
import android.os.Build;
import android.util.Log;
import android.view.WindowInsets;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Objects;
import p021c0.C0697c;
import p021c0.C0702h;
import p215v.C6429c;

/* renamed from: d0.h0 */
/* loaded from: classes.dex */
public class C4628h0 {

    /* renamed from: b */
    public static final C4628h0 f16248b = new a().m18446a().m18433a().m18434b().m18435c();

    /* renamed from: a */
    public final i f16249a;

    /* renamed from: d0.h0$d */
    public static class d {

        /* renamed from: a */
        public final C4628h0 f16257a;

        public d() {
            this(new C4628h0((C4628h0) null));
        }

        /* renamed from: a */
        public C4628h0 mo18450a() {
            throw null;
        }

        /* renamed from: b */
        public void mo18452b(C6429c c6429c) {
        }

        /* renamed from: c */
        public void mo18451c(C6429c c6429c) {
            throw null;
        }

        public d(C4628h0 c4628h0) {
            this.f16257a = c4628h0;
        }
    }

    /* renamed from: d0.h0$g */
    public static class g extends f {
        public g(C4628h0 c4628h0, WindowInsets windowInsets) {
            super(c4628h0, windowInsets);
        }

        @Override // p042d0.C4628h0.i
        /* renamed from: a */
        public C4628h0 mo18460a() {
            return C4628h0.m18432o(this.f16258b.consumeDisplayCutout());
        }

        @Override // p042d0.C4628h0.i
        /* renamed from: d */
        public C4617c mo18461d() {
            return C4617c.m18397a(this.f16258b.getDisplayCutout());
        }

        @Override // p042d0.C4628h0.i
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof g) {
                return Objects.equals(this.f16258b, ((g) obj).f16258b);
            }
            return false;
        }

        @Override // p042d0.C4628h0.i
        public int hashCode() {
            return this.f16258b.hashCode();
        }

        public g(C4628h0 c4628h0, g gVar) {
            super(c4628h0, gVar);
        }
    }

    /* renamed from: d0.h0$i */
    public static class i {

        /* renamed from: a */
        public final C4628h0 f16264a;

        public i(C4628h0 c4628h0) {
            this.f16264a = c4628h0;
        }

        /* renamed from: a */
        public C4628h0 mo18460a() {
            return this.f16264a;
        }

        /* renamed from: b */
        public C4628h0 mo18456b() {
            return this.f16264a;
        }

        /* renamed from: c */
        public C4628h0 mo18457c() {
            return this.f16264a;
        }

        /* renamed from: d */
        public C4617c mo18461d() {
            return null;
        }

        /* renamed from: e */
        public C6429c mo18458e() {
            return C6429c.f21655e;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof i)) {
                return false;
            }
            i iVar = (i) obj;
            return mo18455i() == iVar.mo18455i() && mo18459h() == iVar.mo18459h() && C0697c.m3461a(mo18453f(), iVar.mo18453f()) && C0697c.m3461a(mo18458e(), iVar.mo18458e()) && C0697c.m3461a(mo18461d(), iVar.mo18461d());
        }

        /* renamed from: f */
        public C6429c mo18453f() {
            return C6429c.f21655e;
        }

        /* renamed from: g */
        public C4628h0 mo18454g(int i9, int i10, int i11, int i12) {
            return C4628h0.f16248b;
        }

        /* renamed from: h */
        public boolean mo18459h() {
            return false;
        }

        public int hashCode() {
            return C0697c.m3462b(Boolean.valueOf(mo18455i()), Boolean.valueOf(mo18459h()), mo18453f(), mo18458e(), mo18461d());
        }

        /* renamed from: i */
        public boolean mo18455i() {
            return false;
        }
    }

    public C4628h0(WindowInsets windowInsets) {
        int i9 = Build.VERSION.SDK_INT;
        if (i9 >= 29) {
            this.f16249a = new h(this, windowInsets);
        } else if (i9 >= 28) {
            this.f16249a = new g(this, windowInsets);
        } else {
            this.f16249a = new f(this, windowInsets);
        }
    }

    /* renamed from: k */
    public static C6429c m18431k(C6429c c6429c, int i9, int i10, int i11, int i12) {
        int iMax = Math.max(0, c6429c.f21656a - i9);
        int iMax2 = Math.max(0, c6429c.f21657b - i10);
        int iMax3 = Math.max(0, c6429c.f21658c - i11);
        int iMax4 = Math.max(0, c6429c.f21659d - i12);
        return (iMax == i9 && iMax2 == i10 && iMax3 == i11 && iMax4 == i12) ? c6429c : C6429c.m24592a(iMax, iMax2, iMax3, iMax4);
    }

    /* renamed from: o */
    public static C4628h0 m18432o(WindowInsets windowInsets) {
        return new C4628h0((WindowInsets) C0702h.m3468b(windowInsets));
    }

    /* renamed from: a */
    public C4628h0 m18433a() {
        return this.f16249a.mo18460a();
    }

    /* renamed from: b */
    public C4628h0 m18434b() {
        return this.f16249a.mo18456b();
    }

    /* renamed from: c */
    public C4628h0 m18435c() {
        return this.f16249a.mo18457c();
    }

    /* renamed from: d */
    public int m18436d() {
        return m18440h().f21659d;
    }

    /* renamed from: e */
    public int m18437e() {
        return m18440h().f21656a;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof C4628h0) {
            return C0697c.m3461a(this.f16249a, ((C4628h0) obj).f16249a);
        }
        return false;
    }

    /* renamed from: f */
    public int m18438f() {
        return m18440h().f21658c;
    }

    /* renamed from: g */
    public int m18439g() {
        return m18440h().f21657b;
    }

    /* renamed from: h */
    public C6429c m18440h() {
        return this.f16249a.mo18453f();
    }

    public int hashCode() {
        i iVar = this.f16249a;
        if (iVar == null) {
            return 0;
        }
        return iVar.hashCode();
    }

    /* renamed from: i */
    public boolean m18441i() {
        return !m18440h().equals(C6429c.f21655e);
    }

    /* renamed from: j */
    public C4628h0 m18442j(int i9, int i10, int i11, int i12) {
        return this.f16249a.mo18454g(i9, i10, i11, i12);
    }

    /* renamed from: l */
    public boolean m18443l() {
        return this.f16249a.mo18459h();
    }

    @Deprecated
    /* renamed from: m */
    public C4628h0 m18444m(int i9, int i10, int i11, int i12) {
        return new a(this).m18448c(C6429c.m24592a(i9, i10, i11, i12)).m18446a();
    }

    /* renamed from: n */
    public WindowInsets m18445n() {
        i iVar = this.f16249a;
        if (iVar instanceof e) {
            return ((e) iVar).f16258b;
        }
        return null;
    }

    /* renamed from: d0.h0$b */
    public static class b extends d {

        /* renamed from: c */
        public static Field f16251c = null;

        /* renamed from: d */
        public static boolean f16252d = false;

        /* renamed from: e */
        public static Constructor<WindowInsets> f16253e = null;

        /* renamed from: f */
        public static boolean f16254f = false;

        /* renamed from: b */
        public WindowInsets f16255b;

        public b() {
            this.f16255b = m18449d();
        }

        /* renamed from: d */
        private static WindowInsets m18449d() {
            if (!f16252d) {
                try {
                    f16251c = WindowInsets.class.getDeclaredField("CONSUMED");
                } catch (ReflectiveOperationException e9) {
                    Log.i("WindowInsetsCompat", "Could not retrieve WindowInsets.CONSUMED field", e9);
                }
                f16252d = true;
            }
            Field field = f16251c;
            if (field != null) {
                try {
                    WindowInsets windowInsets = (WindowInsets) field.get(null);
                    if (windowInsets != null) {
                        return new WindowInsets(windowInsets);
                    }
                } catch (ReflectiveOperationException e10) {
                    Log.i("WindowInsetsCompat", "Could not get value from WindowInsets.CONSUMED field", e10);
                }
            }
            if (!f16254f) {
                try {
                    f16253e = WindowInsets.class.getConstructor(Rect.class);
                } catch (ReflectiveOperationException e11) {
                    Log.i("WindowInsetsCompat", "Could not retrieve WindowInsets(Rect) constructor", e11);
                }
                f16254f = true;
            }
            Constructor<WindowInsets> constructor = f16253e;
            if (constructor != null) {
                try {
                    return constructor.newInstance(new Rect());
                } catch (ReflectiveOperationException e12) {
                    Log.i("WindowInsetsCompat", "Could not invoke WindowInsets(Rect) constructor", e12);
                }
            }
            return null;
        }

        @Override // p042d0.C4628h0.d
        /* renamed from: a */
        public C4628h0 mo18450a() {
            return C4628h0.m18432o(this.f16255b);
        }

        @Override // p042d0.C4628h0.d
        /* renamed from: c */
        public void mo18451c(C6429c c6429c) {
            WindowInsets windowInsets = this.f16255b;
            if (windowInsets != null) {
                this.f16255b = windowInsets.replaceSystemWindowInsets(c6429c.f21656a, c6429c.f21657b, c6429c.f21658c, c6429c.f21659d);
            }
        }

        public b(C4628h0 c4628h0) {
            this.f16255b = c4628h0.m18445n();
        }
    }

    /* renamed from: d0.h0$c */
    public static class c extends d {

        /* renamed from: b */
        public final WindowInsets.Builder f16256b;

        public c() {
            this.f16256b = new WindowInsets.Builder();
        }

        @Override // p042d0.C4628h0.d
        /* renamed from: a */
        public C4628h0 mo18450a() {
            return C4628h0.m18432o(this.f16256b.build());
        }

        @Override // p042d0.C4628h0.d
        /* renamed from: b */
        public void mo18452b(C6429c c6429c) {
            this.f16256b.setStableInsets(c6429c.m24593b());
        }

        @Override // p042d0.C4628h0.d
        /* renamed from: c */
        public void mo18451c(C6429c c6429c) {
            this.f16256b.setSystemWindowInsets(c6429c.m24593b());
        }

        public c(C4628h0 c4628h0) {
            WindowInsets.Builder builder;
            WindowInsets windowInsetsM18445n = c4628h0.m18445n();
            if (windowInsetsM18445n != null) {
                builder = new WindowInsets.Builder(windowInsetsM18445n);
            } else {
                builder = new WindowInsets.Builder();
            }
            this.f16256b = builder;
        }
    }

    /* renamed from: d0.h0$f */
    public static class f extends e {

        /* renamed from: d */
        public C6429c f16260d;

        public f(C4628h0 c4628h0, WindowInsets windowInsets) {
            super(c4628h0, windowInsets);
            this.f16260d = null;
        }

        @Override // p042d0.C4628h0.i
        /* renamed from: b */
        public C4628h0 mo18456b() {
            return C4628h0.m18432o(this.f16258b.consumeStableInsets());
        }

        @Override // p042d0.C4628h0.i
        /* renamed from: c */
        public C4628h0 mo18457c() {
            return C4628h0.m18432o(this.f16258b.consumeSystemWindowInsets());
        }

        @Override // p042d0.C4628h0.i
        /* renamed from: e */
        public final C6429c mo18458e() {
            if (this.f16260d == null) {
                this.f16260d = C6429c.m24592a(this.f16258b.getStableInsetLeft(), this.f16258b.getStableInsetTop(), this.f16258b.getStableInsetRight(), this.f16258b.getStableInsetBottom());
            }
            return this.f16260d;
        }

        @Override // p042d0.C4628h0.i
        /* renamed from: h */
        public boolean mo18459h() {
            return this.f16258b.isConsumed();
        }

        public f(C4628h0 c4628h0, f fVar) {
            super(c4628h0, fVar);
            this.f16260d = null;
        }
    }

    /* renamed from: d0.h0$e */
    public static class e extends i {

        /* renamed from: b */
        public final WindowInsets f16258b;

        /* renamed from: c */
        public C6429c f16259c;

        public e(C4628h0 c4628h0, WindowInsets windowInsets) {
            super(c4628h0);
            this.f16259c = null;
            this.f16258b = windowInsets;
        }

        @Override // p042d0.C4628h0.i
        /* renamed from: f */
        public final C6429c mo18453f() {
            if (this.f16259c == null) {
                this.f16259c = C6429c.m24592a(this.f16258b.getSystemWindowInsetLeft(), this.f16258b.getSystemWindowInsetTop(), this.f16258b.getSystemWindowInsetRight(), this.f16258b.getSystemWindowInsetBottom());
            }
            return this.f16259c;
        }

        @Override // p042d0.C4628h0.i
        /* renamed from: g */
        public C4628h0 mo18454g(int i9, int i10, int i11, int i12) {
            a aVar = new a(C4628h0.m18432o(this.f16258b));
            aVar.m18448c(C4628h0.m18431k(mo18453f(), i9, i10, i11, i12));
            aVar.m18447b(C4628h0.m18431k(mo18458e(), i9, i10, i11, i12));
            return aVar.m18446a();
        }

        @Override // p042d0.C4628h0.i
        /* renamed from: i */
        public boolean mo18455i() {
            return this.f16258b.isRound();
        }

        public e(C4628h0 c4628h0, e eVar) {
            this(c4628h0, new WindowInsets(eVar.f16258b));
        }
    }

    /* renamed from: d0.h0$a */
    public static final class a {

        /* renamed from: a */
        public final d f16250a;

        public a() {
            if (Build.VERSION.SDK_INT >= 29) {
                this.f16250a = new c();
            } else {
                this.f16250a = new b();
            }
        }

        /* renamed from: a */
        public C4628h0 m18446a() {
            return this.f16250a.mo18450a();
        }

        /* renamed from: b */
        public a m18447b(C6429c c6429c) {
            this.f16250a.mo18452b(c6429c);
            return this;
        }

        /* renamed from: c */
        public a m18448c(C6429c c6429c) {
            this.f16250a.mo18451c(c6429c);
            return this;
        }

        public a(C4628h0 c4628h0) {
            if (Build.VERSION.SDK_INT >= 29) {
                this.f16250a = new c(c4628h0);
            } else {
                this.f16250a = new b(c4628h0);
            }
        }
    }

    /* renamed from: d0.h0$h */
    public static class h extends g {

        /* renamed from: e */
        public C6429c f16261e;

        /* renamed from: f */
        public C6429c f16262f;

        /* renamed from: g */
        public C6429c f16263g;

        public h(C4628h0 c4628h0, WindowInsets windowInsets) {
            super(c4628h0, windowInsets);
            this.f16261e = null;
            this.f16262f = null;
            this.f16263g = null;
        }

        @Override // p042d0.C4628h0.e, p042d0.C4628h0.i
        /* renamed from: g */
        public C4628h0 mo18454g(int i9, int i10, int i11, int i12) {
            return C4628h0.m18432o(this.f16258b.inset(i9, i10, i11, i12));
        }

        public h(C4628h0 c4628h0, h hVar) {
            super(c4628h0, hVar);
            this.f16261e = null;
            this.f16262f = null;
            this.f16263g = null;
        }
    }

    public C4628h0(C4628h0 c4628h0) {
        if (c4628h0 != null) {
            i iVar = c4628h0.f16249a;
            int i9 = Build.VERSION.SDK_INT;
            if (i9 >= 29 && (iVar instanceof h)) {
                this.f16249a = new h(this, (h) iVar);
                return;
            }
            if (i9 >= 28 && (iVar instanceof g)) {
                this.f16249a = new g(this, (g) iVar);
                return;
            }
            if (iVar instanceof f) {
                this.f16249a = new f(this, (f) iVar);
                return;
            } else if (iVar instanceof e) {
                this.f16249a = new e(this, (e) iVar);
                return;
            } else {
                this.f16249a = new i(this);
                return;
            }
        }
        this.f16249a = new i(this);
    }
}
