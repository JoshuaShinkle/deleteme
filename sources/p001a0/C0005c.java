package p001a0;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/* renamed from: a0.c */
/* loaded from: classes.dex */
public class C0005c {

    /* renamed from: b */
    public HandlerThread f29b;

    /* renamed from: c */
    public Handler f30c;

    /* renamed from: f */
    public final int f33f;

    /* renamed from: g */
    public final int f34g;

    /* renamed from: h */
    public final String f35h;

    /* renamed from: a */
    public final Object f28a = new Object();

    /* renamed from: e */
    public Handler.Callback f32e = new a();

    /* renamed from: d */
    public int f31d = 0;

    /* renamed from: a0.c$a */
    public class a implements Handler.Callback {
        public a() {
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            int i9 = message.what;
            if (i9 == 0) {
                C0005c.this.m40a();
                return true;
            }
            if (i9 != 1) {
                return true;
            }
            C0005c.this.m41b((Runnable) message.obj);
            return true;
        }
    }

    /* renamed from: a0.c$b */
    public class b implements Runnable {

        /* renamed from: b */
        public final /* synthetic */ Callable f37b;

        /* renamed from: c */
        public final /* synthetic */ Handler f38c;

        /* renamed from: d */
        public final /* synthetic */ d f39d;

        /* renamed from: a0.c$b$a */
        public class a implements Runnable {

            /* renamed from: b */
            public final /* synthetic */ Object f41b;

            public a(Object obj) {
                this.f41b = obj;
            }

            @Override // java.lang.Runnable
            public void run() {
                b.this.f39d.mo29a(this.f41b);
            }
        }

        public b(Callable callable, Handler handler, d dVar) {
            this.f37b = callable;
            this.f38c = handler;
            this.f39d = dVar;
        }

        @Override // java.lang.Runnable
        public void run() throws Exception {
            Object objCall;
            try {
                objCall = this.f37b.call();
            } catch (Exception unused) {
                objCall = null;
            }
            this.f38c.post(new a(objCall));
        }
    }

    /* renamed from: a0.c$c */
    public class c implements Runnable {

        /* renamed from: b */
        public final /* synthetic */ AtomicReference f43b;

        /* renamed from: c */
        public final /* synthetic */ Callable f44c;

        /* renamed from: d */
        public final /* synthetic */ ReentrantLock f45d;

        /* renamed from: e */
        public final /* synthetic */ AtomicBoolean f46e;

        /* renamed from: f */
        public final /* synthetic */ Condition f47f;

        public c(AtomicReference atomicReference, Callable callable, ReentrantLock reentrantLock, AtomicBoolean atomicBoolean, Condition condition) {
            this.f43b = atomicReference;
            this.f44c = callable;
            this.f45d = reentrantLock;
            this.f46e = atomicBoolean;
            this.f47f = condition;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                this.f43b.set(this.f44c.call());
            } catch (Exception unused) {
            }
            this.f45d.lock();
            try {
                this.f46e.set(false);
                this.f47f.signal();
            } finally {
                this.f45d.unlock();
            }
        }
    }

    /* renamed from: a0.c$d */
    public interface d<T> {
        /* renamed from: a */
        void mo29a(T t8);
    }

    public C0005c(String str, int i9, int i10) {
        this.f35h = str;
        this.f34g = i9;
        this.f33f = i10;
    }

    /* renamed from: a */
    public void m40a() {
        synchronized (this.f28a) {
            if (this.f30c.hasMessages(1)) {
                return;
            }
            this.f29b.quit();
            this.f29b = null;
            this.f30c = null;
        }
    }

    /* renamed from: b */
    public void m41b(Runnable runnable) {
        runnable.run();
        synchronized (this.f28a) {
            this.f30c.removeMessages(0);
            Handler handler = this.f30c;
            handler.sendMessageDelayed(handler.obtainMessage(0), this.f33f);
        }
    }

    /* renamed from: c */
    public final void m42c(Runnable runnable) {
        synchronized (this.f28a) {
            if (this.f29b == null) {
                HandlerThread handlerThread = new HandlerThread(this.f35h, this.f34g);
                this.f29b = handlerThread;
                handlerThread.start();
                this.f30c = new Handler(this.f29b.getLooper(), this.f32e);
                this.f31d++;
            }
            this.f30c.removeMessages(0);
            Handler handler = this.f30c;
            handler.sendMessage(handler.obtainMessage(1, runnable));
        }
    }

    /* renamed from: d */
    public <T> void m43d(Callable<T> callable, d<T> dVar) {
        m42c(new b(callable, new Handler(), dVar));
    }

    /* renamed from: e */
    public <T> T m44e(Callable<T> callable, int i9) {
        ReentrantLock reentrantLock = new ReentrantLock();
        Condition conditionNewCondition = reentrantLock.newCondition();
        AtomicReference atomicReference = new AtomicReference();
        AtomicBoolean atomicBoolean = new AtomicBoolean(true);
        m42c(new c(atomicReference, callable, reentrantLock, atomicBoolean, conditionNewCondition));
        reentrantLock.lock();
        try {
            if (!atomicBoolean.get()) {
                return (T) atomicReference.get();
            }
            long nanos = TimeUnit.MILLISECONDS.toNanos(i9);
            do {
                try {
                    nanos = conditionNewCondition.awaitNanos(nanos);
                } catch (InterruptedException unused) {
                }
                if (!atomicBoolean.get()) {
                    return (T) atomicReference.get();
                }
            } while (nanos > 0);
            throw new InterruptedException("timeout");
        } finally {
            reentrantLock.unlock();
        }
    }
}
