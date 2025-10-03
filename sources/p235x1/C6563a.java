package p235x1;

import android.util.Log;
import java.util.ArrayList;
import java.util.List;
import p021c0.C0700f;
import p021c0.C0701g;
import p021c0.InterfaceC0699e;

/* renamed from: x1.a */
/* loaded from: classes.dex */
public final class C6563a {

    /* renamed from: a */
    public static final g<Object> f22069a = new a();

    /* renamed from: x1.a$a */
    public class a implements g<Object> {
        @Override // p235x1.C6563a.g
        /* renamed from: a */
        public void mo25131a(Object obj) {
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* renamed from: x1.a$b */
    public class b<T> implements d<List<T>> {
        @Override // p235x1.C6563a.d
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public List<T> mo3287a() {
            return new ArrayList();
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* renamed from: x1.a$c */
    public class c<T> implements g<List<T>> {
        @Override // p235x1.C6563a.g
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void mo25131a(List<T> list) {
            list.clear();
        }
    }

    /* renamed from: x1.a$d */
    public interface d<T> {
        /* renamed from: a */
        T mo3287a();
    }

    /* renamed from: x1.a$e */
    public static final class e<T> implements InterfaceC0699e<T> {

        /* renamed from: a */
        public final d<T> f22070a;

        /* renamed from: b */
        public final g<T> f22071b;

        /* renamed from: c */
        public final InterfaceC0699e<T> f22072c;

        public e(InterfaceC0699e<T> interfaceC0699e, d<T> dVar, g<T> gVar) {
            this.f22072c = interfaceC0699e;
            this.f22070a = dVar;
            this.f22071b = gVar;
        }

        @Override // p021c0.InterfaceC0699e
        /* renamed from: a */
        public boolean mo3464a(T t8) {
            if (t8 instanceof f) {
                ((f) t8).mo3286j().mo25139b(true);
            }
            this.f22071b.mo25131a(t8);
            return this.f22072c.mo3464a(t8);
        }

        @Override // p021c0.InterfaceC0699e
        /* renamed from: b */
        public T mo3465b() {
            T tMo3465b = this.f22072c.mo3465b();
            if (tMo3465b == null) {
                tMo3465b = this.f22070a.mo3287a();
                if (Log.isLoggable("FactoryPools", 2)) {
                    Log.v("FactoryPools", "Created new " + tMo3465b.getClass());
                }
            }
            if (tMo3465b instanceof f) {
                tMo3465b.mo3286j().mo25139b(false);
            }
            return (T) tMo3465b;
        }
    }

    /* renamed from: x1.a$f */
    public interface f {
        /* renamed from: j */
        AbstractC6565c mo3286j();
    }

    /* renamed from: x1.a$g */
    public interface g<T> {
        /* renamed from: a */
        void mo25131a(T t8);
    }

    /* renamed from: a */
    public static <T extends f> InterfaceC0699e<T> m25124a(InterfaceC0699e<T> interfaceC0699e, d<T> dVar) {
        return m25125b(interfaceC0699e, dVar, m25126c());
    }

    /* renamed from: b */
    public static <T> InterfaceC0699e<T> m25125b(InterfaceC0699e<T> interfaceC0699e, d<T> dVar, g<T> gVar) {
        return new e(interfaceC0699e, dVar, gVar);
    }

    /* renamed from: c */
    public static <T> g<T> m25126c() {
        return (g<T>) f22069a;
    }

    /* renamed from: d */
    public static <T extends f> InterfaceC0699e<T> m25127d(int i9, d<T> dVar) {
        return m25124a(new C0700f(i9), dVar);
    }

    /* renamed from: e */
    public static <T extends f> InterfaceC0699e<T> m25128e(int i9, d<T> dVar) {
        return m25124a(new C0701g(i9), dVar);
    }

    /* renamed from: f */
    public static <T> InterfaceC0699e<List<T>> m25129f() {
        return m25130g(20);
    }

    /* renamed from: g */
    public static <T> InterfaceC0699e<List<T>> m25130g(int i9) {
        return m25125b(new C0701g(i9), new b(), new c());
    }
}
