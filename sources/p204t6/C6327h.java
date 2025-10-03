package p204t6;

import java.io.InterruptedIOException;
import java.util.concurrent.TimeUnit;
import p007a6.C0042f;

/* renamed from: t6.h */
/* loaded from: classes.dex */
public class C6327h extends C6343x {

    /* renamed from: f */
    public C6343x f21341f;

    public C6327h(C6343x c6343x) {
        C0042f.m158e(c6343x, "delegate");
        this.f21341f = c6343x;
    }

    @Override // p204t6.C6343x
    /* renamed from: a */
    public C6343x mo24239a() {
        return this.f21341f.mo24239a();
    }

    @Override // p204t6.C6343x
    /* renamed from: b */
    public C6343x mo24240b() {
        return this.f21341f.mo24240b();
    }

    @Override // p204t6.C6343x
    /* renamed from: c */
    public long mo24241c() {
        return this.f21341f.mo24241c();
    }

    @Override // p204t6.C6343x
    /* renamed from: d */
    public C6343x mo24242d(long j9) {
        return this.f21341f.mo24242d(j9);
    }

    @Override // p204t6.C6343x
    /* renamed from: e */
    public boolean mo24243e() {
        return this.f21341f.mo24243e();
    }

    @Override // p204t6.C6343x
    /* renamed from: f */
    public void mo24244f() throws InterruptedIOException {
        this.f21341f.mo24244f();
    }

    @Override // p204t6.C6343x
    /* renamed from: g */
    public C6343x mo24245g(long j9, TimeUnit timeUnit) {
        C0042f.m158e(timeUnit, "unit");
        return this.f21341f.mo24245g(j9, timeUnit);
    }

    /* renamed from: i */
    public final C6343x m24246i() {
        return this.f21341f;
    }

    /* renamed from: j */
    public final C6327h m24247j(C6343x c6343x) {
        C0042f.m158e(c6343x, "delegate");
        this.f21341f = c6343x;
        return this;
    }
}
