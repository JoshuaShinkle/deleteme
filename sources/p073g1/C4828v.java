package p073g1;

import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import p073g1.InterfaceC4820n;
import p217v1.C6451c;
import p243y0.C6592e;
import p252z0.InterfaceC6805d;

/* renamed from: g1.v */
/* loaded from: classes.dex */
public class C4828v<Model> implements InterfaceC4820n<Model, Model> {

    /* renamed from: a */
    public static final C4828v<?> f16808a = new C4828v<>();

    /* renamed from: g1.v$a */
    public static class a<Model> implements InterfaceC4821o<Model, Model> {

        /* renamed from: a */
        public static final a<?> f16809a = new a<>();

        @Deprecated
        public a() {
        }

        /* renamed from: c */
        public static <T> a<T> m19161c() {
            return (a<T>) f16809a;
        }

        @Override // p073g1.InterfaceC4821o
        /* renamed from: a */
        public InterfaceC4820n<Model, Model> mo3831a(C4824r c4824r) {
            return C4828v.m19160c();
        }

        @Override // p073g1.InterfaceC4821o
        /* renamed from: b */
        public void mo3832b() {
        }
    }

    /* renamed from: g1.v$b */
    public static class b<Model> implements InterfaceC6805d<Model> {

        /* renamed from: b */
        public final Model f16810b;

        public b(Model model) {
            this.f16810b = model;
        }

        @Override // p252z0.InterfaceC6805d
        /* renamed from: a */
        public Class<Model> mo56a() {
            return (Class<Model>) this.f16810b.getClass();
        }

        @Override // p252z0.InterfaceC6805d
        /* renamed from: b */
        public void mo57b() {
        }

        @Override // p252z0.InterfaceC6805d
        public void cancel() {
        }

        @Override // p252z0.InterfaceC6805d
        /* renamed from: e */
        public DataSource mo58e() {
            return DataSource.LOCAL;
        }

        @Override // p252z0.InterfaceC6805d
        /* renamed from: g */
        public void mo59g(Priority priority, InterfaceC6805d.a<? super Model> aVar) {
            aVar.mo3903f(this.f16810b);
        }
    }

    @Deprecated
    public C4828v() {
    }

    /* renamed from: c */
    public static <T> C4828v<T> m19160c() {
        return (C4828v<T>) f16808a;
    }

    @Override // p073g1.InterfaceC4820n
    /* renamed from: a */
    public boolean mo3826a(Model model) {
        return true;
    }

    @Override // p073g1.InterfaceC4820n
    /* renamed from: b */
    public InterfaceC4820n.a<Model> mo3827b(Model model, int i9, int i10, C6592e c6592e) {
        return new InterfaceC4820n.a<>(new C6451c(model), new b(model));
    }
}
