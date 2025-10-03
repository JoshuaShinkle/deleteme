package p209u2;

/* renamed from: u2.i */
/* loaded from: classes.dex */
public class C6372i {

    /* renamed from: a */
    public final int f21518a;

    /* renamed from: b */
    public double f21519b;

    /* renamed from: c */
    public double f21520c;

    /* renamed from: d */
    public double[] f21521d;

    /* renamed from: e */
    public int f21522e;

    public C6372i(int i9) {
        this.f21518a = i9;
        this.f21521d = new double[i9];
    }

    /* renamed from: a */
    public void m24482a(double d9) {
        double d10 = this.f21519b;
        double[] dArr = this.f21521d;
        int i9 = this.f21522e;
        double d11 = d10 - dArr[i9];
        int i10 = i9 + 1;
        this.f21522e = i10;
        dArr[i9] = d9;
        this.f21520c = d9;
        this.f21519b = d11 + d9;
        if (i10 >= this.f21518a) {
            this.f21522e = 0;
        }
    }

    /* renamed from: b */
    public double m24483b() {
        return this.f21519b / this.f21518a;
    }
}
