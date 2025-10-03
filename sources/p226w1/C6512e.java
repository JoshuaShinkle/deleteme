package p226w1;

import android.annotation.TargetApi;
import android.os.SystemClock;

/* renamed from: w1.e */
/* loaded from: classes.dex */
public final class C6512e {

    /* renamed from: a */
    public static final double f21917a = 1.0d / Math.pow(10.0d, 6.0d);

    /* renamed from: a */
    public static double m24922a(long j9) {
        return (m24923b() - j9) * f21917a;
    }

    @TargetApi(17)
    /* renamed from: b */
    public static long m24923b() {
        return SystemClock.elapsedRealtimeNanos();
    }
}
