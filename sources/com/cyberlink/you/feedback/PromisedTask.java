package com.cyberlink.you.feedback;

import android.os.AsyncTask;
import android.util.Log;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/* loaded from: classes.dex */
public abstract class PromisedTask<TParam, TProgress, TResult> {

    /* renamed from: i */
    public static Executor f13410i = Executors.newFixedThreadPool(20);

    /* renamed from: b */
    public AsyncTask<Object, Object, Object> f13412b;

    /* renamed from: a */
    public Executor f13411a = f13410i;

    /* renamed from: c */
    public PromisedTask<TResult, ?, ?> f13413c = null;

    /* renamed from: d */
    public Integer f13414d = null;

    /* renamed from: e */
    public String f13415e = null;

    /* renamed from: f */
    public Boolean f13416f = Boolean.FALSE;

    /* renamed from: g */
    public TResult f13417g = null;

    /* renamed from: h */
    public InterfaceC3022c f13418h = null;

    public static class CustomErrorException extends RuntimeException {
        public final int errorCode;

        public CustomErrorException(String str, int i9) {
            super(str);
            this.errorCode = i9;
        }
    }

    /* renamed from: com.cyberlink.you.feedback.PromisedTask$a */
    public class AsyncTaskC3020a extends AsyncTask<Object, Object, Object> {

        /* renamed from: com.cyberlink.you.feedback.PromisedTask$a$a */
        public class a implements InterfaceC3022c {
            public a() {
            }
        }

        public AsyncTaskC3020a() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // android.os.AsyncTask
        public Object doInBackground(Object... objArr) {
            PromisedTask.this.f13418h = new a();
            try {
                return PromisedTask.this.mo5659d(objArr[0]);
            } catch (CustomErrorException e9) {
                Log.e("PromisedTask", e9.toString());
                e9.printStackTrace();
                PromisedTask promisedTask = PromisedTask.this;
                promisedTask.m15446n(e9.errorCode, promisedTask.f13415e);
                return null;
            } catch (Exception e10) {
                Log.e("PromisedTask", e10.toString());
                e10.printStackTrace();
                PromisedTask promisedTask2 = PromisedTask.this;
                promisedTask2.m15446n(-2147483647, promisedTask2.f13415e);
                return null;
            } catch (OutOfMemoryError e11) {
                Log.e("PromisedTask", e11.toString());
                e11.printStackTrace();
                PromisedTask promisedTask3 = PromisedTask.this;
                promisedTask3.m15446n(-2147483642, promisedTask3.f13415e);
                System.gc();
                return null;
            }
        }

        @Override // android.os.AsyncTask
        public void onCancelled() {
            PromisedTask promisedTask = PromisedTask.this;
            if (promisedTask.f13413c == null) {
                promisedTask.mo15434j();
                return;
            }
            if (promisedTask.f13414d != null) {
                PromisedTask promisedTask2 = PromisedTask.this;
                promisedTask2.f13413c.mo5702k(promisedTask2.f13414d.intValue(), PromisedTask.this.f13415e);
            } else {
                PromisedTask.this.f13413c.mo15434j();
            }
            PromisedTask.this.m15448p();
        }

        @Override // android.os.AsyncTask
        public void onPostExecute(Object obj) {
            PromisedTask.this.mo15444l(obj);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // android.os.AsyncTask
        public void onProgressUpdate(Object... objArr) {
            PromisedTask.this.m15445m(objArr[0]);
        }
    }

    /* renamed from: com.cyberlink.you.feedback.PromisedTask$b */
    public static abstract class AbstractC3021b<TResult2> extends PromisedTask<TResult2, Void, TResult2> {
        @Override // com.cyberlink.you.feedback.PromisedTask
        /* renamed from: d */
        public TResult2 mo5659d(TResult2 tresult2) {
            return tresult2;
        }

        @Override // com.cyberlink.you.feedback.PromisedTask
        /* renamed from: l */
        public synchronized void mo15444l(TResult2 tresult2) {
            mo5703q(tresult2);
        }

        /* renamed from: q */
        public abstract void mo5703q(TResult2 tresult2);
    }

    /* renamed from: com.cyberlink.you.feedback.PromisedTask$c */
    public interface InterfaceC3022c {
    }

    public PromisedTask() {
        this.f13412b = null;
        this.f13412b = new AsyncTaskC3020a();
    }

    /* renamed from: c */
    public final boolean m15438c(boolean z8) {
        return this.f13412b.cancel(z8);
    }

    /* renamed from: d */
    public abstract TResult mo5659d(TParam tparam);

    /* renamed from: e */
    public final synchronized void m15439e(AbstractC3021b<TResult> abstractC3021b) {
        this.f13413c = abstractC3021b;
        if (this.f13416f.booleanValue()) {
            this.f13413c.m15441g(this.f13411a, this.f13417g);
        }
    }

    /* renamed from: f */
    public final PromisedTask<TParam, TProgress, TResult> m15440f(TParam tparam) {
        return m15441g(this.f13411a, tparam);
    }

    /* renamed from: g */
    public final PromisedTask<TParam, TProgress, TResult> m15441g(Executor executor, TParam tparam) {
        this.f13411a = executor;
        if (!this.f13412b.isCancelled()) {
            this.f13412b.executeOnExecutor(executor, tparam);
        }
        return this;
    }

    /* renamed from: h */
    public final TResult m15442h() {
        return (TResult) this.f13412b.get();
    }

    /* renamed from: i */
    public final boolean m15443i() {
        return this.f13412b.isCancelled();
    }

    /* renamed from: j */
    public void mo15434j() {
        PromisedTask<TResult, ?, ?> promisedTask = this.f13413c;
        if (promisedTask != null) {
            promisedTask.mo15434j();
        }
        m15448p();
    }

    /* renamed from: k */
    public void mo5702k(int i9, String str) {
        this.f13414d = Integer.valueOf(i9);
        this.f13415e = str;
        m15438c(true);
    }

    /* renamed from: l */
    public synchronized void mo15444l(TResult tresult) {
        this.f13417g = tresult;
        PromisedTask<TResult, ?, ?> promisedTask = this.f13413c;
        if (promisedTask != null) {
            promisedTask.m15441g(this.f13411a, tresult);
        }
        this.f13416f = Boolean.TRUE;
    }

    /* renamed from: m */
    public void m15445m(TProgress tprogress) {
    }

    /* renamed from: n */
    public final void m15446n(int i9, String str) {
        mo5702k(i9, str);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: o */
    public final synchronized <TProgress2, TResult2> PromisedTask<TResult, TProgress2, TResult2> m15447o(PromisedTask<TResult, TProgress2, TResult2> promisedTask) {
        this.f13413c = promisedTask;
        if (this.f13416f.booleanValue()) {
            this.f13413c.m15441g(this.f13411a, this.f13417g);
        }
        return promisedTask;
    }

    /* renamed from: p */
    public void m15448p() {
        this.f13413c = null;
    }
}
