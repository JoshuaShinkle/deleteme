package okio;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import kotlin.collections.C5222e;
import p007a6.C0042f;
import p204t6.C6320a;
import p204t6.C6322c;
import p204t6.C6338s;
import p213u6.C6425c;

/* loaded from: classes.dex */
public final class SegmentedByteString extends ByteString {

    /* renamed from: f */
    public final transient byte[][] f19099f;

    /* renamed from: g */
    public final transient int[] f19100g;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SegmentedByteString(byte[][] bArr, int[] iArr) {
        super(ByteString.f19096e.m21879e());
        C0042f.m158e(bArr, "segments");
        C0042f.m158e(iArr, "directory");
        this.f19099f = bArr;
        this.f19100g = iArr;
    }

    private final Object writeReplace() {
        ByteString byteStringM21907z = m21907z();
        C0042f.m156c(byteStringM21907z, "null cannot be cast to non-null type java.lang.Object");
        return byteStringM21907z;
    }

    @Override // okio.ByteString
    /* renamed from: a */
    public String mo21875a() {
        return m21907z().mo21875a();
    }

    @Override // okio.ByteString
    /* renamed from: c */
    public ByteString mo21877c(String str) throws NoSuchAlgorithmException {
        C0042f.m158e(str, "algorithm");
        MessageDigest messageDigest = MessageDigest.getInstance(str);
        int length = m21905x().length;
        int i9 = 0;
        int i10 = 0;
        while (i9 < length) {
            int i11 = m21904w()[length + i9];
            int i12 = m21904w()[i9];
            messageDigest.update(m21905x()[i9], i11, i12 - i10);
            i9++;
            i10 = i12;
        }
        byte[] bArrDigest = messageDigest.digest();
        C0042f.m155b(bArrDigest);
        return new ByteString(bArrDigest);
    }

    @Override // okio.ByteString
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof ByteString) {
            ByteString byteString = (ByteString) obj;
            if (byteString.m21892r() == m21892r() && mo21886l(0, byteString, 0, m21892r())) {
                return true;
            }
        }
        return false;
    }

    @Override // okio.ByteString
    /* renamed from: g */
    public int mo21881g() {
        return m21904w()[m21905x().length - 1];
    }

    @Override // okio.ByteString
    public int hashCode() {
        int iM21880f = m21880f();
        if (iM21880f != 0) {
            return iM21880f;
        }
        int length = m21905x().length;
        int i9 = 0;
        int i10 = 1;
        int i11 = 0;
        while (i9 < length) {
            int i12 = m21904w()[length + i9];
            int i13 = m21904w()[i9];
            byte[] bArr = m21905x()[i9];
            int i14 = (i13 - i11) + i12;
            while (i12 < i14) {
                i10 = (i10 * 31) + bArr[i12];
                i12++;
            }
            i9++;
            i11 = i13;
        }
        m21888n(i10);
        return i10;
    }

    @Override // okio.ByteString
    /* renamed from: i */
    public String mo21883i() {
        return m21907z().mo21883i();
    }

    @Override // okio.ByteString
    /* renamed from: j */
    public byte[] mo21884j() {
        return m21906y();
    }

    @Override // okio.ByteString
    /* renamed from: k */
    public byte mo21885k(int i9) {
        C6320a.m24153b(m21904w()[m21905x().length - 1], i9, 1L);
        int iM24586b = C6425c.m24586b(this, i9);
        return m21905x()[iM24586b][(i9 - (iM24586b == 0 ? 0 : m21904w()[iM24586b - 1])) + m21904w()[m21905x().length + iM24586b]];
    }

    @Override // okio.ByteString
    /* renamed from: l */
    public boolean mo21886l(int i9, ByteString byteString, int i10, int i11) {
        C0042f.m158e(byteString, "other");
        if (i9 < 0 || i9 > m21892r() - i11) {
            return false;
        }
        int i12 = i11 + i9;
        int iM24586b = C6425c.m24586b(this, i9);
        while (i9 < i12) {
            int i13 = iM24586b == 0 ? 0 : m21904w()[iM24586b - 1];
            int i14 = m21904w()[iM24586b] - i13;
            int i15 = m21904w()[m21905x().length + iM24586b];
            int iMin = Math.min(i12, i14 + i13) - i9;
            if (!byteString.mo21887m(i10, m21905x()[iM24586b], i15 + (i9 - i13), iMin)) {
                return false;
            }
            i10 += iMin;
            i9 += iMin;
            iM24586b++;
        }
        return true;
    }

    @Override // okio.ByteString
    /* renamed from: m */
    public boolean mo21887m(int i9, byte[] bArr, int i10, int i11) {
        C0042f.m158e(bArr, "other");
        if (i9 < 0 || i9 > m21892r() - i11 || i10 < 0 || i10 > bArr.length - i11) {
            return false;
        }
        int i12 = i11 + i9;
        int iM24586b = C6425c.m24586b(this, i9);
        while (i9 < i12) {
            int i13 = iM24586b == 0 ? 0 : m21904w()[iM24586b - 1];
            int i14 = m21904w()[iM24586b] - i13;
            int i15 = m21904w()[m21905x().length + iM24586b];
            int iMin = Math.min(i12, i14 + i13) - i9;
            if (!C6320a.m24152a(m21905x()[iM24586b], i15 + (i9 - i13), bArr, i10, iMin)) {
                return false;
            }
            i10 += iMin;
            i9 += iMin;
            iM24586b++;
        }
        return true;
    }

    @Override // okio.ByteString
    /* renamed from: t */
    public ByteString mo21894t() {
        return m21907z().mo21894t();
    }

    @Override // okio.ByteString
    public String toString() {
        return m21907z().toString();
    }

    @Override // okio.ByteString
    /* renamed from: v */
    public void mo21896v(C6322c c6322c, int i9, int i10) {
        C0042f.m158e(c6322c, "buffer");
        int i11 = i9 + i10;
        int iM24586b = C6425c.m24586b(this, i9);
        while (i9 < i11) {
            int i12 = iM24586b == 0 ? 0 : m21904w()[iM24586b - 1];
            int i13 = m21904w()[iM24586b] - i12;
            int i14 = m21904w()[m21905x().length + iM24586b];
            int iMin = Math.min(i11, i13 + i12) - i9;
            int i15 = i14 + (i9 - i12);
            C6338s c6338s = new C6338s(m21905x()[iM24586b], i15, i15 + iMin, true, false);
            C6338s c6338s2 = c6322c.f21330b;
            if (c6338s2 == null) {
                c6338s.f21373g = c6338s;
                c6338s.f21372f = c6338s;
                c6322c.f21330b = c6338s;
            } else {
                C0042f.m155b(c6338s2);
                C6338s c6338s3 = c6338s2.f21373g;
                C0042f.m155b(c6338s3);
                c6338s3.m24288c(c6338s);
            }
            i9 += iMin;
            iM24586b++;
        }
        c6322c.m24199L(c6322c.size() + i10);
    }

    /* renamed from: w */
    public final int[] m21904w() {
        return this.f19100g;
    }

    /* renamed from: x */
    public final byte[][] m21905x() {
        return this.f19099f;
    }

    /* renamed from: y */
    public byte[] m21906y() {
        byte[] bArr = new byte[m21892r()];
        int length = m21905x().length;
        int i9 = 0;
        int i10 = 0;
        int i11 = 0;
        while (i9 < length) {
            int i12 = m21904w()[length + i9];
            int i13 = m21904w()[i9];
            int i14 = i13 - i10;
            C5222e.m20380c(m21905x()[i9], bArr, i11, i12, i12 + i14);
            i11 += i14;
            i9++;
            i10 = i13;
        }
        return bArr;
    }

    /* renamed from: z */
    public final ByteString m21907z() {
        return new ByteString(m21906y());
    }
}
