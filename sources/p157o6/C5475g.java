package p157o6;

import p007a6.C0040d;
import p007a6.C0042f;

/* renamed from: o6.g */
/* loaded from: classes.dex */
public final class C5475g {

    /* renamed from: c */
    public static final a f18451c = new a(null);

    /* renamed from: a */
    public int f18452a;

    /* renamed from: b */
    public final int[] f18453b = new int[10];

    /* renamed from: o6.g$a */
    public static final class a {
        public a() {
        }

        public /* synthetic */ a(C0040d c0040d) {
            this();
        }
    }

    /* renamed from: a */
    public final int m21177a(int i9) {
        return this.f18453b[i9];
    }

    /* renamed from: b */
    public final int m21178b() {
        if ((this.f18452a & 2) != 0) {
            return this.f18453b[1];
        }
        return -1;
    }

    /* renamed from: c */
    public final int m21179c() {
        if ((this.f18452a & 128) != 0) {
            return this.f18453b[7];
        }
        return 65535;
    }

    /* renamed from: d */
    public final int m21180d() {
        if ((this.f18452a & 16) != 0) {
            return this.f18453b[4];
        }
        return Integer.MAX_VALUE;
    }

    /* renamed from: e */
    public final int m21181e(int i9) {
        return (this.f18452a & 32) != 0 ? this.f18453b[5] : i9;
    }

    /* renamed from: f */
    public final boolean m21182f(int i9) {
        return ((1 << i9) & this.f18452a) != 0;
    }

    /* renamed from: g */
    public final void m21183g(C5475g c5475g) {
        C0042f.m158e(c5475g, "other");
        for (int i9 = 0; i9 < 10; i9++) {
            if (c5475g.m21182f(i9)) {
                m21184h(i9, c5475g.m21177a(i9));
            }
        }
    }

    /* renamed from: h */
    public final C5475g m21184h(int i9, int i10) {
        if (i9 >= 0) {
            int[] iArr = this.f18453b;
            if (i9 < iArr.length) {
                this.f18452a = (1 << i9) | this.f18452a;
                iArr[i9] = i10;
            }
        }
        return this;
    }

    /* renamed from: i */
    public final int m21185i() {
        return Integer.bitCount(this.f18452a);
    }
}
