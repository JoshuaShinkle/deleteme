package p138m5;

import com.perfectcorp.ycl.commons.concurrent.CallingThread;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executor;
import p077g5.C4959a;
import p147n5.C5368f;
import p147n5.C5369g;
import p147n5.InterfaceC5367e;

/* renamed from: m5.b */
/* loaded from: classes2.dex */
public class C5327b {

    /* renamed from: a */
    public final Map<Class<? extends InterfaceC5367e>, Set<? extends b<?>>> f18158a = new IdentityHashMap();

    /* renamed from: m5.b$a */
    public interface a<T extends InterfaceC5367e> {
        /* renamed from: a */
        void mo18340a(C5327b c5327b, T t8);
    }

    /* renamed from: m5.b$b */
    public static final class b<T extends InterfaceC5367e> {

        /* renamed from: a */
        public final a<T> f18159a;

        /* renamed from: b */
        public final Executor f18160b;

        public b(a<T> aVar, Executor executor) {
            this.f18159a = (a) C4959a.m19235b(aVar, "listener == null");
            this.f18160b = executor == null ? CallingThread.MAIN : executor;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof b) && this.f18159a == ((b) obj).f18159a;
        }

        public int hashCode() {
            return System.identityHashCode(this.f18159a);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: d */
    public /* synthetic */ void m20924d(b bVar, InterfaceC5367e interfaceC5367e) {
        bVar.f18159a.mo18340a(this, interfaceC5367e);
    }

    /* renamed from: b */
    public void mo20629b() {
        this.f18158a.clear();
    }

    /* renamed from: c */
    public final <T extends InterfaceC5367e> Set<b<T>> m20925c(Class<T> cls) {
        return (Set) this.f18158a.get(cls);
    }

    /* renamed from: e */
    public final <T extends InterfaceC5367e> Set<b<T>> m20926e(Class<T> cls) {
        Set<b<T>> setM20925c = m20925c(cls);
        if (setM20925c != null) {
            return setM20925c;
        }
        HashSet hashSet = new HashSet();
        this.f18158a.put(cls, hashSet);
        return hashSet;
    }

    /* renamed from: f */
    public <T extends InterfaceC5367e> void m20927f(final T t8) {
        Iterable<b<T>> iterableM20929h = m20929h(t8.getClass());
        if (iterableM20929h != null) {
            for (final b<T> bVar : iterableM20929h) {
                bVar.f18160b.execute(new Runnable() { // from class: m5.a
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f18155b.m20924d(bVar, t8);
                    }
                });
            }
        }
    }

    /* renamed from: g */
    public void m20928g(C5368f c5368f) {
        Iterator<? extends InterfaceC5367e> it = c5368f.asIterable().iterator();
        while (it.hasNext()) {
            m20927f(it.next());
        }
    }

    /* renamed from: h */
    public final <T extends InterfaceC5367e> Iterable<b<T>> m20929h(Class<T> cls) {
        synchronized (this.f18158a) {
            Set<b<T>> setM20925c = m20925c(cls);
            if (setM20925c == null) {
                return null;
            }
            return new ArrayList(setM20925c);
        }
    }

    /* renamed from: i */
    public <T extends InterfaceC5367e> void m20930i(Class<T> cls, a<T> aVar) {
        m20931j(cls, aVar, CallingThread.MAIN);
    }

    /* renamed from: j */
    public <T extends InterfaceC5367e> void m20931j(Class<T> cls, a<T> aVar, Executor executor) {
        C5369g.checkMessageType(cls);
        synchronized (this.f18158a) {
            m20926e(cls).add(new b<>(aVar, executor));
        }
    }
}
