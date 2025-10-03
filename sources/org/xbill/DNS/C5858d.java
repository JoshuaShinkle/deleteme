package org.xbill.DNS;

/* renamed from: org.xbill.DNS.d */
/* loaded from: classes3.dex */
public class C5858d {

    /* renamed from: a */
    public byte[] f20258a;

    /* renamed from: b */
    public int f20259b;

    /* renamed from: c */
    public int f20260c;

    public C5858d(int i9) {
        this.f20258a = new byte[i9];
        this.f20259b = 0;
        this.f20260c = -1;
    }

    /* renamed from: a */
    public final void m23233a(long j9, int i9) {
        long j10 = 1 << i9;
        if (j9 < 0 || j9 > j10) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(j9);
            stringBuffer.append(" out of range for ");
            stringBuffer.append(i9);
            stringBuffer.append(" bit value");
            throw new IllegalArgumentException(stringBuffer.toString());
        }
    }

    /* renamed from: b */
    public int m23234b() {
        return this.f20259b;
    }

    /* renamed from: c */
    public final void m23235c(int i9) {
        byte[] bArr = this.f20258a;
        int length = bArr.length;
        int i10 = this.f20259b;
        if (length - i10 >= i9) {
            return;
        }
        int length2 = bArr.length * 2;
        if (length2 < i10 + i9) {
            length2 = i10 + i9;
        }
        byte[] bArr2 = new byte[length2];
        System.arraycopy(bArr, 0, bArr2, 0, i10);
        this.f20258a = bArr2;
    }

    /* renamed from: d */
    public byte[] m23236d() {
        int i9 = this.f20259b;
        byte[] bArr = new byte[i9];
        System.arraycopy(this.f20258a, 0, bArr, 0, i9);
        return bArr;
    }

    /* renamed from: e */
    public void m23237e(byte[] bArr) {
        m23238f(bArr, 0, bArr.length);
    }

    /* renamed from: f */
    public void m23238f(byte[] bArr, int i9, int i10) {
        m23235c(i10);
        System.arraycopy(bArr, i9, this.f20258a, this.f20259b, i10);
        this.f20259b += i10;
    }

    /* renamed from: g */
    public void m23239g(byte[] bArr) {
        if (bArr.length > 255) {
            throw new IllegalArgumentException("Invalid counted string");
        }
        m23235c(bArr.length + 1);
        byte[] bArr2 = this.f20258a;
        int i9 = this.f20259b;
        this.f20259b = i9 + 1;
        bArr2[i9] = (byte) (255 & bArr.length);
        m23238f(bArr, 0, bArr.length);
    }

    /* renamed from: h */
    public void m23240h(int i9) {
        m23233a(i9, 16);
        m23235c(2);
        byte[] bArr = this.f20258a;
        int i10 = this.f20259b;
        int i11 = i10 + 1;
        bArr[i10] = (byte) ((i9 >>> 8) & 255);
        this.f20259b = i11 + 1;
        bArr[i11] = (byte) (i9 & 255);
    }

    /* renamed from: i */
    public void m23241i(int i9, int i10) {
        m23233a(i9, 16);
        if (i10 > this.f20259b - 2) {
            throw new IllegalArgumentException("cannot write past end of data");
        }
        byte[] bArr = this.f20258a;
        bArr[i10] = (byte) ((i9 >>> 8) & 255);
        bArr[i10 + 1] = (byte) (i9 & 255);
    }

    /* renamed from: j */
    public void m23242j(long j9) {
        m23233a(j9, 32);
        m23235c(4);
        byte[] bArr = this.f20258a;
        int i9 = this.f20259b;
        int i10 = i9 + 1;
        bArr[i9] = (byte) ((j9 >>> 24) & 255);
        int i11 = i10 + 1;
        bArr[i10] = (byte) ((j9 >>> 16) & 255);
        int i12 = i11 + 1;
        bArr[i11] = (byte) ((j9 >>> 8) & 255);
        this.f20259b = i12 + 1;
        bArr[i12] = (byte) (j9 & 255);
    }

    /* renamed from: k */
    public void m23243k(int i9) {
        m23233a(i9, 8);
        m23235c(1);
        byte[] bArr = this.f20258a;
        int i10 = this.f20259b;
        this.f20259b = i10 + 1;
        bArr[i10] = (byte) (i9 & 255);
    }

    public C5858d() {
        this(32);
    }
}
