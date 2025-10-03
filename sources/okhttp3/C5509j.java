package okhttp3;

import java.util.concurrent.TimeUnit;
import okhttp3.internal.connection.C5500f;
import p007a6.C0042f;
import p129l6.C5298e;

/* renamed from: okhttp3.j */
/* loaded from: classes2.dex */
public final class C5509j {

    /* renamed from: a */
    public final C5500f f18892a;

    public C5509j(C5500f c5500f) {
        C0042f.m158e(c5500f, "delegate");
        this.f18892a = c5500f;
    }

    /* renamed from: a */
    public final C5500f m21554a() {
        return this.f18892a;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public C5509j(int i9, long j9, TimeUnit timeUnit) {
        this(new C5500f(C5298e.f17983i, i9, j9, timeUnit));
        C0042f.m158e(timeUnit, "timeUnit");
    }

    public C5509j() {
        this(5, 5L, TimeUnit.MINUTES);
    }
}
