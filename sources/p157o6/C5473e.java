package p157o6;

import com.google.common.base.Ascii;
import kotlin.collections.C5222e;
import net.sqlcipher.database.SQLiteDatabase;
import okio.ByteString;
import p007a6.C0042f;
import p098i6.C5057d;
import p204t6.InterfaceC6323d;
import p204t6.InterfaceC6324e;

/* renamed from: o6.e */
/* loaded from: classes.dex */
public final class C5473e {

    /* renamed from: a */
    public static final C5473e f18441a = new C5473e();

    /* renamed from: b */
    public static final int[] f18442b = {8184, 8388568, 268435426, 268435427, 268435428, 268435429, 268435430, 268435431, 268435432, 16777194, 1073741820, 268435433, 268435434, 1073741821, 268435435, 268435436, 268435437, 268435438, 268435439, 268435440, 268435441, 268435442, 1073741822, 268435443, 268435444, 268435445, 268435446, 268435447, 268435448, 268435449, 268435450, 268435451, 20, 1016, 1017, 4090, 8185, 21, 248, 2042, 1018, 1019, 249, 2043, SQLiteDatabase.MAX_SQL_CACHE_SIZE, 22, 23, 24, 0, 1, 2, 25, 26, 27, 28, 29, 30, 31, 92, 251, 32764, 32, 4091, 1020, 8186, 33, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 252, 115, 253, 8187, 524272, 8188, 16380, 34, 32765, 3, 35, 4, 36, 5, 37, 38, 39, 6, 116, 117, 40, 41, 42, 7, 43, 118, 44, 8, 9, 45, 119, 120, 121, 122, 123, 32766, 2044, 16381, 8189, 268435452, 1048550, 4194258, 1048551, 1048552, 4194259, 4194260, 4194261, 8388569, 4194262, 8388570, 8388571, 8388572, 8388573, 8388574, 16777195, 8388575, 16777196, 16777197, 4194263, 8388576, 16777198, 8388577, 8388578, 8388579, 8388580, 2097116, 4194264, 8388581, 4194265, 8388582, 8388583, 16777199, 4194266, 2097117, 1048553, 4194267, 4194268, 8388584, 8388585, 2097118, 8388586, 4194269, 4194270, 16777200, 2097119, 4194271, 8388587, 8388588, 2097120, 2097121, 4194272, 2097122, 8388589, 4194273, 8388590, 8388591, 1048554, 4194274, 4194275, 4194276, 8388592, 4194277, 4194278, 8388593, 67108832, 67108833, 1048555, 524273, 4194279, 8388594, 4194280, 33554412, 67108834, 67108835, 67108836, 134217694, 134217695, 67108837, 16777201, 33554413, 524274, 2097123, 67108838, 134217696, 134217697, 67108839, 134217698, 16777202, 2097124, 2097125, 67108840, 67108841, 268435453, 134217699, 134217700, 134217701, 1048556, 16777203, 1048557, 2097126, 4194281, 2097127, 2097128, 8388595, 4194282, 4194283, 33554414, 33554415, 16777204, 16777205, 67108842, 8388596, 67108843, 134217702, 67108844, 67108845, 134217703, 134217704, 134217705, 134217706, 134217707, 268435454, 134217708, 134217709, 134217710, 134217711, 134217712, 67108846};

    /* renamed from: c */
    public static final byte[] f18443c;

    /* renamed from: d */
    public static final a f18444d;

    static {
        byte[] bArr = {Ascii.f15380CR, Ascii.ETB, Ascii.f15383FS, Ascii.f15383FS, Ascii.f15383FS, Ascii.f15383FS, Ascii.f15383FS, Ascii.f15383FS, Ascii.f15383FS, Ascii.CAN, Ascii.f15388RS, Ascii.f15383FS, Ascii.f15383FS, Ascii.f15388RS, Ascii.f15383FS, Ascii.f15383FS, Ascii.f15383FS, Ascii.f15383FS, Ascii.f15383FS, Ascii.f15383FS, Ascii.f15383FS, Ascii.f15383FS, Ascii.f15388RS, Ascii.f15383FS, Ascii.f15383FS, Ascii.f15383FS, Ascii.f15383FS, Ascii.f15383FS, Ascii.f15383FS, Ascii.f15383FS, Ascii.f15383FS, Ascii.f15383FS, 6, 10, 10, Ascii.f15382FF, Ascii.f15380CR, 6, 8, Ascii.f15393VT, 10, 10, 8, Ascii.f15393VT, 8, 6, 6, 6, 5, 5, 5, 6, 6, 6, 6, 6, 6, 6, 7, 8, Ascii.f15389SI, 6, Ascii.f15382FF, 10, Ascii.f15380CR, 6, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 8, 7, 8, Ascii.f15380CR, 19, Ascii.f15380CR, Ascii.f15390SO, 6, Ascii.f15389SI, 5, 6, 5, 6, 5, 6, 6, 6, 5, 7, 7, 6, 6, 6, 5, 6, 7, 6, 5, 5, 6, 7, 7, 7, 7, 7, Ascii.f15389SI, Ascii.f15393VT, Ascii.f15390SO, Ascii.f15380CR, Ascii.f15383FS, Ascii.DC4, Ascii.SYN, Ascii.DC4, Ascii.DC4, Ascii.SYN, Ascii.SYN, Ascii.SYN, Ascii.ETB, Ascii.SYN, Ascii.ETB, Ascii.ETB, Ascii.ETB, Ascii.ETB, Ascii.ETB, Ascii.CAN, Ascii.ETB, Ascii.CAN, Ascii.CAN, Ascii.SYN, Ascii.ETB, Ascii.CAN, Ascii.ETB, Ascii.ETB, Ascii.ETB, Ascii.ETB, Ascii.NAK, Ascii.SYN, Ascii.ETB, Ascii.SYN, Ascii.ETB, Ascii.ETB, Ascii.CAN, Ascii.SYN, Ascii.NAK, Ascii.DC4, Ascii.SYN, Ascii.SYN, Ascii.ETB, Ascii.ETB, Ascii.NAK, Ascii.ETB, Ascii.SYN, Ascii.SYN, Ascii.CAN, Ascii.NAK, Ascii.SYN, Ascii.ETB, Ascii.ETB, Ascii.NAK, Ascii.NAK, Ascii.SYN, Ascii.NAK, Ascii.ETB, Ascii.SYN, Ascii.ETB, Ascii.ETB, Ascii.DC4, Ascii.SYN, Ascii.SYN, Ascii.SYN, Ascii.ETB, Ascii.SYN, Ascii.SYN, Ascii.ETB, Ascii.SUB, Ascii.SUB, Ascii.DC4, 19, Ascii.SYN, Ascii.ETB, Ascii.SYN, Ascii.f15381EM, Ascii.SUB, Ascii.SUB, Ascii.SUB, Ascii.ESC, Ascii.ESC, Ascii.SUB, Ascii.CAN, Ascii.f15381EM, 19, Ascii.NAK, Ascii.SUB, Ascii.ESC, Ascii.ESC, Ascii.SUB, Ascii.ESC, Ascii.CAN, Ascii.NAK, Ascii.NAK, Ascii.SUB, Ascii.SUB, Ascii.f15383FS, Ascii.ESC, Ascii.ESC, Ascii.ESC, Ascii.DC4, Ascii.CAN, Ascii.DC4, Ascii.NAK, Ascii.SYN, Ascii.NAK, Ascii.NAK, Ascii.ETB, Ascii.SYN, Ascii.SYN, Ascii.f15381EM, Ascii.f15381EM, Ascii.CAN, Ascii.CAN, Ascii.SUB, Ascii.ETB, Ascii.SUB, Ascii.ESC, Ascii.SUB, Ascii.SUB, Ascii.ESC, Ascii.ESC, Ascii.ESC, Ascii.ESC, Ascii.ESC, Ascii.f15383FS, Ascii.ESC, Ascii.ESC, Ascii.ESC, Ascii.ESC, Ascii.ESC, Ascii.SUB};
        f18443c = bArr;
        f18444d = new a();
        int length = bArr.length;
        for (int i9 = 0; i9 < length; i9++) {
            f18441a.m21166a(i9, f18442b[i9], f18443c[i9]);
        }
    }

    /* renamed from: a */
    public final void m21166a(int i9, int i10, int i11) {
        a aVar = new a(i9, i11);
        a aVar2 = f18444d;
        while (i11 > 8) {
            i11 -= 8;
            int i12 = (i10 >>> i11) & 255;
            a[] aVarArrM21170a = aVar2.m21170a();
            C0042f.m155b(aVarArrM21170a);
            a aVar3 = aVarArrM21170a[i12];
            if (aVar3 == null) {
                aVar3 = new a();
                aVarArrM21170a[i12] = aVar3;
            }
            aVar2 = aVar3;
        }
        int i13 = 8 - i11;
        int i14 = (i10 << i13) & 255;
        a[] aVarArrM21170a2 = aVar2.m21170a();
        C0042f.m155b(aVarArrM21170a2);
        C5222e.m20383f(aVarArrM21170a2, aVar, i14, (1 << i13) + i14);
    }

    /* renamed from: b */
    public final void m21167b(InterfaceC6324e interfaceC6324e, long j9, InterfaceC6323d interfaceC6323d) {
        C0042f.m158e(interfaceC6324e, "source");
        C0042f.m158e(interfaceC6323d, "sink");
        a aVar = f18444d;
        int iM19790d = 0;
        int iM21172c = 0;
        for (long j10 = 0; j10 < j9; j10++) {
            iM19790d = (iM19790d << 8) | C5057d.m19790d(interfaceC6324e.readByte(), 255);
            iM21172c += 8;
            while (iM21172c >= 8) {
                a[] aVarArrM21170a = aVar.m21170a();
                C0042f.m155b(aVarArrM21170a);
                aVar = aVarArrM21170a[(iM19790d >>> (iM21172c - 8)) & 255];
                C0042f.m155b(aVar);
                if (aVar.m21170a() == null) {
                    interfaceC6323d.writeByte(aVar.m21171b());
                    iM21172c -= aVar.m21172c();
                    aVar = f18444d;
                } else {
                    iM21172c -= 8;
                }
            }
        }
        while (iM21172c > 0) {
            a[] aVarArrM21170a2 = aVar.m21170a();
            C0042f.m155b(aVarArrM21170a2);
            a aVar2 = aVarArrM21170a2[(iM19790d << (8 - iM21172c)) & 255];
            C0042f.m155b(aVar2);
            if (aVar2.m21170a() != null || aVar2.m21172c() > iM21172c) {
                return;
            }
            interfaceC6323d.writeByte(aVar2.m21171b());
            iM21172c -= aVar2.m21172c();
            aVar = f18444d;
        }
    }

    /* renamed from: c */
    public final void m21168c(ByteString byteString, InterfaceC6323d interfaceC6323d) {
        C0042f.m158e(byteString, "source");
        C0042f.m158e(interfaceC6323d, "sink");
        int iM21892r = byteString.m21892r();
        long j9 = 0;
        int i9 = 0;
        for (int i10 = 0; i10 < iM21892r; i10++) {
            int iM19790d = C5057d.m19790d(byteString.m21878d(i10), 255);
            int i11 = f18442b[iM19790d];
            byte b9 = f18443c[iM19790d];
            j9 = (j9 << b9) | i11;
            i9 += b9;
            while (i9 >= 8) {
                i9 -= 8;
                interfaceC6323d.writeByte((int) (j9 >> i9));
            }
        }
        if (i9 > 0) {
            interfaceC6323d.writeByte((int) ((j9 << (8 - i9)) | (255 >>> i9)));
        }
    }

    /* renamed from: d */
    public final int m21169d(ByteString byteString) {
        C0042f.m158e(byteString, "bytes");
        long j9 = 0;
        for (int i9 = 0; i9 < byteString.m21892r(); i9++) {
            j9 += f18443c[C5057d.m19790d(byteString.m21878d(i9), 255)];
        }
        return (int) ((j9 + 7) >> 3);
    }

    /* renamed from: o6.e$a */
    public static final class a {

        /* renamed from: a */
        public final a[] f18445a;

        /* renamed from: b */
        public final int f18446b;

        /* renamed from: c */
        public final int f18447c;

        public a() {
            this.f18445a = new a[256];
            this.f18446b = 0;
            this.f18447c = 0;
        }

        /* renamed from: a */
        public final a[] m21170a() {
            return this.f18445a;
        }

        /* renamed from: b */
        public final int m21171b() {
            return this.f18446b;
        }

        /* renamed from: c */
        public final int m21172c() {
            return this.f18447c;
        }

        public a(int i9, int i10) {
            this.f18445a = null;
            this.f18446b = i9;
            int i11 = i10 & 7;
            this.f18447c = i11 == 0 ? 8 : i11;
        }
    }
}
