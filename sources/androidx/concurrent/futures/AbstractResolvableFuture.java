package androidx.concurrent.futures;

import com.google.common.util.concurrent.ListenableFuture;
import java.util.Locale;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import java.util.concurrent.locks.LockSupport;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang3.StringUtils;

/* loaded from: classes.dex */
public abstract class AbstractResolvableFuture<V> implements ListenableFuture<V> {
    static final AbstractC0275b ATOMIC_HELPER;
    private static final Object NULL;
    private static final long SPIN_THRESHOLD_NANOS = 1000;
    volatile C0277d listeners;
    volatile Object value;
    volatile C0281h waiters;
    static final boolean GENERATE_CANCELLATION_CAUSES = Boolean.parseBoolean(System.getProperty("guava.concurrent.generate_cancellation_cause", "false"));
    private static final Logger log = Logger.getLogger(AbstractResolvableFuture.class.getName());

    public static final class Failure {

        /* renamed from: b */
        public static final Failure f1263b = new Failure(new Throwable("Failure occurred while trying to finish a future.") { // from class: androidx.concurrent.futures.AbstractResolvableFuture.Failure.1
            @Override // java.lang.Throwable
            public synchronized Throwable fillInStackTrace() {
                return this;
            }
        });

        /* renamed from: a */
        public final Throwable f1264a;

        public Failure(Throwable th) {
            this.f1264a = (Throwable) AbstractResolvableFuture.checkNotNull(th);
        }
    }

    /* renamed from: androidx.concurrent.futures.AbstractResolvableFuture$b */
    public static abstract class AbstractC0275b {
        public AbstractC0275b() {
        }

        /* renamed from: a */
        public abstract boolean mo1131a(AbstractResolvableFuture<?> abstractResolvableFuture, C0277d c0277d, C0277d c0277d2);

        /* renamed from: b */
        public abstract boolean mo1132b(AbstractResolvableFuture<?> abstractResolvableFuture, Object obj, Object obj2);

        /* renamed from: c */
        public abstract boolean mo1133c(AbstractResolvableFuture<?> abstractResolvableFuture, C0281h c0281h, C0281h c0281h2);

        /* renamed from: d */
        public abstract void mo1134d(C0281h c0281h, C0281h c0281h2);

        /* renamed from: e */
        public abstract void mo1135e(C0281h c0281h, Thread thread);
    }

    /* renamed from: androidx.concurrent.futures.AbstractResolvableFuture$c */
    public static final class C0276c {

        /* renamed from: c */
        public static final C0276c f1265c;

        /* renamed from: d */
        public static final C0276c f1266d;

        /* renamed from: a */
        public final boolean f1267a;

        /* renamed from: b */
        public final Throwable f1268b;

        static {
            if (AbstractResolvableFuture.GENERATE_CANCELLATION_CAUSES) {
                f1266d = null;
                f1265c = null;
            } else {
                f1266d = new C0276c(false, null);
                f1265c = new C0276c(true, null);
            }
        }

        public C0276c(boolean z8, Throwable th) {
            this.f1267a = z8;
            this.f1268b = th;
        }
    }

    /* renamed from: androidx.concurrent.futures.AbstractResolvableFuture$d */
    public static final class C0277d {

        /* renamed from: d */
        public static final C0277d f1269d = new C0277d(null, null);

        /* renamed from: a */
        public final Runnable f1270a;

        /* renamed from: b */
        public final Executor f1271b;

        /* renamed from: c */
        public C0277d f1272c;

        public C0277d(Runnable runnable, Executor executor) {
            this.f1270a = runnable;
            this.f1271b = executor;
        }
    }

    /* renamed from: androidx.concurrent.futures.AbstractResolvableFuture$e */
    public static final class C0278e extends AbstractC0275b {

        /* renamed from: a */
        public final AtomicReferenceFieldUpdater<C0281h, Thread> f1273a;

        /* renamed from: b */
        public final AtomicReferenceFieldUpdater<C0281h, C0281h> f1274b;

        /* renamed from: c */
        public final AtomicReferenceFieldUpdater<AbstractResolvableFuture, C0281h> f1275c;

        /* renamed from: d */
        public final AtomicReferenceFieldUpdater<AbstractResolvableFuture, C0277d> f1276d;

        /* renamed from: e */
        public final AtomicReferenceFieldUpdater<AbstractResolvableFuture, Object> f1277e;

        public C0278e(AtomicReferenceFieldUpdater<C0281h, Thread> atomicReferenceFieldUpdater, AtomicReferenceFieldUpdater<C0281h, C0281h> atomicReferenceFieldUpdater2, AtomicReferenceFieldUpdater<AbstractResolvableFuture, C0281h> atomicReferenceFieldUpdater3, AtomicReferenceFieldUpdater<AbstractResolvableFuture, C0277d> atomicReferenceFieldUpdater4, AtomicReferenceFieldUpdater<AbstractResolvableFuture, Object> atomicReferenceFieldUpdater5) {
            super();
            this.f1273a = atomicReferenceFieldUpdater;
            this.f1274b = atomicReferenceFieldUpdater2;
            this.f1275c = atomicReferenceFieldUpdater3;
            this.f1276d = atomicReferenceFieldUpdater4;
            this.f1277e = atomicReferenceFieldUpdater5;
        }

        @Override // androidx.concurrent.futures.AbstractResolvableFuture.AbstractC0275b
        /* renamed from: a */
        public boolean mo1131a(AbstractResolvableFuture<?> abstractResolvableFuture, C0277d c0277d, C0277d c0277d2) {
            return C0282a.m1138a(this.f1276d, abstractResolvableFuture, c0277d, c0277d2);
        }

        @Override // androidx.concurrent.futures.AbstractResolvableFuture.AbstractC0275b
        /* renamed from: b */
        public boolean mo1132b(AbstractResolvableFuture<?> abstractResolvableFuture, Object obj, Object obj2) {
            return C0282a.m1138a(this.f1277e, abstractResolvableFuture, obj, obj2);
        }

        @Override // androidx.concurrent.futures.AbstractResolvableFuture.AbstractC0275b
        /* renamed from: c */
        public boolean mo1133c(AbstractResolvableFuture<?> abstractResolvableFuture, C0281h c0281h, C0281h c0281h2) {
            return C0282a.m1138a(this.f1275c, abstractResolvableFuture, c0281h, c0281h2);
        }

        @Override // androidx.concurrent.futures.AbstractResolvableFuture.AbstractC0275b
        /* renamed from: d */
        public void mo1134d(C0281h c0281h, C0281h c0281h2) {
            this.f1274b.lazySet(c0281h, c0281h2);
        }

        @Override // androidx.concurrent.futures.AbstractResolvableFuture.AbstractC0275b
        /* renamed from: e */
        public void mo1135e(C0281h c0281h, Thread thread) {
            this.f1273a.lazySet(c0281h, thread);
        }
    }

    /* renamed from: androidx.concurrent.futures.AbstractResolvableFuture$f */
    public static final class RunnableC0279f<V> implements Runnable {

        /* renamed from: b */
        public final AbstractResolvableFuture<V> f1278b;

        /* renamed from: c */
        public final ListenableFuture<? extends V> f1279c;

        public RunnableC0279f(AbstractResolvableFuture<V> abstractResolvableFuture, ListenableFuture<? extends V> listenableFuture) {
            this.f1278b = abstractResolvableFuture;
            this.f1279c = listenableFuture;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.f1278b.value != this) {
                return;
            }
            if (AbstractResolvableFuture.ATOMIC_HELPER.mo1132b(this.f1278b, this, AbstractResolvableFuture.getFutureValue(this.f1279c))) {
                AbstractResolvableFuture.complete(this.f1278b);
            }
        }
    }

    /* renamed from: androidx.concurrent.futures.AbstractResolvableFuture$g */
    public static final class C0280g extends AbstractC0275b {
        public C0280g() {
            super();
        }

        @Override // androidx.concurrent.futures.AbstractResolvableFuture.AbstractC0275b
        /* renamed from: a */
        public boolean mo1131a(AbstractResolvableFuture<?> abstractResolvableFuture, C0277d c0277d, C0277d c0277d2) {
            synchronized (abstractResolvableFuture) {
                if (abstractResolvableFuture.listeners != c0277d) {
                    return false;
                }
                abstractResolvableFuture.listeners = c0277d2;
                return true;
            }
        }

        @Override // androidx.concurrent.futures.AbstractResolvableFuture.AbstractC0275b
        /* renamed from: b */
        public boolean mo1132b(AbstractResolvableFuture<?> abstractResolvableFuture, Object obj, Object obj2) {
            synchronized (abstractResolvableFuture) {
                if (abstractResolvableFuture.value != obj) {
                    return false;
                }
                abstractResolvableFuture.value = obj2;
                return true;
            }
        }

        @Override // androidx.concurrent.futures.AbstractResolvableFuture.AbstractC0275b
        /* renamed from: c */
        public boolean mo1133c(AbstractResolvableFuture<?> abstractResolvableFuture, C0281h c0281h, C0281h c0281h2) {
            synchronized (abstractResolvableFuture) {
                if (abstractResolvableFuture.waiters != c0281h) {
                    return false;
                }
                abstractResolvableFuture.waiters = c0281h2;
                return true;
            }
        }

        @Override // androidx.concurrent.futures.AbstractResolvableFuture.AbstractC0275b
        /* renamed from: d */
        public void mo1134d(C0281h c0281h, C0281h c0281h2) {
            c0281h.f1282b = c0281h2;
        }

        @Override // androidx.concurrent.futures.AbstractResolvableFuture.AbstractC0275b
        /* renamed from: e */
        public void mo1135e(C0281h c0281h, Thread thread) {
            c0281h.f1281a = thread;
        }
    }

    /* renamed from: androidx.concurrent.futures.AbstractResolvableFuture$h */
    public static final class C0281h {

        /* renamed from: c */
        public static final C0281h f1280c = new C0281h(false);

        /* renamed from: a */
        public volatile Thread f1281a;

        /* renamed from: b */
        public volatile C0281h f1282b;

        public C0281h(boolean z8) {
        }

        /* renamed from: a */
        public void m1136a(C0281h c0281h) {
            AbstractResolvableFuture.ATOMIC_HELPER.mo1134d(this, c0281h);
        }

        /* renamed from: b */
        public void m1137b() {
            Thread thread = this.f1281a;
            if (thread != null) {
                this.f1281a = null;
                LockSupport.unpark(thread);
            }
        }

        public C0281h() {
            AbstractResolvableFuture.ATOMIC_HELPER.mo1135e(this, Thread.currentThread());
        }
    }

    static {
        AbstractC0275b c0280g;
        try {
            c0280g = new C0278e(AtomicReferenceFieldUpdater.newUpdater(C0281h.class, Thread.class, "a"), AtomicReferenceFieldUpdater.newUpdater(C0281h.class, C0281h.class, "b"), AtomicReferenceFieldUpdater.newUpdater(AbstractResolvableFuture.class, C0281h.class, "waiters"), AtomicReferenceFieldUpdater.newUpdater(AbstractResolvableFuture.class, C0277d.class, "listeners"), AtomicReferenceFieldUpdater.newUpdater(AbstractResolvableFuture.class, Object.class, "value"));
            th = null;
        } catch (Throwable th) {
            th = th;
            c0280g = new C0280g();
        }
        ATOMIC_HELPER = c0280g;
        if (th != null) {
            log.log(Level.SEVERE, "SafeAtomicHelper is broken!", th);
        }
        NULL = new Object();
    }

    private void addDoneString(StringBuilder sb) {
        try {
            Object uninterruptibly = getUninterruptibly(this);
            sb.append("SUCCESS, result=[");
            sb.append(userObjectToString(uninterruptibly));
            sb.append("]");
        } catch (CancellationException unused) {
            sb.append("CANCELLED");
        } catch (RuntimeException e9) {
            sb.append("UNKNOWN, cause=[");
            sb.append(e9.getClass());
            sb.append(" thrown from get()]");
        } catch (ExecutionException e10) {
            sb.append("FAILURE, cause=[");
            sb.append(e10.getCause());
            sb.append("]");
        }
    }

    private static CancellationException cancellationExceptionWithCause(String str, Throwable th) {
        CancellationException cancellationException = new CancellationException(str);
        cancellationException.initCause(th);
        return cancellationException;
    }

    public static <T> T checkNotNull(T t8) {
        t8.getClass();
        return t8;
    }

    private C0277d clearListeners(C0277d c0277d) {
        C0277d c0277d2;
        do {
            c0277d2 = this.listeners;
        } while (!ATOMIC_HELPER.mo1131a(this, c0277d2, C0277d.f1269d));
        C0277d c0277d3 = c0277d;
        C0277d c0277d4 = c0277d2;
        while (c0277d4 != null) {
            C0277d c0277d5 = c0277d4.f1272c;
            c0277d4.f1272c = c0277d3;
            c0277d3 = c0277d4;
            c0277d4 = c0277d5;
        }
        return c0277d3;
    }

    public static void complete(AbstractResolvableFuture<?> abstractResolvableFuture) {
        C0277d c0277d = null;
        while (true) {
            abstractResolvableFuture.releaseWaiters();
            abstractResolvableFuture.afterDone();
            C0277d c0277dClearListeners = abstractResolvableFuture.clearListeners(c0277d);
            while (c0277dClearListeners != null) {
                c0277d = c0277dClearListeners.f1272c;
                Runnable runnable = c0277dClearListeners.f1270a;
                if (runnable instanceof RunnableC0279f) {
                    RunnableC0279f runnableC0279f = (RunnableC0279f) runnable;
                    abstractResolvableFuture = runnableC0279f.f1278b;
                    if (abstractResolvableFuture.value == runnableC0279f) {
                        if (ATOMIC_HELPER.mo1132b(abstractResolvableFuture, runnableC0279f, getFutureValue(runnableC0279f.f1279c))) {
                            break;
                        }
                    } else {
                        continue;
                    }
                } else {
                    executeListener(runnable, c0277dClearListeners.f1271b);
                }
                c0277dClearListeners = c0277d;
            }
            return;
        }
    }

    private static void executeListener(Runnable runnable, Executor executor) {
        try {
            executor.execute(runnable);
        } catch (RuntimeException e9) {
            log.log(Level.SEVERE, "RuntimeException while executing runnable " + runnable + " with executor " + executor, (Throwable) e9);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private V getDoneValue(Object obj) throws ExecutionException {
        if (obj instanceof C0276c) {
            throw cancellationExceptionWithCause("Task was cancelled.", ((C0276c) obj).f1268b);
        }
        if (obj instanceof Failure) {
            throw new ExecutionException(((Failure) obj).f1264a);
        }
        if (obj == NULL) {
            return null;
        }
        return obj;
    }

    static Object getFutureValue(ListenableFuture<?> listenableFuture) {
        if (listenableFuture instanceof AbstractResolvableFuture) {
            Object obj = ((AbstractResolvableFuture) listenableFuture).value;
            if (!(obj instanceof C0276c)) {
                return obj;
            }
            C0276c c0276c = (C0276c) obj;
            return c0276c.f1267a ? c0276c.f1268b != null ? new C0276c(false, c0276c.f1268b) : C0276c.f1266d : obj;
        }
        boolean zIsCancelled = listenableFuture.isCancelled();
        if ((!GENERATE_CANCELLATION_CAUSES) && zIsCancelled) {
            return C0276c.f1266d;
        }
        try {
            Object uninterruptibly = getUninterruptibly(listenableFuture);
            return uninterruptibly == null ? NULL : uninterruptibly;
        } catch (CancellationException e9) {
            if (zIsCancelled) {
                return new C0276c(false, e9);
            }
            return new Failure(new IllegalArgumentException("get() threw CancellationException, despite reporting isCancelled() == false: " + listenableFuture, e9));
        } catch (ExecutionException e10) {
            return new Failure(e10.getCause());
        } catch (Throwable th) {
            return new Failure(th);
        }
    }

    public static <V> V getUninterruptibly(Future<V> future) {
        V v8;
        boolean z8 = false;
        while (true) {
            try {
                v8 = future.get();
                break;
            } catch (InterruptedException unused) {
                z8 = true;
            } catch (Throwable th) {
                if (z8) {
                    Thread.currentThread().interrupt();
                }
                throw th;
            }
        }
        if (z8) {
            Thread.currentThread().interrupt();
        }
        return v8;
    }

    private void releaseWaiters() {
        C0281h c0281h;
        do {
            c0281h = this.waiters;
        } while (!ATOMIC_HELPER.mo1133c(this, c0281h, C0281h.f1280c));
        while (c0281h != null) {
            c0281h.m1137b();
            c0281h = c0281h.f1282b;
        }
    }

    private void removeWaiter(C0281h c0281h) {
        c0281h.f1281a = null;
        while (true) {
            C0281h c0281h2 = this.waiters;
            if (c0281h2 == C0281h.f1280c) {
                return;
            }
            C0281h c0281h3 = null;
            while (c0281h2 != null) {
                C0281h c0281h4 = c0281h2.f1282b;
                if (c0281h2.f1281a != null) {
                    c0281h3 = c0281h2;
                } else if (c0281h3 != null) {
                    c0281h3.f1282b = c0281h4;
                    if (c0281h3.f1281a == null) {
                        break;
                    }
                } else if (!ATOMIC_HELPER.mo1133c(this, c0281h2, c0281h4)) {
                    break;
                }
                c0281h2 = c0281h4;
            }
            return;
        }
    }

    private String userObjectToString(Object obj) {
        return obj == this ? "this future" : String.valueOf(obj);
    }

    @Override // com.google.common.util.concurrent.ListenableFuture
    public final void addListener(Runnable runnable, Executor executor) {
        checkNotNull(runnable);
        checkNotNull(executor);
        C0277d c0277d = this.listeners;
        if (c0277d != C0277d.f1269d) {
            C0277d c0277d2 = new C0277d(runnable, executor);
            do {
                c0277d2.f1272c = c0277d;
                if (ATOMIC_HELPER.mo1131a(this, c0277d, c0277d2)) {
                    return;
                } else {
                    c0277d = this.listeners;
                }
            } while (c0277d != C0277d.f1269d);
        }
        executeListener(runnable, executor);
    }

    public void afterDone() {
    }

    @Override // java.util.concurrent.Future
    public final boolean cancel(boolean z8) {
        Object obj = this.value;
        if (!(obj == null) && !(obj instanceof RunnableC0279f)) {
            return false;
        }
        C0276c c0276c = GENERATE_CANCELLATION_CAUSES ? new C0276c(z8, new CancellationException("Future.cancel() was called.")) : z8 ? C0276c.f1265c : C0276c.f1266d;
        AbstractResolvableFuture<V> abstractResolvableFuture = this;
        boolean z9 = false;
        while (true) {
            if (ATOMIC_HELPER.mo1132b(abstractResolvableFuture, obj, c0276c)) {
                if (z8) {
                    abstractResolvableFuture.interruptTask();
                }
                complete(abstractResolvableFuture);
                if (!(obj instanceof RunnableC0279f)) {
                    return true;
                }
                ListenableFuture<? extends V> listenableFuture = ((RunnableC0279f) obj).f1279c;
                if (!(listenableFuture instanceof AbstractResolvableFuture)) {
                    listenableFuture.cancel(z8);
                    return true;
                }
                abstractResolvableFuture = (AbstractResolvableFuture) listenableFuture;
                obj = abstractResolvableFuture.value;
                if (!(obj == null) && !(obj instanceof RunnableC0279f)) {
                    return true;
                }
                z9 = true;
            } else {
                obj = abstractResolvableFuture.value;
                if (!(obj instanceof RunnableC0279f)) {
                    return z9;
                }
            }
        }
    }

    @Override // java.util.concurrent.Future
    public final V get(long j9, TimeUnit timeUnit) throws InterruptedException, TimeoutException {
        long nanos = timeUnit.toNanos(j9);
        if (Thread.interrupted()) {
            throw new InterruptedException();
        }
        Object obj = this.value;
        if ((obj != null) && (!(obj instanceof RunnableC0279f))) {
            return getDoneValue(obj);
        }
        long jNanoTime = nanos > 0 ? System.nanoTime() + nanos : 0L;
        if (nanos >= 1000) {
            C0281h c0281h = this.waiters;
            if (c0281h != C0281h.f1280c) {
                C0281h c0281h2 = new C0281h();
                do {
                    c0281h2.m1136a(c0281h);
                    if (ATOMIC_HELPER.mo1133c(this, c0281h, c0281h2)) {
                        do {
                            LockSupport.parkNanos(this, nanos);
                            if (Thread.interrupted()) {
                                removeWaiter(c0281h2);
                                throw new InterruptedException();
                            }
                            Object obj2 = this.value;
                            if ((obj2 != null) && (!(obj2 instanceof RunnableC0279f))) {
                                return getDoneValue(obj2);
                            }
                            nanos = jNanoTime - System.nanoTime();
                        } while (nanos >= 1000);
                        removeWaiter(c0281h2);
                    } else {
                        c0281h = this.waiters;
                    }
                } while (c0281h != C0281h.f1280c);
            }
            return getDoneValue(this.value);
        }
        while (nanos > 0) {
            Object obj3 = this.value;
            if ((obj3 != null) && (!(obj3 instanceof RunnableC0279f))) {
                return getDoneValue(obj3);
            }
            if (Thread.interrupted()) {
                throw new InterruptedException();
            }
            nanos = jNanoTime - System.nanoTime();
        }
        String string = toString();
        String string2 = timeUnit.toString();
        Locale locale = Locale.ROOT;
        String lowerCase = string2.toLowerCase(locale);
        String str = "Waited " + j9 + StringUtils.SPACE + timeUnit.toString().toLowerCase(locale);
        if (nanos + 1000 < 0) {
            String str2 = str + " (plus ";
            long j10 = -nanos;
            long jConvert = timeUnit.convert(j10, TimeUnit.NANOSECONDS);
            long nanos2 = j10 - timeUnit.toNanos(jConvert);
            boolean z8 = jConvert == 0 || nanos2 > 1000;
            if (jConvert > 0) {
                String str3 = str2 + jConvert + StringUtils.SPACE + lowerCase;
                if (z8) {
                    str3 = str3 + ",";
                }
                str2 = str3 + StringUtils.SPACE;
            }
            if (z8) {
                str2 = str2 + nanos2 + " nanoseconds ";
            }
            str = str2 + "delay)";
        }
        if (isDone()) {
            throw new TimeoutException(str + " but future completed as timeout expired");
        }
        throw new TimeoutException(str + " for " + string);
    }

    public void interruptTask() {
    }

    @Override // java.util.concurrent.Future
    public final boolean isCancelled() {
        return this.value instanceof C0276c;
    }

    @Override // java.util.concurrent.Future
    public final boolean isDone() {
        return (!(r0 instanceof RunnableC0279f)) & (this.value != null);
    }

    public final void maybePropagateCancellationTo(Future<?> future) {
        if ((future != null) && isCancelled()) {
            future.cancel(wasInterrupted());
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public String pendingToString() {
        Object obj = this.value;
        if (obj instanceof RunnableC0279f) {
            return "setFuture=[" + userObjectToString(((RunnableC0279f) obj).f1279c) + "]";
        }
        if (!(this instanceof ScheduledFuture)) {
            return null;
        }
        return "remaining delay=[" + ((ScheduledFuture) this).getDelay(TimeUnit.MILLISECONDS) + " ms]";
    }

    public boolean set(V v8) {
        if (v8 == null) {
            v8 = (V) NULL;
        }
        if (!ATOMIC_HELPER.mo1132b(this, null, v8)) {
            return false;
        }
        complete(this);
        return true;
    }

    public boolean setException(Throwable th) {
        if (!ATOMIC_HELPER.mo1132b(this, null, new Failure((Throwable) checkNotNull(th)))) {
            return false;
        }
        complete(this);
        return true;
    }

    public boolean setFuture(ListenableFuture<? extends V> listenableFuture) {
        Failure failure;
        checkNotNull(listenableFuture);
        Object obj = this.value;
        if (obj == null) {
            if (listenableFuture.isDone()) {
                if (!ATOMIC_HELPER.mo1132b(this, null, getFutureValue(listenableFuture))) {
                    return false;
                }
                complete(this);
                return true;
            }
            RunnableC0279f runnableC0279f = new RunnableC0279f(this, listenableFuture);
            if (ATOMIC_HELPER.mo1132b(this, null, runnableC0279f)) {
                try {
                    listenableFuture.addListener(runnableC0279f, DirectExecutor.INSTANCE);
                } catch (Throwable th) {
                    try {
                        failure = new Failure(th);
                    } catch (Throwable unused) {
                        failure = Failure.f1263b;
                    }
                    ATOMIC_HELPER.mo1132b(this, runnableC0279f, failure);
                }
                return true;
            }
            obj = this.value;
        }
        if (obj instanceof C0276c) {
            listenableFuture.cancel(((C0276c) obj).f1267a);
        }
        return false;
    }

    public String toString() {
        String strPendingToString;
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append("[status=");
        if (isCancelled()) {
            sb.append("CANCELLED");
        } else if (isDone()) {
            addDoneString(sb);
        } else {
            try {
                strPendingToString = pendingToString();
            } catch (RuntimeException e9) {
                strPendingToString = "Exception thrown from implementation: " + e9.getClass();
            }
            if (strPendingToString != null && !strPendingToString.isEmpty()) {
                sb.append("PENDING, info=[");
                sb.append(strPendingToString);
                sb.append("]");
            } else if (isDone()) {
                addDoneString(sb);
            } else {
                sb.append("PENDING");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public final boolean wasInterrupted() {
        Object obj = this.value;
        return (obj instanceof C0276c) && ((C0276c) obj).f1267a;
    }

    @Override // java.util.concurrent.Future
    public final V get() throws InterruptedException {
        Object obj;
        if (!Thread.interrupted()) {
            Object obj2 = this.value;
            if ((obj2 != null) & (!(obj2 instanceof RunnableC0279f))) {
                return getDoneValue(obj2);
            }
            C0281h c0281h = this.waiters;
            if (c0281h != C0281h.f1280c) {
                C0281h c0281h2 = new C0281h();
                do {
                    c0281h2.m1136a(c0281h);
                    if (ATOMIC_HELPER.mo1133c(this, c0281h, c0281h2)) {
                        do {
                            LockSupport.park(this);
                            if (!Thread.interrupted()) {
                                obj = this.value;
                            } else {
                                removeWaiter(c0281h2);
                                throw new InterruptedException();
                            }
                        } while (!((obj != null) & (!(obj instanceof RunnableC0279f))));
                        return getDoneValue(obj);
                    }
                    c0281h = this.waiters;
                } while (c0281h != C0281h.f1280c);
            }
            return getDoneValue(this.value);
        }
        throw new InterruptedException();
    }
}
