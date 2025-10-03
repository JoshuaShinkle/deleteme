package p182r2;

import com.cyberlink.util.PriorityThreadPoolExecutor;
import com.cyberlink.you.friends.Group;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import p182r2.RunnableC6211s;
import p209u2.AbstractC6381r;
import p209u2.ThreadFactoryC6373j;

/* renamed from: r2.v */
/* loaded from: classes.dex */
public class C6214v extends AbstractC6195d<C6210r> {

    /* renamed from: f */
    public final PriorityThreadPoolExecutor f20918f = new PriorityThreadPoolExecutor(true, 0, 4, 60, TimeUnit.SECONDS, new ThreadFactoryC6373j("XSender.R"));

    /* renamed from: g */
    public boolean f20919g = false;

    /* renamed from: r2.v$a */
    public class a extends AbstractC6381r<List<C6210r>, Void> {

        /* renamed from: c */
        public final /* synthetic */ AbstractC6381r f20920c;

        public a(AbstractC6381r abstractC6381r) {
            this.f20920c = abstractC6381r;
        }

        @Override // p209u2.AbstractC6381r
        /* renamed from: k, reason: merged with bridge method [inline-methods] */
        public void m24503g(List<C6210r> list) {
            C6214v.this.m23750u(list);
            AbstractC6381r abstractC6381r = this.f20920c;
            if (abstractC6381r != null) {
                abstractC6381r.m24505c();
            }
        }

        @Override // p209u2.AbstractC6381r
        /* renamed from: l, reason: merged with bridge method [inline-methods] */
        public void m24504h(Void r12) {
            AbstractC6381r abstractC6381r = this.f20920c;
            if (abstractC6381r != null) {
                abstractC6381r.m24507e();
            }
        }
    }

    /* renamed from: r2.v$b */
    public class b extends AbstractC6381r<C6210r, Void> {
        public b() {
        }

        @Override // p209u2.AbstractC6381r
        /* renamed from: k, reason: merged with bridge method [inline-methods] */
        public void m24503g(C6210r c6210r) {
            C6214v.this.m23743q(c6210r);
        }
    }

    /* renamed from: r2.v$c */
    public class c extends AbstractC6381r<C6210r, Void> {

        /* renamed from: c */
        public final /* synthetic */ AbstractC6381r f20923c;

        public c(AbstractC6381r abstractC6381r) {
            this.f20923c = abstractC6381r;
        }

        @Override // p209u2.AbstractC6381r
        /* renamed from: k, reason: merged with bridge method [inline-methods] */
        public void m24503g(C6210r c6210r) {
            C6214v.this.m23743q(c6210r);
            AbstractC6381r abstractC6381r = this.f20923c;
            if (abstractC6381r != null) {
                abstractC6381r.m24505c();
            }
        }

        @Override // p209u2.AbstractC6381r
        /* renamed from: l, reason: merged with bridge method [inline-methods] */
        public void m24504h(Void r12) {
            AbstractC6381r abstractC6381r = this.f20923c;
            if (abstractC6381r != null) {
                abstractC6381r.m24507e();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: p */
    public /* synthetic */ void m23742p(ArrayList arrayList) {
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            m23743q((C6210r) it.next());
        }
    }

    @Override // p182r2.AbstractC6195d
    /* renamed from: d */
    public String mo23688d() {
        return "XSender.R";
    }

    /* renamed from: m */
    public void m23744m() {
        this.f20864c.getQueue().clear();
        this.f20918f.m7345c();
    }

    /* renamed from: n */
    public void m23745n(boolean z8) {
        if (z8) {
            this.f20918f.m24495a();
        } else {
            this.f20918f.m24496b();
        }
    }

    /* renamed from: o */
    public void m23746o() {
        if (this.f20919g) {
            throw new IllegalStateException("Has initialized");
        }
        this.f20919g = true;
        this.f20864c.submit(new RunnableC6211s(new RunnableC6211s.a() { // from class: r2.u
            @Override // p182r2.RunnableC6211s.a
            /* renamed from: a */
            public final void mo23739a(ArrayList arrayList) {
                this.f20917a.m23742p(arrayList);
            }
        }));
    }

    @Override // p182r2.AbstractC6195d
    /* renamed from: r, reason: merged with bridge method [inline-methods] */
    public void mo23669g(final C6210r c6210r) {
        super.mo23669g(c6210r);
        if (c6210r.f20913d.decrementAndGet() > 0) {
            C6218z.m23770i("XSender.R", "> retry: %s", c6210r.f20911b);
            this.f20864c.submit(new Runnable() { // from class: r2.t
                @Override // java.lang.Runnable
                public final void run() {
                    this.f20915b.m23743q(c6210r);
                }
            });
        }
    }

    /* renamed from: s */
    public void m23748s(Group group, AbstractC6381r<Void, Void> abstractC6381r) {
        this.f20864c.submit(new RunnableC6207o(group, new a(abstractC6381r)));
    }

    /* renamed from: t */
    public void m23749t(String str, String str2) {
        this.f20864c.submit(new RunnableC6209q(str, str2, new b()));
    }

    /* renamed from: u */
    public void m23750u(List<C6210r> list) {
        m23752w((C6210r[]) list.toArray(new C6210r[0]));
    }

    /* renamed from: v, reason: merged with bridge method [inline-methods] */
    public void m23743q(C6210r c6210r) {
        this.f20918f.m7347f(new C6215w(this, c6210r));
    }

    /* renamed from: w */
    public void m23752w(C6210r... c6210rArr) {
        for (C6210r c6210r : c6210rArr) {
            if (c6210r != null) {
                m23743q(c6210r);
            }
        }
    }

    /* renamed from: x */
    public void m23753x(Group group, String str, AbstractC6381r<Void, Void> abstractC6381r) {
        this.f20864c.submit(new RunnableC6217y(group, str, new c(abstractC6381r)));
    }
}
