package p209u2;

/* renamed from: u2.c */
/* loaded from: classes.dex */
public final class C6366c {

    /* renamed from: a */
    public final int f21508a;

    /* renamed from: b */
    public final long f21509b;

    /* renamed from: c */
    public int f21510c;

    /* renamed from: d */
    public long f21511d;

    public C6366c(int i9) {
        this(i9, 750L);
    }

    /* renamed from: a */
    public boolean m24458a() {
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (jCurrentTimeMillis - this.f21511d > this.f21509b) {
            this.f21510c = 0;
        }
        int i9 = this.f21510c + 1;
        this.f21510c = i9;
        this.f21511d = jCurrentTimeMillis;
        boolean z8 = i9 >= this.f21508a;
        if (z8) {
            this.f21510c = 0;
        }
        return z8;
    }

    public C6366c(int i9, long j9) {
        this.f21510c = 0;
        this.f21511d = 0L;
        this.f21508a = i9;
        this.f21509b = j9;
    }
}
