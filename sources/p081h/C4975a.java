package p081h;

import java.util.concurrent.Executor;

/* renamed from: h.a */
/* loaded from: classes.dex */
public class C4975a extends AbstractC4977c {

    /* renamed from: c */
    public static volatile C4975a f17076c;

    /* renamed from: d */
    public static final Executor f17077d = new a();

    /* renamed from: e */
    public static final Executor f17078e = new b();

    /* renamed from: a */
    public AbstractC4977c f17079a;

    /* renamed from: b */
    public AbstractC4977c f17080b;

    /* renamed from: h.a$a */
    public static class a implements Executor {
        @Override // java.util.concurrent.Executor
        public void execute(Runnable runnable) {
            C4975a.m19264d().mo19267c(runnable);
        }
    }

    /* renamed from: h.a$b */
    public static class b implements Executor {
        @Override // java.util.concurrent.Executor
        public void execute(Runnable runnable) {
            C4975a.m19264d().mo19265a(runnable);
        }
    }

    public C4975a() {
        C4976b c4976b = new C4976b();
        this.f17080b = c4976b;
        this.f17079a = c4976b;
    }

    /* renamed from: d */
    public static C4975a m19264d() {
        if (f17076c != null) {
            return f17076c;
        }
        synchronized (C4975a.class) {
            if (f17076c == null) {
                f17076c = new C4975a();
            }
        }
        return f17076c;
    }

    @Override // p081h.AbstractC4977c
    /* renamed from: a */
    public void mo19265a(Runnable runnable) {
        this.f17079a.mo19265a(runnable);
    }

    @Override // p081h.AbstractC4977c
    /* renamed from: b */
    public boolean mo19266b() {
        return this.f17079a.mo19266b();
    }

    @Override // p081h.AbstractC4977c
    /* renamed from: c */
    public void mo19267c(Runnable runnable) {
        this.f17079a.mo19267c(runnable);
    }
}
