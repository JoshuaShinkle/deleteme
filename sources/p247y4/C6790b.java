package p247y4;

/* renamed from: y4.b */
/* loaded from: classes2.dex */
public class C6790b {

    /* renamed from: a */
    public final char[] f22529a;

    /* renamed from: b */
    public int f22530b = 0;

    public C6790b(String str) {
        this.f22529a = str.toCharArray();
    }

    /* renamed from: a */
    public boolean m25339a(int i9) {
        return this.f22529a.length >= this.f22530b + i9;
    }

    /* renamed from: b */
    public boolean m25340b() {
        return this.f22529a.length <= this.f22530b;
    }

    /* renamed from: c */
    public int m25341c() {
        return this.f22530b;
    }

    /* renamed from: d */
    public void m25342d() {
        this.f22530b--;
    }

    /* renamed from: e */
    public String m25343e(int i9) {
        return new String(this.f22529a, this.f22530b, i9);
    }

    /* renamed from: f */
    public char m25344f(int i9) {
        if (m25339a(i9)) {
            return this.f22529a[this.f22530b + i9];
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    /* renamed from: g */
    public char m25345g() {
        char[] cArr = this.f22529a;
        int i9 = this.f22530b;
        this.f22530b = i9 + 1;
        char c9 = cArr[i9];
        if (C6789a.m25337i(c9)) {
            return ' ';
        }
        return c9;
    }

    /* renamed from: h */
    public void m25346h(int i9) {
        this.f22530b = i9;
    }
}
