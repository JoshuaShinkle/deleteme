package p204t6;

import com.google.common.base.Ascii;
import kotlin.text.C5255l;
import okio.ByteString;
import p007a6.C0042f;
import p204t6.C6322c;
import p213u6.C6424b;

/* renamed from: t6.a */
/* loaded from: classes.dex */
public final class C6320a {

    /* renamed from: a */
    public static final C6322c.a f21315a = new C6322c.a();

    /* renamed from: b */
    public static final int f21316b = -1234567890;

    /* renamed from: a */
    public static final boolean m24152a(byte[] bArr, int i9, byte[] bArr2, int i10, int i11) {
        C0042f.m158e(bArr, "a");
        C0042f.m158e(bArr2, "b");
        for (int i12 = 0; i12 < i11; i12++) {
            if (bArr[i12 + i9] != bArr2[i12 + i10]) {
                return false;
            }
        }
        return true;
    }

    /* renamed from: b */
    public static final void m24153b(long j9, long j10, long j11) {
        if ((j10 | j11) < 0 || j10 > j9 || j9 - j10 < j11) {
            throw new ArrayIndexOutOfBoundsException("size=" + j9 + " offset=" + j10 + " byteCount=" + j11);
        }
    }

    /* renamed from: c */
    public static final int m24154c() {
        return f21316b;
    }

    /* renamed from: d */
    public static final int m24155d(ByteString byteString, int i9) {
        C0042f.m158e(byteString, "<this>");
        return i9 == f21316b ? byteString.m21892r() : i9;
    }

    /* renamed from: e */
    public static final int m24156e(byte[] bArr, int i9) {
        C0042f.m158e(bArr, "<this>");
        return i9 == f21316b ? bArr.length : i9;
    }

    /* renamed from: f */
    public static final int m24157f(int i9) {
        return ((i9 & 255) << 24) | (((-16777216) & i9) >>> 24) | ((16711680 & i9) >>> 8) | ((65280 & i9) << 8);
    }

    /* renamed from: g */
    public static final short m24158g(short s8) {
        int i9 = s8 & 65535;
        return (short) (((i9 & 255) << 8) | ((65280 & i9) >>> 8));
    }

    /* renamed from: h */
    public static final String m24159h(byte b9) {
        return C5255l.m20509h(new char[]{C6424b.m24584f()[(b9 >> 4) & 15], C6424b.m24584f()[b9 & Ascii.f15389SI]});
    }

    /* renamed from: i */
    public static final String m24160i(int i9) {
        if (i9 == 0) {
            return "0";
        }
        int i10 = 0;
        char[] cArr = {C6424b.m24584f()[(i9 >> 28) & 15], C6424b.m24584f()[(i9 >> 24) & 15], C6424b.m24584f()[(i9 >> 20) & 15], C6424b.m24584f()[(i9 >> 16) & 15], C6424b.m24584f()[(i9 >> 12) & 15], C6424b.m24584f()[(i9 >> 8) & 15], C6424b.m24584f()[(i9 >> 4) & 15], C6424b.m24584f()[i9 & 15]};
        while (i10 < 8 && cArr[i10] == '0') {
            i10++;
        }
        return C5255l.m20510i(cArr, i10, 8);
    }
}
