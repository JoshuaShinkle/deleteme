package p204t6;

import java.io.IOException;
import java.io.InputStream;
import p007a6.C0042f;

/* renamed from: t6.k */
/* loaded from: classes.dex */
public class C6330k implements InterfaceC6342w {

    /* renamed from: b */
    public final InputStream f21351b;

    /* renamed from: c */
    public final C6343x f21352c;

    public C6330k(InputStream inputStream, C6343x c6343x) {
        C0042f.m158e(inputStream, "input");
        C0042f.m158e(c6343x, "timeout");
        this.f21351b = inputStream;
        this.f21352c = c6343x;
    }

    @Override // p204t6.InterfaceC6342w
    /* renamed from: a */
    public C6343x mo21076a() {
        return this.f21352c;
    }

    @Override // p204t6.InterfaceC6342w, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.f21351b.close();
    }

    @Override // p204t6.InterfaceC6342w
    /* renamed from: d */
    public long mo21077d(C6322c c6322c, long j9) throws IOException {
        C0042f.m158e(c6322c, "sink");
        if (j9 == 0) {
            return 0L;
        }
        if (!(j9 >= 0)) {
            throw new IllegalArgumentException(("byteCount < 0: " + j9).toString());
        }
        try {
            this.f21352c.mo24244f();
            C6338s c6338sM24202O = c6322c.m24202O(1);
            int i9 = this.f21351b.read(c6338sM24202O.f21367a, c6338sM24202O.f21369c, (int) Math.min(j9, 8192 - c6338sM24202O.f21369c));
            if (i9 != -1) {
                c6338sM24202O.f21369c += i9;
                long j10 = i9;
                c6322c.m24199L(c6322c.size() + j10);
                return j10;
            }
            if (c6338sM24202O.f21368b != c6338sM24202O.f21369c) {
                return -1L;
            }
            c6322c.f21330b = c6338sM24202O.m24287b();
            C6339t.m24292b(c6338sM24202O);
            return -1L;
        } catch (AssertionError e9) {
            if (C6331l.m24257c(e9)) {
                throw new IOException(e9);
            }
            throw e9;
        }
    }

    public String toString() {
        return "source(" + this.f21351b + ')';
    }
}
