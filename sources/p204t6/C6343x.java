package p204t6;

import java.io.InterruptedIOException;
import java.util.concurrent.TimeUnit;
import p007a6.C0040d;
import p007a6.C0042f;

/* renamed from: t6.x */
/* loaded from: classes.dex */
public class C6343x {

    /* renamed from: d */
    public static final b f21380d = new b(null);

    /* renamed from: e */
    public static final C6343x f21381e = new a();

    /* renamed from: a */
    public boolean f21382a;

    /* renamed from: b */
    public long f21383b;

    /* renamed from: c */
    public long f21384c;

    /* renamed from: t6.x$a */
    public static final class a extends C6343x {
        @Override // p204t6.C6343x
        /* renamed from: d */
        public C6343x mo24242d(long j9) {
            return this;
        }

        @Override // p204t6.C6343x
        /* renamed from: f */
        public void mo24244f() {
        }

        @Override // p204t6.C6343x
        /* renamed from: g */
        public C6343x mo24245g(long j9, TimeUnit timeUnit) {
            C0042f.m158e(timeUnit, "unit");
            return this;
        }
    }

    /* renamed from: t6.x$b */
    public static final class b {
        public b() {
        }

        public /* synthetic */ b(C0040d c0040d) {
            this();
        }
    }

    /* renamed from: a */
    public C6343x mo24239a() {
        this.f21382a = false;
        return this;
    }

    /* renamed from: b */
    public C6343x mo24240b() {
        this.f21384c = 0L;
        return this;
    }

    /* renamed from: c */
    public long mo24241c() {
        if (this.f21382a) {
            return this.f21383b;
        }
        throw new IllegalStateException("No deadline".toString());
    }

    /* renamed from: d */
    public C6343x mo24242d(long j9) {
        this.f21382a = true;
        this.f21383b = j9;
        return this;
    }

    /* renamed from: e */
    public boolean mo24243e() {
        return this.f21382a;
    }

    /* renamed from: f */
    public void mo24244f() throws InterruptedIOException {
        if (Thread.currentThread().isInterrupted()) {
            throw new InterruptedIOException("interrupted");
        }
        if (this.f21382a && this.f21383b - System.nanoTime() <= 0) {
            throw new InterruptedIOException("deadline reached");
        }
    }

    /* renamed from: g */
    public C6343x mo24245g(long j9, TimeUnit timeUnit) {
        C0042f.m158e(timeUnit, "unit");
        if (j9 >= 0) {
            this.f21384c = timeUnit.toNanos(j9);
            return this;
        }
        throw new IllegalArgumentException(("timeout < 0: " + j9).toString());
    }

    /* renamed from: h */
    public long m24295h() {
        return this.f21384c;
    }
}
