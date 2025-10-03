package p204t6;

import java.io.EOFException;
import java.io.IOException;
import java.util.Arrays;
import java.util.zip.CRC32;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;
import p007a6.C0042f;

/* renamed from: t6.i */
/* loaded from: classes.dex */
public final class C6328i implements InterfaceC6342w {

    /* renamed from: b */
    public byte f21342b;

    /* renamed from: c */
    public final C6337r f21343c;

    /* renamed from: d */
    public final Inflater f21344d;

    /* renamed from: e */
    public final C6329j f21345e;

    /* renamed from: f */
    public final CRC32 f21346f;

    public C6328i(InterfaceC6342w interfaceC6342w) {
        C0042f.m158e(interfaceC6342w, "source");
        C6337r c6337r = new C6337r(interfaceC6342w);
        this.f21343c = c6337r;
        Inflater inflater = new Inflater(true);
        this.f21344d = inflater;
        this.f21345e = new C6329j(c6337r, inflater);
        this.f21346f = new CRC32();
    }

    @Override // p204t6.InterfaceC6342w
    /* renamed from: a */
    public C6343x mo21076a() {
        return this.f21343c.mo21076a();
    }

    @Override // p204t6.InterfaceC6342w, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.f21345e.close();
    }

    @Override // p204t6.InterfaceC6342w
    /* renamed from: d */
    public long mo21077d(C6322c c6322c, long j9) throws DataFormatException, IOException {
        C0042f.m158e(c6322c, "sink");
        if (!(j9 >= 0)) {
            throw new IllegalArgumentException(("byteCount < 0: " + j9).toString());
        }
        if (j9 == 0) {
            return 0L;
        }
        if (this.f21342b == 0) {
            m24249u();
            this.f21342b = (byte) 1;
        }
        if (this.f21342b == 1) {
            long size = c6322c.size();
            long jMo21077d = this.f21345e.mo21077d(c6322c, j9);
            if (jMo21077d != -1) {
                m24251w(c6322c, size, jMo21077d);
                return jMo21077d;
            }
            this.f21342b = (byte) 2;
        }
        if (this.f21342b == 2) {
            m24250v();
            this.f21342b = (byte) 3;
            if (!this.f21343c.mo24218g()) {
                throw new IOException("gzip finished without exhausting source");
            }
        }
        return -1L;
    }

    /* renamed from: f */
    public final void m24248f(String str, int i9, int i10) throws IOException {
        if (i10 == i9) {
            return;
        }
        String str2 = String.format("%s: actual 0x%08x != expected 0x%08x", Arrays.copyOf(new Object[]{str, Integer.valueOf(i10), Integer.valueOf(i9)}, 3));
        C0042f.m157d(str2, "format(this, *args)");
        throw new IOException(str2);
    }

    /* renamed from: u */
    public final void m24249u() throws IOException {
        this.f21343c.mo24228r(10L);
        byte bM24236z = this.f21343c.f21363c.m24236z(3L);
        boolean z8 = ((bM24236z >> 1) & 1) == 1;
        if (z8) {
            m24251w(this.f21343c.f21363c, 0L, 10L);
        }
        m24248f("ID1ID2", 8075, this.f21343c.readShort());
        this.f21343c.skip(8L);
        if (((bM24236z >> 2) & 1) == 1) {
            this.f21343c.mo24228r(2L);
            if (z8) {
                m24251w(this.f21343c.f21363c, 0L, 2L);
            }
            long jM24195H = this.f21343c.f21363c.m24195H() & 65535;
            this.f21343c.mo24228r(jM24195H);
            if (z8) {
                m24251w(this.f21343c.f21363c, 0L, jM24195H);
            }
            this.f21343c.skip(jM24195H);
        }
        if (((bM24236z >> 3) & 1) == 1) {
            long jM24281f = this.f21343c.m24281f((byte) 0);
            if (jM24281f == -1) {
                throw new EOFException();
            }
            if (z8) {
                m24251w(this.f21343c.f21363c, 0L, jM24281f + 1);
            }
            this.f21343c.skip(jM24281f + 1);
        }
        if (((bM24236z >> 4) & 1) == 1) {
            long jM24281f2 = this.f21343c.m24281f((byte) 0);
            if (jM24281f2 == -1) {
                throw new EOFException();
            }
            if (z8) {
                m24251w(this.f21343c.f21363c, 0L, jM24281f2 + 1);
            }
            this.f21343c.skip(jM24281f2 + 1);
        }
        if (z8) {
            m24248f("FHCRC", this.f21343c.m24284w(), (short) this.f21346f.getValue());
            this.f21346f.reset();
        }
    }

    /* renamed from: v */
    public final void m24250v() throws IOException {
        m24248f("CRC", this.f21343c.m24283v(), (int) this.f21346f.getValue());
        m24248f("ISIZE", this.f21343c.m24283v(), (int) this.f21344d.getBytesWritten());
    }

    /* renamed from: w */
    public final void m24251w(C6322c c6322c, long j9, long j10) {
        C6338s c6338s = c6322c.f21330b;
        C0042f.m155b(c6338s);
        while (true) {
            int i9 = c6338s.f21369c;
            int i10 = c6338s.f21368b;
            if (j9 < i9 - i10) {
                break;
            }
            j9 -= i9 - i10;
            c6338s = c6338s.f21372f;
            C0042f.m155b(c6338s);
        }
        while (j10 > 0) {
            int iMin = (int) Math.min(c6338s.f21369c - r6, j10);
            this.f21346f.update(c6338s.f21367a, (int) (c6338s.f21368b + j9), iMin);
            j10 -= iMin;
            c6338s = c6338s.f21372f;
            C0042f.m155b(c6338s);
            j9 = 0;
        }
    }
}
