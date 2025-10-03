package p110j8;

import com.google.common.primitives.UnsignedBytes;
import java.io.ByteArrayOutputStream;

/* renamed from: j8.a */
/* loaded from: classes3.dex */
public class C5106a {
    /* renamed from: a */
    public static String m19954a(byte[] bArr) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        for (byte b9 : bArr) {
            short s8 = (short) (b9 & UnsignedBytes.MAX_VALUE);
            byteArrayOutputStream.write("0123456789ABCDEF".charAt((byte) (s8 >> 4)));
            byteArrayOutputStream.write("0123456789ABCDEF".charAt((byte) (s8 & 15)));
        }
        return new String(byteArrayOutputStream.toByteArray());
    }
}
