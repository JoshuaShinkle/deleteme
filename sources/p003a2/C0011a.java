package p003a2;

import android.text.TextUtils;

/* renamed from: a2.a */
/* loaded from: classes.dex */
public final class C0011a implements Comparable<C0011a> {

    /* renamed from: e */
    public static final C0011a f62e = new C0011a(-1, "Unknown", 0);

    /* renamed from: b */
    public final int f63b;

    /* renamed from: c */
    public String f64c;

    /* renamed from: d */
    public final long f65d;

    public C0011a(int i9, String str, long j9) {
        this.f63b = i9;
        this.f64c = str;
        this.f65d = j9;
    }

    /* renamed from: a */
    public boolean m66a(String str) throws NumberFormatException {
        long j9;
        if (TextUtils.isEmpty(str)) {
            j9 = 0;
        } else {
            try {
                j9 = Long.parseLong(str);
            } catch (NumberFormatException unused) {
                j9 = -1;
            }
        }
        return this.f65d == j9;
    }

    @Override // java.lang.Comparable
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public int compareTo(C0011a c0011a) {
        int iCompareTo = this.f64c.compareTo(c0011a.f64c);
        return iCompareTo != 0 ? iCompareTo : Integer.compare(this.f63b, c0011a.f63b);
    }

    /* renamed from: c */
    public String m68c() {
        return this.f64c;
    }

    /* renamed from: d */
    public String m69d() {
        return this.f64c + "(" + this.f63b + ")";
    }

    /* renamed from: e */
    public boolean m70e(String str) {
        if (TextUtils.equals(this.f64c, str)) {
            return false;
        }
        this.f64c = str;
        return true;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof C0011a)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        C0011a c0011a = (C0011a) obj;
        return this.f63b == c0011a.f63b && TextUtils.equals(this.f64c, c0011a.f64c) && this.f65d == c0011a.f65d;
    }

    public C0011a(int i9, String str, String str2) throws NumberFormatException {
        long j9;
        this.f63b = i9;
        this.f64c = str;
        if (TextUtils.isEmpty(str2)) {
            j9 = 0;
        } else {
            try {
                j9 = Long.parseLong(str2);
            } catch (NumberFormatException unused) {
                j9 = -1;
            }
        }
        this.f65d = j9;
    }
}
