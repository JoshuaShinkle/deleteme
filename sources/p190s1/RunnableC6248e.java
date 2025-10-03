package p190s1;

import android.graphics.drawable.Drawable;
import android.os.Handler;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import p199t1.InterfaceC6282g;
import p199t1.InterfaceC6283h;
import p208u1.InterfaceC6362b;
import p226w1.C6517j;

/* renamed from: s1.e */
/* loaded from: classes.dex */
public class RunnableC6248e<R> implements InterfaceFutureC6245b<R>, InterfaceC6249f<R>, Runnable {

    /* renamed from: m */
    public static final a f21053m = new a();

    /* renamed from: b */
    public final Handler f21054b;

    /* renamed from: c */
    public final int f21055c;

    /* renamed from: d */
    public final int f21056d;

    /* renamed from: e */
    public final boolean f21057e;

    /* renamed from: f */
    public final a f21058f;

    /* renamed from: g */
    public R f21059g;

    /* renamed from: h */
    public InterfaceC6246c f21060h;

    /* renamed from: i */
    public boolean f21061i;

    /* renamed from: j */
    public boolean f21062j;

    /* renamed from: k */
    public boolean f21063k;

    /* renamed from: l */
    public GlideException f21064l;

    /* renamed from: s1.e$a */
    public static class a {
        /* renamed from: a */
        public void m23906a(Object obj) {
            obj.notifyAll();
        }

        /* renamed from: b */
        public void m23907b(Object obj, long j9) throws InterruptedException {
            obj.wait(j9);
        }
    }

    public RunnableC6248e(Handler handler, int i9, int i10) {
        this(handler, i9, i10, true, f21053m);
    }

    @Override // p199t1.InterfaceC6283h
    /* renamed from: a */
    public void mo23898a(InterfaceC6246c interfaceC6246c) {
        this.f21060h = interfaceC6246c;
    }

    @Override // p199t1.InterfaceC6283h
    /* renamed from: b */
    public void mo23899b(InterfaceC6282g interfaceC6282g) {
    }

    @Override // p190s1.InterfaceC6249f
    /* renamed from: c */
    public synchronized boolean mo7170c(GlideException glideException, Object obj, InterfaceC6283h<R> interfaceC6283h, boolean z8) {
        this.f21063k = true;
        this.f21064l = glideException;
        this.f21058f.m23906a(this);
        return false;
    }

    @Override // java.util.concurrent.Future
    public synchronized boolean cancel(boolean z8) {
        if (isDone()) {
            return false;
        }
        this.f21061i = true;
        this.f21058f.m23906a(this);
        if (z8) {
            m23904k();
        }
        return true;
    }

    @Override // p190s1.InterfaceC6249f
    /* renamed from: d */
    public synchronized boolean mo7171d(R r8, Object obj, InterfaceC6283h<R> interfaceC6283h, DataSource dataSource, boolean z8) {
        this.f21062j = true;
        this.f21059g = r8;
        this.f21058f.m23906a(this);
        return false;
    }

    @Override // p199t1.InterfaceC6283h
    /* renamed from: e */
    public synchronized void mo7182e(Drawable drawable) {
    }

    @Override // p199t1.InterfaceC6283h
    /* renamed from: f */
    public synchronized void mo7183f(R r8, InterfaceC6362b<? super R> interfaceC6362b) {
    }

    @Override // p199t1.InterfaceC6283h
    /* renamed from: g */
    public void mo23900g(Drawable drawable) {
    }

    @Override // java.util.concurrent.Future
    public R get() {
        try {
            return m23905l(null);
        } catch (TimeoutException e9) {
            throw new AssertionError(e9);
        }
    }

    @Override // p199t1.InterfaceC6283h
    /* renamed from: h */
    public InterfaceC6246c mo23901h() {
        return this.f21060h;
    }

    @Override // p199t1.InterfaceC6283h
    /* renamed from: i */
    public void mo23902i(Drawable drawable) {
    }

    @Override // java.util.concurrent.Future
    public synchronized boolean isCancelled() {
        return this.f21061i;
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0010  */
    @Override // java.util.concurrent.Future
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public synchronized boolean isDone() {
        boolean z8;
        if (this.f21061i || this.f21062j) {
            z8 = true;
        } else if (!this.f21063k) {
            z8 = false;
        }
        return z8;
    }

    @Override // p199t1.InterfaceC6283h
    /* renamed from: j */
    public void mo23903j(InterfaceC6282g interfaceC6282g) {
        interfaceC6282g.mo4012e(this.f21055c, this.f21056d);
    }

    /* renamed from: k */
    public final void m23904k() {
        this.f21054b.post(this);
    }

    /* renamed from: l */
    public final synchronized R m23905l(Long l9) {
        if (this.f21057e && !isDone()) {
            C6517j.m24940a();
        }
        if (this.f21061i) {
            throw new CancellationException();
        }
        if (this.f21063k) {
            throw new ExecutionException(this.f21064l);
        }
        if (this.f21062j) {
            return this.f21059g;
        }
        if (l9 == null) {
            this.f21058f.m23907b(this, 0L);
        } else if (l9.longValue() > 0) {
            long jCurrentTimeMillis = System.currentTimeMillis();
            long jLongValue = l9.longValue() + jCurrentTimeMillis;
            while (!isDone() && jCurrentTimeMillis < jLongValue) {
                this.f21058f.m23907b(this, jLongValue - jCurrentTimeMillis);
                jCurrentTimeMillis = System.currentTimeMillis();
            }
        }
        if (Thread.interrupted()) {
            throw new InterruptedException();
        }
        if (this.f21063k) {
            throw new ExecutionException(this.f21064l);
        }
        if (this.f21061i) {
            throw new CancellationException();
        }
        if (!this.f21062j) {
            throw new TimeoutException();
        }
        return this.f21059g;
    }

    @Override // p163p1.InterfaceC5878i
    public void onDestroy() {
    }

    @Override // p163p1.InterfaceC5878i
    public void onStart() {
    }

    @Override // p163p1.InterfaceC5878i
    public void onStop() {
    }

    @Override // java.lang.Runnable
    public void run() {
        InterfaceC6246c interfaceC6246c = this.f21060h;
        if (interfaceC6246c != null) {
            interfaceC6246c.clear();
            this.f21060h = null;
        }
    }

    public RunnableC6248e(Handler handler, int i9, int i10, boolean z8, a aVar) {
        this.f21054b = handler;
        this.f21055c = i9;
        this.f21056d = i10;
        this.f21057e = z8;
        this.f21058f = aVar;
    }

    @Override // java.util.concurrent.Future
    public R get(long j9, TimeUnit timeUnit) {
        return m23905l(Long.valueOf(timeUnit.toMillis(j9)));
    }
}
