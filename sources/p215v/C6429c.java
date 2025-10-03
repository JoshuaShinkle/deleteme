package p215v;

import android.graphics.Insets;

/* renamed from: v.c */
/* loaded from: classes.dex */
public final class C6429c {

    /* renamed from: e */
    public static final C6429c f21655e = new C6429c(0, 0, 0, 0);

    /* renamed from: a */
    public final int f21656a;

    /* renamed from: b */
    public final int f21657b;

    /* renamed from: c */
    public final int f21658c;

    /* renamed from: d */
    public final int f21659d;

    public C6429c(int i9, int i10, int i11, int i12) {
        this.f21656a = i9;
        this.f21657b = i10;
        this.f21658c = i11;
        this.f21659d = i12;
    }

    /* renamed from: a */
    public static C6429c m24592a(int i9, int i10, int i11, int i12) {
        return (i9 == 0 && i10 == 0 && i11 == 0 && i12 == 0) ? f21655e : new C6429c(i9, i10, i11, i12);
    }

    /* renamed from: b */
    public Insets m24593b() {
        return Insets.of(this.f21656a, this.f21657b, this.f21658c, this.f21659d);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || C6429c.class != obj.getClass()) {
            return false;
        }
        C6429c c6429c = (C6429c) obj;
        return this.f21659d == c6429c.f21659d && this.f21656a == c6429c.f21656a && this.f21658c == c6429c.f21658c && this.f21657b == c6429c.f21657b;
    }

    public int hashCode() {
        return (((((this.f21656a * 31) + this.f21657b) * 31) + this.f21658c) * 31) + this.f21659d;
    }

    public String toString() {
        return "Insets{left=" + this.f21656a + ", top=" + this.f21657b + ", right=" + this.f21658c + ", bottom=" + this.f21659d + '}';
    }
}
