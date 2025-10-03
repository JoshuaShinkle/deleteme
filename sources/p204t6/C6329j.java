package p204t6;

import java.io.EOFException;
import java.io.IOException;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;
import p007a6.C0042f;

/* renamed from: t6.j */
/* loaded from: classes.dex */
public final class C6329j implements InterfaceC6342w {

    /* renamed from: b */
    public final InterfaceC6324e f21347b;

    /* renamed from: c */
    public final Inflater f21348c;

    /* renamed from: d */
    public int f21349d;

    /* renamed from: e */
    public boolean f21350e;

    public C6329j(InterfaceC6324e interfaceC6324e, Inflater inflater) {
        C0042f.m158e(interfaceC6324e, "source");
        C0042f.m158e(inflater, "inflater");
        this.f21347b = interfaceC6324e;
        this.f21348c = inflater;
    }

    @Override // p204t6.InterfaceC6342w
    /* renamed from: a */
    public C6343x mo21076a() {
        return this.f21347b.mo21076a();
    }

    @Override // p204t6.InterfaceC6342w, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        if (this.f21350e) {
            return;
        }
        this.f21348c.end();
        this.f21350e = true;
        this.f21347b.close();
    }

    @Override // p204t6.InterfaceC6342w
    /* renamed from: d */
    public long mo21077d(C6322c c6322c, long j9) throws DataFormatException, IOException {
        C0042f.m158e(c6322c, "sink");
        do {
            long jM24252f = m24252f(c6322c, j9);
            if (jM24252f > 0) {
                return jM24252f;
            }
            if (this.f21348c.finished() || this.f21348c.needsDictionary()) {
                return -1L;
            }
        } while (!this.f21347b.mo24218g());
        throw new EOFException("source exhausted prematurely");
    }

    /* renamed from: f */
    public final long m24252f(C6322c c6322c, long j9) throws DataFormatException, IOException {
        C0042f.m158e(c6322c, "sink");
        if (!(j9 >= 0)) {
            throw new IllegalArgumentException(("byteCount < 0: " + j9).toString());
        }
        if (!(!this.f21350e)) {
            throw new IllegalStateException("closed".toString());
        }
        if (j9 == 0) {
            return 0L;
        }
        try {
            C6338s c6338sM24202O = c6322c.m24202O(1);
            int iMin = (int) Math.min(j9, 8192 - c6338sM24202O.f21369c);
            m24253u();
            int iInflate = this.f21348c.inflate(c6338sM24202O.f21367a, c6338sM24202O.f21369c, iMin);
            m24254v();
            if (iInflate > 0) {
                c6338sM24202O.f21369c += iInflate;
                long j10 = iInflate;
                c6322c.m24199L(c6322c.size() + j10);
                return j10;
            }
            if (c6338sM24202O.f21368b == c6338sM24202O.f21369c) {
                c6322c.f21330b = c6338sM24202O.m24287b();
                C6339t.m24292b(c6338sM24202O);
            }
            return 0L;
        } catch (DataFormatException e9) {
            throw new IOException(e9);
        }
    }

    /* renamed from: u */
    public final boolean m24253u() {
        if (!this.f21348c.needsInput()) {
            return false;
        }
        if (this.f21347b.mo24218g()) {
            return true;
        }
        C6338s c6338s = this.f21347b.mo24215b().f21330b;
        C0042f.m155b(c6338s);
        int i9 = c6338s.f21369c;
        int i10 = c6338s.f21368b;
        int i11 = i9 - i10;
        this.f21349d = i11;
        this.f21348c.setInput(c6338s.f21367a, i10, i11);
        return false;
    }

    /* renamed from: v */
    public final void m24254v() {
        int i9 = this.f21349d;
        if (i9 == 0) {
            return;
        }
        int remaining = i9 - this.f21348c.getRemaining();
        this.f21349d -= remaining;
        this.f21347b.skip(remaining);
    }
}
