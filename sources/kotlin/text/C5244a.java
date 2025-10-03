package kotlin.text;

import p027c6.C0747c;

/* renamed from: kotlin.text.a */
/* loaded from: classes2.dex */
public class C5244a {
    /* renamed from: a */
    public static final int m20494a(int i9) {
        if (new C0747c(2, 36).m3616f(i9)) {
            return i9;
        }
        throw new IllegalArgumentException("radix " + i9 + " was not in valid range " + new C0747c(2, 36));
    }

    /* renamed from: b */
    public static final int m20495b(char c9, int i9) {
        return Character.digit((int) c9, i9);
    }

    /* renamed from: c */
    public static final boolean m20496c(char c9) {
        return Character.isWhitespace(c9) || Character.isSpaceChar(c9);
    }
}
