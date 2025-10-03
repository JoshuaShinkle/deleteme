package p226w1;

import java.io.IOException;
import java.io.InputStream;
import java.util.Queue;

/* renamed from: w1.d */
/* loaded from: classes.dex */
public class C6511d extends InputStream {

    /* renamed from: d */
    public static final Queue<C6511d> f21914d = C6517j.m24945f(0);

    /* renamed from: b */
    public InputStream f21915b;

    /* renamed from: c */
    public IOException f21916c;

    /* renamed from: u */
    public static C6511d m24919u(InputStream inputStream) {
        C6511d c6511dPoll;
        Queue<C6511d> queue = f21914d;
        synchronized (queue) {
            c6511dPoll = queue.poll();
        }
        if (c6511dPoll == null) {
            c6511dPoll = new C6511d();
        }
        c6511dPoll.m24921v(inputStream);
        return c6511dPoll;
    }

    @Override // java.io.InputStream
    public int available() {
        return this.f21915b.available();
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.f21915b.close();
    }

    /* renamed from: f */
    public IOException m24920f() {
        return this.f21916c;
    }

    @Override // java.io.InputStream
    public void mark(int i9) {
        this.f21915b.mark(i9);
    }

    @Override // java.io.InputStream
    public boolean markSupported() {
        return this.f21915b.markSupported();
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr) {
        try {
            return this.f21915b.read(bArr);
        } catch (IOException e9) {
            this.f21916c = e9;
            return -1;
        }
    }

    public void release() {
        this.f21916c = null;
        this.f21915b = null;
        Queue<C6511d> queue = f21914d;
        synchronized (queue) {
            queue.offer(this);
        }
    }

    @Override // java.io.InputStream
    public synchronized void reset() {
        this.f21915b.reset();
    }

    @Override // java.io.InputStream
    public long skip(long j9) {
        try {
            return this.f21915b.skip(j9);
        } catch (IOException e9) {
            this.f21916c = e9;
            return 0L;
        }
    }

    /* renamed from: v */
    public void m24921v(InputStream inputStream) {
        this.f21915b = inputStream;
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i9, int i10) {
        try {
            return this.f21915b.read(bArr, i9, i10);
        } catch (IOException e9) {
            this.f21916c = e9;
            return -1;
        }
    }

    @Override // java.io.InputStream
    public int read() {
        try {
            return this.f21915b.read();
        } catch (IOException e9) {
            this.f21916c = e9;
            return -1;
        }
    }
}
