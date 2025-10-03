package p099i7;

import java.io.DataInputStream;
import java.io.IOException;
import org.jivesoftware.smack.SmackException;

/* renamed from: i7.d */
/* loaded from: classes.dex */
public class C5061d {
    /* renamed from: a */
    public static byte[] m19828a(DataInputStream dataInputStream) throws SmackException, IOException {
        byte[] bArr = new byte[5];
        dataInputStream.readFully(bArr, 0, 5);
        if (bArr[3] != 3) {
            throw new SmackException("Unsupported SOCKS5 address type");
        }
        int i9 = bArr[4];
        byte[] bArr2 = new byte[i9 + 7];
        System.arraycopy(bArr, 0, bArr2, 0, 5);
        dataInputStream.readFully(bArr2, 5, i9 + 2);
        return bArr2;
    }
}
