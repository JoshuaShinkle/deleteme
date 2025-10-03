package androidx.appcompat.app;

/* renamed from: androidx.appcompat.app.h */
/* loaded from: classes.dex */
public class C0126h {

    /* renamed from: d */
    public static C0126h f388d;

    /* renamed from: a */
    public long f389a;

    /* renamed from: b */
    public long f390b;

    /* renamed from: c */
    public int f391c;

    /* renamed from: b */
    public static C0126h m406b() {
        if (f388d == null) {
            f388d = new C0126h();
        }
        return f388d;
    }

    /* renamed from: a */
    public void m407a(long j9, double d9, double d10) {
        double d11 = (0.01720197f * ((j9 - 946728000000L) / 8.64E7f)) + 6.24006f;
        double dSin = (Math.sin(d11) * 0.03341960161924362d) + d11 + (Math.sin(2.0f * r4) * 3.4906598739326E-4d) + (Math.sin(r4 * 3.0f) * 5.236000106378924E-6d) + 1.796593063d + 3.141592653589793d;
        double dRound = Math.round((r3 - 9.0E-4f) - r7) + 9.0E-4f + ((-d10) / 360.0d) + (Math.sin(d11) * 0.0053d) + (Math.sin(2.0d * dSin) * (-0.0069d));
        double dAsin = Math.asin(Math.sin(dSin) * Math.sin(0.4092797040939331d));
        double d12 = 0.01745329238474369d * d9;
        double dSin2 = (Math.sin(-0.10471975803375244d) - (Math.sin(d12) * Math.sin(dAsin))) / (Math.cos(d12) * Math.cos(dAsin));
        if (dSin2 >= 1.0d) {
            this.f391c = 1;
            this.f389a = -1L;
            this.f390b = -1L;
        } else {
            if (dSin2 <= -1.0d) {
                this.f391c = 0;
                this.f389a = -1L;
                this.f390b = -1L;
                return;
            }
            double dAcos = (float) (Math.acos(dSin2) / 6.283185307179586d);
            this.f389a = Math.round((dRound + dAcos) * 8.64E7d) + 946728000000L;
            long jRound = Math.round((dRound - dAcos) * 8.64E7d) + 946728000000L;
            this.f390b = jRound;
            if (jRound >= j9 || this.f389a <= j9) {
                this.f391c = 1;
            } else {
                this.f391c = 0;
            }
        }
    }
}
