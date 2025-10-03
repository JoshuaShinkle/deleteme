package p221v5;

/* renamed from: v5.c */
/* loaded from: classes2.dex */
public final class C6487c {
    /* renamed from: a */
    public static final int m24820a(int i9, int i10, int i11) {
        return m24822c(m24822c(i9, i11) - m24822c(i10, i11), i11);
    }

    /* renamed from: b */
    public static final int m24821b(int i9, int i10, int i11) {
        if (i11 > 0) {
            return i9 >= i10 ? i10 : i10 - m24820a(i10, i9, i11);
        }
        if (i11 < 0) {
            return i9 <= i10 ? i10 : i10 + m24820a(i9, i10, -i11);
        }
        throw new IllegalArgumentException("Step is zero.");
    }

    /* renamed from: c */
    public static final int m24822c(int i9, int i10) {
        int i11 = i9 % i10;
        return i11 >= 0 ? i11 : i11 + i10;
    }
}
