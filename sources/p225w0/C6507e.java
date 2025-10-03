package p225w0;

import android.graphics.Bitmap;
import android.util.Log;
import com.google.common.primitives.UnsignedBytes;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;
import java.util.Iterator;
import p225w0.InterfaceC6503a;

/* renamed from: w0.e */
/* loaded from: classes.dex */
public class C6507e implements InterfaceC6503a {

    /* renamed from: u */
    public static final String f21884u = "e";

    /* renamed from: a */
    public int[] f21885a;

    /* renamed from: b */
    public final int[] f21886b;

    /* renamed from: c */
    public final InterfaceC6503a.a f21887c;

    /* renamed from: d */
    public ByteBuffer f21888d;

    /* renamed from: e */
    public byte[] f21889e;

    /* renamed from: f */
    public short[] f21890f;

    /* renamed from: g */
    public byte[] f21891g;

    /* renamed from: h */
    public byte[] f21892h;

    /* renamed from: i */
    public byte[] f21893i;

    /* renamed from: j */
    public int[] f21894j;

    /* renamed from: k */
    public int f21895k;

    /* renamed from: l */
    public C6505c f21896l;

    /* renamed from: m */
    public Bitmap f21897m;

    /* renamed from: n */
    public boolean f21898n;

    /* renamed from: o */
    public int f21899o;

    /* renamed from: p */
    public int f21900p;

    /* renamed from: q */
    public int f21901q;

    /* renamed from: r */
    public int f21902r;

    /* renamed from: s */
    public Boolean f21903s;

    /* renamed from: t */
    public Bitmap.Config f21904t;

    public C6507e(InterfaceC6503a.a aVar, C6505c c6505c, ByteBuffer byteBuffer, int i9) {
        this(aVar);
        m24910o(c6505c, byteBuffer, i9);
    }

    @Override // p225w0.InterfaceC6503a
    /* renamed from: a */
    public int mo24874a() {
        return this.f21896l.f21869c;
    }

    @Override // p225w0.InterfaceC6503a
    public void advance() {
        this.f21895k = (this.f21895k + 1) % this.f21896l.f21869c;
    }

    @Override // p225w0.InterfaceC6503a
    /* renamed from: b */
    public int mo24875b() {
        int i9;
        if (this.f21896l.f21869c <= 0 || (i9 = this.f21895k) < 0) {
            return 0;
        }
        return m24906k(i9);
    }

    @Override // p225w0.InterfaceC6503a
    /* renamed from: c */
    public void mo24876c(Bitmap.Config config) {
        if (config == Bitmap.Config.ARGB_8888 || config == Bitmap.Config.RGB_565) {
            this.f21904t = config;
            return;
        }
        throw new IllegalArgumentException("Unsupported format: " + config + ", must be one of " + Bitmap.Config.ARGB_8888 + " or " + Bitmap.Config.RGB_565);
    }

    @Override // p225w0.InterfaceC6503a
    public void clear() {
        this.f21896l = null;
        byte[] bArr = this.f21893i;
        if (bArr != null) {
            this.f21887c.mo21006d(bArr);
        }
        int[] iArr = this.f21894j;
        if (iArr != null) {
            this.f21887c.mo21008f(iArr);
        }
        Bitmap bitmap = this.f21897m;
        if (bitmap != null) {
            this.f21887c.mo21005c(bitmap);
        }
        this.f21897m = null;
        this.f21888d = null;
        this.f21903s = null;
        byte[] bArr2 = this.f21889e;
        if (bArr2 != null) {
            this.f21887c.mo21006d(bArr2);
        }
    }

    @Override // p225w0.InterfaceC6503a
    /* renamed from: d */
    public void mo24877d() {
        this.f21895k = -1;
    }

    @Override // p225w0.InterfaceC6503a
    /* renamed from: e */
    public int mo24878e() {
        return this.f21895k;
    }

    @Override // p225w0.InterfaceC6503a
    /* renamed from: f */
    public int mo24879f() {
        return this.f21888d.limit() + this.f21893i.length + (this.f21894j.length * 4);
    }

    /* renamed from: g */
    public final int m24902g(int i9, int i10, int i11) {
        int i12 = 0;
        int i13 = 0;
        int i14 = 0;
        int i15 = 0;
        int i16 = 0;
        for (int i17 = i9; i17 < this.f21900p + i9; i17++) {
            byte[] bArr = this.f21893i;
            if (i17 >= bArr.length || i17 >= i10) {
                break;
            }
            int i18 = this.f21885a[bArr[i17] & UnsignedBytes.MAX_VALUE];
            if (i18 != 0) {
                i12 += (i18 >> 24) & 255;
                i13 += (i18 >> 16) & 255;
                i14 += (i18 >> 8) & 255;
                i15 += i18 & 255;
                i16++;
            }
        }
        int i19 = i9 + i11;
        for (int i20 = i19; i20 < this.f21900p + i19; i20++) {
            byte[] bArr2 = this.f21893i;
            if (i20 >= bArr2.length || i20 >= i10) {
                break;
            }
            int i21 = this.f21885a[bArr2[i20] & UnsignedBytes.MAX_VALUE];
            if (i21 != 0) {
                i12 += (i21 >> 24) & 255;
                i13 += (i21 >> 16) & 255;
                i14 += (i21 >> 8) & 255;
                i15 += i21 & 255;
                i16++;
            }
        }
        if (i16 == 0) {
            return 0;
        }
        return ((i12 / i16) << 24) | ((i13 / i16) << 16) | ((i14 / i16) << 8) | (i15 / i16);
    }

    @Override // p225w0.InterfaceC6503a
    public ByteBuffer getData() {
        return this.f21888d;
    }

    @Override // p225w0.InterfaceC6503a
    public synchronized Bitmap getNextFrame() {
        if (this.f21896l.f21869c <= 0 || this.f21895k < 0) {
            String str = f21884u;
            if (Log.isLoggable(str, 3)) {
                Log.d(str, "Unable to decode frame, frameCount=" + this.f21896l.f21869c + ", framePointer=" + this.f21895k);
            }
            this.f21899o = 1;
        }
        int i9 = this.f21899o;
        if (i9 != 1 && i9 != 2) {
            this.f21899o = 0;
            if (this.f21889e == null) {
                this.f21889e = this.f21887c.mo21007e(255);
            }
            C6504b c6504b = this.f21896l.f21871e.get(this.f21895k);
            int i10 = this.f21895k - 1;
            C6504b c6504b2 = i10 >= 0 ? this.f21896l.f21871e.get(i10) : null;
            int[] iArr = c6504b.f21866k;
            if (iArr == null) {
                iArr = this.f21896l.f21867a;
            }
            this.f21885a = iArr;
            if (iArr != null) {
                if (c6504b.f21861f) {
                    System.arraycopy(iArr, 0, this.f21886b, 0, iArr.length);
                    int[] iArr2 = this.f21886b;
                    this.f21885a = iArr2;
                    iArr2[c6504b.f21863h] = 0;
                }
                return m24911p(c6504b, c6504b2);
            }
            String str2 = f21884u;
            if (Log.isLoggable(str2, 3)) {
                Log.d(str2, "No valid color table found for frame #" + this.f21895k);
            }
            this.f21899o = 1;
            return null;
        }
        String str3 = f21884u;
        if (Log.isLoggable(str3, 3)) {
            Log.d(str3, "Unable to decode frame, status=" + this.f21899o);
        }
        return null;
    }

    /* renamed from: h */
    public final void m24903h(C6504b c6504b) {
        int i9;
        int i10;
        int i11;
        int i12;
        int i13;
        int[] iArr = this.f21894j;
        int i14 = c6504b.f21859d;
        int i15 = this.f21900p;
        int i16 = i14 / i15;
        int i17 = c6504b.f21857b / i15;
        int i18 = c6504b.f21858c / i15;
        int i19 = c6504b.f21856a / i15;
        boolean z8 = this.f21895k == 0;
        int i20 = this.f21902r;
        int i21 = this.f21901q;
        byte[] bArr = this.f21893i;
        int[] iArr2 = this.f21885a;
        Boolean bool = this.f21903s;
        int i22 = 8;
        int i23 = 0;
        int i24 = 0;
        int i25 = 1;
        while (i24 < i16) {
            Boolean bool2 = bool;
            if (c6504b.f21860e) {
                if (i23 >= i16) {
                    int i26 = i25 + 1;
                    i9 = i16;
                    if (i26 == 2) {
                        i23 = 4;
                    } else if (i26 == 3) {
                        i22 = 4;
                        i25 = i26;
                        i23 = 2;
                    } else if (i26 == 4) {
                        i25 = i26;
                        i23 = 1;
                        i22 = 2;
                    }
                    i25 = i26;
                } else {
                    i9 = i16;
                }
                i10 = i23 + i22;
            } else {
                i9 = i16;
                i10 = i23;
                i23 = i24;
            }
            int i27 = i23 + i17;
            boolean z9 = i15 == 1;
            if (i27 < i21) {
                int i28 = i27 * i20;
                int i29 = i28 + i19;
                int i30 = i29 + i18;
                int i31 = i28 + i20;
                if (i31 < i30) {
                    i30 = i31;
                }
                i11 = i10;
                int i32 = i24 * i15 * c6504b.f21858c;
                if (z9) {
                    int i33 = i29;
                    while (i33 < i30) {
                        int i34 = i17;
                        int i35 = iArr2[bArr[i32] & UnsignedBytes.MAX_VALUE];
                        if (i35 != 0) {
                            iArr[i33] = i35;
                        } else if (z8 && bool2 == null) {
                            bool2 = Boolean.TRUE;
                        }
                        i32 += i15;
                        i33++;
                        i17 = i34;
                    }
                } else {
                    i13 = i17;
                    int i36 = ((i30 - i29) * i15) + i32;
                    int i37 = i29;
                    while (true) {
                        i12 = i18;
                        if (i37 < i30) {
                            int iM24902g = m24902g(i32, i36, c6504b.f21858c);
                            if (iM24902g != 0) {
                                iArr[i37] = iM24902g;
                            } else if (z8 && bool2 == null) {
                                bool2 = Boolean.TRUE;
                            }
                            i32 += i15;
                            i37++;
                            i18 = i12;
                        }
                    }
                    bool = bool2;
                    i24++;
                    i17 = i13;
                    i16 = i9;
                    i18 = i12;
                    i23 = i11;
                }
            } else {
                i11 = i10;
            }
            i13 = i17;
            i12 = i18;
            bool = bool2;
            i24++;
            i17 = i13;
            i16 = i9;
            i18 = i12;
            i23 = i11;
        }
        Boolean bool3 = bool;
        if (this.f21903s == null) {
            this.f21903s = Boolean.valueOf(bool3 == null ? false : bool3.booleanValue());
        }
    }

    /* renamed from: i */
    public final void m24904i(C6504b c6504b) {
        C6504b c6504b2 = c6504b;
        int[] iArr = this.f21894j;
        int i9 = c6504b2.f21859d;
        int i10 = c6504b2.f21857b;
        int i11 = c6504b2.f21858c;
        int i12 = c6504b2.f21856a;
        boolean z8 = this.f21895k == 0;
        int i13 = this.f21902r;
        byte[] bArr = this.f21893i;
        int[] iArr2 = this.f21885a;
        int i14 = 0;
        byte b9 = -1;
        while (i14 < i9) {
            int i15 = (i14 + i10) * i13;
            int i16 = i15 + i12;
            int i17 = i16 + i11;
            int i18 = i15 + i13;
            if (i18 < i17) {
                i17 = i18;
            }
            int i19 = c6504b2.f21858c * i14;
            int i20 = i16;
            while (i20 < i17) {
                byte b10 = bArr[i19];
                int i21 = i9;
                int i22 = b10 & UnsignedBytes.MAX_VALUE;
                if (i22 != b9) {
                    int i23 = iArr2[i22];
                    if (i23 != 0) {
                        iArr[i20] = i23;
                    } else {
                        b9 = b10;
                    }
                }
                i19++;
                i20++;
                i9 = i21;
            }
            i14++;
            c6504b2 = c6504b;
        }
        this.f21903s = Boolean.valueOf(this.f21903s == null && z8 && b9 != -1);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r7v10 */
    /* JADX WARN: Type inference failed for: r7v11 */
    /* JADX WARN: Type inference failed for: r7v12 */
    /* JADX WARN: Type inference failed for: r7v15, types: [short] */
    /* JADX WARN: Type inference failed for: r7v17 */
    /* renamed from: j */
    public final void m24905j(C6504b c6504b) {
        int i9;
        int i10;
        short s8;
        C6507e c6507e = this;
        if (c6504b != null) {
            c6507e.f21888d.position(c6504b.f21865j);
        }
        if (c6504b == null) {
            C6505c c6505c = c6507e.f21896l;
            i9 = c6505c.f21872f;
            i10 = c6505c.f21873g;
        } else {
            i9 = c6504b.f21858c;
            i10 = c6504b.f21859d;
        }
        int i11 = i9 * i10;
        byte[] bArr = c6507e.f21893i;
        if (bArr == null || bArr.length < i11) {
            c6507e.f21893i = c6507e.f21887c.mo21007e(i11);
        }
        byte[] bArr2 = c6507e.f21893i;
        if (c6507e.f21890f == null) {
            c6507e.f21890f = new short[4096];
        }
        short[] sArr = c6507e.f21890f;
        if (c6507e.f21891g == null) {
            c6507e.f21891g = new byte[4096];
        }
        byte[] bArr3 = c6507e.f21891g;
        if (c6507e.f21892h == null) {
            c6507e.f21892h = new byte[4097];
        }
        byte[] bArr4 = c6507e.f21892h;
        int iM24909n = m24909n();
        int i12 = 1 << iM24909n;
        int i13 = i12 + 1;
        int i14 = i12 + 2;
        int i15 = iM24909n + 1;
        int i16 = (1 << i15) - 1;
        int i17 = 0;
        for (int i18 = 0; i18 < i12; i18++) {
            sArr[i18] = 0;
            bArr3[i18] = (byte) i18;
        }
        byte[] bArr5 = c6507e.f21889e;
        int i19 = i15;
        int i20 = i14;
        int i21 = i16;
        int iM24908m = 0;
        int i22 = 0;
        int i23 = 0;
        int i24 = 0;
        int i25 = 0;
        int i26 = 0;
        int i27 = 0;
        int i28 = -1;
        while (true) {
            if (i17 >= i11) {
                break;
            }
            if (iM24908m == 0) {
                iM24908m = m24908m();
                if (iM24908m <= 0) {
                    c6507e.f21899o = 3;
                    break;
                }
                i22 = 0;
            }
            i24 += (bArr5[i22] & UnsignedBytes.MAX_VALUE) << i23;
            i22++;
            iM24908m--;
            int i29 = i23 + 8;
            int i30 = i20;
            int i31 = i19;
            int i32 = i28;
            int i33 = i15;
            int i34 = i26;
            while (true) {
                if (i29 < i31) {
                    i28 = i32;
                    i20 = i30;
                    i23 = i29;
                    c6507e = this;
                    i26 = i34;
                    i15 = i33;
                    i19 = i31;
                    break;
                }
                int i35 = i14;
                int i36 = i24 & i21;
                i24 >>= i31;
                i29 -= i31;
                if (i36 == i12) {
                    i21 = i16;
                    i31 = i33;
                    i30 = i35;
                    i14 = i30;
                    i32 = -1;
                } else {
                    if (i36 == i13) {
                        i23 = i29;
                        i26 = i34;
                        i20 = i30;
                        i15 = i33;
                        i14 = i35;
                        i28 = i32;
                        i19 = i31;
                        c6507e = this;
                        break;
                    }
                    if (i32 == -1) {
                        bArr2[i25] = bArr3[i36];
                        i25++;
                        i17++;
                        i32 = i36;
                        i34 = i32;
                        i14 = i35;
                        i29 = i29;
                    } else {
                        if (i36 >= i30) {
                            bArr4[i27] = (byte) i34;
                            i27++;
                            s8 = i32;
                        } else {
                            s8 = i36;
                        }
                        while (s8 >= i12) {
                            bArr4[i27] = bArr3[s8];
                            i27++;
                            s8 = sArr[s8];
                        }
                        i34 = bArr3[s8] & UnsignedBytes.MAX_VALUE;
                        byte b9 = (byte) i34;
                        bArr2[i25] = b9;
                        while (true) {
                            i25++;
                            i17++;
                            if (i27 <= 0) {
                                break;
                            }
                            i27--;
                            bArr2[i25] = bArr4[i27];
                        }
                        byte[] bArr6 = bArr4;
                        if (i30 < 4096) {
                            sArr[i30] = (short) i32;
                            bArr3[i30] = b9;
                            i30++;
                            if ((i30 & i21) == 0 && i30 < 4096) {
                                i31++;
                                i21 += i30;
                            }
                        }
                        i32 = i36;
                        i14 = i35;
                        i29 = i29;
                        bArr4 = bArr6;
                    }
                }
            }
        }
        Arrays.fill(bArr2, i25, i11, (byte) 0);
    }

    /* renamed from: k */
    public int m24906k(int i9) {
        if (i9 >= 0) {
            C6505c c6505c = this.f21896l;
            if (i9 < c6505c.f21869c) {
                return c6505c.f21871e.get(i9).f21864i;
            }
        }
        return -1;
    }

    /* renamed from: l */
    public final Bitmap m24907l() {
        Boolean bool = this.f21903s;
        Bitmap bitmapMo21003a = this.f21887c.mo21003a(this.f21902r, this.f21901q, (bool == null || bool.booleanValue()) ? Bitmap.Config.ARGB_8888 : this.f21904t);
        bitmapMo21003a.setHasAlpha(true);
        return bitmapMo21003a;
    }

    /* renamed from: m */
    public final int m24908m() {
        int iM24909n = m24909n();
        if (iM24909n <= 0) {
            return iM24909n;
        }
        ByteBuffer byteBuffer = this.f21888d;
        byteBuffer.get(this.f21889e, 0, Math.min(iM24909n, byteBuffer.remaining()));
        return iM24909n;
    }

    /* renamed from: n */
    public final int m24909n() {
        return this.f21888d.get() & UnsignedBytes.MAX_VALUE;
    }

    /* renamed from: o */
    public synchronized void m24910o(C6505c c6505c, ByteBuffer byteBuffer, int i9) {
        if (i9 <= 0) {
            throw new IllegalArgumentException("Sample size must be >=0, not: " + i9);
        }
        int iHighestOneBit = Integer.highestOneBit(i9);
        this.f21899o = 0;
        this.f21896l = c6505c;
        this.f21895k = -1;
        ByteBuffer byteBufferAsReadOnlyBuffer = byteBuffer.asReadOnlyBuffer();
        this.f21888d = byteBufferAsReadOnlyBuffer;
        byteBufferAsReadOnlyBuffer.position(0);
        this.f21888d.order(ByteOrder.LITTLE_ENDIAN);
        this.f21898n = false;
        Iterator<C6504b> it = c6505c.f21871e.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            } else if (it.next().f21862g == 3) {
                this.f21898n = true;
                break;
            }
        }
        this.f21900p = iHighestOneBit;
        int i10 = c6505c.f21872f;
        this.f21902r = i10 / iHighestOneBit;
        int i11 = c6505c.f21873g;
        this.f21901q = i11 / iHighestOneBit;
        this.f21893i = this.f21887c.mo21007e(i10 * i11);
        this.f21894j = this.f21887c.mo21004b(this.f21902r * this.f21901q);
    }

    /* renamed from: p */
    public final Bitmap m24911p(C6504b c6504b, C6504b c6504b2) {
        int i9;
        int i10;
        Bitmap bitmap;
        int[] iArr = this.f21894j;
        int i11 = 0;
        if (c6504b2 == null) {
            Bitmap bitmap2 = this.f21897m;
            if (bitmap2 != null) {
                this.f21887c.mo21005c(bitmap2);
            }
            this.f21897m = null;
            Arrays.fill(iArr, 0);
        }
        if (c6504b2 != null && c6504b2.f21862g == 3 && this.f21897m == null) {
            Arrays.fill(iArr, 0);
        }
        if (c6504b2 != null && (i10 = c6504b2.f21862g) > 0) {
            if (i10 == 2) {
                if (!c6504b.f21861f) {
                    C6505c c6505c = this.f21896l;
                    int i12 = c6505c.f21878l;
                    if (c6504b.f21866k == null || c6505c.f21876j != c6504b.f21863h) {
                        i11 = i12;
                    }
                } else if (this.f21895k == 0) {
                    this.f21903s = Boolean.TRUE;
                }
                int i13 = c6504b2.f21859d;
                int i14 = this.f21900p;
                int i15 = i13 / i14;
                int i16 = c6504b2.f21857b / i14;
                int i17 = c6504b2.f21858c / i14;
                int i18 = c6504b2.f21856a / i14;
                int i19 = this.f21902r;
                int i20 = (i16 * i19) + i18;
                int i21 = (i15 * i19) + i20;
                while (i20 < i21) {
                    int i22 = i20 + i17;
                    for (int i23 = i20; i23 < i22; i23++) {
                        iArr[i23] = i11;
                    }
                    i20 += this.f21902r;
                }
            } else if (i10 == 3 && (bitmap = this.f21897m) != null) {
                int i24 = this.f21902r;
                bitmap.getPixels(iArr, 0, i24, 0, 0, i24, this.f21901q);
            }
        }
        m24905j(c6504b);
        if (c6504b.f21860e || this.f21900p != 1) {
            m24903h(c6504b);
        } else {
            m24904i(c6504b);
        }
        if (this.f21898n && ((i9 = c6504b.f21862g) == 0 || i9 == 1)) {
            if (this.f21897m == null) {
                this.f21897m = m24907l();
            }
            Bitmap bitmap3 = this.f21897m;
            int i25 = this.f21902r;
            bitmap3.setPixels(iArr, 0, i25, 0, 0, i25, this.f21901q);
        }
        Bitmap bitmapM24907l = m24907l();
        int i26 = this.f21902r;
        bitmapM24907l.setPixels(iArr, 0, i26, 0, 0, i26, this.f21901q);
        return bitmapM24907l;
    }

    public C6507e(InterfaceC6503a.a aVar) {
        this.f21886b = new int[256];
        this.f21904t = Bitmap.Config.ARGB_8888;
        this.f21887c = aVar;
        this.f21896l = new C6505c();
    }
}
