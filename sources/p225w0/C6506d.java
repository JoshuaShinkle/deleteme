package p225w0;

import android.util.Log;
import com.google.common.primitives.UnsignedBytes;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

/* renamed from: w0.d */
/* loaded from: classes.dex */
public class C6506d {

    /* renamed from: b */
    public ByteBuffer f21881b;

    /* renamed from: c */
    public C6505c f21882c;

    /* renamed from: a */
    public final byte[] f21880a = new byte[256];

    /* renamed from: d */
    public int f21883d = 0;

    /* renamed from: a */
    public void m24884a() {
        this.f21881b = null;
        this.f21882c = null;
    }

    /* renamed from: b */
    public final boolean m24885b() {
        return this.f21882c.f21868b != 0;
    }

    /* renamed from: c */
    public C6505c m24886c() {
        if (this.f21881b == null) {
            throw new IllegalStateException("You must call setData() before parseHeader()");
        }
        if (m24885b()) {
            return this.f21882c;
        }
        m24894k();
        if (!m24885b()) {
            m24891h();
            C6505c c6505c = this.f21882c;
            if (c6505c.f21869c < 0) {
                c6505c.f21868b = 1;
            }
        }
        return this.f21882c;
    }

    /* renamed from: d */
    public final int m24887d() {
        try {
            return this.f21881b.get() & UnsignedBytes.MAX_VALUE;
        } catch (Exception unused) {
            this.f21882c.f21868b = 1;
            return 0;
        }
    }

    /* renamed from: e */
    public final void m24888e() {
        this.f21882c.f21870d.f21856a = m24897n();
        this.f21882c.f21870d.f21857b = m24897n();
        this.f21882c.f21870d.f21858c = m24897n();
        this.f21882c.f21870d.f21859d = m24897n();
        int iM24887d = m24887d();
        boolean z8 = (iM24887d & 128) != 0;
        int iPow = (int) Math.pow(2.0d, (iM24887d & 7) + 1);
        C6504b c6504b = this.f21882c.f21870d;
        c6504b.f21860e = (iM24887d & 64) != 0;
        if (z8) {
            c6504b.f21866k = m24890g(iPow);
        } else {
            c6504b.f21866k = null;
        }
        this.f21882c.f21870d.f21865j = this.f21881b.position();
        m24901r();
        if (m24885b()) {
            return;
        }
        C6505c c6505c = this.f21882c;
        c6505c.f21869c++;
        c6505c.f21871e.add(c6505c.f21870d);
    }

    /* renamed from: f */
    public final void m24889f() {
        int iM24887d = m24887d();
        this.f21883d = iM24887d;
        if (iM24887d <= 0) {
            return;
        }
        int i9 = 0;
        int i10 = 0;
        while (true) {
            try {
                i10 = this.f21883d;
                if (i9 >= i10) {
                    return;
                }
                i10 -= i9;
                this.f21881b.get(this.f21880a, i9, i10);
                i9 += i10;
            } catch (Exception e9) {
                if (Log.isLoggable("GifHeaderParser", 3)) {
                    Log.d("GifHeaderParser", "Error Reading Block n: " + i9 + " count: " + i10 + " blockSize: " + this.f21883d, e9);
                }
                this.f21882c.f21868b = 1;
                return;
            }
        }
    }

    /* renamed from: g */
    public final int[] m24890g(int i9) {
        byte[] bArr = new byte[i9 * 3];
        int[] iArr = null;
        try {
            this.f21881b.get(bArr);
            iArr = new int[256];
            int i10 = 0;
            int i11 = 0;
            while (i10 < i9) {
                int i12 = i11 + 1;
                int i13 = i12 + 1;
                int i14 = i13 + 1;
                int i15 = i10 + 1;
                iArr[i10] = ((bArr[i11] & UnsignedBytes.MAX_VALUE) << 16) | (-16777216) | ((bArr[i12] & UnsignedBytes.MAX_VALUE) << 8) | (bArr[i13] & UnsignedBytes.MAX_VALUE);
                i11 = i14;
                i10 = i15;
            }
        } catch (BufferUnderflowException e9) {
            if (Log.isLoggable("GifHeaderParser", 3)) {
                Log.d("GifHeaderParser", "Format Error Reading Color Table", e9);
            }
            this.f21882c.f21868b = 1;
        }
        return iArr;
    }

    /* renamed from: h */
    public final void m24891h() {
        m24892i(Integer.MAX_VALUE);
    }

    /* renamed from: i */
    public final void m24892i(int i9) {
        boolean z8 = false;
        while (!z8 && !m24885b() && this.f21882c.f21869c <= i9) {
            int iM24887d = m24887d();
            if (iM24887d == 33) {
                int iM24887d2 = m24887d();
                if (iM24887d2 == 1) {
                    m24900q();
                } else if (iM24887d2 == 249) {
                    this.f21882c.f21870d = new C6504b();
                    m24893j();
                } else if (iM24887d2 == 254) {
                    m24900q();
                } else if (iM24887d2 != 255) {
                    m24900q();
                } else {
                    m24889f();
                    StringBuilder sb = new StringBuilder();
                    for (int i10 = 0; i10 < 11; i10++) {
                        sb.append((char) this.f21880a[i10]);
                    }
                    if (sb.toString().equals("NETSCAPE2.0")) {
                        m24896m();
                    } else {
                        m24900q();
                    }
                }
            } else if (iM24887d == 44) {
                C6505c c6505c = this.f21882c;
                if (c6505c.f21870d == null) {
                    c6505c.f21870d = new C6504b();
                }
                m24888e();
            } else if (iM24887d != 59) {
                this.f21882c.f21868b = 1;
            } else {
                z8 = true;
            }
        }
    }

    /* renamed from: j */
    public final void m24893j() {
        m24887d();
        int iM24887d = m24887d();
        C6504b c6504b = this.f21882c.f21870d;
        int i9 = (iM24887d & 28) >> 2;
        c6504b.f21862g = i9;
        if (i9 == 0) {
            c6504b.f21862g = 1;
        }
        c6504b.f21861f = (iM24887d & 1) != 0;
        int iM24897n = m24897n();
        if (iM24897n < 2) {
            iM24897n = 10;
        }
        C6504b c6504b2 = this.f21882c.f21870d;
        c6504b2.f21864i = iM24897n * 10;
        c6504b2.f21863h = m24887d();
        m24887d();
    }

    /* renamed from: k */
    public final void m24894k() {
        StringBuilder sb = new StringBuilder();
        for (int i9 = 0; i9 < 6; i9++) {
            sb.append((char) m24887d());
        }
        if (!sb.toString().startsWith("GIF")) {
            this.f21882c.f21868b = 1;
            return;
        }
        m24895l();
        if (!this.f21882c.f21874h || m24885b()) {
            return;
        }
        C6505c c6505c = this.f21882c;
        c6505c.f21867a = m24890g(c6505c.f21875i);
        C6505c c6505c2 = this.f21882c;
        c6505c2.f21878l = c6505c2.f21867a[c6505c2.f21876j];
    }

    /* renamed from: l */
    public final void m24895l() {
        this.f21882c.f21872f = m24897n();
        this.f21882c.f21873g = m24897n();
        int iM24887d = m24887d();
        C6505c c6505c = this.f21882c;
        c6505c.f21874h = (iM24887d & 128) != 0;
        c6505c.f21875i = (int) Math.pow(2.0d, (iM24887d & 7) + 1);
        this.f21882c.f21876j = m24887d();
        this.f21882c.f21877k = m24887d();
    }

    /* renamed from: m */
    public final void m24896m() {
        do {
            m24889f();
            byte[] bArr = this.f21880a;
            if (bArr[0] == 1) {
                this.f21882c.f21879m = ((bArr[2] & UnsignedBytes.MAX_VALUE) << 8) | (bArr[1] & UnsignedBytes.MAX_VALUE);
            }
            if (this.f21883d <= 0) {
                return;
            }
        } while (!m24885b());
    }

    /* renamed from: n */
    public final int m24897n() {
        return this.f21881b.getShort();
    }

    /* renamed from: o */
    public final void m24898o() {
        this.f21881b = null;
        Arrays.fill(this.f21880a, (byte) 0);
        this.f21882c = new C6505c();
        this.f21883d = 0;
    }

    /* renamed from: p */
    public C6506d m24899p(ByteBuffer byteBuffer) {
        m24898o();
        ByteBuffer byteBufferAsReadOnlyBuffer = byteBuffer.asReadOnlyBuffer();
        this.f21881b = byteBufferAsReadOnlyBuffer;
        byteBufferAsReadOnlyBuffer.position(0);
        this.f21881b.order(ByteOrder.LITTLE_ENDIAN);
        return this;
    }

    /* renamed from: q */
    public final void m24900q() {
        int iM24887d;
        do {
            iM24887d = m24887d();
            this.f21881b.position(Math.min(this.f21881b.position() + iM24887d, this.f21881b.limit()));
        } while (iM24887d > 0);
    }

    /* renamed from: r */
    public final void m24901r() {
        m24887d();
        m24900q();
    }
}
