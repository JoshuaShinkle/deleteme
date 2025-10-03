package org.jivesoftware.smack.util;

import java.util.AbstractQueue;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/* renamed from: org.jivesoftware.smack.util.a */
/* loaded from: classes.dex */
public class BlockingQueueC5607a<E> extends AbstractQueue<E> implements BlockingQueue<E> {

    /* renamed from: b */
    public final E[] f19458b;

    /* renamed from: c */
    public int f19459c;

    /* renamed from: d */
    public int f19460d;

    /* renamed from: e */
    public int f19461e;

    /* renamed from: f */
    public final ReentrantLock f19462f;

    /* renamed from: g */
    public final Condition f19463g;

    /* renamed from: h */
    public final Condition f19464h;

    /* renamed from: i */
    public volatile boolean f19465i = false;

    /* renamed from: org.jivesoftware.smack.util.a$a */
    public class a implements Iterator<E> {

        /* renamed from: b */
        public int f19466b;

        /* renamed from: c */
        public E f19467c;

        /* renamed from: d */
        public int f19468d = -1;

        public a() {
            if (BlockingQueueC5607a.this.f19461e == 0) {
                this.f19466b = -1;
            } else {
                this.f19466b = BlockingQueueC5607a.this.f19459c;
                this.f19467c = (E) BlockingQueueC5607a.this.f19458b[BlockingQueueC5607a.this.f19459c];
            }
        }

        /* renamed from: a */
        public final void m22295a() {
            if (this.f19466b == BlockingQueueC5607a.this.f19460d) {
                this.f19466b = -1;
                this.f19467c = null;
                return;
            }
            E e9 = (E) BlockingQueueC5607a.this.f19458b[this.f19466b];
            this.f19467c = e9;
            if (e9 == null) {
                this.f19466b = -1;
            }
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.f19466b >= 0;
        }

        @Override // java.util.Iterator
        public E next() {
            BlockingQueueC5607a.this.f19462f.lock();
            try {
                int i9 = this.f19466b;
                if (i9 < 0) {
                    throw new NoSuchElementException();
                }
                this.f19468d = i9;
                E e9 = this.f19467c;
                this.f19466b = BlockingQueueC5607a.this.m22288m(i9);
                m22295a();
                return e9;
            } finally {
                BlockingQueueC5607a.this.f19462f.unlock();
            }
        }

        @Override // java.util.Iterator
        public void remove() {
            BlockingQueueC5607a.this.f19462f.lock();
            try {
                int i9 = this.f19468d;
                if (i9 < 0) {
                    throw new IllegalStateException();
                }
                this.f19468d = -1;
                int i10 = BlockingQueueC5607a.this.f19459c;
                BlockingQueueC5607a.this.m22292q(i9);
                if (i9 == i10) {
                    i9 = BlockingQueueC5607a.this.f19459c;
                }
                this.f19466b = i9;
                m22295a();
            } finally {
                BlockingQueueC5607a.this.f19462f.unlock();
            }
        }
    }

    public BlockingQueueC5607a(int i9, boolean z8) {
        if (i9 <= 0) {
            throw new IllegalArgumentException();
        }
        this.f19458b = (E[]) new Object[i9];
        ReentrantLock reentrantLock = new ReentrantLock(z8);
        this.f19462f = reentrantLock;
        this.f19463g = reentrantLock.newCondition();
        this.f19464h = reentrantLock.newCondition();
    }

    /* renamed from: h */
    public static final void m22283h(Object obj) {
        obj.getClass();
    }

    @Override // java.util.concurrent.BlockingQueue
    public int drainTo(Collection<? super E> collection) {
        m22283h(collection);
        if (collection == this) {
            throw new IllegalArgumentException();
        }
        this.f19462f.lock();
        try {
            int iM22288m = this.f19459c;
            int i9 = 0;
            while (i9 < this.f19461e) {
                collection.add(this.f19458b[iM22288m]);
                this.f19458b[iM22288m] = null;
                iM22288m = m22288m(iM22288m);
                i9++;
            }
            if (i9 > 0) {
                this.f19461e = 0;
                this.f19460d = 0;
                this.f19459c = 0;
                this.f19464h.signalAll();
            }
            return i9;
        } finally {
            this.f19462f.unlock();
        }
    }

    /* renamed from: i */
    public final void m22284i() throws InterruptedException {
        if (this.f19465i) {
            throw new InterruptedException();
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public Iterator<E> iterator() {
        this.f19462f.lock();
        try {
            return new a();
        } finally {
            this.f19462f.unlock();
        }
    }

    /* renamed from: j */
    public final E m22285j() {
        E[] eArr = this.f19458b;
        int i9 = this.f19459c;
        E e9 = eArr[i9];
        eArr[i9] = null;
        this.f19459c = m22288m(i9);
        this.f19461e--;
        this.f19464h.signal();
        return e9;
    }

    /* renamed from: k */
    public final boolean m22286k() {
        return !m22287l();
    }

    /* renamed from: l */
    public final boolean m22287l() {
        return this.f19461e == 0;
    }

    /* renamed from: m */
    public final int m22288m(int i9) {
        int i10 = i9 + 1;
        if (i10 == this.f19458b.length) {
            return 0;
        }
        return i10;
    }

    /* renamed from: n */
    public final void m22289n(E e9) {
        E[] eArr = this.f19458b;
        int i9 = this.f19460d;
        eArr[i9] = e9;
        this.f19460d = m22288m(i9);
        this.f19461e++;
        this.f19463g.signal();
    }

    /* renamed from: o */
    public final boolean m22290o() {
        return this.f19461e == this.f19458b.length;
    }

    @Override // java.util.Queue, java.util.concurrent.BlockingQueue
    public boolean offer(E e9) {
        m22283h(e9);
        this.f19462f.lock();
        try {
            if (!m22290o() && !this.f19465i) {
                m22289n(e9);
                this.f19462f.unlock();
                return true;
            }
            this.f19462f.unlock();
            return false;
        } catch (Throwable th) {
            this.f19462f.unlock();
            throw th;
        }
    }

    /* renamed from: p */
    public final boolean m22291p() {
        return !m22290o();
    }

    @Override // java.util.Queue
    public E peek() {
        this.f19462f.lock();
        try {
            return m22287l() ? null : this.f19458b[this.f19459c];
        } finally {
            this.f19462f.unlock();
        }
    }

    @Override // java.util.Queue
    public E poll() {
        this.f19462f.lock();
        try {
            if (!m22287l()) {
                return m22285j();
            }
            this.f19462f.unlock();
            return null;
        } finally {
            this.f19462f.unlock();
        }
    }

    @Override // java.util.concurrent.BlockingQueue
    public void put(E e9) throws InterruptedException {
        m22283h(e9);
        this.f19462f.lockInterruptibly();
        while (m22290o()) {
            try {
                try {
                    this.f19464h.await();
                    m22284i();
                } catch (InterruptedException e10) {
                    this.f19464h.signal();
                    throw e10;
                }
            } finally {
                this.f19462f.unlock();
            }
        }
        m22289n(e9);
    }

    /* renamed from: q */
    public final void m22292q(int i9) {
        int i10 = this.f19459c;
        if (i9 == i10) {
            this.f19458b[i10] = null;
            this.f19459c = m22288m(i10);
        } else {
            while (true) {
                int iM22288m = m22288m(i9);
                if (iM22288m == this.f19460d) {
                    break;
                }
                E[] eArr = this.f19458b;
                eArr[i9] = eArr[iM22288m];
                i9 = iM22288m;
            }
            this.f19458b[i9] = null;
            this.f19460d = i9;
        }
        this.f19461e--;
        this.f19464h.signal();
    }

    /* renamed from: r */
    public void m22293r() {
        this.f19462f.lock();
        try {
            this.f19465i = true;
            this.f19463g.signalAll();
            this.f19464h.signalAll();
        } finally {
            this.f19462f.unlock();
        }
    }

    @Override // java.util.concurrent.BlockingQueue
    public int remainingCapacity() {
        this.f19462f.lock();
        try {
            return this.f19458b.length - this.f19461e;
        } finally {
            this.f19462f.unlock();
        }
    }

    /* renamed from: s */
    public void m22294s() {
        this.f19462f.lock();
        try {
            this.f19465i = false;
        } finally {
            this.f19462f.unlock();
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public int size() {
        this.f19462f.lock();
        try {
            return this.f19461e;
        } finally {
            this.f19462f.unlock();
        }
    }

    @Override // java.util.concurrent.BlockingQueue
    public E take() throws InterruptedException {
        this.f19462f.lockInterruptibly();
        try {
            m22284i();
            while (m22287l()) {
                try {
                    this.f19463g.await();
                    m22284i();
                } catch (InterruptedException e9) {
                    this.f19463g.signal();
                    throw e9;
                }
            }
            return m22285j();
        } finally {
            this.f19462f.unlock();
        }
    }

    @Override // java.util.concurrent.BlockingQueue
    public boolean offer(E e9, long j9, TimeUnit timeUnit) throws InterruptedException {
        m22283h(e9);
        long nanos = timeUnit.toNanos(j9);
        this.f19462f.lockInterruptibly();
        while (!m22291p()) {
            try {
                if (nanos <= 0) {
                    this.f19462f.unlock();
                    return false;
                }
                try {
                    nanos = this.f19464h.awaitNanos(nanos);
                    m22284i();
                } catch (InterruptedException e10) {
                    this.f19464h.signal();
                    throw e10;
                }
            } catch (Throwable th) {
                this.f19462f.unlock();
                throw th;
            }
        }
        m22289n(e9);
        this.f19462f.unlock();
        return true;
    }

    @Override // java.util.concurrent.BlockingQueue
    public E poll(long j9, TimeUnit timeUnit) throws InterruptedException {
        long nanos = timeUnit.toNanos(j9);
        this.f19462f.lockInterruptibly();
        try {
            m22284i();
            while (!m22286k()) {
                if (nanos <= 0) {
                    this.f19462f.unlock();
                    return null;
                }
                try {
                    nanos = this.f19463g.awaitNanos(nanos);
                    m22284i();
                } catch (InterruptedException e9) {
                    this.f19463g.signal();
                    throw e9;
                }
            }
            return m22285j();
        } finally {
            this.f19462f.unlock();
        }
    }

    @Override // java.util.concurrent.BlockingQueue
    public int drainTo(Collection<? super E> collection, int i9) {
        m22283h(collection);
        if (collection == this) {
            throw new IllegalArgumentException();
        }
        int i10 = 0;
        if (i9 <= 0) {
            return 0;
        }
        this.f19462f.lock();
        try {
            int iM22288m = this.f19459c;
            int i11 = this.f19461e;
            if (i9 >= i11) {
                i9 = i11;
            }
            while (i10 < i9) {
                collection.add(this.f19458b[iM22288m]);
                this.f19458b[iM22288m] = null;
                iM22288m = m22288m(iM22288m);
                i10++;
            }
            if (i10 > 0) {
                this.f19461e -= i10;
                this.f19459c = iM22288m;
                this.f19464h.signalAll();
            }
            return i10;
        } finally {
            this.f19462f.unlock();
        }
    }
}
