package p204t6;

import android.support.v4.media.session.PlaybackStateCompat;
import com.google.android.exoplayer2.C3322C;
import com.google.android.exoplayer2.extractor.p037ts.PsExtractor;
import com.google.common.primitives.UnsignedBytes;
import java.io.Closeable;
import java.io.EOFException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.ByteChannel;
import java.nio.charset.Charset;
import kotlin.collections.C5222e;
import kotlin.text.C5246c;
import okio.ByteString;
import okio.SegmentedByteString;
import p007a6.C0042f;
import p213u6.C6423a;

/* renamed from: t6.c */
/* loaded from: classes.dex */
public final class C6322c implements InterfaceC6324e, InterfaceC6323d, Cloneable, ByteChannel {

    /* renamed from: b */
    public C6338s f21330b;

    /* renamed from: c */
    public long f21331c;

    /* renamed from: t6.c$a */
    public static final class a implements Closeable {

        /* renamed from: b */
        public C6322c f21332b;

        /* renamed from: c */
        public C6338s f21333c;

        /* renamed from: e */
        public byte[] f21335e;

        /* renamed from: d */
        public long f21334d = -1;

        /* renamed from: f */
        public int f21336f = -1;

        /* renamed from: g */
        public int f21337g = -1;

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            if (!(this.f21332b != null)) {
                throw new IllegalStateException("not attached to a buffer".toString());
            }
            this.f21332b = null;
            m24237f(null);
            this.f21334d = -1L;
            this.f21335e = null;
            this.f21336f = -1;
            this.f21337g = -1;
        }

        /* renamed from: f */
        public final void m24237f(C6338s c6338s) {
            this.f21333c = c6338s;
        }
    }

    /* renamed from: A */
    public long m24188A(byte b9, long j9, long j10) {
        C6338s c6338s;
        int i9;
        long size = 0;
        boolean z8 = false;
        if (0 <= j9 && j9 <= j10) {
            z8 = true;
        }
        if (!z8) {
            throw new IllegalArgumentException(("size=" + size() + " fromIndex=" + j9 + " toIndex=" + j10).toString());
        }
        if (j10 > size()) {
            j10 = size();
        }
        if (j9 == j10 || (c6338s = this.f21330b) == null) {
            return -1L;
        }
        if (size() - j9 < j9) {
            size = size();
            while (size > j9) {
                c6338s = c6338s.f21373g;
                C0042f.m155b(c6338s);
                size -= c6338s.f21369c - c6338s.f21368b;
            }
            while (size < j10) {
                byte[] bArr = c6338s.f21367a;
                int iMin = (int) Math.min(c6338s.f21369c, (c6338s.f21368b + j10) - size);
                i9 = (int) ((c6338s.f21368b + j9) - size);
                while (i9 < iMin) {
                    if (bArr[i9] != b9) {
                        i9++;
                    }
                }
                size += c6338s.f21369c - c6338s.f21368b;
                c6338s = c6338s.f21372f;
                C0042f.m155b(c6338s);
                j9 = size;
            }
            return -1L;
        }
        while (true) {
            long j11 = (c6338s.f21369c - c6338s.f21368b) + size;
            if (j11 > j9) {
                break;
            }
            c6338s = c6338s.f21372f;
            C0042f.m155b(c6338s);
            size = j11;
        }
        while (size < j10) {
            byte[] bArr2 = c6338s.f21367a;
            int iMin2 = (int) Math.min(c6338s.f21369c, (c6338s.f21368b + j10) - size);
            i9 = (int) ((c6338s.f21368b + j9) - size);
            while (i9 < iMin2) {
                if (bArr2[i9] != b9) {
                    i9++;
                }
            }
            size += c6338s.f21369c - c6338s.f21368b;
            c6338s = c6338s.f21372f;
            C0042f.m155b(c6338s);
            j9 = size;
        }
        return -1L;
        return (i9 - c6338s.f21368b) + size;
    }

    /* renamed from: B */
    public long m24189B(ByteString byteString) {
        C0042f.m158e(byteString, "targetBytes");
        return m24190C(byteString, 0L);
    }

    /* renamed from: C */
    public long m24190C(ByteString byteString, long j9) {
        int i9;
        int i10;
        C0042f.m158e(byteString, "targetBytes");
        long size = 0;
        if (!(j9 >= 0)) {
            throw new IllegalArgumentException(("fromIndex < 0: " + j9).toString());
        }
        C6338s c6338s = this.f21330b;
        if (c6338s == null) {
            return -1L;
        }
        if (size() - j9 < j9) {
            size = size();
            while (size > j9) {
                c6338s = c6338s.f21373g;
                C0042f.m155b(c6338s);
                size -= c6338s.f21369c - c6338s.f21368b;
            }
            if (byteString.m21892r() == 2) {
                byte bM21878d = byteString.m21878d(0);
                byte bM21878d2 = byteString.m21878d(1);
                while (size < size()) {
                    byte[] bArr = c6338s.f21367a;
                    i9 = (int) ((c6338s.f21368b + j9) - size);
                    int i11 = c6338s.f21369c;
                    while (i9 < i11) {
                        byte b9 = bArr[i9];
                        if (b9 == bM21878d || b9 == bM21878d2) {
                            i10 = c6338s.f21368b;
                        } else {
                            i9++;
                        }
                    }
                    size += c6338s.f21369c - c6338s.f21368b;
                    c6338s = c6338s.f21372f;
                    C0042f.m155b(c6338s);
                    j9 = size;
                }
                return -1L;
            }
            byte[] bArrMo21884j = byteString.mo21884j();
            while (size < size()) {
                byte[] bArr2 = c6338s.f21367a;
                i9 = (int) ((c6338s.f21368b + j9) - size);
                int i12 = c6338s.f21369c;
                while (i9 < i12) {
                    byte b10 = bArr2[i9];
                    for (byte b11 : bArrMo21884j) {
                        if (b10 == b11) {
                            i10 = c6338s.f21368b;
                        }
                    }
                    i9++;
                }
                size += c6338s.f21369c - c6338s.f21368b;
                c6338s = c6338s.f21372f;
                C0042f.m155b(c6338s);
                j9 = size;
            }
            return -1L;
        }
        while (true) {
            long j10 = (c6338s.f21369c - c6338s.f21368b) + size;
            if (j10 > j9) {
                break;
            }
            c6338s = c6338s.f21372f;
            C0042f.m155b(c6338s);
            size = j10;
        }
        if (byteString.m21892r() == 2) {
            byte bM21878d3 = byteString.m21878d(0);
            byte bM21878d4 = byteString.m21878d(1);
            while (size < size()) {
                byte[] bArr3 = c6338s.f21367a;
                i9 = (int) ((c6338s.f21368b + j9) - size);
                int i13 = c6338s.f21369c;
                while (i9 < i13) {
                    byte b12 = bArr3[i9];
                    if (b12 == bM21878d3 || b12 == bM21878d4) {
                        i10 = c6338s.f21368b;
                    } else {
                        i9++;
                    }
                }
                size += c6338s.f21369c - c6338s.f21368b;
                c6338s = c6338s.f21372f;
                C0042f.m155b(c6338s);
                j9 = size;
            }
            return -1L;
        }
        byte[] bArrMo21884j2 = byteString.mo21884j();
        while (size < size()) {
            byte[] bArr4 = c6338s.f21367a;
            i9 = (int) ((c6338s.f21368b + j9) - size);
            int i14 = c6338s.f21369c;
            while (i9 < i14) {
                byte b13 = bArr4[i9];
                for (byte b14 : bArrMo21884j2) {
                    if (b13 == b14) {
                        i10 = c6338s.f21368b;
                    }
                }
                i9++;
            }
            size += c6338s.f21369c - c6338s.f21368b;
            c6338s = c6338s.f21372f;
            C0042f.m155b(c6338s);
            j9 = size;
        }
        return -1L;
        return (i9 - i10) + size;
    }

    /* renamed from: D */
    public byte[] m24191D() {
        return mo24226o(size());
    }

    /* renamed from: E */
    public ByteString m24192E() {
        return mo24217e(size());
    }

    /* renamed from: F */
    public void m24193F(byte[] bArr) throws EOFException {
        C0042f.m158e(bArr, "sink");
        int i9 = 0;
        while (i9 < bArr.length) {
            int i10 = read(bArr, i9, bArr.length - i9);
            if (i10 == -1) {
                throw new EOFException();
            }
            i9 += i10;
        }
    }

    /* renamed from: G */
    public int m24194G() {
        return C6320a.m24157f(readInt());
    }

    /* renamed from: H */
    public short m24195H() {
        return C6320a.m24158g(readShort());
    }

    /* renamed from: I */
    public String m24196I(long j9, Charset charset) throws EOFException {
        C0042f.m158e(charset, "charset");
        if (!(j9 >= 0 && j9 <= 2147483647L)) {
            throw new IllegalArgumentException(("byteCount: " + j9).toString());
        }
        if (this.f21331c < j9) {
            throw new EOFException();
        }
        if (j9 == 0) {
            return "";
        }
        C6338s c6338s = this.f21330b;
        C0042f.m155b(c6338s);
        int i9 = c6338s.f21368b;
        if (i9 + j9 > c6338s.f21369c) {
            return new String(mo24226o(j9), charset);
        }
        int i10 = (int) j9;
        String str = new String(c6338s.f21367a, i9, i10, charset);
        int i11 = c6338s.f21368b + i10;
        c6338s.f21368b = i11;
        this.f21331c -= j9;
        if (i11 == c6338s.f21369c) {
            this.f21330b = c6338s.m24287b();
            C6339t.m24292b(c6338s);
        }
        return str;
    }

    /* renamed from: J */
    public String m24197J() {
        return m24196I(this.f21331c, C5246c.f17846b);
    }

    /* renamed from: K */
    public String m24198K(long j9) throws EOFException {
        return m24196I(j9, C5246c.f17846b);
    }

    /* renamed from: L */
    public final void m24199L(long j9) {
        this.f21331c = j9;
    }

    /* renamed from: M */
    public final ByteString m24200M() {
        if (size() <= 2147483647L) {
            return m24201N((int) size());
        }
        throw new IllegalStateException(("size > Int.MAX_VALUE: " + size()).toString());
    }

    /* renamed from: N */
    public final ByteString m24201N(int i9) {
        if (i9 == 0) {
            return ByteString.f19096e;
        }
        C6320a.m24153b(size(), 0L, i9);
        C6338s c6338s = this.f21330b;
        int i10 = 0;
        int i11 = 0;
        int i12 = 0;
        while (i11 < i9) {
            C0042f.m155b(c6338s);
            int i13 = c6338s.f21369c;
            int i14 = c6338s.f21368b;
            if (i13 == i14) {
                throw new AssertionError("s.limit == s.pos");
            }
            i11 += i13 - i14;
            i12++;
            c6338s = c6338s.f21372f;
        }
        byte[][] bArr = new byte[i12][];
        int[] iArr = new int[i12 * 2];
        C6338s c6338s2 = this.f21330b;
        int i15 = 0;
        while (i10 < i9) {
            C0042f.m155b(c6338s2);
            bArr[i15] = c6338s2.f21367a;
            i10 += c6338s2.f21369c - c6338s2.f21368b;
            iArr[i15] = Math.min(i10, i9);
            iArr[i15 + i12] = c6338s2.f21368b;
            c6338s2.f21370d = true;
            i15++;
            c6338s2 = c6338s2.f21372f;
        }
        return new SegmentedByteString(bArr, iArr);
    }

    /* renamed from: O */
    public final C6338s m24202O(int i9) {
        if (!(i9 >= 1 && i9 <= 8192)) {
            throw new IllegalArgumentException("unexpected capacity".toString());
        }
        C6338s c6338s = this.f21330b;
        if (c6338s != null) {
            C0042f.m155b(c6338s);
            C6338s c6338s2 = c6338s.f21373g;
            C0042f.m155b(c6338s2);
            return (c6338s2.f21369c + i9 > 8192 || !c6338s2.f21371e) ? c6338s2.m24288c(C6339t.m24293c()) : c6338s2;
        }
        C6338s c6338sM24293c = C6339t.m24293c();
        this.f21330b = c6338sM24293c;
        c6338sM24293c.f21373g = c6338sM24293c;
        c6338sM24293c.f21372f = c6338sM24293c;
        return c6338sM24293c;
    }

    @Override // p204t6.InterfaceC6323d
    /* renamed from: P, reason: merged with bridge method [inline-methods] */
    public C6322c mo24227p(ByteString byteString) {
        C0042f.m158e(byteString, "byteString");
        byteString.mo21896v(this, 0, byteString.m21892r());
        return this;
    }

    @Override // p204t6.InterfaceC6323d
    /* renamed from: Q, reason: merged with bridge method [inline-methods] */
    public C6322c write(byte[] bArr) {
        C0042f.m158e(bArr, "source");
        return write(bArr, 0, bArr.length);
    }

    @Override // p204t6.InterfaceC6323d
    /* renamed from: R, reason: merged with bridge method [inline-methods] */
    public C6322c write(byte[] bArr, int i9, int i10) {
        C0042f.m158e(bArr, "source");
        long j9 = i10;
        C6320a.m24153b(bArr.length, i9, j9);
        int i11 = i10 + i9;
        while (i9 < i11) {
            C6338s c6338sM24202O = m24202O(1);
            int iMin = Math.min(i11 - i9, 8192 - c6338sM24202O.f21369c);
            int i12 = i9 + iMin;
            C5222e.m20380c(bArr, c6338sM24202O.f21367a, c6338sM24202O.f21369c, i9, i12);
            c6338sM24202O.f21369c += iMin;
            i9 = i12;
        }
        m24199L(size() + j9);
        return this;
    }

    @Override // p204t6.InterfaceC6323d
    /* renamed from: S, reason: merged with bridge method [inline-methods] */
    public C6322c writeByte(int i9) {
        C6338s c6338sM24202O = m24202O(1);
        byte[] bArr = c6338sM24202O.f21367a;
        int i10 = c6338sM24202O.f21369c;
        c6338sM24202O.f21369c = i10 + 1;
        bArr[i10] = (byte) i9;
        m24199L(size() + 1);
        return this;
    }

    @Override // p204t6.InterfaceC6323d
    /* renamed from: T, reason: merged with bridge method [inline-methods] */
    public C6322c mo24229s(long j9) {
        boolean z8;
        if (j9 == 0) {
            return writeByte(48);
        }
        int i9 = 1;
        if (j9 < 0) {
            j9 = -j9;
            if (j9 < 0) {
                return mo24221j("-9223372036854775808");
            }
            z8 = true;
        } else {
            z8 = false;
        }
        if (j9 >= 100000000) {
            i9 = j9 < 1000000000000L ? j9 < 10000000000L ? j9 < C3322C.NANOS_PER_SECOND ? 9 : 10 : j9 < 100000000000L ? 11 : 12 : j9 < 1000000000000000L ? j9 < 10000000000000L ? 13 : j9 < 100000000000000L ? 14 : 15 : j9 < 100000000000000000L ? j9 < 10000000000000000L ? 16 : 17 : j9 < 1000000000000000000L ? 18 : 19;
        } else if (j9 >= 10000) {
            i9 = j9 < C3322C.MICROS_PER_SECOND ? j9 < 100000 ? 5 : 6 : j9 < 10000000 ? 7 : 8;
        } else if (j9 >= 100) {
            i9 = j9 < 1000 ? 3 : 4;
        } else if (j9 >= 10) {
            i9 = 2;
        }
        if (z8) {
            i9++;
        }
        C6338s c6338sM24202O = m24202O(i9);
        byte[] bArr = c6338sM24202O.f21367a;
        int i10 = c6338sM24202O.f21369c + i9;
        while (j9 != 0) {
            long j10 = 10;
            i10--;
            bArr[i10] = C6423a.m24575a()[(int) (j9 % j10)];
            j9 /= j10;
        }
        if (z8) {
            bArr[i10 - 1] = 45;
        }
        c6338sM24202O.f21369c += i9;
        m24199L(size() + i9);
        return this;
    }

    @Override // p204t6.InterfaceC6323d
    /* renamed from: U, reason: merged with bridge method [inline-methods] */
    public C6322c mo24223l(long j9) {
        if (j9 == 0) {
            return writeByte(48);
        }
        long j10 = (j9 >>> 1) | j9;
        long j11 = j10 | (j10 >>> 2);
        long j12 = j11 | (j11 >>> 4);
        long j13 = j12 | (j12 >>> 8);
        long j14 = j13 | (j13 >>> 16);
        long j15 = j14 | (j14 >>> 32);
        long j16 = j15 - ((j15 >>> 1) & 6148914691236517205L);
        long j17 = ((j16 >>> 2) & 3689348814741910323L) + (j16 & 3689348814741910323L);
        long j18 = ((j17 >>> 4) + j17) & 1085102592571150095L;
        long j19 = j18 + (j18 >>> 8);
        long j20 = j19 + (j19 >>> 16);
        int i9 = (int) ((((j20 & 63) + ((j20 >>> 32) & 63)) + 3) / 4);
        C6338s c6338sM24202O = m24202O(i9);
        byte[] bArr = c6338sM24202O.f21367a;
        int i10 = c6338sM24202O.f21369c;
        for (int i11 = (i10 + i9) - 1; i11 >= i10; i11--) {
            bArr[i11] = C6423a.m24575a()[(int) (15 & j9)];
            j9 >>>= 4;
        }
        c6338sM24202O.f21369c += i9;
        m24199L(size() + i9);
        return this;
    }

    @Override // p204t6.InterfaceC6323d
    /* renamed from: V, reason: merged with bridge method [inline-methods] */
    public C6322c writeInt(int i9) {
        C6338s c6338sM24202O = m24202O(4);
        byte[] bArr = c6338sM24202O.f21367a;
        int i10 = c6338sM24202O.f21369c;
        int i11 = i10 + 1;
        bArr[i10] = (byte) ((i9 >>> 24) & 255);
        int i12 = i11 + 1;
        bArr[i11] = (byte) ((i9 >>> 16) & 255);
        int i13 = i12 + 1;
        bArr[i12] = (byte) ((i9 >>> 8) & 255);
        bArr[i13] = (byte) (i9 & 255);
        c6338sM24202O.f21369c = i13 + 1;
        m24199L(size() + 4);
        return this;
    }

    @Override // p204t6.InterfaceC6323d
    /* renamed from: W, reason: merged with bridge method [inline-methods] */
    public C6322c writeShort(int i9) {
        C6338s c6338sM24202O = m24202O(2);
        byte[] bArr = c6338sM24202O.f21367a;
        int i10 = c6338sM24202O.f21369c;
        int i11 = i10 + 1;
        bArr[i10] = (byte) ((i9 >>> 8) & 255);
        bArr[i11] = (byte) (i9 & 255);
        c6338sM24202O.f21369c = i11 + 1;
        m24199L(size() + 2);
        return this;
    }

    /* renamed from: X */
    public C6322c m24211X(String str, int i9, int i10, Charset charset) {
        C0042f.m158e(str, "string");
        C0042f.m158e(charset, "charset");
        if (!(i9 >= 0)) {
            throw new IllegalArgumentException(("beginIndex < 0: " + i9).toString());
        }
        if (!(i10 >= i9)) {
            throw new IllegalArgumentException(("endIndex < beginIndex: " + i10 + " < " + i9).toString());
        }
        if (!(i10 <= str.length())) {
            throw new IllegalArgumentException(("endIndex > string.length: " + i10 + " > " + str.length()).toString());
        }
        if (C0042f.m154a(charset, C5246c.f17846b)) {
            return m24213Z(str, i9, i10);
        }
        String strSubstring = str.substring(i9, i10);
        C0042f.m157d(strSubstring, "this as java.lang.String…ing(startIndex, endIndex)");
        byte[] bytes = strSubstring.getBytes(charset);
        C0042f.m157d(bytes, "this as java.lang.String).getBytes(charset)");
        return write(bytes, 0, bytes.length);
    }

    @Override // p204t6.InterfaceC6323d
    /* renamed from: Y, reason: merged with bridge method [inline-methods] */
    public C6322c mo24221j(String str) {
        C0042f.m158e(str, "string");
        return m24213Z(str, 0, str.length());
    }

    /* renamed from: Z */
    public C6322c m24213Z(String str, int i9, int i10) {
        char cCharAt;
        C0042f.m158e(str, "string");
        if (!(i9 >= 0)) {
            throw new IllegalArgumentException(("beginIndex < 0: " + i9).toString());
        }
        if (!(i10 >= i9)) {
            throw new IllegalArgumentException(("endIndex < beginIndex: " + i10 + " < " + i9).toString());
        }
        if (!(i10 <= str.length())) {
            throw new IllegalArgumentException(("endIndex > string.length: " + i10 + " > " + str.length()).toString());
        }
        while (i9 < i10) {
            char cCharAt2 = str.charAt(i9);
            if (cCharAt2 < 128) {
                C6338s c6338sM24202O = m24202O(1);
                byte[] bArr = c6338sM24202O.f21367a;
                int i11 = c6338sM24202O.f21369c - i9;
                int iMin = Math.min(i10, 8192 - i11);
                int i12 = i9 + 1;
                bArr[i9 + i11] = (byte) cCharAt2;
                while (true) {
                    i9 = i12;
                    if (i9 >= iMin || (cCharAt = str.charAt(i9)) >= 128) {
                        break;
                    }
                    i12 = i9 + 1;
                    bArr[i9 + i11] = (byte) cCharAt;
                }
                int i13 = c6338sM24202O.f21369c;
                int i14 = (i11 + i9) - i13;
                c6338sM24202O.f21369c = i13 + i14;
                m24199L(size() + i14);
            } else {
                if (cCharAt2 < 2048) {
                    C6338s c6338sM24202O2 = m24202O(2);
                    byte[] bArr2 = c6338sM24202O2.f21367a;
                    int i15 = c6338sM24202O2.f21369c;
                    bArr2[i15] = (byte) ((cCharAt2 >> 6) | PsExtractor.AUDIO_STREAM);
                    bArr2[i15 + 1] = (byte) ((cCharAt2 & '?') | 128);
                    c6338sM24202O2.f21369c = i15 + 2;
                    m24199L(size() + 2);
                } else if (cCharAt2 < 55296 || cCharAt2 > 57343) {
                    C6338s c6338sM24202O3 = m24202O(3);
                    byte[] bArr3 = c6338sM24202O3.f21367a;
                    int i16 = c6338sM24202O3.f21369c;
                    bArr3[i16] = (byte) ((cCharAt2 >> '\f') | 224);
                    bArr3[i16 + 1] = (byte) ((63 & (cCharAt2 >> 6)) | 128);
                    bArr3[i16 + 2] = (byte) ((cCharAt2 & '?') | 128);
                    c6338sM24202O3.f21369c = i16 + 3;
                    m24199L(size() + 3);
                } else {
                    int i17 = i9 + 1;
                    char cCharAt3 = i17 < i10 ? str.charAt(i17) : (char) 0;
                    if (cCharAt2 <= 56319) {
                        if (56320 <= cCharAt3 && cCharAt3 < 57344) {
                            int i18 = (((cCharAt2 & 1023) << 10) | (cCharAt3 & 1023)) + C3322C.DEFAULT_BUFFER_SEGMENT_SIZE;
                            C6338s c6338sM24202O4 = m24202O(4);
                            byte[] bArr4 = c6338sM24202O4.f21367a;
                            int i19 = c6338sM24202O4.f21369c;
                            bArr4[i19] = (byte) ((i18 >> 18) | PsExtractor.VIDEO_STREAM_MASK);
                            bArr4[i19 + 1] = (byte) (((i18 >> 12) & 63) | 128);
                            bArr4[i19 + 2] = (byte) (((i18 >> 6) & 63) | 128);
                            bArr4[i19 + 3] = (byte) ((i18 & 63) | 128);
                            c6338sM24202O4.f21369c = i19 + 4;
                            m24199L(size() + 4);
                            i9 += 2;
                        }
                    }
                    writeByte(63);
                    i9 = i17;
                }
                i9++;
            }
        }
        return this;
    }

    @Override // p204t6.InterfaceC6342w
    /* renamed from: a */
    public C6343x mo21076a() {
        return C6343x.f21381e;
    }

    /* renamed from: a0 */
    public C6322c m24214a0(int i9) {
        if (i9 < 128) {
            writeByte(i9);
        } else if (i9 < 2048) {
            C6338s c6338sM24202O = m24202O(2);
            byte[] bArr = c6338sM24202O.f21367a;
            int i10 = c6338sM24202O.f21369c;
            bArr[i10] = (byte) ((i9 >> 6) | PsExtractor.AUDIO_STREAM);
            bArr[i10 + 1] = (byte) ((i9 & 63) | 128);
            c6338sM24202O.f21369c = i10 + 2;
            m24199L(size() + 2);
        } else {
            boolean z8 = false;
            if (55296 <= i9 && i9 < 57344) {
                z8 = true;
            }
            if (z8) {
                writeByte(63);
            } else if (i9 < 65536) {
                C6338s c6338sM24202O2 = m24202O(3);
                byte[] bArr2 = c6338sM24202O2.f21367a;
                int i11 = c6338sM24202O2.f21369c;
                bArr2[i11] = (byte) ((i9 >> 12) | 224);
                bArr2[i11 + 1] = (byte) (((i9 >> 6) & 63) | 128);
                bArr2[i11 + 2] = (byte) ((i9 & 63) | 128);
                c6338sM24202O2.f21369c = i11 + 3;
                m24199L(size() + 3);
            } else {
                if (i9 > 1114111) {
                    throw new IllegalArgumentException("Unexpected code point: 0x" + C6320a.m24160i(i9));
                }
                C6338s c6338sM24202O3 = m24202O(4);
                byte[] bArr3 = c6338sM24202O3.f21367a;
                int i12 = c6338sM24202O3.f21369c;
                bArr3[i12] = (byte) ((i9 >> 18) | PsExtractor.VIDEO_STREAM_MASK);
                bArr3[i12 + 1] = (byte) (((i9 >> 12) & 63) | 128);
                bArr3[i12 + 2] = (byte) (((i9 >> 6) & 63) | 128);
                bArr3[i12 + 3] = (byte) ((i9 & 63) | 128);
                c6338sM24202O3.f21369c = i12 + 4;
                m24199L(size() + 4);
            }
        }
        return this;
    }

    @Override // p204t6.InterfaceC6324e, p204t6.InterfaceC6323d
    /* renamed from: b */
    public C6322c mo24215b() {
        return this;
    }

    @Override // p204t6.InterfaceC6324e
    /* renamed from: c */
    public InputStream mo24216c() {
        return new b();
    }

    @Override // p204t6.InterfaceC6342w, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
    }

    @Override // p204t6.InterfaceC6342w
    /* renamed from: d */
    public long mo21077d(C6322c c6322c, long j9) {
        C0042f.m158e(c6322c, "sink");
        if (!(j9 >= 0)) {
            throw new IllegalArgumentException(("byteCount < 0: " + j9).toString());
        }
        if (size() == 0) {
            return -1L;
        }
        if (j9 > size()) {
            j9 = size();
        }
        c6322c.mo21082q(this, j9);
        return j9;
    }

    @Override // p204t6.InterfaceC6324e
    /* renamed from: e */
    public ByteString mo24217e(long j9) throws EOFException {
        if (!(j9 >= 0 && j9 <= 2147483647L)) {
            throw new IllegalArgumentException(("byteCount: " + j9).toString());
        }
        if (size() < j9) {
            throw new EOFException();
        }
        if (j9 < PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM) {
            return new ByteString(mo24226o(j9));
        }
        ByteString byteStringM24201N = m24201N((int) j9);
        skip(j9);
        return byteStringM24201N;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof C6322c) {
            C6322c c6322c = (C6322c) obj;
            if (size() == c6322c.size()) {
                if (size() == 0) {
                    return true;
                }
                C6338s c6338s = this.f21330b;
                C0042f.m155b(c6338s);
                C6338s c6338s2 = c6322c.f21330b;
                C0042f.m155b(c6338s2);
                int i9 = c6338s.f21368b;
                int i10 = c6338s2.f21368b;
                long j9 = 0;
                while (j9 < size()) {
                    long jMin = Math.min(c6338s.f21369c - i9, c6338s2.f21369c - i10);
                    long j10 = 0;
                    while (j10 < jMin) {
                        int i11 = i9 + 1;
                        int i12 = i10 + 1;
                        if (c6338s.f21367a[i9] == c6338s2.f21367a[i10]) {
                            j10++;
                            i9 = i11;
                            i10 = i12;
                        }
                    }
                    if (i9 == c6338s.f21369c) {
                        c6338s = c6338s.f21372f;
                        C0042f.m155b(c6338s);
                        i9 = c6338s.f21368b;
                    }
                    if (i10 == c6338s2.f21369c) {
                        c6338s2 = c6338s2.f21372f;
                        C0042f.m155b(c6338s2);
                        i10 = c6338s2.f21368b;
                    }
                    j9 += jMin;
                }
                return true;
            }
        }
        return false;
    }

    @Override // p204t6.InterfaceC6323d, p204t6.InterfaceC6340u, java.io.Flushable
    public void flush() {
    }

    @Override // p204t6.InterfaceC6324e
    /* renamed from: g */
    public boolean mo24218g() {
        return this.f21331c == 0;
    }

    @Override // p204t6.InterfaceC6323d
    /* renamed from: h */
    public long mo24219h(InterfaceC6342w interfaceC6342w) {
        C0042f.m158e(interfaceC6342w, "source");
        long j9 = 0;
        while (true) {
            long jMo21077d = interfaceC6342w.mo21077d(this, PlaybackStateCompat.ACTION_PLAY_FROM_URI);
            if (jMo21077d == -1) {
                return j9;
            }
            j9 += jMo21077d;
        }
    }

    public int hashCode() {
        C6338s c6338s = this.f21330b;
        if (c6338s == null) {
            return 0;
        }
        int i9 = 1;
        do {
            int i10 = c6338s.f21369c;
            for (int i11 = c6338s.f21368b; i11 < i10; i11++) {
                i9 = (i9 * 31) + c6338s.f21367a[i11];
            }
            c6338s = c6338s.f21372f;
            C0042f.m155b(c6338s);
        } while (c6338s != this.f21330b);
        return i9;
    }

    @Override // p204t6.InterfaceC6324e
    /* renamed from: i */
    public String mo24220i(long j9) throws EOFException {
        if (!(j9 >= 0)) {
            throw new IllegalArgumentException(("limit < 0: " + j9).toString());
        }
        long j10 = j9 != Long.MAX_VALUE ? j9 + 1 : Long.MAX_VALUE;
        long jM24188A = m24188A((byte) 10, 0L, j10);
        if (jM24188A != -1) {
            return C6423a.m24576b(this, jM24188A);
        }
        if (j10 < size() && m24236z(j10 - 1) == 13 && m24236z(j10) == 10) {
            return C6423a.m24576b(this, j10);
        }
        C6322c c6322c = new C6322c();
        m24235y(c6322c, 0L, Math.min(32, size()));
        throw new EOFException("\\n not found: limit=" + Math.min(size(), j9) + " content=" + c6322c.m24192E().mo21883i() + (char) 8230);
    }

    @Override // java.nio.channels.Channel
    public boolean isOpen() {
        return true;
    }

    @Override // p204t6.InterfaceC6324e
    /* renamed from: k */
    public String mo24222k(Charset charset) {
        C0042f.m158e(charset, "charset");
        return m24196I(this.f21331c, charset);
    }

    @Override // p204t6.InterfaceC6324e
    /* renamed from: m */
    public String mo24224m() {
        return mo24220i(Long.MAX_VALUE);
    }

    @Override // p204t6.InterfaceC6324e
    /* renamed from: n */
    public int mo24225n(C6334o c6334o) throws EOFException {
        C0042f.m158e(c6334o, "options");
        int iM24578d = C6423a.m24578d(this, c6334o, false, 2, null);
        if (iM24578d == -1) {
            return -1;
        }
        skip(c6334o.m24272d()[iM24578d].m21892r());
        return iM24578d;
    }

    @Override // p204t6.InterfaceC6324e
    /* renamed from: o */
    public byte[] mo24226o(long j9) throws EOFException {
        if (!(j9 >= 0 && j9 <= 2147483647L)) {
            throw new IllegalArgumentException(("byteCount: " + j9).toString());
        }
        if (size() < j9) {
            throw new EOFException();
        }
        byte[] bArr = new byte[(int) j9];
        m24193F(bArr);
        return bArr;
    }

    @Override // p204t6.InterfaceC6340u
    /* renamed from: q */
    public void mo21082q(C6322c c6322c, long j9) {
        C6338s c6338s;
        C0042f.m158e(c6322c, "source");
        if (!(c6322c != this)) {
            throw new IllegalArgumentException("source == this".toString());
        }
        C6320a.m24153b(c6322c.size(), 0L, j9);
        while (j9 > 0) {
            C6338s c6338s2 = c6322c.f21330b;
            C0042f.m155b(c6338s2);
            int i9 = c6338s2.f21369c;
            C0042f.m155b(c6322c.f21330b);
            if (j9 < i9 - r2.f21368b) {
                C6338s c6338s3 = this.f21330b;
                if (c6338s3 != null) {
                    C0042f.m155b(c6338s3);
                    c6338s = c6338s3.f21373g;
                } else {
                    c6338s = null;
                }
                if (c6338s != null && c6338s.f21371e) {
                    if ((c6338s.f21369c + j9) - (c6338s.f21370d ? 0 : c6338s.f21368b) <= PlaybackStateCompat.ACTION_PLAY_FROM_URI) {
                        C6338s c6338s4 = c6322c.f21330b;
                        C0042f.m155b(c6338s4);
                        c6338s4.m24291f(c6338s, (int) j9);
                        c6322c.m24199L(c6322c.size() - j9);
                        m24199L(size() + j9);
                        return;
                    }
                }
                C6338s c6338s5 = c6322c.f21330b;
                C0042f.m155b(c6338s5);
                c6322c.f21330b = c6338s5.m24290e((int) j9);
            }
            C6338s c6338s6 = c6322c.f21330b;
            C0042f.m155b(c6338s6);
            long j10 = c6338s6.f21369c - c6338s6.f21368b;
            c6322c.f21330b = c6338s6.m24287b();
            C6338s c6338s7 = this.f21330b;
            if (c6338s7 == null) {
                this.f21330b = c6338s6;
                c6338s6.f21373g = c6338s6;
                c6338s6.f21372f = c6338s6;
            } else {
                C0042f.m155b(c6338s7);
                C6338s c6338s8 = c6338s7.f21373g;
                C0042f.m155b(c6338s8);
                c6338s8.m24288c(c6338s6).m24286a();
            }
            c6322c.m24199L(c6322c.size() - j10);
            m24199L(size() + j10);
            j9 -= j10;
        }
    }

    @Override // p204t6.InterfaceC6324e
    /* renamed from: r */
    public void mo24228r(long j9) throws EOFException {
        if (this.f21331c < j9) {
            throw new EOFException();
        }
    }

    @Override // java.nio.channels.ReadableByteChannel
    public int read(ByteBuffer byteBuffer) {
        C0042f.m158e(byteBuffer, "sink");
        C6338s c6338s = this.f21330b;
        if (c6338s == null) {
            return -1;
        }
        int iMin = Math.min(byteBuffer.remaining(), c6338s.f21369c - c6338s.f21368b);
        byteBuffer.put(c6338s.f21367a, c6338s.f21368b, iMin);
        int i9 = c6338s.f21368b + iMin;
        c6338s.f21368b = i9;
        this.f21331c -= iMin;
        if (i9 == c6338s.f21369c) {
            this.f21330b = c6338s.m24287b();
            C6339t.m24292b(c6338s);
        }
        return iMin;
    }

    @Override // p204t6.InterfaceC6324e
    public byte readByte() throws EOFException {
        if (size() == 0) {
            throw new EOFException();
        }
        C6338s c6338s = this.f21330b;
        C0042f.m155b(c6338s);
        int i9 = c6338s.f21368b;
        int i10 = c6338s.f21369c;
        int i11 = i9 + 1;
        byte b9 = c6338s.f21367a[i9];
        m24199L(size() - 1);
        if (i11 == i10) {
            this.f21330b = c6338s.m24287b();
            C6339t.m24292b(c6338s);
        } else {
            c6338s.f21368b = i11;
        }
        return b9;
    }

    @Override // p204t6.InterfaceC6324e
    public int readInt() throws EOFException {
        if (size() < 4) {
            throw new EOFException();
        }
        C6338s c6338s = this.f21330b;
        C0042f.m155b(c6338s);
        int i9 = c6338s.f21368b;
        int i10 = c6338s.f21369c;
        if (i10 - i9 < 4) {
            return ((readByte() & UnsignedBytes.MAX_VALUE) << 24) | ((readByte() & UnsignedBytes.MAX_VALUE) << 16) | ((readByte() & UnsignedBytes.MAX_VALUE) << 8) | (readByte() & UnsignedBytes.MAX_VALUE);
        }
        byte[] bArr = c6338s.f21367a;
        int i11 = i9 + 1;
        int i12 = i11 + 1;
        int i13 = ((bArr[i9] & UnsignedBytes.MAX_VALUE) << 24) | ((bArr[i11] & UnsignedBytes.MAX_VALUE) << 16);
        int i14 = i12 + 1;
        int i15 = i13 | ((bArr[i12] & UnsignedBytes.MAX_VALUE) << 8);
        int i16 = i14 + 1;
        int i17 = i15 | (bArr[i14] & UnsignedBytes.MAX_VALUE);
        m24199L(size() - 4);
        if (i16 == i10) {
            this.f21330b = c6338s.m24287b();
            C6339t.m24292b(c6338s);
        } else {
            c6338s.f21368b = i16;
        }
        return i17;
    }

    @Override // p204t6.InterfaceC6324e
    public short readShort() throws EOFException {
        if (size() < 2) {
            throw new EOFException();
        }
        C6338s c6338s = this.f21330b;
        C0042f.m155b(c6338s);
        int i9 = c6338s.f21368b;
        int i10 = c6338s.f21369c;
        if (i10 - i9 < 2) {
            return (short) (((readByte() & UnsignedBytes.MAX_VALUE) << 8) | (readByte() & UnsignedBytes.MAX_VALUE));
        }
        byte[] bArr = c6338s.f21367a;
        int i11 = i9 + 1;
        int i12 = i11 + 1;
        int i13 = ((bArr[i9] & UnsignedBytes.MAX_VALUE) << 8) | (bArr[i11] & UnsignedBytes.MAX_VALUE);
        m24199L(size() - 2);
        if (i12 == i10) {
            this.f21330b = c6338s.m24287b();
            C6339t.m24292b(c6338s);
        } else {
            c6338s.f21368b = i12;
        }
        return (short) i13;
    }

    public final long size() {
        return this.f21331c;
    }

    @Override // p204t6.InterfaceC6324e
    public void skip(long j9) throws EOFException {
        while (j9 > 0) {
            C6338s c6338s = this.f21330b;
            if (c6338s == null) {
                throw new EOFException();
            }
            int iMin = (int) Math.min(j9, c6338s.f21369c - c6338s.f21368b);
            long j10 = iMin;
            m24199L(size() - j10);
            j9 -= j10;
            int i9 = c6338s.f21368b + iMin;
            c6338s.f21368b = i9;
            if (i9 == c6338s.f21369c) {
                this.f21330b = c6338s.m24287b();
                C6339t.m24292b(c6338s);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x0098  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00a2  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00a6  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00aa A[EDGE_INSN: B:44:0x00aa->B:38:0x00aa BREAK  A[LOOP:0: B:5:0x000d->B:46:?], SYNTHETIC] */
    @Override // p204t6.InterfaceC6324e
    /* renamed from: t */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public long mo24230t() throws EOFException {
        int i9;
        int i10;
        if (size() == 0) {
            throw new EOFException();
        }
        int i11 = 0;
        boolean z8 = false;
        long j9 = 0;
        do {
            C6338s c6338s = this.f21330b;
            C0042f.m155b(c6338s);
            byte[] bArr = c6338s.f21367a;
            int i12 = c6338s.f21368b;
            int i13 = c6338s.f21369c;
            while (i12 < i13) {
                byte b9 = bArr[i12];
                if (b9 < 48 || b9 > 57) {
                    if (b9 >= 97 && b9 <= 102) {
                        i9 = b9 - 97;
                    } else if (b9 >= 65 && b9 <= 70) {
                        i9 = b9 - 65;
                    } else {
                        if (i11 == 0) {
                            throw new NumberFormatException("Expected leading [0-9a-fA-F] character but was 0x" + C6320a.m24159h(b9));
                        }
                        z8 = true;
                        if (i12 != i13) {
                            this.f21330b = c6338s.m24287b();
                            C6339t.m24292b(c6338s);
                        } else {
                            c6338s.f21368b = i12;
                        }
                        if (!z8) {
                            break;
                        }
                    }
                    i10 = i9 + 10;
                } else {
                    i10 = b9 - 48;
                }
                if (((-1152921504606846976L) & j9) != 0) {
                    throw new NumberFormatException("Number too large: " + new C6322c().mo24223l(j9).writeByte(b9).m24197J());
                }
                j9 = (j9 << 4) | i10;
                i12++;
                i11++;
            }
            if (i12 != i13) {
            }
            if (!z8) {
            }
        } while (this.f21330b != null);
        m24199L(size() - i11);
        return j9;
    }

    public String toString() {
        return m24200M().toString();
    }

    /* renamed from: u */
    public final void m24231u() throws EOFException {
        skip(size());
    }

    /* renamed from: v, reason: merged with bridge method [inline-methods] */
    public C6322c clone() {
        return m24234x();
    }

    /* renamed from: w */
    public final long m24233w() {
        long size = size();
        if (size == 0) {
            return 0L;
        }
        C6338s c6338s = this.f21330b;
        C0042f.m155b(c6338s);
        C6338s c6338s2 = c6338s.f21373g;
        C0042f.m155b(c6338s2);
        if (c6338s2.f21369c < 8192 && c6338s2.f21371e) {
            size -= r3 - c6338s2.f21368b;
        }
        return size;
    }

    /* renamed from: x */
    public final C6322c m24234x() {
        C6322c c6322c = new C6322c();
        if (size() != 0) {
            C6338s c6338s = this.f21330b;
            C0042f.m155b(c6338s);
            C6338s c6338sM24289d = c6338s.m24289d();
            c6322c.f21330b = c6338sM24289d;
            c6338sM24289d.f21373g = c6338sM24289d;
            c6338sM24289d.f21372f = c6338sM24289d;
            for (C6338s c6338s2 = c6338s.f21372f; c6338s2 != c6338s; c6338s2 = c6338s2.f21372f) {
                C6338s c6338s3 = c6338sM24289d.f21373g;
                C0042f.m155b(c6338s3);
                C0042f.m155b(c6338s2);
                c6338s3.m24288c(c6338s2.m24289d());
            }
            c6322c.m24199L(size());
        }
        return c6322c;
    }

    /* renamed from: y */
    public final C6322c m24235y(C6322c c6322c, long j9, long j10) {
        C0042f.m158e(c6322c, "out");
        C6320a.m24153b(size(), j9, j10);
        if (j10 != 0) {
            c6322c.m24199L(c6322c.size() + j10);
            C6338s c6338s = this.f21330b;
            while (true) {
                C0042f.m155b(c6338s);
                int i9 = c6338s.f21369c;
                int i10 = c6338s.f21368b;
                if (j9 < i9 - i10) {
                    break;
                }
                j9 -= i9 - i10;
                c6338s = c6338s.f21372f;
            }
            while (j10 > 0) {
                C0042f.m155b(c6338s);
                C6338s c6338sM24289d = c6338s.m24289d();
                int i11 = c6338sM24289d.f21368b + ((int) j9);
                c6338sM24289d.f21368b = i11;
                c6338sM24289d.f21369c = Math.min(i11 + ((int) j10), c6338sM24289d.f21369c);
                C6338s c6338s2 = c6322c.f21330b;
                if (c6338s2 == null) {
                    c6338sM24289d.f21373g = c6338sM24289d;
                    c6338sM24289d.f21372f = c6338sM24289d;
                    c6322c.f21330b = c6338sM24289d;
                } else {
                    C0042f.m155b(c6338s2);
                    C6338s c6338s3 = c6338s2.f21373g;
                    C0042f.m155b(c6338s3);
                    c6338s3.m24288c(c6338sM24289d);
                }
                j10 -= c6338sM24289d.f21369c - c6338sM24289d.f21368b;
                c6338s = c6338s.f21372f;
                j9 = 0;
            }
        }
        return this;
    }

    /* renamed from: z */
    public final byte m24236z(long j9) {
        C6320a.m24153b(size(), j9, 1L);
        C6338s c6338s = this.f21330b;
        if (c6338s == null) {
            C0042f.m155b(null);
            throw null;
        }
        if (size() - j9 < j9) {
            long size = size();
            while (size > j9) {
                c6338s = c6338s.f21373g;
                C0042f.m155b(c6338s);
                size -= c6338s.f21369c - c6338s.f21368b;
            }
            C0042f.m155b(c6338s);
            return c6338s.f21367a[(int) ((c6338s.f21368b + j9) - size)];
        }
        long j10 = 0;
        while (true) {
            long j11 = (c6338s.f21369c - c6338s.f21368b) + j10;
            if (j11 > j9) {
                C0042f.m155b(c6338s);
                return c6338s.f21367a[(int) ((c6338s.f21368b + j9) - j10)];
            }
            c6338s = c6338s.f21372f;
            C0042f.m155b(c6338s);
            j10 = j11;
        }
    }

    /* renamed from: t6.c$b */
    public static final class b extends InputStream {
        public b() {
        }

        @Override // java.io.InputStream
        public int available() {
            return (int) Math.min(C6322c.this.size(), Integer.MAX_VALUE);
        }

        @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
        }

        @Override // java.io.InputStream
        public int read() {
            if (C6322c.this.size() > 0) {
                return C6322c.this.readByte() & UnsignedBytes.MAX_VALUE;
            }
            return -1;
        }

        public String toString() {
            return C6322c.this + ".inputStream()";
        }

        @Override // java.io.InputStream
        public int read(byte[] bArr, int i9, int i10) {
            C0042f.m158e(bArr, "sink");
            return C6322c.this.read(bArr, i9, i10);
        }
    }

    @Override // java.nio.channels.WritableByteChannel
    public int write(ByteBuffer byteBuffer) {
        C0042f.m158e(byteBuffer, "source");
        int iRemaining = byteBuffer.remaining();
        int i9 = iRemaining;
        while (i9 > 0) {
            C6338s c6338sM24202O = m24202O(1);
            int iMin = Math.min(i9, 8192 - c6338sM24202O.f21369c);
            byteBuffer.get(c6338sM24202O.f21367a, c6338sM24202O.f21369c, iMin);
            i9 -= iMin;
            c6338sM24202O.f21369c += iMin;
        }
        this.f21331c += iRemaining;
        return iRemaining;
    }

    public int read(byte[] bArr, int i9, int i10) {
        C0042f.m158e(bArr, "sink");
        C6320a.m24153b(bArr.length, i9, i10);
        C6338s c6338s = this.f21330b;
        if (c6338s == null) {
            return -1;
        }
        int iMin = Math.min(i10, c6338s.f21369c - c6338s.f21368b);
        byte[] bArr2 = c6338s.f21367a;
        int i11 = c6338s.f21368b;
        C5222e.m20380c(bArr2, bArr, i9, i11, i11 + iMin);
        c6338s.f21368b += iMin;
        m24199L(size() - iMin);
        if (c6338s.f21368b == c6338s.f21369c) {
            this.f21330b = c6338s.m24287b();
            C6339t.m24292b(c6338s);
        }
        return iMin;
    }
}
