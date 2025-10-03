package com.cyberlink.util;

import java.util.Comparator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import p209u2.AbstractC6375l;

/* loaded from: classes.dex */
public class PriorityThreadPoolExecutor extends AbstractC6375l {

    /* renamed from: e */
    public final AtomicLong f7247e;

    public enum Priority {
        IDLE(1),
        LOW(3),
        NORMAL(5),
        HIGH(7),
        NOW(9);

        private final int value;

        Priority(int i9) {
            this.value = i9;
        }
    }

    /* renamed from: com.cyberlink.util.PriorityThreadPoolExecutor$a */
    public static class C1401a<V> extends AbstractCallableC1403c<V> {

        /* renamed from: e */
        public final Callable<V> f7254e;

        public C1401a(Callable<V> callable) {
            this.f7254e = callable;
        }

        @Override // java.util.concurrent.Callable
        public V call() {
            return this.f7254e.call();
        }
    }

    /* renamed from: com.cyberlink.util.PriorityThreadPoolExecutor$b */
    public static class C1402b extends AbstractRunnableC1405e {

        /* renamed from: e */
        public final Runnable f7255e;

        public C1402b(Runnable runnable) {
            this.f7255e = runnable;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.f7255e.run();
        }
    }

    /* renamed from: com.cyberlink.util.PriorityThreadPoolExecutor$c */
    public static abstract class AbstractCallableC1403c<V> implements Callable<V> {

        /* renamed from: b */
        public final Priority f7256b;

        /* renamed from: c */
        public final long f7257c;

        /* renamed from: d */
        public long f7258d;

        public AbstractCallableC1403c() {
            this(Priority.NORMAL);
        }

        public AbstractCallableC1403c(Priority priority) {
            this(priority, 0L);
        }

        public AbstractCallableC1403c(Priority priority, long j9) {
            this.f7256b = priority;
            this.f7257c = j9;
            this.f7258d = 0L;
        }
    }

    /* renamed from: com.cyberlink.util.PriorityThreadPoolExecutor$d */
    public static class C1404d<V> extends FutureTask<V> implements Comparable<C1404d> {

        /* renamed from: b */
        public final AbstractCallableC1403c<V> f7259b;

        public C1404d(AbstractRunnableC1405e abstractRunnableC1405e, V v8) {
            this(new C1406f(abstractRunnableC1405e, v8));
        }

        @Override // java.lang.Comparable
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public int compareTo(C1404d c1404d) {
            if (this.f7259b.f7256b != c1404d.f7259b.f7256b) {
                return this.f7259b.f7256b.value > c1404d.f7259b.f7256b.value ? -1 : 1;
            }
            if (this.f7259b.f7257c != c1404d.f7259b.f7257c) {
                return this.f7259b.f7257c > c1404d.f7259b.f7257c ? 1 : -1;
            }
            if (this.f7259b.f7258d != c1404d.f7259b.f7258d) {
                return this.f7259b.f7258d > c1404d.f7259b.f7258d ? 1 : -1;
            }
            return 0;
        }

        public C1404d(AbstractCallableC1403c<V> abstractCallableC1403c) {
            super(abstractCallableC1403c);
            this.f7259b = abstractCallableC1403c;
        }
    }

    /* renamed from: com.cyberlink.util.PriorityThreadPoolExecutor$e */
    public static abstract class AbstractRunnableC1405e implements Runnable {

        /* renamed from: b */
        public final Priority f7260b;

        /* renamed from: c */
        public final long f7261c;

        /* renamed from: d */
        public long f7262d;

        public AbstractRunnableC1405e() {
            this(Priority.NORMAL);
        }

        public AbstractRunnableC1405e(Priority priority) {
            this(priority, 0L);
        }

        public AbstractRunnableC1405e(Priority priority, long j9) {
            this.f7260b = priority;
            this.f7261c = j9;
            this.f7262d = 0L;
        }
    }

    /* renamed from: com.cyberlink.util.PriorityThreadPoolExecutor$f */
    public static final class C1406f<T> extends AbstractCallableC1403c<T> {

        /* renamed from: e */
        public final AbstractRunnableC1405e f7263e;

        /* renamed from: f */
        public final T f7264f;

        public C1406f(AbstractRunnableC1405e abstractRunnableC1405e, T t8) {
            super(abstractRunnableC1405e.f7260b, abstractRunnableC1405e.f7261c);
            this.f7263e = abstractRunnableC1405e;
            this.f7264f = t8;
        }

        @Override // java.util.concurrent.Callable
        public T call() {
            this.f7263e.run();
            return this.f7264f;
        }
    }

    public PriorityThreadPoolExecutor(int i9, int i10, long j9, TimeUnit timeUnit, ThreadFactory threadFactory) {
        super(i9, i10, j9, timeUnit, m7344d(), threadFactory);
        this.f7247e = new AtomicLong();
    }

    /* renamed from: d */
    public static BlockingQueue<Runnable> m7344d() {
        return new PriorityBlockingQueue(10, new Comparator() { // from class: u2.m
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return ((Comparable) obj).compareTo((Comparable) obj2);
            }
        });
    }

    /* renamed from: c */
    public void m7345c() {
        getQueue().clear();
    }

    /* renamed from: e */
    public <T> Future<T> m7346e(AbstractCallableC1403c<T> abstractCallableC1403c) {
        C1404d c1404d = new C1404d(abstractCallableC1403c);
        c1404d.f7259b.f7258d = this.f7247e.getAndIncrement();
        execute(c1404d);
        return c1404d;
    }

    @Override // java.util.concurrent.ThreadPoolExecutor, java.util.concurrent.Executor
    public void execute(Runnable runnable) {
        if ((runnable instanceof AbstractRunnableC1405e) || (runnable instanceof C1404d)) {
            super.execute(runnable);
        } else {
            submit(runnable);
        }
    }

    /* renamed from: f */
    public Future<?> m7347f(AbstractRunnableC1405e abstractRunnableC1405e) {
        return m7348g(abstractRunnableC1405e, null);
    }

    /* renamed from: g */
    public <T> Future<T> m7348g(AbstractRunnableC1405e abstractRunnableC1405e, T t8) {
        C1404d c1404d = new C1404d(abstractRunnableC1405e, t8);
        c1404d.f7259b.f7258d = this.f7247e.getAndIncrement();
        execute(c1404d);
        return c1404d;
    }

    @Override // java.util.concurrent.AbstractExecutorService, java.util.concurrent.ExecutorService
    public Future<?> submit(Runnable runnable) {
        return submit(runnable, null);
    }

    @Override // java.util.concurrent.AbstractExecutorService, java.util.concurrent.ExecutorService
    public <T> Future<T> submit(Runnable runnable, T t8) {
        return runnable instanceof AbstractRunnableC1405e ? m7348g((AbstractRunnableC1405e) runnable, t8) : m7346e(new C1406f(new C1402b(runnable), t8));
    }

    public PriorityThreadPoolExecutor(boolean z8, int i9, int i10, long j9, TimeUnit timeUnit, ThreadFactory threadFactory) {
        super(z8, i9, i10, j9, timeUnit, m7344d(), threadFactory);
        this.f7247e = new AtomicLong();
    }

    @Override // java.util.concurrent.AbstractExecutorService, java.util.concurrent.ExecutorService
    public <T> Future<T> submit(Callable<T> callable) {
        if (callable instanceof AbstractCallableC1403c) {
            return m7346e((AbstractCallableC1403c) callable);
        }
        return m7346e(new C1401a(callable));
    }
}
