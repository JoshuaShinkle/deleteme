package org.jivesoftware.smack.util;

import com.google.common.base.Ascii;
import com.google.common.primitives.UnsignedBytes;
import java.io.ByteArrayOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.GZIPOutputStream;

/* renamed from: org.jivesoftware.smack.util.b */
/* loaded from: classes.dex */
public class C5608b {

    /* renamed from: a */
    public static final Logger f19470a = Logger.getLogger(C5608b.class.getName());

    /* renamed from: b */
    public static final byte[] f19471b = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 43, 47};

    /* renamed from: c */
    public static final byte[] f19472c = {-9, -9, -9, -9, -9, -9, -9, -9, -9, -5, -5, -9, -9, -5, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -5, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, 62, -9, -9, -9, 63, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -9, -9, -9, -1, -9, -9, -9, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, Ascii.f15393VT, Ascii.f15382FF, Ascii.f15380CR, Ascii.f15390SO, Ascii.f15389SI, Ascii.DLE, 17, Ascii.DC2, 19, Ascii.DC4, Ascii.NAK, Ascii.SYN, Ascii.ETB, Ascii.CAN, Ascii.f15381EM, -9, -9, -9, -9, -9, -9, Ascii.SUB, Ascii.ESC, Ascii.f15383FS, Ascii.f15384GS, Ascii.f15388RS, Ascii.f15392US, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -9, -9, -9, -9};

    /* renamed from: d */
    public static final byte[] f19473d = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 45, 95};

    /* renamed from: e */
    public static final byte[] f19474e = {-9, -9, -9, -9, -9, -9, -9, -9, -9, -5, -5, -9, -9, -5, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -5, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, 62, -9, -9, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -9, -9, -9, -1, -9, -9, -9, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, Ascii.f15393VT, Ascii.f15382FF, Ascii.f15380CR, Ascii.f15390SO, Ascii.f15389SI, Ascii.DLE, 17, Ascii.DC2, 19, Ascii.DC4, Ascii.NAK, Ascii.SYN, Ascii.ETB, Ascii.CAN, Ascii.f15381EM, -9, -9, -9, -9, 63, -9, Ascii.SUB, Ascii.ESC, Ascii.f15383FS, Ascii.f15384GS, Ascii.f15388RS, Ascii.f15392US, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -9, -9, -9, -9};

    /* renamed from: f */
    public static final byte[] f19475f = {45, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 95, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122};

    /* renamed from: g */
    public static final byte[] f19476g = {-9, -9, -9, -9, -9, -9, -9, -9, -9, -5, -5, -9, -9, -5, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -5, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, 0, -9, -9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, -9, -9, -9, -1, -9, -9, -9, Ascii.f15393VT, Ascii.f15382FF, Ascii.f15380CR, Ascii.f15390SO, Ascii.f15389SI, Ascii.DLE, 17, Ascii.DC2, 19, Ascii.DC4, Ascii.NAK, Ascii.SYN, Ascii.ETB, Ascii.CAN, Ascii.f15381EM, Ascii.SUB, Ascii.ESC, Ascii.f15383FS, Ascii.f15384GS, Ascii.f15388RS, Ascii.f15392US, 32, 33, 34, 35, 36, -9, -9, -9, -9, 37, -9, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, -9, -9, -9, -9};

    /* renamed from: d */
    public static byte[] m22299d(byte[] bArr, int i9, int i10, int i11) {
        byte[] bArrM22305j = m22305j(i11);
        byte[] bArr2 = new byte[(i10 * 3) / 4];
        byte[] bArr3 = new byte[4];
        int i12 = 0;
        int iM22300e = 0;
        for (int i13 = i9; i13 < i9 + i10; i13++) {
            byte b9 = (byte) (bArr[i13] & Ascii.DEL);
            byte b10 = bArrM22305j[b9];
            if (b10 < -5) {
                f19470a.warning("Bad Base64 input character at " + i13 + ": " + ((int) bArr[i13]) + "(decimal)");
                return null;
            }
            if (b10 >= -1) {
                int i14 = i12 + 1;
                bArr3[i12] = b9;
                if (i14 > 3) {
                    iM22300e += m22300e(bArr3, 0, bArr2, iM22300e, i11);
                    if (b9 == 61) {
                        break;
                    }
                    i12 = 0;
                } else {
                    i12 = i14;
                }
            }
        }
        byte[] bArr4 = new byte[iM22300e];
        System.arraycopy(bArr2, 0, bArr4, 0, iM22300e);
        return bArr4;
    }

    /* renamed from: e */
    public static int m22300e(byte[] bArr, int i9, byte[] bArr2, int i10, int i11) {
        byte[] bArrM22305j = m22305j(i11);
        int i12 = i9 + 2;
        byte b9 = bArr[i12];
        if (b9 == 61) {
            bArr2[i10] = (byte) ((((bArrM22305j[bArr[i9 + 1]] & UnsignedBytes.MAX_VALUE) << 12) | ((bArrM22305j[bArr[i9]] & UnsignedBytes.MAX_VALUE) << 18)) >>> 16);
            return 1;
        }
        int i13 = i9 + 3;
        byte b10 = bArr[i13];
        if (b10 == 61) {
            int i14 = ((bArrM22305j[bArr[i9 + 1]] & UnsignedBytes.MAX_VALUE) << 12) | ((bArrM22305j[bArr[i9]] & UnsignedBytes.MAX_VALUE) << 18) | ((bArrM22305j[b9] & UnsignedBytes.MAX_VALUE) << 6);
            bArr2[i10] = (byte) (i14 >>> 16);
            bArr2[i10 + 1] = (byte) (i14 >>> 8);
            return 2;
        }
        try {
            int i15 = ((bArrM22305j[b9] & UnsignedBytes.MAX_VALUE) << 6) | ((bArrM22305j[bArr[i9]] & UnsignedBytes.MAX_VALUE) << 18) | ((bArrM22305j[bArr[i9 + 1]] & UnsignedBytes.MAX_VALUE) << 12) | (bArrM22305j[b10] & UnsignedBytes.MAX_VALUE);
            bArr2[i10] = (byte) (i15 >> 16);
            bArr2[i10 + 1] = (byte) (i15 >> 8);
            bArr2[i10 + 2] = (byte) i15;
            return 3;
        } catch (Exception e9) {
            Logger logger = f19470a;
            logger.log(Level.SEVERE, e9.getMessage(), (Throwable) e9);
            logger.severe("" + ((int) bArr[i9]) + ": " + ((int) bArrM22305j[bArr[i9]]));
            StringBuilder sb = new StringBuilder();
            sb.append("");
            int i16 = i9 + 1;
            sb.append((int) bArr[i16]);
            sb.append(": ");
            sb.append((int) bArrM22305j[bArr[i16]]);
            logger.severe(sb.toString());
            logger.severe("" + ((int) bArr[i12]) + ": " + ((int) bArrM22305j[bArr[i12]]));
            logger.severe("" + ((int) bArr[i13]) + ": " + ((int) bArrM22305j[bArr[i13]]));
            return -1;
        }
    }

    /* renamed from: f */
    public static byte[] m22301f(byte[] bArr, int i9, int i10, byte[] bArr2, int i11, int i12) {
        byte[] bArrM22304i = m22304i(i12);
        int i13 = (i10 > 0 ? (bArr[i9] << Ascii.CAN) >>> 8 : 0) | (i10 > 1 ? (bArr[i9 + 1] << Ascii.CAN) >>> 16 : 0) | (i10 > 2 ? (bArr[i9 + 2] << Ascii.CAN) >>> 24 : 0);
        if (i10 == 1) {
            bArr2[i11] = bArrM22304i[i13 >>> 18];
            bArr2[i11 + 1] = bArrM22304i[(i13 >>> 12) & 63];
            bArr2[i11 + 2] = 61;
            bArr2[i11 + 3] = 61;
            return bArr2;
        }
        if (i10 == 2) {
            bArr2[i11] = bArrM22304i[i13 >>> 18];
            bArr2[i11 + 1] = bArrM22304i[(i13 >>> 12) & 63];
            bArr2[i11 + 2] = bArrM22304i[(i13 >>> 6) & 63];
            bArr2[i11 + 3] = 61;
            return bArr2;
        }
        if (i10 != 3) {
            return bArr2;
        }
        bArr2[i11] = bArrM22304i[i13 >>> 18];
        bArr2[i11 + 1] = bArrM22304i[(i13 >>> 12) & 63];
        bArr2[i11 + 2] = bArrM22304i[(i13 >>> 6) & 63];
        bArr2[i11 + 3] = bArrM22304i[i13 & 63];
        return bArr2;
    }

    /* renamed from: g */
    public static byte[] m22302g(byte[] bArr, byte[] bArr2, int i9, int i10) {
        m22301f(bArr2, 0, i9, bArr, 0, i10);
        return bArr;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:111:0x0065 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:92:0x006a A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:98:0x006f A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r3v0 */
    /* JADX WARN: Type inference failed for: r3v11 */
    /* JADX WARN: Type inference failed for: r3v12 */
    /* JADX WARN: Type inference failed for: r3v13 */
    /* JADX WARN: Type inference failed for: r3v14, types: [java.io.ByteArrayOutputStream] */
    /* JADX WARN: Type inference failed for: r3v15, types: [java.io.ByteArrayOutputStream] */
    /* JADX WARN: Type inference failed for: r3v16, types: [java.io.ByteArrayOutputStream, java.io.OutputStream] */
    /* JADX WARN: Type inference failed for: r3v17 */
    /* JADX WARN: Type inference failed for: r5v0 */
    /* JADX WARN: Type inference failed for: r5v3 */
    /* JADX WARN: Type inference failed for: r5v4 */
    /* JADX WARN: Type inference failed for: r5v5, types: [org.jivesoftware.smack.util.b$a] */
    /* JADX WARN: Type inference failed for: r5v6, types: [org.jivesoftware.smack.util.b$a] */
    /* JADX WARN: Type inference failed for: r5v7 */
    /* JADX WARN: Type inference failed for: r5v8, types: [java.io.OutputStream, org.jivesoftware.smack.util.b$a] */
    /* renamed from: h */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static String m22303h(byte[] bArr, int i9, int i10, int i11) throws Throwable {
        int i12;
        GZIPOutputStream gZIPOutputStream;
        int i13 = i11 & 8;
        ?? byteArrayOutputStream = i11 & 2;
        ?? aVar = 2;
        if (byteArrayOutputStream != 2) {
            boolean z8 = i13 == 0;
            int i14 = (i10 * 4) / 3;
            byte[] bArr2 = new byte[(i10 % 3 > 0 ? 4 : 0) + i14 + (z8 ? i14 / 76 : 0)];
            int i15 = i10 - 2;
            int i16 = 0;
            int i17 = 0;
            int i18 = 0;
            while (i16 < i15) {
                int i19 = i16;
                m22301f(bArr, i16 + i9, 3, bArr2, i17, i11);
                int i20 = i18 + 4;
                if (z8 && i20 == 76) {
                    bArr2[i17 + 4] = 10;
                    i17++;
                    i18 = 0;
                } else {
                    i18 = i20;
                }
                i16 = i19 + 3;
                i17 += 4;
            }
            int i21 = i16;
            if (i21 < i10) {
                m22301f(bArr, i21 + i9, i10 - i21, bArr2, i17, i11);
                i17 += 4;
            }
            int i22 = i17;
            try {
                i12 = 0;
            } catch (UnsupportedEncodingException unused) {
                i12 = 0;
            }
            try {
                return new String(bArr2, 0, i22, "UTF-8");
            } catch (UnsupportedEncodingException unused2) {
                return new String(bArr2, i12, i22);
            }
        }
        OutputStream outputStream = null;
        try {
            try {
                byteArrayOutputStream = new ByteArrayOutputStream();
            } catch (Throwable th) {
                th = th;
                outputStream = 1;
            }
        } catch (IOException e9) {
            e = e9;
            byteArrayOutputStream = 0;
            gZIPOutputStream = null;
        } catch (Throwable th2) {
            th = th2;
            byteArrayOutputStream = 0;
            aVar = 0;
        }
        try {
            aVar = new a(byteArrayOutputStream, i11 | 1);
            try {
                gZIPOutputStream = new GZIPOutputStream(aVar);
                try {
                    gZIPOutputStream.write(bArr, i9, i10);
                    gZIPOutputStream.close();
                    try {
                        gZIPOutputStream.close();
                    } catch (Exception unused3) {
                    }
                    try {
                        aVar.close();
                    } catch (Exception unused4) {
                    }
                    try {
                        byteArrayOutputStream.close();
                    } catch (Exception unused5) {
                    }
                    try {
                        return new String(byteArrayOutputStream.toByteArray(), "UTF-8");
                    } catch (UnsupportedEncodingException unused6) {
                        return new String(byteArrayOutputStream.toByteArray());
                    }
                } catch (IOException e10) {
                    e = e10;
                    f19470a.log(Level.SEVERE, "Error encoding bytes", (Throwable) e);
                    if (gZIPOutputStream != null) {
                        try {
                            gZIPOutputStream.close();
                        } catch (Exception unused7) {
                        }
                    }
                    if (aVar != 0) {
                        try {
                            aVar.close();
                        } catch (Exception unused8) {
                        }
                    }
                    if (byteArrayOutputStream != 0) {
                        try {
                            byteArrayOutputStream.close();
                        } catch (Exception unused9) {
                        }
                    }
                    return null;
                }
            } catch (IOException e11) {
                e = e11;
                gZIPOutputStream = null;
            } catch (Throwable th3) {
                th = th3;
                if (outputStream != null) {
                    try {
                        outputStream.close();
                    } catch (Exception unused10) {
                    }
                }
                if (aVar != 0) {
                    try {
                        aVar.close();
                    } catch (Exception unused11) {
                    }
                }
                if (byteArrayOutputStream == 0) {
                    throw th;
                }
                try {
                    byteArrayOutputStream.close();
                    throw th;
                } catch (Exception unused12) {
                    throw th;
                }
            }
        } catch (IOException e12) {
            e = e12;
            gZIPOutputStream = null;
            byteArrayOutputStream = byteArrayOutputStream;
            aVar = gZIPOutputStream;
            f19470a.log(Level.SEVERE, "Error encoding bytes", (Throwable) e);
            if (gZIPOutputStream != null) {
            }
            if (aVar != 0) {
            }
            if (byteArrayOutputStream != 0) {
            }
            return null;
        } catch (Throwable th4) {
            th = th4;
            aVar = 0;
        }
    }

    /* renamed from: i */
    public static final byte[] m22304i(int i9) {
        return (i9 & 16) == 16 ? f19473d : (i9 & 32) == 32 ? f19475f : f19471b;
    }

    /* renamed from: j */
    public static final byte[] m22305j(int i9) {
        return (i9 & 16) == 16 ? f19474e : (i9 & 32) == 32 ? f19476g : f19472c;
    }

    /* renamed from: org.jivesoftware.smack.util.b$a */
    public static class a extends FilterOutputStream {

        /* renamed from: b */
        public boolean f19477b;

        /* renamed from: c */
        public int f19478c;

        /* renamed from: d */
        public byte[] f19479d;

        /* renamed from: e */
        public int f19480e;

        /* renamed from: f */
        public int f19481f;

        /* renamed from: g */
        public boolean f19482g;

        /* renamed from: h */
        public byte[] f19483h;

        /* renamed from: i */
        public boolean f19484i;

        /* renamed from: j */
        public int f19485j;

        /* renamed from: k */
        public byte[] f19486k;

        public a(OutputStream outputStream, int i9) {
            super(outputStream);
            this.f19482g = (i9 & 8) != 8;
            boolean z8 = (i9 & 1) == 1;
            this.f19477b = z8;
            int i10 = z8 ? 3 : 4;
            this.f19480e = i10;
            this.f19479d = new byte[i10];
            this.f19478c = 0;
            this.f19481f = 0;
            this.f19484i = false;
            this.f19483h = new byte[4];
            this.f19485j = i9;
            this.f19486k = C5608b.m22305j(i9);
        }

        @Override // java.io.FilterOutputStream, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            m22306f();
            super.close();
            this.f19479d = null;
            ((FilterOutputStream) this).out = null;
        }

        /* renamed from: f */
        public void m22306f() throws IOException {
            int i9 = this.f19478c;
            if (i9 > 0) {
                if (!this.f19477b) {
                    throw new IOException("Base64 input not properly padded.");
                }
                ((FilterOutputStream) this).out.write(C5608b.m22302g(this.f19483h, this.f19479d, i9, this.f19485j));
                this.f19478c = 0;
            }
        }

        @Override // java.io.FilterOutputStream, java.io.OutputStream
        public void write(int i9) throws IOException {
            if (this.f19484i) {
                ((FilterOutputStream) this).out.write(i9);
                return;
            }
            if (!this.f19477b) {
                byte b9 = this.f19486k[i9 & 127];
                if (b9 <= -5) {
                    if (b9 != -5) {
                        throw new IOException("Invalid character in Base64 data.");
                    }
                    return;
                }
                byte[] bArr = this.f19479d;
                int i10 = this.f19478c;
                int i11 = i10 + 1;
                this.f19478c = i11;
                bArr[i10] = (byte) i9;
                if (i11 >= this.f19480e) {
                    ((FilterOutputStream) this).out.write(this.f19483h, 0, C5608b.m22300e(bArr, 0, this.f19483h, 0, this.f19485j));
                    this.f19478c = 0;
                    return;
                }
                return;
            }
            byte[] bArr2 = this.f19479d;
            int i12 = this.f19478c;
            int i13 = i12 + 1;
            this.f19478c = i13;
            bArr2[i12] = (byte) i9;
            int i14 = this.f19480e;
            if (i13 >= i14) {
                ((FilterOutputStream) this).out.write(C5608b.m22302g(this.f19483h, bArr2, i14, this.f19485j));
                int i15 = this.f19481f + 4;
                this.f19481f = i15;
                if (this.f19482g && i15 >= 76) {
                    ((FilterOutputStream) this).out.write(10);
                    this.f19481f = 0;
                }
                this.f19478c = 0;
            }
        }

        @Override // java.io.FilterOutputStream, java.io.OutputStream
        public void write(byte[] bArr, int i9, int i10) throws IOException {
            if (this.f19484i) {
                ((FilterOutputStream) this).out.write(bArr, i9, i10);
                return;
            }
            for (int i11 = 0; i11 < i10; i11++) {
                write(bArr[i9 + i11]);
            }
        }
    }
}
