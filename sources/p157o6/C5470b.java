package p157o6;

import kotlin.text.C5255l;
import okio.ByteString;
import p007a6.C0042f;
import p098i6.C5057d;

/* renamed from: o6.b */
/* loaded from: classes.dex */
public final class C5470b {

    /* renamed from: a */
    public static final C5470b f18399a = new C5470b();

    /* renamed from: b */
    public static final ByteString f18400b = ByteString.f19095d.m21901d("PRI * HTTP/2.0\r\n\r\nSM\r\n\r\n");

    /* renamed from: c */
    public static final String[] f18401c = {"DATA", "HEADERS", "PRIORITY", "RST_STREAM", "SETTINGS", "PUSH_PROMISE", "PING", "GOAWAY", "WINDOW_UPDATE", "CONTINUATION"};

    /* renamed from: d */
    public static final String[] f18402d = new String[64];

    /* renamed from: e */
    public static final String[] f18403e;

    static {
        String[] strArr = new String[256];
        for (int i9 = 0; i9 < 256; i9++) {
            String binaryString = Integer.toBinaryString(i9);
            C0042f.m157d(binaryString, "toBinaryString(it)");
            strArr[i9] = C5255l.m20520s(C5057d.m19806t("%8s", binaryString), ' ', '0', false, 4, null);
        }
        f18403e = strArr;
        String[] strArr2 = f18402d;
        strArr2[0] = "";
        strArr2[1] = "END_STREAM";
        int[] iArr = {1};
        strArr2[8] = "PADDED";
        strArr2[1 | 8] = strArr2[1] + "|PADDED";
        strArr2[4] = "END_HEADERS";
        strArr2[32] = "PRIORITY";
        strArr2[36] = "END_HEADERS|PRIORITY";
        int[] iArr2 = {4, 32, 36};
        for (int i10 = 0; i10 < 3; i10++) {
            int i11 = iArr2[i10];
            int i12 = iArr[0];
            String[] strArr3 = f18402d;
            int i13 = i12 | i11;
            strArr3[i13] = strArr3[i12] + '|' + strArr3[i11];
            strArr3[i13 | 8] = strArr3[i12] + '|' + strArr3[i11] + "|PADDED";
        }
        int length = f18402d.length;
        for (int i14 = 0; i14 < length; i14++) {
            String[] strArr4 = f18402d;
            if (strArr4[i14] == null) {
                strArr4[i14] = f18403e[i14];
            }
        }
    }

    /* renamed from: a */
    public final String m21116a(int i9, int i10) {
        String str;
        if (i10 == 0) {
            return "";
        }
        if (i9 != 2 && i9 != 3) {
            if (i9 == 4 || i9 == 6) {
                return i10 == 1 ? "ACK" : f18403e[i10];
            }
            if (i9 != 7 && i9 != 8) {
                String[] strArr = f18402d;
                if (i10 < strArr.length) {
                    str = strArr[i10];
                    C0042f.m155b(str);
                } else {
                    str = f18403e[i10];
                }
                String str2 = str;
                return (i9 != 5 || (i10 & 4) == 0) ? (i9 != 0 || (i10 & 32) == 0) ? str2 : C5255l.m20521t(str2, "PRIORITY", "COMPRESSED", false, 4, null) : C5255l.m20521t(str2, "HEADERS", "PUSH_PROMISE", false, 4, null);
            }
        }
        return f18403e[i10];
    }

    /* renamed from: b */
    public final String m21117b(int i9) {
        String[] strArr = f18401c;
        return i9 < strArr.length ? strArr[i9] : C5057d.m19806t("0x%02x", Integer.valueOf(i9));
    }

    /* renamed from: c */
    public final String m21118c(boolean z8, int i9, int i10, int i11, int i12) {
        return C5057d.m19806t("%s 0x%08x %5d %-13s %s", z8 ? "<<" : ">>", Integer.valueOf(i9), Integer.valueOf(i10), m21117b(i11), m21116a(i11, i12));
    }
}
