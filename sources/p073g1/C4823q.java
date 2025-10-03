package p073g1;

import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import p021c0.InterfaceC0699e;
import p073g1.InterfaceC4820n;
import p226w1.C6516i;
import p243y0.C6592e;
import p243y0.InterfaceC6589b;
import p252z0.InterfaceC6805d;

/* renamed from: g1.q */
/* loaded from: classes.dex */
public class C4823q<Model, Data> implements InterfaceC4820n<Model, Data> {

    /* renamed from: a */
    public final List<InterfaceC4820n<Model, Data>> f16783a;

    /* renamed from: b */
    public final InterfaceC0699e<List<Throwable>> f16784b;

    /* renamed from: g1.q$a */
    public static class a<Data> implements InterfaceC6805d<Data>, InterfaceC6805d.a<Data> {

        /* renamed from: b */
        public final List<InterfaceC6805d<Data>> f16785b;

        /* renamed from: c */
        public final InterfaceC0699e<List<Throwable>> f16786c;

        /* renamed from: d */
        public int f16787d;

        /* renamed from: e */
        public Priority f16788e;

        /* renamed from: f */
        public InterfaceC6805d.a<? super Data> f16789f;

        /* renamed from: g */
        public List<Throwable> f16790g;

        public a(List<InterfaceC6805d<Data>> list, InterfaceC0699e<List<Throwable>> interfaceC0699e) {
            this.f16786c = interfaceC0699e;
            C6516i.m24937c(list);
            this.f16785b = list;
            this.f16787d = 0;
        }

        @Override // p252z0.InterfaceC6805d
        /* renamed from: a */
        public Class<Data> mo56a() {
            return this.f16785b.get(0).mo56a();
        }

        @Override // p252z0.InterfaceC6805d
        /* renamed from: b */
        public void mo57b() {
            List<Throwable> list = this.f16790g;
            if (list != null) {
                this.f16786c.mo3464a(list);
            }
            this.f16790g = null;
            Iterator<InterfaceC6805d<Data>> it = this.f16785b.iterator();
            while (it.hasNext()) {
                it.next().mo57b();
            }
        }

        @Override // p252z0.InterfaceC6805d.a
        /* renamed from: c */
        public void mo3902c(Exception exc) {
            ((List) C6516i.m24938d(this.f16790g)).add(exc);
            m19137d();
        }

        @Override // p252z0.InterfaceC6805d
        public void cancel() {
            Iterator<InterfaceC6805d<Data>> it = this.f16785b.iterator();
            while (it.hasNext()) {
                it.next().cancel();
            }
        }

        /* renamed from: d */
        public final void m19137d() {
            if (this.f16787d < this.f16785b.size() - 1) {
                this.f16787d++;
                mo59g(this.f16788e, this.f16789f);
            } else {
                C6516i.m24938d(this.f16790g);
                this.f16789f.mo3902c(new GlideException("Fetch failed", new ArrayList(this.f16790g)));
            }
        }

        @Override // p252z0.InterfaceC6805d
        /* renamed from: e */
        public DataSource mo58e() {
            return this.f16785b.get(0).mo58e();
        }

        @Override // p252z0.InterfaceC6805d.a
        /* renamed from: f */
        public void mo3903f(Data data) {
            if (data != null) {
                this.f16789f.mo3903f(data);
            } else {
                m19137d();
            }
        }

        @Override // p252z0.InterfaceC6805d
        /* renamed from: g */
        public void mo59g(Priority priority, InterfaceC6805d.a<? super Data> aVar) {
            this.f16788e = priority;
            this.f16789f = aVar;
            this.f16790g = this.f16786c.mo3465b();
            this.f16785b.get(this.f16787d).mo59g(priority, this);
        }
    }

    public C4823q(List<InterfaceC4820n<Model, Data>> list, InterfaceC0699e<List<Throwable>> interfaceC0699e) {
        this.f16783a = list;
        this.f16784b = interfaceC0699e;
    }

    @Override // p073g1.InterfaceC4820n
    /* renamed from: a */
    public boolean mo3826a(Model model) {
        Iterator<InterfaceC4820n<Model, Data>> it = this.f16783a.iterator();
        while (it.hasNext()) {
            if (it.next().mo3826a(model)) {
                return true;
            }
        }
        return false;
    }

    @Override // p073g1.InterfaceC4820n
    /* renamed from: b */
    public InterfaceC4820n.a<Data> mo3827b(Model model, int i9, int i10, C6592e c6592e) {
        InterfaceC4820n.a<Data> aVarMo3827b;
        int size = this.f16783a.size();
        ArrayList arrayList = new ArrayList(size);
        InterfaceC6589b interfaceC6589b = null;
        for (int i11 = 0; i11 < size; i11++) {
            InterfaceC4820n<Model, Data> interfaceC4820n = this.f16783a.get(i11);
            if (interfaceC4820n.mo3826a(model) && (aVarMo3827b = interfaceC4820n.mo3827b(model, i9, i10, c6592e)) != null) {
                interfaceC6589b = aVarMo3827b.f16776a;
                arrayList.add(aVarMo3827b.f16778c);
            }
        }
        if (arrayList.isEmpty() || interfaceC6589b == null) {
            return null;
        }
        return new InterfaceC4820n.a<>(interfaceC6589b, new a(arrayList, this.f16784b));
    }

    public String toString() {
        return "MultiModelLoader{modelLoaders=" + Arrays.toString(this.f16783a.toArray()) + '}';
    }
}
