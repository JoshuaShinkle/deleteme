package p087h5;

import android.os.AsyncTask;
import java.util.concurrent.Executor;

/* renamed from: h5.a */
/* loaded from: classes2.dex */
public abstract class AbstractAsyncTaskC5023a<Params, Progress, Result> extends AsyncTask<Params, Progress, Result> {

    /* renamed from: c */
    public static final b<?> f17341c = new a();

    /* renamed from: a */
    public Throwable f17342a;

    /* renamed from: b */
    public b<Result> f17343b = (b<Result>) f17341c;

    /* renamed from: h5.a$a */
    public static class a<Result> implements b<Result> {
        @Override // p087h5.AbstractAsyncTaskC5023a.b
        /* renamed from: a */
        public void mo18163a(AbstractAsyncTaskC5023a<?, ?, Result> abstractAsyncTaskC5023a, Throwable th) throws Throwable {
            throw th;
        }

        @Override // p087h5.AbstractAsyncTaskC5023a.b
        /* renamed from: b */
        public void mo18164b(AbstractAsyncTaskC5023a<?, ?, Result> abstractAsyncTaskC5023a, Result result) {
        }

        @Override // p087h5.AbstractAsyncTaskC5023a.b
        /* renamed from: c */
        public void mo18165c(AbstractAsyncTaskC5023a<?, ?, Result> abstractAsyncTaskC5023a, Result result) {
        }

        @Override // p087h5.AbstractAsyncTaskC5023a.b
        /* renamed from: d */
        public void mo18166d(AbstractAsyncTaskC5023a<?, ?, Result> abstractAsyncTaskC5023a, Result result) {
        }
    }

    /* renamed from: h5.a$b */
    public interface b<Result> {
        /* renamed from: a */
        void mo18163a(AbstractAsyncTaskC5023a<?, ?, Result> abstractAsyncTaskC5023a, Throwable th);

        /* renamed from: b */
        void mo18164b(AbstractAsyncTaskC5023a<?, ?, Result> abstractAsyncTaskC5023a, Result result);

        /* renamed from: c */
        void mo18165c(AbstractAsyncTaskC5023a<?, ?, Result> abstractAsyncTaskC5023a, Result result);

        /* renamed from: d */
        void mo18166d(AbstractAsyncTaskC5023a<?, ?, Result> abstractAsyncTaskC5023a, Result result);
    }

    /* renamed from: a */
    public abstract Result mo19588a(Params... paramsArr);

    /* renamed from: b */
    public AbstractAsyncTaskC5023a<Params, Progress, Result> m19589b(b<Result> bVar, Executor executor, Params... paramsArr) {
        if (bVar != null) {
            this.f17343b = bVar;
        }
        executeOnExecutor(executor, paramsArr);
        return this;
    }

    /* renamed from: c */
    public void m19590c(Throwable th) {
        this.f17343b.mo18163a(this, th);
    }

    /* renamed from: d */
    public void m19591d(Result result) {
        this.f17343b.mo18164b(this, result);
    }

    @Override // android.os.AsyncTask
    public final Result doInBackground(Params... paramsArr) {
        try {
            return mo19588a(paramsArr);
        } catch (Throwable th) {
            this.f17342a = th;
            return null;
        }
    }

    /* renamed from: e */
    public void m19592e(Result result) {
        this.f17343b.mo18165c(this, result);
    }

    @Override // android.os.AsyncTask
    public final void onCancelled() {
    }

    @Override // android.os.AsyncTask
    public void onCancelled(Result result) {
        this.f17343b.mo18166d(this, result);
        m19591d(result);
    }

    @Override // android.os.AsyncTask
    public final void onPostExecute(Result result) {
        try {
            Throwable th = this.f17342a;
            if (th == null) {
                m19592e(result);
            } else {
                try {
                    m19590c(th);
                } catch (Throwable th2) {
                    throw C5026d.m19600a(th2);
                }
            }
        } finally {
            m19591d(result);
        }
    }
}
