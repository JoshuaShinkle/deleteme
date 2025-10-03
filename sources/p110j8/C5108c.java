package p110j8;

import com.google.common.primitives.UnsignedBytes;
import java.io.ByteArrayOutputStream;

/* renamed from: j8.c */
/* loaded from: classes3.dex */
public class C5108c {
    /* renamed from: a */
    public static String m19957a(byte[] bArr, int i9, String str, boolean z8) {
        String strM19958b = m19958b(bArr);
        StringBuffer stringBuffer = new StringBuffer();
        int i10 = 0;
        while (i10 < strM19958b.length()) {
            stringBuffer.append(str);
            int i11 = i10 + i9;
            if (i11 >= strM19958b.length()) {
                stringBuffer.append(strM19958b.substring(i10));
                if (z8) {
                    stringBuffer.append(" )");
                }
            } else {
                stringBuffer.append(strM19958b.substring(i10, i11));
                stringBuffer.append("\n");
            }
            i10 = i11;
        }
        return stringBuffer.toString();
    }

    /* renamed from: b */
    public static String m19958b(byte[] bArr) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        for (int i9 = 0; i9 < (bArr.length + 2) / 3; i9++) {
            short[] sArr = new short[3];
            short[] sArr2 = new short[4];
            for (int i10 = 0; i10 < 3; i10++) {
                int i11 = (i9 * 3) + i10;
                if (i11 < bArr.length) {
                    sArr[i10] = (short) (bArr[i11] & UnsignedBytes.MAX_VALUE);
                } else {
                    sArr[i10] = -1;
                }
            }
            sArr2[0] = (short) (sArr[0] >> 2);
            short s8 = sArr[1];
            if (s8 == -1) {
                sArr2[1] = (short) ((sArr[0] & 3) << 4);
            } else {
                sArr2[1] = (short) (((sArr[0] & 3) << 4) + (s8 >> 4));
            }
            short s9 = sArr[1];
            if (s9 == -1) {
                sArr2[3] = 64;
                sArr2[2] = 64;
            } else {
                short s10 = sArr[2];
                if (s10 == -1) {
                    sArr2[2] = (short) ((s9 & 15) << 2);
                    sArr2[3] = 64;
                } else {
                    sArr2[2] = (short) (((s9 & 15) << 2) + (s10 >> 6));
                    sArr2[3] = (short) (sArr[2] & 63);
                }
            }
            for (int i12 = 0; i12 < 4; i12++) {
                byteArrayOutputStream.write("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=".charAt(sArr2[i12]));
            }
        }
        return new String(byteArrayOutputStream.toByteArray());
    }
}
