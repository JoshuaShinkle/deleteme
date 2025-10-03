package p203t5;

import org.apache.commons.lang3.ClassUtils;
import p007a6.C0040d;
import p007a6.C0042f;
import p027c6.C0747c;

/* renamed from: t5.b */
/* loaded from: classes2.dex */
public final class C6314b implements Comparable<C6314b> {

    /* renamed from: f */
    public static final a f21306f = new a(null);

    /* renamed from: g */
    public static final C6314b f21307g = C6315c.m24150a();

    /* renamed from: b */
    public final int f21308b;

    /* renamed from: c */
    public final int f21309c;

    /* renamed from: d */
    public final int f21310d;

    /* renamed from: e */
    public final int f21311e;

    /* renamed from: t5.b$a */
    public static final class a {
        public a() {
        }

        public /* synthetic */ a(C0040d c0040d) {
            this();
        }
    }

    public C6314b(int i9, int i10, int i11) {
        this.f21308b = i9;
        this.f21309c = i10;
        this.f21310d = i11;
        this.f21311e = m24149b(i9, i10, i11);
    }

    @Override // java.lang.Comparable
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compareTo(C6314b c6314b) {
        C0042f.m158e(c6314b, "other");
        return this.f21311e - c6314b.f21311e;
    }

    /* renamed from: b */
    public final int m24149b(int i9, int i10, int i11) {
        boolean z8 = false;
        if (new C0747c(0, 255).m3616f(i9) && new C0747c(0, 255).m3616f(i10) && new C0747c(0, 255).m3616f(i11)) {
            z8 = true;
        }
        if (z8) {
            return (i9 << 16) + (i10 << 8) + i11;
        }
        throw new IllegalArgumentException(("Version components are out of range: " + i9 + ClassUtils.PACKAGE_SEPARATOR_CHAR + i10 + ClassUtils.PACKAGE_SEPARATOR_CHAR + i11).toString());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        C6314b c6314b = obj instanceof C6314b ? (C6314b) obj : null;
        return c6314b != null && this.f21311e == c6314b.f21311e;
    }

    public int hashCode() {
        return this.f21311e;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.f21308b);
        sb.append(ClassUtils.PACKAGE_SEPARATOR_CHAR);
        sb.append(this.f21309c);
        sb.append(ClassUtils.PACKAGE_SEPARATOR_CHAR);
        sb.append(this.f21310d);
        return sb.toString();
    }
}
