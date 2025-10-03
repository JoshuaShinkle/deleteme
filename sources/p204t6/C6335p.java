package p204t6;

import java.io.IOException;
import java.io.OutputStream;
import p007a6.C0042f;

/* renamed from: t6.p */
/* loaded from: classes.dex */
public final class C6335p implements InterfaceC6340u {

    /* renamed from: b */
    public final OutputStream f21357b;

    /* renamed from: c */
    public final C6343x f21358c;

    public C6335p(OutputStream outputStream, C6343x c6343x) {
        C0042f.m158e(outputStream, "out");
        C0042f.m158e(c6343x, "timeout");
        this.f21357b = outputStream;
        this.f21358c = c6343x;
    }

    @Override // p204t6.InterfaceC6340u
    /* renamed from: a */
    public C6343x mo21081a() {
        return this.f21358c;
    }

    @Override // p204t6.InterfaceC6340u, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.f21357b.close();
    }

    @Override // p204t6.InterfaceC6340u, java.io.Flushable
    public void flush() throws IOException {
        this.f21357b.flush();
    }

    @Override // p204t6.InterfaceC6340u
    /* renamed from: q */
    public void mo21082q(C6322c c6322c, long j9) throws IOException {
        C0042f.m158e(c6322c, "source");
        C6320a.m24153b(c6322c.size(), 0L, j9);
        while (j9 > 0) {
            this.f21358c.mo24244f();
            C6338s c6338s = c6322c.f21330b;
            C0042f.m155b(c6338s);
            int iMin = (int) Math.min(j9, c6338s.f21369c - c6338s.f21368b);
            this.f21357b.write(c6338s.f21367a, c6338s.f21368b, iMin);
            c6338s.f21368b += iMin;
            long j10 = iMin;
            j9 -= j10;
            c6322c.m24199L(c6322c.size() - j10);
            if (c6338s.f21368b == c6338s.f21369c) {
                c6322c.f21330b = c6338s.m24287b();
                C6339t.m24292b(c6338s);
            }
        }
    }

    public String toString() {
        return "sink(" + this.f21357b + ')';
    }
}
