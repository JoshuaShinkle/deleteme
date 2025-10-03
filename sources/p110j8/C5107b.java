package p110j8;

import com.google.common.primitives.UnsignedBytes;
import java.io.ByteArrayOutputStream;

/* renamed from: j8.b */
/* loaded from: classes3.dex */
public class C5107b {

    /* renamed from: a */
    public String f17570a;

    /* renamed from: b */
    public boolean f17571b;

    /* renamed from: c */
    public boolean f17572c;

    public C5107b(String str, boolean z8, boolean z9) {
        this.f17570a = str;
        this.f17571b = z8;
        this.f17572c = z9;
    }

    /* renamed from: a */
    public static int m19955a(int i9) {
        if (i9 == 1) {
            return 6;
        }
        if (i9 == 2) {
            return 4;
        }
        if (i9 == 3) {
            return 3;
        }
        if (i9 != 4) {
            return i9 != 5 ? -1 : 0;
        }
        return 1;
    }

    /* renamed from: b */
    public String m19956b(byte[] bArr) {
        int i9;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        for (int i10 = 0; i10 < (bArr.length + 4) / 5; i10++) {
            short[] sArr = new short[5];
            int[] iArr = new int[8];
            int i11 = 5;
            for (int i12 = 0; i12 < 5; i12++) {
                int i13 = (i10 * 5) + i12;
                if (i13 < bArr.length) {
                    sArr[i12] = (short) (bArr[i13] & UnsignedBytes.MAX_VALUE);
                } else {
                    sArr[i12] = 0;
                    i11--;
                }
            }
            int iM19955a = m19955a(i11);
            short s8 = sArr[0];
            iArr[0] = (byte) ((s8 >> 3) & 31);
            short s9 = sArr[1];
            iArr[1] = (byte) (((s8 & 7) << 2) | ((s9 >> 6) & 3));
            iArr[2] = (byte) ((s9 >> 1) & 31);
            short s10 = sArr[2];
            iArr[3] = (byte) (((s9 & 1) << 4) | ((s10 >> 4) & 15));
            int i14 = (s10 & 15) << 1;
            short s11 = sArr[3];
            iArr[4] = (byte) (i14 | (1 & (s11 >> 7)));
            iArr[5] = (byte) ((s11 >> 2) & 31);
            short s12 = sArr[4];
            iArr[6] = (byte) (((s12 >> 5) & 7) | ((s11 & 3) << 3));
            iArr[7] = (byte) (s12 & 31);
            int i15 = 0;
            while (true) {
                i9 = 8 - iM19955a;
                if (i15 >= i9) {
                    break;
                }
                char cCharAt = this.f17570a.charAt(iArr[i15]);
                if (this.f17572c) {
                    cCharAt = Character.toLowerCase(cCharAt);
                }
                byteArrayOutputStream.write(cCharAt);
                i15++;
            }
            if (this.f17571b) {
                while (i9 < 8) {
                    byteArrayOutputStream.write(61);
                    i9++;
                }
            }
        }
        return new String(byteArrayOutputStream.toByteArray());
    }
}
